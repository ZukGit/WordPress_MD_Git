
import cn.hutool.core.util.ImageUtil;
import com.luciad.imageio.webp.WebPReadParam;
//import com.sun.tools.sjavac.CopyFile;

import net.jimmc.jshortcut.JShellLink;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;

import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.security.Key;
import java.security.Security;

// ����  �ļ�����_����Index  ִ�ж�Ӧ�Ĳ����߼�
public class G2_ApplyRuleFor_TypeFile {

	// ����_���� ���Ե�ǰ���͵��ļ�ִ������ִ�еĲ��� html1---��html���͵����ļ�ִ�� ����Ϊ1 ���߼����� String
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

	static int BYTE_CONTENT_LENGTH_Rule7 = 1024 * 10 * 10; // ��ȡ�ļ�Head�ֽ�������
	static String strDefaultKey_Rule7 = "zukgit12"; // 8-length

	static String strZ7DefaultKey_PSW_Rule19 = "752025"; // 8-length
	public static byte[] TEMP_Rule7 = new byte[BYTE_CONTENT_LENGTH_Rule7];

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

	// JDK ��·��
	static String JDK_BIN_PATH = "";

	static OS_TYPE curOS_TYPE = OS_TYPE.Windows;
	static String curOS_ExeTYPE = "";
	static ArrayList<String> mKeyWordName = new ArrayList<>();

	// ��ǰShellĿ¼�µ� �ļ������б� ��ȡ���� ͨ��
	static HashMap<String, ArrayList<File>> CurDirFileTypeMap = new HashMap<String, ArrayList<File>>();;

	static void initSystemInfo() {
		String osName = System.getProperties().getProperty("os.name").toLowerCase();
		String curLibraryPath = System.getProperties().getProperty("java.library.path");
		if (osName.contains("window")) {
			curOS_TYPE = OS_TYPE.Windows;
			Cur_Bat_Name = Cur_Bat_Name + ".bat";
			Cur_Batch_End = ".bat";
			curOS_ExeTYPE = ".exe";
			initJDKPath_Windows(curLibraryPath);
			Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "win_zbin";

		} else if (osName.contains("linux")) {
			curOS_TYPE = OS_TYPE.Linux;
			Cur_Bat_Name = Cur_Bat_Name + ".sh";
			curOS_ExeTYPE = "";
			Cur_Batch_End = ".sh";
			initJDKPath_Linux_MacOS(curLibraryPath);
			Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "lin_zbin";

		} else if (osName.contains("mac")) {
			curOS_TYPE = OS_TYPE.MacOS;
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

	static String curDirPath = ""; // ��ǰ SHELL ����Ŀ¼ Ĭ����main�еĵ�һ�� arg[0] ����shell·��
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
		realTypeRuleList.add(new Append_Pdf_Rule_23()); // �� pdf �ļ� ׷�� �ϲ�Ϊ һ���ļ�
		
		realTypeRuleList.add(new add_Middle_Dir_Rule_24()); // �ڵ�ǰ��Ŀ¼ �� ��Ŀ¼ ֮�� ���� һ���ļ��� , �ļ����������� �û�����
		
		
	}

// 3038�� 5 �� 3 ��

	// operation_type �������� 1--��ȡ�ļ������ַ��� �����޸� 2--���ļ����ļ�����(�ֽ�)--�����޸� 3.��ȫ�����ļ����е����ԵĲ���
	// ���Խ����޸�(�ļ�����)
//     // 4.�Ե�ǰ���ļ�(������Ŀ¼ ���ļ� --��������Ŀ¼ ���ļ�) 5. ��shell �л�ȡ����·�� ȥ��ĳһ���ļ����в���

	// �� ͼƬ�ļ����� �ü� -20_-20_20_20
	// �������ҵ�padding �� -20 20 ͼƬ�����ƶ�20 ��ʾ20�Ŀհ�
	// �� ��-�� ��ʾ20�Ŀհ� ���� ͼƬ����20 ȥ��ͼƬ��20����
	// �� ��-�� ����ʾ20�Ŀհ� ���� ��ͼƬ����20 ȥ��ͼƬ��20����
	// �� ��-�� ����ʾ20�Ŀհ� ���� ��ͼƬ����20 ȥ��ͼƬ��20����
	// �� ��-�� ����ʾ20�Ŀհ� ���� ��ͼƬ����20 ȥ��ͼƬ��20����

	// �ڵ�ǰ��Ŀ¼ �� ��Ŀ¼ ֮�� ���� һ���ļ��� , �ļ����������� �û�����
	class add_Middle_Dir_Rule_24 extends Basic_Rule {

		add_Middle_Dir_Rule_24() {
			super("#", 24, 4); //
			middle_dir_name = "mp4";
		}

		// �м��ļ��е�����
		String middle_dir_name;

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_24  jpg   ##�ڵ�ǰĿ¼����Ŀ¼֮���һ��Ŀ¼jpg  1/1.jpg -> 1/jpg/1.jpg \n" + Cur_Bat_Name
					+ "  #_24  mp4   ##�ڵ�ǰĿ¼����Ŀ¼֮���һ��Ŀ¼jpg  1/1.mp4 -> 1/mp4/1.mp4  1/1.jpg -> 1/mp4/1.jpg \n"
					+ Cur_Bat_Name
					+ " #_24  gif  ##�ڵ�ǰĿ¼����Ŀ¼֮���һ��Ŀ¼jpg  1/1.mp4 -> 1/gif/1.mp4  1/1.jpg -> 1/gif/1.jpg \\n ";
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			// ��ȡ��װ��������
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
						"�޷���⵽��ǰ ��24 Rule  �����м�в�Ŀ¼�����Ʋ��Ϲ� ����   ���������ִ�� inputParams = " + inputFileTypeParams);
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

	class Append_Pdf_Rule_23 extends Basic_Rule {

		ArrayList<File> mPdfFileList; // ��ǰ cmd ���������� pdf �ļ��б� ���κϲ�

		Append_Pdf_Rule_23() {
			super("#", 23, 3); //
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
				System.out.println("��ǰ ����������� pdf �ļ����� С��2���޷� ִ�кϲ� ����!");
				return false;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			// TODO Auto-generated method stub
			if (mPdfFileList.size() < 2) {
				System.out.println("��ǰ ����������� pdf �ļ����� С��2���޷� ִ�кϲ� ����!");
				return null;
			}

			try {

				String originName = mPdfFileList.get(0).getName();
				String currentTimeStamp = "_" + getTimeStamp();
				String newPdfFileName = getFileNameNoPoint(originName) + currentTimeStamp + ".pdf";

				File newPdfFile = new File(curDirPath + File.separator + newPdfFileName);
				File mergedFIle = mulFile2One(mPdfFileList, newPdfFile.getAbsolutePath());
				System.out.println(" �� �ϲ��ļ���С:" + mergedFIle.length());

				if (mergedFIle.length() > 0) {
					for (int i = 0; i < mPdfFileList.size(); i++) {
						File tempFile = mPdfFileList.get(i);
						tempFile.delete();
					}

					tryReName(mergedFIle, originName);
				}

				System.out.println("OK!  PDF �ļ� �Ѿ� ���� --> " + originName);

			} catch (Exception e) {
				System.out.println("��ǰ ִ�� pdf �ϲ���������" + e.getLocalizedMessage());

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_true = "  �Ը����� pdf�ļ�A   pdf�ļ�B  pdf�ļ�C �ļ����кϲ� �ϲ���pdf�ļ�����Ϊ pdfA�ļ�����,��ɾ��ԭpdf�ļ� ";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index
						+ "      <ָ��Pdf�ļ�A> <ָ��Pdf�ļ�B>   <ָ��Pdf�ļ�C>     ## [���� " + index + "]  ����: " + desc_true + "\n";

			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index
						+ "     <ָ��Pdf�ļ�A>  <ָ��Pdf�ļ�B>   <ָ��Pdf�ļ�C>    ##   [���� " + index + "]  ����:" + desc_true;
			}

			return itemDesc;
		}

	}

	public static File mulFile2One(List<File> files, String targetPath) throws IOException {
		// pdf�ϲ�������
		PDFMergerUtility mergePdf = new PDFMergerUtility();
		for (File f : files) {
			if (f.exists() && f.isFile()) {
				// ѭ�����Ҫ�ϲ���pdf
				mergePdf.addSource(f);
			}
		}
		// ���úϲ�����pdf�ļ�����
		mergePdf.setDestinationFileName(targetPath);
		// �ϲ�pdf
		mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		return new File(targetPath);
	}

	class ReSize_Img_Rule_22 extends Basic_Rule {

		ArrayList<String> fliterTypeList;
		ArrayList<File> mSrcFileImage; // ���� ���� ������ ��ǰĿ¼���ļ��еļ���
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
					if (!one_param.contains("_")) { // ��ǰ�ĵ�һ���������� ��_��_��_�� ����
						System.out.println("��ǰ�ĵڶ����������� ��_��_��_�� ����");
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
				System.out.println("�û������� ��Ч���ļ�  ����������ļ����ƣ� ");
				return false;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		// -20_-20_-20_-20
		ArrayList<Integer> calculSize(String size_str) {
			ArrayList<Integer> size_4_List = new ArrayList<Integer>();
			String checkStr = size_str.replaceAll("_", "").replace("+", "").replaceAll("-", "");
			if (!isNumeric(checkStr.trim())) {
				System.out.println("��ǰ�� ��_��_��_�� ���� �������(1):" + size_str);
				return size_4_List;
			}
			String[] arr = size_str.split("_");
			if (arr == null || arr.length != 4) {
				System.out.println("��ǰ�� ��_��_��_�� ���� �������(2):" + size_str);
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
				System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T ֻ�Ե�ǰ���� Img �ļ����д���");
				operationFileList.addAll(mSrcFileImage);
				for (int i = 0; i < operationFileList.size(); i++) {
					File inputFile = operationFileList.get(i);
					System.out.println("inputFile[" + i + "] = " + inputFile.getName());
				}
			} else {
				System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T �û������ļ�Ϊ��--�Ա�������Img jpg png �ļ����д���");

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
			String up_str = up_int > 0 ? "��������" + up_int + "�հס�" : "���ϼ���" + up_int + "���ݡ�";
			String down_str = down_int > 0 ? "��������" + down_int + "�հס�" : "���¼���" + down_int + "���ݡ�";
			String left_str = left_int > 0 ? "��������" + left_int + "�հס�" : "�������" + left_int + "���ݡ�";
			String right_str = right_int > 0 ? "��������" + right_int + "�հס�" : "���Ҽ���" + right_int + "���ݡ�";

			System.out.println("��ǰ����������:" + up_str + down_str + left_str + right_str);
			System.out.println("��ǰ�����ļ�����: " + newOperationFileList.size());
			for (int i = 0; i < newOperationFileList.size(); i++) {
				File imageFile = newOperationFileList.get(i);
				String fileName = imageFile.getName();
				System.out.println("FIle[" + i + "] =" + fileName + "  ��ʼִ�в����� ");
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
				// ��ʾͼƬ����ʼλ��

				int width_input = target_width;
				int height_input = target_high;

				int srcImage_x = left_int; // ԭ���� ��ʼx����
				int srcImage_width = width + right_int; // ԭ���� ��ʼ����

				int srcImage_y = up_int; // ԭ���� ��ʼy����
				int srcImage_high = high + down_int; // ԭ���� ��ʼy����

				double ratiox = 1.0;
				double ratioy = 1.0;

				ratiox = w * ratiox / width_input;
				ratioy = h * ratioy / height_input;

				// ��СͼƬ
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
					// �Ŵ�ͼƬ
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

				// �ӽ�ͼ �ȸ㶨
				// X����ʼ���� �������0�Ļ� ��ô��ʹ��ԭ�е�����ϵ0
				// ���С��0�Ļ� ˵��x��ʼ������Ҫ�ƶ��� Math.abs(left_int)
				int origin_subImage_x = left_int >= 0 ? 0 : Math.abs(left_int);
				int origin_subImage_y = up_int >= 0 ? 0 : Math.abs(up_int);

				int origin_subImage_width = width; // Ĭ��ΪͼƬ�Ŀ��
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

					System.out.println("���·���¿�:" + whiteSpace_BuffImage.getWidth() + "   ���·���¸�:"
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
					System.out.println("�����쳣! ");

				} finally {
//                    if (g != null) {
//                        g.dispose();
//                    }
				}

			}

			System.out.println(" Img Padding ִ�����! ");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		public BufferedImage getBufferedImage(File file) {
			Image img = null;
			try {
				img = ImageIO.read(file); // ����Image����
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}

			int width = img.getWidth(null); // �õ�Դͼ��
			int height = img.getHeight(null); // �õ�Դͼ��

//    return resizeFix(400, 492);
			return resize(img, width, height);
		}

		public BufferedImage resize(Image mImage, int w, int h) {
			// SCALE_SMOOTH �������㷨 ��������ͼƬ��ƽ���ȵ� ���ȼ����ٶȸ� ���ɵ�ͼƬ�����ȽϺ� ���ٶ���
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			try {
				g.drawImage(mImage, 0, 0, w, h, null); // ������С���ͼ
			} finally {
				if (g != null) {
					g.dispose();
				}
			}
			return image;
			// File destFile = new File("C:\\temp\\456.jpg");
			// FileOutputStream out = new FileOutputStream(destFile); // ������ļ���
			// // ��������ʵ��bmp��png��gifתjpg
			// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			// encoder.encode(image); // JPEG����
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
			 * BufferedImage.TYPE_INT_RGB);//INT��ȷ�ȴﵽһ��,RGB��ԭɫ���߶�70,���150 //�õ����Ļ��ƻ���(����ͼƬ�ı�)
			 * Graphics2D g2 = (Graphics2D) bi.getGraphics(); int frontSize = 550;
			 * 
			 * g2.setBackground(currentColor); g2.fillRect(0,0,width,heigh);//���һ������
			 * ���Ͻ�����(0,0),��500,��500;�������ͼƬ g2.fillRect(0,0,width,heigh);//�������ͼƬ(��ʵ�������ñ�����ɫ)
			 * g2.setColor(currentColor);
			 * 
			 */

			imgBuf = new BufferedImage(width, heigh, BufferedImage.TYPE_INT_RGB);
			Graphics curGraphic = imgBuf.getGraphics();
			// ������ɫ
			curGraphic.setColor(currentColor);
			// ���
			curGraphic.fillRect(0, 0, imgBuf.getWidth(), imgBuf.getHeight());

			return imgBuf;
			/*
			 * 
			 * try { mCurFile.createNewFile(); ImageIO.write(imgBuf, "jpg", new
			 * FileOutputStream(mCurFile));//����ͼƬ JPEG��ʾ�����ʽ //
			 * System.out.println("���� RGB "+"R="+r+"  G="+g+"  B="+b+" ͼƬ�ɹ���");
			 * 
			 * } catch (Exception e) { System.out.println("���� RGB ͼƬ��ʽ�����쳣��"+
			 * mCurFile.getAbsolutePath()); }
			 */

		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_A = " ��������� Ĭ�϶Ա�Ŀ¼�µ����� png jpg  ���� 20_20_20_20 ��_��_��_�ҵĲü�";
			String desc_B = " �Ը�����ͼƬ���� 20_20_20_20 ��_��_��_�ҵĲü�  ";
			String desc_C = " �Ը�����ͼƬ���� 200_0_0_0 ��_��_��_�ҵĲü�(��������200�հ����ؿռ�)  ";
			String desc_D = " �Ը�����ͼƬ���� 0_0_0_200 ��_��_��_�ҵĲü�(�ײ�����200�հ����ؿռ�)  ";
			String desc_E = " �Ը�����ͼƬ���� 0_200_0_200 ��_��_��_�ҵĲü�(�ײ�����200 �Ҳ�����200 �հ����ؿռ�)  ";
			String desc_F = " �Ը�����ͼƬ���� -100_-100_-100_-100 ��_��_��_�ҵĲü�(�������� ���ü�100 �հ����ؿռ�)  ";
			String desc_G = " �Ը�����ͼƬ���� 0_-125_0_0 ��_��_��_�ҵĲü�( �ײ��ü�125 ���ؿռ�)  ";
			String desc_H = " �Ը�����ͼƬ���� 0_-110_0_0 ��_��_��_�ҵĲü�( �ײ��ü�110 ���ؿռ�)  ";
			itemDesc = batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  20_20_20_20" + "    #### [���� "
					+ index + "]  ����: " + desc_A + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  20_20_20_20" + "   <ImgFile>  "
					+ "    #### [���� " + index + "]  ����: " + desc_B + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  200_0_0_0"
					+ "    <ImgFile>   #### [���� " + index + "]  ����: " + desc_C + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  0_0_0_200"
					+ "    <ImgFile>   #### [���� " + index + "]  ����: " + desc_D + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  0_200_0_200"
					+ "    <ImgFile>   #### [���� " + index + "]  ����: " + desc_E + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  -100_-100_-100_-100"
					+ "    <ImgFile>   #### [���� " + index + "]  ����: " + desc_F + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  0_-125_0_0"
					+ "    <ImgFile>   #### [���� " + index + "]  ����: " + desc_G + "\n";
			itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "  0_-110_0_0"
					+ "    <ImgFile>   #### [���� " + index + "]  ����: " + desc_H + "\n";

			return itemDesc;

		}

	}

	class Rename_Img_WithSize_Rule_21 extends Basic_Rule {

		ArrayList<String> fliterTypeList;
		ArrayList<File> mSrcFileImage; // ���� ���� ������ ��ǰĿ¼���ļ��еļ���

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
			System.out.println("�T�T�T�T�T�T�T�T�T�T��ʼִ�� " + typtSb.toString() + "���� 1960x1280 ��x�߲��� " + "�T�T�T�T�T�T�T�T�T�T");

			for (int i = 0; i < mSrcFileImage.size(); i++) {
				File imageFile = mSrcFileImage.get(i);
				String fileName = imageFile.getName();
				ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
				int high = imageIcon.getIconHeight();
				int width = imageIcon.getIconWidth();

				// ��ǰ�ļ��� ���
				String str_width_x_high = calculateSizeStr(width, high);
				String newName = str_width_x_high + "_" + fileName;
				tryReName(imageFile, newName);
				System.out.println("File[" + i + "] =  SrcName��" + fileName + "��  TargetName��" + newName + "��");

			}

			System.out.println("Img Size Rename ִ�����! ");

			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		// ��x�� 1000x0900 1280x0720
		String calculateSizeStr(int widthValue, int highValue) {
			String sizeStr = "";
			int fixWidthValue = 0;
			int fixHighValue = 0;

			if (widthValue > 9999) { // ������ֻ�� 9999 ���� �ܲ���
				fixWidthValue = 9999;
			} else {
				fixWidthValue = widthValue;
			}
			if (highValue > 9999) { // ������ֻ�� 9999 ���� �ܲ���
				fixHighValue = 9999;
			} else {
				fixHighValue = highValue;
			}
			String widthStr = addForZeroStr(fixWidthValue);
			String highStr = addForZeroStr(fixHighValue);

			// fixWidthValue �� fixHighValue ���в������

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
			String desc_A = " �Ե�ǰĿ¼�µ�ͼƬ�ļ� ָ������ͼƬ(��������)(png)(jpg)(webp)(gif)���� ������ ��x�� ���� 1960x1280_ԭ�� �Ĳ���";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "" + "    #### [���� " + index
						+ "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_png" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_gif" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_webp" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png_gif_webp"
						+ "    #### [���� " + index + "]  ����: " + desc_A + "\n";

			} else {
				itemDesc = batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "" + "    #### [���� " + index
						+ "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_png" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_gif" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_webp" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png" + "    #### [���� "
						+ index + "]  ����: " + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + "  " + type + "_" + index + "_jpg_png_gif_webp"
						+ "    #### [���� " + index + "]  ����: " + desc_A + "\n";
			}
			return itemDesc;

		}

	}

	class Land_Port_Classify_Rule_20 extends Basic_Rule {

		boolean isTimeStampDir = true; // Land_Port �½����ļ����Ƿ����ʱ���

		// false ---�� �� png �� jpg �ļ����й���
		boolean isGifClassfly = false; // true ---�� ֻ�� gif �ļ� ���� ����

		ArrayList<File> mSrcFileImage; // Shell Ŀ¼��ԭʼ�ļ�Ŀ¼
		ArrayList<File> mLandImageFileList; // Shell/Land_Port_TimeStamp/Land/ �ļ����µ��ļ�
		ArrayList<File> mPortImageFileList; // Shell/Land_Port_TimeStamp/Land/ �ļ����µ��ļ�
		HashMap<File, File> src_target_FileMap; // srcΪ ԭʼ�ļ� targetΪĿ���ļ� ���� copyʱ ��ʹ�õ�

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
				ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
				int high = imageIcon.getIconHeight();
				int width = imageIcon.getIconWidth();

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
			System.out.println("�T�T�T�T�T�T�T�T�T�T" + dir_Land.getAbsolutePath() + "  Land�ļ���ʼ����ִ��" + "�T�T�T�T�T�T�T�T�T�T");
			TryClassifyImage(mLandImageFileList, dir_Land);
			System.out.println("�T�T�T�T�T�T�T�T�T�T" + dir_Port.getAbsolutePath() + "  Port�ļ���ʼ����ִ��" + "�T�T�T�T�T�T�T�T�T�T");
			TryClassifyImage(mPortImageFileList, dir_Port);

			System.out.println("zzfile_3" + Cur_Batch_End + "  " + curDirFile.getAbsoluteFile() + File.separator + dir_1
					+ "                        ####  ���� Land  Port �ļ����Ѿ����ɣ�");

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
				System.out.println("File[" + i + "] = " + "SrcFile��" + imgFile.getAbsolutePath() + "��" + " TargetFile��"
						+ targetFile.getAbsolutePath() + "��");
			}

		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_A = " �Ե�ǰĿ¼�µ�ͼƬ�ļ�(png)(jpg)���� Land���� �� Port��ֱ ���� ���������½�Land_Port_Img_TimeStamp �ļ�����";
			String desc_B = " �Ե�ǰĿ¼�µ�ͼƬ�ļ�(png)(jpg)���� Land���� �� Port��ֱ ���� ���������½�Land_Port_Img�ļ�����(�ļ������ƹ̶�)";
			String desc_C = " �Ե�ǰĿ¼�µ�ͼƬ�ļ�(gif)���� Land���� �� Port��ֱ ���� ���������½�Land_Port_Gif_TimeStamp �ļ�����";
			String desc_D = " �Ե�ǰĿ¼�µ�ͼƬ�ļ�(gif)���� Land���� �� Port��ֱ ���� ���������½�Land_Port_Gif �ļ�����(�ļ������ƹ̶�)";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index + "" + "    #### [���� " + index + "]  ����: "
						+ desc_A + "\n";
				itemDesc += batName.trim() + ".bat  " + type + "_" + index + "_notime" + "    #### [���� " + index
						+ "]  ����: " + desc_B + "\n";
				itemDesc += batName.trim() + ".bat  " + type + "_" + index + "_gif" + "    #### [���� " + index
						+ "]  ����: " + desc_C + "\n";
				itemDesc += batName.trim() + ".bat  " + type + "_" + index + "_gif_notime" + "    #### [���� " + index
						+ "]  ����: " + desc_D + "\n";

			} else {
				itemDesc = batName.trim() + Cur_Batch_End + " " + type + "_" + index + "       ### [���� " + index
						+ "]  ����:" + desc_A + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + " " + type + "_" + index + "_notime" + "       ### [���� "
						+ index + "]  ����:" + desc_B + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + " " + type + "_" + index + "_gif" + "       ### [���� "
						+ index + "]  ����:" + desc_C + "\n";
				itemDesc += batName.trim() + Cur_Batch_End + " " + type + "_" + index + "_gif_notime"
						+ "       ### [���� " + index + "]  ����:" + desc_C;
			}

			return itemDesc;
		}

	}

	// �ѵ�ǰ �ļ� ʹ�� Ĭ�ϵ� ���� 752025 ���� ѹ�� �� 7z �ļ�
	class ExpressTo7z_PassWord_Rule_19 extends Basic_Rule {

		ArrayList<String> inputTypeList;
		// zrule_apply_G2.bat #_14 jpg �ѵ�ǰ���е�jpg��ʽ�ļ����ɿ�ݷ�ʽ�� jpg_ʱ��� �ļ�����

		// ���ܴӲ�������� ��һ�ļ�
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
						"��ǰ 7z ѹ�����򲻴���! ���鵱ǰ�� 7z���� һ��λ�� Desktop/zbin/win_zbin/  mac_zbin lin_zbin ��  z7exeFile = "
								+ z7exeFile.getAbsolutePath());
				return false;
			}
			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

			SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");// �������ڸ�ʽ
//            SimpleDateFormat df_hms = new SimpleDateFormat("HHmmss");//�������ڸ�ʽ
			Date curDate = new Date();
			String date = df.format(curDate);
//            String preHMS = df.format(df_hms);

			if (isSearchAllFile2CurDirFlag) {
				// ���������� ���͵� �ļ� �� ��������
				try7zExpressOperation(fileTypeMap);

			} else {

				for (int i = 0; i < inputTypeList.size(); i++) {
					String type = inputTypeList.get(i);

					ArrayList<File> targetFileList = fileTypeMap.get(type);

					if (targetFileList == null || targetFileList.size() == 0) {
						System.out.println(" ��ǰ·�� " + curDirPath + " ���������� " + type + "���ļ�!");
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
						System.out.println("ִ��\n");
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

					System.out.println("ִ��\n");
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
					String typeStr = entry.getKey(); // Map��Value
					ArrayList<File> fileArr = entry.getValue(); // Map��Value

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

						System.out.println("ִ��\n");
						System.out.println(z7_command);
						execCMD(z7_command);
					}

				}
			}

			return executeFlag;
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + " #_19            ### �ѵ�ǰ�ļ����������ļ����� ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !  "
					+ "\n" + Cur_Bat_Name
					+ " #_19  mp4          ###  �ѵ�ǰ�ļ����� .mp4   ���� ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   " + "\n"
					+ Cur_Bat_Name + " #_19  .mp4         ### �ѵ�ǰ�ļ����� .mp4  ���� ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  .gif         ### �ѵ�ǰ�ļ����� .gif  ���� ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   " + "\n"
					+ Cur_Bat_Name + " #_19  png          ### �ѵ�ǰ�ļ����� .png  ���� ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  zip  7z      ### �ѵ�ǰ�ļ�����  .zip  .7z   ���� ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   " + "\n"
					+ Cur_Bat_Name
					+ " #_19  .zip .7z     ###  �ѵ�ǰ�ļ�����  .zip  .7z   ���� ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   " + "\n"
					+ Cur_Bat_Name + " #_19  jpg          ###  �ѵ�ǰ�ļ�����  .jpg   ����ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  .jpg  .png  .webp .gif                          ### �ѵ�ǰ�ļ�����  .jpg  .png  .webp .gif  ����ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  .mp4  .avi   .wmv .rmvb  .flv .3gp              ### �ѵ�ǰ�ļ�����  .mp4  .avi   .wmv .rmvb  .flv .3gp  ����ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv     ### �ѵ�ǰ�ļ�����  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv  ����ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   "
					+ "\n" + Cur_Bat_Name
					+ " #_19  <ָ���ļ�A> <ָ���ļ�B>          ### �ѵ�ǰ�ļ����� ָ���ļ����� ����ѹ��Ϊ .7z �ļ� �ļ������仯   ����Ĭ��Ϊ 752025 !   \"+ "

			;
		}

	}

	// operation_type �������� 1--��ȡ�ļ������ַ��� �����޸� 2--���ļ����ļ�����(�ֽ�)--�����޸� 3.��ȫ�����ļ����е����ԵĲ���
	// ���Խ����޸�(�ļ�����)
//     // 4.�Ե�ǰ���ļ�(������Ŀ¼ ���ļ� --��������Ŀ¼ ���ļ�) 5. ��shell �л�ȡ����·�� ȥ��ĳһ���ļ����в���

	// ���ļ���md���ֽ��������� �ļ� ������ �ĺ�׺ ��Ӱ�� �������
	class MD_ReName_Rule_18 extends Basic_Rule {

		ArrayList<String> inputTypeList;
		// zrule_apply_G2.bat #_14 jpg �ѵ�ǰ���е�jpg��ʽ�ļ����ɿ�ݷ�ʽ�� jpg_ʱ��� �ļ�����

		// ���ܴӲ�������� ��һ�ļ�
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

			SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");// �������ڸ�ʽ
//            SimpleDateFormat df_hms = new SimpleDateFormat("HHmmss");//�������ڸ�ʽ
			Date curDate = new Date();
			String date = df.format(curDate);
//            String preHMS = df.format(df_hms);

			if (isSearchAllFile2CurDirFlag) {
				// ���������� ���͵� �ļ� �� ��������
				tryReNameOperation(fileTypeMap);

			} else {

				for (int i = 0; i < inputTypeList.size(); i++) {
					String type = inputTypeList.get(i);

					ArrayList<File> targetFileList = fileTypeMap.get(type);

					if (targetFileList == null || targetFileList.size() == 0) {
						System.out.println(" ��ǰ·�� " + curDirPath + " ���������� " + type + "���ļ�!");
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
					String typeStr = entry.getKey(); // Map��Value
					ArrayList<File> fileArr = entry.getValue(); // Map��Value

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
					+ " #_18            ### �ѵ�ǰ�ļ����� �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� 32aeefa9924afb8be0da50976f1a2405.mp4 !  "
					+ "\n" + Cur_Bat_Name
					+ " #_18  mp4          ###  �ѵ�ǰ�ļ����� .mp4 �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� !   " + "\n"
					+ Cur_Bat_Name + " #_18  .mp4         ### �ѵ�ǰ�ļ����� .mp4 �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� !   "
					+ "\n" + Cur_Bat_Name
					+ " #_18  .gif         ### �ѵ�ǰ�ļ����� .gif �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� 32aeefa9924afb8be0da50976f1a2405.gif !   "
					+ "\n" + Cur_Bat_Name
					+ " #_18  png          ### �ѵ�ǰ�ļ����� .png �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� !   " + "\n"
					+ Cur_Bat_Name
					+ " #_18  zip  7z      ### �ѵ�ǰ�ļ�����  .zip  .7z  �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� !   32aeefa9924afb8be0da50976f1a2405.7z  "
					+ "\n" + Cur_Bat_Name
					+ " #_18  .zip .7z     ###  �ѵ�ǰ�ļ�����  .zip  .7z  �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� !   32aeefa9924afb8be0da50976f1a2405.7z "
					+ "\n" + Cur_Bat_Name
					+ " #_18  jpg          ###  �ѵ�ǰ�ļ�����  .jpg  �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� !" + "\n"
					+ Cur_Bat_Name
					+ " #_18  .jpg  .png  .webp .gif                          ### �ѵ�ǰ�ļ�����  .jpg  .png  .webp .gif �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� "
					+ "\n" + Cur_Bat_Name
					+ " #_18  .mp4  .avi   .wmv .rmvb  .flv .3gp              ### �ѵ�ǰ�ļ�����  .mp4  .avi   .wmv .rmvb  .flv .3gp �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� "
					+ "\n" + Cur_Bat_Name
					+ " #_18  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv     ### �ѵ�ǰ�ļ�����  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� "
					+ "\n" + Cur_Bat_Name
					+ " #_18  <ָ���ļ�A> <ָ���ļ�B>          ### �ѵ�ǰ�ļ����� ָ���ļ�����  �ļ�ȫ������Ϊ MD5�����������ļ� ��(32)λ16����.type�� "

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
	// operation_type �������� 1--��ȡ�ļ������ַ��� �����޸� 2--���ļ����ļ�����(�ֽ�)--�����޸� 3.��ȫ�����ļ����е����ԵĲ���
	// ���Խ����޸�(�ļ�����)
//     // 4.�Ե�ǰ���ļ�(������Ŀ¼ ���ļ� --��������Ŀ¼ ���ļ�) 5. ��shell �л�ȡ����·�� ȥ��ĳһ���ļ����в���

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
					System.out.println("����Ŀ¼ " + newDirTemp.getAbsolutePath() + " �ɹ�! ");
				}
				return null;
			} else {
				System.out.println("Make_ZRuleDir_Rule_17   ��ǰ��ȡ����ShellĿ¼Ϊ��!   �޷����� Z�����ļ���!  ");
			}
			return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			String desc_true = " �ڵ�ǰĿ¼�´��� �̶����ļ��� ZDir -> �� 0_Loveon_Place 1_C_Install_Dir  2_WebSite_Download  3_BaiduNetdiskDownload  4_Software  5_WorkPlace   6_Jpg_Video  7_Txt_PDF_DOC_Book  0_Temp_Dir  9_Version  10_Jira_Work  ";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index + "       �� ���� Z����Ŀ¼�� [���� " + index
						+ "]  ����: " + desc_true + "\n";

			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index + "       �� ���� Z����Ŀ¼��   [���� " + index + "]  ����:"
						+ desc_true;
			}

			return itemDesc;
		}

	}

	class File_TimeName_Rule_16 extends Basic_Rule {

		// key = type value = ���Ϲ����ļ���������Ƶ��ļ��ļ���
		// HashMap<String, ArrayList<File>> arrFileMap;
		boolean keepOriginalName = false;
		int inputBeginIndex = 0;

		// true 1.jpg 2,jpg 3.png 4.png ��������
		// false 1.jpg 2,jpg 1.png 2.png ����������
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
					String typeStr = entry.getKey(); // Map��Value
					ArrayList<File> fileArr = entry.getValue(); // Map��Value

					for (int i = 0; i < fileArr.size(); i++) {
						fileOrderIndex++;
						int index = i + 1;
						String newNamePre = index + "_" + getTimeStamp();
						File curFile = fileArr.get(i);
						String curFileName = curFile.getName();
						String newName = "";
						if (keepOriginalName) {
							if (isOrder) { // ��˳������ ���� type�� һֱ��
								newName = fileOrderIndex + "_" + curFileName + "_" + getTimeStampLong() + typeStr;
							} else {
								newName = newNamePre + curFileName + "_" + getTimeStampLong() + typeStr;
							}
						} else {
							// ������������� ��ôû�����͵��ļ� ��ֻ�� ��� û������
							if ("unknow".equals(typeStr)) {
								newName = index + "_" + getTimeStamp() + "_" + getTimeStampLong();
							} else {
								if (isOrder) { // ��˳������ ���� type�� һֱ��
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
			String desc_true = "  (��������ǰ���� ��������������ǰĿ¼�µ��ļ�) �ļ�������ʽΪ:    �������� ���_ʱ���.����   1_201841094.jpg 2_201841094.jpg 3_2018413131.jpg 1_201804021145.png";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index
						+ "       �� index_timestamp.type ���_ʱ���.���� ��������������ļ�  [���� " + index + "]  ����: " + desc_true
						+ "\n";

			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index
						+ "       �� index_timestamp.type ���_ʱ���.���� ��������������ļ�   [���� " + index + "]  ����:" + desc_true;
			}

			return itemDesc;
		}

	}

	// operation_type �������� 1--��ȡ�ļ������ַ��� �����޸� 2--���ļ����ļ�����(�ֽ�)--�����޸� 3.��ȫ�����ļ����е����ԵĲ���
	// ���Խ����޸�(�ļ�����)
	// // 4.�Ե�ǰ���ļ�(������Ŀ¼ ���ļ� --��������Ŀ¼ ���ļ�) 5. ��shell �л�ȡ����·�� ȥ��ĳһ���ļ����в���

	class Webp_To_Jpg_Gif_Rule_15 extends Basic_Rule {
		ArrayList<File> webpFileList;
		ArrayList<File> gif_webpFileList;
		String G2_webp2gif_exe_path = "";

		Webp_To_Jpg_Gif_Rule_15() {
			super("#", 15, 4);
			webpFileList = new ArrayList<File>();
			gif_webpFileList = new ArrayList<File>();
			PushFile2JDKBIN();
			if (curOS_TYPE == OS_TYPE.Windows) {
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
			if (curOS_TYPE == OS_TYPE.Windows) {
				webpLibraryFilePath = JDK_BIN_PATH + File.separator + "webp-imageio.dll";
				G2_LibraryPath = G2_File_Path + File.separator + "webp-imageio.dll";
				Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "win_zbin";
			} else if (curOS_TYPE == OS_TYPE.MacOS) {
				webpLibraryFilePath = JDK_BIN_PATH + File.separator + "libwebp-imageio.dylib";
				G2_LibraryPath = G2_File_Path + File.separator + "libwebp-imageio.dylib";
				Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "mac_zbin";
			} else if (curOS_TYPE == OS_TYPE.Linux) {
				webpLibraryFilePath = JDK_BIN_PATH + File.separator + "libwebp-imageio.so";
				G2_LibraryPath = G2_File_Path + File.separator + "libwebp-imageio.so";
				Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "lin_zbin";
			}

			File webpLibraryFile = new File(webpLibraryFilePath);
			File G2_LibraryFile = new File(G2_LibraryPath);
			if (!G2_LibraryFile.exists()) {
				System.out.println("���� ���ļ� " + G2_LibraryPath + "������ ��������� zbin/G2/.so .dll �ļ�!");
				return;
			}
			if (webpLibraryFile.exists() && webpLibraryFile.length() > 100) {
				System.out.println("��ǰ ���ļ� " + webpLibraryFilePath + "�Ѿ����ص� jre/bin ·����!");
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
				System.out.println("��ǰ�ļ����в����� webp�ļ��ĸ�ʽ");
				return null;
			}
			webpFileList.addAll(webpFile);
			String stampStr = getTimeStamp();
			for (int i = 0; i < webpFileList.size(); i++) {

				File webpFileItem = webpFileList.get(i);
				System.out.println("��ǰ webp����[" + i + "] = " + webpFileItem.getAbsolutePath());
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

				// ��� ���غ��gif ���� ��ô ��Ҫ ���ʱ��� ���⸲��
//             if(gif_absPath_File.exists()){

				fileName = fileName.replace(".webp", "_" + stampStr + ".webp");
				tryReName(gif_webpFileItem, fileName);
				needRename = true;
//              }

				System.out.println("��ͼ ����[" + i + "] = " + fileName);
				System.out.println("ִ�ж�ͼתΪ gif������! ");
				if ("".equals(G2_webp2gif_exe_path)) {
					System.out.println("��ǰ webp2gif Ϊ�� ����!  ���ܵ�ǰϵͳ Linux MacOS ��ûʵ�ָù���!");
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
			// webp ��̬ͼ �ᱨ�� Decode returned code VP8_STATUS_UNSUPPORTED_FEATURE
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

				System.out.println("����ʧ��   ������webp��ͼ!   ���� ArrayList<File> gifList �б���!");
				gif_webpFileList.add(webpFile);
			}

			try {
				ImageIO.write(image, "png", jpgFile);
			} catch (Exception e) {
				System.out.println("д���ļ� " + jpgFile.getAbsolutePath() + " ʧ��");
			}

		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + " webp_15            ### �Ե�ǰĿ¼�µ� webp�ļ�����ת��  ��̬ͼ-> jpg   ��̬ͼ-> gif \n";
		}

	}

	// ������ݷ�ʽ
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
			return null; // ���� ��ݷ�ʽ ��ô ���� "" ���� null
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
		// zrule_apply_G2.bat #_14 jpg �ѵ�ǰ���е�jpg��ʽ�ļ����ɿ�ݷ�ʽ�� jpg_ʱ��� �ļ�����

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

			SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");// �������ڸ�ʽ
//            SimpleDateFormat df_hms = new SimpleDateFormat("HHmmss");//�������ڸ�ʽ

			Date curDate = new Date();
			String date = df.format(curDate);
//            String preHMS = df.format(df_hms);
			for (int i = 0; i < inputTypeList.size(); i++) {
				String type = inputTypeList.get(i);

				ArrayList<File> targetFileList = fileTypeMap.get(type);

				if (targetFileList == null || targetFileList.size() == 0) {
					System.out.println(" ��ǰ·�� " + curDirPath + " ���������� " + type + "���ļ�!");
					continue;
				}

				int fileCount = targetFileList.size();
				// �����ļ��� ��С
//                String dirName = preHMS+"_"+type.replace(".","").toUpperCase().trim()+"_"+date;
				String dirName = date + "_" + type.replace(".", "").toUpperCase().trim() + "[" + fileCount + "]";
				// MP4_4232414141
				File iconDirFile = new File(curDirPath + File.separator + dirName);
				iconDirFile.mkdirs();

				System.out.println("�T�T�T�T�T�T�T�T" + "�ļ�����" + type + "������ݷ�ʽ Begin" + "�T�T�T�T�T�T�T�T");
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

						System.out.println("Index[" + IconIndex + "]Ŀ���ļ�:" + targetTypeFile.getAbsolutePath()
								+ " ������ݷ�ʽ�ɹ�:" + "./" + dirName + File.separator + iconName);
					} else {
						System.out.println("Index[" + IconIndex + "]Ŀ���ļ�:" + targetTypeFile.getAbsolutePath()
								+ " ������ݷ�ʽʧ��:" + "./" + dirName + File.separator + iconName);
					}
				}
				System.out.println("�T�T�T�T�T�T�T�T" + "�ļ�����" + type + "������ݷ�ʽ End" + "�T�T�T�T�T�T�T�T");

			}

			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name
					+ " #_14  mp4          ### Դ�ļ�����˳�������� 1_ 2_ ��̬���㵱ǰ�ļ������������ļ��е�mp4�ļ� ���ڵ�ǰĿ¼���� MP4_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .mp4         ### Դ�ļ�����˳�������� 1_ 2_��̬���㵱ǰ�ļ������������ļ��е�mp4�ļ� ���ڵ�ǰĿ¼���� MP4_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .gif         ### Դ�ļ�����˳�������� 1_ 2_��̬���㵱ǰ�ļ������������ļ��е�gif�ļ� ���ڵ�ǰĿ¼���� GIF_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  png          ### Դ�ļ�����˳�������� 1_ 2_ ��̬���㵱ǰ�ļ������������ļ��е�png�ļ� ���ڵ�ǰĿ¼���� PNG_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  zip  7z      ### Դ�ļ�����˳�������� 1_ 2_ ��̬���㵱ǰ�ļ������������ļ��е� �ļ����е� 7z zip�ļ�   ���ڵ�ǰĿ¼���� 7Z_20200522_154600  ZIP_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .zip .7z     ### Դ�ļ�����˳�������� 1_ 2_ ��̬���㵱ǰ�ļ������������ļ��е� �ļ����е� 7z zip�ļ�   ���ڵ�ǰĿ¼���� 7Z_20200522_154600  ZIP_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  jpg          ### Դ�ļ�����˳�������� 1_ 2_ ��̬���㵱ǰ�ļ������������ļ��е�JPG�ļ� ���ڵ�ǰĿ¼���� JPG_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .jpg  .png  .webp .gif                          ### ����ͼƬ��ʽ�ļ�����  PNG_ʱ���  JPG_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .mp4  .avi   .wmv .rmvb  .flv .3gp              ### ������Ƶ��ʽ�ļ�����    Դ�ļ�����˳�������� 1_ 2_ ��̬���㵱ǰ�ļ������������ļ��е�JPG�ļ� ���ڵ�ǰĿ¼���� MP4_20200522_154600 �������ļ��� \n"
					+ "\n" + Cur_Bat_Name
					+ " #_14  .jpg  .png  .gif  .webp .mp4 .avi .flv .wmv     ### ���� ��Ƶ + ͼƬ ��ʽ�ļ�����  Դ�ļ�����˳�������� 1_ 2_ ��̬���㵱ǰ�ļ������������ļ��е���Ƶ�ļ� ���ڵ�ǰĿ¼���� JPG_20200522_154600 MP4_20200522_154600 �������ļ��� \n"

			;
		}
	}

	// operation_type �������� 1--��ȡ�ļ������ַ��� �����޸� 2--���ļ����ļ�����(�ֽ�)--�����޸� 3.��ȫ�����ļ����е����ԵĲ���
	// ���Խ����޸�(�ļ�����)
	// // 4.�Ե�ǰ���ļ�(������Ŀ¼ ���ļ� --��������Ŀ¼ ���ļ�) 5. ��shell �л�ȡ����·�� ȥ��ĳһ���ļ����в���

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

		String newReplaceName; // G2_Rule13_mp4_3x5 ���� G2_Rule13 �滻������

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
				// ����Ƿ��� paramItem ���Ƶ��ļ���
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
				System.out.println("��ǰ������������� jpg || mp4 || gif  ����������");
				return false;
			}

			String[] params = inputParam.split("_");
			if (params == null) {
				System.out.println("��ǰ������������� jpg || mp4 || gif  ����������");
				return false;
			}
			String TypeDir = params[params.length - 1];

			if (!(("jpg").equals(TypeDir) || ("mp4").equals(TypeDir) || ("gif").equals(TypeDir))) {
				System.out.println("��ǰ������������� jpg || mp4 || gif  ����������");
				return false;
			}
			Type_DIR_NAME = TypeDir.toLowerCase().trim();
			return super.initParams4InputParam(inputParam);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_13_mp4    ### ��̬���㵱ǰ�ļ������������ļ��е�mp4�ļ����е� mp4�ļ�����  ���ڵ�ǰĿ¼����html�ļ� \n"
					+ Cur_Bat_Name + "  #_13_jpg    ### ��̬���㵱ǰ�ļ������������ļ��е�jpg�ļ����е� jpg�ļ����� ���ڵ�ǰĿ¼����html�ļ�\n"
					+ Cur_Bat_Name + "  #_13_gif    ### ��̬���㵱ǰ�ļ������������ļ��е�gif�ļ����е� gif�ļ����� ���ڵ�ǰĿ¼����html�ļ�\n"
					+ Cur_Bat_Name
					+ "  #_13_mp4  <�����Ӽ��в���>  ### ͬû�в���(��shell·����ͬ) ��̬���㵱ǰ�ļ������������ļ��е�mp4�ļ����е� mp4�ļ����� ���ڵ�ǰĿ¼����html�ļ�\n"
					+ Cur_Bat_Name
					+ "  #_13_jpg  <�����Ӽ��в���>  ### ͬû�в���(��shell·����ͬ) ��̬���㵱ǰ�ļ������������ļ��е�jpg�ļ����е� jpg�ļ����� ���ڵ�ǰĿ¼����html�ļ�\n"
					+ Cur_Bat_Name
					+ "  #_13_gif  <�����Ӽ��в���>  ### ͬû�в���(��shell·����ͬ) ��̬���㵱ǰ�ļ������������ļ��е�gif�ļ����е� gif�ļ����� ���ڵ�ǰĿ¼����html�ļ�\n"
					+ Cur_Bat_Name
					+ "  #_13_mp4  <���ļ��в���1> <���ļ��в���2> ....<���ļ��в���N>  ### ��������� ��̬�������·���µ��ļ������������ļ��е�mp4�ļ����е� gif�ļ����� ���ڵ�ǰĿ¼����html�ļ�\n"
					+ Cur_Bat_Name
					+ "  #_13_jpg <���ļ��в���1> <���ļ��в���2> ....<���ļ��в���N>   ### ͬû�в���(��shell·����ͬ) ��̬���㵱ǰ�ļ������������ļ��е�mp4�ļ����е� gif�ļ����� ���ڵ�ǰĿ¼����html�ļ�\n";
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			ArrayList<File> operaDirList = new ArrayList<File>();
			boolean isMultiDirInput = false;
			String curBasePath = "";

			if (inputDirList.size() == 0) { // ���û������Dir���� ��ô ���ڵ�ǰĿ¼����
				operaDirList.addAll(curDirList);
				curBasePath = curDirFile.getAbsolutePath();
			} else if (inputDirList.size() == 1) { // ���ֻ��һ������ ��ôoperaDirList ���� ��ǰ��������Ŀ¼
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

			// ����в��� ��ô ��ǰ�� curDirList
			int index = 0;
			for (int i = 0; i < operaDirList.size(); i++) {
				File cur1DirFileItem = operaDirList.get(i);
				File mTypeDirFile = checkType2Dir(cur1DirFileItem, Type_DIR_NAME);
				int typeFileNum = 0;
				if (mTypeDirFile != null && 0 != (typeFileNum = checkType3File(mTypeDirFile, Type_DIR_NAME))) {
					// ��⵽�� ��Ӧ�� type �ļ�
					// 1.��ȡ��ǰ ��һ��Ŀ¼����
					String dir1DirName = cur1DirFileItem.getName();
					// 2. ��ȡ��Ӧ������ļ�
					String dir2TypeDieName = dir1DirName + File.separator + Type_DIR_NAME;
					dir2TypeDieName = dir2TypeDieName.replace("\\", "/");
					// 3. typeFileNum ��Ӧ�ĵ�ǰ ����Ŀ¼�е��ļ��ĸ���
					int length = typeFileNum;
					String people = "person" + index;

//                    person0 = { index:0 , path:"./7001/mp4/",length:22,};
//                    person0 = { index:0 , path:"./7001\mp4,length:22,};
					String defineItem = "";
					if (!isMultiDirInput) { // ����ǵ����� �ļ�
						defineItem = people + " = { index:" + index + " , path:\"./" + dir2TypeDieName + "/\",length:"
								+ length + ",};\n";
					} else { // ��������� ������ļ� ��ô path��Ҫ�����Ӧ�� ��ǰĿ¼��·��
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

			// ����people
			String defineArrWordStr = defineArrWord.toString().trim();
			while (defineArrWordStr.endsWith(",")) {
				defineArrWordStr = defineArrWordStr.substring(0, defineArrWordStr.length() - 1);
			}

			// �� people ��Ϊ ���� array
			String defineAddStr = defineAdd.toString();

			for (int i = 0; i < htmlModelList.size(); i++) {
				// ��ȡ html�ļ�������
				File htmlModelFile = htmlModelList.get(i);

				// G2_Rule13_mp4_3x5
				String html_old_name = htmlModelFile.getName();
				String readHtmlContent = ReadFileContent(htmlModelFile);

//            String readHtmlContent = "";
				System.out.println("defineAddStr  = " + defineAddStr);
				System.out.println("defineArrWordStr  = " + defineArrWordStr);
				readHtmlContent = readHtmlContent.replace("zukgitPlaceHolderArrayAdd", defineAddStr);
				readHtmlContent = readHtmlContent.replace("zukgitPlaceHolderArrayDefine", defineArrWordStr);

				// ���ļ�д�� ��Ӧ��Ŀ¼
				// ��ǰ �ļ�����
				String newName = html_old_name.replace("G2_Rule13", newReplaceName);

				File curHtmlTargetFile = new File(curBasePath + File.separator + newName);

				// д���ĸ��ļ���

				// 1. �޲��� д�뵱ǰ�� shell ·����
				// 2. һ�����������

				writeContentToFile(curHtmlTargetFile, readHtmlContent);
				System.out.println("����ļ�:" + curHtmlTargetFile.getAbsolutePath());
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

		// ��⵱ǰ�� dirFile Ŀ¼���Ƿ���� �ڶ�������������ͬ���ļ���
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

		// ��鵱ǰĿ¼���Ƿ���ڶ�Ӧ����typeName �ľ�����ļ� ���ļ����Ƶĸ���
		int checkType3File(File dirFile, String typeName) {
			int existNum = 0;

			File[] fileList = dirFile.listFiles();
			if (fileList == null) {
				return existNum;
			}
			for (int i = 0; i < fileList.length; i++) {
				File dirFileItem = fileList[i];
				String dirName = dirFileItem.getName();
				// ��ǰ�ļ������ļ��� ���ҵ�ǰ�ļ����Ƶĺ�׺ �� .��type�� ���� .gif .jpg .mp4
				if (!dirFileItem.isDirectory() && dirFileItem.getName().endsWith("." + typeName)) {
					existNum++;
				}

			}
			return existNum;
		}

	}
	// // zrule_apply_G2.bat 12_mp4 <Ŀ���ļ���Ŀ¼> ### �ѵ�ǰĿ¼mp4�ļ����� html �����ļ�
	// // zrule_apply_G2.bat 12_jpg <Ŀ���ļ���Ŀ¼> ### ��û�����͵��ļ������޸�Ϊ jpg��ʽ����
	// // zrule_apply_G2.bat 12_gif <Ŀ���ļ���Ŀ¼> ### ��û�����͵��ļ������޸�Ϊ jpg��ʽ����

	class CalCulMediaHtml_Rule_12 extends Basic_Rule {

		ArrayList<File> operaDirFileList; // ��ǰ�Ӳ�����õ�Ŀ¼�ļ�����
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

			super("#", 12, 4);
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
				System.out.println("��ǰ�û�û������ִ�е�Ŀ¼����,����������!");
				return false;
			}

			return super.initParamsWithInputList(inputParamList);
		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			if (operaDirFileList.size() == 0) {
				System.out.println("��ǰ�û�û������ִ�е�Ŀ¼����,����������!");
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
				tryReName(curFile, newName1); // ��һ�θ��� �����ظ�
				File file1 = new File(parrentFilePath + File.separator + newName1);
				tempFileList1.add(file1);
			}
			for (int i = 0; i < tempFileList1.size(); i++) {
				index = i + 1;
				File curFile = tempFileList1.get(i);
				String newName = index + fileTypeWithPoint;
				tryReName(curFile, newName); // �ڶ��θ��� ʵ��˳�� 1.xx 2.xx 3.xx 4.xx
			}

		}

		void tryMP4HtmlOperation(File curDirFile, int num) {
// �ѵ�ǰ��html�ļ� �е�  ��Ӧ�� ռλ�� �� num ���� �滻
// ��  html�ļ��� mp4/  ת��Ϊ   ��ǰĿ¼����  90890/
// �ѵ�ǰ�� html  �ļ� ���뵽��ǰ�� shell�� �� Ŀ¼    html����Ϊ   ����Ŀ¼_ԭ������
			String curDirName = curDirFile.getName();

			for (int i = 0; i < mp4HtmlTemplate_FileList.size(); i++) {
				File HtmlFile = mp4HtmlTemplate_FileList.get(i);
				if (!HtmlFile.exists()) {
					System.out.println("ע�⵱ǰHtml�ļ�������!  PATH:  " + HtmlFile.getAbsolutePath());
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
					System.out.println("ע�⵱ǰHtml�ļ�������!  PATH:  " + HtmlFile.getAbsolutePath());
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
					System.out.println("ע�⵱ǰHtml�ļ�������!  PATH:  " + HtmlFile.getAbsolutePath());
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
			return "\n" + Cur_Bat_Name + "  #_12_mp4   <Ŀ���ļ���Ŀ¼>   ### �ѵ�ǰĿ¼mp4�ļ����� html �����ļ�  \n" + Cur_Bat_Name
					+ "  #_12_gif   <Ŀ���ļ���Ŀ¼>   ### �ѵ�ǰĿ¼gif�ļ����� html �����ļ�  \n" + Cur_Bat_Name
					+ "  #_12_jpg   <Ŀ���ļ���Ŀ¼>   ### �ѵ�ǰĿ¼jpg�ļ����� html �����ļ�  \n";
		}

	}

	class AllDirSubFile_Order_Rule_11 extends Basic_Rule {

		AllDirSubFile_Order_Rule_11() {
			super("#", 11, 5);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_11    ## (���ԭ�����ƣ����д�1��ʼ)�ѵ�ǰ������Ŀ¼���ļ� ��ǰĿ¼ �µ�ʵ���ļ����ΰ�˳��������������!  \n";
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
				// ��ȡ��ǰ�ļ����µ��������� �ļ�����Ϊ .jpg .png .mp4 Ϊkey ���е�
				Map<String, ArrayList<File>> curDirSubRealFile = getCurSubFileMap(dirFileItem);

				// ���ļ�����������

				Map.Entry<String, ArrayList<File>> entry;
				// ��ͬ�������ļ���ô����?

				if (curDirSubRealFile != null) {
					Iterator iterator = curDirSubRealFile.entrySet().iterator();
					while (iterator.hasNext()) {
						entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
						String typeStr = entry.getKey(); // Map��Value
						String typeWithOutPot = typeStr.replace(".", "");

						ArrayList<File> fileArr = entry.getValue(); // Map��Value

						// �� 000 ��ʼ
//                    fixedFileIndex = fixedFileIndex ;
						ArrayList<File> curRenamePlace = new ArrayList<File>();
						for (int m = 0; m < fileArr.size(); m++) {
							File curFile = fileArr.get(m);
							String oldName = curFile.getName();
							// String curFileName = curFile.getName();

							System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T m=" + m + "�T�T�T�T�T�T�T�T�T�T�T�T�T");
							// ռλ�� ʹ�� �����ļ��������ɹ� ������Щ�Ѿ��и������˵��ļ�
							String newName1 = "_ZHolder_" + (m + 1)
									+ ("".equals(typeWithOutPot) ? "" : "." + typeWithOutPot);
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
							if (tryReName(curFile, newName1)) {
								System.out.println("�ɹ� Index =" + m + "  ����( " + oldName + " => " + newName1 + ")  => "
										+ curFile.getAbsolutePath());
							} else {
								System.out.println("ʧ�� Index =" + m + "  ����( " + oldName + " => " + newName1 + ")  => "
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
								 * System.out.println("�ɹ� Index ="+m+"  ����( "+oldName+" => "+
								 * newName1+")  => "+curFile.getAbsolutePath()); }else{
								 * System.out.println("ʧ�� Index ="+m+"  ����( "+oldName+" => "+
								 * newName1+")  => "+curFile.getAbsolutePath()); }
								 */

							}

						}
						System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");

						for (int n = 0; n < curRenamePlace.size(); n++) {
							System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T n=" + n + "�T�T�T�T�T�T�T�T�T�T�T�T�T");

							File fileItem2 = curRenamePlace.get(n);
							String newName2 = fileItem2.getName().replace("_ZHolder_", "");
							if (tryReName(fileItem2, newName2)) {
								System.out.println("�ɹ� Index =" + n + "  ����( " + fileItem2.getName() + " => " + newName2
										+ ")  => " + fileItem2.getAbsolutePath());
							} else {
								System.out.println("ʧ�� Index =" + n + "  ����( " + fileItem2.getName() + " => " + newName2
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
				// ��ȡ��ǰ�ļ����µ��������� �ļ�����Ϊ .jpg .png .mp4 Ϊkey ���е�
				Map<String, ArrayList<File>> curDirSubRealFile = getCurSubFileMap(dirFileItem);

				// ���ļ�����������

				Map.Entry<String, ArrayList<File>> entry;
				// ��ͬ�������ļ���ô����?

				if (curDirSubRealFile != null) {
					Iterator iterator = curDirSubRealFile.entrySet().iterator();
					while (iterator.hasNext()) {
						entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
						String typeStr = entry.getKey(); // Map��Value
						String typeWithOutPot = typeStr.replace(".", "");

						ArrayList<File> fileArr = entry.getValue(); // Map��Value

						// �� 000 ��ʼ
//                    fixedFileIndex = fixedFileIndex ;
						ArrayList<File> curRenamePlace = new ArrayList<File>();
						for (int m = 0; m < fileArr.size(); m++) {
							File curFile = fileArr.get(m);
							String oldName = curFile.getName();
							// String curFileName = curFile.getName();

							System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T m=" + m + "�T�T�T�T�T�T�T�T�T�T�T�T�T");
							// ռλ�� ʹ�� �����ļ��������ɹ� ������Щ�Ѿ��и������˵��ļ�
							String newName1 = "_ZHolder_" + m + ("".equals(typeWithOutPot) ? "" : "." + typeWithOutPot);
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
							if (tryReName(curFile, newName1)) {
								System.out.println("�ɹ� Index =" + m + "  ����( " + oldName + " => " + newName1 + ")  => "
										+ curFile.getAbsolutePath());
							} else {
								System.out.println("ʧ�� Index =" + m + "  ����( " + oldName + " => " + newName1 + ")  => "
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
								 * System.out.println("�ɹ� Index ="+m+"  ����( "+oldName+" => "+
								 * newName1+")  => "+curFile.getAbsolutePath()); }else{
								 * System.out.println("ʧ�� Index ="+m+"  ����( "+oldName+" => "+
								 * newName1+")  => "+curFile.getAbsolutePath()); }
								 */

							}

						}
						System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");

						for (int n = 0; n < curRenamePlace.size(); n++) {
							System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T n=" + n + "�T�T�T�T�T�T�T�T�T�T�T�T�T");

							File fileItem2 = curRenamePlace.get(n);
							String newName2 = fileItem2.getName().replace("_ZHolder_", "");
							if (tryReName(fileItem2, newName2)) {
								System.out.println("�ɹ� Index =" + n + "  ����( " + fileItem2.getName() + " => " + newName2
										+ ")  => " + fileItem2.getAbsolutePath());
							} else {
								System.out.println("ʧ�� Index =" + n + "  ����( " + fileItem2.getName() + " => " + newName2
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

	// // zrule_apply_G2.bat #_10_append 2001 ����ǰ�ļ��к�׺���� 2001
	// // zrule_apply_G2.bat #_10_prefix 2001 ����ǰ�ļ���ǰ׺���� 2001
	// // zrule_apply_G2.bat #_10_create 1_100 ����һ�����кŴ�1��100��100���ļ���
	// // zrule_apply_G2.bat #_10_create temp_ 1_100 ����һ�����кŴ�temp1��temp100��100���ļ���
	// // zrule_apply_G2.bat #_10_create _temp 1_100 ����һ�����кŴ�1temp��100temp��100���ļ���
	// // zrule_apply_G2.bat #_10_create i_temp 1_100
	// ����һ�����кŴ�i1temp��i100temp100��100���ļ���

	// // zrule_apply_G2.bat #_10_create 7000_7100 ����һ�����кŴ�7000��ʼ�ĵ�7100�������ļ���
	// // zrule_apply_G2.bat #_10_replace abc_DEF ����һ�����кŴ�7000��ʼ�ĵ�7100�������ļ���

	class DirOperation_Rule_10 extends Basic_Rule {

		String firstParamStr; // ��һ������

		int DIR_OPERA_TYPE_APPEND = 1; // ��׺����
		String appendStr_1;
		int DIR_OPERA_TYPE_PREFIX = 2; // ǰ׺����
		String prefixStr_2;

		int DIR_OPERA_TYPE_CREATE = 3; // �����ļ�
		int beginIndex_3;
		int endIndex_3;
		String prefixStr_3;
		String appendStr_3;

		int DIR_OPERA_TYPE_REPLACE = 4; // �滻�ļ�������
		String replacedStr_4;
		String newNameStr_4;

		// ʶ��ǰ�û� ָ���Ĳ������� 1��׺���� 2ǰ׺���� 3�����ļ� 4�滻�ļ�������
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
						continue; // ��һ������������
					}

					if (!paramItem.contains("_")) {
						falg = false;
						continue;
					}
					String fixedParam = paramItem.replace("_", "");

					if (isNumeric(fixedParam)) { // ����� ��ĸ ˵������ʼ���Ǹ�����
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
					} else { // ���ƵĲ���
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
				System.out.println("��ǰ currentOperaType = " + currentOperaType + "  û���ҵ����ʵĲ�������ȥ���� ");
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
			return "\n" + Cur_Bat_Name + "  #_10_append  _over   ����ǰ�ļ��к�׺���� _over \n" + Cur_Bat_Name
					+ "  #_10_prefix  temp   ����ǰ�ļ���ǰ׺���� temp \n" + Cur_Bat_Name
					+ " #_10_create  1_100   ����һ�����кŴ�1��100��100���ļ���   \n" + Cur_Bat_Name
					+ " #_10_create   temp_  1_100   ����һ�����кŴ�temp1��temp100��100���ļ��� \n " + Cur_Bat_Name
					+ " #_10_create   _temp  1_100   ����һ�����кŴ�1temp��100temp��100���ļ��� \n " + Cur_Bat_Name
					+ " #_10_create   j_temp  1_100   ����һ�����кŴ� j_1_temp ��100temp�� j_100_temp ���ļ��� \n " + Cur_Bat_Name
					+ " #_10_create  7000_7100  ����һ�����кŴ�7000��ʼ�ĵ�7100�������ļ���  \n " + Cur_Bat_Name
					+ " #_10_replace  abc_DEF  �ѵ�ǰ�ļ��������е�  abc תΪ DEF \n ";
		}

	}

	// // zrule_apply_G2.bat #_9 _jpg ��û�����͵��ļ������޸�Ϊ jpg��ʽ����
	// // zrule_apply_G2.bat #_9 jpg_ ȥ����ǰjpg�ĸ�ʽ ʹ�����ļ���ʽδ֪

	// �� ��ǰĿ¼�����ļ� ���и�ʽ��ת��
	// // zrule_apply_G2.bat #_9 _jpg ��û�����͵��ļ������޸�Ϊ jpg��ʽ����
	// // zrule_apply_G2.bat #_9 jpg_ ȥ����ǰjpg�ĸ�ʽ ʹ�����ļ���ʽδ֪
	// zrule_apply_G2.bat #_9 jpg_png �� jpg�ĸ�ʽתΪpng�ĸ�ʽ
	// zrule_apply_G2.bat #_9 png_jpg �� jpg�ĸ�ʽתΪpng�ĸ�ʽ
	// // zrule_apply_G2.bat #_9 gif_ ȥ����ǰgif�ĸ�ʽ ʹ�����ļ���ʽδ֪
	// // zrule_apply_G2.bat #_9 _gif ��û�����͵��ļ������޸�Ϊ jpg��ʽ����
	// // zrule_apply_G2.bat #_9 mp4_ ȥ����ǰmp4�ĸ�ʽ ʹ�����ļ���ʽδ֪
	// // zrule_apply_G2.bat #_9 _mp4 ��û�����͵��ļ������޸�Ϊ mp4��ʽ����
	// // zrule_apply_G2.bat #_9 ԭ����_Ŀ������ ��û�����͵��ļ������޸�Ϊ jpg��ʽ����
	class FileType_Rule_9 extends Basic_Rule {
		String originType;
		String targetType;

		FileType_Rule_9() {
			super("#", 9, 3);
		}

		@Override
		String simpleDesc() {
			return "\n" + Cur_Bat_Name + "  #_9  _jpg   ��û�����͵��ļ������޸�Ϊ jpg��ʽ����\n" + Cur_Bat_Name
					+ "  #_9  jpg_   ȥ����ǰjpg�ĸ�ʽ ʹ�����ļ���ʽδ֪ \n" + Cur_Bat_Name + " #_9  jpg_png  ��  jpg�ĸ�ʽתΪpng�ĸ�ʽ  \n"
					+ Cur_Bat_Name + " #_9  png_jpg  ��  jpg�ĸ�ʽתΪpng�ĸ�ʽ \n " + Cur_Bat_Name
					+ " #_9  gif_   ȥ����ǰgif�ĸ�ʽ ʹ�����ļ���ʽδ֪  \n " + Cur_Bat_Name
					+ " #_9  _gif   ��û�����͵��ļ������޸�Ϊ jpg��ʽ����  \n " + Cur_Bat_Name + " #_9  png_jpg  ��  jpg�ĸ�ʽתΪpng�ĸ�ʽ \n "
					+ Cur_Bat_Name + " #_9  mp4_   ȥ����ǰmp4�ĸ�ʽ ʹ�����ļ���ʽδ֪ \n " + Cur_Bat_Name
					+ " #_9  _mp4   ��û�����͵��ļ������޸�Ϊ mp4��ʽ���� \n " + Cur_Bat_Name
					+ " #_9  7z_7��z   �ѵ�ǰ 7z�ļ�����׺��Ϊ 7��z ʹ���޷����������� \n " + Cur_Bat_Name
					+ " #_9  ԭ����_Ŀ������   ��û�����͵��ļ����ơ�ԭ���͡�->��Ŀ�����͡� \n ";
		}

		@Override
		boolean initParamsWithInputList(ArrayList<String> inputParamList) {
			boolean Flag = true;

			// ��ȡ��װ��������
			String inputFileTypeParams = inputParamList.get(inputParamList.size() - 1);

			if (!inputFileTypeParams.contains("_")) {
				Flag = false;
				System.out.println("�޷���⵽��ǰ ��9 Rule   ԭʼ����_Ŀ�����Ͳ���   ���������ִ��");
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
				// ִ�� �޸��ļ����͵Ĳ���

				// 1. �����ǰ�ļ� ���������� �� ��ô ���ܾ���û���κε�������
				// �����ǰ���˵������� originType ��"" �յĻ� ��ô�ͻ���˳����е��ļ� ��ôֻ���� ������.����Щ�ļ�
				if ("".equals(originType)) {
					if (originName.contains(".")) {
						continue; // ������ . ˵�������� ��ô ������
					}
					String newName = originName + "." + targetType;
					tryReName(curFIle, newName);
				} else {
					// �о���� ���˵��ļ�
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

	// ���ļ���׺�е����ĸ�ȥ���� �������ļ��� ���������ļ�
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

			System.out.println("Rule8_ClearChineseType_8   ��������ʵ���ļ�����:" + curRealFileList.size());

			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = curFile.getName();
				if (currentFileName.contains(".")) {
					String typeStr = currentFileName.substring(currentFileName.lastIndexOf("."));
					if (isContainChinese(typeStr)) {
						// //������� ��� �ո�
						String newType = clearChinese(typeStr).replace(" ", "");
						String newName = currentFileName.replace(typeStr, newType); // ������
						System.out.println("newType = " + newType + "    newName=" + newName);
						tryReName(curFile, newName);
					}
				}

			}

			return curRealFileList;
		}

		@Override
		String simpleDesc() {
			return "�ѵ�ǰ������ļ�����.���ļ��� ��׺�����е����������  ���� 1.7��z -> 1.7z   2.��zip -> 2.zip \n" + Cur_Bat_Name
					+ " #_8    <ָ����׺�����ĵ��ļ�>  [����8]   // �ѵ�ǰĿ¼���ļ� ��׺����ȥ��  \n";
		}
	}

	// operation_type �������� 1--��ȡ�ļ������ַ��� �����޸� 2--���ļ����ļ�����(�ֽ�)--�����޸� 3.��ȫ�����ļ����е����ԵĲ���
	// ���Խ����޸�(�ļ�����)
	// // 4.�Ե�ǰ���ļ�(������Ŀ¼ ���ļ� --��������Ŀ¼ ���ļ�) 5. ��shell �л�ȡ����·�� ȥ��ĳһ���ļ����в���

	// �Ե�ǰĿ¼���ļ����м��� ����
	class Encropty_Rule_7 extends Basic_Rule {
		boolean mEncroptyDirect = true; // true---���� false--����
		boolean isAllFileOperation = false;

		boolean isBatchOperation = false; // �Ƿ������������� �����ɹ̶��� bad_batch good_batch �ļ��� ������ʱ����ļ���

		Encropty_Rule_7() {
			super("#", 7, 4);
			isAllFileOperation = false;
			isBatchOperation = false;
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

			return super.initParams4InputParam(inputParam);
		}

		@Override
		String simpleDesc() {
			return "   Ĭ��bad(����) �ѵ�ǰĿ¼�µ������ļ�(�������ļ���  ���������ļ�)���� ����bad/����good\n" + Cur_Bat_Name
					+ " #_7_bad   (Ĭ��--�����ļ�)  �ѵ�ǰĿ¼�µ������ļ�(�������ļ���  ���������ļ�)���� ����bad ���� �� time + bad �� �����ļ��� \n"
					+ Cur_Bat_Name + " #_7_good   (�����ļ�) �ѵ�ǰĿ¼�µ������ļ�(�������ļ���  ���������ļ�)���� ����good �� time + good �� ���ɽ����ļ���\n"
					+ Cur_Bat_Name + " jpg_7_bad  [����7]   // �ѵ�ǰĿ¼�µ� jpg�ļ� ���� \n" + Cur_Bat_Name
					+ " jpg_7_good  [����7]   // �ѵ�ǰĿ¼�µ� jpg�ļ� ���� \n" + Cur_Bat_Name
					+ " #_7_bad  [����7]   // �ѵ�ǰĿ¼�����ļ����м���  �����ļ����µ� ʱ����ļ����� \n" + Cur_Bat_Name
					+ " #_7_good  [����7]   // �ѵ�ǰĿ¼�����ļ����н���  �����ļ����µ� ʱ����ļ����� \n" + Cur_Bat_Name
					+ " #_7_bad_batch   [����7]   // �ѵ�ǰĿ¼�����ļ����м���  �����ļ����µġ� �̶��ļ��� bad_batch ���� �ʺ��������� \n" + Cur_Bat_Name
					+ " #_7_good_batch   [����7]   // �ѵ�ǰĿ¼�����ļ����н��� �����ļ����µġ� �̶��ļ��� good_batch ���� �ʺ��������� " + "\n";

		}

//                    return "�� ��ǰĿ¼�����е� jpg  mp4 gif  ��תΪ i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif ���ļ���ʽ\n" +
//        Cur_Bat_Name + "  jgm_5_recovery  [����5]   // �ڵ�ǰ Z_VI ��Ŀ¼ ���� ��ǰ�� JPG GIF MP4����ʼֵ \n" +
//        Cur_Bat_Name + "  jgm_5_nextstep  [����5]   //  JPG="+jpgBeginIndex+ " GIF="+gifBeginIndex+" MP4="+mp4BeginIndex+"  JPG����="+nextStepCountJPG +"    GIF����="+nextStepCountGIF + "   MP4����="+nextStepCountMP4+" ���� ��jpg gif png��������ӵ� beginIndex Ȼ��������0 �� \n ";

		void jiamiAllDir(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
			// 1.����һ��ʱ����ļ���
			// 2.�ڵ�ǰ�ļ��еĻ�����

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// �������ڸ�ʽ
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
			System.out.println("ִ�е�ǰ�����ļ� ���ܲ���  ");

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
					// ���ܲ���
					createEncryFile(oldRealFile, newRealFile);
				}

			}

			/*
			 * for (int i = 0; i < curRealFileList.size(); i++) { File oldRealFile =
			 * curRealFileList.get(i); String newRealFilePath =
			 * oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath); File
			 * newRealFile = new File(newRealFilePath); // ���ܲ���
			 * createEncryFile(oldRealFile,newRealFile); }
			 */

		}

		void jiemiAllDir(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap,
				ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

			// 1.����һ��ʱ����ļ���
			// 2.�ڵ�ǰ�ļ��еĻ�����

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// �������ڸ�ʽ
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
			String oldBasePath = curDirFile.getAbsolutePath(); // ԭ�е�·�� /C
			String newBasePath = curBadDirFile.getAbsolutePath(); // ���ɵ� ��·�� /C/good_batch
			if (!curDirList.contains(curDirFile)) {
				curDirList.add(curDirFile);
			}
			System.out.println("ִ�е�ǰ�����ļ� ���ܲ��� ");

			for (int i = 0; i < curDirList.size(); i++) {
				File oldDirFile = curDirList.get(i); // ԭ�е�Ҫ�����ļ�

				if (!isBatchOperation) { // ��� ���� batch ��ô�ᴴ���ļ���
					// ����ǵ�ǰĿ¼�¶� ���ļ��� ��ô�Ͱ���* �� ����»� ��������ļ��� ��ʵ���� batch������²���Ҫ����ļ���
					String newDirFilePath = oldDirFile.getAbsolutePath().replace(oldBasePath, newBasePath);
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

					if (!isBatchOperation) { // ��� ���� batch ��ô�ᴴ���ļ��� ��ԭ������һ��
						String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
						File newRealFile = new File(newRealFilePath);
						// ���ܲ���
						createDecryFile(oldRealFile, newRealFile);
					} else {
//    String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
						String batch_fileName = oldRealFile.getName();
						File newRealFile = new File(newBasePath + File.separator + batch_fileName);
						// ���ܲ���
						createDecryFile(oldRealFile, newRealFile);
					}

				}
			}

//            for (int i = 0; i < curRealFileList.size(); i++) {
//                File oldRealFile = curRealFileList.get(i);
//                String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
//                File newRealFile = new File(newRealFilePath);
//                // ���ܲ���
//                createDecryFile(oldRealFile,newRealFile);
//            }

		}

		@Override
		ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList) {
			System.out.println("Rule7 ��������ʵ���ļ�����:  curRealFileList.size() =" + curRealFileList.size());
			if (isAllFileOperation) {
				if (mEncroptyDirect) {
					// ���������ļ���

					jiamiAllDir(curFileList, subFileTypeMap, getAllSubDirFile(curDirFile), curRealFileList);
				} else {
					// ���ܵ�ǰ�����ļ���
					jiemiAllDir(curFileList, subFileTypeMap, getAllSubDirFile(curDirFile), curRealFileList);

				}
				return null;
			}
			boolean containUserType = curFilterFileTypeList.contains("#"); // �Ƿ�����û�ѡ�е����ļ����� û�а��� ��ô�Ͱ�����ʵ��realty ����

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// �������ڸ�ʽ
			String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ�䣬Ҳ��ʹ�õ�ǰʱ���
			String curNewDirName = date;
			if (mEncroptyDirect) {
				curNewDirName += "_bad";
			} else {
				curNewDirName += "_good";
			}

			if (!containUserType) {
				curNewDirName += "_" + curFilterFileTypeList.get(0); // 1.��������ļ������� ��ôû�к�׺ ���ĳһ���ļ����ͽ��� ��ô��Ӻ�׺
			}
			if (isBatchOperation) {
				if (mEncroptyDirect) {
					curNewDirName = "bad_batch";
				} else {
					curNewDirName = "good_batch";
				}
			}

			File tempDirFile = new File(curDirFile.getAbsolutePath() + File.separator + curNewDirName);
			tempDirFile.mkdirs(); // �����ļ���
			String tempDirPath = tempDirFile.getAbsolutePath();
			System.out.println("Rule7 ��������ʵ���ļ�����:" + curRealFileList.size());

			for (int i = 0; i < curRealFileList.size(); i++) {
				File curFile = curRealFileList.get(i);
				String currentFileName = File.separator + curFile.getName();

//                System.out.println("currentFileName = "+ currentFileName);
				if (mEncroptyDirect) { // ����ʱ ����� �� i_temp ��ͷ ���� �� .jpg Ϊ��βʱ ���ܵ�����ȥ��
					if (currentFileName.contains(".jpg") && currentFileName.contains("i_temp")) {
						currentFileName = currentFileName.replace(".jpg", "");
					} else if (currentFileName.contains(".mp4") && currentFileName.contains("v_temp")) {
						currentFileName = currentFileName.replace(".mp4", "");
					} else if (currentFileName.contains(".gif") && currentFileName.contains("g_temp")) {
						currentFileName = currentFileName.replace(".gif", "");
					}

					File badFile = new File(tempDirPath + File.separator + currentFileName);
					createEncryFile(curFile, badFile);
				} else { // ���� �����ǰ�ļ� ������ .
					if (!currentFileName.contains(".") && currentFileName.contains("i_temp")) {
						currentFileName = currentFileName + ".jpg";
					} else if (!currentFileName.contains(".") && currentFileName.contains("v_temp")) {
						currentFileName = currentFileName + ".mp4";
					} else if (!currentFileName.contains(".") && currentFileName.contains("g_temp")) {
						currentFileName = currentFileName + ".gif";
					}
					File goodFile = new File(tempDirPath + File.separator + currentFileName);
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

			return Cur_Bat_Name + " #_6    // �޸ĵ�ǰ��һ����Ŀ¼�µ��ļ��� �Լ��ļ�  ��˳������ �����_ԭ����.���͡�  (������ ���ļ� ���ļ��� )  \n"
					+ Cur_Bat_Name + " png_6    // �޸ĵ�ǰ��һ����Ŀ¼�µ��ļ����µ� png��ʽ�ļ�  ��˳������ �����_ԭ����.���͡�  (������ ���ļ� ���ļ��� ) \n"
					+ Cur_Bat_Name
					+ " png_6_order    // �޸ĵ�ǰ��һ����Ŀ¼�µ��ļ����µ� png��ʽ�ļ�  ��˳������ ��0000.png 0001.png 0002.png ....��  (������ ���ļ� ���ļ��� ) \n"
					+ Cur_Bat_Name
					+ " m3u8_6_order  // ��type<��ѡ>_6_order�� �޸ĵ�ǰ��Ŀ¼��ָ�����͵� �ļ�  ��˳������[0000.m3u8 0001.m3u8] �����.���͡���������ԭ���ơ�  (������ ���ļ� ���ļ��� )  \n";
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
			if (isFixedAllSubFlag) { // ֻ�а��� #_6 �Ŷ� �ļ��н��в��� png_6 ��ô��ֻ�� ��ǰ�ļ����µ� png�ļ����в���
				for (int i = 0; i < curDirList.size(); i++) {
					File dir = curDirList.get(i);
					String dirName = dir.getName();
					String new_dirName = i + "_" + dirName;
					tryReName(dir, new_dirName);
				}
			}

			Map.Entry<String, ArrayList<File>> entry;
			// ��ͬ�������ļ���ô����?

			if (subFileTypeMap != null) {
				Iterator iterator = subFileTypeMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map��Value
					String typeWithOutPot = typeStr.replace(".", "");

					if (!isFixedAllSubFlag && !curFilterFileTypeList.contains(typeWithOutPot)) {
						// ��� ��ǰ�������ǲ��������ļ� ����������Ͳ���ƥ���б��� ��ô ��ִ�� ����next
						// ����� ȫ ���� ��ô ����ִ��
						// �������ȫ���� ��ǰ���Ͱ��� ��ô����ִ��
						continue;
					}

					ArrayList<File> fileArr = entry.getValue(); // Map��Value

					// �� 000 ��ʼ
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

	public static String getFileTypeWithPoint(String fileName) {
		String name = "";
		if (fileName.contains(".")) {
			name = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
		} else {
			name = "";
		}
		return name.toLowerCase().trim();
	}

	// �� ��ǰĿ¼�����е� jpg mp4 gif ��תΪ i_temp1_1.jpg v_temp2_1.mp4 g_temp3_1.gif ���ļ���ʽ
	class AVI_Rule_5 extends Basic_Rule {
		String tempTag = "temp";
		boolean isTemp; // �Ƿ�����ʱ��ı��
		int mTempBeginIndex = 0; // ��ʱ��ŵ�Ĭ����ʼ��ַ

		boolean isRecovrty = false; // ��ǰ�Ƿ��� ��ȡ��ǰĿ¼ ���� ProPerities��ֵ�Ĳ���
		boolean isEnable = true; // ������������ʱ�� �������� ��ִ�� ��¼�Ĳ���
		boolean isExistAddPart = false; // �Ƿ��������
		boolean executeNextStep = false; // ���û������ ������� ���� nextstep ʱ ִ�� ������ ����0���� ��ӵ�index�Ĳ���

		int jpgBeginIndex = 0;
		int fixed_jpg_BeginIndex = 0;
		String jpgtag = "i";
		int jpgDirTempIndex = 0;
		int jpgEndIndex = 1;
		int nextStepCountJPG = 0; // ��ǰ JPG�� ����

		int gifBeginIndex = 0;
		String giftag = "g";
		int gifDirTempIndex = 0;
		int fixed_gif_BeginIndex = 0;
		int gifEndIndex = 1;
		int nextStepCountGIF = 0; // ��ǰ GIF�� ����

		int mp4BeginIndex = 0; // �� Propertities �ж�ȡ����ֵ
		String mp4tag = "v"; // mp4��ǰ׺
		int mp4DirTempIndex = 0; // ���� mp4BeginIndex ������� temp1 temp2 .... temp100
		int fixed_mp4_BeginIndex = 0; // �ڵ�ǰ tempx �е����� ��СΪ mp4BeginIndex%1000
		int mp4EndIndex = 1; // ��󱣴浽 Propertities �е� ֵ
		int nextStepCountMP4 = 0; // ��ǰ MP4 �� ����

		AVI_Rule_5() {
			super("jgm", 5, 3);
			curFilterFileTypeList.add("jpg");
			curFilterFileTypeList.add("gif");
			curFilterFileTypeList.add("mp4");
			// �� Proprietary �õ���ǰ���ܵ����� ֵ
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
			return "�� ��ǰĿ¼�����е� jpg  mp4 gif  ��תΪ i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif ���ļ���ʽ\n" + Cur_Bat_Name
					+ "  jgm_5_temp0      [����5]   // ��ʱ�ѵ�ǰgif jpg mp4 ���� ��ʼλ������Ϊ0   \n" + Cur_Bat_Name
					+ "  jgm_5_temp99      [����5]   // ��ʱ�ѵ�ǰgif jpg mp4 ���� ��ʼλ������Ϊ99   \n" + Cur_Bat_Name
					+ "  jgm_5_recovery  [����5]   // �ڵ�ǰ Z_VI ��Ŀ¼ ���� ��ǰ�� JPG GIF MP4����ʼֵ \n" + Cur_Bat_Name
					+ "  jgm_5_nextstep  [����5]   //  JPG=" + jpgBeginIndex + " GIF=" + gifBeginIndex + " MP4="
					+ mp4BeginIndex + "  JPG����=" + nextStepCountJPG + "    GIF����=" + nextStepCountGIF + "   MP4����="
					+ nextStepCountMP4 + " ���� ��jpg gif png��������ӵ� beginIndex Ȼ��������0 �� \n ";
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
						mTempBeginIndex = 0; // Ĭ��Ϊ0
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
				curFilterFileTypeList.add("#"); // �ѵ�ǰ�����ļ������뵽�б���
			}
			System.out.println("OLD��¼��Properties��Ϣ:(OLD)   " + " JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
					+ "   MP4=" + mp4BeginIndex + "  JPG����=" + nextStepCountJPG + "    GIF����=" + nextStepCountGIF
					+ "   MP4����=" + nextStepCountMP4);

			if (executeNextStep) { // ����������� ��ǰ��ִ�� �����û�������� nextstep��ʱ�� ִ�� step�ĸ���
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
				System.out.println("��ǰִ��Ŀ¼���� Z_VI�ĸ�Ŀ¼ Git_Dir , ������ִ�� " + Cur_Bat_Name);
				return;
			}

			// ͨ�� ���� ����õ��� type �ļ��� ���� Count
			// ͨ�� ���� �ļ��������ֵõ��� index = Count - 1
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

			if (jpgDynimicCount != jpgBeginIndex || (jpgLastIndex + 1) != jpgDynimicCount) { // ��С �� ��¼����ʼ�� ��һ�� ��ô��Ҫ
																								// ���¸�����
				for (int i = 0; i < jpgTempList.size(); i++) {
					File jpgFile = jpgTempList.get(i);
					String jpgFileName = "i" + "_" + getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,
							gifDirTempIndex, 0, i, 3, "0", true);
					tryReName(jpgFile, jpgFileName);
				}
			}

			if (gifDynimicCount != gifBeginIndex || (gifLastIndex + 1) != gifDynimicCount) { // ��С �� ��¼����ʼ�� ��һ�� ��ô��Ҫ
																								// ���¸�����
				for (int i = 0; i < gifTempList.size(); i++) {
					File gifFile = gifTempList.get(i);
					String gifFileName = "g" + "_" + getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,
							gifDirTempIndex, 0, i, 3, "0", true);
					tryReName(gifFile, gifFileName);
				}
			}

			if (mp4DynimicCount != mp4BeginIndex || (mp4LastIndex + 1) != mp4DynimicCount) { // ��С �� ��¼����ʼ�� ��һ�� ��ô��Ҫ
																								// ���¸�����
				for (int i = 0; i < mp4TempList.size(); i++) {
					File mp4File = mp4TempList.get(i);
					String mp4FileName = "v" + "_" + getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,
							gifDirTempIndex, 0, i, 3, "0", true);
					tryReName(mp4File, mp4FileName);
				}
			}

			System.out.println("recovery ���������ļ� ����:" + subFileList.size());
			if (lastJPGFile != null) {
				jpgLastIndex = calculIndexFromName(lastJPGFile.getName());
				System.out.println("���һ�� JPG �ļ�������Ϊ:" + lastJPGFile.getName() + "  ����:" + jpgLastIndex
						+ (jpgLastIndex != (jpgBeginIndex - 1) ? " ƥ�䲻�ɹ�(��������)" : "ƥ��ɹ�"));

			}
			if (lastGIFFile != null) {
				gifLastIndex = calculIndexFromName(lastGIFFile.getName());
				System.out.println("���һ�� GIF �ļ�������Ϊ:" + lastGIFFile.getName() + "  ����:" + gifLastIndex
						+ (gifLastIndex != (gifBeginIndex - 1) ? " ƥ�䲻�ɹ�(��������)" : "ƥ��ɹ�"));

			}
			if (lastMP4File != null) {
				mp4LastIndex = calculIndexFromName(lastMP4File.getName());
				System.out.println("���һ�� MP4 �ļ�������Ϊ:" + lastMP4File.getName() + "  ����:" + mp4LastIndex
						+ (mp4LastIndex != (mp4BeginIndex - 1) ? " ƥ�䲻�ɹ�(��������)" : "ƥ��ɹ�"));
			}
			System.out.println(
					"jpgDynimicIndex(JPG��̬�����ļ�����)=" + getXsizeString(jpgDynimicCount, 7) + "   (���һ��JPG�ļ���������+1)"
							+ getXsizeString(jpgLastIndex + 1, 7) + " ||    Pro��¼ jpgBeginIndex Ϊ:" + jpgBeginIndex);
			System.out.println(
					"gifDynimicIndex(GIF��̬�����ļ�����)=" + getXsizeString(gifDynimicCount, 7) + "   (���һ��GIF�ļ���������+1)"
							+ getXsizeString(gifLastIndex + 1, 7) + " ||    Pro��¼ gifBeginIndex Ϊ:" + gifBeginIndex);
			System.out.println(
					"mp4DynimicIndex(MP4��̬�����ļ�����)=" + getXsizeString(mp4DynimicCount, 7) + "   (���һ��MP4�ļ���������+1)"
							+ getXsizeString(mp4LastIndex + 1, 7) + " ||    Pro��¼ mp4BeginIndex Ϊ:" + mp4BeginIndex);

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
			valueA = valueA.replace("��", "");
			valueA = valueA.replace("��", "");

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

			System.out.println(" Z_VI(Git_Dir)�ָ�Pro��:(New)    JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
					+ "   MP4=" + mp4BeginIndex + "  JPG����=0    GIF����=0   MP4����=0");
		}

		@SuppressWarnings("unchecked")
		@Override
		ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
			boolean executeFlag = false;

			if (isRecovrty) { // �����Ҫ�ָ��ĵĻ�
				tryDynamicCalCulateBeginIndex(subFileList);
				return null;
			}

			String oldAddPart = "OLD ��¼��Properties����:(OLD)   " + " JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
					+ "   MP4=" + mp4BeginIndex + "  JPG����=" + nextStepCountJPG + "    GIF����=" + nextStepCountGIF
					+ "   MP4����=" + nextStepCountMP4;
			if (!isEnable) {
				System.out.println("��ǰ Rule5 �����ϵ������Ѿ���0  �����ѵõ�ȷ��  �뿪ʼ�ۼ��µ���Դ! ");
				System.out.println("��ǰ��¼��Prop��������Ϣ:(New)  " + " JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
						+ "   MP4=" + mp4BeginIndex + "  JPG����=" + 0 + "    GIF����=" + 0 + "   MP4����=" + 0);

				return null;
			}
			if (isExistAddPart) {
				System.out.println("��ǰ Rule5 ��������ϴλ�δȷ�ϵ����� ��ִ������������ȷ������ ʹ��NextStep���\n" + Cur_Bat_Name
						+ " jgm_5_nextstep      // ���� ��jpg gif png��������ӵ� beginIndex Ȼ��������0 �� \n ");
			}
			Map.Entry<String, ArrayList<File>> entry;
			int nextStepCountJPG_new = 0;
			int nextStepCountGIF_new = 0;
			int nextStepCountMP4_new = 0;
			if (fileTypeMap != null) {
				Iterator iterator = fileTypeMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
					String typeStr = entry.getKey(); // Map��Value
					ArrayList<File> fileArr = entry.getValue(); // Map��Value
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
						System.out.println("��ǰJPG��ʼֵ:" + fixedFileIndex + "    ��ǰGIF���ļ�����:" + fileArr.size());
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
						System.out.println("��ǰMP4��ʼֵ:" + fixedFileIndex + "    ��ǰGIF���ļ�����:" + fileArr.size());
					} else if (".gif".equals(typeStr)) {
						typeTag = giftag;
						dirTempIndex = tempTag + gifDirTempIndex;
						fixedFileIndex = gifBeginIndex;
						tempIndex = gifDirTempIndex;
						nextStepCountGIF = fileArr.size();
						System.out.println("��ǰGIF��ʼֵ:" + fixedFileIndex + "    ��ǰGIF���ļ�����:" + fileArr.size());
						nextStepCountGIF_new = fileArr.size();
						if (!isTemp)
							G2_Properties.setProperty("nextStepCountGIF", "" + nextStepCountGIF);
						gifEndIndex = gifBeginIndex + fileArr.size();

					} else {
						continue;
					}

					if (isTemp) {
						fixedFileIndex = mTempBeginIndex; // ����� temp ��ô Ĭ�� �Ͱ� tempתΪ index
						nextStepCountJPG_new = 0;
						nextStepCountGIF_new = 0;
						nextStepCountMP4_new = 0;
					}
					// �� 000 ��ʼ
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

			String NewAddPart = "New ��¼��Properties����:(New)   " + " JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
					+ "   MP4=" + mp4BeginIndex + "  JPG����=" + nextStepCountJPG_new + "    GIF����="
					+ nextStepCountGIF_new + "   MP4����=" + nextStepCountMP4_new;

			System.out.println("�T�T�T�T�T�T�T�T�T�Tȷ��������Ϣ Begin�T�T�T�T�T�T�T�T�T�T");
			if (isExistAddPart) {
				// System.out.println("Rule5 �ϴε��������:");
				System.out.println(oldAddPart);
			} else {
				System.out.println("OLD     �ϴεĲ���������:(OLD)    JPG=" + jpgBeginIndex + "   GIF=" + gifBeginIndex
						+ "   MP4=" + mp4BeginIndex + " JPG����=0     GIF����=0     MP4����=0");
			}
			// System.out.println("\nRule5 ���ڵ��������: ");
			System.out.println(NewAddPart);

			System.out.println("New ����ʹ����������� New ��ǰ����������ȷ��! \n" + Cur_Bat_Name
					+ " jgm_5_nextstep      // ���� ��jpg gif png��������ӵ� beginIndex Ȼ��������0 �� \n ");
			System.out.println("�T�T�T�T�T�T�T�T�T�Tȷ��������Ϣ End�T�T�T�T�T�T�T�T�T�T");
			if (executeFlag) {
				return curFixedFileList;
			}
			return super.applyFileListRule3(subFileList, fileTypeMap);
		}

		// �� ��ʼ�ĵ�ַ beginIndex ��ʼ����
		String getPaddingIntStringWithDirIndexFileNameWithIndex(String cTempTag, int CurrentTempIndex, int beginIndex,
				int index, int padinglength, String oneStr, boolean dirPre) {

			int indexIdentify = beginIndex + index;
			int tempIndexResult = (indexIdentify / 1000);
			String result = "" + getXsizeString((indexIdentify % 1000), oneStr, padinglength, dirPre);
			return cTempTag + tempIndexResult + "_" + result;

		}

		// ������ʼ�ĵ�ַ ���� ��0��1,2,3.... ��ʼ����
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

	// �� ��ǰĿ¼�����е� png jpeg ��תΪ jpg�ĸ�ʽ
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
			return " �ѵ�ǰĿ¼(������Ŀ¼)���е� .jpg .jpeg ���ļ���׺��Ϊ .png ���ļ���׺";
		}
	}

	// �� ��ǰĿ¼�����е� png jpeg ��תΪ jpg�ĸ�ʽ
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
			return " �ѵ�ǰĿ¼(������Ŀ¼)���е� .png .jpeg ���ļ���׺��Ϊ .jpg ���ļ���׺";
		}
	}

	// ָ��ʲô���͵��ļ��ڵ�ǰʹ��ʲô���Ĺ���
	// operation_type ��������
	// 1--��ȡ�ļ������ַ��� �����޸� String applyOperationRule(String origin)
	// 2--�Ե����ļ����Խ����޸�(�ļ�����) ���ļ�����(�ֽ�)--�����޸� File applyFileByteOperationRule(File
	// originFile)
	// 3--�Լ����ļ����Խ����޸�(�ļ�����) ���������ļ�--�����޸� ArrayList<File>
	// applyFileByteOperationRule(ArrayList<File> subFileList)
	// index Ψһָ����һ�� rule����

	// file_name_2 #_2 �Ե�ǰĿ¼�µ������ļ����� �ļ����Ƶ��������� �������� ��ͷ��������

	class File_Name_Rule_2 extends Basic_Rule {

		// key = type value = ���Ϲ����ļ���������Ƶ��ļ��ļ���
		// HashMap<String, ArrayList<File>> arrFileMap;
		boolean keepOriginalName = true;
		int inputBeginIndex = 0;

		// �Ƿ��ǰ� 1.jpg 2,jpg 3.png 4.png �������� ������ 1.jpg 2,jpg 1.png 2.png ����������
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
					String typeStr = entry.getKey(); // Map��Value
					ArrayList<File> fileArr = entry.getValue(); // Map��Value

					for (int i = 0; i < fileArr.size(); i++) {
						fileOrderIndex++;
						int index = i + 1;
						String newNamePre = index + "_";
						File curFile = fileArr.get(i);
						String curFileName = curFile.getName();
						String newName = "";
						if (keepOriginalName) {
							if (isOrder) { // ��˳������ ���� type�� һֱ��
								newName = fileOrderIndex + "_" + curFileName;
							} else {
								newName = newNamePre + curFileName;
							}
						} else {
							// ������������� ��ôû�����͵��ļ� ��ֻ�� ��� û������
							if ("unknow".equals(typeStr)) {
								newName = index + "";
							} else {
								if (isOrder) { // ��˳������ ���� type�� һֱ��
									newName = fileOrderIndex + typeStr;
								} else {
									newName = index + typeStr;
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
			String desc_true = " (����ԭ����) �ѵ�ǰ���������ļ�(��Ŀ¼)������Ϊ �����_ԭʼ����.���͡�����ʽ ���� hello.jpg =�� 1_hello.jpg  xx.jpg-��2_xx.jpg    001/4.jpg -> 001/3_4.jpg(��ͬ�ļ���)   ����ԭ������ ��ͬ�����ļ���ͬ�ļ��� ʹ��ͬһ�����к�   ";
			String desc_true_1 = " (����ԭ����_���������δ�1��ʼ order ) �ѵ�ǰ���������ļ�(��Ŀ¼)������Ϊ �����_ԭʼ����.���� �ߵ��ס�����ʽ ���� hello.jpg =�� 1_hello.jpg  xx.jpg-��2_xx.jpg   aa.png -> 3_aa.png  | 001/4.zip ->  001/4_4.zip  ����ԭ������ ����ͬ�����ļ���ͬ�ļ��� ʹ��ͬһ�����к� ";
			String desc_true_2 = " (����ԭ����_������������Ϊ��ʼ order ) �ѵ�ǰ���������ļ�(��Ŀ¼)������Ϊ ���Զ������_ԭʼ����.���� �ߵ��ס�����ʽ ���� #_2_false_order_50  hello.jpg =�� 50_hello.jpg  xx.jpg-��51_xx.jpg   aa.png -> 52_aa.png ����ԭ������ ����ͬ�����ļ���ͬ�ļ��� ʹ��ͬһ�����к�(����Զ���) ";

			String desc_false = "(���ԭ����) �ѵ�ǰ���������ļ�(��Ŀ¼)������Ϊ �����.���͡�����ʽ ���� hello.jpg =�� 1.jpg  xx.png-��1.jpg   ������ԭ������ ��ͬ�����ļ���ͬ�ļ��� ʹ��ͬһ�����к�";
			String desc_false_1 = "(���ԭ����_���������� order ) �ѵ�ǰ���������ļ�(��Ŀ¼)������Ϊ �����.���� �ߵ��� ������ʽ ���� hello.jpg =�� 1.jpg  xx.jpg-��2_xx.jpg  xx.png-��3.png  xx.png-��4.png  ������ԭ������ ����ͬ�����ļ���ͬ�ļ��� ʹ��ͬһ�����к� ";
			String desc_false_2 = "(���ԭ����_������ ������������Ϊ��ʼ order ) �ѵ�ǰ���������ļ�(��Ŀ¼)������Ϊ ������Begin���.���� �ߵ��� ������ʽ ����   #_2_false_order_10  hello.jpg =�� 10.jpg  xx.jpg-��11_xx.jpg  xx.png-��12.png  xx.png-��13.png  ������ԭ������ ����ͬ�����ļ���ͬ�ļ��� ʹ��ͬһ�����к�(����Զ���) ";

			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index + "_true" + "    [���� " + index + "]  ����: "
						+ desc_true + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order" + "    [���� " + index
						+ "]  ����: " + desc_true_1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order_20" + "    [ָ����ʼ���� "
						+ index + "]  ����: " + desc_true_2 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false" + "    [���� " + index
						+ "]  ����:" + desc_false + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order" + "    [���� " + index
						+ "]  ����:" + desc_false_1 + "\n";
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order_10" + "    [ָ����ʼ���� "
						+ index + "]  ����:" + desc_false_2 + "\n";

			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index + "_true" + "    [���� " + index + "]  ����:"
						+ desc_true;
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_true_order" + "    [���� " + index
						+ "]  ����: " + desc_true_1;
				itemDesc += "\n" + batName.trim() + ".sh  " + type + "_" + index + "_false" + "    [���� " + index
						+ "]  ����:" + desc_false;
				itemDesc += "\n" + batName.trim() + ".bat  " + type + "_" + index + "_false_order" + "    [���� " + index
						+ "]  ����:" + desc_false_1;
			}

			return itemDesc;
		}

	}

	// html_1
	/*
	 * 1.��ȡ��ǰ�� html�ļ� Ȼ������е� html�ļ��� <script> </script> ���·ŵ� </body> ���� <script>
	 * </script> </body>
	 */

	class HTML_Rule_1 extends Basic_Rule {

		HTML_Rule_1() {
			super("html", 1, 1);
		}

		String applyOperationRule(String origin) {
			StringBuilder sb = new StringBuilder();
			if (origin.contains("<script>") && origin.contains("</script>") && origin.contains("</body>")
					&& origin.indexOf("</body>") > origin.indexOf("<script>") && // <script> </body> // script ����С��
																					// </body>������
					origin.indexOf("</script>") == origin.lastIndexOf("</script>")) { // ֻ����һ�� </script>
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
			return " ��ȡ��ǰ�� html�ļ�  Ȼ������е� html�ļ��� <script> </script>  ���·ŵ� </body> ����";
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

		String simpleDesc() {
			return null;
		}

		String ruleTip(String type, int index, String batName, OS_TYPE curType) {
			String itemDesc = "";
			if (curType == OS_TYPE.Windows) {
				itemDesc = batName.trim() + ".bat  " + type + "_" + index + "    [���� " + index + "]  ����:"
						+ simpleDesc();
			} else {
				itemDesc = batName.trim() + ".sh " + type + "_" + index + "    [���� " + index + "]  ����:" + simpleDesc();
			}

			return itemDesc;
		}

		boolean tryReName(File curFile, String newName) {
			String newFilePath = curFile.getParent() + File.separator + newName;
			String oldName = curFile.getName();
			File newFile = new File(newFilePath);
			if (newFile.exists() && newFilePath.equals(curFile.getAbsolutePath())) {

//           newFilePath = curFile.getParent() + File.separator +"�ظ�_"+newName;
//           newFile = new File(newFilePath);
				System.out.println("��ǰĿ¼�Ѵ�������������ļ�  �ļ�����:" + curFile.getName());
				return false; // �Ѿ����ڵ��ļ������� ֱ�ӷ���

			}
			boolean flag = curFile.renameTo(newFile);
			if (flag) {
				System.out.println(oldName + " תΪ " + newFilePath + " �ɹ���");
				curFixedFileList.add(curFile);
			} else {
				System.out.println(oldName + " תΪ " + newFilePath + " ʧ�ܣ�");
			}
			return flag;
		}
	}

	abstract class Rule {
		// operation_type �������� 1--��ȡ�ļ������ַ��� �����޸� 2--���ļ����ļ�����(�ֽ�)--�����޸� 3.��ȫ�����ļ����е����ԵĲ���
		// ���Խ����޸�(�ļ�����)
		// 4.�Ե�ǰ���ļ�(������Ŀ¼ ���ļ� --��������Ŀ¼ ���ļ�) // 5. ��shell �л�ȡ����·�� ȥ��ĳһ���ļ����в���
		String firstInputIndexStr;
		int operation_type;
		String file_type; // * ��ʶ���е��ļ����� �Լ���ǰ���������ļ� ���� �������ļ���������
		String identify;
		int rule_index; // (type,index) ������������Ψһ��
		ArrayList<String> curFilterFileTypeList; // ��ǰ���ļ��������� �����ļ��������� ����� ���ָ�ʽ jpeg png תΪ jpg ʱ ʹ�õ�
		ArrayList<File> curFixedFileList; // ��ǰ�޸Ĳ����ɹ��ļ���

		abstract String applyStringOperationRule1(String origin);

		abstract File applyFileByteOperationRule2(File originFile);

		abstract ArrayList<File> applyFileListRule3(ArrayList<File> subFileList,
				HashMap<String, ArrayList<File>> fileTypeMap);

		abstract ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
				HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
				ArrayList<File> curRealFileList);

		abstract ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList,
				ArrayList<File> allSubRealFileList);

		abstract boolean initParams4InputParam(String inputParam); // ��ʼ��Rule�Ĳ��� ����������ַ���

		abstract boolean initParamsWithInputList(ArrayList<String> inputParamList);

		abstract String ruleTip(String type, int index, String batName, OS_TYPE curType); // ʹ��˵���б� ������� ��ô�Ͳ�ʹ��Ĭ�ϵ�˵�� ,
																							// Ĭ�Ͼ�һ�����

		abstract String simpleDesc(); // ʹ�õļ����� ���ĵĸ� rule��ʹ����� Ĭ�ϻ��� ruleTip ������

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

	public static String ReadFileContent(File mFilePath) {

		if (mFilePath != null && mFilePath.exists()) {
			// System.out.println("���� ��ǰ�ļ� "+ mFilePath.getAbsolutePath());
		} else {
			System.out.println("������ ��ǰ�ļ� " + mFilePath.getAbsolutePath());

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
//                    System.out.println("��"+index+"�ж�ȡ�����ַ���:"+oldOneLine);
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

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
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
				// �ر���
				generalBufferedInputStream.close();
				encryptBufferedOutputStream.close();
				return;

			}

			// System.out.println("ԭʼ�ļ��ֽڴ�С: " + generalBufferedInputStream.available());
			while (general_offset < BYTE_CONTENT_LENGTH_Rule7) { // ��ȡԭʼ�ļ���ͷ BYTE_CONTENT_LENGTH ���ֽ������м���
				general_position = generalBufferedInputStream.read(TEMP_Rule7, general_offset,
						TEMP_Rule7.length - general_offset);
				if (general_position == -1) {
					break;
				}
				general_offset += general_position;
				// byteTo16(TEMP, general_position); // ���Բ鿴 ָ�� ǰ general_position ����
				// TEMP�����е��ֽ����� ̫�� ע�͵�
			}

			// �Զ�ȡ����TEMP�ֽ����� BYTE_CONTENT_LENGTH ���ֽڽ��� ECBģʽ���� ���Ĵ�С�����Ĵ�Сһ��

			byte[] encrypt_bytes = encrypt(TEMP_Rule7);

			System.out.println("����ԭʼ�ļ�:" + generalFile.getName() + "  ����ǰ���Ĵ�С:" + TEMP_Rule7.length + "   ���ܺ����Ĵ�С:"
					+ encrypt_bytes.length);

			// ���ܺ������ ��� encryptFile�ļ���ͷ�ײ�
			encryptBufferedOutputStream.write(encrypt_bytes, 0, encrypt_bytes.length);
			encryptBufferedOutputStream.flush();
			// �������� general�ļ� ��ȡ BYTE_CONTENT_LENGTH �ֽ���֮��������ֽ�д�뵽 ����File(Head�Ѿ�����)�ļ���ȥ
			while ((general_position = generalBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
				encryptBufferedOutputStream.write(TEMP_Rule7, 0, general_position);
				encryptBufferedOutputStream.flush();
			}
			// �ر���
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
		byte[] arrB = new byte[8]; // ��ĬΪ0
		for (int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
			arrB[i] = arrBTmp[i];
		}
		// �����ܳ�
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	// �����ֽ�����
	public static byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	// �ܽ��ֽ�����
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
						if ("#".equals(type)) { // ��� ������ * ��ô�Ͱ� ���е� ��Ŀ¼�ļ������б���
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

	// ��ȡ�����ļ� �Լ��ܲ��ֽ��н��� Ȼ�����ɽ���֮����ļ� decryptFile
	public static void createDecryFile(File encryptFile, File decryptFile) {

		FileOutputStream decryptileOutputStream = null;
		BufferedOutputStream decryptBufferedOutputStream = null;

		FileInputStream encryptileInputStream = null;
		BufferedInputStream encryptBufferedInputStream = null;

		try {
			if (!decryptFile.exists()) {
				decryptFile.createNewFile();
			}
			encryptileInputStream = new FileInputStream(encryptFile);
			encryptBufferedInputStream = new BufferedInputStream(encryptileInputStream);

			decryptileOutputStream = new FileOutputStream(decryptFile);
			decryptBufferedOutputStream = new BufferedOutputStream(decryptileOutputStream);

			int encrypt_offset = 0;
			int encrypt_position = 0;
			while (encrypt_offset < BYTE_CONTENT_LENGTH_Rule7) { // ��ȡ�������ļ��� �����ֽڲ��� ��СΪ BYTE_CONTENT_LENGTH
				encrypt_position = encryptBufferedInputStream.read(TEMP_Rule7, encrypt_offset,
						TEMP_Rule7.length - encrypt_offset);

				if (encrypt_position == -1) {
					break;
				}
				encrypt_offset += encrypt_position;
				// byteTo16(TEMP, general_position); // ���Բ鿴 ָ�� ǰ general_position ����
				// TEMP�����е��ֽ����� ̫�� ע�͵�
			}

			byte[] decrypt_bytes = decrypt(TEMP_Rule7); // �Լ����ļ��ļ����ֽڽ��н���
			System.out.println("�����ļ�:" + decryptFile.getName() + "  ���ļ����ֽڴ�С:" + TEMP_Rule7.length + "   ��������֮������Ĵ�С:"
					+ decrypt_bytes.length);

			decryptBufferedOutputStream.write(decrypt_bytes);
			decryptBufferedOutputStream.flush();

			// ��ȡ encryptFile�����ļ����������ֽ� BYTE_CONTENT_LENGTH �ֽ���֮��������ֽ�д�뵽
			// ����File(Head�Ѿ�����)�ļ���ȥ
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
		System.out.println("��Type�ļ����� ���� Index ����Ĵ���  identy=�� Type_Index ���� �ļ���׺_��ǰ�����߼�������\n");
		System.out.println("��ǰ��ʵ�ֵ��滻�߼�����:\n");

		int count = 1;
		System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T" + "ʹ�÷����б� Begin" + "�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T" + "\n");
		for (int i = 0; i < realTypeRuleList.size(); i++) {
			Rule itemRule = realTypeRuleList.get(i);
			String type = itemRule.file_type;
			int index = itemRule.rule_index;
			String desc = itemRule.ruleTip(type, index, G2_Bat_Name, curOS_TYPE);

			/*
			 * String itemDesc = ""; if(curOS_TYPE == OS_TYPE.Windows){ itemDesc =
			 * "zrule_apply_G2.bat  "+type+"_"+index + "    [���� "+count+"]  ����:"+desc;
			 * }else{ itemDesc = "zrule_apply_G2 "+type+"_"+index +
			 * "    [���� "+count+"]  ����:"+desc; }
			 */
			System.out.println(desc + "\n");
			count++;
		}
		System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T" + "ʹ�÷����б� End " + "�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T" + "\n");

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
			if (!isNumeric(index)) { // �ڶ����������� ���� ��ô �����ʽ����
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

		G2_ApplyRuleFor_TypeFile mG2_Object = new G2_ApplyRuleFor_TypeFile();
		mG2_Object.InitRule();

		File mCurDirFile = new File(curDirPath);
		curDirFile = new File(curDirPath);

		if (mKeyWordName.size() == 0) {
			showTip();
			return;
		}

		if (!checkInputParamsOK()) {
			System.out.println("��ǰ�û�����ĸ�ʽ����   input=������_������  ����    html_1   html_2    html_3  ");
			return;
		}

		if (curDirFile == null || !mCurDirFile.exists() || !mCurDirFile.isDirectory()) {
			System.out.println("��ǰִ���滻�߼����ļ�·��:" + curDirPath + "  ������! ");
			return;
		}

		// ͨ�� shell��������������в���
		// Rule_Identify_TypeIndexList.add("html_1"); // 1.��Ӵ���������ļ� ����_�����͵Ĵ����߼�����
		// ������1��ʼ

		for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) { // �����ļ����� ȥ�ҵ��ļ�
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
				System.out.println("�޷�ƥ�䵽 ��Ӧ�� index=" + ruleIndex + "  ��Ӧ�Ĺ��� Rule !   ������Ҫ������ӡ�");
				continue; // ������һ��ѭ��
			}
			if (curApplayRule == null && CurSelectedRule != null) {
				return;
			}
			if (curApplayRule.curFilterFileTypeList.size() == 0) {
				curApplayRule.curFilterFileTypeList.add(curType);
			}

			ArrayList<File> typeFileList = new ArrayList<File>();

			if (curApplayRule.operation_type == 4) { // ���� ������ 4 �Ĳ��� ֻ��ȡ��ǰ shell �µ��ļ�
				typeFileList.addAll(Arrays.asList(mCurDirFile.listFiles()));
				System.out.println("operation_type == 4 ��Ŀ¼��С: " + typeFileList.size());
			} else {
				typeFileList = getAllSubFile(mCurDirFile, null, curApplayRule.curFilterFileTypeList);
			}

			if (typeFileList.size() == 0) {
				System.out.println("δ�������������б�ƥ����ļ�:  " + Rule_Identify_TypeIndexList.get(i));
				continue;
			}
			initFileTypeMap(typeFileList);

			if (curApplayRule.operation_type == 4) { // ֻ�� ��ǰ�� �� �ļ�(Ŀ¼ �ļ�)����
				// �Ե�ǰ�ļ���������
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
					System.out.println("Ӧ�ù���:  " + applyRuleString + " �ɹ�!");
				} else {
					System.out.println("Ӧ�ù���:  " + applyRuleString + " ʧ��!");
				}

			} else if (curApplayRule.operation_type == 3) { // �������ļ����е� ͳһ����� ����

				ArrayList<File> resultFileList = curApplayRule.applyFileListRule3(typeFileList, CurDirFileTypeMap);
				if (resultFileList != typeFileList) {

					System.out.println("Ӧ�ù���:  " + applyRuleString + " �ɹ�!");
				} else {
					System.out.println("Ӧ�ù���:  " + applyRuleString + " ʧ��!");
				}

			} else if (curApplayRule.operation_type == 5) { // �������ļ��� �������ļ� ���ļ� ���� ���ļ��� ���ļ���

				ArrayList<File> curAllDirFile = getAllSubDirFile(curDirFile); // ��ȡ���е� �ļ����б� ���� ���� ���ļ���
				ArrayList<File> curAllRealFile = getAllSubFile(curDirFile); // ��ȡ���е� �ļ� �б� ���� ���� ���ļ�
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
					// 2.applyOperationRule ��Ӵ������

					String resultStr = OriApplyOperationRule(curType, curApplyRule, fileCOntent).trim();

					int currentOperationType = 1; // Ĭ�ϲ��������� ��ȡ�ַ��������� ���д���

					String identy = curType.trim() + curApplyRule.trim();
//                Rule applayRule2Identify = getRuleByIdentify(identy);

					Rule applayRule4Index = getRuleByIndex(ruleIndex);
//                �����Ӧ��ͬ�� index�� Rule #_2    ������    MP3_2 �����  ��ô����Ҫ�ѵ�ǰ�� ���е�*���ļ� ����Ϊ mp3���ļ�
//                if("#".equals(applayRule2Identify.file_type) && !curType.equals(applayRule2Identify.file_type)){
//
//                }

					if (applayRule4Index != null) {
						currentOperationType = applayRule4Index.operation_type;
					} else {
						System.out.println("�޷�ƥ�䵽 ��Ӧ�� identy=" + identy + "  ��Ӧ�Ĺ��� Rule !   ������Ҫ������ӡ�");
						return;
					}

					if (currentOperationType == 1) { // ���ַ��������߼����������

						if (!fileCOntent.equals(resultStr)) {
							writeContentToFile(itemFile, resultStr);
							System.out.println("itemFile[" + j + "] ���Ϲ���(String-Content) Ӧ��Rule�ɹ� " + applyRuleString
									+ "  = " + itemFile.getAbsolutePath());
						} else {
							System.out.println(
									"itemFile[" + j + "] �����Ϲ���(String-Content) = " + itemFile.getAbsolutePath());
						}

					} else if (currentOperationType == 2) {

						File resultFile = applayRule4Index.applyFileByteOperationRule2(itemFile);

						if (resultFile != itemFile) {
							System.out.println("itemFile[" + j + "] ���Ϲ���(File) Ӧ��Rule�ɹ� " + applyRuleString + "  = "
									+ itemFile.getAbsolutePath());
						} else {
							System.out.println("itemFile[" + j + "] �����Ϲ���(File) = " + itemFile.getAbsolutePath());
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
				String type = ""; // unknow û�к�׺�����ļ�
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
			System.out.println("û�в�ѯ�� identy =" + identy + "��Ӧ�Ĵ������");
			return mOriContent;
		}
		return applayRule.applyStringOperationRule1(mOriContent);
	}

	static ArrayList<Rule> realTypeRuleList = new ArrayList<Rule>(); // ����ļ���

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

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");// �������ڸ�ʽ
		String date = df.format(new Date());
		return date;
	}

	static String getTimeStamp() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// �������ڸ�ʽ
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
		// �����������������
		try {
			input = new FileInputStream(origin);
			output = new FileOutputStream(target);
			// ��ȡ�ļ�����
			try {
				lengthSize = input.available();
				// ������������
				byte[] buffer = new byte[lengthSize];
				// ���ļ��е�����д�뻺������
				input.read(buffer);
				// �����������е�����������ļ�
				output.write(buffer);

			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (Exception e) {

		} finally {
			if (input != null && output != null) {
				try {
					input.close(); // �ر���
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String execCMD_Windows(String command) {
//        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�TBegin ExE ");
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
			// ɱ������
//            System.out.println("exitValue ="+ exitValue);
			sb.append("\nexitValue = " + exitValue + "\nisAlive = " + isAlive + "\nerrorStream = " + errorStream
					+ "\nerrorSteamCode = " + errorSteamCode + "\nwaitFor = " + waitFor);
//            process.destroy();

		} catch (Exception e) {
			System.out.println("execCMD �����쳣! ");
			return e.toString();
		}

//        System.out.println("sb.toString() = "+ sb.toString());
//        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�TEnd ExE ");
		return sb.toString();
	}

	/**
	 * ִ�� mac(unix) �ű�����~
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

		// ����
		OutputStream os = proc.getOutputStream();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

		try {
			bw.write(command);

			bw.flush();
			bw.close();

			/** ����֣��ѿ���̨�������ӡһ��֮��Ȼ��������ֹ��~ */
//            readConsole(proc);

			/** waitFor() ���������� java �����Ƿ�ȴ� Terminal ִ�нű����~ */
			proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int retCode = proc.exitValue();
		if (retCode != 0) {
			System.out.println("unix script retCode = " + retCode);

//            System.out.println(readConsole(proc));
			System.out.println("UnixScriptUil.execute ������!!");
		}
		return retCode + "";
	}

	public static String execCMD(String command) {

		String result = "";
		if (curOS_TYPE == OS_TYPE.Windows) {
			return execCMD_Windows(command);
		} else if (curOS_TYPE == OS_TYPE.MacOS) {

			return execCMD_Mac(command);
		} else {

			execCMD_Mac(command);
		}
		return result;
	}
}