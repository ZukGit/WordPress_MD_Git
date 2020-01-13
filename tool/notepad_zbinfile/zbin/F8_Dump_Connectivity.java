import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class F8_Dump_Connectivity {

//   getprop.txt  中读取 [ro.bui  ld.version.release]: [10]
//getprop.txt   [ro.hardware.soc.manufacturer]: [qcom]   制造商
//  如果不包含  那么 检测 是否包含MTK
    // .mtk  或者  .mtk
    // .qcom  或者  qcom.




    static String F8DirPathStr = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F8";
    static File F8DirFile = new File(F8DirPathStr);
    static  String getpropFileName = "getprop.txt";
    static  String connectivityFileName = "connectivity.txt";
    static Map<String,File> allDumpMap = new HashMap<String,File>();

    // 获取在目录 F8DirFile 下 所有文件的 绝对路径
    static   ArrayList<File> AllDumpFileList  = new ArrayList<File>();

    static Map<String,String> fileNameMapClass = new HashMap<String,String>();
    static{

        // class ConnectivityService extends IConnectivityManager.Stub {
        fileNameMapClass.put(connectivityFileName,"/frameworks/base/services/core/java/com/android/server/ConnectivityService.java");

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
        F8_Dump_Connectivity mDumpAnalysis = new F8_Dump_Connectivity();
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
            connectivity_1_1.keyword = "NetworkFactories for:";
            connectivity_1_1.explain="网络管理的各个分管经理的集合?  ";
            connectivity_1_1.classNameForShuxing = " ";
            connectivity_1_1.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_1.printLogUrl="";
            connectivity_1_1.expain1="**************** ConnectivityService.NetworkFactoryInfos Begin ****************\n" +
                    "private static class NetworkFactoryInfo {\n" +
                    "public final String name;   // 名称\n" +
                    "public final Messenger messenger;   //传递的消息?  messenger a messenger for ConnectivityService to contact the agent asynchronously.\n" +
                    "public final AsyncChannel asyncChannel;  // 传递消息? \n" +
                    "public final int factorySerialNumber;  // 序列号? the serial number of the factory owning this NetworkAgent\n" +
                    "}\n" +
                    "\t\t\n" +
                    "**************** ConnectivityService.NetworkFactoryInfos End ****************\n" +
                    "\n" +
                    "public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished { \n" +
                    "// NetworkFactoryInfo-各种网络代理类  \n" +
                    "//  Messenger 是异步跨进程交互发送消息的工具类 android.os.Messenger => final class Messenger implements Parcelable { final IMessenger mTarget;}\n" +
                    "HashMap<Messenger, NetworkFactoryInfo> mNetworkFactoryInfos = new HashMap<>();  \n" +
                    "\n" +
                    "adb shell dumpsys connectivity networks\n" +
                    "adb shell dumpsys connectivity --diag\n" +
                    "adb shell dumpsys connectivity --short\n" +
                    "adb shell dumpsys connectivity tethering\n" +
                    "adb shell dumpsys connectivity requests\n" +
                    "adb shell dumpsys connectivity\n" +
                    "\n" +
                    " private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "        final IndentingPrintWriter pw = new IndentingPrintWriter(writer, \"  \");\n" +
                    "        if (!DumpUtils.checkDumpPermission(mContext, TAG, pw)) return;\n" +
                    "        if (asProto) return;\n" +
                    "\n" +
                    "        if (ArrayUtils.contains(args, DIAG_ARG)) {\n" +
                    "            dumpNetworkDiagnostics(pw);  // adb shell dumpsys connectivity --diag \n" +
                    "            return;\n" +
                    "        } else if (ArrayUtils.contains(args, TETHERING_ARG)) {\n" +
                    "            mTethering.dump(fd, pw, args);  // adb shell dumpsys connectivity tethering \n" +
                    "            return;\n" +
                    "        } else if (ArrayUtils.contains(args, NETWORK_ARG)) {\n" +
                    "            dumpNetworks(pw);   // adb shell dumpsys connectivity networks \n" +
                    "            return;\n" +
                    "        } else if (ArrayUtils.contains(args, REQUEST_ARG)) {\n" +
                    "            dumpNetworkRequests(pw);  // adb shell dumpsys connectivity requests\n" +
                    "            return;\n" +
                    "        }\n" +
                    "\n" +
                    "        pw.print(\"NetworkFactories for:\");\n" +
                    "        for (NetworkFactoryInfo nfi : mNetworkFactoryInfos.values()) {\n" +
                    "            pw.print(\" \" + nfi.name);   //  String name;   // 网络工厂 代表不同的网络 代理 名称 \n" +
                    "        }\n" +
                    "        pw.println();\n" +
                    "        pw.println();\n" +
                    "........\n" +
                    "}\n" +
                    "LOG示例:\n" +
                    "NetworkFactories for: \n" +
                    "WifiNetworkFactory                 WIFI网络\n" +
                    "Ethernet                           以太网\n" +
                    "TelephonyNetworkFactory[0]         SIM-1卡槽网络\n" +
                    "PhoneSwitcherNetworkRequstListener 移动网络?\n" +
                    "TelephonyNetworkFactory[1]         SIM-2卡槽网络\n" +
                    "UntrustedWifiNetworkFactory        开放open网络\n";
            keyWordList.add(connectivity_1_1);



            KeyWordItem connectivity_1_2 = new KeyWordItem();
            connectivity_1_2.keyword = "Active default network:";
            connectivity_1_2.explain="当前默认的网络？ ";
            connectivity_1_2.classNameForShuxing = " ";
            connectivity_1_2.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_2.printLogUrl="";
            connectivity_1_2.expain1="**************** Network Begin ****************\n" +
                    "\n" +
                    "public class Network implements Parcelable {\n" +
                    "@UnsupportedAppUsage\n" +
                    "public final int netId;  // netid  app不许使用?\n" +
                    "\n" +
                    "// 用于执行网络操作的类\n" +
                    " private volatile NetworkBoundSocketFactory mNetworkBoundSocketFactory = null;\n" +
                    " \n" +
                    "private final Object mLock = new Object();\n" +
                    "// maybeInitUrlConnectionFactory() 使用前必须检查\n" +
                    "private volatile HttpURLConnectionFactory mUrlConnectionFactory;  // 锁保护对象  连接 \n" +
                    "private boolean httpKeepAlive =Boolean.parseBoolean(System.getProperty(\"http.keepAlive\", \"true\"));  // 保持http 活跃\n" +
                    "// 最大连接数量\n" +
                    "private int httpMaxConnections =httpKeepAlive ? Integer.parseInt(System.getProperty(\"http.maxConnections\", \"5\")) : 0;\n" +
                    "\n" +
                    "// 保持活跃时间\n" +
                    "final long httpKeepAliveDurationMs = Long.parseLong(System.getProperty(\"http.keepAliveDuration\", \"300000\"));  // 5 minutes.\n" +
                    "\n" +
                    "// A boolean to control how getAllByName()/getByName() behaves in the face of Private DNS.\n" +
                    "\n" +
                    "// When true, these calls will request that DNS resolution bypass any\n" +
                    "// Private DNS that might otherwise apply. Use of this feature is restricted\n" +
                    "// and permission checks are made by netd (attempts to bypass Private DNS\n" +
                    "// without appropriate permission are silently turned into vanilla DNS\n" +
                    "// requests). This only affects DNS queries made using this network object.\n" +
                    "\n" +
                    "// It it not parceled to receivers because (a) it can be set or cleared at\n" +
                    "// anytime and (b) receivers should be explicit about attempts to bypass\n" +
                    "// Private DNS so that the intent of the code is easily determined and\n" +
                    "// code search audits are possible.\n" +
                    "final transient boolean mPrivateDnsBypass;  // 是否允许私域 DNS \n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "**************** Network End ****************\n" +
                    "\n" +
                    "\n" +
                    "**************** NetworkAgentInfo Begin ****************\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetworkAgentInfo.java\n" +
                    "\n" +
                    "public class NetworkAgentInfo implements Comparable<NetworkAgentInfo> {\n" +
                    "public NetworkInfo networkInfo;\n" +
                    "public final Network network;\n" +
                    "public LinkProperties linkProperties;\n" +
                    "public NetworkCapabilities networkCapabilities;\n" +
                    "public final NetworkMisc networkMisc;\n" +
                    "public boolean everConnected; // 是否连接过?\n" +
                    "public boolean created;  // 是否创建网络? Indicates if netd has been told to create this Network\n" +
                    "\n" +
                    "// Set to true if this Network successfully passed validation or if it did not satisfy the\n" +
                    "// default NetworkRequest in which case validation will not be attempted.\n" +
                    "// This is a sticky bit; once set it is never cleared even if future validation attempts fail.\n" +
                    "public boolean everValidated; // 是否曾经验证过该网络\n" +
                    "\n" +
                    "// The result of the last validation attempt on this network (true if validated, false if not).\n" +
                    "public boolean lastValidated;  // 最近一次验证是否 通过\n" +
                    "\n" +
                    "// If true, becoming unvalidated will lower the network's score.  如果为true 将降低得分\n" +
                    "public boolean avoidUnvalidated;\n" +
                    "\n" +
                    "// Whether a captive portal was ever detected on this network.\n" +
                    "// This is a sticky bit; once set it is never cleared.\n" +
                    "public boolean everCaptivePortalDetected;  // 是否曾经到该网络是一个portal的网络\n" +
                    "\n" +
                    "// Whether a captive portal was found during the last network validation attempt.\n" +
                    "public boolean lastCaptivePortalDetected; // 最近一次portal验证是否通过\n" +
                    "\n" +
                    "// Indicates the captive portal app was opened to show a login UI to the user, but the network has not validated yet. \n" +
                    "// true---标识已打开UI界面   但是用户还未完成认证 验证  \n" +
                    "public volatile boolean captivePortalValidationPending;\n" +
                    "\t\n" +
                    "// Set to true when partial connectivity was detected.\n" +
                    "public boolean partialConnectivity;  // 支持局部连接? \n" +
                    " }\n" +
                    "\n" +
                    "**************** NetworkAgentInfo End ****************\n" +
                    "\n" +
                    "**************** getDefaultNetwork() Begin ****************\n" +
                    "\n" +
                    "    // Note: if mDefaultRequest is changed, NetworkMonitor needs to be updated.\n" +
                    "    private final NetworkRequest mDefaultRequest;\n" +
                    "\t\n" +
                    "    private NetworkAgentInfo getDefaultNetwork() {\n" +
                    "        return getNetworkForRequest(mDefaultRequest.requestId);\n" +
                    "    }\n" +
                    "\t\n" +
                    "\t    private NetworkAgentInfo getNetworkForRequest(int requestId) {\n" +
                    "        synchronized (mNetworkForRequestId) {\n" +
                    "            return mNetworkForRequestId.get(requestId);   // 拿到 NetworkAgentInfo \n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "@GuardedBy(\"mNetworkForRequestId\")  // 以 requestid为索引的 装 NetworkAgentInfo的集合 SparseArray\n" +
                    "final SparseArray<NetworkAgentInfo> mNetworkForRequestId = new SparseArray<>();\n" +
                    "\n" +
                    "**************** getDefaultNetwork() End ****************\n" +
                    "\t\n" +
                    "public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished { \n" +
                    "\n" +
                    " private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "        final NetworkAgentInfo defaultNai = getDefaultNetwork();  // 获取当前默认网络\n" +
                    "        pw.print(\"Active default network: \");\n" +
                    "        if (defaultNai == null) {  // 没有默认网络\n" +
                    "            pw.println(\"none\");\n" +
                    "        } else {\n" +
                    "            pw.println(defaultNai.network.netId);   // 打印默认网络的 netid\n" +
                    "        }\n" +
                    "        pw.println();\n" +
                    "\t}\n" +
                    "LOG示例:\n" +
                    "Active default network: none  ----没有默认网络\n" +
                    "Active default network: 1     ----当前的默认网络的netid是1 ";
            keyWordList.add(connectivity_1_2);


            KeyWordItem connectivity_1_3 = new KeyWordItem();
            connectivity_1_3.keyword = "Current Networks:";
            connectivity_1_3.explain="打印当前所有的 ConnectivityService->HashMap<Messenger, NetworkAgentInfo> mNetworkAgentInfos  中的  NetworkAgentInfo数组 ";
            connectivity_1_3.classNameForShuxing = " ";
            connectivity_1_3.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_3.printLogUrl="";
            connectivity_1_3.expain1="****************** NetworkAgentInfo.LingerTimer  Begin ******************\n" +
                    "static class LingerTimer implements Comparable<LingerTimer> {\n" +
                    "\tpublic final NetworkRequest request;  //  网络请求 \n" +
                    "\tpublic final long expiryMs;   // 有效期时间戳 endline\n" +
                    "\n" +
                    "\tpublic String toString() {  // expires 剩余有效期\n" +
                    "\treturn String.format(\"%s, expires %dms\", request.toString(), expiryMs - SystemClock.elapsedRealtime());\n" +
                    "}\n" +
                    "\t\t\n" +
                    "}\n" +
                    "\n" +
                    "****************** NetworkAgentInfo.LingerTimer  End  ******************\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "****************** NetworkRequest  Begin ******************\n" +
                    "android.net.NetworkRequest.java\n" +
                    "\n" +
                    "public class NetworkRequest implements Parcelable {\n" +
                    "public  NetworkCapabilities networkCapabilities;  // 网络能力 NetworkCapabilities 已有分析 long bitmask 比特位\n" +
                    "public final int requestId;  // 区分请求的标识 identify \n" +
                    "public final int legacyType;  // 与 enum Type 一一对应的 索引 \n" +
                    "public static enum Type {  NONE, LISTEN(不满足-监听),TRACK_DEFAULT, REQUEST(前台请求),  BACKGROUND_REQUEST(后台请求), };NetworkRequestProto.TYPE_NONE  NetworkRequestProto.REQUEST \n" +
                    "public final Type type;\n" +
                    "\n" +
                    "public String toString() { //  NetworkRequest 的 toString()\n" +
                    " // 打印 type=【enum Type 】  requestId 【 id = int requestId】 legacyType = int legacyType   NetworkCapabilities包含的long bitmask\n" +
                    "\treturn \"NetworkRequest [ \" + type + \" id=\" + requestId +\n" +
                    "      (legacyType != ConnectivityManager.TYPE_NONE ? \", legacyType=\" + legacyType : \"\") +\n" +
                    "        \", \" + networkCapabilities.toString() + \" ]\";\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "****************** NetworkRequest  End ******************\n" +
                    "\n" +
                    "****************** NetworkCapabilities  解释 Begin ******************\n" +
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
                    "\n" +
                    "\n" +
                    "**************** Network Begin ****************\n" +
                    "\n" +
                    "public class Network implements Parcelable {\n" +
                    "@UnsupportedAppUsage\n" +
                    "public final int netId;  // netid  app不许使用?\n" +
                    "\n" +
                    "// 用于执行网络操作的类\n" +
                    " private volatile NetworkBoundSocketFactory mNetworkBoundSocketFactory = null;\n" +
                    " \n" +
                    "private final Object mLock = new Object();\n" +
                    "// maybeInitUrlConnectionFactory() 使用前必须检查\n" +
                    "private volatile HttpURLConnectionFactory mUrlConnectionFactory;  // 锁保护对象  连接 \n" +
                    "private boolean httpKeepAlive =Boolean.parseBoolean(System.getProperty(\"http.keepAlive\", \"true\"));  // 保持http 活跃\n" +
                    "// 最大连接数量\n" +
                    "private int httpMaxConnections =httpKeepAlive ? Integer.parseInt(System.getProperty(\"http.maxConnections\", \"5\")) : 0;\n" +
                    "\n" +
                    "// 保持活跃时间\n" +
                    "final long httpKeepAliveDurationMs = Long.parseLong(System.getProperty(\"http.keepAliveDuration\", \"300000\"));  // 5 minutes.\n" +
                    "\n" +
                    "// A boolean to control how getAllByName()/getByName() behaves in the face of Private DNS.\n" +
                    "\n" +
                    "// When true, these calls will request that DNS resolution bypass any\n" +
                    "// Private DNS that might otherwise apply. Use of this feature is restricted\n" +
                    "// and permission checks are made by netd (attempts to bypass Private DNS\n" +
                    "// without appropriate permission are silently turned into vanilla DNS\n" +
                    "// requests). This only affects DNS queries made using this network object.\n" +
                    "\n" +
                    "// It it not parceled to receivers because (a) it can be set or cleared at\n" +
                    "// anytime and (b) receivers should be explicit about attempts to bypass\n" +
                    "// Private DNS so that the intent of the code is easily determined and\n" +
                    "// code search audits are possible.\n" +
                    "final transient boolean mPrivateDnsBypass;  // 是否允许私域 DNS \n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        return Integer.toString(netId);   // 仅仅打印 netid?\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "**************** Network End ****************\n" +
                    "\n" +
                    "\n" +
                    "**************** NetworkInfo End ****************\n" +
                    "/frameworks/base/core/java/android/net/NetworkInfo.java\n" +
                    "public class NetworkInfo implements Parcelable {\n" +
                    "\n" +
                    "一般状态\n" +
                    "enum State { CONNECTING, CONNECTED, SUSPENDED, DISCONNECTING, DISCONNECTED, UNKNOWN}\n" +
                    "\n" +
                    "详细状态\n" +
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
                    "\tStringBuilder builder = new StringBuilder(\"[\"); \n" +
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
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "**************** NetworkInfo End ****************\n" +
                    "\n" +
                    "\n" +
                    "**************** NetworkAgentInfo Begin ****************\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetworkAgentInfo.java\n" +
                    "\n" +
                    "public class NetworkAgentInfo implements Comparable<NetworkAgentInfo> {\n" +
                    "public NetworkInfo networkInfo;\n" +
                    "public final Network network;\n" +
                    "public LinkProperties linkProperties;\n" +
                    "public NetworkCapabilities networkCapabilities;\n" +
                    "public final NetworkMisc networkMisc;\n" +
                    "public boolean everConnected; // 是否连接过?\n" +
                    "public boolean created;  // 是否创建网络? Indicates if netd has been told to create this Network\n" +
                    "\n" +
                    "// Set to true if this Network successfully passed validation or if it did not satisfy the\n" +
                    "// default NetworkRequest in which case validation will not be attempted.\n" +
                    "// This is a sticky bit; once set it is never cleared even if future validation attempts fail.\n" +
                    "public boolean everValidated; // 是否曾经验证过该网络\n" +
                    "\n" +
                    "// The result of the last validation attempt on this network (true if validated, false if not).\n" +
                    "public boolean lastValidated;  // 最近一次验证是否 通过\n" +
                    "\n" +
                    "// If true, becoming unvalidated will lower the network's score.  如果为true 将降低得分\n" +
                    "public boolean avoidUnvalidated;\n" +
                    "\n" +
                    "// Whether a captive portal was ever detected on this network.\n" +
                    "// This is a sticky bit; once set it is never cleared.\n" +
                    "public boolean everCaptivePortalDetected;  // 是否曾经到该网络是一个portal的网络\n" +
                    "\n" +
                    "// Whether a captive portal was found during the last network validation attempt.\n" +
                    "public boolean lastCaptivePortalDetected; // 最近一次portal验证是否通过\n" +
                    "\n" +
                    "// Indicates the captive portal app was opened to show a login UI to the user, but the network has not validated yet. \n" +
                    "// true---标识已打开UI界面   但是用户还未完成认证 验证  \n" +
                    "public volatile boolean captivePortalValidationPending;\n" +
                    "\t\n" +
                    "// Set to true when partial connectivity was detected.\n" +
                    "public boolean partialConnectivity;  // 支持局部连接? \n" +
                    " \n" +
                    "boolean mLingering;   // Whether the network is lingering or not   是否逗留在该网络\n" +
                    "private long mLingerExpiryMs;  // 在当前网络逗留的时间 \n" +
                    "\n" +
                    "// Used by ConnectivityService to keep track of 464xlat.\n" +
                    "// 464XLAT采用翻译方式进行IPv4 over IPv6，\n" +
                    "// 在IPv4-IPv6网络边界进行NAT翻译，\n" +
                    "// 在IPv6网络中以IPv6报文进行转发和处理\n" +
                    "// 当应用不支持64位DNS，通过CLAT 处理从IPV4到IPV6的转换\n" +
                    "// CLAT是IPV4在GSM网络使用NAT64转换到IPV6 的入口      Android CLAT 实现了android平台的CLAT\n" +
                    "public final Nat464Xlat clatd;  // 464XLAT是用于IPv4与IPv6之间通信\n" +
                    "\t\n" +
                    " // TODO: Print shorter members first and only print the boolean variable which value is true to improve readability.\n" +
                    "public String toString() {\n" +
                    "\treturn \"NetworkAgentInfo{ ni{\" + networkInfo + \"}  \"// 打印 NetworkInfo.toString()->  示例: [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: (none), failover: false, available: true, roaming: false]\n" +
                    "\t\t\t+ \"network{\" + network + \"}     // Network network;  network.toString()->仅仅打印 int netId; 网络ID \n" +
                    "\t\t\t+\" nethandle{\" + network.getNetworkHandle() + \"}  \"  // long getNetworkHandle()  句柄?  Returns a handle representing this {@code Network}, for use with the NDK API.\n" +
                    "\t\t\t+ \"lp{\" + linkProperties + \"}  \"  // 连接速率 LinkProperties \n" +
                    "\t\t\t+ \"nc{\" + networkCapabilities      //   打印 NetworkCapabilities   bit比特功能位 \n" +
                    "\t\t\t+ \"}  Score{\" + getCurrentScore() + \"}  \"    // int getCurrentScore() {}  获取当前网络的分数  Get the current score for this Network.\n" +
                    "\t\t\t+ \"everValidated{\" + everValidated + \"} \"  // boolean 是否曾经验证过\n" +
                    "            + \"lastValidated{\" + lastValidated + \"}  \"   // boolean 上一次验证是否通过\n" +
                    "\t\t\t+ \"created{\" + created + \"}\"     // boolean 是否创建网络\n" +
                    "\t\t\t+\" lingering{\" + isLingering() + \"} \"  //   boolean mLingering;    是否逗留在该网络\n" +
                    "\t\t\t\n" +
                    "\t\t\t// boolean explicitlySelected; 如果用户通过第三方软件 设置 等 手动显式的连接到的网络  那么该值为 true\n" +
                    "\t\t\t+ \"explicitlySelected{\" + networkMisc.explicitlySelected + \"} \" \n" +
                    "\t\t\t+ \"acceptUnvalidated{\" + networkMisc.acceptUnvalidated + \"} \" // 未经过验证仍然使用该网络的标识 \n" +
                    "\t\t\t+ \"allowInvalidation{\" + networkMisc.allowInvalidation + \"} \"  // 是否允许无效? \n" +
                    "\t\t\t\n" +
                    "\t\t\t+ \"everCaptivePortalDetected{\" + everCaptivePortalDetected + \"} \"  // 是否曾经检测到该网络是一个portal的网络\n" +
                    "\t\t\t+ \"lastCaptivePortalDetected{\" + lastCaptivePortalDetected + \"} \" // 上一次是否检测到该网络是一个portal的网络\n" +
                    "\t\t\t+ \"captivePortalValidationPending{\" + captivePortalValidationPending + \"} \"  // 是否已打开UI 进行验证\n" +
                    "\t\t\t+ \"partialConnectivity{\" + partialConnectivity + \"} \"   //  部分连接? \n" +
                    "\t\t\t+ \"acceptPartialConnectivity{\" + networkMisc.acceptPartialConnectivity + \"} \"  //  是否显式的认证?即使部门连接\n" +
                    "\t\t\t+ \"clat{\" + clatd + \"} \"\n" +
                    "\t\t\t+ \"}\";\n" +
                    "\t\t\t\t\n" +
                    "\n" +
                    "\t\t\t\t\n" +
                    "**************** NetworkAgentInfo End ****************\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "**************** NetworkAgentInfo.java -> dumpNetworks(pw)  Begin ****************\n" +
                    "\n" +
                    "    // The list of NetworkRequests being satisfied by this Network.\n" +
                    "    private final SparseArray<NetworkRequest> mNetworkRequests = new SparseArray<>();\n" +
                    "\t\n" +
                    "private void dumpNetworks(IndentingPrintWriter pw) {\n" +
                    "\tfor (NetworkAgentInfo nai : networksSortedById()) {\n" +
                    "\t\tpw.println(nai.toString());   // NetworkAgentInfo.toString\n" +
                    "\t\tpw.increaseIndent();\n" +
                    "\t\tpw.println(String.format(\n" +
                    "\t\t\t\t\"Requests: REQUEST:%d LISTEN:%d BACKGROUND_REQUEST:%d total:%d\",\n" +
                    "\t\t\t\tnai.numForegroundNetworkRequests(),  // 返回当前网络满足的【前台 网络 请求个数】  int numForegroundNetworkRequests() // Returns the number of foreground requests currently satisfied by this network.\n" +
                    "\t\t\t\tnai.numNetworkRequests(总的请求) - nai.numRequestNetworkRequests(满足请求)】,  // 计算出当前网络 不满足的请求 的个数\n" +
                    "\t\t\t\tnai.numBackgroundNetworkRequests(), //  返回当前网络 满足的【后台网络请求个数 】  //      * Returns the number of requests currently satisfied by this network of type * {@link android.net.NetworkRequest.Type.BACKGROUND_REQUEST}.\n" +
                    "\t\t\t\tnai.numNetworkRequests()));  // 返回当前网络满足的任何类型的网络请求   Returns the number of requests of any type currently satisfied by this network.\n" +
                    "\t\tpw.increaseIndent();\n" +
                    "\t\tfor (int i = 0, i < nai.numNetworkRequests(), i++) {  // 打印 所有对该网络的请求 NetworkRequest\n" +
                    "\t\t\tpw.println(nai.requestAt(i).toString());  // NetworkRequest requestAt(int index) { return mNetworkRequests.valueAt(index); }\n" +
                    "\t\t}\n" +
                    "\t\t   \n" +
                    "\t\tpw.decreaseIndent();\n" +
                    "\t\tpw.println(\"Lingered:\");   // 滞留时间? \n" +
                    "\t\tpw.increaseIndent();\n" +
                    " //     public void dumpLingerTimers(PrintWriter pw) {} ---> SortedSet<LingerTimer> mLingerTimers = new TreeSet<>();  \n" +
                    "// for (LingerTimer timer : mLingerTimers) { pw.println(timer); } // 打印 LingerTimer\n" +
                    "\t\tnai.dumpLingerTimers(pw);\n" +
                    "\t\tpw.decreaseIndent();\n" +
                    "\t\tpw.decreaseIndent();\n" +
                    "\t}\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\t\n" +
                    "\n" +
                    "networksSortedById() \n" +
                    "||\n" +
                    "\\/\n" +
                    "\n" +
                    "// Return an array of all current NetworkAgentInfos sorted by network id.\n" +
                    "// 把  HashMap<Messenger, NetworkAgentInfo> mNetworkAgentInfos = new HashMap<>(); 的value 转为 数组 \n" +
                    "\n" +
                    "private NetworkAgentInfo[] networksSortedById() {\n" +
                    "\tNetworkAgentInfo[] networks = new NetworkAgentInfo[0];\n" +
                    "\tnetworks = mNetworkAgentInfos.values().toArray(networks);\n" +
                    "\t// 对  NetworkAgentInfo[] 中的每一个 成员 NetworkAgentInfo nai 比较它们的 netId  升序 排序 \n" +
                    "\tArrays.sort(networks, Comparator.comparingInt(nai -> nai.network.netId));\n" +
                    "\treturn networks;\n" +
                    "}\n" +
                    "\t\n" +
                    "****************  NetworkAgentInfo.java -> dumpNetworks(pw)  End ****************\n" +
                    "\t\n" +
                    "public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished { \n" +
                    "    private boolean mRestrictBackground;  /** 后台数据是否受限 Flag indicating if background data is restricted. */\n" +
                    "\n" +
                    " private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "\tpw.println(\"Current Networks:\");\n" +
                    "\tpw.increaseIndent();\n" +
                    "\tdumpNetworks(pw);\n" +
                    "\tpw.decreaseIndent();\n" +
                    "\tpw.println();\n" +
                    "\n" +
                    "\tpw.print(\"Restrict background: \");\n" +
                    "\tpw.println(mRestrictBackground);\n" +
                    "\tpw.println();\n" +
                    "\t}\n" +
                    "LOG示例:\n" +
                    "Current Networks: 打印ConnectivityService->HashMap<Messenger, NetworkAgentInfo> mNetworkAgentInfos 中的Value 以 netid升序\n" +
                    "Restrict background: true    后台数据受限 mRestrictBackground=true\n" +
                    "Restrict background: false   后台数据不受限 mRestrictBackground=false";
            keyWordList.add(connectivity_1_3);


            KeyWordItem connectivity_1_4 = new KeyWordItem();
            connectivity_1_4.keyword = "Status for known UIDs:";
            connectivity_1_4.explain="应用对应的网络控制权限?   ";
            connectivity_1_4.classNameForShuxing = " ";
            connectivity_1_4.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_4.printLogUrl="";
            connectivity_1_4.expain1="**************** NetworkPolicyManager.java uidRulesToString(int rule) Begin ****************\n" +
                    "//   应用的权限 ?  \n" +
                    " int RULE_NONE = 0;                    NONE   /** 没有设置 No specific rule was set  */\n" +
                    " int RULE_ALLOW_METERED = 1 << 0;      ALLOW_METERED        /** 统计流量的权限  Allow traffic on metered networks.  */\n" +
                    " int RULE_TEMPORARY_ALLOW_METERED = 1 << 1;  TEMPORARY_ALLOW_METERED        /** 仅在前台统计流量的权限 Temporarily allow traffic on metered networks because app is on foreground. */\n" +
                    " int RULE_REJECT_METERED = 1 << 2;     REJECT_METERED  /** 拒绝计量流量?  Reject traffic on metered networks. */\n" +
                    " int RULE_ALLOW_ALL = 1 << 5;     ALLOW_ALL   /** 所有网络流量都计量  Network traffic should be allowed on all networks (metered or non-metered), although metered-network restrictions could still apply. */\n" +
                    " int RULE_REJECT_ALL = 1 << 6;     REJECT_ALL  /** 拒绝所有网络上的流量  Reject traffic on all networks. */\n" +
                    "\n" +
                    "    public static String uidRulesToString(int uidRules) {\n" +
                    "        final StringBuilder string = new StringBuilder().append(uidRules).append(\" (\");\n" +
                    "        if (uidRules == RULE_NONE) {\n" +
                    "            string.append(\"NONE\");\n" +
                    "        } else {\n" +
                    "            string.append(DebugUtils.flagsToString(NetworkPolicyManager.class, \"RULE_\", uidRules));\n" +
                    "        }\n" +
                    "        string.append(\")\");\n" +
                    "        return string.toString();\n" +
                    "    }\n" +
                    "**************** NetworkPolicyManager.java uidRulesToString(int rule) End ****************\n" +
                    "\n" +
                    "\n" +
                    "public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished { \n" +
                    "// Stale copy of uid rules provided by NPMS. As long as they are accessed only in internal handler thread, they don't need a lock.\n" +
                    "\n" +
                    "SparseIntArray mUidRules = new SparseIntArray();\n" +
                    "\n" +
                    "e void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "        pw.println(\"Status for known UIDs:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        final int size = mUidRules.size();\n" +
                    "        for (int i = 0; i < size; i++) {\n" +
                    "            // Don't crash if the array is modified while dumping in bugreports.\n" +
                    "            try {\n" +
                    "                final int uid = mUidRules.keyAt(i);\n" +
                    "                final int uidRules = mUidRules.get(uid, RULE_NONE);\n" +
                    "\t\t\t\t // NetworkPolicyManager.java的 uidRulesToString 方法\n" +
                    "                pw.println(\"UID=\" + uid + \" rules=\" + uidRulesToString(uidRules)); \n" +
                    "            } catch (ArrayIndexOutOfBoundsException e) {\n" +
                    "                pw.println(\"  ArrayIndexOutOfBoundsException\");\n" +
                    "            } catch (ConcurrentModificationException e) {\n" +
                    "                pw.println(\"  ConcurrentModificationException\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "        pw.println();\n" +
                    "        pw.decreaseIndent();\n" +
                    "}\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "  UID=10004 rules=1 (ALLOW_METERED|NONE)\n" +
                    "  UID=10094 rules=64 (NONE|REJECT_ALL)";
            keyWordList.add(connectivity_1_4);


            KeyWordItem connectivity_1_5 = new KeyWordItem();
                        connectivity_1_5.keyword = "Network Requests:";
                        connectivity_1_5.explain="网络请求 NetRequest的集合 ";
                        connectivity_1_5.classNameForShuxing = " ConnectivityService-》 HashMap<NetworkRequest, NetworkRequestInfo> mNetworkRequests ";
                        connectivity_1_5.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
                        connectivity_1_5.printLogUrl="";
                        connectivity_1_5.expain1="****************** NetworkCapabilities  解释 Begin ******************\n" +
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
                                "\n" +
                                "public static @NonNull String capabilityNameOf(@NetCapability int capability) {\n" +
                                "switch (capability) {\n" +
                                "\tcase NET_CAPABILITY_MMS:                  return \"MMS\";\n" +
                                "\tcase NET_CAPABILITY_SUPL:                 return \"SUPL\";\n" +
                                "\tcase NET_CAPABILITY_DUN:                  return \"DUN\";\n" +
                                "\tcase NET_CAPABILITY_FOTA:                 return \"FOTA\";\n" +
                                "\tcase NET_CAPABILITY_IMS:                  return \"IMS\";\n" +
                                "\tcase NET_CAPABILITY_CBS:                  return \"CBS\";\n" +
                                "\tcase NET_CAPABILITY_WIFI_P2P:             return \"WIFI_P2P\";\n" +
                                "\tcase NET_CAPABILITY_IA:                   return \"IA\";\n" +
                                "\tcase NET_CAPABILITY_RCS:                  return \"RCS\";\n" +
                                "\tcase NET_CAPABILITY_XCAP:                 return \"XCAP\";\n" +
                                "\tcase NET_CAPABILITY_EIMS:                 return \"EIMS\";\n" +
                                "\tcase NET_CAPABILITY_NOT_METERED:          return \"NOT_METERED\";\n" +
                                "\tcase NET_CAPABILITY_INTERNET:             return \"INTERNET\";\n" +
                                "\tcase NET_CAPABILITY_NOT_RESTRICTED:       return \"NOT_RESTRICTED\";\n" +
                                "\tcase NET_CAPABILITY_TRUSTED:              return \"TRUSTED\";\n" +
                                "\tcase NET_CAPABILITY_NOT_VPN:              return \"NOT_VPN\";\n" +
                                "\tcase NET_CAPABILITY_VALIDATED:            return \"VALIDATED\";\n" +
                                "\tcase NET_CAPABILITY_CAPTIVE_PORTAL:       return \"CAPTIVE_PORTAL\";\n" +
                                "\tcase NET_CAPABILITY_NOT_ROAMING:          return \"NOT_ROAMING\";\n" +
                                "\tcase NET_CAPABILITY_FOREGROUND:           return \"FOREGROUND\";\n" +
                                "\tcase NET_CAPABILITY_NOT_CONGESTED:        return \"NOT_CONGESTED\";\n" +
                                "\tcase NET_CAPABILITY_NOT_SUSPENDED:        return \"NOT_SUSPENDED\";\n" +
                                "\tcase NET_CAPABILITY_OEM_PAID:             return \"OEM_PAID\";\n" +
                                "\tcase NET_CAPABILITY_MCX:                  return \"MCX\";\n" +
                                "\tcase NET_CAPABILITY_PARTIAL_CONNECTIVITY: return \"PARTIAL_CONNECTIVITY\";\n" +
                                "\tdefault:                                  return Integer.toString(capability);\n" +
                                "}\n" +
                                "}\n" +
                                "\n" +
                                "****************** NetworkCapabilities  解释 End   ******************\n" +
                                "\n" +
                                "****************** NetworkRequest  Begin ******************\n" +
                                "android.net.NetworkRequest.java\n" +
                                "\n" +
                                "public class NetworkRequest implements Parcelable {\n" +
                                "public  NetworkCapabilities networkCapabilities;  // 网络能力 NetworkCapabilities 已有分析 long bitmask 比特位\n" +
                                "public final int requestId;  // 区分请求的标识 identify \n" +
                                "public final int legacyType;  // 与 enum Type 一一对应的 索引 \n" +
                                "public static enum Type {  NONE, LISTEN(不满足-监听),TRACK_DEFAULT, REQUEST(前台请求),  BACKGROUND_REQUEST(后台请求), };NetworkRequestProto.TYPE_NONE  NetworkRequestProto.REQUEST \n" +
                                "public final Type type;\n" +
                                "\n" +
                                "public String toString() { //  NetworkRequest 的 toString()\n" +
                                " // 打印 type=【enum Type 】  requestId 【 id = int requestId】 legacyType = int legacyType   NetworkCapabilities包含的long bitmask\n" +
                                "\treturn \"NetworkRequest [ \" + type + \" id=\" + requestId +\n" +
                                "      (legacyType != ConnectivityManager.TYPE_NONE ? \", legacyType=\" + legacyType : \"\") +\n" +
                                "        \", \" + networkCapabilities.toString() + \" ]\";\n" +
                                "}\n" +
                                "}\n" +
                                "\n" +
                                "****************** NetworkRequest  End ******************\n" +
                                "\n" +
                                "**************** NetworkRequestInfo Begin ****************\n" +
                                " // 追踪请求客户端 Tracks info about the requester.  Also used to notice when the calling process dies so we can self-expire\n" +
                                "\n" +
                                "    class NetworkRequestInfo implements IBinder.DeathRecipient {\n" +
                                "        final NetworkRequest request;   // 网络请求?\n" +
                                "        final PendingIntent mPendingIntent;  // 什么意思?  暂缓条件 PendingIntent\n" +
                                "        boolean mPendingIntentSent;  // 标识是否有 PendingIntent等待发送\n" +
                                "        final IBinder mBinder;   // 客户端的 Client的 Binder?\n" +
                                "        final int mPid;  // 进程ID\n" +
                                "        final int mUid;  // USerID APPID\n" +
                                "        final Messenger messenger;  // 信使 跨进程交互\n" +
                                "        HashSet<NetworkAgentInfo> mSatisfiedSuspendNet = new HashSet<NetworkAgentInfo>();  // 客户端信息? \n" +
                                "\t\n" +
                                "\t\n" +
                                "        public String toString() {\n" +
                                "            return \"uid/pid:\" + mUid + \"/\" + mPid + \" \"    // 进程ID 和 APPID-UID\n" +
                                "\t\t\t  + request  // 打印 NetworkRequest \n" +
                                "\t\t\t  + (mPendingIntent == null ? \"\" : \" to trigger \" + mPendingIntent); //   如果有待发送的 PendingIntent 那么打印\n" +
                                "        }\n" +
                                "\t\t\n" +
                                "\t}\n" +
                                "\t\n" +
                                "**************** NetworkRequestInfo End ****************\n" +
                                "\n" +
                                "  \n" +
                                "  \n" +
                                "  \n" +
                                "public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished { \n" +
                                "\n" +
                                " HashMap<NetworkRequest, NetworkRequestInfo> mNetworkRequests = new HashMap<>();  // 打印所有 NetworkRequestInfo\n" +
                                " \n" +
                                "void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                                "        pw.println(\"Network Requests:\");\n" +
                                "        pw.increaseIndent();\n" +
                                "        dumpNetworkRequests(pw);\n" +
                                "        pw.decreaseIndent();\n" +
                                "        pw.println();\n" +
                                "\n" +
                                "}\n" +
                                "\n" +
                                "||\n" +
                                "\\/\n" +
                                "private void dumpNetworkRequests(IndentingPrintWriter pw) {\n" +
                                "\tfor (NetworkRequestInfo nri : requestsSortedById()) {\n" +
                                "\t\tpw.println(nri.toString());   // 打印所有的 NetworkRequestInfo 信息\n" +
                                "\t}\n" +
                                "}\n" +
                                "\n" +
                                "||\n" +
                                "\\/\n" +
                                "// Return an array of all current NetworkRequest sorted by request id.\n" +
                                "// 获取  HashMap<NetworkRequest, NetworkRequestInfo> mNetworkRequests = new HashMap<>(); 的所有  value\n" +
                                "NetworkRequestInfo[] requestsSortedById() { \n" +
                                "\tNetworkRequestInfo[] requests = new NetworkRequestInfo[0];\n" +
                                "\trequests = mNetworkRequests.values().toArray(requests);\n" +
                                "\tArrays.sort(requests, Comparator.comparingInt(nri -> nri.request.requestId));  // java8 以requestId升序排列\n" +
                                "\treturn requests;\n" +
                                "}\n" +
                                "\n" +
                                "LOG示例:\n" +
                                "  uid/pid:1000/1427 NetworkRequest [ REQUEST id=1, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]\n" +
                                "  uid/pid:1000/1427 NetworkRequest [ BACKGROUND_REQUEST id=2, [ Transports: CELLULAR Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]\n" +
                                "  uid/pid:1000/1427 NetworkRequest [ LISTEN id=4, [ Capabilities: FOREGROUND] ]\n" +
                                "  uid/pid:1000/1427 NetworkRequest [ LISTEN id=5, [ Capabilities: NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 1000] ]\n" +
                                "  uid/pid:1000/1427 NetworkRequest [ REQUEST id=6, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]\n" +
                                "  uid/pid:10015/5322 NetworkRequest [ LISTEN id=29, [ Capabilities: NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 10015] ]\n" +
                                "  ";
        keyWordList.add(connectivity_1_5);


            KeyWordItem connectivity_1_6 = new KeyWordItem();
            connectivity_1_6.keyword = "mLegacyTypeTracker:";
            connectivity_1_6.explain="有效的TypeTracker 追踪   ConnectivityService.java 内部类  ConnectivityService.LegacyTypeTracker ";
            connectivity_1_6.classNameForShuxing = " ConnectivityService -》  private final LegacyTypeTracker mLegacyTypeTracker = new LegacyTypeTracker(this); ";
            connectivity_1_6.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_6.printLogUrl="   mLegacyTypeTracker.dump(pw); ";
            connectivity_1_6.expain1="**************** NetworkAgentInfo Begin ****************\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/NetworkAgentInfo.java\n" +
                    "\n" +
                    "public class NetworkAgentInfo implements Comparable<NetworkAgentInfo> {\n" +
                    "public NetworkInfo networkInfo;    // 网络信息\n" +
                    "public final Network network;   // 网络 netid\n" +
                    "public LinkProperties linkProperties;   // 连接属性\n" +
                    "public NetworkCapabilities networkCapabilities;  // 网络能力\n" +
                    "public final NetworkMisc networkMisc;\n" +
                    "public boolean everConnected; // 是否连接过?\n" +
                    "public boolean created;  // 是否创建网络? Indicates if netd has been told to create this Network\n" +
                    "\n" +
                    "// Set to true if this Network successfully passed validation or if it did not satisfy the\n" +
                    "// default NetworkRequest in which case validation will not be attempted.\n" +
                    "// This is a sticky bit; once set it is never cleared even if future validation attempts fail.\n" +
                    "public boolean everValidated; // 是否曾经验证过该网络\n" +
                    "\n" +
                    "// The result of the last validation attempt on this network (true if validated, false if not).\n" +
                    "public boolean lastValidated;  // 最近一次验证是否 通过\n" +
                    "\n" +
                    "// If true, becoming unvalidated will lower the network's score.  如果为true 将降低得分\n" +
                    "public boolean avoidUnvalidated;\n" +
                    "\n" +
                    "// Whether a captive portal was ever detected on this network.\n" +
                    "// This is a sticky bit; once set it is never cleared.\n" +
                    "public boolean everCaptivePortalDetected;  // 是否曾经到该网络是一个portal的网络\n" +
                    "\n" +
                    "// Whether a captive portal was found during the last network validation attempt.\n" +
                    "public boolean lastCaptivePortalDetected; // 最近一次portal验证是否通过\n" +
                    "\n" +
                    "// Indicates the captive portal app was opened to show a login UI to the user, but the network has not validated yet. \n" +
                    "// true---标识已打开UI界面   但是用户还未完成认证 验证  \n" +
                    "public volatile boolean captivePortalValidationPending;\n" +
                    "\t\n" +
                    "// Set to true when partial connectivity was detected.\n" +
                    "public boolean partialConnectivity;  // 支持局部连接? \n" +
                    " \n" +
                    "boolean mLingering;   // Whether the network is lingering or not   是否逗留在该网络\n" +
                    "private long mLingerExpiryMs;  // 在当前网络逗留的时间 \n" +
                    "\n" +
                    "// Used by ConnectivityService to keep track of 464xlat.\n" +
                    "// 464XLAT采用翻译方式进行IPv4 over IPv6，\n" +
                    "// 在IPv4-IPv6网络边界进行NAT翻译，\n" +
                    "// 在IPv6网络中以IPv6报文进行转发和处理\n" +
                    "// 当应用不支持64位DNS，通过CLAT 处理从IPV4到IPV6的转换\n" +
                    "// CLAT是IPV4在GSM网络使用NAT64转换到IPV6 的入口      Android CLAT 实现了android平台的CLAT\n" +
                    "public final Nat464Xlat clatd;  // 464XLAT是用于IPv4与IPv6之间通信\n" +
                    "\t\n" +
                    " // TODO: Print shorter members first and only print the boolean variable which value is true to improve readability.\n" +
                    "public String toString() {\n" +
                    "\treturn \"NetworkAgentInfo{ ni{\" + networkInfo + \"}  \"// 打印 NetworkInfo.toString()->  示例: [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: (none), failover: false, available: true, roaming: false]\n" +
                    "\t\t\t+ \"network{\" + network + \"}     // Network network;  network.toString()->仅仅打印 int netId; 网络ID \n" +
                    "\t\t\t+\" nethandle{\" + network.getNetworkHandle() + \"}  \"  // long getNetworkHandle()  句柄?  Returns a handle representing this {@code Network}, for use with the NDK API.\n" +
                    "\t\t\t+ \"lp{\" + linkProperties + \"}  \"  // 连接速率 LinkProperties \n" +
                    "\t\t\t+ \"nc{\" + networkCapabilities      //   打印 NetworkCapabilities   bit比特功能位 \n" +
                    "\t\t\t+ \"}  Score{\" + getCurrentScore() + \"}  \"    // int getCurrentScore() {}  获取当前网络的分数  Get the current score for this Network.\n" +
                    "\t\t\t+ \"everValidated{\" + everValidated + \"} \"  // boolean 是否曾经验证过\n" +
                    "            + \"lastValidated{\" + lastValidated + \"}  \"   // boolean 上一次验证是否通过\n" +
                    "\t\t\t+ \"created{\" + created + \"}\"     // boolean 是否创建网络\n" +
                    "\t\t\t+\" lingering{\" + isLingering() + \"} \"  //   boolean mLingering;    是否逗留在该网络\n" +
                    "\t\t\t\n" +
                    "\t\t\t// boolean explicitlySelected; 如果用户通过第三方软件 设置 等 手动显式的连接到的网络  那么该值为 true\n" +
                    "\t\t\t+ \"explicitlySelected{\" + networkMisc.explicitlySelected + \"} \" \n" +
                    "\t\t\t+ \"acceptUnvalidated{\" + networkMisc.acceptUnvalidated + \"} \" // 未经过验证仍然使用该网络的标识 \n" +
                    "\t\t\t+ \"allowInvalidation{\" + networkMisc.allowInvalidation + \"} \"  // 是否允许无效? \n" +
                    "\t\t\t\n" +
                    "\t\t\t+ \"everCaptivePortalDetected{\" + everCaptivePortalDetected + \"} \"  // 是否曾经检测到该网络是一个portal的网络\n" +
                    "\t\t\t+ \"lastCaptivePortalDetected{\" + lastCaptivePortalDetected + \"} \" // 上一次是否检测到该网络是一个portal的网络\n" +
                    "\t\t\t+ \"captivePortalValidationPending{\" + captivePortalValidationPending + \"} \"  // 是否已打开UI 进行验证\n" +
                    "\t\t\t+ \"partialConnectivity{\" + partialConnectivity + \"} \"   //  部分连接? \n" +
                    "\t\t\t+ \"acceptPartialConnectivity{\" + networkMisc.acceptPartialConnectivity + \"} \"  //  是否显式的认证?即使部门连接\n" +
                    "\t\t\t+ \"clat{\" + clatd + \"} \"\n" +
                    "\t\t\t+ \"}\";\n" +
                    "**************** NetworkAgentInfo End ****************\n" +
                    "\n" +
                    "ConnectivityService   doDump() => ConnectivityService.LegacyTypeTracker.dump()\n" +
                    "  \n" +
                    "static class ConnectivityService.LegacyTypeTracker {\n" +
                    "   /**\n" +
                    "\t * Array of lists, one per legacy network type (e.g., TYPE_MOBILE_MMS).\n" +
                    "\t * Each list holds references to all NetworkAgentInfos that are used to\n" +
                    "\t * satisfy requests for that network type.\n" +
                    "\t *\n" +
                    "\t * This array is built out at startup such that an unsupported network\n" +
                    "\t * doesn't get an ArrayList instance, making this a tristate:\n" +
                    "\t * unsupported, supported but not active and active.\n" +
                    "\t *  不支持, 支持&不活跃 , 活跃\n" +
                    "\t * The actual lists are populated when we scan the network types that\n" +
                    "\t * are supported on this device.\n" +
                    "\t *\n" +
                    "\t * Threading model:\n" +
                    "\t *  - addSupportedType() is only called in the constructor\n" +
                    "\t *  - add(), update(), remove() are only called from the ConnectivityService handler thread.\n" +
                    "\t *    They are therefore not thread-safe with respect to each other.\n" +
                    "\t *  - getNetworkForType() can be called at any time on binder threads. It is synchronized\n" +
                    "\t *    on mTypeLists to be thread-safe with respect to a concurrent remove call.\n" +
                    "\t *  - dump is thread-safe with respect to concurrent add and remove calls.\n" +
                    "\t */\n" +
                    "// \t                                                 // 队0   ArrayList<NetworkAgentInfo>\n" +
                    "// ArrayList<NetworkAgentInfo> mTypeLists[] ==》\t // 队1   ArrayList<NetworkAgentInfo>\n" +
                    "// \t                                                 // 队2   ArrayList<NetworkAgentInfo>\n" +
                    "\tprivate final ArrayList<NetworkAgentInfo> mTypeLists[];   //  网络类型对象数组  数组的数组\n" +
                    "\t\n" +
                    "        public void dump(IndentingPrintWriter pw) {\n" +
                    "            pw.println(\"mLegacyTypeTracker:\");\n" +
                    "            pw.increaseIndent();\n" +
                    "            pw.print(\"Supported types:\");\n" +
                    "            for (int type = 0; type < mTypeLists.length; type++) {\n" +
                    "                if (mTypeLists[type] != null) pw.print(\" \" + type);  // 打印网络类型对象列表中不为空 那个队伍的索引\n" +
                    "            }\n" +
                    "            pw.println();\n" +
                    "            pw.println(\"Current state:\");\n" +
                    "            pw.increaseIndent();\n" +
                    "            synchronized (mTypeLists) {\n" +
                    "                for (int type = 0; type < mTypeLists.length; type++) {\n" +
                    "                    if (mTypeLists[type] == null || mTypeLists[type].isEmpty()) continue;\n" +
                    "\t\t\t\t\t//  相当于  for(NetworkAgentInfo nai : ArrayList<NetworkAgentInfo> )  打印 该队列下的每一个 NetworkAgentInfo\n" +
                    "                    for (NetworkAgentInfo nai : mTypeLists[type]) {  \n" +
                    "                        pw.println(type + \" \" + naiToString(nai));\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            }\n" +
                    "            pw.decreaseIndent();\n" +
                    "            pw.decreaseIndent();\n" +
                    "            pw.println();\n" +
                    "        }\n" +
                    "\n" +
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
                    "// ConnectivityManager.TYPE_MOBILE_FOTA = 10;  // Over the air Administration.  卡刷包\n" +
                    "// ConnectivityManager.TYPE_MOBILE_IMS  = 11;  //  IP Multimedia Subsystem. 多媒体子系统\n" +
                    "// ConnectivityManager.TYPE_MOBILE_CBS  = 12;  位置信息? \n" +
                    "// ConnectivityManager.TYPE_WIFI_P2P    = 13;\n" +
                    "// ConnectivityManager.TYPE_MOBILE_IA = 14;  // NetworkCapabilities#NET_CAPABILITY_IA\n" +
                    "// ConnectivityManager.TYPE_MOBILE_EMERGENCY = 15; 紧急转态\n" +
                    "// ConnectivityManager.TYPE_PROXY = 16; 代理\n" +
                    "// ConnectivityManager.TYPE_VPN = 17;  VPN 连接\n" +
                    "\t\n" +
                    "LOG示例:\n" +
                    "mLegacyTypeTracker:\n" +
                    "  Supported types: 0 1 2 3 4 5 7 9 10 11 12 15 17\n" +
                    "  Current state:\n" +
                    "  ";
            keyWordList.add(connectivity_1_6);

            KeyWordItem connectivity_1_7 = new KeyWordItem();
            connectivity_1_7.keyword = "Tethering:";
            connectivity_1_7.explain="手机热点信息: ";
            connectivity_1_7.classNameForShuxing = " ConnectivityService->  Tethering mTethering;  ";
            connectivity_1_7.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){  mTethering.dump(fd, pw, args); }";
            connectivity_1_7.printLogUrl="ConnectivityService - >  mTethering.dump(fd, pw, args); ";
            connectivity_1_7.expain1="******************************* EntitlementManager Begin  *******************************\n" +
                    "public class EntitlementManager {\n" +
                    "\n" +
                    "\n" +
                    "boolean mCellularUpstreamPermitted = true;\n" +
                    "\n" +
                    "// The ArraySet contains enabled downstream types, ex:\n" +
                    "// {@link ConnectivityManager.TETHERING_WIFI}\n" +
                    "// {@link ConnectivityManager.TETHERING_USB}\n" +
                    "// {@link ConnectivityManager.TETHERING_BLUETOOTH}\n" +
                    "private final ArraySet<Integer> mCurrentTethers;\n" +
                    "\t\n" +
                    "// Key: ConnectivityManager.TETHERING_*(downstream).\n" +
                    "// Value: ConnectivityManager.TETHER_ERROR_{NO_ERROR or PROVISION_FAILED}(provisioning result).\n" +
                    "private final SparseIntArray mCellularPermitted;\n" +
                    "\t\n" +
                    "    public void dump(PrintWriter pw) {\n" +
                    "        pw.print(\"mCellularUpstreamPermitted: \");\n" +
                    "        pw.println(mCellularUpstreamPermitted);\n" +
                    "        for (Integer type : mCurrentTethers) {\n" +
                    "            pw.print(\"Type: \");\n" +
                    "            pw.print(typeString(type));\n" +
                    "            if (mCellularPermitted.indexOfKey(type) > -1) {\n" +
                    "                pw.print(\", Value: \");\n" +
                    "                pw.println(errorString(mCellularPermitted.get(type)));\n" +
                    "            } else {\n" +
                    "                pw.println(\", Value: empty\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "******************************* EntitlementManager End  *******************************\n" +
                    "\n" +
                    "\n" +
                    "******************************* TetheringConfiguration Begin  *******************************\n" +
                    "public class TetheringConfiguration {\n" +
                    "  \n" +
                    "  final int subId;   // identify 唯一标识\n" +
                    "   String[] tetherableUsbRegexs;  // [rndis\\d]  使用的通过USB共享热点的接口名称  RNDIS实际上就是TCP/IP over USB\n" +
                    " final String[] tetherableWifiRegexs;  //[wigig0, wlan0, softap0]   通过WIFI共享热点的  接口名称\n" +
                    " final String[] tetherableBluetoothRegexs;  //[bnep\\d, bt-pan]   通过蓝牙BT 共享热点的  接口名称\n" +
                    " final boolean isDunRequired; // 是否需要绑定网络?   有些机器可能有必须绑指定某种类型的网络 才能共享成功\n" +
                    " final boolean chooseUpstreamAutomatically;  // 自动选择上行?\n" +
                    " final Collection<Integer> preferredUpstreamIfaceTypes;  // 上传网络热点的 原始网络类型 对应  ConnectivityManager.TYPE_WIFI\n" +
                    " // preferredUpstreamIfaceTypes: [ETHERNET, MOBILE, WIFI, MOBILE_HIPRI, BLUETOOTH]\n" +
                    " final String[] legacyDhcpRanges;  // 客户端分配IP地址的范围   两个一组, [start1,end1,start2,end2,start3,end3......]\n" +
                    "//   legacyDhcpRanges: [192.168.42.2, 192.168.42.254, 192.168.43.2, 192.168.43.254, 192.168.44.2, 192.168.44.254, 192.168.45.2, 192.168.45.254, 192.168.46.2, 192.168.46.254, 192.168.47.2, 192.168.47.254, 192.168.48.2, 192.168.48.254, 192.168.49.2, 192.168.49.254, 192.168.50.2, 192.168.50.254, 192.168.51.2, 192.168.51.254]\n" +
                    "  String[] defaultIPv4DNS;  //  默认的 DNS对应的 IPv4 的 服务器的网络地址 \n" +
                    " //     defaultIPv4DNS: [8.8.4.4, 8.8.8.8] \n" +
                    " boolean enableLegacyDhcpServer;  // DHCP服务是否可以用户配置?\n" +
                    " \n" +
                    "String[] provisioningApp;  // APP名称? \n" +
                    "final String provisioningAppNoUi;  // 未知?\n" +
                    "   \n" +
                    "    public void dump(PrintWriter pw) {\n" +
                    "        pw.print(\"subId: \");\n" +
                    "        pw.println(subId);  // identify 唯一标识\n" +
                    "\n" +
                    "        dumpStringArray(pw, \"tetherableUsbRegexs\", tetherableUsbRegexs);\n" +
                    "        dumpStringArray(pw, \"tetherableWifiRegexs\", tetherableWifiRegexs);\n" +
                    "        dumpStringArray(pw, \"tetherableBluetoothRegexs\", tetherableBluetoothRegexs);\n" +
                    "\n" +
                    "        pw.print(\"isDunRequired: \");\n" +
                    "        pw.println(isDunRequired); // 是否需要绑定网络?  来自 TelephonyManager .getTetherApnRequired()\n" +
                    "\n" +
                    "        pw.print(\"chooseUpstreamAutomatically: \");\n" +
                    "        pw.println(chooseUpstreamAutomatically);  // 自动上传?   来自getResourceBoolean(res, config_tether_upstream_automatic); \n" +
                    "    \n" +
                    "    dumpStringArray(pw, \"preferredUpstreamIfaceTypes\", preferredUpstreamNames(preferredUpstreamIfaceTypes));\n" +
                    "\n" +
                    "        dumpStringArray(pw, \"legacyDhcpRanges\", legacyDhcpRanges);  // 客户端分配的IP地址范围\n" +
                    "        dumpStringArray(pw, \"defaultIPv4DNS\", defaultIPv4DNS);  //  默认的 DNS对应的 IPv4 的 服务器的网络地址 \n" +
                    "\n" +
                    "        dumpStringArray(pw, \"provisioningApp\", provisioningApp);\n" +
                    "        pw.print(\"provisioningAppNoUi: \");\n" +
                    "        pw.println(provisioningAppNoUi);\n" +
                    "\n" +
                    "        pw.print(\"enableLegacyDhcpServer: \");\n" +
                    "\t\t// 来自 Settings.Global.getInt(cr, TETHER_ENABLE_LEGACY_DHCP_SERVER, 0);  \n" +
                    "\t\t// adb shell settings get global tether_enable_legacy_dhcp_server\n" +
                    "        pw.println(enableLegacyDhcpServer);  // DHCP服务是否可以用户配置? \n" +
                    "    }\n" +
                    "******************************* TetheringConfiguration End  *******************************\n" +
                    "\n" +
                    "\n" +
                    "public class Tethering extends BaseNetworkObserver {\n" +
                    "\n" +
                    " private volatile TetheringConfiguration mConfig;  // 热点的配置类\n" +
                    " \n" +
                    "  private final EntitlementManager mEntitlementMgr;  // 权限管理类\n" +
                    "  \n" +
                    "    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "        // Binder.java closes the resource for us.\n" +
                    "        @SuppressWarnings(\"resource\")\n" +
                    "        final IndentingPrintWriter pw = new IndentingPrintWriter(writer, \"  \");\n" +
                    "        if (!DumpUtils.checkDumpPermission(mContext, TAG, pw)) return;\n" +
                    "\n" +
                    "        pw.println(\"Tethering:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "\n" +
                    "        pw.println(\"Configuration:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        final TetheringConfiguration cfg = mConfig;\n" +
                    "        cfg.dump(pw);  // 打印热点配置\n" +
                    "        pw.decreaseIndent();\n" +
                    "\n" +
                    "        pw.println(\"Entitlement:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        mEntitlementMgr.dump(pw); // 权限管理类 打印\n" +
                    "        pw.decreaseIndent();\n" +
                    "\t\t}\n" +
                    "}\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "Tethering:\n" +
                    "  Configuration:\n" +
                    "    subId: 3\n" +
                    "    tetherableUsbRegexs: [rndis\\d]\n" +
                    "    tetherableWifiRegexs: [wigig0, wlan0, softap0]\n" +
                    "    tetherableBluetoothRegexs: [bnep\\d, bt-pan]\n" +
                    "    isDunRequired: false\n" +
                    "    chooseUpstreamAutomatically: true\n" +
                    "    preferredUpstreamIfaceTypes: [ETHERNET, MOBILE, WIFI, MOBILE_HIPRI, BLUETOOTH]\n" +
                    "    legacyDhcpRanges: [192.168.42.2, 192.168.42.254, 192.168.43.2, 192.168.43.254, 192.168.44.2, 192.168.44.254, 192.168.45.2, 192.168.45.254, 192.168.46.2, 192.168.46.254, 192.168.47.2, 192.168.47.254, 192.168.48.2, 192.168.48.254, 192.168.49.2, 192.168.49.254, 192.168.50.2, 192.168.50.254, 192.168.51.2, 192.168.51.254]\n" +
                    "    defaultIPv4DNS: [8.8.4.4, 8.8.8.8]\n" +
                    "    provisioningApp: []\n" +
                    "    provisioningAppNoUi: \n" +
                    "    enableLegacyDhcpServer: false    【用户无法配置DHCP服务】\n" +
                    "  Entitlement:\n" +
                    "    mCellularUpstreamPermitted: true";
            keyWordList.add(connectivity_1_7);








            KeyWordItem connectivity_1_8 = new KeyWordItem();
            connectivity_1_8.keyword = "  Tether state:";
            connectivity_1_8.explain="网络管理的各个分管经理的集合?  ";
            connectivity_1_8.classNameForShuxing = "  ConnectivityService->  Tethering mTethering; ";
            connectivity_1_8.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_8.printLogUrl="";
            connectivity_1_8.expain1="public class Tethering extends BaseNetworkObserver {\n" +
                    " ArrayMap<String, TetherState> mTetherStates;  //  热点状态集合?\n" +
                    " \n" +
                    "     // TODO: Figure out how to merge this and other downstream-tracking objects into a single coherent structure.\n" +
                    "    private final HashSet<IpServer> mForwardedDownstreams;\n" +
                    "\n" +
                    "\t    // True iff. WiFi tethering should be started when soft AP is ready.\n" +
                    "    private boolean mWifiTetherRequested;\n" +
                    "\t\n" +
                    "\tprivate InterfaceSet mCurrentUpstreamIfaceSet;  // 当前可用上行链接接口集合\n" +
                    "\t\n" +
                    "\t final OffloadController mOffloadController;  // 分流控制\n" +
                    "\t \n" +
                    "    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "        synchronized (mPublicSync) {\n" +
                    "            pw.println(\"Tether state:\");\n" +
                    "            pw.increaseIndent();\n" +
                    "            for (int i = 0; i < mTetherStates.size(); i++) {\n" +
                    "                final String iface = mTetherStates.keyAt(i);  // 接口名称? \n" +
                    "                final TetherState tetherState = mTetherStates.valueAt(i);   // 热点状态\n" +
                    "                pw.print(iface + \" - \");\n" +
                    "\n" +
                    "                switch (tetherState.lastState) {\n" +
                    "                    case IpServer.STATE_UNAVAILABLE:  // 接口 无效\n" +
                    "                        pw.print(\"UnavailableState\");\n" +
                    "                        break;\n" +
                    "                    case IpServer.STATE_AVAILABLE: // 接口 有效\n" +
                    "                        pw.print(\"AvailableState\");\n" +
                    "                        break;\n" +
                    "                    case IpServer.STATE_TETHERED: // 已打开热点状态\n" +
                    "                        pw.print(\"TetheredState\");\n" +
                    "                        break;\n" +
                    "                    case IpServer.STATE_LOCAL_ONLY:  // 本地热点状态? \n" +
                    "                        pw.print(\"LocalHotspotState\");\n" +
                    "                        break;\n" +
                    "                    default:\n" +
                    "                        pw.print(\"UnknownState\");   // 未知状态\n" +
                    "                        break;\n" +
                    "                }\n" +
                    "                pw.println(\" - lastError = \" + tetherState.lastError);  // 错误值 \n" +
                    "            }\n" +
                    "            pw.println(\"Upstream wanted: \" + upstreamWanted());  // 是否已经打开 上行链接?\n" +
                    "            pw.println(\"Current upstream interface(s): \" + mCurrentUpstreamIfaceSet);  // 当前可用上行链接接口集合\n" +
                    "            pw.decreaseIndent();\n" +
                    "        }\n" +
                    "\n" +
                    "        pw.println(\"Hardware offload:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        mOffloadController.dump(pw);\n" +
                    "        pw.decreaseIndent();\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "    private boolean upstreamWanted() {\n" +
                    "        if (!mForwardedDownstreams.isEmpty()) return true;\n" +
                    "        synchronized (mPublicSync) {\n" +
                    "            return mWifiTetherRequested;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "\t\n" +
                    "LOG示例:\n" +
                    "  Tether state:\n" +
                    "    wlan0 - AvailableState - lastError = 0\n" +
                    "    Upstream wanted: false\n" +
                    "    Current upstream interface(s): null";
            keyWordList.add(connectivity_1_8);



            KeyWordItem connectivity_1_9 = new KeyWordItem();
            connectivity_1_9.keyword = "  Hardware offload:";
            connectivity_1_9.explain=" OffloadController 分流控制?  ";
            connectivity_1_9.classNameForShuxing = "  ConnectivityService->  Tethering mTethering - > OffloadController mOffloadController;  // 分流控制";
            connectivity_1_9.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){mTethering->OffloadController->dump() }";
            connectivity_1_9.printLogUrl="";
            connectivity_1_9.expain1="public class Tethering extends BaseNetworkObserver {\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "        pw.println(\"Hardware offload:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        mOffloadController.dump(pw);  // 分流管理?\n" +
                    "        pw.decreaseIndent();\n" +
                    "\t\t}\n" +
                    "}\n" +
                    "\t\n" +
                    "||\n" +
                    "\\/\t\n" +
                    "public class OffloadController {\n" +
                    "\n" +
                    " private LinkProperties mUpstreamLinkProperties;  // 上行速率\n" +
                    " \n" +
                    " private int mNatUpdateCallbacksReceived;  // NAT 地址回调个数\n" +
                    "private int mNatUpdateNetlinkErrors;   // NAT 链接错误个数\n" +
                    "\t\n" +
                    "    public void dump(IndentingPrintWriter pw) {\n" +
                    "\t// Settings.Global.getInt(mContentResolver, TETHER_OFFLOAD_DISABLED, defaultDisposition) != 0);\n" +
                    "\t// 是否支持分流\n" +
                    "        if (isOffloadDisabled()) {  \n" +
                    "            pw.println(\"Offload disabled\");\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        final boolean isStarted = started();\n" +
                    "        pw.println(\"Offload HALs \" + (isStarted ? \"started\" : \"not started\"));\n" +
                    "        LinkProperties lp = mUpstreamLinkProperties;\n" +
                    "        String upstream = (lp != null) ? lp.getInterfaceName() : null;\n" +
                    "        pw.println(\"Current upstream: \" + upstream);   // 上行接口\n" +
                    "        pw.println(\"Exempt prefixes: \" + mLastLocalPrefixStrs);\n" +
                    "        pw.println(\"NAT timeout update callbacks received during the \"\n" +
                    "                + (isStarted ? \"current\" : \"last\")  //  NAT 地址转换回调? \n" +
                    "                + \" offload session: \"\n" +
                    "                + mNatUpdateCallbacksReceived);\n" +
                    "        pw.println(\"NAT timeout update netlink errors during the \"\n" +
                    "                + (isStarted ? \"current\" : \"last\")\n" +
                    "                + \" offload session: \"\n" +
                    "                + mNatUpdateNetlinkErrors);\n" +
                    "    }\n" +
                    "}\n" +
                    "\t\n" +
                    "LOG示例:\n" +
                    "  Hardware offload:\n" +
                    "    Offload HALs not started\n" +
                    "    Current upstream: null\n" +
                    "    Exempt prefixes: []\n" +
                    "    NAT timeout update callbacks received during the last offload session: 0\n" +
                    "    NAT timeout update netlink errors during the last offload session: 0";
            keyWordList.add(connectivity_1_9);


            KeyWordItem connectivity_1_10 = new KeyWordItem();
            connectivity_1_10.keyword = "  Log:";
            connectivity_1_10.explain="Tethering的LOG   ";
            connectivity_1_10.classNameForShuxing = "  ConnectivityService->  Tethering mTethering -> SharedLog ";
            connectivity_1_10.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_10.printLogUrl="";
            connectivity_1_10.expain1="mLog.mark(\"constructed\");   // Tethering构造器打印\n" +
                    "mLog.log(\"OBSERVED UiEnitlementFailed\"); // UI授权失败?\n" +
                    "mLog.log(\"OBSERVED carrier config change\");  // 手机运营商配置 sim卡改变\n" +
                    "mLog.log(\"OBSERVED default data subscription change\");  // 默认订阅数据改变?\n" +
                    "mLog.log(\"IGNORED reevaluate provisioning due to no carrier config loaded\");  // 忽略重新评估网络 因为sim卡以拔出\n" +
                    "mLog.e(\"setWifiTethering: failed to get WifiManager!\");  //  获取 WifiManager 失败\n" +
                    "// 热点分流打开   上行链路是否为空     upstreamShared 是否共享上行数据\n" +
                    "mLog.log(\"sendTetherOffloadInfoBroadcast:offload.started=\"+mOffloadController.started()+\",ForwardedDownstreams=\" + !mForwardedDownstreams.isEmpty() +\",upstreamShared=\" + upstreamSharedInternet);\n" +
                    "mLog.log(\"OBSERVED configuration changed\");  // 热点配置更改\n" +
                    "mLog.log(String.format(\"USB bcast connected:%s configured:%s rndis:%s\",usbConnected, usbConfigured, rndisEnabled)); // USB连接\n" +
                    "mLog.log(\"Canceling WiFi tethering request - AP_STATE=\" + apState); // 取消热点请求  当前请求的热点状态\n" +
                    "// 热点关闭失败  没有找到对应的WIFI接口名称\n" +
                    "mLog.log(\"Error disabling Wi-Fi IP serving; \" +(TextUtils.isEmpty(ifname) ? \"no interface name specified\": \"specified interface: \" + ifname)); \n" +
                    "mLog.e(\"Cannot enable IP serving in unknown WiFi mode: \" + wifiIpMode);  // 未知的 WIFIIp状态  IFACE_IP_MODE_LOCAL_ONLY: IFACE_IP_MODE_TETHERED:\n" +
                    "mLog.e(String.format(\"Cannot enable IP serving in mode %s on missing interface name\",ipServingMode)); // 没有找到对应的接口\n" +
                    "mLog.e(\"setUsbTethering: failed to get UsbManager!\");  // 获取 UsbManager 失败\n" +
                    "mLog.log(state.getName() + \" got \" + sMagicDecoderRing.get(what, Integer.toString(what)));  // 状态 获取到了说明消息\n" +
                    "mLog.log(\"SET master tether settings: ON\");  // 热点打开\n" +
                    "mLog.log(\"SET master tether settings: OFF\"); // 热点关闭\n" +
                    "mLog.i(\"Looking for default routes on: \" + ns.linkProperties);  // 默认链接\n" +
                    "mLog.i(\"Found upstream interface(s): \" + ifaces);  // 上行接口\n" +
                    "mLog.log(String.format(\"SET DNS forwarders: network=%s dnsServers=%s\", network, Arrays.toString(dnsServers))); // 设置DNS服务器\n" +
                    "mLog.e(\"setting DNS forwarders failed, \" + e); // 设置 DNS 服务器 失败\n" +
                    "mLog.e(\"Unknown arg1 value: \" + arg1);  // 未知的上行回调参数\n" +
                    "mLog.log(\"Broadcast \"+ msg + \" in \" + intent);  // 发送广播intent  并携带  msg\n" +
                    "mLog.log(String.format(\"OBSERVED iface=%s state=%s error=%s\", iface, state, error)); // 发生接口状态改变 改变的reason\n" +
                    "mLog.log(\"got notification from stale iface \" + iface);  // 接收到旧接口的 消息?\n" +
                    "// 链接状态改变 \n" +
                    "mLog.log(String.format(\"OBSERVED LinkProperties update iface=%s state=%s lp=%s\",iface, IpServer.getStateString(state), newLp));\n" +
                    "mLog.log(iface + \" is not a tetherable iface, ignoring\");   // 当前接口不是一个可以当做热点接口的 接口\n" +
                    "mLog.log(\"active iface (\" + iface + \") reported as added, ignoring\");  // 当前接口已经添加 \n" +
                    "mLog.log(\"adding TetheringInterfaceStateMachine for: \" + iface); //  添加热点接口状态机\n" +
                    "mLog.log(\"removing TetheringInterfaceStateMachine for: \" + iface);  //  移除 热点的状态机\n" +
                    "public class Tethering extends BaseNetworkObserver {\n" +
                    "\n" +
                    "final SharedLog mLog = new SharedLog(TAG);\n" +
                    "\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "        pw.println(\"Log:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        if (argsContain(args, SHORT_ARG)) {\n" +
                    "            pw.println(\"<log removed for brevity>\");\n" +
                    "        } else {\n" +
                    "            mLog.dump(fd, pw, args);   // 打印LOG \n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n" +
                    "LOG示例:\n" +
                    "SET master tether settings: ON  // 热点打开\n" +
                    "SET master tether settings: OFF // 热点关闭";
            keyWordList.add(connectivity_1_10);


            KeyWordItem connectivity_1_11 = new KeyWordItem();
            connectivity_1_11.keyword = "Supported Socket keepalives:";
            connectivity_1_11.explain="各种传输类型的允许保持的活跃 socket的数量  ";
            connectivity_1_11.classNameForShuxing = "  ConnectivityService->  Tethering mTethering -> KeepaliveTracker";
            connectivity_1_11.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_11.printLogUrl="";
            connectivity_1_11.expain1="public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    "    private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "        pw.println();\n" +
                    "        mKeepaliveTracker.dump(pw);\n" +
                    "        pw.println();\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "||\n" +
                    "\\/\n" +
                    "\n" +
                    "/frameworks/base/services/core/java/com/android/server/connectivity/KeepaliveTracker.java\n" +
                    "\n" +
                    "public class KeepaliveTracker {\n" +
                    "\n" +
                    "// Supported keepalive count for each transport type, can be configured through\n" +
                    "// config_networkSupportedKeepaliveCount. For better error handling, use\n" +
                    "// {@link getSupportedKeepalivesForNetworkCapabilities} instead of direct access.\n" +
                    "// String[]   res = context.getResources().getStringArray( R.array.config_networkSupportedKeepaliveCount);\n" +
                    "//    <string-array translatable=\"false\" name=\"config_networkSupportedKeepaliveCount\">\n" +
                    "//        <item>0,1</item>  // 第1位0= int  transport_type  ;  第2位1= int  supported\n" +
                    "//        <item>1,3</item> // 第1位1=int  transport_type  ;  第2位3= int  supported\n" +
                    "//    </string-array>\t\n" +
                    "//    private static final String[] TRANSPORT_NAMES = {\n" +
                    "//        \"CELLULAR\", transport_type==0 \n" +
                    "//        \"WIFI\",   transport_type==1\n" +
                    "//        \"BLUETOOTH\",transport_type==2\n" +
                    "//        \"ETHERNET\", transport_type==3\n" +
                    "//        \"VPN\",transport_type==4\n" +
                    "//        \"WIFI_AWARE\",transport_type==5\n" +
                    "//        \"LOWPAN\",transport_type==6\n" +
                    "//        \"TEST\"transport_type==7\n" +
                    "//    };\n" +
                    "    private final int[] mSupportedKeepalives;  \n" +
                    "\t\n" +
                    "// Reserved privileged keepalive slots per transport. Caller's permission will be enforced if\n" +
                    "// the number of remaining keepalive slots is less than or equal to the threshold.\n" +
                    "private final int mReservedPrivilegedSlots;  // 保留的特权 类型数量? \n" +
                    "\t\n" +
                    "// Allowed unprivileged keepalive slots per uid. Caller's permission will be enforced if\n" +
                    "// the number of remaining keepalive slots is less than or equal to the threshold.\n" +
                    "private final int mAllowedUnprivilegedSlotsForUid;\n" +
                    "\n" +
                    "    /** Keeps track of keepalive requests.  保存一直活跃的请求 */\n" +
                    "    private final HashMap <NetworkAgentInfo, HashMap<Integer, KeepaliveInfo>> mKeepalives = new HashMap<> ();\n" +
                    "\t\t\t\n" +
                    "    public void dump(IndentingPrintWriter pw) {\n" +
                    "        pw.println(\"Supported Socket keepalives: \" + Arrays.toString(mSupportedKeepalives));  // 支持的保持socket的类型\n" +
                    "        pw.println(\"Reserved Privileged keepalives: \" + mReservedPrivilegedSlots);// 保留的特权 类型数量? \n" +
                    "        pw.println(\"Allowed Unprivileged keepalives per uid: \" + mAllowedUnprivilegedSlotsForUid);\n" +
                    "        pw.println(\"Socket keepalives:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        for (NetworkAgentInfo nai : mKeepalives.keySet()) {\n" +
                    "            pw.println(nai.name());   // 网络类型名称\n" +
                    "            pw.increaseIndent();\n" +
                    "            for (int slot : mKeepalives.get(nai).keySet()) {\n" +
                    "                KeepaliveInfo ki = mKeepalives.get(nai).get(slot);  //  打印 KeepaliveInfo\n" +
                    "                pw.println(slot + \": \" + ki.toString());   // 索引 --  KeepaliveInfo\n" +
                    "            }\n" +
                    "            pw.decreaseIndent();\n" +
                    "        }\n" +
                    "        pw.decreaseIndent();\n" +
                    "    }\n" +
                    "\t\n" +
                    "LOG示例:   // 索引对应的就是 传输的类型    值对应的就是支持的 keepalalives的个数 支持活跃socket的个数\n" +
                    "Supported Socket keepalives: [1【0】,             3【1】,          0【2】,  0【3】,        0【4】,     0【5】,                0【6】,     0【7】]\n" +
                    "Supported Socket keepalives:[ \"CELLULAR【0】\",\"WIFI【1】\",\"BLUETOOTH【2】\",\"ETHERNET【3】\",\"VPN【4】\",\"WIFI_AWARE【5】\",\"LOWPAN【6】\",\"TEST【7】\"]\n" +
                    "Reserved Privileged keepalives: 2\n" +
                    "Allowed Unprivileged keepalives per uid: 2\n" +
                    "Socket keepalives:\n";
            keyWordList.add(connectivity_1_11);



            KeyWordItem connectivity_1_12 = new KeyWordItem();
            connectivity_1_12.keyword = "Bad Wi-Fi avoidance:";
            connectivity_1_12.explain=" 退出 差 WIFI？ 避免差WIFI？ =》 adb shell settings get global network_avoid_bad_wifi   ";
            connectivity_1_12.classNameForShuxing = "  ConnectivityService->  MultinetworkPolicyTracker.java ->  mAvoidBadWifi ";
            connectivity_1_12.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_12.printLogUrl="";
            connectivity_1_12.expain1="public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    "    private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "        pw.println();\n" +
                    "        dumpAvoidBadWifiSettings(pw);\n" +
                    "}\n" +
                    "\n" +
                    "||\n" +
                    "\\/\n" +
                    "\n" +
                    " // TODO: Evaluate whether this is of interest to other consumers of     // MultinetworkPolicyTracker and worth moving out of here.\n" +
                    "\n" +
                    "private void dumpAvoidBadWifiSettings(IndentingPrintWriter pw) {\n" +
                    "\t//   return (mContext.getResources().getInteger(R.integer.config_networkAvoidBadWifi) == 0);   \n" +
                    "\t//R.integer.config_networkAvoidBadWifi    0--true--禁用badwifi    1--false--不禁用badwifi\n" +
                    "        final boolean configRestrict = mMultinetworkPolicyTracker.configRestrictsAvoidBadWifi();\n" +
                    "        if (!configRestrict) {  // 是否打开 Bad_Wi-Fi_avoidance 功能 \n" +
                    "            pw.println(\"Bad Wi-Fi avoidance: unrestricted\");  // true --- 打开(约束)   false--不打开(约束)\n" +
                    "            return;\n" +
                    "        }\n" +
                    "// MultinetworkPolicyTracker.java ->  mAvoidBadWifi = true;  是否使能   , 默认为true,\n" +
                    "// Settings.Global.getString(mResolver, NETWORK_AVOID_BAD_WIFI);  adb shell settings get global network_avoid_bad_wifi\n" +
                    "        pw.println(\"Bad Wi-Fi avoidance: \" + avoidBadWifi());  \n" +
                    "        pw.increaseIndent();\n" +
                    "        pw.println(\"Config restrict:   \" + configRestrict);  // 是否禁用 \n" +
                    "\n" +
                    "\t\t // 获取设置值   Settings.Global.getString(mResolver, NETWORK_AVOID_BAD_WIFI); \n" +
                    "        final String value = mMultinetworkPolicyTracker.getAvoidBadWifiSetting(); \n" +
                    "        String description;\n" +
                    "        // Can't use a switch statement because strings are legal case labels, but null is not.\n" +
                    "        if (\"0\".equals(value)) {\n" +
                    "            description = \"get stuck\"; // 禁用 avoid_bad_wifi\n" +
                    "        } else if (value == null) {\n" +
                    "            description = \"prompt\";   // 未设置 \n" +
                    "        } else if (\"1\".equals(value)) {\n" +
                    "            description = \"avoid\";    // 使能 avoid_bad_wifi\n" +
                    "        } else {\n" +
                    "            description = value + \" (?)\";\n" +
                    "        }\n" +
                    "        pw.println(\"User setting:      \" + description);\n" +
                    "        pw.println(\"Network overrides:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        for (NetworkAgentInfo nai : networksSortedById()) {  \n" +
                    "            if (nai.avoidUnvalidated) {  \n" +
                    "                pw.println(nai.name());   // 打印 使能 avoid_badwifi的 NetworkAgentInfo 信息\n" +
                    "            }\n" +
                    "        }\n" +
                    "        pw.decreaseIndent();\n" +
                    "        pw.decreaseIndent();\n" +
                    "    }\n" +
                    "LOG示例:  \n" +
                    "Bad Wi-Fi avoidance: unrestricted   ( 禁用 Bad Wi-Fi avoidance ) \n" +
                    "Bad Wi-Fi avoidance: true  (   使能 Bad Wi-Fi avoidance  不禁用 )\t\n" +
                    "Bad Wi-Fi avoidance: false ( 不使能 Bad Wi-Fi avoidance  不禁用 )\t";
            keyWordList.add(connectivity_1_12);


            KeyWordItem connectivity_1_13 = new KeyWordItem();
            connectivity_1_13.keyword = "MultipathPolicyTracker:";
            connectivity_1_13.explain=" 多路径网络追踪?  ";
            connectivity_1_13.classNameForShuxing = "  ConnectivityService->  MultipathPolicyTracker mMultipathPolicyTracker;";
            connectivity_1_13.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_13.printLogUrl="";
            connectivity_1_13.expain1="public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    " MultipathPolicyTracker mMultipathPolicyTracker;  // 策略追踪?\n" +
                    " \n" +
                    "private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "pw.println();\n" +
                    "mMultipathPolicyTracker.dump(pw);\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "||\n" +
                    "\\/\n" +
                    "\n" +
                    "\n" +
                    "public class MultipathPolicyTracker {\n" +
                    "\n" +
                    "// Only ever updated on the handler thread. Accessed from other binder threads to retrieve the tracker for a specific network.\n" +
                    " ConcurrentHashMap <Network, MultipathTracker> mMultipathTrackers = new ConcurrentHashMap<>();\n" +
                    "//  不懂！\n" +
                    "public void dump(IndentingPrintWriter pw) {\n" +
                    "\t// Do not use in production. Access to class data is only safe on the handler thrad.\n" +
                    "\tpw.println(\"MultipathPolicyTracker:\");\n" +
                    "\tpw.increaseIndent();\n" +
                    "\tfor (MultipathTracker t : mMultipathTrackers.values()) {\n" +
                    "\t\tpw.println(String.format(\"Network %s: quota %d, budget %d. Preference: %s\",\n" +
                    "\t\t\t\tt.network, t.getQuota(), t.getMultipathBudget(),\n" +
                    "\t\t\t\tDebugUtils.flagsToString(ConnectivityManager.class, \"MULTIPATH_PREFERENCE_\",\n" +
                    "\t\t\t\t\t\tt.getMultipathPreference())));\n" +
                    "\t}\n" +
                    "\tpw.decreaseIndent();\n" +
                    "}\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "MultipathPolicyTracker:";
            keyWordList.add(connectivity_1_13);


            KeyWordItem connectivity_1_14 = new KeyWordItem();
            connectivity_1_14.keyword = "mNetworkRequestInfoLogs";
            connectivity_1_14.explain="NetworkRequestInfoLogs 集合  释放 Release NetworkRequestInfo  和 注册 REGISTER NetworkRequestInfo 的LOG ";
            connectivity_1_14.classNameForShuxing = "  ConnectivityService->  LocalLog mNetworkRequestInfoLogs";
            connectivity_1_14.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_14.printLogUrl="";
            connectivity_1_14.expain1="********************* LocalLog.reverseDump begin*********************\n" +
                    "public final class LocalLog {\n" +
                    "\n" +
                    "    private final Deque<String> mLog;\n" +
                    "\t\n" +
                    "    public synchronized void reverseDump(PrintWriter pw) {\n" +
                    "        Iterator<String> itr = mLog.descendingIterator();  // 逆序 \n" +
                    "        while (itr.hasNext()) {\n" +
                    "            pw.println(itr.next());\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "********************* LocalLog.reverseDump end*********************\n" +
                    "public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    "static final int MAX_NETWORK_REQUEST_LOGS = 20;\n" +
                    "LocalLog mNetworkRequestInfoLogs = new LocalLog(MAX_NETWORK_REQUEST_LOGS);  // LOG 日志\n" +
                    "\n" +
                    "private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "\tpw.println();\n" +
                    "\tpw.println(\"mNetworkRequestInfoLogs (most recent first):\");\n" +
                    "\tpw.increaseIndent();\n" +
                    "\tmNetworkRequestInfoLogs.reverseDump(fd, pw, args);  // 打印当前保存的所有LOG\n" +
                    "\tpw.decreaseIndent();\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "NetworkRequestInfo defaultNRI = new NetworkRequestInfo(null, mDefaultRequest, new Binder());\n" +
                    "mNetworkRequestInfoLogs.log(\"REGISTER \" + defaultNRI);   // 注册默认的  NetworkRequestInfo\n" +
                    "\n" +
                    "handleRegisterNetworkRequest(NetworkRequestInfo nri) {\n" +
                    "mNetworkRequestInfoLogs.log(\"REGISTER \" + nri);   // 运行时注册的   NetworkRequestInfo\n" +
                    "}\n" +
                    "\n" +
                    "private void handleRemoveNetworkRequest(final NetworkRequestInfo nri) {\n" +
                    "mNetworkRequestInfoLogs.log(\"RELEASE \" + nri);  // 运行时移除的   NetworkRequestInfo\n" +
                    "}\n" +
                    "LOG示例:\n" +
                    "2019-12-20T19:41:57.970 - REGISTER 【 注册NetworkRequestInfo 】 uid/pid:1000/7278 NetworkRequest [ LISTEN id=80, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 1000] ]\n" +
                    "2019-12-20T19:41:57.917 - REGISTER uid/pid:1000/7278 NetworkRequest [ LISTEN id=79, [ Capabilities: FOREGROUND Uid: 1000] ]\n" +
                    "2019-12-20T19:41:57.913 - REGISTER uid/pid:1000/7278 NetworkRequest [ LISTEN id=78, [ Transports: WIFI Capabilities: NOT_VPN&FOREGROUND] ]\n" +
                    "2019-12-20T19:41:57.904 - REGISTER uid/pid:1000/7278 NetworkRequest [ LISTEN id=77, [ Transports: WIFI Capabilities: NOT_VPN&FOREGROUND] ]\n" +
                    "2019-12-20T19:41:57.875 - REGISTER uid/pid:1000/7278 NetworkRequest [ TRACK_DEFAULT id=76, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 1000] ]\n" +
                    "2019-12-20T19:38:05.814 - RELEASE 【 释放NetworkRequestInfo 】  uid/pid:1000/7278 NetworkRequest [ LISTEN id=72, [ Transports: WIFI Capabilities: NOT_VPN&FOREGROUND] ]\n";
            keyWordList.add(connectivity_1_14);


            KeyWordItem connectivity_1_15 = new KeyWordItem();
            connectivity_1_15.keyword = "mNetworkInfoBlockingLogs";
            connectivity_1_15.explain="当前UID 的 NetworkRequestInfo  阻塞情况";
            connectivity_1_15.classNameForShuxing = "  ConnectivityService-> LocalLog mNetworkInfoBlockingLogs ";
            connectivity_1_15.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_15.printLogUrl="";
            connectivity_1_15.expain1="public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    "\n" +
                    "// NetworkInfo blocked and unblocked String log entries\n" +
                    "private static final int MAX_NETWORK_INFO_LOGS = 40;\n" +
                    "private final LocalLog mNetworkInfoBlockingLogs = new LocalLog(MAX_NETWORK_INFO_LOGS);\n" +
                    "\n" +
                    "\n" +
                    "private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "            pw.println();\n" +
                    "            pw.println(\"mNetworkInfoBlockingLogs (most recent first):\");   // 阻塞的LOG? \n" +
                    "            pw.increaseIndent();\n" +
                    "            mNetworkInfoBlockingLogs.reverseDump(fd, pw, args);\n" +
                    "            pw.decreaseIndent();\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "    public enum DetailedState {\n" +
                    "        IDLE,   /** Ready to start data connection setup. */\n" +
                    "        SCANNING,  /** Searching for an available access point. */\n" +
                    "        CONNECTING,      /** Currently setting up data connection. */\n" +
                    "        AUTHENTICATING,        /** Network link established, performing authentication. */\n" +
                    "        OBTAINING_IPADDR,     /** Awaiting response from DHCP server in order to assign IP address information. */\n" +
                    "        CONNECTED,     /** IP traffic should be available. */\n" +
                    "        SUSPENDED,    /** IP traffic is suspended */\n" +
                    "        DISCONNECTING,       /** Currently tearing down data connection. */\n" +
                    "        DISCONNECTED,       /** IP traffic not available. */\n" +
                    "        FAILED,  /** Attempt to connect failed. */\n" +
                    "        BLOCKED,   /** Access to this network is blocked. 由于策略导致访问网站被阻塞 无法访问 */\n" +
                    "        VERIFYING_POOR_LINK,    /** Link has poor connectivity. */\n" +
                    "        CAPTIVE_PORTAL_CHECK     /** Checking if network is a captive portal */\n" +
                    "    }\n" +
                    "\t\n" +
                    "private void maybeLogBlockedNetworkInfo(NetworkInfo ni, int uid) {\n" +
                    "NetworkInfo.getDetailedState() == DetailedState.BLOCKED  // Access to this network is blocked 此网络的访问被阻塞\n" +
                    "String action = blocked ? \"BLOCKED\" : \"UNBLOCKED\";\n" +
                    "log(String.format(\"Returning %s NetworkInfo to uid=%d\", action, uid));\n" +
                    "mNetworkInfoBlockingLogs.log(action + \" \" + uid);  //  当前网络是否被阻塞的网络  uid APPID\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "private void maybeLogBlockedStatusChanged(NetworkRequestInfo nri, Network net, boolean blocked) {\n" +
                    "String action = blocked ? \"BLOCKED\" : \"UNBLOCKED\";\n" +
                    "log(String.format(\"Blocked status changed to %s for %d(%d) on netId %d\", blocked,\n" +
                    "\t\tnri.mUid, nri.request.requestId, net.netId));\n" +
                    "mNetworkInfoBlockingLogs.log(action + \" \" + nri.mUid); // 阻塞状态改变 \n" +
                    "}\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "  2019-12-20T18:51:40.966 - UNBLOCKED 10187   // 10187的NetworkRequestInfo请求 转非阻塞 \n" +
                    "  2019-12-20T18:51:40.953 - UNBLOCKED 10152\n" +
                    "  2019-12-20T18:51:40.919 - BLOCKED 10195    // 10195的 NetworkRequestInfo请求 被阻塞 \n" +
                    "  2019-12-20T18:51:40.919 - BLOCKED 10125\n" +
                    "  2019-12-20T18:39:14.923 - UNBLOCKED 10154\n" +
                    "  2019-12-20T18:39:02.326 - UNBLOCKED 10205";
            keyWordList.add(connectivity_1_15);


            KeyWordItem connectivity_1_16 = new KeyWordItem();
            connectivity_1_16.keyword = "  NetTransition WakeLock activity";
            connectivity_1_16.explain="WakeLock 锁持有的情况  ";
            connectivity_1_16.classNameForShuxing = "  ConnectivityService->  LocalLog mWakelockLogs";
            connectivity_1_16.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_16.printLogUrl="";
            connectivity_1_16.expain1=" public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    "int mTotalWakelockAcquisitions = 0;//  请求获取 Wakelock 的个数 \n" +
                    "int mTotalWakelockReleases = 0; // WakeLock的释放次数 \n" +
                    "long mTotalWakelockDurationMs = 0;  // 使用 wakelock的时间  毫秒 \n" +
                    "long mMaxWakelockDurationMs = 0;  // 最大 WakeLock的间隔时间?\n" +
                    "long mLastWakeLockAcquireTimestamp = 0;   //  上一次持有wakelock的时间戳 \n" +
                    "\n" +
                    "private static final int MAX_WAKELOCK_LOGS = 20;\n" +
                    "private final LocalLog mWakelockLogs = new LocalLog(MAX_WAKELOCK_LOGS);  // WakeLock 相关的日志 \n" +
                    "\n" +
                    "private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "    pw.println();\n" +
                    "\tpw.println(\"NetTransition WakeLock activity (most recent first):\");\n" +
                    "\tpw.increaseIndent();\n" +
                    "\tpw.println(\"total acquisitions: \" + mTotalWakelockAcquisitions);  // 请求获取 Wakelock 的个数 \n" +
                    "\tpw.println(\"total releases: \" + mTotalWakelockReleases);  // WakeLock的释放次数 \n" +
                    "\tpw.println(\"cumulative duration: \" + (mTotalWakelockDurationMs / 1000) + \"s\");\n" +
                    "\tpw.println(\"longest duration: \" + (mMaxWakelockDurationMs / 1000) + \"s\");\n" +
                    "\tif (mTotalWakelockAcquisitions > mTotalWakelockReleases) {\n" +
                    "\t\tlong duration = SystemClock.elapsedRealtime() - mLastWakeLockAcquireTimestamp;\n" +
                    "\t\tpw.println(\"currently holding WakeLock for: \" + (duration / 1000) + \"s\");  // 当前还在持有 WakeLock 的时间\n" +
                    "\t}\n" +
                    "\tmWakelockLogs.reverseDump(fd, pw, args);  // 打印WakeLock的日志 \n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "//  // 添加 WakeLock的日志 \n" +
                    "    private void ensureNetworkTransitionWakelock(String forWhom) {\n" +
                    "     mWakelockLogs.log(\"ACQUIRE for \" + forWhom);  // 请求 WakeLock\n" +
                    "\t }\n" +
                    "\t \n" +
                    "\t \n" +
                    "private void handleReleaseNetworkTransitionWakelock(int eventId) {\n" +
                    "     mWakelockLogs.log(String.format(\"RELEASE: already released (%s)\", event));   \n" +
                    "     mWakelockLogs.log(String.format(\"RELEASE (%s)\", event));  // 释放WakeLock 并打印释放的事件类型  \n" +
                    "}\n" +
                    "\t\t\t\t\t\n" +
                    " 事件:\n" +
                    "   int EVENT_CLEAR_NET_TRANSITION_WAKELOCK = 8;   // 清除 WakeLock\n" +
                    "   int EVENT_EXPIRE_NET_TRANSITION_WAKELOCK = 24;   // WakeLock 过期\n" +
                    "   int EVENT_SET_ACCEPT_PARTIAL_CONNECTIVITY = 45; \n" +
                    "  \n" +
                    "  \n" +
                    "\t\t\t\t\t\n" +
                    "LOG示例:\n" +
                    "  NetTransition WakeLock activity (most recent first):\n" +
                    "  total acquisitions: 3\n" +
                    "  total releases: 3\n" +
                    "  cumulative duration: 152s\n" +
                    "  longest duration: 60s\n" +
                    "  2019-12-20T19:43:18.807 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)  // 清除 WakeLock \n" +
                    "  2019-12-20T19:42:46.079 - ACQUIRE for NetworkAgentInfo [WIFI () - 103]\n" +
                    "  2019-12-20T18:52:59.082 - RELEASE (EVENT_EXPIRE_NET_TRANSITION_WAKELOCK)  // WakeLock 过期\n" +
                    "  2019-12-20T18:51:59.078 - ACQUIRE for NetworkAgentInfo [WIFI () - 102]\n" +
                    "  2019-12-20T18:40:18.889 - RELEASE (EVENT_EXPIRE_NET_TRANSITION_WAKELOCK)\n" +
                    "  2019-12-20T18:39:18.876 - ACQUIRE for NetworkAgentInfo [WIFI () - 100]\n" +
                    "  \n";
            keyWordList.add(connectivity_1_16);


            KeyWordItem connectivity_1_17 = new KeyWordItem();
            connectivity_1_17.keyword = "  bandwidth update requests";
            connectivity_1_17.explain="依据uid分类的 key=uid  value=该uid的带宽请求 bandwidth update requests 个数  ";
            connectivity_1_17.classNameForShuxing = "  ConnectivityService-> SparseArray<Integer> mBandwidthRequests ";
            connectivity_1_17.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_17.printLogUrl="";
            connectivity_1_17.expain1="    \n" +
                    " public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    "     @GuardedBy(\"mBandwidthRequests\")\n" +
                    "    private final SparseArray<Integer> mBandwidthRequests = new SparseArray(10);\n" +
                    "\t\n" +
                    "private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "            pw.println();\n" +
                    "            pw.println(\"bandwidth update requests (by uid):\");   // 带宽更新 请求 \n" +
                    "            pw.increaseIndent();\n" +
                    "            synchronized (mBandwidthRequests) {  // SparseArray<Integer>  \n" +
                    "                for (int i = 0; i < mBandwidthRequests.size(); i++) {\n" +
                    "                    pw.println(\"[\" + mBandwidthRequests.keyAt(i)   // uid\n" +
                    "                            + \"]: \" + mBandwidthRequests.valueAt(i));  // 带宽更新 请求个数\n" +
                    "                }\n" +
                    "            }\n" +
                    "     }\n" +
                    "LOG示例:\n" +
                    "  bandwidth update requests (by uid):";
            keyWordList.add(connectivity_1_17);

            KeyWordItem connectivity_1_18 = new KeyWordItem();
            connectivity_1_18.keyword = "NetworkStackClient logs:";
            connectivity_1_18.explain="网络状态栈 请求情况  ";
            connectivity_1_18.classNameForShuxing = "  ConnectivityService->  NetworkStackClient.getInstance().dump(pw) ";
            connectivity_1_18.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_18.printLogUrl="";
            connectivity_1_18.expain1=" public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    "private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "        pw.println();\n" +
                    "        pw.println(\"NetworkStackClient logs:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        NetworkStackClient.getInstance().dump(pw);  // 打印 NetworkStackClient 的 dump\n" +
                    "        pw.decreaseIndent();\n" +
                    "\t}\n" +
                    "\n" +
                    "\t||\n" +
                    "    \\/\n" +
                    "// Service used to communicate with the network stack, which is running in a separate module.\n" +
                    "public class NetworkStackClient {\n" +
                    "private static final String TAG = NetworkStackClient.class.getSimpleName();\n" +
                    "private final SharedLog mLog = new SharedLog(TAG);  // Log\n" +
                    "\n" +
                    " ArrayList<NetworkStackCallback> mPendingNetStackRequests = new ArrayList<>();  // 还未处理的网络回调集合\n" +
                    " \n" +
                    "    public void dump(PrintWriter pw) {\n" +
                    "        // dump is thread-safe on SharedLog\n" +
                    "        mLog.dump(null, pw, null);\n" +
                    "\n" +
                    "        final int requestsQueueLength;\n" +
                    "        synchronized (mPendingNetStackRequests) {\n" +
                    "            requestsQueueLength = mPendingNetStackRequests.size();\n" +
                    "        }\n" +
                    "\n" +
                    "        pw.println();\n" +
                    "        pw.println(\"pendingNetStackRequests length: \" + requestsQueueLength); // 待处理的网络状态请求个数\n" +
                    "    }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "log(\"Network stack init\");\n" +
                    "log(\"Network stack service registered\");\n" +
                    "log(\"Starting network stack\");\n" +
                    "log(\"Starting network stack process\");\n" +
                    "log(\"Starting network stack in-process\");\n" +
                    "log(\"Network stack service start requested\");\n" +
                    "logi(\"Network stack service connected\");  // 服务已连接\n" +
                    "loge(\"Error waiting for NetworkStack connector\", e); \n" +
                    "loge(\"Timeout waiting for NetworkStack connector\", null);  // 连接超时\n" +
                    "示例LOG:\n" +
                    "NetworkStackClient logs:\n" +
                    "  2019-12-20T18:38:05.900 - Network stack init\n" +
                    "  2019-12-20T18:38:08.953 - Starting network stack\n" +
                    "  2019-12-20T18:38:08.954 - Starting network stack in-process\n" +
                    "  2019-12-20T18:38:08.962 - Network stack service start requested\n" +
                    "  2019-12-20T18:38:12.018 - Network stack service connected\n" +
                    "  2019-12-20T18:38:12.021 - Network stack service registered\n" +
                    "  pendingNetStackRequests length: 0";
            keyWordList.add(connectivity_1_18);


            KeyWordItem connectivity_1_19 = new KeyWordItem();
            connectivity_1_19.keyword = "Permission Monitor:";
            connectivity_1_19.explain="权限检测?  VPN检测   ";
            connectivity_1_19.classNameForShuxing = "  ConnectivityService->   PermissionMonitor mPermissionMonitor; ";
            connectivity_1_19.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
            connectivity_1_19.printLogUrl="";
            connectivity_1_19.expain1="******************  PermissionMonitor Begin ******************\n" +
                    "public class PermissionMonitor {\n" +
                    "  \n" +
                    " // Keys are active non-bypassable and fully-routed VPN's interface name, Values are uid ranges for apps under the VPN\n" +
                    "  Map<String, Set<UidRange>> mVpnUidRanges = new HashMap<>();  // VPN 的一些信息\n" +
                    "  \n" +
                    "      /** Dump info to dumpsys */\n" +
                    "    public void dump(IndentingPrintWriter pw) {\n" +
                    "        pw.println(\"Interface filtering rules:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        for (Map.Entry<String, Set<UidRange>> vpn : mVpnUidRanges.entrySet()) {\n" +
                    "            pw.println(\"Interface: \" + vpn.getKey());  // VPN名称\n" +
                    "            pw.println(\"UIDs: \" + vpn.getValue().toString()); // 使用该VPN的 UID\n" +
                    "            pw.println();\n" +
                    "        }\n" +
                    "        pw.decreaseIndent();\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "******************  PermissionMonitor End ******************\n" +
                    " public class ConnectivityService extends IConnectivityManager.Stub implements PendingIntent.OnFinished {\n" +
                    "\n" +
                    "  PermissionMonitor mPermissionMonitor;\n" +
                    "  \n" +
                    "private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto) {\n" +
                    "          pw.println();\n" +
                    "        pw.println(\"Permission Monitor:\");\n" +
                    "        pw.increaseIndent();\n" +
                    "        mPermissionMonitor.dump(pw);\n" +
                    "        pw.decreaseIndent();\n" +
                    "\t}\n" +
                    "\t\n" +
                    "mPermissionMonitor.hasUseBackgroundNetworksPermission(Binder.getCallingUid()); // 是否有后台请求网络的权限\n" +
                    "mPermissionMonitor.startMonitoring();\n" +
                    "mPermissionMonitor.onUserAdded(userId);  //  添加 PID\n" +
                    "mPermissionMonitor.onPackageAdded(packageName, uid); \n" +
                    "mPermissionMonitor.onPackageRemoved(uid);\n" +
                    "mPermissionMonitor.onVpnUidRangesRemoved(oldIface, ranges, vpnAppUid);  // VPN 的 UID 移除 \n" +
                    "mPermissionMonitor.onVpnUidRangesAdded(newIface, ranges, vpnAppUid); // VPN 的 UID 增加";
            keyWordList.add(connectivity_1_19);

/*

NetworkFactories for:


            KeyWordItem connectivity_1_19 = new KeyWordItem();
                        connectivity_1_19.keyword = "Bad Wi-Fi avoidance:";
                        connectivity_1_19.explain="网络管理的各个分管经理的集合?  ";
                        connectivity_1_19.classNameForShuxing = "  ConnectivityService->  Tethering mTethering;";
                        connectivity_1_19.printcode="ConnectivityService   private void doDump(FileDescriptor fd, PrintWriter writer, String[] args, boolean asProto){}";
                        connectivity_1_19.printLogUrl="";
                        connectivity_1_19.expain1="";
        keyWordList.add(connectivity_1_19);

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