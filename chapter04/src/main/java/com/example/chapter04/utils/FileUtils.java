package com.example.chapter04.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.security.FileIntegrityManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {


    public static void saveImageToExternalFileDir(String path, Bitmap bitmap) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path));
        //把bitmap文件压缩到输出流
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.flush();
        if(fos != null){
            fos.close();
        }
    }


    public static Bitmap readImageFromExternalFileDir(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
        if(fileInputStream != null){
            fileInputStream.close();
        }
        return bitmap;
    }
}
