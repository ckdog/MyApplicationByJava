package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ButtonLongClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_long_click);
        TextView txt = findViewById(R.id.rslt);
        Button btnsingle = findViewById(R.id.btnClickSingle);
        Button btnLong = findViewById(R.id.btnClickLong);
        btnLong.setOnLongClickListener(view -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            txt.setText("长按点击事件触发 ： " + simpleDateFormat.format(new Date()));
            return true;
        });
        Button strtbtn = findViewById(R.id.strtbtn);
        Button btnforbid = findViewById(R.id.forbidbtn);
        Button btntest = findViewById(R.id.testbtn);
        strtbtn.setOnClickListener(v -> {
            btntest.setEnabled(true);
            btntest.setTextColor(Color.BLACK);
        });
        btnforbid.setOnClickListener(v -> {
            btntest.setEnabled(false);
            btntest.setTextColor(Color.GRAY);
        });
    }
}