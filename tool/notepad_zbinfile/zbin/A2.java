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