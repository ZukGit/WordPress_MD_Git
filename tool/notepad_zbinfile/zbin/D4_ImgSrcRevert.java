import java.io.*;
import java.util.*;

//  把bat 放入目录  点击 执行  更改目录中文件的名称
//
//land1.mp4
//and1 - 副本.mp4

// 把当前用户目录下的所有 md 文件中的  图片格式
// ![360截图20190801162011556](C:\Users\zhuzj5\Desktop\360截图20190801162011556.jpg)  转为
// <img src="C:\Users\zhuzj5\Desktop\360截图20190801162011556.jpg" alt="360截图20190801162011556"/>



public class D4_ImgSrcRevert {
    static String UserDir =  System.getProperty("user.dir");
    static String CurrentDir =  "";
    public static final ArrayList<File> markdownFileList_All = new ArrayList<File>();
    public static final ArrayList<File> mp4FileList_NeedFix = new ArrayList<File>();
    public static final ArrayList<File> mp4FileList_Fixed = new ArrayList<File>();
    public static Map<String,ArrayList<File>> nameMap = new HashMap<String,ArrayList<File>>();

    public static void main(String[] args) {


        String mMarkDownDirPath = null;

        // 1.bat调用测试打开===begin
        if (args.length >= 1) {
            mMarkDownDirPath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }

        // 1.bat调用测试打开===end

      //    mMarkDownDirPath  = UserDir;          // 2.本地测试打开


        File curMarkDownDir;
        curMarkDownDir = new File(mMarkDownDirPath);
        if (mMarkDownDirPath != null && !mMarkDownDirPath.isEmpty() && (curMarkDownDir.exists()) && curMarkDownDir.isDirectory()) {
            CurrentDir = curMarkDownDir.getAbsolutePath();
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        // 获取 当前路径下的 所有 mp4 文件
        System.out.println("当前文件夹路径:" + curMarkDownDir.getAbsolutePath());
        File[] curFileList = curMarkDownDir.listFiles();
        getMarkdownFileInCurrentDir(curFileList);

        if(markdownFileList_All.size() == 0){
            System.out.println("当前路径下没有md文件,无法img src转换!");
            return;
        }
        //  addfixedFileItemAndUpdate();
        trytoRevertImgSrcFromMarkdown();

        System.out.println("Main-Over!");

    }
    static void trytoRevertImgSrcFromMarkdown() {
        for (int i = 0; i < markdownFileList_All.size(); i++) {
            File curMarkDownFile = markdownFileList_All.get(i);
            DoRevertImgSrc2File(curMarkDownFile);
        }

    }

    static void DoRevertImgSrc2File(File mdFile) {

        StringBuilder sb =  readStringArrFromFile(mdFile);
        writeContentToFile(mdFile,sb.toString());

    }



    static void writeContentToFile(File file, String strParam) {

        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(strParam);
                curBW.flush();
                curBW.close();
                System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //  把 MD 格式的 翻译成  html 格式



    static StringBuilder readStringArrFromFile(File fileItem) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                // 开始判断当前行是否 是 md类型的图片
//aaa![360截图20190801162011556](C:\Users\zhuzj5\Desktop\360截图20190801162011556.jpg)![360截图20190801162011556](C:\Users\zhuzj5\Desktop\360截图20190801162011556.jpg)sasa
// <img src="C:\Users\zhuzj5\Desktop\360截图20190801162011556.jpg" alt="360截图20190801162011556"/>
                // 1. 必须包含  ![  连着的字符
                // 2. 必须包含  ](
                // 3. 必须包含 .png  或者 .jpg

                boolean isImageRow = isImgLine(lineContent);
                if(!isImageRow){ // 当前行不含img的话  直接加入到 StringBuilder
                    sb.append(lineContent + "\n");
                }else{  //  当前行包含  ![ x ]( 1.jpg )

                    String htmlImgSrcStr =   revertImgRowLine(lineContent);
                    sb.append(htmlImgSrcStr + "\n");
                }

            }
            curBR.close();
        } catch (Exception e) {
        }
        return sb;
    }



    static String revertImgRowLine(String imgRow) {
        StringBuilder returnResult = new StringBuilder();
        ArrayList<String> strArr = new ArrayList();
        String[] arr =  imgRow.split("!\\[");
        System.out.println("imgRow = "+ imgRow);
        for (int i = 0; i < arr.length; i++) {

            System.out.println("index = "+i+"    rowvalue = " + arr[i]);
            System.out.println("index = "+i+"    value = " + "!["+arr[i]);

            if("".equals(arr[i].trim())){  // 当前分割成的字符串为 "" 空字符
                if(i == 0){
                    strArr.add(arr[i]);
                }else {
                    strArr.add("!["+arr[i]);
                }

            } else{
                //  当前 分割出的是有字符串的值
// 360截图20190801162011556](C:\Users\zhuzj5\Desktop\360截图20190801162011556.jpg)
//        aa
                String curStringItem = "!["+arr[i];
                if(isImgLine(curStringItem)){  // ![360截图20190801162011556](C:\Users\zhuzj5\Desktop\360截图20190801162011556.jpg)
                    strArr.add(curStringItem);

                }else{  // aa 这样的字符串  不是 img的字符串
                    int preIndex  = i - 1;
                    String preItem = "";
                    if(preIndex < arr.length && preIndex >= 0){
                        preItem = arr[preIndex];
                    }
                    if("".equals(preItem.trim())){  // 如果之前的项为"" 的话 当前 添加原始的数据
                        strArr.add(arr[i]  );
                    }else{  // 如果之前的项不为 ""   那么要在当前字符串前 添加 ![
                        strArr.add(curStringItem);

                    }

                }
            }

            // 第一个右括号  进行变化
//![ = <img alt="
//]( = " src="
//) = " />

        }

        for (int i = 0; i <strArr.size() ; i++) {
            String fixItem = strArr.get(i);
            boolean isImageItem = isImgLine(fixItem);
            if(!isImageItem){
                returnResult.append(fixItem.trim());
            }else{ // 对 ![xx](xx.jpg))))))) 进行改造
// 最前面的 ![ 转为  ![ = <img alt="
                if(fixItem.startsWith("![")){
                    fixItem = "<img alt=\""+fixItem.substring(2);
                }

                // <img alt="x ]( 1.jpg )
                int beginIndex = fixItem.indexOf("](");
                String pre = fixItem.substring(0,beginIndex);
                String end = fixItem.substring(beginIndex+2);
//  ]( = " src="
                fixItem = pre + "\" src=\" " + end;


                //  ) = " />
                //  <img alt="x " src=" 1.jpg )
                int beginIndex1 = fixItem.indexOf(")");
                String pre1 = fixItem.substring(0,beginIndex1);
                String end1 = fixItem.substring(beginIndex1+1);
                fixItem = pre1 + "\" />" + end1;
                returnResult.append(fixItem.trim());
                System.out.println(" cur_img_src = "+ fixItem.trim());

            }

        }



//        System.out.println( imgRow.trim());
        //      System.out.println( returnResult.toString().trim());

//   System.out.println("END!");
        return returnResult.toString().trim();
    }

    static boolean isImgLine(String rowString){
        boolean flag = false ;
        if(rowString.contains("![") && rowString.contains("](") && rowString.contains(")") && ( rowString.contains(".png") || rowString.contains(".jpg")) ){
            flag = true;
        }

        return flag;
    }
    //
    static void getMarkdownFileInCurrentDir(File[] fileList) {
        if(fileList == null || fileList.length ==0){
            System.out.println(" 当前路径的文件夹为0！ ");
            return;
        }
        for (File fileItem: fileList){
            String fileName = fileItem.getName();
            if(fileName.endsWith(".md")){ //  如果文件以 .md 结尾
                markdownFileList_All.add(fileItem);
            }
        }

    }



}