package com.example.chapter04.logdemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter04.R;
import com.example.chapter04.utils.ViewUtils;

import java.util.Random;

public class LoginActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private TextView tv_password;
    private EditText ed_password;
    private Button btn_forget;
    private CheckBox box_forget;
    private EditText edPhone;
    private RadioButton rdpassword;
    private RadioButton rdVerifyCode;
    private ActivityResultLauncher<Intent> launcher;
    private Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        formatCheckBoxIcon();
        tv_password = findViewById(R.id.tv_password);
        ed_password = findViewById(R.id.edpassword);
        btn_forget = findViewById(R.id.forget_password);
        box_forget = findViewById(R.id.id_rem_pasw);
        rdpassword = findViewById(R.id.login_by_pasw);
        rdVerifyCode = findViewById(R.id.login_by_verifycode);
        loginBtn = findViewById(R.id.id_login);
        RadioGroup radioGroup = findViewById(R.id.loginrg);
        //添加监听事件
        radioGroup.setOnCheckedChangeListener(this);
        //添加文本改变事件
        edPhone = findViewById(R.id.edphone);
        edPhone.addTextChangedListener(new HideWatcher(edPhone, 11));
        btn_forget.setOnClickListener(this);
        box_forget.setOnCheckedChangeListener(this);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Log.d("call back", "忘记密码界面返回: ");
                Intent data = result.getData();
                if(data != null && result.getResultCode() == Activity.RESULT_OK){
                    String newpass = data.getStringExtra("newpass");
                    Toast.makeText(LoginActivity.this, "新密码是"+newpass, Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginBtn.setOnClickListener(this);

    }



    private void formatCheckBoxIcon(){
        Drawable dw = getResources().getDrawable(R.drawable.checkbox_48px_icon);
        dw.setBounds(0, 0,50, 50);
        CheckBox cb = findViewById(R.id.id_rem_pasw);
        cb.setCompoundDrawables(dw, null, null, null);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.login_by_pasw:
                tv_password.setText(getString(R.string.login_by_pasword));
                ed_password.setText(getString(R.string.forget_password));
                ed_password.setHint(getString(R.string.password_hint));
                btn_forget.setText(getString(R.string.forget_password));
                box_forget.setVisibility(View.VISIBLE);
                break;
            case R.id.login_by_verifycode:
                tv_password.setText(getString(R.string.verifycode));
                ed_password.setText(getString(R.string.sendcode));
                ed_password.setHint(getString(R.string.inpt_vercod));
                btn_forget.setText(getString(R.string.sendcode));
                box_forget.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_password:
                if(edPhone.toString().length() < 11){
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_LONG).show();
                    return;
                }
                if(rdpassword.isChecked()){
                    //如果选择了密码登录，跳转到忘记密码界面
                    Intent intent = new Intent(this, ForgetPasswordActivity.class);
                    intent.putExtra("phone", edPhone.toString());
                    launcher.launch(intent);
                    return;
                }
                if(rdVerifyCode.isChecked()){
                    //跳转到验证码登录界面
                    String desc = String.format("%06d", new Random().nextInt(999999));
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("请记录验证码");
                    builder.setMessage("手机号码：" + edPhone.getText() + ", 本次验证码是：" + desc);
                    builder.setPositiveButton("ok", null);
                    builder.create().show();
                }
                break;
            case R.id.id_login:
                if(rdpassword.isChecked()){
                    if(!ed_password.getText().toString().equals("123456")){
                        String desc = String.format("输入密码 ：%s, 验证结果：%s", ed_password.getText(), String.valueOf(!ed_password.getText().equals("123456")));
                        Toast.makeText(LoginActivity.this, desc, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    logSuccess();

                }else if(rdVerifyCode.isChecked()){
                    if(!ed_password.getText().toString().equals("123456")){
                        Toast.makeText(LoginActivity.this, "验证码输入错误 "+ed_password.getText()+" ：123456", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    logSuccess();
                }
                break;
            default:
                break;
        }
    }

    private void logSuccess(){
        String desc = String.format("您的手机号码是：%s, 欢迎登录", edPhone.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("欢迎登录");
        builder.setMessage(desc);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //结束当前界面
                finish();
            }
        });
        builder.setNegativeButton("我再看看", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //选中记住密码
        SharedPreferences config = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = config.edit();
        edit.putString("password", ed_password.getText().toString());
        edit.commit();
    }


    public class HideWatcher implements TextWatcher{

        private EditText et;
        private int length;

        public HideWatcher(EditText et, int length) {
            this.et = et;
            this.length = length;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //输入达到指定的长度，自动隐藏输入法
            Log.d("debug", "手机号：" + et.toString());
            System.out.println("phone num: " + et.toString());
            if(et.getText().length() >= length){
                hideInputMedthod(LoginActivity.this, et);
            }
        }



        //隐藏软键盘
        private void hideInputMedthod(Activity act, View view){
            InputMethodManager systemService = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
            systemService.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}