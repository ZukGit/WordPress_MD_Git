import java.io.*;
import java.util.ArrayList;


// 执行命令  grep -rins "hdd_send_association_event: 1382:" .  得到原始的数据 放入输入文件中

public class G0_MacAddress {


    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 读取 G0_oui.txt 文件的内容 路径
    //  网络 地址 : http://standards-oui.ieee.org/oui/oui.txt
    static String G0_oui_File = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"G0_oui.txt";

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

    static  ArrayList<MacInfo>  curMacInfoList = new     ArrayList<MacInfo>();


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



        if (mKeyWordName.size() == 0) {
            System.out.println("当前需要分析的");
            showTip();
            return;
        }


        File MacListFile = new File(G0_oui_File);
        if(!MacListFile.exists()){
            System.out.println("Mac地址产商文件不存在. 路径:" + G0_oui_File);
            System.out.println("请到  http://standards-oui.ieee.org/oui/oui.txt   下载并保存为 " + G0_oui_File );
            return ;
        }


        initMacInfoList(MacListFile);

        System.out.println("MacInfoList 大小: "+ curMacInfoList.size());

        if(curMacInfoList.size() == 0){
            System.out.println("Mac地址产商文件存在.但无法读取到内容  路径:" + G0_oui_File);
            System.out.println("请到  http://standards-oui.ieee.org/oui/oui.txt   下载并保存为 " + G0_oui_File );
            return ;
        }

//        for (int i = 0; i < curMacInfoList.size(); i++) {
//            MacInfo macItem = curMacInfoList.get(i);
//            System.out.println(macItem);
//        }


        for (int i = 0; i < mKeyWordName.size() ; i++) {
            String inputPath = mKeyWordName.get(i);
            String inputABS_Path = curDirPath + File.separator + inputPath;
            File inputFile = new File(inputABS_Path);
            if(!inputFile.exists()){
                System.out.println("当前输入文件不存在");
                continue;
            }

            addVendorInfo2InputFile(inputFile);   // 对输入文件进行逻辑处理

        }
    }


    static  void addVendorInfo2InputFile(File InputFIle){
        ArrayList<String> fixedStrList = new ArrayList<String>();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(InputFIle),"utf-8"));
            String lineContent = "";

            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null ) {
                    continue;
                }

                if(!lineContent.contains(" connected to ")){  // 如果不包含 (hex)  那么  去读取下一行
                    fixedStrList.add(lineContent);
                    continue;
                }


                // 拿到 9c:1c:12:98:44:b1
                String macStr = lineContent.substring(lineContent.indexOf(" connected to ") + " connected to ".length()).trim();
                String macStrOrigin = macStr;
                String macPre = macStr.substring(0,8);
                macStr = macStr.replace(":","");
                macStr = macStr.replace("-","");
                macStr = macStr.toUpperCase();  // 9C1C12  9844B1
                String cVendorMacAddrStr =  macStr.substring(0,6);
//                System.out.println("macStr 1 = "+ macStr);
//                System.out.println("cVendorMacAddrStr 1 = "+ cVendorMacAddrStr);
                MacInfo matchMacInfo =  checkVendorInfoWithMac(cVendorMacAddrStr);
                String fixedStr = "";
                if(matchMacInfo != null){
                    fixedStr = " 【 Vendor-Info: [" + matchMacInfo.mVendorInfo+"] Vendor-Prefix: "+matchMacInfo.mHex_VendorMacAddr_dot_lower+" 】";
                }else{
                    fixedStr = " 【 For [ "+macPre + " ] No Find Vendor Info 】";
                }


                // ./aplogcat-kernel.txt:266094:12-02 18:27:41.666     0     0 I [scheduler_threa][0x8b0f258e5e][18:27:41.666107] wlan: [20091:I:HDD] hdd_send_association_event: 1382: wlan0(vdevid-0): 6e:8b:5e:1c:fd:e8 connected to f8:18:97:7f:1f:2a
                // 转为
                //  11-30 12:22:07 96:88:d0:82:87:8b connected to 4c:12:65:54:44:aa

                String temp1 = lineContent.substring(lineContent.indexOf(":")+1);
                String temp2 = temp1.substring(temp1.indexOf(":")+1);
                String timeStrt = temp2.substring(0,9);
                String selfmacTemp1 = lineContent.substring(0,lineContent.indexOf(" connected to ")).trim();
                String selfMac = selfmacTemp1.substring(selfmacTemp1.length() - 18 ).trim();

                String newContent = timeStrt + " "+ selfMac + " connected to " + macStrOrigin;


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


        writeContentToFile(InputFIle,fixedStrList);

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
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
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


  static  void initMacInfoList(File ouiFile){
      try {
          BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(ouiFile)));
          String lineContent = "";

          while (lineContent != null) {
              lineContent = curBR.readLine();
              if (lineContent == null || lineContent.trim().isEmpty()) {
                  continue;
              }

              if(!lineContent.contains("(hex)")){  // 如果不包含 (hex)  那么  去读取下一行
                  continue;
              }

              // FC-A6-67   (hex)		Amazon Technologies Inc.
              String pre = lineContent.substring(0,lineContent.indexOf("(")).trim();
              String end = lineContent.substring(lineContent.indexOf(")")+1).trim();
              MacInfo macItem = new MacInfo(pre,end);

              curMacInfoList.add(macItem);
          }
          curBR.close();
      } catch (Exception e) {
          System.out.println(" AndroidVersion 读取 异常:" + e.fillInStackTrace());
          e.printStackTrace();
      }

    }


    static  MacInfo checkVendorInfoWithMac(String macPre){
        MacInfo matchMacInfo = null;

        for (int i = 0; i < curMacInfoList.size(); i++) {
            MacInfo macInfoItem = curMacInfoList.get(i);
            String curMacPre = macPre.trim().toUpperCase();
            String macInfoMac = macInfoItem.mHex_VendorMacAddr_Pure.trim().toUpperCase();
            if(macInfoMac.equals(curMacPre)){
                matchMacInfo = macInfoItem;
                return matchMacInfo;
            }
        }

        return matchMacInfo;
    }



    static  class MacInfo{
        String mHex_VendorMacAddr_minus;  // F4-BD-9E
        String mHex_VendorMacAddr_dot;   //  F4:BD:9E
        String mHex_VendorMacAddr_dot_lower;   //  F4:BD:9E
        String mHex_VendorMacAddr_Pure;  //  F4BD9E
        String mVendorInfo ;


        MacInfo(String  cHex_VendorMacAddr_minus , String vendorInfo){
            this.mHex_VendorMacAddr_minus = cHex_VendorMacAddr_minus.trim();
            this.mVendorInfo = vendorInfo.trim();
            this.mHex_VendorMacAddr_dot = mHex_VendorMacAddr_minus.replace("-",":");
            this.mHex_VendorMacAddr_Pure = mHex_VendorMacAddr_minus.replace("-","");
            this.mHex_VendorMacAddr_dot_lower = mHex_VendorMacAddr_dot.toLowerCase();
        }

        @Override
        public String toString() {
            String str ="产商Mac地址前三位:"+ mHex_VendorMacAddr_Pure+"    产商名称: " + mVendorInfo;
            return str;
        }
    }
}
