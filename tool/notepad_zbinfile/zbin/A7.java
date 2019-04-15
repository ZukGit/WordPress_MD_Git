import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A7 {
    public static final ArrayList<String> StringArr = new ArrayList<>();
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
                    StringArr.add(newOneLine.trim());
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