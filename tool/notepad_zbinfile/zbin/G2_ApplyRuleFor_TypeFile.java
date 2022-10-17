
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoSize;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.google.common.collect.Maps;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.luciad.imageio.webp.WebPReadParam;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.spire.presentation.FileFormat;
import com.spire.presentation.IAutoShape;
import com.spire.presentation.IEmbedImage;
import com.spire.presentation.ISlide;
import com.spire.presentation.PortionEx;
//import com.sun.tools.sjavac.CopyFile;
import com.spire.presentation.Presentation;
import com.spire.presentation.ShapeType;
import com.spire.presentation.TextFont;
import com.spire.presentation.drawing.FillFormatType;
import com.sun.mail.util.MailSSLSocketFactory;


import net.jimmc.jshortcut.JShellLink;
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
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

import javax.mail.MessagingException;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.sun.mail.util.MailSSLSocketFactory;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.util.ZipSecureFile;
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
import org.json.JSONException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.ss.usermodel.CellType;

import java.security.Key;
import java.security.Security;

// 对于  文件类型_操作Index  执行对应的操作逻辑
public class G2_ApplyRuleFor_TypeFile {

	// 类型_索引 ，对当前类型的文件执行索引执行的操作 html1---对html类型的子文件执行 索引为1 的逻辑操作 String
	// apply(String)
	static ArrayList<String> Rule_Identify_TypeIndexList = new ArrayList<String>();

	static String Cur_Batch_End = ".bat";
	static String G2_Bat_Name = "zrule_apply_G2";
	static String Cur_Bat_Name = "zrule_apply_G2";
	static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
			+ File.separator + "zbin";
	static String G2_File_Path = zbinPath + File.separator + "G2";
	static String Win_Lin_Mac_ZbinPath = "";

	static File G2_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator
			+ "Desktop" + File.separator + "zbin" + File.separator + "G2.properties");
	static InputStream G2_Properties_InputStream;
	static OutputStream G2_Properties_OutputStream;
	static Properties G2_Properties = new Properties();
	static Map<String, String> propKey2ValueList = new HashMap<String, String>();

	static int BYTE_CONTENT_LENGTH_Rule7 = 1024 * 10 * 10; // 读取文件Head字节数常数
	static String strDefaultKey_Rule7 = "zukgit12"; // 8-length

	static String strZ7DefaultKey_PSW_Rule19 = "752025"; // 8-length
	public static byte[] TEMP_Rule7 = new byte[BYTE_CONTENT_LENGTH_Rule7];

	static G2_ApplyRuleFor_TypeFile mG2_Object;

	static {
		try {
			if (!G2_Properties_File.exists()) {
				G2_Properties_File.createNewFile();
			}
			G2_Properties_InputStream = new BufferedInputStream(
					new FileInputStream(G2_Properties_File.getAbsolutePath()));
			G2_Properties.load(G2_Properties_InputStream);
			Iterator<String> it = G2_Properties.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				// System.out.println("key:" + key + " value: " +
				// G2_Properties.getProperty(key));
				propKey2ValueList.put(key, G2_Properties.getProperty(key));
			}
			G2_Properties_InputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void setProperity() {
		try {
			G2_Properties_OutputStream = new BufferedOutputStream(
					new FileOutputStream(G2_Properties_File.getAbsolutePath()));
			G2_Properties.store(G2_Properties_OutputStream, "");
			G2_Properties_OutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	enum OS_TYPE {
		Windows, Linux, MacOS
	}

	// JDK 的路径
	static String JDK_BIN_PATH = "";

	static File G2_Temp_Text_File = new File(
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

	void InitRule() {

		realTypeRuleList.add(new HTML_Rule_1());
		realTypeRuleList.add(new File_Name_Rule_2());
		realTypeRuleList.add(new Image2Jpeg_Rule_3());
		realTypeRuleList.add(new Image2Png_Rule_4());
		realTypeRuleList.add(new AVI_Rule_5());
		realTypeRuleList.add(new SubDirRename_Rule_6());
		realTypeRuleList.add(new Encropty_Rule_7());
		realTypeRuleList.add(new ClearChineseType_8());
		realTypeRuleList.add(new FileType_Rule_9());
		realTypeRuleList.add(new DirOperation_Rule_10());
		realTypeRuleList.add(new AllDirSubFile_Order_Rule_11());

		realTypeRuleList.add(new CalCulMediaHtml_Rule_12());
		realTypeRuleList.add(new CalMP4_DIR_HTML_Rule_13());
		realTypeRuleList.add(new CreateIconFile_KuaiJieFangShi_Rule_14());
		realTypeRuleList.add(new Webp_To_Jpg_Gif_Rule_15());

		realTypeRuleList.add(new File_TimeName_Rule_16());
		realTypeRuleList.add(new Make_ZRuleDir_Rule_17());
		realTypeRuleList.add(new MD_ReName_Rule_18());
		realTypeRuleList.add(new ExpressTo7z_PassWord_Rule_19());
		realTypeRuleList.add(new Land_Port_Classify_Rule_20());
		realTypeRuleList.add(new Rename_Img_WithSize_Rule_21());
		realTypeRuleList.add(new ReSize_Img_Rule_22());

		// 23
		realTypeRuleList.add(new CheckFileRealFormat_Rule_23());
		realTypeRuleList.add(new add_Middle_Dir_Rule_24()); // 在当前的目录 与 子目录 之间 新增 一层文件夹 , 文件夹名称任意 用户输入

		// 从上往下打印 年月 标示 接受来自 输入的参数 打开 临时文件
		// ## 2020
		// ### 2020.01
		// ### 2020.02
		// zrule_apply_G2.bat #_25 1992_2021 这样
		realTypeRuleList.add(new Time_Head_Rule_25());
		realTypeRuleList.add(new Rename_By_Dir_Rule_26());
		realTypeRuleList.add(new Rercovery_Type_By_DirName_Rule_27());
		// 把当前的 图片 文件 jpg png 等 转为 一个 PPTX 文件 方便 分享 查看
		realTypeRuleList.add(new makeJpg2PPTX_Rule_28());

		// 把 当前 目录的 多个 pptx 合并成 一个 pptx 文件
		realTypeRuleList.add(new mergeSomePPTXAsOne_Rule_29());

		// 对文件的名称进行修改
		realTypeRuleList.add(new FileRenameOperation_Rule_30());

		// 创建文件 创建模板文件
		realTypeRuleList.add(new MakeFile_E5_Rule_31());

		// 把当前目录的 jpg png 文件转为 一个 pdf文件
		realTypeRuleList.add(new MakeJpg2PDF_Rule_32());

		// 把当前目录mp3 文件进行 属性的更改
		realTypeRuleList.add(new MP3_Prop_Fixed_Rule_33());

		// 把当前的 MP3文件转为 JSON 格式数据 方便 布局 树形 结构
		realTypeRuleList.add(new MP3_Revert2JSOn_Rule_34());
		realTypeRuleList.add(new Append_Pdf_Rule_35()); // 把 pdf 文件 追加 合并为 一个文件
		realTypeRuleList.add(new Seperate_Pdf_Rule_36()); // 把 pdf中指定的页数 分隔出来作为一个新的pdf文件

		realTypeRuleList.add(new Zapp_Zmain_dir_Create_Rule_37()); // 在本地目录创建 /sdcard/zapp 和 /sdcard/zmain 相关的 dir目录

		// 把 当前的 xlsx xls 文件 转为 对应的 json 文件
		realTypeRuleList.add(new Revert_xlsx2json_Rule_38());

// 监听 当前 微信的 接收文件 , 判断是否 有新的txt文件  如果有 读取它 , 执行 执行 相应的 命令  如果是 http路径 那么 下载它
		realTypeRuleList.add(new Monitor_WeChatFile_ForWindows_Rule_39());

		// 对网站进行抓取资源到本地
		realTypeRuleList.add(new Monitor_Browser_ForWindows_Rule_40());

// 	// 读取当前文件夹下的jpg文件(仅仅时当前目录) 然后 读取当前的jpg的 artist  Desc  Make  Mode  Copyright UserComment 信息 来生成 .md文件

		realTypeRuleList.add(new Read_Jpg_Exif_Info_Create_MDContent_Rule_41());

		// 传递一个地址 然后返回这个地址的所有的网页的代码 打印在 Temp.txt 中 , 循环到底 持续五分钟 并执行一直写入txt的操作
		// 必须以 http 开头
		realTypeRuleList.add(new GetHttpCode_Rule_42());

// 传递一个 txt文本文件 以及一个 splitstr_XXXX 到程序中,将会去把当前所有的字符串进行split分隔，并输入split分隔后的第一个空格前的字符串

		realTypeRuleList.add(new SqlitTxt_Return_FirstBlankStr_Rule_43());

		realTypeRuleList.add(new Land_Port_Mp4Rename_Rule_44());

		realTypeRuleList.add(new Jpg_Stock_Port_To_MD_Rule_45());

		// 在 jpg 图片 本身 标记 一些 这个图片本身的一些属性
		realTypeRuleList.add(new ShowJpgTagContent_To_Image_Rule_46());

		// 给定一个 西瓜视频 主页 下载 该页面内的所有视频文件
		realTypeRuleList.add(new Download_XiGua_HomeVideo_Rule_47());

		// voice_fafaefafea 读取 voice 的 内容
		realTypeRuleList.add(new Read_Speak_Word_Rule_48());

		// 给定一个 类型的 模板 起始页(默认1) 最终页(默认100)
		// 是否代理(默认false) itemtag(页面内的标记用于过滤) imagetag(详细内容也的照片的标示)
		// CategoryModel_HttpPage_Download_Rule_49
		realTypeRuleList.add(new CategoryModel_HttpPage_Download_Rule_49());

		// 把 当前的 输入的 jpg 或者 当前目录下的 jpg文件 动态计算成 320 宽度的 字符串 并 在打开的文件中打印
		realTypeRuleList.add(new JPG_To_TextChar_Rule_50());

		// 把 当前的 原目录中的所有文件 复制到 对应目录的文件， 如果文件大小一直 那么就跳过复制的过程
		// tip: git action 操作过程中 文件大小不变 但是 它的MD5值 变化了 导致每次都 更新相同的文件
		realTypeRuleList.add(new SrcDir_Copy2_DstDir_WithFileLength_Rule_51());

		// 去除在 一个文件夹中 多余的相同的 MD5 文件 只保留 一个MD5 去重操作
		realTypeRuleList.add(new Delete_SameMD5File_OnlyExistOneInDir_Rule_52());
		// initrule

		// 在 一个 android-studio apk 目录执行的 用于 克隆一个工程到同目录 但工程名称可选
		realTypeRuleList.add(new Copy_APK_Product_ChangeProductName_Rule_53());

		realTypeRuleList.add(new Send_Email_TO_Dst_Rule_54());

	}

	class Send_Email_TO_Dst_Rule_54 extends Basic_Rule {

		ArrayList<String> message_tip_list;

		// 0 只发送文字内容--本机信息等
		// 1--搭配 query_dir 发送本地对应目录的所有文件的字符串
		// 2--发送屏幕截图
		// 3--发送指定文件到附件 // 可以一起实现啊 0123 , 111 那么就只执行一个1 type_0123
		String sendType = "0";
		String quChong_Type = "0";

		ArrayList<File> queryDirList; // 用户输入的多个需要查询所有文件的文件夹的目录
		ArrayList<File> attatchFileList; // 可能包含 文件夹 以及 文件的路径 upfile_
		ArrayList<WifiItem> wifiItemList = new ArrayList<WifiItem>(); // Wifi 相关的信息

		String dstemail = "";
		String content_text = ""; // 邮件的内容

		String subject_text = ""; // 主题

		// 是否重复 一直发
		boolean isLoop = false;

		int repeatcount = 1; // 默认的发送次数

		int interval = 1; // 默认发送的间隔 秒为单位 默认1 秒

		String mCurrentUserName = "null";

		Send_Email_TO_Dst_Rule_54() {
			super("#", 54, 4); //
			attatchFileList = new ArrayList<File>();
			queryDirList = new ArrayList<File>();
			message_tip_list = new ArrayList<String>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// mdname_true // kaoyan_true gaokao_true

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem_lower_trim = inputParamList.get(i);
//				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if (paramItem_lower_trim.startsWith("dst_")) {
					dstemail = paramItem_lower_trim.replace("dst_", "");
				}

				if (paramItem_lower_trim.startsWith("type_")) { // type_012341
					String mSendtype = paramItem_lower_trim.replace("type_", ""); // 0 只发送文字内容 1--发送屏幕截图

					sendType = mSendtype;

//					if(isNumeric(mSendtype)) {  // .contain("0") .contain("1")   .contain("2")    .contain("3")  来判断是否有 
//						sendType = mSendtype;
//					}else {
//						System.out.println("当前发送的 type_ 后缀 不是数字: 请检查! " + paramItem_lower_trim);
//					}

				}

				if (paramItem_lower_trim.startsWith("content_")) {
					content_text = paramItem_lower_trim.replace("content_", ""); // 0 只发送文字内容 1--发送屏幕截图
				}

				if (paramItem_lower_trim.startsWith("subject_")) {
					subject_text = paramItem_lower_trim.replace("subject_", ""); // 0 只发送文字内容 1--发送屏幕截图
				}

				if (paramItem_lower_trim.startsWith("loop_true")) {
					isLoop = true; // 0 只发送文字内容 1--发送屏幕截图
				}

				if (paramItem_lower_trim.startsWith("repeatcount_")) {
					String count_str = paramItem_lower_trim.replace("repeatcount_", "");
					if (isNumeric(count_str)) {
						repeatcount = Integer.parseInt(count_str);
					}

				}

				if (paramItem_lower_trim.startsWith("interval_")) {
					String interval_str = paramItem_lower_trim.replace("interval_", "");
					if (isNumeric(interval_str)) {
						interval = Integer.parseInt(interval_str);
					}

				}

				if (paramItem_lower_trim.startsWith("query_")) {
					String query_dir_path = paramItem_lower_trim.replace("query_", "");
					File queryFile = new File(query_dir_path);
					if (queryFile.exists() && queryFile.isDirectory()) {
						queryDirList.add(queryFile);
					} else {
						System.out.println("paramItem_lower_trim 对应的 queryFile 不存在 ! -->" + paramItem_lower_trim);

						if (!queryFile.exists()) {
							System.out.println("paramItem_lower_trim 对应的 queryFile 不存在 ! -->" + paramItem_lower_trim);
							message_tip_list.add("参数[" + paramItem_lower_trim + "]" + "对应的 queryFile 不存在 ! -->"
									+ paramItem_lower_trim);
						} else if (queryFile.isFile()) {
							System.out.println("paramItem_lower_trim 对应的 upload_file  是文件夹 ! -->" + paramItem_lower_trim
									+ " 无法上传 请检查");
							message_tip_list.add("参数[" + paramItem_lower_trim + "]" + "对应的 是文件  无法查询所有子文件 ! -->"
									+ paramItem_lower_trim);

						}

					}
				}

				if (paramItem_lower_trim.startsWith("upfile_")) {
					String up_file_path = paramItem_lower_trim.replace("upfile_", "");
					File upFile = new File(up_file_path);
					if (upFile.exists() && upFile.isFile()) {
						attatchFileList.add(upFile);
					} else {
						if (!upFile.exists()) {
							System.out.println("paramItem_lower_trim 对应的 upload_file 不存在 ! -->" + paramItem_lower_trim);
							message_tip_list.add("参数[" + paramItem_lower_trim + "]" + "对应的 upload_file 不存在 ! -->"
									+ paramItem_lower_trim);
						} else if (upFile.isDirectory()) {
							System.out.println("paramItem_lower_trim 对应的 upload_file  是文件夹 ! -->" + paramItem_lower_trim
									+ " 无法上传 请检查");
							message_tip_list.add("参数[" + paramItem_lower_trim + "]" + "对应的 是文件夹  无法添加到附件 ! -->"
									+ paramItem_lower_trim);

						}

					}

				}

			}
			System.out.println("═══════════════════输入参数展示 Begin ══════════════════");

			quChong_Type = calcul_QuChong_Type(sendType);
			if (quChong_Type == null || "".equals(quChong_Type)) {
				quChong_Type = "0"; // 默认 执行0 的操作
			}

			ArrayList<String> infoList = getParamsInfoList();
			for (int i = 0; i < infoList.size(); i++) {
				System.out.println(infoList.get(i));
			}

			System.out.println("═══════════════════输入参数展示 End ══════════════════");

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		// 0 只发送文字内容--本机信息等
		// 1--搭配 query_dir 发送本地对应目录的所有文件的字符串
		// 2--发送屏幕截图
		// 3--发送指定文件到附件 // 可以一起实现啊 0123 , 111 那么就只执行一个1 type_0123
		@Override
		String simpleDesc() {

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  dst_zukgit@foxmail.com  type_0  subject_Cmder测试  content_HelloWorld         //  给dst目的邮箱发送邮件 type=0 【0=打印简单信息 1=查询目录(query_)  2=发送截屏 3=发送附件(upload_)】   \n"
					+ Cur_Bat_Name + "  #_" + rule_index
					+ " type_1  query_D/1A/221013    query_D/1A/221012     ###  把对应文件夹下的文件列表发送邮件  dst目的邮箱默认 foxmail  \n"
					+ Cur_Bat_Name + "  #_" + rule_index + " type_2      ###   截取当前电脑屏幕发送到邮箱   \n" + Cur_Bat_Name
					+ "  #_" + rule_index
					+ " type_3  upfile_D/1A/221013/G2.bat  upfile_D/1A/123.txt     ###  把对应多个文件当作有邮箱附件发送  type=3   \n"
					+ Cur_Bat_Name + "  #_" + rule_index + " type_2   loop_true   ###   不断循环发送当前电脑屏幕截屏发送到邮箱   \n"
					+ Cur_Bat_Name + "  #_" + rule_index
					+ " type_2   repeatcount_10 interval_30  ###   发送10个电脑屏幕截屏间隔为30秒发送到邮箱   \n" + Cur_Bat_Name + " #_"
					+ rule_index
					+ "  dst_zukgit@foxmail.com  type_0123  subject_Cmder测试  content_HelloWorld         //   操作 0 1 2 3 一起执行   \n"

					+ "";
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			char[] send_type_arr = quChong_Type.toCharArray();

			ArrayList<String> extraInfoList = new ArrayList<String>();
			HashMap<File, String> imageFile_Desc_Map = new HashMap<File, String>();
			ArrayList<File> mParamAttatchFileList = new ArrayList<File>();
			ArrayList<String> queryDirInfoList = new ArrayList<String>(); // 查询 列表的数据

			if (isLoop) {
				repeatcount = 1000000; // 重复 10000万次 已达到 无限 循环的目的
			}

			for (int i = 0; i < repeatcount; i++) {

				extraInfoList.clear();
				imageFile_Desc_Map.clear();
				mParamAttatchFileList.clear(); // 要 上传 的 文件 本来 就不会重复

				// 0 只发送文字内容--本机信息等 时间戳 等简单的数据
				// 1--搭配 query_dir 发送本地对应目录的所有文件的字符串
				// 2--发送屏幕截图
				// 3--发送指定文件到附件 // 可以一起实现啊 0123 , 111 那么就只执行一个1 type_0123
				for (int j = 0; j < send_type_arr.length; j++) {
					String oneCharType = send_type_arr[j] + "";

					switch (oneCharType) {
					case "0":
						ArrayList<String> simple_info_List = getSimpleInfoList();
						if (simple_info_List != null) {
							extraInfoList.addAll(simple_info_List);
						}

						break;

					case "1":

						if (queryDirInfoList.size() == 0) {

							queryDirInfoList
									.add("═════════" + "QueryDir[" + queryDirList.size() + "]" + " Begin ═════════");

							for (int k = 0; k < queryDirList.size(); k++) {
								File queryDir = queryDirList.get(k);
								queryDirInfoList.add(
										"═════════" + "QueryDir[" + k + "][" + queryDirList.size() + "]" + "═════════");
								if (queryDir.exists() && queryDir.isDirectory()) {
									ArrayList<File> allSubFile = getAllSubFile(queryDir, "*");
									addQueryFileInfo(queryDir, queryDirInfoList, allSubFile, k, queryDirList.size());
									System.out.println("AA_ queryDir=" + queryDir + "  queryDir.exists()="
											+ queryDir.exists() + "    queryDir.isDirectory()=" + queryDir.isDirectory()
											+ "  allSubFile.size()=" + allSubFile.size());

								} else {

									System.out.println(
											"BB_ queryDir=" + queryDir + "  queryDir.exists()=" + queryDir.exists()
													+ "    queryDir.isDirectory()=" + queryDir.isDirectory());
								}

							}
							queryDirInfoList
									.add("═════════" + "QueryDir[" + queryDirList.size() + "]" + " End ═════════");
						}

						if (queryDirInfoList.size() != 0) {

							System.out.println("queryDirInfoList.size() = " + queryDirInfoList.size());

							for (int k = 0; k < queryDirInfoList.size(); k++) {
								System.out.println("query_file = " + queryDirInfoList.get(k));

							}
							extraInfoList.addAll(queryDirInfoList);

						}

						break;

					case "2":

						File screenShotFile = getPCScreenFile();
						if (screenShotFile.exists()) {
							imageFile_Desc_Map.put(screenShotFile,
									getTimeStampyyyyMMdd_HHmmss() + "_" + mCurrentUserName + "屏幕截图");
						} else {
							System.out.println("当前屏幕截图失败 screenShotFile=" + screenShotFile);
						}

						break;

					case "3":
						mParamAttatchFileList.addAll(attatchFileList);

						break;

					default:
						System.out.println("oneCharType = " + oneCharType + "  没有执行规则!");
					}

				}

				try {
					System.out
							.println("═════════════[" + i + "][" + repeatcount + "] 次发送邮件  interval=" + interval + "秒");

					String mSubjectStr = subject_text;
					if (mSubjectStr == null || "".equals(mSubjectStr)) {
						mSubjectStr = mCurrentUserName + "_repeat[" + i + "][" + repeatcount + "]";
					} else {
						mSubjectStr = subject_text + "_" + mCurrentUserName + "_repeat[" + i + "][" + repeatcount + "]";
					}
					sendemail(false ,mCurrentUserName, dstemail, mSubjectStr, content_text, getParamsInfoList(),
							extraInfoList, imageFile_Desc_Map, mParamAttatchFileList);
					Thread.sleep(interval * 1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("e = "+e.getMessage());
				}

			}
			System.out.println(" 次发送邮件 程序 执行 结束 ! ");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void addQueryFileInfo(File queryFile, ArrayList<String> fileInfoList, ArrayList<File> allSubFile, int index,
				int count) {
			if (allSubFile == null || allSubFile.size() == 0) {

				return;
			}

			fileInfoList.add("═════════QueryDir[" + index + "][" + count + "]_" + queryFile.getAbsolutePath());

			for (int i = 0; i < allSubFile.size(); i++) {
				File fileItem = allSubFile.get(i);
				fileInfoList.add("file[" + i + "][" + allSubFile.size() + "]__" + fileItem.getAbsolutePath());
			}
			fileInfoList.add("");
		}

		ArrayList<String> getSimpleInfoList() {
			ArrayList<String> mSimpleInfoList = new ArrayList<String>();

			ArrayList<String> wifiInfoList = getWifiInfoList();
			if (wifiInfoList != null && wifiInfoList.size() > 0) {
				mSimpleInfoList.add("________" + "Wifi_Info" + "________");
				mSimpleInfoList.addAll(wifiInfoList);
				mSimpleInfoList.add("");
				mSimpleInfoList.add("");
			}

			String osName = System.getProperties().getProperty("os.name").toLowerCase();

			Runtime r = Runtime.getRuntime();
			Properties props = System.getProperties();

			Map<String, String> map = System.getenv(); // System.getenv().get("USERNAME")

			String userName = map.get("USERNAME");// 获取用户名
			mCurrentUserName = userName;
			String computerName = map.get("COMPUTERNAME");// 获取计算机名
			String userDomain = map.get("USERDOMAIN");// 获取计算机域名

			ArrayList<String> SystemInfoList = new ArrayList<String>();

			SystemInfoList.add("操作系统OS:" + osName);
			SystemInfoList.add("用户名:" + "【System.getenv().get(\"USERNAME\")" + "】" + userName);
			SystemInfoList.add("计算机名:【System.getenv().get(\"COMPUTERNAME\")】" + computerName);
			SystemInfoList.add("计算机域名:【System.getenv().get(\"USERDOMAIN\")\"】" + userDomain);
			SystemInfoList.add("JVM可以使用的总内存:" + "【Runtime.getRuntime().totalMemory()】 " + r.totalMemory()); // 【Runtime.getRuntime().totalMemory()】
			SystemInfoList.add("JVM可以使用的剩余内存:" + "【Runtime.getRuntime().freeMemory()】 " + r.freeMemory());
			SystemInfoList
					.add("JVM可以使用的处理器个数:" + "【Runtime.getRuntime().availableProcessors()】 " + r.availableProcessors());
			SystemInfoList.add("Java的运行环境版本:" + "【java.version】" + props.getProperty("java.version"));
			SystemInfoList.add("Java的运行环境供应商:" + "【java.vendor】" + props.getProperty("java.vendor"));
			SystemInfoList.add("Java供应商的URL:" + "【java.vendor.url】" + props.getProperty("java.vendor.url"));
			SystemInfoList.add("Java的安装路径:" + "【java.home】" + props.getProperty("java.home"));
			SystemInfoList.add("Java的虚拟机规范版本:" + "【java.vm.specification.version】"
					+ props.getProperty("java.vm.specification.version"));
			SystemInfoList.add("Java的虚拟机规范供应商:" + "【java.vm.specification.vendor】"
					+ props.getProperty("java.vm.specification.vendor"));
			SystemInfoList.add(
					"Java的虚拟机规范名称:" + "【java.vm.specification.name】" + props.getProperty("java.vm.specification.name"));
			SystemInfoList.add("Java的虚拟机实现版本:" + "【java.vm.version】" + props.getProperty("java.vm.version"));
			SystemInfoList.add("Java的虚拟机实现供应商:" + "【java.vm.vendor】" + props.getProperty("java.vm.vendor"));
			SystemInfoList.add("Java的虚拟机实现名称:" + "【java.vm.name】" + props.getProperty("java.vm.name"));
			SystemInfoList.add("Java运行时环境规范版本:" + "【java.specification.version】"
					+ props.getProperty("java.specification.version"));
			SystemInfoList.add(
					"Java运行时环境规范供应商:" + "【java.specification.vender】" + props.getProperty("java.specification.vender"));
			SystemInfoList
					.add("Java运行时环境规范名称:" + "【java.specification.name】" + props.getProperty("java.specification.name"));
			SystemInfoList.add("Java的类格式版本号:" + "【java.class.version】" + props.getProperty("java.class.version"));
			SystemInfoList.add("Java的类路径:" + "【java.class.path】" + props.getProperty("java.class.path"));
			SystemInfoList.add("加载库时搜索的路径列表:" + "【java.library.path】" + props.getProperty("java.library.path"));
			SystemInfoList.add("默认的临时文件路径:" + "【java.io.tmpdir】" + props.getProperty("java.io.tmpdir"));
			SystemInfoList.add("一个或多个扩展目录的路径:" + "【java.ext.dirs】" + props.getProperty("java.ext.dirs"));
			SystemInfoList.add("操作系统的名称:" + "【os.name】" + props.getProperty("os.name"));
			SystemInfoList.add("操作系统的构架:" + "【os.arch】" + props.getProperty("os.arch"));
			SystemInfoList.add("操作系统的版本:" + "【os.version】" + props.getProperty("os.version"));
			SystemInfoList.add("当前系统文件分隔符:" + "【file.separator】" + props.getProperty("file.separator"));
			SystemInfoList.add("当前系统路径分隔符:" + "【path.separator】" + props.getProperty("path.separator"));
			SystemInfoList.add("行分隔符:" + "【line.separator】" + props.getProperty("line.separator"));
			SystemInfoList.add("用户的账户名称:" + "【user.name】" + props.getProperty("user.name"));
			SystemInfoList.add("用户的主目录:" + "【user.home】" + props.getProperty("user.home"));
			SystemInfoList.add("用户的Desktop目录:" + "【user.desktop】" + props.getProperty("user.desktop"));
			SystemInfoList.add("用户的当前工作目录:" + "【user.dir】" + props.getProperty("user.dir"));

			mSimpleInfoList.add("________" + "System_Prop_Info" + "________");
			for (int i = 0; i < SystemInfoList.size(); i++) {
				mSimpleInfoList.add("system[" + i + "][" + SystemInfoList.size() + "]__" + SystemInfoList.get(i));
			}

			mSimpleInfoList.add("");
			mSimpleInfoList.add("________" + "Java_Prop_Info" + "________");

			Properties system_props = System.getProperties();
			Object[] keyObjs = System.getProperties().stringPropertyNames().toArray();

			for (int i = 0; i < keyObjs.length; i++) {
				String key = keyObjs[i] + "";
				String value = system_props.getProperty(key);
				mSimpleInfoList
						.add("Java_Property[" + i + "][" + keyObjs.length + "]" + "【 " + key + "____" + value + "】");
			}

			mSimpleInfoList.add("________" + "Screen_Info" + "________");
			int width = getZScreenWeight();
			int high = getZScreenHeight();
			mSimpleInfoList.add("屏幕分辨率:" + width + "x" + high);
			mSimpleInfoList.add("屏幕宽:" + width);
			mSimpleInfoList.add("屏幕高:" + high);

			return mSimpleInfoList;

		}

		String readStringFromFile_UTF8(File fileItem) {
			StringBuilder sb = new StringBuilder();
			try {
				BufferedReader curBR = new BufferedReader(
						new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
//		            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
				String lineContent = "";
				while (lineContent != null) {
					lineContent = curBR.readLine();
					if (lineContent == null || lineContent.trim().isEmpty()) {
						continue;
					}
					sb.append(lineContent + "\n");
				}
				curBR.close();
			} catch (Exception e) {
			}
			return sb.toString();
		}

		int getZScreenHeight() {
			// CMD 和 IDE下 宽高一致 1920x1080
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int height = gd.getDisplayMode().getHeight();
			return height;

		}

		int getZScreenWeight() {

			// CMD 和 IDE下 宽高一致 1920x1080

			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			return width;

		}

		ArrayList<String> getParamsInfoList() {

			ArrayList<String> infoList = new ArrayList<String>();
//			infoList.add("dst["+dstemail+"]" +"  "+"sendType["+sendType+"]"+"  quChong_Type["+quChong_Type+"] "+"isLoop["+isLoop+"]" +"  interval["+interval+"]  "+"repeatcount["+repeatcount+"]" +"  "+"subject["+subject_text+"]"+" "+"content_text["+content_text+"]");
			infoList.add("dst[" + dstemail + "]");
			infoList.add("sendType[" + sendType + "]");
			infoList.add("quChong_Type[" + quChong_Type + "]");
			infoList.add("isLoop[" + isLoop + "]");
			infoList.add("interval[" + interval + "]");
			infoList.add("repeatcount[" + repeatcount + "]");
			infoList.add("subject[" + subject_text + "]");
			infoList.add("content_text[" + content_text + "]");

			for (int i = 0; i < queryDirList.size(); i++) {
				File queryFile = queryDirList.get(i);
				infoList.add(
						"_____query_file[" + i + "][" + queryDirList.size() + "]" + "__" + queryFile.getAbsolutePath());
			}

			for (int i = 0; i < attatchFileList.size(); i++) {
				File attatchFile = attatchFileList.get(i);
				infoList.add(
						"_____upfile[" + i + "][" + attatchFileList.size() + "]__" + attatchFile.getAbsolutePath());
			}

			for (int i = 0; i < message_tip_list.size(); i++) {
				String tip_item = message_tip_list.get(i);
				infoList.add("_____tip[" + i + "][" + message_tip_list.size() + "]__" + tip_item);
			}

			return infoList;

		}

		// 11111 转为1 01231 转为 0123 321 转为 123 2314转为 1234
		// 0 只发送文字内容--本机信息等
		// 1--搭配 query_dir 发送本地对应目录的所有文件的字符串
		// 2--发送屏幕截图
		// 3--发送指定文件到附件 // 可以一起实现啊 0123 , 111 那么就只执行一个1 type_0123
		String calcul_QuChong_Type(String inputType) { // 去重 去重复
			String quchong_type_str = "";
			StringBuilder inputType_SB = new StringBuilder();

			if (inputType == null || "".equals(inputType.trim())) {
				return "0";
			}

			if (inputType.contains("0")) {
				inputType_SB.append("0");
			}

			if (inputType.contains("1")) {
				inputType_SB.append("1");
			}

			if (inputType.contains("2")) {
				inputType_SB.append("2");
			}

			if (inputType.contains("3")) {
				inputType_SB.append("3");
			}

			if (inputType.contains("4")) {
				inputType_SB.append("4");
			}

			if (inputType.contains("5")) {
				inputType_SB.append("5");
			}

			if (inputType.contains("6")) {
				inputType_SB.append("6");
			}

			if (inputType.contains("7")) {
				inputType_SB.append("7");
			}

			if (inputType.contains("8")) {
				inputType_SB.append("8");
			}

			if (inputType.contains("9")) {
				inputType_SB.append("9");
			}
			if (inputType.contains("a")) { // 往后继续了
				inputType_SB.append("a");
			}

			return inputType_SB.toString();

		}

	}

	/*
	 * 1.AndroidManifest.xml 改变 package 包的名称 <manifest
	 * xmlns:android="http://schemas.android.com/apk/res/android"
	 * xmlns:tools="http://schemas.android.com/tools"
	 * package="com.and.zvideo_and_dy_clone1"> 【】
	 *
	 * 2. 所有的 import com.and.zvideo_and_dy.*; 都改为 import
	 * com.and.改为zvideo_and_dy_clone1.*;
	 *
	 * 然后再把 对应的 package="com.and.zvideo_and_dy_clone1 的文件夹名称从zvideo_and_dy
	 * 改为zvideo_and_dy_clone1
	 *
	 *
	 *
	 * 3.build.gradle
	 *
	 * defaultConfig { applicationId "com.and.zvideo_and_dy_clone1"【】 minSdkVersion
	 * 21 targetSdkVersion 30 versionCode 1 versionName "1.0"
	 *
	 * testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner" }
	 *
	 * 4.settings.gradle rootProject.name = "zvideo_and_dy_clone1"
	 *
	 *
	 * 5.所有的 Java 文件中的 【2.已实现】
	 *
	 * import com.and.zvideo_and_dy.R; 替换为 import com.and.zvideo_and_dy_clone1.R;
	 *
	 * 6. app 名称需要变更为_1 AndroidManifest.xml <application android:allowBackup="true"
	 * android:icon="@mipmap/launch_icon" android:label="@string/app_name"
	 * android:roundIcon="@mipmap/launch_icon" android:theme="@style/AppTheme"
	 * android:largeHeap="true" android:requestLegacyExternalStorage="true"
	 * android:supportsRtl="true">
	 *
	 * strings.xml <string name="app_name">VLC</string> 改为 <string
	 * name="app_name">VLC_1</string>
	 */
	class Copy_APK_Product_ChangeProductName_Rule_53 extends Basic_Rule {
		File src_apk_Dir; // APK源目录 D:\apk_dir/A_product
		File dst_apk_Dir; // APK目的目录 // D:\apk_dir/A_product_20220815_154034_clone

		File src_AndroidManifest_xml_File;
		File src_build_gradle_File;
		File src_settings_gradle_File;
		File src_strings_xml_File;
		ArrayList<File> src_fixed_FileList; // 需要重新写入数据的文件列表
		HashMap<File, ArrayList<String>> srcFile_fixedContent_Map; // 原文件作为key 修复后的内容作为value的 Map

		File dst_AndroidManifest_xml_File;
		File dst_build_gradle_File;
		File dst_settings_gradle_File;
		File dst_strings_xml_File;

		String old_product_name; // 就得工程名称 com.and.app1
		String new_productappend_name; // 新的追加的工程名称 appendname

		String old_product_endfixname; // 原来的工程名称的最后小数点后的字符串 app1
		File old_product_endfixname_DirFile; // 对应的工程目录
		String new_product_endfixname; // 原来的工程名称的最后小数点后的字符串 app1_4qrqr

//		后缀名称   app1  改为 app1_4qrqr

		String new_product_name; // 新的工程名称 com.and.app1_appendname

		String old_app_show_name; // 旧的 app显示的名字 <string name="app_name">VLC</string>
		String new_app_show_name; // 新的 app 显示的名字
		ArrayList<File> src_all_Java_FileList; // 所有的 原目录 java 文件
		ArrayList<File> dst_all_Java_FileList; // 所有的 目的目录 java 文件

		// 有些 xml 中 有自定义view 制订了
		ArrayList<File> src_all_Layout_XmlFileList; // 所有的 原目录 java 文件
		ArrayList<File> dst_all_Layout_XmlFileList; // 所有的 目的目录 java 文件

		// 需要 进行 复制操作的文件 所有 文件夹 所有文件
		ArrayList<File> src_needCopyFileList = new ArrayList<File>();
		String HHmmss_timestr; // 时分秒时间戳
		String YYMMdd_timestr; // 年月日 时间戳

		// 经过 修改 后的 Androidmanifest.xml 的 所有内容
		ArrayList<String> dst_AndroidManifest_xml_File_StrList;
		// 经过 修改 后的 strings.xml 的 所有内容
		ArrayList<String> dst_strings_xml_File_StrList;

		// 经过 修改 后的 build.gradle 的 所有内容
		ArrayList<String> dst_build_gradle_File_StrList;

		// 经过 修改 后的 settings.gradle 的 所有内容
		ArrayList<String> dst_settings_gradle_File_StrList;

		// appname_xxxxxx // apk显示的名称 默认为 a
		// productappend_xxxx // 工程名称后缀增加 什么 已达到简单克隆目的

		Copy_APK_Product_ChangeProductName_Rule_53() {
			super("#", 53, 5); //

			src_needCopyFileList = new ArrayList<File>();

			dst_AndroidManifest_xml_File_StrList = new ArrayList<String>();
			dst_strings_xml_File_StrList = new ArrayList<String>();
			dst_build_gradle_File_StrList = new ArrayList<String>();
			dst_settings_gradle_File_StrList = new ArrayList<String>();

			src_all_Java_FileList = new ArrayList<File>();
			dst_all_Java_FileList = new ArrayList<File>();

			src_all_Layout_XmlFileList = new ArrayList<File>();
			dst_all_Layout_XmlFileList = new ArrayList<File>();

			src_fixed_FileList = new ArrayList<File>();
			srcFile_fixedContent_Map = new HashMap<File, ArrayList<String>>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// mdname_true // kaoyan_true gaokao_true

			// 判断当前的输入的路径 是否是一个apk工程的根目录
			// 1. 包含 AndroidManifest.xml 包含 gradle文件

			if (!isShellInAndroidAPKDir()) {
				System.out.println("不是APK目录  src_settings_gradle_File=" + src_settings_gradle_File
						+ "  src_build_gradle_File=" + src_build_gradle_File + "  src_AndroidManifest_xml_File="
						+ src_AndroidManifest_xml_File + "  src_strings_xml_File=" + src_strings_xml_File);

				return false;
			}

			System.out.println("APK目录 src_settings_gradle_File=" + src_settings_gradle_File + "  src_build_gradle_File="
					+ src_build_gradle_File + "  src_AndroidManifest_xml_File=" + src_AndroidManifest_xml_File
					+ "  src_strings_xml_File=" + src_strings_xml_File);

			// appname_xxxxxx // apk显示的名称 默认为 z
			// productappend_xxxx // 工程名称后缀增加 什么 已达到简单克隆目的 默认为原有dir+时间戳 dy-->dy_081512
			new_app_show_name = "z";
			HHmmss_timestr = getTimeStamp_HHmmss();
			YYMMdd_timestr = getTimeStamp_YYYYMMdd();
			new_productappend_name = HHmmss_timestr;

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem_lower_trim = inputParamList.get(i);
//				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if (paramItem_lower_trim.startsWith("appname_")) {

					String input_appname_str = (" " + paramItem_lower_trim).replace(" appname_", "");
					input_appname_str = input_appname_str.replace(" ", "");

					new_app_show_name = input_appname_str;

				}

				if (paramItem_lower_trim.startsWith("productappend_")) {

					String input_productname_str = (" " + paramItem_lower_trim).replace(" productappend_", "");
					input_productname_str = input_productname_str.replace(" ", "");

					new_productappend_name = input_productname_str;

				}

			}
			System.out.println(
					"new_app_show_name =" + new_app_show_name + "   new_productappend_name=" + new_productappend_name);

			src_apk_Dir = new File(curDirPath);

			if (!src_apk_Dir.exists()) {
				System.out.println("当前Shell 目录 不存在 请检查  src_apk_Dir=" + src_apk_Dir);
				return false;
			}

			String src_dir_name = src_apk_Dir.getName();
			String dst_dir_name = src_dir_name + "_" + new_productappend_name;
			String dst_dir_path = src_apk_Dir.getAbsolutePath().replace(src_dir_name, dst_dir_name);

			dst_apk_Dir = new File(dst_dir_path);

//					dst_apk_Dir = new File(src_apk_Dir.getAbsolutePath()+File.separator+HHmmss_timestr);

			// 读取旧的工程名称 以及 旧的 app的显示的名称
			// String old_product_name; // 就得工程名称 com.and.app1
			// String old_app_show_name; // 旧的 app显示的名字 <string name="app_name">VLC</string>

			old_product_name = readAndroidManifext_ProductName(src_AndroidManifest_xml_File, new_productappend_name);

			if (old_product_name == null) {
				System.out.println(
						"当前工程文件 src_AndroidManifest_xml_File=" + src_AndroidManifest_xml_File + " 无法读取到工程名称!!  请检查!! ");
				return false;
			}
			if (old_product_name.contains(".")) {
				old_product_endfixname = old_product_name.substring(old_product_name.lastIndexOf(".") + 1);
				new_product_endfixname = old_product_endfixname + "_" + new_productappend_name;
			} else {

				old_product_endfixname = old_product_name;
				new_product_endfixname = old_product_endfixname + "_" + new_productappend_name;
			}

			new_product_name = old_product_name + "_" + new_productappend_name;

			System.out.println(" 当前输入 old_product_name[ " + old_product_name + "]   new_product_name[ "
					+ new_product_name + "]" + "old_product_endfixname[" + old_product_endfixname + "]  "
					+ " new_product_endfixname=" + new_product_endfixname);

			if (src_strings_xml_File.exists()) {
				old_app_show_name = readStringsXml_AppName(src_strings_xml_File, new_app_show_name);

			}
			System.out.println(" 当前输入 old_app_show_name[ " + old_app_show_name + "]   new_app_show_name[ "
					+ new_app_show_name + "]" + "  ");

			// settings.gradle 和 build.gradle 的 applicationId "com.and.zvideo_and_dy" 改为 新的

			initSettings_Gradle(src_settings_gradle_File, old_product_name, new_product_name);
			initBuild_Gradle(src_build_gradle_File, old_product_name, new_product_name);

			System.out.println(" 当前输入 SRC[ " + src_apk_Dir.getAbsolutePath() + "]  Dst[ "
					+ dst_apk_Dir.getAbsolutePath() + "]" + " ] 存在  将执行 克隆 apk代码逻辑");

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		void initSettings_Gradle(File settingsGradleFile, String oldPackageName, String newPackageName) {

			ArrayList<String> settingsStrList = ReadFileContentAsList(settingsGradleFile);

			if (settingsStrList == null) {
				System.out.println("从 settingsGradleFile=" + settingsGradleFile + "读取到的数据为空! ");
				return;
			}
			System.out.println("settingsGradleFile = " + settingsGradleFile + "   oldPackageName=" + oldPackageName
					+ "   newPackageName=" + newPackageName);
			for (int i = 0; i < settingsStrList.size(); i++) {
				String lineStr = settingsStrList.get(i);
				// rootProject.name =
				if (lineStr.contains("rootProject.name")) {
					dst_settings_gradle_File_StrList.add("rootProject.name = \"" + new_product_endfixname + "\"");
					continue;
				}

				String lineStr_Replace_Str = lineStr.replace(oldPackageName, newPackageName);
				dst_settings_gradle_File_StrList.add(lineStr_Replace_Str);
			}

			src_fixed_FileList.add(settingsGradleFile);
			srcFile_fixedContent_Map.put(settingsGradleFile, dst_settings_gradle_File_StrList);

			System.out.println("dst_settings_gradle_File_StrList.size() = " + dst_settings_gradle_File_StrList.size());

		}

		void initBuild_Gradle(File BuildGradleFile, String oldPackageName, String newPackageName) {

			ArrayList<String> BuildStrList = ReadFileContentAsList(BuildGradleFile);

			if (BuildStrList == null) {
				System.out.println("从 BuildGradleFile=" + BuildGradleFile + "读取到的数据为空! ");
				return;
			}

			for (int i = 0; i < BuildStrList.size(); i++) {
				String lineStr = BuildStrList.get(i);
				String lineStr_Replace_Str = lineStr.replace(oldPackageName, newPackageName);
				dst_build_gradle_File_StrList.add(lineStr_Replace_Str);
			}

			src_fixed_FileList.add(BuildGradleFile);
			srcFile_fixedContent_Map.put(BuildGradleFile, dst_build_gradle_File_StrList);

			System.out.println("dst_build_gradle_File_StrList.size() = " + dst_build_gradle_File_StrList.size());

		}

		String readStringsXml_AppName(File stringsXmlFile, String newAppName) {

			String local_appName = null;

			if (stringsXmlFile == null || !stringsXmlFile.exists()) {
				return local_appName;
			}

			ArrayList<String> rawStringXml_StrList = ReadFileContentAsList(stringsXmlFile);

			if (rawStringXml_StrList == null) {
				return local_appName;
			}

			for (int i = 0; i < rawStringXml_StrList.size(); i++) {
				String oneLineStr = rawStringXml_StrList.get(i);
				// <string name="app_name">VLC</string>
				if (oneLineStr.contains("name=\"app_name\"")) {
					String appNameBeginStr = oneLineStr.substring(oneLineStr.indexOf("name=\"app_name\"")).trim();

					local_appName = appNameBeginStr.replace("name=\"app_name\"", "").trim();
					local_appName = local_appName.replace(" ", "");
					local_appName = local_appName.replace("</string>", "");
					local_appName = local_appName.replace("<string", "");
					local_appName = local_appName.replace("\"", "");
					local_appName = local_appName.replace(">", "");
					local_appName = local_appName.replace("<", "");
					String fixed_product_linestr = "<string name=\"app_name\">" + newAppName + "</string>";
					dst_strings_xml_File_StrList.add(fixed_product_linestr);
					continue;
				}
				dst_strings_xml_File_StrList.add(oneLineStr);

			}

			src_fixed_FileList.add(stringsXmlFile);
			srcFile_fixedContent_Map.put(stringsXmlFile, dst_strings_xml_File_StrList);

			return local_appName;

		}

		String readAndroidManifext_ProductName(File manifestFile, String productappendStr) {
			String local_prductName = null;

			if (manifestFile == null || !manifestFile.exists()) {
				return local_prductName;
			}

			ArrayList<String> rawAndroidManifest_StrList = ReadFileContentAsList(manifestFile);

			if (rawAndroidManifest_StrList == null) {
				return local_prductName;
			}

			for (int i = 0; i < rawAndroidManifest_StrList.size(); i++) {
				String oneLineStr = rawAndroidManifest_StrList.get(i);

				if (oneLineStr.contains("package=")) {
					String packageBeginStr = oneLineStr.substring(oneLineStr.indexOf("package=")).trim();

					local_prductName = packageBeginStr.replace("package=", "").trim();
					local_prductName = local_prductName.replace(" ", "");
					local_prductName = local_prductName.replace("\"", "");
					local_prductName = local_prductName.replace(">", "");
					local_prductName = local_prductName.replace("<", "");
					local_prductName = local_prductName.replace("\"", "");
					String new_product_Name = local_prductName + "_" + productappendStr;
					String fixed_product_linestr = oneLineStr.replace(local_prductName, new_product_Name);
					System.out.println("fixed_product_linestr = " + fixed_product_linestr + "  new_product_Name="
							+ new_product_Name);
					dst_AndroidManifest_xml_File_StrList.add(fixed_product_linestr);
					continue;
				}
				dst_AndroidManifest_xml_File_StrList.add(oneLineStr);

			}

			src_fixed_FileList.add(manifestFile);
			srcFile_fixedContent_Map.put(manifestFile, dst_AndroidManifest_xml_File_StrList);

			return local_prductName;
		}
		// 1. 根目录下有 settings.gradle
		// 2. 根目录下有 build.gradle
		// 3. 根目录\app\src\main\AndroidManifest.xml 文件存在

		boolean isShellInAndroidAPKDir() {
			boolean isApkDirFlag = false;
			File shellDir = new File(curDirPath);
			if (!shellDir.exists()) {
				System.out.println("当前 输入的路径错误: 不存在:  curDirPath = " + curDirPath);
				return isApkDirFlag;
			}

			src_settings_gradle_File = new File(shellDir.getAbsolutePath() + File.separator + "settings.gradle");
			if (!src_settings_gradle_File.exists()) {
				System.out.println("当前 app工程目录下不包含 settings.gradle 文件 请检查!  curDirPath=" + curDirPath);
				return isApkDirFlag;
			}

			src_build_gradle_File = new File(
					shellDir.getAbsolutePath() + File.separator + "app" + File.separator + "build.gradle");
			if (!src_build_gradle_File.exists()) {
				System.out.println("当前 app工程目录下不包含 apk目录/app/settings.gradle 文件 请检查!  curDirPath=" + curDirPath);
				return isApkDirFlag;
			}

			src_AndroidManifest_xml_File = new File(shellDir.getAbsolutePath() + File.separator + "app" + File.separator
					+ "src" + File.separator + "main" + File.separator + "AndroidManifest.xml");
			if (!src_AndroidManifest_xml_File.exists()) {
				System.out.println(
						"当前 app工程目录下不包含 apk目录/app/src/main/AndroidManifest.xml 文件 请检查!  curDirPath=" + curDirPath);
				return isApkDirFlag;
			}

			src_strings_xml_File = new File(shellDir.getAbsolutePath() + File.separator + "app" + File.separator + "src"
					+ File.separator + "main" + File.separator + "res" + File.separator + "values" + File.separator
					+ "strings.xml");

			if (!src_strings_xml_File.exists()) {
				System.out.println("当前 apk目录/app/src/main/res/values/strings.xml  文件不存在   注意:显示的APK名称可能无法修改!");
			}

			return true;

		}

		@Override
		String simpleDesc() {

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  appname_xxxxxx  productappend_xxxxx    // 如果 当前 shell 目录是app代码根目录 那么在clone一个apk到父文件夹 appname指定显示的apk名称 productappend用于在原有工程名添加后缀使得app相互独立  \n"
					+ Cur_Bat_Name + "  #_" + rule_index
					+ " appname_z      ### 如果 当前 shell 目录是app代码根目录 那么在clone一个apk到父文件夹 appname指定显示的apk名称为z  productappend用于在原有工程名添加后缀使得app相互独立   \n"
					+ ""
//			zrule_apply_G2.bat  #_53  appname_z  productappend_xxx
			;
		}

		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
				ArrayList<File> allSubRealFileList) {

			// 创建 clone 的 目录文件
			try {
				dst_apk_Dir.mkdirs();
			} catch (Exception e) { //

				System.out.println("创建 dst_apk_Dir=" + dst_apk_Dir + " 失败!");
			}

			if (!dst_apk_Dir.exists()) {
				System.out.println("当前 Clone 目录 不存在 请检查  dst_apk_Dir=" + dst_apk_Dir);
				return null;
			}
			dst_apk_Dir.mkdirs();
			if (!dst_apk_Dir.exists()) {

				System.out.println("当前 Clone 目录 不存在 请检查  dst_apk_Dir=" + dst_apk_Dir);
				return null;
			}

			// old_product_endfixname_DirFile
			int matchFileCount = 0;
			for (int i = 0; i < allSubDirFileList.size(); i++) {
				File dirFile = allSubDirFileList.get(i);

				String dirName = dirFile.getName();
				String dirFile_AbsPath = dirFile.getAbsolutePath();

				if (dirName.equals(old_product_endfixname) && !dirFile_AbsPath.contains("ap_generated_sources")
						&& !dirFile_AbsPath.contains("out") && !dirFile_AbsPath.contains("generated")
						&& !dirFile_AbsPath.contains("androidTest")
						&& !dirFile_AbsPath.contains((File.separator + "test" + File.separator))
						&& !dirFile_AbsPath.contains("intermediates")) {
					old_product_endfixname_DirFile = dirFile;
					System.out.println("match_old_product_endfixname[" + matchFileCount + "] = " + dirFile_AbsPath);
					matchFileCount++;
				}
			}

			if (old_product_endfixname_DirFile == null) {
				System.out.println(
						"当前没有找到 " + old_product_name + "对应的工程目录地址:" + old_product_endfixname_DirFile + "  执行失败请检查!");
				return null;
			}
			System.out.println("old_product_endfixname_DirFile = " + old_product_endfixname_DirFile);

// old_product_endfixname;
// zmain-Life-main_203136\app\src\main\java\com\and\zmain_life  变为
// zmain-Life-main_203136\app\src\main\java\com\and\zmain_life_203136

			String old_product_endfixname_Path = old_product_endfixname_DirFile.getAbsolutePath();
			String new_product_endfixname_Path = old_product_endfixname_Path.replace(old_product_endfixname,
					new_product_endfixname);

			for (int i = 0; i < allSubRealFileList.size(); i++) {
				File copyItem = allSubRealFileList.get(i);
				String copyItem_AbsPath = copyItem.getAbsolutePath();
				// zmain-Life-main 和 zmain-Life-main_202111
				String endfixname_path_AbsPath = copyItem_AbsPath.replace(old_product_endfixname_Path,
						new_product_endfixname_Path);
				if (copyItem_AbsPath.contains(old_product_endfixname_Path)) {
					System.out.println("匹配到 old_product_endfixname_Path=" + old_product_endfixname_Path + " match文件 = "
							+ copyItem_AbsPath);
				}
				String targetItem_AbsPath = endfixname_path_AbsPath.replace(src_apk_Dir.getAbsolutePath(),
						dst_apk_Dir.getAbsolutePath());
				File targetItem = new File(targetItem_AbsPath);
				if (copyItem.getName().toLowerCase().endsWith(".java")) {
					src_all_Java_FileList.add(copyItem);
					dst_all_Java_FileList.add(targetItem);
				}

				if (copyItem.getName().toLowerCase().endsWith(".xml") && copyItem.getAbsolutePath().toLowerCase()
						.contains((File.separator + "layout" + File.separator))) {
					src_all_Layout_XmlFileList.add(copyItem);
					dst_all_Layout_XmlFileList.add(targetItem);
				}

				System.out.println("copyItem[" + (i + 1) + "][" + allSubRealFileList.size() + "] = " + copyItem
						+ "  targetItem=" + targetItem_AbsPath + "  old_product_endfixname_Path="
						+ old_product_endfixname_Path + "   new_product_endfixname_Path=" + new_product_endfixname_Path
						+ "   src_apk_Dir=" + src_apk_Dir + "  dst_apk_Dir=" + dst_apk_Dir);

				fileCopy(copyItem, targetItem);
			}

			System.out.println("src_fixed_FileList.size() = " + src_fixed_FileList.size());
			System.out.println("dst_all_Layout_XmlFileList.size() = " + dst_all_Layout_XmlFileList.size());

			// 开始对 目标文件 进行 修改
			for (int i = 0; i < src_fixed_FileList.size(); i++) {
				File product_fixed_file = src_fixed_FileList.get(i);
				String product_fixed_file_AbsPath = product_fixed_file.getAbsolutePath();
				String target_fixed_file_path = product_fixed_file_AbsPath.replace(src_apk_Dir.getAbsolutePath(),
						dst_apk_Dir.getAbsolutePath());
				File target_fixed_file = new File(target_fixed_file_path);
				if (target_fixed_file.exists()) {
					ArrayList<String> matchFixedArr = srcFile_fixedContent_Map.get(product_fixed_file);
					if (matchFixedArr != null && matchFixedArr.size() > 0) {
						System.out.println("Product_writeItem[" + (i + 1) + "][" + src_fixed_FileList.size()
								+ "]  --> linenum=" + matchFixedArr.size());
						writeContentToFile(target_fixed_file, matchFixedArr);
					}

					for (int j = 0; j < matchFixedArr.size(); j++) {
						System.out.println(matchFixedArr.get(j));
					}

				}

			}

			System.out.println("dst_all_Java_FileList.size() = " + dst_all_Java_FileList.size());
			for (int i = 0; i < dst_all_Java_FileList.size(); i++) {

				File javaFileItem = dst_all_Java_FileList.get(i);
				System.out.println("Java_writeItem[" + (i + 1) + "][" + dst_all_Java_FileList.size() + "]  ");

				ArrayList<String> rawContent = ReadFileContentAsList(javaFileItem);

				if (rawContent == null || rawContent.size() == 0) {
					continue;
				}

				ArrayList<String> fixed_rawContent = new ArrayList<String>();
				for (int j = 0; j < rawContent.size(); j++) {
					String lineStr = rawContent.get(j);

					if (lineStr.contains(old_product_name)) {
						String fixedStr = lineStr.replace(old_product_name, new_product_name);
						fixed_rawContent.add(fixedStr);
						continue;
					}

					fixed_rawContent.add(lineStr);

				}
				writeContentToFile(javaFileItem, fixed_rawContent);

			}

			System.out.println("dst_all_Layout_XmlFileList.size() = " + dst_all_Layout_XmlFileList.size());
			for (int i = 0; i < dst_all_Layout_XmlFileList.size(); i++) {

				File xmlFileItem = dst_all_Layout_XmlFileList.get(i);
				System.out.println("Xml_writeItem[" + (i + 1) + "][" + dst_all_Layout_XmlFileList.size() + "]  ");

				ArrayList<String> rawContent = ReadFileContentAsList(xmlFileItem);

				if (rawContent == null || rawContent.size() == 0) {
					continue;
				}

				ArrayList<String> fixed_rawContent = new ArrayList<String>();
				for (int j = 0; j < rawContent.size(); j++) {
					String lineStr = rawContent.get(j);

					if (lineStr.contains(old_product_name)) {
						String fixedStr = lineStr.replace(old_product_name, new_product_name);
						fixed_rawContent.add(fixedStr);
						continue;
					}

					fixed_rawContent.add(lineStr);

				}
				writeContentToFile(xmlFileItem, fixed_rawContent);

			}

			System.out.println("Clone APK 的操作已完成   目标目录=" + dst_apk_Dir);

			return super.applyDir_SubFileListRule5(allSubDirFileList, allSubRealFileList);

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}

	}

	class Delete_SameMD5File_OnlyExistOneInDir_Rule_52 extends Basic_Rule {

		boolean showmd5 = false;
		boolean force_delete = false;
		ArrayList<File> needDeleteFileList; // 需要删除的文件

		ArrayList<String> allMD5List; // 所有文件的MD5的值

		Delete_SameMD5File_OnlyExistOneInDir_Rule_52() {
			super("#", 52, 5); //
			force_delete = false;
			showmd5 = false;
			needDeleteFileList = new ArrayList<File>();
			allMD5List = new ArrayList<String>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// mdname_true // kaoyan_true gaokao_true

//			src_xxxxxx
//			dst_xxxxx

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem_lower_trim = inputParamList.get(i);
//				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if (paramItem_lower_trim.startsWith("delete_true")) {
					force_delete = true;
				}

				if (paramItem_lower_trim.startsWith("showmd5_true")) {
					showmd5 = true;
				}

			}

			System.out.println(" 当前输入 delete_true=" + force_delete);

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
				ArrayList<File> allSubRealFileList) {
			ArrayList<File> allOperationDirList = new ArrayList<File>();
			if (allSubDirFileList != null) {
				allOperationDirList.addAll(allSubDirFileList);
			}
			allSubDirFileList.add(curDirFile);

			for (int i = 0; i < allOperationDirList.size(); i++) {
				File subDir = allOperationDirList.get(i);
				File[] subFileArr = subDir.listFiles();
				if (subFileArr == null || subFileArr.length == 0) {
					continue;
				}
				ArrayList<String> md5ValueList = new ArrayList<String>();

				for (int j = 0; j < subFileArr.length; j++) {
					File fileItem = subFileArr[j];
					if (fileItem.isDirectory()) {
						continue;
					}
					String md5Value = getMD5Three(fileItem.getAbsolutePath());

					if (!allMD5List.contains(md5Value)) {
						allMD5List.add(md5Value);
					}

					if (!md5ValueList.contains(md5Value)) {
						md5ValueList.add(md5Value);
					} else { // 如果 已经 包含 这个 MD5 说明 已经 包含 这个文件了 那么加入 需要被删除的列表
						needDeleteFileList.add(fileItem);
					}

				}

			}

			for (int i = 0; i < needDeleteFileList.size(); i++) {
				File needDeleteFile = needDeleteFileList.get(i);
				System.out.println("need_delete_重复[" + i + "] = " + needDeleteFile.getAbsolutePath());

				if (force_delete) {
					needDeleteFile.delete();
				}
			}

			String delete_tip = force_delete ? "强制删除Flag:OK" : "强制删除Flag:False";
			System.out.println("相同文件夹下重复文件数量:" + needDeleteFileList.size() + "   " + delete_tip);

			if (showmd5) {

				StringBuilder sbLog = new StringBuilder();
				for (int i = 0; i < allMD5List.size(); i++) {
					String md5Item = allMD5List.get(i);
					sbLog.append(md5Item + "\n");

				}
				writeContentToFile(G2_Temp_Text_File, sbLog.toString());
				NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath()); // 打开 notepad++
			}

			return super.applyDir_SubFileListRule5(allSubDirFileList, allSubRealFileList);
		}

		@Override
		String simpleDesc() {

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  delete_true      //  去除在 一个文件夹中 多余的相同的 MD5 文件   只保留 一个MD5  去重操作  \n" + Cur_Bat_Name + "  #_"
					+ rule_index + " showmd5_true    ###  把 当前的目录中的文件的MD5 输出到文本中   \n" + Cur_Bat_Name + "  #_"
					+ rule_index + " showmd5_true delete_true    ###  把 当前的目录中的文件的MD5 输出到文本中 并删除重复文件  \n" + Cur_Bat_Name
					+ "  #_" + rule_index + "    ### 打印当前目录的重复文件  \n" + ""
//			zrule_apply_G2.bat  #_46  copyright_show  harddir_true
			;
		}

	}

	class SrcDir_Copy2_DstDir_WithFileLength_Rule_51 extends Basic_Rule {
		File src_Dir; // 源目录
		File dst_Dir; // 目的目录

		// 需要执行 从 src 复制到 dst 的 实体文件的 对应关系
		HashMap<File, File> srcFile_dstFile_Map;
		ArrayList<File> needCopyFileList = new ArrayList<File>(); // 需要 进行 复制操作的文件(文件大小不一样)
		private File copyFileItem;

		SrcDir_Copy2_DstDir_WithFileLength_Rule_51() {
			super("#", 51, 4); //

			needCopyFileList = new ArrayList<File>();
			srcFile_dstFile_Map = new HashMap<File, File>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// mdname_true // kaoyan_true gaokao_true

//			src_xxxxxx
//			dst_xxxxx

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem_lower_trim = inputParamList.get(i);
//				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if (paramItem_lower_trim.startsWith("src_")) {

					String srcDirPath = (" " + paramItem_lower_trim).replace(" src_", "");
					srcDirPath = srcDirPath.replace(" ", "");

					File srcDirFile = new File(srcDirPath);
					if (!srcDirFile.exists()) {
						System.out.println("当前 SRC目录[" + srcDirPath + "] 不存在 请检查输入参数!");
						return false;
					}

					if (!srcDirFile.isDirectory()) {
						System.out.println("当前 SRC目录[" + srcDirPath + "] 不是文件夹  请检查输入参数!");

						return false;
					}

					src_Dir = srcDirFile;
				}

				if (paramItem_lower_trim.startsWith("dst_")) {

					String dstDirPath = (" " + paramItem_lower_trim).replace(" dst_", "");
					dstDirPath = dstDirPath.replace(" ", "");

					File dstDirFile = new File(dstDirPath);
					if (!dstDirFile.exists()) {
						System.out.println("当前 DST目录[" + dstDirPath + "] 不存在 请检查输入参数!");
						return false;
					}

					if (!dstDirFile.isDirectory()) {
						System.out.println("当前 DST目录[" + dstDirPath + "] 不是文件夹  请检查输入参数!");

						return false;
					}

					dst_Dir = dstDirFile;
				}

			}

			if (src_Dir == null) {
				System.out.println("当前 SRC 目录[" + src_Dir + "] 为空   请检查输入参数!");

				return false;

			}

			if (dst_Dir == null) {
				System.out.println("当前 DST目录[" + dst_Dir + "] 为空   请检查输入参数!");

				return false;

			}

			System.out.println(" 当前输入 SRC[ " + src_Dir.getAbsolutePath() + "]  Dst[ " + dst_Dir.getAbsolutePath() + "]"
					+ " ] 存在  将执行代码逻辑");

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		// 如果当前的需要 提交的 文件中 不包含 json 文件 那么 不提交这次文件 以达到 省空间的 目的
		// xlsx 每次更新都是全替换 而不是 部分替换 导致 更新文件大小过大

		@Override
		String simpleDesc() {

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  src_xxxxxx  dst_xxxxx    //  把 当前的 原目录中的所有文件 复制到 对应目录的文件， 额外必须包含json文件   更新stock-xlsx时使用 如果文件大小一致 那么就跳过复制的过程  \n"
					+ Cur_Bat_Name + "  #_" + rule_index
					+ " src_  dst_  ###  把 当前的 原目录中的所有文件 复制到 对应目录的文件，xlsx 每次更新都是全替换 而不是 部分替换 导致 更新文件大小过大 特用更新ActionDemo 一天只更新一次   \n"
					+ ""
//			zrule_apply_G2.bat  #_46  copyright_show  harddir_true
			;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
// TODO Auto-generated method stub

			File[] srcFileArr = src_Dir.listFiles();
			File[] dstFileArr = dst_Dir.listFiles();

			if (srcFileArr == null || srcFileArr.length == 0) {
				System.out.println("  当前 原目录 Src目录[" + src_Dir.getAbsolutePath() + "] 中文件为空  ");

				return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);

			}

			// needCopyFileList

			for (int i = 0; i < srcFileArr.length; i++) {
				File fileItem = srcFileArr[i];
				if (fileItem.isDirectory()) { // 不对 文件夹进行复制
					continue;
				}

				String name = fileItem.getName();
				String dstMatchFilePath = dst_Dir.getAbsolutePath() + File.separator + name;
				File dstMatchFile = new File(dstMatchFilePath);

				if (dstMatchFile.exists()) {
					// 源文件大小要大于 10
					long srcFileSize = fileItem.length();
					long dstFileSize = dstMatchFile.length();
					long diffSize = Math.abs(dstFileSize - srcFileSize);

					if (dstMatchFile.length() != fileItem.length() // 两个文件的大小不一样才执行 copy
							&& fileItem.length() > 10 // 源文件的大小要大于10 才执行 copy
							&& diffSize > 100) { // 两个文件的差异要大于100 才 执行 copy 文件

						// 原目录 有这个文件 但是和 dst 目录不一致 那么也加入 copy 列表
						needCopyFileList.add(fileItem);
						srcFile_dstFile_Map.put(fileItem, dstMatchFile);
					}
				} else {
					// 原目录 没有这个文件 那么加入copy列表
					needCopyFileList.add(fileItem);
					srcFile_dstFile_Map.put(fileItem, dstMatchFile);
				}

			}

			System.out.println("当前 需要执行 Copy 操作的 文件 的 总数 : " + needCopyFileList.size());
			System.out.println("开始执行 ———————— Copy-Operation begin —————");

			if (needCopyFileList.size() > 0 && isContainJsonFile(needCopyFileList)) {
				for (int i = 0; i < needCopyFileList.size(); i++) {

					File srcItem = needCopyFileList.get(i);

					File dstFile = srcFile_dstFile_Map.get(srcItem);
					System.out.println("开始第[" + i + "] 个_SRC_文件复制 src[" + srcItem + "][Size:" + srcItem.length()
							+ "][MD5:" + getMD5Three(srcItem.getAbsolutePath()) + "]");
					System.out.println("");

					if (dstFile == null) {
						continue;
					}

					System.out.println("完成第[" + i + "] 个_DST_文件复制 dst[" + dstFile + "][Size:" + dstFile.length()
							+ "][MD5:" + getMD5Three(dstFile.getAbsolutePath()) + "]");

					if (dstFile.exists()) {
						dstFile.delete();
					}

					fileCopy(srcItem, dstFile);

				}

			}
			System.out.println("开始执行 ———————— Copy-Operation end —————");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}

		boolean isContainJsonFile(ArrayList<File> mCopyFileList) {
			for (int i = 0; i < mCopyFileList.size(); i++) {
				File copyFileItem = mCopyFileList.get(i);
				String fileName_lower = copyFileItem.getName().toLowerCase().trim();
				System.out.println("copyFileItem[" + i + "]" + copyFileItem.getAbsolutePath());

				if (fileName_lower != null && fileName_lower.endsWith(".json")) {
					System.out.println("当前 复制 目录 包含 json 文件:" + copyFileItem.getAbsolutePath() + " 将执行复制操作!");
					return true;
				}
			}
			System.out.println("当前 复制文件列表 目录 不包含 json 文件      将不执行复制操作!");

			return false;
		}

	}

	// 把 当前的 输入的 jpg 或者 当前目录下的 jpg文件 动态计算成 320 宽度的 字符串 并 在打开的文件中打印
	class JPG_To_TextChar_Rule_50 extends Basic_Rule {
		// true 那么就对当前目录下的所有 jpg文件进行 打印字符串画像操作 false--说明有输入对输入进行操作
		boolean isDirOperation; // 是否没有输入 jpg 文件 而是 输入了一个 目录 默认shell 目录 已经 输入的目录

		StringBuilder allSB;
		File inputDirFile;
		ArrayList<File> jpgFileList;

		JPG_To_TextChar_Rule_50() {
			super("#", 50, 3); //
			jpgFileList = new ArrayList<File>();
			inputDirFile = null;
			allSB = new StringBuilder();
			isDirOperation = false;
		}

		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			// TODO Auto-generated method stub

			for (int i = 0; i < jpgFileList.size(); i++) {
				File jpgFile = jpgFileList.get(i);
				String jpgFileName = jpgFile.getName();
				String getjpgNameNoType = getFileNameNoPoint(jpgFileName);

				File jpgFile_resultDir = null;

				if (isDirOperation && inputDirFile != null) {
					jpgFile_resultDir = inputDirFile;

					// 打印的 宽度 默认设置为 320
					BufferedImage jpgImage;
					try {
						jpgImage = resizeImage(jpgFile.getAbsolutePath(), 320);
						allSB.append(printImage(jpgImage));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//			        System.out.println(" Width:"+image.getWidth()+"  High:"+image.getHeight());

				} else {
					// 如果 不是 输入 目录 的 话 那么就创建 jpg 的 文件名称 如果是目录的话 那么就在当前目录生成 .json

					BufferedImage jpgImage;
					try {
						jpgImage = resizeImage(jpgFile.getAbsolutePath(), 320);
						allSB.append(printImage(jpgImage));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//			        System.out.println(" Width:"+image.getWidth()+"  High:"+image.getHeight());

				}

			}

			// 打开 notepad++

			writeContentToFile(G2_Temp_Text_File, allSB.toString());
			NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index + "  <指定A.jpg文件> <指定B.jpg文件>  ### 对给定的jpg 打印它的 字符串画像   \n"
					+ Cur_Bat_Name + " #_" + rule_index + "   ### 解析当前目录jpg 打印出所有的画像    \n";
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);

				File tempFile = new File(curDirPath + File.separator + strInput);
				if (tempFile.exists() && !tempFile.isDirectory()) {
					String inputFileName = tempFile.getName().toLowerCase();
					if (inputFileName.endsWith(".jpg") || inputFileName.endsWith(".JPG")) {
						jpgFileList.add(tempFile);
					}

				}

				File inputDir = new File(strInput);
				if (inputDir.exists() && inputDir.isDirectory()) {
					isDirOperation = true;
					inputDirFile = inputDir;
				}

				System.out.println("initParamsWithInputList[" + i + "] = " + strInput + "  inputDir.exists()="
						+ inputDir.exists() + "  inputDir.isDirectory()=" + inputDir.isDirectory());

			}

			if (jpgFileList.size() == 0 && inputDirFile == null) {
				System.out.println("当前 输入的 jpg 文件 为 空 无法获取 输入的 jpg 请检查 输入!! ");
				File shellDir = new File(curDirPath);
				if (shellDir != null && shellDir.exists()) {
					File[] listArr = shellDir.listFiles();

					if (listArr == null || listArr.length == 0) {
						System.out.println("当前 输入的目录  " + inputDirFile.getAbsolutePath() + "没有 任何文件操作!!");

						return false;
					}
					for (int i = 0; i < listArr.length; i++) {
						File fileItem = listArr[i];

						String inputFileName = fileItem.getName().toLowerCase();
						if (inputFileName.endsWith(".jpg") || inputFileName.endsWith(".jpg")) {
							jpgFileList.add(fileItem);
							isDirOperation = true;
							inputDirFile = shellDir;
						}

					}

				} else {
					System.out.println("当前 输入的 jpg 文件为空  shell目录为空 无法获取 输入的 xlsx 请检查 输入!! ");
					return false;

				}

			}

			/*
			 * if (inputDirFile != null) { File[] listArr = inputDirFile.listFiles(); if
			 * (listArr == null || listArr.length == 0) { System.out.println("当前 输入的目录  " +
			 * inputDirFile.getAbsolutePath() + "没有 任何文件操作!!");
			 *
			 * return false; } for (int i = 0; i < listArr.length; i++) { File fileItem =
			 * listArr[i];
			 *
			 * String inputFileName = fileItem.getName().toLowerCase(); if
			 * (inputFileName.endsWith(".jpg") || !jpgFileList.contains(fileItem)) {
			 * jpgFileList.add(fileItem); }
			 *
			 * }
			 *
			 * }
			 */

			if (jpgFileList.size() == 0) {
				System.out.println(
						"当前 输入的目录  inputDirFile =" + (inputDirFile == null ? "null" : inputDirFile.getAbsolutePath())
								+ "没有 任何文件的 .jpg .JPG文件进行操作!!");

				return false;

			}
			if (inputDirFile == null) {
				System.out.println("ZXX inputDirFile = null " + " jpgFileList.size()=" + jpgFileList.size()
						+ "   isDirOperation=" + isDirOperation);

			} else {
				System.out.println("ZXX inputDirFile =" + inputDirFile.getAbsolutePath() + "    jpgFileList.size()="
						+ jpgFileList.size() + "   isDirOperation=" + isDirOperation);

			}

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		/**
		 * 图片缩放
		 *
		 * @param srcImagePath 图片路径
		 * @param targetWidth  目标宽度
		 * @return
		 * @throws IOException
		 */
		public BufferedImage resizeImage(String srcImagePath, int targetWidth) throws IOException {
			Image srcImage = ImageIO.read(new File(srcImagePath));
			// 这里除以2 原始高度除以2 notepad++ 打开宽高宽度不一致 这里使得高度减少一半 看起来对称
			int targetHeight = (int) (getTargetHeight(targetWidth, srcImage) / 2);
			BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = resizedImage.createGraphics();
			graphics2D.drawImage(srcImage, 0, 0, targetWidth, targetHeight, null);
			graphics2D.dispose();
			return resizedImage;
		}

		/**
		 * 图片缩放
		 *
		 * @param srcImagePath 图片路径
		 * @param targetWidth  目标宽度
		 * @return
		 * @throws IOException
		 */
		public BufferedImage resizeImage2(String srcImagePath, int targetWidth) throws IOException {
			Image srcImage = ImageIO.read(new File(srcImagePath));
			int targetHeight = getTargetHeight(targetWidth, srcImage);
			Image image = srcImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
			BufferedImage bufferedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			bufferedImage.getGraphics().drawImage(image, 0, 0, null);
			return bufferedImage;
		}

		/**
		 * 根据指定宽度，计算等比例高度
		 *
		 * @param targetWidth 目标宽度
		 * @param srcImage    图片信息
		 * @return
		 */
		private int getTargetHeight(int targetWidth, Image srcImage) {
			int targetHeight = srcImage.getHeight(null);
			if (targetWidth < srcImage.getWidth(null)) {
				targetHeight = Math
						.round((float) targetHeight / ((float) srcImage.getWidth(null) / (float) targetWidth));
			}
			return targetHeight;
		}

		/**
		 * 图片打印
		 *
		 * @param image
		 * @throws IOException
		 */
		public String printImage(BufferedImage image) throws IOException {
			final char[] PIXEL_CHAR_ARRAY = { 'W', '@', '#', '8', '&', '*', 'o', ':', '.', ' ' };
			int width = image.getWidth();
			int height = image.getHeight();
			StringBuilder imageSB = new StringBuilder();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int rgb = image.getRGB(j, i);
					Color color = new Color(rgb);
					int red = color.getRed();
					int green = color.getGreen();
					int blue = color.getBlue();
					// 一个用于计算RGB像素点灰度的公式
					Double grayscale = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
					double index = grayscale / (Math.ceil(255 / PIXEL_CHAR_ARRAY.length) + 0.5);
					System.out.print(PIXEL_CHAR_ARRAY[(int) (Math.floor(index))]);
					imageSB.append(PIXEL_CHAR_ARRAY[(int) (Math.floor(index))]);
				}

				System.out.println();
				imageSB.append("\n");
			}

			imageSB.append("\n");
			imageSB.append("\n");
			imageSB.append("\n");
			imageSB.append("\n");
//	        imageSB.append("\n");
//	        imageSB.append("\n");
			return imageSB.toString();
		}

	}

	// 给定一个 类型的 模板 起始页(默认1) 最终页(默认100)
	// 是否代理(默认false) itemtag(页面内的标记用于过滤) imagetag(详细内容也的照片的标示)
	// CategoryModel_HttpPage_Download_Rule_49

	class CategoryModel_HttpPage_Download_Rule_49 extends Basic_Rule {

		String mCategoryModel; // Page 主页的模板 使用 {page} 来对 页面进行替换 默认为 {page}

		int beginPageIndex;
		int mNextPageStep = 1;
		int endPageIndex;

		ArrayList<String> mDynamicPageUrlList;
		boolean isProxy; // 是否代理

		String mHrefTag = ""; // category 页面下的 每 个项的 标示
		String mImageTag = ""; // 需要下载的图片的特殊标示 如果为空 那么 就下载 全部 不过滤
		int mSleepInterval; // 每个 页面 完成下载的 睡眠 时间 默认 10秒

		// 有些参数从 bat 传递不过来 比如 & ? 等等 需要做转义
		HashMap<String, String> needReplaceWordMap;
		ArrayList<String> needReplaceWordKeyList;
		ArrayList<String> needRealExistWordValueList;

		// https://tieba.baidu.com/f?kw=%E6%9D%8E%E6%AF%85&ie=utf-8&pn=350
		String mCategoryMainUrl; // item 页面是使用相对的路径 没有出现 绝对路径 所以得 保存 这个 主路径
		String httpXpre; // http:// 或者 https://(默认)

		// 有些网页 不是按照 页面内容来的 是按照 多少个item数值 来的 所以有这个参数 每页的步长 默认为1

		ChromeDriver mChromeDriver;
		File ImageDownloadDir;

		CategoryModel_HttpPage_Download_Rule_49() {
			super("#", 49, 4);

			mCategoryModel = "{page}";
			mCategoryMainUrl = mCategoryModel;
			httpXpre = "https://";
			beginPageIndex = 1;
			endPageIndex = 100;
			mDynamicPageUrlList = new ArrayList<String>();

			isProxy = false;
			mHrefTag = ""; // category 页面下的 每 个项的 标示
			mImageTag = ""; // 需要下载的图片的特殊标示
			mSleepInterval = 10;
			mNextPageStep = 1;

			needReplaceWordKeyList = new ArrayList<String>();
			needRealExistWordValueList = new ArrayList<String>();
			needReplaceWordMap = new HashMap<String, String>();
			initReplaceWord();

		}

		void initReplaceWord() {
			addReplaceWord("#", "=");
			addReplaceWord("@", "&");
		}

		void addReplaceWord(String mSrcWord, String mDstWord) {

			needReplaceWordKeyList.add(mSrcWord);
			needRealExistWordValueList.add(mDstWord);
			needReplaceWordMap.put(mSrcWord, mDstWord);

		}

		String replaceWordToExist(String mSrcStr) {
			String result = mSrcStr;

			for (int i = 0; i < needReplaceWordKeyList.size(); i++) {
				String holdstr = needReplaceWordKeyList.get(i);
				String realValue = needReplaceWordMap.get(holdstr);
				result = result.replaceAll(holdstr, realValue);

			}

			return result;

		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if (paramItem_lower_trim.startsWith("model_")) {
					String mModelRaw = paramItem_lower_trim.replace("model_", "").trim();
					mCategoryModel = replaceWordToExist(mModelRaw);
				}

				if (paramItem_lower_trim.startsWith("proxy_true")) {

					isProxy = true;
				}

				if (paramItem_lower_trim.startsWith("pagestep_")) {
					String mPageStepStr = paramItem_lower_trim.replace("pagestep_", "").trim();
					if (isNumeric(mPageStepStr)) {
						mNextPageStep = Integer.parseInt(mPageStepStr);
					}
				}

				if (paramItem_lower_trim.startsWith("beginpage_")) {
					String mBeginPageStr = paramItem_lower_trim.replace("beginpage_", "").trim();
					if (isNumeric(mBeginPageStr)) {
						beginPageIndex = Integer.parseInt(mBeginPageStr);
					}
				}

				if (paramItem_lower_trim.startsWith("endpage_")) {
					String mEndPageStr = paramItem_lower_trim.replace("endpage_", "").trim();
					if (isNumeric(mEndPageStr)) {
						endPageIndex = Integer.parseInt(mEndPageStr);
					}
				}

				if (paramItem_lower_trim.startsWith("sleeptime_")) {
					String mSleepTimeStr = paramItem_lower_trim.replace("sleeptime_", "").trim();
					if (isNumeric(mSleepTimeStr)) {
						mSleepInterval = Integer.parseInt(mSleepTimeStr);
					}
				}

				if (paramItem_lower_trim.startsWith("hreftag_")) {
					String mHrefRaw = paramItem_lower_trim.replace("hreftag_", "").trim();
					mHrefTag = replaceWordToExist(mHrefRaw);
				}

				if (paramItem_lower_trim.startsWith("imagetag_")) {
					String mImageTagRaw = paramItem_lower_trim.replace("imagetag_", "").trim();
					mImageTag = replaceWordToExist(mImageTagRaw);
				}

			}

			ImageDownloadDir = new File(
					curDirPath + File.separator + "Rule" + rule_index + "_Download_" + getTimeStamp());

			if (!ImageDownloadDir.exists()) {
				ImageDownloadDir.mkdirs();
			}

			File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());

			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
			// 驱动位置
			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");

			CUR_CHROME_OPTIONS.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			mChromeDriver = new ChromeDriver(CUR_CHROME_OPTIONS);

//			mCategoryModel = mCategoryModel.replace("{page}", beginPageIndex+"");

//			String mCategoryMainUrl ;  // item 页面是使用相对的路径  没有出现 绝对路径  所以得 保存 这个 主路径
//			String httpXpre ;  //  http://     或者  https://(默认)

			System.out.println(" mCategoryModel = " + mCategoryModel);
			if (mCategoryModel.contains("http://")) {

				httpXpre = "http://";
			}

			String clearHttpModel = mCategoryModel.replace("https://", "").replace("http://", "").trim();

			if (clearHttpModel.contains("/")) {
				mCategoryMainUrl = clearHttpModel.substring(0, clearHttpModel.lastIndexOf("/"));
			}

			System.out.println(" ImageDownloadDir = " + ImageDownloadDir);
			System.out.println(" httpXpre = " + httpXpre);
			System.out.println(" mCategoryMainUrl = " + mCategoryMainUrl);

			System.out.println(" beginPageIndex = " + beginPageIndex);
			System.out.println(" mHrefTag = " + mHrefTag);

			System.out.println(" mImageTag = " + mImageTag);

			System.out.println(" beginPageIndex = " + beginPageIndex);

			System.out.println(" mNextPageStep = " + mNextPageStep);

			System.out.println(" endPageIndex = " + endPageIndex);

			System.out.println(" mSleepInterval = " + mSleepInterval);
			System.out.println(" isProxy = " + isProxy);

			initModelCategoryPageList(mCategoryModel, beginPageIndex, mNextPageStep, endPageIndex);

			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		// model_https://tieba.baidu.com/f?kw#%E6%9D%8E%E6%AF%85@ie#utf-8&pn#{page}
		// beginpage_1 endpage_1000 pagestep_50 hreftag_https://tieba.baidu.com/p/
		// imagetag_ proxy_true sleeptime_10

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index + "   //   给定一个页面{page}模板 =转为# &转为@  传递到代码进行图片的自动搜索下载功能  \n"

					+ Cur_Bat_Name + " #_" + rule_index
					+ "  model_https://www.52pojie.cn/forum-4-{page}.html  beginpage_1  endpage_94 pagestep_1  hreftag_thread  imagetag_attach.52pojie sleeptime_10  proxy_true    ###// 爬取{page}网页-52破解  \n"

					+ Cur_Bat_Name + " #_" + rule_index
					+ "  model_https://tieba.baidu.com/f?kw#%E6%9D%8E%E6%AF%85@ie#utf-8@pn#{page}   beginpage_0  endpage_1000 pagestep_50  hreftag_p   imagetag_    proxy_true  sleeptime_10   ###// 爬取{page}网页-百度贴吧  \n"

			;
		}

		void initModelCategoryPageList(String modelUrl, int beginIndex, int pageStep, int engIndex) {

			String rawModelUrl = new String(modelUrl);

			int curStep = beginIndex;
			for (int i = beginIndex; i <= engIndex; i++) {
				String mModelPageStr = new String(modelUrl);

				String realPage = mModelPageStr.replace("{page}", curStep + "");
				curStep += pageStep;

				mDynamicPageUrlList.add(realPage);
			}

			for (int i = 0; i < mDynamicPageUrlList.size(); i++) {
				String pageTip = mDynamicPageUrlList.get(i);
				System.out.println("realPage[" + (i + 1) + "] = " + pageTip);

			}

		}

		void itemPageOperation(String mPagehtmlCode, String mainUrl, ArrayList<String> mMatchHrefTagInPageList) {

			org.jsoup.nodes.Document curDocument = Jsoup.parse(mPagehtmlCode);

			String rawHtmlStr = curDocument.html();
			ArrayList<String> mAllHrefInPageList = new ArrayList<String>(); // 所有的 href

			Elements mElements = curDocument.getElementsByTag("a");

//			Elements  mElements =  curDocument.select("a[href]");
// a class="bm_h" href="javascript:;" rel="forum.php?mod=forumdisplay&fid=2&page=3" curpage="2" id="autopbn" totalpage="204" picstyle="0" forumdefstyle="">下一页 &raquo;</a>
// <a href="forum-2-3.html" class="nxt">下一页</a>

			if (mElements != null && mElements.size() > 0) {

				System.out.println("a[href] mElements.size()  = " + mElements.size());
				Iterator<org.jsoup.nodes.Element> nextpage_element_iterator = mElements.iterator();
				while (nextpage_element_iterator.hasNext()) {
					org.jsoup.nodes.Element curElement = nextpage_element_iterator.next();
					String innerHtml = curElement.html();
					String href = curElement.attr("href");
					System.out.println("nexttip_innerHtml = " + innerHtml + "   href[" + href + "]");
					mAllHrefInPageList.add(href);

					// 判断这个 Href 是否符合 hreftag
					if (href.startsWith(mHrefTag)) {

						if (!mMatchHrefTagInPageList.contains(httpXpre + mainUrl + "/" + href)) {
							mMatchHrefTagInPageList.add(httpXpre + mainUrl + "/" + href);
						}

					}

					/*
					 * if (isInnerHtmlContainNextTip(innerHtml, nextUrlTipList)) {
					 * 
					 * if (href == null || "".equals(href.trim()) || href.contains("javascript")) {
					 * continue; }
					 * 
					 * if (!href.startsWith("http")) { String fixed_href = rootUrl_A.mRootUrl + "/"
					 * + href; fixed_href = fixed_href.replace("//", "/"); if
					 * (!categoryUrl_B.mCategoryPageUrlList.contains(fixed_href)) {
					 * categoryUrl_B.nextPageUrl = fixed_href;
					 * categoryUrl_B.mCategoryPageUrlList.add(fixed_href);
					 * System.out.println("a[href]_Next_Tip  nextPageUrl=[" + fixed_href +
					 * "]  innerHtml = " + innerHtml + "   href[" + href + "]");
					 * 
					 * return; }
					 * 
					 * }
					 * 
					 * }
					 */

				}

			}

		}

		void HrefDetailHtmlCodeOperation(String mHrefHtmlCode, ArrayList<String> mImageSrcInPageList) {
			// 获取 全部的 <img

			org.jsoup.nodes.Document hrefDocument = Jsoup.parse(mHrefHtmlCode);

			String rawHrefHtmlStr = hrefDocument.html();

			Elements mImgElements = hrefDocument.getElementsByTag("img");

			if (mImgElements != null && mImgElements.size() > 0) {

				System.out.println("a[href] mElements.size()  = " + mImgElements.size());
				Iterator<org.jsoup.nodes.Element> nextpage_element_iterator = mImgElements.iterator();
				while (nextpage_element_iterator.hasNext()) {
					org.jsoup.nodes.Element curElement = nextpage_element_iterator.next();
					String innerHtml = curElement.html();
					String src = curElement.attr("src");
					System.out.println("nexttip_innerHtml = " + innerHtml + "   src[" + src + "]");

					// 判断这个 Href 是否符合 hreftag
					if (mImageTag == null || "".equals(mImageTag)) {
						if (src.startsWith("http")
								&& (src.toLowerCase().endsWith(".gif") || src.toLowerCase().endsWith(".webp")
										|| src.toLowerCase().endsWith(".jpg") || src.toLowerCase().endsWith(".png")

								)) {

							if (!mImageSrcInPageList.contains(src)) {
								mImageSrcInPageList.add(src);
							}

						}

					} else {
						if (src.contains(mImageTag)) {

							if (src.startsWith("http")
									&& (src.toLowerCase().endsWith(".gif") || src.toLowerCase().endsWith(".webp")
											|| src.toLowerCase().endsWith(".jpg") || src.toLowerCase().endsWith(".png")

									)) {

								if (!mImageSrcInPageList.contains(src)) {
									mImageSrcInPageList.add(src);
								}

							}

						}

					}

				}

			}

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			for (int i = 0; i < mDynamicPageUrlList.size(); i++) {
				String pageItemUrl = mDynamicPageUrlList.get(i);
				System.out.println("pageItemUrl[" + (i + 1) + "][" + mDynamicPageUrlList.size() + "] = " + pageItemUrl);
				ArrayList<String> mPageItemHrefList = new ArrayList<String>();

				// 获取到 这个网页的 源码

				String pageHtmlCode = getMainPageHtmlCode(pageItemUrl);

				if (pageHtmlCode == null || pageHtmlCode.length() < 10) {

					System.out.println(" 获取 PageHtmlCode 失败 --> " + "pageItemUrl[" + (i + 1) + "] = " + pageItemUrl);
					continue;
				}

				itemPageOperation(pageHtmlCode, mCategoryMainUrl, mPageItemHrefList);

				if (mPageItemHrefList.size() > 0) {

					for (int j = 0; j < mPageItemHrefList.size(); j++) {
						String hrefUrl = mPageItemHrefList.get(j);
						System.out.println("pageItemUrl[" + (i + 1) + "][" + mDynamicPageUrlList.size() + "] = "
								+ pageItemUrl + "  hrefItemUrl[" + (j + 1) + "][" + mPageItemHrefList.size() + "] = "
								+ hrefUrl);

					}

					for (int j = 0; j < mPageItemHrefList.size(); j++) {
						String hrefUrl = mPageItemHrefList.get(j);

						System.out.println("Down pageItemUrl[" + (i + 1) + "] = " + pageItemUrl + "  hrefItemUrl["
								+ (j + 1) + "][" + j + "] = " + hrefUrl);

						String mHrefHtmlCode = getMainPageHtmlCode(hrefUrl);

						if (mHrefHtmlCode == null || mHrefHtmlCode.length() < 10) {

							System.out.println(" 获取 HrefHtmlCode 失败 --> " + "pageItemUrl[" + (i + 1) + "] = "
									+ pageItemUrl + "  hrefItemUrl[" + (j + 1) + "][" + j + "] = " + hrefUrl);
							continue;
						}

						ArrayList<String> mImageSrcInHrefPageList = new ArrayList<String>();

						HrefDetailHtmlCodeOperation(mHrefHtmlCode, mImageSrcInHrefPageList);

						if (mImageSrcInHrefPageList.size() > 0) {

							for (int k = 0; k < mImageSrcInHrefPageList.size(); k++) {
								String imgSrc = mImageSrcInHrefPageList.get(k);
								String srcType = getFileTypeWithPoint(imgSrc);
								System.out.println("pageItemUrl[" + (i + 1) + "][" + mDynamicPageUrlList.size() + "] = "
										+ pageItemUrl + "  hrefItemUrl[" + (j + 1) + "][" + mPageItemHrefList.size()
										+ "] = " + hrefUrl + " ImgSrc[" + k + "][" + mImageSrcInHrefPageList.size()
										+ "]=" + imgSrc);

								File curFileImag = new File(ImageDownloadDir.getAbsolutePath() + File.separator
										+ getTimeStampLong() + srcType);

								TryDownLoadImage(imgSrc, curFileImag, 3);

							}

						}

					}

				}

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		public void TryDownLoadImage(String httpUrl, File localFile, int repeatTimes) {

			int byteRead;
			try {

				if (localFile.exists()) {
					localFile.createNewFile();
				}
				String fileAddress = localFile.getAbsolutePath();
//			String fileAddress = mDownloadedMonthDir.getAbsolutePath() + File.separator
//					+ (source == null || "".equals(source) ? "" : source + "_") + (fileNameNoPoint.replace(" ", ""))
//					+ "_" + index + "_" + getTimeStamp() + ".mp4";

				URLConnection conn = null;
				URL url = new URL(httpUrl);
				if (isProxy) {
					InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7078);
					Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http代理协议类型
					conn = url.openConnection(proxy);
				} else {
					conn = url.openConnection();
				}

				System.out.println("下载操作:[ " + httpUrl + " ]   \n fileAddress:" + fileAddress + " isProxy=" + isProxy);

				// 输入流
				InputStream inStream = conn.getInputStream();
				// 封装一个保存文件的路径对象
				File fileSavePath = new File(fileAddress);
				// 注:如果保存文件夹不存在,那么则创建该文件夹
//				File fileParent = fileSavePath.getParentFile();
//				if (!fileParent.exists()) {
//					fileParent.mkdirs();
//				}
				// 写入文件
				FileOutputStream fs = new FileOutputStream(fileSavePath);
				byte[] buffer = new byte[1024];
				while ((byteRead = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteRead);
				}
				inStream.close();
				fs.close();
				System.out.println("\n-----url[ " + httpUrl + " ]下载完成-----\n" + fileSavePath.getAbsolutePath());

			} catch (FileNotFoundException e) {
				System.out.println("ZFileNotFoundException==" + e.getMessage());
			} catch (IOException e) {
				System.out.println("ZIOException:" + e.getMessage());
				if (repeatTimes <= 3) {
					TryDownLoadImage(httpUrl, localFile, repeatTimes + 1); // 再次执行 下载操作

				}
			}
		}

	}

	// voice_fafaefafea 读取 voice 的 内容
	class Read_Speak_Word_Rule_48 extends Basic_Rule {
		String mVoice_Utf8;

		Read_Speak_Word_Rule_48() {
			super("#", 48, 4);
			mVoice_Utf8 = "HelloWorld_Zukgit";
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			String mVbsFilePath = zbinPath + File.separator + "B3_voice.vbs";
			String commadStr = "CreateObject(\"SAPI.SpVoice\").Speak \"" + mVoice_Utf8 + "\"";

			try {
				String mANSIStr = new String(commadStr.getBytes("UTF-8"), "GB2312");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File voice_file = new File(mVbsFilePath);

			ANSI_writeContentToFile(voice_file, commadStr);
//		        UTF8File_To_ANSIFile(voice_file);

//		        RuntimeUtil.exec("Wscript.exe  /x " + voice_file.getAbsolutePath());
			execCMD(voice_file.getAbsolutePath());
			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if (paramItem_lower_trim.startsWith("voice_")) {
					mVoice_Utf8 = paramItem_lower_trim.replace("voice_", "").trim();
				}

			}

			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index + "   // 读取给定的voice 发出声音  \n"

					+ Cur_Bat_Name + " #_" + rule_index + "  voice_12345ABCDE   // 读取给定的voice 发出声音  \n"

			;
		}

	}

	// 给定一个 西瓜视频 主页 下载 该页面内的所有视频文件
	class Download_XiGua_HomeVideo_Rule_47 extends Basic_Rule {
		ArrayList<String> allHrefList; // 所有引用的链接
		ArrayList<String> allNumberHrefList; // 所有只有数字的引用链接( 西瓜视频 里 特有的 )
		ArrayList<String> allNumberHttpLinkList; // https://www.ixigua.com/+数值href的集合
		File mDownloadedMonthDir;
		File ChromeDriverFile;
		// 必须以 http 开头
		String searchHttpUrl; // 从输入传入的 需要得到 的 http 源码的 网页的地址
		ChromeDriver mChromeDriver;
		String curPositionHtmlCodeStr; // 每次得到的 html代码的值 用于突然用户终止程序时 使用

		Download_XiGua_HomeVideo_Rule_47() {
			super("#", 47, 4);
			allHrefList = new ArrayList<String>();
			allNumberHrefList = new ArrayList<String>();
			allNumberHttpLinkList = new ArrayList<String>();
			mDownloadedMonthDir = curDirFile;
			if (mDownloadedMonthDir == null) {
				mDownloadedMonthDir = new File(curDirPath);
			}
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
				mainHtml = Jsoup.parse(getXiGua_MainPageSource(url));

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

							downRawVideo_WithUrl(index, NoMainUrl_VideoTag_url, "", "TouTiao");
						} else {

							System.out.println(
									"解析出的 base64_jiami_main_url 为空  NoMainUrl_VideoTag_url 为空 无法下载视频到本地   base64_jiami_url="
											+ base64_jiami_url);

						}

					} else {
						System.out.println("解析出的 base64_jiami_url=[" + base64_jiami_url + "]  尝试解密base64");

						jiemi_base64_url = jiemi_decryptBASE64(base64_jiami_url);
						System.out.println();

						System.out.println("解析出的地址  jiemi_base64_url = [" + jiemi_base64_url + "]");

						if (jiemi_base64_url.startsWith("http")) {
							System.out.println("执行 main_url 下载操作!!!    jiemi_base64_url=[" + jiemi_base64_url + "]");
							System.out.println("XXAAA");
							downRawVideo_WithUrl(index, jiemi_base64_url, "", "TouTiao");

							System.out.println("XXBB");
						} else {
							System.out.println("解密出的地址不是以  http 开头  无法下载!!!");
						}

					}

				} else {
					System.out.println(
							"当前读取到的 网页源码为空 ,   可能 G2_chromedriver版本 和 当前浏览器版本不一致!!   \n chromedriver.exe 下载地址: http://npm.taobao.org/mirrors/chromedriver/");
				}

			} catch (Exception e) {
				e.fillInStackTrace();
				// TODO: handle exception
				System.out.println("异常 Exception.getMessage = " + e.getMessage());

				System.out.println("异常 Exception = " + e);

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
						downRawVideo_WithUrl(index, jiemi_base64_bankurl, "", "TouTiao");

					}

				} catch (Exception e1) {

					System.out.println("尼玛 不干了  备用的 bankup_url 也解析失败!! 下载失败!! jiemi_base64_bankurl =["
							+ jiemi_base64_bankurl + "]");
					// TODO: handle exception
				}

			}

		}

		/**
		 * 获取首页内容
		 *
		 * @return 首页内容
		 * @throws InterruptedException 睡眠中断异常
		 */
		String getXiGua_MainPageSource(String url) {

			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
			// 驱动位置
			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");

//			CUR_CHROME_OPTIONS.addArguments("Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//			CUR_CHROME_OPTIONS.addArguments("Accept-Encoding=gzip, deflate, sdch");
//			CUR_CHROME_OPTIONS.addArguments("Accept-Language=zh-CN,zh;q=0.8");
//			CUR_CHROME_OPTIONS.addArguments("Connection=keep-alive");
//			CUR_CHROME_OPTIONS.addArguments("Host=activityunion-marketing.meituan.com");
//			CUR_CHROME_OPTIONS.addArguments("Upgrade-Insecure-Requests=1");
//			CUR_CHROME_OPTIONS.addArguments("User-Agent=Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4");

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

		// 视频的保存 目录 不能是 当前文件 否则 就会执行 同步操作 影响网速
		@SuppressWarnings("unchecked")
		public void downRawVideo_WithUrl(int index, String httpUrl, String fileNameNoPoint, String source) {
//	        String fileAddress = videoSavePath+"/"+source+"/"+title+".mp4";
			if (mDownloadedMonthDir == null) {
				mDownloadedMonthDir = new File(curDirPath);
			}
			String fileAddress = mDownloadedMonthDir.getAbsolutePath() + File.separator
					+ (source == null || "".equals(source) ? "" : source + "_") + (fileNameNoPoint.replace(" ", ""))
					+ "_" + index + "_" + getTimeStamp() + ".mp4";

			System.out.println("index = " + index);
			System.out.println("fileAddress = " + fileAddress);

			System.out.println("httpUrl = " + httpUrl);
			System.out.println("fileNameNoPoint = " + fileNameNoPoint);
			System.out.println("source = " + source);

			int byteRead;
			try {
				URL url = new URL(httpUrl);
				// 获取链接
				URLConnection conn = url.openConnection();
				// 输入流
				InputStream inStream = conn.getInputStream();
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
				while ((byteRead = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteRead);
				}
				inStream.close();
				fs.close();
				System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
				System.out.println("\nzzfile_3.bat " + fileSavePath.getParentFile().getAbsolutePath());

				System.out.println("由于 isMDName=true  视频文件将以 MD5 属性文件名称进行命名!!! ");

				// 获取文件的 md值 并重命名为 mdxxxx.mp4
				String mdName = getMD5Three(fileSavePath.getAbsolutePath());
				String new_Md_Name = mdName + ".mp4";
				tryReName(fileSavePath, new_Md_Name);
				// 把下载的 mp4 文件 名称 转为 md值

			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
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

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// TODO Auto-generated method stub

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();
				System.out.println("paramItem[" + i + "] = " + paramItem_lower_trim);
				if (paramItem_lower_trim.startsWith("http")) {
					searchHttpUrl = paramItem_lower_trim;
				}

			}

			if (searchHttpUrl == null) {
				System.out.println("当前输入的网页为空  请检查输入!!!");
				return false;
			}

			ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());

			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
			// 驱动位置
			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");

			CUR_CHROME_OPTIONS.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			mChromeDriver = new ChromeDriver(CUR_CHROME_OPTIONS);

			return super.initParamsWithInputList(inputParamList);
		}

		void registerShutDownLister() {
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						Thread.sleep(200);
						System.out.println("════════ 监听到 Ctr+Z stop进程操作 将执行保存当前页面位置代码的操作 ════════");

						writeContentToFile(G2_Temp_Text_File, curPositionHtmlCodeStr);
						NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());

						System.out.println("获得【" + searchHttpUrl + "】 MainPage HtmlCode 突然终止 部分成功 !! ");
						System.out.println("════════" + "════════");
						// some cleaning up code...

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub

			registerShutDownLister();
			if (searchHttpUrl.startsWith("https://profile.zjurl.cn/rogue/ugc/profile")
					&& searchHttpUrl.contains("user_id")) {
				String user_id_raw = searchHttpUrl.substring(searchHttpUrl.indexOf("user_id="));
				String user_id = user_id_raw.substring(0, user_id_raw.indexOf("&")).replace("&", "").replace("user_id=",
						"");
				// https://www.ixigua.com/home/3346556174218692
				System.out.println(" profile searchHttpUrl=" + searchHttpUrl);
				System.out.println(" profile  user_id_raw=" + user_id_raw);
				System.out.println(" profile  user_id=" + user_id);
				searchHttpUrl = "https://www.ixigua.com/home/" + user_id.trim();
			}

			String httpPageCode = BrowserOperation_WithRootUrl(searchHttpUrl);

			// 对页面的链接 href 进行分析
			String mHrefLog = TryHrefAnalysis(httpPageCode);

			if (httpPageCode != null) {

				curPositionHtmlCodeStr = httpPageCode + "\n" + "════════════════════ Href-Log ════════════════════\n"
						+ mHrefLog;

				writeContentToFile(G2_Temp_Text_File,
						httpPageCode + "\n" + "════════════════════ Href-Log ════════════════════\n" + mHrefLog);
				NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());
				System.out.println("获得【" + searchHttpUrl + "】 MainPage HtmlCode 成功!! ");
			} else {
				System.out.println("获得【" + searchHttpUrl + "】 MainPage HtmlCode 失败!! ");

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		String TryHrefAnalysis(String mPageHtmlCode) {
			StringBuilder mLogSB = new StringBuilder();

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

		@Override
		String simpleDesc() {

			// 输入参数有空格 导致 无法直接传递从 shell 传递到 java执行代码 所以这里 注释掉 直接使用 textrule就不存在这样的情况
//			return "\n" + Cur_Bat_Name + " #_" + rule_index+"  https://www.ixigua.com/home/3346556174218692   ###  给定一个 西瓜视频 主页 下载 该页面内的所有视频文件   " +
//		"\n"+	 Cur_Bat_Name + " #_" + rule_index+"  https://profile.zjurl.cn/rogue/ugc/profile/?version_code=851&version_name=80501&user_id=100443303952   ###  给定一个 头条新闻用户主页 下载该页面内的所有视频文件   ";

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  https://www.ixigua.com/home/3346556174218692   ###  给定一个 西瓜视频 主页 下载 该页面内的所有视频文件   ";

//			https://profile.zjurl.cn/rogue/ugc/profile/?version_code=851&version_name=80501&user_id=100443303952
		}

		String BrowserOperation_WithRootUrl(String mMainUrl) {

			String mainPageHtmlStr = null;
			File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());

			ChromeDriver driver = mChromeDriver;
			int loop_index = 0;
			try {
				long waitTime = 1000;
				long timeout = 60_000; // 15 秒 // 给他 1分钟

				driver.get(mMainUrl);
				String title = driver.getTitle();
				System.out.printf("loop_index[" + loop_index + "] = " + title);

				System.out.printf("A now accesss %s \n", driver.getCurrentUrl());

//				 long waitTime = Double.valueOf(Math.max(3, Math.random() * 5) * 1000).longValue();
				TimeUnit.MILLISECONDS.sleep(waitTime);

				// 循环下拉，直到全部加载完成或者超时
				do {
					new Actions(driver).sendKeys(Keys.END).perform();
					TimeUnit.MILLISECONDS.sleep(waitTime);

					timeout -= waitTime;
					loop_index++;
					curPositionHtmlCodeStr = driver.getPageSource();
				} while (!driver.getPageSource().contains("已经到底部，没有新的内容啦") && timeout > 0);
				System.out.println("BrowserOperation_WithRootUrl 已经到底部，没有新的内容啦");
				mainPageHtmlStr = driver.getPageSource();
				curPositionHtmlCodeStr = driver.getPageSource();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("获取网页源码的时候出错  mMainUrl = " + mMainUrl);
				e.printStackTrace();

			}

			if (mainPageHtmlStr != null) {

				System.out.println("当前已经得到网页Html代码如下:\n" + mainPageHtmlStr);

			} else {
				System.out.println("rootUrl.mHtmlStr  ==== null ");
			}

			return mainPageHtmlStr;
		}

	}

// 3038年 5 月 3 日

	class ShowJpgTagContent_To_Image_Rule_46 extends Basic_Rule {

		File outJpgFile; // 当前目录的产出目录
		boolean is_jpgharddir; // 目录是否 固定

		boolean is_model_show;
		boolean is_manufacturer_show;
		boolean is_artist_show;
		boolean is_copyright_show;
		boolean is_description_show;
		boolean is_comment_show;

		ShowJpgTagContent_To_Image_Rule_46() {
			super("#", 46, 4);
			is_model_show = false;
			is_manufacturer_show = false;
			is_artist_show = false;
			is_copyright_show = false;
			is_description_show = false;
			is_comment_show = false;
			is_jpgharddir = false;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// mdname_true // kaoyan_true gaokao_true

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if ("model_show".equals(paramItem_lower_trim)) {
					is_model_show = true;
				}

				if ("manufacturer_show".equals(paramItem_lower_trim)) {
					is_manufacturer_show = true;
				}

				if ("artist_show".equals(paramItem_lower_trim)) {
					is_artist_show = true;
				}

				if ("copyright_show".equals(paramItem_lower_trim)) {
					is_copyright_show = true;
				}

				if ("description_show".equals(paramItem_lower_trim)) {
					is_description_show = true;
				}

				if ("comment_show".equals(paramItem_lower_trim)) {
					is_comment_show = true;
				}

				if ("harddir_true".equals(paramItem_lower_trim)) {
					is_jpgharddir = true;
				}

			}

			System.out.println("is_model_show=" + is_model_show + "  is_manufacturer_show=" + is_manufacturer_show
					+ " is_artist_show =" + is_artist_show + "  is_copyright_show=" + is_copyright_show
					+ " is_description_show=" + is_description_show + " is_comment_show=" + is_comment_show);

			// 只要有一个是 true 才能执行
			if (is_comment_show || is_manufacturer_show || is_artist_show || is_copyright_show || is_description_show
					|| is_comment_show) {

				System.out.println("将执行程序生成 携带JPG属性显示在图片中的图片集合  ");

			} else {

				System.out.println(" 输入参数列表必须要有一个参数才能执行 否则没有意义 ");
				return false;

			}

			if (is_jpgharddir) {
				outJpgFile = new File(curDirFile.getAbsolutePath() + File.separator + "jpg_tag_dir");

			} else {
				outJpgFile = new File(curDirFile.getAbsolutePath() + File.separator + "jpg_tag_" + getTimeStamp());

			}

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index + "    // 把当前的 jpg 和 png 文件转为一个 PDF文件  (不操作 孙文件 孙文件夹 )  \n"
					+ Cur_Bat_Name + "  #_" + rule_index
					+ "  model_show  ### 把当前的 jpg 和 png  并在图片显示 model属性  放到 jpg_tag_timestamp目录   \n" + Cur_Bat_Name
					+ "  #_" + rule_index + "  manufacturer_show  ### 把当前的 jpg 和 png   放到 jpg_tag_timestamp目录   \n"
					+ Cur_Bat_Name + "  #_" + rule_index
					+ "  artist_show  ### 把当前的 jpg 和 png  放到 jpg_tag_timestamp目录   \n" + Cur_Bat_Name + "  #_"
					+ rule_index + "  copyright_show  ### 把当前的 jpg 和 png   放到 jpg_tag_timestamp目录   \n" + Cur_Bat_Name
					+ "  #_" + rule_index + "  description_show  ### 把当前的 jpg 和 png  放到 jpg_tag_timestamp目录  \n"
					+ Cur_Bat_Name + "  #_" + rule_index
					+ "  comment_show  ### 把当前的 jpg 和 png   放到 jpg_tag_timestamp目录   \n" + Cur_Bat_Name + "  #_"
					+ rule_index
					+ "  copyright_show  harddir_true  ### 把当前的 jpg 和 png   放到 固定的 jpg_tag_dir目录 方便批处理   \n"
					+ Cur_Bat_Name + "  #_" + rule_index + "  copyright_show  harddir_true && cd ./jpg_tag_dir && "
					+ Cur_Bat_Name + " #_32 " + "  #### 对 jpg_yan_land 把 md写入图片并生成 pdf文件" + ""
//			zrule_apply_G2.bat  #_46  copyright_show  harddir_true
			;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
// TODO Auto-generated method stub
			ArrayList<File> jpgFileList = getAllSubFile(curDirFile, ".jpg");

			if (jpgFileList.size() == 0) {
				System.out.println(" 当前目录 curDirFile=[" + curDirFile.getAbsolutePath() + "] 内没有 jpg文件!!  请检查后再次执行!! ");
				return null;
			}

			for (int i = 0; i < jpgFileList.size(); i++) {
//		Jpg_Exif
				File jpgFile = jpgFileList.get(i);
				String jpgName = jpgFile.getName();
				JPGExifInfo mStockExifJpg = new JPGExifInfo(jpgFile);
				StringBuilder rawLineSB = new StringBuilder();
				if (is_model_show) {
					if (mStockExifJpg.mImageModel_Utf8 != null) {
						rawLineSB.append("model:" + mStockExifJpg.mImageModel_Utf8 + "\n");
					}

				}

				if (is_manufacturer_show) {
					if (mStockExifJpg.mImageMake_Utf8 != null) {
						rawLineSB.append("manufacturer:" + mStockExifJpg.mImageMake_Utf8 + "\n");
					}

				}

				if (is_artist_show) {
					if (mStockExifJpg.mImageArtist_Utf8 != null) {
						rawLineSB.append("artist:" + mStockExifJpg.mImageArtist_Utf8 + "\n");
					}

				}

				if (is_copyright_show) {
					if (mStockExifJpg.mImageCopyright_Utf8 != null) {
						rawLineSB.append("copyright:" + mStockExifJpg.mImageCopyright_Utf8 + "\n");
					}

				}

				if (is_description_show) {
					if (mStockExifJpg.mImageDescription_Utf8 != null) {
						rawLineSB.append("description:" + mStockExifJpg.mImageDescription_Utf8 + "\n");
					}

				}

				if (is_comment_show) {
					if (mStockExifJpg.mPhotoUserComment_Utf8 != null) {
						rawLineSB.append("comment:" + mStockExifJpg.mPhotoUserComment_Utf8 + "\n");
					}

				}

				File srcJpgFile = jpgFile;
				File targetJpg = new File(outJpgFile.getAbsolutePath() + File.separator + jpgName);
				String drawLine = rawLineSB.toString();

				System.out.println("File[" + i + "] srcJpgFile=" + srcJpgFile.getAbsolutePath() + "  targetJpg="
						+ targetJpg.getAbsolutePath() + " drawLine=" + drawLine);
				drawTagJpg(srcJpgFile, targetJpg, drawLine);

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}

		void drawTagJpg(File srcJpgFile, File dstJpgFile, String drawText) {

			if (!dstJpgFile.getParentFile().exists()) {
				dstJpgFile.getParentFile().mkdirs();
			}

			fileCopy(srcJpgFile, dstJpgFile);
			File mCurFile = srcJpgFile;

			ImageIcon imageIcon = new ImageIcon(dstJpgFile.getAbsolutePath());

			BufferedImage bi = getBufferedImage(dstJpgFile);
			int heigh = bi.getHeight();
			int width = bi.getWidth();
			int jpg_width = width;
			int jpg_hight = heigh;

//				BufferedImage bi = new BufferedImage(width, heigh, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150
			// 得到它的绘制环境(这张图片的笔)
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
//				g2.fillRect(0, 0, jpg_width, jpg_width);// 填充一个矩形 左上角坐标(0,0),宽500,高500;填充整张图片
			g2.setColor(new Color(0, 0, 0));// 设置颜色
//				g2.fillRect(0, 0, width, heigh);// 填充整张图片(其实就是设置背景颜色)
			int frontSize = 16;
			int centerx = jpg_width / 2;
			int centery = jpg_hight / 2;

			int centery_20 = jpg_hight / 20;
			int centerx_20 = (jpg_width / 20) * 19;

			Font f = new Font("宋体", Font.BOLD, frontSize);
			g2.setFont(f); // 设置字体:字体、字号、大小
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bounds = f.getStringBounds(drawText + "", context);

			g2.drawString(drawText + "", (float) (jpg_width - 2 * bounds.getCenterX() - 30),
					(float) (jpg_hight - 2 * bounds.getCenterY() - 30)); // 向图片上写字符串

//				g2.drawString(drawText + "", (float) (centerx - bounds.getCenterX()),	(float) (centery - bounds.getCenterY())); // 向图片上写字符串

			try {
//					dstJpgFile.createNewFile();
				ImageIO.write(bi, "jpg", new FileOutputStream(dstJpgFile));// 保存图片 JPEG表示保存格式
				System.out.println("创建文件[" + dstJpgFile.getName() + "]  = " + dstJpgFile.getAbsolutePath() + "成功");
			} catch (Exception e) {
				System.out.println("复制图片格式出现异常！");
			}

		}

		public BufferedImage getBufferedImage(File file) {
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

		public BufferedImage resize(Image mImage, int w, int h) {
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

	}

	class JPGExifInfo {
		File rawFile;

		public String mImageModel_Utf8; // 股票名称
		public String mImageMake_Utf8; // ts代码
		public String mImageArtist_Utf8; // 股票名称
		public String mImageCopyright_Utf8; // ts代码
		public String mImageDescription_Utf8; // 股票名称
		public String mPhotoUserComment_Utf8; // ts代码

		JPGExifInfo(File mFile) {
			rawFile = mFile;
			initTagProp(rawFile);
		}

		void initTagProp(File jpgFile) {
			String fileName = jpgFile.getAbsolutePath().toLowerCase().trim();
			if (!fileName.endsWith(".jpg")) {
				return;
			}
			initExifInfo(jpgFile);

			System.out.println("mImageModel_Utf8=" + mImageModel_Utf8 + "  mImageMake_Utf8=" + mImageMake_Utf8
					+ "  mImageArtist_Utf8=" + mImageArtist_Utf8 + "  mImageCopyright_Utf8=" + mImageCopyright_Utf8
					+ "  mImageDescription_Utf8=" + mImageDescription_Utf8 + " mPhotoUserComment_Utf8="
					+ mPhotoUserComment_Utf8);

		}

		void initExifInfo(File file) {

			mImageDescription_Utf8 = null;
			mImageMake_Utf8 = null;
			mImageModel_Utf8 = null;
			mImageArtist_Utf8 = null;
			mImageCopyright_Utf8 = null;
			mPhotoUserComment_Utf8 = null;

			int angel = 0;
			Metadata metadata;

			try {
				metadata = JpegMetadataReader.readMetadata(file);
				metadata.getDirectories();

				// zukgit_directory [Exif IFD0] - Orientation = Right side, top (Rotate 90 CW)
				for (Directory directory : metadata.getDirectories()) {
					for (Tag tag : directory.getTags()) {
						// 格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
//						System.out.format("zukgit_directory  [%s] - %s = %s\n", directory.getName(), tag.getTagName(),tag.getDescription());

						if ("Exif IFD0".equals(directory.getName())) {

							String mImageDescription = directory.getString(ExifIFD0Directory.TAG_IMAGE_DESCRIPTION);
							if (mImageDescription != null)
								mImageDescription_Utf8 = new String(mImageDescription.getBytes(), "UTF-8");

							String mImageMake = directory.getString(ExifIFD0Directory.TAG_MAKE);
							if (mImageMake != null)
								mImageMake_Utf8 = new String(mImageMake.getBytes(), "UTF-8");

							String mImageModel = directory.getString(ExifIFD0Directory.TAG_MODEL);
							if (mImageModel != null)
								mImageModel_Utf8 = new String(mImageModel.getBytes(), "UTF-8");

							String mImageArtist = directory.getString(ExifIFD0Directory.TAG_ARTIST);
							if (mImageArtist != null)
								mImageArtist_Utf8 = new String(mImageArtist.getBytes(), "UTF-8");

							String mImageCopyright = directory.getString(ExifIFD0Directory.TAG_COPYRIGHT);
							if (mImageCopyright != null)
								mImageCopyright_Utf8 = new String(mImageCopyright.getBytes(), "UTF-8");

//							System.out.println("XXmImageDescription=["+mImageDescription+"]  Utf8["+mImageDescription_Utf8+"]");
//							System.out.println("XXmImageMake=["+mImageMake+"]  Utf8["+mImageMake_Utf8+"]");
//							System.out.println("XXmImageModel=["+mImageModel+"]  Utf8["+mImageModel_Utf8+"]");
//							System.out.println("XXmImageArtist=["+mImageArtist+"]  Utf8["+mImageArtist_Utf8+"]");
//							System.out.println("XXmImageCopyright=["+mImageCopyright+"]  Utf8["+mImageCopyright_Utf8+"]");

						}

						if ("Exif SubIFD".equals(directory.getName())) {

							if ("User Comment".equals(tag.getTagName())) {
								String mPhotoUserComment = tag.getDescription();
//						System.out.println("AZ_User_Comment=["+tag.getDescription()+"]");
								if (mPhotoUserComment != null)
									mPhotoUserComment_Utf8 = new String(mPhotoUserComment.getBytes(), "utf-8");
//						System.out.println("AZXXmPhotoUserComment=["+mPhotoUserComment+"]   mPhotoUserComment_Utf8=["+mPhotoUserComment_Utf8+"]" );

							}

						}
					}

				}

			} catch (JpegProcessingException e) {
				e.printStackTrace();
				System.out.println("JpegProcessingException  异常事件发生 ");
			} catch (IOException e) {
				System.out.println("IOException  异常事件发生 ");
				e.printStackTrace();
			}

//			String mImageArtist_CategoryStr= null;
//			String mImageCopyright_VideoMD = null;
//		    String mImageDescription_SelfDesc = null;
//
//
//		String mImageMake_Utf8 = null;     // 待定
//		String mImageModel_Utf8 = null;     // 待定
//		String mPhotoUserComment_Utf8 = null;   // 待定

			if (mImageArtist_Utf8 != null) {
				System.out.println(" mImageArtist_Utf8 = " + mImageArtist_Utf8);

			} else {
				System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageArtist_CategoryStr !! ");
			}

			if (mImageDescription_Utf8 != null) {
				System.out.println(" mImageDescription_Utf8 = " + mImageArtist_Utf8);

			} else {
				System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageDescription_SelfDesc !! ");
			}

			if (mImageCopyright_Utf8 != null) {

				System.out.println(" mImageCopyright_Utf8 = " + mImageCopyright_Utf8);

			} else {
				System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageCopyright_VideoMD !! ");
			}

			if (mImageMake_Utf8 != null) {
				System.out.println(" mImageMake_Utf8 = " + mImageMake_Utf8);
			} else {
				System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageMake_Utf8 !! ");
			}

			if (mImageModel_Utf8 != null) {
				System.out.println(" mImageModel_Utf8 = " + mImageModel_Utf8);
			} else {
				System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageModel_Utf8 !! ");
			}

			if (mPhotoUserComment_Utf8 != null) {
				System.out.println(" mPhotoUserComment_Utf8 = " + mPhotoUserComment_Utf8);
			} else {
				System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mPhotoUserComment_Utf8 !! ");
			}

		}

	}

	class Jpg_Stock_Port_To_MD_Rule_45 extends Basic_Rule {

		ArrayList<File> jpgFileList; // 当前目录下的 jpg 文件
		ArrayList<StockExifJpg> jpgExifList; // 当前从 jpg读取信息后生成的原始数据

		boolean is_stock; // 考研 类型

		Jpg_Stock_Port_To_MD_Rule_45() {
			super("#", 45, 4); //
			jpgFileList = new ArrayList<File>();
			jpgExifList = new ArrayList<StockExifJpg>();

		}

		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		String simpleDesc() {

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  stock_true  ###   生成 股票吐槽相关 的.md 读取当前文件夹下的jpg文件的exif信息 生成 这些 jpg_stock_port 中的 创建日期 初始资金 股票列表 用户评论信息    ";

		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// mdname_true // kaoyan_true gaokao_true

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if ("stock_true".equals(paramItem_lower_trim)) {
					is_stock = true;
				}

			}

			if (!is_stock) {
				System.out.println("当前输入的参数 没有包含 stock_true  参数 请检查! ");
				return false;
			}

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
// TODO Auto-generated method stub
			jpgFileList = getAllSubFile(curDirFile, ".jpg");

			if (jpgFileList.size() == 0) {
				System.out.println(" 当前目录 curDirFile=[" + curDirFile.getAbsolutePath() + "] 内没有 jpg文件!!  请检查后再次执行!! ");
				return null;
			}

			for (int i = 0; i < jpgFileList.size(); i++) {
//		Jpg_Exif
				File jpgFile = jpgFileList.get(i);
				StockExifJpg mStockExifJpg = new StockExifJpg(jpgFile);

				if (mStockExifJpg.isImageEnable) {
					jpgExifList.add(mStockExifJpg);
				}

			}

			if (jpgExifList.size() == 0) {

				System.out.println(" 当前目录 curDirFile=[" + curDirFile.getAbsolutePath() + "]  存在[" + jpgFileList.size()
						+ "]个jpg " + " 但无法正常读取到 stock_ exif 信息  请检查后再次执行!! ");

				return null;
			}

			jpgExifList.sort(new Comparator<StockExifJpg>() {
				@Override
				public int compare(StockExifJpg o1, StockExifJpg o2) {
					return o2.createDateStr.trim().compareTo(o1.createDateStr.trim());
				}

			});

			ArrayList<String> mMdContentList = BuildStockMDContent(jpgExifList);

// 对 MD文件 进行分隔处理
			ArrayList<String> segerate_MdContentList = new ArrayList<String>();

//			---
//			layout: post
//			title: 年月里【First When See When Listen When Happen When Do】
//			category: 生活
//			tags: Life
//			keywords: 生活 感想
//			typora-root-url: ..\..\
//			typora-copy-images-to: ..\..\public\zimage
//			---
//
//
//			## 简介
//			 * TOC
//			 {:toc}

			segerate_MdContentList.add("---");
			if (is_stock) {
				segerate_MdContentList.add("layout: post");
				segerate_MdContentList.add("title: 股票吐槽汇");
				segerate_MdContentList.add("category: 生活");
				segerate_MdContentList.add("tags: Life");
				segerate_MdContentList.add("keywords: 生活 股票");
				segerate_MdContentList.add("typora-root-url: ..\\..\\");
				segerate_MdContentList.add("typora-copy-images-to: ..\\..\\public\\zimage\\jpg_stock_port");

			}
			segerate_MdContentList.add("---");
			segerate_MdContentList.add("\n");

			segerate_MdContentList.add("## 简介_UserBean(" + jpgExifList.size() + ")");
			segerate_MdContentList.add(" * TOC");
			segerate_MdContentList.add(" {:toc}");
			segerate_MdContentList.add("\n");

			for (int i = 0; i < mMdContentList.size(); i++) {
				String contentLine = mMdContentList.get(i);
				segerate_MdContentList.add("\n" + contentLine);

			}

			writeContentToFile(G2_Temp_Text_File, segerate_MdContentList);
			NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);

		}

		ArrayList<String> BuildStockMDContent(ArrayList<StockExifJpg> jpgExifList) {
			ArrayList<String> stockMDList = new ArrayList<String>();

			// 1. 首先对 jpg 进行 改名

			// 读取 每一个 jpgStockExif 生成 MD 文件

			for (int i = 0; i < jpgExifList.size(); i++) {
				StockExifJpg jpgExifitem = jpgExifList.get(i);
//				System.out.println("jpg_exif[" + i + "] = " + jpgExifitem.toString());
				jpgExifitem.renameOperation();

				ArrayList<String> exifMDList = jpgExifitem.buildStockContent();

				if (exifMDList != null && exifMDList.size() > 0) {
					stockMDList.addAll(exifMDList);
				}

			}

			return stockMDList;

		}

		class StockExifJpg { // 在 JPG 中 读取到的 exif 相关的股票信息

			@Override
			public String toString() {
				return "StockExifJpg [jpgFile=" + jpgFile + ", createDateStr=" + createDateStr + ", initMoneyDouble="
						+ initMoneyDouble + ", stockMatchRawList=" + stockMatchRawList + ", isImageEnable="
						+ isImageEnable + ", rawExifStr=" + rawExifStr + ", userCommentStr=" + userCommentStr
						+ ", stockItemList=" + stockItemList + "]";
			}

			File jpgFile; // 对应的 jpg 文件

			String image_url_prefix; // 初始资金
			File reNameFile; // 重命名后的那个文件
			String createDateStr; // 创建日期
			String initMoneyDouble; // 初始资金

			ArrayList<String> stockMatchRawList; // 【万科A:000002:20:10000】 这样的字符串的集合

			boolean isImageEnable; // 图片是否 符合 exif 条件
			String rawExifStr; // 从文件读取到的 exif 信息的 集合 组成一句话

			String userCommentStr; // 用户评论字符串 把初始资金 创建日期 股票列表都去除后 剩下的
			ArrayList<StockItemExifInfo> stockItemList; // 从 exif 中 读取到的 股票信息

//			### 2021.12.12_20,000,000
//
//			#### biyadi_20_20000
//
//			#### wankeA_21.22_30000
//			<img src="xxxx"/>
//
//
//			```
//			尼玛 在8.21 买入 在8.20 卖出不就好了  记录 一笔 comment
//
//			```

			public ArrayList<String> buildStockContent() {
				ArrayList<String> stockMDList = new ArrayList<String>();
				DecimalFormat decimalFormat_A = new DecimalFormat(",#00");
				DecimalFormat decimalFormat_B = new DecimalFormat("#00.00");
				// 创建 自身的 MD文件的 内容

				String reNameFileName = reNameFile.getName();

				stockMDList.add(
						"### " + createDateStr + "_" + decimalFormat_A.format(Double.parseDouble(initMoneyDouble)));

				for (int i = 0; i < stockItemList.size(); i++) {

					StockItemExifInfo stockExif = stockItemList.get(i);

					stockMDList.add("#### " + stockExif.stockName + "_"
							+ (decimalFormat_B.format(stockExif.costPriceForOne)) + "_" + stockExif.stockKeepCount);

				}

				stockMDList.add("```");
				stockMDList.add(userCommentStr);
				stockMDList.add("```");

				stockMDList.add(
						"<img src=\"" + (image_url_prefix + reNameFileName) + "\"  height=\"33%\" width=\"33%\"  >");

				stockMDList.add("");

				stockMDList.add("");
				return stockMDList;

			}

			public void renameOperation() {

				String originFileName = jpgFile.getName();
				String parentPath = jpgFile.getParentFile().getAbsolutePath();
				String dynamicFileName = getMatchFileName();

				if (dynamicFileName != null && !dynamicFileName.equals(originFileName)) {

					if (tryReName(jpgFile, dynamicFileName)) {

						System.out.println(
								"当前 jpgFile 文件名[" + originFileName + "]已经 改为 动态计算的名字[" + dynamicFileName + "]  改名成功");
						reNameFile = new File(parentPath + File.separator + dynamicFileName);
					} else {

						System.out.println(
								"当前 jpgFile 文件名[" + originFileName + "] 改为 动态计算的名字[" + dynamicFileName + "] 改名失败 ");
						reNameFile = jpgFile;
					}

				} else {

					System.out.println("当前 jpgFile 文件名已经是 动态计算的结果不需要改名");
					reNameFile = jpgFile;
				}

			}

			// 读取 exif 后 匹配到的
			public String getMatchFileName() {
				StringBuilder sb = new StringBuilder();

//				2021.11.11_福田汽车-21.21-2131_格力电器-31.41-3141_10000000.jpg

				if (stockItemList == null) {
					return jpgFile.getName();
				}
				DecimalFormat decimalFormat_A = new DecimalFormat(",#00");
				DecimalFormat decimalFormat_B = new DecimalFormat("#00.00");

				sb.append(createDateStr);
				sb.append("_");

				for (int i = 0; i < stockItemList.size(); i++) {
					StockItemExifInfo stockItem = stockItemList.get(i);
					String stockName = stockItem.stockName;
					sb.append(stockName);
					sb.append("-");
					sb.append(decimalFormat_B.format(stockItem.costPriceForOne));
					sb.append("-");
					sb.append(stockItem.stockKeepCount);
					sb.append("_");
				}
				sb.append(initMoneyDouble);
				sb.append(".jpg");
				return sb.toString();

			}

			StockExifJpg(File rawFile) {
				jpgFile = rawFile;
				isImageEnable = true;
				if (!jpgFile.exists() || !jpgFile.getName().toLowerCase().endsWith(".jpg")) {
					isImageEnable = false;
					return;
				}

				rawExifStr = getAllExifStr(jpgFile);
				image_url_prefix = "/public/zimage/jpg_stock_port/";
				if (rawExifStr == null || "".equals(rawExifStr)) {
					System.out.println("jpg_stock_port  imageFile[" + jpgFile.getAbsolutePath() + "] 无法读取到 exif信息");
					isImageEnable = false;
					return;
				}

				String initMoneyStr = calculStringMiddleForOne(rawExifStr, "【初始资金:", "】");
				String createDateStrTemp = calculStringMiddleForOne(rawExifStr, "【创建日期:", "】");

				print(" 【createDateStrTemp [" + createDateStrTemp + "]】");
				if (initMoneyStr == null || "".equals(initMoneyStr) || createDateStrTemp == null
						|| "".equals(createDateStrTemp)) {
					System.out.println("无法读取到 初始资金 创建日期 exif 信息 ");
					isImageEnable = false;
					return;
				}

				createDateStr = createDateStrTemp;
				if (!isNumeric(initMoneyStr)) {
					System.out.println("无法读取到 初始资金 exif 信息 ");
					isImageEnable = false;
					return;
				}

				initMoneyDouble = initMoneyStr;

				String stockOnlyStr = rawExifStr.replace("【初始资金:" + initMoneyStr + "】", "");
				stockOnlyStr = stockOnlyStr.replace("【创建日期:" + createDateStr + "】", "");
				// 【福田汽车:600166:3.5:100000】
				ArrayList<String> matchStockStrList = calculExifStockTagList(stockOnlyStr);

				if (matchStockStrList == null && matchStockStrList.size() <= 0) {

					System.out.println("无法读取到 股票信息 如【福田汽车:600166:3.5:100000】   exif 信息 ");
					isImageEnable = false;
					return;
				}

				stockMatchRawList = matchStockStrList;

				userCommentStr = calculUserComment(createDateStr, initMoneyStr, stockMatchRawList, rawExifStr);

				print("userCommentStr = [" + userCommentStr + "]");

				// 对 stockItemList 进行初始化
				stockItemList = new ArrayList<StockItemExifInfo>();

				// 对 【福田汽车:600166:3.5:100000】 这样的数据 解析
				for (int i = 0; i < stockMatchRawList.size(); i++) {
					String oneStockStr = stockMatchRawList.get(i);
					String[] stockArrTag = oneStockStr.split(":");
					if (stockArrTag == null || stockArrTag.length != 4) {
						continue;
					}

					String tsName = stockArrTag[0];
					String tsCode = stockArrTag[1];
					String buyPriceForOne = stockArrTag[2];
					String buyCount = stockArrTag[3];

					StockItemExifInfo stockItem = new StockItemExifInfo();
					stockItem.setCostPriceForOne(Double.parseDouble(buyPriceForOne));
					stockItem.setStockKeepCount(Long.parseLong(buyCount));
					stockItem.setStockName(tsName);
					stockItem.setTsCode(tsCode);

					stockItemList.add(stockItem);

				}

			}

			// 去除 日期 去除 初始资金 去除 股票 剩下的 就是 comment
			public String calculUserComment(String dateStr, String moneyStr, ArrayList<String> stockTagList,
					String rawStr) {

				String reusltStr = rawStr.replace(" ", "");

				System.out.println(" 创建日期 = " + "【创建日期:" + dateStr + "】" + " reusltStr=" + reusltStr);
				reusltStr = reusltStr.replace("【创建日期:" + dateStr + "】", "");
				reusltStr = reusltStr.replace("【初始资金:" + moneyStr + "】", "");

				for (int i = 0; i < stockTagList.size(); i++) {
					String stockTagItem = stockTagList.get(i);
					reusltStr = reusltStr.replace(stockTagItem, "");

				}

				reusltStr = reusltStr.replace("【", "");
				reusltStr = reusltStr.replace("】", "");

				return reusltStr;

			}

			public ArrayList<String> calculExifStockTagList(String rawStr) {
				// 【初始资金:100000】【创建日期:2021.11.11】【福田汽车:600166:3.5:100000】
				ArrayList<String> stockMatchList = new ArrayList<String>();

				String[] stockArr = rawStr.split("】");
				for (int i = 0; i < stockArr.length; i++) {
					String tagItem = stockArr[i].trim();
					System.out.println("stockArr[" + i + "] = " + stockArr[i]);
					if (tagItem.startsWith("【初始资金:") || tagItem.startsWith("【创建日期:")) {
						continue;
					}
					String clearBlank = tagItem.replace("【", "").replace("】", "");
					if (!clearBlank.contains(":")) {
						System.out.println("当前 ImageUserBean 不包含 分隔符:引号");
						continue;
					}
					String[] oneStockArr = clearBlank.split(":");
					if (oneStockArr == null || oneStockArr.length != 4) {
						System.out.println("当前 ImageStockBean 【名称:代码:买入价格:买入数量】 格式不对请检查");
						continue;
					}

					for (int j = 0; j < oneStockArr.length; j++) {
						System.out.println("oneStockArr[" + j + "] = " + oneStockArr[j]);
					}

					stockMatchList.add(clearBlank);

				}

				return stockMatchList;

			}

			public void print(String log) {

				System.out.println(log);
			}

			public String calculStringMiddleForOne(String rawStr, String preStr, String endStr) {
				String matchStr = null;
				String tempStrA = rawStr.substring(rawStr.indexOf(preStr) + preStr.length());
				System.out.println(
						" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = " + rawStr + "   tempStrA=" + tempStrA);
				String tempStrB = tempStrA.substring(0, tempStrA.indexOf(endStr));
				System.out.println(
						" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = " + rawStr + "   tempStrB=" + tempStrB);
				return tempStrB;

			}

			public String getAllExifStr(File imageFile) {
				String mMatchStr = null;

				String mImageDescription_Utf8 = null;
				String mImageMake_Utf8 = null;
				String mImageModel_Utf8 = null;
				String mImageArtist_Utf8 = null;
				String mImageCopyright_Utf8 = null;
				String mPhotoUserComment_Utf8 = null;
				int angel = 0;
				Metadata metadata;

				try {
					metadata = JpegMetadataReader.readMetadata(imageFile);
					metadata.getDirectories();

					// zukgit_directory [Exif IFD0] - Orientation = Right side, top (Rotate 90 CW)
					for (Directory directory : metadata.getDirectories()) {
						for (Tag tag : directory.getTags()) {
							// 格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
//									System.out.format("zukgit_directory  [%s] - %s = %s\n", directory.getName(), tag.getTagName(),tag.getDescription());

							if ("Exif IFD0".equals(directory.getName())) {

								String mImageDescription = directory.getString(ExifIFD0Directory.TAG_IMAGE_DESCRIPTION);
								if (mImageDescription != null)
									mImageDescription_Utf8 = new String(mImageDescription.getBytes(), "UTF-8");

								String mImageMake = directory.getString(ExifIFD0Directory.TAG_MAKE);
								if (mImageMake != null)
									mImageMake_Utf8 = new String(mImageMake.getBytes(), "UTF-8");

								String mImageModel = directory.getString(ExifIFD0Directory.TAG_MODEL);
								if (mImageModel != null)
									mImageModel_Utf8 = new String(mImageModel.getBytes(), "UTF-8");

								String mImageArtist = directory.getString(ExifIFD0Directory.TAG_ARTIST);
								if (mImageArtist != null)
									mImageArtist_Utf8 = new String(mImageArtist.getBytes(), "UTF-8");

								String mImageCopyright = directory.getString(ExifIFD0Directory.TAG_COPYRIGHT);
								if (mImageCopyright != null)
									mImageCopyright_Utf8 = new String(mImageCopyright.getBytes(), "UTF-8");

//										System.out.println("XXmImageDescription=["+mImageDescription+"]  Utf8["+mImageDescription_Utf8+"]");
//										System.out.println("XXmImageMake=["+mImageMake+"]  Utf8["+mImageMake_Utf8+"]");
//										System.out.println("XXmImageModel=["+mImageModel+"]  Utf8["+mImageModel_Utf8+"]");
//										System.out.println("XXmImageArtist=["+mImageArtist+"]  Utf8["+mImageArtist_Utf8+"]");
//										System.out.println("XXmImageCopyright=["+mImageCopyright+"]  Utf8["+mImageCopyright_Utf8+"]");

							}

							if ("Exif SubIFD".equals(directory.getName())) {

								if ("User Comment".equals(tag.getTagName())) {
									String mPhotoUserComment = tag.getDescription();
//									System.out.println("AZ_User_Comment=["+tag.getDescription()+"]");
									if (mPhotoUserComment != null)
										mPhotoUserComment_Utf8 = new String(mPhotoUserComment.getBytes(), "utf-8");
//									System.out.println("AZXXmPhotoUserComment=["+mPhotoUserComment+"]   mPhotoUserComment_Utf8=["+mPhotoUserComment_Utf8+"]" );

								}

							}
						}

					}

					mMatchStr = getExifInfoAsOneStr(mImageModel_Utf8, mImageMake_Utf8, mImageArtist_Utf8,
							mImageCopyright_Utf8, mImageDescription_Utf8, mPhotoUserComment_Utf8);

				} catch (JpegProcessingException e) {
					e.printStackTrace();
					System.out.println("JpegProcessingException  异常事件发生 ");
				} catch (IOException e) {
					System.out.println("IOException  异常事件发生 ");
					e.printStackTrace();
				}

				if (mMatchStr == null || "".equals(mMatchStr)) {
					System.out.println(" 没有读取到  imageUser 的 exif 信息 ");
					return null;
				}
				System.out.println(" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = " + mMatchStr);
				return mMatchStr;

			}

			public String getExifInfoAsOneStr(String A1, String B2, String C3, String D4, String E5, String F6) {
				StringBuilder sb = new StringBuilder();
				sb.append(A1 != null ? A1.trim() : "");
				sb.append(B2 != null ? B2.trim() : "");
				sb.append(C3 != null ? C3.trim() : "");
				sb.append(D4 != null ? D4.trim() : "");
				sb.append(E5 != null ? E5.trim() : "");
				sb.append(F6 != null ? F6.trim() : "");

				return sb.toString();

			}

		}

		class StockItemExifInfo {

			public String stockName; // 股票名称
			public String tsCode; // ts代码
			public double costPriceForOne; // 成本
			public long stockKeepCount; // 持有数量

			public String getStockName() {
				return stockName;
			}

			public void setStockName(String stockName) {
				this.stockName = stockName;
			}

			public String getTsCode() {
				return tsCode;
			}

			public void setTsCode(String tsCode) {
				this.tsCode = tsCode;
			}

			public double getCostPriceForOne() {
				return costPriceForOne;
			}

			public void setCostPriceForOne(double costPriceForOne) {
				this.costPriceForOne = costPriceForOne;
			}

			public long getStockKeepCount() {
				return stockKeepCount;
			}

			public void setStockKeepCount(long stockKeepCount) {
				this.stockKeepCount = stockKeepCount;
			}

			public double getSrockAllCost() {

				return costPriceForOne * stockKeepCount;
			}

		}

	}

	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
//     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

	class Land_Port_Mp4Rename_Rule_44 extends Basic_Rule {

		Land_Port_Mp4Rename_Rule_44() {
			super("#", 44, 4); //

			mSrcMP4FileList = new ArrayList<File>();

			mLandMP4FileList = new ArrayList<File>();

			mPortMP4FileList = new ArrayList<File>();

		}

		File mSouTuDir; // 当前视频的缩略图文件夹

		ArrayList<File> mSrcMP4FileList; // Shell 目录下原始文件目录
		ArrayList<File> mLandMP4FileList; // Shell/Land_Port_TimeStamp/Land/ 文件夹下的文件
		ArrayList<File> mPortMP4FileList; // Shell/Land_Port_TimeStamp/Land/ 文件夹下的文件

		// 在缩图中的名称对应的 是否是 isport的属性
		Map<String, Boolean> notypeFileName_IsPort_Map_InSuotu;

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// TODO Auto-generated method stub

			File[] listFile = curDirFile.listFiles();
			if (listFile == null || listFile.length == 0) {
				System.out.println("当前执行目录文件为空 无法执行程序 请检查!!  ");
				return false;
			}

			for (int i = 0; i < listFile.length; i++) {
				File curFile = listFile[i];
				String fileName = curFile.getName();
				if (curFile.isDirectory() && fileName.startsWith("SuoTu_MP4_")) {
					mSouTuDir = curFile;

				}
			}

			if (mSouTuDir == null || !mSouTuDir.exists() || mSouTuDir.listFiles() == null
					|| mSouTuDir.listFiles().length == 0) {

				System.out.println("当前缩略图文件夹为空 请检查:  mSouTuDir = " + mSouTuDir);
				System.out.println("Tip: ");
				System.out.println("当前程序需要先执行zmpeg_ffmpeg_G8.bat 9     ## 把当前目录下的 Mp4 生成缩略图");
				System.out.println(" 当前程序依赖 软件  ffmpeg.exe ");
				return false;

			}

			initMapWithSuoTuDir(mSouTuDir);

			return super.initParamsWithInputList(inputParamList);
		}

		void initMapWithSuoTuDir(File suotuDir) {
			notypeFileName_IsPort_Map_InSuotu = new HashMap<String, Boolean>();
			File[] fileList = suotuDir.listFiles();

			for (int i = 0; i < fileList.length; i++) {
				File curFile = fileList[i];
				String type = getFileTypeWithPoint(curFile.getName());
				String fileNameNoPoint = getFileNameNoPoint(curFile.getName());
				if (".jpg".equals(type)) {
					ImageIcon imageIcon = new ImageIcon(curFile.getAbsolutePath());
					int high = imageIcon.getIconHeight();
					int width = imageIcon.getIconWidth();
					boolean isPort = true;
					if (width > high) {
						isPort = false;
					} else {
						isPort = true;
					}

					notypeFileName_IsPort_Map_InSuotu.put(fileNameNoPoint, isPort);

				}

			}

			deleteDirectory(suotuDir.getAbsolutePath());

			System.out.println("notypeFileName_IsPort_Map Size[" + notypeFileName_IsPort_Map_InSuotu.size() + "]");

		}

		boolean isVideoPort_MP4Parser(File videoFile) {
			boolean isport = true;
			try {
				IsoFile isoFile = new IsoFile(new FileInputStream(videoFile).getChannel());

				MovieBox movieBox = org.mp4parser.tools.Path.getPath(isoFile, "moov");
// 可以打印这个 movieBox  toString 看看里面有啥
				List<org.mp4parser.Box> boxes = movieBox.getBoxes();
// 宽高时长获取
				long duration = movieBox.getMovieHeaderBox().getDuration();
				int width = 0;
				int height = 0;
				for (org.mp4parser.Box box : boxes) {
					if (box instanceof TrackBox) {
						TrackBox tBbx = (TrackBox) box;
						width = (int) tBbx.getTrackHeaderBox().getWidth();
						height = (int) tBbx.getTrackHeaderBox().getHeight();

						if (width > height) {

							isport = false;
						}

						System.out.println(
								"filename[" + videoFile.getName() + "]" + "   width[" + width + "]" + "  hight["
										+ height + "]" + "   isport[" + isport + "]" + "  duration[" + duration + "]");

						break;
					}
				}

				isoFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("异常发生 e=" + e);
			}

			return isport;

		}

		boolean isVideoPort_MultimediaInfo(File mp4File) {
			boolean isport = true;

			Encoder encoder = new Encoder();

			try {
				MultimediaInfo m = encoder.getInfo(mp4File);

				VideoSize size = m.getVideo().getSize();

				long duration = m.getDuration();

				int lastsecond = (int) duration / 1000 - 1;

				int height = size.getHeight();
				int width = size.getWidth();
				if (height < width) {
					isport = false;
				}
				System.out.println("filename[" + mp4File.getName() + "]" + "   width[" + width + "]" + "    hight["
						+ height + "]" + "   isport[" + isport + "]   duration[" + duration + "]  lastsecond["
						+ lastsecond + "]");

			} catch (Exception e) {
				e.printStackTrace();
			}

			return isport;

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			for (int i = 0; i < curRealFileList.size(); i++) {
				File fileItem = curRealFileList.get(i);
				String fileName = fileItem.getName();
				String fileName_lower = fileName.toLowerCase();

				if (fileName_lower.endsWith(".mp4") || fileName_lower.endsWith(".MP4")) {
					mSrcMP4FileList.add(fileItem);
				}

			}

			for (int i = 0; i < mSrcMP4FileList.size(); i++) {
				File mp4File = mSrcMP4FileList.get(i);
				String mp4NameNoType = getFileNameNoPoint(mp4File.getName());

				if (notypeFileName_IsPort_Map_InSuotu.containsKey(mp4NameNoType)) {
					Boolean isPort = notypeFileName_IsPort_Map_InSuotu.get(mp4NameNoType);
					if (isPort) {
						mPortMP4FileList.add(mp4File);
					} else {
						mLandMP4FileList.add(mp4File);
					}

				} else {

					System.out.println(
							"当前文件 mp4File=" + mp4File.getName() + " 无法在缩略图文件夹 " + mSouTuDir + " 找到对应的缩略图!! 无法判断");

				}

			}

			int allOperationFileCount = mSrcMP4FileList.size() + mPortMP4FileList.size();

			for (int i = 0; i < mPortMP4FileList.size(); i++) {
				File mp4File = mPortMP4FileList.get(i);
				String fileName = mp4File.getName();
				if (fileName.startsWith("Port_")) { // 已经有分类 cintinue
					continue;
				}
				String new_name = "Port_" + fileName;

				System.out.println("Port[" + i + "] AllPort[" + mPortMP4FileList.size() + "]  AllMP4["
						+ allOperationFileCount + "]  OldName[" + fileName + "]  newName[" + new_name + "]");
				tryReName(mp4File, new_name);

			}

			for (int i = 0; i < mLandMP4FileList.size(); i++) {
				File mp4File = mLandMP4FileList.get(i);
				String fileName = mp4File.getName();
				if (fileName.startsWith("Land_")) { // 已经有分类 cintinue
					continue;
				}
				String new_name = "Land_" + fileName;
				System.out.println();
				System.out.println("Land[" + i + "] AllLand[" + mLandMP4FileList.size() + "]  AllMP4["
						+ allOperationFileCount + "]  OldName[" + fileName + "]  newName[" + new_name + "]");

				tryReName(mp4File, new_name);

			}

			System.out.println("" + rule_index + " 对Video 进行 Port_ 和 Land_ 的重命名完成!");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {

			return "\n" + "zmpeg_ffmpeg_G8.bat 9 && " + Cur_Bat_Name + " #_" + rule_index
					+ "   ###  对当前的目录中的MP4文件 先生成动态 缩略图文件夹 获取正确宽高后 以 Port_ 和 Land_前缀来重命名MP4文件名称   ";
		}

	}

	class SqlitTxt_Return_FirstBlankStr_Rule_43 extends Basic_Rule {

		File operationFile; // 不能为空
		String sqlitstr_tag; // 从外输入的 对txt文件进行切割的 标示 不能为空

		SqlitTxt_Return_FirstBlankStr_Rule_43() {
			super("#", 43, 4); //

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub

			String Tag = ReadFileContent(operationFile);
			String[] tagArr = Tag.split(sqlitstr_tag);

			ArrayList<String> matchStrList = new ArrayList<String>();
			matchStrList.add("════════════════════" + "切割Tag=" + sqlitstr_tag + " ════════════════════");
			for (int i = 0; i < tagArr.length; i++) {
				String stritem = tagArr[i];

				// 为了 获取 分隔后的每行, 每行包含一些空格，所以 这里取到每行空格之前的字符串
				String[] blankArr = stritem.split(" ");
				if (blankArr != null) {
					String oneBlankStr = blankArr[0];
					oneBlankStr = oneBlankStr.replace("\"", ""); // 去除引号
					if (oneBlankStr.equals("")) {
						continue;
					}

//					System.out.println("index["+i+"]:oneBlank【"+oneBlankStr+"】");
					matchStrList.add(oneBlankStr);
//					System.out.println("index["+i+"]:oneBlank【"+oneBlankStr+"】:["+tagArr[i]+"]");

				}

			}

			matchStrList.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}

			});

			ArrayList<String> resultStrList = new ArrayList<String>();
			resultStrList.add("输入参数已经结果:  sqlitstr[" + sqlitstr_tag + "]" + "  operationFile["
					+ operationFile.getAbsolutePath() + "]");

			resultStrList.addAll(matchStrList);

			writeContentToFile(G2_Temp_Text_File, resultStrList);
			NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// TODO Auto-generated method stub

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);

				if (paramItem.contains("sqlitstr_")) {
					sqlitstr_tag = paramItem.replace("sqlitstr_", "");
					continue;

				}

				File targetFile = new File(curDirPath + File.separator + paramItem);

				if (targetFile.exists()) {

					operationFile = targetFile;
				}

			}

			if (operationFile == null || !operationFile.exists()) {

				System.out.println("当前 规则:" + rule_index + " 需要输入的文件为空, 请检查输入！！");
				return false;
			}

			if (sqlitstr_tag == null || sqlitstr_tag.equals("")) {

				System.out.println("当前 规则:" + rule_index + " 需要输入切割字符串 sqlitstr_XXX 为空！！, 请检查输入！！");
				return false;
			}

			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		String simpleDesc() {

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  2.txt sqlitstr_href     ### 一个本地操作文件以及一个切割符,返回切割后的每行的第一个空格前的字符串 ";
		}

	}

	class GetHttpCode_Rule_42 extends Basic_Rule {

		boolean html2md_flag; // html2md_true // 把当前的 html 转为 md的操作

		// 必须以 http 开头
		String searchHttpUrl; // 从输入传入的 需要得到 的 http 源码的 网页的地址
		ChromeDriver mChromeDriver;
		String curPositionHtmlCodeStr; // 每次得到的 html代码的值 用于突然用户终止程序时 使用

		GetHttpCode_Rule_42() {
			super("#", 42, 4); //
			html2md_flag = false;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// TODO Auto-generated method stub

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();
				System.out.println("paramItem[" + i + "] = " + paramItem_lower_trim);
				if (paramItem_lower_trim.startsWith("http")) {
					searchHttpUrl = paramItem;
				}

				if (paramItem_lower_trim.startsWith("html2md_true")) {
					html2md_flag = true;
				}

			}

			if (searchHttpUrl == null) {
				System.out.println("当前输入的网页为空  请检查输入!!!");
				return false;
			}

			File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());

			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
			// 驱动位置
			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");

			CUR_CHROME_OPTIONS.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			mChromeDriver = new ChromeDriver(CUR_CHROME_OPTIONS);

			System.out.println("searchHttpUrl=【" + searchHttpUrl + "】  html2md_flag=【" + html2md_flag + "】");

			return super.initParamsWithInputList(inputParamList);
		}

		void registerShutDownLister() {
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						Thread.sleep(200);
						System.out.println("════════ 监听到 Ctr+Z stop进程操作 将执行保存当前页面位置代码的操作 ════════");

						writeContentToFile(G2_Temp_Text_File, curPositionHtmlCodeStr);
						NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());

						System.out.println("获得【" + searchHttpUrl + "】 MainPage HtmlCode 突然终止 部分成功 !! ");
						System.out.println("════════" + "════════");
						// some cleaning up code...

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub

			if (!html2md_flag) { // 确保 html2md 时 不打开 G2_Temp
				registerShutDownLister();
			}

			String httpPageCode = BrowserOperation_WithRootUrl(searchHttpUrl);

			if (httpPageCode != null) {

				if (html2md_flag) {
					System.out.println("开始 进行 Html 转 MD 的文件的操作! ");

					Html_2_MD_Operation(G2_Temp_Text_File);
					System.out.println("执行  Html 转 MD 的文件的操作 成功! ! ");
					return null;
				} else {

					writeContentToFile(G2_Temp_Text_File, httpPageCode);
					NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());
					System.out.println("获得【" + searchHttpUrl + "】 MainPage HtmlCode 成功!! ");

				}
			} else {
				System.out.println("获得【" + searchHttpUrl + "】 MainPage HtmlCode 失败!! ");

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ " https://www.baidu.com   ### 传递一个http路径打开它的html源码(tip:有些页面浏览器另存为的html由于某些html页面) "

					+ "\n" + Cur_Bat_Name + " #_" + rule_index
					+ " https://zhou-yuxin.github.io/articles/2016/%E6%97%A0%E7%BA%BF%E7%BD%91%E7%BB%9CWPA2%E5%8A%A0%E5%AF%86%E7%9A%84%E7%A0%B4%E8%A7%A3/index.html  html2md_true   ### 传递一个http路径打开它的html源码 并解析到 MD文件(以github.io工程的文件夹格式) ";
		}

		// html2md ====================begin==========================

		// 模拟主页
//		String rootUrl = "https://zhou-yuxin.github.io/articles/2016/%E6%97%A0%E7%BA%BF%E7%BD%91%E7%BB%9CWPA2%E5%8A%A0%E5%AF%86%E7%9A%84%E7%A0%B4%E8%A7%A3/";

		String rootUrl = "https://qiankunli.github.io";
		String rootUrl_possible_url1 = "https://qiankunli.github.io";
		// https://qiankunli.github.io/2022/03/20/xxxx资源

		// 当前的主页 https://qiankunli.github.io/2022/03/20/flink.html
		//

		// <p><img src="/public/upload/compute/flink_design.png" alt="" /></p>
		// https://qiankunli.github.io/ public/upload/compute/flink_design.png
		// https://qiankunli.github.io/ 资源的主页
		// <img src="x.jpg"> 中的 x.jpg 对应的 src的 绝对的路径

		HashMap<String, String> srcImageUrl_ZimageUrl_Map; // <img src="x.jpg">【key】 <img src="x.jpg"> 【value】<img
		// src="//../zimage/architect/01_sorttable.jpg">
		HashMap<String, String> srcImageUrl_AbsUrl_Map; // <img src="x.jpg">【key】 https://www.baidu.com/x.jpg【value】
		HashMap<String, String> srcImageUrl_AbsUrl_Possibale1_Map; // <img src="x.jpg">【key】
																	// https://www.baidu.com/x.jpg【value】

		HashMap<String, File> srcImageUrl_LocalFile_Map; // 1.jpg 对应的 本地的 public/zimage/html2md/1.jpg

		ArrayList<String> srcImageUrl_List; // <img src="x.jpg"> 的原始 内容为了最终方便 替换
		ArrayList<String> innderHtmlTag; // 需要去除的 tag

		File curMDShellDir; // 本地的shell执行的路径
		// html_2_md_timestemp
		File html_md_timestamp_dir; // html_2_md_20220818_192012/ 当前目录下 生成的 html_2_md_timestamp
		String html_md_timestamp_dirname; // html_2_md_20220818_192012

		File html_zimage_dir; // html_2_md_20220818_192012/zukgit.github.io/public/zimage/html2md/
		File html_2_mdfile_dir; // html_2_md_20220818_192012/zukgit.github.io/_posts/Technology/html2md/
		// html_2_md_20220818_192012/zukgit.github.io
		// html_2_md_20220818_192012/zukgit.github.io/public/zimage/html2md/
		// 【html_zimage_dir】
		// html_2_md_20220818_192012/zukgit.github.io/public/zimage/html2md/xxxx.jpg
		// html_2_md_20220818_192012/zukgit.github.io/_posts/Technology/html2md/
		// html_2_md_20220818_192012/zukgit.github.io/_posts/Technology/html2md/{title}.md

		String md_title; // 当前html的 title 属性 当做 md 文件的名称
		File md_file; // 目标的 md 文件

		String time_stamp_str; // 日期格式

		// md 文件的头部
		ArrayList<String> mdHeadList;

		void init_http_url() {
			String inputUrl = searchHttpUrl;

			rootUrl = searchHttpUrl;
			rootUrl_possible_url1 = searchHttpUrl;
			if (inputUrl.endsWith(".html")) {
				// /
				rootUrl = inputUrl.substring(0, inputUrl.lastIndexOf("/"));

			}

			// https://qiankunli.github.io
			if (searchHttpUrl.contains("github.io")) {
				rootUrl_possible_url1 = searchHttpUrl.substring(0,
						searchHttpUrl.indexOf("github.io") + "github.io".length());

			}

			System.out.println("searchHttpUrl=【" + searchHttpUrl + "】   rootUrl=【" + rootUrl
					+ "】   rootUrl_possible_url1=" + rootUrl_possible_url1);

		}

		void Html_2_MD_Operation(File httpCodeFile) {
			init_html2md_value();
			init_http_url();
			String httpCodeFileName = time_stamp_str + "_" + httpCodeFile.getName();
			File curShell_TxtFile = new File(curDirFile.getAbsolutePath() + File.separator + httpCodeFileName);
			fileCopy(httpCodeFile, curShell_TxtFile);

			Html_2_MD(curShell_TxtFile);

		}

		void Html_2_MD(File fileItem) {

			curMDShellDir = fileItem.getParentFile();
			System.out.println("curShellDir = " + curMDShellDir);

			initHtml2MD_Dir(curMDShellDir); // 初始化 那些 需要用到的目录

			ArrayList<String> allMD_StrList = new ArrayList<String>();
			ArrayList<String> fixedMD_StrList = new ArrayList<String>();
			ArrayList<String> rawHtml_StrList = ReadFileContentAsList(fileItem);

			// 0. <h1> 转为 # <h6> 转为 ######

			// 1. <p> 转为 空 去除掉
			// 1.1 <img src="1.png"> 转为 相对路径 需要计算 src的绝对路径 所以需要依据参数得到 这里假设有 保留
// 下载到本地 并命名为 	20220818_3142.png
//				<img src="//../zimage/html2md/20220818_3142.png">
			// 1.2 <a> </a> 可保留

			// 2. <pre> 转为 "\n```\n" <pre> 转为 "\n```\n"

			// 3. 最后处理
			// 去除 <body class="p" > 给定参数 body 去除 <body 开头 以及 在它之后的 > 的这段内容
			// <div id="main"> <article id=" <article id= <\ 开头的 以及之后的> 去除

			for (int j = 0; j < rawHtml_StrList.size(); j++) {
				String oneLineStr = rawHtml_StrList.get(j);
				String fixedLineStr = new String(oneLineStr);
				;

				// 0. <h1> 转为 # <h6> 转为 ######
				fixedLineStr = title_to_jing(oneLineStr);
				// 1. <p> operation
				fixedLineStr = clear_p_br_tag(fixedLineStr);

				// 2. <pre> operation
				fixedLineStr = clear_pre_tag(fixedLineStr);

				// 3. 去除 <body class="p" >
				fixedLineStr = clear_inner_tag(fixedLineStr);

				// &lt;==< &gt;==> 的转换
				fixedLineStr = clear_lt_gt_tag(fixedLineStr);

				// amp; 转为 空格
				fixedLineStr = clear_amp_tag(fixedLineStr);

				fixedMD_StrList.add(fixedLineStr);
			}

			// 2022-09-13-
			md_file = new File(html_2_mdfile_dir + File.separator + getYear_Month_Day() + "-" + md_title + ".md");

			initHeadList(md_title); // 初始化 mdHeadList

			// allMD_StrList = mdHeadList + fixedMD_StrList

			System.out.println("srcImageUrl_List.size() = " + srcImageUrl_List.size());

//					HashMap<String,String> srcImageUrl_ZimageUrl_Map;  //<img src="x.jpg">【key】 <img src="x.jpg">    【value】<img src="//../zimage/architect/01_sorttable.jpg">
//					HashMap<String,String> srcImageUrl_AbsUrl_Map;  //<img src="x.jpg">【key】   https://www.baidu.com/x.jpg【value】
//					HashMap<String,File> srcImageUrl_LocalFile_Map;

			for (int j = 0; j < srcImageUrl_List.size(); j++) {
				String imageUrlItem = srcImageUrl_List.get(j);
				String networkAbsUrl = srcImageUrl_AbsUrl_Map.get(imageUrlItem);
				String networkAbsUrl_Possible1 = srcImageUrl_AbsUrl_Map.get(imageUrlItem);
				String publicImage_Src_Url = srcImageUrl_ZimageUrl_Map.get(imageUrlItem);
				File localImgFile = srcImageUrl_LocalFile_Map.get(imageUrlItem);

				System.out.println("image[" + j + "][" + srcImageUrl_List.size() + "]  MD_File="
						+ md_file.getAbsolutePath() + "   showUrl=" + imageUrlItem + "  networkUrl:" + networkAbsUrl
						+ " publicSrc:" + publicImage_Src_Url + "  localFile:" + localImgFile.getAbsolutePath()
						+ " possible_url1=【" + networkAbsUrl_Possible1 + "】");

				if (!downloadImage(localImgFile, networkAbsUrl)) {
					System.out.println("mainUrl:" + networkAbsUrl + "  没有下载成功 尝试使用备用路径 networkAbsUrl_Possible1="
							+ networkAbsUrl_Possible1);

					if (!downloadImage(localImgFile, networkAbsUrl_Possible1)) {
						System.out.println(" FUCK 主路径和配用路径都下载失败!! " + "mainUrl:" + networkAbsUrl
								+ "  没有下载成功 尝试使用备用路径 networkAbsUrl_Possible1=" + networkAbsUrl_Possible1);

					}

				}
			}

			allMD_StrList.addAll(mdHeadList);

			// 去除 占用多行的 注释 <!-- --> 和 <script> 以及 </script>
			ArrayList<String> clearScriptMDList = clearMultiZhuShi_clearMultiScript(fixedMD_StrList);

			// 多行空格转为 一行 空格 使得 整体 看起来紧凑
			ArrayList<String> clear_MultiBlankMDList = clearMultiBlank(clearScriptMDList);
			allMD_StrList.addAll(clear_MultiBlankMDList);

//				writeContentToFile(I9_Temp_Text_File, fixedMD_StrList);
//				NotePadOpenTargetFile(I9_Temp_Text_File.getAbsolutePath());

			writeContentToFile(md_file, allMD_StrList);
			NotePadOpenTargetFile(md_file.getAbsolutePath());

		}

		void init_html2md_value() {
			srcImageUrl_ZimageUrl_Map = new HashMap<String, String>();
			srcImageUrl_AbsUrl_Map = new HashMap<String, String>();
			srcImageUrl_AbsUrl_Possibale1_Map = new HashMap<String, String>();
			srcImageUrl_List = new ArrayList<String>();
			srcImageUrl_LocalFile_Map = new HashMap<String, File>();
			mdHeadList = new ArrayList<String>();
			time_stamp_str = getTimeStampyyyyMMdd_HHmmss();
			init_innerTag_List();

		}

		void init_innerTag_List() {
			innderHtmlTag = new ArrayList<String>();
			innderHtmlTag.add("html");
			innderHtmlTag.add("head");
//			innderHtmlTag.add("title");
			innderHtmlTag.add("link");
			innderHtmlTag.add("body");
			innderHtmlTag.add("header");
			innderHtmlTag.add("meta");
			innderHtmlTag.add("div");
			innderHtmlTag.add("article");

			innderHtmlTag.add("i");
			innderHtmlTag.add("!doctype html");
			innderHtmlTag.add("br");
			innderHtmlTag.add("li");
			innderHtmlTag.add("ul");
			innderHtmlTag.add("table");

			innderHtmlTag.add("tr");

			innderHtmlTag.add("td");
			innderHtmlTag.add("button");
			innderHtmlTag.add("ol");

			innderHtmlTag.add("hr");
			innderHtmlTag.add("thead");

			innderHtmlTag.add("th");

			innderHtmlTag.add("footer");
			innderHtmlTag.add("tbody");
			innderHtmlTag.add("code");
			innderHtmlTag.add("span");
//			innderHtmlTag.add("!--");
//			innderHtmlTag.add("script");

		}

//		---
//		layout: post
//		title: {title}
//		category: html2md
//		tags: html2md
//		keywords:
//		typora-root-url:..\..\..\
//		typora-copy-images-to:..\..\..\public\zimage\html2md
//		---

		void initHeadList(String title) {

			mdHeadList.add("---");
			mdHeadList.add("layout: post");
			if (title == null || title.startsWith("unknow")) {
				mdHeadList.add("title: " + "unknow");

			} else {
				mdHeadList.add("title: " + title);
			}
			mdHeadList.add("category: html2md");
			mdHeadList.add("tags: html2md");
			mdHeadList.add("keywords: ");
			mdHeadList.add("typora-root-url: ..\\..\\..\\");
			mdHeadList.add("typora-copy-images-to: ..\\..\\..\\public\\zimage\\html2md");
			mdHeadList.add("---");

			mdHeadList.add("\n");

			mdHeadList.add("## 简介");
			mdHeadList.add(" * TOC");
			mdHeadList.add(" {:toc}");

			mdHeadList.add("\n");
			mdHeadList.add("\n");

		}

		void initHtml2MD_Dir(File shellDirFile) {
			String shellPath = shellDirFile.getAbsolutePath();

			html_md_timestamp_dirname = "html_2_md" + "_" + time_stamp_str;
			md_title = "unknow" + time_stamp_str;

			html_md_timestamp_dir = new File(shellPath + File.separator + html_md_timestamp_dirname);
			html_zimage_dir = new File(
					html_md_timestamp_dir.getAbsolutePath() + File.separator + "zukgit.github.io" + File.separator
							+ "public" + File.separator + "zimage" + File.separator + "html2md" + File.separator);
			html_2_mdfile_dir = new File(
					html_md_timestamp_dir.getAbsolutePath() + File.separator + "zukgit.github.io" + File.separator
							+ "_posts" + File.separator + "Technology" + File.separator + "html2md" + File.separator);

		}

		ArrayList<String> clearMultiBlank(ArrayList<String> rawMDList) {
			ArrayList<String> clearMultiBlankList = new ArrayList<String>();

			boolean is_preline_blank = false;
			for (int i = 0; i < rawMDList.size(); i++) {
				String oneLine = rawMDList.get(i);
				String clear_blank_str = oneLine.replace("    ", "").replace(" ", "").trim();

				if ("".equals(clear_blank_str)) {
					if (!is_preline_blank) {
						clearMultiBlankList.add("");
					}
					is_preline_blank = true;

					continue;
				} else {
					is_preline_blank = false;
					// 缩进 必须是 2 6 之间 不能是 4 否则会影响后面的 空格
					if (oneLine.startsWith("    ") && !oneLine.startsWith("     ")) {
						oneLine = oneLine.replace("    ", "  ");

					}
					clearMultiBlankList.add(oneLine);
				}

			}

			return clearMultiBlankList;
		}

		ArrayList<String> clearMultiZhuShi_clearMultiScript(ArrayList<String> rawMDList) {
			ArrayList<String> fixedMDList = new ArrayList<String>();

			boolean isEmptyLine_ZhuShi = false; // 是否包含在多行注释内 是否包含在 多行 script 内

			boolean isEmptyLine_Script = false; // 是否包含在多行注释内 是否包含在 多行 script 内

			int isInCodeCount = 0; // 在 code 中的 代码是 不需要 去除的
			for (int i = 0; i < rawMDList.size(); i++) {
				String rawLine = rawMDList.get(i);
				String fixedLine = new String(rawLine);

				// <script 和 </script> 在同一行 那么 去除 这些
				if (rawLine.contains("<script") && rawLine.contains("</script>")) {
					String matchStr = getSubString_WithPre_WithEnd(rawLine, "<script", "</script>");
					if (matchStr != null) {
						fixedLine = rawLine.replace(matchStr, "");
						fixedLine = fixedLine.replace("<script", "");
						fixedLine = fixedLine.replace("</script>", "");
						fixedMDList.add(fixedLine);
						continue;
					}

				}

				if (rawLine.contains("<!--") && rawLine.contains("-->")) {
					String matchStr = getSubString_WithPre_WithEnd(rawLine, "<!--", "-->");
					if (matchStr != null) {
						fixedLine = rawLine.replace(matchStr, "");
						fixedLine = fixedLine.replace("<!--", "");
						fixedLine = fixedLine.replace("-->", "");
						fixedMDList.add(fixedLine);
						continue;
					}

				}

				// 多行 script
				if (rawLine.contains("<script") && !rawLine.contains("</script>")) {
					isEmptyLine_Script = true;
				}

				if (!rawLine.contains("<script") && rawLine.contains("</script>")) {
					isEmptyLine_Script = false;
					continue; // 这一行 也不要了
				}

				// 多行 script
				if (rawLine.contains("<!--") && !rawLine.contains("-->")) {
					isEmptyLine_ZhuShi = true;
				}

				if (!rawLine.contains("<!--") && rawLine.contains("-->")) {
					isEmptyLine_ZhuShi = false;
					continue; // 这一行 也不要了
				}

				if (rawLine.startsWith("```")) {
					isInCodeCount++;
				}

				// isInCodeCount%2 != 1 &&说明是在 code 中
				if (!isEmptyLine_Script && !isEmptyLine_ZhuShi && (isInCodeCount % 2 != 1)) {
					fixedMDList.add(fixedLine);
				}

			}

			return fixedMDList;

		}
		// &lt;==< &gt;==> 的转换
//		fixedLineStr = clear_lt_gt_tag(fixedLineStr);

		// 特殊字符串的处理
		String clear_amp_tag(String rawLine) {
			String fixedLineStr = new String(rawLine);
			fixedLineStr = fixedLineStr.replace("amp;", "");

			return fixedLineStr;
		}

		// 特殊字符串的处理
		String clear_lt_gt_tag(String rawLine) {
			String fixedLineStr = new String(rawLine);
			fixedLineStr = fixedLineStr.replace("&lt;", "<");
			fixedLineStr = fixedLineStr.replace("&gt;", ">");

			if (fixedLineStr.contains("[") && !fixedLineStr.contains("]") && isContainChinese(fixedLineStr)) {
				fixedLineStr = fixedLineStr.replace("[", "【");

			}

			return fixedLineStr;
		}

		boolean downloadImage(File targetImgFile, String networkUrl) {
			boolean downFlag = false;
			int byteRead;

			try {
				// 写入文件
				File fileSavePath = new File(targetImgFile.getAbsolutePath());
				// 注:如果保存文件夹不存在,那么则创建该文件夹
				File fileParent = fileSavePath.getParentFile();
				if (!fileParent.exists()) {
					fileParent.mkdirs();
				}

				URL url = new URL(networkUrl);
				URLConnection conn = url.openConnection();

				InputStream inStream = conn.getInputStream();

				FileOutputStream fs = new FileOutputStream(fileSavePath);
				byte[] buffer = new byte[1024];
				long beginTimeStamp = System.currentTimeMillis();
				System.out.println("FileOutputStream.write  写入本地文件  Begin   比较 downRawVideo_耗时_B ");
				while ((byteRead = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteRead);
				}
				long endTimeStamp = System.currentTimeMillis();
				long distance_second = (endTimeStamp - beginTimeStamp) / 1000;

				System.out.println(
						"FileOutputStream.write  写入本地文件  End ( downRawVideo_耗时_B【" + distance_second + " 秒】 耗时得很)");

				inStream.close();
				fs.close();

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("下载 " + networkUrl + " 图片失败: e=" + e);
			}

			if (targetImgFile.exists() && targetImgFile.length() > 10) {
				downFlag = true;
			}

			return downFlag;

		}

		String clear_inner_tag(String rawLine) {
			String fixed_LineStr = new String(rawLine);
			for (int i = 0; i < innderHtmlTag.size(); i++) {
				String tag = innderHtmlTag.get(i);
				String beginTag = "<" + tag + ">";
				String beginTag_Innder = "<" + tag + " ";
				String EndTag = "</" + tag + ">";

				// <head> </head> 同在一行
				if (fixed_LineStr.contains(beginTag) && fixed_LineStr.contains(EndTag)) {
					fixed_LineStr = fixed_LineStr.replace(beginTag, "");
					fixed_LineStr = fixed_LineStr.replace(EndTag, "");
				} else if (fixed_LineStr.contains(beginTag) && !fixed_LineStr.contains(EndTag)) {
					// 只包含 <head> 没有 </head> 的 字样
					fixed_LineStr = fixed_LineStr.replace(beginTag, "");
				} else if (!fixed_LineStr.contains(beginTag) && fixed_LineStr.contains(EndTag)) {
					// 只包含 </head> 没有 <head> 的 字样
					fixed_LineStr = fixed_LineStr.replace(EndTag, "");
				}

				// da <link rel="stylesheet" type="text/css" media="all"
				// href="../../../style.css"> dada // <div class="highlight">
				if (fixed_LineStr.contains(beginTag_Innder)) {
					String indderStr = getSubString_WithPre_WithEnd(fixed_LineStr, beginTag_Innder, ">");

					if (indderStr != null) {

						fixed_LineStr = fixed_LineStr.replace(indderStr, "");

						while (fixed_LineStr.contains(beginTag_Innder)) {
							String step_place_str = getSubString_WithPre_WithEnd(fixed_LineStr, beginTag_Innder, ">");
							if (step_place_str != null) {
								fixed_LineStr = fixed_LineStr.replace(step_place_str, "");
							}

						}

					}

				}

			}

			if (fixed_LineStr.contains("<title>") && fixed_LineStr.contains("</title>")) {

				md_title = fixed_LineStr.replace("<title>", "").replace("</title>", "").replace("	", "")
						.replace("=", "").replace(" ", "").replace("!", "").replace("&", "").replace("#", "")
						.replace("@", "").replace("|", "").replace("%", "").replace("&", "").trim();
			}

			return fixed_LineStr;

		}

		String clear_strong_tag(String rawLine) {
			String fixedLineStr = new String(rawLine);
			fixedLineStr = fixedLineStr.replace("<strong>", "!");
			fixedLineStr = fixedLineStr.replace("</strong>", "!");
			return fixedLineStr;
		}

		String clear_pre_tag(String rawLine) {
			String fixedLineStr = new String(rawLine);

			fixedLineStr = fixedLineStr.replace("<pre>", "\n```\n");
			fixedLineStr = fixedLineStr.replace("</pre>", "\n```\n");

			// <pre class="highlight"> 也要 改成 ```
			if (fixedLineStr.contains("<pre ")) { //
				String match_Inner_Pre_Tag = getSubString_WithPre_WithEnd(fixedLineStr, "<pre ", ">");

				if (match_Inner_Pre_Tag != null) {
					fixedLineStr = fixedLineStr.replace(match_Inner_Pre_Tag, "\n```\n");

				}

			}
			return fixedLineStr;
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
// begin_ExpIndex = -1 end_FirstIndex=34  oldExp=【      <a class="pjaxlink" href="/"><img src="/public/upload/gavatar/gavatar.jpg" class="img-rounded avatar"></a>】     pre=【<img】  end=【>】
			if (end_FirstIndex == -1) {
				System.out.println("无法匹配到B " + " begin_ExpIndex = " + begin_ExpIndex + " end_FirstIndex="
						+ end_FirstIndex + "  oldExp=【" + oldExp + "】     pre=【" + pre + "】  end=【" + end + "】");

				return null;
			}

			String targetExpNoOut = result.substring(begin_ExpIndex + 1, end_FirstIndex); // 包前 不包后
			String targetExpWithOut = result.substring(begin_ExpIndex, end_FirstIndex + end.length()); // 包前 包后 加上 后缀的长度
			// System.out.println(" oldExp = " + oldExp);
			// System.out.println(" targetExpWithOut = " + targetExpWithOut);
			// System.out.println(" targetExpNoOut = " + targetExpNoOut);
			// System.out.println(" begin_ExpIndex = " + begin_ExpIndex + " end_FirstIndex
			// =" + end_FirstIndex);
			System.out.println(" begin_ExpIndex = " + begin_ExpIndex + " end_FirstIndex=" + end_FirstIndex
					+ "  oldExp=【" + oldExp + "】     pre=【" + pre + "】  end=【" + end + "】" + " targetExpWithOut=【"
					+ targetExpWithOut + "】");

			return targetExpWithOut;
		}

		// 计算 在 字符串 originStr 中 在位置 endIndex 之前 最近的那个 匹配上的 matchStr的 索引
		int calculNearPairIndex(String originStr, int endIndex, String matchStr) {
			int beginIndex = 0;
			String subStr = originStr.substring(0, endIndex);
			beginIndex = subStr.lastIndexOf(matchStr);
			return beginIndex;

		}

		String clear_p_br_tag(String rawLine) {
			String fixedLineStr = new String(rawLine);
			fixedLineStr = fixedLineStr.replace("<p>", "");
			fixedLineStr = fixedLineStr.replace("</p>", "");
			fixedLineStr = fixedLineStr.replace("<br>", "");

			// <img src="2.jpeg">
			if (fixedLineStr.contains("<img") && fixedLineStr.contains(">")) {
				// 类似从 <p> faa <img src="2.jpeg"> fafa</p> 计算得到 <img src="2.jpeg">
				String imageNamePre = time_stamp_str;

				// <img src="1.png">
				// 转为 <img src="/public/zimage/html2md/"+time"1.png">
				System.out.println("当前的 <p> 中包含 链接<img >   fixedLineStr=" + fixedLineStr);

				String fixedmatchImgCode = getSubString_WithPre_WithEnd(fixedLineStr, "<img", ">");

//				int whileCount = 0 ;
//				while(fixedmatchImgCode == null) {
//					String fixed_ImageCode_temp = getSubString_WithPre_WithEnd(fixedLineStr,"<img",">");
//					if(fixed_ImageCode_temp != null) {
//						fixedmatchImgCode = fixed_ImageCode_temp;
//					}else {
//						if(whileCount > 10) {
//							break;
//						}
//
//					}
//					whileCount++;
//				}

				String matchImgCode = fixedmatchImgCode;

				matchImgCode = matchImgCode.replace("<img", "").replace("/>", "").replace(">", "").replace("src=", "")
						.replace("\"", "").replace(" ", "");
//				srcImageUrl_ZimageUrl_Map= new HashMap<String,String>();
//				srcImageUrl_AbsUrl_Map= new HashMap<String,String>();
//				srcImageUrl_List = new ArrayList<String> ();
				System.out.println("matchImgCode = " + matchImgCode);
				if (!srcImageUrl_List.contains(matchImgCode)) {

					// 动态计算 绝对路径的 url
					String match_abs_imgsrc = rootUrl + "/" + matchImgCode;
					String match_abs_imgsrc_possible1 = rootUrl_possible_url1 + "/" + matchImgCode;

					String oneName_NoSep_FileName = matchImgCode.replace("/", "_");
					String match_relative_imgsrc = "/public/zimage/html2md/" + time_stamp_str + "_"
							+ oneName_NoSep_FileName;

//
					File match_relative_img_file = new File(html_zimage_dir.getAbsolutePath() + File.separator
							+ time_stamp_str + "_" + oneName_NoSep_FileName);

					srcImageUrl_List.add(matchImgCode);
					srcImageUrl_LocalFile_Map.put(matchImgCode, match_relative_img_file);
					srcImageUrl_AbsUrl_Map.put(matchImgCode, match_abs_imgsrc);
					srcImageUrl_AbsUrl_Possibale1_Map.put(matchImgCode, match_abs_imgsrc_possible1);
					srcImageUrl_ZimageUrl_Map.put(matchImgCode, match_relative_imgsrc);

//				    String match_relative_imagecode = "<img src=\""+match_relative_imgsrc+"\">";

					// <img src="1.png">
					// 转为 <img src="/public/zimage/html2md/"+time"1.png">
					fixedLineStr = fixedLineStr.replace(matchImgCode, match_relative_imgsrc);

					// zimage/html_to_md/timestamp.jpg
				}

			}

			if (fixedLineStr.contains("<a") && fixedLineStr.contains(">")) {

				System.out.println("当前的 <p> 中包含 链接<a> 保留  fixedLineStr=" + fixedLineStr);

			}

			return fixedLineStr;
		}

		String title_to_jing(String rawLine) {
			String fixedLineStr = new String(rawLine);
			// 0. <h1> 转为 # <h6> 转为 ######
			// <h1 class="entry-title">无线网络WPA2加密的破解</h1>

			fixedLineStr = fixedLineStr.replace("	", "");
			if (fixedLineStr.contains("<h1") && fixedLineStr.contains("</h1>")) {
				String h_tag_innder = getSubString_WithPre_WithEnd(fixedLineStr, "<h1", ">");

				fixedLineStr = fixedLineStr.replace(h_tag_innder, "");
				fixedLineStr = fixedLineStr.replace("</h1>", "");
				fixedLineStr = "## " + fixedLineStr;
			}

			if (fixedLineStr.contains("<h2") && fixedLineStr.contains("</h2>")) {
				String h_tag_innder = getSubString_WithPre_WithEnd(fixedLineStr, "<h2", ">");

				fixedLineStr = fixedLineStr.replace(h_tag_innder, "");
				fixedLineStr = fixedLineStr.replace("</h2>", "");
				fixedLineStr = "### " + fixedLineStr;
			}

			if (fixedLineStr.contains("<h3") && fixedLineStr.contains("</h3>")) {
				String h_tag_innder = getSubString_WithPre_WithEnd(fixedLineStr, "<h3", ">");

				fixedLineStr = fixedLineStr.replace(h_tag_innder, "");
				fixedLineStr = fixedLineStr.replace("</h3>", "");
				fixedLineStr = "#### " + fixedLineStr;
			}

			if (fixedLineStr.contains("<h4") && fixedLineStr.contains("</h4>")) {
				String h_tag_innder = getSubString_WithPre_WithEnd(fixedLineStr, "<h4", ">");

				fixedLineStr = fixedLineStr.replace(h_tag_innder, "");
				fixedLineStr = fixedLineStr.replace("</h4>", "");
				fixedLineStr = "##### " + fixedLineStr;
			}

			if (fixedLineStr.contains("<h5") && fixedLineStr.contains("</h5>")) {
				String h_tag_innder = getSubString_WithPre_WithEnd(fixedLineStr, "<h5", ">");

				fixedLineStr = fixedLineStr.replace(h_tag_innder, "");
				fixedLineStr = fixedLineStr.replace("</h5>", "");
				fixedLineStr = "###### " + fixedLineStr;
			}

			if (fixedLineStr.contains("<h6") && fixedLineStr.contains("</h6>")) {
				String h_tag_innder = getSubString_WithPre_WithEnd(fixedLineStr, "<h6", ">");

				fixedLineStr = fixedLineStr.replace(h_tag_innder, "");
				fixedLineStr = fixedLineStr.replace("</h6>", "");
				fixedLineStr = "###### " + fixedLineStr;
			}

			return fixedLineStr;
		}

		// html2md ==================== end ==========================

		String BrowserOperation_WithRootUrl(String mMainUrl) {

			String mainPageHtmlStr = null;
			File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());

			ChromeDriver driver = mChromeDriver;
			int loop_index = 0;
			try {
				long waitTime = 1000;
				long timeout = 60_000; // 15 秒 // 给他 1分钟

				driver.get(mMainUrl);
				String title = driver.getTitle();
				System.out.printf("loop_index[" + loop_index + "] = " + title);

				System.out.printf("A now accesss %s \n", driver.getCurrentUrl());

//				 long waitTime = Double.valueOf(Math.max(3, Math.random() * 5) * 1000).longValue();
				TimeUnit.MILLISECONDS.sleep(waitTime);

				// 循环下拉，直到全部加载完成或者超时
				do {
					new Actions(driver).sendKeys(Keys.END).perform();
					TimeUnit.MILLISECONDS.sleep(waitTime);

					timeout -= waitTime;
					loop_index++;
					curPositionHtmlCodeStr = driver.getPageSource();
				} while (!driver.getPageSource().contains("已经到底部，没有新的内容啦") && timeout > 0);
				System.out.println("BrowserOperation_WithRootUrl 已经到底部，没有新的内容啦");
				mainPageHtmlStr = driver.getPageSource();
				curPositionHtmlCodeStr = driver.getPageSource();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("获取网页源码的时候出错  mMainUrl = " + mMainUrl);
				e.printStackTrace();

			}

			if (mainPageHtmlStr != null) {

				System.out.println("当前已经得到网页Html代码如下:\n" + mainPageHtmlStr);

			} else {
				System.out.println("rootUrl.mHtmlStr  ==== null ");
			}

			return mainPageHtmlStr;
		}

	}
// 规则:
//1.jpg 的 Copyright 放入对应的 video文件的 md值
//2. jpg  和 video  命名都是 以 md5 文件命名
//3. 所有的 artist  Desc  Make  Mode  Copyright UserComment  信息都以 _ 下划线 结束防止可能的乱码
//4. jpg 的 Artist 放入涉及的知识内容 作为分类的依据 以下划线_分开
//5. Desc  放入自己的对video 的个人见解 理解
// 6. Make  Mode  UserComment 这三项  还没想好放什么
// 7. 涉及初衷 是为了 把 学习过的video 作为 子项 在各个知识点类目下 显示 为了保存自己学习的过程 而设计的这个规则
//  zfilesearch_D6.bat .jpg    能查看到具体的填充项

	// 读取当前文件夹下的jpg文件(仅仅时当前目录) 然后 读取当前的jpg的 artist Desc Make Mode Copyright
	// UserComment 信息 来生成 .md文件

//	_______ type=[.jpg] index=[1] name=[2021-08-04_144330.jpg]  Exif Begin  _______
//			JpgIndex[1] == Artist[隔热_] Desc[个问题_] Make[疯玩五天_] Mode[废物废物_] Copyright[如果我问过我_] UserComment[问他我问过_]

	volatile int mCategory_JpgExif_Count_Rule_41 = 0;

	class Read_Jpg_Exif_Info_Create_MDContent_Rule_41 extends Basic_Rule {
		ArrayList<File> jpgFileList; // 当前目录下的 jpg 文件
		ArrayList<Jpg_Exif> jpgExifList; // 当前从 jpg读取信息后生成的原始数据
		String video_url_prefix; // video md文件中 video标签的前缀
		String image_url_prefix; // jpg md文件中 jpg标签的前缀

		boolean is_kaoyan; // 考研 类型
		boolean is_gaokao; // 高考 类型

		ArrayList<String> English_CharList; // A-Z 的 集合
		ArrayList<String> Chinese_CharList; // 中文的集合 "公式集合" "导式集合" "结尾叹"

		ArrayList<String> mCategoryItemList_In_Jpg; // 所有 分类 的 类型 二级分类的类型

		// key 为 jpg 中的 Category , List 为 符合 这个 category 的 Jpg_Exif
		Map<String, ArrayList<Jpg_Exif>> mCategory_JpgExifList_Map;

		// key 英语Englist OneWord , value 为 符合 这个 Key的 Category的集合

		Map<String, ArrayList<String>> mOneWord_CategoryArr_Map;
		//

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// mdname_true // kaoyan_true gaokao_true

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if ("kaoyan_true".equals(paramItem_lower_trim)) {
					is_kaoyan = true;

				}

				if ("gaokao_true".equals(paramItem_lower_trim)) {
					is_gaokao = true;

				}

			}

			if (is_gaokao) {
				video_url_prefix = "D:/Git_Dir/zschool/zschool_media/mp4_gaokao_land/";
				image_url_prefix = "/public/zimage/zschool_media/jpg_gaokao_land/";
			} else {
				video_url_prefix = "D:/Git_Dir/zschool/zschool_media/mp4_kaoyan_land/";
				image_url_prefix = "/public/zimage/zschool_media/jpg_kaoyan_land/";
			}

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		Read_Jpg_Exif_Info_Create_MDContent_Rule_41() {
			super("#", 41, 4); //
			jpgFileList = new ArrayList<File>();
			jpgExifList = new ArrayList<Jpg_Exif>();

			initEnglishChar();
			initChineseChar();
		}

		void initChineseChar() {
			Chinese_CharList = new ArrayList<String>();
			Chinese_CharList.add("公式集合");
			Chinese_CharList.add("导式集合");
			Chinese_CharList.add("结尾叹");

		}

		void initEnglishChar() {
			English_CharList = new ArrayList<String>();
			mCategoryItemList_In_Jpg = new ArrayList<String>();
			mOneWord_CategoryArr_Map = new HashMap<String, ArrayList<String>>();
			mCategory_JpgExifList_Map = new HashMap<String, ArrayList<Jpg_Exif>>();

			English_CharList.add("A");
			English_CharList.add("B");
			English_CharList.add("C");
			English_CharList.add("D");
			English_CharList.add("E");
			English_CharList.add("F");
			English_CharList.add("G");
			English_CharList.add("H");
			English_CharList.add("I");
			English_CharList.add("J");
			English_CharList.add("K");
			English_CharList.add("L");
			English_CharList.add("M");
			English_CharList.add("N");
			English_CharList.add("O");
			English_CharList.add("P");
			English_CharList.add("Q");
			English_CharList.add("R");
			English_CharList.add("S");
			English_CharList.add("T");
			English_CharList.add("U");
			English_CharList.add("V");
			English_CharList.add("W");
			English_CharList.add("X");
			English_CharList.add("Y");
			English_CharList.add("Z");
		}

		class Jpg_Exif {
			// key 是 对应的首字母的 英文 大写字母 , List 是这个 Jpg_Exif 对应的属于这个索引下的 当前的Category

			Map<String, ArrayList<String>> mEnglishChar_CategoryList_Map;
			ArrayList<String> mCategoryList; // 在 artist_category 中以_分隔的 各个项
			String mImageArtist_CategoryStr = null;
			String mImageCopyright_VideoMD = null;
			String mImageDescription_SelfDesc = null;
			String mImageMake_OriginSrc = null; // 图片的问题的出处 来源问题
			String mPhotoUserComment_KnowledgePoint = null; // 知识点 待定 KnowLedge 知识点

			String mImageModel_HttpUrl = null; // 待定 // 图片截图的网络地址

			File imageFile; // 从该 jpg 文件读取到的 exif 信息 原文件
			String videoName; // 从 jpg 读取到 Copyright 是对应的 video 的文件名称
			String videoUrl; // video 对应的 url 这个 .mp4 结尾 前缀 待定 可能需要从外部读取
			String imageUrl; // jpg 显示的 url

			@SuppressWarnings("unchecked")
			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder();

				Map.Entry<String, ArrayList<String>> entry;

				if (mEnglishChar_CategoryList_Map != null) {
					Iterator iterator = mEnglishChar_CategoryList_Map.entrySet().iterator();
					while (iterator.hasNext()) {
						entry = (Map.Entry<String, ArrayList<String>>) iterator.next();

						// 获取 名称的 首字母
						String oneWordStr = entry.getKey(); // Map的Value // 作者名称
						ArrayList<String> categoryNameList = entry.getValue();
						sb.append("mapKey(" + oneWordStr + "_" + categoryNameList.size() + ")【");
						for (int i = 0; i < categoryNameList.size(); i++) {
							String categoryItem = categoryNameList.get(i);
							sb.append(categoryItem + ",");
						}
						sb.append("】");

					}
				}

				return "ImageName[" + imageFile.getName() + "] category[" + mImageArtist_CategoryStr + "]  video["
						+ mImageCopyright_VideoMD + "] desc[" + mImageDescription_SelfDesc + "] Make["
						+ mImageMake_OriginSrc + "] Model[" + mImageModel_HttpUrl + "] usercomment["
						+ mPhotoUserComment_KnowledgePoint + "]  videoUrl[" + videoUrl + "] imageUrl[" + imageUrl
						+ "]  mCategoryList.size=[" + mCategoryList.size() + "] Map=" + sb.toString();
			}

			void init_Artist_Category() {
				mEnglishChar_CategoryList_Map = new HashMap<String, ArrayList<String>>();
				mCategoryList = new ArrayList<String>();

				if (mImageArtist_CategoryStr != null && !mImageArtist_CategoryStr.contains("_")) {

					mCategoryList.add(mImageArtist_CategoryStr);
					String itemStr = mImageArtist_CategoryStr.replace("_", "").replace(" ", "").trim();

					String Alphabet_Word = getFirstZiMu(itemStr);

					if (mEnglishChar_CategoryList_Map.get(Alphabet_Word) == null) {
						ArrayList<String> category_word_list = new ArrayList<String>();
						category_word_list.add(itemStr);
						mEnglishChar_CategoryList_Map.put(Alphabet_Word, category_word_list);
					} else {

						ArrayList<String> category_word_list = mEnglishChar_CategoryList_Map.get(Alphabet_Word);
						category_word_list.add(itemStr);
					}

					return;
				}

				if (mImageArtist_CategoryStr != null && mImageArtist_CategoryStr.contains("_")) {

					String[] categoryItem = mImageArtist_CategoryStr.trim().split("_");

					for (int i = 0; i < categoryItem.length; i++) {
						String itemStr = categoryItem[i];
						itemStr = itemStr.replace("_", "").replace(" ", "").trim();
						mCategoryList.add(itemStr);

						// zukgit
						String Alphabet_Word = getFirstZiMu(itemStr);

						if (mEnglishChar_CategoryList_Map.get(Alphabet_Word) == null) {
							ArrayList<String> category_word_list = new ArrayList<String>();
							category_word_list.add(itemStr);
							mEnglishChar_CategoryList_Map.put(Alphabet_Word, category_word_list);
						} else {

							ArrayList<String> category_word_list = mEnglishChar_CategoryList_Map.get(Alphabet_Word);
							category_word_list.add(itemStr);
						}

					}

				}

			}

			Jpg_Exif(File curImageFile) {
				imageFile = curImageFile;
				imageUrl = image_url_prefix + curImageFile.getName();
				initExifInfo(curImageFile);
				init_Artist_Category();

			}

			void initExifInfo(File file) {

				String mImageDescription_Utf8 = null;
				String mImageMake_Utf8 = null;
				String mImageModel_Utf8 = null;
				String mImageArtist_Utf8 = null;
				String mImageCopyright_Utf8 = null;
				String mPhotoUserComment_Utf8 = null;
				int angel = 0;
				Metadata metadata;

				try {
					metadata = JpegMetadataReader.readMetadata(file);
					metadata.getDirectories();

					// zukgit_directory [Exif IFD0] - Orientation = Right side, top (Rotate 90 CW)
					for (Directory directory : metadata.getDirectories()) {
						for (Tag tag : directory.getTags()) {
							// 格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
//							System.out.format("zukgit_directory  [%s] - %s = %s\n", directory.getName(), tag.getTagName(),tag.getDescription());

							if ("Exif IFD0".equals(directory.getName())) {

								String mImageDescription = directory.getString(ExifIFD0Directory.TAG_IMAGE_DESCRIPTION);
								if (mImageDescription != null)
									mImageDescription_Utf8 = new String(mImageDescription.getBytes(), "UTF-8");

								String mImageMake = directory.getString(ExifIFD0Directory.TAG_MAKE);
								if (mImageMake != null)
									mImageMake_Utf8 = new String(mImageMake.getBytes(), "UTF-8");

								String mImageModel = directory.getString(ExifIFD0Directory.TAG_MODEL);
								if (mImageModel != null)
									mImageModel_Utf8 = new String(mImageModel.getBytes(), "UTF-8");

								String mImageArtist = directory.getString(ExifIFD0Directory.TAG_ARTIST);
								if (mImageArtist != null)
									mImageArtist_Utf8 = new String(mImageArtist.getBytes(), "UTF-8");

								String mImageCopyright = directory.getString(ExifIFD0Directory.TAG_COPYRIGHT);
								if (mImageCopyright != null)
									mImageCopyright_Utf8 = new String(mImageCopyright.getBytes(), "UTF-8");

//								System.out.println("XXmImageDescription=["+mImageDescription+"]  Utf8["+mImageDescription_Utf8+"]");
//								System.out.println("XXmImageMake=["+mImageMake+"]  Utf8["+mImageMake_Utf8+"]");
//								System.out.println("XXmImageModel=["+mImageModel+"]  Utf8["+mImageModel_Utf8+"]");
//								System.out.println("XXmImageArtist=["+mImageArtist+"]  Utf8["+mImageArtist_Utf8+"]");
//								System.out.println("XXmImageCopyright=["+mImageCopyright+"]  Utf8["+mImageCopyright_Utf8+"]");

							}

							if ("Exif SubIFD".equals(directory.getName())) {

								if ("User Comment".equals(tag.getTagName())) {
									String mPhotoUserComment = tag.getDescription();
//							System.out.println("AZ_User_Comment=["+tag.getDescription()+"]");
									if (mPhotoUserComment != null)
										mPhotoUserComment_Utf8 = new String(mPhotoUserComment.getBytes(), "utf-8");
//							System.out.println("AZXXmPhotoUserComment=["+mPhotoUserComment+"]   mPhotoUserComment_Utf8=["+mPhotoUserComment_Utf8+"]" );

								}

							}
						}

					}

				} catch (JpegProcessingException e) {
					e.printStackTrace();
					System.out.println("JpegProcessingException  异常事件发生 ");
				} catch (IOException e) {
					System.out.println("IOException  异常事件发生 ");
					e.printStackTrace();
				}

//				String mImageArtist_CategoryStr= null;
//				String mImageCopyright_VideoMD = null;
//			    String mImageDescription_SelfDesc = null;
//
//
//			String mImageMake_Utf8 = null;     // 待定
//			String mImageModel_Utf8 = null;     // 待定
//			String mPhotoUserComment_Utf8 = null;   // 待定

				if (mImageArtist_Utf8 != null) {
					mImageArtist_CategoryStr = clear_end_xiahua_xian(mImageArtist_Utf8);

				} else {
					System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageArtist_CategoryStr !! ");
				}

				if (mImageDescription_Utf8 != null) {
					mImageDescription_SelfDesc = fixed_number_point(clear_end_xiahua_xian(mImageDescription_Utf8));

				} else {
					System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageDescription_SelfDesc !! ");
				}

				if (mImageCopyright_Utf8 != null) {

					mImageCopyright_VideoMD = clear_end_xiahua_xian(mImageCopyright_Utf8);
					videoName = mImageCopyright_VideoMD;
					videoUrl = video_url_prefix + videoName + ".mp4";
				} else {
					System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageCopyright_VideoMD !! ");
				}

				if (mImageMake_Utf8 != null) {
					this.mImageMake_OriginSrc = clear_end_xiahua_xian(mImageMake_Utf8);
				} else {
					System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageMake_Utf8 !! ");
				}

				if (mImageModel_Utf8 != null) {
					this.mImageModel_HttpUrl = clear_end_xiahua_xian(mImageModel_Utf8);
				} else {
					System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageModel_Utf8 !! ");
				}

				if (mPhotoUserComment_Utf8 != null) {
					this.mPhotoUserComment_KnowledgePoint = fixed_number_point(
							clear_end_xiahua_xian(mPhotoUserComment_Utf8));
				} else {
					System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mPhotoUserComment_Utf8 !! ");
				}

			}

		}

		//
		String fixed_number_point(String rawStr) {

			String tempStr = rawStr;
			tempStr = tempStr.replace("1.", "\n1.");
			tempStr = tempStr.replace("2.", "\n2.");
			tempStr = tempStr.replace("3.", "\n3.");
			tempStr = tempStr.replace("4.", "\n4.");
			tempStr = tempStr.replace("5.", "\n5.");
			tempStr = tempStr.replace("6.", "\n6.");
			tempStr = tempStr.replace("7.", "\n7.");
			tempStr = tempStr.replace("8.", "\n8.");
			tempStr = tempStr.replace("9.", "\n9.");
			tempStr = tempStr.replace("10.", "\n10.");

			tempStr = tempStr.trim();
			if (tempStr.startsWith("\n")) {
				tempStr = tempStr.substring(1);
			}
			return tempStr;

		}

		String clear_end_xiahua_xian(String rawStr) {

			String tempStr = rawStr;
			while (tempStr.endsWith("_")) {
				tempStr = tempStr.substring(0, tempStr.length() - 1);
			}

			return tempStr;

		}

		boolean isContainInChineseCategory(ArrayList<String> mChineseCategoryList, String matchStr) {

			for (int i = 0; i < mChineseCategoryList.size(); i++) {
				String chineseStr = mChineseCategoryList.get(i);
				if (matchStr.contains(chineseStr)) {
					return true;
				}
			}
			return false;

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
// TODO Auto-generated method stub
			jpgFileList = getAllSubFile(curDirFile, ".jpg");

			if (jpgFileList.size() == 0) {
				System.out.println(" 当前目录 curDirFile=[" + curDirFile.getAbsolutePath() + "] 内没有 jpg文件!!  请检查后再次执行!! ");
				return null;
			}

			for (int i = 0; i < jpgFileList.size(); i++) {
//		Jpg_Exif
				File jpgFile = jpgFileList.get(i);
				Jpg_Exif jpg_exif_item = new Jpg_Exif(jpgFile);

				jpgExifList.add(jpg_exif_item);

			}

			if (jpgExifList.size() == 0) {

				System.out.println(" 当前目录 curDirFile=[" + curDirFile.getAbsolutePath() + "]  存在[" + jpgFileList.size()
						+ "]个jpg " + " 但无法正常读取到 exif 信息  请检查后再次执行!! ");

				return null;
			}

			for (int i = 0; i < jpgExifList.size(); i++) {
				Jpg_Exif jpgExifitem = jpgExifList.get(i);
				System.out.println("jpg_exif[" + i + "] = " + jpgExifitem.toString());

				if (jpgExifitem.mCategoryList.size() > 0) {

					for (int j = 0; j < jpgExifitem.mCategoryList.size(); j++) {
						String exif_category_in_jpg = jpgExifitem.mCategoryList.get(j);

						if (!mCategoryItemList_In_Jpg.contains(exif_category_in_jpg)) {

							mCategoryItemList_In_Jpg.add(exif_category_in_jpg);
						}

						ArrayList<Jpg_Exif> matchJpgExifArr = mCategory_JpgExifList_Map.get(exif_category_in_jpg);
						if (matchJpgExifArr == null) {
							matchJpgExifArr = new ArrayList<Jpg_Exif>();
							matchJpgExifArr.add(jpgExifitem);
							mCategory_JpgExifList_Map.put(exif_category_in_jpg, matchJpgExifArr);

						} else {
							if (!matchJpgExifArr.contains(jpgExifitem)) {
								matchJpgExifArr.add(jpgExifitem);
							}
						}

					}
				}

			}

			// 初始化英语字母 A-B-C-D-E....-Z 的 Category
			for (int i = 0; i < mCategoryItemList_In_Jpg.size(); i++) {
				String categoryItem = mCategoryItemList_In_Jpg.get(i);
				boolean isContainInChineseCategory = isContainInChineseCategory(Chinese_CharList, categoryItem);
				if (isContainInChineseCategory) {
					continue;
				}
				String firstCharName = getFirstZiMu(categoryItem);
				ArrayList<String> matchCategoryList = mOneWord_CategoryArr_Map.get(firstCharName);
				if (matchCategoryList == null) {
					matchCategoryList = new ArrayList<String>();
					matchCategoryList.add(categoryItem);
					mOneWord_CategoryArr_Map.put(firstCharName, matchCategoryList);

				} else {
					if (!matchCategoryList.contains(categoryItem)) {
						matchCategoryList.add(categoryItem);
					}

				}
				System.out.println("category[" + (i + 1) + "] == " + firstCharName + "   " + categoryItem);
			}

			// 初始化 中文的 "结尾叹" "公式集合" "导式集合" category 的
			for (int i = 0; i < mCategoryItemList_In_Jpg.size(); i++) {
				// 排查出符合逻辑的 categoryItem xx zukgit
				String categoryItem = mCategoryItemList_In_Jpg.get(i);
				for (int j = 0; j < Chinese_CharList.size(); j++) {
					String chineseCategory = Chinese_CharList.get(j);
					if (categoryItem.contains(chineseCategory)) {

						ArrayList<String> matchCategoryList = mOneWord_CategoryArr_Map.get(chineseCategory);

						if (matchCategoryList == null) {
							matchCategoryList = new ArrayList<String>();
							matchCategoryList.add(categoryItem);
							mOneWord_CategoryArr_Map.put(chineseCategory, matchCategoryList);

						} else {
							if (!matchCategoryList.contains(categoryItem)) {
								matchCategoryList.add(categoryItem);
							}

						}

					}

				}

			}

			ArrayList<String> mMdContentList = BuildMDContent(English_CharList, Chinese_CharList,
					mOneWord_CategoryArr_Map, mCategory_JpgExifList_Map);

// 对 MD文件 进行分隔处理
			ArrayList<String> segerate_MdContentList = new ArrayList<String>();

			segerate_MdContentList.add("---");
			if (is_gaokao) {
				segerate_MdContentList.add("title: 高考数学学习");
				segerate_MdContentList.add("category: 学习");
				segerate_MdContentList.add("tags: Math");
				segerate_MdContentList.add("keywords: 数学 高考");
				segerate_MdContentList.add("typora-root-url: ..\\..\\");
				segerate_MdContentList
						.add("typora-copy-images-to: ..\\..\\public\\zimage\\zschool_media\\jpg_gaokao_land");

			} else if (is_kaoyan) {
				segerate_MdContentList.add("title: 研究考试学习");
				segerate_MdContentList.add("category: 学习");
				segerate_MdContentList.add("tags: Math");
				segerate_MdContentList.add("keywords: 数学 考研");
				segerate_MdContentList.add("typora-root-url: ..\\..\\");
				segerate_MdContentList
						.add("typora-copy-images-to: ..\\..\\public\\zimage\\zschool_media\\jpg_kaoyan_land");

				// 追加 到mMdContentList 后面的 一些信息
				ArrayList<String> appendForKaoYanList = new ArrayList<String>();

			}
			segerate_MdContentList.add("---");
			segerate_MdContentList.add("\n");

			segerate_MdContentList.add("## 简介_项式量(" + mCategory_JpgExif_Count_Rule_41 + ")");
			segerate_MdContentList.add(" * TOC");
			segerate_MdContentList.add(" {:toc}");
			segerate_MdContentList.add("\n");

			for (int i = 0; i < mMdContentList.size(); i++) {
				String contentLine = mMdContentList.get(i);
				segerate_MdContentList.add("\n" + contentLine);

			}

			writeContentToFile(G2_Temp_Text_File, segerate_MdContentList);
			NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		ArrayList<String> BuildMDContent(ArrayList<String> mEnglishCharList, ArrayList<String> mChineseCharList,
				Map<String, ArrayList<String>> xOneWord_CategoryArr_Map,
				Map<String, ArrayList<Jpg_Exif>> mCategory_JpgExifList_Map) {

//	StringBuilder mMDContentSB = new StringBuilder();

			ArrayList<String> mMDContentStrList = new ArrayList<String>();

			// 添加 英语 Category
			for (int i = 0; i < mEnglishCharList.size(); i++) {
				String charWord = mEnglishCharList.get(i);
				mMDContentStrList.add("## " + charWord);

				ArrayList<String> matchCategoryList = xOneWord_CategoryArr_Map.get(charWord);

				if (matchCategoryList != null) {

					matchCategoryList.sort(new Comparator<String>() {
						@Override
						public int compare(String o1, String o2) {
							return o1.compareTo(o2);
						}

					});

					for (int j = 0; j < matchCategoryList.size(); j++) {
						String categoryStrItem = matchCategoryList.get(j);

						mMDContentStrList.add("### " + (j + 1) + "_" + categoryStrItem);
						mCategory_JpgExif_Count_Rule_41++;
						ArrayList<Jpg_Exif> matchExifJpgArr = mCategory_JpgExifList_Map.get(categoryStrItem);

						if (matchExifJpgArr != null) {

							for (int k = 0; k < matchExifJpgArr.size(); k++) {
								Jpg_Exif jpgExif = matchExifJpgArr.get(k);

								String httpUrl = jpgExif.mImageModel_HttpUrl;
								String imageurl = jpgExif.imageUrl;
								String videourl = jpgExif.videoUrl;
								String videoName = jpgExif.videoName;
								String knowledgeComment = jpgExif.mPhotoUserComment_KnowledgePoint;
								String imageDescString = jpgExif.mImageDescription_SelfDesc;
								String originBirthPlaceInfo = jpgExif.mImageMake_OriginSrc == null ? "网络"
										: jpgExif.mImageMake_OriginSrc;

								// <img src="/public/zimage/tool/graphviz/digraph16.jpg">
								// /public/zimage/zschool/kaoyan/
								mMDContentStrList.add("<img src=\"" + imageurl + "\">");

								mMDContentStrList.add("**涉及知识点:**  ");
								mMDContentStrList.add("```\n" + knowledgeComment + "\n````");

								mMDContentStrList.add("**个人解题想法思路:**  ");
								mMDContentStrList.add("```\n" + imageDescString + "\n````");

								mMDContentStrList.add(
										"**视频网络链接:**  " + "[" + httpUrl + "](" + httpUrl + "){:target=\"_blank\"}");

								mMDContentStrList.add(
										"**视频本地链接:**  " + "[" + videoName + "](" + videourl + "){:target=\"_blank\"}");

								mMDContentStrList.add("**视频链接文件MD值:**  ");
								mMDContentStrList.add("```\n" + videoName + "\n````");
								mMDContentStrList.add("**图片名称:**  ");
								mMDContentStrList.add("```\n" + jpgExif.imageFile.getName() + "\n````");

								mMDContentStrList.add("**题目源信息:**  ");
								mMDContentStrList.add("```\n" + originBirthPlaceInfo + "\n````");

							}

						}

					}

				}
			}

			// 添加 中文 Category
			for (int i = 0; i < mChineseCharList.size(); i++) {

				String ChineseCategoryWord = mChineseCharList.get(i);
				mMDContentStrList.add("## " + ChineseCategoryWord);

				if (ChineseCategoryWord.equals("结尾叹")) {

					mMDContentStrList.add("**涉及知识点**  ");
					mMDContentStrList.add("```\n" + "为后辈们做些能遗留的东西,莫望无前吾辈自强......" + "\n````");

					continue;
				}

				ArrayList<String> matchCategoryList = xOneWord_CategoryArr_Map.get(ChineseCategoryWord);

				if (matchCategoryList != null) {

					matchCategoryList.sort(new Comparator<String>() {
						@Override
						public int compare(String o1, String o2) {
							return o1.compareTo(o2);
						}

					});

					for (int j = 0; j < matchCategoryList.size(); j++) {
						String categoryStrItem = matchCategoryList.get(j);

						mMDContentStrList.add("### " + (j + 1) + "_" + categoryStrItem);
						mCategory_JpgExif_Count_Rule_41++;
						ArrayList<Jpg_Exif> matchExifJpgArr = mCategory_JpgExifList_Map.get(categoryStrItem);

						if (matchExifJpgArr != null) {

							for (int k = 0; k < matchExifJpgArr.size(); k++) {
								Jpg_Exif jpgExif = matchExifJpgArr.get(k);

								String httpUrl = jpgExif.mImageModel_HttpUrl;
								String imageurl = jpgExif.imageUrl;
								String videourl = jpgExif.videoUrl;
								String videoName = jpgExif.videoName;
								String knowledgeComment = jpgExif.mPhotoUserComment_KnowledgePoint;
								String imageDescString = jpgExif.mImageDescription_SelfDesc;
								String originBirthPlaceInfo = jpgExif.mImageMake_OriginSrc == null ? "网络"
										: jpgExif.mImageMake_OriginSrc;

								// <img src="/public/zimage/tool/graphviz/digraph16.jpg">
								// /public/zimage/zschool/kaoyan/
								mMDContentStrList.add("<img src=\"" + imageurl + "\">");
								/*
								 * mMDContentStrList.add("**涉及知识点:**  "); mMDContentStrList.add("```\n" +
								 * knowledgeComment + "\n````");
								 *
								 * mMDContentStrList.add("**个人解题想法思路:**  "); mMDContentStrList.add("```\n" +
								 * imageDescString + "\n````");
								 *
								 * mMDContentStrList.add( "**视频网络链接:**  " + "[" + httpUrl + "](" + httpUrl +
								 * "){:target=\"_blank\"}");
								 *
								 * mMDContentStrList.add( "**视频本地链接:**  " + "[" + videoName + "](" + videourl +
								 * "){:target=\"_blank\"}");
								 *
								 * mMDContentStrList.add("**视频链接文件MD值:**  "); mMDContentStrList.add("```\n" +
								 * videoName + "\n````"); mMDContentStrList.add("**图片名称:**  ");
								 * mMDContentStrList.add("```\n" + jpgExif.imageFile.getName() + "\n````");
								 *
								 * mMDContentStrList.add("**题目源信息:**  "); mMDContentStrList.add("```\n" +
								 * originBirthPlaceInfo + "\n````");
								 */

							}

						}

					}

				}
			}

			System.out.println(" ============== 打印 MD 内容  Begin ============== ");
			for (int i = 0; i < mMDContentStrList.size(); i++) {
				String oneLine = mMDContentStrList.get(i);
				System.out.println(oneLine);
			}

			return mMDContentStrList;
		}

		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		String simpleDesc() {

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  ###  默认生成 考研类型的.md 读取当前文件夹下的jpg文件的exif信息 artist  Desc  Make  Mode  Copyright UserComment 并以此生成 .md文件  "
					+ "\n" + Cur_Bat_Name + " #_" + rule_index
					+ " kaoyan_true  ###  生成考研类型的md  读取当前文件夹下的jpg文件的exif信息 artist  Desc  Make  Mode  Copyright UserComment 并以此生成 .md文件 \n"
					+ "\n" + Cur_Bat_Name + " #_" + rule_index
					+ " gaokao_true   ###  生成高考类型的md  读取当前文件夹下的jpg文件的exif信息 artist  Desc  Make  Mode  Copyright UserComment 并以此生成 .md文件 \n";

		}

	}

	class Monitor_Browser_ForWindows_Rule_40 extends Basic_Rule {
		// 1-----详细 下载路径 2----详细下载路径 3-----详细下载路径
		Map<String, Integer> mDisplayUrl_Index_Map;

		File ImageDownloadDir;
		// 详细下载路径-----路径中要下载的内容
		HashMap<String, ArrayList<String>> mDisplayUrl_ImageUrlList_Map;

		// 所有的下载路径 用于避免重复下载
		ArrayList<String> allDownloadedUrlList;

		ChromeDriver mChromeDriver;

		// ------------- 输入类型A -------------
		String mainurl_InputValue; // 查询的主页面
		ArrayList<String> mCategoryNameList_InputValue; // 查询的分类名称

		// ------------- 输入类型B -------------
		int mPageSize_InputValue; // 查询页数
		String beginurl_InputValue; // 查询页数
		ArrayList<String> keynameList_InputValue; // category页面关键词

		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}

		Monitor_Browser_ForWindows_Rule_40() {
			super("#", 40, 3); // 不包括
			allDownloadedUrlList = new ArrayList<String>();
			mDisplayUrl_ImageUrlList_Map = new HashMap<String, ArrayList<String>>(5000);
			mDisplayUrl_Index_Map = Maps.newLinkedHashMap();
			mCategoryNameList_InputValue = new ArrayList<String>();
			keynameList_InputValue = new ArrayList<String>();
			mPageSize_InputValue = 10; // 默认是10 页
		}

		// mainurl_https://www.52pojie.cn
		// categoryname_还洗论坛AAAB_CategoryABB

		// pagesize_100
		// beginurl_https://www.52pojie.cn/forum-2-1.html
		// keyname_你好我好_大家好_都不好

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// TODO Auto-generated method stub
			ImageDownloadDir = new File(
					curDirPath + File.separator + "Rule" + rule_index + "_Download_" + getTimeStamp());

			ImageDownloadDir.mkdirs();
			ImageDownloadDir.mkdirs();

			for (int i = 0; i < inputParamList.size(); i++) {
				String inputStrItem = inputParamList.get(i);
				if (inputStrItem.startsWith("mainurl_")) {
					String mainUrl = inputStrItem.replace("mainurl_", "").trim();
					mainurl_InputValue = mainUrl;

				} else if (inputStrItem.startsWith("categoryname_")) {

					String categoryListStr = inputStrItem.replace("categoryname_", "").trim();

					String[] categoryNameArr = categoryListStr.split("_");
					if (categoryNameArr == null) {
						continue;

					}
					for (int j = 0; j < categoryNameArr.length; j++) {
						mCategoryNameList_InputValue.add(categoryNameArr[j].trim());
					}
				} else if (inputStrItem.startsWith("beginurl_")) {
					String beginurl = inputStrItem.replace("beginurl_", "").trim();
					beginurl_InputValue = beginurl;

				} else if (inputStrItem.startsWith("pagesize_")) {
					String pagesizeStr = inputStrItem.replace("pagesize_", "").trim();
					if (isNumeric(pagesizeStr)) {
						mPageSize_InputValue = Integer.parseInt(pagesizeStr);
					}

				} else if (inputStrItem.startsWith("keyname_")) {

					String keynameListStr = inputStrItem.replace("keyname_", "").trim();

					String[] keyNameArr = keynameListStr.split("_");
					if (keyNameArr == null) {
						continue;

					}
					for (int j = 0; j < keyNameArr.length; j++) {
						keynameList_InputValue.add(keyNameArr[j].trim());
					}
				}

			}
			System.out.println("输入参数详情如下:\n");
			System.out.println("输入类型A:");
			System.out.println("mainurl_InputValue=[" + mainurl_InputValue + "]");
			if (mCategoryNameList_InputValue.size() > 0) {
				for (int i = 0; i < mCategoryNameList_InputValue.size(); i++) {
					System.out.println(
							"mCategoryNameList_InputValue[" + i + "] = " + mCategoryNameList_InputValue.get(i));
				}
			} else {
				System.out.println("mCategoryNameList_InputValue=[ null ]");
			}
			if (keynameList_InputValue.size() > 0) {
				for (int i = 0; i < keynameList_InputValue.size(); i++) {
					System.out.println("keynameList_InputValue[" + i + "] = " + keynameList_InputValue.get(i));
				}
			} else {
				System.out.println("keynameList_InputValue=[ null ]");
			}

			System.out.println("pagesize=[" + mPageSize_InputValue + "]");

			System.out.println("输入类型B:");

			System.out.println("pagesize=[" + mPageSize_InputValue + "]");
			System.out.println("beginurl=[" + beginurl_InputValue + "]");

			if (keynameList_InputValue.size() > 0) {
				for (int i = 0; i < keynameList_InputValue.size(); i++) {
					System.out.println("keynameList_InputValue[" + i + "] = " + keynameList_InputValue.get(i));
				}
			} else {
				System.out.println("keynameList_InputValue=[ null ]");
			}

			if (mainurl_InputValue == null && keynameList_InputValue.size() == 0 && beginurl_InputValue == null) {

				System.out.println("当前输入的 mainurl  beginurl keynameList_InputValue 为 空! 请检查输入参数!!");
				return false;

			}

			if (!ImageDownloadDir.exists()) {
				System.out
						.println("当前下载文件的保存目录 不存在 程序无法执行下去:  ImageDownloadDir = " + ImageDownloadDir.getAbsolutePath());
				return false;
			}

			File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());

			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
			// 驱动位置
			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");

			CUR_CHROME_OPTIONS.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			mChromeDriver = new ChromeDriver(CUR_CHROME_OPTIONS);

			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		String simpleDesc() {

			return "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  mainurl_https://www.52pojie.cn/  categoryname_『原创发布区』 keyname_小工具  pagesize_5  ### 对网站进行抓取资源到本地  "
					+ "\n" + Cur_Bat_Name + " #_" + rule_index
					+ "  beginurl_https://www.douban.com/group/explore/culture    keyname_水  pagesize_3  ### 对网站进行抓取资源到本地   \n";

		}

		void TestOperationBrowser() {

			ArrayList<RootUrl_A> mSearchWebList = new ArrayList<RootUrl_A>();
			RootUrl_A pojie_52_A = new RootUrl_A(mainurl_InputValue);

			if (mCategoryNameList_InputValue.size() != 0) {
				for (int i = 0; i < mCategoryNameList_InputValue.size(); i++) {
					String catetegoryName = mCategoryNameList_InputValue.get(i);
					if ("".equals(catetegoryName)) {
						continue;
					}
					CategoryUrl_B category_B = new CategoryUrl_B(catetegoryName);
					pojie_52_A.addCategory(category_B);
					category_B.searchPageSize = mPageSize_InputValue;
					category_B.beginCategoryUrl = beginurl_InputValue;

					for (int j = 0; j < keynameList_InputValue.size(); j++) {
						String keyname = keynameList_InputValue.get(j);
						if ("".equals(keyname)) {
							continue;
						}

						category_B.addKeyWord(keyname);
					}
				}

			} else { // 没有输入 mCategoryNameList_InputValue
				if (mainurl_InputValue == null) {
					pojie_52_A.mRootUrl = beginurl_InputValue;
				}

				CategoryUrl_B category_B = new CategoryUrl_B(beginurl_InputValue, mPageSize_InputValue);
				pojie_52_A.addCategory(category_B);
				for (int j = 0; j < keynameList_InputValue.size(); j++) {
					String keyname = keynameList_InputValue.get(j);
					if ("".equals(keyname)) {
						continue;
					}

					category_B.addKeyWord(keyname);
				}

			}

			System.out.println("pojie_52_A.mCategoryUrlList.size() = " + pojie_52_A.mCategoryUrlList.size());

			mSearchWebList.add(pojie_52_A);

			for (int i = 0; i < mSearchWebList.size(); i++) {
				RootUrl_A rootItem = mSearchWebList.get(i);
				BrowserOperation_WithRootUrl(rootItem);
			}

//	System.out.println("mainPageHtmlStr = \n"+ mainPageHtmlStr);

		}

		@SuppressWarnings("unchecked")
		void ShowDisplayUrl() {

			ArrayList<String> urlLog = new ArrayList<String>();

			Map.Entry<String, Integer> entry;

			if (mDisplayUrl_Index_Map != null) {
				Iterator iterator = mDisplayUrl_Index_Map.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, Integer>) iterator.next();

					// 获取 名称的 首字母
					String url = entry.getKey(); // Map的Value // 作者名称
					Integer index = entry.getValue();
					urlLog.add("index[" + index + "]  url=[ " + url + " ]");
				}
			}

			if (urlLog.size() > 0) {

				String txtLogFileAbsPath = ImageDownloadDir.getAbsolutePath() + File.separator + "0_url.txt";
				File txtLogFile = new File(txtLogFileAbsPath);

				System.out.println("____________ DisplayUrlList Begin ____________ ");
				for (int i = 0; i < urlLog.size(); i++) {
					String oneLine = urlLog.get(i);
					System.out.println(oneLine);
				}
				System.out.println("____________ DisplayUrlList End ____________ ");
				urlLog.sort(mStringComparion);

				writeContentToFile(txtLogFile, urlLog);
				System.out.println("写入Log信息到文件:  txtLogFile=" + txtLogFile.getAbsolutePath());
			} else {
				System.out.println("当前没有打开过 DisplayUrl ");
			}

		}

		void BrowserOperation_WithRootUrl(RootUrl_A rootUrl) {

			String mMainUrl = rootUrl.mRootUrl;
			String mainPageHtmlStr = null;
			File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());

//			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
//			// 驱动位置
//			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");
//			CUR_CHROME_OPTIONS.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

//			ChromeDriver driver = new ChromeDriver(CUR_CHROME_OPTIONS);

			ChromeDriver driver = mChromeDriver;
			int loop_index = 0;
			try {
				long waitTime = 2000;
				long timeout = 15_000;

				driver.get(mMainUrl);
				String title = driver.getTitle();
				System.out.printf("loop_index[" + loop_index + "] = " + title);

				System.out.printf("A now accesss %s \n", driver.getCurrentUrl());

//				 long waitTime = Double.valueOf(Math.max(3, Math.random() * 5) * 1000).longValue();
				TimeUnit.MILLISECONDS.sleep(waitTime);

				// 循环下拉，直到全部加载完成或者超时
				do {
					new Actions(driver).sendKeys(Keys.END).perform();
					TimeUnit.MILLISECONDS.sleep(waitTime);

					timeout -= waitTime;
					loop_index++;
				} while (!driver.getPageSource().contains("已经到底部，没有新的内容啦") && timeout > 0);
				System.out.println("BrowserOperation_WithRootUrl 已经到底部，没有新的内容啦");
				rootUrl.mHtmlStr = driver.getPageSource();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("获取网页源码的时候出错  mMainUrl = " + mMainUrl);
				e.printStackTrace();

			}

			int next_index = 1;

			if (rootUrl.mHtmlStr != null) {
				System.out.println("rootUrl.mCategoryUrlList.size() = " + rootUrl.mCategoryUrlList.size());

				for (int i = 0; i < rootUrl.mCategoryUrlList.size(); i++) {
					CategoryUrl_B categoryValueB = rootUrl.mCategoryUrlList.get(i);
//					String keyName =  categoryValueB.mCategoryKeyName;
					ArrayList<String> keyNameList = categoryValueB.mKeyWordList;

					String mCategoryKeyName = rootUrl.mCategoryUrlList.get(i).mCategoryKeyName;
					String begin_categury_url = null;

					// <a href="forum-2-1.html" style="color: #F30012;">『原创发布区』</a>
					// 从 mHtmlStr 代码的 所有的 <a> 标签的 text 中找到 包含 keyName 的 那个 a 连接的 href属性
					if (mCategoryKeyName != null && !"".equals(mCategoryKeyName)) {
						begin_categury_url = calcul_categoryurl_from_html_RoouUrlA(rootUrl, mCategoryKeyName);
						System.out.println("getCategotyUrl   begin_categury_url=" + begin_categury_url
								+ "   mCategoryKeyName=" + mCategoryKeyName);
					} else {
						begin_categury_url = rootUrl.getCategoryList().get(i).beginCategoryUrl;
						System.out.println("DefineCategotyUrl  begin_categury_url = " + begin_categury_url
								+ "  mCategoryKeyName=" + mCategoryKeyName);
					}

					String a_href = begin_categury_url;

					System.out.println("keyName[" + mCategoryKeyName + "]  a_href[" + a_href + "]");

					if (a_href != null) {
//							categoryValueB.mCategoryUrl = a_href;
						// a_href 分类的主页面的 url
						categoryValueB.beginCategoryUrl = a_href; // 搜索到的 url 作为 默认的 起始 url

						if (!categoryValueB.mCategoryPageUrlList.contains(a_href)) {

							categoryValueB.mCategoryPageUrlList.add(a_href);
							BrowserOperation_WithCategoryUrl(rootUrl, categoryValueB, a_href);

						}
					}

					while (categoryValueB.nextPageUrl != null
							&& categoryValueB.nextPageUrl.equals(categoryValueB.mCategoryPageUrlList
									.get(categoryValueB.mCategoryPageUrlList.size() - 1))
							&& categoryValueB.mCategoryPageUrlList.size() <= categoryValueB.searchPageSize) {
						System.out.println("nextPageUrl[" + categoryValueB.nextPageUrl + "]  next_index[" + next_index
								+ "]   categoryValueB.mCategoryPageUrlList.size()["
								+ categoryValueB.mCategoryPageUrlList.size() + "]");
						BrowserOperation_WithCategoryUrl(rootUrl, categoryValueB, categoryValueB.nextPageUrl);
						next_index++;
					}

				}

			} else {
				System.out.println("rootUrl.mHtmlStr  ==== null ");
			}

		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			// TODO Auto-generated method stub

			TestOperationBrowser();
			ShowDisplayUrl();

			if (mChromeDriver != null) {
				mChromeDriver.close();
			}
			System.out.println("════════════════════ Rule_" + rule_index + "  Game Over!! ════════════════════");

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		class RootUrl_A {
			String mRootUrl; // 主目录地址
			String mHtmlStr; // 主目录的页面代码
			ArrayList<CategoryUrl_B> mCategoryUrlList; // 分类目录

			RootUrl_A(String rootUrl) {
				mRootUrl = rootUrl;
				mCategoryUrlList = new ArrayList<CategoryUrl_B>();

			}

			ArrayList<CategoryUrl_B> getCategoryList() {
				return mCategoryUrlList;
			}

			void addCategory(CategoryUrl_B categort) {
				mCategoryUrlList.add(categort);
			}

		}

		class CategoryUrl_B {
			String mCategoryKeyName; // 分类url的名称
			boolean isPageUrlInit; // PageUrl 是否已经完成 搜索 页面 对应的 url
			ArrayList<String> mCategoryPageUrlList; // category源码的url的路径的集合
			String nextPageUrl; // 下一页的地址
			int searchPageSize; // 需要搜索的页面的数量
			String beginCategoryUrl; // 起始的搜索页面

//			String mCategoryUrl_FirstPage; // 分类url的地址  分类首页地址
//			ArrayList<DisplayUrl_C> mDisplayUrlList_FirstPage;  // 展示的详细的地址
//			String mHtmlStr_FirstPage;

			ArrayList<String> mKeyWordList; // 要搜索的 关键词 列表

			Map<String, ArrayList<DisplayUrl_C>> mPageUrl_DisplayUrlList_Map; // 页面url--该页详情的url列表对应的map
			Map<String, String> mPageUrl_HtmlStr_Map; // 对应category 第一页的源码 第二页的源码 第三页的源码.....

			CategoryUrl_B(String beginUrl, int pageSize) {
				mCategoryKeyName = "";
				mPageUrl_DisplayUrlList_Map = Maps.newConcurrentMap();
				mPageUrl_HtmlStr_Map = Maps.newConcurrentMap();
				mCategoryPageUrlList = new ArrayList<String>();
				mKeyWordList = new ArrayList<String>();
				searchPageSize = pageSize;
				beginCategoryUrl = beginUrl;

			}

			CategoryUrl_B(String keyName) {
				mCategoryKeyName = keyName;
				mPageUrl_DisplayUrlList_Map = Maps.newConcurrentMap();
				mPageUrl_HtmlStr_Map = Maps.newConcurrentMap();
				mCategoryPageUrlList = new ArrayList<String>();
				mKeyWordList = new ArrayList<String>();
				searchPageSize = 10;

			}

			void addKeyWord(String keyWord) {
				if (!mKeyWordList.contains(keyWord)) {
					mKeyWordList.add(keyWord);
				}

			}

			void addDisPlayUrl(String pageurl, DisplayUrl_C displayUrl) {
				if (mPageUrl_DisplayUrlList_Map.get(pageurl) == null) {
					ArrayList<DisplayUrl_C> displayUrl_C_item = new ArrayList<DisplayUrl_C>();
					displayUrl_C_item.add(displayUrl);
					mPageUrl_DisplayUrlList_Map.put(pageurl, displayUrl_C_item);
				} else {

					ArrayList<DisplayUrl_C> displayUrl_C_item = mPageUrl_DisplayUrlList_Map.get(pageurl);
					displayUrl_C_item.add(displayUrl);
				}

			}

		}

		class DisplayUrl_C {
			String mHtmlStr;
			String mDisplayName; // 需要在 Category 中 过滤显示的 关键字
			ArrayList<String> mDownloadUrlList;

			DisplayUrl_C(String keyName) {
				mDisplayName = keyName;
				mDownloadUrlList = new ArrayList<String>();
			}

		}

		void BrowserOperation_WithCategoryUrl(RootUrl_A rootUrl_A, CategoryUrl_B categoryUrl_B,
				String href_categoryUrl) {

			String firstPageHtml_InCategory = null;
//			for (int i = 0; i < categoryUrl_B.mKeyWordList.size(); i++) {
//				String keyword_InPage = categoryUrl_B.mKeyWordList.get(i);

			String mCategoryPageHtmlStr = null;
			File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

//				System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());
//
//
//				ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
//				// 驱动位置
//				ChromeDriver driver = new ChromeDriver(CUR_CHROME_OPTIONS);

			ChromeDriver driver = mChromeDriver;

			int loop_index = 0;
			try {
				long waitTime = 2000;
				long timeout = 15_000;

				driver.get(href_categoryUrl);
				String title = driver.getTitle();
				System.out.printf("loop_index[" + loop_index + "] = " + title);

				System.out.printf("A now accesss %s \n", driver.getCurrentUrl());

//					 long waitTime = Double.valueOf(Math.max(3, Math.random() * 5) * 1000).longValue();
				TimeUnit.MILLISECONDS.sleep(waitTime);

				// 循环下拉，直到全部加载完成或者超时
				do {
					new Actions(driver).sendKeys(Keys.END).perform();
					TimeUnit.MILLISECONDS.sleep(waitTime);

					timeout -= waitTime;
					loop_index++;
				} while (!driver.getPageSource().contains("已经到底部，没有新的内容啦") && timeout > 0);
				System.out.println("BrowserOperation_WithCategoryUrl 已经到底部，没有新的内容啦");
				String pageHtmlStr = driver.getPageSource();
				categoryUrl_B.mPageUrl_HtmlStr_Map.put(href_categoryUrl, pageHtmlStr);
				firstPageHtml_InCategory = pageHtmlStr; // 保存该页的内容 推到出第二页的 url
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("获取网页源码的时候出错  href_categoryUrl = " + href_categoryUrl);
				e.printStackTrace();

			} finally {
//					driver.close();

			}

			if (!categoryUrl_B.isPageUrlInit) {

				initCategoryPageUrl(rootUrl_A, categoryUrl_B, firstPageHtml_InCategory);
			}

			if (categoryUrl_B.mPageUrl_HtmlStr_Map.get(href_categoryUrl) != null) {

				System.out.println("categoryUrl_B.mKeyWordList.size() = " + categoryUrl_B.mKeyWordList.size());
				for (int i = 0; i < categoryUrl_B.mKeyWordList.size(); i++) {

					String displayKeyName = categoryUrl_B.mKeyWordList.get(i);
					DisplayUrl_C displayUrl_C = new DisplayUrl_C(displayKeyName);
//						String displayKeyName = displayUrl.mDisplayName;
					System.out.println("displayKeyName-A = " + displayKeyName);
					ArrayList<String> a_href_Display_UrlList = calcul_categoryurl_from_html_CategoryB(rootUrl_A,
							categoryUrl_B, displayKeyName, firstPageHtml_InCategory);
					System.out.println("displayKeyName-B = " + displayKeyName + "  a_href_Display_UrlList=="
							+ a_href_Display_UrlList);

					if (a_href_Display_UrlList == null) {
						continue;
					}
					System.out.println("displayKeyName-C = " + displayKeyName);
					displayUrl_C.mDownloadUrlList.addAll(a_href_Display_UrlList);
					System.out.println("displayKeyName-D = " + displayKeyName
							+ "  displayUrl_C.mDownloadUrlList.size()=" + displayUrl_C.mDownloadUrlList.size());

					for (int j = 0; j < a_href_Display_UrlList.size(); j++) {
						String href_url = a_href_Display_UrlList.get(j);
						System.out.println("Catagery[" + categoryUrl_B.mCategoryKeyName + "]  DisplayUrlIndex[" + i
								+ "] DisplyUrlCount[" + categoryUrl_B.mKeyWordList.size() + "]  A[" + j + "]  ACount["
								+ a_href_Display_UrlList.size() + "] href=" + href_url);

						// 分析 详细 download show 内容url中的
						// href=https://www.52pojie.cn/thread-1467500-1-1.html 对应的页面

						TryAnalysisHrefForDisplayUrl(rootUrl_A, categoryUrl_B, displayUrl_C, href_url);
					}

				}

			} else {
				System.out.println("categoryUrl_B.mPageUrl_HtmlStr_Map.get(href_categoryUrl)  === null   没有解析到html源码?");

			}
//			}

		}

		boolean isInnerHtmlContainNextTip(String innerHtml, ArrayList<String> nextUrlTipList) {
			boolean containFlag = false;
			for (int i = 0; i < nextUrlTipList.size(); i++) {
				String tipitem = nextUrlTipList.get(i);

				if (innerHtml.contains(tipitem)) {
					return true;
				}

			}

			return containFlag;

		}

		// 从首页中 推导 出 第二页 第三页的 地址
		void initCategoryPageUrl(RootUrl_A rootUrl_A, CategoryUrl_B categoryUrl_B, String firstPageHtml) {

			org.jsoup.nodes.Document curDocument = Jsoup.parse(firstPageHtml);

			String rawHtmlStr = curDocument.html();
			ArrayList<String> nextUrlTipList = new ArrayList<String>();
			nextUrlTipList.add("下一页");
			nextUrlTipList.add("后页");
			nextUrlTipList.add("下页");
			Elements mElements = curDocument.getElementsByTag("a");

			if (isInnerHtmlContainNextTip(rawHtmlStr, nextUrlTipList)) {

//			Elements  mElements =  curDocument.select("a[href]");
// a class="bm_h" href="javascript:;" rel="forum.php?mod=forumdisplay&fid=2&page=3" curpage="2" id="autopbn" totalpage="204" picstyle="0" forumdefstyle="">下一页 &raquo;</a>
// <a href="forum-2-3.html" class="nxt">下一页</a>

				if (mElements != null && mElements.size() > 0) {

					System.out.println("a[href] mElements.size()  = " + mElements.size());
					Iterator<org.jsoup.nodes.Element> nextpage_element_iterator = mElements.iterator();
					while (nextpage_element_iterator.hasNext()) {
						org.jsoup.nodes.Element curElement = nextpage_element_iterator.next();
						String innerHtml = curElement.html();
						String href = curElement.attr("href");
						System.out.println("nexttip_innerHtml = " + innerHtml + "   href[" + href + "]");
						if (isInnerHtmlContainNextTip(innerHtml, nextUrlTipList)) {

							if (href == null || "".equals(href.trim()) || href.contains("javascript")) {
								continue;
							}

							if (!href.startsWith("http")) {
								String fixed_href = rootUrl_A.mRootUrl + "/" + href;
								fixed_href = fixed_href.replace("//", "/");
								if (!categoryUrl_B.mCategoryPageUrlList.contains(fixed_href)) {
									categoryUrl_B.nextPageUrl = fixed_href;
									categoryUrl_B.mCategoryPageUrlList.add(fixed_href);
									System.out.println("a[href]_Next_Tip  nextPageUrl=[" + fixed_href
											+ "]  innerHtml = " + innerHtml + "   href[" + href + "]");

									return;
								}

							}

						}

					}

				}

			} else { // 当前 页面 并不包括 下一页 下页 等 标签

				// <a> 数字 <a> 的 标签的 url的 集合
				ArrayList<String> digital_inner_linkA_hrefList = new ArrayList<String>();

				// 当前已经保存了的 url 列表的 集合 如果不再里面 那么就 加入
				ArrayList<String> curPageUrlList = categoryUrl_B.mCategoryPageUrlList;
				if (mElements != null && mElements.size() > 0) {
					System.out.println("a[href]_No_Next_Tip mElements.size()  = " + mElements.size());
					Iterator<org.jsoup.nodes.Element> nextpage_element_iterator = mElements.iterator();
					while (nextpage_element_iterator.hasNext()) {
						org.jsoup.nodes.Element curElement = nextpage_element_iterator.next();
						String innerHtml = curElement.html();
						String href = curElement.attr("href");
						System.out.println("a[href]_No_Next_Tip  innerHtml = " + innerHtml + "   href[" + href + "]");
						if (isNumeric(innerHtml)) {

							if (href == null || "".equals(href.trim()) || href.contains("javascript")) {
								continue;
							}

							if (!href.startsWith("http")) {
								String fixed_href = rootUrl_A.mRootUrl + "/" + href;
								fixed_href = fixed_href.replace("//", "/");
								if (!categoryUrl_B.mCategoryPageUrlList.contains(fixed_href)) {
									categoryUrl_B.nextPageUrl = fixed_href;
									categoryUrl_B.mCategoryPageUrlList.add(fixed_href);
									System.out.println("a[href]_No_Next_Tip  nextPageUrl=[" + fixed_href
											+ "]  innerHtml = " + innerHtml + "   href[" + href + "]");

									return;
								}

							}

						}

					}

				}

			}

		}

		void TryAnalysisHrefForDisplayUrl(RootUrl_A rootUrl_A, CategoryUrl_B categpryUrl_B, DisplayUrl_C displayUrl_C,
				String hrefUrl) {

			// 1_1_kcafalm.jpg 第一个详情页面的第1个照片 第一个1 一定对应了一个详情的地址
			// 1_2_xafafma.jpg
			// 打开 对应的 a href 获取 源码
			String detailContentHtmlCode = getHtmlSource(hrefUrl);
			if (detailContentHtmlCode == null) {
				System.out.println("当前内容详情页面 中的 hrefUrl= " + hrefUrl + " 解析出来的源文件为空!! ");
				return;
			}
			org.jsoup.nodes.Document curDocument = Jsoup.parse(detailContentHtmlCode);

//		Elements  mElements = 	curDocument.getElementsByTag("img");
			Elements mElements = curDocument.select("img[src]");

			if (mElements != null && mElements.size() > 0) {

				System.out.println("img[src] mElements.size()  = " + mElements.size());
				Iterator<org.jsoup.nodes.Element> element_iterator = mElements.iterator();
				int index_image_In_Detailurl = 1;
				while (element_iterator.hasNext()) {
					org.jsoup.nodes.Element curElement = element_iterator.next();
					String src = curElement.attr("abs:src");
					System.out.println("img[" + index_image_In_Detailurl + "] all[" + mElements.size() + "] src=[ "
							+ src + " ] href=[ " + hrefUrl + " ]");

					if (!allDownloadedUrlList.contains(src)) { // 避免 重复下载 url

						allDownloadedUrlList.add(src); // 怎么实现 url 和 它的 下载的页面 对应?
						addImgSrcToDetailMap(hrefUrl, src);
						index_image_In_Detailurl++;
					}

				}

				// 开始执行下载操作
				TryDownloadImageOperation(hrefUrl);
			}

		}

		int mDisplayUrl_ID = 0;

		int getNextDisplayUrl_ID() {
			mDisplayUrl_ID++;
			return mDisplayUrl_ID;

		}

		// 开始执行下载操作
		void TryDownloadImageOperation(String hrefUrl) {
			ArrayList<String> mImageUrlList = mDisplayUrl_ImageUrlList_Map.get(hrefUrl);
			if (mImageUrlList == null || mImageUrlList.size() == 0) {
				System.out.println("当前 详情页面 url=" + hrefUrl + "   解析到的图片资源数量 为0 !!! ");
				return;
			}

			int urlIndex = -1;
			if (mDisplayUrl_Index_Map.containsValue(hrefUrl)) {
				urlIndex = (Integer) mDisplayUrl_Index_Map.get(hrefUrl);
			} else {
				int DisplayID = getNextDisplayUrl_ID();
				mDisplayUrl_Index_Map.put(hrefUrl, DisplayID);
				urlIndex = DisplayID;
			}
			System.out.println("hrefUrl[" + hrefUrl + "]  mImageUrlList.size()=" + mImageUrlList.size());

			for (int i = 0; i < mImageUrlList.size(); i++) {
				String urlItem = mImageUrlList.get(i);
				int imageIndex_InDetail = i;
				int Detail_Index = urlIndex;
				String imageName_Pre = Detail_Index + "_" + imageIndex_InDetail + "_";
				String urlType = urlItem.substring(urlItem.lastIndexOf("."));
				if (urlType == null) {
					urlType = ".jpg"; // 默认为 .jpg
				}
				String fileName_NoType_ABS = ImageDownloadDir.getAbsolutePath() + File.separator + imageName_Pre
						+ getTimeStamp() + urlType;
				fileName_NoType_ABS = fileName_NoType_ABS.replace("?", "").replace("&", "");

				File imageFile = new File(fileName_NoType_ABS);
				downloadOperation(urlItem, imageFile, 1);

			}

		}

		// 视频的保存 目录 不能是 当前文件 否则 就会执行 同步操作 影响网速 repeatTimes 下载失败重复的次数
		@SuppressWarnings("unchecked")
		public void downloadOperation(String httpUrl, File localFile, int repeatTimes) {
			String fileAddress = localFile.getAbsolutePath();
//			String fileAddress = mDownloadedMonthDir.getAbsolutePath() + File.separator
//					+ (source == null || "".equals(source) ? "" : source + "_") + (fileNameNoPoint.replace(" ", ""))
//					+ "_" + index + "_" + getTimeStamp() + ".mp4";

			System.out.println("下载操作:[ " + httpUrl + " ]   \n fileAddress:" + fileAddress);
			int byteRead;
			try {
				URL url = new URL(httpUrl);
				// 获取链接
				URLConnection conn = url.openConnection();
				// 输入流
				InputStream inStream = conn.getInputStream();
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
				while ((byteRead = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteRead);
				}
				inStream.close();
				fs.close();
				System.out.println("\n-----url[ " + httpUrl + " ]下载完成-----\n" + fileSavePath.getAbsolutePath());

			} catch (FileNotFoundException e) {
				System.out.println("ZFileNotFoundException==" + e.getMessage());
			} catch (IOException e) {
				System.out.println("ZIOException:" + e.getMessage());
				if (repeatTimes <= 3) {
					downloadOperation(httpUrl, localFile, repeatTimes + 1); // 再次执行 下载操作

				}
			}
		}

		void addImgSrcToDetailMap(String keyurl, String valueUrl) {
			ArrayList<String> mImageUrlList = mDisplayUrl_ImageUrlList_Map.get(keyurl);
			if (mImageUrlList == null) {
				ArrayList<String> mImageUrlList_filled = new ArrayList<String>();
				mImageUrlList_filled.add(valueUrl);
				mDisplayUrl_ImageUrlList_Map.put(keyurl, mImageUrlList_filled);
			} else {
				if (!mImageUrlList.contains(valueUrl)) {
					mImageUrlList.add(valueUrl);
				}

			}

		}

		String getHtmlSource(String url) {
			String htmlSource = null;
			if (url == null || "".equals(url)) {
				return null;
			}

			File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

			System.setProperty("webdriver.chrome.driver", ChromeDriverFile.getAbsolutePath());

//			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
//			// 驱动位置
//			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");
//			CUR_CHROME_OPTIONS.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//			ChromeDriver driver = new ChromeDriver(CUR_CHROME_OPTIONS);

			ChromeDriver driver = mChromeDriver;

			int loop_index = 0;
			try {
				long waitTime = 2000;
				long timeout = 12_000;

				driver.get(url);

//				 long waitTime = Double.valueOf(Math.max(3, Math.random() * 5) * 1000).longValue();
				TimeUnit.MILLISECONDS.sleep(waitTime);

				// 循环下拉，直到全部加载完成或者超时
				do {
					new Actions(driver).sendKeys(Keys.END).perform();
					TimeUnit.MILLISECONDS.sleep(waitTime);

					timeout -= waitTime;
					loop_index++;
				} while (!driver.getPageSource().contains("已经到底部，没有新的内容啦") && timeout > 0);
				System.out.println("getHtmlSource 已经到底部，没有新的内容啦");
				htmlSource = driver.getPageSource();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("getHtmlSource 获取网页源码的时候出错  url = " + url);
				e.printStackTrace();

			} finally {
//				driver.close();

			}

			return htmlSource;

		}

		ArrayList<String> calcul_categoryurl_from_html_CategoryB(RootUrl_A mRootUrl_A, CategoryUrl_B mCategoryUrl_B,
				String linkA_text, String categoryFirstPageHtmlCode) {
			ArrayList<String> result_href_List = null;

			ArrayList<String> fixed_href_List = new ArrayList<String>();
			// 6.Jsoup解析html
			org.jsoup.nodes.Document document = Jsoup.parse(categoryFirstPageHtmlCode);

			Elements mElements = document.getElementsByTag("a");
			if (mElements != null && mElements.size() > 0) {

				System.out.println("a_link mElements.size()  = " + mElements.size());
				Iterator<org.jsoup.nodes.Element> element_iterator = mElements.iterator();
				int index_a_link = 1;
				while (element_iterator.hasNext()) {
					org.jsoup.nodes.Element curElement = element_iterator.next();
					String mHtml = curElement.outerHtml();
					// 当前的 a 的标签 应该 包含 关键词 a 以及 href

					System.out.println("_____CategoryUrlB_____a[" + index_a_link + "] all[" + mElements.size()
							+ "]   mHtml=" + mHtml);

					index_a_link++;

					if (mHtml.contains(linkA_text) && mHtml.contains("href")) {
						String hrefValue_RawStr = mHtml.substring(mHtml.indexOf("href=\""));
						hrefValue_RawStr = hrefValue_RawStr.replace("href=\"", "");
						hrefValue_RawStr = hrefValue_RawStr.replace("href=", "");
						String hrefValue_Fixed = hrefValue_RawStr.substring(0, hrefValue_RawStr.indexOf("\""));
						// 把 amp; 转为 空
						hrefValue_Fixed = hrefValue_Fixed.replace("amp;", "");

						if (!hrefValue_Fixed.startsWith("http")) {
							hrefValue_Fixed = mRootUrl_A.mRootUrl + "/" + hrefValue_Fixed;
						}
						fixed_href_List.add(hrefValue_Fixed);

					}

				}

			} else {
				System.out.println("当前的 url " + mRootUrl_A.mRootUrl + " 的源码中 没有找到 标签<a> 的 内容");
				System.out.println("源码htmlCode Begin _________________________________");
				System.out.println(mCategoryUrl_B.mPageUrl_HtmlStr_Map.size());
				System.out.println("源码htmlCode Endxx _________________________________");
			}

			if (fixed_href_List.size() > 0) {
				System.out.println("fixed_href_List  = " + fixed_href_List.size());
				return fixed_href_List;
			} else {
				System.out.println("fixed_href_List  = 0");
				return result_href_List;
			}

		}

		String calcul_categoryurl_from_html_RoouUrlA(RootUrl_A mRootUrl_A, String linkA_text) {
			String result_href = null;

			// 6.Jsoup解析html
			org.jsoup.nodes.Document document = Jsoup.parse(mRootUrl_A.mHtmlStr);

			Elements mElements = document.getElementsByTag("a");
			if (mElements != null && mElements.size() > 0) {

				System.out.println("getElementsByTag[a]   mElements.size()  = " + mElements.size() + " linkA_text["
						+ linkA_text + "]");
				Iterator<org.jsoup.nodes.Element> element_iterator = mElements.iterator();
				int index_a_link = 1;
				while (element_iterator.hasNext()) {
					org.jsoup.nodes.Element curElement = element_iterator.next();
					String mHtml = curElement.outerHtml();
					// 当前的 a 的标签 应该 包含 关键词 a 以及 href

					System.out.println(
							"___RootUrlA___a[" + index_a_link + "] all[" + mElements.size() + "]   mHtml=" + mHtml);

					index_a_link++;

					if (mHtml.contains(linkA_text) && mHtml.contains("href")) {
						String hrefValue_RawStr = mHtml.substring(mHtml.indexOf("href=\""));
						hrefValue_RawStr = hrefValue_RawStr.replace("href=\"", "");
						String hrefValue_Fixed = hrefValue_RawStr.substring(0, hrefValue_RawStr.indexOf("\""));
						// 把 amp; 转为 空
						hrefValue_Fixed = hrefValue_Fixed.replace("amp;", "");

						if (!hrefValue_Fixed.startsWith("http")) {
							hrefValue_Fixed = mRootUrl_A.mRootUrl + "/" + hrefValue_Fixed;
						}

						// 最后一个匹配的 符合条件
						result_href = hrefValue_Fixed;
//					return hrefValue_Fixed;
					}

				}

			} else {
				System.out.println("当前的 url " + mRootUrl_A.mRootUrl + " 的源码中 没有找到 标签<a> 的 内容");
				System.out.println("源码htmlCode Begin _________________________________");
				System.out.println(mRootUrl_A.mHtmlStr);
				System.out.println("源码htmlCode Endxx _________________________________");
			}

			return result_href;

		}

	}

	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
//     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

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

	public static int download_failed_time = 0;

	public enum EmailTypeEnum {
		NEW_EMAIL, REPLY
	}

	static class EmailInfo {
		private String subject;
		private String sender;
		private String senderName;
		private Date sendAt;
		private String body;
		private String messageId;
		private String replyMessageId;
		private String sourceFileKey;
		private String ticketId;
		private String ticketTableName;
		private EmailTypeEnum emailTypeEnum;
		private ArrayList<File> attachments;

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}

		public String getSenderName() {
			return senderName;
		}

		public void setSenderName(String senderName) {
			this.senderName = senderName;
		}

		public Date getSendAt() {
			return sendAt;
		}

		public void setSendAt(Date sendAt) {
			this.sendAt = sendAt;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public List<File> getAttachments() {
			return attachments;
		}

		public void setAttachments(ArrayList<File> attachments) {
			this.attachments = attachments;
		}

		public String getMessageId() {
			return messageId;
		}

		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}

		public String getSourceFileKey() {
			return sourceFileKey;
		}

		public void setSourceFileKey(String sourceFileKey) {
			this.sourceFileKey = sourceFileKey;
		}

		public String getReplyMessageId() {
			return replyMessageId;
		}

		public void setReplyMessageId(String replyMessageId) {
			this.replyMessageId = replyMessageId;
		}

		public String getTicketId() {
			return ticketId;
		}

		public void setTicketId(String ticketId) {
			this.ticketId = ticketId;
		}

		public String getTicketTableName() {
			return ticketTableName;
		}

		public void setTicketTableName(String ticketTableName) {
			this.ticketTableName = ticketTableName;
		}

		public EmailTypeEnum getEmailTypeEnum() {
			return emailTypeEnum;
		}

		public void setEmailTypeEnum(EmailTypeEnum emailTypeEnum) {
			this.emailTypeEnum = emailTypeEnum;
		}

		@Override
		public String toString() {
			return "EmailInfo [subject=" + subject + ", sender=" + sender + ", senderName=" + senderName + ", sendAt="
					+ sendAt + ", body=" + body + ", messageId=" + messageId + ", replyMessageId=" + replyMessageId
					+ ", sourceFileKey=" + sourceFileKey + ", ticketId=" + ticketId + ", ticketTableName="
					+ ticketTableName + ", emailTypeEnum=" + emailTypeEnum + ", attachments=" + attachments + "]";
		}

	}

	public static class EmailContext {
		private List<EmailInfo> emailInfos;
		private javax.mail.Message[] messages;
		private javax.mail.Folder folder;

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < messages.length; i++) {
				sb.append(messages[i].toString());
			}
			return "EmailContext [emailInfos=" + emailInfos + ", messages=[" + sb.toString() + "]" + ", folder="
					+ folder + "]";
		}

		public List<EmailInfo> getEmailInfos() {
			return emailInfos;
		}

		public void setEmailInfos(List<EmailInfo> emailInfos) {
			this.emailInfos = emailInfos;
		}

		public javax.mail.Folder getFolder() {
			return folder;
		}

		public void setFolder(javax.mail.Folder folder) {
			this.folder = folder;
		}

		public javax.mail.Message[] getMessages() {
			return messages;
		}

		public void setMessages(javax.mail.Message[] messages) {
			this.messages = messages;
		}
	}

	class Monitor_WeChatFile_ForWindows_Rule_39 extends Basic_Rule {
// C:\Users\zukgit\Documents\WeChat Files\xxxx\FileStorage\File\2021-07

		ArrayList<File> searchRootFileList; // 存放要搜索的 txt文件的 根目录 目前只有 \WeChat Files\和\Tencent Files\
		File mWeChatRootFile; // C:\Users\xx\Documents\WeChat Files\ 目录 微信使用
		File mTencentRootFile; // C:\Users\xx\Documents\Tencent Files\ 目录 QQ使用
		File mLastTxtFile; // 最新的 TXT 文件
		ArrayList<String> urlStrList; // url 字符串列表

		
		//
		int mMailFirstMinute = 1;
		int mMailInteval = 2;
		int last_email_count = 0; // 最新的 email的数量
		HashMap<Integer, ArrayList<EmailInfo>> minute_mailListMap; // key 是当前的分钟的值 value 是当前接收到 cmdermail cmdmail 邮件的集合

		File mDownloadedRootFile;
		File mDownloadedMonthDir; // 在 G2_Monitor_Download/YYYYMM/ 年年年年月月的 目录文件

		File ChromeDriverFile; // G2_chromedriver_v91.exe 下载头条视频时会用到

		ArrayList<File> curAlredyDoTxtFileList; // 当前已经执行了 检测处理的 txt文件 列表 持续增加

		// 下载的视频 是否 以 MD5 进行命名
		boolean isMDName = false;

		boolean isBootUp_VBS_Start = false; // 是否是开机 vbs 启动的进程

		boolean isLogFile = false;
		StringBuilder mLogSB;

		ArrayList<String> mOneTimeLogList; // 一次运行的Log

		File mLogFile;

		// zcmd_run_[]//
		ArrayList<String> zcmdRunCommandList; // 在 文件中 识别出的 zcmd_run_的命令的集合
		
		ArrayList<String> zMailCmdRunCommandList; //  在 邮件中 读取到的 执行的命令的集合 
		ArrayList<EmailInfo> zAlreadRunMailCommandList; //  执行的命令的集合 以及 时间戳  执行过的 就不执行了 
		

		File cmderExePath; // cmder.exe 文件的绝对路径 使得程序 能再 cmder.exe上执行程序 有环境变量的优势
		// 写入的 需要 cmder.exe 需要执行的 命令的 集合 使用 && 来串连
		File cmder_prexe_bat_file; // C:\Users\zhuzj5\Desktop\zbin\win_zbin\zcmder_prexe_G2.bat

		Monitor_WeChatFile_ForWindows_Rule_39() {
			super("#", 39, 3); // 不包括
			urlStrList = new ArrayList<String>();
			mDownloadedRootFile = new File(zbinPath + File.separator + "G2_Monitor_Download");
			mDownloadedMonthDir = new File(
					mDownloadedRootFile.getAbsolutePath() + File.separator + getTimeStamp_YYYYMM());
			ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");
			curAlredyDoTxtFileList = new ArrayList<File>();
			searchRootFileList = new ArrayList<File>();
			isLogFile = false;
			mLogSB = new StringBuilder();
			mLogFile = new File(zbinPath + File.separator + "win_zbin" + File.separator + "zrule_apply_G2_39rule.log");
			mOneTimeLogList = new ArrayList<String>();
			// cmderExePath 是写死的
			zMailCmdRunCommandList = new ArrayList<String>();
			minute_mailListMap = new HashMap<Integer, ArrayList<EmailInfo>>();
			last_email_count = 0;
			zAlreadRunMailCommandList = new ArrayList<EmailInfo>();
		}

		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			for (int i = 0; i < inputParamList.size(); i++) {

				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();

				if ("mdname_true".equals(paramItem_lower_trim)) {
					isMDName = true;
				}

				if ("bootup_true".equals(paramItem_lower_trim)) {
					isBootUp_VBS_Start = true;
				}

				if ("logfile_true".equals(paramItem_lower_trim)) {
					isLogFile = false;
				}

			}

			System.out.println(
					"isLogFile=" + isLogFile + "   isbootup=" + isBootUp_VBS_Start + "   isMDName=" + isMDName);

			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		/*
		 * @Override boolean initParams4InputParam(String inputParam) {
		 * System.out.println("inputParam = " + inputParam + "  curDirPath = " +
		 * curDirPath); if (curDirPath == null) { System.out.println("当前的Shell 路径为空！！！ "
		 * + "inputParam = " + inputParam + "  curDirPath = " + curDirPath); return
		 * false; } String shellAbsPath = curDirPath;
		 * 
		 * String doucumentPath = System.getProperties().getProperty("user.home") +
		 * File.separator + "Documents"; String wechatPathPre =
		 * System.getProperties().getProperty("user.home") + File.separator +
		 * "Documents" + File.separator + "WeChat Files";
		 * 
		 * String tecentAbsFile = System.getProperties().getProperty("user.home") +
		 * File.separator + "Documents" + File.separator + "Tencent Files";
		 * 
		 * File wechatDocumentDirFile = new File(wechatPathPre); File
		 * tecentDocumentDirFile = new File(tecentAbsFile);
		 * 
		 * 
		 * if (!shellAbsPath.startsWith(doucumentPath)) { System.out.println(
		 * "当前的Shell 路径不是 Document["+doucumentPath+"] 下的路径 的目录 ！！！ " + "inputParam = " +
		 * inputParam + "  curDirPath = " + curDirPath);
		 *
		 * return false; }
		 * 
		 * 
		 * File shellFile = new File(curDirPath); String shellDirName =
		 * shellFile.getName(); String shellDirName_clearBlank =
		 * shellDirName.replace("-", ""); // 2021-07 String shellFileAbsPath =
		 * shellFile.getAbsolutePath();
		 * 
		 * String wechatRootPath = System.getProperties().getProperty("user.home") +
		 * File.separator + "Documents" + File.separator + "WeChat Files";
		 * 
		 * String now_yyyymm = getTimeStamp_YYYYMM(); //
		 * C:\Users\zhuzj5\Documents\WeChat Files
		 * 
		 * if (!now_yyyymm.equals(shellDirName_clearBlank)) {
		 * System.out.println("当前的Shell是 WeChat的目录  但不是最新月份下的目录 ！！！ " + "inputParam = "
		 * + inputParam + "  curDirPath = " + curDirPath);
		 * System.out.println("最新目录结果类似于: " +
		 * " C:\\Users\\zukgit\\Documents\\WeChat Files\\xxxx\\FileStorage\\File\\2021-07"
		 * ); return false; }
		 * 
		 * 
		 * // 如果不是wechat的目录 那么提示 路径不对 // if
		 * (!shellFileAbsPath.startsWith(wechatRootPath)) { //
		 * System.out.println("当前的Shell是 WeChat的目录 下的目录 ！！！ " + "inputParam = " +
		 * inputParam // + "  curDirPath = " + curDirPath +"   wechatRootPath = "+
		 * wechatRootPath +"   shellFileAbsPath="+ shellFileAbsPath ); //
		 * System.out.println("最新目录结果类似于: " // +
		 * " C:\\Users\\zukgit\\Documents\\WeChat Files\\xxxx\\FileStorage\\File\\2021-07"
		 * ); // return false; // } // if (!wechatDocumentDirFile.exists() &&
		 * !tecentDocumentDirFile.exists()) {
		 * 
		 * System.out.println("当前 Document目录[" + doucumentPath + "] "); return false; }
		 * 
		 * if (!mDownloadedRootFile.exists()) { mDownloadedRootFile.mkdirs(); }
		 * 
		 * if (!mDownloadedMonthDir.exists()) { mDownloadedMonthDir.mkdirs(); }
		 * 
		 * if (!mDownloadedMonthDir.exists()) { System.out.println(
		 * "当前文件下载保存路径不存在_请检查这个保存文件的目录 mDownloadedMonthDir = " +
		 * mDownloadedMonthDir.getAbsolutePath()); return false;
		 * 
		 * }
		 * 
		 * mWeChatRootFile = wechatDocumentDirFile; mTencentRootFile =
		 * tecentDocumentDirFile;
		 * 
		 * if (mWeChatRootFile.exists()) { searchRootFileList.add(mWeChatRootFile);
		 * System.out.println("当前 WeChat目录存在 将持续为您监听"); } else {
		 * System.out.println("当前 WeChat目录不存在   !!! File Not Exist =" +
		 * mWeChatRootFile.getAbsolutePath()); }
		 * 
		 * if (mTencentRootFile.exists()) { searchRootFileList.add(mTencentRootFile);
		 * System.out.println("当前 Tecent[QQ] 目录存在 将持续为您监听"); } else {
		 * System.out.println("当前 Tecent[QQ]目录不存在   !!! File Not Exist =" +
		 * mTencentRootFile.getAbsolutePath());
		 * 
		 * }
		 * 
		 * mLastTxtFile = calLastTxtFileInFileList(searchRootFileList);
		 * curAlredyDoTxtFileList = getAllSubFileInFileList(searchRootFileList, ".txt");
		 * 
		 * // File[] fileArr = mWeChatRootFile.listFiles(); // if (fileArr == null ||
		 * fileArr.length == 0) { // System.out.println("当前目录文件为空,将休眠1分钟后继续监测!!"); // //
		 * } else { // mLastTxtFile = calLastTxtFileInList(mWeChatRootFile); //
		 * curAlredyDoTxtFileList = getAllSubFile(mWeChatRootFile,".txt"); // }
		 * 
		 * return super.initParams4InputParam(inputParam); }
		 */

		String getSearchFileTip(ArrayList<File> searchFileList) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < searchFileList.size(); i++) {
				File searchFile = searchFileList.get(i);
				sb.append(searchFile.getAbsolutePath() + "_____");
			}
			return sb.toString();

		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			int minute_count = 0;
			String searchFileTip = getSearchFileTip(searchRootFileList);
			if (true) {

				while (true) {

					try {
						File lastTxtFile = calLastTxtFileInFileList(searchRootFileList);
						if (lastTxtFile == null) {
							mLastTxtFile = null;
							System.out.println("当前搜索目录[" + searchFileTip + "] mailFirst["+mMailFirstMinute+"]"+"mailInteval["+mMailInteval+"]"+"没有Txt文件!!  睡眠1分钟后继续检测！！！ ");
						} else if (mLastTxtFile != null
								&& lastTxtFile.getAbsolutePath().equals(mLastTxtFile.getAbsolutePath())) {

							System.out
									.println("当前搜索目录[" + searchFileTip + "] mailFirst["+mMailFirstMinute+"] mailInteval["+mMailInteval+"] 没有Txt文件!!!!  没有产生最新的Txt文件   睡眠1分钟后继续检测！！！ ");
						} else if (lastTxtFile != null && lastTxtFile != mLastTxtFile) {
							System.out.println("当前检测到最新的 TXT 文件  lastTxtFile = " + lastTxtFile.getName()
									+ " 创建新线程 打印内容！！！ 【lastTxtFile == mLastTxtFile】==【" + (lastTxtFile == mLastTxtFile)
									+ "】   lastTxtFile=" + lastTxtFile.getAbsolutePath() + "  mLastTxtFile="
									+ mLastTxtFile.getAbsolutePath());
							mLastTxtFile = lastTxtFile;
							NewFileOperation(mLastTxtFile, minute_count);
						} else {
							System.out.println("什么情况" + "   lastTxtFile=" + lastTxtFile.getAbsolutePath()
									+ "  mLastTxtFile=" + mLastTxtFile.getAbsolutePath());
						}

			

						// ================== 检测 cmdmail 邮件 Begin ================

						
						Receive_Monitor_Mail_Operation(minute_count);

						// ================== 检测 cmdmail 邮件 End ================

						// 第3分钟 以及 每隔 30分钟 发个邮件 ?
						int minutes_inteval = mMailInteval; // 发邮件的间隔  2  
						int first_send_miute = mMailFirstMinute; // 起始发送文件的分钟数 1
						if (minute_count == first_send_miute
								|| (minute_count != 0 && minute_count % minutes_inteval == 0)) {
							if (isBootUp_VBS_Start) { //
								Send_Monitor_Email(minute_count, first_send_miute, minutes_inteval, mOneTimeLogList,
										mLastTxtFile, mWeChatRootFile, mTencentRootFile, zcmdRunCommandList, zMailCmdRunCommandList ,
										searchRootFileList, curAlredyDoTxtFileList);
								// 发送邮件
							}
							mOneTimeLogList.clear();
						}

						System.out.println("══════════════════ " + "Minute[" + minute_count + "] mailFirst["+mMailFirstMinute+"] mailInteval["+mMailInteval+"]:" + getTimeStamp()
						+ " ════════ mLastTxtFile=" + (mLastTxtFile == null ? "null" : mLastTxtFile.getName()));
						
						Thread.sleep((long) ONE_MINUTES_MILLSECOND);
						minute_count++;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		int curUrlIndex_InTxtFile;

		//
		void Receive_Monitor_Mail_Operation(int minute_count) {
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					EmailContext emalContext = null;
					try {
						emalContext = receive_mailcmd_Context(minute_count, "imap", "imap.qq.com", "382581427@qq.com",
								"kyioxkexvqdtbjhd");

						if (minute_mailListMap.get(minute_count) != null && minute_mailListMap.get(minute_count).size() > 0) {
							ArrayList<EmailInfo> match_minutes_MailList = minute_mailListMap.get(minute_count);

							System.out.println("match_minutes_MailList.size() = "+ match_minutes_MailList.size());
							for (int i = 0; i < match_minutes_MailList.size(); i++) {

								EmailInfo emailInfo = match_minutes_MailList.get(i);
								String mSubject = emailInfo.getSubject();

								System.out.println("AA_Receive_Monitor_Mail_Operation  minute_count=" + minute_count + " mail[" + i
										+ "][" + match_minutes_MailList.size() + "]  Subkect= " + mSubject);
								
								if(!isEmailInfoExist(zAlreadRunMailCommandList,emailInfo)) {
									zAlreadRunMailCommandList.add(emailInfo);
									exe_cmd_email_operation(minute_count, emailInfo);
								}else {
									
									System.out.println("当前已经存在相同的 EmailInfoList emailInfo="+emailInfo);
								}
								
								
								System.out.println("BB_Receive_Monitor_Mail_Operation  minute_count=" + minute_count + " mail[" + i
										+ "][" + match_minutes_MailList.size() + "]  Subkect= " + mSubject);

							}

						}else {
							System.out.println("match_minutes_MailList.size() = 0 null  minute_count="+minute_count);
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
			
			
			
			


		}

		
		boolean isEmailInfoExist(ArrayList<EmailInfo> usedEmailList , EmailInfo curEmail) {
			boolean isExist = false;
			
			for (int i = 0; i < usedEmailList.size(); i++) {
				
				EmailInfo  mEmailInfo = 	usedEmailList.get(i);
				
				if(mEmailInfo.body.equals(curEmail.body)  && mEmailInfo.subject.equals(curEmail.subject)) {
					return true;
				}
				
			}
			
			
			
			return isExist;
			
			
			
		}
		void exe_cmd_email_operation(int minutes_count, EmailInfo emailInfo) {
			String mSubject = emailInfo.getSubject();
		
			String task_identify = minutes_count+"_"+mSubject;
			System.out.println("exe_cmd_email_operation  minutes_count="+minutes_count+"    task_identify="+task_identify+"的任务!    containFlag=");
			mLogSB.append("_______________mailcmder_run_[minute:" + minutes_count + "]  subject[" + mSubject
					+ "] Begin ____");

			System.out.println("_______ exe_cmd_email_operation ________mailcmder_run_[minute:" + minutes_count + "]  subject[" + mSubject
					+ "] Begin ____");
//			mSubject    // mailcmd_zhuzj5_run_【afafafa】 %win_zbin%/zrule_apply_G2.bat #_48 voice_12345678900987654321

			StringBuilder tipSb = new StringBuilder();

			if (mSubject.contains("mailcmd_") && mSubject.contains("【") && mSubject.contains("】")) {

				String command_pre_flag = "mailcmd_";
				String userName_raw = mSubject.substring(mSubject.indexOf("【"));
				String userName_fix = userName_raw.replace(command_pre_flag, "").replace("_run_", "").trim();

				String command = mSubject.substring(mSubject.indexOf("】") + 1).trim();

				System.out.println(
						"minutes_count=" + minutes_count + "   userName_fix=" + userName_fix + "   cmdercommand=" + command);

		
				String result = "";
				new Thread(new Runnable() {   // 让 子线程  去执行  否则 卡住 主线程
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
					
						zMailCmdRunCommandList.add(command);
						String result = execCMD(command);
						System.out.println("execCMD command【"+command+"】 result="+result);

					}
				}).start();
		
				System.out.println("minutes_count=" + minutes_count + "   userName_fix=" + userName_fix
						+ "   cmder_command=" + command + "  result=" + result);

			}else {
				
				System.out.println(
						"AA minutes_count=" + minutes_count + "   Subject_command=" + mSubject  );
			}

			if (mSubject.contains("mailcmder_") && mSubject.contains("【") && mSubject.contains("】")) {
				String command_pre_flag = "mailcmder_";
				String userName_raw = mSubject.substring(mSubject.indexOf("【"));
				String userName_fix = userName_raw.replace(command_pre_flag, "").replace("_run_", "").trim();
				String command = mSubject.substring(mSubject.indexOf("】") + 1).trim();

				System.out.println("minutes_count=" + minutes_count + "   userName_fix=" + userName_fix
						+ "   cmd_command=" + command);
			
				String result = "";
				new Thread(new Runnable() { // 让 子线程  去执行  否则 卡住 主线程
		
					@Override
					public void run() {
						// TODO Auto-generated method stub
					
						zMailCmdRunCommandList.add(command);
						String result = run_cmder(command); // cmd 运行
						System.out.println("run_cmder  command【"+command+"】 result="+result);
					}
				}).start();
				
				

				System.out.println("minutes_count=" + minutes_count + "   userName_fix=" + userName_fix
						+ "   cmdcommand=" + command + " result=" + result);

			}else {
				
				System.out.println(
						"BB minutes_count=" + minutes_count + "   Subject_command=" + mSubject  );
			}

			mLogSB.append(
					"_______________mailcmder_run_[minute:" + minutes_count + "]  subject[" + mSubject + "] End ____");
			System.out.println(
					"_______________mailcmder_run_[minute:" + minutes_count + "]  subject[" + mSubject + "] End ____");

		}

		/**
		 * 接收邮件
		 */
		public EmailContext receive_mailcmd_Context(int minutes, String protocol, String host, String username,
				String password) throws Exception {
			// 读取收件箱
			System.out.println("receive_CmdMail 方法开始! AA");
			EmailContext emailContext = readInbox(protocol, host, username, password);
			System.out.println("receive_CmdMail 方法结束! BB  ");
			if (emailContext == null) {
				return null;
			}
			// 处理邮件
			ArrayList<EmailInfo> result = parseCmdMessage(emailContext.messages);
			emailContext.setEmailInfos(result);
			minute_mailListMap.put(minutes, result);
			System.out.println("receive_CmdMail CC  ");
			return emailContext;
		}

		/**
		 * 解析邮件
		 * 
		 * @param allMessages 要解析的邮件列表
		 */
		public ArrayList<EmailInfo> parseCmdMessage(javax.mail.Message... allMessages) throws MessagingException {
			if (allMessages == null || allMessages.length < 1) {
				throw new MessagingException("未找到要解析的邮件!");
			}

			ArrayList<EmailInfo> mCmdMailInfoList = new ArrayList<EmailInfo>();

			// 解析所有邮件
			System.out.println("parseMessage 解析邮件begin  messages.length = " + allMessages.length);
			int all_eamil_count = allMessages.length;
			int receive_mail_count = 0;
			System.out.println("messages.length = " + allMessages.length);
			if (last_email_count == 0) { // 首次监听邮件时候 邮件长度为0

				last_email_count = all_eamil_count;
				System.out.println("当前的邮件长度为: " + last_email_count);
				receive_mail_count = allMessages.length - last_email_count;
			} else {

				receive_mail_count = all_eamil_count - last_email_count;

				System.out.println("当前静默期间 收到 receive_mail_count=" + receive_mail_count + "封邮件!");

			}

			System.out.println("当前的需要读取的邮件长度为: receive_mail_count=" + receive_mail_count);

			if (receive_mail_count <= 0) {

				System.out.println("当前静默期间 收到的邮件为0  receive_mail_count=" + receive_mail_count + "  继续静默!");
				return mCmdMailInfoList;
			}

			// mailcmd_zhuzj5_xxxxxxxxxx
			// mailcmder_gjh123_xxxxxxxxxx

			for (int i = allMessages.length - receive_mail_count; i < allMessages.length; i++) {
				if (!allMessages[i].getFolder().isOpen()) {
					allMessages[i].getFolder().open(javax.mail.Folder.READ_WRITE);
				}
				MimeMessage msg = (MimeMessage) allMessages[i];
				EmailInfo emailInfo = new EmailInfo();
				try {

				
					
					String mSubject_Content = msg.getSubject();
					System.out.println("当前的需要读取的邮件长度为: AA receive_mail_count=" + receive_mail_count +" mSubject_Content="+mSubject_Content);
					if (mSubject_Content == null || "".equals(mSubject_Content)) {
						continue;
					}
					System.out.println("当前的需要读取的邮件长度为: BB receive_mail_count=" + receive_mail_count +" mSubject_Content="+mSubject_Content);

					if (!(mSubject_Content.startsWith("mailcmd_") || mSubject_Content.startsWith("mailcmder_"))) {
						// 只 读取 特定 主题 subject 主题的 邮件
						continue;
					}
					System.out.println("当前的需要读取的邮件长度为: CC receive_mail_count=" + receive_mail_count +" mSubject_Content="+mSubject_Content);

					emailInfo.setSubject(msg.getSubject());
					emailInfo.setSender(getFrom(msg));
					emailInfo.setSenderName(getSenderName(msg));
					emailInfo.setSendAt(msg.getSentDate());
					emailInfo.setMessageId(msg.getMessageID());
					// 邮件源文件
					emailInfo.setSourceFileKey(saveSourceEmailFile(msg));
					emailInfo.setReplyMessageId(getHeader(msg, "In-Reply-To"));
					String body = getTextFromMessage(msg);
					body = obtainEmailType(emailInfo, body);

					try {
						System.out.println("当前的需要读取的邮件长度为: DD receive_mail_count=" + receive_mail_count +" mSubject_Content="+mSubject_Content);

						emailInfo.setBody(body.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (isContainAttachment(msg)) {
						emailInfo.setAttachments(saveAttachment(msg));
					}
					System.out.println("当前的需要读取的邮件长度为: EE receive_mail_count=" + receive_mail_count +" mSubject_Content="+mSubject_Content);

					mCmdMailInfoList.add(emailInfo);
					if (allMessages[i].getFolder().isOpen()) {
						allMessages[i].getFolder().close(true);
					}
					
					System.out.println("当前的需要读取的邮件长度为: FF receive_mail_count=" + receive_mail_count +" mSubject_Content="+mSubject_Content);

				} catch (Exception e) {
					System.out.println("读取邮件失败");
					e.printStackTrace();
				}
			}
			System.out.println("parseMessage 方法结束: List<EmailInfo>.size()  =" + mCmdMailInfoList.size());
			return mCmdMailInfoList;
		}

		/**
		 * 保存附件
		 *
		 * @param part 邮件中多个组合体中的其中一个组合体
		 * @throws UnsupportedEncodingException
		 * @throws MessagingException
		 * @throws IOException
		 */
		private ArrayList<File> saveAttachment(javax.mail.Part part)
				throws UnsupportedEncodingException, MessagingException, IOException {
			System.out.println(" hashCode :" + part.hashCode());

			ArrayList<File> attchFileList = new ArrayList<File>();

			if (part.isMimeType("multipart/*")) {
				javax.mail.Multipart multipart = (javax.mail.Multipart) part.getContent();
				int partCount = multipart.getCount();
				for (int i = 0; i < partCount; i++) {
					javax.mail.BodyPart bodyPart = multipart.getBodyPart(i);
					String disp = bodyPart.getDisposition();
					if (disp != null) {
						String fileName = decodeText(bodyPart.getFileName());
						System.out.println(" fileName :" + fileName);
						// 通过getInputStream方法下载附件
						InputStream attatchIns = bodyPart.getInputStream();

						File attchFile = new File(mDownloadedRootFile + fileName);
						FileOutputStream fos = new FileOutputStream(attchFile);
						byte[] b = new byte[1024];
						while ((attatchIns.read(b)) != -1) {
							fos.write(b);// 写入数据
						}

						attatchIns.close();
						fos.close();// 保存数据

						attchFileList.add(attchFile);

					}
				}
			}

			return attchFileList;
		}

		public String decodeText(String encodeText) throws UnsupportedEncodingException {
			if (encodeText == null || "".equals(encodeText)) {
				return "";
			} else {
				return MimeUtility.decodeText(encodeText).replaceAll("/", "_");
			}
		}

		// 查看是否包含附件
		public boolean isContainAttachment(javax.mail.Part part)
				throws MessagingException, IOException, InterruptedException {
			// 是否包含multipart内容
			if (part.isMimeType("multipart/*")) {
				// 转换content为MimeMultipart对象
				MimeMultipart multipart = (MimeMultipart) part.getContent();
				// 获取bodyPart的数量
				int partCount = multipart.getCount();
				for (int i = 0; i < partCount; i++) {
					javax.mail.BodyPart bodyPart = multipart.getBodyPart(i);
					// 返回bodyPart的配置
					String disp = bodyPart.getDisposition();
					// 是否包含附件
					if (disp != null && (disp.equalsIgnoreCase(javax.mail.Part.ATTACHMENT)
							|| disp.equalsIgnoreCase(javax.mail.Part.INLINE))) {
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * 提取邮件类型
		 * 
		 * @param emailInfo
		 * @param body
		 */
		public String obtainEmailType(EmailInfo emailInfo, String body) {
			if (body == null) {
				emailInfo.setEmailTypeEnum(EmailTypeEnum.NEW_EMAIL);
				return null;
			}
			if (body.contains("《hidden《") && body.contains("》hidden》")) {
				emailInfo.setEmailTypeEnum(EmailTypeEnum.REPLY);
				String startFlag = "《hidden《";
				int start = body.lastIndexOf(startFlag);
				int end = body.lastIndexOf("》hidden》");
				String info = body.substring(start + startFlag.length(), end);
				String[] split = info.split(":");
				if (split.length != 2) {
					emailInfo.setEmailTypeEnum(EmailTypeEnum.NEW_EMAIL);
				} else {
					emailInfo.setTicketId(split[0]);
					emailInfo.setTicketTableName(split[1]);
				}
			} else {
				emailInfo.setEmailTypeEnum(EmailTypeEnum.NEW_EMAIL);
			}
			return body;
		}

		public String getTextFromMessage(javax.mail.Message message) throws MessagingException, IOException {
			String result = "";
			if (message.isMimeType("text/plain") || message.isMimeType("text/html")) {
				result = message.getContent().toString();
			} else if (message.isMimeType("multipart/mixed")) {
				result = getTextFromMimeMultipart((javax.mail.Multipart) message.getContent());
			} else if (message.isMimeType("multipart/alternative")) {
				result = getTextFromMimeMultipart((javax.mail.Multipart) message.getContent());
			}
			return result;
		}

		public String getTextFromMimeMultipart(javax.mail.Multipart mimeMultipart)
				throws MessagingException, IOException {
			String result = "";
			int count = mimeMultipart.getCount();
			for (int i = 0; i < count; i++) {
				javax.mail.BodyPart bodyPart = mimeMultipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					result = result + bodyPart.getContent();
				} else if (bodyPart.isMimeType("text/html")) {
					String html = (String) bodyPart.getContent();
					result = result + html;
				} else if (bodyPart.getContent() instanceof javax.mail.Multipart) {
					result = result + getTextFromMimeMultipart((javax.mail.Multipart) bodyPart.getContent());
				} else if (bodyPart.isMimeType("text/html")) {
					String html = (String) bodyPart.getContent();
					result = result + html;
				} else if (bodyPart.getContent() instanceof javax.mail.Multipart) {
					result = result + getTextFromMimeMultipart((javax.mail.Multipart) bodyPart.getContent());
				}
			}
			return result;
		}

		public String saveSourceEmailFile(MimeMessage msg) {
			return "source_file_key";
		}

		public String getHeader(MimeMessage mimeMessage, String header) {
			String[] headers = new String[0];
			try {
				headers = mimeMessage.getHeader(header);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return headers != null && headers.length > 0 ? headers[0] : null;
		}

		// ----------------------------------------------------------------------------------------------

		/**
		 * 获得邮件主题
		 * 
		 * @param msg 邮件内容
		 * @return 解码后的邮件主题
		 */
		public String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {
			return MimeUtility.decodeText(msg.getSubject());
		}

		// ----------------------------------------------------------------------------------------------

		/**
		 * 获取发件人
		 * 
		 * @param msg
		 * @return
		 */
		public String getSenderName(MimeMessage msg) throws MessagingException {
			javax.mail.Address[] froms = msg.getFrom();
			if (froms.length < 1) {
				return "";
			}
			javax.mail.internet.InternetAddress address = (javax.mail.internet.InternetAddress) froms[0];
			return address.getPersonal();
		}

		// ----------------------------------------------------------------------------------------------

		/**
		 * 获得邮件发件人
		 * 
		 * @param msg 邮件内容
		 * @return 姓名 <Email地址>
		 * @throws MessagingException
		 * @throws UnsupportedEncodingException
		 */
		public String getFrom(MimeMessage msg) throws MessagingException {
			javax.mail.Address[] froms = msg.getFrom();
			if (froms.length < 1) {
				return "";
			}
			javax.mail.internet.InternetAddress address = (javax.mail.internet.InternetAddress) froms[0];
			return address.getAddress();
		}

		/**
		 * Flag 类型列举如下 Flags.Flag.ANSWERED 邮件回复标记，标识邮件是否已回复。 Flags.Flag.DELETED
		 * 邮件删除标记，标识邮件是否需要删除。 Flags.Flag.DRAFT 草稿邮件标记，标识邮件是否为草稿。 Flags.Flag.FLAGGED
		 * 表示邮件是否为回收站中的邮件。 Flags.Flag.RECENT 新邮件标记，表示邮件是否为新邮件。 Flags.Flag.SEEN
		 * 邮件阅读标记，标识邮件是否已被阅读。 Flags.Flag.USER 底层系统是否支持用户自定义标记，只读。
		 */
		public EmailContext readInbox(String protocol, String host, String user, String password) throws Exception {
			// 邮件配置类
			Properties properties = new Properties();
			javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);
			// session.setDebug(true);
			javax.mail.Store store = session.getStore(protocol);
			System.out.println("readInbox connect  方法开始! ");

			store.connect(host, user, password);
			System.out.println("readInbox connect 方法结束 ! ");

			// 获得用户的邮件帐户
			System.out.println("readInbox getFolder 方法开始 ! ");
			javax.mail.Folder folder = store.getFolder("INBOX");

			System.out.println("readInbox getFolder 方法结束 ! ");
			if (folder == null) {
				System.out.println("获取邮箱文件信息为空");
			}

			int message_count = folder.getMessageCount();
			System.out.println("获取邮箱文件信息数量  message_count = " + message_count);

			EmailContext emailContext = new EmailContext();
			// 设置对邮件帐户的访问权限可以读写
			folder.open(javax.mail.Folder.READ_WRITE);

			SearchTerm flagTerm = new FlagTerm(new javax.mail.Flags(javax.mail.Flags.Flag.RECENT), false);// 搜索未读邮件
			System.out.println("readInbox search 方法开始 ! ");
			javax.mail.Message[] messages = folder.search(flagTerm);
			System.out.println("readInbox search 方法结束 ! ");

			System.out.println("readInbox A  messages.length=" + messages.length);

			if (messages == null || messages.length == 0) {
				return null;
			}

			System.out.println("readInbox  B  messages=");

//		        for (int i = 0, count = messages.length; i < count; i++) {//将读取的邮件标位已读状态
//		            messages[i].setFlag(Flags.Flag.SEEN, true);
//		        }

			System.out.println("readInbox  C  messages="  );
			emailContext.setMessages(messages);
			emailContext.setFolder(folder);
			System.out.println("readInbox readInbox End   "  );

			return emailContext;
		}

		void Send_Monitor_Email(int minute_count, int first_send_minute, int minute_inteval, ArrayList<String> LogList,
				File lastTxtFile, File wechatDir, File qqDir, ArrayList<String> cmdList, ArrayList<String> mailCmdList, ArrayList<File> searchDirList,
				ArrayList<File> usedTxtFileList) {

			ArrayList<String> paramStrList = new ArrayList<String>();

			ArrayList<String> extraInfoList = new ArrayList<String>();

			mLogSB.append(
					"minute_count[" + minute_count + "] Begin_getWifiInfoList() " + getTimeStampyyyyMMdd_HHmmss());
			if (isLogFile) {
				writeContentToFile(mLogFile, mLogSB.toString());
			}
			ArrayList<String> wifiInfoList = getWifiInfoList();
//		ArrayList<String> wifiInfoList = 	new ArrayList<String>();

			if (wifiInfoList != null || wifiInfoList.size() == 0) {
				mLogSB.append(
						"minute_count[" + minute_count + "]_End_getWifiInfoList()=0  " + getTimeStampyyyyMMdd_HHmmss());
			} else {
				mLogSB.append("minute_count[" + minute_count + "]_End_getWifiInfoList()=" + wifiInfoList.size() + "  "
						+ getTimeStampyyyyMMdd_HHmmss());
			}

			if (isLogFile) {
				writeContentToFile(mLogFile, mLogSB.toString());
			}

			String pc_userName = System.getenv().get("USERNAME");// 获取用户名

			String mSubjectStr = "uptip" + "_" + pc_userName + "_[minute:" + minute_count + "][first:"
					+ first_send_minute + "][inteval:" + minute_inteval + "][lastTxt:" + (lastTxtFile != null?lastTxtFile.getName():"null") + "]"
					+ "_" + getTimeStamp_HHmmss();

			paramStrList.add("[USERNAME:" + pc_userName + "]");
			paramStrList.add("[isBootUp:" + isBootUp_VBS_Start + "]");
			paramStrList.add("[minute:" + minute_count + "]");
			paramStrList.add("[first_minute:" + first_send_minute + "]");
			paramStrList.add("[inteval:" + minute_inteval + "]");
			
			if(lastTxtFile  != null) {
				paramStrList.add("[lastTxt:" + lastTxtFile.getAbsolutePath() + "]" + "__"+ long2yyyyMMdd_HHmmss(lastTxtFile.lastModified()));

			}else {
				paramStrList.add("[lastTxt:" + lastTxtFile + "]" + "__");

			}
			
			if(wechatDir != null) {
				paramStrList.add("[wechatDir:" + wechatDir.getAbsolutePath() + "]");
			}else {
				paramStrList.add("[wechatDir:" + "null" + "]");
			}
	
			if(qqDir != null) {
				paramStrList.add("[qqDir:" + qqDir.getAbsolutePath() + "]");
			}else {
				paramStrList.add("[qqDir:" + "null" + "]");
			}
	
	
			if (cmdList == null || cmdList.size() == 0) {
				paramStrList.add("[cmdList.size():0]");
			} else {
				paramStrList.add("[cmdList.size():" + cmdList.size() + "]");
				
				for (int i = 0; i < cmdList.size(); i++) {
					paramStrList.add("[cmdList["+i+"]["+cmdList.size()+"]:" + cmdList.get(i)+ "]");
				}
			}
			
			if (mailCmdList == null || mailCmdList.size() == 0) {
				paramStrList.add("[mailCmdList.size():0]");
			} else {
				paramStrList.add("[mailCmdList.size():" + mailCmdList.size() + "]");
				for (int i = 0; i < mailCmdList.size(); i++) {
					paramStrList.add("[mailCmdList["+i+"]["+mailCmdList.size()+"]:" + mailCmdList.get(i)+ "]");
				}
				
			}
			
			
			
			
			paramStrList.add("══════" + " 参数展示结束 " + "══════");

			if (wifiInfoList.size() > 0) {

				extraInfoList.add("══════" + "Wifi信息展示开始" + "══════");
				extraInfoList.addAll(wifiInfoList);

			}

			if (cmdList != null && cmdList.size() > 0) {
				paramStrList.add("══════" + "lastCmd 开始" + "══════");
				paramStrList.add("[lastCmd:" + cmdList.get(cmdList.size() - 1) + "]");
			}

			paramStrList.add("══════" + "searchDir 列表[" + searchDirList.size() + "]" + "══════");
			for (int i = 0; i < searchDirList.size(); i++) {
				File searchDir = searchDirList.get(i);
				paramStrList.add("searchDir[" + i + "][" + searchDirList.size() + "]:" + searchDir.getAbsolutePath());
			}

			ArrayList<String> lastTxtList = ReadFileContentAsList(lastTxtFile);

			if (lastTxtList != null) {
				extraInfoList.add("══════" + lastTxtFile.getAbsolutePath() + "文件内容展示开始" + "══════");
				extraInfoList.addAll(lastTxtList);

			}

			if (usedTxtFileList != null && usedTxtFileList.size() > 0) {
				extraInfoList.add("══════" + "检测到的txt文件展示 usedTxtFileList[" + usedTxtFileList.size() + "]" + "══════");
				usedTxtFileList.sort(mFileDateComparion_New_To_Old);
				for (int i = 0; i < usedTxtFileList.size(); i++) {
					File usedTxtFile = usedTxtFileList.get(i);
					extraInfoList.add("usedTxtFile[" + i + "][" + usedTxtFileList.size() + "]:"
							+ usedTxtFile.getAbsolutePath() + "__" + long2yyyyMMdd_HHmmss(usedTxtFile.lastModified()));
				}
			}

			if (LogList.size() > 0) {

				extraInfoList.add("══════" + "Log展示开始" + "══════");
				extraInfoList.addAll(lastTxtList);

			}

			ArrayList<File> weCharAllFile = getAllSubFile(wechatDir, "*");

			ArrayList<File> QQAllFile = getAllSubFile(qqDir, "*");

			if (weCharAllFile != null && weCharAllFile.size() > 0) {
				extraInfoList.add("══════WeChat_" + wechatDir.getAbsolutePath() + "目录展示[" + weCharAllFile.size() + "]"
						+ "══════");
				weCharAllFile.sort(mFileDateComparion_New_To_Old);
				for (int i = 0; i < weCharAllFile.size(); i++) {
					File wechat_file_item = weCharAllFile.get(i);

					extraInfoList.add("wechat_file[" + i + "][" + weCharAllFile.size() + "]__"
							+ wechat_file_item.getAbsolutePath() + "__"
							+ long2yyyyMMdd_HHmmss(wechat_file_item.lastModified()));
				}

			}

			if (QQAllFile != null && QQAllFile.size() > 0) {
				extraInfoList
						.add("══════ QQ_" + qqDir.getAbsolutePath() + "目录展示[" + weCharAllFile.size() + "]" + "══════");
				QQAllFile.sort(mFileDateComparion_New_To_Old);
				for (int i = 0; i < QQAllFile.size(); i++) {
					File qq_file_item = QQAllFile.get(i);

					extraInfoList.add("qq_file[" + i + "][" + QQAllFile.size() + "]__" + qq_file_item.getAbsolutePath()
							+ "__" + long2yyyyMMdd_HHmmss(qq_file_item.lastModified()));
				}
			}

			HashMap<File, String> file_desc_map = new HashMap<File, String>();

			File screenImage = getPCScreenFile();
			String image_desc = pc_userName + "_" + getTimeStampyyyyMMdd_HHmmss() + "屏幕截图";
			file_desc_map.put(screenImage, image_desc);

			ArrayList<File> attatchFile = new ArrayList<File>();
			if(lastTxtFile != null) {
				attatchFile.add(lastTxtFile);
			}
		

			try {
				sendemail(minute_count == first_send_minute , pc_userName, null, mSubjectStr, "Hello Monitor!", paramStrList, extraInfoList, file_desc_map,attatchFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("send_email 发生异常 e="+e.getMessage());
			}

		}

		boolean isInAlreadyDoTxtFileList(ArrayList<File> mFileList, File singleFile) {
			boolean existFlag = false;
			String singleAbs = singleFile.getAbsolutePath();
			for (int i = 0; i < mFileList.size(); i++) {

				File fileItem = mFileList.get(i);

				String fileItemAbs = fileItem.getAbsolutePath();

				if (fileItemAbs.equals(singleAbs)) {

					existFlag = true;
					return existFlag;
				}
			}

			System.out.println(
					"isInAlreadyDoTxtFileList = false " + "singleAbs = " + singleAbs + " 不在已操作列表  将会执行它的 url 内容");
			return existFlag;

		}

		@SuppressWarnings("unchecked")
		void NewFileOperation(File newFile, int minutes) {

			ArrayList<File> curAllTxtFileList = getAllSubFileInFileList(searchRootFileList, ".txt");
			curAllTxtFileList.sort(mFileDateComparion_Old_To_New);

			ArrayList<File> needOperationList = new ArrayList<File>();

			for (int i = 0; i < curAllTxtFileList.size(); i++) {
				File curFile = curAllTxtFileList.get(i);
				if (isInAlreadyDoTxtFileList(curAlredyDoTxtFileList, curFile)) {
					continue; // 当前的 文件 已经 在 操作完成文件列表中
				}

				needOperationList.add(curFile);

			}

			for (int i = 0; i < needOperationList.size(); i++) {
				File operationFile = needOperationList.get(i);
				curAlredyDoTxtFileList.add(operationFile);
				System.out.println("______________ 新文件操作 lastNewFile[" + newFile.getName() + "] operationFile["
						+ operationFile.getName() + "]" + " index[" + i + "] needOperationCount["
						+ needOperationList.size() + "] " + "______________");

				mOneTimeLogList.add("______________ 新文件操作 Minute[" + minutes + "]lastNewFile[" + newFile.getName()
						+ "] operationFile[" + operationFile.getName() + "]" + " index[" + i + "] needOperationCount["
						+ needOperationList.size() + "] " + "______________");

				new Thread(new Runnable() {
					@Override
					public void run() {
						ArrayList<String> fileContent = ReadFileContentAsList(operationFile);
						if(fileContent == null) {
							System.out.println("ReadFileContentAsList operationFile="+operationFile+"   返回为空! ");
							return;
						}

						String filename = operationFile.getName();
						String fileNameNoPoint = getFileNameNoPoint(filename);

						curUrlIndex_InTxtFile = 0;
						for (int j = 0; j < fileContent.size(); j++) {
							String lineStr = fileContent.get(j);
							String strLine_trim_clearChinese = clearChinese(lineStr.trim());

							// 一行 中 可能 多个 url 列表
							ArrayList<String> oneLineUrlList = new ArrayList<String>();

							// 一行 中 可能 多个 zcmd_run_ 列表
							ArrayList<String> oneLineZCmdRunCommandList = new ArrayList<String>();

							// zcmder_run_ 列表 区别于 zcmd_run 一个运行在cmd 一个运行在cmder
							ArrayList<String> oneLineZCmderRunCommandList = new ArrayList<String>();

							synchronized (this) {
								toGetUrlFromOneLine_And_InitUrlList(strLine_trim_clearChinese, oneLineUrlList);
								zcmd_run_toGetZCmdRunFromOneLine_And_InitZCmdList(lineStr, oneLineZCmdRunCommandList,
										oneLineZCmderRunCommandList);
							}
							System.out.println("line[" + j + "] : str[" + lineStr + "]  clearChinese["
									+ strLine_trim_clearChinese + "] result["
									+ OperationWithOneLine(j, oneLineUrlList, fileNameNoPoint) + "]");

							if (oneLineZCmdRunCommandList.size() > 0) {
								mLogSB.append("___________ zcmd_run_xxx Begin[" + oneLineZCmdRunCommandList.size()
										+ "] line[" + j + "] ___________ " + lineStr + "\n");
								mOneTimeLogList.add("___________ zcmd_run_[" + minutes + "]_Begin["
										+ oneLineZCmdRunCommandList.size() + "] line[" + j + "] ___________ " + lineStr
										+ "\n");
								System.out.println("___________ zcmd_run_xxx Begin[" + oneLineZCmdRunCommandList.size()
										+ "] line[" + j + "] ___________ " + lineStr + "\n");
								String comResult = zcmd_run_OperationWithOneLine(j, oneLineZCmdRunCommandList,
										fileNameNoPoint);
								System.out.println("zcomResult: " + comResult);
								System.out.println(
										"___________ zcmd_run_[" + minutes + "] End[" + oneLineZCmdRunCommandList.size()
												+ "] line[" + j + "] ___________ " + lineStr + "\n");
								mLogSB.append(
										"___________ zcmd_run_[" + minutes + "] End[" + oneLineZCmdRunCommandList.size()
												+ "] line[" + j + "] ___________ " + lineStr + "\n");

								mOneTimeLogList.add("CMDResult:" + comResult);
								mOneTimeLogList.add(
										"___________ zcmd_run_[" + minutes + "] End[" + oneLineZCmdRunCommandList.size()
												+ "] line[" + j + "] ___________ " + lineStr + "\n");

							}

							if (oneLineZCmderRunCommandList.size() > 0) {

								mLogSB.append("___________ zcmder_run_xxx Begin[" + oneLineZCmderRunCommandList.size()
										+ "] line[" + j + "] ___________ " + lineStr + "\n");
								mOneTimeLogList.add("___________ zcmder_run_[" + minutes + "] Begin["
										+ oneLineZCmderRunCommandList.size() + "] line[" + j + "] ___________ "
										+ lineStr + "\n");

								System.out.println(
										"___________ zcmder_run_xxx Begin[" + oneLineZCmderRunCommandList.size()
												+ "] line[" + j + "] ___________ " + lineStr + "\n");
								String comResult = zcmder_run_OperationWithOneLine(j, oneLineZCmderRunCommandList,
										fileNameNoPoint);
								System.out.println("zcomResult: " + comResult);
								mLogSB.append("zcomResult: " + comResult);
								mOneTimeLogList.add("CmderResult:" + comResult);
								mOneTimeLogList.add("___________ zcmder_run_[" + minutes + "] Begin["
										+ oneLineZCmderRunCommandList.size() + "] line[" + j + "] ___________ "
										+ lineStr + "\n");

								System.out
										.println("___________ zcmder_run_xxx End[" + oneLineZCmderRunCommandList.size()
												+ "] line[" + j + "] ___________ lineStr=" + lineStr + "\n");
								mLogSB.append("___________ zcmder_run_xxx End[" + oneLineZCmderRunCommandList.size()
										+ "] line[" + j + "] ___________ " + lineStr + "\n");

							}

						}

						if (isLogFile) {
							writeContentToFile(mLogFile, mLogSB.toString());
						}

						System.out.println("════════════════ OVER ═════════════════");

					}
				}).start();

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

		// 执行 zcmder_run_ 的程序
		String zcmd_run_OperationWithOneLine(int index, ArrayList<String> zcmdRunStrList, String fileNameNoPoint) {
			String tipMessage = null;
			if (zcmdRunStrList == null || zcmdRunStrList.size() == 0) {
				tipMessage = " 当前zcmd_run_xxx  运行命令为空 无逻辑执行";
				return tipMessage;
			}

			StringBuilder tipSb = new StringBuilder();
			StringBuilder resultSB = new StringBuilder();

			for (int i = 0; i < zcmdRunStrList.size(); i++) {
				String strLine = zcmdRunStrList.get(i);

				strLine = strLine.replace("%win_zbin%", Win_Lin_Mac_ZbinPath);
				strLine = strLine.replace("%zbin%", zbinPath);

				System.out.println("_______________zmd_run_[" + i + "]  commond[" + strLine + "] Begin ____");
				tipSb.append("[" + i + "]" + "[" + strLine + "]");
				String result = execCMD(strLine); // cmd 运行
				tipSb.append("result:" + result);
				System.out.println("_______________zmd_run_[" + i + "]  commond[" + strLine + "] End ____");

			}

			return "zcmd_run_执行【" + tipSb.toString() + "】";
		}

		// 执行 zcmder_run_ 的程序
		String zcmder_run_OperationWithOneLine(int index, ArrayList<String> zcmderRunStrList, String fileNameNoPoint) {
			String tipMessage = null;
			if (zcmderRunStrList == null || zcmderRunStrList.size() == 0) {
				tipMessage = " 当前zcmd_run_xxx  运行命令为空 无逻辑执行";
				return tipMessage;
			}

			StringBuilder tipSb = new StringBuilder();
			for (int i = 0; i < zcmderRunStrList.size(); i++) {
				String strLine = zcmderRunStrList.get(i);
				System.out.println("_______________zmder_run_[" + i + "]  commond[" + strLine + "] Begin ____");
				mLogSB.append("_______________zmder_run_[" + i + "]  commond[" + strLine + "] Begin ____");

				tipSb.append("[" + i + "]" + "[" + strLine + "]");
				String result = run_cmder(strLine);
				// cmder 需要写道这里 执行
				tipSb.append("Result:" + result);
				System.out.println("_______________zmder_run_[" + i + "]  commond[" + strLine + "] End ____");

				mLogSB.append("_______________zmder_run_[" + i + "]  commond[" + strLine + "] End ____");

			}

			mLogSB.append("zcmd_run_执行【" + tipSb.toString() + "】");

			return "zcmd_run_执行【" + tipSb.toString() + "】";
		}

		public String run_cmder(String command) {
			String result = "";

			if (CUR_OS_TYPE == OS_TYPE.Windows) {
				return run_cmder_win_bat(command);
			} else if (CUR_OS_TYPE == OS_TYPE.MacOS) {

//				return execCMD_Mac(command);
			} else {

//				execCMD_Mac(command);
			}
			return result;
		}

		public String run_cmder_win_bat(String command) {
			System.out.println("  run_cmder_win_bat  command=" + command);

			String batHeadStr = "@echo off\r\n" + "Setlocal ENABLEDELAYEDEXPANSION\r\n"
					+ "echo  _____________ cmder_pre_exe _____________\r\n" + "@ECHO off\r\n"
					+ "setlocal enabledelayedexpansion\r\n" + "chcp 65001\r\n"
					+ "rem ══════════════════════════════════════════ System_Init_Aera Begin  ══════════════════════════════════════════\r\n"
					+ "\r\n" + "rem 函数定义之前的提示必须以英文结尾 否则 可能 报出一些 找不到之类的 错误 ----  \r\n"
					+ "rem ________________ 系统路径初始化   \r\n" + "set init_cd=%cd%\r\n" + "set init_dp0=%~dp0\r\n"
					+ "set init_f0=%~f0\r\n" + "set init_path=%path%\r\n" + "set init_input_0=%0\r\n"
					+ "echo init_cd=%init_cd%               rem %cd% === 当前执行命令的当前路径  C:\\Users\\zhuxx  \r\n"
					+ "echo init_f0=%init_f0%               rem %~f0 === 当前执行文件的全路径       C:\\Users\\xxx\\Desktop\\zbin\\win_zbin\\init_input_0.bat   \r\n"
					+ "echo init_input_0=%init_input_0%     rem %0 ===当前执行文件的名称 init_input_0=[zbatrule_I9_Rule30.bat]     init_input_0=[zbatrule_I9_Rule30.bat]\r\n"
					+ "echo init_dp0=%init_dp0%             rem %~dp0 ===  当前执行文件的文件夹名称  init_dp0 = C:\\Users\\zhuxx\\Desktop\\zbin\\win_zbin\\  \r\n"
					+ "echo init_path=%init_path%           rem %path% === 当前的系统坏境变量PATH\r\n"
					+ "rem init_path=D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\bin;C:\\Users\\xxx\\Desktop\\zbin\\win_soft\\Redis;C:\\Program Files\\Tesseract-OCR;D:\\software\\ffmpeg\\bin;C:\\Users\\xxx\\Desktop\\zbin\\lin_zbin;C:\\Swift\\bin\\;C:\\Users\\xxx\\Desktop\\zbin\\mac_zbin;C:\\Users\\xxx\\Desktop\\zbin\\win_zbin;C:\\Users\\xxx\\Desktop\\zbin\\python;\r\n"
					+ "rem D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\vendor\\conemu-maximus5\\ConEmu\\Scripts;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\vendor\\conemu-maximus5;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\vendor\\conemu-maximus5\\ConEmu;C:\\Windows\\System32;C:\\Users\\xxx\\Desktop\\zbin\\win_zbin;D:\\ZWin_Software\\D0_Environment_Zip_Dir_Path\\JDK8_64\\jre\\bin;D:\\ZWin_Software\\D0_Environment_Zip_Dir_Path\\JDK8_64\\bin;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\npp.7.8.9.bin.x64;\r\n"
					+ "rem D:\\ZWin_Software\\D0_Environment_Zip_Dir_Path\\python-3.7.9-embed-amd64;D:\\ZWin_Software\\D0_Environment_Zip_Dir_Path\\python-3.7.9-embed-amd64/Scripts;C:\\Program Files (x86)\\Qualcomm\\QUTS\\bin;C:\\Program Files (x86)\\Qualcomm\\QXDM5;C:\\Program Files (x86)\\Qualcomm\\PCAT\\bin;\r\n"
					+ "rem C:\\Program Files (x86)\\Qualcomm\\EUD;C:\\Program Files (x86)\\Qualcomm\\QIKTool\\1.0.109.1;C:\\Windows;C:\\Windows\\System32;D:\\ZWin_Software\\D0_Environment_Zip_Dir_Path\\ADB;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\npp.7.8.9.bin.x64;C:\\Users\\xxx\\Desktop\\zbin\\win_zbin;C:\\Program Files (x86)\\Graphviz2.38\\bin;\r\n"
					+ "rem D:\\ZWin_Software\\D0_Environment_Zip_Dir_Path\\JDK8_64\\bin;C:\\Program Files\\Tesseract-OCR;C:\\Users\\xxx\\AppData\\Local\\Google\\Chrome\\Application;D:\\ZWin_Software\\D0_Environment_Zip_Dir_Path\\JDK8_64\\jre\\bin;C:\\Users\\xxx\\AppData\\Local\\Android\\Sdk\\platform-tools;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\bin;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\vendor\\git-for-windows\\cmd;C:\\Program Files\\Tesseract-OCR;C:\\Users\\xxx\\Desktop\\zbin\\lin_zbin;C:\\Users\\xxx\\Desktop\\zbin\\mac_zbin;\r\n"
					+ "rem C:\\Users\\xxx\\Desktop\\zbin\\win_zbin;C:\\Users\\xxx\\AppData\\Local\\Programs\\Microsoft VS Code\\bin;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\vendor\\git-for-windows\\usr\\bin;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\vendor\\git-for-windows\\usr\\share\\vim\\vim74;D:\\ZWin_Software\\C1_GreenSoft_Zip_Dir\\cmder\\\r\n"
					+ "echo=\r\n" + "echo=\r\n" + "echo=\r\n" + "echo=\r\n" + "\r\n" + " \r\n"
					+ "rem ________________ 相对路径初始化   \r\n" + "set init_userprofile=%userprofile%\r\n"
					+ "set init_desktop=%userprofile%\\Desktop\r\n" + "set desktop=%userprofile%\\Desktop\r\n"
					+ "set init_zbin=%userprofile%\\Desktop\\zbin\r\n" + "set zbin=%userprofile%\\Desktop\\zbin\r\n"
					+ "set win_zbin=%userprofile%\\Desktop\\zbin\\win_zbin\r\n"
					+ "set init_win_zbin=%userprofile%\\Desktop\\zbin\\win_zbin\r\n"
					+ "echo init_userprofile=%init_userprofile%     rem %userprofile% 标示为 用户主目录 init_userprofile=C:\\Users\\xxx  \r\n"
					+ "echo desktop=%desktop%                       rem init_desktop 和 desktop 标示 桌面路径 C:\\Users\\xxx\\Desktop\r\n"
					+ "echo init_desktop=%init_desktop%             rem init_desktop 和 desktop 标示 桌面路径 C:\\Users\\xxx\\Desktop\r\n"
					+ "echo zbin=%zbin%                             rem zbin 和 init_zbin 标示 桌面zbin路径 C:\\Users\\xxx\\Desktop\\zbin\r\n"
					+ "echo init_zbin=%init_zbin%                   rem zbin 和 init_zbin 标示 桌面zbin路径 C:\\Users\\xxx\\Desktop\\zbin\r\n"
					+ "echo win_zbin=%win_zbin%                     rem win_zbin 和 init_win_zbin 标示 桌面zbin路径里的win_zbin C:\\Users\\xxx\\Desktop\\zbin\\win_zbin\r\n"
					+ "echo init_win_zbin=%init_win_zbin%           rem win_zbin 和 init_win_zbin 标示 桌面zbin路径里的win_zbin C:\\Users\\xxx\\Desktop\\zbin\\win_zbin\r\n"
					+ "\n";

			// 写入 zcmder_prexe_G2.bat 的内容
			String cmderPrexe_bat_Content = batHeadStr + command;

			// cmder.exe 定义地址
//			 cmderExePath = new File("D:\\zsoft_dest\\C1_GreenSoft_Zip_Dir\\cmder\\Cmder.exe");

//			cmder_prexe_bat_file = new File(zbinPath + File.separator + "win_zbin"+File.separator+"zcmder_prexe_G2"+Cur_Batch_End);

			cmderExePath = new File("D:\\zsoft_dest\\C1_GreenSoft_Zip_Dir\\cmder\\Cmder.exe");

			cmder_prexe_bat_file = new File(
					zbinPath + File.separator + "win_zbin" + File.separator + "zcmder_prexe_G2.bat");

			writeContentToFile(cmder_prexe_bat_file, cmderPrexe_bat_Content);
			String fixedCommand = null;

			if (!cmderExePath.exists()) {
				System.out.println("\n" + "command=" + command + " cmderExePath 文件不存在  cmderExePath="
						+ cmderExePath.getAbsolutePath());

				mLogSB.append("\n" + "command=" + command + " cmderExePath 文件不存在  cmderExePath="
						+ cmderExePath.getAbsolutePath());

				fixedCommand = command;
			} else if (!cmder_prexe_bat_file.exists()) {
				System.out.println(
						" cmder_prexe_bat_file 文件不存在  cmder_prexe_bat_file=" + cmder_prexe_bat_file.getAbsolutePath());

				mLogSB.append("\n" + "command=" + command + " cmder_prexe_bat_file 文件不存在  cmder_prexe_bat_file="
						+ cmder_prexe_bat_file.getAbsolutePath());

				fixedCommand = command;
			}

			if (cmderExePath.exists() && cmder_prexe_bat_file.exists()) {
				// D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\Cmder.exe /TASK
				// zcmder_prexe_G2.bat
				fixedCommand = cmderExePath.getAbsolutePath() + " /TASK " + cmder_prexe_bat_file.getName();

				System.out.println(" cmder.exe 存在 将在 cmder 下执行逻辑! ");

				mLogSB.append("\n" + " cmder.exe 存在 将在 cmder 下执行逻辑!   cmderExePath=" + cmderExePath.getAbsolutePath()
						+ "  cmder_prexe_bat_file=" + cmder_prexe_bat_file.getAbsolutePath());

			}

			return execCMD(fixedCommand);

		}

		String OperationWithOneLine(int index, ArrayList<String> strLineList, String fileNameNoPoint) {
			String tipMessage = null;
			if (strLineList == null || strLineList.size() == 0) {
				tipMessage = " 当前行字符串为空 无逻辑执行";
				return tipMessage;
			}

			for (int i = 0; i < strLineList.size(); i++) {
				String strLine = strLineList.get(i);

				String strLine_trim = strLine.trim();
				String strLine_trim_clearChinese = clearChinese(strLine_trim);
				System.out.println("strItem[" + i + "][" + strLineList.size() + "]=" + "[" + strLine_trim_clearChinese
						+ "]  On  One Line ");
				boolean isUrl = toJudgeUrl(strLine_trim_clearChinese);
				if (isUrl && urlStrList.contains(strLine_trim_clearChinese)) {
					tipMessage = " 当前url 已经执行过下载操作 跳过逻辑执行";
					return tipMessage;

				}
				// 如果是 url 那么 执行下载
				if (isUrl) {
					System.out.println("urlStrList.contain() == " + urlStrList.contains(strLine_trim_clearChinese)
							+ "  url-size=" + urlStrList.size());
					curUrlIndex_InTxtFile++;

					if (strLine_trim_clearChinese.contains("douyin")) {
						douYinParseUrl(curUrlIndex_InTxtFile, strLine_trim_clearChinese, fileNameNoPoint);
						urlStrList.add(strLine_trim_clearChinese);
						tipMessage = "下载抖音视频";
					} else if (strLine_trim_clearChinese.contains("v.kuaishou.com")) {
						ksParseUrl(curUrlIndex_InTxtFile, strLine_trim_clearChinese, fileNameNoPoint);
						urlStrList.add(strLine_trim_clearChinese);
						tipMessage = "下载快手视频";
					} else if (strLine_trim_clearChinese.startsWith("https://profile.zjurl.cn/rogue/ugc/profile")
							&& strLine_trim_clearChinese.contains("user_id")) {
						// https://profile.zjurl.cn/rogue/ugc/profile/?version_code=851&version_name=80501&user_id=3346556174218692&media_id=1632586053830670&request_source=1&active_tab=dongtai&device_id=65&app_name=news_article&share_token=4c7776c5-9a34-443f-b7df-9e6e69c08f17&tt_from=copy_link&utm_source=copy_link&utm_medium=toutiao_android&utm_campaign=client_share?=推荐《Emath》的主页
						// - 今日头条
						// 获取 用户的id 去它的主页 拿取 所有的 该用户的视频 然后下载
						DownloadUserTouTiaoHomeVideo(strLine_trim_clearChinese.trim());
					} else if (strLine_trim_clearChinese.startsWith("https://www.ixigua.com/home/")) {
						DownloadUserTouTiaoHomeVideo_IXiGuaHome(strLine_trim_clearChinese);

					} else if (strLine_trim_clearChinese.contains("toutiao") // m.toutiaoimg.cn
							// https://m.toutiaocdn.com/i6982548019329843742
							|| strLine_trim_clearChinese.contains("ixigua")) {
						TouTiao_XiGua_Download(curUrlIndex_InTxtFile, strLine_trim_clearChinese);
						urlStrList.add(strLine_trim_clearChinese);
						tipMessage = "下载头条西瓜视频";
					} else if (strLine_trim_clearChinese.contains("https://twitter.com")) {
						// // https://twitter.com/PDChinese/status/1427649465826033672?s=19
						TW_Download(curUrlIndex_InTxtFile, strLine_trim_clearChinese);

						tipMessage = "下载TW视频";

					} else {
						tipMessage = "当前的URL不是抖音-快手-头条路径 暂不支持下载";
						System.out.println("当前的URL不是抖音-快手-头条路径 暂不支持下载  URL = " + strLine_trim_clearChinese);
					}

				}

			}

			System.out.println(" __________________ urlList Begin __________________");
			for (int i = 0; i < urlStrList.size(); i++) {
				String urlStr = urlStrList.get(i);
				System.out.println("url[" + (i + 1) + "]  = " + urlStr);
			}

			System.out.println(" __________________ urlList Endxx __________________");

			return tipMessage;

		}

		// https://www.ixigua.com/home/3346556174218692
		public void DownloadUserTouTiaoHomeVideo_IXiGuaHome(String mIxiguaHomeUrl) {
			if (!mIxiguaHomeUrl.startsWith("https://www.ixigua.com/home/")) {

				System.out.println("当前 路径 " + mIxiguaHomeUrl + "  不是 https://www.ixigua.com/home/ 类型的主页路径 无法批量下载");

				return;
			}

			String homePageCode = getXiGua_MainPageSource(mIxiguaHomeUrl);

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

			String homePageCode = getXiGua_MainPageSource(touTiaoHomePage);

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
				System.out.println("connection.getOutputStream  Begin  获取 id=" + id + "  对应的  TwitterInfo_耗时_A  得很)");

				OutputStream os = connection.getOutputStream();
				long endTimeStamp = System.currentTimeMillis();
				long distance_second = (endTimeStamp - beginTimeStamp) / 1000;
				System.out.println("connection.getOutputStream  Begin  获取 id=" + id + "  成功 TwitterInfo_耗时_A:【"
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

				System.out.println("Debug: extractTwitterVideo  End  statusCode = " + statusCode + "  TwitterInfo_耗时B【"
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
							org.json.JSONArray videos = object.getJSONArray("videos");
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

		String getIdFromTWUrl(String httpPageUrl) {
			// // https://twitter.com/PDChinese/status/1427649465826033672?s=19

			String status_end = httpPageUrl.substring(httpPageUrl.indexOf("status/") + "status/".length());

			String clear_doubt_id = status_end.substring(0, status_end.indexOf("?"));

			return clear_doubt_id;

		}

		TwitterVideo showTwitterInfo_ReturnBigOne(List<TwitterVideo> list, String httpPageUrl) {
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
				System.out.println("最大分辨率-url:" + curBigItem.toString() + "  httpPageUrl:" + httpPageUrl);
			} else {
				System.out.println("没有选中最大分辨率的 url!!  请检查");
			}

			return curBigItem;
		}

		// downRawVideo_WithUrl(index, finalVideoAddress, fileNameNoPoint, "douyin");
		public void downloadByCommonIO(int index, String pageurl, String httpUrl, String fileNameNoPoint,
				String source) {

			String fileAddress = mDownloadedMonthDir.getAbsolutePath() + File.separator
					+ (source == null || "".equals(source) ? "" : source + "_") + (fileNameNoPoint.replace(" ", ""))
					+ "_" + index + "_" + getTimeStamp() + ".mp4";

			try {
				System.out.println();
				System.out.println(
						"downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin FileAddress=" + fileAddress);
				System.out.println("downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin PageUrl=" + pageurl);
				System.out.println("downloadByCommonIO_Retry下载[" + download_failed_time + "] Begin HttpUrl=" + httpUrl);

				File fileSavePath = new File(fileAddress);
				FileUtils.copyURLToFile(new URL(httpUrl), fileSavePath, 30000, 30000);
				download_failed_time = 0;

				System.out
						.println("downloadByCommonIO_下载[" + download_failed_time + "] End  fileAddress=" + fileAddress);

				System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
				System.out.println("\nzzfile_3.bat " + fileSavePath.getParentFile().getAbsolutePath());

				if (isMDName) {
					System.out.println("由于 isMDName=true  视频文件将以 MD5 属性文件名称进行命名!!! ");

					// 获取文件的 md值 并重命名为 mdxxxx.mp4
					String mdName = getMD5Three(fileSavePath.getAbsolutePath());
					String new_Md_Name = mdName + ".mp4";
					tryReName(fileSavePath, new_Md_Name);
					System.out.println("\n-----视频保存路径(MD名称)-----\n" + fileSavePath.getAbsolutePath());

					// 把下载的 mp4 文件 名称 转为 md值
				}

				// 把下载的 mp4 文件 名称 转为 md值
//						url_name_LogList.add(pageUrl+"          "+mdName);
				urlStrList.add(httpUrl);

			} catch (IOException e) {
				download_failed_time++;
				if (download_failed_time % 10 == 0) {
					System.out.println("程序下载 retry " + download_failed_time + " 次 仍然 下载 失败----放弃");
				} else {

					downloadByCommonIO(index, pageurl, httpUrl, fileNameNoPoint, source);
				}
//			            e.printStackTrace();
			}

		}

		public void TW_Download(int index, String httppage) {

			// 1. 获取 tw 的 id
			String id_str = getIdFromTWUrl(httppage);
			if (id_str == null || "".equals(id_str.trim()) || !isNumeric(id_str.trim())) {
				System.out.println("当前 TW-Url: " + httppage + " 识别出的ID出错请检查!! id_str=" + id_str);
				return;
			}

			try {
				List<TwitterVideo> list = extractTwitterVideo(id_str);

				if (list == null || list.size() == 0) {
					System.out.println("返回为空 ");
				} else {

					System.out.println("返回 list.size() == " + list.size());
					TwitterVideo high_url_TwitterVideo = showTwitterInfo_ReturnBigOne(list, httppage);

					if (high_url_TwitterVideo != null) {
//						downRawVideo_WithUrl(httppage, high_url_TwitterVideo.url, id_str, null);
						// downRawVideo_WithUrl(index, finalVideoAddress, fileNameNoPoint, "douyin");

//						downRawVideo_WithUrl(index, high_url_TwitterVideo.url, id_str, null);
						downRawVideo_WithUrl_WithProxy(index, high_url_TwitterVideo.url, id_str, null, httppage);

//						downloadByCommonIO(index,httppage, high_url_TwitterVideo.url, id_str, "tw");
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

		void TouTiao_XiGua_Download(int index, String urlitem) {
			if (!ChromeDriverFile.exists()) {
				System.out.println(
						"当前 ChroneDriver.exe 文件不存在 请检查当前 chrome版本 并去 http://npm.taobao.org/mirrors/chromedriver/ 下载对应版本的 chromedriver.exe 才能执行 头条西瓜视频的下载 ");
				return;
			}

//			if (urlitem.startsWith("https://www.ixigua.com/") || urlitem.startsWith("https://m.toutiaoimg.cn/")) {

			XiGua_TouTiao_ParseUrl(index, urlitem);
//			}

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
				mainHtml = Jsoup.parse(getXiGua_MainPageSource(url));

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

							downRawVideo_WithUrl(index, NoMainUrl_VideoTag_url, "", "TouTiao");
						} else {

							System.out.println(
									"解析出的 base64_jiami_main_url 为空  NoMainUrl_VideoTag_url 为空 无法下载视频到本地   base64_jiami_url="
											+ base64_jiami_url);

						}

					} else {
						System.out.println("解析出的 base64_jiami_url=[" + base64_jiami_url + "]  尝试解密base64");

						jiemi_base64_url = jiemi_decryptBASE64(base64_jiami_url);
						System.out.println();

						System.out.println("解析出的地址  jiemi_base64_url = [" + jiemi_base64_url + "]");

						if (jiemi_base64_url.startsWith("http")) {
							System.out.println("执行 main_url 下载操作!!!    jiemi_base64_url=[" + jiemi_base64_url + "]");
							downRawVideo_WithUrl(index, jiemi_base64_url, "", "TouTiao");

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
						downRawVideo_WithUrl(index, jiemi_base64_bankurl, "", "TouTiao");

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
		String getXiGua_MainPageSource(String url) {

			ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
			// 驱动位置
			CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");

//			CUR_CHROME_OPTIONS.addArguments("Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//			CUR_CHROME_OPTIONS.addArguments("Accept-Encoding=gzip, deflate, sdch");
//			CUR_CHROME_OPTIONS.addArguments("Accept-Language=zh-CN,zh;q=0.8");
//			CUR_CHROME_OPTIONS.addArguments("Connection=keep-alive");
//			CUR_CHROME_OPTIONS.addArguments("Host=activityunion-marketing.meituan.com");
//			CUR_CHROME_OPTIONS.addArguments("Upgrade-Insecure-Requests=1");
//			CUR_CHROME_OPTIONS.addArguments("User-Agent=Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4");

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

//				        List<WebElement> element =  driver.findElements(xgplayer);
//				        if(element != null && element.size() > 0) {
//				        	WebElement endElement = element.get(element.size() -1 );
//				    		System.out.println("!! 触发点击事件  起始 标识 BBB   element.size()="+element.size());
//				        	endElement.click();
//				        }

						/*
						 * System.out.println("!! 触发点击事件  起始 标识 BBB !!"); List<WebElement> playelements
						 * = driver.findElementsByClassName("xgplayer-start");
						 * System.out.println(" xgplayer-start  playelements.size() = " + (playelements
						 * == null ? "null":playelements.size()+"")); if(playelements != null &&
						 * playelements.size() > 0) { for (WebElement webElement : playelements) {
						 * if(webElement != null) {
						 * System.out.println("_______________ webelement begin _______________"); //
						 * System.out.println(webElement.toString());
						 * System.out.println("_______________ webelement endxx _______________");
						 *
						 * }
						 *
						 * }
						 *
						 *
						 *
						 * System.out.println("存在播放按钮 xgplayer-start   触发点击 playelements="+(playelements
						 * == null?"null":""+playelements.size())); }else {
						 *
						 * System.out.println("没有播放按钮 xgplayer-start "); }
						 */

//						TimeUnit.MILLISECONDS.sleep(2000);

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

		@SuppressWarnings("unchecked")
		public void ksParseUrl(int index, String url, String fileNameNoPoint) {
			HashMap headers = MapUtil.newHashMap();
			headers.put("User-Agent",
					"Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Mobile Safari/537.36");
			String redirectUrl = HttpUtil.createGet(url).addHeaders(headers).execute().header("Location");
			String body = HttpUtil.createGet(redirectUrl).addHeaders(headers).execute().body();
			org.jsoup.nodes.Document doc = Jsoup.parse(body);
			Elements videoElement = doc.select("video[id=video-player]");
			String videoUrl = videoElement.get(0).attr("src");
			String title = videoElement.get(0).attr("alt");
			System.out.println();
			System.out.println(videoUrl);
			System.out.println(title);
//	        downVideo(videoUrl,title,"kuaishou");
			downRawVideo_WithUrl(index, videoUrl, fileNameNoPoint, "kuaishou");
		}

		@SuppressWarnings("unchecked")
		public void douYinParseUrl(int index, String url, String fileNameNoPoint) {
			try {
				final String videoPath = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";
				Connection con = Jsoup.connect(clearChinese(url));
				con.header("User-Agent",
						"Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
				Connection.Response resp = con.method(Connection.Method.GET).execute();
				String videoUrl = videoPath + getDouYinItemId(resp.url().toString());
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
				System.out.println("-----抖音去水印链接-----\n" + "finalVideoAddress=" + finalVideoAddress
						+ "  \nvideoAddress=" + videoAddress);
				// 下载无水印视频到本地
				if (finalVideoAddress == null) {
					finalVideoAddress = videoAddress;
				}
				downRawVideo_WithUrl(index, finalVideoAddress, fileNameNoPoint, "douyin");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		// String fileAddress = videoSavePath+"/"+((source==null ||"".equals(source) ?
		// "":source+"_")+title.replace(" ",
		// ""))+"_"+index_download+"_"+timeStamp_Str+".mp4";

		// 视频的保存 目录 不能是 当前文件 否则 就会执行 同步操作 影响网速
		// pageUrl 是页面的url httpUrl 是视频文件的url
		@SuppressWarnings("unchecked")
		public void downRawVideo_WithUrl_WithProxy(int index, String httpUrl, String fileNameNoPoint, String source,
				String pageUrl) {
			if (urlStrList.contains(httpUrl)) {
				System.out.println("当前url 路径已经下载过  跳过下载!!  url路径: " + httpUrl + "");
				return;
			}

			String fileAddress = mDownloadedMonthDir.getAbsolutePath() + File.separator
					+ (source == null || "".equals(source) ? "" : source + "_") + (fileNameNoPoint.replace(" ", ""))
					+ "_" + index + "_" + getTimeStamp() + ".mp4";

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
				URLConnection conn = url.openConnection(proxy);
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

				if (isMDName) {
					System.out.println("由于 isMDName=true  视频文件将以 MD5 属性文件名称进行命名!!! ");

					// 获取文件的 md值 并重命名为 mdxxxx.mp4
					String mdName = getMD5Three(fileSavePath.getAbsolutePath());
					String new_Md_Name = mdName + ".mp4";
					tryReName(fileSavePath, new_Md_Name);
					// 把下载的 mp4 文件 名称 转为 md值
				}

				urlStrList.add(httpUrl);
				download_failed_time = 0;
				System.out
						.println("downloadByCommonIO_下载[" + download_failed_time + "] End  fileAddress=" + fileAddress);
			} catch (Exception e) {

				download_failed_time++;
				if (download_failed_time % 10 == 0) {
					System.out.println("程序下载 retry " + download_failed_time + " 次 仍然 下载 失败----放弃");
				} else {
					downRawVideo_WithUrl_WithProxy(index, httpUrl, fileNameNoPoint, source, pageUrl);
				}
				// e.printStackTrace();

				// System.out.println(e.getMessage());
			}
		}

		// 视频的保存 目录 不能是 当前文件 否则 就会执行 同步操作 影响网速
		@SuppressWarnings("unchecked")
		public void downRawVideo_WithUrl(int index, String httpUrl, String fileNameNoPoint, String source) {
//	        String fileAddress = videoSavePath+"/"+source+"/"+title+".mp4";
			if (mDownloadedMonthDir == null) {
				mDownloadedMonthDir = new File(curDirPath);
			}
			String fileAddress = mDownloadedMonthDir.getAbsolutePath() + File.separator
					+ (source == null || "".equals(source) ? "" : source + "_") + (fileNameNoPoint.replace(" ", ""))
					+ "_" + index + "_" + getTimeStamp() + ".mp4";

			int byteRead;
			try {
				URL url = new URL(httpUrl);
				// 获取链接
				URLConnection conn = url.openConnection();
				// 输入流
				InputStream inStream = conn.getInputStream();
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
				while ((byteRead = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteRead);
				}
				inStream.close();
				fs.close();
				System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
				System.out.println("\nzzfile_3.bat " + fileSavePath.getParentFile().getAbsolutePath());

				if (isMDName) {
					System.out.println("由于 isMDName=true  视频文件将以 MD5 属性文件名称进行命名!!! ");

					// 获取文件的 md值 并重命名为 mdxxxx.mp4
					String mdName = getMD5Three(fileSavePath.getAbsolutePath());
					String new_Md_Name = mdName + ".mp4";
					tryReName(fileSavePath, new_Md_Name);
					// 把下载的 mp4 文件 名称 转为 md值
				}

				urlStrList.add(httpUrl);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		public String getDouYinItemId(String url) {
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

		@SuppressWarnings("unchecked")
		File calLastTxtFileInFileList(ArrayList<File> rootFileList) {

			if (rootFileList == null || rootFileList.size() == 0) {
				System.out.println(" AA   calLastTxtFileInFileList == null ");
				return null;
			}
			// 全目录 搜索 TXT 文件 Begin
			ArrayList<File> allTxtFileList = new ArrayList<File>();
//			ArrayList<File> txtFileList = new ArrayList<File>();
			for (int i = 0; i < rootFileList.size(); i++) {
				File rootDir = rootFileList.get(i);
				ArrayList<File> txtFileList = getAllSubFile(rootDir, ".txt");
				if (txtFileList == null || txtFileList.size() == 0) {
					continue;
				}

				allTxtFileList.addAll(txtFileList);

			}

			if (allTxtFileList.size() == 0) {
				System.out.println(" BB   calLastTxtFileInFileList.size() == 0 ");
				return null;
			}

			System.out.println(" CC   calLastTxtFileInFileList.size() ==  " + allTxtFileList.size());

			allTxtFileList.sort(mFileDateComparion_Old_To_New);
			File lastTxtFile = allTxtFileList.get(allTxtFileList.size() - 1);
			return lastTxtFile;

		}

		@SuppressWarnings("unchecked")
		File calLastTxtFileInList(File rootDir) {

			// 全目录 搜索 TXT 文件 Begin
//			ArrayList<File> txtFileList = new ArrayList<File>();

			ArrayList<File> txtFileList = getAllSubFile(rootDir, ".txt");

			/*
			 * File newRootFile = new File(rootDir.getAbsolutePath()); if (newRootFile ==
			 * null) { System.out.println(" 当前 检测不到根目录 newRootFile = null !!"); return null;
			 * } File[] fileArr = newRootFile.listFiles(); if (fileArr == null ||
			 * fileArr.length == 0) { System.out.println("当前目录文件为空,将休眠1分钟后继续监测!!");
			 * System.out.println("mWeChatRootFile.listFiles().size() ==  0 "); } else {
			 * System.out.println("mWeChatRootFile.listFiles().size() == " +
			 * fileArr.length); for (int i = 0; i < fileArr.length; i++) { File curFile =
			 * fileArr[i]; String fileNmae = curFile.getName().toLowerCase(); if
			 * (fileNmae.endsWith(".txt")) { txtFileList.add(curFile); } }
			 *
			 * }
			 */
			// 全目录 搜索 TXT 文件 End

			if (txtFileList.size() == 0) {
				return null;
			}
			txtFileList.sort(mFileDateComparion_Old_To_New);
			File lastTxtFile = txtFileList.get(txtFileList.size() - 1);
//			mWeChatRootFile = newRootFile;

			return lastTxtFile;

		}

		// 对每行的数据进行分析

		public void toGetUrlFromOneLine_And_InitUrlList(String rowString, ArrayList<String> urlList) {
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

		// 对每行的数据检查是否有 zcmd_run_ 之类的 运行命令

		public void zcmd_run_toGetZCmdRunFromOneLine_And_InitZCmdList(String rowString,
				ArrayList<String> zcmdrunStrList, ArrayList<String> zrunStrList_cmderList) {
			String[] strArrRow = null;
			String fixStr = "";

//	        if(str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
//	                str.trim().startsWith("thunder:") ||   str.trim().startsWith("magnet::") ){

			if (!rowString.contains("zcmd_run_") && !rowString.contains("zcmder_run_")) {
				System.out.println("当前行 rowString=" + rowString + " 不包含标识符 【zcmd_run_】   【zcmder_run_】命令执行失败!");
				return;
			}

			if (rowString.contains("zcmd_run_")) {

				if (rowString != null) {
					fixStr = new String(rowString);
					// http://xxxxxx/sahttp:// 避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
					fixStr = fixStr.replace("zcmd_run_", " zcmd_run_");

					strArrRow = fixStr.split("zcmd_run_");
				}

				if (strArrRow != null && strArrRow.length > 0) {

					for (int i = 0; i < strArrRow.length; i++) {
						String mCommandItem = strArrRow[i];
						System.out.println("strArrRow[" + i + "] = " + mCommandItem);
						if (mCommandItem == null || mCommandItem.trim().equals("")) {
							continue;
						}

						mCommandItem = mCommandItem.replace("zcmd_run_", "");
						System.out.println("zcmd_run_xxx rowString[" + rowString + "]" + "  mCommandItem = ["
								+ mCommandItem + "] ");

						if (mCommandItem.contains("【") && mCommandItem.contains("】")
								&& mCommandItem.indexOf("【") < mCommandItem.indexOf("】")) {

							String tipNum = mCommandItem.substring(mCommandItem.indexOf("【"),
									mCommandItem.lastIndexOf("】") + 1);

							System.out.println("tipNum = " + tipNum);
							mCommandItem = mCommandItem.replace(tipNum, "");

						}

						zcmdrunStrList.add(mCommandItem.trim());

					}

				}

			} else if (rowString.contains("zcmder_run_")) {

				if (rowString != null) {
					fixStr = new String(rowString);
					// http://xxxxxx/sahttp:// 避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
					fixStr = fixStr.replace("zcmder_run_", " zcmder_run_");

					strArrRow = fixStr.split("zcmder_run_");
				}

				if (strArrRow != null && strArrRow.length > 0) {

					for (int i = 0; i < strArrRow.length; i++) {
						String mCommandItem = strArrRow[i];
						System.out.println("strArrRow[" + i + "] = " + mCommandItem);
						if (mCommandItem == null || mCommandItem.trim().equals("")) {
							continue;
						}

						mCommandItem = mCommandItem.replace("zcmder_run_", "");
						System.out.println("zcmd_run_xxx rowString[" + rowString + "]" + "  mCommandItem = ["
								+ mCommandItem + "] ");

						if (mCommandItem.contains("【") && mCommandItem.contains("】")
								&& mCommandItem.indexOf("【") < mCommandItem.indexOf("】")) {

							String tipNum = mCommandItem.substring(mCommandItem.indexOf("【"),
									mCommandItem.lastIndexOf("】") + 1);

							System.out.println("tipNum = " + tipNum);
							mCommandItem = mCommandItem.replace(tipNum, "");

						}

						zrunStrList_cmderList.add(mCommandItem.trim());

					}

				}

			}

		}

		/*
		 * @Override String simpleDesc() {
		 *
		 * return Cur_Bat_Name + " #_" + rule_index +
		 * "  ### 持续检测 WeChat目录 C:\\Users\\zukgit\\Documents\\WeChat Files\\xxxx\\FileStorage\\File\\2021-07 的 TXT文件的内容    \n"
		 * + Cur_Bat_Name + " #_" + rule_index +
		 * "   ### 只有在 WeChat的当前 月份接收文件目录 才能生效 Monitor 监控 \n" + "  explorer.exe  \"" +
		 * System.getProperties().getProperty("user.home") +
		 * "\\Documents\\WeChat Files\\zzj382581427\\FileStorage\\File\"   \n" +
		 * "  explorer.exe  \"" + mDownloadedMonthDir.getAbsolutePath() + "\"   \n" +
		 * "cd  " + "\""+ System.getProperties().getProperty("user.home") +
		 * "\\Documents\\WeChat Files\\zzj382581427\\FileStorage\\File" +
		 * File.separator+ getTimeStamp_YYYY_MM() + "\"" +"  && "+ "  explorer.exe " +
		 * " \""+ mDownloadedMonthDir.getAbsolutePath()+"\"" +
		 * " \n &&   zrule_apply_G2.bat " + "_" + rule_index + "_" + "\n"
		 *
		 * ; }
		 */

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index
					+ "  ### 持续检测 WeChat目录 C:\\Users\\zukgit\\Documents\\WeChat Files\\xxxx\\FileStorage\\File\\2021-07 的 TXT文件的内容    \n"
					+ Cur_Bat_Name + " #_" + rule_index + "  mdname_true   logfile_true "
					+ "  ### 以MD5字符串保存下载视频文件 持续检测 WeChat目录 C:\\Users\\zukgit\\Documents\\WeChat Files\\xxxx\\FileStorage\\File\\2021-07 的 TXT文件的内容    \n"

					+ Cur_Bat_Name + " #_" + rule_index + "   ### 只有在 WeChat的当前 月份接收文件目录 才能生效 Monitor 监控 \n"
					+ Cur_Bat_Name + " #_" + rule_index
					+ "  bootup_true   ### 只有在 WeChat的当前 月份接收文件目录 才能生效 Monitor 监控  bootup_true 标识是开机启动的进程 \n"
					// copy
					// System.getProperties().getProperty("user.home")\Desktop\zbin\win_zbin\zrule_apply_G2_39rule_startup.vbs
					// C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\
					// copy %userprofile%\Desktop\zbin\win_zbin\zrule_apply_G2_39rule_startup.vbs
					// C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\
					// zbatrule_I9_Rule30.bat _31_ file_C:\Users\zhuzj5\Desktop\ScreenShot\D\T.txt
					+ " 【配置检测开机启动】 \"\n" + "zbatrule_I9_Rule30.bat _31_  file_" + Win_Lin_Mac_ZbinPath + File.separator
					+ "zrule_apply_G2_39rule_startup.vbs" + "  " + "\n" + "  explorer.exe  \""
					+ "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\StartUp\\\"  \n"

					+ "  explorer.exe  \"" + System.getProperties().getProperty("user.home")
					+ "\\Documents\\WeChat Files\"  \n" + "  explorer.exe  \""
					+ System.getProperties().getProperty("user.home") + "\\Documents\\Tencent Files\"  \n"

					+ "  explorer.exe  \"" + mDownloadedMonthDir.getAbsolutePath() + "\"   \n" + "cd  " + "\""
					+ System.getProperties().getProperty("user.home") + "\\Documents\\" + "\"" + "  && "

					+ "  explorer.exe " + " \"" + mDownloadedMonthDir.getAbsolutePath() + "\""
					+ " \n &&   zrule_apply_G2.bat " + "_" + rule_index + "_" + "  mdname_true" + "\n"

			;
		}

	}

	static double ONE_MINUTES_SECOND = 60; // 1分钟 单位:秒
	static double ONE_SECOND_MILLSECOND = 1000; // 1 秒钟 单位:毫秒
	static double ONE_MINUTES_MILLSECOND = ONE_MINUTES_SECOND * ONE_SECOND_MILLSECOND; // 1分钟 单位:毫秒

	static Comparator mFileDateComparion_Old_To_New = new Comparator<File>() {
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

	static Comparator mFileDateComparion_New_To_Old = new Comparator<File>() {
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

	static Comparator mStringComparion = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			int o1_length = o1.length();
			int o2_length = o2.length();
			if (o1_length < o2_length) {
				return -1;
			} else if (o1_length == o2_length) {
				return o1.compareTo(o2);
			} else if (o1_length > o2_length) {
				return 1;
			}

			return o1.compareTo(o2);
		}

	};

	class Revert_xlsx2json_Rule_38 extends Basic_Rule {
		boolean isDirOperation; // 是否没有输入 xlsx 文件 而是 输入了一个 目录 默认shell 目录 已经 输入的目录

		File inputDirFile;
		ArrayList<File> xlsxFileList;

		Revert_xlsx2json_Rule_38() {
			super("#", 38, 3); //
			xlsxFileList = new ArrayList<File>();
			inputDirFile = null;
			isDirOperation = false;
		}

		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			// TODO Auto-generated method stub

			for (int i = 0; i < xlsxFileList.size(); i++) {
				File xlsxFile = xlsxFileList.get(i);
				String xlsxFileName = xlsxFile.getName();
				String getxlsxNameNoType = getFileNameNoPoint(xlsxFileName);
				String resut_json_xlsx_name = xlsxFileName.replace(".", "_") + "_" + getTimeStamp();
//				String resut_json_xlsx_name = getxlsxNameNoType+".json";
				File xlsxFile_resultDir = null;

				if (isDirOperation && inputDirFile != null) {
					xlsxFile_resultDir = inputDirFile;
					String resultJsonName = getxlsxNameNoType + ".json";
					File resultJsonFile = new File(inputDirFile.getAbsolutePath() + File.separator + resultJsonName);
					if (resultJsonFile.exists()) {
						// 如果当前 目录 已经 有 xxxx.xlsx 对应的 xxxxx.json 文件 那么 就不解析 这个 xlsx文件
						continue;
					}
					reverXlsxToJson(xlsxFile, xlsxFile_resultDir, true);
				} else {
					// 如果 不是 输入 目录 的 话 那么就创建 xlsx 的 文件名称 如果是目录的话 那么就在当前目录生成 .json
					xlsxFile_resultDir = new File(
							xlsxFile.getParentFile().getAbsolutePath() + File.separator + resut_json_xlsx_name);
					reverXlsxToJson(xlsxFile, xlsxFile_resultDir, false);
				}

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index
					+ "  <指定A.xlsx文件> <指定B.xls文件>  ### 按顺序解析当前的xlsx xls 为对应的json文件 生成在相同文件名_时间戳的文件夹中   \n"
					+ Cur_Bat_Name + " #_" + rule_index
					+ "  A.xlsx B.xls C.xlsx   ### 按顺序解析当前的xlsx day20200202.xls 为对应的day_20200202.json文件 生成在相同文件名_时间戳的文件夹中    \n"
					+ Cur_Bat_Name + " #_" + rule_index
					+ "   ### 解析当前 shell-path 目录下的xlsx 文件 生成对应的.json 文件  没有直接返回    \n" + Cur_Bat_Name + " #_"
					+ rule_index
					+ "  C:\\Users\\xxx\\Desktop\\zbin\\J0_Data  ### 解析指定目录下的xlsx 文件 生成对应的.json 文件 没有直接返回    \n"

			;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);

				File tempFile = new File(curDirPath + File.separator + strInput);
				if (tempFile.exists() && !tempFile.isDirectory()) {
					String inputFileName = tempFile.getName().toLowerCase();
					if (inputFileName.endsWith(".xlsx") || inputFileName.endsWith(".xls")) {
						xlsxFileList.add(tempFile);
					}

				}

				File inputDir = new File(strInput);
				if (inputDir.exists() && inputDir.isDirectory()) {
					isDirOperation = true;
					inputDirFile = inputDir;
				}

				System.out.println("initParamsWithInputList[" + i + "] = " + strInput + "  inputDir.exists()="
						+ inputDir.exists() + "  inputDir.isDirectory()=" + inputDir.isDirectory());

			}

			if (xlsxFileList.size() == 0 && inputDirFile == null) {
				System.out.println("当前 输入的 xlsx 文件 为 空 无法获取 输入的 xlsx 请检查 输入!! ");
				File shellDir = new File(curDirPath);
				if (shellDir != null && shellDir.exists()) {
					File[] listArr = shellDir.listFiles();

					if (listArr == null || listArr.length == 0) {
						System.out.println("当前 输入的目录  " + inputDirFile.getAbsolutePath() + "没有 任何文件操作!!");

						return false;
					}
					for (int i = 0; i < listArr.length; i++) {
						File fileItem = listArr[i];

						String inputFileName = fileItem.getName().toLowerCase();
						if (inputFileName.endsWith(".xlsx") || inputFileName.endsWith(".xls")) {
							xlsxFileList.add(fileItem);
							isDirOperation = true;
							inputDirFile = shellDir;
						}

					}

				} else {
					System.out.println("当前 输入的 xlsx 文件为空  shell目录为空 无法获取 输入的 xlsx 请检查 输入!! ");
					return false;

				}

			}

			if (inputDirFile != null) {
				File[] listArr = inputDirFile.listFiles();
				if (listArr == null || listArr.length == 0) {
					System.out.println("当前 输入的目录  " + inputDirFile.getAbsolutePath() + "没有 任何文件操作!!");

					return false;
				}
				for (int i = 0; i < listArr.length; i++) {
					File fileItem = listArr[i];

					String inputFileName = fileItem.getName().toLowerCase();
					if (inputFileName.endsWith(".xlsx") || inputFileName.endsWith(".xls")) {
						xlsxFileList.add(fileItem);
					}

					System.out.println(
							"inputDir_xlsx[" + i + "] = " + fileItem.getAbsolutePath() + "  Size=" + fileItem.length());

				}

			}

			if (xlsxFileList.size() == 0) {
				System.out.println(
						"当前 输入的目录  inputDirFile =" + (inputDirFile == null ? "null" : inputDirFile.getAbsolutePath())
								+ "没有 任何文件的 .xls .xlsx文件进行操作!!");

				return false;

			}
			if (inputDirFile == null) {
				System.out.println("ZXX inputDirFile = null " + " xlsxFileList.size()=" + xlsxFileList.size()
						+ "   isDirOperation=" + isDirOperation);

			} else {
				System.out.println("ZXX inputDirFile =" + inputDirFile.getAbsolutePath() + "    xlsxFileList.size()="
						+ xlsxFileList.size() + "   isDirOperation=" + isDirOperation);

			}

			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		public void reverXlsxToJson(File xlsxFile, File jsonResultDirFile, boolean isDirOperation) {
			try {
				FileInputStream inp = new FileInputStream(xlsxFile.getAbsolutePath());

				ZipSecureFile.setMinInflateRatio(-1.0d);
				Workbook workbook = null;
//		            Workbook workbook = WorkbookFactory.create(inp);

				if (xlsxFile.getName().toLowerCase().trim().endsWith(".xls")) {
					workbook = new HSSFWorkbook(inp);
				} else {
//			             workbook = WorkbookFactory.create(inp);
					workbook = new XSSFWorkbook(inp);
				}

				// 获取sheet数
				int sheetNum = workbook.getNumberOfSheets();
				System.out.println("Shell_count = " + sheetNum);
				JSONObject jsonObject = new JSONObject();
				for (int s = 0; s < sheetNum; s++) {
					// Get the Sheet of s.
					Sheet sheet = workbook.getSheetAt(s);
					// 获取最大行数
					String shellName = sheet.getSheetName();

					int rownum = sheet.getPhysicalNumberOfRows();
					if (rownum <= 1) {
						continue;
					}

					// 获取第一行
					Row row1 = sheet.getRow(0);
					// 获取最大列数
					int colnum = row1.getPhysicalNumberOfCells();
//					System.out.println("shellIndex["+s+"]  shellName["+ shellName+"]  rownum["+ rownum+"]   colnum["+colnum+ "]");

					JSONArray jsonArray = new JSONArray();
					for (int i = 1; i < rownum; i++) {
						Row row = sheet.getRow(i);
//						System.out.println("currentRow = "+ i );
//		                    if(i > 10) {
//
//		                    	continue;
//		                    }
						if (row != null) {
//		                    List<Object> list = new ArrayList<>();
							JSONObject rowObj = new JSONObject();
							// 循环列
							for (int j = 0; j < colnum; j++) {
								Cell cellData = row.getCell(j);
								if (cellData != null) {
									// 判断cell类型
//									System.out.println("colum="+j);

									switch (cellData.getCellType()) {
									case NUMERIC: {
										rowObj.put(row1.getCell(j).getStringCellValue(),
												cellData.getNumericCellValue());
										break;
									}
									case FORMULA: {
										// 判断cell是否为日期格式
										if (DateUtil.isCellDateFormatted(cellData)) {
											// 转换为日期格式YYYY-mm-dd
											rowObj.put(row1.getCell(j).getStringCellValue(),
													cellData.getDateCellValue());
										} else {
											// 数字
											rowObj.put(row1.getCell(j).getStringCellValue(),
													cellData.getNumericCellValue());
										}
										break;
									}

									case STRING: {

//											System.out.println("row1.getCell(j).toString() = "+ row1.getCell(j).toString());
//											System.out.println("row1.getCell(j).getCellStyle() = "+ row1.getCell(j).getCellStyle());
//											System.out.println("row1.getCell(j).getCellType() = "+ row1.getCell(j).getCellType());

										String cellContent = null;

										try {
											cellContent = cellData.toString();

										} catch (Error e) {
											cellContent = "";

										}

										rowObj.put(row1.getCell(j).toString(), cellContent);

										// 表头 是 富文本 的 时候 调用 getRichStringCellValue() 和 getStringCellValue() 报错!!!
										// Exception in thread "main" java.lang.NoSuchMethodError:
										// org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst.xgetT()
										// Lorg/openxmlformats/schemas/officeDocument/x2006/sharedTypes/STXstring;

										// rowObj.put(row1.getCell(j).getRichStringCellValue().toString(),
										// cellData.getRichStringCellValue());
										// rowObj.put(row1.getCell(j).getStringCellValue().toString(),
										// cellData.getStringCellValue());

										break;
									}
									default:
										rowObj.put(row1.getCell(j).getStringCellValue(), "");
									}
								} else {

									try {
										rowObj.put(row1.getCell(j).getStringCellValue(), "");

									} catch (Error e) {
										System.out.println("发生异常 e=" + e);
										continue;

									}

								}
							}
							jsonArray.add(rowObj);
						}
					}
					String jsonName = sheet.getSheetName() + ".json";

					if (!isDirOperation) { // 如果是 单一的 输入 xlsx 文件 那么才 输出 sheetname.json
						File jsonFile = new File(jsonResultDirFile.getAbsolutePath() + File.separator + jsonName);

						System.out.println(jsonArray.toJSONString());

						writeContentToFile(jsonFile, jsonArray.toJSONString() + "\n");
					}

					jsonObject.put(sheet.getSheetName(), jsonArray);
				}
//		            System.out.println(jsonObject.toJSONString());
//				System.out.println("长度"+jsonObject.toJSONString().length());
//				String allSheetJsonFileName = "AllSheet_"+xlsxFile.getName().replace(".", "_")+".json";

				String getxlsxNameNoType = getFileNameNoPoint(xlsxFile);
				String resut_json_file_name = getxlsxNameNoType + ".json";
				ArrayList<String> jsonRowList = new ArrayList<String>();

				String JsonOneLine = jsonObject.toJSONString(); // 把它分隔多份 不要总是一行

				// 有都逗号 就换行
				JsonOneLine = JsonOneLine.replace(",", ",\n");

				File allSheetJsonFile = new File(
						jsonResultDirFile.getAbsolutePath() + File.separator + resut_json_file_name);

				writeContentToFile(allSheetJsonFile, JsonOneLine);

				System.out.println(" xlsxFile-->" + xlsxFile.getAbsolutePath() + " 解析成功!");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 把 pdf中指定的页数 分隔出来作为一个新的pdf文件 #_36 A.pdf 31_341
	// 在本地目录创建 /sdcard/zapp 和 /sdcard/zmain 相关的 dir目录
	class Zapp_Zmain_dir_Create_Rule_37 extends Basic_Rule {

		boolean isZappFlag;

		boolean isZmainFlag;

		boolean isZmainInputParamFlag;
		boolean isZappInputParamFlag;

		Zapp_Zmain_dir_Create_Rule_37() {
			super("#", 37, 3); //
			isZappFlag = false;

			isZmainFlag = false;
			isZmainInputParamFlag = false;
			isZappInputParamFlag = false;
		}

		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {

				String strParam = inputParamList.get(i);
				System.out.println("strParam[" + i + "] = " + strParam);
				if ("zmain".equals(strParam)) {
					System.out.println("X2 strParam[" + i + "] = " + strParam);
					isZmainFlag = true;
					isZmainInputParamFlag = true;

				}

				if ("zapp".equals(strParam)) {
					System.out.println("X3 strParam[" + i + "] = " + strParam);

					isZappFlag = true;
					isZappInputParamFlag = true;
				}

			}

			if (isZmainInputParamFlag == false && isZappInputParamFlag == false) { // 说明用户没有输入任何指定的参数 那么设置 两个值都为 true
				System.out.println("X4 strParam");

				isZmainFlag = true;
				isZappFlag = true;
			}
			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			// TODO Auto-generated method stub

			String absShellPath = curDirFile.getAbsolutePath();
			System.out.println(" 当前Shell的路径为: curDirFile =  " + absShellPath);

			ArrayList<String> Zmain_createDirNameList = new ArrayList<String>();

			ArrayList<String> Zapp_createDirNameList = new ArrayList<String>();

			if (isZmainFlag) {
				Zmain_createDirNameList.add("gif");
				Zmain_createDirNameList.add("gif_land");
				Zmain_createDirNameList.add("gif_port");
				Zmain_createDirNameList.add("jpg");
				Zmain_createDirNameList.add("jpg_land_home");
				Zmain_createDirNameList.add("jpg_port_home");
				Zmain_createDirNameList.add("jpg_port_wall");
				Zmain_createDirNameList.add("jpg_land_wall");
				Zmain_createDirNameList.add("mp3");
				Zmain_createDirNameList.add("mp4");
				Zmain_createDirNameList.add("mp4_home");
				Zmain_createDirNameList.add("mp4_music");
				Zmain_createDirNameList.add("mp4_scene");

			}

			if (isZappFlag) {

				Zapp_createDirNameList.add("gif");
				Zapp_createDirNameList.add("gif_land");
				Zapp_createDirNameList.add("gif_port");
				Zapp_createDirNameList.add("jpg");
				Zapp_createDirNameList.add("jpg_top_land");
				Zapp_createDirNameList.add("jpg_top_port");
				Zapp_createDirNameList.add("mp4");
				Zapp_createDirNameList.add("mp4_raw");
				Zapp_createDirNameList.add("mp4_hua");

			}

			if (Zmain_createDirNameList.size() > 0 && isZmainFlag) {
				System.out.println("══════════════════ zmain_begin ════════════════");

				for (int i = 0; i < Zmain_createDirNameList.size(); i++) {
					String dirName = Zmain_createDirNameList.get(i);
					String zmain_dirName = "zmain" + File.separator + dirName;
					String abs_path_dir = absShellPath + File.separator + zmain_dirName;
					File dirFile = new File(abs_path_dir);
					dirFile.mkdirs();
					System.out.println("创建_zmain_目录[" + i + "]  = " + abs_path_dir);

				}
				System.out.println("══════════════════ zmain_end ════════════════");
			}

			if (Zapp_createDirNameList.size() > 0 && isZappFlag) {
				System.out.println("══════════════════ zapp_begin ════════════════");

				for (int i = 0; i < Zapp_createDirNameList.size(); i++) {
					String dirName = Zapp_createDirNameList.get(i);
					String zmain_dirName = "zapp" + File.separator + dirName;
					String abs_path_dir = absShellPath + File.separator + zmain_dirName;
					File dirFile = new File(abs_path_dir);
					dirFile.mkdirs();
					System.out.println("创建_zapp_目录[" + i + "]  = " + abs_path_dir);
				}
				System.out.println("══════════════════ zapp_end ════════════════");

			}

			System.out.println("当前 创建 zapp zmain 结构目录的方法 Rule_" + rule_index + " 执行完毕!!  ");

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {
			// TODO Auto-generated method stub

			return Cur_Bat_Name + " #_" + rule_index + "   ###  创建zapp 和 zmain的文件夹结构 /sdcard/zmain /sdcard/zapp   \n"
					+ Cur_Bat_Name + " #_" + rule_index
					+ "  zmain  zapp    ### 创建zapp 和 zmain的文件夹结构 /sdcard/zmain /sdcard/zapp \n" + Cur_Bat_Name + " #_"
					+ rule_index + " zmain      ### 只在当前目录创建 zmain 的 /sdcard/zmain 目录结构 \n" + Cur_Bat_Name + " #_"
					+ rule_index + " zapp      ### 只在当前目录创建 zapp 的 /sdcard/zapp 目录结构 \n"

			;

		}

	}

	class Seperate_Pdf_Rule_36 extends Basic_Rule {

		// 参数的Key是 从参数输入的 文件的名称 参数的Value是对这个文件进行操作的 页面数值

		File targetPDF_File = null; // 目标 pdf target
		// 1. 有前 2.有后 3.有前后
		ArrayList<String> page_page_StrArr; // 输入的分隔的字符串 _10 18_ 10_11 12_20

		Seperate_Pdf_Rule_36() {
			super("#", 36, 3); //

			page_page_StrArr = new ArrayList<String>();
		}

		@Override
		boolean allowEmptyDirFileList() {

			return true;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);

				File tempFile = new File(curDirPath + File.separator + strInput);
				if (tempFile.exists() && !tempFile.isDirectory()) {
					String inputFileName = tempFile.getName().toLowerCase();
					if (inputFileName.endsWith(".pdf")) {
						targetPDF_File = tempFile;
						continue;
					}
				}

				if (strInput.contains("_") && !strInput.contains("#") && strInput.contains("page_")) {
					// 去除掉_ 下划线后 全是数值
					String verifyStr = strInput.replace("page_", "");
					String verifyStr_2 = verifyStr.replace("_", "");
					if (isNumeric(verifyStr_2)) {
						page_page_StrArr.add(verifyStr);
					}
				}

			}

			if (targetPDF_File == null || page_page_StrArr.size() == 0) {
				System.out.println("用户对于 规则 rule" + rule_index + "输入错误！  没有输入 指定的 pdf文件!");
				return false;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			// TODO Auto-generated method stub
			if (targetPDF_File == null || page_page_StrArr.size() == 0) {
				System.out.println("用户对于 规则 rule" + rule_index + "输入错误！  没有输入 指定的 pdf文件!");

				return null;
			}

			try {
				for (int i = 0; i < page_page_StrArr.size(); i++) {

					System.out.println("规则" + rule_index + " Param" + i + "[" + page_page_StrArr.get(i) + "]");
				}

				for (int i = 0; i < page_page_StrArr.size(); i++) {
					String index_str_item = page_page_StrArr.get(i);
					// 可能是 -1 , index_pre 为 -1 标示 从第一页 开始遍历
					int index_pre = calculPageString(index_str_item, true);
					System.out.println("calculPageString_index_pre = " + index_pre);
					if (index_pre == -1 || index_pre < 0) { // 从第一页开始计数的
						index_pre = 1;
					}

					// index_end 为 -1 标示 读取到 pdf的最后一页 这个页数需要读文件 才能拿取到
					int index_end = calculPageString(index_str_item, false);
					System.out.println("calculPageString_index_end = " + index_end);

					PdfReader reader = new PdfReader(targetPDF_File.getAbsolutePath());

					int numberOfPages_Pdf = reader.getNumberOfPages(); // 读取文件大小
					reader.close(); // 关闭

					if (index_end == -1 || index_end < 0) {
						index_end = numberOfPages_Pdf;
					}

					// 如果索引超过了 文件的页数 那么更正
					if (index_end > numberOfPages_Pdf) {
						index_end = numberOfPages_Pdf;
					}

					if (index_pre > index_end) {
						int temp = index_pre;
						index_pre = index_end;
						index_end = temp;
					}

					System.out.println("当前Page_Page字符串【" + index_str_item + "】  当前解析索引 pre_index=" + index_pre
							+ "  end_index=" + index_end + " PDF总页数=" + numberOfPages_Pdf);
					String newPdfName = targetPDF_File.getParentFile().getAbsolutePath() + File.separator
							+ (getFileNameNoPoint(targetPDF_File.getName())) + "_" + index_pre + "_" + index_end
							+ ".pdf";

					reader = new PdfReader(targetPDF_File.getAbsolutePath());
					Document doc = new Document();
					String splitFileName = newPdfName;
					PdfCopy pdfCopy;
					try {
						pdfCopy = new PdfCopy(doc, new FileOutputStream(splitFileName));
					} catch (FileNotFoundException | DocumentException e) {
						System.out.println("分隔失败---》 当前Page_Page字符串【" + index_str_item + "】  当前解析索引 pre_index="
								+ index_pre + "  end_index=" + index_end + " PDF总页数=" + numberOfPages_Pdf);

						throw new Exception("切割文件副本创建失败 AAAAA1111 ");

					}
					doc.open();
					// 将pdf按页复制到新建的PDF中
					System.out.println("PDF 循环起点 index_pre=" + index_pre + "   终点index_end=" + index_end);
					for (int j = index_pre; j <= index_end; ++j) {
//				                System.out.println("jAA  = "+ j);
						doc.newPage();
//				                System.out.println("jBB  = "+ j);
						PdfImportedPage page = pdfCopy.getImportedPage(reader, j);
						pdfCopy.addPage(page);
					}
					doc.close();

					pdfCopy.close();

				}

			} catch (Exception e) {
				System.out.println("当前 执行 pdf 分割操作报错！AAA " + e.getLocalizedMessage());

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		int calculPageString(String pageStr, boolean isPre) {
			int pageIndex = -1;
			String fixed_str = pageStr.replace("__", "_").trim();
			fixed_str = fixed_str.replace("___", "_");
			fixed_str = fixed_str.replace("__", "_");
			fixed_str = fixed_str.replace("__", "_"); // 只有一个 _ 下划线了

			String[] splitArr = fixed_str.split("_");

			if (splitArr == null) {
				return pageIndex;
			}

			if (fixed_str.startsWith("_")) { // 如果以_ 开头说明 没有指定 起点 指定了终点
				if (isPre) {
					return pageIndex;
				} else { //
					String endIndex = fixed_str.replace("_", "");
					if (isNumeric(endIndex)) {
						return Integer.parseInt(endIndex);
					}
				}

			} else if (fixed_str.endsWith("_")) { // 没有指定末尾页数 那么返回 -1 指定了起始页
				if (isPre) {
					String preIndex = fixed_str.replace("_", "");
					if (isNumeric(preIndex)) {
						return Integer.parseInt(preIndex);
					}

				} else { //
					return pageIndex;
				}

			}

			if (splitArr.length != 2) {
				return pageIndex;
			}

			String preStr = splitArr[0];

			String endStr = splitArr[1];
			if (isPre && isNumeric(preStr)) {
				return Integer.parseInt(preStr);

			} else if (!isPre && isNumeric(endStr)) {
				return Integer.parseInt(endStr);
			}

			return pageIndex;
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index
					+ "  <指定Pdf文件>  page_1_10 page_11_20 page_30_40  ###  PDF从第一页开始 分隔当前的pdf文件中的1_10page 形成新的文件   \n"
					+ Cur_Bat_Name + " #_" + rule_index
					+ "  A.pdf  page_1_10  page_2_20  ### 解析当前的A.pdf 生成 下划线分隔 A_1_10.pdf 文件 分隔新page的pdf文件 \n"
					+ Cur_Bat_Name + " #_" + rule_index
					+ "  A.pdf  page_10_  page_50_  ### 解析当前的A.pdf 生成 从第10页开始解析到最后  从第50页开始解析到最后 \n"

			;
		}

	}

	class Append_Pdf_Rule_35 extends Basic_Rule {

		ArrayList<File> mPdfFileList; // 当前 cmd 参数给出的 pdf 文件列表 依次合并

		Append_Pdf_Rule_35() {
			super("#", 35, 3); //
			mPdfFileList = new ArrayList<File>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);

				File tempFile = new File(curDirPath + File.separator + strInput);
				if (tempFile.exists() && !tempFile.isDirectory()) {
					String inputFileName = tempFile.getName().toLowerCase();
					if (inputFileName.endsWith(".pdf")) {
						mPdfFileList.add(tempFile);
					}
				}
			}

			if (mPdfFileList.size() < 2) {
				System.out.println("当前 命令行输入的 pdf 文件个数 小于2个无法 执行合并 操作!");
				return false;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			// TODO Auto-generated method stub
			if (mPdfFileList.size() < 2) {
				System.out.println("当前 命令行输入的 pdf 文件个数 小于2个无法 执行合并 操作!");
				return null;
			}

			try {

				String originName = mPdfFileList.get(0).getName();
				String currentTimeStamp = "_" + getTimeStamp();
				String newPdfFileName = getFileNameNoPoint(originName) + currentTimeStamp + ".pdf";

				File newPdfFile = new File(curDirPath + File.separator + newPdfFileName);
				File mergedFIle = mulFile2One(mPdfFileList, newPdfFile.getAbsolutePath());
				System.out.println(" 新 合并文件大小:" + mergedFIle.length());

				if (mergedFIle.length() > 0) {
					for (int i = 0; i < mPdfFileList.size(); i++) {
						File tempFile = mPdfFileList.get(i);
						tempFile.delete();
					}

					tryReName(mergedFIle, originName);
				}

				System.out.println("OK!  PDF 文件 已经 生成 --> " + originName);

			} catch (Exception e) {
				System.out.println("当前 执行 pdf 合并操作报错！" + e.getLocalizedMessage());

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index
					+ "  <指定A.Pdf文件> <指定B.Pdf文件>  ### 按顺序合并当前所有pdf到一个pdf文件中 原有pdf删除   \n" + Cur_Bat_Name + " #_"
					+ rule_index + "  A.pdf B.pdf C.pdf   ### 按顺序合并当前所有pdf到一个pdf文件中 原有pdf删除    \n";
		}

		/*
		 * String ruleTip(String type, int index, String batName, OS_TYPE curType) {
		 * String itemDesc = ""; String desc_true =
		 * "  对给定的 pdf文件A   pdf文件B  pdf文件C 文件进行合并 合并的pdf文件名称为 pdfA文件名称,并删除原pdf文件 ";
		 *
		 * if (curType == OS_TYPE.Windows) { itemDesc = batName.trim() + ".bat  " + type
		 * + "_" + index + "      <指定Pdf文件A> <指定Pdf文件B>   <指定Pdf文件C>     ## [索引 " +
		 * index + "]  描述: " + desc_true + "\n";
		 *
		 * } else { itemDesc = batName.trim() + ".sh " + type + "_" + index +
		 * "     <指定Pdf文件A>  <指定Pdf文件B>   <指定Pdf文件C>    ##   [索引 " + index + "]  描述:" +
		 * desc_true; }
		 *
		 * return itemDesc; }
		 */

	}

	public static File mulFile2One(List<File> files, String targetPath) throws IOException {
		// pdf合并工具类
		PDFMergerUtility mergePdf = new PDFMergerUtility();
		for (File f : files) {
			if (f.exists() && f.isFile()) {
				// 循环添加要合并的pdf
				mergePdf.addSource(f);
			}
		}
		// 设置合并生成pdf文件名称
		mergePdf.setDestinationFileName(targetPath);
		// 合并pdf
		mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		return new File(targetPath);
	}

	static final transient Rule34_MP3_NodeImpl Rule34_RootNodeImpl = new G2_ApplyRuleFor_TypeFile().new Rule34_MP3_NodeImpl(
			0, "全部", 0, 0, null);

	class MP3_Revert2JSOn_Rule_34 extends Basic_Rule {

		int Node_ID_Num = 0;
		String[] Alphabet;

		int getNextNodeID() {
			Node_ID_Num++;
			return Node_ID_Num;
		}

		// 第一部分的 字母表 序列 level== 1
		ArrayList<Rule34_MP3_NodeImpl> alphabet_node_list;

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			return super.initParamsWithInputList(inputParamList);
		}

		MP3_Revert2JSOn_Rule_34() {
			super("#", 34, 4);
			Alphabet = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
					"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
			alphabet_node_list = new ArrayList<Rule34_MP3_NodeImpl>();
			Node_ID_Num = 0;
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_34   ### 解析当前的MP3文件生成对应的 mp3_tree.json 文件  \n" + Cur_Bat_Name
					+ " #_34    ### 解析当前的MP3文件生成对应的 mp3_tree.json 文件 \n";
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub
			ArrayList<File> allMp3FileList = new ArrayList<File>();

			ArrayList<File> mp3List_1 = subFileTypeMap.get(".mp3");
			ArrayList<File> mp3List_2 = subFileTypeMap.get(".MP3");
			if (mp3List_1 != null) {

				allMp3FileList.addAll(mp3List_1);
			}
			if (mp3List_2 != null) {

				allMp3FileList.addAll(mp3List_2);
			}

			if (allMp3FileList.size() == 0) {
				System.out.println("Rule34 当前的文件夹中 MP3文件的个数为0  请检查!!!");
				return null;
			}

			for (int i = 0; i < Alphabet.length; i++) {
				String alphaItem = Alphabet[i];

				/*
				 * public Rule34_MP3_NodeImpl(long id, String name, int count,int level , String
				 * xmp3Path) { this.id = id; this.name = name; this.count = count; this.level =
				 * level; this.mp3path = xmp3Path;
				 *
				 * }
				 */
				Rule34_MP3_NodeImpl Rule34_RootNodeImpl = mG2_Object.new Rule34_MP3_NodeImpl(getNextNodeID(), alphaItem,
						0, 1, null);
				alphabet_node_list.add(Rule34_RootNodeImpl);
			}
			Rule34_RootNodeImpl.children.addAll(alphabet_node_list);

			// 把 当前 目录下的 mp3 依据 作者分类 分好

			HashMap<String, ArrayList<Rule34_MP3_NodeImpl>> mArtist_File_Map = new HashMap<String, ArrayList<Rule34_MP3_NodeImpl>>();

			// 依据 备注 进行 分类 得到的 Item 粤语 英语 纯语 更多分类 留下扩展
			HashMap<String, ArrayList<Rule34_MP3_NodeImpl>> mComment_File_Map = new HashMap<String, ArrayList<Rule34_MP3_NodeImpl>>();

			for (int i = 0; i < allMp3FileList.size(); i++) {

				try {
					File File_realMP3 = allMp3FileList.get(i);
					String mp3Name_nopoint = getFileNameNoPoint(allMp3FileList.get(i).getName());

					String mp3File_Path = File_realMP3.getAbsolutePath();
					Mp3File mp3file_item = new Mp3File(allMp3FileList.get(i).getAbsolutePath());
					if (mp3file_item.hasId3v2Tag()) {
						ID3v2 id3v2Tag = mp3file_item.getId3v2Tag();

						String titleName_tag = id3v2Tag.getTitle().trim();
						String ArtistName_tag = id3v2Tag.getArtist().trim();
						String CommentName_tag = id3v2Tag.getComment().trim();

						if (mArtist_File_Map.containsKey(ArtistName_tag)) {
							ArrayList<Rule34_MP3_NodeImpl> curArr = mArtist_File_Map.get(ArtistName_tag);
							curArr.add(mG2_Object.new Rule34_MP3_NodeImpl(getNextNodeID(), titleName_tag, 0, 3,
									mp3File_Path));
						} else {
							ArrayList<Rule34_MP3_NodeImpl> targetArr = new ArrayList<Rule34_MP3_NodeImpl>();
							targetArr.add(mG2_Object.new Rule34_MP3_NodeImpl(getNextNodeID(), titleName_tag, 0, 3,
									mp3File_Path));
							mArtist_File_Map.put(ArtistName_tag, targetArr);
						}

					}
				} catch (Exception e) {
					System.out.println("RuleIndex 34  解析MP3文件出现错误! ");

				}
			}

			Show_AddNode_MP3Map(mArtist_File_Map, alphabet_node_list);

			showRootNodeContent(Rule34_RootNodeImpl);

			System.out.println("RuleIndex 34   MP3文件属性的操作 执行完成!");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		// 从 上 到下 依次 显示 当前的 Node 的 内容
		void showRootNodeContent(Rule34_MP3_NodeImpl rootNode) {

//			{
//				  "id": -1,
//				  "name": "全部",
//				  "count": 21,
//				  "children": [  ]
//			}

			System.out.println("════════════════════════════════" + " Root JSON" + "════════════════════════════════");

			String mp3Json = rootNode.json();

//			mp3Json = StringEscape.unescapeJava(mp3Json);

			if (JSONUtil.isJson(mp3Json)) {
				System.out.println("═════════════════ 是JSON  ═════════════════");

				mp3Json = JSONUtil.formatJsonStr(mp3Json);
				System.out.println(mp3Json);
			} else {
				System.out.println("═════════════════ 不是JSON  ═════════════════");

				mp3Json = JSONUtil.formatJsonStr(mp3Json);
//				System.out.println(mp3Json);

			}

			File Mp3_Json_File = new File(curDirFile.getAbsolutePath() + File.separator + "MP3_JSON.json");
			writeContentToFile(Mp3_Json_File, mp3Json);
			NotePadOpenTargetFile(Mp3_Json_File.getAbsolutePath());

		}

		Rule34_MP3_NodeImpl getNodeImpl_With_Zimu(ArrayList<Rule34_MP3_NodeImpl> alphabet_node_list,
				String charAlhapbet) {
			Rule34_MP3_NodeImpl selectedNode = null;
			for (int i = 0; i < alphabet_node_list.size(); i++) {
				Rule34_MP3_NodeImpl node = alphabet_node_list.get(i);
				if (node.name.toUpperCase().equals(charAlhapbet.toUpperCase())) {
					selectedNode = node;
					break;
				}

			}

			return selectedNode;

		}

		@SuppressWarnings("unchecked")
		boolean Show_AddNode_MP3Map(HashMap<String, ArrayList<Rule34_MP3_NodeImpl>> xMP3FileMap,
				ArrayList<Rule34_MP3_NodeImpl> alphabet_node_list) {
			boolean executeFlag = false;
			Map.Entry<String, ArrayList<Rule34_MP3_NodeImpl>> entry;

			if (xMP3FileMap != null) {
				Iterator iterator = xMP3FileMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<Rule34_MP3_NodeImpl>>) iterator.next();

					// 获取 名称的 首字母
					String arrTag = entry.getKey(); // Map的Value // 作者名称
					String Alphabet_Word = getFirstZiMu(arrTag);
					Rule34_MP3_NodeImpl selectedNode_level_1 = getNodeImpl_With_Zimu(alphabet_node_list, Alphabet_Word);
					ArrayList<Rule34_MP3_NodeImpl> fileArr_level_3 = entry.getValue(); // Map的Value 歌曲MP3

					Rule34_MP3_NodeImpl artistNode_level_2 = mG2_Object.new Rule34_MP3_NodeImpl(getNextNodeID(), arrTag,
							fileArr_level_3.size(), 2, null);

					selectedNode_level_1.addChildren(artistNode_level_2);

					System.out.println("═══════════════════════ arrTag[" + arrTag + "]  alphabet=[" + Alphabet_Word
							+ "] all[" + fileArr_level_3.size() + "] ═══════════════════════");

					for (int i = 0; i < fileArr_level_3.size(); i++) {
						Rule34_MP3_NodeImpl mp3FileNode_level_3 = fileArr_level_3.get(i);

						System.out.println("____________ arrTag[" + arrTag + "] alphabet=[" + Alphabet_Word + "] index["
								+ (i + 1) + "] all[" + fileArr_level_3.size() + "] ____________");
						System.out.println(mp3FileNode_level_3.toString());

						artistNode_level_2.addChildren(mp3FileNode_level_3);
					}

				}
			}

			return executeFlag;
		}

		String getFirstZiMu(String srcStr) {
			String firstZimu = "U"; // 默认为 Unknow;
			if (srcStr == null || "".equals(srcStr.trim())) {
				return firstZimu;
			}

			if (!isContainChinese(srcStr)) { // 如果 不包含中文 那么 取这个词的 第一个字符
				String char_1 = srcStr.substring(0, 1).toUpperCase();
				firstZimu = char_1;

			} else {
				String char_1 = srcStr.substring(0, 1).toUpperCase();
//				System.out.println("X2 char_1 = "+char_1 );
				if (!isContainChinese(char_1)) {
					firstZimu = char_1;
				} else { // 如果第一个字母为汉字 那么取到这个字的 拼音的 第一个词
					String pinyinStr = ToPinyin(char_1);
//					System.out.println("X2  pinyinStr = "+pinyinStr );
					String char_1_fixed = pinyinStr.substring(0, 1).toUpperCase();
					firstZimu = char_1_fixed;
				}

			}

//			System.out.println("X3  firstZimu = "+firstZimu );
			return firstZimu;

		}

	}

	class Rule34_MP3_NodeImpl implements Rule34_MP3_Node, Serializable {

		private static final long serialVersionUID = 1L;

		public long id;
		public String name;
		public int count;
		public int level;
		public String mp3path;
		public List<Rule34_MP3_NodeImpl> children = new ArrayList<>();
		private transient long selectedChildId;

//	    {
//	    	  "id": -1,
//	    	  "name": "全部",
//	    	  "count": 21,
//	    	  "children": []
//	    }

		@Override
		public String json() {
			count = children.size();
			System.out.println("___________【" + name + "】 Node_Level_" + level + "节点情况 Begin ___________");
			for (int i = 0; i < children.size(); i++) {
				System.out.println(children.get(i).toString());
			}
			System.out.println("___________【" + name + "】Node_Level_" + level + "节点情况 End ___________");
			System.out.println();

			StringBuilder sb_json = new StringBuilder();
			sb_json.append("{\n");
			sb_json.append("\"id\": " + id + ",\n");
			sb_json.append("\"name\": " + "\"" + name + "\"" + ",\n");
			sb_json.append("\"count\": " + count + ",\n");
			sb_json.append("\"level\": " + level + ",\n");

			if (mp3path != null) {
				sb_json.append("\"mp3path\": " + "\"" + (mp3path.replace("\\", "\\\\")) + "\"" + ",\n");
			} else {
				sb_json.append("\"mp3path\": \"null\"" + ",\n");
			}

			if (children == null || children.size() == 0) {
				sb_json.append("\"children\": " + "[]\n");
			} else {
				sb_json.append("\"children\": " + "[\n");
				for (int i = 0; i < children.size(); i++) {
					Rule34_MP3_NodeImpl childNode = children.get(i);
					if (childNode != null && childNode.children() != null && childNode.children().size() > 0) {
						System.out.println("childNode.name = " + childNode.name + "  childNode.level=" + childNode.level
								+ "  childNode.id=" + childNode.id + "  childNode.count="
								+ childNode.children().size());

						// 子节点 之间 使用 , 逗号 分隔
						if (i == children.size() - 1) {
							sb_json.append(childNode.json() + "\n");
						} else {
							sb_json.append(childNode.json() + ",\n");
						}

					} else {
						System.out.println("结点 的 childNode 为空！！！ 为叶子节点");
						if (childNode != null) {
							if (i == children.size() - 1) {
								sb_json.append(childNode.json() + "\n");
							} else {
								sb_json.append(childNode.json() + ",\n");
							}

						}

					}

				}
				sb_json.append("]\n");
			}

			sb_json.append("}\n");
			return sb_json.toString();
		}

		@Override
		public void setmp3path(String mp3Path) {
			this.mp3path = mp3Path;

		}

		@Override
		public void setlevel(int level) {
			// TODO Auto-generated method stub
			this.level = level;
		}

		@Override
		public int level() {
			// TODO Auto-generated method stub
			return level;
		}

		@Override
		public String mp3path() {
			// TODO Auto-generated method stub
			return mp3path;
		}

		public Rule34_MP3_NodeImpl(long id, String name, int count, int level, String xmp3Path) {
			this.id = id;
			this.name = name;
			this.count = count;
			this.level = level;
			this.mp3path = xmp3Path;
			children = new ArrayList<>();

		}

		@Override
		public long id() {
			return id;
		}

		@Nonnull
		@Override
		public String text() {
			return name + "(" + count + ")";
		}

		@Override
		public long selectedChild() {
			return selectedChildId;
		}

		@Override
		public void setSelectedChild(long id) {
			selectedChildId = id;
		}

		@Nullable
		@Override
		public List<? extends Rule34_MP3_Node> children() {
			return children;
		}

		public void setChildren(List<Rule34_MP3_NodeImpl> children) {
			this.children = children;
		}

		public void addChildren(Rule34_MP3_NodeImpl oneChildren) {
			this.children.add(oneChildren);
		}

		@Override
		public Rule34_MP3_Node getSelectedChild() {
			int i = getSelectedChildPosition();
			if (i == -1)
				return null;
			// in case of IndexOutOfBoundsException
			if (i >= 0 && i < children.size()) {
				return children.get(i);
			} else {
				return null;
			}
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "text=[" + text() + "] " + "  id=[" + id + "] " + "  level=[" + level() + "]" + "  count=["
					+ children.size() + "]" + " mp3path=[" + mp3path() + "]";
		}

		private int getSelectedChildPosition() {
			if (children == null)
				return -1;
			for (int i = 0; i < children.size(); i++) {
				Rule34_MP3_NodeImpl nodeImpl = children.get(i);
				if (nodeImpl == null)
					continue;
				if (nodeImpl.id == selectedChildId) {
					return i;
				}
			}
			return -1;
		}

	}

	public interface Rule34_MP3_Node {

		/**
		 * 标记唯一性
		 *
		 * @return -1 when it is root
		 */
		long id();

		String json();

		// 当前的层级
		int level();

		// 当前的对应的 MP3 文件的 全路径
		String mp3path();

		void setmp3path(String mp3Path);

		void setlevel(int level);

		/**
		 * 显示文案
		 */
		@Nonnull
		String text();

		/**
		 * 被选中的Id
		 *
		 * @return -1 when {@link #children()} is null
		 */
		long selectedChild();

		void setSelectedChild(long id);

		/**
		 * 子树(为空表示是叶节点)
		 *
		 * @return null when it is a leaf
		 */
		@Nullable
		List<? extends Rule34_MP3_Node> children();

		/**
		 * 返回被选中的child
		 */
		Rule34_MP3_Node getSelectedChild();

	}

	class MP3_Prop_Fixed_Rule_33 extends Basic_Rule {
		String mTitle; // mp3文件的 标题
		String mArtist; // mp3文件的 作者
		String mComment; // mp3文件的 备注
		boolean isCommentAppend; // 是否是在Comment 进行追加 而不是替换

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();
				if (paramItem_lower_trim.contains("title_")) {
					String replaceTitle = paramItem.replace("title_", "").replace("Title_", "").trim();
					mTitle = replaceTitle;
				}

				if (paramItem_lower_trim.contains("artist_")) {
					String replaceArtist = paramItem.replace("artist_", "").replace("Artist_", "").trim();
					mArtist = replaceArtist;
				}

				if (paramItem_lower_trim.contains("comment_")) {
					String replaceComment = paramItem.replace("comment_", "").replace("Comment_", "").trim();
					mComment = replaceComment;
				}
				if (paramItem_lower_trim.contains("commentappend_")) {
					String replaceComment = paramItem.replace("commentappend_", "").replace("Commentappend_", "")
							.trim();
					mComment = replaceComment;
					isCommentAppend = true;
				}

			}
			System.out.println("用户输入的 三个替换参数 mTitle=" + mTitle + "  mArtist=" + mArtist + "   mComment=" + mComment);

			if (mTitle == null && mArtist == null && mComment == null) {

				Flag = false;
				System.out.println("无法检测到用户输入的 需要修改的 MP3的三个属性 Title_xx  Artist_xxx Comment_xxx 程序执行失败!  请检查 ");
			}

			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		MP3_Prop_Fixed_Rule_33() {
			super("#", 33, 4);
			isCommentAppend = false;
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_33   artist_周杰伦  comment_国语  title_稻香     ### 把当前的 mp3的属性进行批量的修改  \n"
					+ Cur_Bat_Name + " #_33  artist_周杰伦  comment_国语   ### 把当前的 mp3的属性comment 和 artist 修改 \n"
					+ Cur_Bat_Name + " #_33  artist_周杰伦  title_稻香   ### 把当前的 mp3的属性comment 和 title 修改 \n" + Cur_Bat_Name
					+ " #_33  comment_国语  title_稻香   ### 把当前的 mp3的属性title 和 comment 修改 \n" + Cur_Bat_Name
					+ "  #_33 comment_国语   ### 把当前的 mp3的属性commont 改为国语  \n" + Cur_Bat_Name
					+ "  #_33 comment_粤语   ### 把当前的 mp3的属性commont 改为粤语   \n" + Cur_Bat_Name
					+ "  #_33 comment_英语   ### 把当前的 mp3的属性commont 改为英语   \n" + Cur_Bat_Name
					+ "  #_33 comment_纯语   ### 把当前的 mp3的属性commont 改为纯语   \n" + Cur_Bat_Name
					+ "  #_33 commentappend_收藏   ### 把当前的 mp3的属性commont 追加一个 原始Comment_收藏 为 Comment   \n" + Cur_Bat_Name
					+ "  #_33 commentappend_追赠   ### 把当前的 mp3的属性commont 追加一个 原始Comment_追赠 为 Comment   \n" + Cur_Bat_Name
					+ "  #_33 comment_纯语   ### 把当前的 mp3的属性commont 改为纯语   \n"

					+ Cur_Bat_Name + "  #_33 artist_周杰伦   ### 把当前的 mp3的属性artist 改为周杰伦 \n" + Cur_Bat_Name
					+ "  #_33 title_晴天   ### 把当前的 mp3的属性title 改为晴天 \n";
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub
			ArrayList<File> allMp3FileList = new ArrayList<File>();

			ArrayList<File> mp3List_1 = subFileTypeMap.get(".mp3");
			ArrayList<File> mp3List_2 = subFileTypeMap.get(".MP3");
			if (mp3List_1 != null) {

				allMp3FileList.addAll(mp3List_1);
			}
			if (mp3List_2 != null) {

				allMp3FileList.addAll(mp3List_2);
			}

			if (allMp3FileList.size() == 0) {
				System.out.println("当前的文件夹中 MP3文件的个数为0  请检查!!!");
				return null;
			}

			// 没有 tag的 mp3文件的集合 // 估计会改名
			ArrayList<File> unTagedMp3FileList = new ArrayList<File>();

			String TargetDirName = "MP3_Fixed_" + getTimeStamp();

			String fixed_name = null;
			for (int i = 0; i < allMp3FileList.size(); i++) {

				try {
					String mp3Name_nopoint = getFileNameNoPoint(allMp3FileList.get(i).getName());

					File mp3File = allMp3FileList.get(i);
					Mp3File mp3file = new Mp3File(allMp3FileList.get(i).getAbsolutePath());
					String d3v1_title = null;
					String d3v1_artist = null;
					String d3v1_comment = null;

					if (mp3file.hasId3v1Tag()) {

						ID3v1 id3v1Tag = mp3file.getId3v1Tag();
//						  System.out.println("Track: " + id3v1Tag.getTrack());
//						  System.out.println("Artist: " + id3v1Tag.getArtist());
//						  System.out.println("Title: " + id3v1Tag.getTitle());
//						  System.out.println("Album: " + id3v1Tag.getAlbum());
//						  System.out.println("Year: " + id3v1Tag.getYear());
//						  System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
//						  System.out.println("Comment: " + id3v1Tag.getComment());
						d3v1_title = id3v1Tag.getTitle();
						d3v1_artist = id3v1Tag.getArtist();
						d3v1_comment = id3v1Tag.getComment();

					}

					if (mp3file.hasId3v2Tag()) {
						ID3v2 id3v2Tag = mp3file.getId3v2Tag();
						String titleName_old = id3v2Tag.getTitle();
						String ArtistName_old = id3v2Tag.getArtist();
						String CommentName_old = id3v2Tag.getComment();

						boolean mTitleExist = false;
						boolean mCommentExist = false;
						boolean mArtistExist = false;
//							String mTitle; // mp3文件的 标题
//							String mArtist; // mp3文件的 作者
//							String mComment; // mp3文件的 备注
						if (mTitle != null && !"".equals(mTitle)) {
							id3v2Tag.setTitle(mTitle);
							titleName_old = mTitle;
							mTitleExist = true;
						}

						if (mArtist != null && !"".equals(mArtist)) {
							id3v2Tag.setArtist(mArtist);
							mArtistExist = true;
						}

						if (mComment != null && !"".equals(mComment)) {
							if (isCommentAppend) { // 追加 而不是 替换
								String mCommentAppendStr = CommentName_old + "_" + mComment;
								mCommentAppendStr = mCommentAppendStr.replace("__", "_").trim();
								if (mCommentAppendStr.startsWith("_")) {
									mCommentAppendStr = mCommentAppendStr.substring(1, mCommentAppendStr.length());
								}
								id3v2Tag.setComment(mCommentAppendStr);
							} else {
								id3v2Tag.setComment(mComment);
							}

							mCommentExist = true;
						}

						mp3file.setId3v2Tag(id3v2Tag);

						fixed_name = titleName_old;

						if (titleName_old == null || "".equals(titleName_old.trim())) {

							fixed_name = mp3Name_nopoint.replace("_", "");
							fixed_name = clearNumber(fixed_name).trim();
							if ("".equals(fixed_name)) {
								fixed_name = "unknow";
							}

						}

						String titleName_new = id3v2Tag.getTitle();
						String ArtistName_new = id3v2Tag.getArtist();
						String CommentName_new = id3v2Tag.getComment();

						fixed_name = fixed_name.replace(" ", "_");
						fixed_name = fixed_name.replace("《", "_");
						fixed_name = fixed_name.replace("》", "_");
						fixed_name = fixed_name.replace("（", "_");
						fixed_name = fixed_name.replace("）", "_");
						fixed_name = fixed_name.replace("(", "_");
						fixed_name = fixed_name.replace(")", "_");
						fixed_name = fixed_name.replace("___", "_");
						fixed_name = fixed_name.replace("__", "_");
						fixed_name = fixed_name.replace("__", "_");
						fixed_name = fixed_name + "_" + getTimeStamp();
						fixed_name = fixed_name.replace("__", "_");

						File mp3File_DirTarget_File = new File(allMp3FileList.get(i).getParentFile().getAbsolutePath()
								+ File.separator + TargetDirName);

						if (!mp3File_DirTarget_File.exists()) {
							mp3File_DirTarget_File.mkdirs();
						}
						mp3file.save(allMp3FileList.get(i).getParentFile().getAbsolutePath() + File.separator
								+ TargetDirName + File.separator + fixed_name + ".mp3");

						StringBuilder SBLog = new StringBuilder();

						SBLog.append(" 当前生成文件[" + (i + 1) + "]: " + fixed_name + ".mp3 ");

//							  d3v1_title = id3v1Tag.getTitle();
//							  d3v1_artist = id3v1Tag.getArtist();
//							  d3v1_comment = id3v1Tag.getComment();

						if (mTitleExist) {
							SBLog.append(" Title[" + titleName_old + "] -> Title[" + titleName_new
									+ "]  v2_titleName_old=" + titleName_old + "  v2_ArtistName_old=" + ArtistName_old
									+ "  v2_CommentName_old=" + CommentName_old + "  v1_titleName_old=" + d3v1_title
									+ "  v1_ArtistName_old=" + d3v1_artist + "  v1_CommentName_old=" + d3v1_comment);
						}

						if (mArtistExist) {
							SBLog.append(" Artist[" + ArtistName_old + "] -> Artist[" + ArtistName_new
									+ "]  v2_titleName_old=" + titleName_old + "  v2_ArtistName_old=" + ArtistName_old
									+ "  v2_CommentName_old=" + CommentName_old + "  v1_titleName_old=" + d3v1_title
									+ "  v1_ArtistName_old=" + d3v1_artist + "  v1_CommentName_old=" + d3v1_comment);
						}

						if (mCommentExist) {
							SBLog.append(" Comment[" + CommentName_old + "] -> Comment[" + CommentName_new
									+ "]  v2_titleName_old=" + titleName_old + "  v2_ArtistName_old=" + ArtistName_old
									+ "  v2_CommentName_old=" + CommentName_old + "  v1_titleName_old=" + d3v1_title
									+ "  v1_ArtistName_old=" + d3v1_artist + "  v1_CommentName_old=" + d3v1_comment);
						}

						SBLog.append("  文件原名称[" + mp3Name_nopoint + "]");
						System.out.println(SBLog.toString());

					} else {

						unTagedMp3FileList.add(allMp3FileList.get(i));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					System.out.println("创建 Mp3File 文件失败!!! 失败文件" + (i + 1) + fixed_name + ".mp3 ");
				}

			}

			//
			if (unTagedMp3FileList.size() > 0) {
				System.out
						.println("═════════════════════════ " + "失败!!! 遗憾存在 MP3转换失败文件 " + "═════════════════════════ ");
				for (int j = 0; j < unTagedMp3FileList.size(); j++) {
					System.out.println(
							"没有D3V2-Tag    失败MP3文件[" + (j + 1) + "] = " + unTagedMp3FileList.get(j).getAbsolutePath());
				}

			} else {
				System.out.println(
						"═════════════════════════ " + "恭喜!!! 当前没有 不存在D3V2-Tag的文件 " + "═════════════════════════ ");

			}
			System.out.println("RuleIndex 33 修改 MP3文件属性的操作 执行完成!");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

	}

	class MakeJpg2PDF_Rule_32 extends Basic_Rule {

		MakeJpg2PDF_Rule_32() {
			super("#", 32, 4);

		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_32    // 把当前的 jpg 和 png 文件转为一个 PDF文件  (不操作 孙文件 孙文件夹 )  \n" + Cur_Bat_Name
					+ "  #_32   ### 把当前的 jpg 和 png 文件转为一个 PDF文件  \n"

			;
		}

		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			ArrayList<File> pictureFileList = new ArrayList<File>();
			int picture_index = 1;
			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = curFile.getName().toLowerCase();
				if (currentFileName.endsWith(".jpg") || currentFileName.endsWith(".png")) {
					pictureFileList.add(curFile);
					System.out.println("picture_index[" + picture_index + "] = " + curFile.getAbsolutePath());
					picture_index++;
				}

			}

			if (pictureFileList.size() > 0) {
				SortFileWithName(pictureFileList);
				Document document = new Document();
				String result_pdf = curDirPath + File.separator + "Z_PDF_Merge" + pictureFileList.size() + "_"
						+ getTimeStamp() + ".pdf";

				FileOutputStream os;
				try {
					os = new FileOutputStream(new File(result_pdf));
					try {
						PdfWriter.getInstance(document, os);
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				document.open();
				for (int i = 0; i < pictureFileList.size(); i++) {
					File imageFile = pictureFileList.get(i);
					String imageFilename = imageFile.getName();
					createPicInPDF(document, imageFile.getAbsolutePath(), i);
					System.out.println("jpg[" + i + "] all[" + pictureFileList.size() + "] Name[" + imageFilename + "] "
							+ "图片转为 PDF完成");
				}

				document.close();
				System.out.println("═════════" + pictureFileList.size() + "张图片转为 PDF完成" + "═════════");

			} else {
				System.out.println("当前目录 没有 JPG PNG 文件 请检查! " + curDirPath);
			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void createPicInPDF(Document document, String picturePath, int index) {
			try {
				com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(picturePath);
				float image_width = image.getWidth();
				float image_hight = image.getHeight();

				float imageHeight = image.getScaledHeight();
				float imageWidth = image.getScaledWidth();
				// 设置页面宽高与图片一致
				com.itextpdf.text.Rectangle rectangle = new com.itextpdf.text.Rectangle(imageWidth, imageHeight);
				document.setMargins(0, 0, 0, 0);
				document.setPageSize(rectangle);

				// 图片居中
				image.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);

//					float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
//					float documentHeight = documentWidth / 580 * 320;// 重新设置宽高
//					float documentWidth = document.getPageSize().getWidth();
//					float documentHeight = document.getPageSize().getHeight();
				System.out.println(
						"════════════════════ " + index + " (" + picturePath + ")" + " begin ════════════════════ ");
//					System.out.println("index="+index+"  documentWidth = "+documentWidth+"  documentHeight="+documentHeight +" image_width="+image_width+"  image_hight="+image_hight);
				System.out.println(
						"index=" + index + "   " + " image_width=" + image_width + "  image_hight=" + image_hight);

				image.scaleAbsolute(image_width, image_hight);

				// 根据域的大小缩放图片
//				    image.scaleToFit(documentWidth,documentHeight);
				// 添加图片
//				    image.setAbsolutePosition(documentWidth,documentHeight);
//					image.setAbsolutePosition(documentWidth, documentHeight);
//					image.scaleAbsolute(documentWidth, documentHeight);// 重新设置宽高
//					document.setMargins(0, 0, 0, 0);
				System.out.println("document.topMargin()=" + document.topMargin() + "  document.bottomMargin()"
						+ document.bottomMargin() + "  document.leftMargin()" + document.leftMargin()
						+ "  document.rightMargin()" + document.rightMargin());
				document.newPage();
				document.add(image);
			} catch (Exception ex) {
			}
		}

	}

	class MakeFile_E5_Rule_31 extends Basic_Rule {
		ArrayList<String> fliterTypeList;
		int fileCount; // 创建文件的个数

		// jpg_wordcolor_255_255_255;
		int jpg_R; // 如果是jpg的话 那么 文笔的 颜色
		int jpg_G;
		int jpg_B;

		// jpg_background_255_255_255;
		int jpg_Back_R; // 如果是jpg的话 那么 背景的 颜色
		int jpg_Back_G;
		int jpg_Back_B;

		// jpg_frontsize_500; 默认500
		int jpg_front_size; // 字体的大小 默认 500

		// jpg_wxh_255_500
		int jpg_width; // 生成 照片的宽度
		int jpg_hight; // 生成 照片的高度

		// jpg_shownumber_true;
		boolean isShowNumber; // 是否显示 字母

		MakeFile_E5_Rule_31() {
			super("#", 31, 4);

			fliterTypeList = new ArrayList<String>();
			jpg_R = 255;
			jpg_G = 0;
			jpg_B = 0;

			jpg_Back_R = 255;
			jpg_Back_G = 255;
			jpg_Back_B = 255;

			jpg_front_size = 500;
			jpg_width = 1200;
			jpg_hight = 1200;

			isShowNumber = false;

		}

		@Override
		boolean allowEmptyDirFileList() {
			return true;
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name
					+ "  #_31  100 .jpg     ## 创建100个.jpg文件 白底 默认宽高1200x1200 默认文字颜色 红色 默认背景颜色白色  默认字体大小500  默认不打印数值 \n"
					+ Cur_Bat_Name
					+ "  #_31  100 .png     ## 创建100个.png文件 默认宽高1200x1200 默认文字颜色 红色 默认背景颜色白色  默认字体大小500  默认不打印数值 \n"
					+ Cur_Bat_Name
					+ "  #_31  100 .png .jpg    ## 创建100个.png文件 和 100个.png文件  默认宽高1200x1200 默认文字颜色 红色 默认背景颜色白色  默认字体大小500  默认不打印数值 \n"
					+ Cur_Bat_Name + "  #_31  100 .pptx     ## 创建100个.pptx文件 \n" + Cur_Bat_Name
					+ "  #_31   .pptx     ## 默认创建1个.pptx文件 \n" + Cur_Bat_Name
					+ "  #_31  100 .jpg  jpg_shownumber_true     ## 创建100个白底红字的 jpg图片 默认图片大小1200x1200 \n" + Cur_Bat_Name
					+ "  #_31   .【任意类型】    ## 默认创建1个.【任意类型】格式的文件 \n" + Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_255_255_255 jpg_wordcolor_0_0_0 jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片  白底黑字\n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_0 jpg_wordcolor_255_255_255 jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片  白字黑底\n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 黑底红字  \n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_255_0_0 jpg_wordcolor_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 红底黑字  \n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_255 jpg_wordcolor_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 蓝底黑字  \n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_255 jpg_wordcolor_255_255_255  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 蓝底白字  \n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_255 jpg_wordcolor_255_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 蓝底红字  \n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_255 jpg_wordcolor_255_255_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 蓝底黄字  \n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_255_255_0 jpg_wordcolor_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 黄底黑字  \n"
					+ Cur_Bat_Name
					+ " #_31  100 .jpg jpg_shownumber_true jpg_background_0_255_0 jpg_wordcolor_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 绿底黑字  \n"

			;
		}

		void showParams() {
			System.out.println("═════════════ " + "showParams Rule31--MakeFile" + " ═════════════ ");
			System.out.println("isShowNumber = " + isShowNumber);
			System.out.println("jpg_width = " + jpg_width);
			System.out.println("jpg_hight = " + jpg_hight);
			System.out.println("jpg_front_size = " + jpg_front_size);
			System.out.println("jpg_R = " + jpg_R);
			System.out.println("jpg_G = " + jpg_G);
			System.out.println("jpg_B = " + jpg_B);
			System.out.println("RGB=(" + jpg_R + "," + jpg_G + "," + jpg_B + ")");
			System.out.println("jpg_Back_R = " + jpg_Back_R);
			System.out.println("jpg_Back_G = " + jpg_Back_G);
			System.out.println("jpg_Back_B = " + jpg_Back_B);
			System.out.println("Back_RGB=(" + jpg_Back_R + "," + jpg_Back_G + "," + jpg_Back_B + ")");

		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String param = inputParamList.get(i);
				if (isNumeric(param)) {
					fileCount = Integer.parseInt(param);
				}

				if (param.startsWith(".")) {
					fliterTypeList.add(param);
				}

				// // jpg_frontsize_500;
				if (param.startsWith("jpg_frontsize_500")) {
					String fixedStr = param.replace("jpg_frontsize_", "");
					if (isNumeric(fixedStr)) {
						jpg_front_size = Integer.parseInt(fixedStr);
					}
				}

				// jpg_shownumber_true;
				if (param.startsWith("jpg_shownumber_")) {
					String fixedStr = param.replace("jpg_shownumber_", "").toLowerCase().trim();
					if ("true".endsWith(fixedStr) || "false".equals(fixedStr)) {
						isShowNumber = Boolean.parseBoolean(fixedStr);
					}
				}

				// // jpg_wxh_255_500
				if (param.startsWith("jpg_wxh_")) {
					String fixedStr = param.replace("jpg_wxh_", "");
					if (fixedStr.contains("_")) {
						String[] rectArr = fixedStr.split("_");
						if (rectArr != null && rectArr.length == 2) {

							if (isNumeric(rectArr[0])) {
								jpg_width = Integer.parseInt(rectArr[0]);
							}

							if (isNumeric(rectArr[1])) {
								jpg_hight = Integer.parseInt(rectArr[1]);
							}

						}
					}
				}

				if (param.startsWith("jpg_wordcolor_")) {
					String fixedStr = param.replace("jpg_wordcolor_", "");
					if (fixedStr.contains("_")) {
						String[] colorArr = fixedStr.split("_");
						if (colorArr != null && colorArr.length == 3) {

							if (isNumeric(colorArr[0])) {
								jpg_R = Integer.parseInt(colorArr[0]);
							}

							if (isNumeric(colorArr[1])) {
								jpg_G = Integer.parseInt(colorArr[1]);
							}

							if (isNumeric(colorArr[2])) {
								jpg_B = Integer.parseInt(colorArr[2]);
							}
						}
					}
				}

				// jpg_background_255_255_255
				if (param.startsWith("jpg_background_")) {
					String fixedStr = param.replace("jpg_background_", "");
					if (fixedStr.contains("_")) {
						String[] backcolorArr = fixedStr.split("_");
						if (backcolorArr != null && backcolorArr.length == 3) {

							if (isNumeric(backcolorArr[0])) {
								jpg_Back_R = Integer.parseInt(backcolorArr[0]);
							}

							if (isNumeric(backcolorArr[1])) {
								jpg_Back_G = Integer.parseInt(backcolorArr[1]);
							}

							if (isNumeric(backcolorArr[2])) {
								jpg_Back_B = Integer.parseInt(backcolorArr[2]);
							}
						}
					}
				}

			}
			if (fileCount == 0) {
				System.out.println("当前没有创建文件的个数  默认设置为 1 ");
				fileCount = 1;
			}

			if (fliterTypeList.size() == 0) {
				System.out.println("当前没有需要创建的文件类型 .xx   请检查输入参数!");
				return false;
			}
			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub

			for (int i = 0; i < fliterTypeList.size(); i++) {
				String type = fliterTypeList.get(i);
				ArrayList<File> mTypeFileList = getTypeFileListRule31(curDirFile.getAbsolutePath(), type, fileCount);
				tryDoFillFile(mTypeFileList, type);

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void tryDoFillFile(ArrayList<File> curFile, String pointtype) {
			File curTempFile = null;
			String type = pointtype.replace(".", "");

//	    	        Readers: [JPG, jpg, tiff, pcx, PCX, bmp, BMP, gif, GIF, WBMP, png, PNG, raw, RAW, JPEG, pnm, PNM, tif, TIF, TIFF, wbmp, jpeg]
//	    	        Writers: [JPG, jpg, tiff, bmp, BMP, pcx, PCX, gif, GIF, WBMP, png, PNG, raw, RAW, JPEG, pnm, PNM, tif, TIF, TIFF, wbmp, jpeg]
			if ("jpg".equals(type) || "png".equals(type) || "jpeg".equals(type) || "bmp".equals(type)
					|| "gif".equals(type)) { // 动态创建文件 文件的内容是数值
				// 不支持的格式 || "wbmp".equals(type) || "raw".equals(type)
				generalPicture(curFile, type);

			} else if ("wbmp".equals(type) || "raw".equals(type)) { // 不能通过 ImageIO 来创建的图片格式 wbmp raw

				// 待定
			} else if (isSupportType(type)) { // 从 G2 目录读取模板 然后输入输出 完成文件的创建
				File typeFile = getCurrentTemplateTypeFile(type);
				generalTemplateFile(curFile, type, typeFile);
			} else { // 其他格式 创建空的 后缀文件
				generalTemplateEmptyFile(curFile, type);
			}

		}

		void generalTemplateEmptyFile(ArrayList<File> picFileList, String pointtype) {
			String type = pointtype.replace(".", "");
			for (int i = 0; i < picFileList.size(); i++) {
				File curFile = picFileList.get(i);
				try {
					curFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		void generalTemplateFile(ArrayList<File> picFileList, String type, File templateFile) {
			if (templateFile == null) {
				return;
			}
			for (int i = 0; i < picFileList.size(); i++) {
				int mCurIndex = i;
				File mCurFile = picFileList.get(i);
				fileCopy(templateFile, mCurFile);
			}

		}

		File getCurrentTemplateTypeFile(String pointtype) {
			String type = pointtype.replace(".", "");
			String curType = type.toLowerCase(); // zbin/E5/txt/txt.txt
			File typeFile = new File(
					getG2ZbinDirPath() + File.separator + curType + File.separator + curType + "." + curType);
			if (!typeFile.exists()) {
				System.out.println("当前类型文件:  type:" + type + " 模板文件缺失无法批量创建文件 请创建模板！ 执行失败！");
				return null;
			}
			return typeFile;
		}

		String getG2ZbinDirPath() {
			String path = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator
					+ "zbin" + File.separator + "G2";
			return path;
		}

		void generalPicture(ArrayList<File> picFileList, String pointtype) {

			showParams();
			String type = pointtype.replace(".", "");
			for (int i = 0; i < picFileList.size(); i++) {
				int mCurIndex = i;
				File mCurFile = picFileList.get(i);
				int width = jpg_width;
				int heigh = jpg_width;
				BufferedImage bi = new BufferedImage(width, heigh, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150
				// 得到它的绘制环境(这张图片的笔)
				Graphics2D g2 = (Graphics2D) bi.getGraphics();
				g2.fillRect(0, 0, jpg_width, jpg_width);// 填充一个矩形 左上角坐标(0,0),宽500,高500;填充整张图片
				g2.setColor(new Color(jpg_Back_R, jpg_Back_G, jpg_Back_B));// 设置颜色
				g2.fillRect(0, 0, width, heigh);// 填充整张图片(其实就是设置背景颜色)
				int frontSize = jpg_front_size;
				int centerx = jpg_width / 2;
				int centery = jpg_hight / 2;
				int showIndex = i + 1;
				g2.setColor(new Color(jpg_R, jpg_G, jpg_B));
				Font f = new Font("宋体", Font.BOLD, frontSize);
				g2.setFont(f); // 设置字体:字体、字号、大小
				FontRenderContext context = g2.getFontRenderContext();
				Rectangle2D bounds = f.getStringBounds(showIndex + "", context);
				if (isShowNumber) {
					g2.drawString(showIndex + "", (float) (centerx - bounds.getCenterX()),
							(float) (centery - bounds.getCenterY())); // 向图片上写字符串
				}
				try {
					mCurFile.createNewFile();
					ImageIO.write(bi, "jpg", new FileOutputStream(mCurFile));// 保存图片 JPEG表示保存格式
					System.out.println("创建文件[" + i + "]  = " + mCurFile.getAbsolutePath() + "成功");
				} catch (Exception e) {
					System.out.println("复制图片格式出现异常！");
				}

			}
			showParams();

		}

		public ArrayList<File> getTypeFileListRule31(String dirAbsPath, String typeName, int fileCount) {
			ArrayList<File> curFileList = new ArrayList<File>();
			String typeNameNoPoint = typeName.replace(".", "");

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			String typeDir = dirAbsPath + File.separator + typeNameNoPoint + "_" + date;
			File typeDirFile = new File(typeDir);
			typeDirFile.mkdirs();
			for (int i = 1; i < fileCount + 1; i++) {
				String itemFileStr = typeDirFile.getAbsolutePath() + File.separator + i + "." + typeNameNoPoint;
				File itemFile = new File(itemFileStr);
				curFileList.add(itemFile);
			}
			return curFileList;

		}

		boolean isSupportType(String type) {
			boolean flag = false;
			if ("jpg".equals(type) || "jpeg".equals(type) || "bmp".equals(type) || "png".equals(type)
					|| "wbmp".equals(type) || "gif".equals(type) || ("txt".equals(type)) || ("md".equals(type))
					|| ("java".equals(type)) || ("cpp".equals(type)) || ("c".equals(type)) || ("xml".equals(type))
					|| ("json".equals(type)) || ("bat".equals(type)) || ("sh".equals(type)) || ("zip".equals(type))
					|| ("rar".equals(type)) || ("xlsx".equals(type)) || ("xls".equals(type)) || ("html".equals(type))
					|| ("db".equals(type)) || ("js".equals(type)) || ("dll".equals(type)) || ("exe".equals(type))
					|| ("gitignore".equals(type)) || ("h".equals(type)) || ("jar".equals(type)) || ("py".equals(type))
					|| ("pkt".equals(type)) || ("7z".equals(type)) || ("pdf".equals(type)) || ("doc".equals(type))
					|| ("apk".equals(type)) || ("pcapng".equals(type)) || ("conf".equals(type))
					|| ("properties".equals(type)) || ("prop".equals(type)) || ("css".equals(type))
					|| ("so".equals(type)) || ("bin".equals(type)) || ("raw".equals(type)) || ("ppt".equals(type))
					|| ("pptx".equals(type)) || ("docx".equals(type))

			) {
				flag = true;
			}

			return flag;

		}

	}

	// 对文件的名称进行修改
	class FileRenameOperation_Rule_30 extends Basic_Rule {

		String firstParamStr; // 第一个参数

		int DIR_OPERA_TYPE_APPEND = 1; // 后缀增加
		String appendStr_1;
		int DIR_OPERA_TYPE_PREFIX = 2; // 前缀增加
		String prefixStr_2;

		int DIR_OPERA_TYPE_CREATE = 3; // 创建文件
		int beginIndex_3;
		int endIndex_3;
		String prefixStr_3;
		String appendStr_3;

		int DIR_OPERA_TYPE_REPLACE = 4; // 替换文件夹名称
		String replacedStr_4;
		String newNameStr_4;

		// 识别当前用户 指定的操作类型 1后缀增加 2前缀增加 3创建文件 4替换文件夹名称
		int currentOperaType = 0;

		ArrayList<String> fliterTypeList;

		FileRenameOperation_Rule_30() {
			super("#", 30, 4);
			prefixStr_3 = "";
			appendStr_3 = "";
			fliterTypeList = new ArrayList<String>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean falg = true;

			for (int i = 0; i < inputParamList.size(); i++) {
				String inputTypeItem = inputParamList.get(i).trim();
				if (inputTypeItem.startsWith(".")) {
					fliterTypeList.add(inputTypeItem);

				}
			}
			if (currentOperaType == 1) {
				appendStr_1 = inputParamList.get(inputParamList.size() - 1);
			} else if (currentOperaType == 2) {
				prefixStr_2 = inputParamList.get(inputParamList.size() - 1);
			} else if (currentOperaType == 4) {
				String inputStr = inputParamList.get(inputParamList.size() - 1);
				if (!inputStr.contains("_")) {
					falg = false;
				}

				if (inputStr.endsWith("_")) {

					replacedStr_4 = inputStr.substring(0, inputStr.length() - 1);
					newNameStr_4 = "";

				} else {
					String[] inputArr = inputStr.split("_");
					// item__

					if (inputArr.length >= 2) {

						replacedStr_4 = inputArr[0];
						newNameStr_4 = inputArr[inputArr.length - 1];
					} else {
						falg = false;
					}

				}

			} else if (currentOperaType == 3) {

				for (int i = 0; i < inputParamList.size(); i++) {

					String paramItem = inputParamList.get(i);
					if (paramItem != null && paramItem.equals(firstParamStr)) {
						continue; // 第一个参数不操作
					}

					if (!paramItem.contains("_")) {
						falg = false;
						continue;
					}
					String fixedParam = paramItem.replace("_", "");

					if (isNumeric(fixedParam)) { // 如果是 字母 说明是起始的那个参数
						String[] IndexArr = paramItem.split("_");

						if (IndexArr.length >= 2) {

							String beginIndex_3_Str = IndexArr[0];
							String endIndex_3_Str = IndexArr[IndexArr.length - 1];
							if (isNumeric(beginIndex_3_Str)) {
								beginIndex_3 = Integer.parseInt(beginIndex_3_Str);

							} else {
								falg = false;
							}

							if (isNumeric(endIndex_3_Str)) {
								endIndex_3 = Integer.parseInt(endIndex_3_Str);
							} else {
								falg = false;
							}

						} else {
							falg = false;
						}
					} else { // 名称的参数
						if (paramItem.endsWith("_")) {
							appendStr_3 = "";
							String[] NamePreArr = paramItem.split("_");
							prefixStr_3 = NamePreArr[0];
							System.out.println("appendStr_3=" + appendStr_3 + "   prefixStr_3=" + prefixStr_3);

						} else {
							String[] NamePreArr = paramItem.split("_");
							if (NamePreArr.length >= 2) {
								prefixStr_3 = NamePreArr[0];
								appendStr_3 = NamePreArr[1];
								System.out.println("appendStr_3=" + appendStr_3 + "   prefixStr_3=" + prefixStr_3);

							}

						}

					}

				}

			}

			return super.initParamsWithInputList(inputParamList) || falg;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
// 	// 识别当前用户 指定的操作类型 1后缀增加 2前缀增加 3创建文件 4替换文件夹名称

			ArrayList<File> slectedFileList = getRealFileWithDirAndPointType(curDirFile, fliterTypeList);

			System.out.println("Rule30 修改文件  currentOperaType = " + currentOperaType + "   ");

			switch (currentOperaType) {

			case 1:
				for (int i = 0; i < slectedFileList.size(); i++) {
					File selectFile = slectedFileList.get(i);
					String selectFileName = selectFile.getName();
					String pointType = getFileTypeWithPoint(selectFileName);
					String FileNameWithNoLower = getFileNameNoPoint(selectFileName);
					String newselectFileName = FileNameWithNoLower + appendStr_1 + pointType;
					tryReName(selectFile, newselectFileName);
				}
				break;

			case 2:
				for (int i = 0; i < slectedFileList.size(); i++) {
					File selectFile = slectedFileList.get(i);
					String selectFileName = selectFile.getName();
					String newselectFileName = prefixStr_2 + selectFileName;
					tryReName(selectFile, newselectFileName);
				}
				break;

			case 3:
				System.out.println("beginIndex_3 = " + beginIndex_3 + "   endIndex_3=" + endIndex_3);
				for (int j = 0; j < fliterTypeList.size(); j++) {
					String typeStr = fliterTypeList.get(j);
					for (int i = beginIndex_3; i < endIndex_3 + 1; i++) {
						String absDirPath = curDirFile.getAbsolutePath();

						String selectFilePath = absDirPath + File.separator + prefixStr_3 + i + appendStr_3 + typeStr;
						File curFileItem = new File(selectFilePath);
						System.out.println("创建空 " + typeStr + " 文件 [" + i + "] = " + curFileItem.getName());
						try {
							curFileItem.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

				break;

			// 4替换文件夹名称
			case 4:
				System.out.println("replacedStr_4 = " + replacedStr_4 + "   newNameStr_4=" + newNameStr_4);

				for (int i = 0; i < slectedFileList.size(); i++) {
					File realFile = slectedFileList.get(i);
					String realFileName = realFile.getName();

					String newRealName = realFileName.replace(replacedStr_4, newNameStr_4 == null ? "" : newNameStr_4);
					tryReName(realFile, newRealName);
				}

				break;

			default:
				System.out.println("当前 currentOperaType = " + currentOperaType + "  没有找到合适的操作类型去处理 Rule30 ");
			}

			return curDirList;
		}

		@Override
		boolean initParams4InputParam(String inputParam) {

			firstParamStr = inputParam;
			if (inputParam.contains("append")) {
				currentOperaType = 1;
			} else if (inputParam.contains("prefix")) {
				currentOperaType = 2;

			} else if (inputParam.contains("replace")) {
				currentOperaType = 4;

			} else if (inputParam.contains("create")) {
				currentOperaType = 3;

			}

			return super.initParams4InputParam(inputParam);
		}

		@Override
		String simpleDesc() {
			return "只对文件的文件名进行操作" + "\n" + Cur_Bat_Name + "  #_30_append  _over   ###往当前文件夹后缀增加 _over \n" + Cur_Bat_Name
					+ "  #_30_append .jpg  _over   ###往当前所有文件前缀增加 xx_over.png \n" + Cur_Bat_Name
					+ "  #_30_prefix  temp   ###往当前所有文件前缀增加 temp \n" + Cur_Bat_Name
					+ "  #_30_prefix .jpg temp   ###往当前所有png文件前缀增加 temp tempxxx.png \n" + Cur_Bat_Name
					+ " #_30_create .jpg 1_100   ###创建一个序列号从1.jpg到100.jpg的100个文件   \n" + Cur_Bat_Name
					+ " #_30_create  .pptx   temp_  1_100   ###创建一个序列号从temp1.pptx到temp100.pptx的100个文件 \n" + Cur_Bat_Name
					+ " #_30_create .doc .jpg  _temp  1_100   ###创建一个序列号从1temp.doc到100temp.doc 1temp.jpg到100temp.jpg 的100个文件 \n"
					+ Cur_Bat_Name
					+ " #_30_create .jpg  j_temp  1_100   ###创建一个序列号从 j_1_temp.jpg 到100temp的 j_100_temp.jpg 的文件 \n"
					+ Cur_Bat_Name + " #_30_create .jpg   7000_7100  ###创建一个序列号从7000.png开始的到7100.png结束的文件  \n"
					+ Cur_Bat_Name + " #_30_replace  abc_DEF  ###把当前文件名称中的  abc 转为 DEF \n" + Cur_Bat_Name
					+ " #_30_replace .jpg .png abc_DEF  ###把当前文件夹下的.jpg .png 名称中的  abc 转为 DEF \n";
		}

	}

	class mergeSomePPTXAsOne_Rule_29 extends Basic_Rule { // 把 当前 目录的 多个 pptx 合并成 一个 pptx 文件
		boolean isDeteleOrigin = false; // 是否把原pptx文件删除

		mergeSomePPTXAsOne_Rule_29() {
			super("#", 29, 4);
			isDeteleOrigin = false;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

//			param0[#_28]
//			param1[name_90]

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramStr = inputParamList.get(i);
				String paramStr_lower_trim = paramStr.toLowerCase().trim();
				System.out.println("param" + i + "[" + paramStr + "] ");

				if (paramStr_lower_trim.startsWith("delete")) {
					isDeteleOrigin = true;

				}
			}

			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			ArrayList<File> pptxFileList = new ArrayList<File>();
			int pptx_index = 1;
			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = curFile.getName().toLowerCase();
				if (currentFileName.endsWith(".pptx") || currentFileName.endsWith(".ppt")) {
					pptxFileList.add(curFile);
					System.out.println("pptx_index[" + pptx_index + "] = " + curFile.getAbsolutePath());
					pptx_index++;
				}

			}

			if (pptxFileList.size() == 0) {
				System.out.println("当前目录就没有  pptx文件 请检查! PATH = " + curDirPath);
				return null;
			}

			if (pptxFileList.size() == 1) {
				System.out.println("当前目录只有1个  pptx文件  无需合并pptx操作! PATH = " + curDirPath);
				return null;
			}

			if (pptxFileList.size() > 1) {
				try {
					XMLSlideShow ppt = new XMLSlideShow();
					for (int i = 0; i < pptxFileList.size(); i++) {
						File pptxFileItem = pptxFileList.get(i);
						FileInputStream inputstream;

						inputstream = new FileInputStream(pptxFileItem);

						XMLSlideShow src;

						src = new XMLSlideShow(inputstream);
						int currentWidth = src.getPageSize().width;
						int currentHeight = src.getPageSize().height;
						ppt.setPageSize(new Dimension(currentWidth, currentHeight));
//				        System.out.println("currentWidth = "+currentWidth+"    currentHeight="+currentHeight);

						for (XSLFSlide srcSlide : src.getSlides()) {
							ppt.createSlide().importContent(srcSlide);

						}
					}

					String result_pptx = curDirPath + File.separator + "Z_PPTX_Merge" + pptxFileList.size() + "_"
							+ getTimeStamp() + ".pptx";
					File resultPPTX_File = new File(result_pptx);
					// creating the file object
					FileOutputStream out = new FileOutputStream(resultPPTX_File);

					// saving the changes to a file
					ppt.write(out);
					System.out.println("Merging done successfully");
					out.close();

					if (resultPPTX_File.exists() && resultPPTX_File.length() > 100 && isDeteleOrigin) {
						for (int i = 0; i < pptxFileList.size(); i++) {
							File pptxFileItem = pptxFileList.get(i);
							System.out.println("删除原始 pptx_" + i + "=[" + pptxFileItem.getAbsolutePath() + "]");

							pptxFileItem.delete();
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("当前程序出现异常！！ Rule29 合并多 pdf操作 ");
				}

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_29     ## 把当前目录下的 pptx文件合并为一个 pptx文件  【保留原有】的pptx文件 \n" + Cur_Bat_Name
					+ " #_29  delete  ##把当前目录下的 pptx文件合并为一个 pptx文件  【删除原有】的pptx文件 \n"

			;
		}

	}

	// 把当前的 图片 文件 jpg png 等 转为 一个 PPTX 文件 方便 分享 查看
	class makeJpg2PPTX_Rule_28 extends Basic_Rule {
		// 把文件后缀中的中文给去除掉 不包含文件夹 不包含孙文件

		File TemplatePPTX_File;
		int begin_index; // 由于 免费版本的 Presentation 最大创建 pptx页面为 10页 所以 才出现这些属性 尼玛
		int end_index; // 由于 免费版本的 Presentation 最大创建 pptx页面为 10页 所以 才出现这些属性 尼玛

		boolean isShowName;
		int rotate_value; // 旋转的角度
		boolean bigkeep; // 那些 与 电脑尺寸相同的 照片 保持正向的 比例

		ArrayList<String> mInputParamList; // 记录当前的所有输入的参数

		makeJpg2PPTX_Rule_28() {
			super("#", 28, 4);
			TemplatePPTX_File = new File(zbinPath + File.separator + "G2_EmptyPPTX_Rule28.pptx");
			rotate_value = 0;
			isShowName = false;
			bigkeep = false;
			begin_index = 0;
			end_index = 0;
			mInputParamList = new ArrayList<String>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

//				param0[#_28]
//				param1[name_90]
			mInputParamList.addAll(inputParamList);

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramStr = inputParamList.get(i);
				System.out.println("param" + i + "[" + paramStr + "] ");
				if (paramStr.toLowerCase().contains("name")) {
					isShowName = true;
				}
				if (paramStr.toLowerCase().contains("keepbig")) {
					bigkeep = true;
				}

				if (paramStr.contains("_") && isNumeric(paramStr.replace("_", "").trim())) {
					String[] indesArr = paramStr.split("_");
					if (indesArr != null && indesArr.length == 2) {
						if (isNumeric(indesArr[0])) {
							begin_index = Integer.parseInt(indesArr[0]);
						}

						if (isNumeric(indesArr[1])) {
							end_index = Integer.parseInt(indesArr[1]);
						}

					}

				}

				if (paramStr != null && isNumeric(paramStr.trim())) {

					rotate_value = Integer.parseInt(paramStr.trim());
				}
			}
			/*
			 * String lastParams = inputParamList.get(inputParamList.size()-1);
			 * if(lastParams != null && !lastParams.startsWith("#") &&
			 * lastParams.contains("_")) { String[] arrStr = lastParams.split("_");
			 * if(arrStr != null && arrStr.length > 0) { String lastNumStr =
			 * arrStr[arrStr.length-1]; if(isNumeric(lastNumStr)) { rotate_value =
			 * Integer.parseInt(lastNumStr); } }
			 *
			 * }else
			 */

			System.out.println("isShowName=" + isShowName + "   rotate_value=" + rotate_value);
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			System.out.println("makeJpg2PPTX_Rule_28   搜索到的实体文件个数:" + curRealFileList.size());

			ArrayList<File> pictureFileList = new ArrayList<File>();
			int picture_index = 1;
			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = curFile.getName().toLowerCase();
				if (currentFileName.endsWith(".jpg") || currentFileName.endsWith(".png")) {
					pictureFileList.add(curFile);
					System.out.println("picture_index[" + picture_index + "] = " + curFile.getAbsolutePath());
					picture_index++;
				}

			}

			if (pictureFileList.size() > 0) {
				Presentation ppt = new Presentation();
				File tempPPTXFile = null;
				try {
					tempPPTXFile = new File(TemplatePPTX_File.getAbsolutePath());
					if (!tempPPTXFile.exists()) {
						System.out.println(" 当前 PPTX的模板文件不存在 PATH = " + tempPPTXFile.getAbsolutePath());
						tempPPTXFile.createNewFile();
					}

					ppt.loadFromFile(tempPPTXFile.getAbsolutePath());

					Rectangle2D rect_fullSize = new Rectangle2D.Double(0, 0, ppt.getSlideSize().getSize().getWidth(),
							ppt.getSlideSize().getSize().getHeight());
					double PPT_Width = rect_fullSize.getWidth();
					double PPT_Height = rect_fullSize.getHeight();

					System.out.println("PPT_Width = " + PPT_Width + "   PPT_Height=" + PPT_Height);
					ISlide slide = ppt.getSlides().get(0);

					//
					if (begin_index == 0 && end_index == 0 && pictureFileList.size() > 10) {
						// 通过长度 计算 以及 输入 参数 inputParamList 动态计算 全部解析的命令
						// buildCommandWIth(pictureFileList.size()，inputParamList)
						System.out.println("当前目录图片文件大于10 个 !! 需要动态 动态创建 pptx 文件 请执行如下文件");
						buildDynamic(pictureFileList.size(), mInputParamList);

						return null;
					}
					System.out.println("begin_index = " + begin_index + "   end_index=" + end_index);

					SortFileWithName(pictureFileList);
					for (int i = 0; i < pictureFileList.size(); i++) {
						System.out.println("Format_Index[" + i + "] = " + pictureFileList.get(i).getAbsolutePath());
					}

					for (int i = 0; i < pictureFileList.size(); i++) {
						if (pictureFileList.size() > 10) {
							if (i < begin_index) {
								continue;
							} else if (i > end_index) {
								continue;
							}
						}

						System.out.println("For_Index_i=" + i);
						File imageFile = pictureFileList.get(i);
						String FileName = imageFile.getName();
						String fileNameNoPoint = getFileNameNoPoint(FileName);
						Image img = ImageIO.read(imageFile); // 构造Image对象
						int picture_width = img.getWidth(null);
						int picture_height = img.getHeight(null);
						if (picture_width >= picture_height) {
							// 横屏的情况 使用全覆盖
							System.out.println("fullSize(0,0," + PPT_Width + "," + PPT_Height + ") -> "
									+ "PictureIndex[" + (i + 1) + "]   " + " picture_width=[" + picture_width
									+ "]  picture_height=[" + picture_height + "]  " + "PPT_Width =[" + PPT_Width
									+ "]   PPT_Height=[" + PPT_Height + "]");

							BufferedImage buffImage = rotate(img, bigkeep == true ? 0 : rotate_value);

							ByteArrayOutputStream bs = new ByteArrayOutputStream();
							ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
							ImageIO.write(buffImage, "jpg", imOut);
							InputStream ImageInputStream = new ByteArrayInputStream(bs.toByteArray());

							IEmbedImage image = slide.getShapes().appendEmbedImage(ShapeType.RECTANGLE,
									ImageInputStream, rect_fullSize);
							image.getLine().setFillType(FillFormatType.PICTURE);

							// 获取第一张幻灯片，添加指定大小和位置的矩形文本框

							if (isShowName) {
								System.out.println("i-begin_index = " + (i - begin_index));
								IAutoShape shape = ppt.getSlides().get(i - begin_index).getShapes().appendShape(
										ShapeType.RECTANGLE,
										new Rectangle((int) (PPT_Width / 2 - PPT_Width / 2), 0, (int) PPT_Width, 50));

								// 设置shape样式
								shape.getFill().setFillType(FillFormatType.NONE);
								shape.getShapeStyle().getLineColor().setColor(Color.red);
//						        shape.setRotation(-45);
								shape.getLocking().setSelectionProtection(true);
								shape.getLine().setFillType(FillFormatType.NONE);

								// 添加文本到shape
								shape.getTextFrame().setText(fileNameNoPoint);
								PortionEx textRange = shape.getTextFrame().getTextRange();

								// 设置文本水印样式
								textRange.getFill().setFillType(FillFormatType.SOLID);
								textRange.getFill().getSolidColor().setColor(Color.red);
								textRange.setFontHeight(50);
								System.out.println(" fileNameNoPoint = " + fileNameNoPoint);

							}

						} else {

							BufferedImage buffImage = rotate(img, rotate_value);
							System.out.println("rotate90(0,0," + PPT_Width + "," + PPT_Height + ") -> "
									+ "PictureIndex[" + (i + 1) + "]   " + " picture_width=[" + picture_width
									+ "]  picture_height=[" + picture_height + "]  " + " rotate_width=["
									+ buffImage.getWidth() + "]  rotate_height=[" + buffImage.getHeight() + "]  "
									+ "PPT_Width =[" + PPT_Width + "]   PPT_Height=[" + PPT_Height + "]");

							ByteArrayOutputStream bs = new ByteArrayOutputStream();
							ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
							ImageIO.write(buffImage, "jpg", imOut);
							InputStream ImageInputStream = new ByteArrayInputStream(bs.toByteArray());

							// 方式1 竖屏的情况 保持比例 宽 小于 长 // 从 原有的 高 变为 最高的情况
							// 把 图片旋转 90 ° 再 全尺寸加入
							int picture_height_fixed = (int) PPT_Height;
							int picture_width_fixed = (int) (picture_width * (PPT_Height / picture_height));
							Rectangle2D selected_rect = null;
							if (rotate_value == 0 || rotate_value == 180) {
								selected_rect = new Rectangle2D.Double(PPT_Width / 2 - picture_width_fixed / 2, 0,
										picture_width_fixed, picture_height_fixed);

							} else {

								selected_rect = rect_fullSize;
							}

							System.out.println("selectedSize(" + (PPT_Width / 2 - picture_width_fixed / 2) + ",0,"
									+ picture_width_fixed + "," + picture_height_fixed + ") -> " + "PictureIndex["
									+ (i + 1) + "]   " + " picture_width=[" + picture_width + "]  picture_height=["
									+ picture_height + "]  " + "PPT_Width =[" + PPT_Width + "]   PPT_Height=["
									+ PPT_Height + "]");

							IEmbedImage image = slide.getShapes().appendEmbedImage(ShapeType.RECTANGLE,
									ImageInputStream, selected_rect);
							image.getLine().setFillType(FillFormatType.PICTURE);

							/*
							 * // 方式2 IEmbedImage image =
							 * slide.getShapes().appendEmbedImage(ShapeType.RECTANGLE,
							 * ImageInputStream,rect_fullSize);
							 * image.getLine().setFillType(FillFormatType.PICTURE);
							 */

							if (isShowName) {

								IAutoShape shape = ppt.getSlides().get(i - begin_index).getShapes().appendShape(
										ShapeType.RECTANGLE,
										new Rectangle((int) (PPT_Width / 2 - PPT_Width / 2), 0, (int) PPT_Width, 50));

								// 设置shape样式
								shape.getFill().setFillType(FillFormatType.NONE);
								shape.getShapeStyle().getLineColor().setColor(Color.red);
//						        shape.setRotation(-45);
								shape.getLocking().setSelectionProtection(true);
								shape.getLine().setFillType(FillFormatType.NONE);

								// 添加文本到shape
								shape.getTextFrame().setText(fileNameNoPoint);
								PortionEx textRange = shape.getTextFrame().getTextRange();

								// 设置文本水印样式
								textRange.getFill().setFillType(FillFormatType.SOLID);
								textRange.getFill().getSolidColor().setColor(Color.red);
								textRange.setFontHeight(50);
								System.out.println(" fileNameNoPoint = " + fileNameNoPoint);

							}

						}

						if (i == 0) {
							slide = ppt.getSlides().append();
							System.out.println("AA for 1 ppt.getSlides().size() = " + ppt.getSlides().size());
							continue;
						}
						if (i != pictureFileList.size() - 1 && i != end_index) {
							slide = ppt.getSlides().append();
							System.out.println("AA ppt.getSlides().size() = " + ppt.getSlides().size());

						}
						System.out.println("BB ppt.getSlides().size() = " + ppt.getSlides().size());

					}
					String local_pptx_path = curDirPath + File.separator + "PPTX_" + getTimeStamp() + ".pptx";
					ppt.saveToFile(local_pptx_path, FileFormat.PPTX_2013);

					System.out.println("当前目录所有图片的 PPTX 文件以及生成!  PATH = " + local_pptx_path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("当前目录下 " + curDirPath + " 不存在 jpg 和 png 照片 无法合成 pptx文件!");
			}

			return curRealFileList;
		}

		void buildDynamic(int size, ArrayList<String> params) {
			System.out.println("═══════════════════════════════════" + "动态命令" + "═══════════════════════════════════");
			int forCount = (int) (size / 10) + 1; //

			StringBuilder allCommandSB = new StringBuilder();
			for (int i = 0; i < forCount; i++) {
				ArrayList<String> commandList = new ArrayList<String>();
				StringBuilder tempSB = new StringBuilder();
				commandList.add(Cur_Bat_Name);
				commandList.addAll(params);

				int begin_index = i * 10;
				int end_index = i * 10 + 9;
				if (end_index > size) {
					end_index = size - 1;
				}

				if (begin_index > end_index) {
					continue;
				}
				String order_str = begin_index + "_" + end_index;

				commandList.add(" " + order_str + "");

				for (int j = 0; j < commandList.size(); j++) {
					String itemStr = commandList.get(j);
					tempSB.append(itemStr + "  ");
				}

				if (i != forCount - 1) {
					tempSB.append("  &&  ");
				}

				allCommandSB.append(tempSB.toString());

			}
			if (allCommandSB.toString().trim().endsWith("&&")) {
				System.out.println(allCommandSB + "   " + Cur_Bat_Name + "  #_29  delete");
			} else {
				System.out.println(allCommandSB + "  &&  " + Cur_Bat_Name + "  #_29  delete");
			}

		}

		@Override
		String simpleDesc() {
			return " // 把当前目录下文件 下的picture媒体文件 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28     [索引28]   // 把当前目录下文件 旋转0度 不显示文件名 下的picture媒体文件 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28 0     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转0度 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28 90     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转90度 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28 180     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转180度 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28 270     [索引28]   // 把当前目录下文件 下的picture媒体文件 并旋转270度 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28 name     [索引28]   // 把当前目录下文件 下的picture媒体文件 并添加文件名 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28 name 0     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转0度 并添加文件名 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28 name 90     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转90度 并添加文件名 生成 PPTX文件   \n" + Cur_Bat_Name
					+ " #_28 name 180     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转180度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name
					+ " #_28 name 270     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转270度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name
					+ " #_28 keepbig name 0     [索引28]   // 把当前目录下文件  图片比例与电脑尺寸相同(PC 宽>高)的保持正向 比例不同的(手机 宽<高) 旋转0度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name
					+ " #_28 keepbig name 90     [索引28]   // 把当前目录下文件  图片比例与电脑尺寸相同(PC 宽>高)的保持正向 比例不同的(手机 宽<高) 旋转90度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name
					+ " #_28 keepbig name 180     [索引28]   // 把当前目录下文件  图片比例与电脑尺寸相同(PC 宽>高)的保持正向 比例不同的(手机 宽<高) 旋转180度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name
					+ " #_28 keepbig name 270     [索引28]   // 把当前目录下文件  图片比例与电脑尺寸相同(PC 宽>高)的保持正向 比例不同的(手机 宽<高) 旋转270度 并添加文件名 生成 PPTX文件   \n"

			;

		}
	}

	// 在 包含 mp4 文件夹名称 把 无类型的文件 改为 mp4 , 对应 gif jpg
	class Rercovery_Type_By_DirName_Rule_27 extends Basic_Rule {
		String originType;
		String targetType;

		Rercovery_Type_By_DirName_Rule_27() {
			super("#", 27, 5);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_27  _jpg   把包含 jpg 文件夹名称中的无类型文件改为 jpg格式名称\n" + Cur_Bat_Name
					+ " #_27  _gif  把包含 gif 文件夹名称中的无类型文件改为 gif格式名称\n" + Cur_Bat_Name
					+ " #_27  _mp4  把包含 mp4 文件夹名称中的无类型文件改为 mp4格式名称\n";
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			// 获取到装换的类型
			String inputFileTypeParams = inputParamList.get(inputParamList.size() - 1);

			if (!inputFileTypeParams.contains("_")) {
				Flag = false;
				System.out.println("无法检测到当前 第27 Rule   原始类型_目标类型参数   请检查后重新执行");
			} else {

				if (inputFileTypeParams.endsWith("_")) {
					String target = "";
					String[] parmas = inputFileTypeParams.split("_");
					String origin = parmas[0];
					System.out.println("item=" + inputFileTypeParams + "   origin=" + origin + "     target=" + target);
					originType = origin;
					targetType = target;

				} else {
					String[] parmas = inputFileTypeParams.split("_");
					System.out.println(
							"item=" + inputFileTypeParams + "   origin=" + parmas[0] + "     target=" + parmas[1]);
					originType = parmas[0];
					targetType = parmas[1];
				}

				Flag = true;

			}
			curFilterFileTypeList.add(originType);

			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
				ArrayList<File> allSubRealFileList) {

			for (int i = 0; i < allSubDirFileList.size(); i++) {
				File curDirFile = allSubDirFileList.get(i);
				String curDirName = curDirFile.getName();
				String curDirAbsPath = curDirFile.getAbsolutePath();
				// 如果当前文件夹 包含 过滤类型的名称 如 mp4 gif jpg 那么才往下走
//				System.out.println("A i["+i+"] curDirName=" + curDirName );
				if (!curDirName.contains(targetType) && !curDirAbsPath.toLowerCase().contains(targetType)) {
					continue;
				}

				File[] listFile = curDirFile.listFiles();
//				System.out.println("B i["+i+"] curDirName=" + curDirName );
				if (listFile == null || listFile.length <= 0) {

					continue;
				}

				// jpg_xxx_port/AI/aaaaaaaaaaaa.jpg
				// 1.当遍历 jpg_xxx_port 时发现 里面只有文件夹
				// 2. 当遍历 AI时发现 它又不是 包含jpg 的文件夹名名称 (完全路径 需要 包含 jpg)
				for (int j = 0; j < listFile.length; j++) {
					File mRealFile = listFile[j];
					if (mRealFile.isFile() && !mRealFile.isDirectory()) {
						String realFileNmae = mRealFile.getName();
						String type = getFileTypeWithPoint(realFileNmae);
						System.out.println("j[" + j + "] realFileNmae=" + realFileNmae + "   type=" + type);
						if ("".equals(type)) {
							String newName = realFileNmae + "." + targetType;
							tryReName(mRealFile, newName);
						}

					}
				}

			}

			return super.applyDir_SubFileListRule5(allSubDirFileList, allSubRealFileList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			for (int i = 0; i < subFileList.size(); i++) {
				File curFIle = subFileList.get(i);
				String originName = curFIle.getName();
				// 执行 修改文件类型的操作

				// 1. 如果当前文件 过滤类型是 空 那么 可能就是没有任何的类型了
				// 如果当前过滤的类型是 originType 是"" 空的话 那么就会过滤出所有的文件 那么只操作 不包含.的那些文件
				if ("".equals(originType)) {
					if (originName.contains(".")) {
						continue; // 包含了 . 说明有类型 那么 不操作
					}
					String newName = originName + "." + targetType;
					tryReName(curFIle, newName);
				} else {
					// 有具体的 过滤的文件
					String oldType = "." + originType;
					String newType = "." + targetType;
					if ("".equals(targetType)) {
						newType = "";
					}

					if (originName.contains(oldType)) {
						String newName = originName.replace(oldType, newType);
						tryReName(curFIle, newName);
					}

				}

			}

			return subFileList;
		}
	}

	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
//     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

	class Rename_By_Dir_Rule_26 extends Basic_Rule {

		ArrayList<String> inputTypeList;
		// zrule_apply_G2.bat #_26 jpg 把当前所有的jpg格式文件生在该文件夹下依次重命名

		// 可能从参数输入的 单一文件
		ArrayList<File> inputParamFileList; // .mp4 .jpg 对单独类型进行重命名

		boolean isSearchAllFile2CurDirFlag = false; // 是否检索 所有类型(空输入就是检索所有类型)

		Rename_By_Dir_Rule_26() {
			super("#", 26, 5);
			inputTypeList = new ArrayList<String>();
			inputParamFileList = new ArrayList<File>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);
				if (strInput.equals(firstInputIndexStr)) {
					continue;
				}
				if (!strInput.startsWith(".")) {
					inputTypeList.add("." + strInput.trim());
				} else {
					inputTypeList.add(strInput.trim());
				}

				File tempFile = new File(curDirPath + File.separator + strInput);
				if (tempFile.exists() && !tempFile.isDirectory()) {
					inputParamFileList.add(tempFile);

				}
			}

			if (inputTypeList.size() == 0 && inputParamFileList.size() == 0) {
				isSearchAllFile2CurDirFlag = true;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
				ArrayList<File> allSubRealFileList) {
			if (isSearchAllFile2CurDirFlag) {
				// 比那里所有 类型的 文件 并 重新命名
				tryReNameByDir(allSubDirFileList);

			} else {
				tryReNameByDir_Type(allSubDirFileList, inputTypeList);
				/*
				 * for (int i = 0; i < inputTypeList.size(); i++) { String type =
				 * inputTypeList.get(i);
				 *
				 * ArrayList<File> targetFileList = fileTypeMap.get(type);
				 *
				 * if (targetFileList == null || targetFileList.size() == 0) {
				 * System.out.println(" 当前路径 " + curDirPath + " 不存在类型 " + type + "的文件!");
				 * continue; }
				 *
				 * for (int j = 0; j < targetFileList.size(); j++) { File targetTypeFile =
				 * targetFileList.get(j); String originName = targetTypeFile.getName(); String
				 * mdName = getMD5Three(targetTypeFile.getAbsolutePath()); String mdtype =
				 * getFileTypeWithPoint(targetTypeFile.getName()); String new_md_Name = mdName +
				 * mdtype; tryReName(targetTypeFile, new_md_Name);
				 *
				 * }
				 *
				 * }
				 *
				 * for (int i = 0; i < inputParamFileList.size(); i++) { File targetTypeFile =
				 * inputParamFileList.get(i); String originName = targetTypeFile.getName();
				 * String mdName = getMD5Three(targetTypeFile.getAbsolutePath()); String mdtype
				 * = getFileTypeWithPoint(targetTypeFile.getName()); String new_md_Name = mdName
				 * + mdtype; tryReName(targetTypeFile, new_md_Name); }
				 */

			}

			return super.applyDir_SubFileListRule5(allSubDirFileList, allSubRealFileList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");// 设置日期格式
//            SimpleDateFormat df_hms = new SimpleDateFormat("HHmmss");//设置日期格式
			Date curDate = new Date();
			String date = df.format(curDate);
//            String preHMS = df.format(df_hms);

			if (isSearchAllFile2CurDirFlag) {
				// 比那里所有 类型的 文件 并 重新命名
				tryReNameOperation(fileTypeMap);

			} else {

				for (int i = 0; i < inputTypeList.size(); i++) {
					String type = inputTypeList.get(i);

					ArrayList<File> targetFileList = fileTypeMap.get(type);

					if (targetFileList == null || targetFileList.size() == 0) {
						System.out.println(" 当前路径 " + curDirPath + " 不存在类型 " + type + "的文件!");
						continue;
					}

					for (int j = 0; j < targetFileList.size(); j++) {
						File targetTypeFile = targetFileList.get(j);
						String originName = targetTypeFile.getName();
						String mdName = getMD5Three(targetTypeFile.getAbsolutePath());
						String mdtype = getFileTypeWithPoint(targetTypeFile.getName());
						String new_md_Name = mdName + mdtype;
						tryReName(targetTypeFile, new_md_Name);

					}

				}

				for (int i = 0; i < inputParamFileList.size(); i++) {
					File targetTypeFile = inputParamFileList.get(i);
					String originName = targetTypeFile.getName();
					String mdName = getMD5Three(targetTypeFile.getAbsolutePath());
					String mdtype = getFileTypeWithPoint(targetTypeFile.getName());
					String new_md_Name = mdName + mdtype;
					tryReName(targetTypeFile, new_md_Name);
				}

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		void tryReNameByDir_Type(ArrayList<File> allDirFile, ArrayList<String> mInputTypes) {

			for (int i = 0; i < allDirFile.size(); i++) {
				File dirFile = allDirFile.get(i);
				File[] subFile = dirFile.listFiles();
				if (subFile == null || subFile.length <= 0) {

					continue;
				}

				// 用于计数
				Map<String, Integer> orderMap = new HashMap<String, Integer>();

				for (int j = 0; j < subFile.length; j++) {
					File realFileItem = subFile[j];
					if (realFileItem.isDirectory()) {
						continue;
					}

					String FileTypeStr = getFileTypeWithPoint_unknow(realFileItem.getName());
					if (!mInputTypes.contains(FileTypeStr)) { // 如果 有输入类型 那么把不满足输入类型的 文件过滤掉
						continue;
					}
					Integer curIndex = orderMap.get(FileTypeStr);
					if (curIndex == null) {
						orderMap.put(FileTypeStr, 1);
						curIndex = 0;
					}

					curIndex = curIndex + 1;
					orderMap.put(FileTypeStr, curIndex);
					String newName = curIndex + FileTypeStr;
					System.out.println("依据文件夹 单独重命名文件");
					tryReName(realFileItem, newName);

				}
			}

		}

		void tryReNameByDir(ArrayList<File> allDirFile) {

			for (int i = 0; i < allDirFile.size(); i++) {
				File dirFile = allDirFile.get(i);
				File[] subFile = dirFile.listFiles();
				if (subFile == null || subFile.length <= 0) {

					continue;
				}

				// 用于计数
				Map<String, Integer> orderMap = new HashMap<String, Integer>();

				for (int j = 0; j < subFile.length; j++) {
					File realFileItem = subFile[j];
					if (realFileItem.isDirectory()) {
						continue;
					}

					String FileTypeStr = getFileTypeWithPoint_unknow(realFileItem.getName());
					Integer curIndex = orderMap.get(FileTypeStr);
					if (curIndex == null) {
						orderMap.put(FileTypeStr, 1);
						curIndex = 0;
					}

					curIndex = curIndex + 1;
					orderMap.put(FileTypeStr, curIndex);
					String newName = curIndex + FileTypeStr;
					System.out.println("依据文件夹 单独重命名文件");
					tryReName(realFileItem, newName);

				}
			}

		}

		@SuppressWarnings("unchecked")
		boolean tryReNameOperation(HashMap<String, ArrayList<File>> arrFileMap) {
			boolean executeFlag = false;
			Map.Entry<String, ArrayList<File>> entry;

			if (arrFileMap != null) {
				Iterator iterator = arrFileMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map的Value
					ArrayList<File> fileArr = entry.getValue(); // Map的Value

					for (int i = 0; i < fileArr.size(); i++) {
						File curFile = fileArr.get(i);
//                        String curFileName = curFile.getName();
						String mdName = getMD5Three(curFile.getAbsolutePath());
						String mdtype = getFileTypeWithPoint(curFile.getName());
						String new_md_Name = mdName + mdtype;
						tryReName(curFile, new_md_Name);
					}

				}
			}

			return executeFlag;
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "\n" + Cur_Bat_Name
					+ " #_26           ### 把当前所有文件夹下 依据文件夹重新开始把实体文件重命名  ./A/aa.mp4  ./A/bb.mp4   ./A/xx.jpg >>> ./A/1.mp4  ./A/2.mp4   ./A/3.mp4  ./A/1.jpg (依据文件夹重新1开始)!  "
					+ "\n" + Cur_Bat_Name
					+ "#_26  .mp4         ### 把当前所有文件夹下 依据文件夹重新开始把实体文件重命名 .mp4类型文件 其他类型不变  ./A/aa.mp4  ./A/bb.mp4   ./A/xx.jpg >>> ./A/1.mp4  ./A/2.mp4   ./A/3.mp4  ./A/xx.jpg (依据文件夹 mp4类型文件重新1开始)!  "
					+

					"";
		}

	}

	class Time_Head_Rule_25 extends Basic_Rule {
		int originType;
		int targetType;

		Time_Head_Rule_25() {
			super("#", 25, 5);
			originType = -1;
			targetType = -1;
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_25  1992_" + getCurrentYear() + "   ##打开notepad输出当前1992年至今年"
					+ getCurrentYear() + "年 年月历   MD格式类型(年月里) \n" + Cur_Bat_Name
					+ "  #_25  1992_2020   ##打开notepad输出当前1992年至2020年 年月历   MD格式类型(年月里) \n" + Cur_Bat_Name
					+ "  #_25  1990_   ##打开notepad输出当前1990年至今年" + getCurrentYear() + " 年月历 MD格式类型(年月里) \n"
					+ Cur_Bat_Name + " #_25  _2010   ##打开notepad输出 1992(默认)至2010年年月历 MD格式类型(年月里) \n ";
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			// 获取到装换的类型
			String inputFileTypeParams = inputParamList.get(inputParamList.size() - 1);

			if (!inputFileTypeParams.contains("_")) {
				Flag = false;
				System.out.println("无法检测到当前 第25 Rule   起始年份_截止年份   请检查后重新执行");
			} else {

				if (inputFileTypeParams.endsWith("_")) {
					String target = "";
					String[] parmas = inputFileTypeParams.split("_");
					String origin = parmas[0];
					System.out.println("item=" + inputFileTypeParams + "   origin=" + origin + "     target=" + target);
					if (isNumeric(origin) && !"".equals(origin)) {
						originType = Integer.parseInt(origin);
					}

					if (isNumeric(target) && !"".equals(target)) {
						targetType = Integer.parseInt(target);
					}
				} else {
					String[] parmas = inputFileTypeParams.split("_");
					System.out.println(
							"item=" + inputFileTypeParams + "   origin=" + parmas[0] + "     target=" + parmas[1]);
					if (parmas.length >= 2) {

						if (isNumeric(parmas[0])) {
							originType = Integer.parseInt(parmas[0]);
						}

						if (isNumeric(parmas[1])) {
							targetType = Integer.parseInt(parmas[1]);
						}

					}

				}

				Flag = true;

			}

			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
				ArrayList<File> allSubRealFileList) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			if (originType == -1) { // 没有获取到 初始化值 那么 默认就是 1992
				originType = 1992;
			}

			if (targetType == -1) { // 没有获取到 初始化值 那么 默认就是 1992
				targetType = getCurrentYear();
			}

			// 确保 origin 是 小于 targetType 的
			if (originType > targetType) {
				targetType = originType + targetType;
				originType = targetType - originType;
				targetType = targetType - originType;
			}
			StringBuilder sb = new StringBuilder();
			System.out.println("targetType = " + targetType + "    originType=" + originType);
			for (int i = targetType; i >= originType; i--) {
				sb.append("   \n");
				sb.append("   \n");
				sb.append("   \n");

				for (int j = 12; j >= 1; j--) {
					sb.append("### " + i + "." + (j > 9 ? "" + j : "0" + j));

					sb.append("   \n");
					sb.append("   \n");
				}

				sb.append("## " + i);
				sb.append("   \n");
				sb.append("   \n");

			}
			writeContentToFile(G2_Temp_Text_File, sb.toString());
			NotePadOpenTargetFile(G2_Temp_Text_File.getAbsolutePath());
			return super.applyDir_SubFileListRule5(allSubDirFileList, allSubRealFileList);
		}

	}
	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
//     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

	// 对 图片文件进行 裁剪 -20_-20_20_20
	// 上下左右的padding 上 -20 20 图片往下移动20 显示20的空白
	// 上 正-》 显示20的空白 负》 图片缩进20 去掉图片的20距离
	// 下 正-》 下显示20的空白 负》 下图片缩进20 去掉图片的20距离
	// 左 正-》 左显示20的空白 负》 左图片缩进20 去掉图片的20距离
	// 右 正-》 右显示20的空白 负》 右图片缩进20 去掉图片的20距离

	// 在当前的目录 与 子目录 之间 新增 一层文件夹 , 文件夹名称任意 用户输入
	class add_Middle_Dir_Rule_24 extends Basic_Rule {

		add_Middle_Dir_Rule_24() {
			super("#", 24, 4); //
			middle_dir_name = "mp4";
		}

		// 中间文件夹的名称
		String middle_dir_name;

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_24  jpg   ##在当前目录与子目录之间加一层目录jpg  1/1.jpg -> 1/jpg/1.jpg \n" + Cur_Bat_Name
					+ "  #_24  mp4   ##在当前目录与子目录之间加一层目录jpg  1/1.mp4 -> 1/mp4/1.mp4  1/1.jpg -> 1/mp4/1.jpg \n"
					+ Cur_Bat_Name
					+ " #_24  gif  ##在当前目录与子目录之间加一层目录jpg  1/1.mp4 -> 1/gif/1.mp4  1/1.jpg -> 1/gif/1.jpg \\n ";
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			// 获取到装换的类型
			String inputFileTypeParams = inputParamList.get(inputParamList.size() - 1);

			if (inputFileTypeParams == null || "".equals(inputFileTypeParams) || inputFileTypeParams.contains("#")
					|| inputFileTypeParams.contains("~") || inputFileTypeParams.contains("`")
					|| inputFileTypeParams.contains("@") || inputFileTypeParams.contains("$")
					|| inputFileTypeParams.contains("%") || inputFileTypeParams.contains("^")
					|| inputFileTypeParams.contains("&") || inputFileTypeParams.contains("*")
					|| inputFileTypeParams.contains("(") || inputFileTypeParams.contains(")")
					|| inputFileTypeParams.contains("=") || inputFileTypeParams.contains("+")
					|| inputFileTypeParams.contains("-") || inputFileTypeParams.contains("/")
					|| inputFileTypeParams.contains("?") || inputFileTypeParams.contains("[")
					|| inputFileTypeParams.contains("|") || inputFileTypeParams.contains("{")
					|| inputFileTypeParams.contains("}") || inputFileTypeParams.contains("]")
					|| inputFileTypeParams.contains("|") || inputFileTypeParams.contains(",")
					|| inputFileTypeParams.contains("'") || inputFileTypeParams.contains("!")) {

				Flag = false;
				System.out.println(
						"无法检测到当前 第24 Rule  创建中间夹层目录的名称不合规 请检查   请检查后重新执行 inputParams = " + inputFileTypeParams);
			} else {
				middle_dir_name = inputFileTypeParams;
			}

			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub

			for (int i = 0; i < curFileList.size(); i++) {
				File sonFile = curFileList.get(i);

				File middle_dir = new File(sonFile.getAbsoluteFile() + File.separator + middle_dir_name);
				if (!middle_dir.exists()) {
					middle_dir.mkdirs();
					System.out.println(" FFFFF  middle_dir = " + middle_dir.getAbsolutePath());
				}

				File sunFile = new File(middle_dir.getAbsolutePath() + File.separator + sonFile.getName());

				if (sonFile.isDirectory()) {

					ArrayList<File> searchRealList = getAllSubFile(sonFile);
					String sonFile_abs = sonFile.getAbsolutePath();

					for (int j = 0; j < searchRealList.size(); j++) {
						File realFile = searchRealList.get(j);
						String realFile_abs = realFile.getAbsolutePath();
						String fixed_realFile_abs = realFile_abs.replace(sonFile_abs, middle_dir.getAbsolutePath());
						File targetFile = new File(fixed_realFile_abs);
						fileCopy(realFile, targetFile);
						realFile.delete();
					}

				} else {
//				copyFile(sonFile, sunFile);
					fileCopy(sonFile, sunFile);
				}
				sonFile.delete();
				System.out.println(" EEEEEE ");

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

	}

	// 检查文件的真实类型 并对 那些 不符合真实类型的文件 进行提示
	class CheckFileRealFormat_Rule_23 extends Basic_Rule {

		final HashMap<String, String> mFileTypes; // 魔数 ----- 类型
		final HashMap<String, ArrayList<String>> mSameMoShu_ArrType_Map; // 使用相同魔数 8位字符的文件
		ArrayList<String> NoMoShuTypeList; // 没有魔数的类型 比如 txt
		boolean isFixToRealType = false;

		CheckFileRealFormat_Rule_23() {
			super("#", 23, 4); //
			mFileTypes = new HashMap<String, String>();
			mSameMoShu_ArrType_Map = new HashMap<String, ArrayList<String>>();
			NoMoShuTypeList = new ArrayList<String>();

			isFixToRealType = false;
			initMoShuTypeMap();
			InitInitSameMoShuMap_NoMoShuTypeList();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// TODO Auto-generated method stub

			for (int i = 0; i < inputParamList.size(); i++) {
				String inputParam = inputParamList.get(i);
				if (inputParam.trim().toLowerCase().startsWith("fix2real_true")) {
					isFixToRealType = true;
				} else {
					isFixToRealType = false;
				}

			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_" + rule_index + "    ### 对当前目录的文件进行真实类型的检测[通过魔数字]并打印那些类型和魔数不一样文件的列表信息  \n"
					+ Cur_Bat_Name + " #_" + rule_index
					+ "  fix2real_false    ### 对当前目录的文件进行真实类型的检测[通过魔数字]并打印那些类型和魔数不一样文件的列表信息 \n"

					+ Cur_Bat_Name + " #_" + rule_index
					+ "  fix2real_true    ### 对当前目录的文件进行真实类型的检测[通过魔数字]并修正那些类型和魔数不一样文件的列表信息 \n"

			;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub
			int different_type_file_index = 1;
			ArrayList<File> differentRealTypeFileList = new ArrayList<File>();
			System.out.println("════════════════════════" + " 当前目录文件的 魔数情况 Begin " + "════════════════════════");
			curRealFileList.sort(new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					// TODO Auto-generated method stub
					String o1_type = getFileTypeWithPoint(o1.getName());
					String o2_type = getFileTypeWithPoint(o2.getName());
					return o1_type.compareTo(o2_type);
				}
			});
			for (int i = 0; i < curRealFileList.size(); i++) {

				File realFile = curRealFileList.get(i);
				String originFileType = getFileTypeWithPoint(realFile.getName()).toLowerCase();
				if ("".equals(originFileType)) {
					originFileType = "unknow";
				}
				String fileNameWithoutPoint = getFileTypeWithPoint(realFile.getName()).trim().toLowerCase();
				String realfileType = getFileType(realFile.getAbsolutePath());

				if (realfileType == null) {

					realfileType = "unknow";
//		            	if("".equals(originFileType)) {
//		            		fileType = "";
//		            	}else {
//		            		fileType = originFileType;
//		            	}
				}
				String moshu = getFileHeader(realFile.getAbsolutePath());

				System.out.println("Index[" + i + "]     showType=[" + originFileType + "]      realType=[."
						+ realfileType + "]      MoShu=[" + moshu + "]     PATH=[" + realFile.getAbsolutePath() + "]");

				/*
				 * final HashMap<String, String> mFileTypes; // 魔数 ----- 类型 final
				 * HashMap<String, ArrayList<String>> mSameMoShu_ArrType_Map; // 使用相同魔数 8位字符的文件
				 * ArrayList<String> NoMoShuTypeList ; // 没有魔数的类型 比如 txt
				 */

				if (!originFileType.equals(("." + realfileType).toLowerCase())) {

					if ("unknow".equals(realfileType)
							&& NoMoShuTypeList.contains(originFileType.replace(".", "").toLowerCase())) {
						continue;
					}
					// 如果当前的 文件类型 没有魔术 那么 不对比

					ArrayList<String> sameTypeList = mSameMoShu_ArrType_Map.get(moshu);

					// 如果 两个 文件类型 使用 相同的 type 那么 也排除 这样的文件
					if (sameTypeList != null && sameTypeList.contains(originFileType.replace(".", "").toLowerCase())) {

						continue;
					}

					differentRealTypeFileList.add(realFile);
					different_type_file_index++;

				}

			}

			System.out.println("════════════════════════" + " 当前目录文件的 魔数情况 End " + "════════════════════════");

			if (differentRealTypeFileList.size() > 0) {
				System.out.println("════════════" + " 打印 【" + differentRealTypeFileList.size()
						+ "】 个 显示的类型 和 真实的类型不同的文件 Begin " + "════════════");

				for (int i = 0; i < differentRealTypeFileList.size(); i++) {
					File realType_showType_Diff_File = differentRealTypeFileList.get(i);
					String oldName = realType_showType_Diff_File.getName();
					String showTypeStr = getFileTypeWithPoint(realType_showType_Diff_File.getName());
					String realType = getFileType(realType_showType_Diff_File.getAbsolutePath());
					if (realType == null) {
						realType = "";
					}

					String originFileType = getFileTypeWithPoint(realType_showType_Diff_File.getName());
					String moshu = getFileHeader(realType_showType_Diff_File.getAbsolutePath());
					String fileNameNoPoint = getFileNameNoPointNoLowerCase(realType_showType_Diff_File.getName());

					String realfileType = getFileType(realType_showType_Diff_File.getAbsolutePath());

					if (realfileType == null) {
						realfileType = "unknow";
					}

					System.out.println("Diff_Index[" + i + "]     showType=[" + originFileType + "]      realType=[."
							+ realfileType + "]      MoShu=[" + moshu + "]     PATH=["
							+ realType_showType_Diff_File.getAbsolutePath() + "]  initMoshuTypeItem(\"" + moshu
							+ "\", \"" + (realfileType.equals("unknow") ? showTypeStr.replace(".", "") : realfileType)
							+ "\"); ");

				}
				System.out.println("════════════" + " 打印 【" + differentRealTypeFileList.size()
						+ "】 个 显示的类型 和 真实的类型不同的文件 End " + "════════════");

			} else {

				System.out.println("════════════恭喜  当前所有文件 真实类型 和 显示类型 全都相同 " + "════════════");

			}

			if (isFixToRealType && differentRealTypeFileList.size() > 0) {
				System.out.println("═══════════════════  执行 [" + differentRealTypeFileList.size()
						+ "]个文件 改为真实类型的 操作 Begin ══════════════════");
				for (int i = 0; i < differentRealTypeFileList.size(); i++) {
					File realType_showType_Diff_File = differentRealTypeFileList.get(i);
					String oldName = realType_showType_Diff_File.getName();
					String showTypeStr = getFileTypeWithPoint(realType_showType_Diff_File.getName());
					String realType = getFileType(realType_showType_Diff_File.getAbsolutePath());
					if (realType == null) {
						realType = "";
					}
					String moshu = getFileHeader(realType_showType_Diff_File.getAbsolutePath());
					String fileNameNoPoint = getFileNameNoPointNoLowerCase(realType_showType_Diff_File.getName());

					String newFileName = fileNameNoPoint + "." + realType;
					tryReName(realType_showType_Diff_File, newFileName);
					System.out.println("Index[" + i + "]     showType=[" + showTypeStr + "]      realType=[." + realType
							+ "]      MoShu=[" + moshu + "]  OldName=[" + oldName + "]    NewName[" + newFileName
							+ "]     isFixToRealType=[" + isFixToRealType + "]    PATH=["
							+ realType_showType_Diff_File.getAbsolutePath() + "]");

				}
				System.out.println("═══════════════════  执行 [" + differentRealTypeFileList.size()
						+ "]个文件 改为真实类型的 操作 End ══════════════════");

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void initMoShuTypeMap() {

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
			initMoshuTypeItem("504B0304", "zip"); // 日记本
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
			initMoshuTypeItem("D7CDC69A", "wmf");
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
			initMoshuTypeItem("2F2A0A20", "JAVA");

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
			initMoshuTypeItem("3C21444F", "html");
		}

		void initMoshuTypeItem(String key, String value) {
			mFileTypes.put(key.toUpperCase(), value.toLowerCase());
		}

		/**
		 * @param filePath 文件路径
		 * @return 文件头信息
		 * @author wlx
		 *         <p>
		 *         方法描述：根据文件路径获取文件头信息
		 */
		public String getFileType(String filePath) {
//	        System.out.println(getFileHeader(filePath));
//	        System.out.println(mFileTypes.get(getFileHeader(filePath)));
			return mFileTypes.get(getFileHeader(filePath));
		}

		public void InitInitSameMoShuMap_NoMoShuTypeList() {
//	    	mSameMoShu_ArrType_Map
// NoMoShuTypeList           abcdef

			// ==============================================
			NoMoShuTypeList.add("txt");
			NoMoShuTypeList.add("tmp");
			NoMoShuTypeList.add("vsdconfig");
			NoMoShuTypeList.add("ver");
			NoMoShuTypeList.add("tlog");
			NoMoShuTypeList.add("tcl");
			NoMoShuTypeList.add("swift");
			NoMoShuTypeList.add("sql");
			NoMoShuTypeList.add("sha512");
			NoMoShuTypeList.add("sha1");
			NoMoShuTypeList.add("s");
			NoMoShuTypeList.add("h");
			NoMoShuTypeList.add("rb");
			NoMoShuTypeList.add("py");
			NoMoShuTypeList.add("pm");
			NoMoShuTypeList.add("pkgdef");
			NoMoShuTypeList.add("md");
			NoMoShuTypeList.add("lex");
			NoMoShuTypeList.add("lzz");
			NoMoShuTypeList.add("json");
			NoMoShuTypeList.add("ini");
			NoMoShuTypeList.add("cpp");
			NoMoShuTypeList.add("code");
			NoMoShuTypeList.add("cer");
			NoMoShuTypeList.add("c");
			NoMoShuTypeList.add("admx");
			NoMoShuTypeList.add("adml");
			NoMoShuTypeList.add("vert");
			NoMoShuTypeList.add("tmlanguage");
			NoMoShuTypeList.add("swift");
			NoMoShuTypeList.add("sh");
			NoMoShuTypeList.add("js");
			NoMoShuTypeList.add("idl");
			NoMoShuTypeList.add("expected");

			NoMoShuTypeList.add("exp");
			NoMoShuTypeList.add("def");
			NoMoShuTypeList.add("db");
			NoMoShuTypeList.add("data");
			NoMoShuTypeList.add("dat");
			NoMoShuTypeList.add("bat");
			NoMoShuTypeList.add("at");
			NoMoShuTypeList.add("len");
			NoMoShuTypeList.add("prop");
			NoMoShuTypeList.add("snippet");
			NoMoShuTypeList.add("swift");
			NoMoShuTypeList.add("tmlanguage");
			NoMoShuTypeList.add("ts");
			// ==============================================
			String moshu_zip = "504B0304";
			ArrayList<String> same_zip_TypeList = new ArrayList<String>();
			same_zip_TypeList.add("zip");
			same_zip_TypeList.add("xlsx");
			same_zip_TypeList.add("pptx");
			same_zip_TypeList.add("nupkg");
			same_zip_TypeList.add("jar");
			same_zip_TypeList.add("dotm");
			same_zip_TypeList.add("apk");
			mSameMoShu_ArrType_Map.put(moshu_zip, same_zip_TypeList);

			String moshu_exe = "4D5A9000";
			ArrayList<String> same_exe_TypeList = new ArrayList<String>();
			same_exe_TypeList.add("exe");
			same_exe_TypeList.add("toc");
			same_exe_TypeList.add("node");
			same_exe_TypeList.add("mun");
			same_exe_TypeList.add("mui");
			same_exe_TypeList.add("olb");
			same_exe_TypeList.add("iltoc");
			same_exe_TypeList.add("ildll");
			same_exe_TypeList.add("dll");
			same_exe_TypeList.add("sys");
			mSameMoShu_ArrType_Map.put(moshu_exe, same_exe_TypeList);

			String moshu_so = "7F454C46";
			ArrayList<String> same_so_TypeList = new ArrayList<String>();
			same_so_TypeList.add("so");
			same_so_TypeList.add("elf");
			mSameMoShu_ArrType_Map.put(moshu_so, same_so_TypeList);

			String moshu_xls = "D0CF11E0";
			ArrayList<String> same_xls_TypeList = new ArrayList<String>();
			same_xls_TypeList.add("xls");
			same_xls_TypeList.add("doc");
			same_xls_TypeList.add("ptt");
			mSameMoShu_ArrType_Map.put(moshu_xls, same_xls_TypeList);

			String moshu_xml = "3C3F786D";
			ArrayList<String> same_xml_TypeList = new ArrayList<String>();
			same_xml_TypeList.add("xml");
			same_xml_TypeList.add("pom");
			same_xml_TypeList.add("manifest");
			same_xml_TypeList.add("iml");
			same_xml_TypeList.add("filters");
			same_xml_TypeList.add("config");
			same_xml_TypeList.add("vcxproj");

			mSameMoShu_ArrType_Map.put(moshu_xml, same_xml_TypeList);

			String moshu_JAVA = "2F2A0A20";
			ArrayList<String> same_Java_TypeList = new ArrayList<String>();
			same_Java_TypeList.add("java");
			same_Java_TypeList.add("kt");
			same_Java_TypeList.add("groovy");
			same_Java_TypeList.add("pm");

			mSameMoShu_ArrType_Map.put(moshu_JAVA, same_Java_TypeList);

			String moshu_inf = "FFFE5B00";
			ArrayList<String> same_inf_TypeList = new ArrayList<String>();
			same_inf_TypeList.add("inf");
			same_inf_TypeList.add("inf_loc");
			mSameMoShu_ArrType_Map.put(moshu_inf, same_inf_TypeList);

			String moshu_pdb = "4D696372";
			ArrayList<String> same_pdb_TypeList = new ArrayList<String>();
			same_pdb_TypeList.add("pdb");
			same_pdb_TypeList.add("ilpdb");
			same_pdb_TypeList.add("iltocpdb");

			mSameMoShu_ArrType_Map.put(moshu_pdb, same_pdb_TypeList);

			String moshu_admx = "EFBBBF3C";
			ArrayList<String> same_admx_TypeList = new ArrayList<String>();
			same_admx_TypeList.add("nuspec");
			same_admx_TypeList.add("admx");
			mSameMoShu_ArrType_Map.put(moshu_admx, same_pdb_TypeList);

			String moshu_swift = "7F454C46";
			ArrayList<String> same_swift_TypeList = new ArrayList<String>();
			same_swift_TypeList.add("swift");
			same_swift_TypeList.add("gyb");
			same_swift_TypeList.add("sil");
			mSameMoShu_ArrType_Map.put(moshu_swift, same_so_TypeList);

			// ==============================================
			initMoshuTypeItem("FFFE5B00", "inf_loc");
			initMoshuTypeItem("22205669", "vim");
			initMoshuTypeItem("6C657420", "vim");
			initMoshuTypeItem("00010000", "ttf");
			initMoshuTypeItem("3C737667", "svg");
			initMoshuTypeItem("6F2F636F", "bin");

			initMoshuTypeItem("D4C3B2A1", "cap");
			initMoshuTypeItem("2F2F3D3D", "mm");
			initMoshuTypeItem("2F2A2120", "ts");
			initMoshuTypeItem("4D5A9000", "exe");
			initMoshuTypeItem("23707261", "mof");
			initMoshuTypeItem("482E517F", "ipdb");
			initMoshuTypeItem("2F2F2052", "gyb");
			initMoshuTypeItem("D4C3B2A1", "pcap");
			initMoshuTypeItem("23232323", "properties");
			initMoshuTypeItem("7F454C46", "so");
			initMoshuTypeItem("64862300", "iobj");
			initMoshuTypeItem("FFD8FFE0", "jpeg");
			initMoshuTypeItem("2E636C61", "smali");
			initMoshuTypeItem("4F424A43", "rst");
			initMoshuTypeItem("4155544F", "rst");
			initMoshuTypeItem("420D0D0A", "pyc");
			initMoshuTypeItem("692F6A61", "bin");
			initMoshuTypeItem("D0CF11E0", "xls");
			initMoshuTypeItem("3C3F786D", "xml");
			initMoshuTypeItem("23202121", "pl");
			initMoshuTypeItem("4D696372", "pdb");
			initMoshuTypeItem("04000000", "pak");
			initMoshuTypeItem("626F6479", "out");
			initMoshuTypeItem("05000000", "aux");
			initMoshuTypeItem("4D534654", "olb");
			initMoshuTypeItem("EFBBBF3C", "nuspec");
			initMoshuTypeItem("24504D54", "nma");
			initMoshuTypeItem("6E006F00", "nlp");
			initMoshuTypeItem("23206372", "msg");
			initMoshuTypeItem("0000001C", "mp4");
			initMoshuTypeItem("00000018", "mp4");
			initMoshuTypeItem("213C6172", "lib");
			initMoshuTypeItem("7B7B2320", "jst");
			initMoshuTypeItem("2F2F203D", "js");
			initMoshuTypeItem("4D696372", "iltocpdb");
			initMoshuTypeItem("4D696372", "ilpdb");
			initMoshuTypeItem("00000100", "ico");
			initMoshuTypeItem("2320456E", "enc");
		}

		boolean isTypeUsedSameMoShu(String showType, String realType) {
			boolean isSameMoShu = false;

			return false;
		}

		/**
		 * @param filePath 文件路径
		 * @return 文件头信息
		 * @author wlx
		 *         <p>
		 *         方法描述：根据文件路径获取文件头信息
		 */
		public String getFileHeader(String filePath) {
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
				value = value.toUpperCase();
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
//			System.out.println("文件:" + filePath + "    魔数byte[4]_value_moshu = " + value);

			return value;
		}

		/**
		 * @param src 要读取文件头信息的文件的byte数组
		 * @return 文件头信息
		 * @author wlx
		 *         <p>
		 *         方法描述：将要读取文件头信息的文件的byte数组转换成string类型表示
		 */
		String bytesToHexString(byte[] src) {
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

	}

	class ReSize_Img_Rule_22 extends Basic_Rule {

		ArrayList<String> fliterTypeList;
		ArrayList<File> mSrcFileImage; // 符合 过滤 条件的 当前目录的文件夹的集合
		ArrayList<Integer> up_down_left_right;

		ReSize_Img_Rule_22() {
			super("#", 22, 4); //
			mSrcFileImage = new ArrayList<File>();
			fliterTypeList = new ArrayList<String>();
			up_down_left_right = new ArrayList<Integer>();

			fliterTypeList.add(".jpg");
			fliterTypeList.add(".png");
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				System.out.println("initParamsWithInputList_inputParamList[" + i + "] = " + inputParamList.get(i));

				if (i == 1) {
					String one_param = inputParamList.get(1);
					if (!one_param.contains("_")) { // 当前的第一个参数不是 上_下_左_右 参数
						System.out.println("当前的第二个参数不是 上_下_左_右 参数");
						return false;
					}
					up_down_left_right = calculSize(one_param);
					continue;
				}

				System.out.println("File[" + i + "] = " + curDirPath + File.separator + inputParamList.get(i));
				File inputFile = new File(curDirPath + File.separator + inputParamList.get(i));
				String fileName_lower = inputFile.getName().toLowerCase();
				if (inputFile.exists() && (fileName_lower.endsWith(".jpg") || fileName_lower.endsWith(".png"))) {
					mSrcFileImage.add(inputFile);
				}
			}
			if (mSrcFileImage.size() == 0 && inputParamList.size() >= 3) {
				System.out.println("用户输入了 无效的文件  请检查输入的文件名称！ ");
				return false;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		// -20_-20_-20_-20
		ArrayList<Integer> calculSize(String size_str) {
			ArrayList<Integer> size_4_List = new ArrayList<Integer>();
			String checkStr = size_str.replaceAll("_", "").replace("+", "").replaceAll("-", "");
			if (!isNumeric(checkStr.trim())) {
				System.out.println("当前的 上_下_左_右 参数 输入错误(1):" + size_str);
				return size_4_List;
			}
			String[] arr = size_str.split("_");
			if (arr == null || arr.length != 4) {
				System.out.println("当前的 上_下_左_右 参数 输入错误(2):" + size_str);
				return size_4_List;
			}

			Integer up_int = Integer.parseInt(arr[0]);
			Integer down_int = Integer.parseInt(arr[1]);
			Integer left_int = Integer.parseInt(arr[2]);
			Integer right_int = Integer.parseInt(arr[3]);

			size_4_List.add(up_int);
			size_4_List.add(down_int);
			size_4_List.add(left_int);
			size_4_List.add(right_int);

			return size_4_List;

		}

		boolean checkInFlitterList(String fileName) {
			boolean result = false;

			for (int i = 0; i < fliterTypeList.size(); i++) {
				if (fileName.endsWith(fliterTypeList.get(i))) {
					result = true;
					break;
				}
			}
			return result;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			ArrayList<File> operationFileList = new ArrayList<File>();
			ArrayList<File> newOperationFileList = new ArrayList<File>();
			if (mSrcFileImage.size() > 0) {
				System.out.println("═══════════════════ 只对当前输入 Img 文件进行处理");
				operationFileList.addAll(mSrcFileImage);
				for (int i = 0; i < operationFileList.size(); i++) {
					File inputFile = operationFileList.get(i);
					System.out.println("inputFile[" + i + "] = " + inputFile.getName());
				}
			} else {
				System.out.println("═══════════════════ 用户输入文件为空--对本地所有Img jpg png 文件进行处理");

				for (int i = 0; i < curRealFileList.size(); i++) {
					File fileItem = curRealFileList.get(i);
					String fileName = fileItem.getName();
					String fileName_lower = fileName.toLowerCase();

					boolean isTypeInList = checkInFlitterList(fileName_lower);
					if (isTypeInList) {
						operationFileList.add(fileItem);
					}
				}

			}

			String Dir_Name_Padding = "Img_Padding_" + getTimeStamp();
			File dirPaddingFile = new File(curDirPath + File.separator + Dir_Name_Padding);
			if (!dirPaddingFile.exists()) {
				dirPaddingFile.mkdirs();
			}

			for (int i = 0; i < operationFileList.size(); i++) {
				File srcFile = operationFileList.get(i);
				String fileName = srcFile.getName();
				File newFileItem = new File(dirPaddingFile.getAbsolutePath() + File.separator + fileName);
				fileCopy(srcFile, newFileItem);
				newOperationFileList.add(newFileItem);
			}

			int up_int = up_down_left_right.get(0);
			int down_int = up_down_left_right.get(1);
			int left_int = up_down_left_right.get(2);
			int right_int = up_down_left_right.get(3);
			String up_str = up_int > 0 ? "【上增加" + up_int + "空白】" : "【上减少" + up_int + "内容】";
			String down_str = down_int > 0 ? "【下增加" + down_int + "空白】" : "【下减少" + down_int + "内容】";
			String left_str = left_int > 0 ? "【左增加" + left_int + "空白】" : "【左减少" + left_int + "内容】";
			String right_str = right_int > 0 ? "【右增加" + right_int + "空白】" : "【右减少" + right_int + "内容】";

			System.out.println("当前批操作集合:" + up_str + down_str + left_str + right_str);
			System.out.println("当前操作文件数量: " + newOperationFileList.size());
			for (int i = 0; i < newOperationFileList.size(); i++) {
				File imageFile = newOperationFileList.get(i);
				String fileName = imageFile.getName();
				System.out.println("FIle[" + i + "] =" + fileName + "  开始执行操作！ ");
				ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());

				BufferedImage originImage = getBufferedImage(imageFile);
				int h = originImage.getHeight();
				int w = originImage.getWidth();
				int high = originImage.getHeight();
				int width = originImage.getWidth();
				int up_down_sum = up_int + down_int;
				int left_right_sum = left_int + right_int;

				int target_width = width + left_right_sum;
				int target_high = high + up_down_sum;
				// 显示图片的起始位置

				int width_input = target_width;
				int height_input = target_high;

				int srcImage_x = left_int; // 原画的 起始x坐标
				int srcImage_width = width + right_int; // 原画的 起始坐标

				int srcImage_y = up_int; // 原画的 起始y坐标
				int srcImage_high = high + down_int; // 原画的 起始y坐标

				double ratiox = 1.0;
				double ratioy = 1.0;

				ratiox = w * ratiox / width_input;
				ratioy = h * ratioy / height_input;

				// 缩小图片
				if (ratiox >= 1) {
					if (ratioy < 1) {
						ratiox = height_input * 1.0 / h;
					} else {
						if (ratiox > ratioy) {
							ratiox = height_input * 1.0 / h;
						} else {
							ratiox = width_input * 1.0 / w;
						}
					}
				} else {
					// 放大图片
					if (ratioy < 1) {
						if (ratiox > ratioy) {
							ratiox = height_input * 1.0 / h;
						} else {
							ratiox = width_input * 1.0 / w;
						}
					} else {
						ratiox = width_input * 1.0 / w;
					}
				}

				// 子截图 先搞定
				// X的起始坐标 如果大于0的话 那么就使用原有的坐标系0
				// 如果小于0的话 说明x起始坐标需要移动到 Math.abs(left_int)
				int origin_subImage_x = left_int >= 0 ? 0 : Math.abs(left_int);
				int origin_subImage_y = up_int >= 0 ? 0 : Math.abs(up_int);

				int origin_subImage_width = width; // 默认为图片的宽度
				if (left_int < 0 && right_int < 0) {
					origin_subImage_width = width + left_int + right_int;
				} else if (left_int < 0) {
					origin_subImage_width = width + left_int;
				} else if (right_int < 0) {
					origin_subImage_width = width + right_int;
				}

				int origin_subImage_high = high;
				if (up_int < 0 && down_int < 0) {
					origin_subImage_high = high + up_int + down_int;
				} else if (up_int < 0) {
					origin_subImage_high = high + up_int;
				} else if (down_int < 0) {
					origin_subImage_high = high + down_int;
				}

				// AffineTransformOp op = new
				// AffineTransformOp(AffineTransform.getScaleInstance(ratiox, ratiox), null);
				// originImage = op.filter(originImage, null);
				System.out.println("width=" + width + "    high=" + high);

				System.out.println("up_int=" + up_int + "    down_int=" + down_int + "     left_int=" + left_int
						+ "     right_int=" + right_int);
				System.out.println("origin_subImage_x=" + origin_subImage_x + "    origin_subImage_y="
						+ origin_subImage_y + "  origin_subImage_width =" + origin_subImage_width
						+ "  origin_subImage_high=" + origin_subImage_high);

//                originImage = originImage.getSubimage(0, origin_subImage_y, width, origin_subImage_high);
//                originImage = originImage.getSubimage(origin_subImage_x, 0, origin_subImage_width, originImage.getHeight());

				// originImage = originImage.getSubimage(origin_subImage_x, origin_subImage_y,
				// origin_subImage_width, origin_subImage_high);

				BufferedImage whiteSpace_BuffImage = generalBufferedImage_WhitePicture(target_width, target_high);

				BufferedImage combined = new BufferedImage(target_width, target_high, BufferedImage.TYPE_INT_RGB);
				// paint both images, preserving the alpha channels
				Graphics g = combined.getGraphics();
//                g.setColor(new Color(255, 255, 255));
				try {

//                    int fixed_width = srcImage_width>width?width:srcImage_width;
//                    int fixed_high = srcImage_high>high?high:srcImage_high;
//                    System.out.println("src_width="+width +"    src_high="+high);
//                    System.out.println("WhitePicture_target_width="+target_width +"    WhitePicture_target_high="+target_high);
//                    System.out.println("up_int="+up_int +"    down_int="+down_int+"     left_int="+left_int+"     right_int="+ right_int);
//                    System.out.println("srcImage_x="+srcImage_x +"    srcImage_y="+srcImage_y+"   fixed_width="+fixed_width+ "  fixed_high="+ fixed_high);
//
//                    g.drawImage(originImage, srcImage_x,srcImage_y,fixed_width , fixed_high ,null);
//                    // Save as new image
//                    ImageIO.write(combined, "jpg", imageFile);

					// ImageIO.write(originImage, "jpg", imageFile);
					int big_rect_y = up_int >= 0 ? up_int : 0;
					int big_rect_x = left_int >= 0 ? left_int : 0;

					System.out.println("输出路径下宽:" + whiteSpace_BuffImage.getWidth() + "   输出路径下高:"
							+ whiteSpace_BuffImage.getHeight());
					ImageUtil.cut(imageFile, imageFile, new Rectangle(origin_subImage_x, origin_subImage_y,
							origin_subImage_width, origin_subImage_high));

					// BufferedImage originImage = getBufferedImage(imageFile);
					BufferedImage originImage_fixed = getBufferedImage(imageFile);
					g.drawImage(whiteSpace_BuffImage, 0, 0, null);
					g.drawImage(originImage_fixed, big_rect_x, big_rect_y, null);
					System.out.println("big_rect_x = " + big_rect_x + "    big_rect_y=" + big_rect_y);
					ImageIO.write(combined, "jpg", imageFile);

				} catch (Exception e) {
					System.out.println("发生异常! ");

				} finally {
//                    if (g != null) {
//                        g.dispose();
//                    }
				}

			}

			System.out.println(" Img Padding 执行完成! ");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		public BufferedImage getBufferedImage(File file) {
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

		public BufferedImage resize(Image mImage, int w, int h) {
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

		public BufferedImage generalBufferedImage_WhitePicture(int p_width, int p_heigh) {
			BufferedImage imgBuf = null;
			int width = p_width;
			int heigh = p_heigh;
			Color currentColor = new Color(255, 255, 255);
			/*
			 *
			 * BufferedImage bi = new BufferedImage(width,heigh,
			 * BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度70,宽度150 //得到它的绘制环境(这张图片的笔)
			 * Graphics2D g2 = (Graphics2D) bi.getGraphics(); int frontSize = 550;
			 *
			 * g2.setBackground(currentColor); g2.fillRect(0,0,width,heigh);//填充一个矩形
			 * 左上角坐标(0,0),宽500,高500;填充整张图片 g2.fillRect(0,0,width,heigh);//填充整张图片(其实就是设置背景颜色)
			 * g2.setColor(currentColor);
			 *
			 */

			imgBuf = new BufferedImage(width, heigh, BufferedImage.TYPE_INT_RGB);
			Graphics curGraphic = imgBuf.getGraphics();
			// 设置颜色
			curGraphic.setColor(currentColor);
			// 填充
			curGraphic.fillRect(0, 0, imgBuf.getWidth(), imgBuf.getHeight());

			return imgBuf;
			/*
			 *
			 * try { mCurFile.createNewFile(); ImageIO.write(imgBuf, "jpg", new
			 * FileOutputStream(mCurFile));//保存图片 JPEG表示保存格式 //
			 * System.out.println("创建 RGB "+"R="+r+"  G="+g+"  B="+b+" 图片成功！");
			 *
			 * } catch (Exception e) { System.out.println("创建 RGB 图片格式出现异常！"+
			 * mCurFile.getAbsolutePath()); }
			 */

		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_A = " 无输入参数 默认对本目录下的所有 png jpg  进行 20_20_20_20 上_下_左_右的裁剪";
			String desc_B = " 对给定的图片进行 20_20_20_20 上_下_左_右的裁剪  ";
			String desc_C = " 对给定的图片进行 200_0_0_0 上_下_左_右的裁剪(顶部增加200空白像素空间)  ";
			String desc_D = " 对给定的图片进行 0_0_0_200 上_下_左_右的裁剪(底部增加200空白像素空间)  ";
			String desc_E = " 对给定的图片进行 0_200_0_200 上_下_左_右的裁剪(底部增加200 右部增加200 空白像素空间)  ";
			String desc_F = " 对给定的图片进行 -100_-100_-100_-100 上_下_左_右的裁剪(上下左右 都裁剪100 空白像素空间)  ";
			String desc_G = " 对给定的图片进行 0_-125_0_0 上_下_左_右的裁剪( 底部裁剪125 像素空间)  ";
			String desc_H = " 对给定的图片进行 0_-110_0_0 上_下_左_右的裁剪( 底部裁剪110 像素空间)  ";
			itemDesc = batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  20_20_20_20" + "    #### [索引 "
					+ index + "]  描述: " + desc_A + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  20_20_20_20" + "   <ImgFile>  "
					+ "    #### [索引 " + index + "]  描述: " + desc_B + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  200_0_0_0"
					+ "    <ImgFile>   #### [索引 " + index + "]  描述: " + desc_C + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  0_0_0_200"
					+ "    <ImgFile>   #### [索引 " + index + "]  描述: " + desc_D + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  0_200_0_200"
					+ "    <ImgFile>   #### [索引 " + index + "]  描述: " + desc_E + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  -100_-100_-100_-100"
					+ "    <ImgFile>   #### [索引 " + index + "]  描述: " + desc_F + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  0_-125_0_0"
					+ "    <ImgFile>   #### [索引 " + index + "]  描述: " + desc_G + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  0_-110_0_0"
					+ "    <ImgFile>   #### [索引 " + index + "]  描述: " + desc_H + "\n";

			return itemDesc;

		}

	}

	class Rename_Img_WithSize_Rule_21 extends Basic_Rule {

		ArrayList<String> fliterTypeList;
		ArrayList<File> mSrcFileImage; // 符合 过滤 条件的 当前目录的文件夹的集合
		boolean isPortLandNamed; // 是否 以 port 和 Name 添加前缀

		boolean isClearBlank; // 是否是把当前目录的文件中包含的空格去除 另外的操作逻辑

		Rename_Img_WithSize_Rule_21() {
			super("#", 21, 4); //
			fliterTypeList = new ArrayList<String>();
			mSrcFileImage = new ArrayList<File>();
			isPortLandNamed = false;
			isClearBlank = false;

		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			// TODO Auto-generated method stub
			boolean isEmptyTypeInput = false;
			for (int i = 0; i < inputParamList.size(); i++) {
				String inputParam = inputParamList.get(i);

				if (inputParam.contains("portland_true")) {
					isPortLandNamed = true;

				}

				if (inputParam.contains("clearblank_true")) {
					isClearBlank = true;

				}

				boolean isGifInput = false;
				if (inputParam.contains("gif")) {
					fliterTypeList.add(".gif");
					isGifInput = true;
				}

				boolean isJpgInput = false;
				if (inputParam.contains("jpg")) {
					fliterTypeList.add(".jpg");
					isJpgInput = true;
				}

				boolean isPngInput = false;
				if (inputParam.contains("png")) {
					fliterTypeList.add(".png");
					isPngInput = true;
				}

				boolean isWebpInput = false;
				if (inputParam.contains("webp")) {
					fliterTypeList.add(".webp");
					isWebpInput = true;
				}

				isEmptyTypeInput = isEmptyTypeInput || !(isGifInput || isWebpInput || isPngInput || isJpgInput);
				if (isEmptyTypeInput) {
					fliterTypeList.add(".webp");
					fliterTypeList.add(".jpg");
					fliterTypeList.add(".png");
					fliterTypeList.add(".gif");
				}

			}

			if (isClearBlank) {
				fliterTypeList.clear();
				fliterTypeList.add("*");
			}

			System.out.println("isPortLandNamed = " + isPortLandNamed + "  isEmptyTypeInput=" + isEmptyTypeInput);

			return super.initParamsWithInputList(inputParamList);
		}

		/*
		 * @Override boolean initParams4InputParam(String inputParam) { boolean
		 * isEmptyTypeInput = false;
		 *
		 * boolean isGifInput = false; if (inputParam.contains("gif")) {
		 * fliterTypeList.add(".gif"); isGifInput = true; }
		 *
		 * boolean isJpgInput = false; if (inputParam.contains("jpg")) {
		 * fliterTypeList.add(".jpg"); isJpgInput = true; }
		 *
		 * boolean isPngInput = false; if (inputParam.contains("png")) {
		 * fliterTypeList.add(".png"); isPngInput = true; }
		 *
		 * boolean isWebpInput = false; if (inputParam.contains("webp")) {
		 * fliterTypeList.add(".webp"); isWebpInput = true; }
		 *
		 * isEmptyTypeInput = !(isGifInput || isWebpInput || isPngInput || isJpgInput);
		 * if (isEmptyTypeInput) { fliterTypeList.add(".webp");
		 * fliterTypeList.add(".jpg"); fliterTypeList.add(".png");
		 * fliterTypeList.add(".gif"); }
		 *
		 * return super.initParams4InputParam(inputParam); }
		 */
		boolean checkInFlitterList(String fileName) {
			boolean result = false;

			for (int i = 0; i < fliterTypeList.size(); i++) {
				if (fileName.endsWith(fliterTypeList.get(i))) {
					result = true;
					break;
				}
			}
			return result;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			if (isClearBlank) {
				int blankIndex = 0;

				for (int i = 0; i < curRealFileList.size(); i++) {
					File fileItem = curRealFileList.get(i);
					String fileName = fileItem.getName();
					String fileName_lower = fileName.toLowerCase();
					if (fileItem.isDirectory()) {
						continue;
					}

					if (fileName.contains(" ")) {
						// 去除 名称 中的 空格
						String newName = fileName.replace(" ", "");
						tryReName(fileItem, newName);
						System.out.println(
								"去除空格文件[" + blankIndex + "] oldname[" + fileName + "]  newname[" + newName + "]");
						blankIndex++;
					}

				}

				System.out.println("RealFile Clear Blank  实体文件 清除空格 执行完成! ");

				return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);

			}

			for (int i = 0; i < curRealFileList.size(); i++) {
				File fileItem = curRealFileList.get(i);
				String fileName = fileItem.getName();
				String fileName_lower = fileName.toLowerCase();

				boolean isTypeInList = checkInFlitterList(fileName_lower);
				if (isTypeInList) {
					mSrcFileImage.add(fileItem);
				}
			}
			StringBuffer typtSb = new StringBuffer();
			for (int i = 0; i < mSrcFileImage.size(); i++) {
				typtSb.append(mSrcFileImage.get(i) + " ");
			}
			System.out.println("══════════开始执行 " + typtSb.toString() + "类型 1960x1280 宽x高操作 " + "══════════");

			for (int i = 0; i < mSrcFileImage.size(); i++) {
				File imageFile = mSrcFileImage.get(i);
				String fileName = imageFile.getName();
				ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
				int high = imageIcon.getIconHeight();
				int width = imageIcon.getIconWidth();

				boolean isPort = getRotateAngleForPhoto(imageFile.getAbsolutePath());

				if (!isPort) {
					int temp = high;
					high = width;
					width = temp;
				}

				// 当前文件的 宽高
				String str_width_x_high = calculateSizeStr(width, high);
				String newName = str_width_x_high + "_" + fileName;
				if (isPortLandNamed) {
					if (width > high) {
						newName = "Land_" + newName;
					} else {
						newName = "Port_" + newName;

					}
				}
				tryReName(imageFile, newName);
				System.out.println("File[" + i + "] =  SrcName【" + fileName + "】  TargetName【" + newName + "】");

			}

			System.out.println("Img Size Rename 执行完成! ");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		// 宽x高 1000x0900 1280x0720
		String calculateSizeStr(int widthValue, int highValue) {
			String sizeStr = "";
			int fixWidthValue = 0;
			int fixHighValue = 0;

			if (widthValue > 9999) { // 宽高最大只能 9999 大了 受不了
				fixWidthValue = 9999;
			} else {
				fixWidthValue = widthValue;
			}
			if (highValue > 9999) { // 宽高最大只能 9999 大了 受不了
				fixHighValue = 9999;
			} else {
				fixHighValue = highValue;
			}
			String widthStr = addForZeroStr(fixWidthValue);
			String highStr = addForZeroStr(fixHighValue);

			// fixWidthValue 和 fixHighValue 进行补零操作

			return widthStr + "x" + highStr;

		}

		String addForZeroStr(int value) {
			String valueStr = "";
			if (value > 9999) {
				valueStr = "9999";
			} else if (value >= 1000) {
				valueStr = (value + "").trim();
			} else if (value >= 100) {
				valueStr = ("0" + value).trim();
			} else if (value >= 10) {
				valueStr = ("00" + value).trim();
			} else if (value >= 0) {
				valueStr = ("000" + value).trim();
			}
			return valueStr;
		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_A = " 对当前目录下的图片文件 指定类型图片(参数输入)(png)(jpg)(webp)(gif)进行 进行以 宽x高 类似 1960x1280_原名 的操作";
			String desc_B = " 对当前目录下的图片文件 指定类型图片(参数输入)(png)(jpg)(webp)(gif)进行 进行以 宽x高 类似 Land_1960x1280_原名  Port_600x900 的操作";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "" + "    #### [索引 " + index
						+ "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_png" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_gif" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_webp" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png_gif_webp"
						+ "    #### [索引 " + index + "]  描述: " + desc_A + "\n";

				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png_gif_webp"
						+ "  portland_true " + "    #### [索引 " + index + "]  描述: " + desc_B + "\n";

				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "" + "  clearblank_true "
						+ "    #### [索引 " + index + "]  描述: " + "## 清除当前目录下的包含有空格的文件名称 去除空格" + "\n";

			} else {
				itemDesc = batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "" + "    #### [索引 " + index
						+ "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_png" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_gif" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_webp" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png" + "    #### [索引 "
						+ index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png_gif_webp"
						+ "    #### [索引 " + index + "]  描述: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png_gif_webp"
						+ "    #### [索引 " + index + "]  描述: " + desc_B + "\n";

				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "" + "  clearblank_true "
						+ "    #### [索引 " + index + "]  描述: " + "## 清除当前目录下的包含有空格的文件名称 去除空格" + "\n";

			}
			return itemDesc;

		}

	}

	class Land_Port_Classify_Rule_20 extends Basic_Rule {

		boolean isTimeStampDir = true; // Land_Port 新建的文件夹是否存有时间戳

		// false ---》 对 png 和 jpg 文件进行过滤
		boolean isGifClassfly = false; // true ---》 只对 gif 文件 进行 过滤

		ArrayList<File> mSrcFileImage; // Shell 目录下原始文件目录
		ArrayList<File> mLandImageFileList; // Shell/Land_Port_TimeStamp/Land/ 文件夹下的文件
		ArrayList<File> mPortImageFileList; // Shell/Land_Port_TimeStamp/Land/ 文件夹下的文件
		HashMap<File, File> src_target_FileMap; // src为 原始文件 target为目标文件 进行 copy时 会使用到

		Land_Port_Classify_Rule_20() {
			super("#", 20, 4); //
			isTimeStampDir = true;

			mSrcFileImage = new ArrayList<File>();
			mLandImageFileList = new ArrayList<File>();
			mPortImageFileList = new ArrayList<File>();

			src_target_FileMap = new HashMap<File, File>();
		}

		@Override
		boolean initParams4InputParam(String inputParam) {
			if (inputParam.contains("notime")) {
				isTimeStampDir = false;
			} else {
				isTimeStampDir = true;
			}

			if (inputParam.contains("gif")) {
				isGifClassfly = true;
			} else {
				isGifClassfly = false;
			}

			return super.initParams4InputParam(inputParam);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			for (int i = 0; i < curRealFileList.size(); i++) {
				File fileItem = curRealFileList.get(i);
				String fileName = fileItem.getName();
				String fileName_lower = fileName.toLowerCase();
				if (isGifClassfly) {
					if (fileName_lower.endsWith(".gif")) {
						mSrcFileImage.add(fileItem);
					}
				} else {
					if (fileName_lower.endsWith(".jpg") || fileName_lower.endsWith(".png")) {
						mSrcFileImage.add(fileItem);
					}
				}

			}

			for (int i = 0; i < mSrcFileImage.size(); i++) {
				File imageFile = mSrcFileImage.get(i);

				int high = 0;
				int width = 0;
				ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
				high = imageIcon.getIconHeight();
				width = imageIcon.getIconWidth();
				boolean isPort = getRotateAngleForPhoto(imageFile.getAbsolutePath());

				if (!isPort) {
					int temp = high;
					high = width;
					width = temp;
				}
				/*
				 * try { BufferedImage sourceImg = ImageIO.read(new
				 * FileInputStream(imageFile.getAbsolutePath())); high = sourceImg.getHeight();
				 * width = sourceImg.getWidth(); } catch (IOException e) { e.printStackTrace();
				 * }
				 */

				System.out.println("1_Rule20_Land_Port Index[" + i + "]  width[" + width + "]" + " high[" + high
						+ "]   high >= width[" + (high >= width) + "]  Path[" + imageFile.getAbsolutePath() + "]");
				if (high >= width) {
					mPortImageFileList.add(imageFile);
				} else {
					mLandImageFileList.add(imageFile);
				}
			}

			String PreDirName = "Land_Port_";
			if (isGifClassfly) {
				PreDirName += "Gif";
			} else {
				PreDirName += "Img";
			}

			String dir_1 = isTimeStampDir ? PreDirName + "_" + getTimeStamp() : PreDirName;
			String dir_1_Land_str = dir_1 + File.separator + "Land";
			String dir_1_Port_str = dir_1 + File.separator + "Port";
			File dir_Port = new File(curDirFile.getAbsoluteFile() + File.separator + dir_1_Port_str);
			File dir_Land = new File(curDirFile.getAbsoluteFile() + File.separator + dir_1_Land_str);
			System.out.println("══════════" + dir_Land.getAbsolutePath() + "  Land文件开始过滤执行" + "══════════");
			TryClassifyImage(mLandImageFileList, dir_Land);
			System.out.println("══════════" + dir_Port.getAbsolutePath() + "  Port文件开始过滤执行" + "══════════");
			TryClassifyImage(mPortImageFileList, dir_Port);

			System.out.println("zzfile_3" + Cur_Batch_End + "  " + curDirFile.getAbsoluteFile() + File.separator + dir_1
					+ "                        ####  过滤 Land  Port 文件夹已经生成！");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void TryClassifyImage(ArrayList<File> srcFileImageList, File targetDirFile) {
			if (!targetDirFile.exists()) {
				targetDirFile.mkdirs();
			}
			for (int i = 0; i < srcFileImageList.size(); i++) {
				File imgFile = srcFileImageList.get(i);
				String fileName = imgFile.getName();
				File targetFile = new File(targetDirFile.getAbsoluteFile() + File.separator + fileName);

				fileCopy(imgFile, targetFile);
				System.out.println("File[" + i + "] = " + "SrcFile【" + imgFile.getAbsolutePath() + "】" + " TargetFile【"
						+ targetFile.getAbsolutePath() + "】");
			}

		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_A = " 对当前目录下的图片文件(png)(jpg)进行 Land横屏 和 Port竖直 分类 并放置在新建Land_Port_Img_TimeStamp 文件夹中";
			String desc_B = " 对当前目录下的图片文件(png)(jpg)进行 Land横屏 和 Port竖直 分类 并放置在新建Land_Port_Img文件夹中(文件夹名称固定)";
			String desc_C = " 对当前目录下的图片文件(gif)进行 Land横屏 和 Port竖直 分类 并放置在新建Land_Port_Gif_TimeStamp 文件夹中";
			String desc_D = " 对当前目录下的图片文件(gif)进行 Land横屏 和 Port竖直 分类 并放置在新建Land_Port_Gif 文件夹中(文件夹名称固定)";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index + "" + "    #### [索引 " + index + "]  描述: "
						+ desc_A + "\n";
				itemDesc += batName.trim() + ".bat  " + type + "_" + index + "_notime" + "    #### [索引 " + index
						+ "]  描述: " + desc_B + "\n";
				itemDesc += batName.trim() + ".bat  " + type + "_" + index + "_gif" + "    #### [索引 " + index
						+ "]  描述: " + desc_C + "\n";
				itemDesc += batName.trim() + ".bat  " + type + "_" + index + "_gif_notime" + "    #### [索引 " + index
						+ "]  描述: " + desc_D + "\n";

			} else {
				itemDesc = batName.trim() + Cur_Batch_End + " " + type + "_" + index + "       ### [索引 " + index
						+ "]  描述:" + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + " " + type + "_" + index + "_notime" + "       ### [索引 "
						+ index + "]  描述:" + desc_B + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + " " + type + "_" + index + "_gif" + "       ### [索引 "
						+ index + "]  描述:" + desc_C + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + " " + type + "_" + index + "_gif_notime"
						+ "       ### [索引 " + index + "]  描述:" + desc_C;
			}

			return itemDesc;
		}

	}

	// 把当前 文件 使用 默认的 密码 752025 进行 压缩 成 7z 文件
	class ExpressTo7z_PassWord_Rule_19 extends Basic_Rule {

		ArrayList<String> inputTypeList;
		// zrule_apply_G2.bat #_14 jpg 把当前所有的jpg格式文件生成快捷方式到 jpg_时间戳 文件夹内

		// 可能从参数输入的 单一文件
		ArrayList<File> inputParamFileList;
		File z7exeFile;

		boolean isSearchAllFile2CurDirFlag = false;

		ExpressTo7z_PassWord_Rule_19() {
			super("#", 19, 3);
			inputTypeList = new ArrayList<String>();
			inputParamFileList = new ArrayList<File>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);
				if (strInput.equals(firstInputIndexStr)) {
					continue;
				}
				if (!strInput.startsWith(".")) {
					inputTypeList.add("." + strInput.trim());
				} else {
					inputTypeList.add(strInput.trim());
				}

				File tempFile = new File(curDirPath + File.separator + strInput);
				if (tempFile.exists() && !tempFile.isDirectory()) {
					inputParamFileList.add(tempFile);

				}
			}

			if (inputTypeList.size() == 0 && inputParamFileList.size() == 0) {
				isSearchAllFile2CurDirFlag = true;

			}

			z7exeFile = new File(Win_Lin_Mac_ZbinPath + File.separator + "7z" + curOS_ExeTYPE);
			System.out.println("Win_Lin_Mac_ZbinPath = " + Win_Lin_Mac_ZbinPath);
			if (!z7exeFile.exists() || z7exeFile.isDirectory()) {
				System.out.println(
						"当前 7z 压缩程序不存在! 请检查当前的 7z程序 一般位于 Desktop/zbin/win_zbin/  mac_zbin lin_zbin 中  z7exeFile = "
								+ z7exeFile.getAbsolutePath());
				return false;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");// 设置日期格式
//            SimpleDateFormat df_hms = new SimpleDateFormat("HHmmss");//设置日期格式
			Date curDate = new Date();
			String date = df.format(curDate);
//            String preHMS = df.format(df_hms);

			if (isSearchAllFile2CurDirFlag) {
				// 比那里所有 类型的 文件 并 重新命名
				try7zExpressOperation(fileTypeMap);

			} else {

				for (int i = 0; i < inputTypeList.size(); i++) {
					String type = inputTypeList.get(i);

					ArrayList<File> targetFileList = fileTypeMap.get(type);

					if (targetFileList == null || targetFileList.size() == 0) {
						System.out.println(" 当前路径 " + curDirPath + " 不存在类型 " + type + "的文件!");
						continue;
					}

					for (int j = 0; j < targetFileList.size(); j++) {
						File targetTypeFile = targetFileList.get(j);
						String originName = targetTypeFile.getName();
						String noPointName = getFileNameNoPoint(targetTypeFile);
//                        String mdName = getMD5Three(targetTypeFile.getAbsolutePath());
//                        String mdtype = getFileTypeWithPoint(targetTypeFile.getName());
//                        String new_md_Name = mdName+mdtype;
//                        tryReName(targetTypeFile,new_md_Name);

						String z7_command = z7exeFile.getAbsolutePath() + "  a -tzip  " + noPointName + ".7z" + " -p"
								+ strZ7DefaultKey_PSW_Rule19 + "  " + originName;
						System.out.println("执行\n");
						System.out.println(z7_command);
						execCMD(z7_command);
					}

				}

				for (int i = 0; i < inputParamFileList.size(); i++) {
					File targetTypeFile = inputParamFileList.get(i);
					String originName = targetTypeFile.getName();
					String noPointName = getFileNameNoPoint(targetTypeFile);

//                    tryReName(targetTypeFile,new_md_Name);

					String z7_command = z7exeFile.getAbsolutePath() + "  a -tzip  " + noPointName + ".7z" + " -p"
							+ strZ7DefaultKey_PSW_Rule19 + "  " + originName;

					System.out.println("执行\n");
					System.out.println(z7_command);

					execCMD(z7_command);

				}

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@SuppressWarnings("unchecked")
		boolean try7zExpressOperation(HashMap<String, ArrayList<File>> arrFileMap) {
			boolean executeFlag = false;
			Map.Entry<String, ArrayList<File>> entry;

			if (arrFileMap != null) {
				Iterator iterator = arrFileMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map的Value
					ArrayList<File> fileArr = entry.getValue(); // Map的Value

					for (int i = 0; i < fileArr.size(); i++) {
						File curFile = fileArr.get(i);
//                        String curFileName = curFile.getName();
//                        String mdName = getMD5Three(curFile.getAbsolutePath());
//                        String mdtype = getFileTypeWithPoint(curFile.getName());
//                        String new_md_Name = mdName+mdtype;
//                        tryReName(curFile,new_md_Name);

						String originName = curFile.getName();
						String noPointName = getFileNameNoPoint(curFile);

						String z7_command = z7exeFile.getAbsolutePath() + "  a -tzip  " + noPointName + ".7z" + " -p"
								+ strZ7DefaultKey_PSW_Rule19 + "  " + originName;

						System.out.println("执行\n");
						System.out.println(z7_command);
						execCMD(z7_command);
					}

				}
			}

			return executeFlag;
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + " #_19            ### 把当前文件夹下所有文件单独 压缩为 .7z 文件 文件名不变化   密码默认为 752025 !  "
					+ "\n" + Cur_Bat_Name
					+ " #_19  mp4          ###  把当前文件夹下 .mp4   单独 压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   " + "\n"
					+ Cur_Bat_Name + " #_19  .mp4         ### 把当前文件夹下 .mp4  单独 压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  .gif         ### 把当前文件夹下 .gif  单独 压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   " + "\n"
					+ Cur_Bat_Name + " #_19  png          ### 把当前文件夹下 .png  单独 压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  zip  7z      ### 把当前文件夹下  .zip  .7z   单独 压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   " + "\n"
					+ Cur_Bat_Name
					+ " #_19  .zip .7z     ###  把当前文件夹下  .zip  .7z   单独 压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   " + "\n"
					+ Cur_Bat_Name + " #_19  jpg          ###  把当前文件夹下  .jpg   单独压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  .jpg  .png  .webp .gif                          ### 把当前文件夹下  .jpg  .png  .webp .gif  单独压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  .mp4  .avi   .wmv .rmvb  .flv .3gp              ### 把当前文件夹下  .mp4  .avi   .wmv .rmvb  .flv .3gp  单独压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv     ### 把当前文件夹下  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv  单独压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  <指定文件A> <指定文件B>          ### 把当前文件夹下 指定文件名称 单独压缩为 .7z 文件 文件名不变化   密码默认为 752025 !   \"+ "

			;
		}

	}

	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
//     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

	// 以文件的md数字进行重命名 文件 改名字 改后缀 不影响 这个属性
	class MD_ReName_Rule_18 extends Basic_Rule {

		ArrayList<String> inputTypeList;
		// zrule_apply_G2.bat #_14 jpg 把当前所有的jpg格式文件生成快捷方式到 jpg_时间戳 文件夹内

		// 可能从参数输入的 单一文件
		ArrayList<File> inputParamFileList;

		boolean isSearchAllFile2CurDirFlag = false;

		MD_ReName_Rule_18() {
			super("#", 18, 3);
			inputTypeList = new ArrayList<String>();
			inputParamFileList = new ArrayList<File>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);
				if (strInput.equals(firstInputIndexStr)) {
					continue;
				}
				if (!strInput.startsWith(".")) {
					inputTypeList.add("." + strInput.trim());
				} else {
					inputTypeList.add(strInput.trim());
				}

				File tempFile = new File(curDirPath + File.separator + strInput);
				if (tempFile.exists() && !tempFile.isDirectory()) {
					inputParamFileList.add(tempFile);

				}
			}

			if (inputTypeList.size() == 0 && inputParamFileList.size() == 0) {
				isSearchAllFile2CurDirFlag = true;

			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");// 设置日期格式
//            SimpleDateFormat df_hms = new SimpleDateFormat("HHmmss");//设置日期格式
			Date curDate = new Date();
			String date = df.format(curDate);
//            String preHMS = df.format(df_hms);

			if (isSearchAllFile2CurDirFlag) {
				// 比那里所有 类型的 文件 并 重新命名
				tryReNameOperation(fileTypeMap);

			} else {

				for (int i = 0; i < inputTypeList.size(); i++) {
					String type = inputTypeList.get(i);

					ArrayList<File> targetFileList = fileTypeMap.get(type);

					if (targetFileList == null || targetFileList.size() == 0) {
						System.out.println(" 当前路径 " + curDirPath + " 不存在类型 " + type + "的文件!");
						continue;
					}

					for (int j = 0; j < targetFileList.size(); j++) {
						File targetTypeFile = targetFileList.get(j);
						String originName = targetTypeFile.getName();
						String mdName = getMD5Three(targetTypeFile.getAbsolutePath());
						String mdtype = getFileTypeWithPoint(targetTypeFile.getName());
						String new_md_Name = mdName + mdtype;
						tryReName(targetTypeFile, new_md_Name);

					}

				}

				for (int i = 0; i < inputParamFileList.size(); i++) {
					File targetTypeFile = inputParamFileList.get(i);
					String originName = targetTypeFile.getName();
					String mdName = getMD5Three(targetTypeFile.getAbsolutePath());
					String mdtype = getFileTypeWithPoint(targetTypeFile.getName());
					String new_md_Name = mdName + mdtype;
					tryReName(targetTypeFile, new_md_Name);
				}

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@SuppressWarnings("unchecked")
		boolean tryReNameOperation(HashMap<String, ArrayList<File>> arrFileMap) {
			boolean executeFlag = false;
			Map.Entry<String, ArrayList<File>> entry;

			if (arrFileMap != null) {
				Iterator iterator = arrFileMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map的Value
					ArrayList<File> fileArr = entry.getValue(); // Map的Value

					for (int i = 0; i < fileArr.size(); i++) {
						File curFile = fileArr.get(i);
//                        String curFileName = curFile.getName();
						String mdName = getMD5Three(curFile.getAbsolutePath());
						String mdtype = getFileTypeWithPoint(curFile.getName());
						String new_md_Name = mdName + mdtype;
						tryReName(curFile, new_md_Name);
					}

				}
			}

			return executeFlag;
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name
					+ " #_18            ### 把当前文件夹下 文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 32aeefa9924afb8be0da50976f1a2405.mp4 !  "
					+ "\n" + Cur_Bat_Name
					+ " #_18  mp4          ###  把当前文件夹下 .mp4 文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 !   " + "\n"
					+ Cur_Bat_Name + " #_18  .mp4         ### 把当前文件夹下 .mp4 文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_18  .gif         ### 把当前文件夹下 .gif 文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 32aeefa9924afb8be0da50976f1a2405.gif !   "
					+ "\n" + Cur_Bat_Name
					+ " #_18  png          ### 把当前文件夹下 .png 文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 !   " + "\n"
					+ Cur_Bat_Name
					+ " #_18  zip  7z      ### 把当前文件夹下  .zip  .7z  文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 !   32aeefa9924afb8be0da50976f1a2405.7z  "
					+ "\n" + Cur_Bat_Name
					+ " #_18  .zip .7z     ###  把当前文件夹下  .zip  .7z  文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 !   32aeefa9924afb8be0da50976f1a2405.7z "
					+ "\n" + Cur_Bat_Name
					+ " #_18  jpg          ###  把当前文件夹下  .jpg  文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 !" + "\n"
					+ Cur_Bat_Name
					+ " #_18  .jpg  .png  .webp .gif                          ### 把当前文件夹下  .jpg  .png  .webp .gif 文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 "
					+ "\n" + Cur_Bat_Name
					+ " #_18  .mp4  .avi   .wmv .rmvb  .flv .3gp              ### 把当前文件夹下  .mp4  .avi   .wmv .rmvb  .flv .3gp 文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 "
					+ "\n" + Cur_Bat_Name
					+ " #_18  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv     ### 把当前文件夹下  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv 文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 "
					+ "\n" + Cur_Bat_Name
					+ " #_18  <指定文件A> <指定文件B>          ### 把当前文件夹下 指定文件名称  文件全部改名为 MD5属性命名的文件 【(32)位16进制.type】 "

			;
		}

	}

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bi == null) {
			return "MD5_Null";
		}
		return bi.toString(16);
	}
	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
//     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

	class Make_ZRuleDir_Rule_17 extends Basic_Rule {
		ArrayList<String> dirNameList;

		Make_ZRuleDir_Rule_17() {
			super("#", 17, 4); //
			dirNameList = new ArrayList<String>();
			dirNameList.add("0_Temp_Dir");
			dirNameList.add("1_C_Install_Dir");
			dirNameList.add("1_Loveon_Place");
			dirNameList.add("2_WebSite_Download");
			dirNameList.add("3_BaiduNetdiskDownload");
			dirNameList.add("4_Software");
			dirNameList.add("5_WorkCodePlace");
			dirNameList.add("6_Jpg_Video");
			dirNameList.add("7_Txt_PDF_DOC_Book");
			dirNameList.add("8_Git_Dir");
			dirNameList.add("9_Version");
			dirNameList.add("10_Jira_Work");
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			if (curDirFile != null) {
				for (int i = 0; i < dirNameList.size(); i++) {
					String dirName = dirNameList.get(i);
					String dirAbsPath = curDirFile.getAbsolutePath() + File.separator + dirName;
					File newDirTemp = new File(dirAbsPath);
					newDirTemp.mkdirs();
					System.out.println("创建目录 " + newDirTemp.getAbsolutePath() + " 成功! ");
				}
				return null;
			} else {
				System.out.println("Make_ZRuleDir_Rule_17   当前获取到的Shell目录为空!   无法创建 Z规则文件夹!  ");
			}
			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_true = " 在当前目录下创建 固定的文件夹 ZDir -> 《 0_Loveon_Place 1_C_Install_Dir  2_WebSite_Download  3_BaiduNetdiskDownload  4_Software  5_WorkPlace   6_Jpg_Video  7_Txt_PDF_DOC_Book  0_Temp_Dir  9_Version  10_Jira_Work  ";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index + "       【 创建 Z规则目录】 [索引 " + index
						+ "]  描述: " + desc_true + "\n";

			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index + "       【 创建 Z规则目录】   [索引 " + index + "]  描述:"
						+ desc_true;
			}

			return itemDesc;
		}

	}

	class File_TimeName_Rule_16 extends Basic_Rule {

		// key = type value = 符合过滤文件规则的名称的文件的集合
		// HashMap<String, ArrayList<File>> arrFileMap;
		boolean keepOriginalName = false;
		int inputBeginIndex = 0;

		// true 1.jpg 2,jpg 3.png 4.png 依次命名
		// false 1.jpg 2,jpg 1.png 2.png 类型来命名
		boolean isOrder = false;

		File_TimeName_Rule_16() {
			super("#", 16, 3); //
		}

		@SuppressWarnings("unchecked")
		boolean tryReNameOperation(HashMap<String, ArrayList<File>> arrFileMap) {
			boolean executeFlag = false;
			Map.Entry<String, ArrayList<File>> entry;
			int fileOrderIndex = 0;

			if (arrFileMap != null) {
				Iterator iterator = arrFileMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map的Value
					ArrayList<File> fileArr = entry.getValue(); // Map的Value

					for (int i = 0; i < fileArr.size(); i++) {
						fileOrderIndex++;
						int index = i + 1;
						String newNamePre = index + "_" + getTimeStamp();
						File curFile = fileArr.get(i);
						String curFileName = curFile.getName();
						String newName = "";
						if (keepOriginalName) {
							if (isOrder) { // 按顺序依次 不按 type了 一直走
								newName = fileOrderIndex + "_" + curFileName + "_" + getTimeStampLong() + typeStr;
							} else {
								newName = newNamePre + curFileName + "_" + getTimeStampLong() + typeStr;
							}
						} else {
							// 如果不保留名称 那么没有类型的文件 将只有 序号 没有类型
							if ("unknow".equals(typeStr)) {
								newName = index + "_" + getTimeStamp() + "_" + getTimeStampLong();
							} else {
								if (isOrder) { // 按顺序依次 不按 type了 一直走
									newName = fileOrderIndex + "_" + getTimeStampLong() + typeStr;
								} else {
									newName = index + "_" + getTimeStampLong() + typeStr;
								}

							}
						}
						if (tryReName(curFile, newName)) {
							executeFlag = true;
						}
					}

				}
			}

			return executeFlag;
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			if (tryReNameOperation(fileTypeMap)) {
				return curFixedFileList;
			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_true = "  (不保留当前名称 按类型重命名当前目录下的文件) 文件命名格式为:    依据类型 序号_时间戳.类型   1_201841094.jpg 2_201841094.jpg 3_2018413131.jpg 1_201804021145.png";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index
						+ "       【 index_timestamp.type 序号_时间戳.类型 命名】针对所有文件  [索引 " + index + "]  描述: " + desc_true
						+ "\n";

			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index
						+ "       【 index_timestamp.type 序号_时间戳.类型 命名】针对所有文件   [索引 " + index + "]  描述:" + desc_true;
			}

			return itemDesc;
		}

	}

	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
	// // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

	class Webp_To_Jpg_Gif_Rule_15 extends Basic_Rule {
		ArrayList<File> webpFileList;
		ArrayList<File> gif_webpFileList;
		String G2_webp2gif_exe_path = "";

		Webp_To_Jpg_Gif_Rule_15() {
			super("#", 15, 4);
			webpFileList = new ArrayList<File>();
			gif_webpFileList = new ArrayList<File>();
			PushFile2JDKBIN();
			if (CUR_OS_TYPE == OS_TYPE.Windows) {
				G2_webp2gif_exe_path = zbinPath + File.separator + "G2_webp2gif.exe";
			}

		}

		void PushFile2JDKBIN() {
			if ("".equals(JDK_BIN_PATH)) {
				return;
			}
			String webpLibraryFilePath = null;
			String G2_LibraryPath = null;
			// G2_File_Path
			if (CUR_OS_TYPE == OS_TYPE.Windows) {
				webpLibraryFilePath = JDK_BIN_PATH + File.separator + "webp-imageio.dll";
				G2_LibraryPath = G2_File_Path + File.separator + "webp-imageio.dll";
				Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "win_zbin";
			} else if (CUR_OS_TYPE == OS_TYPE.MacOS) {
				webpLibraryFilePath = JDK_BIN_PATH + File.separator + "libwebp-imageio.dylib";
				G2_LibraryPath = G2_File_Path + File.separator + "libwebp-imageio.dylib";
				Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "mac_zbin";
			} else if (CUR_OS_TYPE == OS_TYPE.Linux) {
				webpLibraryFilePath = JDK_BIN_PATH + File.separator + "libwebp-imageio.so";
				G2_LibraryPath = G2_File_Path + File.separator + "libwebp-imageio.so";
				Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "lin_zbin";
			}

			File webpLibraryFile = new File(webpLibraryFilePath);
			File G2_LibraryFile = new File(G2_LibraryPath);
			if (!G2_LibraryFile.exists()) {
				System.out.println("本地 库文件 " + G2_LibraryPath + "不存在 请重新填充 zbin/G2/.so .dll 文件!");
				return;
			}
			if (webpLibraryFile.exists() && webpLibraryFile.length() > 100) {
				System.out.println("当前 库文件 " + webpLibraryFilePath + "已经加载到 jre/bin 路径下!");
				return;
			}
			fileCopy(G2_LibraryFile, webpLibraryFile);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			ArrayList<File> webpFile = subFileTypeMap.get(".webp");
			if (webpFile == null) {
				System.out.println("当前文件夹中不存在 webp文件的格式");
				return null;
			}
			webpFileList.addAll(webpFile);
			String stampStr = getTimeStamp();
			for (int i = 0; i < webpFileList.size(); i++) {

				File webpFileItem = webpFileList.get(i);
				System.out.println("当前 webp索引[" + i + "] = " + webpFileItem.getAbsolutePath());
				String newFilePath = webpFileItem.getAbsolutePath().replace(".webp", "_" + stampStr + ".jpg");
				File jpgFileItem = new File(newFilePath);
				revertWebp2Jpg(webpFileItem, jpgFileItem);

			}

			for (int i = 0; i < gif_webpFileList.size(); i++) {
				File gif_webpFileItem = gif_webpFileList.get(i);
				String originName = gif_webpFileItem.getName();
				String curParentPath = gif_webpFileItem.getParent();
				boolean needRename = false;
				String absPath = gif_webpFileItem.getAbsolutePath();
				String gif_absPath = absPath.replace(".webp", ".gif");
				File gif_absPath_File = new File(gif_absPath);
				String fileName = gif_webpFileItem.getName();

				// 如果 加载后的gif 存在 那么 需要 添加时间戳 以免覆盖
//             if(gif_absPath_File.exists()){

				fileName = fileName.replace(".webp", "_" + stampStr + ".webp");
				tryReName(gif_webpFileItem, fileName);
				needRename = true;
//              }

				System.out.println("动图 索引[" + i + "] = " + fileName);
				System.out.println("执行动图转为 gif的命令! ");
				if ("".equals(G2_webp2gif_exe_path)) {
					System.out.println("当前 webp2gif 为空 请检查!  可能当前系统 Linux MacOS 还没实现该功能!");
					return null;
				}
				String command = G2_webp2gif_exe_path + " " + fileName;
				execCMD(command);
				if (needRename) {
					tryReName(new File(curParentPath + File.separator + fileName), originName);
				}

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void revertWebp2Jpg(File webpFile, File jpgFile) {
			// webp 动态图 会报错 Decode returned code VP8_STATUS_UNSUPPORTED_FEATURE
			// Obtain a WebP ImageReader instance
			ImageReader reader = ImageIO.getImageReadersByMIMEType("image/webp").next();
			System.out.println("如果报错,将 webp-imageio.dll 等三个文件放入 win_zbin 中继续执行 !! ");
			// Configure decoding parameters
			WebPReadParam readParam = new WebPReadParam();
			readParam.setBypassFiltering(true);
			BufferedImage image = null;
			try {

				// Configure the input on the ImageReader
				reader.setInput(new FileImageInputStream(webpFile));

				// Decode the image
				image = reader.read(0, readParam);
			} catch (IOException e) {

				System.out.println("解析失败   可能是webp动图!   放入 ArrayList<File> gifList 列表中!");
				gif_webpFileList.add(webpFile);
			}

			try {
				ImageIO.write(image, "png", jpgFile);
			} catch (Exception e) {
				System.out.println("写入文件 " + jpgFile.getAbsolutePath() + " 失败");
			}

		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + " webp_15            ### 对当前目录下的 webp文件进行转换  静态图-> jpg   动态图-> gif \n";
		}

	}

	// 创建快捷方式
	static boolean makeShellLink(File targetFile, File iconFile) {
		boolean isOK = false;
		String targetFilePath = targetFile.getAbsolutePath();
		JShellLink link = new JShellLink();
		if (!iconFile.exists()) {

			/*
			 * try { iconFile.createNewFile(); } catch (IOException e) {
			 * e.printStackTrace(); }
			 */
		}

		try {
			String parentAbsPath = iconFile.getParentFile().getAbsolutePath();
			link.setFolder(parentAbsPath);
			String iconName = iconFile.getName();
			link.setName(iconName);
			link.setPath(targetFilePath);
			link.save();
			if (isKuaiJieIcon(iconFile)) {
				isOK = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isOK;

	}

	static String getTargetFilePath(File iconFile) {
		String targetFilePath = "";
		if (!isKuaiJieIcon(iconFile)) {
			return null; // 不是 快捷方式 那么 返回 "" 或者 null
		}
		String parentAbsPath = iconFile.getParentFile().getAbsolutePath();
		String fileName = iconFile.getName();
		JShellLink linkFile = new JShellLink(parentAbsPath, fileName);
		linkFile.load();
		targetFilePath = linkFile.getPath();
		return targetFilePath;
	}

	static boolean isKuaiJieIcon(File kuaijieFile) {
		String absPath = kuaijieFile.getAbsolutePath();
		String parentAbsPath = kuaijieFile.getParentFile().getAbsolutePath();
		String fileName = kuaijieFile.getName();
		JShellLink linkFile = new JShellLink(parentAbsPath, fileName);
		linkFile.load();
		String linkedPath = linkFile.getPath();

		if (absPath.equals(linkedPath)) {
			return false;
		}
		return true;
	}

	class CreateIconFile_KuaiJieFangShi_Rule_14 extends Basic_Rule {

		ArrayList<String> inputTypeList;
		// zrule_apply_G2.bat #_14 jpg 把当前所有的jpg格式文件生成快捷方式到 jpg_时间戳 文件夹内

		CreateIconFile_KuaiJieFangShi_Rule_14() {
			super("#", 14, 3);
			inputTypeList = new ArrayList<String>();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);
				if (strInput.equals(firstInputIndexStr)) {
					continue;
				}
				if (!strInput.startsWith(".")) {
					inputTypeList.add("." + strInput.trim());
				} else {
					inputTypeList.add(strInput.trim());
				}
			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");// 设置日期格式
//            SimpleDateFormat df_hms = new SimpleDateFormat("HHmmss");//设置日期格式

			Date curDate = new Date();
			String date = df.format(curDate);
//            String preHMS = df.format(df_hms);
			for (int i = 0; i < inputTypeList.size(); i++) {
				String type = inputTypeList.get(i);

				ArrayList<File> targetFileList = fileTypeMap.get(type);

				if (targetFileList == null || targetFileList.size() == 0) {
					System.out.println(" 当前路径 " + curDirPath + " 不存在类型 " + type + "的文件!");
					continue;
				}

				int fileCount = targetFileList.size();
				// 创建文件夹 大小
//                String dirName = preHMS+"_"+type.replace(".","").toUpperCase().trim()+"_"+date;
				String dirName = date + "_" + type.replace(".", "").toUpperCase().trim() + "[" + fileCount + "]";
				// MP4_4232414141
				File iconDirFile = new File(curDirPath + File.separator + dirName);
				iconDirFile.mkdirs();

				System.out.println("════════" + "文件类型" + type + "创建快捷方式 Begin" + "════════");
				for (int j = 0; j < targetFileList.size(); j++) {
					File targetTypeFile = targetFileList.get(j);
					String targetName = targetTypeFile.getName();
					int IconIndex = j + 1;
					String targetOrderName = IconIndex + "_" + targetName;
					if (tryReName(targetTypeFile, targetOrderName)) {
						targetTypeFile = new File(
								targetTypeFile.getParentFile().getAbsolutePath() + File.separator + targetOrderName);
					}

					String iconName = IconIndex + "_" + targetName;
					File iconFile = new File(iconDirFile.getAbsolutePath() + File.separator + iconName);
					if (makeShellLink(targetTypeFile, iconFile)) {

						System.out.println("Index[" + IconIndex + "]目标文件:" + targetTypeFile.getAbsolutePath()
								+ " 创建快捷方式成功:" + "./" + dirName + File.separator + iconName);
					} else {
						System.out.println("Index[" + IconIndex + "]目标文件:" + targetTypeFile.getAbsolutePath()
								+ " 创建快捷方式失败:" + "./" + dirName + File.separator + iconName);
					}
				}
				System.out.println("════════" + "文件类型" + type + "创建快捷方式 End" + "════════");

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name
					+ " #_14  mp4          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的mp4文件 并在当前目录生成 MP4_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .mp4         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的mp4文件 并在当前目录生成 MP4_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .gif         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的gif文件 并在当前目录生成 GIF_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  png          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的png文件 并在当前目录生成 PNG_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  zip  7z      ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .zip .7z     ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  jpg          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的JPG文件 并在当前目录生成 JPG_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .jpg  .png  .webp .gif                          ### 生成图片格式文件集合  PNG_时间戳  JPG_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .mp4  .avi   .wmv .rmvb  .flv .3gp              ### 生成视频格式文件集合    源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的JPG文件 并在当前目录生成 MP4_20200522_154600 字样的文件夹 \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv     ### 生成 视频 + 图片 格式文件集合  源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的视频文件 并在当前目录生成 JPG_20200522_154600 MP4_20200522_154600 字样的文件夹 \n"

			;
		}
	}

	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
	// // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

	class CalMP4_DIR_HTML_Rule_13 extends Basic_Rule {
		String Type_DIR_NAME = "";
		ArrayList<File> inputDirList;
		ArrayList<File> htmlModelList;
		// G2_Rule13_mp4_3x5.html
		File mp4_3x5_File;

		// G2_Rule13_mp4__3d.html
		File mp4_3d_File;

		// G2_Rule13_mp4_2x2.html
		File mp4_2x2_File;
		// G2_Rule13_mp4_3x3.html
		File mp4_3x3_File;

		String newReplaceName; // G2_Rule13_mp4_3x5 期中 G2_Rule13 替换的名称

		CalMP4_DIR_HTML_Rule_13() {
			super("#", 13, 4);
			inputDirList = new ArrayList<File>();
			htmlModelList = new ArrayList<File>();
			mp4_3x5_File = new File(zbinPath + File.separator + "G2_Rule13_mp4_3x5.html");
			mp4_3d_File = new File(zbinPath + File.separator + "G2_Rule13_mp4__3d.html");
			mp4_2x2_File = new File(zbinPath + File.separator + "G2_Rule13_mp4_2x2.html");
			mp4_3x3_File = new File(zbinPath + File.separator + "G2_Rule13_mp4_3x3.html");

			newReplaceName = "";
			htmlModelList.add(mp4_3x5_File);
			htmlModelList.add(mp4_3d_File);
			htmlModelList.add(mp4_2x2_File);
			htmlModelList.add(mp4_3x3_File);

		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				// 检查是否有 paramItem 名称的文件夹
				System.out.println("paramItem = " + paramItem);
				File curDir = checkType2Dir(curDirFile, paramItem);
				if (curDir != null && curDir.isDirectory()) {
					inputDirList.add(curDir); //
				}

			}

			for (int i = 0; i < inputDirList.size(); i++) {
				String dirName = inputDirList.get(i).getName();
				newReplaceName = newReplaceName + "_" + dirName;
			}
			while (newReplaceName.endsWith("_")) {
				newReplaceName = newReplaceName.substring(0, newReplaceName.length() - 1);
			}

			while (newReplaceName.startsWith("_")) {
				newReplaceName = newReplaceName.substring(1, newReplaceName.length());
			}

			if ("".equals(newReplaceName)) {
				newReplaceName = "" + curDirFile.getName();

			}

			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		boolean initParams4InputParam(String inputParam) {
			if (!(inputParam.contains("jpg") || inputParam.contains("mp4") || inputParam.contains("gif"))) {
				System.out.println("当前输入参数不包含 jpg || mp4 || gif  请重新输入");
				return false;
			}

			String[] params = inputParam.split("_");
			if (params == null) {
				System.out.println("当前输入参数不包含 jpg || mp4 || gif  请重新输入");
				return false;
			}
			String TypeDir = params[params.length - 1];

			if (!(("jpg").equals(TypeDir) || ("mp4").equals(TypeDir) || ("gif").equals(TypeDir))) {
				System.out.println("当前输入参数不包含 jpg || mp4 || gif  请重新输入");
				return false;
			}
			Type_DIR_NAME = TypeDir.toLowerCase().trim();
			return super.initParams4InputParam(inputParam);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name
					+ "  #_13_mp4    ### 动态计算当前文件夹中所有子文件中的mp4文件夹中的 mp4文件个数(孙文件夹名称必须是mp4)  并在当前shell目录生成html文件 \n"
					+ Cur_Bat_Name
					+ "  #_13_jpg    ### 动态计算当前文件夹中所有子文件中的jpg文件夹中的 jpg文件个数(孙文件夹名称必须是jpg) 并在当前目录生成html文件\n"
					+ Cur_Bat_Name
					+ "  #_13_gif    ### 动态计算当前文件夹中所有子文件中的gif文件夹中的 gif文件个数(孙文件夹名称必须是gif) 并在当前目录生成html文件\n"
					+ Cur_Bat_Name
					+ "  #_13_mp4  <单个子件夹参数>  ### 同没有参数(但shell路径不同) 动态计算当前文件夹中所有子文件中的mp4文件夹中的 mp4文件个数 并在当前目录生成html文件\n"
					+ Cur_Bat_Name
					+ "  #_13_jpg  <单个子件夹参数>  ### 同没有参数(但shell路径不同) 动态计算当前文件夹中所有子文件中的jpg文件夹中的 jpg文件个数 并在当前目录生成html文件\n"
					+ Cur_Bat_Name
					+ "  #_13_gif  <单个子件夹参数>  ### 同没有参数(但shell路径不同) 动态计算当前文件夹中所有子文件中的gif文件夹中的 gif文件个数 并在当前目录生成html文件\n"
					+ Cur_Bat_Name
					+ "  #_13_mp4  <子文件夹参数1> <子文件夹参数2> ....<子文件夹参数N>  ### 多输入参数 动态计算给定路径下的文件夹中所有子文件中的mp4文件夹中的 gif文件个数 并在当前目录生成html文件\n"
					+ Cur_Bat_Name
					+ "  #_13_jpg <子文件夹参数1> <子文件夹参数2> ....<子文件夹参数N>   ### 同没有参数(但shell路径不同) 动态计算当前文件夹中所有子文件中的mp4文件夹中的 gif文件个数 并在当前目录生成html文件\n";
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			ArrayList<File> operaDirList = new ArrayList<File>();
			boolean isMultiDirInput = false;
			String curBasePath = "";

			if (inputDirList.size() == 0) { // 如果没有输入Dir参数 那么 就在当前目录操作
				operaDirList.addAll(curDirList);
				curBasePath = curDirFile.getAbsolutePath();
			} else if (inputDirList.size() == 1) { // 如果只有一个参数 那么operaDirList 放入 当前参数的子目录
				File curInputDir = inputDirList.get(0);
				curBasePath = curInputDir.getAbsolutePath();
				operaDirList.addAll(getCurrentSubDirFile(curInputDir));
				System.out.println(" curInputDir = " + curInputDir);
			} else {
				for (int i = 0; i < inputDirList.size(); i++) {
					operaDirList.addAll(getCurrentSubDirFile(inputDirList.get(i)));
				}
				isMultiDirInput = true;
				curBasePath = curDirFile.getAbsolutePath();
			}
			System.out.println(" inputDirList.size = " + inputDirList.size());
			System.out.println(" curBasePath = " + curBasePath);

//// hoderplace -begin
//zukgitPlaceHolderArrayDefine
//var objectArr = [ zukgitPlaceHolderArrayAdd ];
//// hoderplace -end

			StringBuilder defineArrWord = new StringBuilder();
			StringBuilder defineAdd = new StringBuilder();

			// 如果有参数 那么 当前的 curDirList
			int index = 0;
			for (int i = 0; i < operaDirList.size(); i++) {
				File cur1DirFileItem = operaDirList.get(i);
				File mTypeDirFile = checkType2Dir(cur1DirFileItem, Type_DIR_NAME);
				int typeFileNum = 0;
				if (mTypeDirFile != null && 0 != (typeFileNum = checkType3File(mTypeDirFile, Type_DIR_NAME))) {
					// 检测到了 对应的 type 文件
					// 1.获取当前 第一层目录名称
					String dir1DirName = cur1DirFileItem.getName();
					// 2. 获取对应命令的文件
					String dir2TypeDieName = dir1DirName + File.separator + Type_DIR_NAME;
					dir2TypeDieName = dir2TypeDieName.replace("\\", "/");
					// 3. typeFileNum 对应的当前 孙子目录中的文件的个数
					int length = typeFileNum;
					String people = "person" + index;

//                    person0 = { index:0 , path:"./7001/mp4/",length:22,};
//                    person0 = { index:0 , path:"./7001\mp4,length:22,};
					String defineItem = "";
					if (!isMultiDirInput) { // 如果是单独的 文件
						defineItem = people + " = { index:" + index + " , path:\"./" + dir2TypeDieName + "/\",length:"
								+ length + ",};\n";
					} else { // 如果是两个 量入的文件 那么 path就要加入对应的 当前目录的路径
						String targetDirName = calculBeginDir(mTypeDirFile.getAbsolutePath());
						if (!"".equals(targetDirName)) {
							targetDirName = targetDirName + "/";
						}
						defineItem = people + " = { index:" + index + " , path:\"./" + targetDirName + dir2TypeDieName
								+ "/\",length:" + length + ",};\n";
					}
					defineArrWord.append(defineItem);
					defineAdd.append(people + ",");
					index++;
				}

			}

			// 定义people
			String defineArrWordStr = defineArrWord.toString().trim();
			while (defineArrWordStr.endsWith(",")) {
				defineArrWordStr = defineArrWordStr.substring(0, defineArrWordStr.length() - 1);
			}

			// 把 people 编为 数组 array
			String defineAddStr = defineAdd.toString();

			for (int i = 0; i < htmlModelList.size(); i++) {
				// 获取 html文件的内容
				File htmlModelFile = htmlModelList.get(i);

				// G2_Rule13_mp4_3x5
				String html_old_name = htmlModelFile.getName();
				String readHtmlContent = ReadFileContent(htmlModelFile);

//            String readHtmlContent = "";
				System.out.println("defineAddStr  = " + defineAddStr);
				System.out.println("defineArrWordStr  = " + defineArrWordStr);
				readHtmlContent = readHtmlContent.replace("zukgitPlaceHolderArrayAdd", defineAddStr);
				readHtmlContent = readHtmlContent.replace("zukgitPlaceHolderArrayDefine", defineArrWordStr);

				// 把文件写入 对应的目录
				// 当前 文件名称
				String newName = html_old_name.replace("G2_Rule13", newReplaceName);

				File curHtmlTargetFile = new File(curBasePath + File.separator + newName);

				// 写入哪个文件夹

				// 1. 无参数 写入当前的 shell 路径下
				// 2. 一个参数的情况

				writeContentToFile(curHtmlTargetFile, readHtmlContent);
				System.out.println("输出文件:" + curHtmlTargetFile.getAbsolutePath());
			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		String calculBeginDir(String mediaPath) {
			String inputDirStr = "";
			for (int i = 0; i < inputDirList.size(); i++) {
				File inputDir = inputDirList.get(i);
				String inputDirPath = inputDir.getAbsolutePath();
				if (mediaPath.startsWith(inputDirPath)) {
					inputDirStr = inputDir.getName();
					break;
				}

			}

			return inputDirStr;

		}

		// 检测当前的 dirFile 目录中是否存在 第二个参数名称相同的文件名
		File checkType2Dir(File dirFile, String typeName) {
			String dirNameA = typeName;
			while (dirNameA.endsWith("\\")) {
				dirNameA = dirNameA.substring(0, dirNameA.length() - 1);
			}
			File typeDirFile = null;
			File[] fileList = dirFile.listFiles();
			if (fileList == null) {
				return typeDirFile;
			}
			for (int i = 0; i < fileList.length; i++) {
				File dirFileItem = fileList[i];
				String dirName = dirFileItem.getName();
				if (dirNameA.equals(dirName)) {
					typeDirFile = dirFileItem;
					break;
				}
			}
			return typeDirFile;
		}

		// 检查当前目录下是否存在对应类型typeName 的具体的文件 的文件名称的个数
		int checkType3File(File dirFile, String typeName) {
			int existNum = 0;

			File[] fileList = dirFile.listFiles();
			if (fileList == null) {
				return existNum;
			}
			for (int i = 0; i < fileList.length; i++) {
				File dirFileItem = fileList[i];
				String dirName = dirFileItem.getName();
				// 当前文件不是文件夹 并且当前文件名称的后缀 是 .【type】 例如 .gif .jpg .mp4
				if (!dirFileItem.isDirectory() && dirFileItem.getName().endsWith("." + typeName)) {
					existNum++;
				}

			}
			return existNum;
		}

	}
	// // zrule_apply_G2.bat 12_mp4 <目标文件夹目录> ### 把当前目录mp4文件生成 html 播放文件
	// // zrule_apply_G2.bat 12_jpg <目标文件夹目录> ### 把没有类型的文件名称修改为 jpg格式名称
	// // zrule_apply_G2.bat 12_gif <目标文件夹目录> ### 把没有类型的文件名称修改为 jpg格式名称

	class CalCulMediaHtml_Rule_12 extends Basic_Rule {

		ArrayList<File> operaDirFileList; // 当前从参数获得的目录文件集合
		int operaType; // 0-unknow 1--mp4 2--jpg 3--gif // 4--allmp4 把当前所有的mp4文件转为一个数组 放入到html页面

		ArrayList<File> allMp4FileList; // 当前目录的所有的mp4文件的列表 operaType=4--allmp4 适用

		ArrayList<File> mp4AllHtmlTemplate_FileList;

		ArrayList<File> mp4HtmlTemplate_FileList;
		ArrayList<File> jpgHtmlTemplate_FileList;
		ArrayList<File> gifHtmlTemplate_FileList;

		File Mp4_All_2x2_Html_TemplateFile;
		File Mp4_All_2x3_Html_TemplateFile;
		File Mp4_All_2x4_Html_TemplateFile;
		File Mp4_All_2x5_Html_TemplateFile;

		File Mp4_All_3x2_Html_TemplateFile;
		File Mp4_All_3x3_Html_TemplateFile;
		File Mp4_All_3x4_Html_TemplateFile;
		File Mp4_All_3x5_Html_TemplateFile;

		File Mp4_All_4x2_Html_TemplateFile;
		File Mp4_All_4x3_Html_TemplateFile;
		File Mp4_All_4x4_Html_TemplateFile;
		File Mp4_All_4x5_Html_TemplateFile;

		File Mp4_All_5x2_Html_TemplateFile;
		File Mp4_All_5x3_Html_TemplateFile;
		File Mp4_All_5x4_Html_TemplateFile;
		File Mp4_All_5x5_Html_TemplateFile;

		File Mp4_2x2_Html_TemplateFile;
		File Mp4_3x3_Html_TemplateFile;
		File Mp4_3x5_Html_TemplateFile;
		File Mp4_3d_Html_TemplateFile;
		File Mp4_2x2_Html_SameTempFile;
		File Mp4_3x3_Html_SameTempFile;
		File Mp4_3x5_Html_SameTempFile;

		File Gif_3d_Html_TemplateFile;
		File Gif_1x1_Html_TemplateFile_Left;
		File Gif_1x1_Html_TemplateFile_Right;
		File Gif_2x2_Html_TemplateFile;
		File Gif_2x2_Html_TemplateFile_Left;
		File Gif_2x2_Html_TemplateFile_Right;
		File Gif_2x2_Html_SameTempFile;
		File Gif_3x3_Html_TemplateFile;
		File Gif_3x3_Html_TemplateFile_Left;
		File Gif_3x3_Html_TemplateFile_Right;
		File Gif_3x3_Html_SameTempFile;
		File Gif_3x5_Html_TemplateFile;
		File Gif_3x5_Html_SameTempFile;
		File Gif_2x4_Html_TemplateFile_Left;
		File Gif_2x4_Html_TemplateFile_Right;
		File Gif_4x3_Html_TemplateFile_Left;
		File Gif_4x3_Html_TemplateFile_Right;
		File Gif_4x4_Html_TemplateFile_Left;
		File Gif_4x4_Html_TemplateFile_Right;
		File Gif_3x5_Html_TemplateFile_Left;
		File Gif_3x5_Html_TemplateFile_Right;
		File Gif_4x5_Html_TemplateFile_Left;
		File Gif_4x5_Html_TemplateFile_Right;

		File Jpg_3d_Html_TemplateFile;
		File Jpg_4x3_Html_TemplateFile_Left;
		File Jpg_4x3_Html_TemplateFile_Right;
		File Jpg_1x1_Html_TemplateFile_Left;
		File Jpg_1x1_Html_TemplateFile_Right;
		File Jpg_2x2_Html_TemplateFile;
		File Jpg_2x2_Html_TemplateFile_Left;
		File Jpg_2x2_Html_TemplateFile_Right;
		File Jpg_2x2_Html_SameTempFile;
		File Jpg_3x3_Html_TemplateFile;
		File Jpg_3x3_Html_TemplateFile_Left;
		File Jpg_3x3_Html_TemplateFile_Right;
		File Jpg_3x3_Html_SameTempFile;
		File Jpg_3x5_Html_TemplateFile;
		File Jpg_3x5_Html_SameTempFile;
		File Jpg_2x4_Html_TemplateFile_Left;
		File Jpg_2x4_Html_TemplateFile_Right;
		File Jpg_4x4_Html_TemplateFile_Left;
		File Jpg_4x4_Html_TemplateFile_Right;
		File Jpg_3x5_Html_TemplateFile_Left;
		File Jpg_3x5_Html_TemplateFile_Right;
		File Jpg_4x5_Html_TemplateFile_Left;
		File Jpg_4x5_Html_TemplateFile_Right;

		CalCulMediaHtml_Rule_12() {

			super("#", 12, 5);
			operaType = 0;
			operaDirFileList = new ArrayList<File>();
			mp4AllHtmlTemplate_FileList = new ArrayList<File>();
			mp4HtmlTemplate_FileList = new ArrayList<File>();
			jpgHtmlTemplate_FileList = new ArrayList<File>();
			gifHtmlTemplate_FileList = new ArrayList<File>();

			Mp4_All_2x2_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_2x2.html");
			Mp4_All_2x3_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_2x3.html");
			Mp4_All_2x4_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_2x4.html");
			Mp4_All_2x5_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_2x5.html");

			Mp4_All_3x2_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_3x2.html");
			Mp4_All_3x3_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_3x3.html");
			Mp4_All_3x4_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_3x4.html");
			Mp4_All_3x5_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_3x5.html");

			Mp4_All_4x2_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_4x2.html");
			Mp4_All_4x3_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_4x3.html");
			Mp4_All_4x4_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_4x4.html");
			Mp4_All_4x5_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_4x5.html");

			Mp4_All_5x2_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_5x2.html");
			Mp4_All_5x3_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_5x3.html");
			Mp4_All_5x4_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_5x4.html");
			Mp4_All_5x5_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_all_5x5.html");

			mp4AllHtmlTemplate_FileList.add(Mp4_All_2x2_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_2x3_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_2x4_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_2x5_Html_TemplateFile);

			mp4AllHtmlTemplate_FileList.add(Mp4_All_3x2_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_3x3_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_3x4_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_3x5_Html_TemplateFile);

			mp4AllHtmlTemplate_FileList.add(Mp4_All_4x2_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_4x3_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_4x4_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_4x5_Html_TemplateFile);

			mp4AllHtmlTemplate_FileList.add(Mp4_All_5x2_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_5x3_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_5x4_Html_TemplateFile);
			mp4AllHtmlTemplate_FileList.add(Mp4_All_5x5_Html_TemplateFile);

			Mp4_2x2_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_2x2.html");
			Mp4_3x3_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_3x3.html");
			Mp4_3x5_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_3x5.html");
			Mp4_2x2_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_2x2_same.html");
			Mp4_3x3_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_3x3_same.html");
			Mp4_3x5_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_mp4_3x5_same.html");
			Mp4_3d_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_mp4__3d.html");
			mp4HtmlTemplate_FileList.add(Mp4_2x2_Html_TemplateFile);
			mp4HtmlTemplate_FileList.add(Mp4_3x3_Html_TemplateFile);
			mp4HtmlTemplate_FileList.add(Mp4_3x5_Html_TemplateFile);
			mp4HtmlTemplate_FileList.add(Mp4_2x2_Html_SameTempFile);
			mp4HtmlTemplate_FileList.add(Mp4_3x3_Html_SameTempFile);
			mp4HtmlTemplate_FileList.add(Mp4_3x5_Html_SameTempFile);
			mp4HtmlTemplate_FileList.add(Mp4_3d_Html_TemplateFile);

//-----------------------------JPG--------------------------------------

			Jpg_3d_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_jpg__3d.html");
			Jpg_1x1_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_jpg_1x1_flow_left.html");
			Jpg_1x1_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_jpg_1x1_flow_right.html");
			Jpg_2x2_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_jpg_2x2.html");
			Jpg_2x2_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_jpg_2x2_flow_left.html");
			Jpg_2x2_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_jpg_2x2_flow_right.html");
			Jpg_2x2_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_jpg_2x2_same.html");
			Jpg_3x3_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_jpg_3x3.html");
			Jpg_3x3_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_jpg_3x3_flow_left.html");
			Jpg_3x3_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_jpg_3x3_flow_right.html");
			Jpg_3x3_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_jpg_3x3_same.html");
			Jpg_3x5_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_jpg_3x5.html");
			Jpg_3x5_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_jpg_3x5_same.html");
			Jpg_2x4_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_jpg_4x2_flow_left.html");
			Jpg_2x4_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_jpg_4x2_flow_right.html");
			Jpg_4x3_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_jpg_4x3_flow_left.html");
			Jpg_4x3_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_jpg_4x3_flow_right.html");

			Jpg_4x4_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_jpg_4x4_flow_left.html");
			Jpg_4x4_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_jpg_4x4_flow_right.html");
			Jpg_3x5_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_jpg_5x3_flow_left.html");
			Jpg_3x5_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_jpg_5x3_flow_right.html");
			Jpg_4x5_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_jpg_5x4_flow_right.html");
			Jpg_4x5_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_jpg_5x4_flow_left.html");

			jpgHtmlTemplate_FileList.add(Jpg_3d_Html_TemplateFile);
			jpgHtmlTemplate_FileList.add(Jpg_1x1_Html_TemplateFile_Left);
			jpgHtmlTemplate_FileList.add(Jpg_1x1_Html_TemplateFile_Right);
			jpgHtmlTemplate_FileList.add(Jpg_2x2_Html_TemplateFile);
			jpgHtmlTemplate_FileList.add(Jpg_2x2_Html_TemplateFile_Left);
			jpgHtmlTemplate_FileList.add(Jpg_2x2_Html_TemplateFile_Right);
			jpgHtmlTemplate_FileList.add(Jpg_2x2_Html_SameTempFile);
			jpgHtmlTemplate_FileList.add(Jpg_3x3_Html_TemplateFile);
			jpgHtmlTemplate_FileList.add(Jpg_3x3_Html_TemplateFile_Left);
			jpgHtmlTemplate_FileList.add(Jpg_3x3_Html_TemplateFile_Right);
			jpgHtmlTemplate_FileList.add(Jpg_3x3_Html_SameTempFile);
			jpgHtmlTemplate_FileList.add(Jpg_3x5_Html_TemplateFile);
			jpgHtmlTemplate_FileList.add(Jpg_3x5_Html_SameTempFile);
			jpgHtmlTemplate_FileList.add(Jpg_2x4_Html_TemplateFile_Left);
			jpgHtmlTemplate_FileList.add(Jpg_2x4_Html_TemplateFile_Right);
			jpgHtmlTemplate_FileList.add(Jpg_4x3_Html_TemplateFile_Left);
			jpgHtmlTemplate_FileList.add(Jpg_4x3_Html_TemplateFile_Right);
			jpgHtmlTemplate_FileList.add(Jpg_4x4_Html_TemplateFile_Left);
			jpgHtmlTemplate_FileList.add(Jpg_4x4_Html_TemplateFile_Right);
			jpgHtmlTemplate_FileList.add(Jpg_3x5_Html_TemplateFile_Left);
			jpgHtmlTemplate_FileList.add(Jpg_3x5_Html_TemplateFile_Right);
			jpgHtmlTemplate_FileList.add(Jpg_4x5_Html_TemplateFile_Left);
			jpgHtmlTemplate_FileList.add(Jpg_4x5_Html_TemplateFile_Right);

			// --------------------GIF--------------------------

			Gif_3d_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_gif__3d.html");
			Gif_1x1_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_gif_1x1_flow_left.html");
			Gif_1x1_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_gif_1x1_flow_right.html");
			Gif_2x2_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_gif_2x2.html");
			Gif_2x2_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_gif_2x2_flow_left.html");
			Gif_2x2_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_gif_2x2_flow_right.html");
			Gif_2x2_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_gif_2x2_same.html");
			Gif_3x3_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_gif_3x3.html");
			Gif_3x3_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_gif_3x3_flow_left.html");
			Gif_3x3_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_gif_3x3_flow_right.html");
			Gif_3x3_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_gif_3x3_same.html");
			Gif_3x5_Html_TemplateFile = new File(zbinPath + File.separator + "G2_Rule12_gif_3x5.html");
			Gif_3x5_Html_SameTempFile = new File(zbinPath + File.separator + "G2_Rule12_gif_3x5_same.html");
			Gif_2x4_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_gif_4x2_flow_left.html");
			Gif_2x4_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_gif_4x2_flow_right.html");
			Gif_4x3_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_gif_4x3_flow_left.html");
			Gif_4x3_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_gif_4x3_flow_right.html");
			Gif_4x4_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_gif_4x4_flow_left.html");
			Gif_4x4_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_gif_4x4_flow_right.html");
			Gif_3x5_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_gif_5x3_flow_left.html");
			Gif_3x5_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_gif_5x3_flow_right.html");
			Gif_4x5_Html_TemplateFile_Left = new File(zbinPath + File.separator + "G2_Rule12_gif_5x4_flow_right.html");
			Gif_4x5_Html_TemplateFile_Right = new File(zbinPath + File.separator + "G2_Rule12_gif_5x4_flow_left.html");

			gifHtmlTemplate_FileList.add(Gif_3d_Html_TemplateFile);
			gifHtmlTemplate_FileList.add(Gif_1x1_Html_TemplateFile_Left);
			gifHtmlTemplate_FileList.add(Gif_1x1_Html_TemplateFile_Right);
			gifHtmlTemplate_FileList.add(Gif_2x2_Html_TemplateFile);
			gifHtmlTemplate_FileList.add(Gif_2x2_Html_TemplateFile_Left);
			gifHtmlTemplate_FileList.add(Gif_2x2_Html_TemplateFile_Right);
			gifHtmlTemplate_FileList.add(Gif_2x2_Html_SameTempFile);
			gifHtmlTemplate_FileList.add(Gif_3x3_Html_TemplateFile);
			gifHtmlTemplate_FileList.add(Gif_3x3_Html_TemplateFile_Left);
			gifHtmlTemplate_FileList.add(Gif_3x3_Html_TemplateFile_Right);
			gifHtmlTemplate_FileList.add(Gif_3x3_Html_SameTempFile);
			gifHtmlTemplate_FileList.add(Gif_3x5_Html_TemplateFile);
			gifHtmlTemplate_FileList.add(Gif_3x5_Html_SameTempFile);
			gifHtmlTemplate_FileList.add(Gif_2x4_Html_TemplateFile_Left);
			gifHtmlTemplate_FileList.add(Gif_2x4_Html_TemplateFile_Right);

			gifHtmlTemplate_FileList.add(Gif_4x3_Html_TemplateFile_Left);
			gifHtmlTemplate_FileList.add(Gif_4x3_Html_TemplateFile_Right);
			gifHtmlTemplate_FileList.add(Gif_4x4_Html_TemplateFile_Left);
			gifHtmlTemplate_FileList.add(Gif_4x4_Html_TemplateFile_Right);
			gifHtmlTemplate_FileList.add(Gif_3x5_Html_TemplateFile_Left);
			gifHtmlTemplate_FileList.add(Gif_3x5_Html_TemplateFile_Right);
			gifHtmlTemplate_FileList.add(Gif_4x5_Html_TemplateFile_Left);
			gifHtmlTemplate_FileList.add(Gif_4x5_Html_TemplateFile_Right);

		}

		@Override
		boolean initParams4InputParam(String inputParam) {
			if (inputParam.contains("_mp4")) {
				operaType = 1;
			} else if (inputParam.contains("_jpg")) {
				operaType = 2;
			} else if (inputParam.contains("_gif")) {
				operaType = 3;
			} else if (inputParam.contains("_allmp4")) {
				operaType = 4;
			}

			return super.initParams4InputParam(inputParam);
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			System.out.println("Rule12 inputDirPath [ length ] = " + inputParamList.size());
			for (int i = 0; i < inputParamList.size(); i++) {
				String inputDirPath = inputParamList.get(i);
				System.out.println(
						"Rule12  inputDirPath [ " + i + " ] = " + inputDirPath + "  curDirFile = " + curDirFile);
				if (inputDirPath.endsWith("\\")) {
					inputDirPath = inputDirPath.replace("\\", "");
				}

				File inputDir = new File(curDirFile.getAbsoluteFile() + File.separator + inputDirPath);
				if (inputDir != null && inputDir.exists() && inputDir.isDirectory()) {
					operaDirFileList.add(inputDir);
				}
				System.out.println(" inputDir  = " + inputDir.getAbsolutePath());
			}
			if (operaDirFileList.size() == 0) {
				// ### 检测当前所目录是否存在mp4文件夹 如果有生成 html 播放文件

				System.out.println("当前用户没有输入执行的目录名称,进行检测是否有 mp4文件夹!");
				return true;
			}

			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
				ArrayList<File> allSubRealFileList) {
			if (operaDirFileList.size() == 0) {
				System.out.println("当前用户没有输入执行的目录名称,请重新输入B!");

				for (int i = 0; i < allSubDirFileList.size(); i++) {
					File dirFile = allSubDirFileList.get(i);
					String dirName = dirFile.getName().toLowerCase();
//					int operaType; // 0-unknow 1--mp4 2--jpg 3--gif
					if (operaType == 1 && dirName.contains("mp4")) {
						operaDirFileList.add(dirFile);
					} else if (operaType == 2 && dirName.contains("jpg")) {
						operaDirFileList.add(dirFile);
					} else if (operaType == 3 && dirName.contains("gif")) {
						operaDirFileList.add(dirFile);
					}

				}

			}

			if (operaType == 4) { // 对当前目录的所有的

				OperationHtmlMedia(curDirFile);

			} else {

				if (operaDirFileList.size() == 0) {
					System.out.println("当前用户没有输入执行的目录名称,请重新输入C!");
					return null;
				}

				for (int i = 0; i < operaDirFileList.size(); i++) {
					File operaDirFile = operaDirFileList.get(i);
					OperationHtmlMedia(operaDirFile);
				}

			}

			return super.applyDir_SubFileListRule5(allSubDirFileList, allSubRealFileList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			if (operaDirFileList.size() == 0) {
				System.out.println("当前用户没有输入执行的目录名称,请重新输入C!");
				return null;
			}
			if (operaType == 4) { // mp4all 的 逻辑与 别的逻辑 有点区别

				OperationHtmlMedia(curDirFile);

			} else {
				for (int i = 0; i < operaDirFileList.size(); i++) {
					File operaDirFile = operaDirFileList.get(i);
					OperationHtmlMedia(operaDirFile);
				}

			}

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void OperationHtmlMedia(File xdirFile) {
			switch (operaType) {
			case 1: // mp4
				ArrayList<File> mp4_mediaFileList = getSubTypeFileWithPoint(xdirFile, ".mp4");
				tryMediaFileRenameOperation(mp4_mediaFileList, ".mp4");
				tryMP4HtmlOperation(xdirFile, mp4_mediaFileList.size());
				break;
			case 2: // jpg
				ArrayList<File> jpg_mediaFileList = getSubTypeFileWithPoint(xdirFile, ".jpg");
				tryMediaFileRenameOperation(jpg_mediaFileList, ".jpg");
				tryJPGHtmlOperation(xdirFile, jpg_mediaFileList.size());
				break;
			case 3: // gif
				ArrayList<File> gif_mediaFileList = getSubTypeFileWithPoint(xdirFile, ".gif");
				tryMediaFileRenameOperation(gif_mediaFileList, ".gif");
				tryGIFHtmlOperation(xdirFile, gif_mediaFileList.size());
				break;

			case 4: // allmp4

				allMp4FileList = getAllSubFile(curDirFile, ".mp4");
				if (allMp4FileList == null || allMp4FileList.size() == 0) {
					System.out.println("  当前目录 curDirFile=" + curDirFile.getAbsolutePath()
							+ " 没有寻找到 .mp4文件 执行生成 allmp4-html文件失败!");
					return;
				}
				System.out.println("  当前目录 curDirFile=" + curDirFile.getAbsolutePath() + " allMp4FileList.size()="
						+ allMp4FileList.size());

				StringBuilder htmlCodeSB_head1 = new StringBuilder();
				StringBuilder htmlCodeSB_head2 = new StringBuilder();

				htmlCodeSB_head1.append("var videoCount =  " + allMp4FileList.size() + ";\n");
				htmlCodeSB_head2.append("var videolist = [");
				for (int i = 0; i < allMp4FileList.size(); i++) {
					File mp4File = allMp4FileList.get(i);
					String mp4FilePath = allMp4FileList.get(i).getAbsolutePath();
					String mp4FilePath_fixed = mp4FilePath.replace(File.separator, "/");
					String varName = "videofile_" + i;
					htmlCodeSB_head1.append(
							varName + " ={  index:" + i + " , filepath:\"" + mp4FilePath_fixed + "\" };" + "\n");
					if (i == allMp4FileList.size() - 1) {
						htmlCodeSB_head2.append(varName + "");
					} else {
						htmlCodeSB_head2.append(varName + ",");
					}

				}
				htmlCodeSB_head2.append("];\n");

				String htmlCode = htmlCodeSB_head1 + "\n" + htmlCodeSB_head2.toString();

				System.out.println("  htmlCode=\n " + htmlCode.toString());

				// hoderplace -begin
//							videofile_0 = { index:0 , path:"./mp4_1/mp4/",length:11,};
//							video1 = { index:1 , path:"./mp4_2/mp4/",length:11,};
//							var objectArr = [ person0,person1, ];
				// hoderplace -end

				tryAllMp4HtmlOperation(htmlCode);

				break;

			default:
			}

		}

		void tryMediaFileRenameOperation(ArrayList<File> mp4FileList, String fileTypeWithPoint) {
			int index = 0;
			ArrayList<File> tempFileList1 = new ArrayList<File>();

			for (int i = 0; i < mp4FileList.size(); i++) {
				index = i + 1;
				String timeStamp = "";
				String newName1 = index + "_" + getTimeStamp() + fileTypeWithPoint;
				String newName2 = index + fileTypeWithPoint;
				File curFile = mp4FileList.get(i);
				String parrentFilePath = curFile.getParentFile().getAbsolutePath();
				tryReName(curFile, newName1); // 第一次改名 避免重复
				File file1 = new File(parrentFilePath + File.separator + newName1);
				tempFileList1.add(file1);
			}
			for (int i = 0; i < tempFileList1.size(); i++) {
				index = i + 1;
				File curFile = tempFileList1.get(i);
				String newName = index + fileTypeWithPoint;
				tryReName(curFile, newName); // 第二次改名 实现顺序 1.xx 2.xx 3.xx 4.xx
			}

		}

		void tryMP4HtmlOperation(File curDirFile, int num) {
// 把当前的html文件 中的  对应的 占位符 以 num 进行 替换
// 把  html文件中 mp4/  转换为   当前目录名称  90890/
// 把当前的 html  文件 放入到当前的 shell的 根 目录    html命令为   参数目录_原有名称
			String curDirName = curDirFile.getName();

			for (int i = 0; i < mp4HtmlTemplate_FileList.size(); i++) {
				File HtmlFile = mp4HtmlTemplate_FileList.get(i);
				if (!HtmlFile.exists()) {
					System.out.println("注意当前Html文件不存在!  PATH:  " + HtmlFile.getAbsolutePath());
					continue;
				}
				String htmlname = HtmlFile.getName();
				htmlname = htmlname.replace("G2_Rule12", curDirName);

				String htmlContent = ReadFileContent(HtmlFile);
				htmlContent = htmlContent.replace("zukgitPlaceHolderindex", num + "");
				htmlContent = htmlContent.replace("mp4/", curDirName + "/");
				File curShellHtmlFile = new File(
						curDirFile.getParentFile().getAbsolutePath() + File.separator + "" + htmlname);
				writeContentToFile(curShellHtmlFile, htmlContent);
			}
		}

		void tryJPGHtmlOperation(File curDirFile, int num) {
			String curDirName = curDirFile.getName();

			for (int i = 0; i < jpgHtmlTemplate_FileList.size(); i++) {
				File HtmlFile = jpgHtmlTemplate_FileList.get(i);
				if (!HtmlFile.exists()) {
					System.out.println("注意当前Html文件不存在!  PATH:  " + HtmlFile.getAbsolutePath());
					continue;
				}
				String htmlname = HtmlFile.getName();
				htmlname = htmlname.replace("G2_Rule12", curDirName);

				String htmlContent = ReadFileContent(HtmlFile);
				htmlContent = htmlContent.replace("zukgitPlaceHolderindex", num + "");
				htmlContent = htmlContent.replace("jpg/", curDirName + "/");
				File curShellHtmlFile = new File(
						curDirFile.getParentFile().getAbsolutePath() + File.separator + "" + htmlname);
				writeContentToFile(curShellHtmlFile, htmlContent);
			}

		}

		void tryGIFHtmlOperation(File curDirFile, int num) {
			String curDirName = curDirFile.getName();
			String curParentDirName = curDirFile.getParentFile().getName();
			System.out.println("curDirFile = " + curDirFile.getAbsolutePath());
			System.out.println("ParentFile = " + curDirFile.getParentFile().getAbsolutePath());

			for (int i = 0; i < gifHtmlTemplate_FileList.size(); i++) {
				File HtmlFile = gifHtmlTemplate_FileList.get(i);
				if (!HtmlFile.exists()) {
					System.out.println("注意当前Html文件不存在!  PATH:  " + HtmlFile.getAbsolutePath());
					continue;
				}
				String htmlname = HtmlFile.getName();
				htmlname = htmlname.replace("G2_Rule12", curDirName);

				String htmlContent = ReadFileContent(HtmlFile);
				htmlContent = htmlContent.replace("zukgitPlaceHolderindex", num + "");
				htmlContent = htmlContent.replace("gif/", curDirName + "/");
				File curShellHtmlFile = new File(
						curDirFile.getParentFile().getAbsolutePath() + File.separator + "" + htmlname);
				writeContentToFile(curShellHtmlFile, htmlContent);
			}

		}

		// allMp4 的 操作逻辑
		void tryAllMp4HtmlOperation(String commonHtmlCode) {
			String curDirName = curDirFile.getName();
			String curParentDirName = curDirFile.getParentFile().getName();
			System.out.println("curDirFile = " + curDirFile.getAbsolutePath());
			System.out.println("ParentFile = " + curDirFile.getParentFile().getAbsolutePath());

			for (int i = 0; i < mp4AllHtmlTemplate_FileList.size(); i++) {
				File HtmlFile = mp4AllHtmlTemplate_FileList.get(i);
				if (!HtmlFile.exists()) {
					System.out.println("注意当前Html文件不存在!  PATH:  " + HtmlFile.getAbsolutePath());
					continue;
				}
				String htmlname = HtmlFile.getName();
				htmlname = htmlname.replace("G2_Rule12", curDirName);

				String htmlContent = ReadFileContent(HtmlFile);
				htmlContent = htmlContent.replace("zukgitPlaceHolderindex", commonHtmlCode);

				File curShellHtmlFile = new File(curDirFile.getAbsolutePath() + File.separator + "" + htmlname);
				writeContentToFile(curShellHtmlFile, htmlContent);
				System.out.println("htmlContent:" + htmlContent);
				System.out.println("生成Html文件:" + curShellHtmlFile.getAbsolutePath());
			}

		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name
					+ "  #_12_mp4      ### 把目录中包含mp4的文件夹进行检索 生成 [mp4目录名称-html文件][mp4改名1.2.3][单一文件夹] 播放文件  \n"
					+ Cur_Bat_Name
					+ "  #_12_allmp4    ### 把目录中所有的mp4文件 生成 Arr数组 放入 [allmp4_timestamp_html][mp4不改名][多文件夹] 播放文件  \n"
					+ Cur_Bat_Name + "  #_12_gif      ### 把目录中包含gif的文件夹进行检索 生成 gif-html 播放文件  \n" + Cur_Bat_Name
					+ "  #_12_jpg      ### 把目录中包含jpg的文件夹进行检索 生成 jpg-html 播放文件  \n" + Cur_Bat_Name
					+ "  #_12_mp4   <目标文件夹目录>   ### 把当前输入目录包含mp4文件夹 生成 mp4-html 播放文件  \n" + Cur_Bat_Name
					+ "  #_12_gif   <目标文件夹目录>   ### 把当前输入目录包含gif文件夹 生成 gif-html 播放文件  \n" + Cur_Bat_Name
					+ "  #_12_jpg   <目标文件夹目录>   ### 把当前输入目录包含jpg文件夹 生成 jpg-html 播放文件  \n";

		}

	}

	class AllDirSubFile_Order_Rule_11 extends Basic_Rule {

		AllDirSubFile_Order_Rule_11() {
			super("#", 11, 5);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_11    ## (清除原有名称，序列从1开始)把当前所有子目录的文件 当前目录 下的实体文件依次按顺序按类型重新命名!  \n";
		}

		@SuppressWarnings("unchecked")
		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
				ArrayList<File> allSubRealFileList) {

			System.out.println("allSubDirFileList = " + allSubDirFileList.size());
			System.out.println("allSubRealFileList = " + allSubRealFileList.size());
			if (!allSubDirFileList.contains(curDirFile)) {
				allSubDirFileList.add(curDirFile);
			}

			for (int i = 0; i < allSubDirFileList.size(); i++) {
				File dirFileItem = allSubDirFileList.get(i);
				// 获取当前文件夹下的所有依据 文件类型为 .jpg .png .mp4 为key 进行的
				Map<String, ArrayList<File>> curDirSubRealFile = getCurSubFileMap(dirFileItem);

				// 对文件依次重命名

				Map.Entry<String, ArrayList<File>> entry;
				// 不同的类型文件怎么处理?

				if (curDirSubRealFile != null) {
					Iterator iterator = curDirSubRealFile.entrySet().iterator();
					while (iterator.hasNext()) {
						entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
						String typeStr = entry.getKey(); // Map的Value
						String typeWithOutPot = typeStr.replace(".", "");

						ArrayList<File> fileArr = entry.getValue(); // Map的Value

						// 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;
						ArrayList<File> curRenamePlace = new ArrayList<File>();
						for (int m = 0; m < fileArr.size(); m++) {
							File curFile = fileArr.get(m);
							String oldName = curFile.getName();
							// String curFileName = curFile.getName();

							System.out.println("═════════════ m=" + m + "═════════════");
							// 占位符 使得 所有文件都命名成功 避免那些已经有该名称了的文件
							String newName1 = "_ZHolder_" + (m + 1)
									+ ("".equals(typeWithOutPot) ? "" : "." + typeWithOutPot);
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
							if (tryReName(curFile, newName1)) {
								System.out.println("成功 Index =" + m + "  命名( " + oldName + " => " + newName1 + ")  => "
										+ curFile.getAbsolutePath());
							} else {
								System.out.println("失败 Index =" + m + "  命名( " + oldName + " => " + newName1 + ")  => "
										+ curFile.getAbsolutePath());
							}
							File fileItem2 = new File(
									curFile.getParentFile().getAbsolutePath() + File.separator + newName1);
							if (fileItem2.exists()) {
								curRenamePlace.add(fileItem2);

								/*
								 * System.out.println(fileItem2+ " fileItem2.exists() = "+ fileItem2.exists());
								 * String newName2 = newName1.replace("_ZHolder_","");
								 *
								 * if(tryReName(fileItem2,newName2)){
								 * System.out.println("成功 Index ="+m+"  命名( "+oldName+" => "+
								 * newName1+")  => "+curFile.getAbsolutePath()); }else{
								 * System.out.println("失败 Index ="+m+"  命名( "+oldName+" => "+
								 * newName1+")  => "+curFile.getAbsolutePath()); }
								 */

							}

						}
						System.out.println("════════════════════════════════════════════════════");

						for (int n = 0; n < curRenamePlace.size(); n++) {
							System.out.println("═════════════ n=" + n + "═════════════");

							File fileItem2 = curRenamePlace.get(n);
							String newName2 = fileItem2.getName().replace("_ZHolder_", "");
							if (tryReName(fileItem2, newName2)) {
								System.out.println("成功 Index =" + n + "  命名( " + fileItem2.getName() + " => " + newName2
										+ ")  => " + fileItem2.getAbsolutePath());
							} else {
								System.out.println("失败 Index =" + n + "  命名( " + fileItem2.getName() + " => " + newName2
										+ ")  => " + fileItem2.getAbsolutePath());
							}
						}
						curRenamePlace.clear();

					}
				}

			}
			return super.applyDir_SubFileListRule5(allSubDirFileList, allSubRealFileList);
		}

		@SuppressWarnings("unchecked")
		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			if (!curDirList.contains(curDirFile)) {
				curDirList.add(curDirFile);
			}

			for (int i = 0; i < curDirList.size(); i++) {
				File dirFileItem = curDirList.get(i);
				// 获取当前文件夹下的所有依据 文件类型为 .jpg .png .mp4 为key 进行的
				Map<String, ArrayList<File>> curDirSubRealFile = getCurSubFileMap(dirFileItem);

				// 对文件依次重命名

				Map.Entry<String, ArrayList<File>> entry;
				// 不同的类型文件怎么处理?

				if (curDirSubRealFile != null) {
					Iterator iterator = curDirSubRealFile.entrySet().iterator();
					while (iterator.hasNext()) {
						entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
						String typeStr = entry.getKey(); // Map的Value
						String typeWithOutPot = typeStr.replace(".", "");

						ArrayList<File> fileArr = entry.getValue(); // Map的Value

						// 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;
						ArrayList<File> curRenamePlace = new ArrayList<File>();
						for (int m = 0; m < fileArr.size(); m++) {
							File curFile = fileArr.get(m);
							String oldName = curFile.getName();
							// String curFileName = curFile.getName();

							System.out.println("═════════════ m=" + m + "═════════════");
							// 占位符 使得 所有文件都命名成功 避免那些已经有该名称了的文件
							String newName1 = "_ZHolder_" + m + ("".equals(typeWithOutPot) ? "" : "." + typeWithOutPot);
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
							if (tryReName(curFile, newName1)) {
								System.out.println("成功 Index =" + m + "  命名( " + oldName + " => " + newName1 + ")  => "
										+ curFile.getAbsolutePath());
							} else {
								System.out.println("失败 Index =" + m + "  命名( " + oldName + " => " + newName1 + ")  => "
										+ curFile.getAbsolutePath());
							}
							File fileItem2 = new File(
									curFile.getParentFile().getAbsolutePath() + File.separator + newName1);
							if (fileItem2.exists()) {
								curRenamePlace.add(fileItem2);

								/*
								 * System.out.println(fileItem2+ " fileItem2.exists() = "+ fileItem2.exists());
								 * String newName2 = newName1.replace("_ZHolder_","");
								 *
								 * if(tryReName(fileItem2,newName2)){
								 * System.out.println("成功 Index ="+m+"  命名( "+oldName+" => "+
								 * newName1+")  => "+curFile.getAbsolutePath()); }else{
								 * System.out.println("失败 Index ="+m+"  命名( "+oldName+" => "+
								 * newName1+")  => "+curFile.getAbsolutePath()); }
								 */

							}

						}
						System.out.println("════════════════════════════════════════════════════");

						for (int n = 0; n < curRenamePlace.size(); n++) {
							System.out.println("═════════════ n=" + n + "═════════════");

							File fileItem2 = curRenamePlace.get(n);
							String newName2 = fileItem2.getName().replace("_ZHolder_", "");
							if (tryReName(fileItem2, newName2)) {
								System.out.println("成功 Index =" + n + "  命名( " + fileItem2.getName() + " => " + newName2
										+ ")  => " + fileItem2.getAbsolutePath());
							} else {
								System.out.println("失败 Index =" + n + "  命名( " + fileItem2.getName() + " => " + newName2
										+ ")  => " + fileItem2.getAbsolutePath());
							}
						}
						curRenamePlace.clear();

					}
				}

			}

			return curDirList;
		}
	}

	// // zrule_apply_G2.bat #_10_append 2001 往当前文件夹后缀增加 2001
	// // zrule_apply_G2.bat #_10_prefix 2001 往当前文件夹前缀增加 2001
	// // zrule_apply_G2.bat #_10_create 1_100 创建一个序列号从1到100的100个文件夹
	// // zrule_apply_G2.bat #_10_create temp_ 1_100 创建一个序列号从temp1到temp100的100个文件夹
	// // zrule_apply_G2.bat #_10_create _temp 1_100 创建一个序列号从1temp到100temp的100个文件夹
	// // zrule_apply_G2.bat #_10_create i_temp 1_100
	// 创建一个序列号从i1temp到i100temp100的100个文件夹

	// // zrule_apply_G2.bat #_10_create 7000_7100 创建一个序列号从7000开始的到7100结束的文件夹
	// // zrule_apply_G2.bat #_10_replace abc_DEF 创建一个序列号从7000开始的到7100结束的文件夹

	class DirOperation_Rule_10 extends Basic_Rule {

		String firstParamStr; // 第一个参数

		int DIR_OPERA_TYPE_APPEND = 1; // 后缀增加
		String appendStr_1;
		int DIR_OPERA_TYPE_PREFIX = 2; // 前缀增加
		String prefixStr_2;

		int DIR_OPERA_TYPE_CREATE = 3; // 创建文件
		int beginIndex_3;
		int endIndex_3;
		String prefixStr_3;
		String appendStr_3;

		int DIR_OPERA_TYPE_REPLACE = 4; // 替换文件夹名称
		String replacedStr_4;
		String newNameStr_4;

		// 识别当前用户 指定的操作类型 1后缀增加 2前缀增加 3创建文件 4替换文件夹名称
		int currentOperaType = 0;

		DirOperation_Rule_10() {
			super("#", 10, 4);
			prefixStr_3 = "";
			appendStr_3 = "";
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean falg = true;
			if (currentOperaType == 1) {
				appendStr_1 = inputParamList.get(inputParamList.size() - 1);
			} else if (currentOperaType == 2) {
				prefixStr_2 = inputParamList.get(inputParamList.size() - 1);
			} else if (currentOperaType == 4) {
				String inputStr = inputParamList.get(inputParamList.size() - 1);
				if (!inputStr.contains("_")) {
					falg = false;
				}

				String[] inputArr = inputStr.split("_");

				if (inputArr.length >= 2) {

					replacedStr_4 = inputArr[0];
					newNameStr_4 = inputArr[inputArr.length - 1];
				} else {
					falg = false;
				}
			} else if (currentOperaType == 3) {

				for (int i = 0; i < inputParamList.size(); i++) {

					String paramItem = inputParamList.get(i);
					if (paramItem != null && paramItem.equals(firstParamStr)) {
						continue; // 第一个参数不操作
					}

					if (!paramItem.contains("_")) {
						falg = false;
						continue;
					}
					String fixedParam = paramItem.replace("_", "");

					if (isNumeric(fixedParam)) { // 如果是 字母 说明是起始的那个参数
						String[] IndexArr = paramItem.split("_");

						if (IndexArr.length >= 2) {

							String beginIndex_3_Str = IndexArr[0];
							String endIndex_3_Str = IndexArr[IndexArr.length - 1];
							if (isNumeric(beginIndex_3_Str)) {
								beginIndex_3 = Integer.parseInt(beginIndex_3_Str);

							} else {
								falg = false;
							}

							if (isNumeric(endIndex_3_Str)) {
								endIndex_3 = Integer.parseInt(endIndex_3_Str);
							} else {
								falg = false;
							}

						} else {
							falg = false;
						}
					} else { // 名称的参数
						if (paramItem.endsWith("_")) {
							appendStr_3 = "";
							String[] NamePreArr = paramItem.split("_");
							prefixStr_3 = NamePreArr[0];
							System.out.println("appendStr_3=" + appendStr_3 + "   prefixStr_3=" + prefixStr_3);

						} else {
							String[] NamePreArr = paramItem.split("_");
							if (NamePreArr.length >= 2) {
								prefixStr_3 = NamePreArr[0];
								appendStr_3 = NamePreArr[1];
								System.out.println("appendStr_3=" + appendStr_3 + "   prefixStr_3=" + prefixStr_3);

							}

						}

					}

				}

			}

			return super.initParamsWithInputList(inputParamList) || falg;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			switch (currentOperaType) {

			case 1:
				for (int i = 0; i < curDirList.size(); i++) {
					File dirFile = curDirList.get(i);
					String dirName = dirFile.getName();
					String newName = dirName + appendStr_1;
					tryReName(dirFile, newName);
				}
				break;

			case 2:
				for (int i = 0; i < curDirList.size(); i++) {
					File dirFile = curDirList.get(i);
					String dirName = dirFile.getName();
					String newName = prefixStr_2 + dirName;
					tryReName(dirFile, newName);
				}
				break;

			case 3:
				for (int i = beginIndex_3; i < endIndex_3 + 1; i++) {
					String absDirPath = curDirFile.getAbsolutePath();
					String newDir = absDirPath + File.separator + prefixStr_3 + i + appendStr_3;
					File curDirFileItem = new File(newDir);
					curDirFileItem.mkdirs();
				}
				break;

			case 4:

				for (int i = 0; i < curDirList.size(); i++) {
					File dirFile = curDirList.get(i);
					String dirName = dirFile.getName();
					String newName = dirName.replace(replacedStr_4, newNameStr_4);
					tryReName(dirFile, newName);
				}

				break;

			default:
				System.out.println("当前 currentOperaType = " + currentOperaType + "  没有找到合适的操作类型去处理 ");
			}

			return curDirList;
		}

		@Override
		boolean initParams4InputParam(String inputParam) {

			firstParamStr = inputParam;
			if (inputParam.contains("append")) {
				currentOperaType = 1;
			} else if (inputParam.contains("prefix")) {
				currentOperaType = 2;

			} else if (inputParam.contains("replace")) {
				currentOperaType = 4;

			} else if (inputParam.contains("create")) {
				currentOperaType = 3;

			}

			return super.initParams4InputParam(inputParam);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_10_append  _over   往当前文件夹后缀增加 _over \n" + Cur_Bat_Name
					+ "  #_10_prefix  temp   往当前文件夹前缀增加 temp \n" + Cur_Bat_Name
					+ " #_10_create  1_100   创建一个序列号从1到100的100个文件夹   \n" + Cur_Bat_Name
					+ " #_10_create   temp_  1_100   创建一个序列号从temp1到temp100的100个文件夹 \n " + Cur_Bat_Name
					+ " #_10_create   _temp  1_100   创建一个序列号从1temp到100temp的100个文件夹 \n " + Cur_Bat_Name
					+ " #_10_create   j_temp  1_100   创建一个序列号从 j_1_temp 到100temp的 j_100_temp 个文件夹 \n " + Cur_Bat_Name
					+ " #_10_create  7000_7100  创建一个序列号从7000开始的到7100结束的文件夹  \n " + Cur_Bat_Name
					+ " #_10_replace  abc_DEF  把当前文件夹名称中的  abc 转为 DEF \n ";
		}

	}

	// // zrule_apply_G2.bat #_9 _jpg 把没有类型的文件名称修改为 jpg格式名称
	// // zrule_apply_G2.bat #_9 jpg_ 去除当前jpg的格式 使得其文件格式未知

	// 把 当前目录下子文件 进行格式的转换
	// // zrule_apply_G2.bat #_9 _jpg 把没有类型的文件名称修改为 jpg格式名称
	// // zrule_apply_G2.bat #_9 jpg_ 去除当前jpg的格式 使得其文件格式未知
	// zrule_apply_G2.bat #_9 jpg_png 把 jpg的格式转为png的格式
	// zrule_apply_G2.bat #_9 png_jpg 把 jpg的格式转为png的格式
	// // zrule_apply_G2.bat #_9 gif_ 去除当前gif的格式 使得其文件格式未知
	// // zrule_apply_G2.bat #_9 _gif 把没有类型的文件名称修改为 jpg格式名称
	// // zrule_apply_G2.bat #_9 mp4_ 去除当前mp4的格式 使得其文件格式未知
	// // zrule_apply_G2.bat #_9 _mp4 把没有类型的文件名称修改为 mp4格式名称
	// // zrule_apply_G2.bat #_9 原类型_目标类型 把没有类型的文件名称修改为 jpg格式名称
	class FileType_Rule_9 extends Basic_Rule {
		String originType;
		String targetType;

		FileType_Rule_9() {
			super("#", 9, 3);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_9  _jpg   把没有类型的文件名称修改为 jpg格式名称\n" + Cur_Bat_Name
					+ "  #_9  jpg_   去除当前jpg的格式 使得其文件格式未知 \n" + Cur_Bat_Name + " #_9  jpg_png  把  jpg的格式转为png的格式  \n"
					+ Cur_Bat_Name + " #_9  png_jpg  把  jpg的格式转为png的格式 \n " + Cur_Bat_Name
					+ " #_9  gif_   去除当前gif的格式 使得其文件格式未知  \n " + Cur_Bat_Name
					+ " #_9  _gif   把没有类型的文件名称修改为 jpg格式名称  \n " + Cur_Bat_Name + " #_9  png_jpg  把  jpg的格式转为png的格式 \n "
					+ Cur_Bat_Name + " #_9  mp4_   去除当前mp4的格式 使得其文件格式未知 \n " + Cur_Bat_Name
					+ " #_9  _mp4   把没有类型的文件名称修改为 mp4格式名称 \n " + Cur_Bat_Name
					+ " #_9  7z_7疫z   把当前 7z文件名后缀改为 7疫z 使得无法检测具体类型 \n " + Cur_Bat_Name
					+ " #_9  原类型_目标类型   把没有类型的文件名称【原类型】->【目标类型】 \n ";
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			// 获取到装换的类型
			String inputFileTypeParams = inputParamList.get(inputParamList.size() - 1);

			if (!inputFileTypeParams.contains("_")) {
				Flag = false;
				System.out.println("无法检测到当前 第9 Rule   原始类型_目标类型参数   请检查后重新执行");
			} else {

				if (inputFileTypeParams.endsWith("_")) {
					String target = "";
					String[] parmas = inputFileTypeParams.split("_");
					String origin = parmas[0];
					System.out.println("item=" + inputFileTypeParams + "   origin=" + origin + "     target=" + target);
					originType = origin;
					targetType = target;

				} else {
					String[] parmas = inputFileTypeParams.split("_");
					System.out.println(
							"item=" + inputFileTypeParams + "   origin=" + parmas[0] + "     target=" + parmas[1]);
					originType = parmas[0];
					targetType = parmas[1];
				}

				Flag = true;

			}
			curFilterFileTypeList.add(originType);

			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			for (int i = 0; i < subFileList.size(); i++) {
				File curFIle = subFileList.get(i);
				String originName = curFIle.getName();
				// 执行 修改文件类型的操作

				// 1. 如果当前文件 过滤类型是 空 那么 可能就是没有任何的类型了
				// 如果当前过滤的类型是 originType 是"" 空的话 那么就会过滤出所有的文件 那么只操作 不包含.的那些文件
				if ("".equals(originType)) {
					if (originName.contains(".")) {
						continue; // 包含了 . 说明有类型 那么 不操作
					}
					String newName = originName + "." + targetType;
					tryReName(curFIle, newName);
				} else {
					// 有具体的 过滤的文件
					String oldType = "." + originType;
					String newType = "." + targetType;
					if ("".equals(targetType)) {
						newType = "";
					}

					if (originName.contains(oldType)) {
						String newName = originName.replace(oldType, newType);
						tryReName(curFIle, newName);
					}

				}

			}

			return subFileList;
		}
	}

	// 把文件后缀中的中文给去除掉 不包含文件夹 不包含孙文件
	class ClearChineseType_8 extends Basic_Rule {

		ClearChineseType_8() {
			super("#", 8, 4);
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			System.out.println("Rule8_ClearChineseType_8   搜索到的实体文件个数:" + curRealFileList.size());

			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = curFile.getName();
				if (currentFileName.contains(".")) {
					String typeStr = currentFileName.substring(currentFileName.lastIndexOf("."));
					if (isContainChinese(typeStr)) {
						// //清除中文 清除 空格
						String newType = clearChinese(typeStr).replace(" ", "");
						String newName = currentFileName.replace(typeStr, newType); // 新名称
						System.out.println("newType = " + newType + "    newName=" + newName);
						tryReName(curFile, newName);
					}
				}

			}

			return curRealFileList;
		}

		@Override
		String simpleDesc() {
			return "把当前命令的文件包含.的文件的 后缀名称中的中文清除掉  例如 1.7啊z -> 1.7z   2.你zip -> 2.zip \n" + Cur_Bat_Name
					+ " #_8    <指定后缀有中文的文件>  [索引8]   // 把当前目录下文件 后缀中文去除  \n";
		}
	}

	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
	// // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

	// 对当前目录的文件进行加密 解密
	class Encropty_Rule_7 extends Basic_Rule {
		boolean mEncroptyDirect = true; // true---加密 false--解密
		boolean isAllFileOperation = false;

		boolean isBatchOperation = false; // 是否你是批量处理 会生成固定的 bad_batch good_batch 文件夹 而不是时间戳文件夹

		boolean isZVIOperation = false; // 是否是 1970ZVI 文件夹 操作

		Encropty_Rule_7() {
			super("#", 7, 4);
			isAllFileOperation = false;
			isBatchOperation = false;
			isZVIOperation = false;
		}

		@Override
		boolean initParams4InputParam(String inputParam) {
			if (inputParam.contains("good")) {
				mEncroptyDirect = false;
			} else {
				mEncroptyDirect = true;
			}

			if (inputParam.contains("batch")) {
				isBatchOperation = true;
			} else {
				isBatchOperation = false;
			}

			if (inputParam.contains("#")) {
				isAllFileOperation = true;
			} else {
				isAllFileOperation = false;
			}

			if (inputParam.toLowerCase().contains("zvi") && inputParam.toLowerCase().contains("good")) {
				mEncroptyDirect = false;
				isZVIOperation = true;
				isBatchOperation = false;
			}

			return super.initParams4InputParam(inputParam);
		}

		@Override
		String simpleDesc() {
			return "   默认bad(加密) 把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 加密bad/解密good\n" + Cur_Bat_Name
					+ " #_7_bad   (默认--加密文件)  把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 加密bad 生成 【 time + bad 】 加密文件夹 \n"
					+ Cur_Bat_Name + " #_7_good   (解密文件) 把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 解密good 【 time + good 】 生成解密文件夹\n"
					+ Cur_Bat_Name + " jpg_7_bad  [索引7]   // 把当前目录下的 jpg文件 加密 \n" + Cur_Bat_Name
					+ " jpg_7_good  [索引7]   // 把当前目录下的 jpg文件 解密 \n" + Cur_Bat_Name
					+ " #_7_bad  [索引7]   // 把当前目录所有文件进行加密  加密文件在新的 时间戳文件夹中 \n" + Cur_Bat_Name
					+ " #_7_good  [索引7]   // 把当前目录所有文件进行解密  解密文件在新的 时间戳文件夹中 \n" + Cur_Bat_Name
					+ " #_7_bad_batch   [索引7]   // 把当前目录所有文件进行加密  加密文件在新的【 固定文件夹 bad_batch 】中 适合批量处理 \n" + Cur_Bat_Name
					+ " #_7_good_batch   [索引7]   // 把当前目录所有文件进行解密 解密文件在新的【 固定文件夹 good_batch 】中 适合批量处理 " + "\n"
					+ Cur_Bat_Name
					+ " #_7_good_zvi   [索引7]   // 把当前目录中的 1970ZVI  解密到新的文件夹在新的【 固定文件夹 1970ZVI_Good 】中 适合批量处理 \n";

		}

//                    return "把 当前目录下所有的 jpg  mp4 gif  都转为 i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif 的文件格式\n" +
//        Cur_Bat_Name + "  jgm_5_recovery  [索引5]   // 在当前 Z_VI 根目录 计算 当前的 JPG GIF MP4的起始值 \n" +
//        Cur_Bat_Name + "  jgm_5_nextstep  [索引5]   //  JPG="+jpgBeginIndex+ " GIF="+gifBeginIndex+" MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4+" ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ";

		void jiamiAllDir(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			// 1.创建一个时间戳文件夹
			// 2.在当前文件夹的基础上

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
			String date = df.format(new Date());
			String CurBadDirName = "bad_AllFile_" + date;

			if (isBatchOperation) {
				if (mEncroptyDirect) {
					CurBadDirName = "bad_batch";
				} else {
					CurBadDirName = "good_batch";
				}
			}

			File curBadDirFile = new File(curDirFile.getAbsolutePath() + File.separator + CurBadDirName);
			curBadDirFile.mkdirs();
			String oldBasePath = curDirFile.getAbsolutePath();
			String newBasePath = curBadDirFile.getAbsolutePath();

			if (!curDirList.contains(curDirFile)) {
				curDirList.add(curDirFile);
			}

			for (int i = 0; i < curDirList.size(); i++) {
				File oldDirFile = curDirList.get(i);
				String newDirFilePath = oldDirFile.getAbsolutePath().replace(oldBasePath, newBasePath);
				File newDirFile = new File(newDirFilePath);
				newDirFile.mkdirs();

				for (int j = 0; j < oldDirFile.listFiles().length; j++) {

					File oldRealFile = oldDirFile.listFiles()[j];
					if (oldRealFile.isDirectory()) {
						continue;
					}

					String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
					File newRealFile = new File(newRealFilePath);
					// 加密操作
					createEncryFile(oldRealFile, newRealFile);
				}

			}

			/*
			 * for (int i = 0; i < curRealFileList.size(); i++) { File oldRealFile =
			 * curRealFileList.get(i); String newRealFilePath =
			 * oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath); File
			 * newRealFile = new File(newRealFilePath); // 加密操作
			 * createEncryFile(oldRealFile,newRealFile); }
			 */

		}

		File getContainDirName(File curShellDir, String name, boolean isDir) {
			File targetFile = null;
			String absShellPath = curShellDir.getAbsolutePath();
			System.out.println(" 当前Shell的路径为: curDirFile =  " + absShellPath);
			File[] fileArr = curShellDir.listFiles();
			if (fileArr != null) {

				for (int i = 0; i < fileArr.length; i++) {
					File fileItem = fileArr[i];
					String fileName = fileItem.getName();
					if (isDir) {
						if (fileItem.isDirectory() && fileItem.exists()) {
							if (fileName.equals(name)) {
								return fileItem;
							}
						}
					} else {
						if (fileItem.isFile() && fileItem.exists()) {
							if (fileName.equals(name)) {
								return fileItem;
							}
						}

					}

				}
			}

			return targetFile;

		}

		void jiemi1970ZVIDir(File m1970ZVI_DirFile, ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
			String date = df.format(new Date());
			String CurBadDirName = "1970ZVI_Good";

			// 1970ZVI_Good
			File curBadDirFile = new File(curDirFile.getAbsolutePath() + File.separator + CurBadDirName);

			curBadDirFile.mkdirs();
			// 1970ZVI路径 /C/1970ZVI
			String oldBasePath = curDirFile.getAbsolutePath(); // 原有的路径 /C
			String newBasePath = curBadDirFile.getAbsolutePath(); // 生成的 新路径 /C/1970ZVI_Good

			for (int i = 0; i < curDirList.size(); i++) {
				File oldDirFile = curDirList.get(i); // 原有的要解密文件
				String oldDirPath = oldDirFile.getAbsolutePath();
				if (!oldDirPath.contains("1970ZVI")) { // 如果当前目录 不包含 1970ZVI 那么 不创建这个目录
					continue;
				}

				// 把 原有的 1970ZVI 路径 变化为 1970ZVI_Good 路径
				String newDirFilePath = oldDirFile.getAbsolutePath().replace(oldBasePath + File.separator + "1970ZVI",
						newBasePath);
				System.out.println("AAAAAAAA  newBasePath=" + newBasePath + "  newDirFilePath=" + newDirFilePath);

				System.out.println("oldBasePath/1970ZVI --->  " + oldBasePath + File.separator + "1970ZVI   "
						+ "oldDirFile[" + i + "]  = " + oldDirFile.getAbsolutePath() + "  newDirFilePath[" + i + "] = "
						+ newDirFilePath);
				File newDirFile = new File(newDirFilePath);
				newDirFile.mkdirs();

				for (int j = 0; j < oldDirFile.listFiles().length; j++) {

					File oldRealFile = oldDirFile.listFiles()[j];
					String oldRealFile_Path = oldRealFile.getAbsolutePath();
					if (oldRealFile.isDirectory()) {
						continue;
					}

					if (isExpressType(oldRealFile)) {
						continue;
					}

					// 如果原文件全路径 没有 1970ZVI 那么 不操作 这个文件
					if (!oldRealFile_Path.contains("1970ZVI")) {
						continue;
					}

					String newRealFilePath = oldRealFile.getAbsolutePath().replace("1970ZVI", "1970ZVI_Good");

					String batch_fileName = oldRealFile.getName();
//						File newRealFile = new File(newBasePath + File.separator + batch_fileName);   //  子文件夹取消 消失

					File newRealFile = new File(newRealFilePath); // 子文件夹存在
					// 解密操作
					System.out.println("执行当前 ZVI解密操作  batch_fileName = " + batch_fileName + "     newBasePath="
							+ newBasePath + "    newRealFile=" + newRealFile.getAbsolutePath());

					createDecryFile(oldRealFile, newRealFile);

				}

			}

			/*
			 * if(isZVIOperation) { // 如果是 zvi 操作 新的 地址要去掉 1970ZVI_Good/1970ZVI/xxx ->
			 * 1970ZVI_Good/xxx if(newBasePath.contains(File.separator+"1970ZVI") &&
			 * !newBasePath.endsWith(File.separator+"1970ZVI")) { newDirFilePath =
			 * newDirFilePath.replace("1970ZVI", "").replace("_Good",
			 * "1970ZVI_Good").replace(File.separator+File.separator, File.separator);
			 * System.out.println("BBBBBBBB  newBasePath = "+ newBasePath
			 * +"  newDirFilePath="+newDirFilePath); }else
			 * if(newBasePath.endsWith(File.separator+"1970ZVI")) { newDirFilePath =
			 * curBadDirFile.getAbsolutePath(); System.out.println("CCCCCC  newBasePath = "+
			 * newBasePath);
			 *
			 * }
			 *
			 * }
			 */

			/*
			 * if(isZVIOperation) { // 解密操作 System.out.println("ZVI newRealFilePath = "+
			 * newRealFilePath); newRealFilePath =
			 * oldRealFile.getAbsolutePath().replace(oldBasePath,
			 * newBasePath).replace("1970ZVI", "").replace("_Good",
			 * "1970ZVI_Good").replace(File.separator+File.separator, File.separator); File
			 * newRealFile = new File(newRealFilePath); createDecryFile(oldRealFile,
			 * newRealFile); System.out.println("执行当前 ZVI 解密操作  batch_fileName = "+
			 * batch_fileName
			 * +"     newBasePath="+newBasePath+"    newRealFile="+newRealFile.
			 * getAbsolutePath());
			 *
			 * }
			 */

		}

		void jiemiAllDir(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			// 1.创建一个时间戳文件夹
			// 2.在当前文件夹的基础上
			System.out.println(" curDirFile =" + curDirFile.getAbsolutePath());

			if (isZVIOperation) {
				File zviFileDir = getContainDirName(curDirFile, "1970ZVI", true);
				if (zviFileDir != null) {

					jiemi1970ZVIDir(zviFileDir, curFileList, subFileTypeMap, curDirList, curRealFileList);

				} else {
					System.out.println(" 当前目录没有 1970ZVI 文件夹 无法执行   " + Cur_Bat_Name + " #_7_good_zvi   ## 批量解密操作！！！");
				}

				return;
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
			String date = df.format(new Date());
			String CurBadDirName = "good_AllFile_" + date;

			if (isBatchOperation) {
				if (mEncroptyDirect) {
					CurBadDirName = "bad_batch";
				} else {
					CurBadDirName = "good_batch";
				}
			}

			File curBadDirFile = new File(curDirFile.getAbsolutePath() + File.separator + CurBadDirName);
			curBadDirFile.mkdirs();
			String oldBasePath = curDirFile.getAbsolutePath(); // 原有的路径 /C
			String newBasePath = curBadDirFile.getAbsolutePath(); // 生成的 新路径 /C/good_batch
			if (!curDirList.contains(curDirFile)) {
				curDirList.add(curDirFile);
			}

			System.out.println("执行当前所有文件 解密操作 newBasePath_Src =  " + newBasePath);

			System.out.println("执行当前所有文件  解密操作 newBasePath_Fixed =  " + newBasePath);

			System.out.println("执行当前所有文件 解密操作  curDirList.size() = " + curDirList.size());

			for (int i = 0; i < curDirList.size(); i++) {
				File oldDirFile = curDirList.get(i); // 原有的要解密文件

//				执行当前所有文件  解密操作  [0] = D:\BaiduNetdiskDownload\公式\bad_batch\A
				System.out.println("执行当前所有文件  解密操作  [" + i + "] = " + oldDirFile.getAbsolutePath());
				if (!isBatchOperation) { // 如果 不是 batch 那么会创建文件夹
					// 如果是当前目录下多 子文件夹 那么就把在* 的 情况下会 创建这个文件夹 事实上在 batch的情况下不需要这个文件夹
					String newDirFilePath = oldDirFile.getAbsolutePath().replace(oldBasePath, newBasePath);
					System.out.println("AAAAAAAA  newBasePath=" + newBasePath + "  newDirFilePath=" + newDirFilePath);

					System.out.println("oldDirFile[" + i + "]  = " + oldDirFile.getAbsolutePath() + "  newDirFilePath["
							+ i + "] = " + newDirFilePath);
					File newDirFile = new File(newDirFilePath);
					newDirFile.mkdirs();
				}

				for (int j = 0; j < oldDirFile.listFiles().length; j++) {

					File oldRealFile = oldDirFile.listFiles()[j];
					if (oldRealFile.isDirectory()) {
						continue;
					}

					if (isExpressType(oldRealFile)) {
						continue;
					}

					if (!isBatchOperation) { // 如果 不是 batch 那么会创建文件夹 和原来保持一致
						String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
						File newRealFile = new File(newRealFilePath);
						// 解密操作
						createDecryFile(oldRealFile, newRealFile);
					} else {

						String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);

						/*
						 * 执行当前 解密操作 执行当前所有文件 解密操作 [0] = D:\BaiduNetdiskDownload\公式\bad_batch\A ==
						 * oldDirFile.getAbsolutePath() batch_fileName = A.pdf == batch_fileName
						 * newBasePath=D:\BaiduNetdiskDownload\公式\bad_batch\good_batch
						 * newBasePath=D:\BaiduNetdiskDownload\公式\bad_batch\good_batch\A =====>
						 * newBasePath 应该还有一个 A newRealFilePath =
						 * D:\BaiduNetdiskDownload\公式\bad_batch\good_batch\A\A.pdf
						 * newRealFile=D:\BaiduNetdiskDownload\公式\bad_batch\good_batch\A.pdf
						 */

						String batch_fileName = oldRealFile.getName();
//						File newRealFile = new File(newBasePath + File.separator + batch_fileName);   //  子文件夹取消 消失

						File newRealFile = new File(newRealFilePath); // 子文件夹存在
						// 解密操作
						System.out.println("执行当前 解密操作  batch_fileName = " + batch_fileName + "     newBasePath="
								+ newBasePath + "    newRealFile=" + newRealFile.getAbsolutePath());
						System.out.println(" newRealFilePath = " + newRealFilePath);
						createDecryFile(oldRealFile, newRealFile);

					}

				}
			}

//            for (int i = 0; i < curRealFileList.size(); i++) {
//                File oldRealFile = curRealFileList.get(i);
//                String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
//                File newRealFile = new File(newRealFilePath);
//                // 加密操作
//                createDecryFile(oldRealFile,newRealFile);
//            }

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			System.out.println("Rule7 搜索到的实体文件个数:  curRealFileList.size() =" + curRealFileList.size());
			if (isAllFileOperation) {
				if (mEncroptyDirect) {
					// 加密所有文件夹

					jiamiAllDir(curFileList, subFileTypeMap, getAllSubDirFile(curDirFile), curRealFileList);
				} else {
					// 解密当前所有文件夹
					jiemiAllDir(curFileList, subFileTypeMap, getAllSubDirFile(curDirFile), curRealFileList);

				}
				return null;
			}
			boolean containUserType = curFilterFileTypeList.contains("#"); // 是否包含用户选中的了文件类型 没有包含 那么就把所有实体realty 加密

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			String curNewDirName = date;
			if (mEncroptyDirect) {
				curNewDirName += "_bad";
			} else {
				curNewDirName += "_good";
			}

			if (!containUserType) {
				curNewDirName += "_" + curFilterFileTypeList.get(0); // 1.如果所有文件都加密 那么没有后缀 如果某一个文件类型解密 那么添加后缀
			}
			if (isBatchOperation) {
				if (mEncroptyDirect) {
					curNewDirName = "bad_batch";
				} else {
					curNewDirName = "good_batch";
				}
			}

			File tempDirFile = new File(curDirFile.getAbsolutePath() + File.separator + curNewDirName);
			tempDirFile.mkdirs(); // 创建文件夹
			String tempDirPath = tempDirFile.getAbsolutePath();
			System.out.println("Rule7 搜索到的实体文件个数:" + curRealFileList.size());

			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = File.separator + curFile.getName();

//                System.out.println("currentFileName = "+ currentFileName);
				if (mEncroptyDirect) { // 加密时 如果是 以 i_temp 开头 并且 以 .jpg 为结尾时 加密的类型去掉
					if (currentFileName.contains(".jpg") && currentFileName.contains("i_temp")) {
						currentFileName = currentFileName.replace(".jpg", "");
					} else if (currentFileName.contains(".mp4") && currentFileName.contains("v_temp")) {
						currentFileName = currentFileName.replace(".mp4", "");
					} else if (currentFileName.contains(".gif") && currentFileName.contains("g_temp")) {
						currentFileName = currentFileName.replace(".gif", "");
					}

					File badFile = new File(tempDirPath + File.separator + currentFileName);

					createEncryFile(curFile, badFile);
				} else { // 解密 如果当前文件 不包含 .
					if (!currentFileName.contains(".") && currentFileName.contains("i_temp")) {
						currentFileName = currentFileName + ".jpg";
					} else if (!currentFileName.contains(".") && currentFileName.contains("v_temp")) {
						currentFileName = currentFileName + ".mp4";
					} else if (!currentFileName.contains(".") && currentFileName.contains("g_temp")) {
						currentFileName = currentFileName + ".gif";
					}
					File goodFile = new File(tempDirPath + File.separator + currentFileName);
					System.out.println("createDecryFile goodFile = " + goodFile.getAbsolutePath() + "tempDirPath = "
							+ tempDirPath);
					createDecryFile(curFile, goodFile);
				}
			}

			return null;
		}
	}

	static boolean isExpressType(File targetFile) {
		boolean flag = false;

		if (targetFile.isDirectory()) {
			return false;
		}

		String type = getFileTypeWithPoint(targetFile.getName());

		if (".7z".equals(type) || ".zip".equals(type) || ".rar".equals(type) || ".war".equals(type)) {
			return true;
		}

		return flag;

	}

	class SubDirRename_Rule_6 extends Basic_Rule {

		boolean isOrder_NoOriginName = false;

		SubDirRename_Rule_6() {
			super("#", 6, 4);
			isOrder_NoOriginName = false;
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_6    // 修改当前的一级子目录下的文件夹 以及文件  按顺序命令 【序号_原名称.类型】  (不操作 孙文件 孙文件夹 )  \n"
					+ Cur_Bat_Name + " png_6    // 修改当前的一级子目录下的文件夹下的 png格式文件  按顺序命令 【序号_原名称.类型】  (不操作 孙文件 孙文件夹 ) \n"
					+ Cur_Bat_Name
					+ " png_6_order    // 修改当前的一级子目录下的文件夹下的 png格式文件  按顺序命令 【0000.png 0001.png 0002.png ....】  (不操作 孙文件 孙文件夹 ) \n"
					+ Cur_Bat_Name
					+ " m3u8_6_order  // 【type<可选>_6_order】 修改当前的目录下指定类型的 文件  按顺序命令[0000.m3u8 0001.m3u8] 【序号.类型】【不保留原名称】  (不操作 孙文件 孙文件夹 )  \n";
		}

		@Override
		boolean initParams4InputParam(String inputParam) {
			if (inputParam.contains("order")) {
				isOrder_NoOriginName = true;
			}

			return super.initParams4InputParam(inputParam);
		}

		@SuppressWarnings("unchecked")
		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {

			boolean executeFlag = false;
			boolean isFixedAllSubFlag = curFilterFileTypeList.contains("#");
			if (isFixedAllSubFlag) { // 只有包含 #_6 才对 文件夹进行操作 png_6 那么就只对 当前文件夹下的 png文件进行操作
				for (int i = 0; i < curDirList.size(); i++) {
					File dir = curDirList.get(i);
					String dirName = dir.getName();
					String new_dirName = i + "_" + dirName;
					tryReName(dir, new_dirName);
				}
			}

			Map.Entry<String, ArrayList<File>> entry;
			// 不同的类型文件怎么处理?

			if (subFileTypeMap != null) {
				Iterator iterator = subFileTypeMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map的Value
					String typeWithOutPot = typeStr.replace(".", "");

					if (!isFixedAllSubFlag && !curFilterFileTypeList.contains(typeWithOutPot)) {
						// 如果 当前操作不是操作所有文件 并且这个类型不在匹配列表中 那么 不执行 返回next
						// 如果是 全 操作 那么 往下执行
						// 如果不是全操作 当前类型包含 那么往下执行
						continue;
					}

					ArrayList<File> fileArr = entry.getValue(); // Map的Value

					// 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;
					for (int i = 0; i < fileArr.size(); i++) {
						File curFile = fileArr.get(i);
						// String curFileName = curFile.getName();
						String newName = i + "_" + curFile.getName();
						String fileTypeStr = getFileTypeWithPoint(curFile.getName());
						if (isOrder_NoOriginName) {
							newName = getPaddingIntString(i, 4, "0", true) + fileTypeStr;
						}
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
						if (tryReName(curFile, newName)) {
							executeFlag = true;
						}
					}
				}
			}
			return executeFlag ? curRealFileList
					: super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}
	}

	public static String getFileNameNoPoint(File file) {
		String type = getFileTypeWithPoint(file.getName());
		String originname = file.getName();
		String resultName = originname.replace(type, "");
		return resultName;
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

	// 把 当前目录下所有的 jpg mp4 gif 都转为 i_temp1_1.jpg v_temp2_1.mp4 g_temp3_1.gif 的文件格式
	class AVI_Rule_5 extends Basic_Rule {
		String tempTag = "temp";
		boolean isTemp; // 是否是零时起的编号
		int mTempBeginIndex = 0; // 零时编号的默认起始地址

		boolean isRecovrty = false; // 当前是否是 读取当前目录 计算 ProPerities的值的操作
		boolean isEnable = true; // 当存在增量的时候 不起作用 不执行 记录的操作
		boolean isExistAddPart = false; // 是否存在增量
		boolean executeNextStep = false; // 当用户输入的 输入参数 包含 nextstep 时 执行 增量的 重置0操作 添加到index的操作

		int jpgBeginIndex = 0;
		int fixed_jpg_BeginIndex = 0;
		String jpgtag = "i";
		int jpgDirTempIndex = 0;
		int jpgEndIndex = 1;
		int nextStepCountJPG = 0; // 当前 JPG的 增量

		int gifBeginIndex = 0;
		String giftag = "g";
		int gifDirTempIndex = 0;
		int fixed_gif_BeginIndex = 0;
		int gifEndIndex = 1;
		int nextStepCountGIF = 0; // 当前 GIF的 增量

		int mp4BeginIndex = 0; // 从 Propertities 中读取到的值
		String mp4tag = "v"; // mp4的前缀
		int mp4DirTempIndex = 0; // 依据 mp4BeginIndex 计算出的 temp1 temp2 .... temp100
		int fixed_mp4_BeginIndex = 0; // 在当前 tempx 中的索引 大小为 mp4BeginIndex%1000
		int mp4EndIndex = 1; // 最后保存到 Propertities 中的 值
		int nextStepCountMP4 = 0; // 当前 MP4 的 增量

		AVI_Rule_5() {
			super("jgm", 5, 3);
			curFilterFileTypeList.add("jpg");
			curFilterFileTypeList.add("gif");
			curFilterFileTypeList.add("mp4");
			// 从 Proprietary 拿到当前的总的索引 值
			// jpgBeginIndex =
			// gifBeginIndex =
			// mp4BeginIndex =
			String strJPGBegin = G2_Properties.getProperty("jpgBeginIndex");
			if (strJPGBegin == null) {
				strJPGBegin = "0";
				G2_Properties.put("jpgBeginIndex", "0");
			}
			jpgBeginIndex = Integer.parseInt(strJPGBegin);

			String strGIFBegin = G2_Properties.getProperty("gifBeginIndex");
			if (strGIFBegin == null) {
				strGIFBegin = "0";
				G2_Properties.put("gifBeginIndex", "0");
			}
			gifBeginIndex = Integer.parseInt(strGIFBegin);

			String strMP4Begin = G2_Properties.getProperty("mp4BeginIndex");
			if (strMP4Begin == null) {
				strMP4Begin = "0";
				G2_Properties.put("mp4BeginIndex", "0");
			}
			mp4BeginIndex = Integer.parseInt(strMP4Begin);

			String strNextStepJPG = G2_Properties.getProperty("nextStepCountJPG");
			if (strNextStepJPG == null) {
				strNextStepJPG = "0";
				G2_Properties.put("nextStepCountJPG", "0");
			}
			nextStepCountJPG = Integer.parseInt(strNextStepJPG);

			String strNextStepGIF = G2_Properties.getProperty("nextStepCountGIF");
			if (strNextStepGIF == null) {
				strNextStepGIF = "0";
				G2_Properties.put("nextStepCountGIF", "0");
			}
			nextStepCountGIF = Integer.parseInt(strNextStepGIF);

			String strNextStepMP4 = G2_Properties.getProperty("nextStepCountMP4");
			if (strNextStepMP4 == null) {
				strNextStepMP4 = "0";
				G2_Properties.put("nextStepCountMP4", "0");
			}
			nextStepCountMP4 = Integer.parseInt(strNextStepMP4);

			if (nextStepCountMP4 != 0 || nextStepCountGIF != 0 || nextStepCountJPG != 0) {
				isExistAddPart = true;
			}

			jpgDirTempIndex = jpgBeginIndex / 1000;
			fixed_jpg_BeginIndex = jpgBeginIndex % 1000;

			gifDirTempIndex = gifBeginIndex / 1000;
			fixed_gif_BeginIndex = gifBeginIndex % 1000;

			mp4DirTempIndex = mp4BeginIndex / 1000;
			fixed_mp4_BeginIndex = mp4BeginIndex % 1000;

		}

		@Override
		String simpleDesc() {
			return "把 当前目录下所有的 jpg  mp4 gif  都转为 i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif 的文件格式\n" + Cur_Bat_Name
					+ "  jgm_5_temp0      [索引5]   // 零时把当前gif jpg mp4 类型 起始位置设置为0   \n" + Cur_Bat_Name
					+ "  jgm_5_temp99      [索引5]   // 零时把当前gif jpg mp4 类型 起始位置设置为99   \n" + Cur_Bat_Name
					+ "  jgm_5_recovery  [索引5]   // 在当前 Z_VI 根目录 计算 当前的 JPG GIF MP4的起始值 \n" + Cur_Bat_Name
					+ "  jgm_5_nextstep  [索引5]   //  JPG=" + jpgBeginIndex + " GIF=" + gifBeginIndex + " MP4="
					+ mp4BeginIndex + "  JPG增量=" + nextStepCountJPG + "    GIF增量=" + nextStepCountGIF + "   MP4增量="
					+ nextStepCountMP4 + " ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ";
		}

		@Override
		boolean initParams4InputParam(String inputParam) {

			if (inputParam.contains("temp")) {
				int index = inputParam.indexOf("temp") + "temp".length();
				String tempIndexStr = inputParam.substring(index);
				if (isNumeric(tempIndexStr)) {
					mTempBeginIndex = Integer.parseInt(tempIndexStr);
				} else {
					if (tempIndexStr.contains("_")) {
						String blankIndex = tempIndexStr.substring(0, tempIndexStr.indexOf("_"));
						if (isNumeric(blankIndex)) {
							mTempBeginIndex = Integer.parseInt(blankIndex);
						}
					} else {
						mTempBeginIndex = 0; // 默认为0
					}
				}

				isTemp = true;
			}
			if (inputParam.contains("nextstep")) {
				executeNextStep = true;
			}

			if (inputParam.contains("_recovery")) {
				isRecovrty = true;
				isEnable = false;
				curFilterFileTypeList.add("#"); // 把当前所有文件都加入到列表中
			}
			System.out.println("OLD记录的Properties信息:(OLD)   " + " JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
					+ "   MP4=" + mp4BeginIndex + "  JPG增量=" + nextStepCountJPG + "    GIF增量=" + nextStepCountGIF
					+ "   MP4增量=" + nextStepCountMP4);

			if (executeNextStep) { // 如果存在增量 当前不执行 并且用户是输入的 nextstep的时候 执行 step的更新
				jpgBeginIndex = jpgBeginIndex + nextStepCountJPG;
				gifBeginIndex = gifBeginIndex + nextStepCountGIF;
				mp4BeginIndex = mp4BeginIndex + nextStepCountMP4;
				G2_Properties.setProperty("jpgBeginIndex", "" + jpgBeginIndex);
				G2_Properties.setProperty("gifBeginIndex", "" + gifBeginIndex);
				G2_Properties.setProperty("mp4BeginIndex", "" + mp4BeginIndex);
				G2_Properties.setProperty("nextStepCountJPG", "" + 0);
				G2_Properties.setProperty("nextStepCountGIF", "" + 0);
				G2_Properties.setProperty("nextStepCountMP4", "" + 0);
				isEnable = false;
			}

			return super.initParams4InputParam(inputParam);
		}

		void tryDynamicCalCulateBeginIndex(ArrayList<File> subFileList) {

			String jpg_pre = "i_temp";
			ArrayList<File> jpgTempList = new ArrayList<File>();

			String gif_pre = "g_temp";
			ArrayList<File> gifTempList = new ArrayList<File>();

			String mp4_pre = "v_temp";
			ArrayList<File> mp4TempList = new ArrayList<File>();

			for (int i = 0; i < subFileList.size(); i++) {
				File curFile = subFileList.get(i);
				if (curFile.getAbsolutePath().contains("Z_VI")) {
					if (curFile.getName().startsWith(jpg_pre)) {
						jpgTempList.add(curFile);
					} else if (curFile.getName().startsWith(gif_pre)) {
						gifTempList.add(curFile);
					} else if (curFile.getName().startsWith(mp4_pre)) {
						mp4TempList.add(curFile);
					}
				}

			}

			if (jpgTempList.size() == 0 && gifTempList.size() == 0 && mp4TempList.size() == 0) {
				System.out.println("当前执行目录不在 Z_VI的根目录 Git_Dir , 请重新执行 " + Cur_Bat_Name);
				return;
			}

			// 通过 搜索 计算得到的 type 文件的 长度 Count
			// 通过 计算 文件最后的名字得到的 index = Count - 1
			int jpgDynimicCount = jpgTempList.size();
			int gifDynimicCount = gifTempList.size();
			int mp4DynimicCount = mp4TempList.size();

			jpgTempList.sort(new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {

					int o1Index = calculIndexFromName(o1.getName());

					int o2Index = calculIndexFromName(o2.getName());
					if (o1Index < o2Index) {
						return -1;
					}
					if (o1Index == o2Index) {
						return 0;
					}
					return 1;
				}
			});

			gifTempList.sort(new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {

					int o1Index = calculIndexFromName(o1.getName());

					int o2Index = calculIndexFromName(o2.getName());
					if (o1Index < o2Index) {
						return -1;
					}
					if (o1Index == o2Index) {
						return 0;
					}
					return 1;
				}
			});

			// Comparable VICompare = new Comparable()
			mp4TempList.sort(new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {

					int o1Index = calculIndexFromName(o1.getName());

					int o2Index = calculIndexFromName(o2.getName());
					if (o1Index < o2Index) {
						return -1;
					}
					if (o1Index == o2Index) {
						return 0;
					}
					return 1;
				}
			});
			File lastJPGFile = null;
			File lastGIFFile = null;
			File lastMP4File = null;

			if (jpgTempList.size() > 0) {
				lastJPGFile = jpgTempList.get(jpgTempList.size() - 1);
			}

			if (gifTempList.size() > 0) {
				lastGIFFile = gifTempList.get(gifTempList.size() - 1);
			}

			if (mp4TempList.size() > 0) {
				lastMP4File = mp4TempList.get(mp4TempList.size() - 1);
			}

			int jpgLastIndex = 0;
			int gifLastIndex = 0;
			int mp4LastIndex = 0;
			if (lastJPGFile != null) {
				jpgLastIndex = calculIndexFromName(lastJPGFile.getName());
			}
			if (lastGIFFile != null) {
				gifLastIndex = calculIndexFromName(lastGIFFile.getName());

			}
			if (lastMP4File != null) {
				mp4LastIndex = calculIndexFromName(lastMP4File.getName());
			}

			if (jpgDynimicCount != jpgBeginIndex || (jpgLastIndex + 1) != jpgDynimicCount) { // 大小 和 记录的起始点 不一致 那么需要
				// 重新该名称
				for (int i = 0; i < jpgTempList.size(); i++) {
					File jpgFile = jpgTempList.get(i);
					String jpgFileName = "i" + "_" + getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,
							gifDirTempIndex, 0, i, 3, "0", true);
					tryReName(jpgFile, jpgFileName);
				}
			}

			if (gifDynimicCount != gifBeginIndex || (gifLastIndex + 1) != gifDynimicCount) { // 大小 和 记录的起始点 不一致 那么需要
				// 重新该名称
				for (int i = 0; i < gifTempList.size(); i++) {
					File gifFile = gifTempList.get(i);
					String gifFileName = "g" + "_" + getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,
							gifDirTempIndex, 0, i, 3, "0", true);
					tryReName(gifFile, gifFileName);
				}
			}

			if (mp4DynimicCount != mp4BeginIndex || (mp4LastIndex + 1) != mp4DynimicCount) { // 大小 和 记录的起始点 不一致 那么需要
				// 重新该名称
				for (int i = 0; i < mp4TempList.size(); i++) {
					File mp4File = mp4TempList.get(i);
					String mp4FileName = "v" + "_" + getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,
							gifDirTempIndex, 0, i, 3, "0", true);
					tryReName(mp4File, mp4FileName);
				}
			}

			System.out.println("recovery 搜索到的文件 数量:" + subFileList.size());
			if (lastJPGFile != null) {
				jpgLastIndex = calculIndexFromName(lastJPGFile.getName());
				System.out.println("最后一个 JPG 文件的名称为:" + lastJPGFile.getName() + "  索引:" + jpgLastIndex
						+ (jpgLastIndex != (jpgBeginIndex - 1) ? " 匹配不成功(改名操作)" : "匹配成功"));

			}
			if (lastGIFFile != null) {
				gifLastIndex = calculIndexFromName(lastGIFFile.getName());
				System.out.println("最后一个 GIF 文件的名称为:" + lastGIFFile.getName() + "  索引:" + gifLastIndex
						+ (gifLastIndex != (gifBeginIndex - 1) ? " 匹配不成功(改名操作)" : "匹配成功"));

			}
			if (lastMP4File != null) {
				mp4LastIndex = calculIndexFromName(lastMP4File.getName());
				System.out.println("最后一个 MP4 文件的名称为:" + lastMP4File.getName() + "  索引:" + mp4LastIndex
						+ (mp4LastIndex != (mp4BeginIndex - 1) ? " 匹配不成功(改名操作)" : "匹配成功"));
			}
			System.out.println(
					"jpgDynimicIndex(JPG动态计算文件数量)=" + getXsizeString(jpgDynimicCount, 7) + "   (最后一个JPG文件名称索引+1)"
							+ getXsizeString(jpgLastIndex + 1, 7) + " ||    Pro记录 jpgBeginIndex 为:" + jpgBeginIndex);
			System.out.println(
					"gifDynimicIndex(GIF动态计算文件数量)=" + getXsizeString(gifDynimicCount, 7) + "   (最后一个GIF文件名称索引+1)"
							+ getXsizeString(gifLastIndex + 1, 7) + " ||    Pro记录 gifBeginIndex 为:" + gifBeginIndex);
			System.out.println(
					"mp4DynimicIndex(MP4动态计算文件数量)=" + getXsizeString(mp4DynimicCount, 7) + "   (最后一个MP4文件名称索引+1)"
							+ getXsizeString(mp4LastIndex + 1, 7) + " ||    Pro记录 mp4BeginIndex 为:" + mp4BeginIndex);

			recoveryProperities(jpgDynimicCount, gifDynimicCount, mp4DynimicCount);
			System.out.println();
		}

		int calculIndexFromName(String viName) {

			String valueA = viName.replace("_", "");
			valueA = valueA.replace("gif", "");
			valueA = valueA.replace("jpg", "");
			valueA = valueA.replace("mp4", "");
			valueA = valueA.replace("mp3", "");
			valueA = valueA.replace("png", "");
			valueA = valueA.replace("temp", "");
			valueA = valueA.replace("\"", "");
			valueA = valueA.replace(".", "");
			valueA = valueA.replace("(", "");
			valueA = valueA.replace(")", "");
			valueA = valueA.replace("）", "");
			valueA = valueA.replace("（", "");

			valueA = valueA.replace("a", "");
			valueA = valueA.replace("b", "");
			valueA = valueA.replace("c", "");
			valueA = valueA.replace("d", "");
			valueA = valueA.replace("e", "");
			valueA = valueA.replace("f", "");
			valueA = valueA.replace("g", "");
			valueA = valueA.replace("h", "");
			valueA = valueA.replace("i", "");
			valueA = valueA.replace("j", "");
			valueA = valueA.replace("k", "");
			valueA = valueA.replace("l", "");
			valueA = valueA.replace("m", "");
			valueA = valueA.replace("n", "");
			valueA = valueA.replace("o", "");
			valueA = valueA.replace("p", "");
			valueA = valueA.replace("q", "");
			valueA = valueA.replace("r", "");
			valueA = valueA.replace("s", "");
			valueA = valueA.replace("t", "");
			valueA = valueA.replace("u", "");
			valueA = valueA.replace("v", "");
			valueA = valueA.replace("w", "");
			valueA = valueA.replace("x", "");
			valueA = valueA.replace("y", "");
			valueA = valueA.replace("z", "");
			valueA = valueA.replace(" ", "").trim();
			int resultIndex = 0;
			try {
				resultIndex = Integer.parseInt(valueA);

			} catch (Exception e) {
				resultIndex = 0;
			}

			return resultIndex;
		}

		void recoveryProperities(int jpg, int gif, int mp4) {
			jpgBeginIndex = jpg;
			gifBeginIndex = gif;
			mp4BeginIndex = mp4;
			G2_Properties.setProperty("jpgBeginIndex", "" + jpg);
			G2_Properties.setProperty("gifBeginIndex", "" + gif);
			G2_Properties.setProperty("mp4BeginIndex", "" + mp4);
			G2_Properties.setProperty("nextStepCountJPG", "" + 0);
			G2_Properties.setProperty("nextStepCountGIF", "" + 0);
			G2_Properties.setProperty("nextStepCountMP4", "" + 0);

			System.out.println(" Z_VI(Git_Dir)恢复Pro数:(New)    JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
					+ "   MP4=" + mp4BeginIndex + "  JPG增量=0    GIF增量=0   MP4增量=0");
		}

		@SuppressWarnings("unchecked")
		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			boolean executeFlag = false;

			if (isRecovrty) { // 如果是要恢复的的话
				tryDynamicCalCulateBeginIndex(subFileList);
				return null;
			}

			String oldAddPart = "OLD 记录的Properties增量:(OLD)   " + " JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
					+ "   MP4=" + mp4BeginIndex + "  JPG增量=" + nextStepCountJPG + "    GIF增量=" + nextStepCountGIF
					+ "   MP4增量=" + nextStepCountMP4;
			if (!isEnable) {
				System.out.println("当前 Rule5 规则上的增量已经置0  增量已得到确认  请开始累计新的资源! ");
				System.out.println("当前记录到Prop的增量信息:(New)  " + " JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
						+ "   MP4=" + mp4BeginIndex + "  JPG增量=" + 0 + "    GIF增量=" + 0 + "   MP4增量=" + 0);

				return null;
			}
			if (isExistAddPart) {
				System.out.println("当前 Rule5 规则存在上次还未确认的增量 请执行如下命令来确认增量 使得NextStep完成\n" + Cur_Bat_Name
						+ " jgm_5_nextstep      // ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ");
			}
			Map.Entry<String, ArrayList<File>> entry;
			int nextStepCountJPG_new = 0;
			int nextStepCountGIF_new = 0;
			int nextStepCountMP4_new = 0;
			if (fileTypeMap != null) {
				Iterator iterator = fileTypeMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map的Value
					ArrayList<File> fileArr = entry.getValue(); // Map的Value
					String typeTag = jpgtag;
					String dirTempIndex = tempTag + jpgDirTempIndex;
					int tempIndex = 1;
					int fixedFileIndex = 0;
					if (".jpg".equals(typeStr)) {
						typeTag = jpgtag;
						dirTempIndex = tempTag + jpgDirTempIndex;
						fixedFileIndex = jpgBeginIndex;
						tempIndex = jpgDirTempIndex;
						nextStepCountJPG = fileArr.size();
						nextStepCountJPG_new = fileArr.size();
						if (!isTemp)
							G2_Properties.setProperty("nextStepCountJPG", "" + nextStepCountJPG);
						jpgEndIndex = jpgBeginIndex + fileArr.size();
						System.out.println("当前JPG起始值:" + fixedFileIndex + "    当前GIF的文件长度:" + fileArr.size());
					} else if (".mp4".equals(typeStr)) {
						typeTag = mp4tag;
						dirTempIndex = tempTag + mp4DirTempIndex;
						fixedFileIndex = mp4BeginIndex;
						tempIndex = mp4DirTempIndex;
						nextStepCountMP4 = fileArr.size();
						nextStepCountMP4_new = fileArr.size();
						if (!isTemp)
							G2_Properties.setProperty("nextStepCountMP4", "" + nextStepCountMP4);

						mp4EndIndex = mp4BeginIndex + fileArr.size();
						System.out.println("当前MP4起始值:" + fixedFileIndex + "    当前GIF的文件长度:" + fileArr.size());
					} else if (".gif".equals(typeStr)) {
						typeTag = giftag;
						dirTempIndex = tempTag + gifDirTempIndex;
						fixedFileIndex = gifBeginIndex;
						tempIndex = gifDirTempIndex;
						nextStepCountGIF = fileArr.size();
						System.out.println("当前GIF起始值:" + fixedFileIndex + "    当前GIF的文件长度:" + fileArr.size());
						nextStepCountGIF_new = fileArr.size();
						if (!isTemp)
							G2_Properties.setProperty("nextStepCountGIF", "" + nextStepCountGIF);
						gifEndIndex = gifBeginIndex + fileArr.size();

					} else {
						continue;
					}

					if (isTemp) {
						fixedFileIndex = mTempBeginIndex; // 如果是 temp 那么 默认 就把 temp转为 index
						nextStepCountJPG_new = 0;
						nextStepCountGIF_new = 0;
						nextStepCountMP4_new = 0;
					}
					// 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;

					for (int i = 0; i < fileArr.size(); i++) {

						File curFile = fileArr.get(i);
						// String curFileName = curFile.getName();
						String newName = typeTag + "_" + getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,
								gifDirTempIndex, fixedFileIndex, i, 3, "0", true) + typeStr;

//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;

						if (tryReName(curFile, newName)) {
							executeFlag = true;
						}
//                        fixedFileIndex++;
					}
				}
			}

			String NewAddPart = "New 记录的Properties增量:(New)   " + " JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
					+ "   MP4=" + mp4BeginIndex + "  JPG增量=" + nextStepCountJPG_new + "    GIF增量="
					+ nextStepCountGIF_new + "   MP4增量=" + nextStepCountMP4_new;

			System.out.println("══════════确认增量信息 Begin══════════");
			if (isExistAddPart) {
				// System.out.println("Rule5 上次的增量情况:");
				System.out.println(oldAddPart);
			} else {
				System.out.println("OLD     上次的不存在增量:(OLD)    JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
						+ "   MP4=" + mp4BeginIndex + " JPG增量=0     GIF增量=0     MP4增量=0");
			}
			// System.out.println("\nRule5 现在的增量情况: ");
			System.out.println(NewAddPart);

			System.out.println("New 现在使用如下命令把 New 当前的增量进行确认! \n" + Cur_Bat_Name
					+ " jgm_5_nextstep      // ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ");
			System.out.println("══════════确认增量信息 End══════════");
			if (executeFlag) {
				return curFixedFileList;
			}
			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		// 从 起始的地址 beginIndex 开始计算
		String getPaddingIntStringWithDirIndexFileNameWithIndex(String cTempTag, int CurrentTempIndex, int beginIndex,
				int index, int padinglength, String oneStr, boolean dirPre) {

			int indexIdentify = beginIndex + index;
			int tempIndexResult = (indexIdentify / 1000);
			String result = "" + getXsizeString((indexIdentify % 1000), oneStr, padinglength, dirPre);
			return cTempTag + tempIndexResult + "_" + result;

		}

		// 不从起始的地址 计算 从0，1,2,3.... 开始计算
		String getPaddingIntStringWithDirIndexFileName(String cTempTag, int CurrentTempIndex, int index,
				int padinglength, String oneStr, boolean dirPre) {

			int tempIndexA = (index / 1000);
			int tempIndexResult = CurrentTempIndex + tempIndexA;

			String result = "" + getXsizeString((index % 1000), oneStr, padinglength, dirPre);

			/*
			 * int length = (""+index).length();
			 *
			 * if(length < padinglength){ int distance = padinglength - length; for (int i =
			 * 0; i < distance; i++) { if(dirPre){ result = oneStr+result; }else{ result =
			 * result + oneStr; }
			 *
			 * }
			 *
			 * }
			 */

			return cTempTag + tempIndexResult + "_" + result;

		}

		String getXsizeString(int index, int paddingSize) {

			return getXsizeString(index, " ", paddingSize, true);

		}

		String getXsizeString(int index, String charOne, int paddingSize, boolean directPre) {
			String result = ("" + index);
			int length = ("" + index).length();
			if (length < paddingSize) {
				int distance = paddingSize - length;
				for (int i = 0; i < distance; i++) {
					if (directPre) {
						result = charOne + result;
					} else {
						result = result + charOne;
					}

				}
			}
			return result;
		}

	}

	// 把 当前目录下所有的 png jpeg 都转为 jpg的格式
	class Image2Png_Rule_4 extends Basic_Rule {
		String targetFileType = ".png";

		Image2Png_Rule_4() {
			super("png", 4, 3);
			curFilterFileTypeList.add(".jpeg");
			curFilterFileTypeList.add(".JPEG");
			curFilterFileTypeList.add(".jpg");
			curFilterFileTypeList.add(".JPG");
			curFilterFileTypeList.add(".PNG");
			targetFileType = ".png";
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			boolean falg = false;
			for (int i = 0; i < subFileList.size(); i++) {
				File imageFile = subFileList.get(i);
				String fileName = imageFile.getName();
				String newName = fileName.replace(".jpeg", targetFileType);
				newName = newName.replace(".jpg", targetFileType);
				newName = newName.replace(".JPEG", targetFileType);
				newName = newName.replace(".JPG", targetFileType);
				newName = newName.replace(".PNG", targetFileType);
				if (tryReName(imageFile, newName)) {
					falg = true;
				}
			}

			if (falg) {
				return curFixedFileList;
			}
			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {
			return " 把当前目录(包含子目录)所有的 .jpg .jpeg 的文件后缀改为 .png 的文件后缀";
		}
	}

	// 把 当前目录下所有的 png jpeg 都转为 jpg的格式
	class Image2Jpeg_Rule_3 extends Basic_Rule {
		String targetFileType = ".jpg";

		Image2Jpeg_Rule_3() {
			super("jpg", 3, 3);
			curFilterFileTypeList.add(".jpeg");
			curFilterFileTypeList.add(".JPEG");
			curFilterFileTypeList.add(".JPG");
			curFilterFileTypeList.add(".png");
			curFilterFileTypeList.add(".PNG");
			targetFileType = ".jpg";
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			boolean falg = false;
			for (int i = 0; i < subFileList.size(); i++) {
				File imageFile = subFileList.get(i);
				String fileName = imageFile.getName();
				String newName = fileName.replace(".jpeg", targetFileType);
				newName = newName.replace(".png", targetFileType);
				newName = newName.replace(".JPEG", targetFileType);
				newName = newName.replace(".PNG", targetFileType);
				newName = newName.replace(".JPG", targetFileType);
				if (tryReName(imageFile, newName)) {
					falg = true;
				}
			}

			if (falg) {
				return curFixedFileList;
			}
			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {
			return " 把当前目录(包含子目录)所有的 .png .jpeg 的文件后缀改为 .jpg 的文件后缀";
		}
	}

	// 指定什么类型的文件在当前使用什么样的规则
	// operation_type 操作类型
	// 1--读取文件内容字符串 进行修改 String applyOperationRule(String origin)
	// 2--对单个文件属性进行修改(文件名称) 对文件内容(字节)--进行修改 File applyFileByteOperationRule(File
	// originFile)
	// 3--对集合文件属性进行修改(文件名称) 对所有子文件--进行修改 ArrayList<File>
	// applyFileByteOperationRule(ArrayList<File> subFileList)
	// index 唯一指定的一种 rule规则

	// file_name_2 #_2 对当前目录下的所有文件进行 文件名称的重新命名 命名规则 在头部添加序号

	class File_Name_Rule_2 extends Basic_Rule {

		// key = type value = 符合过滤文件规则的名称的文件的集合
		// HashMap<String, ArrayList<File>> arrFileMap;
		boolean keepOriginalName = true;
		int inputBeginIndex = 0;

		boolean isOrder_Padding5 = false; // 是否 以 00000.jpg 00001.jpg 00002.jpg 这样命名
		// 是否是按 1.jpg 2,jpg 3.png 4.png 依次命名 而不是 1.jpg 2,jpg 1.png 2.png 类型来命名
		boolean isOrder = false;

		File_Name_Rule_2() {
			super("#", 2, 3); //
		}

		@SuppressWarnings("unchecked")
		boolean tryReNameOperation(HashMap<String, ArrayList<File>> arrFileMap) {
			boolean executeFlag = false;
			Map.Entry<String, ArrayList<File>> entry;
			int fileOrderIndex = 0;
//            System.out.println("1 fileOrderIndex = "+ fileOrderIndex);
			if (fileOrderIndex != inputBeginIndex && inputBeginIndex != 0) {
				fileOrderIndex = inputBeginIndex - 1;
			}
//            System.out.println("2 fileOrderIndex = "+ fileOrderIndex);
//            System.out.println("3 inputBeginIndex = "+ inputBeginIndex);
			if (arrFileMap != null) {
				Iterator iterator = arrFileMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map的Value
					ArrayList<File> fileArr = entry.getValue(); // Map的Value

					for (int i = 0; i < fileArr.size(); i++) {
						fileOrderIndex++;
						int index = i + 1;
						String newNamePre = (isOrder_Padding5 ? getPaddingIntString(index, 5, "0", true) : index) + "_";
						File curFile = fileArr.get(i);
						String curFileName = curFile.getName();
						String newName = "";
						if (keepOriginalName) {
							if (isOrder) { // 按顺序依次 不按 type了 一直走
								newName = (isOrder_Padding5 ? getPaddingIntString(fileOrderIndex, 5, "0", true)
										: fileOrderIndex) + "_" + curFileName + typeStr;
							} else {
								newName = newNamePre + curFileName + typeStr;
							}
						} else {
							// 如果不保留名称 那么没有类型的文件 将只有 序号 没有类型
							if ("unknow".equals(typeStr)) {
								newName = (isOrder_Padding5 ? getPaddingIntString(index, 5, "0", true) : index) + "";
							} else {
								if (isOrder) { // 按顺序依次 不按 type了 一直走
									newName = (isOrder_Padding5 ? getPaddingIntString(fileOrderIndex, 5, "0", true)
											: fileOrderIndex) + typeStr;
								} else {
									newName = (isOrder_Padding5 ? getPaddingIntString(index, 5, "0", true) : index)
											+ typeStr;
								}

							}
						}
						if (tryReName(curFile, newName)) {
							executeFlag = true;
						}
						System.out.println("index=[" + i + "] typeStr[" + typeStr + "]  newName[" + newName
								+ "]  keepOriginalName=[" + keepOriginalName + "]  isOrder[" + isOrder
								+ "]  isOrder_Padding5[" + isOrder_Padding5 + "]");
					}

				}
			}

			return executeFlag;
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			if (tryReNameOperation(fileTypeMap)) {
				return curFixedFileList;
			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		boolean initParams4InputParam(String inputParams) {
			if (inputParams.contains("_false")) {
				keepOriginalName = false;
			} else {
				keepOriginalName = true;
			}

			if (inputParams.contains("_order")) {
				isOrder = true;
			} else {
				isOrder = false;
			}

			if (inputParams.contains("padding5")) {
				isOrder_Padding5 = true;
			} else {
				isOrder_Padding5 = false;
			}

			if (inputParams.contains("_")) {
				String[] inputParamArr = inputParams.split("_");
				if (inputParamArr.length > 0 && isNumeric(inputParamArr[inputParamArr.length - 1].trim())) {
					inputBeginIndex = Integer.parseInt(inputParamArr[inputParamArr.length - 1].trim());
//                    System.out.println(" 0 inputBeginIndex = "+ inputBeginIndex);
				}
			}

			return super.initParams4InputParam(inputParams);

		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_true = " (保留原名称) 把当前的所有子文件(非目录)重命名为 【序号_原始名称.类型】的形式 例如 hello.jpg =》 1_hello.jpg  xx.jpg-》2_xx.jpg    001/4.jpg -> 001/3_4.jpg(不同文件夹)   保留原有名称 相同类型文件不同文件夹 使用同一个序列号   ";
			String desc_true_1 = " (保留原名称_按类型依次从1开始 order ) 把当前的所有子文件(非目录)重命名为 【序号_原始名称.类型 走到底】的形式 例如 hello.jpg =》 1_hello.jpg  xx.jpg-》2_xx.jpg   aa.png -> 3_aa.png  | 001/4.zip ->  001/4_4.zip  保留原有名称 不相同类型文件不同文件夹 使用同一个序列号 ";
			String desc_true_2 = " (保留原名称_依照输入索引为起始 order ) 把当前的所有子文件(非目录)重命名为 【自定义序号_原始名称.类型 走到底】的形式 例如 #_2_false_order_50  hello.jpg =》 50_hello.jpg  xx.jpg-》51_xx.jpg   aa.png -> 52_aa.png 保留原有名称 不相同类型文件不同文件夹 使用同一个序列号(序号自定义) ";

			String desc_false = "(清除原名称) 把当前的所有子文件(非目录)重命名为 【序号.类型】的形式 例如 hello.jpg =》 1.jpg  xx.png-》1.jpg   不保留原有名称 相同类型文件不同文件夹 使用同一个序列号";
			String desc_false_1 = "(清除原名称_按类型依次 order ) 把当前的所有子文件(非目录)重命名为 【序号.类型 走到底 】的形式 例如 hello.jpg =》 1.jpg  xx.jpg-》2_xx.jpg  xx.png-》3.png  xx.png-》4.png  不保留原有名称 不相同类型文件不同文件夹 使用同一个序列号 ";
			String desc_false_2 = "(清除原名称_按类型 依照输入索引为起始 order ) 把当前的所有子文件(非目录)重命名为 【输入Begin序号.类型 走到底 】的形式 例如   #_2_false_order_10  hello.jpg =》 10.jpg  xx.jpg-》11_xx.jpg  xx.png-》12.png  xx.png-》13.png  不保留原有名称 不相同类型文件不同文件夹 使用同一个序列号(序号自定义) ";
			String desc_false_3 = "(清除原名称_按类型 每个文件夹单独作为新的起点 以类型重新命名  ) 把当前的所有子文件(以目录分割)重命名实体文件(目录名称不变)为 A/A/aa.jpg  A/A/bb.jpg  A/A/cc.png  A/A/dd.png -> A/A/1.jpg  A/A/2.jpg  A/A/1.png  A/A/2.png  ";
			String desc_false_4 = "(清除原名称_按类型 每个文件夹单独作为新的起点 以类型重新命名  ) 把当前的所有子文件(以目录分割)重命名实体文件(目录名称不变)为 A/A/aa.jpg  A/A/bb.jpg  A/A/cc.png  A/A/dd.png -> A/A/1.jpg  A/A/2.jpg  A/A/1.png  A/A/2.png  ";

			String desc_true_pading1 = " (保留原名称) 把当前的所有子文件(非目录)重命名为 【序号_原始名称.类型】的形式 例如 hello.jpg =》 00001_hello.jpg  xx.jpg-》00002_xx.jpg    001/4.jpg -> 001/00003_4.jpg(不同文件夹)   保留原有名称 相同类型文件不同文件夹 使用同一个序列号对齐5位   ";

			String desc_false_pading1 = "(清除原名称) 把当前的所有子文件(非目录)重命名为 【序号.类型】的形式 例如 hello.jpg =》 00001.jpg  xx.png-》00002.jpg 对其5位数字  不保留原有名称 相同类型文件不同文件夹 使用同一个序列号";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index + "_true" + "    [索引 " + index + "]  描述: "
						+ desc_true + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order" + "    [索引 " + index
						+ "]  描述: " + desc_true_1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order_20" + "    [指定开始索引 "
						+ index + "]  描述: " + desc_true_2 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order_20_padding5"
						+ "    [指定开始索引 " + index + "]  描述: " + desc_true_pading1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false" + "    [索引 " + index
						+ "]  描述:" + desc_false + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order" + "    [索引 " + index
						+ "]  描述:" + desc_false_1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order_padding5" + "    [索引 "
						+ index + "]  描述:" + desc_false_pading1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order_10" + "    [指定开始索引 "
						+ index + "]  描述:" + desc_false_2 + "\n";

			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index + "_true" + "    [索引 " + index + "]  描述:"
						+ desc_true;
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order" + "    [索引 " + index
						+ "]  描述: " + desc_true_1;
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order_padding5" + "    [索引 "
						+ index + "]  描述: " + desc_true_1;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false" + "    [索引 " + index
						+ "]  描述:" + desc_false;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false_order" + "    [索引 " + index
						+ "]  描述:" + desc_false_1;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false_order_padding5" + "    [索引 "
						+ index + "]  描述:" + desc_false_pading1;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false_order_padding5" + "    [索引 "
						+ index + "]  描述:" + desc_true_pading1;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false_order_10" + "    [指定开始索引 "
						+ index + "]  描述:" + desc_false_2 + "\n";
			}

			return itemDesc;
		}

	}

	// html_1
	/*
	 * 1.读取当前的 html文件 然后把所有的 html文件的 <script> </script> 重新放到 </body> 后面 <script>
	 * </script> </body>
	 */

	class HTML_Rule_1 extends Basic_Rule {

		HTML_Rule_1() {
			super("html", 1, 1);
		}

		String applyOperationRule(String origin) {
			StringBuilder sb = new StringBuilder();
			if (origin.contains("<script>") && origin.contains("</script>") && origin.contains("</body>")
					&& origin.indexOf("</body>") > origin.indexOf("<script>") && // <script> </body> // script 索引小于
					// </body>的索引
					origin.indexOf("</script>") == origin.lastIndexOf("</script>")) { // 只包含一个 </script>
				int scriptBegin = origin.indexOf("<script>");
				int scriptEnd = origin.indexOf("</script>") + "</script>".length();
				int bodyEnd = origin.indexOf("</body>");

				String script = origin.substring(scriptBegin, scriptEnd);
				String result = origin.replace(script, "");
				result = result.replace("</body>", "</body>" + "\n" + script);

				sb.append(result);
			} else {
				sb.append(origin);
			}
			return sb.toString();

		}

		String simpleDesc() {
			return " 读取当前的 html文件  然后把所有的 html文件的 <script> </script>  重新放到 </body> 后面";
		}

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
				String secondNewName = newName.replace(fileNameNoPoint, fileNameNoPoint + "_" + getTimeStampLong());
				System.out.println(
						oldName + " 转为 " + newFilePath + " 失败1次！ 尝试使用新名称 secondNewName=[" + secondNewName + "]");
				String newSecondPath = curFile.getParent() + File.separator + secondNewName;
				File secondFile = new File(newSecondPath);
				flag = curFile.renameTo(secondFile);
				if (flag) {
					System.out.println(oldName + " 第二次转为 " + newFilePath + " 成功！");
				} else {
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
				BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
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

	public void UTF8File_To_ANSIFile(File file) {
		StringBuffer buffer = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			br.skip(1);
			while ((line = br.readLine()) != null) {
				buffer.append(line);
				buffer.append("\r\n");
			}
			buffer.delete(buffer.length() - 2, buffer.length());
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(buffer);
		try {
			FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());
			OutputStreamWriter osw = new OutputStreamWriter(fos);
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

	public static String ReadFileContent_No_UTF8(File mFilePath) {

		if (mFilePath != null && mFilePath.exists()) {
			// System.out.println("存在 当前文件 "+ mFilePath.getAbsolutePath());
		} else {
			System.out.println("不存在 当前文件 " + mFilePath.getAbsolutePath());

			return null;
		}
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath)));
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

	public static String ReadFileContent_GBK(File mFilePath) {

		if (mFilePath != null && mFilePath.exists()) {
			// System.out.println("存在 当前文件 "+ mFilePath.getAbsolutePath());
		} else {
			System.out.println("不存在 当前文件 " + mFilePath.getAbsolutePath());

			return null;
		}
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "GBK"));
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

	public static String ReadFileContent_UTF8(File mFilePath) {

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
			System.out.println("不存在 当前文件  ReadFileContentAsList  mFilePath = null "  );

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
				System.out.println("新建文件夹: file=" + file.getAbsolutePath());

				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

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
		if(dirFile == null) {
			return null;
		}

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
						if ("*".equals(type) || "#".equals(type)) { // 如果 类型是 * 那么就把 所有的 非目录文件加入列表中
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
			String desc = itemRule.ruleTip(type, index, G2_Bat_Name, CUR_OS_TYPE);

			/*
			 * String itemDesc = ""; if(curOS_TYPE == OS_TYPE.Windows){ itemDesc =
			 * "zrule_apply_G2.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
			 * }else{ itemDesc = "zrule_apply_G2 "+type+"_"+index +
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

		mG2_Object = new G2_ApplyRuleFor_TypeFile();
		mG2_Object.InitRule();

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

	static String getTimeStamp_HHmmss() {

		SimpleDateFormat df = new SimpleDateFormat("HHmmss");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getTimeStamp_YYYYMMdd() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
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
		File targetDirParentFile = target.getParentFile();
		try {
			if (targetDirParentFile != null && !targetDirParentFile.exists()) {
				targetDirParentFile.mkdirs();
			}
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

	/**
	 * 计算转换后目标矩形的宽高
	 *
	 * @param src   源矩形
	 * @param angel 角度
	 * @return 目标矩形
	 */
	static Rectangle CalcRotatedSize(Rectangle src, int angel) {
		double cos = Math.abs(Math.cos(Math.toRadians(angel)));
		double sin = Math.abs(Math.sin(Math.toRadians(angel)));
		int des_width = (int) (src.width * cos) + (int) (src.height * sin);
		int des_height = (int) (src.height * cos) + (int) (src.width * sin);
		return new Rectangle(new Dimension(des_width, des_height));
	}

	/**
	 * 旋转角度
	 *
	 * @param src   源图片
	 * @param angel 角度
	 * @return 目标图片
	 */
	public static BufferedImage rotate(Image src, int angel) {
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		// calculate the new image size

		Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);

		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = res.createGraphics();
		// transform(这里先平移、再旋转比较方便处理；绘图时会采用这些变化，绘图默认从画布的左上顶点开始绘画，源图片的左上顶点与画布左上顶点对齐，然后开始绘画，修改坐标原点后，绘画对应的画布起始点改变，起到平移的效果；然后旋转图片即可)

		g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);

		g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

//        //先旋转（以目标区域中心点为旋转中心点，源图片左上顶点对准目标区域中心点，然后旋转）
//        g2.translate(rect_des.width/2,rect_des.height/ 2);
//        g2.rotate(Math.toRadians(angel));
//        //再平移（原点恢复到源图的左上顶点处（现在的右上顶点处），否则只能画出1/4）
//        g2.translate(-src_width/2,-src_height/2);

		g2.drawImage(src, null, null);
		return res;
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

	/**
	 * 汉字转为拼音 空间以下划线_分割 1.每个汉字前面添加_ 2.每个汉字后面添加_ 3.把所有的两个__ 下划线转为 一个下划线
	 *
	 * @param chinese
	 * @return
	 */
	public static String ToPinyin(String chinese) {
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

//                    System.out.println("xxxxxxxxxx");
//                    System.out.println("newChar["+i+"] = "+ newChar[i]);
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
//                    System.out.println("pinyinStr = "+ pinyinStr);
//                    System.out.println("xxxxxxxxxx ");
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else { // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
				pinyinStr += newChar[i];
			}
		}
		while (pinyinStr.contains("__")) {
			pinyinStr = pinyinStr.replaceAll("__", "_");
//            System.out.println("pinyinStr1 = " + pinyinStr);
		}

		while (pinyinStr.contains("u:")) { // 女转为 nu: 绿 lu: 需要转为 nv lv
			pinyinStr = pinyinStr.replaceAll("u:", "v");
//            System.out.println("pinyinStr1 = " + pinyinStr);
		}

		while (pinyinStr.startsWith("_")) {
			pinyinStr = pinyinStr.substring(1, pinyinStr.length());
//            System.out.println("pinyinStr2 = " + pinyinStr);
		}
		while (pinyinStr.endsWith("_")) {
			pinyinStr = pinyinStr.substring(0, pinyinStr.length() - 1);
//            System.out.println("pinyinStr3 = " + pinyinStr);
		}
		return pinyinStr;
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

	static String getFirstZiMu(String srcStr) {
		String firstZimu = "U"; // 默认为 Unknow;
		if (srcStr == null || "".equals(srcStr.trim())) {
			return firstZimu;
		}

		if (!isContainChinese(srcStr)) { // 如果 不包含中文 那么 取这个词的 第一个字符
			String char_1 = srcStr.substring(0, 1).toUpperCase();
			firstZimu = char_1;

		} else {
			String char_1 = srcStr.substring(0, 1).toUpperCase();
//			System.out.println("X2 char_1 = "+char_1 );
			if (!isContainChinese(char_1)) {
				firstZimu = char_1;
			} else { // 如果第一个字母为汉字 那么取到这个字的 拼音的 第一个词
				String pinyinStr = ToPinyin(char_1);
//				System.out.println("X2  pinyinStr = "+pinyinStr );
				String char_1_fixed = pinyinStr.substring(0, 1).toUpperCase();
				firstZimu = char_1_fixed;
			}

		}

//		System.out.println("X3  firstZimu = "+firstZimu );
		return firstZimu;

	}

	/**
	 * 删除文件，可以是文件或文件夹
	 *
	 * @param fileName 要删除的文件名
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
	 * @param fileName 要删除的文件的文件名
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
	 * @param dir 要删除的目录的文件路径
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
				flag = deleteDirectory(files[i].getAbsolutePath());
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

	/**
	 * 图片翻转时，计算图片翻转到正常显示需旋转角度
	 */
	public static boolean getRotateAngleForPhoto(String fileName) {

		boolean isPort = true;
		File file = new File(fileName);

		int angel = 0;
		Metadata metadata;

		try {
			metadata = JpegMetadataReader.readMetadata(file);
			metadata.getDirectories();

			// zukgit_directory [Exif IFD0] - Orientation = Right side, top (Rotate 90 CW)
			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					// 格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
					System.out.format("zukgit_directory  [%s] - %s = %s\n", directory.getName(), tag.getTagName(),
							tag.getDescription());

					if ("Exif IFD0".equals(directory.getName())) {
						String orientation = directory.getString(ExifIFD0Directory.TAG_ORIENTATION);
						System.out.println("ZZOrientation = " + orientation);

						if ("1".equals(orientation)) { // 90度 和 270度 宽高对调了
							angel = 0;

						} else if ("6".equals(orientation)) {
							// 6旋转90
							angel = 90;
							isPort = false;
						} else if ("3".equals(orientation)) {
							// 3旋转180
							angel = 180;
						} else if ("8".equals(orientation)) {
							// 8旋转90
							angel = 270;
							isPort = false;
						}
						return isPort;
					}
				}
//				if (directory.hasErrors()) {
//					for (String error : directory.getErrors()) {
//						System.err.format("ERROR: %s", error);
//					}
//				}
			}

			/*
			 * if(directory.containsTag(ExifDirectory.TAG_ORIENTATION)){ // Exif信息中方向 int
			 * orientation = directory.getInt(ExifDirectory.TAG_ORIENTATION); // 原图片的方向信息
			 * if(6 == orientation ){ //6旋转90 angel = 90; }else if( 3 == orientation){
			 * //3旋转180 angel = 180; }else if( 8 == orientation){ //8旋转90 angel = 270; } }
			 */

		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("图片旋转角度：" + angel);
		return isPort;
	}

	public static String getMainPageHtmlCode(String url) {

		File ChromeDriverFile = new File(zbinPath + File.separator + "G2_chromedriver_v91.exe");

		ChromeOptions CUR_CHROME_OPTIONS = new ChromeOptions();
		// 驱动位置
		CUR_CHROME_OPTIONS.addArguments("--start-fullscreen");

//			CUR_CHROME_OPTIONS.addArguments("Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//			CUR_CHROME_OPTIONS.addArguments("Accept-Encoding=gzip, deflate, sdch");
//			CUR_CHROME_OPTIONS.addArguments("Accept-Language=zh-CN,zh;q=0.8");
//			CUR_CHROME_OPTIONS.addArguments("Connection=keep-alive");
//			CUR_CHROME_OPTIONS.addArguments("Host=activityunion-marketing.meituan.com");
//			CUR_CHROME_OPTIONS.addArguments("Upgrade-Insecure-Requests=1");
//			CUR_CHROME_OPTIONS.addArguments("User-Agent=Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4");

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

	static String getTimeStampyyyyMMdd_HHmmss() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static String getYear_Month_Day() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}

	static File getPCScreenFile() {

		// H7_Email_WorkPlace
		// 把截图放到 C:\Users\zhuzj5\Desktop\zbin\H7_Email_WorkPlace\yyMMdd_hhmmss 文件夹中

		String timestamp = getTimeStamp_YYYYMMdd();
		String H7_WorkPlace_TaskDir = zbinPath + File.separator + "G2_Monitor_Download" + File.separator
				+ getTimeStamp_YYYYMM();

		File taskDirFile = new File(H7_WorkPlace_TaskDir);
		if (!taskDirFile.exists()) {
			taskDirFile.mkdirs();
		}

		File imageFIle = null;
		String imageName = "pc_screen_" + getTimeStampyyyyMMdd_HHmmss() + ".png";
		String imageAbsPath = H7_WorkPlace_TaskDir + File.separator + imageName;

		imageFIle = new File(imageAbsPath);

		if (!imageFIle.exists()) {
			try {
				imageFIle.createNewFile();
			} catch (Exception e) {

			}
		}

		// ----------------Image Begin---------
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int height = gd.getDisplayMode().getHeight();
		int width = gd.getDisplayMode().getWidth();
		Rectangle screenRect = new Rectangle(0, 0, width, height);
		cn.hutool.core.swing.ScreenUtil.captureScreen(screenRect, imageFIle);
//----------------Image End---------

		System.out.println("PC截图路径: " + imageAbsPath);
		if (!imageFIle.exists() || imageFIle.length() < 100) {
			return null;
		}

		return imageFIle;
//        File newImage_XY_File =   paintXYFromImageFile(imageFIle);
//        return newImage_XY_File;
	}

	static boolean isImageFile(File imageFIle) {
		boolean flag = false;
		if (imageFIle.getName().endsWith(".jpg") || imageFIle.getName().endsWith(".Jpg")
				|| imageFIle.getName().endsWith(".JPG") || imageFIle.getName().endsWith(".png")
				|| imageFIle.getName().endsWith(".Png") || imageFIle.getName().endsWith(".PNG")) {
			flag = true;
		}
		return flag;
	}

	public static String getFileTypeNoPoint(String fileName) {
		String name = "";
		if (fileName.contains(".")) {
			name = fileName.substring(fileName.lastIndexOf(".") + 1).trim().toLowerCase();
		} else {
			name = "";
		}
		return name.toLowerCase().trim();
	}

	static File paintXYFromImageFile(File imageFile) {
		if (!imageFile.exists() || imageFile.length() < 10 || !isImageFile(imageFile)) {
			return null;
		}

		String originName = getFileNameNoPoint(imageFile.getName());
		String imageType = getFileTypeNoPoint(imageFile.getName());
		String newImageFileNameStr = originName + "_xy" + "." + imageType;
		File newImageFile = new File(
				imageFile.getParentFile().getAbsolutePath() + File.separator + newImageFileNameStr);

		try {
			if (!newImageFile.exists()) {
				newImageFile.createNewFile();
			}
			BufferedImage bi = ImageIO.read(imageFile);
			int height = bi.getHeight();
			int width = bi.getWidth();

			int up_line_num = width / 10; // 竖线的数量
			int down_line_num = height / 10; // 横线的数量

			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			g2.setColor(Color.RED);// 设置颜色
			g2.setStroke(new BasicStroke(1.5f)); // 直线粗细
			Font f = new Font("宋体", Font.BOLD, 20);
			g2.setFont(f); // 设置字体:字体、字号、大小
			for (int i = 0; i < down_line_num; i++) { // 横线的数量
				int down_origin_x_item = 0;
				int down_origin_y_item = 100 * i + 100;
				int down_new_x_item = width;
				int down_new_y_item = down_origin_y_item;

				int text_X = down_origin_x_item + 10; //
				int text_y = down_origin_y_item - 10; //
				g2.drawString(down_origin_y_item + "", text_X, text_y);
				g2.drawLine(down_origin_x_item, down_origin_y_item, down_new_x_item, down_new_y_item);
			}

			for (int i = 0; i < up_line_num; i++) { // 竖线的数量

				int down_origin_x_item = 100 * i + 100;
				int down_origin_y_item = 0;
				int down_new_x_item = down_origin_x_item;
				int down_new_y_item = height;
				int text_X = down_origin_x_item - 50; //
				int text_y = down_origin_y_item + 20;
				g2.drawString(down_origin_x_item + "", text_X, text_y);
				g2.drawLine(down_origin_x_item, down_origin_y_item, down_new_x_item, down_new_y_item);
			}
			ImageIO.write(bi, imageType, new FileOutputStream(newImageFile));
			System.out.println("程序执行结束!");
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("异常=" + e);
			return imageFile; // 出现异常 那么就不操作 x y 坐标了
		}
		return newImageFile;
	}

    @SuppressWarnings("unchecked")
    public static void sendemail(boolean isFirstMonitor , String PcName, String targetEMail, String mTitle, String inputContent,
                                 ArrayList<String> paramTextList, ArrayList<String> extraTextList, HashMap<File, String> imageFile_Desc_Map,
                                 ArrayList<File> attatchFileList) throws Exception {
    	
    	
    	
    	
    	//  isFirstMonitor   如果是  第一个 邮件 那么 正文的 一部分设置成绿色 
        // 给用户发送邮件的邮箱
        String from = "382581427@qq.com";
        // 邮箱的用户名
        String username = "382581427@qq.com";
        // 邮箱授权码 // kyioxkexvqdtbjhd【新】 pwkvngnpfkvpbgcd【旧】

        String password = "kyioxkexvqdtbjhd";
        // 发送邮件的服务器地址，QQ服务器
        String host = "smtp.qq.com";
        // 接收人邮箱
        String to = targetEMail;
        if (to == null || "".equals(to.trim())) {
            to = "zukgit@foxmail.com";
        }

        // 邮件主题
//        String title = "邮件主题[测试混合邮件]";
        String title = mTitle;
        if (title == null || "".equals(title.trim())) {
            title = "YYYYHHDD";
        }

        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com"); // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名和密码

        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        javax.mail.Session session = javax.mail.Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
//       session.setDebug(true);

        // 通过 session 得到 transport 对象
        javax.mail.Transport ts = session.getTransport();

        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);

        // 创建邮件，写邮件
        // 需要传递 session
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new javax.mail.internet.InternetAddress(from)); // 指明邮件的发件人
        message.setRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to)); // 指明邮件的收件人
        message.setSubject(mTitle); // 邮件主题

        // =========================================== 配置开始
        // ==============================
        // 遍历 imageFile_Desc_Map

        StringBuilder extraSB = new StringBuilder();
        if (extraTextList != null && extraTextList.size() > 0) { // 一些 额外的信息
            extraSB.append("<br><br>═══════ 副文开始 ═══════<br><br>");
            for (int i = 0; i < extraTextList.size(); i++) {
                extraSB.append(extraTextList.get(i) + "<br>");
            }
            extraSB.append("<br><br>═══════ 副文结束 ═══════<br><br>");
        }

        StringBuilder paramSB = new StringBuilder();
        if (paramTextList != null && paramTextList.size() > 0) { // 一些 额外的信息

            for (int i = 0; i < paramTextList.size(); i++) {
                paramSB.append(paramTextList.get(i) + "<br>");
            }

        }

        ArrayList<MimeBodyPart> image_desc_MimeBodyPartList = new ArrayList<MimeBodyPart>();

        MimeBodyPart body_content = new MimeBodyPart(); // 文字内容
    	File lastAttatchFile =  null ;
    	if(attatchFileList != null && attatchFileList.size() > 0 ) {
    		
    		 lastAttatchFile = attatchFileList.get(attatchFileList.size()-1);
    		 
    	}
    	
        if(isFirstMonitor) {
        	
            body_content.setContent("<font color=\"green\">"+inputContent + "</font><br><br>" + PcName + " " + getTimeStampyyyyMMdd_HHmmss()+" LastFile:"+lastAttatchFile
            + "<br>═1═1═1═1═1═1═ 正文结束 ═1═1═1═1═1═1═<br>" + "" + "<br>" + paramSB.toString() + "<br>═1═1═1═1═1═1═ 参数结束 ═1═1═1═1═1══<br><br>"
            + extraSB.toString(), "text/html;charset=utf-8");

        }else {

            body_content.setContent(inputContent + "<br><br>" + PcName + " " + getTimeStampyyyyMMdd_HHmmss()+" LastFile:"+lastAttatchFile
            + "<br>═══════ 正文结束 ═══════<br>" + "" + "<br>" + paramSB.toString() + "<br>═══════ 参数结束 ═══════<br><br>"
            + extraSB.toString(), "text/html;charset=utf-8");
        }

        image_desc_MimeBodyPartList.add(body_content);

        if (imageFile_Desc_Map != null) {

            Map.Entry<File, String> entryItem;
            int item_index = 0;
            int map_size = 0;
            map_size = imageFile_Desc_Map.size();
            Iterator iterator = imageFile_Desc_Map.entrySet().iterator();
            while (iterator.hasNext()) {
                entryItem = (Map.Entry<File, String>) iterator.next();
                File key_file = entryItem.getKey(); // Map的Key
                String value_desc = entryItem.getValue(); // Map的Value
                if (key_file == null) {
                    System.out.println("map[" + item_index + "][" + map_size + "]____" + "key[" + key_file + "]-value["
                            + value_desc + "]" + " 为空! ");
                    continue;
                }

                if (!key_file.exists()) {
                    System.out.println("map[" + item_index + "][" + map_size + "]____" + "key[" + key_file + "]-value["
                            + value_desc + "]" + " 不存在! ");
                    continue;
                }
                System.out.println("map[" + item_index + "][" + map_size + "]____" + "key[" + key_file + "]-value["
                        + value_desc + "]");

                MimeBodyPart body_image = new MimeBodyPart();
                body_image.setDataHandler(new javax.activation.DataHandler(
                        new javax.activation.FileDataSource(key_file.getAbsolutePath())));
                body_image.setContentID(key_file.getName());

                // 文本
                // " 图片描述:fafa <img src='cid:1.png'>", "text/html;charset=utf-8"
//   		        body2.setContent("我不是广告，<img src='cid:1.png'>", "text/html;charset=utf-8");
                String image_item_desc = value_desc + " <img src='cid:" + key_file.getName() + "'>";
                MimeBodyPart body_text = new MimeBodyPart();
                body_text.setContent(image_item_desc, "text/html;charset=utf-8");

                image_desc_MimeBodyPartList.add(body_image);
                image_desc_MimeBodyPartList.add(body_text);

                item_index++;
            }
        }

        MimeMultipart image_desc_MimeMultipart = null;
        if (image_desc_MimeBodyPartList != null && image_desc_MimeBodyPartList.size() > 0) {
            image_desc_MimeMultipart = new MimeMultipart();

            for (int i = 0; i < image_desc_MimeBodyPartList.size(); i++) {
                image_desc_MimeMultipart.addBodyPart(image_desc_MimeBodyPartList.get(i));
            }
            image_desc_MimeMultipart.setSubType("related"); // 文本和图片内嵌成功
        }

        ArrayList<MimeBodyPart> attatch_MimeBodyPartList = new ArrayList<MimeBodyPart>();

        if (attatchFileList != null && attatchFileList.size() > 0) {

            for (int i = 0; i < attatchFileList.size(); i++) {
                File attatchFile = attatchFileList.get(i);

                MimeBodyPart body_attatch = new MimeBodyPart();
                body_attatch.setDataHandler(new javax.activation.DataHandler(
                        new javax.activation.FileDataSource(attatchFile.getAbsolutePath())));
                body_attatch.setFileName(attatchFile.getName());
                attatch_MimeBodyPartList.add(body_attatch);
            }

        }

        // 将拼装好的正文内容设置为主体
        MimeBodyPart contentText = new MimeBodyPart();
        if (image_desc_MimeMultipart != null) {
            contentText.setContent(image_desc_MimeMultipart);
        }

        // 拼接附件与正文内容
        MimeMultipart all_MimeMultipart = new MimeMultipart();
        all_MimeMultipart.addBodyPart(contentText);
        for (int i = 0; i < attatch_MimeBodyPartList.size(); i++) {
            all_MimeMultipart.addBodyPart(attatch_MimeBodyPartList.get(i)); // 添加附件
        }
        all_MimeMultipart.setSubType("mixed");

        // =========================================== 配置结束
        // ==============================
        // 设置到消息中，保存修改
        message.setContent(all_MimeMultipart); // 把最后编辑好的邮件放到消息当中
        message.saveChanges(); // 保存修改

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        System.out.println("sendemail finish__________");
        
        // 释放资源
        ts.close();

    }

	static ArrayList<String> getWifiInfoList() {
		ArrayList<String> wifiInfoList = new ArrayList<String>();
		String osName = System.getProperties().getProperty("os.name").toLowerCase();
		File wifiLogFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop"
				+ File.separator + "zbin" + File.separator + "E2_WifiDetail.txt");

		if (osName != null && osName.toLowerCase().contains("win")) { // windows 系统
			String word1 = "上的配置文件"; // 接口 WLAN 上的配置文件 debugtheworld:
			String word2 = "关键内容"; // 关键内容 : 12345678

			String curMatchContent = "";
			String tip = "";
			if (wifiLogFile.exists()) {
				String UTF8_Conent = ReadFileContent_No_UTF8(wifiLogFile);
				String Default_Conent = ReadFileContent_No_UTF8(wifiLogFile);
				String GBK_Conent = ReadFileContent_GBK(wifiLogFile);

				if (UTF8_Conent.contains(word2)) {
					curMatchContent = UTF8_Conent;
					tip = "UTF8_Conent";
				} else if (Default_Conent.contains(word2)) {
					curMatchContent = Default_Conent;
					tip = "Default_Conent";
				} else if (GBK_Conent.contains(word2)) {
					curMatchContent = GBK_Conent;
					tip = "GBK_Conent";
				}

			}

			System.out.println("tipA[" + tip + "]  curMatchContent=" + curMatchContent);

			ArrayList<WifiItem> wifiItemList = new ArrayList<WifiItem>();

			// 如果 有 就 读取 txt 文件 ,
			if (wifiLogFile.exists() && curMatchContent.contains(word2)) {
				// System.out.println("wifiLogFile 文件存在");
				readWifiObjectFromString(curMatchContent, wifiItemList);
				ArrayList<String> wifiList = transactWifiList(wifiItemList);

				System.out.println(" Read-Wifi_Success   wifiList.size()=" + wifiList.size());

				if (wifiList != null && wifiList.size() > 0) {

					for (int i = 0; i < wifiList.size(); i++) {
						String wifiStr = wifiList.get(i);
						if (!wifiInfoList.contains(wifiStr)) {
							wifiInfoList.add(wifiStr);
						}
					}

				}

//                return wifiList;   // 这里不返回   因为  有可能 当前 不是最新的数据

			}

			if (!wifiLogFile.exists() || !curMatchContent.contains(word2)) { // 如果 不存在 那么 执行 相关命令得到 对应的文件 执行
																				// E2_WifiDetail_Exit.bat 文件
				File E2_WifiDetail_Bat_File = new File(System.getProperties().getProperty("user.home") + File.separator
						+ "Desktop" + File.separator + "zbin" + File.separator + "E2_WifiDetail_Exit.bat");

				System.out.println("执行 Bat 文件 " + E2_WifiDetail_Bat_File.getAbsolutePath() + " Begin");
				execCMD(E2_WifiDetail_Bat_File.getAbsolutePath());
				System.out.println("执行 Bat 文件 " + E2_WifiDetail_Bat_File.getAbsolutePath() + " End");

				try {
					Thread.sleep(1500); // 睡 1.5 秒
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				boolean wifiLogExist = wifiLogFile.exists();
				boolean isContainWord = false;
				String wifiLog_Content = "";

				if (wifiLogExist) {
					String UTF8_Conent = ReadFileContent_No_UTF8(wifiLogFile);
					String Default_Conent = ReadFileContent_No_UTF8(wifiLogFile);
					String GBK_Conent = ReadFileContent_GBK(wifiLogFile);

					if (UTF8_Conent.contains(word2)) {
						curMatchContent = UTF8_Conent;
						tip = "UTF8_Conent";
					} else if (Default_Conent.contains(word2)) {
						curMatchContent = Default_Conent;
						tip = "Default_Conent";
					} else if (GBK_Conent.contains(word2)) {
						curMatchContent = GBK_Conent;
						tip = "GBK_Conent";
					}

					wifiLog_Content = ReadFileContent(wifiLogFile);
					isContainWord = curMatchContent.contains(word2);
				}

				boolean conainWord = curMatchContent.contains(word2);
				System.out.println(
						"tipB[" + tip + "]  conainWord=" + conainWord + "   curMatchContent=" + curMatchContent);

				if (wifiLogFile.exists() && curMatchContent.contains(word2)) {
					// System.out.println("wifiLogFile 文件存在");
					readWifiObjectFromString(curMatchContent, wifiItemList);
					ArrayList<String> wifiList = transactWifiList(wifiItemList);
					System.out.println("getWifiInfoList() __A__  wifiList.size= " + wifiList.size() + "wifiLogExist = "
							+ wifiLogExist + "isContainWord=" + isContainWord + "  curMatchContent=" + curMatchContent);

					if (wifiList != null && wifiList.size() > 0) {
						wifiList.addAll(wifiList);
					}

//	              	return wifiList;

				}
				System.out.println("getWifiInfoList() __B__  getWifiInfoList().size= " + wifiInfoList.size()
						+ "   wifiLogExist = " + wifiLogExist + "  isContainWord=" + isContainWord
						+ "  wifiLog_Content=" + wifiLog_Content);

			}

		} else if (osName != null && osName.toLowerCase().contains("mac")) { // mac 的

			ArrayList<String> wifiListMac = new ArrayList<String>();
			wifiListMac.add("MAC系统访问WIFI密码: 请打开LaunchPad 》 其他 》 钥匙串访问 》 系统(right) 》 airport网络密码");
			return wifiListMac;

		} else { // linx

			String wifiPath = "/etc/NetworkManager/system-connections";
			File wifiDir = new File(wifiPath);
			if (!wifiDir.exists()) {
				System.out.println("WiFi-Path: " + wifiPath + "  不存在!");
				return null;
			}

			File[] wifiList = wifiDir.listFiles();
			ArrayList<String> wifiLogList = new ArrayList<String>();
			for (int i = 0; i < wifiList.length; i++) {
				int index = i + 1;
				String str0 = "WIFI索引:" + index;
				wifiLogList.add(str0);
				ArrayList<String> curWifiItemLog = translateLinuxWifi(wifiList[i], i, wifiList.length);
				wifiLogList.addAll(curWifiItemLog);
			}
			wifiInfoList.addAll(wifiLogList);

		}
		System.out.println("getWifiInfoList() __C__  getWifiInfoList().size= " + wifiInfoList.size());

		return wifiInfoList;

	}

	public static synchronized void readWifiObjectFromString(String fileContent, ArrayList<WifiItem> wifiItemList) {
		StringBuilder sb = new StringBuilder();
		String word1 = "上的配置文件"; // 接口 WLAN 上的配置文件 debugtheworld:
		String word2 = "关键内容"; // 关键内容 : 12345678

		try {
			// BufferedReader curBR = new BufferedReader(new InputStreamReader(new
			// FileInputStream(fileItem), "utf-8"));

			String lineContent = "";

			String wifiName = "";
			String wifiKey = "";
			boolean wifiNameReadyFlag = false;
			boolean wifiKeyReadyFlag = false;

			String[] lineArr = fileContent.split("\n");

			for (int i = 0; i < lineArr.length; i++) {
				lineContent = lineArr[i].trim();

				if (lineContent.contains(word1)) {
					wifiName = lineContent
							.substring(lineContent.indexOf(word1) + word1.length(), lineContent.length() - 1).trim();
					wifiNameReadyFlag = true;
					wifiKeyReadyFlag = false;
					wifiKey = "";
				}

				if (lineContent.contains(word2)) {
					wifiKey = lineContent.substring(lineContent.indexOf(word2) + word2.length()).trim();
					wifiKey = wifiKey.substring(1).trim();
					if (wifiNameReadyFlag) {
						wifiKeyReadyFlag = true;
					}
					if (wifiNameReadyFlag && wifiNameReadyFlag) {
						WifiItem wifiItem = new WifiItem(wifiName, wifiKey);
						wifiItemList.add(wifiItem);
						wifiNameReadyFlag = false;
						wifiKeyReadyFlag = false;
					}
				}

			}

		} catch (Exception e) {
			System.out.println("readWifiObjectFromString Exception e=" + e);
		}

	}

	public static ArrayList<String> transactWifiList(ArrayList<WifiItem> wifiList) {
		ArrayList<String> strArr = new ArrayList<String>();

		for (int i = 0; i < wifiList.size(); i++) {
			WifiItem item = wifiList.get(i);
			int index = i + 1;
			String str0 = "WIFI索引:" + index;
			String str1 = "WIFI名称:" + item.getName();
			String str2 = "WIFI密码:" + item.getKey();
			strArr.add("wifi[" + i + "][" + wifiList.size() + "] wifi名称[" + item.getName() + "] wifi密码[" + item.getKey()
					+ "]");

		}
		return strArr;

	}

	public static ArrayList<String> translateLinuxWifi(File wifiFile, int index, int count) {
		ArrayList<String> strArr = new ArrayList<String>();
		String wifiName = wifiFile.getName();
		String wifiPsk = "";
		String fileContent = readStringFromFile(wifiFile);

		if (!fileContent.contains("psk=") || "".equals(fileContent)) {
			wifiPsk = "无权限访问wifi文件:" + wifiFile.getAbsolutePath();
		} else {
			String mWifiPassword = fileContent.substring(fileContent.indexOf("psk=")).trim();
			mWifiPassword = mWifiPassword.substring(0, mWifiPassword.indexOf("[")).trim();
			mWifiPassword = mWifiPassword.replace("psk=", "").trim();
			wifiPsk = mWifiPassword;

		}
//       psk=zhuminghe
//[ipv4]

		if (null == wifiPsk || "".equals(wifiPsk)) {
			wifiPsk = "[无密码]";
		}
		String str1 = "WIFI名称:" + wifiName;
		String str2 = "WIFI密码:" + wifiPsk;
		String str3 = "==================";

		strArr.add("wifi[" + index + "][" + count + "] wifi名称[" + wifiName + "] wifi密码[" + wifiPsk + "]");

		return strArr;
	}

	static String readStringFromFile(File fileItem) {
		StringBuilder sb = new StringBuilder();
		try {
			// BufferedReader curBR = new BufferedReader(new InputStreamReader(new
			// FileInputStream(fileItem), "utf-8"));
			BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
			String lineContent = "";
			while (lineContent != null) {
				lineContent = curBR.readLine();
				if (lineContent == null || lineContent.trim().isEmpty()) {
					continue;
				}
				sb.append(lineContent + "\n");
			}
			curBR.close();
		} catch (Exception e) {
		}
		return sb.toString();
	}

	static String long2yyyyMMdd_HHmmss(long millionMs) {

		Calendar mCalendar = Calendar.getInstance();
		mCalendar.setTimeInMillis(millionMs);
		int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH);
		int day = mCalendar.get(Calendar.DAY_OF_MONTH);

		Date date = mCalendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String date_str = sdf.format(date);
		return date_str;
	}

	static class WifiItem {
		String name;
		String key;

		WifiItem() {

		}

		WifiItem(String name, String key) {
			this.name = name;
			this.key = key;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return name;
		}
	}

}