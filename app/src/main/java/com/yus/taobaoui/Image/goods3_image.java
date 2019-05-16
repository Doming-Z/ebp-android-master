package com.yus.taobaoui.Image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.R;

public class goods3_image extends android.support.v7.widget.AppCompatImageView {
    private goods3_image goods_3;

    public goods3_image(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public goods3_image(Context context) {
        super(context);
        init(context);
    }

    public goods3_image(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        goods_3 =(goods3_image)findViewById(R.id.goods_3);
        Picasso.with(context)
                .load("http://www.zhouzhihui.cn/media/goods/images/putao1.jpg")
                .into(goods_3);
    }

}
