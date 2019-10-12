import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;

public class E2_SystemInfo {

    static NumberFormat nf = new DecimalFormat("0.00");
    static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    static File wifiLogFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "E2_WifiDetail.txt");
    public static void main(String[] args) {
        try {
            addEnvironmentPATH(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "E2");
// System信息，从jvm获取
            property();
            System.out.println("----------------------------------");
            // 屏幕分辨率
            screen();
            System.out.println("----------------------------------");
// cpu信息
            cpu();
            System.out.println("----------------------------------");
// 内存信息
            memory();
            System.out.println("----------------------------------");
// 操作系统信息
            os();
            System.out.println("----------------------------------");
// 用户信息
            who();
            System.out.println("----------------------------------");
// 文件系统信息
            file();
            System.out.println("----------------------------------");
// 网络信息
            net();
            System.out.println("----------------------------------");
// 以太网信息
          //  ethernet();
            ethernetWireless();
            ethernetPC();
            System.out.println("----------------------------------");

            wifi();   //  wifi 连接信息
            System.out.println("----------------------------------");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }



    public static void wifi(){
        String word1 = "上的配置文件";   // 接口 WLAN 上的配置文件 debugtheworld:
        String word2 = "关键内容";    // 关键内容            : 12345678
        if(wifiLogFile.exists() && readStringFromFile(wifiLogFile).contains(word2)){
            //  System.out.println("wifiLogFile 文件存在");
            readWifiObjectFromFile(wifiLogFile);
            ArrayList<String> wifiList = transactWifiList(wifiItemList);
            ArrayPrint(wifiList,"WIFI信息");
            // System.out.println(readStringFromFile(wifiLogFile));

        }else{

        }
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

static ArrayList<WifiItem> wifiItemList = new ArrayList<WifiItem>();



    static   ArrayList<String>  transactWifiList(ArrayList<WifiItem> wifiList) {
        ArrayList<String> strArr = new    ArrayList<String>();

        for (int i = 0; i < wifiList.size(); i++) {
            WifiItem item = wifiList.get(i);
            int index= i + 1;
            String str0 = "WIFI索引:"+index;
            String str1 = "WIFI名称:"+item.getName();
            String str2 = "WIFI密码:"+item.getKey();
            String str3 = "==================";
            strArr.add(str0);
            strArr.add(str1);
            strArr.add(str2);
            strArr.add(str3);
        }



        return strArr;

    }

    static synchronized void readWifiObjectFromFile(File fileItem) {
        StringBuilder sb = new StringBuilder();
        String word1 = "上的配置文件";   // 接口 WLAN 上的配置文件 debugtheworld:
        String word2 = "关键内容";    // 关键内容            : 12345678

        try {
            //   BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
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
                lineContent = lineContent.trim();



                if(lineContent.contains(word1)){
                    wifiName = lineContent.substring(lineContent.indexOf(word1)+word1.length(),lineContent.length()-1).trim();
                    wifiNameReadyFlag = true;
                    wifiKeyReadyFlag = false;
                    wifiKey="";
                }

                if(lineContent.contains(word2)){
                    wifiKey= lineContent.substring(lineContent.indexOf(word2)+word2.length()).trim();
                    wifiKey = wifiKey.substring(1).trim();
                    if(wifiNameReadyFlag){
                        wifiKeyReadyFlag = true;
                    }
                    if(wifiNameReadyFlag && wifiNameReadyFlag){
                        WifiItem  wifiItem = new WifiItem(wifiName , wifiKey);
                        wifiItemList.add(wifiItem);
                        wifiNameReadyFlag = false;
                        wifiKeyReadyFlag = false;
                    }



                }



            }
            curBR.close();
        } catch (Exception e) {
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
        addr = InetAddress.getLocalHost();  // 【InetAddress.getLocalHost().getHostAddress()】
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();  // System.getenv().get("USERNAME")
        ArrayList<String> propertyList = new ArrayList<String>();
        String userName = map.get("USERNAME");// 获取用户名
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        String userDomain = map.get("USERDOMAIN");// 获取计算机域名
        propertyList.add("用户名:"+"【System.getenv().get(\"USERNAME\")"+"】" + userName);
        propertyList.add("计算机名:【System.getenv().get(\"COMPUTERNAME\")】" + computerName);
        propertyList.add("计算机域名:【System.getenv().get(\"USERDOMAIN\")\"】" + userDomain);
        propertyList.add("本地ip地址:" +"【InetAddress.getLocalHost().getHostAddress()】 "+ ip);
        propertyList.add("本地主机名:" +"【InetAddress.getLocalHost().getHostName()】 "+  addr.getHostName());
        propertyList.add("JVM可以使用的总内存:" +"【Runtime.getRuntime().totalMemory()】 "+ r.totalMemory());  // 【Runtime.getRuntime().totalMemory()】
        propertyList.add("JVM可以使用的剩余内存:" +"【Runtime.getRuntime().freeMemory()】 "+ r.freeMemory());
        propertyList.add("JVM可以使用的处理器个数:"  +"【Runtime.getRuntime().availableProcessors()】 "+ r.availableProcessors());
        propertyList.add("Java的运行环境版本:" + "【java.version】"+ props.getProperty("java.version"));
        propertyList.add("Java的运行环境供应商:" + "【java.vendor】"+props.getProperty("java.vendor"));
        propertyList.add("Java供应商的URL:" +"【java.vendor.url】"+ props.getProperty("java.vendor.url"));
        propertyList.add("Java的安装路径:" +"【java.home】"+ props.getProperty("java.home"));
        propertyList.add("Java的虚拟机规范版本:" +"【java.vm.specification.version】"+  props.getProperty("java.vm.specification.version"));
        propertyList.add("Java的虚拟机规范供应商:"  +"【java.vm.specification.vendor】"+  props.getProperty("java.vm.specification.vendor"));
        propertyList.add("Java的虚拟机规范名称:" +"【java.vm.specification.name】"+  props.getProperty("java.vm.specification.name"));
        propertyList.add("Java的虚拟机实现版本:" +"【java.vm.version】"+ props.getProperty("java.vm.version"));
        propertyList.add("Java的虚拟机实现供应商:" +"【java.vm.vendor】"+ props.getProperty("java.vm.vendor"));
        propertyList.add("Java的虚拟机实现名称:" + "【java.vm.name】"+  props.getProperty("java.vm.name"));
        propertyList.add("Java运行时环境规范版本:" + "【java.specification.version】"+  props.getProperty("java.specification.version"));
        propertyList.add("Java运行时环境规范供应商:" + "【java.specification.vender】"+  props.getProperty("java.specification.vender"));
        propertyList.add("Java运行时环境规范名称:" + "【java.specification.name】"+  props.getProperty("java.specification.name"));
        propertyList.add("Java的类格式版本号:" + "【java.class.version】"+  props.getProperty("java.class.version"));
        propertyList.add("Java的类路径:" + "【java.class.path】"+  props.getProperty("java.class.path"));
        propertyList.add("加载库时搜索的路径列表:" + "【java.library.path】"+ props.getProperty("java.library.path"));
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

    private static void memory()  {
        Sigar sigar = new Sigar();
        Mem mem = null;
        try {
            mem = sigar.getMem();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        ArrayList<String> MemLogList = new ArrayList<String>();
        // System.out.println("内存总量:  " + mem.getTotal() / (1024L*1024L*1024L) + "GB "); // 内存总量
//        System.out.println("内存总量:  " + Double.parseDouble(nf.format((Double)(mem.getTotal() / (1024d*1024d*1024d))))    + "GB"); // 当前内存使用量
//        System.out.println("当前内存使用量:  " + Double.parseDouble(nf.format((Double)(mem.getUsed() / (1024d*1024d*1024d))))    + "GB"); // 当前内存使用量
//        System.out.println("当前内存剩余量:  " + Double.parseDouble(nf.format((Double)(mem.getFree() / (1024d*1024d*1024d))))    + "GB"); // 当前内存使用量

        MemLogList.add("内存总量:" + Double.parseDouble(nf.format((Double) (mem.getTotal() / (1024d * 1024d * 1024d)))) + "GB"); // 当前内存使用量
        MemLogList.add("当前内存使用量:" + Double.parseDouble(nf.format((Double) (mem.getUsed() / (1024d * 1024d * 1024d)))) + "GB"); // 当前内存使用量
        MemLogList.add("当前内存剩余量:" + Double.parseDouble(nf.format((Double) (mem.getFree() / (1024d * 1024d * 1024d)))) + "GB"); // 当前内存使用量


        Swap swap = null;
        try {
            swap = sigar.getSwap();
        } catch (SigarException e) {
            e.printStackTrace();
            return;
        }
//        System.out.println("交换区总量:  " + swap.getTotal() / (1024L*1024L*1024L) + "GB "); // 交换区总量
//        System.out.println("当前交换区使用量:  " + swap.getUsed() / (1024L*1024L*1024L) + "GB"); // 当前交换区使用量
//        System.out.println("当前交换区剩余量:  " + swap.getFree() / (1024L*1024L*1024L) + "GB"); // 当前交换区剩余量

//        System.out.println("交换区总量:  " + Double.parseDouble(nf.format((Double)(swap.getTotal() / (1024d*1024d*1024d))))    + "GB"); // 当前内存使用量
//        System.out.println("当前交换区使用量:  " + Double.parseDouble(nf.format((Double)(swap.getUsed() / (1024d*1024d*1024d))))    + "GB"); // 当前内存使用量
//        System.out.println("当前交换区剩余量:  " + Double.parseDouble(nf.format((Double)(swap.getFree() / (1024d*1024d*1024d))))    + "GB"); // 当前内存使用量

        MemLogList.add("交换区总量:" + Double.parseDouble(nf.format((Double) (swap.getTotal() / (1024d * 1024d * 1024d)))) + "GB"); // 当前内存使用量
        MemLogList.add("当前交换区使用量:" + Double.parseDouble(nf.format((Double) (swap.getUsed() / (1024d * 1024d * 1024d)))) + "GB"); // 当前内存使用量
        MemLogList.add("当前交换区剩余量:" + Double.parseDouble(nf.format((Double) (swap.getFree() / (1024d * 1024d * 1024d)))) + "GB"); // 当前内存使用量

        ArrayPrint(MemLogList, "运存情况");

    }


    static int getZScreenHeight() {
        // CMD 和 IDE下 宽高一致  1920x1080
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int height = gd.getDisplayMode().getHeight();
        return height;
/*           在IDE下 分辨率为 1080x720  在 CMD下确是  1920x1080  不一致
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int) screenSize.height;
        if (System.getProperties().getProperty("sun.stderr.encoding") != null &&
                System.getProperties().getProperty("sun.stdout.encoding") != null &&
                "ms936".equals(System.getProperties().getProperty("sun.stderr.encoding")) &&
                "ms936".equals(System.getProperties().getProperty("sun.stdout.encoding"))) {
            screenHeight = (int) (screenHeight * 1.5);
        }
        return screenHeight;*/


    }


    static int getZScreenWeight() {

        // CMD 和 IDE下 宽高一致  1920x1080
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        return width;

/*     在IDE下 分辨率为 1080x720  在 CMD下确是  1920x1080  不一致
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.width;
        if(System.getProperties().getProperty("sun.stderr.encoding") != null &&
                System.getProperties().getProperty("sun.stdout.encoding") != null &&
                "ms936".equals(System.getProperties().getProperty("sun.stderr.encoding")) &&
                "ms936".equals(System.getProperties().getProperty("sun.stdout.encoding"))   ){
            screenWidth = (int)(screenWidth * 1.5);

        }
        return  screenWidth;
        */


    }


    private static void screen() {
        ArrayList<String> screenLogList = new ArrayList<String>();
        int width = getZScreenWeight();
        int high =getZScreenHeight();
        int width2x2 = width / 2;
        int high2x2 = high / 2;
        int width3x3 = width / 3;
        int high3x3 = high / 3;
        int width4x2 = width / 4;
        int high4x2 = high / 2;
        int width4x3 = width / 4;
        int high4x3 = high / 3;
        int width4x4 = width / 4;
        int high4x4 = high / 4;

        int width5x3 = width / 5;
        int high5x3 = high / 3;
        int width5x4 = width / 5;
        int high5x4 = high / 4;

        int width5x5 = width / 5;
        int high5x5 = high / 5;
/*        System.out.println("屏幕分辨率: "+ screen.width+"x"+screen.height);
        System.out.println("屏幕宽:"+ screen.width);
        System.out.println("屏幕高:"+screen.height);
        System.out.println("屏幕2x2宽高信息:  宽:"+ width2x2 + "     高:"+ high2x2);
        System.out.println("屏幕3x3宽高信息:  宽:"+ width3x3 + "     高:"+ high3x3);
        System.out.println("屏幕4x2宽高信息:  宽:"+ width4x2 + "     高:"+ high4x2);
        System.out.println("屏幕4x3宽高信息:  宽:"+ width4x3 + "     高:"+ high4x3);
        System.out.println("屏幕5x3宽高信息:  宽:"+ width5x3 + "     高:"+ high5x3);*/

        screenLogList.add("屏幕分辨率:" + width + "x" + high);
        screenLogList.add("屏幕宽:" + width);
        screenLogList.add("屏幕高:" + high);
        screenLogList.add("屏幕2x2宽高信息:  宽:" + width2x2 + "     高:" + high2x2);
        screenLogList.add("屏幕3x3宽高信息:  宽:" + width3x3 + "     高:" + high3x3);
        screenLogList.add("屏幕4x2宽高信息:  宽:" + width4x2 + "     高:" + high4x2);
        screenLogList.add("屏幕4x3宽高信息:  宽:" + width4x3 + "     高:" + high4x3);
        screenLogList.add("屏幕4x4宽高信息:  宽:" + width4x4 + "     高:" + high4x4);
        screenLogList.add("屏幕5x3宽高信息:  宽:" + width5x3 + "     高:" + high5x3);
        screenLogList.add("屏幕5x4宽高信息:  宽:" + width5x4 + "     高:" + high5x4);
        screenLogList.add("屏幕5x5宽高信息:  宽:" + width5x5 + "     高:" + high5x5);
        ArrayPrint(screenLogList, "屏幕尺寸");
    }

    private static void cpu()  {
        Sigar sigar = new Sigar();
        ArrayList<String> cpuLogList = new ArrayList<String>();
        CpuInfo infos[] = new CpuInfo[0];
        try {
            infos = sigar.getCpuInfoList();
        } catch (SigarException e) {
            e.printStackTrace();
            return;
        }
        CpuPerc cpuList[] = null;
        try {
            cpuList = sigar.getCpuPercList();
        } catch (SigarException e) {
            e.printStackTrace();
            return;
        }
        cpuLogList.add("CPU数量:" + infos.length);
        cpuLogList.add("CPU频率:" + infos[0].getMhz() + "MHz");
        cpuLogList.add("CPU产商:" + infos[0].getVendor());
        cpuLogList.add("CPU类别:" + infos[0].getModel());
        cpuLogList.add("CPU缓存数量:" + infos[0].getCacheSize());
/*        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
            CpuInfo info = infos[i];
            System.out.println("第" + (i + 1) + "块CPU信息");
            System.out.println("CPU的总量MHz:  " + info.getMhz());// CPU的总量MHz
            System.out.println("CPU生产商:  " + info.getVendor());// 获得CPU的卖主，如:Intel
            System.out.println("CPU类别:  " + info.getModel());// 获得CPU的类别，如:Celeron
            System.out.println("CPU缓存数量:  " + info.getCacheSize());// 缓冲存储器数量
            printCpuPerc(cpuList[i]);
        }*/
        ArrayPrint(cpuLogList, "CPU信息");
    }

    private static void printCpuPerc(CpuPerc cpu) {
        System.out.println("CPU用户使用率:  " + CpuPerc.format(cpu.getUser()));// 用户使用率
        System.out.println("CPU系统使用率:  " + CpuPerc.format(cpu.getSys()));// 系统使用率
        System.out.println("CPU当前等待率:  " + CpuPerc.format(cpu.getWait()));// 当前等待率
        System.out.println("CPU当前错误率:  " + CpuPerc.format(cpu.getNice()));//
        System.out.println("CPU当前空闲率:  " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
        System.out.println("CPU总的使用率:  " + CpuPerc.format(cpu.getCombined()));// 总的使用率
    }

    private static void os() {
        OperatingSystem OS = OperatingSystem.getInstance();
        ArrayList<String> OSLogList = new ArrayList<String>();

/*System.out.println("操作系统名称:  " + OS.getName()); // 操作系统类型
System.out.println("操作系统内核类型:  " + OS.getArch()); // 操作系统内核类型如: 386、486、586等x86
System.out.println("操作系统CpuEndian():  " + OS.getCpuEndian());//
System.out.println("操作系统DataModel():  " + OS.getDataModel());//
System.out.println("操作系统的描述:  " + OS.getDescription()); // 系统描述
System.out.println("操作系统最新补丁:  " + OS.getPatchLevel());//
System.out.println("操作系统的卖主:  " + OS.getVendor()); // 操作系统的卖主
System.out.println("操作系统的卖主名:  " + OS.getVendorCodeName()); // 卖主名称
System.out.println("操作系统名称:  " + OS.getVendorName()); // 操作系统名称
System.out.println("操作系统卖主类型:  " + OS.getVendorVersion()); // 操作系统卖主类型
System.out.println("操作系统的版本号:  " + OS.getVersion());// 操作系统的版本号*/


        // OSLogList.add("操作系统名称:  " + OS.getName()); // 操作系统类型   Win32    不对
        OSLogList.add("操作系统的产商:  " + OS.getVendor()); // 操作系统的卖主
        OSLogList.add("操作系统的版本号:  " + OS.getVersion());// 操作系统的版本号
        OSLogList.add("操作系统内核类型:  " + OS.getArch()); // 操作系统内核类型如: 386、486、586等x86
        OSLogList.add("操作系统CpuEndian字节大小端:  " + OS.getCpuEndian());//
        OSLogList.add("操作系统位数:  " + OS.getDataModel());//
        OSLogList.add("操作系统的描述:  " + OS.getDescription()); // 系统描述
        OSLogList.add("操作系统最新补丁:  " + OS.getPatchLevel());//
        // OSLogList.add("操作系统的卖主名:  " + OS.getVendorCodeName()); // 卖主名称
        OSLogList.add("操作系统名称:  " + OS.getVendorName()); // 操作系统名称
        // OSLogList.add("操作系统卖主类型:  " + OS.getVendorVersion()); // 操作系统卖主类型

        ArrayPrint(OSLogList, "操作系统");
    }

    private static void who()  {
        Sigar sigar = new Sigar();
        ArrayList<String> WhoLogList = new ArrayList<String>();


        Who who[] = new Who[0];
        try {
            who = sigar.getWhoList();
        } catch (SigarException e) {
            e.printStackTrace();
            return;
        }
        if (who != null && who.length > 0) {
            for (int i = 0; i < who.length; i++) {
                //System.out.println("当前系统进程表中的用户名" + String.valueOf(i));
                Who _who = who[i];
//System.out.println("设备host用户:  " + _who.getHost());
//System.out.println("用户控制台:  " + _who.getDevice());
//System.out.println("用户系统时间:  " + _who.getTime());
//System.out.println("当前系统进程表中的用户名:  " + _who.getUser()); // 当前系统进程表中的用户名

                WhoLogList.add("设备host用户:  " + _who.getHost());
                WhoLogList.add("用户控制台:  " + _who.getDevice());
// WhoLogList.add("用户系统时间:  " + _who.getTime() +"     "+df.format(new Date(_who.getTime())));
                WhoLogList.add("当前系统进程表中的用户名:  " + _who.getUser()); // 当前系统进程表中的用户名
                WhoLogList.add(":============");
            }
        }
        ArrayPrint(WhoLogList, "Who登陆情况");

    }

    private static void file()  {
        Sigar sigar = new Sigar();
        ArrayList<String> FileLogList = new ArrayList<String>();
        FileSystem fslist[] = new FileSystem[0];
        try {
            fslist = sigar.getFileSystemList();
        } catch (SigarException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < fslist.length; i++) {
            FileSystem fs = fslist[i];
/*            System.out.println("盘符名称:  " + fs.getDevName()); // 分区的盘符名称
            System.out.println("盘符路径:  " + fs.getDirName()); // 分区的盘符路径
            System.out.println("盘符索引" + i);   // 0
            System.out.println("盘符标志:  " + fs.getFlags());// 盘符标志 0
            System.out.println("盘符文件系统类型:  " + fs.getSysTypeName()); // local 文件系统类型，比如 FAT32、NTFS
            System.out.println("盘符类型:  " + fs.getTypeName());  // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            System.out.println("盘符文件系统类型标识:  " + fs.getType()); // 文件系统类型*/

            FileLogList.add("盘符名称:" + fs.getDevName()); // 分区的盘符名称
            FileLogList.add("盘符路径:" + fs.getDirName()); // 分区的盘符路径
            FileLogList.add("盘符索引:" + i);   // 0
            //  FileLogList.add("盘符标志:" + fs.getFlags());// 盘符标志 0
            FileLogList.add("盘符文件系统类型:" + fs.getSysTypeName()); // local 文件系统类型，比如 FAT32、NTFS
            FileLogList.add("盘符类型:" + fs.getTypeName());  // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            FileLogList.add("盘符文件系统类型标识:" + fs.getType()); // 文件系统类型

//            public static final int TYPE_UNKNOWN = 0;
//            public static final int TYPE_NONE = 1;
//            public static final int TYPE_LOCAL_DISK = 2;
//            public static final int TYPE_NETWORK = 3;
//            public static final int TYPE_RAM_DISK = 4;
//            public static final int TYPE_CDROM = 5;
//            public static final int TYPE_SWAP = 6;

            FileSystemUsage usage = null;
            try {
                usage = sigar.getFileSystemUsage(fs.getDirName());
            } catch (SigarException e) {
                e.printStackTrace();
                return;
            }
            switch (fs.getType()) {
                case 0: // TYPE_UNKNOWN :未知
                    break;
                case 1: // TYPE_NONE
                    break;
                case 2: // TYPE_LOCAL_DISK : 本地硬盘
                    double usePercent = usage.getUsePercent() * 100D;
/*                    System.out.println(fs.getDevName() + "总大小:" + (Double.parseDouble(nf.format((Double)(usage.getTotal() / (1024d*1024d*1024d))))) + "GB"); // 文件系统总大小
                    System.out.println(fs.getDevName() + "剩余大小:" + (Double.parseDouble(nf.format((Double)(usage.getFree() / (1024d*1024d*1024d))))) + "GB");// 文件系统剩余大小
                    System.out.println(fs.getDevName() + "可用大小:" + (Double.parseDouble(nf.format((Double)(usage.getAvail() / (1024d*1024d*1024d))))) + "GB"); // 文件系统可用大小
                    System.out.println(fs.getDevName() + "已经使用量:" + (Double.parseDouble(nf.format((Double)(usage.getUsed() / (1024d*1024d*1024d))))) + "GB"); // 文件系统已经使用量   getUsed
                    System.out.println(fs.getDevName() + "资源的利用率:" + usePercent + "%"); // 文件系统资源的利用率*/

                    FileLogList.add("当前盘符总空间:" + fs.getDevName() + "总空间:       " + (Double.parseDouble(nf.format((Double) (usage.getTotal() / (1024d * 1024d))))) + "GB"); // 文件系统总大小
                    FileLogList.add("当前盘符剩余空间:" + fs.getDevName() + "剩余空间:     " + (Double.parseDouble(nf.format((Double) (usage.getFree() / (1024d * 1024d))))) + "GB");// 文件系统剩余大小
                    FileLogList.add("当前盘符可用空间:" + fs.getDevName() + "可用空间:     " + (Double.parseDouble(nf.format((Double) (usage.getAvail() / (1024d * 1024d))))) + "GB"); // 文件系统可用大小
                    FileLogList.add("当前盘符已经使用量:" + fs.getDevName() + "已经使用量:   " + (Double.parseDouble(nf.format((Double) (usage.getUsed() / (1024d * 1024d))))) + "GB"); // 文件系统已经使用量   getUsed
                    FileLogList.add("当前盘符资源利用率:" + fs.getDevName() + "资源的利用率: " + usePercent + "%"); // 文件系统资源的利用率

                    break;
                case 3:// TYPE_NETWORK :网络
                    break;
                case 4:// TYPE_RAM_DISK :闪存
                    break;
                case 5:// TYPE_CDROM :光驱
                    break;
                case 6:// TYPE_SWAP :页面交换
                    break;
            }
/*            System.out.println(fs.getDevName() + "读出:  " + usage.getDiskReads());
            System.out.println(fs.getDevName() + "写入:  " + usage.getDiskWrites());*/
            FileLogList.add("当前盘符读出操作物理磁盘块号:" + fs.getDevName() + "读出块号:  " + usage.getDiskReads());
            FileLogList.add("当前盘符写入操作物理磁盘块号:" + fs.getDevName() + "写入块号:  " + usage.getDiskWrites());
            FileLogList.add(":===========================");
        }
        ArrayPrint(FileLogList, "文件系统");
        return;
    }

    private static void net() {
        Sigar sigar = new Sigar();
        String ifNames[] = new String[0];
        try {
            ifNames = sigar.getNetInterfaceList();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        ArrayList<String> netLogList = new ArrayList<String>();

        netLogList.add("网络设备数量:" + ifNames.length);
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = null;
            try {
                ifconfig = sigar.getNetInterfaceConfig(name);
            } catch (SigarException e) {
                e.printStackTrace();
                return;
            }
//            System.out.println("网络设备名:  " + name);// 网络设备名
//            System.out.println("IP地址:  " + ifconfig.getAddress());// IP地址   // 0.0.0.0
//            System.out.println("子网掩码:  " + ifconfig.getNetmask());// 子网掩码
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                //        System.out.println("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            if ("0.0.0.0".equals(ifconfig.getAddress().trim())) { // 不打印为 IP 为 0 的那些设备
                continue;
            }
            NetInterfaceStat ifstat = null;
            try {
                ifstat = sigar.getNetInterfaceStat(name);
            } catch (SigarException e) {
                e.printStackTrace();
            }
/*            System.out.println("网络设备名:  " + name);// 网络设备名
            System.out.println("IP地址:  " + ifconfig.getAddress());// IP地址
            System.out.println("子网掩码:  " + ifconfig.getNetmask());// 子网掩码
            System.out.println("接收的总包裹数:" +    name + "接收的总包裹数=" +    ifstat.getRxPackets());// 接收的总包裹数
            System.out.println("发送的总包裹数:" +    name + "发送的总包裹数=" +    ifstat.getTxPackets());// 发送的总包裹数
            System.out.println("接收到的总字节数:" +   name + "接收到的总字节数=" +    ifstat.getRxBytes());// 接收到的总字节数
            System.out.println("发送的总字节数:" +    name + "发送的总字节数=" +       ifstat.getTxBytes());// 发送的总字节数
            System.out.println("接收到的错误包数:" +   name + "接收到的错误包数=" +    ifstat.getRxErrors());// 接收到的错误包数
            System.out.println("发送数据包时的错误数:" + name + "发送数据包时的错误数=" +   ifstat.getTxErrors());// 发送数据包时的错误数
            System.out.println("接收时丢弃的包数:"    +name + "接收时丢弃的包数="    +    ifstat.getRxDropped());// 接收时丢弃的包数
            System.out.println("发送时丢弃的包数:"   + name + "发送时丢弃的包数="   + ifstat.getTxDropped());// 发送时丢弃的包数*/


            netLogList.add("网络设备名:  " + name);// 网络设备名
            netLogList.add("IP地址:  " + ifconfig.getAddress());// IP地址
            netLogList.add("子网掩码:  " + ifconfig.getNetmask());// 子网掩码
            netLogList.add("接收的总包裹数:" + name + "接收的总包裹数=" + ifstat.getRxPackets());// 接收的总包裹数
            netLogList.add("发送的总包裹数:" + name + "发送的总包裹数=" + ifstat.getTxPackets());// 发送的总包裹数
            netLogList.add("接收到的总字节数:" + name + "接收到的总字节数=" + ifstat.getRxBytes());// 接收到的总字节数
            netLogList.add("发送的总字节数:" + name + "发送的总字节数=" + ifstat.getTxBytes());// 发送的总字节数
            netLogList.add("接收到的错误包数:" + name + "接收到的错误包数=" + ifstat.getRxErrors());// 接收到的错误包数
            netLogList.add("发送数据包时的错误数:" + name + "发送数据包时的错误数=" + ifstat.getTxErrors());// 发送数据包时的错误数
            netLogList.add("接收时丢弃的包数:" + name + "接收时丢弃的包数=" + ifstat.getRxDropped());// 接收时丢弃的包数
            netLogList.add("发送时丢弃的包数:" + name + "发送时丢弃的包数=" + ifstat.getTxDropped());// 发送时丢弃的包数
            netLogList.add(":=========================");

        }
        ArrayPrint(netLogList, "网络信息");
    }

    private static void ethernetPC()  {
        Sigar sigar = null;
        sigar = new Sigar();
        ArrayList<String> ethernetLogList = new ArrayList<String>();
        String[] ifaces = new String[0];
        try {
            ifaces = sigar.getNetInterfaceList();
        } catch (SigarException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = null;
            try {
                cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            } catch (SigarException e) {
                e.printStackTrace();
            }
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            String desc = cfg.getDescription();

/*            System.out.println("IP地址:" +cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
            System.out.println("网关广播地址:" +cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
            System.out.println("网卡MAC地址:" +cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
            System.out.println("子网掩码:" +cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
            System.out.println("网卡描述信息:" +cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
            System.out.println( "网卡类型" +cfg.getName() + "网卡类型" + cfg.getType());//
            System.out.println(":=================================");*/
            ethernetLogList.add("PC网卡名称:" +cfg.getName());// IP地址
            ethernetLogList.add("IP地址:" +cfg.getName() + " IP地址:" + cfg.getAddress());// IP地址
            ethernetLogList.add("网关广播地址:" +cfg.getName() + " 网关广播地址:" + cfg.getBroadcast());// 网关广播地址
            ethernetLogList.add("网卡MAC地址:" +cfg.getName() + " 网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
            ethernetLogList.add("子网掩码:" +cfg.getName() + " 子网掩码:" + cfg.getNetmask());// 子网掩码
            ethernetLogList.add("网卡描述信息:" +cfg.getName() + " 网卡描述信息:" + cfg.getDescription());// 网卡描述信息
            ethernetLogList.add("网卡类型" +cfg.getName() + " 网卡类型" + cfg.getType());//
            ethernetLogList.add(":=================================");
            ArrayPrint(ethernetLogList,"PC网卡信息");
            return; // 只打印 第一个网卡本身的信息
        }

    }

    private static void ethernetWireless(){
        Sigar sigar = null;
        sigar = new Sigar();
        ArrayList<String> ethernetLogList = new ArrayList<String>();
        String[] ifaces = new String[0];
        try {
            ifaces = sigar.getNetInterfaceList();
        } catch (SigarException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = null;
            try {
                cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            } catch (SigarException e) {
                e.printStackTrace();
            }
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            String desc = cfg.getDescription();
            if(!desc.contains("802.11")){
                continue;
            }
/*            System.out.println("IP地址:" +cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
            System.out.println("网关广播地址:" +cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
            System.out.println("网卡MAC地址:" +cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
            System.out.println("子网掩码:" +cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
            System.out.println("网卡描述信息:" +cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
            System.out.println( "网卡类型" +cfg.getName() + "网卡类型" + cfg.getType());//
            System.out.println(":=================================");*/
            ethernetLogList.add("无线网卡名称:" +cfg.getName());// IP地址
            ethernetLogList.add("IP地址:" +cfg.getName() + " IP地址:" + cfg.getAddress());// IP地址
            ethernetLogList.add("网关广播地址:" +cfg.getName() + " 网关广播地址:" + cfg.getBroadcast());// 网关广播地址
            ethernetLogList.add("网卡MAC地址:" +cfg.getName() + " 网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
            ethernetLogList.add("子网掩码:" +cfg.getName() + " 子网掩码:" + cfg.getNetmask());// 子网掩码
            ethernetLogList.add("网卡描述信息:" +cfg.getName() + " 网卡描述信息:" + cfg.getDescription());// 网卡描述信息
            ethernetLogList.add("网卡类型" +cfg.getName() + " 网卡类型" + cfg.getType());//
            ethernetLogList.add(":=================================");

        }
        ArrayPrint(ethernetLogList,"无线网卡信息");
    }

    private static void ethernet()  {
        Sigar sigar = null;
        sigar = new Sigar();
        ArrayList<String> ethernetLogList = new ArrayList<String>();
        String[] ifaces = new String[0];
        try {
            ifaces = sigar.getNetInterfaceList();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        ethernetLogList.add("网卡数量:"+ ifaces.length);
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = null;
            try {
                cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            } catch (SigarException e) {
                e.printStackTrace();
            }
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
/*            System.out.println("IP地址:" +cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
            System.out.println("网关广播地址:" +cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
            System.out.println("网卡MAC地址:" +cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
            System.out.println("子网掩码:" +cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
            System.out.println("网卡描述信息:" +cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
            System.out.println( "网卡类型" +cfg.getName() + "网卡类型" + cfg.getType());//
            System.out.println(":=================================");*/
            ethernetLogList.add("网卡名称:" +cfg.getName());// IP地址
            ethernetLogList.add("IP地址:" +cfg.getName() + " IP地址:" + cfg.getAddress());// IP地址
            ethernetLogList.add("网关广播地址:" +cfg.getName() + " 网关广播地址:" + cfg.getBroadcast());// 网关广播地址
            ethernetLogList.add("网卡MAC地址:" +cfg.getName() + " 网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
            ethernetLogList.add("子网掩码:" +cfg.getName() + " 子网掩码:" + cfg.getNetmask());// 子网掩码
            ethernetLogList.add("网卡描述信息:" +cfg.getName() + " 网卡描述信息:" + cfg.getDescription());// 网卡描述信息
            ethernetLogList.add("网卡类型" +cfg.getName() + " 网卡类型" + cfg.getType());//
            ethernetLogList.add(":=================================");

        }
        ArrayPrint(ethernetLogList,"网卡信息");
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

    static final int MAX_COUNT_CHAR_IN_ROW = 120;

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