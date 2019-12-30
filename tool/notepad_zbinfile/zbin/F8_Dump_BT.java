import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class F8_Dump_BT {

//   getprop.txt  中读取 [ro.bui  ld.version.release]: [10]
//getprop.txt   [ro.hardware.soc.manufacturer]: [qcom]   制造商
//  如果不包含  那么 检测 是否包含MTK
    // .mtk  或者  .mtk
    // .qcom  或者  qcom.




    static String F8DirPathStr = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F8";
    static File F8DirFile = new File(F8DirPathStr);
    static  String getpropFileName = "getprop.txt";
    static  String gpsFileName = "location.txt";
    static  String btFileName = "bluetooth_manager.txt";     // bluetooth_manager.txt
    static Map<String,File> allDumpMap = new HashMap<String,File>();

    // 获取在目录 F8DirFile 下 所有文件的 绝对路径
    static   ArrayList<File> AllDumpFileList  = new ArrayList<File>();

    static Map<String,String> fileNameMapClass = new HashMap<String,String>();
    static{

        // class BluetoothManagerService extends IBluetoothManager.Stub {
        fileNameMapClass.put(btFileName,"/frameworks/base/services/core/java/com/android/server/BluetoothManagerService.java");

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
        F8_Dump_BT mDumpAnalysis = new F8_Dump_BT();
        mDumpAnalysis.initAnalysisWithVersion();
        curAndroidAnalysis.analysisFile();



    }

// Zukgit1  -- 依据版本生成 对不同版本的解析类  ( 目前只完成了 Android10Analysis )
    void initAnalysisWithVersion(){

        int version = Integer.parseInt(mAndroidVersion);
        System.out.println("initAnalysisWithVersion version = "+ version);
//        switch (version){   // zukgit
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

        // zukgit Zukgit2  -- 依据版本生成 对 不同的 输入文件 进行解析  目前 支持 gps
        ArrayList<String> getVersionNeedAnalysisFileNameList(String versionStr){
            if(versionStr == null)
                return null;
            int version = Integer.parseInt(versionStr);
            ArrayList<String> listFilename = new  ArrayList<String>();
            switch (version){
                case 9:
                    listFilename.add(getpropFileName);
                    listFilename.add(gpsFileName);
                    listFilename.add(btFileName);
                    break;
                case 10:
                    listFilename.add(getpropFileName);
                    listFilename.add(gpsFileName);
                    listFilename.add(btFileName);
                    break;

                case 11:
                    listFilename.add(getpropFileName);
                    listFilename.add(gpsFileName);
                    listFilename.add(btFileName);
                    break;
                default:
                    listFilename.add(getpropFileName);
                    listFilename.add(gpsFileName);
                    listFilename.add(btFileName);
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

            // location.txt;  zukgit
//            file_keyword_map.put(gpsFileName,initLocationkeyWordList());

            // bluetooth_manager.txt
            file_keyword_map.put(btFileName,initBTKeyWordList());  // 文件名    处理该文件的集合
            return file_keyword_map;
        }


        ArrayList<KeyWordItem>    initBTKeyWordList() {
            ArrayList<KeyWordItem> keyWordList = new ArrayList<KeyWordItem>();

// http://androidxref.com/9.0.0_r3/xref/frameworks/base/services/core/java/com/android/server/BluetoothManagerService.java#2611
            KeyWordItem bluetooth_1_1 = new KeyWordItem();
            bluetooth_1_1.keyword = "Bluetooth Status";
            bluetooth_1_1.explain="class BluetoothManagerService extends IBluetoothManager.Stub ";
            bluetooth_1_1.classNameForShuxing = "蓝牙是否打开  蓝牙状态  蓝牙打开时间 蓝牙地址  蓝牙名称  ";
            bluetooth_1_1.printcode="BluetoothManagerService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_1.printLogUrl="";
            bluetooth_1_1.expain1="class BluetoothManagerService extends IBluetoothManager.Stub { \n" +
                    "\n" +
                    "// Context.bindServiceAsUser(intent, mConnection, flags, user) \n" +
                    "BluetoothServiceConnection mConnection = new BluetoothServiceConnection();\n" +
                    "\n" +
                    "//  IBinder service = (IBinder) msg.obj;   // int MESSAGE_BLUETOOTH_SERVICE_CONNECTED = 40;\n" +
                    "// mBluetooth = IBluetooth.Stub.asInterface(Binder.allowBlocking(service));\n" +
                    "IBluetooth mBluetooth; \n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "......\n" +
                    "writer.println(\"Bluetooth Status\");  // 蓝牙状态\n" +
                    "writer.println(\"  enabled: \" + isEnabled());    //  【 boolean 】 mBluetooth.isEnabled();\n" +
                    "// 【 STATE_OFF = 10;蓝牙关闭 OFF 】    【 STATE_TURNING_ON = 11;蓝牙正在打开  TURNING_ON 】\n" +
                    "// 【 STATE_ON = 12;蓝牙已经打开 ON  】 【STATE_TURNING_OFF = 13;蓝牙正在关闭  TURNING_OFF 】\n" +
                    "// 【 STATE_BLE_TURNING_ON = 14; BLE低功耗蓝牙正在开启  BLE_TURNING_ON 】\n" +
                    "// 【 STATE_BLE_ON = 15;低功耗蓝牙已开启 BLE_ON  】 【 STATE_BLE_TURNING_OFF = 16; 低功耗蓝牙正在关闭 BLE_TURNING_OFF 】 \n" +
                    "writer.println(\"  state: \" + BluetoothAdapter.nameForState(mState));  //  int mState;  记录蓝牙状态?\n" +
                    "//  Settings.Secure.getString(mContentResolver, SECURE_SETTINGS_BLUETOOTH_ADDRESS); 【 \"bluetooth_address\" 】\n" +
                    "// adb shell settings get secure bluetooth_address\n" +
                    "writer.println(\"  address: \" + mAddress);  //String mAddress; 蓝牙地址    \n" +
                    "// adb shell settings get secure bluetooth_name            // 蓝牙名称\n" +
                    "writer.println(\"  name: \" + mName);  // String mName;   Settings.Secure.getString(mContentResolver, SECURE_SETTINGS_BLUETOOTH_NAME);\n" +
                    "if (mEnable) {  // 如果蓝牙打开  计算已打开时间时长 \n" +
                    "\t\tlong onDuration = SystemClock.elapsedRealtime() - mLastEnabledTime;   //  时间间隔  时:分:秒:毫秒 01:11:31.106\n" +
                    "\t\tString onDurationString = String.format(Locale.US, \"%02d:%02d:%02d.%03d\",\n" +
                    "\t\t\t\t(int) (onDuration / (1000 * 60 * 60)),\n" +
                    "\t\t\t\t(int) ((onDuration / (1000 * 60)) % 60), (int) ((onDuration / 1000) % 60),\n" +
                    "\t\t\t\t(int) (onDuration % 1000));\n" +
                    "\t\twriter.println(\"  time since enabled: \" + onDurationString);\n" +
                    "\t}\n" +
                    "......\n" +
                    "}\n" +
                    "}\n\n";
            keyWordList.add(bluetooth_1_1);


            KeyWordItem bluetooth_1_2 = new KeyWordItem();
            bluetooth_1_2.keyword = "Enable log:";
            bluetooth_1_2.explain="class BluetoothManagerService extends IBluetoothManager.Stub ";
            bluetooth_1_2.classNameForShuxing = "蓝牙开启日志记录  ";
            bluetooth_1_2.printcode="BluetoothManagerService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_2.printLogUrl="";
            bluetooth_1_2.expain1="class BluetoothManagerService extends IBluetoothManager.Stub {\n" +
                    "LinkedList<ActiveLog> mActiveLogs = new LinkedList<>();\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "......\n" +
                    "          writer.println(\"\\nEnable log:\");\n" +
                    "                for (ActiveLog log : mActiveLogs) {\n" +
                    "                    writer.println(\"  \" + log);\n" +
                    "                }\n" +
                    "......\n" +
                    "}\n" +
                    "\t\t\t\n" +
                    "**************** ActiveLog Begin ****************\n" +
                    "\n" +
                    "// Bluetooth Adapter Enable and Disable Reasons\n" +
                    "enum EnableDisableReasonEnum {\n" +
                    "    ENABLE_DISABLE_REASON_UNSPECIFIED = 0;   未知原因导致的蓝牙开闭操作\n" +
                    "    ENABLE_DISABLE_REASON_APPLICATION_REQUEST = 1;   APPLICATION_REQUEST  应用请求蓝牙开闭操作\n" +
                    "    ENABLE_DISABLE_REASON_AIRPLANE_MODE = 2;   AIRPLANE_MODE  飞行模式导致的蓝牙开闭操作\n" +
                    "    ENABLE_DISABLE_REASON_DISALLOWED = 3;   DISALLOWED   不允许? 没权限?\n" +
                    "    ENABLE_DISABLE_REASON_RESTARTED = 4;   RESTARTED   设备重启?\n" +
                    "    ENABLE_DISABLE_REASON_START_ERROR = 5;  START_ERROR    启动失败?\n" +
                    "    ENABLE_DISABLE_REASON_SYSTEM_BOOT = 6;     SYSTEM_BOOT    重启成功\n" +
                    "    ENABLE_DISABLE_REASON_CRASH = 7;              CRASH   系统异常崩溃\n" +
                    "    ENABLE_DISABLE_REASON_USER_SWITCH = 8;         用户手动切换 导致的 蓝牙开闭操作\n" +
                    "    ENABLE_DISABLE_REASON_RESTORE_USER_SETTING = 9;    RESTORE_USER_SETTING  恢复设置导致的蓝牙开闭操作\n" +
                    "}\n" +
                    "\n" +
                    "    private class ActiveLog {\n" +
                    "        int mReason;   // 原因\n" +
                    "        String mPackageName; // 关闭开启蓝牙的包名称\n" +
                    "        boolean mEnable; // 开启蓝牙--true   关闭蓝牙--false \n" +
                    "        long mTimestamp;  // 操作蓝牙的时间戳\n" +
                    "\t\t\n" +
                    "public String toString() {\n" +
                    "  return timeToLog(mTimestamp) + (mEnable ? \"  Enabled \" : \" Disabled \")\n" +
                    "          + \" due to \" + getEnableDisableReasonString(mReason) + \" by \" + mPackageName;\n" +
                    " }\n" +
                    "}\n" +
                    "\n" +
                    "**************** ActiveLog End ****************\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "Enable log:\n" +
                    "  12-20 18:38:07  Enabled  due to SYSTEM_BOOT by android";
            keyWordList.add(bluetooth_1_2);



            KeyWordItem bluetooth_1_3 = new KeyWordItem();
            bluetooth_1_3.keyword = "Bluetooth crashed";
            bluetooth_1_3.explain="class BluetoothManagerService extends IBluetoothManager.Stub ";
            bluetooth_1_3.classNameForShuxing = "蓝牙 crash 崩溃次数  ";
            bluetooth_1_3.printcode="BluetoothManagerService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_3.printLogUrl="";
            bluetooth_1_3.expain1="class BluetoothManagerService extends IBluetoothManager.Stub {\n" +
                    "int CRASH_LOG_MAX_SIZE =  100;\n" +
                    "int mCrashes;  // 蓝牙崩溃的次数  MESSAGE_BLUETOOTH_SERVICE_DISCONNECTED=>  addCrashLog(){ mCrashes++;}\n" +
                    "LinkedList<Long> mCrashTimestamps = new LinkedList<>();   // 蓝牙崩溃时的时间戳  mCrashTimestamps.add(System.currentTimeMillis()\n" +
                    "public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "\n" +
                    "\twriter.println(\"\\nBluetooth crashed \" + mCrashes + \" time\" + (mCrashes == 1 ? \"\" : \"s\"));\n" +
                    "\tif (mCrashes == CRASH_LOG_MAX_SIZE) {  // int CRASH_LOG_MAX_SIZE =  100;\n" +
                    "\t\twriter.println(\"(last \" + CRASH_LOG_MAX_SIZE + \")\");\n" +
                    "\t}\n" +
                    "\tfor (Long time : mCrashTimestamps) {\n" +
                    "\t\twriter.println(\"  \" + timeToLog(time));\n" +
                    "\t}\n" +
                    "}";
            keyWordList.add(bluetooth_1_3);



            KeyWordItem bluetooth_1_4 = new KeyWordItem();
            bluetooth_1_4.keyword = " BLE app";
            bluetooth_1_4.isContain = true;
            bluetooth_1_4.explain="class BluetoothManagerService extends IBluetoothManager.Stub ";
            bluetooth_1_4.classNameForShuxing = "保存要求保持BLE扫描开启的那些注册APP 字典Map  ";
            bluetooth_1_4.printcode="BluetoothManagerService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_4.printLogUrl="";
            bluetooth_1_4.expain1="  class BluetoothManagerService extends IBluetoothManager.Stub {\n" +
                    "\n" +
                    "// Map of apps registered to keep BLE scanning on. 保存要求保持BLE扫描开启的那些注册APP 字典Map\n" +
                    "//   updateBleAppCount(IBinder token, boolean enable, String packageName) => mBleApps.put(token, deathRec); \n" +
                    "Map<IBinder, ClientDeathRecipient> mBleApps = new ConcurrentHashMap<IBinder, ClientDeathRecipient>();\n" +
                    "\t\t\t\n" +
                    "  public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "......\n" +
                    "   writer.println(\"\\n\" + mBleApps.size() + \" BLE app\" + (mBleApps.size() == 1 ? \"\" : \"s\") + \"registered\");\n" +
                    "\tfor (ClientDeathRecipient app : mBleApps.values()) {\n" +
                    "\t\twriter.println(\"  \" + app.getPackageName());\n" +
                    "\t}\n" +
                    "......\n" +
                    "}";
            keyWordList.add(bluetooth_1_4);


            KeyWordItem bluetooth_1_5 = new KeyWordItem();
            bluetooth_1_5.keyword = "AdapterProperties";
            bluetooth_1_5.explain="class BluetoothManagerService extends IBluetoothManager.Stub ";
            bluetooth_1_5.classNameForShuxing = " 蓝牙配置属性信息 BluetoothManagerService->IBinder mBluetoothBinder;\n" +
                    "class AdapterService { 子类实现接口  AdapterServiceBinder extends IBluetooth.Stub{} } ";
            bluetooth_1_5.printcode="AdapterService.AdapterServiceBinder  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_5.printLogUrl="AdapterProperties mAdapterProperties; 的 dump方法";
            bluetooth_1_5.expain1="BluetoothManagerService 中 IBinder的实现\n" +
                    "*************** IBluetoothHeadset Begin ***************\n" +
                    "    // Save a ProfileServiceConnections object for each of the bound  bluetooth profile services\n" +
                    " Map<Integer, ProfileServiceConnections> mProfileServices = new HashMap<>();  // \n" +
                    "Intent intent = new Intent(IBluetoothHeadset.class.getName());\n" +
                    "ProfileServiceConnections psc = new ProfileServiceConnections(intent);\n" +
                    "psc.bindService()\n" +
                    "*************** IBluetoothHeadset End ***************\n" +
                    "\n" +
                    "*************** IBluetooth Begin ***************\n" +
                    "IBinder mBluetoothBinder;\n" +
                    "Intent intent = new Intent(IBluetooth.class.getName());\n" +
                    "BluetoothServiceConnection mConnection = new BluetoothServiceConnection();\n" +
                    "mContext.bindServiceAsUser(intent, mConnection, flags, user)\n" +
                    "public void onServiceConnected(ComponentName componentName, IBinder service) {}\n" +
                    "*************** IBluetooth End ***************\n" +
                    "\n" +
                    "*************** IBluetoothGatt Begin ***************\n" +
                    "IBluetoothGatt mBluetoothGatt;\n" +
                    "Intent intent = new Intent(IBluetoothGatt.class.getName());\n" +
                    "BluetoothServiceConnection mConnection = new BluetoothServiceConnection();\n" +
                    "mContext.bindServiceAsUser(intent, mConnection, flags, user)\n" +
                    "public void onServiceConnected(ComponentName componentName, IBinder service) {}\n" +
                    "*************** IBluetoothGatt End ***************\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "************************** BluetoothManagerService  Begin **************************\n" +
                    "class BluetoothManagerService extends IBluetoothManager.Stub {\n" +
                    "\n" +
                    " private IBinder mBluetoothBinder;\n" +
                    " //  Context.bindServiceAsUser(intent, mConnection, flags, user) \n" +
                    " //  BluetoothServiceConnection mConnection = new BluetoothServiceConnection();\n" +
                    " //  BluetoothServiceConnection => void onServiceConnected(ComponentName componentName, IBinder service){   msg.obj = service; }\n" +
                    " //  mBluetoothBinder = { MESSAGE_BLUETOOTH_SERVICE_CONNECTED }  (IBinder) msg.obj;\n" +
                    " \n" +
                    "  public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    " ......\n" +
                    "       if (mBluetoothBinder == null) {\n" +
                    "            errorMsg = \"Bluetooth Service not connected\";\n" +
                    "        } else {\n" +
                    "            try {\n" +
                    "\t\t\t\n" +
                    "// /packages/apps/Bluetooth/src/com/android/bluetooth/btservice/AdapterService.java \n" +
                    "// static class AdapterServiceBinder extends IBluetooth.Stub {}  \n" +
                    "// IBluetoothManager.aidl    /system/bt/binder/android/bluetooth/IBluetoothManager.aidl \n" +
                    "//  interface IBinder {} => public void dump(@NonNull FileDescriptor fd, @Nullable String[] args) throws RemoteException;\n" +
                    "                mBluetoothBinder.dump(fd, args);  // 打印 \n" +
                    "            } catch (RemoteException re) {\n" +
                    "                errorMsg = \"RemoteException while dumping Bluetooth Service\";\n" +
                    "            }\n" +
                    "        }\n" +
                    ".........\n" +
                    "}\n" +
                    "\n" +
                    "************************** BluetoothManagerService End **************************\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "***************** AdapterService AdapterServiceBinder  Begin ***************\n" +
                    "\n" +
                    " 【 AdapterService.AdapterServiceBinder 】private static class AdapterServiceBinder extends IBluetooth.Stub {\n" +
                    "        private AdapterService mService;  // 外部类的引用 \n" +
                    "\n" +
                    "        @Override\n" +
                    "        public void dump(FileDescriptor fd, String[] args) {\n" +
                    "            PrintWriter writer = new PrintWriter(new FileOutputStream(fd));\n" +
                    "            AdapterService service = getService();\n" +
                    "            if (service == null) {\n" +
                    "                return;\n" +
                    "            }\n" +
                    "            service.dump(fd, writer, args);  // 内部类 AdapterService.AdapterServiceBinder调用  AdapterService的dump\n" +
                    "            writer.close();\n" +
                    "        }\n" +
                    "\n" +
                    "}\n" +
                    " ||\n" +
                    " ||\n" +
                    " \\/\n" +
                    "\n" +
                    "【 AdapterService 】\n" +
                    "AdapterProperties mAdapterProperties;\n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "........\n" +
                    "        writer.println();\n" +
                    "        mAdapterProperties.dump(fd, writer, args);  // 打印 AdapterProperties 的 dump方法\n" +
                    "        writer.println(\"mSnoopLogSettingAtEnable = \" + mSnoopLogSettingAtEnable);\n" +
                    "        writer.println(\"mDefaultSnoopLogSettingAtEnable = \" + mDefaultSnoopLogSettingAtEnable);\n" +
                    "        writer.println();\n" +
                    "        mAdapterStateMachine.dump(fd, writer, args);\n" +
                    "\n" +
                    "        StringBuilder sb = new StringBuilder();\n" +
                    "        for (ProfileService profile : mRegisteredProfiles) {\n" +
                    "            profile.dump(sb);\n" +
                    "        }\n" +
                    "        mSilenceDeviceManager.dump(fd, writer, args);\n" +
                    "        writer.write(sb.toString());\n" +
                    "        writer.flush();\n" +
                    "        dumpNative(fd, args);\n" +
                    "    }\n" +
                    "\t\n" +
                    "***************** AdapterService AdapterServiceBinder End ***************\n" +
                    "\n" +
                    "\n" +
                    "*************** AdapterProperties  Begin ***************\n" +
                    "\n" +
                    "\n" +
                    "class AdapterProperties {\n" +
                    "static final String TAG = \"AdapterProperties\"; \n" +
                    "// com_android_bluetooth_btservice_vendor.cpp , \"adapterPropertyChangedCallback\", \"([I[[B)V\"); in classInitNative() \n" +
                    "//  adapterPropertyChangedCallback (byte[][] values) => fori =>  val = values[i]; type = types[i];\n" +
                    "volatile String mName; //  mName = new String(val);\n" +
                    "volatile byte[] mAddress;  // mac地址 字节数组  byte[]\n" +
                    "volatile BluetoothClass mBluetoothClass;\n" +
                    "// 扫描模式  BluetoothAdapter.SCAN_MODE_NONE = 20 = SCAN_MODE_NONE  //不可连接不可发现 Indicates that both inquiry scan and page scan are disabled on the local Bluetooth adapter\n" +
                    "// 扫描模式  SCAN_MODE_CONNECTABLE = 21;  // 可连接无法发现 Indicates that inquiry scan is disabled, but page scan is enabled\n" +
                    "// 扫描模式  SCAN_MODE_CONNECTABLE_DISCOVERABLE = 23; //可连接可发现  ndicates that both inquiry scan and page scan are enabled\n" +
                    "volatile int mScanMode;  \n" +
                    "\n" +
                    "// 连接状态-断开      int STATE_DISCONNECTED  = 0 =  BluetoothProtoEnums.CONNECTION_STATE_DISCONNECTED;\n" +
                    "// 连接状态-正在连接  int STATE_CONNECTING    = 1 =  BluetoothProtoEnums.CONNECTION_STATE_CONNECTING;\n" +
                    "// 连接状态-已连接    int STATE_CONNECTED     = 2 =  BluetoothProtoEnums.CONNECTION_STATE_CONNECTED;\n" +
                    "// 连接状态-正在断开  int STATE_DISCONNECTING = 4 =  BluetoothProtoEnums.CONNECTION_STATE_DISCONNECTING;\n" +
                    "volatile int mConnectionState = BluetoothAdapter.STATE_DISCONNECTED;\n" +
                    "//蓝牙功能状态 【 STATE_OFF = 10;蓝牙关闭 OFF 】    【 STATE_TURNING_ON = 11;蓝牙正在打开  TURNING_ON 】\n" +
                    "//蓝牙功能状态 【 STATE_ON = 12;蓝牙已经打开 ON  】 【STATE_TURNING_OFF = 13;蓝牙正在关闭  TURNING_OFF 】\n" +
                    "//蓝牙功能状态 【 STATE_BLE_TURNING_ON = 14; BLE低功耗蓝牙正在开启  BLE_TURNING_ON 】\n" +
                    "//蓝牙功能状态 【 STATE_BLE_ON = 15;低功耗蓝牙已开启 BLE_ON  】 【 STATE_BLE_TURNING_OFF = 16; 低功耗蓝牙正在关闭 BLE_TURNING_OFF 】\n" +
                    "volatile int mState = BluetoothAdapter.STATE_OFF;\n" +
                    "int mMaxConnectedAudioDevices = 1;  // 蓝牙最大连接其他语音设备数量  代码规定 最大为5 \n" +
                    "// int configDefaultMaxConnectedAudioDevices = mService.getResources().getInteger( com.android.internal.R.integer.config_bluetooth_max_connected_audio_devices);  // <integer name=\"config_bluetooth_max_connected_audio_devices\">5</integer>\n" +
                    "//int propertyOverlayedMaxConnectedAudioDevices =SystemProperties.getInt(MAX_CONNECTED_AUDIO_DEVICES_PROPERTY, configDefaultMaxConnectedAudioDevices);   adb shell getprop persist.bluetooth.maxconnectedaudiodevices\n" +
                    "// int MAX_CONNECTED_AUDIO_DEVICES_LOWER_BOND = 1;   int MAX_CONNECTED_AUDIO_DEVICES_UPPER_BOUND = 5;  //\n" +
                    "// mMaxConnectedAudioDevices = Math.min(Math.max(propertyOverlayedMaxConnectedAudioDevices, MAX_CONNECTED_AUDIO_DEVICES_LOWER_BOND), MAX_CONNECTED_AUDIO_DEVICES_UPPER_BOUND); //\n" +
                    "\n" +
                    "\n" +
                    "// mA2dpOffloadEnabled =SystemProperties.getBoolean(A2DP_OFFLOAD_SUPPORTED_PROPERTY, false) && !SystemProperties.getBoolean(A2DP_OFFLOAD_DISABLED_PROPERTY, false);\n" +
                    "// adb shell getprop  ro.bluetooth.a2dp_offload.supported => true  &&  ![null] <=  adb shell getprop persist.bluetooth.a2dp_offload.disabled\n" +
                    "boolean mA2dpOffloadEnabled = false;   // 标识BT硬件是否支持 分流?\n" +
                    "\n" +
                    "boolean mDiscovering;   // 设备是否可发现 \n" +
                    "\n" +
                    "//  mDiscoveryEndMs = System.currentTimeMillis() + DEFAULT_DISCOVERY_TIMEOUT_MS;   可被发现蓝牙结束时间戳\n" +
                    "long mDiscoveryEndMs; //< Time (ms since epoch) that discovery ended or will end.\n" +
                    "\n" +
                    "// 已配对蓝牙设备列表 \n" +
                    "CopyOnWriteArrayList<BluetoothDevice> mBondedDevices = new CopyOnWriteArrayList<BluetoothDevice>();\n" +
                    "\n" +
                    "// 经典  BR/EDR  蓝牙\n" +
                    "// 蓝牙的波段为2400–2483.5MHz(包括防护频带) \n" +
                    "// 蓝牙使用跳频技术，将传输的数据分割成数据包，通过79个指定的蓝牙频道分别传输数据包 。每个频道的频宽为1 MHz\n" +
                    "// 蓝牙4.0使用 2 MHz 间距，可容纳40个频道。第一个频道始于2402 MHz，每1 MHz一个频道，至2480 MHz  2426Mhz 防护频带 不使用\n" +
                    "// 基础速率（Basic Rate，简称BR）运行，瞬时速率可达1Mbit/s  \n" +
                    "// 增强数据率（Enhanced Data Rate，简称EDR）一词用于描述π/4-DPSK 和 8DPSK 方案, 分别可达2 和 3Mbit/s。\n" +
                    "// BR/EDR配置文件包括： 耳机(HSP)、对象交换(OBEX)、音频传输(A2DP)、视频传输(VDP) 和文件传输(FTP)\n" +
                    "\n" +
                    "// 配置文件（Profile）\n" +
                    "// 通用属性配置文件（GATT）可被应用或其他配置文件所调用，让客户端（client）与服务器端（server）进行数据交互。目前有许多采用GATT构建的配置文件定义\n" +
                    "\n" +
                    "// 蓝牙设备类型-未知类型 = 0 = Unknown\n" +
                    "// 蓝牙设备类型: 1 经典蓝牙 Classic - BR/EDR （蓝牙基本速率/增强数据率） devices = 1  \n" +
                    "// 蓝牙设备类型:2  低功耗蓝牙类型  Low Energy - LE-only int DEVICE_TYPE_LE = 2;\n" +
                    "// 蓝牙设备类型: 3  双工蓝牙? 双模式  Dual Mode - BR/EDR/LE   低功耗和经典蓝牙都包含? \n" +
                    "int BluetoothDevice.getType(); // 蓝牙设备的类型\t\n" +
                    "\n" +
                    "protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "writer.println(TAG);  // static final String TAG = \"AdapterProperties\"; \n" +
                    "writer.println(\"  \" + \"Name: \" + getName());  // moto g(7)\n" +
                    "writer.println(\"  \" + \"Address: \" + Utils.getAddressStringFromByte(mAddress));  // 08:CC:27:B4:9D:F8 把字节打印成Mac形式\n" +
                    "writer.println(\"  \" + \"BluetoothClass: \" + getBluetoothClass());  // BluetoothClass: null\n" +
                    "writer.println(\"  \" + \"ScanMode: \" + dumpScanMode(getScanMode()));  // int mScanMode 蓝牙 扫描模式 是否可以发现  是否可以连接 \n" +
                    "writer.println(\"  \" + \"ConnectionState: \" + dumpConnectionState(getConnectionState())); //连接状态  mConnectionState STATE_DISCONNECTED   AdapterService.java->mService.getAdapterConnectionState()\n" +
                    "writer.println(\"  \" + \"State: \" + BluetoothAdapter.nameForState(getState()));  // 蓝牙功能状态 int  mState， ON\n" +
                    "writer.println(\"  \" + \"MaxConnectedAudioDevices: \" + getMaxConnectedAudioDevices());  // 蓝牙最大连接其他语音设备数量 代码规定 最大为5 \n" +
                    "writer.println(\"  \" + \"A2dpOffloadEnabled: \" + mA2dpOffloadEnabled);  // true \n" +
                    "writer.println(\"  \" + \"Discovering: \" + mDiscovering);  // 设备是否可发现 \n" +
                    "writer.println(\"  \" + \"DiscoveryEndMs: \" + mDiscoveryEndMs);  // 结束被发现的时间戳结点 \n" +
                    "\n" +
                    "writer.println(\"  \" + \"Bonded devices:\");   //  已连接设备\n" +
                    "        for (BluetoothDevice device : mBondedDevices) {  // 设备Mac地址  设备名称 \n" +
                    "\t\t// 设备类型  0=Unknown= ????   1=Classic=BR/EDR  2=LE=低功耗  3= DUAL =双模BR/EDR&& LE \n" +
                    " writer.println(  \"    \" + device.getAddress() + \" [\" + dumpDeviceType(device.getType()) + \"] \"      + device.getName());\n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "*************** AdapterProperties  End ***************";
            keyWordList.add(bluetooth_1_5);


            KeyWordItem bluetooth_1_6 = new KeyWordItem();
            bluetooth_1_6.keyword="mSnoopLogSettingAtEnable =";
            bluetooth_1_6.explain="class BluetoothManagerService extends IBluetoothManager.Stub ";
            bluetooth_1_6.classNameForShuxing = " 蓝牙 SnoopLog的模式  以及 默认的模式  BluetoothManagerService->IBinder mBluetoothBinder;\n" +
                    "class AdapterService { 子类实现接口  AdapterServiceBinder extends IBluetooth.Stub{} } ";
            bluetooth_1_6.printcode="BluetoothManagerService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_6.printLogUrl="AdapterService 的  String mSnoopLogSettingAtEnable = \"empty\"; 和 String mDefaultSnoopLogSettingAtEnable = \"empty\"; ";
            bluetooth_1_6.expain1="各种蓝牙规格的实现类  \n" +
                    "BluetoothManager.java         int getConnectionState(BluetoothDevice device, int profile) { in getConnectionState() method in BluetoothManager \n" +
                    "BluetoothDun.java             int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothDun \n" +
                    "BluetoothMap.java             int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothMap \n" +
                    "BluetoothPbap.java            int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothPbap \n" +
                    "BluetoothSap.java             int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothSap \n" +
                    "BluetoothPbapClient.java      int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothPbapClient \n" +
                    "BluetoothA2dpSink.java        int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothA2dpSink \n" +
                    "BluetoothAvrcpController.java int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothAvrcpController \n" +
                    "BluetoothHealth.java          int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothHealth \n" +
                    "BluetoothMapClient.java       int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothMapClient \n" +
                    "BluetoothPan.java             int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothPan \n" +
                    "BluetoothGattServer.java      int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothGattServer \n" +
                    "BluetoothProfile.java      @BtProfileState int getConnectionState(BluetoothDevice device); in getConnectionState() method \n" +
                    "BluetoothA2dp.java         @BtProfileState int getConnectionState(BluetoothDevice device) { in getConnectionState() method in BluetoothA2dp \n" +
                    "BluetoothHearingAid.java   @BluetoothProfile.BtProfileState int getConnectionState( in getConnectionState() method in BluetoothHearingAid \n" +
                    "\n" +
                    "\n" +
                    "public class AdapterService extends Service {\n" +
                    "// mSnoopLogSettingAtEnable = SystemProperties.get(BLUETOOTH_BTSNOOP_LOG_MODE_PROPERTY, \"empty\");\n" +
                    "// adb shell getprop persist.bluetooth.btsnooplogmode    snooplog模式   full|empty   LOG模式? \n" +
                    "String mSnoopLogSettingAtEnable = \"empty\";\n" +
                    "\n" +
                    "//mDefaultSnoopLogSettingAtEnable =Settings.Global.getString(getContentResolver(), Settings.Global.BLUETOOTH_BTSNOOP_DEFAULT_MODE);\n" +
                    "//  adb shell  settings get global bluetooth_btsnoop_default_mode   null    默认的btsnoop 模式\n" +
                    "String mDefaultSnoopLogSettingAtEnable = \"empty\";\n" +
                    "\n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "........\n" +
                    "        writer.println();\n" +
                    "        mAdapterProperties.dump(fd, writer, args);  // 打印 AdapterProperties 的 dump方法\n" +
                    "        writer.println(\"mSnoopLogSettingAtEnable = \" + mSnoopLogSettingAtEnable);\n" +
                    "        writer.println(\"mDefaultSnoopLogSettingAtEnable = \" + mDefaultSnoopLogSettingAtEnable);\n" +
                    "........\n" +
                    "}";
            keyWordList.add(bluetooth_1_6);



            KeyWordItem bluetooth_1_7 = new KeyWordItem();
            bluetooth_1_7.keyword = "AdapterState:";
            bluetooth_1_7.explain="class BluetoothManagerService extends IBluetoothManager.Stub{ IBinder IBluetooth;} \n" +
                    "AdapterService.AdapterServiceBinder  extends IBluetooth.Stub { AdapterState mAdapterStateMachine;  【StateMachine】 }    ";
            bluetooth_1_7.classNameForShuxing = "蓝牙状态机的消息处理列表  ";
            bluetooth_1_7.printcode="AdapterState【WifiState】  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_7.printLogUrl="";
            bluetooth_1_7.expain1="public class AdapterService extends Service {\n" +
                    "//  mAdapterStateMachine =  AdapterState.make(this);   //  AdapterState extends StateMachine \n" +
                    "AdapterState mAdapterStateMachine;  \n" +
                    "\n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "........\n" +
                    "        writer.println();\n" +
                    "        mAdapterStateMachine.dump(fd, writer, args);\n" +
                    "........\n" +
                    "}\n" +
                    "}\n" +
                    "****************** AdapterState Begin ******************\n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/btservice/AdapterState.java\n" +
                    "final class AdapterState extends StateMachine {\n" +
                    "dump()  就是打印状态机 StateMachine的dunp()\n" +
                    "\n" +
                    "// 状态机栈\n" +
                    "addState(mOnState);\n" +
                    "addState(mBleOnState);\n" +
                    "addState(mOffState);\n" +
                    "addState(mTurningOnState);\n" +
                    "addState(mTurningOffState);\n" +
                    "addState(mTurningBleOnState);\n" +
                    "addState(mTurningBleOffState);\n" +
                    "mAdapterService = service;\n" +
                    "setInitialState(mOffState);\n" +
                    "\t\t\n" +
                    "}\n" +
                    "\n" +
                    "        +----- OffState <----+\n" +
                    "        |                    |\n" +
                    "        v                    |\n" +
                    " TurningOnState  TO--->   TurningOffState\n" +
                    "        |                  ^ ^\n" +
                    "        |                  | |\n" +
                    "        +----->        ----+ |\n" +
                    "               BleOnState    |\n" +
                    "        +------        <---+ O\n" +
                    "        v                  | T\n" +
                    "TurningBleOnState  TO---->  TurningBleOffState\n" +
                    "        |                    ^\n" +
                    "        |                    |\n" +
                    "        +----> OnState ------+\n" +
                    " \n" +
                    "  各个状态不能形成栈  自己就是栈顶 \n" +
                    "   OnState   BleOnState  TurningOffState   OffState 【初始状态】   TurningOnState  TurningBleOnState      TurningBleOffState\n" +
                    "Log示例:\n" +
                    "AdapterState:\n" +
                    " total records=4\n" +
                    " rec[0]: time=12-20 18:38:11.980 processed=OffState org=OffState dest=TurningBleOnState what=3(0x3) BLE_TURN_ON\n" +
                    " rec[1]: time=12-20 18:38:13.006 processed=TurningBleOnState org=TurningBleOnState dest=BleOnState what=7(0x7) BLE_STARTED\n" +
                    " rec[2]: time=12-20 18:38:13.017 processed=BleOnState org=BleOnState dest=TurningOnState what=1(0x1) USER_TURN_ON\n" +
                    " rec[3]: time=12-20 18:38:13.978 processed=TurningOnState org=TurningOnState dest=OnState what=5(0x5) BREDR_STARTED\n" +
                    "curState=OnState\n" +
                    "****************** AdapterState End ******************\n";
            keyWordList.add(bluetooth_1_7);


            KeyWordItem bluetooth_1_8 = new KeyWordItem();
            bluetooth_1_8.keyword = "SilenceDeviceManager:";
            bluetooth_1_8.explain="class BluetoothManagerService extends IBluetoothManager.Stub{ IBinder IBluetooth;} \n" +
                    "AdapterService.AdapterServiceBinder  extends IBluetooth.Stub { SilenceDeviceManager mSilenceDeviceManager;  // 设备管理?  已连接过的设备管理?  }    ";
            bluetooth_1_8.classNameForShuxing = "蓝牙曾经配对设备管理类  ";
            bluetooth_1_8.printcode="SilenceDeviceManager  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_8.printLogUrl="";
            bluetooth_1_8.expain1="public class AdapterService extends Service {\n" +
                    "\n" +
                    " SilenceDeviceManager mSilenceDeviceManager;  // 设备管理?  已连接过的设备管理? \n" +
                    "\n" +
                    "protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    ".........\n" +
                    "mSilenceDeviceManager.dump(fd, writer, args);\n" +
                    ".........  \n" +
                    "}\n" +
                    "\n" +
                    "****************** SilenceDeviceManager Begin ******************\n" +
                    "public class SilenceDeviceManager {\n" +
                    " // 曾经连接设备列表  Boolean 标识是否沉默的  true--标识对端蓝牙静默搜不到   false--标识对端蓝牙能搜得到\n" +
                    " Map<BluetoothDevice, Boolean> mSilenceDevices = new HashMap<>(); \n" +
                    " \n" +
                    "\n" +
                    "     boolean getSilenceMode(BluetoothDevice device) {\n" +
                    "        boolean state = false;  // 默认都搜得到? 什么逻辑? \n" +
                    "        if (mSilenceDevices.containsKey(device)) {\n" +
                    "            state = mSilenceDevices.get(device);\n" +
                    "        }\n" +
                    "        return state;\n" +
                    "    }\n" +
                    "\t\n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "        writer.println(\"\\nSilenceDeviceManager:\");\n" +
                    "        writer.println(\"  Address            | Is silenced?\");\n" +
                    "        for (BluetoothDevice device : mSilenceDevices.keySet()) {  // 打印设备的mac地址 \n" +
                    "            writer.println(\"  \" + device.getAddress() + \"  | \" + getSilenceMode(device));\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "****************** SilenceDeviceManager End ******************";
            keyWordList.add(bluetooth_1_8);



            KeyWordItem bluetooth_1_9 = new KeyWordItem();
            bluetooth_1_9.keyword = "Profile: GattService";
            bluetooth_1_9.explain="BT 的 Profile(配置文件)  通用属性配置文件（GATT） 列表  \n" +
                    "class BluetoothManagerService extends IBluetoothManager.Stub{ IBinder IBluetooth;} \n" +
                    "AdapterService.AdapterServiceBinder  extends IBluetooth.Stub { ArrayList<ProfileService> mRegisteredProfiles . Item=> Dump() 配置服务列表  }    ";
            bluetooth_1_9.classNameForShuxing = "AdapterService => ArrayList<ProfileService> mRegisteredProfiles  注册的BT配置服务列表  ";
            bluetooth_1_9.printcode="AdapterService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_9.printLogUrl="AdapterService => ArrayList<ProfileService> mRegisteredProfiles ";
            bluetooth_1_9.expain1="已注册的配置服务:\n" +
                    "Profile: GattService            extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/gatt/\n" +
                    "Profile: HeadsetService         extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/hfp/\n" +
                    "Profile: A2dpService            extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/a2dp/\n" +
                    "Profile: HidHostService         extends ProfileService {}  /packages/apps/Bluetooth/src/com/android/bluetooth/hid/\n" +
                    "Profile: PanService             extends ProfileService {}  /packages/apps/Bluetooth/src/com/android/bluetooth/pan/\n" +
                    "Profile: BluetoothMapService    extends ProfileService {}  /packages/apps/Bluetooth/src/com/android/bluetooth/map/\n" +
                    "Profile: AvrcpControllerService extends ProfileService {}  /packages/apps/Bluetooth/src/com/android/bluetooth/avrcpcontroller/\n" +
                    "Profile: SapService             extends ProfileService {}  /packages/apps/Bluetooth/src/com/android/bluetooth/sap/           \n" +
                    "Profile: BluetoothOppService    extends ProfileService implements IObexConnectionHandler {}  /packages/apps/Bluetooth/src/com/android/bluetooth/opp/\n" +
                    "Profile: BluetoothPbapService   extends ProfileService implements IObexConnectionHandler {}  /packages/apps/Bluetooth/src/com/android/bluetooth/pbap/\n" +
                    "\t\n" +
                    "实现的 配置服务 但未注册列表\n" +
                    "PbapClientService    extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/pbapclient/\n" +
                    "AvrcpTargetService   extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/avrcp/\n" +
                    "A2dpSinkService      extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/a2dpsink/\n" +
                    "MapClientService     extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/mapclient/\n" +
                    "HearingAidService    extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/hearingaid/\n" +
                    "HeadsetClientService extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/hfpclient/\n" +
                    "HidDeviceService     extends ProfileService {}   /packages/apps/Bluetooth/src/com/android/bluetooth/hid/\n" +
                    "BATService           extends ProfileService {}   /vendor/qcom/opensource/commonsys/bluetooth_ext/packages_apps_bluetooth_ext/src/ba/\n" +
                    "\n" +
                    "\n" +
                    "public class AdapterService extends Service {\n" +
                    "\n" +
                    "// 注册的BT配置文件 registerProfileService(ProfileService profile){往里加 mRegisteredProfiles.add(profile)}\n" +
                    "ArrayList<ProfileService> mRegisteredProfiles = new ArrayList<>();  \n" +
                    "\n" +
                    "ArrayList<ProfileService> mRunningProfiles = new ArrayList<>();  // 正在运行的BT配置文件\n" +
                    "\t\n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "\t..........\n" +
                    "        StringBuilder sb = new StringBuilder();\n" +
                    "        for (ProfileService profile : mRegisteredProfiles) {  // Profile: XXXXX\n" +
                    "            profile.dump(sb);\n" +
                    "        }\n" +
                    "        writer.write(sb.toString());\n" +
                    "        writer.flush();\n" +
                    "\t\t}\n" +
                    "}";
            keyWordList.add(bluetooth_1_9);

            KeyWordItem bluetooth_1_10 = new KeyWordItem();
            bluetooth_1_10.keyword = "mAdvertisingServiceUuids";
            bluetooth_1_10.isContain = true;
            bluetooth_1_10.explain="Profile: GattService \n" +
                    "GATT的服务（service）是为了实现设备的某些功能或特征，是一系列数据和行为的集合 \n" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 GattService  extends ProfileService  ";
            bluetooth_1_10.classNameForShuxing = "记录连接扫描的一些日志记录 历史 信息  ";
            bluetooth_1_10.printcode="GattService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_10.printLogUrl="";
            bluetooth_1_10.expain1="********************   AppScanStats Begin ********************\n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/gatt/AppScanStats.java\n" +
                    "\n" +
                    "class AppScanStats {\n" +
                    "long mMaxScanTime = 0;  // 最长扫描时间\n" +
                    "long mMinScanTime = Long.MAX_VALUE;  // 最短 扫描时间\n" +
                    "long mScanStartTime = 0;  // 扫描开始时间戳\n" +
                    "long mTotalScanTime = 0;  //当前已记录的扫描时间累计值\n" +
                    "public String appName;  //  监听 蓝牙扫描的APP名称\n" +
                    "boolean isRegistered = false;  // 是否已经注册开始监听\n" +
                    "final int NUM_SCAN_DURATIONS_KEPT = 5;\n" +
                    "List<LastScan> mLastScans = new ArrayList<LastScan>(NUM_SCAN_DURATIONS_KEPT);\n" +
                    "private int mScansStarted = 0;   // BLE 低功耗扫描次数 累计值\n" +
                    "private int mScansStopped = 0; // BLE 低功耗扫描停止次数 累计值\n" +
                    "\n" +
                    "long mTotalSuspendTime = 0;   // 扫描挂起时间\n" +
                    "int results = 0;  // 蓝牙扫描的结果个数\n" +
                    "\n" +
                    "// key= scannerId  扫描次数\n" +
                    "HashMap<Integer, LastScan> mOngoingScans = new HashMap<Integer, LastScan>();  // \n" +
                    "\n" +
                    "\n" +
                    "   synchronized void dumpToString(StringBuilder sb) {\n" +
                    "        long currTime = SystemClock.elapsedRealtime();\n" +
                    "        long maxScan = mMaxScanTime;\n" +
                    "        long minScan = mMinScanTime;\n" +
                    "        long scanDuration = 0;   // 扫描持续时间\n" +
                    "\n" +
                    "        if (isScanning()) {\n" +
                    "            scanDuration = currTime - mScanStartTime;\n" +
                    "        }\n" +
                    "        minScan = Math.min(scanDuration, minScan);\n" +
                    "        maxScan = Math.max(scanDuration, maxScan);\n" +
                    "\n" +
                    "        if (minScan == Long.MAX_VALUE) {\n" +
                    "            minScan = 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        long avgScan = 0;\n" +
                    "        long totalScanTime = mTotalScanTime + scanDuration;\n" +
                    "        if (mScansStarted > 0) {\n" +
                    "            avgScan = totalScanTime / mScansStarted;\n" +
                    "        }\n" +
                    "\n" +
                    "        sb.append(\"  \" + appName);  // //  监听 蓝牙扫描的APP名称 \n" +
                    "        if (isRegistered) {  //  // 是否已经注册开始监听 \n" +
                    "            sb.append(\" (Registered)\");\n" +
                    "        }\n" +
                    "\n" +
                    "        if (!mLastScans.isEmpty()) {\n" +
                    "            LastScan lastScan = mLastScans.get(mLastScans.size() - 1);  // 最后的那个扫描结果\n" +
                    "            if (lastScan.opportunistic) {  // ?  随机?\n" +
                    "                sb.append(\" (Opportunistic)\");\n" +
                    "            }\n" +
                    "            if (lastScan.background) {  // 是否是前台\n" +
                    "                sb.append(\" (Background)\");\n" +
                    "            }\n" +
                    "            if (lastScan.timeout) {  // 是否超时\n" +
                    "                sb.append(\" (Forced-Opportunistic)\");\n" +
                    "            }\n" +
                    "            if (lastScan.filtered) {  // 是否过滤\n" +
                    "                sb.append(\" (Filtered)\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "        sb.append(\"\\n\");\n" +
                    "\n" +
                    "\t\t// // BLE 低功耗扫描次数 累计值  // BLE 低功耗扫描停止次数 累计值 \n" +
                    "        sb.append(\"  LE scans (started/stopped)         : \" + mScansStarted + \" / \" + mScansStopped  + \"\\n\");\n" +
                    "\t\t// 扫描时间  最小时间  最大时间   平均时间  总的时间\n" +
                    "        sb.append(\"  Scan time in ms (min/max/avg/total): \" + minScan + \" / \" + maxScan + \" / \"  + avgScan + \" / \" + totalScanTime + \"\\n\");\n" +
                    "       \n" +
                    "\n" +
                    "\t   if (mTotalSuspendTime != 0) {\n" +
                    "\t   // 扫描挂起时间\n" +
                    "            sb.append(\"  Total time suspended               : \" + mTotalSuspendTime + \"ms\\n\");\n" +
                    "        }\n" +
                    "\t\t// // 蓝牙扫描的结果个数 \n" +
                    "        sb.append(\"  Total number of results            : \" + results + \"\\n\");\n" +
                    "\n" +
                    "        long currentTime = System.currentTimeMillis();\n" +
                    "        long elapsedRt = SystemClock.elapsedRealtime();\n" +
                    "        if (!mLastScans.isEmpty()) {\n" +
                    "\t\t// 最新扫描结果个数  List<LastScan> mLastScans\n" +
                    "            sb.append(\"  Last \" + mLastScans.size() + \" scans                       :\\n\");\n" +
                    "\n" +
                    "\t\t\t   // 打印当前最新扫描的情况  最近5次的扫描情况\n" +
                    "            for (int i = 0, i < mLastScans.size(), i++) {\n" +
                    "                LastScan scan = mLastScans.get(i);\n" +
                    "                Date timestamp = new Date(currentTime - elapsedRt + scan.timestamp);\n" +
                    "                sb.append(\"    \" + DATE_FORMAT.format(timestamp) + \" - \");\n" +
                    "                sb.append(scan.duration + \"ms \");\n" +
                    "                if (scan.opportunistic) {\n" +
                    "                    sb.append(\"Opp \");\n" +
                    "                }\n" +
                    "                if (scan.background) {\n" +
                    "                    sb.append(\"Back \");\n" +
                    "                }\n" +
                    "                if (scan.timeout) {\n" +
                    "                    sb.append(\"Forced \");\n" +
                    "                }\n" +
                    "                if (scan.filtered) {\n" +
                    "                    sb.append(\"Filter \");\n" +
                    "                }\n" +
                    "                sb.append(scan.results + \" results\");  // 扫描的个数\n" +
                    "                sb.append(\" (\" + scan.scannerId + \")\");\n" +
                    "                sb.append(\"\\n\");\n" +
                    "                if (scan.suspendDuration != 0) {\n" +
                    "                    sb.append(\"      └\" + \" Suspended Time: \" + scan.suspendDuration + \"ms\\n\");\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "\t\t\n" +
                    "//  HashMap<Integer, LastScan> mOngoingScans = new HashMap<Integer, LastScan>()\n" +
                    "// key= scannerId  扫描次数   // value = 扫描的结果\n" +
                    "        if (!mOngoingScans.isEmpty()) {\n" +
                    "            sb.append(\"  Ongoing scans                      :\\n\");\n" +
                    "            for (Integer key : mOngoingScans.keySet()) { // 依次打印  HashMap<Integer, LastScan> mOngoingScans 的值\n" +
                    "                LastScan scan = mOngoingScans.get(key);\n" +
                    "                Date timestamp = new Date(currentTime - elapsedRt + scan.timestamp);\n" +
                    "                sb.append(\"    \" + DATE_FORMAT.format(timestamp) + \" - \");\n" +
                    "                sb.append((elapsedRt - scan.timestamp) + \"ms \");\n" +
                    "                if (scan.opportunistic) {\n" +
                    "                    sb.append(\"Opp \");\n" +
                    "                }\n" +
                    "                if (scan.background) {\n" +
                    "                    sb.append(\"Back \");\n" +
                    "                }\n" +
                    "                if (scan.timeout) {\n" +
                    "                    sb.append(\"Forced \");\n" +
                    "                }\n" +
                    "                if (scan.filtered) {\n" +
                    "                    sb.append(\"Filter \");\n" +
                    "                }\n" +
                    "                if (scan.isSuspended) {\n" +
                    "                    sb.append(\"Suspended \");\n" +
                    "                }\n" +
                    "                sb.append(scan.results + \" results\");\n" +
                    "                sb.append(\" (\" + scan.scannerId + \")\");\n" +
                    "                sb.append(\"\\n\");\n" +
                    "                if (scan.suspendStartTime != 0) {\n" +
                    "                    long duration = scan.suspendDuration + (scan.isSuspended ? (elapsedRt\n" +
                    "                            - scan.suspendStartTime) : 0);\n" +
                    "                    sb.append(\"      └\" + \" Suspended Time: \" + duration + \"ms\\n\");\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        ContextMap.App appEntry = mContextMap.getByName(appName);\n" +
                    "        if (appEntry != null && isRegistered) {\n" +
                    "            sb.append(\"  Application ID                     : \" + appEntry.id + \"\\n\");  // App的 PID\n" +
                    "            sb.append(\"  UUID                               : \" + appEntry.uuid + \"\\n\");  // APP的 UID\n" +
                    "\n" +
                    "            List<ContextMap.Connection> connections = mContextMap.getConnectionByApp(appEntry.id);\n" +
                    "\n" +
                    "            sb.append(\"  Connections: \" + connections.size() + \"\\n\");  //  MAP上下文 连接次数?  历史日志记录?\n" +
                    "\n" +
                    "            Iterator<ContextMap.Connection> ii = connections.iterator();\n" +
                    "            while (ii.hasNext()) {\n" +
                    "                ContextMap.Connection connection = ii.next();\n" +
                    "                long connectionTime = elapsedRt - connection.startTime;\n" +
                    "                Date timestamp = new Date(currentTime - elapsedRt + connection.startTime);\n" +
                    "                sb.append(\"    \" + DATE_FORMAT.format(timestamp) + \" - \");  // 起始连接时间\n" +
                    "                sb.append((connectionTime) + \"ms \");   //  连接时间 \n" +
                    "                sb.append(\": \" + connection.address + \" (\" + connection.connId + \")\\n\");  // 连接地址 和  连接ID\n" +
                    "            }\n" +
                    "        }\n" +
                    "        sb.append(\"\\n\");\n" +
                    "    }\n" +
                    "\n" +
                    "********************   AppScanStats  End ********************\n" +
                    "\n" +
                    "\n" +
                    "********************   ContextMap<C, T>  Begin ********************\n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/gatt/ContextMap.java\n" +
                    "\n" +
                    "class ContextMap<C, T> {\n" +
                    "\n" +
                    "/** Internal map to keep track of logging information by app name */\n" +
                    "// key 是 uid    , value是该APP 的 监听状态信息类\n" +
                    "Map<Integer, AppScanStats> mAppScanStats = new ConcurrentHashMap<Integer, AppScanStats>();\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    void dump(StringBuilder sb) {\n" +
                    "        sb.append(\"  Entries: \" + mAppScanStats.size() + \"\\n\\n\");  \n" +
                    "        Iterator<Map.Entry<Integer, AppScanStats>> it = mAppScanStats.entrySet().iterator();\n" +
                    "        while (it.hasNext()) {  // 遍历所有的 Map项 \n" +
                    "            Map.Entry<Integer, AppScanStats> entry = it.next();\n" +
                    "\n" +
                    "            AppScanStats appScanStats = entry.getValue();\n" +
                    "            appScanStats.dumpToString(sb);  // 打印Map项的 AppScanStats 信息\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "********************   ContextMap<C, T>  End ********************\n" +
                    "\n" +
                    "\n" +
                    "class GattService extends ProfileService {\n" +
                    "List<UUID> mAdvertisingServiceUuids = new ArrayList<UUID>();  // uuid 列表  UUID保证对在同一时空中的所有机器都是唯一的\n" +
                    "int mMaxScanFilters;\n" +
                    "\n" +
                    "// List of our registered scanners.   想要监听蓝牙扫描列表的那些监听类\n" +
                    "class ScannerMap extends ContextMap<IScannerCallback, PendingIntentInfo> {}\n" +
                    "ScannerMap mScannerMap = new ScannerMap();   // 扫描的Map信息\n" +
                    "\n" +
                    "// List of our registered clients. Gatt的客户端 Map // 客户端信息? \n" +
                    "class ClientMap extends ContextMap<IBluetoothGattCallback, Void> {}\n" +
                    "ClientMap mClientMap = new ClientMap();\n" +
                    "\n" +
                    "// List of our registered server apps. 注册的服务端信息? \n" +
                    "class ServerMap extends ContextMap<IBluetoothGattServerCallback, Void> {}\n" +
                    "ServerMap mServerMap = new ServerMap();\n" +
                    "\n" +
                    " // Server handle map.  服务Server提供的 Map\n" +
                    "HandleMap mHandleMap = new HandleMap();\n" +
                    "\n" +
                    "\t\n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    "        super.dump(sb);\n" +
                    "        println(sb, \"mAdvertisingServiceUuids:\");   // 貌似 无作用 \n" +
                    "        for (UUID uuid : mAdvertisingServiceUuids) {\n" +
                    "            println(sb, \"  \" + uuid);\n" +
                    "        }\n" +
                    "        println(sb, \"mMaxScanFilters: \" + mMaxScanFilters);  // 0   貌似也没作用 \n" +
                    "\t\t\n" +
                    "        sb.append(\"\\nGATT Scanner Map\\n\");\n" +
                    "        mScannerMap.dump(sb);   // 扫描的Map信息\n" +
                    "        sb.append(\"GATT Client Map\\n\");\n" +
                    "        mClientMap.dump(sb);   // 客户端信息? \n" +
                    "        sb.append(\"GATT Server Map\\n\");\n" +
                    "        mServerMap.dump(sb);\n" +
                    "        sb.append(\"GATT Handle Map\\n\");\n" +
                    "        mHandleMap.dump(sb);\n" +
                    "    }\n" +
                    "}";
            keyWordList.add(bluetooth_1_10);






            KeyWordItem bluetooth_1_11 = new KeyWordItem();
            bluetooth_1_11.keyword = "GATT Handle Map";
            bluetooth_1_11.explain="Profile: GattService \n" +
                    "GATT的服务（service）是为了实现设备的某些功能或特征，是一系列数据和行为的集合 \n" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 GattService  extends ProfileService  \n" +
                    "class GattService extends ProfileService 中的 HandleMap mHandleMap -》 List<Entry> mEntries 和 Map<Integer【requestId】, Integer【handleid】> mRequestMap ";
            bluetooth_1_11.classNameForShuxing = " 记录请求 Service Chrastices Describe 的日志记录  ";
            bluetooth_1_11.printcode="GattService-->HandleMap   void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            bluetooth_1_11.printLogUrl="";
            bluetooth_1_11.expain1="*************** HandleMap.Entry ***************\n" +
                    "class Entry {\n" +
                    " int serverIf = 0;\n" +
                    " int type = TYPE_UNDEFINED;\n" +
                    " int handle = 0;\n" +
                    " UUID uuid = null;\n" +
                    " int instance = 0;\n" +
                    " int serviceType = 0;  \n" +
                    " int serviceHandle = 0;\n" +
                    " int charHandle = 0;\n" +
                    " boolean started = false;\n" +
                    " boolean advertisePreferred = false;\n" +
                    "} \n" +
                    "\t\t\n" +
                    "*************** HandleMap.Entry ***************\n" +
                    "\n" +
                    "\n" +
                    "class HandleMap {\n" +
                    "\n" +
                    "************************** 中央设备 central  Begin  **************************\n" +
                    "BluetoothGatt mBluetoothGatt = BluetoothDevice.connectGatt() // 获取中央设备\n" +
                    "// ble中有四个角色：\n" +
                    "// 广播者（ Braodcaster ）：广播发送者，是不可连接的设备。 通知周围蓝牙搜被使得自身能被搜索到\n" +
                    "// 观察者（ Observer ）：扫描广播，不能够启动连接。  扫描周围设备  以显示周围打开蓝牙扫搜的设备\n" +
                    "// 广播者和观察者不能建立连接。应用：温度传感器和温度显示器。\n" +
                    "// 外围（ periphery ）：广播发送者，可连接的设备，在单一链路层作为从机\n" +
                    "// 中央（ central ）：  扫描广播，启动连接，在单一或多链路层作为主机。\n" +
                    "// 中央和外围可以进行配对、连接、数据通信。应用：手机和手表。 一个中央可以同时连接多个周边 但是一个周边只能连接一个中央\n" +
                    "\n" +
                    "// BluetoothGatt ： 中央使用和处理数据；\n" +
                    "// BluetoothGattCallback ： 中央的回调。\n" +
                    "// BluetoothGattServer ： 周边提供数据 \n" +
                    "// BluetoothGattServerCallback：周边的回调\n" +
                    "// BluetoothGattService ：Gatt服务\n" +
                    "// BluetoothGattCharacteristic ：Gatt特性\n" +
                    "// BluetoothGattDescriptor ：Gatt描述\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "//搜索附近所有的外围设备   //mBluetoothAdapter.startLeScan(mLeScanCallback);\n" +
                    "//搜索某些uuid的外围设备  // mBluetoothAdapter.startLeScan(uuid[] ,mLeScanCallback);\n" +
                    "//停止扫描  //mBluetoothAdapter.stopLeScan(mLeScanCallback);\n" +
                    "// 扫描回调 device 搜索到的ble设备。  rssi 信号强度 scanRecord 远程设备广告记录的内容（蓝牙名称）\n" +
                    "mLeScanCallback = new BluetoothAdapter.LeScanCallback() {\n" +
                    "void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {}};\n" +
                    "\n" +
                    "// https://blog.csdn.net/vnanyesheshou/article/details/51943870\n" +
                    "// 发起连接请求，获得中央 BluetoothGatt  第二个参数boolean值 false---立即连接  true--标识等待远程设备可用时（在范围内）连接。并不是断开后重新连接。\n" +
                    "// 第三个参数：连接回调 BluetoothGattCallback 类中提供了许多回调，包括  连接状态改变 characteristic 的read、write、change，mtu change等\n" +
                    "BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback) \n" +
                    "BluetoothGatt mBluetoothGatt = device.connectGatt(mContext, false,mGattCallback);\n" +
                    "\n" +
                    "// 连接成功后，发送 gatt服务发现请求   mBluetoothGatt.discoverServices().\n" +
                    "// 发现服务成功 或者 失败都会回调 onServicesDiscovered()函数。\n" +
                    "// 通过 mBluetoothGatt.getServices()获取连接的ble设备所提供的服务列表，返回值类型为List<BluetoothGattService>\n" +
                    "// List<BluetoothGattService> getServices()\n" +
                    " \n" +
                    "//通过服务列表中的  List<BluetoothGattService> ，可以获取到服务所包含的 characteristic\n" +
                    "// BluetoothGattService.getCharacteristics()   返回 List<BluetoothGattCharacteristic> \n" +
                    " \n" +
                    "// 通过BluetoothGattCharacteristic可以获取特征所包含的descriptor\n" +
                    "BluetoothGattCharacteristic.getDescriptors() 返回 返回 List<BluetoothGattDescriptor> \n" +
                    "\n" +
                    "\n" +
                    "// BluetoothGattService、BluetoothGattCharacteristic和BluetoothGattDescriptor三个类中都提供了一个方法getUuid()，\n" +
                    "// 通过该方法可以获取其对应的uuid，从而可以判断是否是自己需要的service、characteristic或者descriptor \n" +
                    "// 通过获取的特征值，可以进行下操作：\n" +
                    "// 1. 写入特性值  characteristic.setValue(data.getBytes());  mBluetoothGatt.writeCharacteristic(characteristic);\n" +
                    "     1.1要想成功写入特征值： characteristic属性满足\n" +
                    "\t    BluetoothGattCharacteristic.PROPERTY_WRITY 或 \n" +
                    "\t    BluetoothGattCharacteristic.PROPERTY_WRITY_NO_RESPONSE\n" +
                    "\t    如果其property都不包含这两个，写特征值writeCharacteristic()函数直接返回false，什么都不做处理\n" +
                    "\t 1.2 其次此characteristic权限应满足 BluetoothGattCharacteristic.PERMISSION_WRITE\n" +
                    "\t     否则onCharacteristicWrite()回调收到 GATT_WRITE_NOT_PERMITTED 回应。\n" +
                    "\t 1.3 写特征值前可以设置写的类型setWriteType()，写类型有三种\n" +
                    "\t      WRITE_TYPE_DEFAULT  默认类型，需要外围设备的确认，也就是需要外围设备的回应，这样才能继续发送写。\n" +
                    "\t\t  WRITE_TYPE_NO_RESPONSE 设置该类型不需要外围设备的回应，可以继续写数据。加快传输速率。\n" +
                    "\t\t  WRITE_TYPE_SIGNED  写特征携带认证签名，具体作用不太清楚。\n" +
                    "\t 1.4 外围设备收到中央写特征值的请求，会回调 onCharacteristicWriteRequest\n" +
                    "\t     如果此次请求需要回应，则外围设备回应 BluetoothGattServer.sendResponse\n" +
                    "\t\t 中央设备收到响应，回调onCharacteristicWrite(BluetoothGatt gatt,BluetoothGattCharacteristic characteristic, int status) \n" +
                    "\t 1.5 int state 回调的状态分类\n" +
                    "\t     final int GATT_SUCCESS = 0; /** 交互成功  A GATT operation completed successfully */\n" +
                    "         final int GATT_READ_NOT_PERMITTED = 0x2; /** 没有读的权限 GATT read operation is not permitted */\n" +
                    "         final int GATT_WRITE_NOT_PERMITTED = 0x3; /** 没有写的权限  GATT write operation is not permitted */\n" +
                    "         final int GATT_INSUFFICIENT_AUTHENTICATION = 0x5; /** Insufficient authentication for a given operation */\n" +
                    "         final int GATT_REQUEST_NOT_SUPPORTED = 0x6; /** The given request is not supported */\n" +
                    "         final int GATT_INSUFFICIENT_ENCRYPTION = 0xf; /** Insufficient encryption for a given operation */\n" +
                    "         final int GATT_INVALID_OFFSET = 0x7;  /** A read or write operation was requested with an invalid offset */\n" +
                    "         final int GATT_INVALID_ATTRIBUTE_LENGTH = 0xd;    /** A write operation exceeds the maximum length of the attribute */\n" +
                    "         final int GATT_CONNECTION_CONGESTED = 0x8f; /** A remote device connection is congested. */\n" +
                    "         final int GATT_FAILURE = 0x101; /** 交互失败 A GATT operation failed, errors other than the above */\n" +
                    "\t \n" +
                    "// 2. 读取特性值  mBluetoothGatt.readCharacteristic(characteristic);\n" +
                    "     2.1 该characteristic属性需包含PROPERTY_READ，否则直接返回false（具体可以看BluetoothGatt源码）。\n" +
                    "         该characteristic权限 property  应满足BluetoothGattCharacteristic.PERMISSION_READ ，\n" +
                    "\t\t 否则onCharacteristicRead()回调收到 GATT_READ_NOT_PERMITTED 回应。\n" +
                    "     2.2 外围设备接收到中央设备的读特征值请求，则会回调 onCharacteristicReadRequest（）函数\n" +
                    "\t     外围设备应该回应此请求 sendResponse\n" +
                    "\t\t 中央设备收到响应回调 onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)\n" +
                    "\n" +
                    "// 3.订阅特性值  mBluetoothGatt.setCharacteristicNotification(characteristic, true);  //第二个参数：true则订阅该特征，false则取消订阅。\n" +
                    "     3.1 当指定Characteristic值发生变化时，是否接收通知 就是 订阅的意思  就是 值监听\n" +
                    "\t 3.2 如果存在值监听 如果Characteristic发生变化时，\n" +
                    "\t     会回调方法 onCharacteristicChanged(BluetoothGatt gatt,   BluetoothGattCharacteristic characteristic)\n" +
                    "\t\t 通过参数characteristic，可获得getValue获得其中的内容。\n" +
                    "\t 3.3 该Characteristic 特征属性 property 满足PROPERTY_NOTIFY 才能有权限 提供监听的能力\n" +
                    "\t 3.4 注意：虽然订阅了该特征，并且该特征属性也满足PROPERTY_NOTIFY，但是并没有收到特征值改变的回调。这是为什么呢？\n" +
                    "\t     查看sdk中的demo，发现需要写一下Descriptor。这样就可以正常监听特征值的改变了。\n" +
                    "\t\t\n" +
                    "         //CLIENT_CHARACTERISTIC_CONFIG = \"00002902-0000-1000-8000-00805f9b34fb\"\n" +
                    "         BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(SampleGattAttributes.CLIENT_CHARACTERISTIC_CONFIG));\n" +
                    "         descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);\n" +
                    "\t\t mBluetoothGatt.writeDescriptor(descriptor);\n" +
                    "\n" +
                    "// 中央设备的其他一些方法\n" +
                    "// readDescriptor(descriptor)  读取描述\n" +
                    "// writeDescriptor(descriptor)  写描述\n" +
                    "// readRemoteRssi()            读取连接设备的rssi。\n" +
                    "// disconnect();              断开bel连接。\n" +
                    "// close();                 关闭中央设备。\n" +
                    "\n" +
                    "************************** 中央设备 central  Begin  **************************\n" +
                    "\n" +
                    "************************** 外围设备 periphery  Begin  **************************\n" +
                    "1.获取/打开周边（外围）\n" +
                    "//其中callback是一个MyGattServerCallback（继承了BluetoothGattServerCallback）对象。\n" +
                    "BluetoothGattServer openGattServer(Context context,BluetoothGattServerCallback callback) \n" +
                    "BluetoothGattServer mGattServer = mBluetoothManager.openGattServer(mContext, callback);\n" +
                    "\n" +
                    "\n" +
                    "2.初始化描述、特性和服务 \n" +
                    "BluetoothGattDescriptor gattDesc = new BluetoothGattDescriptor(UUID.fromString(DESC_UUID), descPermissions); //描述: \n" +
                    "//特性 :\n" +
                    "final int properties = BluetoothGattCharacteristic.PROPERTY_READ\n" +
                    "| BluetoothGattCharacteristic.PROPERTY_WRITE  \n" +
                    "| BluetoothGattCharacteristic.PROPERTY_NOTIFY; \n" +
                    "// 权限\n" +
                    "final int permissions = BluetoothGattCharacteristic.PERMISSION_READ;\n" +
                    "| BluetoothGattCharacteristic.PERMISSION_WRITE;\n" +
                    "\n" +
                    "// 创建 Characteristic  并设置 特性  权限  和  UUID唯一码 \n" +
                    "BluetoothGattCharacteristic gattChar = new BluetoothGattCharacteristic(UUID.fromString(CHAR_UUID), properties, permissions);\n" +
                    "\n" +
                    "// 往 Characteristic  添加 Descriptor\n" +
                    "gattChar.addDescriptor(gattDesc);\n" +
                    "\n" +
                    "property 表示属性。permission 表示权限。这两个都和权限相关\n" +
                    "如果property未设置PROPERTY_READ，permission设置PERMISSION_READ，则中央设备readCharacteristic主动读取特征值方法返回false，此操作失败\n" +
                    "而如果property设置PROPERTY_READ，permission未设置PERMISSION_READ，则中央设备readCharacteristic主动读取特征值方法返回true，此操作成功，外围设备发送响应，中央设备收到响应 GATT_READ_NOT_PERMITTED。\n" +
                    "所以说如果想要characteristic可读，则这两个都要设置。\n" +
                    "\n" +
                    "\n" +
                    "3. 把 Characteristic 添加到 Server 上 \n" +
                    "//服务:\n" +
                    "第二个参数为service type,\n" +
                    "SERVICE_TYPE_PRIMARY    基础服务、主要服务。\n" +
                    "SERVICE_TYPE_SECONDARY  辅助服务（由初级服务包含在内）, 小弟  由 基础服务、主要服务包含\n" +
                    "BluetoothGattService bs = new BluetoothGattService( UUID.fromString(SERV_UUID),BluetoothGattService.SERVICE_TYPE_PRIMARY);\n" +
                    "// BluetoothGattService.addCharacteristic(BluetoothGattCharacteristic)\n" +
                    "bs.addCharacteristic(gattChar);  //   往服务 Service 添加 Characteristic\n" +
                    "\n" +
                    "4. BluetoothGattService 周边外围设备的方法 \n" +
                    "BluetoothGattService 类中方法\n" +
                    "addService(bluetoothGattService)， // 添加服务 bluetoothGattService 将辅助服务添加到主要服务中  \n" +
                    "getIncludeedServices() 获取包含的服务列表\n" +
                    "getType() 获取服务的type\n" +
                    "getUuid() 获取服务的UUID\n" +
                    "mGattServer.cancelConnection(device);  // 连接成功后 外围可以断开连接。\n" +
                    "响应central发起的gatt服务发现请求，回应服务信息。\n" +
                    "响应central发起的gatt特性发现请求，回应特性信息。\n" +
                    "响应central发起的gatt描述发现请求，回应描述信息。\n" +
                    "\n" +
                    "5. 对central的读写做响应。\n" +
                    "   5.1 回应特性值\n" +
                    "       5.1.1 监听连接状态----onConnectionStateChange\n" +
                    "             MyGattServerCallback extends BluetoothGattServerCallback{}\n" +
                    "\t         BluetoothGattServerCallback 其中有几个常用的方法:\n" +
                    "\t         onConnectionStateChange(BluetoothDevice device, int status,  int newState)  监听连接状态\n" +
                    "\t         device远程设备， newStateble 连接状态 status 操作状态\n" +
                    "\t         newStateble 为 BluetoothProfile.STATE_CONNECTED和BluetoothProfile.STATE_DISCONNECTED\n" +
                    "\t\t\t \n" +
                    "\t   5.1.2 监听中心设备读 Characteristic 的请求----onCharacteristicReadRequest\n" +
                    "\t         onCharacteristicReadRequest(BluetoothDevice device,  int requestId, int offset,BluetoothGattCharacteristic characteristic)\n" +
                    "\t         requestId 请求的标识。 offset 特性值偏移量。 Characteristic  要读的特性。 \n" +
                    "\t\t\t 此方法要求作出响应。 mGattServer.sendResponse(device, requestId,BluetoothGatt.GATT_SUCCESS, offset, null); 最后一个参数可以设置传的数据，byte［］类型的\n" +
                    "       \n" +
                    "\t   5.1.3 监听中心设备写 Characteristic 的请求 ---- onCharacteristicWriteRequest\n" +
                    "\t         onCharacteristicWriteRequest(BluetoothDevice device,  int requestId, BluetoothGattCharacteristic characteristic,boolean preparedWrite, boolean responseNeeded, int offset, byte[] value)\n" +
                    "\t\t\t preparedWrite true则写操作必须排队等待稍后执行   responseNeeded 是否需要响应   value 写的数据\n" +
                    "\t\t\t 需要响应则必须  mGattServer.sendResponse(device, requestId,BluetoothGatt.GATT_SUCCESS, offset, null); 最后一个参数可以设置传的数据，byte［］类型的\n" +
                    "\t\t\t \n" +
                    "\t    5.1.4 更新特征值  外围设备向中心设备不能发送数据，必须通过 notify 或者 indicate 的方式，andorid只发现notify接口\n" +
                    "\t\tcharacteristic.setValue(res.getBytes());\n" +
                    "        mGattServer.notifyCharacteristicChanged(device,characteristic, false);  最后一个参数表示是否需要客户端确认。\n" +
                    "\n" +
                    "************************** 外围设备 periphery  End  **************************\n" +
                    "\n" +
                    "\n" +
                    "***********  HandleMap Begin  ***********\n" +
                    "// Gatt：(Generic Attribute Profile)—通用属性配置文件，用于在ble 低功耗蓝牙 链路上发送和接收被称为“属性”的数据块\n" +
                    "// 目前所有的ble应用都是基于GATT的。一个设备可以实现多个配置文件。\n" +
                    "// ble交互的桥梁是Service、Characteristic、Desciptor\n" +
                    "// Characteristic： 可以理解为一个数据类型，它包括一个value和0至多个对此characteristic的描述（Descriptor）\n" +
                    "// Descriptor： 对Characterisctic的描述，如范围、单位等\n" +
                    "// Service：Characteristic的集合。它可以包含多个Characteristic。\n" +
                    "\n" +
                    "// 一个ble终端可以包含多个Service\n" +
                    "//一个 Service 可以包含多个Characteristic\n" +
                    "// 一个 Characteristic 包含一个value和多个Descriptor\n" +
                    "// 一个 Descriptor 包含一个value\n" +
                    "// Service Characteristic  Descriptor    这三部分都由UUID作为唯一标示符，以此区分。 \n" +
                    "// UUID（Universally Unique Identifier），含义是通用唯一识别码，它是在一定范围内唯一的机器生成的标识符。\n" +
                    "// 标准的UUID格式为：xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx (8-4-4-4-12)\n" +
                    "\n" +
                    "public static final int TYPE_UNDEFINED = 0;\n" +
                    "public static final int TYPE_SERVICE = 1;\n" +
                    "public static final int TYPE_CHARACTERISTIC = 2;\n" +
                    "public static final int TYPE_DESCRIPTOR = 3;\n" +
                    "\t\n" +
                    "List<Entry> mEntries = null;   // Entry 列表? \n" +
                    "\n" +
                    "// GattService.java 的 onServerReadXXX 方法会往这里添加数据    例如: onServerReadCharacteristic\n" +
                    "// 会被 JNI 反向 调用   /packages/apps/Bluetooth/jni/com_android_bluetooth_gatt.cpp\n" +
                    "Map<Integer, Integer> mRequestMap = null;  // mRequestMap.put(requestId, handle);  , 请求ID   hadnle的ID? \n" +
                    "int mLastCharacteristic = 0;\n" +
                    "\t\n" +
                    "    void dump(StringBuilder sb) {\n" +
                    "        sb.append(\"  Entries: \" + mEntries.size() + \"\\n\");  // // Entry 列表? \n" +
                    "        sb.append(\"  Requests: \" + mRequestMap.size() + \"\\n\");  // mRequestMap.put(requestId, handle);  请求ID   hadnle的ID? \n" +
                    "        for (Entry entry : mEntries) {\n" +
                    "            sb.append(\"  \" + entry.serverIf + \": [\" + entry.handle + \"] \");\n" +
                    "            switch (entry.type) {\n" +
                    "                case TYPE_SERVICE:  // 请求服务\n" +
                    "                    sb.append(\"Service \" + entry.uuid);\n" +
                    "                    sb.append(\", started \" + entry.started);\n" +
                    "                    break;\n" +
                    "                case TYPE_CHARACTERISTIC:  // 请求功能\n" +
                    "                    sb.append(\"  Characteristic \" + entry.uuid);\n" +
                    "                    break;\n" +
                    "                case TYPE_DESCRIPTOR:  // 请求描述\n" +
                    "                    sb.append(\"    Descriptor \" + entry.uuid);\n" +
                    "                    break;\n" +
                    "            }\n" +
                    "\n" +
                    "            sb.append(\"\\n\");\n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "***********  HandleMap End ***********\n" +
                    "class GattService{\n" +
                    "HandleMap mHandleMap = new HandleMap(); //  // Server handle map.  服务Server提供的 Map\n" +
                    "\n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    "\t.....\n" +
                    "        sb.append(\"GATT Handle Map\\n\");\n" +
                    "        mHandleMap.dump(sb);\n" +
                    "    }\n" +
                    "}\n" +
                    "\t";
            keyWordList.add(bluetooth_1_11);



            KeyWordItem bluetooth_1_12 = new KeyWordItem();
            bluetooth_1_12.keyword = "Profile: HeadsetService";
            bluetooth_1_12.explain="Profile: HeadsetService \n" +
                    "耳机线控服务（HeadsetService）是为了实现设备蓝牙耳机通话 语音播放 的某些功能或特征，是一系列数据和行为的集合 \n" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】class HeadsetService extends ProfileService   \n" +
                    "";
            bluetooth_1_12.classNameForShuxing = " 蓝牙耳机服务 HeadsetService 的日志记录  ";
            bluetooth_1_12.printcode="HeadsetService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_12.printLogUrl="";
            bluetooth_1_12.expain1="******************** BluetoothDevice Begin ********************\n" +
                    "public final class BluetoothDevice implements Parcelable {\n" +
                    "\n" +
                    "String mAddress;   // fuck  2000行 就打印一个字符串\n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        return mAddress;\n" +
                    "    }\n" +
                    "}\n" +
                    "******************** BluetoothDevice End ********************\n" +
                    "\n" +
                    "******************** HeadsetStateMachine Begin ********************\n" +
                    "HeadsetStateMachine extends StateMachine {\n" +
                    "BluetoothDevice mDevice;  // 当前状态机对应的 蓝牙设备\n" +
                    "HeadsetStateBase mCurrentState;  // 当前状态机所处的栈顶状态\n" +
                    "HeadsetStateBase mPrevState;  // 当前状态机所处的栈顶状态 之前的栈顶状态\n" +
                    "\n" +
                    "// 当前连接蓝牙状态\n" +
                    "// 连接状态-断开      int STATE_DISCONNECTED  = 0 =  BluetoothProtoEnums.CONNECTION_STATE_DISCONNECTED;\n" +
                    "// 连接状态-正在连接  int STATE_CONNECTING    = 1 =  BluetoothProtoEnums.CONNECTION_STATE_CONNECTING;\n" +
                    "// 连接状态-已连接    int STATE_CONNECTED     = 2 =  BluetoothProtoEnums.CONNECTION_STATE_CONNECTED;\n" +
                    "// 连接状态-正在断开  int STATE_DISCONNECTING = 4 =  BluetoothProtoEnums.CONNECTION_STATE_DISCONNECTING;\n" +
                    "\n" +
                    "// 当前音频状态 \n" +
                    "//BluetoothHeadset.STATE_AUDIO_DISCONNECTED\n" +
                    "//BluetoothHeadset.STATE_AUDIO_CONNECTING\n" +
                    "//BluetoothHeadset.STATE_AUDIO_CONNECTED\n" +
                    "\t \n" +
                    "boolean mNeedDialingOutReply;  // HSP specific  是否需要回拨? \n" +
                    "\n" +
                    "int mSpeakerVolume;  // 通话音量大小   intent.getIntExtra(AudioManager.EXTRA_VOLUME_STREAM_VALUE, 0) HeadsetHalConstants.VOLUME_TYPE_SPK\n" +
                    "int mMicVolume;  // 输出音量大小   HeadsetHalConstants.VOLUME_TYPE_MIC \n" +
                    " long mConnectingTimestampMs = Long.MIN_VALUE; // 链接上蓝牙耳机时的时间戳 The timestamp when the device entered connecting/connected state\n" +
                    " \n" +
                    " \n" +
                    "     public void dump(StringBuilder sb) {\n" +
                    "        ProfileService.println(sb, \"  mCurrentDevice: \" + mDevice);  // 当前状态机对应的 蓝牙设备\n" +
                    "        ProfileService.println(sb, \"  mCurrentState: \" + mCurrentState);\n" +
                    "        ProfileService.println(sb, \"  mPrevState: \" + mPrevState);\n" +
                    "        ProfileService.println(sb, \"  mConnectionState: \" + getConnectionState());  // 当前连接蓝牙状态\n" +
                    "        ProfileService.println(sb, \"  mAudioState: \" + getAudioState());  //  音频状态? \n" +
                    "        ProfileService.println(sb, \"  mNeedDialingOutReply: \" + mNeedDialingOutReply);  // HSP specific  是否需要回拨? \n" +
                    "        ProfileService.println(sb, \"  mSpeakerVolume: \" + mSpeakerVolume);  // 通话音量大小 \n" +
                    "        ProfileService.println(sb, \"  mMicVolume: \" + mMicVolume);\n" +
                    "        ProfileService.println(sb, \"  mConnectingTimestampMs(uptimeMillis): \" + mConnectingTimestampMs);\n" +
                    "        ProfileService.println(sb, \"  StateMachine: \" + this);  // 打印 类名称   HeadsetStateMachine.java\n" +
                    "        // Dump the state machine logs\n" +
                    "        StringWriter stringWriter = new StringWriter();\n" +
                    "        PrintWriter printWriter = new PrintWriter(stringWriter);\n" +
                    "        super.dump(new FileDescriptor(), printWriter, new String[]{});  //  打印 状态机处理的事件列表 \n" +
                    "        printWriter.flush();\n" +
                    "        stringWriter.flush();\n" +
                    "        ProfileService.println(sb, \"  StateMachineLog:\");\n" +
                    "        Scanner scanner = new Scanner(stringWriter.toString());\n" +
                    "        while (scanner.hasNextLine()) {\n" +
                    "            String line = scanner.nextLine();\n" +
                    "            ProfileService.println(sb, \"    \" + line);\n" +
                    "        }\n" +
                    "        scanner.close();\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "\n" +
                    " \n" +
                    "   public synchronized BluetoothDevice getDevice() {\n" +
                    "        return mDevice;\n" +
                    "    }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "addState(mDisconnected);\n" +
                    "addState(mConnecting);\n" +
                    "addState(mDisconnecting);\n" +
                    "addState(mConnected);\n" +
                    "addState(mAudioOn);\n" +
                    "addState(mAudioConnecting);\n" +
                    "addState(mAudioDisconnecting);\n" +
                    "setInitialState(mDisconnected);\n" +
                    "\n" +
                    "\n" +
                    "\t   A Bluetooth Handset StateMachine\n" +
                    "\t\t\t\t(Disconnected)\n" +
                    "\t\t\t\t   |      ^\n" +
                    "\t\t   CONNECT |      | DISCONNECTED\n" +
                    "\t\t\t\t   V      |\n" +
                    "\t\t  (Connecting)   (Disconnecting)\n" +
                    "\t\t\t\t   |      ^\n" +
                    "\t\t CONNECTED |      | DISCONNECT\n" +
                    "\t\t\t\t   V      |\n" +
                    "\t\t\t\t  (Connected)\n" +
                    "\t\t\t\t   |      ^\n" +
                    "\t CONNECT_AUDIO |      | AUDIO_DISCONNECTED\n" +
                    "\t\t\t\t   V      |\n" +
                    "\t (AudioConnecting)   (AudioDisconnecting)\n" +
                    "\t\t\t\t   |      ^\n" +
                    "   AUDIO_CONNECTED |      | DISCONNECT_AUDIO\n" +
                    "\t\t\t\t   V      |\n" +
                    "\t\t\t\t   (AudioOn)\n" +
                    " \n" +
                    "  各个状态不能形成栈  自己就是栈顶 \n" +
                    "Disconnected 【初始状态】   Connecting  Disconnecting   Connected    AudioOn  AudioConnecting  AudioDisconnecting\n" +
                    "\n" +
                    "******************** HeadsetStateMachine End ********************\n" +
                    "\n" +
                    "public class HeadsetService extends ProfileService {\n" +
                    "// mSetMaxConfig = mMaxHeadsetConnections = 【 (AdapterService) AdapterService AdapterService.getAdapterService()】 mAdapterService.getMaxConnectedAudioDevices();\n" +
                    "// 最大连接语音数量  \n" +
                    "// int configDefaultMaxConnectedAudioDevices = mService.getResources().getInteger( com.android.internal.R.integer.config_bluetooth_max_connected_audio_devices);  // <integer name=\"config_bluetooth_max_connected_audio_devices\">5</integer>\n" +
                    "//int propertyOverlayedMaxConnectedAudioDevices =SystemProperties.getInt(MAX_CONNECTED_AUDIO_DEVICES_PROPERTY, configDefaultMaxConnectedAudioDevices);   adb shell getprop persist.bluetooth.maxconnectedaudiodevices\n" +
                    "// int MAX_CONNECTED_AUDIO_DEVICES_LOWER_BOND = 1;   int MAX_CONNECTED_AUDIO_DEVICES_UPPER_BOUND = 5;  //\n" +
                    "// mMaxConnectedAudioDevices = Math.min(Math.max(propertyOverlayedMaxConnectedAudioDevices, MAX_CONNECTED_AUDIO_DEVICES_LOWER_BOND), MAX_CONNECTED_AUDIO_DEVICES_UPPER_BOUND); //\n" +
                    "int mMaxHeadsetConnections = 1;  // 蓝牙最大连接其他语音设备数量  代码规定 最大为5  来自于  AdapterProperties.java  int mMaxConnectedAudioDevices = 1;\n" +
                    "\n" +
                    "AdapterService mAdapterService; // 适配服务?\n" +
                    "BluetoothDevice mActiveDevice;  // 远端连接设备\n" +
                    "boolean mInbandRingingRuntimeDisable;  //  InbandRinging 手机预置蓝牙来电铃声 是否设置  true--没有设置  false--设置\n" +
                    "\n" +
                    "// A2DP：是一种单向的高品质音频数据传输链路，通常用于播放立体声音乐；\n" +
                    "// SCO： 则是一种双向的音频数据的传输链路，该链路只支持8K及16K单声道的音频数据，只能用于普通语音的传输，若用于播放音乐那就只能呵呵了。\n" +
                    "// 两者的主要区别是： A2DP只能播放，默认是打开的，而SCO既能录音也能播放，默认是关闭的。 如果要录音肯定要打开sco啦，因此调用上面的setBluetoothScoOn(boolean on)就可以通过蓝牙耳机录音、播放音频了，录完、播放完记得要关闭。\n" +
                    "\n" +
                    "boolean mAudioRouteAllowed = true;  // 是否允许 蓝牙耳机 进行音频输出? 音频输出设备是如何决定的 \n" +
                    "\n" +
                    "boolean mVoiceRecognitionStarted;   // 语音识别? \n" +
                    "\n" +
                    "VoiceRecognitionTimeoutEvent mVoiceRecognitionTimeoutEvent;  // 语音识别超时事件 ? \n" +
                    "\n" +
                    "boolean mVirtualCallStarted;  // 虚拟通话开始? \n" +
                    "\n" +
                    "DialingOutTimeoutEvent mDialingOutTimeoutEvent; // 通话超时事件? \n" +
                    "\n" +
                    "boolean mForceScoAudio;  //  强制使用 SCO 技术 传输数据?\n" +
                    "\n" +
                    "boolean mStarted;   // 启动标识  执行 HeadsetService.start() 方法设置为 true   stop()   设置为 false\n" +
                    "boolean mCreated;  // 创建标识  执行 HeadsetService.create() 方法设置为 true   cleanup()设置为 false\n" +
                    "\t\n" +
                    "//  HeadsetObjectsFactory.getInstance().makeSystemInterface(this).init(); \n" +
                    "HeadsetSystemInterface mSystemInterface; // 底层接口? \n" +
                    "\n" +
                    "HashMap<BluetoothDevice, HeadsetStateMachine> mStateMachines = new HashMap<>();  // MAP《蓝牙设备--蓝牙耳机状态机》\n" +
                    "\n" +
                    " \n" +
                    "public void dump(StringBuilder sb) {\n" +
                    "synchronized (mStateMachines) {\n" +
                    "super.dump(sb);\n" +
                    "ProfileService.println(sb, \"mMaxHeadsetConnections: \" + mMaxHeadsetConnections);  // 当前 HeadSet 蓝牙耳机 最大连接数量\n" +
                    "ProfileService.println(sb, \"DefaultMaxHeadsetConnections: \" + mAdapterService.getMaxConnectedAudioDevices()); // 配置文件AdapterProperties 中规定的最大连接数\n" +
                    "ProfileService.println(sb, \"mActiveDevice: \" + mActiveDevice);\n" +
                    "//  蓝牙耳机支持In-band ring，则来电话后听到的铃声是手机预设的来电铃声；如果不支持，听到的就是蓝牙耳机自带的来电铃声 \n" +
                    "ProfileService.println(sb, \"isInbandRingingEnabled: \" + isInbandRingingEnabled());  // 是否使能  InbandRinging 手机预置蓝牙来电铃声  用不用的问题\n" +
                    "ProfileService.println(sb,\"isInbandRingingSupported: \" + BluetoothHeadset.isInbandRingingSupported(this));  // 是否支持InbandRinging  有没有的问题\n" +
                    "ProfileService.println(sb,\"mInbandRingingRuntimeDisable: \" + mInbandRingingRuntimeDisable);  //  是否设置蓝牙耳机内置来电铃声  true--没有设置  false--设置\n" +
                    "ProfileService.println(sb, \"mAudioRouteAllowed: \" + mAudioRouteAllowed);  //  boolean mAudioRouteAllowed = true;  // 是否允许 蓝牙耳机 进行音频输出?\n" +
                    "ProfileService.println(sb, \"mVoiceRecognitionStarted: \" + mVoiceRecognitionStarted);  // boolean mVoiceRecognitionStarted;   // 语音识别? \n" +
                    "ProfileService.println(sb,\"mVoiceRecognitionTimeoutEvent: \" + mVoiceRecognitionTimeoutEvent);  // 语音识别超时事件 ? \n" +
                    "ProfileService.println(sb, \"mVirtualCallStarted: \" + mVirtualCallStarted); // 虚拟通话开始? \n" +
                    "ProfileService.println(sb, \"mDialingOutTimeoutEvent: \" + mDialingOutTimeoutEvent);  // 通话超时事件? \n" +
                    "ProfileService.println(sb, \"mForceScoAudio: \" + mForceScoAudio);   // boolean mForceScoAudio;  //  强制使用 SCO 技术 传输数据?\n" +
                    "ProfileService.println(sb, \"mCreated: \" + mCreated);   // true---执行 start() 函数   false---执行 stop() 函数\n" +
                    "ProfileService.println(sb, \"mStarted: \" + mStarted); // true---执行 create() 函数    false---执行 cleanup() 函数\n" +
                    "ProfileService.println(sb,\"AudioManager.isBluetoothScoOn(): \" + mSystemInterface.getAudioManager().isBluetoothScoOn());  // SCO 模式是否打开\n" +
                    "ProfileService.println(sb, \"Telecom.isInCall(): \" + mSystemInterface.isInCall());   // 蓝牙耳机是否正在通话\n" +
                    "ProfileService.println(sb, \"Telecom.isRinging(): \" + mSystemInterface.isRinging());  // 蓝牙耳机是否正在响来电铃声 正在来电响铃\n" +
                    "\tfor (HeadsetStateMachine stateMachine : mStateMachines.values()) { // 打印所有的 蓝牙状态机 HeadsetStateMachine\n" +
                    "\t\tProfileService.println(sb,\"==== StateMachine for \" + stateMachine.getDevice() + \" ====\");  \n" +
                    "\t\tstateMachine.dump(sb);\n" +
                    "\t}\n" +
                    "  }\n" +
                    "}";
            keyWordList.add(bluetooth_1_12);



            KeyWordItem bluetooth_1_13 = new KeyWordItem();
            bluetooth_1_13.keyword = "Profile: A2dpService";
            bluetooth_1_13.explain="Profile: A2dpService \n" +
                    "A2dpService （service）A2DP全名是Advanced Audio Distribution Profile 蓝牙音频传输模型协定  \n" +
                    " A2DP是能够采用耳机内的芯片来堆栈数据，达到声音的高清晰度。然而并非支持A2DP的耳机就是蓝牙立体声耳机，立体声实现的基本要求是双声道，所以单声道的蓝牙耳机是不能实现立体声的\n" +
                    "A2DP定义了ACL(Asynchronous Connectionless 异步无连接)信道上传送单声道或立体声等高质量音频信息的协议和过程 \n" +
                    "A2DP取决于GAP(Generic Access Profile 通用接入协议)和GAVDP(Generic Audio /Video Distribution Profile 通用音视频分布协议).\n" +
                    "后者定义音频,视频流等建立所需要的过程.A2DP则定义建立音视频流所需要的参数和流程 \n" +
                    "典型应用:   立体声蓝牙耳机   无线蓝牙音箱" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 A2dpService  extends ProfileService  \n" +
                    "class A2dpService extends ProfileService ";
            bluetooth_1_13.classNameForShuxing = " 记录 A2dpService 服务 的日志记录  ";
            bluetooth_1_13.printcode="A2dpService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_13.printLogUrl="";
            bluetooth_1_13.expain1="*********************** BluetoothCodecStatus Begin ***********************\n" +
                    "public final class BluetoothCodecConfig implements Parcelable {\n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        String sampleRateStr = null;  // 速率\n" +
                    "        if (mSampleRate == SAMPLE_RATE_NONE) {        sampleRateStr = appendCapabilityToString(sampleRateStr, \"NONE\");  }\n" +
                    "        if ((mSampleRate & SAMPLE_RATE_44100) != 0)  {sampleRateStr = appendCapabilityToString(sampleRateStr, \"44100\");  }\n" +
                    "        if ((mSampleRate & SAMPLE_RATE_48000) != 0)  {sampleRateStr = appendCapabilityToString(sampleRateStr, \"48000\"); }\n" +
                    "        if ((mSampleRate & SAMPLE_RATE_88200) != 0)  {sampleRateStr = appendCapabilityToString(sampleRateStr, \"88200\"); }\n" +
                    "        if ((mSampleRate & SAMPLE_RATE_96000) != 0)  {sampleRateStr = appendCapabilityToString(sampleRateStr, \"96000\");}\n" +
                    "        if ((mSampleRate & SAMPLE_RATE_176400) != 0) {sampleRateStr = appendCapabilityToString(sampleRateStr, \"176400\"); }\n" +
                    "        if ((mSampleRate & SAMPLE_RATE_192000) != 0) {sampleRateStr = appendCapabilityToString(sampleRateStr, \"192000\");}\n" +
                    "        String bitsPerSampleStr = null;  // 每秒比特位\n" +
                    "        if (mBitsPerSample == BITS_PER_SAMPLE_NONE) { bitsPerSampleStr = appendCapabilityToString(bitsPerSampleStr, \"NONE\");}\n" +
                    "        if ((mBitsPerSample & BITS_PER_SAMPLE_16) != 0) {bitsPerSampleStr = appendCapabilityToString(bitsPerSampleStr, \"16\"); }\n" +
                    "        if ((mBitsPerSample & BITS_PER_SAMPLE_24) != 0) { bitsPerSampleStr = appendCapabilityToString(bitsPerSampleStr, \"24\");}\n" +
                    "        if ((mBitsPerSample & BITS_PER_SAMPLE_32) != 0) { bitsPerSampleStr = appendCapabilityToString(bitsPerSampleStr, \"32\"); }\n" +
                    "\n" +
                    "        String channelModeStr = null;  // 信道类型?\n" +
                    "        if (mChannelMode == CHANNEL_MODE_NONE) {channelModeStr = appendCapabilityToString(channelModeStr, \"NONE\");}\n" +
                    "        if ((mChannelMode & CHANNEL_MODE_MONO) != 0) {channelModeStr = appendCapabilityToString(channelModeStr, \"MONO\");}\n" +
                    "        if ((mChannelMode & CHANNEL_MODE_STEREO) != 0) {channelModeStr = appendCapabilityToString(channelModeStr, \"STEREO\");}\n" +
                    "        return \"{codecName:\" + getCodecName()\n" +
                    "                + \",mCodecType:\" + mCodecType\n" +
                    "                + \",mCodecPriority:\" + mCodecPriority\n" +
                    "                + \",mSampleRate:\" + String.format(\"0x%x\", mSampleRate)\n" +
                    "                + \"(\" + sampleRateStr + \")\"\n" +
                    "                + \",mBitsPerSample:\" + String.format(\"0x%x\", mBitsPerSample)\n" +
                    "                + \"(\" + bitsPerSampleStr + \")\"\n" +
                    "                + \",mChannelMode:\" + String.format(\"0x%x\", mChannelMode)\n" +
                    "                + \"(\" + channelModeStr + \")\"\n" +
                    "                + \",mCodecSpecific1:\" + mCodecSpecific1\n" +
                    "                + \",mCodecSpecific2:\" + mCodecSpecific2\n" +
                    "                + \",mCodecSpecific3:\" + mCodecSpecific3\n" +
                    "                + \",mCodecSpecific4:\" + mCodecSpecific4 + \"}\";\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "int mCodecType;  // 类型 \n" +
                    "int mCodecPriority;  // 级别、\n" +
                    "final int mSampleRate;  // 速率\n" +
                    "final int mBitsPerSample;  // 比特宽度\n" +
                    "int mChannelMode; // 信道模式?\n" +
                    "long mCodecSpecific1;   // 规格1? \n" +
                    "long mCodecSpecific2;   // 规格2? \n" +
                    "long mCodecSpecific3;   // 规格3? \n" +
                    "long mCodecSpecific4;   // 规格4? \n" +
                    "\n" +
                    "\n" +
                    "   public String getCodecName() {\n" +
                    "        switch (mCodecType) {\n" +
                    "            case SOURCE_CODEC_TYPE_SBC:  return \"SBC\";\n" +
                    "            case SOURCE_CODEC_TYPE_AAC: return \"AAC\";\n" +
                    "            case SOURCE_CODEC_TYPE_APTX:   return \"aptX\";\n" +
                    "            case SOURCE_CODEC_TYPE_APTX_HD: return \"aptX HD\";\n" +
                    "            case SOURCE_CODEC_TYPE_LDAC: return \"LDAC\";\n" +
                    "            case SOURCE_CODEC_TYPE_APTX_ADAPTIVE:  return \"aptX Adaptive\";\n" +
                    "            case SOURCE_CODEC_TYPE_APTX_TWSP: return \"aptX TWS+\";\n" +
                    "            case SOURCE_CODEC_TYPE_INVALID:  return \"INVALID CODEC\";\n" +
                    "            default:\n" +
                    "                break;\n" +
                    "        }\n" +
                    "        return \"UNKNOWN CODEC(\" + mCodecType + \")\";\n" +
                    "    }\n" +
                    "\t\n" +
                    "*********************** BluetoothCodecStatus End   ***********************\n" +
                    "\n" +
                    "*********************** A2dpStateMachine Begin ***********************\n" +
                    "\t\n" +
                    "class A2dpStateMachine extends StateMachine {\n" +
                    "\n" +
                    "BluetoothDevice mDevice;\n" +
                    "boolean mIsPlaying = false;  //  是否 正在播放 \n" +
                    "BluetoothCodecStatus mCodecStatus;  // 蓝牙状态类? \n" +
                    "//  BluetoothCodecConfig mCodecConfig = BluetoothCodecStatus.getCodecConfig();\n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    "        ProfileService.println(sb, \"mDevice: \" + mDevice);\n" +
                    "        ProfileService.println(sb, \"  StateMachine: \" + this.toString());  // 打印状态机名称 \n" +
                    "        ProfileService.println(sb, \"  mIsPlaying: \" + mIsPlaying);\n" +
                    "        synchronized (this) {\n" +
                    "            if (mCodecStatus != null) {  // 打印  BluetoothCodecConfig \n" +
                    "                ProfileService.println(sb, \"  mCodecConfig: \" + mCodecStatus.getCodecConfig());\n" +
                    "            }\n" +
                    "        }\n" +
                    "        // Dump the state machine logs\n" +
                    "        StringWriter stringWriter = new StringWriter();\n" +
                    "        PrintWriter printWriter = new PrintWriter(stringWriter);\n" +
                    "        super.dump(new FileDescriptor(), printWriter, new String[]{});  // 打印状态机处理事件的集合\n" +
                    "        printWriter.flush();\n" +
                    "        stringWriter.flush();\n" +
                    "        ProfileService.println(sb, \"  StateMachineLog:\");\n" +
                    "        Scanner scanner = new Scanner(stringWriter.toString());\n" +
                    "        while (scanner.hasNextLine()) {\n" +
                    "            String line = scanner.nextLine();\n" +
                    "            ProfileService.println(sb, \"    \" + line);\n" +
                    "        }\n" +
                    "        scanner.close();\n" +
                    "    }\n" +
                    "\t\n" +
                    "\t\n" +
                    "\taddState(mDisconnected);\n" +
                    "\taddState(mConnecting);\n" +
                    "\taddState(mDisconnecting);\n" +
                    "\taddState(mConnected);\n" +
                    "\tsetInitialState(mDisconnected);\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "A2DP状态机模型\n" +
                    "            (Disconnected)\n" +
                    "               |       ^\n" +
                    "       CONNECT |       | DISCONNECTED\n" +
                    "               V       |\n" +
                    "     (Connecting)<--->(Disconnecting)\n" +
                    "               |       ^\n" +
                    "     CONNECTED |       | DISCONNECT\n" +
                    "               V       |\n" +
                    "              (Connected)\n" +
                    " \n" +
                    "  各个状态不能形成栈  自己就是栈顶 \n" +
                    "Disconnected 【初始状态】  Connecting  Disconnecting Connected\n" +
                    "*********************** A2dpStateMachine End ***********************\n" +
                    "\n" +
                    "\n" +
                    "public class A2dpService extends ProfileService {\n" +
                    "BluetoothDevice mActiveDevice; \n" +
                    "// 配对蓝牙设备--状态机的MAP集合\n" +
                    "ConcurrentMap<BluetoothDevice, A2dpStateMachine> mStateMachines =  new ConcurrentHashMap<>(); \n" +
                    "\n" +
                    "Avrcp mAvrcp;  // ? \n" +
                    "Avrcp_ext mAvrcp_ext; // ? \n" +
                    "\t\n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    "        super.dump(sb);\n" +
                    "        ProfileService.println(sb, \"mActiveDevice: \" + mActiveDevice);  // 当前配对蓝牙耳机\n" +
                    "        synchronized(mBtA2dpLock) {\n" +
                    "            for (A2dpStateMachine sm : mStateMachines.values()) {  // 打印所有配对设备的状态机信息\n" +
                    "                sm.dump(sb);\n" +
                    "            }\n" +
                    "        }\n" +
                    "        synchronized(mBtAvrcpLock) {\n" +
                    "            if (mAvrcp_ext != null) {\n" +
                    "                mAvrcp_ext.dump(sb);\n" +
                    "                return;\n" +
                    "            }\n" +
                    "            if (mAvrcp != null) {\n" +
                    "                mAvrcp.dump(sb);  // 打印  Avrcp com.android.bluetooth.avrcp.Avrcp;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "}";
            keyWordList.add(bluetooth_1_13);


            KeyWordItem bluetooth_1_14 = new KeyWordItem();
            bluetooth_1_14.keyword = "AVRCP:";
            bluetooth_1_14.explain="Profile: A2dpService  -》 Avrcp mAvrcp dump() \n" +
                    "A2dpService （service）A2DP全名是Advanced Audio Distribution Profile 蓝牙音频传输模型协定  \n" +
                    " A2DP是能够采用耳机内的芯片来堆栈数据，达到声音的高清晰度。然而并非支持A2DP的耳机就是蓝牙立体声耳机，立体声实现的基本要求是双声道，所以单声道的蓝牙耳机是不能实现立体声的\n" +
                    "A2DP定义了ACL(Asynchronous Connectionless 异步无连接)信道上传送单声道或立体声等高质量音频信息的协议和过程 \n" +
                    "A2DP取决于GAP(Generic Access Profile 通用接入协议)和GAVDP(Generic Audio /Video Distribution Profile 通用音视频分布协议).\n" +
                    "后者定义音频,视频流等建立所需要的过程.A2DP则定义建立音视频流所需要的参数和流程 \n" +
                    "典型应用:   立体声蓝牙耳机   无线蓝牙音箱" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 A2dpService  extends ProfileService  \n" +
                    "class A2dpService extends ProfileService ";
            bluetooth_1_14.classNameForShuxing = " 记录 A2dpService.Avrcp mAvrcp    Avrcp的日志记录  ";
            bluetooth_1_14.printcode="A2dpService -》 Avrcp mAvrcp -》  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_14.printLogUrl="";
            bluetooth_1_14.expain1="*******************  MediaAttributes Begin ******************* \n" +
                    "\n" +
                    "class MediaAttributes {  // 播放的属性? \n" +
                    " boolean mExists;\n" +
                    " String mTitle;\n" +
                    " String mArtistName;\n" +
                    " String mAlbumName;\n" +
                    " String mMediaNumber;\n" +
                    " String mMediaTotalNumber;\n" +
                    " String mGenre;\n" +
                    " long mPlayingTimeMs;\n" +
                    " String coverArt;\n" +
                    "\n" +
                    " static final int ATTR_TITLE = 1;\n" +
                    " static final int ATTR_ARTIST_NAME = 2;\n" +
                    " static final int ATTR_ALBUM_NAME = 3;\n" +
                    " static final int ATTR_MEDIA_NUMBER = 4;\n" +
                    " static final int ATTR_MEDIA_TOTAL_NUMBER = 5;\n" +
                    " static final int ATTR_GENRE = 6;\n" +
                    " static final int ATTR_PLAYING_TIME_MS = 7;\n" +
                    " static final int ATTR_COVER_ART = 8;\n" +
                    " \n" +
                    " public String toRedactedString() {\n" +
                    "\tif (!mExists) {\n" +
                    "\t\treturn \"[MediaAttributes: none]\";\n" +
                    "\t}\n" +
                    "\n" +
                    "\treturn \"[MediaAttributes: \" + Utils.ellipsize(mTitle) + \" - \" + Utils.ellipsize(mAlbumName) + \n" +
                    "\t        \" by \" + Utils.ellipsize(mArtistName) + \" (\" + mPlayingTimeMs\n" +
                    "\t\t\t+ \" \" + mMediaNumber + \"/\" + mMediaTotalNumber + \") \" + mGenre + \"]\";\n" +
                    "}\n" +
                    "\t\t\n" +
                    "}\n" +
                    "\t\t\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "*******************  MediaAttributes End ******************* \n" +
                    "\n" +
                    "\n" +
                    "*******************  Avrcp Begin ******************* \n" +
                    "public final class Avrcp {\n" +
                    "MediaAttributes mMediaAttributes;\n" +
                    "int mTransportControlFlags;  // 比特功能位?\n" +
                    "PlaybackState mCurrentPlayState;   //  播放状态\n" +
                    "\n" +
                    "// static final int NOTIFICATION_TYPE_INTERIM = 0;\n" +
                    "// static final int NOTIFICATION_TYPE_CHANGED = 1;  状态类型? \n" +
                    "int mPlayStatusChangedNT;   // 播放状态变化类型?\n" +
                    "\n" +
                    "// static final int NOTIFICATION_TYPE_INTERIM = 0;\n" +
                    "// static final int NOTIFICATION_TYPE_CHANGED = 1;\n" +
                    "int mTrackChangedNT; // 音轨改变变化类型?\n" +
                    "\n" +
                    "long mPlaybackIntervalMs;  // 回放间隔时间?\n" +
                    "\n" +
                    "// static final int NOTIFICATION_TYPE_INTERIM = 0;\n" +
                    "// static final int NOTIFICATION_TYPE_CHANGED = 1;\n" +
                    "int mPlayPosChangedNT;  // 播放位置改变变化类型?\n" +
                    "long mNextPosMs;  // 下一步位置 时间戳\n" +
                    "long mPrevPosMs;  // 之前的位置 时间戳\n" +
                    "\n" +
                    "    /* BTRC features */\n" +
                    "    public static final int BTRC_FEAT_METADATA = 0x01;\n" +
                    "    public static final int BTRC_FEAT_ABSOLUTE_VOLUME = 0x02;\n" +
                    "    public static final int BTRC_FEAT_BROWSE = 0x04;\n" +
                    "    public static final int BTRC_FEAT_AVRC_UI_UPDATE = 0x08;\n" +
                    "\t\n" +
                    "int mFeatures;   //  功能比特位 \n" +
                    "int mRemoteVolume;   // 远端音量大小\n" +
                    "int mLastRemoteVolume; // 远端最新音量大小 \n" +
                    "int mLastDirection;  //  方向?\n" +
                    "int mVolumeStep; // 步骤?  mVolumeStep = Math.max(AVRCP_BASE_VOLUME_STEP, AVRCP_MAX_VOL / mAudioStreamMax);\n" +
                    "int mAudioStreamMax; // 最大音量  mAudioStreamMax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);\n" +
                    "boolean mVolCmdSetInProgress;  // 是否正在设置音量中 \n" +
                    "int MAX_ERROR_RETRY_TIMES = 6;  // 最大重试次数\n" +
                    "int mAbsVolRetryTimes;  // 重试次数\n" +
                    "HashMap<Integer, Integer> mVolumeMapping;\n" +
                    "\n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    "        sb.append(\"AVRCP:\\n\");\n" +
                    "        ProfileService.println(sb, \"mMediaAttributes: \" + mMediaAttributes.toRedactedString());\n" +
                    "        ProfileService.println(sb, \"mTransportControlFlags: \" + mTransportControlFlags);  // 比特功能位?\n" +
                    "        ProfileService.println(sb, \"mCurrentPlayState: \" + mCurrentPlayState);  //  播放状态\n" +
                    "        ProfileService.println(sb, \"mPlayStatusChangedNT: \" + mPlayStatusChangedNT); // 播放状态变化类型?\n" +
                    "        ProfileService.println(sb, \"mTrackChangedNT: \" + mTrackChangedNT); // 音轨改变变化类型?\n" +
                    "        ProfileService.println(sb, \"mPlaybackIntervalMs: \" + mPlaybackIntervalMs);  // 回放间隔时间?\n" +
                    "        ProfileService.println(sb, \"mPlayPosChangedNT: \" + mPlayPosChangedNT);  // 播放位置改变变化类型?\n" +
                    "        ProfileService.println(sb, \"mNextPosMs: \" + mNextPosMs);  // 下一步位置 时间戳\n" +
                    "        ProfileService.println(sb, \"mPrevPosMs: \" + mPrevPosMs);  // 之前的位置 时间戳\n" +
                    "        ProfileService.println(sb, \"mFeatures: \" + mFeatures); //  功能比特位 \n" +
                    "        ProfileService.println(sb, \"mRemoteVolume: \" + mRemoteVolume);  // int mRemoteVolume;  远端音量大小\n" +
                    "        ProfileService.println(sb, \"mLastRemoteVolume: \" + mLastRemoteVolume);  // 远端最新音量大小 \n" +
                    "        ProfileService.println(sb, \"mLastDirection: \" + mLastDirection); //  方向?\n" +
                    "        ProfileService.println(sb, \"mVolumeStep: \" + mVolumeStep);\n" +
                    "        ProfileService.println(sb, \"mAudioStreamMax: \" + mAudioStreamMax);  // 最大音量\n" +
                    "        ProfileService.println(sb, \"mVolCmdSetInProgress: \" + mVolCmdSetInProgress);  // 是否正在设置音量中 \n" +
                    "        ProfileService.println(sb, \"mAbsVolRetryTimes: \" + mAbsVolRetryTimes);  // 重试次数 \n" +
                    "        ProfileService.println(sb, \"mVolumeMapping: \" + mVolumeMapping.toString());  // 音量映射 MAP? \n" +
                    "            ..........\n" +
                    "    }\n" +
                    "\n" +
                    "*******************  Avrcp Begin ******************* \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "public class A2dpService extends ProfileService {\n" +
                    "\n" +
                    "Avrcp mAvrcp;  // ? \n" +
                    "Avrcp_ext mAvrcp_ext; // ? \n" +
                    "\t\n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    "..............\n" +
                    "            if (mAvrcp_ext != null) {\n" +
                    "                mAvrcp_ext.dump(sb);\n" +
                    "                return;\n" +
                    "            }\n" +
                    "            if (mAvrcp != null) {\n" +
                    "                mAvrcp.dump(sb);  // AVRCP:  XXXX 打印  Avrcp com.android.bluetooth.avrcp.Avrcp;\n" +
                    "            }\n" +
                    "..............\n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n";
            keyWordList.add(bluetooth_1_14);





            KeyWordItem bluetooth_1_15 = new KeyWordItem();
            bluetooth_1_15.keyword = "mMediaController:";
            bluetooth_1_15.explain="Profile: A2dpService  -》 Avrcp mAvrcp dump() \n" +
                    "A2dpService （service）A2DP全名是Advanced Audio Distribution Profile 蓝牙音频传输模型协定  \n" +
                    " A2DP是能够采用耳机内的芯片来堆栈数据，达到声音的高清晰度。然而并非支持A2DP的耳机就是蓝牙立体声耳机，立体声实现的基本要求是双声道，所以单声道的蓝牙耳机是不能实现立体声的\n" +
                    "A2DP定义了ACL(Asynchronous Connectionless 异步无连接)信道上传送单声道或立体声等高质量音频信息的协议和过程 \n" +
                    "A2DP取决于GAP(Generic Access Profile 通用接入协议)和GAVDP(Generic Audio /Video Distribution Profile 通用音视频分布协议).\n" +
                    "后者定义音频,视频流等建立所需要的过程.A2DP则定义建立音视频流所需要的参数和流程 \n" +
                    "典型应用:   立体声蓝牙耳机   无线蓝牙音箱" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 A2dpService  extends ProfileService  \n" +
                    "class A2dpService extends ProfileService ";
            bluetooth_1_15.classNameForShuxing = " 记录 A2dpService.Avrcp mAvrcp -》 MediaController    Avrcp的日志记录  ";
            bluetooth_1_15.printcode="A2dpService -》 Avrcp mAvrcp -》MediaController -》  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_15.printLogUrl="";
            bluetooth_1_15.expain1="*******************  android.media.session.MediaController Begin ******************* \n" +
                    "/frameworks/base/media/java/android/media/session/MediaController.java\n" +
                    "public final class MediaController {\n" +
                    "\n" +
                    "final ISessionController mSessionBinder;\n" +
                    "\n" +
                    " int mVolumeType;  // 音频类型 PLAYBACK_TYPE_LOCAL   PLAYBACK_TYPE_REMOTE\n" +
                    " int mVolumeControl;  //  VolumeProvider#VOLUME_CONTROL_ABSOLUTE   VolumeProvider#VOLUME_CONTROL_RELATIVE  VolumeProvider#VOLUME_CONTROL_FIXED\n" +
                    " int mMaxVolume;    // 最低声音 \n" +
                    " int mCurrentVolume;  //  当前声音 \n" +
                    " AudioAttributes mAudioAttrs;   // 声音属性 \n" +
                    "\n" +
                    "      public String toString() {\n" +
                    "            return \"volumeType=\" + mVolumeType + \", volumeControl=\" + mVolumeControl\n" +
                    "                    + \", maxVolume=\" + mMaxVolume + \", currentVolume=\" + mCurrentVolume\n" +
                    "                    + \", audioAttrs=\" + mAudioAttrs;\n" +
                    "        }\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    " // Get the session owner's package name.  @return The package name of of the session owner.\n" +
                    "    public String getPackageName() {\n" +
                    "        if (mPackageName == null) {\n" +
                    "            try {\n" +
                    "                mPackageName = mSessionBinder.getPackageName();\n" +
                    "            } \n" +
                    "        }\n" +
                    "        return mPackageName;\n" +
                    "    }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "*******************  android.media.session.MediaController End   ******************* \n" +
                    "\n" +
                    "*******************  MediaController Begin ******************* \n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/avrcp/mockable/MediaController.java\n" +
                    "\n" +
                    "public class MediaController {\n" +
                    " public android.media.session.MediaController mDelegate;\n" +
                    "\n" +
                    "    public android.media.session.MediaController getWrappedInstance() {\n" +
                    "        return mDelegate;\n" +
                    "    }\n" +
                    "\t\n" +
                    "\tpublic String getPackageName() {\n" +
                    "        return mDelegate.getPackageName();\n" +
                    "    }\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "*******************  MediaController End ******************* \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "*******************  Avrcp Begin ******************* \n" +
                    "public final class Avrcp {\n" +
                    "\n" +
                    "private MediaController mMediaController; \n" +
                    "\n" +
                    "\n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    ".............\n" +
                    "\t\tsynchronized (this) {\n" +
                    "\tif (mMediaController != null) {  //  打印 MediaController\n" +
                    "ProfileService.println(sb,\"mMediaController: \" + mMediaController.getWrappedInstance() + \" pkg \"  + mMediaController.getPackageName());\n" +
                    "\t}\n" +
                    "   }\n" +
                    ".............\n" +
                    "}\n" +
                    "\n" +
                    "*******************  Avrcp End *******************\n";
            keyWordList.add(bluetooth_1_15);


            KeyWordItem bluetooth_1_16 = new KeyWordItem();
            bluetooth_1_16.keyword = "  Media Players:";
            bluetooth_1_16.explain="Profile: A2dpService  -》 Avrcp mAvrcp dump() -》 \n" +
                    "A2dpService （service）A2DP全名是Advanced Audio Distribution Profile 蓝牙音频传输模型协定  \n" +
                    " A2DP是能够采用耳机内的芯片来堆栈数据，达到声音的高清晰度。然而并非支持A2DP的耳机就是蓝牙立体声耳机，立体声实现的基本要求是双声道，所以单声道的蓝牙耳机是不能实现立体声的\n" +
                    "A2DP定义了ACL(Asynchronous Connectionless 异步无连接)信道上传送单声道或立体声等高质量音频信息的协议和过程 \n" +
                    "A2DP取决于GAP(Generic Access Profile 通用接入协议)和GAVDP(Generic Audio /Video Distribution Profile 通用音视频分布协议).\n" +
                    "后者定义音频,视频流等建立所需要的过程.A2DP则定义建立音视频流所需要的参数和流程 \n" +
                    "典型应用:   立体声蓝牙耳机   无线蓝牙音箱" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 A2dpService  extends ProfileService  \n" +
                    "class A2dpService extends ProfileService ";
            bluetooth_1_16.classNameForShuxing = " 记录 A2dpService.Avrcp mAvrcp -》 MediaController    Avrcp的日志记录  ";
            bluetooth_1_16.printcode="A2dpService -》 Avrcp mAvrcp -》MediaController -》  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_16.printLogUrl="";
            bluetooth_1_16.expain1 = "******************* MediaPlayerInfo Begin *******************\n" +
                    "\n" +
                    "/* stores information of Media Players in the system */\n" +
                    "class MediaPlayerInfo {\n" +
                    "\n" +
                    "    private byte mMajorType;\n" +
                    "    private int mSubType;\n" +
                    "    private byte mPlayStatus;\n" +
                    "    private short[] mFeatureBitMask;\n" +
                    "    String mPackageName;\n" +
                    "    String mDisplayableName;\n" +
                    "    MediaController mMediaController;\n" +
                    "\t\n" +
                    "    String getDisplayableName() {\n" +
                    "        return mDisplayableName;\n" +
                    "    }\n" +
                    "\t\n" +
                    "    byte getMajorType() {\n" +
                    "        return mMajorType;\n" +
                    "    }\n" +
                    "\t\n" +
                    "\tshort[] getFeatureBitMask() {\n" +
                    "        return mFeatureBitMask;\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "    #1: MediaPlayerInfo com.google.android.apps.youtube.music (as 'YouTube Music') Type = 1, SubType = 0, Status = 0 Feature Bits [40 41 42 44 45 47 48 58 59 62 65 67 68] Controller: null\n" +
                    "    #2: MediaPlayerInfo com.google.android.googlequicksearchbox (as 'Google') Type = 1, SubType = 0, Status = 0 Feature Bits [40 41 42 44 45 47 48 58 59 62 65 67 68] Controller: null\n" +
                    "    #3: MediaPlayerInfo com.google.android.youtube (as 'YouTube') Type = 1, SubType = 0, Status = 0 Feature Bits [40 41 42 44 45 47 48 58 59 62 65 67 68] Controller: null\n" +
                    "  \n" +
                    "  \n" +
                    "public String toString() {\n" +
                    "        StringBuilder sb = new StringBuilder();\n" +
                    "        sb.append(\"MediaPlayerInfo \");\n" +
                    "        sb.append(getPackageName());  // 包名 \n" +
                    "        sb.append(\" (as '\" + getDisplayableName() + \"')\");  // APP简称 \n" +
                    "        sb.append(\" Type = \" + getMajorType());  // Type ,  byte mMajorType 字节   type=0  type=1\n" +
                    "        sb.append(\", SubType = \" + getSubType());  //  int mSubType ,  子类型 \n" +
                    "        sb.append(\", Status = \" + mPlayStatus);  // byte mPlayStatus; 播放状态  字节表示 \n" +
                    "        sb.append(\" Feature Bits [\");\n" +
                    "        short[] bits = getFeatureBitMask();  // 打印 功能比特位  short[] mFeatureBitMask\n" +
                    "        for (int i = 0; i < bits.length; i++) {\n" +
                    "            if (i != 0) {\n" +
                    "                sb.append(\" \");\n" +
                    "            }\n" +
                    "            sb.append(bits[i]);\n" +
                    "        }\n" +
                    "        sb.append(\"] Controller: \");\n" +
                    "        sb.append(getMediaController());  // 打印 MediaController\n" +
                    "        return sb.toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "\t}\n" +
                    "\t\n" +
                    "******************* MediaPlayerInfo End *******************\n" +
                    "\n" +
                    "*******************  Avrcp Begin ******************* \n" +
                    "public final class Avrcp {\n" +
                    "\n" +
                    "MediaController mMediaController;\n" +
                    "\n" +
                    "AddressedMediaPlayer mAddressedMediaPlayer;     /* Addressed player handling */\n" +
                    "\t\n" +
                    " /* List of Media player instances, useful for retrieving MediaPlayerList or MediaPlayerInfo */ \n" +
                    " // 使用蓝牙音频的APP信息列表记录    Integer 是 index? \n" +
                    " SortedMap<Integer, MediaPlayerInfo> mMediaPlayerInfoList;\n" +
                    "\n" +
                    " int mCurrAddrPlayerID; // 标识当前播放来源的 标识 索引 \n" +
                    " \n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    ".............\n" +
                    "        ProfileService.println(sb, \"\");\n" +
                    "        ProfileService.println(sb, \"Media Players:\");   // 打印 Media Players\n" +
                    "        synchronized(this) {\n" +
                    "            synchronized (mMediaPlayerInfoList) {\n" +
                    "\t\t\t// 当前正在播放的来源索引  会多出一个  *# \n" +
                    "                for (Map.Entry<Integer, MediaPlayerInfo> entry : mMediaPlayerInfoList.entrySet()) {\n" +
                    "                  int key = entry.getKey();\n" +
                    "                  ProfileService.println(sb, ((mCurrAddrPlayerID == key) ? \" *#\" : \"  #\") + entry.getKey() + \": \"  + entry.getValue());\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "        ProfileService.println(sb, \"\");\n" +
                    "        mAddressedMediaPlayer.dump(sb, mMediaController);\n" +
                    ".............\n" +
                    "}\n" +
                    "LOG示例:\n" +
                    "    #1: MediaPlayerInfo com.google.android.apps.youtube.music (as 'YouTube Music') Type = 1, SubType = 0, Status = 0 Feature Bits [40 41 42 44 45 47 48 58 59 62 65 67 68] Controller: null\n" +
                    "    #2: MediaPlayerInfo com.google.android.googlequicksearchbox (as 'Google') Type = 1, SubType = 0, Status = 0 Feature Bits [40 41 42 44 45 47 48 58 59 62 65 67 68] Controller: null\n" +
                    "    #3: MediaPlayerInfo com.google.android.youtube (as 'YouTube') Type = 1, SubType = 0, Status = 0 Feature Bits [40 41 42 44 45 47 48 58 59 62 65 67 68] Controller: null\n" +
                    "*******************  Avrcp End *******************";
            keyWordList.add(bluetooth_1_16);







            KeyWordItem bluetooth_1_17 = new KeyWordItem();
            bluetooth_1_17.keyword = "  AddressedPlayer info:";
            bluetooth_1_17.explain="Profile: A2dpService  -》 Avrcp mAvrcp dump() -> \n" +
                    "A2dpService （service）A2DP全名是Advanced Audio Distribution Profile 蓝牙音频传输模型协定  \n" +
                    " A2DP是能够采用耳机内的芯片来堆栈数据，达到声音的高清晰度。然而并非支持A2DP的耳机就是蓝牙立体声耳机，立体声实现的基本要求是双声道，所以单声道的蓝牙耳机是不能实现立体声的\n" +
                    "A2DP定义了ACL(Asynchronous Connectionless 异步无连接)信道上传送单声道或立体声等高质量音频信息的协议和过程 \n" +
                    "A2DP取决于GAP(Generic Access Profile 通用接入协议)和GAVDP(Generic Audio /Video Distribution Profile 通用音视频分布协议).\n" +
                    "后者定义音频,视频流等建立所需要的过程.A2DP则定义建立音视频流所需要的参数和流程 \n" +
                    "典型应用:   立体声蓝牙耳机   无线蓝牙音箱" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 A2dpService  extends ProfileService  \n" +
                    "class A2dpService extends ProfileService ";
            bluetooth_1_17.classNameForShuxing = " 记录 A2dpService.Avrcp mAvrcp -》 MediaController    Avrcp的日志记录  ";
            bluetooth_1_17.printcode="A2dpService -》 Avrcp mAvrcp -》MediaController -》  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_17.printLogUrl="";
            bluetooth_1_17.expain1 = "******************* AddressedMediaPlayer Begin *******************\n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/avrcp/AddressedMediaPlayer.java\n" +
                    "\n" +
                    "public class AddressedMediaPlayer {\n" +
                    "\n" +
                    "long mLastTrackIdSent;   // 音频ID?\n" +
                    "\n" +
                    "List<MediaSession.QueueItem> mNowPlayingList;  // 播放列表 \n" +
                    "    public void dump(StringBuilder sb, @Nullable MediaController mediaController) {\n" +
                    "        ProfileService.println(sb, \"AddressedPlayer info:\");\n" +
                    "        ProfileService.println(sb, \"mLastTrackIdSent: \" + mLastTrackIdSent);\n" +
                    "        ProfileService.println(sb, \"mNowPlayingList: \" + mNowPlayingList.size() + \" elements\");\n" +
                    "        long currentQueueId = getActiveQueueItemId(mediaController);\n" +
                    "        for (MediaSession.QueueItem item : mNowPlayingList) {\n" +
                    "            long itemId = item.getQueueId();\n" +
                    "\t\t\t // 显示MediaSession.QueueItem\n" +
                    "            ProfileService.println(sb,(itemId == currentQueueId ? \"*\" : \" \") + displayMediaItem(item));\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "    String displayMediaItem(MediaSession.QueueItem item) {\n" +
                    "        StringBuilder sb = new StringBuilder();\n" +
                    "        sb.append(\"#\");\n" +
                    "        sb.append(item.getQueueId());  // 播放列表索引\n" +
                    "        sb.append(\": \");\n" +
                    "        sb.append(Utils.ellipsize(getAttrValue(null, AvrcpConstants.ATTRID_TITLE, item, null)));   // 播放标题\n" +
                    "        sb.append(\" - \");\n" +
                    "        sb.append(Utils.ellipsize(getAttrValue(null, AvrcpConstants.ATTRID_ALBUM, item, null)));  // 播放专辑\n" +
                    "        sb.append(\" by \");\n" +
                    "        sb.append(Utils.ellipsize(getAttrValue(null, AvrcpConstants.ATTRID_ARTIST, item, null))); // 演唱人员\n" +
                    "        sb.append(\" (\");\n" +
                    "        sb.append(getAttrValue(null, AvrcpConstants.ATTRID_PLAY_TIME, item, null));  //播放时间 \n" +
                    "        sb.append(\" \");\n" +
                    "        sb.append(getAttrValue(null, AvrcpConstants.ATTRID_TRACK_NUM, item, null));  //  声道\n" +
                    "        sb.append(\"/\");\n" +
                    "        sb.append(getAttrValue(null, AvrcpConstants.ATTRID_NUM_TRACKS, item, null)); //  该专辑歌曲数量\n" +
                    "        sb.append(\") \");\n" +
                    "        sb.append(getAttrValue(null, AvrcpConstants.ATTRID_GENRE, item, null));  // 声音类型 \n" +
                    "        return sb.toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "\n" +
                    "******************* AddressedMediaPlayer End *******************\n" +
                    "\n" +
                    "\n" +
                    "******************* MediaKeyLog Begin *******************\n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/avrcp/Avrcp.java -> MediaKeyLog.class\n" +
                    "private class MediaKeyLog {\n" +
                    " long mTimeSent;    // 时间戳  发送消息的时间戳\n" +
                    " long mTimeProcessed;  // 处理消息的时间戳\n" +
                    " String mPackage;   // 包名\n" +
                    " KeyEvent mEvent;  // 发送的事件   android.view.KeyEvent;   按键点击时间 \n" +
                    " \n" +
                    " public String toString() {\n" +
                    "\tStringBuilder sb = new StringBuilder();\n" +
                    "\tsb.append(android.text.format.DateFormat.format(\"MM-dd HH:mm:ss\", mTimeSent));\n" +
                    "\tsb.append(\" \" + mEvent.toString());  // 事件描述\n" +
                    "\tif (mPackage == null) {\n" +
                    "\t\tsb.append(\" (undispatched)\"); // 如果包名为空  那么显示  (undispatched)\n" +
                    "\t} else {\n" +
                    "\t\tsb.append(\" to \" + mPackage);  // 给 对应的包 发送 事件  在 事件发生XX 毫秒后 被执行  \n" +
                    "\t\tsb.append(\" in \" + (mTimeProcessed - mTimeSent) + \"ms\");\n" +
                    "\t}\n" +
                    "\treturn sb.toString();\n" +
                    "}\n" +
                    "}\n" +
                    "\t\t\n" +
                    "\n" +
                    "******************* MediaKeyLog End *******************\n" +
                    "\n" +
                    "*******************  Avrcp Begin ******************* \n" +
                    "public final class Avrcp {\n" +
                    " int mPassthroughDispatched; //  Number of keys dispatched  保存的Log计数\n" +
                    "int PASSTHROUGH_LOG_MAX_SIZE = DEBUG ? 50 : 10;   // 保存的Log大小\n" +
                    "EvictingQueue<MediaKeyLog>  mPassthroughLogs = new EvictingQueue<MediaKeyLog>(PASSTHROUGH_LOG_MAX_SIZE); // Log容器队列\n" +
                    "\n" +
                    " List<MediaKeyLog> mPassthroughPending; // Passthrough keys sent not dispatched yet  还没发送的 MediaKeyLog集合?\n" +
                    " \n" +
                    "  AddressedMediaPlayer mAddressedMediaPlayer;  /* Addressed player handling */\n" +
                    "\n" +
                    "  \n" +
                    " \n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    ".............\n" +
                    "        ProfileService.println(sb, \"\");\n" +
                    "        mAddressedMediaPlayer.dump(sb, mMediaController);\n" +
                    "\t\t\n" +
                    "        ProfileService.println(sb, \"\");\n" +
                    "        ProfileService.println(sb, mPassthroughDispatched + \" passthrough operations: \");\n" +
                    "        if (mPassthroughDispatched > mPassthroughLogs.size()) {\n" +
                    "            ProfileService.println(sb, \"  (last \" + mPassthroughLogs.size() + \")\");\n" +
                    "        }\n" +
                    "        synchronized (mPassthroughLogs) {\n" +
                    "            for (MediaKeyLog log : mPassthroughLogs) {\n" +
                    "                ProfileService.println(sb, \"  \" + log);  // 打印 MediaKeyLog\n" +
                    "            }\n" +
                    "        }\n" +
                    "        synchronized (mPassthroughPending) {\n" +
                    "            for (MediaKeyLog log : mPassthroughPending) {\n" +
                    "                ProfileService.println(sb, \"  \" + log);  // 打印 MediaKeyLog\n" +
                    "            }\n" +
                    "        }\n" +
                    ".............\n" +
                    "}\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "  AddressedPlayer info:\n" +
                    "  mLastTrackIdSent: -1\n" +
                    "  mNowPlayingList: 0 elements\n" +
                    "  \n" +
                    "  0 passthrough operations: \n" +
                    "*******************  Avrcp End *******************";
            keyWordList.add(bluetooth_1_17);



            KeyWordItem bluetooth_1_18 = new KeyWordItem();
            bluetooth_1_18.keyword = "  Runtime Blacklisted Devices";
            bluetooth_1_18.explain="Profile: A2dpService  -》 Avrcp mAvrcp dump() -> absolute_volume_blacklist.xml 对应的 SharedPreferences \n" +
                    "A2dpService （service）A2DP全名是Advanced Audio Distribution Profile 蓝牙音频传输模型协定  \n" +
                    " A2DP是能够采用耳机内的芯片来堆栈数据，达到声音的高清晰度。然而并非支持A2DP的耳机就是蓝牙立体声耳机，立体声实现的基本要求是双声道，所以单声道的蓝牙耳机是不能实现立体声的\n" +
                    "A2DP定义了ACL(Asynchronous Connectionless 异步无连接)信道上传送单声道或立体声等高质量音频信息的协议和过程 \n" +
                    "A2DP取决于GAP(Generic Access Profile 通用接入协议)和GAVDP(Generic Audio /Video Distribution Profile 通用音视频分布协议).\n" +
                    "后者定义音频,视频流等建立所需要的过程.A2DP则定义建立音视频流所需要的参数和流程 \n" +
                    "典型应用:   立体声蓝牙耳机   无线蓝牙音箱" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 A2dpService  extends ProfileService  \n" +
                    "class A2dpService extends ProfileService ";
            bluetooth_1_18.classNameForShuxing = " 记录 A2dpService.Avrcp mAvrcp -》 SharedPreferences 【 absolute_volume_blacklist.xml 】    蓝牙黑名单记录  ";
            bluetooth_1_18.printcode="A2dpService -》 Avrcp mAvrcp -》SharedPreferences 【 absolute_volume_blacklist.xml 】List -》  for-> item ";

            bluetooth_1_18.printLogUrl="";
            bluetooth_1_18.expain1 = "*******************  Avrcp Begin ******************* \n" +
                    "public final class Avrcp {\n" +
                    "\n" +
                    " String ABSOLUTE_VOLUME_BLACKLIST = \"absolute_volume_blacklist\";\n" +
                    "\n" +
                    "public void dump(StringBuilder sb) {\n" +
                    "   ........\n" +
                    "\t// Print the blacklisted devices (for absolute volume control)  打印蓝牙黑名单\n" +
                    "\t// 获取 absolute_volume_blacklist.xml 文件 \n" +
                    "SharedPreferences pref = mContext.getSharedPreferences(ABSOLUTE_VOLUME_BLACKLIST, Context.MODE_PRIVATE);\n" +
                    "Map<String, ?> allKeys = pref.getAll();\n" +
                    "ProfileService.println(sb, \"\");\n" +
                    "ProfileService.println(sb, \"Runtime Blacklisted Devices (absolute volume):\");\n" +
                    "if (allKeys.isEmpty()) {\n" +
                    "\tProfileService.println(sb, \"  None\");  // 黑名单为空   \n" +
                    "} else {\n" +
                    "\tfor (Map.Entry<String, ?> entry : allKeys.entrySet()) {\n" +
                    "\t\tString key = entry.getKey();\n" +
                    "\t\tObject value = entry.getValue();\n" +
                    "\t\tif (value instanceof String) {\n" +
                    "\t\t\tProfileService.println(sb, \"  \" + key + \" \" + value);   // 打印黑名单 \n" +
                    "\t\t} else {\n" +
                    "\t\t\tProfileService.println(sb, \"  \" + key + \" Reason: Unknown\");\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}\n" +
                    "........\n" +
                    "}\n" +
                    "\t\n" +
                    "LOG示例:\n" +
                    "  Runtime Blacklisted Devices (absolute volume):\n" +
                    "    None   // absolute_volume_blacklist.xml 对应的 SharedPreferences 数据为空 \n" +
                    "*******************  Avrcp End ******************* ";
            keyWordList.add(bluetooth_1_18);



            KeyWordItem bluetooth_1_19 = new KeyWordItem();
            bluetooth_1_19.keyword = "Profile: HidHostService";
            bluetooth_1_19.explain="Profile: HidHostService \n" +
                    " Profile: HidHostService  The Human Interface Device (HID)   蓝牙鼠标  蓝牙键盘 \n" +
                    " The Human Interface Device (HID)定义了蓝牙在人机接口设备中的协议、特征和使用规程。\n" +
                    "典型的应用包括蓝牙鼠标、蓝牙键盘、蓝牙游戏手柄等";
            bluetooth_1_19.printcode="HidHostService   void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_19.printLogUrl="";
            bluetooth_1_19.expain1="******************* HidHostService Begin *******************\n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/hid/HidHostService.java\n" +
                    "public class HidHostService extends ProfileService {\n" +
                    "\n" +
                    " BluetoothDevice mTargetDevice = null;   // 蓝牙键盘  蓝牙自拍杆  蓝牙手柄 蓝牙鼠标\n" +
                    " \n" +
                    " Map<BluetoothDevice, Integer> mInputDevices;  // 接入的所有 蓝牙键盘  蓝牙自拍杆  蓝牙手柄 蓝牙鼠标 集合 \n" +
                    " \n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    "        super.dump(sb);\n" +
                    "        println(sb, \"mTargetDevice: \" + mTargetDevice);\n" +
                    "        println(sb, \"mInputDevices:\");\n" +
                    "        for (BluetoothDevice device : mInputDevices.keySet()) {\n" +
                    "\t\t// 历史连接过的  蓝牙键盘  蓝牙自拍杆  蓝牙手柄 蓝牙鼠标 集合 \n" +
                    "            println(sb, \"  \" + device + \" : \" + mInputDevices.get(device));  \n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "******************* HidHostService End *******************";
            keyWordList.add(bluetooth_1_19);


            KeyWordItem bluetooth_1_20 = new KeyWordItem();
            bluetooth_1_20.keyword = "Profile: AvrcpControllerService";
            bluetooth_1_20.explain="Profile: AvrcpControllerService \n" +
                    "  音视频远程控制协议 Audio/Video Remote Control Profile(AVRCP)定义了蓝牙设备和audio/video控制功能通信的特点和过程 \n" +
                    " AVRCP定义了蓝牙设备之间的音视频传输的特点和流程，来确保不同蓝牙设备之间音视频传输控制的兼容。\n" +
                    "一般包括暂停，停止，播放，音量控制等远程控制操作。例如，使用蓝牙耳机可以暂停，切换下一曲等操作来控制音乐播放器";
            bluetooth_1_20.printcode="AvrcpControllerService   void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_20.printLogUrl="";
            bluetooth_1_20.expain1="*******************  BrowseTree Begin ******************* \n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/avrcpcontroller/BrowseTree.java\n" +
                    "\n" +
                    "// Browsing hierarchy.\n" +
                    "// Root:\n" +
                    "//      Player1:\n" +
                    "//        Now_Playing:\n" +
                    "//          MediaItem1\n" +
                    "//          MediaItem2\n" +
                    "//        Folder1\n" +
                    "//        Folder2\n" +
                    "//        ....\n" +
                    "//      Player2\n" +
                    "//      ....\n" +
                    "public class BrowseTree {  // 播放历史?\n" +
                    "\n" +
                    " private final HashMap<String, BrowseNode> mBrowseMap = new HashMap<String, BrowseNode>();  // BrowseNode的map集合\n" +
                    "   BrowseNode mCurrentBrowseNode;\n" +
                    "   BrowseNode mCurrentBrowsedPlayer;\n" +
                    "   BrowseNode mCurrentAddressedPlayer;\n" +
                    "   int mDepth = 0;\n" +
                    "   BrowseNode mRootNode;\n" +
                    "   BrowseNode mNavigateUpNode;\n" +
                    "   BrowseNode mNowPlayingNode;\n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        String serialized = \"Size: \" + mBrowseMap.size();  // 打印 MAP<BrowseNode> 的 大小 \n" +
                    "        if (VDBG) {\n" +
                    "            serialized += mRootNode.toString();   // 打印 每个 BrowseNode的 toString() 方法\n" +
                    "        }\n" +
                    "        return serialized;\n" +
                    "    }\n" +
                    " }\n" +
                    "\t\n" +
                    "   \n" +
                    "class   BrowseNode {\n" +
                    " MediaItem mItem;   // MediaItem to store the media related details.\n" +
                    "\n" +
                    " BrowseNode mParent;\n" +
                    " List<BrowseNode> mChildren = new ArrayList<BrowseNode>();          // List of children.\n" +
                    " int mExpectedChildrenCount;\n" +
                    " \n" +
                    "byte mBrowseScope = AvrcpControllerService.BROWSE_SCOPE_VFS; // 浏览方式? \n" +
                    "// public static final byte BROWSE_SCOPE_PLAYER_LIST = 0x00;\n" +
                    "// public static final byte BROWSE_SCOPE_VFS = 0x01;\n" +
                    "// public static final byte BROWSE_SCOPE_SEARCH = 0x02;\n" +
                    "// public static final byte BROWSE_SCOPE_NOW_PLAYING = 0x03;\n" +
                    "\n" +
                    "\n" +
                    "synchronized String getID() { // Fetch the Unique UID for this item, this is unique across all elements in the tree.\n" +
                    "\treturn mItem.getDescription().getMediaId();\n" +
                    "}\n" +
                    "\n" +
                    "\t\t\n" +
                    "public synchronized String toString() {\n" +
                    "\tif (VDBG) {    // 打印 结点信息  播放title   播放类型  预期子节点个数\n" +
                    "\t\tString serialized = \"[ Name: \" + mItem.getDescription().getTitle()    + \" Scope:\" + mBrowseScope + \" expected Children: \"   + mExpectedChildrenCount + \"] \";\n" +
                    "\t\tfor (BrowseNode node : mChildren) {\n" +
                    "\t\t\tserialized += node.toString();\n" +
                    "\t\t}\n" +
                    "\t\treturn serialized;\n" +
                    "\t} else {\n" +
                    "\t\treturn \"ID: \" + getID();   // 打印音频ID \n" +
                    "\t}\n" +
                    "}\n" +
                    "}\n" +
                    "}\n" +
                    "*******************  BrowseTree End ******************* \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "*******************  AvrcpControllerService Begin ******************* \n" +
                    "/packages/apps/Bluetooth/src/com/android/bluetooth/avrcpcontroller/AvrcpControllerService.java\n" +
                    "public class AvrcpControllerService extends ProfileService {\n" +
                    "\n" +
                    " Map<BluetoothDevice, AvrcpControllerStateMachine> mDeviceStateMap = new ConcurrentHashMap<>(1);  // 状态机Map\n" +
                    "\t\n" +
                    "static BrowseTree sBrowseTree; //  浏览树?\n" +
                    "\t\n" +
                    "    public void dump(StringBuilder sb) {\n" +
                    "        super.dump(sb);\n" +
                    "        ProfileService.println(sb, \"Devices Tracked = \" + mDeviceStateMap.size());\n" +
                    "\n" +
                    "        for (AvrcpControllerStateMachine stateMachine : mDeviceStateMap.values()) {\n" +
                    "            ProfileService.println(sb,\"==== StateMachine for \" + stateMachine.getDevice() + \" ====\"); // 配对蓝牙device\n" +
                    "            stateMachine.dump(sb);  // 打印 状态机处理事件的Log集合\n" +
                    "        }\n" +
                    "        sb.append(\"\\n  sBrowseTree: \" + sBrowseTree.toString());\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "*******************  AvrcpControllerService End ******************* \n";
            keyWordList.add(bluetooth_1_20);





            KeyWordItem bluetooth_2_1 = new KeyWordItem();
            bluetooth_2_1.keyword = "Connection Events:";
            bluetooth_2_1.explain="libbluetooth_qti.so  native打印的Log 貌似无法看到源码 ";
            bluetooth_2_1.classNameForShuxing = " adb pull /system/lib64/libbluetooth_qti.so   .   ";
            bluetooth_2_1.printcode=" com_android_bluetooth_btservice_AdapterService.cpp => sBluetoothInterface->dump(fd, args); ";

            bluetooth_2_1.printLogUrl="";
            bluetooth_2_1.expain1="*******************  AvrcpControllerService Begin ******************* \n" +
                    "BluetoothManagerService.java ==> void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    "\tIBinder mBluetoothBinder;\n" +
                    "\tmBluetoothBinder.dump(fd, args);\n" +
                    " }\n" +
                    " \n" +
                    " ||\n" +
                    " ||\n" +
                    " \\/ \n" +
                    "\n" +
                    "public class AdapterService extends Service {\n" +
                    "\n" +
                    "native void dumpNative(FileDescriptor fd, String[] arguments);  // 【 进入 native C层去 】\n" +
                    "\n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {\n" +
                    ".......\n" +
                    "  dumpNative(fd, args);\n" +
                    ".......\n" +
                    "    }\n" +
                    " }\n" +
                    "\t\n" +
                    "*************** JNI  com_android_bluetooth_btservice_AdapterService.cpp Begin ***************\n" +
                    "/packages/apps/Bluetooth/jni/com_android_bluetooth_btservice_AdapterService.cpp\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "// 初始化 static const bt_interface_t *sBluetoothInterface 方法  初始化方法\n" +
                    "int hal_util_load_bt_library(const bt_interface_t** interface) {  \n" +
                    "  const char* sym = BLUETOOTH_INTERFACE_STRING;   //  #define BLUETOOTH_INTERFACE_STRING \"bluetoothInterface\"  \n" +
                    "  char path[PROPERTY_VALUE_MAX] = \"\";\n" +
                    "  //  #define PROPERTY_BT_LIBRARY_NAME \"ro.bluetooth.library_name\"    \n" +
                    "  //  adb shell getprop ro.bluetooth.library_name   【 libbluetooth_qti.so 】\n" +
                    "  //  #define DEFAULT_BT_LIBRARY_NAME \"libbluetooth.so\" \n" +
                    "  \n" +
                    " property_get(PROPERTY_BT_LIBRARY_NAME, path, DEFAULT_BT_LIBRARY_NAME);\n" +
                    " void* handle = dlopen(path, RTLD_NOW);  // 打开 libbluetooth.so 或者 libbluetooth_qti.so \n" +
                    " \n" +
                    "  // Get the address of the bt_interface_t.\n" +
                    "  itf = (bt_interface_t*)dlsym(handle, sym);  // 使用 handle 这个文件 去控制 接口 bluetoothInterface ? \n" +
                    "  if (!itf) {  // 判空  过滤失败 \n" +
                    "    LOG(ERROR) << __func__ << \": failed to load symbol from Bluetooth library \"\n" +
                    "               << sym;\n" +
                    "    goto error;\n" +
                    "  }\n" +
                    "*interface = itf;   // 初始化完成  接口开始有值 \n" +
                    "return 0;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "static JNINativeMethod sMethods[] = {\n" +
                    "    {\"dumpNative\", \"(Ljava/io/FileDescriptor;[Ljava/lang/String;)V\",(void*)dumpNative},\n" +
                    "    {\"dumpMetricsNative\", \"()[B\", (void*)dumpMetricsNative}\n" +
                    "\t}\n" +
                    "\t\n" +
                    "\t\n" +
                    "static void dumpNative(JNIEnv* env, jobject obj, jobject fdObj,jobjectArray argArray) {\n" +
                    "  ALOGV(\"%s\", __func__);\n" +
                    "  if (!sBluetoothInterface) return;\n" +
                    "\n" +
                    "  int fd = jniGetFDFromFileDescriptor(env, fdObj);\n" +
                    "  if (fd < 0) return;\n" +
                    "\n" +
                    "  int numArgs = env->GetArrayLength(argArray);\n" +
                    "\n" +
                    "  jstring* argObjs = new jstring[numArgs];\n" +
                    "  const char** args = nullptr;\n" +
                    "  if (numArgs > 0) args = new const char*[numArgs];\n" +
                    "\n" +
                    "  if (!args || !argObjs) {\n" +
                    "    ALOGE(\"%s: not have enough memeory\", __func__);\n" +
                    "    return;\n" +
                    "  }\n" +
                    "\n" +
                    "  for (int i = 0; i < numArgs; i++) {\n" +
                    "    argObjs[i] = (jstring)env->GetObjectArrayElement(argArray, i);\n" +
                    "    args[i] = env->GetStringUTFChars(argObjs[i], NULL);\n" +
                    "  }\n" +
                    "\n" +
                    "// hal_util_load_bt_library((bt_interface_t const**)&sBluetoothInterface)  ==> 初始化  sBluetoothInterface\n" +
                    "// ▲ native打印Log  调用    static const bt_interface_t *sBluetoothInterface = NULL;\n" +
                    "// 打开 /system/lib64/libbluetooth.so 或者 /system/lib64/libbluetooth_qti.so  实现的  dump  方法 \n" +
                    "  sBluetoothInterface->dump(fd, args);  // ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ \n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "*************** JNI  com_android_bluetooth_btservice_AdapterService.cpp End ***************\n" +
                    "https://blog.csdn.net/OswinWang/article/details/61620837\n" +
                    "1.主设备（master,即发起连接的设备）会跳频的方式寻呼（page）\n" +
                    "2.从设备（slave接收连接的设备）固定间隔地去扫描（scan）外部寻呼，即page scan\n" +
                    "3.当scan 到外部page时便会响应response该page,这样两个设备之间便会建立link的连接，即ACL链路的连接。 (物理层)\n" +
                    "  蓝牙异步物理链路:ACL(AsynchronousConnectionless)\n" +
                    "  蓝牙链路分两种同步链路(SCO)和异步链路（ACL）  异步无链接（ACL,Asynchronous Connection Less）\n" +
                    "  蓝牙同步物理链路 SCO(Synchronous Connection Oriented)主要用来传输对时间要求很高的数据通信 主要用于同步话音传送 同步定向链接（SCO，Synchronous Connection Oritened）\n" +
                    "  蓝牙异步物理链路（ACL）主要用于分组数据传送 它既支持对称连接，也支持不对称连接（既可以一对一，也可以一对多）\n" +
                    "4.当ACL 链路连接建立后，主设备会发起channel的连接请求，即L2CAP的连接   (数据链路层)\n" +
                    "    L2CAP：Logical Link Control and Adaptation Layer Protocol，逻辑链路控制和适配层协议。\n" +
                    "    CID：Channel Identifier，信道标识。\n" +
                    "\tL2CAP通过协议多路复用、分段重组操作和组概念,向高层提供面向连接的和无连接的数据服务\n" +
                    "\tL2CAP还屏蔽了低层传输协议中的很多特性，使得高层协议应用开发人员可以不必了解基层协议而进行开发\n" +
                    "\t\n" +
                    "5.建立L2CAP的连接之后，主设备采用SDP去查询从设备的免提服务，从中得到rfcomm的通道号 \n" +
                    "  解决的问题:两个陌生的设备（之前未有过交互）如何去发现对方支持什么服务呢？\n" +
                    "  SDP全称是Service Discovery Protocol，它是一种服务发现的协议   是一种 C/S的架构\n" +
                    "6.最后 主设备会发起rfcomm的连接请求建立rfcomm的连接。然后就建立了应用的连接。\n" +
                    "\n" +
                    "通过上面的分析，我们知道了产生Page timeout的原因是由于在主设备寻呼（page）从设备时，ACL链路没有建立成功。\n" +
                    "我们遇到这类问题时需要从以下两个方面去分析\n" +
                    "1、从设备是否处于可连接的模式下。只有处于可连接的模式下，从设备才会进行Page scan的操作。\n" +
                    "  如果处于可连接模式下，还需要看下page scan interval和page scan window的参数设置是否合理。\n" +
                    "  \n" +
                    "2、从设备是否收到了acl 链路的连接请求。可以通过snoop log或者air log去确认\n" +
                    "  \n" +
                    "蓝牙连接事件示例:\n" +
                    "【时间戳-最新最先】 【连接设备类型-事件类型 【蓝牙Mac地址】    【状态】 \n" +
                    "  12-26 16:08:21.577 CLASSICAL-DISCONNECTED  82:a1:08:0c:7f:1e  status = Local Host Terminated ---- 对端设备关闭\n" +
                    "  12-26 16:08:17.885 SCO-DISCONNECTED        82:a1:08:0c:7f:1e  status = Local Host Terminated\n" +
                    "  12-26 16:08:14.638 eSCO-CONNECTED          82:a1:08:0c:7f:1e  status = Success        ---- 连接成功\n" +
                    "  12-26 13:24:02.196 CLASSICAL-CONNECTED     82:a1:08:0c:7f:1e  status = Success\n" +
                    "  12-26 13:23:58.615 CLASSICAL-CONNECTED     82:a1:08:0c:7f:1e  status = Page Timeout  ---- 寻呼（page）超时\n" +
                    "  12-26 13:23:53.455 CLASSICAL-CONNECTED     82:a1:08:0c:7f:1e  status = Page Timeout\n" +
                    "  12-26 13:23:48.309 CLASSICAL-CONNECTED     82:a1:08:0c:7f:1e  status = Page Timeout";
            keyWordList.add(bluetooth_2_1);


            KeyWordItem bluetooth_2_2 = new KeyWordItem();
            bluetooth_2_2.keyword = "Bond Events:";
            bluetooth_2_2.explain="libbluetooth_qti.so  native打印的Log 貌似无法看到源码 ";
            bluetooth_2_2.classNameForShuxing = " adb pull /system/lib64/libbluetooth_qti.so   .    \n" +
                    "蓝牙绑定事件列表";
            bluetooth_2_2.printcode=" com_android_bluetooth_btservice_AdapterService.cpp => sBluetoothInterface->dump(fd, args); ";

            bluetooth_2_2.printLogUrl="";
            bluetooth_2_2.expain1="LOG示例:\n" +
                    "Bond Events: \n" +
                    "  Total Number of events: 0";
            keyWordList.add(bluetooth_2_2);



            KeyWordItem bluetooth_2_3 = new KeyWordItem();
            bluetooth_2_3.keyword = "A2DP State:";
            bluetooth_2_3.explain="libbluetooth_qti.so  native打印的Log 貌似无法看到源码 ";
            bluetooth_2_3.classNameForShuxing = " adb pull /system/lib64/libbluetooth_qti.so   .    \n" +
                    " A2DP 状态  Advanced Audio Distribution Profile 蓝牙音频传输模型协定 ";
            bluetooth_2_3.printcode=" com_android_bluetooth_btservice_AdapterService.cpp => sBluetoothInterface->dump(fd, args); ";

            bluetooth_2_3.printLogUrl="";
            bluetooth_2_3.expain1="LOG示例:\n" +
                    "A2DP State:\n" +
                    "  TxQueue:\n" +
                    "  Counts (enqueue/dequeue/readbuf)                        : 0 / 0 / 0\n" +
                    "  Last update time ago in ms (enqueue/dequeue/readbuf)    : 0 / 0 / 0\n" +
                    "  Frames per packet (total/max/ave)                       : 0 / 0 / 0\n" +
                    "  Counts (flushed/dropped/dropouts)                       : 0 / 0 / 0\n" +
                    "  Counts (max dropped)                                    : 0\n" +
                    "  Last update time ago in ms (flushed/dropped)            : 0 / 0\n" +
                    "  Counts (underflow)                                      : 0\n" +
                    "  Bytes (underflow)                                       : 0\n" +
                    "  Last update time ago in ms (underflow)                  : 0\n" +
                    "  Enqueue deviation counts (overdue/premature)            : 0 / 0\n" +
                    "  Enqueue overdue scheduling time in ms (total/max/ave)   : 0 / 0 / 0\n" +
                    "  Enqueue premature scheduling time in ms (total/max/ave) : 0 / 0 / 0\n" +
                    "  Dequeue deviation counts (overdue/premature)            : 0 / 0\n" +
                    "  Dequeue overdue scheduling time in ms (total/max/ave)   : 0 / 0 / 0\n" +
                    "  Dequeue premature scheduling time in ms (total/max/ave) : 0 / 0 / 0";
            keyWordList.add(bluetooth_2_3);




            KeyWordItem bluetooth_2_4 = new KeyWordItem();
            bluetooth_2_4.keyword = "Bluetooth Config:";
            bluetooth_2_4.explain="libbluetooth_qti.so  native打印的Log 貌似无法看到源码 ";
            bluetooth_2_4.classNameForShuxing = " adb pull /system/lib64/libbluetooth_qti.so   .    \n" +
                    "蓝牙配置Configuration 信息  ";
            bluetooth_2_4.printcode=" com_android_bluetooth_btservice_AdapterService.cpp => sBluetoothInterface->dump(fd, args); ";

            bluetooth_2_4.printLogUrl="";
            bluetooth_2_4.expain1="LOG示例:\n" +
                    "Bluetooth Config:\n" +
                    "  Config Source: Original file\n" +
                    "  Devices loaded: 0\n" +
                    "  File created/tagged: 2019-10-12 10:34:16\n" +
                    "  File source: Empty";
            keyWordList.add(bluetooth_2_4);




            KeyWordItem bluetooth_2_5 = new KeyWordItem();
            bluetooth_2_5.keyword = "Bluetooth Alarms Statistics:";
            bluetooth_2_5.explain="libbluetooth_qti.so  native打印的Log 貌似无法看到源码 ";
            bluetooth_2_5.classNameForShuxing = " adb pull /system/lib64/libbluetooth_qti.so   .    \n" +
                    "蓝牙配警报? Alarms 信息  ";
            bluetooth_2_5.printcode=" com_android_bluetooth_btservice_AdapterService.cpp => sBluetoothInterface->dump(fd, args); ";
            bluetooth_2_5.printLogUrl="";
            bluetooth_2_5.expain1="LOG示例:\n" +
                    "Bluetooth Alarms Statistics:\n" +
                    "  Total Alarms: 1\n" +
                    "\n" +
                    "  Alarm : btm_ble_addr.refresh_raddr_timer (SINGLE)\n" +
                    "    Action counts (sched/resched/exec/cancel)      : 1 / 0 / 0 / 0\n" +
                    "    Deviation counts (overdue/premature)           : 0 / 0\n" +
                    "    Time in ms (since creation/interval/remaining) : 17386 / 900000 / 882614\n" +
                    "    Callback execution time in ms (total/max/avg)  : 0 / 0 / 0\n" +
                    "    Overdue scheduling time in ms (total/max/avg)  : 0 / 0 / 0\n" +
                    "    Premature scheduling time in ms (total/max/avg): 0 / 0 / 0";
            keyWordList.add(bluetooth_2_5);



            KeyWordItem bluetooth_2_6 = new KeyWordItem();
            bluetooth_2_6.keyword = "connection_manager state:";
            bluetooth_2_6.explain="libbluetooth_qti.so  native打印的Log 貌似无法看到源码 ";
            bluetooth_2_6.classNameForShuxing = " adb pull /system/lib64/libbluetooth_qti.so   .    \n" +
                    "连接管理类 状态?   ";
            bluetooth_2_6.printcode=" com_android_bluetooth_btservice_AdapterService.cpp => sBluetoothInterface->dump(fd, args); ";
            bluetooth_2_6.printLogUrl="";
            bluetooth_2_6.expain1="LOG示例:\n" +
                    "connection_manager state:\n" +
                    "\n" +
                    "\tno Low Energy connection attempts";
            keyWordList.add(bluetooth_2_6);





            KeyWordItem bluetooth_2_7 = new KeyWordItem();
            bluetooth_2_7.keyword = "BT Quality Report Events:";
            bluetooth_2_7.explain="libbluetooth_qti.so  native打印的Log 貌似无法看到源码 ";
            bluetooth_2_7.classNameForShuxing = " adb pull /system/lib64/libbluetooth_qti.so   .    \n" +
                    " 蓝牙质量报告事件列表: ";
            bluetooth_2_7.printcode=" com_android_bluetooth_btservice_AdapterService.cpp => sBluetoothInterface->dump(fd, args); ";
            bluetooth_2_7.printLogUrl="";
            bluetooth_2_7.expain1="LOG示例:\n" +
                    "BT Quality Report Events: \n" +
                    "Event queue is empty.";
            keyWordList.add(bluetooth_2_7);


/*

            KeyWordItem bluetooth_2_2 = new KeyWordItem();
            bluetooth_2_2.keyword = "Bond Events:";
            bluetooth_2_2.explain="libbluetooth_qti.so  native打印的Log 貌似无法看到源码 ";
            bluetooth_2_2.classNameForShuxing = " adb pull /system/lib64/libbluetooth_qti.so   .    \n" +
                    "蓝牙绑定事件列表";
            bluetooth_2_2.printcode=" com_android_bluetooth_btservice_AdapterService.cpp => sBluetoothInterface->dump(fd, args); ";

            bluetooth_2_2.printLogUrl="";
            bluetooth_2_2.expain1="LOG示例:\n" +
                    "Bond Events: \n" +
                    "  Total Number of events: 0";
            keyWordList.add(bluetooth_2_2);

*/


            //     Profile: HidHostService  The Human Interface Device (HID)   蓝牙鼠标  蓝牙键盘
 //     The Human Interface Device (HID)定义了蓝牙在人机接口设备中的协议、特征和使用规程。典型的应用包括蓝牙鼠标、蓝牙键盘、蓝牙游戏手柄等

//  Profile: PanService  PAN就是一种蓝牙个人局域网



//  MAP协议全称是 MessageAccessProfile，允许设备间的信息交换，主要用于汽车免提装置。比如，福特的部分汽车就应用了MAP协议，宝马的 iDrive系统也要用到MAP协议

// 音视频远程控制协议 Audio/Video Remote Control Profile(AVRCP)定义了蓝牙设备和audio/video控制功能通信的特点和过程
// AVRCP定义了蓝牙设备之间的音视频传输的特点和流程，来确保不同蓝牙设备之间音视频传输控制的兼容。
// 一般包括暂停，停止，播放，音量控制等远程控制操作。例如，使用蓝牙耳机可以暂停，切换下一曲等操作来控制音乐播放器


 // SIM 卡接入配置文件 (SAP)  Sim Access profile
 // SAP 允许带有内置 GSM 收发器的车载电话之类的设备连接到蓝牙电话中的 SIM 卡。因此车载电话本身并不需要单独的 SIM 卡。

    // OPP  对象传输协议  Object Push Profile
    // 蓝牙通信程序部分需采用用于设备之间传输数据对象OPP Profile: Object Push Profile
   // 由于OPP profile又细分为OPPC (client)端和 OPPS(server)端 profilr
    //这两个profile区别在于只有client端可以发起数据传输的过程，但是附件设备与手机通信的情景中，既有手机
    //发起数据传输请求也有设备侧发起传输请求的需要，所以要在设备中实现OPPC和OPPS两个profile。


       //     PBAP
      //  电话号码簿访问协议（PhonebookAccess Profile）


            /*
            KeyWordItem bluetooth_1_2 = new KeyWordItem();
            bluetooth_1_2.keyword = "GATT Handle Map";
            bluetooth_1_2.explain="Profile: GattService \n" +
                    "GATT的服务（service）是为了实现设备的某些功能或特征，是一系列数据和行为的集合 \n" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 GattService  extends ProfileService  \n" +
                    "class GattService extends ProfileService 中的 HandleMap mHandleMap -》 List<Entry> mEntries 和 Map<Integer【requestId】, Integer【handleid】> mRequestMap ";
            bluetooth_1_2.classNameForShuxing = " 记录请求 Service Chrastices Describe 的日志记录  ";
            bluetooth_1_2.printcode="GattService-->HandleMap   void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_2.printLogUrl="";
            bluetooth_1_2.expain1="";
            keyWordList.add(bluetooth_1_2);







                        KeyWordItem bluetooth_1_16 = new KeyWordItem();
            bluetooth_1_16.keyword = "mMediaController:";
            bluetooth_1_16.explain="Profile: A2dpService  -》 Avrcp mAvrcp dump() \n" +
                    "A2dpService （service）A2DP全名是Advanced Audio Distribution Profile 蓝牙音频传输模型协定  \n" +
                    " A2DP是能够采用耳机内的芯片来堆栈数据，达到声音的高清晰度。然而并非支持A2DP的耳机就是蓝牙立体声耳机，立体声实现的基本要求是双声道，所以单声道的蓝牙耳机是不能实现立体声的\n" +
                    "A2DP定义了ACL(Asynchronous Connectionless 异步无连接)信道上传送单声道或立体声等高质量音频信息的协议和过程 \n" +
                    "A2DP取决于GAP(Generic Access Profile 通用接入协议)和GAVDP(Generic Audio /Video Distribution Profile 通用音视频分布协议).\n" +
                    "后者定义音频,视频流等建立所需要的过程.A2DP则定义建立音视频流所需要的参数和流程 \n" +
                    "典型应用:   立体声蓝牙耳机   无线蓝牙音箱" +
                    "【ArrayList<ProfileService> mRegisteredProfiles 包含的子项 】 A2dpService  extends ProfileService  \n" +
                    "class A2dpService extends ProfileService ";
            bluetooth_1_16.classNameForShuxing = " 记录 A2dpService.Avrcp mAvrcp -》 MediaController    Avrcp的日志记录  ";
            bluetooth_1_16.printcode="A2dpService -》 Avrcp mAvrcp -》MediaController -》  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";

            bluetooth_1_16.printLogUrl="";
            bluetooth_1_16.expain1 = "";
            keyWordList.add(bluetooth_1_16);

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