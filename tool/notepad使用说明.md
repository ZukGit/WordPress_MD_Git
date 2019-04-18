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
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A1.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【bat】

Wscript.exe  /x %userprofile%\Desktop\zbin\A1.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【vbs】
```

#### A1.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A1.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If

```


#### A1.bat
```
C:\Users\aaa\Desktop\TestA\A1.bat 

@echo off
@cd %1
@javac A1.java
@java A1 %2
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



### A2_对齐行(命令输出的那些)

```
   71     aaa      staff     2414                         
     3      aaa  staff     102                          
   5      aaa      staff     170                          
     5      aaa      staff     170                      
     5      aaa      staff     170                         
     1      aaa      staff     44578168   

变为

71     aaa     staff     2414         
3      aaa     staff     102          
5      aaa     staff     170          
5      aaa     staff     170          
5      aaa     staff     170          
1      aaa     staff     44578168     


```

#### A2.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A2.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If


```
#### A2.bat
```
@echo off
@cd %1
@javac -encoding UTF-8 A2.java
@java A2 %2
@exit

```

#### A2.java

```

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class A2 {

    // 自动判断输入文件中的一行有多少列字符串
    public static int NUM_ERERY_LINE_OLD = 0;     // 输入1.txt文件原本的每行列数  限制条件为 NEW/OLD 是正整数
    public static int NUM_ERERY_LINE_NEW = 0;    //  输出2.txt 文件需要自定义的产生的每行列数


    public static final int PADDING_COLOM = 5;         //  填充padding的距离  每个列之间的间距
    public static final boolean NEED_SORT = false;      //   输出是否需要进行A-Z的排序  默认为false    默认为按照1.txt的读取顺序显示
    public static final boolean DEBUG_LOG = false;       // 是否打印Log标识


    public static String SRC_FILE_NAME = "1.txt";   // 输入文件1.txt
    public static String DES_FILE_NAME = "2.txt";   // 输出文件1.txt


    public static String[] splitArr;    // 读取输入 1.txt每行  原始的split返回值字符串数组
    public static String[] retContentArr;   // 读取输入 1.txt每行  原始的split返回值经过过滤规格过滤后的返回值字符串数组

    public static long fileSumLines;   // 输入文件1.txt 的总行数
    public static long newSumLines;    // 输出文件 2.txt 的总行数
    public static long stringNumOfInput_Max;    // 输入和输出文件中字符串的最大的个数

    public static int[] item_Max_Length = new int[NUM_ERERY_LINE_NEW];  // 在1.txt输入文件每个列中字符串的最大长度的数组  默认为0
    public static int[] item_Max_Length_new = new int[NUM_ERERY_LINE_NEW]; // 在2.txt文件中每个列的字符串最大长度 不足的补充padding

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

//        String mFilePath = System.getProperties().getProperty("user.dir")+File.separator+"1.txt";


        SRC_FILE_NAME =  new File(mFilePath).getAbsolutePath() ;
        DES_FILE_NAME =  new File(mFilePath).getAbsolutePath() ;
        System.out.println("DES_FILE_NAME =  "+ DES_FILE_NAME);
        getLineRow();  // 获得当前输入的数据统计
        if (NUM_ERERY_LINE_NEW == 0 || NUM_ERERY_LINE_OLD == 0) {
            System.out.println("当前文件的列数检测失败  程序以失败姿态退出！ ");
           return ;
        } else {
            System.out.println("1 ");
            item_Max_Length = new int[NUM_ERERY_LINE_NEW];  // 在1.txt输入文件每个列中字符串的最大长度的数组  默认为0
            item_Max_Length_new = new int[NUM_ERERY_LINE_NEW]; // 在2.txt文件中每个列的字符串最大长度 不足的补充padding
        }
        getLineNum();  // 获得当前输入的数据统计

        System.out.println("2 ");


        try {
            System.out.println("3 ");
            //链表数组 包含的是上面 LinkedList<String[]> 中的每一个String，这些String已经排序排好了
            LinkedList<String> sortStringlist = getAllStringItemFromInput();

            // 依据标识位 对 所有的String 进行排序
            sortStringlist = sortAllStringItemMethod(sortStringlist);

            // 链表数组 成员都是 每一行字符串进行split分割后产生的字符串数组 并且每个Item 对应的String[] 长度是 NUM_ERERY_LINE_NEW
            LinkedList<String[]> list_StringArr = new LinkedList<String[]>();
            System.out.println("4 ");

            // 填充输入到2.txt中的字符串数组的List
            fill_List_StringArr(list_StringArr, sortStringlist);


            // list_StringArr.length  就是 2.txt输出文件的行数
            System.out.println("list_StringArr.length 输出文件2.txt 总行数:" + list_StringArr.size());

            //int[] item_Max_Length 数组进行查找  找到每列最大的字符串长度
            getStringMaxLengthMethod(list_StringArr);

            // 创建2.txt  并填充数据
            fillOutputFile(list_StringArr);

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }

    }


    public static void fill_List_StringArr(LinkedList<String[]> list_StringArr, LinkedList<String> sortStringlist) {
        String[] newRow = new String[NUM_ERERY_LINE_NEW];

        for (int i = 0; i < sortStringlist.size(); i++) {

            int index = i % NUM_ERERY_LINE_NEW;
            if (index == 0 && i > 0) {
                list_StringArr.add(newRow);
                newRow = new String[NUM_ERERY_LINE_NEW];
            }
            newRow[index] = sortStringlist.get(i);
        }
        if (!list_StringArr.contains(newRow)) {
            list_StringArr.add(newRow);
        }


    }


    // 对 list_StringArr 内容进行重新填充 到  LinkedList<String[]> list_StringArr
    public static void fixSortInStringArrList(LinkedList<String[]> list_StringArr, LinkedList<String> sortStringlist) {

        int num = 0;
        for (String[] item : list_StringArr) {

            for (int i = 0; i < item.length; i++) {
                if (num + i < sortStringlist.size()) {
                    item[i] = sortStringlist.get(num + i);
                }
            }
            num = num + item.length;

        }

    }

    public static String[] getStringArr_From_EveryRow(String contentString, int num) {
        retContentArr = new String[num];
        if (contentString != null && !"".equals(contentString)) {

            if (num == 1) {

                splitArr = contentString.split(" ");
                splitArr = makeEmptyOut(splitArr);  // 把数组中的空字符 完全剔除
                if (splitArr.length > num) {

                    String contentLine = splitArr[0];
                    String fixString = fixStringMethod(contentLine);
                    retContentArr[0] = fixString;
                    if (DEBUG_LOG) System.out.println("只读取每行第一个字符串 = " + splitArr[0]);
                }

            }

            if (num == 2) {

                splitArr = contentString.split(" ");
                splitArr = makeEmptyOut(splitArr);  // 把数组中的空字符 完全剔除
                if (splitArr.length > num) {
                    retContentArr[0] = splitArr[0];
                    retContentArr[1] = splitArr[splitArr.length - 1];

                } else if (splitArr.length == num) {
                    retContentArr[0] = splitArr[0];
                    retContentArr[1] = splitArr[1];
                }

            } else {
                splitArr = contentString.split(" ");
                if (DEBUG_LOG) System.out.println("行数大于等于3: 值为“+ num+ ”  切割长度为 splitArr.length =" + splitArr.length);
                splitArr = makeEmptyOut(splitArr);  // 把数组中的空字符 完全剔除
                for (int x = 0; x < splitArr.length; x++) {


                    if (DEBUG_LOG) System.out.println("index =" + x + "   content:" + splitArr[x]);
                    if (x == splitArr.length - 1) {
                        if (DEBUG_LOG) System.out.println();
                    }

                }


                if (splitArr.length > num) {
                    int i = 0;
                    int j = 0;
                    for (i = 0; i < num; i++) {

                        retContentArr[i] = splitArr[i];

                    }
                } else if (splitArr.length == num) {
                    for (int x = 0; x < splitArr.length; x++) {

                        retContentArr[x] = splitArr[x];

                    }

                }


            }

        }
        if (DEBUG_LOG) {
            for (String value : retContentArr) {
                System.out.println("value = " + value);
            }
        }

        return retContentArr;
    }


    public static String fixStringMethod(String contentString) {
        int length = contentString.length();
        //  System.out.println("contentString1"+ contentString);
        if (contentString.contains("    ")) {
            contentString = contentString.split("    ")[0].trim();
        } else if (contentString.contains("\t")) {
            contentString = contentString.split("\t")[0].trim();
        }
        System.out.println("contentString2  =  " + contentString);
        return contentString;
    }


    public static String[] makeEmptyOut(String[] strArr) {
        String[] validStrArrRet = null;
        ArrayList<String> validStrArr = new ArrayList<String>();

        if (strArr != null) {

            for (String strItem : strArr) {
                if (strItem == null || "".equals(strItem.trim())) {
                    continue;
                }
                validStrArr.add(strItem);
            }
        }

        if (validStrArr.size() > 0) {
            validStrArrRet = new String[validStrArr.size()];


            for (int x = 0; x < validStrArr.size(); x++) {
                validStrArrRet[x] = validStrArr.get(x).trim();
            }
        }
        return validStrArrRet;

    }

    public static boolean isArrEmpty(String[] strArr) {
        boolean flag = false;

        if (strArr != null) {
            int i = 0;
            for (i = 0; i < strArr.length; i++) {
                if (strArr[i] != null && "".equals(strArr[i])) {
                    flag = true;
                    break;
                }
            }
        } else {
            flag = true;
        }
        return flag;
    }

    public static boolean checkInsert(int i, int j) {
        boolean flag = false;
        if (retContentArr != null && splitArr != null && i < retContentArr.length && j < splitArr.length) {
            if ("".equals(retContentArr[i]) && !"".equals(splitArr[j])) {
                flag = true;
            }
        }
        return flag;
    }


    public static LinkedList<String> getAllStringItemFromInput() {


        //链表数组 包含的是上面 LinkedList<String[]> 中的每一个String，这些String已经排序排好了
        LinkedList<String> sortStringlist = new LinkedList<String>();


        try {



            BufferedReader txtBR  = new BufferedReader(new InputStreamReader(new FileInputStream(new File(SRC_FILE_NAME)),"utf-8"));

            String lineContentFirst = "";   // 读取到的输入文件 1.txt 的每一行字符串


            // 一次性读出所有的字符串String   然后再重新编排？
            while (lineContentFirst != null) {
                lineContentFirst = txtBR.readLine(); // 从1.txt中读取一行字符串出来
                if (lineContentFirst == null) { // 如果读取到的字符串为null 说明读取到了末尾了
                    System.out.println("1.txt read to end!");
                    break;
                }
                // 对读取到的每行字符串 进行分拆   得到每一个当前字符串分拆后的数组
                String[] arrStr = getStringArr_From_EveryRow(lineContentFirst, NUM_ERERY_LINE_OLD);
                if (arrStr != null && arrStr.length == NUM_ERERY_LINE_OLD) {

                    for (String strItem : arrStr) {
                        sortStringlist.add(strItem);  // 包含了所有切分出来的字符串
                    }
                }
            }

            txtBR.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        return sortStringlist;
    }


    public static LinkedList<String> sortAllStringItemMethod(LinkedList<String> sortStringlist) {

        // sortStringlist.size()  是 2.txt 输出中所有字符串的数量
        System.out.println("sortStringlist.length :" + sortStringlist.size());
        if (NEED_SORT) {
            sortStringlist.sort(new Comparator<String>() {   // 对字符串进行排序使得 aA-zZ这样的排序
                @Override
                public int compare(String o1, String o2) {
                    return o1.toLowerCase().compareTo(o2.toLowerCase());
                }
            });
        }

        //  打印排序后的字符串
        if (DEBUG_LOG) {
            for (String sortItem : sortStringlist) {
                System.out.println("sortItem:" + sortItem);
            }
        }


        return sortStringlist;
    }

    public static void getLineRow() {
        try {


            BufferedReader txtBR  = new BufferedReader(new InputStreamReader(new FileInputStream(new File(SRC_FILE_NAME)),"utf-8"));
            System.out.println("NUM_ERERY_LINE_OLD=" + NUM_ERERY_LINE_OLD + "  NUM_ERERY_LINE_NEW=" + NUM_ERERY_LINE_NEW);
            String line = txtBR.readLine();
            int rowNum  = 0 ;
            if (line == null ) {

                line = txtBR.readLine();
            }

            if( line != null && line.trim().isEmpty()){
                line = txtBR.readLine();

            }
            if(line != null ){
                String numRow[] = line.trim().split(" ");

                for (int i = 0 ; i <numRow.length ; i++ ){

                    if(numRow[i].trim().isEmpty()){
                        continue;
                    }

                    rowNum++;
                }

                    NUM_ERERY_LINE_OLD = rowNum;
                    NUM_ERERY_LINE_NEW = rowNum;



            }



            System.out.println("NUM_ERERY_LINE_OLD=" + NUM_ERERY_LINE_OLD + "  NUM_ERERY_LINE_NEW=" + NUM_ERERY_LINE_NEW);
            txtBR.close();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }


    public static void getLineNum() {
        try {

            BufferedReader txtBR  = new BufferedReader(new InputStreamReader(new FileInputStream(new File(SRC_FILE_NAME)),"utf-8"));


            fileSumLines = txtBR.lines().count();  // 当前输入 1.txt的行数
            // 当前输入 1.txt 所包含该的String字符串最大的数量  也是输入文件2.txt最大的字符串数量

            stringNumOfInput_Max = fileSumLines * NUM_ERERY_LINE_OLD;
            newSumLines = (stringNumOfInput_Max / NUM_ERERY_LINE_NEW) + 1;
            System.out.println("old_txt_lines=" + fileSumLines + "  newSumLines=" + newSumLines + "   AllStringNum = " + stringNumOfInput_Max);
            txtBR.close();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }


    public static void getStringMaxLengthMethod(LinkedList<String[]> list_StringArr) {
        if (list_StringArr != null) {
            int num = 0;
            for (String[] item : list_StringArr) {  // 计算出每列的最长的字符串的长度
                if (item == null) {
                    if (DEBUG_LOG) System.out.println("item == null");
                    continue;
                }
                //  System.out.println("item != null  index:"+ (num++) +"item.length="+item.length);
                for (int z = 0; z < item.length; z++) {
                    if (item[z] == null) {
                        if (DEBUG_LOG) System.out.println("item[z] = null");
                        continue;
                    }

                    if (item[z] != null && item[z].length() > item_Max_Length[z]) {
                        if (DEBUG_LOG) System.out.println("item[z].length() = " + item[z].length());
                        item_Max_Length[z] = item[z].length();
                    }
                }

            }

            // 设置2.txt的每一列的长度值
            for (int itemContentLength = 0; itemContentLength < item_Max_Length.length; itemContentLength++) {
                item_Max_Length_new[itemContentLength] = item_Max_Length[itemContentLength] + PADDING_COLOM;  // 每一列的长度值最长值+1  避免内容重叠
                if (DEBUG_LOG)
                    System.out.println("item_Max_Length_new_index:" + itemContentLength + " item_Max_Length_new_value:" + item_Max_Length_new[itemContentLength]);
            }

        }

    }


    public static void fillOutputFile(LinkedList<String[]> list_StringArr) {

        try {
            File txt2File = new File( DES_FILE_NAME);
            if (!txt2File.exists()) {
                txt2File.createNewFile();
            }

            BufferedWriter txt2BW  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(DES_FILE_NAME)),"utf-8"));


            //2.txt的内容进行填充 list_StringArr  中 每一个String【]  是 2.txt 中的一行】
            for (String[] item : list_StringArr) {
                StringBuilder sb = new StringBuilder("");
                if (item == null) {
                    if (DEBUG_LOG) System.out.println("item == null");
                    continue;
                }
                if (DEBUG_LOG) {
                    System.out.println("item != null  item.length=" + item.length);
                    int index = 0;
                    for (String str : item) {
                        System.out.println("item[" + index + "] != null   " + "item[" + index + "]" + str);
                        index++;
                    }

                }
                for (int z = 0; z < item.length; z++) {
                    if (item[z] == null) {
                        continue;
                    }
                    int padding = item_Max_Length_new[z] - item[z].length();
                    String paddingStr = "";
                    for (int paddingNum = 0; paddingNum < padding; paddingNum++) {
                        paddingStr += " ";
                    }
                    String content = item[z] + paddingStr;
                    sb.append(content);
                }
                txt2BW.write(sb.toString());
                txt2BW.newLine();
            }
            txt2BW.flush();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }


}


```
### A3_竖排列切换横排列(命令输出的那些)
```
1
2
3
4
5
6
7
8
9
10

转为
1 2 3 4 
5 6 7 8
9 10




```


#### A3.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A3.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If


```

#### A3.bat
```
@echo off
@cd %1
@javac -encoding UTF-8 A3.java
@java A3 %2
@exit


```


#### A3.java
```

import java.io.*;
import java.util.ArrayList;

public class A3 {


    public static String SRC_FILE_NAME = "1.txt";
    // 实现对  竖屏的字符  添加为 横屏排列的字符
    public static int rowItemMaxLength = 0;   // 最大item的长度
    public static int itemSpace = 5;
    public static int num_row = 4;   // 每行重组的个数
    public static final ArrayList<String> SrcStringArr = new ArrayList<>();  // 源数据的每行数据

    public static final ArrayList<String> DstStringArr = new ArrayList<>();  // 目的数据的每行数据

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

//        String mFilePath = System.getProperties().getProperty("user.dir") + File.separator + "1.txt";

        SRC_FILE_NAME = new File(mFilePath).getAbsolutePath();
        getSrcLineArray();
        getMaxItemLength();
        getDstLineArray();
        writerNewLine();
    }

    public static void getMaxItemLength() {

        for (String item : SrcStringArr) {

            if (item.length() > rowItemMaxLength) {
                rowItemMaxLength = item.length();
                continue;
            }
            System.out.println("最大项item的长度:  rowItemMaxLength =" + rowItemMaxLength);
        }

    }

    public static void writerNewLine() {
        try {

            BufferedWriter txt2BW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(SRC_FILE_NAME)), "utf-8"));
            for (String item : DstStringArr) {
                if (!item.isEmpty()) {
                    txt2BW.write(item);
                    txt2BW.newLine();
                }

            }

            txt2BW.flush();
            txt2BW.close();
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }


    public static void getSrcLineArray() {
        try {

            BufferedReader txtBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(SRC_FILE_NAME)), "utf-8"));
            String lineContentFirst = "";   // 读取到的输入文件 1.txt 的每一行字符串


            // 一次性读出所有的字符串String   然后再重新编排？
            while (lineContentFirst != null) {
                lineContentFirst = txtBR.readLine(); // 从1.txt中读取一行字符串出来
                if (lineContentFirst == null) { // 如果读取到的字符串为null 说明读取到了末尾了
                    System.out.println("文件读取完全");
                    break;
                }
                if (lineContentFirst != null && !lineContentFirst.isEmpty())
                    SrcStringArr.add(lineContentFirst.trim());
                //  System.out.println("lineContentFirst = " + lineContentFirst);
            }

            txtBR.close();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public static void getDstLineArray() {

        String dstLineString = "";
        for (int i = 0; i < SrcStringArr.size(); i++) {
            String item = SrcStringArr.get(i).trim();
            String fixItem = fixItemString(item);
            dstLineString = dstLineString + fixItem;
            if ((i+1) % num_row == 0) {
                if (!dstLineString.trim().isEmpty()) {
                    DstStringArr.add(dstLineString.trim());
                    System.out.println("dstLineString = " + dstLineString);
                }
                dstLineString = "";
            }
        }
        DstStringArr.add(dstLineString.trim());  // 添加剩下的那些 item


    }


    public static String fixItemString(String item) {
        if (item == null) {
            System.out.println("当前行为空1！");
            return "";

        }
        if (item != null && item.isEmpty()) {

            System.out.println("当前行为空2！");
            return "";
        }

        int fixSpace = rowItemMaxLength - item.length();
        String spaceString = "";
        for (int j = 0; j < fixSpace + itemSpace; j++) {

            spaceString = spaceString + " ";
        }
        item = item + spaceString;
        System.out.println("item = "+ item);
        return item;

    }

}




```

### A4_在每行开头以及结尾添加固定字符串
```
1.在行首添加字符串
2.在行尾添加字符串
3.同时的行首以及行尾添加字符串

【1】在行首添加字符串示例(同 alt键)

abcd.mp3
1.mp3
23.mp3
456.mp3
78910.mp3

转为
<audio> <source src="abcd.mp3
<audio> <source src="1.mp3
<audio> <source src="23.mp3
<audio> <source src="456.mp3
<audio> <source src="78910.mp3



【2】在行尾添加字符串示例(由于长度不一致 所以 alt键 发挥不了作用)
<audio> <source src="abcd.mp3
<audio> <source src="1.mp3
<audio> <source src="23.mp3
<audio> <source src="456.mp3
<audio> <source src="78910.mp3
转为
<audio> <source src="abcd.mp3" /><audio>
<audio> <source src="1.mp3" /><audio>
<audio> <source src="23.mp3" /><audio>
<audio> <source src="456.mp3" /><audio>
<audio> <source src="78910.mp3" /><audio>


【3】同时的行首以及行尾添加字符串
abcd.mp3
1.mp3
23.mp3
456.mp3
78910.mp3
转为
<audio> <source src="abcd.mp3" /><audio>
<audio> <source src="1.mp3" /><audio>
<audio> <source src="23.mp3" /><audio>
<audio> <source src="456.mp3" /><audio>
<audio> <source src="78910.mp3" /><audio>

```


#### A4.vbs
```
Set args = WScript.Arguments
If args.Count = 3 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)
paramString=WScript.Arguments(2)

command = "cmd /c "+ zbinpath +"\A4.bat  " +zbinpath+"\  "+textpath+"  "+paramString
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If


```

#### A4.bat
```
@echo off
@cd %1
@javac -encoding UTF-8 A4.java
@echo %3 
@java A4 %2 %3

```

#### A4.java
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

public class A4 {
    public static final ArrayList<String> StringArr = new ArrayList<>();
    /*paramString 替换规则:
    1.【z_z = 空格】
    2.【z# = =等号】
    3.【z[ = <】
    4.【z] = >】
    5. 【z`= 引号"】
        */

    public static String acceptRule(String param) {
        if (param == null) {
            return null;
        }
        if (param.contains("z_z")) {
            param = param.replaceAll("z_z", " ");
        }
        if (param.contains("z#")) {
            param = param.replaceAll("z#", "=");
        }
        while (param.contains("z[")) {
            param = param.replace("z[", "<");
        }

        if (param.contains("z]")) {
            param = param.replaceAll("z]", ">");

        }
        if (param.contains("z`")) {
            param = param.replaceAll("z`", "\"");
        }
        // accept rule param = pre=<audio><source src#end=/><audio>
        System.out.println("accept rule param = "+ param);
        return param;
    }

    public static void main(String[] args) {
        //===============VS-test===============
        String mFilePath = null;
        String preString = null;
        String endString = null;
        if (args.length >= 2) {
            mFilePath = args[0];
            String paramString = args[1]; //  prez#"xxxx"endz#"yyyy"
            System.out.println("mFilePath =" + mFilePath + "  paramString = " + paramString);
            if (paramString != null && paramString.contains("prez") && paramString.contains("endz")) {
                // 前后都要添加字符串的情况
                if (paramString.indexOf("prez") < paramString.indexOf("endz")) {
                    preString = paramString.substring(paramString.indexOf("#") + 1, paramString.indexOf("endz"));
                    endString = paramString.substring(paramString.lastIndexOf("#") + 1, paramString.length());
                    System.out.println("zukgit 1 ");
                } else {  //  endz#"yyyy"prez#"xxxx"
                    System.out.println("zukgit 2 ");
                    endString = paramString.substring(paramString.indexOf("#") + 1, paramString.indexOf("prez"));
                    preString = paramString.substring(paramString.lastIndexOf("#") + 1, paramString.length());

                }

            } else if (paramString != null && paramString.contains("prez")) {
                System.out.println("zukgit 3 ");
                // 只包含要在行首添加字符串的情况   prez#"xxxx"
                preString = paramString.substring(paramString.indexOf("#") + 1, paramString.length());
                endString = "";
            } else if (paramString != null && paramString.contains("endz")) {
                // 只包含要在行尾添加字符串的情况  endz#"xxxx"
                System.out.println("zukgit 4 ");
                preString = "";
                endString = paramString.substring(paramString.indexOf("#") + 1, paramString.length());
            } else {
                System.out.println("input argument pre=xxxx;end=yyyy is error ! retry input again!");
                return;
            }
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
//===============local-test===============
//        String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";


        if ((preString != null && preString.trim().isEmpty()) && (endString != null && endString.trim().isEmpty())) {
            System.out.println("preString and endString both empty ");
            return;
        } else{
            preString = acceptRule(preString);
            endString = acceptRule(endString);
        }


        System.out.println("preString =  " + preString + " endString =" + endString);

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

                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";
                int indexLine = 0;

                while (oldOneLine != null) {
                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }
                    indexLine++;
                    newOneLine = preString + oldOneLine.trim() + endString;
                    StringArr.add(newOneLine);
                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }
}


```

### A5_生成MD语法的表格依据当前数据
```
A  B   C
1  2   3

转为

| A | B | C |
| ---- | ---- | ---- |
| 1 | 2 | 3 |


```

#### A5.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A5.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If

```

#### A5.bat
```
@echo off
@cd %1
@javac -encoding UTF-8 A5.java
@java A5 %2
@exit


```

#### A5.java
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
import java.util.HashMap;

public class A5 {
    public static final ArrayList<String> tableItemList = new ArrayList<>();
    public static int rowInLine = 0;


    public static void getRowInLine(File file) {

        String titleString;
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while ((titleString = curBR.readLine()) != null && !titleString.isEmpty()) {
                break;
            }
            while (titleString.contains("  ")) {
                titleString = titleString.replaceAll("  ", " ");
            }

            String[] strArr = titleString.split(" ");
            rowInLine = strArr.length;

            String sumString = "";
            for (String item : strArr) {
                sumString = sumString + " | " + item;
            }
            sumString = sumString + " |";
            tableItemList.add(sumString);

            String twoLine = "";
            for (int i = 0; i < rowInLine; i++) {

                twoLine = twoLine + "| ---- ";
            }
            twoLine = twoLine + "| ";
            tableItemList.add(twoLine);


            curBR.close();
        } catch (Exception e) {


        }
    }

    public static void main(String[] args) {
        //===============real-test-egin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        //===============real-test-end===============

        //===============local-test begin===============
//          String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
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

        getRowInLine(curFile);
        if (curFile != null) {
            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";

                while ((oldOneLine = curBR.readLine()) != null && !oldOneLine.isEmpty()) {
                    break;   // 跳过首行 当做标题的那行
                }

                oldOneLine = "";
                while (oldOneLine != null) {
                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.isEmpty()) {
                        continue;
                    }
                    String tableItem = new String(oldOneLine);


                    while (tableItem.contains("  ")) {
                        tableItem = tableItem.replaceAll("  ", " ");
                    }
                    String[] strArr = tableItem.split(" ");
                    int length = 0;   //
                    if (strArr.length >= rowInLine) {
                        length = rowInLine;
                    } else {
                        length = strArr.length;
                    }
                    String sumString = "";
                    for (int i = 0; i < length; i++) {
                        sumString = sumString + " | " + strArr[i];
                    }
                    sumString = sumString + " |";
                    if (length < rowInLine) {
                        int blankCount = rowInLine - length;
                        for (int j = 0; j < blankCount; j++) {
                            sumString = sumString + "  | ";
                        }
                    }
                    tableItemList.add(sumString);
                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < tableItemList.size(); i++) {
                    curBW.write(tableItemList.get(i).trim());
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }
}


```


### A6_对当前文件进行Log的分析(持续更新 )
```
log : xxxxxxx
log : xxxxxxx
转为

log : xxxxxxx   wifi开关打开
log : xxxxxxx    wifi断开连接

```

#### A6.vbs

#### A6.bat


#### A6.java(java代码打开notepad)
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

public class A6 {
    public static final ArrayList<String> StringArr = new ArrayList<>();
    public static final ArrayList<String> analysisStringArr = new ArrayList<>();
    public static final ArrayList<AbstractKeyEntry> keyEntryList = new ArrayList<>();

    static {
        keyEntryList.add(new Shutting_down_VM_KeyEntry());   // Shutting down VM APP 奔溃
        keyEntryList.add(new IS_SCREEN_ON_TRUE_KeyEntry());  // Value:IS_SCREEN_ON:  true 亮屏
        keyEntryList.add(new IS_SCREEN_ON_FLASE_KeyEntry()); // Value:IS_SCREEN_ON: false 灭屏
        keyEntryList.add(new SetWifiEnabled_TRUE_KeyEntry()); // setWifiEnabled: true
        keyEntryList.add(new SetWifiEnabled_FALSE_KeyEntry()); // setWifiEnabled: false
        keyEntryList.add(new Updating_SSID_KeyEntry());  // WifiTetherSsidPref: Updating SSID  热点名称
        keyEntryList.add(new onStateChanged_state13_KeyEntry());
        keyEntryList.add(new onStateChanged_state11_KeyEntry());
        keyEntryList.add(new Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry());
        keyEntryList.add(new Launching_fragment_com_android_settings_wifi_WifiSettings());
        keyEntryList.add(new wlan0_CTRL_EVENT_CONNECTED());
        keyEntryList.add(new wlan0_CTRL_EVENT_DISCONNECTED());
        keyEntryList.add(new wlan0_Request_to_deauthenticate());
        keyEntryList.add(new WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent());
        keyEntryList.add(new Trying_to_associate_with_SSID());
        keyEntryList.add(new wlan0_Own_MAC_address());
        //===================Verbose才能打印的Log============
        keyEntryList.add(new handleWifiApStateChange_currentState13_KeyEntry());  // 热点状态打开成功
        keyEntryList.add(new handleWifiApStateChange_currentState13_previousState12__KeyEntry());
        keyEntryList.add(new handleWifiApStateChange_currentState12_previousState11__KeyEntry());
    }


    static class wlan0_Own_MAC_address extends AbstractKeyEntry {
        wlan0_Own_MAC_address() {
            keyword = "wpa_supplicant: wlan0: Own MAC address:";
            explain = "设备 Mac地址 ";
            codePath = null;
        }

        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            String subLogLine = lineContent.substring(lineContent.indexOf("wpa_supplicant: wlan0: Own MAC address:"), lineContent.length());
            if (!logArray.contains(subLogLine)) {
                logArray.add(subLogLine);
                shouldPrint = true;

            } else {
                shouldPrint = false;
            }
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class Trying_to_associate_with_SSID extends AbstractKeyEntry {
        Trying_to_associate_with_SSID() {
            keyword = "wpa_supplicant: wlan0: Trying to associate with SSID";
            explain = "尝试连接该网络";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent extends AbstractKeyEntry {
        WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent() {
            keyword = "WifiStateMachine: FAILURE: LOST_PROVISIONING, NeighborEvent";
            explain = "IPR 丢失邻居事件发生(可能导致断线)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class wlan0_Request_to_deauthenticate extends AbstractKeyEntry {
        wlan0_Request_to_deauthenticate() {
            keyword = "wlan0: Request to deauthenticate";
            explain = "设备 主动发送 断开帧 断开当前网络！ ";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class wlan0_CTRL_EVENT_DISCONNECTED extends AbstractKeyEntry {
        wlan0_CTRL_EVENT_DISCONNECTED() {
            keyword = "wlan0: CTRL-EVENT-DISCONNECTED";
            explain = "WIFI 断开事件发生 ";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class wlan0_CTRL_EVENT_CONNECTED extends AbstractKeyEntry {
        wlan0_CTRL_EVENT_CONNECTED() {
            keyword = "wlan0: CTRL-EVENT-CONNECTED";
            explain = "WIFI 连接成功事件发生 ";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }



    static class Launching_fragment_com_android_settings_wifi_WifiSettings extends AbstractKeyEntry {
        Launching_fragment_com_android_settings_wifi_WifiSettings() {
            keyword = "SubSettings: Launching fragment com.android.settings.wifi.WifiSettings";
            explain = "用户进入WIFI设置界面";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry extends AbstractKeyEntry {
        Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry() {
            keyword = "SubSettings: Launching fragment com.android.settings.wifi.tether.WifiTetherSettings";
            explain = "用户进入热点设置界面";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class onStateChanged_state11_KeyEntry extends AbstractKeyEntry {
        onStateChanged_state11_KeyEntry() {
            keyword = "SoftApCallbackProxy: onStateChanged: state=11";
            explain = "关闭热点成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class onStateChanged_state13_KeyEntry extends AbstractKeyEntry {
        onStateChanged_state13_KeyEntry() {
            keyword = "SoftApCallbackProxy: onStateChanged: state=13";
            explain = "打开热点成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class Updating_SSID_KeyEntry extends AbstractKeyEntry {
        Updating_SSID_KeyEntry() {
            keyword = "WifiTetherSsidPref: Updating SSID";
            explain = "热点名称";
            codePath = null;
        }

        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            String subLogLine = lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());
            if (!logArray.contains(subLogLine)) {
                logArray.add(subLogLine);
                shouldPrint = true;

            } else {
                shouldPrint = false;
            }
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class SetWifiEnabled_TRUE_KeyEntry extends AbstractKeyEntry {
        SetWifiEnabled_TRUE_KeyEntry() {
            keyword = "setWifiEnabled: true";
            explain = "打开 WIFI 开关";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class SetWifiEnabled_FALSE_KeyEntry extends AbstractKeyEntry {
        SetWifiEnabled_FALSE_KeyEntry() {
            keyword = "setWifiEnabled: false";
            explain = "关闭 WIFI 开关";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class IS_SCREEN_ON_TRUE_KeyEntry extends AbstractKeyEntry {
        IS_SCREEN_ON_TRUE_KeyEntry() {
            keyword = "Value:IS_SCREEN_ON: true";
            explain = "开始亮屏";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    static class IS_SCREEN_ON_FLASE_KeyEntry extends AbstractKeyEntry {
        IS_SCREEN_ON_FLASE_KeyEntry() {
            keyword = "Value:IS_SCREEN_ON: false";
            explain = "开始灭屏";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class Shutting_down_VM_KeyEntry extends AbstractKeyEntry {
        Shutting_down_VM_KeyEntry() {
            keyword = "Shutting down VM";
            explain = "APP崩溃";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }

    // =============================Verbose解析类=================
    static class handleWifiApStateChange_currentState13_KeyEntry extends AbstractKeyEntry {
        handleWifiApStateChange_currentState13_KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=13";
            explain = "[verbose]打开热点状态成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class handleWifiApStateChange_currentState12_previousState11__KeyEntry extends AbstractKeyEntry {
        handleWifiApStateChange_currentState12_previousState11__KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=12 previousState=11";
            explain = "[verbose]热点正在打开中 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }


    static class handleWifiApStateChange_currentState13_previousState12__KeyEntry extends AbstractKeyEntry {
        handleWifiApStateChange_currentState13_previousState12__KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=13 previousState=12";
            explain = "[verbose]热点打开成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }
    }



    abstract static class AbstractKeyEntry {
        public String keyword; // 关键字
        public String explain; // 说明
        public String codePath;  // 代码 该Log打印的代码路径
        public String curLogLineContent;  // 当前记录的那行Log  从Log起始点 开始
        public ArrayList<String> logArray;  // 当前记录的有些不一样的记录行的 集合
        public boolean shouldPrint = true; // 是否应该打印


        AbstractKeyEntry() {
            logArray = new ArrayList<String>();
        }

        abstract public void preCheck(String lineContent);

        abstract public void afterCheck(String lineContent);

        public void analysisLineContent(String lineContent) {
            if (lineContent == null || !lineContent.contains(keyword)) {
                return;
            }
            preCheck(lineContent);
            String checkItem;
            if (lineContent.contains(keyword)) {
                checkItem = new String(lineContent);
                checkItem = checkItem + "    " + explain;
                if (codePath != null) {
                    checkItem = checkItem + "   【Code路径: " + codePath + "  】";
                }
                if (shouldPrint) {
                    analysisStringArr.add(checkItem);
                }

            }
            afterCheck(lineContent);
            return;
        }
    }

    public static void tryAnalysis(File mainFile) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mainFile), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {

                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                // 开始对每行开始分析
                for (AbstractKeyEntry keyEntry : keyEntryList) {
                    keyEntry.analysisLineContent(lineContent);
                }
                //

            }

            curBR.close();
            for (String item : analysisStringArr) {
                System.out.println("分析item =" + item);
            }

        } catch (Exception e) {

        }
    }


    public static void main(String[] args) {
        //===============real-test begin===============
//        String mFilePath = null;
//        if (args.length >= 1) {
//            mFilePath = args[0];
//        } else {
//            System.out.println("input argument is empty ! retry input again!");
//            return;
//        }
        //===============real-test end===============


        //===============local-test begin===============
        String mFilePath = System.getProperty("user.dir") + File.separator + "aplogcat-main.txt";
////        String preString = "<audio> <source src=\"";
////        String endString = "\" /><audio>";
        //===============local-test end===============


        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        if (curFile != null) {

            tryAnalysis(curFile);

        }



/*        if (curFile != null) {

            tryAnalysis(curFile);

            FileReader curReader;
            FileWriter curWriter;
            try {

                curReader = new FileReader(curFile);


                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";
                int indexLine = 0;

                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }
                    indexLine++;
                    newOneLine = indexLine + "      " + oldOneLine;
                    StringArr.add(newOneLine);
                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }*/


    }
}

```



### A7_对当前文件中的中文进行清除
```
A你B我C他
A你B我C他
A你B我C他


转为

A B C
A B C
A B C


```

#### A7.vbs
```

Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A7.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If


```

#### A7.bat
```

@echo off
@cd %1
@javac A7.java
@java A7 %2
@exit


```


#### A7.java
```
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A7 {
    public static final ArrayList<String> StringArr = new ArrayList<>();
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

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

            FileReader curReader;
            FileWriter curWriter;
            try {

                curReader = new FileReader(curFile);


                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";


                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }

                    newOneLine = new String(oldOneLine);
                    if (isContainChinese(newOneLine)) {
                        newOneLine = clearChinese(newOneLine);

                    }
                    StringArr.add(newOneLine.trim());
                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile(REGEX_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }

}



```


### A8_对当前文件夹内所有文件进行Log的分析(后续开发 定位先)
```
持续更新


```



### A9_把当前汉字转为下划线拼音形式
```
你我他
大家好
真的好

转为

ni_wo_ta
da_jia_hao
zhen_de_hao
=================================
中 国
美 国
俄 罗 斯
转为

zhong_guo
mei_guo
e_luo_si


```
#### A9.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A9.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If

```


#### A9.bat
```
@echo off
@cd %1
@javac  -cp A9_pinyin4j.jar -encoding UTF-8  A9.java 
@java -cp .;A9_pinyin4j.jar A9 %2
@exit

```


#### A9_pinyin4j.jar
#### A9.java
```

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.util.ArrayList;

/**
 * 汉字转换为拼音
 *
 * @author Red
 */
public class A9 {

    public static final ArrayList<String> StringArr = new ArrayList<>();

    public static void main(String[] args) {

        //System.out.println(ToFirstChar("ABC  汉字转换为拼音CBA").toUpperCase()); //转为首字母大写
        // System.out.println(ToPinyinWithLine("A周 B杰 C伦"));
        // System.out.println(ToPinyinWithLine("ABC汉字转换为拼音CBA"));

        //===============real-test begin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        //===============real-test end===============


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
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";

                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }

                    newOneLine = new String(oldOneLine);
                    newOneLine = ToPinyinWithLine(newOneLine);
                    if (newOneLine != null && !newOneLine.trim().isEmpty()) {
                        StringArr.add(newOneLine);
                    }

                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }

    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese
     * @return
     */
    public static String ToFirstChar(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);  // charAt(0)
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    /**
     * 汉字转为拼音
     *
     * @param chinese
     * @return
     */
    public static String ToPinyin(String chinese) {
        if (chinese == null || chinese.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(chinese);
        while (curItem.contains(" ")) {
            curItem = curItem.replaceAll(" ", "");
        }
        String pinyinStr = "";
        char[] newChar = curItem.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0]; // [0] 标识当前拼音 汉-> han
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {  // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }


    /**
     * 汉字转为拼音 空间以下划线_分割
     * 1.每个汉字前面添加_
     * 2.每个汉字后面添加_
     * 3.把所有的两个__ 下划线转为 一个下划线
     *
     * @param chinese
     * @return
     */
    public static String ToPinyinWithLine(String chinese) {
        if (chinese == null || chinese.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(chinese);
        while (curItem.contains(" ")) {
            curItem = curItem.replaceAll(" ", "");
        }
        String pinyinStr = "";
        char[] newChar = curItem.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += "_" + PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0] + "_"; // [0] 标识当前拼音 汉-> han

                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {  // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
                pinyinStr += newChar[i];
            }
        }
        while (pinyinStr.contains("__")) {
            pinyinStr = pinyinStr.replaceAll("__", "_");
            System.out.println("pinyinStr1 = " + pinyinStr);
        }

        while (pinyinStr.contains("u:")) {  // 女转为 nu:   绿 lu:   需要转为 nv  lv
            pinyinStr = pinyinStr.replaceAll("u:", "v");
            System.out.println("pinyinStr1 = " + pinyinStr);
        }

        while (pinyinStr.startsWith("_")) {
            pinyinStr = pinyinStr.substring(1, pinyinStr.length());
            System.out.println("pinyinStr2 = " + pinyinStr);
        }
        while (pinyinStr.endsWith("_")) {
            pinyinStr = pinyinStr.substring(0, pinyinStr.length() - 1);
            System.out.println("pinyinStr3 = " + pinyinStr);
        }
        return pinyinStr;
    }

}


```

### A0_把当前井号与资源一一对齐
```
#### 半岛铁盒
#### 最后的战役
#### 可爱女人
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ke_ai_nv_ren.mp3"type="audio/mpeg"/></audio>
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ban_dao_tie_he.mp3"type="audio/mpeg"/></audio>
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/zui_hou_de_zhan_yi.mp3"type="audio/mpeg"/></audio>

转为

#### 半岛铁盒
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ban_dao_tie_he.mp3"type="audio/mpeg"/></audio>
#### 最后的战役
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/zui_hou_de_zhan_yi.mp3"type="audio/mpeg"/></audio>
#### 可爱女人
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ke_ai_nv_ren.mp3"type="audio/mpeg"/></audio>

================================

#### ban_dao_tie_he
#### zui_hou_de_zhan_yi
#### ke_ai_nv_ren
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ban_dao_tie_he.mp3"type="audio/mpeg"/></audio>
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/zui_hou_de_zhan_yi.mp3"type="audio/mpeg"/></audio>
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ke_ai_nv_ren.mp3"type="audio/mpeg"/></audio>


转为

#### ban_dao_tie_he
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ban_dao_tie_he.mp3"type="audio/mpeg"/></audio>
#### zui_hou_de_zhan_yi
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/zui_hou_de_zhan_yi.mp3"type="audio/mpeg"/></audio>
#### ke_ai_nv_ren
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ke_ai_nv_ren.mp3"type="audio/mpeg"/></audio>



```

#### A0.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A0.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If



```

#### A0.bat
```
@echo off
@cd %1
@javac  -cp A0_pinyin4j.jar -encoding UTF-8  A0.java 
@java -cp .;A0_pinyin4j.jar A0 %2
@exit


```

#### A0_pinyin4j.jar


#### A0.java
```

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 汉字转换为拼音
 *
 * @author Red
 */
public class A0 {
    public static final ArrayList<String> stringArrTitleOrigin = new ArrayList<>();
    public static final ArrayList<String> stringArrSource = new ArrayList<>();
    public static final ArrayList<String> missLineArr = new ArrayList<>();
    public static final ArrayList<String> stringArrTitleEnglish = new ArrayList<>();
    public static final ArrayList<String> StringArr = new ArrayList<>();
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";
    public static final Map<String,String> english2originMap = new HashMap<String,String>();
    /*
    1.读取文件 中 包含 # 号的那些行  放入 stringArrTitleOrigin( 可能包含汉字 ) ,
      把 stringArrTitleOrigin 中的中文转为拼音 放入到 stringArrTitleEnglish  建立对应关系
    2.读取# 号中 不包含 # 号的 那些行  放入  stringArrSource
    3.循环 stringArrTitleEnglish 中的关键词 , 在  stringArrSource 中找到 匹配项
      找到的话 就加入 按顺序  title 加入到 StringArr ， source 加入到 StringArr
     4. 最后打印这个 StringArr
     5. 打印没有匹配到的那些项
        */
    public static void main(String[] args) {

        //System.out.println(ToFirstChar("ABC  汉字转换为拼音CBA").toUpperCase()); //转为首字母大写
        // System.out.println(ToPinyinWithLine("A周 B杰 C伦"));
        // System.out.println(ToPinyinWithLine("ABC汉字转换为拼音CBA"));

        //===============real-test begin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        //===============real-test end===============


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
            fillOriginArr(curFile);   // 填充  stringArrTitleOrigin  和  stringArrSource
            fillTitleEnglish();   //  填充 stringArrTitleEnglish
            matchTitleAndSource();
            findMissMatch();
            if(missLineArr.size() > 0){
                missLineArr.add(0,"未匹配项如下: ");
                StringArr.addAll(missLineArr);
            }
            try {

                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }



    public static void findMissMatch() {

//        public static final ArrayList<String> stringArrTitleOrigin = new ArrayList<>();
//        public static final ArrayList<String> stringArrSource = new ArrayList<>();
//        public static final ArrayList<String>  StringArr = new ArrayList<>();
        ArrayList<String> allLine = new ArrayList<>();
        allLine.addAll(stringArrTitleOrigin);
        allLine.addAll(stringArrSource);
        for (String item: allLine){
             if(!StringArr.contains(item.trim())){
                 missLineArr.add(item);
             }
        }
    }


    public static void matchTitleAndSource() {
        if(stringArrTitleEnglish.size() > 0 && stringArrSource.size() > 0){

            for (String item1: stringArrTitleEnglish){
                        for(String item2: stringArrSource){
                            if(item2.contains(item1)){
                                StringArr.add(english2originMap.get(item1).trim());
                                StringArr.add(item2.trim());
                            }

                        }

            }
        }
    }

    //  填充 stringArrTitleEnglish and  english2originMap
    public static void fillTitleEnglish() {
        if (stringArrTitleOrigin.size() > 0) {
            for (String item : stringArrTitleOrigin) {
                if (isContainChinese(item)) {
                    String str = fixChineseLine(item);
                    if (str != null && !str.trim().isEmpty()) {
                        stringArrTitleEnglish.add(str);
                        english2originMap.put(str,item);
                    }
                } else {
                    String str = fixLine(item);
                    if (str != null && !str.trim().isEmpty()) {
                        stringArrTitleEnglish.add(str);
                        english2originMap.put(str,item);
                    }
                }
            }
        }
    }

    public static String fixChineseLine(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(str);

        while (curItem.contains("#")) {
            curItem = curItem.replaceAll("#", "").trim();
        }
        while (isContainChinese(curItem)) {
            curItem = ToPinyinWithLine(curItem);
        }

        return curItem;
    }

    public static String fixLine(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(str);

        while (curItem.contains("#")) {
            curItem = curItem.replaceAll("#", "").trim();
        }
        return curItem;
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile(REGEX_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    // 填充  stringArrTitleOrigin  和  stringArrSource
    public static void fillOriginArr(File curFile) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
            String oldOneLine = "";
            String newOneLine = "";

            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                    continue;
                }

                newOneLine = new String(oldOneLine);
                if (newOneLine.contains("#")) {
                    stringArrTitleOrigin.add(newOneLine);
                } else {
                    stringArrSource.add(newOneLine);
                }
            }
            curBR.close();
        } catch (Exception e) {

        }

    }

    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese
     * @return
     */
    public static String ToFirstChar(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);  // charAt(0)
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    /**
     * 汉字转为拼音
     *
     * @param chinese
     * @return
     */
    public static String ToPinyin(String chinese) {
        if (chinese == null || chinese.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(chinese);
        while (curItem.contains(" ")) {
            curItem = curItem.replaceAll(" ", "");
        }
        String pinyinStr = "";
        char[] newChar = curItem.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0]; // [0] 标识当前拼音 汉-> han
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {  // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }


    /**
     * 汉字转为拼音 空间以下划线_分割
     * 1.每个汉字前面添加_
     * 2.每个汉字后面添加_
     * 3.把所有的两个__ 下划线转为 一个下划线
     *
     * @param chinese
     * @return
     */
    public static String ToPinyinWithLine(String chinese) {
        if (chinese == null || chinese.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(chinese);
        while (curItem.contains(" ")) {
            curItem = curItem.replaceAll(" ", "");
        }
        String pinyinStr = "";
        char[] newChar = curItem.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += "_" + PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0] + "_"; // [0] 标识当前拼音 汉-> han

                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {  // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
                pinyinStr += newChar[i];
            }
        }
        while (pinyinStr.contains("__")) {
            pinyinStr = pinyinStr.replaceAll("__", "_");
            System.out.println("pinyinStr1 = " + pinyinStr);
        }
		
        while (pinyinStr.contains("u:")) {  // 女转为 nu:   绿 lu:   需要转为 nv  lv
            pinyinStr = pinyinStr.replaceAll("u:", "v");
            System.out.println("pinyinStr1 = " + pinyinStr);
        }
		
        while (pinyinStr.startsWith("_")) {
            pinyinStr = pinyinStr.substring(1, pinyinStr.length());
            System.out.println("pinyinStr2 = " + pinyinStr);
        }
        while (pinyinStr.endsWith("_")) {
            pinyinStr = pinyinStr.substring(0, pinyinStr.length() - 1);
            System.out.println("pinyinStr3 = " + pinyinStr);
        }
        return pinyinStr;
    }

}



```



### B0_解析当前路径下的所有文件(非文件夹)生成<audio片段>
```
空    文件夹内文件： 蜗牛.mp3    zui_hou_de_zhan_yi.mp3   yuan_you_hui.mp3   路径为：  C:\Users\aaa\Desktop\zbin\test\yuan_you_hui.mp3

转为

#### yuan_you_hui
<audio controls><source src="C:\Users\aaa\Desktop\zbin\test\yuan_you_hui.mp3" type="audio/mpeg"/></audio>
#### zui_hou_de_zhan_yi
<audio controls><source src="C:\Users\aaa\Desktop\zbin\test\zui_hou_de_zhan_yi.mp3" type="audio/mpeg"/></audio>
#### 蜗牛
<audio controls><source src="C:\Users\aaa\Desktop\zbin\test\wo_niu.mp3" type="audio/mpeg"/></audio>



```


### B1_解析当前文件第一行内容转为二维码并自动打开(可设置快捷键)
```
123456789

转为

二维码图片

```

### B2_在当前文件对选中的字符串进行翻译
```



```


# F5_命令小集合
```
cmd /K   echo %OS%             // 检测 %OS% 这个环境变量  打印   Windows_NT



cmd /K   echo %userprofile%\Desktop        //  打印   C:\Users\aaa\Desktop

Wscript.exe  /x %userprofile%\Desktop\zbin\A1.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【A1 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A1.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A1 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\A2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  【A2 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\A3.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A3 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A3.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  【A3 bat】



在VBS中使用下划线_代表空格     在BAT中不需要使用下划线代表空格 【A4 vbs OK】  【A4 vbs  建议使用 bat来操作  使用 空格等号<> vbs需要替换规则】
Wscript.exe /x %userprofile%\Desktop\zbin\A4.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH) endz#"yyyy"prez#"xxxx"     【A4 vbs OK】
Wscript.exe /x %userprofile%\Desktop\zbin\A4.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH) endz#"yy_yy"prez#"xx_xx"  【A4 vbs OK 】
Wscript.exe /x %userprofile%\Desktop\zbin\A4.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH) endz#"后缀"prez#"前缀" 
在VBS中前后缀 使用下划线z_z代表空格blank  使用#代表等号z=  使用z[代表左尖括号<    使用 z]代表> 右尖括号 前缀后缀 
1.【z_z = 空格】 2.【z# = =等号】 3.【z[ = <】  4.【z] = >】 5.【z` = " 引号】

abcd.mp3          <audio><source src="abcd.mp3"/><audio>
1.mp3             <audio><source src="1.mp3"/><audio>
23.mp3   ===》    <audio><source src="23.mp3"/><audio>
456.mp3           <audio><source src="456.mp3"/><audio>
78910.mp3         <audio><source src="78910.mp3"/><audio>
  
Wscript.exe /x %userprofile%\Desktop\zbin\A4.vbs %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH) prez#"z[audioz]z[sourcez_zsrcz#z`"endz#"z`/z]z[audioz]"  【OK 使用困难】
1.【z_z = 空格】 2.【z# = =等号】 3.【z[ = <】  4.【z] = >】 5.【z` = " 引号】
prez#"前缀"endz#"后缀"
prez#"z[audioz]z[sourcez_zsrcz#z`"endz#"z`/z]z[audioz]"
 

cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"前缀"endz#"后缀" 
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"xxxx"endz#"yyyy"   【A4 bat OK】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  endz#"yyyy"prez#"xxxx"  
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  endz#"yyyy"
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"xxxx"
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"<audio> <source src=""endz#"" /><audio>"   【A4 bat OK 使用简单无规则】


Wscript.exe  /x %userprofile%\Desktop\zbin\A5.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A5 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A5.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A5 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\A7.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A7 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A7.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A7 bat】




Wscript.exe  /x %userprofile%\Desktop\zbin\A8.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A8 vbs】  解析Log
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A8.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A8 bat】 解析Log


Wscript.exe  /x %userprofile%\Desktop\zbin\A9.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【A9 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A9.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A9 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\A0.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A0 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A0.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A0 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\B0.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B0 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B0.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【B0 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\B1.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B1 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B1.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B1 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\B2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B2 bat】



Wscript.exe  /x %userprofile%\Desktop\zbin\B3.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B3 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B3.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B3 bat】

```

