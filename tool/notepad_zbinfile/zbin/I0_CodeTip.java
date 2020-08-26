import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class I0_CodeTip {

    static ArrayList<Category> Category_List = new ArrayList<Category>();

    static {// 【 String (Category) static block 】   // Category 主要添加的内容   创作指导地方  布置作业的地方
        Category StringCategory_1 = new Category("String");
        // String_1 ->  获取 subString(begin,end) 子字符串方法
        StringCategory_1.put(1, "String subString(int begin,int end) 获取子字符串方法");

        // String_2 ->   boolean isNumStr(String str) 判断当前字符串参数是不是数字组成的字符串
        StringCategory_1.put(2, "boolean isNumStr(String str) 判断当前字符串参数是不是数字组成的字符串");

        // String_3 ->  boolean isZiMuStr(String str) 判断当前字符串参数是不是a-z A-Z 组成的字符串
        StringCategory_1.put(3, "boolean isZiMuStr(String str) 判断当前字符串参数是不是数字组成的字符串");

        // String_4 ->  boolean isContainChinese(String str) 判断当前字符串参数是不是包含有汉字字符
        StringCategory_1.put(4, "boolean isContainChinese(String str) 判断当前字符串参数是不是包含有汉字字符");

        // String_5 ->  String getTimeStamp() 获取当前时间戳字符串 格式为 yyyyMMdd_HHmmss
        StringCategory_1.put(5, "String getTimeStamp() 获取当前时间戳字符串 格式为 yyyyMMdd_HHmmss ");


        // String_6 ->  String getTimeStamp() 获取当前时间戳字符串 格式为 yyyyMMdd_HHmmss
        StringCategory_1.put(6, "String  getPaddingString(String oriStr , int padinglength , String oneStr , boolean dirPre) 填充原始字符串 ");



        Category_List.add(StringCategory_1);

    }

    static{     // 【 Map (Category) static block 】

        Category MapCategory = new Category("Map");
        // Map_1 ->  迭代Map的Key和Value(通过 Iterator)
        MapCategory.put(1, "void getKeyAndValue(Map<String,String> mMapParam) 迭代Map的Key和Value(通过 Iterator) ");
        Category_List.add(MapCategory);


    }

    static{     // 【 File (Category) static block 】

        Category FileCategory = new Category("File");
        // Map_1 ->  迭代Map的Key和Value(通过 Iterator)
        FileCategory.put(1, " String ReadFileContent( File mFilePath ) 读取文件内容转为字符串String ");
        FileCategory.put(2, "void writeContentToFile(File file, String strParam) 把字符串写入指定文件 ");
        FileCategory.put(3, "void fileCopy(File origin, File target) 把源文件复制到目标文件");
        FileCategory.put(4, "ArrayList<File> getAllSubDirFile(File  rootPath)获取子文件夹  && ArrayList<File> getAllSubRealFile(File  rootPath)  获取子文件");
        FileCategory.put(5, "Long getFileCreateTime(File fileitem) 获取文件创建的时间戳long值");

        //void fileCopy(File origin, File target)
        Category_List.add(FileCategory);


    }

    // 各个program语言  和  它对应的实现的 方法的列表
    // 方法实现固定为   String  Category_Index()  ,
    //  Category为方法的操作类型  Index为方法序号  返回值String 是对这个  方法的描述
    TreeMap<CodeTip, ArrayList<Method>> mProgram_Method_Map;


    Map<String, Map<String, String>> mCategory_Map;
    // programs , category_index, methodDesc
    // categoty_index 就能找对 各种 C java 等实现的 method 列表
    // programs  categoty_index 就能唯一确定一个  method


    public static void main(String[] args) {

        I0_CodeTip Code_Tip_Object = new I0_CodeTip();
        Code_Tip_Object.mProgram_Method_Map = new TreeMap<CodeTip, ArrayList<Method>>();
        Code_Tip_Object.mCategory_Map = new HashMap<String, Map<String, String>>();
        try {

            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Java(), "I0_CodeTip$Tip_Java");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Python(), "I0_CodeTip$Tip_C");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_C(), "I0_CodeTip$Tip_C");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Cpp(), "I0_CodeTip$Tip_Java");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Bat(), "I0_CodeTip$Tip_C");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Sh(), "I0_CodeTip$Tip_Java");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Js(), "I0_CodeTip$Tip_C");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Swift(), "I0_CodeTip$Tip_Java");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Flutter(), "I0_CodeTip$Tip_C");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Go(), "I0_CodeTip$Tip_Java");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Kotlin(), "I0_CodeTip$Tip_C");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Ruby(), "I0_CodeTip$Tip_C");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_CX(), "I0_CodeTip$Tip_C");
            Code_Tip_Object.TryAddDeclareMethod_InitMap(Code_Tip_Object.new Tip_Scala(), "I0_CodeTip$Tip_C");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        HashMap<Object,ArrayList<Method>> allProgramMethodMap = (HashMap)Code_Tip_Object.mProgram_Method_Map;


        for (int i = 0; i < Category_List.size(); i++) {
            System.out.println();
            System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓"+" Category: "+ Category_List.get(i).categoryName+" Begin "+"〓〓〓〓〓〓〓〓〓〓〓〓");
            Code_Tip_Object.showObjectAndMethod_Map(Category_List.get(i).categoryName.toLowerCase());
            System.out.println();
            System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓"+" Category: "+ Category_List.get(i).categoryName+" End "+"〓〓〓〓〓〓〓〓〓〓〓〓");

        }




        showObjectAndMethod_Map(Code_Tip_Object.mProgram_Method_Map);
//        System.out.println("Game Over!");
        ShowCategoryInfo();

    }


    static class Category {
        String categoryName;  // String
        Map<String, String> categoryIndex_Desc_Map;   // String_1  Desc1    //String_2 Desc2    对应方法名就叫 String1 String2

        Category(String mCategoryName) {
            categoryName = mCategoryName;
            categoryIndex_Desc_Map = new HashMap<String, String>();
        }

        void put(int index, String desc) {
            String mapKey = categoryName +"_"+ index;
            categoryIndex_Desc_Map.put(mapKey, desc);

        }


    }

    @SuppressWarnings("unchecked")
    void TryAddDeclareMethod_InitMap(CodeTip realObject, String className) throws ClassNotFoundException {
        if (mProgram_Method_Map == null) {
            mProgram_Method_Map = new TreeMap<CodeTip, ArrayList<Method>>(new Comparator<CodeTip>() {
                @Override
                public int compare(CodeTip o1, CodeTip o2) {
                    return o1.mProgramName.compareTo(o2.mProgramName);
                }
            });

        }
        if (mCategory_Map == null) {
            mCategory_Map = new HashMap<String, Map<String, String>>();
        }
        HashMap  curMethodIndex_Desc_Map = null ;
       if(mCategory_Map.get(realObject.mProgramName) == null){
             curMethodIndex_Desc_Map =    new HashMap<String,String>();
           mCategory_Map.put(realObject.mProgramName,curMethodIndex_Desc_Map);
       }
        curMethodIndex_Desc_Map = (HashMap)mCategory_Map.get(realObject.mProgramName);
        for (int i = 0; i <Category_List.size() ; i++) {
            Category category = Category_List.get(i);
            curMethodIndex_Desc_Map.putAll(category.categoryIndex_Desc_Map);
        }


        Class mClass_Java = Class.forName(className);

        Method[] methodList = realObject.getClass().getDeclaredMethods();
        ArrayList<Method> methodArrayList = new ArrayList<Method>();
        for (int i = 0; i < methodList.length; i++) {
            Method methodItem = methodList[i];
            methodArrayList.add(methodItem);
        }
        mProgram_Method_Map.put(realObject, methodArrayList);


    }


// 1.   Method getMethod("Category一级分类",Index)   拿到对应的实现类的实现方法
// 2.   method.invoke()   调用它

    // ## 1:java
// ## 2:python
// ## 3:c
// ## 4:cpp
// ## 5:bat
// ## 6:sh
// ## 7:js
// ## 8:swift
// ## 9:flutter
// ## 10:go
// ## 11:kotlin
// ## 12:ruby
// ## 13:c#
// ## 14:scala
    class Tip_Java extends CommonTip {  // 【  ## 1:java】
        Tip_Java() {
            mProgramName = "java";
            mRunCodeSite = "https://tool.lu/coderunner/";
        }


  // ═════════════ String Category Begin ═════════════

        String String_1() {
            System.out.println("    static String subString(String valueStr,int begin){\n" +
                    "       return subString(valueStr,begin,valueStr!=null?valueStr.length():0);\n" +
                    "    }\n" +
                    "\n" +
                    "    static String subString(String valueStr,int begin , int end){\n" +
                    "        String subString = null;\n" +
                    "        if(valueStr == null || begin < 0 || end < 0 || begin > valueStr.length() || end > valueStr.length()){\n" +
                    "            return subString;\n" +
                    "        }\n" +
                    "        subString = valueStr.substring(begin,end);\n" +
                    "        return subString;\n" +
                    "    }\n");
            return  "  String subString(String valueStr,int begin , int end) 获取子字符串 1.指定起始与结束位置    2.只指定起始位置(默认截取到最后)";
        }


        String String_2() {
            System.out.println("    public static boolean isNumStr(String str){\n" +
                    "        if(str == null){\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "        return str.matches(\"^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$\");\n" +
                    "    }");

            return "boolean isNumStr(String str) 判断当前字符串参数是不是数字组成的字符串";
        }

        String String_3() {
            System.out.println("    public static boolean isZiMuStr(String str){\n" +
                    "        if(str == null){\n" +
                    "            return false;\n" +
                    "        }\n" +
                    "        return str.matches(\"^[a-z,A-Z].*$\");\n" +
                    "    }");

            return "boolean isZiMuStr(String str) 判断当前字符串参数是不是数字组成的字符串";
        }


        String String_4() {
            System.out.println("    public static boolean isContainChinese(String str) {\n" +
                    "        if(str == null){\n" +
                    "            return  false;\n" +
                    "        }\n" +
                    "        Pattern p = Pattern.compile(\"[\\u4e00-\\u9fa5]\");\n" +
                    "        Matcher m = p.matcher(str);\n" +
                    "        if (m.find()) {\n" +
                    "            return true;\n" +
                    "        }\n" +
                    "        return false;\n" +
                    "    }");

            return "boolean isContainChinese(String str) 判断当前字符串参数是不是包含有汉字字符";
        }

        String String_5() {
            System.out.println("    static String getTimeStamp(){\n" +
                    "        SimpleDateFormat df = new SimpleDateFormat(\"yyyyMMdd_HHmmss\");//设置日期格式\n" +
                    "        String date = df.format(new Date());\n" +
                    "        return date;\n" +
                    "    }\n" +
                    "\n" +
                    "    static String getTimeStampLong(){\n" +
                    "        SimpleDateFormat df = new SimpleDateFormat(\"yyyyMMdd_HHmmss_SSS\");//设置日期格式\n" +
                    "        String date = df.format(new Date());\n" +
                    "        return date;\n" +
                    "    }");

            return "String getTimeStamp() 获取当前时间戳字符串 格式为 yyyyMMdd_HHmmss";
        }


        String String_6() {
            System.out.println("    static  String  getPaddingIntString(int index , int padinglength , String oneStr , boolean dirPre){\n" +
                    "        String result = \"\"+index;\n" +
                    "        int length = (\"\"+index).length();\n" +
                    "        if(length < padinglength){\n" +
                    "            int distance = padinglength  - length;\n" +
                    "            for (int i = 0; i < distance; i++) {\n" +
                    "                if(dirPre){\n" +
                    "                    result = oneStr+result;\n" +
                    "                }else{\n" +
                    "                    result = result + oneStr;\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return result;\n" +
                    "    }");

            return "String  getPaddingString(String oriStr , int padinglength , String oneStr , boolean dirPre) 填充原始字符串";
        }


/*
        // String_1 ->  获取 subString(begin,end) 子字符串方法
        StringCategory_1.put(1, "获取 String subString(int begin,int end) 子字符串方法");

        // String_2 ->   boolean isNumStr(String str) 判断当前字符串参数是不是数字组成的字符串
        StringCategory_1.put(2, "boolean isNumStr(String str) 判断当前字符串参数是不是数字组成的字符串");

        // String_3 ->  boolean isZiMuStr(String str) 判断当前字符串参数是不是a-z A-Z 组成的字符串
        StringCategory_1.put(3, "boolean isZiMuStr(String str) 判断当前字符串参数是不是数字组成的字符串");

        // String_4 ->  boolean isContainChinese(String str) 判断当前字符串参数是不是包含有汉字字符
        StringCategory_1.put(4, "boolean isContainChinese(String str) 判断当前字符串参数是不是包含有汉字字符");

        // String_5 ->  String getTimeStamp() 获取当前时间戳字符串 格式为 yyyyMMdd_HHmmss
        StringCategory_1.put(5, "String getTimeStamp() 获取当前时间戳字符串 格式为 yyyyMMdd_HHmmss ");

        */

 // ═════════════ String Category End  ═════════════




// ═════════════ MAP Category Begin ═════════════


        String Map_1() {
            System.out.println("    @SuppressWarnings(\"unchecked\")\n" +
                    "    public static void getKeyAndValue(Map<String,String> mMapParam){\n" +
                    "        Map.Entry<String , String> entryItem;\n" +
                    "        if(mMapParam != null){\n" +
                    "            Iterator iterator = mMapParam.entrySet().iterator();\n" +
                    "            while( iterator.hasNext() ){\n" +
                    "                entryItem = (Map.Entry<String , String>) iterator.next();\n" +
                    "                entryItem.getKey();   //Map的Key\n" +
                    "                entryItem.getValue();  //Map的Value\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }");
            System.out.println();
            System.out.println();

            System.out.println("    @SuppressWarnings(\"unchecked\")\n" +
                    "    public static void getKeyAndValue(Map<Integer,ArrayList<Integer>> mMapParam){\n" +
                    "        Map.Entry<Integer , ArrayList<Integer>> entryItem;\n" +
                    "        if(mMapParam != null){\n" +
                    "            Iterator iterator = mMapParam.entrySet().iterator();\n" +
                    "            while( iterator.hasNext() ){\n" +
                    "                entryItem = (Map.Entry<Integer , ArrayList<Integer>>) iterator.next();\n" +
                    "                Integer year =   entryItem.getKey();   //Map的Key\n" +
                    "                ArrayList<Integer> year_day =  entryItem.getValue();  //Map的Value\n" +
                    "                System.out.println(\"==========\"+year + \"  =========\");\n" +
                    "                for (int i = 0; i <year_day.size() ; i++) {\n" +
                    "                    System.out.println(\"key = \"+year + \"  value[\"+i+\"] = \"+ year_day.get(i) );\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }");


            return "void getKeyAndValue(Map<String,String> mMapParam) 迭代Map的Key和Value(通过 Iterator)";
        }



        // ═════════════ MAP Category End  ═════════════


        // ═════════════ File Category Begin ═════════════


        String File_1() {
            System.out.println("    public static String ReadFileContent( File mFilePath) {\n" +
                    "        if (mFilePath == null  || !mFilePath.exists()) {\n" +
                    "            System.out.println(\"不存在 当前文件 \"+ mFilePath.getAbsolutePath() );\n" +
                    "            return null;\n" +
                    "        }\n" +
                    "        StringBuilder sb= new StringBuilder();\n" +
                    "        try {\n" +
                    "            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), \"utf-8\"));\n" +
                    "            String oldOneLine = \"\";\n" +
                    "            int index = 1;\n" +
                    "            while (oldOneLine != null) {\n" +
                    "                oldOneLine = curBR.readLine();\n" +
                    "                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {\n" +
                    "                    continue;\n" +
                    "                }\n" +
                    "                sb.append(oldOneLine+\"\\n\");\n" +
                    "                index++;\n" +
                    "            }\n" +
                    "            curBR.close();\n" +
                    "        } catch (Exception e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "        return sb.toString();\n" +
                    "    }");

            return "String ReadFileContent( File mFilePath ) 读取文件内容转为字符串String ";
        }



        String File_2() {
            System.out.println("    static void writeContentToFile(File file, String strParam) {\n" +
                    "        try {\n" +
                    "            if (file != null && !file.exists()) {\n" +
                    "                file.createNewFile();\n" +
                    "            }\n" +
                    "            if (file != null && file.exists()) {\n" +
                    "                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), \"utf-8\"));\n" +
                    "                curBW.write(strParam);\n" +
                    "                curBW.flush();\n" +
                    "                curBW.close();\n" +
                    "            } else {\n" +
                    "                System.out.println(\"write out File  Failed !    File = \" + file.getAbsolutePath());\n" +
                    "            }\n" +
                    "        } catch (IOException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "    }");

            return "void writeContentToFile(File file, String strParam) 把字符串写入指定文件 ";
        }


        String File_3() {
            System.out.println("    public static void fileCopy(File origin, File target) {\n" +
                    "        InputStream input = null;\n" +
                    "        OutputStream output = null;\n" +
                    "        int lengthSize;\n" +
                    "        try {\n" +
                    "            input = new FileInputStream(origin);      // 创建输入输出流对象\n" +
                    "            output = new FileOutputStream(target);\n" +
                    "            try {\n" +
                    "                lengthSize = input.available();  // 获取文件长度\n" +
                    "                byte[] buffer = new byte[lengthSize];        // 创建缓存区域\n" +
                    "                input.read(buffer);      // 将文件中的数据写入缓存数组\n" +
                    "                output.write(buffer);      // 将缓存数组中的数据输出到文件\n" +
                    "            } catch (IOException e) {\n" +
                    "                e.printStackTrace();\n" +
                    "            }\n" +
                    "        } catch (Exception e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        } finally {\n" +
                    "            if (input != null && output != null) {\n" +
                    "                try {\n" +
                    "                    input.close(); // 关闭流\n" +
                    "                    output.close();\n" +
                    "                } catch (IOException e) {\n" +
                    "                    e.printStackTrace();\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }");

            return "void fileCopy(File origin, File target) 把源文件复制到目标文件 ";
        }



        String File_4() {
            System.out.println("    // 获取所有子文件夹目录的方法\n" +
                    "    static ArrayList<File> getAllSubDirFile(File  rootPath) {\n" +
                    "        ArrayList<File> allDirFile = new ArrayList<File>();\n" +
                    "        Path curRootPath = Paths.get(rootPath.getAbsolutePath() + File.separator );\n" +
                    "        try {\n" +
                    "            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {\n" +
                    "                @Override\n" +
                    "                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {\n" +
                    "                    allDirFile.add(dir.toFile());\n" +
                    "                    return super.postVisitDirectory(dir, exc);\n" +
                    "                }\n" +
                    "            });\n" +
                    "        } catch (IOException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "        return allDirFile;\n" +
                    "    }");


            System.out.println("    // 获取所有子文件夹下实体文件目录的方法\n" +
                    "    static ArrayList<File> getAllSubRealFile(File  rootPath) {\n" +
                    "        ArrayList<File> allSubFile = new ArrayList<File>();\n" +
                    "        Path curRootPath = Paths.get(rootPath.getAbsolutePath() + File.separator );\n" +
                    "        try {\n" +
                    "            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {\n" +
                    "                @Override\n" +
                    "                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {\n" +
                    "                    String fileString = file.toAbsolutePath().toString();\n" +
                    "                    File curFile =    new File(fileString);\n" +
                    "                    if(curFile.exists() && curFile.isFile()){\n" +
                    "                        allSubFile.add(curFile);\n" +
                    "                    }\n" +
                    "                    return  FileVisitResult.CONTINUE;\n" +
                    "                }\n" +
                    "            });\n" +
                    "        } catch (IOException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "        return allSubFile;\n" +
                    "    }");

            return "ArrayList<File> getAllSubDirFile(File  rootPath)获取所有子文件夹 同  getAllSubRealFile 获取所有子文件 ";
        }


        String File_5() {
            System.out.println("    static Long getFileCreateTime(File fileItem) {\n" +
                    "        if (fileItem == null || !fileItem.exists()) {\n" +
                    "            return 0L;\n" +
                    "        }\n" +
                    "        try {\n" +
                    "            Path path = Paths.get(fileItem.getAbsolutePath());\n" +
                    "            BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);\n" +
                    "            BasicFileAttributes attr = basicview.readAttributes();\n" +
                    "            return attr.creationTime().toMillis();\n" +
                    "        } catch (Exception e) {\n" +
                    "            e.printStackTrace();\n" +
                    "            return fileItem.lastModified();\n" +
                    "        }\n" +
                    "    }");

            return "Long getFileCreateTime(File fileitem) 获取文件创建的时间戳long值 ";
        }


        //


        // ═════════════ File Category End  ═════════════


    }

    class Tip_Python extends CommonTip { // 【  ## 2:python】
        Tip_Python() {
            mProgramName = "python";
            mRunCodeSite = "https://tool.lu/coderunner/";
        }

    }

    class Tip_C extends CommonTip {  // 【  ## 3:c】

        Tip_C() {
            mProgramName = "c";
            mRunCodeSite = "https://tool.lu/coderunner/";
        }
    }

    class Tip_Cpp extends CommonTip {  // 【  ## 4:cpp】
        Tip_Cpp() {
            mProgramName = "cpp";
            mRunCodeSite = "https://tool.lu/coderunner/";
        }


    }

    class Tip_Bat extends CommonTip {  // 【  ## 5:bat】
        Tip_Bat() {
            mProgramName = "bat";
            mRunCodeSite = " Windows 本地 Cmder ";
        }

    }


    class Tip_Sh extends CommonTip {  // 【  ## 6:sh】
        Tip_Sh() {
            mProgramName = "sh";
            mRunCodeSite = " Linux 本地 zsh ";
        }

    }


    class Tip_Js extends CommonTip {  // 【  ## 7:js】
        Tip_Js() {
            mProgramName = "js";
            mRunCodeSite = "https://c.runoob.com/front-end/61";
        }

    }

    class Tip_Swift extends CommonTip {  // 【  ## 8:swift】
        Tip_Swift() {
            mProgramName = "swift";
            mRunCodeSite = "https://c.runoob.com/compile/20";
        }

    }

    class Tip_Flutter extends CommonTip {  // 【  ## 9:flutter】

        Tip_Flutter() {
            mProgramName = "flutter";
            mRunCodeSite = "http://dart.jsrun.net/";
        }
    }


    class Tip_Go extends CommonTip {  // 【  ## 10:go】
        Tip_Go() {
            mProgramName = "go";
            mRunCodeSite = "https://c.runoob.com/compile/21";
        }

    }


    class Tip_Kotlin extends CommonTip {  // 【  ## 11:kotlin】
        Tip_Kotlin() {
            mProgramName = "kotlin";
            mRunCodeSite = "https://c.runoob.com/compile/2960";
        }

    }


    class Tip_Ruby extends CommonTip {  // 【  ## 12:ruby】
        Tip_Ruby() {
            mProgramName = "ruby";
            mRunCodeSite = "https://c.runoob.com/compile/13";
        }
    }

    class Tip_CX extends CommonTip {  // 【  ## 13:c#】
        Tip_CX() {
            mProgramName = "c#";
            mRunCodeSite = "https://c.runoob.com/compile/14";
        }

    }

    class Tip_Scala extends CommonTip {  // 【  ## 14:scala】

        Tip_Scala() {
            mProgramName = "scala";
            mRunCodeSite = "https://c.runoob.com/compile/15";
        }


    }


    class CommonTip extends CodeTip {


        @Override
        void abs_method_exist4forture() {

        }

        @Override
        public int compareTo(Object o) {
            return this.mProgramName.compareTo(o+"");
        }
    }


    abstract class CodeTip implements Comparable {
        ArrayList<Method> descMethodList;
        //        Object readObject;   // 具体的调用示例方法的那个 Object
        String mRunCodeSite;
        String mProgramName;


        ArrayList<Method> method_Category(String categoryStr) {
            ArrayList<Method> curCategoryMethodList = new ArrayList<Method>();
            String methodFlag = "_" + categoryStr + "_";
            if (categoryStr == null || "".equals(categoryStr.trim())) {
                methodFlag = "_";   // 只要带下划线  就返回
            } else {
                methodFlag = "_" + categoryStr + "_";
            }


            for (int i = 0; i < descMethodList.size(); i++) {
                Method method = descMethodList.get(i);
                String methodName = method.getName();
                if (methodName.contains(methodFlag)) {
//                    System.out.println("method["+i+"] = "+method.getName());
                    curCategoryMethodList.add(method);
                }
            }

            return curCategoryMethodList;
        }


        Method method_Category_Index(String categoryStr, int index) {
            String methodFlag = "_" + categoryStr + "_" + index;
            for (int i = 0; i < descMethodList.size(); i++) {
                Method method = descMethodList.get(i);
                String methodName = method.getName();
                if (methodName.contains(methodFlag)) {
                    return method;
                }
            }
            return null;
        }

        void showMethod(String category) {
            ArrayList<Method> method_Category = method_Category(category);
            for (int i = 0; i < method_Category.size(); i++) {
                Method method = method_Category.get(i);
                System.out.println(this.getClass().getName() + "Methods  = Method[" + i + "] = " + method.getName());
            }
        }


        CodeTip() {
//            System.out.println("════════════════════════════");
            descMethodList = new ArrayList<Method>();
            Method[] methods = this.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
//                System.out.println("CodeTip->methid["+i+"]" +" = "+ methods[i].getName());
                descMethodList.add(methods[i]);
            }
//            System.out.println("════════════════════════════");
        }


        abstract void abs_method_exist4forture();  //  保留 等待以后  添加点 什么 以后


    }


    public static void ShowCategoryInfo() {
        System.out.println();
        System.out.println("══════════════" + "↓↓↓↓↓↓↓↓↓↓  实现类型描述 Beg  ↓↓↓↓↓↓↓↓↓↓ "+"══════════════");
        for (int i = 0; i < Category_List.size(); i++) {
            ArrayList<String> logArr = new   ArrayList<String>();
            Category citem = Category_List.get(i);
            String  cname = citem.categoryName;
            Set<String> keySet =  citem.categoryIndex_Desc_Map.keySet();
            ArrayList<String> keySortList = new  ArrayList<String>();
            keySortList.addAll(keySet);
            keySortList.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                 return   o1.compareTo(o2);
                }
            });

            for (int j = 0; j < keySortList.size(); j++) {
                String keyItem = keySortList.get(j);
                String cDesc = citem.categoryIndex_Desc_Map.get(keyItem);
                logArr.add("String "+keyItem+"()"+" : "+ cDesc) ;
            }
            ArrayPrint(logArr,cname);

        }
        System.out.println("══════════════" + "↑↑↑↑↑↑↑↑↑↑  实现类型描述 End  ↑↑↑↑↑↑↑↑↑↑ "+"══════════════");

    }

    @SuppressWarnings("unchecked")
    public void showObjectAndMethod_Map(String Category) {
        String methodFlag =   Category + "_";
        Map.Entry<Object, ArrayList<Method>> entry;
        int index = 0;

        if (mProgram_Method_Map != null) {
            Iterator iterator = mProgram_Method_Map.entrySet().iterator();


            while (iterator.hasNext()) {
                entry = (Map.Entry<Object, ArrayList<Method>>) iterator.next();
                Object obj = entry.getKey();  //Map的Value
                ArrayList<Method> methodList = entry.getValue();  //Map的Value

                ArrayList<Method> sortedMethodList = new ArrayList<Method>();
                sortedMethodList.addAll(methodList);
                sortedMethodList.sort(new Comparator<Method>() {
                    @Override
                    public int compare(Method o1, Method o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                System.out.println("══════════════" + "Object[" + index + "] = " + obj.getClass().toString() + "══════════════");

                int methodIndex = 1;
                for (int i = 0; i < sortedMethodList.size(); i++) {
                    Method method = sortedMethodList.get(i);
                    String methodName = method.getName().toLowerCase();
                    if (methodName.contains(methodFlag)) {
                        System.out.println("###"+ "  method[" + methodIndex + "] = " + method.getName());
                        methodIndex++;
                        try {
                            System.out.println("```\n");
                            System.out.println();
                            String methodResult = method.invoke(obj) + "";
                            String zhushiStr = zhushiMap.get(Category.toLowerCase().trim()) == null ? "//":zhushiMap.get(Category.toLowerCase().trim());

                            System.out.println(zhushiStr+" 方法描述:" + methodResult + " "+ zhushiStr);
                            System.out.println();
                            System.out.println("```\n");
                            System.out.println("─────────────────────────────────────────");

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }

                }
                index++;

            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void showCategoryMap(Map<String, String> map) {


    }

//  ┌┬┐├┼┤└┴┘┌─┐│┼│└─┘
//┌──────────────────Java───────────────────┐
//│  Object[0] = class I0_CodeTip$Tip_Java  │
//│  method[0] = test_String_1 Desc:        │
//└─────────────────────────────────────────┘
//┌─────────────────C────────────────────┐
//│  Object[1] = class I0_CodeTip$Tip_C  │
//│  method[0] = test_String_2           │
//│  method[1] = test_String_1           │
//└──────────────────────────────────────┘

    @SuppressWarnings("unchecked")
    public static void showObjectAndMethod_Map(Map<CodeTip, ArrayList<Method>> map) {
        System.out.println();
        Map.Entry<CodeTip, ArrayList<Method>> entry;
        int index = 0;
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();

            System.out.println("══════════════" + " ↓↓↓↓↓↓↓↓ 所有记录在案的 Object-Method 列表  Begin ↓↓↓↓↓↓↓↓ " + "══════════════");
            while (iterator.hasNext()) {
                entry = (Map.Entry<CodeTip, ArrayList<Method>>) iterator.next();
                CodeTip mCodeTip = entry.getKey();  //Map的Value
                ArrayList<Method> methodList = entry.getValue();  //Map的Value
                ArrayList<Method> sortedMethodList = new ArrayList<Method>();
                sortedMethodList.addAll(methodList);
                sortedMethodList.sort(new Comparator<Method>() {
                    @Override
                    public int compare(Method o1, Method o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                String curProgramName = mCodeTip.mProgramName;
               ArrayList<String> LogArr =  new ArrayList<String>();
                String className = mCodeTip.getClass().getName();
//                System.out.println("————" + "Object[" + index + "] = " + mCodeTip.getClass().toString());
                LogArr.add("Object[" + index + "] = " + mCodeTip.getClass().getName());
                for (int i = 0; i < sortedMethodList.size(); i++) {
                    Method method = sortedMethodList.get(i);
//                    method.
                    String methodName = method.getName();

//                    for (int j = 0; j < method.getTypeParameters().length; j++) {
//                        System.out.println("参数类型 ["+j+"] = " +  method.getTypeParameters()[j]);
//                    }

                    // 返回值: void      返回值: java.lang.String
//                    System.out.println("返回值: " + method.getReturnType().getName());

                    // method[2] =  method.toString() == java.lang.String I0_CodeTip$Tip_C.test_String_1()
//                    System.out.println("method[" + i + "] = void " + methodName + "() " + getMethodCategoryDesc(methodName));
                    LogArr.add("method[" + (i+1) + "] = String " + methodName + "() " + getMethodCategoryDesc(methodName));
                }
                index++;

                ArrayPrint(LogArr,curProgramName);
                System.out.println();

            }
            System.out.println("══════════════" + "↑↑↑↑↑↑↑↑↑↑ 所有记录在案的 Object-Method 列表  End ↑↑↑↑↑↑↑↑↑↑ " + "══════════════");

        }
    }


    static String getMethodCategoryDesc(String methodName) {
        String desc = "";
        String category = clearNumber(methodName);
        category = category.replace("_","");

        for (int i = 0; i <Category_List.size() ; i++) {
           Category categoryItem =  Category_List.get(i);
           if(categoryItem.categoryName.toLowerCase().trim().equals(category.toLowerCase().trim())){
               desc =  categoryItem.categoryIndex_Desc_Map.get(methodName);
               return desc;
           }
        }


        return desc;

    }

    public static String clearNumber(String str){
        String result = new String(str);
        result = result.replaceAll("0","");
        result = result.replaceAll("1","");
        result = result.replaceAll("2","");
        result = result.replaceAll("3","");
        result = result.replaceAll("4","");
        result = result.replaceAll("5","");
        result = result.replaceAll("6","");
        result = result.replaceAll("7","");
        result = result.replaceAll("8","");
        result = result.replaceAll("9","");
        return result;
    }

    // ArrayPrint ==============================Begin

    static int MAX_COUNT_CHAR_IN_ROW = 160;
    static int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 160;

    static public void printArrObject(Object[] objArr, String title) {
        ArrayList<String> curPropStrArr = new ArrayList<String>();
        for (int i = 0; i < objArr.length; i++) {
            if ("".equals(objArr[i].toString())) {
                continue;
            }
            curPropStrArr.add(objArr[i].toString());
        }
        ArrayPrint(curPropStrArr, title);
    }


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
                if (strArr[i].contains("【") && strArr[i].contains("】")) {
                    subArrA = toSqlitWithhardBlock(strArr[i]);
                } else if (strArr[i].contains(";")) {
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


    public static ArrayList<String> toSqlitWithhardBlock(String mStrList) {
        ArrayList<String> resultList = new ArrayList<String>();
        //【】  【】,
        String mStr = mStrList.trim();

        String pre = mStr.substring(0, mStr.indexOf("【"));
        mStr = mStr.substring(mStr.indexOf("【"));
        resultList.add(pre);
        String end = "";
        if (mStr.endsWith("】")) {
            end = "";
        } else {
            end = mStr.substring(mStr.lastIndexOf("】") + 1);
        }

        mStr = mStr.substring(0, mStr.lastIndexOf("】") + 1);

        while (mStr.contains("】") && mStr.contains("【")) {
            String firstStr = mStr.substring(mStr.indexOf("【"), mStr.indexOf("】") + 1);
            resultList.add(firstStr);
            mStr = mStr.substring(mStr.indexOf("】") + 1);
        }

        if (!"".equals(mStr.trim())) {
            resultList.add(mStr.trim());
        }

        if (!"".equals(end)) {
            resultList.add(end);
        }


//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println("xxx："+i+"  ="+resultList.get(i) +"   mStr="+mStr);
//        }
        return resultList;
    }


    public static ArrayList<String> toMakeListItemLess100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixLengthArr = new ArrayList<String>();

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() < MAX_COUNT_CHAR_IN_ROW) {
                fixLengthArr.add(mStrList.get(i));
            } else {
                String curMaxStr = mStrList.get(i);
                ArrayList<String> fixA = null;
                if (curMaxStr.contains("【") && curMaxStr.contains("】")) {
                    fixA = toSqlitWithhardBlock(curMaxStr);
                } else if (curMaxStr.contains(";")) {
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
        String beginChar = "│ ";
        String endChar = "│";
        String oriStrTrim = oriStr.trim();
        int curLength = oriStrTrim.length();
        int paddingLength = maxLength - curLength;


        int chineseCount = getFrameChineseCount(oriStr);
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

    public static int getFrameChineseCount(String oriStr) {
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
            boolean isChinese = isFrameContainChinese(itemChar + "");
            if (isChinese) {
                count++;
            }
        }
        return count;
    }


    public static boolean isFrameContainChinese(String str) {
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
        int chineseCount = getFrameChineseCount(title);


        String beginRow = "┌──────────────────" + title + "──────────────────┐";
        String endRow = "└──────────────────┘";
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
        String templateString = "┐";
        if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                templateString = "─" + templateString;
            }
        }

        beginRow = beginRow.replace(templateString, "┐");
        //  System.out.println(" fixStrArr.size() =" + fixStrArr.size());
        beginRow = resetBeginRowToDefaultSize(beginRow);
        System.out.println(beginRow);
        for (int i = 0; i < fixStrArr.size(); i++) {
            System.out.println(fixStrArr.get(i));
        }
        endRow = resetEndRowToDefaultSize(endRow);
        System.out.println(endRow);
    }

    static String resetEndRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getFramePaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("─", distance + 3);
        curBeginStr = curBeginStr.replace("┘", paddingString + "┘");
        return curBeginStr;
    }

    public static int getFramePaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getFrameChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    static String getRepeatString(String repeatSrc, int repeatCount) {
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }


    static String resetBeginRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getFramePaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("─", distance + 3);
        curBeginStr = curBeginStr.replace("┐", paddingString + "┐");
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
                if (isFrameContainChinese(curItem)) {
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
            int preLength = getFramePaddingChineseLength(pre); // 中文对齐  取中文长度
            // 需要达到一样的ChineseLength
            // int padding_chinese =  getFrameChineseCount(pre);
            String padding = "";
            if (preLength <= maxMaoPosition) {
                int paddingLength = maxMaoPosition - preLength;
                padding = getPaddingEmptyString(paddingLength);
            }
//            String fixedItem = pre + padding + end;
            String fixedItem = pre  + end;
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
                int paddingSize = getFramePaddingChineseLength(maoString);
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
    static Map<String, String> zhushiMap = new HashMap<String, String>();

    static {
        zhushiMap.put("java", "//");// ## 1:java
        zhushiMap.put("python", "#");// ## 2:python
        zhushiMap.put("c", "//");// ## 3:c
        zhushiMap.put("cpp", "//");// ## 4:cpp
        zhushiMap.put("bat", "::");// ## 5:bat
        zhushiMap.put("sh", "#");// ## 6:sh
        zhushiMap.put("js", "//");// ## 7:js
        zhushiMap.put("swift", "//");// ## 8:swift
        zhushiMap.put("flutter", "//");// ## 9:flutter
        zhushiMap.put("go", "//");// ## 10:go
        zhushiMap.put("kotlin", "//");// ## 11:kotlin
        zhushiMap.put("ruby", "#");// ## 12:ruby
        zhushiMap.put("c#", "//");// ## 13:c#
        zhushiMap.put("scala", "//");// ## 14:scala
    }

}
