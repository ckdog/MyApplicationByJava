package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chapter04.R;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        TextView tv = findViewById(R.id.resource);
        String val = getResources().getString(R.string.weather);
        tv.setText(val);

        TextView tv2 = findViewById(R.id.metadata);
        //getpackagemanager 获取包管理器
        PackageManager packageManager = getPackageManager();
        //调用包管理器方法 getActivityInfo 获取当前活动对象
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            //活动信息对象的metadata是bundle包类型，调用getstring获取指定参数名称
            Bundle metaData = activityInfo.metaData;
            String sdkname = metaData.getString("sdkname");
            tv2.setText(sdkname);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}