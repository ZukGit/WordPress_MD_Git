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


        getDetailInput();
        calculInfoMation();


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









    static ArrayList<String> SingleAge = new ArrayList<>();
    static String AgeTag = "age:";
    static ArrayList<String> RankAge = new ArrayList<>();
    static ArrayList<String> SingleBirth = new ArrayList<>();
    static String BirthTag = "birth:";
    static ArrayList<String> RankBirth = new ArrayList<>();

    static ArrayList<String> CompBirth = new ArrayList<>();
    static ArrayList<String> CompAge = new ArrayList<>();

    static void calculInfoMation() {
        toCalculSingleAge(SingleAge);


    }
    static void toCalculSingleAge( ArrayList<String> singleAgeList) {
        // age:17 age:18 age:19
        for (int i = 0; i < singleAgeList.size(); i++) {
            String rawInputStr  = singleAgeList.get(i);
            int ageIndex = rawInputStr.indexOf(AgeTag);
            int ageLength =AgeTag.length();
            String ageStr = rawInputStr.substring(ageIndex+ageLength);
            int singleAge = Integer.parseInt(ageStr);
            int curBirthYear = currentYear - singleAge;
            int defaultCompBirthYear = getEndRecordYear();
            int defaultYear = currentYear - defaultCompBirthYear;
            showSingleAge(singleAge,curBirthYear,defaultYear,defaultCompBirthYear,true);
        }

    }

    static void showSingleAge( int ageA ,int birthA ,int ageB,int birthB , boolean inputAge) {
        ArrayList<String> singleAgeList = new  ArrayList<String>();

        PopulationYear_Item itemA = getPopulationWithYear(birthA);
        PopulationYear_Item itemB = getPopulationWithYear(birthB);
        if(itemA == null){
            System.out.println("无法查询到 年龄("+ageA+")--出生年份("+birthA+") 的人口数据!");
            return ;
        }
        if(itemB == null){
            System.out.println("无法查询到 年龄("+ageB+")--出生年份("+birthB+") 的人口数据!");
            return ;
        }
        boolean isSingle = (birthB == getEndRecordYear());

        singleAgeList.add("当前查询年龄:"+ageA+"岁");
        singleAgeList.add("出生年份:"+birthA+"年");
        singleAgeList.add("属相:"+itemA.mAnimalString);
        singleAgeList.add("当前("+birthA+"年)总人口:" + calculWanRen(itemA.curSumNum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"总人口:"+calculWanRen(getEndRecordYear_PeopleCount()));
        singleAgeList.add("差距总人口数:("+getEndRecordYear()+"-"+birthA+")"+ calculWanRen(getEndRecordYear_PeopleCount() -itemA.curSumNum ));
        singleAgeList.add("当前("+birthA+"年)出生人数:"+calculWanRen(itemA.birthnum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"出生人数:"+calculWanRen(getEndRecordYear_BirthNum()));

        singleAgeList.add("当前("+birthA+"年)出生率:" + itemA.birthRate + "‰"+"  (千人出生 "+itemA.birthRate+"人)");
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"出生率:"+ itemB.birthRate + "‰"+"  (千人出生 "+itemB.birthRate+"人)");

        singleAgeList.add("当前("+birthA+"年)出生人数(男):"+calculWanRen(itemA.bitrhManNum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"出生人数(男):"+calculWanRen(itemB.bitrhManNum));
        singleAgeList.add("当前("+birthA+"年)出生人数(女):"+calculWanRen(itemA.bitrhWomanNum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"出生人数(女):"+calculWanRen(itemB.bitrhWomanNum));

        singleAgeList.add("当前("+birthA+"年)人口性别比:" + itemA.sexRate);
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"当前("+birthA+"年)人口性别比:"+itemB.sexRate);

        singleAgeList.add("当前("+birthA+"年)男性人口:" + calculWanRen(itemA.curManNum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"男性人口:"+calculWanRen(itemB.curManNum));

        singleAgeList.add("当前("+birthA+"年)女性人口:" + calculWanRen(itemA.curWomanNum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"女性人口:"+calculWanRen(itemB.curWomanNum));

        singleAgeList.add("当前("+birthA+"年)总人口男女数量差(男):" + calculWanRen(itemA.cur_man_woman_distance));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"当前("+birthA+"年)总人口男女数量差(男):"+calculWanRen(itemB.cur_man_woman_distance));

        singleAgeList.add("当前("+birthA+"年)出生率:" + itemA.birthRate + "‰"+"  (千人出生 "+itemA.birthRate+"人)");
        singleAgeList.add("当前("+birthA+"年)死亡率:" + itemA.deadRate + "‰"+"  (千人死亡 "+itemA.deadRate+"人)");
        singleAgeList.add("当前("+birthA+"年)自然增长率:" + itemA.addRate + "‰"+"  (千人净增 "+itemA.addRate+"人)");

        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"出生率:" + itemB.birthRate + "‰"+"  (千人出生 "+itemB.birthRate+"人)");
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"死亡率:"  + itemB.deadRate + "‰"+"  (千人死亡 "+itemB.deadRate+"人)");
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"自然增长率:" + itemB.addRate + "‰"+"  (千人净增 "+itemB.addRate+"人)");



        singleAgeList.add("当前("+birthA+"年)净增长人数:" + calculWanRen(itemA.pureNum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"净增长人数:" + calculWanRen(itemB.pureNum));
        singleAgeList.add("当前("+birthA+"年)净增长人数(男):" + calculWanRen(itemA.pureManNum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"净增长人数(男):" + calculWanRen(itemB.pureManNum));
        singleAgeList.add("当前("+birthA+"年)净增长人数(女):" + calculWanRen(itemA.pureWomanNum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"净增长人数(女):" + calculWanRen(itemB.pureWomanNum));

        singleAgeList.add("当前("+birthA+"年)死亡人口:" + calculWanRen(itemA.deadnum));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"当前("+birthA+"年)死亡人口:" + calculWanRen(itemB.deadnum));
        singleAgeList.add("当前("+birthA+"年)死亡人口(男):" + calculWanRen(itemA.deadMan));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"当前("+birthA+"年)死亡人口(男):" + calculWanRen(itemB.deadMan));
        singleAgeList.add("当前("+birthA+"年)死亡人口(女):" + calculWanRen(itemA.deadWoman));
        singleAgeList.add("现在已记录("+getEndRecordYear()+"年)"+"当前("+birthA+"年)死亡人口(女):" + calculWanRen(itemB.deadWoman));

        ArrayPrint(singleAgeList,inputAge?"age:"+ageA+"岁人口情况":"birth:"+birthA+"年人口情况");
    }


    static void getDetailInput() {

        for (int i = 0; i < mKeyWordName.size(); i++) {
            String curStr = mKeyWordName.get(i);

            if (curStr.contains("age:") && !curStr.contains("+")) {
                SingleAge.add(curStr);
            } else if (curStr.contains("age:") && curStr.contains("+")) {
                RankAge.add(curStr);

            } else if (curStr.contains("birth:") && !curStr.contains("+")) {
                SingleBirth.add(curStr);
            } else if (curStr.contains("birth:") && curStr.contains("+")) {
                RankBirth.add(curStr);
            }else if(curStr.contains("comp:") && curStr.contains("+")){
                String temp = curStr.trim().replace("comp:","").trim();
                int addIndex = temp.indexOf("+");
                String firstDigitalStr = temp.substring(0,addIndex).trim();
                boolean isCompareAge = true;  // 对比年纪
                if(firstDigitalStr.length() >= 4){
                    isCompareAge = false;  // 对比出生日期
                }
                if(isCompareAge){
                     CompAge.add(curStr);   // 对比 年龄
                }else{
                    CompBirth.add(curStr);  // 对比出生 年份
                }



            }
        }
    }

    static void showTip() {
        // zpolulation_E8 age:17 age:18 age:19  输出当前年龄为 17岁的人的情况
        // zpolulation_E8 age:23+30   // 记录当前年纪大于等于 23 至 30 岁的人口情况
        // zpolulation_E8 birth:1992 birth:1993 // 输出1992年的出生的人口情况
        // zpolulation_E8 birth:1992+2008  // 输出1992年的出生的人口情况
        ArrayList<String> tipArr = new ArrayList<String>();
        tipArr.add("依据年龄输出记录:【 zpolulation_E8 age:17 age:18 age:19 】 //当前年龄为 17 18 19岁的人的情况   ");
        tipArr.add("依据年龄范围输出记录:【 zpolulation_E8 age:30+40 】 //当前年龄为 从30到40年龄的人口情况   ");
        tipArr.add("依据出生输出记录:【 zpolulation_E8 birth:1992 】 //输出1992年的出生的人口情况   ");
        tipArr.add("依据出生范围输出记录:【 zpolulation_E8 birth:1990+1999 】 //输出1990年后至1999年出生(90后)的人口情况   ");
        tipArr.add("依据出生输出对比记录:【 zpolulation_E8 comp:1990+1999 】 //对比 1990和1999年的人口情况   ");
        tipArr.add("依据年龄输出对比记录:【 zpolulation_E8 comp:23+26 】 //对比当前23和26岁的人口情况 ");
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
