package com.example.chapter04.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.chapter04.R;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        findViewById(R.id.alertdialog).setOnClickListener(this);
        viewById = findViewById(R.id.tv_alert);
    }

    @Override
    public void onClick(View v) {
        //创建提醒对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提醒:");
        builder.setMessage("您确定要继续操作吗？");
        builder.setPositiveButton("残忍卸载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewById.setText("虽然不舍得，但是我走了");
            };
        });
        builder.setNegativeButton("我在想想", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewById.setText("我将继续陪伴你");
            };
        });
        builder.create().show();
    }
}