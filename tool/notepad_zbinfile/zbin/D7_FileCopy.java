import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class D7_FileCopy {


    // 名称中的关键词
    static ArrayList<String> mKeyWordName = new ArrayList<>();
    static ArrayList<String> mTypeList = new ArrayList<>();

    static int currentMode = 0;
    static int SEARCH_MODE_TYPE_NAME = 0;
    static int SEARCH_MODE_TYPE = 1;
    static int SEARCH_MODE_NAME = 2;

    static Set<File> allDirFileSet = new HashSet<>();  // 工程下所有的 文件夹文件
    static Set<File> allSimpleFileSet = new HashSet<>();   // 当前工程下所有非文件夹的 文件
    static int mSumDirNum = 0;


    public static void main(String[] args) {

        String typeStr = null;
        String mShellPath = null;
        File curDirFile = null;
        File dstFile = null;


        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String mDstDirFilePathStr =date+"_zcopy";
//args[0] = D:\jira_download\0802\src
//args[1] = .jpg.txt.mp4.png
//args[2] = aaa
//args[3] = xxsaadx
//args[4] = xfdsa
//args[5] = sa
//args[6] = daada
//args[7] = dacfada
//args[8] = daada

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (i == 0) {
                    mShellPath = args[0];
                    curDirFile = new File(mShellPath);
                    if (!curDirFile.exists()) {
                        System.out.println("当前shell路径不存在! 执行失败!  失败路径=" + mShellPath);
                        System.out.println("示例输入格式:  zfilesearch .jpg.txt.png.mp4  you  me  she ");
                        return;
                    }
                    continue;
                }
                String itemArgStr = args[i];
                if (itemArgStr.startsWith(".")) {
                    typeStr = itemArgStr;
                    mTypeList = getSingleType(typeStr);
                } else {
                    mKeyWordName.add(itemArgStr);
                }
            }
        }

        if (mKeyWordName.size() == 0 && mTypeList.size() == 0) {
            System.out.println(" 当前搜索的文件类型为空  当前搜索的文件名为空  将显示文件夹文件类型数据!");
            System.out.println(" 执行格式例子_按类型名称: xxxx.bat   .mp4  mp4");
            System.out.println(" 执行格式例子_按类型    : xxxx.bat   .mp4 ");
            System.out.println(" 执行格式例子_按名称    : xxxx.bat    mp4 ");
            // 显示出当前文件夹 文件类型 以及文件个数
            initDirFileSet(curDirFile);
            initSimpleFileSet();
            getFileTypeInfo();
            showMapSummaryDataStyle2();
            System.out.println("程序正常结束!");
            return;
        }

        if (mKeyWordName.size() > 0 && mTypeList.size() == 0) {
            currentMode = SEARCH_MODE_NAME;
        }
        if (mKeyWordName.size() == 0 && mTypeList.size() > 0) {
            currentMode = SEARCH_MODE_TYPE;
        }
        if (mKeyWordName.size() > 0 && mTypeList.size() > 0) {
            currentMode = SEARCH_MODE_TYPE_NAME;
        }


        initDirFileSet(curDirFile);
        initSimpleFileSet();

        initArrFileMap(mTypeList);

        beginFliterFile(mTypeList, mKeyWordName);

        sortMapData();
        showMapDetailData();
        showMapSummaryData();
        dstFile = new File(curDirFile,mDstDirFilePathStr);

        todoCopyOperation(dstFile);

        System.out.println(" 程序正常结束!");
    }

    static int mFileCount =  0 ;
    public static Comparator nameCompara = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            String fileName1 = o1.getName().toLowerCase();
            String fileName2 = o2.getName().toLowerCase();
            return fileName1.compareTo(fileName2);
        }
    };


    public static Comparator strCompara = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };


    @SuppressWarnings("unchecked")
    public static void todoCopyOperation(File dstFile) {

        if(!dstFile.exists()){
            try{
                dstFile.mkdirs();
            }catch (Exception e){
                System.out.println("创建目录"+dstFile.getName()+"失败!   程序退出!");
            }
        }
        if(!dstFile.exists()){
            System.out.println("创建目录"+dstFile.getName()+"失败!   程序退出!");
            return;
        }


        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value

                for (int i = 0; i < fileArr.size(); i++) {
                    File curFile = fileArr.get(i);
                    String fileName = curFile.getName();
                    String fileType = "";
                    if(fileName.contains(".")){
                        fileType = fileName.substring(fileName.lastIndexOf(".")).trim();
                        fileName =  fileName.substring(0,fileName.lastIndexOf(".")).trim();
                    }
                    int fileIndex = i + 1;

                    // 名称重复  可能导致复制失败  所以添加  序号  保证 名称的唯一性
                    File targetFile = new File(dstFile.getAbsolutePath() + File.separator + fileName+"_"+fileIndex+fileType);

                    fileCopy(curFile,targetFile);
                }

            }
        }


    }


    static int curFileIndex = 1;
    public static void fileCopy(File origin, File target) {

        System.out.println("复制中-请等待...当前索引:"+curFileIndex+"  总索引:"+mFileCount + " 当前操作文件:"+origin.getAbsolutePath());
        curFileIndex++;
        InputStream input = null;
        OutputStream output = null;
        int lengthSize;

        // 创建输入输出流对象
        try {

            if(!target.exists()){
                target.createNewFile();
            }

            input = new FileInputStream(origin);
            output = new FileOutputStream(target);
            // 获取文件长度
            try {
                lengthSize = input.available();
                // 创建缓存区域
                byte[] buffer = new byte[lengthSize];
                // 将文件中的数据写入缓存数组
                input.read(buffer);
                // 将缓存数组中的数据输出到文件
                output.write(buffer);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (Exception e) {

        } finally {
            if (input != null && output != null) {
                try {
                    input.close(); // 关闭流
                    output.close();
                } catch (IOException e) {
                    System.out.println("复制过程报错   程序退出!");
                    e.printStackTrace();
                }
            }
        }
    }


    @SuppressWarnings("unchecked")
    public static void sortMapData() {
        Set<String> keySet = arrFileMap.keySet();

        for (String curKey : keySet) {
            ArrayList<File> curFileList = arrFileMap.get(curKey);
            curFileList.sort(nameCompara);
        }

    }

    @SuppressWarnings("unchecked")
    public static void showMapDetailData() {
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                String fixedType = getFixedType(typeStr);
                System.out.println("================" + fixedType + "Begin================");
                for (int i = 0; i < fileArr.size(); i++) {
                    File curFile = fileArr.get(i);
                    String Path = curFile.getAbsolutePath();
                    int index2 = i + 1;
                    String fixedIndex = getFixedIndex(index2);
                    System.out.println(" 【index : " + fixedIndex + "】   " + Path);
                }
                System.out.println("================" + fixedType + "End================");
            }
        }
    }

    static String get15FixedType(String type) {
        // type 最长保留10位
        String fixedType = "";
        int curLength = type.length();
        if (curLength < 15) {
            int blankIndex = 15 - curLength;
            for (int i = 0; i < blankIndex; i++) {
                fixedType = " " + fixedType;
            }

        }

        return type + fixedType;
    }



    static String getFixedType(String type) {
        // type 最长保留10位
        String fixedType = "";
        int curLength = type.length();
        if (curLength < 6) {
            int blankIndex = 6 - curLength;
            for (int i = 0; i < blankIndex; i++) {
                fixedType = " " + fixedType;
            }

        }

        return type + fixedType;
    }

    static String getFixedIndex(int i) {
        String rtnStr = "";
        if (i >= 0 && i < 10) { // 9
            rtnStr = "00000" + i;
        } else if (i >= 10 && i < 100) { // 99
            rtnStr = "0000" + i;
        } else if (i >= 100 && i < 1000) {  // 999
            rtnStr = "000" + i;
        } else if (i >= 1000 && i < 10000) {  // 9999
            rtnStr = "00" + i;
        } else if (i >= 10000 && i < 100000) {  // 99999
            rtnStr = "0" + i;
        } else {
            rtnStr = "" + i;
        }

        return rtnStr;
    }



    @SuppressWarnings("unchecked")
    public static void showMapSummaryDataStyle2() {
        int fileSum = 0 ;
        ArrayList<String> formatStringList = new  ArrayList<String>();
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                int curFileSize = fileArr.size();
                fileSum = fileSum +  curFileSize;
              //  System.out.println("文件类型:" + get15FixedType(typeStr) + "  匹配文件个数:" + fileArr.size());
                formatStringList.add("文件类型:" + get15FixedType(typeStr) + "  匹配文件个数:" + curFileSize);
            }
        }
        formatStringList.sort(strCompara);
        for(String infoItem: formatStringList){
            System.out.println(infoItem);
        }
        mFileCount = fileSum;
        System.out.println("文件总数:"+fileSum);
    }


    @SuppressWarnings("unchecked")
    public static void showMapSummaryData() {
        int fileSum = 0 ;
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                int curFileSize = fileArr.size();
                fileSum = fileSum +  curFileSize;

                System.out.println("文件类型:" + getFixedType(typeStr) + "  匹配文件个数:" + fileArr.size());
            }
        }
        mFileCount = fileSum;
    }


    public static void beginFliterFile(ArrayList<String> typeList, ArrayList<String> nameList) {
        ArrayList<File> allSingleFileList = new ArrayList<File>();
        allSingleFileList.addAll(allSimpleFileSet);
        for (int i = 0; i < allSingleFileList.size(); i++) {
            File curFile = allSingleFileList.get(i);

            if (currentMode == SEARCH_MODE_NAME) {
                String curFileName = curFile.getName();
                boolean matchNameFlag = CheckFileName(curFileName, nameList);

                if (matchNameFlag) {
                    addItemFileToMap("", curFile);
                }


            } else if (currentMode == SEARCH_MODE_TYPE) {
                String curFileName = curFile.getName();
                // 返回匹配到的类型  作为map的key 没有匹配到 就返回 null
                String matchTypeKey = CheckFileType(curFileName, typeList);
                if (matchTypeKey != null) {
                    addItemFileToMap(matchTypeKey, curFile);
                }
            } else {  //  类型和 名称都要检查
                String curFileName = curFile.getName();
                boolean matchNameFlag = CheckFileName(curFileName, nameList);
                String matchTypeKey = CheckFileType(curFileName, typeList);
                if (matchNameFlag && matchTypeKey != null) {
                    addItemFileToMap(matchTypeKey, curFile);

                }

            }


        }

    }

    static void addItemFileToMap(String key, File fileItem) {
        ArrayList<File> fileArr = arrFileMap.get(key);
        fileArr.add(fileItem);
    }

    //
    static boolean CheckFileName(String curFileName, ArrayList<String> nameList) {
        boolean flag = false;

        for (int i = 0; i < nameList.size(); i++) {
            String curNameItem = nameList.get(i).trim().toLowerCase();

            if (curFileName.trim().toLowerCase().contains(curNameItem)) {
                flag = true;
                break;
            }

        }
        return flag;
    }


    static String CheckFileType(String curFileName, ArrayList<String> typeList) {
        String curTypeStr = null;
        if (!curFileName.contains(".")) {
// 当前的文件没有包含后缀  所以无法识别类型 返回 null
            return curTypeStr;
        }


//  获得 当前文件的  .后缀名    .jpg   .png
        String suffix = curFileName.substring(curFileName.lastIndexOf(".")).trim().toLowerCase();
        for (int i = 0; i < typeList.size(); i++) {
            String curFileType = typeList.get(i);

            if (curFileType.equals(suffix)) {
                curTypeStr = suffix;
                break;
            }

        }
        return curTypeStr;
    }


    // key = type       value =  符合过滤文件规则的名称的文件的集合
    public static HashMap<String, ArrayList<File>> arrFileMap = new HashMap<String, ArrayList<File>>();

    // 当 Mode 为 SEARCH_MODE_NAME 时 typeStrList 为空   那么 我们初始化的时候 就以 空字符串 "" 为key
    static void initArrFileMap(ArrayList<String> typeStrList) {
        if (typeStrList.size() == 0) {
            String key = "";
            ArrayList<File> fileList = new ArrayList<File>();
            arrFileMap.put(key, fileList);
        } else {
            for (int i = 0; i < typeStrList.size(); i++) {
                String typeItem = typeStrList.get(i);
                ArrayList<File> fileListItem = new ArrayList<File>();
                arrFileMap.put(typeItem, fileListItem);
            }
        }
    }


    static void addFileMapItemWithKey(String keyType, File curFile) {
        if (arrFileMap.containsKey(keyType)) {
            ArrayList<File> fileList = arrFileMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            arrFileMap.put(keyType, fileList);
        }
    }


    static void initDirFileSet(File shellDirFile) {
        addAllFileDir(shellDirFile);
    }

    static void getFileTypeInfo() {
        for (File curFile : allSimpleFileSet) {
            String fileName = curFile.getName();
            if (!fileName.contains(".")) {
                addFileMapItemWithKey("unknow", curFile);
            } else {
                String suffix = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
                addFileMapItemWithKey(suffix, curFile);
            }

        }
    }


    static void initSimpleFileSet() {
        int fileIndex = 1;
        for (File dirFile : allDirFileSet) {
            //    System.out.println("index=" + fileIndex + "   PATH:" + dirFile.getAbsolutePath());
            File[] childFileList = dirFile.listFiles();
            if (childFileList != null && childFileList.length > 0) {
                for (int i = 0; i < childFileList.length; i++) {
                    if (!childFileList[i].isDirectory()) {
                        allSimpleFileSet.add(childFileList[i]);
                    }
                }

            }
            fileIndex++;
        }
    }


    static int addAllFileDir(File dirFile) {   //  添加所有的 文件夹对象
        if (dirFile != null && dirFile.isDirectory()) {
            allDirFileSet.add(dirFile);
            mSumDirNum++;
        }
        if (isEmptyDirFile(dirFile)) {  // 如果是空的文件夹  返回它的内部文件夹数量是0
            return 0;
        }
        ArrayList<File> childDirFile = getChildDirFileMethod(dirFile);
        if (childDirFile != null && childDirFile.size() > 0) {

            for (File dirFileItem : childDirFile) {
                addAllFileDir(dirFileItem);
            }
        }
        return mSumDirNum;
    }


    static ArrayList<File> getChildDirFileMethod(File dirFile) {
        ArrayList<File> dirFileList = null;  // new   ArrayList<File>();
        if (dirFile == null) {
            return null;
        }
        File[] childChildList = dirFile.listFiles();
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                if (dirFileList == null) {
                    dirFileList = new ArrayList<File>();
                }
                dirFileList.add(childChildList[i]);
            }
        }
        return dirFileList;
    }


    static boolean isEmptyDirFile(File dirFile) {
        boolean flag = true;
        if (dirFile == null) {
            return true;
        }
        File[] childChildList = dirFile.listFiles();
        if (childChildList == null) {
            return true;
        }
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                return false;
            }
        }
        return flag;
    }


    static ArrayList<String> getSingleType(String typeStr) {
        ArrayList<String> strList = new ArrayList<String>();
        String replacePointStr = typeStr.replace(".", "_");
        String[] arr = replacePointStr.split("_");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || "".equals(arr[i].trim())) {
                continue;
            }
            strList.add("." + arr[i].trim());
        }
        return strList;
    }


}
