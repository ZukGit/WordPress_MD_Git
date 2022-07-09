


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.text.DecimalFormat;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;




import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.FormChoiceImpl;



import java.security.Key;
import java.security.Security;

// 动态的对当前指定日期("+getTimeStamp_yyyyMMdd()+")的json文件进行动态的数据填充
public class J0_Dynamic_AppendData_JsonTool {


	static ArrayList<String> Rule_Identify_TypeIndexList = new ArrayList<String>();

	static String Cur_Batch_End = ".bat";
	static String Cur_Bat_Name = "zdaily_stock_json_operation_J0";  
	static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin";
	static String J0_File_Path = zbinPath + File.separator + "J0";
	static String Win_Lin_Mac_ZbinPath = "";

	static File J0_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator
			+ "Desktop" + File.separator + "zbin" + File.separator + "J0_Dynamic_Append_Json.properties");
	static InputStream J0_Properties_InputStream;
	static OutputStream J0_Properties_OutputStream;
	static Properties J0_Properties = new Properties();
	static Map<String, String> propKey2ValueList = new HashMap<String, String>();

	static int BYTE_CONTENT_LENGTH_Rule7 = 1024 * 10 * 10; // 读取文件Head字节数常数
	static String strDefaultKey_Rule7 = "zukgit12"; // 8-length

	static String strZ7DefaultKey_PSW_Rule19 = "752025"; // 8-length
	public static byte[] TEMP_Rule7 = new byte[BYTE_CONTENT_LENGTH_Rule7];

	static J0_Dynamic_AppendData_JsonTool mJ0_Object;



	
	
    static String cur_os_zbinPath;
    static String win_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "win_zbin";
    static String lin_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "lin_zbin";
    static String mac_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "mac_zbin";

    static String J0_Data_Dir_Path = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_Data"+File.separator;
    static String J0_GuPiaoLieBiao_Path = zbinPath+File.separator+"J0_股票列表.xlsx";
    static String J0_JiaoYiRiQi_Path = zbinPath+File.separator+"J0_交易日历.xlsx";
    

    
	static {
		try {
			if (!J0_Properties_File.exists()) {
				J0_Properties_File.createNewFile();
			}
			J0_Properties_InputStream = new BufferedInputStream(
					new FileInputStream(J0_Properties_File.getAbsolutePath()));
			J0_Properties.load(J0_Properties_InputStream);
			Iterator<String> it = J0_Properties.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				// System.out.println("key:" + key + " value: " +
				// J0_Properties.getProperty(key));
				propKey2ValueList.put(key, J0_Properties.getProperty(key));
			}
			J0_Properties_InputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void setProperity() {
		try {
			J0_Properties_OutputStream = new BufferedOutputStream(
					new FileOutputStream(J0_Properties_File.getAbsolutePath()));
			J0_Properties.store(J0_Properties_OutputStream, "");
			J0_Properties_OutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	enum OS_TYPE {
		Windows, Linux, MacOS
	}

	// JDK 的路径
	static String JDK_BIN_PATH = "";

	static File J0_Temp_Text_File = new File(
			System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"
					+ File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + "_Temp_Text.txt");

	static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;
	static String curOS_ExeTYPE = "";
	static ArrayList<String> mKeyWordName = new ArrayList<>();

	// 当前Shell目录下的 文件类型列表 抽取出来 通用
	static HashMap<String, ArrayList<File>> CurDirFileTypeMap = new HashMap<String, ArrayList<File>>();;

	
	
	
    // 在 J0_Data 中的 所有的 文件的集合
    static ArrayList<File> allJ0DataFileList = new ArrayList<File>();
    // 在 J0_Data 中的 所有的 json 文件的集合
    static ArrayList<File> allJsonFileList = new ArrayList<File>();

    // 在 J0_Data 中的 所有的 daily.json 文件的集合   比如 day_2022_0314.json 
    static ArrayList<File> allDailyJsonFileList = new ArrayList<File>();
    

	static boolean is_init_J0Data_File() {
		File J0_Data_DirFile = new File(J0_Data_Dir_Path);
		
		if(!J0_Data_DirFile.exists()) {
			System.out.println("stock_json 存储的位置 J0_Data_DirFile ="+J0_Data_DirFile.getAbsolutePath()+" 不存在 请检查!");
			return false;
			
		}
		
		
		File[] J0Data_FileArr = J0_Data_DirFile.listFiles();
		if(J0Data_FileArr == null || J0Data_FileArr.length == 0 ) {
			System.out.println("stock_json 存储的位置 J0_Data_DirFile ="+J0_Data_DirFile.getAbsolutePath()+" 目录下 文件为空 没有 json_daily.json 文件 请检查");
			return false;
			
		}
		
		for (int i = 0; i < J0Data_FileArr.length; i++) {
			File fileItem = J0Data_FileArr[i];
			allJ0DataFileList.add(fileItem);
			String fileName = fileItem.getName().toLowerCase();
			if(fileName.endsWith(".json")) {
				
				allJsonFileList.add(fileItem);
				
				String fixName = fileName.replace(".json","").replace("day","").replace("_","").trim();
				
				if(isNumeric(fixName) && fixName.length() == 8) {
					
					allDailyJsonFileList.add(fileItem);
				}

			}

		}
		
		
		if(allJ0DataFileList.size() == 0 || allJsonFileList.size() == 0 || allDailyJsonFileList.size() == 0 ) {
			
			System.out.println("allJ0DataFileList.size() = "+ allJ0DataFileList.size());
			System.out.println("allJsonFileList.size() = "+ allJsonFileList.size());
			System.out.println("allDailyJsonFileList.size() = "+ allDailyJsonFileList.size());
			
            System.out.println("当前需要操作的 J0_Data 目录 解析出的文件集合为0  请 检查!");
			return false;
			
			
		}
		
		
		File oldest_json_File = null;
		File lastest_json_File = null;	
		
		// 从大到小 排列
		if(allDailyJsonFileList.size() > 0) {
			allDailyJsonFileList.sort(new Comparator<File>() {
	            @Override
	            public int compare(File o1, File o2) {
	            	String fileName_1 = o1.getName().toLowerCase();
	            	String fileName_2 = o2.getName().toLowerCase();
					String fixName_1 = fileName_1.replace(".json","").replace("day","").replace("_","").trim();
					String fixName_2 = fileName_2.replace(".json","").replace("day","").replace("_","").trim();

					
					if(!isNumeric(fixName_2) && !isNumeric(fixName_1)) {
						return 0;
					}
					
					
					if(!isNumeric(fixName_1)) {
						return 1;
					}
					
					if(!isNumeric(fixName_2)) {
						return 1;
					}
					
					
					int fileName1_int  = Integer.parseInt(fixName_1);
					int fileName2_int  = Integer.parseInt(fixName_2);
	            			
					if(fileName1_int > fileName2_int) {
						return -1;
					}
					
          			
					if(fileName1_int == fileName2_int) {
						return 0;
					}
					
          			
					if(fileName1_int < fileName2_int) {
						return 1;
					}

	                return o1.compareTo(o2);
	            }
	        });
			
			
		}
		
		lastest_json_File =  allDailyJsonFileList.get(0);
		oldest_json_File = allDailyJsonFileList.get(allDailyJsonFileList.size()-1);
		
		
		System.out.println("A.在J0_Data中的所有的文件的集合        allJ0DataFileList.size() = "+ allJ0DataFileList.size());
		System.out.println("B.在J0_Data中的所有的json文件的集合    allJsonFileList.size() = "+ allJsonFileList.size());
		System.out.println("C.在J0_Data 中的所有的daily文件的集合  allDailyJsonFileList.size() = "+ allDailyJsonFileList.size() +" 最新文件:"+(lastest_json_File==null?"null":lastest_json_File.getName())+"  最旧文件:"+(oldest_json_File==null?"null":oldest_json_File.getName()));
		
		
		return true;
	}
	
	static void initSystemInfo(String[] args) {
		
		
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("args[" + i + "] = " + args[i]);
				if (i == 0) {
					curDirPath = args[i];
				} else {
					mKeyWordName.add(args[i]);
					Rule_Identify_TypeIndexList.add(args[i]);
				}
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

	static void initJDKPath_Windows(String environmentPath) {
		String[] environmentArr = environmentPath.split(";");
		for (int i = 0; i < environmentArr.length; i++) {
			String pathItem = environmentArr[i];
			if (pathItem.contains("jdk") && pathItem.contains("bin")) {
				JDK_BIN_PATH = pathItem;
			}
		}
	}

	static String curDirPath = ""; // 当前 SHELL 所在目录 默认是main中的第一个 arg[0] 就是shell路径
	static File curDirFile;

	private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

	public static String clearChinese(String lineContent) {
		if (lineContent == null || lineContent.trim().isEmpty()) {
			return null;
		}
		Pattern pat = Pattern.compile(REGEX_CHINESE);
		Matcher mat = pat.matcher(lineContent);
		return mat.replaceAll(" ");
	}

	void InitRule() {

		realTypeRuleList.add(new Append_Day_3_5_10_15_FlowRate_Rule_1());
		
		
		// initrule
	}

	
	
	
	



	// 动态的对当前指定日期 的 json 文件进行 填充数据的操作
	// 输入一个指定的日期参数 找到这个参数对应的 json文件 然后 依次读取 
	 // 前3 天 的 json文件(含自身文件)   动态计算 这3天的 涨幅的和 填充到 参数文件
	 // 前5 天 的 json文件(含自身文件)   动态计算 这5天的 涨幅的和 填充到 参数文件
	 // 前10 天 的 json文件(含自身文件)   动态计算 这10天的 涨幅的和 填充到 参数文件
	 // 前15 天 的 json文件(含自身文件)   动态计算 这15天的 涨幅的和 填充到 参数文件

	// 前30 天 的 json文件(含自身文件)   动态计算 这5天的 涨幅的和 填充到 参数文件
	// 前60 天 的 json文件(含自身文件)   动态计算 这10天的 涨幅的和 填充到 参数文件
	// 前90 天 的 json文件(含自身文件)   动态计算 这15天的 涨幅的和 填充到 参数文件
	// 前一年 的 json文件(含自身文件)   动态计算 这15天的 涨幅的和 填充到 参数文件

	class Append_Day_3_5_10_15_FlowRate_Rule_1 extends  Basic_Rule{
		
		String inputDayStr ;  // 用户的输入的 参数  day_20220630   指定需要执行的日期 
		int inputDayInt;   // 用户输入的日期的int值 方便计算 
		File matchJsonDayFile ;   // 找到 对应的day_2022_0630.json 文件  如果没有 那么报错
		
		// ts_code , ArrayList<RiXianXingQingvShiJianWeiXu>
		// key 是 ts_code     value 是从 json 读取到的 对应相同的 RiXianXingQingvShiJianWeiXu集合列表
		HashMap<String,ArrayList<RiXianXingQingvShiJianWeiXu>> matchJsonArrMap;
	    JSONObject mTargetDay_JsonObject ; // 读取到的 目标 daily json 文件的 JSONObject
	    
	     // 从 mTargetDay_JsonObject 读取到的 日线行情数据  需要动态改里面的内容 
	     List<RiXianXingQingvShiJianWeiXu> mTargetDay_RiXianList= null ;
	      String mAppendFixedJsonKey = "日线行情v时间为序";
	     
		Append_Day_3_5_10_15_FlowRate_Rule_1(){
			super("#", 1, 4); //
			inputDayInt = -1 ;
			matchJsonDayFile = null ;
			matchJsonArrMap = new HashMap<String,ArrayList<RiXianXingQingvShiJianWeiXu>>();
		}
		
		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// TODO Auto-generated method stub
			
			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i).trim();
				if(paramItem.startsWith("day_")) {
					inputDayStr = paramItem.replace("day_", "");
					if(isNumeric(inputDayStr) && inputDayStr.length() == 8) {
						inputDayInt = Integer.parseInt(inputDayStr);
						
					}
			
				}

			}
			
			if(inputDayInt != -1) {
			String  matchFileName = calcDayJsonName(inputDayInt);
			matchJsonDayFile = new File(J0_Data_Dir_Path+File.separator+matchFileName);
			
			}
			
			System.out.println("inputDayStr = "+ inputDayStr +"     inputDayInt = "+ inputDayInt +"  matchJsonDayFile="+(matchJsonDayFile==null?"null":matchJsonDayFile.getAbsolutePath()));
			
			if(matchJsonDayFile == null || !matchJsonDayFile.exists()){
				
				System.out.println("当前输入的参数日期 inputDayStr+"+inputDayStr+"  无法匹配到本地的 day_"+inputDayStr+".json"+"文件  请检查是否存在该文件!");
			    return false;
			 }
			
			return super.initParamsWithInputList(inputParamList);
		}
		
		
		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub
			
		ArrayList<File> matchTargetFileList = 	getTargetDayAsLastFileList(matchJsonDayFile);
		
		if(matchTargetFileList == null || matchTargetFileList.size() == 0) {
			
			System.out.println("当前 依据匹配到的文件 "+matchJsonDayFile+" 计算得到的 json数据文件列表为空 请检查!");
			return null;
		}
		System.out.println("当前 依据匹配到的文件 "+matchJsonDayFile+" 计算得到的 json数据文件列表大小="+matchTargetFileList.size()  );


			matchTargetFileList.sort(new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {


					String mDailyNumberStr_o1 = o1.getName().toLowerCase().replace("day","").replace("_","")
							.replace(" ","").replace("json","")
							.replace(".","").trim();

					String mDailyNumberStr_o2 = o2.getName().toLowerCase().replace("day","").replace("_","")
							.replace(" ","").replace("json","")
							.replace(".","").trim();

					if(isNumeric(mDailyNumberStr_o1) && isNumeric(mDailyNumberStr_o2) ){

						int curDayInt_o1 = 	Integer.parseInt(mDailyNumberStr_o1);
						int curDayInt_o2 = 	Integer.parseInt(mDailyNumberStr_o2);
						if(curDayInt_o1 > curDayInt_o2){
							return -1;
						} else if(curDayInt_o1 == curDayInt_o2){
							return 0;
						}else{

							return 1;
						}

					}

					return o1.getName().compareTo(o2.getName());
				}
			});
		
		for (int i = 0; i < matchTargetFileList.size(); i++) {
			File fileItem  = matchTargetFileList.get(i);
			System.out.println("matchJson["+(i+1)+"]["+matchTargetFileList.size()+"] : "+ fileItem.getAbsolutePath());
		}
		
		
        StringBuilder jsonSB = new StringBuilder();
        tryReadJsonFromFile(jsonSB,matchJsonDayFile);
        
         mTargetDay_JsonObject =    JSONObject.parseObject(jsonSB.toString());
        
        // 从 targetDay Json 文件中读取到 的 每日行情数据的 列表  
   
        
  

        if(mTargetDay_JsonObject.size() > 0){

            ArrayList<String> keyList =new ArrayList<String>();
            keyList.addAll( mTargetDay_JsonObject.keySet());

            for (int i = 0; i < keyList.size(); i++) {
                String keyItem = keyList.get(i);
                JSONArray sheetValueJSONArray = (JSONArray)mTargetDay_JsonObject.getJSONArray(keyItem);
  
                System.out.println("JSON文件  JsonKey=["+keyItem+"]  valuse.size=["+sheetValueJSONArray.size()+"]");
                
                if(!mAppendFixedJsonKey.equals(keyItem)) {
                	continue;
                }
                
                for (int j = 0; j < sheetValueJSONArray.size(); j++) {
                	Object obj = sheetValueJSONArray.get(j);
//                	System.out.println(obj);
                	
				}
                
                mTargetDay_RiXianList = JSONObject.parseArray(sheetValueJSONArray.toString(), RiXianXingQingvShiJianWeiXu.class);
            
                if(mTargetDay_RiXianList == null) {
                	System.out.println("把 jsonArr 的字符串 转为 List<Stock_Daily_Basic> 数组的过程失败! ");
                }else {
                	
                	System.out.println("把 jsonArr 的字符串 转为 List<Stock_Daily_Basic> 数组的过程成功! mLastDailyStockList.size()="+mTargetDay_RiXianList.size());
                
                	System.out.println("jsonArr[0] = "+mTargetDay_RiXianList.get(0));

                	System.out.println("jsonArr[end] = "+mTargetDay_RiXianList.get(mTargetDay_RiXianList.size()-1));
           
//                	
//                	jsonArr[0] = Stock_Daily_Basic [cname=泰和科技, ts_code=300801.SZ, trade_date=20220303, close=23.88, turnover_rate=3.0616, turnover_rate_f=3.8774, volume_rati
//                			o=0.81, pe=26.4256, pe_ttm=37.0359, pb=2.6653, ps=3.3737, ps_ttm=2.9031, dv_ratio=1.0469, dv_ttm=1.0469, total_share=21600.0, float_share=8348.276, free_share=6591.826, total_mv=515808.0, circ_mv=199356.8309]
//                	jsonArr[end] = Stock_Daily_Basic [cname=null, ts_code=689009.SH, trade_date=20220303, close=55.66, turnover_rate=0.1885, turnover_rate_f=1.4485, volume_ratio=0.8, pe=536.2641, pe_ttm=94.9686, pb=9.2855, ps=6.5638, ps_ttm=4.3172, dv_ratio=, dv_ttm=, total_share=70788.721, float_share=44299.4824, free_share=5766.414, total_mv=3940100.2109, circ_mv=2465709.1904]
//                					
            	
                }
            
            }
        }
        
        
        
        //  把 所有的  需要 读取的文件集合 又 作为 一个集合
        // 最多 读取到  15  json 文件  需要 解析这些 json  转为 
        // HashMap<String,ArrayList<RiXianXingQingvShiJianWeiXu>> matchJsonArrMap; 的 一项数据
//		ArrayList<File> day15_FileList = getSubList(15, matchTargetFileList);
//		initRiXianXingQingMap(mAppendFixedJsonKey ,day15_FileList);

		//
		ArrayList<File> oneYear_FileList = getSubList(365, matchTargetFileList);   //  最多一年的数据
		initRiXianXingQingMap(mAppendFixedJsonKey ,oneYear_FileList);
	
		
		
		
		// 传递 json的key   对应匹配的 rixianItem 里面包含 000001.SZ 是唯一性凭证  第三个是需要 当前需要计算的json 的文件的列表 
		// calcul_sum_pct_chg(mAppendFixedJsonKey,rixianItem,day3_FileList)
		
		
        // 试一试把  rootRiXianList 的 每个item 的 day3 
			System.out.println( "  size="+(mTargetDay_RiXianList == null ? 0:mTargetDay_RiXianList.size()) + " mTargetDay_RiXianList =  "+mTargetDay_RiXianList);

			if(mTargetDay_RiXianList != null && mTargetDay_RiXianList.size() > 0 ) {

		int oneYearJsonCount =	getMatchYearJsonFileCount(oneYear_FileList,inputDayInt);
        	for (int i = 0; i < mTargetDay_RiXianList.size(); i++) {
        		
        		RiXianXingQingvShiJianWeiXu rixianItem = mTargetDay_RiXianList.get(i);
        		
        		String match_tscode = rixianItem.getTs_code();
        		
        		ArrayList<RiXianXingQingvShiJianWeiXu> match_rixianList = matchJsonArrMap.get(match_tscode);
        		if(match_rixianList == null) {
        			continue;
        		}
        		
        		
        		
        		// "day3_pct_chg":0.2802000000000001,
        		
        		ArrayList<RiXianXingQingvShiJianWeiXu> day3_rixianList = getSubList_RiXian(3, match_rixianList);
        		
           		ArrayList<RiXianXingQingvShiJianWeiXu> day5_rixianList = getSubList_RiXian(5, match_rixianList);
        		
           		ArrayList<RiXianXingQingvShiJianWeiXu> day10_rixianList = getSubList_RiXian(10, match_rixianList);
        		
           		ArrayList<RiXianXingQingvShiJianWeiXu> day15_rixianList = getSubList_RiXian(15, match_rixianList);


				ArrayList<RiXianXingQingvShiJianWeiXu> day30_rixianList = getSubList_RiXian(30, match_rixianList);

				ArrayList<RiXianXingQingvShiJianWeiXu> day60_rixianList = getSubList_RiXian(60, match_rixianList);

				ArrayList<RiXianXingQingvShiJianWeiXu> day90_rixianList = getSubList_RiXian(90, match_rixianList);

				ArrayList<RiXianXingQingvShiJianWeiXu> one_year_rixianList = getSubList_RiXian(oneYearJsonCount, match_rixianList);

        		// 每次 都 要读 json   应该只读 一次
        	
        		rixianItem.setDay3_pct_chg(cal_sum_pct_chg(day3_rixianList));
        		rixianItem.setDay5_pct_chg(cal_sum_pct_chg(day5_rixianList));
        		rixianItem.setDay10_pct_chg(cal_sum_pct_chg(day10_rixianList));
        		rixianItem.setDay15_pct_chg(cal_sum_pct_chg(day15_rixianList));
				rixianItem.setDay30_pct_chg(cal_sum_pct_chg(day30_rixianList));
				rixianItem.setDay60_pct_chg(cal_sum_pct_chg(day60_rixianList));
				rixianItem.setDay90_pct_chg(cal_sum_pct_chg(day90_rixianList));

				double cal_year_sum_pct_chg = cal_sum_pct_chg(one_year_rixianList);
				System.out.println(match_tscode+"  oneYearJsonCount="+oneYearJsonCount+"  cal_year_sum_pct_chg = "+cal_year_sum_pct_chg+"   one_year_rixianList.size()="+one_year_rixianList.size() +"  match_rixianList.size()="+match_rixianList.size());
				rixianItem.setYear_pct_chg(cal_year_sum_pct_chg);
			}
        	
        }


        
        ValueFilter filter = new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if(value instanceof BigDecimal || value instanceof Double || value instanceof Float){
                    return new BigDecimal(value.toString());
                }
                return value;
            }
        };
        
//        ParserConfig
//        SerializeConfig 
        // 	// "day3_pct_chg":0.2802000000000001,   解决  double 显示精度的问题 
        JSONArray  rixian_jsonarr =   JSONArray.parseArray(JSON.toJSONString(mTargetDay_RiXianList,filter, new SerializerFeature[0]));
        


        
        mTargetDay_JsonObject.remove(mAppendFixedJsonKey);  // 去除原先的 key 以及对应的数据
        mTargetDay_JsonObject.put(mAppendFixedJsonKey, rixian_jsonarr);  // 添加新的 day3 day5 xx 的 key 
        
 // 写入临时 文件 查看       
//        writeContentToFile(J0_Temp_Text_File, mTargetDay_JsonObject.toJSONString());
//        System.out.println(J0_Temp_Text_File.getAbsolutePath());
        
        
        // 写回 匹配到的  json 日期文件 使得 目录多出 day3 day5 day 10 day 15 数据
        writeContentToFile(matchJsonDayFile, mTargetDay_JsonObject.toJSONString());
        System.out.println(matchJsonDayFile.getAbsolutePath());
       System.out.println(" 动态写入 day3 day5 day 10 day 15   day30 day60  day90  dayyear  数据 数据完成! ");
		
		// 1.开始读取 匹配到的 matchJsonDayFile json 文件  作为  母数据源
		// 2.开始读取前三个 json 文件 动态算出 day3_flowrate   然后赋值给  母数据源
		// 3.      前五个	
		// 4.      前10个         
		// 5.      前15个                 
		// 6. 把母数据重新写会  json    这样 就动态的往  json 文件 添加了 数据
			
			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}
		
		@Override
		String simpleDesc() {

			return "\n"  +  Cur_Bat_Name + " #_" + rule_index+" day_"+getTimeStamp_yyyyMMdd()+" "+"    ###   动态的对当前指定日期("+getTimeStamp_yyyyMMdd()+")的json文件进行动态的数据填充" ;
			
		}


		int getMatchYearJsonFileCount( ArrayList<File> oneYear_FileList ,int dayFlag ){  // // 从输入获得 年份
			int curYearFileCount = 0 ;
			if(oneYear_FileList == null || oneYear_FileList.size() == 0){
				return curYearFileCount;
			}


			// 从输入获得 年份  (inputDayInt/10000)
//			int yearBeginInt = getCurrentYYYY()*10000+101;   // 获得现在的年份
			int yearBeginInt = (dayFlag/10000)*10000+101;  // 从输入获得 年份
			for (int i = 0; i < oneYear_FileList.size() ; i++) {
				File jsonFileItem = oneYear_FileList.get(i);
				String mDailyJsonName = jsonFileItem.getName().toLowerCase();
				String mDailyNumberStr = mDailyJsonName.replace("day","").replace("_","")
						.replace(" ","").replace("json","")
						.replace(".","").trim();

				System.out.println(" getCurrentYearJsonFileCount  mDailyNumberStr["+i+"]="+mDailyNumberStr);
				if(isNumeric(mDailyNumberStr)){

				int curFileDayInt = 	Integer.parseInt(mDailyNumberStr);

				if(curFileDayInt >= yearBeginInt ){
					curYearFileCount++;
				}

				}

				// day_2022_0104.json


			}


			System.out.println(" getCurrentYearJsonFileCount  curYearFileCount="+curYearFileCount);
			return curYearFileCount;


		}



		// 每次 都 要读 json   应该只读 一次
//	
//		rixianItem.setDay3_pct_chg(cal_sum_pct_chg(day3_rixianList));
//		
		double  cal_sum_pct_chg(ArrayList<RiXianXingQingvShiJianWeiXu> mRiXianList){
			double sum_pct_chg = 0.0d;
			StringBuilder logSB = new StringBuilder();
			String tscode = null;
			if(mRiXianList == null) {
				return 0.0d;
			}
			for (int i = 0; i < mRiXianList.size(); i++) {
				RiXianXingQingvShiJianWeiXu  rixian_item = mRiXianList.get(i);
				tscode = rixian_item.getTs_code();
				sum_pct_chg += rixian_item.getPct_chg();
				
				if(i == mRiXianList.size()-1) {
					if(rixian_item.getPct_chg() < 0) {
						logSB.append("("+rixian_item.getPct_chg()+")");
					}else {
						logSB.append(rixian_item.getPct_chg());
					}
				}else {
					
					if(rixian_item.getPct_chg() < 0) {
						logSB.append("("+rixian_item.getPct_chg()+")"+"+");
					}else {
						logSB.append(rixian_item.getPct_chg()+"+");
					}
					
				}


			}
			// System.out.println("tscode="+tscode+"   "+ logSB.toString().replace(" ", ""));
			return sum_pct_chg;
			
		}
		
		
		// Map<ts_code , ArrayList<RiXianXingQingvShiJianWeiXu>>

		void  initRiXianXingQingMap(String mJsonKey , ArrayList<File> mJsonFileList){
// matchJsonArrMap = new HashMap<String,ArrayList<RiXianXingQingvShiJianWeiXu>>();

			System.out.println("initRiXianXingQingMap    mJsonKey="+mJsonKey+"    mJsonFileList.size()="+mJsonFileList.size());
			for (int i = 0; i < mJsonFileList.size(); i++) {
				File jsonFileitem = mJsonFileList.get(i);
				
		        StringBuilder jsonSB_temp = new StringBuilder();
		        tryReadJsonFromFile(jsonSB_temp,jsonFileitem);
		        
		        JSONObject   mJsonObject =    JSONObject.parseObject(jsonSB_temp.toString());
		        if(mJsonObject == null) {
		        	System.out.println("json 文件 "+ jsonFileitem.getAbsolutePath()+" 转为 JSONObject 对象失败!  请检查! ");
		            return;
		        }
		        JSONArray jsonArr =  mJsonObject.getJSONArray(mJsonKey);
		        
		        if(jsonArr == null) {
		        	System.out.println("json 文件 "+ jsonFileitem.getAbsolutePath()+" 转为 JSONObject 对象成功! 但获取"+mJsonKey+"  jsonKey 列表失败!   请检查! ");
		            continue;
		        }
		      
		        // jsonarr ---> list 
		        
                  List<RiXianXingQingvShiJianWeiXu>     mMatchRiXianList =
		        		JSONObject.parseArray(jsonArr.toString(), RiXianXingQingvShiJianWeiXu.class);
	                 
  
                  if(mMatchRiXianList == null || mMatchRiXianList.size() == 0) {
	   
               	System.out.println("当前报 json 文件 "+ jsonFileitem.getAbsolutePath()+" 转为 JSONObject 对象成功! 但获取"+mJsonKey+"  jsonKey 列表成功!  但 将JsonArr 转为 List<RiXianXingQingvShiJianWeiXu> 失败!  请检查!");

                 }
  
                  for (int j = 0; j < mMatchRiXianList.size(); j++) {
                	  // 遍历 每一个 mRiXianItem
                	  RiXianXingQingvShiJianWeiXu mRiXianItem =   mMatchRiXianList.get(j);
                	  String tscode = mRiXianItem.getTs_code();
                	  
                	  
                	  if(matchJsonArrMap.containsKey(tscode) && tscode != null && !"".equals(tscode)) {
                		  
                		  ArrayList<RiXianXingQingvShiJianWeiXu> matchArr =   matchJsonArrMap.get(tscode);
                		  if(matchArr == null) {
                			  matchArr =  new     ArrayList<RiXianXingQingvShiJianWeiXu> (); 
                		  }
                		  
                				  matchArr.add(mRiXianItem);
                				  matchJsonArrMap.put(tscode,matchArr);
                	  } else {
                		  ArrayList<RiXianXingQingvShiJianWeiXu> matchArr =  new     ArrayList<RiXianXingQingvShiJianWeiXu> ();
                		  matchArr.add(mRiXianItem);
                		  matchJsonArrMap.put(tscode,matchArr);
                	  }
                	  
				
				}
  
  
				
			}
		//	ShowMap_RiXianXingQing( matchJsonArrMap);
			System.out.println("matchJsonArrMap.size() = "+ matchJsonArrMap.size());
	
		 }
		
		
		
		@SuppressWarnings("unchecked")
		public void ShowMap_RiXianXingQing(Map<String, ArrayList<RiXianXingQingvShiJianWeiXu>> mMapParam) {
			Map.Entry<String, ArrayList<RiXianXingQingvShiJianWeiXu>> entryItem;
			int Firstindex = 0;
			int mapSize = mMapParam.size();
			if (mMapParam != null) {
				Iterator iterator = mMapParam.entrySet().iterator();
				while (iterator.hasNext()) {
					entryItem = (Map.Entry<String, ArrayList<RiXianXingQingvShiJianWeiXu>>) iterator.next();
					String ts_code = entryItem.getKey(); // Map的Key
					ArrayList<RiXianXingQingvShiJianWeiXu> mList = entryItem.getValue(); // Map的Value
					int SecondIndex = 0;
					int mListSize = mList.size();
					System.out.println("========== MapIndex[" + Firstindex + "] " + ts_code + "  =========");
					for (int i = 0; i < mList.size(); i++) {
						RiXianXingQingvShiJianWeiXu rixianItem = mList.get(i);
						System.out.println("[" + Firstindex + "]["+mapSize+"]-[" + SecondIndex + "]["+mListSize+"]"+":"+rixianItem);
						SecondIndex++;
					}
					Firstindex++;
				}
			}
		}
		
		
	}
	
	
	// pct_chg    日线行情数据才能匹配上
	
	public static class RiXianXingQingvShiJianWeiXu{ // 日线行情v时间为序



		Double amount;
	    Double change;
	    Double close;
	    String cname;
	    Double high;
	    Double low;
	    Double open;
	    Double pct_chg;    // 涨跌幅度百分比
	    Double pre_close;
	    String trade_date;
	    String ts_code;
	    Double vol;
	    
	    
	       //  动态增加的数据  动态增加的属性
		   public Double day3_pct_chg;
		   public Double day5_pct_chg;
		   public Double day10_pct_chg;
		   public Double day15_pct_chg;

		//  动态增加的数据  动态增加的属性
		public Double day30_pct_chg;
		public Double day60_pct_chg;
		public Double day90_pct_chg;
		public Double year_pct_chg;



		@Override
		public String toString() {
			return "RiXianXingQingvShiJianWeiXu{" +
					"amount=" + amount +
					", change=" + change +
					", close=" + close +
					", cname='" + cname + '\'' +
					", high=" + high +
					", low=" + low +
					", open=" + open +
					", pct_chg=" + pct_chg +
					", pre_close=" + pre_close +
					", trade_date='" + trade_date + '\'' +
					", ts_code='" + ts_code + '\'' +
					", vol=" + vol +
					", day3_pct_chg=" + day3_pct_chg +
					", day5_pct_chg=" + day5_pct_chg +
					", day10_pct_chg=" + day10_pct_chg +
					", day15_pct_chg=" + day15_pct_chg +
					", day30_pct_chg=" + day30_pct_chg +
					", day60_pct_chg=" + day60_pct_chg +
					", day90_pct_chg=" + day90_pct_chg +
					", year_pct_chg=" + year_pct_chg +
					'}';
		}

		public Double getDay30_pct_chg() {
			return day30_pct_chg;
		}

		public void setDay30_pct_chg(Double day30_pct_chg) {
			this.day30_pct_chg = day30_pct_chg;
		}

		public Double getDay60_pct_chg() {
			return day60_pct_chg;
		}

		public void setDay60_pct_chg(Double day60_pct_chg) {
			this.day60_pct_chg = day60_pct_chg;
		}

		public Double getDay90_pct_chg() {
			return day90_pct_chg;
		}

		public void setDay90_pct_chg(Double day90_pct_chg) {
			this.day90_pct_chg = day90_pct_chg;
		}

		public Double getYear_pct_chg() {
			return year_pct_chg;
		}

		public void setYear_pct_chg(Double year_pct_chg) {
			this.year_pct_chg = year_pct_chg;
		}

		public Double getDay3_pct_chg() {
				return day3_pct_chg;
			}


			public void setDay3_pct_chg(Double day3_pct_chg) {
				this.day3_pct_chg = day3_pct_chg;
			}


			public Double getDay5_pct_chg() {
				return day5_pct_chg;
			}


			public void setDay5_pct_chg(Double day5_pct_chg) {
				this.day5_pct_chg = day5_pct_chg;
			}


			public Double getDay10_pct_chg() {
				return day10_pct_chg;
			}


			public void setDay10_pct_chg(Double day10_pct_chg) {
				this.day10_pct_chg = day10_pct_chg;
			}


			public Double getDay15_pct_chg() {
				return day15_pct_chg;
			}


			public void setDay15_pct_chg(Double day15_pct_chg) {
				this.day15_pct_chg = day15_pct_chg;
			}
			
		    
		    public Double getAmount(){
		        return amount;
		    }

		    public void setAmount(Double amount){
		        this.amount=amount;
		    }

		    public Double getChange(){
		        return change;
		    }

		    public void setChange(Double change){
		        this.change=change;
		    }

		    public Double getClose(){
		        return close;
		    }

		    public void setClose(Double close){
		        this.close=close;
		    }

		    public String getCname(){
		        return cname;
		    }

		    public void setCname(String cname){
		        this.cname=cname;
		    }

		    public Double getHigh(){
		        return high;
		    }

		    public void setHigh(Double high){
		        this.high=high;
		    }

		    public Double getLow(){
		        return low;
		    }

		    public void setLow(Double low){
		        this.low=low;
		    }

		    public Double getOpen(){
		        return open;
		    }

		    public void setOpen(Double open){
		        this.open=open;
		    }

		    public Double getPct_chg(){
		        return pct_chg;
		    }

		    public void setPct_chg(Double pct_chg){
		        this.pct_chg=pct_chg;
		    }

		    public Double getPre_close(){
		        return pre_close;
		    }

		    public void setPre_close(Double pre_close){
		        this.pre_close=pre_close;
		    }

		    public String getTrade_date(){
		        return trade_date;
		    }

		    public void setTrade_date(String trade_date){
		        this.trade_date=trade_date;
		    }

		    public String getTs_code(){
		        return ts_code;
		    }

		    public void setTs_code(String ts_code){
		        this.ts_code=ts_code;
		    }

		    public Double getVol(){
		        return vol;
		    }

		    public void setVol(Double vol){
		        this.vol=vol;
		    }
		   
	}
	
	
	public  static class MeiRiZhiBiao { // 每日指标


		public Double circ_mv= 0d;
		   public Double close= 0d;
		   public String cname;
		   public String dv_ratio;
		   public String dv_ttm;
		   public Double float_share= 0d;
		   public Double free_share= 0d;
		   public Double pb= 0d;
		   public Double pe= 0d;
		   public Double pe_ttm= 0d;
		   public Double ps= 0d;
		   public Double ps_ttm= 0d;
		   public Double total_mv= 0d;
		   public Double total_share= 0d;
		   public String trade_date;
		   public String ts_code;
		   public Double turnover_rate= 0d;
		   public Double turnover_rate_f = 0d;
		   public Double volume_ratio= 0d;

		   

	@Override
	public String toString() {
		return "Stock_Daily_Basic [cname=" + cname + ", ts_code=" + ts_code + ", trade_date=" + trade_date
				+ ", close=" + close + ", turnover_rate=" + turnover_rate + ", turnover_rate_f=" + turnover_rate_f
				+ ", volume_ratio=" + volume_ratio + ", pe=" + pe + ", pe_ttm=" + pe_ttm + ", pb=" + pb + ", ps="
				+ ps + ", ps_ttm=" + ps_ttm + ", dv_ratio=" + dv_ratio + ", dv_ttm=" + dv_ttm + ", total_share="
				+ total_share + ", float_share=" + float_share + ", free_share=" + free_share + ", total_mv="
				+ total_mv + ", circ_mv=" + circ_mv + "]";
	}
	

  
	}
	
	//===========================    Rule End =========================== 
	


	class Basic_Rule extends Rule {
		Basic_Rule(String ctype, int cindex, int opera_type) {
			this.file_type = ctype;
			this.rule_index = cindex;
			this.operation_type = opera_type;
			this.identify = this.file_type + "" + this.rule_index;
			curFilterFileTypeList = new ArrayList<String>();
			curFixedFileList = new ArrayList<File>();
			firstInputIndexStr = "";
		}

		String applyStringOperationRule1(String origin) {
			return origin;
		}

		File applyFileByteOperationRule2(File originFile) {
			return originFile;
		}

		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			return null;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
											  HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
											  ArrayList<File> curRealFileList) {
			return curFileList;
		}

		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
												  ArrayList<File> allSubRealFileList) {

			return null;
		}

		boolean initParams4InputParam(String inputParam) {
			firstInputIndexStr = inputParam;
			return true;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			return true;
		}

		@Override
		boolean allowEmptyDirFileList() {
			return false;
		}

		String simpleDesc() {
			return null;
		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index + "    [索引 " + index + "]  描述:"
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
			}

			return itemDesc;
		}

		boolean tryReName(File curFile, String newName) {
			String newFilePath = curFile.getParent() + File.separator + newName;
			String oldName = curFile.getName();
			File newFile = new File(newFilePath);
			if (newFile.exists() && newFilePath.equals(curFile.getAbsolutePath())) {

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
				System.out.println("当前目录已存在重命名后的文件  文件名称:" + curFile.getName());
				return false; // 已经存在的文件不处理 直接返回

			}
			boolean flag = curFile.renameTo(newFile);
			if (flag) {
				System.out.println(oldName + " 转为 " + newFilePath + " 成功！");
				curFixedFileList.add(curFile);
			} else {
				String fileNameNoPoint = getFileNameNoPoint(newName);
				String secondNewName = newName.replace(fileNameNoPoint, fileNameNoPoint+"_"+getTimeStampLong());
				System.out.println(oldName + " 转为 " + newFilePath + " 失败1次！ 尝试使用新名称 secondNewName=["+secondNewName+"]");
				String newSecondPath = curFile.getParent() + File.separator + secondNewName;
				File secondFile = new File(newSecondPath);
				flag = curFile.renameTo(secondFile);
				if(flag) {
					System.out.println(oldName + " 第二次转为 " + newFilePath + " 成功！");
				}else {
					System.out.println(oldName + " 第二次转为 " + newFilePath + " 仍然失败！");
				}
			}
			return flag;
		}
	}

	abstract class Rule {
		// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
		// 属性进行修改(文件名称)
		// 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) // 5. 从shell 中获取到的路径 去对某一个文件进行操作
		String firstInputIndexStr;
		int operation_type;
		String file_type; // * 标识所有的文件类型 以及当前操作类型文件 或者 单独的文件过滤类型
		String identify;
		int rule_index; // (type,index) 组成了最基础的唯一键
		ArrayList<String> curFilterFileTypeList; // 当前的文件过滤类型 多种文件过滤类型 例如把 多种格式 jpeg png 转为 jpg 时 使用到
		ArrayList<File> curFixedFileList; // 当前修改操作成功的集合

		abstract boolean allowEmptyDirFileList(); // 是否允许当前的目录下的文件为空

		abstract String applyStringOperationRule1(String origin);

		abstract File applyFileByteOperationRule2(File originFile);

		abstract ArrayList<File> applyFileListRule3(ArrayList<File> subFileList,
													HashMap<String, ArrayList<File>> fileTypeMap);

		abstract ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
													   HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
													   ArrayList<File> curRealFileList);

		abstract ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
														   ArrayList<File> allSubRealFileList);

		abstract boolean initParams4InputParam(String inputParam); // 初始化Rule的参数 依据输入的字符串

		abstract boolean initParamsWithInputList(ArrayList<String> inputParamList);

		abstract String ruleTip(String type, int index, String batName, OS_TYPE curType); // 使用说明列表 如果覆盖 那么就不使用默认的说明 ,
		// 默认就一种情况

		abstract String simpleDesc(); // 使用的简单描述 中文的该 rule的使用情况 默认会在 ruleTip 被调用

	}





	static void ANSI_writeContentToFile(File file, String strParam) {

		try {
			if (file != null && !file.exists()) {
				System.out.println("创建文件:  " + file.getAbsolutePath());
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				file.createNewFile();

			}

			if (file != null && file.exists()) {
				BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
				curBW.write(strParam);
				curBW.flush();
				curBW.close();
				// System.out.println("write out File OK ! File = " + file.getAbsolutePath());
			} else {
				System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	public   void UTF8File_To_ANSIFile(File file) {
		StringBuffer buffer=new StringBuffer();
		try {
			FileInputStream fis=new FileInputStream(file.getAbsolutePath());
			InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
			String line=null;
			br.skip(1);
			while ((line=br.readLine())!=null) {
				buffer.append(line);
				buffer.append("\r\n");
			}
			buffer.delete(buffer.length()-2,buffer.length());
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(buffer);
		try {
			FileOutputStream fos=new FileOutputStream(file.getAbsoluteFile());
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			osw.write(buffer.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}



	static void writeContentToFile(File file, String strParam) {

		try {
			if (file != null && !file.exists()) {
				System.out.println("创建文件:  " + file.getAbsolutePath());
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				file.createNewFile();

			}

			if (file != null && file.exists()) {
				BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
				curBW.write(strParam);
				curBW.flush();
				curBW.close();
				// System.out.println("write out File OK ! File = " + file.getAbsolutePath());
			} else {
				System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String ReadFileContent(File mFilePath) {

		if (mFilePath != null && mFilePath.exists()) {
			// System.out.println("存在 当前文件 "+ mFilePath.getAbsolutePath());
		} else {
			System.out.println("不存在 当前文件 " + mFilePath.getAbsolutePath());

			return null;
		}
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
			String oldOneLine = "";
			int index = 1;
			while (oldOneLine != null) {

				oldOneLine = curBR.readLine();
				if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
					continue;
				}

				sb.append(oldOneLine + "\n");
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
				index++;

			}
			curBR.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();

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

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static boolean isNumeric(String str) {

		if (str == null) {
			return false;
		}

		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		if (str.trim().equals("")) {
			return false;
		}
		return true;
	}

	static void writeContentToFile(File file, ArrayList<String> strList) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strList.size(); i++) {
			sb.append(strList.get(i) + "\n");
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

	static String getPaddingIntString(int index, int padinglength, String oneStr, boolean dirPre) {
		String result = "" + index;
		int length = ("" + index).length();

		if (length < padinglength) {
			int distance = padinglength - length;
			for (int i = 0; i < distance; i++) {
				if (dirPre) {
					result = oneStr + result;
				} else {
					result = result + oneStr;
				}

			}

		}
		return result;

	}

	public static void createEncryFile(File generalFile, File encryptFile) {

		int general_position = 0;
		int general_offset = 0;
		FileInputStream generalFileInputStream = null;
		BufferedInputStream generalBufferedInputStream = null;

		FileOutputStream encryptileOutputStream = null;
		BufferedOutputStream encryptBufferedOutputStream = null;

		try {
			if (!encryptFile.exists()) {
				encryptFile.createNewFile();
			}
			generalFileInputStream = new FileInputStream(generalFile);
			generalBufferedInputStream = new BufferedInputStream(generalFileInputStream);

			encryptileOutputStream = new FileOutputStream(encryptFile);
			encryptBufferedOutputStream = new BufferedOutputStream(encryptileOutputStream);

			if (encryptFile.getAbsolutePath().trim().endsWith("md")) {
				while ((general_position = generalBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
					encryptBufferedOutputStream.write(TEMP_Rule7, 0, general_position);
					encryptBufferedOutputStream.flush();
				}
				// 关闭流
				generalBufferedInputStream.close();
				encryptBufferedOutputStream.close();
				return;

			}

			// System.out.println("原始文件字节大小: " + generalBufferedInputStream.available());
			while (general_offset < BYTE_CONTENT_LENGTH_Rule7) { // 读取原始文件的头 BYTE_CONTENT_LENGTH 个字节数进行加密
				general_position = generalBufferedInputStream.read(TEMP_Rule7, general_offset,
						TEMP_Rule7.length - general_offset);
				if (general_position == -1) {
					break;
				}
				general_offset += general_position;
				// byteTo16(TEMP, general_position); // 可以查看 指定 前 general_position 个在
				// TEMP数组中的字节数据 太多 注释掉
			}

			// 对读取到的TEMP字节数组 BYTE_CONTENT_LENGTH 个字节进行 ECB模式加密 明文大小与密文大小一致

			byte[] encrypt_bytes = encrypt(TEMP_Rule7);

			System.out.println("加密原始文件:" + generalFile.getName() + "  加密前明文大小:" + TEMP_Rule7.length + "   加密后密文大小:"
					+ encrypt_bytes.length);

			// 加密后的密文 填充 encryptFile文件的头首部
			encryptBufferedOutputStream.write(encrypt_bytes, 0, encrypt_bytes.length);
			encryptBufferedOutputStream.flush();
			// 从正常的 general文件 读取 BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 加密File(Head已经加密)文件中去
			while ((general_position = generalBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
				encryptBufferedOutputStream.write(TEMP_Rule7, 0, general_position);
				encryptBufferedOutputStream.flush();
			}
			// 关闭流
			generalBufferedInputStream.close();
			encryptBufferedOutputStream.close();

		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());

		}
	}

	private static Cipher encryptCipher = null;
	private static Cipher decryptCipher = null;

	static {
		try {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			Key key = getKey(strDefaultKey_Rule7.getBytes());
			encryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
			decryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {

		}

	}

	private static Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8]; // 认默为0
		for (int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密匙
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	// 加密字节数组
	public static byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	// 密解字节数组
	public static byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	static ArrayList<File> getAllSubFile(File dirFile) {
		ArrayList<String> typeList = new ArrayList<String>();
		typeList.add("#");
		return getAllSubFile(dirFile, null, typeList);
	}

	static ArrayList<File> getAllSubFile(File dirFile, String typeStr) {
		ArrayList<String> typeList = new ArrayList<String>();
		typeList.add(typeStr);

		return getAllSubFile(dirFile.getAbsolutePath(), "", typeList);

	}

	static ArrayList<File> getAllSubFile(File dirFile, String aospPath, ArrayList<String> typeList) {
		if (aospPath == null || "".equals(aospPath)) {
			return getAllSubFile(dirFile.getAbsolutePath(), "", typeList);
		}
		return getAllSubFile(dirFile.getAbsolutePath(), aospPath, typeList);

	}

	static ArrayList<File> getAllSubFile(String rootPath, String aospItemPath, ArrayList<String> typeList) {
		ArrayList<File> allFile = new ArrayList<File>();
		Path curRootPath = Paths.get(rootPath + File.separator + aospItemPath);

		try {
			Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String fileString = file.toAbsolutePath().toString();
					// System.out.println("pathString = " + fileString);

					for (int i = 0; i < typeList.size(); i++) {
						String type = typeList.get(i);
						if ("#".equals(type)) { // 如果 类型是 * 那么就把 所有的 非目录文件加入列表中
							File curFile = new File(fileString);
							if (!curFile.isDirectory()) {
								allFile.add(curFile);
								break;
							}

						} else {
							if (fileString.endsWith(type)) {
								allFile.add(new File(fileString));
								break;
//                         System.out.println("file found at path: " + file.toAbsolutePath());
							}
						}
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allFile;

	}

	static ArrayList<File> getCurrentSubDirFile(File rootPath) {
		ArrayList<File> allDirFile = new ArrayList<File>();
		File[] files = rootPath.listFiles();
		for (int i = 0; i < files.length; i++) {
			File fileItem = files[i];
			if (fileItem.isDirectory()) {
				allDirFile.add(fileItem);
			}
		}
		return allDirFile;

	}

	static ArrayList<File> getAllSubDirFile(File rootPath) {
		ArrayList<File> allDirFile = new ArrayList<File>();
		Path curRootPath = Paths.get(rootPath.getAbsolutePath() + File.separator);

		try {
			Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					allDirFile.add(dir.toFile());
					return super.postVisitDirectory(dir, exc);
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allDirFile;

	}

	// 读取加密文件 对加密部分进行解密 然后生成解密之后的文件 decryptFile
	public static void createDecryFile(File encryptFile, File decryptFile) {

		FileOutputStream decryptileOutputStream = null;
		BufferedOutputStream decryptBufferedOutputStream = null;

		FileInputStream encryptileInputStream = null;
		BufferedInputStream encryptBufferedInputStream = null;

		try {
			if (!decryptFile.getParentFile().exists()) {
				decryptFile.getParentFile().mkdirs();
			}

			if (!decryptFile.exists()) {
				decryptFile.createNewFile();
			}
			encryptileInputStream = new FileInputStream(encryptFile);
			encryptBufferedInputStream = new BufferedInputStream(encryptileInputStream);

			decryptileOutputStream = new FileOutputStream(decryptFile);
			decryptBufferedOutputStream = new BufferedOutputStream(decryptileOutputStream);

			int encrypt_offset = 0;
			int encrypt_position = 0;
			while (encrypt_offset < BYTE_CONTENT_LENGTH_Rule7) { // 读取到加密文件的 加密字节部分 大小为 BYTE_CONTENT_LENGTH
				encrypt_position = encryptBufferedInputStream.read(TEMP_Rule7, encrypt_offset,
						TEMP_Rule7.length - encrypt_offset);

				if (encrypt_position == -1) {
					break;
				}
				encrypt_offset += encrypt_position;
				// byteTo16(TEMP, general_position); // 可以查看 指定 前 general_position 个在
				// TEMP数组中的字节数据 太多 注释掉
			}

			byte[] decrypt_bytes = decrypt(TEMP_Rule7); // 对加密文件的加密字节进行解密
			System.out.println("源文件:" + encryptFile.getAbsolutePath() + "    解密文件:" + decryptFile.getAbsolutePath()
					+ "  密文加密字节大小:" + TEMP_Rule7.length + "   解密密文之后的明文大小:" + decrypt_bytes.length);

			decryptBufferedOutputStream.write(decrypt_bytes);
			decryptBufferedOutputStream.flush();

			// 读取 encryptFile加密文件中正常的字节 BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到
			// 解密File(Head已经解密)文件中去
			while ((encrypt_offset = encryptBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
				decryptBufferedOutputStream.write(TEMP_Rule7, 0, encrypt_offset);
				decryptBufferedOutputStream.flush();
			}

			encryptBufferedInputStream.close();
			decryptBufferedOutputStream.close();

		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());

		}
	}

	static void showTip() {
		System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
		System.out.println("当前已实现的替换逻辑如下:\n");

		int count = 1;
		System.out.println("═══════════════════" + "使用方法列表 Begin" + "═══════════════════" + "\n");
		for (int i = 0; i < realTypeRuleList.size(); i++) {
			Rule itemRule = realTypeRuleList.get(i);
			String type = itemRule.file_type;
			int index = itemRule.rule_index;
			String desc = itemRule.ruleTip(type, index, Cur_Bat_Name, CUR_OS_TYPE);

			/*
			 * String itemDesc = ""; if(curOS_TYPE == OS_TYPE.Windows){ itemDesc =
			 * "zrule_apply_J0.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
			 * }else{ itemDesc = "zrule_apply_J0 "+type+"_"+index +
			 * "    [索引 "+count+"]  描述:"+desc; }
			 */
			System.out.println(desc + "\n");
			count++;
		}
		System.out.println("═══════════════════" + "使用方法列表 End " + "═══════════════════" + "\n");

	}

	static boolean checkInputParamsOK() {
		boolean inputOk = true;

		for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) {
			String curInputStr = Rule_Identify_TypeIndexList.get(i);
			if (!curInputStr.contains("_")) {
				return false;
			}

			String[] paramsArr = curInputStr.split("_");
			if (paramsArr.length < 2) {
				continue;
			}
			String type = paramsArr[0];
			String index = paramsArr[1];

//          initParams4InputParam
			if (!isNumeric(index)) { // 第二个参数不是 数字 那么 输入格式错误
				return false;
			}
			Rule matchRule = getRuleByIndex(Integer.parseInt(index));
			if (matchRule != null) {
				inputOk = matchRule.initParams4InputParam(curInputStr)
						&& matchRule.initParamsWithInputList(Rule_Identify_TypeIndexList);
				return inputOk;
			}

		}

		return inputOk;
	}

	static Rule CurSelectedRule;

	public static void main(String[] args) {

		initSystemInfo(args);
		if(!is_init_J0Data_File()) {
			System.out.println("初始化 J0Data 目录"+J0_Data_Dir_Path+" 文件集合过程中出错 请检查后再执行!");
		    return;
		}


		mJ0_Object = new J0_Dynamic_AppendData_JsonTool();
		mJ0_Object.InitRule();

		File mCurDirFile = new File(curDirPath);
		curDirFile = new File(curDirPath);

		if (mKeyWordName.size() == 0) {
			showTip();
			return;
		}

		if (!checkInputParamsOK()) {
			System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
			return;
		}

		if (curDirFile == null || !mCurDirFile.exists() || !mCurDirFile.isDirectory()) {
			System.out.println("当前执行替换逻辑的文件路径:" + curDirPath + "  不存在! ");
			return;
		}

		// 通过 shell中输入参数来进行操作
		// Rule_Identify_TypeIndexList.add("html_1"); // 1.添加处理的类型文件 类型_该类型的处理逻辑索引
		// 索引从1开始

		for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) { // 依据文件类型 去找到文件
			// html_1
			String applyRuleString = Rule_Identify_TypeIndexList.get(i);
			String paramsArr[] = applyRuleString.split("_");
			if (paramsArr.length < 2) {
				continue;
			}
			String curType = paramsArr[0];
			String curApplyRule = paramsArr[1];
			if (!isNumeric(curApplyRule)) {
				continue;
			}
			int ruleIndex = Integer.parseInt(curApplyRule);

			Rule curApplayRule = getRuleByIndex(ruleIndex);
			if (curApplayRule != null) {
				CurSelectedRule = curApplayRule;
			}
			if (curApplayRule == null && CurSelectedRule == null) {
				System.out.println("无法匹配到 对应的 index=" + ruleIndex + "  对应的规则 Rule !   可能需要代码添加。");
				continue; // 继续下一个循环
			}
			if (curApplayRule == null && CurSelectedRule != null) {
				return;
			}
			if (curApplayRule.curFilterFileTypeList.size() == 0) {
				curApplayRule.curFilterFileTypeList.add(curType);
			}

			ArrayList<File> typeFileList = new ArrayList<File>();

			if (curApplayRule.operation_type == 4) { // 对于 类型是 4 的操作 只获取当前 shell 下的文件
				typeFileList.addAll(Arrays.asList(mCurDirFile.listFiles()));
				System.out.println("operation_type == 4 子目录大小: " + typeFileList.size());
			} else {
				typeFileList = getAllSubFile(mCurDirFile, null, curApplayRule.curFilterFileTypeList);
			}

			if (typeFileList.size() == 0) {
				System.out.println("未能搜索到类型列表匹配的文件:  " + Rule_Identify_TypeIndexList.get(i));
				if (!curApplayRule.allowEmptyDirFileList()) { // 是否允许当前目录下的文件夹为空
					continue;
				}

			}
			initFileTypeMap(typeFileList);

			if (curApplayRule.operation_type == 4) { // 只对 当前的 子 文件(目录 文件)操作
				// 对当前文件进行整理
				ArrayList<File> subDirList = new ArrayList<File>();
				ArrayList<File> realFileList = new ArrayList<File>();

				outCycle: for (int j = 0; j < typeFileList.size(); j++) {
					File curFile = typeFileList.get(j);
					if (curFile.isDirectory()) {
						subDirList.add(curFile);
					} else {

						if (curApplayRule.curFilterFileTypeList.contains("#")) {
							realFileList.add(curFile);
						} else {

							inCycle: for (int k = 0; k < curApplayRule.curFilterFileTypeList.size(); k++) {
								String curMatchType = curApplayRule.curFilterFileTypeList.get(k);
//                                System.out.println("FileName:"+curFile.getName()+"  curMatchType="+curMatchType);
								if (curFile.getName().endsWith(curMatchType)) {
									realFileList.add(curFile);
									break inCycle;
								}
							}

						}

					}
				}

				ArrayList<File> resultFileList = curApplayRule.applySubFileListRule4(typeFileList, CurDirFileTypeMap,
						subDirList, realFileList);
				if (resultFileList != typeFileList) {
					System.out.println("应用规则:  " + applyRuleString + " 成功!");
				} else {
					System.out.println("应用规则:  " + applyRuleString + " 失败!");
				}

			} else if (curApplayRule.operation_type == 3) { // 对所有文件进行的 统一处理的 类型

				ArrayList<File> resultFileList = curApplayRule.applyFileListRule3(typeFileList, CurDirFileTypeMap);
				if (resultFileList != typeFileList) {

					System.out.println("应用规则:  " + applyRuleString + " 成功!");
				} else {
					System.out.println("应用规则:  " + applyRuleString + " 失败!");
				}

			} else if (curApplayRule.operation_type == 5) { // 对所有文件夹 所有子文件 孙文件 所有 子文件夹 孙文件夹

				ArrayList<File> curAllDirFile = getAllSubDirFile(curDirFile); // 获取所有的 文件夹列表 包含 孙子 子文件夹
				ArrayList<File> curAllRealFile = getAllSubFile(curDirFile); // 获取所有的 文件 列表 包含 孙子 子文件
				// FileChannel
//  zukgit operation_type == 5
				System.out.println(" curDirFile = " + curDirFile.toString());
				System.out.println(" curAllDirFile = " + curAllDirFile.size());
				System.out.println(" curAllRealFile = " + curAllRealFile.size());
				curApplayRule.applyDir_SubFileListRule5(curAllDirFile, curAllRealFile);
			} else {

				for (int j = 0; j < typeFileList.size(); j++) {
					File itemFile = typeFileList.get(j);
					String fileCOntent = ReadFileContent(itemFile).trim();
					// 2.applyOperationRule 添加处理规则

					String resultStr = OriApplyOperationRule(curType, curApplyRule, fileCOntent).trim();

					int currentOperationType = 1; // 默认操作类型是 读取字符串的内容 进行处理

					String identy = curType.trim() + curApplyRule.trim();
//                Rule applayRule2Identify = getRuleByIdentify(identy);

					Rule applayRule4Index = getRuleByIndex(ruleIndex);
//                如果对应相同的 index的 Rule #_2    出现了    MP3_2 的情况  那么就需要把当前的 所有的*的文件 过滤为 mp3的文件
//                if("#".equals(applayRule2Identify.file_type) && !curType.equals(applayRule2Identify.file_type)){
//
//                }

					if (applayRule4Index != null) {
						currentOperationType = applayRule4Index.operation_type;
					} else {
						System.out.println("无法匹配到 对应的 identy=" + identy + "  对应的规则 Rule !   可能需要代码添加。");
						return;
					}

					if (currentOperationType == 1) { // 对字符串进行逻辑处理的类型

						if (!fileCOntent.equals(resultStr)) {
							writeContentToFile(itemFile, resultStr);
							System.out.println("itemFile[" + j + "] 符合规则(String-Content) 应用Rule成功 " + applyRuleString
									+ "  = " + itemFile.getAbsolutePath());
						} else {
							System.out.println(
									"itemFile[" + j + "] 不符合规则(String-Content) = " + itemFile.getAbsolutePath());
						}

					} else if (currentOperationType == 2) {

						File resultFile = applayRule4Index.applyFileByteOperationRule2(itemFile);

						if (resultFile != itemFile) {
							System.out.println("itemFile[" + j + "] 符合规则(File) 应用Rule成功 " + applyRuleString + "  = "
									+ itemFile.getAbsolutePath());
						} else {
							System.out.println("itemFile[" + j + "] 不符合规则(File) = " + itemFile.getAbsolutePath());
						}

					}

				}

			}

		}

		setProperity();
	}

	static void addCurFileTypeMapItemWithKey(String keyType, File curFile) {
		if (CurDirFileTypeMap.containsKey(keyType)) {
			ArrayList<File> fileList = CurDirFileTypeMap.get(keyType);
			fileList.add(curFile);
		} else {
			ArrayList<File> fileList = new ArrayList<File>();
			fileList.add(curFile);
			CurDirFileTypeMap.put(keyType, fileList);
		}
	}

	static void initFileTypeMap(ArrayList<File> subFileList) {
		if (subFileList == null) {
			return;
		}
		for (File curFile : subFileList) {
			String fileName = curFile.getName();
			if (!fileName.contains(".")) {
				addCurFileTypeMapItemWithKey("unknow", curFile);
			} else {
				String suffix = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
				addCurFileTypeMapItemWithKey(suffix, curFile);
			}
		}

	}

	static Map<String, ArrayList<File>> getCurSubFileMap(File mDirFile) {
		HashMap<String, ArrayList<File>> realFileListMap = new HashMap<String, ArrayList<File>>();
		;

		for (File curFile : mDirFile.listFiles()) {
			if (curFile.isDirectory()) {
				continue;
			}
			String fileName = curFile.getName();

			if (!fileName.contains(".")) {
				String type = ""; // unknow 没有后缀名的文件
				if (realFileListMap.containsKey(type)) {
					ArrayList<File> fileList = realFileListMap.get(type);
					fileList.add(curFile);
				} else {
					ArrayList<File> fileList = new ArrayList<File>();
					fileList.add(curFile);
					realFileListMap.put(type, fileList);
				}
			} else {
				String suffix = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();

				if (realFileListMap.containsKey(suffix)) {
					ArrayList<File> fileList = realFileListMap.get(suffix);
					fileList.add(curFile);
				} else {
					ArrayList<File> fileList = new ArrayList<File>();
					fileList.add(curFile);
					realFileListMap.put(suffix, fileList);
				}
			}
		}

		return realFileListMap;
	}

	static String OriApplyOperationRule(String mType, String index, String mOriContent) {
		String identy = mType.trim() + index.trim();
		Rule applayRule = getRuleByIdentify(identy);
		if (applayRule == null) {
			System.out.println("没有查询到 identy =" + identy + "对应的处理规则");
			return mOriContent;
		}
		return applayRule.applyStringOperationRule1(mOriContent);
	}

	static Rule getRuleByIdentify(String identify) {
		for (int i = 0; i < realTypeRuleList.size(); i++) {
			if (realTypeRuleList.get(i).identify.equals(identify)) {
				return realTypeRuleList.get(i);
			}
		}
		return null;
	}
	
	static ArrayList<Rule> realTypeRuleList = new ArrayList<Rule>(); // 规则的集合

	static Rule getRuleByIndex(int index) {
		for (int i = 0; i < realTypeRuleList.size(); i++) {
			if (realTypeRuleList.get(i).rule_index == index) {
				return realTypeRuleList.get(i);
			}
		}
		return null;
	}


	public static String getFileNameNoPoint(String originName) {
		String type = getFileTypeWithPoint(originName);
		return originName.replace(type, "");
	}

	public static String getFileTypeWithPoint_unknow(String fileName) {
		String name = "";
		if (fileName.contains(".")) {
			name = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
		} else {
			name = "";
		}
		return name.toLowerCase().trim();
	}

	public static String getFileTypeWithPoint(String fileName) {
		String name = "";
		if (fileName.contains(".")) {
			name = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
		} else {
			name = "";
		}
		return name.toLowerCase().trim();
	}
	
    static String getTimeStamp_yyyyMMdd() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

	// A1 ..... A2.
	static String get_Bat_Sh_FlagNumber(String mCur_Bat_Name) {
		String mCharNumber = "error";
		String curBat = mCur_Bat_Name;
		if (mCur_Bat_Name.contains(".sh")) {
			curBat = curBat.replace(".sh", "");
		}

		if (mCur_Bat_Name.contains(".bat")) {
			curBat = curBat.replace(".bat", "");
		}
		if (curBat.contains("_")) {
			String[] arrNameList = curBat.split("_");
			mCharNumber = arrNameList[arrNameList.length - 1];
		} else {
			mCharNumber = curBat;
		}

		return mCharNumber;
	}

	static String getTimeStampLong() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}
	
	
	public static		String calcDayJsonName(int tradeDay) {
		 int mMatchYear = tradeDay / 10000;
		
		 String mMatchMMDD = (""+tradeDay).replace(""+mMatchYear, "");
		 String matchJsonName = "day_"+mMatchYear+"_"+mMatchMMDD+".json";

		 // 判断 文件 里面是否 含有 时间v 这里算了  影响性能
		 return matchJsonName;

	}
	
//	allDailyJsonFileList
	
	
	//  给定需要返回的 数组的个数  然后 过滤返回
	public static ArrayList<File> getSubList(int limitSize , ArrayList<File> fileList ){
		ArrayList<File>  subList = new 	ArrayList<File> ();
		if(fileList == null ) {
			
			return subList;
		}

		// 如果大于 自身大小 那么返回自身
		if(limitSize >= fileList.size()){

			return fileList;
		}

		
		for (int i = 0; i < limitSize; i++) {
			File item = fileList.get(i);
			subList.add(item);
		}
		
		
		return subList;
		
		
	}
	
	
	//  给定需要返回的 数组的个数  然后 过滤返回
	public static ArrayList<RiXianXingQingvShiJianWeiXu> getSubList_RiXian(int limitSize , ArrayList<RiXianXingQingvShiJianWeiXu> mList ){
		ArrayList<RiXianXingQingvShiJianWeiXu>  subList = new 	ArrayList<RiXianXingQingvShiJianWeiXu> ();
		if(mList == null ) {
			
			return subList;
		}

		if( mList.size() < limitSize){  // 有些股票新 上市 所以达不到 limitSize的大小 ， 如 一年的 limit  , 那么 全部返回 自身的数据

			return mList;
		}
		for (int i = 0; i < limitSize; i++) {
			RiXianXingQingvShiJianWeiXu item = mList.get(i);
			subList.add(item);
		}
		
		
		return subList;
		
		
	}
	
	
	// 返回 以 指定 day 文件 为 最新 第一个文件的 数组列表 
	public static ArrayList<File> getTargetDayAsLastFileList(File targetFile ){
		ArrayList<File> matchFileList = new ArrayList<File>();
		boolean isMatchFile = false;
		  String  targetFileName = targetFile.getName();
		for (int i = 0; i < allDailyJsonFileList.size(); i++) {
			File fileItem = allDailyJsonFileList.get(i);
			if(isMatchFile) {
				matchFileList.add(fileItem);
				continue;
			}
			
		   String itemName = fileItem.getName();
		 
		   if(itemName.equals(targetFileName)) {
				matchFileList.add(fileItem);
			   isMatchFile = true;
		   }

		}

		return matchFileList;

	}


    static void tryReadJsonFromFile(StringBuilder sb, File file) {

        if (file != null && file.exists()) {

            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                String lineStr = "";

                while (lineStr != null) {
                    lineStr = curBR.readLine();
                    if (lineStr == null || lineStr.trim().isEmpty()) {
                        continue;
                    }

                    sb.append(lineStr.trim());
                }
                curBR.close();

                System.out.println("read json File OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }

	static int getCurrentYYYY() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy");

		return Integer.parseInt(df.format(new Date()));

	}

}