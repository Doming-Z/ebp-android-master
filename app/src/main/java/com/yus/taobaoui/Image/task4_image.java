package com.yus.taobaoui.Image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.R;

public class task4_image extends android.support.v7.widget.AppCompatImageView {
    private task4_image task_4;

    public task4_image(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public task4_image(Context context) {
        super(context);
        init(context);
    }

    public task4_image(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        task_4 =(task4_image)findViewById(R.id.task_4);
        Picasso.with(context)
                .load("https://www.zhouzhihui.cn/media/avatar/default.jpg")
                .into(task_4);
    }

}
