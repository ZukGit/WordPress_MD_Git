import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


// 执行命令  grep -rins "hdd_send_association_event: 1382:" .  得到原始的数据 放入输入文件中
//   能不能在 java 中执行 grep
// https://blog.csdn.net/zhaodecang/article/details/53572879
// 直接提供 目录  然后输出这个 结果
public class G0_MacAddress {


    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 读取 G0_oui.txt 文件的内容 路径
    //  网络 地址 : http://standards-oui.ieee.org/oui/oui.txt
    static String G0_oui_File = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "G0_oui.txt";

    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;
    static ArrayList<String> mKeyWordName = new ArrayList<>();


    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            curOS_TYPE = OS_TYPE.Windows;
        } else if (osName.contains("linux")) {
            curOS_TYPE = OS_TYPE.Linux;
        } else if (osName.contains("mac")) {
            curOS_TYPE = OS_TYPE.MacOS;
        }
    }


    static String curDirPath = "";   // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径

    static void showTip() {
        System.out.println(" 对mac地址前三位 进行统计 输出对应产商信息的工具 ！ ");
    }

    static ArrayList<MacInfo> curMacInfoList = new ArrayList<MacInfo>();


    static ArrayList<File> getAllSubFile(File dirFile) {
        return getAllSubFile(dirFile.getAbsolutePath(), "", "");
    }

    static ArrayList<File> getAllSubFile(String rootPath, String aospItemPath, String type) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath + File.separator + aospItemPath);

        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();
                    //System.out.println("pathString = " + fileString);
//                    if (fileString.endsWith(type)) {
                    allFile.add(new File(fileString));
//                         System.out.println("file found at path: " + file.toAbsolutePath());
//                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allFile;


    }

    //  grep -----------begin
    static Charset charset = Charset.forName("utf-8");
    static CharsetDecoder decoder = charset.newDecoder();
    static Pattern linePattern = Pattern.compile(".*\r?\n");
    static Pattern pattern;

    static ArrayList<String> grep(File f, String searchContent) {
        //取得 FileChannel
        ArrayList<String> matchStrList = null;
        compile(searchContent);
        try {
            FileInputStream fis = new FileInputStream(f);
            FileChannel fc = fis.getChannel();
            // Get the file's size and then map it into memory
            int size = (int) fc.size();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, size);
            //把 字节buffer decode 成 charBuffer
            CharBuffer cb = decoder.decode(bb);
            matchStrList = grep(f, cb, searchContent);
            fc.close();
        } catch (Exception e) {
            //   System.out.println(e.fillInStackTrace());
            //  java.nio.charset.MalformedInputException: Input length = 1
        }
        return matchStrList;
    }

    private static ArrayList<String> grep(File f, CharBuffer cb, String searchContent) {
        ArrayList<String> matchStrList = new ArrayList<String>();
        Matcher lm = linePattern.matcher(cb); //line matcher
        Matcher pm = null; //pattern matcher
        int lines = 0;
        while (lm.find()) {
            lines++;
            CharSequence curLineCs = lm.group();//the current line
            if (pm == null) {
                pm = pattern.matcher(curLineCs);
            } else {
                pm.reset(curLineCs);
            }

            // D:【1】\jira_work\40800\N2PV210043_308193301_USER@2019-12-04_18_46_39_-0500_IKUT-1371816\1.txt→59→./wlan_logs/host_driver_logs_002.txt:【2】42894 :【3】12-04 17:36:20.338631  [scheduler_threa][0x2cacad28c4][17:36:20.329616]wlan: [26175:I:HDD] hdd_send_association_event: 1382: wlan0(vdevid-0): 6e:2a:87:6c:6f:0d connected to 7c:d9:5c:04:54:c9
            // ./aplogcat-kernel.txt:【1】31918:【2】12-04 17:36:20.190


//         .\wlan_logs\host_driver_logs_current.txt→88761→12-04 18:54:54.549873  [scheduler_threa][0xab3c6f450][18:54:54.542279]wlan: [792:I:HDD]
//            hdd_send_association_event: 1382: wlan0(vdevid-0): 6e:2a:87:6c:6f:0d connected to 7c:d9:5c:04:54:c9

            // ./aplogcat-kernel.txt:【1】31918:【2】12-04 17:36:20.190

            if (pm.find()) {
                String absPath = f.getAbsolutePath();
                absPath = absPath.replace(curDirPath, ".");
//                System.out.println(absPath + ":" + lines + ":" + curLineCs);
                matchStrList.add(absPath + ":" + lines + ":" + curLineCs);
            }
            if (lm.end() == cb.limit()) { //文件最后
                break;
            }
        }

        return matchStrList;
    }


    private static void compile(String searchContent) {
        try {
            pattern = Pattern.compile(searchContent);
        } catch (PatternSyntaxException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    //  grep -----------end


    public static void main(String[] args) {
        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {
                    curDirPath = args[i];
                } else {
                    mKeyWordName.add(args[i]);
                }
            }
        }


        MacAddress.showMac48_64(MacAddress.fromString("33:33:ff:03:63:bc"));
        MacAddress.showMac48_64(MacAddress.fromString("72:62:a5:76:fe:86"));


        MacAddress.showMac48_64(MacAddress.fromString("00:60:08:52:f9:d8"));


        if (mKeyWordName.size() == 0) {  // 如果输入除了 当前根目录外 没有其他的输入信息 那么 那么就分析当前目录
            System.out.println("未输入文件参数  当前需要分析的连接厂商信息的目录 :" + curDirPath);
            mKeyWordName.add(curDirPath);
            //    showTip();
            //  return;
        }


        File MacListFile = new File(G0_oui_File);
        if (!MacListFile.exists()) {
            System.out.println("Mac地址产商文件不存在. 路径:" + G0_oui_File);
            System.out.println("请到  http://standards-oui.ieee.org/oui/oui.txt   下载并保存为 " + G0_oui_File);
            return;
        }


        initMacInfoList(MacListFile);

        System.out.println("MacInfoList 大小: " + curMacInfoList.size());

        if (curMacInfoList.size() == 0) {
            System.out.println("Mac地址产商文件存在.但无法读取到内容  路径:" + G0_oui_File);
            System.out.println("请到  http://standards-oui.ieee.org/oui/oui.txt   下载并保存为 " + G0_oui_File);
            return;
        }

//        for (int i = 0; i < curMacInfoList.size(); i++) {
//            MacInfo macItem = curMacInfoList.get(i);
//            System.out.println(macItem);
//        }


        for (int i = 0; i < mKeyWordName.size(); i++) {
            String inputPath = mKeyWordName.get(i);
            String inputABS_Path = "";
            if (inputPath.contains(".")) {
                inputABS_Path = curDirPath + File.separator + inputPath;
            } else {
                if (!inputPath.contains(curDirPath)) {
                    inputABS_Path = curDirPath + File.separator + inputPath;
                } else {
                    inputABS_Path = inputPath;
                }
            }
            File inputFile = new File(inputABS_Path);
            if (!inputFile.exists()) {
//                System.out.println("当前输入文件不存在 : " + inputABS_Path);
                // 检测是否是输入的 产商的前缀
                String inputParams = inputPath.replace(":","");
                inputParams = inputParams.replace("-","");
                inputParams = inputParams.toUpperCase();
                //ABCDED 123456
                if(inputParams.length() <= 6){
                    tryFlutterVendorProdix(inputParams);

                }else {
                    inputParams = inputParams.substring(0,6);
                    tryFlutterVendorProdix(inputParams);
//                    System.out.println("inputParams = "+ inputParams);

                }

                continue;
            }

            // 只有 没有输入别的参数 时  才对 文件进行 全匹配
            if (inputFile.isDirectory() && mKeyWordName.size() ==1) {
                //  如果输入 的是一个目录  那么 搜搜 这个目录  搜索的命令是  grep -rins "hdd_send_association_event: 1382:" .
                ArrayList<File> curAllSubFIle = getAllSubFile(inputFile);    //  获取当前目录下所有的文件
                //  compile("hdd_send_association_event: 1382:");
                for (int j = 0; j < curAllSubFIle.size(); j++) {
                    File curFile = curAllSubFIle.get(j);
                    ArrayList<String> matchStrList = grep(curFile, "hdd_send_association_event: 1382:");
                    if (matchStrList == null || matchStrList.size() == 0) {
                        // System.out.println("当前搜索文件:"+ curFile.getAbsolutePath()+" 命令 hdd_send_association_event: 1382:  为null 或 大小为 0 !  ");
                        continue;
                    }
                    fluterArrayList(matchStrList);
                }

            }

            if (!inputFile.isDirectory()) {
                //  如果当前是文件 那么  就操作这个文件
                addVendorInfo2InputFile(inputFile);   // 对输入文件进行逻辑处理
            }


        }
    }


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    static void   tryFlutterVendorProdix (String preVendor){

        int matchNumIndex = 0;
        for (int i = 0; i < curMacInfoList.size(); i++) {
            MacInfo macItem = curMacInfoList.get(i);
            if(macItem.mHex_VendorMacAddr_Pure.toUpperCase().startsWith(preVendor.toUpperCase())){
                System.out.println("[ 匹配索引 : "+matchNumIndex+"] [ 前缀 : " + macItem.mHex_VendorMacAddr_Pure+" ] [ 产商信息 : "+ macItem.mVendorInfo+" ]" );
                matchNumIndex++;
            }
        }

        if(matchNumIndex == 0){
            System.out.println("无法检索到与当前输入参数: " + preVendor + " 匹配的 Vendor 信息!");
        }


    }


    static void fluterArrayList(ArrayList<String> grepList) {
        ArrayList<String> showList = new ArrayList<String>();
        for (int i = 0; i < grepList.size(); i++) {
            String lineContent = grepList.get(i).trim();

            // lineContent   包含汉字
            if (isContainChinese(lineContent)) {
                continue;   //  如果包含汉字  就不记录该项
            }
            if (!lineContent.contains(" connected to ")) {  // 如果不包含 (hex)  那么  去读取下一行
                showList.add(lineContent);
                continue;
            }

            // 拿到 9c:1c:12:98:44:b1
            String macStr = lineContent.substring(lineContent.indexOf(" connected to ") + " connected to ".length()).trim();
            String macStrOrigin = macStr;
            String macPre = macStr.substring(0, 8);
            macStr = macStr.replace(":", "");
            macStr = macStr.replace("-", "");
            macStr = macStr.toUpperCase();  // 9C1C12  9844B1
            String cVendorMacAddrStr = macStr.substring(0, 6);
//                System.out.println("macStr 1 = "+ macStr);
//                System.out.println("cVendorMacAddrStr 1 = "+ cVendorMacAddrStr);
            MacInfo matchMacInfo = checkVendorInfoWithMac(cVendorMacAddrStr);
            String fixedStr = "";
            if (matchMacInfo != null) {
                fixedStr = " 【 Vendor-Info: [" + matchMacInfo.mVendorInfo + "] Vendor-Prefix: " + matchMacInfo.mHex_VendorMacAddr_dot_lower + " 】";
            } else {
                fixedStr = " 【 For [ " + macPre + " ] No Find Vendor Info 】";
            }


            // ./aplogcat-kernel.txt:266094:12-02 18:27:41.666     0     0 I [scheduler_threa][0x8b0f258e5e][18:27:41.666107] wlan: [20091:I:HDD] hdd_send_association_event: 1382: wlan0(vdevid-0): 6e:8b:5e:1c:fd:e8 connected to f8:18:97:7f:1f:2a
            // 转为
            //  11-30 12:22:07 96:88:d0:82:87:8b connected to 4c:12:65:54:44:aa

            String temp1 = lineContent.substring(lineContent.indexOf(":") + 1);
            String temp2 = temp1.substring(temp1.indexOf(":") + 1);

            String timeStrt = temp2.substring(0, 18);

            // 对  timeSret 进行过滤
            // timeStrt = ./wlan_logs/host_d
            // 12-03 18:13:27
            String timeValid = timeStrt.replace(" ", "");
            timeValid = timeValid.replace(":", "");
            timeValid = timeValid.replace(".", "");
            timeValid = timeValid.replace("-", "");
//            System.out.println(" isNumeric(timeValid)"+isNumeric(timeValid)+"   timeValid = "+ timeValid);
            if (!isNumeric(timeValid)) {
                //  如果 没有得到 时间戳  那么 过滤这个选项

                continue;
            }


            String selfmacTemp1 = lineContent.substring(0, lineContent.indexOf(" connected to ")).trim();
            String selfMac = selfmacTemp1.substring(selfmacTemp1.length() - 18).trim();

            String newContent = timeStrt + " " + selfMac + " connected to " + macStrOrigin;


            showList.add(newContent + fixedStr);

//            .\分析.asm:282:./wlan_logs/host_driver_logs_current.txt:43320:12-04 23:05:59.034100  [scheduler_threa][0x71d480766c][23:05
//:59.015722]wlan: [12706:I:HDD] hdd_send_association_event: 1382: wlan0(vdevid-0): c6:14:84:ab:4f:98 connected to 50:c7:bf:9f:10:a2

            //  打印LOG 时  开启
//            System.out.println("————————————————————————————————————");
//            System.out.println("lineContent = "+ lineContent);
//            System.out.println("timeStrt = "+ timeStrt);
//            System.out.println("REAL: "+  newContent + fixedStr );
//            System.out.println("temp2 = "+ temp2);
//            System.out.println("————————————————————————————————————");
        }

        // ./aplogca 6e:2a:87:6c:6f:0d connected to 7c:d9:5c:04:54:c9 【 Vendor-Info: [Google, Inc.] Vendor-Prefix: 7c:d9:5c 】
        for (int i = 0; i < showList.size(); i++) {
            System.out.println(showList.get(i));
        }


    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    static void addVendorInfo2InputFile(File InputFIle) {
        ArrayList<String> fixedStrList = new ArrayList<String>();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(InputFIle), "utf-8"));
            String lineContent = "";

            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null) {
                    continue;
                }

                if (!lineContent.contains(" connected to ")) {  // 如果不包含 (hex)  那么  去读取下一行
                    fixedStrList.add(lineContent);
                    continue;
                }


                // 拿到 9c:1c:12:98:44:b1
                String macStr = lineContent.substring(lineContent.indexOf(" connected to ") + " connected to ".length()).trim();
                String macStrOrigin = macStr;
                String macPre = macStr.substring(0, 8);
                macStr = macStr.replace(":", "");
                macStr = macStr.replace("-", "");
                macStr = macStr.toUpperCase();  // 9C1C12  9844B1
                String cVendorMacAddrStr = macStr.substring(0, 6);
//                System.out.println("macStr 1 = "+ macStr);
//                System.out.println("cVendorMacAddrStr 1 = "+ cVendorMacAddrStr);
                MacInfo matchMacInfo = checkVendorInfoWithMac(cVendorMacAddrStr);
                String fixedStr = "";
                if (matchMacInfo != null) {
                    fixedStr = " 【 Vendor-Info: [" + matchMacInfo.mVendorInfo + "] Vendor-Prefix: " + matchMacInfo.mHex_VendorMacAddr_dot_lower + " 】";
                } else {
                    fixedStr = " 【 For [ " + macPre + " ] No Find Vendor Info 】";
                }


                // ./aplogcat-kernel.txt:266094:12-02 18:27:41.666     0     0 I [scheduler_threa][0x8b0f258e5e][18:27:41.666107] wlan: [20091:I:HDD] hdd_send_association_event: 1382: wlan0(vdevid-0): 6e:8b:5e:1c:fd:e8 connected to f8:18:97:7f:1f:2a
                // 转为
                //  11-30 12:22:07 96:88:d0:82:87:8b connected to 4c:12:65:54:44:aa

                String temp1 = lineContent.substring(lineContent.indexOf(":") + 1);
                String temp2 = temp1.substring(temp1.indexOf(":") + 1);
                String timeStrt = temp2.substring(0, 9);
                String selfmacTemp1 = lineContent.substring(0, lineContent.indexOf(" connected to ")).trim();
                String selfMac = selfmacTemp1.substring(selfmacTemp1.length() - 18).trim();

                String newContent = timeStrt + " " + selfMac + " connected to " + macStrOrigin;


//               fixedStrList.add(lineContent + fixedStr);
                fixedStrList.add(newContent + fixedStr);

            }
            curBR.close();
        } catch (Exception e) {
            System.out.println(" 添加 VendorInfo 信息异常  读取 异常:" + e.fillInStackTrace());
            e.printStackTrace();
        }


        // 写入  Input文件

        for (int i = 0; i < fixedStrList.size(); i++) {
            System.out.println(fixedStrList.get(i));
        }


        writeContentToFile(InputFIle, fixedStrList);

    }


    static void writeContentToFile(File file, ArrayList<String> strList) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            sb.append(strList.get(i) + "\n");
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
                System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static void initMacInfoList(File ouiFile) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(ouiFile)));
            String lineContent = "";

            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }

                if (!lineContent.contains("(hex)")) {  // 如果不包含 (hex)  那么  去读取下一行
                    continue;
                }

                // FC-A6-67   (hex)		Amazon Technologies Inc.
                String pre = lineContent.substring(0, lineContent.indexOf("(")).trim();
                String end = lineContent.substring(lineContent.indexOf(")") + 1).trim();
                MacInfo macItem = new MacInfo(pre, end);

                curMacInfoList.add(macItem);
            }
            curBR.close();
        } catch (Exception e) {
            System.out.println(" AndroidVersion 读取 异常:" + e.fillInStackTrace());
            e.printStackTrace();
        }

    }


    static MacInfo checkVendorInfoWithMac(String macPre) {
        MacInfo matchMacInfo = null;

        for (int i = 0; i < curMacInfoList.size(); i++) {
            MacInfo macInfoItem = curMacInfoList.get(i);
            String curMacPre = macPre.trim().toUpperCase();
            String macInfoMac = macInfoItem.mHex_VendorMacAddr_Pure.trim().toUpperCase();
            if (macInfoMac.equals(curMacPre)) {
                matchMacInfo = macInfoItem;
                return matchMacInfo;
            }
        }

        return matchMacInfo;
    }


    static class MacInfo {
        String mHex_VendorMacAddr_minus;  // F4-BD-9E
        String mHex_VendorMacAddr_dot;   //  F4:BD:9E
        String mHex_VendorMacAddr_dot_lower;   //  F4:BD:9E
        String mHex_VendorMacAddr_Pure;  //  F4BD9E
        String mVendorInfo;


        MacInfo(String cHex_VendorMacAddr_minus, String vendorInfo) {
            this.mHex_VendorMacAddr_minus = cHex_VendorMacAddr_minus.trim();
            this.mVendorInfo = vendorInfo.trim();
            this.mHex_VendorMacAddr_dot = mHex_VendorMacAddr_minus.replace("-", ":");
            this.mHex_VendorMacAddr_Pure = mHex_VendorMacAddr_minus.replace("-", "").toUpperCase();
            this.mHex_VendorMacAddr_dot_lower = mHex_VendorMacAddr_dot.toLowerCase();
        }

        @Override
        public String toString() {
            String str = "产商Mac地址前三位:" + mHex_VendorMacAddr_Pure + "    产商名称: " + mVendorInfo;
            return str;
        }
    }






    // 执行命令  grep -rins "hdd_send_association_event: 1382:" .  得到原始的数据 放入输入文件中
//   能不能在 java 中执行 grep
// https://blog.csdn.net/zhaodecang/article/details/53572879
// 直接提供 目录  然后输出这个 结果
    static   class MacAddress {


        public static final int ETHER_ADDR_LEN = 6;
        public static final byte[] ETHER_ADDR_BROADCAST = addr(0xff, 0xff, 0xff, 0xff, 0xff, 0xff);
        public static final MacAddress BROADCAST_ADDRESS = MacAddress.fromBytes(ETHER_ADDR_BROADCAST);
        public static final long VALID_LONG_MASK = (1L << 48) - 1;

        public static MacAddress BASE_GOOGLE_MAC = MacAddress.fromString("da:a1:19:0:0:0");


        /** @hide Indicates a MAC address of unknown type. */
        public static final int TYPE_UNKNOWN = 0;
        /** Indicates a MAC address is a unicast address. */
        public static final int TYPE_UNICAST = 1;
        /** Indicates a MAC address is a multicast address. */
        public static final int TYPE_MULTICAST = 2;
        /** Indicates a MAC address is the broadcast address. */
        public static final int TYPE_BROADCAST = 3;
        private static final long LOCALLY_ASSIGNED_MASK = MacAddress.fromString("2:0:0:0:0:0").mAddr;
        private static final long MULTICAST_MASK = MacAddress.fromString("1:0:0:0:0:0").mAddr;
        private static final long OUI_MASK = MacAddress.fromString("ff:ff:ff:0:0:0").mAddr;
        private static final long NIC_MASK = MacAddress.fromString("0:0:0:ff:ff:ff").mAddr;

        // Internal representation of the MAC address as a single 8 byte long.
        // The encoding scheme sets the two most significant bytes to 0. The 6 bytes of the
        // MAC address are encoded in the 6 least significant bytes of the long, where the first
        // byte of the array is mapped to the 3rd highest logical byte of the long, the second
        // byte of the array is mapped to the 4th highest logical byte of the long, and so on.
        static long mAddr;


        static int INADDRSZ = 16;
        static int INT16SZ = 2;
        private MacAddress(long addr) {
            mAddr = (VALID_LONG_MASK & addr);
        }



        private static long longAddrFromByteAddr(byte[] addr) {
            return MacAddressUtils.longAddrFromByteAddr(addr);
        }



        public static MacAddress fromBytes(byte[] addr) {
            return new MacAddress(longAddrFromByteAddr(addr));
        }




        private static byte[] addr(int... in) {
            if (in.length != ETHER_ADDR_LEN) {
                throw new IllegalArgumentException(Arrays.toString(in) + " was not an array with length equal to " + ETHER_ADDR_LEN);
            }
            byte[] out = new byte[ETHER_ADDR_LEN];
            for (int i = 0; i < ETHER_ADDR_LEN; i++) {
                out[i] = (byte) in[i];
            }
            return out;
        }



        public static MacAddress fromString(String addr) {
            return new MacAddress(longAddrFromStringAddr(addr));
        }


        private static long longAddrFromStringAddr(String addr) {
            if(addr == null){
                return 0L;
            }
            String[] parts = addr.split(":");
            if (parts.length != ETHER_ADDR_LEN) {
                throw new IllegalArgumentException(addr + " was not a valid MAC address");
            }
            long longAddr = 0;
            for (int i = 0; i < parts.length; i++) {
                int x = Integer.valueOf(parts[i], 16);
                if (x < 0 || 0xff < x) {
                    throw new IllegalArgumentException(addr + "was not a valid MAC address");
                }
                longAddr = x + (longAddr << 8);
            }
            return longAddr;
        }

        public String toString() {
            return stringAddrFromLongAddr(mAddr);
        }





        public byte[] toByteArray() {
            return byteAddrFromLongAddr(mAddr);
        }

        public static byte[] byteAddrFromLongAddr(long addr) {
            return MacAddressUtils.byteAddrFromLongAddr(addr);
        }

        public static boolean isMacAddress(byte[] addr) {
            return MacAddressUtils.isMacAddress(addr);
        }

        public  String toOuiString() {
            return String.format(
                    "%02x:%02x:%02x", (mAddr >> 40) & 0xff, (mAddr >> 32) & 0xff, (mAddr >> 24) & 0xff);
        }


        public static  String stringAddrFromByteAddr(byte[] addr) {
            if (!isMacAddress(addr)) {
                return null;
            }
            return String.format("%02x:%02x:%02x:%02x:%02x:%02x",
                    addr[0], addr[1], addr[2], addr[3], addr[4], addr[5]);
        }

        public boolean equals(Object o) {
            return (o instanceof MacAddress) && ((MacAddress) o).mAddr == mAddr;
        }

        public boolean matches(MacAddress baseAddress, MacAddress mask) {
            return (mAddr & mask.mAddr) == (baseAddress.mAddr & mask.mAddr);
        }


        public  int getAddressType() {
            if (equals(BROADCAST_ADDRESS)) {
                return TYPE_BROADCAST;
            }
            if ((mAddr & MULTICAST_MASK) != 0) {
                return TYPE_MULTICAST;
            }
            return TYPE_UNICAST;
        }


        public  String getAddressTypeSting() {
            int type = getAddressType();
            String typeStr = "" ;
            if(type == TYPE_BROADCAST ){
                typeStr = "广播地址类型";
            } else if (type == TYPE_MULTICAST ){
                typeStr = "多播地址类型";
            }else if(type == TYPE_UNICAST){

                typeStr = "单播地址类型";
            }else{
                typeStr = "未知地址类型";
            }
            return typeStr;
        }

        public int hashCode() {
            return (int) ((mAddr >> 32) ^ mAddr);
        }



        // 创建一个随机的Mac地址
        public static MacAddress createRandomUnicastAddressWithGoogleBase() {
            return MacAddressUtils.createRandomUnicastAddress(BASE_GOOGLE_MAC, new SecureRandom());
        }


        static  String stringAddrFromLongAddr(long addr) {
            return String.format("%02x:%02x:%02x:%02x:%02x:%02x",
                    (addr >> 40) & 0xff,
                    (addr >> 32) & 0xff,
                    (addr >> 24) & 0xff,
                    (addr >> 16) & 0xff,
                    (addr >> 8) & 0xff,
                    addr & 0xff);
        }


        /**
         * Create a link-local Inet6Address from the MAC address. The EUI-48 MAC address is converted
         * to an EUI-64 MAC address per RFC 4291. The resulting EUI-64 is used to construct a link-local
         * IPv6 address per RFC 4862.
         *
         * @return A link-local Inet6Address constructed from the MAC address.
         */
        public  String getLinkLocalIpv6FromEui48Mac() {
            byte[] macEui48Bytes = toByteArray();
            byte[] addr = new byte[16];

            addr[0] = (byte) 0xfe;
            addr[1] = (byte) 0x80;
            addr[8] = (byte) (macEui48Bytes[0] ^ (byte) 0x02); // flip the link-local bit
            addr[9] = macEui48Bytes[1];
            addr[10] = macEui48Bytes[2];
            addr[11] = (byte) 0xff;
            addr[12] = (byte) 0xfe;
            addr[13] = macEui48Bytes[3];
            addr[14] = macEui48Bytes[4];
            addr[15] = macEui48Bytes[5];

            return    numericToTextFormat(addr);
        }

        public  String getSiteLocalIpv6FromEui48Mac() {
            byte[] macEui48Bytes = toByteArray();
            byte[] addr = new byte[16];

            addr[0] = (byte) 0xfe;
            addr[1] = (byte) 0xc0;
            addr[8] = (byte) (macEui48Bytes[0] ^ (byte) 0x02); // flip the link-local bit
            addr[9] = macEui48Bytes[1];
            addr[10] = macEui48Bytes[2];
            addr[11] = (byte) 0xff;
            addr[12] = (byte) 0xfe;
            addr[13] = macEui48Bytes[3];
            addr[14] = macEui48Bytes[4];
            addr[15] = macEui48Bytes[5];

            return    numericToTextFormat(addr);
        }


        static String numericToTextFormat(byte[] src) {
            StringBuilder sb = new StringBuilder(39);
            for (int i = 0; i < (INADDRSZ / INT16SZ); i++) {
                sb.append(Integer.toHexString(((src[i<<1]<<8) & 0xff00)
                        | (src[(i<<1)+1] & 0xff)));
                if (i < (INADDRSZ / INT16SZ) -1 ) {
                    sb.append(":");
                }
            }
            return sb.toString();
        }


        // EUI-64
        public static void main_X(String[] args) {


/*        for (int i = 0; i < 20 ; i++) {
            MacAddress randomMac =      createRandomUnicastAddressWithGoogleBase();
            System.out.println("随机Mac地址 [ "+i+" ] Mac: "+randomMac.toString()  + "    产商标识:"+randomMac.toOuiString() + "  地址类型:"+randomMac.getAddressTypeSting());
        }*/

/*
        IPv6单播地址有以下六种类型：
        https://blog.csdn.net/hrl7752/article/details/79710681?utm_source=blogxgwz6
        1－Aggregate Global Unicast Address   2xxx:xxxxx/3 - 3FFF: :FFFF
        2001::/16  IPV6因特网地址
        2002::/16  6to4过渡地址
        2－Link Local Address　　　           FE80::/10   (前10位以FE80开头)
                3－Site Local Address (Private)       FEC0::/10
        4－Unspecified Address　　            0:0:0:0:0:0:0:0/128 => ::/128
        5－Loopback Address 　　　            0:0:0:0:0:0:0:1/128 => ::1/128
        6－IPv4 Compatible Address            ::192.168.30.1 => ::C0A8:1E01*/
//  206.123.31.2    ce7b:1f02
//    ::192.168.30.1  => ::C0A8:1E01

            showMac48_64(MacAddress.fromString("33:33:ff:03:63:bc"));
            showMac48_64(MacAddress.fromString("72:62:a5:76:fe:86"));


            showMac48_64(MacAddress.fromString("00:60:08:52:f9:d8"));
        }


         static void showMac48_64(MacAddress mac){
            System.out.println("mac = "+ mac.toString() + " 本地IPv6：=" + mac.getLinkLocalIpv6FromEui48Mac() + "  私有地址IPv6:  "+mac.getSiteLocalIpv6FromEui48Mac());
        }


        static class MacAddressUtils {

            static final long VALID_LONG_MASK = (1L << 48) - 1;
            static final long LOCALLY_ASSIGNED_MASK = longAddrFromByteAddr(MacAddress.fromString("2:0:0:0:0:0").toByteArray());
            static final long MULTICAST_MASK = longAddrFromByteAddr(MacAddress.fromString("1:0:0:0:0:0").toByteArray());
            static final long OUI_MASK = longAddrFromByteAddr(MacAddress.fromString("ff:ff:ff:0:0:0").toByteArray());
            static final long NIC_MASK = longAddrFromByteAddr(MacAddress.fromString("0:0:0:ff:ff:ff").toByteArray());
            // Matches WifiInfo.DEFAULT_MAC_ADDRESS
            static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
            static final int ETHER_ADDR_LEN = 6;

            /**
             * @return true if this MacAddress is a multicast address.
             */
            public static boolean isMulticastAddress( MacAddress address) {
                return (longAddrFromByteAddr(address.toByteArray()) & MULTICAST_MASK) != 0;
            }

            /**
             * Returns a generated MAC address whose 46 bits, excluding the locally assigned bit and the
             * unicast bit, are randomly selected.
             * <p>
             * The locally assigned bit is always set to 1. The multicast bit is always set to 0.
             *
             * @return a random locally assigned, unicast MacAddress.
             */
            public static MacAddress createRandomUnicastAddress() {
                return createRandomUnicastAddress(null, new SecureRandom());
            }

            public static boolean isLocallyAssigned() {
                return (mAddr & LOCALLY_ASSIGNED_MASK) != 0;
            }




            public static MacAddress createRandomUnicastAddress(MacAddress base, Random r) {
                long addr;

                if (base == null) {
                    addr = r.nextLong() & VALID_LONG_MASK;
                } else {
                    addr = (longAddrFromByteAddr(base.toByteArray()) & OUI_MASK)
                            | (NIC_MASK & r.nextLong());
                }
                addr |= LOCALLY_ASSIGNED_MASK;
                addr &= ~MULTICAST_MASK;
                MacAddress mac = MacAddress.fromBytes(byteAddrFromLongAddr(addr));
                if (mac.equals(DEFAULT_MAC_ADDRESS)) {
                    return createRandomUnicastAddress(base, r);
                }
                return mac;
            }

            /**
             * Convert a byte address to long address.
             */
            public static long longAddrFromByteAddr(byte[] addr) {
                Objects.requireNonNull(addr);
                if (!isMacAddress(addr)) {
                    throw new IllegalArgumentException(
                            Arrays.toString(addr) + " was not a valid MAC address");
                }
                long longAddr = 0;
                for (byte b : addr) {
                    final int uint8Byte = b & 0xff;
                    longAddr = (longAddr << 8) + uint8Byte;
                }
                return longAddr;
            }

            /**
             * Convert a long address to byte address.
             */
            public static byte[] byteAddrFromLongAddr(long addr) {
                byte[] bytes = new byte[ETHER_ADDR_LEN];
                int index = ETHER_ADDR_LEN;
                while (index-- > 0) {
                    bytes[index] = (byte) addr;
                    addr = addr >> 8;
                }
                return bytes;
            }

            /**
             * Returns true if the given byte array is a valid MAC address.
             * A valid byte array representation for a MacAddress is a non-null array of length 6.
             *
             * @param addr a byte array.
             * @return true if the given byte array is not null and has the length of a MAC address.
             */
            public static boolean isMacAddress(byte[] addr) {
                return addr != null && addr.length == ETHER_ADDR_LEN;
            }
        }

    }

}
