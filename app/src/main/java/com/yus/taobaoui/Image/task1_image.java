package com.yus.taobaoui.Image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.R;

public class task1_image extends android.support.v7.widget.AppCompatImageView {
    private task1_image task_1;

    public task1_image(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public task1_image(Context context) {
        super(context);
        init(context);
    }

    public task1_image(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        task_1 =(task1_image)findViewById(R.id.task_1);
        Picasso.with(context)
                .load( "http://www.zhouzhihui.cn/media/goods/images/%E8%A5%BF%E7%93%9C2.jpg")
                .into(task_1);
    }

}
