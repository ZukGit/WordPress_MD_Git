

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class C8_AOSP_AddDexFlag {

    static String dex_preopt_mk_Path = System.getProperty("user.dir") + "/build/core/dex_preopt.mk";
    public static void main(String[] args) {
        String dex_fix_path = dex_preopt_mk_Path.replace("//","/");
        dex_fix_path = dex_fix_path.replace("///",File.separator);
        dex_fix_path = dex_fix_path.replace("//",File.separator);
        dex_fix_path = dex_fix_path.replace("/",File.separator);

        File dex_preopt_File = new File(dex_fix_path);
        if(!dex_preopt_File.exists()){ // 如果不是AOSP 的话 从 跟目录寻找
            dex_preopt_File = new File(System.getProperty("user.dir")+File.separator+"dex_preopt.mk");
        }
        String dexCode = readTemplateStringFromFile(dex_preopt_File).toString();
        dexCode = dexCode.replace("DEX_PREOPT_DEFAULT ?= true","DEX_PREOPT_DEFAULT ?= false");
        dexCode =dexCode.replace("GLOBAL_DEXPREOPT_FLAGS :=","GLOBAL_DEXPREOPT_FLAGS :=\nWITH_DEXPREOPT := false");
        writeContentToFile(dex_preopt_File,dexCode);
    }

    static String getOutFileHTMLName() { // 输出文件的名称
        return "C5_WCNSS_qcom_cfg_Item.html";
    }


    static StringBuilder readTemplateStringFromFile(File fileItem) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                sb.append(lineContent + "\n");
            }
            curBR.close();
        } catch (Exception e) {
        }
        return sb;
    }

    static void writeContentToFile(File file, String strParam) {

        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(strParam);
                curBW.flush();
                curBW.close();
                System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //  把 MD 格式的 翻译成  html 格式



}