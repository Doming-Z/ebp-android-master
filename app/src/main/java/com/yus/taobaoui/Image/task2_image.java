package com.yus.taobaoui.Image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.R;

public class task2_image extends android.support.v7.widget.AppCompatImageView {
    private task2_image task_2;

    public task2_image(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public task2_image(Context context) {
        super(context);
        init(context);
    }

    public task2_image(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        task_2 =(task2_image)findViewById(R.id.task_2);
        Picasso.with(context)
                .load("https://www.zhouzhihui.cn/media/avatar/default.jpg")
                .into(task_2);
    }

}
