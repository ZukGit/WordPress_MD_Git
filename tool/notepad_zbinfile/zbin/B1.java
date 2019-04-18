
import java.io.*;

import cn.hutool.core.util.*;
import cn.hutool.extra.qrcode.*;




/**
 * 自动打开 二维码 Qrcode
 *
 */
public class B1 {

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
            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
                String qrCodeString = "";

                while (qrCodeString != null && qrCodeString.trim().isEmpty()) {
                    qrCodeString = curBR.readLine();
                }
                System.out.println("把扫描到的第一行非空字符串转为二维码  qrCodeString = " + qrCodeString);

                curBR.close();
                QrConfig config = new QrConfig();

                File targetFile = QrCodeUtil.generate
                        (qrCodeString, config, new File(dirPath + File.separator + "A1_out.jpg"));

                RuntimeUtil.exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + targetFile.getAbsolutePath());


            } catch (Exception e) {
                System.out.println("Exception !");
            }
        } else {
            System.out.println("Failed !");
        }
    }


}