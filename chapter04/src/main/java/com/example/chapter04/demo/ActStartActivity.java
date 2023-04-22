package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chapter04.R;

public class ActStartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_start);
        Log.d("tag", "创建");
        Button btn = findViewById(R.id.skipbtn);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("tag", "开始运行");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        //activity跳转方式  显示调用
        //1.构造函数方式
        startActivity(new Intent(this, ActFinishActivity.class));
        //意图对象的setclass方式
//        Intent intent = new Intent();
//        intent.setClass(this, ActFinishActivity.class);
        //setComoponent方式
//        ComponentName componentName = new ComponentName(this, ActFinishActivity.class);
//        intent.setComponent(componentName);
//        startActivity(intent);
    }
}