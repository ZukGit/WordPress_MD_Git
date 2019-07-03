

import java.io.*;
import java.util.*;

import it.sauronsoftware.jave.Encoder;

//  把bat 放入目录  点击 执行  更改目录中文件的名称
//
//land1.mp4
//and1 - 副本.mp4

public class D0_MP4_NameWithSize {
    static String UserDir =  System.getProperty("user.dir");
    static String CurrentDir =  "";
    public static final ArrayList<File> mp4FileList_All = new ArrayList<File>();
    public static final ArrayList<File> mp4FileList_NeedFix = new ArrayList<File>();
    public static final ArrayList<File> mp4FileList_Fixed = new ArrayList<File>();
    public static Map<String,ArrayList<File>> nameMap = new HashMap<String,ArrayList<File>>();

    public static void main(String[] args) {


        String mMP4DirPath = null;

        // 1.bat调用测试打开===begin
        if (args.length >= 1) {
            mMP4DirPath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        // 1.bat调用测试打开===end

      //  mMP4DirPath  = UserDir;          // 2.本地测试打开


        File curMP4Dir;
        curMP4Dir = new File(mMP4DirPath);
        if (mMP4DirPath != null && !mMP4DirPath.isEmpty() && (curMP4Dir.exists()) && curMP4Dir.isDirectory()) {
			CurrentDir = curMP4Dir.getAbsolutePath();
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        // 获取 当前路径下的 所有 mp4 文件
		        System.out.println("当前文件夹路径:" + curMP4Dir.getAbsolutePath());
        File[] curFileList = curMP4Dir.listFiles();
getMP4InCurrentDir(curFileList);

addfixedFileItemAndUpdate();


            System.out.println("Main-Over!");

    }

    static void addfixedFileItemAndUpdate( ) {
    // 获取新的名称
if(mp4FileList_Fixed.size() > 0){
        for (int i = 0; i < mp4FileList_Fixed.size() ; i++) {
            File needFixFile = mp4FileList_Fixed.get(i);
            String fileName = needFixFile.getName();
            FixNameFileItem item = new FixNameFileItem(needFixFile, fileName , true);
            fixedFileItemList.add(item);

        }
    }

if(mp4FileList_NeedFix.size() > 0){
        for (int i = 0; i < mp4FileList_NeedFix.size() ; i++) {
            File needFixFile = mp4FileList_NeedFix.get(i);
            String fileName = needFixFile.getName();
            FixNameFileItem item = new FixNameFileItem(needFixFile, fileName , false);
            fixedFileItemList.add(item);
        }
    }



                if(fixedFileItemList.size() > 0){

                    for (FixNameFileItem fixItem:fixedFileItemList){

                        fixItem.update();

                    }

                    //  需要对fixedFileItemList 进行排序   以字母先后顺序
                    fixedFileItemList.sort(new Comparator<FixNameFileItem>() {
                        @Override
                        public int compare(FixNameFileItem o1, FixNameFileItem o2) {

                            if(o1.preType.equals(o2.preType)){   // land   land 相同
                                if(o1.type_size_identity.equals(o2.type_size_identity)){   // land_1x1   相同的 对比 随便


//                                      if(o1.width * o1.high < o2.width * o2.high){
//                                          System.out.println(" returnA fixItem_ident1="+o1.type_size_identity+ "   fixItem="+o1.width+"x"+o1.high);
//                                          System.out.println(" returnA fixItem_ident2="+o2.type_size_identity+ "   fixItem="+o2.width+"x"+o2.high);
//                                          return 1;
//                                      }else{
//
//                                          if(o1.width * o1.high == o2.width * o2.high){
//                                              return 0;
//                                          }
//                                          System.out.println(" returnB fixItem_ident3="+o1.type_size_identity+ "   fixItem="+o1.width+"x"+o1.high);
//                                          System.out.println(" returnB fixItem_ident4="+o2.type_size_identity+ "   fixItem="+o2.width+"x"+o2.high);
//
//
//                                          return -1;
//                                      }


                                    return 0 ;

                                }else{ // land_0100x100  与 land_0200x0200 对比
                                   // lake_0960x0544   lake_0544x0960  随笔
                                    if( o1.width <  o2.width){ // 对比高度

                                        System.out.println(" returnC fixItem_ident1="+o1.type_size_identity+ "   fixItem="+o1.width+"x"+o1.high);
                                        System.out.println(" returnC fixItem_ident2="+o2.type_size_identity+ "   fixItem="+o2.width+"x"+o2.high);

                                        return -1;
                                    }else{

                                        System.out.println(" returnD fixItem_ident1="+o1.type_size_identity+ "   fixItem="+o1.width+"x"+o1.high);
                                        System.out.println(" returnD fixItem_ident2="+o2.type_size_identity+ "   fixItem="+o2.width+"x"+o2.high);
                                        return 1;
                                    }
                                }

                            }else{  // 对比 preType的顺序
                                // land
                                // sky
                                // lake
                              //  getCommonEndIndex(o1.preType , o2.preType);

                                System.out.println(" returnX1 fixItem_ident1="+o1.type_size_identity+ "   fixItem="+o1.width+"x"+o1.high);
                                System.out.println(" returnX2 fixItem_ident2="+o2.type_size_identity+ "   fixItem="+o2.width+"x"+o2.high);

                                int shortPreTypeLength = o1.preType.length() > o2.preType.length() ? o2.preType.length():o1.preType.length();

                                for (int i = 0; i < shortPreTypeLength; i++) {
                                    char c1 = o1.preType.charAt(i);
                                    char c2 = o2.preType.charAt(i);

                                    if(c1 == c2){
                                        continue;
                                    }
                                    if(c1 > c2){
                                        return 1;
                                    } else {
                                        return -1;
                                    }
                                }
                            }
                          //  land_1000x1000;  以字母顺序排序
                            System.out.println(" returnF fixItem_ident1="+o1.type_size_identity+ "   fixItem="+o1.width+"x"+o1.high);
                            System.out.println(" returnF fixItem_ident2="+o2.type_size_identity+ "   fixItem="+o2.width+"x"+o2.high);
                            return 0;    // 依据 名称 进行排序
                        }
                    });


int count = 1;
                    for (FixNameFileItem fixItem:fixedFileItemList){
                        System.out.println("count = "+ count +" fixItem_ident="+fixItem.type_size_identity+ "   fixItem="+fixItem.width+"x"+fixItem.high);
                        fixItem.fillMapMapList();
                        fixItem.updateNewFile();
                        count++;
                    }


                    for (FixNameFileItem fixItem:fixedFileItemList){
                        if(!fixItem.isNewFileExist){ // 先对那些 新文件不存在的那些文件进行更名的操作
                            fixItem.tryRenameOperation();
                        }
                    }

                    for (FixNameFileItem fixItem:fixedFileItemList){
                        if(fixItem.isNewFileExist){ // 之后对 那些 新文件存在的那些文件进行更名的操作
                            fixItem.tryRenameOperation();
                        }
                    }

        }



}

    //
    static void getMP4InCurrentDir(File[] fileList) {
        if(fileList == null || fileList.length ==0){
            System.out.println(" 当前路径的文件夹为0！ ");
            return;
        }


        for (File fileItem: fileList){
String fileName = fileItem.getName();
if(fileName.endsWith(".mp4")){ //  如果文件以 .mp4 结尾
    mp4FileList_All.add(fileItem);
}


        }

      if(mp4FileList_All.size() > 0){
          for (File mp4FileItem : mp4FileList_All ){

               isRuleNameStyle(mp4FileItem);

          }
      }
    }

    //  中间的 002 标识 111x111这样的视频的数量
    //  最后的序号 11 标识在当前land视频中的序号
    //land_0111x0111_002_011.mp4;

    static boolean isRuleNameStyle(File mp4File) {
        String mp4FileName =  mp4File.getName();
        boolean isRuleFlag = false;
        String mNameNoType = mp4FileName.substring(0,mp4FileName.lastIndexOf("."));
        String[] strArr = mNameNoType.split("_");
        if(!mp4FileName.contains(" ")  && strArr != null && strArr.length == 4 && strArr[1].contains("x")
                && Integer.parseInt( strArr[strArr.length -1] ) > 0 && Integer.parseInt( strArr[strArr.length -2] ) > 0 ){
            isRuleFlag = true;
        }

        if(isRuleFlag){
            mp4FileList_Fixed.add(mp4File);
            String typeName = strArr[0];
            if(nameMap.containsKey(typeName)){
                ArrayList<File> fileList = nameMap.get(typeName);
                fileList.add(mp4File);
            }else{
                ArrayList<File> fileList = new  ArrayList<File>();
                fileList.add(mp4File);
                nameMap.put(typeName,fileList);
            }

        }else{
            mp4FileList_NeedFix.add(mp4File);
        }

        return isRuleFlag;
    }


static ArrayList<FixNameFileItem> fixedFileItemList = new ArrayList<FixNameFileItem>();
static Map<String,Map<String,ArrayList<FixNameFileItem>>> mFixNameFileItemMap = new HashMap<String,Map<String,ArrayList<FixNameFileItem>>>();



    static  Encoder encoder = new Encoder();


    static class FixNameFileItem{
        File oldFile;
        boolean isOldFileNameRuled;
        String oldFileName;
        String oldFileAbs_Path;
        String preType;

        int high;
        int width;
        String sizeStr;
        String type_size_identity;
        int typeIndex;
        int typeSizeIndex;

        String newFileName;
        String newFileAbs_Path;
        File newFIle;
        boolean isNewFileExist;

        // 命名规则
// preType_sizeStr_typeSizeIndex_typeIndex.mp4
//land_0111x0111_002_011.mp4;
        FixNameFileItem(File oldFile , String oldFileName  ,boolean isOldFileNameRuled ){
            this.oldFile = oldFile;
            this.oldFileAbs_Path = oldFile.getAbsolutePath();
            this.oldFileName = oldFileName;
            this.isOldFileNameRuled = isOldFileNameRuled;
        }


       void tryRenameOperation(){
         boolean flag =   oldFile.renameTo(newFIle);
         if(flag){
             System.out.println(oldFileName+" 转为 "+ newFileName +" 成功！");
         }else{
             System.out.println(oldFileName+" 转为 "+ newFileName +" 失败！");
         }

        }

       String updateNewFile(){
            String newName = "";
//20190824_21
        //   String timeStamp =
// // preType_sizeStr_typeSizeIndex_typeIndex+"时间戳?".mp4
           // typeSizeIndex 为三位   typeIndex为三位
           newName =type_size_identity+"_"+addForZeroStr(typeSizeIndex)+"_"+addForZeroStr(typeIndex)+".mp4";  // 定义名称
           newFileName = newName;
           newFileAbs_Path = CurrentDir + File.separator+newName;
           newFIle = new File(newFileAbs_Path);
           isNewFileExist = newFIle.exists();
           return newName;
        }





       synchronized void  fillMapMapList(){
        //    fillMapMapList
// static Map<String,Map<String,ArrayList<File>>> mFixNameFileItemMap = new HashMap<String,Map<String,ArrayList<File>>>();
            if(!mFixNameFileItemMap.containsKey(preType)){ // 如果没有包含 前缀 land
              Map<String,ArrayList<FixNameFileItem>> mapItem = new HashMap<String,ArrayList<FixNameFileItem>>();


                ArrayList<FixNameFileItem> listItem = new   ArrayList<FixNameFileItem>();
                this.typeSizeIndex = 1; // 第一次包含时它所对应的 类型_Size index 为 1
                this.typeIndex = 1;    // 第一次包含时 它所对应的  类型 index  为1 ;
                listItem.add(this);
                mapItem.put(type_size_identity,listItem);
                mFixNameFileItemMap.put(preType,mapItem);
            }else{  //  已经包含了前缀 land

// 已经包含了前缀 land   取出 map

                Map<String,ArrayList<FixNameFileItem>> mapItem =  mFixNameFileItemMap.get(preType);

                if(!mapItem.containsKey(type_size_identity)){  // 没包含了 对应尺寸的 ArrayList
                    ArrayList<FixNameFileItem> listItem = new   ArrayList<FixNameFileItem>();
                    this.typeSizeIndex = 1; // 第一次包含时它所对应的 类型_Size index 为 1
                    this.typeIndex = getFixItemSizeWithPreType(preType) + 1;    // 第一次包含时 它所对应的  类型 index  需要计算当前已经有多少个land数据;
                    System.out.println(" 未包含0 当前  type_size_identity = "+ type_size_identity + "    typeIndex ="+ typeIndex);
                    listItem.add(this);
                    mapItem.put(type_size_identity, listItem);

                }else{    // 包含了 对应尺寸的 ArrayList
                    ArrayList<FixNameFileItem> listItem =  mapItem.get(type_size_identity);
                    this.typeSizeIndex = listItem.size() + 1; // 第一次包含时它所对应的 类型_Size index 为 1
                    this.typeIndex = getFixItemSizeWithPreType(preType) + 1;
                    System.out.println(" 以包含1 当前  type_size_identity = "+ type_size_identity + "    typeIndex ="+ typeIndex);
                    listItem.add(this);
                }
            }
            // 继续点   遍历完 FixNameFileItem   创建新的文件 需要往



        }

     synchronized   int getFixItemSizeWithPreType( String preTypeKey){
            int currentSize = 0;
         Map<String,ArrayList<FixNameFileItem>> mapItem =  mFixNameFileItemMap.get(preTypeKey);

         Map.Entry<String , ArrayList<FixNameFileItem>> entry;


         if(mapItem != null){
             Iterator iterator = mapItem.entrySet().iterator();

             while( iterator.hasNext() ){
                 entry = (Map.Entry<String , ArrayList<FixNameFileItem>>) iterator.next();
                 entry.getKey();  //Map的Value
                 currentSize = currentSize + entry.getValue().size();

             }
         }

            return currentSize;
        }



      void  update(){
this.preType = calculatePreType();
          try {
              it.sauronsoftware.jave.MultimediaInfo m = encoder.getInfo(oldFile);
              high = m.getVideo().getSize().getWidth();
              width = m.getVideo().getSize().getHeight();
          }catch (Exception e) {
              e.printStackTrace();
              System.out.println(" 解析MP4文件出现异常！ ");
          }
this.sizeStr =   calculateSizeStr(width,high);
this.type_size_identity = this.preType+"_"+this.sizeStr;
      }





      // 宽x高    1000x0900    1280x0720
        String calculateSizeStr(int widthValue , int highValue){
            String sizeStr = "";
            int fixWidthValue = 0;
            int fixHighValue = 0;

            if(widthValue > 9999){   // 宽高最大只能 9999 大了 受不了
                fixWidthValue = 9999;
            }else{
                fixWidthValue = widthValue;
            }
            if(highValue > 9999){   // 宽高最大只能 9999 大了 受不了
                fixHighValue = 9999;
            }else{
                fixHighValue = highValue;
            }
            String widthStr = addForZeroStr(fixWidthValue);
            String highStr = addForZeroStr(fixHighValue);

            // fixWidthValue 和 fixHighValue  进行补零操作

            return widthStr+"x"+highStr;

        }



        String addForZeroStr(int value){
            String valueStr = "";
            if(value > 9999){
                valueStr = "9999";
            }else if(value >= 1000){
                valueStr =  (value+"").trim();
            }else if( value >= 100 ){
                valueStr =  ("0"+value).trim();
            }else if( value >= 10 ){
                valueStr =  ("00"+value).trim();
            }else if(value >= 0 ){
                valueStr =  ("000"+value).trim();
            }
            return valueStr;
        }


String calculatePreType(){
   String preType = "";
   if(isOldFileNameRuled){
       String[] arr = oldFileName.split("_");

       preType = arr[0].toLowerCase().trim();

   }else{
// 不符合规则的那些文件名称  a-z;
       preType =   calculNameForRandomFile(oldFileName);
   }
   return preType;
}


// 找出首次出现非字母的那个索引
static String calculNameForRandomFile(String fileName){
            String preName = "";
            String fileNameFix = fileName.trim();
            int length = fileNameFix.length();
            int indexNoLetter = 0;
    for (int i = 0; i < length; i++) {
        char ch = fileNameFix.charAt(i);
        if(ch>='A' && ch<='Z'  ||  ch>='a' && ch<='z'){
            continue;
        }else{
            indexNoLetter = i;
            break; //  跳出for循环
        }
    }

    if(indexNoLetter > 0){

        preName =    fileNameFix.substring(0,indexNoLetter).trim().toLowerCase();
    }
            return preName;
}



    }

}
