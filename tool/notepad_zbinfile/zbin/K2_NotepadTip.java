
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
public class K2_NotepadTip  implements ClipboardOwner{
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

    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public static void main(String[] args) {
        initSystemInfo();
       String  mSearchTip =  	ShowNotepad_Search_Tip();
       Copy_To_System_ClipBoard(mSearchTip);

       
        if(CUR_OS_TYPE == OS_TYPE.Windows){
 

        }else if (CUR_OS_TYPE == OS_TYPE.Linux){

        }else if (CUR_OS_TYPE == OS_TYPE.MacOS){

        }



    }

    static void  Copy_To_System_ClipBoard( String content ){
        StringSelection stsel = new StringSelection(content);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
    }

    static void  PrintHead_End( String headStr ){
        System.out.println(OneLine_Pre + ""+headStr+""+ OneLine_End);

    }
    

    static void  PrintRule( String headStr ){
        System.out.println(headStr+"══");

    }
    
    static void  PrintADB_Logcat( String fliterStr ){
    	System.out.println();
        System.out.println("══════════════════"+ " grep_cmder_linux logcat 过滤执行 "+ "══════════════════" );
        System.out.println("adb logcat | grep -E  "+"\""+fliterStr+"\"");
    
        System.out.println("══════════════════"+ " findstr_cmd_windows  logcat 过滤执行 "+ "══════════════════" );

        String findstrparam = fliterStr.replace("|", " ");
        
        System.out.println("adb logcat | findstr   "+"\""+fliterStr+"\"");
    }
    
    
    static String ShowNotepad_Search_Tip(){
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
        logKeyList.add("read  gps_open_ind");  //   agps Session 开始
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
        
        // 把这个字符串 复制到 剪切板 
        
//      // 存入剪贴板，并注册自己为所有者
//      // 用以监控下一次剪贴板内容变化
//      StringSelection tmp = new StringSelection(printTip);


      
        
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
        return printTip;
    }

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub
		
	}


}