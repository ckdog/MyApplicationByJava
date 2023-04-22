package com.example.myapplicationbyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView viewById = findViewById(R.id.tv);
        viewById.setText("你好，第一个android APP HELLO WORLD");

        Button turnBtn = findViewById(R.id.turnid);
        turnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //新建意图
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NewMainActivity.class);
                startActivity(intent);
            }
        });

    }
}