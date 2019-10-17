import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class F6_ZChar {

    // 输入的参数列表

    //


    // 从输入文件读取到的可能是乱码的字符串
    static ArrayList<String> mFileLineList = new ArrayList<>();


    static ArrayList<String> mKeyWordName = new ArrayList<>();
    static NumberFormat nf = new DecimalFormat("0.00");
    static String LOCAL_ENCODEING = "";
    static String InputStr = "ÀÏÂí";  //  乱码字符串测试使用  "ÀÏÂí"   老马

    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" ;
    static String TEXT_INPUT_FILE = zbinPath+File.separator+"F6_Input_Text.txt";
    static String TEXT_OUT_FILE = zbinPath+File.separator+"F6_Output_Text.txt";
    static ArrayList<CharSetResult> allCharSetResultList = new  ArrayList<CharSetResult>();
    static final String[] CharUnicodeEncodes = new String[] { "Big5" ,"windows-1252","UTF-8","GBK","ISO-8859-1"  };
    static class CharSetResult{
        String originSrcStr;    // 从文件读取到的原始的字符串
        String mCharsetAName;  // A----正确的编码方式
        String mCharsetBName;  // B----当前错误的编码方式
        String mCharsetBByteArrStr;  // B编码方式下的二进制形式
        String mShowWithmCharsetA;  //  A-- 可能的正确编码的方式
        byte[]  mCharsetBByteArr; // B 编码方式下的 byte字节数组

        CharSetResult(String cCharsetAName , String cCharsetBName){
            this.mCharsetAName =  cCharsetAName;
            this.mCharsetBName =  cCharsetBName;
        }
    }

    static{
        for (int i = 0; i < CharUnicodeEncodes.length; i++) {

            for (int j = 0; j < CharUnicodeEncodes.length; j++) {
                //  if(i != j){

                allCharSetResultList.add(new CharSetResult(CharUnicodeEncodes[i],CharUnicodeEncodes[j]));

                //   }
            }
        }


    }
    // 以 utf 读取文件
    public static void main(String[] args) {
        long timestamp1 = System.currentTimeMillis();
        //   showCharSet();    编码集合
        LOCAL_ENCODEING = System.getProperty("file.encoding");
        System.out.println("本地编码方式:"+LOCAL_ENCODEING);

        ArrayList<String> OutStrList = new  ArrayList<String>();

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

        if(mKeyWordName.size() == 0){
            ReadFileContent(TEXT_INPUT_FILE);


            for (int i = 0; i < mFileLineList.size(); i++) {
                checkAllCharSet(mFileLineList.get(i));
                String OriginStr = "";
                for (int j = 0; j < allCharSetResultList.size(); j++) {
                    CharSetResult charSetResult = allCharSetResultList.get(j);
                    OriginStr = charSetResult.originSrcStr;
                    break;

                }

//            String originSrcStr;    // 从文件读取到的原始的字符串
//            String mCharsetAName;  // A----正确的编码方式
//            String mCharsetBName;  // B----当前错误的编码方式
//            String mCharsetBByteArrStr;  // B编码方式下的二进制形式
//            String mShowWithmCharsetA;  //  A-- 可能的正确编码的方式

                LinkedHashMap<String,String> arr2titleMapA = new LinkedHashMap<String,String>();
                arr2titleMapA.put("mCharsetBName","B编码(可能的错误编码)【 "+OriginStr+" 】");
                arr2titleMapA.put("mCharsetAName","A编码(可能的正确编码)");
                arr2titleMapA.put("mShowWithmCharsetA","A结果形式");
                TablePrint(allCharSetResultList.toArray(),arr2titleMapA,mFileLineList.get(i)+"显示字符集情况");
                OutStrList.addAll(tableListStr);

                String log1 = "【"+OriginStr+"】的ASCII码 【 "+native2Ascii(OriginStr)+" 】";
                String log2 = "【"+OriginStr+"】的"+LOCAL_ENCODEING+"(本地Native)格式 【 "+ascii2Native(OriginStr)+" 】";
                System.out.println(log1);
                System.out.println(log2);
                OutStrList.add(log1);
                OutStrList.add(log2);
                OutStrList.add("\n");
            }


        }else{

            for (int i = 0; i < mKeyWordName.size(); i++) {
                 String inputStr = mKeyWordName.get(i);
                checkAllCharSet(inputStr);
                LinkedHashMap<String,String> arr2titleMapA = new LinkedHashMap<String,String>();
                arr2titleMapA.put("mCharsetBName","B编码(可能的错误编码)【"+inputStr+"】");
                arr2titleMapA.put("mCharsetAName","A编码(可能的正确编码)");
                arr2titleMapA.put("mShowWithmCharsetA","A结果形式");
                TablePrint(allCharSetResultList.toArray(),arr2titleMapA,"用户输入字符串【"+inputStr+"】显示字符集情况");
                OutStrList.addAll(tableListStr);

                String log1 = "【"+inputStr+"】的ASCII码 【 "+native2Ascii(inputStr)+" 】";
                String log2 = "【"+inputStr+"】的"+LOCAL_ENCODEING+"(本地Native)格式 【 "+ascii2Native(inputStr)+" 】";
                System.out.println(log1);
                System.out.println(log2);
                OutStrList.add(log1);
                OutStrList.add(log2);
                OutStrList.add("\n");


            }
        }

        if(OutStrList.size() > 0){
            writeContentToFile(new File(TEXT_OUT_FILE),OutStrList);
            String commandNotead = "cmd.exe /c start   Notepad++.exe " + TEXT_OUT_FILE;
            execCMD(commandNotead);
        }




/*         //  检测是否能从输入文件中读取到 乱码的字符串
         System.out.println("InputStr = "+ InputStr);

        for (int i = 0; i < mFileLineList.size(); i++) {
            String item  = mFileLineList.get(i);
            if(item.equals(InputStr)){
                System.out.println("InputStr = "+ InputStr + "和 从文件读取到字符串相同 Line:"+item);
            }
        }
*/






    }

    public static String execCMD(String command) {
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


    static void writeContentToFile(File file, ArrayList<String> strList) {

        StringBuilder sb =new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            sb.append(strList.get(i)+"\n");
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
             //   System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            //    System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ReadFileContent(String path) {

        //System.out.println(ToFirstChar("ABC  汉字转换为拼音CBA").toUpperCase()); //转为首字母大写
        // System.out.println(ToPinyinWithLine("A周 B杰 C伦"));
        // System.out.println(ToPinyinWithLine("ABC汉字转换为拼音CBA"));

        //===============real-test begin===============
        String mFilePath = path;

        //===============real-test end===============


        //===============local-test begin===============
        //      String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";
        //===============local-test end===============


        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("存在  当前文件 "+ path);
        } else {
            System.out.println("不存在 当前文件 "+ path );

            return;
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

                    mFileLineList.add(oldOneLine);
                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                    index++;


                }
                curBR.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }

    // 这里可以提供更多地编码格式,另外由于部分编码格式是一致的所以会返回 第一个匹配的编码格式 GBK 和 GB2312
    // Java 的字符串 都是 Unicode 编码的



    static void printByteArr(byte[] destByte,String title){
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < destByte.length; j++) {
            sb.append((char)destByte[j]);
            if(j%4 == 0 ){
                sb.append(" ");
            }
        }
        System.out.println(title+":"+native2Ascii(sb.toString()));
    }


    static void checkAllCharSet(String srcStr){

        for (int i = 0; i < allCharSetResultList.size(); i++) {
            CharSetResult currentCharSet = allCharSetResultList.get(i);

            try {

                byte[] byteArr = srcStr.getBytes(currentCharSet.mCharsetBName);
                String curStr = new String(srcStr.getBytes(currentCharSet.mCharsetBName),currentCharSet.mCharsetAName);
                currentCharSet.mShowWithmCharsetA = curStr;
                currentCharSet.mCharsetBByteArr = byteArr;
                currentCharSet.originSrcStr = new String(srcStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
/*
        for (int i = 0; i < CharUnicodeEncodes.length; i++) {
            String charSetName1 = CharUnicodeEncodes[i];
            try {
                byte[] curCharByte = srcStr.getBytes(charSetName1);
                for (int j = 0; j < CharUnicodeEncodes.length; j++) {
                    String charSetName2 = CharUnicodeEncodes[j];
                    if(i!=j) {

                        String s = new String(srcStr.getBytes(CharUnicodeEncodes[i]),CharUnicodeEncodes[j]);
                        System.out.println("---- 原来编码(A)假设是: "+CharUnicodeEncodes[j]+", 被错误解读为了(B): "+CharUnicodeEncodes[i]);
                        System.out.println(s);
                        System.out.println();

                    }

              String srcStr0 = new String(srcStr);
                    String srcStr0_native2Ascii = native2Ascii(srcStr0);
                    String srcStr0_ascii2Native = ascii2Native(srcStr0);




                    String srcStr1 = new String(curCharByte);
                    String srcStr1_native2Ascii = native2Ascii(srcStr1);
                    String srcStr1_ascii2Native = ascii2Native(srcStr1);

                    String srcStr2 = new String(curCharByte,charSetName2);
                    String srcStr2_native2Ascii = native2Ascii(srcStr2);
                    String srcStr2_ascii2Native = ascii2Native(srcStr1);*/



/*
                    System.out.println("====================Begin("+charSetName1+","+charSetName2+")====================");

                    String showInfoStr1 = "srcStr0(Unicode) = "+ srcStr;
                    String showInfoStr1_1 = "srcStr0(native2Ascii) = "+ srcStr0_native2Ascii;
                    String showInfoStr1_2 = "srcStr0(ascii2Native) = "+ srcStr0_ascii2Native;
                    String showInfoStr2 = "srcStr1("+charSetName1+","+LOCAL_ENCODEING+") = "+ srcStr1;
                    String showInfoStr2_1 = "srcStr1(native2Ascii) = "+ srcStr1_native2Ascii;
                    String showInfoStr2_2 = "srcStr1(ascii2Native) = "+ srcStr1_ascii2Native;

                    String showInfoStr3 = "srcStr2("+charSetName1+","+charSetName2+") = "+ srcStr2;
                    String showInfoStr3_1 = "srcStr2(native2Ascii) = "+ srcStr2_native2Ascii;
                    String showInfoStr3_2 = "srcStr2(ascii2Native) = "+ srcStr2_ascii2Native;


                    System.out.println(showInfoStr1);
                    System.out.println(showInfoStr1_1);
                    System.out.println(showInfoStr1_2);

                    System.out.println(showInfoStr2);
                    System.out.println(showInfoStr2_1);
                    System.out.println(showInfoStr2_2);

                    System.out.println(showInfoStr3);
                    System.out.println(showInfoStr3_1);
                    System.out.println(showInfoStr3_2);
                    System.out.println("====================End("+charSetName1+","+charSetName2+")====================");
     }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            }

                    */






    }





/*
    ASCII: 码表  ASCII 码使用指定的7 位或8 位二进制数组合来表示128 或256 种可能的字符
           http://tool.oschina.net/commons?type=4

           ISO-8859-1 编码是单字节编码，向下兼容ASCII
          ISO-8859-2
    Unicode :为世界上所有字符都分配了一个唯一的数字编号，这个编号范围从 0x000000 到 0x10FFFF (十六进制)，有 110 多万，
            每个字符都有一个唯一的 Unicode 编号，这个编号一般写成 16 进制
            Unicode 就相当于一张表，建立了字符与编号之间的联系【100多万的对应关系】
            Unicode 本身只规定了每个字符的数字编号是多少，并没有规定这个编号如何存储 【这就产生了各种字符集,字符集以存储方式区别】
            说明地址:  https://blog.csdn.net/zhusongziye/article/details/84261211

    UTF-8  UTF-16  UTF-32 GBK  GB2312  GB18030 等字符集都是 遵从Unicode表关系的 代表不同存储方式的的集合
    GB2312：基本集共收入汉字6763个和非汉字图形字符682个。   gb2312只能表示简体字
    GBK：共收录了21003个汉字。   GBK能标识繁体字 也能标识简体字
    GB18030 ：共收录汉字70244个
    */




//    索引:75   字符集名称: UTF-16
//    索引:76   字符集名称: UTF-16BE
//    索引:77   字符集名称: UTF-16LE
//    索引:78   字符集名称: UTF-32
//    索引:79   字符集名称: UTF-32BE
//    索引:80   字符集名称: UTF-32LE
//    索引:81   字符集名称: UTF-8


    static public void showCharSet(){
        Map<String , Charset> map = Charset.availableCharsets();

        int index = 1 ;
        Set<Map.Entry<String , Charset>> set = map.entrySet();
        System.out.println("======================="+"字符集Begin"+"=======================");
        for(Map.Entry<String , Charset> entry : set){
            String key = entry.getKey();
            Charset charSet = entry.getValue();
            String displayName = charSet.displayName();
            String charsetNme = charSet.name();
            System.out.println("索引:"+ index+"   字符集名称: "+ charsetNme);
            index++;

//            System.out.println("key = "+ key + "    displayName="+displayName+"    charsetNme="+charsetNme);

//            if(!key.equals(displayName) ||  !charsetNme.equals(key)){
//                System.out.println("不一致: key = "+ key + "    displayName="+displayName+"    charsetNme="+charsetNme);
//
//            }
        }
        System.out.println("======================="+"字符集End"+"=======================");

    }


    public static String ChineseToASCII(byte[] rec) { //从字节读取内容
        ByteArrayInputStream bais = new ByteArrayInputStream(rec);
        DataInputStream dis = new DataInputStream(bais);
        String BTS=null;
        try {
            BTS=new String(rec,"ISO8859-1");//转换编码
            bais.close();
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BTS;
    }



    public static String ascii2Native(String str) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;
        int index = str.indexOf(PREFIX);
        while (index != -1) {
            sb.append(str.substring(begin, index));
            sb.append(ascii2Char(str.substring(index, index + 6)));
            begin = index + 6;
            index = str.indexOf(PREFIX, begin);
        }
        sb.append(str.substring(begin));
        return sb.toString();
    }

    static char ascii2Char(String str) {
        if (str.length() != 6) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must be 6 character.");
        }
        if (!PREFIX.equals(str.substring(0, 2))) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must start with \"\\u\".");
        }
        String tmp = str.substring(2, 4);
        int code = Integer.parseInt(tmp, 16) << 8;
        tmp = str.substring(4, 6);
        code += Integer.parseInt(tmp, 16);
        return (char) code;
    }


    static String PREFIX = "\\u";
    public static String native2Ascii(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(char2Ascii(chars[i]));
        }
        return sb.toString();
    }


    static String char2Ascii(char c) {
        if (c > 255) {
            StringBuilder sb = new StringBuilder();
            sb.append(PREFIX);
            int code = (c >> 8);
            String tmp = Integer.toHexString(code);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            code = (c & 0xFF);
            tmp = Integer.toHexString(code);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            return sb.toString();
        } else {
            return Character.toString(c);
        }
    }



    /**
     * 获取字符串编码格式
     *
     * @param str
     * @return
     */
    public static String getEncode(String str) {
        byte[] data = str.getBytes();
        byte[] b = null;
        a:for (int i = 0; i < CharUnicodeEncodes.length; i++) {
            try {
                b = str.getBytes(CharUnicodeEncodes[i]);
                if (b.length!=data.length)
                    continue;
                for (int j = 0; j < b.length; j++) {
                    if (b[j] != data[j]) {
                        continue a;
                    }
                }
                return CharUnicodeEncodes[i];
            } catch (UnsupportedEncodingException e) {
                continue;
            }
        }
        return null;
    }

    /**
     * 将字符串转换成指定编码格式
     *
     * @param str
     * @param encode
     * @return
     */
    public static String transcoding(String str, String encode) {
        String df = "ISO-8859-1";
        try {
            String en = getEncode(str);
            if (en == null)
                en = df;
            return new String(str.getBytes(en), encode);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }






    //  TablePrint=======================Begin
    static ArrayList<String> tableListStr = new  ArrayList<String>();

    @SuppressWarnings("unchecked")
    public static void TablePrint(Object[] showItem , LinkedHashMap<String,String> arrAndtitleMap , String titlevalue) {
        System.out.println();
        if(showItem == null || showItem.length == 0  ){
            System.out.println();
            System.out.println(" 当前打印数组为空  无法打印! 请检查参数！");
        }


        //   PrintStringMap(arrAndtitleMap);



        if(arrAndtitleMap == null || arrAndtitleMap.size() == 0){
            System.out.println();
            System.out.println(" 当前打印必需参数 head属性(key)-head名称(value)  为空 , 将默认使用 arr-arr 的样式  ");
            LinkedHashMap<String,String> paramMap = new LinkedHashMap<String,String>();
            Object obj = showItem[0];
            Field[] fieldList =  obj.getClass().getDeclaredFields();
            for (int i = 0; i < fieldList.length; i++) {
                paramMap.put(fieldList[i].getName()+"" , fieldList[i].getName()+"");
            }
            arrAndtitleMap = paramMap;

        }
        if (showItem.length > 0) {
            Object item = showItem[0];
//            Map<String,String> arr2titleMap = new HashMap<String,String>();
//            arr2titleMap.put("arr","属性A");
//            arr2titleMap.put("brr","属性B");
//            arr2titleMap.put("crr","属性C");
//            arr2titleMap.put("drr","属性D");
            InitTableHead(item,arrAndtitleMap);

            ArrayList<String> title = tableTitleList;
            ArrayList<Field> tableField = tableFieldList;
            Map<Field,String> titleFieldMap = tablefieldTitleMap;
            Map<String,Field> fieldTitleMap = tableTitlefieldMap;
            int row_table_num = tableField.size();
            Map<Field, Integer> curMaxSize = new LinkedHashMap<Field, Integer>();

// 修改点

            Map.Entry<String, String> entry;
            if (arrAndtitleMap != null) {
                Iterator iterator = arrAndtitleMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, String>) iterator.next();
                    String fieldName =  entry.getKey();   // 属性名称
                    String headName  = entry.getValue();  // 表头名称
                    //  依据 field拿到Field   依据title拿到 Field
                    curMaxSize.put(fieldTitleMap.get(headName), getPaddingChineseLength(headName.trim()));

                }
            }

            //      PrintField2IntMap(curMaxSize);
//            for (int i = 0; i < tableField.size(); i++) {
//                curMaxSize.put(tableField.get(i), getPaddingChineseLength(title.get(i).trim()));
//            }

            for (int i = 0; i < showItem.length; i++) {
                Object curItem = showItem[i];

                for (int j = 0; j < tableField.size(); j++) {
                    Field curField = tableField.get(j);
                    try {
                        String curTableField = ""+getFieldValue(curItem,curField.getName());
                        int curTableFieldLength = getPaddingChineseLength(curTableField.trim());
                        if(curMaxSize.get(curField) < curTableFieldLength){
                            curMaxSize.put(curField,curTableFieldLength);
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }

            int index = 0 ;

            // 每个项 对应的   最大长度


            // 对当前的最大长度进行修正
            // 如果大小小于  DEFAULT_CONTENT_PADDING_SIZE_MIN  8 , 那么就设置为 8
            //
            int curTableItemNum = row_table_num;  // 每行的 显示项
            int curTableMaxLength = DEFAULT_CONTENT_PADDING_SIZE_MAX;

            Map<Field, Integer> fixed_MaxSizeMap = new LinkedHashMap<Field, Integer>();

            Map.Entry<Field, Integer> entryA;
            if (curMaxSize != null) {
                Iterator iterator = curMaxSize.entrySet().iterator();
                while (iterator.hasNext()) {
                    entryA = (Map.Entry<Field, Integer>) iterator.next();
                    //Map的Value
//                    System.out.println(index+"未修正  Field = "+ entry.getKey().getName());
//                    System.out.println(index+ "未修正 Value = "+ entry.getValue());
                    if(entryA.getValue() < DEFAULT_CONTENT_PADDING_SIZE_MIN){
                        fixed_MaxSizeMap.put(entryA.getKey(),DEFAULT_CONTENT_PADDING_SIZE_MIN);
                    }else if(entryA.getValue() > DEFAULT_CONTENT_PADDING_SIZE_MAX){
                        fixed_MaxSizeMap.put(entryA.getKey(),DEFAULT_CONTENT_PADDING_SIZE_MAX);
                    }else{

                        fixed_MaxSizeMap.put(entryA.getKey(),entryA.getValue());

                    }
                    index++;
                }
            }


            int maxLength = 0 ;

            ArrayList<FieldAndSizeTitle> filedAndSizelist = new   ArrayList<FieldAndSizeTitle>();
            Map.Entry<Field, Integer> fixedEntry;
            if (curMaxSize != null) {
                Iterator iterator = fixed_MaxSizeMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    fixedEntry = (Map.Entry<Field, Integer>) iterator.next();
                    //Map的Value
//                    System.out.println(index+" 已修正  Field = "+ fixedEntry.getKey().getName());
//                    System.out.println(index+ "已未修正 Value = "+ fixedEntry.getValue());
                    //  System.out.println(" 当前Filed序列: " +fixedEntry.getKey().getName());
                    FieldAndSizeTitle FieldAndSizeTitleItem =  new FieldAndSizeTitle(fixedEntry.getKey() ,  fixedEntry.getValue() ,titleFieldMap.get(fixedEntry.getKey()));
                    filedAndSizelist.add(FieldAndSizeTitleItem);
                    maxLength = maxLength  + FieldAndSizeTitleItem.size;
                }
            }


            int inputTitleSize = getPaddingChineseLength(titlevalue);
            int halfLength = maxLength/2 - inputTitleSize/2  ;
            String headBlankStr = buildStringRepeat("=",halfLength);
            String titlefixed = headBlankStr + "【"+titlevalue+"】"+headBlankStr;
            System.out.println(titlefixed);


            ArrayList<String> tableStrList = new  ArrayList<String>();
            for (int i = 0; i < showItem.length; i++) {

                Object itemX = showItem[i];

                String curContentLine = "";
                String spaceLine = "";
                if(i == 0 ){   //   添加 表头
                    String firstLine = "";
                    String titleLine = "";
                    String titleSpaceLine = "";
                    for (int j = 0; j < filedAndSizelist.size(); j++) {
                        String headTitle = filedAndSizelist.get(j).title;
                        Field curField = filedAndSizelist.get(j).field;
                        int MaxSize = filedAndSizelist.get(j).size;
                        //  MaxSize = MaxSize + 1;   //  留一个空格位置
                        int rawSize = getPaddingChineseLength(headTitle); //  标题的大小
                        int distance = MaxSize - rawSize ;  // 后面空格的大小
                        String blankStr = buildStringRepeat(" ",distance);
                        String rawItem = CONTENT_SPACE + headTitle+blankStr;

                        rawItem = rawItem.replace(headTitle+" "," "+headTitle);
                        titleLine = titleLine + rawItem;

                        String beginSpace = "";
                        if(j == 0){
                            beginSpace = ROW_LINE_PRE + buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) + buildStringRepeat(ROW_LINE_Content_Cell,1);

                        }else if( j == filedAndSizelist.size() -1){

                            beginSpace = buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) + ROW_LINE_END;

                        }else{
                            beginSpace = buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) +  buildStringRepeat(ROW_LINE_Content_Cell,1);
                        }



                        titleSpaceLine = titleSpaceLine + beginSpace;
                    }

                    firstLine = titleSpaceLine;
                    firstLine = firstLine.replace(ROW_LINE_PRE,TOP_LINE_PRE);
                    firstLine = firstLine.replace(ROW_LINE_Content_Cell,TTOP_LINE_MIDDLE);
                    firstLine = firstLine.replace(ROW_LINE_END,TOP_LINE_END);
                    tableStrList.add(firstLine);
                    tableStrList.add(titleLine+CONTENT_SPACE);

                    tableStrList.add(titleSpaceLine);
                }


                for (int j = 0; j < filedAndSizelist.size(); j++) {
                    Field curField = filedAndSizelist.get(j).field;
                    int MaxSize = filedAndSizelist.get(j).size;
                    //  MaxSize = MaxSize + 1;   //  留一个空格位置
                    try {
                        String curTableItem =  ""+getFieldValue(itemX,curField.getName());
                        int rawSize = getPaddingChineseLength(curTableItem); //  数值的大小

                        while(rawSize > DEFAULT_CONTENT_PADDING_SIZE_MAX){
                            curTableItem = curTableItem.substring(0,DEFAULT_CONTENT_PADDING_SIZE_MAX-1);
                            rawSize = getPaddingChineseLength(curTableItem);
                        }
                        int distance = MaxSize - rawSize ;  // 后面空格的大小

                        String blankStr = buildStringRepeat(" ",distance);
                        String rawItem = CONTENT_SPACE + curTableItem+blankStr;




                        String beginSpace = "";
                        if(j == 0){
                            beginSpace = ROW_LINE_PRE + buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) + buildStringRepeat(ROW_LINE_Content_Cell,1);

                        }else if( j == filedAndSizelist.size() -1){

                            beginSpace = buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) + ROW_LINE_END;

                        }else{

                            beginSpace = buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) +  buildStringRepeat(ROW_LINE_Content_Cell,1);
                        }
//                        System.out.println();
//                        System.out.println(" rawItem1 = "+ rawItem  );
                        rawItem = rawItem.replace(curTableItem+" "," "+curTableItem);
//                        System.out.println( "    curTableItem ="+ curTableItem);

//                        System.out.println(" distance = "+ distance   + "    curTableItem ="+ curTableItem);
//                        System.out.println(" maxSize = "+ MaxSize   );
//                        System.out.println(" rawSize = "+ rawSize  );
//                        System.out.println(" rawItem2 = "+ rawItem  );
                        // System.out.println();
                        curContentLine = curContentLine + rawItem;
                        spaceLine = spaceLine + beginSpace;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }


                }

                if(i == showItem.length -1){
                    spaceLine = spaceLine.replace(ROW_LINE_PRE,BUTTOM_LINE_PRE);

                    spaceLine = spaceLine.replace(ROW_LINE_Content_Cell,BUTTOM_LINE_MIDDLE);
                    spaceLine = spaceLine.replace(ROW_LINE_END,BUTTOM_LINE_END);
                }
                tableStrList.add(curContentLine+CONTENT_SPACE);
                tableStrList.add(spaceLine);

            }
            ProintArrayLog(tableStrList, titlevalue);
        }
    }

    public static void ProintArrayLog(ArrayList<String> showItem , String title) {
//        System.out.println("======================================="+title+"=======================================");
        tableListStr.clear();
        for (int i = 0; i < showItem.size(); i++) {
            tableListStr.add(showItem.get(i));
            System.out.println(showItem.get(i));
        }
    }

    static String buildStringRepeat(String src , int count){
        String result = "";
        for (int i = 0; i < count; i++) {
            result=src+result;
        }
        return result;
    }


    public static void getCurrentFiledValue() {


    }

    static class FieldAndSizeTitle{
        Field field;
        int size;
        String title;

        FieldAndSizeTitle(Field mField , int mSize , String mtitle){
            this.field = mField;

            this.size = mSize + 2;  // 默认需要   一前  一后 两个空格

            this.title = mtitle;
        }
    }








    static   ArrayList<Field>  tableFieldList = new ArrayList<Field>();
    static   ArrayList<String> tableTitleList = new ArrayList<String>();
    static   Map<Field,String> tablefieldTitleMap = new LinkedHashMap<Field,String>();
    static   Map<String,Field> tableTitlefieldMap = new LinkedHashMap<String,Field>();

    @SuppressWarnings("unchecked")
    static  void InitTableHead(Object targetObject , Map<String,String> arrAndTitle) {
        clearTableValue();
        Map<Field, String> headMap = new HashMap<Field, String>();
        Map.Entry<String, String> entry;
        if(arrAndTitle != null){
            Iterator iterator = arrAndTitle.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                try {
                    headMap.put(targetObject.getClass().getDeclaredField(entry.getKey()), entry.getValue());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            getFieldAndHeadTitle(headMap);
        }
    }

    static void clearTableValue(){
        tableFieldList.clear();
        tableTitleList.clear();
        tablefieldTitleMap.clear();
        tableTitlefieldMap.clear();
    }

    @SuppressWarnings("unchecked")
    static  void getFieldAndHeadTitle(Map<Field, String> map) {
        Map.Entry<Field, String> entry;
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<Field, String>) iterator.next();
                ;  //Map的Value
                tableFieldList.add(entry.getKey());
                ;  //Map的Value
                tableTitleList.add(entry.getValue());
                tablefieldTitleMap.put(entry.getKey() , entry.getValue());
                tableTitlefieldMap.put(entry.getValue(),entry.getKey());
            }
        }
    }

    @SuppressWarnings("unchecked")
    static  void PrintStringMap(Map<String, String> map) {
        int mapIndex = 0;
        Map.Entry<String, String> entry;
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                System.out.println("索引:"+ mapIndex + "   Key:"+entry.getKey() + "     Value:"+entry.getValue());
                mapIndex++;
            }
        }
    }


    @SuppressWarnings("unchecked")
    static  void PrintField2IntMap( Map<Field, Integer> map) {
        int mapIndex = 0;
        Map.Entry<Field, Integer> entry;
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<Field, Integer>) iterator.next();
                System.out.println("Field索引:"+ mapIndex + "   Key:"+entry.getKey().getName() + "     Value:"+entry.getValue());
                mapIndex++;
            }
        }
    }

    /**
     * 获取当前类声明的private/protected变量
     */
    static public Object getFieldValue(Object object, String propertyName)
            throws IllegalAccessException, NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);

        return field.get(object);
    }

    /**
     * @param object
     * @param propertyName
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getFieldValueInAllSuper(Object object, String propertyName)
            throws IllegalAccessException, NoSuchFieldException {
        Class claszz = object.getClass();
        Field field = null;

        do {
            try {
                field = claszz.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                //e.printStackTrace();
                field = null;
            }
            claszz = claszz.getSuperclass();
        }
        while (field == null && claszz != null);

        if (field == null) return null;

        field.setAccessible(true);
        return field.get(object);
    }






    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    public static int getChineseCount(String oriStr) {
        int count = 0;
        for (int i = 0; i < oriStr.length(); i++) {
            char itemChar = oriStr.charAt(i);
            /*

|| (itemChar+"").equals("，")
|| (itemChar+"").equals("’")
|| (itemChar+"").equals("‘")

|| (itemChar+"").equals("；")
             */
            if ((itemChar + "").equals("：")
                    || (itemChar + "").equals("】") || (itemChar + "").equals("【") || (itemChar + "").equals("）")
                    || (itemChar + "").equals("（") || (itemChar + "").equals("￥") || (itemChar + "").equals("—")
                    || (itemChar + "").equals("？") || (itemChar + "").equals("，") || (itemChar + "").equals("；")
                    || (itemChar + "").equals("！") || (itemChar + "").equals("《")
                    || (itemChar + "").equals("》") || (itemChar + "").equals("。") || (itemChar + "").equals("、")
                    ) {
                count++;
                continue;
            }
            boolean isChinese = isContainChinese(itemChar + "");
            if (isChinese) {
                count++;
            }
        }
        return count;
    }


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    static String TOP_LINE_PRE = "╔";  //  最顶层表头
    static String TTOP_LINE_MIDDLE = "╦";
    static String TOP_LINE_END = "╗";


    static String BUTTOM_LINE_PRE = "╚";
    static String BUTTOM_LINE_MIDDLE = "╩";
    static String BUTTOM_LINE_END = "╝";


    static String ROW_LINE_PRE = "╠";
    static String ROW_LINE_MIDDLE = "═";
    static String ROW_LINE_Content_Cell = "╬";
    static String ROW_LINE_END = "╣";


    static String CONTENT_SPACE = "║";

    // 如果长度没有超过默认的长度  那么名字的padding-length 默认为 8
    static int DEFAULT_CONTENT_PADDING_SIZE_MIN = 8;  // 最小单元
    static int DEFAULT_CONTENT_PADDING_SIZE_MAX = 50; // 最大党员


    //  TablePrint=======================End

}


//

/*
=======================字符集Begin=======================
索引:1   字符集名称: Big5
索引:2   字符集名称: Big5-HKSCS
索引:3   字符集名称: CESU-8
索引:4   字符集名称: EUC-JP
索引:5   字符集名称: EUC-KR
索引:6   字符集名称: GB18030
索引:7   字符集名称: GB2312
索引:8   字符集名称: GBK
索引:9   字符集名称: IBM-Thai
索引:10   字符集名称: IBM00858
索引:11   字符集名称: IBM01140
索引:12   字符集名称: IBM01141
索引:13   字符集名称: IBM01142
索引:14   字符集名称: IBM01143
索引:15   字符集名称: IBM01144
索引:16   字符集名称: IBM01145
索引:17   字符集名称: IBM01146
索引:18   字符集名称: IBM01147
索引:19   字符集名称: IBM01148
索引:20   字符集名称: IBM01149
索引:21   字符集名称: IBM037
索引:22   字符集名称: IBM1026
索引:23   字符集名称: IBM1047
索引:24   字符集名称: IBM273
索引:25   字符集名称: IBM277
索引:26   字符集名称: IBM278
索引:27   字符集名称: IBM280
索引:28   字符集名称: IBM284
索引:29   字符集名称: IBM285
索引:30   字符集名称: IBM290
索引:31   字符集名称: IBM297
索引:32   字符集名称: IBM420
索引:33   字符集名称: IBM424
索引:34   字符集名称: IBM437
索引:35   字符集名称: IBM500
索引:36   字符集名称: IBM775
索引:37   字符集名称: IBM850
索引:38   字符集名称: IBM852
索引:39   字符集名称: IBM855
索引:40   字符集名称: IBM857
索引:41   字符集名称: IBM860
索引:42   字符集名称: IBM861
索引:43   字符集名称: IBM862
索引:44   字符集名称: IBM863
索引:45   字符集名称: IBM864
索引:46   字符集名称: IBM865
索引:47   字符集名称: IBM866
索引:48   字符集名称: IBM868
索引:49   字符集名称: IBM869
索引:50   字符集名称: IBM870
索引:51   字符集名称: IBM871
索引:52   字符集名称: IBM918
索引:53   字符集名称: ISO-2022-CN
索引:54   字符集名称: ISO-2022-JP
索引:55   字符集名称: ISO-2022-JP-2
索引:56   字符集名称: ISO-2022-KR
索引:57   字符集名称: ISO-8859-1
索引:58   字符集名称: ISO-8859-13
索引:59   字符集名称: ISO-8859-15
索引:60   字符集名称: ISO-8859-2
索引:61   字符集名称: ISO-8859-3
索引:62   字符集名称: ISO-8859-4
索引:63   字符集名称: ISO-8859-5
索引:64   字符集名称: ISO-8859-6
索引:65   字符集名称: ISO-8859-7
索引:66   字符集名称: ISO-8859-8
索引:67   字符集名称: ISO-8859-9
索引:68   字符集名称: JIS_X0201
索引:69   字符集名称: JIS_X0212-1990
索引:70   字符集名称: KOI8-R
索引:71   字符集名称: KOI8-U
索引:72   字符集名称: Shift_JIS
索引:73   字符集名称: TIS-620
索引:74   字符集名称: US-ASCII
索引:75   字符集名称: UTF-16
索引:76   字符集名称: UTF-16BE
索引:77   字符集名称: UTF-16LE
索引:78   字符集名称: UTF-32
索引:79   字符集名称: UTF-32BE
索引:80   字符集名称: UTF-32LE
索引:81   字符集名称: UTF-8
索引:82   字符集名称: windows-1250
索引:83   字符集名称: windows-1251
索引:84   字符集名称: windows-1252
索引:85   字符集名称: windows-1253
索引:86   字符集名称: windows-1254
索引:87   字符集名称: windows-1255
索引:88   字符集名称: windows-1256
索引:89   字符集名称: windows-1257
索引:90   字符集名称: windows-1258
索引:91   字符集名称: windows-31j
索引:92   字符集名称: x-Big5-HKSCS-2001
索引:93   字符集名称: x-Big5-Solaris
索引:94   字符集名称: x-euc-jp-linux
索引:95   字符集名称: x-EUC-TW
索引:96   字符集名称: x-eucJP-Open
索引:97   字符集名称: x-IBM1006
索引:98   字符集名称: x-IBM1025
索引:99   字符集名称: x-IBM1046
索引:100   字符集名称: x-IBM1097
索引:101   字符集名称: x-IBM1098
索引:102   字符集名称: x-IBM1112
索引:103   字符集名称: x-IBM1122
索引:104   字符集名称: x-IBM1123
索引:105   字符集名称: x-IBM1124
索引:106   字符集名称: x-IBM1166
索引:107   字符集名称: x-IBM1364
索引:108   字符集名称: x-IBM1381
索引:109   字符集名称: x-IBM1383
索引:110   字符集名称: x-IBM300
索引:111   字符集名称: x-IBM33722
索引:112   字符集名称: x-IBM737
索引:113   字符集名称: x-IBM833
索引:114   字符集名称: x-IBM834
索引:115   字符集名称: x-IBM856
索引:116   字符集名称: x-IBM874
索引:117   字符集名称: x-IBM875
索引:118   字符集名称: x-IBM921
索引:119   字符集名称: x-IBM922
索引:120   字符集名称: x-IBM930
索引:121   字符集名称: x-IBM933
索引:122   字符集名称: x-IBM935
索引:123   字符集名称: x-IBM937
索引:124   字符集名称: x-IBM939
索引:125   字符集名称: x-IBM942
索引:126   字符集名称: x-IBM942C
索引:127   字符集名称: x-IBM943
索引:128   字符集名称: x-IBM943C
索引:129   字符集名称: x-IBM948
索引:130   字符集名称: x-IBM949
索引:131   字符集名称: x-IBM949C
索引:132   字符集名称: x-IBM950
索引:133   字符集名称: x-IBM964
索引:134   字符集名称: x-IBM970
索引:135   字符集名称: x-ISCII91
索引:136   字符集名称: x-ISO-2022-CN-CNS
索引:137   字符集名称: x-ISO-2022-CN-GB
索引:138   字符集名称: x-iso-8859-11
索引:139   字符集名称: x-JIS0208
索引:140   字符集名称: x-JISAutoDetect
索引:141   字符集名称: x-Johab
索引:142   字符集名称: x-MacArabic
索引:143   字符集名称: x-MacCentralEurope
索引:144   字符集名称: x-MacCroatian
索引:145   字符集名称: x-MacCyrillic
索引:146   字符集名称: x-MacDingbat
索引:147   字符集名称: x-MacGreek
索引:148   字符集名称: x-MacHebrew
索引:149   字符集名称: x-MacIceland
索引:150   字符集名称: x-MacRoman
索引:151   字符集名称: x-MacRomania
索引:152   字符集名称: x-MacSymbol
索引:153   字符集名称: x-MacThai
索引:154   字符集名称: x-MacTurkish
索引:155   字符集名称: x-MacUkraine
索引:156   字符集名称: x-MS932_0213
索引:157   字符集名称: x-MS950-HKSCS
索引:158   字符集名称: x-MS950-HKSCS-XP
索引:159   字符集名称: x-mswin-936
索引:160   字符集名称: x-PCK
索引:161   字符集名称: x-SJIS_0213
索引:162   字符集名称: x-UTF-16LE-BOM
索引:163   字符集名称: X-UTF-32BE-BOM
索引:164   字符集名称: X-UTF-32LE-BOM
索引:165   字符集名称: x-windows-50220
索引:166   字符集名称: x-windows-50221
索引:167   字符集名称: x-windows-874
索引:168   字符集名称: x-windows-949
索引:169   字符集名称: x-windows-950
索引:170   字符集名称: x-windows-iso2022jp
=======================字符集End=======================
* */