package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chapter04.R;
import com.example.chapter04.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * android 的位图工具是bigmap，app读写bigmap可以使用性能更好的bufferedoutputstream和bufferedinputstream
 * android提供bigmapfactory工具用于读取各种来源的图片
 * 相关方法如下：
 *  decodeResource 从资源文件中获取图片信息
 *  decodeFile 将指定路径的图片读取到bitmap对象
 *  decodeStream 该方法从输入流中读取位图数据
 */
public class ImageWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_write);
        //从指定的资源文件中获取位图对象
        Button btnsave = findViewById(R.id.save);
        Button btnread = findViewById(R.id.read);
        btnsave.setOnClickListener(this);
        btnread.setOnClickListener(this);
        image = findViewById(R.id.image);
    }

    @Override
    public void onClick(View v) {
        String basedir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
        String dir = basedir + File.pathSeparator + "mmexport1681550304030.jpeg";
        switch (v.getId()){
            case R.id.save:
                //获取当前app私有路径
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mmexport1681550304030);
                try {
                    FileUtils.saveImageToExternalFileDir(dir, bitmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.read:

                //方式1
//                    Bitmap bitmap1 = FileUtils.readImageFromExternalFileDir(dir);
//                    image.setImageBitmap(bitmap1);
                //方式2
//                Bitmap bitmap2 = BitmapFactory.decodeFile(dir);
//                image.setImageBitmap(bitmap2);
                //方式3
                image.setImageURI(Uri.parse(dir));
                break;
            default:
                break;
        }
    }
}