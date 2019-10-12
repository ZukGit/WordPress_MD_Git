import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class E8_Population {
    static NumberFormat nf_6 = new DecimalFormat("0.000000");
    static NumberFormat nf_2 = new DecimalFormat("0.00");
    static long currentStamp ;
    static int currentYear ;  //  当前年份
    static int record_end_year;  // 当前记录的最晚的年份
    // 输入的参数列表
    static ArrayList<String> mKeyWordName = new ArrayList<>();


    public static void main(String[] args) {
        currentStamp = System.currentTimeMillis();
        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy");
        String yearDesc =    dateFormatYYYYMMDD.format(Calendar.getInstance().getTime());
        currentYear = Integer.parseInt(yearDesc);
        record_end_year = getEndRecordYear();
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


        initPureData(currentYear);

        if (mKeyWordName.size() == 0) {
            totalInfo();
            System.out.println("用户输入参数为空 以上为 输出" + getFirstRecordYear() + "年---" + getEndRecordYear() + "年人口数据");
            showTip();
            return;
        }


        if(!checkParamOK()){
            return;
        }
        if(!getDetailInput()){
            return;
        }
        calculSingleTimeInfo(SingleAgeAndBirthYear);


        //


    }

    static int getFirstRecordYear(){
        return popularList.get(0).year;
    }

    static int getEndRecordYear(){
        return popularList.get(popularList.size()-1).year;
    }

    static long getEndRecordYear_PeopleCount(){
        return popularList.get(popularList.size()-1).curSumNum;
    }

    static long getEndRecordYear_ManCount(){
        return popularList.get(popularList.size()-1).curManNum;
    }

    static long getEndRecordYear_WomanCount(){
        return popularList.get(popularList.size()-1).curWomanNum;
    }

    static double getEndRecordYear_AddRate(){
        return popularList.get(popularList.size()-1).addRate;
    }

    static double getEndRecordYear_DeadRate(){
        return popularList.get(popularList.size()-1).deadRate;
    }

    static double getEndRecordYear_BirthRate(){
        return popularList.get(popularList.size()-1).birthRate;
    }

    static long getEndRecordYear_BirthNum(){
        return popularList.get(popularList.size()-1).birthnum;
    }

    static double getEndRecordYear_BirthSexRate(){
        return popularList.get(popularList.size()-1).birthSexRate;
    }

    static double getEndRecordYear_SexRate(){
        return popularList.get(popularList.size()-1).sexRate;
    }


    static double getEndRecordYear_DeadNum(){
        return popularList.get(popularList.size()-1).deadnum;
    }

    static double getEndRecordYear_DeadMan(){
        return popularList.get(popularList.size()-1).deadMan;
    }

    static double getEndRecordYear_DeadWoman(){
        return popularList.get(popularList.size()-1).deadWoman;
    }

    static double getEndRecordYear_PureNum(){
        return popularList.get(popularList.size()-1).pureNum;
    }

    static double getEndRecordYear_PureMan(){
        return popularList.get(popularList.size()-1).pureManNum;
    }

    static double getEndRecordYear_PureWoman(){
        return popularList.get(popularList.size()-1).pureWomanNum;
    }








    /*╔════════════════════════════════════════════════命令格式提示═════════════════════════════════════════════════════════════╗
    ║ 输出当前统计记录-----:【 zpopulation_E8   】 //打印 1949年 至 2018年 人口统计数据                                       ║
    ║ 依据年龄输出记录-----:【 zpopulation_E8 17 18 19 】 //当前年龄为 17 18 19岁的人的情况                                   ║
    ║ 依据出生输出记录-----:【 zpopulation_E8 1992  1993 1994 】 //输出1992年  1993年  1994年 的出生的人口情况                ║
    ║ 依据年龄范围输出记录-:【 zpopulation_E8 30+40 】 //当前年龄为 从30到40年龄的人口情况                                    ║
    ║ 依据出生范围输出记录-:【 zpopulation_E8 1990+1999 】 //输出1990年后至1999年出生(90后)的人口情况                         ║
    ║ 依据出生输出对比记录-:【 zpopulation_E8 1990:1999 】 //对比 1990和1999年的人口情况                                      ║
    ║ 依据年龄输出对比记录-:【 zpopulation_E8 23:26 】 //对比当前23和26岁的人口情况                                           ║
    ║ 依据年龄范围输出记录-:【 zpopulation_E8 60+ 】 //当前年龄为60岁以上(现在为终点)人群统计  数据从1949年开始               ║
    ║ 依据出生范围输出记录-:【 zpopulation_E8 1990+ 】 //对从1992年出生到现在人群统计  数据从1949年开始                       ║
    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
            */

    //    static ArrayList<String> SingleAge = new ArrayList<>();    // 【 zpopulation_E8 17 18 19 】
//    static ArrayList<String> SingleBirth = new ArrayList<>();  //  【 zpopulation_E8 1992  1993 1994 】
    static ArrayList<AgeAndBirthSingleItem> SingleAgeAndBirthYear = new ArrayList<>();  //  【 zpopulation_E8 1992  1993 1994 】

    static ArrayList<String> RankAge = new ArrayList<>();   // 【 zpopulation_E8 30+40 】
    static ArrayList<String> RankBirth = new ArrayList<>();  //  【 zpopulation_E8 1990+1999 】
    static ArrayList<String> CompBirth = new ArrayList<>();  // 【 zpopulation_E8 1990:1999 】
    static ArrayList<String> CompAge = new ArrayList<>();    // 【 zpopulation_E8 23:26 】
    static ArrayList<String> BeginAge = new ArrayList<>();    // 【 zpopulation_E8 60+】
    static ArrayList<String> BeginBirth = new ArrayList<>();    // 【 zpopulation_E8 1990+ 】

    static class AgeAndBirthSingleItem{
        int currentAge;
        int currentBirthYear;
        String currentTitle;
        AgeAndBirthSingleItem(int age , int birthYear , String title){
            this.currentAge = age;
            this.currentBirthYear = birthYear;
            this.currentTitle = title;
        }
    }

    static class AgeAndBirthBeginItem{
        int beginAge;
        int endAge;
        int beginBirthYear;
        int endBirthYear;
        String currentTitle;
    }


    static class AgeAndBirthRang{
        int beginAge;
        int endAge;
        int beginBirthYear;
        int endBirthYear;
        String currentTitle;
    }


    static void calculSingleTimeInfo(ArrayList<AgeAndBirthSingleItem>  ageList) {
        // toCalculSingleAge(SingleAge);  //  继续点


        for (int i = 0; i < ageList.size(); i++) {
            AgeAndBirthSingleItem item = ageList.get(i);
            int inputYear = item.currentBirthYear;
            int inputAge = item.currentAge;
            String title = item.currentTitle;
            int recordMaxYear = getEndRecordYear();
            showSingleAge(inputAge,inputYear,recordMaxYear,title);

        }

    }



    static void showSingleAge( int ageA ,int birthA ,int compareYear ,String title ) {


        PopulationYear_Item itemA = getPopulationWithYear(birthA);
        PopulationYear_Item itemB = getPopulationWithYear(compareYear);
        PopulationYear_Item itemC = null;  // 当前最新的年份
        int comapreAge = currentYear - compareYear;
        if(itemA == null){
            System.out.println("无法查询到 年龄("+ageA+")--出生年份("+birthA+") 的人口数据!");
            return ;
        }
        if(itemB == null){
            System.out.println("无法查询到 年龄("+comapreAge+")--出生年份("+compareYear+") 的人口数据!");
            return ;
        }

        boolean isSameYear = false;
        boolean isInoutMaxRecordYear = false;
        if(birthA == compareYear){
            isSameYear = true;
        }

        if(birthA == getEndRecordYear() || compareYear ==getEndRecordYear()){
            isInoutMaxRecordYear = true;
        }

        // isSameYear= false  isInoutMaxRecordYear = false  //  不是同一年  并且输入的也不是记录的最新年份  那么分别对比  【A ,B】

        // isSameYear = true   isInoutMaxRecordYear = false  // 查询的是同一年  但不是最新的那年   那么把 对比的那年设置为 对比的年份
        // itemB = getPopulationWithYear(getEndRecordYear());   // 【A,New】  提示用户
        // isSameYear = false   isInoutMaxRecordYear = true           // 不是同一年    输入的年龄为 最新的 那么 对比 【new , A】
        // isSameYear= true  isInoutMaxRecordYear = true          // 【new  new 】     , 那么 将自己与自己对比  【new , new 】

        if(isSameYear && isInoutMaxRecordYear){  // true  true   只显示一个的信息  【  1 0 0 】
            itemC = null;
            itemB = null;

        }else if(!isSameYear && !isInoutMaxRecordYear){  // false   false   【 1 1 1 】
            itemC = getPopulationWithYear(getEndRecordYear());  // 当前最新的年份

        }else if(isSameYear && !isInoutMaxRecordYear){  //   【1 1 0 】 true   false   两个不是最新的 相同的对比  那么设置itemB为最新的年份
            itemB = getPopulationWithYear(getEndRecordYear());
            itemC = null;

        }else if(!isSameYear && isInoutMaxRecordYear){  // false   true  【 1  1  0 】
            itemC = null;
        }


        showThreeInfoMation(itemA , itemB  , itemC , title);




    }

    static void  showThreeInfoMation(PopulationYear_Item  itemA,PopulationYear_Item itemB , PopulationYear_Item itemC , String title) {
        ArrayList<String> singleAgeList = new  ArrayList<String>();
        int birthA = itemA.year;
        int ageA = itemA.curAge;

        if(itemB == null && itemC == null){   // 显示单独的最新的   【1 0 0 】
            populationInfoYear(birthA,title);

        }else if (itemB != null && itemC == null){  //  【1 1 0 】

            // 显示 对比的数据

            int birthB = itemB.year ;
            int distanceYear = 0;
            if(birthA > itemB.year){
                distanceYear = birthA - itemB.year;
            }else{
                distanceYear = itemB.year - birthA ;
            }
            singleAgeList.add("当前查询年龄:[ "+birthA+"年 ]"+"[ "+ageA+"岁 ]");
            singleAgeList.add("对比查询年龄:[ "+birthB+"年 ]"+"[ "+itemB.curAge+"岁 ]");

            singleAgeList.add("出生年份:"+birthA+"年");
            singleAgeList.add("对比年份:"+birthB+"年");
            singleAgeList.add("相差年份:"+distanceYear+"年");


            singleAgeList.add(birthA+"年属相:"+itemA.mAnimalString);
            singleAgeList.add(birthB+"年属相:"+itemB.mAnimalString);


            long distance2 = 0 ;
            if(itemA.curSumNum > itemB.curSumNum){
                distance2 = itemA.curSumNum - itemB.curSumNum;
            }else{
                distance2 =  itemB.curSumNum - itemA.curSumNum ;
            }

            singleAgeList.add("("+birthA+"年)总人口:" + calculWanRen(itemA.curSumNum));
            singleAgeList.add("("+birthB+"年)总人口:" + calculWanRen(itemB.curSumNum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)总人口差距:"+calculWanRen(distance2));










            // singleAgeList.add("差距总人口数:("+getEndRecordYear()+"-"+birthA+")"+ calculWanRen(getEndRecordYear_PeopleCount() -itemA.curSumNum ));

            long distance5 = 0 ;
            if(itemA.birthnum > itemB.birthnum){
                distance5 = itemA.birthnum - itemB.birthnum;
            }else{
                distance5 =  itemB.birthnum - itemA.birthnum ;
            }
            singleAgeList.add("("+birthA+"年)出生人数:"+calculWanRen(itemA.birthnum));
            singleAgeList.add("("+birthB+"年)出生人数:"+calculWanRen(itemB.birthnum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)出生人数差距:"+calculWanRen(distance5));




            double distace6 = 0;
            if(itemA.birthRate > itemB.birthRate){
                distace6 = itemA.birthRate - itemB.birthRate;
            }else{
                distace6 =  itemB.birthRate - itemA.birthRate ;
            }




            singleAgeList.add("当前("+birthA+"年)出生率:" + itemA.birthRate + "‰"+"  (千人出生 "+itemA.birthRate+"人)");
            singleAgeList.add("对比("+itemB.year+"年)出生率:" + itemB.birthRate + "‰"+"  (千人出生 "+itemB.birthRate+"人)");
            singleAgeList.add("差距("+itemA.year+"年"+"---"+itemB.year+"年"+")出生率差距:" + Double.parseDouble(nf_6.format(distace6)) + "‰"+"  (千人出生差距 "+Double.parseDouble(nf_6.format(distace6))+"人)");


            long distace7 = 0;
            if(itemA.bitrhManNum > itemB.bitrhManNum){
                distace7 = itemA.bitrhManNum - itemB.bitrhManNum;
            }else{
                distace7 =  itemB.bitrhManNum - itemA.bitrhManNum ;
            }

            singleAgeList.add("("+birthA+"年)出生人数(男):"+calculWanRen(itemA.bitrhManNum));
            singleAgeList.add("("+birthB+"年)出生人数(男):"+calculWanRen(itemB.bitrhManNum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)出生人数相差(男):"+calculWanRen(distace7));


            long distace8 = 0;
            if(itemA.bitrhWomanNum > itemB.bitrhWomanNum){
                distace8 = itemA.bitrhWomanNum - itemB.bitrhWomanNum;
            }else{
                distace8 =  itemB.bitrhWomanNum - itemA.bitrhWomanNum ;
            }

            singleAgeList.add("("+birthA+"年)出生人数(女):"+calculWanRen(itemA.bitrhWomanNum));
            singleAgeList.add("("+birthB+"年)出生人数(女):"+calculWanRen(itemB.bitrhWomanNum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)出生人数相差(女):"+calculWanRen(distace8));


            double distace9 = 0;
            if(itemA.sexRate > itemB.sexRate){
                distace9 = itemA.sexRate - itemB.sexRate;
            }else{
                distace9 =  itemB.sexRate - itemA.sexRate ;
            }

            singleAgeList.add("("+birthA+"年)人口性别比:" + itemA.sexRate);
            singleAgeList.add("("+birthB+"年)人口性别比:" + itemB.sexRate);
            singleAgeList.add("("+birthA+"---"+birthB+"年)人口性别比差距:" + Double.parseDouble(nf_6.format(distace9)));


            long distace10 = 0;
            if(itemA.curManNum > itemB.curManNum){
                distace10 = itemA.curManNum - itemB.curManNum;
            }else{
                distace10 =  itemB.curManNum - itemA.curManNum ;
            }

            singleAgeList.add("("+birthA+"年)男性人口:" + calculWanRen(itemA.curManNum));
            singleAgeList.add("("+birthB+"年)男性人口:" + calculWanRen(itemB.curManNum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)男性人口人口差距:" + calculWanRen(distace10));


            long distace11 = 0;
            if(itemA.curWomanNum > itemB.curWomanNum){
                distace11 = itemA.curWomanNum - itemB.curWomanNum;
            }else{
                distace11 =  itemB.curWomanNum - itemA.curWomanNum ;
            }

            singleAgeList.add("("+birthA+"年)女性人口:" + calculWanRen(itemA.curWomanNum));
            singleAgeList.add("("+birthB+"年)女性人口:" + calculWanRen(itemB.curWomanNum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)女性人口人口差距:" + calculWanRen(distace11));


            long distace12 = 0;
            if(itemA.cur_man_woman_distance > itemB.cur_man_woman_distance){
                distace12 = itemA.cur_man_woman_distance - itemB.cur_man_woman_distance;
            }else{
                distace12 =  itemB.cur_man_woman_distance - itemA.cur_man_woman_distance ;
            }
            singleAgeList.add("("+birthA+"年)总人口男女数量差(男):" + calculWanRen(itemA.cur_man_woman_distance));
            singleAgeList.add("("+birthB+"年)总人口男女数量差(男):" + calculWanRen(itemB.cur_man_woman_distance));
            singleAgeList.add("("+birthA+"---"+birthB+"年)总人口男女数量差(男):" + calculWanRen(distace12));

            double   distace13 = 0 ;
            if(itemA.birthRate > itemB.birthRate){
                distace13 = itemA.birthRate - itemB.birthRate;
            }else{
                distace13 =  itemB.birthRate - itemA.birthRate ;
            }

//            singleAgeList.add("("+birthA+"年)出生率:" + itemA.birthRate + "‰"+"  (千人出生 "+itemA.birthRate+"人)");
//            singleAgeList.add("("+birthB+"年)出生率:" + itemB.birthRate + "‰"+"  (千人出生 "+itemB.birthRate+"人)");
//            singleAgeList.add("("+birthA+"---"+birthB+"年)出生率差距:" + distace13+ "‰"+"  (千人出生差距: "+distace13+"人)");


            double   distace14 = 0 ;
            if(itemA.deadRate > itemB.deadRate){
                distace14 = itemA.deadRate - itemB.deadRate;
            }else{
                distace14 =  itemB.deadRate - itemA.deadRate ;
            }

            singleAgeList.add("("+birthA+"年)死亡率:" + itemA.deadRate + "‰"+"  (千人死亡 "+itemA.deadRate+"人)");
            singleAgeList.add("("+birthB+"年)死亡率:" + itemB.deadRate + "‰"+"   (千人死亡 "+itemB.deadRate+"人)");
            singleAgeList.add("("+birthA+"---"+birthB+"年)死亡率差距:" + Double.parseDouble(nf_2.format(distace14)) + "‰"+"  (千人死亡差距: "+Double.parseDouble(nf_2.format(distace14))+"人)");


            double   distace15 = 0 ;
            if(itemA.addRate > itemB.addRate){
                distace15 = itemA.addRate - itemB.addRate;
            }else{
                distace15 =  itemB.addRate - itemA.addRate ;
            }

            singleAgeList.add("("+birthA+"年)自然增长率:" + itemA.addRate + "‰"+"  (千人净增 "+itemA.addRate+"人)");
            singleAgeList.add("("+birthB+"年)自然增长率:" + itemB.addRate + "‰"+"    (千人净增 "+itemB.addRate+"人)");
            singleAgeList.add("("+birthA+"---"+birthB+"年)自然增长率差距:" + Double.parseDouble(nf_2.format(distace15))+ "‰"+"  (千人净增差距: "+Double.parseDouble(nf_2.format(distace15))+"人)");






            long   distace16 = 0 ;
            if(itemA.pureNum > itemB.pureNum){
                distace16 = itemA.pureNum - itemB.pureNum;
            }else{
                distace16 =  itemB.pureNum - itemA.pureNum ;
            }
            singleAgeList.add("("+birthA+"年)净增长人数:" + calculWanRen(itemA.pureNum));
            singleAgeList.add("("+birthB+"年)净增长人数:" + calculWanRen(itemB.pureNum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)净增长人数差距:" + calculWanRen(distace16));



            long   distace17 = 0 ;
            if(itemA.pureManNum > itemB.pureManNum){
                distace17 = itemA.pureManNum - itemB.pureManNum;
            }else{
                distace17 =  itemB.pureManNum - itemA.pureManNum ;
            }
            singleAgeList.add("("+birthA+"年)净增长人数(男):" + calculWanRen(itemA.pureManNum));
            singleAgeList.add("("+birthB+"年)净增长人数(男):" + calculWanRen(itemB.pureManNum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)净增长人数(男)差距:" + calculWanRen(distace17));



            long   distace18 = 0 ;
            if(itemA.pureWomanNum > itemB.pureWomanNum){
                distace18 = itemA.pureWomanNum - itemB.pureWomanNum;
            }else{
                distace18 =  itemB.pureWomanNum - itemA.pureWomanNum ;
            }
            singleAgeList.add("("+birthA+"年)净增长人数(男):" + calculWanRen(itemA.pureWomanNum));
            singleAgeList.add("("+birthB+"年)净增长人数(女):" + calculWanRen(itemB.pureWomanNum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)净增长人数(女)差距:" + calculWanRen(distace18));


            long   distace19 = 0 ;
            if(itemA.deadnum > itemB.deadnum){
                distace19 = itemA.deadnum - itemB.deadnum;
            }else{
                distace19 =  itemB.deadnum - itemA.deadnum ;
            }
            singleAgeList.add("("+birthA+"年)死亡人口:" + calculWanRen(itemA.deadnum));
            singleAgeList.add("("+birthB+"年)死亡人口:" + calculWanRen(itemB.deadnum));
            singleAgeList.add("("+birthA+"---"+birthB+"年)死亡人口差距:" + calculWanRen(distace19));

            long   distace20 = 0 ;
            if(itemA.deadMan > itemB.deadMan){
                distace20 = itemA.deadMan - itemB.deadMan;
            }else{
                distace20 =  itemB.deadMan - itemA.deadMan ;
            }
            singleAgeList.add("("+birthA+"年)死亡人口(男):" + calculWanRen(itemA.deadMan));
            singleAgeList.add("("+birthB+"年)死亡人口(男):" + calculWanRen(itemB.deadMan));
            singleAgeList.add("("+birthA+"---"+birthB+"年)死亡人口(男)差距:" + calculWanRen(distace20));

            long   distace21 = 0 ;
            if(itemA.deadWoman > itemB.deadWoman){
                distace21 = itemA.deadWoman - itemB.deadWoman;
            }else{
                distace21 =  itemB.deadWoman - itemA.deadWoman ;
            }
            singleAgeList.add("("+birthA+"年)死亡人口(女):" + calculWanRen(itemA.deadWoman));
            singleAgeList.add("("+birthB+"年)死亡人口(女):" + calculWanRen(itemB.deadWoman));
            singleAgeList.add("("+birthA+"---"+birthB+"年)死亡人口(女)差距:" + calculWanRen(distace21));



        }  else if( itemB != null && itemC != null){



        }

        ArrayPrint(singleAgeList,title);
    }


    static void populationInfoYear(int  myear,String mtitle) {
        ArrayList<String> arrLogStrList = new ArrayList<String>();
        for (int i = 0; i < popularList.size(); i++) {
            PopulationYear_Item item = popularList.get(i);

            if(item.year ==myear){
                arrLogStrList.add("时间:" + item.year + "年");
                arrLogStrList.add("该年出生人口当前岁数:" + item.curAge + "岁");
                arrLogStrList.add("该年出生人口属相:" + item.mAnimalString );
                arrLogStrList.add("总人口:" + calculWanRen(item.curSumNum));
                arrLogStrList.add("人口性别比:" + item.sexRate);
                arrLogStrList.add("男性人口:" + calculWanRen(item.curManNum));
                arrLogStrList.add("女性人口:" + calculWanRen(item.curWomanNum));
                arrLogStrList.add("总人口男女数量差(男):" + calculWanRen(item.cur_man_woman_distance));
                arrLogStrList.add("出生率:" + item.birthRate + "‰"+"  (千人出生 "+item.birthRate+"人)");
                arrLogStrList.add("死亡率:" + item.deadRate + "‰"+"  (千人死亡 "+item.deadRate+"人)");
                arrLogStrList.add("自然增长率:" + item.addRate + "‰"+"  (千人净增 "+item.addRate+"人)");
                arrLogStrList.add("净增长人数:" + calculWanRen(item.pureNum));
                arrLogStrList.add("净增长人数(男):" + calculWanRen(item.pureManNum));
                arrLogStrList.add("净增长人数(女):" + calculWanRen(item.pureWomanNum));
                arrLogStrList.add("出生人口:" + calculWanRen(item.birthnum));
                arrLogStrList.add("出生性别比:" + item.birthSexRate);
                arrLogStrList.add("出生男女数量差(男):" + calculWanRen(item.birth_man_woman_distance));
                arrLogStrList.add("出生人口(男):" + calculWanRen(item.bitrhManNum));
                arrLogStrList.add("出生人口(女):" + calculWanRen(item.bitrhWomanNum));
                arrLogStrList.add("死亡人口:" + calculWanRen(item.deadnum));
                arrLogStrList.add("死亡人口(男):" + calculWanRen(item.deadMan));
                arrLogStrList.add("死亡人口(女):" + calculWanRen(item.deadWoman));
                break;
            }



            //     System.out.println("time_year   = "+ item.year + "   pure_man="+item.pureManNum + "   pure_woman= "+item.pureWomanNum + (item.pureManNum == 0 ? "": "男女净值比例:"+(double)item.pureManNum/(double)item.pureWomanNum ) );
            //  System.out.println("time_begin = "+ item.year_begin_timestamp);
            //  System.out.println("time_end   = "+ item.year_end_timestamp);

        }
        ArrayPrint(arrLogStrList, mtitle);

    }




    static boolean checkParamOK() {
        // 检测是否含有字母   当前的输入参数中去除 :  和 +  判断是否含有字母
        boolean flag = true;
        String tempImput = "";

        for (int i = 0; i < mKeyWordName.size(); i++) {
            String curStr = mKeyWordName.get(i);
            tempImput = tempImput + curStr.trim().replace("+", "");
        }

        tempImput = tempImput.replaceAll(":", "");
        tempImput = tempImput.replace("+", "");
        tempImput = tempImput.replace(" ", "");
        if (!isNumeric(tempImput)) {
            System.out.println("用户输入的参数无效( 输入参数中包含字母【" + clearNumber(tempImput) + "】导致) 程序退出！ 请重新输入正确参数！");
            showTip();
            return  false;
        }


        return flag;
    }


    static boolean getDetailInput() {
        boolean flag = true;

/*        static ArrayList<String> SingleAge = new ArrayList<>();    // 【 zpopulation_E8 17 18 19 】 默认与现在对比
        static ArrayList<String> SingleBirth = new ArrayList<>();  //  【 zpopulation_E8 1992  1993 1994 】 默认与现在对比
        static ArrayList<String> RankAge = new ArrayList<>();   // 【 zpopulation_E8 30+40 】
        static ArrayList<String> RankBirth = new ArrayList<>();  //  【 zpopulation_E8 1990+1999 】
        static ArrayList<String> CompBirth = new ArrayList<>();  // 【 zpopulation_E8 1990:1999 】
        static ArrayList<String> CompAge = new ArrayList<>();    // 【 zpopulation_E8 23:26 】
        static ArrayList<String> BeginAge = new ArrayList<>();    // 【 zpopulation_E8 60+】
        static ArrayList<String> BeginBirth = new ArrayList<>();    // 【 zpopulation_E8 1990+ 】*/

        for (int i = 0; i < mKeyWordName.size(); i++) {
            String curStr = mKeyWordName.get(i);
            if(!curStr.contains(":") && !curStr.contains("+")){ // 参数是一个单独的数值
                String singleStr = new String(curStr);
                if (!isNumeric(singleStr)) {  // 单独输入的数值
                    System.out.println("用户输入的参数无效( 输入参数中包含字母导致) 程序退出！ 请重新输入正确参数！");
                    showTip();
                    return false;
                }

                int inputInt = Integer.parseInt(singleStr);


                // 输入的年份大于 当前统计年份
                if(inputInt > getEndRecordYear()){
                    System.out.println("抱歉 当前输入的统计年份【"+inputInt + "】年 未包含在记录中! 请重新输入其他参数查询！");
                    showTip();
                    return false;
                }

                // 过滤  150至  1949之间的数值
                if(inputInt > 150 && inputInt < 1949 ){
                    System.out.println("抱歉 当前输入的岁数或年份【"+inputInt + "】未包含在记录中! 请重新输入其他参数查询！");
                    showTip();
                    return false;
                }

                int age =  getAgeFromInput(inputInt);
                int birthyear =  getBirthYearFromInput(inputInt);
                boolean isYear = isCommonYear(inputInt);
                String mTitle = "";
                if(isYear){
                    mTitle = inputInt+"年统计数据";
                }else {
                    mTitle = inputInt+"岁统计数据";
                }
                SingleAgeAndBirthYear.add(new AgeAndBirthSingleItem(age,birthyear,mTitle))  ;

            }else{  // 包含:  或者+
                int value1 = 0 ;
                int value2 = 0 ;
                if(curStr.contains(":")){
                    String singleStr = new String(curStr).toLowerCase().trim();  // 100:1992
                    String preStr = singleStr.substring(0,singleStr.indexOf(":"));
                    String endStr = singleStr.substring(singleStr.indexOf(":")+1);
                    if(!isNumeric(preStr)){
                        System.out.println("用户输入的参数无效( 输入参数中包含字母【" + clearNumber(curStr) + "】导致) 程序退出！ 请重新输入正确参数！");
                        showTip();
                        return false;
                    }
                    if(!isNumeric(endStr)){
                        System.out.println("用户输入的参数无效( 输入参数中包含字母【" + clearNumber(curStr) + "】导致) 程序退出！ 请重新输入正确参数！");
                        showTip();
                        return false;
                    }

                    int pre = Integer.parseInt(singleStr.substring(0,singleStr.indexOf(":")));
                    int end = Integer.parseInt(singleStr.substring(singleStr.indexOf(":")+1));
                    value1 = pre;
                    value2 = end;

                    if(value1 != 0 && value2 != 0 &&  value1 == value2 ){
                        System.out.println("抱歉 当前用户输入的岁数或年份【"+value1+"+"+value2 + "】两者的数据相同无法处理! 请重新输入其他参数查询！");
                        showTip();
                        return false;
                    }


                }else if(curStr.contains("+")){
                    String singleStr = new String(curStr).toLowerCase().trim();  // 100:1992

                    String preStr = singleStr.substring(0,singleStr.indexOf("+"));
                    String endStr = singleStr.substring(singleStr.indexOf("+")+1);
                    if(!isNumeric(preStr)){
                        System.out.println("用户输入的参数无效( 输入参数中包含字母【" + clearNumber(curStr) + "】导致) 程序退出！ 请重新输入正确参数！");
                        showTip();
                        return false;
                    }
                    if(!isNumeric(endStr)){
                        System.out.println("用户输入的参数无效( 输入参数中包含字母【" + clearNumber(curStr) + "】导致) 程序退出！ 请重新输入正确参数！");
                        showTip();
                        return false;
                    }

                    int pre = Integer.parseInt(singleStr.substring(0,singleStr.indexOf("+")));
                    int end = Integer.parseInt(singleStr.substring(singleStr.indexOf("+")+1));
                    value1 = pre;
                    value2 = end;
                    if(value1 != 0 && value2 != 0 &&  value1 == value2 ){
                        System.out.println("抱歉 当前用户输入的岁数或年份【"+value1+":"+value2 + "】两者的数据相同无法处理! 请重新输入其他参数查询！");
                        showTip();
                        return false;

                    }
                }
                //  读取到  前面两者之间的数据    并且保证 都是 数字
                if(value1 != 0 && value2 != 0 &&  value1 != value2){

                    // 两个 四位数对比    年份的对比
                    if((""+value1).length() == 4 && (""+value2).length() == 4){
                        if(value1 < 1949  || value2 < 1949 ||  value1 > getEndRecordYear() ||  value2 > getEndRecordYear() ){
                            System.out.println("抱歉 当前输入的岁数或年份【"+value1+"-"+value2 + "】未包含在记录中! 请重新输入其他参数查询！");
                            showTip();
                            return false;

                        }

                    }else{    // 岁数对比

                        // 过滤  150至  1949之间的数值
                        if(value1 > 150 && value1 < 1949 ){
                            System.out.println("抱歉 当前输入的岁数或年份【"+value1+"-"+value2 + "】未包含在记录中! 请重新输入其他参数查询！");
                            showTip();
                            return false;
                        }

                        // 过滤  150至  1949之间的数值
                        if(value2 > 150 && value2 < 1949 ){
                            System.out.println("抱歉 当前输入的岁数或年份【"+value1+"-"+value2 + "】未包含在记录中! 请重新输入其他参数查询！");
                            showTip();
                            return false;
                        }
                    }
                }
            }
        }

        return flag;

    }

//    int age =  getAgeFromInput(inputInt);
//    int birthyear =  getBirthYearFromInput(inputInt);
//    boolean isYear = isCommonYear(inputInt);

    public static int getAgeFromInput(int  inputInt){
        int age = inputInt ;
        if(( ""+inputInt).trim().length() == 4 && inputInt > 1949 ){ // 给出的是出生年月日
            age = getCurrentYear() - inputInt;
        }

        return age;
    }

    public static int getBirthYearFromInput(int  inputInt){
        int birth = inputInt ;
        if(( ""+inputInt).trim().length() != 4 && inputInt < 150 ){ // 给出的是岁数
            birth = getCurrentYear() - inputInt;   //  年份减岁数  等于 出生年份
        }

        return birth;
    }

    public static boolean   isCommonYear(int  inputInt){
        boolean isYear = true;
        if(( ""+inputInt).trim().length() != 4 && inputInt < 150 ){ // 给出的是岁数
            isYear = false;
        }

        if(( ""+inputInt).trim().length() == 4 && inputInt > 1949 ){ // 给出的是出生年月日
            isYear = true;
        }
        return isYear;
    }


    public static int getCurrentYear(){
        int currentYear = 0;

        currentYear =  Calendar.getInstance().get(Calendar.YEAR);
        return currentYear;
    }
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


    public static String clearNumber(String str){
        String result = new String(str);
        result = result.replaceAll("0","");
        result = result.replaceAll("1","");
        result = result.replaceAll("2","");
        result = result.replaceAll("3","");
        result = result.replaceAll("4","");
        result = result.replaceAll("5","");
        result = result.replaceAll("6","");
        result = result.replaceAll("7","");
        result = result.replaceAll("8","");
        result = result.replaceAll("9","");
        return result;
    }



    static void showTip() {
        // zpolulation_E8 age:17 age:18 age:19  输出当前年龄为 17岁的人的情况
        // zpolulation_E8 age:23+30   // 记录当前年纪大于等于 23 至 30 岁的人口情况
        // zpolulation_E8 birth:1992 birth:1993 // 输出1992年的出生的人口情况
        // zpolulation_E8 birth:1992+2008  // 输出1992年的出生的人口情况
        ArrayList<String> tipArr = new ArrayList<String>();
//        tipArr.add("依据年龄输出记录:【 zpopulation_E8 age:17 age:18 age:19 】 //当前年龄为 17 18 19岁的人的情况   ");
//        tipArr.add("依据年龄范围输出记录:【 zpopulation_E8 age:30+40 】 //当前年龄为 从30到40年龄的人口情况   ");
//        tipArr.add("依据出生输出记录:【 zpopulation_E8 birth:1992 】 //输出1992年的出生的人口情况   ");
//        tipArr.add("依据出生范围输出记录:【 zpopulation_E8 birth:1990+1999 】 //输出1990年后至1999年出生(90后)的人口情况   ");
//        tipArr.add("依据出生输出对比记录:【 zpopulation_E8 comp:1990+1999 】 //对比 1990和1999年的人口情况   ");
//        tipArr.add("依据年龄输出对比记录:【 zpopulation_E8 comp:23+26 】 //对比当前23和26岁的人口情况 ");
//        tipArr.add("=================================== ");
        tipArr.add("输出当前统计记录:【 zpopulation_E8   】 //打印 1949年 至 "+ getEndRecordYear()+"年 人口统计数据");
        tipArr.add("依据年龄输出记录:【 zpopulation_E8 17 18 19 】 //当前年龄为 17 18 19岁的人的情况   ");
        tipArr.add("依据出生输出记录:【 zpopulation_E8 1992  1993 1994 】 //输出1992年  1993年  1994年 的出生的人口情况   ");
        tipArr.add("依据年龄范围输出记录:【 zpopulation_E8 30+40 】 //当前年龄为 从30到40年龄的人口情况   ");
        tipArr.add("依据出生范围输出记录:【 zpopulation_E8 1990+1999 】 //输出1990年后至1999年出生(90后)的人口情况   ");
        tipArr.add("依据出生输出对比记录:【 zpopulation_E8 1990:1999 】 //对比 1990和1999年的人口情况   ");
        tipArr.add("依据年龄输出对比记录:【 zpopulation_E8 23:26 】 //对比当前23和26岁的人口情况 ");
        tipArr.add("依据年龄范围输出记录:【 zpopulation_E8 60+ 】 //当前年龄为60岁以上(现在为终点)人群统计  数据从1949年开始  ");
        tipArr.add("依据出生范围输出记录:【 zpopulation_E8 1990+ 】 //对从1992年出生到现在人群统计  数据从1949年开始  ");

        ArrayPrint(tipArr, "命令格式提示");
    }

    static void initPureData(int curYear) {
        for (int i = 0; i < popularList.size(); i++) {
            PopulationYear_Item item = popularList.get(i);
            item.calculPrueAndDeadNumData(curYear);
        }
    }


    static void totalInfo() {

        for (int i = 0; i < popularList.size(); i++) {
            PopulationYear_Item item = popularList.get(i);
            ArrayList<String> arrLogStrList = new ArrayList<String>();
            arrLogStrList.add("时间:" + item.year + "年");
            arrLogStrList.add("该年出生人口当前岁数:" + item.curAge + "岁");
            arrLogStrList.add("该年出生人口属相:" + item.mAnimalString );
            arrLogStrList.add("总人口:" + calculWanRen(item.curSumNum));
            arrLogStrList.add("人口性别比:" + item.sexRate);
            arrLogStrList.add("男性人口:" + calculWanRen(item.curManNum));
            arrLogStrList.add("女性人口:" + calculWanRen(item.curWomanNum));
            arrLogStrList.add("总人口男女数量差(男):" + calculWanRen(item.cur_man_woman_distance));
            arrLogStrList.add("出生率:" + item.birthRate + "‰"+"  (千人出生 "+item.birthRate+"人)");
            arrLogStrList.add("死亡率:" + item.deadRate + "‰"+"  (千人死亡 "+item.deadRate+"人)");
            arrLogStrList.add("自然增长率:" + item.addRate + "‰"+"  (千人净增 "+item.addRate+"人)");
            arrLogStrList.add("净增长人数:" + calculWanRen(item.pureNum));
            arrLogStrList.add("净增长人数(男):" + calculWanRen(item.pureManNum));
            arrLogStrList.add("净增长人数(女):" + calculWanRen(item.pureWomanNum));
            arrLogStrList.add("出生人口:" + calculWanRen(item.birthnum));
            arrLogStrList.add("出生性别比:" + item.birthSexRate);
            arrLogStrList.add("出生男女数量差(男):" + calculWanRen(item.birth_man_woman_distance));

            arrLogStrList.add("出生人口(男):" + calculWanRen(item.bitrhManNum));
            arrLogStrList.add("出生人口(女):" + calculWanRen(item.bitrhWomanNum));
            arrLogStrList.add("死亡人口:" + calculWanRen(item.deadnum));
            arrLogStrList.add("死亡人口(男):" + calculWanRen(item.deadMan));
            arrLogStrList.add("死亡人口(女):" + calculWanRen(item.deadWoman));

            ArrayPrint(arrLogStrList, item.year + "人口数据");
            //     System.out.println("time_year   = "+ item.year + "   pure_man="+item.pureManNum + "   pure_woman= "+item.pureWomanNum + (item.pureManNum == 0 ? "": "男女净值比例:"+(double)item.pureManNum/(double)item.pureWomanNum ) );
            //  System.out.println("time_begin = "+ item.year_begin_timestamp);
            //  System.out.println("time_end   = "+ item.year_end_timestamp);

        }

    }


    static String calculWanRen(long peopleNum) {
        String value = "万人";
        long resultValue = (long) peopleNum / 10000;
        value = resultValue + value;

        return value;
    }

    static ArrayList<PopulationYear_Item> popularList = new ArrayList<PopulationYear_Item>();

    // https://baike.baidu.com/item/%E4%B8%AD%E5%9B%BD%E4%BA%BA%E5%8F%A3/4417422?fr=aladdin
    static {
        popularList.add(new PopulationYear_Item("1949年", "54167万", "28145万", "26022万", "1950万", 105.55));
        popularList.add(new PopulationYear_Item("1950年", "55196万", "28669万", "26527万", "2042万", 105.55));
        popularList.add(new PopulationYear_Item("1951年", "56300万", "29231万", "27069万", "2128万", 105.55));
        popularList.add(new PopulationYear_Item("1952年", "57482万", "29833万", "27649万", "2127万", 105.55));
        popularList.add(new PopulationYear_Item("1953年", "58796万", "30468万", "28328万", "2175万", 105.55));
        popularList.add(new PopulationYear_Item("1954年", "60266万", "31242万", "29024万", "2288万", 105.55));
        popularList.add(new PopulationYear_Item("1955年", "61465万", "31809万", "29656万", "2042万", 105.55));
        popularList.add(new PopulationYear_Item("1956年", "62828万", "32536万", "30292万", "2004万", 105.55));
        popularList.add(new PopulationYear_Item("1957年", "64653万", "33469万", "31184万", "2200万", 105.55));
        popularList.add(new PopulationYear_Item("1958年", "65994万", "34195万", "31799万", "1928万", 105.55));
        popularList.add(new PopulationYear_Item("1959年", "67207万", "34890万", "32317万", "1665万", 105.55));
        popularList.add(new PopulationYear_Item("1960年", "66207万", "34283万", "31924万", "1381万", 105.55));
        popularList.add(new PopulationYear_Item("1961年", "65859万", "33880万", "31979万", "1187万", 105.55));
        popularList.add(new PopulationYear_Item("1962年", "67296万", "34517万", "32778万", "2491万", 105.55));
        popularList.add(new PopulationYear_Item("1963年", "69172万", "35533万", "33639万", "3000万", 105.55));
        popularList.add(new PopulationYear_Item("1964年", "70499万", "36142万", "34357万", "2759万", 105.55));
        popularList.add(new PopulationYear_Item("1965年", "72538万", "37128万", "35410万", "2748万", 105.55));
        popularList.add(new PopulationYear_Item("1966年", "74542万", "38189万", "36353万", "2795万", 105.55));
        popularList.add(new PopulationYear_Item("1967年", "76368万", "39115万", "37253万", "2593万", 105.55));
        popularList.add(new PopulationYear_Item("1968年", "78534万", "40226万", "38308万", "2795万", 105.55));
        popularList.add(new PopulationYear_Item("1969年", "80671万", "41289万", "39382万", "2752万", 105.55));
        popularList.add(new PopulationYear_Item("1970年", "82992万", "42686万", "40306万", "2774万", 105.55));
        popularList.add(new PopulationYear_Item("1971年", "85229万", "43819万", "41410万", "2612万", 105.55));
        popularList.add(new PopulationYear_Item("1972年", "87177万", "44813万", "42364万", "2595万", 105.55));
        popularList.add(new PopulationYear_Item("1973年", "89211万", "45876万", "43335万", "2491万", 105.55));
        popularList.add(new PopulationYear_Item("1974年", "90859万", "46727万", "44132万", "2255万", 105.55));
        popularList.add(new PopulationYear_Item("1975年", "92420万", "47564万", "44856万", "2126万", 105.55));
        // 以上 出生性别比 为模拟
        popularList.add(new PopulationYear_Item("1976年", "93717万", "48257万", "45460万", "1866万", 104));  //  男女比例: https://www.chedong.com/blog/archives/001479.html
        popularList.add(new PopulationYear_Item("1977年", "94974万", "48908万", "46066万", "1798万", 103));   // http://blog.sina.com.cn/s/blog_9f20dd980102wp7x.html
        popularList.add(new PopulationYear_Item("1978年", "96259万", "49567万", "46692万", "1757万", 102));
        popularList.add(new PopulationYear_Item("1979年", "97542万", "50192万", "47350万", "1738万", 102));
        popularList.add(new PopulationYear_Item("1980年", "98705万", "50785万", "47920万", "1797万", 102.61));
        popularList.add(new PopulationYear_Item("1981年", "100072万", "51519万", "48553万", "2092万", 104.07));
        popularList.add(new PopulationYear_Item("1982年", "101654万", "52352万", "49302万", "2265万", 108.47));
        popularList.add(new PopulationYear_Item("1983年", "103008万", "53152万", "49856万", "2080万", 104.97));
        popularList.add(new PopulationYear_Item("1984年", "104357万", "53848万", "50509万", "2077万", 106.33));
        popularList.add(new PopulationYear_Item("1985年", "105851万", "54725万", "51126万", "2227万", 107.81));
        popularList.add(new PopulationYear_Item("1986年", "107507万", "55581万", "51926万", "2411万", 107.68));
        popularList.add(new PopulationYear_Item("1987年", "109300万", "56290万", "53010万", "2550万", 107.55));
        popularList.add(new PopulationYear_Item("1988年", "111026万", "57201万", "53825万", "2307万", 108.33));
        popularList.add(new PopulationYear_Item("1989年", "112704万", "58099万", "54605万", "2432万", 109.01));
        popularList.add(new PopulationYear_Item("1990年", "114333万", "58904万", "55429万", "2391万", 111.14));
        popularList.add(new PopulationYear_Item("1991年", "115823万", "59466万", "56357万", "2258万", 113.48));
        popularList.add(new PopulationYear_Item("1992年", "117171万", "59811万", "57360万", "2119万", 114.61));
        popularList.add(new PopulationYear_Item("1993年", "118517万", "60472万", "58045万", "2126万", 115.21));
        popularList.add(new PopulationYear_Item("1994年", "119850万", "61246万", "58604万", "2104万", 116.59));
        popularList.add(new PopulationYear_Item("1995年", "121121万", "61808万", "59313万", "2063万", 117.77));
        popularList.add(new PopulationYear_Item("1996年", "122389万", "62200万", "60189万", "2067万", 118.52));
        popularList.add(new PopulationYear_Item("1997年", "123626万", "63131万", "60495万", "2038万", 120.44));
        popularList.add(new PopulationYear_Item("1998年", "124761万", "63940万", "60821万", "1991万", 122.07));
        popularList.add(new PopulationYear_Item("1999年", "125786万", "64692万", "61094万", "1909万", 122.65));
        popularList.add(new PopulationYear_Item("2000年", "126743万", "65437万", "61306万", "1771万", 117.86));
        popularList.add(new PopulationYear_Item("2001年", "127627万", "65672万", "61955万", "1702万", 117.20));
        popularList.add(new PopulationYear_Item("2002年", "128453万", "66115万", "62338万", "1647万", 117.6));
        popularList.add(new PopulationYear_Item("2003年", "129227万", "66556万", "62671万", "1599万", 117.9));
        popularList.add(new PopulationYear_Item("2004年", "129988万", "66976万", "63012万", "1593万", 121.18));
        popularList.add(new PopulationYear_Item("2005年", "130756万", "67375万", "63381万", "1617万", 118.59));
        popularList.add(new PopulationYear_Item("2006年", "131448万", "67728万", "63720万", "1584万", 119.25));
        popularList.add(new PopulationYear_Item("2007年", "132129万", "68048万", "64081万", "1594万", 120.22));
        popularList.add(new PopulationYear_Item("2008年", "132802万", "68357万", "64445万", "1608万", 120.56));
        popularList.add(new PopulationYear_Item("2009年", "133450万", "68647万", "64803万", "1615万", 119.45));
        popularList.add(new PopulationYear_Item("2010年", "134091万", "68748万", "65343万", "1588万", 117.94));
        popularList.add(new PopulationYear_Item("2011年", "134735万", "69068万", "65667万", "1604万", 117.78));
        popularList.add(new PopulationYear_Item("2012年", "135404万", "69395万", "66009万", "1635万", 117.70));
        popularList.add(new PopulationYear_Item("2013年", "136072万", "69728万", "66344万", "1640万", 117.60));
        popularList.add(new PopulationYear_Item("2014年", "136782万", "70079万", "66703万", "1687万", 115.88));

        popularList.add(new PopulationYear_Item("2015年", "137462万", "70414万", "67048万", "1655万", 113.51));
        popularList.add(new PopulationYear_Item("2016年", "138271万", "70815万", "67456万", "1786万", 112.88));
        popularList.add(new PopulationYear_Item("2017年", "139008万", "71137万", "67871万", "1723万", 111.9));
        popularList.add(new PopulationYear_Item("2018年", "139538万", "71351万", "68187万", "1523万", 120.00));  // 预估变大 不公布
        //    popularList.add(new PopulationYear_Item("2019年","55196万","28669万","26527万","0万"));

    }

    static PopulationYear_Item getPopulationWithYear(int year_param){
        PopulationYear_Item item = null;
        for (int i = 0; i < popularList.size(); i++) {
            if(popularList.get(i).year == year_param){
                item = popularList.get(i);
                break;
            }
        }
        return item;
    }

    static class PopulationYear_Item {
        int year;   // 年份
        int curAge;  //  当前岁数 28
        int  nowYear;  // 当前年  =2019
        String mAnimalString; //
        long year_begin_timestamp;  // 时间戳开始
        long year_end_timestamp;  // 时间戳结束
        long pureNum; // 年净增长人数   可能是负值
        long pureWomanNum; // 年净增长人数_女性   可能是负值
        long pureManNum; // 年净增长人数_男性   可能是负值
        long birthnum;   // 出生人口数量
        long bitrhWomanNum;  // 出生女性
        long bitrhManNum;  // 出生男性
        long birth_man_woman_distance; // 出生人口  男女差距人数
        double sexRate;  //   男女比例    当前男性/当前女性
        double birthSexRate;  // 出生性别比
        long deadnum;   //  死亡人数 (无法确定死亡人的年龄分布)
        long deadWoman;   //  死亡女性
        long deadMan;     // 死亡男性
        long curSumNum;   //  当前总的人口数量
        long curManNum;    // 女性
        long curWomanNum;    // 男性
        long cur_man_woman_distance; // 总人口  男女差距人数
        double birthRate;    //  出生率
        double deadRate;    //  死亡率
        double addRate;    // 自然增长率

//        人口出生率－人口死亡率=人口自然增长率 　
//        (出生人口—死亡人口）/总人口=人口自然增长率 　　单位一般为‰


        PopulationYear_Item(int mYear, long mCurSum, long mCurManNum, long mCurWomanNum, long mBirthnum) {
            this.year = mYear;
            this.curSumNum = mCurSum;
            this.curManNum = mCurManNum;
            this.curWomanNum = mCurWomanNum;
            this.birthnum = mBirthnum;
            this.year_begin_timestamp = getMonthBegin("" + year + "-01");
            this.year_end_timestamp = getMonthBegin("" + year + "-12");
        }


        // 单位  万人
        PopulationYear_Item(String mYear, String mCurSum, String mCurManNum, String mCurWomanNum, String mBirthnum) {
            mYear = mYear.replace("年", "");
            mCurManNum = mCurManNum.replace("万", "");
            mCurSum = mCurSum.replace("万", "");
            mCurWomanNum = mCurWomanNum.replace("万", "");
            mBirthnum = mBirthnum.replace("万", "");
            int syear = Integer.parseInt(mYear.trim());
            long sSumNum = Long.parseLong(mCurSum.trim()) * 10000;
            long sCurManNum = Long.parseLong(mCurManNum.trim()) * 10000;
            long sCurWomanNum = Long.parseLong(mCurWomanNum.trim()) * 10000;
            long sBirthnum = Long.parseLong(mBirthnum.trim()) * 10000;

            this.year = syear;
            this.curSumNum = sSumNum;
            this.curManNum = sCurManNum;
            this.curWomanNum = sCurWomanNum;
            this.cur_man_woman_distance = this.curManNum - this.curWomanNum;
            this.birthnum = sBirthnum;
            this.year_begin_timestamp = getMonthBegin("" + year + "-01");
            this.year_end_timestamp = getMonthEnd("" + year + "-12");
            sexRate = Double.parseDouble(nf_6.format((Double) ((double) ((double) this.curManNum / (double) this.curWomanNum))));
            birthSexRate = 0;
        }

        PopulationYear_Item(String mYear, String mCurSum, String mCurManNum, String mCurWomanNum, String mBirthnum, double mbirthSexRate) {
            mYear = mYear.replace("年", "");
            mCurManNum = mCurManNum.replace("万", "");
            mCurSum = mCurSum.replace("万", "");
            mCurWomanNum = mCurWomanNum.replace("万", "");
            mBirthnum = mBirthnum.replace("万", "");
            int syear = Integer.parseInt(mYear.trim());
            long sSumNum = Long.parseLong(mCurSum.trim()) * 10000;
            long sCurManNum = Long.parseLong(mCurManNum.trim()) * 10000;
            long sCurWomanNum = Long.parseLong(mCurWomanNum.trim()) * 10000;
            long sBirthnum = Long.parseLong(mBirthnum.trim()) * 10000;

            this.year = syear;
            this.mAnimalString = calZodiac(this.year);
            this.curSumNum = sSumNum;
            this.curManNum = sCurManNum;
            this.curWomanNum = sCurWomanNum;
            this.cur_man_woman_distance = this.curManNum - this.curWomanNum;
            this.birthnum = sBirthnum;
            this.year_begin_timestamp = getMonthBegin("" + year + "-01");
            this.year_end_timestamp = getMonthEnd("" + year + "-12");
            // sexRate = this.curManNum/this.curWomanNum;

            sexRate = Double.parseDouble(nf_6.format((Double) ((double) ((double) this.curManNum / (double) this.curWomanNum))));

            this.birthSexRate = mbirthSexRate;

        }


        public static String calZodiac(int year) {
            String zodiac = "";
            int remainder = year % 12;

            switch (remainder) {//运用switch...case
                case 0:
                    zodiac = "猴";//monkey
                    break;
                case 1:
                    zodiac = "鸡";
                    break;
                case 2:
                    zodiac = "狗";//2018年属于狗年
                    break;
                case 3:
                    zodiac = "猪";
                    break;
                case 4:
                    zodiac = "鼠";
                    break;
                case 5:
                    zodiac = "牛";
                    break;
                case 6:
                    zodiac = "虎";//tiger
                    break;
                case 7:
                    zodiac = "兔";//rabbit
                    break;
                case 8:
                    zodiac = "龙";//dragon
                    break;
                case 9:
                    zodiac = "蛇";//snake
                    break;
                case 10:
                    zodiac = "马";
                    break;
                case 11:
                    zodiac = "羊";
                    break;
            }
            return zodiac;
        }

        //  计算净人口数据 死亡人口数据 1949年的净值人口  等于 1949年的总人口 - 1948年的总人口
        void calculPrueAndDeadNumData(int curYear) {
            this.curAge = curYear - this.year;
            this.nowYear = this.year;
            long preYearNum = getYearNum(this.year - 1);
            long preYearManNum = getYearManNum(this.year - 1);
            long preYearWomanNum = getYearWomanNum(this.year - 1);

            if (preYearNum == 0 || preYearManNum == 0 || preYearWomanNum == 0) {
                this.pureNum = 0;
                this.pureManNum = 0;
                this.pureWomanNum = 0;
                return;
            }

            // 净增值人口  等于 1949年的总人口 - 1948年的总人口
            this.pureNum = this.curSumNum - preYearNum;
            //净增值男人口  等于 1949年的男人口 - 1948年的男人口
            this.pureManNum = this.curManNum - preYearManNum;
            this.pureWomanNum = this.curWomanNum - preYearWomanNum;
            // 死亡人口 = 出生人口 - 净值人口    (男女死亡人口   男女净增加人口   男女一共增加的人口  男女出生的人口)
            this.deadnum = this.birthnum - this.pureNum;

//          double birthRate;    //  出生率
//          double deadRate;    //  死亡率
//          double addRate;    // 自然增长率
//        人口出生率－人口死亡率=人口自然增长率 　
//        (出生人口—死亡人口）/总人口=人口自然增长率 　　单位一般为‰

//          Double.parseDouble(nf_6.format((Double) ( (double)(this.birthnum / this.curSumNum))));
//          Double.parseDouble(nf_6.format((Double) ( (double)(this.deadnum / this.curSumNum))));
//          Double.parseDouble(nf_6.format((Double) ( (double)(this.addRate / this.curSumNum))));

//          this.birthRate = (double)(this.birthnum / this.curSumNum); // 出生率
//          this.deadRate = (double)(this.deadnum / this.curSumNum); // 死亡率
//          this.addRate = (double)(this.pureNum / this.curSumNum); // addRate自然增长率 出生率

            this.birthRate = Double.parseDouble(nf_6.format((Double) ((double) ((double) this.birthnum / (double) this.curSumNum)))) * 1000;
            this.deadRate = Double.parseDouble(nf_6.format((Double) ((double) ((double) this.deadnum / (double) this.curSumNum)))) * 1000;
            this.addRate = Double.parseDouble(nf_6.format((Double) ((double) ((double) this.pureNum / (double) this.curSumNum)))) * 1000;
            this.birthRate = Double.parseDouble(nf_2.format(this.birthRate));
            this.deadRate = Double.parseDouble(nf_2.format(this.deadRate));
            this.addRate = Double.parseDouble(nf_2.format(this.addRate));


            if (this.birthSexRate != 0) {
                double allRate = this.birthSexRate + 100;
                this.bitrhManNum = (long) ((this.birthnum / allRate) * this.birthSexRate);
                this.bitrhWomanNum = (long) ((this.birthnum / allRate) * 100);
                this.deadMan = this.bitrhManNum - this.pureManNum;
                this.deadWoman = this.bitrhWomanNum - this.pureWomanNum;
                this.birth_man_woman_distance = this.bitrhManNum - this.bitrhWomanNum;
            }
        }


        long getYearNum(int year_param) {
            long resultNum = 0;
            for (int i = 0; i < popularList.size(); i++) {
                PopulationYear_Item item = popularList.get(i);
                if (item.year == year_param) {
                    resultNum = item.curSumNum;
                    break;
                }
            }
            return resultNum;
        }


        long getYearManNum(int year_param) {
            long resultNum = 0;
            for (int i = 0; i < popularList.size(); i++) {
                PopulationYear_Item item = popularList.get(i);
                if (item.year == year_param) {
                    resultNum = item.curManNum;
                    break;
                }
            }
            return resultNum;
        }


        long getYearWomanNum(int year_param) {
            long resultNum = 0;
            for (int i = 0; i < popularList.size(); i++) {
                PopulationYear_Item item = popularList.get(i);
                if (item.year == year_param) {
                    resultNum = item.curWomanNum;
                    break;
                }
            }
            return resultNum;
        }
    }


    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getMonthBegin(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date time = null;
        try {
            time = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(time);

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        //  System.out.println("Begin  年:"+c.get(Calendar.YEAR)+" 月:"+c.get(Calendar.MONTH)+" 日:"+c.get(Calendar.DAY_OF_MONTH)+ "  时:"+c.get(Calendar.HOUR_OF_DAY)+" 分:"+c.get(Calendar.MINUTE) +"  秒:"+c.get(Calendar.SECOND));
        //   System.out.println("Begin  "+c.get(Calendar.YEAR)+"-"+getTimeDoubleString(c.get(Calendar.MONTH)+1)+"-"+getTimeDoubleString(c.get(Calendar.DAY_OF_MONTH))+ " "+getTimeDoubleString(c.get(Calendar.HOUR_OF_DAY))+":"+getTimeDoubleString(c.get(Calendar.MINUTE)) +":"+getTimeDoubleString(c.get(Calendar.SECOND)));

        return c.getTimeInMillis();
    }

    static String getTimeDoubleString(int time) {
        String ctime = (time + "").trim();
        if (ctime.length() == 1) {
            ctime = "0" + ctime;
        }
        return ctime;
    }


    static String getTimeDoubleString(String time) {
        String ctime = time.trim();
        if (ctime.length() == 1) {
            ctime = "0" + ctime;
        }
        return ctime;
    }

    /**
     * 获取指定日期所在月份结束的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getMonthEnd(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date time = null;
        try {
            time = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(time);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        //   631180800001     将毫秒至999  绝对值小  这个是最后的时间戳
        //   631180801000    将毫秒至0    绝对值大

        //  System.out.println("End  年:"+c.get(Calendar.YEAR)+" 月:"+c.get(Calendar.MONTH)+" 日:"+c.get(Calendar.DAY_OF_MONTH)+ "  时:"+c.get(Calendar.HOUR_OF_DAY)+" 分:"+c.get(Calendar.MINUTE) +"  秒:"+c.get(Calendar.SECOND));
        // System.out.println("End  "+c.get(Calendar.YEAR)+":"+c.get(Calendar.MONTH)+":"+c.get(Calendar.DAY_OF_MONTH)+ " "+c.get(Calendar.HOUR_OF_DAY)+"-"+c.get(Calendar.MINUTE) +"-"+c.get(Calendar.SECOND));
        //   System.out.println("End  "+c.get(Calendar.YEAR)+"-"+getTimeDoubleString(c.get(Calendar.MONTH)+1)+"-"+getTimeDoubleString(c.get(Calendar.DAY_OF_MONTH))+ " "+getTimeDoubleString(c.get(Calendar.HOUR_OF_DAY))+":"+getTimeDoubleString(c.get(Calendar.MINUTE)) +":"+getTimeDoubleString(c.get(Calendar.SECOND)));

        return c.getTimeInMillis();
    }


    // ArrayPrint ==============================Begin
    static public void printArrObject(Object[] objArr, String title) {
        ArrayList<String> curPropStrArr = new ArrayList<String>();
        for (int i = 0; i < objArr.length; i++) {
            if ("".equals(objArr[i].toString())) {
                continue;
            }
            curPropStrArr.add(objArr[i].toString());
        }
        ArrayPrint(curPropStrArr, title);
    }


    static int MAX_COUNT_CHAR_IN_ROW = 120;
    static int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 120;

    public static boolean isItemLengthOver100(ArrayList<String> mStrList) {
        boolean flag = false;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > MAX_COUNT_CHAR_IN_ROW) {
                //   System.out.println("index["+i+"]  size= "+mStrList.get(i).length()+"     Value:" + mStrList.get(i) );
                return true;
            }
        }
        return flag;

    }


    public static ArrayList<String> makeStringGroup(String code, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        String oriStr = code.trim();
        while (oriStr.length() > maxcount) {
            String str1 = oriStr.substring(0, maxcount);
            fixArr.add(str1);
            oriStr = oriStr.substring(maxcount);
        }


        return fixArr;
    }


    public static ArrayList<String> sqlitString(String bigString, String sqlitChar) {
        ArrayList<String> fixArr = new ArrayList<String>();
        ArrayList<String> subArr = new ArrayList<String>();
        String[] strArr = bigString.trim().split(sqlitChar.trim());
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].length() > MAX_COUNT_CHAR_IN_ROW) {
                ArrayList<String> subArrA = null;
                if (strArr[i].contains(";")) {
                    subArrA = sqlitString(strArr[i], ";");

                } else if (strArr[i].contains("。")) {
                    subArrA = sqlitString(strArr[i], "。");

                } else if (strArr[i].contains(":")) {
                    subArrA = sqlitString(strArr[i], ":");
                } else if (strArr[i].contains(".")) {
                    subArrA = sqlitString(strArr[i], ".");
                } else if (strArr[i].contains(" ")) {
                    subArrA = sqlitString(strArr[i], " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(strArr[i], MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixArr.add(tempArr.get(j));
                    }

                }

                if (subArrA != null && isItemLengthOver100(subArrA)) {
                    String fixSub = strArr[i].substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixArr.add(fixSub);
                } else {
                    if (subArrA != null) {
                        for (int j = 0; j < subArrA.size(); j++) {
                            fixArr.add(subArrA.get(j));
                        }

                    }
                }

            } else {
                fixArr.add(strArr[i]);
            }
        }
        return fixArr;
    }

    public static ArrayList<String> toMakeListItemLess100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixLengthArr = new ArrayList<String>();

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() < MAX_COUNT_CHAR_IN_ROW) {
                fixLengthArr.add(mStrList.get(i));
            } else {
                String curMaxStr = mStrList.get(i);
                ArrayList<String> fixA = null;
                if (curMaxStr.contains(";")) {
                    fixA = sqlitString(curMaxStr, ";");
                } else if (curMaxStr.contains("。")) {
                    fixA = sqlitString(curMaxStr, "。");
                } else if (curMaxStr.contains(":")) {
                    fixA = sqlitString(curMaxStr, ":");
                } else if (curMaxStr.contains(".")) {
                    fixA = sqlitString(curMaxStr, ".");
                } else if (curMaxStr.contains(" ")) {
                    fixA = sqlitString(curMaxStr, " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(curMaxStr, MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixLengthArr.add(tempArr.get(j));
                    }
                }

                if (fixA != null) {
                    //   System.out.println(" fixA.size="+ fixA.size());
                    for (int j = 0; j < fixA.size(); j++) {
                        // System.out.println(" fixA.size="+ fixA.size() + " i="+j+"   value:"+fixA.get(j));
                    }
                } else {
                    //  System.out.println(" fixA.size= null!");
                }
                if (isItemLengthOver100(fixA)) {
                    String fixSub = curMaxStr.substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixLengthArr.add(fixSub);
                } else {
                    if (fixA != null) {
                        for (int j = 0; j < fixA.size(); j++) {
                            fixLengthArr.add(fixA.get(j));
                        }
                    }
                }


            }
        }

        return fixLengthArr;
    }


    public static int getItemMaxLength(ArrayList<String> mStrList) {
        int itemLength = 0;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > itemLength) {
                itemLength = mStrList.get(i).length();
            }

        }
        return itemLength;
    }

    public static ArrayList<String> fixStrArrMethodCommon100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curStr = mStrList.get(i);
            String fixCurStr = getFixLengthNewStr(curStr, maxcount);
            fixArr.add(fixCurStr);
        }

        return fixArr;
    }


    public static String getFixLengthNewStr(String oriStr, int maxLength) {
        String fixStr = "";
        String beginChar = "║ ";
        String endChar = "║";
        String oriStrTrim = oriStr.trim();
        int curLength = oriStrTrim.length();
        int paddingLength = maxLength - curLength;


        int chineseCount = getChineseCount(oriStr);
        paddingLength = paddingLength - chineseCount;
        if (paddingLength < 0) {
            // return "curString:" + oriStr + "  length more than" + maxLength;
            return "";
        }

        for (int i = 0; i < paddingLength; i++) {
            oriStrTrim += " ";
        }
        oriStrTrim = beginChar + oriStrTrim + endChar;
        //  oriStrTrim = beginChar + oriStrTrim ;
        fixStr = oriStrTrim;
        return fixStr;
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

    public static void showTableLogCommon100(ArrayList<String> mStrList, String title) {
        int maxLength = getItemMaxLength(mStrList);
        ArrayList<String> fixStrArr = fixStrArrMethodCommon100(mStrList, MAX_COUNT_CHAR_IN_ROW);
        int chineseCount = getChineseCount(title);


        String beginRow = "╔════════════════════════════════════════════════" + title + "═════════════════════════════════════════════════════╗";
        String endRow = "╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝";
        int fixLength = 0;
        int oriLength = title.length();
        if (chineseCount == 0) { // 不包含汉字
            fixLength = oriLength;

        } else {
            if (chineseCount == oriLength) { // 全部包含汉字
                fixLength = 2 * oriLength;
            } else { // 一部分汉字  一部分英语

                fixLength = oriLength - chineseCount + (2 * chineseCount);
            }

        }
        String templateString = "╗";
        if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                templateString = "═" + templateString;
            }
        }

        beginRow = beginRow.replace(templateString, "╗");
        //  System.out.println(" fixStrArr.size() =" + fixStrArr.size());
        beginRow = resetBeginRowToDefaultSize(beginRow);
        System.out.println(beginRow);
        for (int i = 0; i < fixStrArr.size(); i++) {
            System.out.println(fixStrArr.get(i));
        }
        endRow = resetEndRowToDefaultSize(endRow);
        System.out.println(endRow);
    }

    static String resetEndRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getPaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═", distance + 3);
        curBeginStr = curBeginStr.replace("╝", paddingString + "╝");
        return curBeginStr;
    }

    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    static String getRepeatString(String repeatSrc, int repeatCount) {
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }


    static String resetBeginRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getPaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═", distance + 3);
        curBeginStr = curBeginStr.replace("╗", paddingString + "╗");
        return curBeginStr;
    }

    public static void ArrayPrint(ArrayList<String> mStrList, String title) {

        ArrayList<String> addMao = CheckAndAddMaoMethod(mStrList);
        // 对mStrList 进行 对其处理  重新转换为 对其的  ArrayList<String> new
        // 1. 判断所有字符串中 第一次出现冒号的位置   查找出最大的位置的那个 并 记录这个最大位置 xMaxLengh
        // 2.  重新排序的规则是  小字符串需要在: 之后添加  xMaxLengh - self().length 的空格 并重新加入新的数组
        ArrayList<String> firstFixedStringArrA = firstFixedStringArr(addMao);
        boolean isOver100 = isItemLengthOver100(firstFixedStringArrA);

        if (isOver100) {
            //     System.out.println("当前的字符串Item 存在大于 100字符的！");
            ArrayList<String> newLessList = toMakeListItemLess100(firstFixedStringArrA, MAX_COUNT_CHAR_IN_ROW);
            showTableLogCommon100(newLessList, title);  //  每一行都小于100个字的打印
        } else { //
            //   System.out.println("当前的字符串Item 不 存在大于 100字符的！");
            showTableLogCommon100(firstFixedStringArrA, title);  //  每一行都小于100个字的打印


        }
    }

    public static String getPaddingEmptyString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += "-";
        }
        return str;
    }

    // 加载库时搜索的路径列表AC-:\Program Files\Java\jdk1.8.0_191\bin
    // 加载库时搜索的路径列表A-:C\Program Files\Java\jdk1.8.0_191\bin
    public static String addMaoChinese(String oriStr) {
        String resultStr = "";
        int chinesePosition = getFirstChinesePosition(oriStr);
        resultStr = oriStr.substring(0, chinesePosition) + ":" + oriStr.substring(chinesePosition);
        return resultStr;
    }


    public static int getFirstChinesePosition(String str) {
        int position = 0;
        boolean getFirstChinese = false;
        char[] newChar = str.toCharArray();  //转为单个字符
        for (int i = 0; i < newChar.length; i++) {
            char curChar = newChar[i];
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(curChar + "");
            if (m.find()) {
                getFirstChinese = true;
                position = i;
            } else {
                if (getFirstChinese == true) {
                    return i;
                }
            }

        }
        return position;
    }

    public static String addMaoBlank(String oriStr) {
        String resultStr = "";
        int blankPosition = oriStr.indexOf(" ");
        resultStr = oriStr.substring(0, blankPosition) + ":" + oriStr.substring(blankPosition);
        return resultStr;
    }

    public static ArrayList<String> CheckAndAddMaoMethod(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            if (isCommonMao(curItem)) {
                fixedArr.add(curItem);
            } else {
                // 对于那些没有冒号的  字段的处理
                //1.如果包含汉字 那么就在 汉字的最后添加一个冒号
                // 2. 如果字符串中有空格 并且第一个空格的位置小于 (MAX_COUNT_CHAR_IN_ROW/2) 那么把它换成冒号
                if (isContainChinese(curItem)) {
                    String addMaoStr = addMaoChinese(curItem);
                    fixedArr.add(addMaoStr);
                } else if (curItem.contains(" ") && curItem.indexOf(" ") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
                    String addMaoStr = addMaoBlank(curItem);
                    fixedArr.add(addMaoStr);
                } else {  // 如果以上条件都不满足   那么就在字符串最前面添加一个冒号

                    fixedArr.add(":" + curItem);

                }

            }

        }
        return fixedArr;
    }


    // 存在冒号 并且 冒号的位置小于 总的一行字数的一半长度  返回true
    public static boolean isCommonMao(String oriStr) {
        boolean flag = false;
        if (oriStr.contains(":") && oriStr.indexOf(":") == oriStr.lastIndexOf(":")) {
            flag = true;  // 只有一个冒号
        } else if (oriStr.contains(":") && oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.indexOf(":\\") && oriStr.indexOf(":") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
            flag = true; // 多个冒号 并且  第一个冒号  :   在 :\ 之前
        } else if (oriStr.contains(":") && !oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.lastIndexOf(":")) {
            flag = true;   // 多个冒号
        }
        return flag;
    }

    public static ArrayList<String> firstFixedStringArr(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        int maxMaoPosition = getMaxMaoPosition(mStrList);
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            int curMaoPosition = curItem.indexOf(":");
            String pre = curItem.substring(0, curMaoPosition);
            String end = curItem.substring(curMaoPosition); // 去掉:
            int preLength = getPaddingChineseLength(pre); // 中文对齐  取中文长度
            // 需要达到一样的ChineseLength
            // int padding_chinese =  getChineseCount(pre);
            String padding = "";
            if (preLength <= maxMaoPosition) {
                int paddingLength = maxMaoPosition - preLength;
                padding = getPaddingEmptyString(paddingLength);
            }
            String fixedItem = pre + padding + end;
            fixedArr.add(fixedItem);


        }
        return fixedArr;
    }

    public static int getMaxMaoPosition(ArrayList<String> mStrList) {
        int maoPosition = 0;
        String maxString = "";
        for (int i = 0; i < mStrList.size(); i++) {
            if ((mStrList.get(i).contains(":"))) {
                int curMaoPosition = mStrList.get(i).indexOf(":");
                String maoString = mStrList.get(i).substring(0, curMaoPosition + 1);
                int paddingSize = getPaddingChineseLength(maoString);
                if (paddingSize > maoPosition) {
                    maoPosition = paddingSize;
                    maxString = mStrList.get(i);
                }
            }

        }
        //  System.out.println("最长的冒号位置: maoPosition="+maoPosition+"   string="+maxString);
        return maoPosition;
    }
    // ArrayPrint ==============================End

}
