package com.and.zmain_life.stock_node;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.StockHolder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

 */
public class Stock_NodeImpl implements Stock_Node, Serializable {

//    private static final long serialVersionUID = 1L;

    public int color_int;
    public long id;

    public int count;
    public List<Stock_NodeImpl> children = new ArrayList<Stock_NodeImpl>();

    public int level;


    private transient long selectedChildId;

    public long parentid;
    static public final transient Stock_NodeImpl EMPTY = new Stock_NodeImpl(0, -1, "ALL", 0);


    public String area;     // 地域  上海 广东  北京   //  https://tushare.pro/document/2?doc_id=25
    public String curr_type;
    public String name;    // 股票简称  一般四个字
    public String enname;          // 股票英文名称
    public String exchange;   // SSE--上市   SZSE----深市
    public String fullname;    // 股票全称
    public String industry;   // 所属行业
    public String is_hs;   //  是否沪深港通标的，N否 H沪股通 S深股通
    public String list_date;      //  上市日期   20040312
    public String delist_date;   //  退市日期  20200825
    public String list_status;  //   L--上市    P---退市  D---退市
    public String market;   // 主板   中小板  科创板  创业板
    public String symbol;   //  600004    300321
    public String ts_code;   // 600004.SH   300321.SZ

    public String level3_key;   // 作为 地3 层 显示的 key , ts_code  name  , market  , .....
    public String level3_value;   // level3_key 对应的 值得字符串


    //------------- 需另外读取其他  非股票里列表才能得到的数据 Begin -------------
    public int showindex;
    public float price;    // 读取 其他的 xlsx 文件才能有的信息



    // --------- XinGuLieBiaoIPO IPO_property  IPO 属性


    public String XinGuLieBiaoIPO_cname;

    public String XinGuLieBiaoIPO_issue_date;
    public String XinGuLieBiaoIPO_name;
    public String XinGuLieBiaoIPO_sub_code;
    public String XinGuLieBiaoIPO_ts_code;

    public double XinGuLieBiaoIPO_amount;
    public double XinGuLieBiaoIPO_ballot;
    public double XinGuLieBiaoIPO_pe;
    public double XinGuLieBiaoIPO_price;
    public double XinGuLieBiaoIPO_limit_amount;
    public double XinGuLieBiaoIPO_market_amount;
    public double XinGuLieBiaoIPO_funds;

    public boolean XinGuLieBiaoIPO_isIPO; // 是否是在 IPO中显示的项


    public   double MeiRiZhiBiao_turnover_rate_f;  // 自由股换手率

    public   double RiXianXingQingvShiJianWeiXu_amount;  // 成交额
    public  float  MeiRiZhiBiao_total_mv; // 	总市值 （万元）
    public String XinGuLieBiaoIPO_ipo_date;     // ipo 日期
    public  float  MeiRiZhiBiao_circ_mv;  // 流通市值（万元）



    public   float MeiRiZhiBiao_price;   // 每日指标  价格

    public   double RiXianXingQingvShiJianWeiXu_low;   // 最低价    //  用于计算振幅

    public   double RiXianXingQingvShiJianWeiXu_change;  // 日线行情 涨跌值
    public   double RiXianXingQingvShiJianWeiXu_pct_chg;  // 日线行情 涨跌幅度
     //  日线行情 成交额
    public   double RiXianXingQingvShiJianWeiXu_high;  // 最高价








    public   double RiXianXingQingvShiJianWeiXu_day3_pct_chg;  // 日线行情 涨跌幅度 3天
    public   double RiXianXingQingvShiJianWeiXu_day5_pct_chg;  // 日线行情 涨跌幅度 5天
    public   double RiXianXingQingvShiJianWeiXu_day10_pct_chg;  // 日线行情 涨跌幅度 10天
    public   double RiXianXingQingvShiJianWeiXu_day15_pct_chg;  // 日线行情 涨跌幅度 15天


    public   double RiXianXingQingvShiJianWeiXu_day30_pct_chg;  // 日线行情 涨跌幅度 30天
    public   double RiXianXingQingvShiJianWeiXu_day60_pct_chg;  // 日线行情 涨跌幅度 60天
    public   double RiXianXingQingvShiJianWeiXu_day90_pct_chg;  // 日线行情 涨跌幅度 季度
    public   double RiXianXingQingvShiJianWeiXu_dayyear_pct_chg;  // 日线行情 涨跌幅度 年









    public double getFlowPercent() {
        if(MeiRiZhiBiao_total_mv == 0 ){
            return 0 ;
        }
        return ((MeiRiZhiBiao_circ_mv)/MeiRiZhiBiao_total_mv) * 100;   // 流通市值/总市值 的 流占比
    }


    public double getzhenfu_today() {
        if(price == 0 ){
            return 0 ;
        }
        return ((RiXianXingQingvShiJianWeiXu_high - RiXianXingQingvShiJianWeiXu_low)/price) * 100;
    }


    public double getRiXianXingQingvShiJianWeiXu_day30_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day30_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day30_pct_chg(double riXianXingQingvShiJianWeiXu_day30_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day30_pct_chg = riXianXingQingvShiJianWeiXu_day30_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day60_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day60_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day60_pct_chg(double riXianXingQingvShiJianWeiXu_day60_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day60_pct_chg = riXianXingQingvShiJianWeiXu_day60_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day90_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day90_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day90_pct_chg(double riXianXingQingvShiJianWeiXu_day90_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day90_pct_chg = riXianXingQingvShiJianWeiXu_day90_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_dayyear_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_dayyear_pct_chg(double riXianXingQingvShiJianWeiXu_dayyear_pct_chg) {
        RiXianXingQingvShiJianWeiXu_dayyear_pct_chg = riXianXingQingvShiJianWeiXu_dayyear_pct_chg;
    }



    public double getRiXianXingQingvShiJianWeiXu_high() {
        return RiXianXingQingvShiJianWeiXu_high;
    }

    public void setRiXianXingQingvShiJianWeiXu_high(double riXianXingQingvShiJianWeiXu_high) {
        RiXianXingQingvShiJianWeiXu_high = riXianXingQingvShiJianWeiXu_high;
    }

    public double getRiXianXingQingvShiJianWeiXu_low() {
        return RiXianXingQingvShiJianWeiXu_low;
    }

    public void setRiXianXingQingvShiJianWeiXu_low(double riXianXingQingvShiJianWeiXu_low) {
        RiXianXingQingvShiJianWeiXu_low = riXianXingQingvShiJianWeiXu_low;
    }





    public Stock_NodeImpl(){

    }


    public double getRiXianXingQingvShiJianWeiXu_day3_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day3_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day3_pct_chg(double riXianXingQingvShiJianWeiXu_day3_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day3_pct_chg = riXianXingQingvShiJianWeiXu_day3_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day5_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day5_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day5_pct_chg(double riXianXingQingvShiJianWeiXu_day5_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day5_pct_chg = riXianXingQingvShiJianWeiXu_day5_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day10_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day10_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day10_pct_chg(double riXianXingQingvShiJianWeiXu_day10_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day10_pct_chg = riXianXingQingvShiJianWeiXu_day10_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day15_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day15_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day15_pct_chg(double riXianXingQingvShiJianWeiXu_day15_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day15_pct_chg = riXianXingQingvShiJianWeiXu_day15_pct_chg;
    }

    public  Stock_NodeImpl  newAreaCopy(){
        Stock_NodeImpl copyItem = new Stock_NodeImpl();
        copyItem.id = StockHolder.getNextStockAreaNodeID();
        copyItem.ts_code = ts_code;
        copyItem.level = level;
        copyItem.name = name;
        copyItem.industry = industry;
        copyItem.area = area;
        copyItem.count = count;
        copyItem.price = price;
        copyItem.MeiRiZhiBiao_total_mv = MeiRiZhiBiao_total_mv;
        return copyItem;

    }

//            sb_json.append(calculJsonItem("id",id));
//        sb_json.append(calculJsonItem("name",name));
//        sb_json.append(calculJsonItem("level",level));
//        sb_json.append(calculJsonItem("industry",industry));
//        sb_json.append(calculJsonItem("area",area));
////        sb_json.append(calculJsonItem("ts_code",ts_code));
//        sb_json.append(calculJsonItem("count",count));
//        sb_json.append(calculJsonItem("parentid",parentid));




    public Stock_NodeImpl(int mid, int mlevel3, String mlevel3_key, String mlevel3_value) {
        if (mlevel3 == 3) {
            this.level = 3;
            this.id = mid;
            this.level3_key = mlevel3_key;
            level3_value = mlevel3_value;

        }

    }


    public void  clearChild(  ){

        if(children == null){
            children = new ArrayList<Stock_NodeImpl>();
        }

        children.clear();
        count = 0;

    }




    public void  deleteChild(Stock_NodeImpl child ){

        if(children == null){
            children = new ArrayList<Stock_NodeImpl>();
        }

        children.remove(child);
        count = children.size();

    }


    public void  addChild(Stock_NodeImpl child ){

        if(children == null){
            children = new ArrayList<Stock_NodeImpl>();
        }

        children.add(child);
        count = children.size();

    }



    public void  areaChildrenAdd(Stock_NodeImpl child , int level){

        if(children == null){
            children = new ArrayList<Stock_NodeImpl>();
        }

        children.add(child);
        child.parentid = id;
        child.level = level;


    }


    public void  areaChildrenAdd(Stock_NodeImpl child){

        if(children == null){
            children = new ArrayList<Stock_NodeImpl>();
        }

            children.add(child);
        child.parentid = id;

    }


    public void  areaChildrenAdd(List<Stock_NodeImpl> childList){

        if(children == null){
            children = new ArrayList<Stock_NodeImpl>();
        }


        for (int i = 0; i < childList.size() ; i++) {
            Stock_NodeImpl item = childList.get(i);
            children.add(item);
            item.parentid = id;

        }


    }

    public void  initChildren(){

        children = new ArrayList<Stock_NodeImpl>();

    }


    public boolean isXinGuLieBiaoIPO_isIPO() {
        return XinGuLieBiaoIPO_isIPO;
    }

    public void setXinGuLieBiaoIPO_isIPO(boolean xinGuLieBiaoIPO_isIPO) {
        XinGuLieBiaoIPO_isIPO = xinGuLieBiaoIPO_isIPO;
    }


    public double getXinGuLieBiaoIPO_amount() {
        return XinGuLieBiaoIPO_amount;
    }

    public void setXinGuLieBiaoIPO_amount(double xinGuLieBiaoIPO_amount) {
        XinGuLieBiaoIPO_amount = xinGuLieBiaoIPO_amount;
    }


    public String getXinGuLieBiaoIPO_ipo_date() {
        return XinGuLieBiaoIPO_ipo_date;
    }

    public void setXinGuLieBiaoIPO_ipo_date(String xinGuLieBiaoIPO_ipo_date) {
        XinGuLieBiaoIPO_ipo_date = xinGuLieBiaoIPO_ipo_date;
    }

    public String getXinGuLieBiaoIPO_issue_date() {
        return XinGuLieBiaoIPO_issue_date;
    }

    public void setXinGuLieBiaoIPO_issue_date(String xinGuLieBiaoIPO_issue_date) {
        XinGuLieBiaoIPO_issue_date = xinGuLieBiaoIPO_issue_date;
    }

    public double getXinGuLieBiaoIPO_limit_amount() {
        return XinGuLieBiaoIPO_limit_amount;
    }

    public void setXinGuLieBiaoIPO_limit_amount(double xinGuLieBiaoIPO_limit_amount) {
        XinGuLieBiaoIPO_limit_amount = xinGuLieBiaoIPO_limit_amount;
    }

    public double getXinGuLieBiaoIPO_market_amount() {
        return XinGuLieBiaoIPO_market_amount;
    }

    public void setXinGuLieBiaoIPO_market_amount(double xinGuLieBiaoIPO_market_amount) {
        XinGuLieBiaoIPO_market_amount = xinGuLieBiaoIPO_market_amount;
    }

    public String getXinGuLieBiaoIPO_sub_code() {
        return XinGuLieBiaoIPO_sub_code;
    }

    public void setXinGuLieBiaoIPO_sub_code(String xinGuLieBiaoIPO_sub_code) {
        XinGuLieBiaoIPO_sub_code = xinGuLieBiaoIPO_sub_code;
    }

    public String getXinGuLieBiaoIPO_ts_code() {
        return XinGuLieBiaoIPO_ts_code;
    }

    public void setXinGuLieBiaoIPO_ts_code(String xinGuLieBiaoIPO_ts_code) {
        XinGuLieBiaoIPO_ts_code = xinGuLieBiaoIPO_ts_code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public double getXinGuLieBiaoIPO_ballot() {
        return XinGuLieBiaoIPO_ballot;
    }

    public void setXinGuLieBiaoIPO_ballot(double xinGuLieBiaoIPO_ballot) {
        XinGuLieBiaoIPO_ballot = xinGuLieBiaoIPO_ballot;
    }

    public String getXinGuLieBiaoIPO_cname() {
        return XinGuLieBiaoIPO_cname;
    }

    public void setXinGuLieBiaoIPO_cname(String xinGuLieBiaoIPO_cname) {
        XinGuLieBiaoIPO_cname = xinGuLieBiaoIPO_cname;
    }

    public double getXinGuLieBiaoIPO_funds() {
        return XinGuLieBiaoIPO_funds;
    }

    public void setXinGuLieBiaoIPO_funds(double xinGuLieBiaoIPO_funds) {
        XinGuLieBiaoIPO_funds = xinGuLieBiaoIPO_funds;
    }

    public String getXinGuLieBiaoIPO_name() {
        return XinGuLieBiaoIPO_name;
    }

    public void setXinGuLieBiaoIPO_name(String xinGuLieBiaoIPO_name) {
        XinGuLieBiaoIPO_name = xinGuLieBiaoIPO_name;
    }

    public double getXinGuLieBiaoIPO_pe() {
        android.util.Log.i("ZgetXinGuLieBiaoIPO_pe", "Name=" + name + "  XinGuLieBiaoIPO_pe=" + ts_code + "   XinGuLieBiaoIPO_pe=" + XinGuLieBiaoIPO_pe);
        return XinGuLieBiaoIPO_pe;
    }

    public void setXinGuLieBiaoIPO_pe(double xinGuLieBiaoIPO_pe) {
        XinGuLieBiaoIPO_pe = xinGuLieBiaoIPO_pe;
    }

    public double getXinGuLieBiaoIPO_price() {
        return XinGuLieBiaoIPO_price;
    }

    public void setXinGuLieBiaoIPO_price(double xinGuLieBiaoIPO_price) {
        XinGuLieBiaoIPO_price = xinGuLieBiaoIPO_price;
    }


    public void setPositionInAdapter(int positionInAdapter) {
        this.positionInAdapter = positionInAdapter;
    }

    public static String getRegexChinese() {
        return REGEX_CHINESE;
    }

    public static void setRegexChinese(String regexChinese) {
        REGEX_CHINESE = regexChinese;
    }

    public int getPositionInAdapter() {
        return positionInAdapter;
    }

    @Override
    public void setapapterposition(int position) {
        this.positionInAdapter = position;
    }


    @Override
    public String ts_code_number() {
        return symbol;
    }


    public int positionInAdapter;  // 以1 开始  在 adapter 中的位置  用于控制显示大小





//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

    public int getColor_int() {
        return color_int;
    }

    public void setColor_int(int color_int) {
        this.color_int = color_int;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Stock_NodeImpl> getChildren() {
        return children;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getSelectedChildId() {
        return selectedChildId;
    }

    public void setSelectedChildId(long selectedChildId) {
        this.selectedChildId = selectedChildId;
    }

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    public static Stock_NodeImpl getEMPTY() {
        return EMPTY;
    }

    public int getShowIndex() {
        return showindex;
    }

    public void setShowIndex(int showIndex) {
        this.showindex = showindex;
    }

    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    public static String clearChinese(String lineContent) {   // 计算非中文的长度
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return "";
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll("");
    }


    // 总的长度 - 非中文 的 长度  =  有多少个中文


    public Stock_NodeImpl(int mId, int mlevel) {
        this.level = mlevel;
        this.id = mId;
        children = new ArrayList<Stock_NodeImpl>();

    }


    public Stock_NodeImpl(long id, long xparentid, String Xname , int mlevel) {
        this.id = id;
        this.parentid = xparentid;
        this.name = Xname;
        if (this.name != null) {
            this.name = this.name.trim();
        }


        this.level = mlevel;
        children = new ArrayList<Stock_NodeImpl>();
        this.color_int = -100;
    }


    public Stock_NodeImpl(long id, long xparentid, String Xname, float total_mv , int mlevel) {
        this.id = id;
        this.parentid = xparentid;
        this.name = Xname;
        if (this.name != null) {
            this.name = this.name.trim();
        }

        this.MeiRiZhiBiao_total_mv = total_mv;
        this.level = mlevel;
        children = new ArrayList<Stock_NodeImpl>();
        this.color_int = -100;
    }

    @Override
    public long parentid() {
        return parentid;
    }

    @Override
    public long id() {
        return id;
    }


    void PaddingPair(String partA, String partB, StringBuilder rowA, StringBuilder rowB) {
        int xName_length = partA.length();
        int xIndustry_length = partB.length();
        int partA_maxLength = xName_length;
        if (partA_maxLength < xIndustry_length) {
            partA_maxLength = xIndustry_length;
        }

        String xName_padding_str = StockHolder.getRepeatString(" ", partA_maxLength - xName_length) + " ";
        String xindustry_padding_str = StockHolder.getRepeatString(" ", xIndustry_length - xName_length) + " ";
        rowA.append(xName_padding_str);
        rowB.append(xindustry_padding_str);
    }


    String getChineseBlockWithCount(int count) {
        if (count <= 0) {
            return "";
        }

        String result = StockHolder.getRepeatString("　", count);
        return result;

    }

    String getEnglishBlockWithCount(int count) {
        if (count <= 0) {
            return "";
        }

        String result = StockHolder.getRepeatString(" ", count);
        return result;

    }

    String getBlankBlockStr(String xname, int maxSize, int desc_asc_count) {
        String xname_origin = xname;
        int paddingSize = 0;
        int originLength = xname.length();
        int chineseCout = getChineseCount(xname);
        int english_Count = originLength - chineseCout;
        int currentLength = chineseCout * 2 + english_Count;

        if (currentLength > maxSize) {
            xname_origin = xname.substring(0, originLength - 2);
            int fixedCount = getStringLength_ContainChinese(xname_origin);
            paddingSize = maxSize - fixedCount;

        } else {
            paddingSize = maxSize - currentLength;
        }

        return StockHolder.getRepeatString(" ", paddingSize + desc_asc_count);

    }

    String calculPaddingWithSize(String originStr, String xname, String indstryStr, int chinese_size, int only_chinese_size, int needPaddingChinesCount, int maxSize) {
        int paddingChineseCount = needPaddingChinesCount - only_chinese_size;


        if (paddingChineseCount == 0) {
            String result = originStr;
            if (maxSize < chinese_size && maxSize <= originStr.length()) {
                return originStr.substring(0, maxSize);

            }
            int padding_size = maxSize - chinese_size;
            String block_str = "";

            if (xname.contains("ST") && !xname.contains("*ST")) {    //  如果是 ST  并且汉字是一样的  那么 少打 2个 英文空格占位符

                if (indstryStr.length() == 4 && xname.length() == 4 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str = StockHolder.getRepeatString(" ", paddingChineseCount + 3);
                } else {
                    block_str = StockHolder.getRepeatString(" ", padding_size);
                }


            } else if (xname.contains("*ST")) {
                if (indstryStr.length() == 4 && xname.length() == 5 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str = StockHolder.getRepeatString(" ", 3);
                } else if (indstryStr.length() == 3 && xname.length() == 4 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str = StockHolder.getRepeatString(" ", 3);
                } else if (indstryStr.length() == 3 && xname.length() == 5 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str = StockHolder.getRepeatString(" ", 4);
                } else if (indstryStr.length() == 4 && xname.length() == 6 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str = StockHolder.getRepeatString(" ", 3);
                } else {
                    block_str = StockHolder.getRepeatString(" ", maxSize - originStr.length() - only_chinese_size);

                }
            } else {
                block_str = StockHolder.getRepeatString(" ", padding_size);
            }


            System.out.println("originStr  = " + originStr + " zxname=" + xname + "   chinese_size=" + chinese_size + "  only_chinese_size=" + only_chinese_size + "  needPaddingChinesCount=" + needPaddingChinesCount + "   paddingChineseCount=" + paddingChineseCount + "   maxSize=" + maxSize + "   paddingChineseCount=" + paddingChineseCount + "  indstryStr.length()=" + indstryStr.length() + "   xname.length()=" + xname.length() + " Result_NoChinesePadding=【" + result.trim() + block_str + "】  (getStringLength_ContainChinese(indstryStr) == indstryStr.length())===" + (getStringLength_ContainChinese(indstryStr) == indstryStr.length()));

            return result + block_str;
        } else {   //  需要 补充汉字
            String block_str_chinese = "";  // 中文空格
            if (xname.contains("ST") && !xname.contains("*ST")) {    //  如果是 ST  并且汉字是一样的  那么 少打 2个 英文空格占位符
//                block_str_chinese =  StockHolder.getRepeatString("　",paddingChineseCount -1 );
                if (indstryStr.length() == 3 && xname.length() == 4) {
                    block_str_chinese = StockHolder.getRepeatString(" ", maxSize - chinese_size);
                } else if (indstryStr.length() == 4 && xname.length() == 4 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str_chinese = StockHolder.getRepeatString(" ", paddingChineseCount + 2);
                } else {
                    block_str_chinese = StockHolder.getRepeatString("　", paddingChineseCount);
                }


            } else if (xname.contains("*ST")) {
//                block_str_chinese =  StockHolder.getRepeatString("　",paddingChineseCount  );

                if (indstryStr.length() == 4 && xname.length() == 5 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str_chinese = StockHolder.getRepeatString(" ", 3);
                } else if (indstryStr.length() == 3 && xname.length() == 5 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str_chinese = StockHolder.getRepeatString(" ", 3);

                } else if (indstryStr.length() == 3 && xname.length() == 4 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str_chinese = StockHolder.getRepeatString(" ", 2);

                } else if (indstryStr.length() == 1 && xname.length() == 5 && (getChineseCount(indstryStr) == indstryStr.length())) {
                    block_str_chinese = StockHolder.getRepeatString(" ", 5);

                } else if (indstryStr.length() == 4 && xname.length() == 6 && (getChineseCount(indstryStr) == indstryStr.length())) {

                    block_str_chinese = StockHolder.getRepeatString(" ", 3);
                } else {

                    block_str_chinese = StockHolder.getRepeatString("　", paddingChineseCount);
                }

            } else {

                block_str_chinese = StockHolder.getRepeatString("　", paddingChineseCount);
            }


            String result = originStr;
            if (maxSize < chinese_size) {
                return originStr.substring(0, maxSize);
            }
            int padding_size = maxSize - chinese_size - 2 * paddingChineseCount;
            String block_str = "";
            if (xname.contains("ST") && !xname.contains("*ST")) {    //  如果是 ST  并且汉字是一样的  那么 少打 2个 英文空格占位符


            } else if (xname.contains("*ST")) {

            } else {
                block_str = StockHolder.getRepeatString(" ", padding_size);
            }
            System.out.println("originStr  = " + originStr + " zxname=" + xname + "   chinese_size=" + chinese_size + "  only_chinese_size=" + only_chinese_size + "  needPaddingChinesCount=" + needPaddingChinesCount + "   paddingChineseCount=" + paddingChineseCount + "   maxSize=" + maxSize + "   paddingChineseCount=" + paddingChineseCount + "  indstryStr.length()=" + indstryStr.length() + "   xname.length()=" + xname.length() + " Result=" + result.trim() + block_str_chinese + block_str);

            return result.trim() + block_str_chinese + block_str;


        }

    }

  String  onlykeepSHSZ(String tsCode){
        if(tsCode.startsWith("3")){
            return "CY";
        }else if(tsCode.startsWith("68")){
            return "KC";
        }
        if(tsCode.contains("SH")){
            return "SH";
        }
      if(tsCode.contains("SZ")){
          return "SZ";
      }
      return "SZ";

    }




static  int  Land_Pre_Content_MAX_Length = 46;
    String getLandStockInfo(Stock_NodeImpl noteImpl , String preContent){
        StringBuilder mLandInfoSB = new StringBuilder();




        String Padding_RiXianXingQingvShiJianWeiXu_pct_chg = "";
        String RiXianXingQingvShiJianWeiXu_pct_chg_str = decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg);
        if(!RiXianXingQingvShiJianWeiXu_pct_chg_str.contains("-")){  // 正数
            Padding_RiXianXingQingvShiJianWeiXu_pct_chg="%+"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg) ;
        }else{
            Padding_RiXianXingQingvShiJianWeiXu_pct_chg="%"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg) ;
        }



        if(DataHolder.System_IS_PAD) {

            int getStringLength_ContainChinese = getStringLength_ContainChinese(preContent);
            int chinseseCount =  getChineseCount(preContent);
            int diff_space = Land_Pre_Content_MAX_Length - getStringLength_ContainChinese;
            String lineA_space = "";
            if(diff_space > 0 ){
                lineA_space = StockHolder.getRepeatString(" ",diff_space);
            }

            android.util.Log.i("zukgit_land_info","index["+noteImpl.getPositionInAdapter()+"] name["+noteImpl.name+"] symbol["+noteImpl.symbol+"]"+"  chinseseCount="+chinseseCount+"  preContent="+preContent+ " Land_Pre_Content_MAX_Length="+Land_Pre_Content_MAX_Length +"  getStringLength_ContainChinese="+getStringLength_ContainChinese);



            mLandInfoSB.append(lineA_space);
            mLandInfoSB.append("0d" + Padding_RiXianXingQingvShiJianWeiXu_pct_chg + " ");   // 涨幅榜
            mLandInfoSB.append("换=%"+decimalFormat.format(noteImpl.MeiRiZhiBiao_turnover_rate_f)+" ") ;  // 换手率
            mLandInfoSB.append("震-%"+decimalFormat.format(noteImpl.getzhenfu_today())+" ") ;// 振幅占比


            mLandInfoSB.append("成-"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_amount/100000)+"亿"+" ") ;

            mLandInfoSB.append("值-"+decimal_MoneyFormat.format(noteImpl.MeiRiZhiBiao_total_mv/10000)+"亿"+" ") ;

            mLandInfoSB.append("流比"+decimal_MoneyFormat.format(noteImpl.getFlowPercent())+"%"+" ") ;
            int line2_space_count = 0 ;
            if(chinseseCount % 2 == 0){
                line2_space_count = Land_Pre_Content_MAX_Length -chinseseCount/2 ;
            }else{
                line2_space_count = Land_Pre_Content_MAX_Length -chinseseCount/2 -1 ;
            }

            if(noteImpl.getPositionInAdapter() < 10 && noteImpl.getPositionInAdapter() >= 0 ){
                line2_space_count = line2_space_count + 1;
            }
            mLandInfoSB.append("\n"+ StockHolder.getRepeatString(" ",line2_space_count) );
            mLandInfoSB.append("3d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day3_pct_chg)+""+" ") ;

            mLandInfoSB.append("5d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day5_pct_chg)+""+" ") ;

            mLandInfoSB.append("10d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day10_pct_chg)+""+" ") ;

            mLandInfoSB.append("15d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day15_pct_chg)+""+" ") ;

            mLandInfoSB.append("30d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day30_pct_chg)+""+" ") ;

            mLandInfoSB.append("60d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day60_pct_chg)+""+" ") ;

            mLandInfoSB.append("90d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day90_pct_chg)+""+" ") ;

            mLandInfoSB.append("Y%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_dayyear_pct_chg)+""+" ") ;

        }else{

            mLandInfoSB.append("" + Padding_RiXianXingQingvShiJianWeiXu_pct_chg + " ");   // 涨幅榜

            mLandInfoSB.append("换%"+decimalFormat.format(noteImpl.MeiRiZhiBiao_turnover_rate_f)+" ") ;  // 换手率
            mLandInfoSB.append("震%"+decimalFormat.format(noteImpl.getzhenfu_today())+" ") ;// 振幅占比


            mLandInfoSB.append("成"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_amount/100000)+"亿"+" ") ;

            mLandInfoSB.append("值"+decimal_MoneyFormat.format(noteImpl.MeiRiZhiBiao_total_mv/10000)+"亿"+" ") ;

            mLandInfoSB.append("流比"+decimal_MoneyFormat.format(noteImpl.getFlowPercent())+"%"+" ") ;

            mLandInfoSB.append("3d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day3_pct_chg)+""+" ") ;

            mLandInfoSB.append("5d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day5_pct_chg)+""+" ") ;

            mLandInfoSB.append("10d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day10_pct_chg)+""+" ") ;

            mLandInfoSB.append("15d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day15_pct_chg)+""+" ") ;

            mLandInfoSB.append("30d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day30_pct_chg)+""+" ") ;

            mLandInfoSB.append("60d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day60_pct_chg)+""+" ") ;

            mLandInfoSB.append("90d%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day90_pct_chg)+""+" ") ;

            mLandInfoSB.append("Y%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_dayyear_pct_chg)+""+" ") ;

        }






        return mLandInfoSB.toString();
    }

    // 动态显示的 属性  如 涨幅榜单什么
    String getDynamicOrderIndexShowProp(Stock_NodeImpl noteImpl){
        String dynamicShowStr = "";

        String RiXianXingQingvShiJianWeiXu_pct_chg_3dstr = decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_day3_pct_chg);

        if(!RiXianXingQingvShiJianWeiXu_pct_chg_3dstr.contains("-")){  // 正数
            RiXianXingQingvShiJianWeiXu_pct_chg_3dstr ="+"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_day3_pct_chg ) ;
        }else{
            RiXianXingQingvShiJianWeiXu_pct_chg_3dstr=""+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_day3_pct_chg );
        }


        String RiXianXingQingvShiJianWeiXu_pct_chg_5dstr = decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_day5_pct_chg);

        if(!RiXianXingQingvShiJianWeiXu_pct_chg_5dstr.contains("-")){  // 正数
            RiXianXingQingvShiJianWeiXu_pct_chg_5dstr ="+"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_day5_pct_chg ) ;
        }else{
            RiXianXingQingvShiJianWeiXu_pct_chg_5dstr=""+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_day5_pct_chg );
        }



        String pad_more_info_str ="      "+ "换%"+decimalFormat.format(noteImpl.MeiRiZhiBiao_turnover_rate_f)+" "+"3d%"+RiXianXingQingvShiJianWeiXu_pct_chg_3dstr+" "+"震%"+decimalFormat.format(noteImpl.getzhenfu_today()) +" "+"5d%"+RiXianXingQingvShiJianWeiXu_pct_chg_5dstr;;

        switch (StockHolder.Stock_NodeImpl_Comparator_Selected_Index){
/*            case  0:   // 价格榜_降序↓     // 价格板 也显示涨跌幅度
                dynamicShowStr="%"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg) ;
                break;*/

            case  0:  //  // 价格榜_降序↓
            case  1:   // 价格榜_升序↑
            case  2:   // 涨幅榜_降序↓
            case  3:  // 涨幅榜_升序↑
                String RiXianXingQingvShiJianWeiXu_pct_chg_str = decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg);
                if(DataHolder.System_IS_PAD){

                    if(!RiXianXingQingvShiJianWeiXu_pct_chg_str.contains("-")){  // 正数
                        dynamicShowStr="%+"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg ) ;
                    }else{
                        dynamicShowStr="%"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg );
                    }


                }else{
                    if(!RiXianXingQingvShiJianWeiXu_pct_chg_str.contains("-")){  // 正数
                        dynamicShowStr="%+"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg) ;
                    }else{
                        dynamicShowStr="%"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_pct_chg) ;
                    }
                }



                break;




            case 4:
                dynamicShowStr="换%"+decimalFormat.format(noteImpl.MeiRiZhiBiao_turnover_rate_f) ;  // 换手率

                break;

            case 5:
                dynamicShowStr="换%"+decimalFormat.format(noteImpl.MeiRiZhiBiao_turnover_rate_f) ;
                break;


            case 6:
                dynamicShowStr="震%"+decimalFormat.format(noteImpl.getzhenfu_today()) ; // 振幅占比

                break;

            case 7:
                dynamicShowStr="震%"+decimalFormat.format(noteImpl.getzhenfu_today()) ; // 振幅占比
                break;



            case 8:
                dynamicShowStr="成"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_amount/100000)+"亿" ; // 成交额  000.0亿   原始数据 千为单位   转为 00.0亿
                break;

            case 9:
                dynamicShowStr="成"+decimalFormat.format(noteImpl.RiXianXingQingvShiJianWeiXu_amount/100000)+"亿" ; // 成交额  000.0亿    1000 * 100000
                break;



            case 10:
                dynamicShowStr="值"+decimal_MoneyFormat.format(noteImpl.MeiRiZhiBiao_total_mv/10000)+"亿" ; //     // 总市值 MeiRiZhiBiao_total_mv  原始数值: 万元为单位

                break;

            case 11:
                dynamicShowStr="值"+decimal_MoneyFormat.format(noteImpl.MeiRiZhiBiao_total_mv/10000)+"亿" ; //
                break;




            case 12:
                dynamicShowStr="流"+decimal_MoneyFormat.format(noteImpl.MeiRiZhiBiao_circ_mv/10000)+"亿" ; //     //流市值 MeiRiZhiBiao_circ_mv

                break;

            case 13:
                dynamicShowStr="流"+decimal_MoneyFormat.format(noteImpl.MeiRiZhiBiao_circ_mv/10000)+"亿" ;    //流市值 MeiRiZhiBiao_circ_mv 万元为单位
                break;



            case 14:
                dynamicShowStr="流比"+decimal_MoneyFormat.format(noteImpl.getFlowPercent())+"%" ; // 流通占比

                break;

            case 15:
                dynamicShowStr="流比"+decimal_MoneyFormat.format(noteImpl.getFlowPercent())+"%" ; // 流通占比

                break;

            case 16:
                dynamicShowStr="生"+noteImpl.list_date ; //  IPO 日期

                break;

            case 17:
                dynamicShowStr="生"+noteImpl.list_date ; //  IPO 日期
                break;




            case 18:   // 3日涨幅榜_降序↓
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day3_pct_chg);
                break;


            case  19:   // 3日跌幅榜_升序↑
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day3_pct_chg);
                break;


            case  20:   // 5日涨幅榜_降序↓
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day5_pct_chg);
                break;


            case  21:   // 5日跌幅榜_升序↑
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day5_pct_chg);
                break;


            case  22:   // 10日涨幅榜_降序↓
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day10_pct_chg);
                break;


            case  23:   // 10日跌幅榜_升序↑
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day10_pct_chg);
                break;



            case  24:   // 15日涨幅榜_降序↓
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day15_pct_chg);
                break;


            case  25:   // 15日跌幅榜_升序↑
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day15_pct_chg);
                break;


            case  26:   // 30日涨幅榜_降序↓
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day30_pct_chg);
                break;


            case  27:   // 30日跌幅榜_升序↑
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day30_pct_chg);
                break;


            case  28:   // 60日涨幅榜_降序↓
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day60_pct_chg);
                break;


            case  29:   // 60日跌幅榜_升序↑
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day60_pct_chg);
                break;

            case  30:   // 90日涨幅榜_降序↓
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day90_pct_chg);
                break;


            case  31:   // 90日跌幅榜_升序↑
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_day90_pct_chg);
                break;


            case 32:   //  年内涨幅榜_降序↓
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_dayyear_pct_chg);
                break;


            case  33:   // 年内跌幅榜_升序↑
                dynamicShowStr="%"+decimalFormat.format(RiXianXingQingvShiJianWeiXu_dayyear_pct_chg);
                break;

            default:
                dynamicShowStr = "";
                break;
        }

        if(DataHolder.System_IS_PAD && !DataHolder.System_IS_LAND){
            dynamicShowStr = dynamicShowStr + " "+ pad_more_info_str;
        }


        return dynamicShowStr;


    }


    String make8date6date(String dateStr) {
        String dateResult = dateStr;
        if (dateResult != null && dateResult.length() > 2 && StockHolder.isNumeric(dateResult)) {   // 新光光电  黑龙江
            dateResult = dateResult.substring(2);
            return dateResult;
        }
        return "";
    }

   static DecimalFormat decimalFormat = new DecimalFormat(",#00.0");

    static DecimalFormat decimal_MoneyFormat = new DecimalFormat("#00");

    static DecimalFormat fourPadding_MoneyFormat = new DecimalFormat("#00000");
    static DecimalFormat fourPadding_PriceFormat = new DecimalFormat("#0000.00");
    // 动态显示 当前的 显示的主句  第二
    String calculPaddingString_little(String cname, String xsymbol, float xprice, String cindustry, String mlist_date, String carea , Stock_NodeImpl noteImpl) {
        StringBuilder part_1_builder = new StringBuilder();
        StringBuilder part_2_builder = new StringBuilder();

        String xlist_date = mlist_date;
        String xname = cname;
        String xarea = carea;

       String dynamicShowStr = getDynamicOrderIndexShowProp(noteImpl);   // 依据  Order_Index 动态显示的 属性value


//
//        android.util.Log.i("dynamicShowStr","dynamicShowStr="+dynamicShowStr+"  xname="+ xname);




        if (xarea != null && xarea.length() > 2) {   // 新光光电  黑龙江
            xarea = carea.substring(0, 2);
        }

        String xindustry = cindustry;
        if (xindustry != null && xindustry.length() > 2) {   // 新光光电  黑龙江
            xindustry = xindustry.substring(0, 2);
//            if(xarea == null && "".equals(xarea)){
//                xindustry = xindustry+getChineseBlockWithCount(2);
//            }
        }


        if (xname != null && xname.length() > 4 && !xname.contains("ST")) {
            xname = xname.substring(0, 4);
        } else if (xname.contains("ST")) {   // ST 的项  保留 5 个 字母

            if (xname != null && xname.length() > 5 && xname.contains("ST")) {
                xname = xname.substring(0, 5);
            }
        }

        if (xlist_date != null && xlist_date.length() == 8) {
            xlist_date = xlist_date.substring(2, 8);
//            xlist_date = StockHolder.calculLittleDigital(xlist_date);
        }


        int chinese_count = getChineseCount(xname);
        int dec_asc_paddingCount = 0;

        if (xname.contains("ST") && !xname.contains("ST*") && chinese_count == 2) {
            dec_asc_paddingCount = -1;   // 301 ST中珠
        } else if (!xname.contains("ST") && xname.contains("N")) {
            dec_asc_paddingCount = -1;   // 301 ST中珠

        } else if (!xname.contains("ST") && xname.contains("C")) {

            dec_asc_paddingCount = -1;   // 301 ST中珠
        } else if (xname.contains("ST*") && chinese_count == 1) {

            dec_asc_paddingCount = -3;   // 301 ST中珠
            System.out.println("1_xname = " + xname + "   dec_asc_paddingCount=" + dec_asc_paddingCount);
        } else if (xname.contains("ST*") && chinese_count == 2) {

            dec_asc_paddingCount = -2;   // 301 ST中珠
            System.out.println("2_xname = " + xname + "   dec_asc_paddingCount=" + dec_asc_paddingCount);
        }


        String lineA = xname + " " + getBlankBlockStr(xname, 8, dec_asc_paddingCount) + padding_price(xprice) + " " + ((xarea == null || "".equals(xarea.trim())) ? "Nill" : xarea) + xindustry + " " + symbol;
        int lineA_allLength = getStringLength_ContainChinese(lineA);

//        String lineB = (dec_asc_paddingCount != 0? " ":"")+StockHolder.getRepeatString(" ",lineA_allLength-xarea.length()-symbol.length()-xarea.length()-4)+(xindustry != null ? (xindustry.length()>=2?xindustry.substring(0,2):"xxx")+" ":"")+xlist_date;
        String lineB = ((dec_asc_paddingCount != 0 && positionInAdapter < 1000) ? " " : "") + StockHolder.getRepeatString(" ", lineA_allLength - xarea.length() - symbol.length() - xarea.length() - (positionInAdapter >= 1000 ? 1 : 0)) + xlist_date;


        if(DataHolder.System_IS_LAND ){   // 如果当前的 方向是Land 的 话    添加更多的数据

          String land_more_info_str =   getLandStockInfo(noteImpl , lineA+" "+dynamicShowStr);
            dynamicShowStr = dynamicShowStr+" "+ land_more_info_str;

        }


        part_1_builder.append(lineA+" "+dynamicShowStr);
        part_2_builder.append(lineB);
        return part_1_builder.toString() + "\n" + part_2_builder.toString();
    }


    String calculPaddingString(String xname, String xsymbol, float xprice, String xindustry, String mlist_date, String xarea) {
        StringBuilder part_1_builder = new StringBuilder();
        StringBuilder part_2_builder = new StringBuilder();
        String xlist_date = mlist_date;
        if (xlist_date != null && xlist_date.length() == 8) {
            xlist_date = xlist_date.substring(2, 8);
        }
        // 最多 5个 中文字  2 个 空格 总共12 个长度
        // 两个1  还不一定 对应 一个汉字
//       PaddingPair(xname,xindustry,part_1_builder,part_2_builder); =============================1
        int xName_length = xname.length();
        int xIndustry_length = xindustry.length();

        int xName_length_chinese = getStringLength_ContainChinese(xname);
        int xindustry_length_chinese = getStringLength_ContainChinese(xindustry);

        int xName_only_Chinese_size = xName_length_chinese - xName_length;
        int xindustry_only_Chinese_size = xindustry_length_chinese - xIndustry_length;
        int diff_chinese_distance = xName_only_Chinese_size;
        if (diff_chinese_distance < xindustry_only_Chinese_size) {
            diff_chinese_distance = xindustry_only_Chinese_size;
        }

        String xName_padding_str = calculPaddingWithSize(xname.trim(), xname.trim(), xindustry.trim(), xName_length_chinese, xName_only_Chinese_size, diff_chinese_distance, 9);
        String xindustry_padding_str = calculPaddingWithSize(xindustry.trim(), xname.trim(), xindustry.trim(), xindustry_length_chinese, xindustry_only_Chinese_size, diff_chinese_distance, 9);


        part_1_builder.append(xName_padding_str);
        part_2_builder.append(xindustry_padding_str);


//       PaddingPair(xsymbol,xlist_date,part_1_builder,part_2_builder); =============================2
        int xsymbol_length = xsymbol.length();
        int xlist_date_length = xlist_date.length();

        int partB_maxLength = xsymbol_length;
        if (partB_maxLength < xlist_date_length) {
            partB_maxLength = xlist_date_length;
        }


        String xsymbol_padding_str = xsymbol.trim() + StockHolder.getRepeatString(" ", partB_maxLength - xsymbol_length) + " ";
        String xlist_date_padding_str = xlist_date.trim() + StockHolder.getRepeatString(" ", partB_maxLength - xlist_date_length) + " ";
        part_1_builder.append(xsymbol_padding_str);
        part_2_builder.append(xlist_date_padding_str);


//       PaddingPair(xprice+"",xarea,part_1_builder,part_2_builder);  =============================3
        int xprice_length = (xprice + "").length();
        int xarea_length = xarea.length();
        String clearChinese_xprice_str = clearChinese(xprice + "") == null ? "" : clearChinese(xprice + "");
        String clearChinese_xarea_str = clearChinese(xarea) == null ? "" : clearChinese(xarea);
        int chinese_xprice_length = xprice_length - clearChinese_xprice_str.length();
        int chinese_xarea_length = xarea_length - clearChinese_xarea_str.length();
        xprice_length = xprice_length;   //  多出的中文 再加 中文的个数  每个中文 默认占两格
        xarea_length = xarea_length;

        int partC_maxLength = xprice_length;
        if (partC_maxLength < xarea_length) {
            partC_maxLength = xarea_length;
        }

        int chinese_padding_countC = xprice_length - xarea_length;
        if (chinese_padding_countC < 0) {
            chinese_padding_countC = xarea_length - xprice_length;
        }
        int xprice_Size = xprice_length > xarea_length ? (partC_maxLength - xprice_length) : (partC_maxLength - xprice_length + chinese_padding_countC * 2);
        int xarea_Size = xarea_length > xprice_length ? (partC_maxLength - xarea_length) : (partC_maxLength - xarea_length + chinese_padding_countC * 2);


        String xprice_padding_str = (xprice + "").trim() + StockHolder.getRepeatString(" ", xprice_Size) + " ";
        String xarea_padding_str = xarea.trim() + StockHolder.getRepeatString(" ", xarea_Size) + " ";
        part_1_builder.append(xprice_padding_str);
        part_2_builder.append(xarea_padding_str);

        String part_1_builder_fixed = part_1_builder.toString().replace(symbol, "").replace(price + "", "").trim();

        String padding_price_str = padding_price(price);

        part_1_builder_fixed = (part_1_builder_fixed + padding_price_str).replace(" ", "") + "      " + symbol;

//       String resultStr =   part_1_builder.toString()+"\n"+part_2_builder.toString();
        String resultStr = part_1_builder_fixed + "\n" + part_2_builder.toString();

        String resultStr_MakeIndustryBlank = resultStr.replace("\n" + xindustry, "\n" + StockHolder.getRepeatString("　", xindustry_only_Chinese_size) + StockHolder.getRepeatString(" ", xindustry.length() - xindustry_only_Chinese_size));
        resultStr_MakeIndustryBlank = resultStr_MakeIndustryBlank.replace(xlist_date, "");
        resultStr_MakeIndustryBlank = resultStr_MakeIndustryBlank.trim() + "_" + industry + xlist_date;


//       int xindustry_length_chinese = getStringLength_ContainChinese(xindustry);
//
//       int xindustry_only_Chinese_size = xindustry_length_chinese - xIndustry_length;


        System.out.println("ZresultStr  " + resultStr_MakeIndustryBlank);
//       return part_1_builder.toString()+"\n"+part_2_builder.toString();
        return resultStr_MakeIndustryBlank;
/*
        int xName_length =  xname.length();
        int xIndustry_length = xindustry.length();
        int partA_maxLength = xName_length;
        if(partA_maxLength < xIndustry_length ){
            partA_maxLength = xIndustry_length;
        }
        String xName_padding_str = StockHolder.getRepeatString(" ",partA_maxLength-xName_length)+" ";
       String xindustry_padding_str = StockHolder.getRepeatString(" ",xIndustry_length-xName_length)+" ";
       part_1_builder.append(xName_padding_str);
       part_2_builder.append(xindustry_padding_str);
*/


    }

    String fixed_onlyPointWithOne(double xprice) {

        return fixed_onlyPointWithOne(xprice + "");
    }


    String fixed_onlyPointWithOne(String xprice) {  // 1.1
        String result = xprice;
        if (result == null || !result.contains(".") || result.endsWith(".") ){
            return "";
        }

        int lastPointIndex = result.lastIndexOf(".");
        return result.substring(0,lastPointIndex+2);
    }



    String padding_price(double xprice){
        return padding_price((float) xprice );
    }
    // 1234.56
    String padding_price(float xprice){  // 1.1
        String result = xprice+"";
        if(result.length() <= 2){
            return  "";
        }
        String last2_str = result.charAt(result.length()-2)+"";
        if(".".equals(last2_str)){
            result = result +"0";   // 变为 1.10
        }


        System.out.println("Z_padding_price   xprice="+xprice);
        int price_length = result.length();
        if(price_length < 7 && result.contains(".") ){
            int padding_zreo_length = 7 - price_length;
            String paddingStr = StockHolder.getRepeatString("0",padding_zreo_length);
            result = paddingStr+result;

        }

        System.out.println("Z_padding_price   xprice="+xprice +"  result="+result);
        return result;
    }

    // 1个英文是一个空格   1个中文是两个空格
    int getChineseCount(String str){
        if(str == null || "".equals(str)){
            return 0;
        }
        int origin_size = str.length();
        int only_englisg_size = clearChinese(str).length();
        int chinsese_size = origin_size - only_englisg_size;

        return chinsese_size;


    }


    // 1个英文是一个空格   1个中文是两个空格
    int getStringLength_ContainChinese(String str){
        if(str == null || "".equals(str)){
            return 0;
        }
        int origin_size = str.length();
        int only_englisg_size = clearChinese(str).length();
        int chinsese_size = origin_size - only_englisg_size;

        return only_englisg_size +  2*chinsese_size;


    }

    // 把 level3 显示的 文字  的 key 属性 进行 转为 中文
    public String level3KeyToChinese(int position ,String level3Key , String level3Value){
        android.util.Log.i("level3Key","level3Key = "+ level3Key + "  level3Value="+level3Value);
        String level3Key_Chinese = "";
        String level3Value_Chinese = "";
        String result_text="";
        if(level3Key == null){
            return "";
        }
        //  不需要的属性的 集合   color_int   count enname  delist_date  curr_type REGEX_CHINESE EMPTY
        // XinGuLieBiaoIPO_sub_code symbol parentid list_status level showindex
switch (level3Key){
    case "area":
        level3Key_Chinese = "地域";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "market":
        level3Key_Chinese = "板块";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "symbol":
        level3Key_Chinese = "代码";
        result_text=level3Key_Chinese+"\n"+level3Value.replace(".","");
        break;

    case "name":
        level3Key_Chinese = "股名";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "delist_date":
        level3Key_Chinese = "退市日";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "XinGuLieBiaoIPO_issue_date":
        level3Key_Chinese = "上市日";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;


    case "XinGuLieBiaoIPO_market_amount":
        level3Key_Chinese = "网发量";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "XinGuLieBiaoIPO_ipo_date":
        level3Key_Chinese = "申购日";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;





    case "XinGuLieBiaoIPO_pe":
        level3Key_Chinese = "市盈率";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "XinGuLieBiaoIPO_price":
        level3Key_Chinese = "发行价";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;


    case "fullname":
        level3Key_Chinese = "";
        result_text=level3Key_Chinese+level3Value;
        break;

    case "XinGuLieBiaoIPO_ballot":
        level3Key_Chinese = "中签率:";
        result_text=level3Key_Chinese+level3Value;
        break;

    case "XinGuLieBiaoIPO_funds":
        level3Key_Chinese = "募集:";
        result_text=level3Key_Chinese+level3Value;
        break;

    case "XinGuLieBiaoIPO_amount":
        level3Key_Chinese = "总发量:";
        result_text=level3Key_Chinese+level3Value;
        break;

    case "XinGuLieBiaoIPO_isIPO":
        level3Key_Chinese = "IPO信息:";
        result_text=level3Key_Chinese+level3Value;
        break;




    case "price":
        level3Key_Chinese = "价格";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;


    case "industry":
        level3Key_Chinese = "行业";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;


    case "is_hs":  // exchange	str	N	交易所 SSE上交所 SZSE深交所
        level3Key_Chinese = "标的";
        if("N".equals(level3Value)){
            level3Value_Chinese="否";
        }

        if("H".equals(level3Value)){
            level3Value_Chinese="沪股通";
        }


        if("S".equals(level3Value)){
            level3Value_Chinese="深股通";
        }

        result_text=level3Key_Chinese+"\n"+level3Value_Chinese;
        break;


    case "exchange":  // exchange	str	N	交易所 SSE上交所 SZSE深交所
        level3Key_Chinese = "交易所";
        if("SSE".equals(level3Value)){
            level3Value_Chinese="上交所";
        }

        if("SZSE".equals(level3Value)){
            level3Value_Chinese="深交所";
        }
        result_text=level3Key_Chinese+"\n"+level3Value_Chinese;
        break;



    case "MeiRiZhiBiao_turnover_rate_f":
        level3Key_Chinese = "换手率";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;


    case "MeiRiZhiBiao_price":
        level3Key_Chinese = "价格";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "MeiRiZhiBiao_total_mv":
        level3Key_Chinese = "总市值";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "MeiRiZhiBiao_circ_mv":
        level3Key_Chinese = "流通市值";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;


    case "RiXianXingQingvShiJianWeiXu_change":
        level3Key_Chinese = "涨跌值";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;


    case "RiXianXingQingvShiJianWeiXu_pct_chg":
        level3Key_Chinese = "涨跌幅";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;

    case "RiXianXingQingvShiJianWeiXu_amount":
        level3Key_Chinese = "成交额";
        result_text=level3Key_Chinese+"\n"+level3Value;
        break;


    default:
        level3Key_Chinese="undefine";
}



        return StockHolder.calculLittleDigital(position)+result_text;
    }

    // 把 两个字符串 进行对齐
    @NonNull
    @Override
    public String text() {
        if(level == 1 ){
            return name + "(" + count + ")";
        }


        return name + "(" + count + ")";

    }


    @Override
    public String area_text( int nodePosition) {
        if(level == 1  ){
            int industry_count = count-1;
            if(industry_count <= 0 ){
                industry_count = 0;
            }
            if("All".equals(name)){
                return name + "(" + industry_count + ")"+"("+StockHolder.TScode_List.size()+")";   // 多了一个  安徽_all 这样的值
            }else{
                int allStockInArea = 0 ;
                if(children.size() > 0){
                     allStockInArea = children.get(0).children.size();
                }
                return name + "(" + industry_count + ")"+"("+allStockInArea+")";   // 多了一个  安徽_all 这样的值
            }

        }

        if(level == 2  ){
            return StockHolder.calculLittleDigital(nodePosition)+name + "(" + count + ")";
        }

//        calculLittleDigital
        if(level == 3){


            if(StockHolder.mShowLevel3Area_In_AreaPage_ModeIndex % StockHolder.mShowLevel3Area_In_AreaPage_ModeCount == 0 ){
                return StockHolder.calculLittleDigital(nodePosition)+name + "_" + price ;
            }else if(StockHolder.mShowLevel3Area_In_AreaPage_ModeIndex % StockHolder.mShowLevel3Area_In_AreaPage_ModeCount == 1 ) {
                return StockHolder.calculLittleDigital(nodePosition)+area+"_"+name + "_" + price ;

            }else if(StockHolder.mShowLevel3Area_In_AreaPage_ModeIndex % StockHolder.mShowLevel3Area_In_AreaPage_ModeCount == 2 ) {
                return StockHolder.calculLittleDigital(nodePosition)+area+"_"+industry+"__"+name + "_" + price ;
            }else if(StockHolder.mShowLevel3Area_In_AreaPage_ModeIndex % StockHolder.mShowLevel3Area_In_AreaPage_ModeCount == 3 ) {
//                return StockHolder.calculLittleDigital(nodePosition)+name + "_" + price+"_"+ decimal_MoneyFormat.format(MeiRiZhiBiao_total_mv/10000)+"亿";
                return StockHolder.calculLittleDigital(nodePosition)+area+"_"+ fourPadding_MoneyFormat.format(MeiRiZhiBiao_total_mv/10000)+"亿_" + fourPadding_PriceFormat.format(price) + "_"+name+"_"+industry ;

            }


        }

        return name ;

    }




    // 把 两个字符串 进行对齐
    @NonNull
    @Override
    public String text(int node3position ) {

        if(level == 3){
            return level3KeyToChinese(node3position,level3_key,level3_value) ;
        }

        return "";
    }


    // 显示的第二个框的列表 的内容
    // 把 两个字符串 进行对齐
    @NonNull
    @Override
    public String text(int position , String node1Type) {

        if(level == 1 || node1Type == null){
             return name + "(" + count + ")";
        }

        if(level == 3){
            return level3_key + " : " + level3_value ;
        }




        switch (node1Type){
            case "All板":   // All 榜 就是把所有 股票都列入它
            case "沪市":   // 沪市 就是 stocknode 的 exchange 属性为 SSE
            case "深市":   // 深市 就是 stocknode 的 exchange 属性为 SZSE
            case "创业板":   // 创业板 就是 stocknode 的 market 属性为 创业板
            case "科创板":   // 科创板 就是 stocknode 的 market 属性为 科创板
            case "中小板":   // 中小板 就是 stocknode 的 market 属性为 中小板
                return    calIndexPaddingText(position,calculPaddingString_little(name,symbol,price,industry,list_date,area,this));

            case "收藏板":   //  ST 板 就是  股票名称中 包含 ST 的股票 的集合

                    return    calIndexPaddingText(position,calculPaddingString_little(name,symbol,price,industry,list_date,area,this));

            case "ST板":   //  ST 板 就是  股票名称中 包含 ST 的股票 的集合
                return    StockHolder.calculLittleDigital((position))+(name.length() ==4 ? name+" ":name)  +" " +  padding_price(price)+" "+make8date6date(list_date)+" "+clearPoint(ts_code)+"\n"+"                     "+ industry +" "+ area;


            case "退市板":   // 退市股板 就是属性 list_status 不为 L 的 股票
                return    StockHolder.calculLittleDigital((position))+calTuiPadding(clearTui(name),4)  +" " +make8date6date(list_date)+"_"+make8date6date(delist_date)+" "+clearPoint(ts_code)+"\n"+"                 "+ industry +" "+ area;


            case "IPO新板":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
//                return    calculPaddingString_little(getXinGuLieBiaoIPO_name(),getXinGuLieBiaoIPO_ts_code(),(float)getXinGuLieBiaoIPO_price(),getXinGuLieBiaoIPO_issue_date(),getXinGuLieBiaoIPO_ipo_date(),area);

                android.util.Log.i("zXinGuLieBiaoIPO","text "+ts_code+": "+toString());

                    return     StockHolder.calculLittleDigital((position))+calIPOName(getXinGuLieBiaoIPO_name(),4)+" "+ padding_price(getXinGuLieBiaoIPO_price())+" "+"PE:"+fixed_onlyPointWithOne(getXinGuLieBiaoIPO_pe())+""+"\n                 " +make8date6date(getXinGuLieBiaoIPO_ipo_date() )+"_"+make8date6date(getXinGuLieBiaoIPO_issue_date())+""+onlykeepSHSZ(ts_code);



            case "个股资金流向榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "Day_Holder涨跌板":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "龙虎榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "Day_Holder沪深股通十大成交股":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "Month_Holder涨跌停板":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "大宗交易信息榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "Year_Holder沪深港资金流向榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "沪深港持股明细榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "融资融券交易明细榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "股票回购信息榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;


            case "限售股解禁榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;

            case "近三月股东增减持信息榜":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                break;




            default:
        }
        return name + "(" + count + ")" + toString();
    }

    String clearPoint(String str){
        if(str != null){
            return  str.replace(".","");
        }
     return "";
    }




    String calIPOName(String str , int maxSize){
        int strLength = str.length();
        int chinese_length = getChineseCount(str);
        int common_english_length  =strLength - chinese_length;

        int chinese_diff = maxSize - chinese_length;
        String paddingStr = "";


        if(chinese_diff > 0){

            paddingStr = StockHolder.getRepeatString("　",chinese_diff ) ;
        }
        return str +paddingStr;
    }


    String calTuiPadding(String str , int maxSize){
  int strLength = str.length();
  int chinese_length = getChineseCount(str);
   int common_english_length  =strLength - chinese_length;

  int chinese_diff = maxSize - chinese_length;
  String paddingStr = "";
if(str.startsWith("T")){
    str = str.substring(1);
}

  if(chinese_diff > 0){

      paddingStr = StockHolder.getRepeatString("　",chinese_diff ) ;
  }
return str +paddingStr;
    }

    String clearTui(String str){
        if(str != null){
            if(!str.contains("退")){
                str = str +"退";
            }
            str =  str.replace("*","");
            str =  str.replace("S","");
            str =  str.replace("ST","");
            str =  str.replace("ST","");
            str =  str.replace("PT","");
            str =  str.replace("(退)","T");
            str =  str.replace("退退","T");
            return str;
        }
        return "";
    }

    // 第二列表项 显示的集合 动态计算
    String calIndexPaddingText(int position, String text){
        String[] strArr = text.split("\n");
        String line_1 = strArr[0];
        String line_2 = strArr[1];


        // 小数字 位置
        String paddingIndexStr = StockHolder.calculLittleDigital((position));

/*        if(num1_count != 0 ){

            if(position >= 10 && position <= 99){
                if(num1_count == 1 &&  positionStr.startsWith("1") ){ //  1x
                    if("10".equals(positionStr)){
                        paddingCount = paddingCount ;
                    }else{
                        paddingCount = paddingCount  ;
                    }
                }else if (num1_count == 1 &&  !positionStr.startsWith("1") ){  // x1
                    paddingCount = paddingCount + 1;
                }
                if(num1_count == 2){  // 11
                    paddingCount = paddingCount + 1;
                }
            }
          if(num1_count == 2){
                paddingCount = paddingCount + num1_count - 1;
            }else if(num1_count == 3) {

                paddingCount = paddingCount + num1_count ;
            }else if(num1_count == 4) {
                paddingCount = paddingCount + num1_count  + 1;
            }else if(num1_count ==  5) {
                paddingCount = paddingCount + num1_count  + 2;
            }else {

              if(num1_count == 1 && position >= 100 && positionStr.startsWith("1")){
                  // 以 1 开头的 只 出现 一次的  项 大于 100 的 以 1 开头的项
                  paddingCount = paddingCount  ;
              }else if(num1_count == 1 && position < 100 && position > 50 && !positionStr.startsWith("1")){
                  // 50与 100 之间 不以 1 开头的 项
                  paddingCount = paddingCount  + 1;
              }

            }

        }*/

        int    Position_Pre_Length = (""+position).length()+1;  // 显示数字的个数
        String blockPading =  StockHolder.getRepeatString(" ",Position_Pre_Length);
        return paddingIndexStr+line_1+"\n"+blockPading+line_2;

    }



    @Override
    public String name() {
        return (name == null || "null".equals(name))?getXinGuLieBiaoIPO_cname():name;
    }


    @Override
    public long selectedChild() {
        return selectedChildId;
    }

    @Override
    public void setSelectedChild(long id) {
        selectedChildId = id;
    }

    @Nullable
    @Override
    public List<? extends Stock_Node> children() {
        return children;
    }

    @Nullable
    @Override
    public void setChildren( List<? extends Stock_Node>  children) {
        this.children =(List<Stock_NodeImpl>) children;
    }


String checkJsonNull(String prop){
        if(prop == null || "".equals(prop) || "null".equals(prop)){
            return "\"\"";
        }
        return "\""+prop.trim()+"\"";
}

    String calculJsonItem(String keyName , boolean keyValue){

        return calculJsonItem(keyName,keyValue+"");
    }

    String calculJsonItem(String keyName , int keyValue){

        return "\""+keyName.trim()+"\""+":"+keyValue+",\n";
    }

    String calculJsonItem(String keyName , float keyValue){

        return "\""+keyName.trim()+"\""+":"+keyValue+",\n";
    }


    String calculJsonItem(String keyName , double keyValue){

        return "\""+keyName.trim()+"\""+":"+keyValue+",\n";
    }

String calculJsonItem(String keyName , String keyValue){
        String jsonResult = "";
        String checkJsonNull_resut = checkJsonNull(keyValue);

        return "\""+keyName.trim()+"\""+":"+checkJsonNull_resut+",\n";
}



    @Override
    public String area_json() {
        count = children.size();

        StringBuilder area_json = new StringBuilder();
        area_json.append("{\n");

        area_json.append(calculJsonItem("id",id));
        area_json.append(calculJsonItem("parentid",parentid));
        area_json.append(calculJsonItem("name",name));
        area_json.append(calculJsonItem("level",level));
        area_json.append(calculJsonItem("area",area));
        area_json.append(calculJsonItem("industry",industry));

//        sb_json.append(calculJsonItem("ts_code",ts_code));
        area_json.append(calculJsonItem("count",count));

        area_json.append(calculJsonItem("price",price));
        area_json.append(calculJsonItem("MeiRiZhiBiao_total_mv",MeiRiZhiBiao_total_mv));
        if(children == null || children.size() == 0) {
            area_json.append("\"children\": "+"[]\n");
        }else {
            area_json.append("\"children\": "+"[\n");
            for (int i = 0; i < children.size(); i++) {
                Stock_NodeImpl childNode = children.get(i);
                if(childNode != null && childNode.children() != null && childNode.children().size() > 0) {
//                    System.out.println("childNode.name = "+ childNode.name + "  childNode.level="+childNode.level+"  childNode.id="+childNode.id + "  childNode.count="+childNode.children().size());

                    // 子节点 之间 使用 , 逗号 分隔
                    if(i == children.size()-1 ) {
                        area_json.append(childNode.area_json()+"\n");
                    }else {
                        area_json.append(childNode.area_json()+",\n");
                    }


                }else {
                    System.out.println("结点 的 childNode 为空！！！ 为叶子节点");
                    if(childNode != null) {
                        if(i == children.size()-1 ) {
                            area_json.append(childNode.area_json()+"\n");
                        }else {
                            area_json.append(childNode.area_json()+",\n");
                        }

                    }

                }

            }
            area_json.append("]\n");
        }

        area_json.append("}\n");
        return area_json.toString();

    }
    @Override
    public String json() {
        count = children.size();
        System.out.println("___________【"+name+ "】 Node_Level_"+level+"节点情况 Begin ___________");
        for (int i = 0; i < children.size(); i++) {
            System.out.println(children.get(i).toString());
        }
        System.out.println("___________【"+name+ "】Node_Level_"+level+"节点情况 End ___________");
        System.out.println();

        StringBuilder sb_json = new StringBuilder();
        sb_json.append("{\n");
        if(level == 3){
            sb_json.append(calculJsonItem("id",id));
            sb_json.append(calculJsonItem("name",name));
            sb_json.append(calculJsonItem("level",level));
            sb_json.append(calculJsonItem("level3_key",level3_key));
            sb_json.append(calculJsonItem("level3_value",level3_value));
            sb_json.append(calculJsonItem("ts_code",ts_code));
            sb_json.append(calculJsonItem("price",price));

            if(XinGuLieBiaoIPO_isIPO){

/*                public   String XinGuLieBiaoIPO_cname ;
                public   String XinGuLieBiaoIPO_ipo_date ;
                public   String XinGuLieBiaoIPO_issue_date ;
                public   String XinGuLieBiaoIPO_name ;
                public   String XinGuLieBiaoIPO_sub_code ;
                public   String XinGuLieBiaoIPO_ts_code ;

                public   double XinGuLieBiaoIPO_amount;
                public   double XinGuLieBiaoIPO_ballot;
                public   double XinGuLieBiaoIPO_pe ;
                public   double XinGuLieBiaoIPO_price ;
                public   double XinGuLieBiaoIPO_limit_amount ;
                public   double XinGuLieBiaoIPO_market_amount ;
                public   double XinGuLieBiaoIPO_funds ;
                public   boolean  xingupiaoliebiao_isipo; // 是否是在 IPO中显示的项*/

                // String
//                sb_json.append("\"id\": "+id+",\n");


                // Double -- float  --- int
//                sb_json.append("\"id\": "+id+",\n");

                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_isIPO",XinGuLieBiaoIPO_isIPO));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_ballot",XinGuLieBiaoIPO_ballot));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_funds",XinGuLieBiaoIPO_funds));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_ipo_date",XinGuLieBiaoIPO_ipo_date));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_issue_date",XinGuLieBiaoIPO_issue_date));

                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_limit_amount",XinGuLieBiaoIPO_limit_amount));

                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_market_amount",XinGuLieBiaoIPO_market_amount));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_name",XinGuLieBiaoIPO_name));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_pe",XinGuLieBiaoIPO_pe));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_price   ",XinGuLieBiaoIPO_price));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_sub_code   ",XinGuLieBiaoIPO_sub_code));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_ts_code   ",XinGuLieBiaoIPO_ts_code));
            }








        }else  if(level == 1){
            sb_json.append(calculJsonItem("id",id));
            sb_json.append(calculJsonItem("name",name));
            sb_json.append(calculJsonItem("count",count));

            sb_json.append(calculJsonItem("ts_code",ts_code));
            sb_json.append(calculJsonItem("level",level));
        }else {
            sb_json.append(calculJsonItem("id",id));

            sb_json.append(calculJsonItem("name",name));

            sb_json.append(calculJsonItem("showindex",showindex));
            sb_json.append(calculJsonItem("level",level));
            sb_json.append(calculJsonItem("price",price));
            sb_json.append(calculJsonItem("ts_code",ts_code));
            sb_json.append(calculJsonItem("symbol",symbol));
            sb_json.append(calculJsonItem("market",market));
            sb_json.append(calculJsonItem("list_status",list_status));

            sb_json.append(calculJsonItem("list_status",list_status));

            sb_json.append(calculJsonItem("delist_date",delist_date));
            sb_json.append(calculJsonItem("list_date",list_date));
            sb_json.append(calculJsonItem("is_hs",is_hs));
            sb_json.append(calculJsonItem("industry",industry));
            sb_json.append(calculJsonItem("fullname",fullname));
            sb_json.append(calculJsonItem("exchange",exchange));
            sb_json.append(calculJsonItem("area",area));
            sb_json.append(calculJsonItem("positionInAdapter",positionInAdapter));


            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_amount",RiXianXingQingvShiJianWeiXu_amount));
            sb_json.append(calculJsonItem("MeiRiZhiBiao_turnover_rate_f",MeiRiZhiBiao_turnover_rate_f));
            sb_json.append(calculJsonItem("MeiRiZhiBiao_total_mv",MeiRiZhiBiao_total_mv));
            sb_json.append(calculJsonItem("MeiRiZhiBiao_circ_mv",MeiRiZhiBiao_circ_mv));
            sb_json.append(calculJsonItem("XinGuLieBiaoIPO_ipo_date",XinGuLieBiaoIPO_ipo_date));

            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_low",RiXianXingQingvShiJianWeiXu_low));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_high",RiXianXingQingvShiJianWeiXu_high));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_change",RiXianXingQingvShiJianWeiXu_change));
            sb_json.append(calculJsonItem("XinGuLieBiaoIPO_issue_date",XinGuLieBiaoIPO_issue_date));


            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_pct_chg",RiXianXingQingvShiJianWeiXu_pct_chg));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_day3_pct_chg",RiXianXingQingvShiJianWeiXu_day3_pct_chg));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_day5_pct_chg",RiXianXingQingvShiJianWeiXu_day5_pct_chg));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_day10_pct_chg",RiXianXingQingvShiJianWeiXu_day10_pct_chg));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_day15_pct_chg",RiXianXingQingvShiJianWeiXu_day15_pct_chg));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_day30_pct_chg",RiXianXingQingvShiJianWeiXu_day30_pct_chg));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_day60_pct_chg",RiXianXingQingvShiJianWeiXu_day60_pct_chg));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_day90_pct_chg",RiXianXingQingvShiJianWeiXu_day90_pct_chg));
            sb_json.append(calculJsonItem("RiXianXingQingvShiJianWeiXu_dayyear_pct_chg",RiXianXingQingvShiJianWeiXu_dayyear_pct_chg));


            if(XinGuLieBiaoIPO_isIPO){
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_isIPO",XinGuLieBiaoIPO_isIPO));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_ballot",XinGuLieBiaoIPO_ballot));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_funds",XinGuLieBiaoIPO_funds));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_ipo_date",XinGuLieBiaoIPO_ipo_date));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_issue_date",XinGuLieBiaoIPO_issue_date));

                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_limit_amount",XinGuLieBiaoIPO_limit_amount));

                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_market_amount",XinGuLieBiaoIPO_market_amount));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_name",XinGuLieBiaoIPO_name));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_pe",XinGuLieBiaoIPO_pe));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_price   ",XinGuLieBiaoIPO_price));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_sub_code   ",XinGuLieBiaoIPO_sub_code));
                sb_json.append(calculJsonItem("XinGuLieBiaoIPO_ts_code   ",XinGuLieBiaoIPO_ts_code));
            }



        }




        if(children == null || children.size() == 0) {
            sb_json.append("\"children\": "+"[]\n");
        }else {
            sb_json.append("\"children\": "+"[\n");
            for (int i = 0; i < children.size(); i++) {
                Stock_NodeImpl childNode = children.get(i);
                if(childNode != null && childNode.children() != null && childNode.children().size() > 0) {
                    System.out.println("childNode.name = "+ childNode.name + "  childNode.level="+childNode.level+"  childNode.id="+childNode.id + "  childNode.count="+childNode.children().size());

                    // 子节点 之间 使用 , 逗号 分隔
                    if(i == children.size()-1 ) {
                        sb_json.append(childNode.json()+"\n");
                    }else {
                        sb_json.append(childNode.json()+",\n");
                    }


                }else {
                    System.out.println("结点 的 childNode 为空！！！ 为叶子节点");
                    if(childNode != null) {
                        if(i == children.size()-1 ) {
                            sb_json.append(childNode.json()+"\n");
                        }else {
                            sb_json.append(childNode.json()+",\n");
                        }

                    }

                }

            }
            sb_json.append("]\n");
        }

        sb_json.append("}\n");
        return sb_json.toString();
    }


    @Override
    public float total_mv() {
       return getMeiRiZhiBiao_total_mv();

    }


    @Override
    public void setparentid(long mparentid) {
        this.parentid = mparentid;

    }


    @Override
    public void setlevel(int level) {
        // TODO Auto-generated method stub
        this.level = level;
    }

    @Override
    public float price(){
        return price;
    }

    @Override
    public int level() {
        // TODO Auto-generated method stub
        return level;
    }

    @Override
    public int color() {
        return color_int;
    }

    @Override
    public void setcolor(int xcolor) {
         color_int = xcolor;
    }




    @Override
    public Stock_Node getSelectedChild() {
        int i = getSelectedChildPosition();
        if (i == -1) return null;
        // in case of IndexOutOfBoundsException
        if (i >= 0 && i < children.size()) {
            return children.get(i);
        } else {
            return null;
        }
    }

    public String getName() {
        return name;
    }







    public void addChildren(Stock_NodeImpl oneChildren) {
        this.children.add(oneChildren);
    }


    private int getSelectedChildPosition() {
        if (children == null) return -1;
        for (int i = 0; i < children.size(); i++) {
            Stock_NodeImpl nodeImpl = children.get(i);
            if (nodeImpl == null) continue;
            if (nodeImpl.id == selectedChildId) {
                return i;
            }
        }
        return -1;
    }


    public String toString_Node() {
        return "Stock_NodeImpl{" +
                "color_int=" + color_int +
                ", id=" + id +
                ", count=" + count +
                ", children=" + children +
                ", level=" + level +
                ", selectedChildId=" + selectedChildId +
                ", parentid=" + parentid +
                ", area='" + area + '\'' +
                ", curr_type='" + curr_type + '\'' +
                ", name='" + name + '\'' +
                ", enname='" + enname + '\'' +
                ", exchange='" + exchange + '\'' +
                ", fullname='" + fullname + '\'' +
                ", industry='" + industry + '\'' +
                ", is_hs='" + is_hs + '\'' +
                ", list_date='" + list_date + '\'' +
                ", delist_date='" + delist_date + '\'' +
                ", list_status='" + list_status + '\'' +
                ", market='" + market + '\'' +
                ", symbol='" + symbol + '\'' +
                ", ts_code='" + ts_code + '\'' +
                ", level3_key='" + level3_key + '\'' +
                ", level3_value='" + level3_value + '\'' +
                ", showindex=" + showindex +
                ", price=" + price +
                ", XinGuLieBiaoIPO_cname='" + XinGuLieBiaoIPO_cname + '\'' +
                ", XinGuLieBiaoIPO_ipo_date='" + XinGuLieBiaoIPO_ipo_date + '\'' +
                ", XinGuLieBiaoIPO_issue_date='" + XinGuLieBiaoIPO_issue_date + '\'' +
                ", XinGuLieBiaoIPO_name='" + XinGuLieBiaoIPO_name + '\'' +
                ", XinGuLieBiaoIPO_sub_code='" + XinGuLieBiaoIPO_sub_code + '\'' +
                ", XinGuLieBiaoIPO_ts_code='" + XinGuLieBiaoIPO_ts_code + '\'' +
                ", XinGuLieBiaoIPO_amount=" + XinGuLieBiaoIPO_amount +
                ", XinGuLieBiaoIPO_ballot=" + XinGuLieBiaoIPO_ballot +
                ", XinGuLieBiaoIPO_pe=" + XinGuLieBiaoIPO_pe +
                ", XinGuLieBiaoIPO_price=" + XinGuLieBiaoIPO_price +
                ", XinGuLieBiaoIPO_limit_amount=" + XinGuLieBiaoIPO_limit_amount +
                ", XinGuLieBiaoIPO_market_amount=" + XinGuLieBiaoIPO_market_amount +
                ", XinGuLieBiaoIPO_funds=" + XinGuLieBiaoIPO_funds +
                ", XinGuLieBiaoIPO_isIPO=" + XinGuLieBiaoIPO_isIPO +
                ", MeiRiZhiBiao_turnover_rate_f=" + MeiRiZhiBiao_turnover_rate_f +
                ", MeiRiZhiBiao_price=" + MeiRiZhiBiao_price +
                ", MeiRiZhiBiao_total_mv=" + MeiRiZhiBiao_total_mv +
                ", MeiRiZhiBiao_circ_mv=" + MeiRiZhiBiao_circ_mv +
                ", RiXianXingQingvShiJianWeiXu_change=" + RiXianXingQingvShiJianWeiXu_change +
                ", RiXianXingQingvShiJianWeiXu_pct_chg=" + RiXianXingQingvShiJianWeiXu_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_amount=" + RiXianXingQingvShiJianWeiXu_amount +
                ", RiXianXingQingvShiJianWeiXu_high=" + RiXianXingQingvShiJianWeiXu_high +
                ", RiXianXingQingvShiJianWeiXu_low=" + RiXianXingQingvShiJianWeiXu_low +
                ", RiXianXingQingvShiJianWeiXu_day3_pct_chg=" + RiXianXingQingvShiJianWeiXu_day3_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day5_pct_chg=" + RiXianXingQingvShiJianWeiXu_day5_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day10_pct_chg=" + RiXianXingQingvShiJianWeiXu_day10_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day15_pct_chg=" + RiXianXingQingvShiJianWeiXu_day15_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day30_pct_chg=" + RiXianXingQingvShiJianWeiXu_day30_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day60_pct_chg=" + RiXianXingQingvShiJianWeiXu_day60_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day90_pct_chg=" + RiXianXingQingvShiJianWeiXu_day90_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_dayyear_pct_chg=" + RiXianXingQingvShiJianWeiXu_dayyear_pct_chg +
                ", positionInAdapter=" + positionInAdapter +
                '}';
    }


    @Override
    public String toString() {
        if(level != 3){
            return "Stock_NodeImpl{" +
                    "color_int=" + color_int +
                    ", id=" + id +
                    ", count=" + count +
                    ", children=" + children +
                    ", level=" + level +
                    ", selectedChildId=" + selectedChildId +
                    ", parentid=" + parentid +
                    ", area='" + area + '\'' +
                    ", curr_type='" + curr_type + '\'' +
                    ", name='" + name + '\'' +
                    ", enname='" + enname + '\'' +
                    ", exchange='" + exchange + '\'' +
                    ", fullname='" + fullname + '\'' +
                    ", industry='" + industry + '\'' +
                    ", is_hs='" + is_hs + '\'' +
                    ", list_date='" + list_date + '\'' +
                    ", delist_date='" + delist_date + '\'' +
                    ", list_status='" + list_status + '\'' +
                    ", market='" + market + '\'' +
                    ", symbol='" + symbol + '\'' +
                    ", ts_code='" + ts_code + '\'' +
                    ", level3_key='" + level3_key + '\'' +
                    ", level3_value='" + level3_value + '\'' +
                    ", showindex=" + showindex +
                    ", price=" + price +
                    ", XinGuLieBiaoIPO_isIPO=" + XinGuLieBiaoIPO_isIPO +
                    ", XinGuLieBiaoIPO_ballot=" + XinGuLieBiaoIPO_ballot +
                    ", XinGuLieBiaoIPO_cname='" + XinGuLieBiaoIPO_cname + '\'' +
                    ", XinGuLieBiaoIPO_funds=" + XinGuLieBiaoIPO_funds +
                    ", XinGuLieBiaoIPO_ipo_date='" + XinGuLieBiaoIPO_ipo_date + '\'' +
                    ", XinGuLieBiaoIPO_issue_date='" + XinGuLieBiaoIPO_issue_date + '\'' +
                    ", XinGuLieBiaoIPO_limit_amount=" + XinGuLieBiaoIPO_limit_amount +
                    ", XinGuLieBiaoIPO_market_amount=" + XinGuLieBiaoIPO_market_amount +
                    ", XinGuLieBiaoIPO_name='" + XinGuLieBiaoIPO_name + '\'' +
                    ", XinGuLieBiaoIPO_pe=" + XinGuLieBiaoIPO_pe +
                    ", XinGuLieBiaoIPO_price=" + XinGuLieBiaoIPO_price +
                    ", XinGuLieBiaoIPO_sub_code='" + XinGuLieBiaoIPO_sub_code + '\'' +
                    ", XinGuLieBiaoIPO_ts_code='" + XinGuLieBiaoIPO_ts_code + '\'' +
                    ", XinGuLieBiaoIPO_amount=" + XinGuLieBiaoIPO_amount +
                    ", positionInAdapter=" + positionInAdapter +
                    '}';
        }

        return   "levelkey["+level3_key+"] level3value["+level3_value+"]";
    }

    public void  initKeyAndValue(String mfieldName , String mfieldValue){
        if(mfieldName == null){
            return;
        }
        try {

            Field[] fields =   this.getClass().getFields();
            if(fields == null){
                android.util.Log.i("StockItem"," FiledList 为空！！");
                return;
            }

            for (int i = 0; i < fields.length; i++) {
                Field fielditem = fields[i];
                String fieldName = fielditem.getName();
                if(fieldName.equals(mfieldName)){
                    fielditem.set(this,mfieldValue);
                    return;

                }
//                android.util.Log.i("StockItem"," index["+i+"] fielditem.getName()="+fielditem.getName() +"   fielditem.toString()="+fielditem.toString()  +"   fielditem.toGenericString()="+fielditem.toGenericString());
            }



        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public String getArea(){
        return area;
    }

    public void setArea(String area){
        this.area=area;
    }

    public String getCurr_type(){
        return curr_type;
    }

    public void setCurr_type(String curr_type){
        this.curr_type=curr_type;
    }

    public String getDelist_date(){
        return delist_date;
    }

    public void setDelist_date(String delist_date){
        this.delist_date=delist_date;
    }

    public String getEnname(){
        return enname;
    }

    public void setEnname(String enname){
        this.enname=enname;
    }

    public String getExchange(){
        return exchange;
    }

    public void setExchange(String exchange){
        this.exchange=exchange;
    }

    public String getFullname(){
        return fullname;
    }

    public void setFullname(String fullname){
        this.fullname=fullname;
    }

    public String getIndustry(){
        return industry;
    }

    public void setIndustry(String industry){
        this.industry=industry;
    }

    public String getIs_hs(){
        return is_hs;
    }

    public void setIs_hs(String is_hs){
        this.is_hs=is_hs;
    }

    public String getList_date(){
        return list_date;
    }

    public void setList_date(String list_date){
        this.list_date=list_date;
    }

    public String getList_status(){
        return list_status;
    }

    public void setList_status(String list_status){
        this.list_status=list_status;
    }

    public String getMarket(){
        return market;
    }

    public void setMarket(String market){
        this.market=market;
    }



    public void setName(String name){
        this.name=name;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String symbol){
        this.symbol=symbol;
    }

    public String getTs_code(){
        return ts_code;
    }

    public void setTs_code(String ts_code){
        this.ts_code=ts_code;
    }

    public String getLevel3_key() {
        return level3_key;
    }

    public void setLevel3_key(String level3_key) {
        this.level3_key = level3_key;
    }

    public String getLevel3_value() {
        return level3_value;
    }

    public void setLevel3_value(String level3_value) {
        this.level3_value = level3_value;
    }





    public int getShowindex() {
        return showindex;
    }

    public void setShowindex(int showindex) {
        this.showindex = showindex;
    }

    public double getMeiRiZhiBiao_turnover_rate_f() {
        return MeiRiZhiBiao_turnover_rate_f;
    }

    public void setMeiRiZhiBiao_turnover_rate_f(double meiRiZhiBiao_turnover_rate_f) {
        MeiRiZhiBiao_turnover_rate_f = meiRiZhiBiao_turnover_rate_f;
    }

    public float getMeiRiZhiBiao_price() {
        return MeiRiZhiBiao_price;
    }

    public void setMeiRiZhiBiao_price(float meiRiZhiBiao_price) {
        MeiRiZhiBiao_price = meiRiZhiBiao_price;
    }

    public float getMeiRiZhiBiao_total_mv() {
        return MeiRiZhiBiao_total_mv;
    }

    public void setMeiRiZhiBiao_total_mv(float meiRiZhiBiao_total_mv) {
        MeiRiZhiBiao_total_mv = meiRiZhiBiao_total_mv;
    }

    public float getMeiRiZhiBiao_circ_mv() {
        return MeiRiZhiBiao_circ_mv;
    }

    public void setMeiRiZhiBiao_circ_mv(float meiRiZhiBiao_circ_mv) {
        MeiRiZhiBiao_circ_mv = meiRiZhiBiao_circ_mv;
    }

    public double getRiXianXingQingvShiJianWeiXu_change() {
        return RiXianXingQingvShiJianWeiXu_change;
    }

    public void setRiXianXingQingvShiJianWeiXu_change(double riXianXingQingvShiJianWeiXu_change) {
        RiXianXingQingvShiJianWeiXu_change = riXianXingQingvShiJianWeiXu_change;
    }

    public double getRiXianXingQingvShiJianWeiXu_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_pct_chg(double riXianXingQingvShiJianWeiXu_pct_chg) {
        RiXianXingQingvShiJianWeiXu_pct_chg = riXianXingQingvShiJianWeiXu_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_amount() {
        return RiXianXingQingvShiJianWeiXu_amount;
    }

    public void setRiXianXingQingvShiJianWeiXu_amount(double riXianXingQingvShiJianWeiXu_amount) {
        RiXianXingQingvShiJianWeiXu_amount = riXianXingQingvShiJianWeiXu_amount;
    }



}
