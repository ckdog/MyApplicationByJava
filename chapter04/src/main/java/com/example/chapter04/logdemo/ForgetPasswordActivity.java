package com.example.chapter04.logdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chapter04.R;

import java.util.Random;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private String phone;
    private EditText frstCode;
    private EditText secCode;
    private EditText verCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        phone = getIntent().getStringExtra("phone");
        findViewById(R.id.getVerifyCode).setOnClickListener(this);
        frstCode = findViewById(R.id.inptCode);
        secCode = findViewById(R.id.confirmCode);
        verCode = findViewById(R.id.vercode);
        Button btn_getCode = findViewById(R.id.getVerifyCode);
        btn_getCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getVerifyCode:
                String desc = String.format("您的验证码是%s,请勿发送她人防止验证码泄露", new Random().nextInt(999999));
                alertDialog(desc);
                break;
            case R.id.btnupdate:
                String frstC = frstCode.getText().toString();
                String secCd = secCode.getText().toString();
                if(!frstC.equals(secCd)){
//                    alertDialog("输入密码错误");
                    toast("输入密码错误");
                    return;
                }
                if(!verCode.getText().toString().equals("111111")){
//                    alertDialog("输入验证码错误,验证码111111");
                    toast("输入验证码错误，验证码111111");
                    return;
                }
                //返货上个页面并返回新密码
                Intent intent = new Intent();
                intent.putExtra("newpass", frstCode.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void alertDialog(String desc){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示：");
        builder.setMessage(desc);
        builder.setPositiveButton("确认", null);
        builder.create().show();
    }

    private void toast(String desc){
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}