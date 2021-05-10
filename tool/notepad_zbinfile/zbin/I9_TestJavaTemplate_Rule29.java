import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class I9_TestJavaTemplate_Rule29 {

	// Bat_Method_Aera { Bat_Operation_A{MethodA,MethodB}
	// Bat_Operation_B{MethodC,MethodD} }

	static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin";
	static String Cur_Bat_Name = "ztextrule_operation_I9";
	static String Win_Lin_Mac_ZbinPath = "";
	static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;
	static String curOS_ExeTYPE = "";
	// JDK 的路径
	static String JDK_BIN_PATH = "";
	static String Cur_Batch_End = ".bat";

	static void initJDKPath_Windows(String environmentPath) {
		String[] environmentArr = environmentPath.split(";");
		for (int i = 0; i < environmentArr.length; i++) {
			String pathItem = environmentArr[i];
			if (pathItem.contains("jdk") && pathItem.contains("bin")) {
				JDK_BIN_PATH = pathItem;
			}
		}
	}

	enum OS_TYPE {
		Windows, Linux, MacOS
	}

	static void initSystemInfo(String[] args) {

		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("args[" + i + "] = " + args[i]);

			}
		}

		String osName = System.getProperties().getProperty("os.name").toLowerCase();
		String curLibraryPath = System.getProperties().getProperty("java.library.path");
		if (osName.contains("window")) {
			CUR_OS_TYPE = OS_TYPE.Windows;
			Cur_Bat_Name = Cur_Bat_Name + ".bat";
			Cur_Batch_End = ".bat";
			curOS_ExeTYPE = ".exe";
			initJDKPath_Windows(curLibraryPath);
			Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "win_zbin";

		} else if (osName.contains("linux")) {
			CUR_OS_TYPE = OS_TYPE.Linux;
			Cur_Bat_Name = Cur_Bat_Name + ".sh";
			curOS_ExeTYPE = "";
			Cur_Batch_End = ".sh";
			initJDKPath_Linux_MacOS(curLibraryPath);
			Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "lin_zbin";

		} else if (osName.contains("mac")) {
			CUR_OS_TYPE = OS_TYPE.MacOS;
			Cur_Bat_Name = Cur_Bat_Name + ".sh";
			curOS_ExeTYPE = "";
			Cur_Batch_End = ".sh";
			initJDKPath_Linux_MacOS(curLibraryPath);
			Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "mac_zbin";

		}

	}

	static void initJDKPath_Linux_MacOS(String environmentPath) {
		String[] environmentArr = environmentPath.split(":");
		for (int i = 0; i < environmentArr.length; i++) {
			String pathItem = environmentArr[i];
			if (pathItem.contains("jdk") && pathItem.contains("bin")) {
				JDK_BIN_PATH = pathItem;
			}
		}
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
	
  static void showStringList( ArrayList<String> strList) {
		 
		 if(strList == null || strList.size() == 0) {
			 System.out.println("当前调用 showStringList 显示的  ArrayList<String>() 字符串数组为空!! ");
		 return;
		 }
		 
		 int line_num = 0 ;
		 System.out.println("════════════════════ ArrayList<String>  " + strList.size()+" 行字符串" +"════════════════════ ");
		 for (int i = 0; i < strList.size(); i++) {
			 line_num++;
			 String oneStr = strList.get(i);
			 System.out.println("Line["+line_num+"]   "+oneStr);
			
		}
		 System.out.println();
	 }
  
 static void showStringList( ArrayList<String> strList , String tag) {
	 
	 if(strList == null || strList.size() == 0) {
		 System.out.println("当前调用 showStringList 显示的  ArrayList<String>() 字符串数组为空!! ");
	 return;
	 }
	 
	 int line_num = 0 ;
	 System.out.println("════════════════════ ArrayList<String>  " + strList.size()+" 行字符串" +"════════════════════ ");
	 for (int i = 0; i < strList.size(); i++) {
		 line_num++;
		 String oneStr = strList.get(i);
		 System.out.println("tag=["+tag+"] Line["+line_num+"]   "+oneStr);
		
	}
	 System.out.println();
 }
 
 

	public static void main(String[] args) {
		initSystemInfo(args);
		System.out.println("Hello World!");
        //  ═══════════════════ 开始你的测试程序 ═══════════════════


	}

}
