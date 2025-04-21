



import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import cn.hutool.core.map.MapUtil;

import java.math.BigInteger;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.text.DecimalFormat;

// 对于  文件类型_操作Index  执行对应的操作逻辑
public class K4_AOSP_Rule {

    // 类型_索引 ，对当前类型的文件执行索引执行的操作 html1---对html类型的子文件执行 索引为1 的逻辑操作 String
    // apply(String)
    static ArrayList<String> Rule_Identify_TypeIndexList = new ArrayList<String>();

    static String Cur_Batch_End = ".bat";  // 动态计算当前系统下 bat sh 的 后缀
    static String Cur_Bat_Name = "zaosp_rule_K4";
    static String Cur_Notepad_Name = "notepad++";
    
    static String Cur_Bat_Only_Name = "zaosp_rule_K4";

    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop"
            + File.separator + "zbin";
    static String K4_File_Path = zbinPath + File.separator + "K4";
    static String Win_Lin_Mac_ZbinPath = "";

    static File K4_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator
            + "Desktop" + File.separator + "zbin" + File.separator + "K4.properties");
    static InputStream K4_Properties_InputStream;
    static OutputStream K4_Properties_OutputStream;
    static Properties K4_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();

    static K4_AOSP_Rule mK4_Object;

    static {
        try {
            if (!K4_Properties_File.exists()) {
                K4_Properties_File.createNewFile();
            }
            K4_Properties_InputStream = new BufferedInputStream(
                    new FileInputStream(K4_Properties_File.getAbsolutePath()));
            K4_Properties.load(K4_Properties_InputStream);
            Iterator<String> it = K4_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " +
                // K4_Properties.getProperty(key));
                propKey2ValueList.put(key, K4_Properties.getProperty(key));
            }
            K4_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void setProperity() {
        try {
            K4_Properties_OutputStream = new BufferedOutputStream(
                    new FileOutputStream(K4_Properties_File.getAbsolutePath()));
            K4_Properties.store(K4_Properties_OutputStream, "");
            K4_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    enum OS_TYPE {
        Windows, Linux, MacOS
    }

    // JDK 的路径
    static String JDK_BIN_PATH = "";

    static File K4_Temp_Text_File = new File(
            System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"
                    + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + "_Temp_Text.txt");

    static File K4_Temp_Bat_File = new File(
            System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"
                    + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + "_Temp_Bat.bat");
    
    static File K4_Temp_Dir_File = new File(
            System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"
                    + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) +"_Temp_Dir");



    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;
    static String curOS_ExeTYPE = "";
    static ArrayList<String> mKeyWordName = new ArrayList<String>();

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
            Cur_Notepad_Name = "notepad++";
            initJDKPath_Windows(curLibraryPath);
            Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "win_zbin";

        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
            curOS_ExeTYPE = "";
            Cur_Batch_End = ".sh";
            initJDKPath_Linux_MacOS(curLibraryPath);
            Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "lin_zbin";
            Cur_Notepad_Name = "gedit";

        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
            curOS_ExeTYPE = "";
            Cur_Batch_End = ".sh";
            initJDKPath_Linux_MacOS(curLibraryPath);
            Win_Lin_Mac_ZbinPath = zbinPath + File.separator + "mac_zbin";
            Cur_Notepad_Name = "notepad";
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

    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";
    
    
    static String Cur_Dir_Path = ""; // 当前 SHELL 所在目录 默认是main中的第一个 arg[0] 就是shell路径
    static File  Cur_Dir_File;
    
    static String  AOSP_VendorSide_ABS_PATH;   // AOSP Vendor 路径
    static String  AOSP_MsiSide_ABS_PATH;       // AOSP Msi 路径
    

    
    // 当前所有的 GIT 路径的集合
    static ArrayList<String> mAllGitPathList = new ArrayList<String>();
    static ArrayList<String> mWifiGitPathList = new ArrayList<String>();
    static ArrayList<String> mGpsGitPathList = new ArrayList<String>();
    static ArrayList<String> mBlueToothGitPathList = new ArrayList<String>();
    static ArrayList<String> mNfcGitPathList = new ArrayList<String>();
 
    //  mWifiGitPathList
    static {

    	mWifiGitPathList.add("/frameworks/opt/net/wifi");
    	mWifiGitPathList.add("/packages/apps/Settings");
    	mWifiGitPathList.add("/packages/modules/Wifi");
    	mWifiGitPathList.add("/external/wpa_supplicant_8");
    	mWifiGitPathList.add("/packages/services/Iwlan");
    	mWifiGitPathList.add("/prebuilts/module_sdk/Wifi");
    	mWifiGitPathList.add("/system/connectivity/wificond");
    	mWifiGitPathList.add("/vendor/mediatek/proprietary/operator/frameworks/wifi");
    	mWifiGitPathList.add("/vendor/mediatek/proprietary/operator/packages/services/WifiOffload");
    	mWifiGitPathList.add("/vendor/mediatek/proprietary/packages/services/WifiOffload");
    	mWifiGitPathList.add("/vendor/mediatek/proprietary/packages/services/WifiOffload_legacy_hidl");
    	mWifiGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/firmware/wlan");
    	mWifiGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/wlan");
    	mWifiGitPathList.add("/vendor/mediatek/kernel_modules/connectivity/wlan/adaptor");
    	mWifiGitPathList.add("/vendor/mediatek/kernel_modules/connectivity/wlan/core/gen4m");
    	mWifiGitPathList.add("/hardware/qcom/wlan");
    	mWifiGitPathList.add("/device/qcom/wlan");
    	mWifiGitPathList.add("/vendor/qcom/nonhlos/WLAN.MSL.3.0.2/wlan_proc");
    	mWifiGitPathList.add("/vendor/qcom/opensource/wlan/qca-wifi-host-cmn");
    	mWifiGitPathList.add("/vendor/qcom/opensource/wlan/qcacld-3.0");
    	mWifiGitPathList.add("/vendor/qcom/proprietary/wlan/cnss-daemon");
    	mAllGitPathList.addAll(mWifiGitPathList);
    }
    
    
    //  mGpsGitPathList
    static {

    	mGpsGitPathList.add("/vendor/mediatek/proprietary/packages/apps/LocationEM2");
    	mGpsGitPathList.add("/vendor/mediatek/kernel_modules/connectivity/gps");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/external/gps_lib");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/external/gps_tlib");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/frameworks/opt/agps_lib");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/firmware/gps_fw_lib");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/firmware/gps_fw_tlib");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/gps_sys");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/gnss/mnld");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/gnss/gnss_hal");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/hardware/lbs/gnss_service");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/hardware/lbs/mtk_gnss_service");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/packages/apps/YGPS");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/packages/apps/agpsInterface");
    	mGpsGitPathList.add("/vendor/mediatek/proprietary/packages/apps/GnssVisibilityControl");
    	mGpsGitPathList.add("/hardware/qcom/gps");
    	mGpsGitPathList.add("/vendor/qcom/proprietary/commonsys/gps");
    	mGpsGitPathList.add("/vendor/qcom/proprietary/commonsys/gps-release");
    	mGpsGitPathList.add("/vendor/qcom/proprietary/gps");
    	mGpsGitPathList.add("/vendor/qcom/proprietary/gps-release");
    	mAllGitPathList.addAll(mGpsGitPathList);
    }
    
    
    
    //  mBlueToothGitPathList
    static {

    	mBlueToothGitPathList.add("/packages/modules/Bluetooth");
    	mBlueToothGitPathList.add("/prebuilts/module_sdk/Bluetooth");
    	mBlueToothGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/bluetooth/bluetooth_audio");
    	mBlueToothGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/bluetooth/driver/mt66xx");
    	mBlueToothGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/bluetooth/driver/mt76xx");
    	mBlueToothGitPathList.add("/vendor/mediatek/proprietary/hardware/connectivity/bluetooth/service");
    	mBlueToothGitPathList.add("/vendor/mediatek/proprietary/packages/modules/Bluetooth");
    	mBlueToothGitPathList.add("/vendor/qcom/proprietary/bluetooth");
    	mBlueToothGitPathList.add("/vendor/qcom/proprietary/commonsys-intf/bluetooth");
    	mBlueToothGitPathList.add("/vendor/qcom/proprietary/commonsys/bt/bt_adv_audio");
    	mBlueToothGitPathList.add("/vendor/qcom/proprietary/commonsys/bt/btdsda");
    	mBlueToothGitPathList.add("/vendor/qcom/proprietary/commonsys/bt/dun");
    	mBlueToothGitPathList.add("/vendor/qcom/nonhlos/BTFM.CHE.3.2.1/btfm_proc");
    	mBlueToothGitPathList.add("/vendor/qcom/nonhlos/BTFW.MOSELLE.1.2.0/btfw_proc");
    	mBlueToothGitPathList.add("/vendor/qcom/opensource/bt-kernel");
    	mBlueToothGitPathList.add("/vendor/qcom/opensource/bt-external");
    	mBlueToothGitPathList.add("/vendor/qcom/opensource/bt-devicetree");
    	mBlueToothGitPathList.add("/vendor/qcom/opensource/commonsys/system/bt");
    	mAllGitPathList.addAll(mBlueToothGitPathList);
    }
    
    
    //  mNfcGitPathList
    static {
    	mNfcGitPathList.add("/hardware/nxp/nfc");
    	mNfcGitPathList.add("/packages/apps/Nfc");
    	mNfcGitPathList.add("/system/nfc");
    	mNfcGitPathList.add("/vendor/mediatek/proprietary/frameworks/opt/nfc");
    	mNfcGitPathList.add("/vendor/nxp/opensource/nfc");
    	mNfcGitPathList.add("/vendor/qcom/opensource/nfc-devicetree");
    	mNfcGitPathList.add("/vendor/qcom/proprietary/nqnfc-firmwar");
    	mAllGitPathList.addAll(mNfcGitPathList);

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

    	// 对 各个 模块的 文件的数据 进行遍历 输出 模块大概分布
        realTypeRuleList.add(new Show_CurAOSP_AllStoredGit_FileInfo_Rule_1());

        
        // 对当前目录下 的 vhw.xml 文件打印出当前的 device  和 当前的  radioid列表
        // 如果是AOSP  那么 进入  device/moto 搜索 到 vhw.xml的文件
        // 如果不是AOSP  那么 搜索当前 所有的  vhw.xml的文件进行解析
        
        realTypeRuleList.add(new Show_AOSP_VHW_XML_Device_RadioIds_INfo_Rule_2());

        
    }
    
    
    class Show_AOSP_VHW_XML_Device_RadioIds_INfo_Rule_2 extends Basic_Rule {
    	
    	
    	ArrayList<File> allVhwFileList ;
    	
    	// 当前 device  匹配的 radio_auto_map 字符串
      	HashMap<String,ArrayList<String>> device_radios_map ;
      	
    	// 当前 device  匹配的 radio_auto_map 字符串
     	HashMap<String,String> device_radio_auto_map ;
     	
     	// 当前 device 所在的 文件
    	HashMap<File,ArrayList<String>> file_devicelist_map; 
    	
    	Show_AOSP_VHW_XML_Device_RadioIds_INfo_Rule_2() {
            super("#", 2, 4);
            allVhwFileList = new ArrayList<File>();
            device_radios_map =new HashMap<String,ArrayList<String>>();
            device_radio_auto_map = new 	HashMap<String,String> ();
            file_devicelist_map = new	HashMap<File,ArrayList<String>> ();
            
        }
    	
    	
    	void     showAllVhwDeviceRadioInfo() {
    		
    		ArrayList<String> logList = new 	ArrayList<String> ();
    		
    		ArrayList<String> vhwFileInfoList = new 	ArrayList<String> ();
    		
    		for (int i = 0; i < allVhwFileList.size(); i++) {
				File vhwXmlFile = allVhwFileList.get(i);
				String vhwXmlFilePath = vhwXmlFile.getAbsolutePath();
				
				
//				System.out.println("════════════【"+allVhwFileList.size()+"_"+(i+1)+"】vhw.xml  ["+vhwXmlFilePath+"]════════════");
				logList.add("════════════【"+allVhwFileList.size()+"_"+(i+1)+"】vhw.xml  ["+vhwXmlFilePath+"]════════════");
				
				ArrayList<String> mMatchDeviceList = file_devicelist_map.get(vhwXmlFile);
				
				if(mMatchDeviceList == null || mMatchDeviceList.size() == 0) {
					continue;
				}
				
				StringBuilder sb = new StringBuilder();
				
				
				for (int j = 0; j < mMatchDeviceList.size(); j++) {
					String mDeviceStr = mMatchDeviceList.get(j);
					String mRadioAuto = device_radio_auto_map.get(mDeviceStr);
					ArrayList<String> radioidList = device_radios_map.get(mDeviceStr);
					logList.add("");
					logList.add("["+mDeviceStr+" " +mMatchDeviceList.size()+"_"+(j+1)+"] : " + mRadioAuto);

			
					sb.append((j+1)+"_"+mDeviceStr+" ");
					
					for (int k = 0; k < radioidList.size(); k++) {
						String radioId = radioidList.get(k);
						
						logList.add("["+mDeviceStr+" " +mMatchDeviceList.size()+"_"+(j+1)+"]__"+"["+radioidList.size()+"_"+(k+1)+"] "+radioId );

					}
					
				}
	
			
				
				vhwFileInfoList.add("vhw["+allVhwFileList.size()+"_"+(i+1)+"]\n"+Cur_Notepad_Name+" "+vhwXmlFilePath +"      【"+sb.toString()+"】");

				
				logList.add("");
				
			}
			logList.add("");
			logList.add("");

    		
			
			logList.addAll(vhwFileInfoList);
			
			
			for (int i = 0; i < logList.size(); i++) {
				System.out.println(logList.get(i));
				
			}
    		
    		
    	}

        @Override
        boolean allowEmptyDirFileList() {
            return true;
        }
        
        
        
        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
                                              HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
                                              ArrayList<File> curRealFileList) {
         
		 // 执行 打印当前保存的git路径下的所有文件的目录详细信息出来
    		
			System.out.println("【Rule"+rule_index+"】"+"curFileList.size()="+ curFileList.size()+"   subFileTypeMap.size()="+subFileTypeMap.size()+"  curDirList.size()="+curDirList.size()+"  curRealFileList.size()="+curRealFileList.size());

			
    		if(isInputPathAOSP) {
    			
    			System.out.println(" 当前执行路是AOSP根目录  CurPath="+Cur_Dir_Path +" 搜索 AOSP/device/skyline下 所有 vhw.xml 文件! ");

    			File targetSearchFile = new File(Cur_Dir_Path+File.separator+"device"+File.separator+"moto");
    			
    			
  		    	ArrayList<File> curDirAllSubFileList = getAllSubFile(targetSearchFile);
	    		System.out.println("curFile:"+targetSearchFile.getAbsolutePath()+"  AllSubFileList.size()="+ curDirAllSubFileList.size());
	    	for (int j = 0; j < curDirAllSubFileList.size(); j++) {
	    		File curSubFile = curDirAllSubFileList.get(j);
	    		System.out.println("curSubFile["+curDirAllSubFileList.size()+"_"+j+"] : "+curSubFile.getAbsolutePath());
	    		if(curSubFile.isFile()) {
		    		
		    	    if("vhw.xml".equals(curSubFile.getName().toLowerCase().trim())) {
    		        	
    		        	allVhwFileList.add(curSubFile);
    		        }
		    	}
			}
	    	
    			
    			
    		
    		} else {
    			System.out.println(" 当前执行路不是AOSP根目录  CurPath="+Cur_Dir_Path +" 搜索当前目录下所有 vhw.xml 文件! ");


    			

    		    for (int i = 0; i < curFileList.size(); i++) {
    		    	File curFile = curFileList.get(i);
    		    	if(curFile.isFile()) {
    		    		
    		    	    if("vhw.xml".equals(curFileList.get(i).getName().toLowerCase().trim())) {
        		        	
        		        	allVhwFileList.add(curFileList.get(i));
        		        }
    		    	} else {
    		    		
    		    		
    		    	ArrayList<File> curDirAllSubFileList = getAllSubFile(curFile);
    		    		System.out.println("curFile:"+curFile.getAbsolutePath()+"  AllSubFileList.size()="+ curDirAllSubFileList.size());
    		    	for (int j = 0; j < curDirAllSubFileList.size(); j++) {
    		    		File curSubFile = curDirAllSubFileList.get(j);
    		    		System.out.println("curSubFile["+curDirAllSubFileList.size()+"_"+j+"] : "+curSubFile.getAbsolutePath());
    		    		if(curSubFile.isFile()) {
        		    		
        		    	    if("vhw.xml".equals(curSubFile.getName().toLowerCase().trim())) {
            		        	
            		        	allVhwFileList.add(curSubFile);
            		        }
        		    	}
					}
    		    }
    		    
    		    }
    		    
    	
		
    		}
    	
    	    System.out.println("allVhwFileList.size() = "+ allVhwFileList.size());
		    
             if(allVhwFileList.size() == 0) {
	
    	 System.out.println("当前AOSP:"+isInputPathAOSP+  "PATH:"+Cur_Dir_Path+"   无法搜索到vhw.xml 文件,解析失败!" );
		    
              return null;
             }
    
		    for (int i = 0; i < allVhwFileList.size(); i++) {
				File vhwFile = allVhwFileList.get(i);
				System.out.println("VhwFile["+allVhwFileList.size()+"_"+i+"] : "+ vhwFile.getAbsolutePath());
			}
		    
			
		    for (int i = 0; i < allVhwFileList.size(); i++) {
					File vhwFile = allVhwFileList.get(i);
					operationVhwXmlFile(vhwFile);
				}
		    
		
        	
		    showAllVhwDeviceRadioInfo();
		    
		    System.out.println("AOSP根目录:"+ isInputPathAOSP);
        	return super.applySubFileListRule4(curFileList,subFileTypeMap , curDirList , curRealFileList);
        }
      
    
  
        String simpleDesc() {
            return "  读取AOSP/device目录下的 vhw.xml 或者本地 vhw.xml文件信息 打印出 device-radioid信息! ";
        }
        
        
        
        void operationVhwXmlFile(File mVhwXmlFile) {
        
       
         	
//        	ArrayList<String> allLineList = ReadFileContentAsList(mVhwXmlFile);
        	
        	String rawContentStr = readStringFromFile(mVhwXmlFile);
        	ArrayList<String> deviceList = new ArrayList<String> ();
        	
        	if(rawContentStr == null || "".equals(rawContentStr)) {
        		System.out.println("读取 文件:"+mVhwXmlFile.getAbsolutePath()+" 内容为空!");
        		return ; 
        	}
        	
        	String[] deviceXmlContentArr = rawContentStr.split("<device name=");
        	
        	if(deviceXmlContentArr == null || deviceXmlContentArr.length ==0) {
        		
        		System.out.println("文件:"+mVhwXmlFile.getAbsolutePath()+" 无法通过 【<device name=】 进行切割 请检查!");
	
        		return ;
        	}
        	
        	
        	for (int i = 0; i < deviceXmlContentArr.length; i++) {
        		
        		if(i == 0) {  // 第一个切割的  <device name=  Arr[0] 是 【<?xml】
        			continue;
        		}
        		String mDeviceStr = deviceXmlContentArr[i];
        		
        		String device = mDeviceStr.split(" ")[0].replace("\"", "");
        		
        		deviceList.add(device);
        		
        		System.out.println("device【"+device+"】");

        		
        		String radioAutoSearchStr = "<string name=\"radio/.auto\">";
        		String stringXmlEndSearchStr =  "</string>";
        		
        		
        		if(mDeviceStr.indexOf(radioAutoSearchStr)> 0 ) {
        			
        			String rawRadioAutoStr = mDeviceStr.substring(mDeviceStr.indexOf(radioAutoSearchStr)+radioAutoSearchStr.length());
        			String radioAutoStr = rawRadioAutoStr.split(stringXmlEndSearchStr)[0].trim();
        		
            		System.out.println("radioauto【"+radioAutoStr+"】");

            		device_radio_auto_map.put(device, radioAutoStr);
        			
        		}
        		
        		ArrayList<String> radioidList = new 	ArrayList<String> ();
        		
        		String radioRangeSearchStr = "<string-array name=\"radio/.range\">";
        		String stringArrXmlEndSearchStr =  "</string-array>";
   		        if(mDeviceStr.indexOf(radioRangeSearchStr)> 0 ) {
        			
        			String rawRadioRangeStr = mDeviceStr.substring(mDeviceStr.indexOf(radioRangeSearchStr)+radioRangeSearchStr.length());
        			String radioRangeStr = rawRadioRangeStr.split(stringArrXmlEndSearchStr)[0].trim();
        		
//            		System.out.println("radiorange【"+radioRangeStr+"】");
            		
            		String itemXmlEndSearchStr = "</item>";
            		
            		String[] radioidRawArr = radioRangeStr.split(itemXmlEndSearchStr);
            		
            		ArrayList<String> radioList = new ArrayList<String> ();
            		
            		for (int j = 0; j < radioidRawArr.length; j++) {
						String radioidRawStr = radioidRawArr[j];
//						System.out.println("radioidRawStr["+radioidRawArr.length+"_"+j+"] = "+ radioidRawStr);
					  
						String  radioidStr = radioidRawStr.replace(" ", "").replace("<item>", "").trim();
						System.out.println("radioidStr["+radioidRawArr.length+"_"+j+"] = "+ radioidStr);

						radioList.add(radioidStr);
						
						
            		}
            		if(radioList.size() > 0 ) {
            			
            		}
            		
            		device_radios_map.put(device, radioList);

        			
        		}
   		
        		
        		
        		
        		

			}
        	
        	
       
        	file_devicelist_map.put(mVhwXmlFile, deviceList);

        	
        }
    	
    }

    
    //  打印当前保存的git路径下的所有文件的目录详细信息出来 当前是AOSP根目录(Msi || Vendor )根目录
    /*
     *
     */

    class Show_CurAOSP_AllStoredGit_FileInfo_Rule_1 extends Basic_Rule {

     	
    	ArrayList<File> allGitFileList ;
    	
   
        
   
    	
    	
    	Show_CurAOSP_AllStoredGit_FileInfo_Rule_1() {
            super("#", 1, 4);
            allGitFileList = new 	ArrayList<File> ();
            
        }

        @Override
        boolean allowEmptyDirFileList() {
            return true;
        }
        
     

        
 
        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,
                                              HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList,
                                              ArrayList<File> curRealFileList) {
         
		 // 执行 打印当前保存的git路径下的所有文件的目录详细信息出来
    		
    		if(!isInputPathAOSP) {
    			
    			System.out.println("当前程序执行失败! 当前执行路不是AOSP根目录  CurPath="+Cur_Dir_Path);
    			return null;
    		}
    	
    		
    		
    		
    		for (int i = 0; i < mAllGitPathList.size(); i++) {
				
    		String mGitRootFilePath = 	mAllGitPathList.get(i);
    		File mGitRootFile = new File(Cur_Dir_Path+File.separator+mGitRootFilePath);
    		
    		
    		if(mGitRootFile.exists()) {
    			allGitFileList.add(mGitRootFile);
    		 }
    		
			}
    		
    		
    		for (int j = 0; j < allGitFileList.size(); j++) {
				System.out.println("Exist Git["+(j+1)+"_"+allGitFileList.size()+"]___ "+ allGitFileList.get(j).getAbsolutePath() );
			}
    		System.out.println("allGitFileList.size() = "+ allGitFileList.size());
    		
        	
        	return super.applySubFileListRule4(curFileList,subFileTypeMap , curDirList , curRealFileList);
        }
      
    
  
        String simpleDesc() {
            return " 读取当前所有已保存的AOSP Git目录列表中的 每一个Git目录里的内容打印 里面的文件详情[按文件大小排序]";
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

        
        @Override
        void   initDynamicValue() {
        	isInputPathAOSP =  isAOSPPath(Cur_Dir_Path);
        	
        }
        
        boolean isAOSPPath(String mShellpath) {
        	
                String shellpath = mShellpath ;
                File shellFile = new File(shellpath);
                File  aosp_settings_wifienabler_file = new File(shellFile.getAbsolutePath()+File.separator+"packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java");
                File   aosp_wpa_supplicant_c_file =    new File(shellFile.getAbsolutePath()+File.separator+"external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c");

                return  aosp_settings_wifienabler_file.exists() && aosp_wpa_supplicant_c_file.exists();
           
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
            } else   if (curType == OS_TYPE.Linux) {
                //  在 Linux 下  #_42 会被当成注释  无法 被识别  所以  必须 把 第一个 # 号改为 下划线
                String  simple_desc = simpleDesc();
                String fixed_simple_desc  = simple_desc.replace("#_","@_");
                itemDesc = batName.trim() + ".sh " + type + "_" + index + "    [索引 " + index + "]  描述:" + fixed_simple_desc;
                itemDesc  = itemDesc.replace("#_","@_");

            } else   if (curType == OS_TYPE.MacOS) {
                //  在Mac 下  # 能被识别
                itemDesc = batName.trim() + ".sh " + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
                itemDesc  = itemDesc.replace("#_","@_");
            } else{

                //  在 Linux 下  #_42 会被当成注释  无法 被识别  所以  必须 把 第一个 # 号改为 下划线
                String  simple_desc = simpleDesc();
                String fixed_simple_desc  = simple_desc.replace("#_","@_");
                itemDesc = batName.trim() + ".sh " + type + "_" + index + "    [索引 " + index + "]  描述:" + fixed_simple_desc;
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

		@Override
		boolean allowEmptyInputParams() {
			// TODO Auto-generated method stub
			return false;
		}
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

    	boolean isInputPathAOSP = false ; // 当前是否是 AOSP的路径
    	
        abstract boolean allowEmptyDirFileList(); // 是否允许当前的目录下的文件为空

        abstract boolean allowEmptyInputParams(); // 是否允许空参数 

        
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

        // 初始化 一些 需要 动态计算出来的值
        abstract void   initDynamicValue();
        
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



    public static ArrayList<String> ReadFileJavaContentAsList_Begin_End(File mFilePath  , String beginClearTag , String endClearTag ) {


        if (mFilePath != null && mFilePath.exists()) {
            // System.out.println("存在 当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件  ReadFileContentAsList  mFilePath = null "  );

            return null;
        }
        ArrayList<String> contentList = new ArrayList<String>();

        boolean isContainLine = true;
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null) {
                    continue;
                }

                if(oldOneLine.contains(beginClearTag) && isContainLine){
                    isContainLine = false;
                    continue;
                }

                // 如果当前 包含 EndTag  b 并且 当前的  f lag是   false 的 话 那么 就 说明 这个  区间结束了
                if(oldOneLine.contains(endClearTag) && !isContainLine){
                    isContainLine = true;
                    continue;

                }


                if(isContainLine){
                    contentList.add(oldOneLine);
                    index++;
                }

//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);

            }
            curBR.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int lineSize  = contentList.size();

        String lastLine = "";

        if(lineSize > 0){
            lastLine = contentList.get(lineSize-1).trim();
        }


        while("".equals(lastLine)){
            int end_index = contentList.size()-1;

            if(contentList.size() <= 0){
                break;
            }
            if(end_index >= 0  ){
                lastLine = contentList.get(end_index).trim();
                if(lastLine.trim().endsWith("}")){
                    break;
                }else{
                    contentList.remove(end_index);
                }
            }

        }

        return contentList;

    }


    public static ArrayList<String> ReadFileContentAsListWithClearTag(File mFilePath , String clearTag) {

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

                if(oldOneLine.contains(clearTag)){
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

    static void showTip() {
        System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
        System.out.println("当前已实现的替换逻辑如下:\n");

        int count = 1;
        System.out.println("═══════════════════" + "使用方法列表 Begin" + "═══════════════════" + "\n");
        for (int i = 0; i < realTypeRuleList.size(); i++) {
            Rule itemRule = realTypeRuleList.get(i);
            String type = itemRule.file_type;
            int index = itemRule.rule_index;
            String desc = itemRule.ruleTip(type, index, Cur_Bat_Only_Name, CUR_OS_TYPE);

            /*
             * String itemDesc = ""; if(curOS_TYPE == OS_TYPE.Windows){ itemDesc =
             * "zrule_apply_K4.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
             * }else{ itemDesc = "zrule_apply_K4 "+type+"_"+index +
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
            	
            	matchRule.initDynamicValue();
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
                    Cur_Dir_Path = args[i];
                } else {
                    mKeyWordName.add(args[i]);
                    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        mK4_Object = new K4_AOSP_Rule();
        mK4_Object.InitRule();

        File mCurDirFile = new File(Cur_Dir_Path);
        Cur_Dir_File = new File(Cur_Dir_Path);

        if (mKeyWordName.size() == 0) {
            showTip();
            return;
        }

        if (!checkInputParamsOK()) {
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }

        if (Cur_Dir_File == null || !mCurDirFile.exists() || !mCurDirFile.isDirectory()) {
            System.out.println("当前执行替换逻辑的文件路径:" + Cur_Dir_Path + "  不存在! ");
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
                    System.out.println("应用规则: curApplayRule.operation_type ="+curApplayRule.operation_type+" " + applyRuleString + " 成功!");
                } else {
                    System.out.println("应用规则: curApplayRule.operation_type ="+curApplayRule.operation_type+" " + applyRuleString + " 失败!");
                }

            } else if (curApplayRule.operation_type == 3) { // 对所有文件进行的 统一处理的 类型

                ArrayList<File> resultFileList = curApplayRule.applyFileListRule3(typeFileList, CurDirFileTypeMap);
                if (resultFileList != typeFileList) {

                    System.out.println("应用规则: curApplayRule.operation_type ="+curApplayRule.operation_type+" " + applyRuleString + " 成功!");
                } else {
                    System.out.println("应用规则: curApplayRule.operation_type ="+curApplayRule.operation_type+" " + applyRuleString + " 失败!");
                }

            } else if (curApplayRule.operation_type == 5) { // 对所有文件夹 所有子文件 孙文件 所有 子文件夹 孙文件夹

                ArrayList<File> curAllDirFile = getAllSubDirFile(Cur_Dir_File); // 获取所有的 文件夹列表 包含 孙子 子文件夹
                ArrayList<File> curAllRealFile = getAllSubFile(Cur_Dir_File); // 获取所有的 文件 列表 包含 孙子 子文件
                // FileChannel
//  zukgit operation_type == 5
                System.out.println(" curDirFile = " + Cur_Dir_File.toString());
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

    static String getTimeStamp_yyyyMMddHHmmss() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
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
        Graphics2D K4 = res.createGraphics();
        // transform(这里先平移、再旋转比较方便处理；绘图时会采用这些变化，绘图默认从画布的左上顶点开始绘画，源图片的左上顶点与画布左上顶点对齐，然后开始绘画，修改坐标原点后，绘画对应的画布起始点改变，起到平移的效果；然后旋转图片即可)

        K4.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);

        K4.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

//        //先旋转（以目标区域中心点为旋转中心点，源图片左上顶点对准目标区域中心点，然后旋转）
//        K4.translate(rect_des.width/2,rect_des.height/ 2);
//        K4.rotate(Math.toRadians(angel));
//        //再平移（原点恢复到源图的左上顶点处（现在的右上顶点处），否则只能画出1/4）
//        K4.translate(-src_width/2,-src_height/2);

        K4.drawImage(src, null, null);
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
        String H7_WorkPlace_TaskDir = zbinPath + File.separator + "K4_Monitor_Download" + File.separator
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

    static String lonK4yyyyMMdd_HHmmss(long millionMs) {

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


    static String bytesToIntString(byte[] src ) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;

        int byteIndex = 0 ;
        for (byte aSrc : src) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toString(aSrc & 0xFF).toUpperCase();
            int value = Integer.parseInt(hv);

            int padding_blank = 2;
            String blank_pandding_str = "  ";
            if( value >= 100) {
                padding_blank = 1;
                blank_pandding_str = " ";
            }
            if (hv.length() < 2) {
                builder.append(0);
            }


            if(byteIndex == src.length -1) {
                builder.append(blank_pandding_str+hv+"  ");
            }else {
                builder.append(blank_pandding_str+hv+"   "+","+" ");
            }

            byteIndex++;

        }

//        System.out.println(builder.toString());
        return builder.toString();
    }
    static String bytesToIntCharString(byte[] src,String rawstr) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;

        int rawStr_size = rawstr.length();

        int byteIndex = 0 ;
        for (byte aSrc : src) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toString(aSrc & 0xFF).toUpperCase();
            int value = Integer.parseInt(hv);

            int padding_blank = 2;
            String blank_pandding_str = "  ";
            if( value >= 100) {
                padding_blank = 1;
                blank_pandding_str = " ";
            }
            if ( value < 10) {
                padding_blank = 3;
                blank_pandding_str = "   ";
            }
            if (hv.length() < 2) {
                builder.append(0);
            }


            if(byteIndex == src.length -1) {
                builder.append(blank_pandding_str+hv+"_"+(byteIndex >= rawStr_size ?"?": rawstr.substring(byteIndex,byteIndex+1))+"");
            }else {
                builder.append(blank_pandding_str+hv+"_"+(byteIndex >= rawStr_size ?"?": rawstr.substring(byteIndex,byteIndex+1))+" "+","+" ");
            }

            byteIndex++;

        }

//        System.out.println(builder.toString());
        return builder.toString();
    }

    static String bytesToHexString_Padding(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;

        int byteIndex = 0 ;
        for (byte aSrc : src) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(aSrc & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            if(byteIndex == src.length -1) {
                builder.append("0x"+hv+" ");
            }else {
                builder.append("0x"+hv+"   "+","+" ");
            }

            byteIndex++;

        }

//        System.out.println(builder.toString());
        return builder.toString();
    }


    static String bytesToHexCharString(byte[] src,String rawstr) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        int rawStr_size = rawstr.length();

        int byteIndex = 0 ;
        for (byte aSrc : src) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(aSrc & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            if(byteIndex == src.length -1) {
                builder.append("0x"+hv+"_"+(byteIndex >= rawStr_size ?"?": rawstr.substring(byteIndex,byteIndex+1))+"");
            }else {
                builder.append("0x"+hv+"_"+(byteIndex >= rawStr_size ?"?": rawstr.substring(byteIndex,byteIndex+1))+" "+","+" ");
            }

            byteIndex++;

        }

//        System.out.println(builder.toString());
        return builder.toString();
    }

    static String getTimeStamp_yyyyMMdd_HHmmssSSS() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");// 设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static String getTimeStamp_yyyyMMdd_HHmmss() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
        String date = df.format(new Date());
        return date;
    }



    static boolean checkExceptionFlag(String codeBack1) {
        boolean flag = false;
        if (codeBack1.contains("(>)") || codeBack1.contains("(>>)") || codeBack1.contains("(>>>)") || codeBack1.contains("(<)")
                || codeBack1.contains("(=)") || codeBack1.contains("(|)") || codeBack1.contains("(<<)") || codeBack1.contains("(<<<)")
                || codeBack1.contains("(&)") || codeBack1.contains("(+)") || codeBack1.contains("(-)") || codeBack1.contains("(!=)") ||
                codeBack1.contains("(/)") || codeBack1.contains("(!)") || codeBack1.contains("(*)") || codeBack1.contains("(^)") ||
                codeBack1.contains("(%)") || codeBack1.contains("(:)") || codeBack1.contains("(*=)") || codeBack1.contains("(%=)") ||
                codeBack1.contains("(&&)") || codeBack1.contains("(&=)") || codeBack1.contains("()") || codeBack1.contains("(+=)") ||
                codeBack1.contains("(-=)") || codeBack1.contains("(->)") || codeBack1.contains("(/=)") || codeBack1.contains("(::)") ||
                codeBack1.contains("(<<=)") || codeBack1.contains("(==)") || codeBack1.contains("(>=)") || codeBack1.contains("(>>=)") ||
                codeBack1.contains("(>>>=)") || codeBack1.contains("(^=)") || codeBack1.contains("(||)")
        ) {

            flag = true;
        }

        return flag;

//                expected one of "!=" "%" "%=" "&" "&&" "&=" "(" ")" "*" "*=" "+" "+=" "," "-" "-=" "->"
//                "/" "/=" "::" "<" "<<=" "<=" "=" "==" ">" ">=" ">>=" ">>>=" "?" "^" "^="
//                "instanceof" "|" "|=" "||"

    }

    static String getRepeatString(String repeatSrc, int repeatCount) {
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }

    static String getUserName() {
        // user.home=C:\Users\zhuzj5
        String username = "";
        String home_dir = System.getProperties().getProperty("user.home") ;
        if(home_dir == null) {
            return null;
        }

        if(home_dir.contains(File.separator)) {
            username = home_dir.substring(home_dir.lastIndexOf(File.separator)+1);
        }

        return username;

    }

    static int getEmptyBeginCount(String oneLine) {
        int empty_count = 0;
        if(!oneLine.startsWith(" ")) {
            return empty_count;
        }




        int strLength = oneLine.length();

        for (int i = 0; i < strLength ;  i++) {
            String pre_str = oneLine.charAt(i)+"";
            if(pre_str.equals(" ")) {
                empty_count++;
            } else {
                break;
            }


        }

        return empty_count;

    }
    static int calculPythonOneEmptyStep(ArrayList<String> srtList) {
        int step_count = 0;
        HashSet<Integer> intSet = new HashSet<Integer>();

        boolean  Tip_Block_Begin = false;
        boolean  Tip_Block_End = false;

        for (int i = 0; i < srtList.size(); i++) {
            String oneLine = srtList.get(i);
            if("".equals(oneLine.trim())) {
                continue;
            }

            if(!Tip_Block_Begin && oneLine.trim().startsWith("'''") &&  oneLine.trim().indexOf("'''") == oneLine.trim().lastIndexOf("'''")) {
                Tip_Block_Begin = true;
                continue;
            }

            if(!Tip_Block_End &&  oneLine.trim().startsWith("'''") &&  oneLine.trim().indexOf("'''") == oneLine.trim().lastIndexOf("'''")) {
                Tip_Block_Begin = false;
                Tip_Block_End = false;
                continue;
            }

            if(Tip_Block_Begin && !Tip_Block_End) {
//				System.out.println("注释阶段 不判断 space! ");
                continue;
            }


            intSet.add(getEmptyBeginCount(oneLine));

        }

        if(intSet.size() == 0) {
            return step_count;
        }

        ArrayList<Integer> intArr  = new ArrayList<Integer>();
        intArr.addAll(intSet);



        intArr.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return o2-o1;
            }
        });



        for (int i = 0; i < intArr.size(); i++) {
            int space_item = intArr.get(i);
            if(space_item == 0 ) {
                continue;
            }

            if(step_count == 0) {
                step_count = space_item;
            }

            if(space_item < step_count ) {
                step_count = space_item;
            }


        }



        return step_count;


    }

    static String ArrToStr(ArrayList<String>  rawList ){
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < rawList.size(); i++) {
            sb.append(rawList.get(i)+"\n");
        }

        return sb.toString();

    }

    static ArrayList<String> getSubArrayWithBeginTag_EndTag(ArrayList<String>  rawList , String beginTag , String endTag){
        ArrayList<String> resultList = new      ArrayList<String>();

        boolean isBegin = false ;

        for (int i = 0; i < rawList.size() ; i++) {
            String oneLine = rawList.get(i);
            if(oneLine.contains(beginTag)){
                isBegin = true;
                resultList.add(oneLine);
                continue;
            }

            if(oneLine.contains(endTag) && isBegin){
                isBegin = false;
                resultList.add(oneLine);
                break;
            }

            if(isBegin){
                resultList.add(oneLine);
            }

        }

        return resultList;


    }





    static ArrayList<String> replaceInList(ArrayList<String>  rawList , String srcTag , String dstTag){
        ArrayList<String> resultList = new      ArrayList<String>();

        for (int i = 0; i < rawList.size() ; i++) {
            String oneLine = rawList.get(i);
            if(oneLine.contains(srcTag)){
                String newLine = oneLine.replace(srcTag,dstTag);
                resultList.add(newLine);
                continue;
            }
            resultList.add(oneLine);
        }

        return resultList;


    }
    static	boolean isContainInStrList(ArrayList<String> srtList, String matchStr) {

        for (int i = 0; i < srtList.size(); i++) {
            String str_item = srtList.get(i);
            if (str_item.contains(matchStr)) {
                return true;
            }
        }
        return false;

    }




    public static  BufferedImage resize(Image mImage, int w, int h) {
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


    public static	String  getContainLineInStrList(ArrayList<String> srtList, String matchStr) {

        for (int i = 0; i < srtList.size(); i++) {
            String str_item = srtList.get(i);
            if (str_item.contains(matchStr)) {
                return str_item;
            }
        }
        return null;

    }


}
