package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.chapter04.R;

public class ReciveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive);
        TextView tv = findViewById(R.id.id_rec);
        Bundle bundle = getIntent().getExtras();
        String request_time = bundle.getString("request_time");
        String request_content = bundle.getString("request_content");
        String extra_req = getIntent().getStringExtra("extra_req");
        String content = String.format("接收到消息:\n请求时间：%s \n请求内容：%s\n extra:%s", request_time, request_content, extra_req);
        tv.setText(content);
    }
}