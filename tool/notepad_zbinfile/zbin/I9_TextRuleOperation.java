
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.JavaRuntimeInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.primitives.Bytes;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
public class I9_TextRuleOperation {

	/******************* 修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 I9 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
	// 修改2. I9_TextRuleOperation 改为当前类名称 A1 Z9
	// 修改2.1 I9 改为 对应的 标识符
// 修改3.  当前是否有默认的规则   如果有默认的规则那么设置 CUR_TYPE_INDEX为对应index , 没有默认规则那么设置为默认的1
	static String Cur_Bat_Name = "ztextrule_operation_I9";
	// 当前用户选中的 操作的类型 0-默认标识没有选中打印帮助字符串 1-标识选中默认规则1
	static int CUR_TYPE_INDEX = 1;
	static boolean allowEmptyInputParam = false; // 是否允许输入参数为空 执行 rule的apply方法

	/******************* 修改属性列表 ------End *********************/
	static String Default_Selected_Rule_Index_Key = "Default_Selected_Rule_Index_Key";

	/******************* 固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
	static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin";

	static String cur_os_zbinPath;
	static String win_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin" + File.separator + "win_zbin";
	static String lin_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin" + File.separator + "lin_zbin";
	static String mac_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin" + File.separator + "mac_zbin";

	// 固定2 当前执行文件的编号 A1 A2 A3 ... G1 I9 G3 ... Z9
	static File I9_Properties_File = new File(
			System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"
					+ File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + ".properties");

	static File I9_Temp_Text_File = new File(
			System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"
					+ File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + "_Temp_Text.txt");

	static String I9_OUT_DIR_PATH = zbinPath + File.separator + "I9_Out";
	static File I9_OUT_DIR = new File(I9_OUT_DIR_PATH);
	static File I9_Qr_Black_Image_File = new File(zbinPath + File.separator + "I9_Qr_Black.jpg");
	static InputStream I9_Properties_InputStream;
	static OutputStream I9_Properties_OutputStream;
	static Properties I9_Properties = new Properties();
	static Map<String, String> propKey2ValueList = new HashMap<String, String>();

	// 当前Shell目录下的 文件类型列表 抽取出来 通用
	static HashMap<String, ArrayList<File>> CurDirFileTypeMap = new HashMap<String, ArrayList<File>>();;

	public static int Rule6_num_row = 0; // 每行重组的个数
	// 固定3 当前操作系统的类型
	static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;
	static String BAT_OR_SH_Point;
	// 固定4 从CMD窗口输入得到的 目录关键字参数
	// 当前 SHELL 所在目录 默认是main中的第一个 arg[0] 就是shell路径
	static String CUR_Dir_1_PATH = ""; // arg[0] 就是shell路径 String 类型
	static File CUR_Dir_FILE; // 当前 CMDER的路径 File 文件
	static File First_Input_Dir; // 用户第一次可能输入的文件夹

	static File First_Input_RealFile; // 用户第一次可能输入的文件 实体文件

	// 固定5 从CMD窗口输入得到的 功能 tyoe 索引类型 以及依据索引 选中的 逻辑规则
	// 输入的第一个数值 是 rule的索引 同时搭配 * # 实现不同功能
	static String CUR_TYPE_2_ParamsStr; // arg[1] 就是输入的第一个参数 固定 通过 tip输出
	static boolean isDefaultOperation = false; // 是否是 默认的操作

	static Rule CUR_Selected_Rule; // 当前默认选中的 操作规则 这里实现了具体的操作逻辑

	// 固定6 从shell 中获取到的 除了 args[0] 和 args[1] 之外的所有其他的输入参数类型
	static ArrayList<String> CUR_INPUT_3_ParamStrList = new ArrayList<>();

	// 固定7 当前保存 Rule的集合
	static ArrayList<Rule> CUR_RULE_LIST = new ArrayList<Rule>(); // 规则的集合

	// 固定8 当前Shell目录下的 文件类型列表 抽取出来 通用 文件类型Str-文件及合
	static HashMap<String, ArrayList<File>> CUR_Dir_FILETypeMap = new HashMap<String, ArrayList<File>>();;

//  固定属性列表 ------End
	/******************* 固定属性列表 ------End *********************/

	// 检测中文 编码序列
	static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

	// PATH 环境变量值进行当前的保存处理
	static String EnvironmentValue = System.getProperties().getProperty("java.library.path");
	static String PathSeparator = System.getProperties().getProperty("path.separator");
	static String[] EnvironmentList = EnvironmentValue.split(PathSeparator);

	static boolean isContainEnvironment(String program) {
		boolean environmentExist = false;
		if (EnvironmentValue.contains(program)) {
			environmentExist = true;
		}
		return environmentExist;
	}

	// ffmpeg -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\I9_1_MergedRule.txt
	// -c copy C:\Users\zhuzj5\Desktop\output2.mp4
	// D:\software\ffmpeg\bin
	// D:\software\ffmpeg\bin\ffmpeg.exe -f concat -safe 0 -i
	// C:\Users\zhuzj5\Desktop\zbin\I9_1_MergedRule.txt -c copy
	// C:\Users\zhuzj5\Desktop\output3.mp4
	static String getEnvironmentExePath(String program) {
		String exename = program.trim().toLowerCase();
		String executePath = null;
		for (int i = 0; i < EnvironmentList.length; i++) {
			String itemPath = EnvironmentList[i];
			String itemPathLower = itemPath.toLowerCase();

			if (itemPathLower.contains(exename)) {
				executePath = itemPath + File.separator + program + (CUR_OS_TYPE == OS_TYPE.Windows ? ".exe" : "");
				break;
			}
		}

		return executePath;
	}

	static String getEnvironmentDefinePath(String program) {
		String exename = program.trim().toLowerCase();
		String executePath = null;
		for (int i = 0; i < EnvironmentList.length; i++) {
			String itemPath = EnvironmentList[i];
			String itemPathLower = itemPath.toLowerCase();

			if (itemPathLower.contains(exename)) {

				return itemPath;
			}
		}

		return executePath;
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

	static {
		try {

			if (!I9_OUT_DIR.exists()) {
				I9_OUT_DIR.mkdirs();
			}

			if (!I9_Temp_Text_File.exists()) {
				I9_Temp_Text_File.createNewFile();
			}

			if (!I9_Properties_File.exists()) {
				I9_Properties_File.createNewFile();
			}
			I9_Properties_InputStream = new BufferedInputStream(
					new FileInputStream(I9_Properties_File.getAbsolutePath()));
			I9_Properties.load(I9_Properties_InputStream);
			Iterator<String> it = I9_Properties.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (key.equals(Default_Selected_Rule_Index_Key)) {
					CUR_TYPE_INDEX = Integer.parseInt(I9_Properties.getProperty(key));
				}
				// System.out.println("key:" + key + " value: " +
				// I9_Properties.getProperty(key));
				propKey2ValueList.put(key, I9_Properties.getProperty(key));
			}
			I9_Properties_InputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void setProperity() {
		try {
			I9_Properties_OutputStream = new BufferedOutputStream(
					new FileOutputStream(I9_Properties_File.getAbsolutePath()));
			I9_Properties.store(I9_Properties_OutputStream, "");
			I9_Properties_OutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	enum OS_TYPE {
		Windows, Linux, MacOS
	}

	static boolean isFreshDefault_SelectedIndex = false;
	static String FreshDefaultInputStr = "";

	// 初始化 从 bat sh 传输而来的参数
	static void initInputParams(String[] args) {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("args[" + i + "] = " + args[i]);
				if (i == 0) { // 第一个参数永远是 当前 shell的目录
					CUR_Dir_1_PATH = args[i];
				} else if (i == 1) { // 第二个参数是用来 对 当前功能进行分类使用的
					if (args[i].contains("default_index_")) {
						FreshDefaultInputStr = args[i];
						isFreshDefault_SelectedIndex = true;
						return;
					}
					CUR_TYPE_2_ParamsStr = args[i];
					// zukgit1 计算得到 当前 索引的列表 首先遇到的第一个数字类型 1_2112 那就是索引1 附带参数 2112 temp_2_
					int userSelectedIndex = calculInputTypeIndex(CUR_TYPE_2_ParamsStr);
					if (userSelectedIndex != 0 && userSelectedIndex != CUR_TYPE_INDEX) {
						// 如果 当前 的操作规则 不是 0 并且 操作索引 和当前 索引 不一样 那么就寻找赋值给 CUR_TYPE_INDEX
						CUR_TYPE_INDEX = userSelectedIndex;
						isDefaultOperation = false;
					} else if (userSelectedIndex == CUR_TYPE_INDEX) {
						// 显式的输入默认值
						isDefaultOperation = true; // 默认的操作
						CUR_TYPE_2_ParamsStr = CUR_TYPE_INDEX + "";
					} else {
						isDefaultOperation = true; // 默认的操作
						// 默认的操作没有index 选项 所以 index1 就是参数
						CUR_INPUT_3_ParamStrList.add(args[i]);
						CUR_TYPE_2_ParamsStr = CUR_TYPE_INDEX + ""; // 默认参数 模拟的第二个参数
					}
				} else {

					if (args[i].contains("default_index_")) {
						FreshDefaultInputStr = args[i];
						isFreshDefault_SelectedIndex = true;
						return;
					}
					CUR_INPUT_3_ParamStrList.add(args[i]); // 当前cmd目录 第一个类型选项 之后所有的参数 保存在 CUR_INPUT_3_ParamStrList
					// Rule_Identify_TypeIndexList.add(args[i]);
				}

			}
		}

		File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
		CUR_Dir_FILE = new File(CUR_Dir_1_PATH);

	}

	static void initSystemInfo() {
		String osName = System.getProperties().getProperty("os.name").toLowerCase();
		if (osName.contains("window")) {
			CUR_OS_TYPE = OS_TYPE.Windows;
			Cur_Bat_Name = Cur_Bat_Name + ".bat";
			BAT_OR_SH_Point = ".bat";
			cur_os_zbinPath = win_zbinPath;
		} else if (osName.contains("linux")) {
			CUR_OS_TYPE = OS_TYPE.Linux;
			Cur_Bat_Name = Cur_Bat_Name + ".sh";
			BAT_OR_SH_Point = ".sh";
			cur_os_zbinPath = lin_zbinPath;
		} else if (osName.contains("mac")) {
			CUR_OS_TYPE = OS_TYPE.MacOS;
			Cur_Bat_Name = Cur_Bat_Name + ".sh";
			BAT_OR_SH_Point = ".sh";
			cur_os_zbinPath = mac_zbinPath;
		}
	}

	public static String clearChinese(String lineContent) {
		if (lineContent == null || lineContent.trim().isEmpty()) {
			return null;
		}
		Pattern pat = Pattern.compile(REGEX_CHINESE);
		Matcher mat = pat.matcher(lineContent);
		return mat.replaceAll(" ");
	}

	void InitRule() {

		// 加入类型一一对应的 那些 规则
		CUR_RULE_LIST.add(new OnlyGetFirstStr_InOneLine_Rule_1());
		CUR_RULE_LIST.add(new OnlyGetFirstStr_AsOneLine_Rule_2());
		CUR_RULE_LIST.add(new AddLineNumberChar_Rule_3());
		CUR_RULE_LIST.add(new ClearChinese_Rule_4());
		CUR_RULE_LIST.add(new Duiqi_Hang_Rule_5());
		CUR_RULE_LIST.add(new PaiLie_2x2_Rule_6());
		CUR_RULE_LIST.add(new PaiLie_3x3_Rule_7());
		CUR_RULE_LIST.add(new PaiLie_4x4_Rule_8());
		CUR_RULE_LIST.add(new PaiLie_5x5_Rule_9());
		CUR_RULE_LIST.add(new Add_BeginStr_EndStr_Rule_10());
		CUR_RULE_LIST.add(new Table_MDStyle_Rule_11());
		CUR_RULE_LIST.add(new BoardCopy_ToLeft_Rule_12());
		CUR_RULE_LIST.add(new ClearChinese_Rule_13());
		CUR_RULE_LIST.add(new Chinese_To_PinYin_Rule_14());
		CUR_RULE_LIST.add(new TextAs_QrCode_Rule_15());
		CUR_RULE_LIST.add(new Image2QrCode_Rule_16()); // 读取当前目录下的照片 并在临时文件显示 二维码信息
		CUR_RULE_LIST.add(new Make_Json_As_JavaFile_Graphviz2Jpg_Rule_17());
		CUR_RULE_LIST.add(new Add_BeginStr_EndStr_Rule_18());
		CUR_RULE_LIST.add(new FirstWord_MakeDir_Rule_19());
		CUR_RULE_LIST.add(new Cal_Install_Command_Rule_20());
		CUR_RULE_LIST.add(new System_Out_Print_Rule_21()); // 把当前文件的每一行 都 转为 System.out.println(xx) 的内容
		CUR_RULE_LIST.add(new ADB_Wireless_WIFI_Rule_22()); // 把 输入的四个参数 转为 无线 adb 连接的命令
		CUR_RULE_LIST.add(new Create_Install_Command_Rule_23()); // 把当前目录的 exe 和 msi 输出 安装的 zbat_xxxx.bat 命令 测试安装命令
		CUR_RULE_LIST.add(new ReadStrFromFile_Rule_24());
		CUR_RULE_LIST.add(new LS_Shell_RealFile_Rule_25()); // //读取当前文件下的 实体文件的文件名称 输出到列表中
		CUR_RULE_LIST.add(new Fliter_Copy_File_WithName_Rule_26()); // //读取当前文件的内容 并在当前文件夹下内寻找该文件 复制到指定目录
		CUR_RULE_LIST.add(new Copy_Port_WithZ_Rule27()); // 把当前文件内容以 ZZZZZZZZZZZZZZZZZZZZZ 分割 专门生成剪切内容保存到零时txt文件
		CUR_RULE_LIST.add(new Show_JavaTest_File_Rule_28()); // 读取 Java模板文件(包含初始化模块) 然后在notepad++打开它
		CUR_RULE_LIST.add(new Bat_Revert_MD_Rule29()); // 读取当前.bat 文件内容 进行 解析生成 MD文件的下半部分 并会解析新增的Method到模板文件
														// zzbattest_I9.bat
		CUR_RULE_LIST.add(new Show_Bat_Template_OnDir_Rule_30()); // 把当前模板文件 zzbattest_I9.bat 内容写进当前目录下 Test_xx.bat文档
																	// 并打开它
		CUR_RULE_LIST.add(new Bat_Format_Rule_31()); // 对当前 bat 文件进行 format 如果不是bat文件不操作 增项假如模板zbatrule_I9_Rule30.bat中
		CUR_RULE_LIST.add(new Build_SH_BAT_WithJavaWithJar_Rule_32());

		// 读取当前文件的每一个字符串 截取每一个字符作为单独的字符串然后复制120份
		CUR_RULE_LIST.add(new MakeStringAsOneString_Copy120AsLine_Rule_33());

		// 读取当前页面的每一行 只留下 颜色的值 并 生成 android的 列表
		CUR_RULE_LIST.add(new Select16Color2Android_Rule_34());

		// 把当前 notepad 打开的文件 转为 二进制格式 即 zbyte_file_F9.bat 的规则 集成进 TextRule
		CUR_RULE_LIST.add(new FileToByte_F9_Rule_35());

		// 对于只包含 0 和 1 的字符串文件 转为一个 byte文件 在 txt中打开
		CUR_RULE_LIST.add(new BinaryStrToFile_Rule_36());

		// 检测 当前 txt文件中的 url 路径   并尝试下载这个 url 对应的文件到本地 
		CUR_RULE_LIST.add(new Analysis_URI_IN_Txt_Download_DouYinMP4_Rule_37());
		
		// 检测当前的 txt文件  只 保留 url 内容 , 并 对这些 内容 进行 排序 在 temp 中 打印出来 

		
//        CUR_RULE_LIST.add( new Image2Png_Rule_4());
//        CUR_RULE_LIST.add( new AVI_Rule_5());
//        CUR_RULE_LIST.add( new SubDirRename_Rule_6());
//        CUR_RULE_LIST.add( new Encropty_Rule_7());
//        CUR_RULE_LIST.add( new ClearChineseType_8());

	}
	
	
	
	class Analysis_URI_IN_Txt_Download_DouYinMP4_Rule_37 extends Basic_Rule {

		String error_string_item;
		
// String targetPath = "奇怪，刚刚和妈妈的衣架子交心攀谈后，怎么感觉头上有一圈星星呢～ https://v.kuaishou.com/6Rq0gB 复制此链接，打开【快手App】直接观看！";

		ArrayList<String> videoUrlList ; 
		ArrayList<String> urlList ;   // 在 url 中 检测到的 mp4 文件    比如 https://v.kuaishou.com/6Rq0gB 
	    String videoSavePath=null;   //  默认为 txt 文件所在 目录下 新建 抖音 mp4 文件 

	    String timeStamp_Str = null;
	    int index_download;
		Analysis_URI_IN_Txt_Download_DouYinMP4_Rule_37() {
			super(37, false);
			urlList = new ArrayList<String>();
			timeStamp_Str = getTimeStamp();
			index_download = 1;
			videoUrlList =  new ArrayList<String>();
		}

	
		@Override
		boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
			// TODO Auto-generated method stub
	
			

			return super.checkParamsOK(shellDir, type2Param, otherParams);
		}
		
		@Override
		void showWrongMessage() {
			// TODO Auto-generated method stub
			super.showWrongMessage();
		}
		
		
		
		
		@Override
		String simpleDesc() {
			return "  检测 当前 txt文件中的 url 路径(抖音 快手视频)   并尝试下载这个 url 对应的文件到本地 tile_时间戳.mp4 并在temp文件打印url列表";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				ArrayList<String> allContentList = new ArrayList<String>();
				File targetFile = curInputFileList.get(i);
				String fileName = getFileNameNoPointNoLowerCase(targetFile.getName());
				ArrayList<String> allContent = ReadFileContentAsList(targetFile);

				if(targetFile.exists()) {
					videoSavePath = targetFile.getParentFile().getAbsolutePath();
				}
				
				if(videoSavePath == null) {
					videoSavePath = targetFile.getParentFile().getAbsolutePath();
				}
				
				if(allContent.size() > 0) {
					for (int j = 0; j < allContent.size(); j++) {
						String oneLine = allContent.get(j);
						toGetUrlFromOneLine_And_InitUrlList(oneLine);
						
						
					}
					
					
					if(urlList.size() == 0) {
						System.out.println("当前执行 "+rule_index +" 规则失败  读取文件中的 http-url数据失败!!");
						return null;
					}
					
					for (int j = 0; j < urlList.size(); j++) {
						String urlItem = urlList.get(j);
						
						 parseUrl(urlItem);
					}
					
	
				}else {
					System.out.println("当前执行 "+rule_index +" 规则失败  读取文件内容为空!!!");
				}

			}
			
//			urlList.sort(Chinese);
			SortString(videoUrlList);
			writeContentToFile(I9_Temp_Text_File, videoUrlList);
			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
			System.out.println("VideoURL 列表打印在 PATH: "+ I9_Temp_Text_File.getAbsolutePath());
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}


		
		
	  public  void parseUrl(String url) {
	        if(url.contains("v.kuaishou.com")){
	            ksParseUrl(url);
	            videoUrlList.add(url);
	        }
	        if (url.contains("v.douyin.com")     ){
	            douYinParseUrl(url);
	            videoUrlList.add(url);
	        }else if(url.contains("douyin")) {
	            downVideo(url,"douyin","抖音视频");
	            videoUrlList.add(url);
	        	
	        }
	    }
	  
	  
	    /**
	     * 方法描述: 抖音解析下载视频

	     */
		@SuppressWarnings("unchecked")
	    public  void douYinParseUrl(String url) {
	        try {
	            final  String videoPath="https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";
	            Connection con= Jsoup.connect(clearChinese(url));
	            con.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
	            Connection.Response resp=con.method(Connection.Method.GET).execute();
	            String videoUrl= videoPath+getItemId(resp.url().toString());
	            String jsonStr = Jsoup.connect(videoUrl).ignoreContentType(true).execute().body();
	            JSONObject json =JSONObject.parseObject(jsonStr);
	            String videoAddress= json.getJSONArray("item_list").getJSONObject(0).getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
	            String title= json.getJSONArray("item_list").getJSONObject(0).getJSONObject("share_info").getString("share_title");
	            videoAddress=videoAddress.replaceAll("playwm","play");
	            HashMap headers = MapUtil.newHashMap();
	            headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
	            String finalVideoAddress = HttpUtil.createGet(videoAddress).addHeaders(headers).execute().header("Location");
	            //注:打印获取的链接
	            System.out.println("-----抖音去水印链接-----\n"+finalVideoAddress);
	            //下载无水印视频到本地
	            downVideo(finalVideoAddress,title,"抖音视频");
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	  
		@SuppressWarnings("unchecked")
	    public  void ksParseUrl(String url) {
	        HashMap headers = MapUtil.newHashMap();
	        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Mobile Safari/537.36");
	        String redirectUrl = HttpUtil.createGet(url).addHeaders(headers).execute().header("Location");
	        String body= HttpUtil.createGet(redirectUrl).addHeaders(headers).execute().body();
	        Document doc= Jsoup.parse(body);
	        Elements videoElement = doc.select("video[id=video-player]");
	        String videoUrl = videoElement.get(0).attr("src");
	        String title = videoElement.get(0).attr("alt");
	        System.out.println();
	        System.out.println(videoUrl);
	        System.out.println(title);
	        downVideo(videoUrl,title,"快手视频");
	    }
		
		@SuppressWarnings("unchecked")
	    public  void downVideo(String httpUrl,String title,String source) {
//	        String fileAddress = videoSavePath+"/"+source+"/"+title+".mp4";
	        String fileAddress = videoSavePath+"/"+(title.replace(" ", ""))+"_"+index_download+"_"+timeStamp_Str+".mp4";
	        index_download++;
	        int byteRead;
	        try {
	            URL url = new URL(httpUrl);
	            //获取链接
	            URLConnection conn = url.openConnection();
	            //输入流
	            InputStream inStream = conn.getInputStream();
	            //封装一个保存文件的路径对象
	            File fileSavePath = new File(fileAddress);
	            //注:如果保存文件夹不存在,那么则创建该文件夹
	            File fileParent = fileSavePath.getParentFile();
	            if(!fileParent.exists()){
	                fileParent.mkdirs();
	            }
	            //写入文件
	            FileOutputStream fs = new FileOutputStream(fileSavePath);
	            byte[] buffer = new byte[1024];
	            while ((byteRead = inStream.read(buffer)) != -1) {
	                fs.write(buffer, 0, byteRead);
	            }
	            inStream.close();
	            fs.close();
	            System.out.println("\n-----视频保存路径-----\n"+fileSavePath.getAbsolutePath());
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    // 对每行的数据进行分析

	    public  void toGetUrlFromOneLine_And_InitUrlList(String rowString) {
	        String[] strArrRow = null;
	        String fixStr = "";

//	        if(str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
//	                str.trim().startsWith("thunder:") ||   str.trim().startsWith("magnet::") ){


	        if (rowString != null) {
	            fixStr = new String(rowString);
	            // http://xxxxxx/sahttp://  避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
	            fixStr = fixStr.replace("http:", " http:");
	            fixStr = fixStr.replace("https:", " https:");
	            fixStr = fixStr.replace("thunder:", " thunder:");
	            fixStr = fixStr.replace("magnet:", " magnet:");
	            strArrRow = fixStr.split(" ");
	        }

	        if (strArrRow != null && strArrRow.length > 0) {

	            for (int i = 0; i < strArrRow.length; i++) {
	                String mCurContent = strArrRow[i];
	                if (mCurContent == null || mCurContent.trim().equals("")) {
	                    continue;
	                }


	                boolean isUrl = toJudgeUrl(mCurContent);
	                if(isUrl){
	                	urlList.add(clearChinese(mCurContent).trim());

	                }

	            }


	        }


	    }
	    
	    public  String clearChinese(String lineContent) {
	        if (lineContent == null || lineContent.trim().isEmpty()) {
	            return null;
	        }
	        Pattern pat = Pattern.compile(REGEX_CHINESE);
	        Matcher mat = pat.matcher(lineContent);
	        return mat.replaceAll(" ");
	    }

	    
	    /**
	     * 方法描述: 过滤分享链接的中文汉字

	     */
	    public  String filterUrl(String rowLine , String url) {
	        String regex = "https?://(\\w|-)+(\\.(\\w|-)+)+(/(\\w+(\\?(\\w+=(\\w|%|-)*(\\&\\w+=(\\w|%|-)*)*)?)?)?)+";//匹配网址
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(rowLine);
	        if(m.find()){
	            return   rowLine.substring(m.start(),m.end());
	        }
	        return "";
	    }

	    /**
	     * 方法描述: 获取分享视频id
	     *

	     */
	    public  String getItemId(String url){
	        int start = url.indexOf("/video/")+7;
	        int end = url.lastIndexOf("/");
	        String itemId = url.substring(start, end);
	        return  itemId;
	    }
	    

	    public  boolean toJudgeUrl(String str) {
	        boolean isUrl = false;

	        if (str.trim().toLowerCase().startsWith("http:") || str.toLowerCase().trim().startsWith("https:") ||
	                str.toLowerCase().trim().startsWith("thunder:") || str.toLowerCase().trim().startsWith("magnet:")) {

	            return true;
	        }


	        return isUrl;
	    }
	    
	    

	}
	

	class BinaryStrToFile_Rule_36 extends Basic_Rule {

		String error_string_item;

		BinaryStrToFile_Rule_36() {
			super(36, false);

		}

		@Override
		String simpleDesc() {
			return "  把当前的只包含二进制字符0和1的文件 转为解析出来的对应的字节文件  zbyte_file_F9.bat 的 逆向操作";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				ArrayList<String> allContentList = new ArrayList<String>();
				File targetFile = curInputFileList.get(i);
				String fileName = getFileNameNoPointNoLowerCase(targetFile.getName());
				ArrayList<String> allContent = ReadFileContentAsList(targetFile);

				// 每一个 item 只包含 8位字符串 所组成的新的字符串集合 方便 转为 byte数组
				ArrayList<String> item8bitstr_List = new ArrayList<String>();

				// 解析出的 byte 的 数组
				ArrayList<Byte> resultByte_List = new ArrayList<Byte>();

				boolean isOnly_0_1_flag = Try8bitAsOneItem(allContent, item8bitstr_List, resultByte_List);

				if (!isOnly_0_1_flag) {
					System.out.println("当前文件不仅仅包含 字节字符 0 和 1 请检查!!!  error_string_item=[" + error_string_item + "]");
					return null;
				}

//               writeContentToFile(targetFile, item8bitstr_List);

				writeByteArrToFile(I9_Temp_Text_File, resultByte_List);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println(
						"rule_" + rule_index + " -> 完成Byte文件写入文件 " + targetFile.getAbsolutePath() + " 的 二进制打印!! ");

				String realType = getFileType(I9_Temp_Text_File.getAbsolutePath());
				if (realType == null) {
					realType = "unknow";
				}
				String realType_Name = fileName + "_BinRecovery_Rule36_" + getTimeStamp() + "." + realType;

				File realFile = new File(targetFile.getParentFile().getAbsolutePath() + File.separator + realType_Name);
				fileCopy(I9_Temp_Text_File, realFile);
				System.out.println("rule_" + rule_index + " -> 完成二进制字符串转为文件 生成文件--> " + realFile.getAbsolutePath()
						+ " 的 " + realType + " 类型的文件!! ");

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}

		synchronized boolean Try8bitAsOneItem(ArrayList<String> contentList, ArrayList<String> item8bitArr,
				ArrayList<Byte> resultByeArr) {
			boolean isOnly_0_1_flag = true;

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < contentList.size(); i++) {
				sb.append(contentList.get(i).trim());
			}

			String rawContetnt = sb.toString();
			int last8str = rawContetnt.length() % 8;
			if (last8str != 0) {
				isOnly_0_1_flag = false;
				error_string_item = "不是8的整数 rawContetnt.length()=" + rawContetnt.length();
				System.out.println("当前文件字节字符 0 和 1的个数  不是8的整数  所以 不是二进制字符串 执行失败!! ");
				return isOnly_0_1_flag;
			}
			synchronized (this) {
				for (int i = 0; i < rawContetnt.length(); i = i + 8) {
					String bit_8_str = rawContetnt.substring(i, i + 8);
					if (bit_8_str == null) {
						continue;
					}
					Byte targetByte = calcul8bitStr_To_ObeByte(bit_8_str);
					if (targetByte != null && bit_8_str != null) {
						resultByeArr.add(targetByte);
						item8bitArr.add(bit_8_str);
					} else if (targetByte == null) {
						isOnly_0_1_flag = false;
						error_string_item = bit_8_str;
						System.out.println("当前文件不仅仅包含 字节字符 0 和 1 请检查!!!   bit_8_str=【" + bit_8_str + "】");
						return isOnly_0_1_flag;
					}

				}
			}

			return isOnly_0_1_flag;
		}

		public Byte calcul8bitStr_To_ObeByte(String bit_8_str) {

			if (bit_8_str == null || "".equals(bit_8_str) || bit_8_str.length() != 8) {
				System.out.println("当前提供的字符串不是8位二进制的字符串");
				return null;
			}
			String veryStr = bit_8_str.replace("1", "").replace("0", "").trim();
			if (!"".equals(veryStr)) {
				System.out.println("当前提供的字符串不是二进制的字符串");
				return null;
			}

//		System.out.println("bit_8_str  ="+ bit_8_str);
			StringBuilder sb = new StringBuilder();
			byte targetByte = 0x00;
			byte tempByte = 0x01;
			for (int i = 7; i >= 0; i--) {
				char charItem = bit_8_str.charAt(i == 7 ? 0 : (i % 7 == 0 ? 7 : (7 - i) % 7));
				byte positionByte = (byte) (tempByte << i);

//System.out.println("--------------- index_byte["+i+"] char["+charItem+"]-------------------");

				if ('1' == charItem) {
					targetByte = (byte) (targetByte | positionByte);
				}
//            System.out.println("positionByte_["+i+"] = "+showByte(positionByte));
//            System.out.println("targetByte_["+i+"] = "+showByte(targetByte));  
			}
			return targetByte;

		}

	}

	class FileToByte_F9_Rule_35 extends Basic_Rule {

		public StringBuilder mHexSB;
		public StringBuilder mBinarySB;
		public StringBuilder mAsciiSB;

		FileToByte_F9_Rule_35() {
			super(35, false);
			mHexSB = new StringBuilder();
			mBinarySB = new StringBuilder();
			mAsciiSB = new StringBuilder();
		}

		@Override
		String simpleDesc() {
			return " 集成Rule zbyte_file_F9.bat 的功能 把当前文件转为 二进制 byte 的txt格式 并在notepad++打开  ";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				ArrayList<String> allContentList = new ArrayList<String>();
				File targetFile = curInputFileList.get(i);
				allContentList
						.add("\n\n\n\n══════════════════════Index" + i + "   PATH : " + targetFile.getAbsolutePath());

				byte[] byteArr = tryFile2Byte(targetFile);
				String dumpHex_result_str = dumpHexString(byteArr, mBinarySB, mAsciiSB, mHexSB);
				allContentList
						.add("\n\n\n\n══════════════════════Index" + i + "   PATH : " + targetFile.getAbsolutePath());
				allContentList.add(dumpHex_result_str);
				allContentList.add("\n\n\n ══════════════════════  单独16进制(十六进制)形式 ══════════════════════ ");
				allContentList.add(mHexSB.toString());
				allContentList.add("\n\n\n ══════════════════════  单独二进制形式 ══════════════════════ ");
				allContentList.add("ZZZZZZZZZZZZZZZZZZZZZ");
				allContentList.add(mBinarySB.toString());
				allContentList.add("ZZZZZZZZZZZZZZZZZZZZZ");

				System.out.println(
						"\n\n\n\n══════════════════════Index" + i + "   PATH : " + targetFile.getAbsolutePath());
				System.out.println(dumpHex_result_str);
				System.out.println("\n\n\n ══════════════════════  单独16进制(十六进制)形式 ══════════════════════ ");

				System.out.println(mHexSB.toString());

				System.out.println("\n\n\n ══════════════════════  单独二进制形式 ══════════════════════ ");
				System.out.println("ZZZZZZZZZZZZZZZZZZZZZ");
				System.out.println(mBinarySB.toString());
				System.out.println("ZZZZZZZZZZZZZZZZZZZZZ");

				ArrayList<String> binary_str_List = new ArrayList<String> ();
				if (mAsciiSB.length() > 0) {
					System.out.println("\n\n\n\n");
					System.out.println("ASCII长度: " + mAsciiSB.length());
					System.out.println("══════════════════════ ASCII码 100长度一行 ══════════════════════ ");
					allContentList.add("\n\n\n\n");
					allContentList.add("ASCII长度: " + mAsciiSB.length());
					allContentList.add("══════════════════════ ASCII码 100长度一行 ══════════════════════ ");

					int originSize = mAsciiSB.length();
					StringBuilder sb = new StringBuilder();
					for (int j = 0; j < originSize; j++) {
						sb.append(mAsciiSB.charAt(j));
						if (j % 100 == 0) {
							String binaryItem = get12size(j) + "    " + sb.toString();
							System.out.println(binaryItem);
							allContentList.add(binaryItem);
							binary_str_List.add(binaryItem);
							sb = new StringBuilder();
						}
						if (j == originSize - 1) {
							String binaryItem = get12size(j) + "    " + sb;
							System.out.println(binaryItem);
							allContentList.add(binaryItem);
							binary_str_List.add(binaryItem);
						}
					}
				}

				writeContentToFile(I9_Temp_Text_File, allContentList);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				
				// 在 文件 本地 创建一个 只有 二进制的 txt文件
				
				String origin_name = targetFile.getName();
				String clearTypeName = getFileNameNoPointNoLowerCase(origin_name);
				String binary_only_filename = clearTypeName+"_binary_"+getTimeStamp()+".txt";
				File binaryFile = new File(targetFile.getParentFile().getAbsolutePath()+File.separator+binary_only_filename);
				
//				writeContentToFile(mBinarySB.toString(), binary_str_List);
				writeContentToFile(binaryFile, mBinarySB.toString());
//				NotePadOpenTargetFile(binaryFile.getAbsolutePath());
				System.out.println("rule_" + rule_index + " -> 完成当前文件 " + targetFile.getAbsolutePath() + " 的 二进制打印!! ");

				System.out.println("rule_" + rule_index + " -> 完成本地二进制文件 " + binaryFile.getAbsolutePath() + " 的 生成!! ");

			}
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}

	}

	// 读取当前页面的每一行 只留下 颜色的值 并 生成 android的 列表
	class Select16Color2Android_Rule_34 extends Basic_Rule {
		Select16Color2Android_Rule_34() {
			super(34, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);

				/*
				 * if(true) { writeContentToFile(I9_Temp_Text_File,contentList);
				 * NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath()); return null; }
				 */
				ArrayList<String> oneWordList = new ArrayList<String>();
				Map<String, String> name_color_map = new HashMap<String, String>();
				for (int j = 0; j < contentList.size(); j++) {
					String wordStr = contentList.get(j).trim();

					String fixed_wordStr = wordStr.replace("###", "#");
					fixed_wordStr = fixed_wordStr.replace("	", " ");
					fixed_wordStr = fixed_wordStr.replace("##", "#");
					fixed_wordStr = fixed_wordStr.replace("##", "#");

					int jing_count = calculStrCount(fixed_wordStr, '#');

					System.out.println(" wordStr = " + wordStr + " jing_count=" + jing_count);

					String[] colorStrArr = fixed_wordStr.split(" ");
					if (jing_count == 0 || colorStrArr == null || colorStrArr.length < 2) {
						System.out.println("过滤A 点 ");
						continue;
					}

					ArrayList<String> coloritemList = new ArrayList<String>();
					StringBuilder sb = new StringBuilder();

					for (int k = 0; k < colorStrArr.length; k++) {
						String strItem = colorStrArr[k];
						if (isNumeric(strItem)) {
							System.out.println("过滤B 点  strItem =" + strItem);
							continue;
						} else {
							System.out.println("通过 B 点  strItem =" + strItem);

						}
						System.out.println("通过 B 点  strItem =" + strItem);

						if (strItem.startsWith("#")) {
							String clear_jing = strItem.replace("#", "");

							if (clear_jing.length() == 8 || clear_jing.length() == 6) {
								if (is16jinzhi(clear_jing)) {
									System.out.println("过滤C 点 ");
									String colorName = coloritemList.get(coloritemList.size() - 1);
									String colorValue = strItem;
									if (name_color_map.containsKey(colorName)) {
										name_color_map.put(colorName + "_1", colorValue);
									} else {
										name_color_map.put(colorName, colorValue);
									}

								} else {
									System.out.println("过滤E 点 ");
									System.out.println("长度不达标！！ strItem = " + strItem);
								}
							}

							continue;
						} else {
							System.out.println("FF 不是以 # 开头 strItem=" + strItem);
						}
						coloritemList.add(strItem); // 把名字 保存起来

					}

				}
				ArrayList<String> codeArr = calculAndroidColorCode(name_color_map);

				writeContentToFile(I9_Temp_Text_File, codeArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " -> 完成读取当前文件的每一行 检测是否有颜色 如果有颜色 那么计算颜色 并输出安卓颜色列表");

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@SuppressWarnings("unchecked")
		public ArrayList<String> calculAndroidColorCode(Map<String, String> mMapParam) {
			ArrayList<String> codeArr = new ArrayList<String>();

			// <color name="rainbow_popul">#228B00FF</color>
			ArrayList<String> xmlcodeArr = new ArrayList<String>();

//            static int[] rainbow_colorList = {
//                    R.color.rainbow_red     ,
//                    R.color.rainbow_orange  ,
//                    R.color.rainbow_yellow  ,
//                    R.color.rainbow_green   ,
//                    R.color.rainbow_blue    ,
//                    R.color.rainbow_popul   };

			ArrayList<String> javacodeArr = new ArrayList<String>();
			javacodeArr.add("static int[] rainbow_colorList = {");

			Map.Entry<String, String> entryItem;

			System.out.println(
					"════════════════════ Map<String,String> 大小:" + mMapParam.size() + " " + "════════════════════ ");
			int index = 0;
			if (mMapParam != null) {
				Iterator iterator = mMapParam.entrySet().iterator();
				while (iterator.hasNext()) {
					entryItem = (Map.Entry<String, String>) iterator.next();

					String key = entryItem.getKey(); // Map的Key
					String value = entryItem.getValue(); // Map的Value
					System.out.println("Map_index[" + index + "]  key=[" + key + "]   value=[" + value + "]");
					xmlcodeArr.add("<color name=\"" + key + "\">" + value + "</color>");
					if (index == mMapParam.size() - 1) {
						javacodeArr.add("R.color." + key);
					} else {
						javacodeArr.add("R.color." + key + ",");
					}

					index++;
				}
			}
			javacodeArr.add("};");

			codeArr.addAll(javacodeArr);
			codeArr.add("ZZZZZZZZZZZZZZZZZZZZZZZZZ");
			codeArr.addAll(xmlcodeArr);
			codeArr.add("ZZZZZZZZZZZZZZZZZZZZZZZZZ");
			return codeArr;
		}

		String copyWord(String rawStr, int copyTime) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < copyTime; i++) {
				sb.append(rawStr);
			}
			return sb.toString();

		}

		@Override
		String simpleDesc() {
			return " 读取当前文件的每一行 检测是否有颜色 如果有颜色 那么计算颜色 并输出安卓颜色列表 ";
		}

	}

	// 读取当前文件的每一个字符串 截取每一个字符作为单独的字符串然后复制120份
	class MakeStringAsOneString_Copy120AsLine_Rule_33 extends Basic_Rule {
		MakeStringAsOneString_Copy120AsLine_Rule_33() {
			super(33, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);

				/*
				 * if(true) { writeContentToFile(I9_Temp_Text_File,contentList);
				 * NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath()); return null; }
				 */
				ArrayList<String> oneWordList = new ArrayList<String>();

				for (int j = 0; j < contentList.size(); j++) {
					String wordStr = contentList.get(j).trim();

					int wordLength = wordStr.length();
					for (int k = 0; k < wordLength; k++) {
						String oneWord = wordStr.substring(k, k + 1);
						if ("".equals(oneWord.trim())) {
							continue;
						}
						oneWordList.add(oneWord);
						int currCount = oneWordList.size();
						System.out.println("Word_index[" + currCount + "] value=" + oneWord + "   lineStr=" + wordStr);
					}

				}

				ArrayList<String> oneWordCopyList = new ArrayList<String>();

				for (int j = 0; j < oneWordList.size(); j++) {

					String oneWord = oneWordList.get(j);
					System.out.println("oneWord[" + j + "] = " + oneWord);
					String copyWord = copyWord(oneWord, 130);
					oneWordCopyList.add(copyWord);
				}

				writeContentToFile(I9_Temp_Text_File, oneWordCopyList);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " -> 完成 copy一个字符 作为 一行 一行120个重复字符的操作");

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		String copyWord(String rawStr, int copyTime) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < copyTime; i++) {
				sb.append(rawStr);
			}
			return sb.toString();

		}

		@Override
		String simpleDesc() {
			return " 读取当前文件的每一个字符串 截取每一个字符作为单独的字符串然后复制120份作为一行 notepad++ temp打开 ";
		}

	}

	// 依据当前目录 动态构建 java 运行命令 包括 sh 和 bat // 当前目录只能有一个 .java 多个 .jar 包
	class Build_SH_BAT_WithJavaWithJar_Rule_32 extends Basic_Rule {

		Build_SH_BAT_WithJavaWithJar_Rule_32() {
			super(32, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			ArrayList<String> realFileNameList = new ArrayList<String>();

			File dirFile = curInputFileList.get(0).getParentFile();
			File[] fileList = dirFile.listFiles();
			System.out.println("dirFile = " + dirFile + "        fileList = " + fileList.length);
			ArrayList<File> CurRealFileList = new ArrayList<File>();

			File javaFile = null;
			String RuleIndexTag = "";
			CurRealFileList.addAll(Arrays.asList(fileList));
			ArrayList<String> jarFileNameList = new ArrayList<String>();

			int java_count = 0;
			for (int i = 0; i < CurRealFileList.size(); i++) {
				File fileItem = CurRealFileList.get(i);
				String fileName = fileItem.getName();
				if (fileName.endsWith(".java")) {
					javaFile = fileItem;
					if (fileName.contains("_")) {
						RuleIndexTag = fileName.split("_")[0].trim();
					}
					java_count++;
					if (java_count > 1) {
						System.out.println("当前目录下的 Java 文件大于1 无法动态生成 " + RuleIndexTag + BAT_OR_SH_Point);
						return null;
					}
				}
				if (fileItem.isFile() && fileName.trim().toLowerCase().endsWith(".jar") && fileItem.exists()) {
					jarFileNameList.add(fileName);
				}
			}

			if (javaFile == null) {
				System.out.println("当前目录 不存在 java 文件 无法生成 bat sh 运行命令! ");
				return null;
			}

			ArrayList<String> allCommandList = new ArrayList<String>();
			ArrayList<String> batCommandList = new ArrayList<String>();
			ArrayList<String> shCommandList = new ArrayList<String>();
			buildBatCommand(batCommandList, javaFile, jarFileNameList, RuleIndexTag);
			buildShCommand(shCommandList, javaFile, jarFileNameList, RuleIndexTag);
			allCommandList.add("════════════════════════ " + RuleIndexTag + ".bat" + " ════════════════════════");
			allCommandList.addAll(batCommandList);
			allCommandList.add("");
			allCommandList.add("════════════════════════ " + RuleIndexTag + ".sh" + " ════════════════════════");
			allCommandList.addAll(shCommandList);
			allCommandList.add("");

			File batFile = new File(dirFile + File.separator + RuleIndexTag + ".bat");
			writeContentToFile(batFile, batCommandList);

			File shFile = new File(dirFile + File.separator + RuleIndexTag + ".sh");
			writeContentToFile(shFile, shCommandList);

			writeContentToFile(I9_Temp_Text_File, allCommandList);
			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void buildBatCommand(ArrayList<String> batList, File javaFile, ArrayList<String> jarNameList, String RuleTag) {
//        	batList.add("════════════════════════ "+RuleTag+BAT_OR_SH_Point+" ════════════════════════");

			StringBuilder jarSB = new StringBuilder();
			for (int i = 0; i < jarNameList.size(); i++) {
				// %userprofile%\Desktop\zbin\G2_webp-imageio.jar;
				// G2_webp-imageio.jar;
				String jarName = jarNameList.get(i);
				if (!jarName.startsWith(RuleTag)) {
					System.out.println("!!!!!!!!!!!!!!!!警告当前存在 Jar包不是规则命名: " + jarName);
					batList.add("!!!!!!!!!!!!!!!!!!警告当前存在 Jar包不是规则命名: " + jarName);
				}
				jarSB.append("%userprofile%\\Desktop\\zbin\\" + jarName + ";");

			}
			String javaNameNoPoint = getFileNameNoPointNoLowerCase(javaFile.getName());

			String classPathStr = jarSB.toString() + "%userprofile%\\Desktop\\zbin";
//        	@echo off
//        	Setlocal ENABLEDELAYEDEXPANSION
//        	@javac -cp %userprofile%\Desktop\zbin\G2_webp-imageio.jar;%userprofile%\Desktop\zbin\G2_spire.presentation.free-3.9.0.jar;%userprofile%\Desktop\zbin\G2_fontbox.jar;%userprofile%\Desktop\zbin\G2_commons-logging-api.jar;%userprofile%\Desktop\zbin\G2_pdfbox.jar;%userprofile%\Desktop\zbin\G2_hutool.jar;%userprofile%\Desktop\zbin\G2_jshortcut_oberzalek.jar -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\G2_ApplyRuleFor_TypeFile.java
//        	@java -cp  %userprofile%\Desktop\zbin\G2_webp-imageio.jar;%userprofile%\Desktop\zbin\G2_spire.presentation.free-3.9.0.jar;%userprofile%\Desktop\zbin\G2_fontbox.jar;%userprofile%\Desktop\zbin\G2_commons-logging-api.jar;%userprofile%\Desktop\zbin\G2_pdfbox.jar;%userprofile%\Desktop\zbin\G2_hutool.jar;%userprofile%\Desktop\zbin\G2_jshortcut_oberzalek.jar;%userprofile%\Desktop\zbin    G2_ApplyRuleFor_TypeFile %1  %2  %3 %4  %5  %6  %7  %8  %9 

			batList.add("@echo off");
			batList.add("Setlocal ENABLEDELAYEDEXPANSION");
			batList.add("@javac -cp " + classPathStr
					+ "  -Xlint:unchecked -encoding UTF-8 %userprofile%\\Desktop\\zbin\\" + javaFile.getName());
			batList.add("@java -cp " + classPathStr + "  " + javaNameNoPoint + "  %1  %2  %3 %4  %5  %6  %7  %8  %9 ");

		}

		void buildShCommand(ArrayList<String> shList, File javaFile, ArrayList<String> jarNameList, String RuleTag) {

//        	shList.add("════════════════════════ "+RuleTag+BAT_OR_SH_Point+" ════════════════════════");

			StringBuilder jarSB = new StringBuilder();
			for (int i = 0; i < jarNameList.size(); i++) {
				// %userprofile%\Desktop\zbin\G2_webp-imageio.jar;
				// G2_webp-imageio.jar;
				String jarName = jarNameList.get(i);
				if (!jarName.startsWith(RuleTag)) {
					System.out.println("!!!!!!!!!!!!!!!!警告当前存在 Jar包不是规则命名: " + jarName);
					shList.add("!!!!!!!!!!!!!!!!!!警告当前存在 Jar包不是规则命名: " + jarName);
				}
				jarSB.append("$HOME/Desktop/zbin/" + jarName + ":");

			}

			String classPathStr = "classpath=$classes:" + jarSB.toString() + "$HOME/Desktop/zbin/";
//        	#!/bin/bash
//        	CURPATH=$(pwd)
//        	DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
//        	#cd $DIR
//        	classes=$DIR
//        	libs=$DIR
//        	classpath=$classes:$HOME/Desktop/zbin/G2_jshortcut_oberzalek.jar:$HOME/Desktop/zbin/G2_webp-imageio.jar:$HOME/Desktop/zbin/
//
//        	javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/G2_ApplyRuleFor_TypeFile.java
//        	java  -classpath $classpath G2_ApplyRuleFor_TypeFile $1 $2 $3 $4 $5 $6 $7 $8 $9 

			String javaNameNoPoint = getFileNameNoPointNoLowerCase(javaFile.getName());

			shList.add("#!/bin/bash");
			shList.add("CURPATH=$(pwd)");
			shList.add("DIR=\"$( cd \"$( dirname \"${BASH_SOURCE[0]}\" )\" && pwd )\" ");
			shList.add("#cd $DIR");
			shList.add("classes=$DIR");
			shList.add(classPathStr);
			shList.add("");
			shList.add("javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/" + javaFile.getName());
			shList.add("@javac -cp " + jarSB.toString()
					+ "  -Xlint:unchecked -encoding UTF-8 %userprofile%\\Desktop\\zbin\\" + javaFile.getName());
			shList.add("java  -classpath $classpath " + "  -Xmx10240m -Xms10240m -Xmn5120m " + javaNameNoPoint + " "
					+ " $1 $2 $3 $4 $5 $6 $7 $8 $9 ");

		}

		@Override
		String simpleDesc() {
			return " ## A1.bat G1.bat G2.sh 本地依据Java名称生成 包括 xx.sh  和 xx.bat  // 当前目录只能有一个 .java 多个 .jar 包 !";
		}

	}

	class Bat_Format_Rule_31 extends Basic_Rule {

		ArrayList<Bat_Method> allMethodList = new ArrayList<Bat_Method>();

		String batHead_1 = "@ECHO off";
		String batHead_2 = "setlocal enabledelayedexpansion";
		String batHead_3 = "chcp 65001"; // 为了支持中文打印

		File bat_template_file = null;
		File bat_ruleMethodName_file = null; // 保存业务函数名称的方法
		ArrayList<Bat_Aera> mAreaList;
		// echo zbatrule_I9_Rule30.bat _1_ ##安装本地目录下的所有apkadbinstall*.apk
		// echo zbatrule_I9_Rule30.bat _2_ ##获取手机当前正在运行的APK到本地
		ArrayList<String> allRuleMethodNameList = new ArrayList<String>();
		ArrayList<String> allRuleTipStrList = new ArrayList<String>();

		Bat_Format_Rule_31() {
			super(31, false);
			bat_template_file = new File(
					zbinPath + File.separator + "win_zbin" + File.separator + "zbatrule_I9_Rule30.bat");
//	            bat_ruleMethodName_file  = new File(zbinPath+File.separator+"win_zbin"+File.separator+"zbatrule_I9_Rule30_method.txt");
			mAreaList = new ArrayList<Bat_Aera>();
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				String fileName = fileItem.getName().toLowerCase();
				if (!fileName.endsWith(".bat")) {
					System.out.println("当前输入的文件不是 .bat文件无法进行 format 操作!  file=" + fileItem.getAbsolutePath());
					continue;
				}
				if (fileItem.equals(bat_template_file)) {
					System.out.println("!!!!模板文件本身的Format!!!!");
				}
				ArrayList<String> InputFile_rawConentList = ReadFileContentAsList(fileItem);

				// 一个 空的 bat文件 , 那么 使用 format 填充它
				if (InputFile_rawConentList.size() < 10) {
					System.out.println("!!!!写入模板!!!!");
					ArrayList<String> TemplateFile_rawConentList = ReadFileContentAsList(bat_template_file);

					ArrayList<String> TemplateFile_FormatBatCodeList = Bat_To_Format(TemplateFile_rawConentList);
					writeContentToFile(fileItem, TemplateFile_FormatBatCodeList);
					continue;
				}

				ArrayList<String> TemplateFile_rawConentList = ReadFileContentAsList(bat_template_file);

//                 ArrayList<String> TemplateFile_FormatBatCodeList  = Bat_To_Format(TemplateFile_rawConentList);

				// 如果 是一个 比 模板文件大 的文件 那么解析 这个文件 生成 这个文件的模板
				if (InputFile_rawConentList.size() >= TemplateFile_rawConentList.size()) {

					// 找到 开始解析 .bat 文件
					ArrayList<String> InputFile_formatAllBatCodeList = Bat_To_Format(InputFile_rawConentList);

					showStringList_WithHead(InputFile_formatAllBatCodeList, "当前格式化bat文件最终代码情况");

					writeContentToFile(fileItem, InputFile_formatAllBatCodeList);

				}

				if (InputFile_rawConentList.size() > 10
						&& InputFile_rawConentList.size() < TemplateFile_rawConentList.size()) {
					System.out.println("输入的文件不是模板文件! file=" + fileItem.getAbsolutePath());

				}

//  如果 是 自己和 自己 比较  那么 更新  method 方法 文件 【 已经不需要 bat_ruleMethodName_file ， 这个文件了 】
//                 if(fileItem.equals(bat_template_file)) {
//         			writeContentToFile(bat_ruleMethodName_file,allRuleMethodNameList);
//                	 
//                 }

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 对当前 bat 文件进行 format 如果不是bat文件不操作 增项假如模板zbatrule_I9_Rule30.bat中 ";
		}

		// Bat_Method_Aera { Bat_Operation_A{MethodA,MethodB}
		// Bat_Operation_B{MethodC,MethodD} }
		// 用于解析当前 解析的文件的方法的个数
//    	ArrayList<Bat_Method> allMethodList_InCommonFile = new ArrayList<Bat_Method> ();
		// 用于解析当前 模板文件 zzbattest_I9.bat 中 方法的 个数
//    	ArrayList<Bat_Method> allMethodList_InTemplate = new ArrayList<Bat_Method> ();
		class Bat_Aera {
			int aera_index; // 从0 开始的区域索引 0.system_init 1.program_exe_area 2.method_define
			String aera_name; // 区域的名称 System_Init_Aera
			String desc; // 该区域的说明
			String begin_Word = "rem ";
			String end_Word = "rem ";
			boolean isdefine_method = false; // 在 area 内部是否有定义 函数

			// rem ══════════════════════════════════════════ System_Init_Aera_Begin
			// ══════════════════════════════════════════
			String aera_Begin_Tag;
			// rem ══════════════════════════════════════════ System_Init_Aera_End═════
			String aera_End_Tag;
			// 1. 含有 rem 2. 含有 area标识 ══ _area_ 3. 含有当前area的名字
			ArrayList<String> aera_beginTagCharList; // 起始区域 起始 那行字符串 所含的 标识字符
			ArrayList<String> aera_endTagCharList;
			ArrayList<String> aera_raw_content; // 从 bat 读取到的 原始的 内容
			ArrayList<Bat_Operation> defineOperationList; // 只有 index=2 的 method_define 才有这个函数列表

			Bat_Aera(int index, String mArea_Name) {
				aera_index = index;
				aera_name = mArea_Name;
				begin_Word = "rem ";
				end_Word = "rem ";

				aera_beginTagCharList = new ArrayList<String>();
				aera_beginTagCharList.add("rem");
				aera_beginTagCharList.add("══");
				aera_beginTagCharList.add("_aera");
				aera_beginTagCharList.add("begin");
				aera_beginTagCharList.add(aera_name);

				aera_endTagCharList = new ArrayList<String>();
				aera_endTagCharList.add("rem");
				aera_endTagCharList.add("══");
				aera_endTagCharList.add("_aera");
				aera_endTagCharList.add("end");
				aera_endTagCharList.add(aera_name);
				aera_raw_content = new ArrayList<String>();
				aera_Begin_Tag = "rem " + "══════════════════════════════════════════" + aera_name.toUpperCase()
						+ " Begin" + "══════════════════════════════════════════";

				aera_End_Tag = "rem " + "══════════════════════════════════════════" + aera_name.toUpperCase() + " End"
						+ "══════════════════════════════════════════";

			}

			Bat_Aera(int index, String mArea_Name, boolean isMethodDefine) {
				this(index, mArea_Name);
				isdefine_method = isMethodDefine;
				if (isdefine_method) {
					defineOperationList = new ArrayList<Bat_Operation>();

				}
			}

			ArrayList<String> getAreaFormatContent() {
				ArrayList<String> allAreaContent = new ArrayList<String>();
				if (!isdefine_method) {
					return aera_raw_content;
				}

				allAreaContent.add("\n");
				allAreaContent.add(aera_Begin_Tag);
				allAreaContent.add("\n");
				for (int i = 0; i < defineOperationList.size(); i++) {
					Bat_Operation bat_operation = defineOperationList.get(i);
					ArrayList<String> curOperationFormatContent = bat_operation.getOperationFormatContent();
					allAreaContent.addAll(curOperationFormatContent);

				}

				allAreaContent.add(aera_End_Tag);

				return allAreaContent;
			}

			HashMap<String, ArrayList<String>> operationName_RawData_Map;
//    		operationName_RawData_Map = new 	  HashMap<String,ArrayList<String>>();

			/*
			 * ======== File_Operation_Begin ====================================
			 * ================================= File_Operation_End =============
			 * ================================= System_Operation_Begin =========
			 * ================================= System_Operation_End ===========
			 * ================================= String_Operation_Begin =========
			 * ================================== String_Operation_End ==========
			 * ================================== Test_Operation_Begin ==========
			 * ================== Test_Operation_End ============================
			 */

			void initOperationAndMethodWithRawContent(ArrayList<String> rawContentList) {
				ArrayList<String> operationNameList = new ArrayList<String>();

				for (int i = 0; i < rawContentList.size(); i++) {
					String oneLine = rawContentList.get(i).toLowerCase().trim();
					if (oneLine.startsWith("rem ") && oneLine.contains("_operation") && oneLine.contains("====")
							&& oneLine.contains("begin")) {

						String OperationName = oneLine.replace("rem ", "");
						OperationName = OperationName.replace(" ", "");
						OperationName = OperationName.replace("=", "");
						OperationName = OperationName.replace("begin", "");
						OperationName = OperationName.toLowerCase().trim();
						operationNameList.add(OperationName);
					}

				}

				showStringList(operationNameList, "Operation名称");

				// defineOperationList = new ArrayList<Bat_Operation>();
				for (int i = 0; i < operationNameList.size(); i++) {
					String operation_name = operationNameList.get(i);
					Bat_Operation curOperation = new Bat_Operation(i, operation_name);
					defineOperationList.add(curOperation);

				}
				System.out.println("defineOperationList.size() = " + defineOperationList.size()
						+ "  rawContentList.size() = " + rawContentList.size());
				for (int i = 0; i < defineOperationList.size(); i++) {
					Bat_Operation curOperation = defineOperationList.get(i);
					ArrayList<String> Operation_raw_contentList = new ArrayList<String>();

					for (int j = 0; j < rawContentList.size(); j++) {
						String oneLineStr = rawContentList.get(j);

						isOperationBegin = curOperation.isOperationBegin(oneLineStr);
						if (isOperationBegin) {
							isOperationRawBegin = true;
						}
						isOperationEnd = curOperation.isOperationEnd(oneLineStr);
						if (isOperationEnd) {
							isOperationRawBegin = false;
							Operation_raw_contentList.add(oneLineStr);
						}

						if (isOperationRawBegin) {
							Operation_raw_contentList.add(oneLineStr);
						}
						// System.out.println("isOperationBegin="+ isOperationBegin + "
						// isOperationEnd="+ isOperationEnd + " isOperationRawBegin="+
						// isOperationRawBegin +" Line="+oneLineStr);

					}
					isOperationBegin = false;
					isOperationEnd = false;
					isOperationRawBegin = false;
					System.out.println("Operation_raw_contentList.size() = " + Operation_raw_contentList.size());
					curOperation.operation_raw_content.addAll(Operation_raw_contentList);

					showStringList(curOperation.operation_raw_content, curOperation.operation_name + "_raw内容");
				}

				// 实现解析 Operation 初始化 Operation里面的 method
				for (int i = 0; i < defineOperationList.size(); i++) {
					Bat_Operation curOperation = defineOperationList.get(i);
					curOperation.initMethodDataWithRawContent(curOperation.operation_raw_content);
				}

			}

			boolean initDataWithRawContent(ArrayList<String> rawContentList) {
				boolean initFlag = false;

				// 定义函数 的 area 区域 包含 operation
				if (isdefine_method) {

					initOperationAndMethodWithRawContent(rawContentList);

				}

				return initFlag;
			}

			boolean isAreaEnd(String oneLine) {
				boolean isEndLine = false;
				String oneLine_trim = oneLine.toLowerCase().trim();
				if (!oneLine_trim.startsWith(end_Word)) {
					return isEndLine;
				}

				for (int i = 0; i < aera_endTagCharList.size(); i++) {
					String endTagChar = aera_endTagCharList.get(i);
					if (!oneLine_trim.contains(endTagChar)) {

//    					System.out.println(" endTagChar = "+ endTagChar + " 不包含在:"+oneLine_trim);

						return isEndLine;
					}
				}

				// rem ══════════════════════════════════════════ System_Init_Aera_End ═════
				// 满足 包含 开始 条件的 所有 情况
				isEndLine = true;
				return isEndLine;

			}

			boolean isAreaBegin(String oneLine) {
				boolean isBeginLine = false;
				String oneLine_trim = oneLine.toLowerCase().trim();
				if (!oneLine_trim.startsWith(begin_Word)) {
					return isBeginLine;
				}

				for (int i = 0; i < aera_beginTagCharList.size(); i++) {
					String beginTagChar = aera_beginTagCharList.get(i);
					if (!oneLine_trim.contains(beginTagChar)) {
//    					System.out.println(" beginTagChar = "+ beginTagChar + " 不包含在:"+oneLine_trim);
						return isBeginLine;
					}
				}

				// rem ══════════════════════════════════════════ System_Init_Aera_Begin ═════
				// 满足 包含 开始 条件的 所有 情况
				isBeginLine = true;

				return isBeginLine;

			}
		}

		class Bat_Operation {
			// rem ================================================ Test_Operation_End =====
			String begin_Word = "rem ";
			String end_Word = "rem ";

			int operation_index;
			String operation_name; // 操作类型 名称
			String operation_desc; // 操作说明
			ArrayList<String> operation_raw_content; // Operation 区域的原始内容
			// 1. 含有 rem 2. 含有 area标识 ==== _Operation_ 3. 含有当前_Operation_的名字 4. begin
			ArrayList<String> operation_beginTagCharList; // 区域定义的 起始 字符串 集合
			ArrayList<String> operation_endTagCharList; // 区域定义的 结束 字符串 集合

			ArrayList<Bat_Method> operation_MethodList; // 当前定义的方法的集合

			// rem =============================== File_Operation_Begin
			// ===============================
			String operation_begin_tag;
			String operation_end_tag;

//          rem ========================   File_Operation_Begin ================================================
//    		rem =========================  File_Operation_End ===================================
			Bat_Operation(int index, String operationName) {
				operation_index = index;
				operation_name = operationName;
				operation_raw_content = new ArrayList<String>();
				operation_MethodList = new ArrayList<Bat_Method>();
				operation_beginTagCharList = new ArrayList<String>();

				operation_beginTagCharList.add("rem");
				operation_beginTagCharList.add("====");
				operation_beginTagCharList.add("begin");
				operation_beginTagCharList.add("_operation");
				operation_beginTagCharList.add(operation_name);

				operation_endTagCharList = new ArrayList<String>();
				operation_endTagCharList.add("rem");
				operation_endTagCharList.add("====");
				operation_endTagCharList.add("end");
				operation_endTagCharList.add("_operation");
				operation_endTagCharList.add(operation_name);

				operation_begin_tag = "rem " + "======================== " + operation_name.toUpperCase() + " Begin"
						+ "======================== ";
				operation_end_tag = "rem " + "======================== " + operation_name.toUpperCase() + " End"
						+ "======================== ";

			}

			ArrayList<String> getOperationFormatContent() {
				ArrayList<String> curOperationFormatList = new ArrayList<String>();
				curOperationFormatList.add(operation_begin_tag);
				curOperationFormatList.add("\n");
				for (int i = 0; i < operation_MethodList.size(); i++) {
					Bat_Method cur_method = operation_MethodList.get(i);
					curOperationFormatList.addAll(cur_method.method_format_content);
					curOperationFormatList.add("\n");
					curOperationFormatList.add("\n");
				}
				curOperationFormatList.add(operation_end_tag);

				return curOperationFormatList;
			}

			boolean isOperationEnd(String oneLine) {
				boolean isEndLine = false;
				String oneLine_trim = oneLine.toLowerCase().trim();
				if (!oneLine_trim.startsWith(end_Word)) {
					return isEndLine;
				}

				for (int i = 0; i < operation_endTagCharList.size(); i++) {
					String endTagChar = operation_endTagCharList.get(i);
					if (!oneLine_trim.contains(endTagChar)) {

//    					System.out.println(" endTagChar = "+ endTagChar + " 不包含在:"+oneLine_trim);

						return isEndLine;
					}
				}

				// rem ══════════════════════════════════════════ System_Init_Aera_End ═════
				// 满足 包含 开始 条件的 所有 情况
				isEndLine = true;
				return isEndLine;

			}

			boolean isOperationBegin(String oneLine) {
				boolean isBeginLine = false;
				String oneLine_trim = oneLine.toLowerCase().trim();
				if (!oneLine_trim.startsWith(begin_Word)) {
					return isBeginLine;
				}

				for (int i = 0; i < operation_beginTagCharList.size(); i++) {
					String beginTagChar = operation_beginTagCharList.get(i);
					if (!oneLine_trim.contains(beginTagChar)) {
//    					System.out.println(" beginTagChar = "+ beginTagChar + " 不包含在:"+oneLine_trim);
						return isBeginLine;
					}
				}

				// rem ══════════════════════════════════════════ System_Init_Aera_Begin ═════
				// 满足 包含 开始 条件的 所有 情况
				isBeginLine = true;

				return isBeginLine;

			}

			void initMethodDataWithRawContent(ArrayList<String> rawOperationDataList) {

//        	System.out.println("rawOperationDataList.size() = "+ rawOperationDataList.size());

				// 在 Operation 寻找 Method
				ArrayList<String> methodNameList = new ArrayList<String>();
				for (int i = 0; i < rawOperationDataList.size(); i++) {
					String oneLine = rawOperationDataList.get(i).toLowerCase().trim();

					if (oneLine.startsWith(":") && !oneLine.startsWith("::") && oneLine.contains("_func_")
							&& oneLine.contains("x") && !oneLine.contains(" ")) { // 以: 开头 并不以 :: 开头

						String[] splitArr = oneLine.split("_");
						if (splitArr != null) {
							String lastPartStr = splitArr[splitArr.length - 1];

							String inputParamStr = lastPartStr.substring(0, lastPartStr.indexOf("x"));
							String outputParamStr = lastPartStr.substring(lastPartStr.indexOf("x") + "x".length());
							if (isNumeric(outputParamStr) && isNumeric(inputParamStr)) {

								String methodName = oneLine.replace(":", "");
								methodNameList.add(methodName);

							}
						}

					}

				}

				showStringList(methodNameList, operation_name + "[MethodItem]");

				for (int i = 0; i < methodNameList.size(); i++) {
					String methodName = methodNameList.get(i);
					Bat_Method curMethod = new Bat_Method(i, methodName);
					operation_MethodList.add(curMethod);
					allMethodList.add(curMethod);

				}

				for (int i = 0; i < operation_MethodList.size(); i++) {
					Bat_Method curMethod = operation_MethodList.get(i);
					ArrayList<String> Method_raw_contentList = new ArrayList<String>();

					System.out.println("═════════════════════════════════" + curMethod.bat_method_name
							+ " method_revert_begin  raw_string[" + rawOperationDataList.size() + "]"
							+ "═════════════════════════════════");

					for (int j = 0; j < rawOperationDataList.size(); j++) {
						String oneLineStr = rawOperationDataList.get(j);
						System.out.println("________________________________________________  ");
						isMethodBegin = curMethod.isMethodBegin(oneLineStr);
						if (isMethodBegin && !isMethodEnd) {
							isMethodRawBegin = true;
						}
						System.out.println(
								"A1 isMethodBegin = " + isMethodBegin + "  isMethodRawBegin=" + isMethodRawBegin);
						isMethodEnd = curMethod.isMethodEnd(oneLineStr);
//      				 System.out.println("X1 methodName="+curMethod.bat_method_name+"   isMethodBegin="+ isMethodBegin + "   isMethodEnd="+ isMethodEnd + "  isMethodRawBegin="+ isMethodRawBegin +" Line["+j+"]["+rawOperationDataList.size()+"]="+oneLineStr);
						System.out.println("A2 isMethodBegin = " + isMethodBegin + "  isMethodRawBegin="
								+ isMethodRawBegin + " isMethodEnd=" + isMethodEnd);

						if (isMethodEnd) {
							isMethodRawBegin = false;
						}

						System.out.println("A3 isMethodBegin = " + isMethodBegin + "  isMethodRawBegin="
								+ isMethodRawBegin + " isMethodEnd=" + isMethodEnd);

						if (isMethodRawBegin) {
							System.out.println("A3_1 添加代码语句  oneLineStr=" + oneLineStr
									+ "  Method_raw_contentList.size()=" + Method_raw_contentList.size());
							Method_raw_contentList.add(oneLineStr);
						}
						System.out.println(
								"A4 methodName=" + curMethod.bat_method_name + " isMethodBegin=" + isMethodBegin
										+ "   isMethodEnd=" + isMethodEnd + "  isMethodRawBegin=" + isMethodRawBegin
										+ " Line[" + j + "][" + rawOperationDataList.size() + "]=" + oneLineStr);

					}

					isMethodBegin = false;
					isMethodEnd = false;
					isMethodRawBegin = false;

					curMethod.method_raw_content.addAll(Method_raw_contentList);
					System.out.println("curMethod = " + curMethod.bat_method_name
							+ "  curMethod.method_raw_content.size()" + curMethod.method_raw_content.size());
					showStringList(curMethod.method_raw_content, curMethod.bat_method_name + "raw方法");
					System.out.println(
							"═════════════════════════════════" + curMethod.bat_method_name + " method_revert_end["
									+ curMethod.method_raw_content.size() + "]" + "═════════════════════════════════");

				}

				BeginMethodFormat_In_Operation();

			}

			void BeginMethodFormat_In_Operation() {

				for (int i = 0; i < operation_MethodList.size(); i++) {
					Bat_Method batMethod = operation_MethodList.get(i);
					batMethod.doFormat();
				}

			}

		}

		class Bat_Method {
			String Pre_SETLOCAL = "::SETLOCAL";
			String End_SETLOCAL = "::ENDLOCAL";
			String End_Method_Tag = "goto:eof";
			String Method_Return_Tag = "_return_";
			String Method_In_Tag = "______________Method_In";
			String Method_Out_Tag = "______________Method_Out";

			int methodIndex;

			String Rule_Bat_File_Name = "zbatrule_I9_Rule30.bat";
			int ruleIndex; // 业务的序号
			boolean isRuleMethod; // 是否是业务方法
			String ruleIndex_Tag; // zbatrule_I9.bat _1_
			ArrayList<String> method_ruletip_list; // 当前 ruletip 规则

			String bat_method_name; // bat 方法 的 名称 recordFileNameToFile_func_1x1
			String bat_method_name_nofunc; // 没有 func 标示的方法的名字
			int input_param_count; // 输入的参数的个数
			int output_param_count; // 输出参数的个数

			String endPrintCode; // 代码最后输出结果的代码 添加对 isRuleMethod 的 支持
			// 正常的 endPrintCode
			// echo [ruletipanalysis_func_0x1 EndPrintCode]
			// ruletipanalysis_return_1=[!ruletipanalysis_return_1!] param1=[__empty__]
			// 对于 rule11vtryMethod_func_1x0 // 这样的 规则函数 是没有 输入参数的 输入参数要自己动态计算
			// 规定 ruleMethod的输入参数名称为 ruletipanalysis_return_1 dynamic1=[__empty__]
			// rule1vbankupapk_func_1x0 echo [rule1vbankupapk_func_1x0 EndPrintCode]
			// output=[__empty__] param1=[%1]
			// echo [rule1vbankupapk_func_1x0 EndPrintCode] output=[__empty__]
			// dynamic_param1=[!rule1vbankupapk_dynamic_param1!]
			// dynamic_param2=[!rule1vbankupapk_dynamic_param2!]

			String method_seperate_line; // 在函数定义下面出现用以分隔符的作用 rem ======================== getTimeNona_func_0x1
			ArrayList<String> method_beginTagCharList;

			ArrayList<String> method_raw_content; // bat的原始的读取到的内容

			// // 1.去除函数定义语句 2.去除 rem注释开头 :: 开头的语句 (暗含 ::SETLOCAL ::ENDLOCAL)
			ArrayList<String> method_firstfixed_content;
			ArrayList<String> method_desc_list; // rem desc: 开头的对函数进行描述的中文集合 rem desc: 注意空格

			ArrayList<String> method_Sample_list; // rem sample: 开头的字样 用来模拟函数调用的情况 一般都是代码
			ArrayList<String> method_SampleOut_list; // 用来描述函数Sample的返回值的情况

			ArrayList<String> method_format_content; // 对 bat method 进行 格式化后的字符串集合

			// 方法的起始标示 1. 以:开头 并且第二个字符不是: 2. 包含 _func_ 3.包含 x 4.trim() 后 不包含空格

			String method_In_PrintCode; // 函数进入打印代码
			String method_Out_PrintCode; // 函数离开打印代码
			// echo ______________Method_In_searchOneTargetFile4Dir4Type_func_2x1:
			// echo ______________Method_Out_searchOneTargetFile4Dir4Type_func_2x1:

			Bat_Method(int cMethodIndex, String methodName) {

				methodIndex = cMethodIndex;
				bat_method_name = methodName; // clearstringpadding_func_1x1
				bat_method_name_nofunc = methodName.substring(0, methodName.indexOf("_func_"));
				initRuleTag();
//    	System.out.println("AA bat_method_name_nofunc =" + bat_method_name_nofunc);
				method_seperate_line = "rem ======================================== " + methodName;
				initParamsCount(methodName);
				buildEndPrintCode(methodName, bat_method_name_nofunc, input_param_count, output_param_count,
						isRuleMethod);
				method_raw_content = new ArrayList<String>();
				method_firstfixed_content = new ArrayList<String>();
				method_Sample_list = new ArrayList<String>();
				method_SampleOut_list = new ArrayList<String>();

				method_desc_list = new ArrayList<String>();
				method_ruletip_list = new ArrayList<String>();
				method_beginTagCharList = new ArrayList<String>();
				method_beginTagCharList.add("_func_");

				method_beginTagCharList.add(":");
				method_beginTagCharList.add("x");

				method_format_content = new ArrayList<String>();

				method_In_PrintCode = "echo " + Method_In_Tag + " " + bat_method_name; // 函数进入打印代码
				method_Out_PrintCode = "echo " + Method_Out_Tag + " " + bat_method_name; // 函数离开打印代码
				// echo ______________Method_In_searchOneTargetFile4Dir4Type_func_2x1:
				// echo ______________Method_Out_searchOneTargetFile4Dir4Type_func_2x1:

			}

			void initRuleTag() {
				String bat_method_name_nofunc_trim_lower = bat_method_name_nofunc.trim().toLowerCase();
				boolean flagA = bat_method_name_nofunc_trim_lower.startsWith("rule")
						&& bat_method_name_nofunc_trim_lower.contains("v");

				if (!flagA) {

					isRuleMethod = false;
					ruleIndex = -1;
					ruleIndex_Tag = "";
					System.out.println(
							"bat_method_name_nofunc = " + bat_method_name_nofunc + " [isRuleMethod = " + isRuleMethod);
					return;
				}

				String preVStr = bat_method_name_nofunc_trim_lower.substring(0,
						bat_method_name_nofunc_trim_lower.indexOf("v"));
				preVStr = preVStr.replace("rule", "");

				if (isNumeric(preVStr)) {
					isRuleMethod = true;
					ruleIndex = Integer.parseInt(preVStr);
//    		ruleIndex_Tag = Rule_Bat_File_Name +" _"+ruleIndex+"_ "; 
					ruleIndex_Tag = "%init_input_0%" + " _" + ruleIndex + "_ ";

					System.out.println("bat_method_name_nofunc = " + bat_method_name_nofunc + " [isRuleMethod = "
							+ isRuleMethod + " ruleIndex=" + ruleIndex + " ruleIndex_Tag=" + ruleIndex_Tag);

				}

			}

			void InitFormatContent() {

				if (method_firstfixed_content.size() == 0) {
					System.out.println("当前Method " + bat_method_name + " 出错 读取到的内容为空"
							+ "method_firstfixed_content.size() = " + method_firstfixed_content.size()
							+ "  method_raw_content.size()=" + method_raw_content.size());
					return;
				}

				// 第一步 添加 函数定义的名称
				method_format_content.add(":" + bat_method_name);

				// 2. 添加 函数分隔提示符
				method_format_content.add(method_seperate_line);

				// 如果 当前 函数 是 rule 函数 那么 需要添加 rule_tip:
				if (isRuleMethod) {
					if (!allRuleMethodNameList.contains(bat_method_name)) {
						allRuleMethodNameList.add(bat_method_name);
					}

					if (method_ruletip_list.size() > 0) {
						for (int i = 0; i < method_ruletip_list.size(); i++) {
							String rule_tip = method_ruletip_list.get(i);
							String addToFormat_ruleStr = rule_tip;
							// 如果当前 tip 不包含 #
							if (!rule_tip.contains("#")) {

								addToFormat_ruleStr = "## " + rule_tip;
							}

							// 把 空格转为 null?
							/*
							 * if(addToFormat_ruleStr.contains(ruleIndex_Tag.replace(" ", ""))) {
							 * 
							 * addToFormat_ruleStr = addToFormat_ruleStr.replace(ruleIndex_Tag.replace(" ",
							 * ""), ""); }
							 */

							// 如果 当前 tip 不包含 zbatrule_I9_Rule30.bat _3_ 这样的字样
							// 那么直接 zbatrule_I9_Rule30.bat _3_ ##
							if (!addToFormat_ruleStr.contains(ruleIndex_Tag)) {
								method_format_content
										.add("rem rule_tip: " + ruleIndex_Tag + " " + addToFormat_ruleStr.trim());
								allRuleTipStrList.add(ruleIndex_Tag + " " + addToFormat_ruleStr.trim());
							} else {
								// 如果当前tip 包含 zbatrule_I9_Rule30.bat _3_ 那么变成
								// rem rule_tip: zbatrule_I9_Rule30.bat _3_
								method_format_content.add("rem rule_tip: " + addToFormat_ruleStr.trim());
								allRuleTipStrList.add(addToFormat_ruleStr);
							}

						}
					} else { // 当前没有 rule_tip:时 直接 输出 rem rule_tip: zbatrule_I9_Rule30.bat _3_
						method_format_content.add("rem rule_tip: " + ruleIndex_Tag + "  ## ");
					}

				}

				// 3. 添加 rem:desc 的语句
				if (method_desc_list.size() > 0) {
					for (int i = 0; i < method_desc_list.size(); i++) {
						String desc = method_desc_list.get(i);
						method_format_content.add("rem desc: " + desc);
					}
				} else {
					method_format_content.add("rem desc: ");
				}

				// 4. 添加 rem:sample 的语句

				if (method_Sample_list.size() > 0) {
					for (int i = 0; i < method_Sample_list.size(); i++) {
						String sample = method_Sample_list.get(i);
						method_format_content.add("rem sample: " + sample);
					}
				} else {
					method_format_content.add("rem sample: ");
				}

				// 5. 添加 rem:sample_out: 的语句

				if (method_SampleOut_list.size() > 0) {
					for (int i = 0; i < method_SampleOut_list.size(); i++) {
						String sample_out = method_SampleOut_list.get(i);
						method_format_content.add("rem sample_out: " + sample_out);
					}
				} else {
					method_format_content.add("rem sample_out: ");
				}

				// 6. 增加 setlocal
				method_format_content.add(Pre_SETLOCAL);

				method_format_content.add(method_In_PrintCode); // 添加进入函数的代码

				// 如果是 ruleIndex 那么 需要定义 它的 动态计算的参数
				if (isRuleMethod) {
					for (int i = 0; i < input_param_count; i++) {
						method_format_content.add("set " + bat_method_name_nofunc + "_dynamic_param" + (i + 1) + "=");
					}
				}
				for (int i = 0; i < method_firstfixed_content.size(); i++) {
					String realCodeOne = method_firstfixed_content.get(i);
					String realCodeOne_lower_trim = realCodeOne.trim().toLowerCase();
					// 如果当前实体代码中遇到 goto: eof 之类的 那么在它之前 添加一个 endprint代码
					if (realCodeOne_lower_trim.startsWith(End_Method_Tag)) {
						// 检测之前的 代码是否是 endPrintCode
						int pre_index = i - 1;
						if (pre_index >= 0 && pre_index < method_firstfixed_content.size() - 1) {
							String preOneCode = method_firstfixed_content.get(pre_index).trim();
//    						if(!preOneCode.startsWith(endPrintCode.trim())) {
							// 如果上一行不是 输出打印的话 是打印 输出 end_return语句的话 那么就只添加 一个 method_Out_PrintCode
							if (!preOneCode.equals(method_Out_PrintCode.trim())
									&& preOneCode.equals(endPrintCode.trim())) {
								method_format_content.add(method_Out_PrintCode);
							} else if (!preOneCode.equals(method_Out_PrintCode.trim())
									&& !preOneCode.equals(endPrintCode.trim())) {
								// 如果 既不是 endcode 也不是 outcode 那么 两行 语句 都要 添加
								method_format_content.add(endPrintCode);
								method_format_content.add(method_Out_PrintCode);
							}
						}

					}

					method_format_content.add(realCodeOne);
				}

				String lastCode = method_format_content.get(method_format_content.size() - 1).trim();
				System.out.println("lastCode = 【" + lastCode + "】 !lastCode.equals(endPrintCode.trim()) = 【"
						+ !lastCode.equals(endPrintCode.trim()) + "】  methodname=【" + bat_method_name
						+ "】  endPrintCode=【" + endPrintCode.trim() + "】");
				if (!lastCode.equals(endPrintCode.trim())) {
					// end3. 添加 endprint 代码
					method_format_content.add(endPrintCode);
				}

				// 添加离开函数的Log
				method_format_content.add(method_Out_PrintCode);
				// end2 . 添加
				method_format_content.add(End_SETLOCAL);
				// end1. 添加 Goto:eof
				method_format_content.add(End_Method_Tag);

				ArrayList<String> fixedChineseFormatList = new ArrayList<String>();

				for (int i = 0; i < method_format_content.size(); i++) {
					String formatItem = method_format_content.get(i);
					String preLine = "";
					if (i > 0) {
						preLine = method_format_content.get(i - 1);
					}
					if (isContainChinese(formatItem) && isContainChinese(preLine)) {
						fixedChineseFormatList.add(""); // 打印的时候 自动 添加 /r/n 意味着多添加了 一行
						fixedChineseFormatList.add(formatItem);
						System.out.println("preLine = " + preLine + "   formatItem=" + formatItem);
						continue;
					}
					if (!"".equals(formatItem.trim())) {
						fixedChineseFormatList.add(formatItem);
						System.out.println("formatItem=[" + formatItem + "]");
					}

				}
				method_format_content.clear();
				method_format_content.addAll(fixedChineseFormatList);
				showStringList(method_format_content, bat_method_name + "-Format格式完成后样子");
			}

//          tag=[helloworld_func_0x0raw方法] Line[1]   :helloworld_func_0x0
//    		tag=[helloworld_func_0x0raw方法] Line[2]   ::SETLOCAL
//    		tag=[helloworld_func_0x0raw方法] Line[3]   echo hello_world zukgit
//    		tag=[helloworld_func_0x0raw方法] Line[4]   ::ENDLOCAL
//    		tag=[helloworld_func_0x0raw方法] Line[5]   goto:eof
//    		tag=[helloworld_func_0x0raw方法] Line[6]   
//    		tag=[helloworld_func_0x0raw方法] Line[7]   rem ================================  Test_Operation_End ============================

			// 对当前的代码进行 format 格式化
			// 第一行: 函数定义 :xxx_funx_0x0
			// 第二行: 函数显示分割符号 rem ================================ xxx_funx_0x0
			// 第三行: 函数说明的集合 rem desc: xxxxxx
			// 第x行: 函数说明的集合 ::SETLOCAL
			// ...... 【自由发挥空间】
			// ...... 【自由发挥空间】
			// ...... 【自由发挥空间】
			// 倒数第三行 echo isFileExist_return_1=[%isFileExist_return_1%] param1=[%1]
			// 倒数第二行 ::ENDLOCAL
			// 最后一行 goto:eof

			// // 1.去除函数定义语句 2.去除 rem注释开头 :: 开头的语句 (暗含 ::SETLOCAL ::ENDLOCAL)
			void initFirstFixedConent() {

				for (int i = 0; i < method_raw_content.size(); i++) {
					String rawContent = method_raw_content.get(i);
					String batCodeOneLine = method_raw_content.get(i).trim().toLowerCase();
					String batCodeOneLine_clearblank = batCodeOneLine.replace(" ", "");
					if ("".equals(batCodeOneLine)) {
						continue; // 去掉空行
					}
					if (batCodeOneLine.startsWith(":" + bat_method_name.toLowerCase())) {
						continue; // 去掉函数定义的 首行
					}

					if (batCodeOneLine.startsWith("rem ") && batCodeOneLine_clearblank.startsWith("remdesc:")) {
						continue; // 去掉函数定义rem:desc
					}

					if (batCodeOneLine.startsWith("rem ") && batCodeOneLine_clearblank.startsWith("remrule_tip:")) {
						continue; // 去掉函数定义rem:desc
					}

					if (batCodeOneLine.startsWith("echo ")
							&& batCodeOneLine.contains(method_In_PrintCode.toLowerCase().trim())) {
						continue; // 去掉函数定义 echo echo
									// ______________Method_In_searchOneTargetFile4Dir4Type_func_2x1 这样的字符串 进入函数字符串
					}

					if (batCodeOneLine.startsWith("echo ")
							&& batCodeOneLine.contains(method_Out_PrintCode.toLowerCase().trim())) {
						continue; // 去掉函数定义 echo ______________Method_Out_searchOneTargetFile4Dir4Type_func_2x1:
					}

					if (batCodeOneLine.startsWith("rem ") && batCodeOneLine_clearblank.startsWith("remsample:")) {
						continue; // 去掉函数定义rem:desc
					}
					if (batCodeOneLine.startsWith("rem ") && batCodeOneLine_clearblank.startsWith("remsample_out:")) {
						continue; // 去掉函数定义rem:desc
					}

					// 去除掉原始的 EndPrintCode
					if (batCodeOneLine.toLowerCase().contains("EndPrintCode".toLowerCase())) {
						continue; // 去掉函数分隔符 rem ======================== helloworld_func_0x0
					}

					if (batCodeOneLine.toLowerCase().contains("Method_In".toLowerCase())) {
						continue; // 去掉函数分隔符 rem ======================== helloworld_func_0x0
					}
					if (batCodeOneLine.toLowerCase().contains("Method_Out".toLowerCase())) {
						continue; // 去掉函数分隔符 rem ======================== helloworld_func_0x0
					}

					if (batCodeOneLine.toLowerCase().contains(bat_method_name_nofunc.toLowerCase() + "_dynamic_param")
							&& batCodeOneLine.trim().endsWith("=")) {
// set rule3vadbscreen_dynamic_param1=  【删除】
//set rule3vadbscreen_dynamic_param1=1000 不删除
						continue; // 去掉函数分隔符 set rule1vbankupapk_dynamic_param
					}

					// 去除掉原始的 EndPrintCode
					if (batCodeOneLine.toLowerCase().startsWith("rem =====".toLowerCase())) {
						continue; // 去掉函数分隔符 rem ======================== helloworld_func_0x0
					}

					if (batCodeOneLine.startsWith("rem ")
							&& batCodeOneLine_clearblank.contains(bat_method_name_nofunc)) {
						continue; // 去掉函数分隔符 rem ======================== helloworld_func_0x0
					}

					if (batCodeOneLine.startsWith("::setlocal") || batCodeOneLine.startsWith("::endlocal")) {
						continue; // 去掉::SETLOCAL 和 ::ENDLOCAL
					}

					if (batCodeOneLine.startsWith("echo ") && isContainChinese(batCodeOneLine)) { // 如果当前包含中文的 echo 开头的
//                       method_firstfixed_content.add(""); // 在中文前面 添加一个空格  空行 使得 输出不乱码	  
						method_firstfixed_content.add(rawContent);
					} else {
						method_firstfixed_content.add(rawContent);
					}

				}
				showStringList(method_firstfixed_content, "清头清空清set的函数内容 bat_method_name=" + bat_method_name);

//           echo recordFileNameToFile_return_1=[%recordFileNameToFile_return_1%]  param1=[%1]  
//    		 goto:eof
//    		 rem ======================== searchLastFile_func_1x1
//    		 rem 检测当前目录下 时间最新的那么文件 
//    		 rem searchLastFile_func_1x1 接受一个路径参数  给出该路径下 最新的那个 实体文件名称   函数的返回值 一致 定义为 函数名_return 
//    		 rem searchLastFile_return=K3_MD_Rule.class

				// 检测在 ArrayList 列表中是否有以 goto:eof 为内容的Item
				boolean isContain_GoTo_Eof = isStartWith_lower_trim_InArr(method_firstfixed_content, "goto:eof");

				// 如果 没有 包含 goto:eof 那么也就 不用去除那最后的字符串了
				if (method_firstfixed_content.size() > 0 && isContain_GoTo_Eof) {
					String lastCodeStr = method_firstfixed_content.get(method_firstfixed_content.size() - 1)
							.toLowerCase().trim();
					// 确保当前最后一行 是 goto:eof
					while (!lastCodeStr.startsWith("goto:eof")) {
						method_firstfixed_content.remove(method_firstfixed_content.size() - 1);
						if (method_firstfixed_content.size() == 0) {
							break;
						}
						lastCodeStr = method_firstfixed_content.get(method_firstfixed_content.size() - 1).toLowerCase()
								.trim();
					}

					// 确保删除了 这 最后一行的 goto:eof , 如果下一行 还是 goto:eof 继续删除它
					lastCodeStr = method_firstfixed_content.get(method_firstfixed_content.size() - 1).toLowerCase()
							.trim();
					while (lastCodeStr.startsWith("goto:eof") || "".equals(lastCodeStr)) { // 如果当前的最后一行 是 goto:eof
																							// 那么删除该行
						method_firstfixed_content.remove(method_firstfixed_content.size() - 1);
						if (method_firstfixed_content.size() == 0) {
							break;
						}
						lastCodeStr = method_firstfixed_content.get(method_firstfixed_content.size() - 1).toLowerCase()
								.trim();
					}
				}

				showStringList(method_firstfixed_content,
						"清头清空清set的函数内容并且以goto:eof结尾的函数  bat_method_name=" + bat_method_name);

				String lastOneCode = "";
				if (method_firstfixed_content.size() - 1 < 0) {
					lastOneCode = "rem ";
				} else {
					lastOneCode = method_firstfixed_content.get(method_firstfixed_content.size() - 1);
				}
				if (!method_firstfixed_content.contains(endPrintCode)) { // 如果最后打印有 不包含 endprintCode 那么假如 endprintCode
//    				method_firstfixed_content.add(endPrintCode);
					System.out.println("最后一句代码就是 不是EndprintCode  需要加入！！");

				} else {
					System.out.println("最后一句代码就是 EndprintCode  不加入！！");
				}

				showStringList(method_firstfixed_content,
						"清头清空清set的函数内容并且以goto:eof结尾的函数 添加EndPrintCode的函数  bat_method_name=" + bat_method_name);

			}

			void initRemDesc_Sample_SampleOut_RuleTip() {

				for (int i = 0; i < method_raw_content.size(); i++) {
					String oneMethodStr = method_raw_content.get(i);
					// 把空格转为 空 导致 注释 时 必须存在 空格消失了 挤在一块
					String clearBlank = oneMethodStr.replace(" ", "");
//    				String clearBlank = oneMethodStr.trim();
					if (clearBlank.startsWith("remdesc:") && oneMethodStr.startsWith("rem ")) {
						String descStr = oneMethodStr.replace("rem desc:", "");
						descStr = descStr.replace("rem  desc:", "");
						descStr = descStr.replace("rem   desc:", "");
						descStr = descStr.replace("rem   desc:", "");
						if (!"".equals(descStr.trim())) {
							method_desc_list.add(descStr.trim());
						}

					}
				}
				showStringList(method_desc_list, "方法说明");

				if (isRuleMethod) {
					for (int i = 0; i < method_raw_content.size(); i++) {
						String oneMethodStr = method_raw_content.get(i);
						// 把 所有的
// rem rule_tip: zbatrule_I9_Rule30.bat _3_  500 ##手机执行adbshellinputswipe340800340100命令向下滑动两下向上滑动一下

						String clearBlank = oneMethodStr.replace(" ", "");
						if (clearBlank.startsWith("remrule_tip:") && oneMethodStr.startsWith("rem ")) {

							if (oneMethodStr.startsWith("rem ")) {
								oneMethodStr = oneMethodStr.substring(4);
							}
							String descStr = oneMethodStr.replace("remrule_tip:", "");
							descStr = descStr.replace("rem  rule_tip:", "");
							descStr = descStr.replace("rem   rule_tip:", "");
							descStr = descStr.replace("rule_tip:", "");
							descStr = descStr.replace("rule_tip:", "");
							// rem rule_tip:
							if (!"".equals(descStr.trim())) {
								method_ruletip_list.add(descStr.trim());
							}

						}
					}
					showStringList(method_ruletip_list, "ruleTip说明");

				}

				for (int i = 0; i < method_raw_content.size(); i++) {
					String oneMethodStr = method_raw_content.get(i);
					String clearBlank = oneMethodStr.replace(" ", "");
					if (clearBlank.startsWith("remsample:") && oneMethodStr.startsWith("rem ")) {
						String descStr = oneMethodStr.replace("remsample:", "");
						descStr = descStr.replace("rem sample:", "");
						descStr = descStr.replace("rem  sample:", "");
						descStr = descStr.replace("rem   sample:", "");
						descStr = descStr.replace("sample:", "");

						if (!"".equals(descStr.trim())) {
							method_Sample_list.add(descStr.trim());
						}

					}
				}
				showStringList(method_Sample_list, "方法实例");

				for (int i = 0; i < method_raw_content.size(); i++) {
					String oneMethodStr = method_raw_content.get(i);
					String clearBlank = oneMethodStr.replace(" ", "");
					if (clearBlank.startsWith("remsample_out:") && oneMethodStr.startsWith("rem ")) {
						String descStr = oneMethodStr.replace("remsample_out:", "");
						descStr = descStr.replace("rem sample_out:", "");
						descStr = descStr.replace("rem  sample_out:", "");
						descStr = descStr.replace("rem   sample_out:", "");
						descStr = descStr.replace("sample_out:", "");

						if (!"".equals(descStr.trim())) {
							method_SampleOut_list.add(descStr.trim());
						}

					}
				}
				showStringList(method_SampleOut_list, "方法实例输出");

			}

			void clearAllData() {
				method_raw_content.clear();
				method_firstfixed_content.clear();
				method_desc_list.clear();
				method_Sample_list.clear();
				method_SampleOut_list.clear();
				method_format_content.clear();
			}

			void doFormat() {
				// 1. 获取 所有 rem desc: 开头的字符串
				// 2. 检测 rawMethod中 最后一行是否是 goto:eof
				// 如果不是 2.1如果否代码 不是 rem开头的话 那么直接删除
				// 2.2如果是代码 那么手动 添加 goto:eof
				// 3. 检测 倒数第二行 是否是 ::ENDLOCAL
				// 3.1 检测当前rawMethod是否包含 ::ENDLOCAL 如果不包含 那么添加 如果包含 那么删除原有的 ::ENDLOCAL 再添加到这里
				// 4. 把 自由发挥空间 过滤出来 导出来到 format_list 中
				// zzzzzzzzzzz

				if (method_raw_content.size() == 0) {
					System.out.println("当前 method_raw_content 为空, 无法执行 Method的 format 方法!");
					return;
				}
				initRemDesc_Sample_SampleOut_RuleTip();
				initFirstFixedConent();
				InitFormatContent(); // 开始拼凑 formatList 了

			}

			// 检测 Method 结束 遇到新的 Method的标识符就意味着结束
			// 检测 Method 开始
			boolean isMethodEnd(String oneLine) {
				boolean isMethodEndLine = false;
				String oneLine_trim = oneLine.toLowerCase().trim();
				// 必须以: 开头 并且 不以:: 开头
				if (!oneLine_trim.startsWith(":") || oneLine_trim.startsWith("::")) {
					System.out.println("ZZZZBBBB_isMethodEnd  beginTagChar =:" + oneLine_trim + "isMethodEndLine = "
							+ isMethodEndLine);

					return isMethodEndLine;
				}

				for (int i = 0; i < method_beginTagCharList.size(); i++) {
					String beginTagChar = method_beginTagCharList.get(i);
					if (!oneLine_trim.contains(beginTagChar)) {
//          					System.out.println(" beginTagChar = "+ beginTagChar + " 不包含在:"+oneLine_trim);
						System.out.println("ZZZZAAA_isMethodEnd  beginTagChar =:" + oneLine_trim + "isMethodEndLine = "
								+ isMethodEndLine);

						return isMethodEndLine;
					}

				}

				System.out.println("ZZZZAAA_isMethodEnd  oneLine_trim=" + oneLine_trim + "    oneLine=" + oneLine);
				// :searchlastfile_func_1x1
				if (oneLine_trim.startsWith(":") && !oneLine_trim.startsWith("::") && oneLine_trim.contains("_func_")
						&& oneLine_trim.contains("x") && !oneLine_trim.contains(" ")) { // 以: 开头 并不以 :: 开头

					String[] splitArr = oneLine.split("_");
					if (splitArr != null) {
						String lastPartStr = splitArr[splitArr.length - 1];
						// :helloworld_func_0x0
						String firstPartStr = splitArr[0].toLowerCase().trim();

						String inputParamStr = lastPartStr.substring(0, lastPartStr.indexOf("x"));
						String outputParamStr = lastPartStr.substring(lastPartStr.indexOf("x") + "x".length());
						// 如果检测到一个新的 方法 这个方法的名字 和 当前的名字 不一样 那么就意味着 这个函数定义的结束

						// ZZZZAAA_isMethodEnd oneLine_trim=:string_test_func_0x0
						// oneLine=:string_test_func_0x0
						// ZZZZ1111_isMethodEnd outputParamStr = 0isNumeric(outputParamStr)=true
						// inputParamStr =0 isNumeric(inputParamStr)=true
						// !firstPartStr.contains(bat_method_name_nofunc)=true beginTagChar
						// =::string_test_func_0x0 isMethodEndLine = false firstPartStr = :string
						// bat_method_name_nofunc=string_test
						// ZZZZ2222_isMethodEnd beginTagChar =:string_test_func_0x0 isMethodEndLine =
						// true firstPartStr = :string bat_method_name_nofunc=string_test
						// ZZZZ5555__isMethodEnd beginTagChar =:string_test_func_0x0 isMethodEndLine =
						// true

						System.out.println("ZZZZ1111_isMethodEnd  outputParamStr = " + outputParamStr
								+ "isNumeric(outputParamStr)=" + isNumeric(outputParamStr) + "  inputParamStr ="
								+ inputParamStr + "  isNumeric(inputParamStr)=" + isNumeric(inputParamStr)
								+ " !firstPartStr.contains(bat_method_name_nofunc)="
								+ !firstPartStr.contains(bat_method_name_nofunc) + "  beginTagChar =:" + oneLine_trim
								+ "   isMethodEndLine = " + isMethodEndLine + "  firstPartStr = " + firstPartStr
								+ "   bat_method_name_nofunc=" + bat_method_name_nofunc);

						if (isNumeric(outputParamStr.trim()) && isNumeric(inputParamStr.trim())
								&& !firstPartStr.contains(bat_method_name_nofunc)) {

							isMethodEndLine = true;
							System.out.println("ZZZZ2222_isMethodEnd  beginTagChar =" + oneLine_trim
									+ "   isMethodEndLine = " + isMethodEndLine + "  firstPartStr = " + firstPartStr
									+ "   bat_method_name_nofunc=" + bat_method_name_nofunc);

						} else {
							System.out.println("ZZZZ3333_isMethodEnd  beginTagChar =" + oneLine_trim
									+ "   isMethodEndLine = " + isMethodEndLine + "  firstPartStr = " + firstPartStr
									+ "   bat_method_name_nofunc=" + bat_method_name_nofunc);

						}
					}

				} else {
					System.out.println("ZZZZ4444__isMethodEnd  beginTagChar =" + oneLine_trim + "   isMethodEndLine = "
							+ isMethodEndLine);

				}
				System.out.println("ZZZZ5555__isMethodEnd  beginTagChar =" + oneLine_trim + "isMethodEndLine = "
						+ isMethodEndLine);

				return isMethodEndLine;

			}

			// 检测 Method 开始
			boolean isMethodBegin(String oneLine) {
				boolean isMethodBeginLine = false;
				String oneLine_trim = oneLine.toLowerCase().trim();
				// 必须以: 开头 并且 不以:: 开头
				if (!oneLine_trim.startsWith(":") || oneLine_trim.startsWith("::")) {

					System.out.println("ZZZZZ0000_isMethodBegin   oneLine_trim=" + oneLine_trim + "    bat_method_name="
							+ bat_method_name);

					return isMethodBeginLine;
				}

				for (int i = 0; i < method_beginTagCharList.size(); i++) {
					String beginTagChar = method_beginTagCharList.get(i);
					if (!oneLine_trim.contains(beginTagChar)) {
//    					System.out.println(" beginTagChar = "+ beginTagChar + " 不包含在:"+oneLine_trim);
						System.out.println("ZZZZZ1111_isMethodBegin   oneLine_trim=" + oneLine_trim
								+ "    bat_method_name=" + bat_method_name);

						return isMethodBeginLine;
					}
//    				method_beginTagCharList.add(methodName);
					if (!oneLine_trim.contains(bat_method_name)) { // 把 名字单独提出来
						System.out.println("ZZZZZ2222_isMethodBegin   oneLine_trim=" + oneLine_trim
								+ "    bat_method_name=" + bat_method_name + " isMethodBeginLine=" + isMethodBeginLine);

						return isMethodBeginLine;
					}
				}

				if (oneLine_trim.startsWith(":") && !oneLine_trim.startsWith("::") && oneLine_trim.contains("_func_")
						&& oneLine_trim.contains("x") && !oneLine_trim.contains(" ")) { // 以: 开头 并不以 :: 开头

					String[] splitArr = oneLine.split("_");
					if (splitArr != null) {
						String lastPartStr = splitArr[splitArr.length - 1];

						String inputParamStr = lastPartStr.substring(0, lastPartStr.indexOf("x"));
						String outputParamStr = lastPartStr.substring(lastPartStr.indexOf("x") + "x".length());
						if (isNumeric(outputParamStr.trim()) && isNumeric(inputParamStr.trim())) {
							System.out.println("ZZZZZ33333_isMethodBegin isMethodBeginLine = true   oneLine_trim="
									+ oneLine_trim + "    bat_method_name=" + bat_method_name + " isMethodBeginLine="
									+ isMethodBeginLine);
							isMethodBeginLine = true;
						}
					}

				} else {

					System.out.println("ZZZZZ333AAA_isMethodBegin   oneLine_trim=" + oneLine_trim
							+ "    bat_method_name=" + bat_method_name + " isMethodBeginLine=" + isMethodBeginLine);

				}
				System.out.println("ZZZZZ4444_isMethodBegin   oneLine_trim=" + oneLine_trim + "    bat_method_name="
						+ bat_method_name + " isMethodBeginLine=" + isMethodBeginLine);

				return isMethodBeginLine;

			}

			// getFileNameNoPointWithFullPath_return_1=[Z_TEMP]
			// param1=[C:\Users\zhuzj5\Desktop\zbin\Z_TEMP.txt]
			void buildEndPrintCode(String methodName, String methodName_nofunc, int inputParamCount,
					int outputParamCount, boolean isRuleMethod) {
				endPrintCode = "";
				String preCode = "echo [" + methodName + " EndPrintCode] ";
				if (inputParamCount == 0 && outputParamCount == 0) {
					// stringTrim_func_1x1 input_1_param ==[%1]
					endPrintCode = preCode + "  output=[__empty__]  param1=[__empty__] ";
					return;
				}

				// 没有输入参数 但有输出参数
				if (inputParamCount == 0 && outputParamCount > 0) {
					StringBuilder sb = new StringBuilder();
					for (int i = 1; i < outputParamCount + 1; i++) {
						String returnItem = methodName_nofunc + Method_Return_Tag + i;
						String currentCode = returnItem + "=[!" + returnItem + "!]" + "   ";
						sb.append(currentCode);
					}

					if (isRuleMethod) {
						sb.append("dynamic_param1=[__empty__] ");

					} else {
						sb.append("param1=[__empty__] ");
					}
					endPrintCode = preCode + sb.toString();
					return;
				}

				// 有输入参数 但有没输出参数
				if (inputParamCount > 0 && outputParamCount == 0) {
					StringBuilder sb = new StringBuilder();
					for (int i = 1; i < inputParamCount + 1; i++) {
						String paramItem = "param" + i;
						String currentCode = paramItem + "=[%" + i + "]" + "   ";

						if (isRuleMethod) {
							paramItem = "dynamic_param" + i;
							currentCode = paramItem + "=[!" + methodName_nofunc + "_dynamic_param" + i + "!]" + "   ";
						}
						sb.append(currentCode);
					}

					endPrintCode = preCode + "   output=[__empty__] " + sb.toString();
					return;
				}

				// 有输入参数 也有 输出参数
				if (inputParamCount > 0 && outputParamCount > 0) {
					StringBuilder sb = new StringBuilder();

					for (int i = 1; i < outputParamCount + 1; i++) {
						String returnItem = methodName_nofunc + Method_Return_Tag + i;
						String currentCode = returnItem + "=[!" + returnItem + "!]" + "   ";
						sb.append(currentCode);
					}

					for (int i = 1; i < inputParamCount + 1; i++) {
						String paramItem = "param" + i;
						String currentCode = paramItem + "=[%" + i + "]" + "   ";
						if (isRuleMethod) {
							paramItem = "dynamic_param" + i;
							currentCode = paramItem + "=[!" + methodName_nofunc + "_dynamic_param" + i + "!]" + "   ";
						}

						sb.append(currentCode);
					}

					endPrintCode = preCode + "  " + sb.toString();
					return;
				}

			}

			// getsubstringwithpre_func_2x1 得到输入的参数个数2 以及要输出的参数个数1
			void initParamsCount(String methodName) {
				String[] splitArr = methodName.split("_");
				if (splitArr != null) {
					String lastPartStr = splitArr[splitArr.length - 1];
					String inputParamStr = lastPartStr.substring(0, lastPartStr.indexOf("x"));
					String outputParamStr = lastPartStr.substring(lastPartStr.indexOf("x") + "x".length());

					if (isNumeric(inputParamStr)) {
						input_param_count = Integer.parseInt(inputParamStr);
					}

					if (isNumeric(outputParamStr)) {
						output_param_count = Integer.parseInt(outputParamStr);
					}
				}
			}

		}

		boolean isContainChinese(String str) {
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher m = p.matcher(str);
			if (m.find()) {
				return true;
			}
			return false;
		}

		ArrayList<String> Bat_To_Format(ArrayList<String> batContentList) {
//    		Bat_Aera system_init_aera = null;
//    		Bat_Aera program_execute_aera = null;
//    		Bat_Aera func_define_aera = null;

			// rem ══════════════════════════════════════════ System_Init_Aera_Begin
			// ══════════════════════════════════════════

			// 1. 含有 rem 并以它为开头 2. 含有 area标识 ══ _area_ 3. 含有当前area的名字
			Bat_Aera system_init_aera = new Bat_Aera(0, "system_init_aera");
			Bat_Aera program_execute_aera = new Bat_Aera(1, "program_execute_aera");
			Bat_Aera func_define_aera = new Bat_Aera(2, "func_define_aera", true);
			Bat_Aera main_enter_aera = new Bat_Aera(3, "main_enter_aera");

			mAreaList.add(system_init_aera);
			mAreaList.add(program_execute_aera);
			mAreaList.add(func_define_aera);
			mAreaList.add(main_enter_aera);
			initRawContentInArea(batContentList, mAreaList);

			ArrayList<String> formatAllBatCodeList = buildAllBatFormatContent(mAreaList);

			showStringList(allRuleTipStrList, "RuleTip列表");
			Bat_Method rulePrintMethod = getMethodByName("ruletipprint");
			build_rulePrintMethod(rulePrintMethod, allRuleTipStrList); // 重绘 rawContent

			formatAllBatCodeList = buildAllBatFormatContent(mAreaList);

			return formatAllBatCodeList;
//    			
		}

		void build_rulePrintMethod(Bat_Method rulePrintMethod, ArrayList<String> tipList) {

			ArrayList<String> newRulePrintMethodRaw = new ArrayList<String>();
			newRulePrintMethodRaw.add(":" + rulePrintMethod.bat_method_name);
			newRulePrintMethodRaw.add("rem desc: Bussiness_Rule打印程序用于打印batrule规则序列");

//    		newRulePrintMethodRaw.addAll(tipList);

			for (int i = 0; i < tipList.size(); i++) {
				String tipStr = tipList.get(i);
				if (isContainChinese(tipStr)) {
//    				newRulePrintMethodRaw.add("\n");
					newRulePrintMethodRaw.add("echo " + tipList.get(i));
				} else {
					newRulePrintMethodRaw.add("echo " + tipList.get(i));
				}

			}

			newRulePrintMethodRaw.add("goto:eof");
			rulePrintMethod.clearAllData();
			rulePrintMethod.method_raw_content = newRulePrintMethodRaw;
			showStringList(rulePrintMethod.method_raw_content, rulePrintMethod.bat_method_name + "新的rawContent");

			rulePrintMethod.doFormat();
		}

		ArrayList<String> buildAllBatFormatContent(ArrayList<Bat_Aera> areaList) {
			ArrayList<String> allFormatList = new ArrayList<String>();
			if (areaList == null || areaList.size() == 0) {
				System.out.println("当前解析到的 ArrayList<Bat_Aera> 为空 无法执行format bat 代码的操作!");
				return null;
			}

			allFormatList.add(batHead_1); // @ECHO off
			allFormatList.add(batHead_2); // setlocal enabledelayedexpansion
			allFormatList.add(batHead_3); // chcp 65001
			for (int i = 0; i < areaList.size(); i++) {
				Bat_Aera batArea_Item = areaList.get(i);
				ArrayList<String> AreaContent = batArea_Item.getAreaFormatContent();
				allFormatList.addAll(AreaContent);
			}

			return allFormatList;

		}

		void showStringList(ArrayList<String> strList) {

			if (strList == null || strList.size() == 0) {
				System.out.println("当前调用 showStringList 显示的  ArrayList<String>() 字符串数组为空!! ");
				return;
			}

			int line_num = 0;
			System.out.println(
					"════════════════════ ArrayList<String>  " + strList.size() + " 行字符串" + "════════════════════ ");
			for (int i = 0; i < strList.size(); i++) {
				line_num++;
				String oneStr = strList.get(i);
				System.out.println("Line[" + line_num + "]   " + oneStr);

			}
			System.out.println();
		}

		void showStringList_WithHead(ArrayList<String> strList, String tag) {

			if (strList == null || strList.size() == 0) {
				System.out.println("当前调用 showStringList 显示的  ArrayList<String>() 字符串数组为空!! ");
				return;
			}

			int line_num = 0;
			System.out.println("════════════════════ showStringList_WithHead  ArrayList<String>  " + strList.size()
					+ " 行字符串 " + tag + " begin " + "════════════════════ ");
			System.out.println("ZZZZZZZZZZZZZZZZZZZZ");
			for (int i = 0; i < strList.size(); i++) {
				line_num++;
				String oneStr = strList.get(i);
				System.out.println(oneStr);

			}
			System.out.println("ZZZZZZZZZZZZZZZZZZZZ");

			System.out.println("════════════════════ showStringList_WithHead  ArrayList<String>  " + strList.size()
					+ " 行字符串 " + tag + " end " + "════════════════════ ");

			System.out.println();
		}

		void showStringList(ArrayList<String> strList, String tag) {

			if (strList == null || strList.size() == 0) {
				System.out.println("当前调用 showStringList 显示的  ArrayList<String>() 字符串数组为空!! ");
				return;
			}

			int line_num = 0;
			System.out.println(
					"════════════════════ ArrayList<String>  " + strList.size() + " 行字符串" + "════════════════════ ");
			for (int i = 0; i < strList.size(); i++) {
				line_num++;
				String oneStr = strList.get(i);
				System.out.println("tag=[" + tag + "] Line[" + line_num + "]   " + oneStr);

			}
			System.out.println();
		}

		public boolean isNumeric(String string) {
			String str = string.trim();
			for (int i = str.length(); --i >= 0;) {
				if (!Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
			return true;
		}

		void initRawContentInArea(ArrayList<String> batContentList, ArrayList<Bat_Aera> areaList) {

			for (int i = 0; i < areaList.size(); i++) {
				Bat_Aera curArea = areaList.get(i);
				ArrayList<String> area_raw_contentList = new ArrayList<String>();

				for (int j = 0; j < batContentList.size(); j++) {
					String oneLineStr = batContentList.get(j);
					isAreaBegin = curArea.isAreaBegin(oneLineStr);
					if (isAreaBegin) {
						isAreaRawBegin = true;
					}
					isAreaEnd = curArea.isAreaEnd(oneLineStr);
					if (isAreaEnd) {
						isAreaRawBegin = false;
						area_raw_contentList.add(oneLineStr);
					}

					if (isAreaRawBegin) {
//    				System.out.println("isAreaBegin="+ isAreaBegin + "   isAreaEnd="+ isAreaEnd + "  isRawBegin="+ isRawBegin);
						area_raw_contentList.add(oneLineStr);
					}

//    			System.out.println("index["+j+"] "+"isAreaBegin="+ isAreaBegin + "   isAreaEnd="+ isAreaEnd + "  isRawBegin="+ isRawBegin);

				}
				curArea.aera_raw_content.addAll(area_raw_contentList);
				isAreaBegin = false;
				isAreaEnd = false;
				isAreaRawBegin = false;
				System.out.println(curArea.aera_name + " 的 raw列表如下");
				showStringList(curArea.aera_raw_content, curArea.aera_name);

				curArea.initDataWithRawContent(area_raw_contentList);
			}

		}

		Bat_Method getMethodByName(String methodName) {
			Bat_Method selectedMethod = null;

			for (int i = 0; i < allMethodList.size(); i++) {
				Bat_Method currentMethod = allMethodList.get(i);
				if (currentMethod.bat_method_name_nofunc.trim().toLowerCase().equals(methodName.toLowerCase().trim())) {
					return currentMethod;
				}
			}
			return selectedMethod;
		}

		volatile boolean isAreaBegin = false;
		volatile boolean isAreaEnd = false;
		volatile boolean isAreaRawBegin = false;

		volatile boolean isOperationBegin = false;
		volatile boolean isOperationEnd = false;
		volatile boolean isOperationRawBegin = false;

		volatile boolean isMethodBegin = false;
		volatile boolean isMethodEnd = false;
		volatile boolean isMethodRawBegin = false;

	}

//  把当前模板文件 zbatrule_I9_Rule30.bat 内容写进当前目录下 Test_20200201_xxx(时间戳).bat 文档 并打开它
	class Show_Bat_Template_OnDir_Rule_30 extends Basic_Rule {
		File bat_template_file;

		Show_Bat_Template_OnDir_Rule_30() {
			super(30, false);
			bat_template_file = new File(
					zbinPath + File.separator + "win_zbin" + File.separator + "zbatrule_I9_Rule30.bat");
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			ArrayList<String> result_list = new ArrayList<String>();
			if (!bat_template_file.exists()) {
				result_list.add("当前 模板文件 " + bat_template_file.getAbsolutePath() + " 不存在 请检查该文件！");
				System.out.println("失败 无法 读取 zbatrule_I9_Rule30.bat 模板文件! " + bat_template_file.getAbsolutePath());
			} else {
				ArrayList<String> contentList = ReadFileContentAsList(bat_template_file);

				for (int i = 0; i < contentList.size(); i++) {
					String oneLine = contentList.get(i);
					/*
					 * // bat 文件 不需要在文件中的名字 没有名字
					 * if(oneLine.contains("public class I9_TestJavaTemplate_Rule29")) {
					 * 
					 * oneLine = oneLine.replace("public class I9_TestJavaTemplate_Rule29",
					 * "public class Test_"+getTimeStampMMdd()); }
					 */
					result_list.add(oneLine);
				}
				System.out.println("成功读取 I9_TextRuleOperation  Show_Bat_Template_OnDir_Rule_30.java "
						+ bat_template_file.getAbsolutePath() + " 模板文件! ");

				// public class I9_TestJavaTemplate_Rule29 把这个文件改名为 Test_0510 这样的日期
			}

			// 找到 开始解析 .bat 文件
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				String curBatName = "test_" + getTimeStampLong() + ".bat";
				File curBatFile = new File(fileItem.getParentFile().getAbsolutePath() + File.separator + curBatName);
				result_list.add("rem ___CMD_Commond__  cmd /k " + curBatFile.getAbsolutePath());
				writeContentToFile(curBatFile, result_list);
				NotePadOpenTargetFile(curBatFile.getAbsolutePath());

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把当前模板文件 zbatrule_I9_Rule30.bat 内容写进当前目录下 Test_20200201_xxx(时间戳).bat 文档 并打开它  ";
		}

	}

	class Bat_Revert_MD_Rule29 extends Basic_Rule {
		Bat_Format_Rule_31 revertTool;
		File batTemplateFile; // bat 模板文件

		Bat_Revert_MD_Rule29() {
			super(29, false);
			revertTool = new Bat_Format_Rule_31();
			batTemplateFile = new File(
					zbinPath + File.separator + "win_zbin" + File.separator + "zbatrule_I9_Rule30.bat");

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			ArrayList<String> MDAllContentList = new ArrayList<String>();

			ArrayList<String> MDHeadRawList = new ArrayList<String>();
			MDHeadRawList.add("---");
			MDHeadRawList.add("layout: post");
			MDHeadRawList.add("title: Bat技巧记载");
			MDHeadRawList.add("category: 代码");
			MDHeadRawList.add("tags: Bat");
			MDHeadRawList.add("keywords: Code Bat");
			MDHeadRawList.add("typora-root-url: ..\\..\\..\\");
			MDHeadRawList.add("typora-copy-images-to: ..\\..\\..\\public\\zimage");
			MDHeadRawList.add("");
			MDHeadRawList.add("---");
			MDHeadRawList.add("");
			MDHeadRawList.add("## 简介                                           ");
			MDHeadRawList.add(" * TOC                                            ");
			MDHeadRawList.add(" {:toc}                                           ");
			MDHeadRawList.add("");

			ArrayList<String> MDBodyRawList = new ArrayList<String>();

			ArrayList<String> RawContentList = ReadFileContentAsList(batTemplateFile);
			// 解析 模板 文件 生成 format 格式 并 尝试 解析为 MD文件

			// 解析好格式的 formatList 作为源码 保存在 最后
			ArrayList<String> formatRawBatList = revertTool.Bat_To_Format(RawContentList);
			ArrayList<Bat_Format_Rule_31.Bat_Aera> batAreaList = revertTool.mAreaList;

			for (int i = 0; i < batAreaList.size(); i++) {
				Bat_Format_Rule_31.Bat_Aera batAreaItem = batAreaList.get(i);
				String areName = batAreaItem.aera_name;
				ArrayList<String> areaRawConent = batAreaItem.getAreaFormatContent();
				MDBodyRawList.add("");
				MDBodyRawList.add("## " + areName);
				MDBodyRawList.add("");
				if (batAreaItem.defineOperationList == null || batAreaItem.defineOperationList.size() == 0) {
					MDBodyRawList.add("");
					MDBodyRawList.add("**代码区域**");
					MDBodyRawList.add("```");
					MDBodyRawList.addAll(areaRawConent);
					MDBodyRawList.add("```");
					MDBodyRawList.add("");
					continue;
				}

				for (int j = 0; j < batAreaItem.defineOperationList.size(); j++) {
					Bat_Format_Rule_31.Bat_Operation curOperation = batAreaItem.defineOperationList.get(j);

					String operationName = curOperation.operation_name;
					MDBodyRawList.add("");
					MDBodyRawList.add("### " + operationName);
					MDBodyRawList.add("");

					for (int k = 0; k < curOperation.operation_MethodList.size(); k++) {

						Bat_Format_Rule_31.Bat_Method methodItem = curOperation.operation_MethodList.get(k);

						String methodName = methodItem.bat_method_name;
						MDBodyRawList.add("");
						MDBodyRawList.add("");
						MDBodyRawList.add("#### " + methodName);

						ArrayList<String> methodRuleTip = methodItem.method_ruletip_list;
						ArrayList<String> methodDescList = methodItem.method_desc_list;
						ArrayList<String> methodFormatContentList = methodItem.method_format_content;
						ArrayList<String> methodSampleList = methodItem.method_Sample_list;
						ArrayList<String> methodSampleOutList = methodItem.method_SampleOut_list;
						addListToMDList(MDBodyRawList, methodRuleTip, "提示");
						addListToMDList(MDBodyRawList, methodDescList, "描述");
						addListToMDList(MDBodyRawList, methodSampleList, "代码实例");
						addListToMDList(MDBodyRawList, methodSampleOutList, "实例输出");
						addCodeListToMDList(MDBodyRawList, methodFormatContentList, "代码区域");

					}

				}
			}

			MDBodyRawList.add("");
			MDBodyRawList.add("## " + "bat代码环境");
			MDBodyRawList.add("");
			MDBodyRawList.add("```");
			MDBodyRawList.addAll(formatRawBatList);
			MDBodyRawList.add("```");
			MDBodyRawList.add("");

			MDAllContentList.addAll(MDHeadRawList);
			MDAllContentList.addAll(MDBodyRawList);

			writeContentToFile(I9_Temp_Text_File, MDAllContentList);
			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			/*
			 * for (int i = 0; i < curInputFileList.size(); i++) { File fileItem =
			 * curInputFileList.get(i); ArrayList<String> contentList =
			 * ReadFileContentAsList(fileItem); }
			 */
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void addListToMDList(ArrayList<String> originList, ArrayList<String> addList, String tag) {

			if (addList.size() > 0 && originList.size() > 0) {
				originList.add("");
				originList.add("**" + tag + "**");
				originList.add("");
				originList.add("```");

				for (int i = 0; i < addList.size(); i++) {
					String addItemStr = addList.get(i);
					String fixedItem = addItemStr.replace("rem rule_tip:", "");
					fixedItem = fixedItem.replace("rem desc:", "");
					fixedItem = fixedItem.replace("rem sample:", "");
					fixedItem = fixedItem.replace("rem sample_out:", "");
					originList.add(fixedItem);
				}
				originList.add("```");
			}
			originList.add("");

		}

		void addCodeListToMDList(ArrayList<String> originList, ArrayList<String> addList, String tag) {

			if (addList.size() > 0 && originList.size() > 0) {

				originList.add("**" + tag + "**");
				originList.add("");
				originList.add("```");

				for (int i = 0; i < addList.size(); i++) {
					String addItemStr = addList.get(i);

					originList.add(addItemStr);
				}
				originList.add("```");
			}
			originList.add("");

		}

		@Override
		String simpleDesc() {
			return " 读取当前指定模板文件 win_zbin/zbatrule_I9_Rule30.bat 生成解析的MD文件 ";
		}

	}

//  生成java Test模板文件 读取 Java模板文件(包含初始化模块)  然后在notepad++打开它 
	class Show_JavaTest_File_Rule_28 extends Basic_Rule {
		File java_template_file;

		Show_JavaTest_File_Rule_28() {
			super(28, false);
			java_template_file = new File(zbinPath + File.separator + "I9_TestJavaTemplate_Rule29.java");

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			ArrayList<String> result_list = new ArrayList<String>();
			String Test_Java_FileName = "Test_" + getTimeStampMMdd() + "_" + getTimeStampHHmmss();
			String public_class_declare = "public class " + Test_Java_FileName;
			if (!java_template_file.exists()) {
				result_list.add("当前 模板文件 " + java_template_file.getAbsolutePath() + " 不存在 请检查该文件！");
				System.out.println(
						"失败 无法 读取 I9_TestJavaTemplate_Rule29.java 模板文件! " + java_template_file.getAbsolutePath());
			} else {
				ArrayList<String> contentList = ReadFileContentAsList(java_template_file);

				for (int i = 0; i < contentList.size(); i++) {
					String oneLine = contentList.get(i);
					if (oneLine.contains("public class I9_TestJavaTemplate_Rule29")) {

						oneLine = oneLine.replace("public class I9_TestJavaTemplate_Rule29", public_class_declare);
					}
					result_list.add(oneLine);
				}
				System.out.println("成功读取 I9_TestJavaTemplate_Rule29.java 模板文件! ");

				// public class I9_TestJavaTemplate_Rule29 把这个文件改名为 Test_0510 这样的日期
			}

			// 开始复制到 本地 java 文件
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				String java_end_file = Test_Java_FileName + ".java";
				String class_end_file = Test_Java_FileName + ".class";
				File javaFile = new File(fileItem.getParentFile().getAbsolutePath() + File.separator + java_end_file);
				String classAbsPath = fileItem.getParentFile().getAbsolutePath() + File.separator + class_end_file;
				String parentPath = fileItem.getParentFile().getAbsolutePath();
				/*
				 * Run绝对路径: javac -encoding UTF-8 D:\Test\Test_0514_160618.java && java -cp
				 * .;D:\Test Test_0514_160618 Run相对路径: javac -encoding UTF-8
				 * Test_0514_160618.java && java -cp .; Test_0514_160618 notepad++: 执行快捷键 alt+j
				 * notepad运行java命令: javac -encoding UTF-8 $(FULL_CURRENT_PATH) && java
				 * .;$(CURRENT_DIRECTORY) $(NAME_PART)
				 */

				result_list.add("/*");
				result_list.add("cmder_Run绝对路径:");
				result_list.add("javac  -encoding UTF-8 " + javaFile.getAbsolutePath() + " && " + " java -cp .;"
						+ parentPath + " " + Test_Java_FileName);
				result_list.add("cmder_Run相对路径:");
				result_list
						.add("javac -encoding UTF-8 " + java_end_file + " && java -cp .;" + " " + Test_Java_FileName);
				result_list.add("notepad++: 执行快捷键  alt+j");
				result_list.add("notepad运行java命令:");
				result_list.add(
						"cmd /K cd /d $(CURRENT_DIRECTORY) && javac  -encoding UTF-8 $(FULL_CURRENT_PATH) && java -cp .;$(CURRENT_DIRECTORY) $(NAME_PART)");
				result_list.add("*/");
				writeContentToFile(javaFile, result_list);
				NotePadOpenTargetFile(javaFile.getAbsolutePath());

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return "  生成java Test模板文件 读取 Java模板文件(包含初始化模块)  然后在notepad++打开它  ";
		}

	}

	// 把当前文件内容以 ZZZZZZZZZZZZZZZZZZZZZ 分割 专门生成剪切内容保存到零时txt文件
	class Copy_Port_WithZ_Rule27 extends Basic_Rule {

		int begin_Z_lineNum = 0;
		int end_Z_lineNum = 0;

		Copy_Port_WithZ_Rule27() {
			super(27, false);
			begin_Z_lineNum = 0;
			end_Z_lineNum = 0;
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);

				// 找到 起始为ZZZZZ 开头的 行数

				for (int j = 0; j < contentList.size(); j++) {
					String lineContent = contentList.get(j);
					if (lineContent.startsWith("Z") || lineContent.startsWith("z")) {

						if (lineContent.startsWith("z") || lineContent.startsWith("Z")) {

							String clearZ_Str = lineContent.replace("z", "").replace("Z", "").trim();
							if (lineContent.length() >= 5 && "".equals(clearZ_Str)) {
								if (begin_Z_lineNum == 0 && end_Z_lineNum == 0) {
									begin_Z_lineNum = j;
									continue;
								}
								if (begin_Z_lineNum != 0 && end_Z_lineNum == 0) {
									end_Z_lineNum = j;
									if (begin_Z_lineNum > 0 && end_Z_lineNum > 0 && begin_Z_lineNum < end_Z_lineNum) {
										break;
									}
								}
							}
						}
					}
				}
				System.out.println("begin_Z_lineNum = " + begin_Z_lineNum + "  end_Z_lineNum = " + end_Z_lineNum
						+ "   contentList.size() =" + contentList.size());
				if (begin_Z_lineNum > 0 && end_Z_lineNum > 0 && begin_Z_lineNum < end_Z_lineNum
						&& end_Z_lineNum <= contentList.size()) {
					ArrayList<String> fixedStrArr = new ArrayList<String>();
					for (int j = begin_Z_lineNum; j <= end_Z_lineNum; j++) {
						fixedStrArr.add(contentList.get(j));
					}
					if (fixedStrArr.size() >= 2) {
						fixedStrArr.remove(0);
						fixedStrArr.remove(fixedStrArr.size() - 1);
					}
					writeContentToFile(I9_Temp_Text_File, fixedStrArr);
					NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
					System.out.println("rule_" + rule_index + " -> 已经截取了 ZZZZZZ之间的【" + (end_Z_lineNum - begin_Z_lineNum)
							+ "行】内容到 TEMP TXT 文件");

				}

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 读取当前文件的内容 并寻找上下以ZZZZZZZ标记的行, 复制两行ZZZZ标记的之间的内容到零时txt文件 ";
		}

	}

	// 读取当前文件的内容 并在当前文件夹下内寻找该文件 复制到输入文件名称_时间戳目录
	class Fliter_Copy_File_WithName_Rule_26 extends Basic_Rule {

		Fliter_Copy_File_WithName_Rule_26(boolean mIsInputDirAsSearchPoint) {
			super(26);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;

		}

		Fliter_Copy_File_WithName_Rule_26() {
			super(26, false);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			ArrayList<File> realShellFileList = new ArrayList<File>();
			ArrayList<String> logInfo = new ArrayList<String>();
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				File curDirFile = fileItem.getParentFile();
				logInfo.add("==============记录文件" + curDirFile.getName() + "==============");
				String curDirPath = curDirFile.getAbsolutePath();
				String new_dir_name = (curDirPath + File.separator + fileItem.getName() + "_" + getTimeStamp())
						.replace(".", "_");
				ArrayList<String> rawContentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedPathDir = fixedFirstWordPath(rawContentList);

				if (curDirFile != null) {
					for (int j = 0; j < fixedPathDir.size(); j++) {
						String lineName = fixedPathDir.get(j);
						File realFileItem = new File(curDirPath + File.separator + lineName);

						if (realFileItem.exists() && realFileItem.isFile()) {

							realShellFileList.add(realFileItem);
						}
					}
				}

				if (realShellFileList.size() > 0) {
					File curOperationDirFile = new File(new_dir_name);
					curOperationDirFile.mkdirs();
					String targetDirPath = curOperationDirFile.getAbsolutePath();
					for (int j = 0; j < realShellFileList.size(); j++) {
						File srcFile = realShellFileList.get(j);
						String fileName = srcFile.getName();
						File targetFile = new File(targetDirPath + File.separator + fileName);
						fileCopy(srcFile, targetFile);
						logInfo.add("复制过滤文件[" + j + "] = " + targetFile.getAbsolutePath());
					}
				}

				writeContentToFile(I9_Temp_Text_File, logInfo);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 读取当前文件的内容 并在当前文件夹下内寻找该文件 复制到输入文件名称_时间戳目录";
		}

		ArrayList<String> fixedFirstWordPath(ArrayList<String> rawList) {
			ArrayList<String> fixedList = new ArrayList<String>();

			for (int i = 0; i < rawList.size(); i++) {
				String itemStr = rawList.get(i).trim();
				itemStr = itemStr.replace("\\", File.separator);
				itemStr = itemStr.replace("/", File.separator);
				itemStr = itemStr.replace("?", "");
				itemStr = itemStr.replace("!", "");
				itemStr = itemStr.replace("！", "");
				itemStr = itemStr.replace("？", "");
				itemStr = itemStr.replace("#", "");
				itemStr = itemStr.replace("@", "");
				itemStr = itemStr.replace("￥", "");
				itemStr = itemStr.replace("~", "");
				itemStr = itemStr.replace("&", "");
				itemStr = itemStr.replace("*", "");
				itemStr = itemStr.replace("|", "");
				itemStr = itemStr.replace("<", "");
				itemStr = itemStr.replace(">", "");
				itemStr = itemStr.replace("。", "");
				itemStr = itemStr.replace(",", "");
				itemStr = itemStr.replace("+", "");
				itemStr = itemStr.replace("\"", "");
				itemStr = itemStr.replace("：", "");
				itemStr = itemStr.replace(":", "");
				itemStr = itemStr.replace(File.separator + File.separator, File.separator);
				itemStr = itemStr.replace(File.separator + File.separator, File.separator);
				itemStr = itemStr.replace("\t", " ");
				if ("".equals(itemStr)) {
					continue;
				}

				fixedList.add(itemStr);

			}

			return fixedList;

		}

	}

	// 读取当前文件下的 实体文件的文件名称 输出到列表中
	class LS_Shell_RealFile_Rule_25 extends Basic_Rule {

		LS_Shell_RealFile_Rule_25(boolean mIsInputDirAsSearchPoint) {
			super(25);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		LS_Shell_RealFile_Rule_25() {
			super(25, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			ArrayList<String> realFileNameList = new ArrayList<String>();

			File dirFile = curInputFileList.get(0).getParentFile();
			File[] fileList = dirFile.listFiles();
			System.out.println("dirFile = " + dirFile + "        fileList = " + fileList.length);
			ArrayList<File> CurRealFileList = new ArrayList<File>();

			CurRealFileList.addAll(Arrays.asList(fileList));

			for (int i = 0; i < CurRealFileList.size(); i++) {
				File fileItem = CurRealFileList.get(i);
				String fileName = fileItem.getName();
				if (fileItem.isFile() && fileItem.exists()) {
					realFileNameList.add(fileName);
				}
			}
			writeContentToFile(I9_Temp_Text_File, realFileNameList);
			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把当前 目录下的实体文件 的 文件名称打印出来 !";
		}

	}

	// 读取当前目录的文件夹 里面的 jpg 和 png 把 读取到的二维码打印出来 到当前 页面
	class Image2QrCode_Rule_16 extends Basic_Rule {

		Image2QrCode_Rule_16(boolean mIsInputDirAsSearchPoint) {
			super(16);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		Image2QrCode_Rule_16() {
			super(16, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			ArrayList<String> AllQrStrList = new ArrayList<String>();
			ArrayList<String> CommonQrStrList = new ArrayList<String>();
			ArrayList<String> manageStrList = new ArrayList<String>();
			File dirFile = curInputFileList.get(0).getParentFile();
			File[] fileList = dirFile.listFiles();
			System.out.println("dirFile = " + dirFile + "        fileList = " + fileList.length);
			ArrayList<File> CurImageFileList = new ArrayList<File>();

			CurImageFileList.addAll(Arrays.asList(fileList));

			for (int i = 0; i < CurImageFileList.size(); i++) {
				File fileItem = CurImageFileList.get(i);
				String fileName_lower = fileItem.getName().toLowerCase();
				String qrStr = "";
				if (fileName_lower.endsWith(".jpg") || fileName_lower.endsWith(".png")) {

					if (CUR_OS_TYPE == OS_TYPE.Windows) {
						qrStr = Image2QrCode_Rule_24_Win(fileItem);
					} else if (CUR_OS_TYPE == OS_TYPE.MacOS) {
						// 实现 Mac 下 读取 二维码
//                        TextAs_QrCode_Rule_15_Mac(fileItem);
						qrStr = Image2QrCode_Rule_24_Win(fileItem);

					} else if (CUR_OS_TYPE == OS_TYPE.Linux) {
						System.out.println("无法在 Linux  下实现  没有notepad++ 啊! ");
					}

				}

				// magnet:?xt=urn:btih:
				if (qrStr != null && !"".equals(qrStr)) {
					if (qrStr.startsWith("magnet:")) {
						manageStrList.add(qrStr);
					} else {
						CommonQrStrList.add(qrStr);
					}

				}
			}

			AllQrStrList.addAll(manageStrList);
			AllQrStrList.add("");
			AllQrStrList.add("");
			if (CommonQrStrList.size() > 0) {
				AllQrStrList.add("═════════ 普通二维码 ═════════");
				AllQrStrList.addAll(CommonQrStrList);
			}

			writeContentToFile(I9_Temp_Text_File, AllQrStrList);
			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把当前路径下的图片中的QrCode png jpg二维码打印出来";
		}

	}

	// 往 每行的加入占位符 开头加入 〖* 第一个空格前加入*
	class Create_Install_Command_Rule_23 extends Basic_Rule {
		Create_Install_Command_Rule_23(boolean mIsInputDirAsSearchPoint) {
			super(23);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;

		}

		Create_Install_Command_Rule_23() {
			super(23, true);
		}

		ArrayList<File> getFileTypeList(File[] fileList, String type) {
			ArrayList<File> fliterList = new ArrayList<File>();
			if (fileList == null) {
				return fliterList;
			}
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].getName().toLowerCase().endsWith(type.toLowerCase())) {
					fliterList.add(fileList[i]);
				}
			}
			return fliterList;
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			File dirFile = curInputFileList.get(0).getParentFile();
			File[] fileList = dirFile.listFiles();
			System.out.println("dirFile = " + dirFile + "        fileList = " + fileList.length);
			ArrayList<File> exeFileList = getFileTypeList(fileList, ".exe");
			ArrayList<File> msiFileList = getFileTypeList(fileList, ".msi");
			ArrayList<File> allOperationFile = new ArrayList<File>();
			if (exeFileList != null) {
				allOperationFile.addAll(exeFileList);
			}

			if (msiFileList != null) {
				allOperationFile.addAll(msiFileList);
			}

			if (allOperationFile.size() == 0) {
				System.out.println("当前没有 exe 和 msi 文件  程序 退出！");
				return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
			}
			System.out.println("allOperationFile.size = " + allOperationFile.size());

			allOperationFile.sort(new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					long modify1 = o1.lastModified();
					long modify2 = o2.lastModified();
					if (modify1 == modify2)
						return 0;
					return modify1 > modify2 ? -1 : 1;
				}
			});
			String curShellPath = dirFile.getAbsolutePath();
			ArrayList<String> command_ContentList = new ArrayList<String>();

			File rule23_template_bat_file = new File(zbinPath + File.separator + "I9_Template_Rule23.bat");
			if (!rule23_template_bat_file.exists()) {
				System.out.println("当前模板文件不存在! 请检查  执行失败!  rule23_template_bat_file = "
						+ rule23_template_bat_file.getAbsolutePath());
				return null;
			}
			ArrayList<String> templateStrList = ReadFileContentAsList(rule23_template_bat_file);

			ArrayList<String> allCommand_InNotepad = new ArrayList<String>();
			ArrayList<String> allCommand_In_RawBat = new ArrayList<String>();
			for (int i = 0; i < allOperationFile.size(); i++) {
				ArrayList<String> curTemplateList = new ArrayList<String>();
				ArrayList<String> targetTemplateList = new ArrayList<String>();
//                Collections.copy(curTemplateList, templateStrList);

				curTemplateList.add("@echo off");
				curTemplateList.add("Setlocal ENABLEDELAYEDEXPANSION");

				for (int j = 0; j < templateStrList.size(); j++) {
					String newItem = new String(templateStrList.get(j));

					curTemplateList.add(newItem);
				}

				File fileItem = allOperationFile.get(i);
				String fileName = fileItem.getName(); // 【ZCUR_NAME】
				String absPath = fileItem.getAbsolutePath();// 【ZABSPATH】
				String fileName_NoPOint = getFileNameNoPoint(fileName);
				String absPath_NoPoint = fileItem.getParentFile().getAbsolutePath() + File.separator + fileName_NoPOint; // 【ZABSPATH_NOPOINT】

				allCommand_In_RawBat.add("echo ===============" + absPath + "===============");

				for (int j = 0; j < curTemplateList.size(); j++) {
					String oneCode = curTemplateList.get(j);
					oneCode = oneCode.replace("【ZCUR_NAME】", fileName);
					oneCode = oneCode.replace("【ZABSPATH】", absPath);
					oneCode = oneCode.replace("【ZABSPATH_NOPOINT】", absPath_NoPoint);
					targetTemplateList.add(oneCode);
					allCommand_In_RawBat.add(oneCode);
				}

				String newBatFile = fileItem.getParentFile().getAbsolutePath() + File.separator + "zbat_"
						+ fileName_NoPOint + ".bat";

				writeContentToFile(new File(newBatFile), targetTemplateList); // 对当前文件的文件进行 bat创建
				allCommand_InNotepad.add("===============" + absPath + "===============");
				allCommand_InNotepad.addAll(targetTemplateList);
				allCommand_InNotepad.add("\n\n");

				allCommand_In_RawBat.add("");
				allCommand_In_RawBat.add("");

			}

			ArrayList<String> allCommand_In_Bat_Fixed = new ArrayList<String>();
			allCommand_In_Bat_Fixed.add("@echo off");
			allCommand_In_Bat_Fixed.add("Setlocal ENABLEDELAYEDEXPANSION");
			allCommand_In_Bat_Fixed.addAll(allCommand_In_RawBat);
			writeContentToFile(new File(curShellPath + File.separator + "zAll_Install.bat"), allCommand_In_Bat_Fixed);
			System.out.println("command_ContentList.size = " + command_ContentList.size());
			writeContentToFile(I9_Temp_Text_File, allCommand_InNotepad);
			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return "读取当前目录的 exe  和 msi  输出 安装的 zbat_xxxx.bat 命令  测试静默安装命令 ";
		}

	}

	// 6位数字为 验证码code 5位数字为端口(两个) 三位数字为IP地址最后一位 组成ADB命令 进行输出
	class ADB_Wireless_WIFI_Rule_22 extends Basic_Rule {

		String pair_code_6tr = ""; // 配对的 6个peiduima

		String pair_port_5str = ""; // 配对码的 端口 5位数

		String adb_wireless_port_5str = ""; // adb 连接的 端口 5 位数

		String ipaddress_last_3str = ""; // IP地址的最后一个值

		String IP_Addpress_Pre_1 = "192.168.0.";
		String IP_Addpress_Pre_2 = "10.106.20.";

		boolean isAllSuccess = false;

		ADB_Wireless_WIFI_Rule_22(boolean mIsInputDirAsSearchPoint) {
			super(22);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		ADB_Wireless_WIFI_Rule_22() {
			super(22, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

//                ArrayList<String>  fixedStrArr =   clearChinese_Rule_13(fileItem);
//                ArrayList<String>  fixedStrArr =   add_system_out_Rule_21(fileItem);

				ArrayList<String> fixedStrArr = new ArrayList<String>();
				String fileContent = ReadFileContent(fileItem);
				fileContent = fileContent.replace("\n", " ");
				fixedStrArr.add("提示1: 手机和电脑 必须在 同一个 网络下才能完成 wireless adb 连接操作! ");
				pair_code_6tr = getDefineLengthDigital(fileContent, 6);
				if (pair_code_6tr == null) { // 一：if
					fixedStrArr.add("[1]->无法搜索到 6位 配对码");
				} else {
					fixedStrArr.add("[1]-> 6位配对码: " + pair_code_6tr);

					String fixed_1_content = fileContent.replace(pair_code_6tr, "");
					pair_port_5str = getDefineLengthDigital(fixed_1_content, 5);

					System.out.println("fixed_1_content = " + fixed_1_content);
					if (pair_port_5str == null) { // 一二：if
						fixedStrArr.add("[2]-> 无法搜索到 5位 配对端口码");
					} else {
						fixedStrArr.add("[2]-> 5位 配对端口码: " + pair_port_5str);

						String fixed_2_content = fixed_1_content.replace(pair_port_5str, "");

						System.out.println("fixed_2_content = " + fixed_2_content);
						adb_wireless_port_5str = getDefineLengthDigital(fixed_2_content, 5);

						if (adb_wireless_port_5str == null) { // 一二三：if
							fixedStrArr.add("[3]-> 无法搜索到 5位 连接端口码");
						} else {
							fixedStrArr.add("[3]-> 5位 连接端口码: " + adb_wireless_port_5str.replace("\n", ""));

							String fixed_3_content = fixed_2_content.replace(adb_wireless_port_5str, "");
							System.out.println("fixed_3_content = " + fixed_3_content);
							ipaddress_last_3str = getDefineLengthDigital_Range(fixed_3_content, 3);
							if (ipaddress_last_3str == null) {
								fixedStrArr.add("[4]-> 无法搜索到 IP地址最后值(最多三位)");
							} else {
								fixedStrArr.add("[4]->IP地址最后值(最多三位): " + ipaddress_last_3str);
								isAllSuccess = true;

							}

						}
					}

				}

				if (isAllSuccess) {
					String IP_Address_1 = IP_Addpress_Pre_1 + ipaddress_last_3str;
					fixedStrArr.add("\n");
					fixedStrArr.add("═════════════════════IP前缀:" + IP_Addpress_Pre_1 + "═════════════════════");
					String adbCommand_1_1 = calcul_Wireless_ADB_Command(IP_Address_1, adb_wireless_port_5str,
							pair_code_6tr, pair_port_5str);
					String adbCommand_1_2 = calcul_Wireless_ADB_Command(IP_Address_1, pair_port_5str, pair_code_6tr,
							adb_wireless_port_5str);

					fixedStrArr.add("连接命令1: \n" + adbCommand_1_1);
					fixedStrArr.add("\n");
					fixedStrArr.add("连接命令2: \n" + adbCommand_1_2);

					String IP_Address_2 = IP_Addpress_Pre_2 + ipaddress_last_3str;
					fixedStrArr.add("\n");
					fixedStrArr.add("═════════════════════ IP前缀:" + IP_Addpress_Pre_2 + "═════════════════════");
					String adbCommand_2_1 = calcul_Wireless_ADB_Command(IP_Address_2, adb_wireless_port_5str,
							pair_code_6tr, pair_port_5str);
					String adbCommand_2_2 = calcul_Wireless_ADB_Command(IP_Address_2, pair_port_5str, pair_code_6tr,
							adb_wireless_port_5str);

					fixedStrArr.add("连接命令1: \n" + adbCommand_2_1);
					fixedStrArr.add("\n");
					fixedStrArr.add("连接命令2: \n" + adbCommand_2_2);
				}

				/*
				 * 
				 * System.out.println("════════════"+"输出文件 Begin " + "════════════"); for (int j
				 * = 0; j < fixedStrArr.size(); j++) { System.out.println(fixedStrArr.get(j)); }
				 * System.out.println("════════════"+"输出文件 End "+"════════════");
				 * 
				 */
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);

				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->  把当前文件的每一行 都 转为 System.out.println(xx) 的内容   File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		String calcul_Wireless_ADB_Command(String ipAddress, String port_5_ip, String pairCode_6, String pair_port_5) {
			String commandStr = "adb kill-server && " + "adb pair " + ipAddress + ":" + pair_port_5 + "  " + pairCode_6
					+ "  && " + " adb connect " + ipAddress + ":" + port_5_ip + " && " + " adb -s " + ipAddress + ":"
					+ port_5_ip + " shell ";
			commandStr = commandStr.replace("\n", "");
			return commandStr;
		}

		String getDefineLengthDigital_Range(String content, int digitalLength) {
			String resultStr = null;
			if (content == null || content.trim().length() < digitalLength) {
				return resultStr;
			}
			if (content.contains(" ")) {
				String[] strArr = content.split(" ");
				if (strArr == null || strArr.length == 0) {
					return resultStr;
				}
				System.out.println(" digitalLength = " + digitalLength + "   Content=\n" + content);
				System.out.println();
				for (int i = 0; i < strArr.length; i++) {
					System.out.println("split[" + i + "] = " + strArr[i]);
				}

				for (int i = 0; i < strArr.length; i++) {
					System.out.println("split[" + i + "] = " + strArr[i]);
					if (isNumeric(strArr[i]) && strArr[i].trim().length() <= digitalLength) {
						System.out.println("split[" + i + "] = " + strArr[i] + "  Selected!");
						return strArr[i];
					}

				}

			}

			return resultStr;
		}

		String getDefineLengthDigital(String content, int digitalLength) {
			String resultStr = null;
			if (content == null || content.trim().length() < digitalLength) {
				return resultStr;
			}

			if (content.contains(" ")) {
				String[] strArr = content.split(" ");
				if (strArr == null || strArr.length == 0) {
					return resultStr;
				}
				System.out.println(" digitalLength = " + digitalLength + "   Content=\n" + content);
				System.out.println();
				for (int i = 0; i < strArr.length; i++) {
					System.out.println("split[" + i + "] = " + strArr[i]);
				}

				for (int i = 0; i < strArr.length; i++) {
					System.out.println("split[" + i + "] = " + strArr[i]);
					if (isNumeric(strArr[i].trim()) && strArr[i].trim().length() == digitalLength) {
						System.out.println("split[" + i + "] = " + strArr[i] + "  Selected!");
						return strArr[i];
					}

				}

			}

			return resultStr;
		}

		@Override
		String simpleDesc() {
			return "  把 输入的四个参数 转为 无线 adb 连接的命令 1.IP地址 2.IP端口 3.6位配对码 4.配对端口";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	class System_Out_Print_Rule_21 extends Basic_Rule {

		System_Out_Print_Rule_21(boolean mIsInputDirAsSearchPoint) {
			super(21);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		System_Out_Print_Rule_21() {
			super(21, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

//                ArrayList<String>  fixedStrArr =   clearChinese_Rule_13(fileItem);
				ArrayList<String> fixedStrArr = add_system_out_Rule_21(fileItem);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->  把当前文件的每一行 都 转为 System.out.println(xx) 的内容   File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return "  把当前文件的每一行 都 转为 System.out.println(xx) 的内容 ";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	// 往 每行的加入占位符 开头加入 〖* 第一个空格前加入*
	class Cal_Install_Command_Rule_20 extends Basic_Rule {
		Cal_Install_Command_Rule_20(boolean mIsInputDirAsSearchPoint) {
			super(20);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;

		}

		Cal_Install_Command_Rule_20() {
			super(20, true);
		}

		ArrayList<File> getFileTypeList(File[] fileList, String type) {
			ArrayList<File> fliterList = new ArrayList<File>();
			if (fileList == null) {
				return fliterList;
			}
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].getName().toLowerCase().endsWith(type.toLowerCase())) {
					fliterList.add(fileList[i]);
				}
			}
			return fliterList;
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			File dirFile = curInputFileList.get(0).getParentFile();
			File[] fileList = dirFile.listFiles();
			System.out.println("dirFile = " + dirFile + "        fileList = " + fileList.length);
			ArrayList<File> exeFileList = getFileTypeList(fileList, ".exe");
			ArrayList<File> msiFileList = getFileTypeList(fileList, ".msi");
			ArrayList<File> allOperationFile = new ArrayList<File>();
			if (exeFileList != null) {
				allOperationFile.addAll(exeFileList);
			}

			if (msiFileList != null) {
				allOperationFile.addAll(msiFileList);
			}

			if (allOperationFile.size() == 0) {
				System.out.println("当前没有 exe 和 msi 文件  程序 退出！");
				return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
			}
			System.out.println("allOperationFile.size = " + allOperationFile.size());

			allOperationFile.sort(new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					long modify1 = o1.lastModified();
					long modify2 = o2.lastModified();
					if (modify1 == modify2)
						return 0;
					return modify1 > modify2 ? -1 : 1;
				}
			});
			String curShellPath = dirFile.getAbsolutePath();
			ArrayList<String> command_ContentList = new ArrayList<String>();

			command_ContentList.add("@echo off");
			command_ContentList.add("Setlocal ENABLEDELAYEDEXPANSION");

			String str_pre_1 = "start /wait /min ";

//            String str_end_1 = " /S /Q /D=E:\\Temp_Install\\";

			String str_end_1 = " /S /Q /D=" + curShellPath + File.separator;

			String str_end_2 = " /quiet /norestart INSTALLDIR=\"" + curShellPath + "\\";

			String str_end_3 = " /VERYSILENT /SUPPRESSMSGBOXES /NORESTART /SP-";
			for (int i = 0; i < allOperationFile.size(); i++) {
				File fileItem = allOperationFile.get(i);
				String fileName = fileItem.getName();
				String newFileName = fileName;
				String absPath = fileItem.getAbsolutePath();
				if (fileName.contains(" ")) {
					newFileName = fileName.replace(" ", "");
					tryReName(fileItem, newFileName);
					System.out.println("  空格改名 " + fileName + "   -->  " + newFileName);
					absPath = absPath.replace(fileName, newFileName);
				}
				File fileItem_new = new File(absPath);
				if (fileItem_new.exists()) {

					String fileName_Point = fileItem_new.getName();
					System.out.println("操作索引[" + i + "]  = " + fileName_Point);
					String nameStr_noPoint = getFileNameNoPoint(fileName_Point);

					String tip1 = "rem #### " + fileName_Point;
					String command1 = str_pre_1 + fileName_Point + " " + str_end_1 + nameStr_noPoint;

					String command2 = str_pre_1 + fileName_Point + " " + str_end_2 + nameStr_noPoint + "\"";

					String command3 = fileItem_new.getAbsolutePath() + str_end_3;
					command_ContentList.add(tip1);
					command_ContentList.add("echo  \"<type1_/S/Q/D> " + command1 + "\"");
					command_ContentList.add(command1);
					command_ContentList.add("");
					command_ContentList.add("echo  \" </quiet /norestart INSTALLDIR> " + command2 + "\"");
					command_ContentList.add(command2);
					command_ContentList.add("");
					command_ContentList
							.add("echo  \" </VERYSILENT /SUPPRESSMSGBOXES /NORESTART /SP->  " + command3 + "\"");
					command_ContentList.add(command3);
					command_ContentList.add("");

					command_ContentList.add("\n");
				}

			}

			System.out.println("command_ContentList.size = " + command_ContentList.size());
			writeContentToFile(I9_Temp_Text_File, command_ContentList);
			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 读取当前的 exe 和 msi 文件 输出两种对应的安装命令 方便测试! ";
		}

	}

	/*
	 * @echo off Setlocal ENABLEDELAYEDEXPANSION start /wait /min
	 * Git-2.30.0-64-bit.exe /S /Q
	 * /D=D:\zbin_model\Software\ZWin_Software\E0_No_Slient_OneExe\git-2.30.0-64-bit
	 * if %errorlevel%==0 ( echo Git-2.30.0-64-bit.exe -- OK[type1_/S/Q/D] ) else (
	 * echo Git-2.30.0-64-bit.exe -- Error[type1_/S/Q/D] start /wait /min
	 * ./Git-2.30.0-64-bit.exe /quiet /norestart INSTALLDIR=
	 * "D:\zbin_model\Software\ZWin_Software\E0_No_Slient_OneExe\git-2.30.0-64-bit"
	 * if %errorlevel%==0 ( echo Git-2.30.0-64-bit.exe -- OK[type2_/quiet] ) else (
	 * echo Git-2.30.0-64-bit.exe -- Error[type2_/quiet]
	 * D:\zbin_model\Software\ZWin_Software\E0_No_Slient_OneExe\Git-2.30.0-64-bit.
	 * exe /VERYSILENT /SUPPRESSMSGBOXES /NORESTART /SP- if %errorlevel%==0 ( echo
	 * Git-2.30.0-64-bit.exe -- OK[type3_/VERYSILENT] ) else ( echo
	 * Git-2.30.0-64-bit.exe -- error[type1_type2_type3] ) ) )
	 */

	// 往 每行的加入占位符 开头加入 〖* 第一个空格前加入*
	class FirstWord_MakeDir_Rule_19 extends Basic_Rule {

		FirstWord_MakeDir_Rule_19(boolean mIsInputDirAsSearchPoint) {
			super(19);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;

		}

		FirstWord_MakeDir_Rule_19() {
			super(19, false);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				File curDirFile = fileItem.getParentFile();

				ArrayList<String> rawContentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedPathDir = fixedFirstWordPath(rawContentList);

				if (curDirFile != null) {
					for (int j = 0; j < fixedPathDir.size(); j++) {
						String dirName = fixedPathDir.get(j);
						String dirAbsPath = curDirFile.getAbsolutePath() + File.separator + dirName;
						File newDirTemp = new File(dirAbsPath);
						String fileName = newDirTemp.getName();
						boolean isDir = true;
						if (fileName.contains(".")) {
							try {
								isDir = false;
								newDirTemp.getParentFile().mkdirs();
								newDirTemp.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							isDir = true;
							newDirTemp.mkdirs();
						}
						String filedesc = isDir ? " 目录" : "文件";
						System.out.println("创建 " + filedesc + "  " + newDirTemp.getAbsolutePath() + " 成功! ");
					}
					return null;
				} else {
					System.out.println("FirstWord_MakeDir_Rule_19   当前获取到的Shell目录为空!   无法创建 Z规则文件夹!  ");
				}

			}
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 读取文件中的每一行的空格前路径 并以此生成在当前文件目录生成指定目录";
		}

		ArrayList<String> fixedFirstWordPath(ArrayList<String> rawList) {
			ArrayList<String> fixedList = new ArrayList<String>();

			for (int i = 0; i < rawList.size(); i++) {
				String itemStr = rawList.get(i).trim();
				itemStr = itemStr.replace("\\", File.separator);
				itemStr = itemStr.replace("/", File.separator);
				itemStr = itemStr.replace("?", "");
				itemStr = itemStr.replace("!", "");
				itemStr = itemStr.replace("！", "");
				itemStr = itemStr.replace("？", "");
				itemStr = itemStr.replace("#", "");
				itemStr = itemStr.replace("@", "");
				itemStr = itemStr.replace("￥", "");
				itemStr = itemStr.replace("~", "");
				itemStr = itemStr.replace("&", "");
				itemStr = itemStr.replace("*", "");
				itemStr = itemStr.replace("|", "");
				itemStr = itemStr.replace("<", "");
				itemStr = itemStr.replace(">", "");
				itemStr = itemStr.replace("。", "");
				itemStr = itemStr.replace(",", "");
				itemStr = itemStr.replace("+", "");
				itemStr = itemStr.replace("\"", "");
				itemStr = itemStr.replace("：", "");
				itemStr = itemStr.replace(":", "");
				itemStr = itemStr.replace(File.separator + File.separator, File.separator);
				itemStr = itemStr.replace(File.separator + File.separator, File.separator);
				itemStr = itemStr.replace("\t", " ");
				if ("".equals(itemStr)) {
					continue;
				}
				if (itemStr.contains(" ")) {
					String[] arr = itemStr.split(" ");
					fixedList.add(arr[0]);
				} else {
					fixedList.add(itemStr);
				}
			}

			return fixedList;

		}

	}

	// 往 每行的加入占位符 开头加入 〖* 第一个空格前加入*
	class Add_BeginStr_EndStr_Rule_18 extends Basic_Rule {

		Add_BeginStr_EndStr_Rule_18(boolean mIsInputDirAsSearchPoint) {
			super(18);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;

		}

		Add_BeginStr_EndStr_Rule_18() {
			super(18, false);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedStrArr = Tushare_TreeNodeData_Rule18(contentList);

				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index
						+ " -> 把tushare的数据 接口 转为 J0_treedata.txt 的 TreeNode 注意格式(直接复制表格)!  结点 [ 中文名 + 网址 ] 开头  File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把tushare的数据 接口 转为 J0_treedata.txt 的 TreeNode 注意格式(直接复制表格)!  结点 [ 中文名 + 网址 ] 开头 https://tushare.pro/document/2?doc_id=103  ";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	// Rule_17 End 对 Json 格式的文件 或者 以json格式保存的文件 生成bean文件 以及 Graphviz 绘图显示结构

	static ArrayList<File> rule17_resultFile_List = new  ArrayList<File> ();
	class Make_Json_As_JavaFile_Graphviz2Jpg_Rule_17 extends Basic_Rule {

		File targetFile_ResultDirFile;  // 在 目标 .json 文件中生成的 放置 目标 图片 目标 .java 文件的目录 
		Make_Json_As_JavaFile_Graphviz2Jpg_Rule_17(boolean mIsInputDirAsSearchPoint) {
			super(17);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		Make_Json_As_JavaFile_Graphviz2Jpg_Rule_17() {
			super(17, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				File parentFile = fileItem.getParentFile();
				String jsonFileName = fileItem.getName();
				
				targetFile_ResultDirFile = new File(parentFile.getAbsolutePath()+File.separator+jsonFileName.replace(".", "_")+"_"+getTimeStamp());
				if (CUR_OS_TYPE == OS_TYPE.Windows) {
					Make_Json_As_JavaFile_Rule_17(fileItem,targetFile_ResultDirFile);
				} else if (CUR_OS_TYPE == OS_TYPE.MacOS) {
					System.out.println("无法在 Linux下实现  没有notepad++ 啊! ");
					System.out.println("无法实现对 Windows下 对 Json 格式的文件 或者 以json格式保存的文件 生成bean文件 以及 Graphviz 绘图显示结构 (B6)");
				} else if (CUR_OS_TYPE == OS_TYPE.Linux) {
					System.out.println("无法在 Linux下实现  没有notepad++ 啊! ");
					System.out.println("无法实现对 Windows下 对 Json 格式的文件 或者 以json格式保存的文件 生成bean文件 以及 Graphviz 绘图显示结构 (B6)");

				}

			}
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " Windows下 对 Json 格式的文件 或者 以json格式保存的文件 生成bean文件 以及 Graphviz 绘图显示结构 (B6)";
		}

	}

	class ReadStrFromFile_Rule_24 extends Basic_Rule {

		ReadStrFromFile_Rule_24(boolean mIsInputDirAsSearchPoint) {
			super(24);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		ReadStrFromFile_Rule_24() {
			super(24, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				if (CUR_OS_TYPE == OS_TYPE.Windows) {
					ReadStrFromFile_Rule_16(fileItem);
				} else {
					System.out.println("无法在 Linux 和 MacOS 下实现  没有notepad++ 啊! ");
				}

			}
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " Windows下 读物当前文件内容 并 播放 vbs 声音 (B3)";
		}

	}

	// Rule_15 Begin 读取文件的第一行转为 二维码显示出来 B1
//    public static void TextAs_QrCode_Rule_15(File srcFile) {

	class TextAs_QrCode_Rule_15 extends Basic_Rule {

		TextAs_QrCode_Rule_15(boolean mIsInputDirAsSearchPoint) {
			super(15);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		TextAs_QrCode_Rule_15() {
			super(15, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				if (CUR_OS_TYPE == OS_TYPE.Windows) {
					TextAs_QrCode_Rule_15_Win(fileItem);
				} else if (CUR_OS_TYPE == OS_TYPE.MacOS) {
					// 实现 Mac 下 显示 二维码
					TextAs_QrCode_Rule_15_Mac(fileItem);

				} else if (CUR_OS_TYPE == OS_TYPE.Linux) {
					System.out.println("无法在 Linux  下实现  没有notepad++ 啊! ");
				}

			}
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 读取文件的第一行转为 二维码 QrCode 显示出来 (B1)";
		}

	}

	// // Rule_14 Begin 汉字转换为拼音 周 zhou 中国 zhong_guo (A9)
	// public static ArrayList<String> Chinese_To_PinYin_Rule_14(File srcFile) {

	class Chinese_To_PinYin_Rule_14 extends Basic_Rule {

		Chinese_To_PinYin_Rule_14(boolean mIsInputDirAsSearchPoint) {
			super(14);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		Chinese_To_PinYin_Rule_14() {
			super(14, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				ArrayList<String> fixedStrArr = Chinese_To_PinYin_Rule_14(fileItem);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->汉字转换为拼音   周 zhou   中国 zhong_guo  (A9)   File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 汉字转换为拼音   周 zhou   中国 zhong_guo  (A9)";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	class ClearChinese_Rule_13 extends Basic_Rule {

		ClearChinese_Rule_13(boolean mIsInputDirAsSearchPoint) {
			super(13);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		ClearChinese_Rule_13() {
			super(13, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				ArrayList<String> fixedStrArr = clearChinese_Rule_13(fileItem);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->去除掉当前文件中的中文 每一个中文 以一个空格代替 （A7)   File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 去除掉当前文件中的中文 每一个中文 以一个空格代替 （A7) ";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	class BoardCopy_ToLeft_Rule_12 extends Basic_Rule {

		BoardCopy_ToLeft_Rule_12(boolean mIsInputDirAsSearchPoint) {
			super(12);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		BoardCopy_ToLeft_Rule_12() {
			super(12, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				ArrayList<String> fixedStrArr = BoardCopyStrToLeft_Rule_12(fileItem);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " -> 把 剪切板的字符串 逐行 复制 到当前 文本的 逐行 后边   A6   File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把 剪切板的字符串 逐行 复制 到当前 文本的 逐行 后边  (A6)";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	// getMDtable_ForCommonText_Rule_11

	class Table_MDStyle_Rule_11 extends Basic_Rule {

		Table_MDStyle_Rule_11(boolean mIsInputDirAsSearchPoint) {
			super(11);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		Table_MDStyle_Rule_11() {
			super(11, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				ArrayList<String> fixedStrArr = getMDtable_ForCommonText_Rule_11(fileItem);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " -> 把表格 2x2 3x3 4x4  ... 转为   MD文件格式 加入 |---| 分割符号  File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把表格 2x2 3x3 4x4  ... 转为   MD文件格式 加入 |---| 分割符号 (A5)";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	// 往 每行的加入占位符 开头加入 【ZHead】 结尾加入【ZTail】 方便替换
	class Add_BeginStr_EndStr_Rule_10 extends Basic_Rule {

		String beginStr;
		String endStr;

		Add_BeginStr_EndStr_Rule_10(boolean mIsInputDirAsSearchPoint) {
			super(10);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
			beginStr = "【ZHead】";
			endStr = "【ZTail】";
		}

		Add_BeginStr_EndStr_Rule_10() {
			super(10, false);
			beginStr = "【ZHead】";
			endStr = "【ZTail】";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedStrArr = addZhanWeiFlag_Rule_10(contentList, beginStr, endStr);
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " -> 往 每行的加入占位符   开头加入 【ZHead】   结尾加入【ZTail】 方便替换  File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 往 每行的加入占位符   开头加入 【ZHead】   结尾加入【ZTail】 方便替换 (A4) ";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	// ShuZhuangHang_PaiLie_Rule_6

	class PaiLie_5x5_Rule_9 extends PaiLie_2x2_Rule_6 {
		PaiLie_5x5_Rule_9() {
			super(9);
			Rule6_num_row = 5;
			rowSize = 5;
		}
	}

	class PaiLie_4x4_Rule_8 extends PaiLie_2x2_Rule_6 {
		PaiLie_4x4_Rule_8() {
			super(8);
			Rule6_num_row = 4;
			rowSize = 4;
		}
	}

	class PaiLie_3x3_Rule_7 extends PaiLie_2x2_Rule_6 {
		PaiLie_3x3_Rule_7() {
			super(7);
			Rule6_num_row = 3;
			rowSize = 3;
		}
	}

	class PaiLie_2x2_Rule_6 extends Basic_Rule {
		int rowSize = 0;

		PaiLie_2x2_Rule_6(boolean mIsInputDirAsSearchPoint) {
			super(6);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
			Rule6_num_row = 2;
			rowSize = Rule6_num_row;
		}

		PaiLie_2x2_Rule_6(int ruleIndex) {
			super(ruleIndex, false);
			Rule6_num_row = 2;
			rowSize = Rule6_num_row;
		}

		PaiLie_2x2_Rule_6() {
			super(6, false);
			Rule6_num_row = 2;
			rowSize = Rule6_num_row;
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			Rule6_num_row = rowSize;
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

//                ArrayList<String> contentList = calcul_duiqi_Rule_5(fileItem,I9_Temp_Text_File);
				ArrayList<String> fixedStrArr = ShuZhuangHang_PaiLie_Rule_6(fileItem);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " -> 把当前顺序排列的数据 转为每行" + rowSize + "个的排序方式  File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把当前顺序排列的数据 转为每行" + rowSize + "个的排序方式 (A3)";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	class Duiqi_Hang_Rule_5 extends Basic_Rule {

		Duiqi_Hang_Rule_5(boolean mIsInputDirAsSearchPoint) {
			super(5);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		Duiqi_Hang_Rule_5() {
			super(5, false);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

//                ArrayList<String> contentList = calcul_duiqi_Rule_5(fileItem,I9_Temp_Text_File);
				ArrayList<String> fixedStrArr = duiqi_Rule_5(fileItem, I9_Temp_Text_File);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_5 -> 把当前 表格数据 行对齐( 每行 数值项个数相等)  File=" + fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把当前 表格数据 行对齐( 需要每行 数值项个数相等) (A2)";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	class ClearChinese_Rule_4 extends Basic_Rule {

		ClearChinese_Rule_4(boolean mIsInputDirAsSearchPoint) {
			super(4);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		ClearChinese_Rule_4() {
			super(4, false);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedStrArr = clearChinese_Rule_4(contentList);
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_4 -> 把当前文件的中文去除  File=" + fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把当前文件的中文去除";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	class AddLineNumberChar_Rule_3 extends Basic_Rule {

		AddLineNumberChar_Rule_3(boolean mIsInputDirAsSearchPoint) {
			super(3);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		AddLineNumberChar_Rule_3() {
			super(3, false);

		}

		// 1. 完成参数的 自我客制化 实现 checkParamsOK 方法

		// 2. 对应的逻辑方法 实现方法 applyOperationRule

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedStrArr = addLineNumberChar_Rule_3(contentList);
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_3 -> 把当前文件加入行号  File=" + fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把当前文件加入行号 (A1)";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	class OnlyGetFirstStr_AsOneLine_Rule_2 extends Basic_Rule {

		OnlyGetFirstStr_AsOneLine_Rule_2(boolean mIsInputDirAsSearchPoint) {
			super(2);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		OnlyGetFirstStr_AsOneLine_Rule_2() {
			super(2, false);

		}

		// 1. 完成参数的 自我客制化 实现 checkParamsOK 方法

		// 2. 对应的逻辑方法 实现方法 applyOperationRule

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedStrArr = clearOneBlankCharAsOneStr_Rule_2(contentList);
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println(
						"rule_2 -> 把当前文件内容的每行的第一个空格之后的字符串组成一个字符串 中间《等号=分割》 File=" + fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return "把当前文件内容的每行的第一个空格之后的字符串组成一个字符串 《中间等号=分割》 《等号=分割》";
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			}

			return itemDesc;
		}
	}

	// 只获取 每一行的第一个空格前的字符串 其余字符串 删除
	class OnlyGetFirstStr_InOneLine_Rule_1 extends Basic_Rule {

		OnlyGetFirstStr_InOneLine_Rule_1(boolean mIsInputDirAsSearchPoint) {
			super(1);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
		}

		OnlyGetFirstStr_InOneLine_Rule_1() {
			super(1, false);

		}

		// 1. 完成参数的 自我客制化 实现 checkParamsOK 方法

		// 2. 对应的逻辑方法 实现方法 applyOperationRule

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedStrArr = clearOneBlankChar_Rule_1(contentList);
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_1 -> 把当前文件内容的每行的第一个空格之后的字符串删除！ File=" + fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:"
						+ " 只获取 每一行的第一个空格前的字符串 其余字符串 删除";
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:"
						+ " 只获取 每一行的第一个空格前的字符串 其余字符串 删除";
			}

			return itemDesc;
		}

		@Override
		String simpleDesc() {
			return "只获取 每一行的第一个空格前的字符串 其余字符串 删除";
		}

	}

	class Basic_Rule extends Rule {
		Basic_Rule(String ctype, int cindex, int opera_type) {
			this.file_type = ctype;
			this.rule_index = cindex;
			this.identify = this.file_type + "" + this.rule_index;
			curFilterFileTypeList = new ArrayList<String>();
			curFixedFileList = new ArrayList<File>();
			needAllFileFlag = true;
			curInputFileList = new ArrayList<File>();
			if (First_Input_RealFile != null) {
				curInputFileList.add(First_Input_RealFile);
			}
		}

		Basic_Rule(int ruleIndex) {
			this.rule_index = ruleIndex;
			this.file_type = "*"; // 文件的处理类型 默认是 *
			this.identify = this.file_type + "" + this.rule_index;
			curFilterFileTypeList = new ArrayList<String>();
			curFixedFileList = new ArrayList<File>();
			errorMsg = "";
			needAllFileFlag = true;
			curInputFileList = new ArrayList<File>();
			if (First_Input_RealFile != null) {
				curInputFileList.add(First_Input_RealFile);
			}
		}

		Basic_Rule(int ruleIndex, boolean mNeedAllFile) {
			this.rule_index = ruleIndex;
			this.file_type = "*"; // 文件的处理类型 默认是 *
			this.identify = this.file_type + "" + this.rule_index;
			curFilterFileTypeList = new ArrayList<String>();
			curFixedFileList = new ArrayList<File>();
			errorMsg = "";
			needAllFileFlag = mNeedAllFile;
			curInputFileList = new ArrayList<File>();
			if (First_Input_RealFile != null) {
				curInputFileList.add(First_Input_RealFile);
			}
		}

		@Override
		void operationRule(ArrayList<String> inputParamsList) {

			ArrayList<File> shellFileList = new ArrayList<File>();
			ArrayList<File> subDirList = new ArrayList<File>();
			ArrayList<File> realFileList = new ArrayList<File>();
			ArrayList<File> allFileList = new ArrayList<File>();
			// 当前 shell 目录下的所有文件
			shellFileList.addAll(Arrays.asList(CUR_Dir_FILE.listFiles()));
			if (needAllFileFlag) {
				if (isInputDirAsSearchPoint && inputDirFile != null) {
					allFileList = getAllSubFile(inputDirFile, null, null);
					initFileTypeMap(allFileList);
				} else {
					allFileList = getAllSubFile(CUR_Dir_FILE, null, null);
					initFileTypeMap(allFileList);
				}

			}

			for (int j = 0; j < allFileList.size(); j++) {
				File curFile = allFileList.get(j);
				if (curFile.isDirectory()) {
					subDirList.add(curFile);
				} else {
					realFileList.add(curFile);
				}
			}
			applyOperationRule(shellFileList, CUR_Dir_FILETypeMap, subDirList, realFileList);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			return null;
		}

		String simpleDesc() {
			return null;
		}

		@Override
		boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {

			if (otherParams == null || otherParams.size() == 0) {
				errorMsg = "用户输入的文件参数为空";
				return false;
			}

			for (int i = 0; i < otherParams.size(); i++) {
				String pre = "." + File.separator;
				String curStringItem = otherParams.get(i).toString();
				String curAbsPath = "";
				if (curStringItem.startsWith(pre)) {
					curStringItem = curStringItem.substring(2);
				}
				curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
				File curFIle = new File(curAbsPath);
				if (curFIle.exists() && !curInputFileList.contains(curFIle)) {
					if (First_Input_RealFile != null
							&& First_Input_RealFile.getAbsolutePath().equals(curFIle.getAbsolutePath())) {
						continue;
					} else {
						curInputFileList.add(curFIle);
					}

					System.out.println("curFIle = " + curFIle.getAbsolutePath() + " TIme = " + getTimeStamp());
				}
			}
			return true; // 默认返回通过 不检查参数 如果有检查的需求 那么就实现它
		}

		// 3. 如果当前 执行 错误 checkParams 返回 false 那么 将 打印这个函数 说明错误的可能原因
		@Override
		void showWrongMessage() {
			System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
			System.out.println(" errorMsg = " + errorMsg);
		}

		// 4. 当前 rule的 说明 将会打印在 用户输入为空时的 提示语句！
		@Override
		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + ""
						+ simpleDesc();
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
		ArrayList<File> curInputFileList;
		int rule_index; // rule_index 组成了最基础的唯一键 rule_index 就是唯一的序号 1 2 3 4 5 6 7

		// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
		// 属性进行修改(文件名称)
		// 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) // 5. 从shell 中获取到的路径 去对某一个文件进行操作

		Rule() {
			inputDirFile = First_Input_Dir;
			if (inputDirFile == null) {
				isInputDirAsSearchPoint = false;
			}
		}

		String file_type; // * 标识所有的文件类型 以及当前操作类型文件 或者 单独的文件过滤类型
		String identify;
		String errorMsg;
		boolean needAllFileFlag;

		File inputDirFile; // 操作文件 目录

		boolean isInputDirAsSearchPoint; // 是否以 输入的 文件夹作为 全局搜索的 起点

		ArrayList<String> curFilterFileTypeList; // 当前的文件过滤类型 多种文件过滤类型 例如把 多种格式 jpeg png 转为 jpg 时 使用到
		ArrayList<File> curFixedFileList; // 当前修改操作成功的集合
		ArrayList<File> inputFileList; // 从输入参数得到的文件的集合

		abstract void operationRule(ArrayList<String> inputParamsList);

		// abstract String applyStringOperationRule1(String origin); // 不要这样的方法了 只保留
		// 最有用的 那个 applyOperationRule
//        abstract    File applyFileByteOperationRule2(File originFile);
//        abstract    ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap);
		// applyFileListRule4
		abstract ArrayList<File> applyOperationRule(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList);

		// abstract void initParams4InputParam(String inputParam); // 初始化Rule的参数
		// 依据输入的字符串
		abstract String ruleTip(String type, int index, String batName, OS_TYPE curType); // 使用说明列表 如果覆盖 那么就不使用默认的说明 ,
																							// 默认就一种情况

		abstract String simpleDesc(); // 使用的简单描述 中文的该 rule的使用情况 默认会在 ruleTip 被调用

		abstract boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams);

		abstract void showWrongMessage();
	}

	static void writeContentToFile(File file, String strParam) {

		try {
			if (file != null && !file.exists()) {
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

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	static byte[] listTobyte(List<Byte> list) {

		byte[] bytes = Bytes.toArray(list);

		return bytes;
	}

	static void writeByteArrToFile(File file, ArrayList<Byte> byteList) {
		try {

			byte[] byteArr = listTobyte(byteList);
			InputStream is = new ByteArrayInputStream(byteArr);

			if (file != null && !file.exists()) {
				file.createNewFile();
			}

			if (file != null && file.exists()) {

				byte[] buff = new byte[1024];
				int len = 0;

				OutputStream curBW = new FileOutputStream(file);
//                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
				while ((len = is.read(buff)) != -1) {
					curBW.write(buff, 0, len);
				}

				curBW.flush();
				is.close();
				curBW.close();
				System.out.println("write Byte out File OK !  File = " + file.getAbsolutePath());
			} else {
				System.out.println("write out Byte File  Failed !    File = " + file.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static void writeContentToFile(File file, ArrayList<String> strList) {
		// PC 以 \r\n 结尾
		// Unix 以 \n 结尾
		// dos2unix 是在末尾把 \r 去掉 所以 文件会变小
		// unix2dos 是在文件末尾把 \n 之前加上 \r\n 所以文件会变大
//    	System.setProperty(“line.separator", "\r\n")"
		String endTagDefault = "\n"; // 默认是 Linux下的 换行符
		if (CUR_OS_TYPE == OS_TYPE.Windows) {
			endTagDefault = "\r\n"; // 如果操作系统是 Windows 那么改变换行符为 \r\n
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

	static ArrayList<File> getAllSubFile(File dirFile, String aospPath, ArrayList<String> typeList) {
		if (typeList == null) {
			typeList = new ArrayList<String>();
			typeList.add("*");
		}
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
						if ("*".equals(type)) { // 如果 类型是 * 那么就把 所有的 非目录文件加入列表中
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

	static void showNoTypeTip(int ruleIndex) {

		System.out.println("当前用户输入的 ruleIndex = " + ruleIndex + "  操作type无法检测到-它是一个int值  \n type 必须是当前 " + Cur_Bat_Name
				+ " 的 第一个输入参数中的第一个int值 ");
		System.out.println("请检查输入参数后重新执行命令!");

	}

	static void showTip() {
		System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
		System.out.println("当前已实现的替换逻辑如下:\n");

		int count = 1;
		System.out.println("═══════════════════" + "使用方法列表 Begin" + "═══════════════════" + "\n");
		for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
			Rule itemRule = CUR_RULE_LIST.get(i);
			String type = itemRule.file_type;
			int index = itemRule.rule_index;
			String desc = itemRule.ruleTip(type, index, Cur_Bat_Name, CUR_OS_TYPE);

			/*
			 * String itemDesc = ""; if(CUR_OS_TYPE == OS_TYPE.Windows){ itemDesc =
			 * "zrule_apply_I9.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
			 * }else{ itemDesc = "zrule_apply_I9 "+type+"_"+index +
			 * "    [索引 "+count+"]  描述:"+desc; }
			 */
			System.out.println(desc + "\n");
			count++;
		}
		System.out.println("═══════════════════" + "使用方法列表 End " + "═══════════════════" + "\n");

		if (isFreshDefault_SelectedIndex) { // 当前输入的参数 有更新 default 的操作
			if (!"".equals(FreshDefaultInputStr)) {
				String fixedInputStr = FreshDefaultInputStr.replace("default_index_", "");
				fixedInputStr = fixedInputStr.replace(" ", "");
				if (isNumeric(fixedInputStr)) {
					int curIndex = Integer.parseInt(fixedInputStr);
					if (curIndex <= 0 || curIndex > CUR_RULE_LIST.size()) {
						System.out.println(" 当前输入的 default_index  不在已定义的规则索引序列中 当前索引参数: " + FreshDefaultInputStr);
						System.out.println(" 可选default_index 范围 [ 1 :" + CUR_RULE_LIST.size() + " ]    请检查输入的规则索引!");
					} else {

						CUR_TYPE_INDEX = curIndex;
						I9_Properties.setProperty(Default_Selected_Rule_Index_Key, "" + CUR_TYPE_INDEX);
						setProperity();
//                        System.out.println(" 设置新的 默认 Index 成功   Default_Selected_Index = "+ CUR_TYPE_INDEX );
					}
				} else {
					System.out.println("当前过滤出的 default_index 不为数值  请检查输入!");
				}
			} else {
				System.out.println(
						"当前 输入的 更新 default_index 的参数参数没有获取到 请重新执行!  FreshDefaultInputStr = " + FreshDefaultInputStr);

			}

		}

		System.out.println("当前默认选中的 default_index_" + CUR_TYPE_INDEX);

		String defaultSelectedStr = "【* 默认】";
		for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
			Rule itemRule = CUR_RULE_LIST.get(i);
			String desc = itemRule.simpleDesc();
			String curInfoitem = " default_index_" + (i + 1) + "    "
					+ ((i + 1) == CUR_TYPE_INDEX ? defaultSelectedStr : "  ") + "  ## Desc:" + desc;
			if (!curInfoitem.contains(defaultSelectedStr)) {
				curInfoitem = curInfoitem.replace("##", "        ##");
			}
			System.out.println(Cur_Bat_Name + curInfoitem);
		}

		// 显示 当前默认的 default_index=1
		// ztextrule_operation_I9.bat default_index = 1 ## Desc:
		// ztextrule_operation_I9.bat default_index=2 【* 默认】 ## Desc:
		// ztextrule_operation_I9.bat default_index= 3 ## Desc:

	}

	static int calculInputTypeIndex(String inputParams) {

		File absFile_B = new File(inputParams);
		if (absFile_B.exists() && absFile_B.isFile()) {
			First_Input_RealFile = absFile_B;
			System.out.println("inputParams  = " + inputParams + " absFilePath = " + inputParams);
			System.out.println(" First_Input_RealFile  = " + First_Input_RealFile.getAbsolutePath());
			return 0;
		}

		if (inputParams == null) {
			return 0;
		}

		String absFilePath = CUR_Dir_1_PATH + File.separator + inputParams;
		File absFile = new File(absFilePath);

//        String absFilePath_B =  inputParams;

		if (absFile.exists() && absFile.isDirectory()) {
			First_Input_Dir = absFile;
			return 0; // 如果输入的参数 和 shell目录 组成一个 存在的文件的话 那么说明 参数不是 选择 rule的参数
		}

		if (isNumeric(inputParams)) {
			return Integer.parseInt(inputParams);
		}

		if (inputParams.contains("_")) {
			String[] mTypeParamArr = inputParams.split("_");
			if (mTypeParamArr.length == 0) {
				return 0;
			}

			for (int i = 0; i < mTypeParamArr.length; i++) {
				String curPositionStr = mTypeParamArr[i];
				if (isNumeric(curPositionStr)) {
					return Integer.parseInt(curPositionStr);
				}
			}
		}

		if (First_Input_RealFile == null) {
			System.out.println("═══════温馨提示═════════");
			System.out.println("温馨提示 如果在 notepad++ F8 环境中 , First_Input_RealFile 没有取得对应的文件");
			System.out.println("可能是由于当前文件名称中含有 空格 导致 输入参数错误!");
			System.out.println("════════════════");
		}
		return 0;

	}

	static void addCurFileTypeMapItemWithKey(String keyType, File curFile) {
		if (CUR_Dir_FILETypeMap.containsKey(keyType)) {
			ArrayList<File> fileList = CUR_Dir_FILETypeMap.get(keyType);
			fileList.add(curFile);
		} else {
			ArrayList<File> fileList = new ArrayList<File>();
			fileList.add(curFile);
			CUR_Dir_FILETypeMap.put(keyType, fileList);
		}
	}

	static void initFileTypeMap(ArrayList<File> subFileList) {
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

	static Rule getRuleByIndex(int index) {
		for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
			if (CUR_RULE_LIST.get(i).rule_index == index) {
				return CUR_RULE_LIST.get(i);
			}
		}
		return null;
	}

	static Rule getRuleByIdentify(String identify) {
		for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
			if (CUR_RULE_LIST.get(i).identify.equals(identify)) {
				return CUR_RULE_LIST.get(i);
			}
		}
		return null;
	}

	// ArrayPrint ==============================Begin

	static int MAX_COUNT_CHAR_IN_ROW = 140;
	static int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 140;

	static public void printArrObject(Object[] objArr, String title) {
		ArrayList<String> curPropStrArr = new ArrayList<String>();
		for (int i = 0; i < objArr.length; i++) {
			if ("".equals(objArr[i].toString())) {
				continue;
			}
			curPropStrArr.add(objArr[i].toString());
		}
		ArrayPrint(curPropStrArr, title);
	}

	public static boolean isItemLengthOver100(ArrayList<String> mStrList) {
		boolean flag = false;

		for (int i = 0; i < mStrList.size(); i++) {
			if (mStrList.get(i).length() > MAX_COUNT_CHAR_IN_ROW) {
				// System.out.println("index["+i+"] size= "+mStrList.get(i).length()+" Value:" +
				// mStrList.get(i) );
				return true;
			}
		}
		return flag;

	}

	public static ArrayList<String> makeStringGroup(String code, int maxcount) {
		ArrayList<String> fixArr = new ArrayList<String>();
		String oriStr = code.trim();
		while (oriStr.length() > maxcount) {
			String str1 = oriStr.substring(0, maxcount);
			fixArr.add(str1);
			oriStr = oriStr.substring(maxcount);
		}

		return fixArr;
	}

	public static ArrayList<String> sqlitString(String bigString, String sqlitChar) {
		ArrayList<String> fixArr = new ArrayList<String>();
		ArrayList<String> subArr = new ArrayList<String>();
		String[] strArr = bigString.trim().split(sqlitChar.trim());
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].length() > MAX_COUNT_CHAR_IN_ROW) {
				ArrayList<String> subArrA = null;
				if (strArr[i].contains("【") && strArr[i].contains("】")) {
					subArrA = toSqlitWithhardBlock(strArr[i]);
				} else if (strArr[i].contains(";")) {
					subArrA = sqlitString(strArr[i], ";");

				} else if (strArr[i].contains("。")) {
					subArrA = sqlitString(strArr[i], "。");

				} else if (strArr[i].contains(":")) {
					subArrA = sqlitString(strArr[i], ":");
				} else if (strArr[i].contains(".")) {
					subArrA = sqlitString(strArr[i], ".");
				} else if (strArr[i].contains(" ")) {
					subArrA = sqlitString(strArr[i], " ");
				} else {
					// 对于超过最大长度 并且没有特殊字符的 每 80刀一分割 把它分割
					ArrayList<String> tempArr = makeStringGroup(strArr[i], MAX_COUNT_CHAR_IN_ROW);
					for (int j = 0; j < tempArr.size(); j++) {
						fixArr.add(tempArr.get(j));
					}

				}

				if (subArrA != null && isItemLengthOver100(subArrA)) {
					String fixSub = strArr[i].substring(0, MAX_COUNT_CHAR_IN_ROW);
					fixArr.add(fixSub);
				} else {
					if (subArrA != null) {
						for (int j = 0; j < subArrA.size(); j++) {
							fixArr.add(subArrA.get(j));
						}

					}
				}

			} else {
				fixArr.add(strArr[i]);
			}
		}
		return fixArr;
	}

	public static ArrayList<String> toSqlitWithhardBlock(String mStrList) {
		ArrayList<String> resultList = new ArrayList<String>();
		// 【】 【】,
		String mStr = mStrList.trim();

		String pre = mStr.substring(0, mStr.indexOf("【"));
		mStr = mStr.substring(mStr.indexOf("【"));
		resultList.add(pre);
		String end = "";
		if (mStr.endsWith("】")) {
			end = "";
		} else {
			end = mStr.substring(mStr.lastIndexOf("】") + 1);
		}

		mStr = mStr.substring(0, mStr.lastIndexOf("】") + 1);

		while (mStr.contains("】") && mStr.contains("【")) {
			String firstStr = mStr.substring(mStr.indexOf("【"), mStr.indexOf("】") + 1);
			resultList.add(firstStr);
			mStr = mStr.substring(mStr.indexOf("】") + 1);
		}

		if (!"".equals(mStr.trim())) {
			resultList.add(mStr.trim());
		}

		if (!"".equals(end)) {
			resultList.add(end);
		}

//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println("xxx："+i+"  ="+resultList.get(i) +"   mStr="+mStr);
//        }
		return resultList;
	}

	public static ArrayList<String> toMakeListItemLess100(ArrayList<String> mStrList, int maxcount) {
		ArrayList<String> fixLengthArr = new ArrayList<String>();

		for (int i = 0; i < mStrList.size(); i++) {
			if (mStrList.get(i).length() < MAX_COUNT_CHAR_IN_ROW) {
				fixLengthArr.add(mStrList.get(i));
			} else {
				String curMaxStr = mStrList.get(i);
				ArrayList<String> fixA = null;
				if (curMaxStr.contains("【") && curMaxStr.contains("】")) {
					fixA = toSqlitWithhardBlock(curMaxStr);
				} else if (curMaxStr.contains(";")) {
					fixA = sqlitString(curMaxStr, ";");
				} else if (curMaxStr.contains("。")) {
					fixA = sqlitString(curMaxStr, "。");
				} else if (curMaxStr.contains(":")) {
					fixA = sqlitString(curMaxStr, ":");
				} else if (curMaxStr.contains(".")) {
					fixA = sqlitString(curMaxStr, ".");
				} else if (curMaxStr.contains(" ")) {
					fixA = sqlitString(curMaxStr, " ");
				} else {
					// 对于超过最大长度 并且没有特殊字符的 每 80刀一分割 把它分割
					ArrayList<String> tempArr = makeStringGroup(curMaxStr, MAX_COUNT_CHAR_IN_ROW);
					for (int j = 0; j < tempArr.size(); j++) {
						fixLengthArr.add(tempArr.get(j));
					}
				}

				if (fixA != null) {
					// System.out.println(" fixA.size="+ fixA.size());
					for (int j = 0; j < fixA.size(); j++) {
						// System.out.println(" fixA.size="+ fixA.size() + " i="+j+"
						// value:"+fixA.get(j));
					}
				} else {
					// System.out.println(" fixA.size= null!");
				}
				if (isItemLengthOver100(fixA)) {
					String fixSub = curMaxStr.substring(0, MAX_COUNT_CHAR_IN_ROW);
					fixLengthArr.add(fixSub);
				} else {
					if (fixA != null) {
						for (int j = 0; j < fixA.size(); j++) {
							fixLengthArr.add(fixA.get(j));
						}
					}
				}

			}
		}

		return fixLengthArr;
	}

	public static int getItemMaxLength(ArrayList<String> mStrList) {
		int itemLength = 0;

		for (int i = 0; i < mStrList.size(); i++) {
			if (mStrList.get(i).length() > itemLength) {
				itemLength = mStrList.get(i).length();
			}

		}
		return itemLength;
	}

	public static ArrayList<String> fixStrArrMethodCommon100(ArrayList<String> mStrList, int maxcount) {
		ArrayList<String> fixArr = new ArrayList<String>();
		for (int i = 0; i < mStrList.size(); i++) {
			String curStr = mStrList.get(i);
			String fixCurStr = getFixLengthNewStr(curStr, maxcount);
			fixArr.add(fixCurStr);
		}

		return fixArr;
	}

	public static String getFixLengthNewStr(String oriStr, int maxLength) {
		String fixStr = "";
		String beginChar = "│ ";
		String endChar = "│";
		String oriStrTrim = oriStr.trim();
		int curLength = oriStrTrim.length();
		int paddingLength = maxLength - curLength;

		int chineseCount = getFrameChineseCount(oriStr);
		paddingLength = paddingLength - chineseCount;
		if (paddingLength < 0) {
			// return "curString:" + oriStr + " length more than" + maxLength;
			return "";
		}

		for (int i = 0; i < paddingLength; i++) {
			oriStrTrim += " ";
		}
		oriStrTrim = beginChar + oriStrTrim + endChar;
		// oriStrTrim = beginChar + oriStrTrim ;
		fixStr = oriStrTrim;
		return fixStr;
	}

	public static int getFrameChineseCount(String oriStr) {
		int count = 0;
		for (int i = 0; i < oriStr.length(); i++) {
			char itemChar = oriStr.charAt(i);
			/*
			 * 
			 * || (itemChar+"").equals("，") || (itemChar+"").equals("’") ||
			 * (itemChar+"").equals("‘")
			 * 
			 * || (itemChar+"").equals("；")
			 */
			if ((itemChar + "").equals("：") || (itemChar + "").equals("】") || (itemChar + "").equals("【")
					|| (itemChar + "").equals("）") || (itemChar + "").equals("（") || (itemChar + "").equals("￥")
					|| (itemChar + "").equals("—") || (itemChar + "").equals("？") || (itemChar + "").equals("，")
					|| (itemChar + "").equals("；") || (itemChar + "").equals("！") || (itemChar + "").equals("《")
					|| (itemChar + "").equals("》") || (itemChar + "").equals("。") || (itemChar + "").equals("、")) {
				count++;
				continue;
			}
			if ((itemChar + "").equals("\t")) {
				count = count + 4;
				continue;
			}

			boolean isChinese = isFrameContainChinese(itemChar + "");
			if (isChinese) {
				count++;
			}
		}
		return count;
	}

	public static boolean isFrameContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static void showTableLogCommon100(ArrayList<String> mStrList, String title) {
		int maxLength = getItemMaxLength(mStrList);
		ArrayList<String> fixStrArr = fixStrArrMethodCommon100(mStrList, MAX_COUNT_CHAR_IN_ROW);
		int chineseCount = getFrameChineseCount(title);

		String beginRow = "┌──────────────────" + title + "──────────────────┐";
		String endRow = "└──────────────────┘";
		int fixLength = 0;
		int oriLength = title.length();
		if (chineseCount == 0) { // 不包含汉字
			fixLength = oriLength;

		} else {
			if (chineseCount == oriLength) { // 全部包含汉字
				fixLength = 2 * oriLength;
			} else { // 一部分汉字 一部分英语

				fixLength = oriLength - chineseCount + (2 * chineseCount);
			}

		}
		String templateString = "┐";
		if (fixLength > 0) {
			for (int i = 0; i < fixLength; i++) {
				templateString = "─" + templateString;
			}
		}

		beginRow = beginRow.replace(templateString, "┐");
		// System.out.println(" fixStrArr.size() =" + fixStrArr.size());
		beginRow = resetBeginRowToDefaultSize(beginRow);
		System.out.println(beginRow);
		for (int i = 0; i < fixStrArr.size(); i++) {
			System.out.println(fixStrArr.get(i));
		}
		endRow = resetEndRowToDefaultSize(endRow);
		System.out.println(endRow);
	}

	static String resetEndRowToDefaultSize(String beginRow) {
		String curBeginStr = new String(beginRow);
		int curPaddingLength = getFramePaddingChineseLength(curBeginStr);
		int distance = 0;
		if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
			distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
		}
		String paddingString = getRepeatString("─", distance + 3);
		curBeginStr = curBeginStr.replace("┘", paddingString + "┘");
		return curBeginStr;
	}

	public static int getFramePaddingChineseLength(String oriStr) {
		int resultLength = 0;
		int oriSize = oriStr.length();
		int chinseSize = getFrameChineseCount(oriStr); // 所有中文的个数
		int distanceSize = oriSize - chinseSize; // 所有英文的个数
		resultLength = chinseSize * 2 + distanceSize;
		return resultLength;

	}

	static String getRepeatString(String repeatSrc, int repeatCount) {
		String src = "";
		for (int i = 0; i < repeatCount; i++) {
			src += repeatSrc;
		}
		return src;
	}

	static String resetBeginRowToDefaultSize(String beginRow) {
		String curBeginStr = new String(beginRow);
		int curPaddingLength = getFramePaddingChineseLength(curBeginStr);
		int distance = 0;
		if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
			distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
		}
		String paddingString = getRepeatString("─", distance + 3);
		curBeginStr = curBeginStr.replace("┐", paddingString + "┐");
		return curBeginStr;
	}

	public static void ArrayPrint(ArrayList<String> mStrList, String title) {

		ArrayList<String> addMao = CheckAndAddMaoMethod(mStrList);
		// 对mStrList 进行 对其处理 重新转换为 对其的 ArrayList<String> new
		// 1. 判断所有字符串中 第一次出现冒号的位置 查找出最大的位置的那个 并 记录这个最大位置 xMaxLengh
		// 2. 重新排序的规则是 小字符串需要在: 之后添加 xMaxLengh - self().length 的空格 并重新加入新的数组
		ArrayList<String> firstFixedStringArrA = firstFixedStringArr(addMao);
		boolean isOver100 = isItemLengthOver100(firstFixedStringArrA);

		if (isOver100) {
			// System.out.println("当前的字符串Item 存在大于 100字符的！");
			ArrayList<String> newLessList = toMakeListItemLess100(firstFixedStringArrA, MAX_COUNT_CHAR_IN_ROW);
			showTableLogCommon100(newLessList, title); // 每一行都小于100个字的打印
		} else { //
			// System.out.println("当前的字符串Item 不 存在大于 100字符的！");
			showTableLogCommon100(firstFixedStringArrA, title); // 每一行都小于100个字的打印

		}
	}

	public static String getEmptyString(int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			str += " ";
		}
		return str;
	}

	public static String getPaddingEmptyString(int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			str += "-";
		}
		return str;
	}

	// 加载库时搜索的路径列表AC-:\Program Files\Java\jdk1.8.0_191\bin
	// 加载库时搜索的路径列表A-:C\Program Files\Java\jdk1.8.0_191\bin
	public static String addMaoChinese(String oriStr) {
		String resultStr = "";
		int chinesePosition = getFirstChinesePosition(oriStr);
		resultStr = oriStr.substring(0, chinesePosition) + ":" + oriStr.substring(chinesePosition);
		return resultStr;
	}

	public static int getFirstChinesePosition(String str) {
		int position = 0;
		boolean getFirstChinese = false;
		char[] newChar = str.toCharArray(); // 转为单个字符
		for (int i = 0; i < newChar.length; i++) {
			char curChar = newChar[i];
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher m = p.matcher(curChar + "");
			if (m.find()) {
				getFirstChinese = true;
				position = i;
			} else {
				if (getFirstChinese == true) {
					return i;
				}
			}

		}
		return position;
	}

	public static String addMaoBlank(String oriStr) {
		String resultStr = "";
		int blankPosition = oriStr.indexOf(" ");
		resultStr = oriStr.substring(0, blankPosition) + ":" + oriStr.substring(blankPosition);
		return resultStr;
	}

	public static ArrayList<String> CheckAndAddMaoMethod(ArrayList<String> mStrList) {
		ArrayList<String> fixedArr = new ArrayList<String>();
		for (int i = 0; i < mStrList.size(); i++) {
			String curItem = mStrList.get(i);
			if (isCommonMao(curItem)) {
				fixedArr.add(curItem);
			} else {
				// 对于那些没有冒号的 字段的处理
				// 1.如果包含汉字 那么就在 汉字的最后添加一个冒号
				// 2. 如果字符串中有空格 并且第一个空格的位置小于 (MAX_COUNT_CHAR_IN_ROW/2) 那么把它换成冒号
				if (isFrameContainChinese(curItem)) {
					String addMaoStr = addMaoChinese(curItem);
					fixedArr.add(addMaoStr);
				} else if (curItem.contains(" ") && curItem.indexOf(" ") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
					String addMaoStr = addMaoBlank(curItem);
					fixedArr.add(addMaoStr);
				} else { // 如果以上条件都不满足 那么就在字符串最前面添加一个冒号

					fixedArr.add(":" + curItem);

				}

			}

		}
		return fixedArr;
	}

	// 存在冒号 并且 冒号的位置小于 总的一行字数的一半长度 返回true
	public static boolean isCommonMao(String oriStr) {
		boolean flag = false;
		if (oriStr.contains(":") && oriStr.indexOf(":") == oriStr.lastIndexOf(":")) {
			flag = true; // 只有一个冒号
		} else if (oriStr.contains(":") && oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.indexOf(":\\")
				&& oriStr.indexOf(":") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
			flag = true; // 多个冒号 并且 第一个冒号 : 在 :\ 之前
		} else if (oriStr.contains(":") && !oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.lastIndexOf(":")) {
			flag = true; // 多个冒号
		}
		return flag;
	}

	public static ArrayList<String> firstFixedStringArr(ArrayList<String> mStrList) {
		ArrayList<String> fixedArr = new ArrayList<String>();
		int maxMaoPosition = getMaxMaoPosition(mStrList);
		for (int i = 0; i < mStrList.size(); i++) {
			String curItem = mStrList.get(i);
			int curMaoPosition = curItem.indexOf(":");
			String pre = curItem.substring(0, curMaoPosition);
			String end = curItem.substring(curMaoPosition); // 去掉:
			int preLength = getFramePaddingChineseLength(pre); // 中文对齐 取中文长度
			// 需要达到一样的ChineseLength
			// int padding_chinese = getFrameChineseCount(pre);
			String padding = "";
			if (preLength <= maxMaoPosition) {
				int paddingLength = maxMaoPosition - preLength;
				padding = getPaddingEmptyString(paddingLength);
			}
			String fixedItem = pre + padding + end;
			fixedArr.add(fixedItem);

		}
		return fixedArr;
	}

	public static int getMaxMaoPosition(ArrayList<String> mStrList) {
		int maoPosition = 0;
		String maxString = "";
		for (int i = 0; i < mStrList.size(); i++) {
			if ((mStrList.get(i).contains(":"))) {
				int curMaoPosition = mStrList.get(i).indexOf(":");
				String maoString = mStrList.get(i).substring(0, curMaoPosition + 1);
				int paddingSize = getFramePaddingChineseLength(maoString);
				if (paddingSize > maoPosition) {
					maoPosition = paddingSize;
					maxString = mStrList.get(i);
				}
			}

		}
		// System.out.println("最长的冒号位置: maoPosition="+maoPosition+" string="+maxString);
		return maoPosition;
	}
	// ArrayPrint ==============================End

	public static String execCMD(String command) {
//        System.out.println("══════════════Begin ExE ");
		StringBuilder sb = new StringBuilder();
		StringBuilder errorSb = new StringBuilder();
		try {

//            Process process = Runtime.getRuntime().exec("CMD.exe /c start  " + command);
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
			sb.append("execCMD 出现异常! ");
			return sb.toString();
		}

//        System.out.println("sb.toString() = "+ sb.toString());
//        System.out.println("══════════════End ExE ");
		return sb.toString();
	}

	static String getTimeStamp() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getTimeStampHHmmss() {

		SimpleDateFormat df = new SimpleDateFormat("HHmmss");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getTimeStampMMdd() {

		SimpleDateFormat df = new SimpleDateFormat("MMdd");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getTimeStampLong() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static File getDirNewestFileWithPointTypeList(File dirFile, ArrayList<String> typeList) {
		File newImageFile = null;

		long max_time = 0;
		File[] allSubFileList = dirFile.listFiles();
		for (int i = 0; i < allSubFileList.length; i++) {
			File fileItem = allSubFileList[i];
			String type = getFileTypeWithPoint(fileItem).toLowerCase();
			if (typeList == null || typeList.contains(type)) {
				long mFileTimeStamp = getFileCreateTime(fileItem.getAbsolutePath());
				if (mFileTimeStamp > max_time) {
					max_time = mFileTimeStamp;
					newImageFile = fileItem;
				}
			}
		}

		return newImageFile;
	}

	static Long getFileCreateTime(File fileItem) {
		if (fileItem == null || !fileItem.exists()) {
			return 0L;
		}
		try {
			Path path = Paths.get(fileItem.getAbsolutePath());
			BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class,
					LinkOption.NOFOLLOW_LINKS);
			BasicFileAttributes attr = basicview.readAttributes();
			return attr.creationTime().toMillis();
		} catch (Exception e) {
			e.printStackTrace();
			return fileItem.lastModified();
		}
	}

	static Long getFileModifyTime(File fileItem) {
		try {
			Path path = Paths.get(fileItem.getAbsolutePath());
			BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class,
					LinkOption.NOFOLLOW_LINKS);
			BasicFileAttributes attr = basicview.readAttributes();
			return attr.lastModifiedTime().toMillis();
		} catch (Exception e) {
			e.printStackTrace();
			return fileItem.lastModified();
		}
	}

	static Long getFileCreateTime(String filePath) {
		File file = new File(filePath);
		try {
			Path path = Paths.get(filePath);
			BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class,
					LinkOption.NOFOLLOW_LINKS);
			BasicFileAttributes attr = basicview.readAttributes();
			return attr.creationTime().toMillis();
		} catch (Exception e) {
			e.printStackTrace();
			return file.lastModified();
		}
	}

	public static String getFileNameNoPoint(File file) {
		return getFileNameNoPoint(file.getAbsolutePath());
	}

	public static String getFileTypeWithPoint(File file) {

		return getFileTypeWithPoint(file.getAbsolutePath());
	}

	public static String getFileNameNoPoint(String fileName) {
		String name = "";
		if (fileName.contains(".")) {
			name = fileName.substring(0, fileName.lastIndexOf(".")).trim().toLowerCase();
		} else {
			name = new String(fileName);
		}
		return name.toLowerCase().trim();
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

	public static String getFileTypeWithPoint(String fileName) {
		String name = "";
		if (fileName.contains(".")) {
			name = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
		} else {
			name = "";
		}
		return name.toLowerCase().trim();
	}

	public static void openImageFile(File imageFile) {
		if (CUR_OS_TYPE == OS_TYPE.Windows) {
			try {
				Runtime.getRuntime().exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  "
						+ imageFile.getAbsolutePath());

			} catch (Exception e) {

			}
		}

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

	public static boolean isValidUrl_String(String urlString) {
		boolean flag = false;
		if (urlString.startsWith("http:")) {
			flag = true;
		} else if (urlString.startsWith("https:")) {
			flag = true;
		} else if (urlString.startsWith("www.")) {
			flag = true;
		}

		return flag;
	}

	public static boolean isValidUrl(String urlString) {
		boolean flag = false;
		long lo = System.currentTimeMillis();
		URL url;
		try {
			url = new URL(urlString);
			URLConnection co = url.openConnection();
			co.setConnectTimeout(5000);
			co.connect();
			flag = true;
			System.out.println("连接可用");
		} catch (Exception e1) {
			System.out.println("连接打不开!");
			url = null;
			flag = false;
		}

		return flag;
	}

	public static void main(String[] args) {

		initSystemInfo();

		initInputParams(args);

		I9_TextRuleOperation mI9_Object = new I9_TextRuleOperation();
		mI9_Object.InitRule();
		// 用户没有输入参数
		if (CUR_INPUT_3_ParamStrList.size() == 0 && !allowEmptyInputParam) {
			showTip();
			return;
		}

		// 默认的索引同时也被修改 没有获得 当前 适配的规则索引
		if (CUR_TYPE_INDEX <= 0 || CUR_TYPE_INDEX > CUR_RULE_LIST.size()) {
			showNoTypeTip(CUR_TYPE_INDEX);
			return;
		}

		System.out.println("Default_Selected_Index  = " + CUR_TYPE_INDEX);

		CUR_Selected_Rule = getRuleByIndex(CUR_TYPE_INDEX); // 获取用户选中的 规则

		// 让各个规则自己去检测 自己需要的参数是否得到满足 并自己提示 给出 1.当前cmd路径下的文件 2.typeIndex 字符串 3.之后的输入参数
		if (CUR_Selected_Rule == null
				|| !CUR_Selected_Rule.checkParamsOK(CUR_Dir_FILE, CUR_TYPE_2_ParamsStr, CUR_INPUT_3_ParamStrList)) {
			if(CUR_Selected_Rule != null) {
				CUR_Selected_Rule.showWrongMessage(); // 提示当前规则的错误信息
			}
			
			System.out.println("当前输入参数可能 不能拼接成一个文件! 请检查输入参数!");
			return;
		}

		if (!CUR_Dir_FILE.exists() || !CUR_Dir_FILE.isDirectory()) {
			System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH + "  不存在! ");
			return;
		}

		/*
		 * if(!checkInputParamsOK()){ System.out.
		 * println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
		 * return; }
		 */

		CUR_Selected_Rule.operationRule(CUR_INPUT_3_ParamStrList); // 传递参数列表 进行处理
		I9_Properties.getProperty(Default_Selected_Rule_Index_Key, "" + CUR_TYPE_INDEX);
		setProperity();

	}

	public static int getImageHigh(File picture) {
		int high = 0;
		ImageIcon imageIcon = new ImageIcon(picture.getAbsolutePath());
		high = imageIcon.getIconHeight();

		return high;
	}

	public static int getImageWidth(File picture) {
		int width = 0;
		ImageIcon imageIcon = new ImageIcon(picture.getAbsolutePath());
		width = imageIcon.getIconWidth();
		return width;
	}

	// 对齐行的操作 主要处理从浏览器复制到的表格数据 不对齐
	ArrayList<String> duiqi_hang_Rule_5(ArrayList<String> originStrList) {
		if (originStrList == null)
			return null;
		ArrayList<String> fixedStrArr = new ArrayList<String>();
		for (int i = 0; i < originStrList.size(); i++) {
			String onrLineStr = originStrList.get(i).trim();
			if ("".equals(onrLineStr.trim())) {
				continue;
			}
			String onrLineStr_clearChinese = clearChinese(onrLineStr);
			fixedStrArr.add(onrLineStr_clearChinese);
		}
		System.out.println("═════════════" + "输入到文件的新内容 Begin 如下:" + "═════════════");
		for (int i = 0; i < fixedStrArr.size(); i++) {
			System.out.println(fixedStrArr.get(i));
		}
		System.out.println("═════════════" + "输入到文件的新内容 End" + "═════════════");

		return fixedStrArr;
	}

	ArrayList<String> clearChinese_Rule_4(ArrayList<String> originStrList) {
		if (originStrList == null)
			return null;
		ArrayList<String> fixedStrArr = new ArrayList<String>();
		for (int i = 0; i < originStrList.size(); i++) {
			String onrLineStr = originStrList.get(i).trim();
			if ("".equals(onrLineStr.trim())) {
				continue;
			}
			String onrLineStr_clearChinese = clearChinese(onrLineStr);
			fixedStrArr.add(onrLineStr_clearChinese);
		}
		System.out.println("═════════════" + "输入到文件的新内容 Begin 如下:" + "═════════════");
		for (int i = 0; i < fixedStrArr.size(); i++) {
			System.out.println(fixedStrArr.get(i));
		}
		System.out.println("═════════════" + "输入到文件的新内容 End" + "═════════════");

		return fixedStrArr;
	}

	ArrayList<String> addLineNumberChar_Rule_3(ArrayList<String> originStrList) {
		if (originStrList == null)
			return null;
		ArrayList<String> fixedStrArr = new ArrayList<String>();
		int lineNum = 1;
		for (int i = 0; i < originStrList.size(); i++) {
			String onrLineStr = originStrList.get(i).trim();
			if ("".equals(onrLineStr.trim())) {
				continue;
			}
			fixedStrArr.add(lineNum + " " + onrLineStr);
			lineNum++;
		}
		System.out.println("═════════════" + "输入到文件的新内容 Begin 如下:" + "═════════════");
		for (int i = 0; i < fixedStrArr.size(); i++) {
			System.out.println(fixedStrArr.get(i));
		}
		System.out.println("═════════════" + "输入到文件的新内容 End" + "═════════════");

		return fixedStrArr;
	}

	static ArrayList<String> clearOneBlankCharAsOneStr_Rule_2(ArrayList<String> originStrList) {
		if (originStrList == null)
			return null;
		ArrayList<String> fixedStrArr = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < originStrList.size(); i++) {
			String onrLineStr = originStrList.get(i).trim();
			String curFixedLineStr = "";
			if ("".equals(onrLineStr.trim())) {
				continue;
			}
			if (onrLineStr.contains("\t")) {
				curFixedLineStr = (onrLineStr.split("\t")[0].trim());
			} else if (onrLineStr.contains(" ")) {
				curFixedLineStr = (onrLineStr.split(" ")[0].trim());
			} else {
				curFixedLineStr = (onrLineStr);
			}
			curFixedLineStr = curFixedLineStr.replace("\n", "");
			sb.append(curFixedLineStr + "=");
		}

		String allStr = sb.toString().trim();
		while (allStr.endsWith("=")) {
			allStr = allStr.substring(0, allStr.length() - 1);
		}
		fixedStrArr.add("《" + allStr + "》");
		System.out.println("═════════════" + "输入到文件的新内容 Begin 如下:" + "═════════════");
		System.out.println("《" + allStr + "》");
		System.out.println("═════════════" + "输入到文件的新内容 End" + "═════════════");

		return fixedStrArr;
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

	ArrayList<String> clearOneBlankChar_Rule_1(ArrayList<String> originStrList) {
		if (originStrList == null)
			return null;
		ArrayList<String> fixedStrArr = new ArrayList<String>();
		for (int i = 0; i < originStrList.size(); i++) {
			String onrLineStr = originStrList.get(i).trim();
			if ("".equals(onrLineStr.trim())) {
				continue;
			}
			if (onrLineStr.contains("\t")) {
				fixedStrArr.add(onrLineStr.split("\t")[0].trim());
			} else if (onrLineStr.contains(" ")) {
				fixedStrArr.add(onrLineStr.split(" ")[0].trim());
			} else {
				fixedStrArr.add(onrLineStr);
			}
		}
		System.out.println("═════════════" + "输入到文件的新内容 Begin 如下:" + "═════════════");
		for (int i = 0; i < fixedStrArr.size(); i++) {
			System.out.println(fixedStrArr.get(i));
		}
		System.out.println("═════════════" + "输入到文件的新内容 End" + "═════════════");

		return fixedStrArr;
	}

	// 自动判断输入文件中的一行有多少列字符串
	public static int Rule5_NUM_ERERY_LINE_OLD = 0; // 输入1.txt文件原本的每行列数 限制条件为 NEW/OLD 是正整数
	public static int Rule5_NUM_ERERY_LINE_NEW = 0; // 输出2.txt 文件需要自定义的产生的每行列数

	public static final int Rule5_PADDING_COLOM = 5; // 填充padding的距离 每个列之间的间距
	public static final boolean Rule5_NEED_SORT = false; // 输出是否需要进行A-Z的排序 默认为false 默认为按照1.txt的读取顺序显示
	public static final boolean Rule5_DEBUG_LOG = true; // 是否打印Log标识

	public static String Rule5_SRC_FILE_NAME = "1.txt"; // 输入文件1.txt
	public static String Rule5_DES_FILE_NAME = "2.txt"; // 输出文件1.txt

	public static String[] Rule5_splitArr; // 读取输入 1.txt每行 原始的split返回值字符串数组
	public static String[] Rule5_retContentArr; // 读取输入 1.txt每行 原始的split返回值经过过滤规格过滤后的返回值字符串数组

	static String Rule5_Split_Str = " ";
	public static long Rule5_fileSumLines; // 输入文件1.txt 的总行数
	public static long Rule5_newSumLines; // 输出文件 2.txt 的总行数
	public static long Rule5_stringNumOfInput_Max; // 输入和输出文件中字符串的最大的个数

	public static int[] Rule5_item_Max_Length = new int[Rule5_NUM_ERERY_LINE_NEW]; // 在1.txt输入文件每个列中字符串的最大长度的数组 默认为0
	public static int[] Rule5_item_Max_Length_new = new int[Rule5_NUM_ERERY_LINE_NEW]; // 在2.txt文件中每个列的字符串最大长度
																						// 不足的补充padding

	public static ArrayList<String> duiqi_Rule_5(File srcFile, File targetFile) {

//        String mFilePath = System.getProperties().getProperty("user.dir")+File.separator+"1.txt";

		Rule5_SRC_FILE_NAME = srcFile.getAbsolutePath();
		Rule5_DES_FILE_NAME = targetFile.getAbsolutePath();
		System.out.println("Rule5_DES_FILE_NAME =  " + Rule5_DES_FILE_NAME);

		getLineRow(srcFile); // 获得当前输入的数据统计
		if (Rule5_NUM_ERERY_LINE_NEW == 0 || Rule5_NUM_ERERY_LINE_OLD == 0) {
			System.out.println("当前文件的列数检测失败  程序以失败姿态退出！ ");
			return null;
		} else {
			Rule5_item_Max_Length = new int[Rule5_NUM_ERERY_LINE_NEW]; // 在1.txt输入文件每个列中字符串的最大长度的数组 默认为0
			Rule5_item_Max_Length_new = new int[Rule5_NUM_ERERY_LINE_NEW]; // 在2.txt文件中每个列的字符串最大长度 不足的补充padding
		}
		getLineNum(srcFile); // 获得当前输入的数据统计

		try {

			// 链表数组 包含的是上面 LinkedList<String[]> 中的每一个String，这些String已经排序排好了
			LinkedList<String> sortStringlist = getAllStringItemFromInput(srcFile);

			// 依据标识位 对 所有的String 进行排序
			sortStringlist = sortAllStringItemMethod(sortStringlist);

			// 链表数组 成员都是 每一行字符串进行split分割后产生的字符串数组 并且每个Item 对应的String[] 长度是
			// Rule5_NUM_ERERY_LINE_NEW
			LinkedList<String[]> list_StringArr = new LinkedList<String[]>();

			// 填充输入到2.txt中的字符串数组的List
			fill_List_StringArr(list_StringArr, sortStringlist);

			// list_StringArr.length 就是 2.txt输出文件的行数
			System.out.println("list_StringArr.length 输出文件  总行数:" + list_StringArr.size());

			// int[] Rule5_item_Max_Length 数组进行查找 找到每列最大的字符串长度
			getStringMaxLengthMethod(list_StringArr);

			// 创建2.txt 并填充数据
			return calcul_duiqi_Rule_5(list_StringArr);

		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
		}
		return null;

	}

	public static void fill_List_StringArr(LinkedList<String[]> list_StringArr, LinkedList<String> sortStringlist) {
		String[] newRow = new String[Rule5_NUM_ERERY_LINE_NEW];

		for (int i = 0; i < sortStringlist.size(); i++) {

			int index = i % Rule5_NUM_ERERY_LINE_NEW;
			if (index == 0 && i > 0) {
				list_StringArr.add(newRow);
				newRow = new String[Rule5_NUM_ERERY_LINE_NEW];
			}
			newRow[index] = sortStringlist.get(i);
		}
		if (!list_StringArr.contains(newRow)) {
			list_StringArr.add(newRow);
		}

	}

	// 对 list_StringArr 内容进行重新填充 到 LinkedList<String[]> list_StringArr
	public static void fixSortInStringArrList(LinkedList<String[]> list_StringArr, LinkedList<String> sortStringlist) {

		int num = 0;
		for (String[] item : list_StringArr) {

			for (int i = 0; i < item.length; i++) {
				if (num + i < sortStringlist.size()) {
					item[i] = sortStringlist.get(num + i);
				}
			}
			num = num + item.length;

		}

	}

	public static String[] getStringArr_From_EveryRow(String contentString, int num) {
		Rule5_retContentArr = new String[num];
		if (contentString != null && !"".equals(contentString)) {

			if (num == 1) {

				Rule5_splitArr = contentString.split(Rule5_Split_Str);
				Rule5_splitArr = makeEmptyOut(Rule5_splitArr); // 把数组中的空字符 完全剔除
				if (Rule5_splitArr.length > num) {

					String contentLine = Rule5_splitArr[0];
					String fixString = fixStringMethod(contentLine);
					Rule5_retContentArr[0] = fixString;
					if (Rule5_DEBUG_LOG)
						System.out.println("只读取每行第一个字符串 = " + Rule5_splitArr[0]);
				}

			}

			if (num == 2) {

				Rule5_splitArr = contentString.split(Rule5_Split_Str);
				Rule5_splitArr = makeEmptyOut(Rule5_splitArr); // 把数组中的空字符 完全剔除
				if (Rule5_splitArr.length > num) {
					Rule5_retContentArr[0] = Rule5_splitArr[0];
					Rule5_retContentArr[1] = Rule5_splitArr[Rule5_splitArr.length - 1];

				} else if (Rule5_splitArr.length == num) {
					Rule5_retContentArr[0] = Rule5_splitArr[0];
					Rule5_retContentArr[1] = Rule5_splitArr[1];
				}

			} else {
				Rule5_splitArr = contentString.split(Rule5_Split_Str);
				if (Rule5_DEBUG_LOG)
					System.out.println("行数大于等于3: 值为“+ num+ ”  切割长度为 Rule5_splitArr.length =" + Rule5_splitArr.length);
				Rule5_splitArr = makeEmptyOut(Rule5_splitArr); // 把数组中的空字符 完全剔除
				for (int x = 0; x < Rule5_splitArr.length; x++) {

					if (Rule5_DEBUG_LOG)
						System.out.println("index =" + x + "   content:" + Rule5_splitArr[x]);
					if (x == Rule5_splitArr.length - 1) {
						if (Rule5_DEBUG_LOG)
							System.out.println();
					}

				}

				if (Rule5_splitArr.length > num) {
					int i = 0;
					int j = 0;
					for (i = 0; i < num; i++) {

						Rule5_retContentArr[i] = Rule5_splitArr[i];

					}
				} else if (Rule5_splitArr.length == num) {
					for (int x = 0; x < Rule5_splitArr.length; x++) {

						Rule5_retContentArr[x] = Rule5_splitArr[x];

					}

				}

			}

		}
		if (Rule5_DEBUG_LOG) {
			for (String value : Rule5_retContentArr) {
				System.out.println("value = " + value);
			}
		}

		return Rule5_retContentArr;
	}

	public static String fixStringMethod(String contentString) {
		int length = contentString.length();
		// System.out.println("contentString1"+ contentString);
		if (contentString.contains("    ")) {
			contentString = contentString.split("    ")[0].trim();
		} else if (contentString.contains("\t")) {
			contentString = contentString.split("\t")[0].trim();
		}
		System.out.println("contentString2  =  " + contentString);
		return contentString;
	}

	public static String[] makeEmptyOut(String[] strArr) {
		String[] validStrArrRet = null;
		ArrayList<String> validStrArr = new ArrayList<String>();

		if (strArr != null) {

			for (String strItem : strArr) {
				if (strItem == null || "".equals(strItem.trim())) {
					continue;
				}
				validStrArr.add(strItem);
			}
		}

		if (validStrArr.size() > 0) {
			validStrArrRet = new String[validStrArr.size()];

			for (int x = 0; x < validStrArr.size(); x++) {
				validStrArrRet[x] = validStrArr.get(x).trim();
			}
		}
		return validStrArrRet;

	}

	public static boolean isArrEmpty(String[] strArr) {
		boolean flag = false;

		if (strArr != null) {
			int i = 0;
			for (i = 0; i < strArr.length; i++) {
				if (strArr[i] != null && "".equals(strArr[i])) {
					flag = true;
					break;
				}
			}
		} else {
			flag = true;
		}
		return flag;
	}

	public static boolean checkInsert(int i, int j) {
		boolean flag = false;
		if (Rule5_retContentArr != null && Rule5_splitArr != null && i < Rule5_retContentArr.length
				&& j < Rule5_splitArr.length) {
			if ("".equals(Rule5_retContentArr[i]) && !"".equals(Rule5_splitArr[j])) {
				flag = true;
			}
		}
		return flag;
	}

	public static LinkedList<String> getAllStringItemFromInput(File srcFile) {

		// 链表数组 包含的是上面 LinkedList<String[]> 中的每一个String，这些String已经排序排好了
		LinkedList<String> sortStringlist = new LinkedList<String>();

		try {

			BufferedReader txtBR = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "utf-8"));

			String lineContentFirst = ""; // 读取到的输入文件 1.txt 的每一行字符串

			// 一次性读出所有的字符串String 然后再重新编排？
			while (lineContentFirst != null) {
				lineContentFirst = txtBR.readLine(); // 从1.txt中读取一行字符串出来
				if (lineContentFirst == null) { // 如果读取到的字符串为null 说明读取到了末尾了
					System.out.println("1.txt read to end!");
					break;
				}
				// 对读取到的每行字符串 进行分拆 得到每一个当前字符串分拆后的数组
				String[] arrStr = getStringArr_From_EveryRow(lineContentFirst, Rule5_NUM_ERERY_LINE_OLD);
				if (arrStr != null && arrStr.length == Rule5_NUM_ERERY_LINE_OLD) {

					for (String strItem : arrStr) {
						sortStringlist.add(strItem); // 包含了所有切分出来的字符串
					}
				}
			}

			txtBR.close();

		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
		}

		return sortStringlist;
	}

	public static LinkedList<String> sortAllStringItemMethod(LinkedList<String> sortStringlist) {

		// sortStringlist.size() 是 2.txt 输出中所有字符串的数量
//        System.out.println("sortStringlist.length :" + sortStringlist.size());
		if (Rule5_NEED_SORT) {
			sortStringlist.sort(new Comparator<String>() { // 对字符串进行排序使得 aA-zZ这样的排序
				@Override
				public int compare(String o1, String o2) {
					return o1.toLowerCase().compareTo(o2.toLowerCase());
				}
			});
		}

		// 打印排序后的字符串
		if (Rule5_DEBUG_LOG) {
			for (String sortItem : sortStringlist) {
				System.out.println("sortItem:" + sortItem);
			}
		}

		return sortStringlist;
	}

	public static void getLineRow(File srcFile) {
		try {

			BufferedReader txtBR = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "utf-8"));
			System.out.println("Rule5_NUM_ERERY_LINE_OLD=" + Rule5_NUM_ERERY_LINE_OLD + "  Rule5_NUM_ERERY_LINE_NEW="
					+ Rule5_NUM_ERERY_LINE_NEW);
			String line = txtBR.readLine();
			int index = 1;
			int rowNum = 0;
			while (line == null) {

				line = txtBR.readLine();
			}

			while (line != null && line.trim().isEmpty()) {
				line = txtBR.readLine();

			}
			System.out.println("line[" + index + "] = " + line);
			index++;
			if (line != null) {
				String numRow[] = null;
				if (line.contains(" ") && !line.contains("\t")) {
					numRow = line.trim().split(" ");
					Rule5_Split_Str = " ";
				} else if (line.contains("\t")) {
					numRow = line.trim().split("\t");
					Rule5_Split_Str = "\t";
				}

				if (numRow != null) {
					for (int i = 0; i < numRow.length; i++) {
						System.out.println("row[" + i + "] = " + numRow[i]);
						if (numRow[i].trim().isEmpty()) {
							continue;
						}

						rowNum++;
					}
					System.out.println("rowNum = " + rowNum);

					Rule5_NUM_ERERY_LINE_OLD = rowNum;
					Rule5_NUM_ERERY_LINE_NEW = rowNum;

				}

			}

			System.out.println("Rule5_NUM_ERERY_LINE_OLD=" + Rule5_NUM_ERERY_LINE_OLD + "  Rule5_NUM_ERERY_LINE_NEW="
					+ Rule5_NUM_ERERY_LINE_NEW);
			txtBR.close();
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
		}
	}

	public static void getLineNum(File srcFile) {
		try {

			BufferedReader txtBR = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "utf-8"));

			Rule5_fileSumLines = txtBR.lines().count(); // 当前输入 1.txt的行数
			// 当前输入 1.txt 所包含该的String字符串最大的数量 也是输入文件2.txt最大的字符串数量

			Rule5_stringNumOfInput_Max = Rule5_fileSumLines * Rule5_NUM_ERERY_LINE_OLD;
			Rule5_newSumLines = (Rule5_stringNumOfInput_Max / Rule5_NUM_ERERY_LINE_NEW) + 1;
			System.out.println("old_txt_lines=" + Rule5_fileSumLines + "  Rule5_newSumLines=" + Rule5_newSumLines
					+ "   AllStringNum = " + Rule5_stringNumOfInput_Max);
			txtBR.close();
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
		}
	}

	public static void getStringMaxLengthMethod(LinkedList<String[]> list_StringArr) {
		if (list_StringArr != null) {
			int num = 0;
			for (String[] item : list_StringArr) { // 计算出每列的最长的字符串的长度
				if (item == null) {
					if (Rule5_DEBUG_LOG)
						System.out.println("item == null");
					continue;
				}
				// System.out.println("item != null index:"+ (num++)
				// +"item.length="+item.length);
				for (int z = 0; z < item.length; z++) {
					if (item[z] == null) {
						if (Rule5_DEBUG_LOG)
							System.out.println("item[z] = null");
						continue;
					}

					if (item[z] != null && item[z].length() > Rule5_item_Max_Length[z]) {
						if (Rule5_DEBUG_LOG)
							System.out.println("item[z].length() = " + item[z].length());
//                             Rule5_item_Max_Length[z] = item[z].length();
						boolean isContainChinese = isContainChinese(item[z]);
						Rule5_item_Max_Length[z] = isContainChinese
								? getFramePaddingChineseLength(item[z]) + item[z].length()
								: item[z].length();

					}
				}

			}

			// 设置2.txt的每一列的长度值
			for (int itemContentLength = 0; itemContentLength < Rule5_item_Max_Length.length; itemContentLength++) {
				Rule5_item_Max_Length_new[itemContentLength] = Rule5_item_Max_Length[itemContentLength]
						+ Rule5_PADDING_COLOM; // 每一列的长度值最长值+1 避免内容重叠
				if (Rule5_DEBUG_LOG)
					System.out.println("Rule5_item_Max_Length_new_index:" + itemContentLength
							+ " Rule5_item_Max_Length_new_value:" + Rule5_item_Max_Length_new[itemContentLength]);
			}

		}

	}

	public static ArrayList<String> calcul_duiqi_Rule_5(LinkedList<String[]> list_StringArr) {
		ArrayList<String> contentList = new ArrayList<String>();
		try {

			// 2.txt的内容进行填充 list_StringArr 中 每一个String【] 是 2.txt 中的一行】
			for (String[] item : list_StringArr) {
				StringBuilder sb = new StringBuilder("");
				if (item == null) {
					if (Rule5_DEBUG_LOG)
						System.out.println("item == null");
					continue;
				}
				if (Rule5_DEBUG_LOG) {
					System.out.println("item != null  item.length=" + item.length);
					int index = 0;
					for (String str : item) {
						System.out.println("item[" + index + "] != null   " + "item[" + index + "]" + str);
						index++;
					}

				}
				for (int z = 0; z < item.length; z++) {
					if (item[z] == null) {
						continue;
					}

					int padding = Rule5_item_Max_Length_new[z] - getFramePaddingChineseLength(item[z]);
//                    int padding = Rule5_item_Max_Length_new[z] - item[z].length();
					String paddingStr = "";
					for (int paddingNum = 0; paddingNum < padding; paddingNum++) {
						paddingStr += " ";
					}
					String content = item[z] + paddingStr;
					sb.append(content);
				}
				contentList.add(sb.toString());
			}
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
		}
		return contentList;
	}

	// Rule_5 对齐 End

	// Rule_6 Begin 竖排列 转为 横竖排列 1xn 转为 4x(n/4)
	public static String Rule6_SRC_FILE_NAME = "";
	// 实现对 竖屏的字符 添加为 横屏排列的字符
	public static int Rule6_rowItemMaxLength = 0; // 最大item的长度
	public static int Rule6_itemSpace = 5;

	public static final ArrayList<String> Rule6_SrcStringArr = new ArrayList<>(); // 源数据的每行数据

	public static final ArrayList<String> Rule6_DstStringArr = new ArrayList<>(); // 目的数据的每行数据

	public static ArrayList<String> ShuZhuangHang_PaiLie_Rule_6(File srcFIle) {

//        String mFilePath = System.getProperties().getProperty("user.dir") + File.separator + "1.txt";

		Rule6_SRC_FILE_NAME = srcFIle.getAbsolutePath();
		getSrcLineArray();
		getMaxItemLength();
		getDstLineArray();
		return getNewStringArr_Rule6();
	}

	public static void getMaxItemLength() {

		for (String item : Rule6_SrcStringArr) {

//            if (item.length() > Rule6_rowItemMaxLength) {
//                Rule6_rowItemMaxLength = item.length();
//                continue;
//            }

			if (getFrameChineseCount(item) > Rule6_rowItemMaxLength) {
				Rule6_rowItemMaxLength = getFramePaddingChineseLength(item);
				continue;
			}

			System.out.println("最大项item的长度:  Rule6_rowItemMaxLength =" + Rule6_rowItemMaxLength);
		}

	}

	public static ArrayList<String> getNewStringArr_Rule6() {
		ArrayList<String> newContent = new ArrayList<String>();
		for (String item : Rule6_DstStringArr) {
			if (!item.isEmpty()) {
				newContent.add(item);
			}
		}

		return newContent;
	}

	public static void getSrcLineArray() {
		try {

			BufferedReader txtBR = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(Rule6_SRC_FILE_NAME)), "utf-8"));
			String lineContentFirst = ""; // 读取到的输入文件 1.txt 的每一行字符串

			// 一次性读出所有的字符串String 然后再重新编排？
			while (lineContentFirst != null) {
				lineContentFirst = txtBR.readLine(); // 从1.txt中读取一行字符串出来
				if (lineContentFirst == null) { // 如果读取到的字符串为null 说明读取到了末尾了
					System.out.println("文件读取完全");
					break;
				}
				if (lineContentFirst != null && !lineContentFirst.isEmpty())
					Rule6_SrcStringArr.add(lineContentFirst.trim());
				// System.out.println("lineContentFirst = " + lineContentFirst);
			}

			txtBR.close();
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
		}
	}

	public static void getDstLineArray() {

		String dstLineString = "";
		for (int i = 0; i < Rule6_SrcStringArr.size(); i++) {
			String item = Rule6_SrcStringArr.get(i).trim();
			String fixItem = fixItemString(item);
			dstLineString = dstLineString + fixItem;
			if ((i + 1) % Rule6_num_row == 0) {
				System.out.println("Rule6_num_row = " + Rule6_num_row);
				if (!dstLineString.trim().isEmpty()) {
					Rule6_DstStringArr.add(dstLineString.trim());
					System.out.println("dstLineString = " + dstLineString);
				}
				dstLineString = "";
			}
		}
		Rule6_DstStringArr.add(dstLineString.trim()); // 添加剩下的那些 item

	}

	public static String fixItemString(String item) {
		if (item == null) {
			System.out.println("当前行为空1！");
			return "";

		}
		if (item != null && item.isEmpty()) {

			System.out.println("当前行为空2！");
			return "";
		}
//        int fixSpace = Rule6_rowItemMaxLength - item.length();
		int fixSpace = Rule6_rowItemMaxLength - getFramePaddingChineseLength(item);
		String spaceString = "";
		for (int j = 0; j < fixSpace + Rule6_itemSpace; j++) {

			spaceString = spaceString + " ";
		}
		item = item + spaceString;
		System.out.println("item = " + item);
		return item;

	}
	// Rule_6 End 竖排列 转为 横竖排列 1xn 转为 4x(n/4)

	public static ArrayList<String> Tushare_TreeNodeData_Rule18(ArrayList<String> content) {

		// showArrList(content);

		ArrayList<String> headList = getTushare_EndTagList(content, "", "输入参数");
		showArrList(headList);
		String webSite = null;
		String chineseTitle = null;
		String pythonMethodName = null;
		String chinesetitle_WebSite = guolvArrList(headList, "https://"); // 包含 https的那一行

		if (chinesetitle_WebSite != null && !"".equals(chinesetitle_WebSite)) {
			webSite = clearChinese(chinesetitle_WebSite).trim();
			chineseTitle = chinesetitle_WebSite.replace(webSite, "").trim();
		}
		String JieKou_Str = guolvArrList(headList, "接口：");
		if (JieKou_Str != null && !"".equals(JieKou_Str)) {
			pythonMethodName = clearChinese(JieKou_Str).trim().replace("：", "");
		}

		ArrayList<String> input_params_list = getTushare_EndTagList(content, "输入参数", "输出参数");

		ArrayList<String> inputList = clearAllChineseLine(input_params_list);
//        showArrList(inputList);
//        System.out.println("--------------3--------------");
		ArrayList<String> output_params_list = getTushare_EndTagList(content, "输出参数", "");
		ArrayList<String> outputList = clearAllChineseLine(output_params_list);
//        showArrList(outputList);

		ArrayList<String> templateArr = new ArrayList<String>();

		templateArr.add("〖" + chineseTitle + "     " + webSite);
		ArrayList<String> paramA = addTushare_Params_Flag_18(inputList, "〖*", "*");
		templateArr.addAll(paramA);
		String title_pinyin = Rule14_ToPinyinWithLine(chineseTitle).replace("_", "");
		String methodLine_pre = title_pinyin + "       【】[] <" + pythonMethodName + "> ( ) ";
		String methodLine_end = clearOneBlankCharAsOneStr_Rule_2(outputList).get(0);
		String methodLine = methodLine_pre + methodLine_end;
		templateArr.add(methodLine);
		showArrList(templateArr);
		return templateArr;
	}

	public static ArrayList<String> clearAllChineseLine(ArrayList<String> content) {
		ArrayList<String> clear_all_Chinese_Line_List = new ArrayList<String>();

		for (int i = 0; i < content.size(); i++) {
			String item = content.get(i);
			String fixed_item = clearChinese(item).trim();
			fixed_item = fixed_item.replace("\t", "");
			fixed_item = fixed_item.replace("：", "");
			if ("".equals(fixed_item)) {
				continue;
			}
			clear_all_Chinese_Line_List.add(item);
		}

		return clear_all_Chinese_Line_List;
	}

	public static String guolvArrList(ArrayList<String> content, String tag) {

		String result = "";
		for (int i = 0; i < content.size(); i++) {
			String item = content.get(i);
			if (item.contains(tag)) {
				result = item;
				return result;
			}
		}
		return result;
	}

	public static ArrayList<String> getTushare_EndTagList(ArrayList<String> content, String beginTag, String endTag) {
		ArrayList<String> tagList = new ArrayList<String>();

		boolean isBegin = false; // 是否从头开始读取
		if (beginTag == null) {
			isBegin = true;
		} else {
			isBegin = "".equals(beginTag) ? true : false;
		}

		for (int i = 0; i < content.size(); i++) {
			String item = content.get(i);
			if (!"".equals(beginTag) && item.contains(beginTag)) {
				isBegin = true;
				continue;
			}

			if (!"".equals(endTag) && item.contains(endTag)) {
				isBegin = false;
				break;
			}
			if (!isBegin) { // 如果没有开始 那么 不记录当前的字符串
				continue;
			}
			if ("".equals(item.trim())) {
				continue;
			}
			tagList.add(item);
		}

		return tagList;
	}

	public static void showArrList(ArrayList<String> content) {

		for (int i = 0; i < content.size(); i++) {
			String line = content.get(i);
			System.out.println("[ " + i + " ] = " + line);
		}

	}

	// 往 每行开头加入〖* 第一个字符串后加入* 其余不变(方便生成tushare的输入参数格式) ts_code -> 〖*ts_code* 描述
	static ArrayList<String> addTushare_Params_Flag_18(ArrayList<String> originStrList, String beginStr,
			String endStr) {
		ArrayList<String> newContentList_Padding = new ArrayList<String>();
		ArrayList<String> newContentList = new ArrayList<String>();
		for (int i = 0; i < originStrList.size(); i++) {
			String originStr = originStrList.get(i);
			String item = new String(originStr);
			item = item.replace("\t", " ").trim();
			item = item.replace("：", " ").trim();
			item = item.replace("  ", " ").trim();
			String[] arr = item.split(" ");
			String fixedStr = "";
			String firstBlankStr = "";
			if (arr == null) {
				firstBlankStr = endStr;
				fixedStr = beginStr + item + firstBlankStr;
			} else {
				firstBlankStr = arr[0] + endStr;
				fixedStr = item.replace(arr[0], firstBlankStr);
				fixedStr = beginStr + fixedStr;
			}
			newContentList.add(fixedStr);
		}

		int maxPointSize = calMaxItemCharPosition(newContentList, endStr) + 2;

		for (int i = 0; i < newContentList.size(); i++) {
			String item = newContentList.get(i);
			int curIndex = item.lastIndexOf(endStr);
			int paddingInt = maxPointSize - curIndex;
			String paddingStr = getEmptyString(paddingInt);
			item = item.replace(endStr + " ", endStr + " " + paddingStr);
			newContentList_Padding.add(item);
		}

		return newContentList_Padding;

	}

	static int calMaxItemCharPosition(ArrayList<String> originStrList, String strFlag) {

		int maxSize = 0;
		for (int i = 0; i < originStrList.size(); i++) {
			String item = originStrList.get(i);
			int curIndex = item.lastIndexOf(strFlag);
			if (curIndex > maxSize) {
				maxSize = curIndex;
			}
		}
		return maxSize;
	}

	ArrayList<String> addZhanWeiFlag_Rule_10(ArrayList<String> originStrList, String beginStr, String endStr) {
		ArrayList<String> newContentList = new ArrayList<String>();
		for (int i = 0; i < originStrList.size(); i++) {
			newContentList.add(beginStr + originStrList.get(i).trim() + endStr);
		}

		return newContentList;

	}

	// Rule_11 Begin 把表格 3x3 转为 MD文件格式 加入 |---| 分割符号
	public static final ArrayList<String> Rule11_tableItemList = new ArrayList<>();
	public static int Rule11_rowInLine = 0;

	public static String getRule11_rowInLine(File file) {

		String titleString = null;
		String sumString = "";
		try {
			BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			titleString = curBR.readLine().trim();
			System.out.println("FirstLineA = " + titleString);

			while ("\t".equals(titleString) || "".equals(titleString)) {
				titleString = curBR.readLine().trim();
				System.out.println("FirstLineA_A = " + titleString);
			}
			while (titleString.contains("\t")) {
				titleString = titleString.replaceAll("\t", " ");
			}
			while (titleString.contains("  ")) {
				titleString = titleString.replaceAll("  ", " ");
			}

			System.out.println("titleStringB = " + titleString);
			String[] strArr = titleString.split(" ");
			Rule11_rowInLine = strArr.length;
//            Rule11_rowInLine = getFramePaddingChineseLength(strArr);

			for (String item : strArr) {
				sumString = sumString + " | " + item;
			}
			sumString = sumString + " |";
			Rule11_tableItemList.add(sumString);

			String twoLine = "";
			for (int i = 0; i < Rule11_rowInLine; i++) {

				twoLine = twoLine + "| ---- ";
			}
			twoLine = twoLine + "| ";
			Rule11_tableItemList.add(twoLine);

			curBR.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return sumString;
	}

	public static ArrayList<String> getMDtable_ForCommonText_Rule_11(File srcFile) {

		ArrayList<String> newContentList = new ArrayList<String>();

		String headLine = getRule11_rowInLine(srcFile);
		System.out.println("Rule11_rowInLine = " + Rule11_rowInLine);
		if (srcFile != null) {
			try {
				BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "utf-8"));
				String oldOneLine = "";

				while ((oldOneLine = curBR.readLine()) != null && !oldOneLine.isEmpty()) {
					break; // 跳过首行 当做标题的那行
				}

				oldOneLine = "";
				int countLine = 1;
				while (oldOneLine != null) {
					oldOneLine = curBR.readLine();
					if (oldOneLine == null || "\t".equals(oldOneLine.trim()) || oldOneLine.trim().isEmpty()) {
						System.out.println("oldOneLine = " + oldOneLine + "   跳过!");
						continue;
					}
					String tableItem = new String(oldOneLine);

					while (tableItem.contains("\t")) {
						tableItem = tableItem.replaceAll("\t", " ");
					}

					while (tableItem.contains("  ")) {
						tableItem = tableItem.replaceAll("  ", " ");
					}
					String[] strArr = tableItem.split(" ");
					int length = 0; //
					if (strArr.length >= Rule11_rowInLine) {
						length = Rule11_rowInLine;
					} else {
						length = strArr.length;
//                        length = getFramePaddingChineseLength(strArr);
					}
					String sumString = "";
					for (int i = 0; i < length; i++) {
						sumString = sumString + " | " + strArr[i];
					}
					sumString = sumString + " |";
					if (length < Rule11_rowInLine) {
						int blankCount = Rule11_rowInLine - length;
						for (int j = 0; j < blankCount; j++) {
							sumString = sumString + "  | ";
						}
					}
					System.out.println("sumString[" + countLine + "] = " + sumString);
					countLine++;
					if (!Rule11_tableItemList.contains(sumString)) {
						System.out.println("加入 headLine = " + headLine + "  sumString = " + sumString);
						Rule11_tableItemList.add(sumString);
					} else {
						System.out.println("头部相同 不加入! headLine = " + headLine + "  sumString = " + sumString);
					}

				}
				curBR.close();

//                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

				for (int i = 0; i < Rule11_tableItemList.size(); i++) {
//                    curBW.write(Rule11_tableItemList.get(i).trim());
					newContentList.add(Rule11_tableItemList.get(i).trim());
//                    curBW.newLine();
				}
//                curBW.close();
				System.out.println("OK !");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed !");
		}
		return newContentList;
	}

	// Rule_11 End 把表格 2x2 3x3 4x4 ... 转为 MD文件格式 加入 |---| 分割符号

	// Rule_12 Begin 把 剪切板的字符串 逐行 复制 到当前 文本的 逐行 后边 A6
	static ArrayList<String> Rule12_curFileStringList = new ArrayList<String>();
	static ArrayList<String> Rule12_clipStringList = new ArrayList<String>();
	static int Rule12_SPACE = 12;
	static int Rule12_MAX_LENGTH_CLIP = 0; // 剪切板的行数
	static int maxLength_old_line = 0;

	public static ArrayList<String> BoardCopyStrToLeft_Rule_12(File srcFile) {

		ArrayList<String> newContentList = new ArrayList<String>();

		String clipStr = Rule12_getSysClipboardText().trim();
		System.out.println("clipStr = " + clipStr);
		if (clipStr.trim().equals("")) {
			System.out.println("clip String is empty or null!");
			newContentList.add("剪切板的内容为空!  请重新复制到剪切板!");
			return newContentList;
		}

		String[] arrLine = clipStr.split("\n");
		int arrLength = arrLine.length;
		int currentMaxLength = 0;
		if (arrLine != null) {
			currentMaxLength = Rule12_getMaxLengthInArr(arrLine);
			Rule12_MAX_LENGTH_CLIP = currentMaxLength;
		}

		for (int i = 0; i < arrLength; i++) {
			System.out.println("index = 【" + i + " 】   item=" + arrLine[i]);
			Rule12_clipStringList.add(arrLine[i]);
		}
		// String source = new String(clipStr.replaceAll("\r|\n", ""));

		//

		Rule12_tryReadContentFromFile(srcFile); // 读取旧的文件内容
		maxLength_old_line = Rule12_getMaxLengthInArr(Rule12_curFileStringList);

		return Rule12_tryWriteNewContentToFile(Rule12_clipStringList, Rule12_curFileStringList, srcFile);
		// 对当前文件重新进行处理

	}

	static void Rule12_tryReadContentFromFile(File file) {

		if (file != null && file.exists()) {

			try {
				BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				String lineStr = "";

				while (lineStr != null) {
					lineStr = curBR.readLine();
					if (lineStr == null || lineStr.trim().isEmpty()) {
						continue;
					}
					lineStr = lineStr.replace("\t", "    ");
					Rule12_curFileStringList.add(lineStr);
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

	static String getRule12_SPACE(int num) {
		String str = "";
		for (int i = 0; i < num; i++) {
			str = str + " ";
		}

		return str;
	}

	static ArrayList<String> Rule12_tryWriteNewContentToFile(ArrayList<String> clipList, ArrayList<String> oldList,
			File file) {

		ArrayList<String> newStrList = new ArrayList<String>();
		if (file != null && file.exists()) {

// String curItem = "#  "+new String(netAddr);
//                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			// curBW.write(curItem);

			int clipListSize = clipList.size();
			int oldListListSize = oldList.size();
			int maxRow = 0;
			if (clipListSize > oldListListSize) {
				maxRow = clipListSize;

			} else {
				maxRow = oldListListSize;
			}

			for (int i = 0; i < maxRow; i++) {
				String clipItem = null;
				if (i >= clipList.size()) {
					clipItem = getEmptyString(maxLength_old_line);
				} else {
					clipItem = clipList.get(i).trim();
				}

				String oldItem = null;
				if (i >= oldList.size()) {
					oldItem = getEmptyString(maxLength_old_line);
				} else {
					oldItem = oldList.get(i);
				}

				int currentNeedRule12_SPACE = maxLength_old_line
						- (oldItem != null ? getFramePaddingChineseLength(oldItem) : 0) + Rule12_SPACE;

//                int currentNeedRule12_SPACE = Rule12_MAX_LENGTH_CLIP - (clipItem != null ? clipItem.length() : 0) + Rule12_SPACE;
				String newItem = oldItem + getRule12_SPACE(currentNeedRule12_SPACE)
						+ (clipItem != null ? clipItem.trim() : "");
				System.out.println("xxxx");
				System.out.println(
						"原有的 oldItem.length = " + (oldItem != null ? getFramePaddingChineseLength(oldItem) : 0));
				System.out.println("maxLength_old_line = " + maxLength_old_line + " currentNeedRule12_SPACE = "
						+ currentNeedRule12_SPACE);
				System.out.println(" oldItem = " + oldItem);
				System.out.println(" newItem = " + newItem);
				System.out.println("xxxx");
//                    curBW.write(newItem);
//                    curBW.newLine();
				newStrList.add(newItem);
			}
//                curBW.close();
			System.out.println("write file OK !");

		} else {
			System.out.println("Failed !");
			newStrList.add("操作执行失败!  请重新执行!");
		}
		return newStrList;
	}

	static int Rule12_getMaxLengthInArr(ArrayList<String> arr) {
		int maxLength = 0;
		if (arr != null) {
			int arrlength = arr.size();

			for (int i = 0; i < arrlength; i++) {
				String item = arr.get(i);
				// getFramePaddingChineseLength()
				int curssidLength = getFramePaddingChineseLength(item);
//                int curssidLength = item.length();
				if (curssidLength > maxLength) {
					maxLength = curssidLength;
				}
			}

		}
		return maxLength;
	}

	static int Rule12_getMaxLengthInArr(String[] arr) {
		int maxLength = 0;
		if (arr != null) {
			int arrlength = arr.length;

			for (int i = 0; i < arrlength; i++) {
				String item = arr[i];
				// getFramePaddingChineseLength()
				int curssidLength = getFramePaddingChineseLength(item);
//                int curssidLength = item.length();
				if (curssidLength > maxLength) {
					maxLength = curssidLength;
				}
			}

		}
		return maxLength;
	}

	public static String Rule12_getSysClipboardText() {
		String ret = "";
		Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 获取剪切板中的内容
		Transferable clipTf = sysClip.getContents(null);

		if (clipTf != null) {
			// 检查内容是否是文本类型
			if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					ret = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	// Rule_12 End 把 剪切板的字符串 逐行 复制 到当前 文本的 逐行 后边 A6

	// Rule_13 Begin 去除掉当前文件中的中文 每一个中文 以一个空格代替 A7

	private static String Rule13_REGEX_CHINESE = "[\u4e00-\u9fa5]";

	public static ArrayList<String> add_system_out_Rule_21(File srcFile) {
		ArrayList<String> newContent = new ArrayList<String>();

		File curFile = srcFile;

		if (curFile != null) {

			FileReader curReader;
			try {

				curReader = new FileReader(curFile);

				BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
				String oldOneLine = "";
				String newOneLine = "";

				while (oldOneLine != null) {

					oldOneLine = curBR.readLine();
					if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
						newContent.add("System.out.println();");
						continue;
					}

//                    System.out.println(" \"this is test string\" ");
//                    System.out.println("adb shell am broadcast -a com.Android.test --es<string> test_string \"this is test string\" —ei<int> test_int 100 —ez<boolean> test_boolean true");
					newOneLine = new String(oldOneLine);

					newOneLine = newOneLine.replace("\\", "\\\\");

					newOneLine = newOneLine.replace("\"", "\\\"");
					newOneLine = newOneLine.replace(".\\", ".\\\\"); // .\w .\w

					// 把 [.bat ] 转为 ["+BAT_OR_SH_Point+"]
					// 把 [.sh ] 转为 ["+BAT_OR_SH_Point+"]

					newOneLine = newOneLine.replace(".bat ", "\"+BAT_OR_SH_Point+\"" + " \" +\"");
					newOneLine = newOneLine.replace(".sh ", "\"+BAT_OR_SH_Point+\"" + " \" +\"");

					newOneLine = "System.out.println(\"" + newOneLine.trim() + "\");";

					newContent.add(newOneLine.trim());
				}
				curBR.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed !");
		}
		return newContent;
	}

	public static ArrayList<String> clearChinese_Rule_13(File srcFile) {
		ArrayList<String> newContent = new ArrayList<String>();

		File curFile = srcFile;

		if (curFile != null) {

			FileReader curReader;
			try {

				curReader = new FileReader(curFile);

				BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
				String oldOneLine = "";
				String newOneLine = "";

				while (oldOneLine != null) {

					oldOneLine = curBR.readLine();
					if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
						continue;
					}

					newOneLine = new String(oldOneLine);
					if (Rule13_isContainChinese(newOneLine)) {
						newOneLine = Rule13_clearChinese(newOneLine);

					}
					newContent.add(newOneLine.trim());
				}
				curBR.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed !");
		}
		return newContent;
	}

	public static boolean Rule13_isContainChinese(String str) {
		Pattern p = Pattern.compile(Rule13_REGEX_CHINESE);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static String Rule13_clearChinese(String lineContent) {
		if (lineContent == null || lineContent.trim().isEmpty()) {
			return null;
		}
		Pattern pat = Pattern.compile(Rule13_REGEX_CHINESE);
		Matcher mat = pat.matcher(lineContent);
		return mat.replaceAll(" ");
	}

	// Rule_13 Begin 去除掉当前文件中的中文 每一个中文 以一个空格代替 A7

	// Rule_14 Begin 汉字转换为拼音 周 zhou 中国 zhong_guo (A9)
	public static ArrayList<String> Chinese_To_PinYin_Rule_14(File srcFile) {

		// System.out.println(Rule14_ToFirstChar("ABC 汉字转换为拼音CBA").toUpperCase());
		// //转为首字母大写
		// System.out.println(Rule14_ToPinyinWithLine("A周 B杰 C伦"));
		// System.out.println(Rule14_ToPinyinWithLine("ABC汉字转换为拼音CBA"));
		ArrayList<String> StringArr = new ArrayList<>();

		File curFile = srcFile;

		if (curFile != null) {
			try {
				BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
				String oldOneLine = "";
				String newOneLine = "";

				while (oldOneLine != null) {

					oldOneLine = curBR.readLine();
					if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
						continue;
					}

					newOneLine = new String(oldOneLine);
					newOneLine = Rule14_ToPinyinWithLine(newOneLine);
					if (newOneLine != null && !newOneLine.trim().isEmpty()) {
						StringArr.add(newOneLine);
					}

				}
				curBR.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed !");
		}
		return StringArr;
	}

	/**
	 * 获取字符串拼音的第一个字母
	 *
	 * @param chinese
	 * @return
	 */
	public static String Rule14_ToFirstChar(String chinese) {
		String pinyinStr = "";
		char[] newChar = chinese.toCharArray(); // 转为单个字符
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < newChar.length; i++) {
			if (newChar[i] > 128) {
				try {
					pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0); // charAt(0)
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinStr += newChar[i];
			}
		}
		return pinyinStr;
	}

	/**
	 * 汉字转为拼音
	 *
	 * @param chinese
	 * @return
	 */
	public static String Rule14_ToPinyin(String chinese) {
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
					pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0]; // [0] 标识当前拼音 汉->
																										// han
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else { // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
				pinyinStr += newChar[i];
			}
		}
		return pinyinStr;
	}

	/**
	 * 汉字转为拼音 空间以下划线_分割 1.每个汉字前面添加_ 2.每个汉字后面添加_ 3.把所有的两个__ 下划线转为 一个下划线
	 *
	 * @param chinese
	 * @return
	 */
	public static String Rule14_ToPinyinWithLine(String chinese) {
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
					// 《》，：“￥。，？。，；【 】。、

					System.out.println("xxxxxxxxxx");
					System.out.println("newChar[" + i + "] = " + newChar[i]);
					String charStr = newChar[i] + "";
					if (charStr.equals("《")) {
						pinyinStr += "<";
						continue;
					} else if (charStr.equals("》")) {
						pinyinStr += ">";
						continue;
					} else if (charStr.equals("，")) {
						pinyinStr += ",";
						continue;
					} else if (charStr.equals("：")) {
						pinyinStr += ":";
						continue;
					} else if (charStr.equals("“")) {
						pinyinStr += "\"";
						continue;
					} else if (charStr.equals("￥")) {
						pinyinStr += "$";
						continue;
					} else if (charStr.equals("？")) {
						pinyinStr += "?";
						continue;
					} else if (charStr.equals("；")) {
						pinyinStr += ";";
						continue;
					} else if (charStr.equals("【")) {
						pinyinStr += "[";
						continue;
					} else if (charStr.equals("】")) {
						pinyinStr += "]";
						continue;
					} else if (charStr.equals("、")) {
						pinyinStr += ",";
						continue;
					}

					String[] arrChar = PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
					if (arrChar == null) {
						System.out.println("pinyinStr = " + null);
						continue;
					}
					pinyinStr += "_" + arrChar[0] + "_"; // [0] 标识当前拼音 汉-> han
					System.out.println("pinyinStr = " + pinyinStr);
					System.out.println("xxxxxxxxxx ");
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else { // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
				pinyinStr += newChar[i];
			}
		}
		while (pinyinStr.contains("__")) {
			pinyinStr = pinyinStr.replaceAll("__", "_");
			System.out.println("pinyinStr1 = " + pinyinStr);
		}

		while (pinyinStr.contains("u:")) { // 女转为 nu: 绿 lu: 需要转为 nv lv
			pinyinStr = pinyinStr.replaceAll("u:", "v");
			System.out.println("pinyinStr1 = " + pinyinStr);
		}

		while (pinyinStr.startsWith("_")) {
			pinyinStr = pinyinStr.substring(1, pinyinStr.length());
			System.out.println("pinyinStr2 = " + pinyinStr);
		}
		while (pinyinStr.endsWith("_")) {
			pinyinStr = pinyinStr.substring(0, pinyinStr.length() - 1);
			System.out.println("pinyinStr3 = " + pinyinStr);
		}
		return pinyinStr;
	}
	// Rule_14 End 汉字转换为拼音 周 zhou 中国 zhong_guo (A9)

	// 默认 只显示 第一行的 字符串
	// Rule_15 Begin 读取文件的第一行转为 二维码显示出来 B1
	public static void Pre50_TextAs_QrCode_Rule_15_Win(File srcFile) {
		File curFile = srcFile;
		if (curFile != null) {
			try {
				BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
				String qrCodeString = "";

				while (qrCodeString != null && qrCodeString.trim().isEmpty()) {
					qrCodeString = curBR.readLine();
				}
				System.out.println("把扫描到的第一行非空字符串转为二维码  qrCodeString = " + qrCodeString);

				curBR.close();
				QrConfig config = new QrConfig();

				File targetFile = QrCodeUtil.generate(qrCodeString, config, new File(I9_OUT_DIR.getAbsolutePath()
						+ File.separator + getFileNameNoPoint(srcFile.getName()) + "_" + getTimeStampLong() + ".jpg"));

				RuntimeUtil.exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  "
						+ targetFile.getAbsolutePath());

			} catch (Exception e) {
				System.out.println("Exception !");
			}
		} else {
			System.out.println("Failed !");
		}
	}
	// Rule_15 End 读取文件的第一行转为 二维码显示出来 B1

	// 默认 只显示 第一行的 字符串
	// Rule_15 Begin 读取文件的第一行转为 二维码显示出来 B1
	public static void TextAs_QrCode_Rule_15_Mac(File srcFile) {
		File curFile = srcFile;
		ArrayList<String> TxtContent = new ArrayList<String>();
		ArrayList<String> Pre50_Content = new ArrayList<String>();

		if (curFile != null) {

			try {
				BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
				String qrCodeString = "";

//                while (qrCodeString != null && qrCodeString.trim().isEmpty()) {
				while (qrCodeString != null) {
					qrCodeString = curBR.readLine();
					TxtContent.add(qrCodeString);
				}
				System.out.println("把扫描到的第一行非空字符串转为二维码  qrCodeString = " + qrCodeString);

				curBR.close();

				int lineNum = 1;
				for (int i = 0; i < TxtContent.size(); i++) {

					String lineStr = TxtContent.get(i);
					System.out.println("line[" + lineNum + "] = " + lineStr);
					lineNum++;
					if (Pre50_Content.size() < 50 && lineStr != null) {
						Pre50_Content.add(lineStr);
					}
				}

				QrConfig config = new QrConfig();
				ArrayList<File> targetFileList = new ArrayList<File>();
				for (int i = 0; i < Pre50_Content.size(); i++) {
					int lineIndex = i + 1;
					String content = Pre50_Content.get(i).trim();
					boolean isEmpty = "".equals(content);
					boolean isUrl = isValidUrl_String(content);
					File targetFile = null;
					if (isEmpty) {
						targetFile = calculBlankIndex(I9_Qr_Black_Image_File, lineIndex);
					} else {
						targetFile = QrCodeUtil.generate(content, config,
								new File(I9_OUT_DIR.getAbsolutePath() + File.separator
										+ getFileNameNoPoint(srcFile.getName()) + "_" + getTimeStampLong() + ".jpg"));
					}
					targetFileList.add(targetFile);

					System.out.println("operation -> " + lineIndex);
				}

				if (targetFileList.size() == 1) {
					String commandOne = " open  " + targetFileList.get(0).getAbsolutePath();
//                    RuntimeUtil.exec(" open  " + targetFileList.get(0).getAbsolutePath());
					execute_Mac(commandOne);

				} else {

					int width = targetFileList.size() * 300;
					if (width >= 3000) {
						width = 3000;
					}

					int high = targetFileList.size() % 10 == 0 ? (targetFileList.size() / 10) * 300
							: ((targetFileList.size() / 10) * 300 + 300);
					if (high >= 1500) {
						high = 1500;
					}

					BufferedImage combined = new BufferedImage(width, high, BufferedImage.TYPE_INT_RGB);
					Graphics g = combined.getGraphics();

					for (int i = 0; i < targetFileList.size(); i++) {
						int LineNum = i + 1;
						File QrFile = targetFileList.get(i);
						BufferedImage originImage = getBufferedImage(QrFile);
						int temp_x = 300 * (i % 10);
						int temp_y = 300 * (i / 10);

						System.out.println("绘制第 " + LineNum + " 图片! " + "x=" + temp_x + " y =" + temp_y + "   QrFile = "
								+ QrFile.exists() + "  路径:" + QrFile.getAbsolutePath());

						g.drawImage(originImage, temp_x, temp_y, null);
					}
					File endTargetFile = new File(I9_OUT_DIR.getAbsolutePath() + File.separator + "Qr_"
							+ getFileNameNoPoint(srcFile.getName()) + "_" + getTimeStampLong() + ".jpg");
					endTargetFile.createNewFile();
					ImageIO.write(combined, "jpg", endTargetFile);
					String showJpgCommand = "open  " + endTargetFile.getAbsolutePath();
//                    System.out.println("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + endTargetFile.getAbsolutePath());
					System.out.println("命令: " + showJpgCommand);
					execute_Mac(showJpgCommand);
//                    RuntimeUtil.exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + endTargetFile.getAbsolutePath());

				}

//                File endTarget = calculQrEndTarget(targetFileList);
//
//                RuntimeUtil.exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + endTarget.getAbsolutePath());

				/*
				 * QrConfig config = new QrConfig();
				 * 
				 * File targetFile = QrCodeUtil.generate (qrCodeString, config, new
				 * File(I9_OUT_DIR.getAbsolutePath() + File.separator +
				 * getFileNameNoPoint(srcFile.getName())+"_"+getTimeStampLong()+".jpg"));
				 * 
				 * RuntimeUtil.
				 * exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  "
				 * + targetFile.getAbsolutePath());
				 */

			} catch (Exception e) {
				System.out.println("Exception ! ");
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed !");
		}
	}
	// Rule_15 End 读取文件的第一行转为 二维码显示出来 B1

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

	/**
	 * 读取控制命令的输出结果 原文链接：http://lavasoft.blog.51cto.com/62575/15599
	 * 
	 * @param
	 * @return 控制命令的输出结果
	 * @throws IOException
	 */
	public static String readConsole(Process process) {
		StringBuffer cmdOut = new StringBuffer();
		InputStream fis = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				cmdOut.append(line).append(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//       System.out.println("执行系统命令后的控制台输出为：\n" + cmdOut.toString());
		return cmdOut.toString().trim();
	}

	// 默认 只显示 第一行的 字符串
	// Rule_15 Begin 读取文件的第一行转为 二维码显示出来 B1
	@SuppressWarnings("unchecked")
	public static String Image2QrCode_Rule_24_Win(File srcFile) {
		String qrStr = "";

		try {
			MultiFormatReader formatReader = new MultiFormatReader();
			BufferedImage bufferedImage = ImageIO.read(srcFile);
			BinaryBitmap binaryBitmap = new BinaryBitmap(
					new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
			// 定义二维码参数
//            Map hints = new HashMap<>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            Result result = formatReader.decode(binaryBitmap, hints);
			Result result = formatReader.decode(binaryBitmap);
			qrStr = result.getText();
			System.out.println("解析二维码数据成功 对于  File---> " + srcFile.getAbsolutePath() + "  【" + qrStr + "】");
		} catch (Exception e) {
			System.out.println("解析二维码数据失败 对于  File---> " + srcFile.getAbsolutePath());
		}
		return qrStr;

	}
	// Rule_15 End 读取文件的第一行转为 二维码显示出来 B1

	// 默认 只显示 第一行的 字符串
	// Rule_15 Begin 读取文件的第一行转为 二维码显示出来 B1
	public static void TextAs_QrCode_Rule_15_Win(File srcFile) {
		File curFile = srcFile;
		ArrayList<String> TxtContent = new ArrayList<String>();
		ArrayList<String> Pre50_Content = new ArrayList<String>();

		if (curFile != null) {

			try {
				BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
				String qrCodeString = "";

//                while (qrCodeString != null && qrCodeString.trim().isEmpty()) {
				while (qrCodeString != null) {
					qrCodeString = curBR.readLine();
					TxtContent.add(qrCodeString);
				}
				System.out.println("把扫描到的第一行非空字符串转为二维码  qrCodeString = " + qrCodeString);

				curBR.close();

				int lineNum = 1;
				for (int i = 0; i < TxtContent.size(); i++) {

					String lineStr = TxtContent.get(i);
					System.out.println("line[" + lineNum + "] = " + lineStr);
					lineNum++;
					if (Pre50_Content.size() < 50 && lineStr != null) {
						Pre50_Content.add(lineStr);
					}
				}

				QrConfig config = new QrConfig();
				ArrayList<File> targetFileList = new ArrayList<File>();
				for (int i = 0; i < Pre50_Content.size(); i++) {
					int lineIndex = i + 1;
					String content = Pre50_Content.get(i).trim();
					boolean isEmpty = "".equals(content);
					boolean isUrl = isValidUrl_String(content);
					File targetFile = null;
					if (isEmpty) {
						targetFile = calculBlankIndex(I9_Qr_Black_Image_File, lineIndex);
					} else {
						targetFile = QrCodeUtil.generate(content, config,
								new File(I9_OUT_DIR.getAbsolutePath() + File.separator
										+ getFileNameNoPoint(srcFile.getName()) + "_" + getTimeStampLong() + ".jpg"));
					}
					targetFileList.add(targetFile);

					System.out.println("operation -> " + lineIndex);
				}

				if (targetFileList.size() == 1) {
					RuntimeUtil.exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  "
							+ targetFileList.get(0).getAbsolutePath());

				} else {

					int width = targetFileList.size() * 300;
					if (width >= 3000) {
						width = 3000;
					}

					int high = targetFileList.size() % 10 == 0 ? (targetFileList.size() / 10) * 300
							: ((targetFileList.size() / 10) * 300 + 300);
					if (high >= 1500) {
						high = 1500;
					}

					BufferedImage combined = new BufferedImage(width, high, BufferedImage.TYPE_INT_RGB);
					Graphics g = combined.getGraphics();

					for (int i = 0; i < targetFileList.size(); i++) {
						int LineNum = i + 1;
						File QrFile = targetFileList.get(i);
						BufferedImage originImage = getBufferedImage(QrFile);
						int temp_x = 300 * (i % 10);
						int temp_y = 300 * (i / 10);

						System.out.println("绘制第 " + LineNum + " 图片! " + "x=" + temp_x + " y =" + temp_y + "   QrFile = "
								+ QrFile.exists() + "  路径:" + QrFile.getAbsolutePath());

						g.drawImage(originImage, temp_x, temp_y, null);
					}
					File endTargetFile = new File(I9_OUT_DIR.getAbsolutePath() + File.separator + "Qr_"
							+ getFileNameNoPoint(srcFile.getName()) + "_" + getTimeStampLong() + ".jpg");
					endTargetFile.createNewFile();
					ImageIO.write(combined, "jpg", endTargetFile);
					System.out.println("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  "
							+ endTargetFile.getAbsolutePath());
					RuntimeUtil.exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  "
							+ endTargetFile.getAbsolutePath());

				}

//                File endTarget = calculQrEndTarget(targetFileList);
//
//                RuntimeUtil.exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + endTarget.getAbsolutePath());

				/*
				 * QrConfig config = new QrConfig();
				 * 
				 * File targetFile = QrCodeUtil.generate (qrCodeString, config, new
				 * File(I9_OUT_DIR.getAbsolutePath() + File.separator +
				 * getFileNameNoPoint(srcFile.getName())+"_"+getTimeStampLong()+".jpg"));
				 * 
				 * RuntimeUtil.
				 * exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  "
				 * + targetFile.getAbsolutePath());
				 */

			} catch (Exception e) {
				System.out.println("Exception ! ");
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed !");
		}
	}
	// Rule_15 End 读取文件的第一行转为 二维码显示出来 B1

	// zukgit
	static File calculBlankIndex(File imageFile, int Index) {
		File blockFile = new File(I9_OUT_DIR.getAbsolutePath() + File.separator + "Block" + "_" + Index + "_"
				+ getTimeStampLong() + ".jpg");

		String imageType = getFileTypeWithPoint(imageFile.getName());
		String type_fixed = imageType.replace(".", "").trim();
		int originHigh = getImageHigh(imageFile);
		int originWidth = getImageWidth(imageFile);
		BufferedImage originImage = getBufferedImage(imageFile);

		BufferedImage combined = new BufferedImage(originWidth, originHigh, BufferedImage.TYPE_INT_RGB);
		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		int padding = 20;

		try {
			g.setColor(Color.RED);
			Font font = new Font("微软雅黑", Font.PLAIN, 100);
			g.setFont(font);
			FontMetrics metrics = g.getFontMetrics(font);
			int x = 0 + (originWidth - metrics.stringWidth("" + Index)) / 2;
			int y = 0 + ((originHigh - metrics.getHeight()) / 2) + metrics.getAscent();
			g.drawImage(originImage, 0, 0, null);
			g.drawString(Index + "", x, y);

			// Save as new image
			ImageIO.write(combined, type_fixed, blockFile);
		} catch (Exception e) {
			System.out.println("发生异常! ");

		} finally {
			if (g != null) {
				g.dispose();
			}
		}

		return blockFile;
	}

	public static BufferedImage resize(Image mImage, int w, int h) {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		try {
			g.drawImage(mImage, 0, 0, w, h, null); // 绘制缩小后的图
		} finally {
			if (g != null) {
				g.dispose();
			}
		}
		return image;
		// File destFile = new File("C:\\temp\\456.jpg");
		// FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// // 可以正常实现bmp、png、gif转jpg
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// encoder.encode(image); // JPEG编码
		// out.close();
	}

	public static BufferedImage getBufferedImage(File file) {
		Image img = null;
		try {
			img = ImageIO.read(file); // 构造Image对象
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		int width = img.getWidth(null); // 得到源图宽
		int height = img.getHeight(null); // 得到源图长

//    return resizeFix(400, 492);
		return resize(img, width, height);
	}

	// zukgit
	static File calculQrEndTarget(ArrayList<File> srcFileList) {
		File targetFile = new File(
				I9_OUT_DIR.getAbsolutePath() + File.separator + "Qr" + "_" + getTimeStampLong() + ".jpg");

		for (int i = 0; i < srcFileList.size(); i++) {

		}

		return targetFile;
	}

	// Rule_16 Begin Windows 读物当前文件内容 并 播放 vbs 声音
	public static void ReadStrFromFile_Rule_16(File srcFile) {
		File curFile = srcFile;
		String ReadCotent = ReadFileContent(srcFile);

		String fixedStr = ReadCotent.replaceAll("\r|\n", "");
		fixedStr = fixedStr.replaceAll(" ", "");
		System.out.println("读取内容 fixedStr = " + fixedStr);
		String source = new String(fixedStr);

		// 创建vbs 并执行它 CreateObject("SAPI.SpVoice").Speak "你好，2019"

		String mVbsFilePath = I9_OUT_DIR_PATH + File.separator + getTimeStampLong() + "_voice.vbs";
		String commadStr = "CreateObject(\"SAPI.SpVoice\").Speak \"" + source + "\"";

		File file = new File(mVbsFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
				BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
				curBW.write(commadStr);
				curBW.close();
			} catch (Exception e) {

			}
		}
		RuntimeUtil.exec("Wscript.exe  /x " + file);
		System.out.println("执行操作:   " + "Wscript.exe  /x " + file);
	}
	// Rule_16 End Windows 读物当前文件内容 并 播放 vbs 声音

	// Rule_17 Begin 对 Json 格式的文件 或者 以json格式保存的文件 生成bean文件 以及 Graphviz 绘图显示结构

	// ProperityItem(String ownnerClassName, String properityName, String TypeName,
	// String refClassName)

	static ArrayList<String> Rule17_dotStringArr = new ArrayList<String>();
	static ArrayList<HashMap<String, ArrayList<ProperityItem>>> Rule17_arrMapList = new ArrayList<HashMap<String, ArrayList<ProperityItem>>>();
	// 对 ArrayList 进行 排序

	static String Rule17_Rule17_outFilepath = "";
	static File Rule17_outFile = null;
	static String Rule17_dot_targetFilePath = "";
	static String Rule17_dirPath = ""; // zbin 路径

	static void Rule17_tryWriteJsonToFile(ArrayList<String> Rule17_dotStringArr, File file, String netAddr) {

		if (file != null && file.exists()) {

			try {
// String curItem = "#  "+new String(netAddr);
				BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
				// curBW.write(curItem);
				for (int i = 0; i < Rule17_dotStringArr.size(); i++) {
					curBW.write(Rule17_dotStringArr.get(i));
					curBW.newLine();
				}
				curBW.close();
				System.out.println("OK !");
				System.out.println("write json File OK !");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed !");
		}
	}

	public static String Rule17_decode(String url) {
		try {
			String prevURL = "";
			String decodeURL = url;
			while (!prevURL.equals(decodeURL)) {
				prevURL = decodeURL;
				decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
			}
			return decodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while decoding" + e.getMessage();
		}
	}

	public static String Rule17_encode(String url) {
		try {
			String encodeURL = URLEncoder.encode(url, "UTF-8");
			return encodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while encoding" + e.getMessage();
		}
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

	public static void Make_Json_As_JavaFile_Rule_17(File srcFile,File resultDirFile) {


		
		try {
			Rule17_dirPath = I9_OUT_DIR_PATH;

			Rule17_Rule17_outFilepath = Rule17_dirPath + File.separator + getTimeStampLong() + ".gv";

			StringBuilder sb = new StringBuilder();
			System.out.println("curFile = " + srcFile.getAbsolutePath());

			
			
			tryReadJsonFromFile(sb, srcFile);

			String firstArrChar = sb.toString().substring(0, 1);
			while (firstArrChar != null && !(firstArrChar.equals("{") || firstArrChar.equals("["))) {

				sb = new StringBuilder(sb.toString().trim().substring(1));
				System.out.println("注意: 不是以{ 或者 [ 开头  firstArrChar=" + firstArrChar);
				firstArrChar = sb.toString().substring(0, 1);
			}

			boolean isArrJson = sb.toString().startsWith("[");
			if (isArrJson) {
				sb = new StringBuilder("{ arr:" + sb.toString() + "}");
				System.out.println("读取到的字符是[开头的!  firstArrChar=" + firstArrChar);
			} else {
				System.out.println("读取到的字符不是[开头的!  firstArrChar=" + firstArrChar);
				// 读取到的字符不是[开头的! firstArrChar=?
			}

			if (sb.toString().trim().equals("")) {
				System.out.println("读取到的Json 文件为空  退出!!");
				return;
			}

			String firstChar = sb.toString().substring(0, 1);
			while (firstChar != null && !firstChar.equals("{")) {
				sb = new StringBuilder(sb.toString().trim().substring(1));
				System.out.println("注意: 不是以{ 开头  firstChar=" + firstChar);
				firstChar = sb.toString().substring(0, 1);

			}
			if (!JSONUtil.isJson(sb.toString().trim())) {
				System.out.println("读取到的文件不是标准的Json 格式 退出!  当前读取到的数据为:\n" + sb.toString());
				System.out.println("读取到的文件不是标准的Json 格式 退出!  firstChar 第一个字符为:\n" + sb.toString().substring(0, 1));
				System.out.println("读取到的文件不是标准的Json 格式 退出!  第二个字符为:\n" + sb.toString().substring(1, 2));
				return;
			}

			Rule17_outFile = new File(Rule17_Rule17_outFilepath);
			Rule17_dot_targetFilePath = Rule17_outFile.getParentFile().getAbsolutePath() + File.separator
					+ getFileNameNoPoint(Rule17_outFile.getName());
			if (!Rule17_outFile.exists()) {
				Rule17_outFile.createNewFile();
			}

//            System.out.println(JsonFormat.format(s));
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test5")).execute();
//
//
//            s = "{\"multyList\":[[[{\"name\":\"xm1\",\"age\":19}]]]}";

			// System.out.println(toujiaoJson);

			I9_TextRuleOperation I9_Object = new I9_TextRuleOperation();
			String result = new Json2Bean(sb.toString(), "RootBean", null, new MyNameGenerator(), new MyJsonParser(),
					new MyBeanGenerator("com.zukgit")).execute();
//            String result = new Json2Bean(sb.toString(), "RootBean", null, new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.zukgit")).execute();

			System.out.println("------------------------------");
			System.out.println("result = " + result);
//
//            List<List<List<String>>> list = new ArrayList<>();
//
//            List<List<String>> li1 = new ArrayList<List<String>>();
//            li1.add(Arrays.asList("d", "e", "f"));
//            li1.add(Arrays.asList("d1", "e1", "f1"));
//
//            List<List<String>> li2 = new ArrayList<List<String>>();
//            li2.add(Arrays.asList("d", "e", "f"));
//            li2.add(Arrays.asList("d2", "e2", "f2"));
//
//            list.add(li1);
//            list.add(li2);
//
//
//            s = JSON.toJSONString(list);
//            System.out.println(s);
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test3")).execute();
//
//            System.out.println("------------------------------");
//
//            s = "{\"post_message\":\"[:f002}[:f003}[:f003}[:f004}[:f004}\",\"intlist\":[1,2,3],\"str\":\"{}\"}";
//            System.out.println(JsonFormat.format(s));
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test4")).execute();
//
//            s = "[[[{\"name\":\"xm1\",\"age\":19},{\"name\":\"[xm2\",\"age\":19},{\"name\":\"{xm3\",\"age\":19}],[{\"name\":\"[[xm4\",\"age\":19},{\"name\":\"{{xm5\",\"age\":19}]],[[{\"name\":\"xm6\",\"age\":19},{\"name\":\"xm7\",\"age\":19}],[{\"name\":\"xm8\",\"age\":19}]]]";
//            System.out.println(JsonFormat.format(s));
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test5")).execute();
//
//
//            s = "{\"multyList\":[[[{\"name\":\"xm1\",\"age\":19}]]]}";
//            System.out.println(JsonFormat.format(s));
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test6")).execute();

			System.out.println(" arrArrList.size()" + Rule17_arrMapList.size());
			int index = 0;
//            Rule17_arrMapList.sort(new Comparator<HashMap<String, ArrayList<ProperityItem>>>() {
//                @Override
//                public int compare(HashMap<String, ArrayList<ProperityItem>> o1, HashMap<String, ArrayList<ProperityItem>> o2) {
//                    int lengthO1 = 0;
//                    int lengthO2 = 0;
//                    Iterator<Map.Entry<String, ArrayList<ProperityItem>>> itr_o1 = o1.entrySet().iterator();
//                    while (itr_o1.hasNext()) {
//                        Map.Entry<String, ArrayList<ProperityItem>> entry_o1 = itr_o1.next();
//                        String k = entry_o1.getKey();
//                        ArrayList<ProperityItem> o1Arr =  entry_o1.getValue();
//                        lengthO1 = o1Arr.size() + lengthO1;
//                    }
//
//                    Iterator<Map.Entry<String, ArrayList<ProperityItem>>> itr_o2 = o2.entrySet().iterator();
//                    while (itr_o2.hasNext()) {
//                        Map.Entry<String, ArrayList<ProperityItem>> entry_o2 = itr_o2.next();
//                        String k = entry_o2.getKey();
//                        ArrayList<ProperityItem> o2Arr =  entry_o2.getValue();
//                        lengthO2 = o2Arr.size() + lengthO2;
//                    }
//                    if(lengthO1 > lengthO2){
//                        return 1;
//                    } else{
//                        return -1;
//                    }
//                }
//            });
			// https://dreampuf.github.io/GraphvizOnline/#
			Rule17_dotStringArr.add("digraph {");
			Rule17_dotStringArr.add("#   zzfile_3.bat  " + Rule17_OutRealJava_Path + "      ##  生成的java代码地址");
			Rule17_dotStringArr.add("#    https://dreampuf.github.io/GraphvizOnline/  #      Giraphz图形地址 ");
			Rule17_dotStringArr.add("#    http://www.bejson.com/jsonviewernew/    Json 数据 \n");
			Rule17_dotStringArr.add("#     dot  " + Rule17_Rule17_outFilepath + " -Tpdf -o " + Rule17_dot_targetFilePath
					+ ".pdf  生成PDF \n");
			Rule17_dotStringArr.add("#     dot  " + Rule17_Rule17_outFilepath + " -Tpng -o " + Rule17_dot_targetFilePath
					+ ".png    生成PNG   \n");
			Rule17_dotStringArr.add("graph [rankdir=BT] \n");
			System.out.println("===============index" + index + "=============== Begin");

			for (Map<String, ArrayList<ProperityItem>> arr : Rule17_arrMapList) {

				// System.out.println("===============index" + index + "=============== Begin");

				Iterator<Map.Entry<String, ArrayList<ProperityItem>>> itr = arr.entrySet().iterator();

				while (itr.hasNext()) {
					Map.Entry<String, ArrayList<ProperityItem>> entry = itr.next();
					String k = entry.getKey();
					ArrayList<ProperityItem> properArr = entry.getValue();
					int subindex = 0;
					// System.out.println("开始打印 ClassName = 【" + k + "】 ");
					for (ProperityItem properityItem : properArr) {
						System.out.println("ClassName = 【" + k + "】 index =【" + index + " 】   subIndex=【" + subindex
								+ "】  str=" + properityItem.toString());
//                        subindex++;
						System.out.println(properityItem.makeNodeString());
						Rule17_dotStringArr.add(properityItem.makeNodeString());

					}
					for (ProperityItem properityItem : properArr) {
						// System.out.println(properityItem.makeNodeRelationString());
						Rule17_dotStringArr.add(properityItem.makeNodeRelationString());
					}

				}
				// System.out.println("===============index" + index + "=============== End");
				index++;

			}
			Rule17_dotStringArr.add("} \n");
			System.out.println("==============Rule17_dotStringArr=============== Begin");
			StringBuilder urlSB = new StringBuilder();
			for (String item : Rule17_dotStringArr) {
				System.out.println(item);
				urlSB.append(item);
			}
			String encodeStr = Rule17_encode(urlSB.toString());
			encodeStr = encodeStr.replaceAll("\\+", "%20");

			encodeStr = encodeStr.replaceAll("\\+", "%20");
			encodeStr = encodeStr.replaceAll("%7B", "%7B%0A%0A%20%20");
			encodeStr = encodeStr.replaceAll("%3B", "%3B%0A%20%20%20%20");

			String netAddr = "https://dreampuf.github.io/GraphvizOnline/#" + encodeStr;
			Rule17_tryWriteJsonToFile(Rule17_dotStringArr, Rule17_outFile, netAddr);
			rule17_resultFile_List.add(Rule17_outFile);
//  浏览器地址最多接受 4096个字符  所以 无法通过编译 .gv 文件来跳转到 对应的 graviz页面

			JavaRuntimeInfo javaRuntimeInfo = new JavaRuntimeInfo();
			System.out.println("=========================runtimeInfo=========================\n" + javaRuntimeInfo);
			String libPath = javaRuntimeInfo.getLibraryPath();
			String dotPath = "";
			// ;C:\Program Files (x86)\Graphviz2.38\bin\dot.exe;

			System.out.println("libPath = " + libPath);
			if (libPath.contains("Graphviz")) {
//            if(libPath.contains("Graphviz") && libPath.contains("dot")){
				String[] libArr = libPath.split(";");
				int length = libArr.length;

				for (int i = 0; i < length; i++) {
//                    if(libArr[i].contains("Graphviz") && libArr[i].contains("dot")){
					if (libArr[i].toLowerCase().contains("Graphviz".toLowerCase())) {
						dotPath = libArr[i];
						break;
					}
				}
			}
			if (!"".equals(dotPath) && dotPath.endsWith("dot")) {
				if (dotPath.endsWith(File.separator)) {
					dotPath = dotPath + "dot";
				} else {
					dotPath = dotPath + File.separator + "dot";
				}
			}

			System.out.println("zukgit  dotPath.substring  dotPath =" + dotPath);
			String dotRule17_dirPath = "";
			if (dotPath != null && !dotPath.isEmpty()) {
				dotRule17_dirPath = dotPath.substring(0, dotPath.lastIndexOf("\\"));
				dotPath = "\"" + dotPath + "\"";
				dotRule17_dirPath = "\"" + dotRule17_dirPath + "\"";
				// dot ./B6.gv -Tpdf -o B6.pdf
				System.out.println("dotRule17_dirPath 1  = " + dotRule17_dirPath);
				File outputPdfFile = new File(Rule17_outFile.getParentFile().getAbsolutePath() + File.separator
						+ getFileNameNoPoint(Rule17_outFile.getName()) + ".pdf");
				String command = " cmd.exe /c cd " + dotRule17_dirPath + "  &&  dot.exe  "
						+ Rule17_outFile.getAbsolutePath() + " -Tpdf -o  "
						+ Rule17_outFile.getParentFile().getAbsolutePath() + File.separator
						+ getFileNameNoPoint(Rule17_outFile.getName()) + ".pdf";

				System.out.println("command = " + command);
				// RuntimeUtil.exec(command);
				String procResult = Rule17_execCMD(command); // 生成PDF 文件
				System.out.println("command = " + command + " procResult=" + procResult);

				// command = cmd.exe /c C:\Program Files (x86)\Graphviz2.38\bin && dot.exe
				// C:\work_space\eclipse_workplace\B6\B6.gv -Tpdf -o
				// C:\work_space\eclipse_workplace\B6\B6.pdf
// cmd.exe /c cd "C:\Program Files (x86)\Graphviz2.38\bin"  &&  dot.exe  C:\work_space\eclipse_workplace\B6\B6.gv -Tpdf -o  C:\work_space\eclipse_workplace\B6\B6.pdf
				String command2 = "cmd.exe /C start acrord32  " + outputPdfFile.getAbsolutePath();
				Thread.sleep(2000);
				String procResult2 = Rule17_execCMD(command2); // 打开 acrord32 Adobe 阅读 PDF 文件
				System.out.println("command2 = " + command2 + " procResult=" + procResult2);
				rule17_resultFile_List.add(outputPdfFile);

			}
			String command3 = "cmd.exe /C start notepad++  " + Rule17_outFile.getAbsolutePath();
			String procResult3 = Rule17_execCMD(command3); // 使用notepad 打开 gv 文件
			System.out.println("command3 = " + command3 + " procResult3=" + procResult3);

			String command4 = "";
			System.out.println("netAddr =   " + netAddr);
			if (netAddr.length() > 4000) {
				command4 = "cmd.exe /C start chrome   https://dreampuf.github.io/GraphvizOnline/ ";

			} else {

				command4 = "cmd.exe /C start chrome  " + netAddr;

			}
			// String command4 = "cmd.exe /C start chrome " +netAddr;

			String command5 = "cmd.exe /C start chrome   http://www.bejson.com/jsonviewernew/ ";

			String procResult5 = Rule17_execCMD(command5); // 使用 Chrome 打开 JSON在线解析

			String procResult4 = Rule17_execCMD(command4); // 使用 Chrome 打开 Graphiz 在线解析网页
			System.out.println("command5 = " + command5 + " procResult5=" + procResult5);
			System.out.println("dotPath = " + dotPath);
			if (dotPath != null && !dotPath.isEmpty()) {
// command6   生成 Png 照片
				// String command6 = "cmd.exe /C start chrome
				// http://www.bejson.com/jsonviewernew/ ";
				File pngFile = new File(Rule17_outFile.getParentFile().getAbsolutePath() + File.separator
						+ getFileNameNoPoint(Rule17_outFile.getName()) + ".png");
				//
				// String command6 = " cmd.exe /c cd " +dotRule17_dirPath+ " && dot.exe
				// "+Rule17_outFile.getAbsolutePath() +" -Tpng -o " +
				// Rule17_outFile.getParentFile().getAbsolutePath()+File.separator+ "B6.png";
				String command6 = " cmd.exe /c cd " + dotRule17_dirPath + "  &&  dot.exe  "
						+ Rule17_outFile.getAbsolutePath() + " -Tpng -o  " + pngFile.getAbsolutePath();
				String procResult6 = Rule17_execCMD(command6);
				System.out.println("command6 = " + command6 + " procResult6=" + procResult6);
				// command7 使用照片浏览器打开照片
				String command7 = "rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  "
						+ pngFile.getAbsolutePath();
				// RuntimeUtil.exec("rundll32.exe
				// C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen " +
				// pngFile.getAbsolutePath());
				String procResult7 = Rule17_execCMD(command7);
				System.out.println("command7 = " + command7 + " procResult7=" + procResult7);
				
				rule17_resultFile_List.add(pngFile);
				
			}
			
			

		} catch (Exception e) {

		}

		// System.out.println("Zukgit-----------End");

		for (int i = 0; i < rule17_resultFile_List.size(); i++) {
			File resultFile = rule17_resultFile_List.get(i);
			if(!resultDirFile.exists()) {
				resultDirFile.mkdirs();
			}
			String resultFileName = resultFile.getName();
			File copyResultFile = new File(resultDirFile.getAbsolutePath()+File.separator+resultFileName);
			fileCopy(resultFile, copyResultFile);
			System.out.println("生成文件["+i+"] AllFile["+rule17_resultFile_List.size()+"] = "+ copyResultFile.getAbsolutePath());
		}
		System.out.println("在 目录 "+resultDirFile.getAbsolutePath()+" 生成了解析的文件!");
		
	}

	public static String Rule17_execCMD(String command) {
		StringBuilder sb = new StringBuilder();
		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			return e.toString();
		}
		return sb.toString();
	}

	static class ProperityItem {
		String properityName;
		String TypeName;
		String ownnerClassName;
		String refClassName; // 当 isClassType = true 时 它所指向引用的那个 类名的名字

		String baseTypeInListName; // List<Double> 中的 基础类型数据的名称
		String classTypeInListName; // List<Class> 中的 类的 名称

		boolean isBaseType; // TypeName 是否基础数据类型 Array<String> 和 String 都是基础数据类型
		boolean isClassType; // 是否是 Class 引用数据类型
		boolean isObjectNullList; // 是否是 List<Object> 的 空的数组

		boolean isList; // 是否是集合 List<>
		boolean isBaseList; // 是否是基础的数据类型集合 List<String> List<Double>
		int baseListCount; // 该属性 包含 List的个数 嵌套数
		boolean isClassList; // 是否是对象的数据类型集合 List<A> List<B>
		int classListCount; // 该属性 包含 List的个数 嵌套数

		@Override
		public String toString() {
			return "【 ownnerClassName=" + ownnerClassName + "  properityName=" + properityName + " TypeName=" + TypeName
					+ " refClassName=" + refClassName + "  isBaseType=" + isBaseType + "  isClassType=" + isClassType
					+ "  isList=" + isList + " baseListCount=" + baseListCount + " classListCount=" + classListCount
					+ "  isObjectNullList " + isObjectNullList + "】";
		}

		ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName) {
			if (ownnerClassName != null && !ownnerClassName.isEmpty()) {
				this.ownnerClassName = ownnerClassName;
			}
			if (properityName != null && !properityName.isEmpty()) {
				this.properityName = properityName;
			}
			if (TypeName != null && !TypeName.isEmpty()) {
				this.TypeName = TypeName;
			}
			if (refClassName != null && !refClassName.isEmpty()) {
				this.refClassName = refClassName;
			}

			if (TypeName != null && !TypeName.isEmpty()) {
				isBaseType = true;
				isClassType = false;
				if (TypeName.contains("String") || TypeName.contains("Double") || TypeName.contains("Integer")) {
					isBaseType = true;
					isClassType = false;
				} else {
					isClassType = true;
					isBaseType = false;
				}
			}

			if (TypeName != null && !TypeName.isEmpty()) {
				isList = false;
				isBaseList = false;
				isClassList = false;
				baseListCount = 0;
				classListCount = 0;
				if (TypeName.contains("List")) {
					isList = true;
					if (TypeName.contains("String")) {
						isBaseList = true;
						isClassList = false;
						baseTypeInListName = "String";
						baseListCount = StrUtil.count(TypeName, "List");
					} else if (TypeName.contains("Double")) {
						isBaseList = true;
						isClassList = false;
						baseTypeInListName = "Double";
						baseListCount = StrUtil.count(TypeName, "List");
					} else if (TypeName.contains("Integer")) {
						isBaseList = true;
						isClassList = false;
						baseTypeInListName = "Integer";
						baseListCount = StrUtil.count(TypeName, "List");
					} else if (TypeName.contains("Object")) { // 包含 Object的 空数组
						isClassList = true;
						isBaseList = false;
						isList = true;
						classListCount = StrUtil.count(TypeName, "List");
						isObjectNullList = true;
						classTypeInListName = "Object";
					} else { // List<List<List<SSSS>>>
						isBaseList = false;
						isClassList = true;
						classListCount = StrUtil.count(TypeName, "List");
						classTypeInListName = TypeName.replaceAll("List", "").replaceAll("<", "").replaceAll(">", "")
								.replaceAll(" ", "").trim();

					}
				}
			}
		}

		String getLable() {
			if (isClassType) {
				return StrUtil.upperFirst(properityName.trim());
			}
			return "\"" + properityName + "\\n" + "【" + TypeName + "】" + "\"";
		}

		// ArrayList<String> =List-String List【】 String【】 baseListCount == 1
		// ArrayList<String> baseListCount == 1 ArrayList<List<String>> baseListCount ==
		// 2
		ArrayList<String> getLableArr() {
			String item2 = "";
			ArrayList<String> stringArr = null;
			if (isList && isBaseType == true && baseListCount > 0) {

				stringArr = new ArrayList<String>();
				for (int i = 0; i < baseListCount; i++) {
					// stringArr.add("List"+"<【" + TypeName +"】>");

					stringArr.add("\"" + getBaseListShowString(properityName, TypeName, i) + "\"");
				}
				// baseTypeInListName String
				stringArr.add("\"" + properityName + "_item" + "\\n" + baseTypeInListName + "\"");

			} else if (isList && isClassType && isObjectNullList) { // 如果是空的列表的话 List<Object>
				stringArr = new ArrayList<String>();

				for (int i = 0; i < classListCount; i++) {
					String strItem = "\"" + getListShowString(properityName, TypeName, i) + "\"";
					// data\nList<【A___B___C】>
					stringArr.add(strItem);
					System.out.println(" properityName = " + properityName + "    TypeName=" + TypeName + "  index ="
							+ i + "strItem = " + strItem);

				}
				item2 = "\"" + classTypeInListName + "\\n" + properityName + "\"";
				stringArr.add(item2); // A 类名 \\n 属性名

			} else if (isList && isClassType == true && classListCount > 0) { // 如果是 List 列表的话
				// // 获取 数组的 data 【List<A>】 properityName=data TypeName=List<A>
				// List<【List<A>】> data List<>
				stringArr = new ArrayList<String>();

				for (int i = 0; i < classListCount; i++) {
					String strItem = "\"" + getListShowString(properityName, TypeName, i) + "\"";
					// data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
					// tempstr=A___B___C
					stringArr.add(strItem);
					System.out.println(" properityName = " + properityName + "    TypeName=" + TypeName + "  index ="
							+ i + "strItem = " + strItem);
					// properityName = data
					// TypeName=List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
					// index =0
					// strItem =
					// "data\nList<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"
				}
				item2 = "\"" + classTypeInListName + "\\n" + properityName + "\"";
				stringArr.add(item2); // A 类名 \\n 属性名
			}

			System.out.println("classTypeInListName = " + classTypeInListName + " properityName" + properityName);
			System.out.println("item2 = " + item2 + " stringArr.size()" + stringArr.size()); // stringArr.size() =2

			// classTypeInListName =
			// A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
			// properityName = data
			// item1 =
			// data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
			// tempstr=A___B___C
			// item2 =
			// A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]\ndata
			return stringArr;
		}

		String getBaseListShowString(String properityName, String strListTypeName, int index) {
			int count = StrUtil.count(strListTypeName, "List");
			if (count == 1 || index == 0) {
				return properityName + "\\n" + getPretyTypeName(TypeName, baseTypeInListName, 0);
				// data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
				// tempstr=A___B___C
			} else {
				return getPretyTypeName(TypeName, baseTypeInListName, index);
			}
			// properityName=data TypeName=List<A> classTypeInListName=A
//List<List<List<String>>>   data    【 count 是总的List的个数】   【index =0 】 是显示的个数 显示的个数是 count - index
// 【count 3 , index 0】  data \n List<【List<List<A>>】>
// 【count 3 , index 1】  List<【<List<A>】>
// 【count 3 , index 2】  List<【A】>

		}

		String getListShowString(String properityName, String strListTypeName, int index) {

			// properityName = data
			// TypeName=List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
			// index =0
			// strItem =
			// "data\nList<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"

			int count = StrUtil.count(strListTypeName, "List");
			if (count == 1 || index == 0) { // 如果只包含一个List
				// strItem =
				// "data\nList<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"
				String curItem = properityName + "\\n" + getPretyTypeName(TypeName, classTypeInListName, 0);
				// data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
				// tempstr=A___B___C
				return curItem;
			} else {
				return getPretyTypeName(TypeName, classTypeInListName, index);
			}
			// properityName=data TypeName=List<A> classTypeInListName=A
//List<List<List<A>>>   data    【 count 是总的List的个数】   【index =0 】 是显示的个数 显示的个数是 count - index
// 【count 3 , index 0】  data \n List<【List<List<A>>】>
// 【count 3 , index 1】  List<【<List<A>】>
// 【count 3 , index 2】  List<【A】>

		}

		String getPretyTypeName(String strListTypeName, String classTypeInListName, int index) {

			// properityName = data
			// TypeName=List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
			// index =0
			// strItem =
			// "data\nList<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"
			// classTypeInListName
			// zclassTypeInListName
			// =A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]

			System.out.println("zclassTypeInListName  =" + classTypeInListName);
			int count = StrUtil.count(strListTypeName, "List");
			if (index > count) {
				System.out.println("getPretyTypeName 索引错误  index=" + index + "   count =" + count);
			}
// 【count 3 , index 0  keepListCount=3 】  data \n List<【List<List<A>>】>
// 【count 3 , index 1  keepListCount=2 】  List<【<List<A>】>
// 【count 3 , index 2 keepListCount=1 】  List<【A】>

			// List<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"
			int keepListCount = count - index;
			String fixClassTypeInListName = new String(classTypeInListName);
//    // List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
			String listString = buildListString(fixClassTypeInListName, keepListCount); // List<A> List<List<List<A>>>
			System.out.println("zlistString " + listString);
			String tempstr = "";
			String resultStr = "";
			if (listString.contains("\\n") && listString.contains("___")) {
				tempstr = classTypeInListName.substring(0, classTypeInListName.indexOf("\\n"));
				resultStr = listString.replaceAll(tempstr, "【" + tempstr + "】"); // List<【A】> List<List<List<【A】>>>
			} else {

				resultStr = listString.replaceAll(classTypeInListName, "【" + classTypeInListName + "】"); // List<【A】>
																											// List<List<List<【A】>>>
			}

			// resultStr
			// List<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
			// tempstr=A___B___C
			System.out.println("resultStr " + resultStr + "  tempstr=" + tempstr);
			return resultStr;
		}

		String buildListString(String classTypeInListName, int listCount) {
			// List<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"

			// classTypeInListName
			// =A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
			// classTypeInListNameX E
			String arrEnd = "";
			String listNameWithoutN = "";
			if (classTypeInListName.contains("\\n")) {
				arrEnd = classTypeInListName.substring(classTypeInListName.indexOf("\\n"),
						classTypeInListName.length());
				listNameWithoutN = classTypeInListName.substring(0, classTypeInListName.indexOf("\\n"));
			}
			System.out.println("classTypeInListNameX " + classTypeInListName);
			String curStr = "";
			String curStrKeep = "";
			for (int i = 0; i < listCount; i++) {
				if (i == 0) {
					curStr = "List<" + (listNameWithoutN.isEmpty() ? classTypeInListName : listNameWithoutN) + ">"
							+ curStrKeep;
					curStrKeep = curStr;
				} else {
					curStr = "List<" + curStrKeep + ">";
					curStrKeep = curStr;
				}
			}
// 【index 1  List<A>】
// 【index 2  List<List<A>>】
// 【index 3  List<List<List<A>>>】
			String result = curStrKeep + arrEnd;
			// List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]

			// zlistString List<>
			System.out.println("result " + result);
			return result;
		}

		ArrayList<String> getShapeArr() {

//            boolean isBaseType;       //  TypeName 是否基础数据类型 Array<String>  和 String  都是基础数据类型
////            boolean isClassType;        //  是否是 Class 引用数据类型
////            boolean isList;       //  是否是集合   List<>
////            boolean isBaseList;   // 是否是基础的数据类型集合  List<String>   List<Double>
////            boolean isClassList;   // 是否是对象的数据类型集合  List<A>   List<B>
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
//            if(isBaseType == true && isList == false){
//                return "box";
//            } else if(isBaseType == true && isList == true){
//                return "doubleoctagon";
//            }else if(isBaseType == false && isList == false){
//                return "circle";
//            }else if(isBaseType == false && isList == true){
//                return "doublecircle";
//            }
//            return "";
			ArrayList<String> stringArr = null;
			if (isList && isBaseType == true && baseListCount > 0) {

				stringArr = new ArrayList<String>();
				for (int i = 0; i < baseListCount; i++) {
					stringArr.add("shape=doubleoctagon");
				}
				stringArr.add("shape=box");
			} else if (isList && isClassType && isObjectNullList) {
				for (int i = 0; i < classListCount; i++) {
					stringArr.add("shape=doubleoctagon");
				}

			} else if (isList && isClassType == true && classListCount > 0) {
				stringArr = new ArrayList<String>();
				for (int i = 0; i < classListCount; i++) {
					stringArr.add("shape=doubleoctagon");
				}
				stringArr.add("shape=circle");
			}
			return stringArr;
		}

		String getShape() {

//            boolean isBaseType;       //  TypeName 是否基础数据类型 Array<String>  和 String  都是基础数据类型
////            boolean isClassType;        //  是否是 Class 引用数据类型
////            boolean isList;       //  是否是集合   List<>
////            boolean isBaseList;   // 是否是基础的数据类型集合  List<String>   List<Double>
////            boolean isClassList;   // 是否是对象的数据类型集合  List<A>   List<B>
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
			if (isBaseType == true && isList == false) {
				return "shape=box";
			} else if (isBaseType == true && isList == true) {
				return "shape=doubleoctagon";
			} else if (isBaseType == false && isList == false) {
				return "shape=circle";
			} else if (isBaseType == false && isList == true && !isObjectNullList) {
				return "shape=doublecircle";
			} else if (isBaseType == false && isList == true && isObjectNullList) {
				return "shape=doubleoctagon";
			}
			return "";
		}

		String getStyle() {
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
			if (isBaseType == true && isList == false) {
				return ""; // 基础数据类型没有颜色
			} else if (isBaseType == true && isList == true) {
				return ""; // 基础数据类型没有颜色
			} else if (isBaseType == false && isList == false) {
				return "style=filled"; // 单个 Class的 颜色是 红色
			} else if (isBaseType == false && isList == true) {
				return "style=filled"; // 多个 List<Class> 是 黄色
			}
			return "";
		}

		ArrayList<String> getStyleArr() {
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
			ArrayList<String> stringArr = null;
			if (isList && isBaseType == true && baseListCount > 0) { // 基础类型数据集合

				stringArr = new ArrayList<String>();
				for (int i = 0; i < baseListCount; i++) {
					stringArr.add("");
				}
				stringArr.add("");
			} else if (isList && isClassType == true && classListCount > 0) { // 对象类型颜色
				stringArr = new ArrayList<String>();
				for (int i = 0; i < classListCount; i++) {
					stringArr.add("style=filled");
				}
				stringArr.add("style=filled");
			}
			return stringArr;

		}

		ArrayList<String> getColorArr() {
//// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
//            // box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
//            if(isBaseType == true && isList == false){
//                return "";   // 基础数据类型没有颜色
//            } else if(isBaseType == true && isList == true){
//                return "";  // 基础数据类型没有颜色
//            }else if(isBaseType == false && isList == false){
//                return "red";   // 单个 Class的 颜色是 红色
//            }else if(isBaseType == false && isList == true){
//                return "yellow";  // 多个 List<Class> 是 黄色
//            }
//            return "";

			ArrayList<String> stringArr = null;
			if (isList && isBaseType == true && baseListCount > 0) { // 基础类型数据集合

				stringArr = new ArrayList<String>();
				for (int i = 0; i < baseListCount; i++) {
					stringArr.add("");
				}
				stringArr.add("");
			} else if (isList && isClassType && isObjectNullList) {
				for (int i = 0; i < classListCount; i++) {
					stringArr.add("color=purple");
				}

			} else if (isList && isClassType == true && classListCount > 0) { // 对象类型颜色
				stringArr = new ArrayList<String>();
				for (int i = 0; i < classListCount; i++) {
					stringArr.add("color=yellow");
				}
				stringArr.add("color=red");
			}
			return stringArr;

		}

		String getColor() {
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
			// box【普通数据类型的数据】 doubleoctagon【普通数据类型的数组】 circle【 Class 引用 】 doublecircle
			// 【List<Class 引用>】
			if (isBaseType == true && isList == false) {
				return ""; // 基础数据类型没有颜色
			} else if (isBaseType == true && isList == true) {
				return ""; // 基础数据类型没有颜色
			} else if (isBaseType == false && isList == false) {
				return "color=red"; // 单个 Class的 颜色是 红色
			} else if (isBaseType == false && isList == true && isObjectNullList) {
				return "color=purple"; // 多个 json为[] 的 对象的 颜色是蓝色的
			} else if (isBaseType == false && isList == true) {
				return "color=yellow"; // 多个 List<Class> 是 黄色
			}
			return "";
		}

		String getNodeName() {
			if (TypeName.equals("Class")) {
				return properityName;
			}
			String nodeName = ownnerClassName + "_" + properityName;
			while (nodeName.startsWith("_")) {
				nodeName = nodeName.substring(1, nodeName.length());
			}
			while (nodeName.endsWith("_")) {
				nodeName = nodeName.substring(0, nodeName.length() - 1);
			}

			// nodeName.get(0)=View_font-size-editor 在 .gv 文件中 不能以 - 命名变量

			nodeName = fixedName_Json2Bean(nodeName);
			return nodeName;
		}

		ArrayList<String> getNodeNameArr() {
			ArrayList<String> stringArr = null;
			if (isList && isBaseType == true && baseListCount > 0) {

				stringArr = new ArrayList<String>();
				for (int i = 0; i < baseListCount; i++) {
					stringArr.add(ownnerClassName + "_" + properityName + "_Arr" + i);
				}
				// stringArr.add(ownnerClassName + "_" + properityName + "_ArrEnd" + "_" +
				// baseTypeInListName);
				stringArr.add(ownnerClassName + "_" + properityName + "_ArrEnd" + "_" + baseTypeInListName);
			} else if (isList && isClassType == true && isObjectNullList) {
				stringArr = new ArrayList<String>();
				stringArr.add(ownnerClassName + "_" + properityName);
			} else if (isList && isClassType == true && classListCount > 0 && !isObjectNullList) {
				stringArr = new ArrayList<String>();
				for (int i = 0; i < classListCount; i++) {
					stringArr.add(ownnerClassName + "_" + properityName + "_Arr" + i);
				}
				// 类名在处理的时候 使用 类自己的名字
				stringArr.add(classTypeInListName);
			}
			return stringArr;
		}

		String getRefNodeName() { //

			return refClassName;
		}

		String makeNodeString() { //
			if (isBaseType == true && isList == false) {
				// RootBean_doubleList_Double [label="Double" shape=box] 单独的 基础类型变量
				return getNodeName() + " [label=" + getLable() + " " + getShape() + " ]";
			} else if (isBaseType == true && isList == true) {
				// RootBean_multyList_List [label="RootBean_multyList_List" shape=doubleoctagon]
				// 基础类型变量 集合
				ArrayList<String> nodeArr = getNodeNameArr();
				ArrayList<String> labelArr = getLableArr();
				ArrayList<String> colorArr = getColorArr();
				ArrayList<String> shapeArr = getShapeArr();
				ArrayList<String> styleArr = getStyleArr();

				String item = "";
				StringBuilder sb = new StringBuilder();
				int length = nodeArr.size();
				for (int i = 0; i < length; i++) {
					item = nodeArr.get(i).trim() + "[ label=" + labelArr.get(i).trim() + "  " + shapeArr.get(i).trim()
							+ " " + styleArr.get(i).trim() + " " + colorArr.get(i) + "] \n ";
					sb.append(item);
				}
				return sb.toString();
			} else if (isClassType == true && isList == false && isClassList == false && TypeName.equals("Class")) {
				// RootBean_Data [label="RootBean_Data" shape=circle style=filled color=red]
				// 单独的类 引用的变量
				return getNodeName() + " [label=" + getLable() + " " + getShape() + " " + getStyle() + " " + getColor()
						+ " ]";

			} else if (isClassType == true && isList == true && isClassList == true && isObjectNullList) {

				System.out.println("Test Here this.toString =" + this.toString());
				// 【 ownnerClassName=null properityName=image_list TypeName=List<Object>
				// refClassName=null isBaseType=false isClassType=true isList=true
				// baseListCount=0 classListCount=1 isObjectNullList true】
// 【 ownnerClassName=Highlight  properityName=title TypeName=List<Object> refClassName=null  isBaseType=false  isClassType=true  isList=true baseListCount=0 classListCount=1  isObjectNullList true】

				String returnNode = getNodeName() + " [label=" + getLable() + " " + getShape() + " " + getStyle() + " "
						+ getColor() + " ]";
				System.out.println("returnNodeX =" + returnNode);
				// B_source [label=Source shape=doublecircle style=filled color=blue ]
				// 【 ownnerClassName=B properityName=source TypeName=List<Object>
				// refClassName=null
				return returnNode;

			} else if (isClassType == true && isList == true && isClassList == true && !isObjectNullList) {
				// RootBean_Data_Item [label="RootBean_Data_Item" shape=doublecircle
				// style=filled color=yellow]
				ArrayList<String> nodeArr = getNodeNameArr();
				ArrayList<String> labelArr = getLableArr();
				ArrayList<String> colorArr = getColorArr();
				ArrayList<String> shapeArr = getShapeArr();
				ArrayList<String> styleArr = getStyleArr();

				String item = "";
				StringBuilder sb = new StringBuilder();
				int length = nodeArr.size();
				for (int i = 0; i < length - 1; i++) {
					// 最后一个是类 Class 属性的话 那么 就 不再创建 Node
					String labelItem = labelArr.get(i).trim(); // 这里不正常
					System.out.println(" labelItemepre = " + labelItem);
					System.out.println(" labelItemeback = " + labelItem);
					// item1 =
					// data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
					// tempstr=A___B___C
					// item2 =
					// A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]\ndata
					item = nodeArr.get(i).trim() + "[ label=" + labelItem.trim() + "  " + shapeArr.get(i).trim() + "  "
							+ styleArr.get(i).trim() + " " + colorArr.get(i) + "] \n ";
					sb.append(item);
				}
				return sb.toString();

			}

			return "";
		}

		String getWeight() {
			if (TypeName.equals("Class")) {
				return "[weight= 30]";
			}
			if (isClassType == true && isList == true && isClassList == true) {

				return "[weight= 50]";
			}

			if (isClassType == true && isList == false && isClassList == false) {

				return "[weight= 20 ]";
			}

			if (isBaseType == true && isList == true) {

				return "[weight= 5]";
			} else {

				return "[weight= 1 ]";
			}
		}

		String makeNodeRelationString() { //

			if (isBaseType == true && isList == false) {
				// 单独的 基础类型变量 关系 RootBean -> RootBean_code
				if (ownnerClassName != null && !ownnerClassName.trim().isEmpty()) {
					System.out.println(
							"make-> 7  ownnerClassName=" + getNodeName() + "    nodeName.get(0)=" + getNodeName());
					return ownnerClassName + " -> " + getNodeName() + " " + getWeight();
				}

			} else if (isBaseType == true && isList == true) {
//                RootBean -> RootBean_multyList
//                RootBean_multyList -> RootBean_multyList_List
//                RootBean_multyList_List -> RootBean_multyList_List_List
//                RootBean_multyList_List_List -> RootBean_multyList_List_List_Item

				if (ownnerClassName != null && !ownnerClassName.trim().isEmpty()) {

					ArrayList<String> nodeName = getNodeNameArr();
					int length = getNodeNameArr().size();
					String item1 = "";
					String item2 = "";
					StringBuilder sb = new StringBuilder();
					if (length > 0) {
						System.out.println("make-> 6  ownnerClassName=" + ownnerClassName + "    nodeName.get(0)="
								+ nodeName.get(0));
						sb.append(ownnerClassName + " -> " + nodeName.get(0) + " " + getWeight() + "\n");
						for (int i = 1; i < length; i++) {
							item1 = nodeName.get(i - 1);
							item2 = nodeName.get(i);
							System.out.println("make-> 5  item1=" + item1 + "    item2=" + item2);
							sb.append(item1 + " -> " + item2 + " " + getWeight() + "\n");
						}
					}

					return sb.toString();
				}
			} else if (isClassType == true && isList == false && isClassList == false && refClassName != null) {
//   对于单独的 Class  它的 关系 已经 通过 属性 来设置了   所以 这里不用设置关系

				System.out.println("make-> 4  refClassName=" + refClassName + "    properityName=" + properityName);
				return refClassName + " -> " + properityName + " " + getWeight();

//   refClassName=B   这里替换为   ownnerClassName 错误   寻找 new Preperty的地方
				// ClassName = 【Extra】 index =【6 】 subIndex=【0】 str=【 ownnerClassName=Extra
				// properityName=topic TypeName=Topic refClassName=B isBaseType=false
				// isClassType=true isList=false baseListCount=0 classListCount=0】
// make-> 4  refClassName=Extra    properityName=Topic_2048
// make-> 4  refClassName=Extra    properityName=Topic
// make-> 4  refClassName=B    properityName=topic
// make-> 4  refClassName=B    properityName=topic_2048
			} else if (isClassType == true && isList == true && isClassList == true && isObjectNullList) {

				System.out.println("make->x   refClassName=" + refClassName + "    properityName=" + properityName
						+ " this.toString=" + this.toString());

				// make->x refClassName=null properityName=image_detail this.toString=【
				// ownnerClassName=B properityName=image_detail TypeName=List<Object>
				// refClassName=null isBaseType=false isClassType=true isList=true
				// baseListCount=0 classListCount=1 isObjectNullList true】

// returnNodeX =Highlight_source [label=Source shape=doublecircle style=filled color=blue ]
				ArrayList<String> arr = getNodeNameArr();
				for (String nullArrStr : arr) {
					System.out.println("nullArrStr = " + nullArrStr);
				}
				String nullRela = ownnerClassName + " -> " + arr.get(0) + " " + getWeight();
				System.out.println("nullRela = " + nullRela);

				// Line 39: Highlight_source [label=Source shape=doublecircle style=filled
				// color=blue ]
				// Line 43: Highlight -> Highlight_source_Arr0 [weight= 50]

				return nullRela;

			} else if (isClassType == true && isList == true && isClassList == true && !isObjectNullList) {
//  对象数组 列表       RootBean.java 的   List<A> data;
//  RootBean -> RootBean_Data  [weight=10]
//  RootBean_Data  -> RootBean_Data_Count

				ArrayList<String> nodeName = getNodeNameArr();
				// makeNodeRelationString = [RootBean_data_Arr0, A___B___C]
				System.out.println("makeNodeRelationString = " + nodeName); // zzj
				int length = getNodeNameArr().size();
				String item1 = "";
				String item2 = "";
				StringBuilder sb = new StringBuilder();
				if (length > 0) {
					System.out.println(
							"make-> 3  ownnerClassName=" + ownnerClassName + "    nodeName.get(0)=" + nodeName.get(0));
					sb.append(ownnerClassName + " -> " + nodeName.get(0) + " " + getWeight() + "\n");
					for (int i = 1; i < length; i++) {
						item1 = nodeName.get(i - 1);
						item2 = nodeName.get(i);
						if (!item2.contains("___")) {
							System.out.println("make-> 2  item1=" + item1 + "    item2=" + item2);
							sb.append(item1 + " -> " + item2 + " " + getWeight() + "\n");
						} else {
							String[] subArr = item2.split("___");
							int lengthSub = subArr.length;
//make-> 1 item1=RootBean_data_Arr0    subArr[x]=A
//make-> 1 item1=RootBean_data_Arr0    subArr[x]=B
// make-> 1 item1=RootBean_data_Arr0    subArr[x]=C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
							for (int x = 0; x < lengthSub; x++) {

								String itemStr = new String(subArr[x]);
								System.out.println("make-> 1 item1=" + item1 + "    subArr[x]pre=" + itemStr);
								if (itemStr.trim().contains("\\n")) {
									itemStr = itemStr.substring(0, itemStr.indexOf("\\n"));
								}
								System.out.println("make-> 1 item1=" + item1 + "    subArr[x]back=" + itemStr);
								sb.append(item1 + " -> " + itemStr + " " + getWeight() + "\n");
							}
						}

					}
				}
				return sb.toString();
			}
			return "";
		}

	}

	public interface BeanGenerator {

		/**
		 * @param className 类名
		 * @param map       字段及类型
		 * @throws IOException
		 */
		void writeBean(String className, Map<String, Object> map) throws IOException;

		/**
		 * @param list List<....>
		 * @throws IOException
		 */
		void writeList(String list) throws IOException;
	}

	public class CamelCaseBeanGenerator extends MyBeanGenerator {

		public CamelCaseBeanGenerator(String packName) {
			super(packName);
		}

		@Override
		public void writeBean(String className, Map<String, Object> map) throws IOException {
			System.out.println(" className1   = " + className); // 找到对应的 Class 中的类 没有执行该类
			File file = new File("src/" + packName.replace(".", "/"));
			if (!file.exists() || file.exists() && file.isFile()) {
				file.mkdirs();
			}
	
			System.out.println(" className2  =  rule17_resultFile_List.add(javaFile) " + className);
			File javaFile = new File(file, className + ".java");
			
			rule17_resultFile_List.add(javaFile);
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(javaFile));
			bw.write("package ");
			bw.write(packName);
			bw.write(";\n");
			bw.write("import java.util.List;\n\n");

			bw.write("/**\n");
			bw.write(" *auto generate\n");
			bw.write(" *\n");
			bw.write(" *@author Zukgit\n");
			bw.write(" *\n");
			bw.write(" */\n");

			bw.write("public class ");
			bw.write(className);
			bw.write("{\n");

			map = sortMapByKey(map);
			Set<Map.Entry<String, Object>> set = map.entrySet();

			for (Map.Entry<String, Object> entry : set) {
				bw.write("    ");
				bw.write(entry.getValue().toString());
				bw.write(" ");
				bw.write(formatReference(entry.getKey()));
				bw.write(";\n");
			}
			bw.write("\n");
			set = map.entrySet();

			for (Map.Entry<String, Object> entry : set) {

				bw.write("    public ");
				bw.write(entry.getValue().toString());
				bw.write(" get");
				bw.write(capitalUpper(entry.getKey()));
				bw.write("(){\n");
				bw.write("        ");
				bw.write("return ");
				bw.write(formatReference(entry.getKey()));

				bw.write(";\n    }\n\n");

				//////////////////////////

				bw.write("    public void ");
				bw.write("set");
				bw.write(capitalUpper(entry.getKey()));
				bw.write("(");
				bw.write(entry.getValue().toString());
				bw.write(" ");
				bw.write(formatReference(entry.getKey()));
				bw.write("){\n");
				bw.write("        ");
				bw.write("this.");
				bw.write(formatReference(entry.getKey()));
				bw.write("=");
				bw.write(formatReference(entry.getKey()));
				bw.write(";\n    }\n");

				bw.write("\n");

			}
			bw.write("}");

			bw.close();
		
		}

		public Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {

			Map<String, Object> sortedMap = new TreeMap<String, Object>(new Comparator<String>() {
				public int compare(String key1, String key2) {

					return key1.compareTo(key2);
				}
			});
			sortedMap.putAll(oriMap);
			return sortedMap;
		}

		private String capitalUpper(String getset) {
			String s = formatReference(getset);
			char[] chs = s.toCharArray();
			if (chs[0] > 'a' && chs[0] < 'z') {
				chs[0] = (char) (chs[0] - 32);
			}

			return new String(chs);

		}

		private String formatReference(String ref) {

			char[] chs = ref.toCharArray();

			if (chs[0] >= 'A' && chs[0] <= 'Z') {
				chs[0] = (char) (chs[0] + 32);
			}
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(chs[0]);
			for (int i = 1; i < chs.length; i++) {
				if (chs[i] == '_') {
					continue;
				}
				if (chs[i - 1] == '_') {
					if (chs[i] >= 'a' && chs[i] <= 'z') {
						stringBuilder.append((char) (chs[i] - 32));
					} else {
						stringBuilder.append(chs[i]);
					}
				} else {
					stringBuilder.append(chs[i]);
				}
			}

			return stringBuilder.toString();
		}
	}

	public static String fixedName_Json2Bean(String originName) {

		if (originName == null) {
			return originName;
		}
		String oldName = new String(originName);
		String nodeName = originName;

		// nodeName.get(0)=View_font-size-editor 在 .gv 文件中 不能以 - 命名变量
		if (nodeName.contains("-")) {
			nodeName = nodeName.replaceAll("-", "_");
		}

		if (nodeName.contains("!")) {
			nodeName = nodeName.replaceAll("!", "_");
		}
		if (nodeName.contains("+")) {
			nodeName = nodeName.replace("+", "_");
		}

		if (nodeName.contains("/")) {
			nodeName = nodeName.replace("/", "_");
		}

		if (nodeName.contains("\\")) {
			nodeName = nodeName.replace("\\", "_");
		}

		if (nodeName.contains("%")) {
			nodeName = nodeName.replace("%", "_");
		}

		if (nodeName.contains("$")) {
			nodeName = nodeName.replace("$", "_");
		}

		if (nodeName.contains(".")) {
			nodeName = nodeName.replace(".", "_");
		}

		if (nodeName.contains("___")) {
			nodeName = nodeName.replaceAll("___", "_");
		}

		if (nodeName.contains("__")) {
			nodeName = nodeName.replaceAll("__", "_");
		}

		nodeName = nodeName.trim();

		String firstChar = nodeName.substring(0, 1);
		String secondChar = nodeName.replaceAll("_", "").trim();
		if (isNumeric(firstChar) || "".equals(secondChar)) {
			nodeName = "Z_" + getTimeStampLong() + "_" + nodeName;
		}
		System.out.println("oldName = " + oldName + "  newName = " + nodeName);
		return nodeName;

	}

	static volatile String returnString = "";

	static class Json2Bean {
		public String json;
		public static JsonParse jsonParse;
		public String name;
		public static NameGenerator nameGeneration;
		public static BeanGenerator generationBean;
		private ArrayList<ProperityItem> arrList;
		public String fatherName;

		@Override
		public String toString() {
			System.out.println("============== 打印 JavaBean: " + this.name + " 开始==============");
			int index = 0;
			for (ProperityItem item : arrList) {

				System.out.println("index=" + index + "  item=" + item);
				index++;
			}
			System.out.println("============== 打印 JavaBean: " + this.name + " 结束==============");
			return "this.name=" + this.name + "   this.fatherName=" + this.fatherName;
		}

		public Json2Bean(String json, String name, String fatherName, NameGenerator nameGeneration, JsonParse jsonParse,
				BeanGenerator generationBean) {
			this.json = json;
			this.name = fixedName_Json2Bean(name);
			this.nameGeneration = nameGeneration;
			this.jsonParse = jsonParse;
			this.fatherName = fatherName;
			this.generationBean = generationBean;
			arrList = new ArrayList<ProperityItem>();
			// ProperityItem(String ownnerClassName, String properityName, String TypeName,
			// String refClassName)
			// 类本身 所添加的 项
			System.out.println(" 新建Bean类 X1  = " + this.name);
			if (this.fatherName != null) {
				this.fatherName = filter(this.fatherName);
			}

			if (this.name != null) {
				this.name = filter(this.name);
			}

			while (this.fatherName != null && this.fatherName.contains(" ")) {
				this.fatherName = this.fatherName.replaceAll(" ", "");
			}

			while (this.name != null && this.name.contains(" ")) {
				this.name = this.name.replaceAll(" ", "");
			}

			arrList.add(new ProperityItem(null, this.name, "Class", this.fatherName)); // 类创建的Bean 所以该处 不可能 产生
			// make-> 4 refClassName=B properityName=topic
			System.out.println(" 新建Bean类 X2  = " + this.name);
		}

		public static String filter(String content) {
			if (content != null && content.length() > 0) {
				char[] contentCharArr = content.toCharArray();
				for (int i = 0; i < contentCharArr.length; i++) {
					if (contentCharArr[i] < 0x20 || contentCharArr[i] == 0x7F) {
						contentCharArr[i] = 0x20;
					}
				}
				return new String(contentCharArr);
			}
			return "";
		}

		@SuppressWarnings("unchecked")
		public String execute() {
			String curRentName = "";
			//
			try {
				if (this.name != null) {
					curRentName = this.name;
					System.out.println("开始解析Bean类: this.name  " + this.name + "  name =" + name);
					System.out.println("zukgit4_1_1_非空  this.name :" + this.name + "  name =" + name + " curRentName ="
							+ curRentName);

				} else {
					System.out.println("zukgit4_1_2 空  this.name :" + this.name + "  name =" + name + " curRentName ="
							+ curRentName);
				}
				// zukgit2 json ={"source":[],"abstract":[],"title":[[0,4]]}
				System.out.println("zukgit2  json =" + json);
				// zukgit2 json
				// =[[[{"name":"xm1","age":19},{"name":"[xm2","age":19},{"name":"{xm3","age":19}],[{"name":"[[xm4","age":19},{"name":"{{xm5","age":19}]],[[{"name":"xm6","age":19},{"name":"xm7","age":19}],[{"name":"xm8","age":19}]]]
				if (json.startsWith("{}")) { // 为空{} 的对象 创建 Bean AA
					System.out.println("zukgit6_1  ");
					parseMap();
					HashMap curMap = new HashMap();
					arrList.sort(new Comparator<ProperityItem>() {
						@Override
						public int compare(ProperityItem o1, ProperityItem o2) {

							if (o1.isClassList && o2.isBaseList) { // 【类集合】大于【基础属性集合】
								return -1;
							} else if (o1.isClassType && o2.isBaseList) { // 【类基础】大于【基础属性集合】
								return -1;
							} else if (o1.isList && !o2.isList) { // 【列表】大于【非列表】
								return -1;
							} else if (o1.isClassType && o2.isBaseType) {
								return -1;
							} else {
								return 0;
							}

						}
					});

					curMap.put(this.name, arrList);
					Rule17_arrMapList.add(curMap);
					return null;

				} else if (json.startsWith("{")) {
					System.out.println("zukgit3   ");
					parseMap();
					System.out.println("zukgit3_1  ");
					HashMap curMap = new HashMap();
					// zukgit2 json ={"source":[],"abstract":[],"title":[[0,4]]}
					arrList.sort(new Comparator<ProperityItem>() {
						@Override
						public int compare(ProperityItem o1, ProperityItem o2) {

							if (o1.isClassList && o2.isBaseList) { // 【类集合】大于【基础属性集合】
								return -1;
							} else if (o1.isClassType && o2.isBaseList) { // 【类基础】大于【基础属性集合】
								return -1;
							} else if (o1.isList && !o2.isList) { // 【列表】大于【非列表】
								return -1;
							} else if (o1.isClassType && o2.isBaseType) {
								return -1;
							} else {
								return 1;
							}

						}
					});
					curMap.put(this.name, arrList);
					Rule17_arrMapList.add(curMap);
					return null;
				} else if (json.startsWith("[]")) { // 如果当前的 对象为 [] 即为空
					// 获取这个
					System.out.println("AA1 json= " + json + " properItem=" + this.toString()); // [对象1, 对象2]
					return "Object";
				} else if (json.startsWith("[") && json.length() > 2) { // ["小康","社会"]
					System.out.println("zukgit4  "); // [对象1, 对象2]
					String clz = parseArray(); // 解析对象 返回 List<Onjext> xxx 等等
					returnString = new String(clz) + this.fatherName;
					// father_name
					System.out.println("zukgitx8  ");
					if (this.fatherName != null && this.fatherName.equals("RootBean")) { // 继续点
// RootBean_Arr Clz = List<A_B_C>  name= null  fathername=RootBean curRentName=
						System.out.println("ZZXX RootBean_Arr Clz = " + clz + "   name= " + name + "  fathername="
								+ this.fatherName + " curRentName=" + curRentName);
					}
					if (name == null && curRentName.trim().isEmpty()) {
						System.out.println("zukgit4_1_2_空  this.name :" + this.name + "  name =" + name
								+ " curRentName =" + curRentName + " clz=" + clz + "  不会执行 writeList(clz) 方法了  操蛋！");
						System.out.println("zukgitx7  ");
						return clz;
					} else {

						System.out.println("zukgit4_1_1_非空  this.name :" + this.name + "  name =" + name
								+ " curRentName =" + curRentName + " clz=" + clz);
					}

//                    Line 214: zukgit4_1  clz =List<String>name =null
//                    Line 299: zukgit4_1  clz =List<Integer>name =null
//                    Line 301: zukgit4_1  clz =List<>name =null
//                    Line 416: zukgit4_1  clz =List<D>name =null
//                    Line 421: zukgit4_1  clz =List<A_B_C>name =null
//                    Line 467: zukgit4_1  clz =List<E>name =null
					System.out.println("zukgitx6  ");
					System.out.println("zukgit4_2  name != null   非空的情况才会在这里 在这里书写 writeList  zzj");
					generationBean.writeList(clz);
					HashMap curMap = new HashMap();
					if (name != null) {
						System.out.println("zukgitx5  ");
						arrList.sort(new Comparator<ProperityItem>() {
							@Override
							public int compare(ProperityItem o1, ProperityItem o2) {

								if (o1.isClassList && o2.isBaseList) { // 【类集合】大于【基础属性集合】
									return -1;
								} else if (o1.isClassType && o2.isBaseList) { // 【类基础】大于【基础属性集合】
									return -1;
								} else if (o1.isList && !o2.isList) { // 【列表】大于【非列表】
									return -1;
								} else if (o1.isClassType && o2.isBaseType) {
									return -1;
								} else {
									return 1;
								}

							}
						});
						curMap.put(name, arrList);
						Rule17_arrMapList.add(curMap);
						System.out.println("zukgitx4  ");
						return clz;
					} else if (curRentName != null && !curRentName.trim().isEmpty()) {

						arrList.sort(new Comparator<ProperityItem>() {
							@Override
							public int compare(ProperityItem o1, ProperityItem o2) {

								if (o1.isClassList && o2.isBaseList) { // 【类集合】大于【基础属性集合】
									return -1;
								} else if (o1.isClassType && o2.isBaseList) { // 【类基础】大于【基础属性集合】
									return -1;
								} else if (o1.isList && !o2.isList) { // 【列表】大于【非列表】
									return -1;
								} else if (o1.isClassType && o2.isBaseType) {
									return -1;
								} else {
									return 1;
								}

							}
						});
						curMap.put(curRentName, arrList);
						Rule17_arrMapList.add(curMap);
						System.out.println("zukgitx3  ");
						return clz;

					} else {
						System.out.println("zukgitx2  ");
						return clz;
					}
				} else {
					System.out.println("zukgitx1  ");
					System.out.println("开始解析Bean类:" + this.name + " 失败 ");
				}
				return null;
			} catch (Exception e) {
				e.fillInStackTrace();
				System.out.println(" 发生异常 ！ Exception =");
				e.printStackTrace();

			} finally {
				return curRentName;
			}
		}

		private void parseMap() throws IOException {
			// parseMap() json={"source":[],"abstract":[],"title":[[0,4]]}
			Map<String, Object> map = jsonParse.toMap(json);
			System.out.println(" parseMap() json=" + json);
			Iterator<Map.Entry<String, Object>> itr = map.entrySet().iterator();
			String childName_arr = null;
			while (itr.hasNext()) {
				Map.Entry<String, Object> entry = itr.next();
				String k = entry.getKey();
				Object v = entry.getValue();
				if (v == null || v.toString().equals("[]")) {
					// old 逻辑: 如果发现 当前的列表为空的话 就从 属性map 中删除它
					// new 逻辑: 如果发现 当前的列表为空的话 就设置它的属性是 List<Object> null 不需要创建类对象 但需要有 这个 Property
					System.out.println(
							"AA  k =" + k + "   v=" + v + " this.toString()" + this.toString() + "   json=" + json);
//  AA  k =abstract   v=[] this.toString()this.name=Highlight   this.fatherName=B   json={"source":[],"abstract":[],"title":[[0,4]]}
					entry.setValue("List<Object>");

//============== 打印 JavaBean: Highlight 开始==============
//index=0  item=【 ownnerClassName=null  properityName=Highlight TypeName=Class refClassName=B  isBaseType=false  isClassType=true  isList=false baseListCount=0 classListCount=0】
//============== 打印 JavaBean: Highlight 结束==============
//  AA  k =image_list   v=[] this.toString()this.name=B   this.fatherName=null   json=
					// itr.remove(); ProperityItem(String ownnerClassName, String properityName,
					// String TypeName, String refClassName)

// AA  k =source   v=[] this.toString()this.name=Highlight   this.fatherName=B   json={"source":[],"abstract":[],"title":[[0,4]]}
					String ownerClassName = "";
					if (this.fatherName != null && this.name != null) {
						ownerClassName = this.name;
					} else if (this.name == null && this.fatherName != null) {
						ownerClassName = this.fatherName;

					} else if (this.name != null && this.fatherName == null) {

						ownerClassName = this.name;
					} else {
						ownerClassName = null;
					}
					ProperityItem item = new ProperityItem(ownerClassName, k, entry.getValue() + "",
							this.name == null ? this.fatherName : null);
					arrList.add(item);
					continue;
				}
				if (v instanceof Integer) {
					entry.setValue("Integer");
				} else if (v instanceof BigDecimal) {
					entry.setValue("Double");
				} else if (v instanceof String) {
					entry.setValue("String");
				} else {
					String childJson = v.toString(); // key 是 对象中 非基础数据类型 value 可能还是空 需要设置
					if (childJson.endsWith("{}")) { // 空的对象 那么 Object

						String childName = nameGeneration.formatName(k); // nima
						entry.setValue(childName);
						String key = entry.getKey();
						String valueName = new Json2Bean(childJson, childName, this.name, nameGeneration, jsonParse,
								generationBean).execute();
						// entry.setValue("valueName");
						System.out.println("zchildJson=" + childJson + "  valueName=" + valueName + "  key=" + key
								+ "  childName=" + childName + "    this.name=" + this.name);
					} else if (childJson.startsWith("{")) {

						String childName = nameGeneration.formatName(k);
						entry.setValue(childName);
						new Json2Bean(childJson, childName, this.name, nameGeneration, jsonParse, generationBean)
								.execute();
					} else if (childJson.startsWith("[]")) { // 是空的对象的话
						String childName = nameGeneration.formatName(k); // 产生名字
						entry.setValue(childName);
						new Json2Bean(childJson, childName, this.name, nameGeneration, jsonParse, generationBean)
								.execute();

					} else if (childJson.startsWith("[")) {

						childName_arr = new Json2Bean(childJson, null, this.name, nameGeneration, jsonParse,
								generationBean).execute();

						if (childName_arr == null || childName_arr.trim().equals("")) {

							if (returnString != null && !returnString.trim().isEmpty()
									&& returnString.contains(this.name)) {
								String item = new String(returnString);
								item = item.replaceAll(this.name, "");
								childName_arr = item;
							}
						}
						// List<A_B_C> 会走这里 zzj 继续点
						if (((String) k).equals("data")) {
							System.out.println("ZZXX childJson.startsWith parseMap    name[fatherName]=" + name
									+ "    this.name[fatherName]=" + this.name + " key =" + k + "   childName[Clz]="
									+ childName_arr);
							// childJson.startsWith parseMap name=RootBean this.name=RootBean key =data
							// childName=空 这里应该返回 List<A_B_C>
						}

						entry.setValue(childName_arr);

					} else {
						entry.setValue("String");
					}

				}
				// // ProperityItem(String ownnerClassName, String properityName, String
				// TypeName, String refClassName)
				arrList.add(new ProperityItem(this.name, k, entry.getValue() + "",
						this.name == null ? this.fatherName : null));
//         ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName)
				// zkey = topic zvalue = Topic this.fatherName=B childName = null
				// this.name=Extra
				// zkey = topic_2048 zvalue = Topic_2048 this.fatherName=B childName = null
				// this.name=Extra
				System.out.println(" zkey = " + k + " zvalue = " + entry.getValue() + " this.fatherName="
						+ this.fatherName + " childName = " + childName_arr + "  this.name=" + this.name);

			}

			System.out.println("writeBean  Aname =" + name);
			generationBean.writeBean(name, map);

		}

		public static boolean isContainChinese(String str) {
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher m = p.matcher(str);
			if (m.find()) {
				return true;
			}
			return false;
		}

		private String parseArray() throws IOException {
			List<Object> list = jsonParse.toArray(json); // ["小康","社会"]
			if (list == null || list.size() == 0) {
				return null;
			}

			Object firstObj = list.get(0);
			System.out.println("Zukgit firstObj =  " + firstObj.toString());

			if (firstObj instanceof Integer) {
				for (int i = 1; i < list.size(); i++) {
					Object v = list.get(i);
					if (v instanceof BigDecimal) {
						System.out.println(" childName 4=  List<Double>");
						// returnStr = "List<Double>";
						return "List<Double>";
					}
				}
				System.out.println(" childName 5 =  List<Integer>");
				// returnStr = "List<Double>";
				return "List<Integer>";
			} else if (firstObj instanceof BigDecimal) {
				System.out.println(" childName 6=  List<Double>");
				// returnStr = "List<Double>";
				return "List<Double>";

			} else if (firstObj instanceof String) {
				System.out.println(" childName 7=  List<String>");
				// returnStr = "List<Double>";
				return "List<String>";
			}

			if (!firstObj.toString().startsWith("{") && !firstObj.toString().startsWith("[")) {

				return "List<String>";
			}

			String childName = "";
			String arrReturenString = "";
			// Object v = list.get(0); // 只拿取 [{1},{2},{3} ....] 只拿取第一个 对象 但往往有些Json的 第一个位置
			// 第二个位置 第三个位置 往往是不同的对象
			// 在这里要判断 它的数组里面的属性是否是相同的 相同的话 取一个分析 就可以 数组对象属性不同的话 那么就要每个拿出来分析 并新创建对象

			List<Object> diffObjectInArr = checkObjectSame(list); //
			System.out.println("diffObjectInArr  =  " + diffObjectInArr.size());
			String returnStr = "";
			if (diffObjectInArr != null) {
				if (diffObjectInArr.size() == 1) {
					Object v = diffObjectInArr.get(0);
					if (v instanceof Integer) {
						for (int i = 1; i < list.size(); i++) {
							v = list.get(i);
							if (v instanceof BigDecimal) {
								System.out.println(" childName 8=  List<Double>");
								returnStr = "List<Double>";
								return "List<Double>";
							}
						}
						System.out.println(" childName 99 =  List<Integer>");
						returnStr = "List<Double>";
						return "List<Integer>";
					} else if (v instanceof BigDecimal) {
						System.out.println(" childName 10=  List<Double>");
						returnStr = "List<Double>";
						return "List<Double>";

					} else if (v instanceof String) {
						System.out.println(" childName 11=  List<String>");
						returnStr = "List<Double>";
						return "List<String>";
					} else {
						String childJson = v.toString();

						if (childJson.startsWith("{}")) {
							return "List<Object>";
						} else if (childJson.startsWith("{")) {
							childName = nameGeneration.nextName();

							new Json2Bean(childJson, childName, name, nameGeneration, jsonParse, generationBean)
									.execute();
							System.out.println(" childName  12= " + childName);
							// arrList.add("List<" + childName + ">");
							returnStr = "List<" + childName + ">";
							return "List<" + childName + ">";
						} else if (childJson.startsWith("[")) {
							childName = new Json2Bean(childJson, null, this.name, nameGeneration, jsonParse,
									generationBean).execute();
							System.out.println(" childName 13= " + childName); // 这里返回为空 curRentName = clz=List<>
							// arrList.add("List<" + childName + ">");
							if (childName == null || childName.trim().isEmpty() || childName.trim().equals("null")) {
								childName = "Object";
							}
							returnStr = "List<" + childName + ">";
							return "List<" + childName + ">";

						} else {
							System.out.println(" childName 14= " + childName);
							// arrList.add("List<String>" + childName);
							returnStr = "List<" + childName + ">";
							return "List<String>";
						}

					}
				} else {
					int ZCount = 0;
//  对于多个 数组中 多个   Object的 处理

					for (Object object : diffObjectInArr) {

//                        String childJson = object.toString();
//                        String className = nameGeneration.nextName();
//
//                        System.out.println("ZCount=" + ZCount + " iffObjectInArr.size =" + diffObjectInArr.size() + " this.name=" + name + "    Zukgit  childName = " + childName + " className =" + className + " childJson=" + childJson);
//                        new Json2Bean(childJson, className, this.name, nameGeneration, jsonParse, generationBean).execute();
//                        System.out.println("ZCount=" + ZCount + "   Zukgit  childJson = " + childJson);
//                        ZCount++;

						String childJson = object.toString();
						System.out.println("ZCount = " + ZCount + "   Zukgit_Object  childJson= " + childJson);
						System.out.println(" zukgit childJson = " + childJson);

						if (childJson.startsWith("{}")) {
							arrReturenString = "List<Object>";
						} else if (childJson.startsWith("{")) {
							childName = nameGeneration.nextName();

							new Json2Bean(childJson, childName, name, nameGeneration, jsonParse, generationBean)
									.execute();

							// arrList.add("List<" + childName + ">");
							curIndexStr = curIndexStr + "\\n" + childName; // /nA[][][][]
							int diffObjectSize = diffObjectInArr.size();
							int indexArrSize = classArr.size(); // <A___B___C>/nA[][][][]/nB[][][]/nC[][][] 的位置不对
							if (diffObjectSize == indexArrSize) {
								ArrayList<Integer> curIndexArr = classArr.get(ZCount);
								for (Integer intx : curIndexArr) {
									curIndexStr = curIndexStr + "[" + intx + "]";
								}
							}

							// A___B___C ==> A[1]___B[2]___C[3]
							arrReturenString = arrReturenString + childName + "___"; // <A___B___C>/nA[][][][]/nB[][][]/nC[][][]
							System.out.println(
									"diffObjectInArr.size" + diffObjectInArr.size() + "   childName 1= " + childName
											+ "  arrReturenString" + arrReturenString + "    childJson= " + childJson);

//                            diffObjectInArr.size3   childName 1= A  arrReturenStringA___
//                            diffObjectInArr.size3   childName 1= B  arrReturenStringA___B___
//                            diffObjectInArr.size3   childName 1= C  arrReturenStringA___B___C___

							// return "List<" + childName + ">"; // 这里没有返回 "List<" + childName + ">"; 导致 程序中
							// 缺 List<>
						} else if (childJson.startsWith("[")) {
							childName = new Json2Bean(childJson, null, this.name, nameGeneration, jsonParse,
									generationBean).execute();
							System.out.println(" childName 2= " + childName);
							// arrList.add("List<" + childName + ">");
							arrReturenString = "【" + childName + "】" + arrReturenString;
							// return "List<" + childName + ">";

						} else {
							System.out.println(" childName 3= " + childName);
							// arrList.add("List<String>" + childName);
							arrReturenString = "【" + childName + "】";
							return "List<String>";
						}
						ZCount++;
					}
					classArr.clear();
				}
			}

			while (arrReturenString.endsWith("_")) {
				arrReturenString = arrReturenString.substring(0, arrReturenString.length() - 1);

			}
			if (arrReturenString.trim().isEmpty()) {
				arrReturenString = "Object";
			}
			String endResult = "List<" + arrReturenString + ">";
			if (!curIndexStr.trim().isEmpty()) {
				endResult = endResult + curIndexStr;
			}

			// endResult=List<【C】【B】【A】>\nA[1][2]\n[3][4][5]\n[6][7][8]
			System.out.println("zendResult=" + endResult + "arrReturenString =" + arrReturenString
					+ " nima ######################");
			return endResult;
		}

		static String curIndexStr = ""; // \nA[1][2]\nB[3]\nC[4]

		static List<Object> checkObjectSame(List<Object> srcList) { // 对象和对象相同
			// 来到这里的都是 [{},{},{}] 样式的数据

			boolean isDiffClass = false;
			List<Object> temp = new ArrayList<Object>();
			int length = srcList.size();
			if (length == 0) {
				return null;
			}
			int index_y = 0;
			System.out.println("=========== Json 原始长度 srcList.length = " + srcList.size() + "===========Begin ");
			for (Object object : srcList) {
				System.out.println("index =" + index_y + "  object =" + object.toString());
				index_y++;

			}
			System.out.println("=========== Json 原始长度 srcList.length = " + srcList.size() + "===========End ");
			Object firstObject = srcList.get(0);
			HashSet<Set<String>> diffObjSet = new HashSet<Set<String>>();
			ArrayList<Integer> indexList = new ArrayList<Integer>();
			for (int index = 0; index < length; index++) {
				Object curObject = srcList.get(index);
				if (curObject instanceof JSONObject && firstObject instanceof JSONObject) {
					Set<String> curKeySet = ((JSONObject) curObject).keySet();
					Set<String> firstKeySet = ((JSONObject) firstObject).keySet();

					if (!firstKeySet.equals(curKeySet)) {

						isDiffClass = true;
					} else {
						isDiffClass = false;
					}

					// 判断 数据列表中 有多少个 Object 的 缺斤少两的 同一归为一个Object
//                    if (curKeySet.equals(firstKeySet)) {
//                        if (!diffObjSet.contains(curKeySet)) {
//                            indexList.add(index);
//                            System.out.println("当前 index = " + index + "    相同的 KeySet 键值集合  ");
//                            diffObjSet.add(curKeySet);  // 如果 相同 则 放入
//                        }
//                    } else {
//                        if (!diffObjSet.contains(curKeySet) && checkMaxValue(diffObjSet, curKeySet)) {
//                            diffObjSet.add(curKeySet);
//                            indexList.add(index);
//                            System.out.println("当前 index = " + index + "    不相同的 KeySet 键值集合  ");
//                        }
//                        System.out.println("难道这里会打印！ ");
//                    }

				} else {

					System.out.println(
							"当前 index = " + index + "     不能转为  JSONObject?  curObject= " + curObject.toString());
				}

			}
			System.out.println("isDiffClass = " + isDiffClass);

			if (!isDiffClass) {
				temp.add(srcList.get(0));
				return temp;

			}
			// 处理 那些 不同 Object [{A:1,B:2},{C:3,D:4}......] 样式的数据
			temp = toCheckDiff(srcList);

			return temp;
		}

		static ArrayList<ArrayList<Integer>> classArr = new ArrayList<ArrayList<Integer>>();

		@SuppressWarnings("unchecked")
		static List<Object> toCheckDiff(List<Object> srcList) {
			List<Object> temp = new ArrayList<Object>();
			int length = srcList.size();
			// 获取到 所有的 Key 以及 所有的Value
//   两个集合的交集大于 当前size的一半  那么 就认为 它们是同一个类
			// 两次 循环 找出 和自己 相似的那个 集合 然后 把 它们的 索引放到一起
			Set<String> allSetKey = new HashSet<String>();
			Map<Integer, Integer> intMap = new HashMap<Integer, Integer>();
			int classCategory = 0;
			// 【1,3】 【1,4】 【1,5】 【1,6】
			// 【2,7】【2，8】
			// 【3,9】【3，10】

			for (int i = 0; i < length; i++) {
				JSONObject curObjectA = (JSONObject) srcList.get(i);
				allSetKey.addAll(curObjectA.keySet());
				for (int j = 0; j < length; j++) {
					if (i == j) {
						continue;
					}
					JSONObject curObjectB = (JSONObject) srcList.get(j);
					Set<String> aSet = new HashSet<String>(curObjectA.keySet());
					Set<String> bSet = new HashSet<String>(curObjectB.keySet());
					int aSize = aSet.size();
					int bSize = aSet.size();
					if (aSet.size() >= bSet.size()) { // 总是以大Set去 交集 小的Set
						aSet.retainAll(bSet);
						if (aSet.size() > (aSize / 2)) {
							intMap.put(i, j);
						}
					} else {
						bSet.retainAll(aSet);
						if (bSet.size() > (bSize / 2)) {
							intMap.put(i, j);
						}
					}
				}
			}

			Map.Entry<Integer, Integer> entry;

			Iterator iterator = intMap.entrySet().iterator();

			int xIndex = 0;
			while (iterator.hasNext()) {
				entry = (Map.Entry<Integer, Integer>) iterator.next();
				Integer key = entry.getKey(); // Map的Value
				Integer value = entry.getValue(); // Map的Value
				System.out.println("【 key=" + key + "  value=" + value + "】");
				if (xIndex == 0) {
					ArrayList<Integer> arrItem = new ArrayList<Integer>();
					arrItem.add(key);
					arrItem.add(value);
					classArr.add(arrItem);
					xIndex++;
					continue;
				}

				ListIterator<ArrayList<Integer>> outArray = classArr.listIterator();
				// for(ArrayList<Integer> arrBean : classArr){
				while (outArray.hasNext()) {

					ArrayList<Integer> arrBean = (ArrayList<Integer>) outArray.next();
//                    for(Integer intIndex : arrBean){
//                        System.out.println("intIndex = "+ intIndex);
//
// if(arrBean.contains(key) || arrBean.contains(value) || intIndex == key || intIndex == value){
//                            arrBean.add(key);
//                            arrBean.add(value);
//                        }else{
//                            ArrayList<Integer> newItem = new  ArrayList<Integer>();
//                            newItem.add(key);
//                            newItem.add(value);
//     classArr.add(newItem);
//                        }
//

					// Iterator<Integer> iteratorInteger = arrBean.iterator();
					ListIterator<Integer> iteratorInteger = arrBean.listIterator();
					while (iteratorInteger.hasNext()) {
						Integer curInteger = iteratorInteger.next();
						System.out.println("curInteger  = " + curInteger);
						if (arrBean.contains(key) || arrBean.contains(value) || curInteger == key
								|| curInteger == value) {
							// 循环中 增加
							if (!arrBean.contains(key) && arrBean.contains(value)) {
								iteratorInteger.add(key);
								break;
							}
							if (!arrBean.contains(value) && arrBean.contains(key)) {
								iteratorInteger.add(value);
								break;
							}

						} else {

// 【 key=9  value=19】
//curInteger  = 1
//
//
//【 key=19  value=9】
// curInteger  = 1

							ArrayList<Integer> arrItemNew = new ArrayList<Integer>();
							if (key < value) {
								arrItemNew.add(key);
								arrItemNew.add(value);
							} else {
								arrItemNew.add(value);
								arrItemNew.add(key);
							}
							if (!classArr.contains(arrItemNew)) {
								outArray.add(arrItemNew);
								break;
							}
							continue;

						}
					}
					break;

				}
			}

// 没有包含索引的那个类 是单独的类
//  具有相同 Value , 以及 该Value 对应的 Key  为 相同的类
			System.out.println("classArr.size = " + classArr.size());

			for (int index_value = 0; index_value < classArr.size(); index_value++) {
				System.out.println("=============数组 ==" + index_value + " Begin=============");
				Collections.sort(classArr.get(index_value));
				for (Integer item : classArr.get(index_value)) {
					System.out.println("item = " + item);
				}
				System.out.println("=============数组 ==" + index_value + " End=============");
			}

			for (int y = 0; y < length; y++) {
				if (intMap.get(y) == null) { // 说明 该索引对应的 {} 为单独存在的 不与其他的项相同
					temp.add(srcList.get(y));
				}
// 存在相同的项的话  就从  classArr 中寻找那个 KeySet 最大的
			}

			List<Object> sameObjectMax = getSameObjectMax(srcList, classArr);

			for (int y = 0; y < length; y++) {
				if (intMap.get(y) == null) { // 说明 该索引对应的 {} 为单独存在的 不与其他的项相同
					// 往 ArrayList<ArrayList<Integer>> classArr 也 添加这个索引 单独在一个ArrayList
					ArrayList<Integer> newItem_alone = new ArrayList<Integer>();
					newItem_alone.add(y);
					classArr.add(newItem_alone);
				}
// 存在相同的项的话  就从  classArr 中寻找那个 KeySet 最大的
			}

			classArr.sort(new Comparator<ArrayList<Integer>>() {
				@Override
				public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
					int minIndexO1 = 0;
					int minIndexO2 = 0;

					for (int x = 0; x < o1.size(); x++) {
						int temp = o1.get(x);
						if (x == 0) {
							minIndexO1 = o1.get(x);
						}
						if (temp < minIndexO1) {
							minIndexO1 = temp;
						}
					}
					for (int x = 0; x < o2.size(); x++) {
						int temp = o2.get(x);
						if (x == 0) {
							minIndexO2 = o2.get(x);
						}
						if (temp < minIndexO2) {
							minIndexO2 = temp;
						}
					}

					if (minIndexO1 < minIndexO2) {
						return -1;
					} else {
						return 1;
					}
				}
			});
			if (sameObjectMax != null) {

				System.out.println("sameObjectMax.size = " + sameObjectMax.size());
				temp.addAll(sameObjectMax);
			}

//【 key=1  value=18】
//【 key=2  value=18】
//【 key=3  value=18】
//【 key=4  value=18】
//【 key=5  value=18】
//【 key=6  value=18】
//【 key=7  value=18】
//【 key=8  value=18】、
//【 key=10  value=18】
//【 key=11  value=18】
//【 key=12  value=18】
//【 key=13  value=18】
//【 key=14  value=18】
//【 key=15  value=18】
//【 key=16  value=18】
//【 key=17  value=18】
//【 key=18  value=17】
//
//【 key=9  value=19】
//【 key=19  value=9】
			return temp;
		}

		static List<Object> getSameObjectMax(List<Object> srcList, ArrayList<ArrayList<Integer>> classArr) {
			List<Object> itemList = new ArrayList<Object>();
			System.out
					.println("maxLenthObj= X2  classArr.size()=" + classArr.size() + " srcList.size" + srcList.size());
			for (int index_value = 0; index_value < classArr.size(); index_value++) {
				JSONObject maxLenthObj = null;
				System.out.println("maxLenthObj= X1");

				for (Integer index_value_y : classArr.get(index_value)) {
					if (maxLenthObj == null) {
						maxLenthObj = (JSONObject) srcList.get(classArr.get(index_value).get(0));
					}
					System.out.println("index_value:" + index_value + "    index_value_y=" + index_value_y);
					JSONObject currentObj = (JSONObject) srcList.get(index_value_y);
					if (currentObj == null) {
						System.out.println("尼玛 空 currentObj= " + currentObj);
						continue;
					}

					int curLength = currentObj.keySet().size();
					int maxLength = maxLenthObj.keySet().size();
					if (curLength >= maxLength) {
						maxLenthObj = currentObj;
					}

				}
				itemList.add(maxLenthObj);
				System.out.println("maxLenthObj= " + maxLenthObj);
			}
			System.out.println("maxLenthObj= X3");
			return itemList;
		}

		static boolean checkMaxValue(HashSet<Set<String>> hashset, Set<String> itemSet) {
			boolean flag = false;
			Iterator<Set<String>> it = hashset.iterator();
			while (it.hasNext()) {
				Set<String> curSet = it.next();
				// 如果当前 添加的到 hashset的 项 set<String> 包含 当前循环找到的 set<String> 那么删除 这个 当前循环到的
				// set<String>
				if (itemSet.containsAll(curSet)) {
					hashset.remove(curSet);
					flag = true;
				} else if (curSet.containsAll(itemSet)) { // 如果 添加的到 hashset的 已经有 item 完全 包含 那么就 不添加该项
					flag = false;
					return false;
				} else {
					// 【data[16] 有 60项 】 data[13]有 56项 但是 data[16] 的 60项中却没有 data[13] 中的 summary 这项
					// curSet.retainAll()
					// 那么 把当前的 扫描的 Item 和 当前参数的 Item 合并 并把 当前item 删除

					Set<String> result = new HashSet<String>();
					result.addAll(itemSet);
					result.addAll(curSet);
					hashset.remove(curSet);
					hashset.add(result);
					flag = false;
				}
			}

			return flag;
		}

	}

	static class JsonFormat {

		/**
		 * 默认每次缩进两个空格
		 */
		private static final String empty = "  ";

		public static String format(String json) {
			try {
				json = removeUnUsedSpaces(json);
				int empty = 0;
				char[] chs = json.toCharArray();
				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < chs.length;) {
					// 若是双引号，则为字符串，下面if语句会处理该字符串
					if (chs[i] == '\"') {

						stringBuilder.append(chs[i]);
						i++;
						// 查找字符串结束位置
						for (; i < chs.length;) {
							// 如果当前字符是双引号，且前面有连续的偶数个\，说明字符串结束
							if (chs[i] == '\"' && isDoubleSerialBackslash(chs, i - 1)) {
								stringBuilder.append(chs[i]);
								i++;
								break;
							} else {
								stringBuilder.append(chs[i]);
								i++;
							}

						}
					} else if (chs[i] == ',') {
						stringBuilder.append(',').append('\n').append(getEmpty(empty));

						i++;
					} else if (chs[i] == '{' || chs[i] == '[') {
						empty++;
						stringBuilder.append(chs[i]).append('\n').append(getEmpty(empty));

						i++;
					} else if (chs[i] == '}' || chs[i] == ']') {
						empty--;
						stringBuilder.append('\n').append(getEmpty(empty)).append(chs[i]);

						i++;
					} else {
						stringBuilder.append(chs[i]);
						i++;
					}

				}

				return stringBuilder.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return json;
			}

		}

		static boolean isDoubleSerialBackslash(char[] chs, int i) {
			int count = 0;
			for (int j = i; j > -1; j--) {
				if (chs[j] == '\\') {
					count++;
				} else {
					return count % 2 == 0;
				}
			}

			return count % 2 == 0;
		}

		/**
		 * 缩进
		 *
		 * @param count
		 * @return
		 */
		static String getEmpty(int count) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < count; i++) {
				stringBuilder.append(empty);
			}

			return stringBuilder.toString();
		}

		static String removeUnUsedSpaces(String json) {
			char[] chs = json.toCharArray();
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < chs.length;) {
				// 若是双引号，则为字符串，下面if语句会处理该字符串
				if (chs[i] == '\"') {

					stringBuilder.append(chs[i]);
					i++;
					// 查找字符串结束位置
					for (; i < chs.length;) {
						// 如果当前字符是双引号，且前面有连续的偶数个\，说明字符串结束
						if (chs[i] == '\"' && isDoubleSerialBackslash(chs, i - 1)) {
							stringBuilder.append(chs[i]);
							i++;
							break;
						} else {
							stringBuilder.append(chs[i]);
							i++;
						}

					}
				} else {

					if (chs[i] == ' ' || chs[i] == '\t' || chs[i] == '\n') {
						i++;
						continue;
					}

					stringBuilder.append(chs[i]);
					i++;
				}

			}

			return stringBuilder.toString();
		}

	}

	static String Rule17_OutJava_Dir_TimeStampStr = null;
	static String Rule17_OutRealJava_Path = null;

	public interface JsonParse {
		Map<String, Object> toMap(String json);

		List<Object> toArray(String json);
	}

	public static class MyBeanGenerator implements BeanGenerator {

		String packName;

		public MyBeanGenerator(String packName) {
			// TODO Auto-generated constructor stub
			this.packName = packName;
		}

		static String[] javaCode = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
				"const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
				"for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new",
				"package", "private", "protected", "public", "return", "strictfp", "short", "static", "super", "switch",
				"synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while" };

		static ArrayList<String> javaCodeArr = new ArrayList<String>();
		static {
			Collections.addAll(javaCodeArr, javaCode);
		}

		@Override
		public void writeBean(String className, Map<String, Object> map) throws IOException {
// Topic048.java  zzj               执行该类
			if (Rule17_OutJava_Dir_TimeStampStr == null) {
				Rule17_OutJava_Dir_TimeStampStr = getTimeStampLong() + "";
			}
			System.out.println(" MyBeanGenerator  className  =" + className);
			System.out.println("writeBean  className =" + className + "packName = " + packName);
			while (className.contains(" ")) {
				className = className.replaceAll(" ", "");
			}
			File file = new File(I9_OUT_DIR + File.separator + Rule17_OutJava_Dir_TimeStampStr + File.separator
					+ packName.replace(".", "/"));
			System.out.println("writeBean  file =" + file.getAbsolutePath());
			if (!file.exists() || file.exists() && file.isFile()) {
				file.mkdirs();
			}
			if (Rule17_OutRealJava_Path == null) {
				Rule17_OutRealJava_Path = file.getAbsolutePath();
			}
			File javaFile = new File(file, className + ".java");
			System.out.println("javaFIle Path = " + javaFile.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(new FileWriter(javaFile));
			bw.write("package ");
			bw.write(packName);
			bw.write(";\n");
			bw.write("import java.util.List;\n\n");

			bw.write("/**\n");
			bw.write(" *auto generate\n");
			bw.write(" *\n");
			bw.write(" *@author Zukgit\n");
			bw.write(" *\n");
			bw.write(" */\n");

			bw.write("public class ");
			bw.write(className);
			bw.write("{\n");
			map = sortMapByKey(map);
			Set<Map.Entry<String, Object>> set = map.entrySet();

			for (Map.Entry<String, Object> entry : set) {
				bw.write("    ");

				String value = entry.getValue().toString();
				System.out.println(" zvaluepre = " + value);
				if (value.contains("\\n")) {
					value = value.substring(0, value.indexOf("\\n"));
				}
				System.out.println(" zvalueback = " + value);
				if (value.contains("List") && value.contains("___")) {
					// List<A___B___C> == > List<Object>
					String pre = value.substring(0, value.indexOf("___") - 1);
					String back = value.substring(value.lastIndexOf("___") + "___".length() + 1, value.length());
					value = pre + "Object" + back; // List<AObject___B___C>
				}
				System.out.println(" zvalue = " + value);
				bw.write(value);
// entry.getValue().key = abstract
				System.out.println("X1  className  =" + className + " entry.getValue().toString() ="
						+ entry.getValue().toString() + " entry.getValue().key = " + entry.getKey());
				// X1 MyBeanGenerator className =RootBean entry.getValue().toString()
				// =List<A___B___C>\nA[1][2][3][4][5][6][7]
				bw.write(" ");
				String key = entry.getKey();
				if (javaCodeArr.contains(key)) {
					key = key + "_javacode";
				}
				bw.write(key);
				bw.write(";\n");
			}
			bw.write("\n");

			set = map.entrySet();

			for (Map.Entry<String, Object> entry : set) {

				bw.write("    public ");
				String value1 = entry.getValue().toString();
				System.out.println(" zvaluepre  value1 = " + value1);
				if (value1.contains("\\n")) {
					value1 = value1.substring(0, value1.indexOf("\\n"));
				}
				System.out.println(" zvalueback  value1 = " + value1);
				if (value1.contains("List") && value1.contains("___")) {
					// List<A___B___C> == > List<Object>
					String pre = value1.substring(0, value1.indexOf("___") - 1);
					String back = value1.substring(value1.lastIndexOf("___") + "___".length() + 1, value1.length());
					value1 = pre + "Object" + back; // List<AObject___C>
				}
				bw.write(value1);
				System.out.println(" zvalue1 = " + value1);
				// // entry.getValue().toString() = abstract
				System.out.println("X2  MyBeanGenerator  x2 className  =" + className + " entry.getValue().toString() ="
						+ entry.getValue().toString());
				bw.write(" get");
				String key = entry.getKey();
				if (javaCodeArr.contains(key)) {
					key = key + "_javacode";
				}

				// 如果 当前的 value 包含 List 和 ___ 那么获取 第一个 ___ 之前的位置 最后一个 ___之后的位置, 截取这个字符串 中间加
				// List<Object>

// entry.getValue().toString() =List<A_B_C>
				bw.write(capitalUpperCase(key));
				System.out.println("X3  MyBeanGenerator x3  className  =" + className + " entry.getValue().toString() ="
						+ entry.getValue().toString() + "  capitalUpperCase(entry.getKey()) ="
						+ capitalUpperCase(entry.getKey()));

				bw.write("(){\n");
				bw.write("        ");
				bw.write("return ");
				bw.write(key);

				bw.write(";\n    }\n\n");

				//////////////////////////

				bw.write("    public void ");
				bw.write("set"); // 设置方法

				String key_set = entry.getKey();
				if (javaCodeArr.contains(key_set)) {
					key_set = key_set + "_javacode";
				}

				bw.write(capitalUpperCase(key_set));

				bw.write("(");
				bw.write(value1);
				bw.write(" ");
				bw.write(key_set);
				bw.write("){\n");
				bw.write("        ");
				bw.write("this.");
				bw.write(key_set);
				bw.write("=");
				bw.write(key_set);
				bw.write(";\n    }\n");

				bw.write("\n");

			}
			bw.write("}");

			bw.close();
			System.out.println("javaFIle Path = " + javaFile.getAbsolutePath());
			
			rule17_resultFile_List.add(javaFile);
		}

		private String capitalUpperCase(String s) {
			char[] chs = s.toCharArray();
			if (chs[0] >= 'a' && chs[0] <= 'z') {
				chs[0] = (char) (chs[0] - 32);
			}
			return new String(chs);

		}

		@Override
		public void writeList(String list) throws IOException {

			System.out.println("className1  writeList  = " + list); // zzj
			File file = new File("src/" + packName.replace(".", "/"));
			if (!file.exists() || file.exists() && file.isFile()) {
				file.mkdirs();
			}

			BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File(file, list.replaceAll("<|>", "_") + ".txt")));

			bw.write(list);
			bw.write(";");

			bw.close();

		}

		public Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {

			Map<String, Object> sortedMap = new TreeMap<String, Object>(new Comparator<String>() {
				public int compare(String key1, String key2) {

					return key1.compareTo(key2);
				}
			});
			sortedMap.putAll(oriMap);
			return sortedMap;
		}

	}

	@SuppressWarnings("unchecked")
	public static void ShowMap_String_String(Map<String, String> mMapParam, String tag) {
		Map.Entry<String, String> entryItem;

		System.out.println(
				"════════════════════ Map<String,String> 大小:" + mMapParam.size() + " " + "════════════════════ ");
		int index = 0;
		if (mMapParam != null) {
			Iterator iterator = mMapParam.entrySet().iterator();
			while (iterator.hasNext()) {
				entryItem = (Map.Entry<String, String>) iterator.next();

				String key = entryItem.getKey(); // Map的Key
				String value = entryItem.getValue(); // Map的Value
				System.out.println("Map_index[" + index + "]  key=[" + key + "]   value=[" + value + "]");
				index++;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void ShowMap_Int_ArrInt(Map<Integer, ArrayList<Integer>> mMapParam) {
		Map.Entry<Integer, ArrayList<Integer>> entryItem;
		int Firstindex = 0;
		if (mMapParam != null) {
			Iterator iterator = mMapParam.entrySet().iterator();
			while (iterator.hasNext()) {
				entryItem = (Map.Entry<Integer, ArrayList<Integer>>) iterator.next();
				Integer year = entryItem.getKey(); // Map的Key
				ArrayList<Integer> year_day = entryItem.getValue(); // Map的Value
				int SecondIndex = 0;
				System.out.println("========== MapIndex[" + Firstindex + "] " + year + "  =========");
				for (int i = 0; i < year_day.size(); i++) {
					System.out.println("MapIndex[" + Firstindex + "] ValueIndex=[" + SecondIndex + "]" + " key=[" + year
							+ "]  value=[" + year_day.get(i) + "] ");
					SecondIndex++;
				}
				Firstindex++;
			}
		}
	}

	public static class MyJsonParser implements JsonParse {

		@SuppressWarnings("unchecked")
		@Override
		public Map<String, Object> toMap(String json) {
			return JSON.parseObject(json, Map.class);
		}

		@Override
		public List<Object> toArray(String json) {
			return JSON.parseArray(json, Object.class);
		}

	}

	public static class MyNameGenerator implements NameGenerator {

		String names[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "JJ", "KK",
				"LL", "MM", "NN" };
		int posiiotn;

		@Override
		public String nextName() {

			return names[posiiotn++];
		}

		// 字符串转换为ascii
		public static String StringToA(String content) {
			String result = "";
			int max = content.length();
			for (int i = 0; i < max; i++) {
				char c = content.charAt(i);
				int b = (int) c;
				result = result + b;
			}
			return result;
		}

		// ascii转换为string
		public static String AToString(int i) {
			return Character.toString((char) i);
		}

		@Override
		public String formatName(String name) {
			char[] chs = name.toCharArray();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(chs[0]);
			System.out.println("chs[0] =" + +chs[0]);
			for (int i = 1; i < chs.length; i++) {
				System.out.println("chs[" + i + "] =" + +chs[i]);
				if (chs[i - 1] == '_') { // 如果当前查询是_ 那么就把后面的 转为大写 _ 对应的ACSII码 是 95
					if (!isNum(AToString(chs[i])) && ("" + AToString(chs[i])).matches("^[a-z,A-Z].*$")) {
						stringBuilder.append((char) (chs[i] - 32));
						System.out.println("test run!  +chs[i] =" + +chs[i]);
					} else {
						stringBuilder.append(chs[i]);
					}
				} else
					stringBuilder.append(chs[i]);
			}
			// String s = stringBuilder.toString().replace("_", "");
			String s = stringBuilder.toString();
			chs = s.toCharArray();
			if (chs[0] >= 'a' && chs[0] <= 'z') {
				chs[0] = (char) (chs[0] - 32);
			}

			return new String(chs);
		}

		public static boolean isNum(String str) {
			return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		}

	}

	public interface NameGenerator {
		String nextName();

		/**
		 * 格式化标识符，驼峰式写法
		 *
		 * @param name
		 * @return
		 */
		String formatName(String name);
	}

	static boolean is16jinzhi(String s) {
		boolean is16jinzhi = false;
		String regex = "^[A-Fa-f0-9]+$";

		if (s.matches(regex)) {

			System.out.println(s.toUpperCase() + "是16进制数");
			is16jinzhi = true;
		} else {

			System.out.println(s.toUpperCase() + "不是16进制数");
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

	static boolean isStartWith_lower_trim_InArr(ArrayList<String> strList, String strValue) {
		boolean isContain = false;
		if (strList == null || strList.size() == 0) {
			return isContain;
		}
		for (int i = 0; i < strList.size(); i++) {
			String strItem = strList.get(i).toLowerCase().trim();

			if (strItem.startsWith(strValue.toLowerCase().trim())) {
				return true;
			}

		}

		return isContain;
	}

	static String get12size(int i) {
		String result = "" + i;
		int padding = 12 - result.length();

		if (padding > 0) {

			for (int j = 0; j < padding; j++) {
				result = "0" + result;

			}

		}

		return result;

	}

	public static String dumpHexString(byte[] array, StringBuilder xBinarySB, StringBuilder xAsciiSB,
			StringBuilder xHexSB) {
		if (array == null)
			return "(null)";
		return dumpHexString(array, 0, array.length, xBinarySB, xAsciiSB, xHexSB);
	}

	private final static char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };
	private final static char[] HEX_LOWER_CASE_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f' };

	static int byteIndex = 0;

	public static String dumpHexString(byte[] array, int offset, int length, StringBuilder mBinarySB,
			StringBuilder mAsciiSB, StringBuilder mHexSB) {
		if (array == null)
			return "(null)";
		StringBuilder result = new StringBuilder();

		byte[] line = new byte[16];
		int lineIndex = 0;

		result.append("\n0x");
		result.append(toHexString(offset));

		// 把 int offset 转为 10 进制 10位数值

		for (int i = offset; i < offset + length; i++) {
			if (lineIndex == 16) {
				result.append(" ");
				ArrayList<Byte> byteList = new ArrayList<Byte>();
				for (int j = 0; j < 16; j++) {
					byteList.add(line[j]);
					if (line[j] >= ' ' && line[j] <= '~') {
						result.append(new String(line, j, 1));
					} else {

						if (line[j] == 0x00) { // 0x00 NUL(null) 空字符 ●

							result.append("●");
						} else if (line[j] == 0x0A) { // 0x0A 换行符 ▲
							result.append("▲");
						} else if (line[j] == 0x0D) { // 0D-■ 回车
							result.append("■");
						} else if (line[j] == 0x09) { // 09 - ◆水平制表符 相当于 Tab
							result.append("◆");
						} else if (line[j] == 0x0B) { // 0x0B -┓ VT (vertical tab) 垂直制表符
							result.append("┓");
						} else if (line[j] == 0x1F) { // █ 0x1F 单元分隔符
							result.append("█");
						} else if (line[j] == 0x0C) { // 0x0C ▼ FF (NP form feed, new page) 换页键
							result.append("▼");
						} else {
							result.append(".");
						}
					}
					if (j == 15) { // 最后字符显示一个分割线
						result.append("  ║ "); // 这里显示的是 字节信息
						for (int k = 0; k < byteList.size(); k++) {
							byte curByte = byteList.get(k);

							String targetChar = "";
							if (curByte >= ' ' && curByte <= '~') {
								targetChar = new String(line, k, 1);
							} else {
//                                targetChar = ".";

								if (curByte == 0x00) {
//                                    result.append("●");
									targetChar = "●";
								} else if (curByte == 0x0A) { // 0x0A 换行符 ▲
//                                    result.append("♂");
									targetChar = "▲";
								} else if (curByte == 0x0D) { // 0D-■ 回车
//                                    result.append("∠");
									targetChar = "■";
								} else if (curByte == 0x09) { // 09 - ◆水平制表符 相当于 Tab
									targetChar = "◆";
								} else if (curByte == 0x0B) { // 0x0B -┓ VT (vertical tab) 垂直制表符
									targetChar = "┓";
								} else if (curByte == 0x1F) { // █ 0x1F 单元分隔符
									targetChar = "█";
								} else if (curByte == 0x0C) { // 0x0C ▼ FF (NP form feed, new page) 换页键
									targetChar = "▼";
								} else {
//                                    result.append(".");
									targetChar = ".";
								}

							}

							String byreStr = toHexString(curByte);
							mAsciiSB.append(targetChar);
							if (k < 9) {
								result.append("【 0" + k + "-" + toHexStringNoTen(byteIndex) + "-"
										+ toTenString(byteIndex).trim() + "-" + byreStr + "-" + targetChar + " 】 ");

							} else {
								result.append("【 " + k + "-" + toHexStringNoTen(byteIndex) + "-"
										+ toTenString(byteIndex).trim() + "-" + byreStr + "-" + targetChar + " 】 ");
							}
							byteIndex++;
							result.append(showByte(curByte, mBinarySB));
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

		if (lineIndex != 0) { // if (lineIndex != 16) AOSP 中存在错误 无法打印 最后是 16个字节的情况
			int count = (16 - lineIndex) * 3;
			count++;
			for (int i = 0; i < count; i++) {
				result.append(" ");
			}
			ArrayList<Byte> byteList = new ArrayList<Byte>();
			for (int i = 0; i < lineIndex; i++) {
				byteList.add(line[i]);
				if (line[i] >= ' ' && line[i] <= '~') {
					result.append(new String(line, i, 1));
				} else {
//                    result.append(".");

					if (line[i] == 0x00) {
						result.append("●");
					} else if (line[i] == 0x0A) { // 0x0A 换行符 ▲
						result.append("▲");
					} else if (line[i] == 0x0D) { // 0D-■回车
						result.append("■");
					} else if (line[i] == 0x09) { // 09 - ◆水平制表符 相当于 Tab
						result.append("◆");
					} else if (line[i] == 0x0B) { // 0x0B -┓ VT (vertical tab) 垂直制表符
						result.append("┓");
					} else if (line[i] == 0x1F) { // █ 0x1F 单元分隔符
						result.append("█");
					} else if (line[i] == 0x0C) { // 0x0C ▼ FF (NP form feed, new page) 换页键
						result.append("▼");
					} else {
						result.append(".");
					}

				}

				if (i == lineIndex - 1) { // 最后字符显示一个分割线
					int paddingSize = 16 - lineIndex;
					for (int j = 0; j < paddingSize; j++) {
						result.append(" ");
					}
					result.append("  ║ "); // 这里显示的是 字节信息

					for (int k = 0; k < byteList.size(); k++) {
						byte curByte = byteList.get(k);

						String targetChar = "";
						if (curByte >= ' ' && curByte <= '~') {
							targetChar = new String(line, k, 1);
						} else {
//                            targetChar = ".";

							if (curByte == 0x00) {
								result.append("●");
							} else if (curByte == 0x0A) { // 0x0A 换行符 ▲
								targetChar = "▲";
							} else if (curByte == 0x0D) { // 0D-■ 回车
								targetChar = "■";
							} else if (curByte == 0x09) { // 09 - ◆水平制表符 相当于 Tab
								targetChar = "◆";
							} else if (curByte == 0x0B) { // 0x0B -┓ VT (vertical tab) 垂直制表符
								targetChar = "┓";
							} else if (curByte == 0x1F) { // █ 0x1F 单元分隔符
								targetChar = "█";
							} else if (curByte == 0x0C) { // 0x0C ▼ FF (NP form feed, new page) 换页键
								targetChar = "▼";
							} else {
								targetChar = ".";
							}
						}

						String byreStr = toHexString(curByte);
						mAsciiSB.append(targetChar);
						if (k < 9) {
							result.append("【 0" + k + "-" + toHexStringNoTen(byteIndex) + "-"
									+ toTenString(byteIndex).trim() + "-" + byreStr + "-" + targetChar + " 】 ");
						} else {
							result.append("【 " + k + "-" + toHexStringNoTen(byteIndex) + "-"
									+ toTenString(byteIndex).trim() + "-" + byreStr + "-" + targetChar + " 】 ");
						}
						byteIndex++;
						result.append(showByte(curByte, mBinarySB));
					}
				}
			}
		}

		return result.toString();
	}

	public static String showByte(byte byteData, StringBuilder mBinarySB) {
		StringBuilder sb = new StringBuilder();
		String result = "";

		for (int i = 7; i >= 0; i--) {

			byte tempByte = byteData;
			tempByte = (byte) (tempByte >> i);
			int value = tempByte & 0x01;
			if (value == 1) {
				sb.append("1");
				mBinarySB.append("1");
			} else {
				sb.append("0");
				mBinarySB.append("0");
			}

			if (i == 4) {
				sb.append("║");
			}
		}

		return sb.toString() + " ";

	}

	public static String toHexString(int i) {
		StringBuilder sb = new StringBuilder();
		sb.append(toHexString(toByteArray(i)));
		sb.append(toTenString(i));

		return sb.toString();
	}

	public static String toHexStringNoTen(int i) {
		StringBuilder sb = new StringBuilder();
		sb.append(toHexString(toByteArray(i)));
		return "0x" + sb.toString().trim();
	}

	public static String toHexString(byte b) {
		return toHexString(toByteArray(b));
	}

	public static String toHexString(byte[] array) {
		return toHexString(array, 0, array.length, true);
	}

	public static String toHexString(byte[] array, boolean upperCase) {
		return toHexString(array, 0, array.length, upperCase);
	}

	public static String toHexString(byte[] array, int offset, int length) {
		return toHexString(array, offset, length, true);
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
		if (c >= '0' && c <= '9')
			return (c - '0');
		if (c >= 'A' && c <= 'F')
			return (c - 'A' + 10);
		if (c >= 'a' && c <= 'f')
			return (c - 'a' + 10);

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
		// System.out.println(" 10进制 = "+ str);
		return str;
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

	static HashMap<String, String> mFileTypes;

	// 魔数
	static void initMoShuTypeMap() {
		mFileTypes = new HashMap<String, String>();
		// images
		initMoshuTypeItem("FFD8FF", "jpg");
		initMoshuTypeItem("89504E47", "png");
		initMoshuTypeItem("47494638", "gif");
		initMoshuTypeItem("49492A00", "tif");
		initMoshuTypeItem("424D", "bmp");
		initMoshuTypeItem("424D", "bmp");
		initMoshuTypeItem("424D228C010000000000", "bmp"); // 16色位图(bmp)
		initMoshuTypeItem("424D8240090000000000", "bmp"); // 24色位图(bmp)
		initMoshuTypeItem("424D8E1B030000000000", "bmp"); // 256色位图(bmp)

		//
		initMoshuTypeItem("41433130", "dwg"); // CAD
		initMoshuTypeItem("38425053", "psd");
		initMoshuTypeItem("7B5C727466", "rtf"); // 日记本
		initMoshuTypeItem("7B5C7274", "rtf"); // 日记本

		initMoshuTypeItem("3C3F786D6C", "xml");
		initMoshuTypeItem("3C3F786D", "xml");

		initMoshuTypeItem("68746D6C3E", "html");
		initMoshuTypeItem("68746D6C", "html");

		initMoshuTypeItem("44656C69766572792D646174653A", "eml"); // 邮件
		initMoshuTypeItem("44656C69", "eml"); // 邮件

		initMoshuTypeItem("D0CF11E0", "doc");

		initMoshuTypeItem("CFAD12FEC5FD746F", "dbx"); /** Outlook Express (dbx) */
		initMoshuTypeItem("CFAD12FE", "dbx");

		initMoshuTypeItem("2142444E", "pst"); // /** Outlook (pst)*/
		initMoshuTypeItem("FF575043", "wpb"); /** Word Perfect (wpd) */

		initMoshuTypeItem("252150532D41646F6265", "esp");
		initMoshuTypeItem("25215053", "esp");
		initMoshuTypeItem("252150532D41646F6265", "PS");
		initMoshuTypeItem("25215053", "PS");
		initMoshuTypeItem("255044462D312E", "PDF");
		initMoshuTypeItem("25504446", "PDF");
		initMoshuTypeItem("AC9EBD8F", "qdf");
		initMoshuTypeItem("458600000600", "qbb");
		initMoshuTypeItem("45860000", "qbb");
		initMoshuTypeItem("E3828596", "PWL");
		initMoshuTypeItem("504B0304", "zip");
		initMoshuTypeItem("52617221", "RAR");
		initMoshuTypeItem("57415645", "WAV");
		initMoshuTypeItem("41564920", "AVI");
		initMoshuTypeItem("2E7261FD", "RAM");
		initMoshuTypeItem("2E524D46", "RM");
		initMoshuTypeItem("2E524D46000000120001", "RMVB");
		initMoshuTypeItem("2E524D46", "RMVB");
		initMoshuTypeItem("000001BA", "MPG");
		initMoshuTypeItem("6D6F6F76", "MOV");
		initMoshuTypeItem("3026B2758E66CF11", "ASF");
		initMoshuTypeItem("3026B275", "ASF");
		initMoshuTypeItem("000060EA", "ARJ");
		initMoshuTypeItem("4D546864", "MID");
		initMoshuTypeItem("00000020667479706D70", "MP4");
		initMoshuTypeItem("00000020", "MP4");
		initMoshuTypeItem("49443303000000002176", "MP3");
		initMoshuTypeItem("49443303", "MP3");
		initMoshuTypeItem("464C5601050000000900", "FLV");
		initMoshuTypeItem("464C5601", "FLV");
		initMoshuTypeItem("1F8B0800", "GZ");
		initMoshuTypeItem("48544D4C207B0D0A0942", "CSS");
		initMoshuTypeItem("48544D4C", "CSS");
		initMoshuTypeItem("696B2E71623D696B2E71", "JS");
		initMoshuTypeItem("696B2E71", "JS");
		initMoshuTypeItem("d0cf11e0a1b11ae10000", "VSD");
		initMoshuTypeItem("d0cf11e0", "VSD");
		initMoshuTypeItem("d0cf11e0a1b11ae10000", "WPS");
		initMoshuTypeItem("d0cf11e0", "WPS");
		initMoshuTypeItem("6431303A637265617465", "TORRENT");
		initMoshuTypeItem("6431303A", "TORRENT");
		initMoshuTypeItem("3C2540207061676520", "JSP");
		initMoshuTypeItem("3C254020", "JSP");
		initMoshuTypeItem("7061636B61676520", "JAVA");
		initMoshuTypeItem("7061636B", "JAVA");
		initMoshuTypeItem("CAFEBABE0000002E00", "CLASS");
		initMoshuTypeItem("CAFEBABE", "CLASS");
		initMoshuTypeItem("504B03040A000000", "JAR");
		initMoshuTypeItem("504B0304", "JAR");
		initMoshuTypeItem("4D616E69666573742D56", "MF");
		initMoshuTypeItem("4D616E69", "MF");
		initMoshuTypeItem("4D5A9000030000000400", "EXE");
		initMoshuTypeItem("4D5A9000", "EXE");
		initMoshuTypeItem("7F454C4601010100", "ELF");
		initMoshuTypeItem("7F454C46", "ELF");
		initMoshuTypeItem("2000604060", "WK1");
		initMoshuTypeItem("20006040", "WK1");
		initMoshuTypeItem("00001A0000100400", "WK3");
		initMoshuTypeItem("00001A00", "WK3");
		initMoshuTypeItem("00001A0002100400", "WK4");
		initMoshuTypeItem("576F726450726F", "LWP");
		initMoshuTypeItem("576F7264", "LWP");
		initMoshuTypeItem("53520100", "SLY");

		initMoshuTypeItem("D0CF11E0", "ppt");
		initMoshuTypeItem("D0CF11E0", "xls");// excel2003版本文件
		initMoshuTypeItem("5374616E64617264204A", "mdb");
		initMoshuTypeItem("5374616E", "mdb");
		initMoshuTypeItem("252150532D41646F6265", "ps");
		initMoshuTypeItem("25215053", "ps");
		initMoshuTypeItem("255044462D312E", "pdf");
		initMoshuTypeItem("25504446", "pdf");
		initMoshuTypeItem("504B0304", "pptx");
		initMoshuTypeItem("504B0304", "docx");
		initMoshuTypeItem("504B0304", "xlsx");// excel2007以上版本文件
		initMoshuTypeItem("52617221", "rar");
		initMoshuTypeItem("57415645", "wav");
		initMoshuTypeItem("41564920", "avi");
		initMoshuTypeItem("2E524D46", "rm");
		initMoshuTypeItem("000001BA", "mpg");
		initMoshuTypeItem("000001B3", "mpg");
		initMoshuTypeItem("6D6F6F76", "mov");
		initMoshuTypeItem("3026B2758E66CF11", "asf");
		initMoshuTypeItem("4D546864", "mid");
		initMoshuTypeItem("1F8B08", "gz");

	}

static	void initMoshuTypeItem(String key , String value){
		mFileTypes.put(key.toLowerCase(), value.toLowerCase());
	}
	
	/**
	 * @param filePath 文件路径
	 * @return 文件头信息
	 * @author wlx
	 *         <p>
	 *         方法描述：根据文件路径获取文件头信息
	 */
	static public String getFileType(String filePath) {
		if (mFileTypes == null) {
			initMoShuTypeMap();
		}
//	        System.out.println(getFileHeader(filePath));
//	        System.out.println(mFileTypes.get(getFileHeader(filePath)));
		return mFileTypes.get(getFileHeader(filePath));
	}

	/**
	 * @param filePath 文件路径
	 * @return 文件头信息
	 * @author wlx
	 *         <p>
	 *         方法描述：根据文件路径获取文件头信息
	 */
	static public String getFileHeader(String filePath) {
		FileInputStream is = null;
		String value = null;
		try {
			is = new FileInputStream(filePath);
			byte[] b = new byte[4];
			/*
			 * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length 个字节的数据读入一个
			 * byte 数组中。 int read(byte[] b, int off, int len) 从此输入流中将最多 len 个字节的数据读入一个 byte
			 * 数组中。
			 */
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
			if(value != null) {
				value = value.toLowerCase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("文件:" + filePath + "    byte[4]_value_moshu = " + value);
		return value;
	}

	/**
	 * @param src 要读取文件头信息的文件的byte数组
	 * @return 文件头信息
	 * @author wlx
	 *         <p>
	 *         方法描述：将要读取文件头信息的文件的byte数组转换成string类型表示
	 */
	static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (byte aSrc : src) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(aSrc & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
//	        System.out.println(builder.toString());
		return builder.toString();
	}

	public static boolean isNumeric(String str) {
		if (str.contains("#")) {
			return false;
		}
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	static void SortString(ArrayList<String> strList) {
	    Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
	    strList.sort((o1, o2) -> {
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
	// List<A_B_C> 需要把这个 创建了三个 JavaBean
// A , B  ,C  这三个 对象的 execute()方法 会执行  parseMap();  zzj
//         而不会执行  parseMap()  的  generationBean.writeList(clz);  去 生成
//       WriteBean
//
//         Line 231: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<String>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 339: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<Integer>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 341: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<Object>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 671: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<D>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 684: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<A_B_C>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 741: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<E>  不会执行 writeList(clz) 方法了  操蛋！
//      writeList(clz)  // 把 clz 写入一个 txt 文件  文件的名称为  List<Object>   转为  List_Object_     List_A_B_C_
//     File file = new File("src/" + packName.replace(".", "/"));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file, list.replaceAll("<|>", "_") + ".txt")));
//    bw.write(list);       写入 .txt  有什么作用?   这个从代码根本看不出来

//  X2  MyBeanGenerator  className  =RootBean entry.getValue().toString() =
//       X3  MyBeanGenerator  MyBeanGenerator  className  =RootBean entry.getValue().toString() = capitalUpperCase(entry.getKey()) =Data
// 它的 key=data  , value 为空  map.put
//  generationBean.writeBean(name, map 【 Map<String, Object> map = jsonParse.toMap(json) 】);  map的 数据来源于 Json

//  [{类A}{类B}{类C}]    以什么方式     value =  List<A_B_C>   List<A___B___C>
// X3  MyBeanGenerator  className  =RootBean entry.getValue().toString() =List<<A___B___C>>  capitalUpperCase(entry.getKey()) =Data
//  如果  当前的 value  包含 List 和 ___   那么获取  第一个 ___ 之前的位置    最后一个 ___之后的位置,  截取这个字符串 中间加 List<Object>

//  B -> topic_2048 [weight=6]
//B -> topic [weight=6]    重复！
// make-> 4  refClassName=B    properityName=topic_2048
// make-> 4  refClassName=Extra    properityName=Topic_2048
// make-> 4  refClassName=Extra    properityName=Topic
// make-> 4  refClassName=B    properityName=topic
// new ProperityItem
//  ArrayList<HashMap<String, ArrayList<ProperityItem>>> Rule17_arrMapList  的 Item 排序

// 对于 对象中 为 {  [] }  为 空的情况的处理
	// Rule_17 End 对 Json 格式的文件 或者 以json格式保存的文件 生成bean文件 以及 Graphviz 绘图显示结构

}