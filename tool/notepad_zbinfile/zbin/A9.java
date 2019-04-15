import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.util.ArrayList;

/**
 * 汉字转换为拼音
 *
 * @author Red
 */
public class A9 {

    public static final ArrayList<String> StringArr = new ArrayList<>();

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
            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";

                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }

                    newOneLine = new String(oldOneLine);
                    newOneLine = ToPinyinWithLine(newOneLine);
                    if (newOneLine != null && !newOneLine.trim().isEmpty()) {
                        StringArr.add(newOneLine);
                    }

                }
                curBR.close();


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