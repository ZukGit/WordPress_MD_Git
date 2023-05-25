package com.and.zmain_life.wireless;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import  java.util.ArrayList;
import com.and.zmain_life.R;

import java.lang.reflect.Field;


// 0. Wifi点击弹框的增加
// 1.Androidmanifest.xml 的 修改
// 2. res/layout 文件的添加
// 3. Activity 文件的增加处理
public   class Wireless_Wifi_Activity extends WirelessBaseActivity {
    WifiManager mWifiManager ;


    @Override
    protected void init() {
        mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManagerInfoBuild(mWifiManager);

    }





    String getDetailField(Field  mField ){
        String mFieldName = mField.getName();


        String mFieldGenericString=  mField.toGenericString();
        String mFieldtoString=  mField.toString();

        String mTypeSimpleName= mField.getType().getSimpleName();
        String mTypeName= mField.getType().getName();
        String mTypetoString= mField.getType().toString();
        String mTypeCanonicalName= mField.getType().getCanonicalName();

        return "mFieldName["+mFieldName+"]  mFieldGenericString["+mFieldGenericString+"] mFieldtoString["+mFieldtoString+"] mTypeSimpleName["+mTypeSimpleName+"] " +
                "mTypeName["+mTypeName+"]  mTypetoString["+mTypetoString+"]  mTypeCanonicalName["+mTypeCanonicalName+"]";
    }


    String getDetailMethod(Method  mMethod ){
        String mMethodName = mMethod.getName();
        String mMethodtoString =  mMethod.toString();
        String mMethodtoGenericString =  mMethod.toGenericString();
        Parameter[] mMethodParameterArrs = null ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             mMethodParameterArrs =  mMethod.getParameters();
        }
        int mMethodParams_Length = 0 ;
        StringBuilder sb = new StringBuilder();
        if(mMethodParameterArrs != null){
            mMethodParams_Length = mMethodParameterArrs.length;



            for (int i = 0; i < mMethodParameterArrs.length; i++) {
                Parameter mParameter = mMethodParameterArrs[i];

             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
              String mParamName =      mParameter.getName();
                 String mParamtoString =      mParameter.toString();
                 String mParamTypeName =       mParameter.getType().getName();
                 String mParamTypeSimpleName =       mParameter.getType().getSimpleName();
                 String mParamTypeCanonicalName =       mParameter.getType().getCanonicalName();
                 String mParamTypeGenericString =       mParameter.getType().toGenericString();
                 String mParamTypetoString =       mParameter.getType().toString();

                 sb.append(i+"mParamName["+mParamName+"] "+i+"mParamtoString["+mParamtoString+"] "+i+"mParamTypeName["+mParamTypeName+"]  "+i+"mParamTypeSimpleName["+mParamTypeSimpleName+"] "+i+"mParamTypeCanonicalName["+mParamTypeCanonicalName+"]  "+i+"mParamTypeGenericString["+mParamTypeGenericString+"] "+i+"mParamTypetoString["+mParamTypetoString+"]");
                }


            }
        }

        return "mMethodName["+mMethodName+"]  mMethodtoString["+mMethodtoString+"] mMethodtoGenericString["+mMethodtoGenericString+"] mMethodParams_Length["+mMethodParams_Length+"] " +
                sb.toString();
    }

    Method[]  getSpecialMethod(Method[]  rawMethodArr){
        Method[] fliter_Method_arr = null ;

        Object obj = new Object();
        Method[] mObjectMethods =  obj.getClass().getDeclaredMethods();

        if(mObjectMethods != null){
            Log.i("Wireless_Wifi","mObjectMethods_Size["+mObjectMethods.length+"] ! ");
        }else{
            Log.i("Wireless_Wifi","mObjectMethods_Size[ 0 ] !  mObjectMethods is null ! ");
        }


        ArrayList<Method> specialMethod = new   ArrayList<Method>();

        A: for (int i = 0; i < rawMethodArr.length; i++) {

            Method rawMethod = rawMethodArr[i];
            String rawMethod_name = rawMethod.getName();

            boolean isObjectMethod = false;


            B:  for (int j = 0; j < mObjectMethods.length; j++) {
                Method objMethod = mObjectMethods[j];
                String objMethod_name = objMethod.getName();
                Log.i("Wireless_Wifi","rawMethod_name["+i+"_"+rawMethodArr.length+":"+rawMethod_name+"]_objectMethod["+j+"_"+mObjectMethods.length+":"+objMethod_name+"]");
                if(rawMethod_name.equals(objMethod_name)){
                    isObjectMethod = true;
                    break B;
                }
            }

            if(!isObjectMethod){
                Log.i("Wireless_Wifi","rawMethod_name["+i+"_"+rawMethodArr.length+":"+rawMethod_name+"]  Is Special Method  getDetailMethod="+getDetailMethod(rawMethod));

                specialMethod.add(rawMethod);
            }
        }


        if(specialMethod.size() == 0){
            return null;
        }

        fliter_Method_arr = new Method[specialMethod.size()];
        for (int i = 0; i < specialMethod.size(); i++) {
            fliter_Method_arr[i] = specialMethod.get(i);
        }
        return fliter_Method_arr;

    }

    Field[]  getSpecialFileld(Field[]  rawFieldArr){
        Field[] fliter_field_arr = null ;

        Object obj = new Object();
        Field[] mObjectFields =  obj.getClass().getDeclaredFields();

        if(mObjectFields != null){
            Log.i("Wireless_Wifi","mObjectFields_Size["+mObjectFields.length+"] ! ");
        }else{
            Log.i("Wireless_Wifi","mObjectFields_Size[ 0 ] !  mObjectFields is null ! ");
        }


        ArrayList<Field> specialField = new   ArrayList<Field>();

       A: for (int i = 0; i < rawFieldArr.length; i++) {

            Field rawField = rawFieldArr[i];
            String rawField_name = rawField.getName();
            boolean isObjectField = false;


          B:  for (int j = 0; j < mObjectFields.length; j++) {
                Field objField = mObjectFields[j];
                String objField_name = objField.getName();
              Log.i("Wireless_Wifi","rawField_name["+i+"_"+rawFieldArr.length+":"+rawField_name+"]_objectField["+j+"_"+mObjectFields.length+":"+objField_name+"]");
                if(rawField_name.equals(objField_name)){
                    isObjectField = true;
                    break B;
                }
            }

            if(!isObjectField){
                Log.i("Wireless_Wifi","rawField_name["+i+"_"+rawFieldArr.length+":"+rawField_name+"]  Is Special Field DetailField="+getDetailField(rawField)+" ! ");

                specialField.add(rawField);
            }
        }


        if(specialField.size() == 0){
            return null;
        }

      fliter_field_arr = new Field[specialField.size()];
        for (int i = 0; i < specialField.size(); i++) {
            fliter_field_arr[i] = specialField.get(i);
        }
        return fliter_field_arr;

    }
    protected void wifiManagerInfoBuild(WifiManager mParam1)  {
        ArrayList<String> mWifiManagetInfoSortedList = new   ArrayList<String>();
        ArrayList<String> mWifiManagetFieldInfoList = new   ArrayList<String>();
        ArrayList<String> mWifiManagetMethodInfoList = new   ArrayList<String>();

        String mClassName   =  mParam1.getClass().getSimpleName();
        String mClassSimpleName   = mParam1.getClass().getSimpleName();
        String mClassCanonicalName   =   mParam1.getClass().getCanonicalName();
        String mClassTypeName   =   null ;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             mClassTypeName   =  mParam1.getClass().getTypeName();
        }



        String mPackagetoString   = mParam1.getClass().getPackage().toString();
       String mPackageName  =  mParam1.getClass().getPackage().getName();

       String mClassPathName = mPackageName+"."+mClassName;

// headTip = ══════ mClassName[WifiManager] simpleName[WifiManager]  mClassCanonicalName[android.net.wifi.WifiManager]  mClassTypeName[android.net.wifi.WifiManager]  mPackagetoString[package android.net.wifi, Unknown, version 0.0]  mPackageName[android.net.wifi]══════
        String headTip = "══════ mClassName["+mClassName+"] simpleName["+mClassSimpleName+"]  mClassCanonicalName["+mClassCanonicalName+"]  mClassTypeName["+mClassTypeName+"]  mPackagetoString["+mPackagetoString+"]  mPackageName["+mPackageName+"]══════";
        Log.i("wifiManagerInfoBuild", " headTip = "+ headTip);

        Field[] mDeclaredFields = mParam1.getClass().getDeclaredFields();
        Field[] mSpecialDeclaredFields = getSpecialFileld(mDeclaredFields);

        Method[] mDeclaredMethods =   mParam1.getClass().getDeclaredMethods();
        Method[] mSpecialDeclaredMethods = getSpecialMethod(mDeclaredMethods);



        int mDeclaredFields_Length = 0;
        int mSpecialDeclaredFields_Length = 0;



        if(mDeclaredFields != null){
            mDeclaredFields_Length = mDeclaredFields.length ;
        }
        if(mSpecialDeclaredFields != null){
            mSpecialDeclaredFields_Length = mSpecialDeclaredFields.length ;
        }

        int mSpecialDeclaredMethods_Length = 0;
        if(mSpecialDeclaredMethods != null){
            mSpecialDeclaredMethods_Length = mSpecialDeclaredMethods.length ;
        }


        if(mSpecialDeclaredFields != null){

            for (int i = 0; i < mSpecialDeclaredFields.length; i++) {
                Field mField = mSpecialDeclaredFields[i];
                try {

//                    mFieldGenericString[public static final int android.net.wifi.WifiManager.WPS_WEP_PROHIBITED]
//                    mClassCanonicalName[android.net.wifi.WifiManager]
//                    Value:4

                    mField.setAccessible(true);

                    String mFieldGenericString = mField.toGenericString();  // [public static final int android.net.wifi.WifiManager.WPS_WEP_PROHIBITED]


                    String field_value_str = mField.get(mParam1).toString();   // 4

                    String field_show_str = mFieldGenericString +" = "+ field_value_str +";" ;
                    field_show_str = field_show_str.replace(mClassCanonicalName+".","");
                    field_show_str = field_show_str.replace("java.lang.","");


                    Log.i("detail_field","detail_field["+i+"_"+mSpecialDeclaredFields.length+"]  mFieldName:"+mField.getName()+  "mFiledType="+mField.getType().getName()+ "  Value:"+mField.get(mParam1).toString());

                    mWifiManagetFieldInfoList.add(field_show_str);

                } catch (Exception e) {
                    Log.i("detail_field","detail_field["+i+"_"+mSpecialDeclaredFields.length+"]:"+mField.getName()+" Exception! ");
                }
            }


        }

        for (int i = 0; i < mWifiManagetFieldInfoList.size(); i++) {

            mWifiManagetInfoSortedList.add("Filed["+(i+1)+"_"+mWifiManagetFieldInfoList.size()+"]  "+ mWifiManagetFieldInfoList.get(i));


        }


        if(mSpecialDeclaredMethods != null){

            for (int i = 0; i < mSpecialDeclaredMethods.length; i++) {
                Method  mMethod = mSpecialDeclaredMethods[i];

               String method_show_str =  mMethod.toGenericString();

                method_show_str =method_show_str.replace(mClassCanonicalName+".","");
                method_show_str = method_show_str.replace("java.lang.","");

                mWifiManagetMethodInfoList.add(method_show_str);

            }

        }
        mWifiManagetInfoSortedList.add("");
        mWifiManagetInfoSortedList.add("");
        mWifiManagetInfoSortedList.add("");
        mWifiManagetInfoSortedList.add("");

        for (int i = 0; i < mWifiManagetMethodInfoList.size(); i++) {

            mWifiManagetInfoSortedList.add("Method["+(i+1)+"_"+mWifiManagetMethodInfoList.size()+"]  "+ mWifiManagetMethodInfoList.get(i));

        }


        Toast.makeText(this,"mDeclaredFields_Length["+mDeclaredFields_Length+"]\n mSpecialDeclaredFields_Length【"+mSpecialDeclaredFields_Length+"】\nmSpecialDeclaredMethods_Length【"+mSpecialDeclaredMethods_Length+"】",Toast.LENGTH_SHORT).show();





        for (int i = 0; i < mWifiManagetInfoSortedList.size() ; i++) {
            String one_line  = mWifiManagetInfoSortedList.get(i);
            Log.i("WifiManagerInfo",one_line);
        }

    }

    @Override
    protected  int setLayoutId(){

      return R.layout.wireless_wifi_main_layout;

    }


}
