package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import java.io.File;

public class StorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        //外部存储私有目录
        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        //外部存储公共目录
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //内部存储空间 /data/data目录
        File filesDir = getFilesDir();

    }
}