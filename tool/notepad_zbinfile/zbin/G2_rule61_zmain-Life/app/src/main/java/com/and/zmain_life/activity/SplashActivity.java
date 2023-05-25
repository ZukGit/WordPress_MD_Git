package com.and.zmain_life.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.and.zmain_life.R;
import com.and.zmain_life.base.BaseActivity;
import com.permissionx.guolindev.PermissionX;


public class SplashActivity extends BaseActivity {
    private String TAG = "SplashActivity";


    void gotoMainPage(){
        new CountDownTimer(500, 500) {
            @Override
            public void onTick(long millisUntilFinished) { }
            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }







    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void init() {
        setFullScreen();
        android.util.Log.i(TAG,"init");
//        requestFinger();


        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            checkStorageManagerPermission(getApplicationContext());

        }


        gotoMainPage();
    }




    public static void checkStorageManagerPermission ( Context context ) {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager ( ) ) {
            Toast.makeText(context,"安卓R(11)需要申请ScopedStorage权限",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent ( Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION ) ;
            intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK ) ;
            context.startActivity ( intent ) ;

        }else{

//            Toast.makeText(context,"安卓R(11)需要申请ScopedStorage权限",Toast.LENGTH_SHORT).show();

            Log.i("SplashActivity","安卓Q(10)及以下 不 需要申请ScopedStorage权限");

        }

    }

}