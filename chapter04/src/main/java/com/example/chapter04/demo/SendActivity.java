package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.chapter04.R;

import java.util.Date;

public class SendActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        tv_send = findViewById(R.id.send_id);
        findViewById(R.id.btn_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, ReciveActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("request_time", new Date().toString());
        bundle.putString("request_content", tv_send.getText().toString());
        intent.putExtra("extra_req", "req_txt_1");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}