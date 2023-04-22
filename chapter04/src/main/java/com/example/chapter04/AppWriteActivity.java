package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AppWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_write);

        Button btn = findViewById(R.id.addid);
        et = findViewById(R.id.name);
        btn.setOnClickListener(this);
        MyApplication instance = MyApplication.getInstance();
        if(instance.map.size()>0){
            et.setText(instance.map.get("name"));
        }
    }

    @Override
    public void onClick(View v) {
        String name = et.getText().toString();
        MyApplication instance = MyApplication.getInstance();
        instance.map.put("name", name);
    }
}