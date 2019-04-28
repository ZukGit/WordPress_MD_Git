import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.RuntimeUtil;

import java.io.*;
import java.util.ArrayList;

public class A6 {

    static String dirPath = "";
    static ArrayList<String> curFileStringList = new ArrayList<String>();
    static ArrayList<String> clipStringList = new ArrayList<String>();
    static int SPACE = 12;
    static int MAX_LENGTH_CLIP = 0;

    static void tryReadContentFromFile(File file) {

        if (file != null && file.exists()) {

            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                String lineStr = "";


                while (lineStr != null) {
                    lineStr = curBR.readLine();
                    if (lineStr == null || lineStr.trim().isEmpty()) {
                        continue;
                    }
                    curFileStringList.add(lineStr);
                }
                curBR.close();


                System.out.println("read json File OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }


    static String getSpace(int num) {
        String str = "";
        for (int i = 0; i < num; i++) {
            str = str + " ";
        }


        return str;
    }


    static void tryWriteNewContentToFile(ArrayList<String> clipList, ArrayList<String> oldList, File file) {

        if (file != null && file.exists()) {

            try {
// String curItem = "#  "+new String(netAddr);
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                // curBW.write(curItem);

                int clipListSize = clipList.size();
                int oldListListSize = oldList.size();
                int maxRow = 0;
                if (clipListSize > oldListListSize) {
                    maxRow = clipListSize;
                } else {
                    maxRow = oldListListSize;
                }

                for (int i = 0; i < maxRow; i++) {
                    String clipItem =null;
                    if(i >= clipList.size()){
                        clipItem = "";
                    }else{
                        clipItem = clipList.get(i).trim();
                    }

                    String oldItem =null;
                    if(i >= oldList.size()){
                        oldItem = "";
                    }else{
                        oldItem = oldList.get(i).trim();
                    }

                    int currentNeedSpace = MAX_LENGTH_CLIP - (clipItem != null ? clipItem.length() : 0) + SPACE;
                    String newItem = (clipItem != null ? clipItem.trim() : "")+ getSpace(currentNeedSpace) + oldItem;
                    curBW.write(newItem);
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("write file OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }


    static int getMaxLengthInArr(String[] arr) {
        int maxLength = 0;
        if (arr != null) {
            int arrlength = arr.length;

            for (int i = 0; i < arrlength; i++) {
                String item = arr[i];
                    int curssidLength = item.length();
                    if (curssidLength > maxLength) {
                        maxLength = curssidLength;
                    }
            }

        }
        return maxLength;
    }

    public static void main(String[] args) {

      //  ===============real-test begin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
       // ===============real-test end===============

        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }


        String clipStr = ClipboardUtil.getStr().trim();
        System.out.println("clipStr = " + clipStr);
        if (clipStr.trim().equals("")) {
            System.out.println("clip String is empty or null!");
            return;
        }

        String[] arrLine = clipStr.split("\n");
        int arrLength = arrLine.length;
        int currentMaxLength = 0;
        if (arrLine != null) {
            currentMaxLength = getMaxLengthInArr(arrLine);
            MAX_LENGTH_CLIP = currentMaxLength;
        }

        for (int i = 0; i < arrLength; i++) {
            System.out.println("index = 【" + i + " 】   item=" + arrLine[i]);
            clipStringList.add(arrLine[i]);
        }
        //   String source = new String(clipStr.replaceAll("\r|\n", ""));

        //

         tryReadContentFromFile(curFile);   //  读取旧的文件内容

        tryWriteNewContentToFile(clipStringList,curFileStringList,curFile);
        //  对当前文件重新进行处理


//        String mVbsFilePath = dirPath + File.separator + "B3_voice.vbs";
//        String commadStr = "CreateObject(\"SAPI.SpVoice\").Speak \"" + source + "\"";
//
//        File file = new File(mVbsFilePath);
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
//                curBW.write(commadStr);
//                curBW.close();
//            } catch (Exception e) {
//
//            }
//        } else {
//            try {
//                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
//                curBW.write(commadStr);
//                curBW.close();
//            } catch (Exception e) {
//            }
//
//            RuntimeUtil.exec("Wscript.exe  /x " + file);
//
//        }
    }
}
