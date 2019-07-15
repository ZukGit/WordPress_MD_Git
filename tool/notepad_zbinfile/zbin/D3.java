

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D3 {
    public static ArrayList<String> StringArr = new ArrayList<>();   // 去除中文后的每行内容

    //http://v.douyin.com/BJyqrA/
    public static ArrayList<String> mHtmlStringArr = new ArrayList<>();   // 添加 short url路径的集合

    //http://v1-dy.ixigua.com/643662497fbb0c76638223a3936e65a6/5d2c36be/video/m/2204cbedbf326b34fe68ff83ee2e1a77c501162d1b5d000080a06add59ad/?rc=M2Y3dTg3bDRlbjMzM2kzM0ApQHRoaGR1KTwzOzw8MzgzMzg0NDQzNDVvQGg2dilAZzN3KUBmM3UpcHpiczFoMXB6QCk1NGRmMGBxL3NrYWdfLS00LS9zcy1vI2p0OmlBLy01Li8vLi4yNDIyNDYtOiNvIzphLW8jOmAtcCM6YGJiXmZeX3RiYl5gNS46
    public static ArrayList<String> mHtmlLongURLArr = new ArrayList<>();   // 添加 long url路径的集合

    // http://v.douyin.com/BJyqrA/
    // http://v1-dy.ixigua.com/643662497fbb0c76638223a3936e65a6/5d2c36be/video/m/2204cbedbf326b34fe68ff83ee2e1a
    public static Map<String,String> short2longURLMap = new HashMap<String,String>();   // 添加url路径的集合


    public static ArrayList<String> mVideoURLArr = new ArrayList<>();   // 添加 视频地址 url路径的集合


    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    static String DIR_PATH = "";

    public static void main(String[] args) {
        //===============real-test begin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        //===============real-test end===============


        //===============local-test begin===============
        //      String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";
        //===============local-test end===============


        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        DIR_PATH = curFile.getParentFile().getAbsolutePath();

        if (curFile != null) {

            FileReader curReader;
            FileWriter curWriter;
            try {

                curReader = new FileReader(curFile);


                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";


                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }

                    newOneLine = new String(oldOneLine);
                    if (isContainChinese(newOneLine)) {
                        newOneLine = clearChinese(newOneLine);

                    }
                    StringArr.add(newOneLine);
                }
                curBR.close();

                for (String rowStr : StringArr) {
                    toGetUrlFromRow(rowStr);
                }


                //  获取地址对应的  video的地址
                // http://v.douyin.com/BJJvH6/
                // String url = "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0300fbd0000bhkfgq7jofsleq3pk120&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&test_cdn=None&improve_bitrate=0&logo_name=aweme";

                for (int i = 0; i < mHtmlStringArr.size(); i++) {
                    String shortUrl = mHtmlStringArr.get(i);
                    HttpRequest httpReq  = HttpRequest.get(shortUrl);
                   HttpResponse httRespon =  httpReq.execute(true);
                    String htmlBody = httRespon.body();
                    System.out.println("Index :"+ i);
                    System.out.println("Body :"+ htmlBody);
                    String beginStr = "<a href=\"";
                    String endStr = "\">";
                 //   short2longURLMap
                    if(htmlBody != null && htmlBody.contains(beginStr) && htmlBody.contains(endStr)){
                        int beginIndex = htmlBody.indexOf(beginStr);
                        int endIndex = htmlBody.indexOf(endStr);
                        String longUrl = htmlBody.substring(beginIndex + beginStr.length(),endIndex );
                        mHtmlLongURLArr.add(longUrl);
                        short2longURLMap.put(shortUrl,longUrl);
                    }
                }

                if(mHtmlLongURLArr.size() > 0){
                    for (int i = 0; i < mHtmlLongURLArr.size(); i++) {
                        String longURLStr = mHtmlLongURLArr.get(i);
                        HttpRequest httpReq  = HttpRequest.get(longURLStr);
                        HttpResponse httRespon =  httpReq.execute(true);
                        String longHtmlBody = httRespon.body();
                        System.out.println("Long-Index :"+ i);
                        System.out.println("Long-Body :"+ longHtmlBody);
                        String videoBeginStr = "playAddr: \"";
                        String videoEndStr = "\",";
                        // mVideoURLArr

                        if(longHtmlBody != null && longHtmlBody.contains(videoBeginStr)){
                            int videoBeginIndex = longHtmlBody.indexOf(videoBeginStr);
                            String videoPath1 = longHtmlBody.substring(videoBeginIndex+videoBeginStr.length());

                            if(videoPath1 != null && videoPath1.contains(videoEndStr)){
                                int videoEndIndex = videoPath1.indexOf(videoEndStr);
                                String videoPath = videoPath1.substring(0,videoEndIndex);
                                System.out.println("【 videoPath:"+ videoPath+" 】");
                                mVideoURLArr.add(videoPath);
                            }
                        }

//
//                        <script>$(function(){
//                            require('web:component/reflow_video/index').create({
//                                    hasData: 1,
//                                    videoWidth: 720,
//                                    videoHeight: 1280,
//                                    playAddr: "https://aweme.snssdk.com/aweme/v1/playwm/?s_vid=93f1b41336a8b7a442dbf1c29c6bbc563b19fa0c606df8371bf186924bee6a194498a165a033922c9f831f4d8ea20f96b7fa70f365b8b259e20b546b67812f74&line=0",
//                                    cover: "https://p1.pstatp.com/large/2a2c90004200bc572cb32.jpg"
//
//            });
//                        });
                    }
                }

// 测试
//    mVideoURLArr.add("http://v9-dy.ixigua.com/b181956b120841dc0cff73ac6912e608/5d2c4b86/video/m/2204cbedbf326b34fe68ff83ee2e1a77c501162d1b5d000080a06add59ad/?rc=M2Y3dTg3bDRlbjMzM2kzM0ApQHRoaGR1KTkzNjs4MzQzMzY3NDQzNDVvQGg2dilAZzN3KUBmM3UpcHpiczFoMXB6QCk1NGRmMGBxL3NrYWdfLS00LS9zcy1vI2p0OmkwLTQ2LS80LS4yMzMyNDYtOiNvIzphLW8jOmAtcCM6YGJiXmZeX3RiYl5gNS46");


                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
                String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
                String namePre = date+"_";

                if(mVideoURLArr.size() > 0){
                    for (int i = 0; i < mVideoURLArr.size(); i++) {
                        String videoUrlItem  = mVideoURLArr.get(i);
                        System.out.println("【 index:"+ (i+1)+" :"+ videoUrlItem + "】");
                        String fileName= namePre+(i+1)+".mp4";
                        if(i < 10){
                             fileName = namePre+"00"+(i+1)+".mp4";
                        }else if( i < 100){
                             fileName = namePre+"0"+(i+1)+".mp4";
                        }else if (i < 1000){
                             fileName = namePre+"0"+(i+1)+".mp4";
                        }

// 20190715_071511_1_xxx
//
                        downLoad(videoUrlItem,fileName,DIR_PATH);
                    }

                }





                writeContent2File(new File(mFilePath), mVideoURLArr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }

    // 对每行的数据进行分析
    public static void toGetUrlFromRow(String rowString) {
        String[] strArrRow = null;
        String fixStr = "";

//        if(str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
//                str.trim().startsWith("thunder:") ||   str.trim().startsWith("magnet::") ){


        if (rowString != null) {
            fixStr = new String(rowString);
            // http://xxxxxx/sahttp://  避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
            fixStr = fixStr.replace("http:", " http:");
            fixStr = fixStr.replace("https:", " https:");
            fixStr = fixStr.replace("thunder:", " thunder:");
            fixStr = fixStr.replace("magnet:", " magnet:");
            strArrRow = fixStr.split(" ");
        }

        if (strArrRow != null && strArrRow.length > 0) {

            for (int i = 0; i < strArrRow.length; i++) {
                String mCurContent = strArrRow[i];
                if (mCurContent == null || mCurContent.trim().equals("")) {
                    continue;
                }


                boolean isUrl = toJudgeUrl(mCurContent);
                if(isUrl){
                    mHtmlStringArr.add(mCurContent.trim());

                }

            }


        }


    }


    public static boolean toJudgeUrl(String str) {
        boolean isUrl = false;

        if (str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
                str.trim().startsWith("thunder:") || str.trim().startsWith("magnet:")) {

            return true;
        }


        return isUrl;
    }

    public static void writeContent2File(File targetFile, ArrayList<String> strList) {

        BufferedWriter curBW = null;
        try {
            curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "utf-8"));

            for (int i = 0; i < strList.size(); i++) {
                curBW.write(strList.get(i));
                curBW.newLine();
            }
            curBW.close();
            System.out.println("OK !");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile(REGEX_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }


// 下载抖音
    public static void  downLoad(String url, String fileName, String savePath){


        Connection.Response document = null;
        try {
            document = Jsoup.connect(url).timeout(0).ignoreContentType(true).maxBodySize(30000000).timeout(10000).execute();
            BufferedInputStream stream = document.bodyStream();
            File outfile = new File(savePath + File.separator + fileName);

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(outfile));

            byte[] bt = new byte[1024];
            int n=0;
            while ((n=stream.read(bt))!=-1) {
                bout.write(bt, 0, n);

            }
            bout.flush();
            stream.close();
            bout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
