package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.chapter04.R;

public class ChckBoxActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chck_box);
        CheckBox ck_system = findViewById(R.id.ck_system);
        CheckBox ck_custom = findViewById(R.id.ck_custom);

        ck_system.setOnCheckedChangeListener(this);
        ck_custom.setOnCheckedChangeListener(this);
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String desc = String.format("你%s了选项", isChecked ? "勾选":"取消了");
        buttonView.setText(desc);
    }
}