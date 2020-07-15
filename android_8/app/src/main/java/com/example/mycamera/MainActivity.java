package com.example.mycamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;
    private static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final int REQUEST_EXTERNAL_CAMERA = 101;
    String[] permissions = new String[] {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.img);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_tkvideo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (isPermissionsReady(this, permissions)) {
            openVideoRecordApp();
        } else {
            reuqestPermissions(this, permissions, REQUEST_EXTERNAL_CAMERA);
        }
    }

    private void openVideoRecordApp() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(takeVideoIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_CAMERA: {
                if (isPermissionsReady(this, permissions)) {
                    openVideoRecordApp();
                }
                break;
            }
        }
    }
    public static boolean isPermissionsReady(Activity activity, String[] permissions) {
        if (permissions == null || android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public static void reuqestPermissions(Activity activity, String[] permissions, int requestCode) {
        if (permissions == null || android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        List<String> mPermissionList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }
}