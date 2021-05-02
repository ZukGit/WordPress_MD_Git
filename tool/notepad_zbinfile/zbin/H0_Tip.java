
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class H0_Tip {
    static String OneLine_Pre = "\n════════════════════════════════════════════════";
    static String OneLine_End = "════════════════════════════════════════════════\n";
    static String OneLine = "════════";

    static String User_Home = System.getProperties().getProperty("user.home");
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";


    static String J1_IDEA_PATH = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+ File.separator+"J1_IDEA";
    static File J1_IDEA_File = new File(J1_IDEA_PATH);



    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static String BAT_OR_SH_Point ;

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            BAT_OR_SH_Point = ".bat";
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            BAT_OR_SH_Point = ".sh";
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            BAT_OR_SH_Point = ".sh";
        }
    }


    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    public static void main(String[] args) {

        initSystemInfo();
        InstallWindowsTip();  //  双系统安装 说明
        Common_IDEA_tip();  // 破解IDEA 说明
        if(CUR_OS_TYPE == OS_TYPE.Windows){

            CmderTip();  // cmder 下的环境变量设置
            WindowsFilePathTip();  // 文件快捷方式提示
            NotePadCommand();
            Linux_BashTip();

        }else if (CUR_OS_TYPE == OS_TYPE.Linux){
            Linux_BashTip();
        }else if (CUR_OS_TYPE == OS_TYPE.MacOS){
            Mac_Tip();
            Mac_Item2_Tip();
            Linux_BashTip();
        }

        Chrome_Tip();
        Security_File_Tip();

    }

    // C:\Program Files\JetBrains\IntelliJ IDEA 2018.3.5\bin\idea64.exe.vmoptions
    static  File search_idea64_exe_vmoptions_1(){
        File idea64_exe_vmoptions = null;

        String  JetBrains_Program_Path = "C:\\Program Files\\JetBrains\\";
        File jetBrainDir = new File(JetBrains_Program_Path);
        if(!jetBrainDir.exists()){
            System.out.println(" 路径 "+ JetBrains_Program_Path + "为空(1) 找不到 idea64.exe.vmoptions ！");
            return null;
        }
        //            IDEA
        File[]  jetBrainFileList = jetBrainDir.listFiles();
        File IDEA_Dir = null;
        if(jetBrainFileList == null){
            System.out.println(" 路径 "+ JetBrains_Program_Path + "为空(2) 找不到 idea64.exe.vmoptions ！");
            return null;
        }

        for (int i = 0; i < jetBrainFileList.length; i++) {
            File dirFileItem = jetBrainFileList[i];
            String dirName = dirFileItem.getName().toLowerCase();
            if(dirName.contains("idea")){
                IDEA_Dir = dirFileItem;
            }
        }

        if(IDEA_Dir == null){
            System.out.println(" 路径 "+ JetBrains_Program_Path + "为空(3) 找不到 idea64.exe.vmoptions ！");
            return null;
        }

        File idea64_exe_vmoptions_1 = new File(IDEA_Dir.getAbsolutePath()+File.separator+"bin"+File.separator+"idea64.exe.vmoptions");
        if(!idea64_exe_vmoptions_1.exists()){
            System.out.println(" 路径 "+ JetBrains_Program_Path + "为空(4) 找不到 idea64.exe.vmoptions ！");
            return null;
        }

        return idea64_exe_vmoptions;
    }

    // C:\Users\zhuzj5\.IntelliJIdea2018.3\config\idea64.exe.vmoptions
    static  File search_idea64_exe_vmoptions_2(){
        File idea64_exe_vmoptions = null;
        File userHomeDir = new File(User_Home);
        File[] homeListFile = userHomeDir.listFiles();
        if(homeListFile == null || homeListFile.length == 0){
            System.out.println(" 路径 "+ userHomeDir + " 用户目录为空(1) 找不到 idea64.exe.vmoptions ！");
            return null;
        }

        File intelijDir = null;
        // IntelliJIdea
        for (int i = 0; i < homeListFile.length; i++) {
            File dirItem = homeListFile[i];
            if(dirItem.isFile()){
                continue;
            }
            String dirItemName = dirItem.getName().toLowerCase();
            if(dirItemName.contains("intellijidea")){
                intelijDir = dirItem;
            }
        }
        if(intelijDir == null){
            System.out.println(" 路径 "+ userHomeDir + "下 intelij 目录为空(2) 找不到 idea64.exe.vmoptions ！");
            return null;
        }
        File targetFile =   new File(intelijDir.getAbsolutePath()+File.separator+"config"+File.separator+"idea64.exe.vmoptions");

        if(!targetFile.exists()){
            System.out.println(" 路径 "+ targetFile.getAbsolutePath() + "下idea64.exe.vmoptions 为空(3) 找不到 idea64.exe.vmoptions ！");
            return null;
        }
        idea64_exe_vmoptions = targetFile;
        return idea64_exe_vmoptions;
    }

    static  ArrayList<File> search_Ideavmoptions_FileList(){
        ArrayList<File> idea_vmoption_FileList = new ArrayList<File>();

        if(CUR_OS_TYPE == OS_TYPE.Windows){  // // Windows 下寻找  idea.vmoptions


            File file_idea64_1 = search_idea64_exe_vmoptions_1();
            File file_idea64_2 = search_idea64_exe_vmoptions_2();
            if(file_idea64_1 != null) {
                idea_vmoption_FileList.add(file_idea64_1);
            }

            if(file_idea64_2 != null) {
                idea_vmoption_FileList.add(file_idea64_2);
            }










        }else if (CUR_OS_TYPE == OS_TYPE.MacOS){  // // MacOS 下寻找  idea.vmoptions
            String path1 = "/Applications/IntelliJ IDEA.app/Contents/bin/idea.vmoptions";
            File path1_File = new File(path1);
            if(path1_File.exists()){
                idea_vmoption_FileList.add(path1_File);
            }

            // User_Home =  /Users/zhuzhengjie/
            // Library = /Users/zhuzhengjie/Library
            File IntelijIDEA_PrefencesFile = null;
            File IntelijIDEA_PrefencesFile_ideaFile = null;

            // /Users/zhuzhengjie/Library/Preferences
            String path2_Perferences =User_Home + File.separator + "Library"+ File.separator +"Preferences";
            File PerferencesFile = new File(path2_Perferences);
            if(PerferencesFile.exists()){
                File[] PerferencesSubFileList = PerferencesFile.listFiles();


                // IntelliJIdea
                for (int i = 0; i < PerferencesSubFileList.length; i++) {
                    File PerferencesFileItem = PerferencesSubFileList[i];
                    if(PerferencesFileItem.isFile()){
                        continue;
                    }

                    String dirName = PerferencesFileItem.getName().toLowerCase();
//                  System.out.println("dirName = "+PerferencesFileItem.getAbsolutePath() +"  ");

                    // IntelliJIdea
                    if(dirName.contains("intellijidea")){
                        IntelijIDEA_PrefencesFile = PerferencesFileItem;
                        break;
                    }
                }


            }else{

                System.out.println("PerferencesFile = "+PerferencesFile.getAbsolutePath() +"  不存在！");
            }

            if(IntelijIDEA_PrefencesFile != null){
                IntelijIDEA_PrefencesFile_ideaFile =  new File(IntelijIDEA_PrefencesFile.getAbsolutePath()+File.separator+"idea.vmoptions");

//              System.out.println("IntelijIDEA_PrefencesFile_ideaFile = "+IntelijIDEA_PrefencesFile_ideaFile.getAbsolutePath() +"  存在="+ IntelijIDEA_PrefencesFile_ideaFile.exists());

                if(IntelijIDEA_PrefencesFile_ideaFile.exists()){
                    idea_vmoption_FileList.add(IntelijIDEA_PrefencesFile_ideaFile);
                }
            }else {
                System.out.println("IntelijIDEA_PrefencesFile 为空 ！！");

            }



        } else {   // Linux 下寻找  idea.vmoptions
            System.out.println("Linux 的 IDEA 破解方法没有实现  return!");

        }




        return idea_vmoption_FileList;


    }

    static  void Common_IDEA_tip(){

        System.out.println(OneLine_Pre + " IDEA 破解方法 Begin "+ OneLine_End);

        if(!J1_IDEA_File.exists() || J1_IDEA_File.listFiles() == null){
            System.out.println("当前保存JetBrain.jar 破解IDEA 的保存文件路径不存在! 请检查 PATH = "+J1_IDEA_File.getAbsolutePath());
            return;
        }


        File[] filelist =  J1_IDEA_File.listFiles();
        File descTxtFile = null;
        ArrayList<File> allJarFileList = new     ArrayList<File>();

        for (int i = 0; i < filelist.length; i++) {
            File item = filelist[i];
            String fileName = item.getName().toLowerCase();
            if(fileName.endsWith(".jar")){
                allJarFileList.add(item);
            }else if(fileName.endsWith(".txt")){
                descTxtFile = item;
            }
        }



//        echo "-javaagent:/Applications/IntelliJ IDEA.app/Contents/bin/Jetbrains-agent_1.jar" >> /Applications/IntelliJ IDEA.app/Contents/bin/idea.vmoptions
//

/*

"-javaagent:/Applications/IntelliJ\ IDEA.app/Contents/bin/Jetbrains-agent_2.jar" >>

/Applications/IntelliJ\ IDEA.app/Contents/bin/idea.vmoptions


/Applications/IntelliJ IDEA.app/Contents/bin/idea.vmoptions

/Applications/IntelliJ\ IDEA.app/Contents/bin/idea.vmoptions

                -javaagent:/Applications/IntelliJ IDEA.app/Contents/bin/Jetbrains-agent_1.jar
                -javaagent:/Applications/IntelliJ IDEA.app/Contents/bin/Jetbrains-agent_2.jar
                -javaagent:/Applications/IntelliJ IDEA.app/Contents/bin/JetbrainsCrack-2.8-release-enc.jar
                -javaagent:/Applications/IntelliJ IDEA.app/Contents/bin/JetbrainsCrack-2.10-release-enc.jar
                -javaagent:/Applications/IntelliJ IDEA.app/Contents/bin/JetbrainsCrack-3.1-release-enc.jar
                -javaagent:/Applications/IntelliJ IDEA.app/Contents/bin/JetbrainsCrack3.4.jar
                -javaagent:/Applications/IntelliJ IDEA.app/Contents/bin/JetbrainsIdesCrack-4.2.jar
*/

        ArrayList<String> javaAgentStrList = new  ArrayList<String> ();
        for (int i = 0; i < allJarFileList.size(); i++) {
            File jarFile = allJarFileList.get(i);
            String javaagentStr = "#-javaagent:"+jarFile.getAbsolutePath()+"\\n";

//            if(i == allJarFileList.size() -1 ){
//                javaagentStr = "\\n-javaagent:"+jarFile.getAbsolutePath()+"\\n";
//                javaAgentStrList.add(0,javaagentStr);
//            }

            javaAgentStrList.add(javaagentStr);
        }
        javaAgentStrList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        ArrayList<File> idea_vmoption_FileList = search_Ideavmoptions_FileList();
        if(idea_vmoption_FileList == null || idea_vmoption_FileList.size() ==0){
            System.out.println(" 当前系统下没有找到 Mac(idea.vmoptions)  Windows【idea.exe.vmoptions  idea64.exe.vmoptions】 返回  ");
            return;
        }



//        echo -n "xxxxsadaa" >> /Applications/IntelliJ\ IDEA.app/Contents/bin/idea.vmoptions
        String openTxtCommand = "";

        for (int i = 0; i < idea_vmoption_FileList.size() ; i++) {
            File ideaFile = idea_vmoption_FileList.get(i);
            String ideaFileAbsPath = ideaFile.getAbsolutePath();
            ideaFileAbsPath = ideaFileAbsPath.replace(" ","\\ ");
//            ArrayList<String> contentList = ReadFileContentAsList(ideaFile);

            StringBuilder sb_AppendComamnd = new StringBuilder();


            if(CUR_OS_TYPE == OS_TYPE.MacOS){
                for (int j = 0; j < javaAgentStrList.size(); j++) {
                    String  jarPath = javaAgentStrList.get(j);
                    if(j == 0 ){
                        jarPath = "\\n"+jarPath.replace("#","");
                    }
                    String commandItem = " echo -n \""+jarPath+"\" >>  "+ ideaFileAbsPath ;
                    sb_AppendComamnd.append(commandItem+"  &&  ");
                }


            }else if(CUR_OS_TYPE == OS_TYPE.Windows){
                for (int j = 0; j < javaAgentStrList.size(); j++) {
                    String  jarPath = javaAgentStrList.get(j);
                    if(j == 0 ){
                        jarPath = ""+jarPath.replace("#","");
                    }
                    String commandItem = " echo "+jarPath+" >> "+ ideaFileAbsPath ;
                    commandItem =  commandItem.replace("\\ ","");
                    commandItem =  commandItem.replace("\\n","");

                    sb_AppendComamnd.append(commandItem+"  &&  ");
                }


            }

            String command_fixed = sb_AppendComamnd.toString().trim();
            if(command_fixed.endsWith("&&")){

                command_fixed = command_fixed.substring(0,command_fixed.length()-2);
            }
            String openFileCommand = "";

            if(CUR_OS_TYPE == OS_TYPE.Windows){
                openFileCommand = " cmd.exe /c start   Notepad++.exe " + ideaFileAbsPath;
                if(descTxtFile != null)
                    openTxtCommand  = " cmd.exe /c start   Notepad++.exe " + descTxtFile.getAbsolutePath();
            }else if(CUR_OS_TYPE ==OS_TYPE.MacOS){
                openFileCommand = " /Applications/UltraEdit  " + ideaFileAbsPath;

                if(descTxtFile != null)
                    openTxtCommand  = "/Applications/UltraEdit  " + descTxtFile.getAbsolutePath();
            }else{
                openFileCommand = " gedit " + ideaFileAbsPath;
                if(descTxtFile != null)
                    openTxtCommand  = "gedit " + descTxtFile.getAbsolutePath();

            }

//            /Applications/UltraEdit
            System.out.println(ideaFileAbsPath+" 执行加入 javaagent 操作命令如下_______________________");
            System.out.println("help 》 Edit Custom  VM Options 》 idea64.exe.vmoptions ");
            System.out.println(command_fixed+" && "+ openFileCommand );
            System.out.println();
            System.out.println();

        }


        if(descTxtFile != null){
            System.out.println("________________ 激活码执行如下步骤查询 进行 ________________ ");
            System.out.println("help 》 register 》 ActivationCode");
            System.out.println("cat "+ descTxtFile.getAbsolutePath()  );
            System.out.println(openTxtCommand );

        }





        System.out.println(OneLine_Pre + " IDEA 破解方法 End "+ OneLine_End);



    }

    static void Mac_Item2_Tip(){

        System.out.println();
        System.out.println("══════════ MacOS 下 iterm2-SHELL头缀只显示PC名称不显示路径 && 自定义别名问题 ══════════");
        System.out.println();
        System.out.println("iterm2预置环境变量");
        System.out.println("PS1='%d%'");
        System.out.println("https://www.jianshu.com/p/bf488bf22cba");
        System.out.println();
        System.out.println("ITERM2 恢复默认设置");
        System.out.println("defaults delete com.googlecode.iterm2");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Iterm2预置环境变量 添加自定义别名  添加自定义目录/Desktop/zbin/mac_zbin 到系统环境变量PATH");
        System.out.println("Iterm2-Preference--Profile--Send Text At Start");
        System.out.println("=====");
        System.out.println("export CLICOLOR=1");
        System.out.println("export LSCOLORS=gxfxcxdxbxegedabagacad");
        System.out.println("export PS1='%d$'");
        System.out.println("alias cls='clear'");
        System.out.println("alias cdd='cd $HOME/Desktop/'");
        System.out.println("export PATH=\"$HOME/Desktop/zbin/mac_zbin:$PATH\"");
        System.out.println("clear");
        System.out.println("════════════════════════════════════════ MacOS item2 End ════════════════════════════════════════");
        System.out.println();



    }
    static void Mac_Tip(){
        PrintHead_End("  Mac下 UltraEdit设置  Begin  ");

        System.out.println("1. UltraEdit 使用  【工具】【工具配置】【命令行】执行 TextOperation");
        System.out.println("$HOME/Desktop/zbin/I9.sh  $HOME/Desktop/zbin %f");
        System.out.println();
        System.out.println("2. windows 到 Mac 存在  sh文件编码的问题 使用 dos2unix 来 转换");
        System.out.println("brew install dos2unix");
        System.out.println("dos2unix *.sh");
        System.out.println();
        System.out.println("3. 在Mac 下 使用 什么东西来打开 Temp 文件");
        System.out.println("/Applications/UltraEdit I9_Temp_Text.txt");
        System.out.println();



        System.out.println("4. 在Mac 下壁纸的路径");
        System.out.println();
        System.out.println("open  /System/Library/Screen\" \"Savers/Default\" \"Collections && open  /Library/Desktop\" \"Pictures  ");

//        /System/Library/Screen\ Savers/Default\ Collections    // 终端
//      /System/Library/Screen Savers/Default Collections     // UNIX

        // cd   /Library/Desktop" "Pictures
//      cd  /System/Library/Screen" "Savers/Default" "Collections    // OK  把 空白转意
        //       MacOS:System:Library:Screen Savers:Default Collections    //HFS
        //  file:///System/Library/Screen%20Savers/Default%20Collections/     //URL


        //   /Library/Desktop\ Pictures         // 终端
//        /Library/Desktop Pictures      // UNIX


        PrintHead_End("   Mac下 UltraEdit设置  End  ");

    }



    static void InstallWindowsTip(){

        System.out.println(OneLine_Pre + " 安装三系统 Win10+Win7+Deepin 三系统 说明 Begin "+ OneLine_End);
        System.out.println("准备材料:  1台正常运行电脑  2.一个空U盘(4G起步)  3.【win10.iso  win7.iso  deepin.iso macos.iso 】等系统镜像    ");
        System.out.println("1.下载制作U盘PE启动软件  https://pan.baidu.com/s/1uyYxw_WUONyAQ_H4lzY_dw  提取码: rcrb ");
        System.out.println("1.1 大白菜安装方法 https://www.dabaicai.com/booklet.html  Deepin系统  https://www.deepin.org/zh/installation/ ");
        System.out.println("1.2 执行操作:  正常运行电脑插入U盘  模式: USB-HDD    格式: NTFS   一键制作USB  【把要加载的 系统.iso镜像 复制到该U盘】 ");
        System.out.println("1.3 把U盘 插入故障电脑   选择以USB盘为启动盘    HP-2570p 开机按住F2 以及 F8 选择启动盘");
        System.out.println("1.4 进入到大白菜选项页面  选择   启动Win10 x64 PE PE(Preinstallation Environment)是带有有限服务的最小Win32子系统 系统 ");
        System.out.println("1.5 进入到PE中 选中 大白菜一键装机 【可在这时 把系统安装目录硬盘格式化 并设置为活动分区 】");
        System.out.println("1.5.1 我的电脑-> 右击 -> 管理 -> 磁盘管理 -> 选中安装硬盘-> 将分区标记为活动分区");
        System.out.println("1.6 在选择镜像中选中 U盘中的系统镜像 ");
        System.out.println("1.7 在弹出框中选中 系统的版本(家庭 专业 工作站)");
        System.out.println("1.7.1  如果弹框 没没有找到硬盘驱动程序  那么转到 我的电脑-> 右击 -> 管理 -> 磁盘管理 -> 选中安装硬盘-> 将分区标记为活动分区");
        System.out.println("1.8 选中当前的安装系统的盘符  点击执行   还原分区:C  引导分区:C  点击是 开始安装系统");

        System.out.println("2.安装win10成功 拔掉U盘 重启进入新安装的 win10 系统的电脑 开始安装 win7子系统 ");
        System.out.println("2.1 参考页面: https://jingyan.baidu.com/article/9f63fb91733a19c8410f0e59.html");
        System.out.println("2.2 在 我的电脑-> 右击 -> 管理 -> 磁盘管理 新建一个硬盘区域 格式化 设置为活动分区 用于安装win7系统");
        System.out.println("2.3 解压缩 Win7.iso 系统镜像到 新建硬盘  并解压缩");
        System.out.println("2.4 zzfile_3.bat  C:\\Windows\\Boot\\EFI\\bootmgr.efi  ,  把当前的 bootmgr.efi 复制到桌面 并改名 bootx64.efi");
        System.out.println("2.5 把桌面的 bootx64.efi 复制到  Win7_ISO解压目录\\efi\\microsoft\\boot 中");
        System.out.println("2.6 点击 Win7_ISO解压目录\\ 中的 setup.exe 打开安装界面  后续选中当前新建的硬盘进行 win7系统的安装 ");
        System.out.println("2.7 安装结束后 重启会出现两个 系统启动项一个 Win10  一个 Win7 至此 双系统安装完成! ");
        System.out.println("2.8 (提示:) 如果界面只有 win7 启动项 没有win10 启动项 那么 使用 easybcd.exe 软件新增一个 Win10 系统盘符的启动项(置为默认选中)");
        System.out.println("2.9 (提示:) 安装完 win10 win7 子系统时不要再 启动 PE的修复引导程序进行 引导修复(因为可能造成win7的盘符错乱 无法显示桌面) ");
        System.out.println("2.10(提示:)  如果当前 Win7 Win10 无法正常显示桌面 只显示鼠标 那么 ctrl+alt+delete 新建任务  CMD 运行 explorer 或者 explorer.exe");

        System.out.println("3.安装Deepin 制作Deepin 的 启动 U盘  【Deepin.iso】【U盘】【deepin-boot-maker.exe 深度启动盘制作工具】 ");
        System.out.println("3.1 创建1个20GB的 ntfs的 deepin的 / 根目录盘  创建1个10GB的 deepin 的 /home 用户盘 ");
        System.out.println("3.2 插入 Deepin的 U盘重启电脑   选择以USB盘为启动盘 选中安装Deepin系统");

        System.out.println("3.3 选择安装位置 -> 手动安装 -> 选择大于16GB的 ntfs空盘 以新文件系统 ext4 作为 根目录 / 的deepin系统安装目录");
        System.out.println("3.4 选择安装位置 -> 手动安装 -> 选择大于10GB的 ntfs空盘 以新文件系统 ext4 作为 根目录 /home 的deepin系统安装目录");
        System.out.println("3.5 安装Deepin系统 开始安装");
        System.out.println("3.6 在新打开的 Deepin的 设置中 设置 默认引导分区为  win 10 !");
        System.out.println("Windows 安装环境变量配置好后  Java环境还未配置  那么使用 zbin/win_zbin/zzinit_8.bat 或者本地的 zzinit_8.bat   配置好Java环境后使用 ztip 查看历史正文!");
        System.out.println(OneLine_Pre + " 安装三系统 Win10+Win7+Deepin 三系统 说明 End "+ OneLine_End);
    }




    static void  PrintHead_End( String headStr ){
        System.out.println(OneLine_Pre + ""+headStr+""+ OneLine_End);

    }




    static void NotePadCommand(){
        PrintHead_End("  NotePad++ 设置  Begin  ");
        System.out.println(" 用户设置 notepad运行【索引1】   ztextrule_operation_I9.bat 的 快捷键为 F8:");
        System.out.println("Name: text_rule   Key:  F8");
        System.out.println("cmd /K cd /d %userprofile%\\Desktop\\zbin & %userprofile%\\Desktop\\zbin\\I9.bat %userprofile%\\Desktop\\zbin  $(FULL_CURRENT_PATH)");
        System.out.println("用户可通过 ztextrule_operation_I9.bat  命令来选择对应的 F8 快剪键 执行的 text_rule 逻辑!");

        System.out.println();
        System.out.println(" 用户设置 notepad运行【索引2】   A8.bat 进行Log分析 打印 的 快捷键为 【 Log_Check : F9 】");
        System.out.println("Name: Log_Check   Key:  F9");
        System.out.println("cmd /K cd /d %userprofile%\\Desktop\\zbin & %userprofile%\\Desktop\\zbin\\A8.bat %userprofile%\\Desktop\\zbin  $(FULL_CURRENT_PATH)  ");
        System.out.println("可修改A8.bat 逻辑为: A8_WIFI_Log_Search.java  A8_GPS_Log_Search.java  F8_Dump_Analysis.java 先后运行 并输出到当前打开文件");

        PrintHead_End("  NotePad++ 设置  End  ");
    }


    //


    static void Chrome_Tip(){

        PrintHead_End(" Chrome_Tip 插件 Begin  ");
        System.out.println("Chrome插件安装位置( Windows 路径空格):\n  "+User_Home+ File.separator+"AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions");
        System.out.println("Chrome插件安装位置( Linux ):\n  zzfile_3.sh "+"  \""+User_Home+ File.separator+".config/google-chrome1/Default/Extensions/\"");
        System.out.println("Chrome插件安装位置( MacOS 路径空格):\n  open  "+User_Home+ File.separator+"Library/Application\" \"Support/Google/Chrome/Default/Extensions/");
        System.out.println();
        System.out.println("Chrome插件备份地址:\n  zzfile_3"+BAT_OR_SH_Point+"  "+zbinPath+File.separator+"J1_Plugin      【插件】【脚本】【书签】 先安装 AA_get-crx 方便插件安装");
        PrintHead_End("   Chrome_Tip 插件 Begin   End  ");
    }



    static void Linux_BashTip(){

        PrintHead_End("  Linux 下 Shell 设置 Begin  ");
        System.out.println(User_Home+ File.separator+"Desktop"+File.separator+"zbin" +"文件夹放置完成后执行 以下代码 添加环境变量:");
        System.out.println("echo \"source ~/Desktop/zbin/lin_zbin/environment_lin_zbin.sh\" >> ~/.bashrc  && "+" echo \"source ~/Desktop/zbin/lin_zbin/environment_lin_zbin.sh\" >>  ~/.zshrc "+ " &&  chmod 777 -R ~/Desktop/zbin/ ");


        PrintHead_End("  Linux 下 Shell 设置  End  ");
    }

    static void  WindowsFilePathTip(){  // 文件快捷方式提示

        PrintHead_End("  Winddows下文件快捷方式 Begin   ");


        System.out.println("Winddows下 Ubuntu子系统 路径:");
        System.out.println(" zzfile_3.bat "+ User_Home+ File.separator+"AppData\\Local\\Packages\\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\\LocalState\\rootfs        // Ubuntu子系统根目录 ");


        System.out.println("桌面壁纸相关");
        System.out.println(" zzfile_3.bat "+ User_Home+ File.separator+"AppData\\Roaming\\Microsoft\\Windows\\Themes        // 当前壁纸位置 ");
        System.out.println(" zzfile_3.bat "+"C:\\Windows\\Web\\Screen                                                      // 壁纸仓库   ");
        System.out.println(" zzfile_3.bat "+User_Home+File.separator+"AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets   // 锁屏壁纸1");
        System.out.println(" zzfile_3.bat "+User_Home+File.separator+"AppData\\Local\\Microsoft\\Windows\\Themes          // 锁屏壁纸2");
        System.out.println("批量打开电脑壁纸相关的路径");
        System.out.println(" zzfile_3.bat "+ User_Home+ File.separator+"AppData\\Roaming\\Microsoft\\Windows\\Themes" +"  C:\\Windows\\Web\\Screen  " +User_Home+File.separator+"AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets " +User_Home+File.separator+"AppData\\Local\\Microsoft\\Windows\\Themes"  );

        System.out.println();
        System.out.println("网络配置Host");
        System.out.println(" zzfile_3.bat C:\\WINDOWS\\system32\\drivers\\etc\\hosts    // Hosts文件的做用是把网址域名与对应的IP地址建立一个关联 数据库 ");
        PrintHead_End("  Winddows下文件快捷方式  End  ");
    }
    static void CmderTip(){
        PrintHead_End(" Winddows下Cmder设置 Begin ");


        System.out.println("添加CMDER.exe的Dir路径到 系统环境变量Path:   电脑(右击)》 属性 》高级系统设置(左面板) 》 环境变量 》 用户变量 》 Path( 添加cmder的dir )  ");
        System.out.println("Cmder.exe /REGISTER ALL       // 鼠标目录右击出现cmder选项   ");
        System.out.println("Cmder.exe /UNREGISTER ALL       // 鼠标目录右击删除cmder选项   ");
        System.out.println("Cmder 下载地址:"+"  https://pan.baidu.com/disk/home#/all?vmode=list&path=%2F%E7%A7%BB%E5%8A%A8%E7%A1%AC%E7%9B%98%2Fsoftware%2Fwin");
        System.out.println("设置环境变量  Setting -> StartUp -> Environment  输入如下的配置信息: ");
        System.out.println();
        System.out.println("set PATH=%ConEmuBaseDir%\\Scripts;%PATH%\n" +
                "set PATH=%USERPROFILE%\\Desktop\\zbin\\win_zbin;%USERPROFILE%\\Desktop\\zbin\\python;%PATH%\n" +
                "set PATH=%USERPROFILE%\\Desktop\\zbin\\lin_zbin;%USERPROFILE%\\Desktop\\zbin\\mac_zbin;%PATH%\n" +
                "\n" +
                "alias gits=git status\n" +
                "alias cdd=cd /D %USERPROFILE%\\Desktop\n" +
                "alias cdz=cd /D %USERPROFILE%\\Desktop\\zbin\n" +
                "\n" +
                "set PATH=D:\\software\\ffmpeg\\bin;%PATH%\n" +
                "set tessdata=C:\\Program Files\\Tesseract-OCR\\tessdata\n" +
                "set PATH=C:\\Program Files\\Tesseract-OCR;%PATH%\n" +
                "set PATH=%USERPROFILE%\\Desktop\\zbin\\win_soft\\Redis;%PATH%");
        System.out.println("set LANG=zh_CN.UTF-8");
        System.out.println();
        PrintHead_End(" Winddows下Cmder设置 End   ");
    }







    static void Security_File_Tip(){

        System.out.println("________________________ ZVI_Position 【解压】【生成Html】 ________________________");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_good_batch  ******原封不动解码");
        System.out.println("cd ./good_batch                    ******进入解码文件夹");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  _mp4       ******把无类型转为mp4类型");
        System.out.println("cd ./ZVI_Position                  ******进入ZVI_Position文件夹");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_24  mp4      ******在ZVI_Position执行在子目录与孙目录构建中间文件夹 mp4");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_26 mp4       ******依据文件夹把每个文件夹下的mp4 依次从1..n开始重命名");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_13_mp4       ******在当前ZVI_Position生成时间30秒间隔轮换 html文件");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_12_mp4       ******在当前ZVI_Position搜索mp4文件 生成对应html播放文件");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_good_batch  && cd ./good_batch && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  _mp4  && cd ./ZVI_Position  && zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_24  mp4  && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_26 mp4 && zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_13_mp4 && zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_12_mp4");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("________________________ ZVI_Individual 【解压】【生成Html】 ________________________");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_good_batch  ******原封不动解码");
        System.out.println("cd ./good_batch                    ******进入解码文件夹");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  _mp4       ******把无类型转为mp4类型");
        System.out.println("cd ./ZVI_Individual                  ******进入ZVI_Individual文件夹");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_26 mp4       ******依据文件夹把每个文件夹下的mp4 依次从1..n开始重命名");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_13_mp4       ******在当前ZVI_Individual生成时间30秒间隔轮换 html文件");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_12_mp4       ******在当前ZVI_Individual搜索mp4文件 生成对应html播放文件");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_good_batch  && cd ./good_batch && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  _mp4  && cd ./ZVI_Individual   && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_26 mp4 && zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_13_mp4 && zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_12_mp4");
        System.out.println();

//        zrule_apply_G2.sh  #_9  jpg_
        //        zrule_apply_G2.sh  #_9  _jpg
        //        zrule_apply_G2.sh  #_9  _gif

//        zrule_apply_G2.sh  #_9  webp_
//        zrule_apply_G2.sh  #_9  gif_
//        zrule_apply_G2.sh  #_9 png_

        System.out.println();
        System.out.println("________________________ MP4 【加密】【不压缩】 ________________________");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_18  .mp4  && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_bad_batch &&  cd ./bad_batch  &&  zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  mp4_");
        System.out.println();
        System.out.println("________________________ MP4 【解密】【不压缩】 ________________________");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_good_batch  && cd ./good_batch && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  _mp4");
        System.out.println();


        System.out.println();
        System.out.println("________________________ 【JPG_Top】jpg png【Jpg Top Land Port 自动分类并加密】加密 改名 无类型批量操作 ________________________");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_18  .jpg   && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_18  .png  && zrule_apply_G2"+BAT_OR_SH_Point+" " +" #_20_notime && cd ./Land_Port_Img/Port/ && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_bad_batch &&  cd ./bad_batch && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  jpg_ && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9 png_ && cd ../../../ && cd ./Land_Port_Img/Land/ && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_bad_batch &&  cd ./bad_batch && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  jpg_ && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9 png_");
        System.out.println();
        System.out.println("________________________ jpg png webp 加密 解密 改名 无类型批量操作 ________________________");
        System.out.println("【对 jpg png webp 改名并加密 生产加密文件夹 】");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_18  .jpg   && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_18  .png  && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_18  .webp  && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_bad_batch &&  cd ./bad_batch  &&  zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  jpg_ && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  webp_ &&  zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9 png_");
        System.out.println("【 对 jpg png webp  对加密文件解密】");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_7_good_batch && cd ./good_batch && zrule_apply_G2"+BAT_OR_SH_Point+" " +"#_9  _jpg");
        System.out.println();


        System.out.println();
        System.out.println("________________________ gif 动态文件 加密 解密 无类型批量操作 批量操作 ________________________ ");
        System.out.println("【对gif 改名并加密 生产加密文件夹】");
        System.out.println(" zrule_apply_G2"+BAT_OR_SH_Point+" #_18  .gif    && zrule_apply_G2"+BAT_OR_SH_Point+" #_18  .webp  &&   zrule_apply_G2"+BAT_OR_SH_Point+" "+"zrule_apply_G2"+BAT_OR_SH_Point+" #_9  gif_ && zrule_apply_G2"+BAT_OR_SH_Point+" #_9  webp_"
                + " #_7_bad_batch       ");
        System.out.println("【 gif 对加密文件解密】");
        System.out.println("zrule_apply_G2"+BAT_OR_SH_Point+" #_9  _gif"+ "  &&  zrule_apply_G2"+BAT_OR_SH_Point+" #_7_good_batch     ");





        PrintHead_End(" 文件加密解密操作 End    ");
    }




    public static ArrayList<String> ReadFileContentAsList( File mFilePath) {

        if (mFilePath != null  && mFilePath.exists()) {
            //  System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 "+ mFilePath.getAbsolutePath() );

            return null;
        }
        ArrayList<String> contentList= new ArrayList<String>();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null ) {
                    continue;
                }

                contentList.add(oldOneLine);
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;

            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentList;

    }



    static void writeContentToFile(File file, ArrayList<String> strList) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            sb.append(strList.get(i) + "\n");
        }
        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(sb.toString());
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












}