package com.example.chapter04.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.chapter04.R;

public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        //xml方式保存map结构 路径/data/data/包路径/应用
        SharedPreferences config = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = config.edit();
        edit.putString("name", "tom");
        edit.putInt("age", 19);
        edit.putString("Sex", "male");
        //提交保存
        edit.commit();
        //读取文件
        String name = config.getString("name", "");
        String age = config.getString("age", String.valueOf(0));
        Log.d("LOGTAG", "config shared preference :" + name +"  " + age);

    }
}