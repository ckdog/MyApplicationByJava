package com.example.chapter04.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.chapter04.domain.BookInfo;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(BookInfo... book);

    @Delete
    void delete(BookInfo... book);

    @Update
    void update(BookInfo... book);

    @Query("select * from bookinfo")
    List<BookInfo> queryAll();

    @Query("select * from BookInfo where name=:name order by id desc limit 1")
    BookInfo queryByName(String name);
}
