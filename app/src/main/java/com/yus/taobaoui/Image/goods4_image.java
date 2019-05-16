package com.yus.taobaoui.Image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.R;

public class goods4_image extends android.support.v7.widget.AppCompatImageView {
    private goods4_image goods_4;

    public goods4_image(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public goods4_image(Context context) {
        super(context);
        init(context);
    }

    public goods4_image(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        goods_4 =(goods4_image)findViewById(R.id.goods_4);
        Log.i("1111",goods_4+"");
        Picasso.with(context)
                .load( "http://www.zhouzhihui.cn/media/goods/images/boluo1.jpg")
                .into(goods_4);
    }

}
