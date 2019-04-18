# hutool工具网站
```

hutool文档地址： https://www.hutool.cn/docs/


hutool的Git地址：  https://github.com/looly/hutool

API介绍文档: https://apidoc.gitee.com/loolly/hutool/


jar包地址：  https://repo1.maven.org/maven2/cn/hutool/hutool-all/4.5.6/


zxing 二维码解析包下载:   http://repo1.maven.org/maven2/com/google/zxing/core/

hutool-aop JDK动态代理封装，提供非IOC下的切面支持
hutool-bloomFilter 布隆过滤，提供一些Hash算法的布隆过滤
hutool-cache 缓存
hutool-core 核心，包括Bean操作、日期、各种Util等
hutool-cron 定时任务模块，提供类Crontab表达式的定时任务
hutool-crypto 加密解密模块
hutool-db JDBC封装后的数据操作，基于ActiveRecord思想
hutool-dfa 基于DFA模型的多关键字查找
hutool-extra 扩展模块，对第三方封装（模板引擎、邮件、Servlet、二维码等）
hutool-http 基于HttpUrlConnection的Http客户端封装
hutool-log 自动识别日志实现的日志门面
hutool-script 脚本执行封装，例如Javascript
hutool-setting 功能更强大的Setting配置文件和Properties封装
hutool-system 系统参数调用封装（JVM信息等）
hutool-json JSON实现
hutool-captcha 图片验证码实现
hutool-poi 针对POI中Excel的封装
hutool-socket 基于Java的NIO和AIO的Socket封装

```


# 包

## cn.hutool.system
```
Classes类:
HostInfo
JavaInfo
JavaRuntimeInfo
JavaSpecInfo
JvmInfo
JvmSpecInfo
OsInfo
RuntimeInfo
SystemUtil
UserInfo




```


### 包类打印数据
```


import java.awt.*;
import java.io.*;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.*;
import cn.hutool.core.swing.ScreenUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.img.*;
public class Hutool {


    public static void main(String[] args) {
         cn_hutool_system();

        //  ScreenUtil.captureScreen(new File(System.getProperty("user.dir") + File.separator + "out.jpg"));
        //ImgUtil.binary(new File(System.getProperty("user.dir") + File.separator + "out.jpg"),new File(System.getProperty("user.dir") + File.separator + "out1.jpg") );

//        ImgUtil.pressText(new File(System.getProperty("user.dir") + File.separator + "out.jpg"),
//                new File(System.getProperty("user.dir") + File.separator + "out2.jpg") ,
//                "Zukgit",
//                Color.BLUE,new Font("宋体",Font.BOLD,50),200 ,200 ,1);
    }


    public static void cn_hutool_system() {
        System.out.println("#########################cn_hutool_system包#########################" );

        HostInfo hostInfo = new HostInfo();
        System.out.println("=========================javaRuntimeInfo=========================\n"+ hostInfo);

        JavaInfo javaInfo = new JavaInfo();
        System.out.println("=========================javaRuntimeInfo=========================\n"+ javaInfo);

        JavaRuntimeInfo javaRuntimeInfo = new JavaRuntimeInfo();
        System.out.println("=========================javaRuntimeInfo=========================\n"+ javaRuntimeInfo);

        JavaSpecInfo javaSpecInfo  = new JavaSpecInfo();
        System.out.println("=========================javaSpecInfo=========================\n"+ javaSpecInfo);

        JvmInfo jvmInfo  = new JvmInfo();
        System.out.println("=========================jvmInfo=========================\n"+ jvmInfo);

        JvmSpecInfo jvmSpecInfo = new JvmSpecInfo();
        System.out.println("=========================jvmSpecInfo=========================\n"+ jvmSpecInfo);

        RuntimeInfo runtimeInfo = new RuntimeInfo();
        System.out.println("=========================runtimeInfo=========================\n"+ runtimeInfo);

        OsInfo   osInfo = new OsInfo();
        System.out.println("=========================osInfo=========================\n"+ osInfo);

        SystemUtil  systemUtil = new SystemUtil();

        System.out.println("=========================systemUtil=========================\n"+ systemUtil);
        systemUtil.dumpSystemInfo();

        UserInfo    userInfo = new UserInfo();
        System.out.println("=========================userInfo=========================\n"+ userInfo);

    }

}




```


### 打印输出
```

#########################cn_hutool_system包#########################
=========================hostInfo=========================
Host Name:    DESKTOP-CN5OQSF
Host Address: 192.168.137.2

=========================javaInfo=========================
Java Version:    1.8.0_144
Java Vendor:     Oracle Corporation
Java Vendor URL: http://java.oracle.com/

=========================javaRuntimeInfo=========================
Java Runtime Name:      Java(TM) SE Runtime Environment
Java Runtime Version:   1.8.0_144-b01
Java Home Dir:          C:\Program Files (x86)\Java\jdk1.8.0_144\jre
Java Extension Dirs:    C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext;C:\WINDOWS\Sun\Java\lib\ext
Java Endorsed Dirs:     C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\endorsed
Java Class Path:        C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\charsets.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\deploy.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\access-bridge-32.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\cldrdata.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\dnsns.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\jaccess.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\jfxrt.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\localedata.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\nashorn.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\sunec.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\sunjce_provider.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\sunmscapi.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\sunpkcs11.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\zipfs.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\javaws.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\jce.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\jfr.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\jfxswt.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\jsse.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\management-agent.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\plugin.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\resources.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\rt.jar;C:\Users\aaa\IdeaProjects\T1\out\production\T1;C:\Users\aaa\IdeaProjects\T1\lib\hutool.jar;C:\Users\aaa\IdeaProjects\T1\lib\B0_pinyin4j.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2018.3.5\lib\idea_rt.jar
Java Class Version:     52.0
Java Library Path:      C:\Program Files (x86)\Java\jdk1.8.0_144\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\ProgramData\Oracle\Java\javapath;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Java\jdk1.8.0_144\bin;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\bin;C:\Program Files\Git\cmd;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\MacType;C:\Program Files (x86)\ADB;C:\Program Files (x86)\Graphviz2.38\bin\dot.exe;C:\Users\aaa\AppData\Local\Programs\Python\Python36-32\Scripts\;C:\Users\aaa\AppData\Local\Programs\Python\Python36-32\;C:\Users\aaa\AppData\Local\Microsoft\WindowsApps;C:\Users\aaa\AppData\Roaming\starrynote.cn\StarryNote\;C:\Program Files (x86)\Graphviz2.38\bin;C:\1_software\cmder171025\cmder;;.
Java Protocol Packages: [n/a]

=========================javaSpecInfo=========================
Java Spec. Name:    Java Platform API Specification
Java Spec. Version: 1.8
Java Spec. Vendor:  Oracle Corporation

=========================jvmInfo=========================
JavaVM Name:    Java HotSpot(TM) Client VM
JavaVM Version: 25.144-b01
JavaVM Vendor:  Oracle Corporation
JavaVM Info:    mixed mode, sharing

=========================jvmSpecInfo=========================
JavaVM Spec. Name:    Java Virtual Machine Specification
JavaVM Spec. Version: 1.8
JavaVM Spec. Vendor:  Oracle Corporation

=========================runtimeInfo=========================
Max Memory:    247.5 MB
Total Memory:     15.5 MB
Free Memory:     11.77 MB
Usable Memory:     243.77 MB

=========================osInfo=========================
OS Arch:        x86
OS Name:        Windows 10
OS Version:     10.0
File Separator: \
Line Separator: 

Path Separator: ;

=========================systemUtil=========================
cn.hutool.system.SystemUtil@177ecd
--------------
JavaVM Spec. Name:    Java Virtual Machine Specification
JavaVM Spec. Version: 1.8
JavaVM Spec. Vendor:  Oracle Corporation

--------------
JavaVM Name:    Java HotSpot(TM) Client VM
JavaVM Version: 25.144-b01
JavaVM Vendor:  Oracle Corporation
JavaVM Info:    mixed mode, sharing

--------------
Java Spec. Name:    Java Platform API Specification
Java Spec. Version: 1.8
Java Spec. Vendor:  Oracle Corporation

--------------
Java Version:    1.8.0_144
Java Vendor:     Oracle Corporation
Java Vendor URL: http://java.oracle.com/

--------------
Java Runtime Name:      Java(TM) SE Runtime Environment
Java Runtime Version:   1.8.0_144-b01
Java Home Dir:          C:\Program Files (x86)\Java\jdk1.8.0_144\jre
Java Extension Dirs:    C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext;C:\WINDOWS\Sun\Java\lib\ext
Java Endorsed Dirs:     C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\endorsed
Java Class Path:        C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\charsets.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\deploy.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\access-bridge-32.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\cldrdata.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\dnsns.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\jaccess.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\jfxrt.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\localedata.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\nashorn.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\sunec.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\sunjce_provider.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\sunmscapi.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\sunpkcs11.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\ext\zipfs.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\javaws.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\jce.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\jfr.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\jfxswt.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\jsse.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\management-agent.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\plugin.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\resources.jar;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\lib\rt.jar;C:\Users\aaa\IdeaProjects\T1\out\production\T1;C:\Users\aaa\IdeaProjects\T1\lib\hutool.jar;C:\Users\aaa\IdeaProjects\T1\lib\B0_pinyin4j.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2018.3.5\lib\idea_rt.jar
Java Class Version:     52.0
Java Library Path:      C:\Program Files (x86)\Java\jdk1.8.0_144\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\ProgramData\Oracle\Java\javapath;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Java\jdk1.8.0_144\bin;C:\Program Files (x86)\Java\jdk1.8.0_144\jre\bin;C:\Program Files\Git\cmd;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\MacType;C:\Program Files (x86)\ADB;C:\Program Files (x86)\Graphviz2.38\bin\dot.exe;C:\Users\aaa\AppData\Local\Programs\Python\Python36-32\Scripts\;C:\Users\aaa\AppData\Local\Programs\Python\Python36-32\;C:\Users\aaa\AppData\Local\Microsoft\WindowsApps;C:\Users\aaa\AppData\Roaming\starrynote.cn\StarryNote\;C:\Program Files (x86)\Graphviz2.38\bin;C:\1_software\cmder171025\cmder;;.
Java Protocol Packages: [n/a]

--------------
OS Arch:        x86
OS Name:        Windows 10
OS Version:     10.0
File Separator: \
Line Separator: 

Path Separator: ;

--------------
User Name:        aaa
User Home Dir:    C:\Users\aaa
User Current Dir: C:\Users\aaa\IdeaProjects\T1
User Temp Dir:    C:\Users\aaa\AppData\Local\Temp\
User Language:    zh
User Country:     CN

--------------
Host Name:    DESKTOP-CN5OQSF
Host Address: 192.168.137.2

--------------
Max Memory:    247.5 MB
Total Memory:     15.5 MB
Free Memory:     11.31 MB
Usable Memory:     243.31 MB

--------------
=========================userInfo=========================
User Name:        aaa
User Home Dir:    C:\Users\aaa
User Current Dir: C:\Users\aaa\IdeaProjects\T1
User Temp Dir:    C:\Users\aaa\AppData\Local\Temp\
User Language:    zh
User Country:     CN


Process finished with exit code 0




```






## cn.hutool.core.util
```
Classes包类:          
ArrayUtil           BooleanUtil         CharsetUtil
CharUtil            ClassLoaderUtil     ClassUtil           EnumUtil
EscapeUtil          HashUtil            HexUtil             IdcardUtil
IdUtil              ImageUtil           ModifierUtil        NumberUtil
ObjectUtil          PageUtil            PinyinUtil          RandomUtil
ReferenceUtil       ReflectUtil         ReUtil              RuntimeUtil
StrUtil             TypeUtil            URLUtil             XmlUtil
ZipUtil


Enums包枚举
ModifierUtil.ModifierType
ReferenceUtil.ReferenceType

```


### StrUtil 类
```
API说明:  https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/StrUtil.html


觉得有用的API:

static String cleanBlank(CharSequence str)  清理空白字符    String item = StrUtil.cleanBlank(" h e l l o_ w o r l d");


static int	compareVersion(CharSequence version1, CharSequence version2)   比较两个版本 null版本排在最小： 
int i = StrUtil.compareVersion("10.2.31.1","10.2.30.18");     return 1【大于】    0【等于】  -1【小于】


static boolean	containsBlank(CharSequence str) 给定字符串是否包含空白符（空白符包括空格、制表符、全角空格和不间断空格） 如果给定字符串为null或者""，则返回false
if(StrUtil.containsBlank("你好啊_ world")){}


static int	count(CharSequence content, char charForSearch)   统计指定内容中包含指定字符的数量
int num = StrUtil.count("hello world","l"); 



static String[]	cut(CharSequence str, int partLength)  将字符串切分为N等份
String[] strArr = StrUtil.cut("1234567890",3); 
  for(String value: strArr){
        System.out.println("value  = "+ value  );  // 打印:  value1  = 123   value2  = 456   value3  = 789    value4  = 0
   }



static boolean	isSurround(CharSequence str, CharSequence prefix, CharSequence suffix)  给定字符串是否被字符包围
boolean flag =  StrUtil.isSurround("<audio xxxx = />","<audio" , "/>");    检查前缀和后缀是否都符合要求 



static String	removePrefixIgnoreCase(CharSequence str, CharSequence prefix)   忽略大小写去掉指定前缀
String itemStr =  StrUtil.removePrefixIgnoreCase("prez#ncjakcbjka","prez#"); 


static String	removeSuffixIgnoreCase(CharSequence str, CharSequence suffix)   忽略大小写去掉指定后缀
String itemStr =  StrUtil.removePrefixIgnoreCase("ncjakcbjkaend#","end#"); 

static String	strip(CharSequence str, CharSequence prefix, CharSequence suffix) 去除两边的指定字符串
String itemStr =  StrUtil.removePrefixIgnoreCase("prencjakcbjkaend","pre","end");     // 返回  ncjakcbjka 

static String	reverse(String str)  反转字符串  例如：abcd =》dcba
String reverseStr =  StrUtil.reverse("123456789");     // 打印 987654321


static int[]	    splitToInt(CharSequence str, char separator)   切分字符串为int数组
static int[]	    splitToInt(CharSequence str, CharSequence separator)  切分字符串为int数组
static List<String>	splitTrim(CharSequence str, char separator)      切分字符串，去除切分后每个元素两边的空白符，去除空白项


static String	uuid()  生成随机UUID
String uuidStr =  StrUtil.uuid();   // uuidStr  = f4377531-6a8f-4f18-9e31-3cb5af68c4bf
```

### RuntimeUtil 类
```
cn.hutool.core.util.RuntimeUtil;
系统运行时工具类，用于执行系统命令的工具

static void	addShutdownHook(Runnable hook)    增加一个JVM关闭后的钩子，用于在JVM关闭时执行某些操作
static Process	exec(String... cmds)          执行命令  命令带参数时参数可作为其中一个参数，也可以将命令和参数组合为一个字符串传入
static Process	exec(String[] envp, File dir, String... cmds)   执行命令   命令带参数时参数可作为其中一个参数，也可以将命令和参数组合为一个字符串传入
static Process	exec(String[] envp, String... cmds)             执行命令  命令带参数时参数可作为其中一个参数，也可以将命令和参数组合为一个字符串传入
static List<String>	execForLines(Charset charset, String... cmds)  执行系统命令，使用系统默认编码
static List<String>	execForLines(String... cmds)                   执行系统命令，使用系统默认编码
static String	execForStr(Charset charset, String... cmds)        执行系统命令，使用系统默认编码
static String	execForStr(String... cmds)                         执行系统命令，使用系统默认编码

Process proc = RuntimeUtil.exec("calc");
List<String> strListProc = RuntimeUtil.execForLines("calc");
```
