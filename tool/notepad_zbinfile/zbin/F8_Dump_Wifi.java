import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class F8_Dump_Wifi {

//   getprop.txt  中读取 [ro.bui  ld.version.release]: [10]
//getprop.txt   [ro.hardware.soc.manufacturer]: [qcom]   制造商
//  如果不包含  那么 检测 是否包含MTK
    // .mtk  或者  .mtk
    // .qcom  或者  qcom.



    // dumpsys wifi     相关的类
    ///frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiServiceImpl.java dump()


    // DhcpClient.java  connmetrics.txt    包含状态机
//    addState(mStoppedState);
//    addState(mDhcpState);
//    addState(mDhcpInitState, mDhcpState);
//    addState(mWaitBeforeStartState, mDhcpState);
//    addState(mDhcpSelectingState, mDhcpState);
//    addState(mDhcpRequestingState, mDhcpState);
//    addState(mDhcpHaveLeaseState, mDhcpState);
//    addState(mConfiguringInterfaceState, mDhcpHaveLeaseState);
//    addState(mDhcpBoundState, mDhcpHaveLeaseState);
//    addState(mWaitBeforeRenewalState, mDhcpHaveLeaseState);
//    addState(mDhcpRenewingState, mDhcpHaveLeaseState);
//    addState(mDhcpRebindingState, mDhcpHaveLeaseState);
//    addState(mDhcpInitRebootState, mDhcpState);
//    addState(mDhcpRebootingState, mDhcpState);
//    setInitialState(mStoppedState);


    static String F8DirPathStr = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F8";
    static File F8DirFile = new File(F8DirPathStr);
    static  String getpropFileName = "getprop.txt";
    static  String wifiFileName = "wifi.txt";
    static Map<String,File> allDumpMap = new HashMap<String,File>();

    // 获取在目录 F8DirFile 下 所有文件的 绝对路径
    static   ArrayList<File> AllDumpFileList  = new ArrayList<File>();

    static Map<String,String> fileNameMapClass = new HashMap<String,String>();
    static{

        fileNameMapClass.put(wifiFileName,"/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiServiceImpl.java");

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
        F8_Dump_Wifi mDumpAnalysis = new F8_Dump_Wifi();
        mDumpAnalysis.initAnalysisWithVersion();
        curAndroidAnalysis.analysisFile();



    }


    void initAnalysisWithVersion(){

        int version = Integer.parseInt(mAndroidVersion);
        System.out.println("initAnalysisWithVersion version = "+ version);
        switch (version){
            case 9:
                curAndroidAnalysis = new Android9Analysis(mAndroidVersion,mVerndor);
                break;
            case 10:
                curAndroidAnalysis = new Android10Analysis(mAndroidVersion,mVerndor);
                System.out.println("curAndroidAnalysis 10 = "+curAndroidAnalysis);
                break;

            case 11:
                curAndroidAnalysis = new Android11Analysis(mAndroidVersion,mVerndor);
                break;
            default:
                curAndroidAnalysis = new Android10Analysis(mAndroidVersion,mVerndor);

        }


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

        ArrayList<String> getVersionNeedAnalysisFileNameList(String versionStr){
            if(versionStr == null)
                return null;
            int version = Integer.parseInt(versionStr);
            ArrayList<String> listFilename = new  ArrayList<String>();
            switch (version){
                case 9:
                    listFilename.add(getpropFileName);

                    break;
                case 10:
                    listFilename.add(getpropFileName);
                    listFilename.add(wifiFileName);
                    listFilename.add("wifiscanner.txt");
                    break;

                case 11:
                    listFilename.add(getpropFileName);

                    break;
                default:
                    listFilename.add(getpropFileName);

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

            // wifi.txt;
            file_keyword_map.put("wifi.txt",initWifikeyWordList());

            // wifi.txt;
            file_keyword_map.put("wifiscanner.txt",initWifiScannerkeyWordList());

            return file_keyword_map;
        }


        ArrayList<KeyWordItem>     initWifiScannerkeyWordList(){
            ArrayList<KeyWordItem> wifiScanner_KeyWordList = new ArrayList<KeyWordItem>();


            KeyWordItem wifisacnner_1_0 = new KeyWordItem();
            wifisacnner_1_0.keyword = "WifiScanningService - Log Begin";
            wifisacnner_1_0.explain="WIFI扫描实现类 ";
            wifisacnner_1_0.printLogUrl="WifiScanningService => LocalLog mLocalLog = new LocalLog(512);  \n" +
                    "WifiScanningService => void dump() {}\n" +
                    "\n " +
                    "pw.println(\"WifiScanningService - Log Begin ----\");\n" +
                    "mLocalLog.dump(fd, pw, args);\n" +
                    "pw.println(\"WifiScanningService - Log End ----\");";
            wifisacnner_1_0.expain1="client connected \\ 标识 对应的UID 与 扫描服务建立起通信 \n" +
                    "registerScanListene  \\ 每个UID 都有自己独立的 监听id  标识 对应的UID 开始监听扫描结果  id 标识( int handler ),来自 int handler = Message.arg2; 用于唯一标识客户监听类 \n" +
                    "每当调用以下接口监听扫描 都会生成对应的 int key , 用于作为 监听类的 identify \n" +
                    "1.每个APP都可以监听扫描结果,WifiScanner 被独立为一个单独的服务.\n" +
                    "2.每个APP维护一个 key List, 这个 key 从1 开始递增, key=0 标识无效的 监听标识\n" +
                    "  UID=1000 Setting 维护 1.2.3.4.5..... 自身的key 列表\n" +
                    "  UID=10159 com.google.android.gms 也维护自身的 key 列表 \n" +
                    "3.每一个key 标识一个在APP实现的监听接口   (uid,key) 唯一标识一个监听实现类\n" +
                    "4.通过WifiScanner.java的 5个方式 来进行监听 并 获取 key \n" +
                    "  void registerScanListener(ScanListener listener) { int key = addListener(listener);}\n" +
                    "  void startBackgroundScan(ScanSettings settings, ScanListener listener,WorkSource workSource) { int key = addListener(listener);}\n" +
                    "  void startScan(ScanSettings settings, ScanListener listener, WorkSource workSource) {int key = addListener(listener);}\n" +
                    "  void startConnectedPnoScan(ScanSettings scanSettings, PnoSettings pnoSettings,PnoScanListener listener) {int key = addListener(listener);}\n" +
                    "  void startDisconnectedPnoScan(ScanSettings scanSettings, PnoSettings pnoSettings,PnoScanListener listener) {int key = addListener(listener);}\n" +
                    "5.registerScanListener 标识一个全监听,即如果有任何的扫描结果 都需要回调 fullsacn接口实现类\n" +
                    "  addSingleScanRequest 仅标识 我这个接口 只进行一次 扫描监听就完成使命，再一下次不需要再次回调 singlescan的实现接口\n\n";
            wifiScanner_KeyWordList.add(wifisacnner_1_0);




            KeyWordItem wifisacnner_1_1 = new KeyWordItem();
            wifisacnner_1_1.keyword = "WifiSingleScanStateMachine:";
            wifisacnner_1_1.explain="WIFI扫描实现类 ";
            wifisacnner_1_1.printLogUrl="WifiScanningServiceImpl.java dump(FileDescriptor fd, PrintWriter pw, String[] args)";
            wifisacnner_1_1.expain1="  _-----=> ScanningState\n" +
                    " / _----=> IdleState\n" +
                    "| /\n" +
                    "|| \n" +
                    "DriverStartedState\n" +
                    "|| \n" +
                    "DefaultState";
            wifiScanner_KeyWordList.add(wifisacnner_1_1);



            KeyWordItem wifisacnner_1_2 = new KeyWordItem();
            wifisacnner_1_2.keyword = "clients:";
            wifisacnner_1_2.explain="WIFI扫描监听接口实现类 的 集合  【uid && hashcode】作为标识符   WifiScanningServiceImpl.java  ArrayMap<Messenger, ClientInfo> mClients";
            wifisacnner_1_2.printLogUrl="WifiScanningServiceImpl.java dump(FileDescriptor fd, PrintWriter pw, String[] args)";
            wifisacnner_1_2.expain1="class WifiScanningServiceImpl extends IWifiScanner.Stub {\n" +
                    "\n" +
                    "ArrayMap<Messenger, ClientInfo> mClients;   // 打印实现监听接口的客户端信息\n" +
                    "dump(){\n" +
                    "\tpw.println(\"clients:\");\n" +
                    "\tfor (ClientInfo client : mClients.values()) {\n" +
                    "\t\tpw.println(\"  \" + client);\n" +
                    "\t}\n" +
                    "}\n" +
                    "\n" +
                    "}";
            wifiScanner_KeyWordList.add(wifisacnner_1_2);



            KeyWordItem wifisacnner_1_3 = new KeyWordItem();
            wifisacnner_1_3.keyword = "listeners:";
            wifisacnner_1_3.explain="打印后台扫描监听接口  以及 对应的扫描配置  ScanSettings  【uid && hashcode】作为标识符  ";
            wifisacnner_1_3.printLogUrl=" WifiScanningServiceImpl.java   WifiBackgroundScanStateMachine mBackgroundScanStateMachine; // 后台扫描状态机";
            wifisacnner_1_3.expain1="class WifiScanningServiceImpl extends IWifiScanner.Stub {\n" +
                    "\n" +
                    "WifiBackgroundScanStateMachine mBackgroundScanStateMachine; // 后台扫描状态机\n" +
                    "dump(){\n" +
                    "\n" +
                    "\tpw.println(\"listeners:\");\n" +
                    "\tfor (ClientInfo client : mClients.values()) {\n" +
                    "\t\tCollection<ScanSettings> settingsList = mBackgroundScanStateMachine.getBackgroundScanSettings(client);\n" +
                    "\t\tfor (ScanSettings settings : settingsList) {\n" +
                    "\t\t\tpw.println(\"  \" + toString(client.mUid, settings));  // 打印UID 和 扫描配置ScanSettings信息\n" +
                    "\t\t}\n" +
                    "\t}\t\n" +
                    "}\n" +
                    "}\n" +
                    "" +
                    "  _-----=> StartedState\n" +
                    " / _----=> PausedState\n" +
                    "| /\n" +
                    "|| \n" +
                    "DefaultState\n" +
                    "WifiBackgroundScanStateMachine 后台扫描状态机";
            wifiScanner_KeyWordList.add(wifisacnner_1_3);




            KeyWordItem wifisacnner_1_4 = new KeyWordItem();
            wifisacnner_1_4.keyword = "schedule:";
            wifisacnner_1_4.explain="BackgroundScanScheduler mBackgroundScheduler;  // 后台扫描计划schedule";
            wifisacnner_1_4.printLogUrl="WifiScanningServiceImpl.java dump(FileDescriptor fd, PrintWriter pw, String[] args)";
            wifisacnner_1_4.expain1="******************** BackgroundScanScheduler.Bucket Begin ********************\n" +
                    "    /**\n" +
                    "     * This class is an intermediate representation for scheduling. This maintins the channel\n" +
                    "     * collection to be scanned by the bucket as settings are added to it.\n" +
                    "     */ // 扫描配置信息的集合  \n" +
                    "    private class Bucket {\n" +
                    "        public int period;   // 进行扫描的间隔 \n" +
                    "        public int bucketId;   //  桶的 id \n" +
                    "        private final List<ScanSettings> mScanSettingsList = new ArrayList<>();\n" +
                    "        private final ChannelCollection mChannelCollection;\n" +
                    "\t}\n" +
                    "******************** BackgroundScanScheduler.Bucket End ********************\n" +
                    "\t\t\n" +
                    "******************** WifiNative.ScanSettings Begin ********************\n" +
                    "\n" +
                    "    public static class ScanSettings {\n" +
                    "        /**\n" +
                    "         * Type of scan to perform. One of {@link ScanSettings#SCAN_TYPE_LOW_LATENCY},\n" +
                    "         * {@link ScanSettings#SCAN_TYPE_LOW_POWER} or {@link ScanSettings#SCAN_TYPE_HIGH_ACCURACY}.\n" +
                    "         */\n" +
                    "        public int scanType;  // 高性能 HIGH_ACCURACY   // 低延时 LOW_LATENCY  // 低功耗 LOW_POWER \n" +
                    "        public int base_period_ms;  // 间隔时间 毫秒\n" +
                    "        public int max_ap_per_scan;  // 间隔时间 毫秒\n" +
                    "        public int report_threshold_percent;  // 百分比 100% \n" +
                    "        public int report_threshold_num_scans;  // int DEFAULT_MAX_SCANS_TO_BATCH = 10; \n" +
                    "        public int num_buckets;  // int DEFAULT_MAX_BUCKETS = 8;   最大8个监听类获取wifi扫描结果? \n" +
                    "\t\t\n" +
                    "        /* Not used for bg scans. Only works for single scans. */\n" +
                    "        public HiddenNetwork[] hiddenNetworks;\n" +
                    "        public BucketSettings[] buckets;\n" +
                    "    }\n" +
                    "\t\n" +
                    "******************** WifiNative.ScanSettings End ********************\n" +
                    "\n" +
                    "class WifiScanningServiceImpl extends IWifiScanner.Stub {\n" +
                    "\n" +
                    "BackgroundScanScheduler mBackgroundScheduler;  // 后台扫描计划schedule 其实是一个 WifiNative.ScanSettings \n" +
                    "\n" +
                    "dump(){\n" +
                    "\n" +
                    "        if (mBackgroundScheduler != null) {\n" +
                    "            WifiNative.ScanSettings schedule = mBackgroundScheduler.getSchedule();\n" +
                    "            if (schedule != null) {    //  打印后台扫描计划 schedule\n" +
                    "                pw.println(\"schedule:\");\n" +
                    "                pw.println(\"  base period: \" + schedule.base_period_ms);  // 扫描间隔时间 毫秒\n" +
                    "                pw.println(\"  max ap per scan: \" + schedule.max_ap_per_scan);  // 间隔时间 毫秒 \n" +
                    "                pw.println(\"  batched scans: \" + schedule.report_threshold_num_scans);   // 基本的上报扫描的个数，不能小于 int DEFAULT_MAX_SCANS_TO_BATCH = 10; \n" +
                    "                pw.println(\"  buckets:\");\n" +
                    "for (int b = 0; b < schedule.num_buckets; b++) {  // 打印   BucketSettings[]   只用于 singlescan\n" +
                    " WifiNative.BucketSettings bucket = schedule.buckets[b];\n" +
                    " pw.println(\"bucket \" + bucket.bucket + \"(\" + bucket.period_ms + \"ms)[\" + bucket.report_events + \"]:\"+ChannelHelper.toString(bucket));\n" +
                    "}\n" +
                    "            }\n" +
                    "        }\n" +
                    "}\n" +
                    "}";
            wifiScanner_KeyWordList.add(wifisacnner_1_4);




            KeyWordItem wifisacnner_1_5 = new KeyWordItem();
            wifisacnner_1_5.keyword = "WifiPnoScanStateMachine:";
            wifisacnner_1_5.explain=" WifiPnoScanStateMachine 状态机 事件列表";
            wifisacnner_1_5.printLogUrl="WifiScanningServiceImpl.WifiPnoScanStateMachine PNO扫描状态机";
            wifisacnner_1_5.expain1="WifiScanningServiceImpl.WifiPnoScanStateMachine PNO扫描状态机\n" +
                    "\n" +
                    "**********************  boolean  config_wifi_background_scan_support **********************\n" +
                    "// Check if the device supports HW PNO scans.  是否支持PNO 扫描\n" +
                    "boolean mHwPnoScanSupported = mContext.getResources().getBoolean(R.bool.config_wifi_background_scan_support);\n" +
                    "frameworks/base/core/res/res/values/config.xml\n" +
                    "<!-- Boolean indicating whether the wifi chipset supports background scanning mechanism.\n" +
                    "\t This mechanism allows the host to remain in suspend state and the dongle to actively\n" +
                    "\t scan and wake the host when a configured SSID is detected by the dongle. This chipset\n" +
                    "\t capability can provide power savings when wifi needs to be always kept on. -->\n" +
                    "<bool translatable=\"false\" name=\"config_wifi_background_scan_support\">false</bool>  // PNO扫描支持开关  wifi芯片支持\n" +
                    "**********************  boolean  config_wifi_background_scan_support **********************\n" +
                    "\n" +
                    "class WifiScanningServiceImpl extends IWifiScanner.Stub {\n" +
                    "\t\t\t\t\n" +
                    "WifiPnoScanStateMachine mPnoScanStateMachine;  // PNO扫描状态 \n" +
                    "\n" +
                    "\t\t\t\n" +
                    "dump(){\n" +
                    "if (mPnoScanStateMachine != null) {\n" +
                    "\tmPnoScanStateMachine.dump(fd, pw, args);\n" +
                    "}\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "SingleScanState\n" +
                    "   ||\n" +
                    "HwPnoScanState\n" +
                    "   || \n" +
                    "StartedState\n" +
                    "   || \n" +
                    "DefaultState";
            wifiScanner_KeyWordList.add(wifisacnner_1_5);



            KeyWordItem wifisacnner_1_6 = new KeyWordItem();
            wifisacnner_1_6.keyword = "Latest scan results:";
            wifisacnner_1_6.explain=" 最新扫描结果 ==》 List<ScanResult> scanResults = mSingleScanStateMachine.getCachedScanResultsAsList()";
            wifisacnner_1_6.printLogUrl="WifiScanningServiceImpl.java dump(FileDescriptor fd, PrintWriter pw, String[] args)";
            wifisacnner_1_6.expain1=" _-----=> ScanningState\n" +
                    "/  _----=> IdleState\n" +
                    "| / \n" +
                    "|| \n" +
                    "DriverStartedState\n" +
                    "|| \n" +
                    "DefaultState\n" +
                    "WifiSingleScanStateMachine  // 单扫描状态机  \n" +
                    "\n" +
                    "class WifiScanningServiceImpl extends IWifiScanner.Stub {\n" +
                    "WifiSingleScanStateMachine mSingleScanStateMachine;  // 单扫描状态机  \n" +
                    "\n" +
                    "dump(){\n" +
                    "  if (mSingleScanStateMachine != null) {\n" +
                    "\t\tmSingleScanStateMachine.dump(fd, pw, args);\n" +
                    "\t\tpw.println();\n" +
                    "\t\tpw.println(\"Latest scan results:\");   // 打印最新的扫描结果 单扫描扫描结果\n" +
                    "\t\tList<ScanResult> scanResults = mSingleScanStateMachine.getCachedScanResultsAsList();\n" +
                    "\t\tlong nowMs = mClock.getElapsedSinceBootMillis();\n" +
                    "\t\tScanResultUtil.dumpScanResults(pw, scanResults, nowMs);  // 可以查看它的对齐 实现方式  \n" +
                    "\t\tpw.println();\n" +
                    "\t}\n" +
                    "}\n" +
                    "}";
            wifiScanner_KeyWordList.add(wifisacnner_1_6);



            KeyWordItem wifisacnner_1_7 = new KeyWordItem();
            wifisacnner_1_7.keyword = "Latest native scan results:";
            wifisacnner_1_7.explain="底层扫描WIFI结果: WifiNative的扫描结果  【ArrayList<ScanDetail> mWifiNative.getScanResults(mIfaceName)】";
            wifisacnner_1_7.printLogUrl="WifiScanningServiceImpl.java  【WifiScannerImpl mScannerImpl;  // 扫描真正实现类  WificondScannerImpl 】 ";
            wifisacnner_1_7.expain1="********************* WificondScannerImpl extends  WifiScannerImpl  Begin  *********************\n" +
                    "public class WificondScannerImpl extends WifiScannerImpl implements Handler.Callback {\n" +
                    "\n" +
                    "ArrayList<ScanDetail> mNativeScanResults;\n" +
                    "\n" +
                    "mNativeScanResults = mWifiNative.getScanResults(mIfaceName);   // ScanDetail 来自于 WifiNative\n" +
                    "   \n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        synchronized (mSettingsLock) {\n" +
                    "            long nowMs = mClock.getElapsedSinceBootMillis();\n" +
                    "            pw.println(\"Latest native scan results:\");  // WificondScannerImpl 内部扫描结果\n" +
                    "            if (mNativeScanResults != null) {\n" +
                    "                List<ScanResult> scanResults = mNativeScanResults.stream().map(r -> {\n" +
                    "                    return r.getScanResult();   // ArrayList<ScanDetail>  转为    ArrayList<ScanResult>\n" +
                    "                }).collect(Collectors.toList());\n" +
                    "                ScanResultUtil.dumpScanResults(pw, scanResults, nowMs);  // 打印来自WifiNative的扫描结果\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t\t.....\n" +
                    "\t\t   }\n" +
                    "\t\t}\n" +
                    "}\n" +
                    "\t\n" +
                    "********************* WificondScannerImpl extends  WifiScannerImpl  End  *********************\n" +
                    "\n" +
                    "class WifiScanningServiceImpl extends IWifiScanner.Stub {\n" +
                    "WifiScannerImpl mScannerImpl;  // 扫描真正实现类  WificondScannerImpl \n" +
                    "\n" +
                    "dump(){\n" +
                    "  if (mScannerImpl != null) {\n" +
                    "      mScannerImpl.dump(fd, pw, args);\n" +
                    "  }\n" +
                    "}";
            wifiScanner_KeyWordList.add(wifisacnner_1_7);



            KeyWordItem wifisacnner_1_8 = new KeyWordItem();
            wifisacnner_1_8.keyword = "Latest native pno scan results:";
            wifisacnner_1_8.explain="最新的PNO扫描结果:  WificondScannerImpl.java  ArrayList<ScanDetail> mNativePnoScanResults ";
            wifisacnner_1_8.printLogUrl=" ArrayList<ScanDetail> mNativePnoScanResults 来自 WifiNative getPnoScanResults 方法 =>   ArrayList<ScanDetail>  mNativePnoScanResults = mWifiNative.getPnoScanResults(mIfaceName);" ;
            wifisacnner_1_8.expain1="class WifiScanningServiceImpl extends IWifiScanner.Stub {\n" +
                    "WifiScannerImpl mScannerImpl;  // 扫描真正实现类  WificondScannerImpl \n" +
                    "\n" +
                    "dump(){\n" +
                    "  if (mScannerImpl != null) {\n" +
                    "      mScannerImpl.dump(fd, pw, args);\n" +
                    "  }\n" +
                    "}\n" +
                    "\n" +
                    "********************* WificondScannerImpl extends  WifiScannerImpl  Begin  *********************\n" +
                    "public class WificondScannerImpl extends WifiScannerImpl implements Handler.Callback {\n" +
                    "\n" +
                    "ArrayList<ScanDetail> mNativePnoScanResults;\n" +
                    "// Pno-ScanDetail 来自于 WifiNative 的 getPnoScanResults API\n" +
                    "mNativePnoScanResults = ArrayList<ScanDetail> mWifiNative.getPnoScanResults(mIfaceName);   \n" +
                    "   \n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        synchronized (mSettingsLock) {\n" +
                    "            long nowMs = mClock.getElapsedSinceBootMillis();\n" +
                    "\n" +
                    "\t\t\t pw.println(\"Latest native pno scan results:\");\n" +
                    "            if (mNativePnoScanResults != null) {\n" +
                    "                List<ScanResult> pnoScanResults = mNativePnoScanResults.stream().map(r -> {\n" +
                    "                    return r.getScanResult();\n" +
                    "                }).collect(Collectors.toList());  // ArrayList<ScanDetail>  转为    ArrayList<ScanResult>\n" +
                    "                ScanResultUtil.dumpScanResults(pw, pnoScanResults, nowMs);  // 打印 \n" +
                    "            }\n" +
                    "\t\t\t\n" +
                    "\t\t   }\n" +
                    "\t\t}\n" +
                    "}\n" +
                    "\t\n" +
                    "********************* WificondScannerImpl extends  WifiScannerImpl  End  *********************";
            wifiScanner_KeyWordList.add(wifisacnner_1_8);


            return wifiScanner_KeyWordList;
        }

        ArrayList<KeyWordItem>    initWifikeyWordList(){
            ArrayList<KeyWordItem> keyWordList = new ArrayList<KeyWordItem>();

            KeyWordItem wifi_1_1 = new KeyWordItem();
            wifi_1_1.keyword = "Wi-Fi is ";
            wifi_1_1.explain="5种wifi开关当前的状态(WIFI_STATE_DISABLING,0,disabling)(WIFI_STATE_DISABLED,1,disabled) (WIFI_STATE_ENABLING,2,enabling) (WIFI_STATE_ENABLED,3,enabled) (WIFI_STATE_UNKNOWN,4,enabled)";
            wifi_1_1.classNameForShuxing = "(ClientModeImpl.java) AtomicInteger mWifiState ";
            wifi_1_1.printcode="pw.println(\"Wi-Fi is \" + mClientModeImpl.syncGetWifiStateByName());";
            wifi_1_1.printLogUrl="WifiServiceImpl.java dump(FileDescriptor fd, PrintWriter pw, String[] args)";
            wifi_1_1.expain1="【打开wifi命令:  adb shell svc wifi enable   】 \n【关闭wifi命令:  adb shell svc wifi disable  】\n" +
                    "【WIFI连接记录详情--搜索】 ═════════════ mConnectionEvents: End\n" +
                    "【WIFI连接持续信息记录---每三秒记录一次--搜素】  WifiScoreReport:\n" +
                    "【当前WIFI的状态--搜索】curState=    【WifiClientModeImpl的(第2个)curState=就是当前手机的状态 】\n   " +
                    "【搜索连接成功的日志(连接成功可能会改变系统时间 导致时间跳跃)--搜索】  org=ObtainingIpState dest=ConnectedState \n" +
                    "【所有连接断开的事件--搜素】 org=ConnectedState dest=D\n" +
                    "【用户关闭WIFI导致的断开--搜索】  org=ConnectedState dest=DefaultState what=CMD_SET_OPERATIONAL_MODE\n" +
                    "【事件发生导致的断开--比如forget-搜素】 org=ConnectedState dest=D\n" +
                    "【WIFI连接过程 org=ConnectedState dest=<null>表示一直处于连接状态 看wifi的时间的持续 】State org=ConnectedState dest=\n" +
                    "【热点打开记录:  搜索】 mSoftApTetheredEvents:\n" +
                    "4. Settings >System > About phone > tap \"Build number\" 4 times >Developer options\n" +
                    "   Setting > System > Advanced > Developer options >Enable WiFi Verbose Logging  [toogle open] \n" +
                    "adb shell settings get global hs20_mncmcc_retail_saved_state  【passpoint-sim开关】 \n" +
                    "adb shell settings put global airplane_mode_on 0 【停止飞行模式】\n" +
                    "adb shell settings get global airplane_mode_on   【获取飞行模式】\n" +
                    "adb shell settings get secure location_mode  【获取位置模式】 ";
            keyWordList.add(wifi_1_1);

            KeyWordItem wifi_2_1 = new KeyWordItem();
            wifi_2_1.keyword = "Verbose logging is";
            wifi_2_1.explain="WIFI详情Log开关是否打开 ";
            wifi_2_1.classNameForShuxing = "(WifiServiceImpl.java) boolean mVerboseLoggingEnabled";
            wifi_2_1.printcode="pw.println(\"Verbose logging is \" + (mVerboseLoggingEnabled ? \"on\" : \"off\"));";
            keyWordList.add(wifi_2_1);



            KeyWordItem wifi_3_1 = new KeyWordItem();
            wifi_3_1.keyword = "Stay-awake conditions:";
            wifi_3_1.explain="获取当前 Settings.Global.STAY_ON_WHILE_PLUGGED_IN 的值 开发者选项中 0-保持不休眠(false)充电时屏幕不会休眠   1-保持不休眠(true)   休眠就是系统不会sleep一直awake(区别于锁屏)";
            wifi_3_1.classNameForShuxing = "(Settings.java) String \"stay_on_while_plugged_in\" || 获取 adb shell settings get global  stay_on_while_plugged_in || 设置  adb shell settings put global  stay_on_while_plugged_in 1\n ";
            wifi_3_1.printcode="getIntegerSetting(mContext, Settings.Global.STAY_ON_WHILE_PLUGGED_IN, 0)";
            keyWordList.add(wifi_3_1);


            KeyWordItem wifi_4_1 = new KeyWordItem();
            wifi_4_1.keyword = "mInIdleMode ";
            wifi_4_1.explain="代表当前手机的状态是否为 idle 状态 ";
            wifi_4_1.classNameForShuxing = "(PowerManagerService.java) boolean \"mInIdleMode \" || 获取 adb shell settings get global  stay_on_while_plugged_in || 设置  adb shell settings put global  stay_on_while_plugged_in 1\n ";
            wifi_4_1.printcode="pw.println(\"mInIdleMode \" + mInIdleMode);";
            wifi_4_1.shuxingCall="mPowerManager.isDeviceIdleMode()";
            keyWordList.add(wifi_4_1);



            KeyWordItem wifi_5_1 = new KeyWordItem();
            wifi_5_1.keyword = "mScanPending ";
            wifi_5_1.explain="标识当前设备在idle状态下有扫描的请求 但无法执行的情况  boolean mScanPending  ";
            wifi_5_1.classNameForShuxing = "(WifiServiceImpl.java) boolean mScanPending; ";
            wifi_5_1.printcode="pw.println(\"mScanPending \" + mScanPending);";
            keyWordList.add(wifi_5_1);


            KeyWordItem wifi_6_1 = new KeyWordItem();
            wifi_6_1.keyword = "WifiController:";
            wifi_6_1.explain="打印 WifiController相关的属性 mWifiController继承StateMachine 实际调用 StateMachine的dump   ";
            wifi_6_1.explain =wifi_6_1.explain+"\n"+" total records=  SmHandler.mLogRecords.count()Log记录长度 ";

            String LogRec = "\n public static class LogRec {\n" +
                    "        private StateMachine mSm; // what=[mSm.getWhatToString(mWhat)] 为空打印十六进制数值 \n " +
                    "        private long mTime; //time时间 \n" +
                    "        private int mWhat; // Message中 msg.what 的值(0x2001)  what=十六进制 对应  (WifiManager.java 的 int数值 )public static final int CONNECT_NETWORK = BASE + 1;  \n" +
                    "        private String mInfo;  // 如果有最后打印 \n" +
                    "        private IState mState;  //处理消息的转态 processed=状态的名称  \n" +
                    "        private IState mOrgState; //接收到消息的初始化状态  org=初始化的状态  \n" +
                    "        private IState mDstState; // dest=目标状态  }";

            String wifiCode ="\n    /** Non system protocols */\n" +
                    "    public static final int BASE_WIFI                                               = 0x00020000;\n" +
                    "    public static final int BASE_WIFI_WATCHDOG                                      = 0x00021000;\n" +
                    "    public static final int BASE_WIFI_P2P_MANAGER                                   = 0x00022000;\n" +
                    "    public static final int BASE_WIFI_P2P_SERVICE                                   = 0x00023000;\n" +
                    "    public static final int BASE_WIFI_MONITOR                                       = 0x00024000;\n" +
                    "    public static final int BASE_WIFI_MANAGER                                       = 0x00025000;\n" +
                    "    public static final int BASE_WIFI_CONTROLLER                                    = 0x00026000;\n" +
                    "    public static final int BASE_WIFI_SCANNER                                       = 0x00027000;\n" +
                    "    public static final int BASE_WIFI_SCANNER_SERVICE                               = 0x00027100;\n" +
                    "    public static final int BASE_WIFI_RTT_MANAGER                                   = 0x00027200;\n" +
                    "    public static final int BASE_WIFI_RTT_SERVICE                                   = 0x00027300;\n" +
                    "    public static final int BASE_WIFI_PASSPOINT_MANAGER                             = 0x00028000;\n" +
                    "    public static final int BASE_WIFI_PASSPOINT_SERVICE                             = 0x00028100;\n" +
                    "    public static final int BASE_WIFI_LOGGER                                        = 0x00028300; \n" ;
            String wifi6_curState = "\n curState=  getCurrentState().getName() 状态栈数组中最顶部的状态= mStateStack[mStateStackTopIndex].state.getName()  StateInfo mStateStack[]  ";
            wifi_6_1.explain =wifi_6_1.explain+"\n"+" rec[0] 是一个  LogRec " +LogRec + wifiCode + wifi6_curState;
            wifi_6_1.classNameForShuxing = "(WifiServiceImpl.java) WifiController mWifiController; ";
            wifi_6_1.printcode="mWifiController(StateMachine.java).dump(fd, pw, args);";
            wifi_6_1.expain1="WifiController extends StateMachine\n" +
                    "  _-----=> StaEnabledState\n" +
                    " / _----=> StaDisabledState\n" +
                    "| / _---=> StaDisabledWithScanState\n" +
                    "|| / _--=> EcmState\n" +
                    "||| /     \n" +
                    "||||    \n" +
                    "||||      \n" +
                    "DefaultState";
            keyWordList.add(wifi_6_1);



            KeyWordItem wifi_7_1 = new KeyWordItem();
            wifi_7_1.keyword = "mPersistWifiState ";
            wifi_7_1.explain="WifiSettingsStore中  int mPersistWifiState=WIFI的状态  (WIFI_DISABLED,0,WIFI不可用) (WIFI_ENABLED,1,WIFI可用) (WIFI_ENABLED_AIRPLANE_OVERRIDE,2,飞行模式下打开WIFI) (WIFI_DISABLED_AIRPLANE_ON,3,WIFI不可用由于打开了WIFI)   \n mAirplaneModeOn=false|true 飞行模式是否打开  ";
            wifi_7_1.classNameForShuxing = "(WifiSettingsStore.java) dump() ";
            wifi_7_1.printcode="mSettingsStore.dump(fd, pw, args);";
            keyWordList.add(wifi_7_1);




            KeyWordItem wifi_8_1 = new KeyWordItem();
            wifi_8_1.keyword = "mTxPkts ";
            wifi_8_1.explain="当前传输的包 (WifiTrafficPoller.java) long mTxPkts  ";
            wifi_8_1.classNameForShuxing = " (WifiServiceImpl.java)WifiTrafficPoller mWifiTrafficPoller;   ";
            wifi_8_1.printcode="mWifiTrafficPoller.dump(fd, pw, args);";
            keyWordList.add(wifi_8_1);



            KeyWordItem wifi_9_1 = new KeyWordItem();
            wifi_9_1.keyword = "mRxPkts ";
            wifi_9_1.explain="当前接收的包 (WifiTrafficPoller.java) long mRxPkts  ";
            wifi_9_1.classNameForShuxing = " (WifiServiceImpl.java)WifiTrafficPoller  mWifiTrafficPoller; long mRxPkts  ";
            wifi_9_1.printcode="mWifiTrafficPoller.dump(fd, pw, args);";
            keyWordList.add(wifi_9_1);


            KeyWordItem wifi_10_1 = new KeyWordItem();
            wifi_10_1.keyword = "mDataActivity ";
            wifi_10_1.explain="追踪网络数据报告的Activity 个数  ";
            wifi_10_1.classNameForShuxing = " (WifiTrafficPoller.java) int mDataActivity  ";
            wifi_10_1.printcode="mWifiTrafficPoller.dump(fd, pw, args);";
            keyWordList.add(wifi_10_1);


            KeyWordItem wifi_11_1 = new KeyWordItem();
            wifi_11_1.keyword = "mRegisteredCallbacks ";
            wifi_11_1.explain="监听追踪网络数据报告的Activity个数变化的接口集合  ";
            wifi_11_1.classNameForShuxing = " (WifiTrafficPoller.java)   ExternalCallbackTracker<ITrafficStateCallback> mRegisteredCallbacks ";
            wifi_11_1.printcode="mWifiTrafficPoller.dump(fd, pw, args);";
            keyWordList.add(wifi_11_1);




            KeyWordItem wifi_12_1 = new KeyWordItem();
            wifi_12_1.keyword = "Locks acquired:";
            wifi_12_1.explain="WIFILock WIFI锁 请求 的情况   full high perf(高性能锁)   low latency(低延时锁)";
            wifi_12_1.classNameForShuxing = " (WifiServiceImpl.java)   WifiLockManager mWifiLockManager; ";
            wifi_12_1.printcode=" mWifiLockManager.dump(pw);-》pw.println(\"Locks acquired: \"+ mFullHighPerfLocksAcquired + \" full high perf, \"+ mFullLowLatencyLocksAcquired + \" full low latency\");";
            wifi_12_1.shuxingDefine=" (WifiLockManager.java)   private int mFullHighPerfLocksAcquired;高性能锁   private int mFullLowLatencyLocksAcquired; 低延时锁" ;
            keyWordList.add(wifi_12_1);



            KeyWordItem wifi_13_1 = new KeyWordItem();
            wifi_13_1.keyword = "Locks released:";
            wifi_13_1.explain="WIFILock WIFI锁 释放release 的情况   full high perf(高性能锁)   low latency(低延时锁)";
            wifi_13_1.classNameForShuxing = " (WifiServiceImpl.java)   WifiLockManager mWifiLockManager; ";
            wifi_13_1.printcode=" mWifiLockManager.dump(pw);-》pw.println(\"Locks acquired: \"+ mFullHighPerfLocksAcquired + \" full high perf, \"+ mFullLowLatencyLocksAcquired + \" full low latency\");";
            wifi_13_1.shuxingDefine=" (WifiLockManager.java)   private int mFullHighPerfLocksReleased; ;高性能锁  private int mFullLowLatencyLocksReleased;低延时锁" ;
            keyWordList.add(wifi_13_1);


            KeyWordItem wifi_14_1 = new KeyWordItem();
            wifi_14_1.keyword = "Locks held:";

            String wifi14_wifiLock = "\n   private class WifiLock implements IBinder.DeathRecipient {\n" +
                    "        String mTag;\n" +
                    "        int mUid;\n" +
                    "        IBinder mBinder;\n" +
                    "        int mMode;\n" +
                    "        WorkSource mWorkSource;\n" +
                    "        long mAcqTimestamp;";

            String wifi14_string = "\n\"WifiLock{\" + this.mTag + \" type=\" + this.mMode + \" uid=\" + mUid + \" workSource=\" + mWorkSource + \"}\";";
            wifi_14_1.explain="打印当前锁的详情 "+wifi14_wifiLock + wifi14_string ;
            wifi_14_1.classNameForShuxing = "  List<WifiLock> mWifiLocks   for (WifiLock lock : mWifiLocks) ";
            wifi_14_1.printcode="mWifiTrafficPoller.dump(fd, pw, args);";
            keyWordList.add(wifi_14_1);




            KeyWordItem wifi_15_1 = new KeyWordItem();
            wifi_15_1.keyword = "mMulticastEnabled ";
            wifi_15_1.explain="允许Multicast 多波的个数 ? ";
            wifi_15_1.classNameForShuxing = " (WifiMulticastLockManager.java) int mMulticastEnabled  ";
            wifi_15_1.printcode=" mWifiMulticastLockManager.dump(pw);";
            keyWordList.add(wifi_15_1);


            KeyWordItem wifi_15_2 = new KeyWordItem();
            wifi_15_2.keyword = "mMulticastDisabled ";
            wifi_15_2.explain="不允许Multicast 多波的个数 ? ";
            wifi_15_2.classNameForShuxing = " (WifiMulticastLockManager.java) int mMulticastDisabled  ";
            wifi_15_2.printcode=" mWifiMulticastLockManager.dump(pw);";
            keyWordList.add(wifi_15_2);


            KeyWordItem wifi_16_1 = new KeyWordItem();
            wifi_16_1.keyword = "Dump of WifiActiveModeWarden";
            wifi_16_1.explain="打印WifiActiveModeWarden 相关数据   ";
            wifi_16_1.shuxingDefine="ActiveModeWarden.Tag = WifiActiveModeWarden ";
            wifi_16_1.classNameForShuxing = " (WifiServiceImpl.java) ActiveModeWarden mActiveModeWarden; ";
            wifi_16_1.printcode=" mActiveModeWarden.dump(fd, pw, args);";
            keyWordList.add(wifi_16_1);

            KeyWordItem wifi_16_2 = new KeyWordItem();
            wifi_16_2.keyword = "Current wifi mode:";
            wifi_16_2.explain=" 获取ActiveModeWarden下的状态名称  mModeStateMachine.getCurrentMode() ";
            wifi_16_2.explain += "包含 mClientModeActiveState || mScanOnlyModeActiveState || mWifiDisabledState ";
            wifi_16_2.classNameForShuxing = " (ActiveModeWarden.java) ModeStateMachine mModeStateMachine; ";
            wifi_16_2.shuxingDefine="(WifiServiceImpl.java) ActiveModeWarden mActiveModeWarden;";
            wifi_16_2.printcode=" mActiveModeWarden.dump(fd, pw, args);";
            wifi_16_2.expain1="ActiveModeWarden.mModeStateMachine [ModeStateMachine ]\n" +
                    "class ModeStateMachine extends StateMachine\n" +
                    "  _-----=> ClientModeActiveState\n" +
                    " / _----=> ScanOnlyModeActiveState\n" +
                    "| / _---=> WifiDisabledState  【Init State】\n" +
                    "|| / \n" +
                    "||| \n" +
                    "没有RootState";
            keyWordList.add(wifi_16_2);



            KeyWordItem wifi_16_3 = new KeyWordItem();
            wifi_16_3.keyword = "NumActiveModeManagers:";
            wifi_16_3.explain=" 打印数组的长度 ActiveModeManager    ArraySet<ActiveModeManager> mActiveModeManagers ";
            wifi_16_3.classNameForShuxing = " (ActiveModeWarden.java) ArraySet<ActiveModeManager> mActiveModeManagers; ";
            wifi_16_3.shuxingDefine="(WifiServiceImpl.java) ActiveModeWarden  mActiveModeWarden;";
            wifi_16_3.printcode="  pw.println(\"NumActiveModeManagers: \" + mActiveModeManagers.size());";
            keyWordList.add(wifi_16_3);


            KeyWordItem wifi_17_1 = new KeyWordItem();
            wifi_17_1.keyword = "--Dump of ClientModeManager";
            String wifi17_clientmanager = "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        pw.println(\"--Dump of ClientModeManager--\");\n" +
                    "        pw.println(\"current StateMachine mode: \" + getCurrentStateName()); // mIdleState||mStartedState   ClientModeStateMachine.getCurrentState().getName() 获取状态机的栈顶状态名称  \n" +
                    "        pw.println(\"mClientInterfaceName: \" + mClientInterfaceName);  //   String mClientInterfaceName; 客户端接口名称 如(wlan0) CMD_START 命令处理时赋值 setupInterfaceForClientInConnectivityMode \n" +
                    "        pw.println(\"mIfaceIsUp: \" + mIfaceIsUp);  // boolean mIfaceIsUp = false;   StartedState的 onUpChanged(boolean isUp)方法内赋值 为true \n" +
                    "    } \n";
            wifi_17_1.explain=" 打印一个ActiveModeManager 实现类 ClientModeManager 的数据  " + wifi17_clientmanager;
            wifi_17_1.classNameForShuxing = " (ActiveModeWarden.java) ArraySet<ActiveModeManager> mActiveModeManagers; ";
            wifi_17_1.printcode="(ActiveModeWarden.java) dump -> for (ActiveModeManager manager : mActiveModeManagers) { manager.dump(fd, pw, args); } ";
            wifi_17_1.expain1="ClientModeManager.mStateMachine[ClientModeStateMachine]\n" +
                    "ClientModeStateMachine extends StateMachine\n" +
                    "\n" +
                    " _----=> StartedState\n" +
                    "/ _---=> IdleState  【Init State】\n" +
                    "| / \n" +
                    "|| \n" +
                    "没有RootState";
            wifi_17_1.classUrl="class ClientModeManager implements ActiveModeManager ";
            wifi_17_1.shuxingDefine="(WifiServiceImpl.java) ActiveModeWarden mActiveModeWarden;";
            keyWordList.add(wifi_17_1);



            KeyWordItem wifi_18_1 = new KeyWordItem();
            wifi_18_1.keyword = "WifiClientModeImpl:";


            String wifi18_WifiClientModeImpl_dump = "   @Override\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        super.dump(fd, pw, args); // 打印 ClientModeImpl 父类  WifiState 的 LogRes \n" +
                    "        mSupplicantStateTracker.dump(fd, pw, args);   // 打印 mSupplicantStateTracker  \n" +
                    "        pw.println(\"mLinkProperties \" + mLinkProperties);\n" +
                    "        pw.println(\"mWifiInfo \" + mWifiInfo);\n" +
                    "        pw.println(\"mDhcpResults \" + mDhcpResults);\n" +
                    "        pw.println(\"mNetworkInfo \" + mNetworkInfo);\n" +
                    "        pw.println(\"mLastSignalLevel \" + mLastSignalLevel);\n" +
                    "        pw.println(\"mLastBssid \" + mLastBssid);\n" +
                    "        pw.println(\"mLastNetworkId \" + mLastNetworkId);\n" +
                    "        pw.println(\"mOperationalMode \" + mOperationalMode);\n" +
                    "        pw.println(\"mUserWantsSuspendOpt \" + mUserWantsSuspendOpt);\n" +
                    "        pw.println(\"mSuspendOptNeedsDisabled \" + mSuspendOptNeedsDisabled);\n" +
                    "        mCountryCode.dump(fd, pw, args);\n" +
                    "        mNetworkFactory.dump(fd, pw, args);\n" +
                    "        mUntrustedNetworkFactory.dump(fd, pw, args);\n" +
                    "        pw.println(\"Wlan Wake Reasons:\" + mWifiNative.getWlanWakeReasonCount());\n" +
                    "        pw.println();\n" +
                    "\n" +
                    "        mWifiConfigManager.dump(fd, pw, args);\n" +
                    "        pw.println();\n" +
                    "        mPasspointManager.dump(pw);\n" +
                    "        pw.println();\n" +
                    "        mWifiDiagnostics.captureBugReportData(WifiDiagnostics.REPORT_REASON_USER_ACTION);\n" +
                    "        mWifiDiagnostics.dump(fd, pw, args);\n" +
                    "        dumpIpClient(fd, pw, args);\n" +
                    "        mWifiConnectivityManager.dump(fd, pw, args);\n" +
                    "        mWifiInjector.getWakeupController().dump(fd, pw, args);\n" +
                    "        mLinkProbeManager.dump(fd, pw, args);\n" +
                    "        mWifiInjector.getWifiLastResortWatchdog().dump(fd, pw, args);\n" +
                    "    }";
            String wifi18_LogRec = "\n public static class LogRec {\n" +
                    "        private StateMachine mSm; // what=[mSm.getWhatToString(mWhat)] 为空打印十六进制数值 \n " +
                    "        private long mTime; //time时间 \n" +
                    "        private int mWhat; // Message中 msg.what 的值(0x2001)  what=十六进制 对应  (WifiManager.java 的 int数值 )public static final int CONNECT_NETWORK = BASE + 1;  \n" +
                    "        private String mInfo;  // 如果有最后打印 \n" +
                    "        private IState mState;  //处理消息的转态 processed=状态的名称  \n" +
                    "        private IState mOrgState; //接收到消息的初始化状态  org=初始化的状态  \n" +
                    "        private IState mDstState; // dest=目标状态  }";

            wifi_18_1.explain="打印 WifiClientModeImpl 相关的信息 LogRec 状态机处理Message的情况  "  + wifi18_WifiClientModeImpl_dump;
            wifi_18_1.classNameForShuxing = " (WifiServiceImpl.java)  ClientModeImpl mClientModeImpl ";
            wifi_18_1.printcode=" (ClientModeImpl.java extends StateMachine)    super.dump(fd, pw, args); ";
            wifi_18_1.expain1="WifiServiceImpl.mClientModeImpl [ ClientModeImpl ]\n" +
                    "ClientModeImpl.TAG = WifiClientModeImpl( ClientModeImpl = WifiClientModeImpl )\n" +
                    "ClientModeImpl extends StateMachine {}\n" +
                    "\n" +
                    "                  _----=> RoamingState\n" +
                    "                 / _---=> ConnectedState\n" +
                    "                | / _--=> ObtainingIpState\n" +
                    "                || /   \n" +
                    "           L2ConnectedState\n" +
                    "                ||    \n" +
                    "  _-----=> ConnectModeState\n" +
                    " / _----=> DisconnectingState\n" +
                    "| / _---=> DisconnectedState\n" +
                    "|| / _--=> WpsRunningState\n" +
                    "||| /\n" +
                    "||||\n" +
                    "||||\n" +
                    "DefaultState";

            keyWordList.add(wifi_18_1);








            KeyWordItem wifi_19_1 = new KeyWordItem();
            wifi_19_1.keyword = "SupplicantStateTracker:";
            String wifi19_dump =  "\n    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        super.dump(fd, pw, args);  // 打印   SupplicantStateTracker extends StateMachine ，当前父类的 LogRes 处理消息状态信息 \n" +
                    "        pw.println(\"mAuthFailureInSupplicantBroadcast \" + mAuthFailureInSupplicantBroadcast); //   boolean mAuthFailureInSupplicantBroadcast = false  是否广播 认证失败的flag  WifiMonitor.AUTHENTICATION_FAILURE_EVENT 事件 \n" +
                    "        pw.println(\"mAuthFailureReason \" + mAuthFailureReason); //  int mAuthFailureReason; 认证失败原因 来自 message.arg1 \n" +
                    "        pw.println(\"mNetworksDisabledDuringConnect \" + mNetworksDisabledDuringConnect);  // 当前网络是否在连接期间被禁用(Default 去执行 CMD_CONNECT的话)  boolean mNetworksDisabledDuringConnect = false;  \n" +
                    "        pw.println();\n" +
                    "    }";
            wifi_19_1.explain="打印 SupplicantStateTracker 相关数据   " + wifi19_dump;
            wifi_19_1.classNameForShuxing = " (WifiServiceImpl.java  - > ClientModeImpl.java  ) SupplicantStateTracker mSupplicantStateTracker ";
            wifi_19_1.printcode=" mSupplicantStateTracker.dump(fd, pw, args); ";
            wifi_19_1.expain1="                 _-----=>   DormantState\n" +
                    "                / _----=>  CompletedState\n" +
                    "                | / _---=> HandshakeState \n" +
                    "                || / _--=> ScanState \n" +
                    "                ||| /\n" +
                    "                ||||\n" +
                    "                ||||\n" +
                    "  _-----=> ConnectionActiveState\n" +
                    " / _----=> DisconnectedState \n" +
                    "| / _---=> InactiveState\n" +
                    "|| / _--=> UninitializedState\n" +
                    "||| /\n" +
                    "||||\n" +
                    "||||\n" +
                    "DefaultState";
            wifi_19_1.shuxingDefine=" SupplicantStateTracker extends StateMachine ";
            keyWordList.add(wifi_19_1);


            //  mLinkProperties       pw.println("mLinkProperties " + mLinkProperties);
            // https://sse.am.mot.com/q_source/xref/mq-r-6125/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ClientModeImpl.java




            KeyWordItem wifi_19_2 = new KeyWordItem();
            wifi_19_2.keyword = "mLinkProperties ";
            wifi_19_2.explain="网络链接LinkProperties连接的属性 \n public final class LinkProperties implements Parcelable {\n" +
                    "    // The interface described by the network link.\n" +
                    "    private String mIfaceName;\n" +
                    "    private final ArrayList<LinkAddress> mLinkAddresses = new ArrayList<>();\n" +
                    "    private final ArrayList<InetAddress> mDnses = new ArrayList<>();\n" +
                    "    \n" +
                    "    // PCSCF addresses are addresses of SIP proxies that only exist for the IMS core service.\n" +
                    "    private final ArrayList<InetAddress> mPcscfs = new ArrayList<InetAddress>();\n" +
                    "    private final ArrayList<InetAddress> mValidatedPrivateDnses = new ArrayList<>();\n" +
                    "    private boolean mUsePrivateDns;\n" +
                    "    private String mPrivateDnsServerName;\n" +
                    "    private String mDomains;\n" +
                    "    private ArrayList<RouteInfo> mRoutes = new ArrayList<>();\n" +
                    "    private ProxyInfo mHttpProxy;\n" +
                    "    private int mMtu;\n" +
                    "    \n" +
                    "    private String mTcpBufferSizes; // in the format \"rmem_min,rmem_def,rmem_max,wmem_min,wmem_def,wmem_max\"\n" +
                    "    private IpPrefix mNat64Prefix;\n" +
                    "    \n" +
                    "    private static final int MIN_MTU    = 68;\n" +
                    "    private static final int MIN_MTU_V6 = 1280;\n" +
                    "    private static final int MAX_MTU    = 10000;\n" +
                    "    \n" +
                    "    // Stores the properties of links that are \"stacked\" above this link.\n" +
                    "    // Indexed by interface name to allow modification and to prevent duplicates being added.\n" +
                    "    private Hashtable<String, LinkProperties> mStackedLinks = new Hashtable<>();\n" +
                    "    }";

            wifi_19_2.shuxingDefine=" (WifiServiceImpl.java)  ClientModeImpl mClientModeImpl\n" +
                    " (ClientModeImpl.java)   LinkPropertie mLinkProperties ";
            wifi_19_2.classNameForShuxing = " ";
            wifi_19_2.printcode="(WifiServiceImpl.java --> ClientModeImpl.java ) dump(){  pw.println(\"mLinkProperties \" + mLinkProperties);  } ";

            wifi_19_2.expain1="String mIfaceName[ InterfaceName  接口名称 ]  \nArrayList<LinkAddress> mLinkAddresses [ LinkAddresses 链接地址 ] [ 逗号分割 TextUtils.join(\",\", mLinkAddresses) ]\n " +
                    "\nArrayList<InetAddress> mDnses [ DnsAddresses DNS地址 ]  逗号分割 TextUtils.join(\",\",mDnses) \nString mDomains; [ Domains 域名] " +
                    "\n int mMtu; [最大传输单元 (Maximum Transmission Unit，MTU ) ]  MIN_MTU= 68; MIN_MTU_V6=1280; MAX_MTU= 10000;  802.11中为68 以太网为1500 " +
                    "\nString mTcpBufferSizes; [缓存大小？ TcpBufferSizes ]   \n\"rmem_min 接收窗口的最小值,rmem_def 接收窗口的默认值,rmem_max 接收窗口的最大值 ,wmem_min TCP发送窗口的最小值 ,wmem_def TCP发送窗口的默认值 ,wmem_max TCP发送窗口的最大值\" \n " +
                    "\nArrayList<RouteInfo> mRoutes [路由信息  Routes ] TextUtils.join(\",\", mRoutes) 逗号分割" +
                    "The gateway address for this route = mGateway   //  网关 mGateway 地址  \n" +
                    "The IP destination address for this route = IpPrefix  // 路由IP目的地址  mDestination\n" +
                    "The interface for this route = String RouteInfo.mInterface\n" +
                    "\n" +
                    "public final class IpPrefix implements Parcelable {\n" +
                    "private final byte[] address;  // network byte order 字节数组\n" +
                    "private final int prefixLength; // 长度 64\n" +
                    "toString(){ InetAddress.getByAddress(address)【 new Inet4Address(host, address)】.getHostAddress() + \"/\" + prefixLength;}\n" +
                    "}\n" +
                    "\n" +
                    "[Inet4Address]  public String getHostAddress() {  return numericToTextFormat(getAddress());  }\n" +
                    "[Inet4Address] String numericToTextFormat(byte[] src) {return (src[0] & 0xff) + \".\" + (src[1] & 0xff) + \".\" + (src[2] & 0xff) + \".\" + (src[3] & 0xff); }\n" +
                    "\n" +
                    "RouteInfo.mDestination[IpPrefix].toString() 路由IP目的地址 -> 网关地址 RouteInfo.mGateway[InetAddress].getHostAddress() +  接口名称 RouteInfo.mInterface ,\n";


            keyWordList.add(wifi_19_2);




            KeyWordItem wifi_19_3 = new KeyWordItem();
            wifi_19_3.keyword = "mWifiInfo ";
            wifi_19_3.explain="当前连接到的wifi网络的信息  ";
            wifi_19_3.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java) ExtendedWifiInfo mWifiInfo  ";
            wifi_19_3.shuxingDefine="WifiServiceImpl.mClientModeImpl [ ClientModeImpl ] 【 \"ClientModeImpl extends StateMachine {}】\n" +
                    "ClientModeImpl.TAG = WifiClientModeImpl( ClientModeImpl = WifiClientModeImpl )\n" +
                    "[ ExtendedWifiInfo ] mClientModeImpl.mWifiInfo ";
            wifi_19_3.printcode="[WifiServiceImpl.mClientModeImpl.dump 中] pw.println(\"mWifiInfo \" + mWifiInfo);";

            wifi_19_3.expain1="class ExtendedWifiInfo extends WifiInfo {\n" +
                    "private static final int SOURCE_UNKNOWN = 0;  // 数据来源未知\n" +
                    "private static final int SOURCE_TRAFFIC_COUNTERS = 1; // 数据来源于传输计数器\n" +
                    "private static final int SOURCE_LLSTATS = 2;  // 数据来源于 link layer层\n" +
                    "\n" +
                    "private static final long RESET_TIME_STAMP = Long.MIN_VALUE; // 重置时间\n" +
                    "private int mLastSource = SOURCE_UNKNOWN; //  数据来源\n" +
                    "private long mLastPacketCountUpdateTimeStamp = RESET_TIME_STAMP; \n" +
                    "private boolean mEnableConnectedMacRandomization = false;  //  是否开启mac地址随机化\n" +
                    "\n" +
                    "   public void setEnableConnectedMacRandomization(boolean enableConnectedMacRandomization) {\n" +
                    "        mEnableConnectedMacRandomization = enableConnectedMacRandomization;\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "class WifiInfo implements Parcelable {\n" +
                    "@Override\n" +
                    "public String toString() {\n" +
                    "StringBuffer sb = new StringBuffer();\n" +
                    "String none = \"<none>\";\n" +
                    "\n" +
                    "sb.append(\"SSID: \").append(mWifiSsid == null ? WifiSsid.NONE : mWifiSsid)  // SSID = WifiSsid mWifiSsid;\n" +
                    ".append(\", BSSID: \").append(mBSSID == null ? none : mBSSID) // BSSID = String mBSSID;\n" +
                    ".append(\", MAC: \").append(mMacAddress == null ? none : mMacAddress) // MAC =  String mMacAddress = xxx;   DEFAULT_MAC_ADDRESS = \"02:00:00:00:00:00\";\n" +
                    ".append(\", Supplicant state: \")\n" +
                    ".append(mSupplicantState == null ? none : mSupplicantState) // Supplicant state = enum SupplicantState mSupplicantState;\n" +
                    "// enum SupplicantState implements Parcelable { DISCONNECTED, INTERFACE_DISABLED(接口不可用),INACTIVE(不活跃wpa_supplicant disabled),  \n" +
                    "//SCANNING, AUTHENTICATING,ASSOCIATING, ASSOCIATED,FOUR_WAY_HANDSHAKE,GROUP_HANDSHAKE,COMPLETED,DORMANT(?),UNINITIALIZED, INVALID; }\n" +
                    ".append(\", RSSI: \").append(mRssi)  // RSSI = int mRssi;   MIN_RSSI = -126;  MAX_RSSI = 200;  INVALID_RSSI = -127;\n" +
                    ".append(\", Link speed: \").append(mLinkSpeed).append(LINK_SPEED_UNITS) // Link speed =  int mLinkSpeed;  String LINK_SPEED_UNITS = \"Mbps\";\n" +
                    ".append(\", Tx Link speed: \").append(mTxLinkSpeed).append(LINK_SPEED_UNITS) // Tx Link speed = int mTxLinkSpeed;\n" +
                    ".append(\", Rx Link speed: \").append(mRxLinkSpeed).append(LINK_SPEED_UNITS) // Rx Link speed = int mRxLinkSpeed;\n" +
                    ".append(\", Frequency: \").append(mFrequency).append(FREQUENCY_UNITS) // Frequency = int mFrequency; \n" +
                    ".append(\", Net ID: \").append(mNetworkId) // Net ID =  int mNetworkId;\n" +
                    ".append(\", Metered hint: \").append(mMeteredHint)  //  Metered hint = boolean mMeteredHint;该网络需要按流量收费的标识\n" +
                    ".append(\", score: \").append(Integer.toString(score)); // int score; 检测得分 从 Creator<WifiInfo> info.score = in.readInt();得到数值\n" +
                    "return sb.toString();\n" +
                    "}\n" +
                    "}";
            keyWordList.add(wifi_19_3);



            KeyWordItem wifi_19_4 = new KeyWordItem();
            wifi_19_4.keyword = "mDhcpResults ";
            wifi_19_4.explain="DHCP Dynamic Host Config protocol 动态主机配置协议结果 ";
            wifi_19_4.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java)  DhcpResults mDhcpResults;";
            wifi_19_4.printcode=" [WifiServiceImpl.mClientModeImpl.dump 中]  pw.println(\"mDhcpResults \" + mDhcpResults);";


            wifi_19_4.expain1="class DhcpResults implements Parcelable {\n" +
                    "public LinkAddress ipAddress;\n" +
                    "public InetAddress gateway;\n" +
                    "ArrayList<InetAddress> dnsServers = new ArrayList<>()\n" +
                    "String domains;\n" +
                    "Inet4Address serverAddress;\n" +
                    "String vendorInfo;\n" +
                    "int leaseDuration;\n" +
                    "int mtu; // 0 标识 未设置  unset\n" +
                    "String serverHostName;\n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "      StringBuffer str = new StringBuffer(super.toString());\n" +
                    "      str.append(\" DHCP server \").append(serverAddress);  //DHCP服务器地址 DHCP server = Inet4Address serverAddress; \n" +
                    "      str.append(\" Vendor info \").append(vendorInfo);  // 产商信息  Vendor info =  String vendorInfo;\n" +
                    "      str.append(\" lease \").append(leaseDuration).append(\" seconds\"); // 租期时间: lease = int leaseDuration; 秒  1800 秒=0.5小时  86400 秒=1天  3600 秒=1小时  43200 秒=0.5天=12小时 \n" +
                    "      if (mtu != 0) str.append(\" MTU \").append(mtu);    // 如果有设置 MTU  那么输出 int mtu;  最大传输单元大小\n" +
                    "      str.append(\" Servername \").append(serverHostName);   // DHCP服务主机名称  Servername = String serverHostName; \n" +
                    "      return str.toString();\n" +
                    "   }\n" +
                    "}\n";
            keyWordList.add(wifi_19_4);



            KeyWordItem wifi_19_5 = new KeyWordItem();
            wifi_19_5.keyword = "mNetworkInfo ";
            wifi_19_5.explain="打印WifiActiveModeWarden 相关数据   ";
            wifi_19_5.shuxingDefine="(WifiServiceImpl.java -> ClientModeImpl.java)  NetworkInfo mNetworkInfo;";
            wifi_19_5.classNameForShuxing = " ";
            wifi_19_5.classUrl=" [WifiServiceImpl.mClientModeImpl.dump 中]  pw.println(\"mNetworkInfo \" + mNetworkInfo);";
            wifi_19_5.printcode="class NetworkInfo implements Parcelable {\n" +
                    "enum State { CONNECTING, CONNECTED, SUSPENDED, DISCONNECTING, DISCONNECTED, UNKNOWN}\n" +
                    "\n" +
                    "enum DetailedState {\n" +
                    "\tIDLE(闲置 但 可以发起连接状态 ),\n" +
                    "\tSCANNING(正在扫描的状态),\n" +
                    "\tCONNECTING(正常连接的状态),\n" +
                    "\tAUTHENTICATING(正在身份认证的状态),\n" +
                    "\tOBTAINING_IPADDR(等待response获取IP地址的状态),\n" +
                    "\tCONNECTED(已连接能上网状态),\n" +
                    "\tSUSPENDED(暂时阻塞),\n" +
                    "\tDISCONNECTING(正在断开连接状态),\n" +
                    "\tDISCONNECTED(断开连接),\n" +
                    "\tFAILED(连接失败),\n" +
                    "\tBLOCKED(被禁用),\n" +
                    "\tVERIFYING_POOR_LINK(无信号低信号状态),\n" +
                    "\tCAPTIVE_PORTAL_CHECK(检测是否是portal网络的状态)\n" +
                    "}\n" +
                    "\n" +
                    "// 详细状态 与 一般状态 的 MAP  , Key(DetailedState) 不同 但 Value(State) 可能相同\n" +
                    "EnumMap<DetailedState, State> stateMap = new EnumMap<DetailedState, State>(DetailedState.class)\n" +
                    "DetailedState.IDLE  -- State.CONNECTED\n" +
                    "DetailedState.CONNECTED  -- State.DISCONNECTED\n" +
                    "DetailedState.OBTAINING_IPADDR  -- State.CONNECTING\n" +
                    "DetailedState.BLOCKED  -- State.DISCONNECTED\n" +
                    "\n" +
                    "int mNetworkType;  // 连接的类型 ConnectivityManager.TYPE_WIFI\n" +
                    "// ConnectivityManager.TYPE_NONE = -1; 没有连接\n" +
                    "// ConnectivityManager.TYPE_MOBILE = 0;  彩信网络 手机sim数据连接\n" +
                    "// ConnectivityManager.TYPE_WIFI        = 1;    // WIFI 连接 \n" +
                    "// ConnectivityManager.TYPE_MOBILE_MMS  = 2;  短信连接 An MMS-specific Mobile data connection\n" +
                    "// ConnectivityManager.TYPE_MOBILE_SUPL = 3; 定位相关的连接 SUPL A SUPL-specific Mobile data connection :SUPL是一种基于标准、允许移动电话用户与定位服务器通信的协议\n" +
                    "// ConnectivityManager.TYPE_MOBILE_DUN  = 4; DUN 提供了通过 Bluetooth 无线技术接入 Internet 和其它拨号服务的标准\n" +
                    "// ConnectivityManager.TYPE_MOBILE_HIPRI = 5; 高优先级移动连接服务  TYPE_MOBILE_HIPRI\n" +
                    "// ConnectivityManager.TYPE_WIMAX       = 6; intel推出的三代3G 标准 全球微波互联接入。是一项新兴的宽带无线接入技术，能提供面向互联网的高速连接，数据传输距离最远可达50km。WiMAX还具有QoS保障\n" +
                    "// ConnectivityManager.TYPE_BLUETOOTH   = 7; 蓝牙连接\n" +
                    "// ConnectivityManager.TYPE_DUMMY       = 8; 模拟连接\n" +
                    "// ConnectivityManager.TYPE_ETHERNET    = 9; 以太网连接\n" +
                    "// ConnectivityManager.TYPE_WIFI_P2P    = 13;\n" +
                    "// ConnectivityManager.TYPE_MOBILE_EMERGENCY = 15; 紧急转态\n" +
                    "// ConnectivityManager.TYPE_PROXY = 16; 代理\n" +
                    "// ConnectivityManager.TYPE_VPN = 17;  VPN 连接\n" +
                    "\n" +
                    "\n" +
                    "int mSubtype;    // 下一级网络的分类\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_UNKNOWN; // = 0.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_GPRS; // = 1.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_EDGE; // = 2.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_UMTS; // = 3.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_CDMA; // = 4.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_EVDO_0; // = 5.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_EVDO_A; // = 6.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_1XRTT; // = 7.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_HSDPA; // = 8.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_HSUPA; // = 9.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_HSPA; // = 10.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_IDEN; // = 11.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_EVDO_B; // = 12.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_LTE; // = 13.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_EHRPD; // = 14.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_HSPAP; // = 15.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_GSM; // = 16.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_TD_SCDMA; // = 17.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_IWLAN; // = 18.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_LTE_CA; // = 19.\n" +
                    "//TelephonyProtoEnums.NETWORK_TYPE_NR; // 20.\n" +
                    "\n" +
                    "String mTypeName;  //  网络类型\n" +
                    "String mSubtypeName; // sub子网网络类型\n" +
                    "State mState;  // 一般状态\n" +
                    "DetailedState mDetailedState;  // 详细状态\n" +
                    "String mReason;  // 建立连接失败的原因\n" +
                    "String mExtraInfo;  // extra information about the network state 额外的网络状态信息 从更底层的网络层得到\n" +
                    "boolean mIsFailover; // {@code true} if this is a failover attempt, {@code false} 故障转移尝试  从一个网络断开后的尝试?\n" +
                    "boolean mIsAvailable;  // 当前网络是否可用\n" +
                    "boolean mIsRoaming;  // 是否正在漫游标识 \n" +
                    "\t\n" +
                    "public String toString() {\n" +
                    "synchronized (this) {\n" +
                    "\tStringBuilder builder = new StringBuilder(\"[\");\n" +
                    "\tbuilder.append(\"type: \").append(getTypeName()).append(\"[\").append(getSubtypeName()); // WIFI=1 MOBILE=0 type = int_Str mNetworkType网络类型[int_Str mSubtype子网络类型] human-readable name 人类可读的网络信息 type: = String mTypeName typexx[ SubType int mSubtype  ]\n" +
                    "\tappend(\"], state: \").append(mState).append(\"/\").append(mDetailedState). // state = mState( State ) mDetailedState( DetailedState )\n" +
                    "\tappend(\", reason: \").append(mReason == null ? \"(unspecified)\" : mReason). // reason =  String mReason\n" +
                    "\tappend(\", extra: \").append(mExtraInfo == null ? \"(none)\" : mExtraInfo).  // extra  =  String mExtraInfo \n" +
                    "\tappend(\", failover: \").append(mIsFailover).  // failover = boolean mIsFailover;\n" +
                    "\tappend(\", available: \").append(mIsAvailable). // available = boolean mIsAvailable;  // 当前网络是否可用\n" +
                    "\tappend(\", roaming: \").append(mIsRoaming).  //  roaming = boolean mIsRoaming; 是否正在漫游标识 \n" +
                    "\tappend(\"]\");\n" +
                    "\treturn builder.toString();\n" +
                    "}\n" +
                    "    }\n" +
                    "}";
            keyWordList.add(wifi_19_5);




            KeyWordItem wifi_19_6 = new KeyWordItem();
            wifi_19_6.keyword = "mLastSignalLevel ";
            wifi_19_6.explain=" 信号强度  五挡 0,1,2,3,4  初始默认为-1   int mLastSignalLevel = -1;";
            wifi_19_6.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java)  int mLastSignalLevel; ";
            wifi_19_6.shuxingDefine="(WifiServiceImpl.java mClientModeImpl[ClientModeImpl.java])  int mLastSignalLevel = -1 ";
            wifi_19_6.printcode="[WifiServiceImpl.mClientModeImpl.dump 中]  pw.println(\"mLastSignalLevel \" + mLastSignalLevel);";
            keyWordList.add(wifi_19_6);




            KeyWordItem wifi_19_7 = new KeyWordItem();
            wifi_19_7.keyword = "mLastBssid ";
            wifi_19_7.explain=" 连接网络的AP的 BSSID  前三位地址可查看对应的产商  地址:  http://standards-oui.ieee.org/oui/oui.txt ";
            wifi_19_7.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java) String mLastBssid; ";
            wifi_19_7.shuxingDefine="(WifiServiceImpl.java mClientModeImpl[ClientModeImpl.java])   String mLastBssid; ";
            wifi_19_7.printcode="[WifiServiceImpl.mClientModeImpl.dump 中]   pw.println(\"mLastBssid \" + mLastBssid); ";
            keyWordList.add(wifi_19_7);


            KeyWordItem wifi_19_8 = new KeyWordItem();
            wifi_19_8.keyword = "mLastNetworkId ";
            wifi_19_8.explain=" 我们成功加入网络分配的 networkId  ， int mLastNetworkId;  // The network Id we successfully joined  ";
            wifi_19_8.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java) int mLastNetworkId; ";
            wifi_19_8.shuxingDefine="(WifiServiceImpl.java mClientModeImpl[ClientModeImpl.java]) int mLastNetworkId;  ";
            wifi_19_8.printcode="[WifiServiceImpl.mClientModeImpl.dump 中]   pw.println(\"mLastNetworkId \" + mLastNetworkId); ; ";
            keyWordList.add(wifi_19_8);


            KeyWordItem wifi_19_9 = new KeyWordItem();
            wifi_19_9.keyword = "mOperationalMode ";
            wifi_19_9.explain=" 操作模式 int mOperationalMode \n//（连接模式 CONNECT_MODE = 1)  /* CONNECT_MODE - connect to any 'known' AP when it becomes available */" +
                    "\n // (扫描模式 SCAN_ONLY_MODE = 2) /* SCAN_ONLY_MODE - don't connect to any APs; scan, but only while apps hold lock */  " +
                    "\n // (关闭wifi扫描不连接 SCAN_ONLY_WITH_WIFI_OFF_MODE = 3)   /* SCAN_ONLY_WITH_WIFI_OFF - scan, but don't connect to any APs */ " +
                    "\n // ( DISABLED_MODE = 4)   /* DISABLED_MODE - Don't connect, don't scan, don't be an AP */";
            wifi_19_9.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java)  int mOperationalMode = DISABLED_MODE (4); ";
            wifi_19_9.shuxingDefine="(WifiServiceImpl.java mClientModeImpl[ClientModeImpl.java])  int mOperationalMode = DISABLED_MODE (4); ";
            wifi_19_9.printcode="[WifiServiceImpl.mClientModeImpl.dump 中]  pw.println(\"mOperationalMode \" + mOperationalMode); ";
            keyWordList.add(wifi_19_9);



            KeyWordItem wifi_19_10 = new KeyWordItem();
            wifi_19_10.keyword = "mUserWantsSuspendOpt ";
            wifi_19_10.explain=" AtomicBoolean mUserWantsSuspendOpt = getSettings(Settings.Global.WIFI_SUSPEND_OPTIMIZATIONS_ENABLED, 1) == 1); \n" +
                    "Settings.java String WIFI_SUSPEND_OPTIMIZATIONS_ENABLED =\"wifi_suspend_optimizations_enabled\";  \n" +
                    "(未找到设置路径默认为 true )当灭屏时暂停优化对于wifi模块 Setting to turn on suspend optimizations at screen off on Wi-Fi. Enabled by default and needs to be set to 0 to disable it.\n";
            wifi_19_10.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java)  AtomicBoolean mUserWantsSuspendOpt = new AtomicBoolean(true); ";
            wifi_19_10.shuxingDefine="(WifiServiceImpl.java mClientModeImpl[ClientModeImpl.java])  int mOperationalMode = DISABLED_MODE (4); ";
            wifi_19_10.printcode="[WifiServiceImpl.mClientModeImpl.dump 中]  pw.println(\"mOperationalMode \" + mOperationalMode); ";
            keyWordList.add(wifi_19_10);

            KeyWordItem wifi_19_11 = new KeyWordItem();
            wifi_19_11.keyword = "mSuspendOptNeedsDisabled ";
            wifi_19_11.explain=" int mSuspendOptNeedsDisabled = 0;   \n " +
                    "int SUSPEND_DUE_TO_DHCP = 1; 1\n" +
                    "int SUSPEND_DUE_TO_HIGH_PERF = 1 << 1;  2\n" +
                    "int SUSPEND_DUE_TO_SCREEN = 1 << 2;  4  \n"+
                    "跟踪是否需要暂停wiif优化 在 dhcp ，高性能模式下 // Tracks if suspend optimizations need to be disabled by DHCP , screen or due to high perf mode.  When any of them needs to disable it, we keep the suspend optimizations disabled\n";
            wifi_19_11.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java)  int mSuspendOptNeedsDisabled = 0;  ";
            wifi_19_11.shuxingDefine="(WifiServiceImpl.java mClientModeImpl[ClientModeImpl.java])  int mSuspendOptNeedsDisabled = 0;  ";
            wifi_19_11.printcode="[WifiServiceImpl.mClientModeImpl.dump 中]  pw.println(\"mSuspendOptNeedsDisabled \" + mSuspendOptNeedsDisabled); ";
            keyWordList.add(wifi_19_11);


          //  --Dump of SoftApManager--



            KeyWordItem wifi_19_11_1 = new KeyWordItem();
            wifi_19_11_1.keyword = "--Dump of SoftApManager--";
            wifi_19_11_1.explain="【 current StateMachine mode 标识:  SoftApManager.java 的 内部类状态机 class SoftApStateMachine extends StateMachine {}】\n" +
                    "current StateMachine mode  包含 【 IdleState-关闭状态   StartedState-启用状态 】\n" +
                    "mMode: 1  【 热点打开 】    mMode: 0  【 热点关闭 】 \n" +
                    "mApConfig.SSID  【 热点名称 】  apBand 【 -1:Any  0:2G  1:5G 】 hiddenSSID 【是否隐藏SSID】 \n" +
                    "mNumAssociatedStations 【 关联的客户端数量 】 mReportedFrequency 【工作频率】  mTimeoutEnabled【是否打开无STA超时关闭功能】";
            wifi_19_11_1.classNameForShuxing = " (WifiServiceImpl.java -> SoftApManager.java)  dump(FileDescriptor fd, PrintWriter pw, String[] args) {}  ";
            wifi_19_11_1.shuxingDefine="(WifiServiceImpl.java SoftApManager.java )  class SoftApManager implements ActiveModeManager {} ";
            wifi_19_11_1.printcode="[WifiServiceImpl.SoftApManager.java.dump 中]  pw.println(); ";
            keyWordList.add(wifi_19_11_1);

            KeyWordItem wifi_19_12 = new KeyWordItem();
            wifi_19_12.keyword = "mRevertCountryCodeOnCellularLoss: ";
            wifi_19_12.explain=" WIFI国家码相关设置  WifiCountryCode.java ";
            wifi_19_12.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java) WifiCountryCode  mCountryCode; ";
            wifi_19_12.shuxingDefine="public class WifiCountryCode {\n" +
                    " static final String TAG = \"WifiCountryCode\";\n" +
                    " final WifiNative mWifiNative;\n" +
                    " boolean DBG = false;\n" +
                    " boolean mReady = false;\n" +
                    " static final SimpleDateFormat FORMATTER = new SimpleDateFormat(\"MM-dd HH:mm:ss.SSS\");\n" +
                    " boolean mRevertCountryCodeOnCellularLoss; // 指示当前国家码丢失使用默认配置的flag\n" +
                    " String mDefaultCountryCode = null;\n" +
                    " String mTelephonyCountryCode = null;\n" +
                    " String mDriverCountryCode = null;\n" +
                    " String mTelephonyCountryTimestamp = null;\n" +
                    " String mDriverCountryTimestamp = null;\n" +
                    " String mReadyTimestamp = null;\n" +
                    " \n" +
                    "   public synchronized void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "      pw.println(\"mRevertCountryCodeOnCellularLoss: \" + mRevertCountryCodeOnCellularLoss); mRevertCountryCodeOnCellularLoss = boolean mRevertCountryCodeOnCellularLoss //  指示当前国家码丢失使用默认配置的flag\n" +
                    "      pw.println(\"mDefaultCountryCode: \" + mDefaultCountryCode); // mDefaultCountryCode = String mDefaultCountryCode 默认的国家码美国 Locale.US   mDefaultCountryCode = oemDefaultCountryCode.toUpperCase(Locale.US);\n" +
                    "      pw.println(\"mDriverCountryCode: \" + mDriverCountryCode);  //  String mDriverCountryCode 驱动国家码  调用 WifiNative.setCountryCode(String country) 打印:  Succeeded to set country code to:\n" +
                    "      pw.println(\"mTelephonyCountryCode: \" + mTelephonyCountryCode); //  电话国家码  String mTelephonyCountryCode  , 当开启飞行模式时 该值为 null\n" +
                    "      pw.println(\"mTelephonyCountryTimestamp: \" + mTelephonyCountryTimestamp); // 电话国家时间戳 String mTelephonyCountryTimestamp  Telephony service电话服务会调用 WifiCountryCode.setCountryCode(String countryCode) 然后设置该值\n" +
                    "      pw.println(\"mDriverCountryTimestamp: \" + mDriverCountryTimestamp); // 设置驱动国家码时间戳 String mDriverCountryTimestamp\n" +
                    "      pw.println(\"mReadyTimestamp: \" + mReadyTimestamp);  // 记录 wpa_supplicant 完成设置国家码的时间戳 is ready to handle country code\n" +
                    "      pw.println(\"mReady: \" + mReady);  //  boolean mReady 记录 wpa_supplicant是否已经设置成功国家码  true-设置成功 false-设置失败或没有变化\n" +
                    "    }\n" +
                    "}";
            wifi_19_12.printcode="[WifiServiceImpl.mClientModeImpl.dump 中]   mCountryCode.dump(fd, pw, args); ";
            keyWordList.add(wifi_19_12);


            KeyWordItem wifi_19_13 = new KeyWordItem();
            wifi_19_13.keyword = "{WifiNetworkFactory";
            wifi_19_13.explain="";
            wifi_19_13.shuxingDefine="(WifiServiceImpl.java -> ClientModeImpl.java) WifiNetworkFactory mNetworkFactory;";
            wifi_19_13.classUrl="WifiNetworkFactory extends NetworkFactory\n" +
                    "class NetworkFactory extends Handler ";
            wifi_19_13.printcode = "[WifiServiceImpl.mClientModeImpl.dump 中 ]  [WifiNetworkFactory]mNetworkFactory.dump(fd, pw, args){ [NetworkFactory]super,dump(); }";
            wifi_19_13.expain1 = "****************** NetworkCapabilities  解释 Begin ******************\n" +
                    "public final class NetworkCapabilities implements Parcelable {\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "long mTransportTypes;  //  64bit-feature 位码 传输类型?\n" +
                    "long mNetworkCapabilities;  //  64bit-feature 位码  网络能力?\n" +
                    "long mUnwantedNetworkCapabilities;  // 并不想离开网络的能力的集合  如果有那么也连接  能力的白名单?\n" +
                    "\n" +
                    "int mLinkUpBandwidthKbps = 0;   // 上行速率\n" +
                    "int mLinkDownBandwidthKbps = 0;  // 下行速率 \n" +
                    "NetworkSpecifier mNetworkSpecifier = null;  // NetworkSpecifier 网络特别的?   abstract class NetworkSpecifier {抽象类} \n" +
                    "TransportInfo mTransportInfo = null;  //  TransportInfo ?  interface TransportInfo {接口}\n" +
                    "int mSignalStrength = Integer.MIN_VALUE;  // 信号强度?\n" +
                    "ArraySet<UidRange> mUids = null;  // UidRange ? \n" +
                    "int mEstablishingVpnAppUid = INVALID_UID  = -1; ;\n" +
                    "private String mSSID;  // 网络的SSID \n" +
                    "\n" +
                    "    public @NonNull String toString() { \n" +
                    "    final StringBuilder sb = new StringBuilder(\"[\");\n" +
                    "    if (0 != mTransportTypes) {\n" +
                    "        sb.append(\" Transports: \");\n" +
                    "        appendStringRepresentationOfBitMaskToStringBuilder(sb, mTransportTypes, NetworkCapabilities::transportNameOf, \"|\");\n" +
                    "    }\n" +
                    "    if (0 != mNetworkCapabilities) {\n" +
                    "        sb.append(\" Capabilities: \");\n" +
                    "        appendStringRepresentationOfBitMaskToStringBuilder(sb, mNetworkCapabilities,NetworkCapabilities::capabilityNameOf, \"&\");\n" +
                    "    }\n" +
                    "    if (0 != mUnwantedNetworkCapabilities) {\n" +
                    "        sb.append(\" Unwanted: \");\n" +
                    "        appendStringRepresentationOfBitMaskToStringBuilder(sb, mUnwantedNetworkCapabilities,NetworkCapabilities::capabilityNameOf, \"&\");\n" +
                    "    }\n" +
                    "    if (mLinkUpBandwidthKbps > 0) {\n" +
                    "        sb.append(\" LinkUpBandwidth>=\").append(mLinkUpBandwidthKbps).append(\"Kbps\");  //  LinkUpBandwidth = int  mLinkUpBandwidthKbps  // 上行速率 \n" +
                    "    }\n" +
                    "    if (mLinkDownBandwidthKbps > 0) {\n" +
                    "    \tsb.append(\" LinkDnBandwidth>=\").append(mLinkDownBandwidthKbps).append(\"Kbps\");  //  LinkDnBandwidth = int  int mLinkDownBandwidthKbps = 0;  // 下行速率 \n" +
                    "    }\n" +
                    "    if (mNetworkSpecifier != null) {\n" +
                    "        sb.append(\" Specifier: <\").append(mNetworkSpecifier).append(\">\");  //   abstract class NetworkSpecifier  抽象类的实现类? [ MatchAllNetworkSpecifier ] \n" +
                    "    }\n" +
                    "    if (mTransportInfo != null) {\n" +
                    "        sb.append(\" TransportInfo: <\").append(mTransportInfo).append(\">\");  // TransportInfo mTransportInfo = null;  //  TransportInfo ?  interface TransportInfo {接口}\n" +
                    "    }\n" +
                    "    if (hasSignalStrength()) {\n" +
                    "        sb.append(\" SignalStrength: \").append(mSignalStrength);  // SignalStrength =  int mSignalStrength = Integer.MIN_VALUE;  // 信号强度? \n" +
                    "    }\n" +
                    "    if (null != mUids) {\n" +
                    "        if ((1 == mUids.size()) && (mUids.valueAt(0).count() == 1)) {\n" +
                    "           sb.append(\" Uid: \").append(mUids.valueAt(0).start);\n" +
                    "        } else {    sb.append(\" Uids: <\").append(mUids).append(\">\");   } // ArraySet<UidRange> mUids = null;  // int UidRange.start  int UidRange.stop  ?  \n" +
                    "    }\n" +
                    "    if (mEstablishingVpnAppUid != INVALID_UID) {\n" +
                    "       sb.append(\" EstablishingAppUid: \").append(mEstablishingVpnAppUid); // EstablishingAppUid =  int mEstablishingVpnAppUid = INVALID_UID  = -1;   建立VPN的UID? 哪个应用建立了VPN\n" +
                    "    }\n" +
                    "    if (null != mSSID) {\n" +
                    "        sb.append(\" SSID: \").append(mSSID); // SSID =  String mSSID;  // 网络的SSID \n" +
                    "    }\n" +
                    "    sb.append(\"]\");\n" +
                    "    return sb.toString();\n" +
                    "}\n" +
                    "\n" +
                    "// 打印出 long bitMask 每一位对应的能力 以分隔符分割开来 \n" +
                    "    public static void appendStringRepresentationOfBitMaskToStringBuilder(@NonNull StringBuilder sb,\n" +
                    "            long bitMask, @NonNull NameOf nameFetcher, @NonNull String separator 【分割符号占位】) {\n" +
                    "        int bitPos = 0;\n" +
                    "        boolean firstElementAdded = false;\n" +
                    "        while (bitMask != 0) {\n" +
                    "            if ((bitMask & 1) != 0) {\n" +
                    "                if (firstElementAdded) {\n" +
                    "                    sb.append(separator);\n" +
                    "                } else {\n" +
                    "                    firstElementAdded = true;\n" +
                    "                }\n" +
                    "                sb.append(nameFetcher.nameOf(bitPos));\n" +
                    "            }\n" +
                    "            bitMask >>= 1;\n" +
                    "            ++bitPos;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "传输类型列表 bit位 以及对应的名称:     \n" +
                    "private static final String[] TRANSPORT_NAMES = {\"CELLULAR\", \"WIFI\",\"BLUETOOTH\",\"ETHERNET\",\"VPN\",\"WIFI_AWARE\", \"LOWPAN\", \"TEST\" };\n" +
                    "int TRANSPORT_CELLULAR = 0;\n" +
                    "int TRANSPORT_WIFI = 1;\n" +
                    "int TRANSPORT_BLUETOOTH = 2;\n" +
                    "int TRANSPORT_ETHERNET = 3;\n" +
                    "int TRANSPORT_VPN = 4;\n" +
                    "int TRANSPORT_WIFI_AWARE = 5;\n" +
                    "int TRANSPORT_LOWPAN = 6;\n" +
                    "int TRANSPORT_TEST = 7;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "//网络能力long bit位  以及每一位对应的Capability能力 \n" +
                    "int NET_CAPABILITY_MMS            = 0;          case NET_CAPABILITY_MMS:                  return \"MMS\";\n" +
                    "int NET_CAPABILITY_SUPL           = 1;          case NET_CAPABILITY_SUPL:                 return \"SUPL\";\n" +
                    "int NET_CAPABILITY_DUN            = 2;          case NET_CAPABILITY_DUN:                  return \"DUN\";\n" +
                    "int NET_CAPABILITY_FOTA           = 3;          case NET_CAPABILITY_FOTA:                 return \"FOTA\";\n" +
                    "int NET_CAPABILITY_IMS            = 4;          case NET_CAPABILITY_IMS:                  return \"IMS\";\n" +
                    "int NET_CAPABILITY_CBS            = 5;          case NET_CAPABILITY_CBS:                  return \"CBS\";\n" +
                    "int NET_CAPABILITY_WIFI_P2P       = 6;          case NET_CAPABILITY_WIFI_P2P:             return \"WIFI_P2P\";\n" +
                    "int NET_CAPABILITY_IA             = 7;          case NET_CAPABILITY_IA:                   return \"IA\";\n" +
                    "int NET_CAPABILITY_RCS            = 8;          case NET_CAPABILITY_RCS:                  return \"RCS\";\n" +
                    "int NET_CAPABILITY_XCAP           = 9;          case NET_CAPABILITY_XCAP:                 return \"XCAP\";\n" +
                    "int NET_CAPABILITY_EIMS           = 10;         case NET_CAPABILITY_EIMS:                 return \"EIMS\";\n" +
                    "int NET_CAPABILITY_NOT_METERED    = 11;         case NET_CAPABILITY_NOT_METERED:          return \"NOT_METERED\";\n" +
                    "int NET_CAPABILITY_INTERNET       = 12;         case NET_CAPABILITY_INTERNET:             return \"INTERNET\";\n" +
                    "int NET_CAPABILITY_NOT_RESTRICTED = 13;         case NET_CAPABILITY_NOT_RESTRICTED:       return \"NOT_RESTRICTED\";\n" +
                    " int NET_CAPABILITY_TRUSTED        = 14;        case NET_CAPABILITY_TRUSTED:              return \"TRUSTED\";\n" +
                    "int NET_CAPABILITY_NOT_VPN        = 15;         case NET_CAPABILITY_NOT_VPN:              return \"NOT_VPN\";\n" +
                    "int NET_CAPABILITY_VALIDATED      = 16;         case NET_CAPABILITY_VALIDATED:            return \"VALIDATED\";\n" +
                    "int NET_CAPABILITY_CAPTIVE_PORTAL = 17;         case NET_CAPABILITY_CAPTIVE_PORTAL:       return \"CAPTIVE_PORTAL\";\n" +
                    "int NET_CAPABILITY_NOT_ROAMING = 18;            case NET_CAPABILITY_NOT_ROAMING:          return \"NOT_ROAMING\";\n" +
                    "int NET_CAPABILITY_FOREGROUND = 19;             case NET_CAPABILITY_FOREGROUND:           return \"FOREGROUND\";\n" +
                    "int NET_CAPABILITY_NOT_CONGESTED = 20;          case NET_CAPABILITY_NOT_CONGESTED:        return \"NOT_CONGESTED\";\n" +
                    "int NET_CAPABILITY_NOT_SUSPENDED = 21;          case NET_CAPABILITY_NOT_SUSPENDED:        return \"NOT_SUSPENDED\";\n" +
                    "int NET_CAPABILITY_OEM_PAID = 22;               case NET_CAPABILITY_OEM_PAID:             return \"OEM_PAID\";\n" +
                    "int NET_CAPABILITY_MCX = 23;                    case NET_CAPABILITY_MCX:                  return \"MCX\";\n" +
                    "int NET_CAPABILITY_PARTIAL_CONNECTIVITY = 24;   case NET_CAPABILITY_PARTIAL_CONNECTIVITY: return \"PARTIAL_CONNECTIVITY\";\n" +
                    "\t\t\t\n" +
                    "\t\n" +
                    "****************** NetworkCapabilities  解释 End   ******************\n" +
                    "\n" +
                    "【 NetworkFactory 】\n" +
                    "public String toString() {\n" +
                    "      StringBuilder sb = new StringBuilder(\"{\").append(LOG_TAG)\n" +
                    "      .append(\" - mSerialNumber=\").append(mSerialNumber) //  mSerialNumber =  int  mSerialNumber  //注册网络请求编号 类似于 int networkid?  ConnectivityManager.from(mContext).registerNetworkFactory(mMessenger,LOG_TAG)\n" +
                    "      .append(\", ScoreFilter=\")  .append(mScore) // ScoreFilter =  int mScore; // 分数  网络分数?\n" +
                    "      .append(\", Filter=\").append(mCapabilityFilter)  // ****** NetworkCapabilities  Filter =  NetworkCapabilities mCapabilityFilter;  //  网络能力 有网没网 VPN ? \n" +
                    "      .append(\", requests=\").append(mNetworkRequests.size())  // requests = SparseArray<NetworkRequestInfo> mNetworkRequests  请求集合大小\n" +
                    "      .append(\", refCount=\").append(mRefCount)  // refCount = int mRefCount = 0;  引用数量?\n" +
                    "      .append(\"}\");\n" +
                    " return sb.toString();\n" +
                    "}\n" +
                    "}";
            keyWordList.add(wifi_19_13);





            KeyWordItem wifi_19_14 = new KeyWordItem();
            wifi_19_14.keyword = "WifiNetworkFactory: mGenericConnectionReqCount";
            wifi_19_14.explain=" WIFI网络工厂?  网络请求的管理类?";
            wifi_19_14.classNameForShuxing = "  WifiNetworkFactory  extends NetworkFactory  :: NetworkFactory  extends Handler   ";
            wifi_19_14.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java) WifiNetworkFactory mNetworkFactory; ";
            wifi_19_14.printcode=" [WifiServiceImpl.mClientModeImpl.dump 中 ]  [WifiNetworkFactory]mNetworkFactory.dump(fd, pw, args)";
            wifi_19_14.expain1="【 WifiNetworkFactory 】\n" +
                    "class WifiNetworkFactory extends NetworkFactory {\n" +
                    "int mGenericConnectionReqCount = 0; // 请求连接个数? \n" +
                    "NetworkRequest mActiveSpecificNetworkRequest;\n" +
                    "Map<String, Set<AccessPoint>> mUserApprovedAccessPointMap = new HashMap<>();  // 相同ssid 对应不同的bssid的集合\n" +
                    "\n" +
                    " public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "      super.dump(fd, pw, args); //【 NetworkFactory.dump()  】\n" +
                    "      pw.println(TAG + \": mGenericConnectionReqCount \" + mGenericConnectionReqCount);   //  mGenericConnectionReqCount =  int mGenericConnectionReqCount = 0;  请求连接个数? \n" +
                    "      pw.println(TAG + \": mActiveSpecificNetworkRequest \" + mActiveSpecificNetworkRequest);  //  NetworkRequest mActiveSpecificNetworkRequest; 当前选中的请求 活跃的请求\n" +
                    "      pw.println(TAG + \": mUserApprovedAccessPointMap \" + mUserApprovedAccessPointMap); // Map<String, Set<AccessPoint>>  mUserApprovedAccessPointMap = 相同ssid 对应不同的bssid的集合\n" +
                    "    }\n" +
                    "}";
            keyWordList.add(wifi_19_14);



            KeyWordItem wifi_19_15 = new KeyWordItem();
            wifi_19_15.keyword = "  {NetworkRequest [ REQUEST id=1,";
            wifi_19_15.explain=" WIFI网络工厂?  网络请求的管理类?";
            wifi_19_15.classNameForShuxing = "  SparseArray<NetworkRequestInfo>  mNetworkRequests  ,  WifiNetworkFactory  extends NetworkFactory  :: NetworkFactory  extends Handler   ";
            wifi_19_15.shuxingDefine="(WifiServiceImpl.java -> ClientModeImpl.java ->  WifiNetworkFactory mNetworkFactory );  SparseArray<NetworkRequestInfo>  mNetworkRequests ";
            wifi_19_15.printcode=" [WifiServiceImpl.mClientModeImpl.dump 中 ]  [WifiNetworkFactory extend NetworkFactory ]mNetworkFactory.dump(fd, pw, args) -> NetworkFactory.dump()";
            wifi_19_15.expain1="****************** android.net.NetworkRequest.java   ******************  android.net.NetworkRequest.java 解析 Begin\n" +
                    "public class NetworkRequest implements Parcelable {\n" +
                    "public  NetworkCapabilities networkCapabilities;  // 网络能力 NetworkCapabilities 已有分析 long bitmask 比特位\n" +
                    "public final int requestId;  // 区分请求的标识 identify \n" +
                    "public final int legacyType;\n" +
                    "public static enum Type {  NONE, LISTEN,TRACK_DEFAULT, REQUEST,  BACKGROUND_REQUEST, };NetworkRequestProto.TYPE_NONE  NetworkRequestProto.REQUEST \n" +
                    "public final Type type;\n" +
                    "\n" +
                    "public String toString() { //  NetworkRequest 的 toString()\n" +
                    " // 打印 type=【enum Type 】  requestId 【 id = int requestId】 legacyType = int legacyType   NetworkCapabilities包含的long bitmask\n" +
                    "\treturn \"NetworkRequest [ \" + type + \" id=\" + requestId +\n" +
                    "      (legacyType != ConnectivityManager.TYPE_NONE ? \", legacyType=\" + legacyType : \"\") +\n" +
                    "        \", \" + networkCapabilities.toString() + \" ]\";\n" +
                    "}\n" +
                    "}\n" +
                    "****************** android.net.NetworkRequest.java   ******************  android.net.NetworkRequest.java 解析 end\n" +
                    "****************** NetworkFactory.NetworkRequestInfo 内部类 解释 Begin ******************\n" +
                    "    private class NetworkRequestInfo {\n" +
                    "      public final NetworkRequest request;  //   网络请求 NetworkRequest\n" +
                    "      public int score;\n" +
                    "      public boolean requested; // do we have a request outstanding, limited by score\n" +
                    "      public int factorySerialNumber;\n" +
                    "    public String toString() {  return \"{\" + request + \", score=\" + score + \", requested=\" + requested + \"}\"; }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "****************** NetworkFactory.NetworkRequestInfo  内部类 解释 End   ******************\n" +
                    "\n" +
                    "private final SparseArray<NetworkRequestInfo> mNetworkRequests = new SparseArray<NetworkRequestInfo>();\n" +
                    "\t\n" +
                    "public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "      final IndentingPrintWriter pw = new IndentingPrintWriter(writer, \"  \");\n" +
                    "      pw.println(toString());  // 打印 NetworkFactory 自身的 toString \n" +
                    "      pw.increaseIndent();\n" +
                    "      for (int i = 0; i < mNetworkRequests.size(); i++) {\n" +
                    "          pw.println(mNetworkRequests.valueAt(i));  // {NetworkRequest [ REQUEST Capabilities 打印每一个 NetworkRequestInfo , NetworkRequestInfo.toString()\n" +
                    "      }\n" +
                    "      pw.decreaseIndent();\n" +
                    "    }\n" +
                    "\t";

            keyWordList.add(wifi_19_15);




            KeyWordItem wifi_19_16 = new KeyWordItem();
            wifi_19_16.keyword = "{UntrustedWifiNetworkFactory";
            wifi_19_16.explain="不信任的网络管理工程类? ";
            wifi_19_16.classNameForShuxing = "  UntrustedWifiNetworkFactory  extends NetworkFactory  :: NetworkFactory  extends Handler   ";
            wifi_19_16.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java) UntrustedWifiNetworkFactory mUntrustedNetworkFactory; ";
            wifi_19_16.printcode=" [WifiServiceImpl.mClientModeImpl.dump 中 ] [UntrustedWifiNetworkFactory] mUntrustedNetworkFactory.dump(fd, pw, args); ";
            wifi_19_16.expain1="public class UntrustedWifiNetworkFactory extends NetworkFactory {\n" +
                    "    private static final String TAG = \"UntrustedWifiNetworkFactory\";\n" +
                    "    private static final int SCORE_FILTER = Integer.MAX_VALUE;\n" +
                    "    private final WifiConnectivityManager mWifiConnectivityManager;\n" +
                    "    private int mConnectionReqCount = 0;\n" +
                    "\t\n" +
                    "\tpublic void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tsuper.dump(fd, pw, args);  // mSerialNumber ScoreFilter Filter  打印父类  NetworkFactory 的 dump 方法   已有分析  搜索  【  {WifiNetworkFactory End  】\n" +
                    "\tpw.println(TAG + \": mConnectionReqCount \" + mConnectionReqCount);   //  连接请求次数\n" +
                    "}\n";
            keyWordList.add(wifi_19_16);



            KeyWordItem wifi_19_17 = new KeyWordItem();
            wifi_19_17.keyword = "UntrustedWifiNetworkFactory: mConnectionReqCount";
            wifi_19_17.explain="不信任的网络管理工程类? ";
            wifi_19_17.classNameForShuxing = "  UntrustedWifiNetworkFactory  extends NetworkFactory  :: NetworkFactory  extends Handler   ";
            wifi_19_17.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java) UntrustedWifiNetworkFactory mUntrustedNetworkFactory; ";
            wifi_19_17.printcode=" [WifiServiceImpl.mClientModeImpl.dump 中 ] [UntrustedWifiNetworkFactory] mUntrustedNetworkFactory.dump(fd, pw, args); ";
            wifi_19_17.expain1="public class UntrustedWifiNetworkFactory extends NetworkFactory {\n" +
                    "    private static final String TAG = \"UntrustedWifiNetworkFactory\";\n" +
                    "    private static final int SCORE_FILTER = Integer.MAX_VALUE;\n" +
                    "    private final WifiConnectivityManager mWifiConnectivityManager;\n" +
                    "    private int mConnectionReqCount = 0;\n" +
                    "\t\n" +
                    "\tpublic void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tsuper.dump(fd, pw, args);  // mSerialNumber ScoreFilter Filter  打印父类  NetworkFactory 的 dump 方法   已有分析  搜索  【  {WifiNetworkFactory End  】\n" +
                    "\tpw.println(TAG + \": mConnectionReqCount \" + mConnectionReqCount);   //  连接请求次数\n" +
                    "}\n";
            keyWordList.add(wifi_19_17);



            KeyWordItem wifi_19_18 = new KeyWordItem();
            wifi_19_18.keyword = "Wlan Wake Reasons:";
            wifi_19_18.explain="不信任的网络管理工程类? ";
            wifi_19_18.classNameForShuxing = " (WifiServiceImpl.java -> ClientModeImpl.java)  WifiNative mWifiNative .getWlanWakeReasonCount() ;   class WifiNative {}  ";
            wifi_19_18.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java) WifiNative  mWifiNative; ";
            wifi_19_18.printcode=" [WifiServiceImpl.mClientModeImpl.dump 中 ] pw.println(\"Wlan Wake Reasons:\" + mWifiNative.getWlanWakeReasonCount());";
            wifi_19_18.expain1="**************** WifiVendorHal Begin  **************** \n" +
                    "public class WifiVendorHal {\n" +
                    "    // Vendor HAL HIDL interface objects.\n" +
                    "    private IWifiChip mIWifiChip;   // .hal 接口对象 \n" +
                    "\t\n" +
                    "    /**\n" +
                    "     * Fetch the host wakeup reasons stats from wlan driver.\n" +
                    "     * @return the |WlanWakeReasonAndCounts| from the wlan driver, or null on failure.\n" +
                    "     */\n" +
                    "    public WlanWakeReasonAndCounts getWlanWakeReasonCount() {\n" +
                    "        class AnswerBox {  public WifiDebugHostWakeReasonStats value = null;   }\n" +
                    "        AnswerBox ans = new AnswerBox();\n" +
                    "        synchronized (sLock) {\n" +
                    "            if (mIWifiChip == null) return null;\n" +
                    "            try {\n" +
                    "\t\t\t // 调用方法 mIWifiChip 的getDebugHostWakeReasonStats 应该是一个.hal定义的接口 并在回调中得到数据 WifiDebugHostWakeReasonStats\n" +
                    "                mIWifiChip.getDebugHostWakeReasonStats((status, stats) -> { \n" +
                    "                    if (ok(status)) {  ans.value = stats; }\n" +
                    "                });\n" +
                    "                return halToFrameworkWakeReasons(ans.value);  // 对数据进行新的处理 WifiDebugHostWakeReasonStats 转 WlanWakeReasonAndCounts 返回\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "   /**\n" +
                    "     * Translates from Hal version of wake reason stats to the framework version of same\n" +
                    "     * @param h - Hal version of wake reason stats\n" +
                    "     * @return framework version of same\n" +
                    "     */\n" +
                    "    private static WlanWakeReasonAndCounts halToFrameworkWakeReasons(\n" +
                    "            WifiDebugHostWakeReasonStats h) {\n" +
                    "        if (h == null) return null;\n" +
                    "        WlanWakeReasonAndCounts ans = new WlanWakeReasonAndCounts();\n" +
                    "        ans.totalCmdEventWake = h.totalCmdEventWakeCnt;\n" +
                    "        ans.totalDriverFwLocalWake = h.totalDriverFwLocalWakeCnt;\n" +
                    "        ans.totalRxDataWake = h.totalRxPacketWakeCnt;\n" +
                    "        ans.rxUnicast = h.rxPktWakeDetails.rxUnicastCnt;\n" +
                    "        ans.rxMulticast = h.rxPktWakeDetails.rxMulticastCnt;\n" +
                    "        ans.rxBroadcast = h.rxPktWakeDetails.rxBroadcastCnt;\n" +
                    "        ans.icmp = h.rxIcmpPkWakeDetails.icmpPkt;\n" +
                    "        ans.icmp6 = h.rxIcmpPkWakeDetails.icmp6Pkt;\n" +
                    "        ans.icmp6Ra = h.rxIcmpPkWakeDetails.icmp6Ra;\n" +
                    "        ans.icmp6Na = h.rxIcmpPkWakeDetails.icmp6Na;\n" +
                    "        ans.icmp6Ns = h.rxIcmpPkWakeDetails.icmp6Ns;\n" +
                    "        ans.ipv4RxMulticast = h.rxMulticastPkWakeDetails.ipv4RxMulticastAddrCnt;\n" +
                    "        ans.ipv6Multicast = h.rxMulticastPkWakeDetails.ipv6RxMulticastAddrCnt;\n" +
                    "        ans.otherRxMulticast = h.rxMulticastPkWakeDetails.otherRxMulticastAddrCnt;\n" +
                    "        ans.cmdEventWakeCntArray = intsFromArrayList(h.cmdEventWakeCntPerType);\n" +
                    "        ans.driverFWLocalWakeCntArray = intsFromArrayList(h.driverFwLocalWakeCntPerType);\n" +
                    "        return ans;\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "**************** WifiVendorHal End  **************** \n" +
                    "\n" +
                    "**************** WlanWakeReasonAndCounts Begin  **************** \n" +
                    "/**\n" +
                    " * A class representing WLAN wake reason accounting.\n" +
                    " */\n" +
                    "public class WlanWakeReasonAndCounts implements Parcelable {\n" +
                    "    public int totalCmdEventWake;\n" +
                    "    public int totalDriverFwLocalWake;\n" +
                    "    public int totalRxDataWake;\n" +
                    "    public int rxUnicast;\n" +
                    "    public int rxMulticast;\n" +
                    "    public int rxBroadcast;\n" +
                    "\t\n" +
                    "    public int icmp;\n" +
                    "    public int icmp6;\n" +
                    "    public int icmp6Ra;\n" +
                    "    public int icmp6Na;\n" +
                    "    public int icmp6Ns;\n" +
                    "\t\n" +
                    "    public int ipv4RxMulticast;\n" +
                    "    public int ipv6Multicast;\n" +
                    "    public int otherRxMulticast;\n" +
                    "    public int[] cmdEventWakeCntArray;\n" +
                    "    public int[] driverFWLocalWakeCntArray;\n" +
                    "\t\n" +
                    "\t}\n" +
                    "\t\n" +
                    "    public String toString() {\n" +
                    "        StringBuffer sb = new StringBuffer();\n" +
                    "        sb.append(\" totalCmdEventWake \").append(totalCmdEventWake);  // totalCmdEventWake =  int totalCmdEventWake;  // CMD事件引起的wake数量\n" +
                    "        sb.append(\" totalDriverFwLocalWake \").append(totalDriverFwLocalWake);  // totalDriverFwLocalWake =  int totalDriverFwLocalWake;  // 驱动Driver 引起的wake数量\n" +
                    "        sb.append(\" totalRxDataWake \").append(totalRxDataWake);  // totalRxDataWake  接收ReceiveData数据引起的 wake数量\n" +
                    "\n" +
                    "        sb.append(\" rxUnicast \").append(rxUnicast);  // rxUnicast  = int rxUnicast; 接收到的 单播数量           一对一\n" +
                    "        sb.append(\" rxMulticast \").append(rxMulticast);  // rxMulticast  = int rxMulticast; 接收到的 组播数量   一对多(但不是所有)\n" +
                    "        sb.append(\" rxBroadcast \").append(rxBroadcast);  // rxBroadcast = int rxBroadcast;   接收到的广播数量  一对所有\n" +
                    "\n" +
                    "        sb.append(\" icmp \").append(icmp);   // icmp  = int icmp; 接收到icmp的数量  ICMP（Internet Control Message Protocol） Internet控制报文协议\n" +
                    "        \n" +
                    "        // ICMPv6消息共分为两大类，定义了近40种消息类型   第一类消息为错误类消息  第二类消息为信息类消息\n" +
                    "        sb.append(\" icmp6 \").append(icmp6);  //int icmp6;  基于IPv6的ICMP协议我们用 ICMPv6 接收到ICMPv6报文的数量   ICMPv6消息需要IPv6协议进行传输  ICMP协议我们最常用的功能莫过于Ping\n" +
                    "        \n" +
                    "        //int icmp6Ra; 接收到RA路由通告协议 报文的数量 Router Advertisement 路由通告协议是基于NDP协议 NDP（Neighbor Discovery Protocol，邻居发现协议）     \n" +
                    "        // 路由通告协议 RA  路由器周期性地通告它的存在以及配置的链路和网络参数，或者对路由器请求消息作出响应。\n" +
                    "        sb.append(\" icmp6Ra \").append(icmp6Ra);  \n" +
                    "        \n" +
                    "        // NDP定义了5种 ICMPv6 报文类型，包括 \n" +
                    "        //1.RS( Router Solicitation 路由器请求 )  \n" +
                    "        //2.RA( Router Advertisement 路由通告协议 )  \n" +
                    "        //3.NS( Neighbor Solicitationh 领居请求 )\n" +
                    "        //4.NA( Neighbor Advertisement 邻居公告 )\n" +
                    "        //5.Redirec t(重定向报文)\n" +
                    "        // NA (Neighbor Advertisement 邻居公告 ) 邻居请求消息的响应。节点也可以发送非请求邻居通告来指示链路层地址的变化 \n" +
                    "        sb.append(\" icmp6Na \").append(icmp6Na); //  int icmp6Na; 接收到  4.NA( Neighbor Advertisement 邻居公告 ) 协议报文的数量 \n" +
                    "        sb.append(\" icmp6Ns \").append(icmp6Ns);  //  int icmp6Ns;  接收到 3.NS( Neighbor Solicitationh 领居请求 ) 协议报文的数量 \n" +
                    "\n" +
                    "        sb.append(\" ipv4RxMulticast \").append(ipv4RxMulticast); //  int ipv4RxMulticast; 接收到ipv4 组播的数量 \n" +
                    "        sb.append(\" ipv6Multicast \").append(ipv6Multicast);   //  int ipv6Multicast; 接收到ipv6 组播的数量 \n" +
                    "        sb.append(\" otherRxMulticast \").append(otherRxMulticast); // int otherRxMulticast; 接收到的其他协议的组播数量 \n" +
                    "\t\t\n" +
                    "        for (int i = 0; i < cmdEventWakeCntArray.length; i++) {\n" +
                    "            sb.append(\" cmdEventWakeCntArray[\" + i + \"] \" + cmdEventWakeCntArray[i]);  // int[] cmdEventWakeCntArray; 打印int数值?\n" +
                    "        }\n" +
                    "        for (int i = 0; i < driverFWLocalWakeCntArray.length; i++) {\n" +
                    "            sb.append(\" driverFWLocalWakeCntArray[\" + i + \"] \" + driverFWLocalWakeCntArray[i]); // int[] driverFWLocalWakeCntArray;  打印int数值?\n" +
                    "        }\n" +
                    "        return sb.toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "\t[WifiServiceImpl.java -> ClientModeImpl.java  ] dump(){ pw.println(\"Wlan Wake Reasons:\" + mWifiNative.getWlanWakeReasonCount());\n" +
                    "\t// 打印 mWifiNative 的 mWifiNative.getWlanWakeReasonCount 【 WlanWakeReasonAndCounts类 】\n" +
                    "**************** WlanWakeReasonAndCounts End  **************** \n" +
                    "**************** WifiNative Begin  **************** \n" +
                    "\tpublic class WifiNative {\n" +
                    "\t private final WifiVendorHal mWifiVendorHal;\n" +
                    "    public WlanWakeReasonAndCounts getWlanWakeReasonCount() {return mWifiVendorHal.getWlanWakeReasonCount();  }\n" +
                    "}\n" +
                    "**************** WifiNative End  **************** ";
            keyWordList.add(wifi_19_18);



            KeyWordItem wifi_19_19 = new KeyWordItem();
            wifi_19_19.keyword = "Dump of WifiConfigManager";
            wifi_19_19.explain=" WIFI配置管理Config配置操作 ";
            wifi_19_19.classNameForShuxing = "  public class WifiConfigManager {}   ";
            wifi_19_19.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java) WifiConfigManager mWifiConfigManager ; ";
            wifi_19_19.printcode=" [WifiServiceImpl.mClientModeImpl.dump 中 ]   mWifiConfigManager.dump(fd, pw, args); ";
            wifi_19_19.expain1="******************** LocalLog Begin ********************\n" +
                    "public final class LocalLog {\n" +
                    "\n" +
                    "    private final Deque<String> mLog;  // 保存的一些日志字符串\n" +
                    "    private final int mMaxLines; // 最大行\n" +
                    "mLog.add(logLine); // 添加Log\n" +
                    "mLog.remove(logLine);  // 移除Log\n" +
                    "\t    public synchronized void dump(PrintWriter pw) {\n" +
                    "        Iterator<String> itr = mLog.iterator();\n" +
                    "        while (itr.hasNext()) {\n" +
                    "            pw.println(itr.next());  // 打印日志\n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n" +
                    "******************** LocalLog end ********************\n" +
                    "public class WifiConfigManager {\n" +
                    "//  Local log used for debugging any WifiConfigManager issues.\n" +
                    "private final LocalLog mLocalLog = new LocalLog(ActivityManager.isLowRamDeviceStatic() ? 128 : 256);\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "    pw.println(\"Dump of WifiConfigManager\");\n" +
                    "    pw.println(\"WifiConfigManager - Log Begin ----\");\n" +
                    "    mLocalLog.dump(fd, pw, args);  // 打印当前的 【WifiConfigManager】.LocalLog  mLocalLog( 存在一个 Deque<String> 保存Log的字符串队列 )\n" +
                    "    pw.println(\"WifiConfigManager - Log End ----\");\n" +
                    "    pw.println(\"WifiConfigManager - Configured networks Begin ----\");\n" +
                    "    for (WifiConfiguration network : getInternalConfiguredNetworks()) {\n" +
                    "    \tpw.println(network);\n" +
                    "    }\n" +
                    "    pw.println(\"WifiConfigManager - Configured networks End ----\");\n" +
                    "    pw.println(\"WifiConfigManager - Next network ID to be allocated \" + mNextNetworkId);\n" +
                    "    pw.println(\"WifiConfigManager - Last selected network ID \" + mLastSelectedNetworkId);\n" +
                    "    pw.println(\"WifiConfigManager - PNO scan frequency culling enabled = \"    + mPnoFrequencyCullingEnabled);\n" +
                    "    pw.println(\"WifiConfigManager - PNO scan recency sorting enabled = \" + mPnoRecencySortingEnabled);\n" +
                    "    mWifiConfigStore.dump(fd, pw, args);\n" +
                    " }\n" +
                    "}\n" +
                    "resetSimNetworks --- 在时间点 xxxx 执行了 WifiConfigManager.resetSimNetworks() 方法\n" +
                    "clearInternalData: Clearing all internal data --- 在时间点 xxxx 执行了 WifiConfigManager.clearInternalData()方法\n" +
                    "setNetworkSelectionStatus: configKey=   在时间点 xxxx 执行了 WifiConfigManager.setNetworkSelectionStatus() 方法";
            keyWordList.add(wifi_19_19);



            KeyWordItem wifi_19_20 = new KeyWordItem();
            wifi_19_20.keyword = "WifiConfigManager - Configured networks Begin";
            wifi_19_20.explain=" 打印当前内部保存的 WifiConfiguration ";
            wifi_19_20.classNameForShuxing = "  class ConfigurationMap {\n" +
                    " Map<Integer, WifiConfiguration> mPerID = new HashMap<>();\n" +
                    " Map<Integer, WifiConfiguration> mPerIDForCurrentUser = new HashMap<>();\n" +
                    " Map<ScanResultMatchInfo, WifiConfiguration> mScanResultMatchInfoMapForCurrentUser = new HashMap<>();\n" +
                    " UserManager mUserManager;\n" +
                    " int mCurrentUserId = UserHandle.USER_SYSTEM;\n" +
                    " }   ";
            wifi_19_20.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java )  ConfigurationMap mConfiguredNetworks; ; ";
            wifi_19_20.printcode=" [WifiServiceImpl.mClientModeImpl.mWifiConfigManager.dump 中 ]   dump(){ for 循环打印WifiConfiguration  (WifiConfiguration network : getInternalConfiguredNetworks()【ConfigurationMap】) {  pw.println(network); }}" +
                    "public class WifiConfigManager {\n" +
                    "void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    ".....\n" +
                    "    for (WifiConfiguration network : getInternalConfiguredNetworks()) {\n" +
                    "    \tpw.println(network 【 WifiConfiguration.toString()】);\n" +
                    "    }\n" +
                    " ......\n" +
                    " }\n" +
                    "}";
            wifi_19_20.expain1="******************** WifiConfiguration   500行 Begin  ********************\n" +
                    "public class WifiConfiguration implements Parcelable {\n" +
                    "int BACKUP_VERSION = 3; // 标识把 WifiConfiguration 写入xml的方式的版本 版本间写入和读取的方式不同 所以版本不同将导致问题\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "String wepTxKeyIdxVarName = \"wep_tx_keyidx\";  // ?\n" +
                    "\n" +
                    "String hiddenSSIDVarName = \"scan_ssid\"; // 需要隐藏SSID的对应的SSID\n" +
                    "String pmfVarName = \"ieee80211w\"; //?\n" +
                    "String updateIdentiferVarName = \"update_identifier\"; // ?\n" +
                    "int INVALID_NETWORK_ID = -1;  // 无效的networkID\n" +
                    "int LOCAL_ONLY_NETWORK_ID = -2; // 本地的networkId?\n" +
                    "String mPasspointManagementObjectTree; // ? 不知道\n" +
                    "int MAXIMUM_RANDOM_MAC_GENERATION_RETRY = 3; // MAC地址随机化地址获取重试次数?\n" +
                    " int UNKNOWN_UID = -1;\n" +
                    " int networkId;  // 网络的 networkid\n" +
                    " String ssidVarName = \"ssid\";  // WIFI名称\n" +
                    " String SSID;  // WIFI 名称\n" +
                    " String bssidVarName = \"bssid\";  // WIFI名称对应的BSSID AP的mac地址\n" +
                    " String BSSID;   //  WIFI的MAC地址\n" +
                    "int status ;  // 当前网络状态  static class Status {  int CURRENT = 0;  int DISABLED = 1; int ENABLED = 2;}\n" +
                    "\n" +
                    "int apBand = AP_BAND_2GHZ;   //  当前热点的信道选择策略\n" +
                    "final int AP_BAND_2GHZ = 0;  // 热点只工作在 2.4G 信道\n" +
                    "final int AP_BAND_5GHZ = 1; // 热点只工作在 5G 信道\n" +
                    "final int AP_BAND_DUAL = 2; // 同时工作在 2.4G 以及 5G 信道上  相当于两个WIFI\n" +
                    "final int AP_BAND_ANY = -1; // 依据设备的能力选择在 5G  或者  2.4G 信道 工作 \n" +
                    "\n" +
                    "int apChannel = 0; // 具体工作的信道编号  2G  【1-11】  5G  【36,40,44,48,149,153,157,161,165】\n" +
                    "String preSharedKey; // WIFI 密码\n" +
                    "public String[] wepKeys;  //  使用 wep 加密时的秘钥\n" +
                    "int wepTxKeyIndex;  //  ranging from 0 to 3.   wep中使用的索引\n" +
                    "\n" +
                    "String priorityVarName = \"priority\"; // 提供给 wpasupplicant的网络连接优先级 \n" +
                    "int priority;  // 等级 \n" +
                    "\n" +
                    "public boolean hiddenSSID; // 是否隐藏热点\n" +
                    "boolean ephemeral;  //  标识该网络时临时的 不应该保存 , 也不应该自动加入 \n" +
                    "boolean requirePMF; // 标识这是一个帧保护的wifi网络  This is a network that requries (Protected Management Frames) (PMF). \n" +
                    "boolean trusted;  // 标识该网络是否得到用户主动加入 似乎信任的网络\n" +
                    "String updateIdentifier; //  更新标识符 identify 用于passpoint网络\n" +
                    "String FQDN; // (Fully Qualified Domain Name)全限定域名 Passpoint 的域名 用于标识运营商\n" +
                    "String providerFriendlyName;  // 人类可读的名称 用于passpoint网络\n" +
                    "boolean isHomeProviderNetwork;  //  是否家庭passpoint网络  \n" +
                    "long[] roamingConsortiumIds;  // 用于passpoint网络的漫游凭证\n" +
                    "boolean mncmcc_hs20_network;   // 是否是依据sim卡的 mncmcc的passpoint网络\n" +
                    "boolean osu;  // 标识当前网络是在线注册的网络吗   Wifi configuration is expected for OSU(Online Sign Up)  \n" +
                    "int  eapRetryCount;  // eap 认证操作的重试次数\n" +
                    "boolean preloaded_ssid;     //是否预置的网络\n" +
                    "boolean preloaded_forget;  // 是否可以忘记delete的忘记\n" +
                    "boolean mHasEverConnected;  // 标识是否曾经成功连接过该网络  Boolean indicating if we have ever successfully connected to this network. \n" +
                    "boolean mNotRecommended;   // 标识网络选择过程中  该网络被标识为不推荐   true为不推荐连接  false为正常\n" +
                    "\n" +
                    "String mNetworkSelectionBSSID;   // 记录通过网络选择 选中的那个网络的 bssid\n" +
                    "\n" +
                    "boolean meteredHint;  // 指示当前网络是否是计费的网络\n" +
                    "int METERED_OVERRIDE_NONE = 0;\n" +
                    "int METERED_OVERRIDE_METERED = 1;\n" +
                    "int METERED_OVERRIDE_NOT_METERED = 2;\n" +
                    "int meteredOverride = METERED_OVERRIDE_NONE;  // wifi网络计费的状态?\n" +
                    "boolean useExternalScores;  // 是否使用外部评分机制  对wifi进行评分\n" +
                    "int numScorerOverride;  // 记录当前 wificonfiguration 与其他的 wificonfig  进行对比的次数\n" +
                    "int numScorerOverrideAndSwitchedNetwork;  // 记录当前 wificonfiguration 与其他的 wificonfig  进行对比  并且完成了网络切换的次数\n" +
                    "int numAssociation;  // 与该wificonfiguration 关联的总的次数\n" +
                    "\n" +
                    "\n" +
                    "String mConnectChoice; // 当前wifi配置对用户可见 但用户明确连接另一个网络配置,该字段就是保存该另一个配置的字符串字段 字符串样式为:\"SSID\"-WEP-WPA_PSK-WPA_EAP \n" +
                    "long mConnectChoiceTimestamp = INVALID_NETWORK_SELECTION_DISABLE_TIMESTAMP;  // 记录除了当前可见wifi 用户连接到另一个可见wifi的时间戳\n" +
                    "ScanResult mCandidate; // 用于在网络选择中 临时缓存 候选项 直到扫描到分数更高的网络 并进行更新  保存到这里\n" +
                    "int mCandidateScore;   // 当前保存的缓存网络扫描结果的得分分数\n" +
                    "boolean mSeenInLastQualifiedNetworkSelection;  //  可见 与 不可见 \n" +
                    "\n" +
                    "//  保存等价的 可以 漫游到 或者 自动加入的 网络的集合  , value 应该是序号  roaming and auto-joining. \n" +
                    "HashMap<String, Integer>  linkedConfigurations;  // key-String 是  configKey()标识一个网络  value是分数?  标识\n" +
                    "int skip_auto_connect;   // 跳过自动连接\n" +
                    "// skip_auto_connect = 0; //  不跳过自动连接\n" +
                    "// skip_auto_connect = 1; //  要跳过自动连接\n" +
                    "\n" +
                    "int macRandomizationSetting = RANDOMIZATION_PERSISTENT;   // 与该网络 wificonfig 连接时使用 mac地址的策略\n" +
                    "final int RANDOMIZATION_NONE = 0;   //  使用自身Mac地址进行连接\n" +
                    "final int RANDOMIZATION_PERSISTENT = 1;   // 使用随机的Mac地址与该网络进行连接  并且下次连接这个wifi也是用这个随机的地址\n" +
                    "MacAddress mRandomizedMacAddress;  // 随机地址  MacAddress implements Parcelable { long mAddr;}\n" +
                    "\n" +
                    "// 选择网络的状态\n" +
                    " NetworkSelectionStatus mNetworkSelectionStatus = new NetworkSelectionStatus();  // 当前的网络状态类\n" +
                    "int mStatus;  // 记录当前的状态  0-ENABLED   1-TEMPORARY_DISABLED  2-PERMANENTLY_DISABLED\n" +
                    "class NetworkSelectionStatus {\n" +
                    " int NETWORK_SELECTION_ENABLED = 0;  // 标识该网络允许加入网络选择中  This network is allowed to join Quality Network Selection\n" +
                    "int NETWORK_SELECTION_TEMPORARY_DISABLED  = 1;  // 当前网络暂时不可加入 一段时间后可恢复 temporary disabled. Can be re-enabled after a time period expire\n" +
                    "int NETWORK_SELECTION_PERMANENTLY_DISABLED  = 2;  // 该网络永远无法加入\n" +
                    " }\n" +
                    " \n" +
                    " \n" +
                    " int mAssociationStatus = NONE;  // 关联失败的原因  3离开自己leave   17热点满了 0-初始化值\n" +
                    " // 选择网络的状态名称列表\n" +
                    "String[] QUALITY_NETWORK_SELECTION_STATUS = {\"NETWORK_SELECTION_ENABLED\", \"NETWORK_SELECTION_TEMPORARY_DISABLED\", \"NETWORK_SELECTION_PERMANENTLY_DISABLED\"};\n" +
                    "// Default value. Means not disabled   网络状态不可用原因\n" +
                    "final int NETWORK_SELECTION_ENABLE = 0; \n" +
                    "final int NETWORK_SELECTION_DISABLED_STARTING_INDEX = 1;\n" +
                    "// 网络状态不可用原因\n" +
                    "int mNetworkSelectionDisableReason;  // 网络不可用的原因, 范围在以下 1 - 14\n" +
                    "final int DISABLED_BAD_LINK = 1;  //  This network is disabled because higher layer (>2) network is bad\n" +
                    "final int DISABLED_ASSOCIATION_REJECTION = 2;  //多次拒绝关联  This network is disabled because multiple association rejects\n" +
                    "final int DISABLED_AUTHENTICATION_FAILURE = 3;  // 多次认证失败\n" +
                    "final int DISABLED_DHCP_FAILURE = 4;   //  多次 DHCP 获取失败 This network is disabled because multiple DHCP failure\n" +
                    "final int DISABLED_DNS_FAILURE = 5;  //  由于网络安全原因禁止  This network is disabled because of security network but no credentials \n" +
                    "final int DISABLED_NO_INTERNET_TEMPORARY = 6;  //由于当前网络当前没有网络 不能上网 所以被禁止  This network is temporarily disabled because it has no Internet access.\n" +
                    "final int DISABLED_WPS_START = 7;   // 由于WPS联网开始  所以其他网络暂时Disable   This network is disabled because we started WPS\n" +
                    "final int DISABLED_TLS_VERSION_MISMATCH = 8;  // EAP-TLS failure  认证失败所以被禁止  This network is disabled because EAP-TLS failure \n" +
                    "final int DISABLED_AUTHENTICATION_NO_CREDENTIALS = 9;  //  由于缺少用户凭证所以当前网络被禁止  This network is disabled due to absence of user credentials\n" +
                    "final int DISABLED_NO_INTERNET_PERMANENT = 10; //  此网络被用户禁用  This network is permanently disabled because it has no Internet access and user does not want to stay connected. \n" +
                    "final int DISABLED_BY_WIFI_MANAGER = 11;  // 被 wifimanager禁用了该网络  This network is disabled due to WifiManager disable it explicitly\n" +
                    "final int DISABLED_DUE_TO_USER_SWITCH = 12;   //由于用户进行了切换网络 此网络被暂时禁用  This network is disabled due to user switching\n" +
                    "final int DISABLED_BY_WRONG_PASSWORD = 13;  // 由于错误的密码 此网络被禁用  This network is disabled due to wrong password\n" +
                    "final int DISABLED_AUTHENTICATION_NO_SUBSCRIPTION = 14; // 由于没有订阅服务 此网络被禁用  This network is disabled because service is not subscribed\n" +
                    "// 网络禁用名称字符串列表 \n" +
                    "public static final String[] QUALITY_NETWORK_SELECTION_DISABLE_REASON = {\n" +
                    "\"NETWORK_SELECTION_ENABLE\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_BAD_LINK\", // deprecated\n" +
                    "\"NETWORK_SELECTION_DISABLED_ASSOCIATION_REJECTION \",\n" +
                    "\"NETWORK_SELECTION_DISABLED_AUTHENTICATION_FAILURE\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_DHCP_FAILURE\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_DNS_FAILURE\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_NO_INTERNET_TEMPORARY\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_WPS_START\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_TLS_VERSION\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_AUTHENTICATION_NO_CREDENTIALS\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_NO_INTERNET_PERMANENT\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_BY_WIFI_MANAGER\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_BY_USER_SWITCH\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_BY_WRONG_PASSWORD\",\n" +
                    "\"NETWORK_SELECTION_DISABLED_AUTHENTICATION_NO_SUBSCRIPTION\"\n" +
                    "};\n" +
                    "\n" +
                    "final long INVALID_NETWORK_SELECTION_DISABLE_TIMESTAMP = -1L;  // 当前禁用网络的时间戳,的默认无效值\n" +
                    "long mTemporarilyDisabledTimestamp = INVALID_NETWORK_SELECTION_DISABLE_TIMESTAMP; // 记录把当前网络wificonfig设置禁用的时间戳\n" +
                    "\n" +
                    "final int CONNECT_CHOICE_EXISTS = 1;  // 标识当前选择的网络 存在 wifi扫描列表  能尝试连接\n" +
                    "final int CONNECT_CHOICE_NOT_EXISTS = -1; // 标识当前选择的网络  不存在 wifi扫描列表 无法连接\n" +
                    "\n" +
                    "boolean fromWifiNetworkSuggestion;   // 标识该网络是由WifiNetworkSuggestion创建   This Wifi configuration is created from a {@link WifiNetworkSuggestion}\n" +
                    "boolean fromWifiNetworkSpecifier;  // 标识该网络是由 WifiNetworkSpecifier 创建   使用 WifiNetworkSpecifier 描述所请求网络的属性来提示用户更改设备所连接的接入点\n" +
                    "boolean shared;  // 设备上的其他用户是否共享此网络\n" +
                    "IpConfiguration mIpConfiguration;  // IP地址配置  IpConfiguration implements Parcelable {}\n" +
                    "\n" +
                    "\n" +
                    "String dhcpServer;  // dhcp 服务器地址\n" +
                    "String wifi_ap_gateway;  // 网关地址\n" +
                    "String defaultGwMacAddress; // 默认网关地址\n" +
                    "String dhcp_start_addr;  //可选的 dhcp 起始地址\n" +
                    "String dhcp_end_addr;  //可选的 dhcp 结束地址\n" +
                    "\n" +
                    "boolean validatedInternetAccess;  // 是否已经验证可访问网络 能上网\n" +
                    "int numNoInternetAccessReports;  // 报告当前网络无联网的次数  不能上网\n" +
                    "boolean noInternetAccessExpected;  // 让用户选择的配置 预计当前config对应的网络wifi不能连接  true(预计不能联网)  false(预计能联网)\n" +
                    "\n" +
                    "\n" +
                    "int dtimInterval = 0; // 信标时间  Valid values are from 1 - 255. Initialized here as 0\n" +
                    "WifiEnterpriseConfig enterpriseConfig;  //  企业配置基类   WifiEnterpriseConfig implements Parcelable {}\n" +
                    "\n" +
                    "int creatorUid; // 创建这个 WifiConfiguration 的 APP的ID UID 用户id\n" +
                    "int lastConnectUid;  // 最后连接此网络的设备上的 UID 用户\n" +
                    "int lastUpdateUid;   // 最近修改这个网络的 UID\n" +
                    "String creatorName;   // 创建这个WifiConfiguration的 用户的名称\n" +
                    "String lastUpdateName;  // WifiConfiguration 配置最后的修改时间\n" +
                    "String updateTime; // 同  lastUpdateName\n" +
                    "String creationTime;  // 创建 WifiConfig的时间戳\n" +
                    "\n" +
                    "long lastConnected;  // 最近连接到该wifi网络的时间\n" +
                    "long lastDisconnected;   // 最近断开该wifi网络的时间\n" +
                    "boolean selfAdded;  // 标识该wificonfig是否是框架自动添加的 不是用户添加的  当用户连接此网络时 该值置为 false\n" +
                    " boolean didSelfAdd;   // 不懂 \n" +
                    "\n" +
                    " int userApproved = USER_UNSPECIFIED; // 连接用户的批准状态 [0缺省 1批准 2拒绝 3等待用户选择]\n" +
                    "int USER_UNSPECIFIED = 0;  缺省未定义\n" +
                    "int USER_APPROVED = 1;  用户批准\n" +
                    "int USER_BANNED = 2;  用户拒绝\n" +
                    "int USER_PENDING = 3; 等待用户 选择的状态\n" +
                    "\n" +
                    "\n" +
                    "int INVALID_RSSI = -127;   // 无效的 wifi 信号强度\n" +
                    "\n" +
                    "int sim_slot;    // 当前连接的eap-sim wifi网络 对应的卡槽\n" +
                    "int max_scb;    // 设备作为热点的最大连接客户端数量\n" +
                    "\n" +
                    "\n" +
                    " //  各种功能比特位\n" +
                    "BitSet allowedKeyManagement;\n" +
                    "BitSet allowedProtocols;\n" +
                    "BitSet allowedAuthAlgorithms;\n" +
                    "BitSet allowedPairwiseCiphers;\n" +
                    "BitSet allowedGroupCiphers;\n" +
                    "BitSet allowedGroupManagementCiphers;\n" +
                    "BitSet allowedSuiteBCiphers;\n" +
                    "\n" +
                    "// 使用wifi密码时的加密方式 \"psk\"\n" +
                    "String pskVarName = \"psk\"; // WIFI密码\n" +
                    "String[] wepKeyVarNames = { \"wep_key0\", \"wep_key1\", \"wep_key2\", \"wep_key3\" }; // 加密类型的数组  都要使用密码 但安全算法不同\n" +
                    "\n" +
                    "\n" +
                    " // 加密方式分类  \"key_mgmt\"\n" +
                    "static class KeyMgmt {  // WIFI机密方式分类\n" +
                    " int NONE = 0; WPA_PSK = 1; WPA_EAP = 2; int IEEE8021X = 3;  int WPA2_PSK = 4; int OSEN = 5;   int FT_PSK = 6;\n" +
                    "  int FT_EAP = 7;  int SAE = 8;  int OWE = 9; int SUITE_B_192 = 10;int WPA_PSK_SHA256 = 11; int WPA_EAP_SHA256 = 12;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    " public static final String[] strings = { \"NONE\", \"WPA_PSK\", \"WPA_EAP\",\"IEEE8021X\", \"WPA2_PSK\", \"OSEN\",\n" +
                    " \"FT_PSK\", \"FT_EAP\", \"SAE\", \"OWE\", \"SUITE_B_192\", \"WPA_PSK_SHA256\", \"WPA_EAP_SHA256\" };\n" +
                    " \n" +
                    " // 协议分类  \"proto\"\n" +
                    " static class Protocol {int WPA = 0; int RSN = 1; int OSEN = 2;}\n" +
                    "public static final String varName = \"proto\";\n" +
                    "public static final String[] strings = { \"WPA\", \"RSN\", \"OSEN\" };\n" +
                    "\n" +
                    "// 认证算法分类 \"auth_alg\"\n" +
                    "static class AuthAlgorithm {  int OPEN = 0;  int SHARED = 1; int LEAP = 2; }\n" +
                    "public static final String varName = \"auth_alg\";\n" +
                    "public static final String[] strings = { \"OPEN\", \"SHARED\", \"LEAP\" };\n" +
                    "\n" +
                    "// 组秘钥加密方式 \"pairwise\"\n" +
                    "static class PairwiseCipher {  int NONE = 0; int TKIP = 1;  int CCMP = 2; int GCMP_256 = 3;}\n" +
                    "public static final String varName = \"pairwise\";\n" +
                    "public static final String[] strings = { \"NONE\", \"TKIP\", \"CCMP\", \"GCMP_256\" };\n" +
                    "\n" +
                    "//组秘钥类型 \"group\"\n" +
                    "static class GroupCipher { int WEP40 = 0; int WEP104 = 1; int TKIP = 2; int CCMP = 3;  int GTK_NOT_USED = 4; int GCMP_256 = 5; }\n" +
                    "public static final String varName = \"group\";\n" +
                    "public static final String[] strings =\n" +
                    "{ /* deprecated */ \"WEP40\", /* deprecated */ \"WEP104\",\"TKIP\", \"CCMP\", \"GTK_NOT_USED\", \"GCMP_256\" };\n" +
                    "\n" +
                    "//组管理帧的加密类型 \"groupMgmt\"\n" +
                    "static class GroupMgmtCipher { int BIP_CMAC_256 = 0; int BIP_GMAC_128 = 1; int BIP_GMAC_256 = 2;}\n" +
                    "static final String varName = \"groupMgmt\";\n" +
                    "static final String[] strings = { \"BIP_CMAC_256\",\"BIP_GMAC_128\", \"BIP_GMAC_256\"};\n" +
                    "\t\n" +
                    "//SuiteB安全方式的加密类型 \"SuiteB\"\n" +
                    "static class SuiteBCipher { int ECDHE_ECDSA = 0;  int ECDHE_RSA = 1; }\n" +
                    "static final String varName = \"SuiteB\";\n" +
                    "static final String[] strings = { \"ECDHE_ECDSA\", \"ECDHE_RSA\" };\n" +
                    "\n" +
                    "//  当前网络的状态\n" +
                    "static class Status {  int CURRENT = 0;  int DISABLED = 1; int ENABLED = 2;}\n" +
                    "String[] strings = { \"current\", \"disabled\", \"enabled\" };\n" +
                    "\n" +
                    "//支持的无线加密类型  Security types we support. \n" +
                    "int SECURITY_TYPE_OPEN = 0;  int SECURITY_TYPE_WEP = 1; int  SECURITY_TYPE_PSK = 2; int SECURITY_TYPE_EAP = 3;\n" +
                    "int SECURITY_TYPE_SAE = 4; int SECURITY_TYPE_EAP_SUITE_B = 5;  int SECURITY_TYPE_OWE = 6;\n" +
                    "\n" +
                    "\n" +
                    "***************** WifiConfiguration 的 toString() 方法 Begin ***************** \n" +
                    "https://www.androidos.net.cn/android/10.0.0_r6/xref/frameworks/base/wifi/java/android/net/wifi/WifiConfiguration.java#2374\n" +
                    "***************** WifiConfiguration 的 toString() 方法 End ***************** \n" +
                    "sim_slot = int sim_slot;    // 当前连接的eap-sim wifi网络 对应的卡槽  mncmcc_hs20_network  boolean mncmcc_hs20_network;   // 是否是依据sim卡的 mncmcc的passpoint网络\n" +
                    "preloaded_forget = boolean preloaded_forget;  // 是否可以忘记delete的忘记   true--可以删除   false--不可以删除\n" +
                    "skip_auto_connect = int skip_auto_connect;   // 跳过自动连接 0-不跳过自动连接  1-要跳过自动连接\n" +
                    "eapRetryCount = int  eapRetryCount;  // eap 认证操作的重试次数   preloaded_ssid = boolean preloaded_ssid; //是否预置的网络 true-预置  false不是预置\n" +
                    "recentFailure = int mAssociationStatus = NONE;  // 关联失败的原因  3离开自己leave   17热点满了 0-初始化值 reasonCode   断开原因列表  https://sse.am.mot.com/p_source/xref/mp-r-sh2019/vendor/qcom/opensource/wlan/qcacld-3.0/core/mac/inc/sir_mac_prot_def.h#745\n" +
                    "linked =  HashMap<String, Integer>  linkedConfigurations.KeySet();  Key = \"D-Link_DIR-816\"WPA_PSK\"  \"D-Link_DIR-816_5G\"WPA_PSK\"  // key-String 是  configKey()标识一个网络  value是分数?  标识 \n" +
                    "noInternetAccessExpected = boolean noInternetAccessExpected;  //  true(预计不能联网)  false(预计能联网)  让用户选择的配置 预计当前config对应的网络wifi不能连接 \n" +
                    "userApproved = int userApproved = USER_UNSPECIFIED; // 连接用户的批准状态 [0-USER_UNSPECIFIED-缺省  1-USER_APPROVED-批准 2-USER_BANNED-拒绝 3-USER_PENDING-等待用户选择]\n" +
                    "lname =  String lastUpdateName;  // WifiConfiguration 配置最后的修改时间  lcuid = int lastConnectUid;  // 最后连接此网络的设备上的 UID 用户\n" +
                    "cname = String creatorName;   // 创建这个WifiConfiguration的 用户的名称    luid =  int lastUpdateUid;   // 最近修改这个网络的 UID\n" +
                    "cuid = int creatorUid; // 创建这个 WifiConfiguration 的 APP的ID UID 用户id\n" +
                    "Proxy settings = mIpConfiguration.toString() = IpConfiguration.[ ProxySettings]proxySettings.toString()   public enum ProxySettings {NONE, STATIC,UNASSIGNED, PAC }\n" +
                    "IP assignment = IP地址分配方式 mIpConfiguration.toString() = IpConfiguration.[IpAssignment]ipAssignment.toString()   enum IpAssignment { STATIC, DHCP, UNASSIGNED}\n" +
                    "IP config = IpConfiguration mIpConfiguration;  // IP地址配置 toString\n" +
                    "Enterprise config = WifiEnterpriseConfig enterpriseConfig;  //  企业配置基类\n" +
                    "PSK/SAE = String preSharedKey; // WIFI 密码 默认使用* 代替\n" +
                    "SuiteBCiphers = BitSet allowedSuiteBCiphers; =  { int ECDHE_ECDSA = 0;  int ECDHE_RSA = 1; } \n" +
                    "GroupMgmtCiphers = BitSet allowedGroupManagementCiphers; = { int BIP_CMAC_256 = 0; int BIP_GMAC_128 = 1; int BIP_GMAC_256 = 2;} \n" +
                    "GroupCiphers = BitSet allowedGroupCiphers; = { int WEP40 = 0; int WEP104 = 1; int TKIP = 2; int CCMP = 3;  int GTK_NOT_USED = 4; int GCMP_256 = 5; }\n" +
                    "AuthAlgorithms = BitSet allowedAuthAlgorithms =  {  int OPEN = 0;  int SHARED = 1; int LEAP = 2; }\n" +
                    "Protocols = BitSet allowedProtocols  = {int WPA = 0; int RSN = 1; int OSEN = 2;}\n" +
                    "KeyMgmt =  BitSet allowedKeyManagement =  KeyMgmt {int NONE = 0; WPA_PSK = 1; WPA_EAP = 2; int IEEE8021X = 3;  int WPA2_PSK = 4; int OSEN = 5;   int FT_PSK = 6;\n" +
                    "int FT_EAP = 7;  int SAE = 8;  int OWE = 9; int SUITE_B_192 = 10;int WPA_PSK_SHA256 = 11; int WPA_EAP_SHA256 = 12;\n" +
                    "DHCP_END_ADDR = String dhcp_end_addr;  //可选的 dhcp 结束地址 \n" +
                    "WIFI_AP_GATEWAY = String wifi_ap_gateway;  // 网关地址  DHCP_START_ADDR = String dhcp_start_addr;  //可选的 dhcp 起始地址 \n" +
                    "mRandomizedMacAddress  =  MacAddress mRandomizedMacAddress;  // 随机地址\n" +
                    "macRandomizationSetting = int macRandomizationSetting; 【 0=使用自身Mac地址进行连接   1 =使用随机的Mac地址与该网络进行连接 】   // 与该网络 wificonfig 连接时使用 mac地址的策略\n" +
                    "creation time = tring creationTime;  // 创建 WifiConfig的时间戳   trusted = boolean trusted;  // 标识该网络是否得到用户主动加入 似乎信任的网络\n" +
                    "hasEverConnected =  boolean mHasEverConnected;  // 标识是否曾经成功连接过该网络    numAssociation =  int numAssociation;  // 与该wificonfiguration 关联的总的次数\n" +
                    "connect choice set time: =  long mConnectChoiceTimestamp  // 记录除了当前可见wifi 用户连接到另一个可见wifi的时间戳\n" +
                    "connect choice =   String mConnectChoice;  // 当前wifi配置对用户可见 但用户明确连接另一个网络配置,该字段就是保存该另一个配置的字符串字段 字符串样式为:\"SSID\"-WEP-WPA_PSK-WPA_EAP   \"D-Link_DIR-816\"WPA_PSK\n" +
                    "NetworkSelectionDisableReason  = int xx[ 1 -- 14 ] QUALITY_NETWORK_SELECTION_DISABLE_REASON  禁用网络的原因\n" +
                    "NetworkSelectionStatus =  NetworkSelectionStatus mNetworkSelectionStatus【 0-ENABLED   1-TEMPORARY_DISABLED  2-PERMANENTLY_DISABLED 】\n" +
                    "PRIO = int priority;  // 等级    HIDDEN = boolean hiddenSSID; // 是否隐藏热点   PMF =  boolean requirePMF; // 标识是否是一个帧保护的wifi网络\n" +
                    "BSSID = String BSSID;   //WIFI的MAC地址    FQDN = String FQDN; // (Fully Qualified Domain Name)全限定域名 Passpoint 的域名 用于标识运营商\n" +
                    "PROVIDER-NAME = String providerFriendlyName;  // 人类可读的名称 用于passpoint网络 \n" +
                    "ID =  int networkId;  // 网络的 networkid       SSID =  String SSID;  // WIFI 名称 \n" +
                    "******************** WifiConfiguration   500行 End  ********************\n";
            keyWordList.add(wifi_19_20);


            KeyWordItem wifi_19_21 = new KeyWordItem();
            wifi_19_21.keyword = "WifiConfigManager - Configured networks End";
            wifi_19_21.explain=" WifiConfigManager 的一些 networkId 属性   PNO 属性 ";
            wifi_19_21.shuxingDefine="(WifiServiceImpl.java mClientModeImpl[ClientModeImpl.java])  WifiConfigManager mWifiConfigManager ";
            wifi_19_21.printcode="[WifiServiceImpl.mClientModeImpl.mWifiConfigManager.dump 中 ]   dump(){} ";
            wifi_19_21.expain1 = "public class WifiConfigManager {\n" +
                    "int mNextNetworkId = 0; // 下一个创建WifiConfiguration 分配得到的 networdId\n" +
                    "int mLastSelectedNetworkId;  // 最新的应用请求连接的 networkId\n" +
                    "\n" +
                    "// 与 Settings.Global.WIFI_PNO_FREQUENCY_CULLING_ENABLED 相关   WIFI_PNO_FREQUENCY_CULLING_ENABLED_DEFAULT\n" +
                    "boolean mPnoFrequencyCullingEnabled = false;   // 是否开启 PNO frequency culling optimization.  PNO 灭屏扫描频率优化\n" +
                    "\n" +
                    "// Settings.Global.WIFI_PNO_RECENCY_SORTING_ENABLED,  WIFI_PNO_RECENCY_SORTING_ENABLED_DEFAULT\n" +
                    "// Setting to enable including recency information when determining pno network priorities\n" +
                    "// 在决定PNO网络的优先级时 包含最新的最近的信息 去计算得分?\n" +
                    "boolean mPnoRecencySortingEnabled = false;  // 是否开启 PNO frequency culling optimization.  PNO 灭屏扫描频率优化\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "    pw.println(\"WifiConfigManager - Configured networks End ----\");\n" +
                    "    pw.println(\"WifiConfigManager - Next network ID to be allocated \" + mNextNetworkId);\n" +
                    "    pw.println(\"WifiConfigManager - Last selected network ID \" + mLastSelectedNetworkId);\n" +
                    "    pw.println(\"WifiConfigManager - PNO scan frequency culling enabled = \"    + mPnoFrequencyCullingEnabled);\n" +
                    "    pw.println(\"WifiConfigManager - PNO scan recency sorting enabled = \" + mPnoRecencySortingEnabled);\n" +
                    "    mWifiConfigStore.dump(fd, pw, args);\n" +
                    " }\n" +
                    "}";
            keyWordList.add(wifi_19_21);




            KeyWordItem wifi_19_22 = new KeyWordItem();
            wifi_19_22.keyword = "Dump of WifiConfigStore";
            wifi_19_22.explain=" 把 WifiConfiguration 写入 XML的 工作管理类  ";
            wifi_19_22.classNameForShuxing = "  class WifiConfigStore {}   ";
            wifi_19_22.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java) WifiConfigStore mWifiConfigStore\n ";
            wifi_19_22.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java)  dump(fd, pw, args){ mWifiConfigStore.dump() }; ";
            wifi_19_22.expain1="****************** StoreData Begin ******************\n" +
                    "public interface StoreData {\n" +
                    "\tvoid serializeData(XmlSerializer out);  // 把 xml 转为 输出流? Serialize a XML data block to the output stream. \n" +
                    "\tvoid deserializeData(@Nullable XmlPullParser in, int outerTagDepth); // 把 input流 转为  xml数据\n" +
                    "\tvoid resetData(); // Reset configuration data.\n" +
                    "\tboolean hasNewDataToSerialize();  // xml输出流 是否还有新的数据\n" +
                    "\tString getName();  // 代表所在存储文件中的一个 Tag标签 根Tag是  <WifiConfigStoreData> 以及该tag标签下的所有数据\t存储单元的名称? name of this store data  The data will be enclosed under this tag in the XML block.\n" +
                    "\t// This should be one of {@link # 0 STORE_FILE_SHARED_GENERAL},  {@link # 1 STORE_FILE_USER_GENERAL} {@link # 2 STORE_FILE_USER_NETWORK_SUGGESTIONS}.\n" +
                    "\tint getStoreFileId(); // 代表所在数据存储的文件位置  File Id where this data needs to be written to \n" +
                    "}\n" +
                    "\n" +
                    "具体的实现类:\n" +
                    "class WakeupConfigStoreData implements StoreData {}  getName()=\"WakeupConfigStoreData\" getStoreFileId()=1-STORE_FILE_USER_GENERAL-\"WifiConfigStore.xml\"\n" +
                    "WifiConfigStore.xml 中具体信息\n" +
                    "<WifiConfigStoreData>\n" +
                    "<WakeupConfigStoreData>\n" +
                    "<FeatureState>\n" +
                    "<boolean name=\"IsActive\" value=\"false\" />\n" +
                    "<boolean name=\"IsOnboarded\" value=\"false\" />\n" +
                    "<int name=\"NotificationsShown\" value=\"1\" />\n" +
                    "</FeatureState>\n" +
                    "</WakeupConfigStoreData>\n" +
                    "</WifiConfigStoreData>\n" +
                    "\n" +
                    "-----------------------------------------------------------------------------\n" +
                    "\n" +
                    "class DeletedEphemeralSsidsStoreData implements WifiConfigStore.StoreData {} getName()=\" DeletedEphemeralSSIDList \" getStoreFileId()=1-\"WifiConfigStore.xml\"\n" +
                    "WifiConfigStore.xml 中具体信息\n" +
                    "<WifiConfigStoreData>\n" +
                    "<DeletedEphemeralSSIDList>\n" +
                    "<set name=\"SSIDList\" />\n" +
                    "</DeletedEphemeralSSIDList>\n" +
                    "</WifiConfigStoreData>\n" +
                    "\n" +
                    "-----------------------------------------------------------------------------\n" +
                    "class SsidSetStoreData implements WifiConfigStore.StoreData {} getName()=\" ConfigData \" getStoreFileId()=1-\"WifiConfigStore.xml\"\n" +
                    "WifiConfigStore.xml 中具体信息\n" +
                    "<WifiConfigStoreData>\n" +
                    "<ConfigData>\n" +
                    "<SSIDSet>\n" +
                    "....\n" +
                    "</SSIDSet>\n" +
                    "</ConfigData>\n" +
                    "</WifiConfigStoreData>\n" +
                    "\n" +
                    "\n" +
                    "-----------------------------------------------------------------------------\n" +
                    "RandomizedMacStoreData implements WifiConfigStore.StoreData {} getName()=\"  MacAddressMap\" getStoreFileId()=0-\"WifiConfigStore.xml\"\n" +
                    "WifiConfigStore.xml 中具体信息\n" +
                    "<WifiConfigStoreData>\n" +
                    "<MacAddressMap>\n" +
                    "<map name=\"MacMapEntry\">\n" +
                    "<string name=\"&quot;D-Link_DIR-816&quot;NONE\">fe:ba:52:91:dc:da</string>\n" +
                    "<string name=\"&quot;D-Link_DIR-816&quot;OWE\">2a:35:a3:41:d9:b6</string>\n" +
                    "<string name=\"&quot;D-Link_DIR-816&quot;WPA_PSK\">f6:da:9d:a4:82:9e</string>\n" +
                    "<string name=\"&quot;D-Link_DIR-816&quot;WEP\">da:cc:72:f1:96:3d</string>\n" +
                    "<string name=\"&quot;lenovo-guest&quot;NONE\">5e:bb:9b:a9:6a:56</string>\n" +
                    "<string name=\"&quot;D-Link_DIR-816_5G&quot;WPA_PSK\">be:d7:1d:84:94:7a</string>\n" +
                    "</map>\n" +
                    "</MacAddressMap>\n" +
                    "</WifiConfigStoreData>\n" +
                    "-----------------------------------------------------------------------------\n" +
                    "保存经批准的所有wifi网络\n" +
                    "class NetworkRequestStoreData implements WifiConfigStore.StoreData {} getName()=\"  NetworkRequestMap \" getStoreFileId()=1-\"WifiConfigStore.xml\"\n" +
                    "WifiConfigStore.xml 中具体信息\n" +
                    "<WifiConfigStoreData>\n" +
                    "<NetworkRequestMap>\n" +
                    "<AccessPoint> </AccessPoint>\n" +
                    "<NetworkType> </NetworkType>\n" +
                    "</NetworkRequestMap>\n" +
                    "</WifiConfigStoreData>\n" +
                    "\n" +
                    "-----------------------------------------------------------------------------\n" +
                    " class SsidSetStoreData implements WifiConfigStore.StoreData {} getName()=  【Name+ \"ConfigData\" 】 getStoreFileId()=1-\"WifiConfigStore.xml\"\n" +
                    " 【\"ConfigData\" 】 包含 SsidSetStoreData 的子类\n" +
                    " 【Name = CarrierNetworkNotifierBlacklist 】   【Name = OpenNetworkNotifierBlacklist 】  \n" +
                    " <CarrierNetworkNotifierBlacklistConfigData />   <OpenNetworkNotifierBlacklistConfigData />\n" +
                    " \n" +
                    " -----------------------------------------------------------------------------\n" +
                    " \n" +
                    "  <PasspointConfigData>  \n" +
                    " -----------------------------------------------------------------------------\n" +
                    " class PasspointConfigSharedStoreData implements WifiConfigStore.StoreData {} getName()= \"PasspointConfigData\"  getStoreFileId()=1-\"WifiConfigStore.xml\"\n" +
                    "<WifiConfigStoreData>\n" +
                    " <PasspointConfigData>\n" +
                    "<ProviderList>\n" +
                    "<Provider>\n" +
                    "<long name=\"ProviderID\" value=\"2\" />\n" +
                    "<int name=\"CreatorUID\" value=\"10009\" />\n" +
                    "<null name=\"CaCertificateAlias\" />\n" +
                    "<null name=\"ClientCertificateAlias\" />\n" +
                    "<null name=\"ClientPrivateKeyAlias\" />\n" +
                    "<boolean name=\"HasEverConnected\" value=\"false\" />\n" +
                    "<Configuration>\n" +
                    "<int name=\"UpdateIdentifier\" value=\"-2147483648\" />\n" +
                    "<int name=\"CredentialPriority\" value=\"-2147483648\" />\n" +
                    "<null name=\"TrustRootCertList\" />\n" +
                    "<long name=\"SubscriptionCreationTime\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"SubscriptionExpirationTime\" value=\"-9223372036854775808\" />\n" +
                    "<null name=\"SubscriptionType\" />\n" +
                    "<long name=\"UsageLimitTimePeriod\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"UsageLimitStartTime\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"UsageLimitDataLimit\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"UsageLimitTimeLimit\" value=\"-9223372036854775808\" />\n" +
                    "<boolean name=\"mncmcc_hs20_network\" value=\"true\" />\n" +
                    "<int name=\"sim_slot\" value=\"2\" />\n" +
                    "<boolean name=\"preloaded_ssid\" value=\"false\" />\n" +
                    "<boolean name=\"preloaded_forget\" value=\"false\" />\n" +
                    "<HomeSP>\n" +
                    "<string name=\"FQDN\">001010FQDN-AKA.com</string>\n" +
                    "<string name=\"FriendlyName\">CESAR_sim2</string>\n" +
                    "<null name=\"IconURL\" />\n" +
                    "<null name=\"HomeNetworkIDs\" />\n" +
                    "<null name=\"MatchAllOIs\" />\n" +
                    "<null name=\"MatchAnyOIs\" />\n" +
                    "<null name=\"OtherHomePartners\" />\n" +
                    "<null name=\"RoamingConsortiumOIs\" />\n" +
                    "</HomeSP>\n" +
                    "<Credential>\n" +
                    "<long name=\"CreationTime\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"ExpirationTime\" value=\"-9223372036854775808\" />\n" +
                    "<string name=\"Realm\">wlan.mnc010.mcc001.3gppnetwork.org</string>\n" +
                    "<boolean name=\"CheckAAAServerCertStatus\" value=\"false\" />\n" +
                    "<SimCredential>\n" +
                    "<string name=\"IMSI\">001010100000010</string>\n" +
                    "<int name=\"EAPType\" value=\"23\" />\n" +
                    "</SimCredential>\n" +
                    "</Credential>\n" +
                    "</Configuration>\n" +
                    "</Provider>\n" +
                    "<Provider>\n" +
                    "<long name=\"ProviderID\" value=\"3\" />\n" +
                    "<int name=\"CreatorUID\" value=\"10009\" />\n" +
                    "<null name=\"CaCertificateAlias\" />\n" +
                    "<null name=\"ClientCertificateAlias\" />\n" +
                    "<null name=\"ClientPrivateKeyAlias\" />\n" +
                    "<boolean name=\"HasEverConnected\" value=\"false\" />\n" +
                    "<Configuration>\n" +
                    "<int name=\"UpdateIdentifier\" value=\"-2147483648\" />\n" +
                    "<int name=\"CredentialPriority\" value=\"-2147483648\" />\n" +
                    "<null name=\"TrustRootCertList\" />\n" +
                    "<long name=\"SubscriptionCreationTime\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"SubscriptionExpirationTime\" value=\"-9223372036854775808\" />\n" +
                    "<null name=\"SubscriptionType\" />\n" +
                    "<long name=\"UsageLimitTimePeriod\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"UsageLimitStartTime\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"UsageLimitDataLimit\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"UsageLimitTimeLimit\" value=\"-9223372036854775808\" />\n" +
                    "<boolean name=\"mncmcc_hs20_network\" value=\"true\" />\n" +
                    "<int name=\"sim_slot\" value=\"2\" />\n" +
                    "<boolean name=\"preloaded_ssid\" value=\"false\" />\n" +
                    "<boolean name=\"preloaded_forget\" value=\"false\" />\n" +
                    "<HomeSP>\n" +
                    "<string name=\"FQDN\">001010FQDN-SIM.com</string>\n" +
                    "<string name=\"FriendlyName\">CESAR_sim2</string>\n" +
                    "<null name=\"IconURL\" />\n" +
                    "<null name=\"HomeNetworkIDs\" />\n" +
                    "<null name=\"MatchAllOIs\" />\n" +
                    "<null name=\"MatchAnyOIs\" />\n" +
                    "<null name=\"OtherHomePartners\" />\n" +
                    "<null name=\"RoamingConsortiumOIs\" />\n" +
                    "</HomeSP>\n" +
                    "<Credential>\n" +
                    "<long name=\"CreationTime\" value=\"-9223372036854775808\" />\n" +
                    "<long name=\"ExpirationTime\" value=\"-9223372036854775808\" />\n" +
                    "<string name=\"Realm\">wlan.mnc010.mcc001.3gppnetwork.org</string>\n" +
                    "<boolean name=\"CheckAAAServerCertStatus\" value=\"false\" />\n" +
                    "<SimCredential>\n" +
                    "<string name=\"IMSI\">001010100000010</string>\n" +
                    "<int name=\"EAPType\" value=\"18\" />\n" +
                    "</SimCredential>\n" +
                    "</Credential>\n" +
                    "</Configuration>\n" +
                    "</Provider>\n" +
                    "</ProviderList>\n" +
                    "</PasspointConfigData>\n" +
                    "</WifiConfigStoreData>\n" +
                    "-----------------------------------------------------------------------------\n" +
                    "class NetworkSuggestionStoreData implements WifiConfigStore.StoreData {}  getName()= \" NetworkSuggestionMap \" getStoreFileId()=1-\"WifiConfigStore.xml\"  \n" +
                    "\n" +
                    "WifiConfigStore.xml 中具体信息\n" +
                    "<WifiConfigStoreData>\n" +
                    "<NetworkSuggestionMap>\n" +
                    "\n" +
                    "<NetworkSuggestion>\n" +
                    "<WifiConfiguration> </WifiConfiguration>\n" +
                    "</NetworkSuggestion>\n" +
                    "\n" +
                    "</NetworkSuggestionMap>\n" +
                    "</WifiConfigStoreData>\n" +
                    "\n" +
                    "-----------------------------------------------------------------------------\n" +
                    "NetworkListStoreData 尼玛实现了接口 却是个抽象类 没有实现field    主要的数据在 WifiConfigStore.xml\n" +
                    "abstract class NetworkListStoreData implements WifiConfigStore.StoreData { } getName()= \" NetworkList \" getStoreFileId()=1-\"WifiConfigStore.xml\"  \n" +
                    "<WifiConfigStoreData>\n" +
                    "<NetworkList>\n" +
                    "<Network>\n" +
                    "<WifiConfiguration> \n" +
                    "<WifiEnterpriseConfiguration> </WifiEnterpriseConfiguration> \n" +
                    "<IpConfiguration> </IpConfiguration>  \n" +
                    " </WifiConfiguration>\n" +
                    "<NetworkStatus></NetworkStatus>\n" +
                    "<Network>\n" +
                    "</NetworkList>\n" +
                    "</WifiConfigStoreData>\n" +
                    "\n" +
                    "NetworkListStoreData 的实现类 \n" +
                    "class NetworkListSharedStoreData extends NetworkListStoreData {} getName()= \" NetworkList \"  getStoreFileId()=0-\"WifiConfigStore.xml\"  \n" +
                    "class NetworkListUserStoreData extends NetworkListStoreData {} getName()= \" NetworkList \"  getStoreFileId()=1-\"WifiConfigStore.xml\"  \n" +
                    "\n" +
                    "****************** StoreData End ******************\n" +
                    "\n" +
                    "class WifiConfigStore {\n" +
                    "\n" +
                    "List<StoreData> mStoreDataList;  // StoreData 的存储数据的集合  是一个接口的实现类? nima \n" +
                    "\n" +
                    "// FieldId 和 存储文件的映射集合  索引-文件名 Mapping of Store file Id to Store file names.\n" +
                    "private static final SparseArray<String> STORE_ID_TO_FILE_NAME =new SparseArray<String>() {{\n" +
                    "\t\tput(STORE_FILE_SHARED_GENERAL, STORE_FILE_NAME_SHARED_GENERAL);\n" +
                    "\t\tput(STORE_FILE_USER_GENERAL, STORE_FILE_NAME_USER_GENERAL);\n" +
                    "\t\tput(STORE_FILE_USER_NETWORK_SUGGESTIONS, STORE_FILE_NAME_USER_NETWORK_SUGGESTIONS);\n" +
                    "\t}};\n" +
                    "\n" +
                    "final int STORE_FILE_SHARED_GENERAL = 0;  // 通用存储类型\n" +
                    "String STORE_FILE_NAME_SHARED_GENERAL = \"WifiConfigStore.xml\";\n" +
                    "\n" +
                    "final int STORE_FILE_USER_GENERAL = 1; // 用户存储的文件  user store file\n" +
                    "final String STORE_FILE_NAME_USER_GENERAL = \"WifiConfigStore.xml\";\n" +
                    "\n" +
                    "final int STORE_FILE_USER_NETWORK_SUGGESTIONS = 2;  // 网络推荐network_suggest 存储的文件\n" +
                    "final String STORE_FILE_NAME_USER_NETWORK_SUGGESTIONS =\"WifiConfigStoreNetworkSuggestions.xml\";\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "    pw.println(\"Dump of WifiConfigStore\");\n" +
                    "    pw.println(\"WifiConfigStore - Store Data Begin ----\");\n" +
                    "    for (StoreData storeData : mStoreDataList) {\n" +
                    "\t//NetworkList---PasspointConfigData 都是 WifiConfigStoreData.xml WifiConfigStoreNetworkSuggestions.xml \n" +
                    "\t//下根tag<WifiConfigStoreData>  下的二级Tag  <NetworkList> <MacAddressMap> 实现了 接口 WifiConfigStore.StoreData\n" +
                    "         pw.print(\"StoreData =>\");                  NetworkRequestMap  WakeupConfigStoreData \n" +
                    "         pw.print(\" \");                           // OpenNetworkNotifierBlacklistConfigData CarrierNetworkNotifierBlacklistConfigData\n" +
                    "         pw.print(\"Name: \" + storeData.getName()); //  NetworkList  DeletedEphemeralSSIDList MacAddressMap  PasspointConfigData\n" +
                    "         pw.print(\", \");\n" +
                    "         pw.print(\"File Id: \" + storeData.getStoreFileId()); //数据保存文件的索引 0.WifiConfigStore.xml   1.WifiConfigStore.xml  2.WifiConfigStoreNetworkSuggestions.xml\n" +
                    "         pw.print(\", \");\n" +
                    "         pw.println(\"File Name: \" + STORE_ID_TO_FILE_NAME.get(storeData.getStoreFileId())); // 数据保存文件名称\n" +
                    "    }\n" +
                    "    pw.println(\"WifiConfigStore - Store Data End ----\");\n" +
                    "}";
            keyWordList.add(wifi_19_22);



            KeyWordItem wifi_19_23 = new KeyWordItem();
            wifi_19_23.keyword = "Dump of PasspointManager";
            wifi_19_23.explain=" 打印 PasspointManager 相关信息";
            wifi_19_23.shuxingDefine = "public class PasspointManager { } ";
            wifi_19_23.classNameForShuxing = "(WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java ) dump(){mPasspointManager.dump(pw);;}\n";
            wifi_19_23.printcode=" \"(WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java ) dump(){mPasspointManager.dump(pw);";
            wifi_19_23.expain1="**********************************  PasspointManager.CarrierInfo Begin ********************************** \n" +
                    "public class CarrierInfo {  // 依赖sim卡的mncmcc获取的信息 class to get current carrier based on SIM's MNC/MCC\n" +
                    "    private final String TAG = \"WifiCarrierInfo\";\n" +
                    "    // list carrier names here  运营商集合\n" +
                    "    private static final String mTMOCarrier = \"T-Mobile\"; \n" +
                    "    private static final String mSprintCarrier = \"Sprint\";\n" +
                    "    private static final String mMetroPCSCarrier = \"MetroPcs\";\n" +
                    "    private static final String mATTCarrier = \"AT&T\";\n" +
                    "\t\n" +
                    "\t// 当前的运营商\n" +
                    "    private String mCurrCarrier = null;\n" +
                    "    private TelephonyManager mTelephonyManager;\n" +
                    "    private PhoneStateListener mPhoneStateListener;  // 监听sim卡状态\n" +
                    "\t}\n" +
                    "\t\n" +
                    "**********************************  PasspointManager.CarrierInfo End ********************************** \n" +
                    "\n" +
                    "**********************************  PasspointManager.AnqpCache Begin  另有分析 AnqpCache ********************************** \n" +
                    "// Cache for storing ANQP data  缓存ANQP的 数据  这只是一个缓存  其他进行ANQP的操作在其他地方进行\n" +
                    "public class AnqpCache {\n" +
                    "\n" +
                    "final long CACHE_SWEEP_INTERVAL_MILLISECONDS = 60000L;   // 60秒间隔\n" +
                    "long mLastSweep;\n" +
                    "Clock mClock;\n" +
                    "Map<ANQPNetworkKey, ANQPData> mANQPCache;  // 缓存的地址 \n" +
                    "\n" +
                    "    public void dump(PrintWriter out) {  // Last sweep 0:00:31.620 ago. \n" +
                    "        out.println(\"Last sweep \" + Utils.toHMS(mClock.getElapsedSinceBootMillis() - mLastSweep) + \" ago.\");\n" +
                    "        for (Map.Entry<ANQPNetworkKey, ANQPData> entry : mANQPCache.entrySet()) {\n" +
                    "            out.println(entry.getKey() + \": \" + entry.getValue());\n" +
                    "        }\n" +
                    "\t\t\n" +
                    "\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "**********************************  PasspointManager.AnqpCache 另有分析 AnqpCache End ********************************** \n" +
                    "\n" +
                    "**********************************  PasspointConfiguration Begin ********************************** \n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~  UpdateParameter Begin ~~~~~~~~~~~~~~~~\n" +
                    "final class UpdateParameter implements Parcelable { \n" +
                    "long mUpdateIntervalInMinutes = Long.MIN_VALUE;  // UpdateInterval 到时间后会进行更新Police?    指定设备与 police server 策略服务器交互一次的时间间隔\n" +
                    "\n" +
                    "String mUpdateMethod = null;  // UpdateMethod 用于更新策略的方法   \"OMA-DM-ClientInitiated\" 和  \"SPP-ClientInitiated\" 之一  The method used to update the policy.\n" +
                    "String mRestriction = null;  // 当前连接的网络类型? 当前约束的网络类型  Restriction  允许的值 \"HomeSP\", \"RoamingPartner\"  \"Unrestricted\"  三种 This specifies the hotspots at which the subscription update is permitted.\n" +
                    "final String UPDATE_RESTRICTION_HOMESP = \"HomeSP\";\n" +
                    "final String UPDATE_RESTRICTION_ROAMING_PARTNER = \"RoamingPartner\";\n" +
                    "final String UPDATE_RESTRICTION_UNRESTRICTED = \"Unrestricted\";\n" +
                    " String mServerUri = null;   //  Username  当前需要访问的更新服务器的地址  The URI of the update server.\n" +
                    " String mTrustRootCertUrl = null;  //  TrustRootCertURL   用于验证 更新服务器的合法性的一个地址 is used to validate  policy server's identity.\n" +
                    " \n" +
                    "public String toString() {\n" +
                    "\tStringBuilder builder = new StringBuilder();\n" +
                    "\tbuilder.append(\"UpdateInterval: \").append(mUpdateIntervalInMinutes).append(\"\\n\");\n" +
                    "\tbuilder.append(\"UpdateMethod: \").append(mUpdateMethod).append(\"\\n\");\n" +
                    "\tbuilder.append(\"Restriction: \").append(mRestriction).append(\"\\n\");\n" +
                    "\tbuilder.append(\"ServerURI: \").append(mServerUri).append(\"\\n\");\n" +
                    "\tbuilder.append(\"Username: \").append(mUsername).append(\"\\n\");\n" +
                    "\tbuilder.append(\"TrustRootCertURL: \").append(mTrustRootCertUrl).append(\"\\n\");\n" +
                    "\treturn builder.toString();\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~  UpdateParameter End ~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~  Policy Begin ~~~~~~~~~~~~~~~~\n" +
                    " final class Policy implements Parcelable {\n" +
                    " long mMinHomeDownlinkBandwidth = Long.MIN_VALUE;  // MinHomeDownlinkBandwidth  最小的下载带宽  单位 kb/s 对于 当前网络\n" +
                    " long mMinHomeUplinkBandwidth = Long.MIN_VALUE;  // MinHomeUplinkBandwidth  最小的上传带宽  单位 kb/s 对于 当前网络\n" +
                    " long mMinRoamingDownlinkBandwidth = Long.MIN_VALUE; // MinRoamingDownlinkBandwidth 最小的下载带宽  单位 kb/s 对于需要漫游到的passpoint网络\n" +
                    " long mMinRoamingUplinkBandwidth = Long.MIN_VALUE;  //  MinRoamingUplinkBandwidth 最小的上传带宽  单位 kb/s 对于需要漫游到的passpoint网络\n" +
                    " String[] mExcludedSsidList = null; // 不推荐的HsmeSP SSID网络   List of SSIDs that are not preferred by the Home SP.\n" +
                    " Map<Integer, String> mRequiredProtoPortMap = null;  // 协议-端口号(由,分割) 的 MAP  协议由int标识 验证端口需要的协议  Validate required protocol to port map.\n" +
                    " int mMaximumBssLoadValue = Integer.MIN_VALUE;  // 指定了当前设备加入的passpoint最大的客户端数量  如果数量大于这个阈值就不加入这个passpoint网络\n" +
                    " List<RoamingPartner> mPreferredRoamingPartnerList = null;   //  合作伙伴信息列表 \n" +
                    " UpdateParameter mPolicyUpdate = null;  //  Meta data used for policy update.\n" +
                    " \n" +
                    " \n" +
                    "    public String toString() {\n" +
                    "        StringBuilder builder = new StringBuilder();\n" +
                    "        builder.append(\"MinHomeDownlinkBandwidth: \").append(mMinHomeDownlinkBandwidth);\n" +
                    "        builder.append(\"MinHomeUplinkBandwidth: \").append(mMinHomeUplinkBandwidth).append(\"\\n\");\n" +
                    "        builder.append(\"MinRoamingDownlinkBandwidth: \").append(mMinRoamingDownlinkBandwidth);\n" +
                    "        builder.append(\"MinRoamingUplinkBandwidth: \").append(mMinRoamingUplinkBandwidth);\n" +
                    "        builder.append(\"ExcludedSSIDList: \").append(mExcludedSsidList).append(\"\\n\");\n" +
                    "        builder.append(\"RequiredProtoPortMap: \").append(mRequiredProtoPortMap).append(\"\\n\");\n" +
                    "        builder.append(\"MaximumBSSLoadValue: \").append(mMaximumBssLoadValue).append(\"\\n\");\n" +
                    "        builder.append(\"PreferredRoamingPartnerList: \").append(mPreferredRoamingPartnerList);\n" +
                    "        if (mPolicyUpdate != null) {\n" +
                    "            builder.append(\"PolicyUpdate Begin ---\\n\");\n" +
                    "            builder.append(mPolicyUpdate);\n" +
                    "            builder.append(\"PolicyUpdate End ---\\n\");\n" +
                    "        }\n" +
                    "        return builder.toString();\n" +
                    "    }\n" +
                    "\t}\n" +
                    "\t\n" +
                    "~~~~~~~~~~~~~~~~  Policy.RoamingPartner Begin ~~~~~~~~~~~~~~~~\n" +
                    "final class Policy.RoamingPartner implements Parcelable {\n" +
                    "String mFqdn = null;  // 合作伙伴的 FQDN  例如: 【<string name=\"FQDN\">001010FQDN-AKA.com</string>】 【<string name=\"FQDN\">001010FQDN-SIM.com</string>】\n" +
                    "// ExactMatch  指示是否使用更精确的匹配  如果这个值为false(不精确)   \"example.com\"  和 \"host.example.com\" 匹配\n" +
                    "//  如果这个值为 true(精确)  \"example.com\"  和 \"host.example.com\" 不匹配  ， \"example.com\" 和 \"example.com.xx\" 匹配\n" +
                    "boolean mFqdnExactMatch = false;   // ExactMatch\n" +
                    "int mPriority = Integer.MIN_VALUE;  //  Priority 和 合作伙伴关联网络的优先级  Priority associated with this roaming partner policy\n" +
                    "String mCountries = null;  // Countries  以逗号分分割的国家字符串编码  ISO/IEC 3166-1  两个字符国家码,两个字符国家码\n" +
                    " \n" +
                    " \n" +
                    "public String toString() {\n" +
                    "StringBuilder builder = new StringBuilder();\n" +
                    "builder.append(\"FQDN: \").append(mFqdn).append(\"\\n\");\n" +
                    "builder.append(\"ExactMatch: \").append(\"mFqdnExactMatch\").append(\"\\n\");\n" +
                    "builder.append(\"Priority: \").append(mPriority).append(\"\\n\");\n" +
                    "builder.append(\"Countries: \").append(mCountries).append(\"\\n\");\n" +
                    "return builder.toString();\n" +
                    "}\n" +
                    "\t\t\n" +
                    " ~~~~~~~~~~~~~~~~  Policy.RoamingPartner End ~~~~~~~~~~~~~~~~\n" +
                    " \n" +
                    " \n" +
                    "~~~~~~~~~~~~~~~~  Policy End ~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ EAP Method Begin ~~~~~~~~~~~~~~~~\n" +
                    "// EAP认证的方式 methodType  https://www.iana.org/assignments/eap-numbers/eap-numbers.xml#eap-numbers-4\n" +
                    "0\tReserved\t\n" +
                    "1\tIdentity\t[RFC3748]\n" +
                    "2\tNotification\t[RFC3748]\n" +
                    "3\tLegacy Nak\t[RFC3748]\n" +
                    "4\tMD5-Challenge\t[RFC3748]\n" +
                    "5\tOne-Time Password (OTP)\t[RFC3748]\n" +
                    "6\tGeneric Token Card (GTC)\t[RFC3748]\n" +
                    "7\tAllocated\t[RFC3748]\n" +
                    "8\tAllocated\t[RFC3748]\n" +
                    "9\tRSA Public Key Authentication\t[William_Whelan]\n" +
                    "10\tDSS Unilateral\t[William_Nace]\n" +
                    "11\tKEA\t[William_Nace]\n" +
                    "12\tKEA-VALIDATE\t[William_Nace]\n" +
                    "13\tEAP-TLS\t[RFC5216]\n" +
                    "14\tDefender Token (AXENT)\t[Michael_Rosselli]\n" +
                    "15\tRSA Security SecurID EAP\t[Magnus_Nystrom]\n" +
                    "16\tArcot Systems EAP\t[Rob_Jerdonek]\n" +
                    "17\tEAP-Cisco Wireless\t[Stuart_Norman]\n" +
                    "18\tGSM Subscriber Identity Modules (EAP-SIM)\t[RFC4186]\n" +
                    "19\tSRP-SHA1\t[James_Carlson]\n" +
                    "20\tUnassigned\t\n" +
                    "21\tEAP-TTLS\t[RFC5281]\n" +
                    "22\tRemote Access Service\t[Steven_Fields]\n" +
                    "23\tEAP-AKA Authentication\t[RFC4187]\n" +
                    "24\tEAP-3Com Wireless\t[Albert_Young]\n" +
                    "25\tPEAP\t[Ashwin_Palekar]\n" +
                    "26\tMS-EAP-Authentication\t[Ashwin_Palekar]\n" +
                    "27\tMutual Authentication w/Key Exchange (MAKE)\t[Romain_Berrendonner]\n" +
                    "28\tCRYPTOCard\t[Stephen_M_Webb]\n" +
                    "29\tEAP-MSCHAP-V2\t[Darran_Potter]\n" +
                    "30\tDynamID\t[Pascal_Merlin]\n" +
                    "31\tRob EAP\t[Sana_Ullah]\n" +
                    "32\tProtected One-Time Password\t[RFC4793][Magnus_Nystrom]\n" +
                    "33\tMS-Authentication-TLV\t[Ashwin_Palekar]\n" +
                    "34\tSentriNET\t[Joe_Kelleher]\n" +
                    "35\tEAP-Actiontec Wireless\t[Victor_Chang]\n" +
                    "36\tCogent Systems Biometrics Authentication EAP\t[John_Xiong]\n" +
                    "37\tAirFortress EAP\t[Richard_Hibbard]\n" +
                    "38\tEAP-HTTP Digest\t[Oliver_K_Tavakoli]\n" +
                    "39\tSecureSuite EAP\t[Matt_Clements]\n" +
                    "40\tDeviceConnect EAP\t[David_Pitard]\n" +
                    "41\tEAP-SPEKE\t[Don_Zick]\n" +
                    "42\tEAP-MOBAC\t[Tom_Rixom]\n" +
                    "43\tEAP-FAST\t[RFC4851]\n" +
                    "44\tZoneLabs EAP (ZLXEAP)\t[Darrin_Bogue]\n" +
                    "45\tEAP-Link\t[Don_Zick]\n" +
                    "46\tEAP-PAX\t[T_Charles_Clancy]\n" +
                    "47\tEAP-PSK\t[RFC4764]\n" +
                    "48\tEAP-SAKE\t[RFC4763]\n" +
                    "49\tEAP-IKEv2\t[RFC5106]\n" +
                    "50\tEAP-AKA'\t[RFC5448]\n" +
                    "51\tEAP-GPSK\t[RFC5433]\n" +
                    "52\tEAP-pwd\t[RFC5931]\n" +
                    "53\tEAP-EKE Version 1\t[RFC6124]\n" +
                    "54\tEAP Method Type for PT-EAP\t[RFC7171]\n" +
                    "55\tTEAP\t[RFC7170]\n" +
                    "56-191\tUnassigned\t\n" +
                    "192-253\tUnassigned\t\n" +
                    "254\tReserved for the Expanded Type\t[RFC3748]\n" +
                    "255\tExperimental\t[RFC3748]\n" +
                    "256-4294967295\tUnassigned\t\n" +
                    "~~~~~~~~~~~~~~~~ EAP Method End ~~~~~~~~~~~~~~~~\n" +
                    "~~~~~~~~~~~~~~~~ Credential Begin ~~~~~~~~~~~~~~~~\n" +
                    "final class Credential implements Parcelable {\n" +
                    " String mUsername = null;  // Username 凭证的用户名    Username of the credential.\n" +
                    " boolean mMachineManaged = false;  // MachineManaged 当前密码是否由机器保存的标识位   Flag indicating if the password is machine managed.\n" +
                    " String mSoftTokenApp = null;  // SoftTokenApp 用于生成密码的应用程序名称  The name of the application used to generate the password.\n" +
                    " boolean mAbleToShare = false; //  AbleToShare 指示该凭证转移到其他机器是否可用 Flag indicating if this credential is usable on other mobile devices as well.\n" +
                    "int mEapType = Integer.MIN_VALUE; // EAPType EAP认证方式  【 EAP Method 】 (Extensible Authentication Protocol) method type\n" +
                    "// 18\tGSM Subscriber Identity Modules (EAP-SIM)   // 47\tEAP-PSK\t[RFC4764]  // 50\tEAP-AKA'\t[RFC5448]\n" +
                    "// 23\tEAP-AKA Authentication\t[RFC4187]   21\tEAP-TTLS\t[RFC5281]\n" +
                    "\n" +
                    "// 使用非EAP方法 使用凭证去关联网络  Set the inner non-EAP method associated with this user credential. \n" +
                    " String mNonEapInnerMethod = null;    // AuthMethod  非EAP的内部认证方法  Non-EAP inner authentication method.\n" +
                    " \n" +
                    "public String toString() {\n" +
                    "\tStringBuilder builder = new StringBuilder();\n" +
                    "\tbuilder.append(\"Username: \").append(mUsername).append(\"\\n\");\n" +
                    "\tbuilder.append(\"MachineManaged: \").append(mMachineManaged).append(\"\\n\");\n" +
                    "\tbuilder.append(\"SoftTokenApp: \").append(mSoftTokenApp).append(\"\\n\");\n" +
                    "\tbuilder.append(\"AbleToShare: \").append(mAbleToShare).append(\"\\n\");\n" +
                    "\tbuilder.append(\"EAPType: \").append(mEapType).append(\"\\n\");\n" +
                    "\tbuilder.append(\"AuthMethod: \").append(mNonEapInnerMethod).append(\"\\n\");\n" +
                    "\treturn builder.toString();\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ Credential End ~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ HomeSp Begin ~~~~~~~~~~~~~~~~\n" +
                    "final class HomeSp implements Parcelable {\n" +
                    "String mFqdn = null;  // identify  FQDN 的名称  (Fully Qualified Domain Name) of this home service provider. 【001010FQDN-AKA.com】 \n" +
                    "String mFriendlyName = null;  // FriendlyName  运营商商标 显示的passpoint名称   【例如: CESAR_sim2 】\n" +
                    "String mIconUrl = null;  //  IconURL passpoint网络图标的url地址  会显示在状态栏 Icon URL of this home service provider. \n" +
                    "Map<String, Long> mHomeNetworkIds = null;  // HomeNetworkIDs 使用UTF8编码  HomeNetworkIDs  <SSID, HESSID>  SSID对应的MAC地址 \n" +
                    "long[] mMatchAllOis 【All】 = null;  //  MatchAllOIs 用来判断该passpoint网络是否来自(OIs) 成员  Used for determining if this provider is a member of a given Hotspot provider / Organization Identifiers (OIs) \n" +
                    "long[] mMatchAnyOis 【Any】 = null;  //MatchAnyOIs  当mMatchAllOis 【All】 为空才使用  备胎?  用来判断该passpoint网络是否来自(OIs) 成员\n" +
                    "String[] mOtherHomePartners = null;   // OtherHomePartners  列出当前FQDN的合作伙伴的 FQDN  List of FQDN (Fully Qualified Domain Name) of partner providers\n" +
                    " long[] mRoamingConsortiumOis = null;  //  标识可漫游的 (OIs) Organization Identifiers (OIs)  成员列表 \n" +
                    "public String toString() {\n" +
                    "\tStringBuilder builder = new StringBuilder();\n" +
                    "\tbuilder.append(\"FQDN: \").append(mFqdn).append(\"\\n\");\n" +
                    "\tbuilder.append(\"FriendlyName: \").append(mFriendlyName).append(\"\\n\");\n" +
                    "\tbuilder.append(\"IconURL: \").append(mIconUrl).append(\"\\n\");\n" +
                    "\tbuilder.append(\"HomeNetworkIDs: \").append(mHomeNetworkIds).append(\"\\n\");\n" +
                    "\tbuilder.append(\"MatchAllOIs: \").append(mMatchAllOis).append(\"\\n\");\n" +
                    "\tbuilder.append(\"MatchAnyOIs: \").append(mMatchAnyOis).append(\"\\n\");\n" +
                    "\tbuilder.append(\"OtherHomePartners: \").append(mOtherHomePartners).append(\"\\n\");\n" +
                    "\tbuilder.append(\"RoamingConsortiumOIs: \").append(mRoamingConsortiumOis).append(\"\\n\");\n" +
                    "\treturn builder.toString();\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~HomeSp End~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "final class PasspointConfiguration implements Parcelable {\n" +
                    "int mUpdateIdentifier = Integer.MIN_VALUE;  // UpdateIdentifier 由订阅服务器设置   subscription server. 每次设置都会更新 使用 Integer.MIN_VALUE 表明未设置 Use Integer.MIN_VALUE to indicate unset value \n" +
                    "int mCredentialPriority = Integer.MIN_VALUE; // CredentialPriority 凭据优先级   最小值标识未设置  The priority of the credential.\n" +
                    "long mSubscriptionCreationTimeInMillis = Long.MIN_VALUE;  // SubscriptionCreationTime 创建订阅的时间 【Not specified标识没有设置】  The time this subscription is created\n" +
                    "long mSubscriptionExpirationTimeInMillis = Long.MIN_VALUE; // SubscriptionExpirationTime 订阅过期的时间戳 【Not specified标识没有设置】  The time this subscription will expire\n" +
                    "long mUsageLimitStartTimeInMillis = Long.MIN_VALUE;  // UsageLimitStartTime 开始使用流量(可能付费)的时间 【Not specified标识没有设置】The time at which usage statistic accumulation  begins.\n" +
                    "\n" +
                    "long mUsageLimitUsageTimePeriodInMinutes = Long.MIN_VALUE;   //mUsageLimitUsageTimePeriodInMinutes 为Long.MIN_VALUE 标识未设置 为0时标识并不统计使用分钟  非0时标识用户可以最多使用多少流量在 1 分钟内\n" +
                    "long mUsageLimitDataLimit = Long.MIN_VALUE;  // mUsageLimitDataLimit  为0时标识用户上网不受最大流量限制  非0时 标识用户最多可上网 XXX MB 流量\n" +
                    "long mUsageLimitTimeLimitInMinutes = Long.MIN_VALUE;  // mUsageLimitTimeLimitInMinutes 为0时标识 当前用户上网没有时间限制  非0 标识用户还有 XXx 分钟上网时长\n" +
                    "\n" +
                    "\n" +
                    "HomeSp mHomeSp = null;  // HomeSP\n" +
                    "Credential mCredential = null;  //Credential ,  Configurations under Credential subtree.\n" +
                    "Policy mPolicy = null;  // Policy , Configurations under Policy subtree.\n" +
                    "UpdateParameter mSubscriptionUpdate = null;  //  SubscriptionUpdate 元数据  用于执行数据更新 Meta data for performing subscription update.\n" +
                    "Map<String, byte[]> mTrustRootCertList = null;  // TrustRootCertServers key 是Http地址  bytep[] 是访问 Http的凭证数据\n" +
                    "Map<String, String> mServiceFriendlyNames = null; //ServiceFriendlyNames  key是语言  value是当前语言下 OSU Online SigleUP 服务名称\n" +
                    "boolean mncmcc_hs20_network = false;   // 是否是 依据Mnc mcc 设置的 passpoint网络\n" +
                    "int sim_slot;   //  当前 读取 mncmcc的卡槽\n" +
                    "boolean preloaded_ssid; // 该psspoint 是否是预置的 网络\n" +
                    "boolean preloaded_forget;  // 如果是预置的网络 是否可以被用户删除  true-删除  false-不能删除\n" +
                    "\n" +
                    " //  查询 toString 方法 \n" +
                    "// https://www.androidos.net.cn/android/9.0.0_r8/xref/frameworks/base/wifi/java/android/net/wifi/hotspot2/PasspointConfiguration.java\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "**********************************  PasspointConfiguration Begin ********************************** \n" +
                    "\n" +
                    "**********************************  PasspointProvider Begin ********************************** \n" +
                    "public class PasspointProvider {\n" +
                    "final int mProviderId ;   // ProviderId  作为唯一标识符 从 0 开始  自增 \n" +
                    "final int mCreatorUid;   // CreatorUID   创建Config的UID\n" +
                    "final String mPackageName;  // PackageName   创建Config的包名 \n" +
                    "PasspointConfiguration mConfig;  //  Passpoint相关的Configuration \n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        StringBuilder builder = new StringBuilder();\n" +
                    "        builder.append(\"ProviderId: \").append(mProviderId).append(\"\\n\");\n" +
                    "        builder.append(\"CreatorUID: \").append(mCreatorUid).append(\"\\n\");\n" +
                    "        if (mPackageName != null) {\n" +
                    "            builder.append(\"PackageName: \").append(mPackageName).append(\"\\n\");\n" +
                    "        }\n" +
                    "        builder.append(\"Configuration Begin ---\\n\");\n" +
                    "\t\t// https://www.androidos.net.cn/android/9.0.0_r8/xref/frameworks/base/wifi/java/android/net/wifi/hotspot2/PasspointConfiguration.java\n" +
                    "        builder.append(mConfig);  //  PasspointConfiguration.toString()\n" +
                    "        builder.append(\"Configuration End ---\\n\");\n" +
                    "        return builder.toString();\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "**********************************  PasspointProvider End ********************************** \n" +
                    "\n" +
                    "public class PasspointManager {\n" +
                    "Map<String, PasspointProvider> mProviders;  // PasspointProvider 的 MAP\n" +
                    "long mProviderIndex;  // 下一个Passpoint分配到的 provider Id  Next provider ID Counter used for assigning unique identifier to each provider.\n" +
                    "AnqpCache mAnqpCache;   // 接入网络查询协议(ANQP  Access Network Query Protocol  ) 查询协议结果缓存\n" +
                    "CarrierInfo mCarrierInfo;   // 运营商信息\n" +
                    "\n" +
                    "    public void dump(PrintWriter pw) {\n" +
                    "        pw.println(\"Dump of PasspointManager\");\n" +
                    "        pw.println(\"PasspointManager - Providers Begin ---\");\n" +
                    "        for (Map.Entry<String, PasspointProvider> entry : mProviders.entrySet()) {\n" +
                    "\t\t// https://www.androidos.net.cn/android/9.0.0_r8/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointProvider.java\n" +
                    "            pw.println(entry.getValue());  // 输出 PasspointProvider.toString()\n" +
                    "        }\n" +
                    "        pw.println(\"PasspointManager - Providers End ---\");\n" +
                    "        pw.println(\"PasspointManager - Next provider ID to be assigned \" + mProviderIndex);\n" +
                    "        mAnqpCache.dump(pw);\n" +
                    "        pw.println(\"Current CarrierIno = \" +mCarrierInfo.getCurrentCarrier());\n" +
                    "    }\n";
            keyWordList.add(wifi_19_23);



            KeyWordItem wifi_19_24 = new KeyWordItem();
            wifi_19_24.keyword = "Last sweep";
            wifi_19_24.explain=" AnqpCache 用于存储Anqp的数据  WifiServiceImpl.ClientModeImpl.WifiConfigManager.PasspointManager.AnqpCache【Map<ANQPNetworkKey, ANQPData>】";
            wifi_19_24.classNameForShuxing = " 【PasspointManager.mAnqpCache  】class AnqpCache {} ";
            wifi_19_24.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java -> PasspointManager.java -> AnqpCache.java.dump )   ";
            wifi_19_24.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java -> PasspointManager.java -> )  dump(fd, pw, args){ mAnqpCache.dump() }; ";
            wifi_19_24.expain1="**********************************  ANQPElement Begin ********************************** \n" +
                    "ANQPElement 抽象类的所有子类如下  分别代表不同的 ANQP帧信息\n" +
                    "class GenericBlobElement extends ANQPElement {}  没有表示类型  仅仅携带数据  final byte[] mData;\n" +
                    "class RawByteElement extends ANQPElement {}   没有表示类型  仅仅携带原生所有数据  final byte[] mPayload;\n" +
                    "ANQPVenueName(1) = class VenueNameElement extends ANQPElement {}\n" +
                    "ANQPRoamingConsortium(2)  = class RoamingConsortiumElement extends ANQPElement {}\n" +
                    "ANQPIPAddrAvailability(3) = class IPAddressTypeAvailabilityElement extends ANQPElement {}\n" +
                    "ANQPNAIRealm(4) = class NAIRealmElement extends ANQPElement {}\n" +
                    "ANQP3GPPNetwork(5) = class ThreeGPPNetworkElement extends ANQPElement {}\n" +
                    "ANQPDomName(6) = class DomainNameElement extends ANQPElement {}\n" +
                    "HSFriendlyName(9) =  class HSFriendlyNameElement extends ANQPElement {}\n" +
                    "HSWANMetrics(10) = class HSWanMetricsElement extends ANQPElement {}\n" +
                    "HSConnCapability(11) = class HSConnectionCapabilityElement extends ANQPElement {}\n" +
                    "HSOSUProviders(13) = class HSOsuProvidersElement extends ANQPElement {}\n" +
                    "HSIconRequest(14) = class HSIconFileElement extends ANQPElement {}\n" +
                    "\n" +
                    "// 在 IEEE802.11u 中定义的 ANQP_element的基础抽象类    Base class for an IEEE802.11u ANQP element.\n" +
                    "abstract class ANQPElement {  // 抽象类  还没有抽象方法 shit\n" +
                    " final Constants.ANQPElementType mID;  // ANQP 类型\n" +
                    "     protected ANQPElement(Constants.ANQPElementType id) {\n" +
                    "        mID = id;\n" +
                    "    }\n" +
                    "    public Constants.ANQPElementType getID() {\n" +
                    "        return mID;\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "public class ANQPParser {  // 在 ANQPParser 中把 指定的数据流 字节数据 ByteBuffer 转为指定类型 ANQPElementType 对应的 数据ANQPElement\n" +
                    "ANQPElement parseVendorSpecificElement(ByteBuffer payload)\n" +
                    "ANQPElement parseElement(Constants.ANQPElementType infoID, ByteBuffer payload)\n" +
                    "ANQPElement parseHS20Element(Constants.ANQPElementType infoID,ByteBuffer payload)\t\t\n" +
                    "}\n" +
                    "\n" +
                    "    public enum ANQPElementType { \n" +
                    "\tANQPQueryList(index-0),  ANQPVenueName(index-1), ANQPRoamingConsortium(2),    ANQPIPAddrAvailability(3), ANQPNAIRealm(4),\n" +
                    "    ANQP3GPPNetwork(5),ANQPDomName(6), ANQPVendorSpec(7),  HSQueryList(8),  HSFriendlyName(9),  HSWANMetrics(10),\n" +
                    "    HSConnCapability(11), HSNAIHomeRealmQuery(12), HSOSUProviders(13), HSIconRequest(14), HSIconFile(15)\n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "class GenericBlobElement extends ANQPElement {}  没有表示类型  仅仅携带数据  final byte[] mData;\n" +
                    "class RawByteElement extends ANQPElement {}   没有表示类型  仅仅携带原生所有数据  final byte[] mPayload;\n" +
                    "ANQPVenueName(1) = class VenueNameElement extends ANQPElement {}\n" +
                    "ANQPRoamingConsortium(2)  = class RoamingConsortiumElement extends ANQPElement {}\n" +
                    "ANQPIPAddrAvailability(3) = class IPAddressTypeAvailabilityElement extends ANQPElement {}\n" +
                    "ANQPNAIRealm(4) = class NAIRealmElement extends ANQPElement {}\n" +
                    "ANQP3GPPNetwork(5) = class ThreeGPPNetworkElement extends ANQPElement {}\n" +
                    "ANQPDomName(6) = class DomainNameElement extends ANQPElement {}\n" +
                    "HSFriendlyName(9) =  class HSFriendlyNameElement extends ANQPElement {}\n" +
                    "HSWANMetrics(10) = class HSWanMetricsElement extends ANQPElement {}\n" +
                    "HSConnCapability(11) = class HSConnectionCapabilityElement extends ANQPElement {}\n" +
                    "HSOSUProviders(13) = class HSOsuProvidersElement extends ANQPElement {}\n" +
                    "HSIconRequest(14) = class HSIconFileElement extends ANQPElement {}\n" +
                    "\n" +
                    "\n" +
                    "**********************************  ANQPElement End ********************************** \n" +
                    "\n" +
                    "\n" +
                    "**********************************  ANQPData Begin ********************************** \n" +
                    "// Class for maintaining ANQP elements and managing the lifetime of the elements.\n" +
                    "// 用于维护ANQP数据的 元数据   基础数据\n" +
                    "public class ANQPData {\n" +
                    "\n" +
                    "    public static final long DATA_LIFETIME_MILLISECONDS = 3600000L;  // 数据的生命时间 1小时\n" +
                    "\n" +
                    "    private final Clock mClock;  // 时钟\n" +
                    "    private final Map<Constants.ANQPElementType 【枚举enum 】, ANQPElement> mANQPElements;\n" +
                    "    private final long mExpiryTime;  // 超时时间点 \n" +
                    "\t\n" +
                    "    public enum ANQPElementType { \n" +
                    "\tANQPQueryList(index-0),  ANQPVenueName(index-1), ANQPRoamingConsortium(2),    ANQPIPAddrAvailability(3), ANQPNAIRealm(4),\n" +
                    "    ANQP3GPPNetwork(5),ANQPDomName(6), ANQPVendorSpec(7),  HSQueryList(8),  HSFriendlyName(9),  HSWANMetrics(10),\n" +
                    "    HSConnCapability(11), HSNAIHomeRealmQuery(12), HSOSUProviders(13), HSIconRequest(14), HSIconFile(15)\n" +
                    "    }\n" +
                    "\t\n" +
                    "    public String toString() {\n" +
                    "        StringBuilder sb = new StringBuilder();\n" +
                    "        sb.append(mANQPElements.size()).append(\" elements, \");\n" +
                    "        long now = mClock.getElapsedSinceBootMillis();\n" +
                    "        sb.append(\" expires in \").append(Utils.toHMS(mExpiryTime - now)).append(' ');\n" +
                    "        sb.append(expired(now) ? 'x' : '-').append(\"\\n\");\n" +
                    "        for (Map.Entry<Constants.ANQPElementType, ANQPElement> entry: mANQPElements.entrySet()) {\n" +
                    "            sb.append(entry.getValue()).append(\"\\n\");\n" +
                    "        }\n" +
                    "        return sb.toString();\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "**********************************  ANQPData End ********************************** \n" +
                    "\t\n" +
                    "\t\n" +
                    "**********************************  ANQPNetworkKey Begin ********************************** \n" +
                    "// Unique key for identifying APs that will contain the same ANQP information.  用于标识相同ANPQ信息ANQPData的 唯一键值\n" +
                    "// APs in the same ESS ( SSID or HESSID) with the same ANQP domain ID will have the same ANQP\n" +
                    "// 具有相同的 SSID 或者 HESSID, 并且具有相同的 ANQP domain ID 【int mAnqpDomainID 】 那么这些AP热点的 ANPQ数据相同\n" +
                    "public class ANQPNetworkKey {\n" +
                    "    private final String mSSID;  //  wifi名称\n" +
                    "    private final long mBSSID;   //  wifi的bssid long类型  可转为 mac字符串 通过方法  Utils.macToString\n" +
                    "    private final long mHESSID;  //  代表一个 wifi集群 相同的局 具有相同 的 HESS  mHESSID 和Mac地址一样  标识在同一个集群内 hessid The HESSID of the AP\n" +
                    "    private final int mAnqpDomainID;   // ANQP domain ID   int 域名ID \n" +
                    "\t}\n" +
                    "\n" +
                    "\t    public String toString() {\n" +
                    "        if (mHESSID != 0L) {\n" +
                    "            return Utils.macToString(mHESSID) + \":\" + mAnqpDomainID;\n" +
                    "        } else if (mBSSID != 0L) {\n" +
                    "            return Utils.macToString(mBSSID) + \":<\" + mSSID + \">\";\n" +
                    "        } else {\n" +
                    "            return \"<\" + mSSID + \">:\" + mAnqpDomainID;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "**********************************  ANQPNetworkKey End ********************************** \n" +
                    "\n" +
                    "// Cache for storing ANQP data  缓存ANQP的 数据  这只是一个缓存  其他进行ANQP的操作在其他地方进行\n" +
                    "public class AnqpCache {\n" +
                    "\n" +
                    "final long CACHE_SWEEP_INTERVAL_MILLISECONDS = 60000L;   // 60秒间隔\n" +
                    "long mLastSweep;  // Last sweep :  记录最新的 返回系统启动到现在的毫秒数 对应的进行缓存超时删除操作的 手机启动时间 60秒访问一次map  map中的boolean 超时属性为 true 超时就删除\n" +
                    "Clock mClock;\n" +
                    "Map<ANQPNetworkKey, ANQPData> mANQPCache;  // 缓存的地址\n" +
                    " \n" +
                    "public void dump(PrintWriter out) {  \n" +
                    "// getElapsedSinceBootMillis = SystemClock.elapsedRealtime() 返回系统启动到现在的毫秒数，包含休眠时间 \n" +
                    "// toHMS 把long 时间差距转为  时分秒的字样  Last sweep 0:00:31.620 ago.   时00:分00:秒00:毫秒000\n" +
                    "        out.println(\"Last sweep \" + Utils.toHMS(mClock.getElapsedSinceBootMillis() - mLastSweep) + \" ago.\");\n" +
                    "        for (Map.Entry<ANQPNetworkKey, ANQPData> entry : mANQPCache.entrySet()) {\n" +
                    "            out.println(entry.getKey() + \": \" + entry.getValue());\n" +
                    "        }\n" +
                    "    }";


            keyWordList.add(wifi_19_24);




            KeyWordItem wifi_19_25 = new KeyWordItem();
            wifi_19_25.keyword = "Current CarrierIno ";
            wifi_19_25.explain=" PasspointManager 从SIM卡得到 mccmnc的类  ";
            wifi_19_25.classNameForShuxing = " public class CarrierInfo {} ";
            wifi_19_25.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java -> PasspointManager.java ) CarrierIno mCarrierIno\n ";
            wifi_19_25.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java -> PasspointManager.java  )  dump(fd, pw, args){ mCarrierIno.toString() }; ";


            wifi_19_25.expain1="public class CarrierInfo {  // 依赖sim卡的mncmcc获取的信息 class to get current carrier based on SIM's MNC/MCC\n" +
                    "    private final String TAG = \"WifiCarrierInfo\";\n" +
                    "    // list carrier names here  运营商集合\n" +
                    "    private static final String mTMOCarrier = \"T-Mobile\"; \n" +
                    "    private static final String mSprintCarrier = \"Sprint\";\n" +
                    "    private static final String mMetroPCSCarrier = \"MetroPcs\";\n" +
                    "    private static final String mATTCarrier = \"AT&T\";\n" +
                    "\t\n" +
                    "\t// 当前的运营商\n" +
                    "    private String mCurrCarrier = null;\n" +
                    "    private TelephonyManager mTelephonyManager;\n" +
                    "    private PhoneStateListener mPhoneStateListener;  // 监听sim卡状态\n" +
                    "\t}\n" +
                    "\t\n" +
                    "\t    public void 【 PasspointManager 】dump(PrintWriter pw) {\n" +
                    "        pw.println(\"Dump of PasspointManager\");\n" +
                    "        pw.println(\"PasspointManager - Providers Begin ---\");\n" +
                    "        pw.println(\"PasspointManager - Providers End ---\");\n" +
                    "        pw.println(\"PasspointManager - Next provider ID to be assigned \" + mProviderIndex);\n" +
                    ".....\n" +
                    "        pw.println(\"Current CarrierIno = \" +mCarrierInfo.getCurrentCarrier());\n" +
                    "    }";
            keyWordList.add(wifi_19_25);



            KeyWordItem wifi_19_26 = new KeyWordItem();
            wifi_19_26.keyword = "Chipset information :";
            wifi_19_26.explain=" 获取WIFI功能的工具类  class BaseWifiDiagnostics {} 【 WifiDiagnostics extends BaseWifiDiagnostics {} 】  ";
            wifi_19_26.classNameForShuxing = " WifiServiceImpl.java -> ClientModeImpl.java -> BaseWifiDiagnostics  mWifiDiagnostics 【父类引用指向子类 WifiDiagnostics extends BaseWifiDiagnostics 】" ;
            wifi_19_26.shuxingDefine="class BaseWifiDiagnostics {} 【 WifiDiagnostics extends BaseWifiDiagnostics {} 】 ";
            wifi_19_26.printcode="(WifiServiceImpl.java -> ClientModeImpl.java -> BaseWifiDiagnostics.java)  dump(){} ";
            wifi_19_26.expain1="【WifiDiagnostics.dump】public synchronized void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tsuper.dump(pw);  【 WifiDiagnostics.java extends BaseWifiDiagnostics 打印父类的 BaseWifiDiagnostics的 dump方法】\n" +
                    "}\n" +
                    "\n" +
                    "class BaseWifiDiagnostics {\n" +
                    "String mFirmwareVersion;  // FW Version is:   fireware 固件版本  来自  mWifiNative.getFirmwareVersion(); \n" +
                    "String mDriverVersion; // Driver Version is:   驱动版本                mWifiNative.getDriverVersion();\n" +
                    "int mSupportedFeatureSet; // Supported Feature set:    bit功能位(一直是-1 废弃感觉 其实还没开发相关功能 还没实现)      mWifiNative.getSupportedLoggerFeatureSet();\n" +
                    "final WifiNative mWifiNative;  // 与驱动交互的类\n" +
                    "\n" +
                    "protected synchronized void dump(PrintWriter pw) {\n" +
                    "\tpw.println(\"Chipset information :-----------------------------------------------\");\n" +
                    "\tpw.println(\"FW Version is: \" + mFirmwareVersion);\n" +
                    "\tpw.println(\"Driver Version is: \" + mDriverVersion);\n" +
                    "\tpw.println(\"Supported Feature set: \" + mSupportedFeatureSet);\n" +
                    "}\n" +
                    "}";
            keyWordList.add(wifi_19_26);



            KeyWordItem wifi_19_27 = new KeyWordItem();
            wifi_19_27.keyword = "Bug dump 0";
            wifi_19_27.explain=" WifiDiagnostics.java 将打印大量的Log日志出来   \n 起始标识【 Bug dump 0 】【 Bug dump 1 】   \n 起始标识【 Alert dump 0 】 【 Alert dump 1 】 \n最后刷新时间点【 Last Flush Time 】 ";
            wifi_19_27.classNameForShuxing = "WifiDiagnostics extends BaseWifiDiagnostics {}  ";
            wifi_19_27.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java  )【WifiDiagnostics】 BaseWifiDiagnostics mWifiDiagnostics\n ";
            wifi_19_27.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiDiagnostics.java   )  dump(fd, pw, args){ mCarrierIno.toString() }; ";
            wifi_19_27.expain1="**********************************  BugReport Begin ********************************** \n" +
                    "class BugReport {\n" +
                    "\tlong systemTimeMs;          // 系统时间  毫秒\n" +
                    "\tlong kernelTimeNanos;   // 内核时间    纳秒\n" +
                    "\tint errorCode;       // 错误码\n" +
                    "\tHashMap<String, byte[][]> ringBuffers = new HashMap(); // 字符串-字节二维数组byte[][] 的 MAP\n" +
                    "\tbyte[] fwMemoryDump;      // 固件镜像内存字节数据\n" +
                    "\tbyte[] mDriverStateDump;   // 驱动状态字节Log数据\n" +
                    "\tbyte[] alertData;     // 提示字节Log数据\n" +
                    "\tLimitedCircularArray<String> kernelLogLines;  // kernelLog 内核Log\n" +
                    "\tArrayList<String> logcatLines;\n" +
                    "public static final String FIRMWARE_DUMP_SECTION_HEADER = \"FW Memory dump\";  // 固件内存镜像\n" +
                    "static final String DRIVER_DUMP_SECTION_HEADER = \"Driver state dump\";  // 驱动状态镜像\n" +
                    "\n" +
                    "\t   【 BugReport.toString() 】     public String toString() {\n" +
                    "            StringBuilder builder = new StringBuilder();\n" +
                    "\n" +
                    "            Calendar c = Calendar.getInstance();\n" +
                    "            c.setTimeInMillis(systemTimeMs);\n" +
                    "\t\t\t//  system time = 11-15 16:24:23.919    系统时间 long systemTimeMs;\n" +
                    "            builder.append(\"system time = \").append(String.format(\"%tm-%td %tH:%tM:%tS.%tL\", c, c, c, c, c, c)).append(\"\\n\");\n" +
                    "\n" +
                    "            long kernelTimeMs = kernelTimeNanos/(1000*1000);\n" +
                    "\t\t\t// kernel time =    把 NS 纳秒  转为  秒了  kernelTimeNanos/(1000*1000*1000)\n" +
                    "            builder.append(\"kernel time = \").append(kernelTimeMs/1000).append(\".\").append(kernelTimeMs%1000).append(\"\\n\");\n" +
                    "\n" +
                    "            if (alertData == null)  // reason =   int errorCode;       // 错误码 \n" +
                    "                builder.append(\"reason = \").append(errorCode).append(\"\\n\");\n" +
                    "            else {\n" +
                    "                builder.append(\"errorCode = \").append(errorCode); // reason =   int errorCode;       // 错误码 \n" +
                    "                builder.append(\"data \\n\");  // data   把alert字节数据转为Base64的字符串输出  byte[] alertData;     // 提示字节Log数据\n" +
                    "                builder.append(compressToBase64(alertData)).append(\"\\n\");\n" +
                    "            }\n" +
                    "\n" +
                    "            if (kernelLogLines != null) {\n" +
                    "                builder.append(\"kernel log: \\n\");  // 打印 kernel log:  //  LimitedCircularArray<String> kernelLogLines;   kernelLog 内核Log\n" +
                    "                for (int i = 0; i < kernelLogLines.size(); i++) {  \n" +
                    "                    builder.append(kernelLogLines.get(i)).append(\"\\n\");\n" +
                    "                }\n" +
                    "                builder.append(\"\\n\");\n" +
                    "            }\n" +
                    "\n" +
                    "            if (logcatLines != null) {\n" +
                    "                builder.append(\"system log: \\n\");\n" +
                    "                for (int i = 0; i < logcatLines.size(); i++) { // 打印logcat的Log \tArrayList<String> logcatLines;\n" +
                    "                    builder.append(logcatLines.get(i)).append(\"\\n\");\n" +
                    "                }\n" +
                    "                builder.append(\"\\n\");\n" +
                    "            }\n" +
                    "\n" +
                    "            for (HashMap.Entry<String, byte[][]> e : ringBuffers.entrySet()) {\n" +
                    "                String ringName = e.getKey();     // 字符串-字节二维数组byte[][] 的 MAP   String字符串是Name\n" +
                    "                byte[][] buffers = e.getValue();  // 打印 ring-buffer = HashMap<String, byte[][]> ringBuffers = new HashMap(); \n" +
                    "                builder.append(\"ring-buffer = \").append(ringName).append(\"\\n\");\n" +
                    "\n" +
                    "                int size = 0;\n" +
                    "                for (int i = 0; i < buffers.length; i++) {\n" +
                    "                    size += buffers[i].length;\n" +
                    "                }\n" +
                    "\n" +
                    "                byte[] buffer = new byte[size];\n" +
                    "                int index = 0;\n" +
                    "                for (int i = 0; i < buffers.length; i++) {\n" +
                    "                    System.arraycopy(buffers[i], 0, buffer, index, buffers[i].length);\n" +
                    "                    index += buffers[i].length;\n" +
                    "                }\n" +
                    "\n" +
                    "\t\t\t\t// 把二维数组所有的 一行数据  拼接成一个等长的一维数组  一维数组 byte[] 转为 base64字符串\n" +
                    "                builder.append(compressToBase64(buffer));\n" +
                    "                builder.append(\"\\n\");\n" +
                    "            }\n" +
                    "\n" +
                    "            if (fwMemoryDump != null) {\n" +
                    "\t\t\t//  String FIRMWARE_DUMP_SECTION_HEADER = \"FW Memory dump\"; \n" +
                    "                builder.append(FIRMWARE_DUMP_SECTION_HEADER);  // 固件内存镜像\n" +
                    "                builder.append(\"\\n\");\n" +
                    "                builder.append(compressToBase64(fwMemoryDump)); // byte[] fwMemoryDump;  转为base64字符串打印    // 固件镜像内存字节数据\n" +
                    "                builder.append(\"\\n\");\n" +
                    "            }\n" +
                    "\n" +
                    "            if (mDriverStateDump != null) {\n" +
                    "\t\t\t//  String DRIVER_DUMP_SECTION_HEADER = \"Driver state dump\";     \n" +
                    "                builder.append(DRIVER_DUMP_SECTION_HEADER);  // \"Driver state dump\"  byte[] mDriverStateDump; 转为\"US-ASCII\" 为编码的字符串   // 驱动状态字节Log数据 \n" +
                    "                if (StringUtil.isAsciiPrintable(mDriverStateDump)) {\n" +
                    "                    builder.append(\" (ascii)\\n\");\n" +
                    "                    builder.append(new String(mDriverStateDump, Charset.forName(\"US-ASCII\")));\n" +
                    "                    builder.append(\"\\n\");\n" +
                    "                } else {\n" +
                    "                    builder.append(\" (base64)\\n\");   //  byte[] mDriverStateDump; 转为 base64 为编码的字符串\n" +
                    "                    builder.append(compressToBase64(mDriverStateDump));\n" +
                    "                }\n" +
                    "            }\n" +
                    "\n" +
                    "            return builder.toString();\n" +
                    "        }\n" +
                    "\t\t\n" +
                    "\t}\n" +
                    "**********************************  BugReport End ********************************** \n" +
                    "**********************************  LimitedCircularArray Begin ********************************** \n" +
                    "class LimitedCircularArray<E> {\n" +
                    "private ArrayList<E> mArrayList;\n" +
                    "private int mMax;\n" +
                    "}\n" +
                    "**********************************  LimitedCircularArray End ********************************** \n" +
                    "\n" +
                    "class WifiDiagnostics extends BaseWifiDiagnostics {  // 追踪框架日志 \n" +
                    "final int MAX_ALERT_REPORTS                       = 1;\n" +
                    "final LimitedCircularArray<BugReport> mLastAlerts = new LimitedCircularArray<BugReport>(MAX_ALERT_REPORTS);\n" +
                    "final LimitedCircularArray<BugReport> mLastBugReports = new LimitedCircularArray<BugReport>(MAX_BUG_REPORTS);\n" +
                    "final SparseLongArray mLastDumpTime = new SparseLongArray();  /** 开机持续时间 Map from dump reason to elapsed time millis */\n" +
                    "\n" +
                    "【 WifiDiagnostics 】public synchronized void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tsuper.dump(pw);  【 WifiDiagnostics.java extends BaseWifiDiagnostics 打印父类的BaseWifiDiagnostics的 dump方法】\n" +
                    "\tfor (int i = 0; i < mLastAlerts.size(); i++) {\n" +
                    "\t\tpw.println(\"--------------------------------------------------------------------\");\n" +
                    "\t\tpw.println(\"Alert dump \" + i); // 【 Alert dump 0 】 【 Alert dump 1 】 【 Alert dump 2 】 【 Alert dump 3 】 【 Alert dump 4 】\n" +
                    "\t\tpw.print(mLastAlerts.get(i));\n" +
                    "\t\tpw.println(\"--------------------------------------------------------------------\");\n" +
                    "\t}\n" +
                    "\tfor (int i = 0; i < mLastBugReports.size(); i++) {\n" +
                    "\t\tpw.println(\"--------------------------------------------------------------------\");\n" +
                    "\t\tpw.println(\"Bug dump \" + i);  // 【 Bug dump 0 】 【 Bug dump 1 】 【 Bug dump 2 】 【 Bug dump 3 】 【 Bug dump 4 】 \n" +
                    "\t\tpw.print(mLastBugReports.get(i));\n" +
                    "\t\tpw.println(\"--------------------------------------------------------------------\");\n" +
                    "\t}\n" +
                    "\tpw.println(\"Last Flush Time: \" + mLastDumpTime.toString());   // Last Flush Time: 最后更新的时间 \n" +
                    "\tpw.println(\"--------------------------------------------------------------------\");\n" +
                    "\n" +
                    "\tdumpPacketFates(pw);\n" +
                    "\tmLastMileLogger.dump(pw);\n" +
                    "\tpw.println(\"--------------------------------------------------------------------\");\n" +
                    "}\n" +
                    "\n" +
                    "打印Log列表:\n" +
                    "【 Bug dump 0 】 【 Bug dump 1 】 【 Bug dump 2 】  --- WifiDiagnostics【List<BugReport>】  BugReport.toString()\n" +
                    "system time = BugReport.systemTimeMs [long ];  [ 系统时间 ]\n" +
                    "kernel time = BugReport.kernelTimeNanos/(1000*1000*1000)  [ 内核时间 ]\n" +
                    "kernel log: = 数组 BugReport.LimitedCircularArray<String> kernelLogLines; // 打印 kernel-log:\n" +
                    "system log: = 数组 BugReport.ArrayList<String> logcatLines;  // 系统bugrepot log\n" +
                    "ring-buffer = 【名称】 BugReport.HashMap<String 【名称】, byte[][]【转为 byte[] 一维数组  再转为字符串输出 】> ringBuffers \n" +
                    "FW Memory dump =  BugReport.byte[] fwMemoryDump; 固件内存的字节数据数组\n" +
                    "Driver state dump =   BugReport.byte[] mDriverStateDump; 转为\"US-ASCII\" 为编码的字符串  或者转为 base64 的 字符串\n" +
                    "Last Flush Time: =  WifiDiagnostics.mLastDumpTime  [ SparseLongArray mLastDumpTime ]";
            keyWordList.add(wifi_19_27);



            KeyWordItem wifi_19_28 = new KeyWordItem();
            wifi_19_28.keyword = "--------------------- Latest fates ----------------------";
            wifi_19_28.explain=" 当前WifiDiagnostics 保存的 最新接收到的帧信息ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure ";
            wifi_19_28.classNameForShuxing = "WifiServiceImpl.java -> ClientModeImpl.java ->【 WifiDiagnostics 】 BaseWifiDiagnostics  mWifiDiagnostics 】  ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure  中的Item--FateReport的toString ";
            wifi_19_28.shuxingDefine=" abstract class FateReport{}  【 TxFateReport extends FateReport {} 】  【 RxFateReport extends FateReport 】   ";
            wifi_19_28.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiDiagnostics.java   )  dump(fd, pw, args){ ArrayList<WifiNative.FateReport>.toString() }; ";
            wifi_19_28.expain1="**********************************  WifiNative.FateReport Begin ********************************** \n" +
                    "\n" +
                    "abstract static class FateReport {  // Packet fate API\n" +
                    "\tfinal static int USEC_PER_MSEC = 1000;\n" +
                    "\t// The driver timestamp is a 32-bit counter, in microseconds. This field holds the\n" +
                    "\t// maximal value of a driver timestamp in milliseconds.\n" +
                    "\tfinal static int MAX_DRIVER_TIMESTAMP_MSEC = (int) (0xffffffffL / 1000);\n" +
                    "\tfinal static SimpleDateFormat dateFormatter = new SimpleDateFormat(\"HH:mm:ss.SSS\");\n" +
                    "\n" +
                    "\tfinal byte mFate;  // 字节类型  用来标记 RX 和 TX 中 具体的帧类型\n" +
                    "\tfinal long mDriverTimestampUSec;\n" +
                    "\tfinal byte mFrameType;\n" +
                    "\tfinal byte[] mFrameBytes;\n" +
                    "\tfinal long mEstimatedWallclockMSec;\n" +
                    "\t\n" +
                    "\tprotected abstract String directionToString();\n" +
                    "    protected abstract String fateToString();\n" +
                    "\t\n" +
                    "\n" +
                    "\tbyte mFrameType;  // 用来标记是哪种帧  管理帧  数据帧 \n" +
                    "    byte FRAME_TYPE_UNKNOWN = 0;        = \"unknown\"\n" +
                    "    byte FRAME_TYPE_ETHERNET_II = 1;    = \"data\"\n" +
                    "    byte FRAME_TYPE_80211_MGMT = 2;     = \"802.11 management\"\n" +
                    "\t\n" +
                    "\t\n" +
                    "\n" +
                    "\t\t\n" +
                    "}\n" +
                    "\n" +
                    "static final class TxFateReport extends FateReport { directionToString()=\"TX\"【用于区别方向】;   }\n" +
                    "byte mFate;  // 字节类型  用于区别发送包的不同\n" +
                    " final byte TX_PKT_FATE_ACKED = 0;                 = \"acked\"\n" +
                    " final byte TX_PKT_FATE_SENT = 1;                  = \"sent\"\n" +
                    " final byte TX_PKT_FATE_FW_QUEUED = 2;             = \"firmware queued\"\n" +
                    " final byte TX_PKT_FATE_FW_DROP_INVALID = 3;       = \"firmware dropped (invalid frame)\"\n" +
                    " final byte TX_PKT_FATE_FW_DROP_NOBUFS = 4;        = \"firmware dropped (no bufs)\"\"\n" +
                    " final byte TX_PKT_FATE_FW_DROP_OTHER = 5;         = \"firmware dropped (other)\"\n" +
                    " final byte TX_PKT_FATE_DRV_QUEUED = 6;            = \"driver queued\"\n" +
                    " final byte TX_PKT_FATE_DRV_DROP_INVALID = 7;      = \"driver dropped (invalid frame)\"\n" +
                    " final byte TX_PKT_FATE_DRV_DROP_NOBUFS = 8;       = \"driver dropped (no bufs)\"\n" +
                    " final byte TX_PKT_FATE_DRV_DROP_OTHER = 9;        = \"driver dropped (other)\"\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "final class RxFateReport extends FateReport { directionToString()=\"RX\"【用于区别方向】; }\n" +
                    "byte mFate;  // 字节类型  用于区别 接收包的不同\n" +
                    "final byte RX_PKT_FATE_SUCCESS = 0;              = \"success\"\n" +
                    "final byte RX_PKT_FATE_FW_QUEUED = 1;            = \"firmware queued\"\n" +
                    "final byte RX_PKT_FATE_FW_DROP_FILTER = 2;       = \"firmware dropped (filter)\"\n" +
                    "final byte RX_PKT_FATE_FW_DROP_INVALID = 3;      = \"firmware dropped (invalid frame)\"\n" +
                    "final byte RX_PKT_FATE_FW_DROP_NOBUFS = 4;       = \"firmware dropped (no bufs)\"\"\n" +
                    "final byte RX_PKT_FATE_FW_DROP_OTHER = 5;        = \"firmware dropped (other)\"\n" +
                    "final byte RX_PKT_FATE_DRV_QUEUED = 6;           = \"driver queued\"\n" +
                    "final byte RX_PKT_FATE_DRV_DROP_FILTER = 7;      = \"driver dropped (filter)\"\n" +
                    "final byte RX_PKT_FATE_DRV_DROP_INVALID = 8;     = \"driver dropped (invalid frame)\"\n" +
                    "final byte RX_PKT_FATE_DRV_DROP_NOBUFS = 9;      = \"driver dropped (no bufs)\"\n" +
                    "final byte RX_PKT_FATE_DRV_DROP_OTHER = 10;      = \"driver dropped (other)\"\n" +
                    "\t\n" +
                    "\t\t\n" +
                    "**********************************  WifiNative.FateReport End ********************************** \n" +
                    "\n" +
                    "\n" +
                    "class WifiDiagnostics extends BaseWifiDiagnostics { \n" +
                    "\n" +
                    " ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure; //FateReport 来回交互帧 WifiNative.FateReport   /** Packet fate reporting */\n" +
                    "final int MAX_FATE_LOG_LEN = 32;   // field in WifiLoggerHal \n" +
                    "\n" +
                    "     private ArrayList<WifiNative.FateReport> fetchPacketFates() {\n" +
                    "        ArrayList<WifiNative.FateReport> mergedFates = new ArrayList<WifiNative.FateReport>();\n" +
                    "        WifiNative.TxFateReport[] txFates =   new WifiNative.TxFateReport[WifiLoggerHal.MAX_FATE_LOG_LEN]; // 长度 32 \n" +
                    "        if (mWifiNative.getTxPktFates(mWifiNative.getClientInterfaceName(), txFates)) {  //  获取 translate 发出的包\n" +
                    "            for (int i = 0; i < txFates.length && txFates[i] != null; i++) {\n" +
                    "                mergedFates.add(txFates[i]);\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        WifiNative.RxFateReport[] rxFates = new WifiNative.RxFateReport[WifiLoggerHal.MAX_FATE_LOG_LEN];  // 长度 32 \n" +
                    "        if (mWifiNative.getRxPktFates(mWifiNative.getClientInterfaceName(), rxFates)) {  //  获取 receive 接收的包\n" +
                    "            for (int i = 0; i < rxFates.length && rxFates[i] != null; i++) {\n" +
                    "                mergedFates.add(rxFates[i]);\n" +
                    "            }\n" +
                    "        }\n" +
                    "        Collections.sort(mergedFates, new Comparator<WifiNative.FateReport>() { // 以 时间为排序规则 重新排序 包\n" +
                    "            public int compare(WifiNative.FateReport lhs, WifiNative.FateReport rhs) {\n" +
                    "                return Long.compare(lhs.mDriverTimestampUSec, rhs.mDriverTimestampUSec);  }\n" +
                    "        });\n" +
                    "        return mergedFates;  // 返回当前 32个接收包   32个发送包 \n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "\n" +
                    "【WifiNative.FateReport】【 toVerboseStringWithPiiAllowed 】 public String toVerboseStringWithPiiAllowed() {\n" +
                    "\tStringWriter sw = new StringWriter();\n" +
                    "\tPrintWriter pw = new PrintWriter(sw);\n" +
                    "\tFrameParser parser = new FrameParser(mFrameType, mFrameBytes);\n" +
                    "\tpw.format(\"Frame direction: %s\\n\", directionToString());  // Frame direction:  打印 RX接收  TX发送\n" +
                    "\tpw.format(\"Frame timestamp: %d\\n\", mDriverTimestampUSec); // 帧内的时间戳 \n" +
                    "\tpw.format(\"Frame fate: %s\\n\", fateToString());  // RX 和 TX  对应的帧的type\n" +
                    "\tpw.format(\"Frame type: %s\\n\", frameTypeToString(mFrameType));   // Frame type: 三种  \"802.11 management\"   \"data\" \"unknown\"\n" +
                    "\tpw.format(\"Frame protocol: %s\\n\", parser.mMostSpecificProtocolString);  //  协议类型\n" +
                    "\tpw.format(\"Frame protocol type: %s\\n\", parser.mTypeString);  //  协议子类型\n" +
                    "\tpw.format(\"Frame length: %d\\n\", mFrameBytes.length);  // 帧的长度\n" +
                    "\tpw.append(\"Frame bytes\");\n" +
                    "\tpw.append(HexDump.dumpHexString(mFrameBytes));  // byte[] mFrameBytes  把byte[] 数据的二进制打印出来   potentially contains PII \n" +
                    "\tpw.append(\"\\n\");\n" +
                    "\treturn sw.toString();\n" +
                    "}\n" +
                    "\n" +
                    "【 dumpPacketFatesInternal 】\n" +
                    "void dumpPacketFatesInternal(PrintWriter pw, String description, ArrayList<WifiNative.FateReport> fates, boolean verbose) {\n" +
                    "        if (fates == null) {     //  数据帧列表为空\n" +
                    "            pw.format(\"No fates fetched for \\\"%s\\\"\\n\", description);\n" +
                    "            return;\n" +
                    "        }\n" +
                    "\n" +
                    "        if (fates.size() == 0) {  //  数据帧列表长度为 0 \n" +
                    "            pw.format(\"HAL provided zero fates for \\\"%s\\\"\\n\", description);\n" +
                    "            return;\n" +
                    "        }\n" +
                    "\n" +
                    "\t\t// --------------------- Latest fates ----------------------\n" +
                    "\t\t// --------------------- Last failed connection fates ----------------------\n" +
                    "        pw.format(\"--------------------- %s ----------------------\\n\", description);\n" +
                    "\n" +
                    "        StringBuilder verboseOutput = new StringBuilder();\n" +
                    "        pw.print(WifiNative.FateReport.getTableHeader());\n" +
                    "        for (WifiNative.FateReport fate : fates) {\n" +
                    "            pw.print(fate.toTableRowString());\n" +
                    "            if (verbose) {\n" +
                    "                // Important: only print Personally Identifiable Information (PII) if verbose\n" +
                    "                // logging is turned on.\n" +
                    "                verboseOutput.append(fate.toVerboseStringWithPiiAllowed()); // 打印出  FateReport 的 可读交互信息\n" +
                    "                verboseOutput.append(\"\\n\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        if (verbose) {\n" +
                    "            pw.format(\"\\n>>> VERBOSE PACKET FATE DUMP <<<\\n\\n\");\n" +
                    "            pw.print(verboseOutput.toString());   //  只有打印verbose  才能打印 FateReport的信息\n" +
                    "        }\n" +
                    "// 打印 ArrayList<WifiNative.FateReport> 数组结束\n" +
                    "        pw.println(\"--------------------------------------------------------------------\");  \n" +
                    "    }\n" +
                    "\t\n" +
                    "【 WifiDiagnostics.dump 】public synchronized void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "dumpPacketFates(pw);\n" +
                    "}\n" +
                    "    private void dumpPacketFates(PrintWriter pw) {\n" +
                    "\t// Last failed connection fates // ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure; 本地保存的交互帧\n" +
                    "dumpPacketFatesInternal(pw, \"Last failed connection fates\", mPacketFatesForLastFailure,isVerboseLoggingEnabled());\n" +
                    "    // 调用时 直接从 WifiNative 拿到的   32个接收包   32个发送包 的64个包数组   fetchPacketFates() =【ArrayList<WifiNative.FateReport>】\n" +
                    "dumpPacketFatesInternal(pw, \"Latest fates\", fetchPacketFates(), isVerboseLoggingEnabled());\n" +
                    "    }\t\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "LOG列表:\n" +
                    "No fates fetched for \"Latest fatess\"   = : WifiDiagnostics. ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure 为空\n" +
                    "HAL provided zero fates for \"Latest fatess\" = : WifiDiagnostics. ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure 长度为0 \n" +
                    "--------------------- Latest fates ---------------------- =  打印(32个最新保存的 FateReport 包)WifiDiagnostics. ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure\n" +
                    "\n" +
                    "No fates fetched for \"Last failed connection fates\"   = :  WifiDiagnostics 从WifiNative得到的FateReport数组为空\n" +
                    "HAL provided zero fates for \"Last failed connection fates\" = : WifiDiagnostics从WifiNative得到的FateReport数组 长度为0 \n" +
                    "--------------------- Last failed connection fates ---------------------- 打印调用WifiNative得到的(64个最新保存的 FateReport(32个发送包_32个接收包)WifiDiagnostics.fetchPacketFates()=ArrayList<WifiNative.FateReport>(64) \n" +
                    "Frame direction: RX   // Frame direction:  打印 RX 接收    WifiNative.FateReport.directionToString() 返回 RX\n" +
                    "Frame direction: TX   // Frame direction:  打印  TX发送    WifiNative.FateReport.directionToString() 返回 TX\n" +
                    "Frame timestamp:  // 帧内的时间戳   FateReport.mDriverTimestampUSec   WifiDiagnostics.list<(WifiNative.FateReport)>   \n" +
                    "Frame fate:      // RX 和 TX  对应的帧的type  // FateReport.byte mFate;  对应的 RX  或 TX 类型\n" +
                    "Frame type: 802.11 management  ||   Frame type: data  || Frame type: unknown    // FateReport.byte mFrameType;   Frame type: 三种  \"802.11 management\"   \"data\" \"unknown\"\n" +
                    "Frame protocol: ??  //  协议类型  new FrameParser(mFrameType 【byte】, mFrameBytes 【byte[]】).mMostSpecificProtocolString\n" +
                    "Frame protocol type: ?? //  协议子类型 new FrameParser(mFrameType 【byte】, mFrameBytes 【byte[]】).mTypeString\n" +
                    "Frame length: 64,   // 帧的长度 mFrameBytes.size()  //    FateReport byte[] mFrameBytes; \n" +
                    "Frame bytes   // 帧的byte[] 数组打印  打印 byte[] mFrameBytes;     FrameParser.java 将另外写关注 \n" +
                    "pw.append(HexDump.dumpHexString(mFrameBytes));  // 打印帧的字节数据  byte[] mFrameBytes  把byte[] 数据的二进制打印出来   potentially contains PII \n" +
                    "\t\n" +
                    "结束LOG1:   >>> VERBOSE PACKET FATE DUMP <<<\n" +
                    "结束LOG2:   结束LOG2:  搜索 【 last-mile log  】";
            keyWordList.add(wifi_19_28);


            KeyWordItem wifi_19_29 = new KeyWordItem();
            wifi_19_29.keyword = "--------------------- Last failed connection fates ----------------------";
            wifi_19_29.explain=" 当前WifiDiagnostics 直接调用WifiNative 获取到的32个接收Receive Frame 和 32  个 TranslateFram 发送帧 ";
            wifi_19_29.classNameForShuxing = "WifiServiceImpl.java -> ClientModeImpl.java ->【 WifiDiagnostics 】 BaseWifiDiagnostics  mWifiDiagnostics 】  mWifiNative.getTxPktFates(32)【WifiNative.FateReport】  mWifiNative.getRxPktFates(32)【WifiNative.FateReport】  ";

            wifi_19_29.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiDiagnostics.java   )  dump(fd, pw, args){ ArrayList<WifiNative.FateReport>.toString() }; ";

            wifi_19_29.shuxingDefine=" abstract class FateReport{}  【 TxFateReport extends FateReport {} 】  【 RxFateReport extends FateReport 】   ";
            wifi_19_29.expain1="**********************************  WifiNative.FateReport Begin ********************************** \n" +
                    "\n" +
                    "abstract static class FateReport {  // Packet fate API\n" +
                    "\tfinal static int USEC_PER_MSEC = 1000;\n" +
                    "\t// The driver timestamp is a 32-bit counter, in microseconds. This field holds the\n" +
                    "\t// maximal value of a driver timestamp in milliseconds.\n" +
                    "\tfinal static int MAX_DRIVER_TIMESTAMP_MSEC = (int) (0xffffffffL / 1000);\n" +
                    "\tfinal static SimpleDateFormat dateFormatter = new SimpleDateFormat(\"HH:mm:ss.SSS\");\n" +
                    "\n" +
                    "\tfinal byte mFate;  // 字节类型  用来标记 RX 和 TX 中 具体的帧类型\n" +
                    "\tfinal long mDriverTimestampUSec;\n" +
                    "\tfinal byte mFrameType;\n" +
                    "\tfinal byte[] mFrameBytes;\n" +
                    "\tfinal long mEstimatedWallclockMSec;\n" +
                    "\t\n" +
                    "\tprotected abstract String directionToString();\n" +
                    "    protected abstract String fateToString();\n" +
                    "\t\n" +
                    "\n" +
                    "\tbyte mFrameType;  // 用来标记是哪种帧  管理帧  数据帧 \n" +
                    "    byte FRAME_TYPE_UNKNOWN = 0;        = \"unknown\"\n" +
                    "    byte FRAME_TYPE_ETHERNET_II = 1;    = \"data\"\n" +
                    "    byte FRAME_TYPE_80211_MGMT = 2;     = \"802.11 management\"\n" +
                    "\t\n" +
                    "\t\n" +
                    "\n" +
                    "\t\t\n" +
                    "}\n" +
                    "\n" +
                    "static final class TxFateReport extends FateReport { directionToString()=\"TX\"【用于区别方向】;   }\n" +
                    "byte mFate;  // 字节类型  用于区别发送包的不同\n" +
                    " final byte TX_PKT_FATE_ACKED = 0;                 = \"acked\"\n" +
                    " final byte TX_PKT_FATE_SENT = 1;                  = \"sent\"\n" +
                    " final byte TX_PKT_FATE_FW_QUEUED = 2;             = \"firmware queued\"\n" +
                    " final byte TX_PKT_FATE_FW_DROP_INVALID = 3;       = \"firmware dropped (invalid frame)\"\n" +
                    " final byte TX_PKT_FATE_FW_DROP_NOBUFS = 4;        = \"firmware dropped (no bufs)\"\"\n" +
                    " final byte TX_PKT_FATE_FW_DROP_OTHER = 5;         = \"firmware dropped (other)\"\n" +
                    " final byte TX_PKT_FATE_DRV_QUEUED = 6;            = \"driver queued\"\n" +
                    " final byte TX_PKT_FATE_DRV_DROP_INVALID = 7;      = \"driver dropped (invalid frame)\"\n" +
                    " final byte TX_PKT_FATE_DRV_DROP_NOBUFS = 8;       = \"driver dropped (no bufs)\"\n" +
                    " final byte TX_PKT_FATE_DRV_DROP_OTHER = 9;        = \"driver dropped (other)\"\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "final class RxFateReport extends FateReport { directionToString()=\"RX\"【用于区别方向】; }\n" +
                    "byte mFate;  // 字节类型  用于区别 接收包的不同\n" +
                    "final byte RX_PKT_FATE_SUCCESS = 0;              = \"success\"\n" +
                    "final byte RX_PKT_FATE_FW_QUEUED = 1;            = \"firmware queued\"\n" +
                    "final byte RX_PKT_FATE_FW_DROP_FILTER = 2;       = \"firmware dropped (filter)\"\n" +
                    "final byte RX_PKT_FATE_FW_DROP_INVALID = 3;      = \"firmware dropped (invalid frame)\"\n" +
                    "final byte RX_PKT_FATE_FW_DROP_NOBUFS = 4;       = \"firmware dropped (no bufs)\"\"\n" +
                    "final byte RX_PKT_FATE_FW_DROP_OTHER = 5;        = \"firmware dropped (other)\"\n" +
                    "final byte RX_PKT_FATE_DRV_QUEUED = 6;           = \"driver queued\"\n" +
                    "final byte RX_PKT_FATE_DRV_DROP_FILTER = 7;      = \"driver dropped (filter)\"\n" +
                    "final byte RX_PKT_FATE_DRV_DROP_INVALID = 8;     = \"driver dropped (invalid frame)\"\n" +
                    "final byte RX_PKT_FATE_DRV_DROP_NOBUFS = 9;      = \"driver dropped (no bufs)\"\n" +
                    "final byte RX_PKT_FATE_DRV_DROP_OTHER = 10;      = \"driver dropped (other)\"\n" +
                    "\t\n" +
                    "\t\t\n" +
                    "**********************************  WifiNative.FateReport End ********************************** \n" +
                    "\n" +
                    "\n" +
                    "class WifiDiagnostics extends BaseWifiDiagnostics { \n" +
                    "\n" +
                    " ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure; //FateReport 来回交互帧 WifiNative.FateReport   /** Packet fate reporting */\n" +
                    "final int MAX_FATE_LOG_LEN = 32;   // field in WifiLoggerHal \n" +
                    "\n" +
                    "     private ArrayList<WifiNative.FateReport> fetchPacketFates() {\n" +
                    "        ArrayList<WifiNative.FateReport> mergedFates = new ArrayList<WifiNative.FateReport>();\n" +
                    "        WifiNative.TxFateReport[] txFates =   new WifiNative.TxFateReport[WifiLoggerHal.MAX_FATE_LOG_LEN]; // 长度 32 \n" +
                    "        if (mWifiNative.getTxPktFates(mWifiNative.getClientInterfaceName(), txFates)) {  //  获取 translate 发出的包\n" +
                    "            for (int i = 0; i < txFates.length && txFates[i] != null; i++) {\n" +
                    "                mergedFates.add(txFates[i]);\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        WifiNative.RxFateReport[] rxFates = new WifiNative.RxFateReport[WifiLoggerHal.MAX_FATE_LOG_LEN];  // 长度 32 \n" +
                    "        if (mWifiNative.getRxPktFates(mWifiNative.getClientInterfaceName(), rxFates)) {  //  获取 receive 接收的包\n" +
                    "            for (int i = 0; i < rxFates.length && rxFates[i] != null; i++) {\n" +
                    "                mergedFates.add(rxFates[i]);\n" +
                    "            }\n" +
                    "        }\n" +
                    "        Collections.sort(mergedFates, new Comparator<WifiNative.FateReport>() { // 以 时间为排序规则 重新排序 包\n" +
                    "            public int compare(WifiNative.FateReport lhs, WifiNative.FateReport rhs) {\n" +
                    "                return Long.compare(lhs.mDriverTimestampUSec, rhs.mDriverTimestampUSec);  }\n" +
                    "        });\n" +
                    "        return mergedFates;  // 返回当前 32个接收包   32个发送包 \n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "\n" +
                    "【WifiNative.FateReport】【 toVerboseStringWithPiiAllowed 】 public String toVerboseStringWithPiiAllowed() {\n" +
                    "\tStringWriter sw = new StringWriter();\n" +
                    "\tPrintWriter pw = new PrintWriter(sw);\n" +
                    "\tFrameParser parser = new FrameParser(mFrameType, mFrameBytes);\n" +
                    "\tpw.format(\"Frame direction: %s\\n\", directionToString());  // Frame direction:  打印 RX接收  TX发送\n" +
                    "\tpw.format(\"Frame timestamp: %d\\n\", mDriverTimestampUSec); // 帧内的时间戳 \n" +
                    "\tpw.format(\"Frame fate: %s\\n\", fateToString());  // RX 和 TX  对应的帧的type\n" +
                    "\tpw.format(\"Frame type: %s\\n\", frameTypeToString(mFrameType));   // Frame type: 三种  \"802.11 management\"   \"data\" \"unknown\"\n" +
                    "\tpw.format(\"Frame protocol: %s\\n\", parser.mMostSpecificProtocolString);  //  协议类型\n" +
                    "\tpw.format(\"Frame protocol type: %s\\n\", parser.mTypeString);  //  协议子类型\n" +
                    "\tpw.format(\"Frame length: %d\\n\", mFrameBytes.length);  // 帧的长度\n" +
                    "\tpw.append(\"Frame bytes\");\n" +
                    "\tpw.append(HexDump.dumpHexString(mFrameBytes));  // byte[] mFrameBytes  把byte[] 数据的二进制打印出来   potentially contains PII \n" +
                    "\tpw.append(\"\\n\");\n" +
                    "\treturn sw.toString();\n" +
                    "}\n" +
                    "\n" +
                    "【 dumpPacketFatesInternal 】\n" +
                    "void dumpPacketFatesInternal(PrintWriter pw, String description, ArrayList<WifiNative.FateReport> fates, boolean verbose) {\n" +
                    "        if (fates == null) {     //  数据帧列表为空\n" +
                    "            pw.format(\"No fates fetched for \\\"%s\\\"\\n\", description);\n" +
                    "            return;\n" +
                    "        }\n" +
                    "\n" +
                    "        if (fates.size() == 0) {  //  数据帧列表长度为 0 \n" +
                    "            pw.format(\"HAL provided zero fates for \\\"%s\\\"\\n\", description);\n" +
                    "            return;\n" +
                    "        }\n" +
                    "\n" +
                    "\t\t// --------------------- Latest fates ----------------------\n" +
                    "\t\t// --------------------- Last failed connection fates ----------------------\n" +
                    "        pw.format(\"--------------------- %s ----------------------\\n\", description);\n" +
                    "\n" +
                    "        StringBuilder verboseOutput = new StringBuilder();\n" +
                    "        pw.print(WifiNative.FateReport.getTableHeader());\n" +
                    "        for (WifiNative.FateReport fate : fates) {\n" +
                    "            pw.print(fate.toTableRowString());\n" +
                    "            if (verbose) {\n" +
                    "                // Important: only print Personally Identifiable Information (PII) if verbose\n" +
                    "                // logging is turned on.\n" +
                    "                verboseOutput.append(fate.toVerboseStringWithPiiAllowed()); // 打印出  FateReport 的 可读交互信息\n" +
                    "                verboseOutput.append(\"\\n\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        if (verbose) {\n" +
                    "            pw.format(\"\\n>>> VERBOSE PACKET FATE DUMP <<<\\n\\n\");\n" +
                    "            pw.print(verboseOutput.toString());   //  只有打印verbose  才能打印 FateReport的信息\n" +
                    "        }\n" +
                    "// 打印 ArrayList<WifiNative.FateReport> 数组结束\n" +
                    "        pw.println(\"--------------------------------------------------------------------\");  \n" +
                    "    }\n" +
                    "\t\n" +
                    "【 WifiDiagnostics.dump 】public synchronized void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "dumpPacketFates(pw);\n" +
                    "}\n" +
                    "    private void dumpPacketFates(PrintWriter pw) {\n" +
                    "\t// Last failed connection fates // ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure; 本地保存的交互帧\n" +
                    "dumpPacketFatesInternal(pw, \"Last failed connection fates\", mPacketFatesForLastFailure,isVerboseLoggingEnabled());\n" +
                    "    // 调用时 直接从 WifiNative 拿到的   32个接收包   32个发送包 的64个包数组   fetchPacketFates() =【ArrayList<WifiNative.FateReport>】\n" +
                    "dumpPacketFatesInternal(pw, \"Latest fates\", fetchPacketFates(), isVerboseLoggingEnabled());\n" +
                    "    }\t\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "LOG列表:\n" +
                    "No fates fetched for \"Latest fatess\"   = : WifiDiagnostics. ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure 为空\n" +
                    "HAL provided zero fates for \"Latest fatess\" = : WifiDiagnostics. ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure 长度为0 \n" +
                    "--------------------- Latest fates ---------------------- =  打印(32个最新保存的 FateReport 包)WifiDiagnostics. ArrayList<WifiNative.FateReport> mPacketFatesForLastFailure\n" +
                    "\n" +
                    "No fates fetched for \"Last failed connection fates\"   = :  WifiDiagnostics 从WifiNative得到的FateReport数组为空\n" +
                    "HAL provided zero fates for \"Last failed connection fates\" = : WifiDiagnostics从WifiNative得到的FateReport数组 长度为0 \n" +
                    "--------------------- Last failed connection fates ---------------------- 打印调用WifiNative得到的(64个最新保存的 FateReport(32个发送包_32个接收包)WifiDiagnostics.fetchPacketFates()=ArrayList<WifiNative.FateReport>(64) \n" +
                    "Frame direction: RX   // Frame direction:  打印 RX 接收    WifiNative.FateReport.directionToString() 返回 RX\n" +
                    "Frame direction: TX   // Frame direction:  打印  TX发送    WifiNative.FateReport.directionToString() 返回 TX\n" +
                    "Frame timestamp:  // 帧内的时间戳   FateReport.mDriverTimestampUSec   WifiDiagnostics.list<(WifiNative.FateReport)>   \n" +
                    "Frame fate:      // RX 和 TX  对应的帧的type  // FateReport.byte mFate;  对应的 RX  或 TX 类型\n" +
                    "Frame type: 802.11 management  ||   Frame type: data  || Frame type: unknown    // FateReport.byte mFrameType;   Frame type: 三种  \"802.11 management\"   \"data\" \"unknown\"\n" +
                    "Frame protocol: ??  //  协议类型  new FrameParser(mFrameType 【byte】, mFrameBytes 【byte[]】).mMostSpecificProtocolString\n" +
                    "Frame protocol type: ?? //  协议子类型 new FrameParser(mFrameType 【byte】, mFrameBytes 【byte[]】).mTypeString\n" +
                    "Frame length: 64,   // 帧的长度 mFrameBytes.size()  //    FateReport byte[] mFrameBytes; \n" +
                    "Frame bytes   // 帧的byte[] 数组打印  打印 byte[] mFrameBytes;     FrameParser.java 将另外写关注 \n" +
                    "pw.append(HexDump.dumpHexString(mFrameBytes));  // 打印帧的字节数据  byte[] mFrameBytes  把byte[] 数据的二进制打印出来   potentially contains PII \n" +
                    "\t\n" +
                    "结束LOG1:   >>> VERBOSE PACKET FATE DUMP <<<\n" +
                    "结束LOG2:  搜索 【 last-mile log  】";
            keyWordList.add(wifi_19_29);



            KeyWordItem wifi_19_30 = new KeyWordItem();
            wifi_19_30.keyword = "-------------------------- Last failed last-mile log ---------------------------";
            wifi_19_30.explain=" WifiDiagnostics.LastMileLogger 提供用于捕获与Wifi控制和数据路径相关的内核跟踪事件的工具。 \n Provides a facility for capturing kernel trace events related to Wifi control and data paths.  ";
            wifi_19_30.classNameForShuxing = "WifiServiceImpl.java -> ClientModeImpl.java ->【 WifiDiagnostics 】 BaseWifiDiagnostics  mWifiDiagnostics 】 ->LastMileLogger  ";
            wifi_19_30.shuxingDefine=" class LastMileLogger {}    ";
            wifi_19_30.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiDiagnostics.java ->LastMileLogger  )  dump(fd, pw, args){ }; ";
            wifi_19_30.expain1="   // 三种PATH 路径 分别得到不同的数据\n" +
                    "   static final String WIFI_EVENT_BUFFER_PATH =\"/sys/kernel/debug/tracing/instances/wifi/trace\";\n" +
                    "xiaomiMix:/ # cat /sys/kernel/debug/tracing/instances/wifi/trace   // 路径打印如下Log字符串  【如果连接上wifi 将打印很多的数据】\n" +
                    "# tracer: nop\n" +
                    "#\n" +
                    "# entries-in-buffer/entries-written: 0/0   #P:8\n" +
                    "#\n" +
                    "#                              _-----=> irqs-off\n" +
                    "#                             / _----=> need-resched\n" +
                    "#                            | / _---=> hardirq/softirq\n" +
                    "#                            || / _--=> preempt-depth\n" +
                    "#                            ||| /     delay\n" +
                    "#           TASK-PID   CPU#  ||||    TIMESTAMP  FUNCTION\n" +
                    "#              | |       |   ||||       |         |\n" +
                    "\n" +
                    "static final String WIFI_EVENT_ENABLE_PATH = \"/sys/kernel/debug/tracing/instances/wifi/tracing_on\"; //0-不追踪-EventLog  1-追踪-EventLog\n" +
                    "\n" +
                    "static final String WIFI_EVENT_RELEASE_PATH = \"/sys/kernel/debug/tracing/instances/wifi/free_buffer\";\n" +
                    "\"  cd /sys/kernel/debug/tracing/instances/wifi/events/cfg80211 \" 水好深   另外分析\n" +
                    "\"cd  /sys/kernel/debug/tracing/instances/wifi/  \"   目录对应文件:\n" +
                    ":/sys/kernel/debug/tracing/instances/wifi # ls -l\n" +
                    "total 0\n" +
                    "-r--r--r--  1 root   root 0 2019-11-21 22:35 available_tracers\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 buffer_size_kb\n" +
                    "-r--r--r--  1 root   root 0 2019-11-21 22:35 buffer_total_size_kb\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 current_tracer\n" +
                    "drwxr-xr-x 99 root   root 0 2019-11-21 22:35 events\n" +
                    "-r--------  1 system root 0 2019-11-21 22:35 free_buffer\n" +
                    "drwxr-xr-x  2 root   root 0 2019-11-21 22:35 options\n" +
                    "drwxr-xr-x 10 root   root 0 2019-11-21 22:35 per_cpu\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_event\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_event_pid\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_ftrace_filter\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_ftrace_notrace\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_ftrace_pid\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 snapshot\n" +
                    "-rw-------  1 system root 0 2019-11-21 22:35 trace\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 trace_clock\n" +
                    "--w--w----  1 root   root 0 2019-11-21 22:35 trace_marker\n" +
                    "--w--w----  1 root   root 0 2019-11-21 22:35 trace_marker_raw\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 trace_options\n" +
                    "-r--r--r--  1 root   root 0 2019-11-21 22:35 trace_pipe\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 tracing_cpumask\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 tracing_max_latency\n" +
                    "--w-------  1 system root 0 2019-11-22 01:54 tracing_on\n" +
                    "\n" +
                    "public class LastMileLogger {\n" +
                    "\n" +
                    "byte[] mLastMileLogForLastFailure;\n" +
                    "\n" +
                    "   static final String WIFI_EVENT_BUFFER_PATH =\"/sys/kernel/debug/tracing/instances/wifi/trace\";\n" +
                    "   \n" +
                    "    public void dump(PrintWriter pw) {\n" +
                    "        dumpInternal(pw, \"Last failed last-mile log\", mLastMileLogForLastFailure);  // Last failed last-mile log 打印当前保存的字节数组  byte[] mLastMileLogForLastFailure; \n" +
                    "        dumpInternal(pw, \"Latest last-mile log\", readTrace());\n" +
                    "    }\n" +
                    "\t\n" +
                    "   byte[] readTrace() {\n" +
                    "        try {\n" +
                    "            return IoUtils.readFileAsByteArray(mEventBufferPath);  // 打印 \" /sys/kernel/debug/tracing/instances/wifi/trace \" 的数据\n" +
                    "        } catch (IOException e) {\n" +
                    "            mLog.warn(\"Failed to read event trace: %\").r(e.getMessage()).flush();\n" +
                    "            return new byte[0];\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            KeyWordItem wifi_19_31 = new KeyWordItem();
            wifi_19_31.keyword = "-------------------------- Latest last-mile log ---------------------------";
            wifi_19_31.explain=" WifiDiagnostics.LastMileLogger 提供用于捕获与Wifi控制和数据路径相关的内核跟踪事件的工具。 \n Provides a facility for capturing kernel trace events related to Wifi control and data paths.  ";
            wifi_19_31.classNameForShuxing = "WifiServiceImpl.java -> ClientModeImpl.java ->【 WifiDiagnostics 】 BaseWifiDiagnostics  mWifiDiagnostics 】 ->LastMileLogger  ";
            wifi_19_31.shuxingDefine=" class LastMileLogger {}    ";
            wifi_19_31.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiDiagnostics.java ->LastMileLogger  )  dump(fd, pw, args){ }; ";
            wifi_19_31.expain1="   // 三种PATH 路径 分别得到不同的数据\n" +
                    "   static final String WIFI_EVENT_BUFFER_PATH =\"/sys/kernel/debug/tracing/instances/wifi/trace\";\n" +
                    "xiaomiMix:/ # cat /sys/kernel/debug/tracing/instances/wifi/trace   // 路径打印如下Log字符串  【如果连接上wifi 将打印很多的数据】\n" +
                    "# tracer: nop\n" +
                    "#\n" +
                    "# entries-in-buffer/entries-written: 0/0   #P:8\n" +
                    "#\n" +
                    "#                              _-----=> irqs-off\n" +
                    "#                             / _----=> need-resched\n" +
                    "#                            | / _---=> hardirq/softirq\n" +
                    "#                            || / _--=> preempt-depth\n" +
                    "#                            ||| /     delay\n" +
                    "#           TASK-PID   CPU#  ||||    TIMESTAMP  FUNCTION\n" +
                    "#              | |       |   ||||       |         |\n" +
                    "\n" +
                    "static final String WIFI_EVENT_ENABLE_PATH = \"/sys/kernel/debug/tracing/instances/wifi/tracing_on\"; //0-不追踪-EventLog  1-追踪-EventLog\n" +
                    "\n" +
                    "static final String WIFI_EVENT_RELEASE_PATH = \"/sys/kernel/debug/tracing/instances/wifi/free_buffer\";\n" +
                    "\"  cd /sys/kernel/debug/tracing/instances/wifi/events/cfg80211 \" 水好深   另外分析\n" +
                    "\"cd  /sys/kernel/debug/tracing/instances/wifi/  \"   目录对应文件:\n" +
                    ":/sys/kernel/debug/tracing/instances/wifi # ls -l\n" +
                    "total 0\n" +
                    "-r--r--r--  1 root   root 0 2019-11-21 22:35 available_tracers\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 buffer_size_kb\n" +
                    "-r--r--r--  1 root   root 0 2019-11-21 22:35 buffer_total_size_kb\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 current_tracer\n" +
                    "drwxr-xr-x 99 root   root 0 2019-11-21 22:35 events\n" +
                    "-r--------  1 system root 0 2019-11-21 22:35 free_buffer\n" +
                    "drwxr-xr-x  2 root   root 0 2019-11-21 22:35 options\n" +
                    "drwxr-xr-x 10 root   root 0 2019-11-21 22:35 per_cpu\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_event\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_event_pid\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_ftrace_filter\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_ftrace_notrace\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 set_ftrace_pid\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 snapshot\n" +
                    "-rw-------  1 system root 0 2019-11-21 22:35 trace\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 trace_clock\n" +
                    "--w--w----  1 root   root 0 2019-11-21 22:35 trace_marker\n" +
                    "--w--w----  1 root   root 0 2019-11-21 22:35 trace_marker_raw\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 trace_options\n" +
                    "-r--r--r--  1 root   root 0 2019-11-21 22:35 trace_pipe\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 tracing_cpumask\n" +
                    "-rw-r--r--  1 root   root 0 2019-11-21 22:35 tracing_max_latency\n" +
                    "--w-------  1 system root 0 2019-11-22 01:54 tracing_on\n" +
                    "\n" +
                    "public class LastMileLogger {\n" +
                    "\n" +
                    "byte[] mLastMileLogForLastFailure;\n" +
                    "\n" +
                    "   static final String WIFI_EVENT_BUFFER_PATH =\"/sys/kernel/debug/tracing/instances/wifi/trace\";\n" +
                    "   \n" +
                    "    public void dump(PrintWriter pw) {\n" +
                    "        dumpInternal(pw, \"Last failed last-mile log\", mLastMileLogForLastFailure);  // Last failed last-mile log 打印当前保存的字节数组  byte[] mLastMileLogForLastFailure; \n" +
                    "        dumpInternal(pw, \"Latest last-mile log\", readTrace());\n" +
                    "    }\n" +
                    "\t\n" +
                    "   byte[] readTrace() {\n" +
                    "        try {\n" +
                    "            return IoUtils.readFileAsByteArray(mEventBufferPath);  // 打印 \" /sys/kernel/debug/tracing/instances/wifi/trace \" 的数据\n" +
                    "        } catch (IOException e) {\n" +
                    "            mLog.warn(\"Failed to read event trace: %\").r(e.getMessage()).flush();\n" +
                    "            return new byte[0];\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";


            KeyWordItem wifi_19_32 = new KeyWordItem();
            wifi_19_32.keyword = "IpClient";
            wifi_19_32.explain=" 打印 IpClient 相关信息  但是 目前IpClient 已经移动到  adb shell dumpsys netstats 中 ";
            wifi_19_32.classNameForShuxing = "volatile IpClientManager mIpClient; ";
            wifi_19_32.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java ->  IpClientManager ) dumpIpClient(fd, pw, args);\n ";
            wifi_19_32.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> )  dump(fd, pw, args){ IpClientManager.toString() }; ";
            keyWordList.add(wifi_19_32);



            KeyWordItem wifi_19_33 = new KeyWordItem();
            wifi_19_33.keyword = "WifiConnectivityManager - Log Begin ----";
            wifi_19_33.explain=" WifiConnectivityManager WIFI连接管理  ";
            wifi_19_33.classNameForShuxing = " WifiConnectivityManager mWifiConnectivityManager; ";
            wifi_19_33.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java ->  WifiConnectivityManager ) WifiConnectivityManager  mWifiConnectivityManager ";
            wifi_19_33.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> )  dump(fd, pw, args){  mWifiConnectivityManager.dump(fd, pw, args);}; ";
            wifi_19_33.expain1="********************* LocalLog Begin *********************\n" +
                    "public final class LocalLog {\n" +
                    "    private final Deque<String> mLog;\n" +
                    "    private final int mMaxLines;\n" +
                    "\t\n" +
                    "\tpublic synchronized void dump(PrintWriter pw) {  // 遍历ArrayList<String> 并打印\n" +
                    "\tIterator<String> itr = mLog.iterator();\n" +
                    "\twhile (itr.hasNext()) {\n" +
                    "\t\tpw.println(itr.next());\n" +
                    "\t}\n" +
                    "********************* LocalLog End *********************\n" +
                    " class WifiConnectivityManager {\n" +
                    " final LocalLog mLocalLog;  // LocalLog?  就是一个 ArrayList<String> \n" +
                    "     public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        pw.println(\"Dump of WifiConnectivityManager\");\n" +
                    "        pw.println(\"WifiConnectivityManager - Log Begin ----\");\n" +
                    "        mLocalLog.dump(fd, pw, args);\n" +
                    "        pw.println(\"WifiConnectivityManager - Log End ----\");\n" +
                    "        mOpenNetworkNotifier.dump(fd, pw, args);\n" +
                    "        mCarrierNetworkNotifier.dump(fd, pw, args);\n" +
                    "        mCarrierNetworkConfig.dump(fd, pw, args);\n" +
                    "    }\n" +
                    " }\n" +
                    " 开始Log:  【 WifiConnectivityManager - Log Begin ---- 】\n" +
                    " 打印一个 Deque<String>   应该是记录了与Wifi相关的日志  就是函数调用 localLog(\"xxx\"); 保存信息的位置\n" +
                    " 结束Log:  【 WifiConnectivityManager - Log End ---- 】";
            keyWordList.add(wifi_19_33);



            KeyWordItem wifi_19_34 = new KeyWordItem();
            wifi_19_34.keyword = "NumActiveModeManagers:";
            wifi_19_34.isContain = true;
            wifi_19_34.explain=" PasspointManager 从SIM卡得到 mccmnc的类  ";
            wifi_19_34.classNameForShuxing = " public class CarrierInfo {} ";
            wifi_19_34.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java -> PasspointManager.java ) CarrierIno mCarrierIno\n ";
            wifi_19_34.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConfigManager.java -> PasspointManager.java  )  dump(fd, pw, args){ mCarrierIno.toString() }; ";
            keyWordList.add(wifi_19_34);



            KeyWordItem wifi_19_35 = new KeyWordItem();
            wifi_19_35.keyword = "PNO settings:";
            wifi_19_35.isContain = true;
            wifi_19_35.explain=" WifiConnectivityManager 构造函数打印Log  ";
            wifi_19_35.classNameForShuxing = "class WifiConnectivityManager {} ";
            wifi_19_35.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java ) WifiConnectivityManager(构造函数打印) ";
            wifi_19_35.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java ) WifiConnectivityManager(构造函数打印)";
            wifi_19_35.expain1="<!-- Integer specifying the basic autojoin parameters -->\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_5GHz_preference_boost_threshold\">-65</integer>\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_5GHz_preference_boost_factor\">40</integer>\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_5GHz_preference_penalty_threshold\">-75</integer>\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_RSSI_SCORE_OFFSET\">85</integer>\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_RSSI_SCORE_SLOPE\">4</integer>\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_SAME_BSSID_AWARD\">24</integer>\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_LAST_SELECTION_AWARD\">480</integer>\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_PASSPOINT_SECURITY_AWARD\">40</integer>\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_SECURITY_AWARD\">80</integer>\n" +
                    "\n" +
                    "class WifiConnectivityManager {\n" +
                    "\n" +
                    "final ScoringParams mScoringParams;\n" +
                    "\n" +
                    "int mCurrentConnectionBonus;  //  分数相关的增加分数数据  当前连接的网络分数增加  16\n" +
                    "int mSameNetworkBonus;  // 有相同网络的ssid 不同 bssid的网络 增加的分数  24\n" +
                    "int mSecureBonus;   // 安全方面的分数  80  \n" +
                    "\n" +
                    "int mRssiScoreOffset;   // 85  信号计算分数时的 前移量 \n" +
                    "int mRssiScoreSlope;  // 4   RSSI的wifi信号档数  \n" +
                    "\t\t\n" +
                    "WifiConnectivityManager(){\n" +
                    "mCurrentConnectionBonus = context.getResources().getInteger(R.integer.config_wifi_framework_current_network_boost);  // Integer indicating RSSI boost given to current network\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_current_network_boost\">16</integer>  // 给整个网络升压 rssi的值?\n" +
                    "\n" +
                    "mSameNetworkBonus = context.getResources().getInteger(R.integer.config_wifi_framework_SAME_BSSID_AWARD);\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_SAME_BSSID_AWARD\">24</integer>   // 自动加入的参数?  <!-- Integer specifying the basic autojoin parameters -->\n" +
                    "\n" +
                    "mSecureBonus = context.getResources().getInteger(R.integer.config_wifi_framework_SECURITY_AWARD);\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_SECURITY_AWARD\">80</integer>\n" +
                    "\n" +
                    "\n" +
                    "mRssiScoreOffset = context.getResources().getInteger(R.integer.config_wifi_framework_RSSI_SCORE_OFFSET);\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_RSSI_SCORE_OFFSET\">85</integer>\n" +
                    "\n" +
                    "mRssiScoreSlope = context.getResources().getInteger(R.integer.config_wifi_framework_RSSI_SCORE_SLOPE);\n" +
                    "<integer translatable=\"false\" name=\"config_wifi_framework_RSSI_SCORE_SLOPE\">4</integer>\n" +
                    "\n" +
                    "int initialScoreMax() {  // 【 112 】 初始化最大的分数\n" +
                    "return mRssiScoreSlope【4】 * (Math.max(mScoringParams.getGoodRssi(ScoringParams.BAND2)【-60】, mScoringParams.getGoodRssi(ScoringParams.BAND5))【-57】 + mRssiScoreOffset【85】);\n" +
                    "【4】*【28】 = 【112】\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "private static final int EXIT = 0;     // 低于这个信号 2.4G[ rssi=-83]  5G[-80]  标识离开了该网络范围\n" +
                    "private static final int ENTRY = 1;    //   能添加到网络的信号档数\n" +
                    "private static final int SUFFICIENT = 2; // 信号一般的档数 \n" +
                    "private static final int GOOD = 3;   // 信号很好的档数\n" +
                    "\t\n" +
                    "/** RSSI thresholds for 2.4 GHz band (dBm) */ 2.4G下四挡rssi信号\n" +
                    "public static final String KEY_RSSI2 = \"rssi2\";\n" +
                    "public final int[] rssi2 = {-83【0】, -80【1】, -73【2】, -60【3】};\n" +
                    "\t\t\n" +
                    "/** RSSI thresholds for 5 GHz band (dBm) */ 5G下四挡rssi信号\n" +
                    "public static final String KEY_RSSI5 = \"rssi5\";\n" +
                    "public final int[] rssi5 = {-80【0】, -77【1】, -70【2】, -57【3】};  \n" +
                    "\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    " localLog(\"PNO settings:\"\n" +
                    "\t\t+ \" min5GHzRssi \" + mScoringParams.getEntryRssi(ScoringParams.BAND5【5000】)  // min5GHzRssi:  5G 第二档最低信号强度 【-77】  final int BAND5 = 5000;   /** Constant to denote someplace in the 5 GHz band */\n" +
                    "\t\t+ \" min24GHzRssi \" + mScoringParams.getEntryRssi(ScoringParams.BAND2【2400】) //min24GHzRssi:  2.4G 第二档最低信号强度 【-80 】  final int BAND2 = 2400;  /** Constant to denote someplace in the 2.4 GHz band */\n" +
                    "\t\t+ \" currentConnectionBonus \" + mCurrentConnectionBonus   //currentConnectionBonus 【16】  当前连接的网络的加分项分 \n" +
                    "\t\t+ \" sameNetworkBonus \" + mSameNetworkBonus  // sameNetworkBonus 【24】 有多ssid 的加分项分 \n" +
                    "\t\t+ \" secureNetworkBonus \" + mSecureBonus  //  secureNetworkBonus  【 80 】  安全加分项分\n" +
                    "\t\t+ \" initialScoreMax \" + initialScoreMax());  //   initialScoreMax 【 112 】 初始化最大的分数\n" +
                    "\n" +
                    "\n" +
                    "}\n" +
                    "示例Log: \n" +
                    "2019-11-15T16:23:55.261 - PNO settings: min5GHzRssi -77 min24GHzRssi -80 currentConnectionBonus 16 sameNetworkBonus 24 secureNetworkBonus 80 initialScoreMax 112\n";
            keyWordList.add(wifi_19_35);





            KeyWordItem wifi_19_36 = new KeyWordItem();
            wifi_19_36.keyword = "mNetworkRecommendationsEnabled:";
            wifi_19_36.isContain = true;
            wifi_19_36.explain=" mNetworkRecommendationsEnabled  代表是否对网络进行评估  如果为false 打印: Skipping evaluateNetworks; Network recommendations disabled. ";
            wifi_19_36.classNameForShuxing = "class ScoredNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {} ";
            wifi_19_36.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java -> WifiNetworkSelector.java )  【 mEvaluators 】 List<NetworkEvaluator> ";
            wifi_19_36.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java -> WifiNetworkSelector.java )";
            wifi_19_36.expain1 = "public interface NetworkEvaluator {\n" +
                    "//  Type of evaluators 评估的类型\n" +
                    "int EVALUATOR_ID_SAVED = 0;\n" +
                    "int EVALUATOR_ID_SUGGESTION = 1;\n" +
                    "int EVALUATOR_ID_PASSPOINT = 2;\n" +
                    "int EVALUATOR_ID_CARRIER = 3;\n" +
                    "int EVALUATOR_ID_SCORED = 4;\n" +
                    "\n" +
                    "int getId(); // 返回当前评估类对应专业评估的哪个网络  Get the evaluator type.\n" +
                    "String getName();  //  获取评估类的 名称 \n" +
                    "void update(List<ScanDetail> scanDetails);  // 更新需要评估类评估的网络\n" +
                    "WifiConfiguration evaluateNetworks(xxx)  // 评估的结果 , 从扫描结果返回一个对应的扫描对应的 WifiConfiguration,如果为空 说明 都没看上\n" +
                    "xxx(List<ScanDetail> ,currentNetwork, String currentBssid,boolean connected, boolean untrustedNetworkAllowed,OnConnectableListener);\n" +
                    "}\n" +
                    "  \n" +
                    "对应的实现类：\n" +
                    "class ScoredNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class SavedNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class NetworkSuggestionEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class PasspointNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "\n" +
                    "class WifiNetworkSelector {\n" +
                    " List<NetworkEvaluator> mEvaluators = new ArrayList<>(3);  // 网络评估实现类 \n" +
                    "}";
            keyWordList.add(wifi_19_36);


            KeyWordItem wifi_19_37 = new KeyWordItem();
            wifi_19_37.keyword = "scanImmediately=true";
            wifi_19_37.isContain = true;
            wifi_19_37.explain=" 立即进行连接扫描标识   WifiConnectivityManager.startConnectivityScan(bool 立即扫描标识) " ;
            wifi_19_37.classNameForShuxing = "class WifiConnectivityManager {} ";
            wifi_19_37.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java  ) void startConnectivityScan(boolean scanImmediately) ";
            wifi_19_37.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java ->  startConnectivityScan()   )";
            wifi_19_37.expain1="class WifiConnectivityManager {\n" +
                    "\n" +
                    "// 开启连接扫描 , 扫描方法依据当前屏幕的状态和当前的wifi状态\t // the current screen state and WiFi state.\t\t  \n" +
                    "// Start a connectivity scan. The scan method is chosen according to\n" +
                    "\n" +
                    "boolean mScreenOn = false;\n" +
                    "int mWifiState = WIFI_STATE_UNKNOWN;  // WifiConnectivityManager 只关系这几种状态 \n" +
                    "final int WIFI_STATE_UNKNOWN = 0;   // 初始化状态\n" +
                    "final int WIFI_STATE_CONNECTED = 1;  // 连接状态\n" +
                    "final int WIFI_STATE_DISCONNECTED = 2;  // 断开状态\n" +
                    "final int WIFI_STATE_TRANSITIONING = 3;  // 连接状态-连接状态 之间的过渡状态\n" +
                    "\t\n" +
                    "boolean mWifiEnabled = false;     // wifi 是否打开\n" +
                    "boolean mWifiConnectivityManagerEnabled = false;\n" +
                    "\n" +
                    "private void startConnectivityScan(boolean scanImmediately) {\n" +
                    "localLog(\"startConnectivityScan: screenOn=\" + mScreenOn    // boolean mScreenOn 屏幕状态\n" +
                    "+ \" wifiState=\" + stateToString(mWifiState)      //int mWifiState , wifi 状态 0-UNKNOWN 1-CONNECTED 2-DISCONNECTED  3-TRANSITIONING\n" +
                    "+ \" scanImmediately=\" + scanImmediately   //  是否立即扫描 \n" +
                    "+ \" wifiEnabled=\" + mWifiEnabled     // \n" +
                    "+ \" wifiConnectivityManagerEnabled=\"  // boolean mWifiConnectivityManagerEnabled  , WifiConnectivityManager 的 使能标识 flag\n" +
                    "+ mWifiConnectivityManagerEnabled);\n" +
                    "}";
            keyWordList.add(wifi_19_37);




            KeyWordItem wifi_19_38 = new KeyWordItem();
            wifi_19_38.keyword = "onResults: start network selection";
            wifi_19_38.isContain = true;
            wifi_19_38.explain="对扫描结果进行评估,返回为false 标识没有候选被选中  true则表示有候选网络被选中   \n boolean handleScanResults(List<ScanDetail> scanDetails, String listenerName) " ;
            wifi_19_38.classNameForShuxing = "class WifiConnectivityManager {} ";
            wifi_19_38.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java  )  boolean handleScanResults(List<ScanDetail> scanDetails) ";
            wifi_19_38.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java ->  boolean handleScanResults(List<ScanDetail> scanDetails)   )";
            wifi_19_38.expain1="class WifiConnectivityManager {\n" +
                    "boolean handleScanResults(List<ScanDetail> scanDetails, String listenerName) {\n" +
                    "....\n" +
                    "  localLog(listenerName + \" onResults: start network selection\");\n" +
                    "....\n" +
                    "}\n" +
                    "}";
            keyWordList.add(wifi_19_38);


            KeyWordItem wifi_19_39 = new KeyWordItem();
            wifi_19_39.keyword = "Networks filtered out due to";
            wifi_19_39.isContain = true;
            wifi_19_39.explain="对扫描结果进行评估,返回为false 标识没有候选被选中  true则表示有候选网络被选中   \n boolean handleScanResults(List<ScanDetail> scanDetails, String listenerName) " ;
            wifi_19_39.classNameForShuxing = "class WifiConnectivityManager {} ";
            wifi_19_39.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java => boolean handleScanResults() =>  【 WifiNetworkSelector 】mNetworkSelector.selectNetwork() =>(过滤质量底下的网络)【WifiNetworkSelector】 filterScanResults()";
            wifi_19_39.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.boolean handleScanResults(List<ScanDetail> scanDetails) -》 WifiNetworkSelector.selectNetwork() => WifiNetworkSelector.filterScanResults()  )";
            wifi_19_39.expain1="// 信号必需大于1 档 才能不被过滤  , 否则认为是 low signal strength\n" +
                    "public final int[] rssi2 = {-83【0】, -80【1】, -73【2】, -60【3】};\n" +
                    "public final int[] rssi5 = {-80【0】, -77【1】, -70【2】, -57【3】};  \t\t\n" +
                    "public boolean isSignalTooWeak(ScanResult scanResult) {\n" +
                    "return (scanResult.level < mScoringParams.getEntryRssi(scanResult.frequency));\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "List<ScanDetail> filterScanResults(List<ScanDetail> scanDetails, HashSet<String> bssidBlacklist, boolean isConnected, String currentBssid) {\n" +
                    "\t\t\t\t\n" +
                    "\t    ArrayList<NetworkKey> unscoredNetworks = new ArrayList<NetworkKey>();\n" +
                    "        List<ScanDetail> validScanDetails = new ArrayList<ScanDetail>();  // 无效的扫描列表\n" +
                    "\t\tStringBuffer blacklistedBssid = new StringBuffer();  // 黑名单的 bssid\n" +
                    "\t\tStringBuffer lowRssi = new StringBuffer(); // 低信号质量的 ScanDetail \n" +
                    "\t\tboolean scanResultsHaveCurrentBssid = false;  // 候选网络中是否有当前已经连接的网络\n" +
                    "\t\tStringBuffer noValidSsid = new StringBuffer();  // ssid为空的扫描 ScanDetail  信息\n" +
                    "\t        for (ScanDetail scanDetail : scanDetails) {\n" +
                    "            ScanResult scanResult = scanDetail.getScanResult();\n" +
                    "\n" +
                    "\t\t\tif (scanResult.BSSID.equals(currentBssid)) {\n" +
                    "                scanResultsHaveCurrentBssid = true;\n" +
                    "            }\n" +
                    "\t\t\t\n" +
                    "            if (TextUtils.isEmpty(scanResult.SSID)) {  // SSID为空的话  就把ScanDetail 放入到无效的扫描列表\n" +
                    "                noValidSsid.append(scanResult.BSSID).append(\" / \");\n" +
                    "                continue;\n" +
                    "            }\n" +
                    "\t\t\tString scanId = toScanId(scanResult);\n" +
                    "\t\t\tif (bssidBlacklist.contains(scanResult.BSSID)) {\n" +
                    "                blacklistedBssid.append(scanId).append(\" / \");  // 参数 HashSet<String> bssidBlacklist 那么放入到 字符Builder中\n" +
                    "                continue;\n" +
                    "            }\n" +
                    "\t\tif (isSignalTooWeak(scanResult)) {  //\n" +
                    "                lowRssi.append(scanId).append(\"(\")\n" +
                    "                    .append(scanResult.is24GHz() ? \"2.4GHz\" : \"5GHz\")\n" +
                    "                    .append(\")\").append(scanResult.level).append(\" / \");  // 打印出 SSID(2.4G,信号强度)/\n" +
                    "                continue;\n" +
                    "\t\t\t\t// Log示例: 5G-Test:18:64:72:5a:d9:34(5GHz)-93 / RX-Test:18:64:72:12:0d:92(5GHz)-89\n" +
                    "            }\n" +
                    "\t\tvalidScanDetails.add(scanDetail);  // 剩下的就作为过滤后的网络 ScanResult \n" +
                    "}\n" +
                    "\n" +
                    "        if (isConnected && !scanResultsHaveCurrentBssid) { // 当前连接的网络不在候选集合中  那么这次扫描可能有问题  跳过网络选择过程\n" +
                    "            localLog(\"Current connected BSSID \" + currentBssid + \" is not in the scan results.\"   + \" Skip network selection.\");\n" +
                    "            validScanDetails.clear();  // 把所有的过滤后的网络清空   然后返回的大小为0 \n" +
                    "            return validScanDetails;\n" +
                    "        }\n" +
                    "        if (noValidSsid.length() != 0) {   // invalid SSID: 打印无效 SSID 的 StringBuffer noValidSsid = new StringBuffer();  // ssid为空的扫描 ScanDetail  信息\n" +
                    "            localLog(\"Networks filtered out due to invalid SSID: \" + noValidSsid);\n" +
                    "        }\n" +
                    "        if (blacklistedBssid.length() != 0) {  // blacklist : StringBuffer blacklistedBssid = new StringBuffer();  // 黑名单的 bssid\n" +
                    "            localLog(\"Networks filtered out due to blacklist: \" + blacklistedBssid);\n" +
                    "        }\n" +
                    "        if (lowRssi.length() != 0) {  // low signal strength:  StringBuffer lowRssi = new StringBuffer(); // 低信号质量的 ScanDetail \n" +
                    "            localLog(\"Networks filtered out due to low signal strength: \" + lowRssi);\n" +
                    "        }\n" +
                    "        return validScanDetails;\n" +
                    "    }\n" +
                    "}";
            keyWordList.add(wifi_19_39);


            KeyWordItem wifi_19_40 = new KeyWordItem();
            wifi_19_40.keyword = "About to run";
            wifi_19_40.isContain = true;
            wifi_19_40.explain="开始使用 实现了接口 Interface WifiNetworkSelector.NetworkEvaluator {}的类 去进行我List<ScanResult>的评估 \n会去执行实现的接口 WifiConfiguration evaluateNetworks(List<ScanResult>) 方法" ;
            wifi_19_40.classNameForShuxing = "class WifiNetworkSelector {} ";
            wifi_19_40.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java => boolean handleScanResults() =>  【 WifiNetworkSelector 】mNetworkSelector.selectNetwork() ";
            wifi_19_40.printcode=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.boolean handleScanResults(List<ScanDetail> scanDetails) -》 WifiNetworkSelector.selectNetwork() )";
            wifi_19_40.expain1="NetworkEvaluator接口 对应的实现类：Interface WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class ScoredNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class SavedNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class NetworkSuggestionEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class PasspointNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "\n" +
                    "public WifiConfiguration selectNetwork(List<ScanDetail> scanDetails,\n" +
                    "\t\tHashSet<String> bssidBlacklist, WifiInfo wifiInfo,\n" +
                    "\t\tboolean connected, boolean disconnected, boolean untrustedNetworkAllowed) {\n" +
                    "        for (NetworkEvaluator registeredEvaluator : mEvaluators) {\n" +
                    "// Log 示例: About to run ScoredNetworkEvaluator\n" +
                    "// Log 示例: About to run SavedNetworkEvaluator\n" +
                    "// Log 示例: About to run NetworkSuggestionEvaluator\n" +
                    "// Log 示例: About to run PasspointNetworkEvaluator\n" +
                    "            localLog(\"About to run \" + registeredEvaluator.getName() + \" :\");\n" +
                    "\t\t\t// 执行实现接口的  evaluateNetworks  方法\n" +
                    "            WifiConfiguration choice = registeredEvaluator.evaluateNetworks( new ArrayList<>(mFilteredNetworks)xxx);\n" +
                    "\t\t\t}\n" +
                    "}";
            keyWordList.add(wifi_19_40);


            KeyWordItem wifi_19_41 = new KeyWordItem();
            wifi_19_41.keyword = "ScoredNetworkEvaluator did not see any good candidates.";
            wifi_19_41.isContain = true;
            wifi_19_41.explain="ScoredNetworkEvaluator 评估过程中没有合适的 候选网络 " ;
            wifi_19_41.classNameForShuxing = " class ScoredNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {} ";
            wifi_19_41.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java => boolean handleScanResults() =>  【 WifiNetworkSelector 】mNetworkSelector.selectNetwork() ->  ScoredNetworkEvaluator->【  evaluateNetworks(){  return getCandidateConfiguration() } 】\n";
            wifi_19_41.printcode=" 【 evaluateNetworks(){ 【 scoreTracker.trackUntrustedCandidate()   scoreTracker.trackUntrustedCandidate() 对网络进行评估】 return getCandidateConfiguration() } 】 \\n WifiConfiguration getCandidateConfiguration(){ locallog.(ScoredNetworkEvaluator did not see any good candidates)}\n ";
            wifi_19_41.expain1="class ScoreTracker {   // 用来计算最高分网络 /** Used to track the network with the highest score. */\n" +
                    "\n" +
                    "int mHighScore = WifiNetworkScoreCache.INVALID_NETWORK_SCORE =  Byte.MIN_VALUE;  // 默认最低分数 ; \n" +
                    "\n" +
                    "int mBestCandidateType = EXTERNAL_SCORED_NONE;  // 代表候选网络的类型\n" +
                    "final int EXTERNAL_SCORED_NONE = 0;   // \n" +
                    "final int EXTERNAL_SCORED_SAVED_NETWORK = 1;  // 候选网络是已保存的网络\n" +
                    "final int EXTERNAL_SCORED_UNTRUSTED_NETWORK = 2;  // 候选网络是不被信任的网络\n" +
                    "WifiConfiguration mEphemeralConfig;\n" +
                    "WifiConfiguration mSavedConfig;    \n" +
                    "ScanResult mScanResultCandidate;   // 扫描结果候选\n" +
                    "ScanDetail mScanDetailCandidate;  // 扫描详情\n" +
                    "\n" +
                    "getCandidateConfiguration 之前执行 trackExternallyScoredCandidate 得到  mBestCandidateType 的值 \n" +
                    "void trackExternallyScoredCandidate(ScanResult scanResult, WifiConfiguration config, boolean isCurrentNetwork) {\n" +
                    "Integer score = getNetworkScore(scanResult, isCurrentNetwork);  // 获取分数 \n" +
                    "\n" +
                    "// 1.分数不为空  并且 【  2.分数大于现已记录的最大分数 || ( 3.当前的记录类型是不信任的网络 && 当前的分数和最高分数一样) 】\n" +
                    "if (score != null && (score > mHighScore|| (mBestCandidateType == EXTERNAL_SCORED_UNTRUSTED_NETWORK && score == mHighScore))) {\n" +
                    "mHighScore = score;    // 记录最高分数的  各种 bean 数据 \n" +
                    "mSavedConfig = config;\n" +
                    "mScanResultCandidate = scanResult;\n" +
                    "mScanDetailCandidate = null;\n" +
                    "mBestCandidateType = EXTERNAL_SCORED_SAVED_NETWORK;\n" +
                    "mWifiConfigManager.setNetworkCandidateScanResult(config.networkId, scanResult, 0);\n" +
                    "debugLog(WifiNetworkSelector.toScanId(scanResult)+ \" becomes the new externally scored saved network candidate.\");\n" +
                    "}\t\n" +
                    "}\n" +
                    "\t\n" +
                    "\n" +
                    "        WifiConfiguration getCandidateConfiguration(OnConnectableListener onConnectableListener) {\n" +
                    "            int candidateNetworkId = WifiConfiguration.INVALID_NETWORK_ID;\n" +
                    "            switch (mBestCandidateType) {\n" +
                    "\n" +
                    "                case ScoreTracker.EXTERNAL_SCORED_NONE:\n" +
                    "                default:\n" +
                    "                    mLocalLog.log(\"ScoredNetworkEvaluator did not see any good candidates.\");\n" +
                    "                    break;\n" +
                    "            }\n" +
                    "\t\t\t// 依据ID 获取 候选网络的 配置\n" +
                    "            WifiConfiguration ans = mWifiConfigManager.getConfiguredNetwork(candidateNetworkId);\n" +
                    "            return ans;\n" +
                    "        }\n" +
                    "\t\t";
            keyWordList.add(wifi_19_41);



            KeyWordItem wifi_19_42 = new KeyWordItem();
            wifi_19_42.keyword = "BubbleFunScorer_v1 would choose";
            wifi_19_42.isContain = true;
            wifi_19_42.explain="WifiNetworkSelector 中所有的分数评估实现类的评估结果打印:  \n WifiNetworkSelector.Map<String, WifiCandidates.CandidateScorer> mCandidateScorers  ==> WifiCandidates.choise(CandidateScorer)" ;
            wifi_19_42.classNameForShuxing = " WifiNetworkSelector. Map<String, WifiCandidates.CandidateScorer> mCandidateScorers  ";
            wifi_19_42.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java => boolean handleScanResults() =>  \n 【 WifiNetworkSelector 】mNetworkSelector.selectNetwork() ->  ScoredNetworkEvaluator->【  evaluateNetworks(){  for( CandidateScorer , mCandidateScorers ){  wifiCandidates.choose(candidateScorer)【使用评估类计算分数】; } } 】\n";
            wifi_19_42.printcode=" WifiNetworkSelector.selectNetwork()==> localLog(id + chooses + networkId + \" score \" + choice.value + \"+/-\" + choice.err + \" expid \" + expid); ";
            wifi_19_42.expain1="class WifiCandidates {\n" +
                    "\n" +
                    "// Key key = new Key(key1, bssid, config.networkId);\n" +
                    "// The key used for tracking candidates, consisting of SSID, security type, BSSID, and network \n" +
                    "Map<Key, CandidateImpl> mCandidates = new ArrayMap<>();   //  当前所有 CandidateImpl 的集合Map Key 是 \n" +
                    "\n" +
                    "public static class Key {\n" +
                    "public final ScanResultMatchInfo matchInfo; // Contains the SSID and security type\n" +
                    "public final MacAddress bssid;\n" +
                    "public final int networkId;                 //  网络的 networkid network configuration id\n" +
                    "}\n" +
                    "\t\t\n" +
                    "\n" +
                    "    public interface Candidate {  // 代表一个 候选网络   Represents a connectable candidate.\n" +
                    "\tKey getKey();\n" +
                    "\tScanDetail getScanDetail();\n" +
                    "\t int getNetworkConfigId();\n" +
                    "\t boolean isOpenNetwork();\n" +
                    "\t  boolean isPasspoint();\n" +
                    "\t  boolean isEphemeral();\n" +
                    "\t  boolean isTrusted();\n" +
                    "\t  【WifiNetworkSelector.NetworkEvaluator.EvaluatorId】 int getEvaluatorId();\n" +
                    "\t  int getEvaluatorScore();\n" +
                    "\t   boolean isCurrentNetwork();\n" +
                    "\t   boolean isCurrentBssid();\n" +
                    "\t    // 1表示最近才使用的 网络 1.0 means the network was recently selected by the user or an app.  \n" +
                    "\t\t// 0表示最近不使用  0.0 means not recently selected by user or app.\n" +
                    "\t    double getLastSelectionWeight(); \n" +
                    "\t\t int getScanRssi();\n" +
                    "\t\t int getFrequency();\n" +
                    "\t\t // 从记分卡中获取统计信息   Gets statistics from the scorecard.\n" +
                    "\t\t WifiScoreCardProto.Signal getEventStatistics(WifiScoreCardProto.Event event);\n" +
                    "\t}\n" +
                    "\t\n" +
                    "    static class CandidateImpl implements Candidate {  // Candidate接口在同一个类中 实现?  WifiCandidates\n" +
                    "\t    static class CandidateImpl implements Candidate {\n" +
                    "        public final Key key;                   // SSID/sectype/BSSID/configId\n" +
                    "        public final ScanDetail scanDetail;\n" +
                    "        public final WifiConfiguration config;\n" +
                    "        // First evaluator to nominate this config\n" +
                    "        public final @WifiNetworkSelector.NetworkEvaluator.EvaluatorId int evaluatorId;\n" +
                    "        public final int evaluatorScore;        // Score provided by first nominating evaluator\n" +
                    "        public final double lastSelectionWeight; // Value between 0 and 1\n" +
                    "\n" +
                    "        private WifiScoreCard.PerBssid mPerBssid; // For accessing the scorecard entry\n" +
                    "        private final boolean mIsCurrentNetwork;\n" +
                    "        private final boolean mIsCurrentBssid;\n" +
                    "\t}\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t    public static class ScoredCandidate {\n" +
                    "        public final double value;  // ScoredCandidate 的 分数\n" +
                    "        public final double err;\n" +
                    "        public final Key candidateKey;\n" +
                    "\t\t}\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t  public interface CandidateScorer {   // 代表一个计算分数的接口   \n" +
                    "        String getIdentifier();   // 得分的方面类型  哪个方向得分了\n" +
                    "        ScoredCandidate scoreCandidates(@NonNull Collection<Candidate> group);  // 计算分数  相同ssid的一组候选\n" +
                    "        boolean userConnectChoiceOverrideWanted(); // 判断是什么连接逻辑  true-用户逻辑  false-传统逻辑\n" +
                    "    }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "class ScoreCardBasedScorer implements WifiCandidates.CandidateScorer {} 三种计分方式 \n" +
                    "class CompatibilityScorer implements WifiCandidates.CandidateScorer {}  // 性能计分\n" +
                    "class BubbleFunScorer implements WifiCandidates.CandidateScorer {  // 冒泡方式计分 rssi 是否安全 是否当前网络 是否最近使用过 是否5G\n" +
                    "final int BUBBLE_FUN_SCORER_DEFAULT_EXPID = 42598151;\n" +
                    "final double SECURITY_AWARD = 80.0;\n" +
                    "final double CURRENT_NETWORK_BOOST = 80.0;\n" +
                    "final double LOW_BAND_FACTOR = 0.3;\n" +
                    "final double TYPICAL_SCAN_RSSI_STD = 4.0;\n" +
                    "\t\n" +
                    "\t\n" +
                    "public String getIdentifier() { return \"BubbleFunScorer_v1\"; }\n" +
                    "public boolean userConnectChoiceOverrideWanted() {return true; }\n" +
                    "\n" +
                    "\n" +
                    " // - Math.exp[ 59.5 * 0.1 ]  = - e^5.95 = - 383.75\n" +
                    " // - Math.exp[ 60.5 * 0.1 ]  = - e^6.05 = - 424.11\n" +
                    " // - Math.exp[ 60 * 0.1 ]  = - e^6 = -403.42\n" +
                    " // - Math.exp[ 80 * 0.1 ]  = - e^8 = -2980.96\n" +
                    "  // - Math.exp[ 85 * 0.1 ]  = - e^8.5 = -4915\n" +
                    "    private static double unscaledShapeFunction(double rssi) {\n" +
                    "        return -Math.exp(-rssi * BELS_PER_DECIBEL); \n" +
                    "    }\n" +
                    "\t\n" +
                    "\t// -60  rssi =  -403.42 * 0.02  = -8.06\n" +
                    "\t// -80  rssi =  -2980.96 * 0.02 = -59.6\n" +
                    "    private static double shapeFunction(double rssi) {\n" +
                    "        return unscaledShapeFunction(rssi) * RESCALE_FACTOR 【0.02】;\n" +
                    "   }\n" +
                    "\n" +
                    "static final double BELS_PER_DECIBEL = 0.1;\n" +
                    "static final double RESCALE_FACTOR【value = 0.02】 = 100.0 / (unscaledShapeFunction(0.0)【-1】 - unscaledShapeFunction(-85.0)【- 4915】);\n" +
                    "\n" +
                    "ScoredCandidate scoreCandidate(Candidate candidate) {\n" +
                    "      final int rssi = candidate.getScanRssi();  // 获取信号强度  比如  -60\n" +
                    "      final int rssiEntryThreshold = mScoringParams.getEntryRssi(candidate.getFrequency());  // 获取2.4G[-80] 或者 5G[-77]的入门信号 比如[ -80 ]\n" +
                    "      double score【 value = 51.54 】 = shapeFunction(rssi)【-60( value = -8.06 )】 - shapeFunction(rssiEntryThreshold)【-80( value = -59.6 )】;\n" +
                    "      \n" +
                    "      // 如果分数是负数  那么把这些分数的负面扩大  使得分数较小 方便过滤\n" +
                    "      if (score < 0.0) score *= 10.0;   // If we are below the entry threshold, make the score more negative\n" +
                    "\t  // A recently selected network gets a large boost 如果最近连接过该网络  那么分数增加 80 \n" +
                    "      score += candidate.getLastSelectionWeight() * CURRENT_NETWORK_BOOST 【80】;       \n" +
                    "      \n" +
                    "      if (candidate.isCurrentNetwork()) {    // Hysteresis to prefer staying on the current network.\n" +
                    "\t  //  更倾向于保持在当前连接所以 当前的网络有一个额外的加分项\n" +
                    "          score += CURRENT_NETWORK_BOOST;    // 如果就是当前已连接的网络  那么分数再加 80 \n" +
                    "      \t}\n" +
                    "      \t\n" +
                    "      if (!candidate.isOpenNetwork()) {  score += SECURITY_AWARD; }  //  如果当前网络不是一个开放的网络 那么在安全方面 加 80分\n" +
                    "      // This is used to estimate the error     // The gain is approximately the derivative of shapeFunction at the given rssi\n" +
                    "      double gain 【value = 0.82 】= shapeFunction(rssi + 0.5)- shapeFunction(rssi - 0.5);  // 计算误差？\n" +
                    "      \n" +
                    "      // Prefer 5GHz when both are strong, but at the fringes, 2.4 might be better\n" +
                    "      // Typically the entry rssi is lower for the 2.4 band, which provides the fringe boost\n" +
                    "      if (candidate.getFrequency() < ScoringParams.MINIMUM_5GHZ_BAND_FREQUENCY_IN_MEGAHERTZ 【5000】) {\n" +
                    "          score *= LOW_BAND_FACTOR 【0.3】;   2.4GHz 频段    分数 = 分数 * 0.3  nima2.4G分数下降70%  5G分数不变 \n" +
                    "          gain *= LOW_BAND_FACTOR  【0.3】;\n" +
                    "      }\n" +
                    "      \n" +
                    "      return new ScoredCandidate(score, TYPICAL_SCAN_RSSI_STD * gain, candidate);\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "public ScoredCandidate scoreCandidates(@NonNull Collection<Candidate> group) {  // 把所有的 Collection<Candidate> 转为 ScoredCandidate 并\n" +
                    "\tScoredCandidate choice = ScoredCandidate.NONE;\n" +
                    "\tfor (Candidate candidate : group) {\n" +
                    "\t // 把  Candidate 转为 ScoredCandidate  并计算 double value ,得到value最大的那一个 \n" +
                    "\t\tScoredCandidate scoredCandidate = scoreCandidate(candidate);   // 计算每一个 Candidate 的分数\n" +
                    "\t\tif (scoredCandidate.value > choice.value) {\n" +
                    "\t\t\tchoice = scoredCandidate;\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "\t\t\treturn choice;\n" +
                    "}\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "classs  WifiNetworkSelector { \n" +
                    "final WifiScoreCard mWifiScoreCard;\n" +
                    "Map<String, WifiCandidates.CandidateScorer> mCandidateScorers = new ArrayMap<>();\n" +
                    "\n" +
                    "final int ID_PREFIX = 42;\n" +
                    "int ID_SUFFIX_MOD = 1_000_000;\n" +
                    "public static int experimentIdFromIdentifier(String id) {\n" +
                    "\tfinal int digits = (int) (((long) id.hashCode()) & Integer.MAX_VALUE) % ID_SUFFIX_MOD;\n" +
                    "\treturn ID_PREFIX * ID_SUFFIX_MOD + digits;\n" +
                    "}\n" +
                    "\t\n" +
                    " WifiConfiguration selectNetwork(List<ScanDetail> scanDetails, HashSet<String> bssidBlacklist, WifiInfo wifiInfo,\n" +
                    "            boolean connected, boolean disconnected, boolean untrustedNetworkAllowed) {\n" +
                    "\t\t\t\n" +
                    "\t\t\t  WifiCandidates wifiCandidates = new WifiCandidates(mWifiScoreCard);  // 依据 WifiScoreCard 创建 WifiCandidates\n" +
                    "\t\t\t  \n" +
                    "\t\t\t  // 拿到当前活跃的评审类 \n" +
                    "\t\t\t  final WifiCandidates.CandidateScorer activeScorer = getActiveCandidateScorer();\n" +
                    "\t\t\t  // 遍历计算候选的方法实现类 \n" +
                    "\t\t       for (WifiCandidates.CandidateScorer candidateScorer : mCandidateScorers.values()) {\n" +
                    "                  WifiCandidates.ScoredCandidate choice;\n" +
                    "                choice = wifiCandidates.choose(candidateScorer); // 适配当前计算方法得到的 \tWifiCandidates.ScoredCandidate\n" +
                    "\t\t\t\n" +
                    "      int networkId = choice.candidateKey == null? WifiConfiguration.INVALID_NETWORK_ID: choice.candidateKey.networkId;\n" +
                    "\t  String id = candidateScorer.getIdentifier();\n" +
                    "\t  int expid = experimentIdFromIdentifier(id);\n" +
                    "\tString chooses = \" would choose \";\n" +
                    "\tif (candidateScorer == activeScorer) {\n" +
                    "\t\tchooses = \" chooses \";\n" +
                    "\t\tlegacyOverrideWanted = candidateScorer.userConnectChoiceOverrideWanted();\n" +
                    "\t\tselectedNetworkId = networkId;\n" +
                    "\t}\n" +
                    "\t  localLog(id + chooses + networkId + \" score \" + choice.value + \"+/-\" + choice.err + \" expid \" + expid);\n" +
                    "//Log示例:   【BubbleFunScorer_v1 计算工具名称】 【 would choose (chooses)】 5【networkId】 score 65.89800639721017【choice.value】+/-0.1206968523887978【choice.err】 expid 42598151【expid唯一标识该计算候选方法类的hash值】\n" +
                    "//             CompatibilityScorer chooses 【 chooses (chooses) 当前是活跃的评审类 activeScorer 不打印 would choose   】 5 score 219.961+/-10.0 expid 42504592\n" +
                    "//             ScoreCardBasedScorer would choose 5 score 200.0+/-10.0 expid 42902385\n" +
                    "// 2019-11-15T17:30:56.928 - BubbleFunScorer_v1 would choose 5 score 65.89800639721017+/-0.1206968523887978 expid 42598151\n" +
                    "// 2019-11-15T17:30:56.929 - CompatibilityScorer chooses 5 score 219.961+/-10.0 expid 42504592\n" +
                    "// 2019-11-15T17:30:56.929 - ScoreCardBasedScorer would choose 5 score 200.0+/-10.0 expid 42902385\n" +
                    "}";
            keyWordList.add(wifi_19_42);


            KeyWordItem wifi_19_43 = new KeyWordItem();
            wifi_19_43.keyword = "the final candidate";
            wifi_19_43.isContain = true;
            wifi_19_43.explain="WifiNetworkSelector中 方法 selectNetwork() 调用 overrideCandidateWithUserConnectChoice() 并满足一些条件 使得它成为最终的候选项  " ;
            wifi_19_43.classNameForShuxing = " WifiNetworkSelector.overrideCandidateWithUserConnectChoice(WifiConfiguration )  ";
            wifi_19_43.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java => boolean handleScanResults() =>  \n 【 WifiNetworkSelector 】mNetworkSelector.selectNetwork() ->   overrideCandidateWithUserConnectChoice() \n";
            wifi_19_43.printcode=" localLog(\"After user selection adjustment, the final candidate is:\"+ WifiNetworkSelector.toNetworkString(candidate) + \" : \" + scanResultCandidate.BSSID);";
            wifi_19_43.expain1="public class WifiNetworkSelector {\n" +
                    "\n" +
                    "WifiConfiguration selectNetwork(List<ScanDetail> scanDetails...){\n" +
                    ".....\n" +
                    "else if (selectedNetwork != null && legacyOverrideWanted) { \n" +
                    "\tselectedNetwork = overrideCandidateWithUserConnectChoice(selectedNetwork);\n" +
                    "\tmLastNetworkSelectionTimeStamp = mClock.getElapsedSinceBootMillis();\n" +
                    "}\n" +
                    ".....\n" +
                    "}\n" +
                    "\t\t\n" +
                    "\n" +
                    "WifiConfiguration overrideCandidateWithUserConnectChoice(WifiConfiguration candidate) {  // 选择用户选择的网络 而不是评估出来的状元候选网络\n" +
                    "        WifiConfiguration tempConfig = Preconditions.checkNotNull(candidate);\n" +
                    "        WifiConfiguration originalCandidate = candidate;\n" +
                    "        boolean mSetItSelfBestChoice = false;\n" +
                    "\t\tScanResult scanResultCandidate = candidate.getNetworkSelectionStatus().getCandidate();\n" +
                    "      while (tempConfig.getNetworkSelectionStatus().getConnectChoice() != null) {\n" +
                    "\t  // WifiConfiguration.getNetworkSelectionStatus().getConnectChoice() 当前网络建议连接的网络的Key\n" +
                    "\t  // WifiConfiguration.configKey() 当前网络的Key   如果这两个Key 相同 那么说明 自己选择了自己\n" +
                    "\t\n" +
                    "\t   String key = tempConfig.getNetworkSelectionStatus().getConnectChoice();\n" +
                    "\t    if ( key.equals(tempConfig.configKey())) {\n" +
                    "        mSetItSelfBestChoice = true;\n" +
                    "        localLog(\"the preferred choice is itself,key=\"+key+\",tempConfig.SSID=\"+tempConfig.SSID);\n" +
                    "         }\n" +
                    "\t\t \n" +
                    "\t\t tempConfig = mWifiConfigManager.getConfiguredNetwork(key);\n" +
                    "\t\t if (tempConfig != null) {\n" +
                    "                WifiConfiguration.NetworkSelectionStatus tempStatus = tempConfig.getNetworkSelectionStatus();\n" +
                    "\t\t\t\t\n" +
                    " // isNetworkEnabled 选出的网络可用  mStatus == NETWORK_SELECTION_ENABLED=0 NETWORK_SELECTION_TEMPORARY_DISABLED=1; NETWORK_SELECTION_PERMANENTLY_DISABLED=2;\n" +
                    "// 【WifiConfiguration】 boolean isNetworkEnabled() { return mStatus == NETWORK_SELECTION_ENABLED; }\t\t\t\n" +
                    "\t\t\tif (tempStatus.getCandidate() != null && tempStatus.isNetworkEnabled()) {\n" +
                    "                    scanResultCandidate = tempStatus.getCandidate();\n" +
                    "/ 如果当前的网络配置 WifiConfiguration 中 有建议网络 WifiConfiguration.getNetworkSelectionStatus().getConnectChoice()【ConfigKey】 那么就去连接该网络\n" +
                    "                    candidate = tempConfig;\n" +
                    "                }\n" +
                    "\t  }else {\n" +
                    "        localLog(\"Connect choice: \" + key + \" has no corresponding saved config.\");  // 打印当前选择的网络 之前没有保存过 第一次遇到\n" +
                    "        break;\n" +
                    "      }\n" +
                    "\t if (mSetItSelfBestChoice){\n" +
                    "\t\tbreak;\n" +
                    "\t  }\n" +
                    "\t  } // 跳出 while 循环  \n" +
                    "\t  \n" +
                    "if (candidate != originalCandidate) {  \n" +
                    "// 如果当前候选项 经过遍历得到 可用的网络   和  原始的 参数【评估出来】WifiConfiguration candidate\n" +
                    "//  不是同一个   说明 用户可能遇到了在保存列表中并且可用的 网络 来替代【评估出来】WifiConfiguration candidate\n" +
                    "\tlocalLog(\"After user selection adjustment, the final candidate is:\"+ WifiNetworkSelector.toNetworkString(candidate) + \" : \" + scanResultCandidate.BSSID);\n" +
                    "// Log示例:  After user selection adjustment, the final candidate is:\"D-Link_DIR-816\":5 : 1c:5f:2b:5e:d5:53\t\n" +
                    "\tmWifiMetrics.setNominatorForNetwork(candidate.networkId, WifiMetricsProto.ConnectionEvent.NOMINATOR_SAVED_USER_CONNECT_CHOICE);\n" +
                    "}\n" +
                    "        return candidate;\n" +
                    "}";
            keyWordList.add(wifi_19_43);


            KeyWordItem wifi_19_44 = new KeyWordItem();
            wifi_19_44.keyword = "WNS candidate";
            wifi_19_44.isContain = true;
            wifi_19_44.explain="WifiConnectivityManager 最终选择的候选 WifiConfiguration , 将会执行 ConnectTo(WifiConfiguration) 连接操作   " ;
            wifi_19_44.classNameForShuxing = " boolean handleScanResults(List<ScanDetail> scanDetails, String listenerName) ";
            wifi_19_44.shuxingDefine="(WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java  )  boolean handleScanResults(List<ScanDetail> scanDetails) ";
            wifi_19_44.printcode=" localLog(listenerName + \":  WNS candidate-\" + candidate.SSID);";
            wifi_19_44.expain1="class WifiConnectivityManager {\n" +
                    "boolean handleScanResults(List<ScanDetail> scanDetails, String listenerName) {\n" +
                    " WifiConfiguration candidate =  mNetworkSelector.selectNetwork(scanDetails, buildBssidBlacklist(), mWifiInfo, mStateMachine.isConnected(), mStateMachine.isDisconnected(),\n" +
                    "......\n" +
                    " if (candidate != null) {\n" +
                    " // 最终选中的 将要 执行 连接操作的 WifiConfiguration\n" +
                    "\t\tlocalLog(listenerName + \":  WNS candidate-\" + candidate.SSID);\n" +
                    "\t\tconnectToNetwork(candidate);\n" +
                    "\t\treturn true;\n" +
                    "\t}\n" +
                    "}\n" +
                    "}";
            keyWordList.add(wifi_19_44);



            KeyWordItem wifi_19_45 = new KeyWordItem();
            wifi_19_45.keyword = "Too short since last network selection";
            wifi_19_45.isContain = true;
            wifi_19_45.explain=" " ;
            wifi_19_45.classNameForShuxing = " // WifiNetworkSelector.isNetworkSelectionNeeded()判断当前环境下是否进行 网络推荐的过程 \\n WifiNetworkSelector.isCurrentNetworkSufficient() 判断当前连接到的wifi网络是否是好的\n";
            wifi_19_45.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java => boolean handleScanResults() =>  【 WifiNetworkSelector 】mNetworkSelector.selectNetwork() -> { if(isNetworkSelectionNeeded(...))】 ";
            wifi_19_45.printcode=" localLog(\"Too short since last network selection: \" + gap + \" ms. Skip network selection.\") ";
            wifi_19_45.expain1="WifiNetworkSelector.isCurrentNetworkSufficient() 判断当前连接到的wifi网络是否是好的\n" +
                    "class WifiNetworkSelector {\n" +
                    "\n" +
                    "// 判断当前连接到的wifi网络是否是好的 , 信号骑马三档 5G[-70] 2.4G[ -72 ]\n" +
                    "  private boolean isCurrentNetworkSufficient(WifiInfo wifiInfo, List<ScanDetail> scanDetails) {\n" +
                    "        // Currently connected?\n" +
                    "        if (wifiInfo.getSupplicantState() != SupplicantState.COMPLETED) {\n" +
                    "            localLog(\"No current connected network.\");\n" +
                    "            return false;\n" +
                    "        } else {\n" +
                    "            localLog(\"Current connected network: \" + wifiInfo.getSSID() + \" , ID: \" + wifiInfo.getNetworkId());\n" +
                    "        }\n" +
                    "        int currentRssi = wifiInfo.getRssi();\n" +
                    "\t\t// 判断当前的 rssi 是否 大于 三档  5G[-70] 2.4G[ -72 ]\n" +
                    "        boolean hasQualifiedRssi = currentRssi > mScoringParams.getSufficientRssi(wifiInfo.getFrequency());\n" +
                    "\t\t// 判断 上下行 速率 是否 大于基本的 最低要求 16 \n" +
                    "        boolean hasActiveStream = (wifiInfo.txSuccessRate > mStayOnNetworkMinimumTxRate【16】) || (wifiInfo.rxSuccessRate > mStayOnNetworkMinimumRxRate【16】);\n" +
                    "        if (hasQualifiedRssi && hasActiveStream) { // 如果都达到 指标  那么说明 连接的这个网络是 好的 \n" +
                    "            localLog(\"Stay on current network because of good RSSI and ongoing traffic\");\n" +
                    "            return true;\n" +
                    "        }\n" +
                    "        WifiConfiguration network = mWifiConfigManager.getConfiguredNetwork(wifiInfo.getNetworkId());\n" +
                    "        if (network == null) {  // 如果指标达不到 并且得不到当前wifiinfo的配置 WifiConfiguration 那么网络不是好的\n" +
                    "            localLog(\"Current network was removed.\");\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "// int LAST_USER_SELECTION_SUFFICIENT_MS = 30_000;  30秒\n" +
                    "// 如果 这个网络是 选择出来的状元  并且时间还没到 30秒间隔  我们认为 这个网络暂时是 好的( 因为 对手弱爆了 )\n" +
                    "        if (mWifiConfigManager.getLastSelectedNetwork() == network.networkId \n" +
                    "\t\t&& (mClock.getElapsedSinceBootMillis() - mWifiConfigManager.getLastSelectedTimeStamp()) \n" +
                    "\t\t   <= LAST_USER_SELECTION_SUFFICIENT_MS) {\n" +
                    "            localLog(\"Current network is recently user-selected.\");\n" +
                    "            return true;\n" +
                    "        }\n" +
                    "\n" +
                    "        // OSU (Online Sign Up) network for Passpoint Release 2 is sufficient network.\n" +
                    "        if (network.osu) { // 如果这个网络是最新选择的   时间超过了 30秒   那么 接下来如果网络为 passpoint网络 那么也认为是 好的网络\n" +
                    "            return true;\n" +
                    "        }\n" +
                    "        // Ephemeral network is not qualified.\n" +
                    "        if (wifiInfo.isEphemeral()) {  // 零时网络是不合格的\n" +
                    "            localLog(\"Current network is an ephemeral one.\");\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "        if (wifiInfo.is24GHz()) {\n" +
                    "            // 2.4GHz networks is not qualified whenever 5GHz is available\n" +
                    "\t\t\t// 当在扫描结果中存在 5G的wifi  并且当前设备连接的是2.4G  那么我们认为 当前wifi 相对 不好\n" +
                    "            if (is5GHzNetworkAvailable(scanDetails)) {\n" +
                    "                localLog(\"Current network is 2.4GHz. 5GHz networks available.\");\n" +
                    "                return false;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        if (!hasQualifiedRssi) {  // 可接受  但是不是很好的质量\n" +
                    "            localLog(\"Current network RSSI[\" + currentRssi + \"]-acceptable but not qualified.\");\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "\n" +
                    "        // Open network is not qualified.  开放网络默认不是好的网络\n" +
                    "        if (WifiConfigurationUtil.isConfigForOpenNetwork(network)) {\n" +
                    "            localLog(\"Current network is a open one.\");\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "        // Network with no internet access reports is not qualified.\n" +
                    "\t\t// 网络没有联网  尝试联网记录大于0  并且预期没有网 \n" +
                    "        if (network.numNoInternetAccessReports > 0 && !network.noInternetAccessExpected) {\n" +
                    "            localLog(\"Current network has [\" + network.numNoInternetAccessReports\n" +
                    "                    + \"] no-internet access reports.\");\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "        return true;\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "class WifiNetworkSelector {\n" +
                    " mEnableAutoJoinWhenAssociated = context.getResources().getBoolean(R.bool.config_wifi_framework_enable_associated_network_selection);\n" +
                    "// config_wifi_framework_enable_associated_network_selection 决定了在连接状态下 是否进行 网络选择\n" +
                    "   private boolean isNetworkSelectionNeeded(List<ScanDetail> scanDetails, WifiInfo wifiInfo,boolean connected, boolean disconnected) {\n" +
                    "        if (scanDetails.size() == 0) {\n" +
                    "            localLog(\"Empty connectivity scan results. Skip network selection.\");\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "        if (connected) {\n" +
                    "            // Is roaming allowed?\n" +
                    "            if (!mEnableAutoJoinWhenAssociated) {  //如果 禁止连接其他网络当设备以连接到wifi  开关打开的话  那么不进行网络选择过程\n" +
                    "                localLog(\"Switching networks in connected state is not allowed. Skip network selection.\");\n" +
                    "                return false;\n" +
                    "            }\n" +
                    "\n" +
                    "\t\t\t// mLastNetworkSelectionTimeStamp 上一次网络选择的时间戳 \n" +
                    "            // Has it been at least the minimum interval since last network selection?   // static final long INVALID_TIME_STAMP = Long.MIN_VALUE;\n" +
                    "            if (mLastNetworkSelectionTimeStamp != INVALID_TIME_STAMP) {  //  两次网络推荐之间的间隔 \n" +
                    "                long gap = mClock.getElapsedSinceBootMillis() - mLastNetworkSelectionTimeStamp;  // 时间间隔\n" +
                    "\t\t\t\t// final int MINIMUM_NETWORK_SELECTION_INTERVAL_MS = 10 * 1000; 如果时间小于 10秒钟  那么不进行 网络选择过程\n" +
                    "                if (gap < MINIMUM_NETWORK_SELECTION_INTERVAL_MS) {\n" +
                    "                    localLog(\"Too short since last network selection: \" + gap + \" ms. Skip network selection.\");\n" +
                    "                    return false;\n" +
                    "                }\n" +
                    "            }\n" +
                    "\n" +
                    "            if (isCurrentNetworkSufficient(wifiInfo, scanDetails)) { //  当前连接的wifi网络 是否是 相对好的网络\n" +
                    "                localLog(\"Current connected network already sufficient. Skip network selection.\");\n" +
                    "                return false;\n" +
                    "            } else {\n" +
                    "                localLog(\"Current connected network is not sufficient.\");\n" +
                    "                return true;\n" +
                    "            }\n" +
                    "        } else if (disconnected) {  // 如果当前是断开网络   那么必须进行网络选择过程\n" +
                    "            return true;\n" +
                    "        } else {\n" +
                    "            // No network selection if ClientModeImpl is in a state other than\n" +
                    "            // CONNECTED or DISCONNECTED. 如果当前状态不是 连接 断开 状态 而是另外的状态  那么 不进行网络选择过程\n" +
                    "            localLog(\"ClientModeImpl is in neither CONNECTED nor DISCONNECTED state.\"  + \" Skip network selection.\");\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "\t// WifiNetworkSelector.isNetworkSelectionNeeded()判断当前环境下是否进行 网络推荐的过程 \n" +
                    "\t//WifiNetworkSelector.isCurrentNetworkSufficient() 判断当前连接到的wifi网络是否是好的";
            keyWordList.add(wifi_19_45);



            KeyWordItem wifi_19_46 = new KeyWordItem();
            wifi_19_46.keyword = "did not see any matching network suggestions";
            wifi_19_46.isContain = true;
            wifi_19_46.explain=" NetworkSuggestionEvaluator 中evaluateNetworks(List<ScanDetail>) 评估到的 \n  ScanDetail-》ScanResult-》Set<WifiNetworkSuggestion>-》WifiNetworkSuggestion-》WifiConfiguration , List<ScanDetail>===》List<WifiConfiguration>为空 " ;
            wifi_19_46.classNameForShuxing = " NetworkEvaluator接口 对应的实现类：Interface WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class ScoredNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class SavedNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class NetworkSuggestionEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class PasspointNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n";
            wifi_19_46.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java -> WifiNetworkSelector.java )  【 mEvaluators 】 List<NetworkEvaluator> => Item【NetworkSuggestionEvaluator.java】.WifiConfiguration evaluateNetworks(xxx) \n ";
            wifi_19_46.printcode="did not see any matching network suggestions";
            wifi_19_46.expain1="NetworkEvaluator接口 对应的实现类：Interface WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class ScoredNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class SavedNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class NetworkSuggestionEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class PasspointNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "\n" +
                    "\n" +
                    "\t \n" +
                    "class NetworkSuggestionEvaluator implements WifiNetworkSelector.NetworkEvaluator {\n" +
                    "public WifiConfiguration evaluateNetworks(List<ScanDetail> scanDetails,) {\n" +
                    "   MatchMetaInfo matchMetaInfo = new MatchMetaInfo();\n" +
                    "  for (int i = 0; i < scanDetails.size(); i++) {\n" +
                    "    ......\n" +
                    "    ScanDetail scanDetail = scanDetails.get(i);\n" +
                    "    ScanResult scanResult = scanDetail.getScanResult();\n" +
                    "\t//  从 每一个 ScanResult 获取到一个 Set<WifiNetworkSuggestion> 网络推荐集合 \n" +
                    "    Set<WifiNetworkSuggestion> matchingNetworkSuggestions = mWifiNetworkSuggestionsManager.getNetworkSuggestionsForScanDetail(scanDetail);\n" +
                    "     WifiNetworkSuggestion matchingNetworkSuggestion = matchingNetworkSuggestions.stream().findAny().get();\n" +
                    "    WifiConfiguration wCmConfiguredNetwork =mWifiConfigManager.getConfiguredNetwork(matchingNetworkSuggestion.wifiConfiguration.configKey());\n" +
                    "   //  把  WifiNetworkSuggestion 转为 WifiConfiguration  并放入到 MatchMetaInfo\n" +
                    "   if (wCmConfiguredNetwork != null) {\n" +
                    "\tif (!wCmConfiguredNetwork.getNetworkSelectionStatus().isNetworkEnabled()  // 网络状态是Enable   mStatus == NETWORK_SELECTION_ENABLED=0 NETWORK_SELECTION_TEMPORARY_DISABLED=1; NETWORK_SELECTION_PERMANENTLY_DISABLED=2;\n" +
                    "\t\t\t&& !mWifiConfigManager.tryEnableNetwork(wCmConfiguredNetwork.networkId)) {\n" +
                    "\t\tmLocalLog.log(\"Ignoring blacklisted network: \"+ WifiNetworkSelector.toNetworkString(wCmConfiguredNetwork));\n" +
                    "\t\tcontinue;\n" +
                    "\t}\n" +
                    "  }\n" +
                    " matchMetaInfo.putAll(matchingNetworkSuggestions, wCmConfiguredNetwork, scanDetail);\n" +
                    "}\n" +
                    "\t \n" +
                    "\t// Return early on no match.\n" +
                    "\t // 如果所有的 List<ScanDetail> 对应的 每一个 WifiNetworkSuggestion.configKey() 对应的 WifiConfiguration\n" +
                    "\t // 如果WifiConfiguration 为空  那么说明 没有找到 网络的选择的结果 可能没有合适的网络\n" +
                    "if (matchMetaInfo.isEmpty()) { \n" +
                    "\tmLocalLog.log(\"did not see any matching network suggestions.\");\n" +
                    "\treturn null;\n" +
                    "}\n" +
                    "}";
            keyWordList.add(wifi_19_46);






            KeyWordItem wifi_19_47 = new KeyWordItem();
            wifi_19_47.keyword = "No suitable Passpoint network found";
            wifi_19_47.isContain = true;
            wifi_19_47.explain=" PasspointNetworkEvaluator.evaluateNetworks(List<ScanDetail> scanDetails..) 没有找到合适的 PasspointNetworkCandidate " ;
            wifi_19_47.classNameForShuxing = "class PasspointNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}" +
                    " \nNetworkEvaluator接口 对应的实现类：Interface WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class ScoredNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class SavedNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "class NetworkSuggestionEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\\n\n" +
                    "\n";
            wifi_19_47.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java -> WifiNetworkSelector.java )  【 mEvaluators 】 List<NetworkEvaluator> => Item【PasspointNetworkEvaluator.java】.WifiConfiguration evaluateNetworks(xxx) \n ";
            wifi_19_47.printcode="localLog(\"No suitable Passpoint network found\");";
            wifi_19_47.expain1="*********************** RssiCurve.lookupScore Begin ***********************\n" +
                    "final int activeNetworkRssiBoost;   // 单位 -dbm  增益\n" +
                    "final int start;  // The starting dBm of the curve\n" +
                    "final int bucketWidth; /** The width of each RSSI bucket, in dBm. */\n" +
                    " byte[] rssiBuckets; //  信号强度 的加分项 /** The score for each RSSI bucket. */\n" +
                    " \n" +
                    " \n" +
                    "    public byte lookupScore(int rssi, boolean isActiveNetwork) {\n" +
                    "        if (isActiveNetwork) {\n" +
                    "            rssi += activeNetworkRssiBoost;\n" +
                    "        }\n" +
                    "\n" +
                    "        int index = (rssi - start) / bucketWidth;\n" +
                    "\n" +
                    "        // Snap the index to the closest bucket if it falls outside the curve.\n" +
                    "        if (index < 0) {\n" +
                    "            index = 0;\n" +
                    "        } else if (index > rssiBuckets.length - 1) {\n" +
                    "            index = rssiBuckets.length - 1;\n" +
                    "        }\n" +
                    "\n" +
                    "        return rssiBuckets[index];\n" +
                    "    }\n" +
                    "*********************** RssiCurve.lookupScore End ***********************\n" +
                    "\t\n" +
                    "*********************** PasspointNetworkScore.calculateScore Begin ***********************\n" +
                    "\n" +
                    " Map<NetworkDetail.Ant, Integer> NETWORK_TYPE_SCORES = new HashMap<>();  // 网络类型对应的分数\n" +
                    " \n" +
                    "NETWORK_TYPE_SCORES.put(NetworkDetail.Ant.FreePublic, PUBLIC_OR_PRIVATE_NETWORK_AWARDS 【4】);\n" +
                    "NETWORK_TYPE_SCORES.put(NetworkDetail.Ant.ChargeablePublic,PUBLIC_OR_PRIVATE_NETWORK_AWARDS);\n" +
                    "NETWORK_TYPE_SCORES.put(NetworkDetail.Ant.PrivateWithGuest, PUBLIC_OR_PRIVATE_NETWORK_AWARDS);\n" +
                    "NETWORK_TYPE_SCORES.put(NetworkDetail.Ant.Private, PUBLIC_OR_PRIVATE_NETWORK_AWARDS);\n" +
                    "NETWORK_TYPE_SCORES.put(NetworkDetail.Ant.Personal, PERSONAL_OR_EMERGENCY_NETWORK_AWARDS【2】);\n" +
                    "NETWORK_TYPE_SCORES.put(NetworkDetail.Ant.EmergencyOnly,PERSONAL_OR_EMERGENCY_NETWORK_AWARDS);\n" +
                    "NETWORK_TYPE_SCORES.put(NetworkDetail.Ant.Wildcard, 0);\n" +
                    "NETWORK_TYPE_SCORES.put(NetworkDetail.Ant.TestOrExperimental, 0);\n" +
                    "\t\t\n" +
                    "private static final Map<Integer, Integer> IPV4_SCORES = new HashMap<>();\n" +
                    "IPV4_SCORES.put(IPAddressTypeAvailabilityElement.IPV4_NOT_AVAILABLE, 0);\n" +
                    "IPV4_SCORES.put(IPAddressTypeAvailabilityElement.IPV4_PORT_RESTRICTED,RESTRICTED_OR_UNKNOWN_IP_AWARDS 【1】);\n" +
                    "IPV4_SCORES.put(IPAddressTypeAvailabilityElement.IPV4_PORT_RESTRICTED_AND_SINGLE_NAT,RESTRICTED_OR_UNKNOWN_IP_AWARDS 【1】);\n" +
                    "IPV4_SCORES.put(IPAddressTypeAvailabilityElement.IPV4_PORT_RESTRICTED_AND_DOUBLE_NAT,RESTRICTED_OR_UNKNOWN_IP_AWARDS);\n" +
                    "IPV4_SCORES.put(IPAddressTypeAvailabilityElement.IPV4_UNKNOWN,RESTRICTED_OR_UNKNOWN_IP_AWARDS);\n" +
                    "IPV4_SCORES.put(IPAddressTypeAvailabilityElement.IPV4_PUBLIC, UNRESTRICTED_IP_AWARDS 【2】 );\n" +
                    "IPV4_SCORES.put(IPAddressTypeAvailabilityElement.IPV4_SINGLE_NAT, UNRESTRICTED_IP_AWARDS);\n" +
                    "IPV4_SCORES.put(IPAddressTypeAvailabilityElement.IPV4_DOUBLE_NAT, UNRESTRICTED_IP_AWARDS);\n" +
                    "\n" +
                    "private static final Map<Integer, Integer> IPV6_SCORES = new HashMap<>();\n" +
                    "\t\n" +
                    "    public static int calculateScore(boolean isHomeProvider, ScanDetail scanDetail,Map<ANQPElementType, ANQPElement> anqpElements, boolean isActiveNetwork) {\n" +
                    "        NetworkDetail networkDetail = scanDetail.getNetworkDetail();\n" +
                    "        int score = 0;  // 起始分数\n" +
                    "        if (isHomeProvider) {\n" +
                    "            score += HOME_PROVIDER_AWARD;  如果是家庭passpoint网络 +100分      //  int HOME_PROVIDER_AWARD = 100;\n" +
                    "        }\n" +
                    "\n" +
                    "        // Adjust score based on Internet accessibility. 是否有网标识   NetworkDetail.java  boolean mInternet;    public boolean isInternet() { return mInternet;}\n" +
                    "        score += (networkDetail.isInternet() ? 1 : -1) * INTERNET_ACCESS_AWARD;   //  有网增加50分 int INTERNET_ACCESS_AWARD = 50;\n" +
                    "\n" +
                    "        // Adjust score based on the network type.\n" +
                    "        Integer ndScore = NETWORK_TYPE_SCORES.get(networkDetail.getAnt());\n" +
                    "        if (ndScore != null) {  // 网络 Ant的类型 获取的加分  很少\n" +
                    "            score += ndScore;\n" +
                    "        }\n" +
                    "\n" +
                    "        if (anqpElements != null) {\n" +
                    "\t\t// 从ANQP 获取 HSWANMetrics对应的帧数据  HSWanMetricsElement\n" +
                    "            HSWanMetricsElement wm = (HSWanMetricsElement) anqpElements.get(ANQPElementType.HSWANMetrics);\n" +
                    "            if (wm != null) {\n" +
                    "\t\t\t//  如果ANQP帧的状态不为 LINK_STATUS_UP 或者 isCapped() 返回true  那么 分数减 50分\n" +
                    "                if (wm.getStatus() != HSWanMetricsElement.LINK_STATUS_UP || wm.isCapped()) {\n" +
                    "                    score -= WAN_PORT_DOWN_OR_CAPPED_PENALTY 【50 】;\n" +
                    "                }\n" +
                    "            }\n" +
                    "\n" +
                    "\t\t\t// 从ANQP 获取帧 \n" +
                    "            IPAddressTypeAvailabilityElement ipa = (IPAddressTypeAvailabilityElement)anqpElements.get(ANQPElementType.ANQPIPAddrAvailability);\n" +
                    "\n" +
                    "            if (ipa != null) {\n" +
                    "\t\t\t// IPv4  IPv6 相关的加分项  很少 \n" +
                    "                Integer v4Score = IPV4_SCORES.get(ipa.getV4Availability());\n" +
                    "                Integer v6Score = IPV6_SCORES.get(ipa.getV6Availability());\n" +
                    "                v4Score = v4Score != null ? v4Score : 0;\n" +
                    "                v6Score = v6Score != null ? v6Score : 0;\n" +
                    "                score += (v4Score + v6Score);\n" +
                    "            }\n" +
                    "        }\n" +
                    "//  static final RssiCurve RSSI_SCORE = new RssiCurve(-80 , 20 , new byte[] {-10, 0, 10, 20, 30, 40} ,  20 );\n" +
                    "\t\t// 再计算 RSSI_SCORE.lookupScore的分数   信号强度的加分项 \n" +
                    "        score += RSSI_SCORE.lookupScore(scanDetail.getScanResult().level, isActiveNetwork);\n" +
                    "        return score;\n" +
                    "    }\n" +
                    "\t\n" +
                    "*********************** PasspointNetworkScore.calculateScore End ***********************\n" +
                    "// Passpoint 相关的网络评估\n" +
                    "class PasspointNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {\n" +
                    "\n" +
                    "\n" +
                    "// 找到列表 List<PasspointNetworkCandidate> 最好的那一个 PasspointNetworkCandidate\n" +
                    "    private PasspointNetworkCandidate findBestNetwork( List<PasspointNetworkCandidate> networkList, String currentNetworkSsid) {\n" +
                    "        PasspointNetworkCandidate bestCandidate = null;\n" +
                    "        int bestScore = Integer.MIN_VALUE;\n" +
                    "        for (PasspointNetworkCandidate candidate : networkList) {\n" +
                    "            ScanDetail scanDetail = candidate.mScanDetail;\n" +
                    "            PasspointMatch match = candidate.mMatchStatus;\n" +
                    "            boolean isActiveNetwork = TextUtils.equals(currentNetworkSsid, ScanResultUtil.createQuotedSSID(scanDetail.getSSID()));\n" +
                    "            // 计算passpoint的分数\n" +
                    "\t\t\tint score = PasspointNetworkScore.calculateScore(match == PasspointMatch.HomeProvider,scanDetail, mPasspointManager.getANQPElements(scanDetail.getScanResult()), isActiveNetwork);\n" +
                    "            if (score > bestScore) {\n" +
                    "                bestCandidate = candidate;\n" +
                    "                bestScore = score;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        localLog(\"Best Passpoint network \" + bestCandidate.mScanDetail.getSSID() + \" provided by \"+ bestCandidate.mProvider.getConfig().getHomeSp().getFqdn());\n" +
                    "        return bestCandidate;\n" +
                    "    }\n" +
                    "\t\n" +
                    "\t\n" +
                    "   /**  依据MccMnc 生成配置文件  PasspointConfiguration\n" +
                    "     * Creates an ephemeral Passpoint profile if it finds a matching Passpoint AP for MCC/MNC\n" +
                    "     * of the current MNO carrier on the device.\n" +
                    "     */\n" +
                    "    private void createEphemeralProfileForMatchingAp(List<ScanDetail> filteredScanDetails) {\n" +
                    "        TelephonyManager telephonyManager = getTelephonyManager();\n" +
                    "        if (telephonyManager == null) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        if (TelephonyUtil.getCarrierType(telephonyManager) != TelephonyUtil.CARRIER_MNO_TYPE) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        if (!mCarrierNetworkConfig.isCarrierEncryptionInfoAvailable()) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        String mccMnc = telephonyManager.createForSubscriptionId(SubscriptionManager.getDefaultDataSubscriptionId()).getSimOperator();\n" +
                    "        if (mPasspointManager.hasCarrierProvider(mccMnc)) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        int eapMethod =mPasspointManager.findEapMethodFromNAIRealmMatchedWithCarrier(filteredScanDetails);\n" +
                    "        if (!isCarrierEapMethod(eapMethod)) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        PasspointConfiguration carrierConfig = mPasspointManager.createEphemeralPasspointConfigForCarrier(eapMethod);\n" +
                    "        if (carrierConfig == null) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        mPasspointManager.installEphemeralPasspointConfigForCarrier(carrierConfig); // 初始化配置文件\n" +
                    "    }\n" +
                    "\t\n" +
                    "\t\n" +
                    "//static final int HS20_DISABLED                      = 0;\n" +
                    "//static final int HS20_ENABLED                       = 1;\n" +
                    "//static final int HS20_DEFAULT                       = 2;\n" +
                    "//  int getPasspointState() {   return Settings.Global.getInt(mContext.getContentResolver(), Settings.Global.HS20_SAVED_STATE); }\n" +
                    "// String HS20_SAVED_STATE = \"hs20_saved_state\" Setting中该项 \"hs20_saved_state\" 决定了是否支持passpoint网络 \n" +
                    "// 1.必须支持 Passpoint-Feature  mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_PASSPOINT)\n" +
                    "//  int isPasspointEnabled =  mSettingsStore.getPasspointState();   获取passpoint的状态值  状态值是 Enable 那么支持passpoint\n" +
                    "// WifiConfigManager.hs20Enabled = (isPasspointEnabled == WifiSettingsStore.HS20_ENABLED); \n" +
                    "\n" +
                    "public WifiConfiguration 【 evaluateNetworks 】 (List<ScanDetail> scanDetails,\n" +
                    "WifiConfiguration currentNetwork, String currentBssid,\n" +
                    "boolean connected, boolean untrustedNetworkAllowed, OnConnectableListener onConnectableListener) {\n" +
                    "\n" +
                    "       if (!WifiConfigManager.hs20Enabled) { // 是否支持passpoint 标识  \n" +
                    "            //dont consider any passpoint networks if user disabled it\n" +
                    "            localLog(\" passpoint disabled by user. so skipping the network\");\n" +
                    "            return null;\n" +
                    "        }\n" +
                    "\t\tmPasspointManager.sweepCache();  // 执行ANQP 一个小时过期删除的程序\n" +
                    "\t\t\n" +
                    "// 对 List<ScanDetail> 进行过滤 \n" +
                    "// long DELETED_EPHEMERAL_SSID_EXPIRY_MS = (long) 1000 * 60 * 60 * 24;\n" +
                    "// 满足1:     s.getNetworkDetail()【ScanDetail->NetworkDetail】.isInterworking() =  public boolean isInterworking() { return mAnt != null; }  // 存在波形交互  Ant mAnt; mAnt non null indicates the presence of Interworking, i.e. 802.11u\n" +
                    "// 满足2:  网络的SSID 是否曾经被删除的SSID  WifiConfigManager.java , Map<String【SSID】, Long【时间戳】> mDeletedEphemeralSsidsToTimeMap  记录超过一天 那么就删除;\n" +
                    "List<ScanDetail> filteredScanDetails = scanDetails.stream()   \n" +
                    ".filter(s -> s.getNetworkDetail().isInterworking())\n" +
                    ".filter(s -> {\n" +
                    "\tif (!mWifiConfigManager.wasEphemeralNetworkDeleted(  //\n" +
                    "\t\t\tScanResultUtil.createQuotedSSID(s.getScanResult().SSID))) {\n" +
                    "\t\treturn true;\n" +
                    "\t} else {\n" +
                    "\t\t// If the user previously disconnects this network, don't select it.\n" +
                    "\t\tmLocalLog.log(\"Ignoring disabled the SSID of Passpoint AP: \"\n" +
                    "\t\t\t\t+ WifiNetworkSelector.toScanId(s.getScanResult()));\n" +
                    "\t\treturn false;\n" +
                    "\t}\n" +
                    "}).collect(Collectors.toList());\n" +
                    "createEphemeralProfileForMatchingAp(filteredScanDetails);   // 依据 TelephonyManager 获取 mnc mcc 并生成 PasspointConfiguration\n" +
                    "\n" +
                    "\n" +
                    "        // Go through each ScanDetail and find the best provider for each ScanDetail.\n" +
                    "        List<PasspointNetworkCandidate> candidateList = new ArrayList<>();\n" +
                    "\t\t\n" +
                    "        for (ScanDetail scanDetail : filteredScanDetails) {\n" +
                    "\t\tScanResult scanResult = scanDetail.getScanResult();\n" +
                    "\t\t// 通过 scanResult  得到一个 Pair<PasspointProvider, PasspointMatch> 再得到 PasspointNetworkCandidate 放入数组\n" +
                    "        Pair<PasspointProvider, PasspointMatch> bestProvider = mPasspointManager.matchProvider(scanResult);\n" +
                    "\t\t if (bestProvider != null) {\n" +
                    "\t\t        // 当passpoint需要使用sim卡作为识别秘钥   并且 当前并不识别  sim卡时   这样的passpoint网络就跳过\n" +
                    "                if (bestProvider.first.isSimCredential()   && !TelephonyUtil.isSimPresent(mSubscriptionManager)) {\n" +
                    "                    // Skip providers backed by SIM credential when SIM is not present.\n" +
                    "                    continue;\n" +
                    "                }\n" +
                    "                candidateList.add(new PasspointNetworkCandidate(bestProvider.first, bestProvider.second, scanDetail));\n" +
                    "            }\n" +
                    "\t\t}\n" +
                    "     \n" +
                    "        if (candidateList.isEmpty()) {    \n" +
                    "\t\t// Done if no candidate is found. 从扫描结果 List<ScanDetail> 没有得到任何的  PasspointNetworkCandidate\n" +
                    "            localLog(\"No suitable Passpoint network found\");\n" +
                    "            return null;\n" +
                    "        }\t\n" +
                    "\n" +
                    " // 从数组 List<PasspointNetworkCandidate> 找到最好的那个网络  Find the best Passpoint network among all candidates.\n" +
                    "PasspointNetworkCandidate bestNetwork = findBestNetwork(candidateList, currentNetwork == null ? null : currentNetwork.SSID);\n" +
                    "\n" +
                    "       // Return the configuration for the current connected network if it is the best network.\n" +
                    "\t    // 如果选中的 候选Passpoint网络 就是当前正在连接的网络 那么直接返回\n" +
                    "        if (currentNetwork != null && TextUtils.equals(currentNetwork.SSID, ScanResultUtil.createQuotedSSID(bestNetwork.mScanDetail.getSSID()))) {\n" +
                    "            localLog(\"Staying with current Passpoint network \" + currentNetwork.SSID);\n" +
                    "\n" +
                    "            // Update current network with the latest scan info. TODO - pull into common code\n" +
                    "            mWifiConfigManager.setNetworkCandidateScanResult(currentNetwork.networkId,bestNetwork.mScanDetail.getScanResult(), 0);\n" +
                    "            mWifiConfigManager.updateScanDetailForNetwork(currentNetwork.networkId,bestNetwork.mScanDetail);\n" +
                    "            onConnectableListener.onConnectable(bestNetwork.mScanDetail, currentNetwork, 0);\n" +
                    "            return currentNetwork;\n" +
                    "        }\n" +
                    "\t\t\n" +
                    "\t\t      WifiConfiguration config = createWifiConfigForProvider(bestNetwork);// PasspointNetworkCandidate->WifiConfiguration\n" +
                    "        if (config != null) {   //  如果当前passpoint网络不是正在连接的网络  那么获取到  WifiConfiguration\n" +
                    "\t\t// // 如果 WifiConfiguration 不能自动连接属性为true  那么就不连接 返回null 标识没有找到合适的passpoint网络\n" +
                    "            if(config.skip_auto_connect == 1) {  \n" +
                    "                localLog(\"Skip this passpoint network\");\n" +
                    "                return null;\n" +
                    "            }\n" +
                    "            onConnectableListener.onConnectable(bestNetwork.mScanDetail, config, 0);\n" +
                    "\t\t\t// 最后打印  将要 连接到 Passpoint network\n" +
                    "            localLog(\"Passpoint network to connect to: \" + config.SSID);\n" +
                    "        }\n" +
                    "        return config;\n" +
                    "}\n" +
                    "Log示例: Best Passpoint network   // 找到最好的passpoint网络标识\n" +
                    "Log示例: Passpoint network to connect to:   // 找到最好的passpoint网络标识 并准备连接它\t\t\t\t\n" +
                    "Log示例: No suitable Passpoint network found   // 从List<ScanDetail> 没有找到合适的 PasspointNetworkCandidate";
            keyWordList.add(wifi_19_47);



            KeyWordItem wifi_19_48 = new KeyWordItem();
            wifi_19_48.keyword = "WifiOpenNetworkNotifier:";
            wifi_19_48.explain="wifi网络提示Notification通知管理类 " ;
            wifi_19_48.classNameForShuxing = "class OpenNetworkNotifier extends AvailableNetworkNotifier {";
            wifi_19_48.shuxingDefine=" (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java => OpenNetworkNotifier mOpenNetworkNotifier;  dump()】\n";
            wifi_19_48.printcode=" 【 (WifiServiceImpl.java -> ClientModeImpl.java -> WifiConnectivityManager.java => dump(){} 】 ";
            wifi_19_48.expain1="class WifiConnectivityManager {\n" +
                    "\n" +
                    "final OpenNetworkNotifier mOpenNetworkNotifier;\n" +
                    "final CarrierNetworkNotifier mCarrierNetworkNotifier;\n" +
                    "final CarrierNetworkConfig mCarrierNetworkConfig;\n" +
                    "\t\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        pw.println(\"Dump of WifiConnectivityManager\");\n" +
                    "        pw.println(\"WifiConnectivityManager - Log Begin ----\");\n" +
                    "        mLocalLog.dump(fd, pw, args);\n" +
                    "        pw.println(\"WifiConnectivityManager - Log End ----\");\n" +
                    "        mOpenNetworkNotifier.dump(fd, pw, args);\n" +
                    "        mCarrierNetworkNotifier.dump(fd, pw, args);\n" +
                    "        mCarrierNetworkConfig.dump(fd, pw, args);\n" +
                    "    }\n" +
                    "\t\n" +
                    "\t\n" +
                    "class OpenNetworkNotifier extends AvailableNetworkNotifier {\n" +
                    "    /** Dump this network notifier's state. */\n" +
                    "\t\n" +
                    "\t \n" +
                    "    private boolean mSettingEnabled; // 用户是否打开了有可用网络提示 的开关    /** Whether the user has set the setting to show the 'available networks' notification. */\n" +
                    "\tlong mNotificationRepeatTime; // 再次显示通知的未来时间点 \n" +
                    "\tlong mNotificationRepeatDelay;  // 再次显示通知的时间间隔  默认900秒\n" +
                    "\tint DEFAULT_REPEAT_DELAY_SEC = 900;\n" +
                    "\tint mState = STATE_NO_NOTIFICATION;  // 当前通知的状态\n" +
                    "\t\n" +
                    "    final int STATE_NO_NOTIFICATION = 0;  初始化状态-无通知  /** The initial notification recommending a network to connect to is shown. */\n" +
                    "    final int STATE_SHOWING_RECOMMENDATION_NOTIFICATION = 1; 有推荐网络的通知    /** The notification of status of connecting to the recommended network is shown. */\n" +
                    "    final int STATE_CONNECTING_IN_NOTIFICATION = 2;  正在加入推荐网络的通知\n" +
                    "    final int STATE_CONNECTED_NOTIFICATION = 3; 成功加入推荐网络的通知  /** The notification that the connection to the recommended network was successful is shown. */\n" +
                    "    final int STATE_CONNECT_FAILED_NOTIFICATION = 4; 无法加入推荐网络的通知 /** The notification to show that connection to the recommended network failed is shown. */\n" +
                    "\t\n" +
                    "\tSet<String> mBlacklistedSsids = new ArraySet<>();  // AP黑名单  List of SSIDs blacklisted from recommendation\n" +
                    "\t\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        pw.println(mTag + \": \");   //  mTag = OpenNetworkNotifier:\n" +
                    "        pw.println(\"mSettingEnabled \" + mSettingEnabled);  // mSettingEnabled = true|false 用户是否打开了有可用网络提示 的开关\n" +
                    "        pw.println(\"currentTime: \" + mClock.getWallClockMillis());  // 时间戳 \n" +
                    "        pw.println(\"mNotificationRepeatTime: \" + mNotificationRepeatTime);  // mNotificationRepeatTime 再次显示通知的未来时间点 \n" +
                    "\t\t  // 当前通知Notification状态 0-无通知   1-提示有推荐网络 2-正在加入网络通知 3-已经加入推荐网络通知  4-无法加入推荐网络通知\n" +
                    "        pw.println(\"mState: \" + mState);  \n" +
                    "        pw.println(\"mBlacklistedSsids: \" + mBlacklistedSsids.toString());\n" +
                    "    }\n" +
                    "}";
            keyWordList.add(wifi_19_48);


            KeyWordItem wifi_20_1 = new KeyWordItem();
            wifi_20_1.keyword = "Dump of WakeupController";
            wifi_20_1.explain="WIFI清醒锁管理器 " ;
            wifi_20_1.classNameForShuxing = "class WakeupController { ";
            wifi_20_1.shuxingDefine=" 【 (WifiServiceImpl.java -> ClientModeImpl.java -> WifiInjector.java => WakeupController.java => dump(){} 】 ";
            wifi_20_1.printcode=" 【 WifiServiceImpl.java -> ClientModeImpl.java -> WifiInjector.java => WakeupController.java => dump(){} 】 ";
            wifi_20_1.expain1="********** WifiInjector Begin **********\n" +
                    "class WifiInjector {\n" +
                    "final WakeupController mWakeupController;\n" +
                    "\n" +
                    "    public WakeupController getWakeupController() {\n" +
                    "        return mWakeupController;\n" +
                    "    }\n" +
                    "}\n" +
                    "\t\n" +
                    "\n" +
                    "********** WifiInjector End **********\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "class ClientModeImpl extends StateMachine {\n" +
                    "final WifiInjector mWifiInjector;\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    ".....\n" +
                    "mWifiInjector.getWakeupController().dump(fd, pw, args);  // 调用 WakeupController 的 dump 方法\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "********** WakeupLock Begin **********\n" +
                    " class WakeupLock {\n" +
                    " int mNumScans;\n" +
                    " Map<ScanResultMatchInfo, Integer> mLockedNetworks = new ArrayMap<>();\n" +
                    " \n" +
                    "    /** Dumps wakeup lock contents. */\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        pw.println(\"WakeupLock: \");\n" +
                    "        pw.println(\"mNumScans: \" + mNumScans);  // 扫描次数  lock起作用后 \n" +
                    "        pw.println(\"mIsInitialized: \" + mIsInitialized);  // lock 是否完成了初始化 \n" +
                    "        pw.println(\"Locked networks: \" + mLockedNetworks.size());  // 当前锁的列表 长度\n" +
                    "        for (Map.Entry<ScanResultMatchInfo, Integer> entry : mLockedNetworks.entrySet()) {\n" +
                    "            pw.println(entry.getKey() + \", scans to evict: \" + entry.getValue());\n" +
                    "        }\n" +
                    "    }\n" +
                    "********** WakeupLock Begin **********\n" +
                    "********** WakeupController Begin **********\n" +
                    "\n" +
                    "class WakeupController {\n" +
                    "\n" +
                    "static final boolean USE_PLATFORM_WIFI_WAKE = true;\n" +
                    " boolean mWifiWakeupEnabled;  // mWifiWakeupEnabled = mFrameworkFacade.getIntegerSetting(mContext, Settings.Global.WIFI_WAKEUP_ENABLED, 0) == 1;\n" +
                    " final WakeupOnboarding mWakeupOnboarding;\n" +
                    " final WakeupConfigStoreData mWakeupConfigStoreData; // 使wifi一直wakeup的配置文件\n" +
                    "  boolean mIsActive = false; //  标识当前 WakeupController 是否起作用 \n" +
                    " int mNumScansHandled = 0;  // wakeup以来扫描的次数\n" +
                    " final WakeupLock mWakeupLock;\n" +
                    " \n" +
                    "    /** Dumps wakeup controller state. */\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        pw.println(\"Dump of WakeupController\");\n" +
                    "        pw.println(\"USE_PLATFORM_WIFI_WAKE: \" + USE_PLATFORM_WIFI_WAKE);\n" +
                    "        pw.println(\"mWifiWakeupEnabled: \" + mWifiWakeupEnabled);  // WifiWake是否使能 \n" +
                    "        pw.println(\"isOnboarded: \" + mWakeupOnboarding.isOnboarded());  // 用户是否登录  Returns whether the user is onboarded.\n" +
                    "        pw.println(\"configStore hasBeenRead: \" + mWakeupConfigStoreData.hasBeenRead()); // 配置文件是否被读取 \n" +
                    "        pw.println(\"mIsActive: \" + mIsActive);  //   boolean mIsActive = false; //  标识当前 WakeupController 是否起作用 \n" +
                    "        pw.println(\"mNumScansHandled: \" + mNumScansHandled);  //  // wakeup以来扫描的次数\n" +
                    "\n" +
                    "        mWakeupLock.dump(fd, pw, args);  // 打印 WakeupLock 的 dump\n" +
                    "    }\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "********** WakeupController End **********";
            keyWordList.add(wifi_20_1);


            KeyWordItem wifi_21_1 = new KeyWordItem();
            wifi_21_1.keyword = "WifiScoreCard:";
            wifi_21_1.explain="WIFI 计算网络的byte[] 数组 对应的 Base64 加密信息 com.android.server.wifi.WifiScoreCardProto 无法看到源码 应该是保密的  \n Base64.encode(com.android.server.wifi.WifiScoreCardProto.NetworkList.Builder.build().toByteArray()  创建 NetworkList 并转为数组 byte[]" ;
            wifi_21_1.classNameForShuxing = "class WifiScoreCard { ";
            wifi_21_1.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiScoreCard.java => Base64.encode(【byte[]】getNetworkListByteArray()); 】 ";
            wifi_21_1.printcode=" pw.println(\"WifiScoreCard:\");  pw.println(wifiScoreCard.getNetworkListBase64(true));  】 ";
            wifi_21_1.expain1="********************** WifiScoreCard Begin **********************\n" +
                    "\n" +
                    "class WifiScoreCard {\n" +
                    "\n" +
                    " Map<MacAddress, PerBssid> mApForBssid = new ArrayMap<>();\n" +
                    " \n" +
                    "final class PerBssid {  // WifiScoreCard.PerBssid\n" +
                    "\tpublic int id;\n" +
                    "\tpublic final String l2Key;\n" +
                    "\tpublic final String ssid;  // wifi名称\n" +
                    "\tpublic final MacAddress bssid; // bssid mac地址\n" +
                    "\tpublic boolean changed;  \n" +
                    "\tSecurityType mSecurityType = null;  // 安全类型 \n" +
                    "\tint mNetworkAgentId = Integer.MIN_VALUE;\n" +
                    "\tint mNetworkConfigId = Integer.MIN_VALUE;\n" +
                    "\tfinal Map<Pair<Event, Integer>, PerSignal> mSignalForEventAndFrequency = new ArrayMap<>();\n" +
                    "\t}\n" +
                    "\t\t\n" +
                    "\n" +
                    "\n" +
                    "    public byte[] getNetworkListByteArray(boolean obfuscate) {\n" +
                    "        Map<String, Network.Builder> networks = new ArrayMap<>();\n" +
                    "        for (PerBssid perBssid: mApForBssid.values()) {  // 循环得到 List<PerBssid> 的 Item\t\t\n" +
                    "            String key = perBssid.ssid;  // 拿到ssid \n" +
                    "            Network.Builder network = networks.get(key);  // 拿ssid 从 Map 拿到 Network.Builder\n" +
                    "            if (network == null) {  //  如果为空 那么就自己创建 NetworkBuilder  并放到 put到 Map中\n" +
                    "                network = Network.newBuilder();\n" +
                    "                networks.put(key, network);\n" +
                    "                if (!obfuscate) {\n" +
                    "                    network.setSsid(perBssid.ssid);  // 设置 ssid 到 Network.Builder\n" +
                    "                }\n" +
                    "                if (perBssid.mSecurityType != null) {   // 设置安全类型\n" +
                    "                    network.setSecurityType(perBssid.mSecurityType);\n" +
                    "                }\n" +
                    "                if (perBssid.mNetworkAgentId >= network.getNetworkAgentId()) {  \n" +
                    "\t\t\t\t// 设置较大的 mNetworkAgentId 到 Network.Builder\n" +
                    "                    network.setNetworkAgentId(perBssid.mNetworkAgentId);\n" +
                    "                }\n" +
                    "                if (perBssid.mNetworkConfigId >= network.getNetworkConfigId()) {\n" +
                    "\t\t\t\t// 设置较大的 mNetworkConfigId 到 Network.Builder\n" +
                    "                    network.setNetworkConfigId(perBssid.mNetworkConfigId);\n" +
                    "                }\n" +
                    "            }\n" +
                    "\t\t\t// Network.Builder  添加accesspoint \n" +
                    "            network.addAccessPoints(perBssid.toAccessPoint(obfuscate));\n" +
                    "        }\n" +
                    "        NetworkList.Builder builder = NetworkList.newBuilder();  // 找不到的包 外部的包 无法看到源码的包 com.android.server.wifi.WifiScoreCardProto.NetworkList; \n" +
                    "        for (Network.Builder network: networks.values()) {  // 从Map<String, Network.Builder> networks 获取value\n" +
                    "            builder.addNetworks(network);\n" +
                    "        }\n" +
                    "\t\t// Base64.encode(com.android.server.wifi.WifiScoreCardProto.NetworkList.Builder.build().toByteArray()  创建 NetworkList 并转为数组 byte[]\n" +
                    "        return builder.build().toByteArray();\n" +
                    "    }\n" +
                    "\t\n" +
                    "    public String getNetworkListBase64(boolean obfuscate) {  // WifiScoreCard:\n" +
                    "        byte[] raw = getNetworkListByteArray(obfuscate);\n" +
                    "\t\t  // 把 NetworkList.Builder.build() 创建 NetworkList 并转为数组 byte[] 转为 Base64.Default编码\n" +
                    "        return Base64.encodeToString(raw, Base64.DEFAULT);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "********************** WifiScoreCard End **********************\n" +
                    "\n" +
                    "class WifiServiceImpl extends BaseWifiService {\n" +
                    "\n" +
                    "void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\n" +
                    "mWifiInjector.getClientModeImplHandler().runWithScissors(() -> {\n" +
                    "\tWifiScoreCard wifiScoreCard = mWifiInjector.getWifiScoreCard();\n" +
                    "\tif (wifiScoreCard != null) {\n" +
                    "\t\tpw.println(\"WifiScoreCard:\");  pw.println(wifiScoreCard.getNetworkListBase64(true)); \n" +
                    "\t\t// 打印网络列表的byte[]数组对应的base64字符串 Base64.encodeToString(raw, Base64.DEFAULT) \n" +
                    "\t\tpw.println(wifiScoreCard.getNetworkListBase64(true));    //  Base64.encodeToStringg(byte[], Base64.DEFAULT);\n" +
                    " // 打印  wifiScoreCard.getNetworkListBase64\n" +
                    "\t}\n" +
                    "}, RUN_WITH_SCISSORS_TIMEOUT_MILLIS);  // int RUN_WITH_SCISSORS_TIMEOUT_MILLIS = 4000;\n" +
                    "}\n";
            keyWordList.add(wifi_21_1);


            KeyWordItem wifi_22_2 = new KeyWordItem();
            wifi_22_2.keyword = "mConnectionEvents:";
            wifi_22_2.explain="连接网络的事件Log 在 WifiMetrics " ;
            wifi_22_2.classNameForShuxing = "class WifiMetrics { ";
            wifi_22_2.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { List<ConnectionEvent> mConnectionEventList 打印连接日志 } 】 ";
            wifi_22_2.printcode=" String eventLine = ConnectionEvent.toString(); pw.println(eventLine);  ";
            wifi_22_2.expain1="********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 Begin*****************\n" +
                    "加密 WifiMetricsProto   com.android.server.wifi.nano.WifiMetricsProto\n" +
                    "********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 End *****************\n" +
                    "\n" +
                    "********************** WifiMetrics.ConnectionEvent Begin **********************\n" +
                    "\n" +
                    "\n" +
                    "// Log event, tracking the start time, end time and result of a wireless connection attempt.\n" +
                    "class ConnectionEvent {\n" +
                    "WifiMetricsProto.ConnectionEvent mConnectionEvent;\n" +
                    "\n" +
                    "int FAILURE_UNKNOWN = 0; //  Failure is unknown\n" +
                    "int FAILURE_NONE = 1;  // NONE\n" +
                    "int FAILURE_ASSOCIATION_REJECTION = 2;  // ASSOCIATION_REJECTION_EVENT\n" +
                    "int FAILURE_AUTHENTICATION_FAILURE = 3;   // AUTHENTICATION_FAILURE_EVENT\n" +
                    "int FAILURE_SSID_TEMP_DISABLED = 4;  //  SSID_TEMP_DISABLED (Also Auth failure)\n" +
                    "int FAILURE_CONNECT_NETWORK_FAILED = 5;   // reconnect() or reassociate() call to WifiNative failed\n" +
                    "int FAILURE_NETWORK_DISCONNECTION = 6; // NETWORK_DISCONNECTION_EVENT\n" +
                    "int FAILURE_NEW_CONNECTION_ATTEMPT = 7;  // NEW_CONNECTION_ATTEMPT before previous finished \n" +
                    "int FAILURE_REDUNDANT_CONNECTION_ATTEMPT = 8;       // New connection attempt to the same network & bssid\n" +
                    "int FAILURE_ROAM_TIMEOUT = 9;   // Roam Watchdog timer triggered (Roaming timed out) \n" +
                    "int FAILURE_DHCP = 10;    // DHCP failure\n" +
                    "int FAILURE_ASSOCIATION_TIMED_OUT = 11;         // ASSOCIATION_TIMED_OUT\n" +
                    "\n" +
                    "\n" +
                    "RouterFingerPrint mRouterFingerPrint;\n" +
                    "long mRealStartTime;\n" +
                    "long mRealEndTime;\n" +
                    "String mConfigSsid;\n" +
                    "String mConfigBssid;\n" +
                    "int mWifiState;\n" +
                    "boolean mScreenOn;   //  是否亮屏 \n" +
                    "\t\t\n" +
                    "\n" +
                    "\tpublic String toString() {\n" +
                    "StringBuilder sb = new StringBuilder();\n" +
                    "sb.append(\"startTime=\");\n" +
                    "Calendar c = Calendar.getInstance();\n" +
                    "synchronized (mLock) {\n" +
                    "\tc.setTimeInMillis(mConnectionEvent.startTimeMillis);  // 开始时间\n" +
                    "\tsb.append(mConnectionEvent.startTimeMillis == 0 ? \" <null>\" : String.format(\"%tm-%td %tH:%tM:%tS.%tL\", c, c, c, c, c, c));\n" +
                    "\tsb.append(\", SSID=\"); sb.append(mConfigSsid);  // 打印 SSID\n" +
                    "\tsb.append(\", BSSID=\"); sb.append(mConfigBssid);\n" +
                    "\tsb.append(\", durationMillis=\");\n" +
                    "\tsb.append(mConnectionEvent.durationTakenToConnectMillis);\n" +
                    "\tsb.append(\", roamType=\");\n" +
                    "\t\t\t\t\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "********************** WifiMetrics.ConnectionEvent End **********************\n" +
                    "\n" +
                    "********************** WifiMetrics Begin **********************\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "// wifi运行时日志\n" +
                    "final WifiMetricsProto.WifiLog mWifiLogProto = new WifiMetricsProto.WifiLog();\n" +
                    "\n" +
                    "// 记录每一个连接wifi网络的日志  Session information that gets logged for every Wifi connection attempt.\n" +
                    "final List<ConnectionEvent> mConnectionEventList = new ArrayList<>();\n" +
                    "\n" +
                    "// 打印wifi的一些log信息 统计信息 \n" +
                    "// Dump all WifiMetrics. Collects some metrics from ConfigStore, Settings and WifiManager  at this time.\n" +
                    "\n" +
                    "void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "pw.println(\"WifiMetrics:\");\n" +
                    "pw.println(\"mConnectionEvents:\");\n" +
                    "       // 打印连接的事件日志 如果是当前网络多打印 \" CURRENTLY OPEN EVENT \"\n" +
                    "\t   for (ConnectionEvent event : mConnectionEventList) {  \n" +
                    "\t\t\tString eventLine = event.toString();\n" +
                    "\t\t\tif (event == mCurrentConnectionEvent) { eventLine += \"CURRENTLY OPEN EVENT\"; }\n" +
                    "\t\t\tpw.println(eventLine);\n" +
                    "\t\t}\n" +
                    "\t\t//Log示例: startTime=11-15 16:24:04.876, SSID=\"D-Link_DIR-816\", BSSID=1c:5f:2b:5e:d5:53, durationMillis=4692, roamType=ROAM_UNRELATED, connectionResult=1, level2FailureCode=NONE, connectivityLevelFailureCode=NONE, signalStrength=-22, wifiState=WIFI_DISCONNECTED, screenOn=true, mRouterFingerprint=mConnectionEvent.roamType=0, mChannelInfo=2412, mDtim=0, mAuthentication=2, mHidden=false, mRouterTechnology=4, mSupportsIpv6=false, useRandomizedMac=true, connectionNominator=NOMINATOR_SAVED_USER_CONNECT_CHOICE, networkSelectorExperimentId=42504592, level2FailureReason=FAILURE_REASON_UNKNOWN\n" +
                    "} \n" +
                    "能查看到WIFI每次连接的WIFI的具体信息  例如灭屏下连接到WIFI WIIF名称  durationMillis=连接消耗的时间\n" +
                    "BSSID=any 主要是加密BSSID     \n" +
                    "roamType=\n" +
                    "0-ROAM_UNKNOWN ( 未知漫游类型 )\n" +
                    "1-ROAM_NONE  ( 无漫游类型 )\n" +
                    "2-ROAM_DBDC ( DBDC roaming ,This is dual band roaming 双频漫游 )  (不同的bssid 不同的网络名称) \n" +
                    "3-ROAM_ENTERPRISE 【企业网络漫游--正常的漫游】  (不同的bssid相同的网络名称)\n" +
                    "4-ROAM_USER_SELECTED 【用户选择的漫游】 // User selected roaming.  \n" +
                    "5-ROAM_UNRELATED   // Unrelated . 不相关的  无关的切换 网络 \n" +
                    "connectionResult=1 标识连接认证成功\n" +
                    "connectionResult=0 标识连接认证失败\n" +
                    "mSupportsIpv6=是否支持IPv6    signalStrength=信号强度-rssi\n" +
                    "useRandomizedMac=true 使用 随机Mac地址连接的网络     =false使用自身的mac地址连接的网络\n" +
                    "wifiState 【Set wifi state (WIFI_UNKNOWN, WIFI_DISABLED, WIFI_DISCONNECTED, WIFI_ASSOCIATED)】连接之前的状态\n" +
                    "connectionNominator = \n" +
                    "mChannelInfo = 连接的信道 \n" +
                    "mHidden = 热点是否是隐藏的\n" +
                    "\n" +
                    "mDtim = 【Delivery Traffic indication Map】Set ConnectionEvent DTIM Interval (if set), and 802.11 Connection mode, from NetworkDetail\n" +
                    "Beacon frame 里面有含TIM 参数，用于高速刚从Sleep状态醒来的STA ，在AP处是否有你的缓存帧数据等待你去拿\n" +
                    "dtim= 是指间隔多少的becaon 才出现一个包含 DTIM信息的beacon帧\n" +
                    "mAuthentication = 网络的加密类型 AUTH_UNKNOWN = 0;     AUTH_OPEN = 1; AUTH_PERSONAL = 2;  AUTH_ENTERPRISE = 3; \n" +
                    "\n" +
                    "mRouterTechnology = 标识路由器支持的802.11 协议\n" +
                    "mRouterTechnology= 0-未知的802.11协议  1-802.11a  2-802.11b  3-802.11g  4-802.11n 5-802.11ac  6-其他协议\n" +
                    "\n" +
                    "connectionNominator = 推荐者ID 列表\n" +
                    "NOMINATOR_UNKNOWN = 0;  // Unknown nominator\n" +
                    "NOMINATOR_MANUAL = 1;   // User selected network manually\n" +
                    "NOMINATOR_SAVED = 2;     // Saved network\n" +
                    "NOMINATOR_SUGGESTION = 3;  // Suggestion API\n" +
                    "NOMINATOR_PASSPOINT = 4;     // Passpoint\n" +
                    "NOMINATOR_CARRIER = 5;     // Carrier suggestion\n" +
                    "NOMINATOR_EXTERNAL_SCORED = 6;  // External scorer\n" +
                    "NOMINATOR_SPECIFIER = 7;     // Network Specifier\n" +
                    "NOMINATOR_SAVED_USER_CONNECT_CHOICE = 8;     // User connected choice override\n" +
                    "NOMINATOR_OPEN_NETWORK_AVAILABLE = 9;     // Open Network Available Pop-up\n" +
                    "\n" +
                    "LOG 示例: \n" +
                    "startTime=11-23 22:32:33.395, SSID=\"D-Link_DIR-816%\", BSSID=any, durationMillis=2346, roamType=ROAM_UNRELATED,\n" +
                    "connectionResult=1, level2FailureCode=NONE, connectivityLevelFailureCode=NONE, signalStrength=0, \n" +
                    "wifiState=WIFI_DISCONNECTED, screenOn=true 【 熄灭屏信息 】, mRouterFingerprint=mConnectionEvent.roamType=0, \n" +
                    "mChannelInfo=0, mDtim=0, mAuthentication=1, mHidden=false, mRouterTechnology=0, mSupportsIpv6=false, \n" +
                    "useRandomizedMac=true, connectionNominator=NOMINATOR_MANUAL, networkSelectorExperimentId=42504592, \n" +
                    "level2FailureReason=FAILURE_REASON_UNKNOWN\n" +
                    "\n";
            keyWordList.add(wifi_22_2);



            KeyWordItem wifi_22_3 = new KeyWordItem();
            wifi_22_3.keyword = "mWifiLogProto.numExternalBackgroundAppOneshotScanRequestsThrottled";
            wifi_22_3.explain="连接网络的事件Log 在 WifiMetrics " ;
            wifi_22_3.classNameForShuxing = "class WifiMetrics { ";
            wifi_22_3.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { WifiMetricsProto.WifiLog.众多属性打印 } 】 ";
            wifi_22_3.printcode="pw.println(\"mWifiLogProto.numExternalBackgroundAppOneshotScanRequestsThrottled=\"+ mWifiLogProto.numExternalBackgroundAppOneshotScanRequestsThrottled);\n ";
            wifi_22_3.expain1="********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 Begin*****************\n" +
                    "加密 WifiMetricsProto   com.android.server.wifi.nano.WifiMetricsProto\n" +
                    "********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 End *****************\n" +
                    "\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "// wifi运行时日志\n" +
                    "final WifiMetricsProto.WifiLog mWifiLogProto = new WifiMetricsProto.WifiLog();\n" +
                    "\n" +
                    "// 打印wifi的一些log信息 统计信息 \n" +
                    "// Dump all WifiMetrics. Collects some metrics from ConfigStore, Settings and WifiManager  at this time.\n" +
                    "\n" +
                    "void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\n" +
                    "// numSavedNetworks 当前保存网络的个数\n" +
                    "pw.println(\"mWifiLogProto.numSavedNetworks=\" + mWifiLogProto.numSavedNetworks);\n" +
                    "// numSavedNetworksWithMacRandomization 使用堆积Mac地址连接的已保存的网络的次数? \n" +
                    "pw.println(\"mWifiLogProto.numSavedNetworksWithMacRandomization=\"+ mWifiLogProto.numSavedNetworksWithMacRandomization); \n" +
                    "// numOpenNetworks 已保存网络中 开放网络的个数 \n" +
                    "pw.println(\"mWifiLogProto.numOpenNetworks=\" + mWifiLogProto.numOpenNetworks);\n" +
                    "// numLegacyPersonalNetworks 已保存网络中需要输入密码的网络的个数 \n" +
                    "pw.println(\"mWifiLogProto.numLegacyPersonalNetworks=\"+ mWifiLogProto.numLegacyPersonalNetworks);\n" +
                    "// numLegacyEnterpriseNetworks 已保存网络中属于企业网络  例如PEAP输入用户名 和 密码的那种 wifi网络\n" +
                    "pw.println(\"mWifiLogProto.numLegacyEnterpriseNetworks=\"+ mWifiLogProto.numLegacyEnterpriseNetworks);\n" +
                    "// numEnhancedOpenNetworks  已保存网络中 WPA3 开放网络的个数\n" +
                    "pw.println(\"mWifiLogProto.numEnhancedOpenNetworks=\"+ mWifiLogProto.numEnhancedOpenNetworks);\n" +
                    "// numWpa3PersonalNetworks  已保存网络中 WPA3-PSK 需要密码连接的网络个数\n" +
                    "pw.println(\"mWifiLogProto.numWpa3PersonalNetworks=\"+ mWifiLogProto.numWpa3PersonalNetworks);\n" +
                    "// numWpa3EnterpriseNetworks  已保存网络中 WPA3-PEAP 企业wifi网络个数\n" +
                    "pw.println(\"mWifiLogProto.numWpa3EnterpriseNetworks=\"+ mWifiLogProto.numWpa3EnterpriseNetworks);\n" +
                    "// numHiddenNetworks   已保存网络中  隐藏网络的个数? \n" +
                    "pw.println(\"mWifiLogProto.numHiddenNetworks=\" + mWifiLogProto.numHiddenNetworks);\n" +
                    "// numPasspointNetworks   已保存网络中  Passpoint网络的个数\n" +
                    "pw.println(\"mWifiLogProto.numPasspointNetworks=\"+ mWifiLogProto.numPasspointNetworks);\n" +
                    "// isLocationEnabled  WIFI定位功能开关\n" +
                    "pw.println(\"mWifiLogProto.isLocationEnabled=\" + mWifiLogProto.isLocationEnabled);\n" +
                    "// isScanningAlwaysEnabled  WIFI扫描总是打开开关\n" +
                    "pw.println(\"mWifiLogProto.isScanningAlwaysEnabled=\"+ mWifiLogProto.isScanningAlwaysEnabled);\n" +
                    "// numNetworksAddedByUser   其他APP添加的wifi网络个数\n" +
                    "pw.println(\"mWifiLogProto.numNetworksAddedByUser=\"+ mWifiLogProto.numNetworksAddedByUser);\n" +
                    "// numNetworksAddedByApps   Settings-APP添加的wifi网络个数\n" +
                    "pw.println(\"mWifiLogProto.numNetworksAddedByApps=\"+ mWifiLogProto.numNetworksAddedByApps);\n" +
                    "// numNonEmptyScanResults   扫描结果非空的次数\n" +
                    "pw.println(\"mWifiLogProto.numNonEmptyScanResults=\"+ mWifiLogProto.numNonEmptyScanResults);\n" +
                    "// numEmptyScanResults  扫描结果为 空的次数\n" +
                    "pw.println(\"mWifiLogProto.numEmptyScanResults=\"+ mWifiLogProto.numEmptyScanResults);\n" +
                    "\n" +
                    "// numConnectivityOneshotScans  执行连接扫描的个数(小于 执行扫描的次数 ) 【WifiConnectivityManager.java.startSingleScan(boolean isFullBandScan, WorkSource workSource)】 \n" +
                    "pw.println(\"mWifiLogProto.numConnecitvityOneshotScans=\"+ mWifiLogProto.numConnectivityOneshotScans);\n" +
                    "// numOneshotScans  执行单次扫描命令 CMD_START_SINGLE_SCAN 的个数 【WifiScanner.java.startScan(ScanSettings, ScanListener, WorkSource)】 int CMD_START_SINGLE_SCAN   = BASE + 21;  \n" +
                    "pw.println(\"mWifiLogProto.numOneshotScans=\"+ mWifiLogProto.numOneshotScans);\n" +
                    "// numOneshotHasDfsChannelScans  包含DFS 动态频率选择的 信道 的 扫描次数 \n" +
                    "pw.println(\"mWifiLogProto.numOneshotHasDfsChannelScans=\"+ mWifiLogProto.numOneshotHasDfsChannelScans);\n" +
                    "// numBackgroundScans  后台扫描的次数   WifiScanner.startBackgroundScan  WifiScanner.CMD_START_BACKGROUND_SCAN = = BASE + 2;  \n" +
                    "pw.println(\"mWifiLogProto.numBackgroundScans=\"+ mWifiLogProto.numBackgroundScans);\n" +
                    "// numExternalAppOneshotScanRequests  外部APP请求扫描的次数 ScanRequestProxy.java  boolean shouldScanRequestBeThrottledForApp\n" +
                    "pw.println(\"mWifiLogProto.numExternalAppOneshotScanRequests=\"+ mWifiLogProto.numExternalAppOneshotScanRequests);\n" +
                    "// numExternalForegroundAppOneshotScanRequestsThrottled  外部APP 前台请求网络扫描次数\n" +
                    "pw.println(\"mWifiLogProto.numExternalForegroundAppOneshotScanRequestsThrottled=\"+ mWifiLogProto.numExternalForegroundAppOneshotScanRequestsThrottled);\n" +
                    "// numExternalBackgroundAppOneshotScanRequestsThrottled  外部APP 在后台请求网络扫描次数\n" +
                    "pw.println(\"mWifiLogProto.numExternalBackgroundAppOneshotScanRequestsThrottled=\"+ mWifiLogProto.numExternalBackgroundAppOneshotScanRequestsThrottled);\n" +
                    "}\n" +
                    "}\n";
            keyWordList.add(wifi_22_3);


            KeyWordItem wifi_22_4 = new KeyWordItem();
            wifi_22_4.keyword = "mScanReturnEntries:";
            wifi_22_4.explain="WIFI扫描结果大概情况 " ;
            wifi_22_4.classNameForShuxing = "class WifiMetrics { final SparseIntArray mScanReturnEntries = new SparseIntArray();} ";
            wifi_22_4.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { SparseIntArray mScanReturnEntries 扫描原因列表 } 】 ";
            wifi_22_4.printcode="pw.println(\"  FAILURE_WIFI_DISABLED: \" + getScanReturnEntry( WifiMetricsProto.WifiLog.FAILURE_WIFI_DISABLED【4】));\n ";
            wifi_22_4.expain1="********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 Begin*****************\n" +
                    "加密 WifiMetricsProto   com.android.server.wifi.nano.WifiMetricsProto\n" +
                    "********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 End *****************\n" +
                    "\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "final SparseIntArray mScanReturnEntries = new SparseIntArray();\n" +
                    "\n" +
                    "public int getScanReturnEntry(int scanReturnCode) { return mScanReturnEntries.get(scanReturnCode);    }\n" +
                    "    \n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\n" +
                    "pw.println(\"mScanReturnEntries:\");  \n" +
                    "// SCAN_UNKNOWN   位置错误扫描失败次数\n" +
                    "pw.println(\"  SCAN_UNKNOWN: \" + getScanReturnEntry( WifiMetricsProto.WifiLog.SCAN_UNKNOWN【0】)); \n" +
                    "// SCAN_SUCCESS  正常扫描成功次数\n" +
                    "pw.println(\"  SCAN_SUCCESS: \" + getScanReturnEntry( WifiMetricsProto.WifiLog.SCAN_SUCCESS【1】));\n" +
                    "// SCAN_FAILURE_INTERRUPTED   扫描被打断导致失败次数\n" +
                    "pw.println(\"  SCAN_FAILURE_INTERRUPTED: \" + getScanReturnEntry(WifiMetricsProto.WifiLog.SCAN_FAILURE_INTERRUPTED【2】));\n" +
                    "// SCAN_FAILURE_INVALID_CONFIGURATION  无效的扫描参数导致扫描失败\n" +
                    "pw.println(\"  SCAN_FAILURE_INVALID_CONFIGURATION: \" + getScanReturnEntry(WifiMetricsProto.WifiLog.SCAN_FAILURE_INVALID_CONFIGURATION【3】));// \n" +
                    "// FAILURE_WIFI_DISABLED  WIFI 不可用 导致的扫描失败 次数\n" +
                    "pw.println(\"  FAILURE_WIFI_DISABLED: \" + getScanReturnEntry( WifiMetricsProto.WifiLog.FAILURE_WIFI_DISABLED【4】));\n" +
                    "......\n" +
                    "}";
            keyWordList.add(wifi_22_4);


            KeyWordItem wifi_22_5 = new KeyWordItem();
            wifi_22_5.keyword = "mSystemStateEntries:";
            wifi_22_5.explain="WIFI状态-【亮屏】【灭屏】-【WIFI扫描次数】 " ;
            wifi_22_5.classNameForShuxing = "class WifiMetrics { SparseIntArray mWifiSystemStateEntries = new SparseIntArray(); // 应该是可以计算包含多少个int值的数组 } ";
            wifi_22_5.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { SparseIntArray mScanReturnEntries WIFI状态下扫描统计 } 】 ";
            wifi_22_5.expain1="********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 Begin*****************\n" +
                    "加密 WifiMetricsProto   com.android.server.wifi.nano.WifiMetricsProto\n" +
                    "********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 End *****************\n" +
                    "\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "SparseIntArray mWifiSystemStateEntries = new SparseIntArray(); // 应该是可以计算包含多少个int值的数组\n" +
                    "\n" +
                    "private static final int SCREEN_ON = 1;\n" +
                    "private static final int SCREEN_OFF = 0;\n" +
                    "\n" +
                    "public int getSystemStateCount(int state, boolean screenOn) {\n" +
                    "\tsynchronized (mLock) {\n" +
                    "\t\tint index = state * 2 + (screenOn ? SCREEN_ON : SCREEN_OFF);\n" +
                    "\t\treturn mWifiSystemStateEntries.get(index);\n" +
                    "\t}\n" +
                    "\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\n" +
                    "pw.println(\"mSystemStateEntries: <state><screenOn> : <scansInitiated>\");\n" +
                    "pw.println(\"  WIFI_UNKNOWN       ON: \"+ getSystemStateCount(WifiMetricsProto.WifiLog.WIFI_UNKNOWN 【1-2(value)】, true【1】)【end=3】);\n" +
                    "pw.println(\"  WIFI_DISABLED      ON: \"+ getSystemStateCount(WifiMetricsProto.WifiLog.WIFI_DISABLED 【2-4(value)】, true【1】)【end=5】);\n" +
                    "pw.println(\"  WIFI_DISCONNECTED  ON: \"+ getSystemStateCount(WifiMetricsProto.WifiLog.WIFI_DISCONNECTED 【3-6(value)】, true【1】) 【end=7】);\n" +
                    "pw.println(\"  WIFI_ASSOCIATED    ON: \"+ getSystemStateCount(WifiMetricsProto.WifiLog.WIFI_ASSOCIATED 【4-8(value)】, true【1】)【end=9】);\n" +
                    "pw.println(\"  WIFI_UNKNOWN      OFF: \"+ getSystemStateCount(WifiMetricsProto.WifiLog.WIFI_UNKNOWN 【1-2(value)】, false【0】)); 【end=2】\n" +
                    "pw.println(\"  WIFI_DISABLED     OFF: \"+ getSystemStateCount(WifiMetricsProto.WifiLog.WIFI_DISABLED 【2-4(value)】, false【0】)); 【end=4】\n" +
                    "pw.println(\"  WIFI_DISCONNECTED OFF: \"+ getSystemStateCount(WifiMetricsProto.WifiLog.WIFI_DISCONNECTED【3-6(value)】, false【0】));【end=6】\n" +
                    "pw.println(\"  WIFI_ASSOCIATED   OFF: \"+ getSystemStateCount(WifiMetricsProto.WifiLog.WIFI_ASSOCIATED 【4-8(value)】, false【0】));【end=8】\n" +
                    "}\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "<state>    <screenOn> : <scansInitiated>\n" +
                    "WIFI_UNKNOWN       ON: 0      WIFI_UNKNOWN 状态下---亮屏状态下---wifi扫描的次数\n" +
                    "WIFI_DISABLED      ON: 0      WIFI_DISABLED 状态下---亮屏状态下---wifi扫描的次数\n" +
                    "WIFI_DISCONNECTED  ON: 191    WIFI_DISCONNECTED 状态下---亮屏状态下---wifi扫描的次数\n" +
                    "WIFI_ASSOCIATED    ON: 209    WIFI_ASSOCIATED 状态下---亮屏状态下---wifi扫描的次数\n" +
                    "WIFI_UNKNOWN      OFF: 0      WIFI_UNKNOWN 状态下---灭屏状态下---wifi扫描的次数\n" +
                    "WIFI_DISABLED     OFF: 0      WIFI_DISABLED 状态下---灭屏状态下---wifi扫描的次数\n" +
                    "WIFI_DISCONNECTED OFF: 776    WIFI_DISCONNECTED 状态下---灭屏状态下---wifi扫描的次数\n" +
                    "WIFI_ASSOCIATED   OFF: 18     WIFI_ASSOCIATED 状态下---灭屏状态下---wifi扫描的次数";
            keyWordList.add(wifi_22_5);




            KeyWordItem wifi_22_6 = new KeyWordItem();
            wifi_22_6.keyword = "mWifiLogProto.numConnectivityWatchdogPnoGood";
            wifi_22_6.explain="WifiMetrics 中的Log信息属性 com.android.server.wifi.nano.WifiMetricsProto.WifiLog " ;
            wifi_22_6.classNameForShuxing = "class WifiMetrics { com.android.server.wifi.nano.WifiMetricsProto.WifiLog  mWifiLogProto}  } ";
            wifi_22_6.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { mWifiLogProto.xxxxx } 】 ";
            wifi_22_6.expain1="********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 Begin*****************\n" +
                    "加密 WifiMetricsProto   com.android.server.wifi.nano.WifiMetricsProto\n" +
                    "********com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密包 End *****************\n" +
                    "\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\n" +
                    "// numConnectivityWatchdogPnoGood 标识在AllSingleScanListener中得到扫描结果中没有有效网络时 如果PNO扫描开启 说明PNO扫描可以继续工作的自增次数\n" +
                    "pw.println(\"mWifiLogProto.numConnectivityWatchdogPnoGood=\"+ mWifiLogProto.numConnectivityWatchdogPnoGood);\n" +
                    "\n" +
                    "// numConnectivityWatchdogPnoBad 标识在AllSingleScanListener中得到扫描结果中存在有效网络时 如果PNO扫描开启 说明PNO扫描不需要再工作的自增次数\n" +
                    "pw.println(\"mWifiLogProto.numConnectivityWatchdogPnoBad=\"+ mWifiLogProto.numConnectivityWatchdogPnoBad);\n" +
                    "// numConnectivityWatchdogBackgroundGood = 0 未使用\n" +
                    "pw.println(\"mWifiLogProto.numConnectivityWatchdogBackgroundGood=\"+ mWifiLogProto.numConnectivityWatchdogBackgroundGood);\n" +
                    "// numConnectivityWatchdogBackgroundBad = 0 未使用\n" +
                    "pw.println(\"mWifiLogProto.numConnectivityWatchdogBackgroundBad=\"+ mWifiLogProto.numConnectivityWatchdogBackgroundBad);\n" +
                    "\n" +
                    "// numLastResortWatchdogTriggers  记录wifi变化的log次数\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogTriggers=\"+ mWifiLogProto.numLastResortWatchdogTriggers);\n" +
                    "// numLastResortWatchdogBadAssociationNetworksTotal  关联失败次数\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogBadAssociationNetworksTotal=\"+ mWifiLogProto.numLastResortWatchdogBadAssociationNetworksTotal);\n" +
                    "// numLastResortWatchdogBadAuthenticationNetworksTotal  认证失败次数\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogBadAuthenticationNetworksTotal=\"+ mWifiLogProto.numLastResortWatchdogBadAuthenticationNetworksTotal);\n" +
                    "// numLastResortWatchdogBadDhcpNetworksTotal   无效DHCP网络报告次数\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogBadDhcpNetworksTotal=\"+ mWifiLogProto.numLastResortWatchdogBadDhcpNetworksTotal);\n" +
                    "// numLastResortWatchdogBadDhcpNetworksTotal   其他无效网络报告次数\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogBadOtherNetworksTotal=\"+ mWifiLogProto.numLastResortWatchdogBadOtherNetworksTotal);\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogAvailableNetworksTotal=\"+ mWifiLogProto.numLastResortWatchdogAvailableNetworksTotal);\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogTriggersWithBadAssociation=\"+ mWifiLogProto.numLastResortWatchdogTriggersWithBadAssociation);\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogTriggersWithBadAuthentication=\"+ mWifiLogProto.numLastResortWatchdogTriggersWithBadAuthentication);\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogTriggersWithBadDhcp=\"+ mWifiLogProto.numLastResortWatchdogTriggersWithBadDhcp);\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogTriggersWithBadOther=\"+ mWifiLogProto.numLastResortWatchdogTriggersWithBadOther);\n" +
                    "pw.println(\"mWifiLogProto.numLastResortWatchdogSuccesses=\"+ mWifiLogProto.numLastResortWatchdogSuccesses);\n" +
                    "pw.println(\"mWifiLogProto.watchdogTotalConnectionFailureCountAfterTrigger=\"+ mWifiLogProto.watchdogTotalConnectionFailureCountAfterTrigger);\n" +
                    "pw.println(\"mWifiLogProto.watchdogTriggerToConnectionSuccessDurationMs=\"+ mWifiLogProto.watchdogTriggerToConnectionSuccessDurationMs);\n" +
                    "// 以上都未知  \n" +
                    "// recordDurationSec  开机持续时间 \n" +
                    "// 3600秒=1小时  1800秒=0.5小时 600秒=10分钟  43200秒=0.5天=12小时  86400秒=1天=24小时 \n" +
                    "// 259200秒=72小时=3天   432000秒=120小时=5天  864000秒=240小时=10天  1296000秒=360小时=15天  \n" +
                    "// 1728000秒=480小时=20天    2592000秒=720小时=30天  1年=365天=8760小时=31536000秒\n" +
                    "pw.println(\"mWifiLogProto.recordDurationSec=\"+ ((mClock.getElapsedSinceBootMillis() / 1000) - mRecordStartTimeSec));\n" +
                    "......\n" +
                    "}";
            keyWordList.add(wifi_22_6);


            KeyWordItem wifi_22_7 = new KeyWordItem();
            wifi_22_7.keyword = "mWifiLogProto.rssiPollCount";
            wifi_22_7.explain=" 连接的网络的信号强度直方图  <channel-count(rssi)> key=信道 value=(信号强度,信号强度出现的次数) " ;
            wifi_22_7.classNameForShuxing = "class WifiMetrics {  Map<Integer, SparseIntArray> mRssiPollCountsMap = new HashMap<>(); // 信道分布直方图  信道-key  List<rssi>-Value\n } ";
            wifi_22_7.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { 【mRssiPollCountsMap 转为 JsonArray】 JSONObject.toString()} 】 ";
            wifi_22_7.expain1="public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "// Mapping of channel frequency to its RSSI distribution histogram\n" +
                    " Map<Integer, SparseIntArray> mRssiPollCountsMap = new HashMap<>(); // 信道分布直方图  信道-key  List<rssi>-Value\n" +
                    "\n" +
                    " public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    " ...........\n" +
                    " try {\n" +
                    "\tJSONObject rssiMap = new JSONObject();\n" +
                    "\tfor (Map.Entry<Integer, SparseIntArray> entry : mRssiPollCountsMap.entrySet()) {\n" +
                    "\t\tint frequency = entry.getKey();  // 获得信道 \n" +
                    "\t\tfinal SparseIntArray histogram = entry.getValue();  // 获得 rssi的数值集合\n" +
                    "\t\tJSONArray histogramElements = new JSONArray();  // 创建Json对象\n" +
                    "\t\tfor (int i = MIN_RSSI_POLL 【-127】; i <= MAX_RSSI_POLL 【0】; i++) {\n" +
                    "\t\t\tint count = histogram.get(i);\n" +
                    "\t\t\tif (count == 0) {  // 从 -127 到 0 遍历所有可能的信号强度   得到该信道上该信号强度出现的次数\n" +
                    "\t\t\t\tcontinue;\n" +
                    "\t\t\t}\n" +
                    "\t\t\tJSONObject histogramElement = new JSONObject();\n" +
                    "\t\t\thistogramElement.put(Integer.toString(i), count);  // key=信号强度  value=出现次数\n" +
                    "\t\t\thistogramElements.put(histogramElement);  // 放入 JSONArray\n" +
                    "\t\t}\n" +
                    "\t\trssiMap.put(Integer.toString(frequency), histogramElements);  \n" +
                    "\t}\n" +
                    "\t// mWifiLogProto.rssiPollCount: 【 JSONObject = rssiMap 】打印 所有信道上 出现的信道强度的个数\n" +
                    "\tpw.println(\"mWifiLogProto.rssiPollCount: \" + rssiMap.toString()); \n" +
                    "} catch (JSONException e) {\n" +
                    "\tpw.println(\"JSONException occurred: \" + e.getMessage());\n" +
                    "}\n" +
                    "...........\n" +
                    "}\n" +
                    "}\n" +
                    "LOG示例:  记录了 5745  2412  5180这三个信道上  信号强度的分布 信息\n" +
                    "{\"5745\"【信道】:[{\"-52【信号强度】\":2【出现次数】},{\"-51\":4},{\"-50\":3},{\"-49\":6},{\"-48\":9},{\"-47\":17},{\"-46\":18},{\"-45\":24},{\"-44\":25},\n" +
                    "{\"-43\":13},{\"-42\":10},{\"-41\":7},{\"-40\":7},{\"-39\":11},{\"-38\":25},{\"-37\":15},{\"-36\":13},{\"-35\":14},{\"-34\":20},\n" +
                    "{\"-33\":14},{\"-32\":16},{\"-31\":20},{\"-30\":25},{\"-29\":37},{\"-28\":18},{\"-27\":26},{\"-26\":12},{\"-25\":21},{\"-24\":18},\n" +
                    "{\"-23\":2}],\n" +
                    "\"2412\":[{\"-40\":1},{\"-39\":3},{\"-38\":4},{\"-36\":3},{\"-35\":1},{\"-34\":2},{\"-33\":3},{\"-32\":3},{\"-31\":4},{\"-30\":1},\n" +
                    "{\"-29\":3},{\"-28\":11},{\"-27\":9},{\"-26\":18},{\"-25\":13},{\"-24\":7},{\"-23\":5}],\n" +
                    "\"5180\":[{\"-40\":3},{\"-34\":1}],\"2462\":[{\"-32\":2},{\"-26\":1},{\"-19\":4}]}";
            keyWordList.add(wifi_22_7);






            KeyWordItem wifi_22_8 = new KeyWordItem();
            wifi_22_8.keyword = "mWifiLogProto.linkSpeedCounts";
            wifi_22_8.explain=" 连接的网络速递次数统计  " ;
            wifi_22_8.classNameForShuxing = "class WifiMetrics { SparseArray<LinkSpeedCount> mLinkSpeedCounts = new SparseArray<>();// 速度 Mb/s 分布直方图  // com.android.server.wifi.nano.WifiMetricsProto.LinkSpeedCount; 无源码  \n } ";
            wifi_22_8.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { 【mLinkSpeedCounts   SparseArray<LinkSpeedCount> 】 toString } 】 ";
            wifi_22_8.expain1="public cmScanReturnEntrieslass WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "/** Mapping of link speed values to LinkSpeedCount objects. */\n" +
                    "// com.android.server.wifi.nano.WifiMetricsProto.LinkSpeedCount; 无源码 \n" +
                    "SparseArray<LinkSpeedCount> mLinkSpeedCounts = new SparseArray<>();\n" +
                    "\n" +
                    "\n" +
                    "  public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "  ......\t\n" +
                    "   pw.println(\"mWifiLogProto.linkSpeedCounts: \");\n" +
                    "\tsb.setLength(0);\n" +
                    "\tfor (int i = 0; i < mLinkSpeedCounts.size(); i++) {\n" +
                    "\t// 获取每一个 LinkSpeedCount\n" +
                    "\t\tLinkSpeedCount linkSpeedCount = mLinkSpeedCounts.valueAt(i);\n" +
                    "\t\tsb.append(linkSpeedCount.linkSpeedMbps).append(\":{\")\n" +
                    "\t\t\t\t.append(linkSpeedCount.count).append(\", \")\n" +
                    "\t\t\t\t.append(linkSpeedCount.rssiSumDbm).append(\", \")\n" +
                    "\t\t\t\t.append(linkSpeedCount.rssiSumOfSquaresDbmSq).append(\"} \");\n" +
                    "\t}\n" +
                    "\tif (sb.length() > 0) {\n" +
                    "\t\tpw.println(sb.toString());\n" +
                    "\t}\n" +
                    "......\t\n" +
                    "}\n" +
                    "\t\n" +
                    "LOG示例:\n" +
                    "6 【链接速度 6Mb/s】:{9【出现次数】, 266【rssiSumDbm rssi信号强度增益】, 8062【信号增益平方?】} \n" +
                    "13:{11, 502, 23018} 29:{35, 1123, 36465} 39:{2, 73, 2665} 52:{10, 331, 11481} \n" +
                    "58:{19, 615, 20671} 72:{75, 2066, 57994} 86:{13, 406, 12974} 96:{10, 259, 6831} 108:{3, 133, 5901} \n" +
                    "117:{1, 36, 1296} 175:{3, 103, 3581} 200:{120, 5433, 246785} 234:{14, 466, 15868} 263:{12, 365, 11329} \n" +
                    "292:{20, 714, 25856} 351:{41, 1283, 41741} 390【链接速度 390Mb/s】:{156, 4776, 149150} \n" +
                    "}";
            keyWordList.add(wifi_22_8);


            KeyWordItem wifi_22_9 = new KeyWordItem();
            wifi_22_9.keyword = "mWifiLogProto.alertReasonCounts=";
            wifi_22_9.explain=" wifi 警告统计列表 (2【reasonCode】,25【OccurCount】) " ;
            wifi_22_9.classNameForShuxing = "class WifiMetrics { SparseIntArray mWifiAlertReasonCounts   reaconCode列表  \n } ";
            wifi_22_9.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { 【SparseIntArray mWifiAlertReasonCounts】 toString } 】 ";
            wifi_22_9.expain1="WIFI提示原因次数结合\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "// wifi 警告原因reaconCode 集合  能计算出每一种reaconCode 出现的次数\n" +
                    "SparseIntArray mWifiAlertReasonCounts = new SparseIntArray();    /** Mapping of alert reason to the respective alert count. */\n" +
                    "\t\n" +
                    "  public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "  ......\t\n" +
                    "pw.print(\"mWifiLogProto.alertReasonCounts=\");\n" +
                    "\tsb.setLength(0);\n" +
                    "\tfor (int i = WifiLoggerHal.WIFI_ALERT_REASON_MIN 【0】;i <= WifiLoggerHal.WIFI_ALERT_REASON_MAX【1024】; i++) {\n" +
                    "\t\tint count = mWifiAlertReasonCounts.get(i);\n" +
                    "\t\tif (count > 0) {\n" +
                    "\t\t\tsb.append(\"(\" + i + \",\" + count + \"),\");\n" +
                    "\t// Log示例: mWifiLogProto.alertReasonCounts=(2【reasonCode】,25【OccurCount】) (3【reasonCode】,11【OccurCount】) \n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}";
            keyWordList.add(wifi_22_9);


            KeyWordItem wifi_22_10 = new KeyWordItem();
            wifi_22_10.keyword = "mWifiLogProto.numTotalScanResults=";
            wifi_22_10.explain=" wifi 扫描分类 次数 等信息  各种分数的分布 等 ) " ;
            wifi_22_10.classNameForShuxing = "class WifiMetrics { SparseIntArray mWifiScoreCounts[基础分数],mWifiUsabilityScoreCounts[可用性分数]   分数列表  \n } ";
            wifi_22_10.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { 【SparseIntArray mWifiScoreCounts , mWifiUsabilityScoreCounts toString } 】 ";
            wifi_22_10.expain1="WIFI提示原因次数结合\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "// WIFI 基础 分数的范围   \n" +
                    "SparseIntArray mWifiScoreCounts = new SparseIntArray();  /** Mapping of Wifi Scores to counts */\n" +
                    " // WIFI 可用性 分数的范围    \n" +
                    "SparseIntArray mWifiUsabilityScoreCounts = new SparseIntArray();  /** Mapping of Wifi Usability Scores to counts */\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\t\n" +
                    "\n" +
                    "// numTotalScanResults 总的扫描结果 人次的概念? \n" +
                    "pw.println(\"mWifiLogProto.numTotalScanResults=\"+ mWifiLogProto.numTotalScanResults);\n" +
                    "// numOpenNetworkScanResults  总的扫描结果中开放网络的扫描 人次 ? \n" +
                    "pw.println(\"mWifiLogProto.numOpenNetworkScanResults=\"+ mWifiLogProto.numOpenNetworkScanResults);\n" +
                    "// numOpenNetworkScanResults  总的扫描结果中需要输入密码的私人网络 的 扫描 人次 ?\n" +
                    "pw.println(\"mWifiLogProto.numLegacyPersonalNetworkScanResults=\"+ mWifiLogProto.numLegacyPersonalNetworkScanResults);\n" +
                    "// numOpenNetworkScanResults  总的扫描结果中需要企业网络PEAP  的 扫描 人次 ?\n" +
                    "pw.println(\"mWifiLogProto.numLegacyEnterpriseNetworkScanResults=\"+ mWifiLogProto.numLegacyEnterpriseNetworkScanResults);\n" +
                    "// numEnhancedOpenNetworkScanResults 总的扫描结果中WPA3 的开放网络 EnhancedOpenNetwork  的 扫描 人次 ?\n" +
                    "pw.println(\"mWifiLogProto.numEnhancedOpenNetworkScanResults=\"+ mWifiLogProto.numEnhancedOpenNetworkScanResults);\n" +
                    "// numWpa3PersonalNetworkScanResults 总的扫描结果中WPA3加密的企业网络 的 扫描 人次 ?\n" +
                    "pw.println(\"mWifiLogProto.numWpa3PersonalNetworkScanResults=\"+ mWifiLogProto.numWpa3PersonalNetworkScanResults);\n" +
                    "// numWpa3EnterpriseNetworkScanResults  总的扫描结果中WPA3加密的企业网络 的 扫描 人次 ?\n" +
                    "pw.println(\"mWifiLogProto.numWpa3EnterpriseNetworkScanResults=\"+ mWifiLogProto.numWpa3EnterpriseNetworkScanResults);\n" +
                    "// numHiddenNetworkScanResults  总的扫描结果中隐藏网络 扫描到的 人次 ?\n" +
                    "pw.println(\"mWifiLogProto.numHiddenNetworkScanResults=\"+ mWifiLogProto.numHiddenNetworkScanResults);\n" +
                    "//  numHotspot2R1NetworkScanResults  总的扫描结果中隐藏网络 Passpoint_V1 版本网络的扫描 人次?\n" +
                    "pw.println(\"mWifiLogProto.numHotspot2R1NetworkScanResults=\"+ mWifiLogProto.numHotspot2R1NetworkScanResults);\n" +
                    "//  numHotspot2R2NetworkScanResults  总的扫描结果中隐藏网络 Passpoint_V2 版本网络的扫描 人次?\n" +
                    "pw.println(\"mWifiLogProto.numHotspot2R2NetworkScanResults=\"+ mWifiLogProto.numHotspot2R2NetworkScanResults);\n" +
                    "//  numScans  总的开机扫描次数?\n" +
                    "pw.println(\"mWifiLogProto.numScans=\" + mWifiLogProto.numScans);\n" +
                    "//  final int MIN_WIFI_SCORE = 0;    int MAX_WIFI_SCORE = NetworkAgent.WIFI_BASE_SCORE = 60;\n" +
                    "// WifiScoreCount  WIFI分数的范围?   mWifiLogProto.WifiScoreCount: [0, 60]\n" +
                    "pw.println(\"mWifiLogProto.WifiScoreCount: [\" + MIN_WIFI_SCORE + \", \"+ MAX_WIFI_SCORE + \"]\");\n" +
                    "for (int i = 0; i <= MAX_WIFI_SCORE; i++) {\n" +
                    "pw.print(mWifiScoreCounts.get(i) + \" \");  // 打印所有分数 出现的次数  索引就是分数  打印的值就是次数\n" +
                    " }  // WIFI基础分数 Log示例: 0【0(索引下标)分出现0次】 0【1分出现0次】 0 0 0 0 0 0 0 15【10分出现15次】 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" +
                    "}\n" +
                    "// WifiUsabilityScoreCount WIFI 可用性分数范围 \n" +
                    "pw.println(\"mWifiLogProto.WifiUsabilityScoreCount: [\" + MIN_WIFI_USABILITY_SCORE 【0】+ \", \" + MAX_WIFI_USABILITY_SCORE【100】 + \"]\");\n" +
                    "for (int i = MIN_WIFI_USABILITY_SCORE; i <= MAX_WIFI_USABILITY_SCORE; i++) {\n" +
                    "\tpw.print(mWifiUsabilityScoreCounts.get(i) + \" \"); // 可用性分数 Log示例: 0【0(索引下标)分出现0次】 0【1分出现0次】 0 0 0 0 0 0 0 15【10分出现15次】 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" +
                    "}";
            keyWordList.add(wifi_22_10);



            KeyWordItem wifi_22_11 = new KeyWordItem();
            wifi_22_11.keyword = "mWifiLogProto.SoftApManagerReturnCodeCounts:";
            wifi_22_11.explain="WIFI WIFI||热点 打开失败 的一些Log 属性值  " ;
            wifi_22_11.classNameForShuxing = "class WifiMetrics {  WIFI||热点 打开失败信息 } ";
            wifi_22_11.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { } ";
            wifi_22_11.expain1="public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\t\n" +
                    "// SoftApManagerReturnCodeCounts  WIFI的Hostpot 热点打开返回值的统计\n" +
                    "pw.println(\"mWifiLogProto.SoftApManagerReturnCodeCounts:\");\n" +
                    "// SOFT_AP_RETURN_CODE_UNKNOWN=0 未知失败原因打开热点失败的次数 未显示\n" +
                    "// SUCCESS:   成功打开 热点AP的次数\n" +
                    "pw.println(\"  SUCCESS: \" + mSoftApManagerReturnCodeCounts.get(WifiMetricsProto.SoftApReturnCodeCount.SOFT_AP_STARTED_SUCCESSFULLY));\n" +
                    "// FAILED_GENERAL_ERROR:   未知原因打开热点AP失败的次数\n" +
                    "pw.println(\"  FAILED_GENERAL_ERROR: \" + mSoftApManagerReturnCodeCounts.get(WifiMetricsProto.SoftApReturnCodeCount.SOFT_AP_FAILED_GENERAL_ERROR));\n" +
                    "// FAILED_NO_CHANNEL:   没有可用WIFI信道导致的打开热点失败的次数\n" +
                    "pw.println(\"  FAILED_NO_CHANNEL: \" + mSoftApManagerReturnCodeCounts.get(WifiMetricsProto.SoftApReturnCodeCount.SOFT_AP_FAILED_NO_CHANNEL));\n" +
                    "// numHalCrashes   WIFI-HAL 的 crash的次数? \n" +
                    "pw.println(\"mWifiLogProto.numHalCrashes=\"+ mWifiLogProto.numHalCrashes);\n" +
                    "//  numWificondCrashes  wificond 的 crash 次数? \n" +
                    "pw.println(\"mWifiLogProto.numWificondCrashes=\"+ mWifiLogProto.numWificondCrashes);\n" +
                    "pw.println(\"mWifiLogProto.numSupplicantCrashes=\"+ mWifiLogProto.numSupplicantCrashes);\n" +
                    "//  numHostapdCrashes  hostapd 进程 crash 的次数\n" +
                    "pw.println(\"mWifiLogProto.numHostapdCrashes=\"+ mWifiLogProto.numHostapdCrashes);\n" +
                    "//   numSetupClientInterfaceFailureDueToHal  由于hal导致的初始化错误的次数\n" +
                    "pw.println(\"mWifiLogProto.numSetupClientInterfaceFailureDueToHal=\"+ mWifiLogProto.numSetupClientInterfaceFailureDueToHal);\n" +
                    "//   numSetupClientInterfaceFailureDueToWificond  由于Wificond导致的初始化错误的次数\n" +
                    "pw.println(\"mWifiLogProto.numSetupClientInterfaceFailureDueToWificond=\"+ mWifiLogProto.numSetupClientInterfaceFailureDueToWificond);\n" +
                    "//   numSetupClientInterfaceFailureDueToSupplicant  由于Supplicant导致的初始化错误的次数\n" +
                    "pw.println(\"mWifiLogProto.numSetupClientInterfaceFailureDueToSupplicant=\"+ mWifiLogProto.numSetupClientInterfaceFailureDueToSupplicant);\n" +
                    "//   numSetupSoftApInterfaceFailureDueToHal  由于 hal 导致的初始化热点错误的次数\n" +
                    "pw.println(\"mWifiLogProto.numSetupSoftApInterfaceFailureDueToHal=\"+ mWifiLogProto.numSetupSoftApInterfaceFailureDueToHal);\n" +
                    "//   numSetupSoftApInterfaceFailureDueToWificond  由于 Wificond 导致的初始化热点错误的次数\n" +
                    "pw.println(\"mWifiLogProto.numSetupSoftApInterfaceFailureDueToWificond=\"+ mWifiLogProto.numSetupSoftApInterfaceFailureDueToWificond);\n" +
                    "//   numSetupSoftApInterfaceFailureDueToHostapd  由于 Hostapd 导致的初始化热点错误的次数\n" +
                    "pw.println(\"mWifiLogProto.numSetupSoftApInterfaceFailureDueToHostapd=\"+ mWifiLogProto.numSetupSoftApInterfaceFailureDueToHostapd);\n" +
                    "// numSarSensorRegistrationFailures  注册监听IO失败的次数  SarManager.java  Linux统计/监控工具SAR 网络 IO 等统计？ 观察服务负载 CPU和内存的占用率、网络的使用率以及磁盘写入和读取速度等。\n" +
                    "pw.println(\"mWifiLogProto.numSarSensorRegistrationFailures=\"+ mWifiLogProto.numSarSensorRegistrationFailures);\n" +
                    "\t\t\t\t\t\t\n" +
                    "}";
            keyWordList.add(wifi_22_11);



            KeyWordItem wifi_22_12 = new KeyWordItem();
            wifi_22_12.keyword = "StaEventList:";
            wifi_22_12.explain="手机作为 WIFI的STA 所遇到的事件  LinkedList<StaEventWithTime> mStaEventList " ;
            wifi_22_12.classNameForShuxing = "class WifiMetrics {  LinkedList<StaEventWithTime> mStaEventList  } \n StaEventWithTime={time+StaEvent}";
            wifi_22_12.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() {  LinkedList<StaEventWithTime>   } ";
            wifi_22_12.expain1="******************** StaEvent.EventType 类型集合枚举  Begin ********************\n" +
                    " enum EventType {\n" +
                    "    TYPE_UNKNOWN = 0;    // Default/Invalid event\n" +
                    "    TYPE_ASSOCIATION_REJECTION_EVENT = 1;     // Supplicant Association Rejection event. Code contains the 802.11\n" +
                    "    TYPE_AUTHENTICATION_FAILURE_EVENT = 2;     // Supplicant L2 event,\n" +
                    "    TYPE_NETWORK_CONNECTION_EVENT = 3;     // Supplicant L2 event\n" +
                    "    TYPE_NETWORK_DISCONNECTION_EVENT = 4;     // Supplicant L2 event\n" +
                    "    TYPE_SUPPLICANT_STATE_CHANGE_EVENT = 5;     // Supplicant L2 event\n" +
                    "    TYPE_CMD_ASSOCIATED_BSSID = 6;     // Supplicant L2 event\n" +
                    "    TYPE_CMD_IP_CONFIGURATION_SUCCESSFUL = 7;     // IP Manager successfully completed IP Provisioning\n" +
                    "    TYPE_CMD_IP_CONFIGURATION_LOST = 8;     // IP Manager failed to complete IP Provisioning\n" +
                    "    TYPE_CMD_IP_REACHABILITY_LOST = 9;     // IP Manager lost reachability to network neighbors\n" +
                    "    TYPE_CMD_TARGET_BSSID = 10;     // Indicator that Supplicant is targeting a BSSID for roam/connection\n" +
                    "    TYPE_CMD_START_CONNECT = 11;     // Wifi framework is initiating a connection attempt\n" +
                    "    TYPE_CMD_START_ROAM = 12;     // Wifi framework is initiating a roaming connection attempt\n" +
                    "    TYPE_CONNECT_NETWORK = 13;    // SystemAPI connect() command, Settings App\n" +
                    "    // Network Agent has validated the internet connection (Captive Portal Check success, or user validation)\n" +
                    "    TYPE_NETWORK_AGENT_VALID_NETWORK = 14;\n" +
                    "    // Framework initiated disconnect. Sometimes generated to give an extra reason for a disconnect\n" +
                    "    // Should typically be followed by a NETWORK_DISCONNECTION_EVENT with a local_gen = true\n" +
                    "    TYPE_FRAMEWORK_DISCONNECT = 15;\n" +
                    "    // The NetworkAgent score for wifi has changed in a way that may impact    // connectivity\n" +
                    "    TYPE_SCORE_BREACH = 16;\n" +
                    "    TYPE_MAC_CHANGE = 17;    // Framework changed Sta interface MAC address\n" +
                    "    TYPE_WIFI_ENABLED = 18;   // Wifi is turned on\n" +
                    "    TYPE_WIFI_DISABLED = 19;    // Wifi is turned off \n" +
                    "    // The NetworkAgent Wifi usability score has changed in a way that may  // impact connectivity\n" +
                    "    TYPE_WIFI_USABILITY_SCORE_BREACH = 20;\n" +
                    "    TYPE_LINK_PROBE = 21;  // Link probe was performed\n" +
                    "  }\n" +
                    "******************** StaEvent.EventType 类型集合枚举  End ********************\n" +
                    "  \n" +
                    "******************** StaEventWithTime Begin  ********************\n" +
                    "private static class StaEventWithTime {\n" +
                    "public StaEvent staEvent;  //机密的 无法看源码 com.android.server.wifi.nano.WifiMetricsProto.StaEvent;  \n" +
                    "public long wallClockMillis;   // 事件产生的时间 \n" +
                    "}\n" +
                    "\n" +
                    "// 打印事件 Event的方法\n" +
                    "public static String staEventToString(StaEvent event) {\n" +
                    "        if (event == null) return \"<NULL>\";\n" +
                    "        StringBuilder sb = new StringBuilder();\n" +
                    "        switch (event.type) {  //  enum StaEvent.EventType  事件类型 \n" +
                    "            case StaEvent.TYPE_ASSOCIATION_REJECTION_EVENT:\n" +
                    "                sb.append(\"ASSOCIATION_REJECTION_EVENT\")\n" +
                    "                        .append(\" timedOut=\").append(event.associationTimedOut)\n" +
                    "                        .append(\" status=\").append(event.status).append(\":\")\n" +
                    "                        .append(ISupplicantStaIfaceCallback.StatusCode.toString(event.status));\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_AUTHENTICATION_FAILURE_EVENT:\n" +
                    "                sb.append(\"AUTHENTICATION_FAILURE_EVENT reason=\").append(event.authFailureReason)\n" +
                    "                        .append(\":\").append(authFailureReasonToString(event.authFailureReason));\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_NETWORK_CONNECTION_EVENT:\n" +
                    "                sb.append(\"NETWORK_CONNECTION_EVENT\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_NETWORK_DISCONNECTION_EVENT:\n" +
                    "                sb.append(\"NETWORK_DISCONNECTION_EVENT\")\n" +
                    "                        .append(\" local_gen=\").append(event.localGen)\n" +
                    "                        .append(\" reason=\").append(event.reason).append(\":\")\n" +
                    "                        .append(ISupplicantStaIfaceCallback.ReasonCode.toString(\n" +
                    "                                (event.reason >= 0 ? event.reason : -1 * event.reason)));\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_CMD_ASSOCIATED_BSSID:\n" +
                    "                sb.append(\"CMD_ASSOCIATED_BSSID\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_CMD_IP_CONFIGURATION_SUCCESSFUL:\n" +
                    "                sb.append(\"CMD_IP_CONFIGURATION_SUCCESSFUL\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_CMD_IP_CONFIGURATION_LOST:\n" +
                    "                sb.append(\"CMD_IP_CONFIGURATION_LOST\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_CMD_IP_REACHABILITY_LOST:\n" +
                    "                sb.append(\"CMD_IP_REACHABILITY_LOST\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_CMD_TARGET_BSSID:\n" +
                    "                sb.append(\"CMD_TARGET_BSSID\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_CMD_START_CONNECT:\n" +
                    "                sb.append(\"CMD_START_CONNECT\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_CMD_START_ROAM:\n" +
                    "                sb.append(\"CMD_START_ROAM\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_CONNECT_NETWORK:\n" +
                    "                sb.append(\"CONNECT_NETWORK\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_NETWORK_AGENT_VALID_NETWORK:\n" +
                    "                sb.append(\"NETWORK_AGENT_VALID_NETWORK\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_FRAMEWORK_DISCONNECT:\n" +
                    "                sb.append(\"FRAMEWORK_DISCONNECT\")\n" +
                    "                        .append(\" reason=\")\n" +
                    "                        .append(frameworkDisconnectReasonToString(event.frameworkDisconnectReason));\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_SCORE_BREACH:\n" +
                    "                sb.append(\"SCORE_BREACH\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_MAC_CHANGE:\n" +
                    "                sb.append(\"MAC_CHANGE\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_WIFI_ENABLED:\n" +
                    "                sb.append(\"WIFI_ENABLED\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_WIFI_DISABLED:\n" +
                    "                sb.append(\"WIFI_DISABLED\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_WIFI_USABILITY_SCORE_BREACH:\n" +
                    "                sb.append(\"WIFI_USABILITY_SCORE_BREACH\");\n" +
                    "                break;\n" +
                    "            case StaEvent.TYPE_LINK_PROBE:\n" +
                    "                sb.append(\"LINK_PROBE\");\n" +
                    "                sb.append(\" linkProbeWasSuccess=\").append(event.linkProbeWasSuccess);\n" +
                    "                if (event.linkProbeWasSuccess) {\n" +
                    "                    sb.append(\" linkProbeSuccessElapsedTimeMs=\")\n" +
                    "                            .append(event.linkProbeSuccessElapsedTimeMs);\n" +
                    "                } else {\n" +
                    "                    sb.append(\" linkProbeFailureReason=\").append(event.linkProbeFailureReason);\n" +
                    "                }\n" +
                    "                break;\n" +
                    "            default:\n" +
                    "                sb.append(\"UNKNOWN \" + event.type + \":\");\n" +
                    "                break;\n" +
                    "        }\n" +
                    "        if (event.lastRssi != -127) sb.append(\" lastRssi=\").append(event.lastRssi);\n" +
                    "        if (event.lastFreq != -1) sb.append(\" lastFreq=\").append(event.lastFreq);\n" +
                    "        if (event.lastLinkSpeed != -1) sb.append(\" lastLinkSpeed=\").append(event.lastLinkSpeed);\n" +
                    "        if (event.lastScore != -1) sb.append(\" lastScore=\").append(event.lastScore);\n" +
                    "        if (event.lastWifiUsabilityScore != -1) {\n" +
                    "            sb.append(\" lastWifiUsabilityScore=\").append(event.lastWifiUsabilityScore);\n" +
                    "            sb.append(\" lastPredictionHorizonSec=\").append(event.lastPredictionHorizonSec);\n" +
                    "        }\n" +
                    "        if (event.supplicantStateChangesBitmask != 0) {\n" +
                    "            sb.append(\", \").append(supplicantStateChangesBitmaskToString( event.supplicantStateChangesBitmask));\n" +
                    "        }\n" +
                    "        if (event.configInfo != null) {\n" +
                    "            sb.append(\", \").append(configInfoToString(event.configInfo));\n" +
                    "        }\n" +
                    "        return sb.toString();\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "******************** StaEventWithTime End  ********************\n" +
                    "\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "LinkedList<StaEventWithTime> mStaEventList = new LinkedList<>();\n" +
                    "\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\t\n" +
                    "pw.println(\"StaEventList:\");   // 打印: LinkedList<StaEventWithTime>   \n" +
                    "for (StaEventWithTime event : mStaEventList) {\n" +
                    "pw.println(event);\n" +
                    "}\n" +
                    "......\t\n" +
                    "}\n" +
                    "}\n";
            keyWordList.add(wifi_22_12);





            KeyWordItem wifi_22_13 = new KeyWordItem();
            wifi_22_13.keyword = "mWifiLogProto.numPasspointProviders=";
            wifi_22_13.explain=" Passpoint网络的相关信息 " ;
            wifi_22_13.classNameForShuxing = "class WifiMetrics { Map<String, PasspointProvider> mProviders;  }";
            wifi_22_13.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { Map<String, PasspointProvider> mProviders  } ";
            wifi_22_13.expain1="********************  PasspointProvider Begin  ********************\n" +
                    "\n" +
                    "public class PasspointProvider {\n" +
                    " final String TAG = \"PasspointProvider\";\n" +
                    "PasspointConfiguration mConfig;  // passpoint的配置文件\n" +
                    "WifiKeyStore mKeyStore;\n" +
                    "}\n" +
                    "********************  PasspointProvider Begin  ********************\n" +
                    "\n" +
                    "********************  PasspointManager Begin  ********************\n" +
                    "class PasspointManager { \n" +
                    " Map<String, PasspointProvider> mProviders;\n" +
                    " \n" +
                    " R2标识与R1 passpoint的区别\n" +
                    " 1. passpoint的id 即 mUpdateIdentifier 不为 默认的 Integer.MIN_VALUE\n" +
                    " 2. UpdateParameter mSubscriptionUpdate = null;\n" +
                    "    订阅passpoint的元数据 Meta data 不为空(默认的空)  并且该订阅是有效的\n" +
                    " 3. HomeSp mHomeSp = null;  // Configurations under HomeSp subtree.\n" +
                    "     HomeSp 不为空  并且  mHomeSp.validate() == true \n" +
                    "  4. Credential mCredential = null;  // Configurations under Credential subtree. \n" +
                    "     Credential 不为空  并且  mCredential.validate() == true \n" +
                    "  5.Policy mPolicy = null;  Configurations under Policy subtree.\n" +
                    "\t Policy 不为空  并且  mPolicy.validate() == true \n" +
                    "  5.1  如果当前 Map<String, byte[]> mTrustRootCertList = null;  为 空 那么这个就是 R2-type的passpoint网络\n" +
                    "       go to 6\n" +
                    "  5.2  如果当前 Map<String, byte[]> mTrustRootCertList != null 不为空 那么判断\n" +
                    "        5.2.1 是否 Map-key<String> 为空  为空 就不是 R2-type的passpoint网络\n" +
                    "\t\t5.2.2 是否 Map-key<String> 不为空 但是UTF-8编码的字符串大于长度 1023 ， 就不是 R2-type的passpoint网络\n" +
                    "        5.2.3 是否 Map-Value<byte[]> 为空  为空 就不是 R2-type的passpoint网络\n" +
                    "        5.2.4 是否 Map-Value<byte[]> 不为空  长度不等于 32  就不是 R2-type的passpoint网络\n" +
                    "\t\t go to 6\n" +
                    "  6.   如果当前 Map<String, byte[]> mTrustRootCertList = null; 为 空 那么这个就是 R2-type的passpoint网络\n" +
                    "  \n" +
                    "\t \n" +
                    "  int mUpdateIdentifier = Integer.MIN_VALUE;  \n" +
                    "     public boolean validateForR2() {\n" +
                    "        if (mUpdateIdentifier == Integer.MIN_VALUE) {   // Required: PerProviderSubscription/UpdateIdentifier\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "\n" +
                    "        // Required: PerProviderSubscription/<X+>/SubscriptionUpdate\n" +
                    "        if (mSubscriptionUpdate == null || !mSubscriptionUpdate.validate()) {\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "        return validateForCommonR1andR2(false);\n" +
                    "    }\n" +
                    "\t\n" +
                    " }\n" +
                    " \n" +
                    " \n" +
                    "     private boolean validateForCommonR1andR2(boolean isR1) {\n" +
                    "        // Required: PerProviderSubscription/<X+>/HomeSP\n" +
                    "        if (mHomeSp == null || !mHomeSp.validate()) {\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "\n" +
                    "        // Required: PerProviderSubscription/<X+>/Credential\n" +
                    "        if (mCredential == null || !mCredential.validate(isR1)) {\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "\n" +
                    "        // Optional: PerProviderSubscription/<X+>/Policy\n" +
                    "        if (mPolicy != null && !mPolicy.validate()) {\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "\n" +
                    "        if (mTrustRootCertList != null) {\n" +
                    "            for (Map.Entry<String, byte[]> entry : mTrustRootCertList.entrySet()) {\n" +
                    "                String url = entry.getKey();\n" +
                    "                byte[] certFingerprint = entry.getValue();\n" +
                    "                if (TextUtils.isEmpty(url)) {\n" +
                    "                    Log.d(TAG, \"Empty URL\");\n" +
                    "                    return false;\n" +
                    "                }\n" +
                    "                if (url.getBytes(StandardCharsets.UTF_8).length > MAX_URL_BYTES) {\n" +
                    "                    Log.d(TAG, \"URL bytes exceeded the max: \"  + url.getBytes(StandardCharsets.UTF_8).length);\n" +
                    "                    return false;\n" +
                    "                }\n" +
                    "\n" +
                    "                if (certFingerprint == null) {\n" +
                    "                    Log.d(TAG, \"Fingerprint not specified\");\n" +
                    "                    return false;\n" +
                    "                }\n" +
                    "                if (certFingerprint.length != CERTIFICATE_SHA256_BYTES 【32】) {\n" +
                    "                    Log.d(TAG, \"Incorrect size of trust root certificate SHA-256 fingerprint: \"  + certFingerprint.length);\n" +
                    "                    return false;\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return true;\n" +
                    "    }\n" +
                    "\t\n" +
                    "********************  PasspointManager End  ********************\n" +
                    "\n" +
                    "public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "IntCounter mInstalledPasspointProfileTypeForR1 = new IntCounter();\n" +
                    "IntCounter mInstalledPasspointProfileTypeForR2 = new IntCounter();\n" +
                    "\t\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\t\n" +
                    "// numPasspointProviders   标识 当前保存的 PasspointConfiguration 配置个数  PasspointManager.Map<String, PasspointProvider> mProviders; 大小\n" +
                    "pw.println(\"mWifiLogProto.numPasspointProviders=\"+ mWifiLogProto.numPasspointProviders);\n" +
                    "// numPasspointProviderInstallation  执行更新passpoint或者增加passpoint网络的执行次数  PasspointManager.java-》addOrUpdateProvider 会使得这个值增加\n" +
                    "pw.println(\"mWifiLogProto.numPasspointProviderInstallation=\"+ mWifiLogProto.numPasspointProviderInstallation);\n" +
                    "// numPasspointProviderInstallSuccess  执行增加passpoint网络 成功的次数  PasspointManager.java-》addOrUpdateProvider 返回 true时 增加这个值\n" +
                    "pw.println(\"mWifiLogProto.numPasspointProviderInstallSuccess=\"+ mWifiLogProto.numPasspointProviderInstallSuccess);\n" +
                    "// numPasspointProviderUninstallation  移除 passpoint网络 PasspointManager.java-》boolean removeProvider(String fqdn) 只要执行就会增加这个值\n" +
                    "pw.println(\"mWifiLogProto.numPasspointProviderUninstallation=\"+ mWifiLogProto.numPasspointProviderUninstallation);\n" +
                    "// numPasspointProviderUninstallSuccess   成功移除passpoint网络的次数    PasspointManager.java-》boolean removeProvider(String fqdn) 返回true时增加\n" +
                    "pw.println(\"mWifiLogProto.numPasspointProviderUninstallSuccess=\"+ mWifiLogProto.numPasspointProviderUninstallSuccess);\n" +
                    "// numPasspointProvidersSuccessfullyConnected   成功连接Passpoint网络的次数 \n" +
                    "pw.println(\"mWifiLogProto.numPasspointProvidersSuccessfullyConnected=\"+ mWifiLogProto.numPasspointProvidersSuccessfullyConnected);\n" +
                    "// installedPasspointProfileTypeForR1  成功添加passpoint_R1 类型的网络的个数\n" +
                    "pw.println(\"mWifiLogProto.installedPasspointProfileTypeForR1:\"+ mInstalledPasspointProfileTypeForR1);\n" +
                    "// mInstalledPasspointProfileTypeForR2   成功添加passpoint_R2 类型的网络的个数   判断是否是R2网络 validateForCommonR1andR2\n" +
                    "pw.println(\"mWifiLogProto.installedPasspointProfileTypeForR2:\"+ mInstalledPasspointProfileTypeForR2);\n" +
                    "/**\n" +
                    "* Installs the trust root CA certificates for AAA, Remediation and Policy Server\n" +
                    "*\n" +
                    "* @param sessionId             indicating current session ID\n" +
                    "* @param trustRootCertificates trust root CA certificates to be installed.\n" +
                    "*/\n" +
                    "// numProvisionSuccess 成功与认证服务器AAA 进行身份验证成功的次数  PasspointProvisioner.java=》void installTrustRootCertificates()\n" +
                    "pw.println(\"mWifiLogProto.passpointProvisionStats.numProvisionSuccess=\"+ mNumProvisionSuccess);\n" +
                    "// mPasspointProvisionFailureCounts  与认证服务器AAA 验证身份失败次数   PasspointProvisioner.java=》resetStateMachineForFailure(int reason)\n" +
                    "pw.println(\"mWifiLogProto.passpointProvisionStats.provisionFailureCount:\"+ mPasspointProvisionFailureCounts);\n" +
                    "......\n" +
                    "}";
            keyWordList.add(wifi_22_13);



            KeyWordItem wifi_22_14 = new KeyWordItem();
            wifi_22_14.keyword = "mWifiLogProto.numRadioModeChangeToMcc";
            wifi_22_14.explain=" 硬件工作模式相关的信息 wifi天线? [ Dbs 实时双频 Wi-Fi技术] [Sbs 实时单频 Wi-Fi技术]  " ;
            wifi_22_14.classNameForShuxing = "class WifiMetrics { numRadioModeChangeTo[DBS][SBS][SCC][MCC]   }";
            wifi_22_14.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() { numRadioModeChangeTo[DBS][SBS][SCC][MCC]   } ";
            wifi_22_14.expain1="// 硬件相关的 工作模式? \n" +
                    "// numSoftApUserBandPreferenceUnsatisfied 热点channel的频道无法使用导致打开失败的次数 //  Increment number of times we detected a channel did not satisfy user band preference.\n" +
                    "// numRadioModeChangeToDbs = Increment number of times we detected a radio mode change to DBS. // Dbs 实时双频 Wi-Fi技术\n" +
                    "// numRadioModeChangeToSbs = Increment number of times we detected a radio mode change to SBS. //  Sbs 实时单频 Wi-Fi技术\n" +
                    "// numRadioModeChangeToScc = Increment number of times we detected a radio mode change to SCC.  // 子载波?   Secondary CC  \n" +
                    "// numRadioModeChangeToMcc  =  Increment number of times we detected a radio mode change to MCC.  // 主载波 \n" +
                    "pw.println(\"mWifiLogProto.numRadioModeChangeToMcc=\"+ mWifiLogProto.numRadioModeChangeToMcc);\n" +
                    "pw.println(\"mWifiLogProto.numRadioModeChangeToScc=\"+ mWifiLogProto.numRadioModeChangeToScc);\n" +
                    "pw.println(\"mWifiLogProto.numRadioModeChangeToSbs=\"+ mWifiLogProto.numRadioModeChangeToSbs);\n" +
                    "pw.println(\"mWifiLogProto.numRadioModeChangeToDbs=\"+ mWifiLogProto.numRadioModeChangeToDbs);  \n" +
                    "pw.println(\"mWifiLogProto.numSoftApUserBandPreferenceUnsatisfied=\"+ mWifiLogProto.numSoftApUserBandPreferenceUnsatisfied);\n";
            keyWordList.add(wifi_22_14);



            KeyWordItem wifi_22_15 = new KeyWordItem();
            wifi_22_15.keyword = "mTotalSsidsInScanHistogram:";
            wifi_22_15.explain=" 《扫描SSID的个数---扫描到这个数量的次数》 《扫描BSSID的个数---扫描到这个数量的次数》 " ;
            wifi_22_15.classNameForShuxing = "class WifiMetrics { SparseIntArray  mTotalSsidsInScanHistogram  }";
            wifi_22_15.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => dump() {SparseIntArray  mTotalSsidsInScanHistogram } ";
            wifi_22_15.expain1="public class WifiMetrics {  【com.android.server.wifi.nano.WifiMetricsProto.WifiLog 加密的包】\n" +
                    "\n" +
                    "SparseIntArray mTotalSsidsInScanHistogram = new SparseIntArray();\n" +
                    "SparseIntArray mTotalBssidsInScanHistogram = new SparseIntArray();\n" +
                    "SparseIntArray mAvailableOpenSsidsInScanHistogram = new SparseIntArray();\n" +
                    "SparseIntArray mAvailableOpenBssidsInScanHistogram = new SparseIntArray();\n" +
                    "SparseIntArray mAvailableSavedSsidsInScanHistogram = new SparseIntArray();\n" +
                    "SparseIntArray mAvailableSavedBssidsInScanHistogram = new SparseIntArray();\n" +
                    "SparseIntArray mAvailableOpenOrSavedSsidsInScanHistogram = new SparseIntArray();\n" +
                    "SparseIntArray mAvailableOpenOrSavedBssidsInScanHistogram = new SparseIntArray();\n" +
                    "SparseIntArray mAvailableSavedPasspointProviderProfilesInScanHistogram =new SparseIntArray();\n" +
                    "SparseIntArray mAvailableSavedPasspointProviderBssidsInScanHistogram = new SparseIntArray();\n" +
                    "\t\t\t\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "......\t\n" +
                    "pw.println(\"mTotalSsidsInScanHistogram:\"+ mTotalSsidsInScanHistogram.toString());\n" +
                    "pw.println(\"mTotalBssidsInScanHistogram:\"+ mTotalBssidsInScanHistogram.toString());\n" +
                    "pw.println(\"mAvailableOpenSsidsInScanHistogram:\"+ mAvailableOpenSsidsInScanHistogram.toString());\n" +
                    "pw.println(\"mAvailableOpenBssidsInScanHistogram:\"+ mAvailableOpenBssidsInScanHistogram.toString());\n" +
                    "pw.println(\"mAvailableSavedSsidsInScanHistogram:\"+ mAvailableSavedSsidsInScanHistogram.toString());\n" +
                    "pw.println(\"mAvailableSavedBssidsInScanHistogram:\"+ mAvailableSavedBssidsInScanHistogram.toString());\n" +
                    "pw.println(\"mAvailableOpenOrSavedSsidsInScanHistogram:\"+ mAvailableOpenOrSavedSsidsInScanHistogram.toString());\n" +
                    "pw.println(\"mAvailableOpenOrSavedBssidsInScanHistogram:\"+ mAvailableOpenOrSavedBssidsInScanHistogram.toString());\n" +
                    "pw.println(\"mAvailableSavedPasspointProviderProfilesInScanHistogram:\"+ mAvailableSavedPasspointProviderProfilesInScanHistogram.toString());\n" +
                    "pw.println(\"mAvailableSavedPasspointProviderBssidsInScanHistogram:\"+ mAvailableSavedPasspointProviderBssidsInScanHistogram.toString());\n" +
                    "pw.println(\"mWifiLogProto.partialAllSingleScanListenerResults=\"+ mWifiLogProto.partialAllSingleScanListenerResults);\n" +
                    "pw.println(\"mWifiLogProto.fullBandAllSingleScanListenerResults=\"+ mWifiLogProto.fullBandAllSingleScanListenerResults);\n" +
                    "......\n" +
                    "}\n" +
                    "\n" +
                    "Log示例\n" +
                    "rssi2.4G = {-88【level_1-EXIT】, -77【level_2-ENTRY】, -66【level_3-SUFFICIENT】, -55【level_4-GOOD】 };\n" +
                    "【level_3有效扫描到 Key 网络的个数】=【扫描到 Key个网络的次数 Value 】\n" +
                    "7【level_3有效扫描到7网络的个数】=5【扫描到7个网络的次数为5】, 10【level_3有效扫描到10网络的个数】=2【扫描到10个网络的次数为2】\n" +
                    "mTotalSsidsInScanHistogram:{7=5,10=2, 11=5, 12=75, 13=161, 14=104, 15=106, 16=86, 17=89, 18=60, 19=22, 20=18, 21=4, 22=2}\n" +
                    "\n" +
                    "【一个SSID包含多个BSSID , 所以这里以bssid为计量单位的扫描结果】\n" +
                    "【30=2 , 扫描到30个BSSID的扫描次数为2 】 【48=6 , 扫描到48个BSSID的扫描次数为6 】\n" +
                    "mTotalBssidsInScanHistogram:{30=2, 36=1, 37=1, 39=1, 41=2, 42=1, 43=4, 44=1, 45=5, 46=3, 47=1, 48=6, 49=17, 50=14, 51=16, 52=23, 53=32, 54=31, 55=20, 56=37, 57=36, 58=36, 59=39, 60=49, 61=52, 62=32, 63=28, 64=41, 65=29, 66=30, 67=30, 68=20, 69=26, 70=20, 71=12, 72=16, 73=10, 74=4, 75=5, 76=1, 77=2, 79=1, 80=1, 81=1}\n" +
                    "mAvailableOpenSsidsInScanHistogram:{2=60, 3=17, 4=659, 5=2, 6=1}\n" +
                    "mAvailableOpenBssidsInScanHistogram:{9=1, 10=1, 11=2, 12=1, 13=2, 14=11, 15=9, 16=11, 17=40, 18=67, 19=69, 20=95, 21=75, 22=83, 23=87, 24=74, 25=41, 26=37, 27=17, 28=15, 30=1}\n" +
                    "mAvailableSavedSsidsInScanHistogram:{0=558, 1=45, 2=14, 3=82, 4=39, 5=1}\n" +
                    "mAvailableSavedBssidsInScanHistogram:{0=558, 1=45, 2=6, 7=1, 8=2, 9=10, 10=17, 11=28, 12=16, 13=15, 14=1, 16=1, 17=2, 18=10, 19=3, 20=3, 21=7, 22=8, 23=2, 25=1, 26=2, 28=1}\n" +
                    "mAvailableOpenOrSavedSsidsInScanHistogram:{2=49, 3=25, 4=622, 5=41, 6=2} //2=49=》 扫描到 2个开放或者已保存的SSID为49次\n" +
                    "mAvailableOpenOrSavedBssidsInScanHistogram:{10=2, 11=1, 12=2, 13=2, 14=6, 15=13, 16=10, 17=37, 18=61, 19=63, 20=92, 21=70, 22=77, 23=84, 24=74, 25=42, 26=39, 27=21, 28=17, 29=3, 30=5, 31=6, 32=3, 33=1, 34=4, 35=1, 37=1, 38=1, 39=1}\n" +
                    "mAvailableSavedPasspointProviderProfilesInScanHistogram:{0=739} //  扫描到 0 个 passpoint网络ssid的次数为 739\n" +
                    "mAvailableSavedPasspointProviderBssidsInScanHistogram:{0=739} //  扫描到 0 个 passpoint网络bssid的次数为 739\n" +
                    "mWifiLogProto.partialAllSingleScanListenerResults=0   //  部分信道扫描的执行次数\n" +
                    "mWifiLogProto.fullBandAllSingleScanListenerResults=739  //  全信道扫描的执行次数";
            keyWordList.add(wifi_22_15);


            KeyWordItem wifi_22_16 = new KeyWordItem();
            wifi_22_16.keyword = "mWifiAwareMetrics:";
            wifi_22_16.explain="WIFI感知统计 省电方面 WifiAwareMetrics mWifiAwareMetrics " ;
            wifi_22_16.classNameForShuxing = "class WifiMetrics { SparseIntArray WifiAwareMetrics mWifiAwareMetrics  }";
            wifi_22_16.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => WifiAwareMetrics.dump() { } ";
            wifi_22_16.expain1="Log示例:  未知 都是初始化的值\n" +
                    "mWifiAwareMetrics:\n" +
                    "mLastEnableUsageMs:0\n" +
                    "mLastEnableUsageInThisSampleWindowMs:0\n" +
                    "mAvailableTimeMs:0\n" +
                    "mHistogramAwareAvailableDurationMs:\n" +
                    "mLastEnableAwareMs:0\n" +
                    "mLastEnableAwareInThisSampleWindowMs:0\n" +
                    "mEnabledTimeMs:0\n" +
                    "mHistogramAwareEnabledDurationMs:\n" +
                    "mAttachDataByUid:\n" +
                    "mAttachStatusData:\n" +
                    "mHistogramAttachDuration:\n" +
                    "mMaxPublishInApp:0\n" +
                    "mMaxSubscribeInApp:0\n" +
                    "mMaxDiscoveryInApp:0\n" +
                    "mMaxPublishInSystem:0\n" +
                    "mMaxSubscribeInSystem:0\n" +
                    "mMaxDiscoveryInSystem:0\n" +
                    "mPublishStatusData:\n" +
                    "mSubscribeStatusData:\n" +
                    "mHistogramPublishDuration:\n" +
                    "mHistogramSubscribeDuration:\n" +
                    "mAppsWithDiscoverySessionResourceFailure:\n" +
                    "mMaxPublishWithRangingInApp:0\n" +
                    "mMaxSubscribeWithRangingInApp:0\n" +
                    "mMaxPublishWithRangingInSystem:0\n" +
                    "mMaxSubscribeWithRangingInSystem:0\n" +
                    "mHistogramSubscribeGeofenceMin:\n" +
                    "mHistogramSubscribeGeofenceMax:\n" +
                    "mNumSubscribesWithRanging:0\n" +
                    "mNumMatchesWithRanging:0\n" +
                    "mNumMatchesWithoutRangingForRangingEnabledSubscribes:0\n" +
                    "mMaxNdiInApp:0\n" +
                    "mMaxNdpInApp:0\n" +
                    "mMaxSecureNdpInApp:0\n" +
                    "mMaxNdiInSystem:0\n" +
                    "mMaxNdpInSystem:0\n" +
                    "mMaxSecureNdpInSystem:0\n" +
                    "mMaxNdpPerNdi:0\n" +
                    "mInBandNdpStatusData:\n" +
                    "mOutOfBandNdpStatusData:\n" +
                    "mNdpCreationTimeDuration:\n" +
                    "mNdpCreationTimeMin:-1\n" +
                    "mNdpCreationTimeMax:0\n" +
                    "mNdpCreationTimeSum:0\n" +
                    "mNdpCreationTimeSumSq:0\n" +
                    "mNdpCreationTimeNumSamples:0\n" +
                    "mHistogramNdpDuration:";
            keyWordList.add(wifi_22_16);


            KeyWordItem wifi_22_17 = new KeyWordItem();
            wifi_22_17.keyword = "mRttMetrics:";
            wifi_22_17.explain="WIFI感知统计 省电方面 WifiAwareMetrics mWifiAwareMetrics " ;
            wifi_22_17.classNameForShuxing = "class WifiMetrics { SparseIntArray WifiAwareMetrics mWifiAwareMetrics  }";
            wifi_22_17.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => WifiAwareMetrics.dump() { } ";
            wifi_22_17.expain1="public class WifiMetrics {  \n" +
                    "\n" +
                    "RttMetrics mRttMetrics;\n" +
                    "\n" +
                    "dump(){\n" +
                    ".......\n" +
                    "pw.println(\"mRttMetrics:\");\n" +
                    "mRttMetrics.dump(fd, pw, args);\n" +
                    "\n" +
                    "}\n" +
                    "**************  PerPeerTypeInfo Begin **************\n" +
                    "    private class PerPeerTypeInfo {\n" +
                    "        public int numCalls;\n" +
                    "        public int numIndividualCalls;\n" +
                    "        public SparseArray<PerUidInfo> perUidInfo = new SparseArray<>();\n" +
                    "        public SparseIntArray numRequestsHistogram = new SparseIntArray();\n" +
                    "        public SparseIntArray requestGapHistogram = new SparseIntArray();\n" +
                    "        public SparseIntArray statusHistogram = new SparseIntArray();\n" +
                    "        public SparseIntArray measuredDistanceHistogram = new SparseIntArray();\n" +
                    "\n" +
                    "        @Override\n" +
                    "        public String toString() {\n" +
                    "            return \"numCalls=\" + numCalls + \", numIndividualCalls=\" + numIndividualCalls\n" +
                    "                    + \", perUidInfo=\" + perUidInfo + \", numRequestsHistogram=\"\n" +
                    "                    + numRequestsHistogram + \", requestGapHistogram=\" + requestGapHistogram\n" +
                    "                    + \", statusHistogram=\" + statusHistogram\n" +
                    "                    + \", measuredDistanceHistogram=\" + measuredDistanceHistogram;\n" +
                    "        }\n" +
                    "    }\n" +
                    "**************  PerPeerTypeInfo End **************\n" +
                    "**************  RttMetrics Begin **************\n" +
                    "public class RttMetrics {  // Wi-Fi RTT metric container/processor.\n" +
                    " int mNumStartRangingCalls = 0;  // WIFI 定位 ? \n" +
                    "SparseIntArray mOverallStatusHistogram = new SparseIntArray();\n" +
                    "final int PEER_AP = 0;\n" +
                    "final int PEER_AWARE = 1;\n" +
                    "PerPeerTypeInfo[] mPerPeerTypeInfo;\n" +
                    "\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        synchronized (mLock) {\n" +
                    "            pw.println(\"RTT Metrics:\");\n" +
                    "            pw.println(\"mNumStartRangingCalls:\" + mNumStartRangingCalls);\n" +
                    "            pw.println(\"mOverallStatusHistogram:\" + mOverallStatusHistogram);\n" +
                    "            pw.println(\"AP:\" + mPerPeerTypeInfo[PEER_AP]);\n" +
                    "            pw.println(\"AWARE:\" + mPerPeerTypeInfo[PEER_AWARE]);\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "LOG 示例:  还未知\n" +
                    "RTT Metrics:  \n" +
                    "mNumStartRangingCalls:0   //  int mNumStartRangingCalls = 0;  \n" +
                    "mOverallStatusHistogram:{}   // SparseIntArray mOverallStatusHistogram  \n" +
                    "// AP:  PerPeerTypeInfo[0]    //  AWARE: PerPeerTypeInfo[1]\n" +
                    "AP:numCalls=0, numIndividualCalls=0, perUidInfo={}, numRequestsHistogram={}, requestGapHistogram={}, statusHistogram={}, measuredDistanceHistogram={}\n" +
                    "AWARE:numCalls=0, numIndividualCalls=0, perUidInfo={}, numRequestsHistogram={}, requestGapHistogram={}, statusHistogram={}, measuredDistanceHistogram={}\n" +
                    "**************  RttMetrics End **************";
            keyWordList.add(wifi_22_17);



            KeyWordItem wifi_22_18 = new KeyWordItem();
            wifi_22_18.keyword = "mPnoScanMetrics.numPnoScanAttempts=";
            wifi_22_18.explain="WIFI offload WIFI负载分流 PNO扫描  [加密]com.android.server.wifi.nano.WifiMetricsProto.PnoScanMetrics;" ;
            wifi_22_18.classNameForShuxing = "class WifiMetrics { PnoScanMetrics mPnoScanMetrics }";
            wifi_22_18.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => PnoScanMetrics.dump() { } ";
            wifi_22_18.expain1="public class WifiMetrics {  \n" +
                    "\n" +
                    "PnoScanMetrics mPnoScanMetrics = new PnoScanMetrics();\n" +
                    "dump(){\n" +
                    ".......\n" +
                    "// numPnoScanAttempts   PNO扫描次数\n" +
                    "pw.println(\"mPnoScanMetrics.numPnoScanAttempts=\"+ mPnoScanMetrics.numPnoScanAttempts);\n" +
                    "// numPnoScanFailed   PNO扫描失败次数\n" +
                    "pw.println(\"mPnoScanMetrics.numPnoScanFailed=\"+ mPnoScanMetrics.numPnoScanFailed);\n" +
                    "// numPnoScanStartedOverOffload  PNO扫描开启 WIFI Offload  负载分流\n" +
                    "// 现在智能移动设备太普及了.网络服务商设备鸭梨山大.于是提供了一种解决当前网络负载过重的方案,就是WIFI OFFLOAD\n" +
                    "// 说白了就是分流部分业务流量到WIFI网络上.\n" +
                    "pw.println(\"mPnoScanMetrics.numPnoScanStartedOverOffload=\"+ mPnoScanMetrics.numPnoScanStartedOverOffload);\n" +
                    "// numPnoScanFailedOverOffload   PNO扫描负载WIFI失败 pno scans failed \n" +
                    "pw.println(\"mPnoScanMetrics.numPnoScanFailedOverOffload=\"+ mPnoScanMetrics.numPnoScanFailedOverOffload);\n" +
                    "// numPnoFoundNetworkEvents  PNO扫描没有找到网络事件的次数\n" +
                    "pw.println(\"mPnoScanMetrics.numPnoFoundNetworkEvents=\"+ mPnoScanMetrics.numPnoFoundNetworkEvents);\n" +
                    ".......\n" +
                    "}\n" +
                    "}";
            keyWordList.add(wifi_22_18);


            KeyWordItem wifi_22_19 = new KeyWordItem();
            wifi_22_19.keyword = "mWifiLinkLayerUsageStats.loggingDurationMs";
            wifi_22_19.explain="WIFI的一些天线使用时间情况 WIFI通知情况" ;
            wifi_22_19.classNameForShuxing = "class WifiMetrics { WifiLinkLayerUsageStats mWifiLinkLayerUsageStats  }";
            wifi_22_19.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java => WifiLinkLayerUsageStats.dump() { } ";
            wifi_22_19.expain1="public class WifiMetrics {  \n" +
                    "\n" +
                    "WifiLinkLayerUsageStats mWifiLinkLayerUsageStats = new WifiLinkLayerUsageStats();\n" +
                    "\n" +
                    " /** Mapping of \"Connect to Network\" notifications to counts. */\n" +
                    " SparseIntArray mConnectToNetworkNotificationCount = new SparseIntArray();\n" +
                    " \n" +
                    "     /** Mapping of \"Connect to Network\" notification user actions to counts. */\n" +
                    "    private final SparseIntArray mConnectToNetworkNotificationActionCount = new SparseIntArray();\n" +
                    "\t\n" +
                    "  int mOpenNetworkRecommenderBlacklistSize = 0;\n" +
                    "  boolean mIsWifiNetworksAvailableNotificationOn = false;\n" +
                    "  int mNumOpenNetworkRecommendationUpdates = 0;\n" +
                    "  int mNumOpenNetworkConnectMessageFailedToSend = 0;\n" +
                    "  \n" +
                    "  SparseIntArray mObservedHotspotR1ApInScanHistogram = new SparseIntArray();\n" +
                    "  SparseIntArray mObservedHotspotR2ApInScanHistogram = new SparseIntArray();\n" +
                    "  SparseIntArray mObservedHotspotR1EssInScanHistogram = new SparseIntArray();\n" +
                    "  SparseIntArray mObservedHotspotR2EssInScanHistogram = new SparseIntArray();\n" +
                    "  SparseIntArray mObservedHotspotR1ApsPerEssInScanHistogram = new SparseIntArray();\n" +
                    "  SparseIntArray mObservedHotspotR2ApsPerEssInScanHistogram = new SparseIntArray();\n" +
                    "  SparseIntArray mObserved80211mcApInScanHistogram = new SparseIntArray();\n" +
                    "\n" +
                    "\n" +
                    "dump(){\n" +
                    ".......\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.loggingDurationMs=\"+ mWifiLinkLayerUsageStats.loggingDurationMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioOnTimeMs=\"+ mWifiLinkLayerUsageStats.radioOnTimeMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioTxTimeMs=\"+ mWifiLinkLayerUsageStats.radioTxTimeMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioRxTimeMs=\"+ mWifiLinkLayerUsageStats.radioRxTimeMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioScanTimeMs=\"+ mWifiLinkLayerUsageStats.radioScanTimeMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioNanScanTimeMs=\"+ mWifiLinkLayerUsageStats.radioNanScanTimeMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioBackgroundScanTimeMs=\"+ mWifiLinkLayerUsageStats.radioBackgroundScanTimeMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioRoamScanTimeMs=\"+ mWifiLinkLayerUsageStats.radioRoamScanTimeMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioPnoScanTimeMs=\"+ mWifiLinkLayerUsageStats.radioPnoScanTimeMs);\n" +
                    "pw.println(\"mWifiLinkLayerUsageStats.radioHs20ScanTimeMs=\"+ mWifiLinkLayerUsageStats.radioHs20ScanTimeMs);\n" +
                    "pw.println(\"mWifiLogProto.connectToNetworkNotificationCount=\"+ mConnectToNetworkNotificationCount.toString());\n" +
                    "pw.println(\"mWifiLogProto.connectToNetworkNotificationActionCount=\"+ mConnectToNetworkNotificationActionCount.toString());\n" +
                    "pw.println(\"mWifiLogProto.openNetworkRecommenderBlacklistSize=\"+ mOpenNetworkRecommenderBlacklistSize);\n" +
                    "pw.println(\"mWifiLogProto.isWifiNetworksAvailableNotificationOn=\"+ mIsWifiNetworksAvailableNotificationOn);\n" +
                    "pw.println(\"mWifiLogProto.numOpenNetworkRecommendationUpdates=\"+ mNumOpenNetworkRecommendationUpdates);\n" +
                    "pw.println(\"mWifiLogProto.numOpenNetworkConnectMessageFailedToSend=\"+ mNumOpenNetworkConnectMessageFailedToSend);\n" +
                    "pw.println(\"mWifiLogProto.observedHotspotR1ApInScanHistogram=\"+ mObservedHotspotR1ApInScanHistogram);\n" +
                    "pw.println(\"mWifiLogProto.observedHotspotR2ApInScanHistogram=\"+ mObservedHotspotR2ApInScanHistogram);\n" +
                    "pw.println(\"mWifiLogProto.observedHotspotR1EssInScanHistogram=\"+ mObservedHotspotR1EssInScanHistogram);\n" +
                    "pw.println(\"mWifiLogProto.observedHotspotR2EssInScanHistogram=\"+ mObservedHotspotR2EssInScanHistogram);\n" +
                    "pw.println(\"mWifiLogProto.observedHotspotR1ApsPerEssInScanHistogram=\"+ mObservedHotspotR1ApsPerEssInScanHistogram);\n" +
                    "pw.println(\"mWifiLogProto.observedHotspotR2ApsPerEssInScanHistogram=\"+ mObservedHotspotR2ApsPerEssInScanHistogram);\n" +
                    "pw.println(\"mWifiLogProto.observed80211mcSupportingApsInScanHistogram\"+ mObserved80211mcApInScanHistogram);\n" +
                    "....\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "mWifiLinkLayerUsageStats.loggingDurationMs=249281534   //  LOG持续毫秒时间 mWifiLinkLayerUsageStats.loggingDurationMs\n" +
                    "mWifiLinkLayerUsageStats.radioOnTimeMs=2173438   // 天线使用时间  毫秒 mWifiLinkLayerUsageStats.radioOnTimeMs\n" +
                    "mWifiLinkLayerUsageStats.radioTxTimeMs=27897    // 天线发送时间  mWifiLinkLayerUsageStats.radioTxTimeMs\n" +
                    "mWifiLinkLayerUsageStats.radioRxTimeMs=108766   // 天线接收时间  mWifiLinkLayerUsageStats.radioRxTimeMs\n" +
                    "mWifiLinkLayerUsageStats.radioScanTimeMs=1207806  // 天线扫描时间 mWifiLinkLayerUsageStats.radioScanTimeMs\n" +
                    "mWifiLinkLayerUsageStats.radioNanScanTimeMs=0      //  天线非扫描时间?  mWifiLinkLayerUsageStats.radioNanScanTimeMs\n" +
                    "mWifiLinkLayerUsageStats.radioBackgroundScanTimeMs=0   // 天线扫描时间 mWifiLinkLayerUsageStats.radioBackgroundScanTimeMs\n" +
                    "mWifiLinkLayerUsageStats.radioRoamScanTimeMs=4700          // 天线漫游时间 mWifiLinkLayerUsageStats.radioRoamScanTimeMs\n" +
                    "mWifiLinkLayerUsageStats.radioPnoScanTimeMs=31580   // PNO扫描时间  mWifiLinkLayerUsageStats.radioPnoScanTimeMs\n" +
                    "mWifiLinkLayerUsageStats.radioHs20ScanTimeMs=0   // passpoint扫描时间? mWifiLinkLayerUsageStats.radioHs20ScanTimeMs \n" +
                    "\n" +
                    "// com.android.server.wifi.nano.WifiMetricsProto.ConnectToNetworkNotificationAndActionCount的通知类型\n" +
                    "// NOTIFICATION_FAILED_TO_CONNECT=0(提示连接失败) \n" +
                    "// NOTIFICATION_RECOMMEND_NETWORK=1 (推荐网络)\n" +
                    "// NOTIFICATION_CONNECTED_TO_NETWORK =2 (提示连接成功)\n" +
                    "// NOTIFICATION_CONNECTING_TO_NETWORK=3 (正在连接网络提示)\n" +
                    "mWifiLogProto.connectToNetworkNotificationCount={1=4}  //推荐网络出现了4次  SparseIntArray mConnectToNetworkNotificationCount\n" +
                    "\n" +
                    "//  ACTION_PICK_WIFI_NETWORK_AFTER_CONNECT_FAILURE = 0\n" +
                    "//  ACTION_CONNECT_TO_NETWORK = 1\n" +
                    "//  ACTION_PICK_WIFI_NETWORK=2 用户对于通知的选择情况\n" +
                    "//  ACTION_USER_DISMISSED_NOTIFICATION =3\n" +
                    "mWifiLogProto.connectToNetworkNotificationActionCount={}\n" +
                    "mWifiLogProto.openNetworkRecommenderBlacklistSize=0   //  黑名单个数 int  mOpenNetworkRecommenderBlacklistSize\n" +
                    "mWifiLogProto.isWifiNetworksAvailableNotificationOn=true  // 是否打开了发现有效网络通知的开关\n" +
                    "mWifiLogProto.numOpenNetworkRecommendationUpdates=50   // 记录 当前开放网络推荐网络的更新次数 未显示通知的更新\n" +
                    "mWifiLogProto.numOpenNetworkConnectMessageFailedToSend=0   // 连接失败的记录次数 \n" +
                    "mWifiLogProto.observedHotspotR1ApInScanHistogram={0=739}\n" +
                    "mWifiLogProto.observedHotspotR2ApInScanHistogram={0=739}\n" +
                    "mWifiLogProto.observedHotspotR1EssInScanHistogram={0=739}\n" +
                    "mWifiLogProto.observedHotspotR2EssInScanHistogram={0=739}  // 扫描到的网络--扫描的次数\n" +
                    "mWifiLogProto.observedHotspotR1ApsPerEssInScanHistogram={}\n" +
                    "mWifiLogProto.observedHotspotR2ApsPerEssInScanHistogram={}\n" +
                    "mWifiLogProto.observed80211mcSupportingApsInScanHistogram{0=739}  //? ";
            keyWordList.add(wifi_22_19);



            KeyWordItem wifi_22_20 = new KeyWordItem();
            wifi_22_20.keyword = "mSoftApTetheredEvents:";
            wifi_22_20.explain="手机热点的一些事件 一些信息" ;
            wifi_22_20.classNameForShuxing = "class WifiMetrics { List<SoftApConnectedClientsEvent> mSoftApEventListTethered  }";
            wifi_22_20.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() { List<SoftApConnectedClientsEvent> mSoftApEventListTethered  } ";
            wifi_22_20.expain1="public class WifiMetrics {  \n" +
                    "\n" +
                    " 热点模式 List of soft AP events related to number of connected clients in local only mode */\n" +
                    "List<SoftApConnectedClientsEvent> mSoftApEventListTethered = new ArrayList<>();\n" +
                    "void dump(){\n" +
                    "pw.println(\"mSoftApLocalOnlyEvents:\"); \n" +
                    "for (SoftApConnectedClientsEvent event : mSoftApEventListTethered) {\n" +
                    "\tStringBuilder eventLine = new StringBuilder();\n" +
                    "\t\n" +
                    "\t// SoftAP event tracking sessions and client counts\n" +
                    "message SoftApConnectedClientsEvent {\n" +
                    "\n" +
                    "// eventType == 0-(SOFT_AP_UP,热点打开可以使用)  1-(SOFT_AP_DOWN,热点关闭)  2-(连接的热点的客户端数量变化)\n" +
                    "// channelBandwidth == 0-(无效的带宽) 1-(非高速20Mhz--Not Very High)  2-(20MHz)  3(40MHz)\n" +
                    "// channelBandwidth == 4-(80Mhz) 5-(80Mhz+80Mhz两个载波)  6-(180MHz)\n" +
                    "\n" +
                    "  \n" +
                    "\teventLine.append(\"event_type=\" + event.eventType);  //事件类型\n" +
                    "\teventLine.append(\",time_stamp_millis=\" + event.timeStampMillis);  // 时间戳(开机起始的时间戳为 0 开始计算的 )\n" +
                    "\teventLine.append(\",num_connected_clients=\" + event.numConnectedClients);  // 当前连接客户端数量\n" +
                    "\teventLine.append(\",channel_frequency=\" + event.channelFrequency);  // 热点工作信道\n" +
                    "\teventLine.append(\",channel_bandwidth=\" + event.channelBandwidth);  // 信道带宽\n" +
                    "\tpw.println(eventLine.toString());\n" +
                    "}\n" +
                    "}";
            keyWordList.add(wifi_22_20);



            KeyWordItem wifi_22_21 = new KeyWordItem();
            wifi_22_21.keyword = "mSoftApTetheredEvents:";
            wifi_22_21.explain="手机LocalOnlyHotspot热点(本地无线播放 共享热点 不能联网)的一些事件 一些信息" ;
            wifi_22_21.classNameForShuxing = "class WifiMetrics { List<SoftApConnectedClientsEvent> mSoftApEventListLocalOnly  }";
            wifi_22_21.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() { List<SoftApConnectedClientsEvent> mSoftApEventListLocalOnly  } ";
            wifi_22_21.expain1="public class WifiMetrics {  \n" +
                    "\n" +
                    " /**无法联网 本地共享视频的热点 List of soft AP events related to number of connected clients in local only mode */\n" +
                    "List<SoftApConnectedClientsEvent> mSoftApEventListLocalOnly = new ArrayList<>();\n" +
                    "//  与  mSoftApTetheredEvents 有区别 \n" +
                    "// 1、LocalOnlyHotspot开启以后，应用退出前台几秒热点就会自动关闭。\n" +
                    "// 2、连接LocalOnlyHotspot不能访问外网。\n" +
                    "\n" +
                    "// LocalOnlyHotspot\n" +
                    "// Android P 新增应用API以实现本地协同的多个设备进行内容共享。\n" +
                    "// 应用程序可使用该API建立本地SoftAP(不可以共享internet上网)，该功能可实现多个应用程序共享同一个LOHS。\n" +
                    "// 通过共享同一个LOHS，多个设备之间不仅可以实现协同播放，还可以进行游戏的局域对战，\n" +
                    "// 增强了多个设备之间同个应用程序的互动性。\n" +
                    "// 详细内容请查看以下链接\n" +
                    "// Android P 开发者预览版：\n" +
                    "// https://developer.android.google.cn/preview/index.html\n" +
                    "// Android Developers 官方文档：\n" +
                    "// https://developer.android.google.cn\n" +
                    "\n" +
                    "void dump(){\n" +
                    "pw.println(\"mSoftApLocalOnlyEvents:\"); \n" +
                    "for (SoftApConnectedClientsEvent event : mSoftApEventListLocalOnly) {\n" +
                    "\tStringBuilder eventLine = new StringBuilder();\n" +
                    "\teventLine.append(\"event_type=\" + event.eventType);  //事件类型\n" +
                    "\teventLine.append(\",time_stamp_millis=\" + event.timeStampMillis);\n" +
                    "\teventLine.append(\",num_connected_clients=\" + event.numConnectedClients);  // 当前连接客户端数量\n" +
                    "\teventLine.append(\",channel_frequency=\" + event.channelFrequency);  // 热点工作信道\n" +
                    "\teventLine.append(\",channel_bandwidth=\" + event.channelBandwidth);  // 信道带宽\n" +
                    "\tpw.println(eventLine.toString());\n" +
                    "}\n" +
                    "\n" +
                    "}";
            keyWordList.add(wifi_22_21);


            KeyWordItem wifi_22_22 = new KeyWordItem();
            wifi_22_22.keyword = "Wifi power metrics:";
            wifi_22_22.explain="手机WIFI功耗情况 使用wifi时间统计" ;
            wifi_22_22.classNameForShuxing = "class WifiMetrics { WifiPowerMetrics mWifiPowerMetrics; }";
            wifi_22_22.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() { WifiPowerMetrics mWifiPowerMetrics;  } ";
            wifi_22_22.expain1="public class WifiMetrics {  \n" +
                    "\n" +
                    "/** Wifi power metrics*/\n" +
                    "WifiPowerMetrics mWifiPowerMetrics;\n" +
                    "\n" +
                    "void dump(){\n" +
                    "   mWifiPowerMetrics.dump(pw);\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "******************  WifiPowerMetrics Begin  ******************\n" +
                    "public class WifiPowerMetrics {\n" +
                    "\n" +
                    "IBatteryStats mBatteryStats;     /* BatteryStats API */\n" +
                    "\n" +
                    "\n" +
                    "    public WifiPowerStats buildProto() {\n" +
                    "        WifiPowerStats m = new WifiPowerStats();\n" +
                    "        WifiBatteryStats stats = getStats(); // mBatteryStats.getWifiBatteryStats();\n" +
                    "        if (stats != null) {\n" +
                    "            m.loggingDurationMs = stats.getLoggingDurationMs();\n" +
                    "            m.energyConsumedMah = stats.getEnergyConsumedMaMs()  / ((double) DateUtils.HOUR_IN_MILLIS);\n" +
                    "            m.idleTimeMs = stats.getIdleTimeMs();\n" +
                    "            m.rxTimeMs = stats.getRxTimeMs();\n" +
                    "            m.txTimeMs = stats.getTxTimeMs();\n" +
                    "            m.wifiKernelActiveTimeMs = stats.getKernelActiveTimeMs();\n" +
                    "            m.numPacketsTx = stats.getNumPacketsTx();\n" +
                    "            m.numBytesTx = stats.getNumBytesTx();\n" +
                    "            m.numPacketsRx = stats.getNumPacketsRx();\n" +
                    "            m.numBytesRx = stats.getNumPacketsRx();\n" +
                    "            m.sleepTimeMs = stats.getSleepTimeMs();\n" +
                    "            m.scanTimeMs = stats.getScanTimeMs();\n" +
                    "            m.monitoredRailEnergyConsumedMah = stats.getMonitoredRailChargeConsumedMaMs()   / ((double) DateUtils.HOUR_IN_MILLIS);\n" +
                    "        }\n" +
                    "        return m;\n" +
                    "    }\n" +
                    "\t\n" +
                    "    public void dump(PrintWriter pw) {\n" +
                    "        WifiPowerStats s = buildProto();\n" +
                    "        if (s!=null) {\n" +
                    "            pw.println(\"Wifi power metrics:\");\n" +
                    "            pw.println(\"Logging duration (time on battery): \" + s.loggingDurationMs);\n" +
                    "            pw.println(\"Energy consumed by wifi (mAh): \" + s.energyConsumedMah); // wifi消耗能量 毫安时mAh\n" +
                    "            pw.println(\"Amount of time wifi is in idle (ms): \" + s.idleTimeMs); // wifi idle时间\n" +
                    "            pw.println(\"Amount of time wifi is in rx (ms): \" + s.rxTimeMs); // wifi rx 时间\n" +
                    "            pw.println(\"Amount of time wifi is in tx (ms): \" + s.txTimeMs); // wifi tx 时间\n" +
                    "            pw.println(\"Amount of time kernel is active because of wifi data (ms): \"   + s.wifiKernelActiveTimeMs);\n" +
                    "            pw.println(\"Amount of time wifi is in sleep (ms): \" + s.sleepTimeMs); // wifi sellp 时间\n" +
                    "            pw.println(\"Amount of time wifi is scanning (ms): \" + s.scanTimeMs);  // wifi scanning 时间\n" +
                    "            pw.println(\"Number of packets sent (tx): \" + s.numPacketsTx);  // 发送包的数量 \n" +
                    "            pw.println(\"Number of bytes sent (tx): \" + s.numBytesTx);   // 发送包对应的字节大小\n" +
                    "            pw.println(\"Number of packets received (rx): \" + s.numPacketsRx); // 接收包的数量 \n" +
                    "            pw.println(\"Number of bytes sent (rx): \" + s.numBytesRx); // 接收包的数量 对应的字节大小 \n" +
                    "\t\t\t// 通过检测wifi消耗的能量？ \n" +
                    "            pw.println(\"Energy consumed across measured wifi rails (mAh): \"+ new DecimalFormat(\"#.##\").format(s.monitoredRailEnergyConsumedMah));\n" +
                    "        }\n" +
                    "        WifiRadioUsage wifiRadioUsage = buildWifiRadioUsageProto();\n" +
                    "        pw.println(\"Wifi radio usage metrics:\");  \n" +
                    "        pw.println(\"Logging duration (time on battery): \" + wifiRadioUsage.loggingDurationMs);\n" +
                    "\t\t// wifi 扫描模式下 耗电时间? \n" +
                    "        pw.println(\"Amount of time wifi is in scan mode while on battery (ms): \"  + wifiRadioUsage.scanTimeMs);\n" +
                    "    }\n" +
                    "\t\n" +
                    "******************  WifiPowerMetrics End  ******************";
            keyWordList.add(wifi_22_22);


            KeyWordItem wifi_22_23 = new KeyWordItem();
            wifi_22_23.keyword = "-------WifiWake metrics-------";
            wifi_22_23.explain="WIFI Wake醒来统计数据记录 " ;
            wifi_22_23.classNameForShuxing = "class WifiMetrics {  WifiWake metrics  }";
            wifi_22_23.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() {  WifiWake metrics   } ";
            wifi_22_23.expain1="public class WifiMetrics {  \n" +
                    "WifiWakeMetrics mWifiWakeMetrics = new WifiWakeMetrics();\n" +
                    "\n" +
                    "dump(){\n" +
                    "mWifiWakeMetrics.dump(pw);\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "****************  WifiWakeMetrics.Session Begin ****************\n" +
                    "static class Session {  /** A single WifiWake session. */\n" +
                    "   final long mStartTimestamp;\n" +
                    "   final int mStartNetworks;\n" +
                    "   int mInitializeNetworks = 0;\n" +
                    "Event mUnlockEvent;  // 解锁事件 \n" +
                    "Event mInitEvent;   // 初始化事件 \n" +
                    "Event mWakeupEvent; // 觉醒事件 \n" +
                    "Event mResetEvent; // 重置事件 \n" +
                    "// Event.toString()  \"{ mNumScans: \" + mNumScans + \", elapsedTime: \" + mElapsedTime + \" }\";\n" +
                    "      public void dump(PrintWriter pw) {\n" +
                    "            pw.println(\"WifiWakeMetrics.Session:\");\n" +
                    "            pw.println(\"mStartTimestamp: \" + mStartTimestamp);  // 开始时间戳\n" +
                    "            pw.println(\"mStartNetworks: \" + mStartNetworks); //  起始网络个数？ \n" +
                    "            pw.println(\"mInitializeNetworks: \" + mInitializeNetworks);  // ？ \n" +
                    "            pw.println(\"mInitEvent: \" + (mInitEvent == null ? \"{}\" : mInitEvent.toString()));\n" +
                    "            pw.println(\"mUnlockEvent: \" + (mUnlockEvent == null ? \"{}\" : mUnlockEvent.toString()));\n" +
                    "            pw.println(\"mWakeupEvent: \" + (mWakeupEvent == null ? \"{}\" : mWakeupEvent.toString()));\n" +
                    "            pw.println(\"mResetEvent: \" + (mResetEvent == null ? \"{}\" : mResetEvent.toString()));\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "****************  WifiWakeMetrics.Session End ****************\n" +
                    "\t\n" +
                    "****************  WifiWakeMetrics Begin ****************\n" +
                    "public class WifiWakeMetrics {  // 把拿了 WIFILock的那些数据打印出来\n" +
                    "// Holds WifiWake metrics and converts them to a protobuf included in WifiLog. \n" +
                    "\n" +
                    "    /** Maximum number of sessions to store in WifiWakeStats proto. */\n" +
                    "    static final int MAX_RECORDED_SESSIONS = 10; // 最大保存10 个 Session的 Log数据\n" +
                    "\n" +
                    "     final List<Session> mSessions = new ArrayList<>();\n" +
                    "     Session mCurrentSession;\n" +
                    "    \n" +
                    "     boolean mIsInSession = false;\n" +
                    "     int mTotalSessions = 0;\n" +
                    "     int mTotalWakeups = 0;\n" +
                    "     int mIgnoredStarts = 0;\n" +
                    "\t  Object mLock = new Object();\n" +
                    "\n" +
                    "public void dump(PrintWriter pw) {\n" +
                    "    synchronized (mLock) {\n" +
                    "\tpw.println(\"-------WifiWake metrics-------\");\n" +
                    "\tpw.println(\"mTotalSessions: \" + mTotalSessions);  // WIFI 觉醒请求会话个数\n" +
                    "\tpw.println(\"mTotalWakeups: \" + mTotalWakeups);   // 当前WIFI总的 觉醒次数\n" +
                    "\tpw.println(\"mIgnoredStarts: \" + mIgnoredStarts);  // 记录 忽略 请求WIFI觉醒  (因为已经醒来)的次数\n" +
                    "\tpw.println(\"mIsInSession: \" + mIsInSession);  // 是否正在 WIFI 觉醒处理中\n" +
                    "\tpw.println(\"Stored Sessions: \" + mSessions.size());  // List<Session> 当前保存的 会话个数\n" +
                    "\tfor (Session session : mSessions) {   // 打印所有的 WIFI Session \n" +
                    "\t\tsession.dump(pw);  // 打印  WifiWakeMetrics.Session: 的列表\n" +
                    "\t}\n" +
                    "\tif (mCurrentSession != null) {\n" +
                    "\t\tpw.println(\"Current Session: \");  // 当前的 WIFI Session \n" +
                    "\t\tmCurrentSession.dump(pw);\n" +
                    "\t}\n" +
                    "\tpw.println(\"----end of WifiWake metrics----\");\n" +
                    "****************  WifiWakeMetrics End ****************";
            keyWordList.add(wifi_22_23);

            KeyWordItem wifi_22_24 = new KeyWordItem();
            wifi_22_24.keyword = "mWifiLogProto.isMacRandomizationOn=";
            wifi_22_24.explain="WIFI 可用性  随机Mac地址  等记录  可用性事件?  不清楚 " ;
            wifi_22_24.classNameForShuxing = "class WifiMetrics {  LinkedList<WifiIsUnusableWithTime> mWifiIsUnusableList , ExperimentValues mExperimentValues  }";
            wifi_22_24.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() {  LinkedList<WifiIsUnusableWithTime> mWifiIsUnusableList , ExperimentValues mExperimentValues   } ";
            wifi_22_24.expain1="public class WifiMetrics {  \n" +
                    "ExperimentValues mExperimentValues = new ExperimentValues();  //  外部 加密 \n" +
                    "LinkedList<WifiIsUnusableWithTime> mWifiIsUnusableList =new LinkedList<WifiIsUnusableWithTime>();\n" +
                    "\t\t\t\n" +
                    "vooid dump(){\n" +
                    "...\n" +
                    "pw.println(\"mWifiLogProto.isMacRandomizationOn=\" + mIsMacRandomizationOn);  // mac地址随机开关 \n" +
                    "pw.println(\"mWifiLogProto.scoreExperimentId=\" + mWifiLogProto.scoreExperimentId);\n" +
                    "pw.println(\"mExperimentValues.wifiIsUnusableLoggingEnabled=\"+ mExperimentValues.wifiIsUnusableLoggingEnabled);\n" +
                    "pw.println(\"mExperimentValues.wifiDataStallMinTxBad=\"+ mExperimentValues.wifiDataStallMinTxBad);\n" +
                    "pw.println(\"mExperimentValues.wifiDataStallMinTxSuccessWithoutRx=\"+ mExperimentValues.wifiDataStallMinTxSuccessWithoutRx);\n" +
                    "pw.println(\"mExperimentValues.linkSpeedCountsLoggingEnabled=\"+ mExperimentValues.linkSpeedCountsLoggingEnabled); // 速率记录开关\n" +
                    "pw.println(\"WifiIsUnusableEventList: \");\n" +
                    "for (WifiIsUnusableWithTime event : mWifiIsUnusableList) {  // WIFI 不可用事件? \n" +
                    "\tpw.println(event);\n" +
                    "}\n" +
                    "pw.println(\"Hardware Version: \" + SystemProperties.get(\"ro.boot.revision\", \"\"));  // 硬件版本 来自于 ro.boot.revision\n" +
                    "}";
            keyWordList.add(wifi_22_24);
            // Last sweep

            KeyWordItem wifi_22_25 = new KeyWordItem();
            wifi_22_25.keyword = "mWifiUsabilityStatsEntriesList:";
            wifi_22_25.explain="LinkedList<WifiUsabilityStatsEntry> 可用性状态列表  太多！！ " ;
            wifi_22_25.classNameForShuxing = "class WifiMetrics {   LinkedList<WifiUsabilityStatsEntry> mWifiUsabilityStatsEntriesList  }";
            wifi_22_25.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() {  LinkedList<WifiUsabilityStatsEntry> mWifiUsabilityStatsEntriesList  } ";
            wifi_22_25.expain1="****************** WifiUsabilityStatsEntry  Begin  ******************\n" +
                    "\n" +
                    "\tclass WifiUsabilityStatsEntry implements Parcelable {\n" +
                    "\n" +
                    "    public static final int PROBE_STATUS_UNKNOWN = 0;     /** Link probe status is unknown */\n" +
                    "    public static final int PROBE_STATUS_NO_PROBE = 1;     /** Link probe is not triggered */\n" +
                    "    public static final int PROBE_STATUS_SUCCESS = 2;     /** Link probe is triggered and the result is success */\n" +
                    "    public static final int PROBE_STATUS_FAILURE = 3;     /** Link probe is triggered and the result is failure */\n" +
                    " long mTimeStampMillis;  /** Absolute milliseconds from device boot when these stats were sampled */\n" +
                    " int mRssi;    /** The RSSI (in dBm) at the sample time */\n" +
                    " int mLinkSpeedMbps;     /** Link speed at the sample time in Mbps */\n" +
                    " long mTotalTxSuccess;  /** The total number of tx success counted from the last radio chip reset */\n" +
                    " long mTotalTxRetries; /** The total number of MPDU data packet retries counted from the last radio chip reset */\n" +
                    " long mTotalTxBad;  /** The total number of tx bad counted from the last radio chip reset */\n" +
                    " long mTotalRxSuccess;   /** The total number of rx success counted from the last radio chip reset */\n" +
                    " long mTotalRadioOnTimeMillis;  /** The total time the wifi radio is on in ms counted from the last radio chip reset */\n" +
                    " long mTotalRadioTxTimeMillis;    /** The total time the wifi radio is doing tx in ms counted from the last radio chip reset */\n" +
                    " long mTotalRadioRxTimeMillis;     /** The total time the wifi radio is doing rx in ms counted from the last radio chip reset */\n" +
                    " long mTotalScanTimeMillis;     /** The total time spent on all types of scans in ms counted from the last radio chip reset */\n" +
                    " long mTotalNanScanTimeMillis;     /** The total time spent on nan scans in ms counted from the last radio chip reset */\n" +
                    " long mTotalBackgroundScanTimeMillis;    /** The total time spent on background scans in ms counted from the last radio chip reset */\n" +
                    " long mTotalRoamScanTimeMillis;     /** The total time spent on roam scans in ms counted from the last radio chip reset */\n" +
                    " long mTotalPnoScanTimeMillis;  /** The total time spent on pno scans in ms counted from the last radio chip reset */\n" +
                    " long mTotalHotspot2ScanTimeMillis;  // 花在 passpoint网络的 GAS  ANQP的时间  /** The total time spent on hotspot2.0 scans and GAS exchange in ms counted  */\n" +
                    " long mTotalCcaBusyFreqTimeMillis;    /** The total time CCA is on busy status on the current frequency in ms counted from the last radio chip reset */\n" +
                    " long mTotalRadioOnFreqTimeMillis;   /** The total radio on time on the current frequency from the last radio chip reset */\n" +
                    " long mTotalBeaconRx;  /** The total number of beacons received from the last radio chip reset */\n" +
                    " int mProbeStatusSinceLastUpdate;   /** The status of link probe since last stats update */\n" +
                    " int mProbeElapsedTimeSinceLastUpdateMillis; /** The elapsed time of the most recent link probe since last stats update */\n" +
                    " int mProbeMcsRateSinceLastUpdate;     /** The MCS rate of the most recent link probe since last stats update */\n" +
                    " int mRxLinkSpeedMbps;     /** Rx link speed at the sample time in Mbps */\n" +
                    " int mCellularDataNetworkType;\n" +
                    " int mCellularSignalStrengthDbm;\n" +
                    " int mCellularSignalStrengthDb;\n" +
                    " boolean mIsSameRegisteredCell;\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "****************** WifiUsabilityStatsEntry  End  ******************\n" +
                    "\n" +
                    "// WIFI 的一些状态列表? \n" +
                    " LinkedList<WifiUsabilityStatsEntry> mWifiUsabilityStatsEntriesList =  new LinkedList<>();\n" +
                    "public class WifiMetrics {  \n" +
                    " void dump(){\n" +
                    "...\n" +
                    "\tpw.println(\"mWifiUsabilityStatsEntriesList:\");\n" +
                    "\tfor (WifiUsabilityStatsEntry stats : mWifiUsabilityStatsEntriesList) {\n" +
                    "\t\tprintWifiUsabilityStatsEntry(pw, stats);\n" +
                    "\t}\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void printWifiUsabilityStatsEntry(PrintWriter pw, WifiUsabilityStatsEntry entry) {\n" +
                    "        StringBuilder line = new StringBuilder();\n" +
                    "        line.append(\"timestamp_ms=\" + entry.timeStampMs); // timeStampMs = 时间戳\n" +
                    "        line.append(\",rssi=\" + entry.rssi);  // rssi = 信号强度\n" +
                    "        line.append(\",link_speed_mbps=\" + entry.linkSpeedMbps);  // link_speed_mbps = 连接速度\n" +
                    "        line.append(\",total_tx_success=\" + entry.totalTxSuccess);\n" +
                    "        line.append(\",total_tx_retries=\" + entry.totalTxRetries);\n" +
                    "        line.append(\",total_tx_bad=\" + entry.totalTxBad);\n" +
                    "        line.append(\",total_rx_success=\" + entry.totalRxSuccess);\n" +
                    "        line.append(\",total_radio_on_time_ms=\" + entry.totalRadioOnTimeMs);\n" +
                    "        line.append(\",total_radio_tx_time_ms=\" + entry.totalRadioTxTimeMs);\n" +
                    "        line.append(\",total_radio_rx_time_ms=\" + entry.totalRadioRxTimeMs);\n" +
                    "        line.append(\",total_scan_time_ms=\" + entry.totalScanTimeMs);\n" +
                    "        line.append(\",total_nan_scan_time_ms=\" + entry.totalNanScanTimeMs);\n" +
                    "        line.append(\",total_background_scan_time_ms=\" + entry.totalBackgroundScanTimeMs);\n" +
                    "        line.append(\",total_roam_scan_time_ms=\" + entry.totalRoamScanTimeMs);\n" +
                    "        line.append(\",total_pno_scan_time_ms=\" + entry.totalPnoScanTimeMs);\n" +
                    "        line.append(\",total_hotspot_2_scan_time_ms=\" + entry.totalHotspot2ScanTimeMs);\n" +
                    "        line.append(\",wifi_score=\" + entry.wifiScore);\n" +
                    "        line.append(\",wifi_usability_score=\" + entry.wifiUsabilityScore);\n" +
                    "        line.append(\",seq_num_to_framework=\" + entry.seqNumToFramework);\n" +
                    "        line.append(\",prediction_horizon_sec=\" + entry.predictionHorizonSec);\n" +
                    "        line.append(\",total_cca_busy_freq_time_ms=\" + entry.totalCcaBusyFreqTimeMs);\n" +
                    "        line.append(\",total_radio_on_freq_time_ms=\" + entry.totalRadioOnFreqTimeMs);\n" +
                    "        line.append(\",total_beacon_rx=\" + entry.totalBeaconRx);\n" +
                    "        line.append(\",probe_status_since_last_update=\" + entry.probeStatusSinceLastUpdate);\n" +
                    "        line.append(\",probe_elapsed_time_ms_since_last_update=\" + entry.probeElapsedTimeSinceLastUpdateMs);\n" +
                    "        line.append(\",probe_mcs_rate_since_last_update=\" + entry.probeMcsRateSinceLastUpdate);\n" +
                    "        line.append(\",rx_link_speed_mbps=\" + entry.rxLinkSpeedMbps);\n" +
                    "        line.append(\",seq_num_inside_framework=\" + entry.seqNumInsideFramework);\n" +
                    "        line.append(\",is_same_bssid_and_freq=\" + entry.isSameBssidAndFreq);\n" +
                    "        line.append(\",cellular_data_network_type=\" + entry.cellularDataNetworkType);\n" +
                    "        line.append(\",cellular_signal_strength_dbm=\" + entry.cellularSignalStrengthDbm);\n" +
                    "        line.append(\",cellular_signal_strength_db=\" + entry.cellularSignalStrengthDb);\n" +
                    "        line.append(\",is_same_registered_cell=\" + entry.isSameRegisteredCell);\n" +
                    "        line.append(\",device_mobility_state=\" + entry.deviceMobilityState);\n" +
                    "        pw.println(line.toString());\n" +
                    "    }\n" +
                    "\t\n" +
                    "Log示例:\n" +
                    "timestamp_ms=261043185 【时间戳】,rssi=-38【信号强度】,\n" +
                    "link_speed_mbps=351【链接速率 Mb/s 】,total_tx_success=347 【成功发送包数量】,\n" +
                    "total_tx_retries=75 【发送包重传数量】,total_tx_bad=0 【发送包丢失数量】,\n" +
                    "total_rx_success=161 【接收包数量】,total_radio_on_time_ms=2194015 【天线开启事件 毫秒】,\n" +
                    "total_radio_tx_time_ms=28371 【天线发送时间 毫秒】,total_radio_rx_time_ms=111255 【天线接收时间 毫秒】,\n" +
                    "total_scan_time_ms=1250476 【总的扫描时间  毫秒】,total_nan_scan_time_ms=0 【非扫描时间】,\n" +
                    "total_background_scan_time_ms=0 【后台扫描时间】,total_roam_scan_time_ms=4700 【漫游时间】,\n" +
                    "total_pno_scan_time_ms=31580 【PNO扫描时间】,total_hotspot_2_scan_time_ms=0【花在 passpoint网络的 GAS  ANQP的时间】 ,\n" +
                    "wifi_score=60 【WIFI基础分数】,wifi_usability_score=-1【WIFI可用性分数】,\n" +
                    "seq_num_to_framework=-1【?】,prediction_horizon_sec=-1 【?】,\n" +
                    "total_cca_busy_freq_time_ms=146743361,total_radio_on_freq_time_ms=612078,\n" +
                    "total_beacon_rx=74131 【接收到的beacon总数】,\n" +
                    "// int PROBE_STATUS_UNKNOWN = 0;   /** Link probe status is unknown */\n" +
                    "// int PROBE_STATUS_NO_PROBE = 1;   /** Link probe is not triggered */\n" +
                    "// int PROBE_STATUS_SUCCESS = 2;     /** Link probe is triggered and the result is success */\n" +
                    "// int PROBE_STATUS_FAILURE = 3;     /** Link probe is triggered and the result is failure */\n" +
                    "probe_status_since_last_update=1 【probe状态】,\n" +
                    "probe_elapsed_time_ms_since_last_update=-1,\n" +
                    "probe_mcs_rate_since_last_update=-1,\n" +
                    "rx_link_speed_mbps=390 【接收速率】,\n" +
                    "seq_num_inside_framework=474,is_same_bssid_and_freq=true 【相同的ssid和频率】,\n" +
                    "cellular_data_network_type=0,cellular_signal_strength_dbm=2147483647,\n" +
                    "cellular_signal_strength_db=2147483647,is_same_registered_cell=false,device_mobility_state=0【设备转态】\n" +
                    "// int DEVICE_MOBILITY_STATE_UNKNOWN = 0; \n" +
                    "// int DEVICE_MOBILITY_STATE_HIGH_MVMT = 1;   // 高速移动  飞机 摩托 自行车\n" +
                    "// int DEVICE_MOBILITY_STATE_LOW_MVMT = 2;  //  低速移动 跑步 散步  Low movement device mobility state.\n" +
                    "// int DEVICE_MOBILITY_STATE_STATIONARY = 3;   //  固定设备  不移动 ";
            keyWordList.add(wifi_22_25);


            KeyWordItem wifi_22_26 = new KeyWordItem();
            wifi_22_26.keyword = "mMobilityStatePnoStatsMap:";
            wifi_22_26.explain=" WifiMetrics 的  SparseArray<DeviceMobilityStatePnoScanStats> " ;
            wifi_22_26.classNameForShuxing = "class WifiMetrics {  SparseArray<DeviceMobilityStatePnoScanStats>  }";
            wifi_22_26.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() { SparseArray<DeviceMobilityStatePnoScanStats>  } ";
            wifi_22_26.expain1="public class WifiMetrics {  \n" +
                    "\t//  PNO 扫描的状态? \n" +
                    "SparseArray<DeviceMobilityStatePnoScanStats> mMobilityStatePnoStatsMap = new SparseArray<>();\n" +
                    "\n" +
                    " void dump(){\n" +
                    "       pw.println(\"mMobilityStatePnoStatsMap:\");\n" +
                    "       for (int i = 0; i < mMobilityStatePnoStatsMap.size(); i++) {\n" +
                    "        printDeviceMobilityStatePnoScanStats(pw, mMobilityStatePnoStatsMap.valueAt(i));\n" +
                    "}\n" +
                    "\t\n" +
                    "\t\n" +
                    "    void printDeviceMobilityStatePnoScanStats(PrintWriter pw,DeviceMobilityStatePnoScanStats stats) {\n" +
                    "        StringBuilder line = new StringBuilder();\n" +
                    "// int DEVICE_MOBILITY_STATE_UNKNOWN = 0; \n" +
                    "// int DEVICE_MOBILITY_STATE_HIGH_MVMT = 1;   // 高速移动  飞机 摩托 自行车\n" +
                    "// int DEVICE_MOBILITY_STATE_LOW_MVMT = 2;  //  低速移动 跑步 散步  Low movement device mobility state.\n" +
                    "// int DEVICE_MOBILITY_STATE_STATIONARY = 3;   //  固定设备  不移动 \n" +
                    "        line.append(\"device_mobility_state=\" + stats.deviceMobilityState);  // 设备的移动状态 \n" +
                    "        line.append(\",num_times_entered_state=\" + stats.numTimesEnteredState); // 执行 PNO的次数？\n" +
                    "        line.append(\",total_duration_ms=\" + stats.totalDurationMs);  // 总的时间 \n" +
                    "        line.append(\",pno_duration_ms=\" + stats.pnoDurationMs);  // PNO 时间 \n" +
                    "        pw.println(line.toString());\n" +
                    "    }\n" +
                    "}";
            keyWordList.add(wifi_22_26);



            KeyWordItem wifi_22_27 = new KeyWordItem();
            wifi_22_27.keyword = "WifiP2pMetrics:";
            wifi_22_27.explain=" WifiMetrics 的  WifiP2pMetrics mWifiP2pMetrics ; " ;
            wifi_22_27.classNameForShuxing = "class WifiMetrics {  WifiP2pMetrics mWifiP2pMetrics  }";
            wifi_22_27.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() {  WifiP2pMetrics mWifiP2pMetrics } ";
            wifi_22_27.expain1="******************  WifiP2pMetrics Begin  ******************\n" +
                    "public voiddump(PrintWriter pw) {   // Collects some metrics at this time.\n" +
                    "synchronized (mLock) {\n" +
                    "pw.println(\"WifiP2pMetrics:\");\n" +
                    "pw.println(\"mConnectionEvents:\");\n" +
                    "for (P2pConnectionEvent event : mConnectionEventList) {\n" +
                    "StringBuilder sb = new StringBuilder();\n" +
                    "Calendar c = Calendar.getInstance();\n" +
                    "c.setTimeInMillis(event.startTimeMillis);\n" +
                    "sb.append(\"startTime=\");\n" +
                    "sb.append(event.startTimeMillis == 0 ? \"            <null>\" : String.format(\"%tm-%td %tH:%tM:%tS.%tL\", c, c, c, c, c, c));\n" +
                    "sb.append(\", connectionType=\"); 【事件类型】\t\n" +
                    "sb.append(\", wpsMethod=\");\n" +
                    "sb.append(\", durationTakenToConnectMillis=\");\n" +
                    "sb.append(event.durationTakenToConnectMillis);\n" +
                    "sb.append(\", connectivityLevelFailureCode=\");\n" +
                    "pw.println(\"mGroupEvents:\");\t\n" +
                    "sb.append(\"netId=\");\n" +
                    "sb.append(event.netId);\n" +
                    "sb.append(\", startTime=\");\n" +
                    "sb.append(event.startTimeMillis == 0 ? \"            <null>\" :\n" +
                    "String.format(\"%tm-%td %tH:%tM:%tS.%tL\", c, c, c, c, c, c));\n" +
                    "sb.append(\", channelFrequency=\");\n" +
                    "sb.append(event.channelFrequency);\n" +
                    "sb.append(\", groupRole=\");\n" +
                    "pw.println(\"mWifiP2pStatsProto.numPersistentGroup=\"+ mNumPersistentGroup);\n" +
                    "pw.println(\"mWifiP2pStatsProto.numTotalPeerScans=\"+ mWifiP2pStatsProto.numTotalPeerScans);\n" +
                    "pw.println(\"mWifiP2pStatsProto.numTotalServiceScans=\"+ mWifiP2pStatsProto.numTotalServiceScans);\t\n" +
                    "}\n" +
                    "******************  WifiP2pMetrics Begin  ******************\n" +
                    "\n" +
                    "public class WifiMetrics {  \n" +
                    "\t// 打印  WifiP2p 相关的 统计信息\n" +
                    " WifiP2pMetrics mWifiP2pMetrics;\n" +
                    " void dump(){\n" +
                    "    mWifiP2pMetrics.dump(pw);\n" +
                    "}\n" +
                    " ";
            keyWordList.add(wifi_22_27);



            KeyWordItem wifi_22_28 = new KeyWordItem();
            wifi_22_28.keyword = "---Easy Connect/DPP metrics---";
            wifi_22_28.explain=" DPP 替换WPS 的 一些统计信息  " ;
            wifi_22_28.classNameForShuxing = "class WifiMetrics {  DppMetrics mDppMetrics;  }";
            wifi_22_28.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() {  DppMetrics mDppMetrics;  } ";
            wifi_22_28.expain1="public class WifiMetrics {\n" +
                    "\n" +
                    "DppMetrics mDppMetrics;  /** DPP */\n" +
                    "\n" +
                    "   void dump(){\n" +
                    "   mDppMetrics.dump(pw);\n" +
                    "   }\n" +
                    "}\n" +
                    "\n" +
                    "************************  DppMetrics begin ************************\n" +
                    "/**\n" +
                    " * Provides metrics for Wi-Fi Easy Connect (DPP). Metrics include number of initiator requests,\n" +
                    " * number of successes, failures and time completion histogram.\n" +
                    " */\n" +
                    "public class DppMetrics {  // DPP 是 替换  WPS 的 一种 快速链接的方式\n" +
                    " WifiMetricsProto.WifiDppLog mWifiDppLogProto = new WifiMetricsProto.WifiDppLog();\n" +
                    "      //   < 1   依据时间分类  该事件  \n" +
                    "    //   [1, 10)\n" +
                    "    //   [10, 25)\n" +
                    "    //   [25, 39)\n" +
                    "    //   >= 39  - which means timeout.\n" +
                    " int[] DPP_OPERATION_TIME = {1, 10, 25, 39};\t\n" +
                    "\n" +
                    " SparseIntArray mHistogramDppFailureCode = new SparseIntArray();   // Failure codes\n" +
                    " SparseIntArray mHistogramDppConfiguratorSuccessCode = new SparseIntArray();     // Configurator success codes\n" +
                    " Object mLock = new Object();\n" +
                    " \n" +
                    "public void dump(PrintWriter pw) {\n" +
                    "synchronized (mLock) {\n" +
                    "\tpw.println(\"---Easy Connect/DPP metrics---\");\n" +
                    "\t// DPP 请求次数  DPP Configurator-Initiator requests\n" +
                    "\tpw.println(\"mWifiDppLogProto.numDppConfiguratorInitiatorRequests=\" + mWifiDppLogProto.numDppConfiguratorInitiatorRequests);\n" +
                    "\t// DPP Enrollee-Initiator requests  次数 \n" +
                    "\tpw.println(\"mWifiDppLogProto.numDppEnrolleeInitiatorRequests=\"  + mWifiDppLogProto.numDppEnrolleeInitiatorRequests);\n" +
                    "\t// numDppEnrolleeSuccess  DPP Enrollee success counter 次数\n" +
                    "\tpw.println(\"mWifiDppLogProto.numDppEnrolleeSuccess=\"  + mWifiDppLogProto.numDppEnrolleeSuccess);\n" +
                    "\tif (mHistogramDppFailureCode.size() > 0) {  // 失败原因列表\n" +
                    "\t\tpw.println(\"mHistogramDppFailureCode=\");\n" +
                    "\t\tpw.println(mHistogramDppFailureCode);\n" +
                    "\t}\n" +
                    "\tif (mHistogramDppConfiguratorSuccessCode.size() > 0) {  // 成功原因列表\n" +
                    "\t\tpw.println(\"mHistogramDppConfiguratorSuccessCode=\");\n" +
                    "\t\tpw.println(mHistogramDppConfiguratorSuccessCode);\n" +
                    "\t}\n" +
                    "\tif (mHistogramDppOperationTime.numNonEmptyBuckets() > 0) {  // 五种时间分类列表\n" +
                    "\t\tpw.println(\"mHistogramDppOperationTime=\");\n" +
                    "\t\tpw.println(mHistogramDppOperationTime);\n" +
                    "\t}\n" +
                    "\tpw.println(\"---End of Easy Connect/DPP metrics---\");\n" +
                    "}\n" +
                    "    }\n" +
                    "}\n" +
                    "************************  DppMetrics End ************************";
            keyWordList.add(wifi_22_28);


            KeyWordItem wifi_22_29 = new KeyWordItem();
            wifi_22_29.keyword = "mNetworkIdToNominatorId:";
            wifi_22_29.explain="用户选择的网络方式 来源 统计 " ;
            wifi_22_29.classNameForShuxing = "class WifiMetrics {  SparseIntArray mNetworkIdToNominatorId  }";
            wifi_22_29.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiMetrics.java =>dump() {  SparseIntArray mNetworkIdToNominatorId } ";
            wifi_22_29.expain1="LOG示例 \n" +
                    "Tracks the nominator for each network (i.e. which entity made the suggestion to connect).\n" +
                    "SparseIntArray mNetworkIdToNominatorId = new SparseIntArray();\n" +
                    "WifiMetrics.dump(){    pw.println(\"mNetworkIdToNominatorId:\\n\" + mNetworkIdToNominatorId); }\n" +
                    "\n" +
                    "mNetworkIdToNominatorId:\n" +
                    "{0=2, 1=2, 2=2, 3=2, 4=2, 5=2, 6=1}\n" +
                    "\n" +
                    "0=2  \n" +
                    "0 --key-- 标识networkid , 对应用户保存的网络\n" +
                    "2 --value-- 标识推荐key这个网络的 推荐者ID \n" +
                    "//  推荐者ID 列表\n" +
                    "NOMINATOR_UNKNOWN = 0;  // Unknown nominator\n" +
                    "NOMINATOR_MANUAL = 1;   // User selected network manually\n" +
                    "NOMINATOR_SAVED = 2;     // Saved network\n" +
                    "NOMINATOR_SUGGESTION = 3;  // Suggestion API\n" +
                    "NOMINATOR_PASSPOINT = 4;     // Passpoint\n" +
                    "NOMINATOR_CARRIER = 5;     // Carrier suggestion\n" +
                    "NOMINATOR_EXTERNAL_SCORED = 6;  // External scorer\n" +
                    "NOMINATOR_SPECIFIER = 7;     // Network Specifier\n" +
                    "NOMINATOR_SAVED_USER_CONNECT_CHOICE = 8;     // User connected choice override\n" +
                    "NOMINATOR_OPEN_NETWORK_AVAILABLE = 9;     // Open Network Available Pop-up";
            keyWordList.add(wifi_22_29);



            KeyWordItem wifi_22_30 = new KeyWordItem();
            wifi_22_30.keyword = "Dump of WifiNetworkSuggestionsManager";
            wifi_22_30.explain="APP 推荐的网络的合计 统计信息 " ;
            wifi_22_30.classNameForShuxing = "class WifiNetworkSuggestionsManager { ";
            wifi_22_30.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiNetworkSuggestionsManager.java =>dump() { } ";
            wifi_22_30.expain1="class WifiServiceImpl extends BaseWifiService {\n" +
                    "WifiNetworkSuggestionsManager mWifiNetworkSuggestionsManager;\n" +
                    "\n" +
                    "void dump() {\n" +
                    "   mWifiNetworkSuggestionsManager.dump(fd, pw, args);\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "******************** WifiNetworkSuggestionsManager Begin ********************\n" +
                    " class WifiNetworkSuggestionsManager {\n" +
                    "// Map of package name of an app to the set of active network suggestions provided by the app.\n" +
                    "// APP包名 以及该包对应的推荐的网络信息  PerAppInfo\n" +
                    "  Map<String, PerAppInfo> mActiveNetworkSuggestionsPerApp = new HashMap<>();\n" +
                    "  \n" +
                    "  // 当前所有APP推荐的网络网络集合 List of {@link WifiNetworkSuggestion} matching the current connected network.\n" +
                    "   Set<ExtendedWifiNetworkSuggestion> mActiveNetworkSuggestionsMatchingConnection;\n" +
                    "  \n" +
                    "  \n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tpw.println(\"Dump of WifiNetworkSuggestionsManager\");\n" +
                    "\tpw.println(\"WifiNetworkSuggestionsManager - Networks Begin ----\");\n" +
                    "\tfor (Map.Entry<String, PerAppInfo> networkSuggestionsEntry : mActiveNetworkSuggestionsPerApp.entrySet()) {\n" +
                    "\t\tpw.println(\"Package Name: \" + networkSuggestionsEntry.getKey());  // 包名\n" +
                    "\t\tPerAppInfo appInfo = networkSuggestionsEntry.getValue();\n" +
                    "\t\tpw.println(\"Has user approved: \" + appInfo.hasUserApproved); // 用户是否确认同意 \n" +
                    "\t\tfor (ExtendedWifiNetworkSuggestion extNetworkSuggestion: appInfo.extNetworkSuggestions) {\n" +
                    "\t\t\tpw.println(\"Network: \" + extNetworkSuggestion);  // 该APP 推荐的网络\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "\tpw.println(\"WifiNetworkSuggestionsManager - Networks End ----\");\n" +
                    "\tpw.println(\"WifiNetworkSuggestionsManager - Network Suggestions matching connection: \"+ mActiveNetworkSuggestionsMatchingConnection);\n" +
                    "}\n" +
                    "\n" +
                    "******************** WifiNetworkSuggestionsManager End ********************";
            keyWordList.add(wifi_22_30);


            KeyWordItem wifi_22_31 = new KeyWordItem();
            wifi_22_31.keyword = "ScoringParams:";
            wifi_22_31.explain="计算WIFI的分数的一些参数 来自 WIFIInfo " ;
            wifi_22_31.classNameForShuxing = "class ScoringParams { ";
            wifi_22_31.shuxingDefine=" 【 (WifiServiceImpl.java -> WifiInjector.java =》ScoringParams.java=>dump() { } ";
            wifi_22_31.expain1="****************  ScoringParams Begin    ****************\n" +
                    "\n" +
                    "public class ScoringParams {\n" +
                    "    private static final int EXIT = 0;\n" +
                    "    private static final int ENTRY = 1;\n" +
                    "    private static final int SUFFICIENT = 2;\n" +
                    "    private static final int GOOD = 3;\n" +
                    "\n" +
                    "\t/** RSSI thresholds for 2.4 GHz band (dBm) */\n" +
                    "public static final String KEY_RSSI2 = \"rssi2\";\n" +
                    "public final int[] rssi2 = {-83, -80, -73, -60};\n" +
                    "\n" +
                    "/** RSSI thresholds for 5 GHz band (dBm) */\n" +
                    "public static final String KEY_RSSI5 = \"rssi5\";\n" +
                    "public final int[] rssi5 = {-80, -77, -70, -57};\n" +
                    "\t\n" +
                    " /** Guidelines based on packet rates (packets/sec) */  包速率\t\n" +
                    "public static final String KEY_PPS = \"pps\";\n" +
                    "public final int[] pps = {0, 1, 100};\n" +
                    "\n" +
                    "/** Number of seconds for RSSI forecast */  // RSSI 预测值 \n" +
                    "public static final String KEY_HORIZON = \"horizon\";\n" +
                    "public static final int MIN_HORIZON = -9;\n" +
                    "public static final int MAX_HORIZON = 60;\n" +
                    "public int horizon = 15;\n" +
                    "\n" +
                    "/** Number 0-10 influencing requests for network unreachability detection */\n" +
                    "public static final String KEY_NUD = \"nud\";  // 网络不可达的标识 \n" +
                    "public static final int MIN_NUD = 0;\n" +
                    "public static final int MAX_NUD = 10;\n" +
                    "public int nud = 8;\n" +
                    "\n" +
                    "/** Experiment identifier */\n" +
                    "public static final String KEY_EXPID = \"expid\";\n" +
                    "public static final int MIN_EXPID = 0;\n" +
                    "public static final int MAX_EXPID = Integer.MAX_VALUE;\n" +
                    "public int expid = 0;\n" +
                    "\t\t\n" +
                    "        public String toString() {\n" +
                    "            StringBuilder sb = new StringBuilder();\n" +
                    "            appendKey(sb, KEY_RSSI2);\n" +
                    "            appendInts(sb, rssi2);\n" +
                    "            appendKey(sb, KEY_RSSI5);\n" +
                    "            appendInts(sb, rssi5);\n" +
                    "            appendKey(sb, KEY_PPS);\n" +
                    "            appendInts(sb, pps);\n" +
                    "            appendKey(sb, KEY_HORIZON);\n" +
                    "            sb.append(horizon);\n" +
                    "            appendKey(sb, KEY_NUD);\n" +
                    "            sb.append(nud);\n" +
                    "            appendKey(sb, KEY_EXPID);\n" +
                    "            sb.append(expid);\n" +
                    "            return sb.toString();\n" +
                    "        }\n" +
                    "\t\t\n" +
                    "   【WifiNative】 public ScoringParams getScoringParams() {\n" +
                    "        return mScoringParams;\n" +
                    "    }\n" +
                    "****************  ScoringParams End    ****************\n" +
                    "class WifiServiceImpl extends BaseWifiService {\n" +
                    "Settings.Global.WIFI_SCORE_PARAMS =  \"wifi_score_params\"  \n" +
                    "void dump() {\n" +
                    " pw.println(\"ScoringParams: settings put global \" + Settings.Global.WIFI_SCORE_PARAMS + \" \" +mWifiInjector.getScoringParams());\n" +
                    "}\n" +
                    "}\n" +
                    "Log示例: \n" +
                    "ScoringParams: settings put global wifi_score_params \n" +
                    "rssi2=-83:-80:-73:-60,rssi5=-80:-77:-70:-57,pps=0:1:100,horizon=15,nud=8,expid=0";
            keyWordList.add(wifi_22_31);

            KeyWordItem wifi_22_32 = new KeyWordItem();
            wifi_22_32.keyword = "WifiScoreReport:";
            wifi_22_32.explain="WIFI 分数的一些报告 " ;
            wifi_22_32.classNameForShuxing = "class WifiScoreReport { ";
            wifi_22_32.shuxingDefine=" 【 (WifiServiceImpl.java -> ClientModeImpl.java =WifiScoreReport.java=>dump() { } ";
            wifi_22_32.expain1="****************  WifiScoreReport Begin    ****************\n" +
                    "public class WifiScoreReport {\n" +
                    " LinkedList<String> mLinkMetricsHistory = new LinkedList<String>(); //These are stored as csv formatted lines\n" +
                    "\t\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        LinkedList<String> history;\n" +
                    "        synchronized (mLinkMetricsHistory) {\n" +
                    "            history = new LinkedList<>(mLinkMetricsHistory);\n" +
                    "        }\n" +
                    "        pw.println(\"time,session,netid,rssi,filtered_rssi,rssi_threshold,\"\n" +
                    "                + \"freq,linkspeed,tx_good,tx_retry,tx_bad,rx_pps,nudrq,nuds,s1,s2,score\");\n" +
                    "        for (String line : history) {\n" +
                    "            pw.println(line);\n" +
                    "        }\n" +
                    "        history.clear();\n" +
                    "    }\n" +
                    "\t\n" +
                    "****************  WifiScoreReport End    ****************\n" +
                    "class WifiServiceImpl extends BaseWifiService {\n" +
                    "void dump() {\n" +
                    "\n" +
                    " WifiScoreReport wifiScoreReport = mClientModeImpl.getWifiScoreReport();\n" +
                    "\tif (wifiScoreReport != null) {\n" +
                    "\t\tpw.println(\"WifiScoreReport:\");\n" +
                    "\t\twifiScoreReport.dump(fd, pw, args);\n" +
                    "\t}\n" +
                    "}\n" +
                    "\n" +
                    "LOG示例: \n" +
                    "time【时间- 1 】,session【会话ID- 2(检测WIFI的累计次数) 】,netid【NetworkAgent.netwokId 网络ID- 3 】,rssi【信号强度- 4 】,\n" +
                    "filtered_rssi【最新估算出来的Rssi强度- 5 】\n" +
                    ",rssi_threshold【最新信号2.4G(-83) 5G(-80) -6】,freq【频段-7】,linkspeed【速率-8 Mb/s】,\n" +
                    "tx_good【传输成功比例过滤最低的信号强度- 9 】,tx_retry【重传比例-10】,tx_bad【丢包比例-11】,\n" +
                    "rx_pps【接口包数量/每秒- 12 】,nudrq【邻居不可达检测请求次数-13】,\n" +
                    "nuds【检测到NUD(Neighbor Unreachable Detection,邻居不可达检测)的自字数-14】\n" +
                    ",s1【连接质量分数- 15 】,s2【连接速率分数- 16 】,score【分数- 17 】\n" +
                    "11-22 01:54:21.343【1】,2【2】,100【3】,-26.0【4】,\n" +
                    "-26.0【 5 】,\n" +
                    "-83.0【6】,2412【7】,6【8 Mb/s】,\n" +
                    "0.15【9】,0.00【10】,0.00【11】,\n" +
                    "0.15【12】,0【13】,\n" +
                    "1【14】,\n" +
                    "97【 15 】,107【 16 】,60【 17 】 \n 记录WIFI连接的情况 只有在WIFI连接的时候才记录  每隔三秒记录一次【亮屏下才记录】 能查看WIFI的使用情况 信道 信号强度 断开 的大概情况";
            keyWordList.add(wifi_22_32);

// https://sse.am.mot.com/q_source/xref/mq-r-6125/frameworks/base/core/java/android/net/NetworkCapabilities.java
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