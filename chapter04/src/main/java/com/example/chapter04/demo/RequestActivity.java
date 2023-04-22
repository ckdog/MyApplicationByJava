package com.example.chapter04.demo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.chapter04.R;

public class RequestActivity extends AppCompatActivity implements View.OnClickListener {

    private String text = "你睡了吗？";
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        TextView tv = findViewById(R.id.id_txt);
        TextView tv_resp = findViewById(R.id.id_resp);
        findViewById(R.id.btn_req).setOnClickListener(this);
        tv.setText(text);
        //注册activity协议      (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>())
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result == null) return;
            Intent data = result.getData();
            if(data != null && result.getResultCode() == Activity.RESULT_OK){
                Bundle extras = data.getExtras();
                String desc = extras.getString("desc");
                String resp_time = extras.getString("resp_time");
                String response = String.format("返回信息：\n%s \n %s", resp_time, desc);
                tv_resp.setText(response);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ResponseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("req_cont", text);
        intent.putExtras(bundle);
        launcher.launch(intent);
    }


}