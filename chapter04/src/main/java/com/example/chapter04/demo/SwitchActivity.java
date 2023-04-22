package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chapter04.R;

public class SwitchActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView tvrslt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        TextView tv = findViewById(R.id.id_tv);
        Switch switchbtn = findViewById(R.id.switchid);
        tvrslt = findViewById(R.id.id_rlst);
        switchbtn.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String desc = String.format("当前开关已经%s", isChecked ? "打开" : "关闭");
        tvrslt.setText(desc);
    }
}