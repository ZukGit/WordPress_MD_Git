import java.io.*;
import java.util.ArrayList;

class F9_Byte {

    static void showTip() {
        System.out.println("当前程序是把文件转为 byte[] 数组并以human可读的方式输出!");
    }

    static String curDirPath = "";   // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径

    static StringBuilder mHexSB = new StringBuilder();
    static StringBuilder mBinarySB = new StringBuilder();

    // 把一个当前文件解析出 byte 字节信息
    public static void main(String[] args) {
        initSystemInfo();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {
                    curDirPath = args[i];
                } else {
                    mKeyWordName.add(args[i]);
                }
            }
        }

        if (mKeyWordName.size() == 0) {
            System.out.println("当前需要解析为byte[] 字节数组");
            showTip();
            return;
        }


        ArrayList<File> curFileList = new ArrayList<File>();

        for (int i = 0; i < mKeyWordName.size(); i++) {
            String curFilePath = mKeyWordName.get(i);
            curFilePath = curFilePath.replace("./", "");
            curFilePath = curFilePath.replace(".\\", "");
            curFilePath = curFilePath.replace("\\", "");
            curFilePath = curFilePath.replace("/", File.separator);
            String curFileRealPath = curDirPath + File.separator + curFilePath;
            curFileRealPath = curFileRealPath.replace(File.separator + File.separator, File.separator);
            File curFile = new File(curFileRealPath);
            if (curFile.exists()) {
                curFileList.add(curFile);
            }
        }

        if (curFileList.size() == 0) {
            System.out.println("当前需要解析的文件为空, 非正常退出!");
            showTip();
            return;
        }


        for (int i = 0; i < curFileList.size(); i++) {
            // 获取要操作的文件
            File targetFile = curFileList.get(i);
            byte[] byteArr = tryFile2Byte(targetFile);
            System.out.println("\n\n\n\n══════════════════════Index" + i + "   PATH : " + targetFile.getAbsolutePath());
            System.out.println(dumpHexString(byteArr));
            System.out.println("\n\n\n ══════════════════════  单独16进制(十六进制)形式 ══════════════════════ ");
            System.out.println(mHexSB.toString());
            System.out.println("\n\n\n ══════════════════════  单独二进制形式 ══════════════════════ ");
            System.out.println(mBinarySB.toString());
            mHexSB = new StringBuilder();
            mBinarySB = new StringBuilder();
        }


    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;
    static ArrayList<String> mKeyWordName = new ArrayList<>();


    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            curOS_TYPE = OS_TYPE.Windows;
        } else if (osName.contains("linux")) {
            curOS_TYPE = OS_TYPE.Linux;
        } else if (osName.contains("mac")) {
            curOS_TYPE = OS_TYPE.MacOS;
        }
    }


    private final static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final static char[] HEX_LOWER_CASE_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    static byte[] tryFile2Byte(File target) {
        InputStream input = null;

        int lengthSize;
        byte[] buffer = null;
        // 创建输入输出流对象
        try {
            input = new FileInputStream(target);

            // 获取文件长度
            try {
                lengthSize = input.available();
                // 创建缓存区域
                buffer = new byte[lengthSize];
                // 将文件中的数据写入缓存数组
                input.read(buffer);
                // 将缓存数组中的数据输出到文件


            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (Exception e) {

        } finally {
            if (input != null) {
                try {
                    input.close(); // 关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }


    public static String dumpHexString(byte[] array) {
        if (array == null) return "(null)";
        return dumpHexString(array, 0, array.length);
    }

    public static String showByte(byte byteData){
        StringBuilder sb  = new StringBuilder();
        String result = "";

        for (int i = 7; i >= 0; i--) {

            byte tempByte = byteData;
            tempByte = (byte)(tempByte >> i);
            int value = tempByte & 0x01;
            if(value == 1){
                sb.append("1");
                mBinarySB.append("1");
            }else{
                sb.append("0");
                mBinarySB.append("0");
            }


            if(i == 4){
                sb.append("║");
            }
        }


        return sb.toString()+ " ";

    }

    static int byteIndex = 0 ;
    public static String dumpHexString(byte[] array, int offset, int length) {
        if (array == null) return "(null)";
        StringBuilder result = new StringBuilder();

        byte[] line = new byte[16];
        int lineIndex = 0;

        result.append("\n0x");
        result.append(toHexString(offset));

        // 把 int offset 转为 10 进制   10位数值

        for (int i = offset; i < offset + length; i++) {
            if (lineIndex == 16) {
                result.append(" ");
                ArrayList<Byte> byteList= new ArrayList<Byte>();
                for (int j = 0; j < 16; j++) {
                    byteList.add(line[j]);
                    if (line[j] > ' ' && line[j] < '~') {
                        result.append(new String(line, j, 1));
                    } else {
                        result.append(".");
                    }
                    if(j == 15){  // 最后字符显示一个分割线
                        result.append("  ║ ");  // 这里显示的是 字节信息
                        for (int k = 0; k < byteList.size(); k++) {
                             byte curByte = byteList.get(k);

                             String targetChar = "";
                            if (curByte > ' ' && curByte < '~') {
                                targetChar = new String(line, k, 1);
                            } else {
                                targetChar = ".";
                            }

                            String byreStr = toHexString(curByte);

                             if(k < 9){
                                 result.append("【 0"+k+"-"+toHexStringNoTen(byteIndex)+"-"+toTenString(byteIndex).trim()+"-"+byreStr+"-"+targetChar+" 】 " );
                             }else{
                                 result.append("【 "+k+"-"+toHexStringNoTen(byteIndex)+"-"+toTenString(byteIndex).trim()+"-"+byreStr+"-"+targetChar+" 】 " );
                             }
                            byteIndex++;
                            result.append(showByte(curByte));
                        }


                    }
                }

                result.append("\n0x");
                mHexSB.append("\n");
                mBinarySB.append("\n");
                result.append(toHexString(i));
                lineIndex = 0;
            }

            byte b = array[i];
            result.append(" ");
            result.append(HEX_DIGITS[(b >>> 4) & 0x0F]);
            result.append(HEX_DIGITS[b & 0x0F]);

            mHexSB.append(" ");
            mHexSB.append(HEX_DIGITS[(b >>> 4) & 0x0F]);
            mHexSB.append(HEX_DIGITS[b & 0x0F]);

            line[lineIndex++] = b;
        }

        if (lineIndex != 0) {  // if (lineIndex != 16)  AOSP 中存在错误  无法打印 最后是 16个字节的情况
            int count = (16 - lineIndex) * 3;
            count++;
            for (int i = 0; i < count; i++) {
                result.append(" ");
            }
            ArrayList<Byte> byteList= new ArrayList<Byte>();
            for (int i = 0; i < lineIndex; i++) {
                byteList.add(line[i]);
                if (line[i] > ' ' && line[i] < '~') {
                    result.append(new String(line, i, 1));
                } else {
                    result.append(".");
                }

                if(i == lineIndex -1){  // 最后字符显示一个分割线
                    int paddingSize = 16 -lineIndex;
                    for (int j = 0; j < paddingSize; j++) {
                        result.append(" ");
                    }
                    result.append("  ║ ");  // 这里显示的是 字节信息

                    for (int k = 0; k < byteList.size(); k++) {
                        byte curByte = byteList.get(k);

                        String targetChar = "";
                        if (curByte > ' ' && curByte < '~') {
                            targetChar = new String(line, k, 1);
                        } else {
                            targetChar = ".";
                        }

                        String byreStr = toHexString(curByte);

                        if(k < 9){
                            result.append("【 0"+k+"-"+toHexStringNoTen(byteIndex)+"-"+toTenString(byteIndex).trim()+"-"+byreStr+"-"+targetChar+" 】 " );
                        }else{
                            result.append("【 "+k+"-"+toHexStringNoTen(byteIndex)+"-"+toTenString(byteIndex).trim()+"-"+byreStr+"-"+targetChar+" 】 " );
                        }
                        byteIndex++;
                        result.append(showByte(curByte));
                    }
                }
            }
        }

        return result.toString();
    }

    public static String toHexString(byte b) {
        return toHexString(toByteArray(b));
    }


    public static String toHexString(byte[] array) {
        return toHexString(array, 0, array.length, true);
    }


    public static String toHexString(byte[] array, boolean upperCase) {
        return toHexString(array, 0, array.length, upperCase);
    }


    public static String toHexString(byte[] array, int offset, int length) {
        return toHexString(array, offset, length, true);
    }

    public static String toHexString(byte[] array, int offset, int length, boolean upperCase) {
        char[] digits = upperCase ? HEX_DIGITS : HEX_LOWER_CASE_DIGITS;
        char[] buf = new char[length * 2];

        int bufIndex = 0;
        for (int i = offset; i < offset + length; i++) {
            byte b = array[i];
            buf[bufIndex++] = digits[(b >>> 4) & 0x0F];
            buf[bufIndex++] = digits[b & 0x0F];
        }

        return new String(buf);
    }

    public static String toTenString(int i) {
        String str = i + "";
        int length = str.length();
        if (length < 10) {
            int paddingSize = 10 - length;

            for (int j = 0; j < paddingSize; j++) {
                str = "0" + str;
            }
        }
        str = " " + str;
        //   System.out.println(" 10进制 = "+ str);
        return str;
    }

    public static String toHexString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(toHexString(toByteArray(i)));
        sb.append(toTenString(i));

        return sb.toString();
    }

    public static String toHexStringNoTen(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(toHexString(toByteArray(i)));
        return "0x"+sb.toString().trim();
    }

    public static byte[] toByteArray(byte b) {
        byte[] array = new byte[1];
        array[0] = b;
        return array;
    }

    public static byte[] toByteArray(int i) {
        byte[] array = new byte[4];

        array[3] = (byte) (i & 0xFF);
        array[2] = (byte) ((i >> 8) & 0xFF);
        array[1] = (byte) ((i >> 16) & 0xFF);
        array[0] = (byte) ((i >> 24) & 0xFF);

        return array;
    }

    private static int toByte(char c) {
        if (c >= '0' && c <= '9') return (c - '0');
        if (c >= 'A' && c <= 'F') return (c - 'A' + 10);
        if (c >= 'a' && c <= 'f') return (c - 'a' + 10);

        throw new RuntimeException("Invalid hex char '" + c + "'");
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] buffer = new byte[length / 2];

        for (int i = 0; i < length; i += 2) {
            buffer[i / 2] = (byte) ((toByte(hexString.charAt(i)) << 4) | toByte(hexString.charAt(i + 1)));
        }

        return buffer;
    }

    public static StringBuilder appendByteAsHex(StringBuilder sb, byte b, boolean upperCase) {
        char[] digits = upperCase ? HEX_DIGITS : HEX_LOWER_CASE_DIGITS;
        sb.append(digits[(b >> 4) & 0xf]);
        sb.append(digits[b & 0xf]);
        return sb;
    }


}