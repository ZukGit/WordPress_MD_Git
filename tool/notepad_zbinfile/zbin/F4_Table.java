
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class F4_Table {


/*  ==============================  Test Begin     ============================== */
    static ArrayList<ShowItem> showItemTest = new ArrayList<ShowItem>();
    static ArrayList<ShowItemA> showItemTestA = new ArrayList<ShowItemA>();
    static {
        showItemTest.add(new ShowItem("aa", "floa是发发的沙发grdgrd沙发沙发啊发发t", "Y", "描述grdgrdrgdgd测试1"));
        showItemTest.add(new ShowItem("b52522grdgrdgrAAb", "flgrdgrdgrdoat", "Y", "描述的诉讼费gdgdrgrdg收费测试222"));
        showItemTest.add(new ShowItem("cgrdgrdgrdc", "fl5252525oat", "Y43243242425", "描述测试的首发525rgdgrdr2525式发生3333"));
        showItemTest.add(new ShowItem("ddd525252仓", "s525252352tr", "NU你好LL", "描述但是VS发测试gdrgdgrdgrd44444"));
        showItemTest.add(new ShowItem("fffgrdgdrgrdgrdf", "stgrgdrhdhthtddgfrgdgrdr", "NULL", "描述测试4的身上的4444"));
        showItemTest.add(new ShowItem("aa", "floa是发发的沙发grdgrd沙发沙发啊发发t", "Y", "描述grdgrdrgdgd测试1"));
        showItemTest.add(new ShowItem("b52522grdgrdgrAAb", "flgrdgrdgrdoat", "Y", "描述的诉讼费gdgdrgrdg收费测试222"));
        showItemTest.add(new ShowItem("cgrdgrdgrdc", "fl5252525oat", "Y43243242425", "描述测试的首发525rgdgrdr2525式发生3333"));
        showItemTest.add(new ShowItem("ddd525252仓", "s525252352tr", "NU你好LL", "描述但是VS发测试gdrgdgrdgrd44444"));
        showItemTest.add(new ShowItem("fffgrdgdrgrdgrdf", "stgrgdrhdhthtddgfrgdgrdr", "NULL", "描述测试4的身上的4444"));
        showItemTest.add(new ShowItem("aa", "floa是发发的沙发grdgrd沙发沙发啊发发t", "Y", "描述grdgrdrgdgd测试1"));
        showItemTest.add(new ShowItem("b52522grdgrdgrAAb", "flgrdgrdgrdoat", "Y", "描述的诉讼费gdgdrgrdg收费测试222"));
        showItemTest.add(new ShowItem("cgrdgrdgrdc", "fl5252525oat", "Y43243242425", "描述测试的首发525rgdgrdr2525式发生3333"));
        showItemTest.add(new ShowItem("ddd525252仓", "s525252352tr", "NU你好LL", "描述但是VS发测试gdrgdgrdgrd44444"));
        showItemTest.add(new ShowItem("fffgrdgdrgrdgrdf", "stgrgdrhdhthtddgfrgdgrdr", "NULL", "描述测试4的身上的4444"));
        showItemTest.add(new ShowItem("aa", "floa是发发的沙发grdgrd沙发沙发啊发发t", "Y", "描述grdgrdrgdgd测试1"));
        showItemTest.add(new ShowItem("b52522grdgrdgrAAb", "flgrdgrdgrdoat", "Y", "描述的诉讼费gdgdrgrdg收费测试222"));
        showItemTest.add(new ShowItem("cgrdgrdgrdc", "fl5252525oat", "Y43243242425", "描述测试的首发525rgdgrdr2525式发生3333"));
        showItemTest.add(new ShowItem("ddd525252仓", "s525252352tr", "NU你好LL", "描述但是VS发测试gdrgdgrdgrd44444"));
        showItemTest.add(new ShowItem("fffgrdgdrgrdgrdf", "stgrgdrhdhthtddgfrgdgrdr", "NULL", "描述测试4的身上的4444"));
        showItemTest.add(new ShowItem("aa", "floa是发发的沙发grdgrd沙发沙发啊发发t", "Y", "描述grdgrdrgdgd测试1"));
        showItemTest.add(new ShowItem("b52522grdgrdgrAAb", "flgrdgrdgrdoat", "Y", "描述的诉讼费gdgdrgrdg收费测试222"));
        showItemTest.add(new ShowItem("cgrdgrdgrdc", "fl5252525oat", "Y43243242425", "描述测试的首发525rgdgrdr2525式发生3333"));
        showItemTest.add(new ShowItem("ddd525252仓", "s525252352tr", "NU你好LL", "描述但是VS发测试gdrgdgrdgrd44444"));
        showItemTest.add(new ShowItem("fffgrdgdrgrdgrdf", "stgrgdrhdhthtddgfrgdgrdr", "NULL", "描述测试4的身上的4444"));
        showItemTest.add(new ShowItem("aa", "floa是发发的沙发grdgrd沙发沙发啊发发t", "Y", "描述grdgrdrgdgd测试1"));
        showItemTest.add(new ShowItem("b52522grdgrdgrAAb", "flgrdgrdgrdoat", "Y", "描述的诉讼费gdgdrgrdg收费测试222"));
        showItemTest.add(new ShowItem("cgrdgrdgrdc", "fl5252525oat", "Y43243242425", "描述测试的首发525rgdgrdr2525式发生3333"));
        showItemTest.add(new ShowItem("ddd525252仓", "s525252352tr", "NU你好LL", "描述但是VS发测试gdrgdgrdgrd44444"));
        showItemTest.add(new ShowItem("fffgrdgdrgrdgrdf", "stgrgdrhdhthtddgfrgdgrdr", "NULL", "描述测试4的身上的4444"));

        showItemTest.add(new ShowItem("aa", "floa是发发的沙发grdgrd沙发沙发啊发发t", "Y", "描述grdgrdrgdgd测试1"));
        showItemTest.add(new ShowItem("b52522grdgrdgrAAb", "flgrdgrdgrdoat", "Y", "描述的诉讼费gdgdrgrdg收费测试222"));
        showItemTest.add(new ShowItem("cgrdgrdgrdc", "fl5252525oat", "Y43243242425", "描述测试的首发525rgdgrdr2525式发生3333"));
        showItemTest.add(new ShowItem("ddd525252仓", "s525252352tr", "NU你好LL", "描述但是VS发测试gdrgdgrdgrd44444"));
        showItemTest.add(new ShowItem("fffgrdgdrgrdgrdf", "stgrgdrhdhthtddgfrgdgrdr", "NULL", "描述测试4的身上的4444"));

        showItemTestA.add(new ShowItemA("aa", "floa是发发的沙发grdgrd沙发沙发啊发发t", "Y", "描述grdgrdrgdgd测试1"));
        showItemTestA.add(new ShowItemA("b52522grdgrdgrAAb", "flgrdgrdgrdoat", "Y", "描述的诉讼费gdgdrgrdg收费测试222"));
        showItemTestA.add(new ShowItemA("cgrdgrdgrdc", "fl5252525oat", "Y43243242425", "描述测试的首发525rgdgrdr2525式发生3333"));
        showItemTestA.add(new ShowItemA("ddd525252仓", "s525252352tr", "NU你好LL", "描述但是VS发测试gdrgdgrdgrd44444"));
        showItemTestA.add(new ShowItemA("fffgrdgdrgrdgrdf", "stgrgdrhdhthtddgfrgdgrdr", "NULL", "描述测试4的身上的4444"));


    }


    static class ShowItem {
        String arr;
        String brr;
        String crr;
        String drr;

        ShowItem(String aa, String bb, String cc, String dd) {
            this.arr = aa;
            this.brr = bb;
            this.crr = cc;
            this.drr = dd;
        }

    }


    static class ShowItemA {
        String a;
        String b;
        String c;
        String d;

        ShowItemA(String aa, String bb, String cc, String dd) {
            this.a = aa;
            this.b = bb;
            this.c = cc;
            this.d = dd;
        }

    }

// main2 > main  to test
    public static void test1() {

        LinkedHashMap<String,String> arr2titleMap = new LinkedHashMap<String,String>();
        arr2titleMap.put("drr","属性D");
        arr2titleMap.put("crr","属性C");
        arr2titleMap.put("brr","属性B");
        arr2titleMap.put("arr","属性A");
        TablePrint(showItemTest.toArray(),arr2titleMap,"Test");

        LinkedHashMap<String,String> arr2titleMapA = new LinkedHashMap<String,String>();
        arr2titleMapA.put("a","属性A");
        arr2titleMapA.put("c","属性C");
        arr2titleMapA.put("d","属性D");
        TablePrint(showItemTestA.toArray(),null,"TestA");

    }
    /*  ==============================  Test End     ============================== */


    static NumberFormat nf = new DecimalFormat("0.00");


    // 输入的参数列表
    static ArrayList<String> mKeyWordName = new ArrayList<>();

    public static void main(String[] args) {

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

        if (mKeyWordName.size() == 0) {
            System.out.println("用户输入的参数为空  仅仅输出一个无意义表格！");
            test1();
            // z1 打印所有的字典信息
            // z2 打印对应的使用方法说明
            return;
        }

        for (int i = 0; i < mKeyWordName.size(); i++) {
            String inputStr = mKeyWordName.get(i);
            System.out.println("input["+i+"] : "+ inputStr);
        }

    }











    //  TablePrint=======================Begin
    @SuppressWarnings("unchecked")
    public static void TablePrint(Object[] showItem , LinkedHashMap<String,String> arrAndtitleMap , String titlevalue) {
        System.out.println();
        if(showItem == null || showItem.length == 0  ){
            System.out.println();
            System.out.println(" 当前打印数组为空  无法打印! 请检查参数！");
        }


     //   PrintStringMap(arrAndtitleMap);



        if(arrAndtitleMap == null || arrAndtitleMap.size() == 0){
            System.out.println();
            System.out.println(" 当前打印必需参数 head属性(key)-head名称(value)  为空 , 将默认使用 arr-arr 的样式  ");
            LinkedHashMap<String,String> paramMap = new LinkedHashMap<String,String>();
            Object obj = showItem[0];
            Field[] fieldList =  obj.getClass().getDeclaredFields();
            for (int i = 0; i < fieldList.length; i++) {
                paramMap.put(fieldList[i].getName()+"" , fieldList[i].getName()+"");
            }
            arrAndtitleMap = paramMap;

        }
        if (showItem.length > 0) {
            Object item = showItem[0];
//            Map<String,String> arr2titleMap = new HashMap<String,String>();
//            arr2titleMap.put("arr","属性A");
//            arr2titleMap.put("brr","属性B");
//            arr2titleMap.put("crr","属性C");
//            arr2titleMap.put("drr","属性D");
           InitTableHead(item,arrAndtitleMap);

            ArrayList<String> title = tableTitleList;
            ArrayList<Field> tableField = tableFieldList;
            Map<Field,String> titleFieldMap = tablefieldTitleMap;
            Map<String,Field> fieldTitleMap = tableTitlefieldMap;
            int row_table_num = tableField.size();
            Map<Field, Integer> curMaxSize = new LinkedHashMap<Field, Integer>();

// 修改点

            Map.Entry<String, String> entry;
            if (arrAndtitleMap != null) {
                Iterator iterator = arrAndtitleMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, String>) iterator.next();
                   String fieldName =  entry.getKey();   // 属性名称
                    String headName  = entry.getValue();  // 表头名称
                    //  依据 field拿到Field   依据title拿到 Field
                 curMaxSize.put(fieldTitleMap.get(headName), getPaddingChineseLength(headName.trim()));

                }
            }

      //      PrintField2IntMap(curMaxSize);
//            for (int i = 0; i < tableField.size(); i++) {
//                curMaxSize.put(tableField.get(i), getPaddingChineseLength(title.get(i).trim()));
//            }

            for (int i = 0; i < showItem.length; i++) {
                Object curItem = showItem[i];

                for (int j = 0; j < tableField.size(); j++) {
                    Field curField = tableField.get(j);
                    try {
                        String curTableField = ""+getFieldValue(curItem,curField.getName());
                        int curTableFieldLength = getPaddingChineseLength(curTableField.trim());
                        if(curMaxSize.get(curField) < curTableFieldLength){
                            curMaxSize.put(curField,curTableFieldLength);
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }

            int index = 0 ;

            // 每个项 对应的   最大长度


            // 对当前的最大长度进行修正
            // 如果大小小于  DEFAULT_CONTENT_PADDING_SIZE_MIN  8 , 那么就设置为 8
            //
            int curTableItemNum = row_table_num;  // 每行的 显示项
            int curTableMaxLength = DEFAULT_CONTENT_PADDING_SIZE_MAX;

            Map<Field, Integer> fixed_MaxSizeMap = new LinkedHashMap<Field, Integer>();

            Map.Entry<Field, Integer> entryA;
            if (curMaxSize != null) {
                Iterator iterator = curMaxSize.entrySet().iterator();
                while (iterator.hasNext()) {
                    entryA = (Map.Entry<Field, Integer>) iterator.next();
                      //Map的Value
//                    System.out.println(index+"未修正  Field = "+ entry.getKey().getName());
//                    System.out.println(index+ "未修正 Value = "+ entry.getValue());
                    if(entryA.getValue() < DEFAULT_CONTENT_PADDING_SIZE_MIN){
                        fixed_MaxSizeMap.put(entryA.getKey(),DEFAULT_CONTENT_PADDING_SIZE_MIN);
                    }else if(entryA.getValue() > DEFAULT_CONTENT_PADDING_SIZE_MAX){
                        fixed_MaxSizeMap.put(entryA.getKey(),DEFAULT_CONTENT_PADDING_SIZE_MAX);
                    }else{

                        fixed_MaxSizeMap.put(entryA.getKey(),entryA.getValue());

                    }
                    index++;
                }
            }


            int maxLength = 0 ;

            ArrayList<FieldAndSizeTitle> filedAndSizelist = new   ArrayList<FieldAndSizeTitle>();
            Map.Entry<Field, Integer> fixedEntry;
            if (curMaxSize != null) {
                Iterator iterator = fixed_MaxSizeMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    fixedEntry = (Map.Entry<Field, Integer>) iterator.next();
                    //Map的Value
//                    System.out.println(index+" 已修正  Field = "+ fixedEntry.getKey().getName());
//                    System.out.println(index+ "已未修正 Value = "+ fixedEntry.getValue());
                  //  System.out.println(" 当前Filed序列: " +fixedEntry.getKey().getName());
                    FieldAndSizeTitle FieldAndSizeTitleItem =  new FieldAndSizeTitle(fixedEntry.getKey() ,  fixedEntry.getValue() ,titleFieldMap.get(fixedEntry.getKey()));
                    filedAndSizelist.add(FieldAndSizeTitleItem);
                    maxLength = maxLength  + FieldAndSizeTitleItem.size;
                }
            }


            int inputTitleSize = getPaddingChineseLength(titlevalue);
            int halfLength = maxLength/2 - inputTitleSize/2  ;
            String headBlankStr = buildStringRepeat("=",halfLength);
            String titlefixed = headBlankStr + "【"+titlevalue+"】"+headBlankStr;
            System.out.println(titlefixed);


            ArrayList<String> tableStrList = new  ArrayList<String>();
            for (int i = 0; i < showItem.length; i++) {

                 Object itemX = showItem[i];

                 String curContentLine = "";
                 String spaceLine = "";
                 if(i == 0 ){   //   添加 表头
                     String firstLine = "";
                     String titleLine = "";
                     String titleSpaceLine = "";
                     for (int j = 0; j < filedAndSizelist.size(); j++) {
                         String headTitle = filedAndSizelist.get(j).title;
                         Field curField = filedAndSizelist.get(j).field;
                         int MaxSize = filedAndSizelist.get(j).size;
                       //  MaxSize = MaxSize + 1;   //  留一个空格位置
                         int rawSize = getPaddingChineseLength(headTitle); //  标题的大小
                         int distance = MaxSize - rawSize ;  // 后面空格的大小
                         String blankStr = buildStringRepeat(" ",distance);
                         String rawItem = CONTENT_SPACE + headTitle+blankStr;

                         rawItem = rawItem.replace(headTitle+" "," "+headTitle);
                         titleLine = titleLine + rawItem;

                         String beginSpace = "";
                         if(j == 0){
                             beginSpace = ROW_LINE_PRE + buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) + buildStringRepeat(ROW_LINE_Content_Cell,1);

                         }else if( j == filedAndSizelist.size() -1){

                             beginSpace = buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) + ROW_LINE_END;

                         }else{
                             beginSpace = buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) +  buildStringRepeat(ROW_LINE_Content_Cell,1);
                         }



                         titleSpaceLine = titleSpaceLine + beginSpace;
                     }

                     firstLine = titleSpaceLine;
                     firstLine = firstLine.replace(ROW_LINE_PRE,TOP_LINE_PRE);
                     firstLine = firstLine.replace(ROW_LINE_Content_Cell,TTOP_LINE_MIDDLE);
                     firstLine = firstLine.replace(ROW_LINE_END,TOP_LINE_END);
                     tableStrList.add(firstLine);
                     tableStrList.add(titleLine+CONTENT_SPACE);

                     tableStrList.add(titleSpaceLine);
                 }


                for (int j = 0; j < filedAndSizelist.size(); j++) {
                    Field curField = filedAndSizelist.get(j).field;
                    int MaxSize = filedAndSizelist.get(j).size;
                  //  MaxSize = MaxSize + 1;   //  留一个空格位置
                    try {
                  String curTableItem =  ""+getFieldValue(itemX,curField.getName());
                  int rawSize = getPaddingChineseLength(curTableItem); //  数值的大小

                        while(rawSize > DEFAULT_CONTENT_PADDING_SIZE_MAX){
                            curTableItem = curTableItem.substring(0,DEFAULT_CONTENT_PADDING_SIZE_MAX-1);
                            rawSize = getPaddingChineseLength(curTableItem);
                        }
                  int distance = MaxSize - rawSize ;  // 后面空格的大小

                  String blankStr = buildStringRepeat(" ",distance);
                  String rawItem = CONTENT_SPACE + curTableItem+blankStr;




                        String beginSpace = "";
                        if(j == 0){
                            beginSpace = ROW_LINE_PRE + buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) + buildStringRepeat(ROW_LINE_Content_Cell,1);

                        }else if( j == filedAndSizelist.size() -1){

                            beginSpace = buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) + ROW_LINE_END;

                        }else{

                            beginSpace = buildStringRepeat(ROW_LINE_MIDDLE,MaxSize) +  buildStringRepeat(ROW_LINE_Content_Cell,1);
                        }
//                        System.out.println();
//                        System.out.println(" rawItem1 = "+ rawItem  );
                        rawItem = rawItem.replace(curTableItem+" "," "+curTableItem);
//                        System.out.println( "    curTableItem ="+ curTableItem);

//                        System.out.println(" distance = "+ distance   + "    curTableItem ="+ curTableItem);
//                        System.out.println(" maxSize = "+ MaxSize   );
//                        System.out.println(" rawSize = "+ rawSize  );
//                        System.out.println(" rawItem2 = "+ rawItem  );
                       // System.out.println();
                        curContentLine = curContentLine + rawItem;
                        spaceLine = spaceLine + beginSpace;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }


                }

                if(i == showItem.length -1){
                    spaceLine = spaceLine.replace(ROW_LINE_PRE,BUTTOM_LINE_PRE);

                    spaceLine = spaceLine.replace(ROW_LINE_Content_Cell,BUTTOM_LINE_MIDDLE);
                    spaceLine = spaceLine.replace(ROW_LINE_END,BUTTOM_LINE_END);
                }
                tableStrList.add(curContentLine+CONTENT_SPACE);
                tableStrList.add(spaceLine);

            }
            ProintArrayLog(tableStrList, titlevalue);
        }
    }

    public static void ProintArrayLog(ArrayList<String> showItem , String title) {
//        System.out.println("======================================="+title+"=======================================");
        for (int i = 0; i < showItem.size(); i++) {
            System.out.println(showItem.get(i));
        }
    }

    static String buildStringRepeat(String src , int count){
        String result = "";
        for (int i = 0; i < count; i++) {
            result=src+result;
        }
        return result;
    }


    public static void getCurrentFiledValue() {


    }

    static class FieldAndSizeTitle{
        Field field;
        int size;
        String title;

        FieldAndSizeTitle(Field mField , int mSize , String mtitle){
            this.field = mField;

            this.size = mSize + 2;  // 默认需要   一前  一后 两个空格

            this.title = mtitle;
        }
     }








    static   ArrayList<Field>  tableFieldList = new ArrayList<Field>();
    static   ArrayList<String> tableTitleList = new ArrayList<String>();
    static   Map<Field,String> tablefieldTitleMap = new LinkedHashMap<Field,String>();
    static   Map<String,Field> tableTitlefieldMap = new LinkedHashMap<String,Field>();

    @SuppressWarnings("unchecked")
    static  void InitTableHead(Object targetObject , Map<String,String> arrAndTitle) {
        clearTableValue();
        Map<Field, String> headMap = new HashMap<Field, String>();
        Map.Entry<String, String> entry;
        if(arrAndTitle != null){
            Iterator iterator = arrAndTitle.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                try {
                    headMap.put(targetObject.getClass().getDeclaredField(entry.getKey()), entry.getValue());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            getFieldAndHeadTitle(headMap);
        }
    }

    static void clearTableValue(){
        tableFieldList.clear();
        tableTitleList.clear();
        tablefieldTitleMap.clear();
        tableTitlefieldMap.clear();
    }

    @SuppressWarnings("unchecked")
    static  void getFieldAndHeadTitle(Map<Field, String> map) {
        Map.Entry<Field, String> entry;
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<Field, String>) iterator.next();
                ;  //Map的Value
                tableFieldList.add(entry.getKey());
                ;  //Map的Value
                tableTitleList.add(entry.getValue());
                tablefieldTitleMap.put(entry.getKey() , entry.getValue());
                tableTitlefieldMap.put(entry.getValue(),entry.getKey());
            }
        }
    }

    @SuppressWarnings("unchecked")
    static  void PrintStringMap(Map<String, String> map) {
        int mapIndex = 0;
        Map.Entry<String, String> entry;
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                System.out.println("索引:"+ mapIndex + "   Key:"+entry.getKey() + "     Value:"+entry.getValue());
                mapIndex++;
            }
        }
    }


    @SuppressWarnings("unchecked")
    static  void PrintField2IntMap( Map<Field, Integer> map) {
        int mapIndex = 0;
        Map.Entry<Field, Integer> entry;
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<Field, Integer>) iterator.next();
                System.out.println("Field索引:"+ mapIndex + "   Key:"+entry.getKey().getName() + "     Value:"+entry.getValue());
                mapIndex++;
            }
        }
    }

    /**
         * 获取当前类声明的private/protected变量
         */
        static public Object getFieldValue(Object object, String propertyName)
                throws IllegalAccessException, NoSuchFieldException {
            Field field = object.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);

            return field.get(object);
        }

        /**
         * @param object
         * @param propertyName
         * @return
         * @throws IllegalAccessException
         * @throws NoSuchFieldException
         */
        public static Object getFieldValueInAllSuper(Object object, String propertyName)
                throws IllegalAccessException, NoSuchFieldException {
            Class claszz = object.getClass();
            Field field = null;

            do {
                try {
                    field = claszz.getDeclaredField(propertyName);
                } catch (NoSuchFieldException e) {
                    //e.printStackTrace();
                    field = null;
                }
                claszz = claszz.getSuperclass();
            }
            while (field == null && claszz != null);

            if (field == null) return null;

            field.setAccessible(true);
            return field.get(object);
        }






    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

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

    static String TOP_LINE_PRE = "╔";  //  最顶层表头
    static String TTOP_LINE_MIDDLE = "╦";
    static String TOP_LINE_END = "╗";


    static String BUTTOM_LINE_PRE = "╚";
    static String BUTTOM_LINE_MIDDLE = "╩";
    static String BUTTOM_LINE_END = "╝";


    static String ROW_LINE_PRE = "╠";
    static String ROW_LINE_MIDDLE = "═";
    static String ROW_LINE_Content_Cell = "╬";
    static String ROW_LINE_END = "╣";


    static String CONTENT_SPACE = "║";

    // 如果长度没有超过默认的长度  那么名字的padding-length 默认为 8
    static int DEFAULT_CONTENT_PADDING_SIZE_MIN = 8;  // 最小单元
    static int DEFAULT_CONTENT_PADDING_SIZE_MAX = 50; // 最大党员


    //  TablePrint=======================End
}