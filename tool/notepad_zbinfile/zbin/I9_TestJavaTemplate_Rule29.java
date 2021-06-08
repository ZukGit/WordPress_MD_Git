import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.Random;
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
	 
	 
	    @SuppressWarnings("unchecked")
	    public static void ShowMap_String_String(Map<String,String> mMapParam, String tag){
	        Map.Entry<String , String> entryItem;
	        
			 System.out.println("════════════════════ Map<String,String> 大小:" + mMapParam.size()+" " +"════════════════════ ");
              int index = 0;
	        if(mMapParam != null){
	            Iterator iterator = mMapParam.entrySet().iterator();
	            while( iterator.hasNext() ){
	                entryItem = (Map.Entry<String , String>) iterator.next();
	           
	               String key =  entryItem.getKey();   //Map的Key
	               String value =    entryItem.getValue();  //Map的Value
	                System.out.println("Map_index["+index+"]  key=["+key+"]   value=["+value+"]");
	                index++;
	            }
	        }
	    }


	    @SuppressWarnings("unchecked")
	    public static void ShowMap_Int_ArrInt(Map<Integer,ArrayList<Integer>> mMapParam){
	        Map.Entry<Integer , ArrayList<Integer>> entryItem;
	        int Firstindex = 0;
	        if(mMapParam != null){
	            Iterator iterator = mMapParam.entrySet().iterator();
	            while( iterator.hasNext() ){
	                entryItem = (Map.Entry<Integer , ArrayList<Integer>>) iterator.next();
	                Integer year =   entryItem.getKey();   //Map的Key
	                ArrayList<Integer> year_day =  entryItem.getValue();  //Map的Value
	                int SecondIndex = 0;
	                System.out.println("========== MapIndex["+Firstindex+"] "+year + "  =========");
	                for (int i = 0; i <year_day.size() ; i++) {
	                    System.out.println( "MapIndex["+Firstindex+"] ValueIndex=["+SecondIndex+"]"+" key=["  +year + "]  value=["+year_day.get(i)+"] "  );
	                    SecondIndex++;
	                }
	                Firstindex++;
	            }
	        }
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
	
    public static boolean isNumeric(String str) {
    	if(str.contains("#")) {
    		return false;
    	}
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    //判断是否是数字
 static boolean isNumericFirstChar(String s){
        return Character.isDigit(s.charAt(0));
    }
 
 

 public String getRandomColor(){
     Random  rd = new Random();
     int r =  rd.nextInt(256);
     int g =  rd.nextInt(256);
     int b =  rd.nextInt(256);
     String r_str = Integer.toHexString(r);
     String g_str = Integer.toHexString(g);
     String b_str = Integer.toHexString(b);
     if(r_str.length() == 1){
         r_str = "0"+r_str;
     }
     if(g_str.length() == 1){
         g_str = "0"+g_str;
     }
     if(b_str.length() == 1){
         b_str = "0"+b_str;
     }

   return   "#"+r_str+g_str+b_str;

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
 
    public static  Byte calcul8bitStr_To_ObeByte( String bit_8_str ){
 	   
		if(bit_8_str == null || "".equals(bit_8_str) || bit_8_str.length() != 8 ) {
			System.out.println("当前提供的字符串不是8位二进制的字符串");
			return  null;
		}
		String veryStr = bit_8_str.replace("1", "").replace("0", "").trim();
		if(!"".equals(veryStr)) {
			System.out.println("当前提供的字符串不是二进制的字符串");
			return null;
		}
    	
		System.out.println("bit_8_str  ="+ bit_8_str);
    	StringBuilder sb  = new StringBuilder();
        byte targetByte = 0x00;
        byte tempByte = 0x01;
        for (int i = 7; i >= 0; i--) {
        	char charItem = bit_8_str.charAt(i==7?0:(i%7==0?7:(7-i)%7));
        	byte positionByte =  (byte)(tempByte << i);
        	
            
System.out.println("--------------- index_byte["+i+"] char["+charItem+"]-------------------");

          
        	if('1' == charItem) {
        		targetByte = (byte) (targetByte | positionByte);
        	}
            System.out.println("positionByte_["+i+"] = "+showByte(positionByte));
            System.out.println("targetByte_["+i+"] = "+showByte(targetByte));  
        }
        return targetByte;

    }
     
    
	static boolean is16jinzhi(String s) {
		boolean is16jinzhi = false;
		String regex="^[A-Fa-f0-9]+$";

		if(s.matches(regex)){

		System.out.println(s.toUpperCase()+"是16进制数");
		is16jinzhi = true;
		}else{

		System.out.println(s.toUpperCase()+"不是16进制数");
		is16jinzhi = false;
		   }

		return is16jinzhi;
		}
	
    static int calculStrCount(String src, char charTarget) {
        int count = 0;
        String curFixStr = src.trim();

        for (int i = 0; i < curFixStr.length(); i++) {
            char charitem = curFixStr.charAt(i);

            if (charitem == charTarget) {
                count++;
            }
        }

        return count;
    }
    
    public static String toHexString(byte b) {
        return toHexString(toByteArray(b));
    }
    

    public static String toHexString(byte[] array) {
        return toHexString(array, 0, array.length, true);
    }
    
    public static String toHexString(byte[] array, int offset, int length, boolean upperCase) {
        char[] digits = upperCase ? HEX_DIGITS : HEX_LOWER_CASE_DIGITS;
        char[] buf = new char[length * 2];

        int bufIndex = 0;
        for (int i = offset; i < offset + length; i++) {
            byte b = array[i];
            buf[bufIndex++] = digits[(b >>> 4) & 0x0F];
            buf[bufIndex++] = digits[b & 0x0F];
        }

        return new String(buf);
    }

    


    public static String toTenString(int i) {
        String str = i + "";
        int length = str.length();
        if (length < 10) {
            int paddingSize = 10 - length;

            for (int j = 0; j < paddingSize; j++) {
                str = "0" + str;
            }
        }
        str = " " + str;
        //   System.out.println(" 10进制 = "+ str);
        return str;
    }
    
    public static String toHexStringNoTen(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(toHexString(toByteArray(i)));
        return "0x"+sb.toString().trim();
    }

    public static byte[] toByteArray(byte b) {
        byte[] array = new byte[1];
        array[0] = b;
        return array;
    }

    public static byte[] toByteArray(int i) {
        byte[] array = new byte[4];

        array[3] = (byte) (i & 0xFF);
        array[2] = (byte) ((i >> 8) & 0xFF);
        array[1] = (byte) ((i >> 16) & 0xFF);
        array[0] = (byte) ((i >> 24) & 0xFF);

        return array;
    }

    private static int toByte(char c) {
        if (c >= '0' && c <= '9') return (c - '0');
        if (c >= 'A' && c <= 'F') return (c - 'A' + 10);
        if (c >= 'a' && c <= 'f') return (c - 'a' + 10);

        throw new RuntimeException("Invalid hex char '" + c + "'");
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] buffer = new byte[length / 2];

        for (int i = 0; i < length; i += 2) {
            buffer[i / 2] = (byte) ((toByte(hexString.charAt(i)) << 4) | toByte(hexString.charAt(i + 1)));
        }

        return buffer;
    }

    public static StringBuilder appendByteAsHex(StringBuilder sb, byte b, boolean upperCase) {
        char[] digits = upperCase ? HEX_DIGITS : HEX_LOWER_CASE_DIGITS;
        sb.append(digits[(b >> 4) & 0xf]);
        sb.append(digits[b & 0xf]);
        return sb;
    }

    public static String toHexString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(toHexString(toByteArray(i)));
        sb.append(toTenString(i));

        return sb.toString();
    }
    
    private final static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final static char[] HEX_LOWER_CASE_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    public static String dumpHexString(byte[] array) {
        if (array == null) return "(null)";
        return dumpHexString(array, 0, array.length);
    }
    
    static StringBuilder mHexSB = new StringBuilder();
    static StringBuilder mBinarySB = new StringBuilder();
    // 每100个可读字符串 跳转到下一行
//    ArrayList<String> humanReadableList = new  ArrayList<String>();
    static StringBuilder mAsciiSB = new StringBuilder();
    static int byteIndex = 0 ;
    public static String dumpHexString(byte[] array, int offset, int length) {
        if (array == null) return "(null)";
        StringBuilder result = new StringBuilder();

        byte[] line = new byte[16];
        int lineIndex = 0;

        result.append("\n0x");
        result.append(toHexString(offset));

        // 把 int offset 转为 10 进制   10位数值

        for (int i = offset; i < offset + length; i++) {
            if (lineIndex == 16) {
                result.append(" ");
                ArrayList<Byte> byteList= new ArrayList<Byte>();
                for (int j = 0; j < 16; j++) {
                    byteList.add(line[j]);
                    if (line[j] >= ' ' && line[j] <= '~') {
                        result.append(new String(line, j, 1));
                    } else {

                        if(line[j] == 0x00){  // 0x00   NUL(null)  空字符   ●

                            result.append("●");
                        }else if(line[j] == 0x0A){  // 0x0A 换行符  ▲
                            result.append("▲");
                        } else if(line[j] == 0x0D){   // 0D-■ 回车
                            result.append("■");
                        }else if( line[j] == 0x09 ){  // 09 - ◆水平制表符 相当于 Tab
                            result.append("◆");
                        }else if(line[j] == 0x0B ){  // 0x0B -┓  VT (vertical tab)  垂直制表符
                            result.append("┓");
                        }else if(line[j] == 0x1F ){   // █ 0x1F 单元分隔符
                            result.append("█");
                        } else if(line[j] == 0x0C){   //   0x0C ▼  FF (NP form feed, new page)   换页键
                            result.append("▼");
                          }else{
                            result.append(".");
                        }
                    }
                    if(j == 15){  // 最后字符显示一个分割线
                        result.append("  ║ ");  // 这里显示的是 字节信息
                        for (int k = 0; k < byteList.size(); k++) {
                             byte curByte = byteList.get(k);

                             String targetChar = "";
                            if (curByte >= ' ' && curByte <= '~') {
                                targetChar = new String(line, k, 1);
                            } else {
//                                targetChar = ".";

                                if(curByte == 0x00){
//                                    result.append("●");
                                    targetChar = "●";
                                }else if(curByte == 0x0A){  // 0x0A 换行符  ▲
//                                    result.append("♂");
                                    targetChar = "▲";
                                }else if(curByte == 0x0D){   // 0D-■ 回车
//                                    result.append("∠");
                                    targetChar = "■";
                                }else if( curByte == 0x09 ){  // 09 - ◆水平制表符 相当于 Tab
                                    targetChar = "◆";
                                }else if(curByte == 0x0B ){  // 0x0B -┓  VT (vertical tab)  垂直制表符
                                    targetChar = "┓";
                                } else if(curByte == 0x1F ){   // █ 0x1F 单元分隔符
                                    targetChar = "█";
                                } else if(curByte == 0x0C) {   //   0x0C ▼  FF (NP form feed, new page)   换页键
                                    targetChar = "▼";
                                } else{
//                                    result.append(".");
                                    targetChar = ".";
                                }

                            }

                            String byreStr = toHexString(curByte);
                            mAsciiSB.append(targetChar);
                             if(k < 9){
                                 result.append("【 0"+k+"-"+toHexStringNoTen(byteIndex)+"-"+toTenString(byteIndex).trim()+"-"+byreStr+"-"+targetChar+" 】 " );

                             }else{
                                 result.append("【 "+k+"-"+toHexStringNoTen(byteIndex)+"-"+toTenString(byteIndex).trim()+"-"+byreStr+"-"+targetChar+" 】 " );
                             }
                            byteIndex++;
                            result.append(showByte(curByte));
                        }


                    }
                }

                result.append("\n0x");
                mHexSB.append("\n");
                mBinarySB.append("\n");
                result.append(toHexString(i));
                lineIndex = 0;
            }

            byte b = array[i];
            result.append(" ");
            result.append(HEX_DIGITS[(b >>> 4) & 0x0F]);
            result.append(HEX_DIGITS[b & 0x0F]);

            mHexSB.append(" ");
            mHexSB.append(HEX_DIGITS[(b >>> 4) & 0x0F]);
            mHexSB.append(HEX_DIGITS[b & 0x0F]);

            line[lineIndex++] = b;
        }

        if (lineIndex != 0) {  // if (lineIndex != 16)  AOSP 中存在错误  无法打印 最后是 16个字节的情况
            int count = (16 - lineIndex) * 3;
            count++;
            for (int i = 0; i < count; i++) {
                result.append(" ");
            }
            ArrayList<Byte> byteList= new ArrayList<Byte>();
            for (int i = 0; i < lineIndex; i++) {
                byteList.add(line[i]);
                if (line[i] >= ' ' && line[i] <= '~') {
                    result.append(new String(line, i, 1));
                } else {
//                    result.append(".");


                    if(line[i] == 0x00){
                        result.append("●");
                    }else if(line[i] == 0x0A){  // 0x0A 换行符  ▲
                        result.append("▲");
                    }else if(line[i] == 0x0D){   // 0D-■回车
                        result.append("■");
                    }else if( line[i] == 0x09 ){  // 09 - ◆水平制表符 相当于 Tab
                        result.append("◆");
                    }else if(line[i] == 0x0B ){  // 0x0B -┓  VT (vertical tab)  垂直制表符
                        result.append("┓");
                    }else if(line[i] == 0x1F ){   // █ 0x1F 单元分隔符
                        result.append("█");
                    } else if(line[i] == 0x0C) {   //   0x0C ▼  FF (NP form feed, new page)   换页键
                        result.append("▼");
                    } else{
                        result.append(".");
                    }

                }

                if(i == lineIndex -1){  // 最后字符显示一个分割线
                    int paddingSize = 16 -lineIndex;
                    for (int j = 0; j < paddingSize; j++) {
                        result.append(" ");
                    }
                    result.append("  ║ ");  // 这里显示的是 字节信息

                    for (int k = 0; k < byteList.size(); k++) {
                        byte curByte = byteList.get(k);

                        String targetChar = "";
                        if (curByte >= ' ' && curByte <= '~') {
                            targetChar = new String(line, k, 1);
                        } else {
//                            targetChar = ".";

                            if(curByte == 0x00){
                                result.append("●");
                            }else if(curByte == 0x0A){  // 0x0A 换行符  ▲
                                targetChar = "▲";
                            }else if(curByte == 0x0D){   // 0D-■ 回车
                                targetChar = "■";
                            }else if( curByte == 0x09 ){  // 09 - ◆水平制表符 相当于 Tab
                                targetChar = "◆";
                            }else if(curByte == 0x0B ){  // 0x0B -┓  VT (vertical tab)  垂直制表符
                                targetChar = "┓";
                            } else if(curByte == 0x1F ){   // █ 0x1F 单元分隔符
                                targetChar = "█";
                            } else if(curByte == 0x0C) {   //   0x0C ▼  FF (NP form feed, new page)   换页键
                                targetChar = "▼";
                            }  else {
                               targetChar = ".";
                            }
                        }

                        String byreStr = toHexString(curByte);
                        mAsciiSB.append(targetChar);
                        if(k < 9){
                            result.append("【 0"+k+"-"+toHexStringNoTen(byteIndex)+"-"+toTenString(byteIndex).trim()+"-"+byreStr+"-"+targetChar+" 】 " );
                        }else{
                            result.append("【 "+k+"-"+toHexStringNoTen(byteIndex)+"-"+toTenString(byteIndex).trim()+"-"+byreStr+"-"+targetChar+" 】 " );
                        }
                        byteIndex++;
                        result.append(showByte(curByte));
                    }
                }
            }
        }

        return result.toString();
    }
    
    
    public static String showByte(byte byteData){
        StringBuilder sb  = new StringBuilder();
        String result = "";

        for (int i = 7; i >= 0; i--) {

            byte tempByte = byteData;
            tempByte = (byte)(tempByte >> i);
            int value = tempByte & 0x01;
            if(value == 1){
                sb.append("1");
                mBinarySB.append("1");
            }else{
                sb.append("0");
                mBinarySB.append("0");
            }


            if(i == 4){
                sb.append("║");
            }
        }


        return sb.toString()+ " ";

    }
    

    
    static byte[] tryFile2Byte(File target) {
        InputStream input = null;

        int lengthSize;
        byte[] buffer = null;
        // 创建输入输出流对象
        try {
            input = new FileInputStream(target);

            // 获取文件长度
            try {
                lengthSize = input.available();
                // 创建缓存区域
                buffer = new byte[lengthSize];
                // 将文件中的数据写入缓存数组
                input.read(buffer);
                // 将缓存数组中的数据输出到文件


            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (Exception e) {

        } finally {
            if (input != null) {
                try {
                    input.close(); // 关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
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

