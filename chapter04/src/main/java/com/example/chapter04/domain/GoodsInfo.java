package com.example.chapter04.domain;

import androidx.room.Dao;

import com.example.chapter04.R;

import java.util.ArrayList;
import java.util.List;

@Dao
public class GoodsInfo {

    private int id;
    private String name;
    private String description;
    private float price;
    //大图保存路径
    private String picPath;
    //大图资源编号
    private int pic;

    //声明手机商品名称数组
    private static String[] mNameArr = {"iPhone11", "Mate30", "小米10", "OPPO Reno3", "vivo X30", "荣耀30s"};

    private static String[] mDescArr = {"Apple iPhone11 256G 绿色 4g全网通",
            "华为 HUAWEI Mate30 8+256G 丹霞红 5g全网通 全面屏手机",
            "小米 MI10 8+256G 钛银黑 5G 游戏拍照手机",
            "OPPO Reno3 8+256g 蓝色星夜 双模5G",
            "vivo X30 8+256g 绯云 5g全网通", "荣耀30s 8+256g 蝶羽红 5g芯片"};

    private static int[] mPriceArr = {6299, 4999, 3999, 2999, 2998, 2399};

    //声明手机商品的大图数组
    private static int[] mPicArr = {R.drawable.iphone, R.drawable.huawei, R.drawable.xiaomi, R.drawable.oppo, R.drawable.vivo, R.drawable.rongyao};

    public static List<GoodsInfo> getDefaultList() {
        List<GoodsInfo> list = new ArrayList<>();
        for (int i = 0; i < mNameArr.length; i++) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setId(i);
            goodsInfo.setName(mNameArr[i]);
            goodsInfo.setDescription(mDescArr[i]);
            goodsInfo.setPrice(mPriceArr[i]);
            goodsInfo.setPic(mPicArr[i]);
            list.add(goodsInfo);
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
