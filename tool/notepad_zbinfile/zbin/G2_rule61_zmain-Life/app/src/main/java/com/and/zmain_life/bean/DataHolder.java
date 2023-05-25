package com.and.zmain_life.bean;

import android.content.Context;
import android.icu.text.Collator;
import android.media.ExifInterface;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.alibaba.fastjson.JSONObject;
import com.and.zmain_life.R;
import com.and.zmain_life.node.NodeImpl;
import com.and.zmain_life.utils.RandomUtil;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import com.sun.mail.util.MailSSLSocketFactory;

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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

// -------------- POI --------------


public  class DataHolder {
    public static int System_SDK_Version = 24;

    public static boolean System_IS_LAND = false;  // true---横屏    false-竖屏
    public static boolean System_IS_PAD = false;  // true---PAD    false-手机
    public static boolean isScreenOff = false; // 是否灭屏
    public static boolean isAPPInBackground = false;   //  false---APP在前台   true---APP在后台

    public static boolean isPlayMusicWhenBackground = false; //  true---后台播放     false---后台不播放

    // **********************  VideoLayoutActivity Begin  ********************

    public static   int row_count_videolayout ;
    public static  int column_count_videolayout ;
    public static   boolean is_all_same_videolayout ;
    public static  int srcFragmentIndex_videolayout;  //  从 哪个 Fragment 传过来的数据的来源
    public static   File[] allMatchFileArr_videolayout;
    public static  String same_url_videolayout ;   // 对于相同的 界面时 设置的 url
    // **********************  VideoLayoutActivity End ********************
    public static File TF_File;
    public static File Sdcard_File;
    public static File ZMain_File;
    public static File Emulated_0_ZMain_File;
    public static String mEmulatedSdcardPath;
    public static String Outtable_TF_File_Path;

    public static int mTitle_Long_Click_Num = 24;   //标题长按的次数  次数%3==0 正常显示  次数%3==1 显示位置 次数%3==2 显示作者
    public static boolean isShowLevel3Node_Position = false;
    public static boolean isShowLevel3Node_Artist = false;

    // **********************  WebView_ZGit
    public static boolean isZGitPage_Already_Loaded = false;
    //    public static Bundle zGitBundle ;
    // **********************  WebView_ZTime
    public static boolean isZTimePage_Already_Loaded = false;
    //    public static Bundle ztimeBundle ;

    // **********************  WebView_ZTime
    //    public static Bundle ztimeBundle ;
    public static boolean isMicroMathPage_Already_Loaded = false;






    // __________________  Kaoyan_Land_Img
    public static int Kaoyan_Land_Img_initPos = 0;
    public static int Kaoyan_Land_Img_count;   // 图片数量
    public static ArrayList<Integer> Kaoyan_Land_Img_random_indexlist;   // 图片索引值_随机
    public static File Kaoyan_Land_Img_list_File;
    public static File[] Kaoyan_Land_Img_file_list;



    public static String Kaoyan_Land_Img_MapKey  = "all";
    public static ArrayList<String> Kaoyan_Land_Img_KeyNameList;   // all  gbxxx   文件夹名称
    public static HashMap<String,String> Kaoyan_Land_Img_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>    <文件夹名称,文件夹名称_7>

    public static HashMap<String,ArrayList<File>> Kaoyan_Land_Img_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合   // <子文件夹名称,ArrayList<FIle>> 子文件夹对应的 图片的集合

    public static String Kaoyan_Land_RootDir_Key  = "root";
    public static HashMap<String,ArrayList<File>> Kaoyan_Land_Img_SubDir_FileArr_Map;  // 子目录文件名  子目录对应的文件夹列表
    public static ArrayList<String> Kaoyan_Land_SubDir_NameList;  // 子目录名称集合  all   考研數學二 spinner的 Key的数据源
    public static HashMap<String,ArrayList<String>> Kaoyan_Land_DirName_RadioTextList_Map;  // root这个spinner 显示的 radioBtn的字符擦混的集合



    // all--Kaoyan_Land_Img_Category_FileArr_Map        數學2考研---<文件名1第一題,ArrayList<File>只包含這一道題>
    public static HashMap<String,HashMap<String,ArrayList<File>>> Kaoyan_Land_DirName_2_Category_FileArr_Map;  //  spinner的 Value的数据源

    public static void refresh_Kaoyan_Land_Img_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Kaoyan_Land_Img_count =  fileArr.size();
            Kaoyan_Land_Img_initPos = 0 ;
            Kaoyan_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(Kaoyan_Land_Img_count);
            Kaoyan_Land_Img_file_list = new File[Kaoyan_Land_Img_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Kaoyan_Land_Img_file_list[i] = fileArr.get(i);
            }
        }
    }



    // **********************  Video_Home_Port
    public static int Video_Home_Port_initPos = 0;
    public static int Video_Home_Port_count;   // 视频数量
    public static ArrayList<Integer> Video_Home_Port_random_indexlist;   // 视频索引值_随机
    public static File Video_Home_Port_list_File;
    public static File[] Video_Home_Port_file_list;


    public static String Video_Home_Port_MapKey  = "all";
    public static ArrayList<String> Video_Home_Port_KeyNameList;   //
    public static HashMap<String,String> Video_Home_Port_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Home_Port_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Home_Port_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Home_Port_count =  fileArr.size();
            Video_Home_Port_initPos = 0 ;
            Video_Home_Port_random_indexlist = RandomUtil.getRandomLengthArr(Video_Home_Port_count);
            Video_Home_Port_file_list = new File[Video_Home_Port_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Home_Port_file_list[i] = fileArr.get(i);
            }
        }
    }

    // **********************  Video_Home_Land
    public static int Video_Home_Land_initPos = 0;
    public static int Video_Home_Land_count;   // 视频数量
    public static ArrayList<Integer> Video_Home_Land_random_indexlist;   // 视频索引值_随机
    public static File Video_Home_Land_list_File;
    public static File[] Video_Home_Land_file_list;


    public static String Video_Home_Land_MapKey  = "all";
    public static ArrayList<String> Video_Home_Land_KeyNameList;   //
    public static HashMap<String,String> Video_Home_Land_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Home_Land_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Home_Land_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Home_Land_count =  fileArr.size();
            Video_Home_Land_initPos = 0 ;
            Video_Home_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Home_Land_count);
            Video_Home_Land_file_list = new File[Video_Home_Land_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Home_Land_file_list[i] = fileArr.get(i);
            }
        }
    }


    // __________________  Gaokao_Land_Img    mobile 壁纸
    public static int Gaokao_Land_Img_initPos = 0;
    public static int Gaokao_Land_Img_count;   // 图片数量
    public static ArrayList<Integer> Gaokao_Land_Img_random_indexlist;   // 图片索引值_随机
    public static File Gaokao_Land_Img_list_File;
    public static File[] Gaokao_Land_Img_file_list;


    public static String Gaokao_Land_Img_MapKey  = "all";
    public static ArrayList<String> Gaokao_Land_Img_KeyNameList;   // all  gbxxx   文件夹名称
    public static HashMap<String,String> Gaokao_Land_Img_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>    <文件夹名称,文件夹名称_7>

    public static HashMap<String,ArrayList<File>> Gaokao_Land_Img_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合   // <子文件夹名称,ArrayList<FIle>> 子文件夹对应的 图片的集合

    public static String Gaokao_Land_RootDir_Key  = "root";
    public static HashMap<String,ArrayList<File>> Gaokao_Land_Img_SubDir_FileArr_Map;  // 子目录文件名  子目录对应的文件夹列表
    public static ArrayList<String> Gaokao_Land_SubDir_NameList;  // 子目录名称集合  all   考研數學二 spinner的 Key的数据源
    public static HashMap<String,ArrayList<String>> Gaokao_Land_DirName_RadioTextList_Map;  // root这个spinner 显示的 radioBtn的字符擦混的集合



    // all--Gaokao_Land_Img_Category_FileArr_Map        數學2高考---<文件名1第一題,ArrayList<File>只包含這一道題>
    public static HashMap<String,HashMap<String,ArrayList<File>>> Gaokao_Land_DirName_2_Category_FileArr_Map;  //  spinner的 Value的数据源


    public static void refresh_Gaokao_Land_Img_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Gaokao_Land_Img_count =  fileArr.size();
            Gaokao_Land_Img_initPos = 0 ;
            Gaokao_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(Gaokao_Land_Img_count);
            Gaokao_Land_Img_file_list = new File[Gaokao_Land_Img_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Gaokao_Land_Img_file_list[i] = fileArr.get(i);
            }
        }
    }



    // **********************  Video_Kaoyan_Land
    public static int Video_Kaoyan_Land_initPos = 0;
    public static int Video_Kaoyan_Land_count;   // 视频数量
    public static ArrayList<Integer> Video_Kaoyan_Land_random_indexlist;   // 视频索引值_随机
    public static File Video_Kaoyan_Land_list_File;
    public static File[] Video_Kaoyan_Land_file_list;


    public static String Video_Kaoyan_Land_MapKey  = "all";
    public static ArrayList<String> Video_Kaoyan_Land_KeyNameList;   //
    public static HashMap<String,String> Video_Kaoyan_Land_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Kaoyan_Land_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Kaoyan_Land_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Kaoyan_Land_count =  fileArr.size();
            Video_Kaoyan_Land_initPos = 0 ;
            Video_Kaoyan_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Kaoyan_Land_count);
            Video_Kaoyan_Land_file_list = new File[Video_Kaoyan_Land_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Kaoyan_Land_file_list[i] = fileArr.get(i);
            }
        }
    }

    // **********************  Video_Gaokao_Land
    public static int Video_Gaokao_Land_initPos = 0;
    public static int Video_Gaokao_Land_count;   // 视频数量
    public static ArrayList<Integer> Video_Gaokao_Land_random_indexlist;   // 视频索引值_随机
    public static File Video_Gaokao_Land_list_File;
    public static File[] Video_Gaokao_Land_file_list;


    public static String Video_Gaokao_Land_MapKey  = "all";
    public static ArrayList<String> Video_Gaokao_Land_KeyNameList;   //
    public static HashMap<String,String> Video_Gaokao_Land_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Gaokao_Land_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Gaokao_Land_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Gaokao_Land_count =  fileArr.size();
            Video_Gaokao_Land_initPos = 0 ;
            Video_Gaokao_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Gaokao_Land_count);
            Video_Gaokao_Land_file_list = new File[Video_Gaokao_Land_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Gaokao_Land_file_list[i] = fileArr.get(i);
            }
        }
    }


    // **********************  Video_Music_Port
    public static int Video_Music_Port_initPos = 0;
    public static int Video_Music_Port_count;   // 视频数量
    public static ArrayList<Integer> Video_Music_Port_random_indexlist;   // 视频索引值_随机
    public static File Video_Music_Port_list_File;
    public static File[] Video_Music_Port_file_list;


    public static String Video_Music_Port_MapKey  = "all";
    public static ArrayList<String> Video_Music_Port_KeyNameList;   //
    public static HashMap<String,String> Video_Music_Port_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Music_Port_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Music_Port_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Music_Port_count =  fileArr.size();
            Video_Music_Port_initPos = 0 ;
            Video_Music_Port_random_indexlist = RandomUtil.getRandomLengthArr(Video_Music_Port_count);
            Video_Music_Port_file_list = new File[Video_Music_Port_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Music_Port_file_list[i] = fileArr.get(i);
            }
        }
    }

    // **********************  Video_Music_Land
    public static int Video_Music_Land_initPos = 0;
    public static int Video_Music_Land_count;   // 视频数量
    public static ArrayList<Integer> Video_Music_Land_random_indexlist;   // 视频索引值_随机
    public static File Video_Music_Land_list_File;
    public static File[] Video_Music_Land_file_list;


    public static String Video_Music_Land_MapKey  = "all";
    public static ArrayList<String> Video_Music_Land_KeyNameList;   //
    public static HashMap<String,String> Video_Music_Land_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Music_Land_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Music_Land_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Music_Land_count =  fileArr.size();
            Video_Music_Land_initPos = 0 ;
            Video_Music_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Music_Land_count);
            Video_Music_Land_file_list = new File[Video_Music_Land_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Music_Land_file_list[i] = fileArr.get(i);
            }
        }
    }



    // **********************  Video_Scene_Port
    public static int Video_Scene_Port_initPos = 0;
    public static int Video_Scene_Port_count;   // 视频数量
    public static ArrayList<Integer> Video_Scene_Port_random_indexlist;   // 视频索引值_随机
    public static File Video_Scene_Port_list_File;
    public static File[] Video_Scene_Port_file_list;


    public static String Video_Scene_Port_MapKey  = "all";
    public static ArrayList<String> Video_Scene_Port_KeyNameList;   //
    public static HashMap<String,String> Video_Scene_Port_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Scene_Port_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Scene_Port_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Scene_Port_count =  fileArr.size();
            Video_Scene_Port_initPos = 0 ;
            Video_Scene_Port_random_indexlist = RandomUtil.getRandomLengthArr(Video_Scene_Port_count);
            Video_Scene_Port_file_list = new File[Video_Scene_Port_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Scene_Port_file_list[i] = fileArr.get(i);
            }
        }
    }



    // **********************  Video_Scene_Land
    public static int Video_Scene_Land_initPos = 0;
    public static int Video_Scene_Land_count;   // 视频数量
    public static ArrayList<Integer> Video_Scene_Land_random_indexlist;   // 视频索引值_随机
    public static File Video_Scene_Land_list_File;
    public static File[] Video_Scene_Land_file_list;


    public static String Video_Scene_Land_MapKey  = "all";
    public static ArrayList<String> Video_Scene_Land_KeyNameList;   //
    public static HashMap<String,String> Video_Scene_Land_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Scene_Land_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Scene_Land_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Scene_Land_count =  fileArr.size();
            Video_Scene_Land_initPos = 0 ;
            Video_Scene_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Scene_Land_count);
            Video_Scene_Land_file_list = new File[Video_Scene_Land_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Scene_Land_file_list[i] = fileArr.get(i);
            }
        }
    }


    // **********************  Video_Zhexue_Land
    public static int Video_Zhexue_Land_initPos = 0;
    public static int Video_Zhexue_Land_count;   // 视频数量
    public static ArrayList<Integer> Video_Zhexue_Land_random_indexlist;   // 视频索引值_随机
    public static File Video_Zhexue_Land_list_File;
    public static File[] Video_Zhexue_Land_file_list;


    public static String Video_Zhexue_Land_MapKey  = "all";
    public static ArrayList<String> Video_Zhexue_Land_KeyNameList;   //
    public static HashMap<String,String> Video_Zhexue_Land_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Zhexue_Land_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Zhexue_Land_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Zhexue_Land_count =  fileArr.size();
            Video_Zhexue_Land_initPos = 0 ;
            Video_Zhexue_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Zhexue_Land_count);
            Video_Zhexue_Land_file_list = new File[Video_Zhexue_Land_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Zhexue_Land_file_list[i] = fileArr.get(i);
            }
        }
    }




    // **********************  Video_English_Land
    public static int Video_English_Land_initPos = 0;
    public static int Video_English_Land_count;   // 视频数量
    public static ArrayList<Integer> Video_English_Land_random_indexlist;   // 视频索引值_随机
    public static File Video_English_Land_list_File;
    public static File[] Video_English_Land_file_list;


    public static String Video_English_Land_MapKey  = "all";
    public static ArrayList<String> Video_English_Land_KeyNameList;   //
    public static HashMap<String,String> Video_English_Land_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_English_Land_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_English_Land_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_English_Land_count =  fileArr.size();
            Video_English_Land_initPos = 0 ;
            Video_English_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_English_Land_count);
            Video_English_Land_file_list = new File[Video_English_Land_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_English_Land_file_list[i] = fileArr.get(i);
            }
        }
    }



    // **********************  Video_Common_Port
    public static int Video_Common_Port_initPos = 0;
    public static int Video_Common_Port_count;   // 视频数量
    public static ArrayList<Integer> Video_Common_Port_random_indexlist;   // 视频索引值_随机
    public static File Video_Common_Port_list_File;
    public static File[] Video_Common_Port_file_list;


    public static String Video_Common_Port_MapKey  = "all";
    public static ArrayList<String> Video_Common_Port_KeyNameList;   //
    public static HashMap<String,String> Video_Common_Port_KeyName_KeyNameSize_Map; //
    public static HashMap<String,ArrayList<File>> Video_Common_Port_Category_FileArr_Map;  //


    public static void refresh_Video_Common_Port_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Common_Port_count =  fileArr.size();
            Video_Common_Port_initPos = 0 ;
            Video_Common_Port_random_indexlist = RandomUtil.getRandomLengthArr(Video_Common_Port_count);
            Video_Common_Port_file_list = new File[Video_Common_Port_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Common_Port_file_list[i] = fileArr.get(i);
            }
        }
    }

    // **********************  Video_Common_Land
    public static int Video_Common_Land_initPos = 0;
    public static int Video_Common_Land_count;   // 视频数量
    public static ArrayList<Integer> Video_Common_Land_random_indexlist;   // 视频索引值_随机
    public static File Video_Common_Land_list_File;
    public static File[] Video_Common_Land_file_list;


    public static String Video_Common_Land_MapKey  = "all";
    public static ArrayList<String> Video_Common_Land_KeyNameList;   //
    public static HashMap<String,String> Video_Common_Land_KeyName_KeyNameSize_Map; // <三角函数,三角函数_7>
    public static HashMap<String,ArrayList<File>> Video_Common_Land_Category_FileArr_Map;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


    public static void refresh_Video_Common_Land_List(ArrayList<File> fileArr){
        if(fileArr != null && fileArr.size() > 0 ){
            Video_Common_Land_count =  fileArr.size();
            Video_Common_Land_initPos = 0 ;
            Video_Common_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Common_Land_count);
            Video_Common_Land_file_list = new File[Video_Common_Land_count];
            for (int i = 0; i <fileArr.size() ; i++) {
                Video_Common_Land_file_list[i] = fileArr.get(i);
            }
        }
    }


    // *************************** Need Remove  Begin ***************************
    // **********************************  GIF   去掉 失效
// __________________  Top_Land_GIF
    public static int Top_Land_Gif_initPos = 0;
    public static int Top_Land_Gif_count;   // 视频数量
    public static ArrayList<Integer> Top_Land_Gif_random_indexlist;   // 视频索引值_随机
    public static File Top_Land_Gif_list_File;
    public static File[] Top_Land_Gif_file_list;

    // __________________  Top_Port_GIF  去掉  失效
    public static int Top_Port_Gif_initPos = 0;
    public static int Top_Port_Gif_count;   // 视频数量
    public static ArrayList<Integer> Top_Port_Gif_random_indexlist;   // gif索引值_随机
    public static File Top_Port_Gif_list_File;
    public static File[] Top_Port_Gif_file_list;
    // *************************** Need Remove  End ***************************
// *************************** Need Add  Begin ***************************

    // __________________  Common_MP3
    public static int MP3_initPos = 0;
    public static int MP3_count;   // MP3数量
    public static String long_click_loop_level2_ID_Name;


    /*    public static int MP3_initPos  = 0 ;
    public static int MP3_count;   // MP3数量
    public static ArrayList<Integer> MP3_random_indexlist;   // MP3索引值_随机
    public static   File MP3_list_File;
    public static  File[] MP3_file_list;*/
    public static ArrayList<File> allMp3FileList;
    public static String mp3Json;  // 放置 MP3_JSON 字符串的 变量
    public static File  mp3Json_File;  // 放置 MP3_JSON 字符串的 变量 的文件   /sdcard/zmain/mp3/mp3_node.json
    public volatile static int Node_ID_Num = 0;
    static final transient NodeImpl RootNodeImpl = new NodeImpl(0, -1, "全部", 0, 0, null);


    // 国语 粤语 纯语  英语 喜欢 这类 归一的名称  调用了
    // initSongWithCommentForMap("纯语", CommentName_tag, titleName_tag, File_realMP3, mArtist_NodeImpl_Map, mComment_File_Map);
    // 这个函数的 的 第一个参数的 集合 以及 A_All Z合格集合
    public static ArrayList<String> MP3_Node2_CatageryNameList;   // MP3索引值_随机

    public static ArrayList<String> MP3_Node2_UserDefine_NameList ;   //  用户自定义的level2 名称


    public static ArrayList<Integer> MP3_random_indexlist;   // MP3索引值_随机
    public static File MP3_list_File;  //   /sdcard/zmain/mp3/
    public static File[] MP3_file_list;
    public static ArrayList<NodeImpl> level_1_alphabet_nodeList;    // 字母表列表 level_1
    public static ArrayList<NodeImpl> level_2_artistism_nodeList;   //  作者列表_ level_2
    public static ArrayList<NodeImpl> level_3_song_nodeList;       // 歌曲列表  level_3


    public static ArrayList<NodeImpl> level_3_song_nodeList_quchong;       // 歌曲列表  level_3 A_ALL中的项
    // 粤语----粤语列表   国语---国语列表   纯语---纯语列表    英语--英语列表  喜欢--喜欢列表
    public static LinkedHashMap<String, ArrayList<NodeImpl>> mComment_File_Map;
    public static   LinkedHashMap<String, ArrayList<NodeImpl>> mArtist_NodeImpl_Map ; // 具体的明星的列表  直接读取json 就不包含 粤语 自定义分类 动态生成那么就会包含粤语 自定义分类

    public static   LinkedHashMap<String, ArrayList<NodeImpl>> mUserDefine_Category_Level2_NodeImpl_Map ; // 包含A_ALL  包含 粤语   不包含歌手



    public static int  MP3_Layout_color_Random =  R.color.rainbow_blue;
    public  static int currentMP3_Track_Position_ChangePage = 0;
    public  static boolean isMP3Page_ChangeTo_AnotherPage = false ;
    public  static boolean isMP3Page_TitleMusic_Play_whiteTrue_blackFalse = false ;  // MP3的 标题 黑色 表示 停止 播放   白色 表示 要播放
    /*
    public static     ArrayList<NodeImpl> yue_yu_list;  // 粤语文件列表  level_3
    public static   ArrayList<NodeImpl> chunyu_list;  // 纯语文件列表  level_3
    public static    ArrayList<NodeImpl> guo_yu_list;  // 国语文件列表  level_3
    public static    ArrayList<NodeImpl> eng_yu_list;  // 英语文件列表  level_3
    public static    ArrayList<NodeImpl> xihuan_list;  // 喜欢歌曲文件列表  level_3
*/

// *************************** Need Add  End ***************************

    // __________________  Common_GIF  保留
    public static int Gif_initPos = 0;
    public static int Gif_count;   // gif数量
    public static ArrayList<Integer> Gif_random_indexlist;   // gif索引值_随机
    public static File Gif_list_File;
    public static File[] Gif_file_list;


    // **********************  IMG
// __________________  Wallpapger_Land_Img  PC壁纸
    public static int Wall_Land_Img_initPos = 0;
    public static int Wall_Land_Img_count;   // 图片数量
    public static ArrayList<Integer> Wall_Land_Img_random_indexlist;   // 图片索引值_随机
    public static File Wall_Land_Img_list_File;
    public static File[] Wall_Land_Img_file_list;

    // __________________  Wall_Port_Img    mobile 壁纸
    public static int Wall_Port_Img_initPos = 0;
    public static int Wall_Port_Img_count;   // 图片数量
    public static ArrayList<Integer> Wall_Port_Img_random_indexlist;   // 图片索引值_随机
    public static File Wall_Port_Img_list_File;
    public static File[] Wall_Port_Img_file_list;




// *************************** Need Add  Begin ***************************

    // __________________  Home_Land_Img  land 家图片
    public static int Home_Land_Img_initPos = 0;
    public static int Home_Land_Img_count;   // 图片数量
    public static ArrayList<Integer> Home_Land_Img_random_indexlist;   // 图片索引值_随机
    public static File Home_Land_Img_list_File;
    public static File[] Home_Land_Img_file_list;

    // __________________  Home_Port_Img    mobile 壁纸  Port 家图片
    public static int Home_Port_Img_initPos = 0;
    public static int Home_Port_Img_count;   // 图片数量
    public static ArrayList<Integer> Home_Port_Img_random_indexlist;   // 图片索引值_随机
    public static File Home_Port_Img_list_File;
    public static File[] Home_Port_Img_file_list;
// *************************** Need Add  End ***************************


    // __________________  Common_Img_Port___Begin
    public static int Common_Img_Port_initPos = 0;   // jpg文件夹下图片
    public static int Common_Img_Port_count;   // 视频数量
    public static ArrayList<Integer> Common_Img_Port_random_indexlist;   // 视频索引值_随机
    public static File Common_Img_Port_list_File;
    public static File[] Common_Img_Port_file_list;


    // __________________  Common_Img_Land___Begin
    public static int Common_Img_Land_initPos = 0;   // jpg文件夹下图片
    public static int Common_Img_Land_count;   // 视频数量
    public static ArrayList<Integer> Common_Img_Land_random_indexlist;   // 视频索引值_随机
    public static File Common_Img_Land_list_File;
    public static File[] Common_Img_Land_file_list;


    // __________________  Txt_Edit 单个文件的操作
    public static   File  sdcard_1_dir;   //   /sdcard/1/  这个目录

    //  Mi-Pad5 只让读取 .jpg 类型的文件   那么  就把当前文件当做 jpg   txt 转 jpg
    public static  File  sdcard_1_txt_file;   // /sdcard/1/1.txt 这个文件
    public static  ArrayList<String> sdcard_1_txt_contentList = new  ArrayList<String> ();  // // /sdcard/1/1.txt 这个文件的内容


    public static  File  sdcard_main_loan_file;   // /sdcard/zmain/loan.txt  这个文件

    public static  ArrayList<String> sdcard_zmain_loan_contentList = new  ArrayList<String> ();  // // /sdcard/zmain/loan.txt 这个文件的内容




    public static void init_Sdcard_Main_Loan_Txt() {
        File zmain_dir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + File.separator + "zmain");
        sdcard_main_loan_file = new File(zmain_dir.getAbsoluteFile() + File.separator + "loan.txt");

        if (!zmain_dir.exists()) {
            zmain_dir.mkdirs();
            android.util.Log.i("zukgit", "A zmain_dir= " + zmain_dir.exists()+"  PTH="+ zmain_dir.getAbsolutePath());
        }


        if (!sdcard_main_loan_file.exists()) {
            try {
                android.util.Log.i("zukgit", "C sdcard_zmain_loan_txt_file= " + sdcard_main_loan_file.exists() + " Path="+ sdcard_main_loan_file.getAbsolutePath());

                sdcard_main_loan_file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        sdcard_zmain_loan_contentList = ReadFileContentAsList(sdcard_main_loan_file);
        if(sdcard_zmain_loan_contentList == null){
            print("sdcard_zmain_loan_contentList  == null  sdcard_main_loan_file="+sdcard_main_loan_file.getAbsolutePath());

        }else  if(sdcard_zmain_loan_contentList.size() == 0){
            print("sdcard_zmain_loan_contentList.size()  == 0  sdcard_main_loan_file="+sdcard_main_loan_file.getAbsolutePath());
        }


    }
    public static void init_Sdcard_Txt() {


        Sdcard_File = initSD_Root_File();
//        sdcard_1_dir = new File(Sdcard_File.getAbsoluteFile() + File.separator + "1");
//        sdcard_1_txt_file = new File(sdcard_1_dir.getAbsoluteFile() + File.separator + "1.txt");

       // 一定是 本身 内存的 文件 不可以是 外部 TF 卡上的 1.txt 文件
        sdcard_1_dir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + File.separator + "1");
        sdcard_1_txt_file = new File(sdcard_1_dir.getAbsoluteFile() + File.separator + "1.txt");




        //

        //
        if (!sdcard_1_dir.exists()) {
            sdcard_1_dir.mkdirs();
            android.util.Log.i("zukgit", "A sdcard_1_dir= " + sdcard_1_dir.exists()+"  PTH="+ sdcard_1_dir.getAbsolutePath());

        }
        android.util.Log.i("zukgit", "B sdcard_1_dir= " + sdcard_1_dir.exists()+"  PTH="+ sdcard_1_dir.getAbsolutePath());


        if (!sdcard_1_txt_file.exists()) {
            try {
                android.util.Log.i("zukgit", "C sdcard_1_txt_file= " + sdcard_1_txt_file.exists() + " Path="+ sdcard_1_txt_file.getAbsolutePath());

                sdcard_1_txt_file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        android.util.Log.i("zukgit", "D sdcard_1_txt_file= " + sdcard_1_txt_file.exists()+"  PTH="+ sdcard_1_txt_file.getAbsolutePath());


        sdcard_1_txt_contentList = ReadFileContentAsList(sdcard_1_txt_file);
        if(sdcard_1_txt_contentList == null){
            print("sdcard_1_txt_contentList  == null ");

        }else  if(sdcard_1_txt_contentList.size() == 0){
            print("sdcard_1_txt_contentList.size()  == 0 ");
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



    // __________________  Common_Img___End
    static String  TAG = "zukgit_DataHolder";

    static void print(String logStr){
        android.util.Log.i(TAG,logStr);


    }




    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
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

    /**
     * 根据类型后缀名简单判断是否Excel文件
     * @param file 文件
     * @return 是否Excel文件
     */

    public static boolean checkIfExcelFile(File file){
        if(file == null) {
            return false;
        }
        String name = file.getName();
        //”.“ 需要转义字符
        String[] list = name.split("\\.");
        //划分后的小于2个元素说明不可获取类型名
        if(list.length < 2) {
            return false;
        }
        String  typeName = list[list.length - 1];
        //满足xls或者xlsx才可以
        return "xls".equals(typeName) || "xlsx".equals(typeName);
    }


    public static ArrayList<Integer> setCommon_Img_Port_Count_BackIndexList(int mImg_count) {
        Common_Img_Port_count = mImg_count;
        Common_Img_Port_random_indexlist = RandomUtil.getRandomLengthArr(mImg_count);
        return Common_Img_Port_random_indexlist;
    }


    public static ArrayList<Integer> setCommon_Img_Land_Count_BackIndexList(int mImg_count) {
        Common_Img_Land_count = mImg_count;
        Common_Img_Land_random_indexlist = RandomUtil.getRandomLengthArr(mImg_count);
        return Common_Img_Land_random_indexlist;
    }




    public static ArrayList<Integer> Home_Land_Img_Init_BackIndexList(int mGif_count) {
        Home_Land_Img_count = mGif_count;
        Home_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Home_Land_Img_random_indexlist;
    }


    public static ArrayList<Integer> Wall_Land_Img_Init_BackIndexList(int mGif_count) {
        Wall_Land_Img_count = mGif_count;
        Wall_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Wall_Land_Img_random_indexlist;
    }


    public static ArrayList<Integer> Wall_Port_Gif_Init_BackIndexList(int mGif_count) {
        Top_Port_Gif_count = mGif_count;
        Top_Port_Gif_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Top_Port_Gif_random_indexlist;
    }

    public static ArrayList<Integer> Top_Land_Gif_Init_BackIndexList(int mGif_count) {
        Top_Land_Gif_count = mGif_count;
        Top_Land_Gif_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Top_Land_Gif_random_indexlist;
    }


    public static ArrayList<Integer> MP3_Init_BackIndexList(int mMp3_count) {
        MP3_count = mMp3_count;
        MP3_random_indexlist = RandomUtil.getRandomLengthArr(mMp3_count);
        return MP3_random_indexlist;
    }


    public static ArrayList<Integer> Gif_Init_BackIndexList(int mGif_count) {
        Gif_count = mGif_count;
        Gif_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Gif_random_indexlist;
    }


    public static ArrayList<Integer> setVideo_Scnece_Port_Count_BackIndexList(int mVideo_count) {
        Video_Scene_Port_count = mVideo_count;
        Video_Scene_Port_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        return Video_Scene_Port_random_indexlist;
    }


    public static ArrayList<Integer> setVideo_Scnece_Land_Count_BackIndexList(int mVideo_count) {
        Video_Scene_Land_count = mVideo_count;
        Video_Scene_Land_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        return Video_Scene_Land_random_indexlist;
    }


    public static ArrayList<Integer> setVideo_Music_Land_Count_BackIndexList(int mVideo_count) {
        Video_Music_Land_count = mVideo_count;
        Video_Music_Land_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        return Video_Music_Land_random_indexlist;
    }

    public static ArrayList<Integer> setVideo_Music_Port_Count_BackIndexList(int mVideo_count) {
        Video_Music_Port_count = mVideo_count;
        Video_Music_Port_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        return Video_Music_Port_random_indexlist;
    }




    public static ArrayList<Integer> setVideo_Common_Port_Count_BackIndexList(int mVideo_count) {
        Video_Common_Port_count = mVideo_count;
        Video_Common_Port_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        return Video_Common_Port_random_indexlist;
    }

    public static ArrayList<Integer> setVideo_Common_Land_Count_BackIndexList(int mVideo_count) {
        Video_Common_Land_count = mVideo_count;
        Video_Common_Land_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        return Video_Common_Land_random_indexlist;
    }


    public static ArrayList<Integer> setVideo_Home_Port_Count_BackIndexList(int mVideo_count) {
        Video_Home_Port_count = mVideo_count;
        Video_Home_Port_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        android.util.Log.i("zuk", "Video_Home_count = " + Video_Home_Port_count + " Video_Home_random_indexlist->size()= " + (Video_Home_Port_random_indexlist == null ? 0 : Video_Home_Port_random_indexlist.size()));
        return Video_Home_Port_random_indexlist;
    }



    static  boolean isFileNameContainInTypeList(ArrayList<String> typeList , String fileName){
        boolean containFlag  = false;
        if(typeList == null || fileName == null){
            return containFlag;
        }

        for (int i = 0; i < typeList.size() ; i++) {
            String typeItem = typeList.get(i);

            if(fileName.trim().toLowerCase().endsWith(typeItem.trim().toLowerCase())){
                containFlag = true;
                break;
            }

        }

        return containFlag;
    }

    public static File[]  guolvType(File[] tempFileArr , ArrayList<String> endTypeTagList) {
        ArrayList<File> targetFileList = new ArrayList<File>();
        File[] targetFileArr = null;
        if(tempFileArr == null){
            return null;
        }

        for (int i = 0; i < tempFileArr.length; i++) {
            File itemFile = tempFileArr[i];
            String itemFileName_lower = itemFile.getName().trim().toLowerCase();

            if(isFileNameContainInTypeList(endTypeTagList,itemFileName_lower)){
                targetFileList.add(itemFile);
            }
        }
        targetFileArr = new File[targetFileList.size()];

        for (int i = 0; i < targetFileList.size(); i++) {
            File itemFile = targetFileList.get(i);
            targetFileArr[i] = itemFile;
        }




        return targetFileArr;
    }

    public static File[]  guolvType(File[] tempFileArr , String endTypeTag) {
        ArrayList<File> targetFileList = new ArrayList<File>();
        File[] targetFileArr = null;
        if(tempFileArr == null){
            return null;
        }
        for (int i = 0; i < tempFileArr.length; i++) {
            File itemFile = tempFileArr[i];
            String itemFileName_lower = itemFile.getName().trim().toLowerCase();
            if(itemFileName_lower.endsWith(endTypeTag.trim().toLowerCase())){
                targetFileList.add(itemFile);
            }
        }
        targetFileArr = new File[targetFileList.size()];

        for (int i = 0; i < targetFileList.size(); i++) {
            File itemFile = targetFileList.get(i);
            targetFileArr[i] = itemFile;
        }
        return targetFileArr;
    }



    public static void init_Mp4_Music_Land() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        // 风景
        Video_Music_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_music_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Music_Land_list_File.exists()) {
            Video_Music_Land_file_list = Video_Music_Land_list_File.listFiles();
            Video_Music_Land_file_list= guolvType(Video_Music_Land_file_list,".mp4");
            android.util.Log.i("zukgit", "A video_Music_Land_file_list=" + Video_Music_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B video_Music_Land_file_list=" + Video_Music_Land_list_File.exists());
        }


        if (Video_Music_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  video_Music_Land_file_list->size =" + Video_Music_Land_file_list.length);

            for (int i = 0; i < Video_Music_Land_file_list.length; i++) {
                File itemFile = Video_Music_Land_file_list[i];
                android.util.Log.i("zukgit", " video_Music_Land_File[" + i + "] = " + itemFile.getAbsolutePath());
            }
            video_file_sum = Video_Music_Land_file_list.length;
        } else {
            android.util.Log.i("zukgit", "B  video_Music_Land_File->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Music_Land_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Music_Land_File_Sum = " + video_file_sum);
        }
        init_Video_Music_Land_Category();
    }


    public static void init_Video_Music_Land_Category() {

        Video_Music_Land_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Music_Land_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Music_Land_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Music_Land_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Music_Land_list_File.exists()){


            File[] zssFileArr=     Video_Music_Land_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Music_Land_File = " + Video_Music_Land_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Music_Land_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Music_Land_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Music_Land_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Music_Land_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Music_Land_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Music_Land_FileList.size() > 0 ){

                    int allRealFile = all_Video_Music_Land_FileList.size();
                    int Video_Music_Land_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Music_Land_FileCount];

                    if(all_Video_Music_Land_FileList != null){
                        for (int i = 0; i < all_Video_Music_Land_FileList.size() ; i++) {
                            File fileItem = all_Video_Music_Land_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Music_Land_FileList.size() ; i++) {

                        File fileItem = all_Video_Music_Land_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Music_Land_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Music_Land   all_Video_Music_Land_FileList 没有文件");


                }

            }




        }


        if(Video_Music_Land_file_list != null && Video_Music_Land_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Music_Land_file_list.length ; i++) {

                File Top_land_file = Video_Music_Land_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Music_Land[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Music_Land_file_list[i]);

            }

            Video_Music_Land_Category_FileArr_Map.put(Video_Music_Land_MapKey,allTopFileArr);
            Video_Music_Land_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Music_Land_file_list.length,"all");
        }


        for (int i = 0; i < Video_Music_Land_KeyNameList.size() ; i++) {
            String ssKey = Video_Music_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Music_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Music_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Music_Land_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Music_Land_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Music_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Music_Land_KeyNameList.add(Video_Music_Land_MapKey);
        if(Video_Music_Land_file_list != null){
            Video_Music_Land_count = Video_Music_Land_file_list.length;
            Video_Music_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Music_Land_count);
        }
    }


    public static void init_Mp4_Music_Port() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        // 风景
        Video_Music_Port_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_music_port");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Music_Port_list_File.exists()) {
            Video_Music_Port_file_list = Video_Music_Port_list_File.listFiles();
            Video_Music_Port_file_list= guolvType(Video_Music_Port_file_list,".mp4");
            android.util.Log.i("zukgit", "A video_Music_Port_file_list=" + Video_Music_Port_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B video_Music_Port_file_list=" + Video_Music_Port_list_File.exists());
        }


        if (Video_Music_Port_file_list != null) {
            android.util.Log.i("zukgit", "A  video_Music_Port_file_list->size =" + Video_Music_Port_file_list.length);
            video_file_sum = Video_Music_Port_file_list.length;
            for (int i = 0; i < Video_Music_Port_file_list.length; i++) {
                File itemFile = Video_Music_Port_file_list[i];
                android.util.Log.i("zukgit", " video_Music_Port_File[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  video_Hua_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Music_Port_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Music_Port_File_Sum = " + video_file_sum);
        }
        init_Video_Music_Port_Category();
    }



    public static void init_Video_Music_Port_Category() {

        Video_Music_Port_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Music_Port_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Music_Port_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Music_Port_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Music_Port_list_File.exists()){


            File[] zssFileArr=     Video_Music_Port_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Music_Port_File = " + Video_Music_Port_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Music_Port_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Music_Port_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Music_Port_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Music_Port_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Music_Port_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Music_Port_FileList.size() > 0 ){

                    int allRealFile = all_Video_Music_Port_FileList.size();
                    int Video_Music_Port_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Music_Port_FileCount];

                    if(all_Video_Music_Port_FileList != null){
                        for (int i = 0; i < all_Video_Music_Port_FileList.size() ; i++) {
                            File fileItem = all_Video_Music_Port_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Music_Port_FileList.size() ; i++) {

                        File fileItem = all_Video_Music_Port_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Music_Port_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Music_Port   all_Video_Music_Port_FileList 没有文件");


                }

            }




        }


        if(Video_Music_Port_file_list != null && Video_Music_Port_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Music_Port_file_list.length ; i++) {

                File Top_land_file = Video_Music_Port_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Music_Port[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Music_Port_file_list[i]);

            }

            Video_Music_Port_Category_FileArr_Map.put(Video_Music_Port_MapKey,allTopFileArr);
            Video_Music_Port_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Music_Port_file_list.length,"all");
        }


        for (int i = 0; i < Video_Music_Port_KeyNameList.size() ; i++) {
            String ssKey = Video_Music_Port_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Music_Port_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Music_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Music_Port_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Music_Port_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Music_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Music_Port_KeyNameList.add(Video_Music_Port_MapKey);
        if(Video_Music_Port_file_list != null){
            Video_Music_Port_count = Video_Music_Port_file_list.length;
            Video_Music_Port_random_indexlist = RandomUtil.getRandomLengthArr(Video_Music_Port_count);
        }
    }





    public static void init_Mp4_Scene_Port() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Scene_Port_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_scene_port");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Scene_Port_list_File.exists()) {
            Video_Scene_Port_file_list = Video_Scene_Port_list_File.listFiles();
            Video_Scene_Port_file_list= guolvType(Video_Scene_Port_file_list,".mp4");
            android.util.Log.i("zukgit", "A Video_Scene_Port_file_list=" + Video_Scene_Port_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Video_Scene_Port_file_list=" + Video_Scene_Port_list_File.exists());
        }

        if (Video_Scene_Port_file_list != null) {
            android.util.Log.i("zukgit", "A  Video_Scene_Port_file_list->size =" + Video_Scene_Port_file_list.length);
            video_file_sum = Video_Scene_Port_file_list.length;
            for (int i = 0; i < Video_Scene_Port_file_list.length; i++) {
                File itemFile = Video_Scene_Port_file_list[i];
                android.util.Log.i("zukgit", " Video_Scene_Port_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Video_Scene_Port_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Scnece_Port_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Raw_file_sum = " + video_file_sum);
        }
        init_Video_Scene_Port_Category();
    }

    public static void init_Video_Scene_Port_Category() {

        Video_Scene_Port_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Scene_Port_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; // <三角函数,三角函数_7>
        Video_Scene_Port_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合



        ArrayList<File> all_Video_Scene_Port_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Scene_Port_list_File.exists()){


            File[] zssFileArr=     Video_Scene_Port_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Scene_Port_File = " + Video_Scene_Port_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Scene_Port_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Scene_Port_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Scene_Port_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Scene_Port_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Scene_Port_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Scene_Port_FileList.size() > 0 ){

                    int allRealFile = all_Video_Scene_Port_FileList.size();
                    int Video_Scene_Port_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Scene_Port_FileCount];

                    if(all_Video_Scene_Port_FileList != null){
                        for (int i = 0; i < all_Video_Scene_Port_FileList.size() ; i++) {
                            File fileItem = all_Video_Scene_Port_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Scene_Port_FileList.size() ; i++) {

                        File fileItem = all_Video_Scene_Port_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Scene_Port_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Scene_Port   all_Video_Scene_Port_FileList 没有文件");


                }

            }




        }


        if(Video_Scene_Port_file_list != null && Video_Scene_Port_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Scene_Port_file_list.length ; i++) {

                File Top_land_file = Video_Scene_Port_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Scene_Port[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Scene_Port_file_list[i]);

            }

            Video_Scene_Port_Category_FileArr_Map.put(Video_Scene_Port_MapKey,allTopFileArr);
            Video_Scene_Port_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Scene_Port_file_list.length,"all");
        }


        for (int i = 0; i < Video_Scene_Port_KeyNameList.size() ; i++) {
            String ssKey = Video_Scene_Port_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Scene_Port_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Scene_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Scene_Port_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Scene_Port_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Scene_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Scene_Port_KeyNameList.add(Video_Scene_Port_MapKey);
        if(Video_Scene_Port_file_list != null){
            Video_Scene_Port_count = Video_Scene_Port_file_list.length;
            Video_Scene_Port_random_indexlist = RandomUtil.getRandomLengthArr(Video_Scene_Port_count);
        }

    }



    public static void init_Mp4_English_Land() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_English_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_english_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_English_Land_list_File.exists()) {
            Video_English_Land_file_list = Video_English_Land_list_File.listFiles();
            Video_English_Land_file_list= guolvType(Video_English_Land_file_list,".mp4");
            android.util.Log.i("zukgit", "A Video_English_Land_file_list=" + Video_English_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Video_English_Land_file_list=" + Video_English_Land_list_File.exists());
        }

        if (Video_English_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  Video_English_Land_file_list->size =" + Video_English_Land_file_list.length);
            video_file_sum = Video_English_Land_file_list.length;
            for (int i = 0; i < Video_English_Land_file_list.length; i++) {
                File itemFile = Video_English_Land_file_list[i];
                android.util.Log.i("zukgit", " Video_English_Land_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Video_English_Land_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Scnece_Land_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Raw_file_sum = " + video_file_sum);
        }
        init_Video_English_Land_Category();
    }

    public static void init_Video_English_Land_Category() {

        Video_English_Land_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_English_Land_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; // <三角函数,三角函数_7>
        Video_English_Land_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合



        ArrayList<File> all_Video_English_Land_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_English_Land_list_File.exists()){


            File[] zssFileArr=     Video_English_Land_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_English_Land_File = " + Video_English_Land_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_English_Land_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_English_Land_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_English_Land_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_English_Land_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_English_Land_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_English_Land_FileList.size() > 0 ){

                    int allRealFile = all_Video_English_Land_FileList.size();
                    int Video_English_Land_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_English_Land_FileCount];

                    if(all_Video_English_Land_FileList != null){
                        for (int i = 0; i < all_Video_English_Land_FileList.size() ; i++) {
                            File fileItem = all_Video_English_Land_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_English_Land_FileList.size() ; i++) {

                        File fileItem = all_Video_English_Land_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_English_Land_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_English_Land   all_Video_English_Land_FileList 没有文件");


                }

            }




        }


        if(Video_English_Land_file_list != null && Video_English_Land_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_English_Land_file_list.length ; i++) {

                File Top_land_file = Video_English_Land_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_English_Land[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_English_Land_file_list[i]);

            }

            Video_English_Land_Category_FileArr_Map.put(Video_English_Land_MapKey,allTopFileArr);
            Video_English_Land_KeyName_KeyNameSize_Map.put("all"+"_"+Video_English_Land_file_list.length,"all");
        }


        for (int i = 0; i < Video_English_Land_KeyNameList.size() ; i++) {
            String ssKey = Video_English_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_English_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_English_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_English_Land_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_English_Land_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_English_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_English_Land_KeyNameList.add(Video_English_Land_MapKey);
        if(Video_English_Land_file_list != null){
            Video_English_Land_count = Video_English_Land_file_list.length;
            Video_English_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_English_Land_count);
        }
    }


    public static void init_Mp4_Zhexue_Land() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Zhexue_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_zhexue_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Zhexue_Land_list_File.exists()) {
            Video_Zhexue_Land_file_list = Video_Zhexue_Land_list_File.listFiles();
            Video_Zhexue_Land_file_list= guolvType(Video_Zhexue_Land_file_list,".mp4");
            android.util.Log.i("zukgit", "A Video_Zhexue_Land_file_list=" + Video_Zhexue_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Video_Zhexue_Land_file_list=" + Video_Zhexue_Land_list_File.exists());
        }

        if (Video_Zhexue_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  Video_Zhexue_Land_file_list->size =" + Video_Zhexue_Land_file_list.length);
            video_file_sum = Video_Zhexue_Land_file_list.length;
            for (int i = 0; i < Video_Zhexue_Land_file_list.length; i++) {
                File itemFile = Video_Zhexue_Land_file_list[i];
                android.util.Log.i("zukgit", " Video_Zhexue_Land_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Video_Zhexue_Land_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Scnece_Land_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Raw_file_sum = " + video_file_sum);
        }
        init_Video_Zhexue_Land_Category();
    }

    public static void init_Video_Zhexue_Land_Category() {

        Video_Zhexue_Land_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Zhexue_Land_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; // <三角函数,三角函数_7>
        Video_Zhexue_Land_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合



        ArrayList<File> all_Video_Zhexue_Land_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Zhexue_Land_list_File.exists()){


            File[] zssFileArr=     Video_Zhexue_Land_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Zhexue_Land_File = " + Video_Zhexue_Land_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Zhexue_Land_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Zhexue_Land_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Zhexue_Land_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Zhexue_Land_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Zhexue_Land_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Zhexue_Land_FileList.size() > 0 ){

                    int allRealFile = all_Video_Zhexue_Land_FileList.size();
                    int Video_Zhexue_Land_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Zhexue_Land_FileCount];

                    if(all_Video_Zhexue_Land_FileList != null){
                        for (int i = 0; i < all_Video_Zhexue_Land_FileList.size() ; i++) {
                            File fileItem = all_Video_Zhexue_Land_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Zhexue_Land_FileList.size() ; i++) {

                        File fileItem = all_Video_Zhexue_Land_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Zhexue_Land_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Zhexue_Land   all_Video_Zhexue_Land_FileList 没有文件");


                }

            }




        }


        if(Video_Zhexue_Land_file_list != null && Video_Zhexue_Land_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Zhexue_Land_file_list.length ; i++) {

                File Top_land_file = Video_Zhexue_Land_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Zhexue_Land[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Zhexue_Land_file_list[i]);

            }

            Video_Zhexue_Land_Category_FileArr_Map.put(Video_Zhexue_Land_MapKey,allTopFileArr);
            Video_Zhexue_Land_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Zhexue_Land_file_list.length,"all");
        }


        for (int i = 0; i < Video_Zhexue_Land_KeyNameList.size() ; i++) {
            String ssKey = Video_Zhexue_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Zhexue_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Zhexue_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Zhexue_Land_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Zhexue_Land_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Zhexue_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Zhexue_Land_KeyNameList.add(Video_Zhexue_Land_MapKey);
        if(Video_Zhexue_Land_file_list != null){
            Video_Zhexue_Land_count = Video_Zhexue_Land_file_list.length;
            Video_Zhexue_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Zhexue_Land_count);
        }
    }



    public static void init_Mp4_Scene_Land() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Scene_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_scene_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Scene_Land_list_File.exists()) {
            Video_Scene_Land_file_list = Video_Scene_Land_list_File.listFiles();
            Video_Scene_Land_file_list= guolvType(Video_Scene_Land_file_list,".mp4");
            android.util.Log.i("zukgit", "A Video_Scene_Land_file_list=" + Video_Scene_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Video_Scene_Land_file_list=" + Video_Scene_Land_list_File.exists());
        }

        if (Video_Scene_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  Video_Scene_Land_file_list->size =" + Video_Scene_Land_file_list.length);
            video_file_sum = Video_Scene_Land_file_list.length;
            for (int i = 0; i < Video_Scene_Land_file_list.length; i++) {
                File itemFile = Video_Scene_Land_file_list[i];
                android.util.Log.i("zukgit", " Video_Scene_Land_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Video_Scene_Land_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Scnece_Land_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Raw_file_sum = " + video_file_sum);
        }
        init_Video_Scene_Land_Category();
    }

    public static void init_Video_Scene_Land_Category() {

        Video_Scene_Land_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Scene_Land_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Scene_Land_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Scene_Land_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Scene_Land_list_File.exists()){


            File[] zssFileArr=     Video_Scene_Land_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Scene_Land_File = " + Video_Scene_Land_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Scene_Land_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Scene_Land_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Scene_Land_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Scene_Land_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Scene_Land_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Scene_Land_FileList.size() > 0 ){

                    int allRealFile = all_Video_Scene_Land_FileList.size();
                    int Video_Scene_Land_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Scene_Land_FileCount];

                    if(all_Video_Scene_Land_FileList != null){
                        for (int i = 0; i < all_Video_Scene_Land_FileList.size() ; i++) {
                            File fileItem = all_Video_Scene_Land_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Scene_Land_FileList.size() ; i++) {

                        File fileItem = all_Video_Scene_Land_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Scene_Land_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Scene_Land   all_Video_Scene_Land_FileList 没有文件");


                }

            }




        }


        if(Video_Scene_Land_file_list != null && Video_Scene_Land_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Scene_Land_file_list.length ; i++) {

                File Top_land_file = Video_Scene_Land_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Scene_Land[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Scene_Land_file_list[i]);

            }

            Video_Scene_Land_Category_FileArr_Map.put(Video_Scene_Land_MapKey,allTopFileArr);
            Video_Scene_Land_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Scene_Land_file_list.length,"all");
        }


        for (int i = 0; i < Video_Scene_Land_KeyNameList.size() ; i++) {
            String ssKey = Video_Scene_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Scene_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Scene_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Scene_Land_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Scene_Land_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Scene_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Scene_Land_KeyNameList.add(Video_Scene_Land_MapKey);
        if(Video_Scene_Land_file_list != null){
            Video_Scene_Land_count = Video_Scene_Land_file_list.length;
            Video_Scene_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Scene_Land_count);
        }
    }



    public static void init_Mp4_Home_Port() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Home_Port_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_home_port");
        android.util.Log.i("zukgit", "A Video_Home_list_File.absPath=" + Video_Home_Port_list_File.getAbsolutePath());

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Home_Port_list_File.exists()) {
            Video_Home_Port_file_list = Video_Home_Port_list_File.listFiles();
            Video_Home_Port_file_list = guolvType(Video_Home_Port_file_list,".mp4");

            android.util.Log.i("zukgit", "A video_Home_Port_file_list=" + Video_Home_Port_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B video_Home_Port_file_list=" + Video_Home_Port_list_File.exists());
        }

        if (Video_Home_Port_file_list != null) {
            android.util.Log.i("zukgit", "A  video_Home_Port_file_list->size =" + Video_Home_Port_file_list.length);
            video_file_sum = Video_Home_Port_file_list.length;
            android.util.Log.i("zukgit", "A  video_Home_Port_file_list.length =" + Video_Home_Port_file_list.length);

            for (int i = 0; i < Video_Home_Port_file_list.length; i++) {
                File itemFile = Video_Home_Port_file_list[i];
                android.util.Log.i("zukgit", " video_Home_Port_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  video_Home_Port_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Home_Port_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Home_Port_file_list_sum = " + video_file_sum);
        }
        init_Video_Home_Port_Category();
    }



    public static void init_Video_Home_Port_Category() {

        Video_Home_Port_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Home_Port_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Home_Port_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Home_Port_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Home_Port_list_File.exists()){


            File[] zssFileArr=     Video_Home_Port_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Home_Port_File = " + Video_Home_Port_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Home_Port_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Home_Port_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Home_Port_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Home_Port_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Home_Port_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Home_Port_FileList.size() > 0 ){

                    int allRealFile = all_Video_Home_Port_FileList.size();
                    int Video_Home_Port_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Home_Port_FileCount];

                    if(all_Video_Home_Port_FileList != null){
                        for (int i = 0; i < all_Video_Home_Port_FileList.size() ; i++) {
                            File fileItem = all_Video_Home_Port_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Home_Port_FileList.size() ; i++) {

                        File fileItem = all_Video_Home_Port_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Home_Port_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Home_Port   all_Video_Home_Port_FileList 没有文件");


                }

            }




        }


        if(Video_Home_Port_file_list != null && Video_Home_Port_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Home_Port_file_list.length ; i++) {

                File Top_land_file = Video_Home_Port_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Home_Port[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Home_Port_file_list[i]);

            }

            Video_Home_Port_Category_FileArr_Map.put(Video_Home_Port_MapKey,allTopFileArr);
            Video_Home_Port_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Home_Port_file_list.length,"all");
        }


        for (int i = 0; i < Video_Home_Port_KeyNameList.size() ; i++) {
            String ssKey = Video_Home_Port_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Home_Port_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Home_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Home_Port_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Home_Port_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Home_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Home_Port_KeyNameList.add(Video_Home_Port_MapKey);
        if(Video_Home_Port_file_list != null){
            Video_Home_Port_count = Video_Home_Port_file_list.length;
            Video_Home_Port_random_indexlist = RandomUtil.getRandomLengthArr(Video_Home_Port_count);
        }
    }


    public static void init_Mp4_Home_Land() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Home_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_home_land");
        android.util.Log.i("zukgit", "A Video_Home_list_File.absPath=" + Video_Home_Land_list_File.getAbsolutePath());

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Home_Land_list_File.exists()) {
            Video_Home_Land_file_list = Video_Home_Land_list_File.listFiles();
            Video_Home_Land_file_list = guolvType(Video_Home_Land_file_list,".mp4");

            android.util.Log.i("zukgit", "A video_Home_Land_file_list=" + Video_Home_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B video_Homee_Land_file_list=" + Video_Home_Land_list_File.exists());
        }

        if (Video_Home_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  video_Homee_Land_file_list->size =" + Video_Home_Land_file_list.length);
            video_file_sum = Video_Home_Land_file_list.length;
            android.util.Log.i("zukgit", "A  video_Homee_Land_file_list.length =" + Video_Home_Land_file_list.length);

            for (int i = 0; i < Video_Home_Land_file_list.length; i++) {
                File itemFile = Video_Home_Land_file_list[i];
                android.util.Log.i("zukgit", " video_Home_Land_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  video_Homee_Land_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Home_Land_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Home_file_list_sum = " + video_file_sum);
        }
        init_Video_Home_Land_Category();
    }


    public static void init_Video_Home_Land_Category() {

        Video_Home_Land_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Home_Land_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Home_Land_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Home_Land_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Home_Land_list_File.exists()){


            File[] zssFileArr=     Video_Home_Land_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Home_Land_File = " + Video_Home_Land_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Home_Land_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Home_Land_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Home_Land_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Home_Land_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Home_Land_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Home_Land_FileList.size() > 0 ){

                    int allRealFile = all_Video_Home_Land_FileList.size();
                    int Video_Home_Land_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Home_Land_FileCount];

                    if(all_Video_Home_Land_FileList != null){
                        for (int i = 0; i < all_Video_Home_Land_FileList.size() ; i++) {
                            File fileItem = all_Video_Home_Land_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Home_Land_FileList.size() ; i++) {

                        File fileItem = all_Video_Home_Land_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Home_Land_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Home_Land   all_Video_Home_Land_FileList 没有文件");


                }

            }




        }


        if(Video_Home_Land_file_list != null && Video_Home_Land_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Home_Land_file_list.length ; i++) {

                File Top_land_file = Video_Home_Land_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Home_Land[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Home_Land_file_list[i]);

            }

            Video_Home_Land_Category_FileArr_Map.put(Video_Home_Land_MapKey,allTopFileArr);
            Video_Home_Land_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Home_Land_file_list.length,"all");
        }


        for (int i = 0; i < Video_Home_Land_KeyNameList.size() ; i++) {
            String ssKey = Video_Home_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Home_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Home_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Home_Land_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Home_Land_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Home_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Home_Land_KeyNameList.add(Video_Home_Land_MapKey);
        if(Video_Home_Land_file_list != null){
            Video_Home_Land_count = Video_Home_Land_file_list.length;
            Video_Home_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Home_Land_count);
        }
    }

    public static ArrayList<Integer> setVideo_Home_Land_Count_BackIndexList(int mVideo_count) {
        Video_Home_Land_count = mVideo_count;
        Video_Home_Land_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        android.util.Log.i("zuk", "Video_Home_Land_count = " + Video_Home_Land_count + " Video_Home_Land_random_indexlist->size()= " + (Video_Home_Land_random_indexlist == null ? 0 : Video_Home_Land_random_indexlist.size()));
        return Video_Home_Land_random_indexlist;
    }


    public static void init_Mp4_Gaokao_Land() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Gaokao_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_gaokao_land");
        android.util.Log.i("zukgit", "A Video_Gaokao_list_File.absPath=" + Video_Gaokao_Land_list_File.getAbsolutePath());

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Gaokao_Land_list_File.exists()) {
            Video_Gaokao_Land_file_list = Video_Gaokao_Land_list_File.listFiles();
            Video_Gaokao_Land_file_list = guolvType(Video_Gaokao_Land_file_list,".mp4");

            android.util.Log.i("zukgit", "A video_Gaokao_Land_file_list=" + Video_Gaokao_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B video_Gaokaoe_Land_file_list=" + Video_Gaokao_Land_list_File.exists());
        }

        if (Video_Gaokao_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  video_Gaokaoe_Land_file_list->size =" + Video_Gaokao_Land_file_list.length);
            video_file_sum = Video_Gaokao_Land_file_list.length;
            android.util.Log.i("zukgit", "A  video_Gaokaoe_Land_file_list.length =" + Video_Gaokao_Land_file_list.length);

            for (int i = 0; i < Video_Gaokao_Land_file_list.length; i++) {
                File itemFile = Video_Gaokao_Land_file_list[i];
                android.util.Log.i("zukgit", " video_Gaokao_Land_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  video_Gaokaoe_Land_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Gaokao_Land_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Gaokao_file_list_sum = " + video_file_sum);
        }
        init_Video_Gaokao_Land_Category();
    }


    public static void init_Video_Gaokao_Land_Category() {

        Video_Gaokao_Land_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Gaokao_Land_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Gaokao_Land_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Gaokao_Land_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Gaokao_Land_list_File.exists()){


            File[] zssFileArr=     Video_Gaokao_Land_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Gaokao_Land_File = " + Video_Gaokao_Land_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Gaokao_Land_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Gaokao_Land_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Gaokao_Land_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Gaokao_Land_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Gaokao_Land_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Gaokao_Land_FileList.size() > 0 ){

                    int allRealFile = all_Video_Gaokao_Land_FileList.size();
                    int Video_Gaokao_Land_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Gaokao_Land_FileCount];

                    if(all_Video_Gaokao_Land_FileList != null){
                        for (int i = 0; i < all_Video_Gaokao_Land_FileList.size() ; i++) {
                            File fileItem = all_Video_Gaokao_Land_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Gaokao_Land_FileList.size() ; i++) {

                        File fileItem = all_Video_Gaokao_Land_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Gaokao_Land_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Gaokao_Land   all_Video_Gaokao_Land_FileList 没有文件");


                }

            }




        }


        if(Video_Gaokao_Land_file_list != null && Video_Gaokao_Land_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Gaokao_Land_file_list.length ; i++) {

                File Top_land_file = Video_Gaokao_Land_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Gaokao_Land[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Gaokao_Land_file_list[i]);

            }

            Video_Gaokao_Land_Category_FileArr_Map.put(Video_Gaokao_Land_MapKey,allTopFileArr);
            Video_Gaokao_Land_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Gaokao_Land_file_list.length,"all");
        }


        for (int i = 0; i < Video_Gaokao_Land_KeyNameList.size() ; i++) {
            String ssKey = Video_Gaokao_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Gaokao_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Gaokao_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Gaokao_Land_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Gaokao_Land_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Gaokao_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Gaokao_Land_KeyNameList.add(Video_Gaokao_Land_MapKey);
        if(Video_Gaokao_Land_file_list != null){
            Video_Gaokao_Land_count = Video_Gaokao_Land_file_list.length;
            Video_Gaokao_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Gaokao_Land_count);
        }
    }

    public static ArrayList<Integer> setVideo_Gaokao_Land_Count_BackIndexList(int mVideo_count) {
        Video_Gaokao_Land_count = mVideo_count;
        Video_Gaokao_Land_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        android.util.Log.i("zuk", "Video_Gaokao_Land_count = " + Video_Gaokao_Land_count + " Video_Gaokao_Land_random_indexlist->size()= " + (Video_Gaokao_Land_random_indexlist == null ? 0 : Video_Gaokao_Land_random_indexlist.size()));
        return Video_Gaokao_Land_random_indexlist;
    }



    public static void init_Mp4_Kaoyan_Land() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Kaoyan_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_Kaoyan_land");
        android.util.Log.i("zukgit", "A Video_Kaoyan_list_File.absPath=" + Video_Kaoyan_Land_list_File.getAbsolutePath());

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Kaoyan_Land_list_File.exists()) {
            Video_Kaoyan_Land_file_list = Video_Kaoyan_Land_list_File.listFiles();
            Video_Kaoyan_Land_file_list = guolvType(Video_Kaoyan_Land_file_list,".mp4");

            android.util.Log.i("zukgit", "A video_Kaoyan_Land_file_list=" + Video_Kaoyan_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B video_Kaoyane_Land_file_list=" + Video_Kaoyan_Land_list_File.exists());
        }

        if (Video_Kaoyan_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  video_Kaoyane_Land_file_list->size =" + Video_Kaoyan_Land_file_list.length);
            video_file_sum = Video_Kaoyan_Land_file_list.length;
            android.util.Log.i("zukgit", "A  video_Kaoyane_Land_file_list.length =" + Video_Kaoyan_Land_file_list.length);

            for (int i = 0; i < Video_Kaoyan_Land_file_list.length; i++) {
                File itemFile = Video_Kaoyan_Land_file_list[i];
                android.util.Log.i("zukgit", " video_Kaoyan_Land_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  video_Kaoyane_Land_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Kaoyan_Land_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! video_Kaoyan_file_list_sum = " + video_file_sum);
        }
        init_Video_Kaoyan_Land_Category();
    }


    public static void init_Video_Kaoyan_Land_Category() {

        Video_Kaoyan_Land_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Kaoyan_Land_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Kaoyan_Land_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Kaoyan_Land_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Kaoyan_Land_list_File.exists()){


            File[] zssFileArr=     Video_Kaoyan_Land_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Kaoyan_Land_File = " + Video_Kaoyan_Land_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Kaoyan_Land_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Kaoyan_Land_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Kaoyan_Land_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Kaoyan_Land_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Kaoyan_Land_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Kaoyan_Land_FileList.size() > 0 ){

                    int allRealFile = all_Video_Kaoyan_Land_FileList.size();
                    int Video_Kaoyan_Land_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Kaoyan_Land_FileCount];

                    if(all_Video_Kaoyan_Land_FileList != null){
                        for (int i = 0; i < all_Video_Kaoyan_Land_FileList.size() ; i++) {
                            File fileItem = all_Video_Kaoyan_Land_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Kaoyan_Land_FileList.size() ; i++) {

                        File fileItem = all_Video_Kaoyan_Land_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Kaoyan_Land_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Kaoyan_Land   all_Video_Kaoyan_Land_FileList 没有文件");


                }

            }




        }


        if(Video_Kaoyan_Land_file_list != null && Video_Kaoyan_Land_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Kaoyan_Land_file_list.length ; i++) {

                File Top_land_file = Video_Kaoyan_Land_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Kaoyan_Land[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Kaoyan_Land_file_list[i]);

            }

            Video_Kaoyan_Land_Category_FileArr_Map.put(Video_Kaoyan_Land_MapKey,allTopFileArr);
            Video_Kaoyan_Land_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Kaoyan_Land_file_list.length,"all");
        }


        for (int i = 0; i < Video_Kaoyan_Land_KeyNameList.size() ; i++) {
            String ssKey = Video_Kaoyan_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Kaoyan_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Kaoyan_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Kaoyan_Land_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Kaoyan_Land_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Kaoyan_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Kaoyan_Land_KeyNameList.add(Video_Kaoyan_Land_MapKey);
        if(Video_Kaoyan_Land_file_list != null){
            Video_Kaoyan_Land_count = Video_Kaoyan_Land_file_list.length;
            Video_Kaoyan_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Kaoyan_Land_count);
        }
    }


    public static ArrayList<Integer> setVideo_Kaoyan_Land_Count_BackIndexList(int mVideo_count) {
        Video_Kaoyan_Land_count = mVideo_count;
        Video_Kaoyan_Land_random_indexlist = RandomUtil.getRandomLengthArr(mVideo_count);
        android.util.Log.i("zuk", "Video_Kaoyan_Land_count = " + Video_Kaoyan_Land_count + " Video_Kaoyan_Land_random_indexlist->size()= " + (Video_Kaoyan_Land_random_indexlist == null ? 0 : Video_Kaoyan_Land_random_indexlist.size()));
        return Video_Kaoyan_Land_random_indexlist;
    }

    public static void init_Mp4_Common_Port() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Common_Port_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_common_port");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Common_Port_list_File.exists()) {
            Video_Common_Port_file_list = Video_Common_Port_list_File.listFiles();
            Video_Common_Port_file_list= guolvType(Video_Common_Port_file_list,".mp4");
            android.util.Log.i("zukgit", "A Video_Common_Port_file_list=" + Video_Common_Port_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Video_Common_Port_file_list=" + Video_Common_Port_list_File.exists());
        }

        if (Video_Common_Port_file_list != null) {
            android.util.Log.i("zukgit", "A  Video_Common_Port_file_list->size =" + Video_Common_Port_file_list.length);
            video_file_sum = Video_Common_Port_file_list.length;
            for (int i = 0; i < Video_Common_Port_file_list.length; i++) {
                File itemFile = Video_Common_Port_file_list[i];
                android.util.Log.i("zukgit", " Video_Common_Port_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Video_Common_Port_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Common_Port_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Video_Common_Port_file_list_sum = " + video_file_sum);
        }
        init_Video_Common_Port_Category();
    }


    public static void init_Video_Common_Port_Category() {

        Video_Common_Port_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Common_Port_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Common_Port_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Common_Port_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Common_Port_list_File.exists()){


            File[] zssFileArr=     Video_Common_Port_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Common_Port_File = " + Video_Common_Port_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Common_Port_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Common_Port_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Common_Port_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Common_Port_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Common_Port_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Common_Port_FileList.size() > 0 ){

                    int allRealFile = all_Video_Common_Port_FileList.size();
                    int Video_Common_Port_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Common_Port_FileCount];

                    if(all_Video_Common_Port_FileList != null){
                        for (int i = 0; i < all_Video_Common_Port_FileList.size() ; i++) {
                            File fileItem = all_Video_Common_Port_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Common_Port_FileList.size() ; i++) {

                        File fileItem = all_Video_Common_Port_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Common_Port_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Common_Port   all_Video_Common_Port_FileList 没有文件");


                }

            }




        }


        if(Video_Common_Port_file_list != null && Video_Common_Port_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Common_Port_file_list.length ; i++) {

                File Top_land_file = Video_Common_Port_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Common_Port[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Common_Port_file_list[i]);

            }

            Video_Common_Port_Category_FileArr_Map.put(Video_Common_Port_MapKey,allTopFileArr);
            Video_Common_Port_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Common_Port_file_list.length,"all");
        }


        for (int i = 0; i < Video_Common_Port_KeyNameList.size() ; i++) {
            String ssKey = Video_Common_Port_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Common_Port_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Common_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Common_Port_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Common_Port_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Common_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Common_Port_KeyNameList.add(Video_Common_Port_MapKey);
        if(Video_Common_Port_file_list != null){
            Video_Common_Port_count = Video_Common_Port_file_list.length;
            Video_Common_Port_random_indexlist = RandomUtil.getRandomLengthArr(Video_Common_Port_count);
        }
    }



    public static void init_Mp4_Common_Land() {

        int video_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Video_Common_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp4_common_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Video_Common_Land_list_File.exists()) {
            Video_Common_Land_file_list = Video_Common_Land_list_File.listFiles();
            Video_Common_Land_file_list= guolvType(Video_Common_Land_file_list,".mp4");
            android.util.Log.i("zukgit", "A Video_Common_Land_file_list=" + Video_Common_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Video_Common_Land_file_list=" + Video_Common_Land_list_File.exists());
        }

        if (Video_Common_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  Video_Common_Land_file_list->size =" + Video_Common_Land_file_list.length);
            video_file_sum = Video_Common_Land_file_list.length;
            for (int i = 0; i < Video_Common_Land_file_list.length; i++) {
                File itemFile = Video_Common_Land_file_list[i];
                android.util.Log.i("zukgit", " Video_Common_Land_file_list[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Video_Common_Land_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setVideo_Common_Land_Count_BackIndexList(video_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Video_Common_Land_file_list_sum = " + video_file_sum);
        }
        init_Video_Common_Land_Category();
    }


    public static void init_Video_Common_Land_Category() {

        Video_Common_Land_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Video_Common_Land_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; //
        Video_Common_Land_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //



        ArrayList<File> all_Video_Common_Land_FileList = new    ArrayList<File>();
        //zTop 操作 吧 当前目录下的所有 实体文件 子目录下的所有实体文件 都 遍历了去
        if(Video_Common_Land_list_File.exists()){


            File[] zssFileArr=     Video_Common_Land_list_File.listFiles();

            if(zssFileArr == null || zssFileArr.length == 0){
                android.util.Log.i("zukgit", " Video_Common_Land_File = " + Video_Common_Land_list_File.getAbsolutePath()+" 下 没有文件 ！ ");

            }else{

                android.util.Log.i("zukgit", " mp4_Top_land  zssFileArr.length  =" + zssFileArr.length);

                for (int i = 0; i < zssFileArr.length ; i++) {
                    File sonFile = zssFileArr[i];
                    // 当前 只拿去  不是 port 目录下  的 文件
                    if(sonFile.isFile() ){

                        all_Video_Common_Land_FileList.add(sonFile);
                        continue;

                    }

                    if(sonFile.isDirectory()){

                        File[] zss_Sun_FileArr =     sonFile.listFiles();
                        String zssDirName = sonFile.getName();


                        if(zss_Sun_FileArr == null || zss_Sun_FileArr.length == 0){
                            android.util.Log.i("zukgit", " sonFile = " + sonFile.getAbsolutePath()+" 下 没有文件 ！ ");
                            android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = 0" );

                            continue;
                        }


                        Video_Common_Land_KeyNameList.add(zssDirName);

                        ArrayList<File> OneTopFileArr = new   ArrayList<File>();


                        android.util.Log.i("zukgit", " mp4_Top_land  zss_Sun_FileArr.length  = " + zss_Sun_FileArr.length );


                        for (int j = 0; j < zss_Sun_FileArr.length ; j++) {
                            File sunFile  = zss_Sun_FileArr[j];

                            if(sunFile.isFile() ){
//                            if(sunFile.isFile() && !sunFile.getAbsolutePath().contains("port")){

                                all_Video_Common_Land_FileList.add(sunFile);
                                OneTopFileArr.add(sunFile);
                                continue;
                            }

                            if(sunFile.isDirectory() ){   //  如果 还有  孙子文件夹  那么继续添加

                                File[] zengsun_FileArr  = sunFile.listFiles();

                                if(zengsun_FileArr == null || zengsun_FileArr.length == 0){
                                    continue;
                                }

                                for (int k = 0; k < zengsun_FileArr.length; k++) {
                                    File zengsun_File  = zengsun_FileArr[k];

                                    if(zengsun_File.isFile() ){
                                        all_Video_Common_Land_FileList.add(zengsun_File);
                                        OneTopFileArr.add(zengsun_File);
                                        continue;
                                    }

                                }

                            }


                        }

                        if(OneTopFileArr.size() > 0 ){

                            Video_Common_Land_Category_FileArr_Map.put(zssDirName,OneTopFileArr);  // 结束点
                        }

                    }

                }
                if(all_Video_Common_Land_FileList.size() > 0 ){

                    int allRealFile = all_Video_Common_Land_FileList.size();
                    int Video_Common_Land_FileCount = allRealFile;
                    File[]  fixedLandArr  = new File[Video_Common_Land_FileCount];

                    if(all_Video_Common_Land_FileList != null){
                        for (int i = 0; i < all_Video_Common_Land_FileList.size() ; i++) {
                            File fileItem = all_Video_Common_Land_FileList.get(i);

                            fixedLandArr[i] = fileItem;
                        }

                    }

                    int beginIndex  = 0;


                    for (int i = 0; i < all_Video_Common_Land_FileList.size() ; i++) {

                        File fileItem = all_Video_Common_Land_FileList.get(i);
                        fixedLandArr[beginIndex] = fileItem;
                        beginIndex++;
                    }


                    Video_Common_Land_file_list  = fixedLandArr;

                } else{
                    android.util.Log.i("zukgit", "Video_Common_Land   all_Video_Common_Land_FileList 没有文件");


                }

            }




        }


        if(Video_Common_Land_file_list != null && Video_Common_Land_file_list.length > 0 ){
            ArrayList<File> allTopFileArr = new   ArrayList<File>();

            for (int i = 0; i < Video_Common_Land_file_list.length ; i++) {

                File Top_land_file = Video_Common_Land_file_list[i];
                long fileSize_MB = Top_land_file.length()/(1024*1024);
                android.util.Log.i("zukgit", " Video_Common_Land[" + i + "]["+fileSize_MB+"MB] = " + Top_land_file.getAbsolutePath());

                allTopFileArr.add(Video_Common_Land_file_list[i]);

            }

            Video_Common_Land_Category_FileArr_Map.put(Video_Common_Land_MapKey,allTopFileArr);
            Video_Common_Land_KeyName_KeyNameSize_Map.put("all"+"_"+Video_Common_Land_file_list.length,"all");
        }


        for (int i = 0; i < Video_Common_Land_KeyNameList.size() ; i++) {
            String ssKey = Video_Common_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Video_Common_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Topkey["+i+"]["+Video_Common_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                Video_Common_Land_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Video_Common_Land_KeyName_KeyNameSize_Map.put(ssKey+"_"+matchFileArr.size(),ssKey);
            }else{

                print("Topkey["+i+"]["+Video_Common_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }
        Video_Common_Land_KeyNameList.add(Video_Common_Land_MapKey);
        if(Video_Common_Land_file_list != null){
            Video_Common_Land_count = Video_Common_Land_file_list.length;
            Video_Common_Land_random_indexlist = RandomUtil.getRandomLengthArr(Video_Common_Land_count);
        }
    }


    public static void init_Common_Img_Land() {


        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        //
        Common_Img_Land_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_common_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        // _____________ Common_Img
        int Common_Img_Land_file_sum = 0;
        if (Common_Img_Land_list_File.exists()) {
            Common_Img_Land_file_list = Common_Img_Land_list_File.listFiles();
            Common_Img_Land_file_list= guolvType(Common_Img_Land_file_list,".jpg");
            android.util.Log.i("zukgit", "A Common_Img_Land_file_list=" + Common_Img_Land_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Common_Img_Land_file_list=" + Common_Img_Land_list_File.exists());
        }

        if (Common_Img_Land_file_list != null) {
            android.util.Log.i("zukgit", "A  Common_Img_Land_file_list->size =" + Common_Img_Land_file_list.length);
            Common_Img_Land_file_sum = Common_Img_Land_file_list.length;
            for (int i = 0; i < Common_Img_Land_file_list.length; i++) {
                File itemFile = Common_Img_Land_file_list[i];
                android.util.Log.i("zukgit", " ImgFile[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Common_Img_Land_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setCommon_Img_Land_Count_BackIndexList(Common_Img_Land_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Common_Img_Land_file_sum = " + Common_Img_Land_file_sum);
        }

    }

    public static void init_Common_Img_Port() {


        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        //
        Common_Img_Port_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_common_port");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        // _____________ Common_Img
        int Common_Img_Port_file_sum = 0;
        if (Common_Img_Port_list_File.exists()) {
            Common_Img_Port_file_list = Common_Img_Port_list_File.listFiles();
            Common_Img_Port_file_list= guolvType(Common_Img_Port_file_list,".jpg");
            android.util.Log.i("zukgit", "A Common_Img_Port_file_list=" + Common_Img_Port_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Common_Img_Port_file_list=" + Common_Img_Port_list_File.exists());
        }

        if (Common_Img_Port_file_list != null) {
            android.util.Log.i("zukgit", "A  Common_Img_Port_file_list->size =" + Common_Img_Port_file_list.length);
            Common_Img_Port_file_sum = Common_Img_Port_file_list.length;
            for (int i = 0; i < Common_Img_Port_file_list.length; i++) {
                File itemFile = Common_Img_Port_file_list[i];
                android.util.Log.i("zukgit", " ImgFile[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Common_Img_Port_file_list->size =" + 0);
        }
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.setCommon_Img_Port_Count_BackIndexList(Common_Img_Port_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Common_Img_Port_file_sum = " + Common_Img_Port_file_sum);
        }

    }


    public static void init_Wall_Land_Img() {
        int Top_Land_Img_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Wall_Land_Img_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_scene_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Wall_Land_Img_list_File.exists()) {
            Wall_Land_Img_file_list = Wall_Land_Img_list_File.listFiles();
            Wall_Land_Img_file_list= guolvType(Wall_Land_Img_file_list,".jpg");
            android.util.Log.i("zukgit", "A Wall_Land_Img_list_File=" + Wall_Land_Img_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Wall_Land_Img_list_File=" + Wall_Land_Img_list_File.exists());
        }

        if (Wall_Land_Img_file_list != null) {
            android.util.Log.i("zukgit", "A  Wall_Land_Img_list_File->size =" + Wall_Land_Img_file_list.length);
            Top_Land_Img_file_sum = Wall_Land_Img_file_list.length;
            for (int i = 0; i < Wall_Land_Img_file_list.length; i++) {
                File itemFile = Wall_Land_Img_file_list[i];
                android.util.Log.i("zukgit", " Wall_Land_Img_list_File[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Wall_Land_Img_list_File->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Wall_Land_Img_Init_BackIndexList(Top_Land_Img_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Top_Land_Img_file_sum = " + Top_Land_Img_file_sum);
        }

    }


    public static void init_Home_Land_Img() {
        int Top_Land_Img_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Home_Land_Img_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_home_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Home_Land_Img_list_File.exists()) {
            Home_Land_Img_file_list = Home_Land_Img_list_File.listFiles();
            Home_Land_Img_file_list= guolvType(Home_Land_Img_file_list,".jpg");
            android.util.Log.i("zukgit", "A Home_Land_Img_list_File=" + Home_Land_Img_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Home_Land_Img_list_File=" + Home_Land_Img_list_File.exists());
        }

        if (Home_Land_Img_file_list != null) {
            android.util.Log.i("zukgit", "A  Home_Land_Img_list_File->size =" + Home_Land_Img_file_list.length);
            Top_Land_Img_file_sum = Home_Land_Img_file_list.length;
            for (int i = 0; i < Home_Land_Img_file_list.length; i++) {
                File itemFile = Home_Land_Img_file_list[i];
                android.util.Log.i("zukgit", " Home_Land_Img_list_File[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Home_Land_Img_list_File->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Home_Land_Img_Init_BackIndexList(Top_Land_Img_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Top_Land_Img_file_sum = " + Top_Land_Img_file_sum);
        }

    }

    public static void init_Wall_Port_Img() {
        int Top_Port_Img_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Wall_Port_Img_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_scene_port");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Wall_Port_Img_list_File.exists()) {
            Wall_Port_Img_file_list = Wall_Port_Img_list_File.listFiles();
            Wall_Port_Img_file_list= guolvType(Wall_Port_Img_file_list,".jpg");
            android.util.Log.i("zukgit", "A Wall_Port_Img_list_File=" + Wall_Port_Img_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Wall_Port_Img_list_File=" + Wall_Port_Img_list_File.exists());
        }

        if (Wall_Port_Img_file_list != null) {
            android.util.Log.i("zukgit", "A  Wall_Port_Img_list_File->size =" + Wall_Port_Img_file_list.length);
            Top_Port_Img_file_sum = Wall_Port_Img_file_list.length;
            for (int i = 0; i < Wall_Port_Img_file_list.length; i++) {
                File itemFile = Wall_Port_Img_file_list[i];
                android.util.Log.i("zukgit", " Wall_Port_Img_list_File[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Wall_Port_Img_list_File->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Wall_Port_Img_Init_BackIndexList(Top_Port_Img_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Top_Port_Img_file_sum = " + Top_Port_Img_file_sum);
        }

    }

    public static void init_Gaokao_Land_Img() {
        int Top_Port_Img_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Gaokao_Land_Img_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_Gaokao_Land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Gaokao_Land_Img_list_File.exists()) {
            Gaokao_Land_Img_file_list = Gaokao_Land_Img_list_File.listFiles();
            Gaokao_Land_Img_file_list= guolvType(Gaokao_Land_Img_file_list,".jpg");

            android.util.Log.i("zukgit", "A Gaokao_Land_Img_list_File=" + Gaokao_Land_Img_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Gaokao_Land_Img_list_File=" + Gaokao_Land_Img_list_File.exists());
        }

        if (Gaokao_Land_Img_file_list != null) {
            android.util.Log.i("zukgit", "A  Gaokao_Land_Img_list_File->size =" + Gaokao_Land_Img_file_list.length);
            Top_Port_Img_file_sum = Gaokao_Land_Img_file_list.length;
            for (int i = 0; i < Gaokao_Land_Img_file_list.length; i++) {
                File itemFile = Gaokao_Land_Img_file_list[i];
                android.util.Log.i("zukgit", " Gaokao_Land_Img_list_File[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Gaokao_Land_Img_list_File->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Gaokao_Land_Img_Init_BackIndexList(Top_Port_Img_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Top_Port_Img_file_sum = " + Top_Port_Img_file_sum);
        }
        init_Gaokao_Land_Img_Artist();
    }



    public static void init_Kaoyan_Land_Img() {
        int Top_Port_Img_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Kaoyan_Land_Img_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_Kaoyan_Land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Kaoyan_Land_Img_list_File.exists()) {
            Kaoyan_Land_Img_file_list = Kaoyan_Land_Img_list_File.listFiles();
            Kaoyan_Land_Img_file_list= guolvType(Kaoyan_Land_Img_file_list,".jpg");

            android.util.Log.i("zukgit", "A Kaoyan_Land_Img_list_File=" + Kaoyan_Land_Img_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Kaoyan_Land_Img_list_File=" + Kaoyan_Land_Img_list_File.exists());
        }

        if (Kaoyan_Land_Img_file_list != null) {
            android.util.Log.i("zukgit", "A  Kaoyan_Land_Img_list_File->size =" + Kaoyan_Land_Img_file_list.length);
            Top_Port_Img_file_sum = Kaoyan_Land_Img_file_list.length;
            for (int i = 0; i < Kaoyan_Land_Img_file_list.length; i++) {
                File itemFile = Kaoyan_Land_Img_file_list[i];
                android.util.Log.i("zukgit", " Kaoyan_Land_Img_list_File[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Kaoyan_Land_Img_list_File->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Kaoyan_Land_Img_Init_BackIndexList(Top_Port_Img_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Top_Port_Img_file_sum = " + Top_Port_Img_file_sum);
        }


//        getExif_Artist_videomd5name

       init_Kaoyan_Land_Img_Artist();

    }

    public static void init_Kaoyan_Land_Img_Artist() {

        Kaoyan_Land_Img_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Kaoyan_Land_Img_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; // <三角函数,三角函数_7>
        Kaoyan_Land_Img_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合
        Kaoyan_Land_DirName_2_Category_FileArr_Map = new   HashMap<String,HashMap<String,ArrayList<File>>>();
        Kaoyan_Land_DirName_RadioTextList_Map  = new  HashMap<String,ArrayList<String>>();

        ArrayList<File> allMatchJpgFile = new    ArrayList<File>();


        if(Kaoyan_Land_Img_file_list != null && Kaoyan_Land_Img_file_list.length > 0){

            for (int i = 0; i < Kaoyan_Land_Img_file_list.length; i++) {
                File fileItem = Kaoyan_Land_Img_file_list[i];
                allMatchJpgFile.add(fileItem);
            }

            Kaoyan_Land_Img_KeyNameList.add(Kaoyan_Land_Img_MapKey);
            Kaoyan_Land_Img_KeyName_KeyNameSize_Map.put(Kaoyan_Land_Img_MapKey,Kaoyan_Land_Img_MapKey+"_"+allMatchJpgFile.size());
            Kaoyan_Land_Img_Category_FileArr_Map.put(Kaoyan_Land_Img_MapKey,allMatchJpgFile);




            for (int i = 0; i < Kaoyan_Land_Img_file_list.length; i++) {
                File matchJpgFIle = Kaoyan_Land_Img_file_list[i];
                JPGExifInfo jpegExifInfo = new JPGExifInfo(matchJpgFIle);
                String artistTag =  jpegExifInfo.mImageArtist_Utf8;
                if(artistTag == null || "".equals(artistTag.trim())){
                    continue;
                }

                if(!artistTag.contains("_")){  // 如果当前 artist 不包含 下划线

                    ArrayList<File> matchArtistFileArr =    Kaoyan_Land_Img_Category_FileArr_Map.get(artistTag);
                    if(matchArtistFileArr == null){
                        matchArtistFileArr = new   ArrayList<File>();
                        matchArtistFileArr.add(matchJpgFIle);
                        Kaoyan_Land_Img_Category_FileArr_Map.put(artistTag,matchArtistFileArr);

                    }else{
                        matchArtistFileArr.add(matchJpgFIle);
                    }

                } else{
                    // 如果当前 artist 包含 下划线
                    String[] singleArrArtist = artistTag.split("_");
                    if(singleArrArtist == null){
                        continue;
                    }

                   for2: for (int j = 0; j < singleArrArtist.length ; j++) {
                        String  singleOne =  singleArrArtist[j];
                        if(singleOne == null || "".equals(singleOne.trim())){
                            continue for2 ;
                        }
                        if(!Kaoyan_Land_Img_KeyNameList.contains(singleOne.trim())){
                            Kaoyan_Land_Img_KeyNameList.add(singleOne.trim());
                        }
                       ArrayList<File> matchArtistFileArr =    Kaoyan_Land_Img_Category_FileArr_Map.get(singleOne.trim());
                       if(matchArtistFileArr == null){
                           matchArtistFileArr = new   ArrayList<File>();
                           matchArtistFileArr.add(matchJpgFIle);
                           Kaoyan_Land_Img_Category_FileArr_Map.put(singleOne.trim(),matchArtistFileArr);

                       }else{
                           matchArtistFileArr.add(matchJpgFIle);
                       }
                    }
                }
            }
        }



   Kaoyan_Land_Img_SubDir_FileArr_Map = new HashMap<String,ArrayList<File>>() ;   // 子目录文件名  子目录对应的文件夹列表
   Kaoyan_Land_SubDir_NameList= new  ArrayList<String> ();// 子目录名称集合
   Kaoyan_Land_SubDir_NameList.add(Kaoyan_Land_RootDir_Key);
        if(Kaoyan_Land_Img_list_File != null){
            File[] all_Kaoyan_Land_fileArr =   Kaoyan_Land_Img_list_File.listFiles();
            if(all_Kaoyan_Land_fileArr != null && all_Kaoyan_Land_fileArr.length > 0 ){
                ArrayList<File> all_Kaoyan_Land_subDirFile_List = new  ArrayList<File>();
                for (int i = 0; i < all_Kaoyan_Land_fileArr.length ; i++) {
                    File subFileItem  = all_Kaoyan_Land_fileArr[i];
                    if(subFileItem.isDirectory() && subFileItem.listFiles() != null){
                        all_Kaoyan_Land_subDirFile_List.add(subFileItem);
                    }
                }

                for (int i = 0; i < all_Kaoyan_Land_subDirFile_List.size() ; i++) {
                    File subDirFile = all_Kaoyan_Land_subDirFile_List.get(i);
                    ArrayList<File> all_file_List = new  ArrayList<File>();
                    File[]  sun_file_arr = subDirFile.listFiles();
                    File[]  jpg_file_arr= guolvType(sun_file_arr,".jpg");
                    if(jpg_file_arr != null && jpg_file_arr.length > 0){

                        for (int j = 0; j < jpg_file_arr.length; j++) {
                            File fileItem = jpg_file_arr[j];
                            all_file_List.add(fileItem);
                        }

                        if(!Kaoyan_Land_Img_KeyNameList.contains(subDirFile.getName().trim())){
                            Kaoyan_Land_Img_KeyNameList.add(subDirFile.getName().trim());
                        }
                        if(!Kaoyan_Land_SubDir_NameList.contains(subDirFile.getName().trim())){
                            Kaoyan_Land_SubDir_NameList.add(subDirFile.getName().trim());
                        }
                        Kaoyan_Land_Img_Category_FileArr_Map.put(subDirFile.getName().trim(),all_file_List);
                        Kaoyan_Land_Img_SubDir_FileArr_Map.put(subDirFile.getName().trim(),all_file_List);
                    }
                }

            }


        }


        for (int i = 0; i <Kaoyan_Land_SubDir_NameList.size() ; i++) {
            String  subDirName = Kaoyan_Land_SubDir_NameList.get(i);
            ArrayList<File> fileArr = Kaoyan_Land_Img_SubDir_FileArr_Map.get(subDirName);  // 一个文件夹下的所有的文件名称
            ArrayList<File> dirAllFileList   = new    ArrayList<File>();

            if(fileArr != null && fileArr.size() > 0 ){
                String dirName = subDirName;
                HashMap<String,ArrayList<File>> oneDirFileArtrMap = new   HashMap<String,ArrayList<File>>();
                ArrayList<String>  realFileNameList = new  ArrayList<String>();

                for (int j = 0; j < fileArr.size() ; j++) {
                    File realFile = fileArr.get(j);
                    String fileName = realFile.getName().trim();
                    ArrayList<File> oneFileList = new    ArrayList<File>();
                    oneFileList.add(realFile);
                    oneDirFileArtrMap.put(fileName,oneFileList);
                    realFileNameList.add(fileName);
                    dirAllFileList.add(realFile);

                }
                oneDirFileArtrMap.put(subDirName,fileArr);
                realFileNameList.add(dirName);
                showString_ArrFileMap(oneDirFileArtrMap);
                // all--Kaoyan_Land_Img_Category_FileArr_Map        數學2考研---<文件名1第一題,ArrayList<File>只包含這一道題>
                Kaoyan_Land_DirName_2_Category_FileArr_Map.put(dirName,oneDirFileArtrMap);
                Kaoyan_Land_DirName_RadioTextList_Map.put(dirName,realFileNameList);

            }


        }






        for (int i = 0; i < Kaoyan_Land_Img_KeyNameList.size() ; i++) {
            String artistKey = Kaoyan_Land_Img_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Kaoyan_Land_Img_Category_FileArr_Map.get(artistKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Kaoyan_Land_Img_key["+i+"]["+Kaoyan_Land_Img_KeyNameList.size()+"] = "+ artistKey+" ArrSize="+matchFileArr.size());

//                Z_Top_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Kaoyan_Land_Img_KeyName_KeyNameSize_Map.put(artistKey+"_"+matchFileArr.size(),artistKey);
            }else{

                print("Kaoyan_Land_Img_key ["+i+"]["+Kaoyan_Land_Img_KeyNameList.size()+"] = "+ artistKey+" ArrSize=0");
            }

        }


        //  root 的 数据源
        Kaoyan_Land_DirName_2_Category_FileArr_Map.put(Kaoyan_Land_RootDir_Key,Kaoyan_Land_Img_Category_FileArr_Map);
        Kaoyan_Land_DirName_RadioTextList_Map.put(Kaoyan_Land_RootDir_Key,Kaoyan_Land_Img_KeyNameList);

        if(Kaoyan_Land_Img_file_list != null){
            Kaoyan_Land_Img_count =  Kaoyan_Land_Img_file_list.length;

            Kaoyan_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(Kaoyan_Land_Img_count);

        }

    }


    public static void init_Gaokao_Land_Img_Artist() {

        Gaokao_Land_Img_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Gaokao_Land_Img_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; // <三角函数,三角函数_7>
        Gaokao_Land_Img_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合
        Gaokao_Land_DirName_2_Category_FileArr_Map = new   HashMap<String,HashMap<String,ArrayList<File>>>();
        Gaokao_Land_DirName_RadioTextList_Map  = new  HashMap<String,ArrayList<String>>();

        ArrayList<File> allMatchJpgFile = new    ArrayList<File>();


        if(Gaokao_Land_Img_file_list != null && Gaokao_Land_Img_file_list.length > 0){

            for (int i = 0; i < Gaokao_Land_Img_file_list.length; i++) {
                File fileItem = Gaokao_Land_Img_file_list[i];
                allMatchJpgFile.add(fileItem);
            }

            Gaokao_Land_Img_KeyNameList.add(Gaokao_Land_Img_MapKey);
            Gaokao_Land_Img_KeyName_KeyNameSize_Map.put(Gaokao_Land_Img_MapKey,Gaokao_Land_Img_MapKey+"_"+allMatchJpgFile.size());
            Gaokao_Land_Img_Category_FileArr_Map.put(Gaokao_Land_Img_MapKey,allMatchJpgFile);




            for (int i = 0; i < Gaokao_Land_Img_file_list.length; i++) {
                File matchJpgFIle = Gaokao_Land_Img_file_list[i];
                JPGExifInfo jpegExifInfo = new JPGExifInfo(matchJpgFIle);
                String artistTag =  jpegExifInfo.mImageArtist_Utf8;
                if(artistTag == null || "".equals(artistTag.trim())){
                    continue;
                }

                if(!artistTag.contains("_")){  // 如果当前 artist 不包含 下划线

                    ArrayList<File> matchArtistFileArr =    Gaokao_Land_Img_Category_FileArr_Map.get(artistTag);
                    if(matchArtistFileArr == null){
                        matchArtistFileArr = new   ArrayList<File>();
                        matchArtistFileArr.add(matchJpgFIle);
                        Gaokao_Land_Img_Category_FileArr_Map.put(artistTag,matchArtistFileArr);

                    }else{
                        matchArtistFileArr.add(matchJpgFIle);
                    }

                } else{
                    // 如果当前 artist 包含 下划线
                    String[] singleArrArtist = artistTag.split("_");
                    if(singleArrArtist == null){
                        continue;
                    }

                    for2: for (int j = 0; j < singleArrArtist.length ; j++) {
                        String  singleOne =  singleArrArtist[j];
                        if(singleOne == null || "".equals(singleOne.trim())){
                            continue for2 ;
                        }
                        if(!Gaokao_Land_Img_KeyNameList.contains(singleOne.trim())){
                            Gaokao_Land_Img_KeyNameList.add(singleOne.trim());
                        }
                        ArrayList<File> matchArtistFileArr =    Gaokao_Land_Img_Category_FileArr_Map.get(singleOne.trim());
                        if(matchArtistFileArr == null){
                            matchArtistFileArr = new   ArrayList<File>();
                            matchArtistFileArr.add(matchJpgFIle);
                            Gaokao_Land_Img_Category_FileArr_Map.put(singleOne.trim(),matchArtistFileArr);

                        }else{
                            matchArtistFileArr.add(matchJpgFIle);
                        }
                    }
                }
            }
        }



        Gaokao_Land_Img_SubDir_FileArr_Map = new HashMap<String,ArrayList<File>>() ;   // 子目录文件名  子目录对应的文件夹列表
        Gaokao_Land_SubDir_NameList= new  ArrayList<String> ();// 子目录名称集合
        Gaokao_Land_SubDir_NameList.add(Gaokao_Land_RootDir_Key);
        if(Gaokao_Land_Img_list_File != null){
            File[] all_Gaokao_Land_fileArr =   Gaokao_Land_Img_list_File.listFiles();
            if(all_Gaokao_Land_fileArr != null && all_Gaokao_Land_fileArr.length > 0 ){
                ArrayList<File> all_Gaokao_Land_subDirFile_List = new  ArrayList<File>();
                for (int i = 0; i < all_Gaokao_Land_fileArr.length ; i++) {
                    File subFileItem  = all_Gaokao_Land_fileArr[i];
                    if(subFileItem.isDirectory() && subFileItem.listFiles() != null){
                        all_Gaokao_Land_subDirFile_List.add(subFileItem);
                    }
                }

                for (int i = 0; i < all_Gaokao_Land_subDirFile_List.size() ; i++) {
                    File subDirFile = all_Gaokao_Land_subDirFile_List.get(i);
                    ArrayList<File> all_file_List = new  ArrayList<File>();
                    File[]  sun_file_arr = subDirFile.listFiles();
                    File[]  jpg_file_arr= guolvType(sun_file_arr,".jpg");
                    if(jpg_file_arr != null && jpg_file_arr.length > 0){

                        for (int j = 0; j < jpg_file_arr.length; j++) {
                            File fileItem = jpg_file_arr[j];
                            all_file_List.add(fileItem);
                        }

                        if(!Gaokao_Land_Img_KeyNameList.contains(subDirFile.getName().trim())){
                            Gaokao_Land_Img_KeyNameList.add(subDirFile.getName().trim());
                        }
                        if(!Gaokao_Land_SubDir_NameList.contains(subDirFile.getName().trim())){
                            Gaokao_Land_SubDir_NameList.add(subDirFile.getName().trim());
                        }
                        Gaokao_Land_Img_Category_FileArr_Map.put(subDirFile.getName().trim(),all_file_List);
                        Gaokao_Land_Img_SubDir_FileArr_Map.put(subDirFile.getName().trim(),all_file_List);
                    }
                }

            }


        }


        for (int i = 0; i <Gaokao_Land_SubDir_NameList.size() ; i++) {
            String  subDirName = Gaokao_Land_SubDir_NameList.get(i);
            ArrayList<File> fileArr = Gaokao_Land_Img_SubDir_FileArr_Map.get(subDirName);  // 一个文件夹下的所有的文件名称

            if(fileArr != null && fileArr.size() > 0 ){
                String dirName = subDirName;
                HashMap<String,ArrayList<File>> oneDirFileArtrMap = new   HashMap<String,ArrayList<File>>();
                ArrayList<String>  realFileNameList = new  ArrayList<String>();
                ArrayList<File> dirAllFileList   = new    ArrayList<File>();



                for (int j = 0; j < fileArr.size() ; j++) {
                    File realFile = fileArr.get(j);
                    String fileName = realFile.getName().trim();
                    ArrayList<File> oneFileList = new    ArrayList<File>();
                    oneFileList.add(realFile);
                    oneDirFileArtrMap.put(fileName,oneFileList);
                    realFileNameList.add(fileName);
                    dirAllFileList.add(realFile);
                }
                oneDirFileArtrMap.put(dirName,dirAllFileList);
                // all--Gaokao_Land_Img_Category_FileArr_Map
                // 數學2考研---<文件名1第一題,ArrayList<File>只包含這一道題>
               //  數學2考研---<數學2考研,ArrayList<File>所有的题>
                Gaokao_Land_DirName_2_Category_FileArr_Map.put(dirName,oneDirFileArtrMap);
                showString_ArrFileMap(oneDirFileArtrMap);
                realFileNameList.add(dirName);
                Gaokao_Land_DirName_RadioTextList_Map.put(dirName,realFileNameList);

            }


        }






        for (int i = 0; i < Gaokao_Land_Img_KeyNameList.size() ; i++) {
            String artistKey = Gaokao_Land_Img_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Gaokao_Land_Img_Category_FileArr_Map.get(artistKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Gaokao_Land_Img_key["+i+"]["+Gaokao_Land_Img_KeyNameList.size()+"] = "+ artistKey+" ArrSize="+matchFileArr.size());

//                Z_Top_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Gaokao_Land_Img_KeyName_KeyNameSize_Map.put(artistKey+"_"+matchFileArr.size(),artistKey);
            }else{

                print("Gaokao_Land_Img_key ["+i+"]["+Gaokao_Land_Img_KeyNameList.size()+"] = "+ artistKey+" ArrSize=0");
            }

        }


        //  root 的 数据源
        Gaokao_Land_DirName_2_Category_FileArr_Map.put(Gaokao_Land_RootDir_Key,Gaokao_Land_Img_Category_FileArr_Map);
        Gaokao_Land_DirName_RadioTextList_Map.put(Gaokao_Land_RootDir_Key,Gaokao_Land_Img_KeyNameList);

        if(Gaokao_Land_Img_file_list != null){
            Gaokao_Land_Img_count =  Gaokao_Land_Img_file_list.length;

            Gaokao_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(Gaokao_Land_Img_count);

        }

    }


    public static void init_Gaokao_Land_Img_Artist_OLD() {

        Gaokao_Land_Img_KeyNameList = new   ArrayList<String> ();   // all   gbxxx
        Gaokao_Land_Img_KeyName_KeyNameSize_Map = new  HashMap<String,String> ();  ; // <三角函数,三角函数_7>
        Gaokao_Land_Img_Category_FileArr_Map = new HashMap<String,ArrayList<File>>() ;  //  // <三角函数,ArrayList<FIle>> 对应的 图片的集合


        ArrayList<File> allMatchJpgFile = new    ArrayList<File>();


        if(Gaokao_Land_Img_file_list != null && Gaokao_Land_Img_file_list.length > 0){

            for (int i = 0; i < Gaokao_Land_Img_file_list.length; i++) {
                File fileItem = Gaokao_Land_Img_file_list[i];
                allMatchJpgFile.add(fileItem);
            }

            Gaokao_Land_Img_KeyNameList.add(Gaokao_Land_Img_MapKey);
            Gaokao_Land_Img_KeyName_KeyNameSize_Map.put(Gaokao_Land_Img_MapKey,Gaokao_Land_Img_MapKey+"_"+allMatchJpgFile.size());
            Gaokao_Land_Img_Category_FileArr_Map.put(Gaokao_Land_Img_MapKey,allMatchJpgFile);




            for (int i = 0; i < Gaokao_Land_Img_file_list.length; i++) {
                File matchJpgFIle = Gaokao_Land_Img_file_list[i];
//                String artistTag =  getExif_Artist(matchJpgFIle);

                JPGExifInfo jpegExifInfo = new JPGExifInfo(matchJpgFIle);
                String artistTag =  jpegExifInfo.mImageArtist_Utf8;

                if(artistTag == null || "".equals(artistTag.trim())){
                    continue;
                }

                if(!artistTag.contains("_")){  // 如果当前 artist 不包含 下划线

                    ArrayList<File> matchArtistFileArr =    Gaokao_Land_Img_Category_FileArr_Map.get(artistTag);
                    if(matchArtistFileArr == null){
                        matchArtistFileArr = new   ArrayList<File>();
                        matchArtistFileArr.add(matchJpgFIle);
                        Gaokao_Land_Img_Category_FileArr_Map.put(artistTag,matchArtistFileArr);

                    }else{
                        matchArtistFileArr.add(matchJpgFIle);
                    }

                } else{
                    // 如果当前 artist 包含 下划线
                    String[] singleArrArtist = artistTag.split("_");
                    if(singleArrArtist == null){
                        continue;
                    }

                    for2: for (int j = 0; j < singleArrArtist.length ; j++) {
                        String  singleOne =  singleArrArtist[j];
                        if(singleOne == null || "".equals(singleOne.trim())){
                            continue for2 ;
                        }
                        if(!Gaokao_Land_Img_KeyNameList.contains(singleOne.trim())){
                            Gaokao_Land_Img_KeyNameList.add(singleOne.trim());
                        }
                        ArrayList<File> matchArtistFileArr =    Gaokao_Land_Img_Category_FileArr_Map.get(singleOne.trim());
                        if(matchArtistFileArr == null){
                            matchArtistFileArr = new   ArrayList<File>();
                            matchArtistFileArr.add(matchJpgFIle);
                            Gaokao_Land_Img_Category_FileArr_Map.put(singleOne.trim(),matchArtistFileArr);

                        }else{
                            matchArtistFileArr.add(matchJpgFIle);
                        }
                    }
                }
            }
        }else{
            print("Gaokao_Land_Img_key" + "+matchFileArr.size() == null  Gaokao_Land_Img_KeyNameList.size() ="+ Gaokao_Land_Img_KeyNameList.size() );

        }



        Gaokao_Land_Img_SubDir_FileArr_Map = new HashMap<String,ArrayList<File>>() ;   // 子目录文件名  子目录对应的文件夹列表
        Gaokao_Land_SubDir_NameList= new  ArrayList<String> ();// 子目录名称集合
        if(Gaokao_Land_Img_list_File != null){
            File[] all_Gaokao_Land_fileArr =   Gaokao_Land_Img_list_File.listFiles();
            if(all_Gaokao_Land_fileArr != null && all_Gaokao_Land_fileArr.length > 0 ){
                ArrayList<File> all_Gaokao_Land_subDirFile_List = new  ArrayList<File>();
                for (int i = 0; i < all_Gaokao_Land_fileArr.length ; i++) {
                    File subFileItem  = all_Gaokao_Land_fileArr[i];
                    if(subFileItem.isDirectory() && subFileItem.listFiles() != null){
                        all_Gaokao_Land_subDirFile_List.add(subFileItem);
                    }
                }

                for (int i = 0; i < all_Gaokao_Land_subDirFile_List.size() ; i++) {
                    File subDirFile = all_Gaokao_Land_subDirFile_List.get(i);
                    ArrayList<File> all_file_List = new  ArrayList<File>();
                    File[]  sun_file_arr = subDirFile.listFiles();
                    File[]  jpg_file_arr= guolvType(sun_file_arr,".jpg");
                    if(jpg_file_arr != null && jpg_file_arr.length > 0){

                        for (int j = 0; j < jpg_file_arr.length; j++) {
                            File fileItem = jpg_file_arr[i];
                            all_file_List.add(fileItem);
                        }

                        if(!Gaokao_Land_Img_KeyNameList.contains(subDirFile.getName().trim())){
                            Gaokao_Land_Img_KeyNameList.add(subDirFile.getName().trim());
                        }
                        if(!Gaokao_Land_SubDir_NameList.contains(subDirFile.getName().trim())){
                            Gaokao_Land_SubDir_NameList.add(subDirFile.getName().trim());
                        }
                        Gaokao_Land_Img_Category_FileArr_Map.put(subDirFile.getName().trim(),all_file_List);
                        Gaokao_Land_Img_SubDir_FileArr_Map.put(subDirFile.getName().trim(),all_file_List);
                    }
                }

            }


        }



        for (int i = 0; i < Gaokao_Land_Img_KeyNameList.size() ; i++) {
            String artistKey = Gaokao_Land_Img_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    Gaokao_Land_Img_Category_FileArr_Map.get(artistKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Gaokao_Land_Img_key["+i+"]["+Gaokao_Land_Img_KeyNameList.size()+"] = "+ artistKey+" ArrSize="+matchFileArr.size());

//                Z_Top_KeyName_KeyNameSize_map.put(ssKey+"_"+matchFileArr.size(),ssKey);

                Gaokao_Land_Img_KeyName_KeyNameSize_Map.put(artistKey+"_"+matchFileArr.size(),artistKey);
            }else{

                print("Gaokao_Land_Img_key ["+i+"]["+Gaokao_Land_Img_KeyNameList.size()+"] = "+ artistKey+" ArrSize=0");
            }

        }


        if(Gaokao_Land_Img_file_list != null){
            Gaokao_Land_Img_count =  Gaokao_Land_Img_file_list.length;
            Gaokao_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(Gaokao_Land_Img_count);
        }



    }


    public  static void showString_ArrFileMap(HashMap<String, ArrayList<File>> params){
        int i = 1;
        for (Map.Entry<String,  ArrayList<File>> entry : params.entrySet()) {
            String keyStr = entry.getKey();
            ArrayList<File> arrFileList =  entry.getValue();
            if(arrFileList == null){
                continue;
            }
            for (int j = 0; j < arrFileList.size(); j++) {
                File file = arrFileList.get(j);
                print("showString_ArrFileMap["+i+"]   ArrFile["+ arrFileList.size()+"]["+j+"]  Key["+keyStr+"]  Value["+file.getName()+"]");
            }


            i++;

        }

    }




    public  static void showStringMap(HashMap<String, String> params){
        int i = 1;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String keyStr = entry.getKey();
            String base64Str =  entry.getValue();

            print("showStringMap["+i+"]  Key["+keyStr+"]  Value["+base64Str+"]");
            i++;

        }

    }



    public static ArrayList<Integer> Kaoyan_Land_Img_Init_BackIndexList(int mGif_count) {
        Kaoyan_Land_Img_count = mGif_count;
        Kaoyan_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Kaoyan_Land_Img_random_indexlist;
    }


    public static ArrayList<Integer> Gaokao_Land_Img_Init_BackIndexList(int mGif_count) {
        Gaokao_Land_Img_count = mGif_count;
        Gaokao_Land_Img_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Gaokao_Land_Img_random_indexlist;
    }


    public static void init_Home_Port_Img() {
        int Top_Port_Img_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Home_Port_Img_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "jpg_home_port");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Home_Port_Img_list_File.exists()) {
            Home_Port_Img_file_list = Home_Port_Img_list_File.listFiles();
            Home_Port_Img_file_list= guolvType(Home_Port_Img_file_list,".jpg");

            android.util.Log.i("zukgit", "A Home_Port_Img_list_File=" + Home_Port_Img_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Home_Port_Img_list_File=" + Home_Port_Img_list_File.exists());
        }

        if (Home_Port_Img_file_list != null) {
            android.util.Log.i("zukgit", "A  Home_Port_Img_list_File->size =" + Home_Port_Img_file_list.length);
            Top_Port_Img_file_sum = Home_Port_Img_file_list.length;
            for (int i = 0; i < Home_Port_Img_file_list.length; i++) {
                File itemFile = Home_Port_Img_file_list[i];
                android.util.Log.i("zukgit", " Home_Port_Img_list_File[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Home_Port_Img_list_File->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Home_Port_Img_Init_BackIndexList(Top_Port_Img_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Top_Port_Img_file_sum = " + Top_Port_Img_file_sum);
        }

    }




    public static ArrayList<Integer> Home_Port_Img_Init_BackIndexList(int mGif_count) {
        Home_Port_Img_count = mGif_count;
        Home_Port_Img_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Home_Port_Img_random_indexlist;
    }


    public static ArrayList<Integer> Wall_Port_Img_Init_BackIndexList(int mGif_count) {
        Wall_Port_Img_count = mGif_count;
        Wall_Port_Img_random_indexlist = RandomUtil.getRandomLengthArr(mGif_count);
        return Wall_Port_Img_random_indexlist;
    }


    public static void init_Top_Port_Gif() {
        int Top_Port_gif_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Top_Port_Gif_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "gif_common_port");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Top_Port_Gif_list_File.exists()) {
            Top_Port_Gif_file_list = Top_Port_Gif_list_File.listFiles();
            Top_Port_Gif_file_list = guolvType(Top_Port_Gif_file_list,".gif");
            android.util.Log.i("zukgit", "A Top_Port_gif_file_list=" + Top_Port_Gif_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Top_Port_gif_file_list=" + Top_Port_Gif_list_File.exists());
        }

        if (Top_Port_Gif_file_list != null) {
            android.util.Log.i("zukgit", "A  Top_Land_gif_file_list->size =" + Top_Port_Gif_file_list.length);
            Top_Port_gif_file_sum = Top_Port_Gif_file_list.length;
            for (int i = 0; i < Top_Port_Gif_file_list.length; i++) {
                File itemFile = Top_Port_Gif_file_list[i];
                android.util.Log.i("zukgit", " Top_Port_gifFile[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Top_Port_gif_file_list->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Wall_Port_Gif_Init_BackIndexList(Top_Port_gif_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Top_Port_gif_file_sum = " + Top_Port_gif_file_sum);
        }

    }


    public static void init_Top_Land_Gif() {
        int Top_Land_gif_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Top_Land_Gif_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "gif_common_land");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Top_Land_Gif_list_File.exists()) {
            Top_Land_Gif_file_list = Top_Land_Gif_list_File.listFiles();
            Top_Land_Gif_file_list = guolvType(Top_Land_Gif_file_list,".gif");

            android.util.Log.i("zukgit", "A Top_Land_gif_file_list=" + Top_Land_Gif_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B Top_Land_gif_file_list=" + Top_Land_Gif_list_File.exists());
        }

        if (Top_Land_Gif_file_list != null) {
            android.util.Log.i("zukgit", "A  Top_Land_gif_file_list->size =" + Top_Land_Gif_file_list.length);
            Top_Land_gif_file_sum = Top_Land_Gif_file_list.length;
            for (int i = 0; i < Top_Land_Gif_file_list.length; i++) {
                File itemFile = Top_Land_Gif_file_list[i];
                android.util.Log.i("zukgit", " Top_Land_gifFile[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  Top_Land_gif_file_list->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Top_Land_Gif_Init_BackIndexList(Top_Land_gif_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! Top_Land_gif_file_sum = " + Top_Land_gif_file_sum);
        }

    }

    public static void init_Gif() {
        int gif_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        Gif_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "gif");

        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (Gif_list_File.exists()) {
            Gif_file_list = Gif_list_File.listFiles();
            Gif_file_list = guolvType(Gif_file_list,".gif");
            android.util.Log.i("zukgit", "A gif_file_list=" + Gif_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B gif_file_list=" + Gif_list_File.exists());
        }

        if (Gif_file_list != null) {
            android.util.Log.i("zukgit", "A  gif_file_list->size =" + Gif_file_list.length);
            gif_file_sum = Gif_file_list.length;
            for (int i = 0; i < Gif_file_list.length; i++) {
                File itemFile = Gif_file_list[i];
                android.util.Log.i("zukgit", " gifFile[" + i + "] = " + itemFile.getAbsolutePath());
            }
        } else {
            android.util.Log.i("zukgit", "B  gif_file_list->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.Gif_Init_BackIndexList(gif_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! gif_file_sum = " + gif_file_sum);
        }


    }


    public static void init_MP3() {
        int MP3_file_sum = 0;
        Sdcard_File = initSD_Root_File();
        ZMain_File = new File(Sdcard_File.getAbsoluteFile() + File.separator + "zmain");

        MP3_list_File = new File(ZMain_File.getAbsoluteFile() + File.separator + "mp3");

        mp3Json_File  = new File(MP3_list_File.getAbsoluteFile() + File.separator + "mp3.json");

        MP3_Node2_CatageryNameList = new ArrayList<String>();
        MP3_Node2_UserDefine_NameList = new ArrayList<String>();
        init_UserDefine_Lev2_Category_Name();
        if (!ZMain_File.exists()) {
            ZMain_File.mkdirs();
            android.util.Log.i("zukgit", " ZMain_File=" + ZMain_File.exists());

        }

        if (MP3_list_File.exists()) {
            MP3_file_list = MP3_list_File.listFiles();
            ArrayList<String> mp3TypeList  = new  ArrayList<String>();
            mp3TypeList.add(".mp3");
//            mp3TypeList.add(".m4a");

            MP3_file_list= guolvType(MP3_file_list,mp3TypeList);
            android.util.Log.i("zukgit", "A MP3_file_list=" + MP3_list_File.exists());
        } else {
            android.util.Log.i("zukgit", "B MP3_file_list=" + MP3_list_File.exists());
        }

        if (MP3_file_list != null) {
            android.util.Log.i("zukgit", "A  MP3_file_list->size =" + MP3_file_list.length);
            MP3_file_sum = MP3_file_list.length;
            for (int i = 0; i < MP3_file_list.length; i++) {
                File itemFile = MP3_file_list[i];
                android.util.Log.i("zukgit", " MP3File[" + i + "] = " + itemFile.getAbsolutePath() +"  MP3_file_list.length="+MP3_file_list.length);
            }
        } else {
            android.util.Log.i("zukgit", "B  MP3_file_list->size =" + 0);
        }

        new DataCreate().initData();
        new DataCreate().initData();
        if (DataHolder.MP3_Init_BackIndexList(MP3_file_sum) == null) {
            android.util.Log.i("zukgit", " 无法搜索到文件!! MP3_file_sum = " + MP3_file_sum);
        }


    }





    public synchronized static int getNextNodeID() {
        Node_ID_Num++;
        return Node_ID_Num;
    }

    static String[] Alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    static int getIndexFromAlphabet(String atristName) {
        int index = -1;
        for (int i = 0; i < Alphabet.length; i++) {
            String firstTag = Alphabet[i];
            if (atristName.equals(firstTag)) {
                return i + 1;
            }
        }
        return index;

    }

    public synchronized static String getZmainMp3Json() {
        if (mp3Json != null) {
            return mp3Json;
        }

        if (MP3_file_list == null || MP3_file_list.length == 0) {
            android.util.Log.i("json_result", "没有读取到  /sdcard/zmain/mp3/目录下的文件 或者该目录下文件为0 !");
            return RootNodeImpl.json();
        }

        if (allMp3FileList == null || allMp3FileList.size() == 0) {

            allMp3FileList = new ArrayList<File>();


            for (int i = 0; i < MP3_file_list.length; i++) {
                File mp3File_item = MP3_file_list[i];
                String mp3FileName = mp3File_item.getName().toLowerCase();
                if (mp3FileName.endsWith(".mp3")) {
                    allMp3FileList.add(mp3File_item);
                }

            }

        }


        if (level_1_alphabet_nodeList == null) {
            level_1_alphabet_nodeList = new ArrayList<NodeImpl>();
        }
        if (level_2_artistism_nodeList == null) {
            level_2_artistism_nodeList = new ArrayList<NodeImpl>();
        }



        if (level_3_song_nodeList == null) {
            level_3_song_nodeList = new ArrayList<NodeImpl>();
        }

        if (level_3_song_nodeList_quchong == null) {
            level_3_song_nodeList_quchong = new ArrayList<NodeImpl>();
        }




        //  判断 mp3_node.json 中的All 中的个数  和  MP3_file_list.length 的个数 是否一样
        String mp3JsonInMp3JsonFile = ReadFileContent(mp3Json_File);

        int all_count_In_Mp3JsonFile = get_A_All_Count_InJson(mp3JsonInMp3JsonFile);

        if(all_count_In_Mp3JsonFile == allMp3FileList.size()){  //  如果  数量 一致  那么 直接返回 json
            mp3Json =   mp3JsonInMp3JsonFile ;
            return mp3JsonInMp3JsonFile;
        }








        ArrayList<NodeImpl> alphabet_node_list = new ArrayList<NodeImpl>();


        for (int i = 0; i < Alphabet.length; i++) {
            String alphaItem = Alphabet[i];


            NodeImpl Rule34_RootNodeImpl = new NodeImpl(getNextNodeID(), 0, alphaItem, 0, 1, null);
            alphabet_node_list.add(Rule34_RootNodeImpl);
        }
        RootNodeImpl.children.addAll(alphabet_node_list);
        //  把 当前 目录下的 mp3  依据  作者分类 分好

        mArtist_NodeImpl_Map = new LinkedHashMap<String, ArrayList<NodeImpl>>();
        mUserDefine_Category_Level2_NodeImpl_Map = new LinkedHashMap<String, ArrayList<NodeImpl>>();

        // 依据 备注 进行 分类 得到的 Item  粤语 英语 纯语  更多分类 留下扩展
        mComment_File_Map = new LinkedHashMap<String, ArrayList<NodeImpl>>();


        for (int i = 0; i < allMp3FileList.size(); i++) {

            try {
                File File_realMP3 = allMp3FileList.get(i);

                String mp3File_Path = File_realMP3.getAbsolutePath();
//                Mp3File mp3file_item = new Mp3File(allMp3FileList.get(i).getAbsolutePath());

                if(DataHolder.System_SDK_Version >= 26){
                    Mp3File mp3file_item = new Mp3File(File_realMP3);
                    if (mp3file_item.hasId3v2Tag()) {
                        ID3v2 id3v2Tag = mp3file_item.getId3v2Tag();


//                        String titleName_tag = id3v2Tag.getTitle().trim();
//                        String ArtistName_tag = id3v2Tag.getArtist().trim();
//                        String CommentName_tag = id3v2Tag.getComment().trim();
//                        String firstStr = getFirstZiMu(ArtistName_tag);



                        String id3v2Tag_artist =  id3v2Tag.getArtist() != null ? id3v2Tag.getArtist().trim():null;
                        String id3v2Tag_songname = id3v2Tag.getTitle() != null ? id3v2Tag.getTitle().trim():null;
                        String id3v2Tag_comment = id3v2Tag.getComment() != null ? id3v2Tag.getComment().trim():null;
                        String firstStr = getFirstZiMu(id3v2Tag_artist);
                        if(id3v2Tag_comment != null) {
//                            id3v2Tag_comment =   clearLuanMaForCommantTag(id3v2Tag_comment.getBytes());
                        }



                        if(id3v2Tag_artist == null || id3v2Tag_songname == null || "".equals(id3v2Tag_artist) || "".equals(id3v2Tag_songname) ){
                            //  解决好 MP3的 Comment 的读取正常时  再打开   Lower25_SDK_SongInfo的comment 读取 有问题   没读取到文件  那么 跳过
                            android.util.Log.i("zukgitSFailed", "index=["+i+"] file=["+File_realMP3.getAbsolutePath()+"]  title=["+ id3v2Tag_songname +  "] artist=[" +id3v2Tag_artist + "]  comment=[" + id3v2Tag_comment+"]");

                            continue;
                        }

                        NodeImpl nodeImp = null;
                        if (mArtist_NodeImpl_Map.containsKey(id3v2Tag_artist)) {
                            ArrayList<NodeImpl> curArr = mArtist_NodeImpl_Map.get(id3v2Tag_artist);
                            nodeImp = new NodeImpl(getNextNodeID(), getIndexFromAlphabet(firstStr), id3v2Tag_songname, 0, 3, mp3File_Path);
                            nodeImp.setArtist(id3v2Tag_artist);
                            curArr.add(nodeImp);
                        } else {
                            ArrayList<NodeImpl> targetArr = new ArrayList<NodeImpl>();
                            nodeImp = new NodeImpl(getNextNodeID(), getIndexFromAlphabet(firstStr), id3v2Tag_songname, 0, 3, mp3File_Path);
                            targetArr.add(nodeImp);
                            nodeImp.setArtist(id3v2Tag_artist);
                            mArtist_NodeImpl_Map.put(id3v2Tag_artist, targetArr);
                        }


                        // 为 A_ALL准备
                        if (nodeImp != null && !level_3_song_nodeList_quchong.contains(nodeImp)) {
                            nodeImp = new NodeImpl(getNextNodeID(), getIndexFromAlphabet("A"), id3v2Tag_songname, 0, 3, mp3File_Path);
                            nodeImp.setArtist(id3v2Tag_artist);
                            level_3_song_nodeList_quchong.add(nodeImp);
                        }
                        android.util.Log.i("zukgit_UP26", "index=["+i+"] file=["+File_realMP3.getAbsolutePath()+"]  title=["+ id3v2Tag_songname +  "] artist=[" +id3v2Tag_artist + "]  comment=[" + id3v2Tag_comment+"]");

                        for (int j = 0; j <  MP3_Node2_UserDefine_NameList.size(); j++) {
                            initSongWithCommentForMap(MP3_Node2_UserDefine_NameList.get(j), id3v2Tag_comment, id3v2Tag_songname, id3v2Tag_artist ,File_realMP3, mArtist_NodeImpl_Map, mComment_File_Map);
                        }

                    }
                }else{   // 小于 26  比如 魅蓝note6是 25    使用 Mp3File会报错
/*                     Uri mprUri =  MediaStore.Files.getContentUri(File_realMP3.getAbsolutePath());


                        MediaMetadataRetriever xMediaMetadataRetriever = new MediaMetadataRetriever();
                        xMediaMetadataRetriever.setDataSource(mp3File_Path);
                        String title = xMediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

                        String artist = xMediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                        String firstStr_title = getFirstZiMu(title);*/

                    RandomAccessFile raFile = new RandomAccessFile(File_realMP3, "r");
                    byte[] buffer = new byte[128];

                    raFile.seek(raFile.length() - 128);
                    raFile.read(buffer);
                    Lower25_SDK_SongInfo mp3Info = new Lower25_SDK_SongInfo(buffer);
                    String titleName_tag = mp3Info.getSongName();
                    String ArtistName_tag =  mp3Info.getArtist();
                    String CommentName_tag = mp3Info.getComment();
                    String firstStr = getFirstZiMu(ArtistName_tag);
                    if("".equals(titleName_tag) || "".equals(ArtistName_tag) ){
                        //  解决好 MP3的 Comment 的读取正常时  再打开   Lower25_SDK_SongInfo的comment 读取 有问题   没读取到文件  那么 跳过
                        android.util.Log.i("zukgit_Lowe_", "index=["+i+"] file=["+File_realMP3.getAbsolutePath()+"]  title=["+ mp3Info.getSongName() +  "] artist=[" + mp3Info.getArtist() + "]  comment=[" + mp3Info.getComment()+"]");

                        continue;
                    }
                    NodeImpl nodeImp = null;
                    if (mArtist_NodeImpl_Map.containsKey(ArtistName_tag)) {
                        ArrayList<NodeImpl> curArr = mArtist_NodeImpl_Map.get(ArtistName_tag);
                        nodeImp = new NodeImpl(getNextNodeID(), getIndexFromAlphabet(firstStr), titleName_tag, 0, 3, mp3File_Path);
                        curArr.add(nodeImp);
                    } else {
                        ArrayList<NodeImpl> targetArr = new ArrayList<NodeImpl>();
                        nodeImp = new NodeImpl(getNextNodeID(), getIndexFromAlphabet(firstStr), titleName_tag, 0, 3, mp3File_Path);
                        targetArr.add(nodeImp);
                        mArtist_NodeImpl_Map.put(ArtistName_tag, targetArr);
                    }

                    // 为 A_ALL准备
                    if (nodeImp != null && !level_3_song_nodeList_quchong.contains(nodeImp)) {
                        nodeImp = new NodeImpl(getNextNodeID(), getIndexFromAlphabet("A"), titleName_tag, 0, 3, mp3File_Path);
                        level_3_song_nodeList_quchong.add(nodeImp);
                    }

                    for (int j = 0; j <  MP3_Node2_UserDefine_NameList.size(); j++) {
                        initSongWithCommentForMap(MP3_Node2_UserDefine_NameList.get(j),  CommentName_tag, titleName_tag,ArtistName_tag, File_realMP3, mArtist_NodeImpl_Map, mComment_File_Map);
                    }

                    android.util.Log.i("zukgSongInfo", "index=["+i+"] file=["+File_realMP3.getAbsolutePath()+"]  title=["+ mp3Info.getSongName() +  "] artist=[" + mp3Info.getArtist() + "]  comment=[" + mp3Info.getComment()+"]");

                }

            } catch (Exception e) {
                System.out.println("RuleIndex 34  解析MP3文件出现错误! ");

            }
        }
        //  添加一个 A--字母下的 ALL 的 列表  标示  所有 音乐文件   使得 清除当前已经 有多少个音乐文件了
        add_A_All_Node(mArtist_NodeImpl_Map, level_3_song_nodeList_quchong);

        mArtist_NodeImpl_Map = sortHashMap(mArtist_NodeImpl_Map);
        Show_AddNode_MP3Map(mArtist_NodeImpl_Map, alphabet_node_list);


        if(System_SDK_Version >= 25) {
            SortNodeWithName(level_2_artistism_nodeList);
        }
        addLevel3_IndexInParent(level_2_artistism_nodeList);
        mp3Json = getRootNodeContent(RootNodeImpl);


      //    showCommenNodeImpltMap(mComment_File_Map);

        android.util.Log.i("zukgit", "   作者数量  mArtist_NodeImpl_Map.size() = " + mArtist_NodeImpl_Map.size() + "  所有结点歌曲数量(有重)=" + level_3_song_nodeList.size() + "  所有歌曲数量(去重)=" + level_3_song_nodeList_quchong.size());

        writeContentToFile(DataHolder.mp3Json_File , mp3Json);

        return mp3Json;

    }


    static void init_UserDefine_Lev2_Category_Name(){



        MP3_Node2_UserDefine_NameList.add("国语");
        MP3_Node2_UserDefine_NameList.add("粤语");
        MP3_Node2_UserDefine_NameList.add("纯语");
        MP3_Node2_UserDefine_NameList.add("英语");
        MP3_Node2_UserDefine_NameList.add("喜欢");
        MP3_Node2_UserDefine_NameList.add("古风");
        MP3_Node2_UserDefine_NameList.add("民谣");
        MP3_Node2_UserDefine_NameList.add("DJ" );
        MP3_Node2_UserDefine_NameList.add("家" );
        MP3_Node2_UserDefine_NameList.add("韩语");
        MP3_Node2_UserDefine_NameList.add("日语");
        MP3_Node2_UserDefine_NameList.add("抖音");
        MP3_Node2_UserDefine_NameList.add("吴语");
        MP3_Node2_UserDefine_NameList.add("闽语");
        MP3_Node2_UserDefine_NameList.add("法语");
        MP3_Node2_UserDefine_NameList.add("俄语");
        MP3_Node2_UserDefine_NameList.add("京剧");
        MP3_Node2_UserDefine_NameList.add("越南语");
        MP3_Node2_UserDefine_NameList.add("泰语");
        MP3_Node2_UserDefine_NameList.add("德语");
        MP3_Node2_UserDefine_NameList.add("意大利");

    }

    static void addLevel3_IndexInParent(ArrayList<NodeImpl> level2NodeList){
        android.util.Log.i("addLevel3_IndexInParent","level2NodeList.size() = "+level2NodeList.size());
        for (int i = 0; i < level2NodeList.size(); i++) {
            NodeImpl node2 = level2NodeList.get(i);
            android.util.Log.i("addLevel3_IndexInParent","------------------------------"+"node2["+i+"] nodeName["+node2.name+"] Begin -----------------------------");

            android.util.Log.i("addLevel3_IndexInParent","node2["+i+"] = "+ node2.toString());
            ArrayList<NodeImpl> node3List = (ArrayList<NodeImpl>)node2.children;
            if(node3List != null){

                for (int j = 0; j < node3List.size(); j++) {

                    NodeImpl node3 = node3List.get(j);
                    android.util.Log.i("addLevel3_IndexInParent","node2["+i+"]_node3["+j+"] = "+ node3.toString());
                    node3.setindex4parent(j);
                }
            }
            android.util.Log.i("addLevel3_IndexInParent","------------------------------"+"node2["+i+"] nodeName["+node2.name+"] End -----------------------------");

        }

    }


    static void add_A_All_Node(LinkedHashMap<String, ArrayList<NodeImpl>> artistName_MP3_FileMap, ArrayList<NodeImpl> A_All_NodeImpArr) {

        if (artistName_MP3_FileMap != null) {
            artistName_MP3_FileMap.put("A_All", A_All_NodeImpArr);
            if(!MP3_Node2_CatageryNameList.contains("A_All")){
                MP3_Node2_CatageryNameList.add("A_All");
            }

        }
    }


    static void showCommenNodeImpltMap(LinkedHashMap<String, ArrayList<NodeImpl>> xComment_File_Map) {

        Map.Entry<String, ArrayList<NodeImpl>> entry;

        if (xComment_File_Map != null) {
            Iterator iterator = xComment_File_Map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<NodeImpl>>) iterator.next();

                //  获取 名称的 首字母  喜欢   粤语   国语   纯语
                String mLanguage = entry.getKey(); // Map的Value  //  Comment 的 后缀 的字符串
                String Alphabet_Word = getFirstZiMu(mLanguage);

                ArrayList<NodeImpl> mLanguage_level3NodeList = entry.getValue(); // Map的Value  歌曲MP3

                System.out.println("═══════════════════════ Language[" + mLanguage + "]  alphabet=[" + Alphabet_Word + "] all[" + mLanguage_level3NodeList.size() + "] ═══════════════════════");

                for (int i = 0; i < mLanguage_level3NodeList.size(); i++) {
                    NodeImpl mp3FileNode_level_3_Language = mLanguage_level3NodeList.get(i);

                    System.out.println("____________ Language[" + mLanguage + "] alphabet=[" + Alphabet_Word + "] index[" + (i + 1) + "] all[" + mLanguage_level3NodeList.size() + "] ____________");
                    System.out.println(mp3FileNode_level_3_Language.toString());

                }


            }
        }


    }

    static NodeImpl initSongWithCommentForMap(String lanugage, String mComment, String mp3Title, String atrist, File mp3File, LinkedHashMap<String, ArrayList<NodeImpl>> mArtist_NodeImpl_Map, LinkedHashMap<String, ArrayList<NodeImpl>> mComment_File_Map) {
        NodeImpl language_node_impl = null;

        if(!MP3_Node2_CatageryNameList.contains(lanugage)){
            MP3_Node2_CatageryNameList.add(lanugage);
        }


        if (mComment.contains(lanugage)) {
            String Yue_Char = getFirstZiMu(lanugage);
            if("日语".equals(lanugage)){
                System.out.println("ZZlanugage ="+ lanugage +"   Yue_Char="+Yue_Char);
            }

            if (mArtist_NodeImpl_Map.containsKey(lanugage)) {
                ArrayList<NodeImpl> curArr = mArtist_NodeImpl_Map.get(lanugage);
                language_node_impl = new NodeImpl(getNextNodeID(), getIndexFromAlphabet(Yue_Char), mp3Title, 0, 3, mp3File.getAbsolutePath());
                language_node_impl.setArtist(atrist);
                curArr.add(language_node_impl);
            } else {
                ArrayList<NodeImpl> targetArr = new ArrayList<NodeImpl>();
                language_node_impl = new NodeImpl(getNextNodeID(), getIndexFromAlphabet(Yue_Char), mp3Title, 0, 3, mp3File.getAbsolutePath());
                targetArr.add(language_node_impl);
                language_node_impl.setArtist(atrist);
                mArtist_NodeImpl_Map.put(lanugage, targetArr);
            }

            if (mComment_File_Map.containsKey(lanugage) && language_node_impl != null) {
                ArrayList<NodeImpl> curArr = mComment_File_Map.get(lanugage);
                curArr.add(language_node_impl);
            } else if (!mComment_File_Map.containsKey(lanugage) && language_node_impl != null) {
                ArrayList<NodeImpl> targetArr = new ArrayList<NodeImpl>();
                targetArr.add(language_node_impl);
                mComment_File_Map.put(lanugage, targetArr);
            }


        }

        return language_node_impl;
    }

    static synchronized String getRootNodeContent(NodeImpl rootNode) {

        String content = rootNode.json();
        ArrayList jsonArr = new ArrayList<String>();
        jsonArr.add(content);
        writeContentToFile(new File(ZMain_File.getAbsolutePath() + File.separator + "mp3" + File.separator + "mp3.json"), jsonArr);

        android.util.Log.i("mp3json", "  adb pull /sdcard/zmain/mp3/mp3.json . ");

        return content;


    }

    public  static void writeContentToFile(File file,String oneLineStr) {

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

    public static void writeContentToFile(File file, ArrayList<String> strList) {
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


    static NodeImpl getNodeImpl_With_Zimu(ArrayList<NodeImpl> alphabet_node_list, String charAlhapbet) {
        NodeImpl selectedNode = null;
        for (int i = 0; i < alphabet_node_list.size(); i++) {
            NodeImpl node = alphabet_node_list.get(i);
            if (node.name.toUpperCase().equals(charAlhapbet.toUpperCase())) {
                selectedNode = node;
                break;
            }

        }


        return selectedNode;

    }


    @SuppressWarnings("unchecked")
    static void Show_AddNode_MP3Map(LinkedHashMap<String, ArrayList<NodeImpl>> xMP3FileMap, ArrayList<NodeImpl> alphabet_node_list) {


        Map.Entry<String, ArrayList<NodeImpl>> entry;

        if (xMP3FileMap != null) {
            Iterator iterator = xMP3FileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<NodeImpl>>) iterator.next();

                //  获取 名称的 首字母
                String arrTag = entry.getKey(); // Map的Value  // 作者名称 A_All   周杰伦
                String Alphabet_Word = getFirstZiMu(arrTag);
                // 字母 Node ---A---B-----Z
                NodeImpl selectedNode_level_1 = getNodeImpl_With_Zimu(alphabet_node_list, Alphabet_Word);
                ArrayList<NodeImpl> fileArr_level_3 = entry.getValue(); // Map的Value  歌曲MP3

                if(selectedNode_level_1 == null || fileArr_level_3 == null ){
                    continue;
                }
                // 作者 Node  A_All    周杰伦
                NodeImpl artistNode_level_2 = new NodeImpl(getNextNodeID(), selectedNode_level_1.id(), arrTag, fileArr_level_3.size(), 2, null);

                selectedNode_level_1.addChildren(artistNode_level_2);
                artistNode_level_2.parentid = selectedNode_level_1.id();

                // 歌曲 排序
                if(System_SDK_Version >= 25) {
                    SortNodeWithName(fileArr_level_3);  // 排序
                }

                System.out.println("═══════════════════════ arrTag[" + arrTag + "]  alphabet=[" + Alphabet_Word + "] all[" + fileArr_level_3.size() + "] ═══════════════════════");

                for (int i = 0; i < fileArr_level_3.size(); i++) {
                    NodeImpl mp3FileNode_level_3 = fileArr_level_3.get(i);

                    System.out.println(mp3FileNode_level_3.toString());
                    mp3FileNode_level_3.parentid = artistNode_level_2.id();
                    artistNode_level_2.addChildren(mp3FileNode_level_3);
                    System.out.println("____PPP________ arrTag[" + arrTag + "] alphabet=[" + Alphabet_Word + "] index[" + (i + 1) + "] all[" + fileArr_level_3.size() + "] artistNode_level_2.id"+artistNode_level_2.id()+"   mp3FileNode_level_3.id="+mp3FileNode_level_3.id()+"  mp3FileNode_level_3.parentid="+mp3FileNode_level_3.parentid+"____________");

                    if (mp3FileNode_level_3 != null && !level_3_song_nodeList.contains(mp3FileNode_level_3)) {
                        level_3_song_nodeList.add(mp3FileNode_level_3);
                    }
                }

                if (selectedNode_level_1 != null && !level_1_alphabet_nodeList.contains(selectedNode_level_1)) {
                    level_1_alphabet_nodeList.add(selectedNode_level_1);
                }
                if (artistNode_level_2 != null && !level_2_artistism_nodeList.contains(artistNode_level_2)) {
                    level_2_artistism_nodeList.add(artistNode_level_2);
                }


            }
        }


    }


    public  static String getFirstZiMu(String srcStr) {
        String firstZimu = "U";  //  默认为 Unknow;
        if (srcStr == null || "".equals(srcStr.trim())) {
            return firstZimu;
        }

        if (!isContainChinese(srcStr)) {  // 如果 不包含中文  那么 取这个词的 第一个字符
            String char_1 = srcStr.substring(0, 1).toUpperCase();
            firstZimu = char_1;

        } else {
            String char_1 = srcStr.substring(0, 1).toUpperCase();
//				System.out.println("X2 char_1 = "+char_1 );
            if (!isContainChinese(char_1)) {
                firstZimu = char_1;
            } else {  // 如果第一个字母为汉字  那么取到这个字的 拼音的 第一个词
                String pinyinStr = ToPinyin(char_1);
//					System.out.println("X2  pinyinStr = "+pinyinStr );
                String char_1_fixed = pinyinStr.substring(0, 1).toUpperCase();
                firstZimu = char_1_fixed;
            }


        }

//			System.out.println("X3  firstZimu = "+firstZimu );
        return firstZimu;

    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    public static String ToPinyin(String chinese) {
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
                    // 《》，：“￥。，？。，；【 】。、

//                    System.out.println("xxxxxxxxxx");
//                    System.out.println("newChar["+i+"] = "+ newChar[i]);
                    String charStr = newChar[i] + "";
                    if (charStr.equals("《")) {
                        pinyinStr += "<";
                        continue;
                    } else if (charStr.equals("》")) {
                        pinyinStr += ">";
                        continue;
                    } else if (charStr.equals("，")) {
                        pinyinStr += ",";
                        continue;
                    } else if (charStr.equals("：")) {
                        pinyinStr += ":";
                        continue;
                    } else if (charStr.equals("“")) {
                        pinyinStr += "\"";
                        continue;
                    } else if (charStr.equals("￥")) {
                        pinyinStr += "$";
                        continue;
                    } else if (charStr.equals("？")) {
                        pinyinStr += "?";
                        continue;
                    } else if (charStr.equals("；")) {
                        pinyinStr += ";";
                        continue;
                    } else if (charStr.equals("【")) {
                        pinyinStr += "[";
                        continue;
                    } else if (charStr.equals("】")) {
                        pinyinStr += "]";
                        continue;
                    } else if (charStr.equals("、")) {
                        pinyinStr += ",";
                        continue;
                    }


                    String[] arrChar = PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
                    if (arrChar == null) {
                        System.out.println("pinyinStr = " + null);
                        continue;
                    }
                    pinyinStr += "_" + arrChar[0] + "_"; // [0] 标识当前拼音 汉-> han
//                    System.out.println("pinyinStr = "+ pinyinStr);
//                    System.out.println("xxxxxxxxxx ");
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {  // 汉字的编码是大于 128的 所以 小于 128编码的就直接认为是 ascii编码的
                pinyinStr += newChar[i];
            }
        }
        while (pinyinStr.contains("__")) {
            pinyinStr = pinyinStr.replaceAll("__", "_");
//            System.out.println("pinyinStr1 = " + pinyinStr);
        }

        while (pinyinStr.contains("u:")) {  // 女转为 nu:   绿 lu:   需要转为 nv  lv
            pinyinStr = pinyinStr.replaceAll("u:", "v");
//            System.out.println("pinyinStr1 = " + pinyinStr);
        }

        while (pinyinStr.startsWith("_")) {
            pinyinStr = pinyinStr.substring(1, pinyinStr.length());
//            System.out.println("pinyinStr2 = " + pinyinStr);
        }
        while (pinyinStr.endsWith("_")) {
            pinyinStr = pinyinStr.substring(0, pinyinStr.length() - 1);
//            System.out.println("pinyinStr3 = " + pinyinStr);
        }
        return pinyinStr;
    }


    static public NodeImpl get3level_parentlevel2(long curID ) {
        NodeImpl parentNode = null;

        for (int i = 0; i < level_2_artistism_nodeList.size() ; i++) {
            NodeImpl curNodeImpl = level_2_artistism_nodeList.get(i);
            if(curNodeImpl.id() == curID){
                parentNode = curNodeImpl;
                break;
            }

        }
        return parentNode;

    }

    public static String showByte(byte byteData  ){
        StringBuilder sb  = new StringBuilder();
        String result = "";

        for (int i = 7; i >= 0; i--) {

            byte tempByte = byteData;
            tempByte = (byte)(tempByte >> i);
            int value = tempByte & 0x01;
            if(value == 1){
                sb.append("1");
            }else{
                sb.append("0");
            }

        }


        return sb.toString()+ " ";

    }

    // MP3的Comment 的 Tag 每次读取 都读取到
//      D3V2_Comment=[?国语]_CharSet[UTF-8]_Byte[00111111 10111001 11111010 11010011 11101111 ]  Byte_Comment=[?国语]_ChatSet[GB2312]_Byte[00111111 10111001 11111010 11010011 111011
//   问号  ?国语    为了把问号 去掉  所以创建这个方法                                                                                                                                      11 ]

    static String clearLuanMaForCommantTag(byte[] arr) {
        String resultStr = null;
        if(arr == null) {
            return null;
        }


        byte[] temp_byte_arr = new byte[arr.length-1];

        int temp_index = 0;
        for (int i = 0; i < arr.length; i++) {
            byte curByte = arr[i];
            String byteStr = showByte(curByte);
            if(byteStr != null && byteStr.trim().equals("00111111") && i ==0) {
                continue;
            }
            if(temp_index <= temp_byte_arr.length -1) {
                temp_byte_arr[temp_index] = curByte;
                temp_index++;
            }


        }


        try {
            resultStr =  new String(temp_byte_arr,"gbk");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultStr;
    }


    static public long getParentId(long curID ) {
        long parentId = -1;

        for (int i = 0; i < level_3_song_nodeList.size() ; i++) {
            NodeImpl curNodeImpl = level_3_song_nodeList.get(i);
            if(curNodeImpl.id() == curID){
                parentId = curNodeImpl.parentid;
                break;
            }

        }
        return parentId;

    }


    static public String getNode2NameWithId(long node2Id ) {
        String currentNodeName = "";

        for (int i = 0; i < level_2_artistism_nodeList.size() ; i++) {
            NodeImpl curNodeImpl = level_2_artistism_nodeList.get(i);
            if(curNodeImpl.id() == node2Id){
                currentNodeName = curNodeImpl.name;
                return currentNodeName;
            }

        }
        return currentNodeName;

    }


    static public String getOriginNode2NameWithName(String node3Name ) {


//        MP3_Node2_CatageryNameList
        String currentNodeName = "";


//        MP3_Node2_CatageryNameList
        for (int i = 0; i < level_3_song_nodeList.size() ; i++) {
            NodeImpl curNodeImpl = level_3_song_nodeList.get(i);
            String nodeName = curNodeImpl.name;
            String arrtist =  curNodeImpl.artist;
            if(nodeName != null){
                if(nodeName.equals(node3Name)){
                    NodeImpl parentNode =     get3level_parentlevel2(curNodeImpl.parentid);
                    if(!MP3_Node2_CatageryNameList.contains(parentNode.name)){
                        return  getNode2NameWithId(curNodeImpl.parentid);
                    }

                }
            }

        }
        return currentNodeName;

    }




    static public int getPositionInList(long curID , long xParentId) {

        int curPosition = -1;


        NodeImpl parentNode_level_2 = null;
        for (int i = 0; i < level_2_artistism_nodeList.size() ; i++) {
            NodeImpl curNodeImpl = level_2_artistism_nodeList.get(i);
            if(curNodeImpl.id() == xParentId){
                parentNode_level_2 = curNodeImpl;
            }
        }

        if(parentNode_level_2 == null){
            android.util.Log.i("zukgit","getPositionInList  无法找到 id="+xParentId+"的 level_2_node  in  DataHolder.getRandomNextSongId() !!!  curID="+curID+"   xParentId="+xParentId);
            return -1;
        }
        String level_2_name = parentNode_level_2.name;   //  歌手的名字



        ArrayList<NodeImpl> nodeLevel_list =      mArtist_NodeImpl_Map.get(level_2_name);
        ArrayList<NodeImpl> nodeLevel_userdefine_list =  mUserDefine_Category_Level2_NodeImpl_Map.get(level_2_name);

        if( (nodeLevel_list == null || nodeLevel_list.size() == 0) && (nodeLevel_userdefine_list == null || nodeLevel_userdefine_list.size() == 0) ){
            android.util.Log.i("zukgit","getPositionInList  nodeLevel_list=null   level_2_name="+level_2_name+" 无法找到 id="+xParentId+"的 level_2_node  in  DataHolder.getRandomNextSongId() !!!  curID="+curID+"   xParentId="+xParentId);

            return  -1;
        }



if(nodeLevel_list != null){

            for (int i = 0; i < nodeLevel_list.size() ; i++) {
            NodeImpl curNodeImpl_level_3 = nodeLevel_list.get(i);
            if(curNodeImpl_level_3.id() == curID){
                android.util.Log.i("zukgit","getPositionInList_A   curPosition="+(int)curNodeImpl_level_3.index4parent +" level_2_name="+level_2_name+" level3_name="+curNodeImpl_level_3.name);
                return  (int)curNodeImpl_level_3.index4parent;
            }
        }

}

        if(nodeLevel_userdefine_list != null) {
            for (int i = 0; i < nodeLevel_userdefine_list.size(); i++) {
                NodeImpl curNodeImpl_level_3 = nodeLevel_userdefine_list.get(i);
                if (curNodeImpl_level_3.id() == curID) {
                    int cur_node_position =curNodeImpl_level_3.index4parent;
                    android.util.Log.i("zukgit", "getPositionInList_B   cur_node_position=" + (int) curNodeImpl_level_3.index4parent + " level_2_name=" + level_2_name +" level3_name="+curNodeImpl_level_3.name);
                    return (int) curNodeImpl_level_3.index4parent;
                }
            }
        }

        android.util.Log.i("zukgit","getPositionInList  curPosition="+curPosition+" level_2_name="+level_2_name);

        return curPosition;

    }



    static public NodeImpl getRandomNextSongId(long curID , long xParentId) {
        android.util.Log.i("zukgit","_________________________________ getRandomNextSongId 方法 Begin  _________________________________");

        NodeImpl nextNodeItem = null;
        ArrayList<NodeImpl> sameParentNode_List = new  ArrayList<NodeImpl>();
        NodeImpl selfNode = null;
        android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_1  curID="+curID+"    xParentId="+xParentId);
        for (int i = 0; i < level_3_song_nodeList.size() ; i++) {
            NodeImpl curNodeImpl = level_3_song_nodeList.get(i);
            android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_2 循环  Z1  i="+i+"    ");
            if(curNodeImpl.id() == curID){
                selfNode = curNodeImpl;
                android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_3  selfNode="+selfNode.toString());
            }
            if(curNodeImpl.parentid == xParentId  ){
                sameParentNode_List.add(curNodeImpl);
                android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_4  selfNode="+selfNode);
            }
        }
        android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_3 循环出口  selfNode="+selfNode);
        if(selfNode != null && xParentId != selfNode.parentid ){
            android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_4_1    尼玛调用父类的点和子类的点不一样  selfNode.parentid="+selfNode.parentid+"   xParentId="+xParentId);
            sameParentNode_List.clear();


            for (int i = 0; i < level_3_song_nodeList.size() ; i++) {
                NodeImpl curNodeImpl = level_3_song_nodeList.get(i);
                if(curNodeImpl.parentid == selfNode.parentid  ){
                    sameParentNode_List.add(curNodeImpl);
                    android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_4_2   添加新的Item_index ["+i+"]");
                }
            }
            android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_4_3   sameParentNode_List.size="+sameParentNode_List.size());
        }
        android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_5  ");
        if(sameParentNode_List.size() <= 1 && selfNode != null){  // 只有 一个 是 自己
            nextNodeItem = selfNode;
            android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_6   返回自己  selfNode="+selfNode.toString());

            android.util.Log.i("zukgit","A_2 getRandomNextSongId  next_click_level3_ID  sameParentNode_List.size() = "+ sameParentNode_List.size());
        }else{
            int curNodeSize = sameParentNode_List.size();
            android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_7_1    curNodeSize ="+curNodeSize);
//            SortNodeWithName(sameParentNode_List);
            if(curNodeSize == 1){   // 只有  唯一一个 歌曲  那么停止
                int currentIndex =  sameParentNode_List.indexOf(selfNode);
                android.util.Log.i("zukgit","A_3 getRandomNextSongId next_click_level3_ID  sameParentNode_List.size() = "+ sameParentNode_List.size() + "currentIndex = "+ currentIndex);
                Random rd = new Random();
                nextNodeItem = sameParentNode_List.get((currentIndex+rd.nextInt(curNodeSize))%curNodeSize);

                android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_7_A      nextNodeItem="+nextNodeItem.toString());
            }else if(curNodeSize > 1){

                int currentIndex =  sameParentNode_List.indexOf(selfNode);
                sameParentNode_List.remove(selfNode);

                for (int i = 0; i < sameParentNode_List.size(); i++) {

                    Random rd = new Random();

                   int randomIndex =  (rd.nextInt(sameParentNode_List.size()))%sameParentNode_List.size();

                    nextNodeItem = sameParentNode_List.get(randomIndex);

                    android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_7_A1  currentIndex="+currentIndex+"  i="+i+" randomIndex="+randomIndex+" sameParentNode_List.size()="+sameParentNode_List.size()+"     nextNodeItem="+nextNodeItem.toString());

                    if(nextNodeItem == selfNode){
                        continue;
                    }
                    if(nextNodeItem != selfNode){   //  确保 随机 时候  不选中 自己
                        android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_7_B      nextNodeItem="+nextNodeItem.toString());

                        break;
                    }
                }


            }
            android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_7_1   nextNodeItem  =" + nextNodeItem);

        }
        if(nextNodeItem != null){
            android.util.Log.i("zukgit","A_4 getRandomNextSongId next_click_level3_ID  nextNodeItem.id = "+ nextNodeItem.id());
            android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_8      nextNodeItem="+nextNodeItem.toString());
        }else{
            android.util.Log.i("zukgit","A_4 null getRandomNextSongId next_click_level3_ID  nextNodeItem.id = null ");
            android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_9      nextNodeItem=null");

        }

        android.util.Log.i("zukgit","【getRandomNextSongId 方法】 ____入口_10      nextNodeItem="+nextNodeItem);
        android.util.Log.i("zukgit","_________________________________ getRandomNextSongId 方法 End  _________________________________");
        return  nextNodeItem;

    }


    static public int  getNode3NumberWithSameParent(long curID , long xParentId){
        NodeImpl nextNodeItem = null;
        ArrayList<NodeImpl> sameParentNode_List = new  ArrayList<NodeImpl>();
        NodeImpl selfNode = null;

        for (int i = 0; i < level_3_song_nodeList.size() ; i++) {
            NodeImpl curNodeImpl = level_3_song_nodeList.get(i);
            if(curNodeImpl.id() == curID){
                selfNode = curNodeImpl;
            }
            if(curNodeImpl.parentid == xParentId  ){
                sameParentNode_List.add(curNodeImpl);
            }
        }
        if(selfNode != null && xParentId != selfNode.parentid ){
            android.util.Log.i("zukgit","【getNode3NumberWithSameParent 方法】 ____入口_4_1    尼玛调用父类的点和子类的点不一样  selfNode.parentid="+selfNode.parentid+"   xParentId="+xParentId);
            sameParentNode_List.clear();


            for (int i = 0; i < level_3_song_nodeList.size() ; i++) {
                NodeImpl curNodeImpl = level_3_song_nodeList.get(i);
                if(curNodeImpl.parentid == selfNode.parentid  ){
                    sameParentNode_List.add(curNodeImpl);
                    android.util.Log.i("zukgit","【getNode3NumberWithSameParent 方法】 ____入口_4_2   添加新的Item_index ["+i+"]");
                }
            }
            android.util.Log.i("zukgit","【getNode3NumberWithSameParent 方法】 ____入口_4_3   sameParentNode_List.size="+sameParentNode_List.size());
        }





        return  sameParentNode_List.size();


    }


    static public NodeImpl getNextSongId(long curID , long xParentId) {
        NodeImpl nextNodeItem = null;
        ArrayList<NodeImpl> sameParentNode_List = new  ArrayList<NodeImpl>();
        NodeImpl selfNode = null;

        for (int i = 0; i < level_3_song_nodeList.size() ; i++) {
            NodeImpl curNodeImpl = level_3_song_nodeList.get(i);
            if(curNodeImpl.id() == curID){
                selfNode = curNodeImpl;
            }
            if(curNodeImpl.parentid == xParentId  ){
                sameParentNode_List.add(curNodeImpl);
            }
        }
        if(selfNode != null && xParentId != selfNode.parentid ){
            android.util.Log.i("zukgit","【getNextSongId 方法】 ____入口_4_1    尼玛调用父类的点和子类的点不一样  selfNode.parentid="+selfNode.parentid+"   xParentId="+xParentId);
            sameParentNode_List.clear();


            for (int i = 0; i < level_3_song_nodeList.size() ; i++) {
                NodeImpl curNodeImpl = level_3_song_nodeList.get(i);
                if(curNodeImpl.parentid == selfNode.parentid  ){
                    sameParentNode_List.add(curNodeImpl);
                    android.util.Log.i("zukgit","【getNextSongId 方法】 ____入口_4_2   添加新的Item_index ["+i+"]");
                }
            }
            android.util.Log.i("zukgit","【getNextSongId 方法】 ____入口_4_3   sameParentNode_List.size="+sameParentNode_List.size());
        }


        if(selfNode == null){
            return null;
        }

        if(sameParentNode_List.size() <= 1){  // 只有 一个 是 自己
            nextNodeItem = selfNode;
            android.util.Log.i("zukgit","A_2 getNextSongId  next_click_level3_ID  sameParentNode_List.size() = "+ sameParentNode_List.size());
        }else{
            int curNodeSize = sameParentNode_List.size();
//            SortNodeWithName(sameParentNode_List);
            int currentIndex =  sameParentNode_List.indexOf(selfNode);
            android.util.Log.i("zukgit","A_3 getNextSongId next_click_level3_ID  sameParentNode_List.size() = "+ sameParentNode_List.size() + "currentIndex = "+ currentIndex);

            nextNodeItem = sameParentNode_List.get((currentIndex+1)%curNodeSize);
        }
        android.util.Log.i("zukgit","A_4 null getNextSongId next_click_level3_ID  nextNodeItem.id = "+ nextNodeItem.id());

        return  nextNodeItem;
    }

    //判断是否是数字
    static boolean isNumericFirstChar(String s){
        return Character.isDigit(s.charAt(0));
    }



    static void SortNodeWithName(ArrayList<NodeImpl> fileList) {
        Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
        fileList.sort((o1_node, o2_node) -> {
            //比较的基本原则，拿最小长度的字符串进行比较，若全部相等，则长字符串往后排
            String o1 = o1_node.text();
            String o2 = o2_node.text();
            int len1 = o1.length();
            int len2 = o2.length();
            int len = (len1 - len2) <= 0 ? len1 : len2;
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < len; i++) {
                String s1 = o1.substring(i, i + 1);
                String s2 = o2.substring(i, i + 1);
                if (isNumericFirstChar(s1) && isNumericFirstChar(s2)){
                    //取出所有的数字
                    sb1.append(s1);
                    sb2.append(s2);
                    //取数字时，不比较
                    continue;
                }
                if (sb1.length() != 0 && sb2.length() != 0){
                    if (!isNumericFirstChar(s1) && !isNumericFirstChar(s2)){
                        int value1 = Integer.valueOf(sb1.toString());
                        int value2 = Integer.valueOf(sb2.toString());
                        return value1 - value2;
                    } else if (isNumericFirstChar(s1)) {
                        return 1;
                    } else if (isNumericFirstChar(s2)) {
                        return -1;
                    }
                }
                int result = CHINA_COMPARE.compare(s1, s2);
                if (result != 0) {
                    return result;
                }
            }
            //这一步：是为了防止以下情况：第10  第20，正好以数字结尾，且字符串长度相等
            if (len1 == len2 && sb1.length() != 0 && sb2.length() != 0) {
                int value1 = Integer.valueOf(sb1.toString());
                int value2 = Integer.valueOf(sb2.toString());
                return value1 - value2;
            }
            //若前面都相等，则直接比较字符串的长度，长的排后面，短的排前面
            return Integer.compare(len1, len2);
        });

    }

    static void SortStringWithName(ArrayList<String> fileList) {
        Comparator<Object>    CHINA_COMPARE =  Collator.getInstance(java.util.Locale.CHINA);

        fileList.sort((o1_str, o2_str) -> {
            //比较的基本原则，拿最小长度的字符串进行比较，若全部相等，则长字符串往后排


            String o1 = o1_str;
            String o2 = o2_str;
            int len1 = o1.length();
            int len2 = o2.length();
            int len = (len1 - len2) <= 0 ? len1 : len2;
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < len; i++) {
                String s1 = o1.substring(i, i + 1);
                String s2 = o2.substring(i, i + 1);
                if (isNumericFirstChar(s1) && isNumericFirstChar(s2)){
                    //取出所有的数字
                    sb1.append(s1);
                    sb2.append(s2);
                    //取数字时，不比较
                    continue;
                }
                if (sb1.length() != 0 && sb2.length() != 0){
                    if (!isNumericFirstChar(s1) && !isNumericFirstChar(s2)){
                        int value1 = Integer.valueOf(sb1.toString());
                        int value2 = Integer.valueOf(sb2.toString());
                        return value1 - value2;
                    } else if (isNumericFirstChar(s1)) {
                        return 1;
                    } else if (isNumericFirstChar(s2)) {
                        return -1;
                    }
                }

                int result = CHINA_COMPARE.compare(s1, s2);
                if (result != 0) {
                    return result;
                }

            }
            //这一步：是为了防止以下情况：第10  第20，正好以数字结尾，且字符串长度相等
            if (len1 == len2 && sb1.length() != 0 && sb2.length() != 0) {
                int value1 = Integer.valueOf(sb1.toString());
                int value2 = Integer.valueOf(sb2.toString());
                return value1 - value2;
            }
            //若前面都相等，则直接比较字符串的长度，长的排后面，短的排前面
            return Integer.compare(len1, len2);
        });


    }

    public static  String   getSongNameWithID(long id){
        String nextSongName = null;
        for (int i = 0; i < level_3_song_nodeList.size(); i++) {
            NodeImpl nextNodeImpl = level_3_song_nodeList.get(i);
            if(nextNodeImpl.id() == id){
                nextSongName = nextNodeImpl.name;
                break;
            }
        }
        return nextSongName;
    }

    public static LinkedHashMap<String,ArrayList<NodeImpl>> sortHashMap(LinkedHashMap<String,ArrayList<NodeImpl>> map){
        LinkedHashMap<String,ArrayList<NodeImpl>> sortedMap = new LinkedHashMap<String,ArrayList<NodeImpl>>();
        ArrayList<String> list = new ArrayList<String>();
        Iterator<String> item = map.keySet().iterator();
        while(item.hasNext()){
            list.add(item.next());
        }
        if(System_SDK_Version >= 25){
            SortStringWithName(list);
        }

        Iterator<String> item2 = list.iterator();
        while(item2.hasNext()){
            String key = item2.next();
            ArrayList<NodeImpl> nodeArr  = map.get(key);
            if(System_SDK_Version >= 25) {
                SortNodeWithName(nodeArr);
            }
            sortedMap.put(key,map.get(key));
        }
        return sortedMap;
    }

    public static String getRandomColorString(){
        Random  rd = new Random();
        int a = rd.nextInt(256);
        int r =  rd.nextInt(256);
        int g =  rd.nextInt(256);
        int b =  rd.nextInt(256);
        String a_str = Integer.toHexString(a);
        String r_str = Integer.toHexString(r);
        String g_str = Integer.toHexString(g);
        String b_str = Integer.toHexString(b);
        if(a_str.length() == 1){
            a_str = "0"+a_str;
        }


        if(r_str.length() == 1){
            r_str = "0"+r_str;
        }
        if(g_str.length() == 1){
            g_str = "0"+g_str;
        }
        if(b_str.length() == 1){
            b_str = "0"+b_str;
        }

        return   "#"+a_str+r_str+g_str+b_str;

    }



    public static String getFileTypeWithPoint(String fileName) {
        String name = "";
        if (fileName.contains(".")) {
            name = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
        } else {
            name = "";
        }
        return name.toLowerCase().trim();
    }


    public  static String getExif_Artist(File jpgFile){

        if(jpgFile == null || !jpgFile.exists()){
            return null;    // 当前文件不存在
        }

        String m_artist = "";
        String fileName = jpgFile.getName().toLowerCase();
        String fileType = getFileTypeWithPoint(fileName);
        if(!".jpg".equals(fileType)){
            return null;    // 只有  jpg 文件才有  exif copyright 属性
        }


        try {
            ExifInterface exif = new ExifInterface(jpgFile.getAbsolutePath());
            String m_artist_get = exif.getAttribute(ExifInterface.TAG_ARTIST);
            if (m_artist_get != null){
                m_artist = new String(m_artist_get.getBytes(), "UTF-8");
            }
            System.out.println("Kaoyan_Land_Img_key "+"m_artist_get="+m_artist_get+"    m_artist="+m_artist+"   file="+jpgFile.getAbsolutePath());



        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(" 获取 图片文件 "+jpgFile.getAbsolutePath()+" 的 exif 属性 copyright属性失败! ");
            e.printStackTrace();
        }


        return m_artist;

    }


    public  static String getExif_Copyright_videomd5name(File jpgFile){

        if(jpgFile == null || !jpgFile.exists()){
            return null;    // 当前文件不存在
        }

        String videoMD5_name = "";
        String fileName = jpgFile.getName().toLowerCase();
        String fileType = getFileTypeWithPoint(fileName);
        if(!".jpg".equals(fileType)){
            return null;    // 只有  jpg 文件才有  exif copyright 属性
        }


        try {
            ExifInterface exif = new ExifInterface(jpgFile.getAbsolutePath());
            String m_videomd5name_copyright = exif.getAttribute(ExifInterface.TAG_COPYRIGHT);
            videoMD5_name = m_videomd5name_copyright;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(" 获取 图片文件 "+jpgFile.getAbsolutePath()+" 的 exif 属性 copyright属性失败! ");
            e.printStackTrace();
        }


        return videoMD5_name;

    }


    public static String getOuttableSDcardStoragePath(Context mContext) {

        String mEmulated0Path = getEmulated0Path(mContext);
        print("mEmulated0Path = "+ mEmulated0Path);
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);



        Class<?>  storageVolumeClazz = null;

        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");

            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");

            Method getPath = storageVolumeClazz.getMethod("getPath");

            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");

            Object result = getVolumeList.invoke(mStorageManager);

            final int length = Array.getLength(result);

            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);

                String path = (String) getPath.invoke(storageVolumeElement);

                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);

                if (true == removable) {
                    Outtable_TF_File_Path = path;
                    return path;

                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }

        return null;

    }

    public static String getRootStoragePath(Context mContext) {
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);


          Class<?>  storageVolumeClazz = null;

        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");

            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");

            Method getPath = storageVolumeClazz.getMethod("getPath");

            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");

            Object result = getVolumeList.invoke(mStorageManager);

            final int length = Array.getLength(result);

            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);

                String path = (String) getPath.invoke(storageVolumeElement);

                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);

                if (false == removable) {
                    return path;

                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }

        return null;

    }


 public static String   getEmulated0Path(Context mContext){

        // SDCard地址 /storage/emulated/0
        // getExternalStorageDirectory在29已废弃
//  String saveDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        // getExternalFilesDir() 用于获取SDCard/Android/data/你的应用的包名/files/ 目录
//     mEmulatedSdcardPath
        File externalFileRootDir = mContext.getExternalFilesDir(null);
        do {
            externalFileRootDir = Objects.requireNonNull(externalFileRootDir).getParentFile();
        } while (Objects.requireNonNull(externalFileRootDir).getAbsolutePath().contains("/Android"));

        String saveDir = Objects.requireNonNull(externalFileRootDir).getAbsolutePath();
     mEmulatedSdcardPath = saveDir;
     Emulated_0_ZMain_File =  new File(mEmulatedSdcardPath+File.separator+"zmain");
     print(" mEmulatedSdcardPath = "+ mEmulatedSdcardPath+"   Emulated_0_ZMain_File="+ Emulated_0_ZMain_File);
     return mEmulatedSdcardPath;
    }



    public  static  File initSD_Root_File(){
      File   mSdcard_File = Environment.getExternalStorageDirectory();  // 获取到系统自带存储空间 /sdcard/  不是TF卡空间


        print("AA Sdcard_File = "+ Sdcard_File+"  Outtable_TF_File_Path="+ Outtable_TF_File_Path);
        File mTF_File = null;
        if(Outtable_TF_File_Path != null){
            mTF_File = new File(Outtable_TF_File_Path);
            if(mTF_File.exists()){
                TF_File =   mTF_File;
                File TF_Zmain = new File(TF_File.getAbsolutePath()+File.separator+"zmain");
                print("BB Sdcard_File = "+ Sdcard_File+"  Outtable_TF_File_Path="+ Outtable_TF_File_Path+"  TF_File="+TF_File.getAbsolutePath());

                if(TF_Zmain.exists() ){  //  只有 tf 路径有 zmain 文件夹才算数
                    print("CC Sdcard_File = "+ Sdcard_File+"  Outtable_TF_File_Path="+ Outtable_TF_File_Path+"  TF_File="+TF_File.getAbsolutePath()+" TF_Zmain="+TF_Zmain.getAbsolutePath());
                    return TF_File;
                }

            }
        }
        return mSdcard_File;
    }



    public static String clearChinese(String lineContent) {
        String REGEX_CHINESE = "[\u4e00-\u9fa5]";
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }


    /**
     * 方法描述: 抖音解析下载视频  判断 land   和 port  并放入响应的 文件夹中

     */
    @SuppressWarnings("unchecked")
    public static File douYinParseUrl(String url,File targetPortDirFile , File targetLandDirFile ) {
        File videoFile = null;
        String  finalVideoAddress = null ;
        String  mDouyinItemId = null ;
        String title= null ;
        try {
            final  String videoPath_Pre_1="https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";
            org.jsoup.Connection con= org.jsoup.Jsoup.connect(clearChinese(url));
            con.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
            org.jsoup.Connection.Response resp=con.method(org.jsoup.Connection.Method.GET).execute();
            mDouyinItemId = getDouYinItemId(resp.url().toString());
            String videoUrl_1= videoPath_Pre_1+mDouyinItemId;
            String jsonStr = org.jsoup.Jsoup.connect(videoUrl_1).ignoreContentType(true).execute().body();
            JSONObject json = JSONObject.parseObject(jsonStr);

            print("douYinParseUrl  json="+json+"   jsonStr="+jsonStr+"  videoUrl_1="+videoUrl_1 +"  resp.url()="+resp.url());

           if(json != null){
               String videoAddress= json.getJSONArray("item_list").getJSONObject(0).getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
                title= json.getJSONArray("item_list").getJSONObject(0).getJSONObject("share_info").getString("share_title");
               videoAddress=videoAddress.replaceAll("playwm","play");
               HashMap headers = new HashMap<String,String>();
               headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");

               //  报错  静态初始化错误
//            String finalVideoAddress = HttpUtil.createGet(videoAddress).addHeaders(headers).execute().header("Location");



               headers.put("Connection", "keep-alive");
               headers.put("Host", "aweme.snssdk.com");

               finalVideoAddress = Douyin_GetRealVideoUrl(videoAddress, headers);

               if (finalVideoAddress == null) {
                   print("Douyin  finalVideoAddress == null 尝试 使用  videoAddress 下载" );
                   if(videoAddress != null){
                       finalVideoAddress  = videoAddress;
                   }

                   // return null;
               }

               System.out.println("-----抖音去水印链接-----\n"+"finalVideoAddress【"+finalVideoAddress+"】   jsonStr_1="+jsonStr );


           } else if(mDouyinItemId != null){  //   第一种 解析方式得到的数据为 空

               final  String videoPath_Pre_2="https://www.iesdouyin.com/aweme/v1/web/aweme/detail/?aweme_id="+mDouyinItemId;


               String jsonStr_2 = org.jsoup.Jsoup.connect(videoPath_Pre_2).ignoreContentType(true).execute().body();
               JSONObject json_2 = JSONObject.parseObject(jsonStr_2);


               print("douYinParseUrl  json_2="+json_2+"   jsonStr_2="+jsonStr_2);



               if(json_2 == null){
                   return null;
               }


               String videoAddress_2= json_2.getJSONObject("aweme_detail").getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
                title= json_2.getJSONObject("aweme_detail").getJSONObject("share_info").getString("share_desc");
               videoAddress_2=videoAddress_2.replaceAll("playwm","play");
               HashMap headers = new HashMap<String,String>();
               headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");


               print("douYinParseUrl  videoAddress_2="+videoAddress_2+"   title="+title);


               //  报错  静态初始化错误
//            String finalVideoAddress = HttpUtil.createGet(videoAddress).addHeaders(headers).execute().header("Location");



               headers.put("Connection", "keep-alive");
               headers.put("Host", "aweme.snssdk.com");

               finalVideoAddress = Douyin_GetRealVideoUrl(videoAddress_2, headers);

               if(finalVideoAddress == null ){
                   finalVideoAddress = videoAddress_2;

               }




               print("-----抖音去水印链接 douYinParseUrl -----\n"+"finalVideoAddress【"+finalVideoAddress+"】   jsonStr_2="+jsonStr_2 );
           }



            //注:打印获取的链接

            print("-----最终 抖音去水印链接 douYinParseUrl -----\n"+"finalVideoAddress【"+finalVideoAddress+"】");
            if(finalVideoAddress == null){
                // 如果 finalVideoAddress 为 null  那么尝试 使用 videoAddress下载
                return null;
            }

            return  downRawVideo_WithUrl(url,finalVideoAddress,title,"douyin",targetPortDirFile,targetLandDirFile);
        } catch (IOException e) {
            print("Douyin 下载失败 -- "+e.getMessage());
        }
        return videoFile;
    }


    /**
     * 方法描述: 抖音解析下载视频

     */
    @SuppressWarnings("unchecked")
    public static File douYinParseUrl(String url,File targetDirFile) {
        File videoFile = null;
        try {
            final  String videoPath="https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";
            org.jsoup.Connection con= org.jsoup.Jsoup.connect(clearChinese(url));
            con.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
            org.jsoup.Connection.Response resp=con.method(org.jsoup.Connection.Method.GET).execute();
            String videoUrl= videoPath+getDouYinItemId(resp.url().toString());
            String jsonStr = org.jsoup.Jsoup.connect(videoUrl).ignoreContentType(true).execute().body();
            JSONObject json = JSONObject.parseObject(jsonStr);
            String videoAddress= json.getJSONArray("item_list").getJSONObject(0).getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
            String title= json.getJSONArray("item_list").getJSONObject(0).getJSONObject("share_info").getString("share_title");
            videoAddress=videoAddress.replaceAll("playwm","play");
            HashMap headers = new HashMap<String,String>();
            headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");

            //  报错  静态初始化错误
//            String finalVideoAddress = HttpUtil.createGet(videoAddress).addHeaders(headers).execute().header("Location");


/*
 // 报错  不支持 二进制
            HttpRequest httprequest  =  HttpUtil.createGet(videoAddress).addHeaders(headers);
print("A_videoAddress = "+videoAddress);
          HttpResponse httpResponse =   httprequest.execute();
            print("B_videoAddress = "+videoAddress);

            String finalVideoAddress =   httpResponse.header("Location");
            print("C_videoAddress = "+videoAddress+"   finalVideoAddress="+finalVideoAddress);
*/


/*            OkHttpClient httpClient = new OkHttpClient();
            String result = null;
            OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .build();

            okhttp3.Response detailResp = client.newCall(request).execute();

            String finalVideoAddress  =     detailResp.header("Location");

            result = detailResp.body().string();*/



            headers.put("Connection", "keep-alive");
            headers.put("Host", "aweme.snssdk.com");

            String  finalVideoAddress = Douyin_GetRealVideoUrl(videoAddress, headers);


            if (finalVideoAddress == null) {
                print("Douyin  finalVideoAddress == null 尝试 使用  videoAddress 下载" );
                if(videoAddress != null){
                    finalVideoAddress  = videoAddress;
                }


                 // return null;
            }


/*
            print("Douyin CC_videoAddress = "+videoAddress);
            org.jsoup.Connection detailConnect  = org.jsoup.Jsoup.connect(clearChinese(videoAddress));
            detailConnect.headers(headers);
            // 不报错  但 没取到 真实的地址
            org.jsoup.Connection.Response detailResp=con.method(org.jsoup.Connection.Method.GET).execute();

          String finalVideoAddress  =   detailResp.header("Location");  //detailResp.body();


*/


/*
            int  statusCode_A  = detailResp.code();
            ArrayList<String> headNameList = new ArrayList<String>();

            headNameList.addAll(detailResp.headers().names());

            for (int i = 0; i < headNameList.size(); i++) {
                String headName = headNameList.get(i);
                print("Douyin head["+i+"_"+headName+"] = "+detailResp.header(headName) );
            }

   print("Douyin  statusCode_A="+statusCode_A+"   heads="+detailResp.headers().size()+ "  D_videoAddress = "+videoAddress+"  finalVideoAddress="+finalVideoAddress);

*/


/*
   org.jsoup.nodes.Document doc =     org.jsoup.Jsoup.connect(videoAddress).ignoreContentType(true)
                    .headers(headers)
                    // ignoreHttpErrors
                    //这个很重要 否则会报HTTP error fetching URL. Status=404
                    .ignoreHttpErrors(true)  //这个很重要
                    .timeout(3000).post();

            System.out.println("Douyin-----抖音去水印链接-----\n"+doc.html());
                   String finalVideoAddress  =  doc.location();
*/




            //注:打印获取的链接
            System.out.println("-----抖音去水印链接-----\n"+"finalVideoAddress【"+finalVideoAddress+"】 "+ " \nvideoAddress【"+videoAddress+"】   \nvideoUrl【"+videoUrl+"】"+"   \njsonStr【"+jsonStr+"】" );

            if(finalVideoAddress == null){
                // 如果 finalVideoAddress 为 null  那么尝试 使用 videoAddress下载
                finalVideoAddress = videoAddress;
            }

           return  downRawVideo_WithUrl(url,finalVideoAddress,title,"douyin",targetDirFile);
        } catch (IOException e) {
            System.out.println("Douyin 下载失败 -- "+e.getMessage());
        }
        return videoFile;
    }


    private static int sBufferSize = 524288;


    /**
     * Write file from input stream.
     *
     * @param file     The file.
     * @param is       The input stream.
     * @param append   True to append, false otherwise.
     * @param listener The progress update listener.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean writeFileFromIS(final File file,
                                          final InputStream is,
                                          final boolean append
                                        ) {
        if (is == null || !createOrExistsFile(file)) return false;
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file, append), sBufferSize);

                byte[] data = new byte[sBufferSize];
                for (int len; (len = is.read(data)) != -1; ) {
                    os.write(data, 0, len);
                }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static boolean createOrExistsFile(final String filePath) {
        return createOrExistsFile(getFileByPath(filePath));
    }

    private static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }


    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }


    private static boolean createOrExistsFile(final File file) {
        if (file == null) return false;
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static String Douyin_GetRealVideoUrl(String url, Map<String, String> headers) {
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            conn.setDoInput(true);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
                print("Douyin header_key:" + entry.getKey() + ",value:" + entry.getValue());
            }
            int code = conn.getResponseCode();
            print("Douyin  endd_code:" + code);
            print("Douyin  end_url:" + url);

            if (code == 200) {
//                InputStream in = conn.getInputStream();
                return url;
            }
            if (code == 302) {
                //如果会重定向，保存302重定向地址，以及Cookies,然后重新发送请求(模拟请求)
                String locationUrl = conn.getHeaderField("Location");
                print("Douyin locationUrl:" + locationUrl);
                return Douyin_GetRealVideoUrl(locationUrl, new HashMap<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
        String date = df.format(new Date());
        return date;
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



    public  static  int download_failed_time = 0;

    public static  boolean isPortMp4File(File  mp4File){
        if(!mp4File.getName().trim().toLowerCase().endsWith(".mp4")){
            return false;

        }
        android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();

        mmr.setDataSource(mp4File.getAbsolutePath());

        String width = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);//宽
        String height = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);//高
        double width_double  = Double.parseDouble(width);

        double height_double  = Double.parseDouble(height);
        boolean isPortMp4 = height_double  > width_double ? true: false;
        Log.i("isPortMp4File"," isPort="+isPortMp4+"   width="+width+"  height="+height+"   mp4File="+mp4File.getAbsolutePath());

        return isPortMp4;

    }


    public  static void fileCopy(File origin, File target) {
        InputStream input = null;
        OutputStream output = null;
        int lengthSize;
        // 创建输入输出流对象
        try {
            if(!target.getParentFile().exists()){
                target.getParentFile().mkdirs();
            }
            input = new FileInputStream(origin);
            output = new FileOutputStream(target);
            // 获取文件长度
            try {
                lengthSize = input.available();
                // 创建缓存区域
                byte[] buffer = new byte[lengthSize];
                // 将文件中的数据写入缓存数组
                input.read(buffer);
                // 将缓存数组中的数据输出到文件
                output.write(buffer);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (Exception e) {

        } finally {
            if (input != null && output != null) {
                try {
                    input.close(); // 关闭流
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 视频的保存 目录 不能是 当前文件 否则 就会执行 同步操作 影响网速
    // pageUrl 是页面的url   httpUrl 是视频文件的url  并会判断 port land 并返回对应的 mp4_scene_port  mp4_scene_land
    @SuppressWarnings("unchecked")
    public static File downRawVideo_WithUrl(String pageUrl , String httpUrl, String fileNameNoPoint,
                                            String source,File targetPortDirFile ,  File targetLandDirFile) {


        File resultFile = null;
        //	        String fileAddress = videoSavePath+"/"+source+"/"+title+".mp4";
        String fileAddress = targetPortDirFile.getAbsolutePath()+File.separator+getTimeStamp()+".mp4";
        fileAddress = clearChinese(fileAddress);
        fileAddress = fileAddress.replace(" ", "");
        fileAddress = fileAddress.replace("	", "");
        fileAddress = fileAddress.replace("！", "");
        fileAddress = fileAddress.replace("!", "");
        fileAddress = fileAddress.replace("，", "");
        fileAddress = fileAddress.replace("：", "");
        fileAddress = fileAddress.replace("《", "");
        fileAddress = fileAddress.replace("？", "");
        fileAddress = fileAddress.replace("。", "");
        int byteRead;


        try {

            // 获取链接

            System.out.println("downloadByCommonIO_Retry下载["+download_failed_time+"] Begin fileAddress= "+fileAddress);
            System.out.println("downloadByCommonIO_Retry下载["+download_failed_time+"] Begin HttpUrl= "+httpUrl);
            System.out.println("downloadByCommonIO_Retry下载["+download_failed_time+"] Begin PageUrl= "+pageUrl);


//            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7078);
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http代理协议类型

            URL url = new URL(httpUrl);
//				URLConnection conn = url.openConnection(proxy);    // 代理
            URLConnection conn = url.openConnection();
            // 输入流
            long  beginTimeStamp = System.currentTimeMillis();
            System.out.println("Douyin conn.getInputStream 获得 输入流  Begin ( downRawVideo耗时_A 得很) ");
            InputStream inStream = conn.getInputStream();
            long  endTimeStamp = System.currentTimeMillis();
            long distance_second = (endTimeStamp -beginTimeStamp)/1000;


            System.out.println("Douyin conn.getInputStream 获得 输入流  End ( downRawVideo耗时_A【"+distance_second+" 秒】 得很)");


            // 封装一个保存文件的路径对象
            print("Douyin  fileAddress = "+ fileAddress);
            File fileSavePath = new File(fileAddress);
            // 注:如果保存文件夹不存在,那么则创建该文件夹
            print("Douyin  fileAddress = "+ fileAddress+"   fileSavePath="+fileSavePath.getAbsolutePath());
            File fileParent = fileSavePath.getParentFile();
            print("A_Douyin  fileParent = "+ fileParent.getAbsolutePath());
            if (!fileParent.exists()) {
                print("B_Douyin  fileParent = "+ fileParent.getAbsolutePath());
                fileParent.mkdirs();
            }
            print("C_Douyin  fileParent = "+ fileParent.getAbsolutePath());
            // 写入文件
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            print("D_Douyin  fileParent = "+ fileParent.getAbsolutePath());
            byte[] buffer = new byte[1024];
            beginTimeStamp = System.currentTimeMillis();
            print("Douyin FileOutputStream.write  写入本地文件  Begin   比较 downRawVideo_耗时_B ");
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            print("End Douyin FileOutputStream.write  写入本地文件  Begin   比较 downRawVideo_耗时_B End ");
            endTimeStamp = System.currentTimeMillis();
            distance_second = (endTimeStamp -beginTimeStamp)/1000;

            print("Douyin FileOutputStream.write  写入本地文件  End ( downRawVideo_耗时_B【"+distance_second+" 秒】 得很)");

            inStream.close();
            fs.close();
            print("\n-----Douyin 视频保存路径-----\n" + fileSavePath.getAbsolutePath());
            print("\nDouyin zzfile_3.bat " + fileSavePath.getParentFile().getAbsolutePath());


            //  获取文件的 md值   并重命名为 mdxxxx.mp4
            String mdName = getMD5Three(fileSavePath.getAbsolutePath());
            String new_Md_Name = mdName+".mp4";
            tryReName(fileSavePath, new_Md_Name);
            //  把下载的 mp4 文件 名称 转为 md值
            resultFile = new File(targetPortDirFile.getAbsolutePath()+File.separator+new_Md_Name);

            // 判断  port 和 land   ,  如果是 land  那么需要另外单独的操作  复制到 mp4_scene_land  然后 删除 MP4_scene_port

            // 判断是 port 还是 land
            if(!isPortMp4File(resultFile)){  // 如果是 Land


               File  mp4File_Land = new File(targetLandDirFile.getAbsolutePath()+File.separator+new_Md_Name);

                fileCopy(resultFile,mp4File_Land);

                resultFile.delete();

                resultFile = mp4File_Land;
            }




            download_failed_time = 0;
            print("isPortMp4File Douyin downloadByCommonIO_下载["+download_failed_time+"] End  fileAddress="+fileAddress+"  resultFile="+resultFile.getAbsolutePath());
            return resultFile;
        } catch (Exception e) {
            print("Douyin Exception:"+e.getMessage());

            download_failed_time++;
            if(download_failed_time%10 == 0) {
                print("Douyin 程序下载 retry "+download_failed_time+" 次 仍然 下载 失败----放弃");
            }else {
                return downRawVideo_WithUrl( pageUrl ,  httpUrl,  fileNameNoPoint,  source,targetPortDirFile , targetLandDirFile);
            }
            // e.printStackTrace();

            // 	System.out.println(e.getMessage());
        }
        return resultFile;
    }


    // 视频的保存 目录 不能是 当前文件 否则 就会执行 同步操作 影响网速
    // pageUrl 是页面的url   httpUrl 是视频文件的url
    @SuppressWarnings("unchecked")
    public static File downRawVideo_WithUrl(String pageUrl , String httpUrl, String fileNameNoPoint, String source,File targetDirFile) {


        File resultFile = null;
        //	        String fileAddress = videoSavePath+"/"+source+"/"+title+".mp4";
        String fileAddress = targetDirFile.getAbsolutePath()+File.separator+getTimeStamp()+".mp4";
        fileAddress = clearChinese(fileAddress);
        fileAddress = fileAddress.replace(" ", "");
        fileAddress = fileAddress.replace("	", "");
        fileAddress = fileAddress.replace("！", "");
        fileAddress = fileAddress.replace("!", "");
        fileAddress = fileAddress.replace("，", "");
        fileAddress = fileAddress.replace("：", "");
        fileAddress = fileAddress.replace("《", "");
        fileAddress = fileAddress.replace("？", "");
        fileAddress = fileAddress.replace("。", "");
        int byteRead;


        try {

            // 获取链接

            System.out.println("downloadByCommonIO_Retry下载["+download_failed_time+"] Begin fileAddress= "+fileAddress);
            System.out.println("downloadByCommonIO_Retry下载["+download_failed_time+"] Begin HttpUrl= "+httpUrl);
            System.out.println("downloadByCommonIO_Retry下载["+download_failed_time+"] Begin PageUrl= "+pageUrl);


//            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7078);
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http代理协议类型

            URL url = new URL(httpUrl);
//				URLConnection conn = url.openConnection(proxy);    // 代理
            URLConnection conn = url.openConnection();
            // 输入流
            long  beginTimeStamp = System.currentTimeMillis();
            System.out.println("Douyin conn.getInputStream 获得 输入流  Begin ( downRawVideo耗时_A 得很) ");
            InputStream inStream = conn.getInputStream();
            long  endTimeStamp = System.currentTimeMillis();
            long distance_second = (endTimeStamp -beginTimeStamp)/1000;


            System.out.println("Douyin conn.getInputStream 获得 输入流  End ( downRawVideo耗时_A【"+distance_second+" 秒】 得很)");


            // 封装一个保存文件的路径对象
            print("Douyin  fileAddress = "+ fileAddress);
            File fileSavePath = new File(fileAddress);
            // 注:如果保存文件夹不存在,那么则创建该文件夹
            print("Douyin  fileAddress = "+ fileAddress+"   fileSavePath="+fileSavePath.getAbsolutePath());
            File fileParent = fileSavePath.getParentFile();
            print("A_Douyin  fileParent = "+ fileParent.getAbsolutePath());
            if (!fileParent.exists()) {
                print("B_Douyin  fileParent = "+ fileParent.getAbsolutePath());
                fileParent.mkdirs();
            }
            print("C_Douyin  fileParent = "+ fileParent.getAbsolutePath());
            // 写入文件
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            print("D_Douyin  fileParent = "+ fileParent.getAbsolutePath());
            byte[] buffer = new byte[1024];
            beginTimeStamp = System.currentTimeMillis();
            print("Douyin FileOutputStream.write  写入本地文件  Begin   比较 downRawVideo_耗时_B ");
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            print("End Douyin FileOutputStream.write  写入本地文件  Begin   比较 downRawVideo_耗时_B End ");
            endTimeStamp = System.currentTimeMillis();
            distance_second = (endTimeStamp -beginTimeStamp)/1000;

            print("Douyin FileOutputStream.write  写入本地文件  End ( downRawVideo_耗时_B【"+distance_second+" 秒】 得很)");

            inStream.close();
            fs.close();
            print("\n-----Douyin 视频保存路径-----\n" + fileSavePath.getAbsolutePath());
            print("\nDouyin zzfile_3.bat " + fileSavePath.getParentFile().getAbsolutePath());


            //  获取文件的 md值   并重命名为 mdxxxx.mp4
            String mdName = getMD5Three(fileSavePath.getAbsolutePath());
            String new_Md_Name = mdName+".mp4";
            tryReName(fileSavePath, new_Md_Name);
            //  把下载的 mp4 文件 名称 转为 md值
             resultFile = new File(targetDirFile.getAbsolutePath()+File.separator+new_Md_Name);


            download_failed_time = 0;
            print("Douyin downloadByCommonIO_下载["+download_failed_time+"] End  fileAddress="+fileAddress);
            return resultFile;
        } catch (Exception e) {
            print("Douyin Exception:"+e.getMessage());

            download_failed_time++;
            if(download_failed_time%10 == 0) {
                print("Douyin 程序下载 retry "+download_failed_time+" 次 仍然 下载 失败----放弃");
            }else {
               return downRawVideo_WithUrl( pageUrl ,  httpUrl,  fileNameNoPoint,  source,targetDirFile);
            }
            // e.printStackTrace();

            // 	System.out.println(e.getMessage());
        }
        return resultFile;
    }


    public static  boolean tryReName(File curFile, String newName) {
        String newFilePath = curFile.getParent() + File.separator + newName;
        String oldName = curFile.getName();
        File newFile = new File(newFilePath);
        if (newFile.exists() && newFilePath.equals(curFile.getAbsolutePath())) {

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
            System.out.println("当前目录已存在重命名后的文件  文件名称:" + curFile.getName());
            return false; // 已经存在的文件不处理 直接返回

        }
        boolean flag = curFile.renameTo(newFile);
        if (flag) {
            System.out.println(oldName + " 转为 " + newFilePath + " 成功！");
        } else {
            System.out.println(oldName + " 转为 " + newFilePath + " 失败！");
        }
        return flag;
    }

    public static String getDouYinItemId(String url){
        int start = url.indexOf("/video/")+7;
        int end = url.lastIndexOf("/");
        String itemId = url.substring(start, end);
        return  itemId;
    }



    public static boolean isNumeric(String str) {

        if (str == null) {
            return false;
        }

        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        if (str.trim().equals("")) {
            return false;
        }
        return true;
    }

    public static String clearLittleDigital(String result){
        if(result == null) return null;
        result =    result.replace("⁰","");
        result =    result.replace("¹","");
        result =    result.replace("²","");
        result =    result.replace("³","");
        result =    result.replace("⁴","");
        result =    result.replace("⁵","");
        result =    result.replace("⁶","");
        result =    result.replace("⁷","");
        result =    result.replace("⁸","");
        result =    result.replace("⁹","");

        return result;

    }

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

    public static boolean isPad(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y); // 屏幕尺寸
        return screenInches >= 7.0;
    }



    public     static int get_A_All_Count_InJson(String  allJsonStr) {
        int count_AAll_InJson = 0 ;
        if(allJsonStr == null){
            return count_AAll_InJson;
        }

        if(allJsonStr.contains("A_All\",")) {

            String  all_count_beginstr = allJsonStr.substring(allJsonStr.indexOf("A_All\",")+"A_All\",".length());

            if(all_count_beginstr.contains(":") && all_count_beginstr.contains(",")) {

                String mp3_count_str = all_count_beginstr.substring(all_count_beginstr.indexOf(":")+":".length(),all_count_beginstr.indexOf(",")).trim();
//                System.out.println("mp3_count_str = "+ mp3_count_str);
                if(isNumeric(mp3_count_str)) {
                    count_AAll_InJson = Integer.parseInt(mp3_count_str);
                }
            }


        }
        System.out.println("get_A_All_Count_InJson     count_AAll_InJson = "+ count_AAll_InJson);
        return count_AAll_InJson;

    }

    public static String ReadFileContent(File mFilePath) {

        if (mFilePath != null && mFilePath.exists()) {
            // System.out.println("存在 当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 " + mFilePath.getAbsolutePath());

            return null;
        }
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                    continue;
                }

                sb.append(oldOneLine + "\n");
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;

            }
            curBR.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    public  static String getTimeStampyyyyMMdd_HHmmss() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");// 设置日期格式
        String date = df.format(new Date());
        return date;
    }


    @SuppressWarnings("unchecked")
    public static void sendemail(String PcName, String targetEMail, String mTitle, String inputContent,
                                 ArrayList<String> paramTextList, ArrayList<String> extraTextList, HashMap<File, String> imageFile_Desc_Map,
                                 ArrayList<File> attatchFileList) throws Exception {
        // 给用户发送邮件的邮箱
        String from = "382581427@qq.com";
        // 邮箱的用户名
        String username = "382581427@qq.com";
        // 邮箱授权码 // kyioxkexvqdtbjhd【新】 pwkvngnpfkvpbgcd【旧】

        String password = "kyioxkexvqdtbjhd";
        // 发送邮件的服务器地址，QQ服务器
        String host = "smtp.qq.com";
        // 接收人邮箱
        String to = targetEMail;
        if (to == null || "".equals(to.trim())) {
            to = "zukgit@foxmail.com";
        }

        // 邮件主题
//        String title = "邮件主题[测试混合邮件]";
        String title = mTitle;
        if (title == null || "".equals(title.trim())) {
            title = "YYYYHHDD";
        }

        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com"); // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名和密码

        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        print(" SendEmailOperation  session  begin session ");
        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        javax.mail.Session session = javax.mail.Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });
        print(" SendEmailOperation  session  end session =" +  session);
        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
//       session.setDebug(true);

        // 通过 session 得到 transport 对象
        javax.mail.Transport ts = session.getTransport();

        print(" SendEmailOperation  session  end ts =" +  ts);

        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);

        // 创建邮件，写邮件
        // 需要传递 session
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new javax.mail.internet.InternetAddress(from)); // 指明邮件的发件人
        message.setRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to)); // 指明邮件的收件人
        message.setSubject(mTitle); // 邮件主题

        print(" SendEmailOperation  session  end message =" +  message);

        // =========================================== 配置开始
        // ==============================
        // 遍历 imageFile_Desc_Map

        StringBuilder extraSB = new StringBuilder();
        if (extraTextList != null && extraTextList.size() > 0) { // 一些 额外的信息
            extraSB.append("<br><br>═══════ 副文开始 ═══════<br><br>");
            for (int i = 0; i < extraTextList.size(); i++) {
                extraSB.append(extraTextList.get(i) + "<br>");
            }
            extraSB.append("<br><br>═══════ 副文结束 ═══════<br><br>");
        }

        StringBuilder paramSB = new StringBuilder();
        if (paramTextList != null && paramTextList.size() > 0) { // 一些 额外的信息

            for (int i = 0; i < paramTextList.size(); i++) {
                paramSB.append(paramTextList.get(i) + "<br>");
            }

        }
        print(" SendEmailOperation 111_A  session  end_end ts =" +  ts);
        ArrayList<javax.mail.internet.MimeBodyPart> image_desc_MimeBodyPartList = new ArrayList<javax.mail.internet.MimeBodyPart>();
        print(" SendEmailOperation 111_B session  end_end ts =" +  ts);

        javax.mail.internet.MimeBodyPart body_content = new javax.mail.internet.MimeBodyPart(); // 文字内容
        print(" SendEmailOperation 111_CCC session  end_end ts =" +  ts);


        body_content.setContent(inputContent + "<br><br>" + PcName + " " + getTimeStampyyyyMMdd_HHmmss()
                + "<br>═══════ 正文结束 ═══════<br>" + "" + "<br>" + paramSB.toString() + "<br>═══════ 参数结束 ═══════<br><br>"
                + extraSB.toString(), "text/html;charset=utf-8");

        print(" SendEmailOperation 111_D session  end_end ts =" +  ts);

        image_desc_MimeBodyPartList.add(body_content);

        print(" SendEmailOperation 111  session  end_end ts =" +  ts);

        if (imageFile_Desc_Map != null) {

            Map.Entry<File, String> entryItem;
            int item_index = 0;
            int map_size = 0;
            map_size = imageFile_Desc_Map.size();
            Iterator iterator = imageFile_Desc_Map.entrySet().iterator();
            while (iterator.hasNext()) {
                entryItem = (Map.Entry<File, String>) iterator.next();
                File key_file = entryItem.getKey(); // Map的Key
                String value_desc = entryItem.getValue(); // Map的Value
                if (key_file == null) {
                    System.out.println("map[" + item_index + "][" + map_size + "]____" + "key[" + key_file + "]-value["
                            + value_desc + "]" + " 为空! ");
                    continue;
                }

                if (!key_file.exists()) {
                    System.out.println("map[" + item_index + "][" + map_size + "]____" + "key[" + key_file + "]-value["
                            + value_desc + "]" + " 不存在! ");
                    continue;
                }
                System.out.println("map[" + item_index + "][" + map_size + "]____" + "key[" + key_file + "]-value["
                        + value_desc + "]");

                MimeBodyPart body_image = new MimeBodyPart();
                body_image.setDataHandler(new javax.activation.DataHandler(
                        new javax.activation.FileDataSource(key_file.getAbsolutePath())));
                body_image.setContentID(key_file.getName());

                // 文本
                // " 图片描述:fafa <img src='cid:1.png'>", "text/html;charset=utf-8"
//   		        body2.setContent("我不是广告，<img src='cid:1.png'>", "text/html;charset=utf-8");
                String image_item_desc = value_desc + " <img src='cid:" + key_file.getName() + "'>";
                MimeBodyPart body_text = new MimeBodyPart();
                body_text.setContent(image_item_desc, "text/html;charset=utf-8");

                image_desc_MimeBodyPartList.add(body_image);
                image_desc_MimeBodyPartList.add(body_text);

                item_index++;
            }
        }
        print(" SendEmailOperation 112  session  end_end ts =" +  ts);

        MimeMultipart image_desc_MimeMultipart = null;
        if (image_desc_MimeBodyPartList != null && image_desc_MimeBodyPartList.size() > 0) {
            image_desc_MimeMultipart = new MimeMultipart();

            for (int i = 0; i < image_desc_MimeBodyPartList.size(); i++) {
                image_desc_MimeMultipart.addBodyPart(image_desc_MimeBodyPartList.get(i));
            }
            image_desc_MimeMultipart.setSubType("related"); // 文本和图片内嵌成功
        }
        print(" SendEmailOperation 113  session  end_end ts =" +  ts);

        ArrayList<MimeBodyPart> attatch_MimeBodyPartList = new ArrayList<MimeBodyPart>();
        print(" SendEmailOperation 114  session  end_end ts =" +  ts);

        if (attatchFileList != null && attatchFileList.size() > 0) {

            for (int i = 0; i < attatchFileList.size(); i++) {
                File attatchFile = attatchFileList.get(i);

                MimeBodyPart body_attatch = new MimeBodyPart();
                body_attatch.setDataHandler(new javax.activation.DataHandler(
                        new javax.activation.FileDataSource(attatchFile.getAbsolutePath())));
                body_attatch.setFileName(attatchFile.getName());
                attatch_MimeBodyPartList.add(body_attatch);
            }

        }
        print(" SendEmailOperation 115  session  end_end ts =" +  ts);

        // 将拼装好的正文内容设置为主体
        MimeBodyPart contentText = new MimeBodyPart();
        if (image_desc_MimeMultipart != null) {
            contentText.setContent(image_desc_MimeMultipart);
        }
        print(" SendEmailOperation 116  session  end_end ts =" +  ts);

        // 拼接附件与正文内容
        MimeMultipart all_MimeMultipart = new MimeMultipart();
        all_MimeMultipart.addBodyPart(contentText);
        for (int i = 0; i < attatch_MimeBodyPartList.size(); i++) {
            all_MimeMultipart.addBodyPart(attatch_MimeBodyPartList.get(i)); // 添加附件
        }
        all_MimeMultipart.setSubType("mixed");
        print(" SendEmailOperation 117  session  end_end ts =" +  ts);

        // =========================================== 配置结束
        // ==============================
        // 设置到消息中，保存修改
        message.setContent(all_MimeMultipart); // 把最后编辑好的邮件放到消息当中
        message.saveChanges(); // 保存修改
        print(" SendEmailOperation 118  session  end_end ts =" +  ts);

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        print(" SendEmailOperation 119  session  end_end ts =" +  ts);

        print(" SendEmailOperation  session  end_end ts =" +  ts);

        // 释放资源
        ts.close();

    }

    public static File[] invokeGetFileListFromFragmentName(String fragmentName) {
        System.out.println("fragment_name_B = " + fragmentName);
        File[] matchFileArr = null;
        if (fragmentName == null) {
            return matchFileArr;
        }

        switch (fragmentName) {
            case "Video_Common_Land_Fragment":
                return DataHolder.Video_Common_Land_file_list ;

            case "Video_Common_Port_Fragment":
                return DataHolder.Video_Common_Port_file_list;

            case "Video_English_Land_Fragment":
                return DataHolder.Video_English_Land_file_list;

            case "Video_Gaokao_Land_Fragment":
                return DataHolder.Video_Gaokao_Land_file_list;

            case "Video_Home_Land_Fragment":
                return DataHolder.Video_Home_Land_file_list;

            case "Video_Home_Port_Fragment":
                return DataHolder.Video_Home_Port_file_list;

            case "Video_Kaoyan_Land_Fragment":
                return DataHolder.Video_Kaoyan_Land_file_list;

            case "Video_Music_Land_Fragment":
                return DataHolder.Video_Music_Land_file_list;

            case "Video_Music_Port_Fragment":
                return DataHolder.Video_Music_Port_file_list;

            case "Video_Scene_Land_Fragment":
                return DataHolder.Video_Scene_Land_file_list;

            case "Video_Scene_Port_Fragment":
                return DataHolder.Video_Scene_Port_file_list;

            case "Video_Zhexue_Land_Fragment":
                return DataHolder.Video_Zhexue_Land_file_list;


            default:

        }


            return matchFileArr;
    }
}