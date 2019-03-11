# 全屏__F11
```
全屏快捷键:   F11

```


# 查找__Ctrl+F
```

查找下一个   F3 
查找上一个   Shift+F3

查找上一个   F4 
查找下一个   Shift+F4

```


# 调整字体_ Ctrl + 鼠标滚轮
```
 Ctrl + 鼠标滚轮↑        // 大字体

 Ctrl + 鼠标滚轮↓        // 小字体
```


# 主题_设置-语言格式设置-选择主题->Navajo
```
设置-语言格式设置-选择主题->Navajo    土褐色

```

# 当前行上下移动_ Ctrl + Shift + ↑
```

Ctrl + Shift + ↑          // 当前行向上移动一行

Ctrl + Shift + ↓          // 当前行向下移动一行

```

# 快速复制一行_ Ctrl + D
```
Ctrl + D   // 快速复制一行 

```

# 双视图_点击tab标签下拉
```

移动到另一视图   √ 选中即可打开双视图
复制到另一视图
```

# 书签_F2
```
1. 双击行号进行书签  添加/删除
2. F2 切换到书签位置  或下一个书签
3. Shift + F2 切换到上一个书签
```

# notepad 默认快捷键
```
F1：   打开版本窗口  【鸡肋】【打开文件管理器】
F2:    书签
F3:    查找
F4：   查找
F5:    运行窗口
F6:    【未定义】 【自动换行 View-自动换行】 运行-》管理快捷键-》主菜单-》View-自动换行  存储为F6
F7：  打开搜索结果框
F8：   激活从视图【鸡肋】
F9:    【未定义】
F10:    【未定义】
F11:     切换全屏模式【鸡肋】
F12:     切换便签模式
```

# notepad默认环境变量
```

变量名称               含义              结果
FULL_CURRENT_PATH     文件路径名称      C:/test/HelloWorld.cs
CURRENT_DIRECTORY     文件目录          C:/test
FILE_NAME             文件全名称        HelloWorld.cs
NAME_PART             文件名称          HelloWorld
EXT_PART              文件扩展名        cs

```


# F5_运行命令窗口


## 打开当前文件目录FileManager_F5
```
1.   F5 打开运行窗口

2. 输入命令:        explorer  $(CURRENT_DIRECTORY) 

3.该命令可保存为快捷键执行
```

## 打开当前文件目录CMD窗口_F5
```
1.   F5 打开运行窗口

2. 输入命令:        cmd /K cd /d $(CURRENT_DIRECTORY) 

3.该命令可保存为快捷键执行 即可快速该路径打开CMD窗口
```




## notepad执行bat命令_F5
```
1. F5 打开运行窗口
2. 输入命令:  
    cmd /K cd /d $(CURRENT_DIRECTORY) & C:\Users\aaa\Desktop\zbin\A1.bat  $(FULL_CURRENT_PATH)

其中 C:\Users\aaa\Desktop\zbin\A1.bat 为对应的 BAT命令  $(FULL_CURRENT_PATH) 表示当前文件的全路径

```

### A1_添加行号
```
aaa              01      aaa
bbb              02      bbb
ccc              03      ccc
ddd              04      ddd
eee              05      eee
fff              06      fff
ggg   ===》      07      ggg
hhh              08      hhh
jjj              09      jjj
kkk              10      kkk
lll              11      lll
mmm              12      mmm
nnn              13      nnn
bbb              14      bbb
null             15      null

```

#### commandA1
```   
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A1.bat  $(FULL_CURRENT_PATH)
```

#### A1.bat
```
C:\Users\aaa\Desktop\TestA\A1.bat 

@echo off
@javac A1.java
@java A1 %1
@exit


```

#### A1.java
```
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

public class A1 {
    public static final ArrayList<String> StringArr = new ArrayList<>();

    public static void main(String[] args) {
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        if (curFile != null) {

            FileReader curReader;
            FileWriter curWriter;
            try {

                curReader = new FileReader(curFile);




                BufferedReader curBR  = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)),"utf-8"));
                String oldOneLine = "";
                String newOneLine = "";
                int indexLine = 0;

                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    indexLine++;
                    newOneLine = indexLine + "      " + oldOneLine;
                    StringArr.add(newOneLine);
                }
                curBR.close();



                BufferedWriter curBW  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)),"utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("Failed !");
        }
    }
}



```



# F5_命令小集合
```
cmd /K   echo %OS%             // 检测 %OS% 这个环境变量  打印   Windows_NT



cmd /K   echo %userprofile%\Desktop        //  打印   C:\Users\aaa\Desktop


cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A1.bat  $(FULL_CURRENT_PATH)
```