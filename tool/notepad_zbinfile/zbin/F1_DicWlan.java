import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class F1_DicWlan {

    static HashMap<String, ArrayList<Dic_Item>> dictory_All = new HashMap<String, ArrayList<Dic_Item>>();
    static NumberFormat nf = new DecimalFormat("0.00");


    enum OS_TYPE{
        Windows,
        Linux,
        MacOS
    }
    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;


    // 输入的参数列表
    static ArrayList<String> mKeyWordName = new ArrayList<>();


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

    // 随系统 动态调整大小
    static void Dynic_MAX_COUNT_CHAR_IN_ROW(){
        if(curOS_TYPE == OS_TYPE.Windows){
            MAX_COUNT_CHAR_IN_ROW = 140;
        }else if(curOS_TYPE == OS_TYPE.Linux ){
            MAX_COUNT_CHAR_IN_ROW = 126;
        }else if(curOS_TYPE == OS_TYPE.Linux ){
            MAX_COUNT_CHAR_IN_ROW = 126;
        }
    }

    public static void main(String[] args) {
        initSystemInfo();
        Dynic_MAX_COUNT_CHAR_IN_ROW();

        long timestamp1 = System.currentTimeMillis();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                mKeyWordName.add(itemArgStr);
            }
        }

        if (mKeyWordName.size() == 0) {
            System.out.println("用户输入的英文缩写参数为空-将打印所有保存的字典信息");
            showAllDicInfo();
            // z1 打印所有的字典信息
            // z2 打印对应的使用方法说明
            return;
        }

        for (int i = 0; i < mKeyWordName.size(); i++) {
            String item = mKeyWordName.get(i);
            String targetChar = item.toLowerCase().charAt(0) + "";
            if (!"".equals(item) && item.length() == 1) {
                // 如果当前只包含一个字母   那么打印该字母所有的对应缩写信息
                //z3 打印
                ArrayList<Dic_Item> targetArr = dictory_All.get(targetChar);
                if (targetArr == null || targetArr.size() == 0) {
                    break;
                }
                ArrayDicPrint(targetArr, item + "【" + targetArr.size() + "】查询结果");

            } else {
                String prefix = item.toLowerCase().trim();   //
                //
                ArrayList<Dic_Item> printDicArr = new ArrayList<Dic_Item>();  // 保存经过过滤的 字典项
                ArrayList<Dic_Item> targetArr = dictory_All.get(targetChar);
                if (targetArr == null) {
                    break;
                }
                for (int j = 0; j < targetArr.size(); j++) {
                    String itemShort = targetArr.get(j).getShortWord().toLowerCase();  // 拿到保存的缩写
                    if (itemShort.startsWith(prefix)) {
                        printDicArr.add(targetArr.get(j));
                    }
                }
                ArrayDicPrint(printDicArr, item + "【" + printDicArr.size() + "】查询结果");

            }

        }

        long timestamp2 = System.currentTimeMillis();
        long timedistance = timestamp2 - timestamp1;

        System.out.println("程序执行花销 " + Double.parseDouble(nf.format((Double) (timedistance / (1024d)))) + "秒!");


    }


    public static void ArrayDicPrint(ArrayList<Dic_Item> dicList, String title) {
        ArrayList<String> logArr = new ArrayList<String>();
        ArrayList<String> urlArr = new ArrayList<String>();

        for (int i = 0; i < dicList.size(); i++) {
            Dic_Item dicItem = dicList.get(i);
            String value1 = dicItem.getShortWord();
            String valie2 = dicItem.getFullWorld_en();
            String valie3 = dicItem.getFullWorld_ch();
            ArrayList<String> descList = dicItem.getDescList();
            logArr.add("缩写:" + value1);
            logArr.add("缩写全拼:" + valie2);
            logArr.add("中文解释:" + valie3);

            for (int j = 0; j < descList.size(); j++) {
                logArr.add("Desc:" + descList.get(j));
            }
            logArr.add("-----------------------------");
            ArrayList<String> urlListItem = dicItem.getRelativeUrl();
            if (dicList.size() <= 2) {  // 自动打开Chrome 浏览器 打开网站?
                for (int j = 0; j < urlListItem.size(); j++) {
                    urlArr.add(urlListItem.get(j));
                }
            }


        }


        ArrayPrint(logArr, title);
        if (urlArr.size() > 0) {
            for (int i = 0; i < urlArr.size(); i++) {
                String urlItem = urlArr.get(i);
                // 打开对应的网页
                //  command4 = "cmd.exe /C start chrome   https://dreampuf.github.io/GraphvizOnline/ " ;
                String command = "cmd.exe /C start chrome  " + urlItem;
                execCMD(command);
            }
        }
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


    @SuppressWarnings("unchecked")
    public static void showAllDicInfo() {
        Map.Entry<String, ArrayList<Dic_Item>> entry;
        int alldicSize = 0;

        if (dictory_All != null) {
            Iterator iterator = dictory_All.entrySet().iterator();

            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<Dic_Item>>) iterator.next();
                String keyStr = entry.getKey();  //Map的Value
                ArrayList<Dic_Item> dicList = entry.getValue();  //Map的Value
                alldicSize = alldicSize + dicList.size();
                if (dicList.size() > 0) {
                    ArrayDicPrintFormMap(dicList, keyStr + "--" + keyStr.toUpperCase() + "【" + dicList.size() + "】");
                }

            }
        }
        System.out.println("当前收集到的所有 Dic_Item 数量为:" + alldicSize);

    }


    public static void ArrayDicPrintFormMap(ArrayList<Dic_Item> dicList, String title) {
        ArrayList<String> logArr = new ArrayList<String>();
        for (int i = 0; i < dicList.size(); i++) {
            Dic_Item dicItem = dicList.get(i);
            String value1 = dicItem.getShortWord();
            String valie2 = dicItem.getFullWorld_en();
            String valie3 = dicItem.getFullWorld_ch();
            ArrayList<String> descList = dicItem.getDescList();
            logArr.add("缩写:" + value1);
            logArr.add("缩写全拼:" + valie2);
            logArr.add("中文解释:" + valie3);

            for (int j = 0; j < descList.size(); j++) {
                logArr.add("Desc:" + descList.get(j));
            }
            logArr.add("-----------------------------");
        }


        ArrayPrint(logArr, title);

    }


    // a-A
    static {

        ArrayList<Dic_Item> a_DicList = new ArrayList<Dic_Item>();
        /*a-begin*/
        /*aggregation*/
        Dic_Item aggregation = new Dic_Item("aggregation");
        aggregation.setFullWorld_en("Aggregation");
        aggregation.setFullWorld_ch("帧聚合");
        ArrayList<String> aggregation_DescList = new ArrayList<String>();
        aggregation_DescList.add("1.把多个MSDU【MAC Service Data Unit,MAC服务数据单元】通过一定的方式聚合成一个较大的载荷(类似压缩技术?)");
        aggregation_DescList.add("2.帧聚合机制通过在一次信道接入机会中发送多个数据帧，极大提升了MAC层效率和MAC层吞吐量");
        aggregation_DescList.add("3.在802.11n 版本中首次运用实践");
        ArrayList<String> aggregation_UrlList = new ArrayList<String>();
        aggregation_UrlList.add("https://baike.baidu.com/item/Aggregation%20MSDU/4084575");
        aggregation_UrlList.add("https://blog.csdn.net/qq_16390523/article/details/46618925");
        aggregation.setDescList(aggregation_DescList);
        aggregation.setRelativeUrl(aggregation_UrlList);
        a_DicList.add(aggregation);


        /*amsdu*/
        Dic_Item amsdu = new Dic_Item("amsdu");
        amsdu.setFullWorld_en("Aggregate Medium Access Control Service Data Unitil");
        amsdu.setFullWorld_ch("MAC服务数据单元聚合");
        ArrayList<String> amsdu_DescList = new ArrayList<String>();
        amsdu_DescList.add("1.802.11n 引入帧聚合 ,MAC服务数据单元聚合 MSDU 位于MAC层的顶部  多个MSDU聚合为  A_MSDU  ");
        amsdu_DescList.add("2.A_MSDU 经过加密分片聚合而来 来到Mac层的底部  变为  A_MPDU MAC协议数据单元聚合 ");
        ArrayList<String> amsdu_UrlList = new ArrayList<String>();
        amsdu_UrlList.add("https://blog.csdn.net/qq_16390523/article/details/46618925");
        amsdu.setDescList(amsdu_DescList);
        amsdu.setRelativeUrl(amsdu_UrlList);
        a_DicList.add(amsdu);




        /*a-end*/
        dictory_All.put("a", a_DicList);

    }

    // b-B
    static {

        ArrayList<Dic_Item> b_DicList = new ArrayList<Dic_Item>();
        /*b_begin*/
        /*ba*/
        Dic_Item ba = new Dic_Item("ba");
        ba.setFullWorld_en("Block Acknowledgement");
        ba.setFullWorld_ch("块确认");
        ArrayList<String> ba_DescList = new ArrayList<String>();
        ba_DescList.add("1.为保证数据传输的可靠性，802.11最初的 802.11a 802.11b  802.11g 协议规定每收到一个单播数据帧，都必须立即回应以ACK帧。");
        ba_DescList.add("2.802.11n 加入帧聚合机制机制(Aggregation)");
        ba_DescList.add("3.【 Block Acknowledgement通过使用一个ACK帧来完成对多个MPDU的应答】【以降低这种情况下的ACK帧的数量，以此提高Mac效率】");
        ArrayList<String> ba_UrlList = new ArrayList<String>();
        ba_UrlList.add("https://blog.csdn.net/h784707460/article/details/80731772");
        ba.setDescList(ba_DescList);
        ba.setRelativeUrl(ba_UrlList);
        b_DicList.add(ba);



        /*b_end*/
        dictory_All.put("b", b_DicList);

    }

    // c-C
    static {

        ArrayList<Dic_Item> c_DicList = new ArrayList<Dic_Item>();
        /*c_begin*/
        /*csma_ca*/
        Dic_Item csma_ca = new Dic_Item("csma_ca");
        csma_ca.setFullWorld_en("Carrier Sense Multiple Access With Collision Avoidance");
        csma_ca.setFullWorld_ch("载波监听_多路访问_冲突避免");
        ArrayList<String> csma_ca_DescList = new ArrayList<String>();
        csma_ca_DescList.add("1.载波侦听多路访问（英语：Carrier Sense Multiple Access，缩写：CSMA）是一种介质访问控制（MAC）的协议。");
        csma_ca_DescList.add("2.载波侦听（英语：Carrier Sense）指任何连接到介质的设备在欲发送帧前，必须对介质进行侦听，当确认其空闲时，才可以发送。");
        csma_ca_DescList.add("3.多路访问（英语：Multiple Access）指多个设备可以同时访问介质，一个设备发送的帧也可以被多个设备接收。");
        csma_ca_DescList.add("4.冲突避免（英语：Collision Avoidance）在发送帧的同时要对信道进行侦听，以确定是否发生碰撞，若在发送数据过程中检测到碰撞则进行退避处理。");
        csma_ca_DescList.add("5.冲突避免（英语：Collision Avoidance）在发送较大帧 Big Frame  使用RTS CTS 机制来达到 冲突避免的效果");
        ArrayList<String> csma_ca_UrlList = new ArrayList<String>();
        csma_ca_UrlList.add("https://baike.baidu.com/item/CSMA%2FCA/10898090?fr=aladdin");
        csma_ca_UrlList.add("https://zh.wikipedia.org/wiki/%E8%BD%BD%E6%B3%A2%E4%BE%A6%E5%90%AC%E5%A4%9A%E8%B7%AF%E8%AE%BF%E9%97%AE#CSMA/CA");
        csma_ca.setDescList(csma_ca_DescList);
        csma_ca.setRelativeUrl(csma_ca_UrlList);
        c_DicList.add(csma_ca);


        /*cck*/
        Dic_Item cck = new Dic_Item("cck");
        cck.setFullWorld_en("Complementary Code Keying");
        cck.setFullWorld_ch("补码键控");
        ArrayList<String> cck_DescList = new ArrayList<String>();
        cck_DescList.add("1.IEEE 802.11b协议所采用的物理调制方式");
        cck_DescList.add("2.CCK的核心编码中有64个8位编码组成的集合， CCK的码字有很强的位置对称性和良好的自相关特性");
        ArrayList<String> cck_UrlList = new ArrayList<String>();
        cck_UrlList.add("https://baike.baidu.com/item/CCK/9208577?fr=aladdin#4");
        cck.setDescList(cck_DescList);
        cck.setRelativeUrl(cck_UrlList);
        c_DicList.add(cck);





        /*c_end*/
        dictory_All.put("w", c_DicList);

    }


    // d-D
    static {
        ArrayList<Dic_Item> d_DicList = new ArrayList<Dic_Item>();
        /*d_begin*/
        /*dfs*/
        Dic_Item dfs = new Dic_Item("dfs");
        dfs.setFullWorld_en("Dynamic Frequency Selection");
        dfs.setFullWorld_ch("动态频率选择");
        ArrayList<String> dfs_DescList = new ArrayList<String>();
        dfs_DescList.add("1.2003年世界无线电大会(WRC-03)要求所有工作在 5250Mhz---5350MHz  5470MHz---5725MHz 的设备必须支持DFS 和 先听后发机制");
        dfs_DescList.add("2.动态频率选择能监听到来自其他系统的信号,如果检测出雷达信号超过一定的门限值,DFS机制将使得设备切换到其他备用信道,避免干扰雷达");
        dfs_DescList.add("3.dfs功能在 802.11h标准中引入 , DFS是工作于5GHz频段的WLAN系统的必选功能");
        ArrayList<String> dfs_UrlList = new ArrayList<String>();
        dfs_UrlList.add("https://baike.baidu.com/item/DFS/3931922?fr=aladdin");
        dfs_UrlList.add("https://zh.wikipedia.org/wiki/%E4%BF%A1%E9%81%93%E5%88%86%E9%85%8D%E6%96%B9%E6%A1%88");
        dfs.setDescList(dfs_DescList);
        dfs.setRelativeUrl(dfs_UrlList);
        d_DicList.add(dfs);



        /*dsss*/
        Dic_Item dsss = new Dic_Item("dsss");
        dsss.setFullWorld_en("Direct Sequence Spread Spectrum");
        dsss.setFullWorld_ch("直接序列扩频");
        ArrayList<String> dsss_DescList = new ArrayList<String>();
        dsss_DescList.add("1.DSSS、就是直接把传输的数据由【高码率的宽频码序列】编码后,对原始载波进行调制以达到扩展频谱的目的");
        dsss_DescList.add("2.在DSSS接收端使用相同的【高码率的宽频码序列】对宽频序列进行解扩 使得得到原始载波数据 ");
        dsss_DescList.add("3.【高码率的宽频码序列】中使用的最多的是 伪造声序列 Pseudorandom Noise PN ");
        dsss_DescList.add("4.DSSS使得传输数据有较高的抗干扰能力,防窃听,频带利用率高的特点 ");
        ArrayList<String> dsss_UrlList = new ArrayList<String>();
        dsss_UrlList.add("https://baike.baidu.com/item/%E7%9B%B4%E6%8E%A5%E5%BA%8F%E5%88%97%E6%89%A9%E9%A2%91/4776014?fr=aladdin");
        dsss_UrlList.add("https://zh.wikipedia.org/wiki/%E7%9B%B4%E6%8E%A5%E5%BA%8F%E5%88%97%E6%89%A9%E9%A2%91");
        dsss.setDescList(dsss_DescList);
        dsss.setRelativeUrl(dsss_UrlList);
        d_DicList.add(dsss);





        /*d_end*/
        dictory_All.put("d", d_DicList);


    }


    // e-E
    static {


    }

    // f-F
    static {


        ArrayList<Dic_Item> w_DicList = new ArrayList<Dic_Item>();
        /*f-begin*/
        /*fcc*/
        Dic_Item fcc = new Dic_Item("fcc");
        fcc.setFullWorld_en("Federal Communications Commission");
        fcc.setFullWorld_ch("美国联邦通信委员会");
        ArrayList<String> fcc_DescList = new ArrayList<String>();
        fcc_DescList.add("1.美国政府的一个独立机构，直接对国会负责。FCC通过控制无线电广播、电视、电信、卫星和电缆来协调国内和国际的通信");
        ArrayList<String> fcc_UrlList = new ArrayList<String>();
        fcc_UrlList.add("https://baike.baidu.com/item/%E7%BE%8E%E5%9B%BD%E8%81%94%E9%82%A6%E9%80%9A%E4%BF%A1%E5%A7%94%E5%91%98%E4%BC%9A/5333414");
        fcc_UrlList.add("https://zh.wikipedia.org/wiki/%E8%81%94%E9%82%A6%E9%80%9A%E4%BF%A1%E5%A7%94%E5%91%98%E4%BC%9A");
        fcc.setDescList(fcc_DescList);
        fcc.setRelativeUrl(fcc_UrlList);
        w_DicList.add(fcc);


        /*fhss*/
        Dic_Item fhss = new Dic_Item("fhss");
        fhss.setFullWorld_en("Frequency-Hopping Spread Spectrum");
        fhss.setFullWorld_ch("跳频扩频技术");
        ArrayList<String> fhss_DescList = new ArrayList<String>();
        fhss_DescList.add("1.接受两端以特定型式的窄频载波来传送讯号，对于一个非特定的接受器，FHSS所产生的跳动讯号对它而言，也只算是脉冲噪声。");
        fhss_DescList.add("2.跳频扩频就是发送端根据扩频码序列去进行移频键控（FSK）调制，使载波的频率不断地跳变。");
        fhss_DescList.add("3.接收端由于有与发送端完全相同的扩频码序列，从而可以跟踪频率不断跳变的信号并对信号进行解扩，然后通过解调正确地恢复原有的信息。");
        ArrayList<String> fhss_UrlList = new ArrayList<String>();
        fhss_UrlList.add("https://baike.baidu.com/item/fhss");
        fhss_UrlList.add("https://zh.wikipedia.org/wiki/%E8%B7%B3%E9%A2%91%E6%89%A9%E9%A2%91");
        fhss.setDescList(fhss_DescList);
        fhss.setRelativeUrl(fhss_UrlList);
        w_DicList.add(fhss);


        /*f-end*/
        dictory_All.put("f", w_DicList);
    }

    // g-G
    static {


    }

    // h-H
    static {


    }


    // i-I
    static {


    }


    // j-J
    static {


    }


    // k-K
    static {


    }


    // l-L
    static {


    }


    // m-M
    static {

        ArrayList<Dic_Item> m_DicList = new ArrayList<Dic_Item>();
        /*m_begin*/
        /*mac*/
        Dic_Item mac = new Dic_Item("mac");
        mac.setFullWorld_en("Media Access Control");
        mac.setFullWorld_ch("媒体访问控制");
        ArrayList<String> mac_DescList = new ArrayList<String>();
        mac_DescList.add("1.OSI七层网络栈中(数据链路层DLL)中两个子层(LLC逻辑链路层 和 MAC媒体控制层)中的一个子层");
        mac_DescList.add("2.Mac层又可详细分为两层(1. Mac子层  2.Mac管理子层)");
        mac_DescList.add("3.【Mac子层 负责: 访问机制 数据帧的分组与拆分 】");
        mac_DescList.add("4.【 Mac管理子层负责:  ESS 管理 电源管理 关联过程中的 关联 解关联 重关联 的过程管理】");
        ArrayList<String> mac_UrlList = new ArrayList<String>();
        mac_UrlList.add("https://baike.baidu.com/item/MAC/329671?fr=aladdin");

        mac_UrlList.add("https://zh.wikipedia.org/wiki/%E4%BB%8B%E8%B4%A8%E8%AE%BF%E9%97%AE%E6%8E%A7%E5%88%B6");
        mac.setDescList(mac_DescList);
        mac.setRelativeUrl(mac_UrlList);
        m_DicList.add(mac);



        /*m_end*/
        dictory_All.put("m", m_DicList);

    }

    // n-N
    static {


    }

    // o-O
    static {

        ArrayList<Dic_Item> w_DicList = new ArrayList<Dic_Item>();
        /*o-begin*/
        /*osi*/
        Dic_Item osi = new Dic_Item("osi");
        osi.setFullWorld_en("Open System Interconnect");
        osi.setFullWorld_ch("开放系统互联模型(7层  APST...)");
        ArrayList<String> osi_DescList = new ArrayList<String>();
        osi_DescList.add("1.OSI模型把网络通信的工作分为7层，分别是物理层、数据链路层、网络层、传输层[T]、会话层[S]、表示层[P]和应用层[A]");
        ArrayList<String> osi_UrlList = new ArrayList<String>();
        osi_UrlList.add("https://baike.baidu.com/item/OSI/5520");
        osi_UrlList.add("https://zh.wikipedia.org/wiki/OSI%E6%A8%A1%E5%9E%8B");
        osi.setDescList(osi_DescList);
        osi.setRelativeUrl(osi_UrlList);
        w_DicList.add(osi);


        /*ofdm*/
        Dic_Item ofdm = new Dic_Item("ofdm");
        ofdm.setFullWorld_en("Orthogonal Frequency Division Multiplexing");
        ofdm.setFullWorld_ch("正交频分复用技术");
        ArrayList<String> ofdm_DescList = new ArrayList<String>();
        ofdm_DescList.add("1.【OFDM是一种多载波调制方式，通过减小和消除码间串扰的影响来克服信道的频率选择性衰落】");
        ofdm_DescList.add("2.【其基本原理是将信号分割为N个子信号，然后用N个子信号分别调制N个相互正交的子载波】");
        ofdm_DescList.add("3.通过频分复用实现高速串行数据的并行传输, 它具有较好的抗多径衰弱的能力，能够支持多用户接入");
        ofdm_DescList.add("4.OFDM在发送端子载波上的正交调制可以使用 傅里叶逆变换 IFFT( Inverse Fast Fourier Transform) 来实现");
        ofdm_DescList.add("5.OFDM接收端可使用 快速傅里叶变换 FTT (Fast Fourier Transform) 来实现对接收到的正交信号还原为原始信号");
        ArrayList<String> ofdm_UrlList = new ArrayList<String>();
        ofdm_UrlList.add("https://baike.baidu.com/item/OFDM");
        ofdm_UrlList.add("https://zh.wikipedia.org/wiki/%E6%AD%A3%E4%BA%A4%E9%A0%BB%E5%88%86%E5%A4%8D%E7%94%A8");
        ofdm.setDescList(ofdm_DescList);
        ofdm.setRelativeUrl(ofdm_UrlList);
        w_DicList.add(ofdm);


        /*o-end*/
        dictory_All.put("o", w_DicList);

    }

    // p-P
    static {

        ArrayList<Dic_Item> p_DicList = new ArrayList<Dic_Item>();
        /*p_begin*/
        /*plcp*/
        Dic_Item plcp = new Dic_Item("plcp");
        plcp.setFullWorld_en("Physical Layer Convergence Procedure");
        plcp.setFullWorld_ch("物理层汇聚过程层");
        ArrayList<String> plcp_DescList = new ArrayList<String>();
        plcp_DescList.add("1. 802.11协议将服务层进一步划分为 PLCP(物理层汇聚过程层)  PMD(物理层媒体依赖层) PLM(物理层管理子层)");
        plcp_DescList.add("2.  PLCP(物理层汇聚过程层) 将Mac帧映射到媒体上 主要进行载波监听分析以及 针对不同的物理层形成相应格式的分组");
        ArrayList<String> plcp_UrlList = new ArrayList<String>();
        plcp_UrlList.add("https://baike.baidu.com/item/PLCP");
        plcp_UrlList.add("http://blog.chinaunix.net/uid-26611973-id-3431390.html");
        plcp.setDescList(plcp_DescList);
        plcp.setRelativeUrl(plcp_UrlList);
        p_DicList.add(plcp);



        /*pmd*/
        Dic_Item pmd = new Dic_Item("pmd");
        pmd.setFullWorld_en("Physical Media Dependent");
        pmd.setFullWorld_ch("物理层媒体依赖层");
        ArrayList<String> pmd_DescList = new ArrayList<String>();
        pmd_DescList.add("1. 802.11协议将服务层进一步划分为 pmd(物理层汇聚过程层)  PMD(物理层媒体依赖层) PLM(物理层管理子层)");
        pmd_DescList.add("2.  pmd(物理层媒体依赖层) 用于识别相关媒体传输的信号所使用的调制以及编码技术 并完成帧的发送");
        ArrayList<String> pmd_UrlList = new ArrayList<String>();
        pmd_UrlList.add("https://baike.baidu.com/item/PMD/23680945");
        pmd_UrlList.add("http://blog.chinaunix.net/uid-26611973-id-3431390.html");
        pmd.setDescList(pmd_DescList);
        pmd.setRelativeUrl(pmd_UrlList);
        p_DicList.add(pmd);


        /*plm*/
        Dic_Item plm = new Dic_Item("plm");
        plm.setFullWorld_en("Physical Layer Manager");
        plm.setFullWorld_ch("物理层管理子层");
        ArrayList<String> plm_DescList = new ArrayList<String>();
        plm_DescList.add("1. 802.11协议将服务层进一步划分为 plm(物理层汇聚过程层)  plm(物理层媒体依赖层) PLM(物理层管理子层)");
        plm_DescList.add("2.  plm(物理层管理子层) 为物理层进行信道选择 和 协调");
        ArrayList<String> plm_UrlList = new ArrayList<String>();
        plm_UrlList.add("http://blog.chinaunix.net/uid-26611973-id-3431390.html");
        plm.setDescList(plm_DescList);
        plm.setRelativeUrl(plm_UrlList);
        p_DicList.add(plm);




        /*p-end*/
        dictory_All.put("p", p_DicList);

    }


    // q-Q
    static {

        ArrayList<Dic_Item> q_DicList = new ArrayList<Dic_Item>();
        /*q-begin*/
        /*qos*/
        Dic_Item qos = new Dic_Item("qos");
        qos.setFullWorld_en("qos");
        qos.setFullWorld_ch("服务质量");
        ArrayList<String> qos_DescList = new ArrayList<String>();
        qos_DescList.add("1.qos是生活中熟悉的字眼，体现了对服务提供者提供的服务的满意程度,是对服务者服务水平的度量和评价(口碑?)");
        qos_DescList.add("2.网络的Qos问题目前以成为国际网络研究最重要，最富有魅力的研究领域");
        ArrayList<String> qos_UrlList = new ArrayList<String>();
        qos_UrlList.add("https://zh.wikipedia.org/wiki/%E6%9C%8D%E5%8A%A1%E8%B4%A8%E9%87%8F");
        qos_UrlList.add("https://baike.baidu.com/item/qos");
        qos.setDescList(qos_DescList);
        qos.setRelativeUrl(qos_UrlList);
        q_DicList.add(qos);



        /*qam*/
        Dic_Item qam = new Dic_Item("qam");
        qam.setFullWorld_en("Quadrature Amplitude Modulation");
        qam.setFullWorld_ch("正交振幅调制");
        ArrayList<String> qam_DescList = new ArrayList<String>();
        qam_DescList.add("1. 8QAM 16QAM 32QAM 64QAM  128QAM  是一种在多个正交载波上进行幅度调制的调制方式 数字标识每个元信号所表示bit个数");
        qam_DescList.add("2.QAM 一般使用 星座图表示");
        ArrayList<String> qam_UrlList = new ArrayList<String>();
        qam_UrlList.add("https://baike.baidu.com/item/QAM");
        qam_UrlList.add("https://zh.wikipedia.org/wiki/%E6%AD%A3%E4%BA%A4%E5%B9%85%E5%BA%A6%E8%B0%83%E5%88%B6");
        qam.setDescList(qam_DescList);
        qam.setRelativeUrl(qam_UrlList);
        q_DicList.add(qam);



        /*q-end*/
        dictory_All.put("q", q_DicList);


    }


    // r-R
    static {

        ArrayList<Dic_Item> r_DicList = new ArrayList<Dic_Item>();
        /*r-begin*/
        /*roaming*/
        Dic_Item roaming = new Dic_Item("roaming");
        roaming.setFullWorld_en("Roaming");
        roaming.setFullWorld_ch("漫游");
        ArrayList<String> roaming_DescList = new ArrayList<String>();
        roaming_DescList.add("1.存在多AP时,STA从一个AP过渡到另一个AP仍保持上层应用程序的网络连接的功能,称为漫游");
        roaming_DescList.add("2.漫游的决定权由STA掌握,而决定STA是否漫游的规则由无线网卡制造商决定,通常包含 信号强度 噪声水平 误码率");
        roaming_DescList.add("3.STA在通信时会持续寻找其他AP,并与范围内的AP进行认证,STA可以与多个AP进行认证,但STA只能与一个AP进行关联");
        roaming_DescList.add("4.WLAN漫游是指STA在同属于一个ESS内的AP之间来回切换过程中,保持已有业务不中断,IP地址不会改变");
        roaming_DescList.add("4.WLAN漫游发生的必要条件 一:AP的安全策略相同 二:AP覆盖范围有重叠区域 ,华为建议AP重叠范围应保持在15%--25%");

        ArrayList<String> roaming_UrlList = new ArrayList<String>();
        roaming_UrlList.add("https://baike.baidu.com/item/%E6%97%A0%E7%BA%BF%E6%BC%AB%E6%B8%B8/981630");
        roaming.setDescList(roaming_DescList);
        roaming.setRelativeUrl(roaming_UrlList);
        r_DicList.add(roaming);



        /*w-end*/
        dictory_All.put("r", r_DicList);


    }


    // s-S
    static {


        ArrayList<Dic_Item> s_DicList = new ArrayList<Dic_Item>();
        /*s_begin*/
        /*sig*/
        Dic_Item sig = new Dic_Item("sig");
        sig.setFullWorld_en("Bluetooth Special Interest Group ");
        sig.setFullWorld_ch("蓝牙技术联盟");
        ArrayList<String> sig_DescList = new ArrayList<String>();
        sig_DescList.add("1.蓝牙Bluetooth最初由爱立信公司于1994年创立");
        sig_DescList.add("2.蓝牙Bluetooth的规范开发,认证由 SIG 蓝牙技术联盟 负责");
        sig_DescList.add("3.蓝牙Bluetooth一般工作在2.4GHz 采用 FHSS技术(跳频技术 Frequency-Hopping Spread Spectrum )");
        sig_DescList.add("4.一般使用79个信道 每个信道带宽1MHz  跳频速率为 1600Hz");
        ArrayList<String> sig_UrlList = new ArrayList<String>();
        sig_UrlList.add("https://baike.baidu.com/item/%E8%93%9D%E7%89%99%E6%8A%80%E6%9C%AF%E8%81%94%E7%9B%9F/9308756?fr=aladdin");
        sig.setDescList(sig_DescList);
        sig.setRelativeUrl(sig_UrlList);
        s_DicList.add(sig);

        /*ss*/
        Dic_Item ss = new Dic_Item("ss");
        ss.setFullWorld_en("Spread Spectrum");
        ss.setFullWorld_ch("扩频技术 射频传输中的扩频技术");
        ArrayList<String> ss_DescList = new ArrayList<String>();
        ss_DescList.add("1.扩频的工作原理就是利用数学函数将信号功率分散到较大的较宽的频率范围中去，使得信号受干扰较小");
        ss_DescList.add("2.扩频是无线局域网WLAN中数据传输使用的技术,把发射信号的能量扩展到更宽的频带内,使其看起来像噪音一样");
        ss_DescList.add("3.数据【调制】操作三步骤   一:信息调制(编码) 二:扩频调制  三:射频调制");
        ss_DescList.add("4.数据【解调制】操作三步骤 一:射频解制       二:扩频解制  三: 信息解调(解码)");
        ss_DescList.add("5.扩频通信的理论基础 香农定理:   C=W*log②(1+S/N)");
        ss_DescList.add("6. C=信道容量    W=信道带宽   S=信号功率  N=噪声功率  S/N=信噪比");
        ArrayList<String> ss_UrlList = new ArrayList<String>();
        ss_UrlList.add("https://baike.baidu.com/item/%E6%89%A9%E9%A2%91%E6%8A%80%E6%9C%AF");
        ss.setDescList(ss_DescList);
        ss.setRelativeUrl(ss_UrlList);
        s_DicList.add(ss);



        /*w-end*/
        dictory_All.put("s", s_DicList);


    }


    // t-T
    static {

        ArrayList<Dic_Item> t_DicList = new ArrayList<Dic_Item>();
        /*t_begin*/
        /*tpc*/
        Dic_Item tpc = new Dic_Item("tpc");
        tpc.setFullWorld_en("Transmit Power Control");
        tpc.setFullWorld_ch("发射功率控制");
        ArrayList<String> tpc_DescList = new ArrayList<String>();
        tpc_DescList.add("1.2003年世界无线电大会(WRC-03)要求所有工作在 5250Mhz---5350MHz  5470MHz---5725MHz 的设备必须支持dfs  和 tpc");
        tpc_DescList.add("2.动态频率选择能监听到来自其他系统的信号,如果检测出雷达信号超过一定的门限值,dfs机制将使得设备切换到其他备用信道,避免干扰雷达");
        tpc_DescList.add("3.tpc功能在 802.11h标准中引入 , tpc是工作于5GHz频段的WLAN系统的必选功能,避免同类无线技术5G频谱上的相互干扰");
        ArrayList<String> tpc_UrlList = new ArrayList<String>();
        tpc_UrlList.add("https://baike.baidu.com/item/%E5%8F%91%E5%B0%84%E5%8A%9F%E7%8E%87%E6%8E%A7%E5%88%B6/5934323");
        tpc.setDescList(tpc_DescList);
        tpc.setRelativeUrl(tpc_UrlList);
        t_DicList.add(tpc);



        /*t_end*/
        dictory_All.put("t", t_DicList);
    }


    // u-U
    static {


    }


    // v-V
    static {
        ArrayList<Dic_Item> v_DicList = new ArrayList<Dic_Item>();
        /*v_begin*/
        /*vht*/
        Dic_Item vht = new Dic_Item("vht");
        vht.setFullWorld_en("Very High Throughput");
        vht.setFullWorld_ch("甚高吞吐量");
        ArrayList<String> vht_DescList = new ArrayList<String>();
        vht_DescList.add("1.802.11ac标准被称为 vht, 工作在5GHZ频段，实现较802.11n更好的数据传输速率 吞吐率 与安全性 ");
        ArrayList<String> vht_UrlList = new ArrayList<String>();
        vht_UrlList.add("https://www.cnblogs.com/usingnamespace-caoliu/p/11159671.html");
        vht.setDescList(vht_DescList);
        vht.setRelativeUrl(vht_UrlList);
        v_DicList.add(vht);



        /*v_end*/
        dictory_All.put("v", v_DicList);


    }

    // w-W
    static {
        ArrayList<Dic_Item> w_DicList = new ArrayList<Dic_Item>();
        /*w-begin*/
        /*wlan*/
        Dic_Item wlan = new Dic_Item("wlan");
        wlan.setFullWorld_en("Wireless Local Area Networks");
        wlan.setFullWorld_ch("无线局域网");
        ArrayList<String> wlan_DescList = new ArrayList<String>();
        wlan_DescList.add("1.WLAN无线局域网是指利用无线通讯技术在一定的局部范围内建立起来的网络,是计算机与无线通讯相结合的产物");
        wlan_DescList.add("2. 【 WPAN(Wireless Personal Area Network) 无线个域网 (范围 < 10米)】 【WLAN(Local) 无线局域网 (范围 < 100米)】 【WMAN(Metro) 无线城域网 (范围 < 5000米)】【WWAN(Wide) 无线广域网 (范围 < 15000米)】");
        ArrayList<String> wlan_UrlList = new ArrayList<String>();
        wlan_UrlList.add("https://baike.baidu.com/item/%E6%97%A0%E7%BA%BF%E5%B1%80%E5%9F%9F%E7%BD%91/176200?fromtitle=WLAN&fromid=612199&fr=aladdin");
        wlan_UrlList.add("https://zh.wikipedia.org/wiki/%E6%97%A0%E7%BA%BF%E5%B1%80%E5%9F%9F%E7%BD%91");
        wlan.setDescList(wlan_DescList);
        wlan.setRelativeUrl(wlan_UrlList);
        w_DicList.add(wlan);



        /*w-end*/
        dictory_All.put("w", w_DicList);
    }

    // x-X
    static {


    }


    // y-Y
    static {


    }

    // z-Z
    static {


    }


    // 0
    static {


    }


    // 1
    static {


    }


    // 2
    static {


    }


    // 3
    static {


    }

    // 4
    static {


    }


    // 5
    static {


    }


    // 6
    static {


    }


    // 7
    static {


    }


    // 8
    static {


    }

    // 9
    static {


    }

    static class Dic_Item {
        char firstChar;  // 首字母  以便分类
        String shortWord;  // 缩写
        String fullWorld_en; //  英文全拼
        String fullWorld_ch; //  中文全拼
        ArrayList<String> descList;  // 相关描述
        ArrayList<String> relativeUrl;  // 相关URL地址

        Dic_Item(String mshortWord) {
            this.shortWord = mshortWord;
            this.firstChar = mshortWord.toLowerCase().trim().charAt(0);
        }

        public char getFirstChar() {
            return firstChar;
        }

        public void setFirstChar(char firstChar) {
            this.firstChar = firstChar;
        }

        public String getShortWord() {
            return shortWord;
        }

        public void setShortWord(String shortWord) {
            this.shortWord = shortWord;
        }

        public String getFullWorld_en() {
            return fullWorld_en;
        }

        public void setFullWorld_en(String fullWorld_en) {
            this.fullWorld_en = fullWorld_en;
        }

        public String getFullWorld_ch() {
            return fullWorld_ch;
        }

        public void setFullWorld_ch(String fullWorld_ch) {
            this.fullWorld_ch = fullWorld_ch;
        }

        public ArrayList<String> getDescList() {
            return descList;
        }

        public void setDescList(ArrayList<String> descList) {
            this.descList = descList;
        }

        public ArrayList<String> getRelativeUrl() {
            return relativeUrl;
        }

        public void setRelativeUrl(ArrayList<String> relativeUrl) {
            this.relativeUrl = relativeUrl;
        }
    }


    // ArrayPrint ==============================Begin

    static int MAX_COUNT_CHAR_IN_ROW = 140;
    static int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 140;

    static public void printArrObject(Object[] objArr, String title) {
        ArrayList<String> curPropStrArr = new ArrayList<String>();
        for (int i = 0; i < objArr.length; i++) {
            if ("".equals(objArr[i].toString())) {
                continue;
            }
            curPropStrArr.add(objArr[i].toString());
        }
        ArrayPrint(curPropStrArr, title);
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


    public static ArrayList<String> sqlitString(String bigString, String sqlitChar) {
        ArrayList<String> fixArr = new ArrayList<String>();
        ArrayList<String> subArr = new ArrayList<String>();
        String[] strArr = bigString.trim().split(sqlitChar.trim());
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].length() > MAX_COUNT_CHAR_IN_ROW) {
                ArrayList<String> subArrA = null;
                if (strArr[i].contains("【") && strArr[i].contains("】")) {
                    subArrA = toSqlitWithhardBlock(strArr[i]);
                } else if (strArr[i].contains(";")) {
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


    public static ArrayList<String> toSqlitWithhardBlock(String mStrList) {
        ArrayList<String> resultList = new ArrayList<String>();
        //【】  【】,
        String mStr = mStrList.trim();

        String pre = mStr.substring(0, mStr.indexOf("【"));
        mStr = mStr.substring(mStr.indexOf("【"));
        resultList.add(pre);
        String end = "";
        if (mStr.endsWith("】")) {
            end = "";
        } else {
            end = mStr.substring(mStr.lastIndexOf("】") + 1);
        }

        mStr = mStr.substring(0, mStr.lastIndexOf("】") + 1);

        while (mStr.contains("】") && mStr.contains("【")) {
            String firstStr = mStr.substring(mStr.indexOf("【"), mStr.indexOf("】") + 1);
            resultList.add(firstStr);
            mStr = mStr.substring(mStr.indexOf("】") + 1);
        }

        if (!"".equals(mStr.trim())) {
            resultList.add(mStr.trim());
        }

        if (!"".equals(end)) {
            resultList.add(end);
        }


//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println("xxx："+i+"  ="+resultList.get(i) +"   mStr="+mStr);
//        }
        return resultList;
    }


    public static ArrayList<String> toMakeListItemLess100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixLengthArr = new ArrayList<String>();

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() < MAX_COUNT_CHAR_IN_ROW) {
                fixLengthArr.add(mStrList.get(i));
            } else {
                String curMaxStr = mStrList.get(i);
                ArrayList<String> fixA = null;
                if (curMaxStr.contains("【") && curMaxStr.contains("】")) {
                    fixA = toSqlitWithhardBlock(curMaxStr);
                } else if (curMaxStr.contains(";")) {
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


    public static int getItemMaxLength(ArrayList<String> mStrList) {
        int itemLength = 0;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > itemLength) {
                itemLength = mStrList.get(i).length();
            }

        }
        return itemLength;
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


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

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
        beginRow = resetBeginRowToDefaultSize(beginRow);
        System.out.println(beginRow);
        for (int i = 0; i < fixStrArr.size(); i++) {
            System.out.println(fixStrArr.get(i));
        }
        endRow = resetEndRowToDefaultSize(endRow);
        System.out.println(endRow);
    }

    static String resetEndRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getPaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═", distance + 3);
        curBeginStr = curBeginStr.replace("╝", paddingString + "╝");
        return curBeginStr;
    }

    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    static String getRepeatString(String repeatSrc, int repeatCount) {
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }


    static String resetBeginRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getPaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═", distance + 3);
        curBeginStr = curBeginStr.replace("╗", paddingString + "╗");
        return curBeginStr;
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
    // ArrayPrint ==============================End


}
