import java.io.File;

public class H0_Tip {
    static String OneLine_Pre = "\n════════════════════════════════════════════════";
    static String OneLine_End = "════════════════════════════════════════════════\n";
    static String OneLine = "════════";

    static String User_Home = System.getProperties().getProperty("user.home");
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

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

        if(CUR_OS_TYPE == OS_TYPE.Windows){
            InstallWindowsTip();  //  双系统安装 说明

            CmderTip();  // cmder 下的环境变量设置
            WindowsFilePathTip();  // 文件快捷方式提示
            NotePadCommand();
            Linux_BashTip();
            Chrome_Tip();
            Security_File_Tip();
        }else if (CUR_OS_TYPE == OS_TYPE.Linux){
            Linux_BashTip();
        }


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
        System.out.println(" cmd /K cd /d %userprofile%\\Desktop\\zbin & %userprofile%\\Desktop\\zbin\\I9.bat %userprofile%\\Desktop\\zbin  $(FULL_CURRENT_PATH)");
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
        System.out.println("Chrome插件安装位置( Linux ):\n  zzfile_3.sh  \""+User_Home+ File.separator+".config/google-chrome1/Default/Extensions/\"");
        System.out.println("Chrome插件安装位置( MacOS 路径空格):\n  zzfile_3.sh  "+User_Home+ File.separator+"Library/Application Support/Google/Chrome/Default/Extensions/");
        System.out.println();
        System.out.println("Chrome插件备份地址:\n  zzfile_3.bat  "+zbinPath+File.separator+"J1_Plugin      【插件】【脚本】【书签】 先安装 AA_get-crx 方便插件安装");
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
        System.out.println();
        PrintHead_End(" Winddows下Cmder设置 End   ");
    }







    static void Security_File_Tip(){

        PrintHead_End(" 文件加密解密操作 Begin    ");
        System.out.println("________________________ MP4 加密批量操作 ________________________ ");
        System.out.println("1. 先对当前文件内容进行改名    zrule_apply_G2.bat *_18 ");
        System.out.println("2. 对当前文件夹下的文件进行加密 生成 bad_batch 加密文件夹  zrule_apply_G2.bat #_7_bad_batch ");
        System.out.println("3. 进入 bad_batch 加密文件夹   cd ./bad_batch ");
        System.out.println("4.把当前所有文件压缩为 .7z 格式 密码默认为 752025    zrule_apply_G2.bat *_19 ");
        System.out.println("5.对当前.7z 文件进行 后缀名称处理  zrule_apply_G2.bat #_9  7z_7疫z ");
        System.out.println("6.打开当前文件夹  zzfile_3.bat ");
        System.out.println(" zrule_apply_G2.bat *_18  .mp4   &&   zrule_apply_G2.bat #_7_bad_batch && cd ./bad_batch  && zrule_apply_G2.bat *_19 &&  zrule_apply_G2.bat #_9  7z_7疫z   &&   zzfile_3.bat");

        System.out.println("");
        System.out.println("________________________ MP4 解密批量操作 ________________________ ");
        System.out.println("1.对当前文件中 后缀是包含中文的文件进行修复    zrule_apply_G2.bat  #_8");
        System.out.println("2.对当前的压缩文件进行解压缩  zzip_H5.bat 752025 zukgit11");
        System.out.println("3.对解压缩出来的文件进行解密   zrule_apply_G2.bat *_7_good_batch  ");
        System.out.println("4.进入到解压文件夹 cd ./good_batch  ");
        System.out.println("5.对解压的文件搜索输出快捷方式文件夹  zrule_apply_G2.bat *_14  .mp4  .avi   .wmv .rmvb  .flv .3gp");
        System.out.println("6.打开当前文件    zzfile_3.bat ");
        System.out.println("zrule_apply_G2.bat  #_8   &&   zzip_H5.bat 752025 zukgit11  &&  zrule_apply_G2.bat *_7_good_batch  && cd ./good_batch && zzfile_3.bat ");

        System.out.println();
        System.out.println();
        System.out.println("________________________ jpg png webp 加密 解密 批量操作 ________________________ ");
        System.out.println(" zrule_apply_G2.bat *_18  .jpg   && zrule_apply_G2.bat *_18  .png  && zrule_apply_G2.bat *_18  .webp  &&   zrule_apply_G2.bat #_7_bad_batch       【对img改名并加密】");
        System.out.println(" zrule_apply_G2.bat *_7_good_batch     【对加密文件解密】");
        PrintHead_End(" 文件加密解密操作 End    ");
    }












}