package com.and.zmain_life.bean;

import android.content.Context;
import android.icu.text.Collator;
import android.media.ExifInterface;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.and.zmain_life.R;
import com.and.zmain_life.node.NodeImpl;
import com.and.zmain_life.utils.RandomUtil;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

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
import org.jsoup.Connection;

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
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// -------------- POI --------------


public  class FunctionHolder {


  static  String TAG = "FunctionHolder";


    static void print(String log){

        Log.i(TAG,log);

  }

    void getWifiManager(Context mContext){


        WifiManager wifiManager = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);

      Method[] wifiMethodArr =   wifiManager.getClass().getMethods();

      if(wifiMethodArr == null){
          print("Method[] wifiMethodArr   为空!");
          return ;
      }


        for (int i = 0; i < wifiMethodArr.length; i++) {
            Method wifiMethod = wifiMethodArr[i];
            print("wifi-method["+i+"] = "+buildMethodTip(wifiMethod));
        }

    }


    static String buildMethodTip( Method xMethod ){

        if(xMethod == null){
            return " Method 为空!";
        }
        StringBuilder sb = new StringBuilder();


        Annotation[]  getDeclaredAnnotations =  xMethod.getDeclaredAnnotations();

        Object getDefaultValue =    xMethod.getDefaultValue();

        Class<?>  getDeclaringClass =  xMethod.getDeclaringClass();

        Class<?> getClass =   xMethod.getClass();

        Class<?>[]  getExceptionTypes =  xMethod.getExceptionTypes();

        Type[]    getGenericExceptionTypes =      xMethod.getGenericExceptionTypes();


        Type[]  getGenericParameterTypes =   xMethod.getGenericParameterTypes();

        Type getGenericReturnType =   xMethod.getGenericReturnType();

         int getModifiers =  xMethod.getModifiers();

       String getName =  xMethod.getName();


        String toGenericString =    xMethod.toGenericString();

//        Parameter[]  getParameters =   xMethod.getParameters();
//       int  getParameterCount =  xMethod.getParameterCount();

        String toString =   xMethod.toString();

        sb.append("toString【"+toString+"】");


        return sb.toString();


    }
}