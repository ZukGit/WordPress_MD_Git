import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class A8_LocalTest {
    public static final ArrayList<String> StringArr = new ArrayList<>();
    public static final ArrayList<String> analysisStringArr = new ArrayList<>();
    public static final ArrayList<AbstractKeyEntry> keyEntryList = new ArrayList<>();

    static {
        keyEntryList.add(new Shutting_down_VM_KeyEntry());   // Shutting down VM APP 奔溃
        keyEntryList.add(new IS_SCREEN_ON_TRUE_KeyEntry());  // Value:IS_SCREEN_ON:  true 亮屏
        keyEntryList.add(new IS_SCREEN_ON_FLASE_KeyEntry()); // Value:IS_SCREEN_ON: false 灭屏
        keyEntryList.add(new SetWifiEnabled_TRUE_KeyEntry()); // setWifiEnabled: true
        keyEntryList.add(new SetWifiEnabled_FALSE_KeyEntry()); // setWifiEnabled: false
        keyEntryList.add(new Updating_SSID_KeyEntry());  // WifiTetherSsidPref: Updating SSID  热点名称
        keyEntryList.add(new onStateChanged_state13_KeyEntry());
        keyEntryList.add(new onStateChanged_state11_KeyEntry());
        keyEntryList.add(new Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry());
        keyEntryList.add(new Launching_fragment_com_android_settings_wifi_WifiSettings());
        keyEntryList.add(new wlan0_CTRL_EVENT_CONNECTED());
        keyEntryList.add(new wlan0_CTRL_EVENT_DISCONNECTED());
        keyEntryList.add(new wlan0_Request_to_deauthenticate());
        keyEntryList.add(new WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent());
        keyEntryList.add(new Trying_to_associate_with_SSID());
        keyEntryList.add(new wlan0_Own_MAC_address());
        //===================Verbose才能打印的Log============
        keyEntryList.add(new handleWifiApStateChange_currentState13_KeyEntry());  // 热点状态打开成功
        keyEntryList.add(new handleWifiApStateChange_currentState13_previousState12__KeyEntry());
        keyEntryList.add(new handleWifiApStateChange_currentState12_previousState11__KeyEntry());
    }


    static class wlan0_Own_MAC_address extends AbstractKeyEntry {
        wlan0_Own_MAC_address() {
            keyword = "wpa_supplicant: wlan0: Own MAC address:";
            explain = "设备 Mac地址 ";
            codePath = null;
        }

        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            String subLogLine = lineContent.substring(lineContent.indexOf("wpa_supplicant: wlan0: Own MAC address:"), lineContent.length());
            if (!logArray.contains(subLogLine)) {
                logArray.add(subLogLine);
                shouldPrint = true;

            } else {
                shouldPrint = false;
            }
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class Trying_to_associate_with_SSID extends AbstractKeyEntry {
        Trying_to_associate_with_SSID() {
            keyword = "wpa_supplicant: wlan0: Trying to associate with SSID";
            explain = "尝试连接该网络";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent extends AbstractKeyEntry {
        WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent() {
            keyword = "WifiStateMachine: FAILURE: LOST_PROVISIONING, NeighborEvent";
            explain = "IPR 丢失邻居事件发生(可能导致断线)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class wlan0_Request_to_deauthenticate extends AbstractKeyEntry {
        wlan0_Request_to_deauthenticate() {
            keyword = "wlan0: Request to deauthenticate";
            explain = "设备 主动发送 断开帧 断开当前网络！ ";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class wlan0_CTRL_EVENT_DISCONNECTED extends AbstractKeyEntry {
        wlan0_CTRL_EVENT_DISCONNECTED() {
            keyword = "wlan0: CTRL-EVENT-DISCONNECTED";
            explain = "WIFI 断开事件发生 ";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class wlan0_CTRL_EVENT_CONNECTED extends AbstractKeyEntry {
        wlan0_CTRL_EVENT_CONNECTED() {
            keyword = "wlan0: CTRL-EVENT-CONNECTED";
            explain = "WIFI 连接成功事件发生 ";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }



    static class Launching_fragment_com_android_settings_wifi_WifiSettings extends AbstractKeyEntry {
        Launching_fragment_com_android_settings_wifi_WifiSettings() {
            keyword = "SubSettings: Launching fragment com.android.settings.wifi.WifiSettings";
            explain = "用户进入WIFI设置界面";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry extends AbstractKeyEntry {
        Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry() {
            keyword = "SubSettings: Launching fragment com.android.settings.wifi.tether.WifiTetherSettings";
            explain = "用户进入热点设置界面";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class onStateChanged_state11_KeyEntry extends AbstractKeyEntry {
        onStateChanged_state11_KeyEntry() {
            keyword = "SoftApCallbackProxy: onStateChanged: state=11";
            explain = "关闭热点成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class onStateChanged_state13_KeyEntry extends AbstractKeyEntry {
        onStateChanged_state13_KeyEntry() {
            keyword = "SoftApCallbackProxy: onStateChanged: state=13";
            explain = "打开热点成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class Updating_SSID_KeyEntry extends AbstractKeyEntry {
        Updating_SSID_KeyEntry() {
            keyword = "WifiTetherSsidPref: Updating SSID";
            explain = "热点名称";
            codePath = null;
        }

        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            String subLogLine = lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());
            if (!logArray.contains(subLogLine)) {
                logArray.add(subLogLine);
                shouldPrint = true;

            } else {
                shouldPrint = false;
            }
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class SetWifiEnabled_TRUE_KeyEntry extends AbstractKeyEntry {
        SetWifiEnabled_TRUE_KeyEntry() {
            keyword = "setWifiEnabled: true";
            explain = "打开 WIFI 开关";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class SetWifiEnabled_FALSE_KeyEntry extends AbstractKeyEntry {
        SetWifiEnabled_FALSE_KeyEntry() {
            keyword = "setWifiEnabled: false";
            explain = "关闭 WIFI 开关";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class IS_SCREEN_ON_TRUE_KeyEntry extends AbstractKeyEntry {
        IS_SCREEN_ON_TRUE_KeyEntry() {
            keyword = "Value:IS_SCREEN_ON: true";
            explain = "开始亮屏";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class IS_SCREEN_ON_FLASE_KeyEntry extends AbstractKeyEntry {
        IS_SCREEN_ON_FLASE_KeyEntry() {
            keyword = "Value:IS_SCREEN_ON: false";
            explain = "开始灭屏";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class Shutting_down_VM_KeyEntry extends AbstractKeyEntry {
        Shutting_down_VM_KeyEntry() {
            keyword = "Shutting down VM";
            explain = "APP崩溃";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    // =============================Verbose解析类=================
    static class handleWifiApStateChange_currentState13_KeyEntry extends AbstractKeyEntry {
        handleWifiApStateChange_currentState13_KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=13";
            explain = "[verbose]打开热点状态成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class handleWifiApStateChange_currentState12_previousState11__KeyEntry extends AbstractKeyEntry {
        handleWifiApStateChange_currentState12_previousState11__KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=12 previousState=11";
            explain = "[verbose]热点正在打开中 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class handleWifiApStateChange_currentState13_previousState12__KeyEntry extends AbstractKeyEntry {
        handleWifiApStateChange_currentState13_previousState12__KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=13 previousState=12";
            explain = "[verbose]热点打开成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }



    abstract static class AbstractKeyEntry {
        public String keyword; // 关键字
        public String explain; // 说明
        public String codePath;  // 代码 该Log打印的代码路径
        public String curLogLineContent;  // 当前记录的那行Log  从Log起始点 开始
        public ArrayList<String> logArray;  // 当前记录的有些不一样的记录行的 集合
        public boolean shouldPrint = true; // 是否应该打印


        AbstractKeyEntry() {
            logArray = new ArrayList<String>();
        }

        abstract public void preCheck(String lineContent);

        abstract public void afterCheck(String lineContent);

        public void analysisLineContent(String lineContent) {
            if (lineContent == null || !lineContent.contains(keyword)) {
                return;
            }
            preCheck(lineContent);
            String checkItem;
            if (lineContent.contains(keyword)) {
                checkItem = new String(lineContent);
                checkItem = checkItem + "    " + explain;
                if (codePath != null) {
                    checkItem = checkItem + "   【Code路径: " + codePath + "  】";
                }
                if (shouldPrint) {
                    analysisStringArr.add(checkItem);
                }

            }
            afterCheck(lineContent);
            return;
        }
    }

    public static void tryAnalysis(File mainFile) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mainFile), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {

                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                // 开始对每行开始分析
                for (AbstractKeyEntry keyEntry : keyEntryList) {
                    keyEntry.analysisLineContent(lineContent);
                }
                //

            }

            curBR.close();
            for (String item : analysisStringArr) {
                System.out.println("分析item =" + item);
            }

        } catch (Exception e) {

        }
    }


    public static void main(String[] args) {
        //===============real-test begin===============
//        String mFilePath = null;
//        if (args.length >= 1) {
//            mFilePath = args[0];
//        } else {
//            System.out.println("input argument is empty ! retry input again!");
//            return;
//        }
        //===============real-test end===============


        //===============local-test begin===============
        String mFilePath = System.getProperty("user.dir") + File.separator + "aplogcat-main.txt";
////        String preString = "<audio> <source src=\"";
////        String endString = "\" /><audio>";
        //===============local-test end===============


        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        if (curFile != null) {

            tryAnalysis(curFile);

        }



/*        if (curFile != null) {

            tryAnalysis(curFile);

            FileReader curReader;
            FileWriter curWriter;
            try {

                curReader = new FileReader(curFile);


                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";
                int indexLine = 0;

                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }
                    indexLine++;
                    newOneLine = indexLine + "      " + oldOneLine;
                    StringArr.add(newOneLine);
                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }*/


    }
}