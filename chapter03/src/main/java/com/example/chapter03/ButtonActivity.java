package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ButtonActivity extends AppCompatActivity {

    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        txtView = findViewById(R.id.txtid);

    }

    public void doClick(View view){
        System.out.println("点击按钮省高校被点击");
        DateTimeFormatter dtf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
        String datestr =" --  ";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            datestr = LocalDateTime.now().atZone(ZoneId.systemDefault()).format(dtf);
        }
        System.out.println(datestr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sdfstr = sdf.format(new Date());
        System.out.println(sdfstr);

        String str = String.format("%s 您点击按钮 %s", sdfstr, ((Button) view).getText());
        txtView.setText(str);
    }
}