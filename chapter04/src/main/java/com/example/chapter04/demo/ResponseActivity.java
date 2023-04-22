package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter04.R;

import java.util.Date;

public class ResponseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        TextView tv = findViewById(R.id.txtreq);
        Bundle bundle = getIntent().getExtras();
        String req_cont = bundle.getString("req_cont");
        tv.setText(req_cont);

        //应答
        Button btn = findViewById(R.id.response);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("resp_time", new Date().toString());
        bundle.putString("desc","我还没睡");
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        //结束当前活动页面
        finish();
    }
}