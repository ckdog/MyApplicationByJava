package com.example.chapter04.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.chapter04.domain.BookInfo;

/**
 * entities 表示数据库有哪些表
 * version 表示数据库的版本号
 * exportschema 表示是否导出数据库信息的json串。 建议设置为false，如果设置为true，还需要指定json。配置在build.gradle中配置
 */
@Database(entities = {BookInfo.class}, version= 1, exportSchema = false)
public abstract class BookDataBase extends RoomDatabase {

    public abstract BookDao bookDao();
}
