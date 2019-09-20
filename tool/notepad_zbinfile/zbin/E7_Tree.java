import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class E7_Tree{

    static int DEFAULT_DEEP_NUM = 30;
    static int DEFAULT_DEEP_INDEX = 4;

    // 输入的参数列表
    static ArrayList<String> mKeyWordName = new ArrayList<>();
    static ArrayList<Integer> mDeepList = new ArrayList<Integer>();
    public static void main(String[] args) {
        long timestamp1 = System.currentTimeMillis();
        int currentDeep = DEFAULT_DEEP_INDEX;
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
            System.out.println("输入树深度数值为空 默认输出深度为:"+DEFAULT_DEEP_INDEX+" 的树形图");
            mDeepList.add(DEFAULT_DEEP_INDEX);
        }else{

            for (int i = 0; i < mKeyWordName.size(); i++) {
                String inputItem = mKeyWordName.get(i).trim();
                if( isNumeric(inputItem) && Integer.parseInt(inputItem) > 0){
                   int curDeepIndex =  Integer.parseInt(inputItem);
                    mDeepList.add(curDeepIndex);
                }
            }
        }

if(mDeepList.size() == 0){
    System.out.println("输入树深度数值未包含树深度数据   默认输出深度为:"+DEFAULT_DEEP_INDEX+" 的树形图");
    mDeepList.add(DEFAULT_DEEP_INDEX);
}

        for (int i = 0; i < mDeepList.size(); i++) {
            showTreePictureWithDeep(mDeepList.get(i));
        }

        long timestamp2 = System.currentTimeMillis();
        long timedistance = timestamp2 - timestamp1;

        System.out.println("程序执行花销 "+ Double.parseDouble(nf.format((Double) (timedistance / (1024d))))+ "秒!");

    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    static void  showTreePictureWithDeep(int deepCount){
        if(deepCount > DEFAULT_DEEP_NUM){
            System.out.println("(超过当前显示最大深度-请重新输入树深度) 当前支持最大深度:"+DEFAULT_DEEP_NUM);
            return;
        }
                for (int i = 0; i < mDeepTreeList.size(); i++) {
                   if(deepCount == (int)(mDeepTreeList.get(i).deep)){
                       mDeepTreeList.get(i).calculPicture();
                       mDeepTreeList.get(i).showPicture();
                       break;
                   }

        }

    }

   static void  testBlock(int deepCount, int curDeep , String content){

       ArrayList<String> blockArr =   DeepTreeParam.buildSinggleBlock(deepCount,curDeep,content);

       for (int i = 0; i < blockArr.size() ; i++) {
           System.out.println(blockArr.get(i));
       }
    }






    static NumberFormat nf = new DecimalFormat("0.000");
    static int BLOCK_LENGTH = 6 ;  // 当前的结点的长度
    static int BLOCK_CONTENT_LENGTH = 4 ;  // 当前的结点内容长度
    static int BLOCK_DISTANCE = 4 ;  // 当前的结点间间隔
    static String paddingString = "-";
    static String BlockStr_Top_RIGHT = "╬";  // 构成Block的四个角的图形
    static String BlockStr_Top_LEFT = "╬";
    static String BlockStr_Buttom_RIGHT = "╬";
    static String BlockStr_Buttom_LEFT = "╬";
    static String BlockStr_UP = "║";
    static String BlockStr_DOWN = "═";
static ArrayList<DeepTreeParam> mDeepTreeList = new ArrayList<DeepTreeParam>();

static{
    for (int i = 1; i < DEFAULT_DEEP_NUM +1 ; i++) {
        int deep = i;
        //    System.out.println("深度:"+i+"     叶子数:"+new E7_Tree().new DeepTreeParam().getLeafCountWithDeep(i)+"   总框数:"+getBlockWithDeep(i) + "   总宽度:"+getWidthWithDeep(i) + "   宽度分配:"+ Arrays.toString(getLeafRank(i)));
        mDeepTreeList.add( new DeepTreeParam(deep));
    }



}
    static class DeepTreeParam{
        double deep;  //  深度
        double width;  // 宽度
        double blocks;  // 总的结点数
        double leafBlocks ; // 底层叶子结点数量
        double[] leafRankArr ;  // 底层叶子结点的均分情况
        double rootContentPaddingWidth ; // 顶部内容补间间隔
        double rootContentWidth;  // 顶部内容的长度
        double paddingLeft ;  // 离左补间间隔
        double paddingRight;   //  离由补间间隔
        int treeRow;  // 画出属性图 所需要的行数
        double blockDistance; // block 相互之间的距离
        ArrayList<String> curPicTreeStringList;  // 画出当前深度树  所需要的字符队列




        DeepTreeParam(int deep){
            this.deep = deep;
            initWithDeep(deep);
        }


        @Override
        public String toString() {
            return "【深度 Deep="+this.deep+"】"+"【宽度 width="+this.width+"】"+"【结点数 blocks="+this.blocks+"】"+"【图像行数 treeRow="+this.treeRow+"】"+"【叶子结点数 leafBlocks="+this.leafBlocks+"】"+"【Block宽度="+this.rootContentWidth+"】"+"  【内容间隔 rootContentPaddingWidth="+this.rootContentPaddingWidth+"】"+"【左右间距 paddingLeft="+this.paddingLeft+"】";
        }

        void initWithDeep(int deep){
            this.blocks = getBlockWithDeep(deep);
            this.leafBlocks = getLeafCountWithDeep(deep);
            this.leafRankArr = getLeafRank(deep);
            this.width = getWidthWithDeep(deep);
            this.treeRow = calculTreeRow(deep);
            this.rootContentPaddingWidth = calculContentPaddingWidthWithArr(leafRankArr);
           this.rootContentWidth = this.rootContentPaddingWidth + 4;
           this.paddingLeft = calculPaddingLeftWithArrAndDeep(leafRankArr);
           this.paddingRight = this.paddingLeft;
        }

        static double calculContentPaddingWidthWithArr(double[] arr){
            double padding = 0;
            if(arr.length != 3){
                System.out.println(" calculContentPaddingWidthWithArr 当前double[] 长度不等于3 发生异常！");
                return -1;
            }
            double pre = arr[0];
            double middle = arr[1];

            if(middle > pre){  // 中间大
                padding = middle * 6 + (middle -1)*4 -4;

            }else{  // 中间小
                padding = middle * 6 + (middle + 1)*4 -2;
            }

            if(padding < BLOCK_LENGTH){
                padding = 0;
            }

            return padding;
        }


        static double calculPaddingLeftWithArrAndDeep(double[] arr ){
            double paddingLeft = 0;
            if(arr.length != 3){
                System.out.println(" calculPaddingLeftWithArr  当前double[] 长度不等于3 发生异常！");
                return -1;
            }
            double pre = arr[0];
            double middle = arr[1];

            if(middle > pre){  // 左边小
                paddingLeft = pre * 6 + (pre)*4;

            }else{  // 左边大
                paddingLeft = pre * 6 + (pre -1 )*4 -1;
            }

            if(paddingLeft < BLOCK_LENGTH){
                paddingLeft = 0;
            }


            return paddingLeft;
        }

        static double getWidthWithDeep(double deep){
            double  leafNum =  Math.pow(2.0,deep -1 );
            double width = leafNum * 6 + (leafNum -1)* 4;
            return width;
        }

        static double  getBlockWithDeep(double deep){
            double blockNum =  Math.pow(2.0,deep  ) -1 ;
            return blockNum;
        }




        static int  calculTreeRow(int deep){

            return deep * 3;
        }


        static double[] getLeafRank(double deep){
            double[] intArr = {0,0,0};
            if(deep == 1){
                return  intArr;
            }

            double width = getWidthWithDeep(deep);
            double leafNum = getLeafCountWithDeep(deep);
            int pre = 0;
            int middle = 0;
            int end = 0;
            int pre_middle_end = pre + middle + end;
            boolean addFlag = false;
            int num = 0;
            while(!(pre_middle_end ==leafNum && pre == end &&  Math.abs(pre - middle) == 1 )){
                if(addFlag){  // 自增
                    pre ++;
                    end = pre;
                    middle = pre + 1;
                    addFlag = false;
                }else{
                    addFlag = true ;
                    middle = pre -1;
                }
                pre_middle_end = pre + middle + end;
                num++;
                // System.out.println("计算了 "+ num+"次！   pre="+pre+"    middle="+middle+"   end="+end);
            }
            intArr[0] = pre;
            intArr[1] = middle;
            intArr[2] = end;


            return intArr;
        }

        static int[]    getPositionRankWithLawer(double lawer){
            int[] initArr = {1,1};

            if(lawer == 1){
                return initArr;
            }
            int beginPosition =  (int)Math.pow(2.0,lawer -1 );
            int endposition = (int)Math.pow(2.0,lawer  ) -1 ;
            initArr[0] = beginPosition;
            initArr[1] = endposition;
            return initArr;
        }


        static double getRowBlockNum(double row ){
            double  leafNum =  Math.pow(2.0,row -1 );
            return leafNum;
        }



        static double getLeafCountWithDeep(double deep){
            double  leafNum =  Math.pow(2.0,deep -1 );
            return leafNum;
        }


         void calculPicture(){
        this.curPicTreeStringList = new ArrayList<String>();
        double curWidth = this.width;
        double deepCount = this.deep;
             double middlePadding = this.rootContentPaddingWidth;
             double leftPadding = this.paddingLeft;
             for (int i = 1; i < deepCount+1; i++) {
                 // 当前该行 画多少个 block 出来   首先 应该画出的是root行
                double currentLawer  =i ;   //  当前的深度
                double curRowBlockNum = getRowBlockNum(currentLawer); // 当前行的block 个数
                int[] positionRank = getPositionRankWithLawer(currentLawer);  // 当前block内容起始的位置
                ArrayList<String>  curRowPicture = buildBlockPicture((int)currentLawer,(int) deepCount,(int) curRowBlockNum ,positionRank , (int)middlePadding,(int)leftPadding,(int)curWidth );
                //  左右对称 来绘制图形
                 if(curRowPicture != null && curRowPicture.size() > 0 ){

                     for (int j = 0; j <curRowPicture.size() ; j++) {
                         curPicTreeStringList.add(curRowPicture.get(j));
                     }

                 }
             }
        }


        void showPicture(){
            if(this.curPicTreeStringList != null && this.curPicTreeStringList.size() > 0){
                for (int i = 0; i < this.curPicTreeStringList.size(); i++) {
                    System.out.println(this.curPicTreeStringList.get(i));
                }
                System.out.println("------------------------深度"+(int)this.deep+"树形图-----------------------------");
            }

        }

         ArrayList<String>   buildBlockPicture(int curLawer ,int layerCount, int curBlockNum , int[] initPositionArr, int middlePadding,int paddingLeft,int width){
            ArrayList<String> currentStringList =  new  ArrayList<String>();
            ArrayList<ArrayList<String>> arrArrList = new  ArrayList<ArrayList<String>>();
int beginPosition = initPositionArr[0];
             for (int i = 0; i < curBlockNum; i++) {
                 arrArrList.add(buildSinggleBlock(layerCount,curLawer,""+(beginPosition++)));
             }
             int blockDistance = calculBlockDistance(curLawer, layerCount);

             String lineTop_1 = "";
             String lineMiddle_1 = "";
             String lineButtom_1 = "";
             for (int i = 0; i < arrArrList.size(); i++) {
                 ArrayList<String> blockItemArr = arrArrList.get(i);
                 String top_temp = blockItemArr.get(0);
                 String middle_temp = blockItemArr.get(1);
                 String buttom_temp = blockItemArr.get(2);
                 lineTop_1 = lineTop_1 + top_temp + (i==arrArrList.size()-1 ? "": buildStringRepeat(" ",blockDistance));
                 lineMiddle_1 = lineMiddle_1 + middle_temp+ (i==arrArrList.size()-1 ? "": buildStringRepeat(" ",blockDistance));
                 lineButtom_1 = lineButtom_1 + buttom_temp+ (i==arrArrList.size()-1 ? "": buildStringRepeat(" ",blockDistance));
             }

             int currentPaddingLeft = calculPaddingLeft(curLawer,layerCount);
             String lineTop_2 = buildStringRepeat(" ",currentPaddingLeft)+lineTop_1+buildStringRepeat(" ",currentPaddingLeft);
             String lineMiddle_2 =buildStringRepeat(" ",currentPaddingLeft)+lineMiddle_1+buildStringRepeat(" ",currentPaddingLeft);
             String lineButtom_2 =buildStringRepeat(" ",currentPaddingLeft)+lineButtom_1+buildStringRepeat(" ",currentPaddingLeft);

             if(lineTop_2.length() !=width || lineMiddle_2.length() !=width || lineButtom_2.length() !=width){
                 System.out.println("width = "+width +"   当前存在不对齐的情况  lineTop_2.length() ="+lineTop_2.length() +"    lineMiddle_2.length()="+lineMiddle_2.length()+"   lineButtom_2.length()="+lineButtom_2.length());
                 System.out.println("lineTop_2 ="+lineTop_2);
                 System.out.println("lineMiddle_2 ="+lineMiddle_2);
                 System.out.println("lineButtom_2 ="+lineButtom_2);
                 System.out.println("currentPaddingLeft ="+currentPaddingLeft);
             }

             currentStringList.add(lineTop_2);
             currentStringList.add(lineMiddle_2);
             currentStringList.add(lineButtom_2);
// 首先创建  空的 空格
/*            ╬════╬
             ║        ║
            ╬════╬*/



             return currentStringList;
        }

        static int  calculPaddingLeft(int curLawer , int deep){
            int distance = 0 ;

            if(deep == 1){
                return 0;
            }

            if(curLawer == deep){  // 最后一层左右间距为0
                return 0;
            }

            if(deep - curLawer ==1){  //  倒数第二层 间距为5
                return 5;
            }

            int currentPaddingLawerIndex = deep - curLawer + 1;
//(1,3)

            int preLawerContentWidth = getPaddinghFromList(currentPaddingLawerIndex);
            distance = preLawerContentWidth;
//   (1,4)    0
// ( 2 ,4)   24   第二层的间距是第一层的block的大小-2
// (3 ,4 )   14
// (4,4)     4


            // (1,3)  0
            //(2,3)  14
            //(3,3)  4

            return distance;





        }
      static int  calculBlockDistance(int curLawer , int deep){
            int distance = 0 ;

            if(curLawer ==1){  //  第一层 间隔为0  只有一个Block
                return 0;
            }

            if(curLawer == deep){  // 最后一层间隔为4
                return 4;
            }

            int preLawerDeep = deep - curLawer + 2;
            int preLawerContentWidth = getBlockWidthFromList(preLawerDeep);
            distance = preLawerContentWidth -2;
//   (1,4)    0
// ( 2 ,4)   24   第二层的间距是第一层的block的大小-2
// (3 ,4 )   14
// (4,4)     4


          // (1,3)  0
          //(2,3)  14
          //(3,3)  4

            return distance;


        }
        static String buildStringRepeat(String src , int count){
            String result = "";
            for (int i = 0; i < count; i++) {
                result=src+result;
            }
            return result;
        }


        // 总的层数 , 当前层数 ， content 是方框内的显示的内容
      static   ArrayList<String> buildSinggleBlock(int deepCount, int curDeep , String content){
            ArrayList<String> singleBlockList = new ArrayList<String>();
int lawerIndex = deepCount - curDeep + 1;  // 1 是最下面的那层
                                      // 2 是倒数第二层
             String lineTop = "";
             String lineMiddle = "";
             String lineButtom = "";
             String curContent = content.trim();


             if(lawerIndex == 1 || lawerIndex == 2){

                 int positionPaddingSize = getPaddingChineseLength(curContent);
                 if(positionPaddingSize > BLOCK_CONTENT_LENGTH){
                     curContent = curContent.substring(0,BLOCK_CONTENT_LENGTH);
                     positionPaddingSize = getPaddingChineseLength(curContent);
                 }
                 int contentEmptyCount = BLOCK_CONTENT_LENGTH -positionPaddingSize;

                 lineTop ="╬════╬";
                 lineMiddle = "║"+curContent+buildStringRepeat(" ",contentEmptyCount)+ "║";
                 lineButtom="╬════╬";
                 singleBlockList.add(lineTop) ;
                 singleBlockList.add(lineMiddle);
                 singleBlockList.add(lineButtom);
                 return singleBlockList;
             }
             int currentWidth = (int)getBlockWidthFromList(lawerIndex);
             int contentWidth = currentWidth -2; // 当前block的内容宽度 不包含墙
             int positionPaddingSize = getPaddingChineseLength(curContent);
          if(positionPaddingSize > BLOCK_CONTENT_LENGTH){
              curContent = curContent.substring(0,BLOCK_CONTENT_LENGTH);
              positionPaddingSize = getPaddingChineseLength(curContent);
          }
             int contentEmptyCount = contentWidth -positionPaddingSize;



             lineTop = "╬"+buildStringRepeat("═",currentWidth-2)+"╬";
             if(positionPaddingSize < ((contentWidth/12)*5) ){ //如果宽度的内容小于总的内容的 5/12
                 int emptyCount = contentWidth - 2*getPaddingChineseLength(curContent);
                 lineMiddle="║"+curContent+buildStringRepeat("-",emptyCount)+curContent+"║";
             }else{    // 大于   5/12  那么 只显示一边  显示在中间
                 int emptyCount = (contentWidth - getPaddingChineseLength(curContent))/2;
                 lineMiddle="║"+buildStringRepeat("-",emptyCount)+curContent+buildStringRepeat("-",emptyCount)+"║";
             }

             lineButtom = "╬"+buildStringRepeat("═",currentWidth-2)+"╬";

             singleBlockList.add(lineTop);
             singleBlockList.add(lineMiddle);
             singleBlockList.add(lineButtom);
             if(lineTop.length() != lineMiddle.length() || lineMiddle.length() != lineButtom.length() ){

                 System.out.println("   长度计算不一致  lineTop.length() ="+lineTop.length() +"    lineMiddle.length()="+lineMiddle.length()+"   lineButtom.length()="+lineButtom.length());
//                 System.out.println("lineTop_2 ="+lineTop_2);
//                 System.out.println("lineMiddle_2 ="+lineMiddle_2);
//                 System.out.println("lineButtom_2 ="+lineButtom_2);
//                 System.out.println("currentPaddingLeft ="+currentPaddingLeft);
             }
            return singleBlockList;
        }





        static int getPaddinghFromList(int currentDeep){
            int mPadding = 0;
            for (int i = 0; i <mDeepTreeList.size() ; i++) {
                if(currentDeep == mDeepTreeList.get(i).deep){
                    mPadding = (int)mDeepTreeList.get(i).paddingLeft;
                    break;
                }

            }

            return mPadding;
        }

        static int getBlockWidthFromList(int currentDeep){
            int mBlockWidth = 0;
            for (int i = 0; i <mDeepTreeList.size() ; i++) {
                if(currentDeep == mDeepTreeList.get(i).deep){
                    mBlockWidth = (int)mDeepTreeList.get(i).rootContentWidth;
                    break;
                }

            }

              return mBlockWidth;
        }



    }


    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }


    public static int getChineseCount(String oriStr) {
        int count = 0;
        for (int i = 0; i < oriStr.length(); i++) {
            char itemChar = oriStr.charAt(i);
            /*

|| (itemChar+"").equals("，")
|| (itemChar+"").equals("’")
|| (itemChar+"").equals("‘")

|| (itemChar+"").equals("；")
             */
            if ((itemChar + "").equals("：")
                    || (itemChar + "").equals("】") || (itemChar + "").equals("【") || (itemChar + "").equals("）")
                    || (itemChar + "").equals("（") || (itemChar + "").equals("￥") || (itemChar + "").equals("—")
                    || (itemChar + "").equals("？") || (itemChar + "").equals("，") || (itemChar + "").equals("；")
                    || (itemChar + "").equals("！") || (itemChar + "").equals("《")
                    || (itemChar + "").equals("》") || (itemChar + "").equals("。") || (itemChar + "").equals("、")
            ) {
                count++;
                continue;
            }
            boolean isChinese = isContainChinese(itemChar + "");
            if (isChinese) {
                count++;
            }
        }
        return count;
    }


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }






}