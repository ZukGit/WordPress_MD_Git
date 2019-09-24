
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class E9_ScreenShot {

    static File ImageDirFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "E9_ScreenShot");
    static NumberFormat nf = new DecimalFormat("0.00");


    public static void main(String[] args) {
        long timestamp1 = System.currentTimeMillis();
        String todayImageDirName = getTodayDesc();

        File todayImageDir = new File(ImageDirFile.getAbsolutePath() + File.separator + todayImageDirName);
        if (!todayImageDir.exists()) {
            todayImageDir.mkdirs();
        }

        String curImageFileName = getTimeStampDesc() + ".jpg";

        File imageFile = new File(todayImageDir.getAbsolutePath() + File.separator + curImageFileName);
        if (!imageFile.exists()) {
            try {
                imageFile.createNewFile();
            } catch (Exception e) {

            }
        }


        // 1920x 1080
        // 怎么 编程 1080x720 了 ?
        //  方法1  2.85秒
        /*        ScreenUtil.captureScreen(imageFile);*/


//        for (int i = 0; i < proDize; i++) {
////            System.out.println("["+arr[i].toString()+"] = 【"+ System.getProperties().getProperty(arr[i].toString())+"】");
////        }
        // 方法2   2.2 秒

        int width = getZScreenWeight();
        int heigh = getZScreenHeight();
        System.out.println("宽:" + width + "     高:" + heigh);


        Robot robot = null;
        try {
            robot = new Robot();
            //根据指定的区域抓取屏幕的指定区域，1300是长度，800是宽度。
            BufferedImage bi = robot.createScreenCapture(new Rectangle(getZScreenWeight(), getZScreenHeight()));
            //把抓取到的内容写到一个jpg文件中
            ImageIO.write(bi, "jpg", imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }


        long timestamp2 = System.currentTimeMillis();
        long timedistance = timestamp2 - timestamp1;

        System.out.println("程序执行花销 " + Double.parseDouble(nf.format((Double) (timedistance / (1024d)))) + "秒!");

    }


    static String getTodayDesc() {
       // String todayDesc = DateUtil.today();

        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
        String todayDesc =    dateFormatYYYYMMDD.format(Calendar.getInstance().getTime());

        if (todayDesc.contains(" ")) {
            todayDesc = todayDesc.replaceAll(" ", "_");
        }
        if (todayDesc.contains("-")) {
            todayDesc = todayDesc.replaceAll("-", "_");
        }
        if (todayDesc.contains(":")) {
            todayDesc = todayDesc.replaceAll(":", "_");
        }

        return todayDesc.trim();
    }


    static String getTimeStampDesc() {
      //  String timeDesc = DateUtil.now();


        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeDesc =    dateFormatYYYYMMDD.format(Calendar.getInstance().getTime());

        if (timeDesc.contains(" ")) {
            timeDesc = timeDesc.replaceAll(" ", "_");
        }
        if (timeDesc.contains("-")) {
            timeDesc = timeDesc.replaceAll("-", "_");
        }
        if (timeDesc.contains(":")) {
            timeDesc = timeDesc.replaceAll(":", "_");
        }

        return timeDesc.trim();
    }


    static int getZScreenHeight() {
        // CMD 和 IDE下 宽高一致  1920x1080
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int height = gd.getDisplayMode().getHeight();
        return height;
/*           在IDE下 分辨率为 1080x720  在 CMD下确是  1920x1080  不一致
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int) screenSize.height;
        if (System.getProperties().getProperty("sun.stderr.encoding") != null &&
                System.getProperties().getProperty("sun.stdout.encoding") != null &&
                "ms936".equals(System.getProperties().getProperty("sun.stderr.encoding")) &&
                "ms936".equals(System.getProperties().getProperty("sun.stdout.encoding"))) {
            screenHeight = (int) (screenHeight * 1.5);
        }
        return screenHeight;*/


    }


    static int getZScreenWeight() {

        // CMD 和 IDE下 宽高一致  1920x1080
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        return width;

/*     在IDE下 分辨率为 1080x720  在 CMD下确是  1920x1080  不一致
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.width;
        if(System.getProperties().getProperty("sun.stderr.encoding") != null &&
                System.getProperties().getProperty("sun.stdout.encoding") != null &&
                "ms936".equals(System.getProperties().getProperty("sun.stderr.encoding")) &&
                "ms936".equals(System.getProperties().getProperty("sun.stdout.encoding"))   ){
            screenWidth = (int)(screenWidth * 1.5);

        }
        return  screenWidth;
        */


    }

}
