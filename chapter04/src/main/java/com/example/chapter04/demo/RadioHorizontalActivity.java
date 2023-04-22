package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chapter04.R;

public class RadioHorizontalActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_horizontal);
        RadioGroup rg = findViewById(R.id.idgroup);
        rg.setOnCheckedChangeListener(this);
        tv = findViewById(R.id.rslt);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.male:
                tv.setText("男");
                break;
            case R.id.female:
                tv.setText("女");
                break;
            default:
                break;
        }
    }
}