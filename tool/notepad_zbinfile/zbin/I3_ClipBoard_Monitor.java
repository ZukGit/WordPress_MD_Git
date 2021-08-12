
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 剪贴板监控器
 * 负责对剪贴板文本的监控和操作
 * 由于监控需要一个对象作为ClipboardOwner，故不能用静态类
 *
 */
public class I3_ClipBoard_Monitor implements ClipboardOwner{


    static File CUR_Dir_FILE;
    static String CUR_Dir_1_PATH = "";    //  arg[0] 就是shell路径 String 类型
    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
   static JFrame jframe = null;
    public I3_ClipBoard_Monitor(){
        //如果剪贴板中有文本，则将它的ClipboardOwner设为自己
        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
            clipboard.setContents(clipboard.getContents(null), this);
        }
    }


    /************
     * 测试代码 *
     * **********
     */
    public static void main(String[] args) {

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                }
            }
        }

            File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);

            if(!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory()){
                System.out.println("当前shell-dir路径为空 无法执行程序!");
                return;
            }
           CUR_Dir_FILE = new File(CUR_Dir_1_PATH);

        I3_ClipBoard_Monitor temp = new I3_ClipBoard_Monitor();
        if(jframe != null){
            jframe.setVisible(true); // 软件窗口
        }else{
            jframe = new JFrame();
            jframe.setVisible(true); // 软件窗口
        }
    }

    /**********************************************
     * 如果剪贴板的内容改变，则系统自动调用此方法 *
     **********************************************
     */
    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        // 如果不暂停一下，经常会抛出IllegalStateException
        // 猜测是操作系统正在使用系统剪切板，故暂时无法访问
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 取出文本并进行一次文本处理
        String text = null;
        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
            try {
                text = (String)clipboard.getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        String clearedText = Text.handle(text); // 自定义的处理方法
//        // 存入剪贴板，并注册自己为所有者
//        // 用以监控下一次剪贴板内容变化
//        StringSelection tmp = new StringSelection(clearedText);
//        clipboard.setContents(tmp, this);
        Operation(text);
        I3_ClipBoard_Monitor temp = new I3_ClipBoard_Monitor();
        new JFrame().setVisible(true); // 软件窗口
    }


    static void Operation(String clipText){
        System.out.println("════════"+clipText+"  Begin════════");
        System.out.println("time = ");
//        System.out.println("clip_text = "+ clipText);

        File originMP4File = new File(clipText);
            if(!originMP4File.exists()){
                System.out.println("【1】 当前剪切板文件 不存在! ");
                return;
          }
        System.out.println("【1】 当前剪切板文件存在!  Path -> "+ originMP4File.getAbsolutePath());

            if(!originMP4File.getName().toLowerCase().endsWith(".mp4")){
                System.out.println("【2】 当前剪切板文件存在,但不是MP4文件  Path -> "+ originMP4File.getAbsolutePath());
                System.out.println("════════"+clipText+" End Failed════════");
                return;
            }
        System.out.println("【2】 当前剪切板文件存在! 并且是MP4文件!  Path -> "+ originMP4File.getAbsolutePath());


            long fileSize = originMP4File.length();

            if(fileSize > 1024L*1024L*100){
                System.out.println("【3】 MP4文件文件过大 大于100MB (不能复制)!  FileSize -> "+ getFileSize(originMP4File));
                System.out.println("════════"+clipText+" End Failed════════");
                return;
            }
        System.out.println("【3】 MP4文件文件小于100MB  正常 (能复制)!  FileSize -> "+ getFileSize(originMP4File));


            String fileName = originMP4File.getName();
            File newShellFile = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+fileName);

            if  (newShellFile.exists() && newShellFile.length() > 10*1024L ){
                System.out.println("【4】 当前目标shell目录已存在同名文件 并有空间大小 所以不执行复制操作！  shellFileSize = "+getFileSize(newShellFile));
                System.out.println("════════"+clipText+" End Failed════════");
                return;
            }
        System.out.println("【4】 MP4文件文件小于100MB  正常 (能复制)!  FileSize -> "+ getFileSize(originMP4File));


        System.out.println("【5】 开始执行复制操作!");
        fileCopy(originMP4File,newShellFile);
        System.out.println("════════"+clipText+" End Success════════");
    }

    public static void fileCopy(File origin, File target) {
        InputStream input = null;
        OutputStream output = null;
        int lengthSize;
        // 创建输入输出流对象
        try {
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
            System.out.println("【6】 复制成功!!");
        } catch (Exception e) {
            System.out.println("【5】 复制失败!");
        } finally {
            if (input != null && output != null) {
                try {
                    input.close(); // 关闭流
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static String getFileSize(File file){
 long fileSize = file.length();
 String name = file.getName();
 String sizeStr = "";
 if(fileSize > 1024L &&  fileSize <= 1024L*1024L ){
     sizeStr = fileSize/ (1024L) + "KB ";
 } else if(fileSize > 1024L*1024L &&  fileSize <= 1024L*1024L*1024L){
     sizeStr = fileSize/ (1024L*1024L) + "MB ";
 }else if(fileSize > 1024L*1024L*1024L &&  fileSize <= 1024L*1024L*1024L*1024L){
     sizeStr = fileSize/ (1024L*1024L*1024L) + "GB ";
 }
 if("".endsWith(sizeStr)){
     sizeStr = fileSize+ "B";
 }
return name+ " "+ sizeStr;
    }

    static String getTimeStamp(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

}