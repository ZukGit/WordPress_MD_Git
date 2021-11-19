

import com.google.common.collect.Maps;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// 对于  文件类型_操作Index  执行对应的操作逻辑
public class J0_GuPiao_Analysis {

	
    static ArrayList<String> TScode_List = new ArrayList<String>();    // 读取  J0_股票列表 里的股票名称的集合
    
    // tscode 与 名称 一一对应起来
    static Map<String,String> TScode_StockName_Map = new HashMap<String,String>(); 
    
    //  key 是 今年的 年份     value 是 今年的 交易的日期的集合
    static Map<Integer,ArrayList<Integer>> mYear_TradeDayList_Map = new HashMap<Integer,ArrayList<Integer>>();  
    
	static String[] MainStock_SheetChineseNameArr = {"收盘价","涨跌幅","涨跌值","成交额"};
	static String[] MainStock_SheetEnglishNameArr = {"close","pct_chg","change","amount"};
	
	static String[] SheetHead_Part_1 = {"cname","ts_code"};
	
    
	// 类型_索引 ，对当前类型的文件执行索引执行的操作 html1---对html类型的子文件执行 索引为1 的逻辑操作 String
	// apply(String)
	static ArrayList<String> Rule_Identify_TypeIndexList = new ArrayList<String>();

	static String Cur_Batch_End = ".bat";
	static String J0_Bat_Name = "zgupiao_analysis_J0";
	static String Cur_Bat_Name = "zgupiao_analysis_J0";
	static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin";
	static String J0_File_Path = zbinPath + File.separator + "J0";
	static String Win_Lin_Mac_ZbinPath = "";

	static File J0_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator
			+ "Desktop" + File.separator + "zbin" + File.separator + "J0.properties");
	static InputStream J0_Properties_InputStream;
	static OutputStream J0_Properties_OutputStream;
	static Properties J0_Properties = new Properties();
	static Map<String, String> propKey2ValueList = new HashMap<String, String>();

	static int BYTE_CONTENT_LENGTH_Rule7 = 1024 * 10 * 10; // 读取文件Head字节数常数
	static String strDefaultKey_Rule7 = "zukgit12"; // 8-length

	static String strZ7DefaultKey_PSW_Rule19 = "752025"; // 8-length
	public static byte[] TEMP_Rule7 = new byte[BYTE_CONTENT_LENGTH_Rule7];

	static J0_GuPiao_Analysis mJ0_Object;

	



    static String cur_os_zbinPath;
    static String win_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "win_zbin";
    static String lin_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "lin_zbin";
    static String mac_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "mac_zbin";

    static String J0_Data_Dir_Path = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_Data"+File.separator;
    static String J0_GuPiaoLieBiao_Path = zbinPath+File.separator+"J0_股票列表.xlsx";
    static String J0_JiaoYiRiQi_Path = zbinPath+File.separator+"J0_交易日历.xlsx";
    

    // 当前保存   2021_main_stock.xlsx  2020_main_stock.xlsx 文件的集合  放置于 
	static ArrayList<File> mYearMainStockFileList = new ArrayList<File>(); // 规则的集合
	

	

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

	static void initSystemInfo() {
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
	
	
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    
    
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
    
    
    static void  initTradeDayList(){
        File tradeDayFile = new File(J0_JiaoYiRiQi_Path);
        if(!tradeDayFile.exists()){
            System.out.println("当前 没有 基础数据文件【交易日历.xlsx】( 请添置该文件 ) J0_JiaoYiRiQi_Path ="+ J0_JiaoYiRiQi_Path);
            return;
        }

        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = tradeDayFile.getAbsolutePath();
//        System.out.println("J0_JiaoYiRiQi_Path xlsx Path = "+ filePath);
        String columns[] = {"exchange","cal_date","is_open","pretrade_date"};
        wb = readExcel(filePath);

        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
//            sheet = wb.getSheet("股票列表");
//            sheet = wb.getSheetAt(0);
            sheet = wb.getSheet("交易日历");
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }

        String SH_TAG = "SSE";
        String HK_TAG = "XHKG";
        //遍历解析出来的list
      first:  for (Map<String,String> map : list) {
        second:     for (Map.Entry<String,String> entry : map.entrySet()) {
//                System.out.print(entry.getKey()+":"+entry.getValue()+",");

                if("exchange".equals(entry.getKey()) && SH_TAG.equals(entry.getValue()) ){
                    addSHTradeDay(map);
                     break ;
                }
                //    System.out.println(" entry.getKey() = "+entry.getKey() + "   entry.getValue() = "+ entry.getValue()+"【Over】");
            }
//            System.out.println();
        }

        //    HK_TradeDayList =
//    SH_TradeDayList =
//    HK_No_TradeDayList
//    SH_No_TradeDayList

//        showTradeDayList(SH_TradeDayList,"上证交易日");
//        showTradeDayList(SH_No_TradeDayList,"上证非交易日");
//        showTradeDayList(HK_TradeDayList,"港市交易日");
//        showTradeDayList(HK_No_TradeDayList,"港市非交易日");


     int nowYeayTradeDaySize =  mYear_TradeDayList_Map.get(getCurrentYear()).size();
     
//     System.out.println("今年【"+getCurrentYear()+"】 总共有 【"+ nowYeayTradeDaySize+"】 个交易日! ");
      
    }
    
    static void   addSHTradeDay(Map<String,String> map ){
        // addSHTradeDay  isopen = 1.0     cal_date = 19910418
        // 1.0 转为 int  报错
        String isopen = map.get("is_open").trim();
        float isOpenInt = Float.parseFloat(isopen);
        String cal_date =  map.get("cal_date").trim();
     //   System.out.println("addSHTradeDay  isopen = "+isopen + "   isOpenInt ="+isOpenInt+  "     cal_date = "+ cal_date);

        if(isOpenInt == 1 && cal_date != null && cal_date.trim().length() == 8 ) {  // 20200101
        	
            int dayIntFlag = Integer.parseInt(cal_date);
            String yearStr =cal_date.trim().substring(0,4);
            int yearInt = Integer.parseInt(yearStr); 
            
            
           Add_SH_Now_TradeDayList_FromNow(yearInt,dayIntFlag);    //  添加 日期   分分 应该.

          
        	
        } 
        	
        	
 

    }
    
    // 从年初 到 今天的 日期的集合
    static void Add_SH_Now_TradeDayList_FromNow(int yearInt , int dayIntFlag){
    ArrayList<Integer> matchTradeDayList = 	mYear_TradeDayList_Map.get(yearInt);
    	if(matchTradeDayList == null) {
    		matchTradeDayList = new ArrayList<Integer>();
    		mYear_TradeDayList_Map.put(yearInt, matchTradeDayList);
    	}
    	if(!matchTradeDayList.contains(dayIntFlag)) {
    		matchTradeDayList.add(dayIntFlag);
    	}
    	
    }
    
    
    static void  initTsCodeList(){
// TScode_List

        File ts_code_File = new File(J0_GuPiaoLieBiao_Path);
        if(!ts_code_File.exists()){
            System.out.println("当前 没有 基础数据文件【股票列表.xlsx】( 请添置该文件 ) J0_GuPiaoLieBiao_Path ="+ J0_GuPiaoLieBiao_Path);
            return;
        }

    	System.out.println("开始读取 "+J0_GuPiaoLieBiao_Path+" 文件股票数据");
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = ts_code_File.getAbsolutePath();
        System.out.println("xlsx Path = "+ filePath);
        String columns[] = {"ts_code","symbol","name","area","industry","fullname","enname","market","exchange","curr_type","list_status","list_date","delist_date","is_hs"};
        wb = readExcel(filePath);

        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
//            sheet = wb.getSheet("股票列表");
//            sheet = wb.getSheetAt(0);
            sheet = wb.getSheet("股票列表");
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }
        //遍历解析出来的list


        for (Map<String,String> map : list) {
        	String ts_code = null;
        	String stockName = null;
            for (Map.Entry<String,String> entry : map.entrySet()) {
//                System.out.print(entry.getKey()+":"+entry.getValue()+",");

                if("ts_code".equals(entry.getKey())){
                    TScode_List.add(entry.getValue());
                    ts_code = entry.getValue();
                }

                if("name".equals(entry.getKey())){
                	stockName = entry.getValue();
                }
                

                //    System.out.println(" entry.getKey() = "+entry.getKey() + "   entry.getValue() = "+ entry.getValue()+"【Over】");
            }
            if(ts_code != null && stockName != null  ) {
            	TScode_StockName_Map.put(ts_code, stockName);
            	
            	
            }
//            System.out.println();
        }


        TScode_List.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

   	 System.out.println("TScode_List.size = 【 "+ TScode_List.size()+"】 TScode_StockName_Map.size=【"+TScode_StockName_Map.size()+"】  保存于" +J0_GuPiaoLieBiao_Path+"股票列表! ");

    }
    

	void initrule() {
//	
//	 realTypeRuleList.add(new  Daily_basic_YYYYMM_XLSX_Rule_0());

		addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	addRuleToList(new  AddData_To_Year_Main_Stock_Xlsx_Rule_1(),true);	
	}
	
	
	
	
void 	addRuleToList(Rule rule , boolean isShowInXlsxHead){
	  realTypeRuleList.add(rule);	
	  rule.isShowHeaderInXlsx = isShowInXlsxHead;
	  rule.dynamicIndexInList = realTypeRuleList.size();
	
		
		
	}

	
	
	
	
	
// 3038年 5 月 3 日

	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
//     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作


	
	//  创建一个 xlsx 把 一年的 数据都包含进去  收盘股价 涨跌幅度   涨跌值  成交量  四个指标
	// 放入的文件名称是  2021_main_stock.xlsx    包含 close pct_chg  change  amount  四个指标
	//  每个xlsx 分为三部分  第一部分 是 股票名称    第二部分是 动态计算的 最近结果  第三部分是 当前属性的指标
	
	class AddData_To_Year_Main_Stock_Xlsx_Rule_1 extends  Basic_Rule{
		
		// 默认为今年   如果是 从外部获取 那么 就是指定的 年份  最终会 匹配到  2021_main_stock.xlsx 这样的文件
		int mYearInt;   // 默认为 今年  从 输入得到 
		File mMatchYearMainStockFile;  // 匹配到的 xxxx_main_stock.xlsx
		ArrayList<File> mDayXlsxFileList ;   // 在 J0_Data 中的 day_2021_1105.xlsx 文件的集合
		ArrayList<File> mDayJsonFileList ; // 在 J0_Data 中的 day_2021_1105.json 文件的集合
		ArrayList<Integer> mNeedAddTradeDayList;//  读取 yyyy_main_stock.xlsx 后需要加载数据的日期列表
	
		ArrayList<Integer> mYearTradeDayList;//    今年总共需要写入的 交易日期 列表
		
		ArrayList<Integer> mdAddFaiedTradeDayList;  //  添加数据失败的 日期的 天数  可能由于 没有文件导致
		
		// key【000001(ts_code)】---> value【 key【0811】,value【RiXianXingQingvShiJianWeiXu(四个属性)】 】
	    // 拿到了 对应的 数据 
 		Map<String,Map<String,RiXianXingQingvShiJianWeiXu>>  mStockPropMap;
		

 		boolean isShowLog;
		
		File mJ0_Data_Dir_File;
	
		
		
		
		AddData_To_Year_Main_Stock_Xlsx_Rule_1() {
			super("#", 1, 4); //
			// TODO Auto-generated constructor stub
			mYearInt = getCurrentYear();
			mDayXlsxFileList = new ArrayList<File>();
			mDayJsonFileList = new ArrayList<File>();
			mNeedAddTradeDayList = new  ArrayList<Integer>();
			mdAddFaiedTradeDayList = new  ArrayList<Integer>();
			mYearTradeDayList =  new  ArrayList<Integer>();
			mStockPropMap = new  HashMap<String,Map<String,RiXianXingQingvShiJianWeiXu>>();
			
			isShowLog = false;
		}
		
		
		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
		// TODO Auto-generated method stub
		
			mJ0_Data_Dir_File = new File(J0_Data_Dir_Path);
			
			if(!mJ0_Data_Dir_File.exists()) {
				System.out.println(" J0_Data 路径 "+ J0_Data_Dir_Path +" 文件不存在 程序停止执行 请检查!! 该路径");
			   return false;
			}
			
			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i).toLowerCase();
				
				
				if (paramItem.startsWith("year_")) {
					String mYearStr =  paramItem.replace("year_", "").trim();
					
					if(isNumeric(mYearStr) && mYearStr.length() == 4) {
						mYearInt = Integer.parseInt(mYearStr);
						
					}
				}
				
				if (paramItem.equals("showlog_true")) {
					isShowLog = true;
			
				}
				
				
				
				
			}
	
			mMatchYearMainStockFile = new File(mJ0_Data_Dir_File.getAbsolutePath()+File.separator+mYearInt+"_main_stock.xlsx");
			
			if(!mMatchYearMainStockFile.exists()) {
				System.out.println("当前目录有没对应的 xxxx_main_stock.xlsx 文件 将创建这样的文件!");
				initYearMainStockXlsxForYear(mYearInt);
			}
			
			if(!initJ0DataDay_Xlsx_Json_File()) {
				System.out.println("当前 "+mJ0_Data_Dir_File.getAbsolutePath()+" 目录没有对应的 day_"+mYearInt+"_xxxx.xlsx 文件请执行  zstock_tushare_tool_J0.bat 创建这样的文件" );
			   return false;
			}

			
			System.out.println("mYearInt【"+mYearInt+"】  isShowLog 【"+isShowLog+"】");
			
			
			return super.initParamsWithInputList(inputParamList);
		}
		
		@Override
		boolean allowEmptyDirFileList() {
		// TODO Auto-generated method stub
		return true;
		}
		
		
		
		
		//  【 000001.SH  】   【Map<MMDD,close>>】
		
		
		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
			ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
		// TODO Auto-generated method stub
			// 对当前 的 xlsx 文件 进行 填充数据的操作 

			System.out.println("mYearInt_B 【"+mYearInt+"】");	

	
			File mainStockFileItem = mMatchYearMainStockFile;

			int fileYearInt = mYearInt;
			mYearTradeDayList = 	mYear_TradeDayList_Map.get(fileYearInt);
			ArrayList<Integer> fromNowTradeDayList = calculFromNowTradeDayList(getCurrentYYYYMMDD(),mYearTradeDayList);
			// 检测当前 目前为止  没有记录的 交易日的集合
			ArrayList<Integer> mRecordDayList = calculRecordTradeDayList(fileYearInt,getCurrentYYYYMMDD(),fromNowTradeDayList,mainStockFileItem , SheetHead_Part_1 , realTypeRuleList , mYearTradeDayList);
			int needRecordCount = fromNowTradeDayList.size() - mRecordDayList.size();
			boolean isContainToady = isContainDayInList(getCurrentYYYYMMDD() , mRecordDayList);
			
		
			mNeedAddTradeDayList.addAll(fromNowTradeDayList);
			
//			for (int i = 0; i < mRecordDayList.size(); i++) {
//				int recordDayInt = mRecordDayList.get(i);
//				mNeedAddTradeDayList.remove(Integer.valueOf(recordDayInt));  // 去除对应的Object 
//		      System.out.println("去除已经有数据的日期 "+recordDayInt);
//			}
			
			
			for (int i = 0; i < mNeedAddTradeDayList.size(); i++) {
				int needAddTradeDayInt = mNeedAddTradeDayList.get(i);
				if(mRecordDayList.contains(Integer.valueOf(needAddTradeDayInt))) {
					
					System.out.println("已经有数据的交易日期 "+needAddTradeDayInt+" !  无需检查 json文件  continue ");
					continue;
				}
				
			File matchDayJsonFile = 	getMatchTradeDayJsonFile(needAddTradeDayInt,mDayJsonFileList);
				if(matchDayJsonFile == null) {
					mdAddFaiedTradeDayList.add(needAddTradeDayInt);
					System.out.println("没有匹配到的交易日期 "+needAddTradeDayInt+" 的Json文件为空! ");
					continue;
				}else {
					
					System.out.println("匹配到的交易日期 "+needAddTradeDayInt+" 的Json文件! matchDayJsonFile="+matchDayJsonFile.getAbsolutePath());

				}
				
				
			      StringBuilder mDayjsonSB = new StringBuilder();
                  tryReadJsonFromFile(mDayjsonSB,matchDayJsonFile);
                  
                  
                  com.alibaba.fastjson.JSONObject day_jsonobject =    com.alibaba.fastjson.JSONObject.parseObject(mDayjsonSB.toString());
                  
                  
                  ArrayList<String> keyList =new ArrayList<String>();
                  keyList.addAll( day_jsonobject.keySet());
                  // json 中 应该包含 每日行情 这样的 key 
                  
//                  if(!isContainRiXianHangQing("RiXianXingQingvShiJianWeiXu",keyList)) {
//  					mdAddFaiedTradeDayList.add(needAddTradeDayInt);
//                	  continue;
//                  }

//                  匹配到的交易日期 20211104
                  
                  for (int j = 0; j < keyList.size(); j++) {
					String keyStr = keyList.get(j);
					System.out.println(needAddTradeDayInt+" Key["+j+"]="+keyStr);
				}
                  
                if(!keyList.contains("日线行情v时间为序")) {
                	System.out.println("匹配到的交易日期 "+needAddTradeDayInt+" 的Json文件  不包含 每日行情v时间为序 needAddTradeDayInt【"+needAddTradeDayInt+"】  matchDayJsonFile="+matchDayJsonFile.getAbsolutePath());
					mdAddFaiedTradeDayList.add(needAddTradeDayInt);
              	  continue;
                }

                
                  
                  
                com.alibaba.fastjson.JSONArray mMeiRiHangQingJSONArray = (com.alibaba.fastjson.JSONArray)day_jsonobject.getJSONArray("日线行情v时间为序");
                  
              
                if(mMeiRiHangQingJSONArray == null) {
                	System.out.println("尼玛  从 "+matchDayJsonFile.getName()+" 获取 日线行情v时间为序 数据失败!");
					mdAddFaiedTradeDayList.add(needAddTradeDayInt);
	              	  continue;
                }
                  
  	      		// key【000001(ts_code)】---> value【 key【0811】,value【RiXianXingQingvShiJianWeiXu(四个属性)】 】

                  
	              List<RiXianXingQingvShiJianWeiXu> mRiXianXingQingvShiJianWeiXuList =  mMeiRiHangQingJSONArray.toJavaList(RiXianXingQingvShiJianWeiXu.class);

	              for (int j = 0; j < mRiXianXingQingvShiJianWeiXuList.size(); j++) {
	            	  RiXianXingQingvShiJianWeiXu mMeiRiHangQing = 	  mRiXianXingQingvShiJianWeiXuList.get(j);
	            
	            	
	            	  
	            	  String tsCode = mMeiRiHangQing.getTs_code();
	            	  String matchDayInt = needAddTradeDayInt+"";
	            	  
	            
	        
	            
	            	  
	            	  Map<String,RiXianXingQingvShiJianWeiXu> matchDateMap = 	  mStockPropMap.get(tsCode);
	            	  
	            	  if(matchDateMap == null) {
	            		  
	            		  matchDateMap = new HashMap<String,RiXianXingQingvShiJianWeiXu>();
	            	  }
	            	  
	            	  if(!matchDateMap.containsKey(matchDayInt)) {
	            		  matchDateMap.put(matchDayInt, mMeiRiHangQing);
	            	  }
	            		
	            	  mStockPropMap.put(tsCode, matchDateMap);
	           
	          		// key【000001(ts_code)】---> value【 key【0811】,value【RiXianXingQingvShiJianWeiXu(四个属性)】 】
//	          		Map<String,Map<String,RiXianXingQingvShiJianWeiXu>>  
	          		
	          		
	          		
	            	  
	            	
				}
	      
//                  Map mDay_MeiRiHangQing_Map = new HashMap<String,List<RiXianXingQingvShiJianWeiXu>>();
//	              mDay_MeiRiHangQing_Map.put(needAddTradeDayInt+"", RiXianXingQingvShiJianWeiXu);
	              
	              
	           
                  
                  
                  
				// 开始 转换 json 为 Object  
				
				
//				JSONObject day_jsonobject =    JSONObject.parseObject(jsonSB.toString());
			}
			
			
			// 1. 判断 当前 MainStock 缺少的 数据 往里面填充    
			
			// 2. 依据 本地搜索到的 .xlsx 以及 对应的 json ( 优先使用json 查询数据)
	
			// 显示对应的 数据
			if(isShowLog) {
				  ShowStockPropMap();
			}
			
			
			// 感觉 还不如 创建 新的  20xx_main_stock.xlsx 文件  这样 还更快点
			createMainXlsxWithData(mainStockFileItem,mYearTradeDayList,mStockPropMap);
			
			
			
			
			// 填充对应的数据
			
			
		return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}
		
		
		@SuppressWarnings("unchecked")
		void createMainXlsxWithData(File tmpFile, ArrayList<Integer> mYearAllTradeDayList , Map<String,Map<String,RiXianXingQingvShiJianWeiXu>>  mTsCode_Data_Map ) {

			
		    OutputStream outputStreamExcel = null;
		    tmpFile.delete();   // 删除 原有的文件    从新创建 新文件
	        if (!tmpFile.getParentFile().exists()) {
	            tmpFile.getParentFile().mkdirs();//创建目录
	        }
	        if(!tmpFile.exists()) {
	            try {
					tmpFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("创建文件 mainStock【"+tmpFile.getAbsolutePath()+"】失败A !");
					e.printStackTrace();
				}//创建文件
	        }
	        
	        
	        Workbook workbook = null;
	        workbook = new XSSFWorkbook();//创建Workbook对象(excel的文档对象)
	        
	   
	        
	        for (int i = 0; i < MainStock_SheetChineseNameArr.length; i++) {
	        	
	            Map<String,Integer> headName_ColumnNum_Map = new     HashMap<String,Integer>();
	            
	        	// 收盘价    涨跌幅   涨跌值  成交额
	        	String curShhetName = MainStock_SheetChineseNameArr[i];
	           // 	{"close","pct_chg","change","amount"};
	        	String curSheetName_English = MainStock_SheetEnglishNameArr[i];

	            Sheet mSheet = workbook.createSheet(curShhetName);// 建建sheet对象（excel的表单）
	            
	            // 设置单元格字体
	            Font headerFont = workbook.createFont(); // 字体
	            headerFont.setFontHeightInPoints((short)14);
	            headerFont.setFontName("黑体");
	            
	     
	            Row row = mSheet.createRow(0);
	            
	            
	            int rowIndex = 0 ;
	            
	        
//	            SheetHead_Part_1
	            
	            for (int j = 0; j < SheetHead_Part_1.length; j++) {
	            	// cname【0】  ts_code【1】 
	            	headName_ColumnNum_Map.put(SheetHead_Part_1[j], rowIndex);
	                row.createCell(rowIndex++).setCellValue(SheetHead_Part_1[j]);
				}
	            
	 
	            
	            for (int j = 0; j < realTypeRuleList.size(); j++) {
					String columnName =  realTypeRuleList.get(j).getXlsxDynamicHeader();
					// 有些 规则 是  不显示 在 head  中的  有些规则 则作用在 header 中 
					if(realTypeRuleList.get(j).isShowHeaderInXlsx) {
			        	// dynamicProp【3】   ......  dynamicProp【8】
					 	headName_ColumnNum_Map.put(columnName, rowIndex);
					    row.createCell(rowIndex++).setCellValue(columnName);
					}
		
				}
	            
	            for (int j = mYearAllTradeDayList.size() -1 ; j >= 0 ; j--) {
					int dayFalg = mYearAllTradeDayList.get(j);
					String monthday = (""+dayFalg).substring(4);
					
				   	// 1231【19】   ......  0101【42】
				 	headName_ColumnNum_Map.put(monthday, rowIndex);
		            row.createCell(rowIndex++).setCellValue(monthday);
	            
				}
	            int columnIndex = 1 ;
	   
	            
	            
	            
	        	
				Map.Entry<String,Map<String,RiXianXingQingvShiJianWeiXu>> mOutEntry;

				if (mStockPropMap != null) {
					Iterator mOutIterator = mStockPropMap.entrySet().iterator();
					while (mOutIterator.hasNext()) {
						// ts_code --- XXX【0201  prop】
						mOutEntry = (Map.Entry<String,Map<String,RiXianXingQingvShiJianWeiXu>> ) mOutIterator.next();

						// 获取 名称的 首字母
						String ts_code_key = mOutEntry.getKey(); // Map的Value // 作者名称
						Map<String,RiXianXingQingvShiJianWeiXu>  mData_RiXianHangQing_Map = mOutEntry.getValue();
					
						if(mData_RiXianHangQing_Map == null) {
							continue;
						}
//						logsb.append("ts_code_key(___" + ts_code_key + "___" + mData_RiXianHangQing_Map.size() + "___");
//						System.out.println("ts_code_key(___" + ts_code_key + "___" + mData_RiXianHangQing_Map.size() + "___");
						Map.Entry<String,RiXianXingQingvShiJianWeiXu>  mInnerEntry;
						
						Iterator InnerIterator = mData_RiXianHangQing_Map.entrySet().iterator();
						
					
						boolean isRowInit = false;
						Row rowNext = null ; 
						while (InnerIterator.hasNext()) {
							
							mInnerEntry = (Map.Entry<String,RiXianXingQingvShiJianWeiXu> ) InnerIterator.next();
		
							String tradeDayStr = mInnerEntry.getKey(); // Map的Value // 作者名称
							RiXianXingQingvShiJianWeiXu mRiXianHangQing = mInnerEntry.getValue();
							
							String mMMDD = tradeDayStr.trim().substring(4);
							
							
							String tscode = mRiXianHangQing.ts_code;
							String cname = mRiXianHangQing.cname;
		
			
							
							// amount 的单位是 千元 在 这里 改为 单位为 元 
				
							
							if( rowNext == null) {
								 rowNext = mSheet.createRow(columnIndex++);
							}
							
							rowNext.createCell(0).setCellValue(cname);
							rowNext.createCell(1).setCellValue(tscode);
							
							if(curSheetName_English.equals("close")) {
								double close = mRiXianHangQing.close;
								
								rowNext.createCell(headName_ColumnNum_Map.get(mMMDD)).setCellValue(close);
								
							}	else	if(curSheetName_English.equals("change")) {
								double change = mRiXianHangQing.change;
								
								rowNext.createCell(headName_ColumnNum_Map.get(mMMDD)).setCellValue(change);
								
							}	else	if(curSheetName_English.equals("pct_chg")) {
								double pct_chg = mRiXianHangQing.pct_chg;
								
								rowNext.createCell(headName_ColumnNum_Map.get(mMMDD)).setCellValue(pct_chg);
								
							}	else if(curSheetName_English.equals("amount")) {
								//  由于成交量的 单位是 千元  直观看 不方便   此处把 double 的小数点去掉 并且 小数点后保持三位
//								String keep3PointFixed = clearPointAndKeepBackNum(amount,3);
								Long amountLongNoPoint = mRiXianHangQing.getAmountAsLongNoPoint();
								if(amountLongNoPoint != null) {
									rowNext.createCell(headName_ColumnNum_Map.get(mMMDD)).setCellValue(amountLongNoPoint);

								}
							}
							
							
							
//							headName_ColumnNum_Map
						
//							logsb.append(ts_code_key+"_"+dayIndex+" = "+ mRiXianHangQing.toString());
//							System.out.println(ts_code_key+"_"+dayIndex+" = "+ mRiXianHangQing.toString());
					
					
						}
						
						

					}
				}
				
	            
	            
	            
	            //  这里 写入 数据  遍历Map 
	            //  SheetHead_Part_1    与   两行 强关联 
//	            for (int j = 0; j < tscodeList.size(); j++) {
//					String tscode = tscodeList.get(j);
//					String cname = tScode_StockName_Map.get(tscode);
//		
//			            Row rowNext = mSheet.createRow(columnIndex++);
//			            rowNext.createCell(0).setCellValue(cname);
//			            rowNext.createCell(1).setCellValue(tscode);
//				}
	    
	            
	            
	            
	            
			}
	        try {
				outputStreamExcel = new FileOutputStream(tmpFile);
		        workbook.write(outputStreamExcel);
		        outputStreamExcel.flush();
		        outputStreamExcel.close();
		        
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("创建文件 createMainXlsxWithData  mainStock【"+tmpFile.getAbsolutePath()+"】失败B !");

				e.printStackTrace();
			}

	        
	        


	        if(tmpFile.exists() && tmpFile.length() > 10) {
	            System.out.println(" Rule1 创建 createMainXlsxWithData  "+ tmpFile.getAbsolutePath()+" 文件 Success 成功! ");
	        }else {
	        	
	            System.out.println(" Rule1 创建 createMainXlsxWithData  "+ tmpFile.getAbsolutePath()+" 文件 Failed 失败! ");

	        }

			//  1. 创建 sheet 页面
			//  2. 在 sheet 页面填充 初始化的数据

		}
		
	
			
	
		String clearPointAndKeepBackNum(double amountValue , int pointBackNum){
			String amountResultStr = ""+amountValue;

			if("".equals(amountResultStr)) {
				return "";
			}
			
			// 如果当前 value 包含 点号  那么 计算 点号 后面有所少个值
			if(amountResultStr.contains(".")) {
				String endPointStr = amountResultStr.substring(amountResultStr.lastIndexOf(".")).trim();
				String end3NumStr = "000";
				amountResultStr = 	amountResultStr.replace(endPointStr, end3NumStr);   // .3134 变为  000  把最后三位都变为 0 

			}else {  // 如果当前 没有点号  那么 再 末尾 添加 三个0 
				
				
				amountResultStr = amountResultStr+"000";
			}
			
			
			return amountResultStr;
			
			
			
		}
		
		@SuppressWarnings("unchecked")
		void ShowStockPropMap() {
			

			StringBuilder logsb = new StringBuilder();
			
			Map.Entry<String,Map<String,RiXianXingQingvShiJianWeiXu>> mOutEntry;

			if (mStockPropMap != null) {
				Iterator mOutIterator = mStockPropMap.entrySet().iterator();
				while (mOutIterator.hasNext()) {
					mOutEntry = (Map.Entry<String,Map<String,RiXianXingQingvShiJianWeiXu>> ) mOutIterator.next();

					// 获取 名称的 首字母
					String ts_code_key = mOutEntry.getKey(); // Map的Value // 作者名称
					Map<String,RiXianXingQingvShiJianWeiXu>  mData_RiXianHangQing_Map = mOutEntry.getValue();
				
					if(mData_RiXianHangQing_Map == null) {
						continue;
					}
//					logsb.append("ts_code_key(___" + ts_code_key + "___" + mData_RiXianHangQing_Map.size() + "___");
					System.out.println("ts_code_key(___" + ts_code_key + "___" + mData_RiXianHangQing_Map.size() + "___");
					Map.Entry<String,RiXianXingQingvShiJianWeiXu>  mInnerEntry;
					
					Iterator InnerIterator = mData_RiXianHangQing_Map.entrySet().iterator();
					
					int dayIndex = 0;
					while (InnerIterator.hasNext()) {
						
						mInnerEntry = (Map.Entry<String,RiXianXingQingvShiJianWeiXu> ) InnerIterator.next();
	
						String tradeDayStr = mInnerEntry.getKey(); // Map的Value // 作者名称
						RiXianXingQingvShiJianWeiXu mRiXianHangQing = mInnerEntry.getValue();
						
					
//						logsb.append(ts_code_key+"_"+dayIndex+" = "+ mRiXianHangQing.toString());
						System.out.println(ts_code_key+"_"+tradeDayStr+"_"+dayIndex+" = "+ mRiXianHangQing.toString());
						dayIndex++;
				
					}
					
					

				}
			}
			
			System.out.println("从 day_xxx.json 文件的个数"+ mDayJsonFileList.size()+"    股票列表数 mStockPropMap.size() = "+ mStockPropMap.size() );

		}
		
		
		
		boolean isContainRiXianHangQing(String keyStr , ArrayList<String> keyList) {
			boolean containFlag = false;
			
			for (int i = 0; i < keyList.size(); i++) {
				String chineseKeyStr = keyList.get(i);
				
		        String stcKeyStr_clearChinese_FirstCharUP_Str = ToPinyin_WithFirstBig(chineseKeyStr);
		        
		        if(keyStr.equals(stcKeyStr_clearChinese_FirstCharUP_Str)) {
		        	return true;
		        }
			}
			
			
			
			return containFlag;
			
			
		}
		
	    public  String toUpperFirstChar(String srcStr) {
	        if(srcStr == null) {
	            return "";
	        }
	        String secondStr = srcStr.substring(1).toLowerCase();
	        String firstChar = (srcStr.charAt(0)+"").toUpperCase();

	        return firstChar+secondStr;

	    }
	    
	    public  String  ToPinyin_WithFirstBig(String chinese) {
	        if (chinese == null || chinese.trim().isEmpty()) {
	            return null;
	        }
	        String curItem = new String(chinese);
	        while (curItem.contains(" ")) {
	            curItem = curItem.replaceAll(" ", "");
	        }
	        String pinyinStr = "";
	        char[] newChar = curItem.toCharArray();
	        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
	        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	        for (int i = 0; i < newChar.length; i++) {
	            if (newChar[i] > 128) {
	                try {
	                    pinyinStr += toUpperFirstChar(PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0]); // [0] 标识当前拼音 汉->
	                    // han
	                } catch (BadHanyuPinyinOutputFormatCombination e) {
	                    e.printStackTrace();
	                }
	            } else { // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
	                pinyinStr += (newChar[i]+"");
	            }
	        }
	        return pinyinStr;
	    }
	    
	     void tryReadJsonFromFile(StringBuilder sb, File file) {

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

//	                System.out.println("read json File OK !");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Failed !");
	        }
	    }
		
	    
		
		File getMatchTradeDayJsonFile(int tradeDayInt , ArrayList<File> jsonFileList) {
			File matchJsonFile = null;
			
			for (int i = 0; i < jsonFileList.size(); i++) {
				File jsonFile = jsonFileList.get(i);
				String fileName = jsonFile.getName().toLowerCase();
				
				String onlyDigitalFileName = fileName.replace(".json", "").replace(".", "").replace("_", "").replace("day", "").trim();
				
				if((tradeDayInt+"").equals(onlyDigitalFileName)) {
					return jsonFile;
				}
			}
			
			return matchJsonFile;
			
			
		}
		
		boolean initJ0DataDay_Xlsx_Json_File() {

			
		File[] mFileArr = 	mJ0_Data_Dir_File.listFiles();
			if(mFileArr == null || mFileArr.length == 0) {
				return false;
			}
			
			for (int i = 0; i < mFileArr.length; i++) {
				File item = mFileArr[i];
				String fileName = item.getName().toLowerCase();
				if(fileName.startsWith("day_"+mYearInt+"_") && fileName.endsWith(".xlsx")) {
					
					mDayXlsxFileList.add(item);
				}else if(fileName.startsWith("day_"+mYearInt+"_") && fileName.endsWith(".json")) {
					mDayJsonFileList.add(item);
				}

			}
			
			if(mDayXlsxFileList.size() == 0) {
				return false;
			}
		
				return true;
			
		}
		
		
		
	
		
		@Override
		String simpleDesc() {

			return "\n"  +  Cur_Bat_Name + " #_" + rule_index+"  "+" year_"+mYearInt+"   ###  对当前"+mYearInt+"_main_stock.xlsx 文件进行数据添加  不打印Log    " +
					"\n"  +  Cur_Bat_Name + " #_" + rule_index+"  "+" year_"+mYearInt+"  showlog_true  ###  对当前"+mYearInt+"_main_stock.xlsx 文件进行数据添加 会打印Log    ";
			
		}
		
		
		

		
		
	}
	
	
	
	static class RiXianXingQingvShiJianWeiXu{
	    @Override
		public String toString() {
			return " RiXian [ close="+close+" amount=" + amount + ", change=" + change + ", cname=" + cname
					+ ", trade_date=" + trade_date + ", ts_code=" + ts_code + "]";
		}

		Double amount;
	    Double change;
	    Double close;
	    String cname;
	    Double high;
	    Double low;
	    Double open;
	    Double pct_chg;
	    Double pre_close;
	    String trade_date;
	    String ts_code;
	    Double vol;

	    
		Long getAmountAsLongNoPoint(){
			
			String amountResultStr = ""+amount;

			if("".equals(amountResultStr)) {
				return null;
			}
			
			 Long longValue =   (long) (amount * 1000);
			 
			//	System.out.println("tscode["+ts_code+"] name["+cname+"] trade_date["+trade_date+"]  amountResultStr["+amountResultStr+"]  amount["+amount+"] +  longValue["+longValue+"]");

				
			 return longValue;
	
		}
		
		Long getAmountAsLongNoPointA(){
			String amountResultStr = ""+amount;

			if("".equals(amountResultStr)) {
				return null;
			}
			
			// 如果当前 value 包含 点号  那么 计算 点号 后面有所少个值
			if(amountResultStr.contains(".")) {
				String endPointStr = amountResultStr.substring(amountResultStr.lastIndexOf(".")).trim();
		
				String end3NumStr = "000";
				amountResultStr = 	amountResultStr.replace(endPointStr, end3NumStr);   // .3134 变为  000  把最后三位都变为 0 

			}else {  // 如果当前 没有点号  那么 再 末尾 添加 三个0 
				
				
				amountResultStr = amountResultStr+"000";
			}
			
			Long longValue = Long.parseLong(amountResultStr);
			
			System.out.println("tscode["+ts_code+"] name["+cname+"] trade_date["+trade_date+"]  amountResultStr["+amountResultStr+"]  amount["+amount+"] +  longValue["+longValue+"]");

			
			return longValue;
			
			
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
	
	
	// 分析在  J0_Data_Dir_Path   zbin/J0_Data/中的  daily_basic_*.xlsx  输出一个XLSX结果  daily_basic_202108 
	class  Daily_basic_YYYYMM_XLSX_Rule_0 extends  Basic_Rule{

		ArrayList<File>  mAllDailyBasicXlsxFileList ;   // 所有在  /zbin/J0_Data/中的 daily_basic_*.xlsx 文件
		ArrayList<Stock_NodeImpl> allStockDailyBasicList;  // 所有的股票列表 以及 这个股票的 daily_basic.xlsx 信息
		
		
		 Daily_basic_YYYYMM_XLSX_Rule_0() {
			super("#", 0, 4); //
			// TODO Auto-generated constructor stub
			mAllDailyBasicXlsxFileList = new ArrayList<File>();
			allStockDailyBasicList =  new ArrayList<Stock_NodeImpl>();
		
		}
		
	 void 	showFileArrayList(ArrayList<File> fileList){
			
	
	for (int i = 0; i < fileList.size(); i++) {
		File fileItem = fileList.get(i);
		System.out.println("file["+i+"]  = "+ fileItem.getAbsolutePath());
	}
		 System.out.println("fileList.size() = "+ fileList.size());
			
		}
	 
	 
		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
		// TODO Auto-generated method stub
		
			File J0_Data_Dir_File = new File(J0_Data_Dir_Path);
			
			if(!J0_Data_Dir_File.exists()) {
				System.out.println(" J0_Data 路径 "+ J0_Data_Dir_Path +" 文件不存在 程序停止执行 请检查!! 该路径");
			   return false;
			}
			
			
			File[]  allJ0DataFileArr = J0_Data_Dir_File.listFiles();
			
			for (int i = 0; i < allJ0DataFileArr.length; i++) {
				File curFile = allJ0DataFileArr[i];
				String fileName = curFile.getName();
				
				if(fileName.startsWith("daily_basic_")) {
					mAllDailyBasicXlsxFileList.add(curFile);
				}
			}
			
			
		
			System.out.println("___________"+"/zbin/J0_Data/daily_basic_*.xlsx 文件信息 Begin "+"___________");
			showFileArrayList(mAllDailyBasicXlsxFileList);
			System.out.println("___________"+"/zbin/J0_Data/daily_basic_*.xlsx 文件信息 End   "+"___________");

			
			tryInitStockItem(mAllDailyBasicXlsxFileList);
			
			return super.initParamsWithInputList(inputParamList);
		}
		
		
		
		
		void tryInitStockItem(ArrayList<File> mDailyBasicFileList) {
			
			
			int all_i = mDailyBasicFileList.size();
			
			for (int i = 0; i < mDailyBasicFileList.size(); i++) {
				final File mDailyBasicFile = mDailyBasicFileList.get(i);
				String mDailtBasicFileName = mDailyBasicFile.getName();
				
				final int fileIndex = i ;
		
				
				Runnable curRunnable  =  new Runnable() {
					public void run() {
						

				        Workbook wb =null;
				
				        Row row = null;
				        
				     // 一个Xlsx 多个 Sheet页的内容  nima  
				        List<List<Map<String,String>>> mXlsxDataList = null; 
				        
				        List<Map<String,String>> mSheetDataList = null;   // 一个Sheet页的内容
				        
				        String cellData = null;
				        String filePath = mDailyBasicFile.getAbsolutePath();
				        System.out.println("xlsx Path = "+ filePath);
				     //   String columns[] = {"ts_code","symbol","name","area","industry","fullname","enname","market","exchange","curr_type","list_status","list_date","delist_date","is_hs"};
				        
				        String columns[] = {"cname","ts_code","trade_date","close","turnover_rate","turnover_rate_f","volume_ratio","pe","pe_ttm","pb","ps","ps_ttm","dv_ratio","dv_ttm","total_share","float_share","free_share","total_mv","circ_mv"};

				        
				        wb = readExcel(filePath);

				        if(wb != null){
				        	// 仅仅 初始化
				            mSheetDataList = new ArrayList<Map<String,String>>();
				            mXlsxDataList = new  ArrayList<List<Map<String,String>>>();
				            //获取第一个sheet
//				            sheet = wb.getSheet("股票列表");
//				            sheet = wb.getSheetAt(0);
//				          Iterator<Sheet> sheetIterator =   wb.sheetIterator(); 
				          int sheetCount =    wb.getNumberOfSheets();
				           System.out.println();
				          System.out.println("fileIndex["+fileIndex+"]"+" SheetNums = "+ sheetCount + "  xlsx_Path = "+ filePath);
				           int sheetIndex = 1;
				               int all_j = sheetCount - 1;
				           for (int j = 0; j < sheetCount; j++) {
				        		  Sheet  mCurSheet =    wb.getSheetAt(j);
				            	  String sheetName = mCurSheet.getSheetName();
				            	  if("Sheet".equals(sheetName)) {
				            		  continue;
				            	  }
					        	  System.out.println("FileName["+mDailtBasicFileName+"]"+"  SheetIndex["+sheetIndex+"]"+ " SheetName["+sheetName+"]");
					        	  sheetIndex++;
					        	  
					        	  
						            //用来存放表中数据  ArrayItem【Map<String,String>】 是一个sheet页的一行的数据
					              //用来存放表中数据
					              mSheetDataList = new ArrayList<Map<String,String>>();
					              //获取第一个sheet
//					              sheet = wb.getSheet("股票列表");
//					       
					              //获取最大行数
					              int rownum = mCurSheet.getPhysicalNumberOfRows();
					              //获取第一行
					              row = mCurSheet.getRow(0);
					              //获取最大列数
					              int colnum = row.getPhysicalNumberOfCells();
					              int all_k = rownum - 1;
					              int all_z = colnum - 1;
					              for (int k = 1; k<rownum; k++) {
					                  Map<String,String> map = new LinkedHashMap<String,String>();
					                  row = mCurSheet.getRow(k);
					                  if(row !=null){
					                      for (int z=0;z<colnum  ;z++){
					                          cellData = (String) getCellFormatValue(row.getCell(z));
					                          map.put(columns[z], cellData);
					                          System.out.println("i["+fileIndex+"]["+all_i+"]"+"_j["+j+"]["+all_j+"]"+"_k["+k+"]["+all_k+"]"+"_z["+z+"]["+all_z+"]__"+"columns[z]=["+columns[z]+"]  cellData=["+cellData+"]");
					                      }
					                  }else{
					                      break;
					                  }
					                  mSheetDataList.add(map);  // 增加一行
					              }
					              
					              
					              mXlsxDataList.add(mSheetDataList);  //   添加一个sheet 
					              
						           System.out.println("mXlsxDataList.size()="+mXlsxDataList.size()+"    mSheetDataList.size() = "+ mSheetDataList.size()+"  ");
						}
			

				        }   //   if(wb != null)  End 
				        //遍历解析出来的list
				        //  解析当前的 mXlsxDataList 解析出的全部数据   
				        // 由于程序数据太大 耗时太大 所以继续写下去该 Rule1 意义不大 需要换思维处理
				       
						
				          System.out.println("Thread_Runnable_OVER!! "+"fileIndex["+fileIndex+"]  filePath["+ filePath+"]");

					}
				};
				

				Thread readXlsxThread = new Thread(curRunnable);
				readXlsxThread.start();
		        
			}
			
		}
		
		
		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
			ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
		// TODO Auto-generated method stub
		return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}
		
		
		@Override
		String simpleDesc() {

			return "\n"  +  Cur_Bat_Name + " #_" + rule_index+"   ###  【太耗时_可行性失败_废弃】针对 Desktop/zbin/J0_Data/ 目录下的   daily_basic_YYYYMM.xlsx  进行客制分析输出 一个 .xlsx的说明文件    ";
		}
		
		
	}
	

	
	
	public class Stock_NodeImpl {
		
         String cname;
         String ts_code;
	    
         Map<String,Stock_Daily_Basic> mDailyBasicMap;  //  当前股票的 每日的信息
	    
	}
	
	
	
	public class Stock_Daily_Basic {
	String	cname	            ;
	String	ts_code	            ;
	String	trade_date	        ;
	String	close	            ;
	String	turnover_rate	    ;
	String	turnover_rate_f	    ;
	String	volume_ratio	    ;
	String	pe	                ;
	String	pe_ttm	            ;
	String	pb	                ;
	String	ps	                ;
	String	ps_ttm	            ;
	String	dv_ratio	        ;
	String	dv_ttm	            ;
	String	total_share	        ;
	String	float_share	        ;
	String	free_share	        ;
	String	total_mv	        ;
	String	circ_mv             ;

		

	}
	
	
	

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

		String getXlsxDynamicHeader() {
			return "动态"+dynamicIndexInList;
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
				System.out.println(oldName + " 转为 " + newFilePath + " 失败！");
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

		boolean isShowHeaderInXlsx ;  //  Rule的规则是否在  main_stock.xlsx 中显示
		int dynamicIndexInList ;    // 在 List 中的位置
		
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

		
		abstract String getXlsxDynamicHeader(); //  在 xlsx中的动态计算的 head 描述

		
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
		if (str.equals("")) {
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

	
	static void initYYYYMainStockXlsx() {
		
	
	    File J0_Data_File = new File(J0_Data_Dir_Path);
	    if(!J0_Data_File.exists() || J0_Data_File.listFiles() == null ) {
	    	
	    	
	    	System.out.println("当前 "+J0_Data_Dir_Path+"路径中 不存在数据文件!");
	    	return;
	    	
	    }
	    File[] J0DataFileArr = J0_Data_File.listFiles();
	    
	    
		for (int i = 0; i < J0DataFileArr.length; i++) {
			File curFile = J0DataFileArr[i];
			String fileName = curFile.getName().toLowerCase();
			//   把 J0_Data 中 以  main_stock.xlsx 结尾的文件集合起来 
			if(fileName.endsWith("main_stock.xlsx") && !fileName.contains("~$") ) {
				mYearMainStockFileList.add(curFile);
			}
		}

	}
	
	static void showRuleMethod() {
		
		System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
		System.out.println("当前已实现的替换逻辑如下:\n");

		int count = 1;
		System.out.println("═══════════════════" + "使用方法列表 Begin" + "═══════════════════" + "\n");
		for (int i = 0; i < realTypeRuleList.size(); i++) {
			Rule itemRule = realTypeRuleList.get(i);
			String type = itemRule.file_type;
			int index = itemRule.rule_index;
			String desc = itemRule.ruleTip(type, index, J0_Bat_Name, CUR_OS_TYPE);

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
	

	static boolean isContainNowYearMainStockFile( ArrayList<File> mMainStockFileList ) {
		boolean isContainFla = false;
		
		for (int i = 0; i < mMainStockFileList.size(); i++) {
			File mainStockFile = mMainStockFileList.get(i);
			String mMainStockName = mainStockFile.getName();
//			System.out.println("mMainStockName="+mMainStockName+"    Cur=" + getCurrentYear()+"_main_stock.xlsx");
			if(mMainStockName.equals(getCurrentYear()+"_main_stock.xlsx")) {
				return true;
			}
		}

		return isContainFla;

	}
	static void showMainFileStatus() {
	
		// 打印当前文件的   main_stock.xlsx 文件情况
		System.out.println("═══════════════════" + " xxxx_main_stock.xlsx  情况 Begin" + "═══════════════════" + "\n");

		
		// 当前目录 没有任何一个 main_stock.xlsx 活着当前
	
		if(mYearMainStockFileList.size() == 0 || !isContainNowYearMainStockFile(mYearMainStockFileList) ) {
	
			System.out.println("当前目录没有任何 xxxx_main_stock.xslx 将初始化  以及今年的 main_stock.xlsx 文件【"+getCurrentYear()+"_main_stock.xlsx】"+ "\n");
			
			initYearMainStockXlsxForYear(getCurrentYear() );
			
		}else {
		
			//  分析 当前 xlsx 文件的 记录 项数据
			 initTradeDayList();
//			 【年总项:244】【至今项:155】【记账项:44】【遗落项:67】【今日账:false】
			for (int i = 0; i < mYearMainStockFileList.size(); i++) {
				File mainStockFileItem = mYearMainStockFileList.get(i);
				String fileName = mainStockFileItem.getName();
				String yearFromFileName = fileName.substring(0,4);
				int fileYearInt = Integer.parseInt(yearFromFileName);
				
				StringBuilder mTipSb = new StringBuilder();
		    	ArrayList<Integer> yearTradeDayList = 	mYear_TradeDayList_Map.get(fileYearInt);
				String mYearTip = "【年总项:"+yearTradeDayList.size()+"】";
				ArrayList<Integer> fromNowTradeDayList = calculFromNowTradeDayList(getCurrentYYYYMMDD(),yearTradeDayList);
				String mFromNowTip = "【至今项:"+fromNowTradeDayList.size()+"】";
				// 检测当前 目前为止  没有记录的 交易日的集合
				ArrayList<Integer> mRecordDayList = calculRecordTradeDayList(fileYearInt,getCurrentYYYYMMDD(),fromNowTradeDayList,mainStockFileItem , SheetHead_Part_1 , realTypeRuleList , yearTradeDayList);
				
				String mRecordTip = "【记账项:"+mRecordDayList.size()+"】";
				int needRecordCount = fromNowTradeDayList.size() - mRecordDayList.size();
				String mNeedRecordTip = "【遗落项:"+needRecordCount+"】";
				boolean isContainToady = isContainDayInList(getCurrentYYYYMMDD() , mRecordDayList);
				String mRecordToday = "【今日账:"+isContainToady+"】";
				mTipSb.append(mYearTip+" "+mFromNowTip+" "+mRecordTip+" "+mNeedRecordTip+" "+mRecordToday );
				
				mTipSb.append("\n");
				System.out.println("MainStock["+(i+1)+"_"+(mYearMainStockFileList.size())+"]:"+fileName+" "+ mTipSb.toString());
				
			}
		}
		
		
		System.out.println("═══════════════════" + " xxxx_main_stock.xlsx  情况 End" + "═══════════════════" + "\n");

		

	}
	
	static boolean isContainDayInList(int curDayInt , ArrayList<Integer> intList) {
		boolean flag = false;
		if(intList == null || intList.size() == 0) {
			return false;
		}
		
		for (int i = 0; i < intList.size(); i++) {
			int itemInt = intList.get(i);
			if(curDayInt == itemInt) {
				return true;
			}
		}
		
		
		return flag;
		
	}
	
	// 读取文件 去 检测 当前目前 为止 还没有记录的 日期的集合 
	static ArrayList<Integer> 	calculRecordTradeDayList( int nowYear , int nowYYYYMMDD ,
			ArrayList<Integer> mFromNowOnTradeDayList , File mMainStockXlsxFile , 
			String [] SheetHead_Part_1_tscodeName  ,  ArrayList<Rule>  SheetHead_Part_B_RuleList , 
			ArrayList<Integer> SheetHead_Part_C  ){
		
		
		
		 ArrayList<Integer> fromNowDayList = new  ArrayList<Integer> ();
		
 // SheetHead_Part_1
 // ShhetHead_Part_2
		 
ArrayList<String> columnHeadArr = new ArrayList<String>();

for (int i = 0; i < SheetHead_Part_1_tscodeName.length; i++) {
	columnHeadArr.add(SheetHead_Part_1_tscodeName[i]);
}

for (int i = 0; i < SheetHead_Part_B_RuleList.size(); i++) {
	if(SheetHead_Part_B_RuleList.get(i).isShowHeaderInXlsx) {
		columnHeadArr.add(SheetHead_Part_B_RuleList.get(i).getXlsxDynamicHeader());
	}

}



for (int i = SheetHead_Part_C.size() -1 ; i >= 0 ; i--) {
	columnHeadArr.add((SheetHead_Part_C.get(i)+"").substring(4));
}




String[] columns =  new String[columnHeadArr.size()];

for (int i = 0; i < columnHeadArr.size(); i++) {
	columns[i] = columnHeadArr.get(i);
//	System.out.println("columns["+i+"]  = " + columns[i]);
}





		   Workbook mainStockWorkBook  =null;
	        Sheet mainStockSheet = null;
	        Row mainStockRow = null;
	        List<Map<String,String>> mainStockList = null;
	        String mainStockCellData = null;

	        mainStockWorkBook = readExcel(mMainStockXlsxFile.getAbsolutePath());
          
	        
	        if(mainStockWorkBook != null){
	            //用来存放表中数据
	        	// 行数据集合
	        	// 每个行都是一个Map     Map的Key 是 Colum的名称  Value 是单元格的值
	        	mainStockList = new ArrayList<Map<String,String>>();
	            //获取第一个sheet
//	            sheet = wb.getSheet("股票列表");
//	            sheet = wb.getSheetAt(0);
	            mainStockSheet = mainStockWorkBook.getSheet(MainStock_SheetChineseNameArr[0]);
	            //获取最大行数
	            int rownum = mainStockSheet.getPhysicalNumberOfRows();
	            //获取第一行
	            mainStockRow = mainStockSheet.getRow(0);
	            //获取最大列数
	            int colnum = mainStockRow.getPhysicalNumberOfCells();
	            for (int i = 1; i<rownum; i++) {
	                Map<String,String> map = new LinkedHashMap<String,String>();
	                mainStockRow = mainStockSheet.getRow(i);
	                if(mainStockRow !=null){
	                    for (int j=0;j<colnum;j++){
	                    	mainStockCellData = (String) getCellFormatValue(mainStockRow.getCell(j));
	                        map.put(columns[j], mainStockCellData);
	                    }
	                }else{
	                    break;
	                }
	                mainStockList.add(map);
	            }
	            
	            
	            //遍历解析出来的list
	            
	            
	        }
	        
	        if(mainStockList == null || mainStockList.size() == 0 ) {
	        	
	        	System.out.println(" 读取 " + mMainStockXlsxFile.getAbsolutePath() +" 文件失败! ");
	       	 return fromNowDayList;
	        
	        }
	        
//	 System.out.println(" 读取 " + mMainStockXlsxFile.getAbsolutePath() +" 文件 共 " + mainStockList.size() +" 行 !" +"  至今交易日数量:  "+mFromNowOnTradeDayList.size());
   	
	        	
	      
	        for (int i = 0; i < mFromNowOnTradeDayList.size(); i++) {
	        	Random rd = new Random();
	        	int nowOnDayInt = mFromNowOnTradeDayList.get(i);
	        	String mMMDD = (""+nowOnDayInt).substring(4).trim();
//	        	System.out.println("MMDD = "+ mMMDD);
	        	if(mMMDD.length() != 4) {
	        		continue;
	        	}
	        	
	        	int randomIndexA = rd.nextInt(mainStockList.size()-1) +1;
	        	int randomIndexB = rd.nextInt(mainStockList.size()-1) +1;
	        	int randomIndexC = rd.nextInt(mainStockList.size()-1) +1;
	        	
	        	   Map<String,String> mRowMapA=  	mainStockList.get(randomIndexA);
	          	   Map<String,String> mRowMapB=  	mainStockList.get(randomIndexB);
	          	   Map<String,String> mRowMapC=  	mainStockList.get(randomIndexC);
	          	   
	             	String CellA =  mRowMapA.get(mMMDD);
	            	String CellB =  mRowMapB.get(mMMDD);
	            	String CellC =  mRowMapC.get(mMMDD);
	            	
	            
	            	// 如果三个 都不等于 null  那么说明 这个 项 就没有 初始化了   是 
	            	// 只要三个 项  其中 一个项 不是空  那么说明  这天的 数据填充过
	            	if( (CellA != null && !"".equals(CellA))
	            		||	(CellB != null && !"".equals(CellB))
	               		||	(CellC != null && !"".equals(CellC))		) {
	            		fromNowDayList.add(nowOnDayInt);
//	    	        	System.out.println("MMDD = "+ mMMDD+"  nowOnDayInt="+nowOnDayInt+" RA["+randomIndexA+"]【CellA="+CellA+"】"+"  RB["+randomIndexB+"]【CellB="+CellB+"】"+"  RC["+randomIndexC+"]【CellC="+CellC+"】");

	            	}
	            	
//    	        	System.out.println("MMDD = "+ mMMDD+"  nowOnDayInt="+nowOnDayInt+" RA["+randomIndexA+"]【CellA="+CellA+"】"+"  RB["+randomIndexB+"]【CellB="+CellB+"】"+"  RC["+randomIndexC+"]【CellC="+CellC+"】");

    	        	
	          	   
			}
	        
	        
//	        System.out.println("当前 记录的 日期 数量为: "+ fromNowDayList.size() );
	        
	        for (int i = 0; i < fromNowDayList.size(); i++) {
//				System.out.println("recordIndex["+i+"_"+fromNowDayList.size()+"] = "+fromNowDayList.get(i));
			}
	        
	        
	        
       
		 return fromNowDayList;
	}
	
	
	static ArrayList<Integer>  calculFromNowTradeDayList( int nowDay ,  ArrayList<Integer> curYearTradeDayList ){
		 ArrayList<Integer> fromNowDayList = new  ArrayList<Integer> ();
		 
		 
		 for (int i = 0; i < curYearTradeDayList.size(); i++) {
		int curDay = 	 curYearTradeDayList.get(i);

		if(nowDay >= curDay ) {
			
			fromNowDayList.add(curDay);
			
//			System.out.println("add index["+i+ "]   nowDay="+nowDay+"   curDay="+curDay);
			
		}else {
			
//			System.out.println("noadd index["+i+ "]   nowDay="+nowDay+"   curDay="+curDay);
		}
			 
			
		}
		
		
		 return fromNowDayList;
		 
		
	}
	

	static void initYearMainStockXlsxForYear(int curYear){
		
		String curYearMainStockXlsxName = curYear+"_main_stock.xlsx";
		File targetFile = new File(J0_Data_Dir_Path+File.separator+curYearMainStockXlsxName);
		
		
		// 创建 这个 xlsx 文件 
		//  1.从 交易日历 读取到 今年的交易日期的集合  
		//  2.从 股票列表 读取到 今年的 股票列表
		//  3. 动态计算的列表 需要预留 留他个 20 个动态列表  组成一个新的xlsx 文件 
		
		 initTsCodeList();
		 
		 ArrayList<Rule> dynamicColumn = realTypeRuleList ;   // 动态计算column逻辑的项
		 initTradeDayList();
		
		 // 
		ArrayList<Integer> matchTradeDayList =  mYear_TradeDayList_Map.get(curYear);
//		TScode_StockName_Map    
//		TScode_List
		
		
		initYearMainStockXlsxWithData_createxlsx(curYear,targetFile,TScode_List,TScode_StockName_Map,realTypeRuleList,matchTradeDayList);
		
		
	}
	

	
	static void initYearMainStockXlsxWithData_createxlsx(int curYear , File tmpFile ,
			ArrayList<String> tscodeList , Map<String, String> tScode_StockName_Map ,
			ArrayList<Rule> ruleList , ArrayList<Integer> tradeDayList ) {

		
	    OutputStream outputStreamExcel = null;
        if (!tmpFile.getParentFile().exists()) {
            tmpFile.getParentFile().mkdirs();//创建目录
        }
        if(!tmpFile.exists()) {
            try {
				tmpFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("创建文件 mainStock【"+tmpFile.getAbsolutePath()+"】失败A !");
				e.printStackTrace();
			}//创建文件
        }
        
        
        Workbook workbook = null;
        workbook = new XSSFWorkbook();//创建Workbook对象(excel的文档对象)
        
        for (int i = 0; i < MainStock_SheetChineseNameArr.length; i++) {
        	String curShhetName = MainStock_SheetChineseNameArr[i];
        	

            Sheet mSheet = workbook.createSheet(curShhetName);// 建建sheet对象（excel的表单）
            
            // 设置单元格字体
            Font headerFont = workbook.createFont(); // 字体
            headerFont.setFontHeightInPoints((short)14);
            headerFont.setFontName("黑体");
            
     
            Row row = mSheet.createRow(0);
            
            
            int rowIndex = 0 ;
            
        
//            SheetHead_Part_1
            
            for (int j = 0; j < SheetHead_Part_1.length; j++) {
                row.createCell(rowIndex++).setCellValue(SheetHead_Part_1[j]);
			}
            
 
            
            for (int j = 0; j < ruleList.size(); j++) {
				String columnName =  ruleList.get(j).getXlsxDynamicHeader();
				// 有些 规则 是  不显示 在 head  中的  有些规则 则作用在 header 中 
				if(ruleList.get(j).isShowHeaderInXlsx) {
				    row.createCell(rowIndex++).setCellValue(columnName);
				}
	
			}
            
            for (int j = tradeDayList.size() -1 ; j >= 0 ; j--) {
				int dayFalg = tradeDayList.get(j);
				String monthday = (""+dayFalg).substring(4);
	            row.createCell(rowIndex++).setCellValue(monthday);
            
			}
            int columnIndex = 1 ;
   
            //  SheetHead_Part_1    与   两行 强关联 
            for (int j = 0; j < tscodeList.size(); j++) {
				String tscode = tscodeList.get(j);
				String cname = tScode_StockName_Map.get(tscode);
	
		            Row rowNext = mSheet.createRow(columnIndex++);
		            rowNext.createCell(0).setCellValue(cname);
		            rowNext.createCell(1).setCellValue(tscode);
			}
    
		}
        try {
			outputStreamExcel = new FileOutputStream(tmpFile);
	        workbook.write(outputStreamExcel);
	        outputStreamExcel.flush();
	        outputStreamExcel.close();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("创建文件 mainStock【"+tmpFile.getAbsolutePath()+"】失败B !");

			e.printStackTrace();
		}

        
        


        if(tmpFile.exists() && tmpFile.length() > 10) {
            System.out.println(" 创建 "+ tmpFile.getAbsolutePath()+" 文件 Success 成功! ");
        }else {
        	
            System.out.println(" 创建 "+ tmpFile.getAbsolutePath()+" 文件 Failed 失败! ");

        }

        
        
		
		//  1. 创建 sheet 页面
		//  2. 在 sheet 页面填充 初始化的数据
		
		
		
	}
	
	static void showTip() {
	
		showRuleMethod();
		showMainFileStatus();
		
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

		initSystemInfo();

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

		mJ0_Object = new J0_GuPiao_Analysis();
		mJ0_Object.initrule();
		

		File mCurDirFile = new File(curDirPath);
		curDirFile = new File(curDirPath);

		// 初始化  mYY
		initYYYYMainStockXlsx();
		
		
		if (mKeyWordName.size() == 0) {
			showTip();
			return;
		}

		if (!checkInputParamsOK()) {
			System.out.println("当前用户输入的格式错误  或者 输入参数不符合 规则要求  请检查  ");
			return;
		}

		if (curDirFile == null || !mCurDirFile.exists() || !mCurDirFile.isDirectory()) {
			System.out.println("当前执行替换逻辑的文件路径:" + curDirPath + "  不存在! ");
			return;
		}


		
		// 通过 shell中输入参数来进行操作
		// Rule_Identify_TypeIndexList.add("html_1"); // 1.添加处理的类型文件 类型_该类型的处理逻辑索引
		// 索引从1开始
		initTsCodeList();   //  开始 初始化  TS_Code
		initTradeDayList();
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

	static ArrayList<Rule> realTypeRuleList = new ArrayList<Rule>(); // 规则的集合

	static Rule getRuleByIndex(int index) {
		for (int i = 0; i < realTypeRuleList.size(); i++) {
			if (realTypeRuleList.get(i).rule_index == index) {
				return realTypeRuleList.get(i);
			}
		}
		return null;
	}

	ArrayList<File> getSubTypeFileWithPoint(File dirFile, String pointType) {
		ArrayList<File> targetFileList = new ArrayList<File>();
		String fillterFileStr = "" + pointType.toLowerCase();
		if (!dirFile.isDirectory()) {
			return targetFileList;
		}
		File[] allSubFileList = dirFile.listFiles();
		for (File curFile : allSubFileList) {
			String fileName = curFile.getName().toLowerCase();
			if (fileName.endsWith(fillterFileStr)) {
				targetFileList.add(curFile);
			}
		}

		return targetFileList;
	}

	static String getTimeStampLong() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getTimeStamp_YYYYMM() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getTimeStamp_YYYY_MM() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getTimeStamp() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static Rule getRuleByIdentify(String identify) {
		for (int i = 0; i < realTypeRuleList.size(); i++) {
			if (realTypeRuleList.get(i).identify.equals(identify)) {
				return realTypeRuleList.get(i);
			}
		}
		return null;
	}

	public static void fileCopy(File origin, File target) {
		InputStream input = null;
		OutputStream output = null;
		int lengthSize;
		// 创建输入输出流对象
		try {
			input = new FileInputStream(origin);
			output = new FileOutputStream(target);
			// 获取文件长度
			try {
				lengthSize = input.available();
				// 创建缓存区域
				byte[] buffer = new byte[lengthSize];
				// 将文件中的数据写入缓存数组
				input.read(buffer);
				// 将缓存数组中的数据输出到文件
				output.write(buffer);

			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (Exception e) {

		} finally {
			if (input != null && output != null) {
				try {
					input.close(); // 关闭流
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String execCMD_Windows(String command) {
//        System.out.println("══════════════Begin ExE ");
		StringBuilder sb = new StringBuilder();
		StringBuilder errorSb = new StringBuilder();
		try {

			Process process = Runtime.getRuntime().exec("CMD.exe /c start /B " + command);

			InputStreamReader inputReader = new InputStreamReader(process.getInputStream(), "GBK");
			BufferedReader bufferedReader = new BufferedReader(inputReader);
			String line;
			int waitFor = process.waitFor();
//            Stream<String> lines = bufferedReader.lines();
//            lines.iterator();
//            System.out.println("line Count = "+lines.count());

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");

			}

			boolean isAlive = process.isAlive();
			int errorSteamCode = process.getErrorStream().read();

			String errorStream = process.getErrorStream().toString();
			int exitValue = process.exitValue();
//            process.getErrorStream().
			// 杀掉进程
//            System.out.println("exitValue ="+ exitValue);
			sb.append("\nexitValue = " + exitValue + "\nisAlive = " + isAlive + "\nerrorStream = " + errorStream
					+ "\nerrorSteamCode = " + errorSteamCode + "\nwaitFor = " + waitFor);
//            process.destroy();

		} catch (Exception e) {
			System.out.println("execCMD 出现异常! ");
			return e.toString();
		}

//        System.out.println("sb.toString() = "+ sb.toString());
//        System.out.println("══════════════End ExE ");
		return sb.toString();
	}

	/**
	 * 执行 mac(unix) 脚本命令~
	 *
	 * @param command
	 * @return
	 */
	public static String execCMD_Mac(String command) {
		String[] cmd = { "/bin/bash" };
		Runtime rt = Runtime.getRuntime();
		Process proc = null;
		try {
			proc = rt.exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 打开流
		OutputStream os = proc.getOutputStream();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

		try {
			bw.write(command);

			bw.flush();
			bw.close();

			/** 真奇怪，把控制台的输出打印一遍之后竟然能正常终止了~ */
//            readConsole(proc);

			/** waitFor() 的作用在于 java 程序是否等待 Terminal 执行脚本完毕~ */
			proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int retCode = proc.exitValue();
		if (retCode != 0) {
			System.out.println("unix script retCode = " + retCode);

//            System.out.println(readConsole(proc));
			System.out.println("UnixScriptUil.execute 出错了!!");
		}
		return retCode + "";
	}
	
	
	static int getCurrentYYYYMMDD() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		return Integer.parseInt(df.format(new Date()));

	}
	

	static int getCurrentYear() {

		SimpleDateFormat df = new SimpleDateFormat("YYYY");

		return Integer.parseInt(df.format(new Date()));

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

	public static String execCMD(String command) {

		String result = "";
		if (CUR_OS_TYPE == OS_TYPE.Windows) {
			return execCMD_Windows(command);
		} else if (CUR_OS_TYPE == OS_TYPE.MacOS) {

			return execCMD_Mac(command);
		} else {

			execCMD_Mac(command);
		}
		return result;
	}

	/**
	 * 执行 mac(unix) 脚本命令~
	 * 
	 * @param command
	 * @return
	 */
	public static int execute_Mac(String command) {
		String[] cmd = { "/bin/bash", "-c", command };
		Runtime rt = Runtime.getRuntime();
		Process proc = null;
		try {
			proc = rt.exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 打开流
//        OutputStream os = proc.getOutputStream();
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

		try {
//            String newCommand = "/bin/bash -c "+"\""+command+"\"";
//            System.out.println("newCommand = " + newCommand);

//            bw.write(newCommand);

//
//            bw.flush();
//            bw.close();

			/** 真奇怪，把控制台的输出打印一遍之后竟然能正常终止了~ */
//            readConsole(proc);

			/** waitFor() 的作用在于 java 程序是否等待 Terminal 执行脚本完毕~ */
			proc.waitFor();
			Thread.sleep(100000);

		} catch (Exception e) {
			e.printStackTrace();
		}
//        int retCode = proc.exitValue();
//        if (retCode != 0) {
//            System.out.println("unix script retCode = " + retCode);
//
//            System.out.println(readConsole(proc));
//            System.out.println("UnixScriptUil.execute 出错了!!");
//        }

		return 0;
	}



	static void SortString(ArrayList<String> strList) {
		Comparator<Object> CHINA_COMPARE = Collator.getInstance(Locale.CHINA);
		strList.sort((o1, o2) -> {
			// 比较的基本原则，拿最小长度的字符串进行比较，若全部相等，则长字符串往后排

			int len1 = o1.length();
			int len2 = o2.length();
			int len = (len1 - len2) <= 0 ? len1 : len2;
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i < len; i++) {
				String s1 = o1.substring(i, i + 1);
				String s2 = o2.substring(i, i + 1);
				if (isNumericFirstChar(s1) && isNumericFirstChar(s2)) {
					// 取出所有的数字
					sb1.append(s1);
					sb2.append(s2);
					// 取数字时，不比较
					continue;
				}
				if (sb1.length() != 0 && sb2.length() != 0) {
					if (!isNumericFirstChar(s1) && !isNumericFirstChar(s2)) {
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
			// 这一步：是为了防止以下情况：第10 第20，正好以数字结尾，且字符串长度相等
			if (len1 == len2 && sb1.length() != 0 && sb2.length() != 0) {
				int value1 = Integer.valueOf(sb1.toString());
				int value2 = Integer.valueOf(sb2.toString());
				return value1 - value2;
			}
			// 若前面都相等，则直接比较字符串的长度，长的排后面，短的排前面
			return Integer.compare(len1, len2);
		});

	}

	static void SortFileWithName(ArrayList<File> fileList) {
		Comparator<Object> CHINA_COMPARE = Collator.getInstance(Locale.CHINA);
		fileList.sort((o1_file, o2_file) -> {
			// 比较的基本原则，拿最小长度的字符串进行比较，若全部相等，则长字符串往后排
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
				if (isNumericFirstChar(s1) && isNumericFirstChar(s2)) {
					// 取出所有的数字
					sb1.append(s1);
					sb2.append(s2);
					// 取数字时，不比较
					continue;
				}
				if (sb1.length() != 0 && sb2.length() != 0) {
					if (!isNumericFirstChar(s1) && !isNumericFirstChar(s2)) {
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
			// 这一步：是为了防止以下情况：第10 第20，正好以数字结尾，且字符串长度相等
			if (len1 == len2 && sb1.length() != 0 && sb2.length() != 0) {
				int value1 = Integer.valueOf(sb1.toString());
				int value2 = Integer.valueOf(sb2.toString());
				return value1 - value2;
			}
			// 若前面都相等，则直接比较字符串的长度，长的排后面，短的排前面
			return Integer.compare(len1, len2);
		});

	}

	// 判断是否是数字
	static boolean isNumericFirstChar(String s) {
		return Character.isDigit(s.charAt(0));
	}

	static ArrayList<File> getRealFileWithDirAndPointType(File dirFile, ArrayList<String> selectTypeList) {

		ArrayList<File> targetFileList = new ArrayList<File>();
		if (dirFile == null || !dirFile.exists() || dirFile.isFile()) {
			return targetFileList;
		}

		File[] dir_fileList = dirFile.listFiles();

		for (int i = 0; i < dir_fileList.length; i++) {
			File itemFile = dir_fileList[i];
			if (itemFile.isDirectory()) {
				continue;
			}

			if (selectTypeList == null || selectTypeList.size() == 0) {
				targetFileList.add(itemFile);
				continue;
			}
			String fileName_lower = itemFile.getName().toLowerCase();

			for (int j = 0; j < selectTypeList.size(); j++) {
				String typeStr = selectTypeList.get(j);
				if (fileName_lower.endsWith(typeStr.trim().toLowerCase())) {
					targetFileList.add(itemFile);
				}

			}

		}
		return targetFileList;
	}

	static ArrayList<File> getRealFileWithDirAndPointType(File dirFile, String type) {

		ArrayList<File> targetFileList = new ArrayList<File>();
		if (dirFile == null || !dirFile.exists() || dirFile.isFile()) {
			return targetFileList;
		}

		File[] dir_fileList = dirFile.listFiles();

		for (int i = 0; i < dir_fileList.length; i++) {
			File itemFile = dir_fileList[i];
			if (itemFile.isDirectory()) {
				continue;
			}
			if (type == null || "".equals(type.trim())) {
				targetFileList.add(itemFile);
				continue;
			}
			String fileName_lower = itemFile.getName().toLowerCase();

			if (fileName_lower.endsWith(type.trim().toLowerCase())) {
				targetFileList.add(itemFile);
			}

		}
		return targetFileList;
	}

	public static String clearNumber(String str) {
		String result = new String(str);
		result = result.replaceAll("0", "");
		result = result.replaceAll("1", "");
		result = result.replaceAll("2", "");
		result = result.replaceAll("3", "");
		result = result.replaceAll("4", "");
		result = result.replaceAll("5", "");
		result = result.replaceAll("6", "");
		result = result.replaceAll("7", "");
		result = result.replaceAll("8", "");
		result = result.replaceAll("9", "");
		return result;
	}


	public static String getFileNameNoPointNoLowerCase(String fileName) {
		String name = "";
		if (fileName.contains(".")) {
			name = fileName.substring(0, fileName.lastIndexOf(".")).trim();
		} else {
			name = new String(fileName);
		}
		return name.trim();
	}

	/**
	 * BASE64解密
	 * 
	 * @throws Exception
	 */
	public static String jiemi_decryptBASE64(String key) throws Exception {
		return new String(Base64.getDecoder().decode(key));
	}

	/**
	 * BASE64加密
	 */
	public static String jiami_encryptBASE64(byte[] key) throws Exception {

		return new String(Base64.getEncoder().encode(key));
	}

	static void NotePadOpenTargetFile(String absPath) {
		String commandNotead = "";
		if (CUR_OS_TYPE == OS_TYPE.Windows) {
			commandNotead = "cmd.exe /c start   Notepad++.exe " + absPath;
			execCMD(commandNotead);

		} else if (CUR_OS_TYPE == OS_TYPE.Linux) {
			commandNotead = " gedit " + absPath;
		} else if (CUR_OS_TYPE == OS_TYPE.MacOS) {
			commandNotead = "/Applications/UltraEdit  " + absPath;
			execute_Mac(commandNotead);
		}
	}

	static ArrayList<File> getAllSubFileInFileList(ArrayList<File> rootFileList, String typeStr) {
		if (rootFileList == null || rootFileList.size() == 0) {
			return null;
		}
		ArrayList<File> ResultFileList = new ArrayList<File>();

		ArrayList<String> typeList = new ArrayList<String>();
		typeList.add(typeStr);

		for (int i = 0; i < rootFileList.size(); i++) {
			File dirFile = rootFileList.get(i);
			ArrayList<File> flitterFileList = getAllSubFile(dirFile.getAbsolutePath(), "", typeList);
			if (flitterFileList == null || flitterFileList.size() == 0) {
				continue;
			}
			ResultFileList.addAll(flitterFileList);
		}

		return ResultFileList;

	}


	
	
	/**
	* 删除文件，可以是文件或文件夹
	*
	* @param fileName
	* 要删除的文件名
	* @return 删除成功返回true，否则返回false
	*/
	public static boolean delete(String fileName) {
	File file = new File(fileName);
	if (!file.exists()) {
	System.out.println("删除文件失败:" + fileName + "不存在！");
	return false;
	} else {
	if (file.isFile())
	return deleteFile(fileName);
	else
	return deleteDirectory(fileName);
	}
	}

	/**
	* 删除单个文件
	*
	* @param fileName
	* 要删除的文件的文件名
	* @return 单个文件删除成功返回true，否则返回false
	*/
	public static boolean deleteFile(String fileName) {
	File file = new File(fileName);
	// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	if (file.exists() && file.isFile()) {
	if (file.delete()) {
	System.out.println("删除单个文件" + fileName + "成功！");
	return true;
	} else {
	System.out.println("删除单个文件" + fileName + "失败！");
	return false;
	}
	} else {
	System.out.println("删除单个文件失败：" + fileName + "不存在！");
	return false;
	}
	}

	/**
	* 删除目录及目录下的文件
	*
	* @param dir
	* 要删除的目录的文件路径
	* @return 目录删除成功返回true，否则返回false
	*/
	public static boolean deleteDirectory(String dir) {
	// 如果dir不以文件分隔符结尾，自动添加文件分隔符
	if (!dir.endsWith(File.separator))
	dir = dir + File.separator;
	File dirFile = new File(dir);
	// 如果dir对应的文件不存在，或者不是一个目录，则退出
	if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
	System.out.println("删除目录失败：" + dir + "不存在！");
	return false;
	}
	boolean flag = true;
	// 删除文件夹中的所有文件包括子目录
	File[] files = dirFile.listFiles();
	for (int i = 0; i < files.length; i++) {
	// 删除子文件
	if (files[i].isFile()) {
	flag = deleteFile(files[i].getAbsolutePath());
	if (!flag)
	break;
	}
	// 删除子目录
	else if (files[i].isDirectory()) {
	flag = deleteDirectory(files[i]
	.getAbsolutePath());
	if (!flag)
	break;
	}
	}
	if (!flag) {
	System.out.println("删除目录失败！");
	return false;
	}
	// 删除当前目录
	if (dirFile.delete()) {
	System.out.println("删除目录" + dir + "成功！");
	return true;
	} else {
	return false;
	}
	}
	

}