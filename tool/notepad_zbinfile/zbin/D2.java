
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D2 {
    public static ArrayList<String> StringArr = new ArrayList<>();   // 去除中文后的每行内容
    public static ArrayList<String> mHtmlStringArr = new ArrayList<>();   // 添加url路径的集合
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    public static void main(String[] args) {
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

            FileReader curReader;
            FileWriter curWriter;
            try {

                curReader = new FileReader(curFile);


                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";


                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }

                    newOneLine = new String(oldOneLine);
                    if (isContainChinese(newOneLine)) {
                        newOneLine = clearChinese(newOneLine);

                    }
                    StringArr.add(newOneLine);
                }
                curBR.close();

                for (String rowStr : StringArr) {
                    toGetUrlFromRow(rowStr);
                }


                writeContent2File(new File(mFilePath), mHtmlStringArr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }

    // 对每行的数据进行分析
    public static void toGetUrlFromRow(String rowString) {
        String[] strArrRow = null;
        String fixStr = "";

//        if(str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
//                str.trim().startsWith("thunder:") ||   str.trim().startsWith("magnet::") ){


        if (rowString != null) {
            fixStr = new String(rowString);
            // http://xxxxxx/sahttp://  避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
            fixStr = fixStr.replace("http:", " http:");
            fixStr = fixStr.replace("https:", " https:");
            fixStr = fixStr.replace("thunder:", " thunder:");
            fixStr = fixStr.replace("magnet:", " magnet:");
            strArrRow = fixStr.split(" ");
        }

        if (strArrRow != null && strArrRow.length > 0) {

            for (int i = 0; i < strArrRow.length; i++) {
                String mCurContent = strArrRow[i];
                if (mCurContent == null || mCurContent.trim().equals("")) {
                    continue;
                }


                boolean isUrl = toJudgeUrl(mCurContent);
                if(isUrl){
                    mHtmlStringArr.add(mCurContent.trim());

                }

            }


        }


    }


    public static boolean toJudgeUrl(String str) {
        boolean isUrl = false;

        if (str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
                str.trim().startsWith("thunder:") || str.trim().startsWith("magnet:")) {

            return true;
        }


        return isUrl;
    }

    public static void writeContent2File(File targetFile, ArrayList<String> strList) {

        BufferedWriter curBW = null;
        try {
            curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "utf-8"));

            for (int i = 0; i < strList.size(); i++) {
                curBW.write(strList.get(i));
                curBW.newLine();
            }
            curBW.close();
            System.out.println("OK !");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile(REGEX_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }

}
