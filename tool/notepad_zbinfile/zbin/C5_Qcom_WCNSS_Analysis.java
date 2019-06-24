import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class C5_Qcom_WCNSS_Analysis {
    static File current_juwuba_template_file = new File(System.getProperty("user.dir") + File.separator + "C5_juwuba_template.html");
    static String current_juwuba_html_template_content = "";

    static String curProjectPath = System.getProperty("user.dir");
    static String wlan_hdd_cfg_h_Path = curProjectPath + "/vendor/qcom/opensource/wlan/qcacld-3.0/core/hdd/inc/wlan_hdd_cfg.h";
    static String WCNSS_qcom_cfg_ini_Path = curProjectPath + "/device/qcom/wlan/talos/WCNSS_qcom_cfg.ini";

    static String config_begin_string = "Defines for all of the things we read from the configuration (registry)";
    static boolean isConfigBegin = false;
    static String struct_define_begin_string = "struct hdd_config {";
    static boolean isStruct_HddConfigBegin = false;

    public static void main(String[] args) {
        // 去除 可能的符号错误
        wlan_hdd_cfg_h_Path = wlan_hdd_cfg_h_Path.replace("/",File.separator);
        WCNSS_qcom_cfg_ini_Path= WCNSS_qcom_cfg_ini_Path.replace("/",File.separator);
        if(!new File(wlan_hdd_cfg_h_Path).exists()){

            wlan_hdd_cfg_h_Path = curProjectPath + File.separator + "wlan_hdd_cfg.h";
        }
        if(!new File(WCNSS_qcom_cfg_ini_Path).exists()){
            WCNSS_qcom_cfg_ini_Path = curProjectPath + File.separator + "WCNSS_qcom_cfg.ini";
        }


        //  是 #define 开头的话
//  #include
//  struct hdd_context;
//  #ifdef
//  #endif
//  /* Number of items that can be configured */
//
        //
        //
        File wlan_hdd_cfg_h_file = new File(wlan_hdd_cfg_h_Path);

        if (!wlan_hdd_cfg_h_file.exists()) {
            System.out.println("当前 wlan_hdd_cfg.h 文件不存在-请检查该文件！");
            return;
        }

        toAnalisys_wlan_hdd_cfg_h(wlan_hdd_cfg_h_file);


        File WCNSS_qcom_cfg_ini_File = new File(WCNSS_qcom_cfg_ini_Path);

        if (!WCNSS_qcom_cfg_ini_File.exists()) {
            System.out.println("当前 WCNSS_qcom_cfg.ini 文件不存在-请检查该文件！");
            return;
        }

        toAnalisys_WCNSS_qcom_cfg_ini(WCNSS_qcom_cfg_ini_File);


        //  过滤掉那些  mConfigName =  的那些配置项  放到新的数组中去
        ArrayList<Qcom_Ini_Config_Item> emptyNameConfig = new ArrayList<Qcom_Ini_Config_Item>();

        Iterator<Qcom_Ini_Config_Item> iter = mConfigItemList.iterator();
        while (iter.hasNext()) {
            Qcom_Ini_Config_Item item = iter.next();
            if (item == null || item.mConfigName.trim().isEmpty()) {
                emptyNameConfig.add(item);
                iter.remove();   //  从列表删除为空的那些 数据集合
            }
        }


        // 对config_name为空的 List 的集合
        for (int i = 0; i < emptyNameConfig.size(); i++) {

//            Line 151610: 空配置索引:  新名称: DP_TRACE_CONFIG_
//            Line 151637: 空配置索引:  新名称: DP_TRACE_CONFIG_DEFAULT_LIVE_MODE
//            Line 151668: 空配置索引:  新名称: DP_TRACE_CONFIG_DEFAULT_THRESH
//            Line 151703: 空配置索引:  新名称: DP_TRACE_CONFIG_DEFAULT_
            System.out.println("空配置索引:  新名称: " + emptyNameConfig.get(i).setNewConfigNameFromSamePart());
            // System.out.println(emptyNameConfig.get(i).toString());

            // 计算得到这些配置索引的公共相同的部分作为configName
        }
//  当前的configName 是否包含在  列表中的configName  ,
        //  对于 configName DP_TRACE_CONFIG_ 是否存在  如果存在

        ArrayList<Qcom_Ini_Config_Item> emptyNameConfigFix = new ArrayList<Qcom_Ini_Config_Item>();
        for (int i = 0; i < emptyNameConfig.size(); i++) {
            if (emptyNameConfigFix.size() == 0) {
                emptyNameConfigFix.add(emptyNameConfig.get(i));
            } else {   // 把当前的 对象和 集合里的对象作对比    测试是否包含相同的  configName
                // 对于 包含
                Qcom_Ini_Config_Item currentItem = emptyNameConfig.get(i);
                //  Qcom_Ini_Config_Item savedItem =
                Qcom_Ini_Config_Item megedItem = tofindSameInList(emptyNameConfigFix, currentItem);
                if (megedItem == null) {
                    continue;
                }
                if (megedItem != null) {
                    emptyNameConfigFix.add(megedItem);
                }


            }
        }


        // 对config_name为空的 List 的集合
        for (int i = 0; i < emptyNameConfigFix.size(); i++) {
//            Line 151610: 空配置索引:  新名称: DP_TRACE_CONFIG_
//            Line 151637: 空配置索引:  新名称: DP_TRACE_CONFIG_DEFAULT_LIVE_MODE
//            Line 151668: 空配置索引:  新名称: DP_TRACE_CONFIG_DEFAULT_THRESH
//            Line 151703: 空配置索引:  新名称: DP_TRACE_CONFIG_DEFAULT_
            System.out.println("修改好的 fixed 的 配置索引:  emptyNameConfigFix.size():  " + emptyNameConfigFix.size());
            System.out.println("修改好的 fixed 的 配置索引:  新名称:  " + emptyNameConfigFix.get(i).mConfigName);
            //   System.out.println(emptyNameConfigFix.get(i).toString());
            // 计算得到这些配置索引的公共相同的部分作为configName
            mConfigItemList.add(emptyNameConfigFix.get(i)); //  重新加入索引
        }


        ArrayList<Qcom_Ini_Config_Item> ConfigWithCommonName = new ArrayList<Qcom_Ini_Config_Item>();
        for (int i = 0; i < mConfigItemList.size(); i++) {
            // System.out.println("配置索引XX:"+ i);
            Qcom_Ini_Config_Item curItem = mConfigItemList.get(i);
            boolean isNameEmpty = curItem.isConfigNameEmpty;
            boolean isNameWithBlank = curItem.isContainBlank();

            if (!isNameEmpty && !isNameWithBlank) { // 名称非空     名称不包含空格的配置项
                ConfigWithCommonName.add(curItem);


            }
            System.out.println(mConfigItemList.get(i).toString());
        }

        ConfigWithCommonName.sort(configComparator);
        StringBuilder mdContetByOrderIndex = new StringBuilder();
        mdContetByOrderIndex.append("#  配置项Item集合 \n");
        System.out.println("\n======================= MD-Part-A-Begin======================\n");
        for (int i = 0; i < ConfigWithCommonName.size(); i++) {
//             System.out.println("配置索引zz:"+ i);
//            System.out.println(ConfigWithCommonName.get(i).toString());
            mdContetByOrderIndex.append(ConfigWithCommonName.get(i).buildMdContent(2));
        }


//        //   普通宏定义集合
        mdContetByOrderIndex.append("#  普通宏定义Macro集合 \n");
        for (int i = 0; i < emptyNameConfigFix.size(); i++) {

            mdContetByOrderIndex.append(emptyNameConfigFix.get(i).buildMdContent(2));
        }
        System.out.println(mdContetByOrderIndex.toString());
        System.out.println("\n======================= MD-Part-A-End======================\n");
        String markdownHtml = translateFromMarkdownToHtml(mdContetByOrderIndex.toString());
        System.out.println("\n=======================markdownHtml-Begin======================\n");
        System.out.println(markdownHtml);


        if (current_juwuba_template_file != null && current_juwuba_template_file.exists()) {
            current_juwuba_html_template_content = readTemplateStringFromFile(current_juwuba_template_file).toString();
            System.out.println("================================ current_juwuba_template_content   begin =================================");
            //  System.out.println(" current_juwuba_template_content  = " + current_juwuba_html_template_content);
            System.out.println("================================ current_juwuba_template_content   end =================================");
        } else {
            System.out.println(" current_juwuba_template_content  = null ");
        }


        String html_template_str = new String(current_juwuba_html_template_content);  // 生成 html 文件的  StringBuilder  先放入 html模板
        String outfile_html_name = getOutFileHTMLName();
        String htmlTitle = outfile_html_name.replace(".html", "");
        // tag 标题
        html_template_str = html_template_str.replace("ZukgitHeadTitleHoldPlace_1", outfile_html_name);
        html_template_str = html_template_str.replace("ZukgitContentTitleHoldPlace_2", htmlTitle + "文件集合");
        Date day = new Date();
        SimpleDateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        html_template_str = html_template_str.replace("ZukgitTimeStampHoldPlace_3", simpleDf.format(day));
        html_template_str = html_template_str.replace("ZukgitMDContentHoldPlace_4", markdownHtml);
        writeContentToFile(new File(outfile_html_name), html_template_str.toString());
    }

    static String getOutFileHTMLName() { // 输出文件的名称
        return "C5_WCNSS_qcom_cfg_Item.html";
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
                System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //  把 MD 格式的 翻译成  html 格式

    static StringBuilder readTemplateStringFromFile(File fileItem) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
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
        return sb;
    }

    static String translateFromMarkdownToHtml(String markdownCode) {

        //如果当前的 内容并不在 ```  precode里面的情况
        // #空格   <h1>  </h1>
        //##空格   <h2>  </h2>
        // ###空格   <h3>  </h3>
        // ####空格   <h4>  </h4>
        // #####空格   <h5>  </h5>
        // ######空格   <h6>  </h6>
        // #######空格   <h7>  </h7>
        // ########空格   <h8>  </h8>
        // #########空格   <h9>  </h9>


        // 每次遇到 ```  就把 标志位索引 0 + 1  ,  奇数 i%2 ==1 那么就在precode 外面  ,  偶数i%2 == 0  就在precode 里面
        // 当在 precode 以内  把数据的格式 整理下
        //1.   pre ```   =   "\n<pre><code class=\"\">\n"     end``` =</code></pre>

        // 2. 左右 括号的 切换
//        inprecodeStr = inprecodeStr.replace("<", "&lt;");
//        inprecodeStr = inprecodeStr.replace(">", "&gt;");

        StringBuilder sb = new StringBuilder();
        String htmlCode = "";
        htmlCode = markdownCode.replace("\n\n\n\n", "\n");
        htmlCode = htmlCode.replace("\n\n\n", "\n");
        htmlCode = htmlCode.replace("\n\n", "\n");
        htmlCode = htmlCode.replace("\n\n", "\n");
        String[] mdArr = htmlCode.split("\n");
        int codeIndex = 1;
        for (int i = 0; i < mdArr.length; i++) {
            String codeHtmlLine = new String(mdArr[i]);
            if (codeIndex % 2 == 0) {  // 偶数 在 preCode 里面
                if (mdArr[i].trim().equals("```")) {   //  遇到 end```
                    codeIndex++;
                    codeHtmlLine = "\n</code></pre>\n";
                } else {
                    codeHtmlLine = mdArr[i].replace("<", "&lt;");
                    codeHtmlLine = codeHtmlLine.replace(">", "&gt;");
                }

            } else { // 奇数 在 preCode外面

                if (mdArr[i].trim().equals("```")) { // 首次遇到  pre```
                    codeIndex++;
                    codeHtmlLine = "\n<pre><code class=\"\">\n";
                } else {
                    if (mdArr[i].trim().startsWith("######### ")) { // h9
                        codeHtmlLine = mdArr[i].trim().replace("######### ", "<h9>");
                        codeHtmlLine = codeHtmlLine + "</h9>";
                    } else if (mdArr[i].trim().startsWith("######## ")) { // h8
                        codeHtmlLine = mdArr[i].trim().replace("######## ", "<h8>");
                        codeHtmlLine = codeHtmlLine + "</h8>";
                    } else if (mdArr[i].trim().startsWith("####### ")) { // h7
                        codeHtmlLine = mdArr[i].trim().replace("####### ", "<h7>");
                        codeHtmlLine = codeHtmlLine + "</h7>";
                    } else if (mdArr[i].trim().startsWith("###### ")) { // h6
                        codeHtmlLine = mdArr[i].trim().replace("###### ", "<h6>");
                        codeHtmlLine = codeHtmlLine + "</h6>";
                    } else if (mdArr[i].trim().startsWith("##### ")) { // h5
                        codeHtmlLine = mdArr[i].trim().replace("##### ", "<h5>");
                        codeHtmlLine = codeHtmlLine + "</h5>";
                    } else if (mdArr[i].trim().startsWith("#### ")) { // h4
                        codeHtmlLine = mdArr[i].trim().replace("#### ", "<h4>");
                        codeHtmlLine = codeHtmlLine + "</h4>";
                    } else if (mdArr[i].trim().startsWith("### ")) { // h3
                        codeHtmlLine = mdArr[i].trim().replace("### ", "<h3>");
                        codeHtmlLine = codeHtmlLine + "</h3>";
                    } else if (mdArr[i].trim().startsWith("## ")) { // h2
                        codeHtmlLine = mdArr[i].trim().replace("## ", "<h2>");
                        codeHtmlLine = codeHtmlLine + "</h2>";
                    } else if (mdArr[i].trim().startsWith("# ")) { // h1
                        codeHtmlLine = mdArr[i].trim().replace("# ", "<h1>");
                        codeHtmlLine = codeHtmlLine + "</h1>";
                    }
                }

            }
            sb.append(codeHtmlLine + "\n");

            System.out.println("第" + i + "行: " + mdArr[i]);
        }
        return sb.toString();
    }

    static Comparator configComparator = new Comparator<Qcom_Ini_Config_Item>() {
        @Override
        public int compare(Qcom_Ini_Config_Item o1, Qcom_Ini_Config_Item o2) {
            int minLength = o1.mConfigName.length() > o2.mConfigName.length() ? o2.mConfigName.length() : o1.mConfigName.length();
            String name1_fix = o1.mConfigName;
            String name2_fix = o2.mConfigName;

            for (int i = 0; i < minLength; i++) {
//                    System.out.println("charIndex = "+i);
//                    System.out.println("name1_fix.toUpperCase() = "+name1_fix.toUpperCase() + "FilePath="+ o1.getAbsolutePath() +"    name1="+name1);
//                    System.out.println("name2_fix.toUpperCase() = "+name2_fix.toUpperCase()+ "FilePath="+ o2.getAbsolutePath()+  "    name2="+name2);
                char charName1 = name1_fix.toUpperCase().charAt(i);
                char charName2 = name2_fix.toUpperCase().charAt(i);

                if (charName1 == charName2) {
                    continue;
                }
                // ASCII码   A-65    Z-90  A 在前面
                if (charName1 > charName2) {
                    return 1;  // Z 在后面
                }
                if (charName1 < charName2) {
                    return -1;
                }
            }
            return 0;
        }
    };

    static Qcom_Ini_Config_Item tofindSameInList(ArrayList<Qcom_Ini_Config_Item> exptyConfigNameList_Fix, Qcom_Ini_Config_Item curConfigItem) {

        Qcom_Ini_Config_Item currentFindSameItem = null;
        Iterator<Qcom_Ini_Config_Item> iter = exptyConfigNameList_Fix.iterator();
        while (iter.hasNext()) {
            Qcom_Ini_Config_Item item = iter.next();
            String saveConfigName = item.mConfigName;
            String curConfigItemName = curConfigItem.mConfigName;
            String nameLong = "";
            String namShort = "";
            System.out.println("saveConfigName: " + saveConfigName + "  curConfigItemName =" + curConfigItemName);
            if (saveConfigName.length() > curConfigItemName.length()) {  // 保存名字较短的那个配置项
                nameLong = saveConfigName;
                namShort = curConfigItemName;
            } else {
                nameLong = curConfigItemName;
                namShort = saveConfigName;
            }
            System.out.println("nameLong1: " + nameLong + "  namShort2 =" + namShort);
            if (nameLong.contains(namShort)) {  // 如果 当前对比的两个项  短的名称 包含在长的名称中   说明他们可能是一组  把他们合成一个新的 Qcom_Ini_Config_Item
                currentFindSameItem = buildMergedConfig(item, curConfigItem);  //  两个对象拼接成一个对象
                System.out.println("nameLong: " + nameLong + "  namShort =" + namShort);
                // 把当前保存的对象丢弃掉；
                iter.remove();

            }  // 没有包含关系

            if (currentFindSameItem != null) {  // 有包含关系   即可添加到 列表
                return currentFindSameItem;
            }
        }

        if (currentFindSameItem == null) { // 如果没有 没有找到 当前的currentFindSameItem 标识 数组里没有这个 Item 需要 加入
            return curConfigItem;
        }
        return currentFindSameItem;

    }

    static Qcom_Ini_Config_Item buildMergedConfig(Qcom_Ini_Config_Item a, Qcom_Ini_Config_Item b) {
        Qcom_Ini_Config_Item currentReturnItem;
        String nameA = a.mConfigName;
        String nameB = b.mConfigName;
        if (nameA.length() < nameB.length()) {
            currentReturnItem = a;
            a.mergedEmptyConfig(b);
        } else {
            currentReturnItem = b;
            b.mergedEmptyConfig(a);
        }
        return currentReturnItem;
    }


    static void toAnalisys_WCNSS_qcom_cfg_ini(File iniFile) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(iniFile), "utf-8"));
            String lineContent = "";
            String currentLineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                currentLineContent = lineContent.trim();
                System.out.println("inicurrentLineContent = " + currentLineContent);
                if (currentLineContent.equals("END")) { // 当读取到END 字符串时 标识当前已经 读取结束  不需要继续读取
                    break;
                }


                toAnalysisIniLine(currentLineContent);

            }
            curBR.close();
        } catch (Exception e) {

            e.fillInStackTrace();
            e.printStackTrace();
            System.out.println("出现异常 e = " + e.fillInStackTrace());

        }
        return;

    }


    //   保存那些 开始重新定义的 Qcom_Ini_Config_Item 的内容
    static ArrayList<String> iniConfigStringList = new ArrayList<String>();

    // Key: g_enable_bcast_probe_rsp
    //Value:
    // # Flag to enable/disable FILS element in Probe Request
    //g_enable_bcast_probe_rsp=0

    static HashMap<String, String> configMap = new HashMap<String, String>();

    static void toAnalysisIniLine(String currentIniContent) {

        // ################ NAN feature set start ###################
        //  以 ### 开头的语句那么忽略
        if (currentIniContent.trim().startsWith("###")) {
            return;
        }
        //  以 #  号开头的是配置的注释   放入到数据集合中去
        if (currentIniContent.trim().startsWith("#")) {
            iniConfigStringList.add(currentIniContent + "\n");
            return;
        }

        // 如果当前的字符串 不是以 # 号开头  并且包含 等号的话说明是一个配置项     // enable_rtt_mac_randomization=1
        if (currentIniContent.trim().contains("=")) {
// 获取配置的名称
            String configName = currentIniContent.substring(0, currentIniContent.indexOf("=")).trim();
            String defineValueAtIni = currentIniContent.substring(currentIniContent.indexOf("=") + 1).trim();
            configMap.put(configName, currentIniContent.trim());  // 把 configName 与 configContent 组成一个MAP

            String configExplainAtIni = "";
            // 从iniConfigStringList 中 取出来  注释
            for (String explainItem : iniConfigStringList) {
                configExplainAtIni = configExplainAtIni + explainItem + "\n";
            }

            // 把对应的 Qcom_config 从 ArrayList 中 找出来
            Qcom_Ini_Config_Item qcomConfig = getConfigByName(configName);
            if (qcomConfig == null) {
                System.out.println("发现没有在定义在 .h文件但在 .ini 文件中有 定义的 配置项！");
                return;
            }
            qcomConfig.isDefineAtIni = true;
            qcomConfig.defineContentAtIni = currentIniContent;
            qcomConfig.defineValueAtIni = defineValueAtIni;
            qcomConfig.mExplainAtIni = configExplainAtIni;
            iniConfigStringList.clear();   // 对保存的注释清除 迎接下一个注释 配置项 的到来
        }

    }

    static void toAnalisys_wlan_hdd_cfg_h(File fileItem) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            String lineContent = "";
            String currentLineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                currentLineContent = lineContent.trim();
                System.out.println("currentLineContent = " + currentLineContent);
                if (isConfigBegin || currentLineContent.contains(config_begin_string)) {
                    isConfigBegin = true;
                }
                if (isStruct_HddConfigBegin || currentLineContent.contains(struct_define_begin_string)) {
                    isStruct_HddConfigBegin = true;
                }

                toAnalysisLine(currentLineContent, lineContent, isConfigBegin, isStruct_HddConfigBegin);

            }
            curBR.close();
        } catch (Exception e) {

            e.fillInStackTrace();
            e.printStackTrace();
            System.out.println("出现异常 e = " + e.fillInStackTrace());

        }

    }

    //   保存那些 开始重新定义的 Qcom_Ini_Config_Item 的内容
    static ArrayList<String> mCurrentConfigStringList = new ArrayList<String>();
    static boolean isNewConfigBegin = false;

    static void toAnalysisLine(String lineContent, String originLineContent, boolean isConfigBegin, boolean isStructBegin) {
        if (isConfigBegin == false && isStructBegin == false) { //  定义配置项   Qcom_Ini_Config_Macro
            System.out.println("普通宏定义阶段！");
            if (lineContent.startsWith("#if") || lineContent.startsWith("/*") ||
                    lineContent.startsWith("*/") || lineContent.startsWith("*") ||
                    lineContent.startsWith("#include") || lineContent.startsWith("struct hdd_context;") ||
                    lineContent.startsWith("#ifdef") || lineContent.startsWith("#endif") || lineContent.startsWith("#ifndef")
            ) {
                return;    // 在 定义宏的阶段  遇到注释 /* 就退出 检测下一行数据
            } else {
                //  遇到 define 的 #define CFG_ENABLE_DYNAMIC_RPS		(1 << 3)
// #define IPADDR_STRING_LENGTH   (16)
                if (lineContent.startsWith("#define")) {  // 当前的代码是一个定义的宏
                    Qcom_Ini_Config_Macro macro = new Qcom_Ini_Config_Macro();
                    macro.mMacroCommand = lineContent;
                    String contentFix = lineContent.replaceAll("    ", " ");
                    contentFix = contentFix.replaceAll("\t", " ");
                    String[] strArr = contentFix.split(" ");
                    String mMacroName = "";
                    if (strArr.length >= 2) {
                        mMacroName = strArr[1];
                    }
                    macro.mMacroName = mMacroName;
                    mMacroList.add(macro);
                    System.out.println("macro  = " + macro);
                }

            }


        } else if (isConfigBegin == true && isStructBegin == false) { //  定义宏  Qcom_Ini_Config_Macro
            System.out.println("配置定义阶段！");

            if (lineContent == null) {
                return;    //  当前的字符串为空
            }


            // #define CFG_QDF_TRACE_ENABLE_HTC_NAME     "qdf_trace_enable_htc"
            // 如果遇到了 #define  包含两个 ""  // 检查上一个字符串 是否也包含这样的情况
            // 如果包含  说明它一次性定义了 很多的 配置名称项

            // /* Allow unsigned Int Max for now */


            if (lineContent.startsWith("/*") && !lineContent.trim().endsWith("*/")) {  //  新的 配置文件的注释开始了  多行注释时是新的开始
                isNewConfigBegin = true;

                StringBuilder sb = new StringBuilder();

                for (String strItem : mCurrentConfigStringList) {
                    sb.append(strItem + "\n");
                }
                System.out.println("============2======Config Print Begin============= \n ");
                System.out.println("新的配置Item需要开始  旧的Config需要保存 = \n " + sb.toString());
                System.out.println("============2======Config Print End============= \n ");
                if (sb.toString().contains("#define")) {
                    Qcom_Ini_Config_Item newItem = new Qcom_Ini_Config_Item(sb.toString());
                    mConfigItemList.add(newItem);
                }
                mCurrentConfigStringList.clear();  // 把之前保存的数据 删除
                mCurrentConfigStringList.add(originLineContent);
            }

//            else if(lineContent.trim().startsWith("/*") && lineContent.trim().endsWith("*/")){ // 如果是单行注释
////1 .查看当前保存的字符串集合里面 mCurrentConfigStringList 是否包含 _DEFAULT  或者 _DEF 字样
//// 直接把  当前的当行注释加入到配置  也不行   马蛋  当行注释不要了   判断下一行去
//                return;
//
//            }

            else if (lineContent.startsWith("*")) {
                mCurrentConfigStringList.add(originLineContent);   //   以 * 开头的注释  加入内容列表中
            } else if (lineContent.startsWith("\"")) {  // 以 ” 号开头   多行宏定义
                //        #define CFG_OCE_ENABLE_RSSI_BASED_ASSOC_REJECT_NAME \
//        "oce_enable_rssi_assoc_reject"
                mCurrentConfigStringList.add(originLineContent);   //   以 * 开头的注释  加入内容列表中
            } else if (lineContent.startsWith("#define")) {   // 以  #define开头的值
                //   System.out.println("toJudgeAndAddSameConfig ！");

                // 当前的配置的最新那个String 是否是 包含的 "" 的那个  如果包含
                // 并且 当前待分析的 字符串 也是 包含"" 的那部分   说明 是一个新的配置项
//#define CFG_QDF_TRACE_ENABLE_HDD_NAME     "qdf_trace_enable_hdd"
//#define CFG_QDF_TRACE_ENABLE_SME_NAME     "qdf_trace_enable_sme"
                String lastestStr = mCurrentConfigStringList.get(mCurrentConfigStringList.size() - 1);
                int firstIndex_save = -1;
                int secondIndex_save = -1;
                if (lastestStr.trim().startsWith("#define")) {
                    firstIndex_save = lastestStr.indexOf("\"");
                    secondIndex_save = lastestStr.lastIndexOf("\"");
                }

                int firstIndex_cur = lineContent.indexOf("\"");
                int secondIndex_cur = lineContent.lastIndexOf("\"");

                boolean isDefineNameLastSaved = false;

                if (firstIndex_save != -1 && secondIndex_save != -1 && firstIndex_save < secondIndex_save) {
                    isDefineNameLastSaved = true;   // 上一个字符串保存的是 #define CFG_QDF_TRACE_ENABLE_HDD_NAME     "qdf_trace_enable_hdd" 这样的
                }

                boolean isDefineNameCurrent = false;
                if (firstIndex_cur != -1 && secondIndex_cur != -1 && firstIndex_cur < secondIndex_cur) {
                    isDefineNameCurrent = true;
                }
                System.out.println("ZlineContent = " + lineContent);
                System.out.println("firstIndex_cur = " + firstIndex_cur + "       secondIndex_cur =" + secondIndex_cur);
                System.out.println("ZlastestStr = " + lastestStr);
                System.out.println("firstIndex_save = " + firstIndex_save + "       secondIndex_save =" + secondIndex_save);
                System.out.println("  ZisDefineNameCurrent=" + isDefineNameCurrent + "       ZisDefineNameLastSaved=" + isDefineNameLastSaved);

//                ZlineContent = #define CFG_QDF_TRACE_ENABLE_HDD_NAME     "qdf_trace_enable_hdd"
//                ZlastestStr = #define CFG_QDF_TRACE_ENABLE_WDI_NAME     "qdf_trace_enable_wdi"
//                ZisDefineNameCurrent=false       ZisDefineNameLastSaved=false


//#define CFG_INTF0_MAC_ADDR_NAME                  "Intf0MacAddress"   是数值的对比
//#define CFG_INTF0_MAC_ADDR_MIN                   "000000000000"
//#define CFG_INTF0_MAC_ADDR_MAX                   "ffffffffffff"
//#define CFG_INTF0_MAC_ADDR_DEFAULT               "000AF58989FF"


//#define CFG_NEIGHBOR_SCAN_CHAN_LIST_NAME                      "gNeighborScanChannelList"
//#define CFG_NEIGHBOR_SCAN_CHAN_LIST_DEFAULT                   ""

                if (isDefineNameCurrent && isDefineNameLastSaved) {  // 如果 当前的字符串是定义 名称   并且上一次也是定义的 名称

                    String configNameSaved = lastestStr.substring(firstIndex_save + 1, secondIndex_save).trim();
                    String configNameCurrent = lineContent.substring(firstIndex_cur + 1, secondIndex_cur).trim();
                    if (configNameSaved.contains(",")) {
                        configNameSaved = configNameSaved.replace(",", "");
                        configNameSaved = configNameSaved.replace(" ", ""); // 把空格消除掉
                        configNameSaved = configNameSaved.replace("  ", ""); // 把空格消除掉
                    }
                    if (configNameCurrent.contains(",")) {
                        configNameCurrent = configNameCurrent.replace(",", "");
                        configNameCurrent = configNameCurrent.replace(" ", ""); // 把空格消除掉
                        configNameCurrent = configNameCurrent.replace("  ", ""); // 把空格消除掉
                    }

                    // 如果当前 保存的是 名称   而不是  A0F0 ，0000 这样的数值的话 那么它们是一组
                    //  如果两个 都是名称 那么才 创建新的对象
                    String regex = "^[A-Fa-f0-9]+$";
                    configNameCurrent = configNameCurrent.replace(" ", "");

                    boolean isDigitalCur = configNameCurrent.matches(regex);
                    boolean isDigitalSave = configNameSaved.matches(regex);
                    System.out.println("configNameCurrent = " + configNameCurrent + "       configNameSaved =" + configNameSaved);
                    System.out.println("isDigitalCur = " + isDigitalCur + "       isDigitalSave =" + isDigitalSave);
                    if (!isDigitalCur && !isDigitalSave && !configNameCurrent.isEmpty() && !configNameSaved.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        for (String strItem : mCurrentConfigStringList) {
                            sb.append(strItem + "\n");
                        }
                        Qcom_Ini_Config_Item newItem = new Qcom_Ini_Config_Item(sb.toString());
                        mConfigItemList.add(newItem);  // 把上次的保存为一个配置项

                        mCurrentConfigStringList.clear();  // 把之前保存的数据 删除
                        mCurrentConfigStringList.add(originLineContent);  // 重新开始下一个保存字符串的添加
                    } else {  // 其中一个配置的名称是  数字 那么 把它放入到 toJudgeAndAddSameConfig


                        toJudgeAndAddSameConfig(mCurrentConfigStringList, lineContent, originLineContent);
                    }

                } else {  // 如果当前定义的是一个名称         ||     上一次定义的是一个名称  ， 名称只有一个的情况
                    toJudgeAndAddSameConfig(mCurrentConfigStringList, lineContent, originLineContent);
                }

            } else if ((lineContent.endsWith("|\\") && lineContent.contains("_")) || (lineContent.endsWith(")\\") && lineContent.contains("_"))) {
                //  mCurrentConfigStringList.add(originLineContent);
                String lastest = mCurrentConfigStringList.get(mCurrentConfigStringList.size() - 1);
                lastest = lastest + "\n" + originLineContent;
                mCurrentConfigStringList.remove(mCurrentConfigStringList.size() - 1);
                mCurrentConfigStringList.add(lastest);

            }

            //   int firstIndex = contentWithOutExplain.indexOf("\"");
            //   int secondIndex = contentWithOutExplain.lastIndexOf("\"");

        } else if (isConfigBegin == true && isStructBegin == true) {   // 开始定义 结构体的那段代码段
            System.out.println("数据结构定义阶段！");

        }


    }

    static void toJudgeAndAddSameConfig(ArrayList<String> mCurContentList, String defineStr, String originLineContent) {

//   对于多行 宏定义的处理
//        #define CFG_OCE_ENABLE_RSSI_BASED_ASSOC_REJECT_NAME \
//        "oce_enable_rssi_assoc_reject"
//#define CFG_OCE_ENABLE_RSSI_BASED_ASSOC_REJECT_MIN     (0)
//#define CFG_OCE_ENABLE_RSSI_BASED_ASSOC_REJECT_MAX     (1)
//#define CFG_OCE_ENABLE_RSSI_BASED_ASSOC_REJECT_DEFAULT (1)


        // 拿到   CFG_ENABLE_CONNECTED_SCAN_DEFAULT    #define CFG_ENABLE_CONNECTED_SCAN_DEFAULT     (1)

        String contentFix = defineStr.replaceAll("    ", " ");
        contentFix = contentFix.replaceAll("\t", " ");
        String[] strArr = contentFix.split(" ");
        String mMacroName = "";
        if (strArr.length >= 2) {
            mMacroName = strArr[1];
        }
        if (mMacroName.isEmpty()) {  // 如果拿不到 宏的名称 那么就 退出  不操作了
            return;
        }
        //  把当前的 累计保存的 mCurContentList  去除  注释的那部分
        ArrayList<String> noExplainList = new ArrayList<String>();

        for (int i = 0; i < mCurContentList.size(); i++) {
            String itemStr = mCurContentList.get(i);
            if (itemStr.trim().startsWith("*") || itemStr.trim().startsWith("/*")) {
                continue;   // 把注释的那部分 去掉
            }
            noExplainList.add(itemStr);
        }
        System.out.println("Z4  noExplainList.size =" + noExplainList.size());
        // 如果去除掉注释后 剩下的字符串长度为 0 说明 配置才刚刚开始  添加这个 #define 到  列表
        if (noExplainList.size() == 0) {
            mCurContentList.add(originLineContent);

        } else {  // 存在 #define  XXXX      ，    和当前内容  #define
//#define CFG_ENABLE_CONNECTED_SCAN_NAME 【alredyInList】       "gEnableConnectedScan"
//#define CFG_ENABLE_CONNECTED_SCAN_MIN 【mMacroName】        (0)
            String lastestDefineStrInList = mCurContentList.get(mCurContentList.size() - 1);
            System.out.println("Z3  originLineContent =" + originLineContent);
            if (!lastestDefineStrInList.trim().startsWith("#define")) { //      如果最后一个字符串不是以 #define 开头的字符串
                mCurContentList.add(originLineContent);   // 把当前的 #define 加入到 内容列表
                System.out.println("Z2  originLineContent =" + originLineContent);
                return;
            } else {
                // 获取最后的那个字符串的 内容
                System.out.println("Z1");

                String lastestDefineStrInList_Fix = lastestDefineStrInList.replaceAll("    ", " ");
                lastestDefineStrInList_Fix = lastestDefineStrInList_Fix.replaceAll("\t", "");
                String[] endStrArr = lastestDefineStrInList_Fix.trim().split(" ");
                String endDefineNameStr = "";
                if (endStrArr.length >= 2) {
                    endDefineNameStr = endStrArr[1];
                }
                if (endDefineNameStr.isEmpty()) {  // 如果从 # define XXX  拿到的是空  那么退出  也不保存当前字符串
                    return;
                }

                String endDefineStr = endDefineNameStr;
                String currentDefineStr = mMacroName;

                //  判断这两个字符串的相似度   比较他们第一次不一样字符的索引位置 , 比上  长度较小的那么字符串的长度


                System.out.println("originLineContent = " + originLineContent);
                System.out.println("lastestDefineStrInList = " + lastestDefineStrInList);

                boolean isSameGroup = toCheckSameConfigGroup(currentDefineStr, endDefineStr, originLineContent);
                if (isDefineDiffatMiddle) {
                    return;  // 发现 那些 存在中间差异的项  继续下一个测试
                }
                System.out.println(" isSameGroup= " + isSameGroup + "      currentDefineStr = " + currentDefineStr + "          endDefineStr = " + endDefineStr);

                if (isSameGroup) {  // 两个 # define 是 同一组的
                    mCurContentList.add(originLineContent);

                } else {   //  有新的 ConfigItem 出现了

                    StringBuilder sb = new StringBuilder();

                    for (String strItem : mCurContentList) {
                        sb.append(strItem + "\n");
                    }
                    System.out.println("==================Config Print Begin============= \n ");
                    System.out.println("新的配置Item需要开始  旧的Config需要保存 = \n " + sb.toString());

                    Qcom_Ini_Config_Item newItem = new Qcom_Ini_Config_Item(sb.toString());
                    mConfigItemList.add(newItem);
                    System.out.println("==================Config Print End============= \n ");
                    mCurContentList.clear();
                    mCurContentList.add(originLineContent);


                }
                // 	Line 263:     currentDefineStr = CFG_ENABLE_NUD_TRACKING_DEFAULT          endDefineStr = CFG_ENABLE_NUD_TRACKING_NAME
                //	Line 311:     currentDefineStr = CFG_RTS_THRESHOLD_MIN          endDefineStr = CFG_RTS_THRESHOLD_NAME

                // 大多数情况下  currentDefineStr  和 endDefineStr 是一组的
                // 如果不是一组 说明  它们的定义之间 没有   /**/ 分割
                //   currentDefineStr = CFG_ENABLE_LTE_COEX          endDefineStr = CFG_WLAN_MCC_TO_SCC_SWITCH_MODE

            }

        }

    }

    static boolean isDefineDiffatMiddle = false;

    static boolean toCheckSameConfigGroup(String curMacroName, String endSavedMacroName, String originLineContent) {
        isDefineDiffatMiddle = false;
        boolean isSame = false;
        int shortLength = curMacroName.length() > endSavedMacroName.length() ? endSavedMacroName.length() : curMacroName.length();
        int sameIndex = 0;
        int sameNumInAll = 0;
        for (int i = 0; i < shortLength; i++) {
            char curChar = curMacroName.charAt(i);
            char endChar = endSavedMacroName.charAt(i);
            if (curChar == endChar) {
                sameIndex = i;
                continue;
            }

            if (curChar != endChar) {
                sameIndex = i;
                break;
            }

        }

        for (int i = 0; i < shortLength; i++) {
            char curChar = curMacroName.charAt(i);
            char endChar = endSavedMacroName.charAt(i);
            if (curChar == endChar) {
                sameNumInAll++;
            }
        }

// 以 _ 分割  相同的部分 大于 等于 50%
//#define CFG_ENABLE_PHY_REG  "gEnableFastPwrTransition"
//#define CFG_PHY_REG_DEFAULT (0x0)
//#define CFG_PHY_REG_MIN     (0x0)
//#define CFG_PHY_REG_MAX     (0x2)
// 单行  名称不匹配的问题

        String[] curArrStr = curMacroName.trim().split("_");
        String[] savedArrStr = endSavedMacroName.trim().split("_");
        int lengthLongArr = curArrStr.length > savedArrStr.length ? curArrStr.length : savedArrStr.length;
        int lengthShortArr = curArrStr.length > savedArrStr.length ? savedArrStr.length : curArrStr.length;
        boolean isCurMacNameLong = false;
        if (curArrStr.length > savedArrStr.length) {
            isCurMacNameLong = true;
        }
        int sameInArr = 0;
        if (isCurMacNameLong) {
            for (int i = 0; i < savedArrStr.length; i++) {   // 短的 列表在上

                String itemStringA = savedArrStr[i];
                for (int j = 0; j < curArrStr.length; j++) {
                    if (itemStringA.equals(curArrStr[j])) {
                        sameInArr++;
                        break;
                    }
                }
            }

        } else {
            for (int i = 0; i < curArrStr.length; i++) {   // 短的 列表在上

                String itemStringA = curArrStr[i];
                for (int j = 0; j < savedArrStr.length; j++) {
                    if (itemStringA.equals(savedArrStr[j])) {
                        sameInArr++;
                        break;
                    }
                }
            }


        }


        double sameInArrRate = (double) sameInArr / (double) lengthShortArr;
        double sameRate = (double) sameIndex / (double) shortLength;
        double sameinAllRate = (double) sameNumInAll / (double) shortLength;
        boolean isLastItemEndWithDefault = endSavedMacroName.trim().endsWith("_DEFAULT") || endSavedMacroName.trim().endsWith("_DEF");
        //  如果 Default  定义在 中间 也将 隔开


        if (!isLastItemEndWithDefault) {  // 最后一个Item不是 Default   并且 对比达到了 44%   对比度达到了 44% 就可以认为是一组
            if (sameRate > 0.44) {    // 相似的比例达到44成
                isSame = true;
            } else {  // 相似度 小于 0.44 的情况  而 总的 相似度 又大于 66的
                System.out.println(" AisLastItemEndWithDefault = " + isLastItemEndWithDefault);
                // 检查 ArrayList里有没有 DEF

                StringBuilder sb = new StringBuilder();

                for (String strItem : mCurrentConfigStringList) {
                    sb.append(strItem + "\n");
                }
                String currentContent = sb.toString();
                boolean hasDefault = currentContent.contains("_DEFAULT") || currentContent.contains("_DEF");

///* GPIO pin to toggle when capture tsf */
//#define CFG_SET_TSF_GPIO_PIN_NAME                  "gtsf_gpio_pin"
//#define CFG_SET_TSF_GPIO_PIN_MIN                   (0)
//#define CFG_SET_TSF_GPIO_PIN_MAX                   (254)
//#define TSF_GPIO_PIN_INVALID                       (255)     【这里太嚣张了】
//#define CFG_SET_TSF_GPIO_PIN_DEFAULT               (TSF_GPIO_PIN_INVALID)
                if (sameRate == 0.0 && !hasDefault) {
                    int size = mCurrentConfigStringList.size();
                    if (size > 2) {
                        mCurrentConfigStringList.add(size - 2, originLineContent);  // 把当前这个 嚣张的东西放到 倒数第二的位置
                        System.out.println(" 嚣张1 = " + originLineContent);
                        isDefineDiffatMiddle = true;
                    }
                    System.out.println(" 嚣张2 = " + originLineContent);
                }


                if (sameRate < 0.44 && sameinAllRate > 0.66) {
                    isSame = true;

                } else if (sameInArrRate >= 0.5) {
                    isSame = true;
                }
                System.out.println(" sameInArrRate = " + sameInArrRate + "  curMacroName =" + curMacroName + " endSavedMacroName" + endSavedMacroName);


            }


        }

        if (isLastItemEndWithDefault) {         // 如果最后的对比是 Default     那么判断 对比度  只有对比度大于 60% 的才会被认为是一组
            if (sameRate > 0.60) {    // 相似的比例达到6成
                isSame = true;
            }

        }
        System.out.println("isLastItemEndWithDefault 最后的比较是否是Default 如果是 那么不用比较相似度 新configItem的开始 ");
        System.out.println("isLastItemEndWithDefault = " + isLastItemEndWithDefault);
        System.out.println("curMacroName = " + curMacroName + "   endSavedMacroName = " + endSavedMacroName);
        System.out.println("sameIndex = " + sameIndex + "   shortLength = " + shortLength + "    sameRate =" + sameRate);
        return isSame;
    }

    static ArrayList<Qcom_Ini_Config_Macro> mMacroList = new ArrayList<Qcom_Ini_Config_Macro>();

    static class Qcom_Ini_Config_Macro {
        //  分界线  /* Defines for all of the things we read from the configuration (registry). */
//  在该语句之后的所有定义都是 Qcom_Ini_Config_Item   之前的话 都是 宏
        String mMacroName;   // CFG_ENABLE_RX_THREAD 配置的注释  有些情况下没有
        String mMacroCommand;  // #define CFG_ENABLE_RX_THREAD		(1 << 0)

        @Override
        public String toString() {
            return "mMacroName = " + mMacroName + "\n   mMacroCommand =" + mMacroCommand;
        }
    }

    static ArrayList<Qcom_Ini_Config_Item> mConfigItemList = new ArrayList<Qcom_Ini_Config_Item>();


    static Qcom_Ini_Config_Item getConfigByName(String configName) {
        Qcom_Ini_Config_Item currentConfig = null;
        for (Qcom_Ini_Config_Item configItem : mConfigItemList) {
            if (configItem.mConfigName.equals(configName)) {
                currentConfig = configItem;
            }

        }
        return currentConfig;
    }

    static class Qcom_Ini_Config_Item {
        String mExplain;   // 配置的注释  有些情况下没有
        String contentWithOutExplain;  // 没有注释的那一部分
        String mSupported_Feature;  // 配置中支持的特性  为"" 或者null 标识注释没有 说明

        String mConfigName;  // 配置的名称 ""   "gEnableConnectedScan"

        String allContent;


        String defaultRowValue;  //  如果 没有ini配置  那么默认的值为什么
        String defaultConfigContent;
        ArrayList<String> mDefineConfigStringList;
/*#define CFG_ENABLE_CONNECTED_SCAN_NAME        "gEnableConnectedScan"
#define CFG_ENABLE_CONNECTED_SCAN_MIN         (0)
#define CFG_ENABLE_CONNECTED_SCAN_MAX         (1)
#define CFG_ENABLE_CONNECTED_SCAN_DEFAULT     (1)*/

        boolean isDefineAtIni = false;   //  在  init 文件中是否有配置
        String defineValueAtIni;    //  在 ini 文件 总配置的值
        String defineContentAtIni;   // 在  ini 文件中配置的原始的语句

        String mExplainAtIni;  // 在 ini文件中 该配置对应的注释

        boolean isConfigNameEmpty = false;

        Qcom_Ini_Config_Item(String allContentString) {
            allContent = allContentString;
            trytoGetDataFromAllContent(allContentString);
        }


        String setNewConfigNameFromSamePart() {
            String newConfigName = "";
            isConfigNameEmpty = true;  // 调用到这个方法就会把 configName 设置为 true
            if (mDefineConfigStringList == null || mDefineConfigStringList.size() == 0) {
                System.out.println("znewConfigName0 = " + newConfigName);
                return "";     //  设置新的配置名称  并返回这个名称
            }

            if (mDefineConfigStringList.size() == 1) {
                String defineContent = mDefineConfigStringList.get(0).trim();
                // #define  OFFLOAD_11K_BITMASK_NEIGHBOR_REPORT_REQUEST  0x1
                String contentFix = defineContent.replaceAll("    ", " ");
                contentFix = contentFix.replaceAll("\t", " ");
                contentFix = contentFix.replaceAll("  ", " ");
                contentFix = contentFix.replaceAll("  ", " ");
                String[] strArr = contentFix.trim().split(" ");
                String mMacroName = "";
                if (strArr.length >= 2) {
                    mMacroName = strArr[1];
                }
                mConfigName = mMacroName;
                newConfigName = mMacroName;
                System.out.println("znewConfigName1 = " + newConfigName + "    strArr =" + Arrays.toString(strArr));
                return newConfigName;

            }

            if (mDefineConfigStringList.size() >= 2) {
                String defineContent1 = mDefineConfigStringList.get(0).trim();
                String defineContent2 = mDefineConfigStringList.get(1).trim();

                String contentFix = defineContent1.replaceAll("    ", " ");
                contentFix = contentFix.replaceAll("\t", " ");
                contentFix = contentFix.replaceAll("  ", " ");
                contentFix = contentFix.replaceAll("  ", " ");
                String[] strArr = contentFix.trim().split(" ");
                String mMacroName1 = "";
                if (strArr.length >= 2) {
                    mMacroName1 = strArr[1];
                }
                String name1 = mMacroName1;


                String contentFix2 = defineContent2.replaceAll("    ", " ");
                contentFix2 = contentFix2.replaceAll("\t", " ");
                contentFix2 = contentFix2.replaceAll("  ", " ");
                contentFix2 = contentFix2.replaceAll("  ", " ");
                String[] strArr2 = contentFix2.trim().split(" ");
                String mMacroName2 = "";
                if (strArr2.length >= 2) {
                    mMacroName2 = strArr2[1];
                }

                String name2 = mMacroName2;


                int shortLength = name1.length() > name2.length() ? name2.length() : name1.length();
                int sameIndex = 0;
                int sameNumInAll = 0;
                for (int i = 0; i < shortLength; i++) {
                    char curChar = name1.charAt(i);
                    char endChar = name2.charAt(i);
                    if (curChar == endChar) {
                        sameIndex = i;
                        continue;
                    }

                    if (curChar != endChar) {
                        sameIndex = i;
                        break;
                    }

                }

                System.out.println("   name1=" + name1 + "   Arrays.toString(strArr1)) =" + Arrays.toString(strArr));
                System.out.println("   name2=" + name2 + "   Arrays.toString(strArr2)) =" + Arrays.toString(strArr2));

                System.out.println("znewConfigName2 = " + newConfigName + "    sameIndex =" + sameIndex + "    name1= " + name1 + "   name2=" + name2);
                String samePart = name1.substring(0, sameIndex);
                newConfigName = samePart;  // 返回 新的配置的名称
            }
            //    System.out.println("znewConfigName2 = "+ newConfigName  );
            mConfigName = newConfigName;
            return newConfigName;
        }

        void trytoGetDataFromAllContent(String allContentString) {
            if (allContentString.trim().startsWith("/*") && allContentString.contains("/*") && allContentString.contains("*/")) {
                mExplain = allContentString.substring(allContentString.indexOf("/*"), allContentString.indexOf("*/") + 2);
                contentWithOutExplain = allContentString.substring(allContentString.indexOf("*/") + 2);
            } else {
                mExplain = "";   // 当前的配置中没有注释
                contentWithOutExplain = allContentString;  // 当没有注释的时候  所有内容就是 contentWithOutExplain
            }

            if (!mExplain.isEmpty()) {
                // 从注释中找到  mSupported_Feature
                if (mExplain.contains("Supported Feature:")) {
                    String featureToEnd = mExplain.substring(mExplain.indexOf("Supported Feature:") + "Supported Feature:".length());
                    mSupported_Feature = featureToEnd.substring(0, featureToEnd.indexOf("*"));  // 下一个 * 的位置
                    mSupported_Feature = mSupported_Feature.trim().replace("\n", "");
                } else {
                    mSupported_Feature = "";  // 注释中并不包含 Supported_Feature
                }
            } else {
                mSupported_Feature = "";  // 不包含注释 也就 不包含 mSupported_Feature
            }


            int firstIndex = contentWithOutExplain.indexOf("\"");
            int secondIndex = contentWithOutExplain.lastIndexOf("\"");
            System.out.println("contentWithOutExplain2 = " + contentWithOutExplain);
            System.out.println("mConfigName1 = " + mConfigName + " firstIndex = " + firstIndex + "    secondIndex = " + secondIndex);

            if (firstIndex != -1 && secondIndex != -1 && (firstIndex < secondIndex)) {
                mConfigName = contentWithOutExplain.substring(firstIndex + 1, secondIndex).trim();
                while (mConfigName.trim().contains("\"")) {
                    mConfigName = mConfigName.replace("\"", "");
                }

                while (mConfigName.trim().contains("\"")) {
                    mConfigName = mConfigName.replace("\"", "");
                }
                // mConfigName0 = gIbssBssid"
                System.out.println("mConfigName0 = " + mConfigName);
                // 在这里判断上一个字符串是否也是包含 两个 "" 引号的 字符串  如果是 说明又是一个新的配置的开始
            } else {
                mConfigName = "";   // 配置的名称 没有 查找到
                System.out.println("mConfigName2 = " + mConfigName);
            }

            mDefineConfigStringList = new ArrayList<String>();
            if (contentWithOutExplain.contains("#define")) {

                String[] macroArr = contentWithOutExplain.split("#define");
                for (int i = 0; i < macroArr.length; i++) {
                    if (!macroArr[i].trim().isEmpty()) {
                        mDefineConfigStringList.add("#define " + macroArr[i]);
                        if (macroArr[i].contains("_DEFAULT")) {
                            defaultConfigContent = "#define " + macroArr[i];
                            defaultRowValue = macroArr[i].substring(macroArr[i].indexOf("_DEFAULT") + "_DEFAULT".length()).trim();
                        } else if (macroArr[i].contains("_DEF")) { // 有些配置项没有定义DEFAULT  但 定义了 _DEF
// #define CFG_NUM_TX_CHAINS_11b_DEF       0x02492492
                            defaultConfigContent = "#define " + macroArr[i];
                            defaultRowValue = macroArr[i].substring(macroArr[i].indexOf("_DEF") + "_DEF".length()).trim();


                            // #define OFFLOAD_11K_BITMASK_NEIGHBOR_REPORT_REQUEST  0x1   没有定义相似的 那么它就是一个普通的宏
                            // 这样的配置文件  defaultRowValue = null

//  尼玛 这样 中间有定义 /**/ 注释的配置项  这是受不了 // 这样的配置文件  defaultRowValue = null
//#define CFG_IBSS_PS_WARMUP_TIME_NAME               "gIbssPsWarmupTime"
//#define CFG_IBSS_PS_WARMUP_TIME_MIN                (0)
///* Allow unsigned Int Max for now */
//#define CFG_IBSS_PS_WARMUP_TIME_MAX                (65535)
//#define CFG_IBSS_PS_WARMUP_TIME_DEFAULT            (0)

//  单独 定义的名称   中间又有 /**/  间隔  隔开来  怎么判断这个注释 是 中间的  还是一个配置开始的
                            // 注释 完全在 1行   并且之前没有 DEFAULT的 字符   就说明  还没结束?
//#define CFG_QDF_TRACE_ENABLE_WDI_NAME     "qdf_trace_enable_wdi"
//#define CFG_QDF_TRACE_ENABLE_HDD_NAME     "qdf_trace_enable_hdd"
//#define CFG_QDF_TRACE_ENABLE_SME_NAME     "qdf_trace_enable_sme"


//  不同
//sameIndex = 8   shortLength = 23    sameRate =0.34782608695652173
//isSameGroup= false      currentDefineStr = CFG_INTF3_MAC_ADDR_NAME          endDefineStr = CFG_INTF2_MAC_ADDR_DEFAULT


                            //  相同
//sameIndex = 8   shortLength = 18    sameRate =0.4444444444444444
//isSameGroup= false      currentDefineStr = CFG_WOW_ENABLE_MIN          endDefineStr = CFG_WOW_STATUS_NAME

                            //   不同   但是 endDefineStr 包含有  _DEFAULT
//sameIndex = 20   shortLength = 41    sameRate =0.4878048780487805
//isSameGroup= false      currentDefineStr = CFG_BAD_PEER_TX_CTL_PCT_LEVEL_IEEEAG_NAME          endDefineStr = CFG_BAD_PEER_TX_CTL_DELTA_LEVEL_IEEEAG_DEFAULT

// 不同     但是 endDefineStr 包含有  _DEFAULT
//sameIndex = 20   shortLength = 40    sameRate =0.5
//isSameGroup= false      currentDefineStr = CFG_BAD_PEER_TX_CTL_PCT_LEVEL_IEEEN_NAME          endDefineStr = CFG_BAD_PEER_TX_CTL_DELTA_LEVEL_IEEEN_DEFAULT


// 相似度  没有达到 60%的比例
//#define CFG_WOW_STATUS_NAME                    "gEnableWoW"
//#define CFG_WOW_ENABLE_MIN                     (0)
//#define CFG_WOW_ENABLE_MAX                     (3)
//#define CFG_WOW_STATUS_DEFAULT                 (3)


                        }
                    }

                }

            }
        }


        void mergedEmptyConfig(Qcom_Ini_Config_Item joinItem) {
            for (int i = 0; i < joinItem.mDefineConfigStringList.size(); i++) {
                mDefineConfigStringList.add(mDefineConfigStringList.get(i));  // 把 注释加入到列表中
            }

            contentWithOutExplain = contentWithOutExplain + "\n" + joinItem.contentWithOutExplain;
            allContent = allContent + "\n" + joinItem.allContent;
            mExplain = mExplain + "\n" + joinItem.mExplain;

        }


        boolean isContainBlank() {  //  名称包含空格的配置项  是普通的宏  不是配置项
            return mConfigName.contains(" ");
        }


        String buildMdContent(int beginType) { // 1-#  2-##  3-###  4-#### 5-#####  6-#####
            StringBuilder sb = new StringBuilder();
            String beginTypeString = "";
            for (int i = 0; i < beginType; i++) {
                beginTypeString = beginTypeString + "#";
            }

            String type2 = beginTypeString + "#";

            String code1 = beginTypeString + " " + mConfigName + "[" + (isDefineAtIni ? "in" : "not") + "-ini" + (isDefineAtIni ? "<" + defineValueAtIni + ">" : "") + "<默认:" + defaultRowValue + ">]";
            sb.append(code1);
            String code2 = "\n" + type2 + " define-info";
            String code_begin = "\n\n```\n\n";
            String code3_pre1 = "默认值:  " + defaultRowValue + "\n";
            String code3 = "默认选项:  " + defaultConfigContent + "\n\n";
            String code4 = "可选值:  \n" + contentWithOutExplain + "\n";
            String code5 = "注释: \n" + mExplain + "\n";
            String code_end = "\n\n```\n\n";
            String preCode = code2 + code_begin + code3_pre1 + code3 + code4 + code5 + code_end;
//         preCode = preCode.replace("<", "&lt;");
//         preCode = preCode.replace(">", "&gt;");
//
//         if (preCode.contains("<Object>")) {
//             preCode = preCode.replace("<Object>", "<ZObject>");
//         }

            sb.append(preCode);

            if (isDefineAtIni) {
                String code100 = "\n" + type2 + " ini-info";


                String code100_begin = "\n\n```\n\n";
                String code101 = ".ini中定义的值:      " + defineValueAtIni.trim() + "\n";
                String code102 = "ini 内容:\n" + mExplainAtIni.trim() + "\n";
                String code103 = defineContentAtIni.trim() + "\n";
                String code100_end = "\n\n```\n\n";
                sb.append(code100 + code100_begin + code101 + code102 + code103 + code100_end);
            }
            return sb.toString() + "\n\n";

        }

        @Override
        public String toString() {
            System.out.println("========================Qcom_Ini_Config_Item-Begin================================");
            System.out.println("mConfigName = " + mConfigName);

            System.out.println("isConfigNameEmpty = " + isConfigNameEmpty);
            System.out.println("mSupported_Feature = " + mSupported_Feature);
            System.out.println("mExplain = " + mExplain);
            System.out.println("contentWithOutExplain = " + contentWithOutExplain);
            System.out.println("allContent = " + allContent);
            System.out.println("mDefineConfigStringList = " + mDefineConfigStringList);

            System.out.println("defaultConfigContent = " + defaultConfigContent);
            System.out.println("defaultRowValue = " + defaultRowValue);
            System.out.println("defineValueAtIni = " + defineValueAtIni);
            System.out.println("isDefineAtIni = " + isDefineAtIni);
            System.out.println("defineContentAtIni = " + defineContentAtIni);
            System.out.println("mExplainAtIni = " + mExplainAtIni);
            for (String defineItem : mDefineConfigStringList) {
                System.out.println("defineItem = " + defineItem);
            }
            System.out.println("========================Qcom_Ini_Config_Item-End================================");
            return "";
        }
    }
}