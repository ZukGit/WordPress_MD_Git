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
