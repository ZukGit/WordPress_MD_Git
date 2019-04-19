import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.swing.ScreenUtil;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.RuntimeUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

public class B4 {

    static String dirPath = "";
    static String git_repo_image_name = "monitor_image";
    static String git_repo_text_name = "monitor_text";
    static String gitPushVbsFile = "B4_push.vbs";


    //   TIMESPACE = 10 秒钟       push_interval_minutes = 3分钟(值: 18 )
//   TIMESPACE = 20 秒钟       push_interval_minutes = 3分钟(值: 9 )
//   TIMESPACE = 10 秒钟       push_interval_minutes = 3分钟(值: 18 )
//   TIMESPACE = 10 秒钟       push_interval_minutes = 4分钟(值: 24 )
//   TIMESPACE = 60秒       push_interval_minutes = 5分钟(值: 5 )
//   TIMESPACE = 180秒       push_interval_minutes = 30(值: 10 )
    static double MINUTES_SECOND = 60 ;   //  每次拍照的 间隔时间 是 10秒
    static double Milli_SECOND = 1000 ;   //  每次拍照的 间隔时间 是 10秒
    static double CAPTURE_TIMESPACE_SECOND =  300; // 60 每次拍照的 间隔时间 是 15秒  【1】 数字可变 单位秒
    static double PUSH_TIMESPACE_MINUTES =  30; // 每次 上传 图片的 间隔 时间   【2】 数字可变  单位 分钟
    static double PUSH_TIMESPACE_MINUTES_TEXT =  10; // 每次 上传 图片的 间隔 时间   【2】 数字可变  单位 分钟
    static double TIMESPACE = Milli_SECOND * CAPTURE_TIMESPACE_SECOND;   //  【1 具体的值】
    static double ONE_MINUTE_CAPTURE_TIME =  (MINUTES_SECOND/(TIMESPACE /Milli_SECOND)) ;   //  每一分钟 拍照对少次
    static double countTopush = PUSH_TIMESPACE_MINUTES * ONE_MINUTE_CAPTURE_TIME ;    //  【2 具体的值】
    static double countTopush_Text = PUSH_TIMESPACE_MINUTES_TEXT * ONE_MINUTE_CAPTURE_TIME ;    //
    // 每20 秒 拍一张照片
    //  三分钟 是 9 张照片   上传一次  index 那里 就是 9  而现在 是 18
    static long BIG_LONG_VALUE = 10000000000L;
    static int index = 1;

    public static void main2(String[] args) {
// DateUtil.now() =   2019-04-19 10:30:50
// DateUtil.today() =   2019-04-19
        String timeStamp = DateUtil.now();
        if (timeStamp.contains(" ")) {
            timeStamp = timeStamp.replaceAll(" ", "_");
        }
        if (timeStamp.contains("-")) {
            timeStamp = timeStamp.replaceAll("-", "_");
        }
        if (timeStamp.contains(":")) {
            timeStamp = timeStamp.replaceAll(":", "_");
        }

        System.out.println(" timeStamp =   " + timeStamp);
        System.out.println(" DateUtil.now() =   " + DateUtil.now());
        long currentSeconds = DateUtil.currentSeconds();

        //  currentSeconds =   1555652886
        // currentSeconds =   1555652902
        System.out.println(" currentSeconds =   " + currentSeconds);
        long value  = 60/15;
        System.out.println(" value =   " + value);

        System.out.println(" ONE_MINUTE_CAPTURE_TIME =   " + ONE_MINUTE_CAPTURE_TIME);
        System.out.println(" countTopush =   " + countTopush);


    }

    public static void main(String[] args) {

        //===============real-test begin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        if(mFilePath.endsWith("zbin") || mFilePath.endsWith("zbin/")){
            System.out.println("mFilePath is right  mFilePath = "+ mFilePath);
        } else{
            System.out.println("mFilePath does not zbin path !  mFilePath = "+ mFilePath);
            return;
        }

        System.out.println("mFilePath  =  " + mFilePath);
        // dirPath = mFilePath.trim().substring(0, mFilePath.lastIndexOf(File.separator));
        // dirPath = mFilePath.trim().substring(0, mFilePath.lastIndexOf(File.separator));
        dirPath = mFilePath ;
        System.out.println("dirPath  =  " + dirPath);
        //===============real-test end===============

//  传递  zbin 路径
//===============local-test begin===============
        //       String mFilePath = System.getProperty("user.dir") + File.separator + "1.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";
//===============local-test end===============



        System.out.println("dirPath  =  " + dirPath);

        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            System.out.println("dirFile  argument is invalid ! retry input again!  dirPath ="+ dirPath);
            return;
        }

        File gitImageRepoDir = null;
        File gitTextRepoDir = null;
        File pushVbsFile = null;
        for(File fileItem : dirFile.listFiles()){
            if(fileItem.isDirectory() && fileItem.getName().equals(git_repo_image_name)){
                gitImageRepoDir = fileItem;
            }
            if(fileItem.isFile() && fileItem.getName().equals(gitPushVbsFile)){
                pushVbsFile = fileItem;
            }
            if(fileItem.isDirectory() && fileItem.getName().equals(git_repo_text_name)){
                gitTextRepoDir = fileItem;
            }

        }

        if( gitImageRepoDir == null || pushVbsFile == null  || gitTextRepoDir == null){
            System.out.println("gitRepoExist || pushVbsFile || gitTextRepoDir does not exist!");
            return;
        }


        boolean flag = true;
        while (flag) {
            System.out.println("第 " + index + " 次拍照！");
            String timeStamp = DateUtil.now();
            if (timeStamp.contains(" ")) {
                timeStamp = timeStamp.replaceAll(" ", "_");
            }
            if (timeStamp.contains("-")) {
                timeStamp = timeStamp.replaceAll("-", "_");
            }
            if (timeStamp.contains(":")) {
                timeStamp = timeStamp.replaceAll(":", "_");
            }

            //    File itemFile = new File(System.getProperty("user.dir") + File.separator + timeStamp + ".jpg");
//  数值小的放最上面
            long pot = BIG_LONG_VALUE -  DateUtil.currentSeconds();
            File itemFile = new File(gitImageRepoDir.getAbsolutePath() + File.separator +pot+"_"+ timeStamp+"_index"+index + ".jpg");

            if (!itemFile.exists()) {
                try {
                    itemFile.createNewFile();
                } catch (Exception e) {

                }
            }

            File textFile = new File(gitTextRepoDir.getAbsolutePath() + File.separator + "1.txt");

//----------------Image Begin---------
            ScreenUtil.captureScreen(itemFile);
//----------------Image End---------

//----------------Text Begin---------
            appendToFile(textFile);
//----------------Text Begin---------
            try {

                System.out.println("拍照间隔 CAPTURE_TIMESPACE_SECOND="+CAPTURE_TIMESPACE_SECOND+"秒   " );
                System.out.println("每次拍"+countTopush+"张 照片 上传一次    ImageGit" );
                System.out.println("每次写"+countTopush_Text+"条 Text  上传一次  TextGit " );
                System.out.println("每次上传的间隔是 "+PUSH_TIMESPACE_MINUTES+" 分钟 " );
                System.out.println("该次开始睡" + TIMESPACE / 1000 + "秒");
                Thread.sleep((long)TIMESPACE);
                System.out.println("该次睡" + TIMESPACE / 1000 + "秒结束");




                index++;




                if((index%((long)countTopush))  == 0){
                    System.out.println("每 "+countTopush+" 张照片 上传 git 一次 ---------开始执行 git pull ------------");
                    RuntimeUtil.exec("Wscript.exe  /x " + pushVbsFile.getAbsolutePath()+"  " + dirFile.getAbsolutePath() + "    "+ gitImageRepoDir.getAbsolutePath());
                    System.out.println("  ---------执行 git pull 结束 ------------");
                }

                if((index%((long)countTopush_Text))  == 0){
                    System.out.println(" ---------开始执行 git pull for Text ------------");
                    RuntimeUtil.exec("Wscript.exe  /x " + pushVbsFile.getAbsolutePath()+"  " + dirFile.getAbsolutePath() + "    "+ gitTextRepoDir.getAbsolutePath());
                    System.out.println(" ---------执行 git pull 结束   for Text------------");
                }



            } catch (Exception e) {
                System.out.println("Sleep 睡眠异常了 好像!");
            }
        }
        System.out.println("程序执行结束!");
    }


    public static void appendToFile(File file) {
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
           if(index == 1){
               curBW.append("============开机打印【"+DateUtil.today()+ "】==========");
           }else{
               curBW.append(DateUtil.today());
           }
            curBW.close();
        } catch( Exception e ){


        }


    }




}
