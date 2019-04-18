import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.RuntimeUtil;

import java.io.*;
import java.util.ArrayList;

public class B3 {

    static String dirPath = "";

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

        dirPath = mFilePath.trim().substring(0, mFilePath.lastIndexOf(File.separator));
        System.out.println("dirPath  =  " + dirPath);

        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }


        String clipStr = ClipboardUtil.getStr().trim();
        System.out.println("clipStr = " + clipStr);
        String source = new String(clipStr.replaceAll("\r|\n", ""));


        //  创建vbs  并执行它  CreateObject("SAPI.SpVoice").Speak "你好，2019"





        String mVbsFilePath = dirPath + File.separator + "B3_voice.vbs";
        String commadStr = "CreateObject(\"SAPI.SpVoice\").Speak \"" + source + "\"";

        File file = new File(mVbsFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(commadStr);
                curBW.close();
            } catch (Exception e) {

            }
        } else {
            try {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(commadStr);
                curBW.close();
            } catch (Exception e) {
            }

            RuntimeUtil.exec("Wscript.exe  /x " + file);

        }
    }
}
