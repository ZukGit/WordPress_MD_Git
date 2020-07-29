import java.io.*;
import java.util.ArrayList;

public class I5_Vim_Tip {
    static String OneLine_Pre = "\n════════ ";
    static String OneLine_End = " ════════\n";
    static String OneLine = "══════════════════════════════";
    // user.home
    static String HomePath = System.getProperties().getProperty("user.home")+ File.separator;

    static String Vim_Confige_Path =HomePath + "_vimrc";
    static File Vim_Confige_File ;

    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }


    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
        }
    }


    static ArrayList<ArrayList<String>> VimSettingTip_allSettingList = new  ArrayList<ArrayList<String>>();
    static String vimSettingContent ;  // vimrc 中填写 的 配置项



    // http://www.voidcn.com/article/p-mixgffgf-xs.html
    public static void main(String[] args) {
        initSystemInfo();

        Vim_Confige_File = new File(Vim_Confige_Path);
        if(!Vim_Confige_File.exists()){
            System.out.println("当前用户Home 目录没有 .vimrc 配置文件  Vim_Confige_Path = "+ Vim_Confige_Path);
        }else{
            System.out.println("当前用户Home 目录 存在文件 _vimrc  Vim_Confige_Path = "+ Vim_Confige_Path);
        }



        VimSettingTip();
        writeContentToFile(Vim_Confige_File,vimSettingContent);  // 写入 配置文件?


        EscEditModeKeyTip();
        ViewerModeKeyTip();
        InsertModeKeyTip();
        CommandInputModeKeyTip();
//        PullFileTip();
//        VerboseLogTip();
//        PrintLogTip();
//
//        AdbCommandTip();
    }

    static void CommandInputModeKeyTip(){


        System.out.println(OneLine);
        System.out.println(OneLine_Pre + " 命令行模式下快捷键" + OneLine_End);
        System.out.println(" Esc          退出 当前模式  为后续进入模式的准备工作  ");
        System.out.println(":1,$s/杰/强/g   【  :%s/杰/强/g  】     ▲ 替换第 n=1 行开始到最后一行中每一行的所有 杰 为 强");

        System.out.println(" :            进入 命令行模式 ");
        System.out.println("ZZ                     相当于命令行的 :x  :wq  强制重bai写然后退出(关闭)当然缓冲区du。如果仅有一个缓冲区的话，会退出 vim !");
        System.out.println(":q! 　　　 放弃编辑,直接离开");
        System.out.println(":wq! 　　　强制存档,并离开");
        System.out.println(": n       :100      将光标移到第 n=100 行");

        System.out.println(":f newname         将当前文件重命名为 file");
        System.out.println(":f              打印当前文件名称和状态，如文件的行数、光标所在的行号等");


        System.out.println("s         命令可以实现字符串的替换。具体的用法包括");
        System.out.println(":s/str1/str2/      在当前行中 用字符串 str2 替换行中首次出现的字符串 str1");
        System.out.println(":n,$s/vivian/sky/ 替换第 n 行开始到最后一行中每一行的第一个 vivian 为 sky");
        System.out.println(":1,$s/杰/强/    等同于 【 :%s/杰/强   】  替换第 n=1 行开始到最后一行中每一行的第一个 杰 为 强");
        System.out.println("n,$s/vivian/sky/g 替换第 n 行开始到最后一行中每一行所有 vivian 为 sky");
        System.out.println(":1,$s/杰/强/g     等同于 【 :%s/杰/强/g   】     ▲ 替换第 n=1 行开始到最后一行中每一行的所有 杰 为 强");
        System.out.println("  :%s/vivian/sky/  ");

        System.out.println(":scriptnames    查看启动加载的所有script脚本");
        System.out.println("scriptnames 输出结果! ");
        System.out.println(" 1: ~\\_vimrc");
        System.out.println(" 2: C:\\Program Files (x86)\\Vim\\vim82\\colors\\ron.vim");
        System.out.println(" 3: C:\\Program Files (x86)\\Vim\\vim82\\filetype.vim");
        System.out.println(" 4: C:\\Program Files (x86)\\Vim\\vim82\\menu.vim");
        System.out.println(" 5: C:\\Program Files (x86)\\Vim\\vim82\\lang\\menu_zh_cn.utf-8.vim  ");
        System.out.println(" 6: C:\\Program Files (x86)\\Vim\\vim82\\autoload\\paste.vim         ");
        System.out.println(" 7: C:\\Program Files (x86)\\Vim\\vim82\\indent.vim                 ");
        System.out.println(" 8: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\getscriptPlugin.vim ");
        System.out.println(" 9: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\gzip.vim            ");
        System.out.println("10: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\logiPat.vim         ");
        System.out.println("11: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\manpager.vim        ");
        System.out.println("12: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\matchparen.vim      ");
        System.out.println("13: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\netrwPlugin.vim     ");
        System.out.println("14: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\rrhelper.vim        ");
        System.out.println("15: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\spellfile.vim       ");
        System.out.println("16: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\tarPlugin.vim       ");
        System.out.println("17: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\tohtml.vim          ");
        System.out.println("18: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\vimballPlugin.vim   ");
        System.out.println("19: C:\\Program Files (x86)\\Vim\\vim82\\plugin\\zipPlugin.vim       ");


        System.out.println(":version     查看当前 VIM的版本");
        System.out.println(" :version                                                                       ");
        System.out.println(" VIM - Vi IMproved 8.2 (2019 Dec 12, compiled Jul 19 2020 22:10:08)             ");
        System.out.println(" MS-Windows 32 位图形界面版本 带 OLE 支持                                       ");
        System.out.println(" 包含补丁: 1-1253                                                               ");
        System.out.println(" 编译者 appveyor@APPVYR-WIN                                                     ");
        System.out.println(" 巨型版本 带图形界面。  可使用(+)与不可使用(-)的功能:                           ");
        System.out.println(" +acl                +eval               +multi_byte_ime/dyn -tag_old_static    ");
        System.out.println(" +arabic             +ex_extra           +multi_lang         -tag_any_white     ");
        System.out.println(" +autocmd            +extra_search       +mzscheme/dyn       +tcl/dyn           ");
        System.out.println(" +autochdir          -farsi              +netbeans_intg      -termguicolors     ");
        System.out.println(" +autoservername     +file_in_path       +num64              +terminal          ");
        System.out.println(" +balloon_eval       +find_in_path       +ole                -termresponse      ");
        System.out.println(" -balloon_eval_term  +float              +packages           +textobjects       ");
        System.out.println(" +browse             +folding            +path_extra         +textprop          ");
        System.out.println(" ++builtin_terms     -footer             +perl/dyn           -tgetent           ");
        System.out.println(" +byte_offset        +gettext/dyn        +persistent_undo    +timers            ");
        System.out.println(" +channel            -hangul_input       +popupwin           +title             ");
        System.out.println(" +cindent            +iconv/dyn          -postscript         +toolbar           ");
        System.out.println(" +clientserver       +insert_expand      +printer            +user_commands     ");
        System.out.println(" +clipboard          +ipv6               +profile            +vartabs           ");
        System.out.println(" +cmdline_compl      +job                +python/dyn         +vertsplit         ");
        System.out.println(" +cmdline_hist       +jumplist           +python3/dyn        +virtualedit       ");
        System.out.println(" +cmdline_info       +keymap             +quickfix           +visual            ");
        System.out.println(" +comments           +lambda             +reltime            +visualextra       ");
        System.out.println("      系统 vimrc 文件: \"$VIM\\vimrc\"                   ");
        System.out.println("      用户 vimrc 文件: \"$HOME\\_vimrc\"                 ");
        System.out.println("  第二用户 vimrc 文件: \"$HOME\\vimfiles\\vimrc\"         ");
        System.out.println("  第三用户 vimrc 文件: \"$VIM\\_vimrc\"                  ");
        System.out.println("       用户 exrc 文件: \"$HOME\\_exrc\"                  ");
        System.out.println("   第二用户 exrc 文件: \"$VIM\\_exrc\"                   ");
        System.out.println("     系统 gvimrc 文件: \"$VIM\\gvimrc\"                  ");
        System.out.println("     用户 gvimrc 文件: \"$HOME\\_gvimrc\"                ");
        System.out.println(" 第二用户 gvimrc 文件: \"$HOME\\vimfiles\\gvimrc\"        ");
        System.out.println(" 第三用户 gvimrc 文件: \"$VIM\\_gvimrc\"                 ");
        System.out.println("        defaults file: \"$VIMRUNTIME\\defaults.vim\"      ");
        System.out.println("         系统菜单文件: \"$VIMRUNTIME\\menu.vim\"         ");

    }

    static void  InsertModeKeyTip(){


        System.out.println(OneLine_Pre + " 插入模式下快捷键(未输入命令情况) " + OneLine_End);
        System.out.println(" Esc          退出 当前模式  为后续进入模式的准备工作  ");
        System.out.println("i             进入插入（insert） 插入模式  在光标左侧输入正文");
        System.out.println("I             进入插入（insert） 插入模式  在光标所在行的开头输入正文");
        System.out.println("o             进入插入（insert） 插入模式  并在当前光标下一行新建一行 进行插入编辑操作");
        System.out.println("A             进入插入（insert） 插入模式  在光标所在行的末尾输入正文");

        System.out.println("s             进入插入（insert） 插入模式  同时能删除 鼠标选中的字符串后进入插入模式  有点同 x的感觉 ");

        System.out.println("c$           删除光标所在字符串 到该行结尾$ 的字符串 然后进入 插入模式 ");

        System.out.println("c0           删除光标所在字符串 与 开头0  之间的字符串 然后进入 插入模式 ");
        System.out.println("Esc      退出插入模式  进入 编辑模式");
        System.out.println();
    }

    static void  ViewerModeKeyTip(){




        System.out.println(OneLine_Pre + " 可视模式下快捷键" + OneLine_End);
        System.out.println(" Esc          退出 当前模式  为后续进入模式的准备工作  ");
        System.out.println("V  |  v          进入可视模式（visual） 可视模式用于文字的选中");
        System.out.println("gg    光标移动到第一行第一列");
        System.out.println("G  (大小写有区别)         拖动光标移动到最后一行");
        System.out.println("ggVG  (大小写有区别)   光标选中所有文字");
        System.out.println("y         复制选中内容到 0 号寄存器 ");
        System.out.println("d          删除");
        System.out.println("p          粘贴 寄存器内容");





    }



    static void EscEditModeKeyTip(){


        System.out.println(OneLine_Pre + " 初始默认 编辑 模式 快捷键" + OneLine_End);
        System.out.println(OneLine+"重点命令");
        System.out.println("Esc          退出 当前模式 进入初始 编辑 模式  为后续进入模式的准备工作");
        System.out.println("u              撤销之前的操作");
        System.out.println("x               删除光标所指向的当前字符");
        System.out.println("0               数字0，左移光标，到本行的开头");
        System.out.println("$               右移光标，到本行的末尾");
        System.out.println("d          删除 同x");
        System.out.println("dd          删除鼠标所在行");
        System.out.println("ndd   10dd 8035dd      删除当前光标下 共 n=10 行内容，并去除空隙");
        System.out.println("p               小写字母 p，将缓冲区的内容粘贴到光标的后面");
        System.out.println("y               复制当前鼠标选中内容到缓冲区");
        System.out.println("yy              复制当前行到内存缓冲区");
        System.out.println("nyy    5yy         复制当前光标下 n=5 行内容到内存缓冲区 ");
        System.out.println(" /xxxx      / 后面跟以要搜索的字符串xxxx ，然后按回车键  正向查找");
        System.out.println(" ?xxxx      ? 后面跟以要搜索的字符串xxxx ，然后按回车键  反向查找");
        System.out.println("n      搜索结果为多个时  n 用于查找下一个 方向由 /正向 和 ?反向 决定");



        System.out.println(OneLine+"移动光标");
        System.out.println("gg                     移动当前鼠标光标到首行首列!");
        System.out.println("H                  将光标移到屏幕的最上行（Highest）");
        System.out.println("M                 将光标移到屏幕的中间（Middle） ");
        System.out.println("L                  将光标移到屏幕的最下行（Lowest）");
        System.out.println("w               在指定行内右移光标，到下一个【字的开头】( 英文 单字  中文双字)");
        System.out.println("e               在指定行内右移光标，到一个【字的末尾】");
        System.out.println("b               在指定行内左移光标，到前一个字的开头");
        System.out.println("0               数字0，左移光标，到本行的开头");
        System.out.println("$               右移光标，到本行的末尾");
        System.out.println("^                移动光标，到本行的第一个非空字符");

        System.out.println(OneLine+" 替换和删除");
        System.out.println("将光标定位于文件内指定位置后，可以用其他字符来替换光标所指向的字符，或从当前光标位置删除一个或多个字符");
        System.out.println("rx              r是replace的意思  用 x 替换光标所指向的当前字符  一个换一个");
        System.out.println("5rc             把当前选中的文字  替换为 5个c   即 ccccc   1个换5个");
        System.out.println("x               删除光标所指向的当前字符");
        System.out.println("dw               删除光标右侧的字 只删英文 只删除中文");
        System.out.println("db              删除光标左侧的字");
        System.out.println("dd              删除光标所在行，并去除空隙");
        System.out.println("ndd   10dd 8035dd      删除当前光标下 共 n=10 行内容，并去除空隙");

        System.out.println(OneLine+"粘贴和复制");
        System.out.println("p               小写字母 p，将缓冲区的内容粘贴到光标的后面");
        System.out.println("P               大写字母 P，将缓冲区的内容粘贴到光标的前面");
        System.out.println("y               复制当前鼠标选中内容到缓冲区");
        System.out.println("yy              复制当前行到内存缓冲区");
        System.out.println("nyy    5yy         复制当前光标下 n=5 行内容到内存缓冲区 ");

        System.out.println(OneLine+"搜索字符串");
        System.out.println(" /xxxx      / 后面跟以要搜索的字符串xxxx ，然后按回车键  正向查找");
        System.out.println(" ?xxxx      ? 后面跟以要搜索的字符串xxxx ，然后按回车键  反向查找");
        System.out.println("n      搜索结果为多个时  n 用于查找下一个 方向由 /正向 和 ?反向 决定");


        System.out.println(OneLine+"撤销和重复");
        System.out.println("u               撤消前一条命令的结果");
        System.out.println(".               重复最后一条修改正文的命令");




        System.out.println(OneLine+"保存退出");
        System.out.println("ZZ                     相当于命令行的 :x  :wq  强制重bai写然后退出(关闭)当然缓冲区du。如果仅有一个缓冲区的话，会退出 vim !");




    }



//        set fileencoding=utf-8       "编辑文件的保存到本地的字符编码方式
//        set fileencodings=utf-8,ucs-bom,shift-jis,gb18030,gbk,gb2312,cp936,utf-16,big5,euc-jp,latin1  "Vim自动探测fileencoding的顺序列表
//        set guifont=Monaco:h11         "设置单字节英文字体为Courier New,大小10号
//        set guifont=Courier_New:h14:cANSI  " 设置中文双字节显示字体 大小 h14
//        colo ron                       " 设置主题 为 ron

    static void VimSettingTip(){


        ArrayList<String>  zitiArr = initSettingCategoty("字体设置");
        zitiArr.add("set guifont=Monaco:h11         \"设置单字节英文字体为Courier New,大小10号");
        zitiArr.add("set guifont=Courier_New:h14:cANSI  \" 设置中文双字节显示字体 大小 h14");


        ArrayList<String>  bianmaArr = initSettingCategoty("编码格式设置");
        bianmaArr.add("set fileencodings=utf-8,ucs-bom,shift-jis,gb18030,gbk,gb2312,cp936,utf-16,big5,euc-jp,latin1  \"Vim自动探测fileencoding的顺序列表");
        bianmaArr.add("set fileencoding=utf-8       \"编辑文件的保存到本地的字符编码方式");
        bianmaArr.add("language messages zh_CN.UTF-8       \"设置语言编码!");




        ArrayList<String>  zhutiArr = initSettingCategoty("UI主题设置");
        zhutiArr.add("colo ron                       \" 设置主题 为 ron 黑底蓝字");
        zhutiArr.add("set showmode                       \" 显示当前模式 (插入模式) (命令模式)");
        zhutiArr.add("set t_Co=256                       \" 使用 256 色 ");
        zhutiArr.add("set number                       \" 显示行号   set nonumber  关闭行号   ");
        zhutiArr.add("set cursorline                      \" 光标所在的当前行高亮   ");
        zhutiArr.add("set wrap                      \" 一行文字太多自动拆行   set nowrap  关闭拆行 ");
        zhutiArr.add("set linebreak                     \" 只有遇到指定的符号（比如空格、连词号和其他标点符号），才发生折行 ");
        zhutiArr.add("set laststatus=2                     \" 是否显示状态栏。0 表示不显示，1 表示只在多窗口时显示，2 表示显示 ");
        zhutiArr.add("set  ruler                     \" 在状态栏显示光标的当前位置（位于哪一行哪一列） ");
        zhutiArr.add("set showmatch                     \" 光标遇到圆括号、方括号、大括号时，自动高亮对应的另一个圆括号、方括号和大括号 ");


        ArrayList<String>  featureArr = initSettingCategoty("feature设置");
        featureArr.add("filetype indent on                      \" 开启文件爱你类型检查 ");
        featureArr.add("set autoindent                      \" 自动保持新行相同缩进距离 ");
        featureArr.add("set softtabstop=2                      \" Tab键输入时 键入 多少个空格键 ");
        featureArr.add("set hlsearch                      \" 搜索时，高亮显示匹配结果 ");
        featureArr.add("set incsearch                      \" 输入搜索模式时，每输入一个字符，就自动跳到第一个匹配的结果 ");
        featureArr.add("set ignorecase                      \" 搜索时，高亮显示匹配结果 ");
        featureArr.add("set nobackup                      \" 不额外创建备份文件 ");
        featureArr.add("set noswapfile                      \" 不额外创建交换.swap 文件 ");
        featureArr.add("\" set undofile                      \" 保留撤销历史  撤消文件是跟原文件保存在一起的隐藏文件，文件名以.un~开头 ");
        featureArr.add("set noerrorbells                      \" 出错时，不要发出响声 ");
        featureArr.add("set visualbell                     \" 出错时，发出视觉提示，通常是屏幕闪烁 ");
        featureArr.add("set history=10000                     \" Vim 需要记住多少次历史操作");
        featureArr.add("set autoread                    \" 打开文件监视。如果在编辑过程中文件发生外部改变（比如被别的编辑器编辑了），就会发出提示");
        featureArr.add("set listchars=tab:\\|\\ ,trail:.,extends:>,precedes:<,eol:$                   \" 如果行尾有多余的空格（包括 Tab 键），该配置将让这些空格显示成可见的 ~ ");
        featureArr.add("set list                                         \" 如果行尾有多余的空格（包括 Tab 键），该配置将让这些空格显示成可见的小方块");
        featureArr.add("set wildmenu                              \" 命令模式下，底部操作指令按下 Tab 键自动补全。第一次按下 Tab，会显示所有匹配的操作指令的清单；第二次按下 Tab，会依次选择各个指令");
        featureArr.add("set wildmode=longest:list,full             \" 命令模式下，底部操作指令按下 Tab 键自动补全。第一次按下 Tab，会显示所有匹配的操作指令的清单；第二次按下 Tab，会依次选择各个指令");
        featureArr.add("set nocompatible            \" 去掉讨厌的有关vi一致性模式，避免以前版本的一些bug和局限");
        featureArr.add("set backspace=2             \" 设置 backspace 正常的删除功能");



         showAllSettingItem();
    }

    static ArrayList<String>   initSettingCategoty(String category){
        ArrayList<String> categoryArr = new    ArrayList<String>();
        categoryArr.add("\"════════ "+category);
        VimSettingTip_allSettingList.add(categoryArr);
        return categoryArr;
    }

    static void showAllSettingItem(){
        StringBuilder vimrcSB = new StringBuilder();
        System.out.println(OneLine_Pre+".vimrc文件说明  Begin "+OneLine_End);

        for (int i = 0; i < VimSettingTip_allSettingList.size(); i++) {
            ArrayList<String> settingItemList = VimSettingTip_allSettingList.get(i);
            for (int j = 0; j < settingItemList.size(); j++) {
                String mSettingItem = settingItemList.get(j);
                vimrcSB.append(mSettingItem+"\n");
                System.out.println(mSettingItem);
            }
            System.out.println();
        }
        System.out.println(OneLine_Pre+".vimrc文件说明  End "+OneLine_End);
        vimSettingContent = vimrcSB.toString();
    }
//

    static void PrintLogTip(){

        System.out.println(OneLine_Pre + " 打印Log操作 "+ OneLine_End);
        System.out.println(" adb logcat | grep zukgit " );

        for (int i = 0; i < 1; i++) {
            continue;
        }

    }


    static void PullFileTip(){

        System.out.println(OneLine_Pre + " Pull 拉取文件的操作 "+ OneLine_End);
        System.out.println("adb root & adb pull /data/system/users/0/settings_system.xml   \n");
        System.out.println("adb root & adb pull /data/system/users/0/settings_secure.xml   \n");
        System.out.println("adb root & adb pull /data/system/users/0/settings_global.xml   \n");
        System.out.println("adb root & adb pull /vendor/etc/wifi/WCNSS_qcom_cfg.ini        \n");
        System.out.println("adb root & adb pull /vendor/etc/wifi/wpa_supplicant.conf       \n");
        System.out.println("adb root & adb pull /system/etc/wifi/p2p_supplicant.conf       \n");
        System.out.println("adb root & adb pull /data/misc/wifi/WifiConfigStore.xml        \n");
        System.out.println("adb root & adb pull /data/misc/wifi/softap.conf                \n");
        System.out.println("adb root & adb pull /system/build.prop                         \n");
        System.out.println("adb root & adb shell getprop >  ./prop.txt                     \n");
        System.out.println("adb root & adb pull   /vendor/fireware_mnt/image/wlanmdsp.mbn  \n");
        System.out.println("adb root & adb pull  /vendor/fireware_mnt/image/Data.msc       \n");
        System.out.println("adb root & adb pull /vendor/rfs/msm/mpss/ramdumps              \n");
        System.out.println("adb root & adb pull /storage/emulated/0/Pictures/Screenshots   \n");
        System.out.println("adb root & adb pull /system/etc/hostapd/hostapd.deny           \n");
        System.out.println("adb root & adb pull /system/etc/hostapd/hostapd.accept         \n");
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.conf     \n");
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.accept   \n");
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.deny     \n");
        System.out.println("adb root & adb pull /data/vendor/bug2go                        \n");
    }



    static void VerboseLogTip(){

        System.out.println(OneLine_Pre + " WIFI详情开关描述 "+ OneLine_End);
        System.out.println("Settings >System > About phone > tap \"Build number\" 4 times >Developer options\n" +
                "Setting > System > Advanced > Developer options >Enable WiFi Verbose Logging  [toogle open] ");


    }




    static void AdbCommandTip(){

        System.out.println(OneLine_Pre + " Settings.apk 安装 push命令 "+ OneLine_End);
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\Settings.apk /product/priv-app/Settings/  && adb push .\\oat  /product/priv-app/Settings    ##### 连续重启两遍    ");

//        adb root && adb disable-verity && adb reboot
        System.out.println(OneLine_Pre + " adb disable-verity 提升权限命令 "+ OneLine_End);
        System.out.println(" adb root && adb disable-verity && adb reboot ");
        System.out.println(OneLine_Pre + " wifi-service.jar  push命令 "+ OneLine_End);
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\wifi-service.jar /system/framework/   ##### 连续重启两遍?    ");



    }

    static void writeContentToFile(File file, String strParam) {

        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
//                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                curBW.write(strParam);
                curBW.flush();
                curBW.close();
                //    System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
