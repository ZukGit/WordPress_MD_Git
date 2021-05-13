import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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

	
	static {
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
	
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    
	
	static void initSystemInfo(String[] args) {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("args[" + i + "] = " + args[i]);

			}
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
	
    static void writeContentToFile(File file, ArrayList<String> strList) {
    	// PC 以 \r\n 结尾
    	// Unix  以 \n  结尾 
    	// dos2unix 是在末尾把 \r 去掉   所以 文件会变小
    	// unix2dos 是在文件末尾把 \n 之前加上  \r\n  所以文件会变大 
//    	System.setProperty(“line.separator", "\r\n")"
    	String endTagDefault = "\n";  // 默认是 Linux下的 换行符   
    	if(CUR_OS_TYPE == OS_TYPE.Windows) {
    		endTagDefault = "\r\n";    // 如果操作系统是 Windows 那么改变换行符为  \r\n 
    	}
    	StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
        
            sb.append(strList.get(i) + endTagDefault);
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
	
	 public static boolean isNumeric(String string) {
		 String str = string.trim();
	     for (int i = str.length(); --i >= 0; ) {
	         if (!Character.isDigit(str.charAt(i))) {
	             return false;
	         }
	     }
	     return true;
	 }
    
		boolean isStartWith_lower_trim_InArr(ArrayList<String> strList,String strValue) {
			boolean isContain = false;
			if(strList == null  || strList.size() == 0) {
				return isContain;
			}
			for (int i = 0; i < strList.size(); i++) {
				String strItem = strList.get(i).toLowerCase().trim();
				
				if(strItem.startsWith(strValue.toLowerCase().trim())) {
					return true;
				}
				
			}

			return isContain;
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
 
 

	public static void main(String[] args) {
		initSystemInfo(args);
		System.out.println("Hello World!");
        //  ═══════════════════ 开始你的测试程序 ═══════════════════


	}

}
