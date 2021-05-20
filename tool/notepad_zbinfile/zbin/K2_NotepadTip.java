
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class K2_NotepadTip {
    static String OneLine_Pre = "\n════════════════════════════════════════════════";
    static String OneLine_End = "════════════════════════════════════════════════\n";
    static String OneLine = "════════";

    static String User_Home = System.getProperties().getProperty("user.home");
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";




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
       	ShowNotepad_Search_Tip();
        if(CUR_OS_TYPE == OS_TYPE.Windows){
 

        }else if (CUR_OS_TYPE == OS_TYPE.Linux){

        }else if (CUR_OS_TYPE == OS_TYPE.MacOS){

        }



    }


    static void  PrintHead_End( String headStr ){
        System.out.println(OneLine_Pre + ""+headStr+""+ OneLine_End);

    }
    

    static void  PrintRule( String headStr ){
        System.out.println(headStr+"══");

    }
    
    static void  PrintADB_Logcat( String fliterStr ){
    	System.out.println();
        System.out.println("══════════════════"+ " logcat 过滤执行 "+ "══════════════════" );
        System.out.println("adb logcat | grep -E  "+"\""+fliterStr+"\"");

    }
    
    
    static void ShowNotepad_Search_Tip(){
    	PrintHead_End("【Windos----NotePad++】搜索Tip");
        System.out.println(OneLine+" GPS 相关 正则表达式搜索Tip:");
        ArrayList<String> logKeyList = new   ArrayList<String> ();
        logKeyList.add("GPS_STATUS_SESSION_BEGIN");
        logKeyList.add("GPS_STATUS_SESSION_END");
        logKeyList.add("Used In Fix:");
        logKeyList.add("GnssLocationProvider: TTFF:");
        logKeyList.add("UpdateRecord for com.android.phone");
        logKeyList.add("new cell broadcast message");
        logKeyList.add("incoming location: Location");
        logKeyList.add("Sending Location to com.android.phone");
        logKeyList.add("Type Search Begin");
        logKeyList.add("Sending Location to com.google.android.apps.maps");
        logKeyList.add("Sending Location to com.waze");
        logKeyList.add("GnssLocationProvider: SNR");
        logKeyList.add("Sending Location to com.baidu.BaiduMap, Provider: gps");
        logKeyList.add("Sending Location to com.autonavi.minimap, Provider: gps");
        logKeyList.add("Location UpdateRecord for");
        
        StringBuilder printSB = new StringBuilder();
        
        for (int i = 0; i < logKeyList.size(); i++) {
        	String logItem = logKeyList.get(i);
        	printSB.append(logItem.trim()+"|");
		}
        
        String printTip = printSB.toString();
        while(printTip.endsWith("|")) {
        	printTip = printTip.substring(0, printTip.length()-1);

        }
        
        PrintRule(printTip);
        
        PrintADB_Logcat(printTip);
        
        
        
        
        
        
//        PrintRule("GPS_STATUS_SESSION_BEGIN|"
//        		+ "GPS_STATUS_SESSION_END|"
//        		+ "Used In Fix:|"
//        		+ "GnssLocationProvider: TTFF:|"
//        		+ "UpdateRecord for com.android.phone|"
//        		+ "new cell broadcast message|"
//        		+ "incoming location: Location|"
//        		+ "Sending Location to com.android.phone|"
//        		+ "Type Search Begin|"
//        		+ "Sending Location to com.google.android.apps.maps|"
//        		+ "Sending Location to com.waze|"
//        		+ "GnssLocationProvider: SNR|"
//        		+ "Sending Location to com.baidu.BaiduMap, Provider: gps"
//        		+ "Sending Location to com.autonavi.minimap, Provider: gps");
        System.out.println();
    }


}