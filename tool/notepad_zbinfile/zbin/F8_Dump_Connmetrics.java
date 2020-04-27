import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class F8_Dump_Connmetrics {

//   getprop.txt  中读取 [ro.bui  ld.version.release]: [10]
//getprop.txt   [ro.hardware.soc.manufacturer]: [qcom]   制造商
//  如果不包含  那么 检测 是否包含MTK
    // .mtk  或者  .mtk
    // .qcom  或者  qcom.




    static String F8DirPathStr = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F8";
    static File F8DirFile = new File(F8DirPathStr);
    static  String getpropFileName = "getprop.txt";
    static  String connectivityFileName = "connmetrics.txt";

    // adb shell dumpsys connmetrics  > connmetrics.txt
    // adb shell dumpsys connmetrics  proto
    // adb shell dumpsys connmetrics  list
    // adb shell dumpsys connmetrics  flush
    static Map<String,File> allDumpMap = new HashMap<String,File>();

    // 获取在目录 F8DirFile 下 所有文件的 绝对路径
    static   ArrayList<File> AllDumpFileList  = new ArrayList<File>();

    static Map<String,String> fileNameMapClass = new HashMap<String,String>();
    static{

        // class ConnectivityService extends IConnectivityManager.Stub {
   //     fileNameMapClass.put(connectivityFileName,"/frameworks/base/services/core/java/com/android/server/ConnectivityService.java");

     //   /frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java
    //   public final class Impl extends IIpConnectivityMetrics.Stub {
        fileNameMapClass.put(connectivityFileName,"/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java");

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
        F8_Dump_Connmetrics mDumpAnalysis = new F8_Dump_Connmetrics();
        mDumpAnalysis.initAnalysisWithVersion();
        curAndroidAnalysis.analysisFile();
        NotePadOpenTargetFile();



    }

    static void NotePadOpenTargetFile(){
        String absPath = F8DirPathStr+File.separator+connectivityFileName;
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
                    listFilename.add(connectivityFileName);
                    break;
                case 10:
                    listFilename.add(getpropFileName);
                    listFilename.add(connectivityFileName);
                    break;

                case 11:
                    listFilename.add(getpropFileName);
                    listFilename.add(connectivityFileName);

                    break;
                default:
                    listFilename.add(getpropFileName);
                    listFilename.add(connectivityFileName);

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

            //     pw.print("NetworkFactories for:");
            // connectivity.txt;


            file_keyword_map.put(connectivityFileName,initConnectivityKeyWordList());

            return file_keyword_map;
        }


        ArrayList<KeyWordItem>    initConnectivityKeyWordList(){
            ArrayList<KeyWordItem> keyWordList = new ArrayList<KeyWordItem>();

            KeyWordItem connectivity_1_1 = new KeyWordItem();
            connectivity_1_1.keyword = "metrics events:";
            connectivity_1_1.explain="网络度量事件的集合  断网啊 显示无网之类的 ";
            connectivity_1_1.classNameForShuxing = "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java";
            connectivity_1_1.printcode="IpConnectivityMetrics.java   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_1.printLogUrl="ApfStats |  ApfProgramEvent | DhcpClientEvent | IpReachabilityEvent | IpManagerEvent | NetworkEvent | RaEvent | ValidationProbeEvent\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ ApfStats\n" +
                    "ApfStats(31056905ms 4096B RA: {0 received, 0 matching, 0 dropped, 0 zero lifetime, 0 parse errors}, updates: {all: 8, RAs: 0, allow multicast: 0})\n" +
                    "/frameworks/base/core/java/android/net/metrics/ApfStats.java   包过虑机制 APF(Advanced Policy Firewall) linux下的高级策略防火墙\n" +
                    "class ApfStats implements IpConnectivityLog.Event {\n" +
                    "\n" +
                    "//An event logged for an interface with APF capabilities when its IpClient state machine exits.\n" +
                    "// IpClient state machine  IPClient状态机退出时 记录 APF(Advanced Policy Firewall) 高级策略防火墙 capabilities 的能力\n" +
                    "class ApfCapabilities implements Parcelable {}\n" +
                    "Get the APF (Android Packet Filter) capabilities of the device  获取安卓的帧过滤功能\n" +
                    "public String toString() {\n" +
                    "\treturn new StringBuilder(\"ApfStats(\")\n" +
                    "\t\t\t.append(String.format(\"%dms \", durationMs))  //  long durationMs;  时间间隔\n" +
                    "\t\t\t.append(String.format(\"%dB RA: {\", maxProgramSize))  // int maxProgramSize; 由硬件声明的最大帧过滤包大小  maximum APF program size advertised by hardware.\n" +
                    "\t\t\t.append(String.format(\"%d received, \", receivedRas)) // int receivedRas; 接收地址的个数 number of received RAs.\n" +
                    "\t\t\t.append(String.format(\"%d matching, \", matchingRas)) // int matchingRas;   number of received RAs matching a known RA.\n" +
                    "\t\t\t.append(String.format(\"%d dropped, \", droppedRas)) //  int droppedRas; number of received RAs ignored due to the MAX_RAS limit.\n" +
                    "\t\t\t.append(String.format(\"%d zero lifetime, \", zeroLifetimeRas)) //   int zeroLifetimeRas; number of received RAs with a minimum lifetime of 0.\n" +
                    "\t\t\t.append(String.format(\"%d parse errors}, \", parseErrors))  // int parseErrors;  umber of received RAs that could not be parsed.\n" +
                    "\t\t\t.append(String.format(\"updates: {all: %d, RAs: %d, allow multicast: %d})\",\n" +
                    "\t\t\t\t\tprogramUpdatesAll, programUpdates, programUpdatesAllowingMulticast))\n" +
                    "\t\t\t// int programUpdatesAll;  \ttotal number of APF program updates. \n" +
                    "\t\t\t// int programUpdates ;   number of APF program updates from receiving RAs. \n" +
                    "\t\t\t// int programUpdatesAllowingMulticast;    number of APF program updates from allowing multicast traffic.\n" +
                    "\t\t\t.toString();\n" +
                    "}\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ ApfProgramEvent\n" +
                    "ApfProgramEvent(0/0 RAs 221B 7890s/forever FLAG_MULTICAST_FILTER_ON|FLAG_HAS_IPV4_ADDRESS)\n" +
                    "更新防火墙程序的操作日志记录\n" +
                    "/frameworks/base/core/java/android/net/metrics/ApfProgramEvent.java\n" +
                    "\n" +
                    "class ApfProgramEvent implements IpConnectivityLog.Event {\n" +
                    "\n" +
                    "long lifetime;       // 生命周期 Maximum computed lifetime of the program in seconds  默认值是  Long.MAX_VALUE\n" +
                    "int filteredRas;     // Number of RAs filtered by the APF program  \n" +
                    "// 我在我们DHCP服务器上查询一个IP地址时发现其MAC地址显示为RAS\n" +
                    "// RAS简称Remote Access Service，即远程访问服务，你的网络应该是有人用VPN连接进来过，DHCP会给这些用户分配IP地址，类型就是RAS，而不是MAC地址\n" +
                    "int currentRas;      // Total number of current RAs at generation time\n" +
                    "int programLength;   // Length of the APF program in bytes\n" +
                    "long actualLifetime; // Effective program lifetime in seconds\n" +
                    "int flags;           //  bit功能标识位   Bitfield compound of FLAG_* constants \n" +
                    "public static final int FLAG_MULTICAST_FILTER_ON = 0;     //  是否组播过滤\n" +
                    "public static final int FLAG_HAS_IPV4_ADDRESS    = 1;   // 是否有Ipv4地址\n" +
                    "\t\n" +
                    "public String toString() {\n" +
                    "\tString lifetimeString = (lifetime < Long.MAX_VALUE) ? lifetime + \"s\" : \"forever\";\n" +
                    "\treturn String.format(\"ApfProgramEvent(%d/%d RAs %dB %ds/%s %s)\", filteredRas, currentRas,\n" +
                    "\t\t\tprogramLength, actualLifetime, lifetimeString, namesOf(flags));\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ DhcpClientEvent\n" +
                    "DhcpClientEvent(DhcpRequestingState, 130ms)\n" +
                    "DhcpClientEvent(ConfiguringInterfaceState, 44ms)\n" +
                    "DhcpClientEvent(InitialBoundState, 2230ms)\n" +
                    "\n" +
                    "An event recorded when a DhcpClient state machine transitions to a new state. \n" +
                    "当 DhcpClient state machine  转移动新的状态时记录的日志\n" +
                    "public final class DhcpClientEvent implements IpConnectivityLog.Event {\n" +
                    "\n" +
                    "String msg;   //  Names for recording DhcpClient pseudo-state transitions.\n" +
                    "int durationMs;  // 事件持续时间\n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        return String.format(\"DhcpClientEvent(%s, %dms)\", msg, durationMs);\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "InitialBoundState 标识从 DhcpInitState to DhcpBoundState\n" +
                    "RenewingBoundState  标识从  DhcpRenewingState  to  DhcpBoundState   \n" +
                    "\n" +
                    "                     \t\t          _-----=>  ConfiguringInterfaceState\n" +
                    "                                     / _----=>  WaitBeforeRenewalState\n" +
                    "                                     | / _---=> DhcpRenewingState \n" +
                    "                                     || / _--=> DhcpRebindingState \n" +
                    "                                     ||| /_--=> DhcpBoundState\n" +
                    "                                     ||||/\n" +
                    "                                     |||||\n" +
                    "\t\t\t\t\t _------=>  DhcpHaveLeaseState\n" +
                    "                    / _-----=>  DhcpInitState\n" +
                    "                    | / _----=> WaitBeforeStartState \n" +
                    "                    || / _---=> DhcpSelectingState\n" +
                    "                    ||| / _--=> DhcpRequestingState\n" +
                    "                    |||| /_--=> DhcpInitRebootState\n" +
                    "                    |||||/ _-=> DhcpRebootingState\n" +
                    "                    ||||||/\n" +
                    "   StoppedState   DhcpState\n" +
                    "   \n" +
                    "   \n" +
                    "~~~~~~~~~~~~~~~~ IpReachabilityEvent\n" +
                    "/frameworks/base/core/java/android/net/metrics/IpReachabilityEvent.java\n" +
                    "\n" +
                    "\n" +
                    "发送邻居探测  或者 接收邻居探测结果 记录的日志 \n" +
                    "class IpReachabilityEvent implements IpConnectivityLog.Event {\n" +
                    "\n" +
                    "    // Event types. 事件类型分类\n" +
                    "    /** A probe forced by IpReachabilityMonitor. */  请求探测邻居事件\n" +
                    "    public static final int PROBE                     = 1 << 8;\n" +
                    "\t\n" +
                    "    /** Neighbor unreachable after a forced probe. */   请求探测邻居未回应事件\n" +
                    "    public static final int NUD_FAILED                = 2 << 8;\n" +
                    "\t\n" +
                    "/** Neighbor unreachable after a forced probe, IP provisioning is also lost. */请求探测邻居未回应事件 并且连接断开事件\n" +
                    "    public static final int PROVISIONING_LOST         = 3 << 8;\n" +
                    "\t\n" +
                    "    /** Neighbor unreachable notification from kernel. */  来自内核 kernel的 邻居不可达事件\n" +
                    "    public static final int NUD_FAILED_ORGANIC        = 4 << 8;\n" +
                    "\t\n" +
                    "/** Neighbor unreachable notification from kernel, IP provisioning is also lost. */来自内核 kernel的邻居不可达并且连接断开事件\n" +
                    "    public static final int PROVISIONING_LOST_ORGANIC = 5 << 8;\n" +
                    "\n" +
                    "    // eventType byte format (MSB to LSB):\n" +
                    "    // byte 0: unused   第0个字节\n" +
                    "    // byte 1: unused   第1个字节\n" +
                    "    // byte 2: type of event: PROBE, NUD_FAILED, PROVISIONING_LOST\n" +
                    "    // byte 3: when byte 2 == PROBE, errno code from RTNetlink or IpReachabilityMonitor.\n" +
                    "    /** @hide */\n" +
                    "    public final int eventType;  // bit 功能标识位 \n" +
                    "\n" +
                    "String[] constants =  new String[]{\"PROBE\", \"PROVISIONING_\", \"NUD_\"}\n" +
                    "  \n" +
                    "   public String toString() {\n" +
                    "        int hi = eventType & 0xff00;\n" +
                    "        int lo = eventType & 0x00ff;\n" +
                    "        String eventName = Decoder.constants.get(hi);\n" +
                    "        return String.format(\"IpReachabilityEvent(%s:%02x)\", eventName, lo);\n" +
                    "    }\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "IpReachabilityEvent(PROBE:00)   // 00--eventType的第三个和第四个字节\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~  IpManagerEvent  IP获取机制—IpManager \n" +
                    "https://blog.csdn.net/u011006622/article/details/78850097 \n" +
                    "IpManagerEvent(COMPLETE_LIFECYCLE, 31056966ms)   状态机 \n" +
                    "IpManager 来管理dhcpcd成功还是失败等状态\n" +
                    "将ip赋值给IpConfiguration和LinkProperties传递到上层的framework\n" +
                    "/frameworks/base/core/java/android/net/metrics/IpManagerEvent.java\n" +
                    "/frameworks/base/services/net/java/android/net/ip/IpClientManager.java\n" +
                    "/packages/modules/NetworkStack/src/android/net/ip/IpClient.java\n" +
                    "\n" +
                    "class IpManagerEvent implements IpConnectivityLog.Event {\n" +
                    "IpManagerEvent 事件类型\n" +
                    "int PROVISIONING_OK                       = 1; \n" +
                    "int PROVISIONING_FAIL                     = 2;\n" +
                    "int COMPLETE_LIFECYCLE                    = 3;  // 【完成IP地址的获取了?】\n" +
                    "int ERROR_STARTING_IPV4                   = 4;\n" +
                    "int ERROR_STARTING_IPV6                   = 5;\n" +
                    "int ERROR_STARTING_IPREACHABILITYMONITOR  = 6;\n" +
                    "int ERROR_INVALID_PROVISIONING            = 7;\n" +
                    "int ERROR_INTERFACE_NOT_FOUND             = 8;\n" +
                    "\n" +
                    "    public final @EventType int eventType;  // 事件类型 \n" +
                    "    public final long durationMs;   //持续时间 \n" +
                    "\t\n" +
                    "public String toString() {\n" +
                    "\treturn String.format(\"IpManagerEvent(%s, %dms)\", Decoder.constants.get(eventType), durationMs);\n" +
                    "}\n" +
                    "                    _-----=>  RunningState\n" +
                    "                   /\n" +
                    "                   |\n" +
                    "                   |\n" +
                    "StoppedState   StartedState  StoppingState\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ NetworkEvent\n" +
                    "/frameworks/base/core/java/android/net/metrics/NetworkEvent.java\n" +
                    "class NetworkEvent implements IpConnectivityLog.Event {\n" +
                    " int NETWORK_CONNECTED            = 1;    // 连接成功\n" +
                    " int NETWORK_VALIDATED            = 2;    // 网络已验证OK\n" +
                    " int NETWORK_VALIDATION_FAILED    = 3;   // 网络已验证失败   将提示当前网络联网失败\n" +
                    " int NETWORK_CAPTIVE_PORTAL_FOUND = 4;   // 当前网络有portal的要求  强制主页”或“强制登录门户”\n" +
                    " int NETWORK_LINGER   = 5; // 当前网络进入拖延的状态(移动网络开启时 又链接上了wifi 那么移动网络进入拖延的状态)\n" +
                    " int NETWORK_UNLINGER             = 6;       // 离开 拖延状态\n" +
                    " int NETWORK_DISCONNECTED         = 7;    //  网络断开\n" +
                    " int NETWORK_FIRST_VALIDATION_SUCCESS      = 8;   // 首次验证成功\n" +
                    " int NETWORK_REVALIDATION_SUCCESS          = 9;   // 重复再验证成功\n" +
                    " int NETWORK_FIRST_VALIDATION_PORTAL_FOUND = 10;  // 首次验证时 发现 portal门户网站\n" +
                    " int NETWORK_REVALIDATION_PORTAL_FOUND     = 11;  // 再次验证时  发现 登录门户 (不强制)\n" +
                    " int NETWORK_CONSECUTIVE_DNS_TIMEOUT_FOUND = 12;  // DNS 超时\n" +
                    " int NETWORK_PARTIAL_CONNECTIVITY = 13;   // 链接上 PARTIAL 网络 \n" +
                    "\n" +
                    "     public final @EventType int eventType;  // 事件类型 \n" +
                    "    public final long durationMs;  //  持续时间 \n" +
                    "\t\n" +
                    "    public String toString() {\n" +
                    "        return String.format(\"NetworkEvent(%s, %dms)\", Decoder.constants.get(eventType), durationMs);\n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "NetworkEvent(NETWORK_REVALIDATION_SUCCESS, 130ms)\n" +
                    "NetworkEvent(NETWORK_CONNECTED, 0ms)\n" +
                    "NetworkEvent(NETWORK_FIRST_VALIDATION_SUCCESS, 355ms)\n" +
                    "NetworkEvent(NETWORK_DISCONNECTED, 0ms)\n" +
                    "NetworkEvent(NETWORK_VALIDATION_FAILED, 0ms)\n" +
                    "NetworkEvent(NETWORK_LINGER, 0ms)\n" +
                    "NetworkEvent(NETWORK_UNLINGER, 0ms)\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ RaEvent\n" +
                    "An event logged when the APF packet socket receives an RA packet.\n" +
                    "当防火墙socket接收到一个 RA packet 包事记录的日志 \n" +
                    "RaEvent(lifetimes: router=1800s, prefix_valid=221134s, prefix_preferred=134734s, route_info=-1s, rdnss=1800s, dnssl=-1s)\n" +
                    "\n" +
                    "/frameworks/base/core/java/android/net/metrics/RaEvent.java\n" +
                    "class RaEvent implements IpConnectivityLog.Event {\n" +
                    "\n" +
                    "long NO_LIFETIME = -1L;\n" +
                    " long routerLifetime;\n" +
                    " long prefixValidLifetime;\n" +
                    " long prefixPreferredLifetime;\n" +
                    " long routeInfoLifetime;\n" +
                    " long rdnssLifetime;\n" +
                    " long dnsslLifetime;\n" +
                    "\n" +
                    "\n" +
                    "   public String toString() {\n" +
                    "        return new StringBuilder(\"RaEvent(lifetimes: \")\n" +
                    "                .append(String.format(\"router=%ds, \", routerLifetime))\n" +
                    "                .append(String.format(\"prefix_valid=%ds, \", prefixValidLifetime))\n" +
                    "                .append(String.format(\"prefix_preferred=%ds, \", prefixPreferredLifetime))\n" +
                    "                .append(String.format(\"route_info=%ds, \", routeInfoLifetime))\n" +
                    "                .append(String.format(\"rdnss=%ds, \", rdnssLifetime))\n" +
                    "                .append(String.format(\"dnssl=%ds)\", dnsslLifetime))\n" +
                    "                .toString();\n" +
                    "    }\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "RaEvent(lifetimes: router=1800s, prefix_valid=221134s, prefix_preferred=134734s, route_info=-1s, rdnss=1800s, dnssl=-1s)\n" +
                    "\n" +
                    "\t\n" +
                    "~~~~~~~~~~~~~~~~ ValidationProbeEvent\n" +
                    "/frameworks/base/core/java/android/net/metrics/ValidationProbeEvent.java\n" +
                    " An event recorded by NetworkMonitor when sending a probe for finding captive portals.\n" +
                    " 去查找门户网站时记录的日志\n" +
                    " \n" +
                    "public final class ValidationProbeEvent implements IpConnectivityLog.Event {\n" +
                    "\n" +
                    "    public static final int PROBE_DNS       = 0;\n" +
                    "    public static final int PROBE_HTTP      = 1;\n" +
                    "    public static final int PROBE_HTTPS     = 2;\n" +
                    "    public static final int PROBE_PAC       = 3;\n" +
                    "    public static final int PROBE_FALLBACK  = 4;\n" +
                    "    public static final int PROBE_PRIVDNS   = 5;\n" +
                    "\n" +
                    "    public static final int DNS_FAILURE = 0;\n" +
                    "    public static final int DNS_SUCCESS = 1;\n" +
                    "\n" +
                    "    private static final int FIRST_VALIDATION  = 1 << 8;\n" +
                    "    private static final int REVALIDATION      = 2 << 8;\n" +
                    "\t\n" +
                    "    public final long durationMs;   // 持续时间 \n" +
                    "\t\n" +
                    "\tfinal @ReturnCode int returnCode;  // 返回code  404-关闭\n" +
                    "\t\n" +
                    "    // probeType byte format (MSB to LSB):\n" +
                    "    // byte 0: unused\n" +
                    "    // byte 1: unused\n" +
                    "    // byte 2: 0 = UNKNOWN, 1 = FIRST_VALIDATION, 2 = REVALIDATION\n" +
                    "    // byte 3: PROBE_* constant\n" +
                    "    public final int probeType;\n" +
                    "\t\n" +
                    "        return String.format(\"ValidationProbeEvent(%s:%d %s, %dms)\",\n" +
                    "                getProbeName(probeType), returnCode, getValidationStage(probeType), durationMs);\n" +
                    "    }\n" +
                    "\t\n" +
                    "LOG示例:\n" +
                    "ValidationProbeEvent(PROBE_DNS:0 REVALIDATION, 21ms)\n" +
                    "ValidationProbeEvent(PROBE_PAC:599 REVALIDATION, 12ms)\n" +
                    "ValidationProbeEvent(PROBE_HTTP:204 FIRST_VALIDATION, 147ms)\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ ConnectivityMetricsEvent.Parcelable data Begin ~~~~~~~~~~~~~~~~\n" +
                    "public Parcelable data;  // 携带的具体的数据   不透明的相关数据  /** Opaque event-specific data. */\n" +
                    "\n" +
                    "data类型集合: 实现了Parcelable 接口的所有类型都可以在这里打印\n" +
                    "\n" +
                    "ApfStats\n" +
                    "ApfStats(31056905ms 4096B RA: {0 received, 0 matching, 0 dropped, 0 zero lifetime, 0 parse errors}, updates: {all: 8, RAs: 0, allow multicast: 0})\n" +
                    "ApfStats(24685ms 4096B RA: {1 received, 0 matching, 0 dropped, 0 zero lifetime, 0 parse errors}, updates: {all: 3, RAs: 1, allow multicast: 0})\n" +
                    "ApfStats(7891130ms 4096B RA: {0 received, 0 matching, 0 dropped, 0 zero lifetime, 0 parse errors}, updates: {all: 2, RAs: 0, allow multicast: 0})\n" +
                    "\n" +
                    "ApfProgramEvent\n" +
                    "ApfProgramEvent(0/0 RAs 221B 5808s/forever FLAG_MULTICAST_FILTER_ON|FLAG_HAS_IPV4_ADDRESS)\n" +
                    "ApfProgramEvent(1/1 RAs 432B 22s/299s FLAG_MULTICAST_FILTER_ON|FLAG_HAS_IPV4_ADDRESS)\n" +
                    "ApfProgramEvent(0/0 RAs 221B 7890s/forever FLAG_MULTICAST_FILTER_ON|FLAG_HAS_IPV4_ADDRESS)\n" +
                    "\n" +
                    "\n" +
                    "DhcpClientEvent\n" +
                    "DhcpClientEvent(DhcpBoundState, 2179486ms)\n" +
                    "DhcpClientEvent(WaitBeforeStartState, 20ms)\n" +
                    "DhcpClientEvent(DhcpInitState, 2041ms)\n" +
                    "DhcpClientEvent(DhcpRequestingState, 130ms)\n" +
                    "DhcpClientEvent(ConfiguringInterfaceState, 44ms)\n" +
                    "DhcpClientEvent(InitialBoundState, 2230ms)\n" +
                    "DhcpClientEvent(RenewingBoundState, 201ms)\n" +
                    "\n" +
                    "IpReachabilityEvent\n" +
                    "IpReachabilityEvent(PROBE:00)\n" +
                    "\n" +
                    "\n" +
                    "IpManagerEvent\n" +
                    "IpManagerEvent(COMPLETE_LIFECYCLE, 31056966ms)\n" +
                    "IpManagerEvent(PROVISIONING_OK, 475ms)\n" +
                    "\n" +
                    "NetworkEvent\n" +
                    "NetworkEvent(NETWORK_REVALIDATION_SUCCESS, 130ms)\n" +
                    "NetworkEvent(NETWORK_CONNECTED, 0ms)\n" +
                    "NetworkEvent(NETWORK_FIRST_VALIDATION_SUCCESS, 355ms)\n" +
                    "NetworkEvent(NETWORK_DISCONNECTED, 0ms)\n" +
                    "NetworkEvent(NETWORK_VALIDATION_FAILED, 0ms)\n" +
                    "NetworkEvent(NETWORK_LINGER, 0ms)\n" +
                    "NetworkEvent(NETWORK_UNLINGER, 0ms)\n" +
                    "\n" +
                    "RaEvent\n" +
                    "RaEvent(lifetimes: router=1800s, prefix_valid=221134s, prefix_preferred=134734s, route_info=-1s, rdnss=1800s, dnssl=-1s)\n" +
                    "\n" +
                    "ValidationProbeEvent\n" +
                    "ValidationProbeEvent(PROBE_DNS:0 REVALIDATION, 21ms)\n" +
                    "ValidationProbeEvent(PROBE_PAC:599 REVALIDATION, 12ms)\n" +
                    "ValidationProbeEvent(PROBE_HTTP:204 FIRST_VALIDATION, 147ms)\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ ConnectivityMetricsEvent.Parcelable data End ~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ ConnectivityMetricsEvent Begin ~~~~~~~~~~~~~~~~\n" +
                    "/frameworks/base/core/java/android/net/ConnectivityMetricsEvent.java\n" +
                    "\n" +
                    "public final class ConnectivityMetricsEvent implements Parcelable {\n" +
                    "\n" +
                    "public long timestamp;  //  收集到该事件的时间戳   来自  System.currentTimeMillis()\n" +
                    "\n" +
                    "public long transports;  // 传输性能  bit位控制  \n" +
                    "\n" +
                    "public int netId;  // 与事件关联的网络的唯一id\n" +
                    "\n" +
                    "public String ifname;  // 与事件关联的网络接口的名称\n" +
                    "\n" +
                    "public Parcelable data;  // 携带的具体的数据   不透明的相关数据  /** Opaque event-specific data. */\n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        StringBuilder buffer = new StringBuilder(\"ConnectivityMetricsEvent(\");\n" +
                    "\t\t // %tT 时分秒  // %tL 毫秒 ConnectivityMetricsEvent(08:27:39.955 =>  【 08:27:39 】.【 955 】  \n" +
                    " buffer.append(String.format(\"%tT.%tL\", timestamp, timestamp)); \n" +
                    "        if (netId != 0) {\n" +
                    "            buffer.append(\", \").append(\"netId=\").append(netId);  // 打印关联的 网络的id 【 netId=602】\n" +
                    "        }\n" +
                    "        if (ifname != null) {\n" +
                    "            buffer.append(\", \").append(ifname);     // 网络接口名称  【 wlan0 ，\"空\"】\n" +
                    "        }\n" +
                    "        for (int t : BitUtils.unpackBits(transports)) {\n" +
                    "//  网络的类型  TRANSPORT_NAMES之一    【BLUETOOTH, WIFI , CELLULAR, ETHERNET,VPN , WIFI_AWARE ,LOWPAN】\n" +
                    "//  private static final String[] TRANSPORT_NAMES = {  // NetworkCapabilities.transportNameOf 如下\n" +
                    "//  \"CELLULAR\"【0】,   \"WIFI\"【1】,  \"BLUETOOTH\"【2】, \"ETHERNET\"【3】,\n" +
                    "//  \"VPN\"【4】, \"WIFI_AWARE\"【5】,   \"LOWPAN\"【6】,   \"TEST\"【7】 };\n" +
                    "\t\t\n" +
                    "      buffer.append(\", \").append(NetworkCapabilities.transportNameOf(t));  // 打印Bit位的功能信息\n" +
                    "        }\n" +
                    "\t\t\n" +
                    "        buffer.append(\"): \").append(data.toString());\n" +
                    "        return buffer.toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "\t\n" +
                    "LOG示例:\n" +
                    "ConnectivityMetricsEvent(15:02:22.752, netId=631, CELLULAR): NetworkEvent(NETWORK_CONNECTED, 0ms)\n" +
                    "ConnectivityMetricsEvent(15:02:22.954, netId=631, CELLULAR): ValidationProbeEvent(PROBE_DNS:1 FIRST_VALIDATION, 173ms)\n" +
                    "ConnectivityMetricsEvent(15:02:23.100, netId=631, CELLULAR): ValidationProbeEvent(PROBE_HTTP:204 FIRST_VALIDATION, 147ms)\n" +
                    "ConnectivityMetricsEvent(15:02:23.107, netId=631, CELLULAR): NetworkEvent(NETWORK_FIRST_VALIDATION_SUCCESS, 355ms)\n" +
                    "ConnectivityMetricsEvent(15:03:01.928, netId=631, CELLULAR): NetworkEvent(NETWORK_DISCONNECTED, 0ms)\n" +
                    "\n" +
                    "ConnectivityMetricsEvent(08:27:39.955, netId=602, WIFI): NetworkEvent(NETWORK_CONNECTED, 0ms)\n" +
                    "ConnectivityMetricsEvent(08:27:39.992, netId=602, WIFI): ValidationProbeEvent(PROBE_DNS:0 FIRST_VALIDATION, 16ms)\n" +
                    "ConnectivityMetricsEvent(08:27:39.995, netId=602, WIFI): ValidationProbeEvent(PROBE_HTTP:599 FIRST_VALIDATION, 3ms)\n" +
                    "ConnectivityMetricsEvent(08:27:39.999, netId=602, WIFI): NetworkEvent(NETWORK_VALIDATION_FAILED, 0ms)  【无网络】\n" +
                    "\n" +
                    "ConnectivityMetricsEvent(15:03:45.732, netId=615, WIFI): NetworkEvent(NETWORK_VALIDATION_FAILED, 0ms)  【无网络】\n" +
                    "ConnectivityMetricsEvent(15:03:46.770, netId=615, WIFI): ValidationProbeEvent(PROBE_DNS:0 REVALIDATION, 31ms)\n" +
                    "ConnectivityMetricsEvent(15:03:46.829, netId=615, WIFI): ValidationProbeEvent(PROBE_PAC:599 REVALIDATION, 59ms)\n" +
                    "ConnectivityMetricsEvent(15:06:29.685, netId=615, WIFI): NetworkEvent(NETWORK_REVALIDATION_SUCCESS, 293022ms) 【有网络】\n" +
                    "~~~~~~~~~~~~~~~~ ConnectivityMetricsEvent End ~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~ getEvents() Begin ~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java\n" +
                    "\n" +
                    "RingBuffer<ConnectivityMetricsEvent> mEventLog = new RingBuffer(ConnectivityMetricsEvent.class, DEFAULT_LOG_SIZE);\n" +
                    "\n" +
                    "   // 把 RingBuffer<Event>  事件转为 List<ConnectivityMetricsEvent>\n" +
                    "\t\t\n" +
                    "   private List<ConnectivityMetricsEvent> getEvents() {  \n" +
                    "            return Arrays.asList(mEventLog.toArray());\n" +
                    "    }\n" +
                    "\n " +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java \n " +
                    "~~~~~~~~~~~~~~~~ getEvents() End ~~~~~~~~~~~~~~~~";

            keyWordList.add(connectivity_1_1);



            KeyWordItem connectivity_2_1 = new KeyWordItem();
            connectivity_2_1.keyword = "dns/connect events:";
            connectivity_2_1.explain="网络度量事件的集合  断网啊 显示无网之类的 ";
            connectivity_2_1.classNameForShuxing = "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java";
            connectivity_2_1.printcode="IpConnectivityMetrics.java   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_2_1.printLogUrl="~~~~~~~~~~~~~~~~~~~ ConnectStats\n" +
                    "public class ConnectStats {\n" +
                    "    private final static int EALREADY     = OsConstants.EALREADY;\n" +
                    "    private final static int EINPROGRESS  = OsConstants.EINPROGRESS;\n" +
                    "\n" +
                    "   \n" +
                    " int netId;  /** Network id of the network associated with the event, or 0 if unspecified. */\n" +
                    "\n" +
                    " long transports;    /** Transports of the network associated with the event, as defined in NetworkCapabilities. */\n" +
                    "\n" +
                    " SparseIntArray errnos = new SparseIntArray();    /** 错误码集合 How many events resulted in a given errno. */\n" +
                    "\t\n" +
                    "    /** Latencies of successful blocking connects. TODO: add non-blocking connects latencies. */\n" +
                    "  IntArray latencies = new IntArray();  // 非阻塞连接延迟\n" +
                    "  \n" +
                    "\n" +
                    "TokenBucket mLatencyTb;    /** TokenBucket for rate limiting latency recording. */\n" +
                    "\n" +
                    "int mMaxLatencyRecords;     /** Maximum number of latency values recorded. */ 最大的延迟记录数\n" +
                    "\n" +
                    "public int eventCount = 0;   /** Total count of events */ 总的事件个数 \n" +
                    "\n" +
                    "public int connectCount = 0;  /** Total count of successful connects. */  // 连接成功的事件个数\n" +
                    "\n" +
                    "// 在阻塞模式下 成功连接的次数\n" +
                    "public int connectBlockingCount = 0;     /** Total count of successful connects done in blocking mode. */\n" +
                    "\n" +
                    "/** Total count of successful connects with IPv6 socket address. */\n" +
                    "public int ipv6ConnectCount = 0;  // 使用 ipv6 连接成功的 次数\n" +
                    "\n" +
                    "\n" +
                    "\t   public String toString() {\n" +
                    "        StringBuilder builder =\n" +
                    "                new StringBuilder(\"ConnectStats(\").append(\"netId=\").append(netId).append(\", \");\n" +
                    "// 【BLUETOOTH, WIFI , CELLULAR, ETHERNET,VPN , WIFI_AWARE ,LOWPAN】 网络类型 \n" +
                    "        for (int t : BitUtils.unpackBits(transports)) {  \t\n" +
                    "            builder.append(NetworkCapabilities.transportNameOf(t)).append(\", \");\n" +
                    "        }\n" +
                    "        builder.append(String.format(\"%d events, \", eventCount));  //  总的事件个数  \n" +
                    "        builder.append(String.format(\"%d success, \", connectCount));   // 连接成功的事件个数\n" +
                    "        builder.append(String.format(\"%d blocking, \", connectBlockingCount));  // 在阻塞模式下 成功连接的次数\n" +
                    "        builder.append(String.format(\"%d IPv6 dst\", ipv6ConnectCount));  // 使用 ipv6 连接成功的 次数\n" +
                    "\t\t// http://man7.org/linux/man-pages/man3/errno.3.html  ENETUNREACH   ECONNREFUSED  ETIMEDOUT ECONNABORTED\n" +
                    "\t\t// 错误码 以及错误提示\n" +
                    "        for (int i = 0; i < errnos.size(); i++) {\n" +
                    "            String errno = OsConstants.errnoName(errnos.keyAt(i));  // 错误码 描述   key是值 \n" +
                    "            int count = errnos.valueAt(i);  // value 是次数  \n" +
                    "            builder.append(String.format(\", %s: %d\", errno, count));\n" +
                    "        }\n" +
                    "        return builder.append(\")\").toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "LOG示例:\t\n" +
                    "dns/connect events:\n" +
                    "ConnectStats(netId=0, 19030 events, 8 success, 0 blocking, 1 IPv6 dst, ENETUNREACH: 2162, ECONNREFUSED: 16860)\n" +
                    "ConnectStats(netId=100, CELLULAR, 1657 events, 1655 success, 9 blocking, 1090 IPv6 dst, ETIMEDOUT: 2)\n" +
                    "ConnectStats(netId=101, CELLULAR, 7 events, 0 success, 0 blocking, 0 IPv6 dst, ENETUNREACH: 7)\n" +
                    "ConnectStats(netId=102, WIFI, 332038 events, 4180 success, 157 blocking, 2896 IPv6 dst, ENETUNREACH: 62, ETIMEDOUT: 125, ECONNREFUSED: 327671)\n" +
                    "ConnectStats(netId=118, CELLULAR, 96 events, 96 success, 2 blocking, 80 IPv6 dst)\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~~~~ NetworkMetrics Begin ~~~~~~~~~~~~~~~~~~~\n" +
                    "// A class accumulating network metrics received from Netd regarding dns queries and connect() calls on a given network.\n" +
                    "/frameworks/base/core/java/android/net/metrics/NetworkMetrics.java\n" +
                    "\n" +
                    "public class NetworkMetrics {\n" +
                    "\n" +
                    "    private static final int INITIAL_DNS_BATCH_SIZE = 100;\n" +
                    "    private static final int CONNECT_LATENCY_MAXIMUM_RECORDS = 20000;\n" +
                    "\n" +
                    "    // The network id of the Android Network.\n" +
                    "    public final int netId;  // 网络的 netid  \n" +
                    "\t\n" +
                    "\t// 【BLUETOOTH, WIFI , CELLULAR, ETHERNET,VPN , WIFI_AWARE ,LOWPAN】 网络类型 \n" +
                    "    // The transport types bitmap of the Android Network, as defined in NetworkCapabilities.java.\n" +
                    "    public final long transports;\n" +
                    "\t\n" +
                    "    // Accumulated metrics for connect events.  //  用于记录网络连接事件\n" +
                    "    public final ConnectStats connectMetrics;   //  NetworkMetrics.connectMetrics  连接事件统计\n" +
                    "\t\n" +
                    "    // Accumulated metrics for dns events.  //  用于记录Dns连接事件\n" +
                    "    public final DnsEvent dnsMetrics;\n" +
                    "\t\n" +
                    "    // Running sums of latencies and error counts for connect and dns events.\n" +
                    "    public final Summary summary;   // 记录统计\n" +
                    "\t\n" +
                    "    // Running sums of the most recent latencies and error counts for connect and dns events.\n" +
                    "    // Starts null until some events are accumulated.\n" +
                    "    // Allows to collect periodic snapshot of the running summaries for a given network.\n" +
                    "    public Summary pendingSummary;  // 记录统计\n" +
                    "\t\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~~~~ NetworkMetrics End ~~~~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetdEventListenerService.java\n" +
                    "\n" +
                    "    // Array of aggregated DNS and connect events sent by netd, grouped by net id. 按netid分组的 聚合DNS和链接事件:\n" +
                    "\t\n" +
                    "   SparseArray<NetworkMetrics> mNetworkMetrics = new SparseArray<>();\n" +
                    "\t\n" +
                    "    public synchronized void list(PrintWriter pw) {\n" +
                    "        pw.println(\"dns/connect events:\");\n" +
                    "        for (int i = 0; i < mNetworkMetrics.size(); i++) {\n" +
                    "\t\t// ConnectStats(netId=0, 19030 events, 8 success, 0 blocking, 1 IPv6 dst, ENETUNREACH: 2162, ECONNREFUSED: 16860)\n" +
                    "\t\t // 打印 ConnectStats \n" +
                    "            pw.println(mNetworkMetrics.valueAt(i).connectMetrics); \n" +
                    "        }\n" +
                    "        for (int i = 0; i < mNetworkMetrics.size(); i++) {\n" +
                    "\t\t// DnsEvent(netId=512, CELLULAR, 757 events, 751 success)\n" +
                    "\t\t// 打印  DNS Log  dnsMetrics  \n" +
                    "            pw.println(mNetworkMetrics.valueAt(i).dnsMetrics);   \n" +
                    "        }\n" +
                    "\t\t.......\n" +
                    "        pw.println(\"\");\n" +
                    "        pw.println(\"network statistics:\");\n" +
                    "        for (NetworkMetricsSnapshot s : getNetworkMetricsSnapshots()) {\n" +
                    "            pw.println(s);\n" +
                    "        }\n" +
                    "        pw.println(\"\");\n" +
                    "        pw.println(\"packet wakeup events:\");\n" +
                    "        for (int i = 0; i < mWakeupStats.size(); i++) {\n" +
                    "            pw.println(mWakeupStats.valueAt(i));\n" +
                    "        }\n" +
                    "        for (WakeupEvent wakeup : mWakeupEvents.toArray()) {\n" +
                    "            pw.println(wakeup);\n" +
                    "        }\n" +
                    "    }\n" +
                    "LOG示例:\n" +
                    "ConnectStats(netId=0, 19030 events, 8 success, 0 blocking, 1 IPv6 dst, ENETUNREACH: 2162, ECONNREFUSED: 16860)\n" +
                    "ConnectStats(netId=100, CELLULAR, 1657 events, 1655 success, 9 blocking, 1090 IPv6 dst, ETIMEDOUT: 2)\n" +
                    "ConnectStats(netId=101, CELLULAR, 7 events, 0 success, 0 blocking, 0 IPv6 dst, ENETUNREACH: 7)\n" +
                    "\n" +
                    "LOG示例:\t\n" +
                    "DnsEvent(netId=512, CELLULAR, 757 events, 751 success)\n" +
                    "DnsEvent(netId=513, WIFI, 4493 events, 4416 success)\n" +
                    "DnsEvent(netId=514, CELLULAR, 3 events, 3 success)\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java\n" +
                    "\n" +
                    "    // Subservice listening to Netd events via INetdEventListener.aidl.\n" +
                    "    NetdEventListenerService mNetdListener;\n" +
                    "\t\n" +
                    "   private void cmdList(PrintWriter pw) {\n" +
                    "..........\n" +
                    "        pw.println(\"\");\n" +
                    "        if (mNetdListener != null) {\n" +
                    "            mNetdListener.list(pw);   // 调用 NetdEventListenerService 的 list方法 \n" +
                    "        }\n" +
                    "        pw.println(\"\");\n" +
                    "        mDefaultNetworkMetrics.listEvents(pw);\n" +
                    "    }\n" +
                    "\t\t\n" +
                    "LOG示例:\t\n" +
                    "dns/connect events:\n" +
                    "ConnectStats(netId=0, 19030 events, 8 success, 0 blocking, 1 IPv6 dst, ENETUNREACH: 2162, ECONNREFUSED: 16860)\n" +
                    "ConnectStats(netId=100, CELLULAR, 1657 events, 1655 success, 9 blocking, 1090 IPv6 dst, ETIMEDOUT: 2)\n";

            keyWordList.add(connectivity_2_1);



            KeyWordItem connectivity_3_1 = new KeyWordItem();
            connectivity_3_1.keyword = "DnsEvent(netId=0";
            connectivity_3_1.explain="dns events 相关的Log  ";
            connectivity_3_1.classNameForShuxing = "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java";
            connectivity_3_1.printcode="IpConnectivityMetrics.java   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetdEventListenerService.java ->  void list(PrintWriter pw) ";

            connectivity_3_1.printLogUrl="~~~~~~~~~~~~~~~~~~~~~~~~~ DnsEvent Begin ~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "// A batch of DNS events recorded by NetdEventListenerService for a specific network.\n" +
                    "class DnsEvent {\n" +
                    "\n" +
                    "\n" +
                    "    // Network id of the network associated with the event, or 0 if unspecified.\n" +
                    "    public final int netId;\n" +
                    "\t \n" +
                    "    public final long transports; \t// 【BLUETOOTH, WIFI , CELLULAR, ETHERNET,VPN , WIFI_AWARE ,LOWPAN】 网络类型 \n" +
                    "\t\n" +
                    "    // The number of DNS queries recorded. Queries are stored in the structure-of-array style where\n" +
                    "    // the eventTypes, returnCodes, and latenciesMs arrays have the same length and the i-th event\n" +
                    "    // is spread across the three array at position i.\n" +
                    "    public int eventCount;  // DNS 查询次数 \n" +
                    "\t\n" +
                    "    // The number of successful DNS queries recorded.\n" +
                    "    public int successCount;   // DNS 查询成功的次数\n" +
                    "\t\n" +
                    "    // The types of DNS queries as defined in INetdEventListener.\n" +
                    "    public byte[] eventTypes;  // Dns 查询类型? \n" +
                    "\t\n" +
                    "    // Current getaddrinfo codes go from 1 to EAI_MAX = 15. gethostbyname returns errno, but there\n" +
                    "    // are fewer than 255 errno values. So we store the result code in a byte as well.\n" +
                    "    public byte[] returnCodes; 返回的 returnCode 的 集合   一个字节存储返回的结果\n" +
                    "\t\n" +
                    "    // Latencies in milliseconds of queries, stored as ints.\n" +
                    "    public int[] latenciesMs;  // 延迟时间数组   毫秒 \n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        StringBuilder builder =\n" +
                    "                new StringBuilder(\"DnsEvent(\").append(\"netId=\").append(netId).append(\", \");\n" +
                    "        for (int t : BitUtils.unpackBits(transports)) {\n" +
                    "            builder.append(NetworkCapabilities.transportNameOf(t)).append(\", \");\n" +
                    "        }\n" +
                    "        builder.append(String.format(\"%d events, \", eventCount)); // 总共发起 4493 起DNS查询   成功了 4416次 \n" +
                    "        builder.append(String.format(\"%d success)\", successCount));\n" +
                    "        return builder.toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "LOG示例:\t\n" +
                    "DnsEvent(netId=512, CELLULAR, 757 events, 751 success)  \n" +
                    "DnsEvent(netId=513, WIFI, 4493 events, 4416 success) // 总共发起 4493 起DNS查询   成功了 4416次 \n" +
                    "DnsEvent(netId=514, CELLULAR, 3 events, 3 success)\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~  DnsEvent End  ~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~ NetworkMetrics.DnsMetrics Begin \n" +
                    "public class NetworkMetrics {\n" +
                    "\n" +
                    "    // Accumulated metrics for dns events.\n" +
                    "    public final DnsEvent dnsMetrics;\n" +
                    "}\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~   NetworkMetrics.DnsMetrics End \n" +
                    "\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetdEventListenerService.java\n" +
                    "\n" +
                    "    // Array of aggregated DNS and connect events sent by netd, grouped by net id. 按netid分组的 聚合DNS和链接事件:\n" +
                    "\t\n" +
                    "   SparseArray<NetworkMetrics> mNetworkMetrics = new SparseArray<>();\n" +
                    "\t\n" +
                    "    public synchronized void list(PrintWriter pw) { \n" +
                    "        pw.println(\"dns/connect events:\");\n" +
                    "        for (int i = 0; i < mNetworkMetrics.size(); i++) {\n" +
                    "\t\t// ConnectStats(netId=0, 19030 events, 8 success, 0 blocking, 1 IPv6 dst, ENETUNREACH: 2162, ECONNREFUSED: 16860)\n" +
                    "\t\t // 打印 ConnectStats \n" +
                    "            pw.println(mNetworkMetrics.valueAt(i).connectMetrics); \n" +
                    "        }\n" +
                    "        for (int i = 0; i < mNetworkMetrics.size(); i++) {\n" +
                    "\t\t// 打印  DNS Log  dnsMetrics  \n" +
                    "\t\t // 【 打印 DNS  DnsEvent(netId=512, CELLULAR, 757 events, 751 success) 】  【1 】 \n" +
                    "            pw.println(mNetworkMetrics.valueAt(i).dnsMetrics);    \n" +
                    "        }\n" +
                    "\t\t.......\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "\t/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java\n" +
                    "\n" +
                    "    // Subservice listening to Netd events via INetdEventListener.aidl.\n" +
                    "    NetdEventListenerService mNetdListener;\n" +
                    "\t\n" +
                    "   private void cmdList(PrintWriter pw) {\n" +
                    "..........\n" +
                    "        pw.println(\"\");\n" +
                    "        if (mNetdListener != null) {\n" +
                    "            mNetdListener.list(pw);  【0】  // 调用 NetdEventListenerService 的 list方法 \n" +
                    "        }\n" +
                    "        pw.println(\"\");\n" +
                    "        mDefaultNetworkMetrics.listEvents(pw);\n" +
                    "    }\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "LOG示例:\t\n" +
                    "DnsEvent(netId=512, CELLULAR, 757 events, 751 success)\n" +
                    "DnsEvent(netId=513, WIFI, 4493 events, 4416 success)\n" +
                    "DnsEvent(netId=514, CELLULAR, 3 events, 3 success)";

            keyWordList.add(connectivity_3_1);


            KeyWordItem connectivity_4_1 = new KeyWordItem();
            connectivity_4_1.keyword = "network statistics:";
            connectivity_4_1.explain="网络统计 ";
            connectivity_4_1.classNameForShuxing = "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java";
            connectivity_4_1.printcode="IpConnectivityMetrics.java   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetdEventListenerService.java ->  void list(PrintWriter pw) ";

            connectivity_4_1.printLogUrl="~~~~~~~~~~~~~~~ NetworkMetrics.Summary  Begin ~~~~~~~~~~~~~\n" +
                    "/frameworks/base/core/java/android/net/metrics/NetworkMetrics.java\n" +
                    "\n" +
                    "/** Represents running sums for dns and connect average error counts and average latencies. */\n" +
                    "public static class Summary {\n" +
                    "\n" +
                    "\n" +
                    "public final int netId;  // 网络ID \n" +
                    "\n" +
                    "public final long transports;  // 【BLUETOOTH, WIFI , CELLULAR, ETHERNET,VPN , WIFI_AWARE ,LOWPAN】 网络类型  \n" +
                    "\n" +
                    "// DNS latencies measured in milliseconds.\n" +
                    "public final Metrics dnsLatencies = new Metrics();  // DNS 延迟时间 \n" +
                    "\n" +
                    "// DNS error rate measured in percentage points.\n" +
                    "public final Metrics dnsErrorRate = new Metrics();  // DNS 错误百分比\n" +
                    "\n" +
                    "// Blocking connect latencies measured in milliseconds. // DNS 延迟时间  阻塞连接延迟\n" +
                    "public final Metrics connectLatencies = new Metrics();\n" +
                    "\n" +
                    "// Blocking and non blocking connect error rate measured in percentage points.\n" +
                    "public final Metrics connectErrorRate = new Metrics();  // 连接错误率 百分比\n" +
                    "\n" +
                    "// TCP socket packet loss stats collected from Netlink sock_diag.\n" +
                    "public final Metrics tcpLossRate = new Metrics();  // TCP丢包信息\n" +
                    "\n" +
                    "// TCP averaged microsecond round-trip-time stats collected from Netlink sock_diag.\n" +
                    "public final Metrics roundTripTimeUs = new Metrics();  // 往返时间统计信息\n" +
                    "\n" +
                    "// TCP stats collected from Netlink sock_diag that averages millisecond per-socket\n" +
                    "// differences between last packet sent timestamp and last ack received timestamp.\n" +
                    "public final Metrics sentAckTimeDiffenceMs = new Metrics();  // 每个帧的平均响应时间 \n" +
                    "\t\t\n" +
                    "\n" +
                    "static class Metrics {\n" +
                    "public double sum;  // 所有的数值之和 \n" +
                    "public double max = Double.MIN_VALUE;  // 数组中的最大值\n" +
                    "public int count;  // 数值的个数 \n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "public String toString() {\n" +
                    "StringJoiner j = new StringJoiner(\", \", \"{\", \"}\");\n" +
                    "j.add(\"netId=\" + netId);  // netid 网络ID\n" +
                    "for (int t : BitUtils.unpackBits(transports)) {\n" +
                    "    j.add(NetworkCapabilities.transportNameOf(t));  // 网络类型 \n" +
                    "}\n" +
                    "// dns avg=    // DNS 平均DNS时间  【dns avg=77ms max=334ms err=1.1% tot=87】\n" +
                    "// max=  DNS 耗时最长的记录值\n" +
                    "// err=  DNS 错误百分比      \n" +
                    "// tot = DNS 错误的次数\n" +
                    "  j.add(String.format(\"dns avg=%dms max=%dms err=%.1f%% tot=%d\",(int) dnsLatencies.average(), (int) dnsLatencies.max,100 * dnsErrorRate.average(), dnsErrorRate.count));\n" +
                    "\n" +
                    "// connect avg= 平均连接延迟时间      【connect avg=0ms max=1ms err=99.7% tot=65701】\n" +
                    "// max= 连接延迟数据中最大的那个数值\n" +
                    "// err=  连接失败的百分比数据\n" +
                    "// tot=  连接失败的次数\n" +
                    "  j.add(String.format(\"connect avg=%dms max=%dms err=%.1f%% tot=%d\", (int) connectLatencies.average(), (int) connectLatencies.max,100 * connectErrorRate.average(), connectErrorRate.count));\n" +
                    "\n" +
                    "// tcp avg_loss=    TCP的平均丢包率  【 tcp avg_loss=4.7% total_sent=985 total_lost=46 】\n" +
                    "// total_sent =   TCP总共发出的包 \n" +
                    "// total_lost    TCP总共丢失的包\n" +
                    "  j.add(String.format(\"tcp avg_loss=%.1f%% total_sent=%d total_lost=%d\", 100 * tcpLossRate.average(), tcpLossRate.count, (int) tcpLossRate.sum));\n" +
                    "\n" +
                    "// tcp rtt=  TCP往返平均时间         【tcp rtt=209ms, tcp sent-ack_diff=3209ms 】\n" +
                    "// tcp sent-ack_diff=    每个帧的平均响应时间  \n" +
                    "  j.add(String.format(\"tcp rtt=%dms\", (int) (roundTripTimeUs.average() / 1000)));\n" +
                    "  j.add(String.format(\"tcp sent-ack_diff=%dms\", (int) sentAckTimeDiffenceMs.average()));\n" +
                    "    return j.toString();\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "11:45:00.000: {netId=600, CELLULAR, dns avg=77ms max=334ms err=1.1% tot=87, connect avg=0ms max=1ms err=99.7% tot=65701, tcp avg_loss=4.7% total_sent=985 total_lost=46, tcp rtt=55ms, tcp sent-ack_diff=-92ms}, {netId=603, WIFI, dns avg=30ms max=169ms err=10.7% tot=28, connect avg=0ms max=1ms err=30.0% tot=40, tcp avg_loss=4.6% total_sent=477 total_lost=22, tcp rtt=35ms, tcp sent-ack_diff=-502ms}, {netId=604, WIFI, dns avg=39ms max=56ms err=66.7% tot=3, connect avg=0ms max=0ms err=0.0% tot=0, tcp avg_loss=25.0% total_sent=16 total_lost=4, tcp rtt=0ms, tcp sent-ack_diff=0ms}\n" +
                    "11:50:00.000: {netId=600, CELLULAR, dns avg=26328ms max=87583ms err=43.5% tot=62, connect avg=0ms max=5ms err=0.0% tot=70, tcp avg_loss=11.7% total_sent=358 total_lost=42, tcp rtt=209ms, tcp sent-ack_diff=3209ms}, {netId=604, WIFI, dns avg=151ms max=5158ms err=36.2% tot=282, connect avg=0ms max=8ms err=99.5% tot=65910, tcp avg_loss=0.9% total_sent=4737 total_lost=45, tcp rtt=37ms, tcp sent-ack_diff=-364ms}\n" +
                    "11:55:00.000: {netId=604, WIFI, dns avg=102ms max=3879ms err=3.7% tot=54, connect avg=0ms max=1ms err=3.4% tot=88, tcp avg_loss=3.5% total_sent=1030 total_lost=36, tcp rtt=36ms, tcp sent-ack_diff=-9934ms}\n" +
                    "12:00:00.000: {netId=604, WIFI, dns avg=46ms max=520ms err=2.1% tot=97, connect avg=0ms max=8ms err=1.6% tot=311, tcp avg_loss=2.7% total_sent=1634 total_lost=44, tcp rtt=44ms, tcp sent-ack_diff=-6783ms}\n" +
                    "12:05:00.000: {netId=0, dns avg=0ms max=5ms err=100.0% tot=44, connect avg=0ms max=0ms err=100.0% tot=170, tcp avg_loss=0.0% total_sent=0 total_lost=0, tcp rtt=0ms, tcp sent-ack_diff=0ms}, {netId=604, WIFI, dns avg=175ms max=5062ms err=4.9% tot=41, connect avg=0ms max=1ms err=4.5% tot=89, tcp avg_loss=1.0% total_sent=1293 total_lost=13, tcp rtt=64ms, tcp sent-ack_diff=-217ms}\n" +
                    "12:10:00.000: {netId=0, dns avg=1ms max=3ms err=100.0% tot=11, connect avg=0ms max=0ms err=0.0% tot=0, tcp avg_loss=0.0% total_sent=0 total_lost=0, tcp rtt=0ms, tcp sent-ack_diff=0ms}, {netId=606, CELLULAR, dns avg=50ms max=131ms err=0.0% tot=40, connect avg=0ms max=2ms err=99.8% tot=59928, tcp avg_loss=6.7% total_sent=375 total_lost=25, tcp rtt=23ms, tcp sent-ack_diff=-27ms}\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~ NetworkMetrics.Summary  End ~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~ NetworkMetricsSnapshot Begin  ~~~~~~~~~~~~~\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetdEventListenerService.java\n" +
                    "\n" +
                    "\n" +
                    "/** Helper class for buffering summaries of NetworkMetrics at regular time intervals */ 固定间隙收集的网络信息\n" +
                    "static class NetworkMetricsSnapshot {\n" +
                    "\n" +
                    "public long timeMs;\n" +
                    "public List<NetworkMetrics.Summary> stats = new ArrayList<>();\n" +
                    "\n" +
                    "        @Override\n" +
                    "        public String toString() {\n" +
                    "            StringJoiner j = new StringJoiner(\", \");\n" +
                    "            for (NetworkMetrics.Summary s : stats) {\n" +
                    "                j.add(s.toString());  // 添加一次 NetworkMetricsSnapshot 快照的 所有 NetworkMetrics.Summary \n" +
                    "            }\n" +
                    "\t\t\t// %tT.%tL  %tT标识-时:分:秒   %tL标识 毫秒\n" +
                    "            return String.format(\"%tT.%tL: %s\", timeMs, timeMs, j.toString());\n" +
                    "        }\n" +
                    "\n" +
                    "\t}\n" +
                    "LOG示例:\n" +
                    "11:45:00.000: {netId=600, CELLULAR, dns avg=77ms max=334ms err=1.1% tot=87, connect avg=0ms max=1ms err=99.7% tot=65701, tcp avg_loss=4.7% total_sent=985 total_lost=46, tcp rtt=55ms, tcp sent-ack_diff=-92ms}, {netId=603, WIFI, dns avg=30ms max=169ms err=10.7% tot=28, connect avg=0ms max=1ms err=30.0% tot=40, tcp avg_loss=4.6% total_sent=477 total_lost=22, tcp rtt=35ms, tcp sent-ack_diff=-502ms}, {netId=604, WIFI, dns avg=39ms max=56ms err=66.7% tot=3, connect avg=0ms max=0ms err=0.0% tot=0, tcp avg_loss=25.0% total_sent=16 total_lost=4, tcp rtt=0ms, tcp sent-ack_diff=0ms}\n" +
                    "11:50:00.000: {netId=600, CELLULAR, dns avg=26328ms max=87583ms err=43.5% tot=62, connect avg=0ms max=5ms err=0.0% tot=70, tcp avg_loss=11.7% total_sent=358 total_lost=42, tcp rtt=209ms, tcp sent-ack_diff=3209ms}, {netId=604, WIFI, dns avg=151ms max=5158ms err=36.2% tot=282, connect avg=0ms max=8ms err=99.5% tot=65910, tcp avg_loss=0.9% total_sent=4737 total_lost=45, tcp rtt=37ms, tcp sent-ack_diff=-364ms}\n" +
                    "11:55:00.000: {netId=604, WIFI, dns avg=102ms max=3879ms err=3.7% tot=54, connect avg=0ms max=1ms err=3.4% tot=88, tcp avg_loss=3.5% total_sent=1030 total_lost=36, tcp rtt=36ms, tcp sent-ack_diff=-9934ms}\n" +
                    "12:00:00.000: {netId=604, WIFI, dns avg=46ms max=520ms err=2.1% tot=97, connect avg=0ms max=8ms err=1.6% tot=311, tcp avg_loss=2.7% total_sent=1634 total_lost=44, tcp rtt=44ms, tcp sent-ack_diff=-6783ms}\n" +
                    "12:05:00.000: {netId=0, dns avg=0ms max=5ms err=100.0% tot=44, connect avg=0ms max=0ms err=100.0% tot=170, tcp avg_loss=0.0% total_sent=0 total_lost=0, tcp rtt=0ms, tcp sent-ack_diff=0ms}, {netId=604, WIFI, dns avg=175ms max=5062ms err=4.9% tot=41, connect avg=0ms max=1ms err=4.5% tot=89, tcp avg_loss=1.0% total_sent=1293 total_lost=13, tcp rtt=64ms, tcp sent-ack_diff=-217ms}\n" +
                    "12:10:00.000: {netId=0, dns avg=1ms max=3ms err=100.0% tot=11, connect avg=0ms max=0ms err=0.0% tot=0, tcp avg_loss=0.0% total_sent=0 total_lost=0, tcp rtt=0ms, tcp sent-ack_diff=0ms}, {netId=606, CELLULAR, dns avg=50ms max=131ms err=0.0% tot=40, connect avg=0ms max=2ms err=99.8% tot=59928, tcp avg_loss=6.7% total_sent=375 total_lost=25, tcp rtt=23ms, tcp sent-ack_diff=-27ms}\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~ NetworkMetricsSnapshot End  ~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~ NetdEventListenerService.java Begin ~~~~~~~~~~~~~\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetdEventListenerService.java\n" +
                    "\n" +
                    " int METRICS_SNAPSHOT_BUFFER_SIZE = 48; // 4 hours  快照的容量 \n" +
                    " \n" +
                    "long METRICS_SNAPSHOT_SPAN_MS = 5 * DateUtils.MINUTE_IN_MILLIS;  // 300000  毫秒\n" +
                    "\n" +
                    "long mLastSnapshot = 0;  // 快照时间点\n" +
                    "\n" +
                    "RingBuffer<NetworkMetricsSnapshot> mNetworkMetricsSnapshots = new RingBuffer<>(NetworkMetricsSnapshot.class, METRICS_SNAPSHOT_BUFFER_SIZE);\n" +
                    "\t\t\n" +
                    "    private void collectPendingMetricsSnapshot(long timeMs) {\n" +
                    "        // Detects time differences larger than the snapshot collection period.\n" +
                    "        // This is robust against clock jumps and long inactivity periods.\n" +
                    "        if (Math.abs(timeMs - mLastSnapshot) <= METRICS_SNAPSHOT_SPAN_MS) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        mLastSnapshot = projectSnapshotTime(timeMs);\n" +
                    "        NetworkMetricsSnapshot snapshot = NetworkMetricsSnapshot.collect(mLastSnapshot, mNetworkMetrics);\n" +
                    "        if (snapshot.stats.isEmpty()) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        mNetworkMetricsSnapshots.append(snapshot);  // 快照放入 \n" +
                    "    }\n" +
                    "\t\n" +
                    "\t\n" +
                    "    private NetworkMetricsSnapshot[] getNetworkMetricsSnapshots() {\n" +
                    "        collectPendingMetricsSnapshot(System.currentTimeMillis());\n" +
                    "        return mNetworkMetricsSnapshots.toArray();\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "\t\n" +
                    "    public synchronized void list(PrintWriter pw) {\n" +
                    "        pw.println(\"dns/connect events:\");\n" +
                    "        for (int i = 0; i < mNetworkMetrics.size(); i++) {\n" +
                    "            pw.println(mNetworkMetrics.valueAt(i).connectMetrics);\n" +
                    "        }\n" +
                    "        for (int i = 0; i < mNetworkMetrics.size(); i++) {\n" +
                    "            pw.println(mNetworkMetrics.valueAt(i).dnsMetrics);\n" +
                    "        }\n" +
                    "        pw.println(\"\");\n" +
                    "        pw.println(\"network statistics:\"); \n" +
                    "        for (NetworkMetricsSnapshot s : getNetworkMetricsSnapshots()) {\n" +
                    "            pw.println(s); // 打印快照   【! 这里打印2 !】\n" +
                    "        }\n" +
                    "        pw.println(\"\");\n" +
                    "        pw.println(\"packet wakeup events:\");\n" +
                    "        for (int i = 0; i < mWakeupStats.size(); i++) {\n" +
                    "            pw.println(mWakeupStats.valueAt(i));\n" +
                    "        }\n" +
                    "        for (WakeupEvent wakeup : mWakeupEvents.toArray()) {\n" +
                    "            pw.println(wakeup);\n" +
                    "        }\n" +
                    "    }\n" +
                    "~~~~~~~~~~~~~ NetdEventListenerService.java End ~~~~~~~~~~~~~\n" +
                    "\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java\n" +
                    "\n" +
                    "// Subservice listening to Netd events via INetdEventListener.aidl.\n" +
                    "NetdEventListenerService mNetdListener;\n" +
                    "\n" +
                    "private void cmdList(PrintWriter pw) {\n" +
                    "..........\n" +
                    "\tpw.println(\"\");\n" +
                    "\tif (mNetdListener != null) {\n" +
                    "\t\tmNetdListener.list(pw);   // 调用 NetdEventListenerService 的 list方法 【! 这里打印1 !】\n" +
                    "\t}\n" +
                    "\tpw.println(\"\");\n" +
                    "\tmDefaultNetworkMetrics.listEvents(pw);\n" +
                    "}";

            keyWordList.add(connectivity_4_1);

            KeyWordItem connectivity_5_1 = new KeyWordItem();
            connectivity_5_1.keyword = "default network events:";
            connectivity_5_1.explain="默认网络事件 ";
            connectivity_5_1.classNameForShuxing = "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java";
            connectivity_5_1.printcode="IpConnectivityMetrics.java   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}\n" +"/frameworks/base/services/core/java/com/android/server/connectivity/NetdEventListenerService.java ->  void list(PrintWriter pw) ";
            connectivity_5_1.printLogUrl="~~~~~~~~~~~~~~~~~~~~~~~~~~~  计算公式 Begin ~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "1.最后一条是当前的默认网络    \n" +
                    "  当前默认网络的起始时间 +  当前记录的持续时间 = 现今的时间点(now| 用户执行打印Log的时间点)\n" +
                    "2.最后一条默认网络的的起始时间  不变  但是  持续时间一直的增加\n" +
                    "  其余记录条的起始时间是每次调用变化的， 而持续时间不变\n" +
                    "3.本地的时间   -  当前网络事件持续的时间  =  开始时间  (由于本地时间一直在变  所以也导致了 开始时间在变)\n" +
                    "4.事件起始时间 -  事件结束时间 = 当前网络事件持续的时间   ( 怎么求 开始时间 和 结束时间呢? )】\n" +
                    "5.     本地记录Log的时间点     = 最后一条记录的开始时间 + 最后一条记录的持续时间\n" +
                    "例如: \n" +
                    "[1].14:54:14.007: DefaultNetworkEvent(netId=110, WIFI, ip=IPv4, initial_score=20, final_score=20, duration=432s, validation=00.0%)\n" +
                    "[2].15:01:20.042: DefaultNetworkEvent(netId=0, ip=NONE, duration=6s, validation=00.0%)\n" +
                    "[3].14:54:30.607: DefaultNetworkEvent(netId=111, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=415s, validation=100.0%)\n" +
                    "[4].22:03:03.494: DefaultNetworkEvent(netId=0, ip=NONE, duration=61103s, validation=00.0%)\n" +
                    "[5].10:42:33.444: DefaultNetworkEvent(netId=113, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=15533s, validation=100.0%)\n" +
                    "[6].14:30:38.532: DefaultNetworkEvent(netId=0, ip=NONE, duration=1847s, validation=00.0%)\n" +
                    "\n" +
                    "(LOG时间点|now)  = 14:30:38.532+1847s    2020年4月27日15时1分25秒   打印的Log   15:01:25.532 本地记录Log的时间点 系统本地时间戳\n" +
                    "本地时间戳时间 - 记录持续的时间  =   打印的时间( 这狗  尼玛错了啊 )\n" +
                    "[1] 15:01:25.532-432s   =  2020年4月27日14时54分13秒    14:54:13   约等于 14:54:14\n" +
                    "[2] 15:01:25.532-6s     =  2020年4月27日15时1分19秒     15:01:19   约等于 15:01:20\n" +
                    "[3] 15:01:25.532-415s   =  2020年4月27日14时54分30秒    14:54:30   约等于 14:54:30\n" +
                    "[4] 15:01:25.532-61103s =  2020年4月26日22时3分2秒      22:03:02   约等于 22:03:03\n" +
                    "[5] 15:01:25.532-15533s =  2020年4月27日10时42分32秒    10:42:32   约等于 10:42:33\n" +
                    "\n" +
                    "\n" +
                    "另一种计算:   当前记录时间 + 当前持续时间  = 一个定值的时间戳  ( 这狗  尼玛错了啊 )\n" +
                    "[1]  14:54:14.007+432s   = 2020-04-27_15:01:26.007\n" +
                    "[2]  15:01:20.042+6s     = 2020-04-27_15:01:26.042\n" +
                    "[3]  14:54:30.607+415s   = 2020-04-27_15:01:25.607\n" +
                    "[4]  22:03:03.494+61103s = 2020-04-28_15:01:26.494\n" +
                    "[5]  10:42:33.444+15533s = 2020-04-27_15:01:26.444\n" +
                    "[6]  14:30:38.532+1847s  = 2020-04-27_15:01:25.532\n" +
                    "\n" +
                    "\n" +
                    "尝试:【貌似可以】  用当前的时间戳 减去  从低往上的持续时间的和  是否能等于当前 事件发生的 起始时间戳 和 结束时间戳\n" +
                    "[9]16:06:09.562: DefaultNetworkEvent(netId=0, ip=NONE, duration=6s, validation=00.0%)\n" +
                    "[8]15:59:20.127: DefaultNetworkEvent(netId=111, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=415s, validation=100.0%)\n" +
                    "[7]23:07:53.014: DefaultNetworkEvent(netId=0, ip=NONE, duration=61103s, validation=00.0%)\n" +
                    "[6]11:47:22.964: DefaultNetworkEvent(netId=113, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=15533s, validation=100.0%)\n" +
                    "[5]14:34:47.158: DefaultNetworkEvent(netId=0, ip=NONE, duration=5488s, validation=00.0%)\n" +
                    "[4]16:04:57.335: DefaultNetworkEvent(netId=115, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=78s, validation=99.8%)\n" +
                    "[3]16:05:37.560: DefaultNetworkEvent(netId=0, ip=NONE, duration=38s, validation=00.0%)\n" +
                    "[2]16:04:16.960: DefaultNetworkEvent(netId=116, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=119s, validation=99.9%)\n" +
                    "[1]16:06:01.624: DefaultNetworkEvent(netId=0, ip=NONE, duration=14s, validation=00.0%)\n" +
                    "\n" +
                    "16:04:04 左右打开WIIF     16:06:04 左右关闭WIIF   \n" +
                    "1.计算时间戳:    16:06:01.624+14s   = 2020-04-27_16:06:15.624\n" +
                    "2.【错误】计算[2]的起始时间:   2020-04-27_16:06:15.624-(14s+119s) =  2020-04-27_16:06:15.624-233s =  2020-04-27_16:02:21.376[XX]\n" +
                    "2. 把最后一个记录的时间戳[1]当做[2]的结束时间戳   那么计算 [2的开始时间戳]\n" +
                    "\n" +
                    "n结束时间戳 =  (n+1)开始时间戳\n" +
                    "n开始时间戳 =  n结束时间戳-n持续时间\n" +
                    "\t\n" +
                    "[1] 结束时间戳                              \n" +
                    "[1] 开始时间戳                                              16:06:01.624    【无网络】\n" +
                    "\n" +
                    "[2] 结束时间戳                                   2020-04-27_16:06:01.624   【连接上WIFI网络】\n" +
                    "[2] 开始时间戳   16:06:01.624-119s             = 2020-04-27_16:04:01.376\n" +
                    "\n" +
                    "[3] 结束时间戳                                   2020-04-27_16:04:01.376     【无网络】\n" +
                    "[3] 开始时间戳   2020-04-27_16:04:01.376-38s   = 2020-04-27_16:03:22.624  \n" +
                    "\n" +
                    "[4] 结束时间戳                                   2020-04-27_16:03:22.624      【连接上WIFI网络】\n" +
                    "[4] 开始时间戳   2020-04-27_16:03:22.624-78s   = 2020-04-27_16:02:03.376\n" +
                    "\n" +
                    "[5] 结束时间戳                                   2020-04-27_16:02:03.376     【无网络】\n" +
                    "[5] 开始时间戳   2020-04-27_16:02:03.376-5488s = 2020-04-27_14:30:34.624\n" +
                    "  \n" +
                    "[6] 结束时间戳                                   2020-04-27_14:30:34.624     【连接上WIFI网络】\n" +
                    "[6] 开始时间戳   2020-04-27_14:30:34.624-15533s= 2020-04-27_10:11:40.376\n" +
                    "\n" +
                    "[7] 结束时间戳                                   2020-04-27_10:11:40.376       【无网络】\n" +
                    "[7] 开始时间戳   2020-04-27_10:11:40.376-61103s= 2020-04-26_17:13:16.624\n" +
                    "\n" +
                    "[8] 结束时间戳                                   2020-04-26_17:13:16.624      【连接上WIFI网络】\n" +
                    "[8] 开始时间戳   2020-04-26_17:13:16.624-415s=   2020-04-26_17:06:20.376\n" +
                    "\n" +
                    "[9] 结束时间戳                                   2020-04-26_17:06:20.376       【无网络】\n" +
                    "[9] 开始时间戳   2020-04-26_17:06:20.376-6s=     2020-04-26_17:06:13.624\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~  同一台机器   验证2 ~~~~~~~~~~~~~~~~~~~~~~~~~~~  相差1秒误差  所以该方法能正确打印\n" +
                    "\n" +
                    "[20]16:31:26.604: DefaultNetworkEvent(netId=0, ip=NONE, duration=1s, validation=00.0%)\n" +
                    "[19]16:21:35.469: DefaultNetworkEvent(netId=106, WIFI, ip=IPv4, initial_score=20, final_score=20, duration=593s, validation=00.0%)\n" +
                    "[18]16:31:26.659: DefaultNetworkEvent(netId=0, ip=NONE, duration=1s, validation=00.0%)\n" +
                    "[17]16:31:08.010: DefaultNetworkEvent(netId=107, WIFI, ip=IPv4, initial_score=20, final_score=20, duration=20s, validation=00.0%)\n" +
                    "[16]16:31:26.550: DefaultNetworkEvent(netId=0, ip=NONE, duration=2s, validation=00.0%)\n" +
                    "[15]18:44:25.769: DefaultNetworkEvent(netId=108, WIFI, ip=IPv4, initial_score=20, final_score=20, duration=164822s, validation=00.0%)\n" +
                    "[14]16:31:25.232: DefaultNetworkEvent(netId=0, ip=NONE, duration=3s, validation=00.0%)\n" +
                    "[13]16:30:25.980: DefaultNetworkEvent(netId=109, WIFI, ip=IPv4, initial_score=20, final_score=20, duration=62s, validation=00.0%)\n" +
                    "[12]16:31:26.536: DefaultNetworkEvent(netId=0, ip=NONE, duration=2s, validation=00.0%)\n" +
                    "[11]16:24:16.088: DefaultNetworkEvent(netId=110, WIFI, ip=IPv4, initial_score=20, final_score=20, duration=432s, validation=00.0%)\n" +
                    "[10]16:31:22.123: DefaultNetworkEvent(netId=0, ip=NONE, duration=6s, validation=00.0%)\n" +
                    "[9]16:24:32.688: DefaultNetworkEvent(netId=111, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=415s, validation=100.0%)\n" +
                    "[8]23:33:05.575: DefaultNetworkEvent(netId=0, ip=NONE, duration=61103s, validation=00.0%)\n" +
                    "[7]12:12:35.525: DefaultNetworkEvent(netId=113, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=15533s, validation=100.0%)\n" +
                    "[6]14:59:59.719: DefaultNetworkEvent(netId=0, ip=NONE, duration=5488s, validation=00.0%)\n" +
                    "[5]16:30:09.896: DefaultNetworkEvent(netId=115, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=78s, validation=99.8%)\n" +
                    "[4]16:30:50.121: DefaultNetworkEvent(netId=0, ip=NONE, duration=38s, validation=00.0%)\n" +
                    "[3]16:29:29.521: DefaultNetworkEvent(netId=116, WIFI, ip=IPv4, initial_score=20, final_score=60, duration=119s, validation=99.9%)\n" +
                    "[2]16:06:54.962: DefaultNetworkEvent(netId=0, ip=NONE, duration=1473s, validation=00.0%)\n" +
                    "[1]16:30:34.749: DefaultNetworkEvent(netId=117, WIFI, ip=IPv4, initial_score=20, duration=53s, validation=99.7%)\n" +
                    "\n" +
                    "[1] 结束时间戳 \n" +
                    "[1] 开始时间戳                                             16:30:34.749     【连接上WIFI网络】\n" +
                    "\n" +
                    "[2] 结束时间戳                                  2020-04-27_16:30:34.749     【无网络】\n" +
                    "[2] 开始时间戳   16:30:34.749-1473s           = 2020-04-27_16:06:00.251\n" +
                    "\n" +
                    "[3] 结束时间戳                                  2020-04-27_16:06:00.251     【连接上WIFI网络】\n" +
                    "[3] 开始时间戳   16:06:00.251-119s            = 2020-04-27_16:04:00.749\n" +
                    "\n" +
                    "[4] 结束时间戳                                  2020-04-27_16:04:00.749     【无网络】\n" +
                    "[4] 开始时间戳   16:04:00.749-38s            =  2020-04-27_16:03:21.251     \n" +
                    "\n" +
                    "[5] 结束时间戳                                  2020-04-27_16:03:21.251     【连接上WIFI网络】\n" +
                    "[5] 开始时间戳   16:03:21.251-78s            =  2020-04-27_16:02:02.749       \n" +
                    "\n" +
                    "[6] 结束时间戳                                  2020-04-27_16:02:02.749      【无网络】\n" +
                    "[6] 开始时间戳   16:02:02.749-5488s          =  2020-04-27_14:30:33.251\n" +
                    "\n" +
                    "[7] 结束时间戳                                  2020-04-27_14:30:33.251     【连接上WIFI网络】\n" +
                    "[7] 开始时间戳   14:30:33.251-15533s         =  2020-04-27_10:11:39.749 \n" +
                    "\n" +
                    "[8] 结束时间戳                                  2020-04-27_10:11:39.749     【无网络】\n" +
                    "[8] 开始时间戳   10:11:39.749-61103s         =  2020-04-26_17:13:15.251     \n" +
                    "\n" +
                    "[9] 结束时间戳                                  2020-04-26_17:13:15.251     【连接上WIFI网络】\n" +
                    "[9] 开始时间戳   17:13:15.251-415s         =    2020-04-27_17:06:19.749    \n" +
                    "\n" +
                    "[10] 结束时间戳                                  2020-04-27_17:06:19.749     【无网络】\n" +
                    "[10] 开始时间戳   17:06:19.749-6s         =      2020-04-27_17:06:12.251    \n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~~~~~  正确判断出 网络变化时间的计算公式如下 Begin ~~~~~~~~~~~~~~~~~~~~\n" +
                    "[0]  结束时间戳      未知\n" +
                    "[0]  开始时间戳      0号Log项的开始时间\n" +
                    "\n" +
                    "[1]  结束时间戳      0号Log项的开始时间   \n" +
                    "[1]  开始时间戳      0号Log项的开始时间-1号Log的持续时间\n" +
                    "\n" +
                    "[2]  结束时间戳       0号Log项的开始时间-1号Log的持续时间\n" +
                    "[2]  开始时间戳        0号Log项的开始时间-1号Log的持续时间 -2号Log的持续时间\n" +
                    "\n" +
                    "n结束时间戳 =  (n+1)开始时间戳\n" +
                    "n开始时间戳 =  n结束时间戳-n持续时间\n" +
                    "~~~~~~~~~~~~~~~~~~~~  正确判断出 网络变化时间的计算公式如下 End ~~~~~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~  DefaultNetworkEvent Begin ~~~~~~~~~~~~~~~\n" +
                    "/frameworks/base/core/java/android/net/metrics/DefaultNetworkEvent.java\n" +
                    " An event recorded by ConnectivityService when there is a change in the default network.\n" +
                    " 默认网络改变的事件 \n" +
                    " \n" +
                    "public class DefaultNetworkEvent {\n" +
                    "\n" +
                    "// The creation time in milliseconds of this DefaultNetworkEvent.\n" +
                    "public final long creationTimeMs;  // 事件时间戳    离开机的时间时长 \n" +
                    "\t\n" +
                    "// The network ID of the network or NETID_UNSET if none.\n" +
                    "public int netId = NETID_UNSET;  // 网络ID\n" +
                    "\n" +
                    "// The list of transport types, as defined in NetworkCapabilities.java.\n" +
                    "public int transports;  //【BLUETOOTH, WIFI , CELLULAR, ETHERNET,VPN , WIFI_AWARE ,LOWPAN】 网络类型  \n" +
                    "\n" +
                    "// The list of transport types of the last previous default network.\n" +
                    "public int previousTransports;  // 之前的默认网络传输类型 \n" +
                    "\t\n" +
                    "// Whether the network has IPv4/IPv6 connectivity.\n" +
                    "public boolean ipv4;   // 当前网络是否似乎 ipv4网络\n" +
                    "public boolean ipv6;  // 当前网络是否似乎 ipv6网络\n" +
                    "\n" +
                    "// The initial network score when this network became the default network.\n" +
                    "public int initialScore;  // 初始得分分数  当这个网络作为默认网络\n" +
                    "\t\n" +
                    "    // The initial network score when this network stopped being the default network.\n" +
                    "    public int finalScore;  // 当这个网络从默认网络转为非默认网络时候的得分\n" +
                    "\t\n" +
                    "    // The total duration in milliseconds this network was the default network.\n" +
                    "    public long durationMs;  // 当前网络在默认网络的持续时间\n" +
                    "\t\n" +
                    "    // The total duration in milliseconds this network was the default network and was validated.\n" +
                    "    public long validatedMs;  // 经过验证后的 在默认网络的持续时间\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t   private String ipSupport() {\n" +
                    "        if (ipv4 && ipv6) {\n" +
                    "            return \"IPv4v6\";\n" +
                    "        }\n" +
                    "        if (ipv6) {\n" +
                    "            return \"IPv6\";\n" +
                    "        }\n" +
                    "        if (ipv4) {\n" +
                    "            return \"IPv4\";\n" +
                    "        }\n" +
                    "        return \"NONE\";\n" +
                    "    }\n" +
                    "\t\n" +
                    "   public String toString() {\n" +
                    "        StringJoiner j = new StringJoiner(\", \", \"DefaultNetworkEvent(\", \")\");\n" +
                    "        j.add(\"netId=\" + netId);  //  网络ID \n" +
                    "        for (int t : BitUtils.unpackBits(transports)) {\n" +
                    "            j.add(NetworkCapabilities.transportNameOf(t));  // 网络类型 \n" +
                    "        }\n" +
                    "        j.add(\"ip=\" + ipSupport());   // 网络支持的IP类型 \n" +
                    "        if (initialScore > 0) {\n" +
                    "            j.add(\"initial_score=\" + initialScore);  // 初始得分\n" +
                    "        }\n" +
                    "        if (finalScore > 0) {\n" +
                    "            j.add(\"final_score=\" + finalScore);  // 最终得分\n" +
                    "        }\n" +
                    "        j.add(String.format(\"duration=%.0fs\", durationMs / 1000.0));  // 该网络是默认网络的持续时间  秒 \n" +
                    " j.add(String.format(\"validation=%04.1f%%\", (validatedMs * 100.0) / durationMs));  // 验证通过时间 占 总连接时间  占比百分比\n" +
                    "        return j.toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "LOG示例:\n" +
                    "14:10:37.350: DefaultNetworkEvent(netId=615, WIFI, ip=IPv4, initial_score=60, final_score=20, duration=3510s, validation=100.0%)\n" +
                    "~~~~~~~~~~~~~~~  DefaultNetworkEvent End ~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~DefaultNetworkMetrics Begin ~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/DefaultNetworkMetrics.java\n" +
                    " class DefaultNetworkMetrics {\n" +
                    " \n" +
                    " \n" +
                    "     // Rolling event buffer used for dumpsys and bugreports.\n" +
                    "    @GuardedBy(\"this\")\n" +
                    "  RingBuffer<DefaultNetworkEvent> mEventsLog = new RingBuffer(DefaultNetworkEvent.class, ROLLING_LOG_SIZE);\n" +
                    "\t\t\t\n" +
                    "    // Information about the current status of the default network.\n" +
                    "    private DefaultNetworkEvent mCurrentDefaultNetwork;\n" +
                    "\t\n" +
                    "\n" +
                    "  static void printEvent(long localTimeMs, PrintWriter pw, DefaultNetworkEvent ev) {\n" +
                    "        long localCreationTimeMs = localTimeMs - ev.durationMs;   //  本地的时间  减去 当前网络持续的时间 = 起始打印的时间\n" +
                    "        pw.println(String.format(\"%tT.%tL: %s\", localCreationTimeMs, localCreationTimeMs, ev));\n" +
                    "    }\n" +
                    "\t\n" +
                    "    public synchronized void listEvents(PrintWriter pw) {\n" +
                    "        pw.println(\"default network events:\");\n" +
                    "        long localTimeMs = System.currentTimeMillis();  // 系统时间戳\n" +
                    "        long timeMs = SystemClock.elapsedRealtime();  // 开机时间戳\n" +
                    "\t\t\n" +
                    "        for (DefaultNetworkEvent ev : mEventsLog.toArray()) {  // 打印 DefaultNetworkEvent 默认网络事件  \n" +
                    "            printEvent(localTimeMs, pw, ev);\n" +
                    "        }\n" +
                    "        mCurrentDefaultNetwork.updateDuration(timeMs);\n" +
                    "        // When printing default network events for bug reports, update validation time\n" +
                    "        // and refresh the last validation timestmap for future validation time updates.\n" +
                    "        if (mIsCurrentlyValid) {\n" +
                    "            updateValidationTime(timeMs);\n" +
                    "            mLastValidationTimeMs = timeMs;\n" +
                    "        }\n" +
                    "        printEvent(localTimeMs, pw, mCurrentDefaultNetwork);  // 打印当前的默认网络事件   // 最后一个?\n" +
                    "    }\n" +
                    "Log示例:\n" +
                    "14:10:37.350: DefaultNetworkEvent(netId=615, WIFI, ip=IPv4, initial_score=60, final_score=20, duration=3510s, validation=100.0%)\n" +
                    "15:06:25.033: DefaultNetworkEvent(netId=632, CELLULAR, ip=IPv4, initial_score=50, final_score=50, duration=163s, validation=100.0%)\n" +
                    "15:06:29.686: DefaultNetworkEvent(netId=615, WIFI, ip=IPv4, initial_score=60, duration=158s, validation=100.0%) 【最后一个】\n" +
                    "\n" +
                    "\n" +
                    " \n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~DefaultNetworkMetrics End ~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java\n" +
                    "// Subservice listening to Netd events via INetdEventListener.aidl.\n" +
                    "\n" +
                    " DefaultNetworkMetrics mDefaultNetworkMetrics = new DefaultNetworkMetrics();\n" +
                    " \n" +
                    "private void cmdList(PrintWriter pw) {\n" +
                    "..........\n" +
                    "\tpw.println(\"\");\n" +
                    "\tif (mNetdListener != null) {\n" +
                    "\t\tmNetdListener.list(pw);  \n" +
                    "\t}\n" +
                    "\tpw.println(\"\");\n" +
                    "\tmDefaultNetworkMetrics.listEvents(pw); 【! 这里打印1 最后一条Log !】  // 默认 DefaultNetworkEvent \n" +
                    "}】\n" +
                    "\n";
            keyWordList.add(connectivity_5_1);

/*
    KeyWordItem connectivity_5_1 = new KeyWordItem();
                connectivity_5_1.keyword = "network statistics:";
                connectivity_5_1.explain="网络统计 ";
                connectivity_5_1.classNameForShuxing = "/frameworks/base/services/core/java/com/android/server/connectivity/IpConnectivityMetrics.java";
                connectivity_5_1.printcode="IpConnectivityMetrics.java   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}\n" +"/frameworks/base/services/core/java/com/android/server/connectivity/NetdEventListenerService.java ->  void list(PrintWriter pw) ";
                connectivity_5_1.printLogUrl="";
keyWordList.add(connectivity_5_1);
            */
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