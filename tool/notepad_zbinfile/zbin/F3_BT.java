import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import org.eclipse.ecf.protocol.bittorrent.TorrentFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class F3_BT {



    static String Magnet_PreHead ="magnet:?xt=urn:btih:";
    static  String DESKTOP_PATH =  System.getProperties().getProperty("user.home")+ File.separator+"Desktop";
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" ;
    static ArrayList<String> mKeyWordName = new ArrayList<>();
    static String curPath = "";
    static String F3_INPUT_Name = "F3.txt";
    static File F3_INPUT_FILE ;

    static File F3_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F3.properties");

    static InputStream F3_Properties_InputStream;
    static OutputStream F3_Properties_OutputStream;
    static Properties F3_Properties = new Properties();
    static Set<String> downloadKeySet = new HashSet<String>();
    static Map<String,String> allPropMap = new HashMap<String,String>();
    static  Set<BTFile> btFileSet =new HashSet<BTFile>();
    static {
        try {
            if(!F3_Properties_File.exists()){
                F3_Properties_File.createNewFile();
            }
            F3_Properties_InputStream = new BufferedInputStream(new FileInputStream(F3_Properties_File.getAbsolutePath()));
            F3_Properties.load(F3_Properties_InputStream);
            Iterator<String> it = F3_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                downloadKeySet.add(key);
                allPropMap.put(key,F3_Properties.getProperty(key));
//                   System.out.println("key:" + key + " value: " + F3_Properties.getProperty(key));
            }
            F3_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static  Comparator timeComp = new Comparator<BTFile>() {
        @Override
        public int compare(BTFile o1, BTFile o2) {
            if(o1.getTimestamps() < o2.getTimestamps()){
                return -1;
            }else{
                return 1;
            }
        }
    };

    public static void main(String[] args) {

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

        if(mKeyWordName.size() == 0){
            System.out.println("当前输入的路径为空 无法解析! 请重新执行!");
            return;
        }

        String arg1 = mKeyWordName.get(0);
        curPath = arg1;
        F3_INPUT_FILE = new File(curPath+File.separator+F3_INPUT_Name);
        File dirFile = new File(arg1);

        if(!dirFile.exists() || !dirFile.isDirectory() ){
            System.out.println("当前输入的路径不存在 无法解析! 请重新执行!");
            return;
        }

        if(mKeyWordName.size() == 1){  //   默认只调用了 zbttool_F3.bat
            initDataFromInputTxt(F3_INPUT_FILE);
            initDataFromBTFile(dirFile);
            initDataFromImageQrFile(dirFile);
            initDataFromProp(allPropMap);
            analysisAllBTFile();
        }else if(mKeyWordName.size() == 2){
            long cursorTimeStamp =   Long.parseLong(mKeyWordName.get(1));
            initDataFromInputTxt(F3_INPUT_FILE);
            initDataFromBTFile(dirFile);
            initDataFromProp(allPropMap);
            doCursorTimeStampStep(cursorTimeStamp);
            System.out.println("════════════"+"已完成资源DownFlag的标志位记录! "+"════════════");

            System.out.println("════════════"+"最新资源情况"+"════════════"); //
            AllBTFileInfo();
            System.out.println("════════════"+"已完成资源DownFlag的标志位记录! "+"════════════");
        }


        setProperity();
    }



    @SuppressWarnings("unchecked")
    static void  AllBTFileInfo(){
        ArrayList<BTFile> btFilesListArr = new ArrayList<BTFile>();
        btFilesListArr.addAll(btFileSet);
        btFilesListArr.sort(timeComp);
        BTFile minTimeFile = btFilesListArr.get(0);  // 最久的记录
        BTFile maxTimeFile = btFilesListArr.get(btFilesListArr.size()-1);;  // 最新的记录
        BTFile cursorFile = null;   // 当前游标记录的还未完成下载的离现在最远的那个记录
        ArrayList<BTFile> needDownloadBTFilesList= new ArrayList<BTFile>();
        ArrayList<BTFile> mDownloadBTFilesList= new ArrayList<BTFile>();  // 已下载
        int cursorIndex = 0 ;
        System.out.println("════════════"+"当前保存总管长度:"+btFilesListArr.size()+"════════════");
        for (int i = 0; i < btFilesListArr.size(); i++) {
            BTFile btItem = btFilesListArr.get(i);
            if(!btItem.isHasDownloaded()){
                needDownloadBTFilesList.add(btItem);
            }else{
                mDownloadBTFilesList.add(btItem);
            }


            if(cursorFile == null && !btItem.isHasDownloaded()){
                cursorFile = btItem;
                cursorIndex = i;
            }
            System.out.println("index["+get8Padding(i+1)+"]  Key:【 "+btItem.getKey()+" 】"+"  Value:【 "+btItem.getValue()+" 】");
        }


        //sout
        System.out.println();
        System.out.println("════════════"+"当前下载Flag情况"+"════════════");
        if(minTimeFile != null){
            System.out.println("最久Item的索引 ["+get8Padding(1)+"] 时间戳: ["+minTimeFile.getTimestamps() +" ]   对应:[ "+getTimeStapStr(minTimeFile.getTimestamps()) +" ]");
        }
        if(maxTimeFile != null){
            System.out.println("最新Item的索引 ["+get8Padding(btFilesListArr.size())+"] 时间戳: ["+maxTimeFile.getTimestamps() +" ]   对应:[ "+getTimeStapStr(maxTimeFile.getTimestamps()) +" ]");
        }
        if(cursorFile != null){
            System.out.println("游标Item的索引 ["+get8Padding((cursorIndex+1))+"] 时间戳: ["+cursorFile.getTimestamps() +" ]   对应:[ "+getTimeStapStr(cursorFile.getTimestamps()) +" ]");
        }else{
            System.out.println("游标Item的索引为空！！！  请添加新的资源！！！");

        }

        System.out.println();
        System.out.println("════════════"+"当前已下载Item列表长度:"+mDownloadBTFilesList.size()+"════════════");
        for (int i = 0; i < mDownloadBTFilesList.size() ; i++) {
            BTFile btItem = mDownloadBTFilesList.get(i);
            System.out.println("index["+get8Padding(i+1)+"]  时间:[ "+getTimeStapStr(btItem.getTimestamps())+" ]  大小[ "+getGBStr(btItem.getBtSize())+" ]  Value:【"+btItem.getValue()+"】");
        }


        System.out.println();
        System.out.println("════════════"+"当前未下载Item列表长度:"+needDownloadBTFilesList.size()+"════════════");
        for (int i = 0; i < needDownloadBTFilesList.size() ; i++) {
            BTFile btItem = needDownloadBTFilesList.get(i);
            System.out.println("index["+get8Padding(i+1)+"]  时间:[ "+getTimeStapStr(btItem.getTimestamps())+" ]  大小[ "+getGBStr(btItem.getBtSize())+" ]  Value:【"+btItem.getValue()+"】");
        }




        System.out.println();
        System.out.println();
        System.out.println();
        ArrayList<BTFile> nextStep =   getNextStepItemList(needDownloadBTFilesList);
        if(nextStep == null){
            System.out.println("════════════"+"NextStep移动索引情况[无资源,请增加资源]"+"════════════");
            System.out.println("当前已经没有可下载资源请添加下载资源!");
        }else{
            ArrayList<String> downloadUrlList = new   ArrayList<String>();
            String allSizeStr = getBTAllSizeGBStr(nextStep);
            int nextCount = nextStep.size();
            System.out.println("════════════"+"NextStep移动索引情况 数量[ "+nextCount+" ]  大小[ "+allSizeStr+" ]" +"════════════");

            for (int i = 0; i < nextStep.size(); i++) {
                BTFile btItem = nextStep.get(i);
                downloadUrlList.add(btItem.getDownUrl());
                System.out.println("NextStep下载索引 index["+get8Padding(i+1)+"]  时间:[ "+getTimeStapStr(btItem.getTimestamps())+" ]  大小[ "+getGBStr(btItem.getBtSize())+" ]  Value:【"+btItem.getValue()+"】");
            }
            System.out.println("该次资源NextStep的Cursor索引确认命令════════════════════════════════════");
            System.out.println("zbttool_F3.bat "+ (nextStep.get(nextStep.size()-1).getTimestamps()+1) );
            System.out.println("资源列表════════════════════════════════════");
            for (int i = 0; i < downloadUrlList.size(); i++) {
                System.out.println(downloadUrlList.get(i));
            }


        }

    }
    @SuppressWarnings("unchecked")
    static void  analysisAllBTFile(){

        ArrayList<BTFile> btFilesListArr = new ArrayList<BTFile>();
        btFilesListArr.addAll(btFileSet);
        btFilesListArr.sort(timeComp);
        if(btFilesListArr.size() == 0){
            System.out.println("!!!!!!!!当前已经没有可下载资源请添加下载资源!!!!!");
            return;
        }
        BTFile minTimeFile = btFilesListArr.get(0);  // 最久的记录
        BTFile maxTimeFile = btFilesListArr.get(btFilesListArr.size()-1);;  // 最新的记录
        BTFile cursorFile = null;   // 当前游标记录的还未完成下载的离现在最远的那个记录
        ArrayList<BTFile> needDownloadBTFilesList= new ArrayList<BTFile>();
        ArrayList<BTFile> mDownloadBTFilesList= new ArrayList<BTFile>();  // 已下载
        int cursorIndex = 0 ;
        System.out.println("════════════"+"当前保存总管长度:"+btFilesListArr.size()+"════════════");
        for (int i = 0; i < btFilesListArr.size(); i++) {
            BTFile btItem = btFilesListArr.get(i);
            if(!btItem.isHasDownloaded()){
                needDownloadBTFilesList.add(btItem);
            }else{
                mDownloadBTFilesList.add(btItem);
            }


            if(cursorFile == null && !btItem.isHasDownloaded()){
                cursorFile = btItem;
                cursorIndex = i;
            }
            setProperityValue(btItem.getKey(),btItem.getValue());
            System.out.println("index["+get8Padding(i+1)+"]  Key:【 "+btItem.getKey()+" 】"+"  Value:【 "+btItem.getValue()+" 】");
        }


        //sout
        System.out.println();
        System.out.println("════════════"+"当前下载Flag情况"+"════════════");
        if(minTimeFile != null){
            System.out.println("最久Item的索引 ["+get8Padding(1)+"] 时间戳: ["+minTimeFile.getTimestamps() +" ]   对应:[ "+getTimeStapStr(minTimeFile.getTimestamps()) +" ]");
        }
        if(maxTimeFile != null){
            System.out.println("最新Item的索引 ["+get8Padding(btFilesListArr.size())+"] 时间戳: ["+maxTimeFile.getTimestamps() +" ]   对应:[ "+getTimeStapStr(maxTimeFile.getTimestamps()) +" ]");
        }
        if(cursorFile != null){
            System.out.println("游标Item的索引 ["+get8Padding((cursorIndex+1))+"] 时间戳: ["+cursorFile.getTimestamps() +" ]   对应:[ "+getTimeStapStr(cursorFile.getTimestamps()) +" ]");
        }else{
            System.out.println("游标Item的索引为空！！！  请添加新的资源！！！");

        }

        System.out.println();
        System.out.println("════════════"+"当前已下载Item列表长度:"+mDownloadBTFilesList.size()+"════════════");
        for (int i = 0; i < mDownloadBTFilesList.size() ; i++) {
            BTFile btItem = mDownloadBTFilesList.get(i);
            System.out.println("index["+get8Padding(i+1)+"]  时间:[ "+getTimeStapStr(btItem.getTimestamps())+" ]  大小[ "+getGBStr(btItem.getBtSize())+" ]  Value:【"+btItem.getValue()+"】");
        }


        System.out.println();
        System.out.println("════════════"+"当前未下载Item列表长度:"+needDownloadBTFilesList.size()+"════════════");
        for (int i = 0; i < needDownloadBTFilesList.size() ; i++) {
            BTFile btItem = needDownloadBTFilesList.get(i);
            System.out.println("index["+get8Padding(i+1)+"]  时间:[ "+getTimeStapStr(btItem.getTimestamps())+" ]  大小[ "+getGBStr(btItem.getBtSize())+" ]  Value:【"+btItem.getValue()+"】");
        }




        System.out.println();
        System.out.println();
        System.out.println();
        ArrayList<BTFile> nextStep =   getNextStepItemList(needDownloadBTFilesList);
        if(nextStep == null){
            System.out.println("════════════"+"NextStep移动索引情况[无资源,请增加资源]"+"════════════");
            System.out.println("当前已经没有可下载资源请添加下载资源!");
        }else{
            ArrayList<String> downloadUrlList = new   ArrayList<String>();
            String allSizeStr = getBTAllSizeGBStr(nextStep);
            int nextCount = nextStep.size();
            System.out.println("════════════"+"NextStep移动索引情况 数量[ "+nextCount+" ]  大小[ "+allSizeStr+" ]" +"════════════");

            for (int i = 0; i < nextStep.size(); i++) {
                BTFile btItem = nextStep.get(i);
                downloadUrlList.add(btItem.getDownUrl());
                System.out.println("NextStep下载索引 index["+get8Padding(i+1)+"]  时间:[ "+getTimeStapStr(btItem.getTimestamps())+" ]  大小[ "+getGBStr(btItem.getBtSize())+" ]  Value:【"+btItem.getValue()+"】");
            }
            System.out.println("该次资源NextStep的Cursor索引确认命令════════════════════════════════════");
            System.out.println("zbttool_F3.bat "+ (nextStep.get(nextStep.size()-1).getTimestamps()+1) );
            StringBuilder btStringBuilder = new StringBuilder();

            System.out.println("资源列表(已复制到剪切板)════════════════════════════════════");
            for (int i = 0; i < downloadUrlList.size(); i++) {
                System.out.println(downloadUrlList.get(i));
                btStringBuilder.append(downloadUrlList.get(i)+"\n");
            }
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(btStringBuilder.toString()), null);

        }

    }

    static ArrayList<BTFile>  getNextStepItemList(ArrayList<BTFile> needDownList){
        ArrayList<BTFile> nextStepFile = new    ArrayList<BTFile>();
        if(needDownList.size() == 0){
            System.out.println("!!!!!!!!当前已经没有可下载资源请添加下载资源!!!!!");
            return null;
        }
        if(needDownList.size() <= 10){
            System.out.println("!!!!!!当前的剩余下载资源数 ["+needDownList.size()+"] 小于10个 将全部输出下载! 下载后将导致下次无下载资源,请及时更新下载资源!!!!!"  );
            return needDownList;
        }

        long allSize = 0;
        int allCount = 0 ;
        for (int i = 0; i < needDownList.size() ; i++) {
            BTFile btItem =  needDownList.get(i);
            if(allSize < 20000000000L && allCount <= 15){
                //  if(allSize < 10000000000L && allCount <= 10){ // 下一步的条件  大小大于20GB  并且最小数量大于15个   ，
                nextStepFile.add(btItem);
                allSize += btItem.getBtSize();
                allCount++;
            }
        }
        return  nextStepFile;
    }

    static String  getBTAllSizeGBStr(ArrayList<BTFile> nextStepList){
        long size = 0;
        for (int i = 0; i < nextStepList.size(); i++) {
            size+= nextStepList.get(i).getBtSize();
        }
        return getGBStr(size);

    }
    static String getGBStr(long size){
        if(size == 0){
            return "0.000GB";
        }
        NumberFormat nf = new DecimalFormat("0.000");
        String GBstr =    Double.parseDouble(nf.format((Double) (size / (1024d * 1024d * 1024d)))) + "GB";
        if(GBstr.length() == 6){
            GBstr = GBstr.replace("GB","0GB");
        }
        return GBstr;

    }


    static String get8Padding(int size){
        String sizeStr = " "+size;
        int length = 8 - sizeStr.length();
        if(length < 0){
            return sizeStr;
        }else{

            for (int i = 0; i < length; i++) {
                sizeStr = sizeStr + " ";
            }
        }
        return sizeStr;

    }

    static void    doCursorTimeStampStep(long timeStamp){
        ArrayList<BTFile> btFilesListArr = new ArrayList<BTFile>();
        ArrayList<BTFile> btFlagFilesListArr = new ArrayList<BTFile>();
        btFilesListArr.addAll(btFileSet);

        for (int i = 0; i < btFilesListArr.size(); i++) {
            BTFile btItem = btFilesListArr.get(i);
            if(!btItem.hasDownloaded && btItem.getTimestamps() < timeStamp){
                btItem.setHasDownloaded(true);
                btFlagFilesListArr.add(btItem);
                setProperityValue(btItem.getKey(),btItem.getValue());
            }

        }
        System.out.println("════════════"+"资源填充已下载[true]标志位 数量[ "+btFlagFilesListArr.size()+" ]════════════");
        for (int i = 0; i < btFlagFilesListArr.size(); i++) {
            BTFile btItem = btFlagFilesListArr.get(i);
            System.out.println("index["+get8Padding(i+1)+"] Downloaded-Flag:[ true ] 时间:[ "+getTimeStapStr(btItem.getTimestamps())+" ]  大小[ "+getGBStr(btItem.getBtSize())+" ]  Value:【"+btItem.getValue()+"】");
        }
    }

    @SuppressWarnings("unchecked")
    static void initDataFromProp(Map<String,String> propMap ){
        int     addItemCount = 1;
        ArrayList<String> logList = new    ArrayList<String>();
        Map.Entry<String, String> entry;
        if (propMap != null) {
            Iterator iterator = propMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String UrlKey =  entry.getKey();   // 属性名称
                String ValueStr  = entry.getValue();  // 表头名称
                long timeStamp = 0 ;
                String timeStampStr = "0";
                boolean hasDownLoadedFlag = false;
                long btSize = 0 ;
                String btSizeStr = "0";
                if(ValueStr.contains("_false_")){
                    timeStampStr =  ValueStr.substring(0,ValueStr.indexOf("_false_"));
                    btSizeStr =  ValueStr.substring(ValueStr.indexOf("_false_")+"_false_".length());
                    hasDownLoadedFlag = false;
                }else if(ValueStr.contains("_true_")){
                    timeStampStr =  ValueStr.substring(0,ValueStr.indexOf("_true_"));
                    btSizeStr =  ValueStr.substring(ValueStr.indexOf("_true_")+"_true_".length());
                    hasDownLoadedFlag = true;
                }
                timeStamp = Long.parseLong(timeStampStr);
                btSize =  Long.parseLong(btSizeStr);
                BTFile btItem=      new BTFile(UrlKey,timeStamp,hasDownLoadedFlag,btSize);
                btFileSet.add(btItem);
//                System.out.println("Prop文件保存项 ["+addItemCount+":" + btItem.getDesc());
                logList.add("Prop文件保存项 [ "+addItemCount+" ]:" + btItem.getDesc());
                addItemCount++;
            }
            logList.add(0,"════════════"+"prop文件路径"+F3_Properties_File.getAbsolutePath()+"当前保存数量【"+(addItemCount-1)+"】"+"════════════");
            for (int i = 0; i < logList.size() ; i++) {
                System.out.println(logList.get(i));
            }
            System.out.println();
            System.out.println();
        }
    }


    static void initDataFromImageQrFile(File dirFile){
        File[] allFile =  dirFile.listFiles();
        ArrayList<File> ImageFileList = new   ArrayList<File>();



        ArrayList<String> AllQrStrList = new  ArrayList<String>();


        for (int i = 0; i < allFile.length; i++) {
             if(allFile[i].getName().toLowerCase().endsWith(".jpg") || allFile[i].getName().toLowerCase().endsWith(".png")){
                ImageFileList.add(allFile[i]);
            }
        }


        for (int i = 0; i < ImageFileList.size() ; i++) {
            File ImageFile = ImageFileList.get(i);
            String qrStr = getQrCodeInfo(ImageFile);

            if (qrStr != null && !"".equals(qrStr)) {
                if (qrStr.startsWith(Magnet_PreHead)) {
                    AllQrStrList.add(qrStr);
                }
            }
        }

//        String btHashUrl = Magnet_PreHead+btFile.getHexHash().toUpperCase();
        int addItemCount = 1 ;
        ArrayList<String> logList = new    ArrayList<String>();

        for (int i = 0; i < AllQrStrList.size(); i++) {
            String btHashUrl = AllQrStrList.get(i);
            long timeStamp = System.currentTimeMillis() + i * 1000;
            boolean isDownloaded = false;
            long size =123456789L;

            if (allPropMap.containsKey(btHashUrl) && allPropMap.get(btHashUrl).contains("_true_")) {
                isDownloaded = true;
            }

            if(allPropMap.containsKey(btHashUrl)){   //  如果当前 prop 已经包含了 这个 url
                //  时间戳_true_size   时间戳_false_size
                String ValueStr = allPropMap.get(btHashUrl).trim();
                String timeStampStr = "0";
                if(ValueStr.contains("_false_")){
                    timeStampStr =  ValueStr.substring(0,ValueStr.indexOf("_false_"));
                }else if(ValueStr.contains("_true_")){
                    timeStampStr =  ValueStr.substring(0,ValueStr.indexOf("_true_"));
                }
                long timeStampStrLong = Long.parseLong(timeStampStr);
                if(timeStampStrLong != 0){
                    timeStamp = timeStampStrLong;
                }
            }

            if(!downloadKeySet.contains(btHashUrl)){  // 需要下载的  btHashUrl 集合 不包含 的 话
                downloadKeySet.add(btHashUrl);
                BTFile  btItem = new BTFile(btHashUrl,timeStamp,isDownloaded,size);
                btFileSet.add(btItem);
//                    System.out.println("从 文件路径 增加项 ["+addItemCount+"]:" +  btItem.getDesc());
                logList.add("从 二维码路径 增加项 ["+addItemCount+"]:" +  btItem.getDesc());
                addItemCount++;
            }

        }


        logList.add(0,"════════════"+"QrCode二维码图片路径"+dirFile.getAbsolutePath()+" 增加【"+logList.size()+"】"+"════════════");
        for (int i = 0; i < logList.size() ; i++) {
            System.out.println(logList.get(i));
        }
        System.out.println();
        System.out.println();




    }

    @SuppressWarnings("unchecked")
    public static String getQrCodeInfo(File srcFile) {
        String qrStr = "";

        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            BufferedImage bufferedImage = ImageIO.read(srcFile);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            //定义二维码参数
//            Map hints = new HashMap<>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            Result result = formatReader.decode(binaryBitmap, hints);
            Result result = formatReader.decode(binaryBitmap);
            qrStr =  result.getText();
           // System.out.println("解析二维码数据成功 对于  File---> " + srcFile.getAbsolutePath()+"  【"+qrStr+"】");
        } catch (Exception e){
            System.out.println("解析二维码数据失败 对于  File---> " + srcFile.getAbsolutePath());
        }
        return qrStr;

    }
    static void initDataFromBTFile(File dirFile){

        File[] allFile =  dirFile.listFiles();

        ArrayList<File> BTSimpleFileList = new   ArrayList<File>();


        ArrayList<String> logList = new    ArrayList<String>();

        for (int i = 0; i < allFile.length; i++) {
            if(allFile[i].getName().endsWith(".torrent")){
                BTSimpleFileList.add(allFile[i]);
            }
        }
        int addItemCount = 1 ;

        for (int i = 0; i < BTSimpleFileList.size(); i++) {
            try {

                // System.out.println(BTSimpleFileList.get(i).getName());
                TorrentFile btFile =     new TorrentFile(BTSimpleFileList.get(i));
                String btHashUrl = Magnet_PreHead+btFile.getHexHash().toUpperCase();

                long timeStamp = System.currentTimeMillis() + i * 1000;
                boolean isDownloaded = false;
                long size = btFile.getTotalLength();
                if (allPropMap.containsKey(btHashUrl) && allPropMap.get(btHashUrl).contains("_true_")) {
                    isDownloaded = true;
                }
                if(allPropMap.containsKey(btHashUrl)){
                    //  时间戳_true_size   时间戳_false_size
                    String ValueStr = allPropMap.get(btHashUrl).trim();
                    String timeStampStr = "0";
                    if(ValueStr.contains("_false_")){
                        timeStampStr =  ValueStr.substring(0,ValueStr.indexOf("_false_"));
                    }else if(ValueStr.contains("_true_")){
                        timeStampStr =  ValueStr.substring(0,ValueStr.indexOf("_true_"));
                    }
                    long timeStampStrLong = Long.parseLong(timeStampStr);
                    if(timeStampStrLong != 0){
                        timeStamp = timeStampStrLong;
                    }
                }

                if(!downloadKeySet.contains(btHashUrl)){
                    downloadKeySet.add(btHashUrl);
                    BTFile  btItem = new BTFile(btHashUrl,timeStamp,isDownloaded,size);
                    btFileSet.add(btItem);
//                    System.out.println("从 文件路径 增加项 ["+addItemCount+"]:" +  btItem.getDesc());
                    logList.add("从 文件路径 增加项 ["+addItemCount+"]:" +  btItem.getDesc());
                    addItemCount++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        logList.add(0,"════════════"+"文件路径"+dirFile.getAbsolutePath()+" 增加【"+logList.size()+"】"+"════════════");
        for (int i = 0; i < logList.size() ; i++) {
            System.out.println(logList.get(i));
        }
        System.out.println();
        System.out.println();


    }

    static String getTimeStapStr(long timestamp){
        String result  ="";
        Calendar calendar = Calendar.getInstance();
        long time = timestamp;
        calendar.setTimeInMillis(time);
        String yearStr =   get2Size(calendar.get(Calendar.YEAR))+"年" ;
        String monthStr  =   get2Size((calendar.get(Calendar.MONTH) + 1 ))+"月" ;
        String dayStr  =   get2Size(calendar.get(Calendar.DAY_OF_MONTH))+"日" ;
        String hourStr  = get2Size((calendar.get(Calendar.HOUR_OF_DAY)))+"时";
        String minuteStr  = get2Size(calendar.get(Calendar.MINUTE))+"分";
        String secondStr  = get2Size(calendar.get(Calendar.SECOND))+"秒";
        return yearStr+monthStr+dayStr+hourStr+minuteStr+secondStr;
    }


    static String get2Size(int value){
        String result = value+"";
        if(value >= 10){
            return result;
        }else {
            return "0"+result;
        }
    }

    static void initDataFromInputTxt(File inoutFile){

        ArrayList<String> BTSimpleURLList = new   ArrayList<String>();
        ArrayList<String> logList = new    ArrayList<String>();


        if(F3_INPUT_FILE.exists() && !F3_INPUT_FILE.isDirectory() ){
// 读取文件的每一行  非空行 添加到 ArrayList<String> 中
            BTSimpleURLList.addAll(ReadFileContent(F3_INPUT_FILE.getAbsolutePath()));
            int addItemCount = 1 ;
            for (int i = 0; i < BTSimpleURLList.size(); i++) {
                String simpleURL = BTSimpleURLList.get(i).trim();
                long size = 0;
                long timeStamp = System.currentTimeMillis() + i * 1000;
                boolean isDownloaded = false;
                if (allPropMap.containsKey(simpleURL) && allPropMap.get(simpleURL).contains("_true_")) {
                    isDownloaded = true;
                }
                if(allPropMap.containsKey(simpleURL)){
                    //  时间戳_true_size   时间戳_false_size
                    String ValueStr = allPropMap.get(simpleURL).trim();
                    String timeStampStr = "0";
                    if(ValueStr.contains("_false_")){
                        timeStampStr =  ValueStr.substring(0,ValueStr.indexOf("_false_"));
                    }else if(ValueStr.contains("_true_")){
                        timeStampStr =  ValueStr.substring(0,ValueStr.indexOf("_true_"));
                    }
                    long timeStampStrLong = Long.parseLong(timeStampStr);
                    if(timeStampStrLong != 0){
                        timeStamp = timeStampStrLong;
                    }
                }
                if(!downloadKeySet.contains(simpleURL)){
                    downloadKeySet.add(simpleURL);
                    BTFile btItem =  new BTFile(simpleURL,timeStamp,isDownloaded,size);
                    btFileSet.add(btItem);
//                    System.out.println("从 F3.txt 增加项 ["+addItemCount+"]:  " +  btItem.getDesc());
                    logList.add("从 F3.txt 增加项 ["+addItemCount+"]:  " +  btItem.getDesc());
                    addItemCount++;
                }
            }

            logList.add(0,"════════════"+"txt文件"+F3_INPUT_FILE.getAbsolutePath()+" 增加【"+logList.size()+"】"+"════════════");
            for (int i = 0; i < logList.size() ; i++) {
                System.out.println(logList.get(i));
            }
            System.out.println();
            System.out.println();


        }

    }
    static void setProperity() {
        try {
            F3_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(F3_Properties_File.getAbsolutePath()));
            F3_Properties.store(F3_Properties_OutputStream, "");
            F3_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void setProperityValue( String key ,String value) {
//        String valueStr = F3_Properties.getProperty(key); //   如果拿到的key 有value值 那么不执行操作
        F3_Properties.setProperty(key, "" + value);


    }


    // XXXXXXXX=timestamps_hasDownloaded_btSize
    static class BTFile{
        String downUrl;
        long timestamps;
        boolean hasDownloaded;
        long btSize;


        public BTFile(String downUrl, long timestamps, boolean hasDownloaded, long btSize) {
            this.downUrl = downUrl;
            this.timestamps = timestamps;
            this.hasDownloaded = hasDownloaded;
            this.btSize = btSize;
        }


        public String   getKey(){
            return downUrl;
        }

        public String   getValue(){
            return timestamps+"_"+hasDownloaded+"_"+btSize;
        }

        public String  getDesc(){
            return "Value【"+getValue()+"】  Key【"+getKey()+"】";
        }


        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public long getTimestamps() {
            return timestamps;
        }

        public void setTimestamps(long timestamps) {
            this.timestamps = timestamps;
        }

        public boolean isHasDownloaded() {
            return hasDownloaded;
        }

        public void setHasDownloaded(boolean hasDownloaded) {
            this.hasDownloaded = hasDownloaded;
        }

        public long getBtSize() {
            return btSize;
        }

        public void setBtSize(long btSize) {
            this.btSize = btSize;
        }
    }


    public static ArrayList<String>  ReadFileContent(String path) {

        String mFilePath = path;

        ArrayList<String> contentList = new ArrayList<String>();

        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("存在当前文件 "+ path);
        } else {
            System.out.println("不存在 当前文件 "+ path );
            return null;
        }
        System.out.println();

        if (curFile != null) {
            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                int index = 1;
                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }
                    contentList.add(oldOneLine);
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                    index++;


                }
                curBR.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return contentList;
    }

}
