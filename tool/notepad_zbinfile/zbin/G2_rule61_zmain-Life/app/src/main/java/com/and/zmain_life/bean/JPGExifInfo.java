package com.and.zmain_life.bean;

import android.content.Context;
import android.icu.text.Collator;
import android.media.ExifInterface;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.and.zmain_life.R;
import com.and.zmain_life.node.NodeImpl;
import com.and.zmain_life.utils.RandomUtil;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// -------------- POI --------------


public 	class JPGExifInfo{
    File rawFile ;

    public	String mImageModel_Utf8;  // 股票名称
    public	String mImageMake_Utf8;  // ts代码
    public	String mImageArtist_Utf8;  // 股票名称
    public	String mImageCopyright_Utf8;  // ts代码
    public	String mImageDescription_Utf8;  // 股票名称
    public	String mPhotoUserComment_Utf8;  // ts代码


    JPGExifInfo(File mFile){
        rawFile = mFile;
        initTagProp(rawFile);
    }

    void initTagProp(File jpgFile) {
        String fileName = jpgFile.getAbsolutePath().toLowerCase().trim();
        if(!fileName.endsWith(".jpg")) {
            return;
        }
        initExifInfo(jpgFile);

        System.out.println("mImageModel_Utf8="+mImageModel_Utf8+"  mImageMake_Utf8="+mImageMake_Utf8+"  mImageArtist_Utf8="+mImageArtist_Utf8
                +"  mImageCopyright_Utf8="+mImageCopyright_Utf8+"  mImageDescription_Utf8="+mImageDescription_Utf8+" mPhotoUserComment_Utf8="+mPhotoUserComment_Utf8);


    }



    void initExifInfo(File file) {

        mImageDescription_Utf8 = null;
        mImageMake_Utf8 = null;
        mImageModel_Utf8 = null;
        mImageArtist_Utf8 = null;
        mImageCopyright_Utf8 = null;
        mPhotoUserComment_Utf8 = null;

        int angel = 0;
        Metadata metadata;

        try {
            metadata = JpegMetadataReader.readMetadata(file);
            metadata.getDirectories();

            // zukgit_directory [Exif IFD0] - Orientation = Right side, top (Rotate 90 CW)
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    // 格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
//						System.out.format("zukgit_directory  [%s] - %s = %s\n", directory.getName(), tag.getTagName(),tag.getDescription());

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

//							System.out.println("XXmImageDescription=["+mImageDescription+"]  Utf8["+mImageDescription_Utf8+"]");
//							System.out.println("XXmImageMake=["+mImageMake+"]  Utf8["+mImageMake_Utf8+"]");
//							System.out.println("XXmImageModel=["+mImageModel+"]  Utf8["+mImageModel_Utf8+"]");
//							System.out.println("XXmImageArtist=["+mImageArtist+"]  Utf8["+mImageArtist_Utf8+"]");
//							System.out.println("XXmImageCopyright=["+mImageCopyright+"]  Utf8["+mImageCopyright_Utf8+"]");

                    }

                    if ("Exif SubIFD".equals(directory.getName())) {

                        if ("User Comment".equals(tag.getTagName())) {
                            String mPhotoUserComment = tag.getDescription();
//						System.out.println("AZ_User_Comment=["+tag.getDescription()+"]");
                            if (mPhotoUserComment != null)
                                mPhotoUserComment_Utf8 = new String(mPhotoUserComment.getBytes(), "utf-8");
//						System.out.println("AZXXmPhotoUserComment=["+mPhotoUserComment+"]   mPhotoUserComment_Utf8=["+mPhotoUserComment_Utf8+"]" );

                        }

                    }
                }

            }

        } catch (JpegProcessingException e) {
            e.printStackTrace();
            System.out.println("JpegProcessingException  异常事件发生 ");
        } catch (IOException e) {
            System.out.println("IOException  异常事件发生 ");
            e.printStackTrace();
        }

//			String mImageArtist_CategoryStr= null;
//			String mImageCopyright_VideoMD = null;
//		    String mImageDescription_SelfDesc = null;
//
//
//		String mImageMake_Utf8 = null;     // 待定
//		String mImageModel_Utf8 = null;     // 待定
//		String mPhotoUserComment_Utf8 = null;   // 待定

        if (mImageArtist_Utf8 != null) {
            System.out.println(" mImageArtist_Utf8 = "+ mImageArtist_Utf8);

        } else {
            System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageArtist_CategoryStr !! ");
        }

        if (mImageDescription_Utf8 != null) {
            System.out.println(" mImageDescription_Utf8 = "+ mImageArtist_Utf8);

        } else {
            System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageDescription_SelfDesc !! ");
        }

        if (mImageCopyright_Utf8 != null) {

            System.out.println(" mImageCopyright_Utf8 = "+ mImageCopyright_Utf8);

        } else {
            System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageCopyright_VideoMD !! ");
        }

        if (mImageMake_Utf8 != null) {
            System.out.println(" mImageMake_Utf8 = "+ mImageMake_Utf8);
        } else {
            System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageMake_Utf8 !! ");
        }

        if (mImageModel_Utf8 != null) {
            System.out.println(" mImageModel_Utf8 = "+ mImageModel_Utf8);
        } else {
            System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mImageModel_Utf8 !! ");
        }

        if (mPhotoUserComment_Utf8 != null) {
            System.out.println(" mPhotoUserComment_Utf8 = "+ mPhotoUserComment_Utf8);
        } else {
            System.out.println("当前文件 [" + file.getName() + "] 没有读取到 mPhotoUserComment_Utf8 !! ");
        }

    }


}