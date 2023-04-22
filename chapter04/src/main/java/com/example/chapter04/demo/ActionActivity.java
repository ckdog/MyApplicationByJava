package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.chapter04.R;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_txt).setOnClickListener(this);
        findViewById(R.id.btn_myact).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_dial:
                intent.setAction(Intent.ACTION_DIAL);
                Uri parse = Uri.parse("tel:" + "12345");
                intent.setData(parse);
                startActivity(intent);
                break;
            case R.id.btn_txt:
                intent.setAction(Intent.ACTION_SENDTO);
                Uri urimsg = Uri.parse("smsto:" + "发送短信msg");
                intent.setData(urimsg);
                startActivity(intent);
                break;
            case R.id.btn_myact:
                intent.setAction("android.intent.action.MyName");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}