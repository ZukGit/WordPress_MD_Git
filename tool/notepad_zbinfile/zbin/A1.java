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

public class A1 {
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
                    indexLine++;
                    newOneLine = indexLine + "      " + oldOneLine;
                    StringArr.add(newOneLine);
                }
                curBR.close();



                BufferedWriter curBW  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)),"utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("Failed !");
        }
    }
}
