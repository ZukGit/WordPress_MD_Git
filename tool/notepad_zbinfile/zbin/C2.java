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

public class C2 {
    public static final ArrayList<String> StringArr = new ArrayList<>();

    public static void main(String[] args) {
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
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




                BufferedReader curBR  = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)),"utf-8"));
                String oldOneLine = "";
                String newOneLine = "";
                int indexLine = 0;

                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if(oldOneLine == null || oldOneLine.trim().isEmpty()){
                        continue;
                    }
                    StringArr.add(oldOneLine);
                }
                curBR.close();

                String oneLineCode = fixMulStringToCode(StringArr);

                BufferedWriter curBW  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)),"utf-8"));


                curBW.write(oneLineCode);
                curBW.flush();
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("Failed !");
        }
    }

    static  String fixMulStringToCode(ArrayList<String> strList ){
        StringBuilder sb = new StringBuilder();
        for (String curString: strList) {

            String fixBlock = curString.replace("\"" , "ZKONGGE");
            String fix2 = fixBlock.replace("ZKONGGE" , "\\\"");
            sb.append(fix2);
        }
        return "String codeLog = \""+sb.toString() + "\";";
    }
}
