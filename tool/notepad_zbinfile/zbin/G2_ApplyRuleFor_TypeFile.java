
import cn.hutool.core.util.ImageUtil;
import cn.hutool.json.JSONUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
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
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
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

	static File G2_Temp_Text_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name)+"_Temp_Text.txt");


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

		//  23
		realTypeRuleList.add(new CheckFileRealFormat_Rule_23());
		realTypeRuleList.add(new add_Middle_Dir_Rule_24()); // 在当前的目录 与 子目录 之间 新增 一层文件夹 , 文件夹名称任意 用户输入

		// 从上往下打印 年月 标示   接受来自 输入的参数  打开 临时文件
		//  ## 2020
		//  ### 2020.01
		//  ### 2020.02
		// zrule_apply_G2.bat #_25  1992_2021 这样
		realTypeRuleList.add(new Time_Head_Rule_25());
		realTypeRuleList.add(new Rename_By_Dir_Rule_26());
		realTypeRuleList.add(new Rercovery_Type_By_DirName_Rule_27());
		//  把当前的 图片 文件  jpg png 等 转为 一个 PPTX 文件 方便 分享 查看
		realTypeRuleList.add(new makeJpg2PPTX_Rule_28());

		//  把 当前 目录的 多个 pptx 合并成 一个 pptx 文件
		realTypeRuleList.add(new mergeSomePPTXAsOne_Rule_29());

		// 对文件的名称进行修改
		realTypeRuleList.add(new FileRenameOperation_Rule_30());

		// 创建文件 创建模板文件
		realTypeRuleList.add(new MakeFile_E5_Rule_31());

		// 把当前目录的 jpg png 文件转为 一个 pdf文件
		realTypeRuleList.add(new MakeJpg2PDF_Rule_32());


		// 把当前目录mp3 文件进行 属性的更改
		realTypeRuleList.add(new MP3_Prop_Fixed_Rule_33());

		//  把当前的 MP3文件转为  JSON 格式数据 方便 布局 树形 结构
		realTypeRuleList.add(new MP3_Revert2JSOn_Rule_34());
		realTypeRuleList.add(new Append_Pdf_Rule_35()); // 把 pdf 文件 追加 合并为 一个文件
		realTypeRuleList.add(new Seperate_Pdf_Rule_36()); // 把 pdf中指定的页数 分隔出来作为一个新的pdf文件

		realTypeRuleList.add(new Zapp_Zmain_dir_Create_Rule_37()); // 在本地目录创建 /sdcard/zapp  和 /sdcard/zmain 相关的 dir目录

		//  把 当前的 xlsx  xls 文件 转为 对应的 json 文件
		realTypeRuleList.add(new Revert_xlsx2json_Rule_38());


	}

// 3038年 5 月 3 日


	// operation_type 操作类型 1--读取文件内容字符串 进行修改 2--对文件对文件内容(字节)--进行修改 3.对全体子文件进行的随性的操作
	// 属性进行修改(文件名称)
//     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作


	class Revert_xlsx2json_Rule_38 extends Basic_Rule {
		boolean isDirOperation;  // 是否没有输入 xlsx 文件 而是 输入了一个 目录  默认shell 目录 已经 输入的目录 

		File inputDirFile;
		ArrayList<File> xlsxFileList ;
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
				String xlsxFileName =  xlsxFile.getName();
				String getxlsxNameNoType = getFileNameNoPoint(xlsxFileName);
				String resut_json_xlsx_name = xlsxFileName.replace(".", "_")+"_"+getTimeStamp();
//				String resut_json_xlsx_name = getxlsxNameNoType+".json";
				File xlsxFile_resultDir = null ;

				if(isDirOperation && inputDirFile != null) {
					xlsxFile_resultDir = inputDirFile;
					String resultJsonName = getxlsxNameNoType+".json";
					File resultJsonFile = new File(inputDirFile.getAbsolutePath()+File.separator+resultJsonName);
					if(resultJsonFile.exists()) {
						// 如果当前 目录 已经 有 xxxx.xlsx 对应的 xxxxx.json 文件  那么 就不解析 这个 xlsx文件 
						continue;
					}
					 reverXlsxToJson(xlsxFile,xlsxFile_resultDir,true);
				}else {
					// 如果 不是 输入 目录 的 话  那么就创建 xlsx 的 文件名称   如果是目录的话  那么就在当前目录生成 .json 
					 xlsxFile_resultDir = new File(xlsxFile.getParentFile().getAbsolutePath()+File.separator+resut_json_xlsx_name);
					 reverXlsxToJson(xlsxFile,xlsxFile_resultDir,false);
				}
				
			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {

			return  Cur_Bat_Name + " #_"+rule_index+"  <指定A.xlsx文件> <指定B.xls文件>  ### 按顺序解析当前的xlsx xls 为对应的json文件 生成在相同文件名_时间戳的文件夹中   \n"
					+ Cur_Bat_Name + " #_"+rule_index+"  A.xlsx B.xls C.xlsx   ### 按顺序解析当前的xlsx day20200202.xls 为对应的day_20200202.json文件 生成在相同文件名_时间戳的文件夹中    \n"
					+ Cur_Bat_Name + " #_"+rule_index+"   ### 解析当前 shell-path 目录下的xlsx 文件 生成对应的.json 文件  没有直接返回    \n"
					+ Cur_Bat_Name + " #_"+rule_index+"  C:\\Users\\xxx\\Desktop\\zbin\\J0_Data  ### 解析指定目录下的xlsx 文件 生成对应的.json 文件 没有直接返回    \n"

					;
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String strInput = inputParamList.get(i);

				File tempFile = new File(curDirPath + File.separator + strInput);
				if (tempFile.exists() && !tempFile.isDirectory()) {
					String inputFileName = tempFile.getName().toLowerCase();
					if (inputFileName.endsWith(".xlsx") || inputFileName.endsWith(".xls") ) {
						xlsxFileList.add(tempFile);
					}
					
	
				}
				
				File inputDir = new File(strInput);
				if(inputDir.exists() && inputDir.isDirectory()) {
					isDirOperation = true;
					inputDirFile = inputDir;
				}
				
				System.out.println("initParamsWithInputList["+i+"] = "+strInput +"  inputDir.exists()="+inputDir.exists()+"  inputDir.isDirectory()="+inputDir.isDirectory());
				
			}

			if (xlsxFileList.size() == 0 && inputDirFile == null) {
				System.out.println("当前 输入的 xlsx 文件 为 空 无法获取 输入的 xlsx 请检查 输入!! ");
				File shellDir = new File(curDirPath);
				if(shellDir != null && shellDir.exists()) {
					File[]  listArr = shellDir.listFiles();
					
					if(listArr == null || listArr.length ==0) {
						System.out.println("当前 输入的目录  "+inputDirFile.getAbsolutePath()+"没有 任何文件操作!!");

						return false;
					}
					for (int i = 0; i < listArr.length; i++) {
						File fileItem = listArr[i];
						
						String inputFileName = fileItem.getName().toLowerCase();
						if (inputFileName.endsWith(".xlsx") || inputFileName.endsWith(".xls") ) {
							xlsxFileList.add(fileItem);
							isDirOperation = true;
							inputDirFile = shellDir;
						}
						
					}
					
					
					
					
				}else {
					System.out.println("当前 输入的 xlsx 文件为空  shell目录为空 无法获取 输入的 xlsx 请检查 输入!! ");
					return false;
					
				}
				
				
		
			}
			
			if(inputDirFile != null) {
				File[]  listArr = inputDirFile.listFiles();
				if(listArr == null || listArr.length ==0) {
					System.out.println("当前 输入的目录  "+inputDirFile.getAbsolutePath()+"没有 任何文件操作!!");

					return false;
				}
				for (int i = 0; i < listArr.length; i++) {
					File fileItem = listArr[i];
					
					String inputFileName = fileItem.getName().toLowerCase();
					if (inputFileName.endsWith(".xlsx") || inputFileName.endsWith(".xls") ) {
						xlsxFileList.add(fileItem);
					}
					
				}
				

			}


			if(xlsxFileList.size() == 0  ) {
				System.out.println("当前 输入的目录  inputDirFile ="+( inputDirFile == null ? "null":inputDirFile.getAbsolutePath() )+"没有 任何文件的 .xls .xlsx文件进行操作!!");

				return false;
			
			}
			if(inputDirFile == null) {
				System.out.println("ZXX inputDirFile = null " +    " xlsxFileList.size()="+xlsxFileList.size()+"   isDirOperation="+isDirOperation);
	
			}else {
				System.out.println("ZXX inputDirFile ="+inputDirFile.getAbsolutePath() +"    xlsxFileList.size()="+xlsxFileList.size()+"   isDirOperation="+isDirOperation);

			}
			
			

			
			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}



		public  void reverXlsxToJson(File xlsxFile , File jsonResultDirFile,boolean isDirOperation) {
			try {
				FileInputStream inp = new FileInputStream(xlsxFile.getAbsolutePath());

				Workbook workbook = null;
//		            Workbook workbook = WorkbookFactory.create(inp);

				if (xlsxFile.getName().toLowerCase().trim().endsWith(".xls"))
				{
					workbook = new HSSFWorkbook(inp);
				}
				else
				{
//			             workbook = WorkbookFactory.create(inp);
					workbook = new XSSFWorkbook(inp);
				}


				//获取sheet数
				int sheetNum = workbook.getNumberOfSheets();
				System.out.println("Shell_count = "+ sheetNum);
				JSONObject jsonObject = new JSONObject();
				for (int s = 0; s < sheetNum; s++) {
					// Get the Sheet of s.
					Sheet sheet = workbook.getSheetAt(s);
					//获取最大行数
					String shellName =  sheet.getSheetName();

					int rownum = sheet.getPhysicalNumberOfRows();
					if (rownum <= 1) {
						continue;
					}

					//获取第一行
					Row row1 = sheet.getRow(0);
					//获取最大列数
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
							//循环列
							for (int j = 0; j < colnum; j++) {
								Cell cellData = row.getCell(j);
								if (cellData != null) {
									//判断cell类型
//									System.out.println("colum="+j);

									switch (cellData.getCellType()) {
										case NUMERIC: {
											rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getNumericCellValue());
											break;
										}
										case FORMULA: {
											//判断cell是否为日期格式
											if (DateUtil.isCellDateFormatted(cellData)) {
												//转换为日期格式YYYY-mm-dd
												rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getDateCellValue());
											} else {
												//数字
												rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getNumericCellValue());
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

											} catch(Error e) {
												cellContent ="";

											}


											rowObj.put(row1.getCell(j).toString(), cellContent);

											// 表头 是 富文本 的 时候 调用 	  getRichStringCellValue()  和 getStringCellValue() 报错!!!
											// Exception in thread "main" java.lang.NoSuchMethodError: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst.xgetT()
											// Lorg/openxmlformats/schemas/officeDocument/x2006/sharedTypes/STXstring;

											// rowObj.put(row1.getCell(j).getRichStringCellValue().toString(), cellData.getRichStringCellValue());
											// rowObj.put(row1.getCell(j).getStringCellValue().toString(), cellData.getStringCellValue());

											break;
										}
										default:
											rowObj.put(row1.getCell(j).getStringCellValue(), "");
									}
								} else {
									rowObj.put(row1.getCell(j).getStringCellValue(), "");

								}
							}
							jsonArray.add(rowObj);
						}
					}
					String jsonName = sheet.getSheetName()+".json";
					
					if(!isDirOperation) {   //  如果是 单一的 输入 xlsx 文件 那么才 输出 sheetname.json
						File jsonFile = new File(jsonResultDirFile.getAbsolutePath()+File.separator+jsonName);

						System.out.println(jsonArray.toJSONString());
			
						writeContentToFile(jsonFile, jsonArray.toJSONString()+"\n");
					}
	
					
					
					jsonObject.put(sheet.getSheetName(), jsonArray);
				}
//		            System.out.println(jsonObject.toJSONString());
//				System.out.println("长度"+jsonObject.toJSONString().length());
//				String allSheetJsonFileName = "AllSheet_"+xlsxFile.getName().replace(".", "_")+".json";
			
				String getxlsxNameNoType = getFileNameNoPoint(xlsxFile);
				String resut_json_file_name = getxlsxNameNoType+".json";
				ArrayList<String> jsonRowList = new ArrayList<String>();

		
				
				
				String JsonOneLine = jsonObject.toJSONString();  //  把它分隔多份  不要总是一行
				
				// 有都逗号 就换行 
				JsonOneLine = JsonOneLine.replace(",", ",\n");
				
				File allSheetJsonFile =  new File(jsonResultDirFile.getAbsolutePath()+File.separator+resut_json_file_name);
			
				
				writeContentToFile(allSheetJsonFile, JsonOneLine);

				System.out.println(" xlsxFile-->"+xlsxFile.getAbsolutePath()+" 解析成功!");


			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}




	// 把 pdf中指定的页数 分隔出来作为一个新的pdf文件      #_36  A.pdf 31_341
	// 在本地目录创建 /sdcard/zapp  和 /sdcard/zmain 相关的 dir目录
	class Zapp_Zmain_dir_Create_Rule_37 extends Basic_Rule {

		boolean isZappFlag ;

		boolean isZmainFlag ;

		boolean isZmainInputParamFlag ;
		boolean isZappInputParamFlag ;

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
				System.out.println("strParam["+i+"] = "+ strParam);
				if("zmain".equals(strParam)) {
					System.out.println("X2 strParam["+i+"] = "+ strParam);
					isZmainFlag = true;
					isZmainInputParamFlag = true;

				}

				if("zapp".equals(strParam)) {
					System.out.println("X3 strParam["+i+"] = "+ strParam);

					isZappFlag = true;
					isZappInputParamFlag = true;
				}

			}

			if(isZmainInputParamFlag == false && isZappInputParamFlag == false) {   // 说明用户没有输入任何指定的参数 那么设置 两个值都为 true
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
			System.out.println(" 当前Shell的路径为: curDirFile =  "+ absShellPath);

			ArrayList<String> Zmain_createDirNameList = new 	ArrayList<String> ();

			ArrayList<String> Zapp_createDirNameList = new 	ArrayList<String> ();

			if(isZmainFlag) {
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

			if(isZappFlag) {

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

			if(Zmain_createDirNameList.size() > 0 && isZmainFlag) {
				System.out.println("══════════════════ zmain_begin ════════════════");

				for (int i = 0; i < Zmain_createDirNameList.size(); i++) {
					String dirName = Zmain_createDirNameList.get(i);
					String zmain_dirName = "zmain"+File.separator+dirName;
					String abs_path_dir = absShellPath + File.separator+ zmain_dirName;
					File dirFile = new File(abs_path_dir);
					dirFile.mkdirs();
					System.out.println("创建_zmain_目录["+i+"]  = "+ abs_path_dir);

				}
				System.out.println("══════════════════ zmain_end ════════════════");
			}



			if(Zapp_createDirNameList.size() > 0  && isZappFlag ) {
				System.out.println("══════════════════ zapp_begin ════════════════");

				for (int i = 0; i < Zapp_createDirNameList.size(); i++) {
					String dirName = Zapp_createDirNameList.get(i);
					String zmain_dirName = "zapp"+File.separator+dirName;
					String abs_path_dir = absShellPath + File.separator+ zmain_dirName;
					File dirFile = new File(abs_path_dir);
					dirFile.mkdirs();
					System.out.println("创建_zapp_目录["+i+"]  = "+ abs_path_dir);
				}
				System.out.println("══════════════════ zapp_end ════════════════");

			}

			System.out.println("当前 创建 zapp zmain 结构目录的方法 Rule_"+ rule_index +" 执行完毕!!  " );



			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {
			// TODO Auto-generated method stub


			return Cur_Bat_Name + " #_"+rule_index+"   ###  创建zapp 和 zmain的文件夹结构 /sdcard/zmain /sdcard/zapp   \n"
					+ Cur_Bat_Name + " #_"+rule_index+"  zmain  zapp    ### 创建zapp 和 zmain的文件夹结构 /sdcard/zmain /sdcard/zapp \n"
					+ Cur_Bat_Name + " #_"+rule_index+" zmain      ### 只在当前目录创建 zmain 的 /sdcard/zmain 目录结构 \n"
					+ Cur_Bat_Name + " #_"+rule_index+" zapp      ### 只在当前目录创建 zapp 的 /sdcard/zapp 目录结构 \n"

					;

		}



	}
	class Seperate_Pdf_Rule_36 extends Basic_Rule {


		// 参数的Key是 从参数输入的 文件的名称  参数的Value是对这个文件进行操作的 页面数值

		File targetPDF_File = null ;    // 目标 pdf target
		// 1. 有前  2.有后 3.有前后
		ArrayList<String>  page_page_StrArr;   // 输入的分隔的字符串 _10 18_ 10_11 12_20
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
						targetPDF_File =  tempFile;
						continue;
					}
				}

				if(strInput.contains("_") && !strInput.contains("#") && strInput.contains("page_")) {
					//去除掉_ 下划线后 全是数值
					String verifyStr = strInput.replace("page_","");
					String verifyStr_2 = verifyStr.replace("_","");
					if(isNumeric(verifyStr_2)) {
						page_page_StrArr.add(verifyStr);
					}
				}


			}

			if(targetPDF_File  == null || page_page_StrArr.size() == 0){
				System.out.println("用户对于 规则 rule"+rule_index +"输入错误！  没有输入 指定的 pdf文件!");
				return false;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			// TODO Auto-generated method stub
			if (targetPDF_File  == null  || page_page_StrArr.size() == 0) {
				System.out.println("用户对于 规则 rule"+rule_index +"输入错误！  没有输入 指定的 pdf文件!");

				return null;
			}

			try {
				for (int i = 0; i < page_page_StrArr.size(); i++) {

					System.out.println("规则"+rule_index+" Param"+i+"["+page_page_StrArr.get(i)+"]");
				}

				for (int i = 0; i < page_page_StrArr.size(); i++) {
					String index_str_item = page_page_StrArr.get(i);
					//  可能是 -1  , index_pre 为 -1   标示 从第一页 开始遍历
					int index_pre = calculPageString(index_str_item,true);
					System.out.println("calculPageString_index_pre = "+index_pre);
					if(index_pre == -1 || index_pre < 0) {  // 从第一页开始计数的
						index_pre = 1 ;
					}

					// index_end 为 -1 标示 读取到 pdf的最后一页  这个页数需要读文件 才能拿取到
					int index_end = calculPageString(index_str_item,false);
					System.out.println("calculPageString_index_end = "+index_end);



					PdfReader reader = new PdfReader(targetPDF_File.getAbsolutePath());

					int numberOfPages_Pdf = reader.getNumberOfPages();  // 读取文件大小
					reader.close();  //  关闭

					if(index_end == -1 || index_end < 0) {
						index_end = numberOfPages_Pdf ;
					}

					// 如果索引超过了 文件的页数  那么更正
					if(index_end > numberOfPages_Pdf) {
						index_end = numberOfPages_Pdf;
					}

					if(index_pre > index_end) {
						int temp = index_pre;
						index_pre = index_end;
						index_end = temp;
					}


					System.out.println("当前Page_Page字符串【"+index_str_item+"】  当前解析索引 pre_index="+index_pre+"  end_index="+index_end +" PDF总页数="+numberOfPages_Pdf );
					String newPdfName = targetPDF_File.getParentFile().getAbsolutePath()+File.separator+(getFileNameNoPoint(targetPDF_File.getName()))+"_"+index_pre+"_"+index_end+".pdf";

					reader = new PdfReader(targetPDF_File.getAbsolutePath());
					Document doc = new Document();
					String splitFileName = newPdfName;
					PdfCopy pdfCopy;
					try {
						pdfCopy = new PdfCopy(doc, new FileOutputStream(splitFileName));
					} catch (FileNotFoundException | DocumentException e) {
						System.out.println("分隔失败---》 当前Page_Page字符串【"+index_str_item+"】  当前解析索引 pre_index="+index_pre+"  end_index="+index_end +" PDF总页数="+numberOfPages_Pdf );

						throw new Exception("切割文件副本创建失败 AAAAA1111 ");

					}
					doc.open();
					// 将pdf按页复制到新建的PDF中
					System.out.println("PDF 循环起点 index_pre="+index_pre+"   终点index_end="+index_end);
					for (int j = index_pre; j <= index_end ; ++j) {
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

		int calculPageString(String pageStr , boolean isPre) {
			int pageIndex = -1;
			String fixed_str = pageStr.replace("__", "_").trim();
			fixed_str = fixed_str.replace("___", "_");
			fixed_str = fixed_str.replace("__", "_");
			fixed_str = fixed_str.replace("__", "_");  // 只有一个 _ 下划线了

			String[] splitArr = fixed_str.split("_");


			if(splitArr == null) {
				return pageIndex;
			}

			if(fixed_str.startsWith("_")) { // 如果以_ 开头说明 没有指定 起点   指定了终点
				if(isPre) {
					return pageIndex;
				}else {   //
					String endIndex = fixed_str.replace("_", "");
					if(isNumeric(endIndex)) {
						return Integer.parseInt(endIndex);
					}
				}

			}else if(fixed_str.endsWith("_")) { // 没有指定末尾页数 那么返回 -1  指定了起始页
				if(isPre) {
					String preIndex = fixed_str.replace("_", "");
					if(isNumeric(preIndex)) {
						return Integer.parseInt(preIndex);
					}

				}else {   //
					return pageIndex;
				}

			}


			if(splitArr.length  != 2) {
				return pageIndex;
			}

			String preStr = splitArr[0];

			String endStr = splitArr[1];
			if(isPre && isNumeric(preStr)) {
				return Integer.parseInt(preStr);

			}else if(!isPre && isNumeric(endStr)) {
				return Integer.parseInt(endStr);
			}

			return pageIndex;
		}


		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_"+rule_index+"  <指定Pdf文件>  page_1_10 page_11_20 page_30_40  ###  PDF从第一页开始 分隔当前的pdf文件中的1_10page 形成新的文件   \n"
					+ Cur_Bat_Name + " #_"+rule_index+"  A.pdf  page_1_10  page_2_20  ### 解析当前的A.pdf 生成 下划线分隔 A_1_10.pdf 文件 分隔新page的pdf文件 \n"
					+ Cur_Bat_Name + " #_"+rule_index+"  A.pdf  page_10_  page_50_  ### 解析当前的A.pdf 生成 从第10页开始解析到最后  从第50页开始解析到最后 \n"

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

			return  Cur_Bat_Name + " #_"+rule_index+"  <指定A.Pdf文件> <指定B.Pdf文件>  ### 按顺序合并当前所有pdf到一个pdf文件中 原有pdf删除   \n"
					+ Cur_Bat_Name + " #_"+rule_index+"  A.pdf B.pdf C.pdf   ### 按顺序合并当前所有pdf到一个pdf文件中 原有pdf删除    \n"
					;
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


	static final transient Rule34_MP3_NodeImpl Rule34_RootNodeImpl  = new G2_ApplyRuleFor_TypeFile().new Rule34_MP3_NodeImpl(0, "全部", 0,0,null);

	class MP3_Revert2JSOn_Rule_34 extends Basic_Rule {

		int  Node_ID_Num = 0;
		String[] Alphabet;

		int getNextNodeID(){
			Node_ID_Num++;
			return Node_ID_Num;
		}
		// 第一部分的 字母表 序列   level== 1
		ArrayList<Rule34_MP3_NodeImpl> alphabet_node_list;

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			return super.initParamsWithInputList(inputParamList) ;
		}


		MP3_Revert2JSOn_Rule_34() {
			super("#", 34, 4);
			Alphabet = new String []{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
			alphabet_node_list = new ArrayList<Rule34_MP3_NodeImpl>();
			Node_ID_Num = 0;
		}

		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_34   ### 解析当前的MP3文件生成对应的 mp3_tree.json 文件  \n"
					+ Cur_Bat_Name + " #_34    ### 解析当前的MP3文件生成对应的 mp3_tree.json 文件 \n"
					;
		}





		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
											  ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub
			ArrayList<File> allMp3FileList = new 	ArrayList<File> ();

			ArrayList<File>  mp3List_1 = subFileTypeMap.get(".mp3");
			ArrayList<File>  mp3List_2 = subFileTypeMap.get(".MP3");
			if(mp3List_1 != null) {

				allMp3FileList.addAll(mp3List_1);
			}
			if(mp3List_2 != null) {

				allMp3FileList.addAll(mp3List_2);
			}

			if(allMp3FileList.size() == 0) {
				System.out.println("Rule34 当前的文件夹中 MP3文件的个数为0  请检查!!!");
				return null;
			}

			for (int i = 0; i < Alphabet.length; i++) {
				String alphaItem = Alphabet[i];


/*public Rule34_MP3_NodeImpl(long id, String name, int count,int level , String xmp3Path) {
    this.id = id;
    this.name = name;
    this.count = count;
    this.level = level;
    this.mp3path = xmp3Path;

}
*/
				Rule34_MP3_NodeImpl Rule34_RootNodeImpl  = mG2_Object.new Rule34_MP3_NodeImpl(getNextNodeID(), alphaItem, 0,1,null);
				alphabet_node_list.add(Rule34_RootNodeImpl);
			}
			Rule34_RootNodeImpl.children.addAll(alphabet_node_list);





			//  把 当前 目录下的 mp3  依据  作者分类 分好

			HashMap<String,ArrayList<Rule34_MP3_NodeImpl>> mArtist_File_Map = new HashMap<String,ArrayList<Rule34_MP3_NodeImpl>>();

			// 依据 备注 进行 分类 得到的 Item  粤语 英语 纯语  更多分类 留下扩展
			HashMap<String,ArrayList<Rule34_MP3_NodeImpl>> mComment_File_Map = new HashMap<String,ArrayList<Rule34_MP3_NodeImpl>>();


			for (int i = 0; i < allMp3FileList.size(); i++) {

				try {
					File File_realMP3 = allMp3FileList.get(i);
					String mp3Name_nopoint = getFileNameNoPoint(allMp3FileList.get(i).getName());


					String mp3File_Path = File_realMP3.getAbsolutePath();
					Mp3File mp3file_item = new Mp3File(allMp3FileList.get(i).getAbsolutePath());
					if (mp3file_item.hasId3v2Tag()) {
						ID3v2 id3v2Tag = mp3file_item.getId3v2Tag();


						String titleName_tag =  id3v2Tag.getTitle().trim();
						String ArtistName_tag =  id3v2Tag.getArtist().trim();
						String CommentName_tag =  id3v2Tag.getComment().trim();

						if(mArtist_File_Map.containsKey(ArtistName_tag)) {
							ArrayList<Rule34_MP3_NodeImpl> curArr =    mArtist_File_Map.get(ArtistName_tag);
							curArr.add(mG2_Object.new Rule34_MP3_NodeImpl(getNextNodeID(), titleName_tag, 0,3,mp3File_Path));
						}else {
							ArrayList<Rule34_MP3_NodeImpl> targetArr = new  ArrayList<Rule34_MP3_NodeImpl>();
							targetArr.add(mG2_Object.new Rule34_MP3_NodeImpl(getNextNodeID(), titleName_tag, 0,3,mp3File_Path));
							mArtist_File_Map.put(ArtistName_tag, targetArr);
						}

					}
				}catch( Exception e) {
					System.out.println("RuleIndex 34  解析MP3文件出现错误! ");

				}
			}

			Show_AddNode_MP3Map(mArtist_File_Map,alphabet_node_list);

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

			System.out.println("════════════════════════════════"+ " Root JSON"+"════════════════════════════════");

			String mp3Json = rootNode.json();

//			mp3Json = StringEscape.unescapeJava(mp3Json);

			if(JSONUtil.isJson(mp3Json)) {
				System.out.println("═════════════════ 是JSON  ═════════════════");

				mp3Json = JSONUtil.formatJsonStr(mp3Json);
				System.out.println(mp3Json);
			}else {
				System.out.println("═════════════════ 不是JSON  ═════════════════");

				mp3Json = JSONUtil.formatJsonStr(mp3Json);
//				System.out.println(mp3Json);

			}

			File Mp3_Json_File = new File(curDirFile.getAbsolutePath() + File.separator+"MP3_JSON.json");
			writeContentToFile(Mp3_Json_File, mp3Json);
			NotePadOpenTargetFile(Mp3_Json_File.getAbsolutePath());


		}



		Rule34_MP3_NodeImpl	getNodeImpl_With_Zimu(ArrayList<Rule34_MP3_NodeImpl> alphabet_node_list , String charAlhapbet){
			Rule34_MP3_NodeImpl  selectedNode = null;
			for (int i = 0; i < alphabet_node_list.size(); i++) {
				Rule34_MP3_NodeImpl node = alphabet_node_list.get(i);
				if(node.name.toUpperCase().equals(charAlhapbet.toUpperCase())) {
					selectedNode = node;
					break;
				}

			}


			return selectedNode;

		}

		@SuppressWarnings("unchecked")
		boolean Show_AddNode_MP3Map(HashMap<String, ArrayList<Rule34_MP3_NodeImpl>>  xMP3FileMap ,  ArrayList<Rule34_MP3_NodeImpl> alphabet_node_list) {
			boolean executeFlag = false;
			Map.Entry<String, ArrayList<Rule34_MP3_NodeImpl>> entry;

			if (xMP3FileMap != null) {
				Iterator iterator = xMP3FileMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<Rule34_MP3_NodeImpl>>) iterator.next();

					//  获取 名称的 首字母
					String arrTag = entry.getKey(); // Map的Value  // 作者名称
					String Alphabet_Word = getFirstZiMu(arrTag);
					Rule34_MP3_NodeImpl  selectedNode_level_1 = 	getNodeImpl_With_Zimu(alphabet_node_list,Alphabet_Word);
					ArrayList<Rule34_MP3_NodeImpl> fileArr_level_3 = entry.getValue(); // Map的Value  歌曲MP3

					Rule34_MP3_NodeImpl artistNode_level_2 = mG2_Object.new Rule34_MP3_NodeImpl(getNextNodeID(), arrTag, fileArr_level_3.size(),2,null);

					selectedNode_level_1.addChildren(artistNode_level_2);

					System.out.println("═══════════════════════ arrTag["+arrTag+"]  alphabet=["+Alphabet_Word+"] all["+fileArr_level_3.size()+"] ═══════════════════════");

					for (int i = 0; i < fileArr_level_3.size(); i++) {
						Rule34_MP3_NodeImpl mp3FileNode_level_3 = fileArr_level_3.get(i);

						System.out.println("____________ arrTag["+arrTag+"] alphabet=["+Alphabet_Word+"] index["+(i+1)+"] all["+fileArr_level_3.size()+"] ____________");
						System.out.println(mp3FileNode_level_3.toString());

						artistNode_level_2.addChildren(mp3FileNode_level_3);
					}

				}
			}

			return executeFlag;
		}


		String getFirstZiMu(String srcStr) {
			String firstZimu = "U";  //  默认为 Unknow;
			if(srcStr == null || "".equals(srcStr.trim())) {
				return firstZimu;
			}

			if(!isContainChinese(srcStr)) {  // 如果 不包含中文  那么 取这个词的 第一个字符
				String char_1 = srcStr.substring(0,1).toUpperCase();
				firstZimu = char_1;

			}else {
				String char_1 = srcStr.substring(0,1).toUpperCase();
//				System.out.println("X2 char_1 = "+char_1 );
				if(!isContainChinese(char_1)) {
					firstZimu = char_1;
				}else {  // 如果第一个字母为汉字  那么取到这个字的 拼音的 第一个词
					String pinyinStr = ToPinyin(char_1);
//					System.out.println("X2  pinyinStr = "+pinyinStr );
					String char_1_fixed = pinyinStr.substring(0,1).toUpperCase();
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
			System.out.println("___________【"+name+ "】 Node_Level_"+level+"节点情况 Begin ___________");
			for (int i = 0; i < children.size(); i++) {
				System.out.println(children.get(i).toString());
			}
			System.out.println("___________【"+name+ "】Node_Level_"+level+"节点情况 End ___________");
			System.out.println();

			StringBuilder sb_json = new StringBuilder();
			sb_json.append("{\n");
			sb_json.append("\"id\": "+id+",\n");
			sb_json.append("\"name\": "+"\""+name+"\""+",\n");
			sb_json.append("\"count\": "+count+",\n");
			sb_json.append("\"level\": "+level+",\n");

			if(mp3path != null) {
				sb_json.append("\"mp3path\": "+"\""+(mp3path.replace("\\", "\\\\"))+"\""+",\n");
			}else {
				sb_json.append("\"mp3path\": \"null\""+",\n");
			}

			if(children == null || children.size() == 0) {
				sb_json.append("\"children\": "+"[]\n");
			}else {
				sb_json.append("\"children\": "+"[\n");
				for (int i = 0; i < children.size(); i++) {
					Rule34_MP3_NodeImpl childNode = children.get(i);
					if(childNode != null && childNode.children() != null && childNode.children().size() > 0) {
						System.out.println("childNode.name = "+ childNode.name + "  childNode.level="+childNode.level+"  childNode.id="+childNode.id + "  childNode.count="+childNode.children().size());

						// 子节点 之间 使用 , 逗号 分隔
						if(i == children.size()-1 ) {
							sb_json.append(childNode.json()+"\n");
						}else {
							sb_json.append(childNode.json()+",\n");
						}


					}else {
						System.out.println("结点 的 childNode 为空！！！ 为叶子节点");
						if(childNode != null) {
							if(i == children.size()-1 ) {
								sb_json.append(childNode.json()+"\n");
							}else {
								sb_json.append(childNode.json()+",\n");
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



		public Rule34_MP3_NodeImpl(long id, String name, int count,int level , String xmp3Path) {
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
			if (i == -1) return null;
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
			return  "text=["+text()+"] " + "  id=[" + id + "] " + "  level=[" + level() + "]" + "  count=[" + children.size() + "]" + " mp3path=["+mp3path()+"]"  ;
		}

		private int getSelectedChildPosition() {
			if (children == null) return -1;
			for (int i = 0; i < children.size(); i++) {
				Rule34_MP3_NodeImpl nodeImpl = children.get(i);
				if (nodeImpl == null) continue;
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
		boolean isCommentAppend; //  是否是在Comment 进行追加 而不是替换


		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramItem = inputParamList.get(i);
				String paramItem_lower_trim = paramItem.toLowerCase().trim();
				if(paramItem_lower_trim.contains("title_")) {
					String replaceTitle = paramItem.replace("title_", "").replace("Title_", "").trim();
					mTitle = replaceTitle;
				}

				if(paramItem_lower_trim.contains("artist_")) {
					String replaceArtist = paramItem.replace("artist_", "").replace("Artist_", "").trim();
					mArtist = replaceArtist;
				}

				if(paramItem_lower_trim.contains("comment_")) {
					String replaceComment = paramItem.replace("comment_", "").replace("Comment_", "").trim();
					mComment = replaceComment;
				}
				if(paramItem_lower_trim.contains("commentappend_")) {
					String replaceComment = paramItem.replace("commentappend_", "").replace("Commentappend_", "").trim();
					mComment = replaceComment;
					isCommentAppend = true;
				}


			}
			System.out.println("用户输入的 三个替换参数 mTitle="+mTitle+"  mArtist="+mArtist+"   mComment="+mComment);

			if(mTitle == null && mArtist == null && mComment == null ) {

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
					+ Cur_Bat_Name + " #_33  artist_周杰伦  title_稻香   ### 把当前的 mp3的属性comment 和 title 修改 \n"
					+ Cur_Bat_Name + " #_33  comment_国语  title_稻香   ### 把当前的 mp3的属性title 和 comment 修改 \n"
					+ Cur_Bat_Name + "  #_33 comment_国语   ### 把当前的 mp3的属性commont 改为国语  \n"
					+ Cur_Bat_Name + "  #_33 comment_粤语   ### 把当前的 mp3的属性commont 改为粤语   \n"
					+ Cur_Bat_Name + "  #_33 comment_英语   ### 把当前的 mp3的属性commont 改为英语   \n"
					+ Cur_Bat_Name + "  #_33 comment_纯语   ### 把当前的 mp3的属性commont 改为纯语   \n"
					+ Cur_Bat_Name + "  #_33 commentappend_收藏   ### 把当前的 mp3的属性commont 追加一个 原始Comment_收藏 为 Comment   \n"
					+ Cur_Bat_Name + "  #_33 commentappend_追赠   ### 把当前的 mp3的属性commont 追加一个 原始Comment_追赠 为 Comment   \n"
					+ Cur_Bat_Name + "  #_33 comment_纯语   ### 把当前的 mp3的属性commont 改为纯语   \n"

					+ Cur_Bat_Name + "  #_33 artist_周杰伦   ### 把当前的 mp3的属性artist 改为周杰伦 \n"
					+ Cur_Bat_Name + "  #_33 title_晴天   ### 把当前的 mp3的属性title 改为晴天 \n"
					;
		}





		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
											  ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub
			ArrayList<File> allMp3FileList = new 	ArrayList<File> ();

			ArrayList<File>  mp3List_1 = subFileTypeMap.get(".mp3");
			ArrayList<File>  mp3List_2 = subFileTypeMap.get(".MP3");
			if(mp3List_1 != null) {

				allMp3FileList.addAll(mp3List_1);
			}
			if(mp3List_2 != null) {

				allMp3FileList.addAll(mp3List_2);
			}

			if(allMp3FileList.size() == 0) {
				System.out.println("当前的文件夹中 MP3文件的个数为0  请检查!!!");
				return null;
			}

			// 没有 tag的 mp3文件的集合  // 估计会改名
			ArrayList<File> unTagedMp3FileList = new ArrayList<File> ();

			String TargetDirName = "MP3_Fixed_"+getTimeStamp();


			String fixed_name = null;
			for (int i = 0; i < allMp3FileList.size(); i++) {

				try {
					String mp3Name_nopoint = getFileNameNoPoint(allMp3FileList.get(i).getName());

					File mp3File = allMp3FileList.get(i);
					Mp3File mp3file = new Mp3File(allMp3FileList.get(i).getAbsolutePath());
					String d3v1_title=null;
					String d3v1_artist=null;
					String d3v1_comment=null;

					if(mp3file.hasId3v1Tag()) {

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
						String titleName_old =  id3v2Tag.getTitle();
						String ArtistName_old =  id3v2Tag.getArtist();
						String CommentName_old =  id3v2Tag.getComment();

						boolean mTitleExist = false;
						boolean mCommentExist = false;
						boolean mArtistExist = false;
//							String mTitle; // mp3文件的 标题
//							String mArtist; // mp3文件的 作者
//							String mComment; // mp3文件的 备注
						if(mTitle != null && !"".equals(mTitle)) {
							id3v2Tag.setTitle(mTitle);
							titleName_old = mTitle;
							mTitleExist = true;
						}

						if(mArtist != null && !"".equals(mArtist)) {
							id3v2Tag.setArtist(mArtist);
							mArtistExist = true;
						}

						if(mComment != null && !"".equals(mComment)) {
							if(isCommentAppend) {  //追加 而不是 替换
								String mCommentAppendStr = CommentName_old+"_"+mComment;
								mCommentAppendStr = mCommentAppendStr.replace("__", "_").trim();
								if(mCommentAppendStr.startsWith("_")) {
									mCommentAppendStr =mCommentAppendStr.substring(1,mCommentAppendStr.length());
								}
								id3v2Tag.setComment(mCommentAppendStr);
							}else {
								id3v2Tag.setComment(mComment);
							}

							mCommentExist = true;
						}

						mp3file.setId3v2Tag(id3v2Tag);

						fixed_name   = titleName_old;

						if(titleName_old == null || "".equals(titleName_old.trim())) {

							fixed_name = mp3Name_nopoint.replace("_", "");
							fixed_name = clearNumber(fixed_name).trim();
							if("".equals(fixed_name)) {
								fixed_name = "unknow";
							}


						}

						String titleName_new =  id3v2Tag.getTitle();
						String ArtistName_new =  id3v2Tag.getArtist();
						String CommentName_new =  id3v2Tag.getComment();

						fixed_name = fixed_name.replace(" ","_");
						fixed_name = fixed_name.replace("《","_");
						fixed_name = fixed_name.replace("》","_");
						fixed_name = fixed_name.replace("（","_");
						fixed_name = fixed_name.replace("）","_");
						fixed_name = fixed_name.replace("(","_");
						fixed_name = fixed_name.replace(")","_");
						fixed_name = fixed_name.replace("___","_");
						fixed_name = fixed_name.replace("__","_");
						fixed_name = fixed_name.replace("__","_");
						fixed_name = fixed_name+ "_"+getTimeStamp();
						fixed_name = fixed_name.replace("__","_");

						File mp3File_DirTarget_File = new File(allMp3FileList.get(i).getParentFile().getAbsolutePath()+File.separator+TargetDirName);

						if(!mp3File_DirTarget_File.exists()) {
							mp3File_DirTarget_File.mkdirs();
						}
						mp3file.save(allMp3FileList.get(i).getParentFile().getAbsolutePath()+File.separator+TargetDirName+File.separator +fixed_name+".mp3");


						StringBuilder SBLog = new StringBuilder();

						SBLog.append(" 当前生成文件["+(i+1)+"]: "+ fixed_name+".mp3 ");

//							  d3v1_title = id3v1Tag.getTitle();
//							  d3v1_artist = id3v1Tag.getArtist();
//							  d3v1_comment = id3v1Tag.getComment();

						if(mTitleExist) {
							SBLog.append(" Title["+titleName_old+"] -> Title["+titleName_new+"]  v2_titleName_old="+titleName_old+"  v2_ArtistName_old="+ArtistName_old+"  v2_CommentName_old="+CommentName_old +"  v1_titleName_old="+d3v1_title+"  v1_ArtistName_old="+d3v1_artist+"  v1_CommentName_old="+d3v1_comment );
						}

						if(mArtistExist) {
							SBLog.append(" Artist["+ArtistName_old+"] -> Artist["+ArtistName_new+"]  v2_titleName_old="+titleName_old+"  v2_ArtistName_old="+ArtistName_old+"  v2_CommentName_old="+CommentName_old +"  v1_titleName_old="+d3v1_title+"  v1_ArtistName_old="+d3v1_artist+"  v1_CommentName_old="+d3v1_comment );
						}

						if(mCommentExist) {
							SBLog.append(" Comment["+CommentName_old+"] -> Comment["+CommentName_new+"]  v2_titleName_old="+titleName_old+"  v2_ArtistName_old="+ArtistName_old+"  v2_CommentName_old="+CommentName_old +"  v1_titleName_old="+d3v1_title+"  v1_ArtistName_old="+d3v1_artist+"  v1_CommentName_old="+d3v1_comment );
						}

						SBLog.append("  文件原名称["+mp3Name_nopoint+"]");
						System.out.println(SBLog.toString());


					}else {

						unTagedMp3FileList.add(allMp3FileList.get(i));
					}
				}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					System.out.println("创建 Mp3File 文件失败!!! 失败文件"+(i+1)+ fixed_name+".mp3 ");
				}

			}

			//
			if(unTagedMp3FileList.size() > 0) {
				System.out.println("═════════════════════════ "+"失败!!! 遗憾存在 MP3转换失败文件 "+"═════════════════════════ ");
				for (int j = 0; j < unTagedMp3FileList.size(); j++) {
					System.out.println("没有D3V2-Tag    失败MP3文件["+(j+1)+"] = "+unTagedMp3FileList.get(j).getAbsolutePath() );
				}

			}else {
				System.out.println("═════════════════════════ "+"恭喜!!! 当前没有 不存在D3V2-Tag的文件 "+"═════════════════════════ ");

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

			return Cur_Bat_Name + " #_32    // 把当前的 jpg 和 png 文件转为一个 PDF文件  (不操作 孙文件 孙文件夹 )  \n"
					+ Cur_Bat_Name + "  #_32   ### 把当前的 jpg 和 png 文件转为一个 PDF文件  \n"

					;
		}


		@Override
		boolean allowEmptyDirFileList() {
			// TODO Auto-generated method stub
			return true;
		}


		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
											  ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			ArrayList<File> pictureFileList = new 	ArrayList<File> ();
			int picture_index = 1 ;
			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = curFile.getName().toLowerCase();
				if (currentFileName.endsWith(".jpg") || currentFileName.endsWith(".png") ) {
					pictureFileList.add(curFile);
					System.out.println("picture_index["+picture_index+"] = "+ curFile.getAbsolutePath());
					picture_index++;
				}

			}


			if(pictureFileList.size() > 0) {
				SortFileWithName(pictureFileList);
				Document document = new Document();
				String result_pdf = curDirPath+File.separator+"Z_PDF_Merge"+pictureFileList.size()+"_"+getTimeStamp()+".pdf";

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
					createPicInPDF(document, imageFile.getAbsolutePath(),i);
					System.out.println("jpg["+i+"] all["+pictureFileList.size()+"] Name["+imageFilename+"] "+"图片转为 PDF完成");
				}

				document.close();
				System.out.println("═════════"+pictureFileList.size()+"张图片转为 PDF完成"+"═════════");

			}else {
				System.out.println("当前目录 没有 JPG PNG 文件 请检查! "+curDirPath);
			}


			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void createPicInPDF(Document document, String picturePath,int index) {
			try {
				com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(picturePath);
				float image_width = 	image.getWidth();
				float image_hight = 	image.getHeight();

				float imageHeight=image.getScaledHeight();
				float imageWidth=image.getScaledWidth();
				//设置页面宽高与图片一致
				com.itextpdf.text.Rectangle rectangle = new com.itextpdf.text.Rectangle(imageWidth, imageHeight);
				document.setMargins(0, 0, 0, 0);
				document.setPageSize(rectangle);

				//图片居中
				image.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);

//					float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
//					float documentHeight = documentWidth / 580 * 320;// 重新设置宽高
//					float documentWidth = document.getPageSize().getWidth();
//					float documentHeight = document.getPageSize().getHeight();
				System.out.println("════════════════════ "+index+" ("+picturePath+")"+" begin ════════════════════ ");
//					System.out.println("index="+index+"  documentWidth = "+documentWidth+"  documentHeight="+documentHeight +" image_width="+image_width+"  image_hight="+image_hight);
				System.out.println("index="+index+"   "+ " image_width="+image_width+"  image_hight="+image_hight);

				image.scaleAbsolute(image_width, image_hight);

				// 根据域的大小缩放图片
//				    image.scaleToFit(documentWidth,documentHeight);
				// 添加图片
//				    image.setAbsolutePosition(documentWidth,documentHeight);
//					image.setAbsolutePosition(documentWidth, documentHeight);
//					image.scaleAbsolute(documentWidth, documentHeight);// 重新设置宽高
//					document.setMargins(0, 0, 0, 0);
				System.out.println("document.topMargin()="+document.topMargin()+"  document.bottomMargin()"+document.bottomMargin()+"  document.leftMargin()"+document.leftMargin()+"  document.rightMargin()"+document.rightMargin());
				document.newPage();
				document.add(image);
			} catch (Exception ex) {
			}
		}


	}
	class MakeFile_E5_Rule_31 extends Basic_Rule {
		ArrayList<String> fliterTypeList ;
		int fileCount ;   //创建文件的个数

		//	jpg_wordcolor_255_255_255;
		int jpg_R;   // 如果是jpg的话 那么  文笔的 颜色
		int jpg_G;
		int jpg_B;

		//	jpg_background_255_255_255;
		int jpg_Back_R;   // 如果是jpg的话 那么  背景的 颜色
		int jpg_Back_G;
		int jpg_Back_B;

		//	jpg_frontsize_500; 默认500
		int jpg_front_size;   // 字体的大小  默认 500

		// jpg_wxh_255_500
		int jpg_width;  // 生成 照片的宽度
		int jpg_hight;  // 生成 照片的高度

		//	  jpg_shownumber_true;
		boolean isShowNumber ; // 是否显示 字母




		MakeFile_E5_Rule_31() {
			super("#", 31, 4);


			fliterTypeList = new ArrayList<String>();
			jpg_R =255;
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
			return "\n"
					+ Cur_Bat_Name + "  #_31  100 .jpg     ## 创建100个.jpg文件 白底 默认宽高1200x1200 默认文字颜色 红色 默认背景颜色白色  默认字体大小500  默认不打印数值 \n"
					+ Cur_Bat_Name + "  #_31  100 .png     ## 创建100个.png文件 默认宽高1200x1200 默认文字颜色 红色 默认背景颜色白色  默认字体大小500  默认不打印数值 \n"
					+ Cur_Bat_Name + "  #_31  100 .png .jpg    ## 创建100个.png文件 和 100个.png文件  默认宽高1200x1200 默认文字颜色 红色 默认背景颜色白色  默认字体大小500  默认不打印数值 \n"
					+ Cur_Bat_Name + "  #_31  100 .pptx     ## 创建100个.pptx文件 \n"
					+ Cur_Bat_Name + "  #_31   .pptx     ## 默认创建1个.pptx文件 \n"
					+ Cur_Bat_Name + "  #_31  100 .jpg  jpg_shownumber_true     ## 创建100个白底红字的 jpg图片 默认图片大小1200x1200 \n"
					+ Cur_Bat_Name + "  #_31   .【任意类型】    ## 默认创建1个.【任意类型】格式的文件 \n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_255_255_255 jpg_wordcolor_0_0_0 jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片  白底黑字\n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_0 jpg_wordcolor_255_255_255 jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片  白字黑底\n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 黑底红字  \n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_255_0_0 jpg_wordcolor_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 红底黑字  \n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_255 jpg_wordcolor_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 蓝底黑字  \n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_255 jpg_wordcolor_255_255_255  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 蓝底白字  \n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_255 jpg_wordcolor_255_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 蓝底红字  \n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_0_0_255 jpg_wordcolor_255_255_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 蓝底黄字  \n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_255_255_0 jpg_wordcolor_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 黄底黑字  \n"
					+ Cur_Bat_Name + " #_31  100 .jpg jpg_shownumber_true jpg_background_0_255_0 jpg_wordcolor_0_0_0  jpg_frontsize_600 jpg_wxh_1000_1000   ##创建100个依据参数确定的.jpg图片 绿底黑字  \n"

					;
		}


		void	showParams(){
			System.out.println("═════════════ "+"showParams Rule31--MakeFile"+" ═════════════ ");
			System.out.println("isShowNumber = "+isShowNumber);
			System.out.println("jpg_width = "+jpg_width);
			System.out.println("jpg_hight = "+jpg_hight);
			System.out.println("jpg_front_size = "+jpg_front_size);
			System.out.println("jpg_R = "+jpg_R);
			System.out.println("jpg_G = "+jpg_G);
			System.out.println("jpg_B = "+jpg_B);
			System.out.println("RGB=("+jpg_R+","+jpg_G+","+jpg_B+")");
			System.out.println("jpg_Back_R = "+jpg_Back_R);
			System.out.println("jpg_Back_G = "+jpg_Back_G);
			System.out.println("jpg_Back_B = "+jpg_Back_B);
			System.out.println("Back_RGB=("+jpg_Back_R+","+jpg_Back_G+","+jpg_Back_B+")");

		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

			for (int i = 0; i < inputParamList.size(); i++) {
				String param = inputParamList.get(i);
				if(isNumeric(param)) {
					fileCount = Integer.parseInt(param);
				}

				if(param.startsWith(".")) {
					fliterTypeList.add(param);
				}


				// //	jpg_frontsize_500;
				if(param.startsWith("jpg_frontsize_500")) {
					String fixedStr = param.replace("jpg_frontsize_", "");
					if(isNumeric(fixedStr)) {
						jpg_front_size	 =  Integer.parseInt(fixedStr);
					}
				}

				// jpg_shownumber_true;
				if(param.startsWith("jpg_shownumber_")) {
					String fixedStr = param.replace("jpg_shownumber_", "").toLowerCase().trim();
					if("true".endsWith(fixedStr) || "false".equals(fixedStr)) {
						isShowNumber =  Boolean.parseBoolean(fixedStr);
					}
				}

				// 	// jpg_wxh_255_500
				if(param.startsWith("jpg_wxh_")) {
					String fixedStr = param.replace("jpg_wxh_", "");
					if(fixedStr.contains("_")) {
						String[] rectArr = fixedStr.split("_");
						if(rectArr != null && rectArr.length == 2) {

							if(isNumeric(rectArr[0])) {
								jpg_width = Integer.parseInt(rectArr[0]);
							}

							if(isNumeric(rectArr[1])) {
								jpg_hight = Integer.parseInt(rectArr[1]);
							}


						}
					}
				}

				if(param.startsWith("jpg_wordcolor_")) {
					String fixedStr = param.replace("jpg_wordcolor_", "");
					if(fixedStr.contains("_")) {
						String[] colorArr = fixedStr.split("_");
						if(colorArr != null && colorArr.length == 3) {

							if(isNumeric(colorArr[0])) {
								jpg_R = Integer.parseInt(colorArr[0]);
							}

							if(isNumeric(colorArr[1])) {
								jpg_G = Integer.parseInt(colorArr[1]);
							}

							if(isNumeric(colorArr[2])) {
								jpg_B = Integer.parseInt(colorArr[2]);
							}
						}
					}
				}

				// jpg_background_255_255_255
				if(param.startsWith("jpg_background_")) {
					String fixedStr = param.replace("jpg_background_", "");
					if(fixedStr.contains("_")) {
						String[] backcolorArr = fixedStr.split("_");
						if(backcolorArr != null && backcolorArr.length == 3) {

							if(isNumeric(backcolorArr[0])) {
								jpg_Back_R = Integer.parseInt(backcolorArr[0]);
							}

							if(isNumeric(backcolorArr[1])) {
								jpg_Back_G = Integer.parseInt(backcolorArr[1]);
							}

							if(isNumeric(backcolorArr[2])) {
								jpg_Back_B = Integer.parseInt(backcolorArr[2]);
							}
						}
					}
				}




			}
			if(fileCount == 0) {
				System.out.println("当前没有创建文件的个数  默认设置为 1 ");
				fileCount = 1;
			}

			if(fliterTypeList.size() == 0) {
				System.out.println("当前没有需要创建的文件类型 .xx   请检查输入参数!");
				return false;
			}
			// TODO Auto-generated method stub
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
											  ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub


			for (int i = 0; i < fliterTypeList.size(); i++) {
				String type = fliterTypeList.get(i);
				ArrayList<File> mTypeFileList = getTypeFileListRule31( curDirFile.getAbsolutePath(),  type , fileCount);
				tryDoFillFile(mTypeFileList , type);

			}



			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		void tryDoFillFile(ArrayList<File> curFile , String pointtype){
			File curTempFile = null;
			String type = pointtype.replace(".", "");

//	    	        Readers: [JPG, jpg, tiff, pcx, PCX, bmp, BMP, gif, GIF, WBMP, png, PNG, raw, RAW, JPEG, pnm, PNM, tif, TIF, TIFF, wbmp, jpeg]
//	    	        Writers: [JPG, jpg, tiff, bmp, BMP, pcx, PCX, gif, GIF, WBMP, png, PNG, raw, RAW, JPEG, pnm, PNM, tif, TIF, TIFF, wbmp, jpeg]
			if("jpg".equals(type) || "png".equals(type) || "jpeg".equals(type) || "bmp".equals(type) || "gif".equals(type)
			){  // 动态创建文件  文件的内容是数值
				//  不支持的格式      || "wbmp".equals(type) || "raw".equals(type)
				generalPicture(curFile , type );

			}else if("wbmp".equals(type) || "raw".equals(type)){  // 不能通过 ImageIO 来创建的图片格式  wbmp  raw

				// 待定
			} else if (isSupportType(type)){  // 从 G2  目录读取模板 然后输入输出 完成文件的创建
				File typeFile = getCurrentTemplateTypeFile(type);
				generalTemplateFile(curFile , type ,typeFile);
			}else{ // 其他格式 创建空的 后缀文件
				generalTemplateEmptyFile(curFile , type );
			}

		}

		void generalTemplateEmptyFile(ArrayList<File> picFileList , String pointtype ) {
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


		void generalTemplateFile(ArrayList<File> picFileList , String type , File templateFile)  {
			if(templateFile == null){
				return ;
			}
			for (int i = 0; i < picFileList.size(); i++) {
				int mCurIndex = i ;
				File mCurFile = picFileList.get(i);
				fileCopy(templateFile,mCurFile);
			}

		}


		File getCurrentTemplateTypeFile(String pointtype){
			String type = pointtype.replace(".", "");
			String curType = type.toLowerCase();  // zbin/E5/txt/txt.txt
			File typeFile = new File(getG2ZbinDirPath()+File.separator+curType+File.separator+curType+"."+curType);
			if(!typeFile.exists()){
				System.out.println("当前类型文件:  type:"+type +" 模板文件缺失无法批量创建文件 请创建模板！ 执行失败！");
				return null;
			}
			return typeFile;
		}

		String getG2ZbinDirPath(){
			String path =System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"G2";
			return path;
		}


		void generalPicture(ArrayList<File> picFileList , String pointtype)  {

			showParams();
			String type = pointtype.replace(".", "");
			for (int i = 0; i < picFileList.size(); i++) {
				int mCurIndex = i ;
				File mCurFile = picFileList.get(i);
				int width = jpg_width;
				int heigh = jpg_width;
				BufferedImage bi = new BufferedImage(width,heigh, BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度70,宽度150
				//得到它的绘制环境(这张图片的笔)
				Graphics2D g2 = (Graphics2D) bi.getGraphics();
				g2.fillRect(0,0,jpg_width,jpg_width);//填充一个矩形 左上角坐标(0,0),宽500,高500;填充整张图片
				g2.setColor(new Color(jpg_Back_R,jpg_Back_G,jpg_Back_B));//设置颜色
				g2.fillRect(0,0,width,heigh);//填充整张图片(其实就是设置背景颜色)
				int frontSize = jpg_front_size;
				int centerx = jpg_width/2;
				int centery = jpg_hight/2;
				int showIndex = i + 1;
				g2.setColor(new Color(jpg_R,jpg_G,jpg_B));
				Font f =  new Font("宋体",Font.BOLD,frontSize);
				g2.setFont(f); //设置字体:字体、字号、大小
				FontRenderContext context = g2.getFontRenderContext();
				Rectangle2D bounds = f.getStringBounds(showIndex+"", context);
				if(isShowNumber) {
					g2.drawString(showIndex+"",(float)(centerx-bounds.getCenterX()),(float)(centery-bounds.getCenterY())); //向图片上写字符串
				}
				try {
					mCurFile.createNewFile();
					ImageIO.write(bi,type,new FileOutputStream(mCurFile));//保存图片 JPEG表示保存格式
					System.out.println("创建文件["+i+"]  = "+mCurFile.getAbsolutePath()+"成功");
				}catch (Exception e){
					System.out.println("复制图片格式出现异常！");
				}

			}
			showParams();

		}


		public  ArrayList<File> getTypeFileListRule31(String dirAbsPath , String typeName , int fileCount) {
			ArrayList<File> curFileList = new ArrayList<File>();
			String typeNameNoPoint = typeName.replace(".", "");

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			String typeDir =  dirAbsPath + File.separator + typeNameNoPoint+"_"+date;
			File typeDirFile = new File(typeDir);
			typeDirFile.mkdirs();
			for (int i = 1; i < fileCount + 1; i++) {
				String itemFileStr = typeDirFile.getAbsolutePath()+File.separator+i+"."+typeNameNoPoint;
				File itemFile = new File(itemFileStr);
				curFileList.add(itemFile);
			}
			return curFileList;

		}


		boolean isSupportType( String type){
			boolean flag = false ;
			if("jpg".equals(type) || "jpeg".equals(type)  || "bmp".equals(type)  ||  "png".equals(type)  ||
					"wbmp".equals(type) || "gif".equals(type)
					|| ("txt".equals(type)) || ("md".equals(type))  || ("java".equals(type)) || ("cpp".equals(type))
					|| ("c".equals(type))  || ("xml".equals(type)) || ("json".equals(type)) || ("bat".equals(type))
					|| ("sh".equals(type))   || ("zip".equals(type)) || ("rar".equals(type))
					|| ("xlsx".equals(type))  || ("xls".equals(type))  || ("html".equals(type))
					|| ("db".equals(type))  || ("js".equals(type))  || ("dll".equals(type)) || ("exe".equals(type))
					|| ("gitignore".equals(type))   || ("h".equals(type))    || ("jar".equals(type))
					|| ("py".equals(type))  || ("pkt".equals(type))  || ("7z".equals(type)) || ("pdf".equals(type))
					|| ("doc".equals(type))  || ("apk".equals(type))  || ("pcapng".equals(type))
					|| ("conf".equals(type))   || ("properties".equals(type))   || ("prop".equals(type)) || ("css".equals(type))
					|| ("so".equals(type))     || ("bin".equals(type)) || ("raw".equals(type))
					|| ("ppt".equals(type))     || ("pptx".equals(type))|| ("docx".equals(type))

			){
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


		ArrayList<String> fliterTypeList ;

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
				if(inputTypeItem.startsWith(".")) {
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

				if(inputStr.endsWith("_")  ) {

					replacedStr_4 = inputStr.substring(0,inputStr.length()-1);
					newNameStr_4="";

				}else {
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

			ArrayList<File> slectedFileList = getRealFileWithDirAndPointType(curDirFile,fliterTypeList);

			System.out.println("Rule30 修改文件  currentOperaType = "+currentOperaType+"   ");

			switch (currentOperaType) {

				case 1:
					for (int i = 0; i < slectedFileList.size(); i++) {
						File selectFile = slectedFileList.get(i);
						String selectFileName = selectFile.getName();
						String pointType = getFileTypeWithPoint(selectFileName);
						String FileNameWithNoLower = getFileNameNoPoint(selectFileName);
						String newselectFileName = FileNameWithNoLower + appendStr_1 +pointType;
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
					System.out.println("beginIndex_3 = "+beginIndex_3+"   endIndex_3="+endIndex_3);
					for (int j = 0; j < fliterTypeList.size(); j++) {
						String typeStr = fliterTypeList.get(j);
						for (int i = beginIndex_3; i < endIndex_3 + 1; i++) {
							String absDirPath = curDirFile.getAbsolutePath();


							String selectFilePath = absDirPath + File.separator + prefixStr_3 + i + appendStr_3+typeStr;
							File curFileItem = new File(selectFilePath);
							System.out.println("创建空 "+typeStr+" 文件 ["+i+"] = "+curFileItem.getName());
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
					System.out.println("replacedStr_4 = "+replacedStr_4+"   newNameStr_4="+newNameStr_4);

					for (int i = 0; i < slectedFileList.size(); i++) {
						File realFile = slectedFileList.get(i);
						String realFileName = realFile.getName();

						String newRealName = realFileName.replace(replacedStr_4, newNameStr_4==null?"":newNameStr_4);
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
			return "只对文件的文件名进行操作"+"\n" + Cur_Bat_Name + "  #_30_append  _over   ###往当前文件夹后缀增加 _over \n" + Cur_Bat_Name
					+ "  #_30_append .jpg  _over   ###往当前所有文件前缀增加 xx_over.png \n" + Cur_Bat_Name
					+ "  #_30_prefix  temp   ###往当前所有文件前缀增加 temp \n" + Cur_Bat_Name
					+ "  #_30_prefix .jpg temp   ###往当前所有png文件前缀增加 temp tempxxx.png \n" + Cur_Bat_Name
					+ " #_30_create .jpg 1_100   ###创建一个序列号从1.jpg到100.jpg的100个文件   \n" + Cur_Bat_Name
					+ " #_30_create  .pptx   temp_  1_100   ###创建一个序列号从temp1.pptx到temp100.pptx的100个文件 \n" + Cur_Bat_Name
					+ " #_30_create .doc .jpg  _temp  1_100   ###创建一个序列号从1temp.doc到100temp.doc 1temp.jpg到100temp.jpg 的100个文件 \n" + Cur_Bat_Name
					+ " #_30_create .jpg  j_temp  1_100   ###创建一个序列号从 j_1_temp.jpg 到100temp的 j_100_temp.jpg 的文件 \n" + Cur_Bat_Name
					+ " #_30_create .jpg   7000_7100  ###创建一个序列号从7000.png开始的到7100.png结束的文件  \n" + Cur_Bat_Name
					+ " #_30_replace  abc_DEF  ###把当前文件名称中的  abc 转为 DEF \n"+ Cur_Bat_Name
					+ " #_30_replace .jpg .png abc_DEF  ###把当前文件夹下的.jpg .png 名称中的  abc 转为 DEF \n"
					;
		}

	}



	class mergeSomePPTXAsOne_Rule_29 extends Basic_Rule { 	//  把 当前 目录的 多个 pptx 合并成 一个 pptx 文件
		boolean isDeteleOrigin = false;   // 是否把原pptx文件删除
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
				String paramStr_lower_trim= paramStr.toLowerCase().trim();
				System.out.println("param"+i+"["+paramStr+"] ");

				if(paramStr_lower_trim.startsWith("delete")) {
					isDeteleOrigin = true;

				}
			}

			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
											  ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			ArrayList<File> pptxFileList = new 	ArrayList<File> ();
			int pptx_index = 1 ;
			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = curFile.getName().toLowerCase();
				if (currentFileName.endsWith(".pptx") || currentFileName.endsWith(".ppt") ) {
					pptxFileList.add(curFile);
					System.out.println("pptx_index["+pptx_index+"] = "+ curFile.getAbsolutePath());
					pptx_index++;
				}

			}

			if(pptxFileList.size() == 0) {
				System.out.println("当前目录就没有  pptx文件 请检查! PATH = "+curDirPath);
				return null;
			}

			if(pptxFileList.size() == 1) {
				System.out.println("当前目录只有1个  pptx文件  无需合并pptx操作! PATH = "+curDirPath);
				return null;
			}

			if(pptxFileList.size() > 1) {
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
						ppt.setPageSize(new Dimension(currentWidth,currentHeight));
//				        System.out.println("currentWidth = "+currentWidth+"    currentHeight="+currentHeight);



						for (XSLFSlide srcSlide : src.getSlides()) {
							ppt.createSlide().importContent(srcSlide);

						}
					}

					String result_pptx = curDirPath+File.separator+"Z_PPTX_Merge"+pptxFileList.size()+"_"+getTimeStamp()+".pptx";
					File resultPPTX_File =  new File(result_pptx);
					// creating the file object
					FileOutputStream out = new FileOutputStream(resultPPTX_File);

					// saving the changes to a file
					ppt.write(out);
					System.out.println("Merging done successfully");
					out.close();

					if(resultPPTX_File.exists() && resultPPTX_File.length() > 100 && isDeteleOrigin) {
						for (int i = 0; i < pptxFileList.size(); i++) {
							File pptxFileItem = pptxFileList.get(i);
							System.out.println("删除原始 pptx_"+i+"=["+pptxFileItem.getAbsolutePath()+"]");

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
			return "\n" + Cur_Bat_Name + "  #_29     ## 把当前目录下的 pptx文件合并为一个 pptx文件  【保留原有】的pptx文件 \n"
					+ Cur_Bat_Name + " #_29  delete  ##把当前目录下的 pptx文件合并为一个 pptx文件  【删除原有】的pptx文件 \n"

					;
		}


	}


	//  把当前的 图片 文件  jpg png 等 转为 一个 PPTX 文件 方便 分享 查看
	class makeJpg2PPTX_Rule_28 extends Basic_Rule {
		// 把文件后缀中的中文给去除掉 不包含文件夹 不包含孙文件

		File TemplatePPTX_File ;
		int begin_index;   // 由于 免费版本的 Presentation 最大创建 pptx页面为 10页 所以 才出现这些属性 尼玛
		int end_index;   // 由于 免费版本的 Presentation 最大创建 pptx页面为 10页 所以 才出现这些属性 尼玛

		boolean isShowName ;
		int rotate_value;  //旋转的角度
		boolean bigkeep;  //  那些 与 电脑尺寸相同的 照片 保持正向的 比例

		ArrayList<String> mInputParamList;  // 记录当前的所有输入的参数

		makeJpg2PPTX_Rule_28() {
			super("#", 28, 4);
			TemplatePPTX_File = new File(zbinPath+File.separator+"G2_EmptyPPTX_Rule28.pptx");
			rotate_value = 0;
			isShowName = false;
			bigkeep = false;
			begin_index = 0;
			end_index = 0;
			mInputParamList = new ArrayList<String> ();
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {

//				param0[#_28]
//				param1[name_90]
			mInputParamList.addAll(inputParamList);

			for (int i = 0; i < inputParamList.size(); i++) {
				String paramStr = inputParamList.get(i);
				System.out.println("param"+i+"["+paramStr+"] ");
				if(paramStr.toLowerCase().contains("name")) {
					isShowName = true;
				}
				if(paramStr.toLowerCase().contains("keepbig")) {
					bigkeep = true;
				}

				if(paramStr.contains("_") && isNumeric(paramStr.replace("_", "").trim())) {
					String[] indesArr = paramStr.split("_");
					if(indesArr != null && indesArr.length == 2) {
						if(isNumeric(indesArr[0])) {
							begin_index = Integer.parseInt(indesArr[0]);
						}

						if(isNumeric(indesArr[1])) {
							end_index = Integer.parseInt(indesArr[1]);
						}


					}


				}

				if(paramStr != null && isNumeric(paramStr.trim())){

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





			System.out.println("isShowName="+isShowName+"   rotate_value="+rotate_value);
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
											  HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
											  ArrayList<File> curRealFileList) {

			System.out.println("makeJpg2PPTX_Rule_28   搜索到的实体文件个数:" + curRealFileList.size());

			ArrayList<File> pictureFileList = new 	ArrayList<File> ();
			int picture_index = 1 ;
			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = curFile.getName().toLowerCase();
				if (currentFileName.endsWith(".jpg") || currentFileName.endsWith(".png") ) {
					pictureFileList.add(curFile);
					System.out.println("picture_index["+picture_index+"] = "+ curFile.getAbsolutePath());
					picture_index++;
				}

			}

			if(pictureFileList.size() > 0) {
				Presentation ppt = new Presentation();
				File tempPPTXFile = null;
				try {
					tempPPTXFile = new File(TemplatePPTX_File.getAbsolutePath());
					if(!tempPPTXFile.exists()) {
						System.out.println(" 当前 PPTX的模板文件不存在 PATH = "+ tempPPTXFile.getAbsolutePath());
						tempPPTXFile.createNewFile();
					}


					ppt.loadFromFile(tempPPTXFile.getAbsolutePath());



					Rectangle2D rect_fullSize = new Rectangle2D.Double(0, 0, ppt.getSlideSize().getSize().getWidth(), ppt.getSlideSize().getSize().getHeight());
					double PPT_Width = rect_fullSize.getWidth();
					double PPT_Height = rect_fullSize.getHeight();

					System.out.println("PPT_Width = "+ PPT_Width + "   PPT_Height="+PPT_Height);
					ISlide slide = ppt.getSlides().get(0);

					//
					if(begin_index == 0 && end_index == 0 && pictureFileList.size() > 10 ) {
						// 通过长度 计算   以及 输入 参数 inputParamList 动态计算 全部解析的命令
						// buildCommandWIth(pictureFileList.size()，inputParamList)
						System.out.println("当前目录图片文件大于10 个 !! 需要动态 动态创建 pptx 文件 请执行如下文件");
						buildDynamic(pictureFileList.size(),mInputParamList);

						return null;
					}
					System.out.println("begin_index = "+ begin_index + "   end_index="+end_index);


					SortFileWithName(pictureFileList);
					for (int i = 0; i < pictureFileList.size(); i++) {
						System.out.println("Format_Index["+i+"] = "+pictureFileList.get(i).getAbsolutePath());
					}

					for (int i = 0; i < pictureFileList.size(); i++) {
						if(pictureFileList.size() > 10) {
							if(i < begin_index ) {
								continue;
							}else if(i > end_index) {
								continue;
							}
						}

						System.out.println("For_Index_i="+i);
						File imageFile = pictureFileList.get(i);
						String FileName = imageFile.getName();
						String fileNameNoPoint = getFileNameNoPoint(FileName);
						Image img = ImageIO.read(imageFile); // 构造Image对象
						int picture_width = 	img.getWidth(null);
						int picture_height = 	img.getHeight(null);
						if(picture_width >= picture_height ) {
							// 横屏的情况  使用全覆盖
							System.out.println("fullSize(0,0,"+PPT_Width+","+PPT_Height+") -> "+"PictureIndex["+(i+1)+"]   " +" picture_width=["+picture_width+"]  picture_height=["+picture_height+"]  "+"PPT_Width =["+ PPT_Width + "]   PPT_Height=["+PPT_Height+"]" );

							BufferedImage buffImage = rotate(img, bigkeep==true?0:rotate_value);

							ByteArrayOutputStream bs = new ByteArrayOutputStream();
							ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
							ImageIO.write(buffImage, "jpg", imOut);
							InputStream ImageInputStream = new ByteArrayInputStream(bs.toByteArray());


							IEmbedImage image = slide.getShapes().appendEmbedImage(ShapeType.RECTANGLE, ImageInputStream, rect_fullSize);
							image.getLine().setFillType(FillFormatType.PICTURE);

							//获取第一张幻灯片，添加指定大小和位置的矩形文本框

							if(isShowName) {
								System.out.println("i-begin_index = "+ (i-begin_index));
								IAutoShape shape = ppt.getSlides().get(i-begin_index).getShapes().appendShape(ShapeType.RECTANGLE,new Rectangle((int)(PPT_Width/2-PPT_Width/2), 0, (int)PPT_Width, 50));


								//设置shape样式
								shape.getFill().setFillType(FillFormatType.NONE);
								shape.getShapeStyle().getLineColor().setColor(Color.red);
//						        shape.setRotation(-45);
								shape.getLocking().setSelectionProtection(true);
								shape.getLine().setFillType(FillFormatType.NONE);

								//添加文本到shape
								shape.getTextFrame().setText(fileNameNoPoint);
								PortionEx textRange = shape.getTextFrame().getTextRange();

								//设置文本水印样式
								textRange.getFill().setFillType(FillFormatType.SOLID);
								textRange.getFill().getSolidColor().setColor(Color.red);
								textRange.setFontHeight(50);
								System.out.println(" fileNameNoPoint = "+ fileNameNoPoint);


							}


						}else {

							BufferedImage buffImage = rotate(img, rotate_value);
							System.out.println("rotate90(0,0,"+PPT_Width+","+PPT_Height+") -> "+"PictureIndex["+(i+1)+"]   " +" picture_width=["+picture_width+"]  picture_height=["+picture_height+"]  "+" rotate_width=["+buffImage.getWidth()+"]  rotate_height=["+buffImage.getHeight()+"]  "+"PPT_Width =["+ PPT_Width + "]   PPT_Height=["+PPT_Height+"]" );

							ByteArrayOutputStream bs = new ByteArrayOutputStream();
							ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
							ImageIO.write(buffImage, "jpg", imOut);
							InputStream ImageInputStream = new ByteArrayInputStream(bs.toByteArray());

							// 方式1 竖屏的情况  保持比例   宽 小于 长   		// 从 原有的 高 变为  最高的情况
							// 把 图片旋转 90 °   再  全尺寸加入
							int picture_height_fixed = (int)PPT_Height;
							int picture_width_fixed = (int)(picture_width*(PPT_Height/picture_height));
							Rectangle2D selected_rect = null;
							if(rotate_value == 0 || rotate_value == 180) {
								selected_rect = new Rectangle2D.Double(PPT_Width/2-picture_width_fixed/2, 0,picture_width_fixed  , picture_height_fixed);

							} else {

								selected_rect = rect_fullSize;
							}

							System.out.println("selectedSize("+(PPT_Width/2-picture_width_fixed/2)+",0,"+picture_width_fixed+","+picture_height_fixed+") -> "+"PictureIndex["+(i+1)+"]   " +" picture_width=["+picture_width+"]  picture_height=["+picture_height+"]  "+"PPT_Width =["+ PPT_Width + "]   PPT_Height=["+PPT_Height+"]" );



							IEmbedImage image = slide.getShapes().appendEmbedImage(ShapeType.RECTANGLE, ImageInputStream, selected_rect);
							image.getLine().setFillType(FillFormatType.PICTURE);




							/*
							 * // 方式2 IEmbedImage image =
							 * slide.getShapes().appendEmbedImage(ShapeType.RECTANGLE, ImageInputStream,rect_fullSize);
							 *  image.getLine().setFillType(FillFormatType.PICTURE);
							 */


							if(isShowName) {

								IAutoShape shape = ppt.getSlides().get(i-begin_index).getShapes().appendShape(ShapeType.RECTANGLE,new Rectangle((int)(PPT_Width/2-PPT_Width/2), 0, (int)PPT_Width, 50));


								//设置shape样式
								shape.getFill().setFillType(FillFormatType.NONE);
								shape.getShapeStyle().getLineColor().setColor(Color.red);
//						        shape.setRotation(-45);
								shape.getLocking().setSelectionProtection(true);
								shape.getLine().setFillType(FillFormatType.NONE);

								//添加文本到shape
								shape.getTextFrame().setText(fileNameNoPoint);
								PortionEx textRange = shape.getTextFrame().getTextRange();

								//设置文本水印样式
								textRange.getFill().setFillType(FillFormatType.SOLID);
								textRange.getFill().getSolidColor().setColor(Color.red);
								textRange.setFontHeight(50);
								System.out.println(" fileNameNoPoint = "+ fileNameNoPoint);


							}

						}

						if(i == 0) {
							slide = ppt.getSlides().append();
							System.out.println("AA for 1 ppt.getSlides().size() = "+ppt.getSlides().size());
							continue;
						}
						if(i != pictureFileList.size()-1 && i != end_index ) {
							slide = ppt.getSlides().append();
							System.out.println("AA ppt.getSlides().size() = "+ppt.getSlides().size());

						}
						System.out.println("BB ppt.getSlides().size() = "+ppt.getSlides().size());


					}
					String local_pptx_path = curDirPath+File.separator+"PPTX_"+getTimeStamp()+".pptx";
					ppt.saveToFile(local_pptx_path, FileFormat.PPTX_2013);

					System.out.println("当前目录所有图片的 PPTX 文件以及生成!  PATH = "+local_pptx_path );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("当前目录下 "+curDirPath+" 不存在 jpg 和 png 照片 无法合成 pptx文件!");
			}


			return curRealFileList;
		}


		void	buildDynamic(int size , ArrayList<String> params){
			System.out.println("═══════════════════════════════════"+"动态命令"+"═══════════════════════════════════");
			int forCount = (int)(size/10) + 1;  //

			StringBuilder allCommandSB = new StringBuilder();
			for (int i = 0; i < forCount; i++) {
				ArrayList<String> commandList = new 	ArrayList<String>();
				StringBuilder tempSB = new StringBuilder();
				commandList.add(Cur_Bat_Name);
				commandList.addAll(params);

				int begin_index = i*10;
				int end_index = i*10 + 9;
				if(end_index > size ) {
					end_index = size - 1;
				}

				if(begin_index > end_index) {
					continue;
				}
				String order_str = begin_index+"_"+end_index;

				commandList.add(" "+order_str+"");

				for (int j = 0; j < commandList.size(); j++) {
					String itemStr = commandList.get(j);
					tempSB.append(itemStr+"  ");
				}

				if(i != forCount -1) {
					tempSB.append("  &&  ");
				}

				allCommandSB.append(tempSB.toString());


			}
			if(allCommandSB.toString().trim().endsWith("&&")) {
				System.out.println(allCommandSB +"   "+Cur_Bat_Name +"  #_29  delete");
			}else {
				System.out.println(allCommandSB +"  &&  "+Cur_Bat_Name +"  #_29  delete");
			}



		}
		@Override
		String simpleDesc() {
			return " // 把当前目录下文件 下的picture媒体文件 生成 PPTX文件   \n"
					+ Cur_Bat_Name	+ " #_28     [索引28]   // 把当前目录下文件 旋转0度 不显示文件名 下的picture媒体文件 生成 PPTX文件   \n"
					+ Cur_Bat_Name+ " #_28 0     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转0度 生成 PPTX文件   \n"
					+ Cur_Bat_Name+ " #_28 90     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转90度 生成 PPTX文件   \n"
					+ Cur_Bat_Name+ " #_28 180     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转180度 生成 PPTX文件   \n"
					+ Cur_Bat_Name	+ " #_28 270     [索引28]   // 把当前目录下文件 下的picture媒体文件 并旋转270度 生成 PPTX文件   \n"
					+ Cur_Bat_Name+ " #_28 name     [索引28]   // 把当前目录下文件 下的picture媒体文件 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name   + " #_28 name 0     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转0度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name  + " #_28 name 90     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转90度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name  + " #_28 name 180     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转180度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name  + " #_28 name 270     [索引28]   // 把当前目录下文件 下的picture媒体文件 旋转270度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name  + " #_28 keepbig name 0     [索引28]   // 把当前目录下文件  图片比例与电脑尺寸相同(PC 宽>高)的保持正向 比例不同的(手机 宽<高) 旋转0度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name  + " #_28 keepbig name 90     [索引28]   // 把当前目录下文件  图片比例与电脑尺寸相同(PC 宽>高)的保持正向 比例不同的(手机 宽<高) 旋转90度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name  + " #_28 keepbig name 180     [索引28]   // 把当前目录下文件  图片比例与电脑尺寸相同(PC 宽>高)的保持正向 比例不同的(手机 宽<高) 旋转180度 并添加文件名 生成 PPTX文件   \n"
					+ Cur_Bat_Name  + " #_28 keepbig name 270     [索引28]   // 把当前目录下文件  图片比例与电脑尺寸相同(PC 宽>高)的保持正向 比例不同的(手机 宽<高) 旋转270度 并添加文件名 生成 PPTX文件   \n"

					;

		}
	}


	// 在  包含 mp4 文件夹名称 把 无类型的文件 改为 mp4 , 对应 gif  jpg
	class Rercovery_Type_By_DirName_Rule_27 extends Basic_Rule {
		String originType;
		String targetType;

		Rercovery_Type_By_DirName_Rule_27() {
			super("#", 27, 5);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_27  _jpg   把包含 jpg 文件夹名称中的无类型文件改为 jpg格式名称\n"
					+ Cur_Bat_Name + " #_27  _gif  把包含 gif 文件夹名称中的无类型文件改为 gif格式名称\n"
					+ Cur_Bat_Name + " #_27  _mp4  把包含 mp4 文件夹名称中的无类型文件改为 mp4格式名称\n"
					;
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
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList, ArrayList<File> allSubRealFileList) {

			for (int i = 0; i < allSubDirFileList.size(); i++) {
				File curDirFile = allSubDirFileList.get(i);
				String curDirName = curDirFile.getName();

				// 如果当前文件夹 包含 过滤类型的名称 如 mp4   gif  jpg  那么才往下走
				if(!curDirName.contains(targetType)){
					continue;
				}


				File[] listFile = 	curDirFile.listFiles();

				if(listFile == null || listFile.length <= 0){

					continue;
				}

				for (int j = 0; j < listFile.length; j++) {
					File mRealFile= listFile[j];
					if(mRealFile.isFile() && !mRealFile.isDirectory() ){
						String  realFileNmae = mRealFile.getName();
						String type = getFileTypeWithPoint(realFileNmae);
						if("".equals(type)){
							String newName = realFileNmae+"."+targetType;
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
		ArrayList<File> inputParamFileList; //  .mp4  .jpg     对单独类型进行重命名

		boolean isSearchAllFile2CurDirFlag = false;  //是否检索 所有类型(空输入就是检索所有类型)

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
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList, ArrayList<File> allSubRealFileList) {
			if (isSearchAllFile2CurDirFlag) {
				// 比那里所有 类型的 文件 并 重新命名
				tryReNameByDir(allSubDirFileList);

			} else {
				tryReNameByDir_Type(allSubDirFileList,inputTypeList);
/*				for (int i = 0; i < inputTypeList.size(); i++) {
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
				}*/

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


		void tryReNameByDir_Type( ArrayList<File> allDirFile , ArrayList<String> mInputTypes) {

			for (int i = 0; i < allDirFile.size(); i++) {
				File dirFile = allDirFile.get(i);
				File[] subFile = dirFile.listFiles();
				if(subFile == null || subFile.length <= 0){

					continue;
				}

				// 用于计数
				Map<String,Integer> orderMap = new HashMap<String,Integer>() ;

				for (int j = 0; j < subFile.length; j++) {
					File realFileItem = subFile[j];
					if(realFileItem.isDirectory()){
						continue;
					}

					String FileTypeStr = getFileTypeWithPoint_unknow(realFileItem.getName());
					if(!mInputTypes.contains(FileTypeStr)){   // 如果 有输入类型  那么把不满足输入类型的 文件过滤掉
						continue;
					}
					Integer curIndex = orderMap.get(FileTypeStr);
					if(curIndex == null){
						orderMap.put(FileTypeStr,1);
						curIndex = 0;
					}

					curIndex = curIndex + 1;
					orderMap.put(FileTypeStr,curIndex);
					String newName = curIndex+FileTypeStr;
					System.out.println("依据文件夹 单独重命名文件");
					tryReName(realFileItem,newName);


				}
			}

		}

		void tryReNameByDir( ArrayList<File> allDirFile) {

			for (int i = 0; i < allDirFile.size(); i++) {
				File dirFile = allDirFile.get(i);
				File[] subFile = dirFile.listFiles();
				if(subFile == null || subFile.length <= 0){

					continue;
				}

				// 用于计数
				Map<String,Integer> orderMap = new HashMap<String,Integer>() ;

				for (int j = 0; j < subFile.length; j++) {
					File realFileItem = subFile[j];
					if(realFileItem.isDirectory()){
						continue;
					}

					String FileTypeStr = getFileTypeWithPoint_unknow(realFileItem.getName());
					Integer curIndex = orderMap.get(FileTypeStr);
					if(curIndex == null){
						orderMap.put(FileTypeStr,1);
						curIndex = 0;
					}

					curIndex = curIndex + 1;
					orderMap.put(FileTypeStr,curIndex);
					String newName = curIndex+FileTypeStr;
					System.out.println("依据文件夹 单独重命名文件");
					tryReName(realFileItem,newName);


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
			return "\n" + Cur_Bat_Name +
					"\n"+	 Cur_Bat_Name + " #_26           ### 把当前所有文件夹下 依据文件夹重新开始把实体文件重命名  ./A/aa.mp4  ./A/bb.mp4   ./A/xx.jpg >>> ./A/1.mp4  ./A/2.mp4   ./A/3.mp4  ./A/1.jpg (依据文件夹重新1开始)!  " +
					"\n"+	 Cur_Bat_Name +"#_26  .mp4         ### 把当前所有文件夹下 依据文件夹重新开始把实体文件重命名 .mp4类型文件 其他类型不变  ./A/aa.mp4  ./A/bb.mp4   ./A/xx.jpg >>> ./A/1.mp4  ./A/2.mp4   ./A/3.mp4  ./A/xx.jpg (依据文件夹 mp4类型文件重新1开始)!  "+


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
			return "\n" + Cur_Bat_Name + "  #_25  1992_2020   ##打开notepad输出当前1992年至2020年 年月历  \n" + Cur_Bat_Name
					+ "  #_25  1990_   ##打开notepad输出当前1990年至今年"+getCurrentYear()+" 年月历 \n"
					+ Cur_Bat_Name + " #_25  _2010   ##打开notepad输出 1992(默认)至2010年年月历 \n ";
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
					if(isNumeric(origin) && !"".equals(origin)) {
						originType = Integer.parseInt(origin);
					}

					if(isNumeric(target) && !"".equals(target)) {
						targetType = Integer.parseInt(target);
					}
				} else {
					String[] parmas = inputFileTypeParams.split("_");
					System.out.println(
							"item=" + inputFileTypeParams + "   origin=" + parmas[0] + "     target=" + parmas[1]);
					if(parmas.length >= 2) {

						if(isNumeric(parmas[0]) ) {
							originType = Integer.parseInt(parmas[0]);
						}

						if(isNumeric(parmas[1])) {
							targetType = Integer.parseInt(parmas[1]);
						}

					}

				}

				Flag = true;

			}


			return super.initParamsWithInputList(inputParamList) && Flag;
		}

		@Override
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList, ArrayList<File> allSubRealFileList) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			if(originType == -1 ) {  // 没有获取到 初始化值 那么 默认就是 1992
				originType = 1992;
			}

			if(targetType == -1 ) {  // 没有获取到 初始化值 那么 默认就是 1992
				targetType =  getCurrentYear();
			}

			// 确保  origin 是 小于 targetType 的
			if(originType > targetType) {
				targetType = originType + targetType;
				originType = targetType - originType;
				targetType = targetType - originType;
			}
			StringBuilder sb = new StringBuilder();

			for (int i = targetType; i >= originType; i--) {
				sb.append("   \n");
				sb.append("   \n");
				sb.append("   \n");

				for (int j = 12; j <= 1 ; j--) {
					sb.append("### "+i+"."+(j>9?""+j:"0"+j));

					sb.append("   \n");
					sb.append("   \n");
				}

				sb.append("## "+i);
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
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
											  ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub

			for (int i = 0; i < curFileList.size(); i++) {
				File sonFile = curFileList.get(i);


				File middle_dir = new File(sonFile.getAbsoluteFile() + File.separator + middle_dir_name);
				if (!middle_dir.exists()) {
					middle_dir.mkdirs();
					System.out.println(" FFFFF  middle_dir = "+ middle_dir.getAbsolutePath());
				}

				File sunFile = new File(middle_dir.getAbsolutePath() + File.separator + sonFile.getName());

				if (sonFile.isDirectory()) {

					ArrayList<File> searchRealList =getAllSubFile(sonFile);
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

		final HashMap<String, String> mFileTypes;   // 魔数 ----- 类型
		final HashMap<String, ArrayList<String>> mSameMoShu_ArrType_Map;  // 使用相同魔数 8位字符的文件
		ArrayList<String> NoMoShuTypeList ;  // 没有魔数的类型 比如 txt
		boolean isFixToRealType = false;

		CheckFileRealFormat_Rule_23() {
			super("#", 23, 4); //
			mFileTypes = new HashMap<String, String>();
			mSameMoShu_ArrType_Map  = new HashMap<String, ArrayList<String>>();
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
				if(inputParam.trim().toLowerCase().startsWith("fix2real_true")) {
					isFixToRealType = true;
				} else {
					isFixToRealType = false;
				}

			}
			return super.initParamsWithInputList(inputParamList);
		}


		@Override
		String simpleDesc() {

			return Cur_Bat_Name + " #_"+rule_index+"    ### 对当前目录的文件进行真实类型的检测[通过魔数字]并打印那些类型和魔数不一样文件的列表信息  \n"
					+ Cur_Bat_Name + " #_"+rule_index+"  fix2real_false    ### 对当前目录的文件进行真实类型的检测[通过魔数字]并打印那些类型和魔数不一样文件的列表信息 \n"

					+ Cur_Bat_Name + " #_"+rule_index+"  fix2real_true    ### 对当前目录的文件进行真实类型的检测[通过魔数字]并修正那些类型和魔数不一样文件的列表信息 \n"

					;
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
											  HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
											  ArrayList<File> curRealFileList) {
			// TODO Auto-generated method stub
			int different_type_file_index = 1;
			ArrayList<File> differentRealTypeFileList = new ArrayList<File>();
			System.out.println("════════════════════════"+" 当前目录文件的 魔数情况 Begin "+"════════════════════════");
			curRealFileList.sort(new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					// TODO Auto-generated method stub
					String o1_type  = getFileTypeWithPoint(o1.getName());
					String o2_type  = getFileTypeWithPoint(o2.getName());
					return o1_type.compareTo(o2_type);
				}
			});
			for (int i = 0; i < curRealFileList.size(); i++) {

				File realFile = curRealFileList.get(i);
				String originFileType = getFileTypeWithPoint(realFile.getName()).toLowerCase();
				if("".equals(originFileType)) {
					originFileType = "unknow";
				}
				String fileNameWithoutPoint = getFileTypeWithPoint(realFile.getName()).trim().toLowerCase();
				String realfileType = getFileType(realFile.getAbsolutePath());

				if(realfileType == null ) {

					realfileType = "unknow";
//		            	if("".equals(originFileType)) {
//		            		fileType = "";
//		            	}else {
//		            		fileType = originFileType;
//		            	}
				}
				String moshu = getFileHeader(realFile.getAbsolutePath());

				System.out.println("Index["+i+"]     showType=["+ originFileType+"]      realType=[."+realfileType+"]      MoShu=["+moshu+"]     PATH=["+realFile.getAbsolutePath()+"]");


				/*
				 * final HashMap<String, String> mFileTypes; // 魔数 ----- 类型 final
				 * HashMap<String, ArrayList<String>> mSameMoShu_ArrType_Map; // 使用相同魔数 8位字符的文件
				 * ArrayList<String> NoMoShuTypeList ; // 没有魔数的类型 比如 txt
				 */

				if(!originFileType.equals(("."+realfileType).toLowerCase())) {

					if("unknow".equals(realfileType) &&  NoMoShuTypeList.contains(originFileType.replace(".","").toLowerCase())) {
						continue;
					}
					// 如果当前的 文件类型 没有魔术 那么 不对比

					ArrayList<String> sameTypeList = 	 mSameMoShu_ArrType_Map.get(moshu);

					// 如果 两个 文件类型 使用 相同的  type  那么 也排除 这样的文件
					if(sameTypeList != null &&  sameTypeList.contains(originFileType.replace(".", "").toLowerCase())) {

						continue;
					}



					differentRealTypeFileList.add(realFile);
					different_type_file_index++;



				}


			}

			System.out.println("════════════════════════"+" 当前目录文件的 魔数情况 End "+"════════════════════════");


			if(differentRealTypeFileList.size() > 0) {
				System.out.println("════════════"+" 打印 【"+ differentRealTypeFileList.size() +"】 个 显示的类型 和 真实的类型不同的文件 Begin "+"════════════");

				for (int i = 0; i < differentRealTypeFileList.size(); i++) {
					File realType_showType_Diff_File = differentRealTypeFileList.get(i);
					String oldName = realType_showType_Diff_File.getName();
					String showTypeStr  = getFileTypeWithPoint(realType_showType_Diff_File.getName());
					String realType = getFileType(realType_showType_Diff_File.getAbsolutePath());
					if(realType == null) {
						realType = "";
					}

					String originFileType = getFileTypeWithPoint(realType_showType_Diff_File.getName());
					String moshu = getFileHeader(realType_showType_Diff_File.getAbsolutePath());
					String fileNameNoPoint = getFileNameNoPointNoLowerCase(realType_showType_Diff_File.getName());

					String realfileType = getFileType(realType_showType_Diff_File.getAbsolutePath());

					if(realfileType == null ) {
						realfileType = "unknow";
					}

					System.out.println("Diff_Index["+i+"]     showType=["+ originFileType+"]      realType=[."+realfileType+"]      MoShu=["+moshu+"]     PATH=["+realType_showType_Diff_File.getAbsolutePath()+"]  initMoshuTypeItem(\""+moshu+"\", \""+(realfileType.equals("unknow")?showTypeStr.replace(".", ""):realfileType)+"\"); ");




				}
				System.out.println("════════════"+" 打印 【"+ differentRealTypeFileList.size() +"】 个 显示的类型 和 真实的类型不同的文件 End "+"════════════");

			}else {

				System.out.println("════════════恭喜  当前所有文件 真实类型 和 显示类型 全都相同 "+"════════════");

			}

			if(isFixToRealType && differentRealTypeFileList.size() > 0) {
				System.out.println("═══════════════════  执行 ["+differentRealTypeFileList.size()+"]个文件 改为真实类型的 操作 Begin ══════════════════");
				for (int i = 0; i < differentRealTypeFileList.size(); i++) {
					File realType_showType_Diff_File = differentRealTypeFileList.get(i);
					String oldName = realType_showType_Diff_File.getName();
					String showTypeStr  = getFileTypeWithPoint(realType_showType_Diff_File.getName());
					String realType = getFileType(realType_showType_Diff_File.getAbsolutePath());
					if(realType == null) {
						realType = "";
					}
					String moshu = getFileHeader(realType_showType_Diff_File.getAbsolutePath());
					String fileNameNoPoint = getFileNameNoPointNoLowerCase(realType_showType_Diff_File.getName());

					String newFileName = fileNameNoPoint +"."+realType;
					tryReName(realType_showType_Diff_File, newFileName);
					System.out.println("Index["+i+"]     showType=["+ showTypeStr+"]      realType=[."+realType+"]      MoShu=["+moshu+"]  OldName=["+oldName+"]    NewName["+newFileName+"]     isFixToRealType=["+isFixToRealType+"]    PATH=["+realType_showType_Diff_File.getAbsolutePath()+"]");

				}
				System.out.println("═══════════════════  执行 ["+differentRealTypeFileList.size()+"]个文件 改为真实类型的 操作 End ══════════════════");

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

		void initMoshuTypeItem(String key , String value){
			mFileTypes.put(key.toUpperCase(), value.toLowerCase());
		}

		/**
		 * @param filePath 文件路径
		 * @return 文件头信息
		 * @author wlx
		 * <p>
		 * 方法描述：根据文件路径获取文件头信息
		 */
		public  String getFileType(String filePath) {
//	        System.out.println(getFileHeader(filePath));
//	        System.out.println(mFileTypes.get(getFileHeader(filePath)));
			return mFileTypes.get(getFileHeader(filePath));
		}

		public void InitInitSameMoShuMap_NoMoShuTypeList(){
//	    	mSameMoShu_ArrType_Map
// NoMoShuTypeList           abcdef

			//==============================================
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
			//==============================================
			String moshu_zip="504B0304";
			ArrayList<String> same_zip_TypeList = new ArrayList<String>();
			same_zip_TypeList.add("zip");
			same_zip_TypeList.add("xlsx");
			same_zip_TypeList.add("pptx");
			same_zip_TypeList.add("nupkg");
			same_zip_TypeList.add("jar");
			same_zip_TypeList.add("dotm");
			same_zip_TypeList.add("apk");
			mSameMoShu_ArrType_Map.put(moshu_zip, same_zip_TypeList);


			String moshu_exe="4D5A9000";
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



			String moshu_so="7F454C46";
			ArrayList<String> same_so_TypeList = new ArrayList<String>();
			same_so_TypeList.add("so");
			same_so_TypeList.add("elf");
			mSameMoShu_ArrType_Map.put(moshu_so, same_so_TypeList);


			String moshu_xls="D0CF11E0";
			ArrayList<String> same_xls_TypeList = new ArrayList<String>();
			same_xls_TypeList.add("xls");
			same_xls_TypeList.add("doc");
			same_xls_TypeList.add("ptt");
			mSameMoShu_ArrType_Map.put(moshu_xls, same_xls_TypeList);


			String moshu_xml="3C3F786D";
			ArrayList<String> same_xml_TypeList = new ArrayList<String>();
			same_xml_TypeList.add("xml");
			same_xml_TypeList.add("pom");
			same_xml_TypeList.add("manifest");
			same_xml_TypeList.add("iml");
			same_xml_TypeList.add("filters");
			same_xml_TypeList.add("config");
			same_xml_TypeList.add("vcxproj");


			mSameMoShu_ArrType_Map.put(moshu_xml, same_xml_TypeList);



			String moshu_JAVA="2F2A0A20";
			ArrayList<String> same_Java_TypeList = new ArrayList<String>();
			same_Java_TypeList.add("java");
			same_Java_TypeList.add("kt");
			same_Java_TypeList.add("groovy");
			same_Java_TypeList.add("pm");

			mSameMoShu_ArrType_Map.put(moshu_JAVA, same_Java_TypeList);

			String moshu_inf="FFFE5B00";
			ArrayList<String> same_inf_TypeList = new ArrayList<String>();
			same_inf_TypeList.add("inf");
			same_inf_TypeList.add("inf_loc");
			mSameMoShu_ArrType_Map.put(moshu_inf, same_inf_TypeList);


			String moshu_pdb="4D696372";
			ArrayList<String> same_pdb_TypeList = new ArrayList<String>();
			same_pdb_TypeList.add("pdb");
			same_pdb_TypeList.add("ilpdb");
			same_pdb_TypeList.add("iltocpdb");


			mSameMoShu_ArrType_Map.put(moshu_pdb, same_pdb_TypeList);


			String moshu_admx="EFBBBF3C";
			ArrayList<String> same_admx_TypeList = new ArrayList<String>();
			same_admx_TypeList.add("nuspec");
			same_admx_TypeList.add("admx");
			mSameMoShu_ArrType_Map.put(moshu_admx, same_pdb_TypeList);


			String moshu_swift="7F454C46";
			ArrayList<String> same_swift_TypeList = new ArrayList<String>();
			same_swift_TypeList.add("swift");
			same_swift_TypeList.add("gyb");
			same_swift_TypeList.add("sil");
			mSameMoShu_ArrType_Map.put(moshu_swift, same_so_TypeList);


			//==============================================
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


		boolean isTypeUsedSameMoShu(String showType , String realType) {
			boolean isSameMoShu = false;




			return false;
		}

		/**
		 * @param filePath 文件路径
		 * @return 文件头信息
		 * @author wlx
		 * <p>
		 * 方法描述：根据文件路径获取文件头信息
		 */
		public  String getFileHeader(String filePath) {
			FileInputStream is = null;
			String value = null;
			try {
				is = new FileInputStream(filePath);
				byte[] b = new byte[4];
				/*
				 * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
				 * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
				 * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
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
		 * <p>
		 * 方法描述：将要读取文件头信息的文件的byte数组转换成string类型表示
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

		Rename_Img_WithSize_Rule_21() {
			super("#", 21, 4); //
			fliterTypeList = new ArrayList<String>();
			mSrcFileImage = new ArrayList<File>();
		}

		@Override
		boolean initParams4InputParam(String inputParam) {
			boolean isEmptyTypeInput = false;

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

			isEmptyTypeInput = !(isGifInput || isWebpInput || isPngInput || isJpgInput);
			if (isEmptyTypeInput) {
				fliterTypeList.add(".webp");
				fliterTypeList.add(".jpg");
				fliterTypeList.add(".png");
				fliterTypeList.add(".gif");
			}

			return super.initParams4InputParam(inputParam);
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

				// 当前文件的 宽高
				String str_width_x_high = calculateSizeStr(width, high);
				String newName = str_width_x_high + "_" + fileName;
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

				if(!isPort){
					int temp = high;
					high = width ;
					width = temp;
				}
/*				try {
					BufferedImage sourceImg = ImageIO.read(new FileInputStream(imageFile.getAbsolutePath()));
						 high = sourceImg.getHeight();
                   		 width = sourceImg.getWidth();
				} catch (IOException e) {
					e.printStackTrace();
				}*/



				System.out.println("1_Rule20_Land_Port Index["+i+"]  width["+width+"]" + " high["+high+"]   high >= width["+(high >= width)+"]  Path["+imageFile.getAbsolutePath()+"]");
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

	/**
		 * 图片翻转时，计算图片翻转到正常显示需旋转角度
		 */
		public boolean getRotateAngleForPhoto(String fileName){

boolean isPort  = true;
			File file = new File(fileName);

			int angel = 0;
			Metadata metadata;

			try{
				metadata = JpegMetadataReader.readMetadata(file);
				metadata.getDirectories();

				// zukgit_directory  [Exif IFD0] - Orientation = Right side, top (Rotate 90 CW)
				for (Directory directory : metadata.getDirectories()) {
					for (Tag tag : directory.getTags()) {
						//格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
						System.out.format("zukgit_directory  [%s] - %s = %s\n", directory.getName(), tag.getTagName(), tag.getDescription());

if("Exif IFD0".equals(directory.getName())){
	String orientation = directory.getString(ExifIFD0Directory.TAG_ORIENTATION);
	System.out.println("ZZOrientation = "+ orientation);

	if("1".equals(orientation) ){   //  90度 和 270度  宽高对调了
		angel = 0;

	} else	if("6".equals(orientation) ){
		//6旋转90
		angel = 90;
		isPort = false;
	} else	if("3".equals(orientation) ){
		//3旋转180
		angel = 180;
	} else	if("8".equals(orientation) ){
		//8旋转90
		angel = 270;
		isPort = false;
	}
return isPort;
}
					}
//					if (directory.hasErrors()) {
//						for (String error : directory.getErrors()) {
//							System.err.format("ERROR: %s", error);
//						}
//					}
				}


/*				if(directory.containsTag(ExifDirectory.TAG_ORIENTATION)){
					// Exif信息中方向　　
					int orientation = directory.getInt(ExifDirectory.TAG_ORIENTATION);
					// 原图片的方向信息
					if(6 == orientation ){
						//6旋转90
						angel = 90;
					}else if( 3 == orientation){
						//3旋转180
						angel = 180;
					}else if( 8 == orientation){
						//8旋转90
						angel = 270;
					}
				}*/

			} catch(JpegProcessingException e){
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
//			System.out.println("图片旋转角度：" + angel);
			return isPort;
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
			return "\n" + Cur_Bat_Name + "  #_13_mp4    ### 动态计算当前文件夹中所有子文件中的mp4文件夹中的 mp4文件个数  并在当前目录生成html文件 \n"
					+ Cur_Bat_Name + "  #_13_jpg    ### 动态计算当前文件夹中所有子文件中的jpg文件夹中的 jpg文件个数 并在当前目录生成html文件\n"
					+ Cur_Bat_Name + "  #_13_gif    ### 动态计算当前文件夹中所有子文件中的gif文件夹中的 gif文件个数 并在当前目录生成html文件\n"
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
		int operaType; // 0-unknow 1--mp4 2--jpg 3--gif

		ArrayList<File> mp4HtmlTemplate_FileList;
		ArrayList<File> jpgHtmlTemplate_FileList;
		ArrayList<File> gifHtmlTemplate_FileList;

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
			mp4HtmlTemplate_FileList = new ArrayList<File>();
			jpgHtmlTemplate_FileList = new ArrayList<File>();
			gifHtmlTemplate_FileList = new ArrayList<File>();

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
		ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList, ArrayList<File> allSubRealFileList) {
			if (operaDirFileList.size() == 0) {
				System.out.println("当前用户没有输入执行的目录名称,请重新输入B!");

				for (int i = 0; i <allSubDirFileList.size() ; i++) {
					File dirFile = allSubDirFileList.get(i);
					String dirName = dirFile.getName().toLowerCase();
//					int operaType; // 0-unknow 1--mp4 2--jpg 3--gif
					if(operaType == 1 && dirName.contains("mp4")){
						operaDirFileList.add(dirFile);
					}else if(operaType == 2 &&  dirName.contains("jpg")){
						operaDirFileList.add(dirFile);
					}else if(operaType == 3 &&  dirName.contains("gif")){
						operaDirFileList.add(dirFile);
					}


				}




			}
			if (operaDirFileList.size() == 0) {
				System.out.println("当前用户没有输入执行的目录名称,请重新输入C!");
				return null;
			}

			for (int i = 0; i < operaDirFileList.size(); i++) {
				File operaDirFile = operaDirFileList.get(i);
				OperationHtmlMedia(operaDirFile);
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
			for (int i = 0; i < operaDirFileList.size(); i++) {
				File operaDirFile = operaDirFileList.get(i);
				OperationHtmlMedia(operaDirFile);
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

		@Override
		String simpleDesc() {
			return "\n"
					+ Cur_Bat_Name + "  #_12_mp4      ### 把当前可能的目录mp4文件生成 html 播放文件  \n"
					+ Cur_Bat_Name + "  #_12_gif      ### 把当前可能的目录gif文件生成 html 播放文件  \n"
					+ Cur_Bat_Name + "  #_12_jpg      ### 把当前可能的目录jpg文件生成 html 播放文件  \n"
					+ Cur_Bat_Name + "  #_12_mp4   <目标文件夹目录>   ### 把当前目录mp4文件生成 html 播放文件  \n"
					+ Cur_Bat_Name + "  #_12_gif   <目标文件夹目录>   ### 把当前目录gif文件生成 html 播放文件  \n"
					+ Cur_Bat_Name + "  #_12_jpg   <目标文件夹目录>   ### 把当前目录jpg文件生成 html 播放文件  \n";
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

		boolean isZVIOperation = false;  // 是否是 1970ZVI 文件夹 操作
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
			
			if (inputParam.toLowerCase().contains("zvi") &&  inputParam.toLowerCase().contains("good") ) {
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
					+ " #_7_good_batch   [索引7]   // 把当前目录所有文件进行解密 解密文件在新的【 固定文件夹 good_batch 】中 适合批量处理 "+ "\n" + Cur_Bat_Name
					+ " #_7_good_zvi   [索引7]   // 把当前目录中的 1970ZVI  解密到新的文件夹在新的【 固定文件夹 1970ZVI_Good 】中 适合批量处理 \n" 
					;

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
		
		File getContainDirName(File curShellDir , String name , boolean isDir) {
			File targetFile = null;
			String absShellPath = curShellDir.getAbsolutePath();
			System.out.println(" 当前Shell的路径为: curDirFile =  "+ absShellPath);
			File[] fileArr = curShellDir.listFiles();
			if(fileArr != null) {
				
				for (int i = 0; i < fileArr.length; i++) {
					File fileItem = fileArr[i];
					String fileName = fileItem.getName();
					if(isDir) {
						if(fileItem.isDirectory() && fileItem.exists() ) {
			                  if(fileName.equals(name)) {
			                	  return fileItem;
			                  }
						}
					}else {
						if(fileItem.isFile() && fileItem.exists()) {
			                  if(fileName.equals(name)) {
			                	  return fileItem;
			                  }
						}
						
					}
					
				}
			}
			
			
			return targetFile;
			
		}

		void jiemi1970ZVIDir(File m1970ZVI_DirFile , ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				 ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
			String date = df.format(new Date());
			String CurBadDirName = "1970ZVI_Good" ;
			
			
			// 1970ZVI_Good
			File curBadDirFile = new File(curDirFile.getAbsolutePath() + File.separator + CurBadDirName);
			
			curBadDirFile.mkdirs();
			                                                   // 1970ZVI路径  /C/1970ZVI
			String oldBasePath = curDirFile.getAbsolutePath(); // 原有的路径 /C
			String newBasePath = curBadDirFile.getAbsolutePath(); // 生成的 新路径 /C/1970ZVI_Good
			
			
			for (int i = 0; i < curDirList.size(); i++) {
				File oldDirFile = curDirList.get(i); // 原有的要解密文件
				String oldDirPath = oldDirFile.getAbsolutePath();
				if(!oldDirPath.contains("1970ZVI")) {   // 如果当前目录 不包含 1970ZVI   那么 不创建这个目录
					continue;
				}
				
				
				// 把 原有的   1970ZVI 路径 变化为 1970ZVI_Good 路径
				String newDirFilePath = oldDirFile.getAbsolutePath().replace(oldBasePath+File.separator+"1970ZVI", newBasePath);
				System.out.println("AAAAAAAA  newBasePath="+newBasePath +"  newDirFilePath="+ newDirFilePath);


				System.out.println("oldBasePath/1970ZVI --->  "+oldBasePath+File.separator+"1970ZVI   " + "oldDirFile["+i+"]  = " +oldDirFile.getAbsolutePath()+"  newDirFilePath["+i+"] = "+ newDirFilePath);
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

					// 如果原文件全路径 没有 1970ZVI     那么 不操作 这个文件 
					if (!oldRealFile_Path.contains("1970ZVI")) {  
						continue;
					}
					
        	String newRealFilePath = oldRealFile.getAbsolutePath().replace("1970ZVI", "1970ZVI_Good");


						String batch_fileName = oldRealFile.getName();
//						File newRealFile = new File(newBasePath + File.separator + batch_fileName);   //  子文件夹取消 消失

							File newRealFile = new File(newRealFilePath);  //  子文件夹存在
							// 解密操作
							System.out.println("执行当前 ZVI解密操作  batch_fileName = "+ batch_fileName +"     newBasePath="+newBasePath+"    newRealFile="+newRealFile.getAbsolutePath());
		
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
			System.out.println(" curDirFile ="+ curDirFile.getAbsolutePath());
			
			if(isZVIOperation) {
			File zviFileDir	= getContainDirName(curDirFile,"1970ZVI" , true);
				if( zviFileDir != null) {
					
					jiemi1970ZVIDir(zviFileDir ,curFileList , subFileTypeMap ,curDirList ,curRealFileList   );
					
				}else {
					System.out.println(" 当前目录没有 1970ZVI 文件夹 无法执行   "+Cur_Bat_Name+" #_7_good_zvi   ## 批量解密操作！！！");
				}
				
				return ;
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

			
			System.out.println("执行当前所有文件 解密操作  curDirList.size() = "+ curDirList.size());

			for (int i = 0; i < curDirList.size(); i++) {
				File oldDirFile = curDirList.get(i); // 原有的要解密文件

//				执行当前所有文件  解密操作  [0] = D:\BaiduNetdiskDownload\公式\bad_batch\A
				System.out.println("执行当前所有文件  解密操作  ["+i+"] = "+ oldDirFile.getAbsolutePath());
				if (!isBatchOperation) { // 如果 不是 batch 那么会创建文件夹
					// 如果是当前目录下多 子文件夹 那么就把在* 的 情况下会 创建这个文件夹 事实上在 batch的情况下不需要这个文件夹
					String newDirFilePath = oldDirFile.getAbsolutePath().replace(oldBasePath, newBasePath);
					System.out.println("AAAAAAAA  newBasePath="+newBasePath +"  newDirFilePath="+ newDirFilePath);


					System.out.println("oldDirFile["+i+"]  = " +oldDirFile.getAbsolutePath()+"  newDirFilePath["+i+"] = "+ newDirFilePath);
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
执行当前 解密操作
执行当前所有文件  解密操作  [0] = D:\BaiduNetdiskDownload\公式\bad_batch\A  == oldDirFile.getAbsolutePath()
batch_fileName = A.pdf  == batch_fileName
newBasePath=D:\BaiduNetdiskDownload\公式\bad_batch\good_batch
newBasePath=D:\BaiduNetdiskDownload\公式\bad_batch\good_batch\A  =====> newBasePath 应该还有一个 A
newRealFilePath = D:\BaiduNetdiskDownload\公式\bad_batch\good_batch\A\A.pdf
newRealFile=D:\BaiduNetdiskDownload\公式\bad_batch\good_batch\A.pdf
*/


						String batch_fileName = oldRealFile.getName();
//						File newRealFile = new File(newBasePath + File.separator + batch_fileName);   //  子文件夹取消 消失

							File newRealFile = new File(newRealFilePath);  //  子文件夹存在
							// 解密操作
							System.out.println("执行当前 解密操作  batch_fileName = "+ batch_fileName +"     newBasePath="+newBasePath+"    newRealFile="+newRealFile.getAbsolutePath());
							System.out.println(" newRealFilePath = "+ newRealFilePath);
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
					System.out.println("createDecryFile goodFile = "+goodFile.getAbsolutePath()  +"tempDirPath = "+tempDirPath);
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

		boolean isOrder_Padding5 = false;  // 是否 以 00000.jpg  00001.jpg  00002.jpg 这样命名
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
						String newNamePre = (isOrder_Padding5?getPaddingIntString(index,5,"0",true):index) + "_";
						File curFile = fileArr.get(i);
						String curFileName = curFile.getName();
						String newName = "";
						if (keepOriginalName) {
							if (isOrder) { // 按顺序依次 不按 type了 一直走
								newName =(isOrder_Padding5?getPaddingIntString(fileOrderIndex,5,"0",true):fileOrderIndex)   + "_" + curFileName+typeStr;
							} else {
								newName = newNamePre + curFileName+typeStr;
							}
						} else {
							// 如果不保留名称 那么没有类型的文件 将只有 序号 没有类型
							if ("unknow".equals(typeStr)) {
								newName = (isOrder_Padding5?getPaddingIntString(index,5,"0",true):index) + "";
							} else {
								if (isOrder) { // 按顺序依次 不按 type了 一直走
									newName = (isOrder_Padding5?getPaddingIntString(fileOrderIndex,5,"0",true):fileOrderIndex) + typeStr;
								} else {
									newName = (isOrder_Padding5?getPaddingIntString(index,5,"0",true):index) + typeStr;
								}

							}
						}
						if (tryReName(curFile, newName)) {
							executeFlag = true;
						}
						System.out.println("index=["+i+"] typeStr["+ typeStr+"]  newName["+newName+"]  keepOriginalName=["+keepOriginalName+"]  isOrder["+isOrder+"]  isOrder_Padding5["+isOrder_Padding5+"]" );
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
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order_20_padding5" + "    [指定开始索引 "
						+ index + "]  描述: " + desc_true_pading1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false" + "    [索引 " + index
						+ "]  描述:" + desc_false + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order" + "    [索引 " + index
						+ "]  描述:" + desc_false_1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order_padding5" + "    [索引 " + index
						+ "]  描述:" + desc_false_pading1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order_10" + "    [指定开始索引 "
						+ index + "]  描述:" + desc_false_2 + "\n";


			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index + "_true" + "    [索引 " + index + "]  描述:"
						+ desc_true;
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order" + "    [索引 " + index
						+ "]  描述: " + desc_true_1;
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order_padding5" + "    [索引 " + index
						+ "]  描述: " + desc_true_1;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false" + "    [索引 " + index
						+ "]  描述:" + desc_false;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false_order" + "    [索引 " + index
						+ "]  描述:" + desc_false_1;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false_order_padding5" + "    [索引 " + index
						+ "]  描述:" + desc_false_pading1;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false_order_padding5" + "    [索引 " + index
						+ "]  描述:" + desc_true_pading1;
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

		abstract boolean allowEmptyDirFileList();  // 是否允许当前的目录下的文件为空

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

	static void writeContentToFile(File file, String strParam) {

		try {
			if (file != null && !file.exists()) {
				System.out.println("创建文件:  "+file.getAbsolutePath());
				if(!file.getParentFile().exists()) {
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

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static boolean isNumeric(String str ) {

		if(str == null ) {
			return false;
		}

		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		if(str.equals("")) {
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
			System.out.println("源文件:"+encryptFile.getAbsolutePath()+"    解密文件:" + decryptFile.getAbsolutePath() + "  密文加密字节大小:" + TEMP_Rule7.length + "   解密密文之后的明文大小:"
					+ decrypt_bytes.length);

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

			if (typeFileList.size() == 0 ) {
				System.out.println("未能搜索到类型列表匹配的文件:  " + Rule_Identify_TypeIndexList.get(i));
				if(!curApplayRule.allowEmptyDirFileList()) {  // 是否允许当前目录下的文件夹为空
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
		if(subFileList == null) {
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

	static int getCurrentYear() {

		SimpleDateFormat df = new SimpleDateFormat("YYYY");

		return Integer.parseInt(df.format(new Date()));

	}

	// A1  ..... A2.
	static String get_Bat_Sh_FlagNumber(String mCur_Bat_Name){
		String mCharNumber = "error";
		String curBat =mCur_Bat_Name;
		if(mCur_Bat_Name.contains(".sh")){
			curBat = curBat.replace(".sh","");
		}

		if(mCur_Bat_Name.contains(".bat")){
			curBat = curBat.replace(".bat","");
		}
		if(curBat.contains("_")){
			String[] arrNameList =    curBat.split("_");
			mCharNumber =   arrNameList[arrNameList.length-1];
		}else{
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
	 * @param command
	 * @return
	 */
	public static int execute_Mac(String command) {
		String[] cmd = {"/bin/bash","-c",command};
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
	 * @param src 源矩形
	 * @param angel 角度
	 * @return 目标矩形
	 */
	static Rectangle CalcRotatedSize(Rectangle src, int angel) {
		double cos = Math.abs(Math.cos(Math.toRadians(angel)));
		double sin = Math.abs(Math.sin(Math.toRadians(angel)));
		int des_width = (int)(src.width *  cos) + (int)(src.height * sin);
		int des_height =  (int)(src.height *  cos) + (int)(src.width * sin);
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}

	/**
	 * 旋转角度
	 * @param src 源图片
	 * @param angel 角度
	 * @return 目标图片
	 */
	public static BufferedImage rotate(Image src, int angel) {
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		// calculate the new image size

		Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);

		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height,
				BufferedImage.TYPE_INT_RGB);
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

	//判断是否是数字
	static boolean isNumericFirstChar(String s){
		return Character.isDigit(s.charAt(0));
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

	/**
	 * 汉字转为拼音 空间以下划线_分割
	 * 1.每个汉字前面添加_
	 * 2.每个汉字后面添加_
	 * 3.把所有的两个__ 下划线转为 一个下划线
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
					String charStr = newChar[i]+"";
					if(charStr.equals("《")  ){
						pinyinStr += "<";
						continue;
					}else  if(charStr.equals("》")  ){
						pinyinStr += ">";
						continue;
					}else  if(charStr.equals("，")  ){
						pinyinStr += ",";
						continue;
					}else  if(charStr.equals("：")  ){
						pinyinStr += ":";
						continue;
					}else  if(charStr.equals("“")  ){
						pinyinStr += "\"";
						continue;
					}else  if(charStr.equals("￥")  ){
						pinyinStr += "$";
						continue;
					}else  if(charStr.equals("？")  ){
						pinyinStr += "?";
						continue;
					}else  if(charStr.equals("；")  ){
						pinyinStr += ";";
						continue;
					}else  if(charStr.equals("【")  ){
						pinyinStr += "[";
						continue;
					}else  if(charStr.equals("】")  ){
						pinyinStr += "]";
						continue;
					}else  if(charStr.equals("、")  ){
						pinyinStr += ",";
						continue;
					}




					String[] arrChar =   PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
					if(arrChar == null){
						System.out.println("pinyinStr = "+ null);
						continue;
					}
					pinyinStr += "_" + arrChar[0] + "_"; // [0] 标识当前拼音 汉-> han
//                    System.out.println("pinyinStr = "+ pinyinStr);
//                    System.out.println("xxxxxxxxxx ");
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {  // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
				pinyinStr += newChar[i];
			}
		}
		while (pinyinStr.contains("__")) {
			pinyinStr = pinyinStr.replaceAll("__", "_");
//            System.out.println("pinyinStr1 = " + pinyinStr);
		}

		while (pinyinStr.contains("u:")) {  // 女转为 nu:   绿 lu:   需要转为 nv  lv
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


	static void NotePadOpenTargetFile(String absPath){
		String commandNotead = "";
		if(CUR_OS_TYPE == OS_TYPE.Windows){
			commandNotead = "cmd.exe /c start   Notepad++.exe " + absPath;
			execCMD(commandNotead);

		}else if(CUR_OS_TYPE == OS_TYPE.Linux){
			commandNotead  = " gedit " + absPath;
		}else if(CUR_OS_TYPE == OS_TYPE.MacOS){
			commandNotead  = "/Applications/UltraEdit  " + absPath;
			execute_Mac(commandNotead);
		}
	}

}