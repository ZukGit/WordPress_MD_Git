import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class F0_RepoCommand {


    // zrepo  解析一个 html文件  并把解析的内容放入到 properity 中
    // zrepo_command_F0   xx.git  xada   dada.xml  lima
    // -u ssh://gerrit.mot.com:29418/home/repo/dev/platform/android/platform/manifest/o.git
    //  -m  xxxxx.xml
    //   xxxx.git      mq     xxxx.xml


    //【lima】  o.git  prodp-mtk6771  r-mt6771.xml
    //  -u o.git  --manifest-branch=prodp-mtk6771   -m  r-mt6771.xml

    //【sofia】
    // q.git  mq     r-6125.xml
    //  -u q.git  --manifest-branch=mq   -m  r-6125.xml

    // 输入的参数列表
    static String productName = "";  // 产品名称
    static String xmlbranchName = "";  // .xml输入
    static String gitRepoName = "";  // .git 输入
    static String manifestBranchName = "";  // manifest的分支


    static String curDirPath = "";   // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径


    enum OS_TYPE{
        Windows,
        Linux,
        MacOS
    }
    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;



    static File F0_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F0.properties");
    static InputStream F0_Properties_InputStream;
    static OutputStream F0_Properties_OutputStream;
    static Properties F0_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();
    static Map<String, String> productKey2ValueList = new HashMap<String, String>();
    static ArrayList<Repo_Meta_Data> propRepoMetaList = new ArrayList<Repo_Meta_Data>();

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
        String productName = "";  // 产品名称
        String xmlbranchName = "";  // .xml输入
        String gitRepoName = "";  // .git 输入
        String manifestBranchName = "";  // manifest的分支
        //  String commonBuildingCommand = "";  // 普通的 创建项目的命令
        ArrayList<String> BuildingCommandList;  //  oem 等特殊创建创建项目的命令

        Repo_Meta_Data() {
            BuildingCommandList = new ArrayList<String>();
        }


        // o.git prodp-mtk6771 r-mt6771.xml lima
        void initWith4Params(String params) {
            String inputParam = params.trim();
            String[] results = inputParam.split(" ");
            if (results.length != 4) {
                System.out.println("initWith4Params 方法解析错误!  params =" + params);
                return;
            }
            this.gitRepoName = results[0];
            this.manifestBranchName = results[1];
            this.xmlbranchName = results[2];
            this.productName = results[3];

        }

        void initBuildingCommandWithMap(Map<String, String> propMap) {
            String TAG = this.productName + "_buildcommand";
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
            BuildingCommandList.addAll(commandList);
        }


    }

    static ArrayList<String> mKeyWordName = new ArrayList<>();
    static int DEFAULT_INPUT_NUM = 4;


    static void initSystemInfo(){
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if(osName.contains("window")){
            curOS_TYPE = OS_TYPE.Windows;
        }else if(osName.contains("linux")){
            curOS_TYPE = OS_TYPE.Linux;
        }else if(osName.contains("mac")){
            curOS_TYPE = OS_TYPE.MacOS;
        }
    }


    public static void main(String[] args) {
        initSystemInfo();
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

        RepoMetaListInitWithMap(propRepoMetaList, propKey2ValueList);

        if (mKeyWordName.size() == 0) {
            System.out.println("输入的Product参数数据为空!");
            showtip();
            return;
        } else if (mKeyWordName.size() == 1) {  //  传递一个 ReleaseNote文件进来进行解析
            String releaseNoteHtmlPath = mKeyWordName.get(0);

            if (!releaseNoteHtmlPath.endsWith("ReleaseNotes.html")) {
                System.out.println("当前输入的一个参数并不是  **ReleaseNotes.html 无法进行下一步解析 请检查重新执行!");
                showtip();
                return;
            }

            if ("".equals(curDirPath)) {
                System.out.println("当前输入的一个参数没有获得当前文件夹shell的路径,请检查,并重新执行!");
                showtip();
                return;
            }


            String htmlRealPath = buildReleaseNoteRealPath(curDirPath, releaseNoteHtmlPath);

            File htmlFile = new File(htmlRealPath);

            if (!htmlFile.exists()) {
                System.out.println("当前输入一个参数 *ReleaseNote.html文件不存在,请检查,并重新执行!");
                showtip();
                return;
            }

            Repo_Meta_Data metaData = new Repo_Meta_Data();

            if (!InitRepoMetaData(htmlFile, metaData)) {
                System.out.println("解析当前 ReleaseNote.html 文件 解析不到完整数据 导致异常 ,请检查,并重新执行!");
                showtip();
                return;
            }

            // 把  Repo_Meta_Data 的属性保存的 properities 中
            String productName = metaData.productName;
            String xmlbranchName = metaData.xmlbranchName;
            String gitRepoName = metaData.gitRepoName;
            String manifestBranchName = metaData.manifestBranchName;
            String key = productName.trim();
            String value = gitRepoName.trim() + " " + manifestBranchName.trim() + " " + xmlbranchName.trim() + " " + productName.trim();
            propKey2ValueList.put(key, value);
            productKey2ValueList.put(key,value);
            F0_Properties.setProperty(key, value);

            for (int i = 0; i < metaData.BuildingCommandList.size(); i++) {
                int index = i + 1;
                String buildKey = productName + "_buildcommand_" + index;
                String buildValue = metaData.BuildingCommandList.get(i);
                F0_Properties.setProperty(buildKey, buildValue);
            }
            setProperity();
            System.out.println("已保存当前解析的 ReleaseNote.html  详情调用如下:");
            showtip();
            return;
        } else if (mKeyWordName.size() != DEFAULT_INPUT_NUM) {
            System.out.println("输入的Product参数数据个数不对,当前为 " + mKeyWordName.size() + "个参数,实际需要" + DEFAULT_INPUT_NUM + "个!");
            showtip();
            return;
        }

        productName = mKeyWordName.get(mKeyWordName.size() - 1);  // 获取最后一个当做产品名称


        if ("".equals(productName)) {
            System.out.println("输入的Product产品名称为空!");
            showtip();
            return;
        }
        ArrayList<String> otherParamList = new ArrayList<String>();
        for (int i = 0; i < mKeyWordName.size() - 1; i++) {
            otherParamList.add(mKeyWordName.get(i));
        }

        getDetailInput(otherParamList);


        if (!checkParamRight()) {
            System.out.println("检测到当前输入参数存在空的情况，请重新输入!");
            showtip();
            return;
        }

        // 查看当前输入的产品名称是否有保存在 prop中
        Repo_Meta_Data selectRepoMetaData = checkRepoMetaInProp(productName);
        if (selectRepoMetaData == null) {   //  如果当前的 项目名称不存在 prop 中 那么打印 传统的数据
            System.out.println("该 ProductName="+productName+" 【不存在于 prop】 中将打印可能的命令(也许会执行失败)");
            showRepoInitFrameworkDexFlag();
            System.out.println();
            showRepoInit();
            System.out.println();
            showRebuildingCommand();
            System.out.println();
            showBuildingAppCommand();
            System.out.println();
            showCommitTip();
            showProperiesMap(productKey2ValueList);
//            showProperiesMap(propKey2ValueList);
        } else {   // 如果在当前的 prop中  拿到数据 去解析
            System.out.println("该 ProductName="+productName+" 【存在于 prop】 中将打印所有的命令");

            System.out.println();
            showRepoInitFrameworkDexFlag(selectRepoMetaData);
            System.out.println();
            showRepoInit(selectRepoMetaData);
            System.out.println();
            showRepoInitAddCpCommand(selectRepoMetaData);
            System.out.println();
            showRebuildingCommand(selectRepoMetaData);
            System.out.println();
            showBuildingAppCommand();
            System.out.println();
            showCommitTip();
            showProperiesMap(productKey2ValueList);
//            showProperiesMap(propKey2ValueList);
        }

    }


    static Repo_Meta_Data checkRepoMetaInProp(String cproductName) {
        Repo_Meta_Data selectRepoMeta = null;
        for (int i = 0; i < propRepoMetaList.size(); i++) {
            Repo_Meta_Data itemData = propRepoMetaList.get(i);
            if (itemData.productName.trim().equals(cproductName.trim())) {
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

//        NameKey1 = lima
//        NameKey1 = lima_buildcommand_1
//        NameKey1 = lima_buildcommand_2
//        NameKey1 = lima_buildcommand_3

        for (int i = 0; i < keyObjs.length; i++) {
            String keyItem = keyObjs[i] + "";
//            System.out.println("NameKey1 = "+ keyItem);
            //   _下划线  小于 = 等于号 , 说明等号在前面  那么 就标识这是一个项目的名称
            if (keyItem.trim().indexOf("_") < keyItem.trim().indexOf("=") || !keyItem.contains("_")) {
                nameList.add(keyItem);
//                System.out.println("NameKey2 = "+ keyItem);
                productKey2ValueList.put(keyItem,propkeyValueMap.get(keyItem));
            }
        }


        Object[] nameObjs = nameList.toArray();
        for (int i = 0; i < nameObjs.length; i++) {
            Repo_Meta_Data repoData = new Repo_Meta_Data();
            repoData.productName = nameObjs[i] + "";
            repoData.initWith4Params(propkeyValueMap.get(nameObjs[i]));
            repoData.initBuildingCommandWithMap(propkeyValueMap);
            propRepoMetaList.add(repoData);
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


    static boolean InitRepoMetaData(File htmlFile, Repo_Meta_Data metaData) {

//<th>BUILD TARGET</th>   产品名称!   // 9892:
//<td>lima_retail</td>


//<th>JOB NAME</th>      xml文件  _test-keys   // 9880
//<td>PMD29_lima-retail_userdebug_prodp-mtk6771_r-mt6771_test-keys_daily</td>

// .git 输入    // 10512
//repo init -u ssh://gerrit.mot.com:29418/home/repo/dev/platform/android/platform/manifest/o.git -b refs/tags/PMD29.114-LIMA-SHA1 -m sha1_embedded_manifest.xml --repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git  --repo-branch=mot<br />


        // manifest的分支   // 9932
//      <th>MANIFEST BRANCH</th>
//      <td>prodp-mtk6771</td>
        String PRODUCT_NAME_TAG = "<th>BUILD TARGET</th>";
        String PRODUCT_NAME_RAW = "";
        boolean product_name_begin = false;

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

                    if (product_name_begin) {
                        PRODUCT_NAME_RAW = oldOneLine;

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
                        BUILDING_COMMAND_RAW = BUILDING_COMMAND_RAW.replace(" -jX", " -j4").trim();
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
        mProductName = PRODUCT_NAME_RAW;

        String mXml = "";
        XML_RAW = XML_RAW.trim().replace("<td>", "");
        XML_RAW = XML_RAW.replace("</td>", "");
        String xml1 = XML_RAW.substring(0, XML_RAW.indexOf("_test-keys")).trim();
        String xml2 = xml1.substring(xml1.lastIndexOf("_") + 1, xml1.length());
        mXml = xml2.trim() + ".xml";

        String mManifest = "";
        Manifest_RAW = Manifest_RAW.trim().replace("<td>", "");
        Manifest_RAW = Manifest_RAW.replace("</td>", "").trim();
        mManifest = Manifest_RAW.trim();


        String mGit = "";
        String git1 = GIT_RAW.substring(0, GIT_RAW.trim().indexOf(".git")).trim();
        String git2 = git1.substring(git1.lastIndexOf("/") + 1).trim();
        mGit = git2 + ".git";


//        System.out.println("mProductName = "+ mProductName);
//        System.out.println("mXml = "+ mXml);
//        System.out.println("mManifest = "+ mManifest);
//        System.out.println("mGit = "+ mGit);

        if ("".equals(mProductName) || "".equals(mXml) || ".xml".equals(mXml) || "".equals(mManifest) || "".equals(mGit) || ".git".equals(mGit)) {
            System.out.println("解析文件无法解析出需要的 1.mProductName 2.mGit 3.mManifest 4.mXml 数据， 请检查!");
            return false;
        }

        metaData.gitRepoName = mGit;
        metaData.manifestBranchName = mManifest;
        metaData.productName = mProductName;
        metaData.xmlbranchName = mXml;
        metaData.BuildingCommandList.addAll(buildCommandList);


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
        printSchema("【 提交commit命令 提示】");
        System.out.println("git push origin TEMP:refs/for/【当前分支| 通过 git gui ，gitk 查看提交分支】");
        System.out.println("示例1:  git push origin TEMP:refs/for/bp ");
        System.out.println("示例2:  git push origin TEMP:refs/for/bq ");
        System.out.println("示例3:  git push origin TEMP:refs/for/bp-mtk ");
    }

    static void showBuildingAppCommand() {
        printSchema("【 编译 apk jar so bin 命令】");
        //   System.out.println("--------------------------------------------------");
        System.out.println("【 Settings.apk " + productName + "_retail-userdebug" + "】");
        System.out.println(buildSettingsApk(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail"));
        printLine();
        System.out.println("【 framework.jar " + productName + "_retail-userdebug" + "】");
        System.out.println(buildFrameworkJar(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail"));


        printLine();
        System.out.println("【 wifi-services.jar " + productName + "_retail-userdebug" + "】");
        System.out.println(buildWifiServiceJar(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail"));

        printLine();
        System.out.println("【 bin/wpa_supplicant " + productName + "_retail-userdebug" + "】");
        System.out.println(buildWpaSupplicant(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail"));


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
                System.out.println("【" + productName + "_retail" + " 【 Rebuilding -E = OEM+SW】 】");
                System.out.println(command);
                System.out.println();

            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + productName + "_retail" + " 【 Rebuilding -e = OEM+Only】 】");
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + productName + "_retail" + " 【 Rebuilding retain_common】 】");
                System.out.println(command);
                System.out.println();
            }

        }
        printSchema("");
    }

    static void showRebuildingCommand() {
        printSchema("【 rebuilding 再次编译 命令】");
        //   System.out.println("--------------------------------------------------");
        System.out.println("【 rebuilding " + productName + "_retail" + " 【 retail_common 】】");
        System.out.println(buildRebuildingCommand(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail"));
        printLine();

        System.out.println("【rebuilding " + productName + "_retail" + " -e oem-image】 【 -e = OEM + Only 】");
        String rebuild_retail_e = buildRebuildingCommand_OEM(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail");
        rebuild_retail_e = rebuild_retail_e.replace(" -E oem-image"," -e oem-image");
        rebuild_retail_e = rebuild_retail_e.replace("oem.log","oem_only.log");
        System.out.println(rebuild_retail_e);
        printLine();


        System.out.println("【rebuilding " + productName + "_retail" + " -E oem-image】 【 -E = OEM + SW 】");
        String rebuild_retail_E =  buildRebuildingCommand_OEM(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail");
        rebuild_retail_E = rebuild_retail_E.replace("oem.log","OEM_SW.log");
        System.out.println(rebuild_retail_E);
        printLine();
        printSchema("");
    }


    static void showRepoInitFrameworkDexFlag() {
        printSchema("【repo init + dexfalg-frameworks.jar  命令】");

        String TAG = "repo start --all TEMP";
        String TAG_TARGET = "repo start --all TEMP  &&  zadddex_flag_C8.sh  ";

        // System.out.println("--------------------------------------------------");
        printLine();
        System.out.println("【"+productName+"_retain_common + frameworks.jar】");
        String retain_common = buildInitAndCompileCommand(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail");
        retain_common = retain_common.replace(TAG,TAG_TARGET);
        System.out.println(retain_common);
        printLine();
        System.out.println();

        System.out.println("【"+productName+"_retail_e"+" -e = OEM-only  + frameworks.jar 】");
        String oem_retain = buildInitAndCompileCommand_oemimage(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail");
        oem_retain = oem_retain.replace("-E oem-image","-e oem-image");
        oem_retain = oem_retain.replace(TAG,TAG_TARGET);
        System.out.println(oem_retain);
        printLine();
        System.out.println();

        System.out.println("【"+productName+"_retail_E"+"  -E = OEM + SW + frameworks.jar 】");  // -E oem-image
        String retail_E = buildInitAndCompileCommand_oemimage(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail");
        retail_E = retail_E.replace(TAG,TAG_TARGET);
        System.out.println(retail_E);
        printLine();




        System.out.println( "【"+productName+"_factory_common】 ");
        System.out.println(buildInitAndCompileCommand(gitRepoName, manifestBranchName, xmlbranchName, productName, "factory"));
        printLine();

        System.out.println("【"+productName+"_factory_e"+"  -e = OEM-only 】 ");
        String oem_factory = buildInitAndCompileCommand_oemimage(gitRepoName, manifestBranchName, xmlbranchName, productName, "factory");
        oem_factory = oem_factory.replace("-E oem-image","-e oem-image");
        System.out.println(oem_factory);
        printLine();

        System.out.println("【"+productName+"__factory_E"+"  -E = OEM + SW 】 ");
        System.out.println(buildInitAndCompileCommand_oemimage(gitRepoName, manifestBranchName, xmlbranchName, productName, "factory"));

        printSchema("");

    }

    static void showRepoInit() {
        printSchema("【repo init common 命令】");
        // System.out.println("--------------------------------------------------");
        printLine();
        System.out.println("【"+productName+"_retain_common】");
        System.out.println(buildInitAndCompileCommand(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail"));
        printLine();

        System.out.println("【"+productName+"_retail_e"+" -e = OEM-only 】");
        String oem_retain = buildInitAndCompileCommand_oemimage(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail");
        oem_retain = oem_retain.replace("-E oem-image","-e oem-image");
        System.out.println(oem_retain);
        printLine();

        System.out.println("【"+productName+"_retail_E"+"  -E = OEM + SW 】");  // -E oem-image
        System.out.println(buildInitAndCompileCommand_oemimage(gitRepoName, manifestBranchName, xmlbranchName, productName, "retail"));
        printLine();




        System.out.println( "【"+productName+"_factory_common】 ");
        System.out.println(buildInitAndCompileCommand(gitRepoName, manifestBranchName, xmlbranchName, productName, "factory"));
        printLine();

        System.out.println("【"+productName+"_factory_e"+"  -e = OEM-only 】 ");
        String oem_factory = buildInitAndCompileCommand_oemimage(gitRepoName, manifestBranchName, xmlbranchName, productName, "factory");
        oem_factory = oem_factory.replace("-E oem-image","-e oem-image");
        System.out.println(oem_factory);
        printLine();

        System.out.println("【"+productName+"__factory_E"+"  -E = OEM + SW 】 ");
        System.out.println(buildInitAndCompileCommand_oemimage(gitRepoName, manifestBranchName, xmlbranchName, productName, "factory"));

        printSchema("");

    }


    // buildType  分为 "retail"   "
    static ArrayList<String> buildInitAndCompileCommandWithMetaData(Repo_Meta_Data metaData) {
        ArrayList<String>  buildcompileList = new ArrayList<String>();
        String logname = metaData.productName;
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str1_2 = "source /opt/conf/moto.conf && ";
        String str2_1 = "repo init -u ssh://gerrit.mot.com/home/repo/dev/platform/android/platform/manifest/" + metaData.gitRepoName.trim()  + " " + "--repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git ";
        String str2_2 = "--manifest-branch=" + metaData.manifestBranchName.trim() + "  ";
        String str2_3 = "-m " + metaData.xmlbranchName.trim() + "  && ";
        String str3_1 = "repo sync -j2  && repo start --all TEMP  && ";

        for (int i = 0; i < metaData.BuildingCommandList.size(); i++) {
            String buildList = metaData.BuildingCommandList.get(i);

            if(buildList.contains("-e oem-image")){
                logname = logname+"_OEM_ONLY";
            }else if(buildList.contains("-E oem-image")){
                logname = logname+"_OEM_SW";
            }else{
                logname = logname+"_COMMON";
            }
//            String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim()  + "  -g -j4 ";
            String str4_1 =  buildList;
            String str5_1 = " 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
            logname = metaData.productName;  // 文件名称还原
            result = str1_1 + str1_2 + str2_1 + str2_2 + str2_3 + str3_1 + str4_1 + str5_1;
            buildcompileList.add(result);
        }
        // motorola/build/bin/build_device.bash -b nightly -p lima_retail -g -jX -e oem-image

        return buildcompileList;
    }

    static void showRepoInitAddCpCommand(Repo_Meta_Data metaData) {
        printSchema("【repo init cp-add 初始化复制命令】");
        // System.out.println("--------------------------------------------------");
        printLine();
        String TAG = "repo start --all TEMP";
        String TAG_TARGET = "repo start --all TEMP && cp -fr ../zukgit1.txt  ./ &&  cp -fr ../../zukgit2.txt  ./  ";
        productName = metaData.productName;
        gitRepoName = metaData.gitRepoName;
        manifestBranchName = metaData.manifestBranchName;
        xmlbranchName = metaData.xmlbranchName;
        ArrayList<String> initandcompileCommand =  buildInitAndCompileCommandWithMetaData(metaData);
        for (int i = 0; i < initandcompileCommand.size(); i++) {
            String command = initandcompileCommand.get(i);
            command = command.replace(TAG,TAG_TARGET);
            if(command.contains("-E oem-image")){
                printLine();
                System.out.println("【" + productName + "_retail" + " 【-E = OEM+SW】 额外添加cp复制 】");
                System.out.println(command);
                System.out.println();
            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + productName + "_retail" + " 【-e = OEM+Only】额外添加cp复制  】");
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + productName + "_retail" + " 【retain_common】 额外添加cp复制 】");
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
        String TAG_TARGET = "repo start --all TEMP  &&  zadddex_flag_C8.sh  ";


        printLine();
        productName = metaData.productName;
        gitRepoName = metaData.gitRepoName;
        manifestBranchName = metaData.manifestBranchName;
        xmlbranchName = metaData.xmlbranchName;
        ArrayList<String> initandcompileCommand =  buildInitAndCompileCommandWithMetaData(metaData);
        for (int i = 0; i < initandcompileCommand.size(); i++) {
            String command = initandcompileCommand.get(i);
            if(command.contains("-E oem-image")){
                printLine();
                System.out.println("【" + productName + "_retail" + " 【-E = OEM+SW + frameworks.jar 】 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();

            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + productName + "_retail" + " 【-e = OEM+Only + frameworks.jar 】 】");
                command = command.replace(TAG,TAG_TARGET);
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + productName + "_retail" + " 【retain_common + frameworks.jar 】 】");
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
        productName = metaData.productName;
        gitRepoName = metaData.gitRepoName;
        manifestBranchName = metaData.manifestBranchName;
        xmlbranchName = metaData.xmlbranchName;
        ArrayList<String> initandcompileCommand =  buildInitAndCompileCommandWithMetaData(metaData);
        for (int i = 0; i < initandcompileCommand.size(); i++) {
            String command = initandcompileCommand.get(i);
            if(command.contains("-E oem-image")){
                printLine();
                System.out.println("【" + productName + "_retail" + " 【-E = OEM+SW】 】");
                System.out.println(command);
                System.out.println();
            }else if(command.contains("-e oem-image")){
                printLine();
                System.out.println("【" + productName + "_retail" + " 【-e = OEM+Only】 】");
                System.out.println(command);
                System.out.println();
            }else{
                printLine();
                System.out.println("【" + productName + "_retail" + " 【retain_common】 】");
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


    static String buildInitAndCompileCommand(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String logname = productName + "_" + buildType;
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str1_2 = "source /opt/conf/moto.conf && ";
        String str2_1 = "repo init -u ssh://gerrit.mot.com/home/repo/dev/platform/android/platform/manifest/" + cGitRepoName.trim() + " " + "--repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git ";
        String str2_2 = "--manifest-branch=" + cManifestBranchName.trim() + "  ";
        String str2_3 = "-m " + cXmlbranchName.trim() + "  && ";
        String str3_1 = "repo sync -j2  && repo start --all TEMP  && ";
        String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -j4 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
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
        String str3_1 = "repo sync -j2  && repo start --all TEMP  && ";
        String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -j4 -E oem-image 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";

        result = str1_1 + str1_2 + str2_1 + str2_2 + str2_3 + str3_1 + str4_1;
        return result;

    }


    static  ArrayList<String> buildRebuildingCommand(Repo_Meta_Data metaData) {
        ArrayList<String> reBuildingCommandList = new  ArrayList<String>();

        String logname = metaData.productName ;

        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = "source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  &&  ";
//        String str3_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -j4"
        for (int i = 0; i < metaData.BuildingCommandList.size(); i++) {
            String buildingCommand = metaData.BuildingCommandList.get(i);

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
            logname = metaData.productName ;
            reBuildingCommandList.add(result);
        }


        return reBuildingCommandList;
    }


    static String buildRebuildingCommand(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String logname = productName + "_" + buildType ;
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = "source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  &&  ";
        String str3_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -j4  2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
        result = str1_1 + str2_1 + str3_1;
        return result;
    }


    static String buildRebuildingCommand_OEM(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String logname = productName + "_" + buildType + "_oem";
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = "source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  &&  ";
        String str3_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() + "_" + buildType + "  -g -j4 -E oem-image 2>&1 | tee " + getTimeStampDesc() + "_" + logname + ".log";
        result = str1_1 + str2_1 + str3_1;
        return result;
    }


    static String buildSettingsApk(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";

        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "_retail-userdebug && ";
        String str4_1 = " mmm packages/apps/Settings ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildSettingsLibs(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "_retail-userdebug && ";
        String str4_1 = " mmm frameworks/base/packages/SettingsLib ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildFrameworkJar(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "_retail-userdebug && ";
        String str4_1 = " mmm frameworks/base/ ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildWifiServiceJar(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "_retail-userdebug && ";
        String str4_1 = " mmm frameworks/opt/net/wifi/service ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildWpaSupplicant(String cGitRepoName, String cManifestBranchName, String cXmlbranchName, String productName, String buildType) {
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 = " lunch " + productName + "_retail-userdebug && ";
        String str4_1 = " mmm external/wpa_supplicant_8 ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static boolean checkParamRight() {
        boolean flag = true;
        if ("".equals(gitRepoName) || "".equals(xmlbranchName) || "".equals(manifestBranchName) || "".equals(productName)) {
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
                gitRepoName = item;
            } else if (item.endsWith(".xml")) {
                xmlbranchName = item;
            } else {
                manifestBranchName = item;
            }
        }


    }

    static void showtip() {
        System.out.println("当前示例的格式为【四个参数】: zrepo_command_F0  q.git  bp  test.xml  sky   【打印对应命令】");
        System.out.println("当前示例的格式为【一个参数】: zrepo_command_F0  xxxxReleaseNotes.html   【解析对应html文件并保存在 properties 中】");
        System.out.println("q.git   【git仓库(.git结尾) (ReleaseNote.html 搜索 .git )】    ");
        System.out.println("bp      【 MANIFEST BRANCH 分支 (ReleaseNote.html 搜索 MANIFEST BRANCH)】");
        System.out.println("test.xml【 具体的manifest文件(必须.xml结尾) (ReleaseNote.html 搜索 test-keys 之前的字符串) 】");
        System.out.println("sky     【产品名称,必须在最后输入 ( ReleaseNote.html 搜索 BUILD TARGET )】");

        showProperiesMap(productKey2ValueList);

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
        System.out.println("══════════Prop数据情况══════════");
        if (productName != null) {
            Iterator iterator = productName.entrySet().iterator();
            //     zrepo_command_F0  o.git mp r-sh2019.xml foles > foles.txt
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String key = entry.getKey();  //Map的Value
                String value = entry.getValue();  //Map的Value
                System.out.println(pre + value);
            }
        }
    }
}
