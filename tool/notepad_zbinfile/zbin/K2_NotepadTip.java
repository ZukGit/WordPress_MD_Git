
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;

public class K2_NotepadTip implements ClipboardOwner {
	static String Title_1_Line_Begin = "\n════════════════════════════════════════════════";
	static String Title_1_Line_End = "════════════════════════════════════════════════\n";

	static String Title_2_Line_Begin = "\n══════════════════════";
	static String Title_2_Line_End = "══════════════════════\n";

	static String OneLine = "════════";

	static String User_Home = System.getProperties().getProperty("user.home");
	static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin";

	// 动态的搜这些文件 而不是写死 为 以后方便扩展 J9_K2_Dynamic_KeyWord_Tel
	// J9_K2_Dynamic_KeyWord_Camera
	// J9_K2_Dynamic_KeyWord_Common.txt // 动态创建 这样的 Txt 文件写入每行关键字
	// J9_K2_Dynamic_KeyWord_GPS.txt
	// J9_K2_Dynamic_KeyWord_WIFI.txt
	// J9_K2_Dynamic_KeyWord_BT.txt
	// J9_K2_Dynamic_KeyWord_NFC.txt
	// 动态的去 zbin 目录下找 以 J9_K2_Dynamic_KeyWord开头 并以 .txt 结尾的文件 并要解析出 type
	static ArrayList<File> J9_K2_Dynamic_FileList = new ArrayList<File>();
	// key 是 GPS BT wifi nfc common camera 等以后需要扩展的 类型
	// value 是 对应的 J9_K2_Dynamic_KeyWord_Common.txt 的文件里面的 内容的扩展
	static HashMap<String, ArrayList<String>> J9_K2_Dynamic_Type_ContentList_Map = new HashMap<String, ArrayList<String>>();

	// 用户输入的 类型 匹配到的 文件中的字符串的列表
	static ArrayList<String> J9_K2_Dynamic_Match_ContentList = new ArrayList<String>();

	enum OS_TYPE {
		Windows, Linux, MacOS
	}

	static String BAT_OR_SH_Point;

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

	// 固定3 当前操作系统的类型
	static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

	private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	static String selectedMode = "";
	static String inputType = "";
	static String ErrorMessage = "";

	static void initInputTypeParams(String[] argsParam) {
		if (argsParam == null || argsParam.length == 0) {
			System.out.println("输入参数为空,默认剪切模块为:  selectedMode=" + selectedMode);
		}

		if (argsParam != null && argsParam.length > 0) {

			// 输入参数的最后一个字符 为 选中的类型 如果 当前类型map 没有这个类型 就把当前选中类型置为空
			selectedMode = argsParam[argsParam.length - 1];
			inputType = argsParam[argsParam.length - 1];
			if (!J9_K2_Dynamic_Type_ContentList_Map.containsKey(selectedMode)) {
				ErrorMessage = "当前输入的类型【" + selectedMode + "】 类型不存在! 将忽略该字符输入!  ";
				System.out.println(ErrorMessage);

			} else {

				J9_K2_Dynamic_Match_ContentList = J9_K2_Dynamic_Type_ContentList_Map.get(selectedMode);

			}
		}

		System.out.println(
				" 当前输入参数的最后一个字符  selectedMode=" + selectedMode + "   关键词数量:" + J9_K2_Dynamic_Match_ContentList.size());

	}

	// 动态的搜这些文件 而不是写死 为 以后方便扩展 J9_K2_Dynamic_KeyWord_Tel
	// J9_K2_Dynamic_KeyWord_Camera
	// J9_K2_Dynamic_KeyWord_Common.txt // 动态创建 这样的 Txt 文件写入每行关键字
	// J9_K2_Dynamic_KeyWord_GPS.txt
	// J9_K2_Dynamic_KeyWord_WIFI.txt
	// J9_K2_Dynamic_KeyWord_BT.txt
	// J9_K2_Dynamic_KeyWord_NFC.txt
	// 动态的去 zbin 目录下找 以 J9_K2_Dynamic_KeyWord开头 并以 .txt 结尾的文件 并要解析出 type

	static void init_J9_K2_Dynamic_FileList() {

		File zbinDir = new File(zbinPath);

		if (!zbinDir.exists() || !zbinDir.isDirectory()) {

			System.out.println("zbin不存在无法执行:  zbinPath=" + zbinPath);
			return;
		}

		File[] zbinFileList = zbinDir.listFiles();

		if (zbinFileList == null || zbinFileList.length == 0) {

			System.out.println("zbin目前子文件数量为0 :  zbinPath=" + zbinPath);
			return;
		}

		for (int i = 0; i < zbinFileList.length; i++) {
			File zbinFileItem = zbinFileList[i];
			if (zbinFileItem.isDirectory()) {
				continue;
			}
			String name = zbinFileItem.getName();

			// J9_K2_Dynamic_KeyWord_WIFI.txt 去除 J9_K2_Dynamic_KeyWord_ 和 .txt 获得类型
			String mType = name;

			if (name.startsWith("J9_K2_Dynamic_KeyWord_") && name.endsWith(".txt")) {
				mType = mType.replace("J9_K2_Dynamic_KeyWord_", "");
				mType = mType.replace(".txt", "");
				mType = mType.replace(".TXT", "").trim();

				J9_K2_Dynamic_FileList.add(zbinFileItem);
				ArrayList<String> matchContentList = ReadFileContentAsList(zbinFileItem);

				if (matchContentList != null && matchContentList.size() > 0) {

					J9_K2_Dynamic_Type_ContentList_Map.put(mType.toLowerCase(), FixArrCode(matchContentList));
				}

			}

		}

	}

	
	// grep -E 搜索 不能包含 ( ) 左右括号 需要转为  . 小数点  并 进行排序
	static ArrayList<String> FixArrCode(ArrayList<String> rawList) {
		
		if(rawList == null || rawList.size() == 0 ) {
			return rawList;
		}
		
		rawList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length() ){
                    return 0;
                }
                return o1.length() > o2.length() ? 1: -1;
            }
        });
		
		ArrayList<String>  fixedArr = new ArrayList<String>();
		
		for (int i = 0; i < rawList.size(); i++) {
			String rawItem = rawList.get(i);
			String fixedItem = rawItem;
			fixedItem = fixedItem.replace("[", ".");
			fixedItem = fixedItem.replace("]", ".");
			fixedItem = fixedItem.replace("(", ".");
			fixedItem = fixedItem.replace(")", ".");
			fixedItem = fixedItem.replace("/", ".");
			fixedArr.add(fixedItem);
		}
		
		return fixedArr;

	}
	public static void main(String[] args) {

		initSystemInfo();

		init_J9_K2_Dynamic_FileList();
		initInputTypeParams(args);

		// 匹配到了一个 type 类型 如 gps bt wifi 对应的文件 列表
		if (J9_K2_Dynamic_Match_ContentList.size() > 0) {

			String matchTypeStr = Show_Type_Search_Tip(selectedMode, J9_K2_Dynamic_Match_ContentList, true);
			Copy_To_System_ClipBoard(matchTypeStr);
			// 打印 匹配到 的 List 对应的 各种操作 然后 默认写入匹配到的类型 全局搜索 写 剪切板
		} else { // 没有匹配到 输入的type 类型 打印出所有的Type 类型 那么 默认写入gps到 剪切板

			// J9_K2_Dynamic_Type_ContentList_Map 遍历Map

			ShowAllTypeMapData();

			// 没有输入 type 默认打印 GPS的 type
			selectedMode = "gps";
			String gpsTypeStr = Show_Type_Search_Tip(selectedMode, J9_K2_Dynamic_Type_ContentList_Map.get(selectedMode), false);
			Copy_To_System_ClipBoard(gpsTypeStr);
	

		}

		
		if (CUR_OS_TYPE == OS_TYPE.Windows) {

		} else if (CUR_OS_TYPE == OS_TYPE.Linux) {

		} else if (CUR_OS_TYPE == OS_TYPE.MacOS) {

		}

		System.out.println("InputMode【"+inputType+"】  SelectedMode【" + selectedMode + "】");

	}

	@SuppressWarnings("unchecked")
	static void ShowAllTypeMapData() {
		Map.Entry<String, ArrayList<String>> entryItem;

		Iterator iterator = J9_K2_Dynamic_Type_ContentList_Map.entrySet().iterator();
		while (iterator.hasNext()) {
			entryItem = (Map.Entry<String, ArrayList<String>>) iterator.next();
			String mType = entryItem.getKey(); // Map的Key Sheet名称 gps bt wifi 之类
			ArrayList<String> entryObjectList = entryItem.getValue(); // Map的Value Sheet 内容

			Show_Type_Search_Tip(mType, entryObjectList, true);

//            System.out.println("══════════ EntryList_Map【" + mType + "(" + entryObjectList.size() + ")】Begin══════════ ");
//            for (int i = 0; i < entryObjectList.size(); i++) {
//            	String keyEntry = entryObjectList.get(i);
//            }

		}

	}

	static void Copy_To_System_ClipBoard(String content) {
		StringSelection stsel = new StringSelection(content);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
	}



	static void showStrList(ArrayList<String> logArr) {
		for (int i = 0; i < logArr.size(); i++) {
			String logItem = logArr.get(i);
			if ("".equals(logItem)) {
				System.out.println();
			} else {
				System.out.println(logItem);
			}

		}

	}

	static String Title_1(String headStr) {
		return Title_1_Line_Begin + "" + headStr + "" + Title_1_Line_End;
	}

	static String Title_2(String headStr) {
		return Title_2_Line_Begin + "" + headStr + "" + Title_2_Line_End;
	}



//    ════════ GPS 相关 notepad++  正则表达式搜索Tip:
//    	GPS_STATUS_SESSION_BEGIN|GPS_STATUS_SESSION_END|Used In Fix:|GnssLocationProvider: TTFF:|
//      new cell broadcast message|incoming location: Location|Type Search Begin|
//      GnssLocationProvider: SNR|read  gps_open_ind|Location UpdateRecord for|
//    reportLocation: Location|BaiduNetworkLocation onBind receive action|
//    UpdateRecord for com.android.phone|Sending Location to com.android.phone|
//    Sending Location to com.waze|Sending Location to com.google.android.apps.maps|
//    Sending Location to com.baidu.BaiduMap, Provider: gps|
//    Sending Location to com.autonavi.minimap, Provider: gps|
//    Sending Location to com.motorola.duogpstest|Sending Location to com.baidu.map.location.test|
//    u0 com.autonavi.minimap.com.autonavi.map.activity.NewMapActivity|
//    targetClass: com.baidu.map.location.BaiduNetworkLocationService|AgpsApn :|
//    SUPL network connection|SUPL: got TCP Error══

	static String Show_Type_Search_Tip(String selectedMode, ArrayList<String> contentList, boolean isShowLog) {

		ArrayList<String> allPrintList = new ArrayList<String>();

		StringBuilder mNotepadKeyWordSearchSB = new StringBuilder();

		for (int i = 0; i < contentList.size(); i++) {
			String oneKeyWord = contentList.get(i).trim();
			if (i == contentList.size() - 1) {
				mNotepadKeyWordSearchSB.append(oneKeyWord);
			} else {
				mNotepadKeyWordSearchSB.append(oneKeyWord + "|");
			}
		}

		// A|B|C|D|E 这样的表达式
		String mKeySB_With_Line_SB = mNotepadKeyWordSearchSB.toString();

		// notepad 开始加入Log
		String title = Title_1("【Windos----NotePad++】搜索 " + selectedMode + "_Tip (正则表达式模式) 数量:"+contentList.size());
		String subTitle_notepad = OneLine + " " + selectedMode + " 相关 notepad++ 正则表达式搜索Tip():";
		allPrintList.add(title);
		allPrintList.add(subTitle_notepad);
		allPrintList.add(mKeySB_With_Line_SB + "════════"); // 添加一个 明显的 结尾 符号
		allPrintList.add("");

		// grep 开始加入Log
		String subTitle_grep = Title_2(" " + selectedMode + " grep -rins -E 过滤执行(cmder目录):");
		allPrintList.add(subTitle_grep);
		allPrintList.add(" grep -rins -E  " + "\"" + mKeySB_With_Line_SB + "\"" + " .  " +" > "+ selectedMode+"_"+getTimeStampMMdd_HHmmss()+".log");
		allPrintList.add("");
		allPrintList.add("");

		// adb logcat_linux 开始加入Log
		String subTitle_adblogcat_linux = Title_2(" " + selectedMode + " grep_cmder_linux logcat 过滤执行");
		allPrintList.add(subTitle_adblogcat_linux);
		allPrintList.add("adb logcat | grep -E  " + "\"" + mKeySB_With_Line_SB + "\"");
		allPrintList.add("");
		allPrintList.add("");

		// adb logcat_win 开始加入Log
		String subTitle_adblogcat_win = Title_2(" " + selectedMode + " findstr_cmd_windows  logcat 过滤执行");
		allPrintList.add(subTitle_adblogcat_win);
		allPrintList.add("adb logcat | findstr   " + "\"" + mKeySB_With_Line_SB + "\"");
		allPrintList.add("");
		allPrintList.add("");

		if (isShowLog) {
			// 打印 ArrayList<String> 出来
			showStrList(allPrintList);
		}

		return mKeySB_With_Line_SB;

	}







	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub

	}

	public static ArrayList<String> ReadFileContentAsList(File mFilePath) {

		if (mFilePath != null && mFilePath.exists()) {
			// System.out.println("存在 当前文件 "+ mFilePath.getAbsolutePath());
		} else {
			System.out.println("不存在 当前文件 " + mFilePath.getAbsolutePath());

			return null;
		}
		ArrayList<String> contentList = new ArrayList<String>();

		try {
			BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
			String oldOneLine = "";
			int index = 1;
			while (oldOneLine != null) {

				oldOneLine = curBR.readLine();
				if (oldOneLine == null) {
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

	static String getTimeStampMMdd_HHmmss() {

		SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	
}