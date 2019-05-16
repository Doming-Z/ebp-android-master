package com.yus.taobaoui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.R;

public class MyImageView extends android.support.v7.widget.AppCompatImageView {
    private MyImageView goods_1;

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init_goods_1(context);
    }
    public MyImageView(Context context) {
        super(context);
        init_goods_1(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init_goods_1(context);
    }

    public void init_goods_1(Context context){
        goods_1 =(MyImageView)findViewById(R.id.goods_1);
        Log.i("1111",goods_1+"");
        Picasso.with(context)
                .load("https://pic.cnblogs.com/avatar/1142647/20170416093225.png")
                .into(goods_1);
    }

}
