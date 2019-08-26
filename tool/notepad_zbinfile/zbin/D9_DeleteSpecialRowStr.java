
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

public class D9_DeleteSpecialRowStr {
    public static final ArrayList<String> StringArr = new ArrayList<>();
/*paramString 替换规则:
1.【z_z = 空格】
2.【z# = =等号】
3.【z[ = <】
4.【z] = >】
5. 【z`= 引号"】
6.  【\\ = 双斜杆 只表示一个斜杆】
*/

// 1.实现对当前 notepad 打开的文件的 操作 实现 把 特定字符串之前的 字符串删除   (保留后   默认 )
// 2. 实现对当前 notepad 打开的文件的 操作 实现 把 特定字符串之后的 字符串删除 ( 保留前)


// endz  保留end字符串尾部  引号内包含的是特殊符号   前部_特殊符号_后部
//cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  endz#"yyyy"

//  prez   保留后者字符串前部
//cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"xxxx"


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
        System.out.println("accept rule param = " + param);
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
//                if (paramString.indexOf("prez") < paramString.indexOf("endz")) {
//                    preString = paramString.substring(paramString.indexOf("#") + 1, paramString.indexOf("endz"));
//                    endString = paramString.substring(paramString.lastIndexOf("#") + 1, paramString.length());
//                    System.out.println("zukgit 1 ");
//                } else {  //  endz#"yyyy"prez#"xxxx"
//                    System.out.println("zukgit 2 ");
//                    endString = paramString.substring(paramString.indexOf("#") + 1, paramString.indexOf("prez"));
//                    preString = paramString.substring(paramString.lastIndexOf("#") + 1, paramString.length());
//
//                }
                System.out.println("输入格式错误: "+"mFilePath =" + mFilePath + "  paramString = " + paramString);
                System.out.println("输入格式样例一(保留尾部): "+"D9_DeleteSpecialRowStr  endz#\"yyyy\"");
                System.out.println("输入格式样例一(保留头部): "+"D9_DeleteSpecialRowStr  prez#\"yyyy\"");
                return;
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
        } else {
            preString = acceptRule(preString);
            endString = acceptRule(endString);
        }


        if(preString != null && !"".equals(preString.trim())){
            System.out.println("preString =  " + preString);
        }else  if(endString != null && !"".equals(endString.trim())){
            System.out.println("endString =  " + endString);
        }

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
                 //   newOneLine = preString + oldOneLine.trim() + endString;
                    if(preString != null && !"".equals(preString.trim())){

                        if(oldOneLine.contains(preString)){
                            // 截取 字符串  取 前面的字符
                      String subStr = oldOneLine.substring(0,oldOneLine.indexOf(preString));  // 取到第一次匹配的到的字符串
                      newOneLine = subStr;

                        }else{
                            newOneLine = oldOneLine;
                        }


                    }else if(endString != null && !"".equals(endString.trim())){


                        if(oldOneLine.contains(endString)){
                            // 截取 字符串  取 前面的字符
                            String subStr = oldOneLine.substring(oldOneLine.lastIndexOf(endString) + endString.length());  // 取到最后匹配的到的字符串
                            newOneLine = subStr;

                        }else{
                            newOneLine = oldOneLine;
                        }


                    }


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