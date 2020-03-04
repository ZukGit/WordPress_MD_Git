import java.io.*;

        import java.nio.file.*;
        import java.nio.file.attribute.BasicFileAttributes;
        import java.text.SimpleDateFormat;
        import java.util.*;

        import java.util.regex.Matcher;
        import java.util.regex.Pattern;
        import javax.crypto.Cipher;
        import java.security.Key;
        import java.security.Security;


// 对于  文件类型_操作Index  执行对应的操作逻辑
public class G2_ApplyRuleFor_TypeFile {



    //  类型_索引  ，对当前类型的文件执行索引执行的操作     html1---对html类型的子文件执行 索引为1 的逻辑操作  String apply(String)
    static ArrayList<String> Rule_Identify_TypeIndexList = new ArrayList<String>();


    static String G2_Bat_Name = "zrule_apply_G2";
    static String Cur_Bat_Name = "zrule_apply_G2";
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";


    static File G2_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "G2.properties");
    static InputStream G2_Properties_InputStream;
    static OutputStream G2_Properties_OutputStream;
    static Properties G2_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();


    static int BYTE_CONTENT_LENGTH_Rule7= 1024 * 10 * 10;   // 读取文件Head字节数常数
    static String strDefaultKey_Rule7 = "********"; //  8-length
    public static byte[] TEMP_Rule7 = new byte[BYTE_CONTENT_LENGTH_Rule7];


    static {
        try {
            if (!G2_Properties_File.exists()) {
                G2_Properties_File.createNewFile();
            }
            G2_Properties_InputStream = new BufferedInputStream(new FileInputStream(G2_Properties_File.getAbsolutePath()));
            G2_Properties.load(G2_Properties_InputStream);
            Iterator<String> it = G2_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + G2_Properties.getProperty(key));
                propKey2ValueList.put(key, G2_Properties.getProperty(key));
            }
            G2_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            G2_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(G2_Properties_File.getAbsolutePath()));
            G2_Properties.store(G2_Properties_OutputStream, "");
            G2_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;
    static ArrayList<String> mKeyWordName = new ArrayList<>();

    // 当前Shell目录下的 文件类型列表  抽取出来  通用
    static  HashMap<String, ArrayList<File>> CurDirFileTypeMap = new  HashMap<String, ArrayList<File>>(); ;

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            curOS_TYPE = OS_TYPE.Windows;
            Cur_Bat_Name = Cur_Bat_Name+".bat";
        } else if (osName.contains("linux")) {
            curOS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name+".sh";
        } else if (osName.contains("mac")) {
            curOS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name+".sh";
        }
    }


    static String curDirPath = "";   // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径
    static File curDirFile ;



    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }



    void InitRule(){

        realTypeRuleList.add( new HTML_Rule_1());
        realTypeRuleList.add( new File_Name_Rule_2());
        realTypeRuleList.add( new Image2Jpeg_Rule_3());
        realTypeRuleList.add( new Image2Png_Rule_4());
        realTypeRuleList.add( new AVI_Rule_5());
        realTypeRuleList.add( new SubDirRename_Rule_6());
        realTypeRuleList.add( new Encropty_Rule_7());
        realTypeRuleList.add( new ClearChineseType_8());
    }



    // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
    //     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作



    // 把文件后缀中的中文给去除掉  不包含文件夹   不包含孙文件
    class ClearChineseType_8 extends Basic_Rule{


        ClearChineseType_8() {
            super("#", 8, 4);
        }

        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

            System.out.println("Rule8_ClearChineseType_8   搜索到的实体文件个数:" + curRealFileList.size());

            for (int i = 0; i < curRealFileList.size() ; i++) {
                File curFile = curRealFileList.get(i);
                String currentFileName =  curFile.getName();
                if(currentFileName.contains(".")){
                    String typeStr = currentFileName.substring(currentFileName.lastIndexOf("."));
                    if(isContainChinese(typeStr)){
                     //        //清除中文  清除 空格
                        String newType =  clearChinese(typeStr).replace(" ","");
                        String newName = currentFileName.replace(typeStr,newType);  // 新名称
                        System.out.println("newType = "+newType + "    newName="+newName);
                        tryReName(curFile,newName);
                    }
                }


            }


            return curRealFileList;
        }

        @Override
        String simpleDesc() {
        return "把当前命令的文件包含.的文件的 后缀名称中的中文清除掉  例如 1.7啊z -> 1.7z   2.你zip -> 2.zip \n"+
            Cur_Bat_Name + " 8     [索引8]   // 把当前目录下文件 后缀中文去除  \n";
        }
    }

    // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
    //     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作


    // 对当前目录的文件进行加密 解密
    class Encropty_Rule_7 extends Basic_Rule{
        boolean mEncroptyDirect = true;  //  true---加密      false--解密
        Encropty_Rule_7() {
            super("#", 7, 4);

        }

        @Override
        void initParams4InputParam(String inputParam) {
            if(inputParam.contains("good")){
                mEncroptyDirect = false;
            }else {
                    mEncroptyDirect = true;
            }
            super.initParams4InputParam(inputParam);
        }

        @Override
        String simpleDesc() {
            return "   默认bad(加密) 把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 加密bad/解密good\n" +
                    Cur_Bat_Name+ " #_7_bad   (默认--加密文件)  把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 加密bad\n" +
                    Cur_Bat_Name+ " #_7_good   (加密文件) 把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 解密good\n" +
                    Cur_Bat_Name + " jpg_7_bad  [索引7]   // 把当前目录下的 jpg文件 加密 \n" +
                    Cur_Bat_Name + " jpg_7_good  [索引7]   // 把当前目录下的 jpg文件 解密 " ;
        }

//                    return "把 当前目录下所有的 jpg  mp4 gif  都转为 i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif 的文件格式\n" +
//        Cur_Bat_Name + "  jgm_5_recovery  [索引5]   // 在当前 Z_VI 根目录 计算 当前的 JPG GIF MP4的起始值 \n" +
//        Cur_Bat_Name + "  jgm_5_nextstep  [索引5]   //  JPG="+jpgBeginIndex+ " GIF="+gifBeginIndex+" MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4+" ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ";


        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

            boolean containUserType = curFilterFileTypeList.contains("#");  // 是否包含用户选中的了文件类型  没有包含 那么就把所有实体realty 加密

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            String curNewDirName = date;
            if(mEncroptyDirect){
                curNewDirName += "_bad";
            }else{
                curNewDirName += "_good";
            }

            if(!containUserType){
                curNewDirName += "_"+curFilterFileTypeList.get(0);  //  1.如果所有文件都加密  那么没有后缀 如果某一个文件类型解密 那么添加后缀
            }

            File tempDirFile = new File(curDirFile.getAbsolutePath()+File.separator+curNewDirName);
            tempDirFile.mkdirs();  // 创建文件夹
            String tempDirPath = tempDirFile.getAbsolutePath();
            System.out.println("Rule7 搜索到的实体文件个数:" + curRealFileList.size());

            for (int i = 0; i < curRealFileList.size() ; i++) {
                File curFile = curRealFileList.get(i);
                String currentFileName = File.separator+curFile.getName();


//                System.out.println("currentFileName = "+ currentFileName);
                if(mEncroptyDirect){   // 加密时  如果是 以   i_temp 开头   并且 以 .jpg 为结尾时   加密的类型去掉
                    if(currentFileName.contains(".jpg") && currentFileName.contains("i_temp")  ){
                        currentFileName = currentFileName.replace(".jpg","");
                    }else  if(currentFileName.contains(".mp4") && currentFileName.contains("v_temp")){
                        currentFileName = currentFileName.replace(".mp4","");
                    }else  if(currentFileName.contains(".gif") && currentFileName.contains("g_temp")){
                        currentFileName = currentFileName.replace(".gif","");
                    }

                    File badFile = new File(tempDirPath+File.separator+currentFileName);
                    createEncryFile(curFile,badFile);
                }else{  // 解密    如果当前文件 不包含 .
                    if(!currentFileName.contains(".") && currentFileName.contains("i_temp")){
                        currentFileName = currentFileName + ".jpg";
                    }else  if(!currentFileName.contains(".") && currentFileName.contains("v_temp")){
                        currentFileName = currentFileName + ".mp4";
                    }else  if(!currentFileName.contains(".") && currentFileName.contains("g_temp")){
                        currentFileName = currentFileName + ".gif";
                    }
                    File goodFile = new File(tempDirPath+File.separator+currentFileName);
                    createDecryFile(curFile,goodFile);
                }
            }

            return null;
        }
    }
    class SubDirRename_Rule_6 extends Basic_Rule{

        SubDirRename_Rule_6() {
            super("#", 6, 4);
        }

        @Override
        String simpleDesc() {
            return "#_6    // 修改当前的一级子目录下的文件夹 以及文件  按顺序命令  (不操作 孙文件 孙文件夹 )";
        }

        @SuppressWarnings("unchecked")
        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

            boolean executeFlag = false;
            boolean isFixedAllSubFlag = curFilterFileTypeList.contains("#");
            if(isFixedAllSubFlag){  //  只有包含  #_6 才对 文件夹进行操作    png_6  那么就只对 当前文件夹下的 png文件进行操作
                for (int i = 0; i < curDirList.size(); i++) {
                    File dir = curDirList.get(i);
                    String dirName = dir.getName();
                    String new_dirName = i+"_"+dirName;
                    tryReName(dir,new_dirName);
                }
            }

            Map.Entry<String, ArrayList<File>> entry;
            // 不同的类型文件怎么处理?

            if (subFileTypeMap != null) {
                Iterator iterator = subFileTypeMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    String typeStr = entry.getKey();  //Map的Value
                    String typeWithOutPot = typeStr.replace(".","");

                    if(!isFixedAllSubFlag &&  !curFilterFileTypeList.contains(typeWithOutPot)){
                        //   如果   当前操作不是操作所有文件    并且这个类型不在匹配列表中   那么 不执行  返回next
                        //   如果是 全 操作    那么 往下执行
                        // 如果不是全操作     当前类型包含   那么往下执行
                        continue;
                    }

                    ArrayList<File> fileArr = entry.getValue();  //Map的Value

                    // 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;
                    for (int i = 0; i < fileArr.size(); i++) {
                        File curFile = fileArr.get(i);
                        //String curFileName = curFile.getName();
                        String newName = i+"_"+curFile.getName();
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
                        if(tryReName(curFile,newName)){
                            executeFlag = true;
                        }
                    }
                }
            }
            return executeFlag?curRealFileList:super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
        }
    }

    // 把 当前目录下所有的 jpg  mp4 gif  都转为 i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif 的文件格式
    class AVI_Rule_5 extends Basic_Rule{
        String tempTag = "temp";
        boolean isTemp;  // 是否是零时起的编号
        int mTempBeginIndex = 0; //  零时编号的默认起始地址

        boolean isRecovrty = false; //  当前是否是  读取当前目录  计算 ProPerities的值的操作
        boolean isEnable = true;  // 当存在增量的时候  不起作用    不执行 记录的操作
        boolean isExistAddPart = false;  // 是否存在增量
        boolean executeNextStep = false;  // 当用户输入的 输入参数 包含 nextstep 时 执行 增量的 重置0操作 添加到index的操作


        int jpgBeginIndex = 0;
        int fixed_jpg_BeginIndex = 0;
        String jpgtag = "i";
        int jpgDirTempIndex = 0;
        int jpgEndIndex = 1;
        int nextStepCountJPG = 0 ; //  当前 JPG的 增量


        int gifBeginIndex = 0;
        String giftag = "g";
        int gifDirTempIndex = 0;
        int fixed_gif_BeginIndex = 0;
        int gifEndIndex = 1;
        int nextStepCountGIF = 0 ; //  当前 GIF的 增量

        int mp4BeginIndex = 0;   // 从 Propertities 中读取到的值
        String mp4tag = "v";    // mp4的前缀
        int mp4DirTempIndex = 0;   //  依据 mp4BeginIndex 计算出的 temp1 temp2 .... temp100
        int fixed_mp4_BeginIndex = 0;   // 在当前 tempx 中的索引   大小为 mp4BeginIndex%1000
        int mp4EndIndex = 1;   // 最后保存到 Propertities 中的 值
        int nextStepCountMP4 = 0 ; //  当前 MP4 的 增量


        AVI_Rule_5() {
            super("jgm", 5, 3);
            curFilterFileTypeList.add("jpg");
            curFilterFileTypeList.add("gif");
            curFilterFileTypeList.add("mp4");
            // 从 Proprietary 拿到当前的总的索引  值
            //  jpgBeginIndex =
            // gifBeginIndex =
            // mp4BeginIndex =
            String strJPGBegin = G2_Properties.getProperty("jpgBeginIndex");
            if(strJPGBegin == null){
                strJPGBegin = "0";
                G2_Properties.put("jpgBeginIndex","0");
            }
            jpgBeginIndex = Integer.parseInt(strJPGBegin);

            String strGIFBegin = G2_Properties.getProperty("gifBeginIndex");
            if(strGIFBegin == null){
                strGIFBegin = "0";
                G2_Properties.put("gifBeginIndex","0");
            }
            gifBeginIndex = Integer.parseInt(strGIFBegin);


            String strMP4Begin = G2_Properties.getProperty("mp4BeginIndex");
            if(strMP4Begin == null){
                strMP4Begin = "0";
                G2_Properties.put("mp4BeginIndex","0");
            }
            mp4BeginIndex = Integer.parseInt(strMP4Begin);


            String strNextStepJPG = G2_Properties.getProperty("nextStepCountJPG");
            if(strNextStepJPG == null){
                strNextStepJPG = "0";
                G2_Properties.put("nextStepCountJPG","0");
            }
            nextStepCountJPG = Integer.parseInt(strNextStepJPG);


            String strNextStepGIF = G2_Properties.getProperty("nextStepCountGIF");
            if(strNextStepGIF == null){
                strNextStepGIF = "0";
                G2_Properties.put("nextStepCountGIF","0");
            }
            nextStepCountGIF = Integer.parseInt(strNextStepGIF);


            String strNextStepMP4 = G2_Properties.getProperty("nextStepCountMP4");
            if(strNextStepMP4 == null){
                strNextStepMP4 = "0";
                G2_Properties.put("nextStepCountMP4","0");
            }
            nextStepCountMP4 = Integer.parseInt(strNextStepMP4);

            if( nextStepCountMP4 != 0 || nextStepCountGIF != 0 || nextStepCountJPG != 0 ){
                isExistAddPart = true;
            }

            jpgDirTempIndex = jpgBeginIndex/1000 ;
            fixed_jpg_BeginIndex = jpgBeginIndex%1000;

            gifDirTempIndex = gifBeginIndex/1000 ;
            fixed_gif_BeginIndex = gifBeginIndex%1000;

            mp4DirTempIndex = mp4BeginIndex/1000 ;
            fixed_mp4_BeginIndex = mp4BeginIndex%1000;

        }

        @Override
        String simpleDesc() {
            return "把 当前目录下所有的 jpg  mp4 gif  都转为 i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif 的文件格式\n" +
                    Cur_Bat_Name + "  jgm_5_temp0      [索引5]   // 零时把当前gif jpg mp4 类型 起始位置设置为0   \n" +
                    Cur_Bat_Name + "  jgm_5_temp99      [索引5]   // 零时把当前gif jpg mp4 类型 起始位置设置为99   \n" +
                    Cur_Bat_Name + "  jgm_5_reco very  [索引5]   // 在当前 Z_VI 根目录 计算 当前的 JPG GIF MP4的起始值 \n" +
                    Cur_Bat_Name + "  jgm_5_nextstep  [索引5]   //  JPG="+jpgBeginIndex+ " GIF="+gifBeginIndex+" MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4+" ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ";
        }


        @Override
        void initParams4InputParam(String inputParam) {

            if(inputParam.contains("temp")){
                int index = inputParam.indexOf("temp")+"temp".length() ;
                String tempIndexStr = inputParam.substring(index);
                if(isNumeric(tempIndexStr)){
                    mTempBeginIndex = Integer.parseInt(tempIndexStr);
                }else{
                    if(tempIndexStr.contains("_")){
                        String blankIndex = tempIndexStr.substring(0,tempIndexStr.indexOf("_"));
                        if(isNumeric(blankIndex)){
                            mTempBeginIndex = Integer.parseInt(blankIndex);
                        }
                    }else{
                        mTempBeginIndex = 0 ;  //  默认为0
                    }
                }

                isTemp = true;
            }
            if(inputParam.contains("nextstep")){
                executeNextStep = true;
            }

            if(inputParam.contains("_recovery")){
                isRecovrty = true;
                isEnable = false;
                curFilterFileTypeList.add("*");  // 把当前所有文件都加入到列表中
            }
            System.out.println("OLD记录的Properties信息:(OLD)   "+" JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4 );

            if( executeNextStep){  // 如果存在增量 当前不执行   并且用户是输入的 nextstep的时候  执行 step的更新
                jpgBeginIndex = jpgBeginIndex + nextStepCountJPG;
                gifBeginIndex = gifBeginIndex + nextStepCountGIF;
                mp4BeginIndex = mp4BeginIndex + nextStepCountMP4;
                G2_Properties.setProperty("jpgBeginIndex",""+jpgBeginIndex);
                G2_Properties.setProperty("gifBeginIndex",""+gifBeginIndex);
                G2_Properties.setProperty("mp4BeginIndex",""+mp4BeginIndex);
                G2_Properties.setProperty("nextStepCountJPG",""+0);
                G2_Properties.setProperty("nextStepCountGIF",""+0);
                G2_Properties.setProperty("nextStepCountMP4",""+0);
                isEnable = false;
            }

            super.initParams4InputParam(inputParam);
        }

        void  tryDynamicCalCulateBeginIndex(ArrayList<File> subFileList ){

            String jpg_pre = "i_temp";
            ArrayList<File> jpgTempList = new ArrayList<File>();

            String gif_pre = "g_temp";
            ArrayList<File> gifTempList = new ArrayList<File>();

            String mp4_pre = "v_temp";
            ArrayList<File> mp4TempList = new ArrayList<File>();



            for (int i = 0; i < subFileList.size(); i++) {
                File curFile = subFileList.get(i);
                if(curFile.getAbsolutePath().contains("Z_VI")){
                    if(curFile.getName().startsWith(jpg_pre)){
                        jpgTempList.add(curFile);
                    }else if(curFile.getName().startsWith(gif_pre)){
                        gifTempList.add(curFile);
                    }else if(curFile.getName().startsWith(mp4_pre)){
                        mp4TempList.add(curFile);
                    }
                }

            }

            if(jpgTempList.size() == 0 && gifTempList.size() == 0 && mp4TempList.size() == 0  ){
                System.out.println("当前执行目录不在 Z_VI的根目录 Git_Dir , 请重新执行 "+Cur_Bat_Name);
                return;
            }

            //  通过 搜索 计算得到的 type 文件的 长度  Count
            //  通过 计算 文件最后的名字得到的  index = Count - 1
            int jpgDynimicCount = jpgTempList.size() ;
            int gifDynimicCount = gifTempList.size();
            int mp4DynimicCount = mp4TempList.size();

            jpgTempList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {

                    int o1Index =   calculIndexFromName(o1.getName());

                    int o2Index =       calculIndexFromName(o2.getName());
                    if(o1Index < o2Index ){
                        return -1;
                    }
                    if(o1Index == o2Index){
                        return 0;
                    }
                    return 1;
                }
            });

            gifTempList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {

                    int o1Index =   calculIndexFromName(o1.getName());

                    int o2Index =       calculIndexFromName(o2.getName());
                    if(o1Index < o2Index ){
                        return -1;
                    }
                    if(o1Index == o2Index){
                        return 0;
                    }
                    return 1;
                }
            });

            //          Comparable VICompare = new Comparable()
            mp4TempList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {

                    int o1Index =   calculIndexFromName(o1.getName());

                    int o2Index =       calculIndexFromName(o2.getName());
                    if(o1Index < o2Index ){
                        return -1;
                    }
                    if(o1Index == o2Index){
                        return 0;
                    }
                    return 1;
                }
            });
            File lastJPGFile = null;
            File lastGIFFile = null;
            File lastMP4File = null;

            if(jpgTempList.size() > 0){
                lastJPGFile = jpgTempList.get(jpgTempList.size()-1);
            }

            if(gifTempList.size() > 0){
                lastGIFFile = gifTempList.get(gifTempList.size()-1);
            }

            if(mp4TempList.size() > 0){
                lastMP4File = mp4TempList.get(mp4TempList.size()-1);
            }

            int jpgLastIndex = 0;
            int gifLastIndex = 0;
            int mp4LastIndex = 0;
            if(lastJPGFile != null){
                jpgLastIndex =  calculIndexFromName(lastJPGFile.getName());
            }
            if(lastGIFFile != null){
                gifLastIndex =  calculIndexFromName(lastGIFFile.getName());

            }
            if(lastMP4File != null){
                mp4LastIndex =  calculIndexFromName(lastMP4File.getName());
            }



            if(jpgDynimicCount != jpgBeginIndex || (jpgLastIndex+1) != jpgDynimicCount){  // 大小 和 记录的起始点 不一致 那么需要 重新该名称
                for (int i = 0; i < jpgTempList.size() ; i++) {
                    File jpgFile = jpgTempList.get(i);
                    String jpgFileName = "i"+"_"+getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,gifDirTempIndex,0,i,3,"0",true);
                    tryReName(jpgFile,jpgFileName);
                }
            }

            if(gifDynimicCount != gifBeginIndex || (gifLastIndex+1) != gifDynimicCount){  // 大小 和 记录的起始点 不一致 那么需要 重新该名称
                for (int i = 0; i < gifTempList.size() ; i++) {
                    File gifFile = gifTempList.get(i);
                    String gifFileName = "g"+"_"+getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,gifDirTempIndex,0,i,3,"0",true);
                    tryReName(gifFile,gifFileName);
                }
            }


            if(mp4DynimicCount != mp4BeginIndex || (mp4LastIndex+1) != mp4DynimicCount ){  // 大小 和 记录的起始点 不一致 那么需要 重新该名称
                for (int i = 0; i < mp4TempList.size() ; i++) {
                    File mp4File = mp4TempList.get(i);
                    String mp4FileName = "v"+"_"+getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,gifDirTempIndex,0,i,3,"0",true);
                    tryReName(mp4File,mp4FileName);
                }
            }



            System.out.println("recovery 搜索到的文件 数量:" + subFileList.size()  );
            if(lastJPGFile != null){
                jpgLastIndex =  calculIndexFromName(lastJPGFile.getName());
                System.out.println("最后一个 JPG 文件的名称为:"+ lastJPGFile.getName() + "  索引:"+jpgLastIndex + (jpgLastIndex !=(jpgBeginIndex -1) ? " 匹配不成功(改名操作)":"匹配成功"));

            }
            if(lastGIFFile != null){
                gifLastIndex =  calculIndexFromName(lastGIFFile.getName());
                System.out.println("最后一个 GIF 文件的名称为:"+ lastGIFFile.getName()+ "  索引:"+ gifLastIndex+ (gifLastIndex !=(gifBeginIndex-1)  ? " 匹配不成功(改名操作)":"匹配成功"));

            }
            if(lastMP4File != null){
                mp4LastIndex =  calculIndexFromName(lastMP4File.getName());
                System.out.println("最后一个 MP4 文件的名称为:"+ lastMP4File.getName() + "  索引:"+ mp4LastIndex+ (mp4LastIndex !=(mp4BeginIndex-1) ? " 匹配不成功(改名操作)":"匹配成功"));
            }
            System.out.println("jpgDynimicIndex(JPG动态计算文件数量)=" + getXsizeString(jpgDynimicCount,7) + "   (最后一个JPG文件名称索引+1)"+getXsizeString(jpgLastIndex+1,7)+" ||    Pro记录 jpgBeginIndex 为:"+ jpgBeginIndex );
            System.out.println("gifDynimicIndex(GIF动态计算文件数量)=" + getXsizeString(gifDynimicCount,7) + "   (最后一个GIF文件名称索引+1)"+getXsizeString(gifLastIndex+1,7)+" ||    Pro记录 gifBeginIndex 为:"+ gifBeginIndex );
            System.out.println("mp4DynimicIndex(MP4动态计算文件数量)=" + getXsizeString(mp4DynimicCount,7) + "   (最后一个MP4文件名称索引+1)"+getXsizeString(mp4LastIndex+1,7)+" ||    Pro记录 mp4BeginIndex 为:"+ mp4BeginIndex );

            recoveryProperities(jpgDynimicCount,gifDynimicCount,mp4DynimicCount);
            System.out.println();
        }

        int calculIndexFromName(String viName){

            String valueA =   viName.replace("_","");
            valueA =  valueA.replace("gif","");
            valueA =  valueA.replace("jpg","");
            valueA =  valueA.replace("mp4","");
            valueA =  valueA.replace("mp3","");
            valueA =  valueA.replace("png","");
            valueA =  valueA.replace("temp","");
            valueA =  valueA.replace("\"","");
            valueA =  valueA.replace(".","");
            valueA =  valueA.replace("(","");
            valueA =  valueA.replace(")","");
            valueA =  valueA.replace("）","");
            valueA =  valueA.replace("（","");

            valueA =  valueA.replace("a","");
            valueA =  valueA.replace("b","");
            valueA =  valueA.replace("c","");
            valueA =  valueA.replace("d","");
            valueA =  valueA.replace("e","");
            valueA =  valueA.replace("f","");
            valueA =  valueA.replace("g","");
            valueA =  valueA.replace("h","");
            valueA =  valueA.replace("i","");
            valueA =  valueA.replace("j","");
            valueA =  valueA.replace("k","");
            valueA =  valueA.replace("l","");
            valueA =  valueA.replace("m","");
            valueA =  valueA.replace("n","");
            valueA =  valueA.replace("o","");
            valueA =  valueA.replace("p","");
            valueA =  valueA.replace("q","");
            valueA =  valueA.replace("r","");
            valueA =  valueA.replace("s","");
            valueA =  valueA.replace("t","");
            valueA =  valueA.replace("u","");
            valueA =  valueA.replace("v","");
            valueA =  valueA.replace("w","");
            valueA =  valueA.replace("x","");
            valueA =  valueA.replace("y","");
            valueA =  valueA.replace("z","");
            valueA =  valueA.replace(" ","").trim();
            int resultIndex = 0;
            try{
                resultIndex = Integer.parseInt(valueA) ;

            }   catch(Exception e){
                resultIndex = 0;
            }

            return resultIndex;
        }
        void  recoveryProperities(int jpg , int gif , int mp4){
            jpgBeginIndex = jpg;
            gifBeginIndex = gif;
            mp4BeginIndex = mp4;
            G2_Properties.setProperty("jpgBeginIndex",""+jpg);
            G2_Properties.setProperty("gifBeginIndex",""+gif);
            G2_Properties.setProperty("mp4BeginIndex",""+mp4);
            G2_Properties.setProperty("nextStepCountJPG",""+0);
            G2_Properties.setProperty("nextStepCountGIF",""+0);
            G2_Properties.setProperty("nextStepCountMP4",""+0);


            System.out.println(" Z_VI(Git_Dir)恢复Pro数:(New)    JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量=0    GIF增量=0   MP4增量=0");
        }

        @SuppressWarnings("unchecked")
        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
            boolean executeFlag = false;

            if(isRecovrty){  //  如果是要恢复的的话
                tryDynamicCalCulateBeginIndex(subFileList);
                return null;
            }

            String oldAddPart = "OLD 记录的Properties增量:(OLD)   "+" JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4;
            if(!isEnable){
                System.out.println("当前 Rule5 规则上的增量已经置0  增量已得到确认  请开始累计新的资源! " );
                System.out.println("当前记录到Prop的增量信息:(New)  "+" JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量="+0 +"    GIF增量="+0 + "   MP4增量="+0 );

                return null;
            }
            if(isExistAddPart){
                System.out.println("当前 Rule5 规则存在上次还未确认的增量 请执行如下命令来确认增量 使得NextStep完成\n" +
                        Cur_Bat_Name + " jgm_5_nextstep      // ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ");
            }
            Map.Entry<String, ArrayList<File>> entry;
            int nextStepCountJPG_new = 0 ;
            int nextStepCountGIF_new = 0 ;
            int nextStepCountMP4_new = 0 ;
            if (fileTypeMap != null) {
                Iterator iterator = fileTypeMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    String typeStr = entry.getKey();  //Map的Value
                    ArrayList<File> fileArr = entry.getValue();  //Map的Value
                    String typeTag = jpgtag;
                    String dirTempIndex = tempTag+jpgDirTempIndex;
                    int tempIndex = 1;
                    int fixedFileIndex = 0 ;
                    if(".jpg".equals(typeStr)){
                        typeTag = jpgtag;
                        dirTempIndex = tempTag+jpgDirTempIndex;
                        fixedFileIndex =  jpgBeginIndex;
                        tempIndex = jpgDirTempIndex;
                        nextStepCountJPG =  fileArr.size();
                        nextStepCountJPG_new =  fileArr.size();
                        if(!isTemp)   G2_Properties.setProperty("nextStepCountJPG",""+nextStepCountJPG);
                        jpgEndIndex = jpgBeginIndex + fileArr.size();
                        System.out.println("当前JPG起始值:"+fixedFileIndex+"    当前GIF的文件长度:"+ fileArr.size() );
                    } else if (".mp4".equals(typeStr)){
                        typeTag = mp4tag;
                        dirTempIndex = tempTag+mp4DirTempIndex;
                        fixedFileIndex =  mp4BeginIndex;
                        tempIndex = mp4DirTempIndex;
                        nextStepCountMP4 =  fileArr.size();
                        nextStepCountMP4_new =  fileArr.size();
                        if(!isTemp)  G2_Properties.setProperty("nextStepCountMP4",""+nextStepCountMP4);

                        mp4EndIndex = mp4BeginIndex + fileArr.size();
                        System.out.println("当前MP4起始值:"+fixedFileIndex+"    当前GIF的文件长度:"+ fileArr.size() );
                    } else if(".gif".equals(typeStr)) {
                        typeTag = giftag;
                        dirTempIndex = tempTag+gifDirTempIndex;
                        fixedFileIndex =  gifBeginIndex;
                        tempIndex = gifDirTempIndex;
                        nextStepCountGIF =  fileArr.size();
                        System.out.println("当前GIF起始值:"+fixedFileIndex+"    当前GIF的文件长度:"+ fileArr.size() );
                        nextStepCountGIF_new =  fileArr.size();
                        if(!isTemp)  G2_Properties.setProperty("nextStepCountGIF",""+nextStepCountGIF);
                        gifEndIndex = gifBeginIndex + fileArr.size();

                    }else{
                        continue;
                    }

                    if(isTemp){
                        fixedFileIndex = mTempBeginIndex ;  // 如果是 temp 那么 默认 就把  temp转为 index
                       nextStepCountJPG_new = 0 ;
                       nextStepCountGIF_new = 0 ;
                       nextStepCountMP4_new = 0 ;
                    }
                    // 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;


                    for (int i = 0; i < fileArr.size(); i++) {

                        File curFile = fileArr.get(i);
                        //String curFileName = curFile.getName();
                        String newName = typeTag+"_"+getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,gifDirTempIndex,fixedFileIndex,i,3,"0",true)+typeStr;

//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;

                        if(tryReName(curFile,newName)){
                            executeFlag = true;
                        }
//                        fixedFileIndex++;
                    }
                }
            }

            String NewAddPart = "New 记录的Properties增量:(New)   "+ " JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG_new +"    GIF增量="+nextStepCountGIF_new + "   MP4增量="+nextStepCountMP4_new;

            System.out.println("══════════确认增量信息 Begin══════════");
            if(isExistAddPart){
                //     System.out.println("Rule5 上次的增量情况:");
                System.out.println(oldAddPart);
            }else{
                System.out.println("OLD     上次的不存在增量:(OLD)    JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+" JPG增量=0     GIF增量=0     MP4增量=0");
            }
            //    System.out.println("\nRule5 现在的增量情况: ");
            System.out.println(NewAddPart);

            System.out.println("New 现在使用如下命令把 New 当前的增量进行确认! \n" +
                    Cur_Bat_Name + " jgm_5_nextstep      // ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ");
            System.out.println("══════════确认增量信息 End══════════");
            if(executeFlag){
                return curFixedFileList;
            }
            return super.applyFileListRule3(subFileList, fileTypeMap);
        }


        // 从 起始的地址  beginIndex 开始计算
        String getPaddingIntStringWithDirIndexFileNameWithIndex(String cTempTag , int CurrentTempIndex,int beginIndex , int index , int padinglength , String oneStr , boolean dirPre){

            int indexIdentify = beginIndex + index ;
            int tempIndexResult =  (indexIdentify/1000) ;
            String result = ""+getXsizeString((indexIdentify%1000),oneStr,padinglength,dirPre);
            return cTempTag + tempIndexResult+"_"+ result;

        }


        //  不从起始的地址 计算    从0，1,2,3.... 开始计算
        String  getPaddingIntStringWithDirIndexFileName(String cTempTag , int CurrentTempIndex,int index , int padinglength , String oneStr , boolean dirPre){

            int tempIndexA =  (index/1000);
            int tempIndexResult = CurrentTempIndex + tempIndexA;

            String result = ""+getXsizeString((index%1000),oneStr,padinglength,dirPre);

 /*           int length = (""+index).length();

            if(length < padinglength){
                int distance = padinglength  - length;
                for (int i = 0; i < distance; i++) {
                    if(dirPre){
                        result = oneStr+result;
                    }else{
                        result = result + oneStr;
                    }

                }

            }*/

            return cTempTag + tempIndexResult+"_"+ result;

        }


        String  getXsizeString(int index , int paddingSize ){

            return   getXsizeString(index," ",paddingSize,true);

        }

        String  getXsizeString(int index , String charOne , int paddingSize ,boolean directPre ){
            String result = (""+index);
            int length = (""+index).length();
            if(length < paddingSize){
                int distance = paddingSize  - length;
                for (int i = 0; i < distance; i++) {
                    if(directPre){
                        result = charOne+result;
                    }else{
                        result = result+charOne;
                    }

                }
            }
            return result;
        }

    }

    // 把 当前目录下所有的 png jpeg 都转为 jpg的格式
    class Image2Png_Rule_4 extends Basic_Rule{
        String targetFileType = ".png";
        Image2Png_Rule_4() {
            super("png", 4, 3);
            curFilterFileTypeList.add(".jpeg");
            curFilterFileTypeList.add(".JPEG");
            curFilterFileTypeList.add(".jpg");
            curFilterFileTypeList.add(".JPG");
            curFilterFileTypeList.add(".PNG");
            targetFileType = ".png";
        }

        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
            boolean falg = false;
            for (int i = 0; i < subFileList.size(); i++) {
                File imageFile = subFileList.get(i);
                String fileName = imageFile.getName();
                String newName =  fileName.replace(".jpeg",targetFileType);
                newName =  newName.replace(".jpg",targetFileType);
                newName =  newName.replace(".JPEG",targetFileType);
                newName =  newName.replace(".JPG",targetFileType);
                newName =  newName.replace(".PNG",targetFileType);
                if(tryReName(imageFile,newName)){
                    falg = true;
                }
            }

            if(falg){
                return curFixedFileList;
            }
            return super.applyFileListRule3(subFileList, fileTypeMap);
        }

        @Override
        String simpleDesc() {
            return " 把当前目录(包含子目录)所有的 .jpg .jpeg 的文件后缀改为 .png 的文件后缀";
        }
    }


    // 把 当前目录下所有的 png jpeg 都转为 jpg的格式
    class Image2Jpeg_Rule_3 extends Basic_Rule{
        String targetFileType = ".jpg";
        Image2Jpeg_Rule_3() {
            super("jpg", 3, 3);
            curFilterFileTypeList.add(".jpeg");
            curFilterFileTypeList.add(".JPEG");
            curFilterFileTypeList.add(".JPG");
            curFilterFileTypeList.add(".png");
            curFilterFileTypeList.add(".PNG");
            targetFileType = ".jpg";
        }

        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
            boolean falg = false;
            for (int i = 0; i < subFileList.size(); i++) {
                File imageFile = subFileList.get(i);
                String fileName = imageFile.getName();
                String newName =  fileName.replace(".jpeg",targetFileType);
                newName =  newName.replace(".png",targetFileType);
                newName =  newName.replace(".JPEG",targetFileType);
                newName =  newName.replace(".PNG",targetFileType);
                newName =  newName.replace(".JPG",targetFileType);
                if(tryReName(imageFile,newName)){
                    falg = true;
                }
            }

            if(falg){
                return curFixedFileList;
            }
            return super.applyFileListRule3(subFileList, fileTypeMap);
        }

        @Override
        String simpleDesc() {
            return " 把当前目录(包含子目录)所有的 .png .jpeg 的文件后缀改为 .jpg 的文件后缀";
        }
    }



    // 指定什么类型的文件在当前使用什么样的规则
    // operation_type  操作类型
    // 1--读取文件内容字符串 进行修改        String applyOperationRule(String origin)
    // 2--对单个文件属性进行修改(文件名称)  对文件内容(字节)--进行修改 File applyFileByteOperationRule(File originFile)
    // 3--对集合文件属性进行修改(文件名称)  对所有子文件--进行修改 ArrayList<File> applyFileByteOperationRule(ArrayList<File> subFileList)
    // index  唯一指定的一种 rule规则

    // file_name_2   *_2   对当前目录下的所有文件进行 文件名称的重新命名 命名规则  在头部添加序号

    class File_Name_Rule_2 extends Basic_Rule{

        // key = type       value =  符合过滤文件规则的名称的文件的集合
        //   HashMap<String, ArrayList<File>> arrFileMap;
        boolean keepOriginalName = true;

        File_Name_Rule_2() {
            super("*", 2, 3);  //
        }




        @SuppressWarnings("unchecked")
        boolean   tryReNameOperation( HashMap<String, ArrayList<File>> arrFileMap ){
            boolean executeFlag = false;
            Map.Entry<String, ArrayList<File>> entry;
            if (arrFileMap != null) {
                Iterator iterator = arrFileMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    String typeStr = entry.getKey();  //Map的Value
                    ArrayList<File> fileArr = entry.getValue();  //Map的Value

                    for (int i = 0; i < fileArr.size(); i++) {
                        int index = i + 1;
                        String newNamePre = index+"_";
                        File curFile = fileArr.get(i);
                        String curFileName = curFile.getName();
                        String newName = "";
                        if( keepOriginalName ){
                            newName = newNamePre + curFileName;
                        }else{
                            // 如果不保留名称  那么没有类型的文件 将只有 序号  没有类型
                            if("unknow".equals(typeStr)){
                                newName = index+"";
                            }else{
                                newName = index+typeStr;
                            }

                        }
                        if(tryReName(curFile,newName)){
                            executeFlag = true;
                        }
                    }

                }
            }

            return executeFlag;
        }






        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap ){

            if(tryReNameOperation(fileTypeMap)){
                return curFixedFileList;
            }

            return super.applyFileListRule3(subFileList , fileTypeMap );
        }

        void  initParams4InputParam(String inputParams){
            if(inputParams.contains("_false")){
                keepOriginalName = false;
            }else{
                keepOriginalName = true;
            }

        }

        String ruleTip(String type,int index , String batName,OS_TYPE curType){
            String itemDesc = "";
            String desc_true = " (保留原名称) 把当前的所有子文件(非目录)重命名为 【序号_原始名称.类型】的形式 例如 hello.jpg =》 1_hello.jpg  xx.jpg-》2_xx.jpg ";
            String desc_false = "(清除原名称) 把当前的所有子文件(非目录)重命名为 【序号.类型】的形式 例如 hello.jpg =》 1.jpg  xx.jpg-》2_xx.jpg ";

            if(curType == OS_TYPE.Windows){
                itemDesc = batName.trim()+".bat  "+type+"_"+index+"_true" + "    [索引 "+index+"]  描述: "+ desc_true;
                itemDesc +="\n"+ batName.trim()+".bat  "+type+"_"+index+"_false" + "    [索引 "+index+"]  描述:" + desc_false;
            }else{
                itemDesc = batName.trim()+".sh "+type+"_"+index+"_true" + "    [索引 "+index+"]  描述:"+ desc_true;
                itemDesc +="\n"+ batName.trim()+".sh  "+type+"_"+index+"_false" + "    [索引 "+index+"]  描述:"+ desc_false;

            }

            return itemDesc;
        }




    }

    // html_1
/*    1.读取当前的 html文件  然后把所有的 html文件的 <script> </script>  重新放到 </body> 后面
 <script>
</script>
</body>
*/

    class HTML_Rule_1 extends Basic_Rule{

        HTML_Rule_1(){
            super("html",1,1);
        }
        String applyOperationRule(String origin){
            StringBuilder sb = new StringBuilder();
            if(origin.contains("<script>") &&
                    origin.contains("</script>") &&
                    origin.contains("</body>") &&
                    origin.indexOf("</body>") >  origin.indexOf("<script>") &&   // <script> </body>  // script 索引小于 </body>的索引
                    origin.indexOf("</script>") == origin.lastIndexOf("</script>")){  // 只包含一个  </script>
                int scriptBegin = origin.indexOf("<script>");
                int scriptEnd = origin.indexOf("</script>") + "</script>".length();
                int bodyEnd = origin.indexOf("</body>");

                String script = origin.substring(scriptBegin,scriptEnd);
                String result = origin.replace(script,"");
                result = result.replace("</body>","</body>"+"\n"+script);

                sb.append(result);
            }else{
                sb.append(origin);
            }
            return sb.toString();

        }

        String simpleDesc(){
            return " 读取当前的 html文件  然后把所有的 html文件的 <script> </script>  重新放到 </body> 后面";
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
        // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
        // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件)   // 5. 从shell 中获取到的路径 去对某一个文件进行操作

        int operation_type;
        String file_type;   // * 标识所有的文件类型   以及当前操作类型文件  或者 单独的文件过滤类型
        String identify;
        int rule_index;   //  (type,index)   组成了最基础的唯一键
        ArrayList<String> curFilterFileTypeList;  //  当前的文件过滤类型   多种文件过滤类型  例如把 多种格式 jpeg png 转为 jpg 时 使用到
        ArrayList<File> curFixedFileList;  // 当前修改操作成功的集合
        abstract    String applyStringOperationRule1(String origin);
        abstract    File applyFileByteOperationRule2(File originFile);
        abstract    ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap);
        abstract    ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList , HashMap<String, ArrayList<File>> subFileTypeMap , ArrayList<File> curDirList ,ArrayList<File> curRealFileList);
        abstract    void initParams4InputParam(String inputParam);  // 初始化Rule的参数 依据输入的字符串
        abstract   String ruleTip(String type,int index , String batName,OS_TYPE curType);  // 使用说明列表  如果覆盖 那么就不使用默认的说明 , 默认就一种情况
        abstract   String simpleDesc();  // 使用的简单描述  中文的该 rule的使用情况  默认会在 ruleTip 被调用

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




    public static void createEncryFile(File generalFile , File encryptFile) {

        int general_position = 0;
        int general_offset = 0;
        FileInputStream generalFileInputStream = null;
        BufferedInputStream generalBufferedInputStream = null;


        FileOutputStream encryptileOutputStream = null;
        BufferedOutputStream encryptBufferedOutputStream = null;

        try {
            if(!encryptFile.exists()){
                encryptFile.createNewFile();
            }
            generalFileInputStream = new FileInputStream(generalFile);
            generalBufferedInputStream = new BufferedInputStream(generalFileInputStream);


            encryptileOutputStream = new FileOutputStream(encryptFile);
            encryptBufferedOutputStream = new BufferedOutputStream(encryptileOutputStream);

            if(encryptFile.getAbsolutePath().trim().endsWith("md")){
                while ((general_position = generalBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
                    encryptBufferedOutputStream.write(TEMP_Rule7, 0, general_position);
                    encryptBufferedOutputStream.flush();
                }
                // 关闭流
                generalBufferedInputStream.close();
                encryptBufferedOutputStream.close();
                return;

            }


          //  System.out.println("原始文件字节大小:  " + generalBufferedInputStream.available());
            while (general_offset < BYTE_CONTENT_LENGTH_Rule7) {   // 读取原始文件的头 BYTE_CONTENT_LENGTH 个字节数进行加密
                general_position = generalBufferedInputStream.read(TEMP_Rule7, general_offset, TEMP_Rule7.length - general_offset);
                if (general_position == -1) {
                    break;
                }
                general_offset += general_position;
                // byteTo16(TEMP, general_position);   // 可以查看 指定 前 general_position 个在 TEMP数组中的字节数据 太多 注释掉
            }


            // 对读取到的TEMP字节数组 BYTE_CONTENT_LENGTH 个字节进行 ECB模式加密 明文大小与密文大小一致

            byte[]    encrypt_bytes = encrypt(TEMP_Rule7);

            System.out.println("加密原始文件:"+generalFile.getName()+"  加密前明文大小:" + TEMP_Rule7.length + "   加密后密文大小:" + encrypt_bytes.length);

            //加密后的密文 填充   encryptFile文件的头首部
            encryptBufferedOutputStream.write(encrypt_bytes, 0, encrypt_bytes.length);
            encryptBufferedOutputStream.flush();
            // 从正常的 general文件 读取  BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 加密File(Head已经加密)文件中去
            while ((general_position = generalBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
                encryptBufferedOutputStream.write(TEMP_Rule7, 0, general_position);
                encryptBufferedOutputStream.flush();
            }
            // 关闭流
            generalBufferedInputStream.close();
            encryptBufferedOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }
    }


    private static Cipher encryptCipher = null;
    private static Cipher decryptCipher = null;

    static {
        try {
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strDefaultKey_Rule7.getBytes());
            encryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {

        }

    }

    private static Key getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8]; //认默为0
        for (int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
            arrB[i] = arrBTmp[i];
        }
        //生成密匙
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }


    // 加密字节数组
    public static byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }


    //密解字节数组
    public static byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
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


    // 读取加密文件  对加密部分进行解密 然后生成解密之后的文件 decryptFile
    public static void createDecryFile(File encryptFile, File decryptFile) {


        FileOutputStream decryptileOutputStream = null;
        BufferedOutputStream decryptBufferedOutputStream = null;

        FileInputStream encryptileInputStream = null;
        BufferedInputStream encryptBufferedInputStream = null;


        try {
            if(!decryptFile.exists()){
                decryptFile.createNewFile();
            }
            encryptileInputStream = new FileInputStream(encryptFile);
            encryptBufferedInputStream = new BufferedInputStream(encryptileInputStream);


            decryptileOutputStream = new FileOutputStream(decryptFile);
            decryptBufferedOutputStream = new BufferedOutputStream(decryptileOutputStream);


            int encrypt_offset = 0;
            int encrypt_position = 0;
            while (encrypt_offset < BYTE_CONTENT_LENGTH_Rule7) {    // 读取到加密文件的  加密字节部分 大小为 BYTE_CONTENT_LENGTH
                encrypt_position = encryptBufferedInputStream.read(TEMP_Rule7, encrypt_offset, TEMP_Rule7.length - encrypt_offset);

                if (encrypt_position == -1) {
                    break;
                }
                encrypt_offset += encrypt_position;
                // byteTo16(TEMP, general_position);   // 可以查看 指定 前 general_position 个在 TEMP数组中的字节数据 太多 注释掉
            }

            byte[] decrypt_bytes = decrypt(TEMP_Rule7);  // 对加密文件的加密字节进行解密
            System.out.println("解密文件:"+decryptFile.getName()+"  密文加密字节大小:" + TEMP_Rule7.length + "   解密密文之后的明文大小:" + decrypt_bytes.length);

            decryptBufferedOutputStream.write(decrypt_bytes);
            decryptBufferedOutputStream.flush();


            // 读取 encryptFile加密文件中正常的字节    BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 解密File(Head已经解密)文件中去
            while ((encrypt_offset = encryptBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
                decryptBufferedOutputStream.write(TEMP_Rule7, 0, encrypt_offset);
                decryptBufferedOutputStream.flush();
            }

            encryptBufferedInputStream.close();
            decryptBufferedOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }
    }

    static void showTip() {
        System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
        System.out.println("当前已实现的替换逻辑如下:\n");

        int count = 1;
        System.out.println("═══════════════════"+"使用方法列表 Begin"+"═══════════════════"+"\n");
        for (int i = 0; i < realTypeRuleList.size() ; i++) {
            Rule itemRule = realTypeRuleList.get(i);
            String type =  itemRule.file_type;
            int index =  itemRule.rule_index;
            String desc =  itemRule.ruleTip(type , index ,G2_Bat_Name,curOS_TYPE);

/*
            String itemDesc = "";
           if(curOS_TYPE == OS_TYPE.Windows){
                itemDesc = "zrule_apply_G2.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_G2 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }
           */
            System.out.println(desc+"\n");
            count++;
        }
        System.out.println("═══════════════════"+"使用方法列表 End "+"═══════════════════"+"\n");

    }





    static boolean  checkInputParamsOK(){
        boolean inputOk = true;

        for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) {
            String curInputStr = Rule_Identify_TypeIndexList.get(i);
            if(!curInputStr.contains("_")){
                return false;
            }

            String type =  curInputStr.split("_")[0];
            String index =  curInputStr.split("_")[1];

//          initParams4InputParam
            if(!isNumeric(index)){  //  第二个参数不是 数字 那么 输入格式错误
                return false;
            }
            Rule matchRule = getRuleByIndex(Integer.parseInt(index));
            if(matchRule != null){
                matchRule.initParams4InputParam(curInputStr);
            }

        }

        return inputOk;
    }

    public static void main(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {
                    curDirPath = args[i];
                } else {
                    mKeyWordName.add(args[i]);
                    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        G2_ApplyRuleFor_TypeFile  mG2_Object = new G2_ApplyRuleFor_TypeFile();
        mG2_Object.InitRule();

        if (mKeyWordName.size() == 0) {
            showTip();
            return;
        }

        if(!checkInputParamsOK()){
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }


        File mCurDirFile = new File(curDirPath);
        curDirFile = new  File(curDirPath);
        if (!mCurDirFile.exists() || !mCurDirFile.isDirectory() ) {
            System.out.println("当前执行替换逻辑的文件路径:" + curDirPath+"  不存在! ");
            return;
        }



        //  通过  shell中输入参数来进行操作
        //  Rule_Identify_TypeIndexList.add("html_1");  //  1.添加处理的类型文件  类型_该类型的处理逻辑索引      索引从1开始


        for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) {  //  依据文件类型 去找到文件
            // html_1
            String applyRuleString = Rule_Identify_TypeIndexList.get(i);
            String curType = applyRuleString.split("_")[0];
            String curApplyRule =  applyRuleString.split("_")[1];
            int ruleIndex = Integer.parseInt(curApplyRule);


            Rule curApplayRule = getRuleByIndex(ruleIndex);
            if(curApplayRule == null){
                System.out.println("无法匹配到 对应的 index="+ ruleIndex +"  对应的规则 Rule !   可能需要代码添加。");
                continue;   // 继续下一个循环
            }
            if(curApplayRule.curFilterFileTypeList.size() == 0){
                curApplayRule.curFilterFileTypeList.add(curType);
            }



            ArrayList<File>  typeFileList = new  ArrayList<File>();

            if(curApplayRule.operation_type == 4){  // 对于 类型是 4 的操作  只获取当前 shell 下的文件
                typeFileList.addAll(Arrays.asList(mCurDirFile.listFiles()));
                System.out.println("operation_type == 4 子目录大小: "+typeFileList.size());
            }else{
                typeFileList =  getAllSubFile(mCurDirFile,null,curApplayRule.curFilterFileTypeList);
            }

            if(typeFileList.size() == 0){
                System.out.println("未能搜索到类型列表匹配的文件:  "+ Rule_Identify_TypeIndexList.get(i));
                continue;
            }
            initFileTypeMap(typeFileList);



            if(curApplayRule.operation_type == 4){  // 只对 当前的 子 文件(目录 文件)操作
                //  对当前文件进行整理
                ArrayList<File> subDirList = new  ArrayList<File>();
                ArrayList<File> realFileList = new  ArrayList<File>();

                outCycle: for (int j = 0; j < typeFileList.size(); j++) {
                    File curFile = typeFileList.get(j);
                    if(curFile.isDirectory()){
                        subDirList.add(curFile);
                    }else{

                        if(curApplayRule.curFilterFileTypeList.contains("#")){
                            realFileList.add(curFile);
                        }else{

                            inCycle:  for (int k = 0; k < curApplayRule.curFilterFileTypeList.size(); k++) {
                                String curMatchType =  curApplayRule.curFilterFileTypeList.get(k);
//                                System.out.println("FileName:"+curFile.getName()+"  curMatchType="+curMatchType);
                                if(curFile.getName().endsWith(curMatchType)){
                                    realFileList.add(curFile);
                                    break inCycle;
                                }
                            }

                        }

                    }
                }

                ArrayList<File> resultFileList =   curApplayRule.applySubFileListRule4(typeFileList,CurDirFileTypeMap,subDirList,realFileList);
                if(resultFileList != typeFileList){
                    System.out.println("应用规则:  "+ applyRuleString +" 成功!");
                }else{
                    System.out.println("应用规则:  "+ applyRuleString +" 失败!");
                }

            }
            else if(curApplayRule.operation_type == 3){  // 对所有文件进行的 统一处理的 类型

                ArrayList<File> resultFileList =   curApplayRule.applyFileListRule3(typeFileList,CurDirFileTypeMap);
                if(resultFileList != typeFileList){

                    System.out.println("应用规则:  "+ applyRuleString +" 成功!");
                }else{
                    System.out.println("应用规则:  "+ applyRuleString +" 失败!");
                }


            }else{

                for (int j = 0; j < typeFileList.size(); j++) {
                    File itemFile =  typeFileList.get(j);
                    String fileCOntent =  ReadFileContent(itemFile).trim();
                    // 2.applyOperationRule  添加处理规则


                    String resultStr = OriApplyOperationRule(curType,curApplyRule,fileCOntent).trim();

                    int currentOperationType = 1;  // 默认操作类型是 读取字符串的内容 进行处理

                    String identy = curType.trim()+curApplyRule.trim();
//                Rule applayRule2Identify = getRuleByIdentify(identy);

                    Rule applayRule4Index = getRuleByIndex(ruleIndex);
//                如果对应相同的 index的 Rule *_2    出现了    MP3_2 的情况  那么就需要把当前的 所有的*的文件 过滤为 mp3的文件
//                if("*".equals(applayRule2Identify.file_type) && !curType.equals(applayRule2Identify.file_type)){
//
//                }


                    if(applayRule4Index != null){
                        currentOperationType =  applayRule4Index.operation_type;
                    }else{
                        System.out.println("无法匹配到 对应的 identy="+ identy +"  对应的规则 Rule !   可能需要代码添加。");
                        return;
                    }

                    if(currentOperationType == 1){    // 对字符串进行逻辑处理的类型

                        if(!fileCOntent.equals(resultStr)){
                            writeContentToFile(itemFile,resultStr);
                            System.out.println("itemFile["+j+"] 符合规则(String-Content) 应用Rule成功 " + applyRuleString+ "  = " + itemFile.getAbsolutePath() );
                        }else{
                            System.out.println("itemFile["+j+"] 不符合规则(String-Content) = " + itemFile.getAbsolutePath() );
                        }

                    }else if(currentOperationType == 2){

                        File resultFile = applayRule4Index.applyFileByteOperationRule2(itemFile);

                        if(resultFile != itemFile ){
                            System.out.println("itemFile["+j+"] 符合规则(File) 应用Rule成功 " + applyRuleString+ "  = " + itemFile.getAbsolutePath() );
                        }else{
                            System.out.println("itemFile["+j+"] 不符合规则(File) = " + itemFile.getAbsolutePath() );
                        }


                    }

                }

            }

        }

        setProperity();
    }


    static  void addCurFileTypeMapItemWithKey(String keyType, File curFile) {
        if (CurDirFileTypeMap.containsKey(keyType)) {
            ArrayList<File> fileList = CurDirFileTypeMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            CurDirFileTypeMap.put(keyType, fileList);
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

    static ArrayList<Rule> realTypeRuleList = new ArrayList<Rule>();  // 规则的集合


    static Rule getRuleByIndex(int index){
        for (int i = 0; i <realTypeRuleList.size() ; i++) {
            if(realTypeRuleList.get(i).rule_index == index){
                return realTypeRuleList.get(i);
            }
        }
        return null;
    }



    static  Rule getRuleByIdentify(String identify){
        for (int i = 0; i <realTypeRuleList.size() ; i++) {
            if(realTypeRuleList.get(i).identify.equals(identify)){
                return realTypeRuleList.get(i);
            }
        }
        return null;
    }
}
