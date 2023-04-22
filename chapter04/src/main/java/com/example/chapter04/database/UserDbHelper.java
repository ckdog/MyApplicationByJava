package com.example.chapter04.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.chapter04.domain.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user";
    private static final int DB_VERSION = 1;

    private static UserDbHelper helper = null;

    private SQLiteDatabase rdb;
    private SQLiteDatabase wdb;

    private static final String TABLE_NAME = "USER_INFO";

    public UserDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private UserDbHelper getIntance(Context context){
        if(helper != null){
            return helper;
        }
        helper = new UserDbHelper(context);
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userdb = "CREATE TABLE IF NOT EXISTS USER_INFO(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR NOT NULL," +
                "age INTEGER NOT NULL," +
                "height LONG NOT NULL," +
                "weight FLOAT NOT NULL," +
                "married INTEGER NOT NULL);";
        db.execSQL(userdb);
    }

    public SQLiteDatabase openRead(){
        if(rdb == null || !rdb.isOpen()){
            rdb = helper.getReadableDatabase();
        }
        return rdb;
    }

    public SQLiteDatabase openWrite(){
        if(wdb == null || !wdb.isOpen()){
            wdb = helper.getWritableDatabase();
        }
        return wdb;
    }

    public void close(){
        if(rdb != null || rdb.isOpen()){
            rdb.close();
            rdb = null;
        }
        if(wdb != null || wdb.isOpen()){
            wdb.close();
            rdb = null;
        }
    }

    public void insert(User user) throws Exception {
        ContentValues contentValues = new ContentValues();
        Class<? extends User> userClazz = user.getClass();
        Field[] declaredFields = userClazz.getDeclaredFields();
        for(Field field : declaredFields){
            field.setAccessible(true);
            contentValues.put(field.getName(), (String) field.get(user));
        }
        //2th 参数代表列名称，为了符合标准sql而设置，如果每个字段都有值，不需要该参数
        long insert = wdb.insert(TABLE_NAME, null, contentValues);
        // -1 表示插入失败
    }

    public void deleteByColume(String name){
        wdb.delete(TABLE_NAME, "name =?", new String[]{name});
    }

    public void update(User user){
        ContentValues values = new ContentValues();
        wdb.update(TABLE_NAME, values, "name=?", new String[]{user.getName()});
    }

    public List<User> queryAll(){
        List<User> users = new ArrayList<>();
        Cursor cursor = rdb.query(TABLE_NAME, null, null, null, null
                , null, null);
        while(cursor.moveToNext()){
            User user = new User();
            user.setName(cursor.getString(0));
            users.add(user);
        }
        return users;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
