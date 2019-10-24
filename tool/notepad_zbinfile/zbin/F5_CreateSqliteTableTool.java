import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class F5_CreateSqliteTableTool {



    static String TestPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "F5_Sqlite_1020.txt" ;

    static ArrayList<String> mKeyWordName = new ArrayList<>();

    public static void main(String[] args) {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                mKeyWordName.add(itemArgStr);
            }
        }

        if (mKeyWordName.size() == 0) {
            System.out.println("用户输入的文件路径为空  无法读取文件并解析文件!   请重新输入文件!");
            return;
        }

        // 第一参数就是文件的路径
        String inputPath = mKeyWordName.get(0);


//       String inputPath =  TestPath;



        File inputFile = new File(inputPath);

        if (!inputFile.exists()) {
            System.out.println("当前输入文件路径:" + inputPath + "  并不存在  执行失败! 请重新输入文件! ");
            return;
        }


        ArrayList<String> RawContentList = ReadFileContent(inputPath);
        String tableName = getTableName(RawContentList);
        String tableDesc = getTableDesc(RawContentList);

        CUR_TABLE_PARAM.initTableRowName(tableName);
        CUR_TABLE_PARAM.tableDescRow = tableDesc;


        if("".equals(CUR_TABLE_PARAM.tableNameRow) || null == CUR_TABLE_PARAM.tableNameRow ||
                "".equals(CUR_TABLE_PARAM.tableNameUpper) || null == CUR_TABLE_PARAM.tableNameUpper   ){
            System.out.println("获取文件接口名称为空 程序执行失败!  请重新输入!");
            return;
        }

        AnalysisInputParam(RawContentList);
        if(CUR_TABLE_PARAM.inputParam.size() == 0){
            System.out.println("解析到的输入 inputParam 参数个数为0 , 请检查! 请重新输入!");
            return;
        }

        AnalysisOutputParam(RawContentList);
        if(CUR_TABLE_PARAM.outputParam.size() == 0){
            System.out.println("解析到的输出 outputParam 参数个数为0 , 请检查! 请重新输入!");
            return;
        }

        int inputCount = CUR_TABLE_PARAM.inputParam.size();
        int outputCount =  CUR_TABLE_PARAM.outputParam.size();


        System.out.println("解析得到 输入参数:"+inputCount +"个。   输出参数:"+ outputCount+" 个。");


        ArrayList<String> staticTableCode = CUR_TABLE_PARAM.createStaticTableCode();
        appendToFile(inputFile,staticTableCode);
        System.out.println("程序成功执行结束！");




    }


    public static void appendToFile(File file , ArrayList<String> appendList) {
        try {
            if(!file.exists()){
                file.createNewFile();
            }



            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(file, "rwd");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);

//            if(index == 1){
//                randomFile.write(("index = "+index+"============开机打印【"+DateUtil.now()+ "】========== \n").getBytes("utf-8"));
//            }else{
//                randomFile.write((" index = "+index +"【"+DateUtil.now()+ "】 \n").getBytes("utf-8"));
//            }

            for (int i = 0; i < appendList.size(); i++) {
                randomFile.write(appendList.get(i).getBytes("utf-8"));

            }

            //  randomFile.write("\n".getBytes());  // 换行
            randomFile.close();
        } catch( Exception e ){


        }
    }



    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    public static boolean isContainChinese(String str) {   // 字符串中包含中文
        Pattern p = Pattern.compile(REGEX_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }




    public static boolean isOnlyContainChinese(String str) {  // 字符串中只包含中文
        String strTemp = str;
        strTemp = strTemp.replace("\t", "");
        strTemp = strTemp.replace(" ", "");
        boolean curCharisChinese = true; // 没有包含中文
        for (int i = 0; i < strTemp.length(); i++) {
            String onlyoneStr = strTemp.substring(i, i + 1);
            curCharisChinese = isContainChinese(onlyoneStr);
            if (!curCharisChinese) {  //  如果当前的字符串返回false  说明 没有匹配到中文  说明当前行中包含其他英文字母
                return false;
            } else {
                continue;  //  如果包含中文  那么返回 continue  继续下一个  如果循环结束 还没有检测到其他非汉字 那么返回true
            }
        }

        return curCharisChinese;
    }



    public static String SubStringAtFirstChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        int chineseIndex = 0 ;
        for (int i = 0; i < lineContent.length(); i++) {
            String onlyoneStr = lineContent.substring(i, i + 1);
            if(isContainChinese(onlyoneStr)){
                chineseIndex = i;
                break;
            }

        }
        String englishStr = lineContent;
        if(chineseIndex > 0){
            englishStr = lineContent.substring(0,chineseIndex);
        }

        englishStr =englishStr.replace("\t"," ");
        englishStr =englishStr.replace("         "," ");
        englishStr =englishStr.replace("        "," ");
        englishStr =englishStr.replace("       "," ");
        englishStr =englishStr.replace("      "," ");
        englishStr =englishStr.replace("     "," ");
        englishStr =englishStr.replace("    "," ");
        englishStr =englishStr.replace("   "," ");
        englishStr =englishStr.replace("  "," ");
        englishStr =englishStr.replace("  "," ");
        englishStr =englishStr.replace("  "," ");
//        strItem = is_hs str     N       是否沪深港通标的，N否 H沪股通 S深股通
//                inputWithEnglish = is_hs        str     N
//        inputwithChinese = 是否沪深港通标的否沪股通深股通
        System.out.println("englishStr = "+ englishStr);

        return englishStr.trim();
    }



    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }


    public static String clearEnglish(String str) {   // 字符串中包含中文
        String chineseResult = "";
        String chineseStr = str.replace(" ", "");

        for (int i = 0; i < chineseStr.length(); i++) {
            String onlyoneStr = chineseStr.substring(i, i + 1);
            boolean curCharisChinese = isContainChinese(onlyoneStr);
            if (curCharisChinese) {  //  如果当前的字符串返回true  说明 匹配到中文  加入 字符串
                chineseResult = chineseResult + onlyoneStr;
            } else {
                continue;  //  如果没有匹配到中文  说明是英文  继续下一个匹配
            }
        }

        return chineseResult;
    }




    public static String getTableName(ArrayList<String> rawContent) {
        String Tag = "接口：";
        String tableName = "";

        for (int i = 0; i < rawContent.size(); i++) {
            String lineContent  =rawContent.get(i);
            if(lineContent.contains(Tag)){
                tableName = lineContent.replace(Tag,"").trim();
                if(!isContainChinese(tableName)){   //  并不包含  中文
                    return tableName;
                }
            }
        }
        return tableName;
    }

    public static String getTableDesc(ArrayList<String> rawContent) {
        String Tag = "描述：";
        String tableDesc = "";

        for (int i = 0; i < rawContent.size(); i++) {
            String lineContent  =rawContent.get(i);
            if(lineContent.contains(Tag)){  // 包含  接口:  那么就直接返回
                tableDesc = lineContent.replace(Tag,"").trim();
                return tableDesc;

            }
        }
        return tableDesc;
    }




    public static void AnalysisOutputParam(ArrayList<String> rawContent) {
        String Tag = "输出参数";
        boolean outputparamBegin = false;
        for (int i = 0; i < rawContent.size(); i++) {
            String strItem = rawContent.get(i);
            if(strItem.contains(Tag)){
                outputparamBegin = true;
                continue;
            }

            if(outputparamBegin){   //  输出参数 开始

                if (!isOnlyContainChinese(strItem)) {  // 如果当前字符串 只包含 汉字的话 返回 true

                    String outputwithChinese = clearEnglish(strItem);   // 去除中文的字符串

                    String outputWithEnglish = (strItem).replace("\t"," ");    //  只有英语的字符串
                    outputWithEnglish = outputWithEnglish.trim();

                    if(outputWithEnglish.contains(" ")){
                        // 把 多个空格转为 一个空格
                        outputWithEnglish = outputWithEnglish.replace("   "," ");
                        outputWithEnglish = outputWithEnglish.replace("  "," ");
                        outputWithEnglish = outputWithEnglish.replace("  "," ");
                        outputWithEnglish = outputWithEnglish.replace("\t"," ");
                        outputWithEnglish = outputWithEnglish.trim();

                        String[] outputStrArr = outputWithEnglish.split(" ");

                        if(outputStrArr.length >= 3 ){

                            Table_Output_Param outputParam = new Table_Output_Param();
                            outputParam.outputDesc = outputwithChinese;



                            String outputName1 = outputStrArr[0];
                            String outputType2 = outputStrArr[1];
                            String outputNecessaryFalg3 = outputStrArr[2];
                            outputParam.outputName = outputName1;
                            outputParam.outputType = outputType2;
                            outputParam.outputNecessaryFalg = outputNecessaryFalg3;
                            CUR_TABLE_PARAM.addOutputParam(outputParam);
                        }else {
                            System.out.println("解析出来的 输出 OutputParams  数组不对 请检查输入文件格式!  outputWithEnglish ="+outputWithEnglish);
                            System.out.println("outputStrArr.length = "+ outputStrArr.length);
                            for (int j = 0; j < outputStrArr.length; j++) {
                                System.out.println("length="+outputStrArr.length+"   outputs["+j+"] = "+ outputStrArr[j]);
                            }

                            continue;
                        }


                    }else{
                        System.out.println(" 当前的输出字符串可能存在 异常字符串!   ");
                        System.out.println("strItem = "+ strItem);
                        System.out.println("inputWithEnglish = "+ outputWithEnglish);
                        System.out.println("inputwithChinese = "+ outputwithChinese);


//                        strItem = is_hs str     N       是否沪深港通标的，N否 H沪股通 S深股通
//                                inputWithEnglish = is_hs        str     N
//                        inputwithChinese = 是否沪深港通标的否沪股通深股通

                        continue;
                    }


                }

            }




        }


    }
    public static void AnalysisInputParam(ArrayList<String> rawContent) {

        String tag1 = "输入参数";
        String tag2 = "输出参数";
        boolean inputparamBegin = false;
        boolean inputparamEnd = false;

        for (int i = 0; i < rawContent.size(); i++) {
            String strItem = rawContent.get(i);
            if (tag1.equals(strItem)) {
                inputparamBegin = true;
                continue;
            }

            if (tag2.equals(strItem)) {
                inputparamEnd = true;
                continue;
            }

            if(inputparamBegin && !inputparamEnd){   // 输入参数的范围

                if (!isOnlyContainChinese(strItem)) {  // 如果当前字符串 只包含 汉字的话 返回 true



                    String inputwithChinese = clearEnglish(strItem);   // 去除中文的字符串


                    String inputWithEnglish = SubStringAtFirstChinese(strItem).trim();    //  只有英语的字符串

                    if(inputWithEnglish.contains(" ")){
                        // 把 多个空格转为 一个空格
                        inputWithEnglish = inputWithEnglish.replace("   "," ");
                        inputWithEnglish = inputWithEnglish.replace("  "," ");
                        inputWithEnglish = inputWithEnglish.replace("  "," ");
                        inputWithEnglish = inputWithEnglish.replace("（","");
                        inputWithEnglish = inputWithEnglish.replace("）","");
                        inputWithEnglish = inputWithEnglish.replace("\t"," ");

                        inputWithEnglish = inputWithEnglish.trim();

                        String[] inputs = inputWithEnglish.split(" ");

                        if(inputs.length == 3){

                            Table_Input_Param inputParam = new Table_Input_Param();
                            inputParam.inputDesc = inputwithChinese;



                            String inputName1 = inputs[0];
                            String inputType2 = inputs[1];
                            String inputNecessaryFalg3 = inputs[2];
                            inputParam.inputName = inputName1;
                            inputParam.inputType = inputType2;
                            inputParam.inputNecessaryFalg = inputNecessaryFalg3;
                            CUR_TABLE_PARAM.addInputParam(inputParam);
                        }else{
                            System.out.println("解析出来的输入 InputParams 数组不对 请检查输入文件格式! ");
                            for (int j = 0; j < inputs.length; j++) {
                                System.out.println("length="+inputs.length+"   inputs["+j+"] = "+ inputs[j]);
                            }
                            continue;
                        }


                    }else{
                        System.out.println(" 当前的输入字符串可能存在 异常字符串!   ");
                        System.out.println("strItem = "+ strItem);
                        System.out.println("inputWithEnglish = "+ inputWithEnglish);
                        System.out.println("inputwithChinese = "+ inputwithChinese);


//                        strItem = is_hs str     N       是否沪深港通标的，N否 H沪股通 S深股通
//                                inputWithEnglish = is_hs        str     N
//                        inputwithChinese = 是否沪深港通标的否沪股通深股通

                        continue;
                    }


                }

            }else{
                continue;
            }







        }


//        输入参数
//
//        名称	类型	必选	描述
//        ts_code	str	N	股票代码 （股票和时间参数至少输入一个）
//        trade_date	str	N	交易日期
//        start_date	str	N	开始日期
//        end_date	str	N	结束日期
//
//
//        输出参数
//
//        名称	类型	默认显示	描述
//        ts_code	str	Y	TS代码
//        trade_date	str	Y	交易日期
//        buy_sm_vol	int	Y	小单买入量（手）
//        buy_sm_amount	float	Y	小单买入金额（万元）
//        sell_sm_vol	int	Y	小单卖出量（手）
//        sell_sm_amount	float	Y	小单卖出金额（万元）
//        buy_md_vol	int	Y	中单买入量（手）
//        buy_md_amount	float	Y	中单买入金额（万元）
//        sell_md_vol	int	Y	中单卖出量（手）
//        sell_md_amount	float	Y	中单卖出金额（万元）
//        buy_lg_vol	int	Y	大单买入量（手）
//        buy_lg_amount	float	Y	大单买入金额（万元）
//        sell_lg_vol	int	Y	大单卖出量（手）
//        sell_lg_amount	float	Y	大单卖出金额（万元）
//        buy_elg_vol	int	Y	特大单买入量（手）
//        buy_elg_amount	float	Y	特大单买入金额（万元）
//        sell_elg_vol	int	Y	特大单卖出量（手）
//        sell_elg_amount	float	Y	特大单卖出金额（万元）
//        net_mf_vol	int	Y	净流入量（手）
//        net_mf_amount	float	Y	净流入额（万元）



    }

    static Table_Param CUR_TABLE_PARAM = new Table_Param();

    static class Table_Param {
        ArrayList<Table_Input_Param> inputParam;
        ArrayList<Table_Output_Param> outputParam;
        String tableNameRow;
        String tableDescRow;
        String tableNameUpper;


        void  addInputParam(Table_Input_Param input){
            inputParam.add(input);

        }


        void  addOutputParam(Table_Output_Param output){
            outputParam.add(output);

        }


        String getOutputTypeSqlType(String type){
            String resultStr = "";
            if("float".equals(type)){
                return "SQLITE_TYPE.REAL";
            } else if("str".equals(type)){
                return "SQLITE_TYPE.TEXT";
            } else if("int".equals(type)){
                return "SQLITE_TYPE.INT";
            }

            return resultStr;

        }

        ArrayList<String> createStaticTableCode(){
//          StringBuilder  sb = new StringBuilder();
            ArrayList<String> codeList = new ArrayList<String>();
            codeList.add("\n");
            codeList.add("\n");
            codeList.add("\n");

            String pre = "static { \n";
            String end = " } \n";

            codeList.add(pre);

            String code1_1 = "//  【 table_ 】 "+ this.tableDescRow.trim();
            String tableObjName = this.tableNameUpper;
            String code2_1 = "DB_Table " + this.tableNameUpper +" = new DB_Table(\"" + this.tableNameRow+"\");";
            String code3_1 =  this.tableNameUpper +".tableIndex = 18;";
            String code4_1 =  this.tableNameUpper +".viceTableIndex = 1;";
            String code5_1 =  this.tableNameUpper +".tableDesc = \""+this.tableDescRow.trim()+"\";";
            String inputParamStr = "";

            codeList.add(code1_1 + "\n");
            codeList.add(code2_1 + "\n");
            codeList.add(code3_1 + "\n");
            codeList.add(code4_1 + "\n");
            codeList.add(code5_1 + "\n");

            ArrayList<String> codeInputList= new ArrayList<String>();
            for (int i = 0; i < inputParam.size(); i++) {
                Table_Input_Param inputItem = inputParam.get(i);
                String inputName = inputItem.inputName;
                String inputDesc = inputItem.inputDesc;
                String inputType = inputItem.inputType;
                String inputObjName =tableObjName+"_"+inputName;

                String code1 = "Table_Input_Param " +tableObjName+"_"+inputName + " = new Table_Input_Param(\"" +inputName +"\" , \""+ inputType + "\", \"\");" ;
                String code2 = inputObjName+".desc = \""+ inputDesc+ "\" ;";
                String code3 = tableObjName+".addTableInputParam("+inputObjName+ ");";

                codeList.add(code1 + "\n");
                codeList.add(code2+ "\n");
                codeInputList.add(code3+ "\n");

            }
            codeList.addAll(codeInputList);
            codeList.add("\n");


            ArrayList<String> codeOutputList= new ArrayList<String>();
            for (int i = 0; i < outputParam.size(); i++) {
                Table_Output_Param outputItem = outputParam.get(i);
                String outName = outputItem.outputName;
                String outDesc = outputItem.outputDesc;
                String outType = outputItem.outputType;
                String outNeceFlag = outputItem.outputNecessaryFalg;
                String outSqlType = getOutputTypeSqlType(outType);
                String outObjName =tableObjName+"_"+outName+"_out";

                String code1 = "Table_Item " +outObjName + " = new Table_Item(\"" +outName +"\" , "+ outSqlType + ", \""+outDesc+"\");" ;
                //  String code2 = outObjName+".desc = \""+ outDesc+ "\";";
                String code3 = tableObjName+".addTableItem("+outObjName+ ");";

                codeList.add(code1 + "\n");
                //  codeList.add(code2+ "\n");
                codeOutputList.add(code3+ "\n");

            }
            codeList.addAll(codeOutputList);
            codeList.add("\n");
            String code6 = "SQLite_Tables.add("+tableObjName+");";
            codeList.add(code6 +"\n");
            codeList.add(end);


/*             static {
        // 【table_2】  交易日历
        //     RequestBody: {"api_name": "trade_cal", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"exchange":"","start_date":"","end_date":"","is_open":""},"fields": "exchange,cal_date,is_open,pretrade_date"}



        DB_Table TradeCal = new DB_Table("trade_cal");
        TradeCal.tableIndex = 18;
        TradeCal.viceTableIndex = 1;
        TradeCal.tableDesc = "限售股解禁数据";

        Table_Input_Param TradeCal_exchange = new Table_Input_Param("exchange", "str", "");
        TradeCal_exchange.desc = "【交易所】 [SSE上交所] [SZSE深交所]";
        Table_Input_Param TradeCal_start_date = new Table_Input_Param("start_date", "str", "");
        TradeCal_start_date.desc = "开始日期";
        Table_Input_Param TradeCal_end_date = new Table_Input_Param("end_date", "str", "");
        TradeCal_end_date.desc = "结束日期";
        Table_Input_Param TradeCal_is_open = new Table_Input_Param("is_open", "str", "");
        TradeCal_is_open.desc = "【是否交易】 [0休市] [1交易]";
        TradeCal.addTableInputParam(TradeCal_exchange);
        TradeCal.addTableInputParam(TradeCal_start_date);
        TradeCal.addTableInputParam(TradeCal_end_date);
        TradeCal.addTableInputParam(TradeCal_is_open);




        Table_Item TradeCal_exchange_out = new Table_Item("exchange", SQLITE_TYPE.TEXT, "【交易所】 [SSE上交所] [SZSE深交所]");
        Table_Item TradeCal_cal_date_out = new Table_Item("cal_date", SQLITE_TYPE.TEXT, "日历日期");
        Table_Item TradeCal_is_open_out = new Table_Item("is_open", SQLITE_TYPE.TEXT, "是否交易");
        Table_Item TradeCal_pretrade_date_out = new Table_Item("pretrade_date", SQLITE_TYPE.TEXT, "上一个交易日");
        TradeCal.addTableItem(TradeCal_exchange_out);
        TradeCal.addTableItem(TradeCal_cal_date_out);
        TradeCal.addTableItem(TradeCal_is_open_out);
        TradeCal.addTableItem(TradeCal_pretrade_date_out);


        TradeCal.tableIndex = 2;
        TradeCal.viceTableIndex = 1;
//        SQLite_Tables.add(TradeCal);

    }

           }*/



            return codeList;

        }

        Table_Param() {
            this.inputParam = new ArrayList<Table_Input_Param>();
            this.outputParam = new ArrayList<Table_Output_Param>();
        }

        void   initTableRowName(String rowName){
            this.tableNameRow = rowName;
            if(!rowName.contains("_")){  // 没有包含下划线
                this.tableNameUpper =    toUpperFirstCharinString(rowName);

            }else{
                String[] sqliteStr = rowName.split("_");
                ArrayList<String> upNameList = new ArrayList<String>();
                for (int i = 0; i < sqliteStr.length; i++) {
                    String itemStr = sqliteStr[i];
                    upNameList.add(toUpperFirstCharinString(itemStr));
                }

                String name = "";
                for (int i = 0; i < upNameList.size(); i++) {
                    String item = upNameList.get(i).trim()+"_";
                    name = name + item;
                }

                while(name.endsWith("_")){

                    name =  name.substring(0,name.length() -1);
                }

                this.tableNameUpper =    name;

            }

        }



    }

    static String toUpperFirstCharinString(String str){
        if(str.length() == 0){
            return "";
        }

        if(str.length() == 1){
            return str.toUpperCase();
        }

        String str1=str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());

        return str1;
    }


    static class Table_Input_Param {
        String inputName;
        String inputType;
        String inputDesc;
        String inputNecessaryFalg;

    }

    static class Table_Output_Param {
        String outputName;
        String outputType;
        String outputDesc;
        String outputNecessaryFalg;
    }


    public static ArrayList<String> ReadFileContent(String path) {

        //System.out.println(ToFirstChar("ABC  汉字转换为拼音CBA").toUpperCase()); //转为首字母大写
        // System.out.println(ToPinyinWithLine("A周 B杰 C伦"));
        // System.out.println(ToPinyinWithLine("ABC汉字转换为拼音CBA"));

        //===============real-test begin===============
        String mFilePath = path;
// StringBuilder sb  =new StringBuilder();
        ArrayList<String> content = new ArrayList<String>();
        //===============real-test end===============


        //===============local-test begin===============
        //      String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";
        //===============local-test end===============


        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("存在  当前文件 " + path);
        } else {
            System.out.println("不存在 当前文件 " + path);

            return null;
        }

        if (curFile != null) {
            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                int index = 1;
                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }

                    content.add(oldOneLine);
//                    sb.append(oldOneLine);
                    //   System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                    index++;


                }
                curBR.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }

        return content;
    }
}