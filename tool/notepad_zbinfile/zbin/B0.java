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
 * 读取txt文件所在目录的所有其他文件
 *
 * @author Red
 */
public class B0 {
    public static final ArrayList<String> stringArrTitleOrigin = new ArrayList<>();  // 最原始的名字 _ 包含中文  应该去处 .mp3  文件后缀
    public static final ArrayList<String> stringArrSource = new ArrayList<>();  // 资源名称 这里不应该包含中文了
    public static final ArrayList<String> stringArrTitleEnglish = new ArrayList<>();

    public static final ArrayList<String> missLineArr = new ArrayList<>();

    public static final ArrayList<String> StringArr = new ArrayList<>();
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";
    public static final Map<String, String> english2originMap = new HashMap<String, String>();
    public static final Map<String, String> origin2englishMap = new HashMap<String, String>();
    public static File curDirFile;
    public static String curDirPath;
    public static String mFilePath;
    public static ArrayList<File>   curFileList  = new ArrayList<File>();
    private static String sourcetemplatePre = "";
    private static String sourcetemplateEnd = "";
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
        mFilePath = null;
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
        // mFilePath =  C:\Users\Administrator\Desktop\test\test.txt
        System.out.println("input argument success mFilePath = " + mFilePath);
        curDirPath = mFilePath.substring(0, mFilePath.lastIndexOf("\\"));
        curDirFile = new File(curDirPath);
        if (curDirFile == null || !curDirFile.exists() || !curDirFile.isDirectory()) {
            return;
        }

        for(File fileItem: curDirFile.listFiles()) {
            if(fileItem.isFile()){
                curFileList.add(fileItem);
            }

        }



        if (curFileList.size() > 1) {
            fillOriginArr(curFileList);   // 填充  stringArrTitleOrigin  和  stringArrSource
            System.out.println("zukgit4 = ");
            fillSourceArr();  // 填充 资源  这个资源的名字  应该是英文的 !
            System.out.println("zukgit5 = ");
            fillTitleEnglish();   //  填充 stringArrTitleEnglish
            System.out.println("zukgit6 = ");
            matchTitleAndSource();
            System.out.println("zukgit7 = ");
            // findMissMatch();
            if (missLineArr.size() > 0) {
                missLineArr.add(0, "未匹配项如下: ");
                StringArr.addAll(missLineArr);
            }
            try {
                System.out.println("zukgit8 = ");
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


            // 开始修改对应的文件名称
            for (File fileItem : curFileList) {
                if (fileItem.getAbsolutePath().equals(mFilePath)) {
                    continue;
                }
                // file:shang_hai_yi_jiu_si_san.mp3    renameTo flag =true   englishName=null
                String englishName = "";
                String fileType = "";
                String fileName   =  fileItem.getName();  // xxx.mp3
                if(!isContainChinese(fileName)){
                    englishName =fileName;
                    fileType="";
                }else{
                    if(fileName.contains(".")){
                        fileType =fileName.substring(fileName.lastIndexOf("."),fileName.length());
                        englishName = ToPinyinWithLine(fileName.substring(0,fileName.lastIndexOf(".")));
                    }else{
                        fileType="";
                        englishName=ToPinyinWithLine(fileName);
                    }
                }

                boolean flag = fileItem.renameTo(new File(curDirPath+"\\"+englishName+fileType));
                System.out.println("file:"+fileItem.getName()+"    renameTo flag ="+ flag +"   englishName="+ englishName+" fileType"+ fileType);
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
        for (String item : allLine) {
            if (!StringArr.contains(item.trim())) {
                missLineArr.add(item);
            }
        }
    }


    public static void matchTitleAndSource() {
        if (stringArrTitleEnglish.size() > 0 && stringArrSource.size() > 0) {

            for (String item1 : stringArrTitleEnglish) {
                for (String item2 : stringArrSource) {
                    if (item2.contains(item1)) {
                        String name = english2originMap.get(item1).trim();
                        if(name.contains(".")){
                            StringArr.add("#### "+name.substring(0,name.lastIndexOf(".")));
                        }else{
                            StringArr.add("#### "+name);
                        }

                        StringArr.add(item2.trim());
                    }

                }

            }
        }
    }




    //  填充 stringArrTitleEnglish and  english2originMap
    // 把中文的名字转为英文的名字 放到数组中
    public static void fillTitleEnglish() {
        if (stringArrTitleOrigin.size() > 0) {
            for (String item : stringArrTitleOrigin) {
                if (isContainChinese(item)) {
                    String str = fixChineseLine(item);
                    if (str != null && !str.trim().isEmpty()) {
                        stringArrTitleEnglish.add(str);
                        english2originMap.put(str, item);
                        //  zukgit9 Englisstr=san_nian_yi_ban_.mp3       origin=三年一班.mp3
                        System.out.println("zukgit9 Englisstr="+str+"       origin="+item);
//                        origin2englishMap.put(item,str );
                    }
                } else {
                    String str = fixLine(item);
                    if (str != null && !str.trim().isEmpty()) {
                        stringArrTitleEnglish.add(str);
                        english2originMap.put(str, item);
                        System.out.println("zukgit9 Englisstr="+str+"       origin="+item);
//                        origin2englishMap.put(item,str );
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
    //<audio controls><source src="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/an_jing.mp3" type="audio/mpeg"/></audio>
    // 填充   stringArrSource
    // <audio controls><source src="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/an_jing.mp3"
// mFilePath =  C:\Users\Administrator\Desktop\test\test.txt
// mFilePath = G:\Git_Dir\Z_Music\music\chinese\li_rong_hao
    // <audio controls><source src="C:\Users\Administrator\Desktop\test\test.txt"
    public static void fillSourceArr() {
        if(mFilePath.contains("Z_Music") && curDirPath.contains("Z_Music")){
            String temp = curDirPath.substring(curDirPath.lastIndexOf("Z_Music")+"Z_Music".length()+1,curDirPath.length())+"/";
            System.out.println("zukgit1 = ");
            while(temp.contains("\\")){
                temp = temp.replace("\\","/");
            }
            System.out.println("zukgit2 = ");
            //Z_Music/master/music\chinese\test/ta
            sourcetemplatePre="<audio controls><source src=\"https://raw.githubusercontent.com/ZukGit/Z_Music/master/"+temp;
            sourcetemplateEnd="\" type=\"audio/mpeg\"/></audio>";

            for(File itemFile: curFileList){
                if (itemFile.getAbsolutePath().equals(mFilePath)) {
                    continue;
                }
                String fileName = itemFile.getName();
                String fileType = "";
                if(fileName.contains(".")){
                    fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length());
                    fileName =fileName.substring(0,fileName.lastIndexOf("."));
                }
                stringArrSource.add(sourcetemplatePre+ToPinyinWithLine(fileName)+fileType+sourcetemplateEnd);
            }

        }else{
            sourcetemplatePre="<audio controls><source src=\"";
            sourcetemplateEnd="\" type=\"audio/mpeg\"/></audio>";

            for(File itemFile: curFileList){
                if (itemFile.getAbsolutePath().equals(mFilePath)) {
                    continue;
                }
                String fileName = itemFile.getName();
                String fileType = "";
                if(fileName.contains(".")){
                    fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length());
                    fileName =fileName.substring(0,fileName.lastIndexOf("."));
                }
                stringArrSource.add(sourcetemplatePre+curDirPath+"\\"+ToPinyinWithLine(fileName)+fileType+sourcetemplateEnd);
            }
        }
    }


    // 填充  stringArrTitleOrigin
    public static void fillOriginArr(ArrayList<File> curFileList) {
        try {
            for (File fileItem : curFileList) {
                if (fileItem.getAbsolutePath().equals(mFilePath)) {
                    continue;
                }
                String fileName = fileItem.getName();
                if(fileName.contains(".")){
                    fileName = fileName.substring(0,fileName.lastIndexOf("."));
                }
                stringArrTitleOrigin.add(fileName);

            }


//            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
//            String oldOneLine = "";
//            String newOneLine = "";
//
//            while (oldOneLine != null) {
//
//                oldOneLine = curBR.readLine();
//                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
//                    continue;
//                }
//
//                newOneLine = new String(oldOneLine);
//                if (newOneLine.contains("#")) {
//                    stringArrTitleOrigin.add(newOneLine);
//                } else {
//                    stringArrSource.add(newOneLine);
//                }
//            }
//            curBR.close();
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