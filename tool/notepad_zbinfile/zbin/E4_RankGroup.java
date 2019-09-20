import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class E4_RankGroup {

    static NumberFormat nf = new DecimalFormat("0.00");

    // 输入的参数列表
    static ArrayList<String> mKeyWordName = new ArrayList<>();

    public static void main(String[] args) {
        // System.out.println("Hello World!");

long timestamp1 = System.currentTimeMillis();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                mKeyWordName.add(itemArgStr);
            }
        }

        if(mKeyWordName.size() == 0){
            System.out.println("输入需要排列的字符串数值为空 默认排序【 ABCDEF 】!   zrank_group ABCDEF!");
            mKeyWordName.add("ABCDEF");
        }

        for (int z = 0; z < mKeyWordName.size(); z++) {

            System.out.println("=================参数"+mKeyWordName.get(z)+"排列数据情况如下=================");

            ArrayList<String> bianhuaArr = new ArrayList<String>();
            ArrayList<String> ArrGroupA = calculSortedGroup(mKeyWordName.get(z));
            int GroupASize = ArrGroupA.size();



            ArrayList<String> allGroupArr =      calculGroupAll(mKeyWordName.get(z));
            int allCount = allGroupArr.size();
            String bianhua_title = "参数"+mKeyWordName.get(z)+"位置散列序列数长度"+ allCount;
          //  System.out.println("   位置散列序列数="+ allCount);
            for (int i = 0; i < allGroupArr.size(); i++) {
         //       System.out.println("位置散列序列数   Index="+i+ "   allCount="+ allCount+  "   Value =【"+ allGroupArr.get(i)+"】");
          String bianhua_item =  "位置散列序列数索引:"+i+"   总长:  "+allCount + "  当前值: 【 " + allGroupArr.get(i)+" 】";
                bianhuaArr.add(bianhua_item);
           }
            ArrayPrint(bianhuaArr,bianhua_title);


            ArrayList<String> gudingArr = new ArrayList<String>();
            String mTitle_guding = "参数"+mKeyWordName.get(z)+"位置固定序列长度"+ GroupASize;
            // System.out.println("位置固定序列="+ GroupASize);
            for (int i = 0; i < ArrGroupA.size(); i++) {
                //System.out.println("位置固定序列 Index="+i+ "   allCount="+ GroupASize+  "   Value =【"+ ArrGroupA.get(i)+"】");
            String guding_item = "位置固定序列索引:"+i+"   总长:  "+GroupASize + "  当前值: 【 " + ArrGroupA.get(i)+" 】";
                gudingArr.add(guding_item);
            }
            ArrayPrint(gudingArr,mTitle_guding);
        //    System.out.println("位置固定序列数="+ GroupASize+"      位置散列序列数="+allCount);
        }


        long timestamp2 = System.currentTimeMillis();
        long timedistance = timestamp2 - timestamp1;

        System.out.println("程序执行花销 "+ Double.parseDouble(nf.format((Double) (timedistance / (1024d))))+ "秒!");

        ArrayList<String> cankaoArr = new ArrayList<String>();
        cankaoArr.add("ABCD:0.1秒");
        cankaoArr.add("ABCDE:0.1秒");
        cankaoArr.add("ABCDEF:20秒");
        cankaoArr.add("ABCDEFG:1600秒");
        cankaoArr.add("123456:0.1秒");
        cankaoArr.add("1234567:0.1秒");
        cankaoArr.add("12345678:14秒");
        cankaoArr.add("123456789:800秒");
        cankaoArr.add("0123456789:X秒");
        ArrayPrint(cankaoArr,"花销参考时间");
    }


    public static  ArrayList<String> calculGroupAll(String value){
        ArrayList<String> allResultStr = new  ArrayList<String>();
        ArrayList<String> arr = calculSortedGroup(value);
        //   System.out.println(" X1 arr.size="+arr.size());
        int length = value.length();
        int[] arrLength = new int[length];
        for (int i = 0; i < length; i++) {
            arrLength[i] = i;
        }

        getIntRankGroup(arrLength,arrLength.length,0);
        //  System.out.println(" X2 intArr.size="+intArr.size());
        for (int i = 0; i < intArr.size(); i++) {
            int[] intarr = intArr.get(i);
            int intarrLength = intarr.length;
//    System.out.print("[");
//            for (int j = 0; j < intarrLength; j++) {
//                System.out.print(intarr[j]+",");
//            }
//  System.out.println("]");
            ArrayList<String>  temp =    calculStringArrAndIntArr(arr,intarr);
            //      System.out.println("X3  temp.size() = "+ temp.size());
            for (int j = 0; j < temp.size(); j++) {
                String curItemStr = temp.get(j);
                if(!allResultStr.contains(curItemStr)){
                    allResultStr.add(curItemStr);
                }
            }
        }
        allResultStr.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                    int str1Length = o1.length();
                    int str2Length = o2.length();
                    int possibleSize = 0; // 拿到小的那个
                    if(str1Length >= str2Length ){
                        possibleSize = str2Length;
                    }else{
                        possibleSize = str1Length;
                    }

                    // az全小写
                    // a..Z 一个字母大写

                    for (int i = 0; i < possibleSize; i++) {
                        char charA = o1.charAt(i);
                        char charB = o2.charAt(i); //
                        if(charA == charB ){ // 两个相同的字母  那么继续下一次循环
                            continue;
                        }else if(charB >= 97  && charA < 97 ){ // 一个大写 一个小写   大写在后
                            return 1;
                        }else if(charA >= 97  && charB < 97 ){  // 一个大写 一个小写   大写在后
                            return -1;
                        }else if(charA > charB && charA < 97 ){ // 两个字母都是大写 charA > charB        a[97]z[122]    A[65] 90[Z]
                            return 1;   // 97 Z  == 66 B
                        }else if(charB > charA && charB < 97){ //都是大写 谁小谁在前 // 97 Z  == 66 B
                            return -1;
                        }else if(charA > charB && charB >= 97){  // 都是小写 谁小谁在前
                            return 1;
                        }else if(charB > charA && charA >= 97){ // 都是小写  谁小谁在前
                            return -1;
                        }

                    }

                return 0;

            }
        });

        return allResultStr;
    }


    static int  getUpCharIndexCount(String str){  //
        int  value = 0 ;
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if(curChar < 97){
                value += i;
            }
        }
        return value;
    }


    static int  getUpCharCount(String str){
        int  value = 0 ;
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if(curChar < 97){
                value++;
            }
        }
        return value;
    }


    public static ArrayList<String> calculStringArrAndIntArr(ArrayList<String> orderStrArr , int [] orderIndex ){
        ArrayList<String> tempArr = new ArrayList<String>();
        int length = orderStrArr.size();
        String tempStr = "";
        for (int i = 0; i < length; i++) {
            String curStrValue = orderStrArr.get(i);
            tempStr="";
            char[] charArr = curStrValue.toCharArray();

            for (int j = 0; j < orderIndex.length; j++) {
                tempStr = tempStr+charArr[orderIndex[j]]+"";
            }
            if(!"".equals(tempStr) && !tempArr.contains(tempStr)){
                tempArr.add(tempStr);
            }


        }


        return tempArr;
    }


    public static ArrayList<String> calculSortedGroup(String value){
        ArrayList<String> resut = calculGroup(value);
        resut.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1); // 小写在前
            }
        });
        return resut;
    }

    public static  ArrayList<String> calculGroup(String value){
        ArrayList<String> tempArr = new ArrayList<String>();
        if(value.length() == 1){
            String uperStr = value.toUpperCase();
            String lowerStr =  value.toLowerCase();
            if(uperStr.equals(lowerStr)){
                if(!tempArr.contains(uperStr)){
                    tempArr.add(uperStr);
                }

            }else{
                if(!tempArr.contains(uperStr)){
                    tempArr.add(uperStr);
                }
                if(!tempArr.contains(lowerStr)){
                    tempArr.add(lowerStr);
                }
            }

        }else{

            String otherStr = value.substring(1);
            String firstStr = value.substring(0,1);
            String lowerStr = firstStr.toLowerCase();
            String upStringStr = firstStr.toUpperCase();
            ArrayList<String> NextArr = calculGroup(otherStr);
            for (int i = 0; i < NextArr.size(); i++) {
                String lowerTemp = (lowerStr+ NextArr.get(i)).trim();
                String upTemp = (upStringStr+ NextArr.get(i)).trim();
                if(!tempArr.contains(upTemp)){
                    tempArr.add(upTemp);
                }
                if(!tempArr.contains(lowerTemp)){
                    tempArr.add(lowerTemp);
                }
            }
        }

        return tempArr;
    }


    public static Stack<Integer> stack = new Stack<Integer>();
    public static ArrayList<int[]> intArr = new ArrayList<int[]>();

    /**
     *
     * @param shu   待选择的数组
     * @param targ  要选择多少个次  从数组中选择多少个分组进行排列
     * @param cur   当前选择的是第几次
     */

    public static void getIntRankGroup(int[] shu, int targ, int cur) {
        // TODO Auto-generated method stub
        if(cur == targ) {
            //   System.out.println(stack);
            Object[] objs = stack.toArray();
            int[] curArr = new int[stack.size()];
            int length = stack.size();
            for (int i = 0; i < length; i++) {
                curArr[i] =   (int)objs[i];
            }
            intArr.add(curArr);
            return;
        }

        for(int i=0;i<shu.length;i++) {
            if(!stack.contains(shu[i])) {
                stack.add(shu[i]);
                getIntRankGroup(shu, targ, cur+1);
                stack.pop();
            }

        }
    }

    // ArrayPrint ==============================Begin
    static  public void printArrObject(Object[] objArr,String title){
        ArrayList<String> curPropStrArr = new  ArrayList<String>();
        for (int i = 0; i < objArr.length; i++) {
            if("".equals(objArr[i].toString())){
                continue;
            }
            curPropStrArr.add(objArr[i].toString());
        }
        ArrayPrint(curPropStrArr , title);
    }


    static  int MAX_COUNT_CHAR_IN_ROW = 120;
    static  int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 120;

    public static boolean isItemLengthOver100(ArrayList<String> mStrList) {
        boolean flag = false;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > MAX_COUNT_CHAR_IN_ROW) {
                //   System.out.println("index["+i+"]  size= "+mStrList.get(i).length()+"     Value:" + mStrList.get(i) );
                return true;
            }
        }
        return flag;

    }


    public static ArrayList<String> makeStringGroup(String code, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        String oriStr = code.trim();
        while (oriStr.length() > maxcount) {
            String str1 = oriStr.substring(0, maxcount);
            fixArr.add(str1);
            oriStr = oriStr.substring(maxcount);
        }


        return fixArr;
    }


    public static ArrayList<String> sqlitString(String bigString, String sqlitChar) {
        ArrayList<String> fixArr = new ArrayList<String>();
        ArrayList<String> subArr = new ArrayList<String>();
        String[] strArr = bigString.trim().split(sqlitChar.trim());
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].length() > MAX_COUNT_CHAR_IN_ROW) {
                ArrayList<String> subArrA = null;
                if (strArr[i].contains(";")) {
                    subArrA = sqlitString(strArr[i], ";");

                } else if (strArr[i].contains("。")) {
                    subArrA = sqlitString(strArr[i], "。");

                } else if (strArr[i].contains(":")) {
                    subArrA = sqlitString(strArr[i], ":");
                } else if (strArr[i].contains(".")) {
                    subArrA = sqlitString(strArr[i], ".");
                } else if (strArr[i].contains(" ")) {
                    subArrA = sqlitString(strArr[i], " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(strArr[i], MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixArr.add(tempArr.get(j));
                    }

                }

                if (subArrA != null && isItemLengthOver100(subArrA)) {
                    String fixSub = strArr[i].substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixArr.add(fixSub);
                } else {
                    if (subArrA != null) {
                        for (int j = 0; j < subArrA.size(); j++) {
                            fixArr.add(subArrA.get(j));
                        }

                    }
                }

            } else {
                fixArr.add(strArr[i]);
            }
        }
        return fixArr;
    }

    public static ArrayList<String> toMakeListItemLess100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixLengthArr = new ArrayList<String>();

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() < MAX_COUNT_CHAR_IN_ROW) {
                fixLengthArr.add(mStrList.get(i));
            } else {
                String curMaxStr = mStrList.get(i);
                ArrayList<String> fixA = null;
                if (curMaxStr.contains(";")) {
                    fixA = sqlitString(curMaxStr, ";");
                } else if (curMaxStr.contains("。")) {
                    fixA = sqlitString(curMaxStr, "。");
                } else if (curMaxStr.contains(":")) {
                    fixA = sqlitString(curMaxStr, ":");
                } else if (curMaxStr.contains(".")) {
                    fixA = sqlitString(curMaxStr, ".");
                } else if (curMaxStr.contains(" ")) {
                    fixA = sqlitString(curMaxStr, " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(curMaxStr, MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixLengthArr.add(tempArr.get(j));
                    }
                }

                if (fixA != null) {
                    //   System.out.println(" fixA.size="+ fixA.size());
                    for (int j = 0; j < fixA.size(); j++) {
                        // System.out.println(" fixA.size="+ fixA.size() + " i="+j+"   value:"+fixA.get(j));
                    }
                } else {
                    //  System.out.println(" fixA.size= null!");
                }
                if (isItemLengthOver100(fixA)) {
                    String fixSub = curMaxStr.substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixLengthArr.add(fixSub);
                } else {
                    if (fixA != null) {
                        for (int j = 0; j < fixA.size(); j++) {
                            fixLengthArr.add(fixA.get(j));
                        }
                    }
                }


            }
        }

        return fixLengthArr;
    }


    public static int getItemMaxLength(ArrayList<String> mStrList) {
        int itemLength = 0;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > itemLength) {
                itemLength = mStrList.get(i).length();
            }

        }
        return itemLength;
    }

    public static ArrayList<String> fixStrArrMethodCommon100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curStr = mStrList.get(i);
            String fixCurStr = getFixLengthNewStr(curStr, maxcount);
            fixArr.add(fixCurStr);
        }

        return fixArr;
    }


    public static String getFixLengthNewStr(String oriStr, int maxLength) {
        String fixStr = "";
        String beginChar = "║ ";
        String endChar = "║";
        String oriStrTrim = oriStr.trim();
        int curLength = oriStrTrim.length();
        int paddingLength = maxLength - curLength;


        int chineseCount = getChineseCount(oriStr);
        paddingLength = paddingLength - chineseCount;
        if (paddingLength < 0) {
            // return "curString:" + oriStr + "  length more than" + maxLength;
            return "";
        }

        for (int i = 0; i < paddingLength; i++) {
            oriStrTrim += " ";
        }
        oriStrTrim = beginChar + oriStrTrim + endChar;
        //  oriStrTrim = beginChar + oriStrTrim ;
        fixStr = oriStrTrim;
        return fixStr;
    }

    public static int getChineseCount(String oriStr) {
        int count = 0;
        for (int i = 0; i < oriStr.length(); i++) {
            char itemChar = oriStr.charAt(i);
            /*

|| (itemChar+"").equals("，")
|| (itemChar+"").equals("’")
|| (itemChar+"").equals("‘")

|| (itemChar+"").equals("；")
             */
            if ((itemChar + "").equals("：")
                    || (itemChar + "").equals("】") || (itemChar + "").equals("【") || (itemChar + "").equals("）")
                    || (itemChar + "").equals("（") || (itemChar + "").equals("￥") || (itemChar + "").equals("—")
                    || (itemChar + "").equals("？") || (itemChar + "").equals("，") || (itemChar + "").equals("；")
                    || (itemChar + "").equals("！") || (itemChar + "").equals("《")
                    || (itemChar + "").equals("》") || (itemChar + "").equals("。") || (itemChar + "").equals("、")
            ) {
                count++;
                continue;
            }
            boolean isChinese = isContainChinese(itemChar + "");
            if (isChinese) {
                count++;
            }
        }
        return count;
    }


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void showTableLogCommon100(ArrayList<String> mStrList, String title) {
        int maxLength = getItemMaxLength(mStrList);
        ArrayList<String> fixStrArr = fixStrArrMethodCommon100(mStrList, MAX_COUNT_CHAR_IN_ROW);
        int chineseCount = getChineseCount(title);


        String beginRow = "╔════════════════════════════════════════════════" + title + "═════════════════════════════════════════════════════╗";
        String endRow = "╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝";
        int fixLength = 0;
        int oriLength = title.length();
        if (chineseCount == 0) { // 不包含汉字
            fixLength = oriLength;

        } else {
            if (chineseCount == oriLength) { // 全部包含汉字
                fixLength = 2 * oriLength;
            } else { // 一部分汉字  一部分英语

                fixLength = oriLength - chineseCount + (2 * chineseCount);
            }

        }
        String templateString = "╗";
        if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                templateString = "═" + templateString;
            }
        }

        beginRow = beginRow.replace(templateString, "╗");
        //  System.out.println(" fixStrArr.size() =" + fixStrArr.size());
        beginRow =  resetBeginRowToDefaultSize(beginRow);
        System.out.println(beginRow);
        for (int i = 0; i < fixStrArr.size(); i++) {
            System.out.println(fixStrArr.get(i));
        }
        endRow =  resetEndRowToDefaultSize(endRow);
        System.out.println(endRow);
    }

    static String resetEndRowToDefaultSize(String beginRow){
        String curBeginStr = new String(beginRow);
        int curPaddingLength =  getPaddingChineseLength(curBeginStr);
        int distance = 0 ;
        if(curPaddingLength < MAX_COUNT_CHAR_IN_ROW){
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═",distance + 3);
        curBeginStr = curBeginStr.replace("╝",paddingString+"╝");
        return curBeginStr;
    }

    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    static String   getRepeatString(String repeatSrc,int repeatCount){
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }



    static String resetBeginRowToDefaultSize(String beginRow){
        String curBeginStr = new String(beginRow);
        int curPaddingLength =  getPaddingChineseLength(curBeginStr);
        int distance = 0 ;
        if(curPaddingLength < MAX_COUNT_CHAR_IN_ROW){
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═",distance + 3);
        curBeginStr = curBeginStr.replace("╗",paddingString+"╗");
        return curBeginStr;
    }

    public static void ArrayPrint(ArrayList<String> mStrList, String title) {

        ArrayList<String> addMao = CheckAndAddMaoMethod(mStrList);
        // 对mStrList 进行 对其处理  重新转换为 对其的  ArrayList<String> new
        // 1. 判断所有字符串中 第一次出现冒号的位置   查找出最大的位置的那个 并 记录这个最大位置 xMaxLengh
        // 2.  重新排序的规则是  小字符串需要在: 之后添加  xMaxLengh - self().length 的空格 并重新加入新的数组
        ArrayList<String> firstFixedStringArrA = firstFixedStringArr(addMao);
        boolean isOver100 = isItemLengthOver100(firstFixedStringArrA);

        if (isOver100) {
            //     System.out.println("当前的字符串Item 存在大于 100字符的！");
            ArrayList<String> newLessList = toMakeListItemLess100(firstFixedStringArrA, MAX_COUNT_CHAR_IN_ROW);
            showTableLogCommon100(newLessList, title);  //  每一行都小于100个字的打印
        } else { //
            //   System.out.println("当前的字符串Item 不 存在大于 100字符的！");
            showTableLogCommon100(firstFixedStringArrA, title);  //  每一行都小于100个字的打印


        }
    }

    public static String getPaddingEmptyString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += "-";
        }
        return str;
    }

    // 加载库时搜索的路径列表AC-:\Program Files\Java\jdk1.8.0_191\bin
    // 加载库时搜索的路径列表A-:C\Program Files\Java\jdk1.8.0_191\bin
    public static String addMaoChinese(String oriStr) {
        String resultStr = "";
        int chinesePosition = getFirstChinesePosition(oriStr);
        resultStr = oriStr.substring(0, chinesePosition) + ":" + oriStr.substring(chinesePosition);
        return resultStr;
    }


    public static int getFirstChinesePosition(String str) {
        int position = 0;
        boolean getFirstChinese = false;
        char[] newChar = str.toCharArray();  //转为单个字符
        for (int i = 0; i < newChar.length; i++) {
            char curChar = newChar[i];
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(curChar + "");
            if (m.find()) {
                getFirstChinese = true;
                position = i;
            } else {
                if (getFirstChinese == true) {
                    return i;
                }
            }

        }
        return position;
    }

    public static String addMaoBlank(String oriStr) {
        String resultStr = "";
        int blankPosition = oriStr.indexOf(" ");
        resultStr = oriStr.substring(0, blankPosition) + ":" + oriStr.substring(blankPosition);
        return resultStr;
    }

    public static ArrayList<String> CheckAndAddMaoMethod(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            if (isCommonMao(curItem)) {
                fixedArr.add(curItem);
            } else {
                // 对于那些没有冒号的  字段的处理
                //1.如果包含汉字 那么就在 汉字的最后添加一个冒号
                // 2. 如果字符串中有空格 并且第一个空格的位置小于 (MAX_COUNT_CHAR_IN_ROW/2) 那么把它换成冒号
                if (isContainChinese(curItem)) {
                    String addMaoStr = addMaoChinese(curItem);
                    fixedArr.add(addMaoStr);
                } else if (curItem.contains(" ") && curItem.indexOf(" ") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
                    String addMaoStr = addMaoBlank(curItem);
                    fixedArr.add(addMaoStr);
                } else {  // 如果以上条件都不满足   那么就在字符串最前面添加一个冒号

                    fixedArr.add(":" + curItem);

                }

            }

        }
        return fixedArr;
    }


    // 存在冒号 并且 冒号的位置小于 总的一行字数的一半长度  返回true
    public static boolean isCommonMao(String oriStr) {
        boolean flag = false;
        if (oriStr.contains(":") && oriStr.indexOf(":") == oriStr.lastIndexOf(":")) {
            flag = true;  // 只有一个冒号
        } else if (oriStr.contains(":") && oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.indexOf(":\\") && oriStr.indexOf(":") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
            flag = true; // 多个冒号 并且  第一个冒号  :   在 :\ 之前
        } else if (oriStr.contains(":") && !oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.lastIndexOf(":")) {
            flag = true;   // 多个冒号
        }
        return flag;
    }

    public static ArrayList<String> firstFixedStringArr(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        int maxMaoPosition = getMaxMaoPosition(mStrList);
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            int curMaoPosition = curItem.indexOf(":");
            String pre = curItem.substring(0, curMaoPosition);
            String end = curItem.substring(curMaoPosition); // 去掉:
            int preLength = getPaddingChineseLength(pre); // 中文对齐  取中文长度
            // 需要达到一样的ChineseLength
            // int padding_chinese =  getChineseCount(pre);
            String padding = "";
            if (preLength <= maxMaoPosition) {
                int paddingLength = maxMaoPosition - preLength;
                padding = getPaddingEmptyString(paddingLength);
            }
            String fixedItem = pre + padding + end;
            fixedArr.add(fixedItem);


        }
        return fixedArr;
    }

    public static int getMaxMaoPosition(ArrayList<String> mStrList) {
        int maoPosition = 0;
        String maxString = "";
        for (int i = 0; i < mStrList.size(); i++) {
            if ((mStrList.get(i).contains(":"))) {
                int curMaoPosition = mStrList.get(i).indexOf(":");
                String maoString = mStrList.get(i).substring(0, curMaoPosition + 1);
                int paddingSize = getPaddingChineseLength(maoString);
                if (paddingSize > maoPosition) {
                    maoPosition = paddingSize;
                    maxString = mStrList.get(i);
                }
            }

        }
        //  System.out.println("最长的冒号位置: maoPosition="+maoPosition+"   string="+maxString);
        return maoPosition;
    }
    // ArrayPrint ==============================End
}