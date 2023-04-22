package com.example.chapter04.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.IDNA;
import android.nfc.cardemulation.CardEmulation;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.chapter04.domain.CartInfo;
import com.example.chapter04.domain.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDBHelper extends SQLiteOpenHelper {

    private static final String SHOP_DB = "shopping.db";
    private static final String TABLE_GOODS_INFO = "goods_info";
    private static final String TABLE_CART_INFO = "cart_info";

    private static final int DB_VERSION = 1;
    private static ShoppingDBHelper shopHelper = null;
    private SQLiteDatabase readDB;
    private SQLiteDatabase writeDB;

    public ShoppingDBHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        //factory 暂时设置为null
        super(context, SHOP_DB, null, DB_VERSION);
    }

    public static ShoppingDBHelper getInstance(Context context){
        if(shopHelper != null){
            return shopHelper;
        }
        shopHelper = new ShoppingDBHelper(context, null);
        return shopHelper;
    }

    public SQLiteDatabase openWriteDB(){
        if(writeDB != null && writeDB.isOpen()) return writeDB;

        writeDB = shopHelper.getWritableDatabase();
        return writeDB;
    }

    public SQLiteDatabase openReadDB(){
        if(readDB != null && readDB.isOpen()) return readDB;
        readDB = shopHelper.getReadableDatabase();
        return readDB;
    }

    public void closeLink(){
        if(writeDB != null && writeDB.isOpen()){
            writeDB.close();
        }
        if(readDB != null && readDB.isOpen()){
            readDB.close();
        }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库
        //创建商品信息表
//        db.execSQL("delete from " + TABLE_GOODS_INFO);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS_INFO +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "NAME VARCHAR NOT NULL," +
                "DESCRIPTION VARCHAR NOT NULL," +
                "PRICE FLOAT NOT NULL," +
                "PIC_PATH VARCHAR NOT NULL);";
        db.execSQL(sql);
        Log.i("[DEBUG]", "商品信息表建表成功");

        //购物车信息表
//        db.execSQL("delete from " + TABLE_CART_INFO);
        sql = "CREATE TABLE IF NOT EXISTS " + TABLE_CART_INFO +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "GOODS_ID INTEGER NOT NULL," +
                "COUNT INTEGER NOT NULL);";
        db.execSQL(sql);
        Log.i("[DEBUG]", "购物车表建表成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<GoodsInfo> getAllGoods(){
        String sql = "select * from " + TABLE_GOODS_INFO;
        List<GoodsInfo> list = new ArrayList<>();
        Cursor cursor = readDB.rawQuery(sql, null);
        while (cursor.moveToNext()){
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setId(cursor.getInt(0));
            goodsInfo.setName(cursor.getString(1));
            goodsInfo.setDescription(cursor.getString(2));
            goodsInfo.setPrice(cursor.getFloat(3));
            goodsInfo.setPicPath(cursor.getString(4));
            list.add(goodsInfo);
        }
        cursor.close();
        return list;
    }


    public void insertGoods(List<GoodsInfo> list) {
        try {
            //插入多条记录  同一个事务中
            writeDB.beginTransaction();
            for (GoodsInfo good : list){
                ContentValues values = new ContentValues();
                values.put("name",  good.getName());
                values.put("description",  good.getDescription());
                values.put("price",  good.getPrice());
                values.put("pic_path",  good.getPicPath());
                writeDB.insert(TABLE_GOODS_INFO, null, values);
            }
            writeDB.setTransactionSuccessful();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            writeDB.endTransaction();
        }
    }

    public void insertCart(int id) {
        CartInfo cartInfo = getCartInfo(id);
        ContentValues values = new ContentValues();
        values.put("goods_id", id);
        if(cartInfo == null){
            values.put("count", 1);
            writeDB.insert(TABLE_CART_INFO, null, values);
        }else{
            values.put("id", id);
            values.put("count", cartInfo.getCount() + 1);
            writeDB.update(TABLE_CART_INFO, values, "id = ?", new String[]{String.valueOf(id)});
        }
    }

    private CartInfo getCartInfo(int id){
        Cursor cursor = readDB.query(TABLE_CART_INFO, null, "goods_id = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToNext()){
            CartInfo cartInfo = new CartInfo();
            cartInfo.setId(cursor.getInt(0));
            cartInfo.setGoodsId(cursor.getInt(1));
            cartInfo.setCount(cursor.getInt(2));
            return cartInfo;
        }
        return null;
    }

    public int getCountInCart() {
        String sql = "select sum(count) from " + TABLE_CART_INFO;
        Cursor cursor = readDB.rawQuery(sql, null);
        if(cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }
}
