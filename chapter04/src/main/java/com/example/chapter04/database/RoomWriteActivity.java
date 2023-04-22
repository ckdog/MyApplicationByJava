package com.example.chapter04.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chapter04.MyApplication;
import com.example.chapter04.R;
import com.example.chapter04.domain.BookInfo;

import java.util.List;

public class RoomWriteActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etBookName;
    private EditText etAuthor;
    private EditText etPublish;
    private EditText etPrice;
    private Button btnadd;
    private Button btndel;
    private Button btnupd;
    private Button btnqry;
    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_write);
        etBookName = findViewById(R.id.bookname);
        etAuthor = findViewById(R.id.author);
        etPublish = findViewById(R.id.publish);
        etPrice = findViewById(R.id.price);
        btnadd = findViewById(R.id.add);
        btndel = findViewById(R.id.del);
        btnupd = findViewById(R.id.upd);
        btnqry = findViewById(R.id.qry);
        btnadd.setOnClickListener(this);
        btndel.setOnClickListener(this);
        btnqry.setOnClickListener(this);
        btnupd.setOnClickListener(this);
        bookDao = MyApplication.getInstance().getBookDB().bookDao();
    }

    @Override
    public void onClick(View v) {
        BookInfo book = new BookInfo();

        switch (v.getId()){
            case R.id.add:
                book.setAuthor(etAuthor.getText().toString());
                book.setName(etBookName.getText().toString());
                book.setPublish(etPublish.getText().toString());
                book.setPrice(etPrice.getText().toString());
                bookDao.insert(book);
                break;
            case R.id.del:
                book.setId(1);
                bookDao.delete(book);
                break;
            case R.id.upd:
                BookInfo bookInfo = bookDao.queryByName(etBookName.getText().toString());
                bookInfo.setName("修改名称");
                bookDao.update(bookInfo);
                break;
            case R.id.qry:
                List<BookInfo> bookInfos = bookDao.queryAll();
                bookInfos.stream().forEach(b -> {
                    Log.d("TAG", "book: " + b.toString());
                });
                break;
            default:
                break;
        }
    }

    private void toast(String desc){
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}