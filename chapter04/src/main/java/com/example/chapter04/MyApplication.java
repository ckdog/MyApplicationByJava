package com.example.chapter04;

import android.app.Application;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.chapter04.database.BookDao;
import com.example.chapter04.database.BookDataBase;
import com.example.chapter04.database.ShoppingDBHelper;
import com.example.chapter04.domain.GoodsInfo;
import com.example.chapter04.utils.FileUtils;
import com.example.chapter04.utils.SharedUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyApplication extends Application {

    private static MyApplication mapp;
    public Map<String, String> map = new HashMap<>();

    private static BookDataBase bookDataBase;

    public int countInCart;

    public static MyApplication getInstance(){
        if(mapp != null){
            return mapp;
        }
        mapp = new MyApplication();
        return mapp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "自定义应用组件创建: ");
        bookDataBase = Room.databaseBuilder(this, BookDataBase.class, "book")
                .addMigrations()    //允许迁移数据库。数据库变更时room默认删除原来的数据库创建一个新的。会导致数据丢失
                .allowMainThreadQueries() //允许主线程操作数据库。默认不允许
                .build();
        //商城项目  商品信息初始化
        initGoodProjectInfo();

    }

    private void initGoodProjectInfo(){
        //判断共享参数，是否为首次打开
        boolean firstRun = SharedUtils.getInstance(this).readBoolean("firstRun", true);
        //获取当前app的私有下载路径
        final String dir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separator;
        if(firstRun){
            SharedUtils.getInstance(this).writeBoolean("firstRun", true);
            List<GoodsInfo> defaultList = GoodsInfo.getDefaultList();
            defaultList.stream().forEach(goods -> {
                //读取图片文件
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), goods.getPic());
                String picPath = dir + goods.getId() + ".jpg";
                //将图片保存到对应的目录下
                try {
                    FileUtils.saveImageToExternalFileDir(picPath, bitmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //回收位图资源 避免内存浪费
                bitmap.recycle();
                goods.setPicPath(picPath);
            });
            ShoppingDBHelper instance = ShoppingDBHelper.getInstance(this);
            SQLiteDatabase wdb = instance.openWriteDB();
            instance.insertGoods(defaultList);
            //关闭数据库联机
            instance.closeLink();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("TAG", "onTerminate: ");
    }

    /**
     * 横屏、竖屏调整
     * @param newConfig The new device configuration.
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("TAG", "onConfigurationChanged: ");
    }


    public BookDataBase getBookDB(){
        return bookDataBase;
    }
}
