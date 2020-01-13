import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Service global info
class F8_Dump_Camera {

//   getprop.txt  中读取 [ro.bui  ld.version.release]: [10]
//getprop.txt   [ro.hardware.soc.manufacturer]: [qcom]   制造商
//  如果不包含  那么 检测 是否包含MTK
    // .mtk  或者  .mtk
    // .qcom  或者  qcom.




    static String F8DirPathStr = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F8";
    static File F8DirFile = new File(F8DirPathStr);
    static  String getpropFileName = "getprop.txt";
    static  String cameraFileName = "media.camera.txt";
    static Map<String,File> allDumpMap = new HashMap<String,File>();

    // 获取在目录 F8DirFile 下 所有文件的 绝对路径
    static   ArrayList<File> AllDumpFileList  = new ArrayList<File>();

    static Map<String,String> fileNameMapClass = new HashMap<String,String>();
    static{

        // Mutex CameraService::sProxyMutex; sp<hardware::ICameraServiceProxy> CameraService::sCameraServiceProxy;
        //CameraService::CameraService() :mEventLog(DEFAULT_EVENT_LOG_LENGTH),mNumberOfCameras(0), mSoundRef(0), mInitialized(false),
        fileNameMapClass.put(cameraFileName,"/frameworks/av/services/camera/libcameraservice/CameraService.cpp");

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
        F8_Dump_Camera mDumpAnalysis = new F8_Dump_Camera();
        mDumpAnalysis.initAnalysisWithVersion();
        curAndroidAnalysis.analysisFile();
        NotePadOpenTargetFile();



    }

    static void NotePadOpenTargetFile(){
        String absPath = F8DirPathStr+File.separator+cameraFileName;
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
                    listFilename.add(cameraFileName);
                    break;
                case 10:
                    listFilename.add(getpropFileName);
                    listFilename.add(cameraFileName);
                    break;

                case 11:
                    listFilename.add(getpropFileName);
                    listFilename.add(cameraFileName);

                    break;
                default:
                    listFilename.add(getpropFileName);
                    listFilename.add(cameraFileName);

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



            file_keyword_map.put(cameraFileName,initCameraKeyWordList());

            return file_keyword_map;
        }


        ArrayList<KeyWordItem>    initCameraKeyWordList(){

            ArrayList<KeyWordItem> keyWordList = new ArrayList<KeyWordItem>();

            KeyWordItem camera_1_1 = new KeyWordItem();
            camera_1_1.keyword = "== Service global info: ==";
            camera_1_1.explain="Camera的一些id信息  adb shell  dumpsys  media.camera";
            camera_1_1.classNameForShuxing = "  /frameworks/av/services/camera/libcameraservice/CameraService.cpp  ";
            camera_1_1.printcode="CameraService.h CameraService.cpp   status_t CameraService::dump(int fd, const Vector<String16>& args) {} ";
            camera_1_1.printLogUrl="";
            camera_1_1.expain1="CameraService.cpp\n" +
                    "\n" +
                    "int   mNumberOfCameras;   //  mNumberOfCameras(0),  当前设备的摄像头个数 \n" +
                    "std::vector<std::string> mNormalDeviceIds;   // 摄像头的ID集合\n" +
                    "\n" +
                    "// Container for managing currently active application-layer clients\n" +
                    "CameraClientManager mActiveClientManager;  // 管理客户端的manager\n" +
                    "\t\n" +
                    "// Currently allowed user IDs\n" +
                    "std::set<userid_t> mAllowedUsers;   // 当前允许使用camera的 appid集合\n" +
                    "\n" +
                    "status_t CameraService::dump(int fd, const Vector<String16>& args) {\n" +
                    "  dprintf(fd, \"\\n== Service global info: ==\\n\\n\");\n" +
                    "    dprintf(fd, \"Number of camera devices: %d\\n\", mNumberOfCameras);\n" +
                    "    dprintf(fd, \"Number of normal camera devices: %zu\\n\", mNormalDeviceIds.size());\n" +
                    "    for (size_t i = 0; i < mNormalDeviceIds.size(); i++) {\n" +
                    "        dprintf(fd, \"    Device %zu maps to \\\"%s\\\"\\n\", i, mNormalDeviceIds[i].c_str());  // 打印摄像头的ID值\n" +
                    "    }\n" +
                    "    String8 activeClientString = mActiveClientManager.toString();\n" +
                    "    dprintf(fd, \"Active Camera Clients:\\n%s\", activeClientString.string());  // 当前活跃的客户端\n" +
                    "    dprintf(fd, \"Allowed user IDs: %s\\n\", toString(mAllowedUsers).string()); //  // 当前允许使用camera的 appid集合\n" +
                    "\t\n" +
                    "}\n" +
                    "LOG示例:\nActive Camera Clients:\n" +
                    "[\n" +
                    "(Camera ID: 0, Cost: 100, PID: 8926, Score: -2147483648, State: 2User Id: 0, Client Package Name: com.motorola.camera2, Conflicting Client Devices: {})\n" +
                    "]\n" +
                    "Allowed user IDs: 0";


            keyWordList.add(camera_1_1);


            KeyWordItem camera_1_2 = new KeyWordItem();
            camera_1_2.keyword = "== Camera service events log";
            camera_1_2.explain="Camera的 Service 日志信息   ";
            camera_1_2.classNameForShuxing = "  CameraService.h CameraService.cpp ";
            camera_1_2.printcode="   status_t CameraService::dump(int fd, const Vector<String16>& args) ";
            camera_1_2.printLogUrl="";
            camera_1_2.expain1="************** Camera service events 添加日志代码 Begin  **************\n" +
                    "String8 msg = String8::format(\"%s : DENIED connect device %s client for package %s \"\n" +
                    "\t\t\"(PID %d, score %d state %d) due to eviction policy\", curTime.string(),\n" +
                    "\t\tcameraId.string(), packageName.string(), clientPid,\n" +
                    "\t\tpriorityScores[priorityScores.size() - 1],\n" +
                    "\t\tstates[states.size() - 1]);\n" +
                    "\t\t\t\t\t\n" +
                    "msg.appendFormat(\"\\n   - Blocked by existing device %s client for package %s\"\n" +
                    "\t\t\t\t\"(PID %\" PRId32 \", score %\" PRId32 \", state %\" PRId32 \")\",\n" +
                    "\t\t\t\ti->getKey().string(),\n" +
                    "\t\t\t\tString8{i->getValue()->getPackageName()}.string(),\n" +
                    "\t\t\t\ti->getOwnerId(), i->getPriority().getScore(),\n" +
                    "\t\t\t\ti->getPriority().getState());\n" +
                    "\t\t\t\t\t\t\t\n" +
                    "mEventLog.add(msg);\n" +
                    "\n" +
                    "void CameraService::logEvent(const char* event) {\n" +
                    "mEventLog.add(String8::format(\"%s : %s\", curTime.string(), event));\n" +
                    "}\n" +
                    "\n" +
                    "// Log the clients evicted\n" +
                    "logEvent(String8::format(\"EVICT device %s client held by package %s (PID\"\n" +
                    "\t\t\" %\" PRId32 \", score %\" PRId32 \", state %\" PRId32 \")\\n - Evicted by device %s client for\"\n" +
                    "\t\t\" package %s (PID %d, score %\" PRId32 \", state %\" PRId32 \")\",\n" +
                    "\t\ti->getKey().string(), String8{clientSp->getPackageName()}.string(),\n" +
                    "\t\ti->getOwnerId(), i->getPriority().getScore(),\n" +
                    "\t\ti->getPriority().getState(), cameraId.string(),\n" +
                    "\t\tpackageName.string(), clientPid,\n" +
                    "\t\tpriorityScores[priorityScores.size() - 1],\n" +
                    "\t\tstates[states.size() - 1]));\n" +
                    "\t\t\n" +
                    "// Log the clients evicted\n" +
                    "logEvent(String8::format(\"EVICT device %s client held by package %s (PID %\"\n" +
                    "\t\tPRId32 \", score %\" PRId32 \", state %\" PRId32 \")\\n   - Evicted due\"\n" +
                    "\t\t\" to user switch.\", i->getKey().string(),\n" +
                    "\t\tString8{clientSp->getPackageName()}.string(),\n" +
                    "\t\ti->getOwnerId(), i->getPriority().getScore(),\n" +
                    "\t\ti->getPriority().getState()));\n" +
                    "\n" +
                    "// Log the clients evicted\n" +
                    "logEvent(String8::format(\"DISCONNECT device %s client for package %s (PID %d)\", cameraId, clientPackage, clientPid));\n" +
                    "\t\n" +
                    "// Log the clients CONNECT\n" +
                    "logEvent(String8::format(\"CONNECT device %s client for package %s (PID %d)\", cameraId,clientPackage, clientPid));\n" +
                    "\n" +
                    "// Log the client rejected\n" +
                    "logEvent(String8::format(\"REJECT device %s client for package %s (PID %d), reason: (%s)\",cameraId, clientPackage, clientPid, reason));\n" +
                    "\t\n" +
                    "// Log torch event\n" +
                    "logEvent(String8::format(\"Torch for camera id %s turned %s for client PID %d\", cameraId, torchState, clientPid));\t\n" +
                    "\n" +
                    "// Log the new and old users\n" +
                    "CameraService::logUserSwitch() -> logEvent(String8::format(\"USER_SWITCH previous allowed user IDs: %s, current allowed user IDs: %s\",oldUsers.string(), newUsers.string()));\n" +
                    "\n" +
                    "// Log the device removal\n" +
                    "logEvent(String8::format(\"REMOVE device %s, reason: (%s)\", cameraId, reason));\n" +
                    "\n" +
                    "// Log the device ADD\n" +
                    "logEvent(String8::format(\"ADD device %s, reason: (%s)\", cameraId, reason));\n" +
                    "\n" +
                    "// Log the device DIED\n" +
                    "logEvent(String8::format(\"DIED client(s) with PID %d, reason: (%s)\", clientPid, reason));\n" +
                    "\t\n" +
                    "logServiceError -> logEvent(String8::format(\"SERVICE ERROR: %s : %d (%s)\", msg, errorCode, strerror(-errorCode)));\n" +
                    "\n" +
                    "************** Camera service events 添加日志代码 End  **************\n" +
                    "\n" +
                    "status_t CameraService::dump(int fd, const Vector<String16>& args) {\n" +
                    "......\n" +
                    "    dumpEventLog(fd);\n" +
                    "......\n" +
                    "}\n" +
                    "\n" +
                    "    // Circular buffer for storing event logging for dumps\n" +
                    "    RingBuffer<String8> mEventLog;   // 日志缓存  \n" +
                    "\t\n" +
                    "void CameraService::dumpEventLog(int fd) {\n" +
                    "    dprintf(fd, \"\\n== Camera service events log (most recent at top): ==\\n\");\n" +
                    "\n" +
                    "    Mutex::Autolock l(mLogLock);\n" +
                    "    for (const auto& msg : mEventLog) {  // 打印每条日志 \n" +
                    "        dprintf(fd, \"  %s\\n\", msg.string());\n" +
                    "    }\n" +
                    "\n" +
                    "    if (mEventLog.size() == DEFAULT_EVENT_LOG_LENGTH) {  //  size_t DEFAULT_EVENT_LOG_LENGTH = 100;\n" +
                    "        dprintf(fd, \"  ...\\n\");\n" +
                    "    } else if (mEventLog.size() == 0) {\n" +
                    "        dprintf(fd, \"  [no events yet]\\n\");  // 没有日志信息\n" +
                    "    }\n" +
                    "    dprintf(fd, \"\\n\");\n" +
                    "}\n" +
                    "LOG示例:\n" +
                    "  01-10 14:46:52 : CONNECT device 0 client for package com.camera2 (PID 8926)\n" +
                    "  01-10 14:46:52 : DISCONNECT device 1 client for package com.camera2 (PID 8926)\n" +
                    "  01-10 14:46:49 : CONNECT device 1 client for package com.camera2 (PID 8926)\n" +
                    "  01-10 14:46:49 : DISCONNECT device 0 client for package com.camera2 (PID 8926)\n" +
                    "  01-10 14:46:42 : CONNECT device 0 client for package com.camera2 (PID 8926)\n" +
                    "  12-16 13:40:59 : USER_SWITCH previous allowed user IDs: <None>, current allowed user IDs: 0\n" +
                    "  12-16 13:40:49 : ADD device 0, reason: (Device added)\n" +
                    "  12-16 13:40:49 : ADD device 1, reason: (Device added)\n" +
                    "  12-16 13:40:49 : ADD device 4, reason: (Device added)\n" +
                    "  12-16 13:40:49 : ADD device 2, reason: (Device added)\n" +
                    "  12-16 13:40:49 : ADD device 3, reason: (Device added)";
            keyWordList.add(camera_1_2);



            KeyWordItem camera_1_3 = new KeyWordItem();
            camera_1_3.keyword = "== Camera device 0 dynamic info";
            camera_1_3.explain="Camera的 动态info信息   ";
            camera_1_3.classNameForShuxing = "  CameraService.h CameraService.cpp ";
            camera_1_3.printcode="   status_t CameraService::dump(int fd, const Vector<String16>& args) ";
            camera_1_3.printLogUrl="";
            camera_1_3.expain1="******************** Camera3Device.cpp -> CameraDeviceBase.cpp dump()  Begin ********************   \n" +
                    "status_t Camera3Device::dump(int fd, const Vector<String16> &args) {\n" +
                    "    ATRACE_CALL();\n" +
                    "    (void)args;\n" +
                    "    // Try to lock, but continue in case of failure (to avoid blocking in deadlocks)\n" +
                    "    bool gotInterfaceLock = tryLockSpinRightRound(mInterfaceLock);\n" +
                    "    bool gotLock = tryLockSpinRightRound(mLock);\n" +
                    "\n" +
                    "    ALOGW_IF(!gotInterfaceLock,\n" +
                    "            \"Camera %s: %s: Unable to lock interface lock, proceeding anyway\",\n" +
                    "            mId.string(), __FUNCTION__);\n" +
                    "    ALOGW_IF(!gotLock,\"Camera %s: %s: Unable to lock main lock, proceeding anyway\",  mId.string(), __FUNCTION__);\n" +
                    "\n" +
                    "    bool dumpTemplates = false;\n" +
                    "\n" +
                    "    String16 templatesOption(\"-t\");\n" +
                    "    int n = args.size();\n" +
                    "    for (int i = 0; i < n; i++) {\n" +
                    "        if (args[i] == templatesOption) {\n" +
                    "            dumpTemplates = true;\n" +
                    "        }\n" +
                    "        if (args[i] == TagMonitor::kMonitorOption) {\n" +
                    "            if (i + 1 < n) {\n" +
                    "                String8 monitorTags = String8(args[i + 1]);\n" +
                    "                if (monitorTags == \"off\") {\n" +
                    "                    mTagMonitor.disableMonitoring();\n" +
                    "                } else {\n" +
                    "                    mTagMonitor.parseTagsToMonitor(monitorTags);\n" +
                    "                }\n" +
                    "            } else {\n" +
                    "                mTagMonitor.disableMonitoring();\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    String8 lines;\n" +
                    "\n" +
                    "    const char *status =\n" +
                    "            mStatus == STATUS_ERROR         ? \"ERROR\" :\n" +
                    "            mStatus == STATUS_UNINITIALIZED ? \"UNINITIALIZED\" :\n" +
                    "            mStatus == STATUS_UNCONFIGURED  ? \"UNCONFIGURED\" :\n" +
                    "            mStatus == STATUS_CONFIGURED    ? \"CONFIGURED\" :\n" +
                    "            mStatus == STATUS_ACTIVE        ? \"ACTIVE\" :\n" +
                    "            \"Unknown\";\n" +
                    "\n" +
                    "    lines.appendFormat(\"    Device status: %s\\n\", status);\n" +
                    "    if (mStatus == STATUS_ERROR) {\n" +
                    "        lines.appendFormat(\"    Error cause: %s\\n\", mErrorCause.string());\n" +
                    "    }\n" +
                    "    lines.appendFormat(\"    Stream configuration:\\n\");\n" +
                    "    const char *mode =\n" +
                    "            mOperatingMode == static_cast<int>(StreamConfigurationMode::NORMAL_MODE) ? \"NORMAL\" :\n" +
                    "            mOperatingMode == static_cast<int>(\n" +
                    "                StreamConfigurationMode::CONSTRAINED_HIGH_SPEED_MODE) ? \"CONSTRAINED_HIGH_SPEED\" :\n" +
                    "            \"CUSTOM\";\n" +
                    "    lines.appendFormat(\"    Operation mode: %s (%d) \\n\", mode, mOperatingMode);\n" +
                    "\n" +
                    "    if (mInputStream != NULL) {\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "        mInputStream->dump(fd, args);\n" +
                    "    } else {\n" +
                    "        lines.appendFormat(\"      No input stream.\\n\");\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "    }\n" +
                    "    for (size_t i = 0; i < mOutputStreams.size(); i++) {\n" +
                    "        mOutputStreams[i]->dump(fd,args);\n" +
                    "    }\n" +
                    "\n" +
                    "    if (mBufferManager != NULL) {\n" +
                    "        lines = String8(\"    Camera3 Buffer Manager:\\n\");\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "        mBufferManager->dump(fd, args);\n" +
                    "    }\n" +
                    "\n" +
                    "    lines = String8(\"    In-flight requests:\\n\");\n" +
                    "    if (mInFlightMap.size() == 0) {\n" +
                    "        lines.append(\"      None\\n\");\n" +
                    "    } else {\n" +
                    "        for (size_t i = 0; i < mInFlightMap.size(); i++) {\n" +
                    "            InFlightRequest r = mInFlightMap.valueAt(i);\n" +
                    "            lines.appendFormat(\"      Frame %d |  Timestamp: %\" PRId64 \", metadata\"\n" +
                    "                    \" arrived: %s, buffers left: %d\\n\", mInFlightMap.keyAt(i),\n" +
                    "                    r.shutterTimestamp, r.haveResultMetadata ? \"true\" : \"false\",\n" +
                    "                    r.numBuffersLeft);\n" +
                    "        }\n" +
                    "    }\n" +
                    "    write(fd, lines.string(), lines.size());\n" +
                    "\n" +
                    "    if (mRequestThread != NULL) {\n" +
                    "        mRequestThread->dumpCaptureRequestLatency(fd,\n" +
                    "                \"    ProcessCaptureRequest latency histogram:\");\n" +
                    "    }\n" +
                    "\n" +
                    "    {\n" +
                    "        lines = String8(\"    Last request sent:\\n\");\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "\n" +
                    "        CameraMetadata lastRequest = getLatestRequestLocked();\n" +
                    "        lastRequest.dump(fd, /*verbosity*/2, /*indentation*/6);\n" +
                    "    }\n" +
                    "\n" +
                    "    if (dumpTemplates) {\n" +
                    "        const char *templateNames[CAMERA3_TEMPLATE_COUNT] = {\n" +
                    "            \"TEMPLATE_PREVIEW\",\n" +
                    "            \"TEMPLATE_STILL_CAPTURE\",\n" +
                    "            \"TEMPLATE_VIDEO_RECORD\",\n" +
                    "            \"TEMPLATE_VIDEO_SNAPSHOT\",\n" +
                    "            \"TEMPLATE_ZERO_SHUTTER_LAG\",\n" +
                    "            \"TEMPLATE_MANUAL\",\n" +
                    "        };\n" +
                    "\n" +
                    "        for (int i = 1; i < CAMERA3_TEMPLATE_COUNT; i++) {\n" +
                    "            camera_metadata_t *templateRequest = nullptr;\n" +
                    "            if(mInterface != NULL){\n" +
                    "                mInterface->constructDefaultRequestSettings(   (camera3_request_template_t) i, &templateRequest);\n" +
                    "            }\n" +
                    "\n" +
                    "            lines = String8::format(\"    HAL Request %s:\\n\", templateNames[i-1]);\n" +
                    "            if (templateRequest == nullptr) {\n" +
                    "                lines.append(\"       Not supported\\n\");\n" +
                    "                write(fd, lines.string(), lines.size());\n" +
                    "            } else {\n" +
                    "                write(fd, lines.string(), lines.size());\n" +
                    "                dump_indented_camera_metadata(templateRequest,  fd, /*verbosity*/2, /*indentation*/8);\n" +
                    "            }\n" +
                    "            free_camera_metadata(templateRequest);\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    mTagMonitor.dumpMonitoredMetadata(fd);\n" +
                    "\n" +
                    "    // Motorola, oesteves, IKSWO-12306, null-check\n" +
                    "    if (mInterface != NULL && mInterface->valid()) {\n" +
                    "        lines = String8(\"     HAL device dump:\\n\");\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "        mInterface->dump(fd);\n" +
                    "    }\n" +
                    "\n" +
                    "    if (gotLock) mLock.unlock();\n" +
                    "    if (gotInterfaceLock) mInterfaceLock.unlock();\n" +
                    "\n" +
                    "    return OK;\n" +
                    "}\n" +
                    "  \n" +
                    "******************** Camera3Device.cpp -> CameraDeviceBase.cpp dump()  End ********************   \n" +
                    "  \n" +
                    "******************** Camera2ClientBase.cpp  dumpDevice()  End ********************  \n" +
                    "\n" +
                    "template <typename TClientBase>\n" +
                    "status_t Camera2ClientBase<TClientBase>::dumpDevice(   int fd,    const Vector<String16>& args) {\n" +
                    "    String8 result;\n" +
                    "\n" +
                    "    result = \"  Device dump:\\n\";\n" +
                    "    write(fd, result.string(), result.size());\n" +
                    "    sp<CameraDeviceBase> device = mDevice;\n" +
                    "    if (!device.get()) {\n" +
                    "        result = \"  *** Device is detached\\n\";\n" +
                    "        write(fd, result.string(), result.size());\n" +
                    "        return NO_ERROR;\n" +
                    "    }\n" +
                    "\n" +
                    "    status_t res = device->dump(fd, args);  // CameraDeviceBase 的 dump() 方法\n" +
                    "    if (res != OK) {\n" +
                    "        result = String8::format(\"   Error dumping device: %s (%d)\",strerror(-res), res);\n" +
                    "        write(fd, result.string(), result.size());\n" +
                    "    }\n" +
                    "\n" +
                    "    return NO_ERROR;\n" +
                    "}\n" +
                    "\n" +
                    "******************** Camera2ClientBase.cpp  dumpDevice()  Begin ********************  \n" +
                    "******************** PhysicalCaptureResultInfo.java  Begin  ********************\n" +
                    "class PhysicalCaptureResultInfo implements Parcelable {\n" +
                    "   String cameraId;\n" +
                    "   CameraMetadataNative cameraMetadata;\n" +
                    "}\n" +
                    "   \n" +
                    "******************** PhysicalCaptureResultInfo.java   End  ********************\n" +
                    "\n" +
                    "\n" +
                    "******************** CameraMetadata.cpp dump() Begin   ********************\n" +
                    "CameraMetadata.cpp\n" +
                    "\n" +
                    "void CameraMetadata::dump(int fd, int verbosity, int indentation) const {\n" +
                    "    dump_indented_camera_metadata(mBuffer, fd, verbosity, indentation);\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "/system/media/camera/src/camera_metadata.c\n" +
                    "\n" +
                    "void dump_indented_camera_metadata(const camera_metadata_t *metadata,  int fd,  int verbosity,   int indentation) {\n" +
                    "    if (metadata == NULL) {\n" +
                    "        dprintf(fd, \"%*sDumping camera metadata array: Not allocated\\n\",  indentation, \"\");\n" +
                    "        return;\n" +
                    "    }\n" +
                    "    unsigned int i;\n" +
                    "    dprintf(fd,\n" +
                    "            \"%*sDumping camera metadata array: %\" PRIu32 \" / %\" PRIu32 \" entries, \"\n" +
                    "            \"%\" PRIu32 \" / %\" PRIu32 \" bytes of extra data.\\n\", indentation, \"\",\n" +
                    "            metadata->entry_count, metadata->entry_capacity,\n" +
                    "            metadata->data_count, metadata->data_capacity);\n" +
                    "\t\t\t\n" +
                    "    dprintf(fd, \"%*sVersion: %d, Flags: %08x\\n\",\n" +
                    "            indentation + 2, \"\",\n" +
                    "            metadata->version, metadata->flags);\n" +
                    "    camera_metadata_buffer_entry_t *entry = get_entries(metadata);\n" +
                    "    for (i=0; i < metadata->entry_count; i++, entry++) {\n" +
                    "\n" +
                    "        const char *tag_name, *tag_section;\n" +
                    "        tag_section = get_local_camera_metadata_section_name(entry->tag, metadata);\n" +
                    "        if (tag_section == NULL) {\n" +
                    "            tag_section = \"unknownSection\";\n" +
                    "        }\n" +
                    "        tag_name = get_local_camera_metadata_tag_name(entry->tag, metadata);\n" +
                    "        if (tag_name == NULL) {\n" +
                    "            tag_name = \"unknownTag\";\n" +
                    "        }\n" +
                    "        const char *type_name;\n" +
                    "        if (entry->type >= NUM_TYPES) {\n" +
                    "            type_name = \"unknown\";\n" +
                    "        } else {\n" +
                    "            type_name = camera_metadata_type_names[entry->type];\n" +
                    "        }\n" +
                    "        dprintf(fd, \"%*s%s.%s (%05x): %s[%\" PRIu32 \"]\\n\",\n" +
                    "             indentation + 2, \"\",\n" +
                    "             tag_section,\n" +
                    "             tag_name,\n" +
                    "             entry->tag,\n" +
                    "             type_name,\n" +
                    "             entry->count);\n" +
                    "\n" +
                    "        if (verbosity < 1) continue;\n" +
                    "\n" +
                    "        if (entry->type >= NUM_TYPES) continue;\n" +
                    "\n" +
                    "        size_t type_size = camera_metadata_type_size[entry->type];\n" +
                    "        uint8_t *data_ptr;\n" +
                    "        if ( type_size * entry->count > 4 ) {\n" +
                    "            if (entry->data.offset >= metadata->data_count) {\n" +
                    "                ALOGE(\"%s: Malformed entry data offset: %\" PRIu32 \" (max %\" PRIu32 \")\",\n" +
                    "                        __FUNCTION__,\n" +
                    "                        entry->data.offset,\n" +
                    "                        metadata->data_count);\n" +
                    "                continue;\n" +
                    "            }\n" +
                    "            data_ptr = get_data(metadata) + entry->data.offset;\n" +
                    "        } else {\n" +
                    "            data_ptr = entry->data.value;\n" +
                    "        }\n" +
                    "        int count = entry->count;\n" +
                    "        if (verbosity < 2 && count > 16) count = 16;\n" +
                    "\n" +
                    "        print_data(fd, data_ptr, entry->tag, entry->type, count, indentation);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "******************** CameraMetadata.cpp dump()  End  ********************\n" +
                    "\n" +
                    "******************** FrameProcessorBase Begin   ********************\n" +
                    "\n" +
                    "void FrameProcessorBase::dump(int fd, const Vector<String16>& /*args*/) {\n" +
                    "    String8 result(\"    Latest received frame:\\n\");\n" +
                    "    write(fd, result.string(), result.size());\n" +
                    "\n" +
                    "    CameraMetadata lastFrame;\n" +
                    "    std::map<std::string, CameraMetadata> lastPhysicalFrames;\n" +
                    "    {\n" +
                    "        // Don't race while dumping metadata\n" +
                    "        Mutex::Autolock al(mLastFrameMutex);\n" +
                    "        lastFrame = CameraMetadata(mLastFrame); // 获取 CameraMetadata\n" +
                    "\n" +
                    "\t\t// mLastPhysicalFrames  物理帧集合   std::vector<PhysicalCaptureResultInfo> mLastPhysicalFrames; \n" +
                    "\t\t\n" +
                    "        for (const auto& physicalFrame : mLastPhysicalFrames) { // mLastPhysicalFrames  物理帧 \n" +
                    "\t\t// 编译:vector<PhysicalCaptureResultInfo>  并加入 map<std::string, CameraMetadata>  \n" +
                    "\t\t// key是cameraid, value是 physicalFrame.CameraMetadata\n" +
                    "\t\t\n" +
                    "            lastPhysicalFrames.emplace(String8(physicalFrame.mPhysicalCameraId),  physicalFrame.mPhysicalCameraMetadata);\n" +
                    "        }\n" +
                    "    }\n" +
                    "    lastFrame.dump(fd, /*verbosity*/2, /*indentation*/6);   // 打印 最新帧  CameraMetadata 的 dump方法\n" +
                    "\n" +
                    "\t // 遍历CameraMetadata 打印所有的 CameraMetadata  std::map<std::string, CameraMetadata>\n" +
                    "    for (const auto& physicalFrame : lastPhysicalFrames) { \n" +
                    "        result = String8::format(\"   Latest received frame for physical camera %s:\\n\",   physicalFrame.first.c_str());\n" +
                    "        write(fd, result.string(), result.size());\n" +
                    "        CameraMetadata lastPhysicalMetadata = CameraMetadata(physicalFrame.second);\n" +
                    "        lastPhysicalMetadata.sort();\n" +
                    "        lastPhysicalMetadata.dump(fd, /*verbosity*/2, /*indentation*/6);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "******************** FrameProcessorBase End  ********************\n" +
                    "  \n" +
                    "******************** CameraDeviceClient dumpClient() Begin ********************  \n" +
                    "CameraDeviceClient.cpp  继承 class BasicClient : public virtual RefBase {}\n" +
                    "\n" +
                    "status_t CameraDeviceClient::dumpClient(int fd, const Vector<String16>& args) {\n" +
                    "    dprintf(fd, \"  CameraDeviceClient[%s] (%p) dump:\\n\",  mCameraIdStr.string(), (getRemoteCallback() != NULL ?\n" +
                    "            IInterface::asBinder(getRemoteCallback()).get() : NULL) );\n" +
                    "    dprintf(fd, \"    Current client UID %u\\n\", mClientUid);   // 客户端UID   BasicClient.mClientUid \n" +
                    "\n" +
                    "    dprintf(fd, \"    State:\\n\");\n" +
                    "\t\n" +
                    "\t // 占用CameraID的个数  CameraDeviceClient.h   int32_t mRequestIdCounter;\n" +
                    "    dprintf(fd, \"      Request ID counter: %d\\n\", mRequestIdCounter); \n" +
                    "\t\n" +
                    "// struct InputStreamConfiguration {\n" +
                    "//         bool configured;\n" +
                    "//         int32_t width;\n" +
                    "//         int32_t height;\n" +
                    "//         int32_t format;\n" +
                    "//         int32_t id;\n" +
                    "//     } mInputStream;\n" +
                    "\n" +
                    "    if (mInputStream.configured) {\n" +
                    "        dprintf(fd, \"      Current input stream ID: %d\\n\", mInputStream.id);  // 输入ID 流Steam 配置\n" +
                    "    } else {\n" +
                    "        dprintf(fd, \"      No input stream configured.\\n\");\n" +
                    "    }\n" +
                    "\t\n" +
                    "// IGraphicsBufferProducer binder -> Stream ID + Surface ID for output streams\n" +
                    "//  KeyedVector<sp<IBinder>, StreamSurfaceId> mStreamMap;  // 输出流的容器 MAP\n" +
                    "\n" +
                    "    if (!mStreamMap.isEmpty()) {\n" +
                    "        dprintf(fd, \"      Current output stream/surface IDs:\\n\");   // 输出的流  对应的输出的surface\n" +
                    "        for (size_t i = 0; i < mStreamMap.size(); i++) {\n" +
                    "            dprintf(fd, \"        Stream %d Surface %d\\n\",        //  输出流ID  与  Surface的ID\n" +
                    "                                mStreamMap.valueAt(i).streamId(),\n" +
                    "                                mStreamMap.valueAt(i).surfaceId());\n" +
                    "        }\n" +
                    "    } else if (!mDeferredStreams.isEmpty()) {   //     Vector<int32_t> mDeferredStreams;   保持的延期的流ID集合\n" +
                    "        dprintf(fd, \"      Current deferred surface output stream IDs:\\n\");\n" +
                    "        for (auto& streamId : mDeferredStreams) {\n" +
                    "            dprintf(fd, \"        Stream %d\\n\", streamId);  // 打印的流ID \n" +
                    "        }\n" +
                    "    } else {\n" +
                    "        dprintf(fd, \"      No output streams configured.\\n\");\n" +
                    "    }\n" +
                    "    // TODO: print dynamic/request section from most recent requests\n" +
                    "\t\n" +
                    "\t   \n" +
                    " // sp<camera2::FrameProcessorBase> mFrameProcessor; // 预览相关的帧处理回调处理/** Preview callback related members */\n" +
                    "\t\n" +
                    "    mFrameProcessor->dump(fd, args);  \n" +
                    "\n" +
                    "    return dumpDevice(fd, args);\n" +
                    "}\n" +
                    "\n" +
                    "******************** CameraDeviceClient dumpClient() End ********************\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "******************** CameraParameters dump() Begin ********************\n" +
                    "CameraParameters.cpp \n" +
                    " DefaultKeyedVector<String8,String8>    mMap;  \n" +
                    " \n" +
                    "void CameraParameters::dump() const\n" +
                    "{\n" +
                    "    ALOGD(\"dump: mMap.size = %zu\", mMap.size());\n" +
                    "    for (size_t i = 0; i < mMap.size(); i++) {\n" +
                    "        String8 k, v;\n" +
                    "        k = mMap.keyAt(i);\n" +
                    "        v = mMap.valueAt(i);\n" +
                    "        ALOGD(\"%s: %s\\n\", k.string(), v.string());\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "******************** CameraParameters dump() End ********************\n" +
                    "\n" +
                    "// Mapping from camera ID -> state for each device, map is protected by mCameraStatesLock\n" +
                    "std::map<String8, std::shared_ptr<CameraState>> mCameraStates;\n" +
                    "\n" +
                    "// Container for managing currently active application-layer clients\n" +
                    "CameraClientManager mActiveClientManager;\n" +
                    "\n" +
                    "ClientDescriptor   // Camera 客户端的描述?  是 CameraClient.cpp ? \n" +
                    "\n" +
                    "status_t CameraService::dump(int fd, const Vector<String16>& args) { \n" +
                    "  \n" +
                    "  for (auto& state : mCameraStates) {\n" +
                    "\tString8 cameraId = state.first;\n" +
                    "\n" +
                    "\tdprintf(fd, \"== Camera device %s dynamic info: ==\\n\", cameraId.string());  // 打印每个camera的动态信息\n" +
                    "\n" +
                    "\tCameraParameters p = state.second->getShimParams();  // 获取 CameraParameters 参数\n" +
                    "\tif (!p.isEmpty()) {  // CameraParameters 参数 不为空 那么打印参数   DefaultKeyedVector<String8,String8>    mMap;   bool CameraParameters::isEmpty() const {   return mMap.isEmpty();}\n" +
                    "\t\tdprintf(fd, \"  Camera1 API shim is using parameters:\\n        \");     // 好像没有打印\n" +
                    "\t\tp.dump(fd, args);\n" +
                    "\t}\n" +
                    "\n" +
                    "// ClientManager.h -> class ClientDescriptor final {}  \n" +
                    "// ClientDescriptor 包含  ClientPriority &getPriority() const;   // Return the priority for this descriptor.\n" +
                    "//   typedef std::shared_ptr<resource_policy::ClientDescriptor<String8,   sp<CameraService::BasicClient>>> DescriptorPtr;\n" +
                    "\n" +
                    "// ClientManager.h -> class ClientPriority { }\n" +
                    "//   int32_t getScore() const { return mScore; }  分数       int32_t mScore;\n" +
                    "//   int32_t getState() const { return mState; }  状态        int32_t mState;\n" +
                    "//     bool mIsVendorClient = false;                        是否是产商的APPID\n" +
                    "\n" +
                    "// clientDescriptor 是一个 DescriptorPtr ?    \n" +
                    "\tauto clientDescriptor = mActiveClientManager.get(cameraId);  // 获取该cameraid对用的客户端使用信息\n" +
                    "\tif (clientDescriptor != nullptr) {\n" +
                    "\t\tdprintf(fd, \"  Device %s is open. Client instance dump:\\n\",cameraId.string());\n" +
                    "\t\tdprintf(fd, \"    Client priority score: %d state: %d\\n\",      \n" +
                    "\t\t\t\tclientDescriptor->getPriority().getScore(),      //  客户端的分数 ?\n" +
                    "\t\t\t\tclientDescriptor->getPriority().getState());   // 客户端的状态? \n" +
                    "\t\tdprintf(fd, \"    Client PID: %d\\n\", clientDescriptor->getOwnerId());  // 客户端的  PID\n" +
                    "\n" +
                    "\t\tauto client =  clientDescriptor->getValue();\n" +
                    "\t\tdprintf(fd, \"    Client package: %s\\n\",String8(client->getPackageName()).string()); // PID 对应的 包名\n" +
                    "\n" +
                    "\t\tclient->dumpClient(fd, args);  // CameraDeviceClient 客户端打印\n" +
                    "\t} else {\n" +
                    "\t  dprintf(fd, \"  Device %s is closed, no client instance\\n\",cameraId.string()); // 对应的camera是关闭的 没有客户端使用\n" +
                    "\t}\n" +
                    "\n" +
                    "}\n" +
                    "}\n";
            keyWordList.add(camera_1_3);


            KeyWordItem camera_1_4 = new KeyWordItem();
            camera_1_4.keyword = "  Device dump:";
            camera_1_4.explain="Camera3Device 的 dump() 方法  ";
            camera_1_4.classNameForShuxing = "  CameraService.h CameraService.cpp ";
            camera_1_4.printcode="   status_t CameraService::dump(int fd, const Vector<String16>& args) ";
            camera_1_4.printLogUrl="";
            camera_1_4.expain1="******************** Camera3BufferManager.cpp dump() Begin ********************\n" +
                    "\n" +
                    "KeyedVector<StreamSetId, StreamSet> mStreamSetMap;\n" +
                    "InfoMap streamInfoMap;\n" +
                    "\n" +
                    "void Camera3BufferManager::dump(int fd, const Vector<String16>& args) const {\n" +
                    "    Mutex::Autolock l(mLock);\n" +
                    "\n" +
                    "    (void) args;\n" +
                    "    String8 lines;\n" +
                    "    lines.appendFormat(\"      Total stream sets: %zu\\n\", mStreamSetMap.size());\n" +
                    "    for (size_t i = 0; i < mStreamSetMap.size(); i++) {\n" +
                    "        lines.appendFormat(\"        Stream set %d has below streams:\\n\", mStreamSetMap.keyAt(i));\n" +
                    "        for (size_t j = 0; j < mStreamSetMap[i].streamInfoMap.size(); j++) {\n" +
                    "            lines.appendFormat(\"          Stream %d\\n\", mStreamSetMap[i].streamInfoMap[j].streamId);\n" +
                    "        }\n" +
                    "        lines.appendFormat(\"          Stream set max allowed buffer count: %zu\\n\",\n" +
                    "                mStreamSetMap[i].maxAllowedBufferCount);\n" +
                    "        lines.appendFormat(\"          Stream set buffer count water mark: %zu\\n\",\n" +
                    "                mStreamSetMap[i].allocatedBufferWaterMark);\n" +
                    "        lines.appendFormat(\"          Handout buffer counts:\\n\");\n" +
                    "        for (size_t m = 0; m < mStreamSetMap[i].handoutBufferCountMap.size(); m++) {\n" +
                    "            int streamId = mStreamSetMap[i].handoutBufferCountMap.keyAt(m);\n" +
                    "            size_t bufferCount = mStreamSetMap[i].handoutBufferCountMap.valueAt(m);\n" +
                    "            lines.appendFormat(\" stream id: %d, buffer count: %zu.\\n\",streamId, bufferCount);\n" +
                    "        }\n" +
                    "        lines.appendFormat(\"          Attached buffer counts:\\n\");\n" +
                    "        for (size_t m = 0; m < mStreamSetMap[i].attachedBufferCountMap.size(); m++) {\n" +
                    "            int streamId = mStreamSetMap[i].attachedBufferCountMap.keyAt(m);\n" +
                    "            size_t bufferCount = mStreamSetMap[i].attachedBufferCountMap.valueAt(m);\n" +
                    "            lines.appendFormat(\"stream id: %d, attached buffer count: %zu.\\n\", streamId, bufferCount);\n" +
                    "        }\n" +
                    "    }\n" +
                    "    write(fd, lines.string(), lines.size());\n" +
                    "}\n" +
                    "\n" +
                    "******************** Camera3BufferManager.cpp dump() End ********************\n" +
                    "\n" +
                    "  \n" +
                    "******************** Camera3Device.cpp -> CameraDeviceBase.cpp dump()  Begin ********************   \n" +
                    "status_t Camera3Device::dump(int fd, const Vector<String16> &args) {\n" +
                    "    ATRACE_CALL();\n" +
                    "    (void)args;\n" +
                    "    // Try to lock, but continue in case of failure (to avoid blocking in deadlocks)\n" +
                    "    bool gotInterfaceLock = tryLockSpinRightRound(mInterfaceLock);\n" +
                    "    bool gotLock = tryLockSpinRightRound(mLock);\n" +
                    "\n" +
                    "    ALOGW_IF(!gotInterfaceLock,\n" +
                    "            \"Camera %s: %s: Unable to lock interface lock, proceeding anyway\",\n" +
                    "            mId.string(), __FUNCTION__);\n" +
                    "    ALOGW_IF(!gotLock,\"Camera %s: %s: Unable to lock main lock, proceeding anyway\",  mId.string(), __FUNCTION__);\n" +
                    "\n" +
                    "    bool dumpTemplates = false;\n" +
                    "\n" +
                    "    String16 templatesOption(\"-t\");\n" +
                    "    int n = args.size();\n" +
                    "    for (int i = 0; i < n; i++) {\n" +
                    "        if (args[i] == templatesOption) {\n" +
                    "            dumpTemplates = true;\n" +
                    "        }\n" +
                    "        if (args[i] == TagMonitor::kMonitorOption) {\n" +
                    "            if (i + 1 < n) {\n" +
                    "                String8 monitorTags = String8(args[i + 1]);\n" +
                    "                if (monitorTags == \"off\") {\n" +
                    "                    mTagMonitor.disableMonitoring();\n" +
                    "                } else {\n" +
                    "                    mTagMonitor.parseTagsToMonitor(monitorTags);\n" +
                    "                }\n" +
                    "            } else {\n" +
                    "                mTagMonitor.disableMonitoring();\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    String8 lines;\n" +
                    "\n" +
                    "    const char *status =\n" +
                    "            mStatus == STATUS_ERROR         ? \"ERROR\" :\n" +
                    "            mStatus == STATUS_UNINITIALIZED ? \"UNINITIALIZED\" :\n" +
                    "            mStatus == STATUS_UNCONFIGURED  ? \"UNCONFIGURED\" :\n" +
                    "            mStatus == STATUS_CONFIGURED    ? \"CONFIGURED\" :\n" +
                    "            mStatus == STATUS_ACTIVE        ? \"ACTIVE\" :\n" +
                    "            \"Unknown\";\n" +
                    "\n" +
                    "    lines.appendFormat(\"    Device status: %s\\n\", status);  // 设备状态\n" +
                    "    if (mStatus == STATUS_ERROR) {\n" +
                    "        lines.appendFormat(\"    Error cause: %s\\n\", mErrorCause.string());\n" +
                    "    }\n" +
                    "    lines.appendFormat(\"    Stream configuration:\\n\");    // 流模式配置 ?  \"NORMAL\" \"CONSTRAINED_HIGH_SPEED\" \"CUSTOM\"\n" +
                    "    const char *mode =\n" +
                    "            mOperatingMode == static_cast<int>(StreamConfigurationMode::NORMAL_MODE) ? \"NORMAL\" :\n" +
                    "            mOperatingMode == static_cast<int>(\n" +
                    "                StreamConfigurationMode::CONSTRAINED_HIGH_SPEED_MODE) ? \"CONSTRAINED_HIGH_SPEED\" :\n" +
                    "            \"CUSTOM\";\n" +
                    "    lines.appendFormat(\"    Operation mode: %s (%d) \\n\", mode, mOperatingMode);\n" +
                    "\n" +
                    "    if (mInputStream != NULL) {\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "        mInputStream->dump(fd, args);  // sp<camera3::Camera3Stream> mInputStream;  流的 dump \n" +
                    "    } else {\n" +
                    "        lines.appendFormat(\"      No input stream.\\n\");\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "    }\n" +
                    "\t//   StreamSet                  mOutputStreams;  输出流的ID集合 \n" +
                    "//Camera3Device.h -> 内部类 class StreamSet {\n" +
                    "// public:\n" +
                    "// status_t add(int streamId, sp<camera3::Camera3OutputStreamInterface>);\n" +
                    "// ssize_t remove(int streamId);\n" +
                    "// sp<camera3::Camera3OutputStreamInterface> get(int streamId);\n" +
                    "// // get by (underlying) vector index\n" +
                    "// sp<camera3::Camera3OutputStreamInterface> operator[] (size_t index);\n" +
                    "// size_t size() const;\n" +
                    "// std::vector<int> getStreamIds();\n" +
                    "// void clear();\n" +
                    "// \n" +
                    "// private:\n" +
                    "// mutable std::mutex mLock;\n" +
                    "// KeyedVector<int, sp<camera3::Camera3OutputStreamInterface>> mData;\n" +
                    "// };\n" +
                    "\n" +
                    "\t\n" +
                    "    for (size_t i = 0; i < mOutputStreams.size(); i++) {\n" +
                    "        mOutputStreams[i]->dump(fd,args);  // \n" +
                    "    }\n" +
                    "\n" +
                    "\t//  sp<camera3::Camera3BufferManager> mBufferManager;  \n" +
                    "    if (mBufferManager != NULL) {\n" +
                    "        lines = String8(\"    Camera3 Buffer Manager:\\n\");\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "        mBufferManager->dump(fd, args);  // Camera3BufferManager 的 dump 打印\n" +
                    "    }\n" +
                    "\n" +
                    "    lines = String8(\"    In-flight requests:\\n\");\n" +
                    "    if (mInFlightMap.size() == 0) {\n" +
                    "        lines.append(\"      None\\n\");\n" +
                    "    } else {\n" +
                    "\t//     InFlightMap            mInFlightMap;    // 请求 InFlightRequest 的 Map\n" +
                    "        for (size_t i = 0; i < mInFlightMap.size(); i++) {\n" +
                    "            InFlightRequest r = mInFlightMap.valueAt(i);\n" +
                    "            lines.appendFormat(\"      Frame %d |  Timestamp: %\" PRId64 \", metadata\"\n" +
                    "                    \" arrived: %s, buffers left: %d\\n\", mInFlightMap.keyAt(i),\n" +
                    "                    r.shutterTimestamp, r.haveResultMetadata ? \"true\" : \"false\",\n" +
                    "                    r.numBuffersLeft);\n" +
                    "        }\n" +
                    "    }\n" +
                    "    write(fd, lines.string(), lines.size());\n" +
                    "\n" +
                    "\t//     sp<RequestThread> mRequestThread;    请求线程列表 \n" +
                    "    if (mRequestThread != NULL) {\n" +
                    "\t// 打印 RequestThread 的  dumpCaptureRequestLatency\n" +
                    "        mRequestThread->dumpCaptureRequestLatency(fd,\"ProcessCaptureRequest latency histogram:\");\n" +
                    "    }\n" +
                    "\n" +
                    "    {\n" +
                    "        lines = String8(\"    Last request sent:\\n\");\n" +
                    "        write(fd, lines.string(), lines.size());\n" +
                    "\n" +
                    "        CameraMetadata lastRequest = getLatestRequestLocked();  \n" +
                    "        lastRequest.dump(fd, /*verbosity*/2, /*indentation*/6);   // 打印 CameraMetadata\n" +
                    "    }\n" +
                    "//  bool dumpTemplates = false;   \n" +
                    "    if (dumpTemplates) {\n" +
                    "        const char *templateNames[CAMERA3_TEMPLATE_COUNT] = {\n" +
                    "            \"TEMPLATE_PREVIEW\",\n" +
                    "            \"TEMPLATE_STILL_CAPTURE\",\n" +
                    "            \"TEMPLATE_VIDEO_RECORD\",\n" +
                    "            \"TEMPLATE_VIDEO_SNAPSHOT\",\n" +
                    "            \"TEMPLATE_ZERO_SHUTTER_LAG\",\n" +
                    "            \"TEMPLATE_MANUAL\",\n" +
                    "        };\n" +
                    "\n" +
                    "        for (int i = 1; i < CAMERA3_TEMPLATE_COUNT; i++) {\n" +
                    "            camera_metadata_t *templateRequest = nullptr;\n" +
                    "            if(mInterface != NULL){\n" +
                    "\t\t\t// 初始化 templateRequest\n" +
                    "                mInterface->constructDefaultRequestSettings(   (camera3_request_template_t) i, &templateRequest);\n" +
                    "            }\n" +
                    "\n" +
                    "            lines = String8::format(\"    HAL Request %s:\\n\", templateNames[i-1]);\n" +
                    "            if (templateRequest == nullptr) {\n" +
                    "                lines.append(\"       Not supported\\n\");\n" +
                    "                write(fd, lines.string(), lines.size());\n" +
                    "            } else {\n" +
                    "                write(fd, lines.string(), lines.size());\n" +
                    "\t\t\t\t// 打印  camera_metadata_t \n" +
                    "                dump_indented_camera_metadata(templateRequest,  fd, /*verbosity*/2, /*indentation*/8);\n" +
                    "            }\n" +
                    "            free_camera_metadata(templateRequest);\n" +
                    "        }\n" +
                    "    }\n" +
                    "//     TagMonitor mTagMonitor;  监控Metadata类的dump\n" +
                    " \n" +
                    "    mTagMonitor.dumpMonitoredMetadata(fd);\n" +
                    "\n" +
                    "\n" +
                    "    if (gotLock) mLock.unlock();\n" +
                    "    if (gotInterfaceLock) mInterfaceLock.unlock();\n" +
                    "\n" +
                    "    return OK;\n" +
                    "}\n" +
                    "  \n" +
                    "******************** Camera3Device.cpp -> CameraDeviceBase.cpp dump()  End ********************   ";
            keyWordList.add(camera_1_4);


            KeyWordItem camera_1_5 = new KeyWordItem();
            camera_1_5.keyword = "== Camera Provider HAL";
            camera_1_5.explain="Camera的 Provider HAL 相关的 INFO  ";
            camera_1_5.classNameForShuxing = "  CameraService.h CameraService.cpp -》 CameraProviderManager  ";
            camera_1_5.printcode="   status_t CameraService::dump(int fd, const Vector<String16>& args){ CameraProviderManager.dump() } ";
            camera_1_5.printLogUrl="";
            camera_1_5.expain1="*********************** CameraProviderManager.cpp dump() Begin *********************** \n" +
                    "status_t CameraProviderManager::ProviderInfo::dump(int fd, const Vector<String16>&) const {\n" +
                    "// Camera Provider HAL [legacy/0]【Provider名称】 (v2.[5]【版本】, [remote]【\"remote\"或者\"passthrough\" HAL-Binder方式】) static info: 5【摄像头数量】 devices:\n" +
                    "    dprintf(fd, \"== Camera Provider HAL %s (v2.%d, %s) static info: %zu devices: ==\\n\",\n" +
                    "            mProviderName.c_str(),\n" +
                    "            mMinorVersion,\n" +
                    "            mIsRemote ? \"remote\" : \"passthrough\",\n" +
                    "            mDevices.size());\n" +
                    "\n" +
                    "    for (auto& device : mDevices) {  // 打印每个DeviceInfo   std::vector<std::unique_ptr<DeviceInfo>> mDevices;\n" +
                    "\t\n" +
                    "// == Camera HAL device [device@3.4/legacy/0]【设备名称】 (v[3]【主版本】.[4]【副版本】) static information: ==\n" +
                    "\n" +
                    "        dprintf(fd, \"== Camera HAL device %s (v%d.%d) static information: ==\\n\", device->mName.c_str(),\n" +
                    "                device->mVersion.get_major(), device->mVersion.get_minor());\n" +
                    "        dprintf(fd, \"  Resource cost: %d\\n\", device->mResourceCost.resourceCost);\n" +
                    "        if (device->mResourceCost.conflictingDevices.size() == 0) {\n" +
                    "            dprintf(fd, \"  Conflicting devices: None\\n\");\n" +
                    "        } else {\n" +
                    "            dprintf(fd, \"  Conflicting devices:\\n\");\n" +
                    "            for (size_t i = 0; i < device->mResourceCost.conflictingDevices.size(); i++) {\n" +
                    "                dprintf(fd, \"    %s\\n\", device->mResourceCost.conflictingDevices[i].c_str());\n" +
                    "            }\n" +
                    "        }\n" +
                    "        dprintf(fd, \"  API1 info:\\n\");\n" +
                    "        dprintf(fd, \"    Has a flash unit: %s\\n\",  device->hasFlashUnit() ? \"true\" : \"false\"); // 是否存在闪光功能\n" +
                    "        hardware::CameraInfo info;\n" +
                    "        status_t res = device->getCameraInfo(&info);\n" +
                    "        if (res != OK) {\n" +
                    "            dprintf(fd, \"   <Error reading camera info: %s (%d)>\\n\",\n" +
                    "                    strerror(-res), res);\n" +
                    "        } else {\n" +
                    "\t\t\n" +
                    "// Facing: Back    ,  Back后置摄像头   Facing: Front     Front前置摄像头\n" +
                    "// Orientation: 90   // 摄像头成像角度 \n" +
                    "\t\n" +
                    "            dprintf(fd, \" Facing: %s\\n\", info.facing == hardware::CAMERA_FACING_BACK ? \"Back\" : \"Front\");\n" +
                    "            dprintf(fd, \" Orientation: %d\\n\", info.orientation);\n" +
                    "        }\n" +
                    "        CameraMetadata info2;\n" +
                    "        res = device->getCameraCharacteristics(&info2);\n" +
                    "        if (res == INVALID_OPERATION) {\n" +
                    "            dprintf(fd, \"  API2 not directly supported\\n\");\n" +
                    "        } else if (res != OK) {\n" +
                    "            dprintf(fd, \"  <Error reading camera characteristics: %s (%d)>\\n\",\n" +
                    "                    strerror(-res), res);\n" +
                    "        } else {\n" +
                    "            dprintf(fd, \"  API2 camera characteristics:\\n\");\n" +
                    "            info2.dump(fd, /*verbosity*/ 2, /*indentation*/ 4);  // 打印 CameraMetadata \n" +
                    "        }\n" +
                    "\n" +
                    "        // Dump characteristics of non-standalone physical camera\n" +
                    "        if (device->mIsLogicalCamera) {\n" +
                    "            for (auto& id : device->mPhysicalIds) {\n" +
                    "                // Skip if physical id is an independent camera\n" +
                    "                if (std::find(mProviderPublicCameraIds.begin(), mProviderPublicCameraIds.end(), id)\n" +
                    "                        != mProviderPublicCameraIds.end()) {\n" +
                    "                    continue;\n" +
                    "                }\n" +
                    "\n" +
                    "                CameraMetadata physicalInfo;\n" +
                    "                status_t status = device->getPhysicalCameraCharacteristics(id, &physicalInfo);\n" +
                    "                if (status == OK) {\n" +
                    "                    dprintf(fd, \"  Physical camera %s characteristics:\\n\", id.c_str());\n" +
                    "                    physicalInfo.dump(fd, /*verbosity*/ 2, /*indentation*/ 4);\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        dprintf(fd, \"== Camera HAL device %s (v%d.%d) dumpState: ==\\n\", device->mName.c_str(),\n" +
                    "                device->mVersion.get_major(), device->mVersion.get_minor());\n" +
                    "        res = device->dumpState(fd);\n" +
                    "        if (res != OK) {\n" +
                    "            dprintf(fd, \"   <Error dumping device %s state: %s (%d)>\\n\",\n" +
                    "                    device->mName.c_str(), strerror(-res), res);\n" +
                    "        }\n" +
                    "    }\n" +
                    "    return OK;\n" +
                    "}\n" +
                    "\n" +
                    "*********************** CameraProviderManager.cpp dump() End *********************** \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "*********************** CameraService.cpp  dump() Begin ***********************\n" +
                    " mCameraProviderManager = new CameraProviderManager(); in enumerateProviders() \n" +
                    " \n" +
                    " status_t CameraService::dump(int fd, const Vector<String16>& args) {\n" +
                    " mCameraProviderManager->dump(fd, args);\n" +
                    " }\n" +
                    " \n" +
                    "*********************** CameraService.cpp  dump() End ***********************\n" +
                    "LOG示例: \n" +
                    "// Camera Provider HAL [legacy/0]【Provider名称】 (v2.[5]【版本】, [remote]【\"remote\"或者\"passthrough\" HAL-Binder方式】)  static info: 5 【摄像头数量】 devices:\n" +
                    "// == Camera HAL device [device@3.4/legacy/0]【设备名称】 (v[3]【主版本】.[4]【副版本】) static information: ==\n" +
                    "// Has a flash unit: true   【true-存在闪光功能  false-不存在闪光功能】\n" +
                    "// Facing: Back    ,  Back后置摄像头   Facing: Front     Front前置摄像头\n" +
                    "// Orientation: 90   // 摄像头成像角度 \n" +
                    "//  API2 camera characteristics:       CameraMetadata info2-> dump() 方法 ";
            keyWordList.add(camera_1_5);


            KeyWordItem camera_1_6 = new KeyWordItem();
            camera_1_6.keyword = " dumpState: ==";
            camera_1_6.isContain = true;
            camera_1_6.explain="DeviceInfo 的 dump()   ";
            camera_1_6.classNameForShuxing = "  CameraService.h CameraService.cpp ";
            camera_1_6.printcode="   status_t CameraService::dump(int fd, const Vector<String16>& args) ";
            camera_1_6.printLogUrl="";
            camera_1_6.expain1="*********************  CameraDeviceSession.cpp  dumpState () Begin *********************\n" +
                    "/hardware/interfaces/camera/device/3.2/default/CameraDeviceSession.cpp   // 3.4 版本为空  3.2版本才有这个函数\n" +
                    "\n" +
                    "void CameraDeviceSession::dumpState(const native_handle_t* fd) {\n" +
                    "    if (!isClosed()) {\n" +
                    "        mDevice->ops->dump(mDevice, fd->data[0]);  //  \n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "*********************  CameraDeviceSession.cpp  dumpState () End   *********************\n" +
                    "\n" +
                    "\n" +
                    "********************* ExternalCameraDevice.cpp  dump () Begin *********************\n" +
                    "/hardware/interfaces/camera/device/3.4/default/ExternalCameraDevice.cpp\t\n" +
                    "\n" +
                    "Return<void> ExternalCameraDevice::dumpState(const ::android::hardware::hidl_handle& handle) {\n" +
                    "    Mutex::Autolock _l(mLock);\n" +
                    "    if (handle.getNativeHandle() == nullptr) {\n" +
                    "        ALOGE(\"%s: handle must not be null\", __FUNCTION__);\n" +
                    "        return Void();\n" +
                    "    }\n" +
                    "    if (handle->numFds != 1 || handle->numInts != 0) {\n" +
                    "        ALOGE(\"%s: handle must contain 1 FD and 0 integers! Got %d FDs and %d ints\",   __FUNCTION__, handle->numFds, handle->numInts);\n" +
                    "        return Void();\n" +
                    "    }\n" +
                    "    int fd = handle->data[0];\n" +
                    "    if (mSession == nullptr) {\n" +
                    "        dprintf(fd, \"No active camera device session instance\\n\");\n" +
                    "        return Void();\n" +
                    "    }\n" +
                    "    auto session = mSession.promote();\n" +
                    "    if (session == nullptr) {\n" +
                    "        dprintf(fd, \"No active camera device session instance\\n\");\n" +
                    "        return Void();\n" +
                    "    }\n" +
                    "    // Call into active session to dump states\n" +
                    "    session->dumpState(handle);  // 打印 CameraDeviceSession \n" +
                    "    return Void();\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "********************* ExternalCameraDevice.cpp  dump () End *********************\n" +
                    "\n" +
                    "********************* CameraDevice.cpp  dump () Begin *********************\n" +
                    "/hardware/interfaces/camera/device/3.2/default/CameraDevice.cpp\n" +
                    "\n" +
                    "Return<void> CameraDevice::dumpState(const ::android::hardware::hidl_handle& handle)  {\n" +
                    "    Mutex::Autolock _l(mLock);\n" +
                    "    if (handle.getNativeHandle() == nullptr) {\n" +
                    "        ALOGE(\"%s: handle must not be null\", __FUNCTION__);\n" +
                    "        return Void();\n" +
                    "    }\n" +
                    "    if (handle->numFds != 1 || handle->numInts != 0) {\n" +
                    "        ALOGE(\"%s: handle must contain 1 FD and 0 integers! Got %d FDs and %d ints\",     __FUNCTION__, handle->numFds, handle->numInts);\n" +
                    "        return Void();\n" +
                    "    }\n" +
                    "    int fd = handle->data[0];\n" +
                    "    if (mSession == nullptr) {\n" +
                    "        dprintf(fd, \"No active camera device session instance\\n\");\n" +
                    "        return Void();\n" +
                    "    }\n" +
                    "    sp<CameraDeviceSession> session = mSession.promote();\n" +
                    "    if (session == nullptr) {\n" +
                    "        dprintf(fd, \"No active camera device session instance\\n\");\n" +
                    "        return Void();\n" +
                    "    }\n" +
                    "    // Call into active session to dump states\n" +
                    "    session->dumpState(handle);   // 打印 CameraDeviceSession \n" +
                    "    return Void();\n" +
                    "}\n" +
                    "\n" +
                    "********************* CameraDevice.cpp  dump () End *********************\n" +
                    "\n" +
                    "\t\n" +
                    "********************* CameraProviderManager  dump () Begin *********************\n" +
                    "status_t CameraProviderManager::ProviderInfo::dump(int fd, const Vector<String16>&) const {\n" +
                    "\n" +
                    "\t\t  for (auto& device : mDevices) {  // 打印每个DeviceInfo   std::vector<std::unique_ptr<DeviceInfo>> mDevices;\n" +
                    "\t\t        dprintf(fd, \"== Camera HAL device %s (v%d.%d) dumpState: ==\\n\", device->mName.c_str(),\n" +
                    "                device->mVersion.get_major(), device->mVersion.get_minor());\n" +
                    "        res = device->dumpState(fd);\n" +
                    "        if (res != OK) {\n" +
                    "            dprintf(fd, \"   <Error dumping device %s state: %s (%d)>\\n\",\n" +
                    "                    device->mName.c_str(), strerror(-res), res);\n" +
                    "        }\n" +
                    "    }\n" +
                    "    return OK;\n" +
                    "}\n" +
                    "********************* CameraProviderManager  dump () End *********************\n" +
                    "\n" +
                    " CameraService.dump() 调用 CameraProviderManager.dum()  //   mCameraProviderManager = new CameraProviderManager(); \n" +
                    " \n" +
                    " status_t CameraService::dump(int fd, const Vector<String16>& args) {\n" +
                    " mCameraProviderManager->dump(fd, args);\n" +
                    " }";
            keyWordList.add(camera_1_6);


            KeyWordItem camera_1_7 = new KeyWordItem();
            camera_1_7.keyword = "== Vendor tags: ==";
            camera_1_7.explain="厂商定义TAG 描述   ";
            camera_1_7.classNameForShuxing = "  CameraService.h CameraService.cpp -》 VendorTagDescriptor ";
            camera_1_7.printcode="   status_t CameraService::dump(int fd, const Vector<String16>& args) { VendorTagDescriptor }";
            camera_1_7.printLogUrl="";
            camera_1_7.expain1=" status_t CameraService::dump(int fd, const Vector<String16>& args) {\n" +
                    " ...........\n" +
                    "    dprintf(fd, \"\\n== Vendor tags: ==\\n\\n\");\n" +
                    "    sp<VendorTagDescriptor> desc = VendorTagDescriptor::getGlobalVendorTagDescriptor();  // 厂商自定义tag描述?\n" +
                    "    if (desc == NULL) {\n" +
                    "        sp<VendorTagDescriptorCache> cache = VendorTagDescriptorCache::getGlobalVendorTagCache();\n" +
                    "        if (cache == NULL) {\n" +
                    "            dprintf(fd, \"No vendor tags.\\n\");\n" +
                    "        } else {\n" +
                    "            cache->dump(fd, /*verbosity*/2, /*indentation*/2);  // VendorTagDescriptorCache 的 dump\n" +
                    "        }\n" +
                    "    } else {\n" +
                    "        desc->dump(fd, /*verbosity*/2, /*indentation*/2);// VendorTagDescriptorCache 的 dump\n" +
                    "    }";
            keyWordList.add(camera_1_7);


            KeyWordItem camera_1_8 = new KeyWordItem();
            camera_1_8.keyword = "== Camera error traces";
            camera_1_8.explain="Camera 错误追踪  ";
            camera_1_8.classNameForShuxing = "  CameraService.h CameraService.cpp -》 VendorTagDescriptor ";
            camera_1_8.printcode="   status_t CameraService::dump(int fd, const Vector<String16>& args) { VendorTagDescriptor }";
            camera_1_8.printLogUrl="";
            camera_1_8.expain1="************* ProcessCallStack.cpp  dump() Begin *************\n" +
                    "\n" +
                    "void ProcessCallStack::dump(int fd, int indent, const char* prefix) const {\n" +
                    "\n" +
                    "    if (indent < 0) {\n" +
                    "        ALOGW(\"%s: Bad indent (%d)\", __FUNCTION__, indent);\n" +
                    "        return;\n" +
                    "    }\n" +
                    "\n" +
                    "    FdPrinter printer(fd, static_cast<unsigned int>(indent), prefix);  // FdPrinter?\n" +
                    "    print(printer);\n" +
                    "}\n" +
                    "\n" +
                    "void ProcessCallStack::print(Printer& printer) const {\n" +
                    "    /*\n" +
                    "     * Print the header/footer with the regular printer.\n" +
                    "     * Print the callstack with an additional two spaces as the prefix for legibility.\n" +
                    "     */\n" +
                    "    PrefixPrinter csPrinter(printer, CALL_STACK_PREFIX);\n" +
                    "    printInternal(printer, csPrinter);  // Internal 打印 \n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "/// KeyedVector<pid_t, ThreadInfo> mThreadMap;  // 线程信息MAP\n" +
                    "\n" +
                    "void ProcessCallStack::printInternal(Printer& printer, Printer& csPrinter) const {\n" +
                    "    dumpProcessHeader(printer, getpid(), getTimeString(mTimeUpdated).string());  // 打印 Head 时间戳  PID 进程ID\n" +
                    "\n" +
                    "    for (size_t i = 0; i < mThreadMap.size(); ++i) {\n" +
                    "        pid_t tid = mThreadMap.keyAt(i);\n" +
                    "        const ThreadInfo& threadInfo = mThreadMap.valueAt(i);\n" +
                    "        const String8& threadName = threadInfo.threadName;\n" +
                    "\n" +
                    "        printer.printLine(\"\");\n" +
                    "        printer.printFormatLine(\"\\\"%s\\\" sysTid=%d\", threadName.string(), tid);  // 打印 TID 线程ID\n" +
                    "\n" +
                    "        threadInfo.callStack.print(csPrinter);  // threadInfo 调用栈打印 \n" +
                    "    }\n" +
                    "\n" +
                    "    dumpProcessFooter(printer, getpid());  // 进程分割线 \n" +
                    "}\n" +
                    "\n" +
                    "【CallStack】 Vector<String8> mFrameLines;   \n" +
                    "// std::unique_ptr<Backtrace> backtrace(Backtrace::Create(BACKTRACE_CURRENT_PROCESS, tid)); \n" +
                    "// for (size_t i = 0; i < backtrace->NumFrames(); i++) {  mFrameLines.push_back(String8(backtrace->FormatFrameData(i).c_str())); }\n" +
                    "\n" +
                    "void 【threadInfo.callStack.print】 CallStack::print(Printer& printer) const {\n" +
                    "    for (size_t i = 0; i < mFrameLines.size(); i++) {\n" +
                    "        printer.printLine(mFrameLines[i]);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "static void dumpProcessFooter(Printer& printer, pid_t pid) {\n" +
                    "    printer.printLine();\n" +
                    "    printer.printFormatLine(\"----- end %d -----\", pid);\n" +
                    "    printer.printLine();\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "************* ProcessCallStack  dump() END *************\n" +
                    "\n" +
                    "************* CameraTraces.cpp  dump()  begin *************\n" +
                    "  static CameraTracesImpl& sImpl;\n" +
                    "  \n" +
                    "status_t CameraTraces::dump(int fd, const Vector<String16> &args __attribute__((unused))) {\n" +
                    "    ALOGV(\"%s: fd = %d\", __FUNCTION__, fd);\n" +
                    "    Mutex::Autolock al(sImpl.tracesLock);\n" +
                    "    List<ProcessCallStack>& pcsList = sImpl.pcsList;  // CameraTracesImpl 的 ProcessCallStack\n" +
                    "\n" +
                    "    if (fd < 0) {   // 句柄 \n" +
                    "        ALOGW(\"%s: Negative FD (%d)\", __FUNCTION__, fd);\n" +
                    "        return BAD_VALUE;\n" +
                    "    }\n" +
                    "\n" +
                    "    dprintf(fd, \"== Camera error traces (%zu): ==\\n\", pcsList.size());\n" +
                    "\n" +
                    "    if (pcsList.empty()) {\n" +
                    "        dprintf(fd, \"  No camera traces collected.\\n\");\n" +
                    "    }\n" +
                    "\n" +
                    "    // Print newest items first\n" +
                    "    List<ProcessCallStack>::iterator it, end;   // 调用堆栈\n" +
                    "    for (it = pcsList.begin(), end = pcsList.end(); it != end; ++it) {\n" +
                    "        const ProcessCallStack& pcs = *it;\n" +
                    "        pcs.dump(fd, DUMP_INDENT);  // 打印 ProcessCallStack的 dump \n" +
                    "    }\n" +
                    "\n" +
                    "    return OK;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "************* CameraTraces dump()  end *************\n" +
                    "\n" +
                    " status_t CameraService::dump(int fd, const Vector<String16>& args) {\n" +
                    " ...........\n" +
                    "    // Dump camera traces if there were any\n" +
                    "    dprintf(fd, \"\\n\");\n" +
                    "    camera3::CameraTraces::dump(fd, args);\n" +
                    " ...........\n" +
                    " }";
            keyWordList.add(camera_1_8);


            /*

Service global info:


            KeyWordItem camera_1_8 = new KeyWordItem();
                        camera_1_8.keyword = "== Camera error traces";
                        camera_1_8.explain="Camera 错误追踪  ";
                        camera_1_8.classNameForShuxing = "  CameraService.h CameraService.cpp -》 VendorTagDescriptor ";
                        camera_1_8.printcode="   status_t CameraService::dump(int fd, const Vector<String16>& args) { VendorTagDescriptor }";
                        camera_1_8.printLogUrl="";
                        camera_1_8.expain1="";
        keyWordList.add(camera_1_8);

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