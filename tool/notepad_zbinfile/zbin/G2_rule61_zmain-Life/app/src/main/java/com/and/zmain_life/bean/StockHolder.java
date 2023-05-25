package com.and.zmain_life.bean;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.and.zmain_life.adapter.StockSimulateTradeAdapter;
import com.and.zmain_life.application.ZMainApplication;
import com.and.zmain_life.node.NodeImpl;
import com.and.zmain_life.node.StockNodeShareInfo;
import com.and.zmain_life.stock_node.Stock_Node;
import com.and.zmain_life.stock_node.Stock_NodeImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.and.zmain_life.stock_sheet_bean.DaZongJiaoYi;
import com.and.zmain_life.stock_sheet_bean.GangGuTongMeiRiChengJiaoTongJi;
import com.and.zmain_life.stock_sheet_bean.GangGuTongShiDaChengJiaoGu;
import com.and.zmain_life.stock_sheet_bean.GeGuZiJinLiuXiang;
import com.and.zmain_life.stock_sheet_bean.GuDongZengJianChi;
import com.and.zmain_life.stock_sheet_bean.GuPiaoHuiGou;
import com.and.zmain_life.stock_sheet_bean.HuShenGangGuTongChiGuMingXi;
import com.and.zmain_life.stock_sheet_bean.HuShenGangTongZiJinLiuXiang;
import com.and.zmain_life.stock_sheet_bean.HuShenGuTongShiDaChengJiaoGu;
import com.and.zmain_life.stock_sheet_bean.LongHuBangJiGouMingXi;
import com.and.zmain_life.stock_sheet_bean.LongHuBangMeiRiMingXi;
import com.and.zmain_life.stock_sheet_bean.MeiRiTingFuPaiXinXi;
import com.and.zmain_life.stock_sheet_bean.MeiRiZhangDieTingJiaGe;
import com.and.zmain_life.stock_sheet_bean.MeiRiZhiBiao;
import com.and.zmain_life.stock_sheet_bean.RiXianXingQingvShiJianWeiXu;
import com.and.zmain_life.stock_sheet_bean.RongZiRongQuanJiaoYiHuiZong;
import com.and.zmain_life.stock_sheet_bean.RongZiRongQuanJiaoYiMingXi;
import com.and.zmain_life.stock_sheet_bean.XianShouGuJieJin;
import com.and.zmain_life.stock_sheet_bean.XinGuLieBiaoIPO;
import com.and.zmain_life.stock_sheet_bean.YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi;
import com.and.zmain_life.stock_sheet_bean.ZhouXianXingQingvShiJianWeiXu;
import com.and.zmain_life.stock_sheet_bean.ZuiJinYueZhangDieTingTongJi;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.google.gson.Gson;


public class StockHolder {


    public static File Sdcard_File;
    public static File ZMain_File;


    // 21 22年上证指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sh000001.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sh000001.js?visitDstTime=1

// 21 22年创业指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399006.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sz399006.js?visitDstTime=1

// 21 22年深证指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399001.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sz399001.js?visitDstTime=1

// 21 22 科创板指数
//https://data.gtimg.cn/flashdata/hushen/daily/22/sh000688.js?visitDstTime=1
//https://data.gtimg.cn/flashdata/hushen/daily/21/sh000688.js?visitDstTime=1

    //  399006.SZ   创业板指数   399001.SZ 深证指数     000001.SH 上证指数   000688.SH  科创板指数
    // 上证指数的返回的 字符串结合
    public static StockSimulate_ZhiShu   kcbzs_zhishu  ;
    public static StockSimulate_ZhiShu cybzs_zhishu;
    public static StockSimulate_ZhiShu shzs_zhishu;
    public static StockSimulate_ZhiShu szzs_zhishu;
    public static   Map<String,StockSimulate_ZhiShu> ZhiShu_TscodeKey_Map;  //


    // 把 Stock_Common_Fragment 中的数据 剥离 到 StockHolder
    public static Stock_NodeImpl  Stock_NodeImpl_Root;
    // 是否首次初始化json  true--首次初始化完成    false-首次初始化未完成
    public static boolean isFirstInitDailyJson_FinishFlag = false;
    // 是否第二次进行 json 的 数据化  为了覆盖 之前的json   false--还未完成第二次初始化  true-完成第二次初始化
    public static boolean isSecondInitDailyJson_FinishFlag = false;
    // __________________  Stock__Begin
    //    static String J0_GuPiaoLieBiao_Path = zbinPath+File.separator+"J0_股票列表.xlsx";
//    static String J0_JiaoYiRiQi_Path = zbinPath+File.separator+"J0_交易日历.xlsx";
    public  static boolean isStockPage_ChangeTo_AnotherPage = false ;

    public static File mStock_Root_File;  // /sdcard/zmain/stock/ 路径
    public static String J0_JiaoYiRiQi_Path;  // /sdcard/zmain/stock/J0_交易日历.xlsx  路径
    public static String J0_GuPiaoLieBiao_Path;  // /sdcard/zmain/stock/J0_股票列表.xlsx  路径  只有这一个文件
    public static File J0_GuPiaoLieBiao_JSON_File;  // /sdcard/zmain/stock/gupiaoliebiao.json  路径
    public static File J0_GuPiaoLieBiao_Path_File;  //   = new File( J0_GuPiaoLieBiao_Path);
    public static File J0_JiaoYiRiQi_Path_File  ;  //    = new File( J0_JiaoYiRiQi_Path);

    public static File mAttentionStock_File  ;  //   /sdcard/zmain/stock/_AttentionStock.txt  受关注的股票的文件内容
    public static ArrayList<String> mAttentionStock_OnlyNumTS_List ;  // 在关注中 ts 的 列表  从 _AttentionStock.txt  中读取到
    public static ArrayList<Stock_NodeImpl> mAttentionStockImpl_List ;  //  收藏列表 Stock_NodeImpl 列表
    public static Stock_NodeImpl AttentionRank_Level1_Stock_NodeImpl;   // 收藏板 的 那个  一级结点


    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";



    public static Stock_NodeImpl  getAttentionStock_From_RootStock(Stock_NodeImpl rootNode){

        Stock_NodeImpl mAttentionNode = null ;

        if(rootNode == null){
            return null;
        }

        if(rootNode.children == null){
            return null;
        }

        for (int i = 0; i < rootNode.children.size(); i++) {
            Stock_NodeImpl level1_node   =  rootNode.children.get(i);
                    if("收藏板".equals(level1_node.name)){

                        return level1_node;
                    }
        }
        return mAttentionNode;

    }


    public static boolean   clearup_attention_stock_operation(){
        boolean clear_up_flag = false;

        mAttentionStock_OnlyNumTS_List.clear();
        mAttentionStockImpl_List.clear();

        if(AttentionRank_Level1_Stock_NodeImpl != null && AttentionRank_Level1_Stock_NodeImpl.children != null){
            AttentionRank_Level1_Stock_NodeImpl.clearChild();

            android.util.Log.i("清空收藏","  AttentionRank_Level1_Stock_NodeImpl.child="+AttentionRank_Level1_Stock_NodeImpl.children );
        }

        writeContentToFile(mAttentionStock_File , "");

        if(mAttentionStock_OnlyNumTS_List.size() == 0){
            clear_up_flag = true;
        }

        android.util.Log.i("清空收藏","  mAttentionStock_OnlyNumTS_List.size() ="+mAttentionStock_OnlyNumTS_List.size() );

        return clear_up_flag;

    }


    public static boolean   attention_stock_operation(String ts_code_numstr , boolean isAdd){

        boolean operation_flag = false;
        // 当前操作的 NodeImpl 是否存在 于 收藏板 中
        AttentionRank_Level1_Stock_NodeImpl  =   getAttentionStock_From_RootStock(Root_StockNodeImpl);
       boolean  isAlreadyExist =  mAttentionStock_OnlyNumTS_List.contains(ts_code_numstr);

       int attention_size_begin =  mAttentionStock_OnlyNumTS_List.size();

       ArrayList<String> tscode_name_list = new ArrayList<String>();

        android.util.Log.i("Z收藏_Begin","  isAlreadyExist="+isAlreadyExist+"   mAttentionStock_OnlyNumTS_List.size()="+mAttentionStock_OnlyNumTS_List.size()+"  "+Arrays.toString(mAttentionStock_OnlyNumTS_List.toArray()));

        if(isAdd){   //  添加收藏


            android.util.Log.i("添加Z收藏_A","  isAlreadyExist="+isAlreadyExist+"   ");

            Stock_NodeImpl add_node_item =   getStockNodeWithTsCodeNoSHSZ(ts_code_numstr);
            if(add_node_item == null){
                android.util.Log.i("添加Z收藏_A1","  isAlreadyExist="+isAlreadyExist+"   ts_code_numstr="+ts_code_numstr+"   添加的ts-code 找到的 Stock_NodeImpl为空! ");

            }
            if(add_node_item != null && !isAlreadyExist ){   // 当前项不为空 并且 不存在收藏板中 那么执行收藏操作
                mAttentionStock_OnlyNumTS_List.add(ts_code_numstr);
                mAttentionStockImpl_List.add(add_node_item);
                android.util.Log.i("添加Z收藏_B","  isAlreadyExist="+isAlreadyExist+"   add_node_item="+add_node_item.name +"  ts_code_numstr="+ts_code_numstr);

                if(AttentionRank_Level1_Stock_NodeImpl != null  && AttentionRank_Level1_Stock_NodeImpl.children != null && !AttentionRank_Level1_Stock_NodeImpl.children.contains(add_node_item)){


                    AttentionRank_Level1_Stock_NodeImpl.addChild(add_node_item);
                    AttentionRank_Level1_Stock_NodeImpl.children.sort(StockHolder.pricedown_StockComparator);

                    android.util.Log.i("添加Z收藏_C","  isAlreadyExist="+isAlreadyExist+"   add_node_item="+add_node_item.name);


                }

            }



        }else{    //  删除收藏

            android.util.Log.i("删除Z收藏_A","  isAlreadyExist="+isAlreadyExist+"   ts_code_numstr="+ts_code_numstr);

            if(isAlreadyExist){   // 存在 那么 删除 这个 item 项


                Stock_NodeImpl delete_node_item =   getStockNodeWithTsCodeNoSHSZ(ts_code_numstr);
                android.util.Log.i("删除Z收藏_B","  isAlreadyExist="+isAlreadyExist+"   delete_node_item="+ delete_node_item +" AttentionRank_Level1_Stock_NodeImpl.children ="+AttentionRank_Level1_Stock_NodeImpl.children +"  AttentionRank_Level1_Stock_NodeImpl="+AttentionRank_Level1_Stock_NodeImpl.name);
                mAttentionStock_OnlyNumTS_List.remove(ts_code_numstr);
                mAttentionStockImpl_List.remove(delete_node_item);

                if(AttentionRank_Level1_Stock_NodeImpl != null && AttentionRank_Level1_Stock_NodeImpl.children != null){
                    AttentionRank_Level1_Stock_NodeImpl.deleteChild(delete_node_item);
                    android.util.Log.i("删除Z收藏_C","  isAlreadyExist="+isAlreadyExist+"   AttentionRank_Level1_Stock_NodeImpl="+AttentionRank_Level1_Stock_NodeImpl.name);

                    android.util.Log.i("删除Z收藏","  isAlreadyExist="+isAlreadyExist+"   delete_node_item="+delete_node_item.name);
                }
            }

        }

        // 为什么 有的 不显示
        android.util.Log.i("Z收藏_End_A","  isAlreadyExist="+isAlreadyExist+"   mAttentionStock_OnlyNumTS_List.size()="+mAttentionStock_OnlyNumTS_List.size()+"  "+"  AttentionRank_Level1_Stock_NodeImpl="+AttentionRank_Level1_Stock_NodeImpl);

        for (int i = 0; i < mAttentionStockImpl_List.size(); i++) {
            Stock_NodeImpl item = mAttentionStockImpl_List.get(i);
            tscode_name_list.add(item.symbol+"_"+item.name);
        }

        android.util.Log.i("Z收藏_End_B","size="+mAttentionStock_OnlyNumTS_List.size()+ "  mAttentionStock_OnlyNumTS_List="+Arrays.toString(mAttentionStock_OnlyNumTS_List.toArray()) +"  tscode_name_list="+Arrays.toString(tscode_name_list.toArray()) );


        int attention_size_end =  mAttentionStock_OnlyNumTS_List.size();

        if(attention_size_end == attention_size_begin){
            operation_flag = false;   // 没有执行成功
        }else{
            operation_flag = true;   // 执行成功
            tscode_name_list.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            writeContentToFile(mAttentionStock_File , tscode_name_list);   // 写入文件  保存 这次的 收藏的数据
        }


        android.util.Log.i("Z收藏_End_C", "attention_size_begin="+attention_size_begin +"   attention_size_end="+attention_size_end  +"   operation_flag="+operation_flag);

        return operation_flag;


    }

    public static String   clearChar_clearChinese(String rawContent){
        String original_str = new String(rawContent);

//        String original_str = clearChinese(original_str);
        String digits_tscode = original_str.replaceAll("[^0-9]", "");

        digits_tscode = digits_tscode.replace(" ","");
        digits_tscode = digits_tscode.replace(".","");
        digits_tscode = digits_tscode.replace("-","");
        return digits_tscode;
    }

    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }

    public static void   init_Attention_Stock_List(File mAttentonFile){
        // 读取 收藏文件 过滤出 ts


        ArrayList<String> mAttentonRawContentList = readFile(mAttentonFile);


        for (int i = 0; i < mAttentonRawContentList.size() ; i++) {
            String rawLineStr = mAttentonRawContentList.get(i);

            if(rawLineStr.length() < 6 ){   // 000001  ts——code 都是 6位的

                continue;
            }


            String onlyDigitalTScode = clearChar_clearChinese(rawLineStr).trim();

            if(onlyDigitalTScode.length() != 6){   // ts_code 不等于6位那么 舍弃
                continue;
            }

            if(!mAttentionStock_OnlyNumTS_List.contains(onlyDigitalTScode)){
                mAttentionStock_OnlyNumTS_List.add(onlyDigitalTScode);
            }

        }

        for (int i = 0; i < mAttentionStock_OnlyNumTS_List.size() ; i++) {
            String ts_code = mAttentionStock_OnlyNumTS_List.get(i);
            print("股票收藏列表["+(i+1)+"]["+mAttentionStock_OnlyNumTS_List.size()+"] = "+ mAttentionStock_OnlyNumTS_List.get(i));

            Stock_NodeImpl matchNodeImpl =  getStockNodeWithTsCodeNoSHSZ(ts_code);
            if(matchNodeImpl != null){
                mAttentionStockImpl_List.add(matchNodeImpl);
            }
        }

        for (int i = 0; i < mAttentionStockImpl_List.size() ; i++) {

            print("股票Impl列表["+(i+1)+"]["+mAttentionStockImpl_List.size()+"] = "+ mAttentionStockImpl_List.get(i));
        }
    }




    public static ArrayList<File> day_timestamp_xlsxFileArr;     // 以 day_20200202.xlsx 这样形式命名的文件的集合
    public static File  day_last_xlsxFile ; // 时间上最新的 那个  day_ 开头的 时间戳的 文件
    public static String day_lastxlsx_timestamp;   // 时间上最新的那个文件的 时间戳  要显示的就是那个文件的内容
    public static String day_lastxlsx_Year;
    public static String day_lastxlsx_Month;
    public static String day_lastxlsx_Day;


    public static ArrayList<File> day_timestamp_jsonFileArr;     // 以 day_2021_1105.json 这样形式命名的文件的集合
    // 可以手动改变     如果 改变了   说明 用户有 手动设置了新的 json 文件
    public static File  day_last_jsonFile ; // 时间上最新的 那个  day_ 开头的 时间戳的 文件   可以手动改变
    public static File day_alway_last_json_File;   // 总是 时间上 最新的 那个 json  文件
    public static String day_lastjson_timestamp;   // 时间上最新的那个文件的 时间戳  要显示的就是那个文件的内容
    public static String day_lastjson_Year;
    public static String day_lastjson_Month;
    public static String day_lastjson_Day;

    public static int Stock_Type_Calculate_Size = 50;   // 计算 振幅 换手率 成交量的 数量
    //   Map<int,ArrayList<Stock>> 的 Key 当前表示 默认的所有的Key
    public static int Stock_TYPE_ALL = 0 ;   // 所有股票的 KEY
    public static int Stock_TYPE_UP = Stock_TYPE_ALL + 1 ;   // 所有涨停的股票
    public static int Stock_TYPE_DOWN = Stock_TYPE_ALL + 2 ;   // 所有跌停的股票
    public static int Stock_TYPE_Price_1000_UP = Stock_TYPE_ALL + 3 ;   //   以后想到 再处理吧
    public static int Stock_TYPE_Price_500_1000 = Stock_TYPE_ALL + 4 ;   //
    public static int Stock_TYPE_Price_100_500 = Stock_TYPE_ALL + 5 ;   //
    public static int Stock_TYPE_Price_50_100 = Stock_TYPE_ALL + 6 ;
    public static int Stock_TYPE_Price_10_50 = Stock_TYPE_ALL + 7 ;
    public static int Stock_TYPE_Price_0_10 = Stock_TYPE_ALL + 8 ; // 价格在区间 0-10的股票

    public static int Stock_TYPE_ChangeRate = Stock_TYPE_ALL + 9 ; // 换手率
    public static int Stock_TYPE_BigDeal = Stock_TYPE_ALL + 10 ; // 成交量
    public static int Stock_TYPE_ZhenFu = Stock_TYPE_ALL + 11 ; // 振幅

    public static int Stock_TYPE_ChangeRate_Day3_UP = Stock_TYPE_ALL + 12 ; //  前3天的涨幅 排列
    public static int Stock_TYPE_ChangeRate_Day3_DOWN = Stock_TYPE_ALL + 13 ; //  前3天的涨幅 排列
    public static int Stock_TYPE_ChangeRate_Day5_UP = Stock_TYPE_ALL + 14 ; //   前5天的涨幅 排列
    public static int Stock_TYPE_ChangeRate_Day5_DOWN = Stock_TYPE_ALL + 15 ; //   前5天的涨幅 排列
    public static int Stock_TYPE_ChangeRate_Day10_UP = Stock_TYPE_ALL + 16 ; // 前10天的涨幅 排列
    public static int Stock_TYPE_ChangeRate_Day10_DOWN = Stock_TYPE_ALL + 17 ; // 前10天的涨幅 排列
    public static int Stock_TYPE_ChangeRate_Day30_UP = Stock_TYPE_ALL + 18 ; // 前10天的涨幅 排列
    public static int Stock_TYPE_ChangeRate_Day30_DOWN = Stock_TYPE_ALL + 19 ; // 前10天的涨幅 排列
    public static int Stock_TYPE_ChangeRate_Day90_UP = Stock_TYPE_ALL + 20 ;  //  前宇哥季度的 排列
    public static int Stock_TYPE_ChangeRate_Day90_DOWN = Stock_TYPE_ALL + 21 ;  //  前宇哥季度的 排列
    public static int Stock_TYPE_ChangeRate_DayYear_UP= Stock_TYPE_ALL + 22 ;  //  前年度的 排列
    public static int Stock_TYPE_ChangeRate_DayYear_DOWN= Stock_TYPE_ALL + 23 ;  //  前年度的 排列


    public static int Stock_TYPE_AllWorth = Stock_TYPE_ALL + 100 ; // 总市值

    //  // 股票列表中的 StockItem  符合各种条件的ArrayList<Stock_NodeImpl> 列表的集合
    public static LinkedHashMap<Integer,ArrayList<Stock_NodeImpl>> mStock_NodeImplArr_Type_Map;




    // 000002.SH 这样的
    public static ArrayList<String> TScode_List = new ArrayList<String>();

    public static File mStock_Log_File;  // /sdcard/zmain/stock/ 路径
    public static File[] mStock_file_list; // /sdcard/zmain/stock/ 路径  下的 所有 的 子文件
    public static File mStock_Day_DirFile;  // /sdcard/zmain/stock/day/ 目录   专门保存每日更新的数据   day_时间戳.xlsx
    public static File mStock_LastFile_InDay_File;   // /sdcard/zmain/stock/day/day_20210616.xlsx  最新的那一个文件
    public static File mStock_RootNode_ByMarket_JsonFile;  // /sdcard/zmain/stock/Market_Root_StockNode.json


    public static ArrayList<Stock_NodeImpl> gupiaoliebiao_StockList;   // 股票列表中的 StockItem

    public static ArrayList<Comparator<Stock_NodeImpl>> Stock_NodeImpl_ComparatorList;     //  排序的列表集合
    public static Map<Integer,Comparator<Stock_NodeImpl>>  Stock_NodeImpl_Comparator_Map ;
    public static ArrayList<String> Stock_NodeImpl_ComparatorTip_List;     //  排序的列表集合 提示 语句的集合
    public static int Stock_NodeImpl_Comparator_Selected_Index = 0 ;   // 默认选中的 排序方式
    public static LinkedHashMap<String, StockNodeShareInfo> tscode_stockShareInfo_Map;   // 股票列表中的 StockItem

    // zukgitxxxxxxxxxxxx

    // 放置  形成 stock_node 根节点 1结点  2 结点  3结点的 json字符串
    public static String GuPiaoLieBiao_NodeJson_Str;

    // 读取到的   J0_股票列表.xlsx  形成的   stock_nodeimpl  列表的 jsonArr 形式的 json字符串
    public static   String GuPiaoLieBiao_ArrJson_Str   ;


    public static boolean isInSimulateFragment;  // 标记当前是否在 Simulate 页面

    public  static  transient Stock_NodeImpl Root_StockNodeImpl = new Stock_NodeImpl(0, -1, "All",0);

    // 关闭窗口切换fragment时  windows是否是显示的   如果是显示的 切换回时则显示
    public  static  boolean isShowWindows_For_CommonFragment_When_Dismiss = false;


    static{   // 静态 执行方法


        init_Stock_NodeImpl_ComparatorList();
        mAttentionStock_OnlyNumTS_List = new  ArrayList<String>();
        mAttentionStockImpl_List = new  ArrayList<Stock_NodeImpl>();
    }


    static   void init_NodeImplArr_Type_Map(){
        mStock_NodeImplArr_Type_Map = new LinkedHashMap<Integer,ArrayList<Stock_NodeImpl>>(50);

        if(gupiaoliebiao_StockList != null){

            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ALL,gupiaoliebiao_StockList);


            ArrayList<Stock_NodeImpl> all_up_stock = new  ArrayList<Stock_NodeImpl>();
            Set<String> tscodeSet_up = new HashSet<String>();

            ArrayList<Stock_NodeImpl> all_down_stock = new  ArrayList<Stock_NodeImpl>();
            Set<String> tscodeSet_down = new HashSet<String>();


            for (int i = 0; i < gupiaoliebiao_StockList.size(); i++) {
                Stock_NodeImpl stockNode =     gupiaoliebiao_StockList.get(i);
                String cname = stockNode.getName();
                String tssCode = stockNode.ts_code;

                double pct_change = stockNode.getRiXianXingQingvShiJianWeiXu_pct_chg();

                if(pct_change > 9.9){

                    if(!tscodeSet_up.contains(tssCode)){
                        tscodeSet_up.add(tssCode);
                        all_up_stock.add(stockNode);
                    }

                }else if(pct_change < -9.0){
                    if(!tscodeSet_down.contains(tssCode)){
                        tscodeSet_down.add(tssCode);
                        all_down_stock.add(stockNode);
                    }

                }

//                print("init_NodeImplArr_Type_Map["+i+"] 【pct_change="+pct_change+"】【"+cname+":"+stockNode+"】");


            }

            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_UP,all_up_stock);
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_DOWN,all_down_stock);

            print("init_NodeImplArr_Type_Map   gupiaoliebiao_StockList.size()="+gupiaoliebiao_StockList.size());
            print("init_NodeImplArr_Type_Map   all_up_stock.size()="+all_up_stock.size());
            print("init_NodeImplArr_Type_Map   all_down_stock.size()="+all_down_stock.size());




            // 换手率     public   double MeiRiZhiBiao_turnover_rate_f;  // 自由股换手率
            ArrayList<Stock_NodeImpl> changerate_stock = new  ArrayList<Stock_NodeImpl>();

            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.MeiRiZhiBiao_turnover_rate_f < o2.MeiRiZhiBiao_turnover_rate_f){
                        return 1 ;
                    }else if(o1.MeiRiZhiBiao_turnover_rate_f > o2.MeiRiZhiBiao_turnover_rate_f){
                        return -1 ;
                    }  else  if(o1.MeiRiZhiBiao_turnover_rate_f == o2.MeiRiZhiBiao_turnover_rate_f){
                        return (o1.MeiRiZhiBiao_turnover_rate_f > o2.MeiRiZhiBiao_turnover_rate_f) ? -1 : ((o1.MeiRiZhiBiao_turnover_rate_f == o2.MeiRiZhiBiao_turnover_rate_f) ? 0 : 1) ;
                    }

                    return (o1.MeiRiZhiBiao_turnover_rate_f > o2.MeiRiZhiBiao_turnover_rate_f) ? -1 : ((o1.MeiRiZhiBiao_turnover_rate_f == o2.MeiRiZhiBiao_turnover_rate_f) ? 0 : 1) ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                changerate_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ changerate_stock["+changerate_stock.size()+"] = "+gupiaoliebiao_StockList.get(i));

            }


            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate,changerate_stock);


//            public   double RiXianXingQingvShiJianWeiXu_amount;     //  日线行情 成交额
            ArrayList<Stock_NodeImpl> bigdeal_stock = new  ArrayList<Stock_NodeImpl>();

            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {


                    if(o1.RiXianXingQingvShiJianWeiXu_amount > o2.RiXianXingQingvShiJianWeiXu_amount){
                        return -1 ;
                    }else     if(o1.RiXianXingQingvShiJianWeiXu_amount < o2.RiXianXingQingvShiJianWeiXu_amount){
                        return 1 ;
                    } else     if(o1.RiXianXingQingvShiJianWeiXu_amount == o2.RiXianXingQingvShiJianWeiXu_amount){
                        return 0 ;
                    }


                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                bigdeal_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ bigdeal_stock["+bigdeal_stock.size()+"] = "+gupiaoliebiao_StockList.get(i));

            }


            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_BigDeal,bigdeal_stock);



//            public   double RiXianXingQingvShiJianWeiXu_high;  // 最高价
//            public   double RiXianXingQingvShiJianWeiXu_low;   // 最低价    //  用于计算振幅
            ArrayList<Stock_NodeImpl> zhenfu_stock = new  ArrayList<Stock_NodeImpl>();


// Comparison method violates its general contract!
            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
                    if(o1.price == 0 && o2.price != 0 ){

                        return 1;
                    }
                    if(o1.price != 0 && o2.price == 0 ){

                        return -1;
                    }

                    if(o1.price == 0 && o2.price == 0 ){

                        return 0;
                    }


                    double o1_zhenfu = (o1.RiXianXingQingvShiJianWeiXu_high - o1.RiXianXingQingvShiJianWeiXu_low)/o1.price;
                    double o2_zhenfu = (o2.RiXianXingQingvShiJianWeiXu_high - o2.RiXianXingQingvShiJianWeiXu_low)/o2.price;


                    if(o1_zhenfu > o2_zhenfu){
                        return -1 ;
                    } else         if(o1_zhenfu < o2_zhenfu){
                        return 1 ;
                    }else   if(o1_zhenfu == o2_zhenfu){
                        return 0 ;
                    }





                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                zhenfu_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ zhenfu_stock["+zhenfu_stock.size()+"] = "+gupiaoliebiao_StockList.get(i));
            }


            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ZhenFu,zhenfu_stock);


            print(" ZukgitZ changerate_stock.size()="+changerate_stock.size());
            print(" ZukgitZ bigdeal_stock.size()="+bigdeal_stock.size());
            print(" ZukgitZ zhenfu_stock.size()="+zhenfu_stock.size());


// Day3_Up_Comparison  Begin ========================
            ArrayList<Stock_NodeImpl> day3_up_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day3_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day3_pct_chg()){
                        return -1 ;
                    } else                     if(o1.getRiXianXingQingvShiJianWeiXu_day3_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day3_pct_chg()){
                        return 1 ;
                    }else    if(o1.getRiXianXingQingvShiJianWeiXu_day3_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day3_pct_chg()){
                        return 0 ;
                    }





                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day3_up_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day3_up_pct_stock["+day3_up_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day3_up_pct_stock["+day3_up_pct_stock.size()+"] = ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day3_UP,day3_up_pct_stock);

// Day3_Down_Comparison  Begin ========================

            ArrayList<Stock_NodeImpl> day3_down_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day3_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day3_pct_chg()){
                        return -1 ;
                    }else                     if(o1.getRiXianXingQingvShiJianWeiXu_day3_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day3_pct_chg()){
                        return 1 ;
                    }else        if(o1.getRiXianXingQingvShiJianWeiXu_day3_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day3_pct_chg()){
                        return 0 ;
                    }




                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day3_down_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day3_down_pct_stock["+day3_down_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day3_down_pct_stock["+day3_down_pct_stock.size()+"]"  );

            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day3_DOWN,day3_down_pct_stock);




// Day5_Up_Comparison  Begin ========================
            ArrayList<Stock_NodeImpl> day5_up_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day5_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day5_pct_chg()){
                        return -1 ;
                    }else                     if(o1.getRiXianXingQingvShiJianWeiXu_day5_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day5_pct_chg()){
                        return 1 ;
                    }else     if(o1.getRiXianXingQingvShiJianWeiXu_day5_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day5_pct_chg()){
                        return 0 ;
                    }





                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day5_up_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day5_up_pct_stock["+day5_up_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day5_up_pct_stock["+day5_up_pct_stock.size()+"]  ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day5_UP,day5_up_pct_stock);

// Day5_Down_Comparison  Begin ========================

            ArrayList<Stock_NodeImpl> day5_down_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day5_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day5_pct_chg()){
                        return -1 ;
                    }else  if(o1.getRiXianXingQingvShiJianWeiXu_day5_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day5_pct_chg()){
                        return 0 ;
                    }else    if(o1.getRiXianXingQingvShiJianWeiXu_day5_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day5_pct_chg()){
                        return 1 ;
                    }

                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day5_down_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day5_down_pct_stock["+day5_down_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day5_down_pct_stock["+day5_down_pct_stock.size()+"] = ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day5_DOWN,day5_down_pct_stock);




// Day10_Up_Comparison  Begin ========================
            ArrayList<Stock_NodeImpl> day10_up_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day10_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day10_pct_chg()){
                        return -1 ;
                    } else                     if(o1.getRiXianXingQingvShiJianWeiXu_day10_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day10_pct_chg()){
                        return 1 ;
                    } else   if(o1.getRiXianXingQingvShiJianWeiXu_day10_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day10_pct_chg()){
                        return 0 ;
                    }





                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day10_up_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day10_up_pct_stock["+day10_up_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day10_up_pct_stock["+day10_up_pct_stock.size()+"] = ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day10_UP,day10_up_pct_stock);

// Day10_Down_Comparison  Begin ========================

            ArrayList<Stock_NodeImpl> day10_down_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day10_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day10_pct_chg()){
                        return -1 ;
                    } else                     if(o1.getRiXianXingQingvShiJianWeiXu_day10_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day10_pct_chg()){
                        return 1 ;
                    }else  if(o1.getRiXianXingQingvShiJianWeiXu_day10_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day10_pct_chg()){
                        return 0 ;
                    }







                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day10_down_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day10_down_pct_stock["+day10_down_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day10_down_pct_stock["+day10_down_pct_stock.size()+"] = ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day10_DOWN,day10_down_pct_stock);



// Day30_Up_Comparison  Begin ========================
            ArrayList<Stock_NodeImpl> day30_up_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day30_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day30_pct_chg()){
                        return -1 ;
                    }else                     if(o1.getRiXianXingQingvShiJianWeiXu_day30_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day30_pct_chg()){
                        return 1 ;
                    }else  if(o1.getRiXianXingQingvShiJianWeiXu_day30_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day30_pct_chg()){
                        return 0 ;
                    }

                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day30_up_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day30_up_pct_stock["+day30_up_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day30_up_pct_stock["+day30_up_pct_stock.size()+"] = ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day30_UP,day30_up_pct_stock);

// Day30_Down_Comparison  Begin ========================

            ArrayList<Stock_NodeImpl> day30_down_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
                    if(o1.getRiXianXingQingvShiJianWeiXu_day30_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day30_pct_chg()){
                        return -1 ;
                    }else     if(o1.getRiXianXingQingvShiJianWeiXu_day30_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day30_pct_chg()){
                        return 1 ;
                    }else    if(o1.getRiXianXingQingvShiJianWeiXu_day30_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day30_pct_chg()){
                        return 0 ;
                    }
                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day30_down_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day30_down_pct_stock["+day30_down_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day30_down_pct_stock["+day30_down_pct_stock.size()+"] = ");

            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day30_DOWN,day30_down_pct_stock);



// Day90_Up_Comparison  Begin ========================
            ArrayList<Stock_NodeImpl> day90_up_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day90_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day90_pct_chg()){
                        return 1 ;
                    }else   if(o1.getRiXianXingQingvShiJianWeiXu_day90_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day90_pct_chg()){
                        return -1 ;
                    } else  if(o1.getRiXianXingQingvShiJianWeiXu_day90_pct_chg()  == o2.getRiXianXingQingvShiJianWeiXu_day90_pct_chg())  {
                        return 0 ;
                    }
                    return 0 ;
                 }

            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day90_up_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day90_up_pct_stock["+day90_up_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day90_up_pct_stock["+day90_up_pct_stock.size()+"] = ");

            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day90_UP,day90_up_pct_stock);

// Day90_Down_Comparison  Begin ========================

            ArrayList<Stock_NodeImpl> day90_down_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_day90_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_day90_pct_chg()){
                        return 1 ;
                    }else    if(o1.getRiXianXingQingvShiJianWeiXu_day90_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_day90_pct_chg()){
                        return -1 ;
                    }else if (o1.getRiXianXingQingvShiJianWeiXu_day90_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_day90_pct_chg()){
                        return 0 ;
                    }




                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                day90_down_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ day90_down_pct_stock["+day90_down_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ day90_down_pct_stock["+day90_down_pct_stock.size()+"] = ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_Day90_DOWN,day90_down_pct_stock);





// Dayyear_Up_Comparison  Begin ========================
            ArrayList<Stock_NodeImpl> dayyear_up_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg()){
                        return -1 ;
                    } else  if(o1.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg()){
                        return 0 ;
                    }else  if(o1.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg()){
                        return 1 ;
                    }

                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                dayyear_up_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ dayyear_up_pct_stock["+dayyear_up_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ dayyear_up_pct_stock["+dayyear_up_pct_stock.size()+"] = ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_DayYear_UP,dayyear_up_pct_stock);

// Dayyear_Down_Comparison  Begin ========================

            ArrayList<Stock_NodeImpl> dayyear_down_pct_stock = new  ArrayList<Stock_NodeImpl>();


            gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

                    if(o1.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg() < o2.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg()){
                        return -1 ;
                    } else          if(o1.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg() > o2.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg()){
                        return 1 ;
                    }else  if(o1.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg() == o2.getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg()){
                        return 0 ;
                    }


                    return 0 ;
                }
            });

            for (int i = 0; i < Stock_Type_Calculate_Size ; i++) {
                dayyear_down_pct_stock.add(gupiaoliebiao_StockList.get(i));
                print(" ZukgitZ dayyear_down_pct_stock["+dayyear_down_pct_stock.size()+"] = "+gupiaoliebiao_StockList.get(i).toString_Node());
            }
            print(" ZukgitZ dayyear_down_pct_stock["+dayyear_down_pct_stock.size()+"] = ");
            mStock_NodeImplArr_Type_Map.put(Stock_TYPE_ChangeRate_DayYear_DOWN,dayyear_down_pct_stock);


        }else {

            print("init_NodeImplArr_Type_Map   gupiaoliebiao_StockList == null  ");

        }


    }
    static  public   Stock_NodeImpl getStockNodeWithTsCodeNoSHSZ(String tsCode){
        Stock_NodeImpl szStock =    getStockNodeWithTsCode(tsCode+".SZ");
        if(szStock == null){
            szStock =    getStockNodeWithTsCode(tsCode+".SH");

        }

        return szStock;
    }

    // 依据 TS_Code 获取到  Stock_NodeIpl
    static  public   Stock_NodeImpl getStockNodeWithTsCode(String tsCode){
        Stock_NodeImpl matchStockNode = null;
        if(gupiaoliebiao_StockList == null || gupiaoliebiao_StockList.size() == 0 || tsCode == null || "".equals(tsCode)){
            return matchStockNode;
        }
        for (int i = 0; i < gupiaoliebiao_StockList.size() ; i++) {
            Stock_NodeImpl stockItem = gupiaoliebiao_StockList.get(i);
            String item_ts_code = stockItem.ts_code;
            if(item_ts_code != null && tsCode.equals(item_ts_code)){
                return stockItem;
            }
        }
        return matchStockNode;
    }


    static  public Stock_NodeImpl getStockNodeWithStockName(String stockName ){
        Stock_NodeImpl matchStockNode = null;
        if(gupiaoliebiao_StockList == null || gupiaoliebiao_StockList.size() == 0 || stockName == null || "".equals(stockName)){
            return matchStockNode;
        }
        for (int i = 0; i < gupiaoliebiao_StockList.size() ; i++) {
            Stock_NodeImpl stockItem = gupiaoliebiao_StockList.get(i);
            String item_stockname = stockItem.name;
            if(item_stockname != null && stockName.equals(item_stockname)){
                return stockItem;
            }
        }

        return matchStockNode;
    }


    // 一开始不显示 详细信息   只作用一次
    public   static   boolean isAlredyShowSimulateDetailWhenFirstOpen = false ;

    // 当前 全打开 全关闭的状态值  来回切换
    public   static   boolean isShowForSimulateDetail = false ;


    public  static StockSimulate_BankBean  mBankBean;



    // 沪市
// 深市
// 创业板
// 科创板
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
// 沪深港持股明细榜
// 融资融券交易明细榜
// 龙虎榜
// 限售股解禁榜
//  股票回购信息榜
//   大宗交易信息榜
//   近三月股东增减持信息榜
    static String[] StockBlock = new String[]{"All板","收藏板","沪市","科创板","深市","中小板","创业板","IPO新板","ST板","退市板","个股资金流向榜","Day_Holder涨跌板","龙虎榜","Day_Holder沪深股通十大成交股","Month_Holder涨跌停板","大宗交易信息榜","Year_Holder沪深港资金流向榜","沪深港持股明细榜","融资融券交易明细榜","股票回购信息榜","限售股解禁榜","停复牌板","近三月股东增减持信息榜"};

    // zukgitxxxxxxxxxxxxxxx  继续点
    public static   LinkedHashMap<String,ArrayList<Stock_NodeImpl>> level1Name_level2StockImpl_Map;  // 分别 添加 再 分别 sort 再 merge到 统一的 list 中
    public static ArrayList<Stock_NodeImpl> level_1_StockBlock_nodeList;    // 股票板块 列表 level_1
    public static ArrayList<Stock_NodeImpl> level_2_ShortStock_nodeList;   //  股票简明信息列表_ level_2

    public static ArrayList<Stock_NodeImpl> level_3_DetaiLStock_nodeList;       // 单独的股票详细信息列表 level_3

    public static Stock_NodeImpl selected_level1_stocknode;
    public static Stock_NodeImpl selected_level2_stocknode;   // 被选中的 level2_结点  决定了显示什么node3
    public static Stock_NodeImpl selected_level3_stocknode;  // 显示的具体详情的 node 3的 结点

    // __________________  Stock__End


    // __________________   Stock_Area_Fragment_Begin  __________________


    public  static boolean isAreaPage_ChangeTo_AnotherPage = false ;

    // 是否在 areaPage 中的  第三个项 显示  省份名称?
    public  static int mShowLevel3Area_In_AreaPage_ModeIndex = 0 ;
    public  static int mShowLevel3Area_In_AreaPage_ModeCount = 4 ;

    static String[] StockArea_Arr_Level_1 = new String[]{"All","安徽","北京","重庆","福建","广东","广西","贵州","甘肃","湖南","湖北","河南","河北","海南","黑龙江","江苏","江西","吉林","辽宁","内蒙","宁夏","青海","上海","山东","山西","四川","陕西","天津","台湾","西藏","新疆","云南","浙江"};

    // 第一item是 省份区域_第二item是工业类型 的 Map  组成的 key  例如
    // 北京_房地产 为Key  对应了 一个 ArrayList<Stock_NodeImpl> 列表
    public static   LinkedHashMap<Stock_NodeImpl,ArrayList<Stock_NodeImpl>> level2AreaIndustry_level3StockImplList_Map;
    //  第一列的省份  以及 对应的  符合这个区域的 省份的产业    北京(Key)-----北京_汽车制造
    //  北京(Key)-----【北京_汽车制造-----北京飞机制造-----北京航空】
    public static   LinkedHashMap<Stock_NodeImpl,ArrayList<Stock_NodeImpl>> level1AreaName_level2AreaIndustryStockNode_Map;


    // 省份对应的 股票的 列表 作为 level2  它的 level3 就是 属于这个省份的股票的列表的集合
    public static   LinkedHashMap<Stock_NodeImpl,ArrayList<Stock_NodeImpl>>  areall_level2AreaName_level3StockImplList_Map;




    public static ArrayList<String> allIndustryNameList;  // 所有产业名称的集合  确保唯一
    // 这个产业对应的公司 全国范围内   填充 Aera_All 的 内容用到
    public static   LinkedHashMap<String,ArrayList<Stock_NodeImpl>> mIndustry_StockImplList_Map;


    // 放置  形成 area_node 根节点 1结点  2 结点  3结点的 json字符串
    public static String AreaPage_RootStockNode_Json_Str;

    //  Area 界面的 根节点
    static final transient Stock_NodeImpl AreaPage_RootStockNode_Impl = new Stock_NodeImpl(0, -1, "All",0);


    // area_industry_stock.json
    public static File J0_AreaPage_JSON_File;




    //  遍历  gupiaoliebiao_StockList   股票 列表   找到  area 属于  具体哪个省份的项  第二项为 industry

    public static ArrayList<Stock_NodeImpl> level_1_AreaBlock_nodeList;    //  区域 列表 level_1
    public static ArrayList<Stock_NodeImpl> level_2_AreaIndustry_nodeList;      //  产业列表 level_2
    public static ArrayList<Stock_NodeImpl> level_3_AreaIndustryStock_nodeList;   // 相同区域 相同行业 内的股票的 名称







    // __________________   Stock_Area_Fragment_End  __________________


    // __________________   Simulate_Stock_Fragment_Begin  __________________

    // simulate_stock.json     模拟股票存放的json  用于数据持久化
    public static File Simulate_Stock_JSON_File;



    // 存放 记录 股票图片 不可编辑的 股票类型
    public static File Simulate_Stock_JPG_File;
    public static File[] Simulate_Stock_JPG_FileArr;
    // 从 jpg_stock_port 读取出来的 user 列表
    public static ArrayList<StockSimulate_BankBean.StockSimulate_UserBean> mSimulateImageUserList ;

    // 从 图片读取 stock 出来
    public     static void    initSimulateStockWithImage(){



        int video_file_sum = 0;
        File Sdcard_File = DataHolder.initSD_Root_File();
        mSimulateImageUserList = new ArrayList<StockSimulate_BankBean.StockSimulate_UserBean>();
        File ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Simulate_Stock_JPG_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_stock_port");
        android.util.Log.i("zukgit", "A jpg_stock_port.absPath=" + Simulate_Stock_JPG_File.getAbsolutePath());



        if (Simulate_Stock_JPG_File.exists()) {
            Simulate_Stock_JPG_FileArr = Simulate_Stock_JPG_File.listFiles();
            Simulate_Stock_JPG_FileArr = DataHolder.guolvType(Simulate_Stock_JPG_FileArr,".jpg");

            initImageUserBean(Simulate_Stock_JPG_FileArr,mSimulateImageUserList);

            if( StockHolder.mBankBean == null ){
                StockHolder.mBankBean = new StockSimulate_BankBean();
                StockHolder.mBankBean.setSimulateuserlist(mSimulateImageUserList);


            }else{

                StockHolder.mBankBean.addSimulateuserList(mSimulateImageUserList);
            }



            android.util.Log.i("zukgit", "A Simulate_Stock_JPG_FileArr=" + Simulate_Stock_JPG_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Simulate_Stock_JPG_FileArr=" + Simulate_Stock_JPG_File.exists());
        }




    }

    public     static void    initImageUserBean(File[] imageFileArr , ArrayList<StockSimulate_BankBean.StockSimulate_UserBean> mImageUserList){
        if(imageFileArr == null || imageFileArr.length == 0 ){
            print("jpg_stock_port 下的 jpg  文件为0  无法生成 对应类型的 ImageUserBean");
            return ;
        }

        for (int i = 0; i < imageFileArr.length; i++) {
            File imageFile = imageFileArr[i];
// 读取 图片中的   初始资金  创建日期  股票列表信息 三种类型
            String exifOneStr = getStringOneWordFromExif(imageFile);

            if(exifOneStr  == null || "".equals(exifOneStr)){
                print("jpg_stock_port  imageFile["+imageFile.getAbsolutePath()+"] 无法读取到 exif信息");
                continue;
            }
            String  initMoneyStr = calculStringMiddleForOne(exifOneStr,"【初始资金:","】");
            String createDateStr =  calculStringMiddleForOne(exifOneStr,"【创建日期:","】");
            String mUserTitle =  calculStringMiddleForOne(exifOneStr,"【标题:","】");


            StockSimulate_BankBean.StockSimulate_UserBean userImageBean = new StockSimulate_BankBean.StockSimulate_UserBean();
            userImageBean.setImageUserBean(true);

            userImageBean.setInitsimulatemoney(Double.parseDouble(initMoneyStr));
            userImageBean.setDaytime(createDateStr);
            userImageBean.setUsertitle(mUserTitle);


            ArrayList<String> matchStockStrList =  calculExifStockTagList(exifOneStr);

            for (int j = 0; j < matchStockStrList.size(); j++) {
                String oneStockStr = matchStockStrList.get(j);
                String[]  stockArrTag = oneStockStr.split(":");
                if(stockArrTag == null || stockArrTag.length != 4 ){
                    continue;
                }

                StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean  stockImageBean = new StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean();



                String   tsName =   stockArrTag[0];
                String tsCode =     stockArrTag[1];
                String buyPrice =    stockArrTag[2];
                String buyCount =      stockArrTag[3];
                stockImageBean.setStockname(tsName);
                stockImageBean.setStocktscode(tsCode);
                stockImageBean.setStockkeepcount(Integer.parseInt(buyCount));
                stockImageBean.setStockcostforone(Double.parseDouble(buyPrice));
                stockImageBean.setImageStockBean(true);
                // imageBean 的 日期 和  user的日期保持一致
                stockImageBean.setStockbuytime(userImageBean.getDaytime());
                userImageBean.buyNewStock(stockImageBean);

            }

//            ArrayList<String> stockKeyValueList  = getStringListFromExif(imageFile,"【","】");

            if(userImageBean.getStocklist() != null || userImageBean.getStocklist().size() > 0){
                mImageUserList.add(userImageBean);
            }
            print("jpg_stock_port imageUser initMoneyStr = "+ initMoneyStr);
            print("jpg_stock_port imageUser  createDateStr = "+ createDateStr);
        }


    }

    public static String calculStringMiddleForOne (String rawStr, String preStr , String endStr) {
        String matchStr = null;
        if(!rawStr.contains(preStr)){
            return "";
        }

        String tempStrA =   rawStr.substring( rawStr.indexOf(preStr)+preStr.length());
        print(" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = "+rawStr+"   tempStrA="+tempStrA);
        String tempStrB =    tempStrA.substring(0,tempStrA.indexOf(endStr));
        print(" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = "+rawStr+"   tempStrB="+tempStrB);
        return tempStrB;


    }


    public static ArrayList<String> calculExifStockTagList(String rawStr){
        // 【初始资金:100000】【创建日期:2021.11.11】【福田汽车:600166:3.5:100000】
        ArrayList<String>  stockMatchList = new  ArrayList<String>();


        String[] stockArr = rawStr.split("】");
        for (int i = 0; i < stockArr.length; i++) {
            String tagItem = stockArr[i].trim();
            System.out.println("stockArr["+i+"] = "+ stockArr[i]);
            if(tagItem.startsWith("【初始资金:") || tagItem.startsWith("【创建日期:") || tagItem.startsWith("【标题:")) {
                continue;
            }
            String clearBlank = tagItem.replace("【", "").replace("】", "");
            if(!clearBlank.contains(":")) {
                System.out.println("当前 ImageUserBean 不包含 分隔符:引号");
                continue;
            }
            String[] oneStockArr =  clearBlank.split(":");
            if(oneStockArr == null || oneStockArr.length != 4){
                System.out.println("当前 ImageStockBean 【名称:代码:买入价格:买入数量】 格式不对请检查");
            }


            for (int j = 0; j < oneStockArr.length; j++) {
                System.out.println("oneStockArr["+j+"] = "+ oneStockArr[j]);
            }

            stockMatchList.add(clearBlank);



        }

        return stockMatchList;

    }

/*
    // 对应的文件  已经这个文件中的exif 中的对应的 前缀
 public static String  getStringValueFromExif(File imageFile , String preFixStr ,String endFixStr){
        String mMatchStr = null;



      try {
          print("尝试取到  imageFile"+imageFile.getAbsolutePath()+" 的 exif 信息 ");
          ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());

          String mModel = exif.getAttribute(ExifInterface.TAG_MODEL);
          if(mModel != null)
          mModel = new String(mModel.getBytes(),"UTF-8");

          String mMake = exif.getAttribute(ExifInterface.TAG_MAKE);
          if(mMake != null)
          mMake = new String(mMake.getBytes(),"UTF-8");

          String mArtist = exif.getAttribute(ExifInterface.TAG_ARTIST);
          if(mArtist != null)
          mArtist = new String(mArtist.getBytes(),"UTF-8");

          String mCopyright = exif.getAttribute(ExifInterface.TAG_COPYRIGHT);
          if(mCopyright != null)
          mCopyright = new String(mCopyright.getBytes(),"UTF-8");

          String mDesc = exif.getAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION);
          if(mDesc != null)
          mDesc = new String(mDesc.getBytes(),"UTF-8");

          String mComment = exif.getAttribute(ExifInterface.TAG_USER_COMMENT);
          if(mComment != null)
          mComment = new String(mComment.getBytes(),"UTF-8");

          print("  imageUser 的 exif 信息  mDesc="+mDesc+"  mModel="+mModel+"  mMake="+mMake+" mArtist="+mArtist+"  mCopyright="+mCopyright+"  mComment="+mComment);


          mMatchStr = getOneStr(mModel,mMake,mArtist,mCopyright,mDesc,mComment);
      } catch (IOException e) {
          // TODO Auto-generated catch block
          System.out.println(" imageUser 获取 图片文件 "+imageFile.getAbsolutePath()+" 的 exif 属性 copyright属性失败! ");
          e.printStackTrace();
      }

      if(mMatchStr == null){
          print(" 没有读取到  imageUser 的 exif 信息 ");
          return null;
      }
      //
      print(" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = "+mMatchStr);
      if(!mMatchStr.contains(preFixStr) || !mMatchStr.contains(endFixStr) ){
          return null;
      }

   String tempStrA =   mMatchStr.substring( mMatchStr.indexOf(preFixStr)+preFixStr.length());
      print(" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = "+mMatchStr+"   tempStrA="+tempStrA);
   String tempStrB =    tempStrA.substring(0,tempStrA.indexOf(endFixStr)-endFixStr.length());
      print(" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = "+mMatchStr+"   tempStrB="+tempStrB);


        return tempStrB;


  }*/


    public static String  getStringOneWordFromExif(File imageFile ){
        String mMatchStr = null;

        String mImageDescription_Utf8 = null;
        String mImageMake_Utf8 = null;
        String mImageModel_Utf8 = null;
        String mImageArtist_Utf8 = null;
        String mImageCopyright_Utf8 = null;
        String mPhotoUserComment_Utf8 = null;
        int angel = 0;
        Metadata metadata;

        try {
            metadata = JpegMetadataReader.readMetadata(imageFile);
            metadata.getDirectories();

            // zukgit_directory [Exif IFD0] - Orientation = Right side, top (Rotate 90 CW)
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    // 格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
//							System.out.format("zukgit_directory  [%s] - %s = %s\n", directory.getName(), tag.getTagName(),tag.getDescription());

                    if ("Exif IFD0".equals(directory.getName())) {

                        String mImageDescription = directory.getString(ExifIFD0Directory.TAG_IMAGE_DESCRIPTION);
                        if (mImageDescription != null)
                            mImageDescription_Utf8 = new String(mImageDescription.getBytes(), "UTF-8");

                        String mImageMake = directory.getString(ExifIFD0Directory.TAG_MAKE);
                        if (mImageMake != null)
                            mImageMake_Utf8 = new String(mImageMake.getBytes(), "UTF-8");

                        String mImageModel = directory.getString(ExifIFD0Directory.TAG_MODEL);
                        if (mImageModel != null)
                            mImageModel_Utf8 = new String(mImageModel.getBytes(), "UTF-8");

                        String mImageArtist = directory.getString(ExifIFD0Directory.TAG_ARTIST);
                        if (mImageArtist != null)
                            mImageArtist_Utf8 = new String(mImageArtist.getBytes(), "UTF-8");

                        String mImageCopyright = directory.getString(ExifIFD0Directory.TAG_COPYRIGHT);
                        if (mImageCopyright != null)
                            mImageCopyright_Utf8 = new String(mImageCopyright.getBytes(), "UTF-8");

//								System.out.println("XXmImageDescription=["+mImageDescription+"]  Utf8["+mImageDescription_Utf8+"]");
//								System.out.println("XXmImageMake=["+mImageMake+"]  Utf8["+mImageMake_Utf8+"]");
//								System.out.println("XXmImageModel=["+mImageModel+"]  Utf8["+mImageModel_Utf8+"]");
//								System.out.println("XXmImageArtist=["+mImageArtist+"]  Utf8["+mImageArtist_Utf8+"]");
//								System.out.println("XXmImageCopyright=["+mImageCopyright+"]  Utf8["+mImageCopyright_Utf8+"]");

                    }

                    if ("Exif SubIFD".equals(directory.getName())) {

                        if ("User Comment".equals(tag.getTagName())) {
                            String mPhotoUserComment = tag.getDescription();
//							System.out.println("AZ_User_Comment=["+tag.getDescription()+"]");
                            if (mPhotoUserComment != null)
                                mPhotoUserComment_Utf8 = new String(mPhotoUserComment.getBytes(), "utf-8");
//							System.out.println("AZXXmPhotoUserComment=["+mPhotoUserComment+"]   mPhotoUserComment_Utf8=["+mPhotoUserComment_Utf8+"]" );

                        }

                    }
                }

            }

            mMatchStr = getOneStr(mImageModel_Utf8,mImageMake_Utf8,mImageArtist_Utf8,mImageCopyright_Utf8,mImageDescription_Utf8,mPhotoUserComment_Utf8);

        } catch (JpegProcessingException e) {
            e.printStackTrace();
            System.out.println("JpegProcessingException  异常事件发生 ");
        } catch (IOException e) {
            System.out.println("IOException  异常事件发生 ");
            e.printStackTrace();
        }


        if(mMatchStr == null || "".equals(mMatchStr)){
            print(" 没有读取到  imageUser 的 exif 信息 ");
            return null;
        }
        print(" 读取到  imageUser 的 exif 信息 ImageUser_exifInfo = "+mMatchStr);
        return mMatchStr;

    }



    public     static String  getOneStr(String A1 , String B2 ,String C3 , String D4 , String E5 , String F6){
        StringBuilder sb = new StringBuilder();
        sb.append(A1 != null ? A1.trim() : "");
        sb.append(B2 != null ? B2.trim() : "");
        sb.append(C3 != null ? C3.trim() : "");
        sb.append(D4 != null ? D4.trim() : "");
        sb.append(E5 != null ? E5.trim() : "");
        sb.append(F6 != null ? F6.trim() : "");

        return sb.toString();

    }


    // 对 bank  进行初始化数据操作
    public     static void    initSimulateStockWithJson(){




        Sdcard_File = DataHolder.initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");
        mStock_Root_File = new File(ZMain_File.getAbsolutePath()+File.separator+"stock");


        Simulate_Stock_JSON_File = new File(mStock_Root_File.getAbsolutePath()+File.separator+"simulate_stock.json");

        Download_Last_Git_JsonFile();  //  嘗試下載 Git 的 Json文件

        if(!Simulate_Stock_JSON_File.exists()){  // 文件不存在
            print("initSimulateStockWithJson  初始化 Simulate的Json文件  不存在  Simulate_Stock_JSON_File="+Simulate_Stock_JSON_File.getAbsolutePath()+" 出错!");

            if( StockHolder.mBankBean == null ){
                StockHolder.mBankBean = new StockSimulate_BankBean();
                StockHolder.mBankBean.setSimulateuserlist(new SimulateStockModel().getData());
            }else{

                StockHolder.mBankBean.addSimulateuserList(new SimulateStockModel().getData());
            }



        }else{ // 文件存在
            StringBuilder mBankJsonSB = new StringBuilder();
            tryReadJsonFromFile(mBankJsonSB,  Simulate_Stock_JSON_File) ;
            if(mBankJsonSB.toString().length() > 2){

//      JSONObject mBankJsonObject =    JSONObject.parseObject(mBankJsonSB.toString());

                StockSimulate_BankBean bankBean = JSONObject.parseObject(mBankJsonSB.toString(),StockSimulate_BankBean.class);
                if(bankBean != null){

                    // 更新数据信息
                    StockHolder.mBankBean = bankBean ;

                    StockHolder.mBankBean.refreshBankStockNowPriceWithJson();
                    print("StockHolder.mBankBean 初始化成功   Json数据为\n"+mBankJsonSB.toString());

                    print("StockHolder.mBankBean 初始化成功   自身转为数据为\n"+mBankBean.toString());

                }else{

                    print("initSimulateStockWithJson  初始化 Simulate的Json文件存在  Simulate_Stock_JSON_File="+Simulate_Stock_JSON_File.getAbsolutePath()+"   但 从 Json 字符串 转为 Bank 过程出错 ");

                    StockHolder.mBankBean = new StockSimulate_BankBean();
                    StockHolder.mBankBean.setSimulateuserlist(new SimulateStockModel().getData());

                }

            }else{

                print("initSimulateStockWithJson  初始化 Simulate的Json文件 Simulate_Stock_JSON_File="+Simulate_Stock_JSON_File.getAbsolutePath()+" 出错!");
            }


            // 防错 措施
            if(mBankBean == null){
                StockHolder.mBankBean = new StockSimulate_BankBean();
                StockHolder.mBankBean.setSimulateuserlist(new SimulateStockModel().getData());
            }


        }




    }


    // __________________   Simulate_Stock_Fragment_End  __________________

    static String  TAG = "zukgit_StockHolder";


    static void print(String logStr){
        android.util.Log.i(TAG,logStr);


    }



    public  static boolean isDoubleNumeric(String str){

        Pattern Double_Pattern = Pattern.compile("-?[0-9]+[.]{0,1}[0-9]*[dD]{0,1}");

        Matcher isNum = Double_Pattern.matcher(str);

        if( !isNum.matches() ){
            return false;

        }

        return true;

    }



    public static boolean isNumeric(String str ) {

        if(str == null ) {
            return false;
        }

        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        if(str.equals("")) {
            return false;
        }
        return true;
    }


   public static void  initDayTimeStamp_JSON_WithStockDir(File[] stockFileArr){
        if(stockFileArr == null || stockFileArr.length == 0){
            print("当前 mStock_Root_File ="+ mStock_Root_File.getAbsolutePath()+"  没有任何文件！！！！");
            return;
        }
        day_timestamp_jsonFileArr = new ArrayList<File>();
        for (int i = 0; i < stockFileArr.length; i++) {
            File fileItem = stockFileArr[i];
            String fileName = fileItem.getName();
            System.out.println("day_lastjson_Year  fileName="+fileName);
            if(!fileName.endsWith(".json")){
                System.out.println("day_lastjson_Year  A1 ");
                continue;
            }
            if(!fileName.startsWith("day_")){
                System.out.println("day_lastjson_Year  A2 ");
                continue;
            }

            String onlyTimeStampStr = fileName.replace(".json","").replace("day_","").replace("_","");

            if(isNumeric(onlyTimeStampStr) && onlyTimeStampStr.length() == 8){
                day_timestamp_jsonFileArr.add(fileItem);
            }


        }
        if(day_timestamp_jsonFileArr.size() > 0 ){
            day_timestamp_jsonFileArr.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o2.getName().replace("_","").compareTo(o1.getName().replace("_",""));
                }
            });


            if(day_last_jsonFile == null && day_alway_last_json_File == null){

                //          day_timestamp_jsonFileArr.get(0);
                day_last_jsonFile = getAvaliableLastDayJson(day_timestamp_jsonFileArr) ;
                day_alway_last_json_File = day_last_jsonFile;
            } else if(day_last_jsonFile != null && day_alway_last_json_File != null && day_alway_last_json_File != day_last_jsonFile ){
                //
            }

            print("day_jsonobject 用户发生了 json 文件的选择: day_last_jsonFile="+day_last_jsonFile+"  day_alway_last_json_File="+day_alway_last_json_File);


            if(day_last_jsonFile != null){
                String lastDayFileName = day_last_jsonFile.getName();
                String onlyTimeStampStr = lastDayFileName.replace(".json","").replace("day_","");

                day_lastjson_timestamp =       onlyTimeStampStr;
                System.out.println("day_lastjson_Year day_lastjson_timestamp =  "+day_lastjson_timestamp);
                if(isNumeric(day_lastjson_timestamp) && day_lastjson_timestamp.length() == 8){

                    day_lastjson_Year = day_lastjson_timestamp.substring(0,4);
                    day_lastjson_Month = day_lastjson_timestamp.substring(4,6);
                    day_lastjson_Day = day_lastjson_timestamp.substring(6,8);

                    System.out.println("day_jsonobject day_lastjson_Year day_lastjson_timestamp =  "+day_lastjson_timestamp +" day_lastjson_Day = "+ day_lastjson_Day  +" day_lastjson_Year = "+ day_lastjson_Year  +" day_lastjson_Month = "+ day_lastjson_Month );

                }else{
                    System.out.println("day_lastjson_Year 长度不为8  ");
                }

            }else{
                System.out.println("day_lastjson_Year  day_last_jsonFile 最新的day_xxxx.json 文件不存在 ");
            }

        }else{
            System.out.println("day_lastjson_Year  没有找到 对应 day_yyyymmdd.json 文件! ");
        }


    }

    static File getAvaliableLastDayJson(ArrayList<File> dayjsonFileList){
        File matchFile = null;

        for (int i = 0; i < dayjsonFileList.size() ; i++) {
            File jsonFile = dayjsonFileList.get(i);
            StringBuilder dayJsonStrBuilder  = new StringBuilder();
            tryReadJsonFromFile(dayJsonStrBuilder,jsonFile);
            // 必须包含 每日 指标
            if(dayJsonStrBuilder.toString().contains("每日指标")){
                return jsonFile;
            }
        }

        return matchFile;

    }
            /*      public static File[] day_timestamp_xlsxFileArr;     // 以 day_20200202.xlsx 这样形式命名的文件的集合
        public static File  day_last_xlsxFile ; // 时间上最新的 那个  day_ 开头的 时间戳的 文件
        public static String day_lastxlsx_timestamp;   // 时间上最新的那个文件的 时间戳  要显示的就是那个文件的内容*/

    static void  initDayTimeStamp_XLSX_WithStockDir(File[] stockFileArr){
        if(stockFileArr == null || stockFileArr.length == 0){
            print("当前 mStock_Root_File ="+ mStock_Root_File.getAbsolutePath()+"  没有任何文件！！！！");
            return;
        }

        day_timestamp_xlsxFileArr = new ArrayList<File>();
        for (int i = 0; i < stockFileArr.length; i++) {
            File fileItem = stockFileArr[i];
            String fileName = fileItem.getName();
            System.out.println("day_lastxlsx_Year  fileName="+fileName);
            if(!fileName.endsWith(".xlsx")){
                System.out.println("day_lastxlsx_Year  A1 ");
                continue;
            }
            if(!fileName.startsWith("day_")){
                System.out.println("day_lastxlsx_Year  A2 ");
                continue;
            }

            String onlyTimeStampStr = fileName.replace(".xlsx","").replace("day_","").replace("-","").replace("_","");

            print("onlyTimeStampStr["+i+"] = "+ onlyTimeStampStr +"  fileName="+fileName);
            if(isNumeric(onlyTimeStampStr) && onlyTimeStampStr.length() == 8){
                print("MatchFile["+i+"] =  fileName="+ fileName);
                day_timestamp_xlsxFileArr.add(fileItem);
            }


        }

        print("day_timestamp_xlsxFileArr.size() = "+day_timestamp_xlsxFileArr.size());

        for (int i = 0; i < day_timestamp_xlsxFileArr.size(); i++) {
            print("fileindex["+i+"] = "+ day_timestamp_xlsxFileArr.get(i).getAbsolutePath());

        }
        if(day_timestamp_xlsxFileArr.size() > 0 ){
            day_timestamp_xlsxFileArr.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().replace("-","").replace("_","").compareTo(o2.getName().replace("-","").replace("_",""));
                }
            });

            day_last_xlsxFile  = day_timestamp_xlsxFileArr.get(0);

            if(day_last_xlsxFile != null){
                String lastDayFileName = day_last_xlsxFile.getName();
                String onlyTimeStampStr = lastDayFileName.replace(".xlsx","").replace("day_","").replace("-","");

                day_lastxlsx_timestamp =       onlyTimeStampStr;
                print("day_lastxlsx_Year day_lastxlsx_timestamp =  "+day_lastxlsx_timestamp);
                if(isNumeric(day_lastxlsx_timestamp) && day_lastxlsx_timestamp.length() == 8){

                    day_lastxlsx_Year = day_lastxlsx_timestamp.substring(0,4);
                    day_lastxlsx_Month = day_lastxlsx_timestamp.substring(4,6);
                    day_lastxlsx_Day = day_lastxlsx_timestamp.substring(6,8);
//                public static String day_lastxlsx_Year;
//                public static String day_lastxlsx_Month;
//                public static String day_lastxlsx_Day;


                }else{
                    print("day_lastxlsx_Year 长度不为8  day_last_xlsxFile="+day_last_xlsxFile);
                }

            }else{
                print("day_lastxlsx_Year  day_last_xlsxFile 最新的day_xxxx.xlsx 文件不存在 day_last_xlsxFile="+day_last_xlsxFile);
            }

        }else{
            print("day_lastxlsx_Year  没有找到 对应 day_yyyymmdd.xlsx 文件! ");
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    static public void init_stock(boolean isFirstInitFlag){

//        Sdcard_File = Environment.getExternalStorageDirectory();
        Sdcard_File = DataHolder.initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");
        mStock_Root_File = new File(ZMain_File.getAbsolutePath()+File.separator+"stock");

        J0_JiaoYiRiQi_Path = mStock_Root_File.getAbsolutePath()+File.separator+"J0_交易日历.xlsx";
        J0_GuPiaoLieBiao_Path = mStock_Root_File.getAbsolutePath()+File.separator+"J0_股票列表.xlsx";

        mAttentionStock_File = new File(mStock_Root_File.getAbsolutePath()+File.separator+"_AttentionStock.txt");



        J0_GuPiaoLieBiao_Path_File =  new File(J0_GuPiaoLieBiao_Path);
        J0_JiaoYiRiQi_Path_File =  new File(J0_JiaoYiRiQi_Path);
        if(mStock_Root_File.exists()){
            mStock_file_list =   mStock_Root_File.listFiles();

                initDayTimeStamp_XLSX_WithStockDir(mStock_file_list);
                initDayTimeStamp_JSON_WithStockDir(mStock_file_list);


        }





        J0_GuPiaoLieBiao_JSON_File = new File(mStock_Root_File.getAbsolutePath()+File.separator+"gupiaoliebiao.json");
        mStock_Log_File =  new File(mStock_Root_File.getAbsolutePath()+File.separator+"StockLog.txt");

        mStock_RootNode_ByMarket_JsonFile = new File(mStock_Root_File.getAbsolutePath()+File.separator+"market_root_stocknode.json");

        if(gupiaoliebiao_StockList != null){
            gupiaoliebiao_StockList.clear();
        }
        gupiaoliebiao_StockList = new  ArrayList<Stock_NodeImpl>(10000);
        tscode_stockShareInfo_Map = new LinkedHashMap<String,StockNodeShareInfo>(10000);
        level1Name_level2StockImpl_Map = new LinkedHashMap<String,ArrayList<Stock_NodeImpl>>();



        // Area-Fragment-Begin
        level2AreaIndustry_level3StockImplList_Map = new LinkedHashMap<Stock_NodeImpl,ArrayList<Stock_NodeImpl>>();

        areall_level2AreaName_level3StockImplList_Map = new LinkedHashMap<Stock_NodeImpl,ArrayList<Stock_NodeImpl>>();





        J0_AreaPage_JSON_File = new File(mStock_Root_File.getAbsolutePath()+File.separator+"area_industry_stock.json");



        level1AreaName_level2AreaIndustryStockNode_Map = new LinkedHashMap<Stock_NodeImpl,ArrayList<Stock_NodeImpl>>();

        allIndustryNameList = new ArrayList<String>();
        mIndustry_StockImplList_Map =  new LinkedHashMap<String,ArrayList<Stock_NodeImpl>>();

        level_1_AreaBlock_nodeList = new ArrayList<Stock_NodeImpl>();
        // Area-Fragment-End


        isGuPiaoLieBiao_FInish_MapInit_Flag = false;
        if(!J0_GuPiaoLieBiao_Path_File.exists()){
            print("当前 必须的 xlsx 文件 不存在!! PATH = "+J0_GuPiaoLieBiao_Path_File.getAbsolutePath());
            return;
        }



        if(TScode_List.size() == 0){
            initTsCodeList();
        }


        if(mAttentionStock_File.exists()){

            init_Attention_Stock_List(mAttentionStock_File);  //  初始化 关注的股票的列表
        }





        String stockArrJsonStr  =   getJson4Arr_SockItem(gupiaoliebiao_StockList);
        writeContentToFile(J0_GuPiaoLieBiao_JSON_File,stockArrJsonStr);
        showStockLogPullCommand(J0_GuPiaoLieBiao_JSON_File,"股票列表.json 的文件 提示 TScode_List.size()="+TScode_List.size());



        if(day_last_jsonFile != null && day_last_jsonFile.exists()){

//  day_xxxxx.json 的数据 进行 解析
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    print("开始把 XLSX 文件 【"+day_last_xlsxFile.getAbsolutePath()+"】 转为json文件 开始!!");
//                            File dayJsonFile =     reverXlsxToJson(day_last_xlsxFile,mStock_Root_File);
//                            showStockLogPullCommand(dayJsonFile,"股票列表.json 的文件 提示 ");
//                    print("开始把 XLSX 文件 【"+day_last_xlsxFile.getAbsolutePath()+"】 转为json文件 结束！!!");



                    print("开始读取 JSON文件 【"+day_last_jsonFile.getAbsolutePath()+"】 开始!!");

                    StringBuilder jsonSB = new StringBuilder();
                    tryReadJsonFromFile(jsonSB,day_last_jsonFile);
                    showStockLogPullCommand(day_last_jsonFile,day_last_jsonFile.getName()+".json 的文件 提示 ");
                    print("开始读取 JSON文件件 【"+day_last_jsonFile.getAbsolutePath()+"】  结束！!!");
//                    print("打印JSON文件内容:"+jsonSB.toString());
//                    JsonObject day_jsonobject = new JsonObject();
                    JSONObject day_jsonobject =    JSONObject.parseObject(jsonSB.toString());
                    print("JSON文件 内容  生成对象 day_jsonobject.size()="+day_jsonobject.size()+"  day_last_jsonFile.getAbsolutePath()="+day_last_jsonFile.getAbsolutePath());


                    if(day_jsonobject.size() > 0){

                        ArrayList<String> keyList =new ArrayList<String>();
                        keyList.addAll( day_jsonobject.keySet());

                        for (int i = 0; i < keyList.size(); i++) {
                            String keyItem = keyList.get(i);
                            JSONArray sheetValueJSONArray = (JSONArray)day_jsonobject.getJSONArray(keyItem);
                            KindOfJsonArrWithKey(keyItem,sheetValueJSONArray);

                            print("day_jsonobject  JSON文件  JsonKey=["+keyItem+"]  valuse.size=["+sheetValueJSONArray.size()+"] "+day_last_jsonFile.getAbsolutePath());
                        }
                        InitWithStockNodeShareInfoMap();
                        init_NodeImplArr_Type_Map();

//                        showLogInfo();

       /*                 for (int i = 0; i <day_jsonobject.size() ; i++) {
                            JSONObject sheetObject = (JSONObject)day_jsonobject.get(i);

                            // 怎么 拿到 sheetname 呢?




                        }
*/
                        isGuPiaoLieBiao_FInish_MapInit_Flag = true;   //  加载完成 .json 文件的 线程 标示
                        if(mBankBean != null){
                            mBankBean.refreshBankStockNowPriceWithJson();

                        }
                    }


                    // 对ArrList<Stock>进行整理


                }
            }).start();



        }
    }

    //  以 股票 上市 时间 进行 排序 显示的 列表    时间降序 显示(最新的在最顶部)
    public static Comparator delist_date_timedown_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o2.delist_date == null || "".equals(o2.delist_date.trim())){
                print("A1_1 o2.delist_date["+o2.delist_date+" "+o2.text()+"]   o1.delist_date=["+o1.delist_date+" "+o1.text()+"]  return = -1");
                return 1;
            }

            if(o1.delist_date == null || "".equals(o1.delist_date.trim())){
                print("A1_2 o2.delist_date["+o2.delist_date+" "+o2.text()+"]   o1.delist_date=["+o1.delist_date+" "+o1.text()+"]  return = -1");
                return -1;
            }


            if (o1.delist_date == null && o2.delist_date != null) {
                print("A2 o2.delist_date["+o2.delist_date+" "+o2.text()+"]   o1.delist_date=["+o1.delist_date+" "+o1.text()+"]  return = -1");

                return -1;
            } else if (o1.delist_date != null && o2.delist_date == null) {
                print("A3  o2.delist_date["+o2.delist_date+" "+o2.text()+"]   o1.delist_date=["+o1.delist_date+" "+o1.text()+"]  return = -1");

                return 1;
            } else if (o1.delist_date == null && o2.delist_date == null) {
                print("A4  o2.delist_date["+o2.delist_date+" "+o2.text()+"]   o1.delist_date=["+o1.delist_date+" "+o1.text()+"]  return = 0");

                return 0;

            }





            int return_int = o2.delist_date.compareTo(o1.delist_date);
            print("A5 o2.delist_date["+o2.delist_date+" "+o2.text()+"]   o1.delist_date=["+o1.delist_date+" "+o1.text()+"]  return = " + return_int);

            return return_int;   // 最新上市的 在最前面


        }};

    //  以 股票 上市 时间 进行 排序 显示的 列表    时间降序 显示(最新的在最顶部)
    public static Comparator list_date_timedown_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if (o1.list_date == null && o2.list_date != null) {
                return -1;
            } else if (o1.list_date != null && o2.list_date == null) {
                return 1;
            } else if (o1.list_date == null && o2.list_date == null) {
                return 0;

            }
            return o2.list_date.compareTo(o1.list_date);   // 最新上市的 在最前面


        }};

    public static Comparator list_date_timeup_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if("L".equals(o1.list_status) && "L".equals(o2.list_status) ) {
                if (o1.list_date == null && o2.list_date != null) {
                    return 1;
                } else if (o1.list_date != null && o2.list_date == null) {
                    return -1;
                } else if (o1.list_date == null && o2.list_date == null) {
                    return 0;

                }
                return o2.list_date.compareTo(o1.list_date);   // 最新上市的 在最前面
            }else if("L".equals(o1.list_status) && !"L".equals(o2.list_status) ) {
                return 1;
            }else if(!"L".equals(o1.list_status) && "L".equals(o2.list_status) ) {
                return -1;
            }  else {

                if((o1.getDelist_date() != null  && o2.getDelist_date() == null )){
                    return 1;
                } else if((o1.getDelist_date() == null  && o2.getDelist_date() == null )){
                    return 0;
                }

                return o2.getDelist_date().compareTo(o1.getDelist_date());   // 最新上市的 在最前面

            }

        }};






//    o1.RiXianXingQingvShiJianWeiXu_pct_chg;
//    o1.RiXianXingQingvShiJianWeiXu_day3_pct_chg;
//    o1.RiXianXingQingvShiJianWeiXu_day5_pct_chg;
//    o1.RiXianXingQingvShiJianWeiXu_day10_pct_chg;
//    o1.RiXianXingQingvShiJianWeiXu_day15_pct_chg;
//    o1.RiXianXingQingvShiJianWeiXu_day30_pct_chg;
//    o1.RiXianXingQingvShiJianWeiXu_day60_pct_chg;
//    o1.RiXianXingQingvShiJianWeiXu_day90_pct_chg;
//    o1.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg


    // Level2_Stock_NodeImpl_Sort_PriceDown_Type  = 1;
    // Level2_Stock_NodeImpl_Sort_PriceUp_Type  = 2;
    //



    static void   init_Stock_NodeImpl_ComparatorList(){

        Stock_NodeImpl_Comparator_Map = new HashMap<Integer,Comparator<Stock_NodeImpl>>();


        Stock_NodeImpl_ComparatorList = new ArrayList<Comparator<Stock_NodeImpl>>();

        Stock_NodeImpl_ComparatorTip_List =  new ArrayList<String>();
        Stock_NodeImpl_ComparatorTip_List.add("."+"价格榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,pricedown_StockComparator);


        Stock_NodeImpl_ComparatorTip_List.add("."+"价格榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,priceup_StockComparator);

        Stock_NodeImpl_ComparatorTip_List.add("."+"涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day0_pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day0_pct_up_StockComparator);





        Stock_NodeImpl_ComparatorTip_List.add("."+"换手榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_turnover_rate_down_StockComparator  );   // 换手率
        Stock_NodeImpl_ComparatorTip_List.add("."+"换手榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_turnover_rate_up_StockComparator  );   // 换手率

        Stock_NodeImpl_ComparatorTip_List.add("."+"振幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_zhenfu_rate_down_StockComparator   );   //振幅占比
        Stock_NodeImpl_ComparatorTip_List.add("."+"振幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_zhenfu_rate_up_StockComparator   );   // 振幅占比

        Stock_NodeImpl_ComparatorTip_List.add("."+"成交榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_tradeamount_up_StockComparator        );   // 成交额
        Stock_NodeImpl_ComparatorTip_List.add("."+"成交榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_tradeamount_down_StockComparator    );   // 成交额

        Stock_NodeImpl_ComparatorTip_List.add("."+"总市值榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_total_mv_up_StockComparator  );   // 总市值
        Stock_NodeImpl_ComparatorTip_List.add("."+"总市值榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_total_mv_down_StockComparator  );   // 总市值

        Stock_NodeImpl_ComparatorTip_List.add("."+"流通值榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_flow_mv_up_StockComparator  );   // 流通值
        Stock_NodeImpl_ComparatorTip_List.add("."+"流通值榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_flow_mv_down_StockComparator  );   // 流通值

        Stock_NodeImpl_ComparatorTip_List.add("."+"流通占比榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_mvflowrate_down_StockComparator  );   // 流通占比
        Stock_NodeImpl_ComparatorTip_List.add("."+"流通占比榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_mvflowrate_up_StockComparator    );   // 流通占比

        Stock_NodeImpl_ComparatorTip_List.add("."+"上市日期榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_ipo_date_up_StockComparator  );   // 上市日期
        Stock_NodeImpl_ComparatorTip_List.add("."+"上市日期榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day_ipo_date_down_StockComparator  );   // 上市日期
        Stock_NodeImpl_ComparatorTip_List.add("."+"3日涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day3pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"3日跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day3pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"5日涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day5pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"5日跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day5pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"10日涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day10pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"10日跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day10pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"15日涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day15pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"15日跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day15pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"30日涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day30pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"30日跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day30pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"60日涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day60pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"60日跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day60pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"90日涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day90pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"90日跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,day90pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"年内涨幅榜_降序↓");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,dayyearpct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"年内跌幅榜_升序↑");
        Stock_NodeImpl_Comparator_Map.put(Stock_NodeImpl_ComparatorTip_List.size()-1,dayyearpct_up_StockComparator);

    }


    static void   init_Stock_NodeImpl_ComparatorList_Old(){



        Stock_NodeImpl_ComparatorList = new ArrayList<Comparator<Stock_NodeImpl>>();

        Stock_NodeImpl_ComparatorTip_List =  new ArrayList<String>();
        Stock_NodeImpl_ComparatorTip_List.add("."+"价格榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(pricedown_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"价格榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(priceup_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day0_pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day0_pct_up_StockComparator);



        Stock_NodeImpl_ComparatorTip_List.add("."+"换手榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day_turnover_rate_down_StockComparator  );   // 换手率
        Stock_NodeImpl_ComparatorTip_List.add("."+"换手榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day_turnover_rate_up_StockComparator  );   // 换手率

        Stock_NodeImpl_ComparatorTip_List.add("."+"振幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day_zhenfu_rate_down_StockComparator   );   //振幅占比
        Stock_NodeImpl_ComparatorTip_List.add("."+"振幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day_zhenfu_rate_up_StockComparator   );   // 振幅占比

        Stock_NodeImpl_ComparatorTip_List.add("."+"成交榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day_tradeamount_up_StockComparator        );   // 成交额
        Stock_NodeImpl_ComparatorTip_List.add("."+"成交榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day_tradeamount_down_StockComparator    );   // 成交额

        Stock_NodeImpl_ComparatorTip_List.add("."+"总市值榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day_total_mv_up_StockComparator  );   // 总市值
        Stock_NodeImpl_ComparatorTip_List.add("."+"总市值榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day_total_mv_down_StockComparator  );   // 总市值

        Stock_NodeImpl_ComparatorTip_List.add("."+"流通值榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day_flow_mv_up_StockComparator  );   // 流通值
        Stock_NodeImpl_ComparatorTip_List.add("."+"流通值榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day_flow_mv_down_StockComparator  );   // 流通值

        Stock_NodeImpl_ComparatorTip_List.add("."+"流通占比榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day_mvflowrate_down_StockComparator  );   // 流通占比
        Stock_NodeImpl_ComparatorTip_List.add("."+"流通占比榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day_mvflowrate_up_StockComparator    );   // 流通占比

        Stock_NodeImpl_ComparatorTip_List.add("."+"上市日期榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day_ipo_date_up_StockComparator  );   // 上市日期
        Stock_NodeImpl_ComparatorTip_List.add("."+"上市日期榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day_ipo_date_down_StockComparator  );   // 上市日期


        Stock_NodeImpl_ComparatorTip_List.add("."+"3日涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day3pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"3日跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day3pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"5日涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day5pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"5日跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day5pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"10日涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day10pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"10日跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day10pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"15日涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day15pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"15日跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day15pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"30日涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day30pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"30日跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day30pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"60日涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day60pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"60日跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day60pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"90日涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(day90pct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"90日跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(day90pct_up_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"年内涨幅榜_降序↓");
        Stock_NodeImpl_ComparatorList.add(dayyearpct_down_StockComparator);
        Stock_NodeImpl_ComparatorTip_List.add("."+"年内跌幅榜_升序↑");
        Stock_NodeImpl_ComparatorList.add(dayyearpct_up_StockComparator);

    }





    public   static void Stock_NodeImpl_Comparator_With_Type(int orderIndex ){

//        Comparator<Stock_NodeImpl> matchCompar =    Stock_NodeImpl_ComparatorList.get(orderIndex);

        Comparator  matchCompar =  getMatchComparator_With_Type(orderIndex);

        for (int i = 0; i < Root_StockNodeImpl.children.size(); i++) {
            Stock_NodeImpl  level1_stocknode = Root_StockNodeImpl.children.get(i);
            if(level1_stocknode == null || level1_stocknode.children == null ){
                continue;
            }

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && matchCompar != null){




                print("Stock_NodeImpl_Comparator_With_Type ════════════ "+level1_stocknode.children.size() +" id="+level1_stocknode.id+"════════════ Order="+ matchCompar);
                print("Stock_NodeImpl_Comparator_With_Type  level1_stocknode ="+level1_stocknode );

                print("___Begin Sort___Stock_NodeImpl_Comparator_With_Type  orderIndex="+orderIndex +"   level1_stocknode="+level1_stocknode.children);

                level1_stocknode.children.sort(matchCompar);

                print("_____End Sort___Stock_NodeImpl_Comparator_With_Type  orderIndex="+orderIndex +"   level1_stocknode="+level1_stocknode.children);
                print("Stock_NodeImpl_Comparator_With_Type ══════════════════════"+" id="+level1_stocknode.id+" END ═════════════════════════════════════════════");
            }
        }

    }


//    public   static Comparator getMatchComparator_With_Type(int type ) {
//
//        return Stock_NodeImpl_Comparator_Map.get(type);
//    }


    public   static Comparator getMatchComparator_With_Type(int type ){



        Comparator defaultComparator = pricedown_StockComparator;

        switch(type){
            case 0:
                defaultComparator = pricedown_StockComparator;
                break;

            case 1:
                defaultComparator = priceup_StockComparator;
                break;

            case 2:
                defaultComparator = day0_pct_down_StockComparator;  // 涨幅降序
                break;

            case 3:
                defaultComparator = day0_pct_up_StockComparator;
                break;




            case 4:
                defaultComparator = day_turnover_rate_down_StockComparator;  // 换手率
                break;

            case 5:
                defaultComparator = day_turnover_rate_up_StockComparator;
                break;


            case 6:
                defaultComparator = day_zhenfu_rate_down_StockComparator;  // 振幅占比
                break;

            case 7:
                defaultComparator = day_zhenfu_rate_up_StockComparator;
                break;



            case 8:
                defaultComparator = day_tradeamount_down_StockComparator ;  // 成交额
                break;

            case 9:
                defaultComparator = day_tradeamount_up_StockComparator ;
                break;







            case 10:
                defaultComparator = day_total_mv_down_StockComparator  ;  // 总市值
                break;

            case 11:
                defaultComparator = day_total_mv_up_StockComparator;
                break;




            case 12:
                defaultComparator = day_flow_mv_down_StockComparator   ;  // 流通值
                break;

            case 13:
                defaultComparator = day_flow_mv_up_StockComparator;
                break;



            case 14:
                defaultComparator = day_mvflowrate_down_StockComparator   ;  // 流通占比
                break;

            case 15:
                defaultComparator = day_mvflowrate_up_StockComparator;
                break;

            case 16:
                defaultComparator = day_ipo_date_down_StockComparator   ;  // 流通占比
                break;

            case 17:
                defaultComparator = day_ipo_date_up_StockComparator;
                break;


            case 18:
                defaultComparator = day3pct_down_StockComparator;
                break;

            case 19:
                defaultComparator = day3pct_up_StockComparator;
                break;
            case 20:
                defaultComparator = day5pct_down_StockComparator;
                break;

            case 21:
                defaultComparator = day5pct_up_StockComparator;
                break;

            case 22:
                defaultComparator = day10pct_down_StockComparator;
                break;

            case 23:
                defaultComparator = day10pct_up_StockComparator;
                break;

            case 24:
                defaultComparator = day15pct_down_StockComparator;
                break;

            case 25:
                defaultComparator = day15pct_up_StockComparator;
                break;

            case 26:
                defaultComparator = day30pct_down_StockComparator;
                break;

            case 27:
                defaultComparator = day30pct_up_StockComparator;
                break;

            case 28:
                defaultComparator = day60pct_down_StockComparator;
                break;

            case 29:
                defaultComparator = day60pct_up_StockComparator;
                break;
            case 30:
                defaultComparator = day90pct_down_StockComparator;
                break;

            case 31:
                defaultComparator = day90pct_up_StockComparator;
                break;

            case 32:
                defaultComparator = dayyearpct_down_StockComparator;
                break;

            case 33:
                defaultComparator = dayyearpct_up_StockComparator;
                break;

            default:
                break;

        }

        return defaultComparator;
    }


    public   static Comparator<Stock_NodeImpl> day0_pct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_pct_chg > o2.RiXianXingQingvShiJianWeiXu_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_pct_chg == o2.RiXianXingQingvShiJianWeiXu_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_pct_chg < o2.RiXianXingQingvShiJianWeiXu_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day0_pct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_pct_chg > o2.RiXianXingQingvShiJianWeiXu_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_pct_chg == o2.RiXianXingQingvShiJianWeiXu_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_pct_chg < o2.RiXianXingQingvShiJianWeiXu_pct_chg ){
                return 1;
            }
            return -1;

        }};


    public   static Comparator<Stock_NodeImpl> dayyearpct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg > o2.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg == o2.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg < o2.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> dayyearpct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg > o2.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg == o2.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg < o2.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg ){
                return 1;
            }
            return -1;


        }};


    public   static Comparator<Stock_NodeImpl> day90pct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day90_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day90_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day90_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day90_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day90_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day90_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day90pct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day90_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day90_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day90_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day90_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day90_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day90_pct_chg ){
                return 1;
            }
            return -1;


        }};


    public   static Comparator<Stock_NodeImpl> day60pct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day60_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day60_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day60_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day60_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day60_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day60_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day60pct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day60_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day60_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day60_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day60_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day60_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day60_pct_chg ){
                return 1;
            }
            return -1;


        }};


    public   static Comparator<Stock_NodeImpl> day30pct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day30_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day30_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day30_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day30_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day30_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day30_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day30pct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day30_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day30_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day30_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day30_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day30_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day30_pct_chg ){
                return 1;
            }
            return -1;


        }};


    public   static Comparator<Stock_NodeImpl> day15pct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day15_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day15_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day15_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day15_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day15_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day15_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day15pct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day15_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day15_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day15_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day15_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day15_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day15_pct_chg ){
                return 1;
            }
            return -1;


        }};

    public   static Comparator<Stock_NodeImpl> day10pct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day10_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day10_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day10_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day10_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day10_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day10_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day10pct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day10_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day10_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day10_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day10_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day10_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day10_pct_chg ){
                return 1;
            }
            return -1;


        }};


    public   static Comparator<Stock_NodeImpl> day5pct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day5_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day5_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day5_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day5_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day5_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day5_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day5pct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day5_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day5_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day5_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day5_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day5_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day5_pct_chg ){
                return 1;
            }
            return -1;


        }};


    public   static Comparator<Stock_NodeImpl> day3pct_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day3_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day3_pct_chg ){
                return 1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day3_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day3_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day3_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day3_pct_chg ){
                return -1;
            }
            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day3pct_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_day3_pct_chg > o2.RiXianXingQingvShiJianWeiXu_day3_pct_chg ){
                return -1;
            }
            if(o1.RiXianXingQingvShiJianWeiXu_day3_pct_chg == o2.RiXianXingQingvShiJianWeiXu_day3_pct_chg ){
                return 0;
            }

            if(o1.RiXianXingQingvShiJianWeiXu_day3_pct_chg < o2.RiXianXingQingvShiJianWeiXu_day3_pct_chg ){
                return 1;
            }
            return -1;


        }};



    //  以 股票 上市 时间 进行 排序 显示的 列表  价格↑ 升序
    public   static Comparator<Stock_NodeImpl> priceup_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.price > o2.price ){
                return 1;
            }
            if(o1.price == o2.price ){
                return 0;
            }

            if(o1.price < o2.price ){
                return -1;
            }
            return -1;
        }};


    //  价格↑ 降序
    public  static Comparator<Stock_NodeImpl> pricedown_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.price > o2.price ){
                return -1;
            }
            if(o1.price == o2.price ){
                return 0;
            }

            if(o1.price < o2.price ){
                return 1;
            }
            return -1;
        }};





    // 换手率
    public   static Comparator<Stock_NodeImpl> day_turnover_rate_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.MeiRiZhiBiao_turnover_rate_f > o2.MeiRiZhiBiao_turnover_rate_f ){
                return 1;
            } else             if(o1.MeiRiZhiBiao_turnover_rate_f < o2.MeiRiZhiBiao_turnover_rate_f ){
                return -1;
            }else     if(o1.MeiRiZhiBiao_turnover_rate_f == o2.MeiRiZhiBiao_turnover_rate_f ){
                return 0;
            }


            return -1;
        }};

    public   static Comparator<Stock_NodeImpl> day_turnover_rate_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.MeiRiZhiBiao_turnover_rate_f > o2.MeiRiZhiBiao_turnover_rate_f ){
                return -1;
            }else             if(o1.MeiRiZhiBiao_turnover_rate_f < o2.MeiRiZhiBiao_turnover_rate_f ){
                return 1;
            } else   if(o1.MeiRiZhiBiao_turnover_rate_f == o2.MeiRiZhiBiao_turnover_rate_f ){
                return 0;
            }


            return -1;


        }};

    public   static Comparator<Stock_NodeImpl> day_ipo_date_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.list_date == null  && o2.list_date == null){
                return 0 ;
            }

            if(o1.list_date != null  && o2.list_date == null){
                return -1 ;
            }

            if(o1.list_date == null  && o2.list_date != null){
                return 1 ;
            }
      return  o2.list_date.compareTo(o1.list_date);

        }

    };


    public   static Comparator<Stock_NodeImpl> day_ipo_date_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.list_date == null  && o2.list_date == null){
                return 0 ;
            }

            if(o1.list_date != null  && o2.list_date == null){
                return -1 ;
            }

            if(o1.list_date == null  && o2.list_date != null){
                return 1 ;
            }
            return o1.list_date.compareTo(o2.list_date);

        }

    };





        // 流通值
    public   static Comparator<Stock_NodeImpl> day_flow_mv_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.MeiRiZhiBiao_circ_mv < o2.MeiRiZhiBiao_circ_mv ){
                return -1;
            }else   if(o1.MeiRiZhiBiao_circ_mv > o2.MeiRiZhiBiao_circ_mv ){
                return 1;
            }else    if(o1.MeiRiZhiBiao_circ_mv == o2.MeiRiZhiBiao_circ_mv ){
                return 0;
            }


            return -1;
        }};

    // 流通值
    public   static Comparator<Stock_NodeImpl> day_flow_mv_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.MeiRiZhiBiao_circ_mv > o2.MeiRiZhiBiao_circ_mv ){
                return -1;
            } else             if(o1.MeiRiZhiBiao_circ_mv < o2.MeiRiZhiBiao_circ_mv ){
                return 1;
            }else      if(o1.MeiRiZhiBiao_circ_mv == o2.MeiRiZhiBiao_circ_mv ){
                return 0;
            }


            return -1;


        }};


    // 总市值
    public   static Comparator<Stock_NodeImpl> day_total_mv_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.MeiRiZhiBiao_total_mv < o2.MeiRiZhiBiao_total_mv ){
                return -1;
            }else       if(o1.MeiRiZhiBiao_total_mv > o2.MeiRiZhiBiao_total_mv ){
                return 1;
            }else   if(o1.MeiRiZhiBiao_total_mv == o2.MeiRiZhiBiao_total_mv ){
                return 0;
            }


            return -1;
        }};

    // 总市值
    public   static Comparator<Stock_NodeImpl> day_total_mv_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.MeiRiZhiBiao_total_mv < o2.MeiRiZhiBiao_total_mv ){
                return 1;
            }else   if(o1.MeiRiZhiBiao_total_mv > o2.MeiRiZhiBiao_total_mv ){
                return -1;
            }else   if(o1.MeiRiZhiBiao_total_mv == o2.MeiRiZhiBiao_total_mv ){
                return 0;
            }


            return -1;


        }};


    // 成交额
    public   static Comparator<Stock_NodeImpl> day_tradeamount_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.RiXianXingQingvShiJianWeiXu_amount < o2.RiXianXingQingvShiJianWeiXu_amount ){
                return -1;
            }else   if(o1.RiXianXingQingvShiJianWeiXu_amount > o2.RiXianXingQingvShiJianWeiXu_amount ){
                return 1;
            }else   if(o1.RiXianXingQingvShiJianWeiXu_amount == o2.RiXianXingQingvShiJianWeiXu_amount ){
                return 0;
            }


            return -1;
        }};

    // 成交额
    public   static Comparator<Stock_NodeImpl> day_tradeamount_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {

            if(o1.RiXianXingQingvShiJianWeiXu_amount > o2.RiXianXingQingvShiJianWeiXu_amount ){
                return -1;
            } else             if(o1.RiXianXingQingvShiJianWeiXu_amount < o2.RiXianXingQingvShiJianWeiXu_amount ){
                return 1;
            }else   if(o1.RiXianXingQingvShiJianWeiXu_amount == o2.RiXianXingQingvShiJianWeiXu_amount ){
                return 0;
            }


            return -1;


        }};


    public   static Comparator<Stock_NodeImpl> day_zhenfu_rate_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.price == 0 && o2.price != 0 ){

                return 1;
            }
            if(o1.price != 0 && o2.price == 0 ){

                return -1;
            }

            if(o1.price == 0 && o2.price == 0 ){

                return 0;
            }


            double o1_zhenfu = (o1.RiXianXingQingvShiJianWeiXu_high - o1.RiXianXingQingvShiJianWeiXu_low)/o1.price;
            double o2_zhenfu = (o2.RiXianXingQingvShiJianWeiXu_high - o2.RiXianXingQingvShiJianWeiXu_low)/o2.price;


            if(o1_zhenfu > o2_zhenfu){
                return -1 ;
            } else         if(o1_zhenfu < o2_zhenfu){
                return 1 ;
            }else   if(o1_zhenfu == o2_zhenfu){
                return 0 ;
            }





            return 0 ;
        }
    };


    public   static Comparator<Stock_NodeImpl> day_zhenfu_rate_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.price == 0 && o2.price != 0 ){

                return 1;
            }
            if(o1.price != 0 && o2.price == 0 ){

                return -1;
            }

            if(o1.price == 0 && o2.price == 0 ){

                return 0;
            }


            double o1_zhenfu = (o1.RiXianXingQingvShiJianWeiXu_high - o1.RiXianXingQingvShiJianWeiXu_low)/o1.price;
            double o2_zhenfu = (o2.RiXianXingQingvShiJianWeiXu_high - o2.RiXianXingQingvShiJianWeiXu_low)/o2.price;


            if(o1_zhenfu > o2_zhenfu){
                return 1 ;
            } else         if(o1_zhenfu < o2_zhenfu){
                return -1 ;
            }else   if(o1_zhenfu == o2_zhenfu){
                return 0 ;
            }
            return 0 ;
        }
    };




    // 流通占比
    public   static Comparator<Stock_NodeImpl> day_mvflowrate_up_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.MeiRiZhiBiao_total_mv == 0 && o2.MeiRiZhiBiao_total_mv != 0 ){

                return 1;
            }
            if(o1.MeiRiZhiBiao_total_mv != 0 && o2.MeiRiZhiBiao_total_mv == 0 ){

                return -1;
            }

            if(o1.MeiRiZhiBiao_total_mv == 0 && o2.MeiRiZhiBiao_total_mv == 0 ){

                return 0;
            }


            double o1_flowrate = (o1.MeiRiZhiBiao_total_mv - o1.MeiRiZhiBiao_circ_mv)/o1.MeiRiZhiBiao_total_mv;
            double o2_flowrate = (o2.MeiRiZhiBiao_total_mv - o2.MeiRiZhiBiao_circ_mv)/o2.MeiRiZhiBiao_total_mv;


            if(o1_flowrate > o2_flowrate){
                return -1 ;
            } else         if(o1_flowrate < o2_flowrate){
                return 1 ;
            }else   if(o1_flowrate == o2_flowrate){
                return 0 ;
            }

            return 0 ;
        }
    };

    // 流通占比
    public   static Comparator<Stock_NodeImpl> day_mvflowrate_down_StockComparator = new Comparator<Stock_NodeImpl>() {
        @Override
        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
            if(o1.MeiRiZhiBiao_total_mv == 0 && o2.MeiRiZhiBiao_total_mv != 0 ){

                return 1;
            }
            if(o1.MeiRiZhiBiao_total_mv != 0 && o2.MeiRiZhiBiao_total_mv == 0 ){

                return -1;
            }

            if(o1.MeiRiZhiBiao_total_mv == 0 && o2.MeiRiZhiBiao_total_mv == 0 ){

                return 0;
            }


            double o1_flowrate = (o1.MeiRiZhiBiao_total_mv - o1.MeiRiZhiBiao_circ_mv)/o1.MeiRiZhiBiao_total_mv;
            double o2_flowrate = (o2.MeiRiZhiBiao_total_mv - o2.MeiRiZhiBiao_circ_mv)/o2.MeiRiZhiBiao_total_mv;


            if(o1_flowrate > o2_flowrate){
                return 1 ;
            } else         if(o1_flowrate < o2_flowrate){
                return -1 ;
            }else   if(o1_flowrate == o2_flowrate){
                return 0 ;
            }
            return 0 ;
        }
    };



    //
    static void  init_level1Name_level2StockImpl_Map_SortType(String bangdanStr , Comparator<Stock_NodeImpl> comparator) {
        if(level1Name_level2StockImpl_Map != null){

            ArrayList<Stock_NodeImpl> stockArrList =    level1Name_level2StockImpl_Map.get(bangdanStr);

            if(stockArrList != null){
                stockArrList.sort(comparator);
            }

        }



    }


    static void show_level1Name_level2StockImpl_Map() {


        Map.Entry<String, ArrayList<Stock_NodeImpl>> stockmap_entry;
        if(level1Name_level2StockImpl_Map != null){
            print("show_level1Name_level2StockImpl_Map   level1Name_level2StockImpl_Map != null");
            Iterator iterator = level1Name_level2StockImpl_Map.entrySet().iterator();

            while (iterator.hasNext()) {
                stockmap_entry = (Map.Entry<String, ArrayList<Stock_NodeImpl>>) iterator.next();

                //  获取 名称的 首字母
                String arrTag = stockmap_entry.getKey(); // Map的Value  // 作者名称
                ArrayList<Stock_NodeImpl> stockArrList = stockmap_entry.getValue();
                print("level1Name_level2StockImpl_Map Key["+arrTag+"]  ArrSize["+stockArrList.size()+"]");
            }
        }else{
            print("show_level1Name_level2StockImpl_Map   level1Name_level2StockImpl_Map == null");
        }

    }

    static  ArrayList<Stock_NodeImpl> Sort_level1Name_level2StockImplMap_BackStockList(String[] stockblock) {
        ArrayList<Stock_NodeImpl> sortedArr = new   ArrayList<Stock_NodeImpl>(10000);
        for (int i = 0; i < stockblock.length ; i++) {
            String keyStr = stockblock[i];
            ArrayList<Stock_NodeImpl>  stockArr =  level1Name_level2StockImpl_Map.get(keyStr);
            if(keyStr.equals("IPO新榜") && stockArr != null){
                print("A zzpre keyStr["+keyStr+"]  ArrSize= "+ stockArr.size());
                stockArr.sort(list_date_timedown_StockComparator);
            }else   if(keyStr.equals("退市板") && stockArr != null ){
                print("B zzpre keyStr["+keyStr+"]  ArrSize= "+ stockArr.size());
                stockArr.sort(list_date_timedown_StockComparator);
            } else {

                if( stockArr != null){
                    print("C zzpre keyStr["+keyStr+"]  ArrSize= "+ stockArr.size());
                    stockArr.sort(pricedown_StockComparator);
                }else {
                    print("D zzpre keyStr["+keyStr+"]  ArrSize= null ");
                }

            }

            if(stockArr != null){
                sortedArr.addAll(stockArr);
            }
        }


        return sortedArr;
    }

    static void showLogInfo() {


        ArrayList<String> gupiaoliebiao_Content = new ArrayList<String>();

        // 对 股票列表 进行 排序  这里 需要 解耦 分隔
//        gupiaoliebiao_StockList.sort(pricedown_StockComparator);
        show_level1Name_level2StockImpl_Map();

        //  对股票列表进行 重新排序
        print("zzpre A----- gupiaoliebiao_StockList.size() = " + gupiaoliebiao_StockList.size());

// 此处 gupiaoliebiao_StockList.size() = 4798  但 level1Name_level2StockImplMap 为 空
//       gupiaoliebiao_StockList =    Sort_level1Name_level2StockImplMap_BackStockList(StockBlock);
        print("zzpre B----- gupiaoliebiao_StockList.size() = " + gupiaoliebiao_StockList.size());

/*
        2-08 15:44:15.696 6194-14317/? E/AndroidRuntime: FATAL EXCEPTION: Thread-7182
        Process: packagename.web, PID: 6194
        java.util.ConcurrentModificationException
        at java.util.ArrayList$Itr.next(ArrayList.java:860)
        at com.and.zmain_life.bean.StockHolder.showLogInfo(StockHolder.java:1311)
        at com.and.zmain_life.bean.StockHolder$3.run(StockHolder.java:1064)
        at java.lang.Thread.run(Thread.java:923)*/

        int srockLength = gupiaoliebiao_StockList.size();
        synchronized(gupiaoliebiao_StockList){
            for ( Stock_NodeImpl item : gupiaoliebiao_StockList) {

                if(item == null){
                    continue;
                }


                String stock_toSting = item.toString();

                gupiaoliebiao_Content.add(stock_toSting);
                item.setShowIndex(srockLength-stock_index);  // 最新上市的股票的 索引 最后
                android.util.Log.i(TAG," zindex["+(stock_index)+"] Stock_NodeImpl["+stock_toSting+"]");
//                print(" zindex["+stock_index+"] Stock_NodeImpl["+Stock_NodeImpl.toString()+"]");
                stock_index++;

            }
        }


        print("TScode_List.size() = " + TScode_List.size());
        print("zindex gupiaoliebiao_StockList.size() = " + gupiaoliebiao_StockList.size());
        print("zindex gupiaoliebiao_Content.size() = " + gupiaoliebiao_Content.size());
        writeContentToFile(mStock_Log_File, gupiaoliebiao_Content);
        showStockLogPullCommand(mStock_Log_File, "Stock_NodeImpl数组的Log(奇怪单独打印总是跳索引)");

    }

    // 读取 json的 列表
    static void KindOfJsonArrWithKey(String stcKeyStr , JSONArray jsonArr){

        String stcKeyStr_clearChinese_FirstCharUP_Str = Rule14_ToPinyin_WithFirstBig(stcKeyStr);


        switch(stcKeyStr_clearChinese_FirstCharUP_Str){
            case "DaZongJiaoYi":

//                List<JmtCase(实体类)> collection = JSONObject.parseArray(js, JmtCase.class);

                List<DaZongJiaoYi> DaZongJiaoYiList =  jsonArr.toJavaList(DaZongJiaoYi.class);
                print("JSON文件  KindOfJsonArrWithKey   【DaZongJiaoYiList="+DaZongJiaoYiList.size()+"】");

                break;
            case "GangGuTongMeiRiChengJiaoTongJi":
                List<GangGuTongMeiRiChengJiaoTongJi> GangGuTongMeiRiChengJiaoTongJiList =  jsonArr.toJavaList(GangGuTongMeiRiChengJiaoTongJi.class);
                print("JSON文件  KindOfJsonArrWithKey   【GangGuTongMeiRiChengJiaoTongJiList="+GangGuTongMeiRiChengJiaoTongJiList.size()+"】");

                break;
            case "GangGuTongShiDaChengJiaoGu":

                List<GangGuTongShiDaChengJiaoGu> GangGuTongShiDaChengJiaoGuList =  jsonArr.toJavaList(GangGuTongShiDaChengJiaoGu.class);
                print("JSON文件  KindOfJsonArrWithKey   【GangGuTongShiDaChengJiaoGuList="+GangGuTongShiDaChengJiaoGuList.size()+"】");


                break;
            case "GeGuZiJinLiuXiang":


                List<GeGuZiJinLiuXiang> GeGuZiJinLiuXiangList =  jsonArr.toJavaList(GeGuZiJinLiuXiang.class);
                print("JSON文件  KindOfJsonArrWithKey   【GeGuZiJinLiuXiangList="+GeGuZiJinLiuXiangList.size()+"】");


                break;
            case "GuDongZengJianChi":

                List<GuDongZengJianChi> GuDongZengJianChi =  jsonArr.toJavaList(GuDongZengJianChi.class);
                print("JSON文件  KindOfJsonArrWithKey   【GuDongZengJianChi="+GuDongZengJianChi.size()+"】");



                break;
            case "GuPiaoHuiGou":

                List<GuPiaoHuiGou> GuPiaoHuiGou =  jsonArr.toJavaList(GuPiaoHuiGou.class);
                print("JSON文件  KindOfJsonArrWithKey   【GuPiaoHuiGou="+GuPiaoHuiGou.size()+"】");

                break;
            case "HuShenGangGuTongChiGuMingXi":

                List<HuShenGangGuTongChiGuMingXi> HuShenGangGuTongChiGuMingXi =  jsonArr.toJavaList(HuShenGangGuTongChiGuMingXi.class);
                print("JSON文件  KindOfJsonArrWithKey   【HuShenGangGuTongChiGuMingXi="+HuShenGangGuTongChiGuMingXi.size()+"】");

                break;
            case "HuShenGangTongZiJinLiuXiang":

                List<HuShenGangTongZiJinLiuXiang> HuShenGangTongZiJinLiuXiang =  jsonArr.toJavaList(HuShenGangTongZiJinLiuXiang.class);
                print("JSON文件  KindOfJsonArrWithKey   【HuShenGangTongZiJinLiuXiang="+HuShenGangTongZiJinLiuXiang.size()+"】");


                break;
            case "HuShenGuTongShiDaChengJiaoGu":


                List<HuShenGuTongShiDaChengJiaoGu> HuShenGuTongShiDaChengJiaoGu =  jsonArr.toJavaList(HuShenGuTongShiDaChengJiaoGu.class);
                print("JSON文件  KindOfJsonArrWithKey   【HuShenGuTongShiDaChengJiaoGu="+HuShenGuTongShiDaChengJiaoGu.size()+"】");



                break;
            case "LongHuBangJiGouMingXi":


                List<LongHuBangJiGouMingXi> LongHuBangJiGouMingXi =  jsonArr.toJavaList(LongHuBangJiGouMingXi.class);
                print("JSON文件  KindOfJsonArrWithKey   【LongHuBangJiGouMingXi="+LongHuBangJiGouMingXi.size()+"】");



                break;
            case "LongHuBangMeiRiMingXi":

                List<LongHuBangMeiRiMingXi> LongHuBangMeiRiMingXi =  jsonArr.toJavaList(LongHuBangMeiRiMingXi.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【LongHuBangJiGouMingXi="+LongHuBangMeiRiMingXi.size()+"】");


                break;
            case "MeiRiTingFuPaiXinXi":

                List<MeiRiTingFuPaiXinXi> MeiRiTingFuPaiXinXi =  jsonArr.toJavaList(MeiRiTingFuPaiXinXi.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【MeiRiTingFuPaiXinXi="+MeiRiTingFuPaiXinXi.size()+"】");


                break;
            case "MeiRiZhangDieTingJiaGe":


                List<MeiRiZhangDieTingJiaGe> MeiRiZhangDieTingJiaGe =  jsonArr.toJavaList(MeiRiZhangDieTingJiaGe.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【MeiRiZhangDieTingJiaGe="+MeiRiZhangDieTingJiaGe.size()+"】");



                break;
            case "MeiRiZhiBiao":


                List<MeiRiZhiBiao> MeiRiZhiBiao =  jsonArr.toJavaList(MeiRiZhiBiao.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【MeiRiZhiBiao="+(MeiRiZhiBiao != null? MeiRiZhiBiao.size():" null ")+"】");
                if(MeiRiZhiBiao != null && MeiRiZhiBiao.size() >0){
                    InitArr_With_MeiRiZhiBiao(MeiRiZhiBiao);  //  把  每日指标的数据进行分析
                }


                break;
            case "RiXianXingQingvShiJianWeiXu":


                List<RiXianXingQingvShiJianWeiXu> RiXianXingQingvShiJianWeiXu =  jsonArr.toJavaList(RiXianXingQingvShiJianWeiXu.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【RiXianXingQingvShiJianWeiXu="+RiXianXingQingvShiJianWeiXu.size()+"】");

                if(RiXianXingQingvShiJianWeiXu != null && RiXianXingQingvShiJianWeiXu.size() >0){
                    InitArr_With_RiXianXingQingvShiJianWeiXu(RiXianXingQingvShiJianWeiXu);  //  把  每日指标的数据进行分析
                }


                break;
            case "RongZiRongQuanJiaoYiHuiZong":

                List<RongZiRongQuanJiaoYiHuiZong> RongZiRongQuanJiaoYiHuiZong =  jsonArr.toJavaList(RongZiRongQuanJiaoYiHuiZong.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【RongZiRongQuanJiaoYiHuiZong="+RongZiRongQuanJiaoYiHuiZong.size()+"】");


                break;
            case "RongZiRongQuanJiaoYiMingXi":

                List<RongZiRongQuanJiaoYiMingXi> RongZiRongQuanJiaoYiMingXi =  jsonArr.toJavaList(RongZiRongQuanJiaoYiMingXi.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【RongZiRongQuanJiaoYiMingXi="+RongZiRongQuanJiaoYiMingXi.size()+"】");


                break;
            case "XianShouGuJieJin":

                List<XianShouGuJieJin> XianShouGuJieJin =  jsonArr.toJavaList(XianShouGuJieJin.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【XianShouGuJieJin="+XianShouGuJieJin.size()+"】");


                break;
            case "XinGuLieBiaoIPO":

                List<XinGuLieBiaoIPO> XinGuLieBiaoIPO =  jsonArr.toJavaList(XinGuLieBiaoIPO.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【XinGuLieBiaoIPO="+XinGuLieBiaoIPO.size()+"】");

                if(XinGuLieBiaoIPO != null && XinGuLieBiaoIPO.size() >0){
                    InitArr_With_XinGuLieBiaoIPO(XinGuLieBiaoIPO);  //  把  每日指标的数据进行分析
                }



                break;
            case "YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi":

                List<YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi> YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi =  jsonArr.toJavaList(YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi="+YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi.size()+"】");


                break;
            case "ZhouXianXingQingvShiJianWeiXu":

                List<ZhouXianXingQingvShiJianWeiXu> ZhouXianXingQingvShiJianWeiXu =  jsonArr.toJavaList(ZhouXianXingQingvShiJianWeiXu.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【ZhouXianXingQingvShiJianWeiXu="+ZhouXianXingQingvShiJianWeiXu.size()+"】");


                break;
            case "ZuiJinYueZhangDieTingTongJi":

                List<ZuiJinYueZhangDieTingTongJi> ZuiJinYueZhangDieTingTongJi =  jsonArr.toJavaList(ZuiJinYueZhangDieTingTongJi.class);
                print("JSON文件  LongHuBangMeiRiMingXi   【ZuiJinYueZhangDieTingTongJi="+ZuiJinYueZhangDieTingTongJi.size()+"】");


                break;



            default:
                print("当前的 Key名不存在对应的Bean类 stcKeyStr=【"+stcKeyStr+"】 clearChinese_StrKey=【"+stcKeyStr_clearChinese_FirstCharUP_Str+"】");
        }



    }

    static void  showStockLogPullCommand(File targetFile,String tip){
        if(targetFile == null){
            android.util.Log.i("zindex","═════【"+tip+" <当前文件为空！！>】═════    "  +  "adb pull "+ targetFile.getAbsolutePath()+" . "+ "    ══════════" );

            return;
        }
        android.util.Log.i("zindex","═════【"+tip+"】═════    "  +  "adb pull "+ targetFile.getAbsolutePath()+" . "+ "    ══════════" );
    }



    static ArrayList<String> readFile(File file) {

        ArrayList<String>  rawContentList = new  ArrayList<String>();
        if (file != null && file.exists()) {

            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                String lineStr = "";

                while (lineStr != null) {
                    lineStr = curBR.readLine();
                    if (lineStr == null || lineStr.trim().isEmpty()) {
                        continue;
                    }

                    rawContentList.add(lineStr.trim());
                }
                curBR.close();

                System.out.println("read "+file.getAbsolutePath()+" File OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("read "+file.getAbsolutePath()+" File Failed !");
        }

        return rawContentList;
    }


    static void tryReadJsonFromFile(StringBuilder sb, File file) {

        if (file != null && file.exists()) {

            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                String lineStr = "";

                while (lineStr != null) {
                    lineStr = curBR.readLine();
                    if (lineStr == null || lineStr.trim().isEmpty()) {
                        continue;
                    }

                    sb.append(lineStr.trim());
                }
                curBR.close();

                System.out.println("read json File OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }

    static  volatile int stock_index = 1 ;
    @RequiresApi(api = Build.VERSION_CODES.N)
    static void  initTsCodeList(){
// TScode_List
        print("stock_initTsCodeLis方法_Begin");
        File ts_code_File = new File(J0_GuPiaoLieBiao_Path);
        if(!ts_code_File.exists()){
            System.out.println("当前 没有 基础数据文件【股票列表.xlsx】( 请添置该文件 ) J0_GuPiaoLieBiao_Path ="+ J0_GuPiaoLieBiao_Path);
            return;
        }


        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = ts_code_File.getAbsolutePath();
        System.out.println("xlsx Path = "+ filePath);
        String columns[] = {"ts_code","symbol","name","area","industry","fullname","enname","market","exchange","curr_type","list_status","list_date","delist_date","is_hs"};
        wb = readExcel(filePath);

        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
//            sheet = wb.getSheet("股票列表");
//            sheet = wb.getSheetAt(0);
            sheet = wb.getSheet("股票列表");
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }
        //遍历解析出来的list


        int row_index = 1 ;
        int column_index = 1;
        if (list != null) {
            for (Map<String,String> map : list) {
                Stock_NodeImpl Stock_NodeImpl = new Stock_NodeImpl(getNextStockMarketNodeID(),2);

                for (Map.Entry<String,String> entry : map.entrySet()) {
//                System.out.print(entry.getKey()+":"+entry.getValue()+",");

                    if("ts_code".equals(entry.getKey())){
                        TScode_List.add(entry.getValue());
                    }

//                print("row["+row_index+"] column["+column_index+"]  key["+entry.getKey()+"] value=["+entry.getValue()+"]");
                    column_index++;
                    //    System.out.println(" entry.getKey() = "+entry.getKey() + "   entry.getValue() = "+ entry.getValue()+"【Over】");
                    Stock_NodeImpl.initKeyAndValue(entry.getKey(),entry.getValue());
                }
                row_index++;
                column_index=1;
                gupiaoliebiao_StockList.add(Stock_NodeImpl);
//            System.out.println();
            }
        }

        try{

            TScode_List.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
        } catch ( Exception e){
            print(" TScode_List.sort 发生异常 == "+e);
        }



        print("stock_initTsCodeLis方法_End");


    }


    //Arralist json转化为 Arraylist 对象
    public static  ArrayList<Stock_NodeImpl> getArrObj4Json_SockItem(String jsonstr, Stock_NodeImpl t){
        ArrayList<Stock_NodeImpl> obj= (ArrayList<Stock_NodeImpl>) com.alibaba.fastjson.JSONObject.parseArray(jsonstr, t.getClass());
        return obj;
    }


    //将对象转化成json String
    public static String getJson4Arr_SockItem(ArrayList<Stock_NodeImpl> t){
        ObjectMapper mapper = new ObjectMapper();
        String jsonstr = null;
        try {
            jsonstr = mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonstr;
    }

    static void writeContentToFile(File file,String oneLineStr) {

        String endTagDefault = "\n";  // 默认是 Linux下的 换行符


        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(oneLineStr+endTagDefault);
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

    static void writeContentToFile(File file, ArrayList<String> strList) {
        // PC 以 \r\n 结尾
        // Unix  以 \n  结尾
        // dos2unix 是在末尾把 \r 去掉   所以 文件会变小
        // unix2dos 是在文件末尾把 \n 之前加上  \r\n  所以文件会变大
//    	System.setProperty(“line.separator", "\r\n")"
        String endTagDefault = "\n";  // 默认是 Linux下的 换行符

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {

            sb.append(strList.get(i) + endTagDefault);
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

    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC :{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

    /**
     * 读取Excel文件
     * @param file
     * @throws FileNotFoundException
     */
    public static void readExcel(File file) throws FileNotFoundException {
        if(file == null) {
            print("NullFile 读取Excel出错，文件为空文件");
            return;
        }
        InputStream stream = new FileInputStream(file);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int r = 0; r<rowsCount; r++) {
                Row row = sheet.getRow(r);
                int cellsCount = row.getPhysicalNumberOfCells();
                //每次读取一行的内容
                for (int c = 0; c<cellsCount; c++) {
                    //将每一格子的内容转换为字符串形式
                    String value = getCellAsString(row, c, formulaEvaluator);
                    String cellInfo = "r:"+r+"; c:"+c+"; v:"+value;
                    print(cellInfo);
                }
            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            print(e.toString());
        }

    }

    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        File curFile = new File(filePath);
        if(filePath==null){
            return null;
        }
        if(!curFile.exists()){
            print("curFile = "+ curFile.getAbsolutePath()+"  不存在 无法读取!! ");
            return null;
        }else{
            print("curFile = "+ curFile.getAbsolutePath()+"  存在 !! ");
        }
        print("filePath = "+ filePath);
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 读取excel文件中每一行的内容
     * @param row
     * @param c
     * @param formulaEvaluator
     * @return
     */
    private static String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e) {
            /* proper error handling should be here */
            print(e.toString());
        }
        return value;
    }


    public volatile static int StockMarket_Node_ID_Num = 0;
    public synchronized static int getNextStockMarketNodeID() {
        StockMarket_Node_ID_Num++;
        return StockMarket_Node_ID_Num;
    }


    public volatile static int StockArea_Node_ID_Num = 0;
    public synchronized static int getNextStockAreaNodeID() {
        StockArea_Node_ID_Num++;
        return StockArea_Node_ID_Num;
    }



    //  按索引来区分股票
    // 按市值来区分股票 //

    // 按 日期 来区分 股票
    static void releasionship_level1_level2_stocknode_ByDate(Stock_NodeImpl level1_stocknode ,Stock_NodeImpl level2_stocknode ){

    }


    // 按 地域 来区分 股票
    static void releasionship_level1_level2_stocknode_ByArea(Stock_NodeImpl level1_stocknode ,Stock_NodeImpl level2_stocknode ){

    }

    static void releasionship_level1_level2_stocknode_ByIndistry(Stock_NodeImpl level1_stocknode ,Stock_NodeImpl level2_stocknode ){

    }

    public  static void level2_Init_level3Node_Operation(Stock_Node  stocknode ) {

        String level2Json =   stocknode.json();
        Stock_NodeImpl mStock_NodeImpl = JSONObject.parseObject(level2Json,Stock_NodeImpl.class);

        android.util.Log.i("refreshLevel3Adapter","mStock_NodeImpl ="+mStock_NodeImpl.toString());
        android.util.Log.i("refreshLevel3Adapter",level2Json);
        ArrayList<Stock_NodeImpl> node3List =   StockHolder.calNode3ListWithNode2(mStock_NodeImpl);

        stocknode.setChildren(node3List);
    }


    //    不需要定义的 level3 显示的 属性的 名称的集合
    public static ArrayList<String> stockImpl_noneed_propList ;

    //  不需要的属性的 集合   color_int   count enname  delist_date  curr_type REGEX_CHINESE EMPTY
    // XinGuLieBiaoIPO_sub_code symbol parentid list_status level showindex
// zukgit
    static void  init_No_Define_StockPropName(){
        stockImpl_noneed_propList = new ArrayList<String>();
        stockImpl_noneed_propList.add("color_int");
        stockImpl_noneed_propList.add("enname");
        stockImpl_noneed_propList.add("XinGuLieBiaoIPO_cname");
        stockImpl_noneed_propList.add("selectedChildId");
        stockImpl_noneed_propList.add("positionInAdapter");

        stockImpl_noneed_propList.add("curr_type");
        stockImpl_noneed_propList.add("REGEX_CHINESE");
        stockImpl_noneed_propList.add("EMPTY");
        stockImpl_noneed_propList.add("XinGuLieBiaoIPO_sub_code");

        stockImpl_noneed_propList.add("parentid");
        stockImpl_noneed_propList.add("list_status");
        stockImpl_noneed_propList.add("level");
        stockImpl_noneed_propList.add("showindex");
        stockImpl_noneed_propList.add("id");
        stockImpl_noneed_propList.add("children");
        stockImpl_noneed_propList.add("count");
        stockImpl_noneed_propList.add("XinGuLieBiaoIPO_ts_code");
        stockImpl_noneed_propList.add("XinGuLieBiaoIPO_limit_amount");
        stockImpl_noneed_propList.add("XinGuLieBiaoIPO_name");
        stockImpl_noneed_propList.add("level3_key");
        stockImpl_noneed_propList.add("list_date");
        stockImpl_noneed_propList.add("level3_value");
        stockImpl_noneed_propList.add("ts_code");
    }
    // 太消耗 内存  换种方式实现
    public  static ArrayList<Stock_NodeImpl> calNode3ListWithNode2(Stock_NodeImpl level2_stocknode ){
        ArrayList<Stock_NodeImpl> node3List = new ArrayList<Stock_NodeImpl>();
        if(stockImpl_noneed_propList == null){
            init_No_Define_StockPropName();
        }
        android.util.Log.i("StockItem"," Stock_NodeImpl_Level3  调用 level2_Init_level3Node_Operation ");
        try {

            Field[] fields =   level2_stocknode.getClass().getDeclaredFields();

            if(fields == null){
                android.util.Log.i("StockItem"," Stock_NodeImpl_Level3 FiledList 为空！！");
                return null;
            }
            android.util.Log.i("StockItem"," Stock_NodeImpl_Level3 fields.length="+fields.length);

            for (int i = 0; i < fields.length; i++) {
                Field fielditem = fields[i];
                fielditem.setAccessible(true);
                String fieldName = fielditem.getName();
                android.util.Log.i("StockItem"," Stock_NodeImpl_Level3 showField fieldName="+fieldName+"   fielditem="+fielditem.toString());

            }
            for (int i = 0; i < fields.length; i++) {
                Field fielditem = fields[i];
                String fieldName = fielditem.getName();
                fielditem.setAccessible(true);
                android.util.Log.i("StockItem"," Stock_NodeImpl_Level3 fieldName="+fieldName+"   fielditem="+fielditem.toString());

                // 去除属性      工作量 太大  需要重新规划
                if(!stockImpl_noneed_propList.contains(fieldName)){
                    String fieldValue = fielditem.get(level2_stocknode)+"";

                    //  Stock_NodeImpl( int mid  , int  level3 , String mlevel3_key , String mlevel3_value  )
                    Stock_NodeImpl level3Node = new Stock_NodeImpl(getNextStockMarketNodeID(),3,fieldName,fieldValue);
                    node3List.add(level3Node);
                    android.util.Log.i("Stock_NodeImpl_Level3AA"," index["+i+"] fielditem.getName()="+fielditem.getName() +"   fielditem.toString()="+fielditem.toString()  +"   fielditem.toGenericString()="+fielditem.toGenericString());

                }
                android.util.Log.i("Stock_NodeImpl_Level3BB"," index["+i+"] fielditem.getName()="+fielditem.getName() +"   fielditem.toString()="+fielditem.toString()  +"   fielditem.toGenericString()="+fielditem.toGenericString());
            }


            android.util.Log.i("StockItem"," Stock_NodeImpl_Level3 Finish ZZZ ");
        } catch (IllegalAccessException e) {
            android.util.Log.i("StockItem"," Stock_NodeImpl_Level3 level2_Init_level3Node_Operation  发生异常  " + e.fillInStackTrace().toString());
            e.printStackTrace();
        }

        return node3List;

//        public String level3_key ;   // 作为 地3 层 显示的 key , ts_code  name  , market  , .....
//        public String level3_value ;   // level3_key 对应的 值得字符串


    }

    // 按 市场股票 来区分股票
    static void releasionship_level1_level2_stocknode_ByMarket(Stock_NodeImpl level1_stocknode ,Stock_NodeImpl level2_stocknode ){
        if(level1_stocknode.children.contains(level2_stocknode)) {  // 已经 有了 这个部下 那么 不操作 返回
            return ;
        }

/*       public  String area;     // 地域  上海 广东  北京   //  https://tushare.pro/document/2?doc_id=25
       public  String curr_type;
       public   String name;    // 股票简称  一般四个字
       public  String enname;          // 股票英文名称
       public  String exchange;   // SSE--上市   SZSE----深市
       public  String fullname;    // 股票全称
       public  String industry;   // 所属行业
       public  String is_hs;   //  是否沪深港通标的，N否 H沪股通 S深股通
       public  String list_date;      //  上市日期   20040312
       public  String delist_date;   //  退市日期  20200825
       public  String list_status;  //   L--上市    P---退市  D---退市
       public  String market;   // 主板   中小板  科创板  创业板
       public  String symbol;   //  600004    300321
       public  String ts_code;   // 600004.SH   300321.SZ*/
//        static String[] StockBlock = new String[]{
//        "All板","沪市","深市","创业板","科创板","IPO新板",中小板, 退市股板,"个股资金流向榜","Day_Holder涨跌板",
//        "龙虎榜","Day_Holder沪深股通十大成交股","Month_Holder涨跌停板",
//        "大宗交易信息榜","Year_Holder沪深港资金流向榜","沪深港持股明细榜",// 融资融券交易明细榜
//        "股票回购信息榜","限售股解禁榜","停复牌板","近三月股东增减持信息榜"};

        String exchange = level2_stocknode.exchange;
        String market = level2_stocknode.market;
        String  list_status  = level2_stocknode.list_status;
        String stockName = level2_stocknode.name;
        boolean isIPOstock = level2_stocknode.XinGuLieBiaoIPO_isIPO;



/*
      final  String dayrank_zhangdiebang =  ("Day_Holder涨跌板".replace("Day_Holder",StockHolder.day_lastjson_Day+"日"));

        final  String dayrank_hushengutongshidachengjiaogu =  "Day_Holder沪深股通十大成交股".replace("Day_Holder",StockHolder.day_lastjson_Day+"日");
        final   String monthRank_zhangdietingbang =  "Month_Holder涨跌停板".replace("Month_Holder",StockHolder.day_lastjson_Month+"月");

        final String yearRank =  "Year_Holder沪深港资金流向榜".replace("Year_Holder",StockHolder.day_lastjson_Year+"年");
*/

        String switchTypeValue = level1_stocknode.name.replace(StockHolder.day_lastjson_Month+"月","Month_Holder");
        switchTypeValue = switchTypeValue.replace(StockHolder.day_lastjson_Day+"日","Day_Holder");
        switchTypeValue = switchTypeValue.replace(StockHolder.day_lastjson_Year+"年","Year_Holder");


// zukgitxxxxxxxxxxxx
        switch (switchTypeValue){
            case "All板":   // All 榜 就是把所有 股票都列入它
                if("L".equals(list_status) && isIPOstock == false){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);

                }

                break;

            case "收藏板":   // All 榜 就是把所有 股票都列入它
           // level2_tscode   包含的tscode 从 .txt 中去读取
                if(mAttentionStockImpl_List != null && mAttentionStockImpl_List.contains(level2_stocknode)){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);


                }
                break;





            case "沪市":   // 沪市 就是 stocknode 的 exchange 属性为 SSE

//                if( "SSE".equals(exchange) && "L".equals(list_status) &&  !stockName.contains("ST") ){
                if( "SSE".equals(exchange) && "L".equals(list_status) && "主板".equals(market)  ){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);
                }
                break;

            case "深市":   // 深市 就是 stocknode 的 exchange 属性为 SZSE  并且 me
//                if( "SZSE".equals(exchange) &&  "L".equals(list_status) &&  !stockName.contains("ST") && "主板".equals(market)  ){
                if( "SZSE".equals(exchange) &&  "L".equals(list_status)  && "主板".equals(market)  ){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);
                }
                break;

            case "创业板":   // 创业板 就是 stocknode 的 market 属性为 创业板
                if("创业板".equals(market) && "L".equals(list_status) &&  !stockName.contains("ST")){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);
                }
                break;

            case "科创板":   // 科创板 就是 stocknode 的 market 属性为 科创板
                if("科创板".equals(market) &&  "L".equals(list_status)){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);
                }
                break;

            case "ST板":   //  ST 板 就是  股票名称中 包含 ST 的股票 的集合
                if("L".equals(list_status) && stockName.contains("ST")){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);
                }
                break;


            case "退市板":   // 退市股板 就是属性 list_status 不为 L 的 股票
                if(!"L".equals(list_status) && isIPOstock == false){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);
                }
                break;

            case "中小板":   // 中小板 就是 stocknode 的 market 属性为 中小板
                if( "中小板".equals(market) && "L".equals(list_status) ){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);
                }
                break;

            case "IPO新板":   //
                // 待实现   需要读取 另外的 xlsx文件生成 该数据
                if(isIPOstock){
                    level1_stocknode.children.add(level2_stocknode);
                    level1Name_level2StockImpl_AddItem(switchTypeValue,level2_stocknode);
                }

                break;

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
                android.util.Log.i("zukgitode_ByMarket","  level1_stocknode.name="+level1_stocknode.name+" 没有找到板单");
                //    System.out.println("当前没有找到 level1=【"+ level1_stocknode.toString()+"】  level2=【"+level2_stocknode.toString()+"】 的关系");
        }



    }



    public static void level1Name_level2StockImpl_AddItem(String switchTypeValue , Stock_NodeImpl level2_stocknode){

        ArrayList<Stock_NodeImpl> allBangArr = level1Name_level2StockImpl_Map.get(switchTypeValue);
        print("level1Name_level2StockImpl   Add Item switchTypeValue =" + switchTypeValue +"   allBangArr.contains(level2_stocknode)="+allBangArr.contains(level2_stocknode));
        if(allBangArr != null && !allBangArr.contains(level2_stocknode)){
            allBangArr.add(level2_stocknode);

        }else{

        }
    }
    public static int  getCharCount(String originStr, String charStr){
        int count  = 0 ;
        if(originStr == null || charStr == null){

            return count;
        }
        for (int i = 0; i < originStr.length(); i++) {
            String charOne = originStr.substring(i,i+1);
            if(charStr.equals(charOne)){
                count++;
            }
        }
        return count;

    }
    public static void xxx(String repeatSrc, int repeatCount) {
        String chinese_block="　";
        String english_block=" ";
        String  little_block=" ";

    }



    public static String getRepeatString(String repeatSrc, int repeatCount) {
        if(repeatCount <= 0){
            return "";
        }
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }



    public synchronized static String calAreaIndustryJson() {
        if(!isGuPiaoLieBiao_FInish_MapInit_Flag){   // 还没完成 股票 列表的填装
            calGuPiaoLieBiaoJson();
            return "";
        }

        if (AreaPage_RootStockNode_Json_Str != null) {
            return AreaPage_RootStockNode_Json_Str;
        }

        return AreaPage_RootStockNode_Json_Str;
    }







    // 返回给 Stock_Common_Fragment 的 股票的 列表
    public synchronized static String calGuPiaoLieBiaoJson_With_Order(int orderIndex ) {




        if(Root_StockNodeImpl != null){
            return Root_StockNodeImpl.json();
        }




        if (mStock_Log_File == null ||  mStock_file_list == null ||  mStock_file_list.length == 0) {
            android.util.Log.i("stock_json_result", "没有读取到  /sdcard/zmain/stock/目录下的文件 或者该目录下文件为0  没有 J0_股票列表.xlsx !");
            return Root_StockNodeImpl.json();
        }






        if (level_1_StockBlock_nodeList == null) {
            level_1_StockBlock_nodeList = new ArrayList<Stock_NodeImpl>();
        }
        if (level_2_ShortStock_nodeList == null) {
            level_2_ShortStock_nodeList = new ArrayList<Stock_NodeImpl>();
        }

        if (level_3_DetaiLStock_nodeList == null) {
            level_3_DetaiLStock_nodeList = new ArrayList<Stock_NodeImpl>();
        }

        level_1_StockBlock_nodeList = new ArrayList<Stock_NodeImpl>();
        level_2_ShortStock_nodeList = new ArrayList<Stock_NodeImpl>();
        level_3_DetaiLStock_nodeList = new ArrayList<Stock_NodeImpl>();


        ArrayList<Stock_NodeImpl> StockBlock_node_list = new ArrayList<Stock_NodeImpl>();



        for (int i = 0; i < StockBlock.length; i++) {
            String stockBlock = StockBlock[i];

            // 初始化 榜单的 各个 Map
            if(level1Name_level2StockImpl_Map.get(stockBlock) == null){
                ArrayList<Stock_NodeImpl> bangdan_level2_nodeList = new   ArrayList<Stock_NodeImpl>(10000);
                level1Name_level2StockImpl_Map.put(stockBlock,bangdan_level2_nodeList);
                print("level1Name_level2StockImpl_Map == null  for key["+stockBlock+"] initArr ");
            }else{
                print("level1Name_level2StockImpl_Map != null  for key["+stockBlock+"]");
            }
            System.out.println("day_lastjson_Year = "+ day_lastjson_Year +"   day_lastjson_Month="+day_lastjson_Month+"   day_lastjson_Day="+day_lastjson_Day);
            if(day_lastjson_Year != null &&  day_lastjson_Month != null && day_lastjson_Day != null ){
                if(stockBlock.contains("Day_Holder")){
                    stockBlock = stockBlock.replace("Day_Holder",day_lastjson_Day+"日");
                }else if(stockBlock.contains("Month_Holder")){
                    stockBlock = stockBlock.replace("Month_Holder",day_lastjson_Month+"月");
                }else if(stockBlock.contains("Year_Holder")){
                    stockBlock = stockBlock.replace("Year_Holder",day_lastjson_Year+"年");
                }

            }



            // Stock_NodeImpl(long id, long xparentid ,String mts_code  ,int mlevel )
            Stock_NodeImpl mLevel1_StockNodeImpl = new Stock_NodeImpl(getNextStockMarketNodeID(), 0, stockBlock, 1);

            //

            if("收藏板".equals(stockBlock)){
                AttentionRank_Level1_Stock_NodeImpl = mLevel1_StockNodeImpl;
            }

            StockBlock_node_list.add(mLevel1_StockNodeImpl);
            level_1_StockBlock_nodeList.add(mLevel1_StockNodeImpl);

        }
        Root_StockNodeImpl.children.addAll(StockBlock_node_list);
        //  把 当前 目录下的 mp3  依据  作者分类 分好


        for (int i = 0; i < StockBlock_node_list.size(); i++) {
            Stock_NodeImpl  level1_stocknode = StockBlock_node_list.get(i);
            if(level1_stocknode == null){
                continue;
            }

            for (int j = 0; j < gupiaoliebiao_StockList.size()  ; j++) {
                Stock_NodeImpl level2_stocknode = gupiaoliebiao_StockList.get(j);
//                level2_Init_level3Node_Operation(level2_stocknode);   // 动态创建自己的child 这个就是一些 详细的信息
                //  node1  和  node2 形成 关系的  函数
                releasionship_level1_level2_stocknode_ByMarket(level1_stocknode,level2_stocknode);
                level_2_ShortStock_nodeList.add(level2_stocknode);
            }
            level1_stocknode.setCount(level1_stocknode.children.size());
            System.out.println("zzpre1__ level1_stocknode.text = "+ level1_stocknode.text());
            System.out.println("zzpre1__ level1_stocknode.children.size() = "+ level1_stocknode.children.size());
            if(level1_stocknode.text() != null && level1_stocknode.text().contains("IPO新板")){

                level1_stocknode.children.sort(list_date_timedown_StockComparator);
            }else if(level1_stocknode.text() != null && level1_stocknode.text().contains("退市板")){
                level1_stocknode.children.sort(delist_date_timedown_StockComparator);

            }else {
                level1_stocknode.children.sort(getMatchComparator_With_Type(orderIndex));
            }
        }


//        init_level1Name_level2StockImpl_Map_SortType("IPO新板",list_date_timedown_StockComparator);
//        init_level1Name_level2StockImpl_Map_SortType("退市板",list_date_timedown_StockComparator);


        String rootStockNode_ByMarket_Json =    Root_StockNodeImpl.json();

        writeContentToFile(mStock_RootNode_ByMarket_JsonFile,rootStockNode_ByMarket_Json);

        showStockLogPullCommand(mStock_RootNode_ByMarket_JsonFile,"Root_ByMarket_StockNode.json 以市场类型区分的 根结点的json值");

        show_level1Name_level2StockImpl_Map();


        //  Area_fragment_Operation_Begin
        print("initAreaFragmentData  showMap Begin ");
// 放在 请初始化 后边
        initAreaFragmentData();
        print("initAreaFragmentData  showMap End ");
        // Area_fragment_Operation_End

        GuPiaoLieBiao_NodeJson_Str = rootStockNode_ByMarket_Json;
        return rootStockNode_ByMarket_Json;

    }


    // 返回给 Stock_Common_Fragment 的 股票的 列表
    public synchronized static String calGuPiaoLieBiaoJson() {
        if(!isGuPiaoLieBiao_FInish_MapInit_Flag){   // 还没完成 股票 列表的填装
            return "";
        }

        if (GuPiaoLieBiao_NodeJson_Str != null ) {
            return GuPiaoLieBiao_NodeJson_Str;
        }



        if (mStock_Log_File == null ||  mStock_file_list == null ||  mStock_file_list.length == 0) {
            android.util.Log.i("stock_json_result", "没有读取到  /sdcard/zmain/stock/目录下的文件 或者该目录下文件为0  没有 J0_股票列表.xlsx !");
            return Root_StockNodeImpl.json();
        }






        if (level_1_StockBlock_nodeList == null) {
            level_1_StockBlock_nodeList = new ArrayList<Stock_NodeImpl>();
        }
        if (level_2_ShortStock_nodeList == null) {
            level_2_ShortStock_nodeList = new ArrayList<Stock_NodeImpl>();
        }

        if (level_3_DetaiLStock_nodeList == null) {
            level_3_DetaiLStock_nodeList = new ArrayList<Stock_NodeImpl>();
        }

        level_1_StockBlock_nodeList = new ArrayList<Stock_NodeImpl>();
        level_2_ShortStock_nodeList = new ArrayList<Stock_NodeImpl>();
        level_3_DetaiLStock_nodeList = new ArrayList<Stock_NodeImpl>();


        ArrayList<Stock_NodeImpl> StockBlock_node_list = new ArrayList<Stock_NodeImpl>();



        for (int i = 0; i < StockBlock.length; i++) {
            String stockBlock = StockBlock[i];

            // 初始化 榜单的 各个 Map
            if(level1Name_level2StockImpl_Map.get(stockBlock) == null){
                ArrayList<Stock_NodeImpl> bangdan_level2_nodeList = new   ArrayList<Stock_NodeImpl>(10000);
                level1Name_level2StockImpl_Map.put(stockBlock,bangdan_level2_nodeList);
                print("level1Name_level2StockImpl_Map == null  for key["+stockBlock+"] initArr ");
            }else{
                print("level1Name_level2StockImpl_Map != null  for key["+stockBlock+"]");
            }
            System.out.println("day_lastjson_Year = "+ day_lastjson_Year +"   day_lastjson_Month="+day_lastjson_Month+"   day_lastjson_Day="+day_lastjson_Day);
            if(day_lastjson_Year != null &&  day_lastjson_Month != null && day_lastjson_Day != null ){
                if(stockBlock.contains("Day_Holder")){
                    stockBlock = stockBlock.replace("Day_Holder",day_lastjson_Day+"日");
                }else if(stockBlock.contains("Month_Holder")){
                    stockBlock = stockBlock.replace("Month_Holder",day_lastjson_Month+"月");
                }else if(stockBlock.contains("Year_Holder")){
                    stockBlock = stockBlock.replace("Year_Holder",day_lastjson_Year+"年");
                }

            }



            // Stock_NodeImpl(long id, long xparentid ,String mts_code  ,int mlevel )
            Stock_NodeImpl mLevel1_StockNodeImpl = new Stock_NodeImpl(getNextStockMarketNodeID(), 0, stockBlock, 1);
            StockBlock_node_list.add(mLevel1_StockNodeImpl);
            level_1_StockBlock_nodeList.add(mLevel1_StockNodeImpl);

            if("收藏板".equals(stockBlock)){
                AttentionRank_Level1_Stock_NodeImpl = mLevel1_StockNodeImpl;
            }



        }
        Root_StockNodeImpl.children.addAll(StockBlock_node_list);
        //  把 当前 目录下的 mp3  依据  作者分类 分好


        for (int i = 0; i < StockBlock_node_list.size(); i++) {
            Stock_NodeImpl  level1_stocknode = StockBlock_node_list.get(i);
            if(level1_stocknode == null){
                continue;
            }

            for (int j = 0; j < gupiaoliebiao_StockList.size()  ; j++) {
                Stock_NodeImpl level2_stocknode = gupiaoliebiao_StockList.get(j);
//                level2_Init_level3Node_Operation(level2_stocknode);   // 动态创建自己的child 这个就是一些 详细的信息
                //  node1  和  node2 形成 关系的  函数
                releasionship_level1_level2_stocknode_ByMarket(level1_stocknode,level2_stocknode);
                level_2_ShortStock_nodeList.add(level2_stocknode);
            }
            level1_stocknode.setCount(level1_stocknode.children.size());
            System.out.println("zzpre1__ level1_stocknode.text = "+ level1_stocknode.text());
            System.out.println("zzpre1__ level1_stocknode.children.size() = "+ level1_stocknode.children.size());
            if(level1_stocknode.text() != null && level1_stocknode.text().contains("IPO新板")){

                level1_stocknode.children.sort(list_date_timedown_StockComparator);
            }else if(level1_stocknode.text() != null && level1_stocknode.text().contains("退市板")){
                level1_stocknode.children.sort(delist_date_timedown_StockComparator);

            }else {
                level1_stocknode.children.sort(pricedown_StockComparator);
            }
        }


//        init_level1Name_level2StockImpl_Map_SortType("IPO新板",list_date_timedown_StockComparator);
//        init_level1Name_level2StockImpl_Map_SortType("退市板",list_date_timedown_StockComparator);


        String rootStockNode_ByMarket_Json =    Root_StockNodeImpl.json();

        writeContentToFile(mStock_RootNode_ByMarket_JsonFile,rootStockNode_ByMarket_Json);

        showStockLogPullCommand(mStock_RootNode_ByMarket_JsonFile,"Root_ByMarket_StockNode.json 以市场类型区分的 根结点的json值");

        show_level1Name_level2StockImpl_Map();


        //  Area_fragment_Operation_Begin
        print("initAreaFragmentData  showMap Begin ");

        initAreaFragmentData();
        print("initAreaFragmentData  showMap End ");
        // Area_fragment_Operation_End

        GuPiaoLieBiao_NodeJson_Str = rootStockNode_ByMarket_Json;
        return rootStockNode_ByMarket_Json;

    }


    public static void initAreaFragmentData(){
        print("initAreaFragmentData AAAA  ");

        // 初始化  level1_node
        for (int i = 0; i < StockArea_Arr_Level_1.length; i++) {
            String stockArea = StockArea_Arr_Level_1[i];
//            if("All".equals(stockArea)){
//                continue;
//            }

            Stock_NodeImpl    mLevel1_AreaPage_StockNodeImpl = new Stock_NodeImpl(getNextStockAreaNodeID(), 0, stockArea, 1);

            // 初始化 level1_对应的key的 value 的 列表 List
            if(level1AreaName_level2AreaIndustryStockNode_Map.get(stockArea) == null){
                ArrayList<Stock_NodeImpl> mapValueList = new ArrayList<Stock_NodeImpl>();
                level1AreaName_level2AreaIndustryStockNode_Map.put(mLevel1_AreaPage_StockNodeImpl,mapValueList);

            }

            level_1_AreaBlock_nodeList.add(mLevel1_AreaPage_StockNodeImpl);
            //   添加 第一个  area_level1_item
            AreaPage_RootStockNode_Impl.areaChildrenAdd(mLevel1_AreaPage_StockNodeImpl,1);
            mLevel1_AreaPage_StockNodeImpl.level = 1;
        }


        //  初始化  level2_node
        gupiaoliebiao_StockList.sort(new Comparator<Stock_NodeImpl>() {
            @Override
            public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
                if(o1.price > o2.price){
                    return -1;
                } else if(o1.price < o2.price){
                    return 1;
                }

                return 0 ;
            }
        });
        for (int j = 0; j < gupiaoliebiao_StockList.size(); j++) {

            Stock_NodeImpl stockItem = gupiaoliebiao_StockList.get(j);  //  level3 的 node集合
            String areaItem = stockItem.area;
            areaItem = fixedAreaWithProvidse(areaItem);
            String industryItem = stockItem.industry;



            if(areaItem != null &&  industryItem != null
                    && !"".equals(industryItem)    && !"".equals(areaItem)){

                if(!allIndustryNameList.contains(industryItem)){
                    allIndustryNameList.add(industryItem);//    所有 industry 的 列表
                }

                if(mIndustry_StockImplList_Map.get(industryItem) == null){
                    ArrayList<Stock_NodeImpl> matchIndustryList = new  ArrayList<Stock_NodeImpl>();
                    mIndustry_StockImplList_Map.put(industryItem,matchIndustryList);

                }
                mIndustry_StockImplList_Map.get(industryItem).add(stockItem);
                // industry  以及 匹配 这个 level2_industry的 level3_stockimpl


                Stock_NodeImpl    mArea_Level1_StockNodeImpl =   getLevel1AreaStockNodeFromAreaKey(areaItem);

                //  如果当前的industry  当前 没有 包含的话  俺么 就创建 这个 level2 的 StockItem
                boolean isContainLevel2IndustryInLevelArea = isAreaContainIndustry(mArea_Level1_StockNodeImpl.children,industryItem);

                if(mArea_Level1_StockNodeImpl != null && !isContainLevel2IndustryInLevelArea){

                    //  初始化  level1 和  level2 的  关系了
                    Stock_NodeImpl level2_industry_item =  new Stock_NodeImpl(getNextStockAreaNodeID(), mArea_Level1_StockNodeImpl.id, industryItem, 2);
//                    ArrayList<Stock_NodeImpl> mArea_AreaIndustry_Arr =  level1AreaName_level2AreaIndustryStockNode_Map.get(Area_Level1_StockNodeImpl);

                    mArea_Level1_StockNodeImpl.areaChildrenAdd(level2_industry_item,2);
                }

                //    需要在这里 初始化 level2   和  level 3 的  关系了
                //     从 level1_List 中找到 对应的  level2 的 列表
                Stock_NodeImpl  matchLevel2_StockNode  =   getLevel2_Area_IndustryStockNodeFromIndustryKey(mArea_Level1_StockNodeImpl.children,industryItem);
                if(matchLevel2_StockNode == null){
//    print("getLevel2_Area_IndustryStockNodeFromIndustryKey matchLevel2_StockNode == null ->  isContainLevel2IndustryInLevelArea ["+isContainLevel2IndustryInLevelArea+"] mArea_Level1_StockNodeImpl.name="+mArea_Level1_StockNodeImpl.name+"  industryItem="+industryItem);
                    continue;
                }else{
//    print("getLevel2_Area_IndustryStockNodeFromIndustryKey ZZA matchLevel2_StockNode != null  && isContainLevel2IndustryInLevelArea["+isContainLevel2IndustryInLevelArea+"]  && "+matchLevel2_StockNode.toString());
                }

                matchLevel2_StockNode.areaChildrenAdd(stockItem.newAreaCopy(),3);

            }else{
                System.out.println("查询不到匹配的 Level1 对应的 nodeItem ");
                continue;
            }
        }






//            ArrayList<Stock_NodeImpl>  sameAreaStockArr =  level1Area_level2Industry_Map.get(stockArea);

/*        int stockCount = gupiaoliebiao_StockList.size();
            for (int j = 0; j < gupiaoliebiao_StockList.size(); j++) {
                Stock_NodeImpl stockItem = gupiaoliebiao_StockList.get(j);  //  level3 的 node集合
                String areaItem = stockItem.area;
                areaItem = fixedAreaWithProvidse(areaItem);
                String industryItem = stockItem.industry;

                if(areaItem != null &&  industryItem != null
                        && !"".equals(industryItem)    && !"".equals(areaItem)){

                    String mAreaMapKey = areaItem+industryItem;



                    if(mIndustry_StockImplList_Map.get(industryItem) == null){
                        ArrayList<Stock_NodeImpl> matchIndustryList = new  ArrayList<Stock_NodeImpl>();
                        mIndustry_StockImplList_Map.put(industryItem,matchIndustryList);

                    }
                    mIndustry_StockImplList_Map.get(industryItem).add(stockItem);


//                    print("j["+j+"]all["+stockCount+"]"+"stockArea["+areaItem+"] andX "+"mAreaMapKey["+mAreaMapKey+"]");

                    //     北京__北京汽车___

            ArrayList<Stock_NodeImpl> mArea_AreaIndustry_Arr =  level1AreaName_level2AreaIndustryStockNode_Map.get(getLevel1AreaStockNodeFromAreaKey(areaItem));
            if(mArea_AreaIndustry_Arr != null && !isAreaStockListContainAreaKey(mArea_AreaIndustry_Arr,mAreaMapKey)){
//                mArea_AreaIndustry_Arr.add(mAreaMapKey);
                mArea_AreaIndustry_Arr.add(stockItem);
            }



                    // 初始化 榜单的 各个 Map
                    if(level2AreaIndustry_level3StockImplList_Map.get(mAreaMapKey) == null){
                        ArrayList<Stock_NodeImpl> bangdan_level2_nodeList = new   ArrayList<Stock_NodeImpl>(10000);
                        level2AreaIndustry_level3StockImplList_Map.put(mAreaMapKey,bangdan_level2_nodeList);
//                        print("level1Name_level2StockImpl_Map == null  for stockArea["+mAreaMapKey+"] initArr ");
                    }

                    //  北京汽车_对应stock 集合
                    ArrayList<Stock_NodeImpl>  sameAreaStockArr =  level2AreaIndustry_level3StockImplList_Map.get(mAreaMapKey);
                    sameAreaStockArr.add(stockItem);

                }else{
                    print("j["+j+"]all["+stockCount+"]"+ "areaItem["+areaItem+"]  industryItem["+industryItem+"] 其中之一为空! ");
                }

            }*/

        // tainjia All的 第一 level

//        level1AreaName_level2AreaIndustryStockNode_Map.put("All",allIndustryNameList);
        Stock_NodeImpl level1_area_all_stock = getLevel1AreaStockNodeFromAreaKey("All");
        for (int i = 0; i < allIndustryNameList.size() ; i++) {
            String industryName =  allIndustryNameList.get(i);
            Stock_NodeImpl level2_all_areastock_item = new Stock_NodeImpl(getNextStockAreaNodeID(),level1_area_all_stock.id,industryName,2);
            level1_area_all_stock.children.add(level2_all_areastock_item);
            ArrayList<Stock_NodeImpl> matchIndustryList =  mIndustry_StockImplList_Map.get(industryName);

            if(matchIndustryList != null){

                for (int j = 0; j <matchIndustryList.size(); j++) {
                    Stock_NodeImpl matchStock =      matchIndustryList.get(j);
                    Stock_NodeImpl newStockItem =   matchStock.newAreaCopy();
                    level2_all_areastock_item.areaChildrenAdd(newStockItem,3);
//                  newStockItem.name = newStockItem.area+"_"+newStockItem.name;
                }


            }
            //  这里 最好 有 industry__ArrayList<StockImpl> 这样的 Map
            //  方便 组成 All_industry_srock 这样的关系
        }


//

//   产生  北京_北京All_的列表   即 注册地是北京市的股票的列表
        for (int i = 0; i < StockArea_Arr_Level_1.length ; i++) {
            String areaName = StockArea_Arr_Level_1[i];
            if("All".equals(areaName) || "台湾".equals(areaName)){
                continue;
            }
            Stock_NodeImpl  allLevel2Item_InLevel1Area_Item = new Stock_NodeImpl();
            allLevel2Item_InLevel1Area_Item.level = 2;
            allLevel2Item_InLevel1Area_Item.name = areaName+"_All";
            allLevel2Item_InLevel1Area_Item.area = areaName;
            allLevel2Item_InLevel1Area_Item.id = getNextStockAreaNodeID();

            for (int j = 0; j < level_1_AreaBlock_nodeList.size(); j++) {
                Stock_NodeImpl  level1_nodeItem = level_1_AreaBlock_nodeList.get(j);
                if(level1_nodeItem.name.equals(areaName)){   //
                    allLevel2Item_InLevel1Area_Item.parentid = level1_nodeItem.id;
                    // 在 一个省份 下的 所有的 股票 , 该区域内 的 所有的上市股票

                    for (int k = 0; k < level1_nodeItem.children.size(); k++) {
                        Stock_NodeImpl  level2_nodeItem = level1_nodeItem.children.get(k);
                        for (int l = 0; l < level2_nodeItem.children.size(); l++) {
                            Stock_NodeImpl  level3_nodeItem = level2_nodeItem.children.get(l);
                            allLevel2Item_InLevel1Area_Item.areaChildrenAdd(level3_nodeItem.newAreaCopy(),3);

                        }

                    }
                    allLevel2Item_InLevel1Area_Item.children.sort(new Comparator<Stock_NodeImpl>() {
                        @Override
                        public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
                            if(o1.price > o2.price){
                                return -1;
                            } else if(o1.price < o2.price){
                                return 1;
                            }

                            return 0 ;
                        }
                    });
                    level1_nodeItem.areaChildrenAdd(allLevel2Item_InLevel1Area_Item,2);

                }
            }

        }


        for (int i = 0; i < level_1_AreaBlock_nodeList.size(); i++) {
            Stock_NodeImpl  nodeItem = level_1_AreaBlock_nodeList.get(i);
            nodeItem.children.sort(new Comparator<Stock_NodeImpl>() {
                @Override
                public int compare(Stock_NodeImpl o1, Stock_NodeImpl o2) {
                    if(o1.children.size() == o2.children.size()){
                        return 0;
                    }

                    if(o1.children.size() > o2.children.size()){
                        return -1;
                    }

                    if(o1.children.size() < o2.children.size()){
                        return 1;
                    }

                    return 0;
                }
            });
        }



        AreaPage_RootStockNode_Json_Str =  AreaPage_RootStockNode_Impl.area_json();

        print("【抓取 J0_Area_Industry_Stock.json  】  " + "adb pull "+J0_AreaPage_JSON_File.getAbsolutePath()+" .");

        writeContentToFile(J0_AreaPage_JSON_File,AreaPage_RootStockNode_Json_Str);



    }

    static String fixedAreaWithProvidse(String mArea){
        String curArea = mArea;
        if(curArea == null ){
            return null;
        }
        if("深圳".equals(curArea)){
            curArea =  "广东";
        }

        return curArea;

    }


    public static Stock_NodeImpl  getLevel1AreaStockNodeFromAreaKey(String areaKey){
        Stock_NodeImpl  matchNodeImpl = null;

        for (int i = 0; i < level_1_AreaBlock_nodeList.size(); i++) {
            Stock_NodeImpl  nodeItem = level_1_AreaBlock_nodeList.get(i);
            if(nodeItem.name.equals(areaKey)){
                return nodeItem;
            }
        }
        return matchNodeImpl;



    }

    public static   boolean isAreaStockListContainAreaKey(ArrayList<Stock_NodeImpl> stockNodeList , String areaKey){
        boolean isContain = false;

        for (int i = 0; i < stockNodeList.size(); i++) {
            Stock_NodeImpl stockNodeImpl = stockNodeList.get(i);
            if(stockNodeImpl.name.equals(areaKey)){

                return true;
            }
        }
        return isContain;

    }
    public static String calculLittleDigital(String digital){
        // ₀₁₂₃₄₅₆₇₈₉  // ⁰¹²³⁴⁵⁶⁷⁸⁹  // ⁰¹²³⁴⁵⁶⁷⁸⁹    // ⁴⁵⁶⁷⁸⁹
        String result = digital+""; // ⁰¹²³⁴⁵⁶⁷⁸⁹   // ₀₁₂₃₄₅₆₇₈₉  // ₀₁₂₃₄₅₆₇₈₉  // ⁰¹²³⁴⁵⁶⁷⁸⁹

/*       // ⁰¹²³⁴⁵⁶⁷⁸⁹
        result =    result.replace("0","⁰");
        result =    result.replace("1","¹");
        result =    result.replace("2","²");
        result =    result.replace("3","³");
        result =    result.replace("4","⁴");
        result =    result.replace("5","⁵");
        result =    result.replace("6","⁶");
        result =    result.replace("7","⁷");
        result =    result.replace("8","⁸");
        result =    result.replace("9","⁹");  */

        //⁰¹²³⁴⁵⁶⁷⁸⁹   :º ¹ ² ³ ⁴⁵ ⁶ ⁷ ⁸ ⁹   // ⁰¹²³⁴⁵⁶⁷⁸⁹
        result =    result.replace("0","⁰");
        result =    result.replace("1","¹");
        result =    result.replace("2","²");
        result =    result.replace("3","³");
        result =    result.replace("4","⁴");
        result =    result.replace("5","⁵");
        result =    result.replace("6","⁶");
        result =    result.replace("7","⁷");
        result =    result.replace("8","⁸");
        result =    result.replace("9","⁹");

/*        // ₀₁₂₃₄₅₆₇₈₉
        result =    result.replace("0","₀");
        result =    result.replace("1","₁");
        result =    result.replace("2","₂");
        result =    result.replace("3","₃");
        result =    result.replace("4","₄");
        result =    result.replace("5","₅");
        result =    result.replace("6","₆");
        result =    result.replace("7","₇");
        result =    result.replace("8","₈");
        result =    result.replace("9","₉");*/


        return result;

    }

    public static String calculLittleDigital(int digital ){
        // ₀₁₂₃₄₅₆₇₈₉  // ⁰¹²³⁴⁵⁶⁷⁸⁹  // ⁰¹²³⁴⁵⁶⁷⁸⁹    // ⁴⁵⁶⁷⁸⁹
        String result = digital+""; // ⁰¹²³⁴⁵⁶⁷⁸⁹   // ₀₁₂₃₄₅₆₇₈₉  // ₀₁₂₃₄₅₆₇₈₉  // ⁰¹²³⁴⁵⁶⁷⁸⁹

/*       // ⁰¹²³⁴⁵⁶⁷⁸⁹
        result =    result.replace("0","⁰");
        result =    result.replace("1","¹");
        result =    result.replace("2","²");
        result =    result.replace("3","³");
        result =    result.replace("4","⁴");
        result =    result.replace("5","⁵");
        result =    result.replace("6","⁶");
        result =    result.replace("7","⁷");
        result =    result.replace("8","⁸");
        result =    result.replace("9","⁹");  */

/*        //⁰¹²³⁴⁵⁶⁷⁸⁹   :º ¹ ² ³ ⁴⁵ ⁶ ⁷ ⁸ ⁹   // ⁰¹²³⁴⁵⁶⁷⁸⁹
        result =    result.replace("0","⁰");
        result =    result.replace("1","¹");
        result =    result.replace("2","²");
        result =    result.replace("3","³");
        result =    result.replace("4","⁴");
        result =    result.replace("5","⁵");
        result =    result.replace("6","⁶");
        result =    result.replace("7","⁷");
        result =    result.replace("8","⁸");
        result =    result.replace("9","⁹");*/

        // ₀₁₂₃₄₅₆₇₈₉
        result =    result.replace("0","₀");
        result =    result.replace("1","₁");
        result =    result.replace("2","₂");
        result =    result.replace("3","₃");
        result =    result.replace("4","₄");
        result =    result.replace("5","₅");
        result =    result.replace("6","₆");
        result =    result.replace("7","₇");
        result =    result.replace("8","₈");
        result =    result.replace("9","₉");

/*        if(DataHolder.System_IS_PAD){   // Pad 环境下  输出 ₀₁  这样方便对齐
            if(result.length() == 1){
                result = "₀"+result;
            }

        }*/


        return result;

    }




// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
/*    day_timestamp.xlsx【新股IPO列表】   包含最近 新上市股票
   cname	ts_code	sub_code	name	ipo_date	issue_date	amount	market_amount	price	pe	limit_amount	funds	ballot
    null	605086.SH	707086	龙高股份	20210407		3200	1280	12.86	21.82	1.2	4.115	0
    null	605378.SH	707378	野马电池	20210330		3334	1334	0	0	1.3	5.875	0
    null	688683.SH	787683	莱尔科技	20210330		3714	947	0	0	0.9	6.375	0*/

    /*    day_timestamp.xlsx【日线行情-时间为序】 包含股票的当前的最新的  日价格  day_close  day_close_data
    cname	ts_code	trade_date	open	high	low	close	pre_close	change	pct_chg	vol	amount
    平安银行	000001.SZ	20210323	21.57	21.65	20.96	21.23	21.55	-0.32	-1.4849	682903.38	1448043.613
    万科A	000002.SZ	20210323	31.61	31.66	31.14	31.42	31.63	-0.21	-0.6639	422660.07	1324354.573
    国华网安	000004.SZ	20210323	17.72	17.94	17.51	17.59	17.72	-0.13	-0.7336	15685	27769.191 */

    /*    day_timestamp.xlsx【月线行情-每个交易月的最后一日】 包含股票的当前的最新的  周末价格  week_close  week_close_data
cname	ts_code	trade_date	close	open	high	low	pre_close	change	pct_chg	vol	amount
平安银行	000001.SZ	20210319	20.47	21.4	22.12	20.37	21.48	-1.01	-0.047	493715413	10497624004
万科A	000002.SZ	20210319	31.2	31.3	33.43	30.85	31.49	-0.29	-0.0092	401576675	12907516958
国华网安	000004.SZ	20210319	17.63	17.17	17.87	16.8	17.19	0.44	0.0256	7691858	133964059.8
 */

    /*    day_timestamp.xlsx【月线行情-时间为序】 包含股票的当前的最新的  月末价格  month_close  month_close_date
    cname	ts_code	trade_date	close	open	high	low	pre_close	change	pct_chg	vol	amount
    平安银行	000001.SZ	20210531	24.2	23.1	25.16	22.6	23.29	0.91	0.0391	1015281590	24221421506
    万科A	000002.SZ	20210531	26.7	27.87	28.35	26.11	28.17	-1.47	-0.0522	1039111900	28092226443
    国华网安	000004.SZ	20210531	16.04	15.94	16.3	14.81	15.93	0.11	0.0069	33141775	515522540.8
 */

// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
    /*   day_timestamp.xlsx【每日停复牌信息】  Map<String,String>  // 日期--S   日期--R
cname	ts_code	trade_date	suspend_timing	suspend_type
*ST德奥	002260.SZ	20210609		S
*ST德奥	002260.SZ	20210610		S
*ST德奥	002260.SZ	20210611		S
*ST德奥	002260.SZ	20210615		S
*ST德奥	002260.SZ	20210616		S
*ST德奥	002260.SZ	20210617		S
*ST高升	000971.SZ	20210617		S
*ST高升	000971.SZ	20210618		R
*ST航通(退)	600677.SH	20210609		S
*ST航通(退)	600677.SH	20210610		S
*ST航通(退)	600677.SH	20210611		S
*ST航通(退)	600677.SH	20210615		S
*ST航通(退)	600677.SH	20210616		S
*ST航通(退)	600677.SH	20210617		S

 */

/*   day_timestamp.xlsx【每日指标】   level_3_node 的 信息  包含当前价格
    cname	ts_code	trade_date	close	turnover_rate	turnover_rate_f	volume_ratio	pe	pe_ttm	pb	ps	ps_ttm	dv_ratio	dv_ttm	total_share	float_share	free_share	total_mv	circ_mv
    三变科技	002112.SZ	20210616	7.04	0.7624	1.2224	0.8	44.7384	44.2748	3.4877	1.4047	1.3265			20160	19880.2893	12399.0672	141926.4	139957.2367
    国中水务	600187.SH	20210616	2.33	0.5771	0.777	1.07	126.1652	1287.7943	1.1287	10.1653	9.408			165393.5128	165393.5128	122831.1728	385366.8848	385366.8848
*/

/*
    // 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
 day_timestamp.xlsx【个股资金流向】
    cname	ts_code	trade_date	buy_sm_vol	buy_sm_amount	sell_sm_vol	sell_sm_amount	buy_md_vol	buy_md_amount	sell_md_vol	sell_md_amount	buy_lg_vol	buy_lg_amount	sell_lg_vol	sell_lg_amount	buy_elg_vol	buy_elg_amount	sell_elg_vol	sell_elg_amount	net_mf_vol	net_mf_amount
    健盛集团	603558.SH	20210616	5477	445.82	4793	390.02	2798	227.93	2992	243.78	282	22.93	772	62.88	0	0	0	0	1802	147.46
    七一二	603712.SH	20210616	17877	6601.33	17085	6319.57	9905	3655.94	8639	3190	3045	1119.92	4974	1824.91	374	140.32	503	183.04	1384	513.96
    芯朋微	688508.SH	20210616	11131	9996.38	10572	9516.95	14272	12892.34	14488	13086.72	8633	7815.57	7996	7215.15	1224	1099.44	2205	1984.9	-945	-824.53
    好利来	002729.SZ	20210616	841	407.62	1156	560.46	1284	623	951	461.68	166	80.31	184	88.79	0	0	0	0	-112	-52.38
*/


/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板

 day_timestamp.xlsx【个股资金流向】
    cname	trade_date	ts_code	pre_close	up_limit	down_limit
    平安银行	20210616	000001.SZ	23.22	25.54	20.9
    万科A	20210616	000002.SZ	24.76	27.24	22.28
    国华网安	20210616	000004.SZ	21.58	23.74	19.42
*/


/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板


 day_timestamp.xlsx【月涨跌停统计】
   cname	trade_date	ts_code	name	close	pct_chg	amp	fc_ratio	fl_ratio	fd_amount	first_time	last_time	open_times	strth	limit
    海量数据	20210616	603138.SH	海量数据	14.04	10.03	9.64	23.1721	0.5206	18468216			0	0	U
    金溢科技	20210616	002869.SZ	金溢科技	20.23	10.01	7.01	73.5292	1.8813	56773472			0	0	U

    */



/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
 day_timestamp.xlsx【沪深港通资金流向】  和 具体的股票无关  和市场有关  需要新的代码
trade_date	ggt_ss	ggt_sz	hgt	sgt	north_money	south_money
20210616	-1417.7	-1218.43	-199.19	-205.98	-405.17	-2636.13
20210615	-952.19	553.77	-5608.09	454.4	-5153.69	-398.42
20210611	684.25	1214.73	-3015.14	-16.93	-3032.07	1898.98*/



/*

// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
  day_timestamp.xlsx【沪深股通十大成交股】    Map<data_str,obj> gushengutongshidachnegjiaohuMap;
   cname	trade_date	ts_code	name	close	change	rank	market_type	amount	net_amount	buy	sell
    美的集团	20210616	000333.SZ	美的集团	75	2.4031	10	3	772461115	-214674325	278893395	493567720
    泸州老窖	20210616	000568.SZ	泸州老窖	242.58	-2.9136	9	3	793724634	-83692086	355016274	438708360
    五粮液	20210616	000858.SZ	五粮液	292.32	-2.3191	7	3	992665546	-208657662	392003942	600661604
    歌尔股份	20210616	002241.SZ	歌尔股份	38	-4.4025	4	3	1090358439	-414418921	337969759	752388680*/


/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
// 沪深港持股明细榜
// day_timestamp.xlsx【沪深港股通持股明细】
    cname	code	trade_date	ts_code	name	vol	ratio	exchange
    睿创微纳	30002	20210616	688002.SH	睿創微納	10147986	4.43	SH
    杭可科技	30006	20210616	688006.SH	杭可科技	6009408	5.43	SH
    澜起科技	30008	20210616	688008.SH	瀾起科技	20358076	4.83	SH*/

/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
// 沪深港持股明细榜
// 融资融券交易明细榜
// day_timestamp.xlsx【融资融券交易明细】
    cname	trade_date	ts_code	name	rzye	rqye	rzmre	rqyl	rzche	rqchl	rqmcl	rzrqye
    平安银行	20210616	000001.SZ	平安银行	2665741511	2088864836	53069082	89805023	67966317	107000	2285160	4754606347
    万科A	20210616	000002.SZ	万  科Ａ	7025679139	1815950148	158292858	72754413	206419018	1242100	2727700	8841629287
    深振业A	20210616	000006.SZ	深振业Ａ	488743016	332690	1964222	64600	2066692	9100	0	489075706
*/


    /*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
// 沪深港持股明细榜
// 融资融券交易明细榜
// 龙虎榜
// day_timestamp.xlsx【龙虎榜明细】   【修改代码  把 一年的 信息 都抓取 到  】
GQY视讯	20210616	300076.SZ	GQY视讯	7.26	20	32.22	854712658	69807704	78669097	148476801	8861393	1.04	17.37		日涨幅达到15%的前5只证券
GQY视讯	20210616	300076.SZ	GQY视讯	7.26	20	32.22	1302018141	110774036.2	111120019.9	221894056.1	345983.76	0.03	17.04		连续三个交易日内，涨幅偏离值累计达到30%的证券
大富科技	20210616	300134.SZ	大富科技	13	11.3014	10.32	1311694628	72655669.88	94044676.86	166700346.7	21389006.98	1.63	12.71		连续三个交易日内，涨幅偏离值累计达到30%的证券

// day_timestamp.xlsx【龙虎榜结构】 对上面的补充
cname	trade_date	ts_code	exalter	buy	buy_rate	sell	sell_rate
湖北宜化	20210616	000422.SZ	机构专用	35835250.39	2.12	33149008.16	1.96
湖北宜化	20210616	000422.SZ	申港证券股份有限公司深圳分公司	35239756.28	2.09	37157716.63	2.2
湖北宜化	20210616	000422.SZ	中国中金财富证券有限公司南京太平南路证券营业部	33003519	1.96	21584	0
湖北宜化	20210616	000422.SZ	兴业证券股份有限公司武汉新华路证券营业部	32330784.1	1.92	647659	0.04
湖北宜化	20210616	000422.SZ	机构专用	30044158	1.78	0	0
湖北宜化	20210616	000422.SZ	申港证券股份有限公司深圳分公司	35239756.28	2.09	37157716.63	2.2
湖北宜化	20210616	000422.SZ	机构专用	35835250.39	2.12	33149008.16	1.96
湖北宜化	20210616	000422.SZ	中银国际证券股份有限公司武汉黄孝河路证券营业部	604581	0.04	32006919.84	1.9


*/

/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
// 沪深港持股明细榜
// 融资融券交易明细榜
// 龙虎榜
//  股票回购信息榜
// 限售股解禁榜
// day_timestamp.xlsx【限售股解禁榜】
    cname	ts_code	ann_date	end_date	proc	exp_date	vol	amount	high_limit	low_limit
    居然之家	000785.SZ	20210617	20210616	实施		16332294	118851812.7	7.88	6.25
    荣科科技	300290.SZ	20210617		预案			1262265	1.957	1.957
    奥普家居	603551.SH	20210617	20210616	实施		146600	1821519.7	12.53	12.33
    汇顶科技	603160.SH	20210617		预案			500000000	169.98*/


/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
// 沪深港持股明细榜
// 融资融券交易明细榜
// 龙虎榜
// 限售股解禁榜
//  股票回购信息榜
// day_timestamp.xlsx【股票回购】
cname	ts_code	ann_date	end_date	proc	exp_date	vol	amount	high_limit	low_limit
居然之家	000785.SZ	20210617	20210616	实施		16332294	118851812.7	7.88	6.25
荣科科技	300290.SZ	20210617		预案			1262265	1.957	1.957
奥普家居	603551.SH	20210617	20210616	实施		146600	1821519.7	12.53	12.33
汇顶科技	603160.SH	20210617		预案			500000000	169.98
庄园牧场	002910.SZ	20210617	20210617	完成		1299568	9310209.19	6.84	6.84
美思德	603041.SH	20210617		预案			117264	6.98	6.98
阳光照明	600261.SH	20210617	20210616	实施		5193800	20034483.03	3.9	3.8

*/

/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
// 沪深港持股明细榜
// 融资融券交易明细榜
// 龙虎榜
// 限售股解禁榜
//  股票回购信息榜
//   大宗交易信息榜
// day_timestamp.xlsx【大宗交易】
cname	ts_code	trade_date	price	vol	amount	buyer	seller
TCL科技	000100.SZ	20210616	7.28	70	509.6	国泰君安证券股份有限公司上海黄陂南路证券营业部	中信证券股份有限公司南京浦口大道证券营业部
美的集团	000333.SZ	20210616	75	4.4	330	中信证券股份有限公司上海分公司	招商证券股份有限公司深圳南山南油大道证券营业部
北新建材	000786.SZ	20210616	47	100	4700	机构专用	海通证券股份有限公司泰安岱宗大街证券营业部
*ST高升	000971.SZ	20210616	2.64	50	132	平安证券股份有限公司浙江分公司	海通证券股份有限公司北京密云鼓楼东大街证券营业部
*ST高升	000971.SZ	20210616	2.64	50	132	海通证券股份有限公司乌鲁木齐友好北路证券营业部	海通证券股份有限公司北京密云鼓楼东大街证券营业部
*/

/*
// 沪市A
// 创业板
// 科创板
// 深市A
// IPO新板
// 停复牌板
// 个股资金流向榜
// x日涨跌板
// x月涨跌停板
// x年沪深港资金流向榜
// x日沪深股通十大成交股
// 沪深港持股明细榜
// 融资融券交易明细榜
// 龙虎榜
// 限售股解禁榜
//  股票回购信息榜
//   大宗交易信息榜
//   近三月股东增减持信息榜
// day_timestamp.xlsx【股东增减持】
    cname	ts_code	ann_date	holder_name	holder_type	in_de	change_vol	change_ratio	after_share	after_ratio	avg_price	total_share	begin_date	close_date
    天源迪科	300047.SZ	20210616	陈秀琴	G	DE	108197	0.0206			7.773	324591	20210611	20210611
    天源迪科	300047.SZ	20210616	罗赞	G	DE	17000	0.0032			7.65	54074	20210615	20210615
    天源迪科	300047.SZ	20210616	管四新	G	DE	19000	0.0036			7.59	57200	20210615	20210615
*/


    public static  File reverXlsxToJson(File xlsxFile , File jsonResultDirFile) {
        File allSheetJsonFile = null ;
        try {

            FileInputStream inp = new FileInputStream(xlsxFile.getAbsolutePath());

            Workbook workbook = null;
//		            Workbook workbook = WorkbookFactory.create(inp);

            if (xlsxFile.getName().toLowerCase().trim().endsWith(".xls"))
            {
                workbook = new HSSFWorkbook(inp);
            }
            else
            {
//			             workbook = WorkbookFactory.create(inp);
                workbook = new XSSFWorkbook(inp);
            }


            //获取sheet数
            int sheetNum = workbook.getNumberOfSheets();
//            System.out.println("Shell_count = "+ sheetNum);
            JSONObject jsonObject = new JSONObject();
            for (int s = 0; s < sheetNum; s++) {
                // Get the Sheet of s.
                Sheet sheet = workbook.getSheetAt(s);
                //获取最大行数
                String shellName =  sheet.getSheetName();

                int rownum = sheet.getPhysicalNumberOfRows();
                if (rownum <= 1) {
                    continue;
                }

                //获取第一行
                Row row1 = sheet.getRow(0);
                //获取最大列数
                int colnum = row1.getPhysicalNumberOfCells();
//                System.out.println("shellIndex["+s+"]  shellName["+ shellName+"]  rownum["+ rownum+"]   colnum["+colnum+ "]");

                JSONArray jsonArray = new JSONArray();
                for (int i = 1; i < rownum; i++) {
                    Row row = sheet.getRow(i);
//                    System.out.println("currentRow = "+ i );
//		                    if(i > 10) {
//
//		                    	continue;
//		                    }
                    if (row != null) {
//		                    List<Object> list = new ArrayList<>();
                        JSONObject rowObj = new JSONObject();
                        //循环列
                        for (int j = 0; j < colnum; j++) {
                            Cell cellData = row.getCell(j);
                            if (cellData != null) {
                                //判断cell类型
//                                System.out.println("colum="+j);

                                switch (cellData.getCellType()) {
                                    case Cell.CELL_TYPE_NUMERIC : {
                                        rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getNumericCellValue());
                                        break;
                                    }
                                    case Cell.CELL_TYPE_FORMULA: {
                                        //判断cell是否为日期格式
                                        if (DateUtil.isCellDateFormatted(cellData)) {
                                            //转换为日期格式YYYY-mm-dd
                                            rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getDateCellValue());
                                        } else {
                                            //数字
                                            rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getNumericCellValue());
                                        }
                                        break;
                                    }


                                    case Cell.CELL_TYPE_STRING: {


//                                        System.out.println("row1.getCell(j).toString() = "+ row1.getCell(j).toString());
//                                        System.out.println("row1.getCell(j).getCellStyle() = "+ row1.getCell(j).getCellStyle());
//                                        System.out.println("row1.getCell(j).getCellType() = "+ row1.getCell(j).getCellType());

                                        String cellContent = null;

                                        try {
                                            cellContent = cellData.toString();

                                        } catch(Error e) {
                                            cellContent ="";

                                        }


                                        rowObj.put(row1.getCell(j).toString(), cellContent);

                                        // 表头 是 富文本 的 时候 调用 	  getRichStringCellValue()  和 getStringCellValue() 报错!!!
                                        // Exception in thread "main" java.lang.NoSuchMethodError: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst.xgetT()
                                        // Lorg/openxmlformats/schemas/officeDocument/x2006/sharedTypes/STXstring;

                                        // rowObj.put(row1.getCell(j).getRichStringCellValue().toString(), cellData.getRichStringCellValue());
                                        // rowObj.put(row1.getCell(j).getStringCellValue().toString(), cellData.getStringCellValue());

                                        break;
                                    }
                                    default:
                                        rowObj.put(row1.getCell(j).getStringCellValue(), "");
                                }
                            } else {
                                rowObj.put(row1.getCell(j).getStringCellValue(), "");

                            }
                        }
                        jsonArray.add(rowObj);
                    }
                }
                //  把 sheet的单独作为一个  json 文件
//                String jsonName = sheet.getSheetName()+".json";
//                File jsonFile = new File(jsonResultDirFile.getAbsolutePath()+File.separator+jsonName);
//                System.out.println(jsonArray.toJSONString());
//                writeContentToFile(jsonFile, jsonArray.toJSONString());


                jsonObject.put(sheet.getSheetName(), jsonArray);
            }
//		            System.out.println(jsonObject.toJSONString());
            System.out.println("长度"+jsonObject.toJSONString().length());
            String allSheetJsonFileName =xlsxFile.getName().replace(".", "_")+".json";
            allSheetJsonFile =  new File(jsonResultDirFile.getAbsolutePath()+File.separator+allSheetJsonFileName);
            writeContentToFile(allSheetJsonFile, jsonObject.toJSONString());   // 把 xlsx 作为一个所有的jso文件

            System.out.println(" zukgit reverXlsxToJson  xlsxFile-->"+xlsxFile.getAbsolutePath()+" 解析成功!");



        } catch (Exception e) {
            e.printStackTrace();
        }
        return allSheetJsonFile;
    }

    public static String Rule14_ToPinyin_WithFirstBig(String chinese) {
        if (chinese == null || chinese.trim().isEmpty()) {
            return null;
        }
        String curItem = new String(chinese);
        while (curItem.contains(" ")) {
            curItem = curItem.replaceAll(" ", "");
        }
        String pinyinStr = "";
        char[] newChar = curItem.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += toUpperFirstChar(PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0]); // [0] 标识当前拼音 汉->
                    // han
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else { // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
                pinyinStr += (newChar[i]+"");
            }
        }
        return pinyinStr;
    }

    public static String toUpperFirstChar(String srcStr) {
        if(srcStr == null) {
            return "";
        }
        String secondStr = srcStr.substring(1).toLowerCase();
        String firstChar = (srcStr.charAt(0)+"").toUpperCase();

        return firstChar+secondStr;

    }
    public  static boolean isGuPiaoLieBiao_FInish_MapInit_Flag ;
    public   static void InitWithStockNodeShareInfoMap(){

        // 先遍历Map
        Set<String> keySet_tscode =  tscode_stockShareInfo_Map.keySet();
        ArrayList<String> map_tscode_key = new ArrayList<String>();
        if(keySet_tscode != null){
            map_tscode_key.addAll(keySet_tscode) ;
        }

        for (int i = 0; i < map_tscode_key.size(); i++) {
            String tsCode_InMap = map_tscode_key.get(i);
            StockNodeShareInfo shareObj =     tscode_stockShareInfo_Map.get(tsCode_InMap);
            if(!TScode_List.contains(tsCode_InMap)){
                TScode_List.add(tsCode_InMap);
                // 如果 Map 中 有 TScode_List 不包含的 tsCode  那么 要新创建 nodeimpl
                gupiaoliebiao_StockList.add(  new Stock_NodeImpl(getNextStockMarketNodeID(), -1 , shareObj.getXinGuLieBiaoIPO_name(),2));
            }
        }



        for (int i = 0; i < gupiaoliebiao_StockList.size(); i++) {
            Stock_NodeImpl currentNodeImpl = gupiaoliebiao_StockList.get(i);
            if(currentNodeImpl == null){
                continue;
            }
            String nodeImpl_tscode = currentNodeImpl.ts_code;
            StockNodeShareInfo shareObj =  tscode_stockShareInfo_Map.get(nodeImpl_tscode);
            if(shareObj == null){
                continue;
            }

            currentNodeImpl.price = shareObj.price;


            currentNodeImpl.MeiRiZhiBiao_turnover_rate_f = shareObj.MeiRiZhiBiao_turnover_rate_f;
            currentNodeImpl.MeiRiZhiBiao_price = shareObj.MeiRiZhiBiao_price;
            currentNodeImpl.MeiRiZhiBiao_total_mv = shareObj.MeiRiZhiBiao_total_mv;
            currentNodeImpl.MeiRiZhiBiao_circ_mv = shareObj.MeiRiZhiBiao_circ_mv;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_change = shareObj.RiXianXingQingvShiJianWeiXu_change;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_pct_chg;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_amount = shareObj.RiXianXingQingvShiJianWeiXu_amount;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_high = shareObj.RiXianXingQingvShiJianWeiXu_high;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_low = shareObj.RiXianXingQingvShiJianWeiXu_low;


            currentNodeImpl.RiXianXingQingvShiJianWeiXu_day3_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_day3_pct_chg;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_day5_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_day5_pct_chg;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_day10_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_day10_pct_chg;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_day15_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_day15_pct_chg;


            currentNodeImpl.RiXianXingQingvShiJianWeiXu_day30_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_day30_pct_chg;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_day60_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_day60_pct_chg;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_day90_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_day90_pct_chg;
            currentNodeImpl.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg = shareObj.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg;

//            public   double MeiRiZhiBiao_turnover_rate_f;  // 自由股换手率
//            public   float MeiRiZhiBiao_price;   // 每日指标  价格
//            public  float  MeiRiZhiBiao_total_mv; // 	总市值 （万元）
//            public  float  MeiRiZhiBiao_circ_mv;  // 流通市值（万元）
//
//
//            public   double RiXianXingQingvShiJianWeiXu_change;  // 日线行情 涨跌值
//            public   double RiXianXingQingvShiJianWeiXu_pct_chg;  // 日线行情 涨跌幅度
//            public   double RiXianXingQingvShiJianWeiXu_amount;     //  日线行情 成交额


/*            if(!currentNodeImpl.XinGuLieBiaoIPO_isIPO){

            }
            currentNodeImpl.XinGuLieBiaoIPO_isIPO =  shareObj.XinGuLieBiaoIPO_isIPO;
            currentNodeImpl.XinGuLieBiaoIPO_ballot= shareObj.XinGuLieBiaoIPO_ballot;
            currentNodeImpl.XinGuLieBiaoIPO_cname= shareObj.XinGuLieBiaoIPO_name;
            currentNodeImpl.XinGuLieBiaoIPO_funds= shareObj.XinGuLieBiaoIPO_funds;
            currentNodeImpl.XinGuLieBiaoIPO_ipo_date= shareObj.XinGuLieBiaoIPO_ipo_date;
            currentNodeImpl.XinGuLieBiaoIPO_issue_date= shareObj.XinGuLieBiaoIPO_issue_date;
            currentNodeImpl.XinGuLieBiaoIPO_limit_amount= shareObj.XinGuLieBiaoIPO_limit_amount;
            currentNodeImpl.XinGuLieBiaoIPO_market_amount= shareObj.XinGuLieBiaoIPO_market_amount;
            currentNodeImpl.XinGuLieBiaoIPO_name= shareObj.XinGuLieBiaoIPO_name;
            currentNodeImpl.XinGuLieBiaoIPO_pe= shareObj.XinGuLieBiaoIPO_pe;
            currentNodeImpl.XinGuLieBiaoIPO_price= shareObj.XinGuLieBiaoIPO_price;
            currentNodeImpl.XinGuLieBiaoIPO_ts_code= shareObj.XinGuLieBiaoIPO_ts_code;*/



            System.out.println("ZA_A3 close_price="+currentNodeImpl.price+"   currentNodeImpl.price="+currentNodeImpl.price+"  nodeImpl_tscode="+nodeImpl_tscode );

        }



    }





    static void InitArr_With_XinGuLieBiaoIPO(List<XinGuLieBiaoIPO> XinGuLieBiaoIPOArr) {

        synchronized(gupiaoliebiao_StockList){
            for (int i = 0; i < XinGuLieBiaoIPOArr.size(); i++) {
                XinGuLieBiaoIPO mXinGuLieBiaoIPO = XinGuLieBiaoIPOArr.get(i);
                String mXinGuLieBiaoIPO_tscode = mXinGuLieBiaoIPO.getTs_code();
                String mXinGuLieBiaoIPO_name = mXinGuLieBiaoIPO.getName();
                String mXinGuLieBiaoIPO_ipo_date = mXinGuLieBiaoIPO.getIpo_date();   // 上市首日
                String mXinGuLieBiaoIPO_issue_date = mXinGuLieBiaoIPO.getIssue_date();  // 申购日
                double mXinGuLieBiaoIPO_amount = mXinGuLieBiaoIPO.getAmount() ; //  发行数量  万股
                double mXinGuLieBiaoIPO_price = mXinGuLieBiaoIPO.getPrice() ; //  发行数量  万股
                double mXinGuLieBiaoIPO_pe = mXinGuLieBiaoIPO.getPe() ; //  市盈率
                double mXinGuLieBiaoIPO_limit_amount = mXinGuLieBiaoIPO.getLimit_amount();  // 个人申购上限（万股）
                double mXinGuLieBiaoIPO_funds = mXinGuLieBiaoIPO.getFunds() ;  // 募集资金
                double mXinGuLieBiaoIPO_ballot = mXinGuLieBiaoIPO.getBallot() ;  // 中签率
                double mXinGuLieBiaoIPO_market_amount = mXinGuLieBiaoIPO.getMarket_amount();


                Stock_NodeImpl Stock_NodeImpl = new Stock_NodeImpl(getNextStockMarketNodeID(),2);

                Stock_NodeImpl.setXinGuLieBiaoIPO_isIPO(true);
                Stock_NodeImpl.setXinGuLieBiaoIPO_ts_code(mXinGuLieBiaoIPO_tscode);
                Stock_NodeImpl.setXinGuLieBiaoIPO_name(mXinGuLieBiaoIPO_name);
                Stock_NodeImpl.setTs_code(mXinGuLieBiaoIPO_tscode);

                Stock_NodeImpl.setXinGuLieBiaoIPO_ipo_date(mXinGuLieBiaoIPO_ipo_date);
                Stock_NodeImpl.setXinGuLieBiaoIPO_issue_date(mXinGuLieBiaoIPO_issue_date);
                Stock_NodeImpl.setXinGuLieBiaoIPO_amount(mXinGuLieBiaoIPO_amount);
                Stock_NodeImpl.setXinGuLieBiaoIPO_price(mXinGuLieBiaoIPO_price);
                Stock_NodeImpl.setXinGuLieBiaoIPO_pe(mXinGuLieBiaoIPO_pe);
                Stock_NodeImpl.setXinGuLieBiaoIPO_funds(mXinGuLieBiaoIPO_funds);
                Stock_NodeImpl.setXinGuLieBiaoIPO_limit_amount(mXinGuLieBiaoIPO_limit_amount);
                Stock_NodeImpl.setXinGuLieBiaoIPO_ballot(mXinGuLieBiaoIPO_ballot);
                Stock_NodeImpl.setXinGuLieBiaoIPO_market_amount(mXinGuLieBiaoIPO_market_amount);

                //  新创建的 Stock_NodeImpl   必须 要有的项
                Stock_NodeImpl.setList_date(mXinGuLieBiaoIPO_ipo_date);   //  IPO 申购的 日期   //  setList_date
                Stock_NodeImpl.setName(mXinGuLieBiaoIPO_name);
                Stock_NodeImpl.setXinGuLieBiaoIPO_ts_code(mXinGuLieBiaoIPO_tscode);
                Stock_NodeImpl.setList_status("L");
                Stock_NodeImpl.setPrice((float)mXinGuLieBiaoIPO_price);
                gupiaoliebiao_StockList.add(Stock_NodeImpl);

                android.util.Log.i("zXinGuLieBiaoIPO","InitArr_With_XinGuLieBiaoIPO_ZIPO["+i+"] ="+mXinGuLieBiaoIPO.toString() +"\nStock_NodeImpl="+Stock_NodeImpl.toString());

  /*          StockNodeShareInfo shareObj = null;
            if (!tscode_stockShareInfo_Map.containsKey(mXinGuLieBiaoIPO_tscode)) {
                shareObj = new StockNodeShareInfo();
                tscode_stockShareInfo_Map.put(mXinGuLieBiaoIPO_tscode, shareObj);
            }

            shareObj = tscode_stockShareInfo_Map.get(mXinGuLieBiaoIPO_tscode);
            shareObj.XinGuLieBiaoIPO_isIPO=true;
            shareObj.XinGuLieBiaoIPO_ballot=mXinGuLieBiaoIPO_ballot;
            shareObj.XinGuLieBiaoIPO_cname=mXinGuLieBiaoIPO_name;
            shareObj.XinGuLieBiaoIPO_funds=mXinGuLieBiaoIPO_funds;
            shareObj.XinGuLieBiaoIPO_ipo_date=mXinGuLieBiaoIPO_ipo_date;
            shareObj.XinGuLieBiaoIPO_issue_date=mXinGuLieBiaoIPO_issue_date;
            shareObj.XinGuLieBiaoIPO_limit_amount=mXinGuLieBiaoIPO_limit_amount;
            shareObj.XinGuLieBiaoIPO_market_amount=mXinGuLieBiaoIPO_market_amount;
            shareObj.XinGuLieBiaoIPO_name=mXinGuLieBiaoIPO_name;
            shareObj.XinGuLieBiaoIPO_pe=mXinGuLieBiaoIPO_pe;
            shareObj.XinGuLieBiaoIPO_price=mXinGuLieBiaoIPO_price;
            shareObj.XinGuLieBiaoIPO_ts_code=mXinGuLieBiaoIPO_tscode;
*/
            }
        }


    }





    public static Stock_NodeImpl getLevel2_Area_IndustryStockNodeFromIndustryKey(List<Stock_NodeImpl> stockList ,  String industry){
        Stock_NodeImpl matchLevel2_IndustryNode = null;


        for (int i = 0; i < stockList.size(); i++) {
            Stock_NodeImpl stockIndustryItem = stockList.get(i);
//            print("getLevel2_Area_IndustryStockNodeFromIndustryKey i["+i+"]stockIndustryItem.name["+stockIndustryItem.name()+"]  industry="+industry);
            if(stockIndustryItem.name.equals(industry) && industry != null){
//                print("getLevel2_Area_IndustryStockNodeFromIndustryKey Get stockIndustryItem.name["+stockIndustryItem.name()+"]  industry="+industry);

                return stockIndustryItem;
            }

        }
        return matchLevel2_IndustryNode;


    }


    public static boolean isAreaContainIndustry(List<Stock_NodeImpl> stockList ,  String industry){
        boolean isContainInstryStock = false;


        for (int i = 0; i < stockList.size(); i++) {
            Stock_NodeImpl stockIndustryItem = stockList.get(i);
            if(stockIndustryItem.name.equals(industry)){
                return true;
            }

        }
        return isContainInstryStock;


    }




    static void InitArr_With_RiXianXingQingvShiJianWeiXu(List<RiXianXingQingvShiJianWeiXu>  mRiXianXingQingvShiJianWeiXuArr){



        for (int i = 0; i < mRiXianXingQingvShiJianWeiXuArr.size() ; i++) {
            RiXianXingQingvShiJianWeiXu mRiXianXingQingvShiJianWeiXu  = mRiXianXingQingvShiJianWeiXuArr.get(i);
            String mmRiXianXingQingvShiJianWeiXu_tscode =   mRiXianXingQingvShiJianWeiXu.getTs_code();
            double mRiXian_price =  mRiXianXingQingvShiJianWeiXu.getClose();   // 日线价格

            double mRiXian_change =  mRiXianXingQingvShiJianWeiXu.getChange();   // 日线涨跌值

            double mRiXian_pct_chg =  mRiXianXingQingvShiJianWeiXu.getPct_chg();   // 日线涨跌幅度

            double mRiXian_amount =  mRiXianXingQingvShiJianWeiXu.getAmount();   // 成交额


            double mRiXian_low =     mRiXianXingQingvShiJianWeiXu.getLow();
            double mRiXian_high =      mRiXianXingQingvShiJianWeiXu.getHigh();


/*            // 有些json 由于没有 day30的数据 所以会存在 crash 的情况  需要 避免
            double day3_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay3_pct_chg();
            double day5_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay5_pct_chg();
            double day10_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay10_pct_chg();
            double day15_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay15_pct_chg();*/


            double day3_pct_chg =     0d;
            double day5_pct_chg =      0d;
            double day10_pct_chg =      0d;
            double day15_pct_chg =     0d;

            if(mRiXianXingQingvShiJianWeiXu.getDay3_pct_chg() != null){
                day3_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay3_pct_chg();
            }


            if(mRiXianXingQingvShiJianWeiXu.getDay5_pct_chg() != null){
                day5_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay5_pct_chg();
            }


            if(mRiXianXingQingvShiJianWeiXu.getDay10_pct_chg() != null){
                day10_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay10_pct_chg();
            }


            if(mRiXianXingQingvShiJianWeiXu.getDay15_pct_chg() != null){
                day15_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay15_pct_chg();
            }





/*          // 有些json 由于没有 day30的数据 所以会存在 crash 的情况  需要 避免
            double day30_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay30_pct_chg();
            double day60_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay60_pct_chg();
            double day90_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay90_pct_chg();
            double dayyear_pct_chg =      mRiXianXingQingvShiJianWeiXu.getYear_pct_chg();*/

            double day30_pct_chg =     0d;
            double day60_pct_chg =      0d;
            double day90_pct_chg =      0d;
            double dayyear_pct_chg =     0d;

            if(mRiXianXingQingvShiJianWeiXu.getDay30_pct_chg() != null){
                day30_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay30_pct_chg();
            }


            if(mRiXianXingQingvShiJianWeiXu.getDay60_pct_chg() != null){
                day60_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay60_pct_chg();
            }


            if(mRiXianXingQingvShiJianWeiXu.getDay90_pct_chg() != null){
                day90_pct_chg =      mRiXianXingQingvShiJianWeiXu.getDay90_pct_chg();
            }


            if(mRiXianXingQingvShiJianWeiXu.getYear_pct_chg() != null){
                dayyear_pct_chg =      mRiXianXingQingvShiJianWeiXu.getYear_pct_chg();
            }





            StockNodeShareInfo shareObj = null;
            if(!tscode_stockShareInfo_Map.containsKey(mmRiXianXingQingvShiJianWeiXu_tscode)){
                shareObj = new StockNodeShareInfo();
                tscode_stockShareInfo_Map.put(mmRiXianXingQingvShiJianWeiXu_tscode,shareObj);
            }
            shareObj =  tscode_stockShareInfo_Map.get(mmRiXianXingQingvShiJianWeiXu_tscode);


            shareObj.RiXianXingQingvShiJianWeiXu_amount =mRiXian_amount;
            shareObj.RiXianXingQingvShiJianWeiXu_pct_chg =mRiXian_pct_chg;
            shareObj.RiXianXingQingvShiJianWeiXu_change =mRiXian_change;
            shareObj.RiXianXingQingvShiJianWeiXu_high =mRiXian_high;
            shareObj.RiXianXingQingvShiJianWeiXu_low =mRiXian_low;

            shareObj.RiXianXingQingvShiJianWeiXu_day3_pct_chg = day3_pct_chg;
            shareObj.RiXianXingQingvShiJianWeiXu_day5_pct_chg = day5_pct_chg;
            shareObj.RiXianXingQingvShiJianWeiXu_day10_pct_chg = day10_pct_chg;
            shareObj.RiXianXingQingvShiJianWeiXu_day15_pct_chg = day15_pct_chg;

            shareObj.RiXianXingQingvShiJianWeiXu_day30_pct_chg = day30_pct_chg;
            shareObj.RiXianXingQingvShiJianWeiXu_day60_pct_chg = day60_pct_chg;
            shareObj.RiXianXingQingvShiJianWeiXu_day90_pct_chg = day90_pct_chg;
            shareObj.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg = dayyear_pct_chg;

            // turnover_rate_f	float	换手率（自由流通股）

        }



    }

    static void InitArr_With_MeiRiZhiBiao(List<MeiRiZhiBiao> mMeiRiZhiBiaoArr){

        for (int i = 0; i < mMeiRiZhiBiaoArr.size() ; i++) {
            MeiRiZhiBiao mMeiRiZhiBiao = mMeiRiZhiBiaoArr.get(i);
            String mMeiRiZhiBiao_tscode =   mMeiRiZhiBiao.getTs_code();
            double realPrice =  mMeiRiZhiBiao.getClose();   // 价格



            double Total_mv =   mMeiRiZhiBiao.getTotal_mv();   // 总市值
            double Circ_mv =    mMeiRiZhiBiao.getCirc_mv();  // 流通市值



            Double turnover_rate_f =  mMeiRiZhiBiao.getTurnover_rate_f();  // 换手率

            StockNodeShareInfo shareObj = null;
            if(!tscode_stockShareInfo_Map.containsKey(mMeiRiZhiBiao_tscode)){
                shareObj = new StockNodeShareInfo();
                tscode_stockShareInfo_Map.put(mMeiRiZhiBiao_tscode,shareObj);
            }
            shareObj =  tscode_stockShareInfo_Map.get(mMeiRiZhiBiao_tscode);
            if(shareObj == null){
                return;
            }
            shareObj.price = (float)realPrice;

            if(turnover_rate_f != null){
                shareObj.MeiRiZhiBiao_turnover_rate_f =turnover_rate_f.doubleValue();
            }


            shareObj.MeiRiZhiBiao_price =  (float) realPrice;
            shareObj.MeiRiZhiBiao_total_mv =  (float) Total_mv;
            shareObj.MeiRiZhiBiao_circ_mv =  (float) Circ_mv;

            // turnover_rate_f	float	换手率（自由流通股）

        }



    }



//      tscode_stockShareInfo_Map

/*
out1: for (int i = 0; i < gupiaoliebiao_StockList.size(); i++) {
          Stock_NodeImpl currentNodeImpl = gupiaoliebiao_StockList.get(i);
          String nodeImpl_tscode = currentNodeImpl.ts_code;

 out2:    for (int j = 0; j < mMeiRiZhiBiaoArr.size() ; j++) {
              MeiRiZhiBiao mMeiRiZhiBiao = mMeiRiZhiBiaoArr.get(j);
            String mMeiRiZhiBiao_tscode =   mMeiRiZhiBiao.getTs_code();
             System.out.println("ZA_A1["+i+"_"+nodeImpl_tscode+"]["+j+"_"+mMeiRiZhiBiao_tscode+"]  nodeImpl_tscode="+nodeImpl_tscode+"     mMeiRiZhiBiao_tscode="+mMeiRiZhiBiao_tscode );
            if(nodeImpl_tscode.equals(mMeiRiZhiBiao_tscode)){
              double close_price =   mMeiRiZhiBiao.getClose();
                System.out.println("ZA_A2 close_price="+close_price+"   currentNodeImpl.price="+currentNodeImpl.price+"  nodeImpl_tscode="+nodeImpl_tscode+"     mMeiRiZhiBiao_tscode="+mMeiRiZhiBiao_tscode );

                currentNodeImpl.price = (float)close_price;

                System.out.println("ZA_A3 close_price="+close_price+"   currentNodeImpl.price="+currentNodeImpl.price+"  nodeImpl_tscode="+nodeImpl_tscode+"     mMeiRiZhiBiao_tscode="+mMeiRiZhiBiao_tscode );

                break out2;
            }

          }



      }*/






    public static void showMap(Map<String, ArrayList<String>> mStr_ArrStr_Map , String mTip , boolean isInitLevel2AllCategory) {
        print("____showMap_PreA____");
//        StringBuilder sb = new StringBuilder();
        ArrayList<String> logArr = new   ArrayList<String>();

        Map.Entry<String, ArrayList<String>> entry;

        print("____showMap_PreB____");

        // all_新疆 ------ 对应新疆的股票列表
        Map<String,ArrayList<Stock_NodeImpl>>  level2_allItem_Map  = new HashMap<String,ArrayList<Stock_NodeImpl>>();

        if (mStr_ArrStr_Map != null) {
            Iterator iterator = mStr_ArrStr_Map.entrySet().iterator();

            print("═══════════════════ AshowMap 【 "+mTip+" 】 Begin  ═══════════════════" );
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<String>>) iterator.next();

                // 获取 名称的 首字母
                String oneWordStr = entry.getKey();
                ArrayList<String> categoryNameList = entry.getValue();
//                sb.append("mapKey(" + oneWordStr + "_" + categoryNameList.size() + ")【");
                logArr.add("__________________ ZKey["+oneWordStr+"] Size["+categoryNameList.size()+"] Begin "+"__________________");
                logArr.add("mapKey["+oneWordStr+"] Size["+categoryNameList.size()+"]  \n");
                String MapKey_All = "All_"+oneWordStr;    // all_上海   all_新疆 符合这个区域的 股票列表集合
                ArrayList<Stock_NodeImpl> MapKey_All_Stock = new  ArrayList<Stock_NodeImpl>();
                for (int i = 0; i < categoryNameList.size(); i++) {
                    String categoryItem = categoryNameList.get(i);

                    ArrayList<Stock_NodeImpl> matchStockList =        level2AreaIndustry_level3StockImplList_Map.get(categoryItem);
                    int matchStockSize = matchStockList == null? 0:matchStockList.size();
                    if(matchStockList != null ){
                        MapKey_All_Stock.addAll(matchStockList);
                    }

                    logArr.add("Key["+oneWordStr+"]   ValueArr["+i+"]=["+categoryItem+"] StockSize["+matchStockSize+"]");

                }

                if(MapKey_All_Stock != null && MapKey_All_Stock.size() > 0 ){
                    level2_allItem_Map.put(MapKey_All,MapKey_All_Stock);

                }


//                logArr.add("Key["+oneWordStr+"]   ValueArr["+categoryNameList.size()+"]=["+MapKey_All+"] StockSize["+MapKey_All_Stock.size()+"]");


                logArr.add("__________________ ZKey["+oneWordStr+"] End "+"__________________");


            }


//            for (int i = 0; i < logArr.size(); i++) {
//               String logItem =  logArr.get(i);
//                print(logItem);
//            }

            File  J0_Area_Log = new File(mStock_Root_File+File.separator+"J0_Area_Log.txt");
            writeContentToFile(J0_Area_Log, logArr);

            print("【抓取 J0_Area_Log 方法: 】  " + "adb pull "+J0_Area_Log.getAbsolutePath()+" .");
            print("═══════════════════ AshowMap 【 "+mTip+" 】 End  ═══════════════════" );

//            print(sb.toString());
        }else{
            print("showMap 输入的参数 mStr_ArrStr_Map 为空!!  mTip = 【 "+ mTip +"】");
        }

        if(isInitLevel2AllCategory){
//            initAreaAllItem(  level2_allItem_Map );

        }




    }

    public static RequestQueue mSimulateRequestqueue;


    // 直接通过 ts_code 访问 新浪的 股价 接口
    public static void queryStockNowPriceWith_TX_Tscode(String mTscode , Context mContent
            , EditText priceEditText , TextView allBuyText ,
                                                        TextView dieText , TextView todayOpenText , TextView zhangText){

        double matchPrice = -1 ;
        String  shTscode = "sh"+mTscode;
        if(mTscode.startsWith("6")){
            shTscode = "sh"+mTscode;
        } else if(mTscode.startsWith("8")){
            shTscode = "bj"+mTscode;
        } else {
            shTscode = "sz"+mTscode;
        }
        print(" TitleBuyStockA 请求 "+shTscode+" TX接口 decimalFormat = "+matchPrice);

        if (shTscode == null || shTscode.equals("")) {
            return ;
        }
        print(" TitleBuyStockB 请求 "+shTscode+" 新浪接口 decimalFormat = "+matchPrice);

        final  String shTscode_final =   shTscode  ;

        if (mSimulateRequestqueue == null){
            if(mContent != null){
                mSimulateRequestqueue = Volley.newRequestQueue(mContent);
            }else{
                mSimulateRequestqueue = Volley.newRequestQueue(ZMainApplication.getInstance().getBaseContext());
            }

        }

        //新浪股票API，url类似：https://hq.sinajs.cn/list=sh600000,sh600536
        // TX 的 API 接口   https://qt.gtimg.cn/q=sh600000,sh600536

        String url = "http://qt.gtimg.cn/q=" + shTscode;

        //实例化一个 StringRequest作为网络请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        if (response.trim().startsWith("v_")){  // 成功调用

                            String[] leftRight = response.split("=");
                            String right = leftRight[1].replaceAll("\"", "");
                            String[] stockArray = right.split("~");





                            // 昨日收盘盘价
                            double    nowPrice = Double.parseDouble(stockArray[3]);


                            // 昨日收盘盘价
                            double    YesterDayCloseValue = Double.parseDouble(stockArray[4]);


                            // 今日开盘价
                            double    TodayOpenValue = Double.parseDouble(stockArray[5]);


                            if(YesterDayCloseValue != 0){
                                String zhangtingStr =    getTodayZhangTingPriceStr(mTscode,YesterDayCloseValue);

                                String ditTingStr =    getTodayDieTingPriceStr(mTscode,YesterDayCloseValue);
                                zhangText.setText("涨停:"+zhangtingStr);
                                dieText.setText("跌停:"+ditTingStr);

                            }

                            // 昨日收盘盘价
                            if(TodayOpenValue != 0){
                                DecimalFormat priceFormat  = new DecimalFormat("#0.00");

                                todayOpenText.setText("开盘:"+priceFormat.format(TodayOpenValue));
                            }


                            double    curValue = nowPrice;
                            DecimalFormat   decimalFormat = new DecimalFormat("#0.00");
                            if(priceEditText != null && allBuyText != null){
                                priceEditText.setText(decimalFormat.format(curValue));
                                allBuyText.performClick();
                            }



                            print(" TX TSCode_请求_OK   mTscode="+mTscode);
                        } else{
                            print(" TX  TSCode_请求_Failed   mTscode="+mTscode);


                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        LogUtil.e("请求数据失败");
                        print(" 请求 "+shTscode_final+" TX 接口失败  url="+url +"   error="+error);

                    }
                });

        mSimulateRequestqueue.add(stringRequest);
        mSimulateRequestqueue.start();
        print(" TitleBuyStockC 请求 "+shTscode+" TX 接口 decimalFormat = "+matchPrice);

    }



    // 直接通过 ts_code 访问 TX的 股价 接口
    public static void queryStockNowPriceWith_Sina_Tscode(String mTscode , Context mContent
            , EditText priceEditText , TextView allBuyText ,
                                                          TextView dieText , TextView todayOpenText , TextView zhangText){

        double matchPrice = -1 ;
        String  shTscode = "sh"+mTscode;
        if(mTscode.startsWith("6")){
            shTscode = "sh"+mTscode;
        } else if(mTscode.startsWith("8")){
            shTscode = "bj"+mTscode;
        } else {
            shTscode = "sz"+mTscode;
        }
        print(" TitleBuyStockA 请求 "+shTscode+" TX接口 decimalFormat = "+matchPrice);

        if (shTscode == null || shTscode.equals("")) {
            return ;
        }
        print(" TitleBuyStockB 请求 "+shTscode+" TX 接口 decimalFormat = "+matchPrice);

        final  String shTscode_final =   shTscode  ;

        if (mSimulateRequestqueue == null){
            if(mContent != null){
                mSimulateRequestqueue = Volley.newRequestQueue(mContent);
            }else{
                mSimulateRequestqueue = Volley.newRequestQueue(ZMainApplication.getInstance().getBaseContext());
            }

        }

        //新浪股票API，url类似：http://hq.sinajs.cn/list=sh600000,sh600536
        // TX 的 API 接口   https://qt.gtimg.cn/q=sh600000,sh600536
        String url = "http://hq.sinajs.cn/list=s" + shTscode;

        //     // https://qt.gtimg.cn/q=sz003022,sh601318,sz000002,sz300075

        //实例化一个 StringRequest作为网络请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //   单独   请求 tscode

                        String[] leftRight = response.split("=");
                        String right = leftRight[1].replaceAll("\"", "");
                        String[] stockArray = right.split(",");
                        if (stockArray != null && stockArray.length >= 30){


                            // 今日开盘价
                            double    TodayOpenValue = Double.parseDouble(stockArray[1]);

                            // 昨日收盘盘价
                            double    YesterDayCloseValue = Double.parseDouble(stockArray[2]);

                            if(YesterDayCloseValue != 0){
                                String zhangtingStr =    getTodayZhangTingPriceStr(mTscode,YesterDayCloseValue);

                                String ditTingStr =    getTodayDieTingPriceStr(mTscode,YesterDayCloseValue);
                                zhangText.setText("涨停:"+zhangtingStr);
                                dieText.setText("跌停:"+ditTingStr);

                            }

                            if(TodayOpenValue != 0){
                                DecimalFormat priceFormat  = new DecimalFormat("#0.00");

                                todayOpenText.setText("开盘:"+priceFormat.format(TodayOpenValue));
                            }


                            double    curValue = Double.parseDouble(stockArray[3]);
                            DecimalFormat   decimalFormat = new DecimalFormat("#0.00");
                            if(priceEditText != null && allBuyText != null){
                                priceEditText.setText(decimalFormat.format(curValue));
                                allBuyText.performClick();
                            }




                            print(" TitleBuyStock 请求 "+shTscode_final+" SINA 接口 decimalFormat = "+curValue+"  response="+response);

                        } else{
                            print(" TitleBuyStock  请求 "+shTscode_final+" SINA 接口 Price失败  response="+response);

                            queryStockNowPriceWith_TX_Tscode(mTscode,mContent,priceEditText,allBuyText,dieText,todayOpenText,zhangText);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        LogUtil.e("请求数据失败");
                        print(" 请求 "+shTscode_final+" Sina 接口失败  url="+url +"   error="+error);

                        queryStockNowPriceWith_TX_Tscode(mTscode,mContent,priceEditText,allBuyText,dieText,todayOpenText,zhangText);

                    }
                });

        mSimulateRequestqueue.add(stringRequest);
        mSimulateRequestqueue.start();

        print(" TitleBuyStockC 请求 "+shTscode+" TX接口 decimalFormat = "+matchPrice);


    }


    // tscode   以 sz  和 sh  开头
    public static void queryStockWithSina(StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean stockBean, Context mContent, StockSimulateTradeAdapter adapter) {
        // Volley作为网络请求
        String mTscode =  stockBean.getStocktscode();

        String  shTscode = "sh"+mTscode;
        if(mTscode.startsWith("6")){
            shTscode = "sh"+mTscode;
        }else if(mTscode.startsWith("8")){
            shTscode = "bj"+mTscode;
        } else{
            shTscode = "sz"+mTscode;
        }

        print(" 请求 "+shTscode+" 新浪接口失败   stockBean = "+stockBean);

        if (shTscode == null || shTscode.equals("")) {
            return ;
        }
        final  String shTscode_final =   shTscode  ;

        if (mSimulateRequestqueue == null){
            if(mContent != null){
                mSimulateRequestqueue = Volley.newRequestQueue(mContent);
            }else{
                mSimulateRequestqueue = Volley.newRequestQueue(ZMainApplication.getInstance().getBaseContext());
            }


        }



        //新浪股票API，url类似：http://hq.sinajs.cn/list=sh600000,sh600536
        String url = "https://hq.sinajs.cn/list=" + shTscode;

        //实例化一个 StringRequest作为网络请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String[] leftRight = response.split("=");
                        String right = leftRight[1].replaceAll("\"", "");
                        String[] stockArray = right.split(",");
                        if (stockArray != null && stockArray.length >= 30){

                            double    preClodePrice = Double.parseDouble(stockArray[2]);

                            double    curValue = Double.parseDouble(stockArray[3]);


                            stockBean.setStocknowprice(curValue);


                            if(preClodePrice != 0){

                                if(stockBean.stockname.startsWith("退")  || stockBean.stockname.endsWith("退")){
                                    stockBean.setPredaystockprice(curValue);  //  如果 有退市股 那么 不能计算它的 今日盈亏  定格为 0
                                }else{
                                    stockBean.setPredaystockprice(preClodePrice);
                                }


                            }



                            if(adapter != null){
                                adapter.notifyDataChange_NoJson();
                            }

//                          if(curValue != nowPrice &&  curValue != 0 ){
//                              stockBean.setStocknowprice(curValue);
//                              if(adapter != null){
//                                  adapter.notifyDataChange_NoJson();
//                              }
//
//                          }




                            print(" AA请求_OK "+shTscode_final+" 新浪接口 Price = "+curValue+"  preClodePrice="+preClodePrice+" stockBean.name="+stockBean.stockname+"  isImage="+stockBean.isImageStockBean);

                        } else{
                            print(" AA请求_Failed "+shTscode_final+" 新浪接口 Price失败  response="+response+"   stockBean.name="+stockBean.stockname+"  isImage="+stockBean.isImageStockBean);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        LogUtil.e("请求数据失败");
                        print(" AA请求 "+shTscode_final+" 新浪接口失败  url="+url +"   error="+error);
                        print(" AA请求_Failed "+shTscode_final+" 新浪接口 Price = stockBean.name="+stockBean.stockname+"  isImage="+stockBean.isImageStockBean);

                    }
                });


//        com.android.volley.Cache.Entry requestEntry = new com.android.volley.Cache.Entry();
//
//        stringRequest.setCacheEntry(requestEntry);
        mSimulateRequestqueue.add(stringRequest);
        mSimulateRequestqueue.start();
    }



    // 以 模拟账户为整体   拼接 tscode 形成
    // https://qt.gtimg.cn/q=sz003022,sh601318,sz000002,sz300075    多部请求
    public static void queryUserStockListWithTX(StockSimulate_BankBean.StockSimulate_UserBean  userBean, Context mContent, StockSimulateTradeAdapter adapter) {

        if (mSimulateRequestqueue == null){
            if(mContent != null){
                mSimulateRequestqueue = Volley.newRequestQueue(mContent);
            }else{
                mSimulateRequestqueue = Volley.newRequestQueue(ZMainApplication.getInstance().getBaseContext());
            }


        }

        // https://qt.gtimg.cn/q=sz003022,sh601318,sz000002,sz300075
        String url = userBean.getQTQueryUrl();

        //实例化一个 StringRequest作为网络请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (response.trim().startsWith("v_")){  // 成功调用


                            userBean.parseTXReponse(response);

                            if(adapter != null){
                                adapter.notifyDataChange_NoJson();
                            }

                            print(" TX 请求_OK   user["+userBean.getUsertitle()+"]"+" 新浪接口 url = "+url);

                        } else{
                            print(" TX 请求_Failed   user["+userBean.getUsertitle()+"]"+" 新浪接口 url = "+url);                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        LogUtil.e("请求数据失败");
                        print(" TX_请求_Failed   user["+userBean.getUsertitle()+"]"+"  新浪接口 url= "+ url+"  error="+ error);

                    }
                });

        mSimulateRequestqueue.add(stringRequest);
        mSimulateRequestqueue.start();
    }

    // 以 模拟账户为整体   拼接 tscode 形成
    // https://hq.sinajs.cn/list=sz003022,sh601318,sz000002,sz300075    多部请求
    public static void queryUserStockListWithSina(StockSimulate_BankBean.StockSimulate_UserBean  userBean, Context mContent, StockSimulateTradeAdapter adapter) {

        if (mSimulateRequestqueue == null){
            if(mContent != null){
                mSimulateRequestqueue = Volley.newRequestQueue(mContent);
            }else{
                mSimulateRequestqueue = Volley.newRequestQueue(ZMainApplication.getInstance().getBaseContext());
            }


        }

        //新浪股票API，url类似：http://hq.sinajs.cn/list=sh600000,sh600536
        String url = userBean.getSinaQueryUrl();

        //实例化一个 StringRequest作为网络请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (response.trim().startsWith("var")){  // 成功调用


                            userBean.parseSinaReponse(response);

                            if(adapter != null){
                                adapter.notifyDataChange_NoJson();
                            }

                            print(" AA请求_OK   user["+userBean.getUsertitle()+"]"+" 新浪接口 url = "+url);

                        } else{
                            print(" AA请求_Failed   user["+userBean.getUsertitle()+"]"+" 新浪接口 url = "+url);                        }
                        queryUserStockListWithTX(userBean,mContent,adapter);   //  sina 调用失败   尝试调用Tx接口
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        LogUtil.e("请求数据失败");
                        print(" Sina 请求_Failed   user["+userBean.getUsertitle()+"]"+"  新浪接口 url= "+ url+"  error="+ error);
                        queryUserStockListWithTX(userBean,mContent,adapter);   //  sina 调用失败   尝试调用Tx接口
                    }
                });

        mSimulateRequestqueue.add(stringRequest);

        mSimulateRequestqueue.start();
    }


    public static String getTodayZhangTingPriceStr(String stocktscode , double predaystockprice ){
        double upValue = 0 ;
        DecimalFormat priceFormat  = new DecimalFormat("#0.00");
        String resultStr = "";
        if(stocktscode.startsWith("3")){  // 创业板
            upValue = predaystockprice * 0.2+predaystockprice;
            resultStr =  priceFormat.format(upValue);
        } else if(stocktscode.startsWith("8")){
            resultStr =  "+∞";

        }else if(predaystockprice == 0){
            resultStr ="NaN";

        } else {

            upValue = predaystockprice * 0.1+predaystockprice;
            resultStr =  priceFormat.format(upValue);
        }

        return resultStr;
    }


    public static String getTodayDieTingPriceStr(String stocktscode , double predaystockprice ){
        DecimalFormat priceFormat  = new DecimalFormat("#0.00");
        double upValue = 0 ;
        String resultStr = "";
        if(stocktscode.startsWith("3")){  // 创业板
            upValue = predaystockprice - predaystockprice * 0.2;
            resultStr =  priceFormat.format(upValue);
        } else if(stocktscode.startsWith("8")){
            resultStr =  "-∞";

        }else if(predaystockprice == 0){
            resultStr ="NaN";

        } else {

            upValue = predaystockprice - predaystockprice * 0.1;
            resultStr =  priceFormat.format(upValue);
        }

        return resultStr;
    }

    static  public     String getTimeStamp_YYYY_MM_DD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }



    public static boolean Download_Git_With_Prefix_Path( String inputEndPath  , File localFile){  // 2023_main_stock.xlsx    和 本地下载的路径  /sdcard/1/2023_main_stock.xlsx
boolean downloadFlag = false;

        ArrayList<String> multiGitUrlList = new   ArrayList<String>();
        String GitMainAdress = "ZukGit/ActionDemo/main/J0_Data";


        String gitAddress_Pre_1 = "https://gcore.jsdelivr.net/gh/ZukGit/ActionDemo@main/J0_Data"+"/";
        String gitAddress_Pre_2 = "https://raw.githubusercontent.com/"+GitMainAdress+"/";
        String gitAddress_Pre_3 = "https://github-do.panbaidu.cn//https://raw.githubusercontent.com/"+GitMainAdress+"/";

        String gitAddress_Pre_4 = "https://ghproxy.com/https://raw.githubusercontent.com/"+GitMainAdress+"/";


        // https://github-do.panbaidu.cn//https://raw.githubusercontent.com/ZukGit/ActionDemo/main/J0_Data/day_2022_0607.json
        String targetJsonUrl_1 = gitAddress_Pre_1+inputEndPath;

        // https://raw.githubusercontent.com/ZukGit/ActionDemo/main/J0_Data/day_2022_0607.json
        String targetJsonUrl_2 = gitAddress_Pre_2+inputEndPath;

        // https://gcore.jsdelivr.net/gh/ZukGit/ActionDemo@main/J0_Data/day_2022_0607.json
        String targetJsonUrl_3 = gitAddress_Pre_3+inputEndPath;
        String targetJsonUrl_4 = gitAddress_Pre_4+inputEndPath;

        multiGitUrlList.add(targetJsonUrl_1 );
        multiGitUrlList.add(targetJsonUrl_2);
        multiGitUrlList.add(targetJsonUrl_3);
        multiGitUrlList.add(targetJsonUrl_4);

        for (int i = 0; i < multiGitUrlList.size(); i++) {

            String targetJsonUrl = multiGitUrlList.get(i);

            // 本地的 json文件路径
//            File CUR_Local_Byte_File = new File(DataHolder.ZMain_File+File.separator+"stock"+File.separator+""+inputEndPath);  // day_2022_0225.json
            File CUR_Local_Byte_File =   localFile ;

            print("当前 isGitDown  上的 "+CUR_Local_Byte_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"尝试下载!");
            if(Download_Git_ByteFile(targetJsonUrl,CUR_Local_Byte_File.getAbsolutePath())){

                print("当前 isGitDown Git_Byte  上的 "+CUR_Local_Byte_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"已经下载到本地!!");
                return true;
            }

/*            if(CUR_Local_Byte_File.exists() && CUR_Local_Byte_File.length() > 100){  // 重新下载新的  不去判断是否存在
                print("当前 CUR_Local_Byte_File  上的 "+CUR_Local_Byte_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"已经存在于本地 将不会执行下载操作");
            }else{
                print("当前 CUR_Local_Byte_File  上的 "+CUR_Local_Byte_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"不存在本地   将执行下载操作");

            }*/


        }

        if(!downloadFlag){
            print("当前 isGitDown  上的 "+localFile.getAbsolutePath()+" 尝试下载 完全失败!");
        }
        return downloadFlag;


    }

    public static boolean Download_Git_ByteFile(String urlpath, String savepath){

        int byteRead;

        try{
            URL url = new URL(urlpath);

            URLConnection conn = url.openConnection();

            InputStream inStream = conn.getInputStream();


            File fileSavePath = new File(savepath);
            if(!fileSavePath.exists()){
                fileSavePath.createNewFile();
            }


            // 写入文件
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            Log.i("Download_Git_ByteFile","urlpath="+urlpath+"  fileSavePath="+fileSavePath+" 下载成功A!");
            inStream.close();
            fs.close();

        }catch(Exception e){
            Log.i("Download_Git_ByteFile","urlpath="+urlpath+"  fileSavePath="+savepath+" 下载失败!");

            return false;
        }
        Log.i("Download_Git_ByteFile","urlpath="+urlpath+"  fileSavePath="+savepath+" 下载成功B!");
        return true;


    }

    public static boolean Download_Git_JsonFile_With_IntFlag( int dayFlag ){

        ArrayList<String> multiGitUrlList = new   ArrayList<String>();

        String GitMainAdress = "ZukGit/ActionDemo/main/J0_Data";

        String gitAddress_Pre_1 = "https://github-do.panbaidu.cn//https://raw.githubusercontent.com/"+GitMainAdress+"/day_";


        String gitAddress_Pre_2 = "https://raw.githubusercontent.com/"+GitMainAdress+"/day_";

        String gitAddress_Pre_3 = "https://gcore.jsdelivr.net/gh/ZukGit/ActionDemo@main/J0_Data"+"/day_";

        String gitAddress_Pre_4 = "https://ghproxy.com/https://raw.githubusercontent.com/"+GitMainAdress+"/day_";


//        https://gcore.jsdelivr.net/gh/ZukGit/ActionDemo@main/J0_Data/day_2022_0607.json

        // 这里 还要有 判断 最新 交易日的时间  只有当天交易日的下午5点后才能获取当前最新的数据
        String todayStr  = dayFlag+"";
        print("当前需要下载文件的 dayIntFlag ="+ dayFlag);
        if(todayStr.length() != 8){
            print("当前需要下载文件的 dayIntFlag ="+ dayFlag +" 不是标准的8位格式  例如 20230101 八位的数值");
            return false;
        }

        String curYear = (""+todayStr).substring(0,4);
        String MonthStr = (""+todayStr).substring(4,6);
        String DayStr = (""+todayStr).substring(6);


        // 20220228 --> 2020_0228
        todayStr = todayStr.replace(curYear,curYear+"_");


        String gitAddress_End =".json";
        // day_2022_0217.json

        // https://github-do.panbaidu.cn//https://raw.githubusercontent.com/ZukGit/ActionDemo/main/J0_Data/day_2022_0607.json
        String targetJsonUrl_1 = gitAddress_Pre_1+todayStr+gitAddress_End;

        // https://raw.githubusercontent.com/ZukGit/ActionDemo/main/J0_Data/day_2022_0607.json
        String targetJsonUrl_2 = gitAddress_Pre_2+todayStr+gitAddress_End;

        // https://gcore.jsdelivr.net/gh/ZukGit/ActionDemo@main/J0_Data/day_2022_0607.json
        String targetJsonUrl_3 = gitAddress_Pre_3+todayStr+gitAddress_End;

        String targetJsonUrl_4 = gitAddress_Pre_4+todayStr+gitAddress_End;
        multiGitUrlList.add(targetJsonUrl_4);
        multiGitUrlList.add(targetJsonUrl_3);
        multiGitUrlList.add(targetJsonUrl_2);
        multiGitUrlList.add(targetJsonUrl_1);



        for (int i = 0; i < multiGitUrlList.size(); i++) {

            String targetJsonUrl = multiGitUrlList.get(i);

            // 本地的 json文件路径
            File CUR_Local_Json_File = new File(DataHolder.ZMain_File+File.separator+"stock"+File.separator+"day_"+todayStr+gitAddress_End);  // day_2022_0225.json

            if(CUR_Local_Json_File.exists() && CUR_Local_Json_File.length() > 10000){  // 必须 确保 daily_json 的 文件大于 10000
                print("当前 Git_Json 上的 "+CUR_Local_Json_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"已经存在于本地 将不会执行下载操作");
                return false;
            }else{
                print("当前 Git_Json 上的 "+CUR_Local_Json_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"不存在本地   将执行下载操作");

                if(Download_Git_Json(targetJsonUrl,CUR_Local_Json_File.getAbsolutePath())){

                    print("当前 Git_Json 上的 "+CUR_Local_Json_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"已经下载到本地!!");
                    return true;
                }

            }


        }

        print("当前 Git_Json 上的  daily_json 文件 "+dayFlag+"   完全下载失败  经过 【"+multiGitUrlList.size()+"】 个代理路径 都下载失败! ");
        return false;

    }



    public static void Download_Last_Git_JsonFile(){

        ArrayList<String> multiGitUrlList = new   ArrayList<String>();

        String GitMainAdress = "ZukGit/ActionDemo/main/J0_Data";

        String gitAddress_Pre_1 = "https://github-do.panbaidu.cn//https://raw.githubusercontent.com/"+GitMainAdress+"/day_";


        String gitAddress_Pre_2 = "https://raw.githubusercontent.com/"+GitMainAdress+"/day_";

        String gitAddress_Pre_3 = "https://gcore.jsdelivr.net/gh/ZukGit/ActionDemo@main/J0_Data"+"/day_";





//        https://gcore.jsdelivr.net/gh/ZukGit/ActionDemo@main/J0_Data/day_2022_0607.json

        // 这里 还要有 判断 最新 交易日的时间  只有当天交易日的下午5点后才能获取当前最新的数据
        String todayStr  = TimeUtil.getCurrentYYYYMMDDWith17Point()+"";

        String curYear = TimeUtil.getYYYY();
        // 20220228 --> 2020_0228
        todayStr = todayStr.replace(curYear,curYear+"_");


        String gitAddress_End =".json";
        // day_2022_0217.json

        // https://github-do.panbaidu.cn//https://raw.githubusercontent.com/ZukGit/ActionDemo/main/J0_Data/day_2022_0607.json
        String targetJsonUrl_1 = gitAddress_Pre_1+todayStr+gitAddress_End;

        // https://raw.githubusercontent.com/ZukGit/ActionDemo/main/J0_Data/day_2022_0607.json
        String targetJsonUrl_2 = gitAddress_Pre_2+todayStr+gitAddress_End;

        // https://gcore.jsdelivr.net/gh/ZukGit/ActionDemo@main/J0_Data/day_2022_0607.json
        String targetJsonUrl_3 = gitAddress_Pre_3+todayStr+gitAddress_End;
        multiGitUrlList.add(targetJsonUrl_3);
        multiGitUrlList.add(targetJsonUrl_2);
        multiGitUrlList.add(targetJsonUrl_1);



        for (int i = 0; i < multiGitUrlList.size(); i++) {

            String targetJsonUrl = multiGitUrlList.get(i);

            // 本地的 json文件路径
            File CUR_Local_Json_File = new File(DataHolder.ZMain_File+File.separator+"stock"+File.separator+"day_"+todayStr+gitAddress_End);  // day_2022_0225.json

            if(CUR_Local_Json_File.exists() && CUR_Local_Json_File.length() > 10000){  // 必须 确保 daily_json 的 文件大于 10000
                print("当前 Git_Json 上的 "+CUR_Local_Json_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"已经存在于本地 将不会执行下载操作");
            }else{
                print("当前 Git_Json 上的 "+CUR_Local_Json_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"不存在本地   将执行下载操作");

                if(Download_Git_Json(targetJsonUrl,CUR_Local_Json_File.getAbsolutePath())){

                    print("当前 Git_Json 上的 "+CUR_Local_Json_File.getAbsolutePath()+"   Url["+i+"]["+targetJsonUrl+"]"+"已经下载到本地!!");
                    return;
                }

            }


        }



    }

    public static boolean Download_Git_Json(String urlpath, String savepath){
        try{
            URL url = new URL(urlpath);

//	        BufferedInputStream in = new BufferedInputStream(url.openStream());

            InputStreamReader inputstream = new InputStreamReader(url.openStream(), "UTF-8");

            BufferedReader in = new BufferedReader(inputstream);
            int line1;
            StringBuffer sb1 = new StringBuffer();
            while((line1=in.read())!=-1){
                sb1.append((char)line1);
            }
            String str1 = sb1.toString();

            File day_json_File = new File(savepath);
            Write_Git_JsonFile(day_json_File,str1);

            print("Download_Git_Json Failed day_json_File Size ="+day_json_File.length());

            if(day_json_File.length() < 10000){  // 当前的 daily 肯定必须大于 10000 bit 的
                return false;
            }
        }catch(Exception e){
            print("Download_Git_Json Failed urlpath="+urlpath+"\n"+e.toString());
            e.printStackTrace();
            return false;
        }

        return true;
    }


    static void Write_Git_JsonFile(File file, String strParam) {

        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(strParam);
                curBW.flush();
                curBW.close();
                // System.out.println("write out File OK ! File = " + file.getAbsolutePath());

            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            print("Write_Git_JsonFile Failed"+e.toString());
            e.printStackTrace();
        }
    }



static public String tscode_To_tscodeArea(String mTscode){

        if(mTscode == null ){
            return null;
        }

        if(!isNumeric(mTscode)){
            print("tscode_To_tscodeArea 返回空 mTscode="+ mTscode);
            return null;
        }

    String  shTscode = mTscode+".SH";
    if(mTscode.startsWith("6")){
        shTscode = mTscode+".SH";
    }else if(mTscode.startsWith("8")){
        shTscode = mTscode+".BJ";
    } else{
        shTscode = mTscode+".SZ";
    }

    return shTscode;
}




// 21 22年上证指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sh000001.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sh000001.js?visitDstTime=1

// 21 22年创业指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399006.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sz399006.js?visitDstTime=1

// 21 22年深证指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399001.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sz399001.js?visitDstTime=1

/*    daily_data_22="\n\
            220104 3649.15 3632.33 3651.89 3610.09 405027769\n\
            220105 3628.26 3595.18 3628.26 3583.47 423902028\n\
........
            220706 3391.03 3355.35 3391.03 3333.10 384630047\n\
            220707 3353.13 3364.40 3375.86 3332.31 334400202\n\
            【日期】【开盘价】【收盘价】【最高价】【最低价】【成交金额】
            (【今日收盘价】-【昨日收盘价】)/【昨日收盘价】= 当日涨幅
            ";*/


    public static String clearDigital(String digital){
        // ₀₁₂₃₄₅₆₇₈₉  // ⁰¹²³⁴⁵⁶⁷⁸⁹  // ⁰¹²³⁴⁵⁶⁷⁸⁹    // ⁴⁵⁶⁷⁸⁹
        String result = digital+""; // ⁰¹²³⁴⁵⁶⁷⁸⁹   // ₀₁₂₃₄₅₆₇₈₉  // ₀₁₂₃₄₅₆₇₈₉  // ⁰¹²³⁴⁵⁶⁷⁸⁹


        result =    result.replace("0","");
        result =    result.replace("1","");
        result =    result.replace("2","");
        result =    result.replace("3","");
        result =    result.replace("4","");
        result =    result.replace("5","");
        result =    result.replace("6","");
        result =    result.replace("7","");
        result =    result.replace("8","");
        result =    result.replace("9","");

        return result;

    }


    public static  void check_Zhishu(Context mContent){

        if((shzs_zhishu != null && !shzs_zhishu.isContainCurYearData()) || (shzs_zhishu != null && shzs_zhishu.year_zhushu_pct == 0)){
            requestNetwork_ZhiShu(mContent,shzs_zhishu);
        }

        if(szzs_zhishu != null && !szzs_zhishu.isContainCurYearData() || (szzs_zhishu != null && szzs_zhishu.year_zhushu_pct == 0)){
            requestNetwork_ZhiShu(mContent,szzs_zhishu);
        }

        if(cybzs_zhishu != null &&  !cybzs_zhishu.isContainCurYearData()   || (cybzs_zhishu != null && cybzs_zhishu.year_zhushu_pct == 0)){
            requestNetwork_ZhiShu(mContent,cybzs_zhishu);
        }

        if(kcbzs_zhishu != null &&  !kcbzs_zhishu.isContainCurYearData()   || (kcbzs_zhishu != null && kcbzs_zhishu.year_zhushu_pct == 0)){
            requestNetwork_ZhiShu(mContent,kcbzs_zhishu);
        }
    }
   public static  void init_ZhiShu(Context mContent){
 //  399006.SZ   创业板指数   399001.SZ 深证指数     000001.SH 上证指数   000688.SH  科创板指数

         ZhiShu_TscodeKey_Map = new HashMap<String,StockSimulate_ZhiShu>();  //
         shzs_zhishu = new StockSimulate_ZhiShu("000001.SH","上证指数",TimeUtil.getCurrentYYYY());
         szzs_zhishu = new StockSimulate_ZhiShu("399001.SZ","深证指数",TimeUtil.getCurrentYYYY());
         cybzs_zhishu = new StockSimulate_ZhiShu("399006.SZ","创业板指数",TimeUtil.getCurrentYYYY());
         kcbzs_zhishu = new StockSimulate_ZhiShu("000688.SH","科创板指数",TimeUtil.getCurrentYYYY());

        ZhiShu_TscodeKey_Map.put(shzs_zhishu.zhishu_tscode,shzs_zhishu);
        ZhiShu_TscodeKey_Map.put(szzs_zhishu.zhishu_tscode,szzs_zhishu);
        ZhiShu_TscodeKey_Map.put(cybzs_zhishu.zhishu_tscode,cybzs_zhishu);
        ZhiShu_TscodeKey_Map.put(kcbzs_zhishu.zhishu_tscode,kcbzs_zhishu);


        requestNetwork_ZhiShu(mContent,shzs_zhishu);
        requestNetwork_ZhiShu(mContent,szzs_zhishu);
        requestNetwork_ZhiShu(mContent,cybzs_zhishu);
        requestNetwork_ZhiShu(mContent,kcbzs_zhishu);





    }



    //    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399001.js?visitDstTime=1
    public static int calculYearFromUrl(String  urlItem  ) {

        int matchYear = 0 ;

        if(urlItem != null && urlItem.contains("daily/")){
            String beginYearStr = urlItem.substring(urlItem.indexOf("daily/")+"daily/".length());

            if(beginYearStr != null && beginYearStr.contains("/")){
                String year_twonum_str = beginYearStr.substring(0,beginYearStr.indexOf("/")).trim();

                if(isNumeric(year_twonum_str)){


                     matchYear  = Integer.parseInt(year_twonum_str);
                    if(matchYear < 100){
                        matchYear = (TimeUtil.getCurrentYYYY()/100) * 100 + matchYear;
                    }

                }

            }


        }




        return  matchYear;


    }



        public static void requestNetwork_ZhiShu(Context mContent ,StockSimulate_ZhiShu  mZhiShu  ){

        if (mSimulateRequestqueue == null){
            if(mContent != null){
                mSimulateRequestqueue = Volley.newRequestQueue(mContent);
            }else{
                mSimulateRequestqueue = Volley.newRequestQueue(ZMainApplication.getInstance().getBaseContext());
            }

        }

     ArrayList<String> requestUrlList =    mZhiShu.getZhiShuUrlList();

       //    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399001.js?visitDstTime=1   深
       //    https://data.gtimg.cn/flashdata/hushen/daily/22/sh000001.js?visitDstTime=1   上
       //    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399006.js?visitDstTime=1   创
       //    https://data.gtimg.cn/flashdata/hushen/daily/22/sh000688.js?visitDstTime=1   科

        for (int i = 0; i < requestUrlList.size(); i++) {
            String url = requestUrlList.get(i);

         final   int url_year =  calculYearFromUrl(url);

            print(mZhiShu.zhishu_tscode+" url["+i+"] = "+ url +"  执行请求 ");

            final int  callNetworkIndex = i ;

            //实例化一个 StringRequest作为网络请求
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            mZhiShu.mYear_ResponseMap.put(url_year,response);
                            if(mZhiShu.rawResponseText == null){
                                mZhiShu.rawResponseText = response;
                            }else{
                                mZhiShu.rawResponseText +=  response;
                            }

                            print(mZhiShu.zhishu_tscode+"  = "+ url +"  执行请求 结果: "+ response);
                            if(!mZhiShu.isContainCurYearData() || mZhiShu.year_zhushu_pct == 0){    // 不包含 今年的数据 那么 初始化
                                print(mZhiShu.zhishu_tscode+"_请求   接口 url= "+ url+"  成功!  执行 initWithResponseString 操作  response="+ response);
                                mZhiShu.initWithResponseString(response , (callNetworkIndex == requestUrlList.size() -1) );

                            }else{

                                print(mZhiShu.zhishu_tscode+"_请求_ 接口 url= "+ url+"  成功!"+ " 但 当前数据 已经 包含 " +getTimeStamp_YYYY_MM_DD()+" 年份数据! 取消过滤");
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                        LogUtil.e("请求数据失败");
                            print(mZhiShu.zhishu_tscode+"_请求_Failed   接口 url= "+ url+"  error="+ error);

                            if(!mZhiShu.isContainCurYearData() || mZhiShu.year_zhushu_pct == 0 ){  //  当前数据并不 包含  今年的数据 那么 清空
                                mZhiShu.allZhiShuItemList.clear();
                                mZhiShu.allZhiShuItemList = new ArrayList<StockSimulate_ZhiShu.StockSimulate_ZhiShu_Item>();

                            }
                  }
                    });

            mSimulateRequestqueue.add(stringRequest);
            mSimulateRequestqueue.start();


        }




    }


  public  static File calculateSelectedJsonFile(String jsonName){
        File matchFile  = null;

        for (int i = 0; i < day_timestamp_jsonFileArr.size(); i++) {
            File fileItem = day_timestamp_jsonFileArr.get(i);
            String fileName = fileItem.getName();
            if(fileName.equals(jsonName)){

                return fileItem;
            }

        }
        return matchFile;


    }



    //  在 /sdcard/zmain/J0_股票列表.xlsx 转为的 json 文件  转为 一个 Stock_NodeImpl
    public  static synchronized  Stock_NodeImpl generate_gupiaoliebiao_with_OrderIndex(Context context,int orderIndex) {

        try {

            String json_result = StockHolder.calGuPiaoLieBiaoJson_With_Order(orderIndex);

//            String json_result = DataHolder.getZmainMp3Json();
            Stock_NodeImpl tree = new Gson().fromJson(json_result, Stock_NodeImpl.class);
//            android.util.Log.i("json_result","ZZZZZZZZZZZZZZZZZZZZZ");
//            android.util.Log.i("json_result",json_result);
//            LogPrint(json_result,"json_result");
//            android.util.Log.i("json_result","ZZZZZZZZZZZZZZZZZZZZZ");
            if (!isFirstInitDailyJson_FinishFlag) {
                isFirstInitDailyJson_FinishFlag = true;


                return tree;
            } else {
                //noinspection ConstantConditions
                android.util.Log.i("xxzukgit","FF_1 ");
//                tree.children().remove(0);
                return tree;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Stock_NodeImpl.EMPTY;
    }


    //  在 /sdcard/zmain/J0_股票列表.xlsx 转为的 json 文件  转为 一个 Stock_NodeImpl
    public  static synchronized  Stock_NodeImpl generate_gupiaoliebiao_withZMainStockDir(Context context) {

        try {

            String json_result = StockHolder.calGuPiaoLieBiaoJson();

//            String json_result = DataHolder.getZmainMp3Json();
            Stock_NodeImpl tree = new Gson().fromJson(json_result, Stock_NodeImpl.class);
//            android.util.Log.i("json_result","ZZZZZZZZZZZZZZZZZZZZZ");
//            android.util.Log.i("json_result",json_result);
//            LogPrint(json_result,"json_result");
//            android.util.Log.i("json_result","ZZZZZZZZZZZZZZZZZZZZZ");
            if (!isFirstInitDailyJson_FinishFlag) {
                isFirstInitDailyJson_FinishFlag = true;


                return tree;
            } else {
                //noinspection ConstantConditions
                android.util.Log.i("xxzukgit","FF_1 ");
//                tree.children().remove(0);
                return tree;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Stock_NodeImpl.EMPTY;
    }


/*    public static Stock_NodeImpl generate_withJsonFile(Context context) {
        android.util.Log.i("xxzukgit","EE_1 ");
        try {
            InputStream is = context.getAssets().open("tree.json");
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            Stock_NodeImpl tree = new Gson().fromJson(result.toString(), Stock_NodeImpl.class);
            if (isFirst) {
                isFirst = false;
                return tree;
            } else {
                //noinspection ConstantConditions
                android.util.Log.i("xxzukgit","FF_1 ");
//                tree.children().remove(0);
                return tree;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Stock_NodeImpl.EMPTY;
    }*/


    //
    public static boolean  isWeekEnd(int dayFlagInt ){
        boolean isWeekEndDay = false;
        String YearStr = (""+dayFlagInt).substring(0,4);
        String MonthStr = (""+dayFlagInt).substring(4,6);
        String DayStr = (""+dayFlagInt).substring(6);
        int xyear = Integer.parseInt(YearStr);
        int xmonth = Integer.parseInt(MonthStr);
        int xday =  Integer.parseInt(DayStr);
        String xinqi = StockHolder.calculXinQi2Chinese(xyear,xmonth,xday);
        if("7".equals(xinqi) || "6".equals(xinqi) ){
             isWeekEndDay = true;

        }


        return isWeekEndDay;
    }
   public static String calculXinQi2Chinese(int year , int month ,int day) {
        String xinqiValue = "";
        Calendar calendar_object = Calendar.getInstance();
        calendar_object.set(Calendar.YEAR,year);
        calendar_object.set(Calendar.MONTH,month-1);
        calendar_object.set(Calendar.DAY_OF_MONTH,day);
        int xinqi = calendar_object.get(Calendar.DAY_OF_WEEK) - 1;  //
        System.out.println(" Bxiniq = "+ xinqi);
        // 0 ---> 周一
        // 1 ---> 周二
        // 2---> 周三
        //
        switch (xinqi) {
            case 0:
                xinqiValue = "7";
                break;
            case 1:
                xinqiValue = "1";
                break;
            case 2:
                xinqiValue = "2";
                break;
            case 3:
                xinqiValue = "3";
                break;
            case 4:
                xinqiValue = "4";
                break;
            case 5:
                xinqiValue = "5";
                break;
            case 6:
                xinqiValue = "6";
                break;
            case 7:
                xinqiValue = "7";
                break;
            default:
                xinqiValue = "1";  // 默认周一
        }
        return xinqiValue;
    }
}
