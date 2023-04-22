package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter04.R;
import com.example.chapter04.utils.ViewUtils;

public class EditActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private EditText tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        tx = findViewById(R.id.user);
        tx.setOnFocusChangeListener(this);
        tx.addTextChangedListener(new HideTextWatcher(tx));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            Editable text = tx.getText();
            if(text.length() < 5){
                //编辑框请求焦点， 将光标移回手机号码编辑框
                tx.requestFocus();
                //吐司事件提示
                Toast.makeText(this, "请输入5位以上用户名称", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public class HideTextWatcher implements TextWatcher{

        private EditText edit;
        private TextView tv;

        public HideTextWatcher(EditText edit) {
            this.edit = edit;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            if(str.length() >=10){
                //隐藏输入法键盘
                ViewUtils.hideInputMethod(EditActivity.this, edit);
            }
        }
    }
}