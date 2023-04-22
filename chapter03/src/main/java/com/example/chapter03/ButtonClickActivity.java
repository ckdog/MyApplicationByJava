package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ButtonClickActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click);
        Button btn = findViewById(R.id.btnClickSingle);
        txtView = findViewById(R.id.rslt);
        btn.setOnClickListener(new MyOnClickLinstener(txtView));
        Button btnpub = findViewById(R.id.btnClickPub);
        btnpub.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnClickPub){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

            txtView.setText("公共点击事件触发 ： " + simpleDateFormat.format(new Date()));
        }
    }


    /**
     * static 静态防止内存泄漏
     */
    static class MyOnClickLinstener implements View.OnClickListener{

        private final TextView txtView;

        public MyOnClickLinstener(TextView txtView) {
            this.txtView = txtView;
        }

        @Override
        public void onClick(View view) {
            System.out.println(" -- 触发点击事件");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            System.out.println(simpleDateFormat.format(new Date()));
            txtView.setText(simpleDateFormat.format(new Date()));
        }
    }
}