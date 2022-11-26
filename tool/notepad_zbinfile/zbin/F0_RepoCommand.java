import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class F0_RepoCommand {


    // zrepo  解析一个 html文件  并把解析的内容放入到 properity 中


	 // zrepo_command_F0  r.git mr r-qsm2021.xml milanf >  milanf.txt
	
    //【lima】  o.git  prodp-mtk6771  r-mt6771.xml  
    //  -u o.git  --manifest-branch=prodp-mtk6771   -m  r-mt6771.xml

    //【sofia】
    // q.git  mq     r-6125.xml
    //  -u q.git  --manifest-branch=mq   -m  r-6125.xml

	

	// MANIFEST URL	ssh://gerrit.mot.com:29418/home/repo/dev/platform/android/platform/manifest/s.git
	// MANIFEST BRANCH	ms
	// MANIFEST FILE	r-q8450.xml
	
	
    // 输入的参数列表
    static String input_productName = "";    // 产品名称
    static String input_xml_manifestFileName = "";  // r-q8450.xml  输入
    static String input_gitRepoName = "";    // .git 输入
    static String input_manifestBranchName = "";  // ms  manifest的分支
    static char input_VersionChar = 'q';    // 产品名称



    static String curDirPath = "";   // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径


    enum OS_TYPE{
        Windows,
        Linux,
        MacOS
    }
    static String Cur_Batch_End = ".bat";  // .sh 或者 .bat
    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;

    static String Zbin = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator ;
    
    
    static File F0_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F0.properties");
    static File F0_Repo_Init_File_Origin = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F0_repo_init.sh");
    
    static InputStream F0_Properties_InputStream;
    static OutputStream F0_Properties_OutputStream;
    static Properties F0_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();
    
    
    static Map<String, String> productKey2ValueList = new HashMap<String, String>();  // F0.prop中的 key 和 value 的 集合
    static ArrayList<Repo_Meta_Data> propRepoMetaList = new ArrayList<Repo_Meta_Data>();  // 一个repo的数据

    static {
        try {
            if (!F0_Properties_File.exists()) {
                F0_Properties_File.createNewFile();
            }
            F0_Properties_InputStream = new BufferedInputStream(new FileInputStream(F0_Properties_File.getAbsolutePath()));
            F0_Properties.load(F0_Properties_InputStream);
            Iterator<String> it = F0_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + F0_Properties.getProperty(key));
                propKey2ValueList.put(key, F0_Properties.getProperty(key));
            }
            F0_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Repo_Meta_Data {
        // 输入的参数列表



        //  String commonBuildingCommand = "";  // 普通的 创建项目的命令
        
        
        
        File repo_info_store_file ;  // 对应的 存储信息的文件 
        
        
 
        
        
        
        String BuildSummary_Content;  //  
        ArrayList<String> BuildSummaryList ;  //  
        String buildsummary_jobname ;
        String buildsummary_build_target ; // 产品名称

        String BuildInstruction_Content;  //  
        String BuildInstruction_MoveHtml_Content;  // 
        ArrayList<String> BuildInstructionList;  //  
        
        String BuildInstruction_VendorOnly_Content;  // 
        ArrayList<String> BuildInstruction_VendorOnly_List;  //  
        String  buildinstruction_vendoronly_repo_init;  // 原始
        String  buildinstruction_vendoronly_repo_fixed_init;  // 原始
        
        String  buildinstruction_vendoronly_compile_command;
        
        String BuildInstruction_MsiOnly_Content;  //  
        ArrayList<String> BuildInstruction_MsiOnly_List;  //  
        String  buildinstruction_msionly_repo_init;  // 原始
        String  buildinstruction_msionly_repo_init_git;  // 原始  在 msi_only 中显示的 git name
        String  buildinstruction_msionly_repo_fixed_init; 
        
        String  buildinstruction_msionly_compile_command;
        String  buildinstruction_msionly_repo_artifactory_url;    // userdebug_mt_r-qmsi-all_test-keys_continuous/1761  
        String  buildinstruction_msionly_repo_artifactory_url_branch;  //  mt
        
        String  buildinstruction_msionly_repo_artifactory_url_git;  //  mt中的 t.git 
        
        String  buildinstruction_msionly_repo_artifactory_url_manifestname_noend;  // r-qmsi-all
        String  buildinstruction_msionly_repo_artifactory_url_manifestfilename; // r-qmsi-all.xml
        
        
        
        String ManifestInfo_Content;  // 
        String ManifestInfo_MoveHtml_Content;  // 
        
        ArrayList<String> ManifestInfoList; 
        String manifestinfo_branch ;   // MANIFEST BRANCH	ms  // manifest的分支
        String manifestinfo_xmlfile ;     // MANIFEST FILE	r-q8450.xml   // .xml输入
        String manifestinfo_url ;     //  ssh://gerrit.mot.com:29418/home/repo/dev/platform/android/platform/manifest/s.git
        String manifestinfo_url_gitname ; // s.git // .git 输入

        String ModemRelease_Content;  //  
        String ModemRelease_MoveHtml_Content;  //  
        ArrayList<String> ModemReleaseList ;  // 
        
        
        
        Repo_Meta_Data() {
            BuildInstructionList = new ArrayList<String>();
            ManifestInfoList = new ArrayList<String>();
            BuildSummaryList =  new ArrayList<String>();
            BuildInstruction_MsiOnly_List =  new ArrayList<String>();
            BuildInstruction_VendorOnly_List = new ArrayList<String>();
            ModemReleaseList =  new ArrayList<String>();
        }

        
        
        boolean ManifestInfo_Operation() {
        	boolean is_ok  = false;
        	String  manifest_url    = SearchStrWith_Pre_End("MANIFEST URL","MANIFEST BRANCH",ManifestInfo_Content);
        	
 
        	String  manifest_branch  = SearchStrWith_Pre_End("MANIFEST BRANCH","MANIFEST FILE",ManifestInfo_Content);

        	String manifest_xmlfile  = SearchStrWith_Pre_End("MANIFEST FILE","MANIFEST GROUP",ManifestInfo_Content);
  
    		
        	if(manifest_branch == null ) {
        		System.out.println("当前无法读取到 manifest_branch="+ manifest_branch);
        		return false;
        	}
        	
        	if(manifest_xmlfile == null ) {
        		System.out.println("当前无法读取到 manifest_xmlfile="+ manifest_xmlfile);
        		return false;
        	}
        	
        	if(manifest_url == null ) {
        		System.out.println("当前无法读取到 manifest_url="+ manifest_url);
        		return false;
        	}
        	
        	// ssh://gerrit.mot.com:29418/home/repo/dev/platform/android/platform/manifest/s.git
        	if(manifest_url.endsWith(".git")) {
        		
        		manifestinfo_url_gitname = manifest_url.substring(manifest_url.lastIndexOf("/")+"/".length());
        		
        	}
  
        	if(manifestinfo_url_gitname == null || !manifestinfo_url_gitname.endsWith(".git")) {
        		
        		
        		System.out.println("当前无法读取到 manifestinfo_url_gitname="+ manifestinfo_url_gitname);
        		return false;
        	}
        	
        	
        	System.out.println("manifest_branch = 【"+manifest_branch+"】");
        	System.out.println("manifest_xmlfile = 【"+manifest_xmlfile+"】");
           	System.out.println("manifest_url = 【"+manifest_url+"】");
         	System.out.println("manifestinfo_url_gitname = 【"+manifestinfo_url_gitname+"】");
           	
        	
           	manifestinfo_branch = manifest_branch;
        	
           	manifestinfo_xmlfile = manifest_xmlfile;
           	
           	manifestinfo_url = manifest_url;
           	

        	return true;
        	
        }
        
        
        
        
        
        
    
        
        
        
        boolean Build_Instruction_Msi_Operation() {
        	boolean is_ok  = false;
        	
        	
        	String msi_repo_init_str  = SearchStrWith_Pre_End("repo init -u","Compile command",BuildInstruction_MsiOnly_Content);
        	
        	String msi_build_str  = SearchStrWith_Pre_End("Compile command","</tbody>",BuildInstruction_MsiOnly_Content);
        	
        	
          	String msi_artifactory_url_str  = SearchStrWith_Pre_End("py -artifactory_url","-output_directory",BuildInstruction_MsiOnly_Content);
          	
          	
          	
          	if(msi_artifactory_url_str == null ) {
        		System.out.println("当前无法读取到 msi_artifactory_url_str="+ msi_artifactory_url_str+"  RawStr"+ BuildInstruction_MsiOnly_Content);
        		return false;
        	}
          	
    
        	if(msi_repo_init_str == null ) {
        		System.out.println("当前无法读取到 msi_repo_init_str="+ msi_repo_init_str+"  RawStr"+ BuildInstruction_MsiOnly_Content);
        		return false;
        	}
        	
        	if(msi_build_str == null ) {
        		System.out.println("当前无法读取到 msi_build_str="+ msi_build_str+"  RawStr"+ BuildInstruction_MsiOnly_Content);
        		return false;
        	}
        	
        	System.out.println("msi_repo_init_str = 【"+msi_repo_init_str+"】");
        	System.out.println("msi_build_str = 【"+msi_build_str+"】");
        	
        	System.out.println("msi_artifactory_url_str = 【"+msi_artifactory_url_str+"】");
        	
        	
          	String msi_brach_manifestname_str  = SearchStrWith_Pre_End("userdebug_","_test-keys",msi_artifactory_url_str);
          	
        	
          	if(msi_brach_manifestname_str == null ) {
        		System.out.println("当前无法读取到 msi_brach_manifestname_str="+ msi_brach_manifestname_str+"  RawStr"+ msi_artifactory_url_str);
        		return false;
        	}
          	
          	
//          	msi_brach_manifestname_str = 【mt_r-qmsi-all】
        	System.out.println("msi_brach_manifestname_str = 【"+msi_brach_manifestname_str+"】");
        	
        	if(!msi_brach_manifestname_str.contains("_")) {
        		System.out.println("当前读取到 msi_brach_manifestname_str="+ msi_brach_manifestname_str+" 无法 匹配到下划线分隔  BranchName_ManifestName");
        		return false;	
        		
        	}
        	
        	String[] brach_manifestname_arr = msi_brach_manifestname_str.split("_");
        	
        	if(brach_manifestname_arr == null) {
        		System.out.println("当前读取到 msi_brach_manifestname_str="+ msi_brach_manifestname_str+" 无法以 _下划线  分割split   brach_manifestname_arr=null");
        		return false;	
        		
        		
        	}
        	
        	
        	if( brach_manifestname_arr.length  != 2) {
        		System.out.println("当前读取到 msi_brach_manifestname_str="+ msi_brach_manifestname_str+"  _下划线  分割split  长度不为2 请检查 brach_manifestname_arr.length="+brach_manifestname_arr.length);
        		return false;	
        		
        		
        	}
        	
        String msi_branchname  = 	brach_manifestname_arr[0];
        	
        String msi_manifestname  =    	brach_manifestname_arr[1];
        	 
        		
        
    	System.out.println("msi_branchname = 【"+msi_branchname+"】");
    	System.out.println("msi_manifestname = 【"+msi_manifestname+"】");
    	
        	
          buildinstruction_msionly_repo_artifactory_url = msi_artifactory_url_str ;    // userdebug_mt_r-qmsi-all_test-keys_continuous/1761  
          buildinstruction_msionly_repo_artifactory_url_branch = msi_branchname ;  //  mt
          buildinstruction_msionly_repo_artifactory_url_manifestname_noend = msi_manifestname ;  // r-qmsi-all
          buildinstruction_msionly_repo_artifactory_url_manifestfilename = msi_manifestname +".xml"; // r-qmsi-all.xml
        
            buildinstruction_msionly_repo_artifactory_url_git =  (buildinstruction_msionly_repo_artifactory_url_branch.replace("m", "")+".git").trim();
          
        	
        	buildinstruction_msionly_repo_init = msi_repo_init_str;
        	
        	// 获取 xx.git
        	if(buildinstruction_msionly_repo_init.contains(" ") ) {
        		String []  repo_init_arr = buildinstruction_msionly_repo_init.trim().split(" ");
        		if(repo_init_arr != null) {
        			String init_one_word = repo_init_arr[0];
        			
        			if(init_one_word != null && init_one_word.contains(".git")) {
        				
        				
        				buildinstruction_msionly_repo_init_git =  	init_one_word.substring(init_one_word.lastIndexOf("/")+"/".length());
        				
        			}
        			
        			
        		}

        	}
        	System.out.println("buildinstruction_msionly_repo_init_git = 【"+buildinstruction_msionly_repo_init_git+"】");
        	
        	
        	if( buildinstruction_msionly_repo_init_git ==  null) {
            	System.out.println("buildinstruction_msionly_repo_init_git = 【"+buildinstruction_msionly_repo_init_git+"】 为空  请检查来自于【"+buildinstruction_msionly_repo_init+"】");        	
            	return false;	
        		
        		
        	}
        	
        	buildinstruction_msionly_compile_command = msi_build_str;

        	return true;
        	
        }
        
        
        
        
        boolean Build_Instruction_Vendor_Operation() {
        	boolean is_ok  = false;
        	String vendor_repo_init_str  = SearchStrWith_Pre_End("Getting the Source Code","repo sync",BuildInstruction_VendorOnly_Content);
        	
        	String vendor_build_str  = SearchStrWith_Pre_End("Build Command","NOTE:",BuildInstruction_VendorOnly_Content);
        	

        	if(vendor_repo_init_str == null ) {
        		System.out.println("当前无法读取到 vendor_repo_init_str="+ vendor_repo_init_str);
        		return false;
        	}
        	
        	if(vendor_build_str == null ) {
        		System.out.println("当前无法读取到 job_name="+ vendor_build_str);
        		return false;
        	}
        	
        	
        	System.out.println("vendor_repo_init_str = 【"+vendor_repo_init_str+"】");
        	System.out.println("vendor_build_str = 【"+vendor_build_str+"】");
        	
        	
        	buildinstruction_vendoronly_repo_init = vendor_repo_init_str;
        	
        	buildinstruction_vendoronly_compile_command = vendor_build_str;

        	return true;
        	
        }
        
        boolean Build_Summary_Operation() {
        	boolean is_ok  = false;
        	String job_name  = SearchStrWith_Pre_End("JOB NAME","BUILD NUMBER",BuildSummary_Content);
        	
        	String build_target  = SearchStrWith_Pre_End("BUILD TARGET","SOFTWARE",BuildSummary_Content);
        	
        	
        	System.out.println("job_name = 【"+job_name+"】");
        	System.out.println("build_target = 【"+build_target+"】");
        	
        	if(build_target == null || build_target.contains(" ")) {
        		System.out.println("当前无法读取到 build_target="+ build_target);
        		return false;
        	}
        	
        	if(job_name == null || job_name.contains(" ")) {
        		System.out.println("当前无法读取到 job_name="+ job_name);
        		return false;
        	}
        	
        	buildsummary_jobname = job_name;
        	
        	buildsummary_build_target = build_target;
        	
        	if(!buildsummary_jobname.contains("userdebug")) {
        		System.out.println("请尝试喂它 userbebug 的  Release 文件  拒绝  User 的 Release 文件 ! ");
        		return false;
        	}
        	
        	
        	return true;
        	
        }
        
        String SearchStrWith_Pre_End(String preStr , String endStr , String rawStr ) {
        	String matchResultStr = null;
        	String mPreStr = preStr;
        	String mEndStr = endStr;
        	
        	if(rawStr == null) {
        		
        		System.out.println("当前 字符串 preStr【"+preStr+"】  endStr【"+endStr+"】  原始字符串rawStr【"+rawStr+"】 为空  请检查!" );
        		return matchResultStr;
        	}
        	if(mPreStr == null || mEndStr == null  ) {
        		System.out.println("当前 字符串 preStr【"+preStr+"】  endStr【"+endStr+"】  为空  请检查!" );
        		return matchResultStr;
        		
        	}
        	if(!rawStr.contains(preStr) || !rawStr.contains(endStr)) {
        		System.out.println("当前 字符串 preStr【"+preStr+"】  endStr【"+endStr+"】  并不包含在 rawStr【"+rawStr+"】 中!" );
        		return matchResultStr;
        	}
        	
        	if(rawStr.indexOf(preStr) >  rawStr.indexOf(endStr)) {
        		mPreStr = endStr;
        		mEndStr = preStr;
        	}
        	
        	
        	
        	int pre_index = rawStr.indexOf(mPreStr);
        	int end_index = rawStr.indexOf(endStr);
        	
        	String middleStr = rawStr.substring(pre_index+mPreStr.length(),end_index);
        	
        	if(middleStr != null ) {
        		
        		middleStr = middleStr.replace("<th>", "").replace("<table>", "").replace("<td>", "").replace("<tr>", "")
        				.replace("</th>", "").replace("</table>", "").replace("</td>", "").replace("</tr>", "").replace("<br />", "") 
        				.replace("&lt;", "【").replace("&gt;", "】")
        				.replace("\n", "").trim();
        	}
        	while(middleStr.startsWith("_")) {
        		middleStr =middleStr.substring(1);
        	}
        	
        	while(middleStr.endsWith("_")) {
        		middleStr =middleStr.substring(0,middleStr.length()-1);
        	}
        	
        
        	
        	return middleStr ;
        }
        
        
        
        boolean calculDynamicData() {
        	boolean is_get_data = false ;
        	
        	
        	if(!Build_Summary_Operation()) {
        		
        		return is_get_data;
        	}
        	
        	
    	if(!Build_Instruction_Msi_Operation()) {
        		
        		return is_get_data;
        	}
    	
   
        	if(!Build_Instruction_Vendor_Operation()) {
        		
        		return is_get_data;
        	}

        	
    	if(!ManifestInfo_Operation()) {
    		
    		return is_get_data;
    	}
    	
    	
    	// 把 repo init 中的 -m  manifest.xml  和 -b refs/tags/TTGN33.19-GENEVNA-SHA1  换成对应正确的值
    	repo_init_fixed();
    	
    	
     
        	return true;
	
        	
        	
        }
        
        void repo_init_fixed() {
        	
        	String msi_init_fixed_str = repo_init_msi_command_fixed(buildinstruction_msionly_repo_init);
        	
         	String vendor_init_fixed_str = repo_init_vendor_command_fixed(buildinstruction_vendoronly_repo_init);
        	
         	
         	buildinstruction_msionly_repo_fixed_init = msi_init_fixed_str;
         	buildinstruction_vendoronly_repo_fixed_init = vendor_init_fixed_str;
         	
         	System.out.println("buildinstruction_msionly_repo_fixed_init = 【"+ buildinstruction_msionly_repo_fixed_init+ "】");
         	System.out.println("buildinstruction_vendoronly_repo_fixed_init = 【"+ buildinstruction_vendoronly_repo_fixed_init+ "】");
         	
        }
        
        
        String repo_init_msi_command_fixed(String rawInitCommand) {
        	StringBuilder fixedCommand  = new StringBuilder();
        	String[] repoInitArr = rawInitCommand.split(" ");
        	
//        	refs/tags/TTGN33.19-GENEVNA-SHA1 -m sha1_embedded_manifest.xml
        	
        	for (int i = 0; i < repoInitArr.length; i++) {
				String itemStr = repoInitArr[i];
				
				if(itemStr.startsWith("refs/tags")) {
					
					fixedCommand.append(buildinstruction_msionly_repo_artifactory_url_branch+" ");
					continue;
				}
				
				
				if(itemStr.endsWith(".xml")) {
					
					fixedCommand.append(buildinstruction_msionly_repo_artifactory_url_manifestfilename+" ");
					continue;
				}
				

		
				fixedCommand.append(itemStr+" ");
				
			}
        	String fixed_command = fixedCommand.toString();
        	
			if(buildinstruction_msionly_repo_artifactory_url_git != null && buildinstruction_msionly_repo_init_git != null && !buildinstruction_msionly_repo_artifactory_url_git.equals(buildinstruction_msionly_repo_init_git)) {
				
				fixed_command = fixed_command.replace(buildinstruction_msionly_repo_init_git, buildinstruction_msionly_repo_artifactory_url_git);
				
			}
			
        	
        	return  " repo init -u "+fixed_command;
        }
        
        
        String repo_init_vendor_command_fixed(String rawInitCommand) {
        	StringBuilder fixedCommand  = new StringBuilder();
        	String[] repoInitArr = rawInitCommand.split(" ");
        	
//        	refs/tags/TTGN33.19-GENEVNA-SHA1 -m sha1_embedded_manifest.xml
        	
        	for (int i = 0; i < repoInitArr.length; i++) {
				String itemStr = repoInitArr[i];
				
				if(itemStr.startsWith("refs/tags")) {
					
					fixedCommand.append(manifestinfo_branch+" ");
					continue;
				}
				
				
				if(itemStr.endsWith(".xml")) {
					
					fixedCommand.append(manifestinfo_xmlfile+" ");
					continue;
				}
		
				fixedCommand.append(itemStr+" ");
				
			}
        	
        	
        	return  ""+fixedCommand.toString();
        }
        
        
        
        
        // o.git prodp-mtk6771 r-mt6771.xml lima
        void initWith4Params(String params) {
            String inputParam = params.trim();
            String[] results = inputParam.split(" ");
            if (results.length != 4) {
                System.out.println("initWith4Params 方法解析错误!  params =" + params+"  results.length="+results.length);
                return;
            }
            this.manifestinfo_url_gitname = results[0];
            this.manifestinfo_branch = results[1];
            this.manifestinfo_xmlfile = results[2];
            this.buildsummary_build_target = results[3];

        }

        void initBuildingCommandWithMap(Map<String, String> propMap) {
            String TAG = this.buildsummary_build_target + "_buildcommand";
            Set<String> keyList = new HashSet<>();
            Object[] keyObjs = propMap.keySet().toArray();
            for (int i = 0; i < keyObjs.length; i++) {
                String keyItem = keyObjs[i] + "".trim();
                if (keyItem.startsWith(TAG)) {
                    keyList.add(keyItem);
                }
            }

            ArrayList<String> commandList = new ArrayList<String>();
            Object[] selectedKeyObjs = keyList.toArray();
            for (int i = 0; i < selectedKeyObjs.length; i++) {
                String commandKey = selectedKeyObjs[i] + "";
                commandList.add(propMap.get(commandKey));
            }
            BuildInstructionList.addAll(commandList);
        }


    }

    static ArrayList<String> mKeyWordName = new ArrayList<>();
    static int DEFAULT_INPUT_NUM = 4;


    static void initSystemInfo(String[] args){
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if(osName.contains("window")){
            curOS_TYPE = OS_TYPE.Windows;
            Cur_Batch_End = ".bat";
        }else if(osName.contains("linux")){
            curOS_TYPE = OS_TYPE.Linux;
            Cur_Batch_End = ".sh";
        }else if(osName.contains("mac")){
            curOS_TYPE = OS_TYPE.MacOS;
            Cur_Batch_End = ".sh";
        }
        
        
      if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                if (i == 0) {
                    curDirPath = itemArgStr;
                } else {
                    mKeyWordName.add(itemArgStr);
                }
            }
        }
        
        
    }

    
    public  static  void   Operation_1_Params_init() {
    	
    	String init_fileName = F0_Repo_Init_File_Origin.getName();
    	File curShell_Init_File = new File(curDirPath+File.separator+init_fileName);
    	fileCopy(F0_Repo_Init_File_Origin, curShell_Init_File);
    //	System.out.println("F0_Repo_Init_File_Origin.exist() = "+ F0_Repo_Init_File_Origin.exists());
    	
    	if(curShell_Init_File.exists() && curShell_Init_File.length() > 10) {
            System.out.println("init 命令 复制 F0_repo_init.sh 文件 到本地 【成功】");
    	}else {
            System.out.println("init 命令 复制 F0_repo_init.sh 文件 到本地 【失败】");
    	}
    	return;
    
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
	
	
    
    public  static  void   Operation_1_Params_Html(String htmlName) {
    	
    	
        if ("".equals(curDirPath)) {
            System.out.println("当前输入的一个参数没有获得当前文件夹shell的路径,请检查,并重新执行!");
            showtip();
            return;
        }


        String htmlRealPath = buildReleaseNoteRealPath(curDirPath, htmlName);

        File htmlFile = new File(htmlRealPath);

        if (!htmlFile.exists()) {
            System.out.println("当前输入一个参数 .html文件不存在,请检查,并重新执行!");
            showtip();
            return;
        }
        
        
        
        String mRawHtmlContent = ReadFileContent(htmlFile);
        
        
        
        ArrayList<String> mHtmlContentList = ReadFileContentAsList(htmlFile);
        
        
        //  是有效的 html 文件的判断 
        
       boolean is_manifestinfo_exist =  isStringContainInList("Manifest Info",mHtmlContentList)  || isStringContainInList("MANIFEST INFO",mHtmlContentList);
        
       boolean is_buildinstruct_exist =  isStringContainInList("Build Instructions",mHtmlContentList)  || isStringContainInList("BUILD INSTRUCTIONS",mHtmlContentList);
       
       
        if(!is_manifestinfo_exist ||  !is_buildinstruct_exist ) {
 
        	System.out.println("mHtmlContentList.lenth() = "+mHtmlContentList.size());
            System.out.println("当前输入一个参数 .html文件  不包含关键字 Contain【MANIFEST INFO】="+is_manifestinfo_exist+"  和  Contain【BUILD INSTRUCTIONS】="+is_buildinstruct_exist+"  请检查 ");
      
            return;
        	
        }
        
        Repo_Meta_Data metaData = new Repo_Meta_Data();

        if (!InitRepoMetaData(htmlFile,mRawHtmlContent , mHtmlContentList ,  metaData)) {
            System.out.println("解析当前 【"+htmlName+"】ReleaseNote.html 文件 解析不到完整数据 导致异常 ,请检查,并重新执行!");
            showtip();
            return;
        }

        
        if(!metaData.calculDynamicData()) {
            System.out.println("解析当前 【"+htmlName+"】ReleaseNote.html 的数据 失败 请检查,并重新执行! !");
            showtip();
            return;

        }
        
        // 把  Repo_Meta_Data 的属性保存的 properities 中
        String productName = metaData.buildsummary_build_target;
        String xmlbranchName = metaData.manifestinfo_xmlfile;
        String gitRepoName = metaData.manifestinfo_url_gitname;
        input_VersionChar= gitRepoName.substring(0,1).charAt(0);
        String manifestBranchName = metaData.manifestinfo_branch;
        String key = productName.trim();
        String value = gitRepoName.trim() + " " + manifestBranchName.trim() + " " + xmlbranchName.trim() + " " + productName.trim();
        propKey2ValueList.put(key, value);
        productKey2ValueList.put(key,value);
        F0_Properties.setProperty(key, value);

        
        //  把 repo 相关的信息  写入    F0_miami.txt 文件中  方便 下次读取 
        
        ArrayList<String> product_txt_List = new       ArrayList<String>();


        
         // 写入文件 zukgit 
        product_txt_List.add("1_build_target       =  " +metaData.buildsummary_build_target);
        product_txt_List.add("2_job_name           =  " +metaData.buildsummary_jobname);

        product_txt_List.add("3_msi_artif_uri      =  " +metaData.buildinstruction_msionly_repo_artifactory_url); 
        product_txt_List.add("4_msi_artif_branch   =  " +metaData.buildinstruction_msionly_repo_artifactory_url_branch); 
        product_txt_List.add("5_msi_artif_git      =  " +metaData.buildinstruction_msionly_repo_artifactory_url_git); 

        
        product_txt_List.add("6_msi_artif_manifest =  " +metaData.buildinstruction_msionly_repo_artifactory_url_manifestfilename); 
        product_txt_List.add("7_msi_init           =  " +metaData.buildinstruction_msionly_repo_init);
        product_txt_List.add("8_msi_init_git       =  " +metaData.buildinstruction_msionly_repo_init_git);
        if(metaData.buildinstruction_msionly_repo_init_git != null && metaData.buildinstruction_msionly_repo_artifactory_url_git != null 
        		&& !metaData.buildinstruction_msionly_repo_init_git.equals(metaData.buildinstruction_msionly_repo_artifactory_url_git)) {
            product_txt_List.add("______notice_____"+"从Artifactory读取到的【MSI的repo分支("+metaData.buildinstruction_msionly_repo_artifactory_url_git+")】 "+
            		"从 Build Instruction 读取到的【MSI的repo分支("+metaData.buildinstruction_msionly_repo_init_git+")】 不一致 Fuck 以Artifactory为准 "+
            		"______notice_____");
        }
        
        product_txt_List.add("9_msi_fixed_init     =  " +metaData.buildinstruction_msionly_repo_fixed_init);
        product_txt_List.add("10_msi_build         =  " +metaData.buildinstruction_msionly_compile_command); 
        product_txt_List.add("11_vendor_init       =  " +metaData.buildinstruction_vendoronly_repo_init);
        product_txt_List.add("12_vendor_fixed_init =  " +metaData.buildinstruction_vendoronly_repo_fixed_init);
        product_txt_List.add("13_vendor_build      =  " +metaData.buildinstruction_vendoronly_compile_command); 
        product_txt_List.add("14_manifest_branch   =  " +metaData.manifestinfo_branch); 
        product_txt_List.add("15_manifest_xmlfile  =  " +metaData.manifestinfo_xmlfile); 
        product_txt_List.add("16_manifest_url      =  " +metaData.manifestinfo_url); 
        product_txt_List.add("17_manifest_git      =  " +metaData.manifestinfo_url_gitname); 
        
        
        // -------------------------------   【 Modem Release Info 信息】-----------------------------
        product_txt_List.add("");
        
        product_txt_List.add(getSchema("【"+productName+"】  Modem Release Info 信息"));
        product_txt_List.add(metaData.ModemRelease_MoveHtml_Content );
        product_txt_List.add(""); 
        
        
        // -------------------------------   【 Manifest    Info 信息】-----------------------------
        product_txt_List.add("");
        
        product_txt_List.add(getSchema("【"+productName+"】  Manifest  Info 信息"));
        product_txt_List.add(metaData.ManifestInfo_MoveHtml_Content  );
        product_txt_List.add(""); 
        
        

        // -------------------------------   【 BUILD INSTRUCTIONS  Info 信息】-----------------------------
        product_txt_List.add("");
        
        product_txt_List.add(getSchema("【"+productName+"】  Build Instructions Info 信息"));
        product_txt_List.add(metaData.BuildInstruction_MoveHtml_Content  );
        product_txt_List.add(""); 
        
        
        
        
        // -------------------------------   【  MSI Init Info 信息】-----------------------------

        product_txt_List.add("");
        product_txt_List.add("");
        
        product_txt_List.add(getSchema("【"+productName+" MSI Init 】 MSI Repo Init 初始化 MSI 命令"));
        product_txt_List.add("");
        product_txt_List.add(metaData.buildinstruction_msionly_repo_fixed_init+" && "+ " repo sync -j2 " +" && "+" repo start --all TEMP " +" && "+" "+" && "+"source /opt/conf/moto.conf "+" && "+metaData.buildinstruction_msionly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");
        product_txt_List.add("");
        
        product_txt_List.add(getSchema("【"+productName+" MSI Init && Zrule 】 MSI Repo Init 初始化 MSI 命令  && Zrule修改命令 "));
        product_txt_List.add("");
        product_txt_List.add(metaData.buildinstruction_msionly_repo_fixed_init+" && "+ " repo sync -j2 " +" && "+" repo start --all TEMP " +" && "+"zrule_apply_G2"+Cur_Batch_End+" #_57 1  2  tip_qcom_mtk"+" && "+"source /opt/conf/moto.conf "+" && "+metaData.buildinstruction_msionly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        
        
        product_txt_List.add("");
        product_txt_List.add(getSchema("【"+productName+" MSI-Track Init 】  MSI Repo Trace Init 初始化 MSI 命令"));
        product_txt_List.add("");
        product_txt_List.add("source /opt/conf/moto.conf "+" && "+ metaData.buildinstruction_msionly_repo_fixed_init+" && "+ " repo --trace sync  -cdf  -j2 " +" && "+" repo start --all TEMP " +" && "+metaData.buildinstruction_msionly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");
        product_txt_List.add("");

        product_txt_List.add(getSchema("【"+productName+" MSI-Track Init && Zrule  】  MSI Repo Trace Init 初始化 MSI 命令  && Zrule修改命令 "));
        product_txt_List.add("");
        product_txt_List.add(metaData.buildinstruction_msionly_repo_fixed_init+" && "+ " repo --trace sync  -cdf  -j2  " +" && "+" repo start --all TEMP " +" && "+"zrule_apply_G2"+Cur_Batch_End+" #_57 1  2  tip_qcom_mtk"+" && "+"source /opt/conf/moto.conf "+" && "+metaData.buildinstruction_msionly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        product_txt_List.add("");
        
        
        product_txt_List.add(getSchema("【"+productName+" MSI Rebuild 】 "));
        product_txt_List.add("");
        product_txt_List.add("source /opt/conf/moto.conf "+" && "+metaData.buildinstruction_msionly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        product_txt_List.add("");
        product_txt_List.add(getSchema("【"+productName+" MSI Rebuild && Zrule  】 &&  Zrule修改命令 "));
        product_txt_List.add("");
        product_txt_List.add("zrule_apply_G2"+Cur_Batch_End+" #_57 1  2  tip_qcom_mtk"+" && "+"source /opt/conf/moto.conf "+" && "+metaData.buildinstruction_msionly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        
        
        product_txt_List.add("");
        product_txt_List.add("");
        product_txt_List.add("");
        product_txt_List.add("");
        // -------------------------------   【  Vendor Init Info 信息】-----------------------------
        
        product_txt_List.add(getSchema("【"+productName+" Vendor Init 】  Vendor Repo Init 初始化 Vendor 命令"));
        product_txt_List.add("");
        product_txt_List.add("source /opt/conf/moto.conf "+" && "+ metaData.buildinstruction_vendoronly_repo_fixed_init+" && " +" repo sync -j2 " +" && "+" repo start --all TEMP " +" && "+metaData.buildinstruction_vendoronly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        product_txt_List.add("");
        product_txt_List.add(getSchema("【"+productName+" Vendor Init && Zrule  】  Vendor Repo Init 初始化 Vendor 命令 && zrule修改命令"));
        product_txt_List.add("");
        product_txt_List.add( metaData.buildinstruction_vendoronly_repo_fixed_init+" && " +" repo sync -j2 " +" && "+" repo start --all TEMP "+" && "+"zrule_apply_G2"+Cur_Batch_End+" #_57 1  2  tip_qcom_mtk"  +" && "+"source /opt/conf/moto.conf "+" && "+metaData.buildinstruction_vendoronly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        
        
        product_txt_List.add("");
        product_txt_List.add(getSchema("【"+productName+" Vendor Track Init 】 Vendor Repo Trace Init  初始化 Vendor 命令"));
        product_txt_List.add("");
        product_txt_List.add("source /opt/conf/moto.conf "+" && "+ metaData.buildinstruction_vendoronly_repo_fixed_init+" && " +" repo --trace sync  -cdf  -j2 " +" && "+" repo start --all TEMP " +" && "+metaData.buildinstruction_vendoronly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        
        product_txt_List.add("");
        product_txt_List.add(getSchema("【"+productName+" Vendor Track Init && Zrule  】 Vendor Repo Trace Init  初始化 Vendor 命令 && Zrule修改命令 "));
        product_txt_List.add("");
        product_txt_List.add( metaData.buildinstruction_vendoronly_repo_fixed_init+" && " +" repo --trace sync  -cdf  -j2 " +" && "+" repo start --all TEMP "+" && "+"zrule_apply_G2"+Cur_Batch_End+" #_57 1  2  tip_qcom_mtk"  +" && "+"source /opt/conf/moto.conf "+" && "+metaData.buildinstruction_vendoronly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        
        product_txt_List.add("");
        
        product_txt_List.add(getSchema("【"+productName+" Vendor Rebuild 】 "));
        product_txt_List.add("");
        product_txt_List.add("source /opt/conf/moto.conf "+"  && "+metaData.buildinstruction_vendoronly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        product_txt_List.add("");
        
        
        product_txt_List.add(getSchema("【"+productName+" Vendor Rebuild  && Zrule 】  && Zrule修改命令"));
        product_txt_List.add("");
        product_txt_List.add("zrule_apply_G2"+Cur_Batch_End+" #_57 1  2  tip_qcom_mtk"+" && "+"source /opt/conf/moto.conf "+"  && "+metaData.buildinstruction_vendoronly_compile_command+ " 2>&1 | tee "+getTimeStamp()+"_"+metaData.buildsummary_build_target+".log");

        product_txt_List.add("");

        // -------------------------------   【 编译 apk jar so bin 命令】-----------------------------
        product_txt_List.add("");
        product_txt_List.add(getSchema("【"+productName+"】 【 编译 apk jar so bin 命令】"));

        product_txt_List.add("【 Settings.apk " + productName + "" + "】");
        product_txt_List.add(buildSettingsApk(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, productName, ""));
        product_txt_List.add("");
        product_txt_List.add("【 framework.jar " + productName + "-userdebug" + "】");
        product_txt_List.add(buildFrameworkJar(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, productName, ""));


        product_txt_List.add("");
        product_txt_List.add("【 wifi-services.jar " + productName + "-userdebug" + "】");
        product_txt_List.add(buildWifiServiceJar(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, productName, ""));

        product_txt_List.add("");
        product_txt_List.add("【 bin/wpa_supplicant " + productName + "-userdebug" + "】");
        product_txt_List.add(buildWpaSupplicant(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, productName, ""));
        product_txt_List.add("push命令:  adb root && adb remount && adb push ./wpa_supplicant  /vendor/bin/hw/ && adb reboot ");
        product_txt_List.add("");
        product_txt_List.add("【 mtk/SarControlService " + productName + "-userdebug" + "】");
        product_txt_List.add("");
        product_txt_List.add(" export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH &&  source build/envsetup.sh &&  source /opt/conf/moto.conf  && lunch "+productName+"-userdebug  && cd ./motorola/modem/mtk/SarControl/SarControlService &&  mm ");
        product_txt_List.add("");
        product_txt_List.add("zzfile_3.sh   ./out/target/product/tesla/system/priv-app/MtkSarControlService.apk");   
        
        
        product_txt_List.add("");
        product_txt_List.add("【 matk/sarwifi " + productName + "-userdebug" + "】");   
        product_txt_List.add(" export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH &&  source build/envsetup.sh &&  source /opt/conf/moto.conf  && lunch "+productName+"-userdebug  &&   cd ./vendor/mediatek/proprietary/hardware/connectivity/wlan/sarwifi/service &&  mm   ");
        
        
        
        product_txt_List.add("");
        product_txt_List.add("【 qcom/libmdmcutback " + productName + "-userdebug" + "】");   
        product_txt_List.add(" export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH &&  source build/envsetup.sh &&  source /opt/conf/moto.conf  && lunch "+productName+"-userdebug  &&   make libmdmcutback | tee build_libmdmcutback.log     ");
        

        
   
        
        // -------------------------------   【  Git Command Tip 命令提示】-----------------------------
        
        
        product_txt_List.add("");
        product_txt_List.add(getSchema("【"+productName+"】  Git Command Tip 命令提示"));
        product_txt_List.add("git am **.patch                          【打patch】 ");
        product_txt_List.add("git am *.patch  --whitespace=fix         【修复有空格错误的patch提交】");
        product_txt_List.add("git fetch origin mr-2021-q3/br:TEMP_A    【拉取远程分支到本地 TEMP_A分支】");
        product_txt_List.add("git pull --rebase origin mq-2020-q4/bq   【拉取远程分支 rebase 】");
        product_txt_List.add("repo forall -c  git reset --hard HEAD~3  【所有git 回退到上三个版本】");
        product_txt_List.add("repo forall -c git clean -nfdx           【清理项目 删除 untracked files&dir 以及gitignore标记的文件目录】  ");
        product_txt_List.add("repo forall -c git clean -nfd            【清理项目 删除 untracked files&dir 】  ");

        product_txt_List.add("mkdir msi && cd msi                      【创建目录并进入】 ");
        product_txt_List.add("cp -fr ../zukgit.txt  ./                 【复制上一目录的文件到当前文件夹】 ");
        product_txt_List.add("chmod 777 -R ./F0_repo_init.sh           【对当前 sh 文件赋权】 ");
        product_txt_List.add("grep -rins \"zukgit\" .                  【全局搜索文件内容中包含 zukgit 的文件】 ");
        product_txt_List.add("find . -name \"*zukgit*\" .              【全局查找文件名称包含 zukgit 的文件】 ");
        product_txt_List.add("df -h                                    【查看磁盘文件大小】 ");
        product_txt_List.add("cd /home && ls -l                        【查看当前工作站用户】");
        
        product_txt_List.add("");
        
        
        product_txt_List.add(getSchema("【"+productName+"】  Git Sync Error  命令提示"));
        product_txt_List.add("一.unable to read sha1 file of xx 问题的处理");
        product_txt_List.add("  1.【  repo --trace sync  -cdf  -j2  】  ___ 查看repo的动作 看哪一个 git 分支报错");
        product_txt_List.add("   如:cd /Code1/Vendor2/kernel/prebuilts/5.4/arm64 : git read-tree --reset -u -v HEAD 1>| 2>|  报错"); 
        product_txt_List.add("   说明 路径/kernel/prebuilts/5.4/arm64 报错");
        product_txt_List.add("  2.【  rm -fr .repo/projects/kernel/prebuilts/5.4/arm64.git   】  ___ 删除.repo 下文件");
        product_txt_List.add("  3.【  rm -fr .repo/project-objects/home/repo/dev/platform/android/kernel/prebuilts/5.4/arm64.git   】  ___ 删除.repo 下文件");
        product_txt_List.add("  4.【  repo init xxx  &&  repo --trace sync  -cdf  -j2   】 重新更新代码  ");
        product_txt_List.add("");
        product_txt_List.add("二. error: Exited sync due to fetch errors && would clobber existing tag 拉取失败问题处理 ");
        product_txt_List.add("  1.【   repo forall -c git fetch --tags -f  && repo --trace sync  -cdf  -j2  】  ___ 更新远程代码");
        product_txt_List.add("");
        product_txt_List.add("三. 拉取远程分支到本地 到本地新分支的操作 ");
        product_txt_List.add("  1.【 git branch -a > a.txt   】  【查看当前的远程分支】");
        product_txt_List.add("  1.【 git fetch origin mr-2021-q3/br:TEMP_A  】  【拉取远程分支到本地 TEMP_A分支】");
        
        
        metaData.repo_info_store_file = new File(Zbin+"F0_"+productName+".txt");
        writeContentToFile(metaData.repo_info_store_file,product_txt_List);
    
        
        setProperity();
        showtip();
        System.out.println("═════════════════════════ 已保存当前解析的 ReleaseNote.html produce_name【"+metaData.buildsummary_build_target+"  "+metaData.repo_info_store_file.getName()+"】  详情调用如下: ═════════════════════════ ");
  
        return;
        

    
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

	
    public  static  String   Clear_Empty_Line_For_String(String rawContent) {
    	StringBuilder sb = new  StringBuilder();
    	
    	if(!rawContent.contains("\n")) {
    		return rawContent;
    	}

    	String[] arr_raws = rawContent.split("\n");
    	
    	if(arr_raws == null) {
    		return rawContent;
    	}
    	
    	for (int i = 0; i < arr_raws.length; i++) {
			String str_item = arr_raws[i]; 
			if("".equals(str_item.trim())) {
				continue;
			}
			sb.append(str_item+"\n");
		}
    	
    	return sb.toString();
    	
    }
    
    public  static  void   Operation_5_Params_Delete(String productName) {
    
        propKey2ValueList.remove(productName);
        productKey2ValueList.remove(productName);

        F0_Properties.remove(productName);

        setProperity();

        showtip();
        
        System.out.println("═════════════════════ 执行 完成 删除 【"+productName+"】repo 信息 的操作! ═════════════════════ ");
     
    }
	
    
    public  static  void   Operation_4_Params_QueryRepo(ArrayList<String> inputKeyList) {
    	
    	System.out.println("执行  查询  repo 信息 的操作! ");
    	
    	
        input_productName = inputKeyList.get(inputKeyList.size() - 1);  // 获取最后一个当做产品名称


        if ("".equals(input_productName)) {
            System.out.println("输入的Product产品名称为空!");
            showtip();
            return;
        }
        ArrayList<String> otherParamList = new ArrayList<String>();
        for (int i = 0; i < inputKeyList.size() - 1; i++) {
            otherParamList.add(inputKeyList.get(i));
        }

        getDetailInput(otherParamList);


        if (!checkParamRight()) {
            System.out.println("检测到当前输入参数存在空的情况，请重新输入!");
            showtip();
            return;
        }
        
        
        //  查看当前的 Map 中是否 有 对应的 工程的 记录  没有的话  也不打印 
        if(!productKey2ValueList.containsKey(input_productName)) {
        	
        	System.out.println("当前 没有输入的 工程名 ProductName【"+input_productName+"】  请喂它 对应文件的 realease html 文件! ");
        	return ;
        	
        }
      

        // 查看当前输入的产品名称是否有保存在 prop中
        Repo_Meta_Data selectRepoMetaData = checkRepoMetaInProp(input_productName);

        if (selectRepoMetaData == null) {   //  如果当前的 项目名称不存在 prop 中 那么打印 传统的数据
            System.out.println("该 ProductName="+input_productName+" 【不存在于 prop】 中将打印可能的命令(也许会执行失败)");
            System.out.println("请输入 ...【"+input_productName+"】ReleaseHtml.html 文件 来完成 数据的初始化");
//            showProperiesMap(propKey2ValueList);
        } else {   // 如果在当前的 prop中  拿到数据 去解析
            System.out.println("该 ProductName="+input_productName+" 【存在于 prop】 中将打印所有的命令");
           
            input_VersionChar= selectRepoMetaData.manifestinfo_url_gitname.substring(0,1).toLowerCase().charAt(0);
            System.out.println();
            

//            showRepoInitFrameworkDexFlag(selectRepoMetaData);
//            System.out.println();
//            showRepoInitFrameworkDexFlagAndAddLog(selectRepoMetaData);
//            System.out.println();

            // 显示  初始化 的 描述
            selectRepoMetaData.repo_info_store_file = new File(Zbin+"F0_"+input_productName+".txt");
            
           ArrayList<String> product_txt_list =  ReadFileContentAsList(selectRepoMetaData.repo_info_store_file);
           showProductTxtList(product_txt_list , selectRepoMetaData.repo_info_store_file);  // 显示 从 txt 中读取到的内容 
           
           
//           System.out.println();
//            showRepoInit(selectRepoMetaData);
           
           
//            System.out.println();
//            showRepoInitAddCpCommand(selectRepoMetaData);
            
            
//            System.out.println();
//            showRebuildingCommand(selectRepoMetaData);
           
//            System.out.println();
//            showBuildingAppCommand();
           
            System.out.println();
    
            showPathTip(input_productName,input_VersionChar);
            showProperiesMap(productKey2ValueList);
            showCommitTip();
        }
        
    	
    	
    }
	
    
    public  static  void   showProductTxtList(ArrayList<String> productDetailList , File  productFile) {
        printSchema("【 "+productFile.getName()+" 详细信息 】");
    	
    	for (int i = 0; i < productDetailList.size(); i++) {
    		String lineItem = productDetailList.get(i);
    		
    		System.out.println(lineItem);
			
		}
    	
    }
    
    
    public  static  void   Repo_Operation(ArrayList<String> inputKeyList) {
    	int input_key_len = inputKeyList.size();
    	
    	
    	switch(input_key_len) {
    	case 0 :
            System.out.println("输入的Product参数数据为空!");
            showtip();
            return;
    		
    	case 1 :
    		String oneParam = inputKeyList.get(inputKeyList.size()-1);
        
    		 if(oneParam.toLowerCase().equals("init")) {
    			Operation_1_Params_init();
    		 } else if(oneParam.toLowerCase().endsWith(".html")) {
    			 
    				Operation_1_Params_Html(oneParam);
    		 }
         
            return;
       
            
    	 case 4 :
            System.out.println("输入的Product参数数据为空!");
			Operation_4_Params_QueryRepo(inputKeyList);
            return;
            
            
    	 case 5 :   // 
            if(isStringInList("-Delete",inputKeyList)) {
 				Operation_5_Params_Delete(inputKeyList.get(inputKeyList.size() -2));
            }else {
            	System.out.println("用户输入的 5 个参数 无法找到 对应的 处理规则 请检查!");
            	
            }
    		 
             return;
             
             
            
    		default:
    			
    			System.out.println("当前用户输入的参数的个数为: "+input_key_len+" 没有实现对应的规则! ");
    	
    	
    	
    	
    	}
    	
    	
    	
    	
    }
    
    
    public static boolean isStringContainInList(String str , ArrayList<String> strList) {
    	boolean  existFlag = false;
    	
    	for (int i = 0; i < strList.size(); i++) {
    		String strItem = strList.get(i);
    	//	System.out.println("line["+i+"] = "+strItem  +" falg="+strItem.contains(str));
    		if(strItem.contains(str)) {
    			
    			return true;
    		}
			
		}
    	
    	
    	
    	return existFlag;
    	
    	
    }
    
    
    
    public static boolean isStringInList(String str , ArrayList<String> strList) {
    	boolean  existFlag = false;
    	
    	for (int i = 0; i < strList.size(); i++) {
    		String strItem = strList.get(i);
    		if(strItem.equals(str)) {
    			
    			return true;
    		}
			
		}
    	
    	
    	
    	return existFlag;
    	
    	
    }

    //  Build 部分     ManiFest Info 部分 
    // 1. 读取 release 文件  保存到 对应项目名称中的 .txt 文件中  ,  文件名称读取失败 报错   写入的时候读取 文件名称 出来 到 对应的目录中去
    public static void main(String[] args) {
        initSystemInfo(args);


        RepoMetaListInitWithMap(propRepoMetaList, propKey2ValueList);
        
        Repo_Operation(mKeyWordName);
		/*
		 * if(true) { return ; }
		 * 
		 * 
		 * if (mKeyWordName.size() == 0) { // 空的参数的操作
		 * System.out.println("输入的Product参数数据为空!"); showtip(); return; } else if
		 * (mKeyWordName.size() == 1) { // 传递一个 ReleaseNote文件进来进行解析 String
		 * releaseNoteHtmlPath = mKeyWordName.get(0);
		 * 
		 * // 如果传入的只有一个参数 并且 这个参数的 开头是 init 那么 就把 F0_repo_init.sh 复制到当前文件夹路径下
		 * if(releaseNoteHtmlPath.toLowerCase().equals("init")) {
		 * System.out.println("!! F0_repo_init.sh 文件复制到当前路径: "+ curDirPath);
		 * 
		 * String init_fileName = F0_Repo_Init_File_Origin.getName(); File
		 * curShell_Init_File = new File(curDirPath+File.separator+init_fileName);
		 * fileCopy(F0_Repo_Init_File_Origin, curShell_Init_File); //
		 * System.out.println("F0_Repo_Init_File_Origin.exist() = "+
		 * F0_Repo_Init_File_Origin.exists());
		 * 
		 * if(curShell_Init_File.exists() && curShell_Init_File.length() > 10) {
		 * System.out.println("init 命令 复制 F0_repo_init.sh 文件 到本地 【成功】"); }else {
		 * System.out.println("init 命令 复制 F0_repo_init.sh 文件 到本地 【失败】"); } return; }
		 * 
		 * if (!releaseNoteHtmlPath.endsWith("ReleaseNotes.html") &&
		 * !releaseNoteHtmlPath.startsWith("release_notes")) {
		 * System.out.println("当前输入的一个参数并不是  **ReleaseNotes.html 无法进行下一步解析 请检查重新执行!");
		 * showtip(); return; }
		 * 
		 * if ("".equals(curDirPath)) {
		 * System.out.println("当前输入的一个参数没有获得当前文件夹shell的路径,请检查,并重新执行!"); showtip();
		 * return; }
		 * 
		 * 
		 * String htmlRealPath = buildReleaseNoteRealPath(curDirPath,
		 * releaseNoteHtmlPath);
		 * 
		 * File htmlFile = new File(htmlRealPath);
		 * 
		 * if (!htmlFile.exists()) {
		 * System.out.println("当前输入一个参数 *ReleaseNote.html文件不存在,请检查,并重新执行!"); showtip();
		 * return; }
		 * 
		 * Repo_Meta_Data metaData = new Repo_Meta_Data();
		 * 
		 * if (!InitRepoMetaData(htmlFile, metaData)) {
		 * System.out.println("解析当前 ReleaseNote.html 文件 解析不到完整数据 导致异常 ,请检查,并重新执行!");
		 * showtip(); return; }
		 * 
		 * // 把 Repo_Meta_Data 的属性保存的 properities 中 String productName =
		 * metaData.productName; String xmlbranchName = metaData.xmlbranchName; String
		 * gitRepoName = metaData.gitRepoName; mVersionChar=
		 * gitRepoName.substring(0,1).charAt(0); String manifestBranchName =
		 * metaData.manifestBranchName; String key = productName.trim(); String value =
		 * gitRepoName.trim() + " " + manifestBranchName.trim() + " " +
		 * xmlbranchName.trim() + " " + productName.trim(); propKey2ValueList.put(key,
		 * value); productKey2ValueList.put(key,value); F0_Properties.setProperty(key,
		 * value);
		 * 
		 * for (int i = 0; i < metaData.BuildingCommandList.size(); i++) { int index = i
		 * + 1; String buildKey = productName + "_buildcommand_" + index; String
		 * buildValue = metaData.BuildingCommandList.get(i);
		 * F0_Properties.setProperty(buildKey, buildValue); } setProperity();
		 * System.out.println("已保存当前解析的 ReleaseNote.html produce_name【"+metaData.
		 * productName+"】  详情调用如下:"); showtip(); return; } else if (mKeyWordName.size()
		 * != DEFAULT_INPUT_NUM) { System.out.println("输入的Product参数数据个数不对,当前为 " +
		 * mKeyWordName.size() + "个参数,实际需要" + DEFAULT_INPUT_NUM + "个!"); showtip();
		 * return; }
		 * 
		 * productName = mKeyWordName.get(mKeyWordName.size() - 1); // 获取最后一个当做产品名称
		 * 
		 * 
		 * if ("".equals(productName)) { System.out.println("输入的Product产品名称为空!");
		 * showtip(); return; } ArrayList<String> otherParamList = new
		 * ArrayList<String>(); for (int i = 0; i < mKeyWordName.size() - 1; i++) {
		 * otherParamList.add(mKeyWordName.get(i)); }
		 * 
		 * getDetailInput(otherParamList);
		 * 
		 * 
		 * if (!checkParamRight()) { System.out.println("检测到当前输入参数存在空的情况，请重新输入!");
		 * showtip(); return; }
		 * 
		 * // 查看当前输入的产品名称是否有保存在 prop中 Repo_Meta_Data selectRepoMetaData =
		 * checkRepoMetaInProp(productName);
		 * 
		 * if (selectRepoMetaData == null) { // 如果当前的 项目名称不存在 prop 中 那么打印 传统的数据
		 * System.out.println("该 ProductName="
		 * +productName+" 【不存在于 prop】 中将打印可能的命令(也许会执行失败)");
		 * showRepoInitFrameworkDexFlag(); System.out.println(); showRepoInit();
		 * System.out.println(); showRebuildingCommand(); System.out.println();
		 * showBuildingAppCommand(); System.out.println(); showCommitTip();
		 * showPathTip(productName,mVersionChar);
		 * showProperiesMap(productKey2ValueList); //
		 * showProperiesMap(propKey2ValueList); } else { // 如果在当前的 prop中 拿到数据 去解析
		 * System.out.println("该 ProductName="+productName+" 【存在于 prop】 中将打印所有的命令");
		 * mVersionChar=
		 * selectRepoMetaData.gitRepoName.substring(0,1).toLowerCase().charAt(0);
		 * System.out.println(); showRepoInitFrameworkDexFlag(selectRepoMetaData);
		 * System.out.println();
		 * showRepoInitFrameworkDexFlagAndAddLog(selectRepoMetaData);
		 * System.out.println();
		 * 
		 * showRepoInit(selectRepoMetaData); System.out.println();
		 * showRepoInitAddCpCommand(selectRepoMetaData); System.out.println();
		 * showRebuildingCommand(selectRepoMetaData); System.out.println();
		 * showBuildingAppCommand(); System.out.println(); showCommitTip();
		 * showPathTip(productName,mVersionChar);
		 * showProperiesMap(productKey2ValueList); //
		 * showProperiesMap(propKey2ValueList); }
		 */
    }


    static Repo_Meta_Data checkRepoMetaInProp(String cproductName) {
        Repo_Meta_Data selectRepoMeta = null;
        for (int i = 0; i < propRepoMetaList.size(); i++) {
            Repo_Meta_Data itemData = propRepoMetaList.get(i);
            if (itemData.buildsummary_build_target.trim().equals(cproductName.trim())) {
                selectRepoMeta = itemData;
                break;
            }
        }
        return selectRepoMeta;
    }


    static void RepoMetaListInitWithMap(ArrayList<Repo_Meta_Data> repoMetaDataList, Map<String, String> propkeyValueMap) {
        Set<String> keySet = propkeyValueMap.keySet();    //  判断出当前总共有多少个项目
        Set<String> nameList = new HashSet<String>();
        Object[] keyObjs = keySet.toArray();
//        System.out.println("RepoMetaListInitWithMap.size = "+ keyObjs.length);



        for (int i = 0; i < keyObjs.length; i++) {
            String keyItem = keyObjs[i] + "";
//            System.out.println("NameKey1 = "+ keyItem);
            //   _下划线  小于 = 等于号 , 说明等号在前面  那么 就标识这是一个项目的名称
//            if (keyItem.trim().indexOf("_") < keyItem.trim().indexOf("=") || !keyItem.contains("_")) {
                nameList.add(keyItem);
//                System.out.println("NameKey2 = "+ keyItem);
                productKey2ValueList.put(keyItem,propkeyValueMap.get(keyItem));
//            }
        }


        Object[] nameObjs = nameList.toArray();
        for (int i = 0; i < nameObjs.length; i++) {
            Repo_Meta_Data repoData = new Repo_Meta_Data();
            repoData.buildsummary_build_target = nameObjs[i] + "";
//            System.out.println("XXXX repoData.productName="+ repoData.productName);
            repoData.initWith4Params(propkeyValueMap.get(nameObjs[i]));
            repoData.initBuildingCommandWithMap(propkeyValueMap);
            repoMetaDataList.add(repoData);
        }
    }

    static void setProperity() {
        try {
            F0_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(F0_Properties_File.getAbsolutePath()));
            F0_Properties.store(F0_Properties_OutputStream, "");
            F0_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static String buildReleaseNoteRealPath(String dirPath, String filePath) {
        String realPath = "";
        String curFilepath = filePath.trim();
        curFilepath = curFilepath.replace("./", "");

        curFilepath = curFilepath.replace("/", File.separator);

        realPath = dirPath + File.separator + curFilepath;
//        args[0] = C:\Users\zhuzj5\Desktop\F1_repo
//        args[1] = ./PMD29.114-LIMA-SHA1-oem_ReleaseNotes.html


        return realPath;

    }

    
	public static  ArrayList<String>  Str2StrList(String str ) {    
		
		ArrayList<String>  allStrList = new 	ArrayList<String> ();
		
		String[] strArr = str.split("\\n");
		
		
		allStrList.addAll(Arrays.asList(strArr));
		
		return allStrList;
		
	}
	
    static boolean InitRepoMetaData(File htmlFile, String htmlContent  ,  ArrayList<String> mHtmlContentList , Repo_Meta_Data metaData) {
    	
    	
    	String[] HeadBlockList = htmlContent.split("<h3 class=");
    	
    	if(HeadBlockList == null) {
			System.out.println("HeadBlockList[] 为空 ! ");
    		return false;
    	}
		System.out.println("HeadBlockList.length = " + HeadBlockList.length  + " htmlContent.length="+htmlContent.length());
    	for (int i = 0; i < HeadBlockList.length; i++) {
    		String headBlock = HeadBlockList[i].trim();
    		
			// System.out.println("head["+i+"] = " + headBlock);
			
			
			if(headBlock.contains("Build Summary</h3>")) {
				
				metaData.BuildSummary_Content = headBlock;
				metaData.BuildSummaryList = Str2StrList(headBlock);
				
			} else if(headBlock.contains("Manifest Info</h3>")) {
				metaData.ManifestInfo_Content  = headBlock;
				metaData.ManifestInfoList = Str2StrList(headBlock);
				metaData.ManifestInfo_MoveHtml_Content  =  ModemRelease_MoveHtml(metaData.ManifestInfo_Content);;
				
			}else if(headBlock.contains("Build Instructions</h3>")) {
				metaData.BuildInstruction_Content = headBlock;
				metaData.BuildInstructionList = Str2StrList(headBlock);
				String Msi_Begin_Str = "can be populated from Artifactory using this command";
				
				if(headBlock.contains(Msi_Begin_Str)) {   // NOTE: The '<path to MSI workspace>' can be populated from Artifactory using this command:
					metaData.BuildInstruction_MsiOnly_Content  = 	headBlock.substring(headBlock.indexOf(Msi_Begin_Str)+Msi_Begin_Str.length());
					
					metaData.BuildInstruction_MsiOnly_List = Str2StrList(metaData.BuildInstruction_MsiOnly_Content);
					
					metaData.BuildInstruction_VendorOnly_Content  = 	headBlock.substring(0,headBlock.indexOf(Msi_Begin_Str));	
					metaData.BuildInstruction_VendorOnly_List = Str2StrList(metaData.BuildInstruction_VendorOnly_Content);
					
					
				}
				
				metaData.BuildInstruction_MoveHtml_Content  = ModemRelease_MoveHtml(metaData.BuildInstruction_Content);
				
				
				
			}else if(headBlock.contains("Modem Release Notes</h3>")) {
				metaData.ModemRelease_Content = headBlock;
				metaData.ModemReleaseList = Str2StrList(headBlock);
				
				metaData.ModemRelease_MoveHtml_Content = ModemRelease_MoveHtml(metaData.ModemRelease_Content);
				
			}
			
			
			
		}
    	
    	if(metaData.BuildSummaryList.size() == 0 || metaData.BuildInstructionList.size() == 0) {
    		System.out.println("读取 BuildSummary  和  BuildInstruction 失败!  请检查! ");
    		return false;
    	}
//    	System.out.println("metaData.BuildInstruction_MsiOnly_Content ="+metaData.BuildInstruction_MsiOnly_Content);
//    	System.out.println("metaData.BuildInstruction_VendorOnly_Content ="+metaData.BuildInstruction_VendorOnly_Content);
//    	System.out.println("metaData.BuildSummaryList.size() ="+metaData.BuildSummaryList.size());
//    	
//    	for (int i = 0; i < metaData.BuildSummaryList.size(); i++) {
//    		System.out.println("str["+i+"]["+metaData.BuildSummaryList.size()+"] = "+metaData.BuildSummaryList.get(i));
//    	}
    	
    	return true;
    	
    	
    	
    }

    
    
    
    
    
    static String ModemRelease_MoveHtml(String htmlContent ) {
    	String modem_release_content = new String(htmlContent);
    	
    	modem_release_content = 	modem_release_content.replace("\"close\">", "").replace("</h3>", "").replace("<div>", "")
    	.replace("<pre>", "").replace("</pre>", "").replace("</div>", "").replace("<br />", "").replace("<hr />", "")
    	.replace("</h4>", "").replace("<table>", "").replace("<tbody>", "").replace("<tr>", "").replace("<th>", "")
    	.replace("</th>", "").replace("</tr>", "").replace("</td>", "").replace("</tbody>", "").replace("</table>", "")
     	.replace("<td>", "").replace("<h4>", "")
     	.replace("&lt;", "【").replace("&gt;", "】")
     	
    	
    	.replace("<div class=\"content\" data-collapse>", "").trim();
    	;
    	
    	return modem_release_content;
    	
    }
    
    static boolean InitRepoMetaDataX(File htmlFile, String htmlContent , Repo_Meta_Data metaData) {


    	
    	

    	String[] HeadBlockList = htmlContent.split("<h3 class=");

    	

    	
//        BuildInstructionList = new ArrayList<String>();
//        ManifestInfoList = new ArrayList<String>();
//        BuildSummaryList =  new ArrayList<String>();
//        BuildInstruction_MsiOnly_List =  new ArrayList<String>();
//        BuildInstruction_VendorOnly_List = new ArrayList<String>();
        
        
        
        // manifest的分支   // 9932
//      <th>MANIFEST BRANCH</th>
//      <td>prodp-mtk6771</td>
        String PRODUCT_NAME_TAG = "<th>BUILD TARGET</th>";
        String PRODUCT_NAME_RAW = "";
        boolean product_name_begin = false;
        boolean product_name_over = false;


        String XML_TAG = "<th>JOB NAME</th>";
        String XML_RAW = "";
        boolean xml_begin = false;

        String Manifest_TAG = "<th>MANIFEST BRANCH</th>";
        String Manifest_RAW = "";
        boolean manifest_begin = false;

        String GIT_TAG = ".git";
        String GIT_RAW = "";
//        boolean git_begin = false;


        String common_BuildingCommand_TAG = "motorola/build/bin/build_device.bash";
        ArrayList<String> buildCommandList = new ArrayList<String>();
        String BUILDING_COMMAND_RAW = "";

        if (htmlFile != null) {
            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(htmlFile), "utf-8"));
                String oldOneLine = "";
                int index = 1;
                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }
//                    System.out.println("第" + index + "行读取到的字符串:" + oldOneLine);

                    if (product_name_begin && !product_name_over) {
                        PRODUCT_NAME_RAW = oldOneLine;
                        if("<th>FILE NAME</th>".equals(oldOneLine.trim())) {
                        //   过滤 
                        }else if(PRODUCT_NAME_RAW.contains("retail")  || PRODUCT_NAME_RAW.contains("_g") ){
                            product_name_over = true;
                        }

                    } else if (xml_begin) {
                        XML_RAW = oldOneLine;

                    } else if (manifest_begin) {
                        Manifest_RAW = oldOneLine;
                    }


                    if (oldOneLine.contains(PRODUCT_NAME_TAG)) {
                        product_name_begin = true;
                    } else if (oldOneLine.contains(XML_TAG)) {
                        xml_begin = true;
                    } else if (oldOneLine.contains(Manifest_TAG)) {
                        manifest_begin = true;
                    } else if (oldOneLine.contains(GIT_TAG)) {
                        GIT_RAW = oldOneLine;
                    } else if (oldOneLine.contains(common_BuildingCommand_TAG)) {

                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW = oldOneLine.trim();
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace("<td>", "");
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace("</td>", "");
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace("<br />", "");
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace("<br/>", "");
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace("<br>", "").trim();
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace(" -jX", " -jX").trim();
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace(" -jX", " -jX").trim();
//                        path to MSI workspace
                        
                        // &lt
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace(";", "");
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace("path to MSI workspace", "");
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace("&lt", "");
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace("&gt", "");

                        
                        if(!BUILDING_COMMAND_RAW.startsWith(common_BuildingCommand_TAG)){
                            BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.substring(BUILDING_COMMAND_RAW.indexOf(common_BuildingCommand_TAG));
                        }
                        buildCommandList.add(BUILDING_COMMAND_RAW);

                    } else {
                        product_name_begin = false;
                        xml_begin = false;
                        manifest_begin = false;
                    }

                    index++;


                }
                curBR.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }


//        System.out.println("PRODUCT_NAME_RAW = "+ PRODUCT_NAME_RAW);
//        System.out.println("XML_RAW = "+ XML_RAW);
//        System.out.println("Manifest_RAW = "+ Manifest_RAW);
//        System.out.println("GIT_RAW = "+ GIT_RAW);


        if ("".equals(PRODUCT_NAME_RAW) || "".equals(XML_RAW) || "".equals(Manifest_RAW) || "".equals(GIT_RAW)) {
            System.out.println("解析文件无法解析出需要的 1.PRODUCT_NAME 2.GIT 3.Manifest 4.XML 数据， 请检查!");
            return false;
        }

        String mProductName = "";
        PRODUCT_NAME_RAW = PRODUCT_NAME_RAW.trim().replace("<td>", "");
        PRODUCT_NAME_RAW = PRODUCT_NAME_RAW.replace("</td>", "");
        PRODUCT_NAME_RAW = PRODUCT_NAME_RAW.replace("_retail", "").trim();
        PRODUCT_NAME_RAW = PRODUCT_NAME_RAW.replace("_g", "").trim();
        mProductName = PRODUCT_NAME_RAW;
        System.out.println("XXX mProductName = "+ mProductName);

        String mXml = "";
        XML_RAW = XML_RAW.trim().replace("<td>", "");
        XML_RAW = XML_RAW.replace("</td>", "");
        String xml1 = XML_RAW.substring(0, XML_RAW.indexOf("_test-keys")).trim();
        String xml2 = xml1.substring(xml1.lastIndexOf("_") + 1, xml1.length());
        mXml = xml2.trim() + ".xml";
        System.out.println("XXX mXml = "+ mXml);
        
        String mManifest = "";
        Manifest_RAW = Manifest_RAW.trim().replace("<td>", "");
        Manifest_RAW = Manifest_RAW.replace("</td>", "").trim();
        mManifest = Manifest_RAW.trim();
        System.out.println("XXX mManifest = "+ mManifest);

        String mGit = "";
        GIT_RAW = GIT_RAW.trim();
        String git1 = GIT_RAW.substring(0, GIT_RAW.trim().indexOf(".git")).trim();
        String git2 = git1.substring(git1.lastIndexOf("/") + 1).trim();
        mGit = git2 + ".git";
        // XXX mGit = mani.git
        System.out.println("GIT_RAW="+ GIT_RAW);
        System.out.println("git1="+ git1);
        System.out.println("git2="+ git2);
        System.out.println("mGit="+ mGit);
        System.out.println("XXX mGit = "+ mGit+"    GIT_RAW="+ GIT_RAW);


//        System.out.println("mProductName = "+ mProductName);
//        System.out.println("mXml = "+ mXml);
//        System.out.println("mManifest = "+ mManifest);
//        System.out.println("mGit = "+ mGit);

        if ("".equals(mProductName) || "".equals(mXml) || ".xml".equals(mXml) || "".equals(mManifest) || "".equals(mGit) || ".git".equals(mGit)) {
            System.out.println("解析文件无法解析出需要的 1.mProductName 2.mGit 3.mManifest 4.mXml 数据， 请检查!");
            return false;
        }

        metaData.manifestinfo_url_gitname = mGit;
        metaData.manifestinfo_branch = mManifest;
        metaData.buildsummary_build_target = mProductName;
        metaData.manifestinfo_xmlfile = mXml;
        metaData.BuildInstructionList.addAll(buildCommandList);


        for (int i = 0; i < buildCommandList.size(); i++) {
            System.out.println("Building命令" + i + ": " + buildCommandList.get(i));
        }
        return true;


//PRODUCT_NAME_RAW =       <td>lima_retail</td>
//XML_RAW =       <td>PMD29_lima-retail_userdebug_prodp-mtk6771_r-mt6771_test-keys_daily</td>
//Manifest_RAW =       <td>prodp-mtk6771</td>
//GIT_RAW = repo init -u ssh://gerrit.mot.com:29418/home/repo/dev/platform/android/platform/manifest/o.git -b refs/tags/PMD29.114-LIMA-SHA1 -m sha1_embedded_manifest.xml --repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git  --repo-branch=mot<br />


    }


    static void showCommitTip() {
        System.out.println(" ");
        System.out.println(" ");
        printSchema("【 提交commit命令 提示】");
        System.out.println("git push origin TEMP:refs/for/【当前分支| 通过 git gui ，gitk 查看提交分支】");
        System.out.println("示例1:  git push origin TEMP:refs/for/bp ");
        System.out.println("示例2:  git push origin TEMP:refs/for/bp-mtk ");
        System.out.println("示例3:  git push origin TEMP:refs/for/bq ");
        System.out.println("示例4:  git push origin TEMP:refs/for/br ");
        System.out.println("示例5:  git push origin TEMP:refs/for/br-mtk ");
        System.out.println("示例6:  git push origin TEMP:refs/for/bs ");
        System.out.println("示例7:  git push origin TEMP:refs/for/bs-mtk ");
        System.out.println("示例8:  git push origin TEMP:refs/for/prods-mtk ");
        System.out.println("示例9:  git push origin TEMP:refs/for/bt ");
        System.out.println("示例10:  git push origin TEMP:refs/for/bt-mtk ");

      
        printLine();
        printSchema("");
    }


    static void showPathTip(String productname , char versonChar ) {
        printSchema("【 特殊文件 Path 提示】  ");
        System.out.println("【1. framework/overlay/value/value.xml 资源覆盖版本路径】 ");
        System.out.println("zzfile_3.sh  ./device/moto/"+productname+"/overlay/frameworks/base/core/res/res/values");
        System.out.println("zzfile_3.sh  ./device/moto/"+productname+"/overlay_32/frameworks/base/core/res/res/values");
        System.out.println("【2. prop覆盖宏配置文件 】 ");
        System.out.println("zzfile_3.sh  ./vendor/moto/"+productname+"_oem_config/"+productname+"_retail/oem_reteu_"+productname+"/contents/oem.prop");
        System.out.println("【3. out/framework.jar 文件 】 ");
        System.out.println("zzfile_3.sh  ./out/target/product/"+productname+"/system/framework/framework.jar");
        System.out.println("【4. out/wifi-service.jar  】 ");
        System.out.println("zzfile_3.sh  ./out/target/product/"+productname+"/system/framework/wifi-service.jar");

        if(versonChar <= 'p'){
            System.out.println("【5. Settings.apk(P及以下) 文件 】 ");
            System.out.println("zzfile_3.sh  ./out/target/product/"+productname+"/system/priv-app/Settings/Settings.apk");

        }else{
            System.out.println("【5. Settings.apk(Q及以上) 文件 】 ");
            System.out.println("zzfile_3.sh  ./out/target/product/"+productname+"/product/priv-app/Settings/Settings.apk");
        }

        System.out.println("【6. framework/base/wifi/java/android/net/wifi 路径  wifimanager 】 ");
        System.out.println("zzfile_3.sh  ./frameworks/base/wifi/java/android/net/wifi");


        System.out.println("【7. framework/opt/wifi/service/wifi 路径 wifiservice 】 ");
        System.out.println("zzfile_3.sh  ./frameworks/opt/net/wifi/service/java/com/android/server/wifi");


        printLine();
        printSchema("");
    }


    static void showBuildingAppCommand() {
        printSchema("【 编译 apk jar so bin 命令】");
        //   System.out.println("--------------------------------------------------");
        System.out.println("【 Settings.apk " + input_productName + "_retail-userdebug" + "】");
        System.out.println(buildSettingsApk(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail"));
        printLine();
        System.out.println("【 framework.jar " + input_productName + "_retail-userdebug" + "】");
        System.out.println(buildFrameworkJar(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail"));


        printLine();
        System.out.println("【 wifi-services.jar " + input_productName + "_retail-userdebug" + "】");
        System.out.println(buildWifiServiceJar(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail"));

        printLine();
        System.out.println("【 bin/wpa_supplicant " + input_productName + "_retail-userdebug" + "】");
        System.out.println(buildWpaSupplicant(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail"));


        printLine();
        printSchema("");
    }


    static void showRebuildingCommand(Repo_Meta_Data metaData) {
        printSchema("【 rebuilding 再次编译 命令】");
        ArrayList<String> rebuildingList =   buildRebuildingCommand(metaData);
        for (int i = 0; i <rebuildingList.size() ; i++) {
            String command =  rebuildingList.get(i);
            if(command.contains("-E oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【 Rebuilding -E = OEM+SW】 】");
                System.out.println(command);
                System.out.println();

            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【 Rebuilding -e = OEM+Only】 】");
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【 Rebuilding retain_common】 】");
                System.out.println(command);
                System.out.println();
            }

        }
        printSchema("");
    }

    static void showRebuildingCommand() {
        printSchema("【 rebuilding 再次编译 命令】");
        //   System.out.println("--------------------------------------------------");
        System.out.println("【 rebuilding " + input_productName + "_retail" + " 【 retail_common 】】");
        System.out.println(buildRebuildingCommand(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail"));
        printLine();

        System.out.println("【rebuilding " + input_productName + "_retail" + " -e oem-image】 【 -e = OEM + Only 】");
        String rebuild_retail_e = buildRebuildingCommand_OEM(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail");
        rebuild_retail_e = rebuild_retail_e.replace(" -E oem-image"," -e oem-image");
        rebuild_retail_e = rebuild_retail_e.replace("oem.log","oem_only.log");
        System.out.println(rebuild_retail_e);
        printLine();


        System.out.println("【rebuilding " + input_productName + "_retail" + " -E oem-image】 【 -E = OEM + SW 】");
        String rebuild_retail_E =  buildRebuildingCommand_OEM(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail");
        rebuild_retail_E = rebuild_retail_E.replace("oem.log","OEM_SW.log");
        System.out.println(rebuild_retail_E);
        printLine();
        printSchema("");
    }


    static void showRepoInitFrameworkDexFlag() {
        printSchema("【repo init + dexfalg-frameworks.jar  命令】");

        String TAG = "repo start --all TEMP";
        String TAG_TARGET = "repo start --all TEMP  &&  zadddex_flag_C8.sh  &&  ztag_aosp_wifi_bt_gps_J6.sh  ";

        // System.out.println("--------------------------------------------------");
        printLine();
        System.out.println("【"+input_productName+"_retain_common + frameworks.jar】");
        String retain_common = buildInitAndCompileCommand(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail");
        retain_common = retain_common.replace(TAG,TAG_TARGET);
        System.out.println(retain_common);
        printLine();
        System.out.println();

        System.out.println("【"+input_productName+"_retail_e"+" -e = OEM-only  + frameworks.jar 】");
        String oem_retain = buildInitAndCompileCommand_oemimage(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail");
        oem_retain = oem_retain.replace("-E oem-image","-e oem-image");
        oem_retain = oem_retain.replace(TAG,TAG_TARGET);
        System.out.println(oem_retain);
        printLine();
        System.out.println();

        System.out.println("【"+input_productName+"_retail_E"+"  -E = OEM + SW + frameworks.jar 】");  // -E oem-image
        String retail_E = buildInitAndCompileCommand_oemimage(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail");
        retail_E = retail_E.replace(TAG,TAG_TARGET);
        System.out.println(retail_E);
        printLine();




        System.out.println( "【"+input_productName+"_factory_common】 ");
        System.out.println(buildInitAndCompileCommand(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "factory"));
        printLine();

        System.out.println("【"+input_productName+"_factory_e"+"  -e = OEM-only 】 ");
        String oem_factory = buildInitAndCompileCommand_oemimage(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "factory");
        oem_factory = oem_factory.replace("-E oem-image","-e oem-image");
        System.out.println(oem_factory);
        printLine();

        System.out.println("【"+input_productName+"__factory_E"+"  -E = OEM + SW 】 ");
        System.out.println(buildInitAndCompileCommand_oemimage(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "factory"));

        printSchema("");

    }

    static void showRepoInit() {
        printSchema("【repo init common 命令】");
        // System.out.println("--------------------------------------------------");
        printLine();
        System.out.println("【"+input_productName+"_retain_common】");
        System.out.println(buildInitAndCompileCommand(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail"));
        printLine();


        printLine();
        System.out.println("【"+input_productName+"_retain_common + F0_repo_init.sh 】");
        System.out.println(buildInitAndCompileCommand_Init_Sh(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail"));
        printLine();


        System.out.println("【"+input_productName+"_retail_e"+" -e = OEM-only 】");
        String oem_retain = buildInitAndCompileCommand_oemimage(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail");
        oem_retain = oem_retain.replace("-E oem-image","-e oem-image");
        System.out.println(oem_retain);
        printLine();

        System.out.println("【"+input_productName+"_retail_E"+"  -E = OEM + SW 】");  // -E oem-image
        System.out.println(buildInitAndCompileCommand_oemimage(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "retail"));
        printLine();




        System.out.println( "【"+input_productName+"_factory_common】 ");
        System.out.println(buildInitAndCompileCommand(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "factory"));
        printLine();

        System.out.println("【"+input_productName+"_factory_e"+"  -e = OEM-only 】 ");
        String oem_factory = buildInitAndCompileCommand_oemimage(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "factory");
        oem_factory = oem_factory.replace("-E oem-image","-e oem-image");
        System.out.println(oem_factory);
        printLine();

        System.out.println("【"+input_productName+"__factory_E"+"  -E = OEM + SW 】 ");
        System.out.println(buildInitAndCompileCommand_oemimage(input_gitRepoName, input_manifestBranchName, input_xml_manifestFileName, input_productName, "factory"));

        printSchema("");

    }


    // buildType  分为 "retail"   "   source /opt/conf/moto.conf  会把java改为50版本  很多jar包无法再50版本使用



    // buildType  分为 "retail"   "
    static ArrayList<String> buildInitAndCompileCommandWithMetaData(Repo_Meta_Data metaData) {
        ArrayList<String>  buildcompileList = new ArrayList<String>();
        String logname = metaData.buildsummary_build_target;
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";

        String str2_1 = "repo init -u ssh://gerrit.mot.com/home/repo/dev/platform/android/platform/manifest/" + metaData.manifestinfo_url_gitname.trim()  + " " + "--repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git ";
        String str2_2 = "--manifest-branch=" + metaData.manifestinfo_branch.trim() + "  ";
        String str2_3 = "-m " + metaData.manifestinfo_xmlfile.trim() + "  && ";
        String str3_1 = "gsync  && repo start --all TEMP  &&  ztag_aosp_wifi_bt_gps_J6.sh  &&  ";
        String str4_1_1 = "source /opt/conf/moto.conf && ";
        for (int i = 0; i < metaData.BuildInstructionList.size(); i++) {
            String buildList = metaData.BuildInstructionList.get(i);

            if(buildList.contains("-e oem-image")){
                logname = logname+"_OEM_ONLY";
            }else if(buildList.contains("-E oem-image")){
                logname = logname+"_OEM_SW";
            }else{
                logname = logname+"_COMMON";
            }
//            String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim()  + "  -g -jX ";
            String str4_1 =  buildList;
            String str5_1 = " 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";

            result = str1_1  + str2_1 + str2_2 + str2_3 + str3_1 +str4_1_1 + str4_1 + str5_1;

            buildcompileList.add(result);
            if(!buildList.contains("-e oem-image") && !buildList.contains("-E oem-image")){

                String result_new_1 = result.replace(str3_1,str3_1+" chmod 777 -R ../F0_repo_init.sh &&  ../F0_repo_init.sh   &&  ");
                result_new_1 = "cp  -fr ~/Desktop/zbin/F0_repo_init.sh  ../ && "+ result_new_1;
               buildcompileList.add(result_new_1);
//                System.out.println("result_new_1 = "+ result_new_1);
            }
            logname = metaData.buildsummary_build_target;  // 文件名称还原
        }
        // motorola/build/bin/build_device.bash -b nightly -p lima_retail -g -jX -e oem-image

       // System.out.println("buildcompileList.size() = " + buildcompileList.size());
      //  System.out.println("metaData.BuildingCommandList.size() = "+ metaData.BuildingCommandList.size());
        return buildcompileList;
    }

    static void showRepoInitAddCpCommand(Repo_Meta_Data metaData) {
        printSchema("【repo init cp-add 初始化复制命令】");
        // System.out.println("--------------------------------------------------");
        printLine();
        String TAG = "repo start --all TEMP";
        String TAG_TARGET = "repo start --all TEMP  &&  ztag_aosp_wifi_bt_gps_J6.sh &&  cp -fr ../zukgit1.txt  ./ &&  cp -fr ../../zukgit2.txt  ./  ";
        input_productName = metaData.buildsummary_build_target;
        input_gitRepoName = metaData.manifestinfo_url_gitname;
        input_manifestBranchName = metaData.manifestinfo_branch;
        input_xml_manifestFileName = metaData.manifestinfo_xmlfile;
        ArrayList<String> initandcompileCommand =  buildInitAndCompileCommandWithMetaData(metaData);
        for (int i = 0; i < initandcompileCommand.size(); i++) {
            String command = initandcompileCommand.get(i);
            command = command.replace(TAG,TAG_TARGET);
            if(command.contains("-E oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【-E = OEM+SW】 额外添加cp复制 】");
                System.out.println(command);
                System.out.println();
            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【-e = OEM+Only】额外添加cp复制  】");
                System.out.println(command);
                System.out.println();
            }else if(command.contains("F0_repo_init.sh")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【retain_common + F0_repo_init.sh 】 额外添加 可执行的 Project目录下的 F0_repo_init.sh 】");
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【retain_common】 额外添加cp复制 】");
                System.out.println(command);
                System.out.println();
            }
        }

        printSchema("");
    }


    static void showRepoInitFrameworkDexFlagAndAddLog(Repo_Meta_Data metaData) {
        printSchema("【repo init + dexfalg-frameworks.jar + logadd .java添加Log 命令】");
        // System.out.println("--------------------------------------------------");

        String TAG = "repo start --all TEMP";
        String TAG_TARGET = "repo start --all TEMP  &&  zadddex_flag_C8.sh   &&  ztag_aosp_wifi_bt_gps_J6.sh && zlog_add_B8.sh ./packages/apps/Settings  ";


        printLine();
        input_productName = metaData.buildsummary_build_target;
        input_gitRepoName = metaData.manifestinfo_url_gitname;
        input_manifestBranchName = metaData.manifestinfo_branch;
        input_xml_manifestFileName = metaData.manifestinfo_xmlfile;
        ArrayList<String> initandcompileCommand =  buildInitAndCompileCommandWithMetaData(metaData);
        for (int i = 0; i < initandcompileCommand.size(); i++) {
            String command = initandcompileCommand.get(i);
            if(command.contains("-E oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【-E = OEM+SW + frameworks.jar 】 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();

            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【-e = OEM+Only + frameworks.jar 】 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();
            }else if(command.contains("F0_repo_init.sh")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【retain_common + frameworks.jar  + F0_repo_init.sh 】 额外添加  Project目录下的 F0_repo_init.sh 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【retain_common + frameworks.jar 】 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();
            }
        }

        printSchema("");

    }
    static void showRepoInitFrameworkDexFlag(Repo_Meta_Data metaData) {

        printSchema("【repo init + dexfalg-frameworks.jar 命令】");
        // System.out.println("--------------------------------------------------");

        String TAG = "repo start --all TEMP";
        String TAG_TARGET = "repo start --all TEMP  &&  zadddex_flag_C8.sh   &&  ztag_aosp_wifi_bt_gps_J6.sh ";


        printLine();
        input_productName = metaData.buildsummary_build_target;
        input_gitRepoName = metaData.manifestinfo_url_gitname;
        input_manifestBranchName = metaData.manifestinfo_branch;
        input_xml_manifestFileName = metaData.manifestinfo_xmlfile;
        ArrayList<String> initandcompileCommand =  buildInitAndCompileCommandWithMetaData(metaData);
        for (int i = 0; i < initandcompileCommand.size(); i++) {
            String command = initandcompileCommand.get(i);
            if(command.contains("-E oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【-E = OEM+SW + frameworks.jar 】 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();

            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【-e = OEM+Only + frameworks.jar 】 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();
            }else if(command.contains("F0_repo_init.sh")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【retain_common + frameworks.jar + F0_repo_init.sh 】 额外添加 可执行的 Project目录下的 F0_repo_init.sh 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【retain_common + frameworks.jar 】 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();
            }
        }

        printSchema("");

    }

    static void showRepoInit(Repo_Meta_Data metaData) {
        printSchema("【repo init common 命令】");
        // System.out.println("--------------------------------------------------");
        printLine();
        input_productName = metaData.buildsummary_build_target;
        input_gitRepoName = metaData.manifestinfo_url_gitname;
        input_manifestBranchName = metaData.manifestinfo_branch;
        input_xml_manifestFileName = metaData.manifestinfo_xmlfile;
        ArrayList<String> initandcompileCommand =  buildInitAndCompileCommandWithMetaData(metaData);
        for (int i = 0; i < initandcompileCommand.size(); i++) {
            String command = initandcompileCommand.get(i);
//            System.out.println("commands["+i+"] = "+ initandcompileCommand);
            if(command.contains("-E oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【-E = OEM+SW】 】");
                System.out.println(command);
                System.out.println();
            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【-e = OEM+Only】 】");
                System.out.println(command);
                System.out.println();
            }else if(command.contains("F0_repo_init.sh")){
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【retain_common + F0_repo_init.sh 】 额外添加 可执行的 Project目录下的 F0_repo_init.sh 】");
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + input_productName + "_retail" + " 【retain_common】 】");
                System.out.println(command);
                System.out.println();
            }
        }

        printSchema("");

    }



    static void printLine() {
        System.out.println("———————————");
    }

    static void printSchema(String title) {
        if("".equals(title)){
            System.out.println();
            return;
        }
        System.out.println("════════════════════════════════════════════" + title + "════════════════════════════════════════════");
    }

    
    static String getSchema(String title) {
    	String mtitle = title;
        if("".equals(title)){
        	mtitle = "════════════════════════════════════════════" + title + "════════════════════════════════════════════";
           
            return mtitle;
        }
    return "════════════════════════════════════════════" + title + "════════════════════════════════════════════" ;
    }

    

    static String buildInitAndCompileCommand_Init_Sh(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String logname = productName + "_" + buildType;
        String result = "";
        String str1_1 = "cp  -fr ~/Desktop/zbin/F0_repo_init.sh  ../  && "+ "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str1_2 = "source /opt/conf/moto.conf && ";
        String str2_1 = "repo init -u ssh://gerrit.mot.com/home/repo/dev/platform/android/platform/manifest/" + cGitRepoName.trim() + " " + "--repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git ";
        String str2_2 = "--manifest-branch=" + cManifestBranchName.trim() + "  ";
        String str2_3 = "-m " + cXmlbranchName.trim() + "  && ";
        String str3_1 = "gsync  && repo start --all TEMP   &&  ztag_aosp_wifi_bt_gps_J6.sh  && chmod 777 -R ../F0_repo_init.sh && ../F0_repo_init.sh && ";
        String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -jX 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
        result = str1_1 + str1_2 + str2_1 + str2_2 + str2_3 + str3_1 + str4_1;
        return result;
    }

    static String buildInitAndCompileCommand(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String logname = productName + "_" + buildType;
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str1_2 = "source /opt/conf/moto.conf && ";
        String str2_1 = "repo init -u ssh://gerrit.mot.com/home/repo/dev/platform/android/platform/manifest/" + cGitRepoName.trim() + " " + "--repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git ";
        String str2_2 = "--manifest-branch=" + cManifestBranchName.trim() + "  ";
        String str2_3 = "-m " + cXmlbranchName.trim() + "  && ";
        String str3_1 = "gsync  && repo start --all TEMP   &&  ztag_aosp_wifi_bt_gps_J6.sh  && ";
        String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -jX 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
        result = str1_1 + str1_2 + str2_1 + str2_2 + str2_3 + str3_1 + str4_1;
        return result;
    }


    static String buildInitAndCompileCommand_oemimage(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String logname = productName + "_" + buildType + "_oem";
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str1_2 = "source /opt/conf/moto.conf && ";
        String str2_1 = "repo init -u ssh://gerrit.mot.com/home/repo/dev/platform/android/platform/manifest/" + cGitRepoName.trim() + " " + "--repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git ";
        String str2_2 = "--manifest-branch=" + cManifestBranchName.trim() + "  ";
        String str2_3 = "-m " + cXmlbranchName.trim() + "  && ";
        String str3_1 = "gsync  && repo start --all TEMP   &&  ztag_aosp_wifi_bt_gps_J6.sh && ";
        String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -jX -E oem-image 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";

        result = str1_1 + str1_2 + str2_1 + str2_2 + str2_3 + str3_1 + str4_1;
        return result;

    }


    static  ArrayList<String> buildRebuildingCommand(Repo_Meta_Data metaData) {
        ArrayList<String> reBuildingCommandList = new  ArrayList<String>();

        String logname = metaData.buildsummary_build_target ;

        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = "source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  &&  ";
//        String str3_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -jX"
        for (int i = 0; i < metaData.BuildInstructionList.size(); i++) {
            String buildingCommand = metaData.BuildInstructionList.get(i);

            if(buildingCommand.contains("-e oem-image")){
                logname = logname+"_OEM_ONLY";
            }else if(buildingCommand.contains("-E oem-image")){
                logname = logname+"_OEM_SW";
            }else{
                logname = logname+"_COMMON";
            }

            String str3_1 = buildingCommand;
            String str4_1 = " 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
            result = str1_1 + str2_1 + str3_1 + str4_1;
            logname = metaData.buildsummary_build_target ;
            reBuildingCommandList.add(result);
        }


        return reBuildingCommandList;
    }


    static String buildRebuildingCommand(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String logname = productName + "_" + buildType ;
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = "source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  &&  ";
        String str3_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -jX  2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
        result = str1_1 + str2_1 + str3_1;
        return result;
    }


    static String buildRebuildingCommand_OEM(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String logname = productName + "_" + buildType + "_oem";
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = "source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  &&  ";
        String str3_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -jX -E oem-image 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
        result = str1_1 + str2_1 + str3_1;
        return result;
    }


    static String buildSettingsApk(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";

        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "-userdebug && ";
        String str4_1 = " mmm packages/apps/Settings ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildSettingsLibs(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "-userdebug && ";
        String str4_1 = " mmm frameworks/base/packages/SettingsLib ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildFrameworkJar(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "-userdebug && ";
        String str4_1 = " mmm frameworks/base/ ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildWifiServiceJar(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "-userdebug && ";
        String str4_1 = " mmm frameworks/opt/net/wifi/service ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildWpaSupplicant(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "-userdebug && ";
        String str4_1 = " mmm external/wpa_supplicant_8 ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static boolean checkParamRight() {
        boolean flag = true;
        if ("".equals(input_gitRepoName) || "".equals(input_xml_manifestFileName) || "".equals(input_manifestBranchName) || "".equals(input_productName)) {
            flag = false;
        }
        return flag;
    }


    static String getTimeStampDesc() {
        //  String timeDesc = DateUtil.now();


        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("MMdd HHmm");
        String timeDesc = dateFormatYYYYMMDD.format(Calendar.getInstance().getTime());

        if (timeDesc.contains(" ")) {
            timeDesc = timeDesc.replaceAll(" ", "_");
        }
        if (timeDesc.contains("-")) {
            timeDesc = timeDesc.replaceAll("-", "_");
        }
        if (timeDesc.contains(":")) {
            timeDesc = timeDesc.replaceAll(":", "_");
        }

        return timeDesc.trim();
    }

    static void getDetailInput(ArrayList<String> paramList) {

        for (int i = 0; i < paramList.size(); i++) {
            String item = paramList.get(i).toLowerCase().trim();

            if (item.endsWith(".git")) {
                input_gitRepoName = item;
            } else if (item.endsWith(".xml")) {
                input_xml_manifestFileName = item;
            } else {
                input_manifestBranchName = item;
            }
        }


    }

    static void showtip() {
        System.out.println("当前需要 retail_userdebug  或者 g_userdebug 类型的 ReleaseNote文件! 才能增加  ");
        System.out.println("当前示例的格式为【四个参数】: zrepo_command_F0  q.git  bp  test.xml  sky   【打印已经添加过html的repo对应命令】");
        System.out.println("当前示例的格式为【一个参数】: zrepo_command_F0  xxxxReleaseNotes.html   【解析对应html文件并保存在 properties 中】");
        System.out.println("q.git   【git仓库(.git结尾) (ReleaseNote.html 搜索 .git )】    ");
        System.out.println("bp      【 MANIFEST BRANCH 分支 (ReleaseNote.html 搜索 MANIFEST BRANCH)】");
        System.out.println("test.xml【 具体的manifest xml 文件(必须.xml结尾) (ReleaseNote.html 搜索 test-keys 之前的字符串) 】");
        System.out.println("sky     【产品名称,必须在最后输入 ( ReleaseNote.html 搜索 BUILD TARGET )】");

        showProperiesMap(productKey2ValueList);

    }


	static String getTimeStamp() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}
	
    @SuppressWarnings("unchecked")
    static void showProperiesMap(Map<String,String> productName) {
        System.out.println("════════════════════" + "Properities数据表" + "════════════════════");
        Map.Entry<String, String> entry;
        String pre = "";
        if(curOS_TYPE == OS_TYPE.Windows ){
            pre = "zrepo_command_F0  ";
        }else if(curOS_TYPE == OS_TYPE.Linux){
            pre = "zrepo_command_F0.sh  ";
        }else if(curOS_TYPE == OS_TYPE.MacOS){
            pre = "zrepo_command_F0.sh  ";
        }else{
            pre = "zrepo_command_F0  ";
        }


        System.out.println("══════════Repo本地命令══════════");
        StringBuilder sb  = new StringBuilder();
        if (productName != null) {
            Iterator iterator = productName.entrySet().iterator();

            //     zrepo_command_F0  o.git mp r-sh2019.xml foles > foles.txt
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String key = entry.getKey();  //Map的Value
                String value = entry.getValue();  //Map的Value
                String fileName = value.substring(value.lastIndexOf(".xml")+".xml".length()).trim();
                String target = "    > " + fileName+".txt &&  ";
//                System.out.println(pre + value + target);
                sb.append(pre + value + target);
            }
        }
        String localGeneral = sb.toString().trim();
        while(localGeneral.endsWith("&&")){
            localGeneral = localGeneral.substring(0,localGeneral.length() -2);
        }
        System.out.println(localGeneral);
        
        System.out.println("══════════Prop删除数据操作══════════");
        if (productName != null) {
            Iterator iterator = productName.entrySet().iterator();
            //     zrepo_command_F0  o.git mp r-sh2019.xml foles > foles.txt
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String key = entry.getKey();  //Map的Value
                String value = entry.getValue();  //Map的Value
                String[] mStrArr =  value.split(" ");
                String lastname = null;
                if(mStrArr.length > 1) {
                     lastname = mStrArr[mStrArr.length-1];
                }
        
                System.out.println(pre + value +" -Delete ");
            }
        }
        
        
        System.out.println("══════════Prop当前数据情况══════════");
        if (productName != null) {
            Iterator iterator = productName.entrySet().iterator();
            //     zrepo_command_F0  o.git mp r-sh2019.xml foles > foles.txt
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String key = entry.getKey();  //Map的Value
                String value = entry.getValue();  //Map的Value
                String[] mStrArr =  value.split(" ");
                String lastname = null;
                if(mStrArr.length > 1) {
                     lastname = mStrArr[mStrArr.length-1];
                }
        
                System.out.println(pre + value +" > "+" "+ (lastname!=null?lastname:getTimeStamp())+".txt");
            }
        }
        System.out.println("══════════Tip══════════");
        
        System.out.println("zrepo_command_F0"+Cur_Batch_End+ "  init   ## 把当前文件 "+F0_Repo_Init_File_Origin.getName()+"初始化到本地！");
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
}