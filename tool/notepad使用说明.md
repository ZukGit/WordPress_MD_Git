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

#### A4.java
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

```