

import java.io.*;



public class C8_AOSP_AddDexFlag {

    static String dex_preopt_mk_Path = System.getProperty("user.dir") + "/build/core/dex_preopt.mk";
    static String dex_preopt_config_mk_Path = System.getProperty("user.dir") + "/build/core/dex_preopt_config.mk";

    static String dex_board_config_mk_Path = System.getProperty("user.dir") + "/build/core/board_config.mk";

    public static void main(String[] args) {
        String dex_fix_path = dex_preopt_mk_Path.replace("//","/");
        dex_fix_path = dex_fix_path.replace("///",File.separator);
        dex_fix_path = dex_fix_path.replace("//",File.separator);
        dex_fix_path = dex_fix_path.replace("/",File.separator);

        String dex_fix_config_path = dex_preopt_config_mk_Path.replace("//","/");
        dex_fix_config_path = dex_fix_config_path.replace("///",File.separator);
        dex_fix_config_path = dex_fix_config_path.replace("//",File.separator);
        dex_fix_config_path = dex_fix_config_path.replace("/",File.separator);

        String dex_fix_board_config_path = dex_board_config_mk_Path.replace("//","/");
        dex_fix_board_config_path = dex_fix_board_config_path.replace("///",File.separator);
        dex_fix_board_config_path = dex_fix_board_config_path.replace("//",File.separator);
        dex_fix_board_config_path = dex_fix_board_config_path.replace("/",File.separator);


        String curDirPath = System.getProperty("user.dir");

        File dex_preopt_File = new File(dex_fix_path);
        if(!dex_preopt_File.exists()){ // 如果不是AOSP 的话 从 跟目录寻找
//            dex_preopt_File = new File(System.getProperty("user.dir")+File.separator+"dex_preopt.mk");
            System.out.println("当前不是AOSP目录 无法查询到依据目录规则生成的文件  "+curDirPath+"/build/core/dex_preopt.mk");
            System.out.println("程序非正常结束! 请价差当前调用程序的路径是否是AOSP路径!");
            return;
        }
        String dexCode = readTemplateStringFromFile(dex_preopt_File).toString();
        dexCode = dexCode.replace("DEX_PREOPT_DEFAULT ?= true","DEX_PREOPT_DEFAULT ?= false");
        dexCode =dexCode.replace("WITH_DEXPREOPT := false","");
        dexCode =dexCode.replace("GLOBAL_DEXPREOPT_FLAGS :=","GLOBAL_DEXPREOPT_FLAGS :=\nWITH_DEXPREOPT := false");
        writeContentToFile(dex_preopt_File,dexCode);

        File dex_preopt_config_File = new File(dex_fix_config_path);
        if(dex_preopt_config_File.exists()) {
            String dexConfigCode = readTemplateStringFromFile(dex_preopt_config_File).toString();
            dexConfigCode = dexConfigCode.replace("DEX_PREOPT_DEFAULT ?= true","DEX_PREOPT_DEFAULT ?= false\nWITH_DEXPREOPT ?= false");
            writeContentToFile(dex_preopt_config_File,dexConfigCode);
            System.out.println("当前AOSP路径:"+ curDirPath +" 以完成在 /build/core/dex_preopt_config.mk 的flag添加！版本可直接 替换frameworks.jar" );

        }else{
            System.out.println("当前不是AOSP目录 无法查询到依据目录规则生成的文件  "+curDirPath+"/build/core/dex_preopt_config.mk");
            System.out.println("程序将只修改 /build/core/dex_preopt.mk 文件 ");
        }

        File dex_board_config_File = new File(dex_fix_board_config_path);
        if(dex_board_config_File.exists()) {
            String dexConfigCode = readTemplateStringFromFile(dex_board_config_File).toString();
            dexConfigCode = dexConfigCode.replace("WITH_DEXPREOPT := true","WITH_DEXPREOPT := false");
            writeContentToFile(dex_board_config_File,dexConfigCode);
            System.out.println("当前AOSP路径:"+ curDirPath +" 以完成在 /build/core/board_config.mk 的flag添加！版本可直接 替换frameworks.jar" );

        }



        System.out.println("恭喜 C8_AOSP_AddDexFlag 成功执行!  ");
        System.out.println("当前AOSP路径:"+ curDirPath +" 以完成在 /build/core/dex_preopt.mk 的flag添加！版本可直接 替换frameworks.jar" );
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
                if (lineContent == null ) {
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