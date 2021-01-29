
import java.io.*;
import java.util.ArrayList;

public class K1_ShowQcomInfo {
    static String OneLine_Pre = "\n════════════════════════════════════════════════";
    static String OneLine_End = "════════════════════════════════════════════════\n";
    static String OneLine = "════════";

    static String User_Home = System.getProperties().getProperty("user.home");
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";


    static String J1_IDEA_PATH = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+ File.separator+"J1_IDEA";
    static File J1_IDEA_File = new File(J1_IDEA_PATH);



    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static String BAT_OR_SH_Point ;

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            BAT_OR_SH_Point = ".bat";
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            BAT_OR_SH_Point = ".sh";
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            BAT_OR_SH_Point = ".sh";
        }
    }


    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    public static void main(String[] args) {
        initSystemInfo();
        ShowQcomTip();
        if(CUR_OS_TYPE == OS_TYPE.Windows){
        }else if (CUR_OS_TYPE == OS_TYPE.Linux){

        }else if (CUR_OS_TYPE == OS_TYPE.MacOS){
        }



    }


    public static void  ShowQcomTip(){

        System.out.println("═══════════════ 缩写 ═══════════════");
        System.out.println("QXDM   ====   Qualcomm eXtensive Diagnostic Monitor    ==== 获取上下行信令 数据业务包");
        System.out.println("QPST   ====   Qualcomm Product Support Tool            ==== 对手机资源进行管理 用于手机与电脑通讯");
        System.out.println("QCAT   ====   QualComm Log Analysis Tool               ==== 分析QXDM获取到的LOG");
        System.out.println();
        System.out.println("═══════════════ 软件Desc ═══════════════");
        System.out.println("【 QXDM ---> {.isf}   {.dmc}                   QXDM 】  关闭打开备份还原NV、NV修改、抓modem log(.sif)");
        System.out.println("【 QCAT ---> {.dlf}   {.qmdl}    {.qmdl2}      QCAT 】  解析log工具，方便查看");
        System.out.println("【 QPST ---> {}                                QPST 】  手机NV备份(QCN) 资源管理器(EFS Explorer) dump日志");
        System.out.println();
        System.out.println("═══════════════ 文件转换Desc ═══════════════");
        System.out.println("【 .slf   <--->   .dlf 】");
        System.out.println("【 .qmdl  <--->   .slf 】");
        System.out.println("【 .qmdl  <--->   .dlf 】");
        System.out.println();
        System.out.println("═══════════════ 文件说明Desc ═══════════════");
        System.out.println(".cfg   《GNSS_V9.cfg》 【bug2go 抓取Log配置文件】");
        System.out.println(".dmc   《QXDM Professional Confiruration File》【QXDM的配置文件】");
        System.out.println(".dlf   《Dialog Log file》《DLF文件是高通老版本的分析工具产生的文件》《dlf 可转为 isf》");
        System.out.println(".isf   《item Stored  file》【QXDM中 item-View 面板的日志Log】");
        System.out.println(".qmdl  《diag诊断端口Log》");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("═══════════════ QXDM 激活码 ═══════════════");
        System.out.println("User Name:   ZTE");
        System.out.println("Password:        walshcode");
        System.out.println("Admin Key:      1071");
        System.out.println();
        System.out.println("下载地址: https://pan.baidu.com/disk/home#/all?vmode=list&path=%2FQXDM");
        System.out.println();
        System.out.println();
        System.out.println("═══════════════ QXDM 使用技巧 ═══════════════");
        System.out.println("Ctrl  +F -----搜索");
        System.out.println("Ctrl  +N -----下一个搜索结果");
        System.out.println("Ctrl  +G -----调站到指定索引Log");
        System.out.println("Ctrl  +M ----- 配置 dmc文件");
        System.out.println("C:\\ProgramData\\Qualcomm\\QXDM\\Config\\Qualcomm DMC Library\\Primary\\Default.dmg");
        System.out.println("C:\\ProgramData\\Qualcomm\\QXDM\\Config\\Qualcomm DMC Library\\Secondary\\Audio/AudioBasic.dmc");
        System.out.println("C:\\ProgramData\\Qualcomm\\QXDM\\Config\\Qualcomm DMC Library\\Secondary\\DPL/DPLFull.dmc");
        System.out.println("C:\\ProgramData\\Qualcomm\\QXDM\\Config\\Qualcomm DMC Library\\Secondary\\GPS/GNSSLocationLogging.dmc");
        System.out.println("C:\\ProgramData\\Qualcomm\\QXDM\\Config\\Qualcomm DMC Library\\Secondary\\HLOS Data/CnEMessageAll.dmc");
        System.out.println("C:\\ProgramData\\Qualcomm\\QXDM\\Config\\Qualcomm DMC Library\\Secondary\\IoT Chipsets/IOT_Default_NB-IOT.dmc");
        System.out.println("C:\\ProgramData\\Qualcomm\\QXDM\\Config\\Qualcomm DMC Library\\Secondary\\Voice/VoiceAll.dmc");
        System.out.println();
        System.out.println("Alt   +S -----恢复FliterView自动滚动");
        System.out.println("Alt   +L -----打开/关闭 Log View");
        System.out.println("Alt   +M -----在当前的View视频中打开 Match列表 可过滤 对应选中的  type类型的字符 在新窗口中显示");
        System.out.println("Alt   +R -----  根据当前面板中的 type-int 值 再进行一次过滤");
        System.out.println("Alt   +B -----  把当前Log中的一项 type-int-time 加入到 标签列表  将深色显示 , 再次 Alt+B 将删除标记点");
        System.out.println("Alt   +D -----  Down 向下快速定位下一个标签点");
        System.out.println("Alt   +U -----  Up 向上快速定位上一个标签点");
        System.out.println("View > VookmarkList View   可查看当前标签集合");
        System.out.println("Shift +Del -----删除当前Pane中的Log项");
        System.out.println("Item View > 显示当前配置文件过滤的所有的Log项");
        System.out.println("File > Load Deafult Configuration     回复使用默认的配置文件来显示当前的Log项");
        System.out.println("Fliter View  >  右键  > Configuration 可配置只在Fliter View 显示最新的匹配 type 的Log项(type-int)");
        System.out.println("Windows -> Cascade          把所有窗口依次排列 队列对齐");
        System.out.println("Windows -> Minized All      把所有窗口最小化 排列在窗口底部");
        System.out.println("Windows -> Restore All      把最小化的窗口 恢复到显示窗口");
        System.out.println("Windows -> Tile Horizon     把当前窗口水平排列 填满窗口");
        System.out.println();
        System.out.println("╔══════════════════════════════╦══════════════╗");
        System.out.println("║QXDM数据type类型              ║ 数据源Sorce  ║");
        System.out.println("╠══════════════════════════════╬══════════════╣");
        System.out.println("║Diag Malformed Packets        ║ Phone        ║");
        System.out.println("║Diag Responses                ║ Phone        ║");
        System.out.println("║Event Reports                 ║ Phone        ║");
        System.out.println("║GPS Reports                   ║ GPS Receiver ║");
        System.out.println("║Log Packets                   ║ Phone        ║");
        System.out.println("║Log Packets (OTA)             ║ Phone        ║");
        System.out.println("║Subsystem Dispatch Responses  ║ Phone        ║");
        System.out.println("║Message                       ║ Packets Phone║");
        System.out.println("║Diag Requests                 ║ QXDM, User   ║");
        System.out.println("║Strings                       ║ QXDM, User   ║");
        System.out.println("║Subsystem Dispatch Requests   ║ QXDM, User   ║");
        System.out.println("╚══════════════════════════════╩══════════════╝");
        System.out.println();
        System.out.println("toolbar (右击上下菜单显示 开关 )命令行: Videw finder 中 输入 out 打开 output command view");
        System.out.println("mode offline-a             ##手机转为离线A模拟nalog模式 命令行");
        System.out.println("mode offline-d             ##手机转为离线数字Digital模式 命令行");
        System.out.println("mode ftm                   ## 转为工厂测试模式");
        System.out.println("mode lpm                   ## 转为低功耗模式");
        System.out.println("mode online                ## 转为online digital 模式");
        System.out.println("mode reset                 ## 重置  reset the target");
        System.out.println("pause                     ## 暂停");
        System.out.println("echo \"this is zzzzz\"      ##把字符串输入到当前的记录的 isf文件列表");
        System.out.println("RequestNVItemRead \"banner\"    ## 请求 NVram的值  参数似乎 NV item entity to read");
        System.out.println("RequestNVItemRead \"air_timer\"");
        System.out.println("RequestNVItemWrite \"banner\"  \"xxxx\"");
        System.out.println("RequestNVItemIDRead 71");
        System.out.println("RequestNVItemIDRead 10 0 0 0");
        System.out.println("RequestNVItemIDWrite 71 77 83 77 32 54 53 53 48");
        System.out.println("RequestNVItemIDWrite 10 0 0 0");
        System.out.println("SendRawRequest 39 71 0 77 83 77 32 54 53 53 48");
        System.out.println("SendRawRequest 0");
        System.out.println("WaitForItem EVENT_CALL_CONTROL_TERMINATED");
        System.out.println("WaitForItem \"Searcher and Finger\"");
        System.out.println();


    }



    public static ArrayList<String> ReadFileContentAsList( File mFilePath) {

        if (mFilePath != null  && mFilePath.exists()) {
            //  System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 "+ mFilePath.getAbsolutePath() );

            return null;
        }
        ArrayList<String> contentList= new ArrayList<String>();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null ) {
                    continue;
                }

                contentList.add(oldOneLine);
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;

            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentList;

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












}