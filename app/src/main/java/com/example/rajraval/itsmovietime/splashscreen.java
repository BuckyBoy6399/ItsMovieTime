package com.example.rajraval.itsmovietime;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splashscreen);
//        Dexter.withActivity(splashscreen.this)
//        .withPermissions(Manifest.permission.INTERNET,
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.BLUETOOTH).withListener(new MultiplePermissionsListener() {
//            @Override
//            public void onPermissionsChecked(MultiplePermissionsReport report) {
//                if(report.areAllPermissionsGranted()){
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent i = new Intent(splashscreen.this, loginpage.class);
//                            startActivity(i);
//                            finish();
//                        }
//                    },1000);
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Grant Permissions",Toast.LENGTH_LONG).show();
//                    finish();
//                }
//
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//
//            }
//        }).check();
        if(ContextCompat.checkSelfPermission(splashscreen.this,Manifest.permission.INTERNET)+
                ContextCompat.checkSelfPermission(splashscreen.this,Manifest.permission.READ_EXTERNAL_STORAGE)+
                ContextCompat.checkSelfPermission(splashscreen.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(splashscreen.this,new String[]{Manifest.permission.INTERNET,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
        else
        {
            runmethod();
        }

    }
    public void runmethod()
    {
        if(AppManager.spGetBoolean(splashscreen.this,AppConst.LOGGED_IN)){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashscreen.this, loginpage.class);
                startActivity(i);
                finish();
            }
        },1000);
    }
    else
        {
            Intent i = new Intent(splashscreen.this, loginpage.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100)
        {
            runmethod();
        }
        else
        {
            Toast.makeText(splashscreen.this,"Please Grant Permissions",Toast.LENGTH_LONG).show();
        }
    }
}

