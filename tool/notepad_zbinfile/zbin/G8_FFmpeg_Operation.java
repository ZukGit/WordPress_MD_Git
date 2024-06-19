import cn.hutool.system.JavaRuntimeInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoSize;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//
public class G8_FFmpeg_Operation {

    /*   FFMPEG的工具命令
---------------------------------------------------------------

        ffmpeg -i sky1.mp4 -b:a 128k output.mp3     // 把mp4文件的音频分离出来 单独生成 mp3 文件


        ffmpeg -i sky1.mp4  image%d.jpg     //  把 mp4视频文件每个帧 都抠出来

        ffmpeg -i sky1.mp4 -r 1 image%d.jpg   // 每秒输入一张图片
        ffmpeg -i a.avi example.%b.jpg


ffmpeg -i 1.mp4 -vf "rotate=90*PI/180:ow=ih:oh=iw" 2.mp4        // 顺时针旋转90度
ffmpeg -i 1.mp4 -vf "rotate=PI"      3.mp4          // 顺时针旋转180度
ffmpeg -i 1.mp4 -vf "rotate=270*PI/180:ow=ih:oh=iw"  4.mp4      // 顺时针旋转270度

        ffprobe.exe 123.mkv
        ffprobe.exe 1.mp3
        ffprobe.exe 1.mp4   // 查看文件信息

//转MP3为wav
        ffmpeg -i input.mp3 -acodec pcm_s16le -ac 2 -ar 44100 output.wav
//转m4a为wav
        ffmpeg -iinput.m4a -acodec pcm_s16le -ac 2 -ar 44100 output.wav
//wav与PCM的相互转换，把raw改为pcm即可
        ffmpeg -iinput.wav -f s16le -ar 44100 -acodec pcm_s16le output.raw
//PCM转wav
        ffmpeg -f s16le -ar 44100 -ac 2 -acodec pcm_s16le -i input.raw output.wav
        ffmpeg -i 1.mp4 -vf delogo=0:0:220:90:100:1 x1.mp4    // 去掉水印
        ---------------------------------------------------------------
*/


// 【Mp4截取 Gif 】 -ss  视频截取gif的起点  -t gif持续的时长  -i 输入  -r 帧率   -y 强制覆盖
// ffmpeg -ss 00:00:04 -t 3 -i 1.mp4 -r 15  1.gif -y



    //  把 mp4 文件转为 .ts 文件  并生成  .m3u8 播放列表   然后对文件内容 进行操作

    // 先创建文件夹   再 执行  解析命令  segment_time 指定 截取的每段时间长度为30秒
//    ffmpeg -i 1.mp4 -c:v copy -c:a copy -bsf:v h264_mp4toannexb -f ssegment -segment_list .\2020_10_26\2020_10_26_out.m3u 8 -segment_time 30 .\2020_10_26\2020_10_26_out%03d.ts

    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 G8 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    static String Cur_Bat_Name = "zmpeg_ffmpeg_G8";


/*******************修改属性列表 ------End *********************/



    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File G8_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name)+".properties");
    static InputStream G8_Properties_InputStream;
    static OutputStream G8_Properties_OutputStream;
    static Properties G8_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();

    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    //  固定4  从CMD窗口输入得到的 目录关键字参数
    // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径
    static String CUR_Dir_1_PATH = "";    //  arg[0] 就是shell路径 String 类型
    static File CUR_Dir_FILE ;   // 当前 CMDER的路径 File 文件


    // 固定5 从CMD窗口输入得到的 功能 tyoe 索引类型  以及依据索引 选中的 逻辑规则
    // 输入的第一个数值 是 rule的索引   同时搭配  * # 实现不同功能
    static String CUR_TYPE_2_ParamsStr;  //  arg[1] 就是输入的第一个参数  固定 通过 tip输出


    static int CUR_TYPE_INDEX = 0;   // 当前用户选中的 操作的类型  默认0-标识没有选中
    static Rule CUR_Selected_Rule ;    // 当前默认选中的 操作规则 这里实现了具体的操作逻辑

    // 固定6  从shell 中获取到的 除了 args[0] 和 args[1] 之外的所有其他的输入参数类型
    static ArrayList<String> CUR_INPUT_3_ParamStrList = new ArrayList<>();


    // 固定7  当前保存 Rule的集合
    static ArrayList<Rule> CUR_RULE_LIST = new ArrayList<Rule>();  // 规则的集合



    // 固定8 当前Shell目录下的 文件类型列表  抽取出来  通用  文件类型Str-文件及合
    static  HashMap<String, ArrayList<File>> CUR_Dir_FILETypeMap = new  HashMap<String, ArrayList<File>>(); ;





//  固定属性列表 ------End
    /*******************固定属性列表 ------End *********************/


    // 检测中文 编码序列
    static String REGEX_CHINESE = "[\u4e00-\u9fa5]";
    static JavaRuntimeInfo JavaRuntimeInfoValue =  new JavaRuntimeInfo();

    // PATH 环境变量值进行当前的保存处理
    static String EnvironmentValue=JavaRuntimeInfoValue.getLibraryPath();
    static String[] EnvironmentList=JavaRuntimeInfoValue.getLibraryPathArray();

    static java.text.DateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");


    static ArrayList<String> videoTypeList = new  ArrayList<String>();
    static {
        videoTypeList.add(".avi");
        videoTypeList.add(".mp4");
        videoTypeList.add(".rmvb");
        videoTypeList.add(".flv");
        videoTypeList.add(".mkv");
        videoTypeList.add(".m4a");
        videoTypeList.add(".mp3");
    }


    static ArrayList<String> mediaTypeList = new  ArrayList<String>();
    static {
        mediaTypeList.add(".avi");
        mediaTypeList.add(".mp4");
        mediaTypeList.add(".rmvb");
        mediaTypeList.add(".flv");
        mediaTypeList.add(".mp3");
        mediaTypeList.add(".wav");
        mediaTypeList.add(".aac");
        mediaTypeList.add(".mkv");
        mediaTypeList.add(".m4a");

    }


    static boolean  isContainEnvironment(String program){
        boolean environmentExist = false;
        if(EnvironmentValue.toLowerCase().contains(program.toLowerCase())){
            environmentExist = true;
        }

        if(!environmentExist){
            System.out.println("当前环境变量列表/n");
            for (int i = 0; i < EnvironmentList.length; i++) {
                System.out.println("环境变量索引: "+i+"   Value: "+EnvironmentList[i]);
            }
        }
        return environmentExist;
    }


    // ffmpeg  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\G8_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output2.mp4
    // D:\software\ffmpeg\bin
    // D:\software\ffmpeg\bin\ffmpeg.exe  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\G8_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output3.mp4
    static String  getEnvironmentExePath(String program){
        String exename = program.trim().toLowerCase();
        String executePath = null;
        if(CUR_OS_TYPE == OS_TYPE.Windows){
            for (int i = 0; i < EnvironmentList.length; i++) {
                String itemPath = EnvironmentList[i];
                String itemPathLower = itemPath.toLowerCase();

                if(itemPathLower.contains(exename)){
                    executePath =   itemPath + File.separator + program + (CUR_OS_TYPE==OS_TYPE.Windows? ".exe" : "");
                    break;
                }
            }

        } else { // 在 Mac 和 Linux 下 不使用全路径 而直接使用命令  ffmpeg

            executePath =   "ffmpeg ";

        }


        return executePath;
    }


    // A1  ..... A2.
    static String get_Bat_Sh_FlagNumber(String mCur_Bat_Name){
        String mCharNumber = "error";
        String curBat =mCur_Bat_Name;
        if(mCur_Bat_Name.contains(".sh")){
            curBat = curBat.replace(".sh","");
        }

        if(mCur_Bat_Name.contains(".bat")){
            curBat = curBat.replace(".bat","");
        }
        if(curBat.contains("_")){
            String[] arrNameList =    curBat.split("_");
            mCharNumber =   arrNameList[arrNameList.length-1];
        }else{
            mCharNumber = curBat;
        }

        return mCharNumber;
    }


    static {
        try {
            if (!G8_Properties_File.exists()) {
                G8_Properties_File.createNewFile();
            }
            G8_Properties_InputStream = new BufferedInputStream(new FileInputStream(G8_Properties_File.getAbsolutePath()));
            G8_Properties.load(G8_Properties_InputStream);
            Iterator<String> it = G8_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + G8_Properties.getProperty(key));
                propKey2ValueList.put(key, G8_Properties.getProperty(key));
            }
            G8_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            G8_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(G8_Properties_File.getAbsolutePath()));
            G8_Properties.store(G8_Properties_OutputStream, "");
            G8_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }




    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            Cur_Bat_Name = Cur_Bat_Name+".bat";
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name+".sh";
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name+".sh";
        }
    }








    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }



    void InitRule(){

        //   加入类型一一对应的 那些 规则
        //  提供一个 mp4 文件  输入该文件的两倍长度的文件
        CUR_RULE_LIST.add( new MergeMP4_Rule_1());

        //  提供一个 mp4 rmvb avi flv 文件  输入该文件音频mp3文件
        CUR_RULE_LIST.add( new GETMP3_Rule_2());

        // 传入视频文件  获取该视频文件的 所有 图片帧
        CUR_RULE_LIST.add( new GETJPGFrame4Video_Rule_3());

//  把当前 mp4 avi flv rmvb 视频 旋转90度输出 sky.mp4
        CUR_RULE_LIST.add( new VideoRoast_Rule_4());

//  对当前 给定的 Mp4文件进行切割为ts文件  文件结构为  当前目录 ./out 【输出文件夹 包含m3u8 文件】  ./out/TS_Dir 【TS文件的输出文件夹  包含 TS 文件】
        CUR_RULE_LIST.add( new MP4_To_TS_Rule_5());

// UC 本地化  使 绝对路径转为 相对路径  方便查看  adb pull  /storage/emulated/0/UCDownloads/VideoData/ .  【   /storage/emulated/0/UCDownloads/VideoData/  转为 .  】
        CUR_RULE_LIST.add( new UC_OutPut_TS_Localized_6());



// ffmpeg -ss 00:00:00  -accurate_seek  -to 00:00:10  -i 1.mp4 -codec copy 1_output.mp4   //  截取视频
        CUR_RULE_LIST.add( new CutDown_Video_Rule_7());


        //  把当前的 mov文件转为 mp4文件 输出到本地文件夹
        CUR_RULE_LIST.add( new MOV_Revert_MP4_Rule_8());

        // 把当前目录下的 Mp4 生成缩略图  保存到  本地  生成在 目录 SuoTu_Mp4_时间戳 目录中
        CUR_RULE_LIST.add( new CaptureSuoLueTu_From_MP4_Rule_9());


        // 批量旋转当前目录下的 mp4 文件
        CUR_RULE_LIST.add( new Batch_VideoRoast_Rule_10());


        // 转换 mp4 指定时刻 为 gif 指定持续的长度的时间
        CUR_RULE_LIST.add( new Revert_MP4_To_Gif_Rule_11());


        //  合并 多个 mp4 文件到  一个 mp4 文件中 
        CUR_RULE_LIST.add( new Concat_MulMp4_To_OneMp4_Rule_12());

        
     // 一个输入 模糊 多个文件输出   
     // ffmpeg -ss 00:00:00  -accurate_seek  -to 00:00:10  -i 1.mp4 -codec copy 1_output.mp4   //  截取视频
        CUR_RULE_LIST.add( new CutDown_MultiVideoOut_Rule_13());
        

    }
    
    
    
    // ffmpeg -ss 00:00:00  -accurate_seek  -to 00:00:10  -i 1.mp4 -codec copy 1_output.mp4   //  截取视频
    class CutDown_MultiVideoOut_Rule_13 extends  Basic_Rule{
        ArrayList<File> mInputMediaFileList ;  // 输入的 视频文件

        File targetInputMP4File ;  // 输入的 Mp4文件
        
        
        // 通过计算得到的 需要 多次截图的信息集合 列表
        ArrayList<CutVideo_Info>  outVideoInfoList ;  
        HashMap<String,CutVideo_Info> originAbsPath_CutVideo_Map ; 
        
        
        String beginTimeStr;    // 外部输入的开始时间字符串
        String endTimeStr;      // 外部输入的结束时间字符串
        String outputFileName;  // 输出文件的名称
        int stepInterval = 500 ; // 毫秒   step_500 间隔
        File outputDirFile ;   // 输出文件的目录  用于检测 输出的文件大小 

        int mFootNum  = 6 ; // (-1,0,1)(-1,0,1)给个方向 前进的步数 目前默认为6
        
        
        CutDown_MultiVideoOut_Rule_13(){
            super(13);
            mInputMediaFileList = new  ArrayList<File>();
            outVideoInfoList  = new  ArrayList<CutVideo_Info>();
            originAbsPath_CutVideo_Map = new HashMap<String,CutVideo_Info>();
            stepInterval = 500 ;
            mFootNum = 6 ;
        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return
                    "\n"+Cur_Bat_Name+ "  13   10-     1.mp4      <mp4,flv,avi.rmvb 路径>      ## 秒数往后截取视频   \n"+
                            "\n"+Cur_Bat_Name+ "  13   -100     1.mp4          <mp4,flv,avi.rmvb 路径>    ## 秒数往后截取视频   \n"+
                            "\n"+Cur_Bat_Name+ "  13  10-50     1.mp4          <mp4,flv,avi.rmvb 路径>    ## 秒数往后截取视频   \n"+
                            "\n"+Cur_Bat_Name+ "  13  01:10-    1.mp4          <mp4,flv,avi.rmvb 路径>    ## 分钟数往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  13  -01:10    1.mp4         <mp4,flv,avi.rmvb 路径>    ## 分钟数往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  13   01:10-02:50  stepms_500   1.mp4      <mp4,flv,avi.rmvb 路径>    ## 分钟数往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  13   00:00:10-    stepms_500   1.mp4      <mp4,flv,avi.rmvb 路径>    ## 时分秒往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  13   -00:00:10    stepms_500   1.mp4     <mp4,flv,avi.rmvb 路径>    ## 时分秒往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  13   00:00:10-00:00:50  stepms_800 1.mp4  <mp4,flv,avi.rmvb 路径>    ## 时分秒往后截取视频 \n" +
                            "\n"+Cur_Bat_Name+ "  13   00:00:10-00:00:50  footnum_10 stepms_800 1.mp4  <mp4,flv,avi.rmvb 路径>    ## 往方向生成footnum个视频(当前默认 6 步)时分秒往后截取视频 \n"; }



        // ffmpeg -ss 00:00:00  -accurate_seek  -to 00:00:10  -i 1.mp4 -codec copy 1_output.mp4    //  截取视频
        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule13 shellDir = "+ shellDir);
            System.out.println("rule13  otherParams = "+ otherParams.size());


            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }



            System.out.println("rule13 otherParams.size() = "+ otherParams.size());

            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                
                if(curStringItem.startsWith("footnum_")){
                    String mFootNumStr  = curStringItem.replace("footnum_", "");
                    if(isNumeric(mFootNumStr)) {
                    	mFootNum = Integer.parseInt(mFootNumStr);
                    }
                    continue;
                }
                
                
                if(curStringItem.startsWith("stepms_")){
                    String stepMsStr  = curStringItem.replace("stepms_", "");
                    if(isNumeric(stepMsStr)) {
                    	stepInterval = Integer.parseInt(stepMsStr);
                    }
                    continue;
                }
                
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("curAbsPath  = "+ curAbsPath);
                if(curFIle.exists() && videoTypeList.contains(getFileTypeWithPoint(curFIle.getName())) ){  // 判断
                    mInputMediaFileList.add(curFIle);
                }
            }
            if(mInputMediaFileList.size() == 0){
                errorMsg = "当前从参数找不到对应的输入源 .mp4  .flv .rmvb .avi 文件 ";
                System.out.println(errorMsg);
                return false;
            }
            System.out.println("rule13 checkParamsOK mInputMediaFileList.size() = "+ mInputMediaFileList.size());
            targetInputMP4File = mInputMediaFileList.get(mInputMediaFileList.size()-1);
            String tagFlag = otherParams.get(0);
            System.out.println("targetInputMP4File = "+ targetInputMP4File.getAbsolutePath());
            System.out.println("tagFlag = "+ tagFlag);

            if(!tagFlag.contains("-"))
            {
                System.out.println("输入的 时间标识字符串 " +tagFlag +" 不包含分割符号 -  无法识别! 程序退出!");
                return false;
            }

            if(tagFlag.startsWith("-")){
                tagFlag = "00:00:00"+tagFlag;
            }

            if(tagFlag.endsWith("-")){
                tagFlag = tagFlag+ReadVideoTime(targetInputMP4File);
            }

            String[] tagArr = tagFlag.split("-");
            if(tagArr == null || tagArr.length != 2){
                System.out.println("tagFlag = "+ tagFlag +" 分割符号 - 数组为空 或者长度不为2  程序退出!  tagArr.length = " + tagArr.length);
                return false;
            }



            String pre_Str = tagArr[0];
            if(!"".equals(pre_Str.trim())){
                beginTimeStr =   fixedTimeStr(pre_Str);
            }else{
                beginTimeStr =  "00:00:00";
            }



            String end_Str = tagArr[1];
            if(!"".equals(end_Str.trim())){
                endTimeStr =   fixedTimeStr(end_Str);
            }else{
                endTimeStr =  ReadVideoTime(targetInputMP4File);
            }


            String originName = targetInputMP4File.getName();
            String typeStr = getFileTypeWithPoint(originName);
            String fileNameOnly = getFileNameNoPoint(originName);

            outputFileName = fileNameOnly+"_"+beginTimeStr.replace(":","")+"_"+endTimeStr.replace(":","")+"_"+System.currentTimeMillis()/1000+typeStr;
            outputFileName = outputFileName.replace(" ","");
            String beginTemp1 = beginTimeStr.replace(":","");
            String endTemp1 = endTimeStr.replace(":","");
            int beginTemp1_int = Integer.parseInt(beginTemp1);
            int endTemp1_int = Integer.parseInt(endTemp1);

            // D:\zsoft\win_env_zip\ffmpeg\ffmpeg.exe
            // -ss 00:00:00 -accurate_seek  -to 00:00:10  -i "1.mp4"  
            // -codec copy -avoid_negative_ts 1 1_000000_000010_1718186515.mp4
            
            // D:\zsoft\win_env_zip\ffmpeg\ffmpeg.exe -ss 00:00:00.600 -accurate_seek  -to 00:00:10.100  -i "1.mp4"   -codec copy -avoid_negative_ts 1 1_000000_000010_1718186515.mp4
            
            System.out.println("beginTimeStr = "+ beginTimeStr +"   endTimeStr = "+ endTimeStr  +"   outputFileName =  "+ outputFileName  + "targetInputMP4File = "+ targetInputMP4File.getName());

            if(beginTemp1_int > endTemp1_int){
                System.out.println("开始时间大于结束时间! 请检查参数!   beginTimeStr = "+ beginTimeStr  +"       endTimeStr = "+ endTimeStr);
            }

            String outDirPath = targetInputMP4File.getParentFile().getAbsolutePath()+File.separator+"zzzz_"+getTimeStamp_yyyyMMdd_HHmmss()+"_"+targetInputMP4File.getName();
            outDirPath = outDirPath.replace(" ", "");
        	
            
            outputDirFile = new File(outDirPath);
            
            long video_file_endtime_millsecond_long = ReadVideoTimeWithMillSecond(targetInputMP4File) ;
            // 进行 多文件的参数的计算 
            
            // 把 自身 00 00 加入
            outVideoInfoList.add(calCutVideoInfo(beginTimeStr,endTimeStr,-1,-1,0,video_file_endtime_millsecond_long,targetInputMP4File));
            
            
            //  8 个 组别
            for (int i = 0; i < 8 ; i++) {
				
            	// 多产生 ， 最后生成 后 还要检测大小是否相同,只保留一个size 一致的
 
                // 00:00:10      00:00:20
                //outnum_20   step_500毫秒 , 可以通过外部获取
                // group_num = 0 ..... 7 
                // step
                // count 第几次  余%6
                // beginstring
                // endString 
                
                // out_name   
                
                // 每个 组 有 6 个 数据
                for (int j = 0; j < mFootNum; j++) {
                	
                	CutVideo_Info cutInfo = calCutVideoInfo(beginTimeStr,endTimeStr,i,j,stepInterval,video_file_endtime_millsecond_long,targetInputMP4File);
           
                    outVideoInfoList.add(cutInfo);
                    
                    originAbsPath_CutVideo_Map.put(cutInfo.AbsPath, cutInfo);
				}

                
			}
            
  
            System.out.println("outVideoInfoList.size=【"+outVideoInfoList.size()+"】");
            

            
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }

        
        
        
        
        //Group【0】_______________________ ←【A】   B____________
        //【1】 00:00:09.500  00:00:20   
        //【2】 00:00:09.000  00:00:20
        //【3】 00:00:08.500  00:00:20
        //【4】 00:00:08.000  00:00:20
        //【5】 00:00:07.500  00:00:20
        //【6】 00:00:07.000  00:00:20
        //Group【1】_______________________ 【A】→   B____________
        //【7】 00:00:10.500  00:00:20
        //【8】 00:00:11.000  00:00:20
        //【9】 00:00:11.500  00:00:20
        //【A】 00:00:12.000  00:00:20
        //【B】 00:00:12.500  00:00:20
        //【C】 00:00:13.000  00:00:20
        
        //Group【2】_______________________ A   ←【B】  ____________ 

        //【1】 00:00:10.000  00:00:19.500   
        //【2】 00:00:10.000  00:00:19.000
        //【3】 00:00:10.000  00:00:18.500
        //【4】 00:00:10.000  00:00:18.000
        //【5】 00:00:10.000  00:00:17.500
        //【6】 00:00:10.000  00:00:17.000

        //Group【3】_______________________  A   【B】→____________
        //【1】 00:00:10.000  00:00:20.500   
        //【2】 00:00:10.000  00:00:21.000
        //【3】 00:00:10.000  00:00:21.500
        //【4】 00:00:10.000  00:00:22.000
        //【5】 00:00:10.000  00:00:22.500
        //【6】 00:00:10.000  00:00:23.000
        
        
        //Group【4】_______________________  ←【A】   【B】→____________
        //【1】 00:00:09.500  00:00:20.500   
        //【2】 00:00:09.000  00:00:21.000
        //【3】 00:00:08.500  00:00:21.500
        //【4】 00:00:08.000  00:00:22.000
        //【5】 00:00:07.500  00:00:22.500
        //【6】 00:00:07.000  00:00:23.000

        //Group【5】_______________________  【A】→   ←【B】____________
        //【7】 00:00:10.500  00:00:19.500
        //【8】 00:00:11.000  00:00:19.000
        //【9】 00:00:11.500  00:00:18.500
        //【A】 00:00:12.000  00:00:18.000
        //【B】 00:00:12.500  00:00:17.500
        //【C】 00:00:13.000  00:00:17.000

        //Group【6】_______________________  ←【A】   ←【B】____________ 
        //【7】 00:00:09.500  00:00:19.500
        //【8】 00:00:09.000  00:00:19.000
        //【9】 00:00:08.500  00:00:18.500
        //【A】 00:00:08.000  00:00:18.000
        //【B】 00:00:07.500  00:00:17.500
        //【C】 00:00:07.000  00:00:17.000
        
        //Group【7】_______________________  【A】→   【B】→____________    
        //【1】 00:00:10.500  00:00:20.500   
        //【2】 00:00:11.000  00:00:21.000
        //【3】 00:00:11.500  00:00:21.500
        //【4】 00:00:12.000  00:00:22.000
        //【5】 00:00:12.500  00:00:22.500
        //【6】 00:00:13.000  00:00:23.000

        CutVideo_Info   calCutVideoInfo(String beginTimeStr , String endTimeStr  , int groupIndex , int arrayIndex , int stepInterval , long videoAllMillSecond, File matchFile){
        	// -1 左边    0不动    1 中间
        	int A_dir = 0 ;
        	int B_dir = 0 ;
        	CutVideo_Info  info = new CutVideo_Info();
        	
        	// 00:00:10   10
        	// 00:20:00   2000
        	// 02:03:04   20304
        	long inputBeginTime_MillSecond = calVideoStringTimeAsMillSecond(beginTimeStr);
        	
        	long inputEndTime_MillSecond = calVideoStringTimeAsMillSecond(endTimeStr);

            
            //Group【0】_______________________ ←【A】   B____________
        	if(groupIndex == 0 ) {
        		A_dir = -1;
        		B_dir = 0;	
            //Group【1】_______________________ 【A】→   B____________
        	} else if(groupIndex == 1 ) {
        		A_dir = 1;
        		B_dir = 0;	
            //Group【2】_______________________ A   ←【B】  ____________ 
        	}else if(groupIndex == 2 ) {
        		A_dir = 0;
        		B_dir = -1;	
             //Group【3】_______________________  A   【B】→____________
        	}else if(groupIndex == 3 ) {
        		A_dir = 0;
        		B_dir = 1;	
            //Group【4】_______________________  ←【A】   【B】→____________
        	}else if(groupIndex == 4 ) {
        		A_dir = -1;
        		B_dir = 1;	
            //Group【5】_______________________  【A】→   ←【B】____________
        	}else if(groupIndex == 5 ) {
        		A_dir = 1;
        		B_dir = -1;	
            //Group【6】_______________________  ←【A】   ←【B】____________ 	
        	}else if(groupIndex == 6 ) {
        		A_dir = -1;
        		B_dir = -1;	
            //Group【7】_______________________  【A】→   【B】→____________    
        	}else if(groupIndex == 7 ) {
        		A_dir = 1;
        		B_dir = 1;		
        	}
        	// 3000 毫秒
        	long A_allStep = A_dir * arrayIndex * stepInterval;
        	long B_allStep = B_dir * arrayIndex * stepInterval;
        	
        	
        	long A_result_millsecond = inputBeginTime_MillSecond + A_allStep;
        	
        	if(A_result_millsecond < 0 ) {
        		A_result_millsecond = 0 ;
        	}
        	long B_result_millsecond = inputEndTime_MillSecond + B_allStep;
        	
        	if(B_result_millsecond > videoAllMillSecond  ) {
        		
        		B_result_millsecond  = videoAllMillSecond;
        	}

        	System.out.println("beginTimeStr="+beginTimeStr+"   inputBeginTime_MillSecond = "+ inputBeginTime_MillSecond +"   endTimeStr="+endTimeStr+"   inputEndTime_MillSecond="+inputEndTime_MillSecond+" groupIndex【"+groupIndex+"】 arrayIndex【"+arrayIndex+"】  stepInterval【"+stepInterval+"】  videoAllMillSecond【"+videoAllMillSecond+"】  A_result_millsecond【"+A_result_millsecond+"】 B_result_millsecond【"+B_result_millsecond+"】");

        	
        	// 00:00:10   10    10000、
        	// 00:20:00   2000
        	// 02:03:04   20304
        	
        	String A_result_timeString = calTimeMillSecondAsString(A_result_millsecond);
        	
        	String B_result_timeString = calTimeMillSecondAsString(B_result_millsecond);

        	
        	//  把  毫秒 转为  01:02:03.456 这样子
        	
        	info.mBeginTimeStr = A_result_timeString;
        	
        	
        	info.mEndTimeStr = B_result_timeString;
        	
        	info.yyyyMMdd_HHmmssSSS  = getTimeStamp_yyyyMMdd_HHmmssSSS();
        	
        	
        	String fileName = outputDirFile.getAbsolutePath()+File.separator+A_dir+"_"+B_dir+"_"+arrayIndex+"_"+info.yyyyMMdd_HHmmssSSS+"_" +matchFile.getName();
        	
        	info.AbsPath =  fileName.replaceAll(" ", "");
	
        	
        	info.parentDirFile = outputDirFile;
        	System.out.println("Group【"+groupIndex+"】Array【"+arrayIndex+"】_____________A_result_timeString【"+A_result_timeString+"】  B_result_timeString【"+B_result_timeString+"】 Path: "+info.AbsPath);

        	
        	return info;
        }

        @Override
        void operationRule(ArrayList<String> inputParamsList) {


            System.out.println("beginTimeStr = "+ beginTimeStr +"   endTimeStr = "+ endTimeStr  +"   outputFileName =  "+ outputFileName  + "targetInputMP4File = "+ targetInputMP4File.getName());



            //     ffmpeg -i sky1.mp4  image%d.jpg    抠图

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule13 curInputFileList.size() = "+mInputMediaFileList.size());
            System.out.println("rule13 ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt


            System.out.println("═══════════════════════════ outVideoInfoList.size【"+outVideoInfoList.size()+"】═════════════");
            for (int i = 0; i < outVideoInfoList.size(); i++) {
				
            	
            	CutVideo_Info curVideoInfo = outVideoInfoList.get(i);
            	 if(!curVideoInfo.parentDirFile.exists()) {
            		 
            		 curVideoInfo.parentDirFile.mkdirs();
            	 }
            	
             	System.out.println("═════【"+i+"_"+ outVideoInfoList.size()+"】 begin["+curVideoInfo.mBeginTimeStr+"] end["+curVideoInfo.mEndTimeStr+"]  path["+curVideoInfo.AbsPath+"]");

                String command = ffmpeg_path +" -ss "+curVideoInfo.mBeginTimeStr  + " -accurate_seek  -to " + curVideoInfo.mEndTimeStr +"  -i " + "\""+targetInputMP4File.getName()+ "\"" +" "+ "  -codec copy -avoid_negative_ts 1 "+ curVideoInfo.AbsPath;
                execCMD(command);
                
                System.out.println("裁剪输出文件完成 -》 " + curVideoInfo.AbsPath);
                
			}
            
            
          File[] subFileList =   outputDirFile.listFiles();
          
          System.out.println("subFileList.length = "+ subFileList.length);
            
          ArrayList<Long> fileSizeList = new  ArrayList<Long> ();
          
          // 删除重复的文件 
          for (int i = 0; i < subFileList.length; i++) {
        	  File curFile = subFileList[i];
        	  if(curFile.exists() && curFile.length() > 0) {
        		  if(fileSizeList.contains(curFile.length())) {
        			  
        		      System.out.println("subFile["+i+"_"+subFileList.length+"] size["+curFile.length()+"] = "+ curFile.getAbsolutePath()+" will be delete!");

        		      
        			  curFile.delete();
        		  } else {
        			  
        			  fileSizeList.add( curFile.length());
        		  }
        	  }
		}
          
          // 重命名 更为有意义的文件名称
          
          File[] mExistFileList =   outputDirFile.listFiles();
          
          System.out.println("mExistFileList.length = "+ mExistFileList.length);
            
          HashMap<File,String> renameMap = new HashMap<File,String>();
        
          for (int i = 0; i < subFileList.length; i++) {
        	  File curFile = subFileList[i];
        	  if(curFile.exists() && curFile.length() > 0) {
        		  
        		  if(curFile.getName().toLowerCase().endsWith(".mp4")) {
        			  
        			 long timeStamp = ReadVideoTimeWithMillSecond(curFile);
        			 
        			 String short_videotime_str = calTimeMillSecondAsShortString(timeStamp);
        			 
        			 CutVideo_Info matchVideoInfo = originAbsPath_CutVideo_Map.get(curFile.getAbsolutePath());
        			 
        			 if(matchVideoInfo != null) {
        				 
        				 String newPartName = short_videotime_str+"_" +matchVideoInfo.yyyyMMdd_HHmmssSSS;
        				 
        				 String oldName = curFile.getName();
        				 String newName = oldName.replace(matchVideoInfo.yyyyMMdd_HHmmssSSS, newPartName);
        				 
        				 if(newName != null && !"".equals(newName.trim()) && newName.trim().length() > 0 ) {
        					 
        					 
        					 renameMap.put(curFile, newName);
//        					 tryReName(curFile, newName);
        					 
        				 }
        				 
        			 }

        		  }
        	
        	  }
		}
          
          
          try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
          Map.Entry<File, String> entry;
          Iterator iterator = renameMap.entrySet().iterator();
          while (iterator.hasNext()) {
              entry = (Map.Entry<File, String>) iterator.next();
              File oriFile = entry.getKey();  //Map的Value
              String newName = entry.getValue();  //Map的Value
         	  tryReName(oriFile, newName);
          }
          
          
            
            
            // ffmpeg -ss 00:00:00  -accurate_seek  -to 00:00:10  -i 1.mp4 -codec copy 1_output.mp4
        

   

       

/*            for (int i = 0; i < mInputMediaFileList.size(); i++) {

                File mp4File = mInputMediaFileList.get(i);
                StringBuilder sb =new StringBuilder();

                String originName = mp4File.getName();
                String noPointFileName = getFileNameNoPoint(originName);
                String type = getFileTypeWithPoint(mp4File.getName());
//                File jpgDirFile = new File(CUR_Dir_1_PATH+File.separator+noPointFileName+"_"+DateFormat.format(new Date())+File.separator);
//                jpgDirFile.mkdirs();
//                String newFileName = originName+"_mp3_"+DateFormat.format(new Date())+".mp3";
                String newFileName = originName.replace(type,"_"+"_"+DateFormat.format(new Date())+type);
//                String newFileName = mp4File.getName().replace(".mp4","_x"+bigNum+"_"+DateFormat.format(new Date())+".mp4");     //  新的文件的名称  2.mp4 2_mergedxxxxxxxxxx.mp4
//                String imageStr = noPointFileName+"_%d.jpg";
//                String absImagePath = jpgDirFile.getAbsolutePath()+File.separator+imageStr;
                String newFileAbsPath = mp4File.getParentFile().getAbsolutePath()+File.separator + newFileName;

*//*                ffmpeg -i 1.mp4 -vf "rotate=90*PI/180" 2.mp4        // 顺时针旋转90度
                ffmpeg -i 1.mp4 -vf "rotate=PI"      3.mp4          // 顺时针旋转180度
                ffmpeg -i 1.mp4 -vf "rotate=270*PI/180"  4.mp4      // 顺时针旋转270度*//*

                String command = "";

//                    command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() + " -vf \"rotate="+rotate+"*PI/180\" " + newFileAbsPath;


                System.out.println(command);
                execCMD(command);
            }*/



        }



    }


    
    class CutVideo_Info{
    	
        String mBeginTimeStr;
        String mEndTimeStr;
        String AbsPath;  // 输出文件的完整的名称
        File parentDirFile;
        String yyyyMMdd_HHmmssSSS;   // 用于替换  202414142232 替换为 20s_202414142232
        String mVideoTimeStr; // 视频播放时长的字符串 1h2m3s 这样的字符串
    	
    }

    

    class Concat_MulMp4_To_OneMp4_Rule_12 extends  Basic_Rule{
        ArrayList<File> mInputMP4FileList ;  // 输入的 视频文件

        String inputFileType ;


        Concat_MulMp4_To_OneMp4_Rule_12(){
            super(12);
            mInputMP4FileList = new  ArrayList<File>();
        }

        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule2 shellDir = "+ shellDir);
            System.out.println("rule2  otherParams = "+ otherParams.size());


            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }


            System.out.println("otherParams.size() = "+ otherParams.size());

            ArrayList<String> fileTypeList = new  ArrayList<String> ();

            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("curAbsPath  = "+ curAbsPath);
                String fileType = getFileTypeWithPoint(curFIle.getName()).trim().toLowerCase();

                fileTypeList.add(fileType);
                inputFileType = fileType;

                if(curFIle.exists() && videoTypeList.contains(fileType) ){  // 判断

//                  if(curFIle.exists() && "mp4".equals(getFileTypeWithPoint(curFIle.getName()).toLowerCase().trim()) ){  // 判断
                    mInputMP4FileList.add(curFIle);
                }

            }
            if(mInputMP4FileList.size() == 0){
                errorMsg = "当前从参数找不到对应的输入源 .mp4   文件   请检查 ! ";
                System.out.println(errorMsg);
                return false;
            }

            if(mInputMP4FileList.size() == 1){
                errorMsg = "当前从参数只找到一个输入 mp4 文件 "+mInputMP4FileList.get(0).getAbsolutePath()+"   无法完成视频的合并操作    请检查 ! ";
                System.out.println(errorMsg);
                return false;
            }

            if(inputFileType == null) {
                errorMsg = "当前从参数 中计算得到的类型  inputFileType="+inputFileType+" 为空    无法完成视频的合并操作    请检查 ! ";
                System.out.println(errorMsg);
                return false;

            }

            for (int i = 0; i < fileTypeList.size(); i++) {
                String  fileType_item = fileTypeList.get(i);

                System.out.println("input_type["+i+"] = "+ fileType_item);
                if(!inputFileType.equals(fileType_item)) {

                    errorMsg = "当前从参数 中计算得到的类型列表不一致  inputFileType="+inputFileType+"  "+fileType_item+"=fileType_item"+"   无法完成视频的合并操作    请检查 ! ";
                    System.out.println(errorMsg);
                    return false;

                }
            }

            System.out.println("rule"+rule_index+" checkParamsOK mInputMP4FileList.size() = "+ mInputMP4FileList.size() +" inputFileType="+inputFileType);
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return  "\n"+Cur_Bat_Name+ "  "+index+"    <mp4_1路径>    <mp4_2路径>      ## 把当前 mp4_1 mp4_2 mp4_3 ... 合并为一个文件输出  \n" ;
        }


        @Override
        void operationRule(ArrayList<String> inputParamsList) {




            //  ffmpeg -f concat -i filelist.txt -c copy output.mkv     // 把mp4文件的音频分离出来 单独生成 mp3        文件

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule"+rule_index+" curInputFileList.size() = "+mInputMP4FileList.size());
            System.out.println("rule"+rule_index+" ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt



            File ffmpeg_txt_inputfile = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+"Concat_FFmpeg_"+getTimeStamp_yyyyMMdd_HHmmss()+".txt");


            File output_file = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+"out_cancat_"+getTimeStamp_yyyyMMdd_HHmmss()+inputFileType);



            // file '绝对路径input1.mkv'    构建全局的 file '绝对路径input1.mkv'

            ArrayList<String> input_file_list =new  ArrayList<String>();

            for (int i = 0; i < mInputMP4FileList.size(); i++) {

                File mp4File = mInputMP4FileList.get(i);

//                String mp4_abs_path =   mp4File.getAbsolutePath();
                String mp4_abs_path =   mp4File.getName();
                input_file_list.add("file '"+mp4_abs_path+"'");

            }

            writeContentToFile(ffmpeg_txt_inputfile, input_file_list);



            //  ffmpeg -f concat -i filelist.txt -c copy output.mkv     // 把mp4文件的音频分离出来 单独生成 mp3        文件

            String command_concat = ffmpeg_path+ " -f concat -i "+ffmpeg_txt_inputfile.getAbsolutePath()+" -c copy  "+ output_file.getAbsolutePath();

            System.out.println(" 执行命令:\n"+ command_concat);


            execCMDNoStart(command_concat);
//             execCMD(command_concat);
            if(output_file.exists() && output_file.length() > 0) {
                System.out.println(" 合并文件成功命令:\n  output_file ="+ command_concat);
                System.out.println(" 合并文件成功:  output_file ="+ output_file.getAbsolutePath());

                ffmpeg_txt_inputfile.delete();
            }else {
                System.out.println(" 合并文件失败:  output_file ="+ output_file);
                System.out.println(" 请手动执行命令:\n"+ command_concat);
            }

        }




    }

    class Revert_MP4_To_Gif_Rule_11 extends  Basic_Rule{
        ArrayList<File> mInputMediaFileList ;  // 输入的 视频文件

        File targetInputMP4File ;  // 输入的 Mp4文件
        String beginTimeStr;
        String endTimeStr;
        int timeDistance ;   //  时间的间距  秒 ..
        boolean isAllMp4_Operation_Tag ; //   是否 包含  allmp4 标识  如果有 那么对当前所有的mp4转为 gif
        String outputFileName;  // 输出文件的名称  Gif 的输出的文件名称


        Revert_MP4_To_Gif_Rule_11(){
            super(11);
            mInputMediaFileList = new  ArrayList<File>();
            isAllMp4_Operation_Tag = false;

        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return          "\n"+Cur_Bat_Name+ "  11   <mp4,flv,avi.rmvb 路径>    ## 输入单个MP4路径 把单个MP4转Gif   \n"+
                    "\n"+Cur_Bat_Name+ "  11   10-              <mp4,flv,avi.rmvb 路径>       ## 秒数MP4转Gif   \n"+
                    "\n"+Cur_Bat_Name+ "  11   -100              <mp4,flv,avi.rmvb 路径>    ## 秒数MP4转Gif   \n"+
                    "\n"+Cur_Bat_Name+ "  11  10-50              <mp4,flv,avi.rmvb 路径>    ## 秒数MP4转Gif   \n"+
                    "\n"+Cur_Bat_Name+ "  11  01:10-             <mp4,flv,avi.rmvb 路径>    ## 分钟数MP4转Gif \n"+
                    "\n"+Cur_Bat_Name+ "  11  -01:10             <mp4,flv,avi.rmvb 路径>    ## 分钟数MP4转Gif \n"+
                    "\n"+Cur_Bat_Name+ "  11  01:10-02:50        <mp4,flv,avi.rmvb 路径>    ## 分钟数MP4转Gif \n"+
                    "\n"+Cur_Bat_Name+ "  11  00:00:10-          <mp4,flv,avi.rmvb 路径>    ## 时分秒MP4转Gif \n"+
                    "\n"+Cur_Bat_Name+ "  11  -00:00:10          <mp4,flv,avi.rmvb 路径>    ## 时分秒MP4转Gif \n"+
                    "\n"+Cur_Bat_Name+ "  11  00:00:00-00:00:10  <mp4,flv,avi.rmvb 路径>    ## 时分秒MP4转Gif \n" +
                    "\n"+Cur_Bat_Name+ "  11  allmp4      ### <mp4,flv,avi.rmvb 路径>   把本地的所有的MP4转Gif   \n"; }




        // 【Mp4截取 Gif 】 -ss  视频截取gif的起点  -t gif持续的时长  -i 输入  -r 帧率   -y 强制覆盖
        // ffmpeg -ss 00:00:04 -t 3 -i 1.mp4 -r 15  1.gif -y

        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule11 shellDir = "+ shellDir);
            System.out.println("rule11  otherParams = "+ otherParams.size());


            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                // 检测当前目录下的 mp4 文件
                System.out.println(errorMsg);
                return false;
            }

            if(otherParams.size() == 1) {
                String firstParam = otherParams.get(0).toLowerCase();
                if("allmp4".equals(firstParam)) {

                    isAllMp4_Operation_Tag = true;
                    System.out.println("当前 对 本地目录所有mp4  进行 gif的 格式转换! ");
                    return true ;
                }


            }



            System.out.println("rule11 otherParams.size() = "+ otherParams.size());

            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("curAbsPath  = "+ curAbsPath);
                if(curFIle.exists() && videoTypeList.contains(getFileTypeWithPoint(curFIle.getName())) ){  // 判断
                    mInputMediaFileList.add(curFIle);
                }
            }
            if(mInputMediaFileList.size() == 0){
                errorMsg = "当前从参数找不到对应的输入源 .mp4  .flv .rmvb .avi 文件 ";
                System.out.println(errorMsg);
                return false;
            }
            System.out.println("rule7 checkParamsOK mInputMediaFileList.size() = "+ mInputMediaFileList.size());
            targetInputMP4File = mInputMediaFileList.get(mInputMediaFileList.size()-1);
            String tagFlag = otherParams.get(0);
            System.out.println("targetInputMP4File = "+ targetInputMP4File.getAbsolutePath());
            System.out.println("tagFlag = "+ tagFlag);





            if(tagFlag.startsWith("-")){
                tagFlag = "00:00:00"+tagFlag;
            }

            if(tagFlag.endsWith("-")){
                tagFlag = tagFlag+ReadVideoTime(targetInputMP4File);
            }

            String[] tagArr = tagFlag.split("-");
            if(tagArr == null || tagArr.length != 2){
                System.out.println("tagFlag = "+ tagFlag +"   tagArr.length = " + tagArr.length);
                beginTimeStr = null;    // 不输入  时间参数时
                endTimeStr = null;


                String originName = targetInputMP4File.getName();
                String fileNameOnly = getFileNameNoPoint(originName);

                outputFileName = fileNameOnly+"_"+System.currentTimeMillis()/1000+".gif";

                return true;
            }



            String pre_Str = tagArr[0];
            if(!"".equals(pre_Str.trim())){
                beginTimeStr =   fixedTimeStr(pre_Str);
            }else{
                beginTimeStr =  "00:00:00";
            }



            String end_Str = tagArr[1];
            if(!"".equals(end_Str.trim())){
                endTimeStr =   fixedTimeStr(end_Str);
            }else{
                endTimeStr =  ReadVideoTime(targetInputMP4File);
            }


            String originName = targetInputMP4File.getName();
//          String typeStr = getFileTypeWithPoint(originName);
            String typeStr =".gif";
            String fileNameOnly = getFileNameNoPoint(originName);

            outputFileName = fileNameOnly+"_"+beginTimeStr.replace(":","")+"_"+endTimeStr.replace(":","")+"_"+System.currentTimeMillis()/1000+typeStr;
            outputFileName = outputFileName.replace(" ","");





            String beginTemp1 = beginTimeStr.replace(":","");
            String endTemp1 = endTimeStr.replace(":","");
            int beginTemp1_int = Integer.parseInt(beginTemp1);
            int endTemp1_int = Integer.parseInt(endTemp1);

            System.out.println("beginTimeStr = "+ beginTimeStr +"   endTimeStr = "+ endTimeStr  +"   outputFileName =  "+ outputFileName  + "targetInputMP4File = "+ targetInputMP4File.getName());

            if(beginTemp1_int > endTemp1_int){
                System.out.println("开始时间大于结束时间! 请检查参数!   beginTimeStr = "+ beginTimeStr  +"       endTimeStr = "+ endTimeStr);
            }

            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }


        void TryAllMp42GifOperation(File[] allLocalFileArr , String mpegPath) {
            if(allLocalFileArr == null || allLocalFileArr.length == 0) {

                System.out.println("当前目录 CUR_Dir_FILE="+CUR_Dir_FILE.getAbsolutePath()+" 子文件为空!");
            }

            ArrayList<File> allMp4File = new ArrayList<File> ();


            for (int i = 0; i < allLocalFileArr.length; i++) {
                File fileItem = allLocalFileArr[i];

                if(fileItem.isFile()) {
                    String fileName_lower =  fileItem.getName().toLowerCase();

                    if(fileName_lower.endsWith(".mp4")) {
                        allMp4File.add(fileItem);

                    }

                }

            }


            if(allMp4File.size()  == 0 ) {

                System.out.println("当前目录 CUR_Dir_FILE="+CUR_Dir_FILE.getAbsolutePath()+"  不包含 mp4文件 !");

                System.out.println("请检查当前目录是否包含 mp4 文件 然后再试 ! ");

                return ;
            }

            for (int i = 0; i < allMp4File.size(); i++) {
                File mp4FileItem = allMp4File.get(i);
                String mp4AbsPath = mp4FileItem.getAbsolutePath();

                String originName = mp4FileItem.getName();
                String fileNameOnly = getFileNameNoPoint(originName);

                outputFileName = fileNameOnly+"_"+System.currentTimeMillis()/1000+".gif";


                String  command = mpegPath +" -i " + "\""+mp4AbsPath+ "\"" +" "+ "   -r 15  "+ outputFileName +" -y ";

                System.out.println("allmp4["+(i+1)+"]["+allMp4File.size()+"] outputFileName[ "+outputFileName+" ]command【 "+command+" 】");
                execCMDNoStart(command);



            }

            System.out.println("当前本地 CUR_Dir_FILE="+CUR_Dir_FILE.getAbsolutePath()+" MP4 文件 转为 gif 文件完成! ");



        }

        @Override
        void operationRule(ArrayList<String> inputParamsList) {


            System.out.println("beginTimeStr = "+ beginTimeStr +"   endTimeStr = "+ endTimeStr  +"   outputFileName =  "+ outputFileName  + "targetInputMP4File = "+ targetInputMP4File);



            //     ffmpeg -i sky1.mp4  image%d.jpg    抠图

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule7 curInputFileList.size() = "+mInputMediaFileList.size());
            System.out.println("rule7 ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt

            System.out.println("isAllMp4_Operation_Tag = "+isAllMp4_Operation_Tag);

            if(isAllMp4_Operation_Tag) {

                File[]  mFileArr = 	CUR_Dir_FILE.listFiles();
                TryAllMp42GifOperation(mFileArr,ffmpeg_path);

                return;
            }


            // ffmpeg -ss 00:00:04 -t 3 -i 1.mp4 -r 15  1.gif -y

            // ffmpeg -ss 00:00:04 -to  00:00:10 -i 1.mp4 -r 15  1.gif -y
            // ffmpeg -i 1.mp4 -r 15  1.gif -y

            String command =  null;
            if(beginTimeStr != null && endTimeStr != null) {
                command = ffmpeg_path +" -ss "+beginTimeStr  + "  -to " + endTimeStr +"  -i " + "\""+targetInputMP4File.getName()+ "\"" +" "+ "   -r 15  "+ outputFileName +" -y ";
            }else {
                command = ffmpeg_path +" -i " + "\""+targetInputMP4File.getName()+ "\"" +" "+ "   -r 15  "+ outputFileName +" -y ";
            }



            System.out.println(command);
            execCMD(command);
            System.out.println("裁剪输出文件完成 -》 " + outputFileName);



        }



    }




    // 批量旋转当前目录下的 mp4 文件
    class Batch_VideoRoast_Rule_10 extends  Basic_Rule{

        ArrayList<File> curDirMP4FileList ;  // 当前目录的 mp4文件    如果 MDName
        ArrayList<File> needChangeToMDFileList  ;  // 当前如果包含中文那么需要转为 MD5名称 的 文件 的 列表
        int batchRotate = 90;
        String batchDirName = "";
        String ffmpeg_path = "";
        File batchDirFile = null;

        Batch_VideoRoast_Rule_10(){
            super(10);
            curDirMP4FileList = new  ArrayList<File>();
            needChangeToMDFileList =  new  ArrayList<File>();
            batchRotate = 90;
        }


        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return
                    "\n"+Cur_Bat_Name+ " "+rule_index+ "  batch_90    ## 把当前目录下的 Mp4文件 批量旋转90度  保存到90_rotate_时间戳 目录中   \n"+
                            "\n"+Cur_Bat_Name+ "  "+rule_index+ " batch_180    ## 把当前目录下的 Mp4文件 批量旋转180度  保存到180_rotate_时间戳 目录中 中  \n" +
                            "\n"+Cur_Bat_Name+ " "+rule_index+ "  batch_270    ## 把当前目录下的 Mp4文件 批量旋转270度  保存到270_rotate_时间戳 目录中 中   \n"

                    ; }




        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule"+rule_index+" shellDir = "+ shellDir);
            System.out.println("rule"+rule_index+"  otherParams = "+ otherParams.size());


            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }



            System.out.println("rule4 otherParams.size() = "+ otherParams.size());
            for (int i = 0; i <otherParams.size() ; i++) {
                String paramsItem = otherParams.get(i);
                if(paramsItem.startsWith("batch_")) {

                    String mRotateStr = paramsItem.replace("batch_", "").trim();
                    if(isNumeric(mRotateStr)) {

                        batchRotate =  Integer.parseInt(mRotateStr);
                    }



                }

            }

            ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null || "".equals(ffmpeg_path)){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return false;
            }

            // 检查 中文 mp4文件, 可能会引起 执行失败  needChangeToMDFileList
            for (File fileItem:shellDir.listFiles()) {
                String fileItemName = fileItem.getName().toLowerCase().trim();
                if(isContainChinese(fileItemName) && fileItemName.endsWith(".mp4")) {
                    needChangeToMDFileList.add(fileItem);
                }
            }

            //  把 中文的文件 改为 md 命名的文件
            for (int i = 0; i < needChangeToMDFileList.size(); i++) {
                File chineseNameFileItem =  needChangeToMDFileList.get(i);
                String fileName = chineseNameFileItem.getName();

                String mdName = getMD5Three(chineseNameFileItem.getAbsolutePath())+".mp4";

                System.out.println("file["+i+"] fileName="+fileName+" 改为 "+mdName);
                tryReName(chineseNameFileItem, mdName);

            }



            // 检查 mp4  文件

            for (File fileItem:shellDir.listFiles()) {
                String fileItemName = fileItem.getName().toLowerCase().trim();
                if(fileItemName.endsWith(".mp4")) {
                    curDirMP4FileList.add(fileItem);
                }

            }

            if(curDirMP4FileList.size() == 0) {
                errorMsg = "当前 shell 目录下 mp4 文件的个数为 0  shellDir="+shellDir.getAbsolutePath();
                System.out.println(errorMsg);
                return false;

            }


            // 180_rotate_
            batchDirName = batchRotate+"_"+"roast"+rule_index+"_"+getTimeStamp();


            batchDirFile =  new File(shellDir.getAbsolutePath()+File.separator+""+batchDirName);
            if(!batchDirFile.exists()) {
                batchDirFile.mkdirs();
            }



            System.out.println("rule"+rule_index+"  batchRotate="+batchRotate+"   batchDirName="+ batchDirName+"   ffmpeg_path="+ ffmpeg_path);
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }






        @Override
        void operationRule(ArrayList<String> inputParamsList) {



            //     ffmpeg -i sky1.mp4  image%d.jpg    抠图


            System.out.println("rule4 ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt
            for (int i = 0; i < curDirMP4FileList.size(); i++) {

                File mp4File = curDirMP4FileList.get(i);
                StringBuilder sb =new StringBuilder();

                String originName = mp4File.getName();
                String noPointFileName = getFileNameNoPoint(originName);
                String type = getFileTypeWithPoint(mp4File.getName());
//                File jpgDirFile = new File(CUR_Dir_1_PATH+File.separator+noPointFileName+"_"+DateFormat.format(new Date())+File.separator);
//                jpgDirFile.mkdirs();
//                String newFileName = originName+"_mp3_"+DateFormat.format(new Date())+".mp3";

                String newFileName =  originName;
//                String newFileName = mp4File.getName().replace(".mp4","_x"+bigNum+"_"+DateFormat.format(new Date())+".mp4");     //  新的文件的名称  2.mp4 2_mergedxxxxxxxxxx.mp4
//                String imageStr = noPointFileName+"_%d.jpg";
//                String absImagePath = jpgDirFile.getAbsolutePath()+File.separator+imageStr;
                String newFileAbsPath = batchDirFile.getAbsolutePath()+File.separator + newFileName;

/*                ffmpeg -i 1.mp4 -vf "rotate=90*PI/180" 2.mp4        // 顺时针旋转90度
                ffmpeg -i 1.mp4 -vf "rotate=PI"      3.mp4          // 顺时针旋转180度
                ffmpeg -i 1.mp4 -vf "rotate=270*PI/180"  4.mp4      // 顺时针旋转270度*/

                String command = "";
                if(batchRotate == 90 || batchRotate == 270){
                    command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() + " -vf \"rotate="+batchRotate+"*PI/180:ow=ih:oh=iw\"  " + newFileAbsPath;

                }else{
                    command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() + " -vf \"rotate="+batchRotate+"*PI/180\" " + newFileAbsPath;
                }

                System.out.println(command);
                execCMD(command);
            }

        }




    }
    // 把当前目录下的 Mp4 生成缩略图  保存到  本地  生成在 目录 SuoTu_Mp4_时间戳 目录中
    class CaptureSuoLueTu_From_MP4_Rule_9 extends  Basic_Rule{

        ArrayList<File> curDirMP4FileList ;  // 当前目录的 mp4文件

        ArrayList<String> curMp4NameNoTypeList;   // 当前需要产生

        File SuoTu_Mp4_Dir ;
        String ffmpeg_path ;

        CaptureSuoLueTu_From_MP4_Rule_9(){
            super(9);
            curDirMP4FileList = new  ArrayList<File>();
            curMp4NameNoTypeList = new  ArrayList<String>();

        }



        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return
                    "\n"+Cur_Bat_Name+ " "+rule_index+ "     ## 把当前目录下的 Mp4 生成缩略图  保存到  本地  生成在 目录 SuoTu_Mp4_时间戳 目录中   \n"+
                            "\n"+Cur_Bat_Name+ "  "+rule_index+ "    ##  把当前目录下的 Mp4 生成缩略图  保存到  本地  生成在 目录 SuoTu_Mp4_时间戳 目录中  \n"; }



        @Override
        void operationRule(ArrayList<String> inputParamsList) {

            ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule8 curDirMP4FileList.size() = "+curDirMP4FileList.size());
            System.out.println("rule8 ffmpeg_path = "+ffmpeg_path);

            String SuoTu_Mp4_DirName = "SuoTu_MP4_"+getTimeStamp();
            SuoTu_Mp4_Dir = new File(CUR_Dir_FILE+File.separator+SuoTu_Mp4_DirName);

            if(!SuoTu_Mp4_Dir.exists()) {
                SuoTu_Mp4_Dir.mkdirs();
            }
            for (int i = 0; i < curDirMP4FileList.size(); i++) {
                File mp4File = curDirMP4FileList.get(i);
                String mp4FileAbs = mp4File.getAbsolutePath();
                String fileName = mp4File.getName();
                String fileNameNoPointType = getFileNameNoPoint_NoCase(fileName);
                curMp4NameNoTypeList.add(fileNameNoPointType);
                String target_jpg_abs_path = (SuoTu_Mp4_Dir.getAbsolutePath()+File.separator+fileNameNoPointType+".jpg").replace(" ","");




                // D:\software\ffmpeg\bin\ffmpeg.exe -i D:\TEMP\ZZ\mp4_home\temp\mp4_home_land\Land_5.mp4 -r 0.001  D:\TEMP\ZZ\mp4_home\temp\mp4_home_land\land_5.jpg

                String command = ffmpeg_path +" -i "+mp4FileAbs  + "  -r 0.001  " + target_jpg_abs_path;
                System.out.println("--------ruleIndex["+rule_index+"] fileIndex["+i+"]  Path=["+target_jpg_abs_path.replace(" ","")+"] ");
                System.out.println(command);
                execCMD(command);


            }

            System.out.println("等待程序执行1.5秒");
            try {
                Thread.sleep(1500);
            }catch (Exception e){

            }

            int whilecount = 1;
            //  检测哪些文件是 执行失败的 没有 创建 文件
            int curMP4Count = curDirMP4FileList.size();
            int jpgCount = 0;
            if(SuoTu_Mp4_Dir.listFiles() != null) {
                jpgCount = 	SuoTu_Mp4_Dir.listFiles().length;

            }

            if(curMP4Count != jpgCount) {

                System.out.println("当前MP4数量["+curMP4Count+"]  JPG数量["+jpgCount+"] 不一致  存在缩略图失败的文件 Failed");

                ArrayList<File> failedMP4FileList = calculFailedMP4(SuoTu_Mp4_Dir,curMp4NameNoTypeList);

                System.out.println("failedMP4FileList.size()  = "+ failedMP4FileList.size());

                while(failedMP4FileList.size() > 0 && whilecount <= 10  ) {
                    System.out.println("执行第 [ "+whilecount+" ] 次循环排除失败项 failedMP4FileList.size()="+failedMP4FileList.size());
                    generalSuoTu(failedMP4FileList);
                    failedMP4FileList = calculFailedMP4(SuoTu_Mp4_Dir,curMp4NameNoTypeList);
                    whilecount++;
                }

                if(whilecount > 10) {
                    System.out.println("尝试对Failed 文件进行 【"+whilecount+"】 次 创建缩略图操作仍然失败..... 程序执行OVER:");

                }



            }else {
                System.out.println("当前MP4数量["+curMP4Count+"]  JPG数量["+jpgCount+"] 一致  缩略图执行PASS ");


            }

            //  检测哪些文件是 执行失败的 没有 创建 文件
            int curMP4Count_end = curDirMP4FileList.size();
            int jpgCount_end = 0;
            if(SuoTu_Mp4_Dir.listFiles() != null) {
                jpgCount_end = 	SuoTu_Mp4_Dir.listFiles().length;

            }

            if(curMP4Count_end != jpgCount_end) {
                System.out.println("程序执行结束!!   尼玛 还是有失败_Failed!!  curMP4Count_end["+curMP4Count_end+"]"+"  jpgCount_end["+jpgCount_end +"]  whilecount=["+whilecount+"]");
            }else {

                System.out.println("程序执行结束!!  程序执行成功_OK!!  curMP4Count_end["+curMP4Count_end+"]"+"  jpgCount_end["+jpgCount_end +"]  whilecount=["+whilecount+"]");

            }



        }



        ArrayList<File>  calculFailedMP4(File SuotuDir , ArrayList<String> allMp4NameNoTypeList){
            ArrayList<File>  failedMp4List = new  ArrayList<File> ();

            File[] allFile =  SuotuDir.listFiles();
            ArrayList<String> allMp4NameNoType_TempList = new  ArrayList<String>();
            allMp4NameNoType_TempList.addAll(allMp4NameNoTypeList);


            for (int i = 0; i < allFile.length; i++) {
                File jpgFile = allFile[i];
                String fileName = jpgFile.getName();
                String fileNameNoPointType = getFileNameNoPoint_NoCase(fileName);

                allMp4NameNoType_TempList.remove(fileNameNoPointType);

                System.out.println();
            }

            if(allMp4NameNoType_TempList.size() == 0){

                return failedMp4List;
            }


            for (int i = 0; i < allMp4NameNoType_TempList.size() ; i++) {
                String fileNameNoPointType =allMp4NameNoType_TempList.get(i);
                File failedFile = 	new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+fileNameNoPointType+".mp4");
                if(failedFile.exists()) {

                    failedMp4List.add(failedFile);
                }
            }




            return failedMp4List;


        }

        void  generalSuoTu(ArrayList<File> mp4FileList ){

            System.out.println("等待程序执行1秒");
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }

            for (int i = 0; i < mp4FileList.size(); i++) {
                File mp4File = mp4FileList.get(i);
                String mp4FileAbs = mp4File.getAbsolutePath();
                String fileName = mp4File.getName();
                String fileNameNoPointType = getFileNameNoPoint_NoCase(fileName);

                String target_jpg_abs_path = (SuoTu_Mp4_Dir.getAbsolutePath()+File.separator+fileNameNoPointType+".jpg").replace(" ","");




                // D:\software\ffmpeg\bin\ffmpeg.exe -y -i D:\TEMP\ZZ\mp4_home\temp\mp4_home_land\Land_5.mp4 -r 0.001  D:\TEMP\ZZ\mp4_home\temp\mp4_home_land\land_5.jpg

                String command = ffmpeg_path +" -y -i "+mp4FileAbs  + "  -r 0.001  " + target_jpg_abs_path;
                System.out.println("--------ruleIndex["+rule_index+"] fileIndex["+i+"]  Path=["+target_jpg_abs_path.replace(" ","")+"] ");
                System.out.println(command);
                execCMD(command);


            }


        }


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            // TODO Auto-generated method stub

            File[] listFile = shellDir.listFiles();

            if(listFile == null) {
                System.out.println("当前目录下的 文件为空   程序执行失败 ");
                return false;
            }

            for (int i = 0; i < listFile.length; i++) {
                File itemFile = listFile[i];

                if(itemFile.isDirectory()) {
                    continue;
                }
                String fileName_tolower = itemFile.getName().toLowerCase();
                if(fileName_tolower.endsWith(".mp4")) {
                    curDirMP4FileList.add(itemFile);
                }
            }

            if(curDirMP4FileList.size() == 0) {

                System.out.println("当前目录下的 MP4 文件为空   程序不能执行   程序执行失败 ");
                return false;
            }

            return super.checkParamsOK(shellDir, type2Param, otherParams);
        }


    }

    class MOV_Revert_MP4_Rule_8 extends  Basic_Rule{

        ArrayList<File> curDirMovFileList ;  // 当前目录的 mov文件
        boolean isCreateMP4InSameDir;  //  是否创建 mp4 在当前的shell 目录

        MOV_Revert_MP4_Rule_8(){
            super(8);
            curDirMovFileList = new  ArrayList<File>();
            isCreateMP4InSameDir = false;
        }



        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return
                    "\n"+Cur_Bat_Name+ " "+rule_index+ "     ##  把当前的 .vob  .wmv  .mkv .ts .mov .avi .m4v  文件转为 mp4文件 生成在 目录 Mov_To_Mp4_时间戳 目录中   \n"+
                    "\n"+Cur_Bat_Name+ "  "+rule_index+ "    ##  把当前的 .vob   .wmv .mkv .avi .ts .mov .m4v  文件转为 mp4文件 生成在 目录 Mov_To_Mp4_时间戳 目录中  \n"+
                    "\n"+Cur_Bat_Name+ "  "+rule_index+ " samedir_true   ##  把当前的  .vob .wmv .mkv .avi .ts .mov .m4v  文件转为 mp4文件 生成在 当前相同目录中  并删除原始文件  \n"
            ; }



        @Override
        void operationRule(ArrayList<String> inputParamsList) {

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule8 curDirMovFileList.size() = "+curDirMovFileList.size());
            System.out.println("rule8 ffmpeg_path = "+ffmpeg_path);

            String Mov_2_Mp4_DirName = "Mov_To_Mp4_"+getTimeStamp();
            File Mov_2_Mp4_Dir = new File(CUR_Dir_FILE+File.separator+Mov_2_Mp4_DirName);
            if(isCreateMP4InSameDir){
                Mov_2_Mp4_Dir = new File(CUR_Dir_FILE.getAbsolutePath());
            }

            if(!Mov_2_Mp4_Dir.exists()) {
                Mov_2_Mp4_Dir.mkdirs();
            }
            for (int i = 0; i < curDirMovFileList.size(); i++) {
                File movFile = curDirMovFileList.get(i);
                String movFileAbs = movFile.getAbsolutePath();
                String fileName = movFile.getName();
                String fileNameNoPointType = getFileNameNoPoint_NoCase(fileName);
                String target_mp4_abs_path = (Mov_2_Mp4_Dir.getAbsolutePath()+File.separator+fileNameNoPointType+".mp4").replace(" ","");


                String type = getFileTypeWithPoint(movFile.getName()).toLowerCase();

                // ffmpeg -i 2.ts -acodec copy -vcodec copy -absf aac_adtstoasc output.mp4

                //  ffmpeg -i movie.mov -vcodec copy -acodec copy out.mp4

                String command = ffmpeg_path +" -i "+movFileAbs  + "  -vcodec copy -acodec copy  " + target_mp4_abs_path;

                if(type.endsWith("wmv")){   // ffmpeg -i sample.wmv -vcodec libx264 -acodec aac out.mp4
                    command = ffmpeg_path +" -i "+movFileAbs  + " -vcodec libx264 -acodec aac  " + target_mp4_abs_path;
                } else if(type.endsWith("mkv")){   // ffmpeg -i 1.mkv  -c:v copy -c:a aac 1.mp4
                    command = ffmpeg_path +" -i "+movFileAbs  + "  -c:v copy -c:a aac   " + target_mp4_abs_path;
                } else  if(type.endsWith("avi")){   // ffmpeg -i input_filename.avi -c:v copy -c:a copy -y output_filename.mp4
                    command = ffmpeg_path +" -i "+movFileAbs  + "   -c:v copy -c:a copy -y   " + target_mp4_abs_path;
                } else if(type.endsWith("ts")){
                    command = ffmpeg_path +" -i "+movFileAbs  + "  -vcodec copy -absf aac_adtstoasc   " + target_mp4_abs_path;
                } else if(type.endsWith("mov")){
                    // ffmpeg -i 2.mov -vcodec libx264 -preset fast -crf 20 -y -vf "scale=1920:-1" -acodec libmp3lame -ab 128k new_2.mp4
                    command = ffmpeg_path +" -i "+movFileAbs  + " -vcodec libx264 -preset fast -crf 20 -y -vf \"scale=1920:-1\" -acodec libmp3lame -ab 128k   " + target_mp4_abs_path;
                }else{
                    command = ffmpeg_path +" -i "+movFileAbs  + "  -vcodec copy -acodec copy  " + target_mp4_abs_path;
                }

                System.out.println("--------ruleIndex["+rule_index+"] fileIndex["+i+"]  Path=["+movFile.getAbsolutePath().replace(" ","")+"] ");

                System.out.println(command);
                execCMD(command);



            }
            try {
                Thread.sleep(5000);
            }catch ( Exception e){
                System.out.println("睡眠 5秒 ");
            }

            for (int i = 0; i < curDirMovFileList.size(); i++) {
                File movFile = curDirMovFileList.get(i);
                String movFileAbs = movFile.getAbsolutePath();
                String fileName = movFile.getName();
                String fileNameNoPointType = getFileNameNoPoint_NoCase(fileName);
                String target_mp4_abs_path = (Mov_2_Mp4_Dir.getAbsolutePath()+File.separator+fileNameNoPointType+".mp4").replace(" ","");
                File target_mp4_abs_File = new File(target_mp4_abs_path);
                if (isCreateMP4InSameDir) {
                    System.out.println("删除源文件A: "+movFile.getAbsolutePath() +"  target_mp4_abs_path="+ target_mp4_abs_path);
                    if (target_mp4_abs_File.exists() && target_mp4_abs_File.length() > 100) {
                        boolean feleteflag =     movFile.delete();  //删除 源文件
                        System.out.println("删除源文件B: "+movFile.getAbsolutePath());

                        if(!feleteflag){
                            movFile.delete();
                        }


                    }

                }
            }




        }
        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            // TODO Auto-generated method stub

            File[] listFile = shellDir.listFiles();

            if(listFile == null) {
                System.out.println("当前目录下的 Mov 文件为空_1  程序执行失败 ");
                return false;
            }

            for (int i = 0; i < otherParams.size() ; i++) {
                String paramItem = otherParams.get(i);
                if("samedir_true".equals(paramItem)){
                    isCreateMP4InSameDir = true;
                }
            }

            for (int i = 0; i < listFile.length; i++) {
                File itemFile = listFile[i];

                if(itemFile.isDirectory()) {
                    continue;
                }
                String fileName_tolower = itemFile.getName().toLowerCase();
                if(fileName_tolower.endsWith(".mov") || fileName_tolower.endsWith(".m4v")
                        || fileName_tolower.endsWith(".ts")   || fileName_tolower.endsWith(".avi") || fileName_tolower.endsWith(".vob")
                        || fileName_tolower.endsWith(".mkv")  || fileName_tolower.endsWith("wmv")){
                    curDirMovFileList.add(itemFile);
                }
            }

            if(curDirMovFileList.size() == 0) {

                System.out.println("当前目录下的 Ts Mov 或者 m4v 文件为空_2  程序执行失败 ");
                return false;
            }
            System.out.println("isCreateMP4InSameDir = "+ isCreateMP4InSameDir);
            return super.checkParamsOK(shellDir, type2Param, otherParams);
        }
    }



    // ffmpeg -ss 00:00:00  -accurate_seek  -to 00:00:10  -i 1.mp4 -codec copy 1_output.mp4   //  截取视频
    class CutDown_Video_Rule_7 extends  Basic_Rule{
        ArrayList<File> mInputMediaFileList ;  // 输入的 视频文件

        File targetInputMP4File ;  // 输入的 Mp4文件
        String beginTimeStr;
        String endTimeStr;
        String outputFileName;  // 输出文件的名称
        



        CutDown_Video_Rule_7(){
            super(7);
            mInputMediaFileList = new  ArrayList<File>();

        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return
                    "\n"+Cur_Bat_Name+ "  7   10-   1.mp4       <mp4,flv,avi.rmvb 路径>      ## 秒数往后截取视频   \n"+
                            "\n"+Cur_Bat_Name+ "  7   -100     1.mp4           <mp4,flv,avi.rmvb 路径>    ## 秒数往后截取视频   \n"+
                            "\n"+Cur_Bat_Name+ "  7  10-50     1.mp4           <mp4,flv,avi.rmvb 路径>    ## 秒数往后截取视频   \n"+
                            "\n"+Cur_Bat_Name+ "  7  01:10-    1.mp4           <mp4,flv,avi.rmvb 路径>    ## 分钟数往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  7  -01:10    1.mp4          <mp4,flv,avi.rmvb 路径>    ## 分钟数往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  7  01:10-02:50    1.mp4        <mp4,flv,avi.rmvb 路径>    ## 分钟数往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  7  00:00:10-      1.mp4       <mp4,flv,avi.rmvb 路径>    ## 时分秒往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  7  -00:00:10      1.mp4      <mp4,flv,avi.rmvb 路径>    ## 时分秒往后截取视频 \n"+
                            "\n"+Cur_Bat_Name+ "  7  00:00:10-00:00:50  stepms_500  1.mp4  <mp4,flv,avi.rmvb 路径>    ## 时分秒往后截取视频 \n"; }



        // ffmpeg -ss 00:00:00  -accurate_seek  -to 00:00:10  -i 1.mp4 -codec copy 1_output.mp4    //  截取视频
        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule7 shellDir = "+ shellDir);
            System.out.println("rule7  otherParams = "+ otherParams.size());


            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }



            System.out.println("rule7 otherParams.size() = "+ otherParams.size());

            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                
 
                
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("curAbsPath  = "+ curAbsPath);
                if(curFIle.exists() && videoTypeList.contains(getFileTypeWithPoint(curFIle.getName())) ){  // 判断
                    mInputMediaFileList.add(curFIle);
                }
            }
            if(mInputMediaFileList.size() == 0){
                errorMsg = "当前从参数找不到对应的输入源 .mp4  .flv .rmvb .avi 文件 ";
                System.out.println(errorMsg);
                return false;
            }
            System.out.println("rule7 checkParamsOK mInputMediaFileList.size() = "+ mInputMediaFileList.size());
            targetInputMP4File = mInputMediaFileList.get(mInputMediaFileList.size()-1);
            String tagFlag = otherParams.get(0);
            System.out.println("targetInputMP4File = "+ targetInputMP4File.getAbsolutePath());
            System.out.println("tagFlag = "+ tagFlag);

            if(!tagFlag.contains("-"))
            {
                System.out.println("输入的 时间标识字符串 " +tagFlag +" 不包含分割符号 -  无法识别! 程序退出!");
                return false;
            }

            if(tagFlag.startsWith("-")){
                tagFlag = "00:00:00"+tagFlag;
            }

            if(tagFlag.endsWith("-")){
                tagFlag = tagFlag+ReadVideoTime(targetInputMP4File);
            }

            String[] tagArr = tagFlag.split("-");
            if(tagArr == null || tagArr.length != 2){
                System.out.println("tagFlag = "+ tagFlag +" 分割符号 - 数组为空 或者长度不为2  程序退出!  tagArr.length = " + tagArr.length);
                return false;
            }



            String pre_Str = tagArr[0];
            if(!"".equals(pre_Str.trim())){
                beginTimeStr =   fixedTimeStr(pre_Str);
            }else{
                beginTimeStr =  "00:00:00";
            }



            String end_Str = tagArr[1];
            if(!"".equals(end_Str.trim())){
                endTimeStr =   fixedTimeStr(end_Str);
            }else{
                endTimeStr =  ReadVideoTime(targetInputMP4File);
            }


            String originName = targetInputMP4File.getName();
            String typeStr = getFileTypeWithPoint(originName);
            String fileNameOnly = getFileNameNoPoint(originName);

            outputFileName = fileNameOnly+"_"+beginTimeStr.replace(":","")+"_"+endTimeStr.replace(":","")+"_"+System.currentTimeMillis()/1000+typeStr;
            outputFileName = outputFileName.replace(" ","");
            String beginTemp1 = beginTimeStr.replace(":","");
            String endTemp1 = endTimeStr.replace(":","");
            int beginTemp1_int = Integer.parseInt(beginTemp1);
            int endTemp1_int = Integer.parseInt(endTemp1);

            System.out.println("beginTimeStr = "+ beginTimeStr +"   endTimeStr = "+ endTimeStr  +"   outputFileName =  "+ outputFileName  + "targetInputMP4File = "+ targetInputMP4File.getName());

            if(beginTemp1_int > endTemp1_int){
                System.out.println("开始时间大于结束时间! 请检查参数!   beginTimeStr = "+ beginTimeStr  +"       endTimeStr = "+ endTimeStr);
            }

            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }



        @Override
        void operationRule(ArrayList<String> inputParamsList) {


            System.out.println("beginTimeStr = "+ beginTimeStr +"   endTimeStr = "+ endTimeStr  +"   outputFileName =  "+ outputFileName  + "targetInputMP4File = "+ targetInputMP4File.getName());



            //     ffmpeg -i sky1.mp4  image%d.jpg    抠图

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule7 curInputFileList.size() = "+mInputMediaFileList.size());
            System.out.println("rule7 ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt


            // ffmpeg -ss 00:00:00  -accurate_seek  -to 00:00:10  -i 1.mp4 -codec copy 1_output.mp4
            String command = ffmpeg_path +" -ss "+beginTimeStr  + " -accurate_seek  -to " + endTimeStr +"  -i " + "\""+targetInputMP4File.getName()+ "\"" +" "+ "  -codec copy -avoid_negative_ts 1 "+ outputFileName;


            System.out.println(command);
            execCMD(command);
            System.out.println("裁剪输出文件完成 -》 " + outputFileName);

/*            for (int i = 0; i < mInputMediaFileList.size(); i++) {

                File mp4File = mInputMediaFileList.get(i);
                StringBuilder sb =new StringBuilder();

                String originName = mp4File.getName();
                String noPointFileName = getFileNameNoPoint(originName);
                String type = getFileTypeWithPoint(mp4File.getName());
//                File jpgDirFile = new File(CUR_Dir_1_PATH+File.separator+noPointFileName+"_"+DateFormat.format(new Date())+File.separator);
//                jpgDirFile.mkdirs();
//                String newFileName = originName+"_mp3_"+DateFormat.format(new Date())+".mp3";
                String newFileName = originName.replace(type,"_"+"_"+DateFormat.format(new Date())+type);
//                String newFileName = mp4File.getName().replace(".mp4","_x"+bigNum+"_"+DateFormat.format(new Date())+".mp4");     //  新的文件的名称  2.mp4 2_mergedxxxxxxxxxx.mp4
//                String imageStr = noPointFileName+"_%d.jpg";
//                String absImagePath = jpgDirFile.getAbsolutePath()+File.separator+imageStr;
                String newFileAbsPath = mp4File.getParentFile().getAbsolutePath()+File.separator + newFileName;

*//*                ffmpeg -i 1.mp4 -vf "rotate=90*PI/180" 2.mp4        // 顺时针旋转90度
                ffmpeg -i 1.mp4 -vf "rotate=PI"      3.mp4          // 顺时针旋转180度
                ffmpeg -i 1.mp4 -vf "rotate=270*PI/180"  4.mp4      // 顺时针旋转270度*//*

                String command = "";

//                    command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() + " -vf \"rotate="+rotate+"*PI/180\" " + newFileAbsPath;


                System.out.println(command);
                execCMD(command);
            }*/



        }



    }


    static boolean  isVideoPort(File mp4File){
        boolean isport = true;

        Encoder encoder = new Encoder();

        try {
            MultimediaInfo m = encoder.getInfo(mp4File);

            VideoSize size =   m.getVideo().getSize();

            int height = size.getHeight();
            int width =   size.getWidth();
            if(height < width) {
                isport = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isport;

    }

    
    static long ReadVideoTimeWithMillSecond(File source) {
        Encoder encoder = new Encoder();
        long length = 0L;
        try {
            MultimediaInfo m = encoder.getInfo(source);
            length = m.getDuration();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    

    
    static String ReadVideoTime(File source) {
        Encoder encoder = new Encoder();
        String length = "";
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration()/1000;
            int hour = (int) (ls/3600);
            int minute = (int) (ls%3600)/60;
            int second = (int) (ls-hour*3600-minute*60);

            String hourStr =  hour >= 10?hour+"":"0"+hour;
            String minutesStr =  minute >= 10?minute+"":"0"+minute;
            String secondRestStr =  second >= 10?second+"":"0"+second;

            length = hourStr+":"+minutesStr+":"+secondRestStr+":";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    
    // 1h20m30s
    static String calTimeMillSecondAsShortString(long mTimeMillSecond) { // 毫秒
    	String timeResult = null ;

        int hour = (int) (mTimeMillSecond/ (3600 * 1000 ));
        int minute = (int)( (mTimeMillSecond - hour * 3600 * 1000)/ (60 * 1000) );
        int second = (int) ((mTimeMillSecond-hour*3600*1000-minute*60*1000)/1000);
        int millSecond = (int)(mTimeMillSecond%1000);
        
        System.out.println("mTimeMillSecond【"+mTimeMillSecond+"】 mTimeMillSecond【"+mTimeMillSecond+"】   hour【"+hour+"】  minute【"+minute+"】  second【"+second+"】 millSecond【"+millSecond+"】");
        if(hour !=0 || minute !=0 || second !=0 || millSecond !=0 ) {
        	
        String   fixedStr =  hour+"h"+minute+"m"+second+"s";
          return fixedStr;
        }
    	return "0s";
    	
    }
    
    
    static String calTimeMillSecondAsString(long mTimeMillSecond) { // 毫秒
    	String timeResult = null ;

        int hour = (int) (mTimeMillSecond/ (3600 * 1000 ));
        int minute = (int)( (mTimeMillSecond - hour * 3600 * 1000)/ (60 * 1000) );
        int second = (int) ((mTimeMillSecond-hour*3600*1000-minute*60*1000)/1000);
        int millSecond = (int)(mTimeMillSecond%1000);
        
        System.out.println("mTimeMillSecond【"+mTimeMillSecond+"】 mTimeMillSecond【"+mTimeMillSecond+"】   hour【"+hour+"】  minute【"+minute+"】  second【"+second+"】 millSecond【"+millSecond+"】");
        if(hour !=0 || minute !=0 || second !=0 || millSecond !=0 ) {
        	
            String hourStr =  hour >= 10?hour+"":"0"+hour;
            String minutesStr =  minute >= 10?minute+"":"0"+minute;
            String secondRestStr =  second >= 10?second+"":"0"+second;
            String millSecondRestStr =  millSecond >= 100 ? millSecond+"":(millSecond >= 10 ? ("0"+millSecond ):("00"+millSecond ));
          String   fixedStr =  hourStr+":"+minutesStr+":"+secondRestStr+"."+millSecondRestStr;
          return fixedStr;
        }
    	
    	return "00:00:00.000";
    	
    	
    }

    // 00:00:00 转为对应的毫秒数
    static long calVideoStringTimeAsMillSecond(String timeString) {
        long millSecondResult = 0;
        String fixedStr = timeString;
        
        if(timeString.contains(":") && timeString.indexOf(":") != timeString.lastIndexOf(":") ){
            // 输入的就是 时分秒
            fixedStr =   fixedStr.replace(" ","").trim();
            String[] timeStr =   fixedStr.split(":");


            int hourInt = Integer.parseInt(timeStr[0]);
            int minutes =  Integer.parseInt(timeStr[1]);
            int secondInt = Integer.parseInt(timeStr[2]);

            long matchMills =  hourInt * 60 * 60 *  1000  + minutes * 60 * 1000 + secondInt * 1000;
            
            return matchMills;

        }
        
        return millSecondResult;
      }
      
    
    
    // 对给定的 字符串 进行 时间 上的设置   总是输出  00:00:00 这样的字段
    static  String fixedTimeStr(String originStr){
        String fixedStr = originStr;
        if(originStr.contains(":") && originStr.indexOf(":") != originStr.lastIndexOf(":") ){
            // 输入的就是 时分秒
            fixedStr =   fixedStr.replace(" ","").trim();
            String[] timeStr =   fixedStr.split(":");


            int hourInt = Integer.parseInt(timeStr[0]);
            int minutes =  Integer.parseInt(timeStr[1]);
            int secondInt = Integer.parseInt(timeStr[2]);

            String hourStr =  hourInt >= 10?hourInt+"":"0"+hourInt;
            String minutesStr =  minutes >= 10?minutes+"":"0"+minutes;
            String secondRestStr =  secondInt >= 10?secondInt+"":"0"+secondInt;

            fixedStr =  hourStr+":"+minutesStr+":"+secondRestStr;
            return fixedStr;

        }else if(originStr.contains(":") && originStr.indexOf(":") == originStr.lastIndexOf(":")){
            // 输入的就是 分秒
            fixedStr = fixedStr.replace(" ","").trim();
            fixedStr = "00:"+fixedStr;
            return fixedTimeStr(fixedStr);
        }

        if(!originStr.contains(":")){
            // 输入的是秒数    需要转为  分秒:
            if(isNumeric(originStr)){
                int secondInt = Integer.parseInt(originStr);
                int hourInt = secondInt/3600;
                int minutes = secondInt/60;
                int secondRest = secondInt%60;
                String hourStr =  hourInt >= 10?hourInt+"":"0"+hourInt;
                String minutesStr =  minutes >= 10?minutes+"":"0"+minutes;
                String secondRestStr =  secondRest >= 10?secondRest+"":"0"+secondRest;

                fixedStr =  hourStr+":"+minutesStr+":"+secondRestStr;
                return fixedStr;

            }

        }

        return  fixedStr;

    }



    // UC 本地化  使 绝对路径转为 相对路径  方便查看  adb pull  /storage/emulated/0/UCDownloads/VideoData/ .  【   /storage/emulated/0/UCDownloads/VideoData/  转为 .  】
    class UC_OutPut_TS_Localized_6 extends  Basic_Rule{

        File VideoDataDirFile ;  //  out 输出文件夹 VideoDataDirFile

        File origin_abspath_path_Dir;
        File order_orgin_abspath_path_Dir;

        UC_OutPut_TS_Localized_6(){
            super(6);
        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return  "\n"+Cur_Bat_Name+ "  6    ## 把从UC 拉取出来的 VideoData 本地化(绝对路径转为相对路径) \nadb pull  /storage/emulated/0/UCDownloads/VideoData . && cd  ./VideoData  && "+Cur_Bat_Name +" 6  " +
                    "\n移动原有无规则命名的m3du 到 origin_abspath_m3du 文件夹中 (保留绝对路径) \n移动原有无规则命名的m3du改为有规则命名的 到 order_origin_abspath_m3du 中(保留绝对路径)" ;}


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule6 shellDir = "+ shellDir);
            System.out.println("rule6  otherParams = "+ otherParams.size());
            String curDirName = shellDir.getName();
            if(!"VideoData".equals(curDirName)){
                System.out.println("当前规则必须是在 UC 拉取下来的 VideoData文件中执行 ， 当前文件夹名字并不是 VideoData 而是 "+curDirName +" 请检查当前shell路径  程序退出!");
                return false;
            }
            VideoDataDirFile =  shellDir;
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }


        @Override
        void operationRule(ArrayList<String> inputParamsList) {

            origin_abspath_path_Dir = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+"origin_abspath_m3du");
            if(!origin_abspath_path_Dir.exists()){
                origin_abspath_path_Dir.mkdirs();
            }

            order_orgin_abspath_path_Dir = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+"order_origin_abspath_m3du");
            if(!order_orgin_abspath_path_Dir.exists()){
                order_orgin_abspath_path_Dir.mkdirs();
            }


            origin_abosolution_path_Dir_Operation();
            order_origin_abosolution_path_Dir_Operation();
            m3u8_Rename_PathFixed();
            System.out.println("导入 安卓 命令: ");
            System.out.println("adb push ./VideoData  /sdcard/UCDownloads/");
            System.out.println("导出 安卓 命令: ");
            System.out.println("adb push  /sdcard/UCDownloads/VideoData  .");
            System.out.println("连续导出 && 导出 安卓 命令: ");
            System.out.println("adb pull  /storage/emulated/0/UCDownloads/VideoData . && cd  ./VideoData  && "+Cur_Bat_Name +" 6  ");
        }

        void  order_origin_abosolution_path_Dir_Operation(){
            File[] TS_List = VideoDataDirFile.listFiles();
            System.out.println("outDir_Size  = "+TS_List.length);

            int curIndex = 0 ;
            for (int i = 0; i < TS_List.length ; i++) {
                File fileItem = TS_List[i];
                System.out.println("index["+i+"] : " + fileItem.getName());
                if(fileItem.getName().endsWith(".m3u8")) {
                    ArrayList<String> fixedStrArr = new  ArrayList<String>();
                    ArrayList<String> fixedM3U8_Content = ReadFileContentAsList(fileItem);

                    File originFile = new File(order_orgin_abspath_path_Dir.getAbsolutePath()+File.separator+getPaddingIntString(curIndex,4,"0",true)+".m3u8");
                    for (int j = 0; j < fixedM3U8_Content.size(); j++) {
                        String item = fixedM3U8_Content.get(j);
                        if(item.contains("./")){
                            String fixedPathItem = item.replace("./","/storage/emulated/0/UCDownloads/VideoData/");
                            fixedStrArr.add(fixedPathItem);
                            continue;
                        }
                        fixedStrArr.add(item);
                    }
                    System.out.println("index["+i+"] : " + fileItem.getName());
                    writeContentToFile(originFile,fixedStrArr);
                    curIndex++;
                }
            }

        }

        void  origin_abosolution_path_Dir_Operation(){
            File[] TS_List = VideoDataDirFile.listFiles();
            System.out.println("outDir_Size  = "+TS_List.length);

            for (int i = 0; i < TS_List.length ; i++) {
                File fileItem = TS_List[i];
                System.out.println("index["+i+"] : " + fileItem.getName());
                if(fileItem.getName().endsWith(".m3u8")) {
                    ArrayList<String> fixedStrArr = new  ArrayList<String>();
                    ArrayList<String> fixedM3U8_Content = ReadFileContentAsList(fileItem);

                    File originFile = new File(origin_abspath_path_Dir.getAbsolutePath()+File.separator+fileItem.getName());
                    for (int j = 0; j < fixedM3U8_Content.size(); j++) {
                        String item = fixedM3U8_Content.get(j);
                        if(item.contains("./")){
                            String fixedPathItem = item.replace("./","/storage/emulated/0/UCDownloads/VideoData/");
                            fixedStrArr.add(fixedPathItem);
                            continue;
                        }
                        fixedStrArr.add(item);
                    }
                    System.out.println("index["+i+"] : " + fileItem.getName());
                    writeContentToFile(originFile,fixedStrArr);
                }
            }

        }

        void m3u8_Rename_PathFixed(){

            File[] TS_List = VideoDataDirFile.listFiles();
            System.out.println("outDir_Size  = "+TS_List.length);
            int curIndex = 0 ;
            for (int i = 0; i < TS_List.length ; i++) {
                File fileItem = TS_List[i];
                System.out.println("index["+i+"] : " + fileItem.getName());
//            if(".m3u8".endsWith(fileItem.getName())){
                if(fileItem.getName().endsWith(".m3u8")){
                    ArrayList<String> fixedStrArr = new  ArrayList<String>();
                    ArrayList<String> fixedM3U8_Content = ReadFileContentAsList(fileItem);
                    boolean isJiaMiKey = checkM3U8URL(fixedM3U8_Content);

                    for (int j = 0; j < fixedM3U8_Content.size(); j++) {
                        String item = fixedM3U8_Content.get(j);
                        if(item.startsWith("#")){
                            fixedStrArr.add(item);
                            continue;
                        }
                        System.out.println("item = "+ item  + (item.endsWith(".ts")?"ts后缀":"非ts"));

/*                        if(item.endsWith(".ts")){
                            String newItem = item.replace(".ts","");
                            String path_1 = "./TS_Dir/"+newItem;
                            fixedStrArr.add(path_1);
                            continue;
                        }*/

                        if(item.startsWith("/storage/emulated/0/UCDownloads/VideoData/")){
                            item =  item.replace("//","/");
                            String fixedPathItem = item.replace("/storage/emulated/0/UCDownloads/VideoData//","./");
                            fixedPathItem = fixedPathItem.replace("/storage/emulated/0/UCDownloads/VideoData/","./");
                            fixedStrArr.add(fixedPathItem);
                            continue;
                        }
                        fixedStrArr.add(item);
                    }
                    System.out.println("index["+i+"] : " + fileItem.getName());
                    writeContentToFile(fileItem,fixedStrArr);


             /*       //  如果当前名字中包含中文   那么把中去去除

                    String fileName = fileItem.getName();
                    String type = getFileTypeWithPoint(fileName);
                    String fileNameOnly  = getFileNameNoPoint(fileName);
                    if(isContainChinese(fileName)){
                        String englishName = clearChinese(fileNameOnly)+"_"+getTimeStamp()+type;
                        englishName = englishName.replace(" ","");
                        englishName = englishName.replace("  ","");
                        englishName = englishName.replace("  ","");
                        englishName = englishName.replace(" ","");
                        englishName = englishName.replace("[","");
                        englishName = englishName.replace("]","");
                        englishName = englishName.replace("《","");
                        englishName = englishName.replace("》","");
                        englishName = englishName.replace("，","");
                        tryReName(fileItem,englishName);
                    }*/
                    String type = getFileTypeWithPoint(fileItem.getName());
                    String englishName = getPaddingIntString(curIndex,4,"0",true)+type;
                    if(isJiaMiKey){
                        englishName  = getPaddingIntString(curIndex,4,"0",true)+"_Key"+type;
                    }
                    tryReName(fileItem,englishName);
                    curIndex++;
                }

            }

        }


        boolean checkM3U8URL(ArrayList<String> contentList){
            boolean isUrlKey = false;
            for (int i = 0; i < contentList.size() ; i++) {
                String lineStr = contentList.get(i);
                if(lineStr.contains("EXT-X-KEY") && lineStr.contains("URI") ){
                    return true;
                }
            }
            return isUrlKey;
        }


    }


    static int Rule5_Order_Index = 1;



    //    ffmpeg -i 2.mp4 -c:v copy -c:a copy -bsf:v h264_mp4toannexb -f ssegment -segment_list ./out/2020_10_26_out.m3u8 -segment_time 10 ./out/TS_DIR/2020_10_26_out%03d.ts
    //  对当前 给定的 Mp4文件进行切割为ts文件  文件结构为  当前目录 ./out 【输出文件夹 包含m3u8 文件】  ./out/TS_Dir 【TS文件的输出文件夹  包含 TS 文件】
    //  对生成的 .m3du 文件  删除 .ts 后缀  增加 ./TS_Dir/前缀
    // 对生成的 ts  文件  把 后缀.ts 文件删除
    //  依据文件名  时间  2020_10_26_165720_文件名.m3u8    2020_10_26_165720_文件名_001.ts   2020_10_26_165720_文件名_002.ts   2020_10_26_165720_文件名_003.ts
    class MP4_To_TS_Rule_5 extends  Basic_Rule{
        ArrayList<File> mInputMediaFileList ;  // 输入的 视频文件
        Map<File,File> InputFile_OutDirMap ;   //  输入的视频文件 和输出文件夹集合
        File outDir ;  //  out 输出文件夹
        File out_TS_Dir;   // out 目录下 ts 文件列表


        MP4_To_TS_Rule_5(){
            super(5);
            mInputMediaFileList = new  ArrayList<File>();
            InputFile_OutDirMap  = new  HashMap<File,File>();
            outDir  = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+"VideoData");
            out_TS_Dir = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+"VideoData"+File.separator+"TS_Dir");
        }


        int   getNextOrderIndex(){
            return Rule5_Order_Index++;
        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return  "\n"+Cur_Bat_Name+ "  5     <mp4 输入1路径>  <mp4 输入2路径> <多路径MP4文件>      ## 把当前 mp4 视频 裁剪为ts文件 输出到文件夹 sky.mp4 --> sky_20201026/sky_20201026.m3u8 xxx1.ts xxx2.ts  \n【  adb push ./VideoData  /sdcard/UCDownloads/   】   \n" +
                    "\n"+Cur_Bat_Name+ "  5_all    ##  把当前 shell 目录下所有.mp4文件 作为输入参数 进行 MP4 转 ts 的 剪切    ## 把当前 mp4 视频 裁剪为ts文件 输出到文件夹  \n 【  adb push ./VideoData  /sdcard/UCDownloads/   】 \n"  ;
        }


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule5 shellDir = "+ shellDir);
            System.out.println("rule5  otherParams = "+ otherParams.size());

            if(type2Param.contains("all")){
                File[] shellDirList = CUR_Dir_FILE.listFiles();
                for (int i = 0; i < shellDirList.length; i++) {
                    File mp4Item = shellDirList[i];
                    if(mp4Item.getName().toLowerCase().endsWith(".mp4")){
                        mInputMediaFileList.add(mp4Item);
                    }
                }
            }

            if( mInputMediaFileList.size() == 0 && (otherParams == null || otherParams.size() ==0) ){
                errorMsg = "用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }



            System.out.println("rule5 otherParams.size() = "+ otherParams.size());


            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("curAbsPath  = "+ curAbsPath);
                if(curFIle.exists() && curFIle.getName().toLowerCase().endsWith(".mp4") ){  // 判断
                    mInputMediaFileList.add(curFIle);
                }
            }
            if(mInputMediaFileList.size() == 0){
                errorMsg = "当前从参数找不到对应的输入源 .mp4  .flv .rmvb .avi 文件 ";
                System.out.println(errorMsg);
                return false;
            }
            System.out.println("rule5 checkParamsOK mInputMediaFileList.size() = "+ mInputMediaFileList.size());
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }


        @Override
        void operationRule(ArrayList<String> inputParamsList) {

            if(!outDir.exists()){
                outDir.mkdirs();
            }
            if(!out_TS_Dir.exists()){
                out_TS_Dir.mkdirs();
            }

            //     ffmpeg -i sky1.mp4  image%d.jpg    抠图

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule5 curInputFileList.size() = "+mInputMediaFileList.size());
            System.out.println("rule5 ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt
            for (int i = 0; i < mInputMediaFileList.size(); i++) {

//    ffmpeg -i 2.mp4 -c:v copy -c:a copy -bsf:v h264_mp4toannexb -f ssegment -segment_list ./out/2020_10_26_out.m3u8 -segment_time 30 ./out/TS_DIR/2020_10_26_out%03d.ts

                File mp4File = mInputMediaFileList.get(i);
                StringBuilder sb =new StringBuilder();

                String originName = mp4File.getName();
                String noPointFileName = getFileNameNoPoint(mp4File.getName());

                String fileTimeStr =  getTimeStamp();
                String yyymmdd_str =  getTimeStamp_YYYMMDD();
                String hhmmss_str =  getTimeStamp_HHmmss();
                int orderIndex = getNextOrderIndex();
                fileTimeStr = yyymmdd_str+"_"+ getPaddingIntString(orderIndex,5,"0",true)+"_"+hhmmss_str;
                String m3u8FileName = "./VideoData/"+fileTimeStr+"_"+noPointFileName+".m3u8";// zzzz

                String ts_FileName = "./VideoData/TS_DIR/"+fileTimeStr+"_"+noPointFileName+"_"+"%03d.ts";

                String command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() + " -c:v copy -c:a copy -bsf:v h264_mp4toannexb -f ssegment -segment_list "+ m3u8FileName+" "+ " -segment_time 30 "+ ts_FileName;

                System.out.println(command);
                execCMD(command);
            }
            System.out.println("等待程序执行1秒");
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }

            tsFile_TryRename();
            m3u8_Fixed();
            System.out.println("导入 安卓 命令: ");
            System.out.println("adb push ./VideoData  /sdcard/UCDownloads/");
        }

        void tsFile_TryRename(){
            File[] TS_List = out_TS_Dir.listFiles();
            System.out.println("TS_FILE_SIZE = "+TS_List.length);
            for (int i = 0; i < TS_List.length ; i++) {
                File fileItem = TS_List[i];
                System.out.println("index["+i+"] : " + fileItem.getName());
//            if(".ts".endsWith(fileItem.getName())){
                if(fileItem.getName().endsWith(".ts")){
                    String newName = fileItem.getName().replace(".ts","");
                    System.out.println("index["+i+"] : oldName="+fileItem.getName() +"   newName:"+newName);
                    tryReName(fileItem,newName);

                }
            }
        }

        void m3u8_Fixed(){

            File[] TS_List = outDir.listFiles();
            System.out.println("outDir_Size  = "+TS_List.length);
            for (int i = 0; i < TS_List.length ; i++) {
                File fileItem = TS_List[i];
                System.out.println("index["+i+"] : " + fileItem.getName());
//            if(".m3u8".endsWith(fileItem.getName())){
                if(fileItem.getName().endsWith(".m3u8")){
                    ArrayList<String> fixedStrArr = new  ArrayList<String>();
                    ArrayList<String> fixedM3U8_Content = ReadFileContentAsList(fileItem);

                    for (int j = 0; j < fixedM3U8_Content.size(); j++) {
                        String item = fixedM3U8_Content.get(j);
                        if(item.startsWith("#")){
                            fixedStrArr.add(item);
                            continue;
                        }
                        System.out.println("item = "+ item  + (item.endsWith(".ts")?"ts后缀":"非ts"));
                        if(item.endsWith(".ts")){
                            String newItem = item.replace(".ts","");
                            String path_1 = "./TS_Dir/"+newItem;
                            fixedStrArr.add(path_1);
                            continue;
                        }
                        fixedStrArr.add(item);
                    }
                    System.out.println("index["+i+"] : " + fileItem.getName());
                    writeContentToFile(fileItem,fixedStrArr);
                }
                //  如果当前名字中包含中文   那么把中去去除

                String fileName = fileItem.getName();
                String type = getFileTypeWithPoint(fileName);
                String fileNameOnly  = getFileNameNoPoint(fileName);
                if(isContainChinese(fileName)){
                    String englishName = clearChinese(fileNameOnly)+"_"+getTimeStamp()+type;
                    tryReName(fileItem,englishName);
                }
            }

        }

    }


    static String getTimeStamp_YYYMMDD(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static String getTimeStamp_HHmmss(){

        SimpleDateFormat df = new SimpleDateFormat("HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static String getTimeStamp(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static String getTimeStamp_yyyyMMdd_HHmmss(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static String getTimeStamp_yyyyMMdd_HHmmssSSS(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");//设置日期格式
        String date = df.format(new Date());
        return date;
    }
    
    class VideoRoast_Rule_4 extends  Basic_Rule{
        ArrayList<File> mInputMediaFileList ;  // 输入的 视频文件
        int rotate = 90;

        VideoRoast_Rule_4(){
            super(4);
            mInputMediaFileList = new  ArrayList<File>();
            rotate = 90;
        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return  "\n"+Cur_Bat_Name+ "  4       <mp4,flv,avi.rmvb 路径>      ## 把当前 mp4 avi flv rmvb 视频 旋转90度输出 sky.mp4 --> sky_90_20200304020201.mp4  \n"+
                    "\n"+Cur_Bat_Name+ "  4_r90   <mp4,flv,avi.rmvb 路径>      ## 把当前 mp4 avi flv rmvb 视频 旋转90( r90 顺时针90度)度输出 sky.mp4 --> sky_90_20200304020201.mp4  \n" +
                    "\n"+Cur_Bat_Name+ "  4_r180   <mp4,flv,avi.rmvb 路径>     ## 把当前 mp4 avi flv rmvb 视频 旋转180( r180 顺时针180度)度输出 sky.mp4 --> sky_180_20200304020201.mp4   \n" +
                    "\n"+Cur_Bat_Name+ "  4_r270   <mp4,flv,avi.rmvb 路径>     ## 把当前 mp4 avi flv rmvb 视频 旋转270( r270 顺时针270度)度输出 sky.mp4 --> sky_270_20200304020201.mp4    \n" ;
        }


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule4 shellDir = "+ shellDir);
            System.out.println("rule4  otherParams = "+ otherParams.size());


            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }

            if(type2Param.contains("r")){
                String rotateStr = type2Param.substring(type2Param.indexOf("r")+1);
                rotate = Integer.parseInt(rotateStr);
            }else{
                rotate = 90;
            }


            System.out.println("rule4 otherParams.size() = "+ otherParams.size());
            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("curAbsPath  = "+ curAbsPath);
                if(curFIle.exists() && videoTypeList.contains(getFileTypeWithPoint(curFIle.getName())) ){  // 判断
                    mInputMediaFileList.add(curFIle);
                }
            }
            if(mInputMediaFileList.size() == 0){
                errorMsg = "当前从参数找不到对应的输入源 .mp4  .flv .rmvb .avi 文件 ";
                System.out.println(errorMsg);
                return false;
            }
            System.out.println("rule4 checkParamsOK mInputMediaFileList.size() = "+ mInputMediaFileList.size());
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }


        @Override
        void operationRule(ArrayList<String> inputParamsList) {



            //     ffmpeg -i sky1.mp4  image%d.jpg    抠图

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule4 curInputFileList.size() = "+mInputMediaFileList.size());
            System.out.println("rule4 ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt
            for (int i = 0; i < mInputMediaFileList.size(); i++) {

                File mp4File = mInputMediaFileList.get(i);
                StringBuilder sb =new StringBuilder();

                String originName = mp4File.getName();
                String noPointFileName = getFileNameNoPoint(originName);
                String type = getFileTypeWithPoint(mp4File.getName());
//                File jpgDirFile = new File(CUR_Dir_1_PATH+File.separator+noPointFileName+"_"+DateFormat.format(new Date())+File.separator);
//                jpgDirFile.mkdirs();
//                String newFileName = originName+"_mp3_"+DateFormat.format(new Date())+".mp3";
                String newFileName = originName.replace(type,"_"+rotate+"_"+DateFormat.format(new Date())+type);
//                String newFileName = mp4File.getName().replace(".mp4","_x"+bigNum+"_"+DateFormat.format(new Date())+".mp4");     //  新的文件的名称  2.mp4 2_mergedxxxxxxxxxx.mp4
//                String imageStr = noPointFileName+"_%d.jpg";
//                String absImagePath = jpgDirFile.getAbsolutePath()+File.separator+imageStr;
                String newFileAbsPath = mp4File.getParentFile().getAbsolutePath()+File.separator + newFileName;

/*                ffmpeg -i 1.mp4 -vf "rotate=90*PI/180" 2.mp4        // 顺时针旋转90度
                ffmpeg -i 1.mp4 -vf "rotate=PI"      3.mp4          // 顺时针旋转180度
                ffmpeg -i 1.mp4 -vf "rotate=270*PI/180"  4.mp4      // 顺时针旋转270度*/

                String command = "";
                if(rotate == 90 || rotate == 270){
                    command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() + " -vf \"rotate="+rotate+"*PI/180:ow=ih:oh=iw\"  " + newFileAbsPath;

                }else{
                    command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() + " -vf \"rotate="+rotate+"*PI/180\" " + newFileAbsPath;
                }

                System.out.println(command);
                execCMD(command);
            }
        }



    }

    // 传入视频文件  获取该视频文件的 所有 图片帧
    //   ffmpeg -i sky1.mp4  image%d.jpg     //  把 mp4视频文件每个帧 都抠出来

    //   ffmpeg -i sky1.mp4 -r 1 image%d.jpg   // 每秒输入一张图片
    class GETJPGFrame4Video_Rule_3 extends  Basic_Rule{
        ArrayList<File> mInputMediaFileList ;  // 输入的 视频文件
        float  secondInteval = 0 ; //  视频截取图片  时间间隔
        GETJPGFrame4Video_Rule_3(){
            super(3);
            mInputMediaFileList = new  ArrayList<File>();
            secondInteval = 0 ;
        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return  "\n"+Cur_Bat_Name+ "  3   <mp4,flv,avi.rmvb 路径>      ## 把当前 mp4 avi flv rmvb 视频文件的每个帧抠出来(默认 每秒25帧) 保存在 相同文件目录 sky.mp4 --> sky_20200304020201/sky_1.jpg sky_2.jpg  \n"+
                    "\n"+Cur_Bat_Name+ "  3_s1   <mp4,flv,avi.rmvb 路径>      ## 把当前 mp4 avi flv rmvb 视频文件的每隔1秒( s1 每秒1帧) sky.mp4 --> sky_20200304020201/sky_1.jpg sky_2.jpg  \n" +
                    "\n"+Cur_Bat_Name+ "  3_s0.2   <mp4,flv,avi.rmvb 路径>      ## 把当前 mp4 avi flv rmvb 视频文件的每隔1秒( s0.2  5秒一帧 每秒0.2帧 ) sky.mp4 --> sky_20200304020201/sky_1.jpg sky_2.jpg  \n" +
                    "\n"+Cur_Bat_Name+ "  3_s0.1   <mp4,flv,avi.rmvb 路径>      ## 把当前 mp4 avi flv rmvb 视频文件的每隔1秒( s0.1  10秒一帧 每秒0.1帧 ) sky.mp4 --> sky_20200304020201/sky_1.jpg sky_2.jpg  \n" +
                    "\n"+Cur_Bat_Name+ "  3_s0.01   <mp4,flv,avi.rmvb 路径>      ## 把当前 mp4 avi flv rmvb 视频文件的每隔1秒( s0.01  100秒一帧  每秒0.01帧 ) sky.mp4 --> sky_20200304020201/sky_1.jpg sky_2.jpg  \n"
                    ;
        }

        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule3 shellDir = "+ shellDir);
            System.out.println("rule3  otherParams = "+ otherParams.size());


            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }

            if(type2Param.contains("s")){
                String bigNumStr = type2Param.substring(type2Param.indexOf("s")+1);
                secondInteval = Float.parseFloat(bigNumStr);
            }else{
                secondInteval = 0;
            }


            System.out.println("rule3 otherParams.size() = "+ otherParams.size());
            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("curAbsPath  = "+ curAbsPath);
                if(curFIle.exists() && videoTypeList.contains(getFileTypeWithPoint(curFIle.getName())) ){  // 判断
                    mInputMediaFileList.add(curFIle);
                }
            }
            if(mInputMediaFileList.size() == 0){
                errorMsg = "当前从参数找不到对应的输入源 .mp4  .flv .rmvb .avi 文件 ";
                System.out.println(errorMsg);
                return false;
            }
            System.out.println("rule3 checkParamsOK mInputMediaFileList.size() = "+ mInputMediaFileList.size());
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }


        @Override
        void operationRule(ArrayList<String> inputParamsList) {



            //     ffmpeg -i sky1.mp4  image%d.jpg    抠图

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule3 curInputFileList.size() = "+mInputMediaFileList.size());
            System.out.println("rule3 ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt
            for (int i = 0; i < mInputMediaFileList.size(); i++) {

                File mp4File = mInputMediaFileList.get(i);
                StringBuilder sb =new StringBuilder();

                String originName = mp4File.getName();
                String noPointFileName = getFileNameNoPoint(originName);
                String type = getFileTypeWithPoint(mp4File.getName());
                File jpgDirFile = new File(CUR_Dir_1_PATH+File.separator+noPointFileName+"_"+DateFormat.format(new Date())+File.separator);
                jpgDirFile.mkdirs();
//                String newFileName = originName+"_mp3_"+DateFormat.format(new Date())+".mp3";
//                String newFileName = originName.replace(type,"_mp3_"+DateFormat.format(new Date())+".mp3");
//                String newFileName = mp4File.getName().replace(".mp4","_x"+bigNum+"_"+DateFormat.format(new Date())+".mp4");     //  新的文件的名称  2.mp4 2_mergedxxxxxxxxxx.mp4

                String imageStr = noPointFileName+"_%d.jpg";
                String absImagePath = jpgDirFile.getAbsolutePath()+File.separator+imageStr;



                String command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() +(secondInteval == 0f ?"":" -r " + secondInteval)+"  " + absImagePath;

                System.out.println(command);
                execCMD(command);
            }
        }

    }

    //  提供一个 mp4 rmvb avi flv 文件  输入该文件音频mp3文件
    class GETMP3_Rule_2 extends  Basic_Rule{
        ArrayList<File> mInputMediaFileList ;  // 输入的 视频文件



        GETMP3_Rule_2(){
            super(2);
            mInputMediaFileList = new  ArrayList<File>();
        }

        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule2 shellDir = "+ shellDir);
            System.out.println("rule2  otherParams = "+ otherParams.size());


            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }


            System.out.println("otherParams.size() = "+ otherParams.size());
            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("curAbsPath  = "+ curAbsPath);
                if(curFIle.exists() && videoTypeList.contains(getFileTypeWithPoint(curFIle.getName())) ){  // 判断
                    mInputMediaFileList.add(curFIle);
                }
            }
            if(mInputMediaFileList.size() == 0){
                errorMsg = "当前从参数找不到对应的输入源 .mp4  .flv .rmvb .avi 文件 ";
                System.out.println(errorMsg);
                return false;
            }
            System.out.println("rule2 checkParamsOK mInputMediaFileList.size() = "+ mInputMediaFileList.size());
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return  "\n"+Cur_Bat_Name+ "  2   <mp4,flv,avi,rmvb,m4a 路径>       ## 把当前 mp4 avi flv rmvb m4a 视频文件 分离出它的 mp3音频文件 \n" ;
        }


        @Override
        void operationRule(ArrayList<String> inputParamsList) {


            //  ffmpeg -i sky1.mp4 -b:a 128k output.mp3     // 把mp4文件的音频分离出来 单独生成 mp3 文件

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("rule2 curInputFileList.size() = "+mInputMediaFileList.size());
            System.out.println("rule2 ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt
            for (int i = 0; i < mInputMediaFileList.size(); i++) {

                File mp4File = mInputMediaFileList.get(i);
                StringBuilder sb =new StringBuilder();

                String originName = mp4File.getName();
                String type = getFileTypeWithPoint(mp4File.getName());
//                String newFileName = originName+"_mp3_"+DateFormat.format(new Date())+".mp3";
                String newFileName = originName.replace(type,"_mp3_"+DateFormat.format(new Date())+".mp3");
//                String newFileName = mp4File.getName().replace(".mp4","_x"+bigNum+"_"+DateFormat.format(new Date())+".mp4");     //  新的文件的名称  2.mp4 2_mergedxxxxxxxxxx.mp4

                String command = ffmpeg_path +" -i "+ mp4File.getAbsolutePath() +" -b:a  128k  " + CUR_Dir_1_PATH+File.separator+newFileName;

                System.out.println(command);
                execCMD(command);
            }
        }



    }


    //  提供一个 mp4 文件  输入该文件的两倍长度的文件
    class MergeMP4_Rule_1 extends  Basic_Rule{
        ArrayList<File> curInputFileList ;
        File G8_1_Rule_File ;
        int bigNum ;
        MergeMP4_Rule_1(){
            super(1);
            curInputFileList = new  ArrayList<File>();
            G8_1_Rule_File = new File(zbinPath+File.separator+"G8_1_MergedRule.txt");
            bigNum = 1;
        }


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            System.out.println("rule1 shellDir = "+ shellDir);
            System.out.println("rule1 otherParams = "+ otherParams.size());
            if(type2Param.contains("x")){
                String bigNumStr = type2Param.substring(type2Param.indexOf("x")+1);
                bigNum = Integer.parseInt(bigNumStr);
            }else{
                bigNum = 1;
            }
            if(!G8_1_Rule_File.exists()){
                try {  //   创建  文件输入的    放置 file 'C:\Users\zhuzj5\Desktop\testA\1\1.mp4'
                    G8_1_Rule_File.createNewFile();
                } catch (IOException e) {
                    System.out.println("rule1 创建文件 " + G8_1_Rule_File.getAbsolutePath() +"失败!");
                    e.printStackTrace();
                }
            }

            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "rule1 用户输入的文件参数为空";
                System.out.println(errorMsg);
                return false;
            }


            System.out.println("rule1 otherParams.size() = "+ otherParams.size());
            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                System.out.println("rule1 curAbsPath  = "+ curAbsPath);
                String curType = getFileTypeWithPoint(curFIle.getName());
                System.out.println("rule1 curType  = "+ curType);
                if(curFIle.exists() && mediaTypeList.contains(curType)){
                    curInputFileList.add(curFIle);
                }
            }
            if(curInputFileList.size() == 0){
                errorMsg = "rule1 当前从参数找不到对应的输入源 .mp4 .mp3 .avi  ..多媒体文件 ";
                System.out.println(errorMsg);
                return false;
            }
            System.out.println("rule1  checkParamsOK curInputFileList.size() = "+ curInputFileList.size());
            return  super.checkParamsOK(shellDir,type2Param,otherParams);
        }


        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return  "\n"+Cur_Bat_Name+ "  1   <mp3,mp4,flv.avi,rmvb,wav,aac...路径>       ## 把当前多媒体<mp4...mp3>文件重复显示合并 1分钟-》2分钟 \n" +
                    "\n"+Cur_Bat_Name+ "  1_x10   <mp3,mp4,flv.avi,rmvb,wav,aac...路径>   ## 把当前多媒体<mp4...mp3>文件重复10变显示合并 1分钟-》10分钟 \n" ;
        }



        @Override
        void operationRule(ArrayList<String> inputParamsList) {

            //
            //  ffmpeg -f concat -i G8_MergedRule1.txt -c copy output.mp4

            //  ffmpeg  -f当前从参数找不到对应的输入源 concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\G8_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output2.mp4
// file 'C:\Users\zhuzj5\Desktop\testA\1\1.mp4'
            // C:\Users\zhuzj5\Desktop\zbin\G8_1_MergedRule.txt

            String ffmpeg_path = getEnvironmentExePath("ffmpeg");
            if(ffmpeg_path ==null){
                errorMsg = "当前 ffmpeg 不在环境变量中 请下载该库 并添加到 环境变量中";
                System.out.println(errorMsg);
                return;
            }
            System.out.println("curInputFileList.size() = "+curInputFileList.size());
            System.out.println("ffmpeg_path = "+ffmpeg_path);
            // 把 当前的 mp4 文件写入 G8_1_MergedRule.txt
            for (int i = 0; i < curInputFileList.size(); i++) {

                File mp4File = curInputFileList.get(i);
                StringBuilder sb =new StringBuilder();
                String typePoint = getFileTypeWithPoint(mp4File.getName());
//                sb.append("file '"+mp4File.getAbsolutePath()+"'\n");
                for (int j = 0; j < bigNum; j++) {
                    sb.append("file '"+mp4File.getAbsolutePath()+"'\n");
                }

                String originFIleTxt =sb.toString();
                writeContentToFile(G8_1_Rule_File , originFIleTxt);
                String newFileName = mp4File.getName().replace(typePoint,"_x"+bigNum+"_"+DateFormat.format(new Date())+typePoint);     //  新的文件的名称  2.mp4 2_mergedxxxxxxxxxx.mp4

                String command = ffmpeg_path +"  -f concat -safe 0 -i "+ G8_1_Rule_File.getAbsolutePath() +" -c copy " + CUR_Dir_1_PATH+File.separator+newFileName;

                System.out.println(command);
                execCMD(command);
            }
        }
    }




    class Basic_Rule extends Rule {
        Basic_Rule(String ctype, int cindex,int opera_type){
            this.file_type = ctype;
            this.rule_index = cindex;
            this.operation_type = opera_type;
            this.identify = this.file_type+""+this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
        }

        Basic_Rule(int ruleIndex){
            this.rule_index = ruleIndex;
            this.file_type = "";
            this.operation_type = 0;
            this.identify = this.file_type+""+this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
            errorMsg = "";
        }

        String applyStringOperationRule1(String origin){
            return origin;
        }

        File applyFileByteOperationRule2(File originFile){
            return originFile;
        }

        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap ){
            return subFileList;
        }

        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            return curFileList;
        }


        void initParams4InputParam(String inputParam){}

        String simpleDesc(){
            return null;
        }

        void operationRule(ArrayList<String> inputParamsList){}


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            return true;  // 默认返回通过   不检查参数   如果有检查的需求 那么就实现它
        }

        @Override
        void showWrongMessage() {
            System.out.println("当前 type 索引 "+rule_index +" 执行错误  可能是输入参数错误 请检查输入参数!");
            System.out.println("ErrorMsg:"+ errorMsg);
        }

        String ruleTip(String type,int index , String batName,OS_TYPE curType){
            String itemDesc = "";
            if(curType == OS_TYPE.Windows){
                itemDesc = batName.trim()+".bat  "+type+"_"+index + "    [索引 "+index+"]  描述:"+simpleDesc();
            }else{
                itemDesc = batName.trim()+".sh "+type+"_"+index + "    [索引 "+index+"]  描述:"+simpleDesc();
            }

            return itemDesc;
        }

        boolean tryReName(File curFile , String newName){
            String newFilePath = curFile.getParent() + File.separator + newName;
            String oldName = curFile.getName();
            File newFile = new File(newFilePath);
            if(newFile.exists() && newFilePath.equals(curFile.getAbsolutePath()) ){

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
                System.out.println("当前目录已存在重命名后的文件  文件名称:"+ curFile.getName());
                return false;    // 已经存在的文件不处理 直接返回

            }
            boolean flag =   curFile.renameTo(newFile);
            if(flag){
                System.out.println(oldName+" 转为 "+ newFilePath +" 成功！");
                curFixedFileList.add(curFile);
            }else{
                System.out.println(oldName+" 转为 "+ newFilePath +" 失败！");
            }
            return flag;
        }
    }

    abstract  class Rule{

        int rule_index;   //  rule_index  组成了最基础的唯一键 rule_index 就是唯一的序号  1 2 3 4 5 6 7

        // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
        // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件)   // 5. 从shell 中获取到的路径 去对某一个文件进行操作

        int operation_type;  // 默认为0
        String file_type;   // * 标识所有的文件类型   以及当前操作类型文件  或者 单独的文件过滤类型
        String identify;
        String errorMsg;
        ArrayList<String> curFilterFileTypeList;  //  当前的文件过滤类型   多种文件过滤类型  例如把 多种格式 jpeg png 转为 jpg 时 使用到
        ArrayList<File> curFixedFileList;  // 当前修改操作成功的集合
        ArrayList<File> inputFileList ;  // 从输入参数得到的文件的集合
        abstract    void operationRule(ArrayList<String> inputParamsList);
        abstract    String applyStringOperationRule1(String origin);
        abstract    File applyFileByteOperationRule2(File originFile);
        abstract    ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap);
        abstract    ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList , HashMap<String, ArrayList<File>> subFileTypeMap , ArrayList<File> curDirList ,ArrayList<File> curRealFileList);
        abstract    void initParams4InputParam(String inputParam);  // 初始化Rule的参数 依据输入的字符串
        abstract    String ruleTip(String type,int index , String batName,OS_TYPE curType);  // 使用说明列表  如果覆盖 那么就不使用默认的说明 , 默认就一种情况
        abstract    String simpleDesc();  // 使用的简单描述  中文的该 rule的使用情况  默认会在 ruleTip 被调用
        abstract    boolean checkParamsOK(File shellDir,String type2Param,ArrayList<String> otherParams);
        abstract    void showWrongMessage();
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
                //    System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> ReadFileContentAsList( File mFilePath) {

        if (mFilePath != null  && mFilePath.exists()) {
            //  System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 "+ mFilePath.getAbsolutePath() );

            return null;
        }
        ArrayList<String> contentList= new ArrayList<String>();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null ) {
                    continue;
                }

                contentList.add(oldOneLine);
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;

            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentList;

    }


    public static String ReadFileContent( File mFilePath) {

        if (mFilePath != null  && mFilePath.exists()) {
            //  System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 "+ mFilePath.getAbsolutePath() );

            return null;
        }
        StringBuilder sb= new StringBuilder();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                    continue;
                }

                sb.append(oldOneLine+"\n");
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;


            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }





    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    static void writeContentToFile(File file, ArrayList<String> strList) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            sb.append(strList.get(i) + "\n");
        }
        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(sb.toString());
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


    static  String  getPaddingIntString(int index , int padinglength , String oneStr , boolean dirPre){
        String result = ""+index;
        int length = (""+index).length();

        if(length < padinglength){
            int distance = padinglength  - length;
            for (int i = 0; i < distance; i++) {
                if(dirPre){
                    result = oneStr+result;
                }else{
                    result = result + oneStr;
                }

            }

        }
        return result;

    }









    static ArrayList<File> getAllSubFile(File dirFile ,String aospPath , ArrayList<String> typeList) {
        if(aospPath == null || "".equals(aospPath)){
            return getAllSubFile(dirFile.getAbsolutePath(), "", typeList);
        }
        return getAllSubFile(dirFile.getAbsolutePath(), aospPath, typeList);

    }

    static ArrayList<File> getAllSubFile(String rootPath, String aospItemPath,  ArrayList<String>  typeList) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath + File.separator + aospItemPath);

        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();
                    //System.out.println("pathString = " + fileString);
                    for (int i = 0; i < typeList.size(); i++) {
                        String type =  typeList.get(i);
                        if("*".equals(type)){  // 如果 类型是 * 那么就把 所有的 非目录文件加入列表中
                            File curFile =    new File(fileString);
                            if(!curFile.isDirectory()){
                                allFile.add(curFile);
                                break;
                            }


                        }else {
                            if (fileString.endsWith(type)) {
                                allFile.add(new File(fileString));
                                break;
//                         System.out.println("file found at path: " + file.toAbsolutePath());
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allFile;


    }



    static void showNoTypeTip() {

        System.out.println("当前用户输入的 操作type无法检测到-它是一个int值  \n type 必须是当前 " + Cur_Bat_Name+" 的 第一个输入参数中的第一个int值 ");
        System.out.println("请检查输入参数后重新执行命令!");

    }
    static void showTip() {
        System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
        System.out.println("当前已实现的替换逻辑如下:\n");

        int count = 1;
        System.out.println("═══════════════════"+"使用方法列表 Begin"+"═══════════════════"+"\n");
        for (int i = 0; i < CUR_RULE_LIST.size() ; i++) {
            Rule itemRule = CUR_RULE_LIST.get(i);
            String type =  itemRule.file_type;
            int index =  itemRule.rule_index;
            String desc =  itemRule.ruleTip(type , index ,Cur_Bat_Name,CUR_OS_TYPE);

/*
            String itemDesc = "";
           if(CUR_OS_TYPE == OS_TYPE.Windows){
                itemDesc = "zrule_apply_G8.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_G8 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }
           */
            System.out.println(desc+"\n");
            count++;
        }
        System.out.println("═══════════════════"+"使用方法列表 End "+"═══════════════════"+"\n");

    }





    static int calculInputTypeIndex(String inputParams){
        if(inputParams == null){
            return 0;
        }
        if(isNumeric(inputParams)){
            return Integer.parseInt(inputParams);
        }
        if(inputParams.contains("_")){
//            System.out.println("calculInputTypeIndexA  = "+ inputParams);
            String[] mTypeParamArr = inputParams.split("_");
            if(mTypeParamArr.length == 0 ){
                return 0;
            }

            for (int i = 0; i < mTypeParamArr.length; i++) {
                String curPositionStr = mTypeParamArr[i];
//                System.out.println("calculInputTypeIndexB  = "+ curPositionStr);
                if(isNumeric(curPositionStr)){
                    return  Integer.parseInt(curPositionStr);
                }
            }
        }

        return 0;

    }
    public static void main(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else if(i == 1){  // 第二个参数是用来 对 当前功能进行分类使用的
                    CUR_TYPE_2_ParamsStr = args[i];
                    //zukgit1    计算得到 当前 索引的列表   首先遇到的第一个数字类型  1_2112  那就是索引1  附带参数 2112   temp_2_
                    CUR_TYPE_INDEX = calculInputTypeIndex(CUR_TYPE_2_ParamsStr);
                    System.out.println("calculInputTypeIndex - > CUR_TYPE_INDEX  = "+ CUR_TYPE_INDEX);
                } else {
                    if(!"".equals(args[i])){
                        CUR_INPUT_3_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_3_ParamStrList
                    }
                }
            }
        }
        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new  File(CUR_Dir_1_PATH);



        G8_FFmpeg_Operation mG8_Object = new G8_FFmpeg_Operation();
        mG8_Object.InitRule();
        // 用户没有输入参数
        if (CUR_TYPE_INDEX == 0 &&  CUR_INPUT_3_ParamStrList.size() == 0) {
            showTip();
            return;
        }


        if(CUR_TYPE_INDEX == 0){
            showNoTypeTip();
            return;
        }




        CUR_Selected_Rule = getRuleByIndex(CUR_TYPE_INDEX);  //  获取用户选中的 规则

// 检测是否 包含 该文件
        if(!isContainEnvironment("FFmpeg") && CUR_OS_TYPE == OS_TYPE.Windows){
            String  errorMsg = " 当前Windows版本下  merge合并操作依赖环境变量 FFmpeg 请添加环境变量";
            System.out.println(errorMsg);
            return ;
        }

        // 让各个规则自己去检测 自己需要的参数是否得到满足 并自己提示  给出 1.当前cmd路径下的文件  2.typeIndex 字符串   3.之后的输入参数
        if (CUR_Selected_Rule == null || !CUR_Selected_Rule.checkParamsOK(CUR_Dir_FILE,CUR_TYPE_2_ParamsStr,CUR_INPUT_3_ParamStrList)) {
            CUR_Selected_Rule.showWrongMessage();   // 提示当前规则的错误信息
            return;
        }



/*
        if(!checkInputParamsOK()){
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }
*/



        if (!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory() ) {
            System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH+"  不存在! ");
            return;
        }



        CUR_Selected_Rule.operationRule(CUR_INPUT_3_ParamStrList);  // 传递参数列表 进行处理



        setProperity();
    }


    static  void addCurFileTypeMapItemWithKey(String keyType, File curFile) {
        if (CUR_Dir_FILETypeMap.containsKey(keyType)) {
            ArrayList<File> fileList = CUR_Dir_FILETypeMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            CUR_Dir_FILETypeMap.put(keyType, fileList);
        }
    }

    static void  initFileTypeMap(ArrayList<File> subFileList){
        for (File curFile : subFileList) {
            String fileName = curFile.getName();
            if (!fileName.contains(".")) {
                addCurFileTypeMapItemWithKey("unknow", curFile);
            } else {
                String suffix = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
                addCurFileTypeMapItemWithKey(suffix, curFile);
            }
        }


    }

    static String OriApplyOperationRule(String mType ,String index  , String mOriContent){
        String identy = mType.trim()+index.trim();
        Rule applayRule = getRuleByIdentify(identy);
        if(applayRule == null){
            System.out.println("没有查询到 identy ="+ identy+"对应的处理规则");
            return mOriContent;
        }
        return  applayRule.applyStringOperationRule1(mOriContent);
    }




    static Rule getRuleByIndex(int index){
        for (int i = 0; i <CUR_RULE_LIST.size() ; i++) {
            if(CUR_RULE_LIST.get(i).rule_index == index){
                return CUR_RULE_LIST.get(i);
            }
        }
        return null;
    }


    /**
     * 执行 mac(unix) 脚本命令~
     *
     * @param command
     * @return
     */
    public static String execCMD_Mac(String command) {
        String[] cmd = { "/bin/bash" };
        Runtime rt = Runtime.getRuntime();
        Process proc = null;
        try {
            proc = rt.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 打开流
        OutputStream os = proc.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

        try {
            bw.write(command);

            bw.flush();
            bw.close();

            /** 真奇怪，把控制台的输出打印一遍之后竟然能正常终止了~ */
//            readConsole(proc);

            /** waitFor() 的作用在于 java 程序是否等待 Terminal 执行脚本完毕~ */
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int retCode = proc.exitValue();
        if (retCode != 0) {
            System.out.println("unix script retCode = " + retCode);

//            System.out.println(readConsole(proc));
            System.out.println("UnixScriptUil.execute 出错了!!");
        }
        return retCode + "";
    }

    public static String execCMDNoStart(String command) {
        StringBuilder sb =new StringBuilder();

        if(CUR_OS_TYPE == OS_TYPE.Windows){
            try {
                Process process=Runtime.getRuntime().exec("cmd /c  "+command);
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while((line=bufferedReader.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
            } catch (Exception e) {
                return e.toString();
            }

        } else {   // mac  和  Linux的  执行的 命令

            sb.append(execCMD_Mac(command));

        }

        return sb.toString();
    }




    public static String execCMD(String command) {
        StringBuilder sb =new StringBuilder();

        if(CUR_OS_TYPE == OS_TYPE.Windows){

            try {
                Process process=Runtime.getRuntime().exec("cmd /c start "+command  +" ");

                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while((line=bufferedReader.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
              
            } catch (Exception e) {
                return e.toString();
            }


        } else {   // mac  和  Linux的  执行的 命令

            sb.append(execCMD_Mac(command));

        }
        System.out.println("result: "+ sb.toString() +" command="+command);

        return sb.toString();
    }


    static  Rule getRuleByIdentify(String identify){
        for (int i = 0; i <CUR_RULE_LIST.size() ; i++) {
            if(CUR_RULE_LIST.get(i).identify.equals(identify)){
                return CUR_RULE_LIST.get(i);
            }
        }
        return null;
    }

    public  static String getFileNameNoPoint(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(0,fileName.lastIndexOf(".") ).trim().toLowerCase();
        }else{
            name = new String(fileName);
        }
        return name.toLowerCase().trim();
    }

    public  static String getFileNameNoPoint_NoCase(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(0,fileName.lastIndexOf(".") ).trim();
        }else{
            name = new String(fileName);
        }
        return name.trim();
    }


    public  static String getFileTypeWithPoint(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(fileName.lastIndexOf(".") ).trim().toLowerCase();
        }else{
            name = "";
        }
        return name.toLowerCase().trim();
    }

    public static String getMD5Three(String path) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }


}