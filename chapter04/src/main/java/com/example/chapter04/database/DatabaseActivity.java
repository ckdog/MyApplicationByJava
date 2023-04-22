package com.example.chapter04.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.chapter04.R;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


    }

    private void data(){
        String filedir = getFilesDir() + "/test1.db";
        //创建数据库sqllite
        SQLiteDatabase database = openOrCreateDatabase(filedir, Context.MODE_PRIVATE, null);
        //删除数据库
        boolean b = deleteDatabase(filedir);
        //常用的数据管理
        /**
         * openDatabase 打开指定的路径数据库
         * isOpen 判断数据库是否打开
         * close  关闭数据库
         * getVersion   获取数据库版本
         * setVersion   设置数据库版本
         * beginTransaction 开始事务
         * setTransactionSuccessful 设置事务的成功标识
         * endTransaction 结束事务
         * execSQL 执行sql
         * delete 删除符合条件的sql
         * update 更新符合条件的记录
         * insert 插入一条记录
         * qury 执行查询
         * rawQuery 执行查询并返回结果集的游标
         *
         */
    }
}