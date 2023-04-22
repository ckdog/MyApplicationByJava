package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chapter03.utils.UnitTransferUtils;

public class ViewBorderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_border);
        TextView tv = findViewById(R.id.borderid);
        //获取tv的布局参数（包含高度和宽度）
        ViewGroup.LayoutParams layoutParams = tv.getLayoutParams();
        //设置边界参数 单位默认是px， 设置需要将dp其他单位映射为px
        layoutParams.width = UnitTransferUtils.dip2px(this, 300);
        //设置布局
        tv.setLayoutParams(layoutParams);
    }
}