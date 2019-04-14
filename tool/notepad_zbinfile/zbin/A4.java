import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class A4 {
    public static final ArrayList<String> StringArr = new ArrayList<>();
    /*paramString 替换规则:
    1.【z_z = 空格】
    2.【z# = =等号】
    3.【z[ = <】
    4.【z] = >】
    5. 【z`= 引号"】
        */

    public static String acceptRule(String param) {
        if (param == null) {
            return null;
        }
        if (param.contains("z_z")) {
            param = param.replaceAll("z_z", " ");
        }
        if (param.contains("z#")) {
            param = param.replaceAll("z#", "=");
        }
        while (param.contains("z[")) {
            param = param.replace("z[", "<");
        }

        if (param.contains("z]")) {
            param = param.replaceAll("z]", ">");

        }
        if (param.contains("z`")) {
            param = param.replaceAll("z`", "\"");
        }
        // accept rule param = pre=<audio><source src#end=/><audio>
        System.out.println("accept rule param = "+ param);
        return param;
    }

    public static void main(String[] args) {
        //===============VS-test===============
        String mFilePath = null;
        String preString = null;
        String endString = null;
        if (args.length >= 2) {
            mFilePath = args[0];
            String paramString = args[1]; //  prez#"xxxx"endz#"yyyy"
            System.out.println("mFilePath =" + mFilePath + "  paramString = " + paramString);
            if (paramString != null && paramString.contains("prez") && paramString.contains("endz")) {
                // 前后都要添加字符串的情况
                if (paramString.indexOf("prez") < paramString.indexOf("endz")) {
                    preString = paramString.substring(paramString.indexOf("#") + 1, paramString.indexOf("endz"));
                    endString = paramString.substring(paramString.lastIndexOf("#") + 1, paramString.length());
                    System.out.println("zukgit 1 ");
                } else {  //  endz#"yyyy"prez#"xxxx"
                    System.out.println("zukgit 2 ");
                    endString = paramString.substring(paramString.indexOf("#") + 1, paramString.indexOf("prez"));
                    preString = paramString.substring(paramString.lastIndexOf("#") + 1, paramString.length());

                }

            } else if (paramString != null && paramString.contains("prez")) {
                System.out.println("zukgit 3 ");
                // 只包含要在行首添加字符串的情况   prez#"xxxx"
                preString = paramString.substring(paramString.indexOf("#") + 1, paramString.length());
                endString = "";
            } else if (paramString != null && paramString.contains("endz")) {
                // 只包含要在行尾添加字符串的情况  endz#"xxxx"
                System.out.println("zukgit 4 ");
                preString = "";
                endString = paramString.substring(paramString.indexOf("#") + 1, paramString.length());
            } else {
                System.out.println("input argument pre=xxxx;end=yyyy is error ! retry input again!");
                return;
            }
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
//===============local-test===============
//        String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";


        if ((preString != null && preString.trim().isEmpty()) && (endString != null && endString.trim().isEmpty())) {
            System.out.println("preString and endString both empty ");
            return;
        } else{
            preString = acceptRule(preString);
            endString = acceptRule(endString);
        }


        System.out.println("preString =  " + preString + " endString =" + endString);

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
                int indexLine = 0;

                while (oldOneLine != null) {
                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }
                    indexLine++;
                    newOneLine = preString + oldOneLine.trim() + endString;
                    StringArr.add(newOneLine);
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
}
