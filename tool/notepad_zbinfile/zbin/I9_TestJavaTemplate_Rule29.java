import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class I9_TestJavaTemplate_Rule29 {

	// Bat_Method_Aera { Bat_Operation_A{MethodA,MethodB}
	// Bat_Operation_B{MethodC,MethodD} }

	static String zdesktop =  System.getProperties().getProperty("user.home") + File.separator + "Desktop";
	static String ztemp_dir =  zdesktop+File.separator+"Temp";

	
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
    
    static String getTimeStamp(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    
	static void initSystemInfo(String[] args) {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("args[" + i + "] = " + args[i]);

			}
		}

	}

    public static String clearNumber(String str){
        String result = new String(str);
        result = result.replaceAll("0","");
        result = result.replaceAll("1","");
        result = result.replaceAll("2","");
        result = result.replaceAll("3","");
        result = result.replaceAll("4","");
        result = result.replaceAll("5","");
        result = result.replaceAll("6","");
        result = result.replaceAll("7","");
        result = result.replaceAll("8","");
        result = result.replaceAll("9","");
        return result;
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

    

    static  class StringEscape {
        static final String[][] JAVA_CTRL_CHARS_UNESCAPE = new String[][]{{"\\b", "\b"}, {"\\n", "\n"}, {"\\t", "\t"}, {"\\f", "\f"}, {"\\r", "\r"}};
        static final String[][] JAVA_CTRL_CHARS_ESCAPE = new String[][]{{"\b", "\\b"}, {"\n", "\\n"}, {"\t", "\\t"}, {"\f", "\\f"}, {"\r", "\\r"}};
        static final StringEscape.CharSequenceTranslator ESCAPE_JAVA;
        static final StringEscape.CharSequenceTranslator UNESCAPE_JAVA;


        private StringEscape() {
        }

        public static final String escapeJava(final String input) {
            return ESCAPE_JAVA.translate(input);
        }

        public static String unescapeJava(final String input) {
            return UNESCAPE_JAVA.translate(input);
        }

        static {
            ESCAPE_JAVA = new StringEscape.AggregateTranslator(new StringEscape.CharSequenceTranslator[]{new StringEscape.LookupTranslator(new String[][]{{"\"", "\\\""}, {"\\", "\\\\"}}), new StringEscape.LookupTranslator((CharSequence[][])JAVA_CTRL_CHARS_ESCAPE.clone())});
            UNESCAPE_JAVA = new StringEscape.AggregateTranslator(new StringEscape.CharSequenceTranslator[]{new StringEscape.OctalUnescaper(), new StringEscape.UnicodeUnescaper(), new StringEscape.LookupTranslator((CharSequence[][])JAVA_CTRL_CHARS_UNESCAPE.clone()), new StringEscape.LookupTranslator(new String[][]{{"\\\\", "\\"}, {"\\\"", "\""}, {"\\'", "'"}, {"\\", ""}})});
        }

        private static class UnicodeUnescaper extends StringEscape.CharSequenceTranslator {
            private UnicodeUnescaper() {
                super();
            }

            public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
                if (input.charAt(index) == '\\' && index + 1 < input.length() && input.charAt(index + 1) == 'u') {
                    int i;
                    for(i = 2; index + i < input.length() && input.charAt(index + i) == 'u'; ++i) {
                    }

                    if (index + i < input.length() && input.charAt(index + i) == '+') {
                        ++i;
                    }

                    if (index + i + 4 <= input.length()) {
                        CharSequence unicode = input.subSequence(index + i, index + i + 4);

                        try {
                            int value = Integer.parseInt(unicode.toString(), 16);
                            out.write((char)value);
                        } catch (NumberFormatException var7) {
                            throw new IllegalArgumentException("Unable to parse unicode value: " + unicode, var7);
                        }

                        return i + 4;
                    } else {
                        throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length()) + "' due to end of CharSequence");
                    }
                } else {
                    return 0;
                }
            }
        }

        private static class OctalUnescaper extends StringEscape.CharSequenceTranslator {
            private OctalUnescaper() {
//            super(null);
            }

            public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
                int remaining = input.length() - index - 1;
                StringBuilder builder = new StringBuilder();
                if (input.charAt(index) == '\\' && remaining > 0 && this.isOctalDigit(input.charAt(index + 1))) {
                    int next = index + 1;
                    int next2 = index + 2;
                    int next3 = index + 3;
                    builder.append(input.charAt(next));
                    if (remaining > 1 && this.isOctalDigit(input.charAt(next2))) {
                        builder.append(input.charAt(next2));
                        if (remaining > 2 && this.isZeroToThree(input.charAt(next)) && this.isOctalDigit(input.charAt(next3))) {
                            builder.append(input.charAt(next3));
                        }
                    }

                    out.write(Integer.parseInt(builder.toString(), 8));
                    return 1 + builder.length();
                } else {
                    return 0;
                }
            }

            private boolean isOctalDigit(final char ch) {
                return ch >= '0' && ch <= '7';
            }

            private boolean isZeroToThree(final char ch) {
                return ch >= '0' && ch <= '3';
            }
        }

        private static class AggregateTranslator extends StringEscape.CharSequenceTranslator {
            private final StringEscape.CharSequenceTranslator[] translators;

            public AggregateTranslator(final StringEscape.CharSequenceTranslator... translators) {
//            super(null);
                this.translators = translators == null ? null : (StringEscape.CharSequenceTranslator[])translators.clone();
            }

            public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
                StringEscape.CharSequenceTranslator[] var4 = this.translators;
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    StringEscape.CharSequenceTranslator translator = var4[var6];
                    int consumed = translator.translate(input, index, out);
                    if (consumed != 0) {
                        return consumed;
                    }
                }

                return 0;
            }
        }

        private static class LookupTranslator extends StringEscape.CharSequenceTranslator {
            private final HashMap<String, String> lookupMap = new HashMap();
            private final HashSet<Character> prefixSet = new HashSet();
            private final int shortest;
            private final int longest;

            public LookupTranslator(final CharSequence[]... lookup) {
//            super(null);
                int _shortest = 2147483647;
                int _longest = 0;
                if (lookup != null) {
                    CharSequence[][] var4 = lookup;
                    int var5 = lookup.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        CharSequence[] seq = var4[var6];
                        this.lookupMap.put(seq[0].toString(), seq[1].toString());
                        this.prefixSet.add(seq[0].charAt(0));
                        int sz = seq[0].length();
                        if (sz < _shortest) {
                            _shortest = sz;
                        }

                        if (sz > _longest) {
                            _longest = sz;
                        }
                    }
                }

                this.shortest = _shortest;
                this.longest = _longest;
            }

            public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
                if (this.prefixSet.contains(input.charAt(index))) {
                    int max = this.longest;
                    if (index + this.longest > input.length()) {
                        max = input.length() - index;
                    }

                    for(int i = max; i >= this.shortest; --i) {
                        CharSequence subSeq = input.subSequence(index, index + i);
                        String result = (String)this.lookupMap.get(subSeq.toString());
                        if (result != null) {
                            out.write(result);
                            return i;
                        }
                    }
                }

                return 0;
            }
        }

        private abstract static class CharSequenceTranslator {
            private CharSequenceTranslator() {
            }

            public abstract int translate(CharSequence input, int index, Writer out) throws IOException;

            public final String translate(final CharSequence input) {
                if (input == null) {
                    return null;
                } else {
                    try {
                        StringWriter writer = new StringWriter(input.length() * 2);
                        this.translate(input, writer);
                        return writer.toString();
                    } catch (IOException var3) {
                        throw new RuntimeException(var3);
                    }
                }
            }

            public final void translate(final CharSequence input, final Writer out) throws IOException {
                if (out == null) {
                    throw new IllegalArgumentException("The Writer must not be null");
                } else if (input != null) {
                    int pos = 0;
                    int len = input.length();

                    while(true) {
                        while(pos < len) {
                            int consumed = this.translate(input, pos, out);
                            if (consumed == 0) {
                                char c1 = input.charAt(pos);
                                out.write(c1);
                                ++pos;
                                if (Character.isHighSurrogate(c1) && pos < len) {
                                    char c2 = input.charAt(pos);
                                    if (Character.isLowSurrogate(c2)) {
                                        out.write(c2);
                                        ++pos;
                                    }
                                }
                            } else {
                                for(int pt = 0; pt < consumed; ++pt) {
                                    pos += Character.charCount(Character.codePointAt(input, pos));
                                }
                            }
                        }

                        return;
                    }
                }
            }

            public final StringEscape.CharSequenceTranslator with(final StringEscape.CharSequenceTranslator... translators) {
                StringEscape.CharSequenceTranslator[] newArray = new StringEscape.CharSequenceTranslator[translators.length + 1];
                newArray[0] = this;
                System.arraycopy(translators, 0, newArray, 1, translators.length);
                return new StringEscape.AggregateTranslator(newArray);
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
 
 

	static void SortStringWithName(ArrayList<String> fileList) {
	    Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
	    fileList.sort((o1, o2) -> {
	        //比较的基本原则，拿最小长度的字符串进行比较，若全部相等，则长字符串往后排

	        int len1 = o1.length();
	        int len2 = o2.length();
	        int len = (len1 - len2) <= 0 ? len1 : len2;
	        StringBuilder sb1 = new StringBuilder();
	        StringBuilder sb2 = new StringBuilder();
	        for (int i = 0; i < len; i++) {
	            String s1 = o1.substring(i, i + 1);
	            String s2 = o2.substring(i, i + 1);
	            if (isNumericFirstChar(s1) && isNumericFirstChar(s2)){
	                //取出所有的数字
	                sb1.append(s1);
	                sb2.append(s2);
	                //取数字时，不比较
	                continue;
	            }
	            if (sb1.length() != 0 && sb2.length() != 0){
	                if (!isNumericFirstChar(s1) && !isNumericFirstChar(s2)){
	                    int value1 = Integer.valueOf(sb1.toString());
	                    int value2 = Integer.valueOf(sb2.toString());
	                    return value1 - value2;
	                } else if (isNumericFirstChar(s1)) {
	                    return 1;
	                } else if (isNumericFirstChar(s2)) {
	                    return -1;
	                }
	            }
	            int result = CHINA_COMPARE.compare(s1, s2);
	            if (result != 0) {
	                return result;
	            }
	        }
	        //这一步：是为了防止以下情况：第10  第20，正好以数字结尾，且字符串长度相等
	        if (len1 == len2 && sb1.length() != 0 && sb2.length() != 0) {
	            int value1 = Integer.valueOf(sb1.toString());
	            int value2 = Integer.valueOf(sb2.toString());
	            return value1 - value2;
	        }
	        //若前面都相等，则直接比较字符串的长度，长的排后面，短的排前面
	        return Integer.compare(len1, len2);
	    });
		
	}
	
	
    //判断是否是数字
 static boolean isNumericFirstChar(String s){
        return Character.isDigit(s.charAt(0));
    }
 
 
	static void SortFileWithName(ArrayList<File> fileList) {
	    Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
	    fileList.sort((o1_file, o2_file) -> {
	        //比较的基本原则，拿最小长度的字符串进行比较，若全部相等，则长字符串往后排
	    	String o1 = o1_file.getName();
	    	String o2 = o2_file.getName();
	        int len1 = o1.length();
	        int len2 = o2.length();
	        int len = (len1 - len2) <= 0 ? len1 : len2;
	        StringBuilder sb1 = new StringBuilder();
	        StringBuilder sb2 = new StringBuilder();
	        for (int i = 0; i < len; i++) {
	            String s1 = o1.substring(i, i + 1);
	            String s2 = o2.substring(i, i + 1);
	            if (isNumericFirstChar(s1) && isNumericFirstChar(s2)){
	                //取出所有的数字
	                sb1.append(s1);
	                sb2.append(s2);
	                //取数字时，不比较
	                continue;
	            }
	            if (sb1.length() != 0 && sb2.length() != 0){
	                if (!isNumericFirstChar(s1) && !isNumericFirstChar(s2)){
	                    int value1 = Integer.valueOf(sb1.toString());
	                    int value2 = Integer.valueOf(sb2.toString());
	                    return value1 - value2;
	                } else if (isNumericFirstChar(s1)) {
	                    return 1;
	                } else if (isNumericFirstChar(s2)) {
	                    return -1;
	                }
	            }
	            int result = CHINA_COMPARE.compare(s1, s2);
	            if (result != 0) {
	                return result;
	            }
	        }
	        //这一步：是为了防止以下情况：第10  第20，正好以数字结尾，且字符串长度相等
	        if (len1 == len2 && sb1.length() != 0 && sb2.length() != 0) {
	            int value1 = Integer.valueOf(sb1.toString());
	            int value2 = Integer.valueOf(sb2.toString());
	            return value1 - value2;
	        }
	        //若前面都相等，则直接比较字符串的长度，长的排后面，短的排前面
	        return Integer.compare(len1, len2);
	    });
		
		
	}
	
	static ArrayList<File> getRealFileWithDirAndPointType(File dirFile,ArrayList<String> selectTypeList){
		
		ArrayList<File> targetFileList = new 	ArrayList<File> ();
		if(dirFile ==null || !dirFile.exists() || dirFile.isFile()) {
			return targetFileList;
		}
		
        File[] dir_fileList = dirFile.listFiles();
		
        for (int i = 0; i < dir_fileList.length; i++) {
			File itemFile = dir_fileList[i];
			if(itemFile.isDirectory()) {
				continue;
			}
			if(selectTypeList == null || selectTypeList.size() == 0) {
				targetFileList.add(itemFile);
				continue;
			}
			String fileName_lower = itemFile.getName().toLowerCase();
			
			for (int j = 0; j < selectTypeList.size(); j++) {
				String typeStr = selectTypeList.get(j);
				if(fileName_lower.endsWith(typeStr.trim().toLowerCase())) {
					targetFileList.add(itemFile);
				}

			}
		

			
		}
		return targetFileList;
	}
	
	static String copyWord(String rawStr , int copyTime) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < copyTime; i++) {
    		sb.append(rawStr);
		}
    	return sb.toString();
    	
    }
 
	static ArrayList<File> getRealFileWithDirAndPointType(File dirFile,String type){
		
		ArrayList<File> targetFileList = new 	ArrayList<File> ();
		if(dirFile ==null || !dirFile.exists() || dirFile.isFile()) {
			return targetFileList;
		}
		
        File[] dir_fileList = dirFile.listFiles();
		
        for (int i = 0; i < dir_fileList.length; i++) {
			File itemFile = dir_fileList[i];
			if(itemFile.isDirectory()) {
				continue;
			}
			if(type == null || "".equals(type.trim())) {
				targetFileList.add(itemFile);
				continue;
			}
			String fileName_lower = itemFile.getName().toLowerCase();
			
			
			if(fileName_lower.endsWith(type.trim().toLowerCase())) {
				targetFileList.add(itemFile);
			}
			
		}
		return targetFileList;
	}
 

	public static void main(String[] args) {
		initSystemInfo(args);
		System.out.println("Hello World!");
        //  ═══════════════════ 开始你的测试程序 ═══════════════════


	}

}

