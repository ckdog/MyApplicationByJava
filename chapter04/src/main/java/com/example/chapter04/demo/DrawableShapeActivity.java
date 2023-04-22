package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.chapter04.R;

public class DrawableShapeActivity extends AppCompatActivity implements View.OnClickListener {

    private View v_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_shape);
        v_content = findViewById(R.id.id_view);
        findViewById(R.id.id_btn1).setOnClickListener(this);
        findViewById(R.id.id_btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_btn1:
                v_content.setBackgroundResource(R.drawable.shape_rect_file);
                break;
            case R.id.id_btn2:
                v_content.setBackgroundResource(R.drawable.shape_oval_file);
                break;
            default:
                break;
        }
    }
}