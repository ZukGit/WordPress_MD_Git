package com.and.zmain_life.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;

public class RandomUtil {



    public   static  ArrayList<Integer> getRandomLengthArr(int length ){
        if(length <= 0){
            return null;
        }

        ArrayList<Integer> indexArr = new   ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            indexArr.add(i);
        }
        Random rd_1 = new Random(indexArr.size());
        Random rd_2 = new Random();
        Random rd_3 = new Random(100);

        for (int i = 0; i < indexArr.size(); i++) {
            int curIndexValue = indexArr.get(i);
            int randomIndex = (int)(Math.abs(rd_1.nextGaussian() * length + System.currentTimeMillis() + System.currentTimeMillis()%length + rd_2.nextGaussian() * length +  rd_3.nextGaussian() * Math.PI)%length);
            int randomIndexValue = indexArr.get(randomIndex);

            if(curIndexValue == randomIndexValue ){
                continue;
            }

            randomIndexValue = curIndexValue + randomIndexValue;
            curIndexValue = randomIndexValue - curIndexValue;
            randomIndexValue = randomIndexValue - curIndexValue;
            indexArr.set(i,curIndexValue);
            indexArr.set(randomIndex,randomIndexValue);
        }

        return indexArr;
    }

}
