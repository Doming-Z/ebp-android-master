package com.yus.taobaoui.Image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.R;

public class goods2_image extends android.support.v7.widget.AppCompatImageView {
    private goods2_image goods_2;

    public goods2_image(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public goods2_image(Context context) {
        super(context);
        init(context);
    }

    public goods2_image(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        goods_2 =(goods2_image)findViewById(R.id.goods_2);
        Picasso.with(context)
                .load("http://www.zhouzhihui.cn/media/goods/images/mangguo1.jpg")
                .into(goods_2);
    }

}
