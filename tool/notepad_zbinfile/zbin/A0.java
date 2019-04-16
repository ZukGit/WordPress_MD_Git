import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 汉字转换为拼音
 *
 * @author Red
 */
public class A0 {
    public static final ArrayList<String> stringArrTitleOrigin = new ArrayList<>();
    public static final ArrayList<String> stringArrSource = new ArrayList<>();
    public static final ArrayList<String> missLineArr = new ArrayList<>();
    public static final ArrayList<String> stringArrTitleEnglish = new ArrayList<>();
    public static final ArrayList<String> StringArr = new ArrayList<>();
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";
    public static final Map<String,String> english2originMap = new HashMap<String,String>();
    /*
    1.读取文件 中 包含 # 号的那些行  放入 stringArrTitleOrigin( 可能包含汉字 ) ,
      把 stringArrTitleOrigin 中的中文转为拼音 放入到 stringArrTitleEnglish  建立对应关系
    2.读取# 号中 不包含 # 号的 那些行  放入  stringArrSource
    3.循环 stringArrTitleEnglish 中的关键词 , 在  stringArrSource 中找到 匹配项
      找到的话 就加入 按顺序  title 加入到 StringArr ， source 加入到 StringArr
     4. 最后打印这个 StringArr
     5. 打印没有匹配到的那些项
        */
    public static void main(String[] args) {

        //System.out.println(ToFirstChar("ABC  汉字转换为拼音CBA").toUpperCase()); //转为首字母大写
        // System.out.println(ToPinyinWithLine("A周 B杰 C伦"));
        // System.out.println(ToPinyinWithLine("ABC汉字转换为拼音CBA"));

        //===============real-test begin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        //===============real-test end===============


        //===============local-test begin===============
        //      String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";
        //===============local-test end===============


        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        if (curFile != null) {
            fillOriginArr(curFile);   // 填充  stringArrTitleOrigin  和  stringArrSource
            fillTitleEnglish();   //  填充 stringArrTitleEnglish
            matchTitleAndSource();
            findMissMatch();
            if(missLineArr.size() > 0){
                missLineArr.add(0,"未匹配项如下: ");
                StringArr.addAll(missLineArr);
            }
            try {

                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }



    public static void findMissMatch() {

//        public static final ArrayList<String> stringArrTitleOrigin = new ArrayList<>();
//        public static final ArrayList<String> stringArrSource = new ArrayList<>();
//        public static final ArrayList<String>  StringArr = new ArrayList<>();
        ArrayList<String> allLine = new ArrayList<>();
        allLine.addAll(stringArrTitleOrigin);
        allLine.addAll(stringArrSource);
        for (String item: allLine){
             if(!StringArr.contains(item.trim())){
                 missLineArr.add(item);
             }
        }
    }


    public static void matchTitleAndSource() {
        if(stringArrTitleEnglish.size() > 0 && stringArrSource.size() > 0){

            for (String item1: stringArrTitleEnglish){
                        for(String item2: stringArrSource){
                            if(item2.contains(item1)){
                                StringArr.add(english2originMap.get(item1).trim());
                                StringArr.add(item2.trim());
                            }

                        }

            }
        }
    }

    //  填充 stringArrTitleEnglish and  english2originMap
    public static void fillTitleEnglish() {
        if (stringArrTitleOrigin.size() > 0) {
            for (String item : stringArrTitleOrigin) {
                if (isContainChinese(item)) {
                    String str = fixChineseLine(item);
                    if (str != null && !str.trim().isEmpty()) {
                        stringArrTitleEnglish.add(str);
                        english2originMap.put(str,item);
                    }
                } else {
                    String str = fixLine(item);
                    if (str != null && !str.trim().isEmpty()) {
                        stringArrTitleEnglish.add(str);
                        english2originMap.put(str,item);
                    }
                }
            }
        }
    }

    public static String fixChineseLine(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(str);

        while (curItem.contains("#")) {
            curItem = curItem.replaceAll("#", "").trim();
        }
        while (isContainChinese(curItem)) {
            curItem = ToPinyinWithLine(curItem);
        }

        return curItem;
    }

    public static String fixLine(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(str);

        while (curItem.contains("#")) {
            curItem = curItem.replaceAll("#", "").trim();
        }
        return curItem;
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile(REGEX_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    // 填充  stringArrTitleOrigin  和  stringArrSource
    public static void fillOriginArr(File curFile) {
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
                if (newOneLine.contains("#")) {
                    stringArrTitleOrigin.add(newOneLine);
                } else {
                    stringArrSource.add(newOneLine);
                }
            }
            curBR.close();
        } catch (Exception e) {

        }

    }

    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese
     * @return
     */
    public static String ToFirstChar(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);  // charAt(0)
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
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0]; // [0] 标识当前拼音 汉-> han
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {  // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
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
    public static String ToPinyinWithLine(String chinese) {
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
                    pinyinStr += "_" + PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0] + "_"; // [0] 标识当前拼音 汉-> han

                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {  // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
                pinyinStr += newChar[i];
            }
        }
        while (pinyinStr.contains("__")) {
            pinyinStr = pinyinStr.replaceAll("__", "_");
            System.out.println("pinyinStr1 = " + pinyinStr);
        }
		
        while (pinyinStr.contains("u:")) {  // 女转为 nu:   绿 lu:   需要转为 nv  lv
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

}