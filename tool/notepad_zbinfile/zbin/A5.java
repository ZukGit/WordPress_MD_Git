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
import java.util.HashMap;

public class A5 {
    public static final ArrayList<String> tableItemList = new ArrayList<>();
    public static int rowInLine = 0;


    public static void getRowInLine(File file) {

        String titleString;
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while ((titleString = curBR.readLine()) != null && !titleString.isEmpty()) {
                break;
            }
            while (titleString.contains("  ")) {
                titleString = titleString.replaceAll("  ", " ");
            }

            String[] strArr = titleString.split(" ");
            rowInLine = strArr.length;

            String sumString = "";
            for (String item : strArr) {
                sumString = sumString + " | " + item;
            }
            sumString = sumString + " |";
            tableItemList.add(sumString);

            String twoLine = "";
            for (int i = 0; i < rowInLine; i++) {

                twoLine = twoLine + "| ---- ";
            }
            twoLine = twoLine + "| ";
            tableItemList.add(twoLine);


            curBR.close();
        } catch (Exception e) {


        }
    }

    public static void main(String[] args) {
        //===============real-test-egin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        //===============real-test-end===============

        //===============local-test begin===============
//          String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
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

        getRowInLine(curFile);
        if (curFile != null) {
            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";

                while ((oldOneLine = curBR.readLine()) != null && !oldOneLine.isEmpty()) {
                    break;   // 跳过首行 当做标题的那行
                }

                oldOneLine = "";
                while (oldOneLine != null) {
                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.isEmpty()) {
                        continue;
                    }
                    String tableItem = new String(oldOneLine);


                    while (tableItem.contains("  ")) {
                        tableItem = tableItem.replaceAll("  ", " ");
                    }
                    String[] strArr = tableItem.split(" ");
                    int length = 0;   //
                    if (strArr.length >= rowInLine) {
                        length = rowInLine;
                    } else {
                        length = strArr.length;
                    }
                    String sumString = "";
                    for (int i = 0; i < length; i++) {
                        sumString = sumString + " | " + strArr[i];
                    }
                    sumString = sumString + " |";
                    if (length < rowInLine) {
                        int blankCount = rowInLine - length;
                        for (int j = 0; j < blankCount; j++) {
                            sumString = sumString + "  | ";
                        }
                    }
                    tableItemList.add(sumString);
                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < tableItemList.size(); i++) {
                    curBW.write(tableItemList.get(i).trim());
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
