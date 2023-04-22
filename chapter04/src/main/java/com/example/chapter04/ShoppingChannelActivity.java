package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter04.database.ShoppingDBHelper;
import com.example.chapter04.domain.GoodsInfo;

import java.util.List;

public class ShoppingChannelActivity extends AppCompatActivity {

    private ShoppingDBHelper shoppingDBHelper;
    private TextView tv_title;
    private TextView tv_goods_num;
    private GridLayout gl_channel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_channel);
        init();
        //从数据库查询商品信息
        showGoods();

    }

    //跟用户交互之前执行onresume
    @Override
    protected void onResume() {
        super.onResume();

        int countInCart = getCountInCart();
        MyApplication.getInstance().countInCart = countInCart;
        tv_goods_num.setText(String.valueOf(MyApplication.getInstance().countInCart));
    }

    private int getCountInCart(){
        return shoppingDBHelper.getCountInCart();
    }

    private void showGoods(){
        //商品条目是一个线性布局，设置布局的宽度为屏幕一半
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        //设置布局参数
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthPixels / 2, LinearLayout.LayoutParams.WRAP_CONTENT);
        List<GoodsInfo> allGoods = shoppingDBHelper.getAllGoods();
        for (GoodsInfo good : allGoods) {
            //获取布局文件item_goods.xml的跟视图
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_good, null);
            ImageView imageView = itemView.findViewById(R.id.iv_view);
            TextView titleName = itemView.findViewById(R.id.tv_view);
            TextView count = itemView.findViewById(R.id.count);
            Button btn = itemView.findViewById(R.id.btnadd);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //添加购物车
                    addToCart(good.getId(), good.getName());
                    int num = MyApplication.getInstance().countInCart + 1;
                    MyApplication.getInstance().countInCart = num;
                    tv_goods_num.setText(String.valueOf(num));
                }
            });
            imageView.setImageURI(Uri.parse(good.getPicPath()));
            titleName.setText(good.getName());
            count.setText(String.valueOf(good.getPrice()));

            //设置网格布局的参数,把商品视图添加到网格布局
            gl_channel.addView(itemView, layoutParams);
        }
    }

    private void init(){
        shoppingDBHelper = ShoppingDBHelper.getInstance(this);
        shoppingDBHelper.openReadDB();
        shoppingDBHelper.openWriteDB();
        tv_title = findViewById(R.id.id_tvtitle);
        tv_title.setText("手机商城");
        tv_goods_num = findViewById(R.id.tv_goods_num);
        gl_channel = findViewById(R.id.gl_channel);

    }

    private void addToCart(int id, String name){

        shoppingDBHelper.insertCart(id);
        Toast.makeText(this, "已添加到购物车", Toast.LENGTH_SHORT).show();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        shoppingDBHelper.closeLink();
    }
}